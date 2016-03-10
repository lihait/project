package service;

import mysql.conMysql;

public class getresultbyid {

	/**
	 * @param args
	 */
public static Integer getResult(int myid) throws Exception{
		
		int results = conMysql.getResultById(myid);
		
		//int results = 0;
		
		return results;
	}

}
