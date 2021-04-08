package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

	/**
	 * Create the frame.
	 */

	String path;

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
				new Batting(); // 수정
				dispose();

			}
		});
		
		JoinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Join();
			}
		});
	}
}
