package channelpopularity.channel.video;

/**
 * A generic exception to be thrown for all types of exceptions while using
 * {@link VideoStoreI}
 * 
 * @author harshitv
 *
 */
public class VideoStoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VideoStoreException(String message) {
		super(message);
	}

}
