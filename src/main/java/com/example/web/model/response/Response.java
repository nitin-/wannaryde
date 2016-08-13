package com.example.web.model.response;

public class Response {
	
	private static Response response;
	private Response(){		
	}
	
	private int status;
	private String message;
	private Object data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static Response getResponseInstance(){
    	if(null == response)
    		response = new Response();
    	return response;
    }
	
}
