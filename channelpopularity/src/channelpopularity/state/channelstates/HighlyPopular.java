package channelpopularity.state.channelstates;

import channelpopularity.context.ContextI;
import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public class HighlyPopular extends AbstractState {


	public HighlyPopular(ContextI context, Results results) {
		super(context, results);
	}

	@Override
	protected StateName getName() {
		return StateName.HIGHLY_POPULAR;
	}

	@Override
	public boolean approveAd(String videoName, int length) {
		// TODO Auto-generated method stub
		return false;
	}

}
