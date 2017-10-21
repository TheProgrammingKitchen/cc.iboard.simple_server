package cc.iboard.backend;

public class Response {

	public static final int HTTP_OK = 200;
	public static final int HTTP_NOT_FOUND = 404;
	public static final int HTTP_FORBIDDEN = 403;

	private String body;
	private int status;

	public Response(int status, String body) {
		this.status = status;
		this.body = body;
	};

	public String body() {
		return body;
	}
	public int status() {
		return status;
	}
}
