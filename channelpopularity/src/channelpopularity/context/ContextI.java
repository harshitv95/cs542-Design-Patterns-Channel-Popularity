package channelpopularity.context;

import java.util.Map;

import channelpopularity.channel.video.VideoStoreI;
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
	void setState(StateName stateName);

	/**
	 * Performs the required action based on the {@code operation} (type:
	 * {@link Operation}), using various values from the Map {@code values}
	 * 
	 * @param operation instance of {@link channelpopularity.operation.Operation}
	 * @param values    Map containing various params to be used to perform the
	 *                  operation
	 */
	void action(Operation operation, Map<Operation.ParamKeys, ?> values);

	/**
	 * Returns the popularity score of the channel, which is the average popularity
	 * score of all videos in the channel
	 * 
	 * @return Popularity score of the channel
	 */
	double getPopularity();

	/**
	 * Sets the popularity score of the channel
	 * 
	 * @param popularityScore The score to set the channel's popularity to
	 */
	void setPopularity(double popularityScore);

	/**
	 * Returns the channel's video store
	 * 
	 * @return Instance of {@link VideoStoreI}, representing the Videos Store for
	 *         the current channel
	 */
	VideoStoreI getVideoStore();

}
