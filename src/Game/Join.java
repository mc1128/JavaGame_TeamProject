package Game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Color;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Join.class.getResource("/Game/image/joinback.png")));
		setTitle("회원가입 화면 텟");
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Panel_Join = new JLabel("회원가입");
		Panel_Join.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		Panel_Join.setHorizontalAlignment(SwingConstants.CENTER);
		Panel_Join.setBounds(114, 79, 160, 42);
		contentPane.add(Panel_Join);
		
		ID_Join = new JTextField();
		ID_Join.setBounds(103, 160, 172, 25);
		contentPane.add(ID_Join);
		ID_Join.setColumns(10);
		
		PW_Join = new JPasswordField();
		PW_Join.setBounds(103, 214, 171, 25);
		contentPane.add(PW_Join);
		PW_Join.setColumns(10);
		
		btnNewButton = new JButton("완료");
		btnNewButton.setBounds(129, 280, 125, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(165, 140, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(139, 195, 115, 15);
		contentPane.add(lblNewLabel_2);
		
		// 이하 배경화면 
		String path="";
		
		try { // path 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		String join_path = path + "image/joinback.png";

		JLabel join_back = new JLabel(new ImageIcon(join_path));
		join_back.setLocation(0, 0);
		join_back.setSize(384, 361);
		contentPane.add(join_back);
		
		setVisible(true);	// GUI 최하단에 두기
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();	// DB연결
				join();		// DB profile 테이블에 회원가입 정보 삽입
				
				new Main(); // 수정
				setVisible(false);
			}
		});
		
	}
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
	
	private void join() {	// DB profile 테이블에 회원가입 정보를 삽입하는 메서드

		if(ID_Join.getText().isEmpty()) {	// id를 입력하지 않았을 경우 기본창 반환
			JOptionPane.showMessageDialog(btnNewButton, "ID를 입력하세요.");
			return;
		}
		else if(PW_Join.getPassword().length == 0) {	// 비밀번호를 입력하지 않았을 경우 기본창 반환
			JOptionPane.showMessageDialog(btnNewButton, "비밀번호를 입력하세요.");
			return;
		}
		
		try {	// gold 기본값(1000), 나머지는 모두 0
			String sql = "insert into profile values(?,?,1000,0,0,0,sysdate)";
			String password = new String(PW_Join.getPassword());
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID_Join.getText());
			pstmt.setString(2, password);
	
			int res = pstmt.executeUpdate();
			
			if(res>0) {
				JOptionPane.showMessageDialog(btnNewButton, "가입이 완료되었습니다.");
			}else {
				JOptionPane.showMessageDialog(btnNewButton, "다시 시도하세요.");
			}
			
			pstmt.close(); conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}