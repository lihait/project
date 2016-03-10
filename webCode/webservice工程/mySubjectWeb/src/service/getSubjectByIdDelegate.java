package service;

import java.sql.SQLException;
import mysql.conMysql;

@javax.jws.WebService(targetNamespace = "http://service/", serviceName = "getSubjectByIdService", portName = "getSubjectByIdPort", wsdlLocation = "WEB-INF/wsdl/getSubjectByIdService.wsdl")
public class getSubjectByIdDelegate {

	service.getSubjectById getSubjectById = new service.getSubjectById();

	public String[] getSubjectById(int id) throws Exception {
		return getSubjectById.getSubjectById(id);
	}

}