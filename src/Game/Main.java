package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField ID_Field;
	private JPasswordField PW_Field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Values();
		new Main();
	}

	// Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	static String path;

	String user_id;
	String user_pw;
	
	static Image img;

	public Main() {

		setTitle("메인화면");

		try { // path 설정
			path = URLDecoder.decode(Game_Screen.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");	
		}
		;

		String title_path = path + "image/logoresize.png";
		String back_path = path + "image/background.png";

		// 화면 아이콘 설정
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		img = toolkit.getImage(title_path);
		setIconImage(img);

		// button 이미지
		String button_path = path + "image/button.png";
		ImageIcon originIcon = new ImageIcon(button_path); // ImageIcon객체를 생성
		Image originImg = originIcon.getImage(); // ImageIcon에서 Image를 추출
		Image changedImg1 = originImg.getScaledInstance(100, 45, Image.SCALE_SMOOTH);
		Image changedImg2 = originImg.getScaledInstance(89, 31, Image.SCALE_SMOOTH);

		String button_path_b = path + "image/button_b.png";
		ImageIcon originIcon_b = new ImageIcon(button_path_b); // ImageIcon객체를 생성
		Image originImg_b = originIcon_b.getImage(); // ImageIcon에서 Image를 추출
		Image changedImg_b1 = originImg_b.getScaledInstance(100, 45, Image.SCALE_SMOOTH);
		Image changedImg_b2 = originImg_b.getScaledInstance(89, 31, Image.SCALE_SMOOTH);
		ImageIcon b1 = new ImageIcon(changedImg_b1);
		ImageIcon b2 = new ImageIcon(changedImg_b2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ID_Field = new JTextField();
		ID_Field.setBounds(200, 159, 203, 30);
		contentPane.add(ID_Field);
		ID_Field.setColumns(10);

		PW_Field = new JPasswordField();
		PW_Field.setEchoChar('*');

		PW_Field.setColumns(10);
		PW_Field.setBounds(200, 199, 203, 30);
		contentPane.add(PW_Field);

		JButton LoginButton = new JButton("로그인", new ImageIcon(changedImg1));
		LoginButton.setForeground(Color.WHITE); // 글씨색 흰색
		LoginButton.setHorizontalTextPosition(JButton.CENTER); // 글자 중앙정렬
		LoginButton.setContentAreaFilled(false); // 버튼 배경 투명화
		LoginButton.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		LoginButton.setRolloverIcon(b1); // 호버효과
		LoginButton.setBounds(251, 259, 100, 45);
		contentPane.add(LoginButton);

		// LoginButton.setBorderPainted(false);
		// LoginButton.setFocusPainted(false);
		// LoginButton.setContentAreaFilled(false);

		JButton JoinButton = new JButton("회원가입", new ImageIcon(changedImg2));
		JoinButton.setForeground(Color.WHITE); // 글씨색 흰색
		JoinButton.setHorizontalTextPosition(JButton.CENTER); // 글자 중앙정렬
		JoinButton.setContentAreaFilled(false); // 버튼 배경 투명화
		JoinButton.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		JoinButton.setRolloverIcon(b2); // 호버효과

		JoinButton.setBounds(257, 314, 89, 31);
		contentPane.add(JoinButton);

		try { // path 설정
			path = URLDecoder.decode(Game_Screen.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		// 타이틀 이미지
		JLabel GameTitle = new JLabel(new ImageIcon(title_path));
		GameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GameTitle.setBounds(146, 10, 312, 139);
		contentPane.add(GameTitle);

		// 배경 이미지
		JLabel Back = new JLabel(new ImageIcon(back_path));
		Back.setBounds(0, 0, 584, 361);
		contentPane.add(Back);

		setVisible(true);

		LoginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		JoinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Join();
			}
		});

		ID_Field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});

		PW_Field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});

	}

	private void login() {

		try {
			String test = "SELECT * FROM PROFILES where user_id = ?";
			DBConnection.dbConn = DBConnection.getConnection();
			pstm = DBConnection.dbConn.prepareStatement(test);

			user_id = ID_Field.getText();

			pstm.setString(1, user_id);

			pstm.executeUpdate();

			String password = new String(PW_Field.getPassword());

			// user_gold = rs.getInt(3);

			String id_input = ID_Field.getText();

			rs = pstm.executeQuery();

			while (rs.next()) {

				Values.user_id = rs.getString(1);
				user_pw = rs.getString(2);
				Values.user_name = rs.getString(3);
				Values.gold_save = rs.getInt(4);
				Values.user_win = rs.getInt(5);
				Values.user_defeat = rs.getInt(6);
				Values.user_draw = rs.getInt(7);
				String user_reg = rs.getString(8);

				if (Values.user_id.equals(id_input) && user_pw.equals(password)) {
					new Loading(); // 수정
					dispose();
					break;
				} else {
					System.out.println("재입력");
					System.out.println(user_id + " " + user_pw);
				}
			}

			ID_Field.setText(null);
			PW_Field.setText(null);
			ID_Field.requestFocus();

			rs.close();
			pstm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
