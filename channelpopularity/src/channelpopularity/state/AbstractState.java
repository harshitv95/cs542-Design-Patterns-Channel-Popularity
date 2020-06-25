package channelpopularity.state;

import channelpopularity.channel.video.VideoMetrics;
import channelpopularity.context.ContextI;
import channelpopularity.util.ResultsI;

public abstract class AbstractState implements StateI {

	protected final ContextI context;
	protected final ResultsI results;

	public AbstractState(ContextI context, ResultsI results) {
		this.context = context;
		this.results = results;
	}

	protected double calcPopularity(int oldVidCount, double newPopularityScore) {
		double avgPopularity = context.getVideoStore().count() == 0 ? 0
				: ((context.getPopularity() * oldVidCount) + newPopularityScore) / context.getVideoStore().count();
		context.setPopularity(avgPopularity);
		return avgPopularity;
	}

	@Override
	public StateName updateState() {
		if (context.getPopularity() == 0)
			return StateName.UNPOPULAR;
		for (StateName state : StateName.values()) {
			if (state.getLowerBoundExclusive() < context.getPopularity()
					&& context.getPopularity() <= state.getUpperBoundInclusive()) {
				context.setState(state);
				return state;
			}
		}
		throw new IllegalArgumentException("Failed to update state : Invalid popularity score ["
				+ context.getPopularity() + "], unable to determine new state");
	}

	@Override
	public void processAdRequest(String videoName, int length) {
		if (!context.getVideoStore().exists(videoName))
			throw new RuntimeException("Video [" + videoName + "] does not exist, could not display ad");

		results.printLn(
				this.getName() + "__AD_REQUEST::" + (this.approveAd(videoName, length) ? "APPROVED" : "REJECTED"));
	}

	@Override
	public void addVideo(String videoName) {
		int oldVidCount = context.getVideoStore().count();
		context.getVideoStore().add(videoName);
		calcPopularity(oldVidCount, 0);
		results.printLn(this.getName() + "__VIDEO_ADDED::" + videoName);
		context.setState(updateState());
	}

	@Override
	public void removeVideo(String videoName) {
		int oldVidCount = context.getVideoStore().count();
		VideoMetrics video = context.getVideoStore().remove(videoName);
		calcPopularity(oldVidCount, -video.calcPopularityScore());
		results.printLn(this.getName() + "__VIDEO_REMOVED::" + videoName);
		context.setState(updateState());
	}

	@Override
	public void updateVideoMetrics(VideoMetrics newMetrics) {
		int oldVidCount = context.getVideoStore().count();
		context.getVideoStore().updateMetrics(newMetrics.getVideoName(), newMetrics);
		calcPopularity(oldVidCount, newMetrics.calcPopularityScore());
		double ps = context.getPopularity();
		results.printLn(
				this.getName() + "__POPULARITY_SCORE_UPDATE::" + (ps % 1 == 0 ? ps : String.format("%.02f", ps)));
		context.setState(updateState());
	}

	protected abstract String getName();

	@Override
	public String toString() {
		return "{state: " + this.getName() + "}";
	}

}
