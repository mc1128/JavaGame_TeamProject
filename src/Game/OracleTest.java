package Game;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 

public class OracleTest {
	public static void main(String args[])
    {
        Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String test = "SELECT user_pw FROM PROFILE";
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(test);
            rs = pstm.executeQuery();
            
            /*  EMP 테이블의 데이터 타입
             * 
                EMPNO NOT NULL NUMBER(4) -- int
                ENAME VARCHAR2(10) -- String
                JOB VARCHAR2(9) -- String
                MGR NUMBER(4) -- int
                HIREDATE DATE -- Date
                SAL NUMBER(7,2) -- float/double
                COMM NUMBER(7,2) -- float/double
                DEPTNO NUMBER(2) -- int
            */
            
            System.out.println("============================================");
            
            while(rs.next()){
            	
            	
            	String pw = rs.getString(1);
                /*int PNUM = rs.getInt(1);
                //int empno = rs.getInt("empno"); 숫자 대신 컬럼 이름을 적어도 된다.
                String CATECPRU_FK = rs.getString(2);
                String PRODUCTS_NAME = rs.getString(3);
                String ep_code_fk = rs.getString(4);
                int input_price = rs.getInt(5); // Date 타입 처리
                int output_price = rs.getInt(6);
                int trans_cost = rs.getInt(7);
                int mileage = rs.getInt(8);
                String company = rs.getString(9);
                String status = rs.getString(10);*/
                
                //String result = PNUM+" "+CATECPRU_FK+" "+PRODUCTS_NAME+" "+ep_code_fk+" "+input_price+" "+output_price+" "+trans_cost+" "+mileage;
            	String result = pw+ "";
                System.out.println(result);
            }
            
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
    }
}
