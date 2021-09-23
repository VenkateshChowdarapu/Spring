package com.dbs.web.response;

public class ResponsePage {

	private int status;
	private String message;
	
	public ResponsePage() {
		// TODO Auto-generated constructor stub
	}
	
	public ResponsePage(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponsePage [status=" + status + ", message=" + message + "]";
	}

	
	
}
