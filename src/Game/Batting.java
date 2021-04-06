package Game;

import javax.swing.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.awt.Color;
import java.awt.Font;

public class Batting extends JFrame {

	public static void main(String[] args) {
		new Batting();
	}

	String path;

	public Batting() {

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
		
		ImageIcon panelimage = new ImageIcon(path + "image/panel.png");	// panel 이미지

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
		
		JButton button = new JButton("돌아가기");
		button.setFont(new Font("굴림", Font.PLAIN, 20));
		button.setBounds(230, 283, 130, 25);
		getContentPane().add(button);

		// 오로지 gui만을 위한 판넬
		JPanel jp9 = new JPanel();
		jp9.setBounds(0, 0, 594, 21);
		getContentPane().add(jp9);

		JPanel jp10 = new JPanel();
		jp10.setBounds(0, 330, 594, 43);
		getContentPane().add(jp10);

		// 색상 지정
		Color backColor = new Color(210, 180, 145);
		Color lineColor = new Color(252, 247, 222);
		Color buttonColor = new Color(121, 117, 117);
		Color panelColor = new Color(233, 180, 95);

		jp9.setBackground(lineColor);
		jp10.setBackground(lineColor);
		getContentPane().setBackground(backColor);

		batting_one.setBackground(panelColor);
		batting_two.setBackground(panelColor);
		batting_three.setBackground(panelColor);
		
		JLabel lblNewLabel_1 = new JLabel(panelimage);
		lblNewLabel_1.setBounds(80, 67, 430, 200);
		getContentPane().add(lblNewLabel_1);
		
		setVisible(true);

		batting_one.addActionListener(new ActionListener() {

			@Override // 350 버튼 이벤트
			public void actionPerformed(ActionEvent e) {

				batting_one();

			}
		});

		batting_two.addActionListener(new ActionListener() {

			@Override // 700 버튼 이벤트
			public void actionPerformed(ActionEvent e) {
				batting_two();

			}
		});

		batting_three.addActionListener(new ActionListener() {

			@Override // 1500 버튼 이벤트
			public void actionPerformed(ActionEvent e) {
				batting_three();

			}
		});

		button.addActionListener(new ActionListener() {

			@Override // 나가기 버튼 이벤트
			public void actionPerformed(ActionEvent e) {
				new Main();
				dispose();
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

	public void batting_one() {
		Values.gold = 350;
		setVisible(false);
		new Game_Screen1(); // 게임화면으로 넘어감
	}

	public void batting_two() {
		Values.gold = 700;
		setVisible(false);
		new Game_Screen1(); // 게임화면으로 넘어감
	}

	public void batting_three() {
		Values.gold = 1500;
		setVisible(false);
		new Game_Screen1(); // 게임화면으로 넘어감
	}
}
