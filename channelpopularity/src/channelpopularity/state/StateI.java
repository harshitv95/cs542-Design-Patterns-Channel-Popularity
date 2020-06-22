package channelpopularity.state;

import channelpopularity.channel.video.VideoMetrics;
import channelpopularity.channel.video.VideoStoreException;

/**
 * Simple State interface representing the super type of all States
 * 
 * @author Harshit Vadodaria
 *
 */
public interface StateI {

	/**
	 * Changes the state of the context based on the parameter
	 * {@code popularityScore}, and returns the name (instance of {@link StateName})
	 * of the new state
	 * 
	 * @return {@link StateName} instance representing the name of the new state
	 */
	StateName updateState();

	/**
	 * Process the ad request, i.e. determine whether ad of certain length can be
	 * displayed on specific video (this differs with each state)
	 * 
	 * @param videoName Name of the video to display ads on
	 * @param length    Length of the advertisement to display
	 */
	void processAdRequest(String videoName, int length);

	/**
	 * Adds a new video to list of videos of this channel
	 * 
	 * @throws VideoStoreException if video with number same as that of parameter
	 *                             {@code video} already exists
	 * @param videoName Name of the video to be added
	 */
	void addVideo(String videoName);

	/**
	 * Deletes the video at the specified index {@code videoNumber} from the channel
	 * 
	 * @throws VideoStoreException if video numbered {@code videoNumber} does not
	 *                             exist
	 * @param videoName Name of the video to be deleted
	 */
	void removeVideo(String videoName);

	/**
	 * Updates the metrics of an existing video
	 * 
	 * @throws VideoStoreException if video with the same number as that of
	 *                             parameter {@code video} does not exist
	 * @param video
	 */
	void updateVideoMetrics(VideoMetrics video);

	/**
	 * Asserts whether an advertisement of the specified length can be displayed on
	 * any video in the channel. (The param {@code videoName} may not be required to
	 * determine visibility of ads, except to validate the video's existence, but it
	 * could be, in the future, used as a factor to determine visibility of ads on
	 * that particular video, hence {@code videoName} parameter is required here)
	 * 
	 * @param length
	 * @return
	 */
	boolean approveAd(String videoName, int length);
}
