package Game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField ID_Join;
	private JTextField PW_Join;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join frame = new Join();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Join() {
		setTitle("회원가입 화면");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Panel_Join = new JLabel("회원가입");
		Panel_Join.setFont(new Font("맑은 고딕", Font.PLAIN, 36));
		Panel_Join.setHorizontalAlignment(SwingConstants.CENTER);
		Panel_Join.setBounds(114, 51, 160, 42);
		contentPane.add(Panel_Join);
		
		ID_Join = new JTextField();
		ID_Join.setBounds(95, 160, 200, 25);
		contentPane.add(ID_Join);
		ID_Join.setColumns(10);
		
		PW_Join = new JTextField();
		PW_Join.setBounds(95, 220, 200, 25);
		contentPane.add(PW_Join);
		PW_Join.setColumns(10);
		
		JButton btnNewButton = new JButton("완료");
		btnNewButton.setBounds(130, 300, 125, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(165, 140, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 200, 115, 15);
		contentPane.add(lblNewLabel_2);
	}
}
