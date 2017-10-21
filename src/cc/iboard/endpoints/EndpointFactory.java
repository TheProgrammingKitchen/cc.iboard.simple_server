package cc.iboard.endpoints;

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
            return instantiateHandlerByName(handlerName);
        } catch (Exception e) {
            throw new Exception("Endpoint not found: " + handlerName + e.getMessage());
        }
    }

    private Endpoint instantiateHandlerByName(String handlerName)
            throws IllegalArgumentException, 
            		   InvocationTargetException, 
            		   NoSuchMethodException, 
            		   SecurityException, 
            		   InstantiationException, 
            		   IllegalAccessException, 
            		   ClassNotFoundException
    {
        Endpoint endpoint;
        String name = Endpoint.class.getPackage().getName();
        endpoint = (Endpoint) Class.forName(name+"."+handlerName).getDeclaredConstructor().newInstance();
        return endpoint;
    }

}
