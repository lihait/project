package mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public static Integer getResultById(int id) throws SQLException{
		
		int myresult = 0;
		//PreparedStatement preparedStatement = null;
		Statement st = (Statement) connection.createStatement();
		//String sql = "select st.result from tb_student st where st.studentID = 2011303524 ";
		String sql = "select * from tb_student st";
		//String sql = "select *　from tb_student";
		// preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		// preparedStatement.setString(1,name);
        ResultSet res = st.executeQuery(sql);
       
		while(res.next()){
			if(res.getInt("studentID")==id){
				myresult = res.getInt("result");
			}
			//System.out.println(myresult);
		}
		//System.out.println(myresult);
		return myresult;		
	}
	
	public  static void main(String [] args) throws Exception {
		Connection con= conMysql.getConnection();
		//getResult(2011303524);
		if(!con.equals("")){
			System.out.print("数据库连接成功，连接id是:"+con);
		}
	}
	
}