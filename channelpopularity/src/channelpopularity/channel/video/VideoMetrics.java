package channelpopularity.channel.video;

import java.util.Map;

import channelpopularity.operation.Operation;

/**
 * A POJO class to hold the video's metrics, specifically number of views, likes
 * and dislikes, and the video's number
 * 
 * @author Harshit Vadodaria
 *
 */
public class VideoMetrics {

	private int likes, dislikes, views;
	private final String videoName;

	public VideoMetrics(String videoName) {
		this(videoName, null);
	}

	public VideoMetrics(String videoName, Map<Operation.ParamKeys, String> metrics) {
		this.videoName = videoName;
		this.unsfeUpdateMetrics(metrics);
	}

	public String getVideoName() {
		return videoName;
	}

	protected void unsafeUpdateMetrics(int views, int likes, int dislikes) {
		this.views += views;
		this.likes += likes;
		this.dislikes += dislikes;
	}
	
	protected VideoMetrics unsfeUpdateMetrics(Map<Operation.ParamKeys, String> metrics) {
		if (metrics != null)
			this.unsafeUpdateMetrics(Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.VIEWS, "0")),
					Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.LIKES, "0")),
					Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.DISLIKES, "0")));
		return this;
	}

	public VideoMetrics updateMetrics(Map<Operation.ParamKeys, String> metrics) {
		if (metrics != null) {
			this.updateMetrics(Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.VIEWS, "0")),
					Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.LIKES, "0")),
					Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.DISLIKES, "0")));
		}
		return this;
	}

	public VideoMetrics updateMetrics(VideoMetrics metrics) {
		if (metrics != null)
			this.updateMetrics(metrics.getViews(), metrics.getLikes(), metrics.getDislikes());
		return this;
	}


	public VideoMetrics updateMetrics(int views, int likes, int dislikes) {
		if (views < 0)
			throw new RuntimeException("Invalid value for number of VIEWS [" + views + "] for video ["
					+ this.getVideoName() + "], expected positive value.\nVideo:" + this);

		if (-likes > this.getLikes())
			throw new RuntimeException("Invalid value for number of LIKES [" + likes + "] for video ["
					+ this.getVideoName() + "], attempted to reduce LIKES below 0.\nVideo:" + this);

		if (-dislikes > this.getDislikes())
			throw new RuntimeException("Invalid value for number of DISLIKES [" + dislikes + "] for video ["
					+ this.getVideoName() + "], attempted to reduce DISLIKES below 0.\nVideo:" + this);
//
//		if (this.getViews() < (this.getLikes() + this.getDislikes()))
//			throw new RuntimeException(
//					"Count of Likers and Dislikers of video were greater than the number of Viewers (A video cannot be liked or disliked before viewing it).\n"
//							+ this);
		this.unsafeUpdateMetrics(views, likes, dislikes);
		return this;
	}

	public int calcPopularityScore() {
		return views + (2 * (likes - dislikes));
//		return popScore >= 0 ? popScore : 0;
	}

	public int getLikes() {
		return likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public int getViews() {
		return views;
	}

	@Override
	public String toString() {
		return "{name:" + this.getVideoName() + ",views:" + this.getViews() + ",likes:" + this.getLikes() + ",dislikes:"
				+ this.getDislikes() + "}";
	}

}
