package channelpopularity.channel.video;

import channelpopularity.operation.Operation;

/**
 * An interface enlisting various functionalities expected to be supported by
 * any implementation of a Video Store
 * 
 * @author Harshit Vadodaria
 *
 */
public interface VideoStoreI {

	/**
	 * Add a new video with parameter 1 {@code videoName} as name, and initialize
	 * all the metrics to 0 (zero)
	 * 
	 * @param videoName Name of the video to be saved to the store
	 * 
	 * @throws {@link VideoStoreException} If the video already exists
	 */
	void add(String videoName);

	/**
	 * Add a new video with parameter 1 {@code videoName} as name, and sets its
	 * metrics as parameter 2 ({@code metrics})
	 * 
	 * @param videoName Name of the video to be saved to the store
	 * @param metrics   Instance of {@link VideoMetrics}, representing the metrics
	 *                  of the video being added
	 * 
	 * @throws {@link VideoStoreException} If the video already exists
	 */
	void add(String videoName, VideoMetrics metrics);

	/**
	 * Adds the new metric values in parameter 2 to the existing metrics of video
	 * {@code videoName}
	 * 
	 * @param videoName Name of the video to be saved to the store
	 * @param metrics   Instance of {@link VideoMetrics}, representing the new
	 *                  metrics to be updated to the existing metrics of the video
	 * 
	 * @throws {@link VideoStoreException} If the video does not exist
	 * 
	 */
	void updateMetrics(String videoName, VideoMetrics metrics);

	/**
	 * Removes video with the given name from the store
	 * 
	 * @param videoName Name of the video to be removed
	 * @return Instance of {@link VideoMetrics} representing the metrics of the
	 *         video that was just removed from the store
	 * 
	 * @throws {@link VideoStoreException} If the video does not exist
	 * 
	 */
	VideoMetrics remove(String videoName);

	/**
	 * Get the metrics of the video of the name {@code videoName}
	 * 
	 * @param videoName
	 * @return Instance of {@link VideoMetrics} representing the metrics of the
	 *         video that was just removed from the store
	 *
	 * @throws {@link VideoStoreException} If the video does not exist
	 *
	 */
	VideoMetrics get(String videoName);

	/**
	 * Provides number of videos in the Video Store
	 * 
	 * @return Number of videos in the store
	 */
	int count();

	/**
	 * Callback function that would be called after every action, i.e. add, remove,
	 * or update video
	 * 
	 * @param op Operation that resulted in this callback
	 */
	void callback(Operation op);

	/**
	 * Checks if the video with name {@code videoName} exists in the current store
	 * 
	 * @return {@code true} or {@code false} depending on whether or not the video
	 *         exists
	 */
	boolean exists(String videoName);
}
