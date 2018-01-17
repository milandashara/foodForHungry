package com.foodForHungry.exception;

/**
 * Created by mashara on 5/12/17.
 */
public class InvalidRequestException extends Exception {

	private static final long serialVersionUID = 4664456874499611218L;

	private String errorCode="InvalidRequestException";

	public InvalidRequestException(String message, String errorCode){
		super(message);
		this.errorCode=errorCode;
	}

	public InvalidRequestException(String message){
		super(message);
	}

	public String getErrorCode(){
		return this.errorCode;
	}

}
