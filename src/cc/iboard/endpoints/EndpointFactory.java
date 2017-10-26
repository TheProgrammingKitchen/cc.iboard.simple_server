package cc.iboard.endpoints;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

/**
 * The singleton `EndpointFactory` creates a class which is derived from `Endpoint`.
 */
public enum EndpointFactory {
    INSTANCE;

    public Endpoint createEndpoint(String queryString) throws EndpointNotFound {
        String endpointName = determineEndpointName(queryString);
        return newEndpoint(endpointName);
    };

    // IMPLEMENTATION

    private Endpoint newEndpoint(String handlerName) throws EndpointNotFound {
        try {
            return newEndpointByName(handlerName);
        } catch (Exception e) {
            throw new EndpointNotFound(handlerName, e);
        }
    };

    private Endpoint newEndpointByName(String handlerName)
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
    };

    private Constructor<?> getConstructor(String className) throws NoSuchMethodException, ClassNotFoundException {
        return Class.forName(className).getDeclaredConstructor();
    };

    private String getClassName(String handlerName) {
        return getPackageName() + "." + handlerName;
    };

    private String getPackageName() {
        return Endpoint.class.getPackage().getName();
    };

    private String determineEndpointName(String queryString) {
        String path = getPath(queryString);
        return getEndpointFromPath(path);
    }

    private String getEndpointFromPath(String path) {
        return path.split("\\/")[0];
    }

    private String getPath(String queryString) {
        return queryString.split("\\?", Pattern.LITERAL)[0];
    };
}
