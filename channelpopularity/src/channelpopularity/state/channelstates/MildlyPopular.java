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
	public void processAdRequest(int length) {
		// TODO Auto-generated method stub
	}

	@Override
	protected StateName getName() {
		return StateName.MILDLY_POPULAR;
	}

}
