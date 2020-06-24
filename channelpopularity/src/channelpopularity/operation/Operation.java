package channelpopularity.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Represents the Operation required to be performed by the input line
 * 
 * @author Harshit Vadodaria
 *
 */
public enum Operation {
	ADD_VIDEO("ADD_VIDEO::.+") {
		@Override
		public Map<ParamKeys, String> getParams(String line) {
			validateSyntax(line);
			String[] arr = line.split("::");
			return new HashMap<ParamKeys, String>() {
				{
					put(ParamKeys.VIDEONAME, arr[1]);
				}
			};
		}
	},
	REMOVE_VIDEO("REMOVE_VIDEO::.+") {
		@Override
		public Map<ParamKeys, String> getParams(String line) {
			validateSyntax(line);
			String[] arr = line.split("::");
			return new HashMap<ParamKeys, String>() {
				{
					put(ParamKeys.VIDEONAME, arr[1]);
				}
			};
		}
	},
	METRICS("METRICS__.+::\\[VIEWS=[-|+]{0,1}[0-9]+,LIKES=[-|+]{0,1}[0-9]+,DISLIKES=[-|+]{0,1}[0-9]+\\]") {
		@Override
		public Map<ParamKeys, String> getParams(String line) {
			validateSyntax(line);
			Map<ParamKeys, String> params = new HashMap<>();
			String[] split = line.split("::");
			params.put(ParamKeys.VIDEONAME, split[0].split("__")[1]);
			split[1] = split[1].substring(1, split[1].length() - 1);
			String splitPrm[];
			for (String prm : split[1].split(",")) {
				splitPrm = prm.split("=");
				params.put(ParamKeys.valueOf(splitPrm[0]), splitPrm[1]);
			}
			return params;
		}
	},
	AD_REQUEST("AD_REQUEST__.+::LEN=[0-9]+") {
		@Override
		public Map<ParamKeys, String> getParams(String line) {
			validateSyntax(line);
			Map<ParamKeys, String> params = new HashMap<>();
			String[] split = line.split("::");
			params.put(ParamKeys.VIDEONAME, split[0].split("__")[1]);
			String splitPrm[] = split[1].split("=");
			params.put(ParamKeys.LEN, splitPrm[1]);
			return params;
		}
	};

	/**
	 * Extracts the params from the input line, and returns a HashMap representing
	 * the required arguments for the {@link Operation}
	 * 
	 * @param line {@code String} containing an input line
	 * @return {@code Map} containing the input arguments
	 */
	public abstract Map<ParamKeys, String> getParams(String line);

	/**
	 * Validates whether the input line follows the syntax required as input for the
	 * operation
	 * 
	 * @param line {@code String} containing an input line
	 */
	public void validateSyntax(String line) {
		if (!Pattern.matches(regexStr, line))
			throw new PatternSyntaxException("Invalid syntax for " + this.name() + ": [" + line + "]", regexStr, -1);
	}

	private String regexStr;

	Operation(String regexStr) {
		this.regexStr = regexStr;
	}

	/**
	 * Represents Keys of input arguments for all Operations
	 * 
	 * @author Harshit Vadodaria
	 *
	 */
	public static enum ParamKeys {
		VIDEONAME, VIEWS, LIKES, DISLIKES, LEN;
	}
}
