package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Result extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Result();
	}

	static JLabel Result_Label;
	static JLabel Gold_Label;
	static ImageIcon goldIcon;
	String resultText;	// 결과값 메시지
	int goldResult;		// 골드값 메시지	
	
	
	public Result() {
		
		setTitle("게임 결과");		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Result_Label = new JLabel("결과값");
		Result_Label.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		Result_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Result_Label.setBounds(230, 21, 129, 45);
		contentPane.add(Result_Label);
		
		Gold_Label = new JLabel("얻은 골드 값");
		Gold_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Gold_Label.setBounds(237, 76, 114, 21);
		contentPane.add(Gold_Label);
		
		// 골드 이미지
		String gold_path = result();	// 결과 처리
		JLabel Gold_Image = new JLabel(new ImageIcon(gold_path));
		Gold_Image.setHorizontalAlignment(SwingConstants.CENTER);
		Gold_Image.setBounds(223, 120, 150, 150);
		contentPane.add(Gold_Image);
		
		JButton Replay_Button = new JButton("다시 플레이");
		Replay_Button.setBounds(100, 300, 150, 30);
		contentPane.add(Replay_Button);
		
		JButton BackMain_Button = new JButton("나가기");
		BackMain_Button.setBounds(335, 300, 150, 30);
		contentPane.add(BackMain_Button);
		
		// 색상 지정
		Color backColor = new Color(210, 180, 145);
		Color lineColor = new Color(252, 247, 222);
		Color buttonColor = new Color(121, 117, 117);
		
		JPanel jp9 = new JPanel();
		jp9.setBounds(0, 0, 594, 21);
		getContentPane().add(jp9);
		
		JPanel jp10 = new JPanel();
		jp10.setBounds(0, 330, 594, 43);
		getContentPane().add(jp10);
		
		jp9.setBackground(lineColor);
		jp10.setBackground(lineColor);		
		contentPane.setBackground(backColor);
		
		setVisible(true);
		
		// 이벤트 처리
		// 다시 플레이 버튼
		Replay_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Batting();
				dispose();
			}
		});
		
		// 나가기 버튼
		BackMain_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Main();
				dispose();
			}
		});
				
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = DBConnection.getConnection();
		
		
		try {
			String sql = "update profile set user_gold = ?,user_win = ?, user_defeat = ? , user_draw = ? where user_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			 pstmt.setDouble(1, Values.gold_save);
			 pstmt.setDouble(2, Values.user_win);
			 pstmt.setDouble(3, Values.user_defeat);
			 pstmt.setDouble(4, Values.user_draw);
			 pstmt.setString(5, Values.id_save);
			 
			 pstmt.executeUpdate();
			 
			pstmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(Values.gold_save);
		
		
		
	}// 생성자 end
	
	String result() {
		
		String gold_path = "";	// 결과값에 따른 이미지 경로
		String path = "";	// 기본 경로
		
		try { // path 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;
		
		if((Values.userHPData > Values.comHPData) && Values.comHPData == 0) {	// 대승리
			resultText = "이겼습니다!";
			Values.reward = Values.gold * 2.5;
			Values.gold_save += Values.reward;
			Values.user_win++;
			gold_path = path+"image/logoresize.png";
		}else if(Values.userHPData > Values.comHPData) {	// 승리
			resultText = "이겼습니다!";
			Values.reward = Values.gold * 2;
			Values.gold_save += Values.reward;
			Values.user_win++;
			gold_path = path+"image/logo.png";
		}else if(Values.userHPData == Values.comHPData) {	// 무승부
			resultText = "비겼습니다!";
			Values.reward = Values.gold * 1;
			Values.gold_save += Values.reward;
			Values.user_draw++;
			gold_path = path+"image/sample03.gif";
		}else if(Values.userHPData < Values.comHPData) {	// 패배
			resultText = "졌습니다!";
			Values.reward = Values.gold * 0;
			Values.gold_save += Values.reward;
			Values.user_defeat++;
			gold_path = path+"image/sample04.gif";
		}
		
		Result_Label.setText(resultText);
		Gold_Label.setText(String.valueOf("+ "+Values.reward+" gold"));
		return gold_path;
		
		
		
	} // result() end
}// 클래스 end
