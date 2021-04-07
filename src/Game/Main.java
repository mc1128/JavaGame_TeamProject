package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

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
		new Main();
	}

	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	String path;

	// password 암호 해석해서 받기위해 설정
	String pw = "";
	char[] secret_pw;

	public Main() {

		setTitle("메인화면 테스트용 수정2");

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

		JButton LoginButton = new JButton("로그인");
		LoginButton.setBounds(251, 259, 100, 45);
		contentPane.add(LoginButton);

		JButton JoinButton = new JButton("회원가입");
		JoinButton.setBounds(257, 314, 89, 31);
		contentPane.add(JoinButton);

		try { // path 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		String title_path = path + "image/logoresize.png";
		String back_path = path + "image/background.png";

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

	}

	private void login() {

		secret_pw = PW_Field.getPassword();

		for (char cha : secret_pw) {
			Character.toString(cha);
			pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
		}

		try {
			String test = "SELECT user_id, user_pw FROM PROFILE";
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(test);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String user_id = rs.getString(1);
				String user_pw = rs.getString(2);

				String id_input = ID_Field.getText();

				if (user_id.equals(id_input) && user_pw.equals(pw)) {
					Values.id_save = user_id;
					new Loading(); // 수정
					dispose();
					break;
				} else {
					System.out.println("재입력");
					pw = "";
					ID_Field.setText(null);
					PW_Field.setText(null);
					ID_Field.requestFocus();
				}
			}
			rs.close(); pstm.close(); conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
