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

	UNPOPULAR(Unpopular.class, 0, 1000.0),
	MILDLY_POPULAR(MildlyPopular.class, 1000.0, 10000.0),
	HIGHLY_POPULAR(HighlyPopular.class, 10000.0, 100000.0),
	ULTRA_POPULAR(UltraPopular.class, 100000.0, Integer.MAX_VALUE);

	private final Class<? extends StateI> clazz;
	private final double min, max;

	StateName(Class<? extends StateI> clazz, double minInclusive, double maxInclusive) {
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
	 * Returns the Lower Bound (<b>Exclusive</b>) for the instantiated StateName
	 * 
	 * @return {@code double}
	 */
	public double getLowerBoundExclusive() {
		return this.min;
	}

	/**
	 * Returns the Upper Bound (<b>Inclusive</b>) for the instantiated StateName
	 * 
	 * @return {@code double}
	 */
	public double getUpperBoundInclusive() {
		return this.max;
	}
}
