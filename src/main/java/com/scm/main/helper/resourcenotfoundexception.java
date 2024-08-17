package com.scm.main.helper;



public class resourcenotfoundexception extends RuntimeException{
	public resourcenotfoundexception(String message) {
		super(message);
	}
	
	public resourcenotfoundexception() {
		super("user not found");
	}
}
