package channelpopularity.channel;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;
import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;

public class ChannelContext implements ContextI {

	private AbstractState state;
	private final SimpleStateFactoryI factory;
	private final Map<StateName, AbstractState> statesCache;
	private final Results outputRes;

	public ChannelContext(SimpleStateFactoryI factory, Results outputRes) {
		this.factory = factory;
		this.statesCache = new HashMap<>();
		this.outputRes = outputRes;
		
		this.setState(StateName.UNPOPULAR);
	}

	@Override
	public void setState(StateName stateName) {
		if (!statesCache.containsKey(stateName))
			statesCache.put(stateName, (AbstractState) this.factory.create(stateName, outputRes));
		this.state = statesCache.get(stateName);
	}

	@Override
	public void action(Operation op, Map<String, Integer> values) {
		switch (op) {
		case AD_REQUEST:
			state.processAdRequest(values.get("AD_LENGTH"));
			break;
		}
	}

}
