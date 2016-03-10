package com.sanqing.dao;

import com.sanqing.action.SubmitExamAction;

public class test {

	public static Integer getResult(String name){
		
		getResult GetResult = new getResult();
		
		int r = GetResult.getResultString(name);
		
		return r;
	}
}
