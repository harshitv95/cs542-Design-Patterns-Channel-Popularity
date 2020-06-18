package channelpopularity.state.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public class SimpleStateFactory implements SimpleStateFactoryI {

	@Override
	public StateI create(StateName stateName, Object... args) {
		Class<? extends StateI> clazz = stateName.getStateClass();
		try {
			// Get the correct constructor accepting the parameters 'args'
			Constructor<? extends StateI> constr = clazz
					.getConstructor((Class<?>[]) Stream.of(args).map(arg -> arg.getClass()).toArray());
			
			// Initialize the constructor with parameters 'args'
			return constr.newInstance(args);
		} catch (NoSuchMethodException | InvocationTargetException e) {
			throw new RuntimeException("Failed to instantiate State [" + stateName.name()
					+ "] : Invalid declaration of State constructor (Require " + args.length + " arguments of types ["
					+ Stream.of(args).map(arg -> arg.getClass().getName()).collect(Collectors.joining(","))
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
