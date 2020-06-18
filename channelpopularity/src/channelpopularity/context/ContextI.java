package channelpopularity.context;

import java.util.Map;

import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;

/**
 * Simple interface to represent a context with multiple states, providing
 * capabilities like jumping between states, performing certain actions, which:
 * <ul>
 * <li>are dependent on the current state, or</li>
 * <li>will determine the current state</li>
 * </ul>
 * 
 * @author Harshit Vadodaria
 *
 */
public interface ContextI {

	/**
	 * Sets the current state of the Context to the State represented by param
	 * {@code stateName}
	 * 
	 * @param stateName
	 */
	public void setState(StateName stateName);

	/**
	 * Performs the required action based on the {@code operation} (type:
	 * {@link Operation}), using various values from the Map {@code values}
	 * 
	 * @param operation instance of {@link channelpopularity.operation.Operation}
	 * @param values    Map containing various params to be used to perform the
	 *                  operation
	 */
	public void action(Operation operation, Map<String, Integer> values);
}
