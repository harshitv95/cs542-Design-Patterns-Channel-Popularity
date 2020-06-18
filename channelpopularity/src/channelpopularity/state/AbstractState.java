package channelpopularity.state;

public abstract class AbstractState implements StateI {

	protected final String AD_ACCEPTED = "ACCEPTED", AD_REJECTED = "REJECTED";

	public int calcPopularity() {
		// TODO
		return 0;
	}

	@Override
	public StateName updateState(int popularityScore) {
		for (StateName state : StateName.values()) {
			if (state.getLowerBound() <= popularityScore && popularityScore <= state.getUpperBound())
				return state;
		}
		throw new IllegalArgumentException("Failed to update state : Invalid popularity score [" + popularityScore
				+ "], unable to determine new state");
	}

	abstract public String processAdRequest(int length);

}
