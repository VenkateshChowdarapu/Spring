package com.dbs.payment.model;

public class ResponsePage {
	
	String msg;
	int status;

	
	

	public ResponsePage( int status,String msg) {
		super();
		this.msg = msg;
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResponsePage [msg=" + msg + ", status=" + status + "]";
	}

	
	
	

}
