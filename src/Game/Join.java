package Game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField ID_Join;
	private JPasswordField PW_Join;
	private JButton btnNewButton;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new Join();
	}

	public Join() {
		setTitle("회원가입 화면 텟");

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

		PW_Join = new JPasswordField();
		PW_Join.setEchoChar('*');
		PW_Join.setBounds(95, 220, 200, 25);
		contentPane.add(PW_Join);
		PW_Join.setColumns(10);

		btnNewButton = new JButton("완료");
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

		setVisible(true); // GUI 최하단에 두기

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				connect(); // DB연결
				join(); // DB profile 테이블에 회원가입 정보 삽입

			}
		});
	} // 생성자 end

	public void Main() {
		setVisible(false);
		new Main();
	}

	private void connect() {
		try {
			conn = DBConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void join() { // DB profile 테이블에 회원가입 정보를 삽입하는 메서드

		if (ID_Join.getText().isEmpty()) { // id를 입력하지 않았을 경우 기본창 반환
			JOptionPane.showMessageDialog(btnNewButton, "ID를 입력하세요.");
			return;
		} else if (PW_Join.getPassword().length == 0) { // 비밀번호를 입력하지 않았을 경우 기본창 반환
			JOptionPane.showMessageDialog(btnNewButton, "비밀번호를 입력하세요.");
			return;
		}

		try { // gold 기본값(1000), 나머지는 모두 0
			String sql = "insert into profile values(?,?,1000,0,0,0,sysdate)";
			
			String password = new String(PW_Join.getPassword());
	         
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID_Join.getText());
			pstmt.setString(2, password);

			int res = pstmt.executeUpdate();

			if (res > 0) {
				JOptionPane.showMessageDialog(btnNewButton, "가입이 완료되었습니다.");
			} else {
				JOptionPane.showMessageDialog(btnNewButton, "다시 시도하세요.");
			}

			pstmt.close();
			conn.close();

		} catch (SQLIntegrityConstraintViolationException e1) { // id 중복시 실행창
			e1.printStackTrace();
			JOptionPane.showMessageDialog(btnNewButton, "중복된 ID입니다.");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Main(); // 수정
		dispose();

	}

}