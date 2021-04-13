package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Batting extends JFrame {

	String path;

	public static void main(String[] args) {

		new Batting();
	}

	public Batting() {

		setTitle("배팅 화면");

		getContentPane().setLayout(null);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try { // path 기본 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		ImageIcon bat1 = new ImageIcon(path + "image/350.png"); // 350 기본 이미지
		ImageIcon bat2 = new ImageIcon(path + "image/350b.png"); // 350 흑백 이미지

		ImageIcon bat3 = new ImageIcon(path + "image/700.png"); // 700 기본 이미지
		ImageIcon bat4 = new ImageIcon(path + "image/700b.png"); // 700 흑백 이미지

		ImageIcon bat5 = new ImageIcon(path + "image/1500.png"); // 1500 기본 이미지
		ImageIcon bat6 = new ImageIcon(path + "image/1500b.png"); // 1500 흑백 이미지

		ImageIcon panelimage = new ImageIcon(path + "image/panel.png"); // panel 이미지

		JLabel lblNewLabel_2 = new JLabel("보유 골드" + Values.gold_save);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(396, 79, 114, 31);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("베팅을 하세요");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(235, 67, 130, 50);
		getContentPane().add(lblNewLabel);

		JButton batting_one = new JButton(bat2); // 350 골드 배팅
		batting_one.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_one.setBounds(107, 140, 100, 100);
		getContentPane().add(batting_one);

		JButton batting_two = new JButton(bat4); // 700 골드 배팅
		batting_two.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_two.setBounds(247, 140, 100, 100);
		getContentPane().add(batting_two);

		JButton batting_three = new JButton(bat6); // 1500 골드 배팅
		batting_three.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_three.setBounds(387, 140, 100, 100);
		getContentPane().add(batting_three);

		JButton back_button = new JButton("돌아가기");
		back_button.setFont(new Font("굴림", Font.PLAIN, 20));
		back_button.setBounds(230, 283, 130, 25);
		getContentPane().add(back_button);

		// 색상 지정
		Color backColor = new Color(210, 180, 145);
		Color lineColor = new Color(252, 247, 222);
		Color buttonColor = new Color(121, 117, 117);
		Color panelColor = new Color(233, 180, 95);

		getContentPane().setBackground(backColor);

		batting_one.setBackground(panelColor);
		batting_two.setBackground(panelColor);
		batting_three.setBackground(panelColor);

		JLabel lblNewLabel_1 = new JLabel(panelimage);
		lblNewLabel_1.setBounds(80, 67, 430, 200);
		getContentPane().add(lblNewLabel_1);

		String back_path = path + "image/battingback.png";

		JLabel back = new JLabel(new ImageIcon(back_path));
		back.setBackground(Color.LIGHT_GRAY);
		back.setLocation(0, 0);
		back.setSize(594, 371);
		getContentPane().add(back);

		setVisible(true);

		batting_one.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Values.gold_save >= 350) {
					batting_gold(350);
				} else {
					JOptionPane.showMessageDialog(null, "gold가 부족합니다.");
				}

			}
		});

		batting_two.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Values.gold_save >= 700) {
					batting_gold(700);
				} else {
					JOptionPane.showMessageDialog(null, "gold가 부족합니다.");
				}

			}
		});

		batting_three.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Values.gold_save >= 1500) {
					batting_gold(1500);
				} else {
					JOptionPane.showMessageDialog(null, "gold가 부족합니다.");
				}

			}
		});

		back_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Profile();
			}
		});

		// hover 효과 350
		batting_one.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				batting_one.setIcon(bat1);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				batting_one.setIcon(bat2);
			}
		});
		// hover 효과 700
		batting_two.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				batting_two.setIcon(bat3);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				batting_two.setIcon(bat4);
			}
		});
		// hover 효과 1500
		batting_three.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				batting_three.setIcon(bat5);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				batting_three.setIcon(bat6);
			}
		});

	}

	void batting_gold(int a) {
		Values.gold = a;
		Values.gold_save -= a;
		setVisible(false);
		new Test_Game_Screen1(); // 게임화면으로 넘어감
	}
}
