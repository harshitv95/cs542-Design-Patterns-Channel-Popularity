package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

/**
 * Single-mehod interface following Simple Factory Pattern, used to create an
 * instance of {@link StateI channelpopularity.state.StateI} based on some
 * params, with the use of the {@link #create(StateName, Object...)} method
 * 
 * @author Harshit Vadodaria
 *
 */
public interface SimpleStateFactoryI {
	/**
	 * Responsible for creating an instance of type {@link StateI}, whose subtype is
	 * represented by the {@link StateName} param 1, instantiating it with the
	 * parameters {@code args}
	 * 
	 * @param stateName
	 * @param args
	 * @return instance of {@link StateI channelpopularity.state.StateI}
	 */
	StateI create(StateName stateName, Object... args);
}
