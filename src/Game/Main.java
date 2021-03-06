package Game;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
		ImageIcon changedImg1 = Values.imgResize(button_path, 100, 45);	// 이미지 리사이즈
		ImageIcon changedImg2 = Values.imgResize(button_path, 89, 31);	

		String button_path_b = path + "image/button_b.png";
		ImageIcon b1 = Values.imgResize(button_path_b, 100, 45);	// 이미지 리사이즈
		ImageIcon b2 = Values.imgResize(button_path_b, 89, 31);

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

		JButton LoginButton = new JButton("로그인", changedImg1);
		Values.setButton(LoginButton, b1);	// 버튼 기본세팅

		LoginButton.setBounds(251, 259, 100, 45);
		contentPane.add(LoginButton);	

		// LoginButton.setBorderPainted(false);
		// LoginButton.setFocusPainted(false);
		// LoginButton.setContentAreaFilled(false);

		JButton JoinButton = new JButton("회원가입", changedImg2);
		Values.setButton(JoinButton, b2); 	// 버튼 기본세팅
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
