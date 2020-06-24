package channelpopularity.state.channelstates;

import channelpopularity.context.ContextI;
import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public class MildlyPopular extends AbstractState {

	public MildlyPopular(ContextI context, Results results) {
		super(context, results);
	}

	@Override
	protected String getName() {
		return StateName.MILDLY_POPULAR.name();
	}

	@Override
	public boolean approveAd(String videoName, int length) {
		return 1 < length && length <= 20;
	}

}
