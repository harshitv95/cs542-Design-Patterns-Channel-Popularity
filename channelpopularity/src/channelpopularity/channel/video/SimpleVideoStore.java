package channelpopularity.channel.video;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.operation.Operation;

public class SimpleVideoStore implements VideoStoreI {

	protected final Map<String, VideoMetrics> videos = new HashMap<>();

	@Override
	public void add(String videoName) {
		add(videoName, null);
	}

	@Override
	public void add(String videoName, VideoMetrics metrics) {
		if (exists(videoName))
			throw new VideoStoreException(
					"Video [" + videoName + "] already exists, cannot add new video with same name");

		try {
			videos.put(videoName, metrics != null ? metrics : new VideoMetrics(videoName));
		} finally {
			callback(Operation.ADD_VIDEO);
		}
	}

	@Override
	public void updateMetrics(String videoName, VideoMetrics metrics) {
		if (!exists(videoName))
			throw new VideoStoreException("Video [" + videoName + "] does not exist, could not update metrics");

		try {
			videos.get(videoName).updateMetrics(metrics);
		} finally {
			callback(Operation.METRICS);
		}
	}

	@Override
	public VideoMetrics remove(String videoName) {
		if (!exists(videoName))
			throw new VideoStoreException("Video [" + videoName + "] does not exist, could not remove video");

		try {
			return videos.remove(videoName);
		} finally {
			callback(Operation.REMOVE_VIDEO);
		}
	}

	@Override
	public VideoMetrics get(String videoName) {
		if (exists(videoName))
			throw new VideoStoreException("Video [" + videoName + "] does not exist, could not get metrics");

		return videos.get(videoName);
	}

	@Override
	public int count() {
		return videos.size();
	}

	@Override
	public void callback(Operation op) {

	}

	@Override
	public boolean exists(String videoName) {
		return videos.containsKey(videoName) && videos.get(videoName) != null;
	}

	@Override
	public String toString() {
		return "{"
				+ "\ncount: " + this.count()
				+ ",\nvideos: " + this.videos.values()
				+ "\n}";
	}

}
