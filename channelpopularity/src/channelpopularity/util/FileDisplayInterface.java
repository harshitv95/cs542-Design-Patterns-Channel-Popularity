package channelpopularity.util;

import java.io.Writer;

public interface FileDisplayInterface {

	/**
	 * Prints {@code printStr} to a file
	 * 
	 * @param printStr : String to print to a file
	 */
	void printToFile(String printStr);

	/**
	 * Initialize a Writer instance that writes to the file with name
	 * {@code filename}
	 * 
	 * @param filename
	 * @return {@link Writer}
	 */
	Writer initFileWriter(String filename);

	/**
	 * Prints {@code printStr} to all available OutputStream Writers
	 * 
	 * @param printStr
	 */
	void printToAll(String printStr);

}
