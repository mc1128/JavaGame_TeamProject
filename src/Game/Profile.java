package Game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Profile extends JFrame {
	private final JLabel back = new JLabel("New label");

	String path;

	static JLabel lbGold;
	
	public Profile() {

		setTitle("유저 프로필");

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("유저 프로필");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setBounds(224, 27, 150, 30);
		getContentPane().add(lblNewLabel);

		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));

		lbGold = new JLabel("보유 골드 : " + Values.gold_save);
		lbGold.setBounds(171, 109, 111, 25);
		getContentPane().add(lbGold);


		JLabel lbWin = new JLabel("승 : " + Values.user_win);
		lbWin.setBounds(171, 131, 111, 25);
		getContentPane().add(lbWin);


		JLabel lbDraw = new JLabel("무 : " + Values.user_draw);
		lbDraw.setBounds(171, 154, 111, 25);
		getContentPane().add(lbDraw);


		JLabel defeat = new JLabel("패 : " + Values.user_defeat);
		defeat.setBounds(171, 177, 111, 25);
		getContentPane().add(defeat);

		
		int total = Values.user_win + Values.user_defeat + Values.user_draw;
		double rate = (double) ((double) Values.user_win / (double) total) * 100.0;
		if (Double.isNaN(rate)) {
			rate = (double) (0.00);
		}
		String dispPattern = "0.##";
		DecimalFormat form = new DecimalFormat(dispPattern);
		String winning_rate = form.format(rate) + "%";
		
		JLabel lbWinRate = new JLabel("승률 : " + winning_rate);
		lbWinRate.setBounds(171, 212, 111, 25);
		getContentPane().add(lbWinRate);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try { // path 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		String back_path = path + "image/p_parch.png";
		String frame_path = path + "image/p_frame.png";
		String gold_path = path + "image/p_gold.png";
		String gold_b_path = path + "image/p_gold_b.png";

		JLabel lbName = new JLabel("닉네임 : " + Values.user_name);
		lbName.setBounds(171, 85, 111, 25);
		getContentPane().add(lbName);


		JButton startGame = new JButton("게임시작");
		startGame.setBounds(240, 291, 100, 30);
		getContentPane().add(startGame);
		
		JButton Rank_show = new JButton("랭킹확인");
		Rank_show.setBounds(120, 291, 100, 30);
		getContentPane().add(Rank_show);
		
		JButton Logout = new JButton("로그아웃");
		Logout.setBounds(360, 291, 100, 30);
		getContentPane().add(Logout);
		
		JLabel frame = new JLabel(new ImageIcon(frame_path));
		frame.setBounds(324, 67, 150, 214);
		getContentPane().add(frame);

		// 배경 이미지
		
		ImageIcon gold = new ImageIcon(gold_path);
		ImageIcon gold_b = new ImageIcon(gold_b_path);
		
		JButton get_gold = new JButton(gold);
		get_gold.setBounds(257, 236, 55, 45);
		getContentPane().add(get_gold);
		
		//get_gold.setHorizontalTextPosition(JButton.CENTER);
		
		
		get_gold.setBorderPainted(false); 
		get_gold.setFocusPainted(false); 
		get_gold.setContentAreaFilled(false);

		
		JLabel back_1 = new JLabel(new ImageIcon(back_path));
		back_1.setBounds(0, 0, 584, 361);
		getContentPane().add(back_1);

		
		
		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				con = DBConnection.getConnection();

				try {
					String sql = "update profiles set user_gold = ? where user_id = ?";

					pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, Values.gold_save);
					pstmt.setString(2, Values.user_id);

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
				
				setVisible(false);
				new Batting();
			}
		});
		
		Rank_show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Ranking();
			}
		});
		
		Logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Main();		
			}
		});
		
		get_gold.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				get_gold.setIcon(gold_b);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				get_gold.setIcon(gold);
			}
		});
		
		get_gold.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Values.gold_save++;
				lbGold.setText("보유 골드 : " + Values.gold_save);
			}
		});
		
		

		setBounds(100, 100, 600, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Profile();

	}
}
