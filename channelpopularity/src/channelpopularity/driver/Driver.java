package channelpopularity.driver;

import java.io.IOException;

import channelpopularity.channel.ChannelContext;
import channelpopularity.channel.video.SimpleVideoStore;
import channelpopularity.context.ContextI;
import channelpopularity.helpers.OperationHelper;
import channelpopularity.helpers.ValidationHelper;
import channelpopularity.operation.Operation;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;

/**
 * @author Harshit Vadodaria
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		ValidationHelper validation = new ValidationHelper().critical();

		validation.validateFile(args[0]); // Validating Input File
		validation.validateNotEmpty(args[1], "Output filename was empty"); // Validating Output File

		FileProcessor fp = null;
		ContextI context;
		OperationHelper opHelp;
		Operation operation;

		try (Results results = new Results(args[1], true)) {
			fp = new FileProcessor(args[0]);
			opHelp = new OperationHelper();
			context = new ChannelContext(new SimpleVideoStore(), new SimpleStateFactory(), results);

			String line = null;
			int lineCount = 0;

			while ((line = fp.poll()) != null) {
				operation = opHelp.getOperation(line);
				context.action(operation, operation.getParams(line));
				results.flush();
			}

			if (lineCount == 0)
				throw new RuntimeException("Input file was empty");

		} catch (Exception e) {
			System.err.println("The following Exception occurred:");
			e.printStackTrace();
		} finally {
			if (fp != null)
				try {
					fp.close();
				} catch (IOException e) {
					System.err.println("Failed to close input file processor");
					e.printStackTrace();
				}
		}

	}
}
