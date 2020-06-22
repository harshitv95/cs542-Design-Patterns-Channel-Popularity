package channelpopularity.util;

import java.io.Writer;

public interface StdoutDisplayInterface {

	/**
	 * Initiate and return an instance of Writer, which writes to stdout
	 * 
	 * @return {@link Writer}
	 */
	Writer initStdOutWriter();

	/**
	 * Prints {@code printStr} to stdout
	 * 
	 * @param printStr : String to print to stdout
	 */
	void printToStdOut(String printStr);

	/**
	 * Prints {@code printStr} to all available OutputStream Writers
	 * 
	 * @param printStr
	 */
	void printToAll(String printStr);

}
