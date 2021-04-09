package Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Ranking extends JFrame {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	JComboBox<String> jcb, jcb1;
	DefaultTableModel model;
	JTable table;
	JTextField jtf1, jtf2, jtf3, jtf5, jtf6;

	public Ranking() {

		setTitle("랭킹확인");

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
	

		JLabel jl1 = new JLabel("아이디 : ");
		jtf1 = new JTextField(7);

		String[] header = { "랭킹", "아이디", "골드량", "승리횟수", "패배횟수", "무승부횟수", "승률" };

		model = new DefaultTableModel(header, 0);

		table = new JTable(model);

		JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton jb1 = new JButton("랭킹목록");
		JButton jb2 = new JButton("나의 랭킹확인");

		jp1.add(jl1);
		jp1.add(jtf1);

		jp3.add(jb1);
		jp3.add(jb2);

		JPanel pg = new JPanel(new BorderLayout());

		pg.add(jp2, BorderLayout.NORTH);
		pg.add(jsp, BorderLayout.CENTER);
		pg.add(jp3, BorderLayout.SOUTH);

		add(jp1, BorderLayout.NORTH);
		add(pg, BorderLayout.CENTER);

		connect();

		setBounds(200, 200, 600, 400);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);


		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
				model.setRowCount(0); // 전체 테이블 화면을 지워줌
				Rank();

			}
		});

		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
				userRank();

				// 입력하는 텍스트필드 영역 초기화
				jtf1.setText(null);
				jtf1.requestFocus();
				jcb.setSelectedIndex(0);

				model.setRowCount(0);
				Rank();

			}
		});

	} // 생성자 end

	// DB 연동하는 메서드
	private void connect() {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";

		try {
			// 1. 접속할 오라클 드라이버를 메모리에 올리자.
			Class.forName(driver);

			// 2. 데이터베이스와 연결을 하자.
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void userRank() {

		try {

			String sql = "select user_id, user_gold, user_win, user_defeat, user_draw from profile order by user_gold desc, user_win desc";

			pstmt = con.prepareStatement(sql);

			String user_get = jtf2.getText();
			
			pstmt.setString(1, user_get);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			}

			// open 객체 닫기
			rs.close();
			pstmt.close(); // con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	// 랭킹조회 메서드
	private void Rank() {

		try {
			String sql = "select user_id, user_gold, user_win, user_defeat, user_draw from profile order by user_gold desc, user_win desc";

			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int i = 1;

			while (rs.next()) { // DB 레코드 수만큼 반복
				
				String user_id = rs.getString("user_id");
				int user_gold = rs.getInt("user_gold");
				int user_win = rs.getInt("user_win");
				int user_defeat = rs.getInt("user_defeat");
				int user_draw = rs.getInt("user_draw");
				//String user_draw = rs.getString("hiredate").substring(0, 10);
				
				//승률 계산
				int total = user_win + user_defeat + user_draw;
				double rate = (double)((double)user_win / (double)total) * 100.0;
				
				System.out.println(rate);
				
				//승리, 패배, 무승부 값이 0이라서 double 값이 NaN이라면 승률에 0.00%로 변경
				if(Double.isNaN(rate)) {
					rate = (double)(0.00);
				}
				
				//퍼센트를 소숫점 두자리까지만 표시되도록 패턴 설정
				String dispPattern = "0.##";
				DecimalFormat form = new DecimalFormat(dispPattern);
				String winning_rate = form.format(rate) + "%";
				
				System.out.println(winning_rate);
				
				Object[] data = { i, user_id, user_gold, user_win, user_defeat, user_draw, winning_rate};

				// table model에 data 추가
				model.addRow(data);
				
				i++;
			}

			// 4. open 객체 닫기
			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {

		new Ranking();

	}

}