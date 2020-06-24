package channelpopularity.state.channelstates;

import channelpopularity.context.ContextI;
import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public class Unpopular extends AbstractState {


	public Unpopular(ContextI context, Results results) {
		super(context, results);
	}

	@Override
	protected String getName() {
		return StateName.UNPOPULAR.name();
	}

	@Override
	public boolean approveAd(String videoName, int length) {
		return 1 < length && length <= 10;
	}

}
