package channelpopularity.state;

import channelpopularity.state.channelstates.HighlyPopular;
import channelpopularity.state.channelstates.MildlyPopular;
import channelpopularity.state.channelstates.UltraPopular;
import channelpopularity.state.channelstates.Unpopular;

/**
 * enum listing the names of various States using in current Context
 * 
 * @author Harshit Vadodaria
 *
 */
public enum StateName {

	UNPOPULAR(Unpopular.class, 0, 1000),
	MILDLY_POPULAR(MildlyPopular.class, 1001, 10000),
	HIGHLY_POPULAR(HighlyPopular.class, 10001, 100000),
	ULTRA_POPULAR(UltraPopular.class, 100001, Integer.MAX_VALUE);

	private final Class<? extends StateI> clazz;
	private final int min, max;

	StateName(Class<? extends StateI> clazz, int minInclusive, int maxInclusive) {
		this.clazz = clazz;
		this.min = minInclusive;
		this.max = maxInclusive;
	}

	/**
	 * Returns a reference of Type Class of the {@link StateI} represented by the
	 * instantiated StateName
	 * 
	 * @return instance of {@link channelpopularity.state.StateI}
	 */
	public Class<? extends StateI> getStateClass() {
		return this.clazz;
	}

	/**
	 * Returns the Lower Bound (<b>Inclusive</b>) for the instantiated StateName
	 * 
	 * @return {@code int}
	 */
	public int getLowerBound() {
		return this.min;
	}

	/**
	 * Returns the Upper Bound (<b>Inclusive</b>) for the instantiated StateName
	 * 
	 * @return
	 */
	public int getUpperBound() {
		return this.max;
	}
}
