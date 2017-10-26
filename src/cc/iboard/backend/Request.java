package cc.iboard.backend;

import cc.iboard.Parameters;

public class Request {
	private String method;
	private Parameters parameters;
	private String path;

    // CONSTRUCTOR
    
	public Request(String method, String requestString) {
		this.method = method;
		this.path = extractPath(requestString);
		this.parameters = new Parameters(requestString);
	}

    // API
    
	public String method() { return method; }

	public String path() { return path; }

	public Parameters parameters() { return parameters; }

    // IMPLEMENTATION
    
	private String extractPath(String queryString) {
	  String[] parts = queryString.split("\\?");
      return parts[0];
	}

}
