package com.sanqing.dao;

import java.util.List;

import org.hibernate.Session;

import com.sanqing.dao.StudentDAOImpl;
import com.sanqing.hibernate.HibernateSessionFactory;
import com.sanqing.po.Student;

public class getResult {

	/**
	 * @param args
	 */
	public Integer getResultString(String nameString) {
		     Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		     Integer result = 0; 
		     System.out.println("+++++++++++++++++++++++++++");
             StudentDAOImpl student = new StudentDAOImpl();
             List<Student> list = student.findByStudentName(nameString);
             for(Student student2 : list){
            	result = student2.getResult();
            	System.out.println(result);
            }
			return result;
	}
	/*
	public static void main(String args[]){
		getResult result = new getResult();
		String nameString = "¿Ó∫£ÃŒ";
		result.getResultString(nameString);  
	}
	*/
}
