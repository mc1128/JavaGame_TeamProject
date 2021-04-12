package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Result extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		new Result();
	}

	static JLabel Result_Label;
	static JLabel Gold_Label;
	static ImageIcon goldIcon;
	String resultText; // 결과값 메시지
	int goldResult; // 골드값 메시지
	
	String path;

	public Result() {
		
		try { // path 기본 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		};
		
		String back_path = path + "image/battingback.png";
		String board_path = path + "image/r_board.png";

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
		String gold_path = result(); // 결과 처리
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


		contentPane.setBackground(backColor);

		JLabel board = new JLabel(new ImageIcon(board_path));
		board.setLocation(12, 39);
		board.setSize(346, 97);
		getContentPane().add(board);

		JLabel back = new JLabel(new ImageIcon(back_path));
		back.setLocation(0, 0);
		back.setSize(594, 371);
		getContentPane().add(back);
		
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

			pstmt.setInt(1, Values.gold_save);
			pstmt.setInt(2, Values.user_win);
			pstmt.setInt(3, Values.user_defeat);
			pstmt.setInt(4, Values.user_draw);
			pstmt.setString(5, Values.user_id);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}

			pstmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	String result() {

		String gold_path = ""; // 결과값에 따른 이미지 경로
		String path = ""; // 기본 경로

		try { // path 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		if ((Values.userHPData > Values.comHPData) && Values.comHPData == 0) { // 대승리
			resultText = "이겼습니다!";
			Values.reward = (int) (Values.gold * 2.5);
			Values.gold_save += Values.reward;
			Values.user_win++;
			gold_path = path + "image/r_win.png";
		} else if (Values.userHPData > Values.comHPData) { // 승리
			resultText = "이겼습니다!";
			Values.reward = Values.gold * 2;
			Values.gold_save += Values.reward;
			Values.user_win++;
			gold_path = path + "image/r_win.png";
		} else if (Values.userHPData == Values.comHPData) { // 무승부
			resultText = "비겼습니다!";
			Values.reward = Values.gold * 1;
			Values.gold_save += Values.reward;
			Values.user_draw++;
			gold_path = path + "image/r_draw.png";
		} else if (Values.userHPData < Values.comHPData) { // 패배
			resultText = "졌습니다!";
			Values.reward = Values.gold * 0;
			Values.gold_save += Values.reward;
			Values.user_defeat++;
			gold_path = path + "image/r_defeat.png";
		}

		Result_Label.setText(resultText);
		Gold_Label.setText(String.valueOf("+ " + Values.reward + " gold"));

		return gold_path;

	} // result() end
}// 클래스 end
