package cc.iboard.endpoints;

import java.util.HashMap;
import java.util.Map;

/**
 * The singleton `EndpointFactory` creates a class which is derived from `Endpoint`.
 * A private `Map` acts as a cache for already created handlers.
 */
public enum EndpointFactory {
    INSTANCE;

    private Map<String, Endpoint> handlers = new HashMap<String, Endpoint>();

    public Endpoint createHandler(String handlerName) throws Exception {
        Endpoint endpoint = handlers.get(handlerName);
        if (endpoint != null)
            return endpoint;
        else
            return newHandler(handlerName);
    }

    // Helpers

    private Endpoint newHandler(String handlerName) throws Exception {
        try {
            return cacheNewHandler(handlerName);
        } catch (Exception e) {
            throw new Exception("Endpoint not found: " + handlerName + e.getMessage());
        }
    }

    private Endpoint cacheNewHandler(String handlerName)
            throws InstantiationException,
                   IllegalAccessException,
                   ClassNotFoundException
    {
        Endpoint endpoint = instantiateHandlerByName(handlerName);
        handlers.put(handlerName, endpoint);
        return endpoint;
    }

    // TODO: Get rid of `newInstance`
    private Endpoint instantiateHandlerByName(String handlerName)
            throws InstantiationException,
                   IllegalAccessException,
                   ClassNotFoundException
    {
        Endpoint endpoint;
        String name = Endpoint.class.getPackage().getName();
        endpoint = (Endpoint) Class.forName(name+"."+handlerName).newInstance();
        return endpoint;
    }

}
