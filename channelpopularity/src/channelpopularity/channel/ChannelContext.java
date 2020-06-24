package channelpopularity.channel;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.channel.video.VideoMetrics;
import channelpopularity.channel.video.VideoStoreI;
import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;

public class ChannelContext implements ContextI {

	protected StateI state;
	protected final SimpleStateFactoryI factory;
	protected final Map<StateName, StateI> statesCache;
	protected final Results outputRes;

	protected final VideoStoreI videos;

	protected double avgPopularityScore;

	public ChannelContext(VideoStoreI videoStore, SimpleStateFactoryI factory, Results outputRes) {
		this.factory = factory;
		this.statesCache = new HashMap<>();
		this.outputRes = outputRes;
		this.videos = videoStore;

		this.setState(StateName.UNPOPULAR);
	}

	@Override
	public void setState(StateName stateName) {
		if (!statesCache.containsKey(stateName))
			statesCache.put(stateName, this.factory.create(stateName, this, outputRes));
		this.state = statesCache.get(stateName);
	}

	public StateI getState() {
		return this.state;
	}

	@Override
	public void action(Operation op, Map<Operation.ParamKeys, String> params) {
		switch (op) {
		case ADD_VIDEO:
			state.addVideo((String) params.get(Operation.ParamKeys.VIDEONAME));
			break;
		case REMOVE_VIDEO:
			state.removeVideo((String) params.get(Operation.ParamKeys.VIDEONAME));
			break;
		case METRICS:
			state.updateVideoMetrics(new VideoMetrics(params.get(Operation.ParamKeys.VIDEONAME).toString(), params));
			break;
		case AD_REQUEST:
			state.processAdRequest((String) params.get(Operation.ParamKeys.VIDEONAME),
					Integer.parseInt(params.get(Operation.ParamKeys.LEN)));
			break;
		}
	}

	@Override
	public VideoStoreI getVideoStore() {
		return this.videos;
	}

	@Override
	public double getPopularity() {
		return avgPopularityScore;
	}

	@Override
	public void setPopularity(double popularityScore) {
		avgPopularityScore = popularityScore;
	}

}
