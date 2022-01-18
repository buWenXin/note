package com.utis;

/**
 * CommomError
 */
public interface CommomError {

	int getErrCode();
	
	String getErrMsg();
	
	CommomError setErrMsg(String errMsg);
}
