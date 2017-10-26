package cc.iboard.backend;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Request {
	private String method;
	private List<Entry<String,String>> parameters;
	private String path;

	public Request(String method, String requestString) {
		this.method = method;
		this.path = extractPath(requestString);
		this.parameters = extractParameters(requestString);
	}

	public String method() {
		return method;
	}

	public String path() {
		return path;
	}

	public List<Entry<String,String>> parameters() {
		return parameters;
	}

	private List<Entry<String,String>> extractParameters(String requestString) {
        List<Map.Entry<String, String>> params = new ArrayList<>();
	    String[] parts = requestString.split("\\?");
	    String paramsString;
		if (parts.length > 1)
			paramsString = parts[1];
	    else
			return params;
	     
	    String[] allParams = paramsString.split("\\&");
	    for( int i=0; i < allParams.length; i++) {
	      String[] p = allParams[i].split("=");
	      SimpleEntry<String,String> param = new SimpleEntry<String,String>(p[0],p[1]);
	      params.add(param);
	    }
	   
		return params;
	}

	private String extractPath(String queryString) {
	  String[] parts = queryString.split("\\?");
      return parts[0];
	}



}
