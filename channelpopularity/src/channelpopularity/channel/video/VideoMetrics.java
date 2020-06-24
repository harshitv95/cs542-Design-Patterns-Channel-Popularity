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
		this.updateMetrics(metrics);
	}

	public String getVideoName() {
		return videoName;
	}

	public VideoMetrics updateMetrics(Map<Operation.ParamKeys, String> metrics) {
		if (metrics != null) {
			views += Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.VIEWS, "0"));
			likes += Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.LIKES, "0"));
			dislikes += Integer.parseInt(metrics.getOrDefault(Operation.ParamKeys.DISLIKES, "0"));
		}
		return this;
	}

	public VideoMetrics updateMetrics(VideoMetrics metrics) {
		if (metrics != null) {
			this.views += metrics.getViews();
			this.likes += metrics.getLikes();
			this.dislikes += metrics.getDislikes();
		}
		return this;
	}

	public VideoMetrics updateMetrics(int views, int likes, int dislikes) {
		this.views += views;
		this.likes += likes;
		this.dislikes += dislikes;
		return this;
	}

	public int calcPopularityScore() {
		int popScore = views + (2 * (likes - dislikes));
		return popScore >= 0 ? popScore : 0;
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

}
