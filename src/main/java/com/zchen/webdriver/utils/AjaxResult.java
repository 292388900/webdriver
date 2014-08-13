package com.zchen.webdriver.utils;

import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object> {
	
	public static AjaxResult get(){
		return new AjaxResult();
	}
	
	public AjaxResult success(){
		this.put("success", true);
		return this;
	}
	
	public AjaxResult failure(){
		this.put("success", false);
		return this;
	}
	
	public AjaxResult setMessage(String message){
		this.put("message", message);
		return this;
	}
}
