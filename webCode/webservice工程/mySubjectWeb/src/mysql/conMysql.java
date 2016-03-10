package mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class conMysql {
	private static Connection connection;
	static{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/db_examsystem";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("加载驱动失败");
			e.printStackTrace();
		}
		try {
			connection=DriverManager.getConnection(url, "root", "lht19921021");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() {
		return connection;
	}
	
	public static String[] getSubjectById(int id) throws SQLException{
		
	    //List<ResultSet> list = new ArrayList();
	    String[] subject = new String[6];
		Statement st = (Statement) connection.createStatement();
		//String sql = "select st.result from tb_student st where st.studentID = 2011303524 ";
		String sql = "select * from tb_subject sub";
		//String sql = "select *　from tb_student";
		// preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		// preparedStatement.setString(1,name);
        ResultSet res = st.executeQuery(sql);
       
		while(res.next()){
			if(res.getInt("subjectID")==id){
				
				subject[0] = "题目：" + res.getString("subjectTitle");
				subject[1] = "A" + "." + res.getString("subjectOptionA");
				subject[2] = "B" + "." + res.getString("subjectOptionB");
				subject[3] = "C" + "." + res.getString("subjectOptionC");
				subject[4] = "D" + "." + res.getString("subjectOptionD");
				subject[5] = "答案："  +   res.getString("subjectAnswer");
			
			}
		}
		//System.out.println(myresult);
		//for(int i=0;i<6;i++){
		//	System.out.println(subject[i]);
		//}
		/*
		for(ResultSet resa : list){
			System.out.println("qqqqqq");
			while(resa.next()){
				System.out.println("wwwww");
				System.out.println(resa.getString("subjectTitle"));
				System.out.println(resa.getString("subjectOptionA"));
			}
		}
		*/
		return subject;		
	}
	
	public  static void main(String [] args) throws Exception {
		Connection con= conMysql.getConnection();
		//getResult(2011303524);
		//getSubjectById(6);
		if(!con.equals("")){
			System.out.print("数据库连接成功，连接id是:"+con);
		}
	}
	
}