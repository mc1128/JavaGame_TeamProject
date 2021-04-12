package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Profile extends JFrame {


	
	public Profile() {
		getContentPane().setForeground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(255, 239, 213));

		setTitle("유저 프로필");

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("유저 프로필");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(244, 27, 100, 30);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(211, 211, 211));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(50, 40, 500, 300);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lbName = new JLabel("닉네임 : " + Values.user_name);
		lbName.setForeground(new Color(0, 0, 0));
		lbName.setBackground(new Color(211, 211, 211));
		lbName.setOpaque(true);
		lbName.setBounds(123, 39, 111, 25);
		panel.add(lbName);

		JLabel lbGold = new JLabel("보유 골드 : " + Values.gold_save);
		lbGold.setForeground(new Color(0, 0, 0));
		lbGold.setBounds(123, 74, 111, 25);
		lbGold.setOpaque(true);
		panel.add(lbGold);
		lbGold.setBackground(new Color(211, 211, 211));

		JLabel lbWin = new JLabel("승 : " + Values.user_win);
		lbWin.setBackground(new Color(211, 211, 211));
		lbWin.setForeground(new Color(0, 0, 0));
		lbWin.setBounds(123, 109, 111, 25);
		lbWin.setOpaque(true);
		panel.add(lbWin);

		JLabel lbDraw = new JLabel("무 : " + Values.user_draw);
		lbDraw.setForeground(new Color(0, 0, 0));
		lbDraw.setBackground(new Color(211, 211, 211));
		lbDraw.setBounds(123, 144, 111, 25);
		lbDraw.setOpaque(true);
		panel.add(lbDraw);

		JLabel defeat = new JLabel("패 : " + Values.user_defeat);
		defeat.setForeground(new Color(0, 0, 0));
		defeat.setBackground(new Color(211, 211, 211));
		defeat.setBounds(123, 179, 111, 25);
		defeat.setOpaque(true);
		panel.add(defeat);

		
		
		int total = Values.user_win + Values.user_defeat + Values.user_draw;
		double rate = (double)((double)Values.user_win / (double)total) * 100.0;
		if(Double.isNaN(rate)) {
			rate = (double)(0.00);
		}
		String dispPattern = "0.##";
		DecimalFormat form = new DecimalFormat(dispPattern);
		String winning_rate = form.format(rate) + "%";
		JLabel lbWinRate = new JLabel("승률 : " + winning_rate);
		lbWinRate.setBackground(new Color(211, 211, 211));
		lbWinRate.setForeground(new Color(0, 0, 0));
		lbWinRate.setBounds(123, 214, 111, 25);
		lbWinRate.setOpaque(true);
		panel.add(lbWinRate);
		
		
		JButton startGame = new JButton("게임 시작");
		startGame.setBounds(180, 250, 120, 35);
		panel.add(startGame);
		
		JButton searchRank = new JButton("랭킹 확인");
		searchRank.setBounds(380, 256, 97, 23);
		panel.add(searchRank);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		setBounds(100, 100, 600, 400);
		
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Batting();
			}
		});
		
		searchRank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Ranking();
				
			}
		});
		
	}


	public static void main(String[] args) {
		new Profile();
		
	}
}
