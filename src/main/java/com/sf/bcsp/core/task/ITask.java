package com.sf.bcsp.core.task;

import com.sf.bcsp.exception.TimeOutException;

;

/**
 * 
 * @author 172162
 *
 */
public interface ITask {
	
	String getName();
	
	int getTimeOut();

	boolean doStart() throws TimeOutException;
	
	boolean doTask() throws TimeOutException;
	
	boolean doFinal();

}
