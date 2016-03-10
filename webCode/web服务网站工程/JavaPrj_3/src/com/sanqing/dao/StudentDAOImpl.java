package com.sanqing.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import bean.TbStudent;

import com.sanqing.hibernate.HibernateSessionFactory;
import com.sanqing.po.Student;
import com.sanqing.po.Subject;

public class StudentDAOImpl implements StudentDAO{
	public Student findByStudentID(String studentID) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Student student = (Student) session.get(Student.class, studentID);
		HibernateSessionFactory.closeSession();//�ر�Session����
		return student;
	}

	public void updateStudent(Student student) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Transaction  transaction = null;//����һ���������
		try{
			transaction = session.beginTransaction();//��������
			session.update(student);//����ѧ����Ϣ
			transaction.commit();//�ύ����
		}catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();//����ع�
		}
		HibernateSessionFactory.closeSession();//�ر�Session����
	}
	public List<Student> findByStudentName(String studentName) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Student as stu where stu.studentName = ?");
		query.setString(0, studentName);
		List<Student> list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();		//�ر�Session����
		return list;
	}
	public static Integer findResultByName(String studentName) {
		Integer result = 0;
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Student as stu where stu.studentName = ?");
		query.setString(0, studentName);
		List<Student> list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();		//�ر�Session����
		for(Student student : list){
			result = student.getResult();
		}
		return result;
	}
	public List<Student> findByStudentClass(String sclass) {
		Session session = HibernateSessionFactory.getSession();//���Session����
		Query query = session.createQuery("from Student as stu where stu.sclass = ?");
		query.setString(0, sclass);
		List list = query.list();					//��ѯ������浽list��
		HibernateSessionFactory.closeSession();		//�ر�Session����
		return list;
	}
}
