package cc.iboard.endpoints;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

/**
 * The singleton `EndpointFactory` creates a class which is derived from `Endpoint`.
 */
public enum EndpointFactory {
    INSTANCE;

    public Endpoint createEndpoint(String queryString) throws Exception {
       String[] parts = queryString.split("\\?", Pattern.LITERAL);
       String path = parts[0];
       //String _params = parts[1];
       parts = path.split("\\/");
       String handlerName = parts[0];
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
