package channelpopularity.state;

import channelpopularity.channel.video.VideoMetrics;
import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

public abstract class AbstractState implements StateI {

	protected final ContextI context;
	protected final Results results;

	public AbstractState(ContextI context, Results results) {
		this.context = context;
		this.results = results;
	}

	protected final String AD_ACCEPTED = "ACCEPTED", AD_REJECTED = "REJECTED";

	protected double calcPopularity(int oldVidCount, double newPopularityScore) {
		double avgPopularity = ((context.getPopularity() * oldVidCount) + newPopularityScore)
				/ context.getVideoStore().count();
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

		results.printToAll(
				this.getName() + "__AD_REQUEST::" + (this.approveAd(videoName, length) ? "APPROVED" : "REJECTED"));
	}

	@Override
	public void addVideo(String videoName) {
		int oldVidCount = context.getVideoStore().count();
		context.getVideoStore().add(videoName);
		calcPopularity(oldVidCount, 0);
		results.printToAll(this.getName() + "__VIDEO_ADDED::" + videoName);
		context.setState(updateState());
	}

	@Override
	public void removeVideo(String videoName) {
		int oldVidCount = context.getVideoStore().count();
		VideoMetrics video = context.getVideoStore().remove(videoName);
		calcPopularity(oldVidCount, -video.calcPopularityScore());
		results.printToAll(this.getName() + "__VIDEO_REMOVED::" + videoName);
		context.setState(updateState());
	}

	@Override
	public void updateVideoMetrics(VideoMetrics video) {
		int oldVidCount = context.getVideoStore().count();
		context.getVideoStore().updateMetrics(video.getVideoName(), video);
		calcPopularity(oldVidCount, 0);
		results.printToAll(this.getName() + "__POPULARITY_SCORE_UPDATE::" + context.getPopularity());
		context.setState(updateState());
	}

	protected abstract StateName getName();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// TODO toString in all classes
		return super.toString();
	}
	
	

}
