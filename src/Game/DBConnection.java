package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	 public static Connection dbConn;
	 public static PreparedStatement pstm;
	 public static ResultSet rs;
	    
     public static Connection getConnection()
     {
         Connection dbConn = null;
         try {
             String user = "web"; 
             String pw = "1234";
             String url = "jdbc:oracle:thin:@localhost:1521:xe";
             
             Class.forName("oracle.jdbc.driver.OracleDriver");        
             dbConn = DriverManager.getConnection(url, user, pw);
             
             System.out.println("Database에 연결되었습니다.\n");
             
         } catch (ClassNotFoundException cnfe) {
             System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
         } catch (SQLException sqle) {
             System.out.println("DB 접속실패 : "+sqle.toString());
         } catch (Exception e) {
             System.out.println("Unkonwn error");
             e.printStackTrace();
         }
         return dbConn;     
     }

}
