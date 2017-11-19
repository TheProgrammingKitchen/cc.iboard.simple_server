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
    fun map(): HashMap<String, String> {
        return params
    }

    /**
     * Get all keys as a string-array
     * @return
     */
    fun keys(): Array<String> {
        val keys = paramKeys
        return listToStringArray(keys)
    }

    /**
     * Return all values as a string-array
     * @return
     */
    fun values(): Array<String> {
        val values = paramValues
        return listToStringArray(values)
    }

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

    private fun listToStringArray(keys: List<String>): Array<String> {
        val keyArray = arrayOfNulls<String>(keys.size)
        return keys.toTypedArray()
    }

    private fun extractParameters(requestString: String) {
        val paramsString = extractParamsString(requestString)
        extractParamMap(paramsString)
    }

    private fun extractParamMap(paramsString: String) {
        if (paramsString.isEmpty())
            return

        val allParams = paramsString.split("\\&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        addParamsToMap(allParams)
    }

    private fun addParamsToMap(allParams: Array<String>) {
        for (i in allParams.indices) {
            val p = allParams[i].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            params.put(p[0], p[1])
        }
    }

    private fun extractParamsString(requestString: String): String {
        val paramsString: String
        val parts = requestString.split("\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (parts.size > 1)
            paramsString = parts[1]
        else
            paramsString = ""
        return paramsString
    }

}
