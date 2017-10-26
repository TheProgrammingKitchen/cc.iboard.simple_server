package cc.iboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Parameter class to handle request parameters from request strings.
 * A request-string of type /PATH/resource/ID?KEY=value&OTHER_KEY=other_value
 * is parsed and a Map of key/value pairs is created.
 */
public class Parameters {

	private HashMap<String,String> params = new HashMap<String, String>();

    /**
     * Initialize parameters from string `/PATH/resource/ID?KEY=value&OTHER_KEY=other_value`
     * @param requestString
     */
	public Parameters(String requestString) {
		extractParameters(requestString);
	}
	
	/**
	 * Returns parsed parameters as a map of key/value pairs.
	 * @return
	 */
	public HashMap<String,String> map() { return params; }

    /**
     * Get all keys as a string-array
     * @return
     */
	public String[] keys() {
	    List<String> keys = getParamKeys();
	    return listToStringArray(keys);
	}

    /**
     * Return all values as a string-array
     * @return
     */
	public String[] values() {
	    List<String> values = getParamValues();
	    return listToStringArray(values);
	}
	
	/**
	 * Return the value of the given key
	 * @param key
	 * @return
	 */
	public String key(String key) {
	    return params.get(key);
	}


    // IMPLEMENTATION
    
    private List<String> getParamKeys() {
        List<String> keys = new ArrayList<String>();
	    params.forEach( (k,_v) -> { keys.add(k); });
        return keys;
    }

    private List<String> getParamValues() {
        List<String> values = new ArrayList<String>();
	    params.forEach( (_k,v) -> { values.add(v); });
        return values;
    }

	private String[] listToStringArray(List<String> keys) {
		String[] keyArray = new String[keys.size()];
	    return keys.toArray(keyArray);
	}
	
	private void extractParameters(String requestString) {
	    String paramsString = extractParamsString(requestString);
	    extractParamMap(paramsString);
	}

	private void extractParamMap(String paramsString) {
	    if(paramsString.isEmpty())
	        return;

		String[] allParams = paramsString.split("\\&");
	    addParamsToMap(allParams);
	}

    private void addParamsToMap(String[] allParams) {
        for( int i=0; i < allParams.length; i++) {
	      String[] p = allParams[i].split("=");
	      params.put(p[0], p[1]);
	    }
    }

	private String extractParamsString(String requestString) {
		String paramsString;
	    String[] parts = requestString.split("\\?");
		if (parts.length > 1)
			paramsString = parts[1];
	    else
			paramsString = "";
		return paramsString;
	}

}
