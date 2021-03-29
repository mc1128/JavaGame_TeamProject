package Game;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

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
	public Main() {
		setTitle("메인화면 테스트용 수정");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel GameTitle = new JLabel("게임타이틀");
		GameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GameTitle.setBounds(200, 51, 205, 60);
		contentPane.add(GameTitle);
		
		ID_Field = new JTextField();
		ID_Field.setBounds(200, 151, 203, 30);
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
		
		setVisible(true);
		
		LoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//LoginButton.setEnabled(false);
				Batting();
			}
		});
		
		JoinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Join();
			}
		});
		
		
	}
	public void Batting() {
		setVisible(false);
		new Batting(); 
}
	public void Join() {
		setVisible(false);
		new Join(); 
}
}