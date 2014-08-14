package com.zchen.webdriver.utils;

import java.util.HashMap;

public class AjaxResult {

    private boolean success;

    private String message;

    private Object data;

	public static AjaxResult get(){
		return new AjaxResult();
	}
	
	public AjaxResult success(){
		this.success = true;
		return this;
	}
	
	public AjaxResult failure(){
		this.success = false;
		return this;
	}
	
	public AjaxResult setMessage(String message){
		this.message = message;
		return this;
	}

    public AjaxResult setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
