package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class Profile extends JFrame {
	private final JLabel back = new JLabel("New label");

	String title;
	
	String path;
	
	static JLabel lbGold;
	private JLabel chingho;

	public Profile() {

		setTitle("유저 프로필"); 
		
		setIconImage(Main.img);
		
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("유저 프로필");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setBounds(224, 27, 150, 30);
		getContentPane().add(lblNewLabel);

		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));

		lbGold = new JLabel("보유 골드 : " + Values.gold_save);
		lbGold.setBounds(171, 135, 111, 25);
		getContentPane().add(lbGold);

		JLabel lbWin = new JLabel("승 : " + Values.user_win);
		lbWin.setBounds(171, 160, 111, 25);
		getContentPane().add(lbWin);

		JLabel lbDraw = new JLabel("무 : " + Values.user_draw);
		lbDraw.setBounds(171, 185, 111, 25);
		getContentPane().add(lbDraw);

		JLabel defeat = new JLabel("패 : " + Values.user_defeat);
		defeat.setBounds(171, 210, 111, 25);
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
		lbWinRate.setBounds(171, 235, 111, 25);
		getContentPane().add(lbWinRate);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try { // path 설정
			path = URLDecoder.decode(Game_Screen.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		String back_path = path + "image/p_parch.png";
		String frame_path = path + "image/p_frame.png";
		String gold_path = path + "image/p_gold.png";
		String gold_b_path = path + "image/p_gold_b.png";

		// button 이미지
		String button_path = path + "image/button.png";
		ImageIcon changedImg = imageResize(button_path, 100, 30);		
		
		String button_path_b = path + "image/button_b.png";
		ImageIcon b = imageResize(button_path_b, 100, 30);
		
		// 칭호 이미지
		String pla_path = path + "image/p_pla.png";		
		ImageIcon pla = imageResize(pla_path, 35, 35);
		
		String silver_path = path + "image/p_silver.png";		
		ImageIcon silver = imageResize(silver_path, 35, 35);
		
		String bronze_path = path + "image/p_bronze.png";		
		ImageIcon bronze = imageResize(bronze_path, 35, 35);

		
		JLabel lbName = new JLabel("닉네임 : " + Values.user_name);
		lbName.setBounds(171, 110, 111, 25);
		getContentPane().add(lbName);

		JButton startGame = new JButton("게임시작", changedImg);
		startGame.setForeground(Color.WHITE);	// 글씨색 흰색
		startGame.setHorizontalTextPosition(JButton.CENTER);	// 글자 중앙정렬
		startGame.setContentAreaFilled(false);	// 버튼 배경 투명화
		startGame.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		startGame.setRolloverIcon(b); // 호버효과
		startGame.setBounds(240, 291, 100, 30);
		getContentPane().add(startGame);

		JButton Rank_show = new JButton("랭킹확인", changedImg);
		Rank_show.setForeground(Color.WHITE);	// 글씨색 흰색
		Rank_show.setHorizontalTextPosition(JButton.CENTER);	// 글자 중앙정렬
		Rank_show.setContentAreaFilled(false);	// 버튼 배경 투명화
		Rank_show.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		Rank_show.setRolloverIcon(b); // 호버효과
		Rank_show.setBounds(120, 291, 100, 30);
		getContentPane().add(Rank_show);

		JButton Logout = new JButton("로그아웃", changedImg);
		Logout.setForeground(Color.WHITE);	// 글씨색 흰색
		Logout.setHorizontalTextPosition(JButton.CENTER);	// 글자 중앙정렬
		Logout.setContentAreaFilled(false);	// 버튼 배경 투명화
		Logout.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		Logout.setRolloverIcon(b); // 호버효과
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
		
		// 칭호
		JLabel chingho = new JLabel();
		chingho.setBounds(131, 75, 35, 35);
		getContentPane().add(chingho);
		
		get_gold.setBorderPainted(false); 
		get_gold.setFocusPainted(false); 
		get_gold.setContentAreaFilled(false);
		
		if(Values.gold_save >= 20000 && rate >= 50) {
			title = "도박의 신";
			chingho.setIcon(pla);
		}else if(Values.gold_save >= 10000) {
			title = "도박왕";
			chingho.setIcon(silver);
		}else if(Values.gold_save < 10000) {
			title = "뉴비";
			chingho.setIcon(bronze);
		}
		
		JLabel LbTitle = new JLabel("칭호 : " + title);
		LbTitle.setBounds(171, 85, 141, 15);
		getContentPane().add(LbTitle);
		

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
	
	ImageIcon imageResize(String path, int width, int heigh){
		ImageIcon originIcon = new ImageIcon(path); // ImageIcon객체를 생성
		Image originImg = originIcon.getImage(); // ImageIcon에서 Image를 추출
		Image changedImg = originImg.getScaledInstance(width, heigh, Image.SCALE_SMOOTH);
		ImageIcon changedImgIcon = new ImageIcon(changedImg);
		return changedImgIcon;
	}

	public static void main(String[] args) {
		new Profile();

	}
}
