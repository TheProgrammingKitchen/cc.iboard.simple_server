package cc.iboard.endpoints

import cc.iboard.backend.BackendHelper

import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.util.regex.Pattern

/**
 * The singleton `EndpointFactory` creates a class which is derived from `Endpoint`.
 */
enum class EndpointFactory {
    INSTANCE;

    @Throws(EndpointNotFound::class)
    fun createEndpoint(queryString: String): Endpoint {
        val endpointName = determineEndpointName(queryString)
        return newEndpoint(endpointName)
    }

    // IMPLEMENTATION

    @Throws(EndpointNotFound::class)
    private fun newEndpoint(handlerName: String): Endpoint {
        try {
            return newEndpointByName(handlerName)
        } catch (e: Exception) {
            throw EndpointNotFound(handlerName, e)
        }

    }

    @Throws(IllegalArgumentException::class, InvocationTargetException::class, NoSuchMethodException::class, SecurityException::class, InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    private fun newEndpointByName(handlerName: String): Endpoint {
        val className = getClassName(handlerName)
        return getConstructor(className).newInstance() as Endpoint
    }

    @Throws(NoSuchMethodException::class, ClassNotFoundException::class)
    private fun getConstructor(className: String): Constructor<*> {
        return Class.forName(className).getDeclaredConstructor()
    }

    private fun getClassName(handlerName: String): String {
        return packageName + "." + handlerName
    }

    private val packageName: String
        get() = Endpoint::class.java.`package`.name

    private fun determineEndpointName(queryString: String): String {
        val path = getPath(queryString)
        return getEndpointFromPath(path)
    }

    private fun getEndpointFromPath(path: String): String {
        val parts = path.split("\\/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val be = if (parts.size > 1) parts[1] else parts[0]
        return BackendHelper.upcaseFirstChar(be)
    }

    private fun getPath(queryString: String): String {
        return queryString.split("\\?".toRegex(), Pattern.LITERAL).toTypedArray()[0]
    }
}
