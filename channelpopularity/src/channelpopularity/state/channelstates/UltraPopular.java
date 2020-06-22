package channelpopularity.state.channelstates;

import channelpopularity.context.ContextI;
import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public class UltraPopular extends AbstractState {

	public UltraPopular(ContextI context, Results results) {
		super(context, results);
	}

	@Override
	public void processAdRequest(int length) {
		// TODO Auto-generated method stub
	}

	@Override
	protected StateName getName() {
		return StateName.ULTRA_POPULAR;
	}

}
