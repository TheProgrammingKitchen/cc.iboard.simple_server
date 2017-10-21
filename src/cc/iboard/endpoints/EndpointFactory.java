package cc.iboard.endpoints;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * The singleton `EndpointFactory` creates a class which is derived from `Endpoint`.
 */
public enum EndpointFactory {
    INSTANCE;

    public Endpoint createHandler(String handlerName) throws Exception {
       return newHandler(handlerName);
    }

    // Helpers

    private Endpoint newHandler(String handlerName) throws Exception {
        try {
            return newHandlerByName(handlerName);
        } catch (Exception e) {
            throw new Exception("Endpoint not found: " + handlerName + e.getMessage());
        }
    }

    private Endpoint newHandlerByName(String handlerName)
            throws IllegalArgumentException, 
            		   InvocationTargetException, 
            		   NoSuchMethodException, 
            		   SecurityException, 
            		   InstantiationException, 
            		   IllegalAccessException, 
            		   ClassNotFoundException
    {
		String className = getClassName(handlerName);
		return (Endpoint) getConstructor(className).newInstance();
    }

	private Constructor<?> getConstructor(String className) throws NoSuchMethodException, ClassNotFoundException {
		return Class.forName(className).getDeclaredConstructor();
	}

	private String getClassName(String handlerName) {
		return getPackageName() + "." + handlerName;
	}

	private String getPackageName() {
		return Endpoint.class.getPackage().getName();
	}

}
