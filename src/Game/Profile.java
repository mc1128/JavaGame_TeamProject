package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;

import javax.swing.*;

public class Profile extends JFrame {
	private final JLabel back = new JLabel("New label");

	String path;

	public Profile() {
//		getContentPane().setForeground(new Color(0, 0, 0));
//		getContentPane().setBackground(new Color(255, 239, 213));

		setTitle("유저 프로필");

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("유저 프로필");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(224, 27, 150, 30);
		getContentPane().add(lblNewLabel);
//		lblNewLabel.setOpaque(true);
//		lblNewLabel.setBackground(new Color(211, 211, 211));
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));

		JLabel lbGold = new JLabel("보유 골드 : " + Values.gold_save);
		lbGold.setBounds(171, 109, 111, 25);
		getContentPane().add(lbGold);
//		lbGold.setOpaque(true);
		// lbGold.setBackground(new Color(211, 211, 211));

		JLabel lbWin = new JLabel("승 : " + Values.user_win);
		lbWin.setBounds(171, 131, 111, 25);
		getContentPane().add(lbWin);
//		lbWin.setOpaque(true);

		JLabel lbDraw = new JLabel("무 : " + Values.user_draw);
		lbDraw.setBounds(171, 154, 111, 25);
		getContentPane().add(lbDraw);
//		lbDraw.setOpaque(true);

		JLabel defeat = new JLabel("패 : " + Values.user_defeat);
		defeat.setBounds(171, 177, 111, 25);
		getContentPane().add(defeat);
//		defeat.setOpaque(true);
		
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
//		lbWinRate.setBackground(new Color(211, 211, 211));
//		lbWinRate.setForeground(new Color(0, 0, 0));
//		lbWinRate.setOpaque(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try { // path 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		String back_path = path + "image/p_parch.png";
		String frame_path = path + "image/p_frame.png";

		JLabel lbName = new JLabel("닉네임 : " + Values.name);
		lbName.setBounds(171, 85, 111, 25);
		getContentPane().add(lbName);
		// lbName.setForeground(new Color(0, 0, 0));
		// lbName.setBackground(new Color(211, 211, 211));
//		lbName.setOpaque(true);

		JButton startGame = new JButton("게임 시작");
		startGame.setBounds(243, 291, 120, 35);
		getContentPane().add(startGame);
		
		JLabel frame = new JLabel(new ImageIcon(frame_path));
		frame.setBounds(324, 67, 150, 214);
		getContentPane().add(frame);

		// 배경 이미지

		JLabel back_1 = new JLabel(new ImageIcon(back_path));
		back_1.setBounds(0, 0, 584, 361);
		getContentPane().add(back_1);

		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Batting();
			}
		});

		setBounds(100, 100, 600, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Profile();

	}
}
