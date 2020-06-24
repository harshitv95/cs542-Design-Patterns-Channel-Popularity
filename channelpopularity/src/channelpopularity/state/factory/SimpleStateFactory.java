package channelpopularity.state.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

/**
 * A simple factory for creating States, using the Reflections API. This factory
 * is made so generic, so that in order to register a new state, you would just
 * add a new state (entry) in the {@link StateName} enum, and no changes are
 * required in the Context or State or any other components.
 * 
 * @author Harshit Vadodaria
 *
 */
public class SimpleStateFactory implements SimpleStateFactoryI {

	private boolean constructorMatches(Constructor<?> constr, Object... args) {
		if (constr.getParameterCount() != args.length)
			return false;
		Parameter[] params = constr.getParameters();
		for (int i = 0; i < args.length; i++)
			if (!params[i].getType().isAssignableFrom(args[i].getClass()))
				return false;
		return true;
	}

	@Override
	public StateI create(StateName stateName, Object... args) {
		Class<? extends StateI> clazz = stateName.getStateClass();
		try {
			Constructor<? extends StateI> constr = Stream.of(clazz.getConstructors())
					.map(con -> (Constructor<? extends StateI>) con)
					.filter(con -> con.getParameterCount() == args.length && constructorMatches(con, args)).findFirst()
					.orElse(null);

			// Initialize the constructor with parameters 'args'
			return constr.newInstance(args);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Failed to instantiate State [" + stateName.name()
					+ "] : Invalid declaration of State constructor or Incorrect number/types of arguments provided (Got "
					+ args.length + " arguments of types ["
					+ Stream.of(args).map(arg -> arg.getClass().getName()).collect(Collectors.joining(", "))
					+ "] as constructor parameters)", e);
		} catch (SecurityException | IllegalAccessException e) {
			throw new RuntimeException("Failed to instantiate State [" + stateName.name()
					+ "] : Constructor is not accessible, make sure the constructor is public)", e);
		} catch (InstantiationException e) {
			throw new RuntimeException("Failed to instantiate State [" + stateName.name() + "]", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Failed to instantiate State [" + stateName.name()
					+ "] : Invalid declaration of State (Require only instance of SimpleStateFactoryI as constructor parameter)",
					e);
		}
	}

}
