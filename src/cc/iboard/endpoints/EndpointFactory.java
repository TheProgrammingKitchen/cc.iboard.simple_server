package cc.iboard.endpoints;

import java.util.HashMap;
import java.util.Map;

/**
 * The singleton `EndpointFactory` creates a class which is derived from `Endpoint`.
 */
public enum EndpointFactory {
    INSTANCE;

    private Map<String, Endpoint> handlers = new HashMap<>();

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
