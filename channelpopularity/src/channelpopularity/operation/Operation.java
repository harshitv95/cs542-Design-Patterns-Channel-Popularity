package channelpopularity.operation;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
	ADD_VIDEO() {
		@Override
		Map<ParamKeys, String> getParams(String line) {
			String[] arr = line.split("::");
			return new HashMap<ParamKeys, String>() {
				{
					put(ParamKeys.VIDEONAME, arr[1]);
				}
			};
		}
	},
	REMOVE_VIDEO {
		@Override
		Map<ParamKeys, ?> getParams(String line) {
			String[] arr = line.split("::");
			return new HashMap<ParamKeys, String>() {
				{
					put(ParamKeys.VIDEONAME, arr[1]);
				}
			};
		}
	},
	METRICS {
		@Override
		Map<ParamKeys, ?> getParams(String line) {
			Map<ParamKeys, Object> params = new HashMap<>();
			String[] split = line.split("::");
			params.put(ParamKeys.VIDEONAME, split[0].split("__")[1]);
			if (!(split[1].startsWith("[") && split[1].endsWith("]")))
				throw new RuntimeException("Syntax for Metrics is invalid in line [" + line + "]");
			split[1] = split[1].substring(1, split[1].length());
			String splitPrm[];
			for (String prm : split[1].split(",")) {
				splitPrm = prm.split("=");
				params.put(ParamKeys.valueOf(splitPrm[0]), splitPrm[1]);
			}
			return params;
		}
	},
	AD_REQUEST {
		@Override
		Map<ParamKeys, Integer> getParams(String line) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	abstract Map<ParamKeys, ?> getParams(String line);

	public static enum ParamKeys {
		VIDEONAME,
		VIEWS,
		LIKES,
		DISLIKES,
		LEN
		;
	}
}
