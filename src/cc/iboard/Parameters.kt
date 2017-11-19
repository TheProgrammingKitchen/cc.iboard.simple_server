package cc.iboard

import java.util.ArrayList
import java.util.HashMap

/**
 * Parameter class to handle request parameters from request strings.
 * A request-string of type /PATH/resource/ID?KEY=value&OTHER_KEY=other_value
 * is parsed and a Map of key/value pairs is created.
 */
class Parameters
/**
 * Initialize parameters from string `/PATH/resource/ID?KEY=value&OTHER_KEY=other_value`
 * @param requestString
 */
(requestString: String) {

    private val params = HashMap<String, String>()

    init {
        extractParameters(requestString)
    }

    /**
     * Returns parsed parameters as a map of key/value pairs.
     * @return
     */
    fun map(): HashMap<String, String> = params

    /**
     * Get all keys as a string-array
     * @return
     */
    fun keys(): Array<String> = listToStringArray(paramKeys)

    /**
     * Return all values as a string-array
     * @return
     */
    fun values(): Array<String> = listToStringArray(paramValues)

    /**
     * Return the value of the given key
     * @param key
     * @return
     */
    fun key(key: String): String? = params[key]


    // IMPLEMENTATION

    private val paramKeys: List<String>
        get() {
            val keys = ArrayList<String>()
            params.forEach { k, _v -> keys.add(k) }
            return keys
        }

    private val paramValues: List<String>
        get() {
            val values = ArrayList<String>()
            params.forEach { _k, v -> values.add(v) }
            return values
        }

    private fun listToStringArray(keys: List<String>)
            : Array<String> = keys.toTypedArray()

    private fun extractParameters(requestString: String) {
        val paramsString = extractParamsString(requestString)
        extractParamMap(paramsString)
    }

    private fun extractParamMap(paramsString: String) {
        if (paramsString.isEmpty())
            return

        val allParams = paramsString
                .split("\\&".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
        addParamsToMap(allParams)
    }

    private fun addParamsToMap(allParams: Array<String>) {
        allParams.map { it.split("=".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                      }
                .forEach { params.put(it[0], it[1]) }
    }

    private fun extractParamsString(requestString: String): String {
        val parts = requestString.split("\\?".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
        return if (parts.size > 1)
            parts[1]
        else
            ""
    }

}
