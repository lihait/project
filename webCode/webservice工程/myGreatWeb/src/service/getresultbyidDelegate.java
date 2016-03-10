package service;

import mysql.conMysql;

@javax.jws.WebService(targetNamespace = "http://service/", serviceName = "getresultbyidService", portName = "getresultbyidPort", wsdlLocation = "WEB-INF/wsdl/getresultbyidService.wsdl")
public class getresultbyidDelegate {

	service.getresultbyid getresultbyid = new service.getresultbyid();

	public Integer getResult(int myid) throws Exception {
		return getresultbyid.getResult(myid);
	}

}