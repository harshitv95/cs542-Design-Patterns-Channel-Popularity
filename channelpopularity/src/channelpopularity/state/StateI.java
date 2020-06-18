package channelpopularity.state;

/**
 * Simple State interface representing the super type of all States
 * 
 * @author Harshit Vadodaria
 *
 */
public interface StateI {
	
	/**
	 * @param popularityScore
	 * @return
	 */
	StateName updateState(int popularityScore);
}
