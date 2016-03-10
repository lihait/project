package subjectclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class getMyResultById {

	/**
	 * @param args
	 * @throws Exception_Exception 
	 */
	public static void main(String[] args) throws Exception_Exception {
		// TODO Auto-generated method stub
		 GetSubjectByIdService Service = new GetSubjectByIdService();
         GetSubjectByIdDelegate sss = Service.getGetSubjectByIdPort();
         System.out.println("请输入要查询的题号：");
         Scanner scanner = new Scanner(System.in);
         int id = scanner.nextInt();
         String[] mysubject = new String[6];
         List list = new ArrayList();
         list = sss.getSubjectById(id);
         for(int i=0;i<list.size();i++){
        	System.out.println(list.get(i));
         }
	}
}
