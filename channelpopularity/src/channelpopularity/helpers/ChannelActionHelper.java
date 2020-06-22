package channelpopularity.helpers;

import channelpopularity.operation.Operation;

public class ChannelActionHelper {
	
	public Operation getOperation(String line) {
		for (Operation op : Operation.values())
			if (line.startsWith(op.name()))
				return op;
		return null;
	}
	
}
