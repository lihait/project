package service;

import java.sql.SQLException;

import mysql.conMysql;

public class getSubjectById {

	public getSubjectById(){
		
	}
	
	public static String[] getSubjectById(int id) throws Exception{
		String[] subjectStrings = new String[6];
		subjectStrings = conMysql.getSubjectById(id);
		return subjectStrings;
	}
}
