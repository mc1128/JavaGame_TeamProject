package Game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

public class Batting extends JFrame{
	
	public static void main(String[] args) {
		new Batting();
	}
	
	
	
	public Batting() {
				
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("베팅을 하세요");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(235, 75, 130, 50);
		getContentPane().add(lblNewLabel);
		
		JButton batting_one = new JButton("350");
		batting_one.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_one.setBounds(101, 176, 100, 50);
		getContentPane().add(batting_one);
		
		JButton batting_two = new JButton("700");
		batting_two.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_two.setBounds(251, 176, 100, 50);
		getContentPane().add(batting_two);
		
		JButton batting_three = new JButton("1500");
		batting_three.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_three.setBounds(401, 176, 100, 50);
		getContentPane().add(batting_three);
		
		setBounds(100, 100, 600, 400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("돌아가기");
		button.setFont(new Font("굴림", Font.PLAIN, 20));
		button.setBounds(235, 305, 130, 25);
		getContentPane().add(button);
		
		// 색상 지정
		Color backColor = new Color(210, 180, 145);
		Color lineColor = new Color(252, 247, 222);
		Color buttonColor = new Color(121, 117, 117);
		
		JPanel jp9 = new JPanel();
		jp9.setBounds(0, 0, 594, 21);
		getContentPane().add(jp9);
		
		JPanel jp10 = new JPanel();
		jp10.setBounds(0, 330, 594, 43);
		getContentPane().add(jp10);
	
		jp9.setBackground(lineColor);
		jp10.setBackground(lineColor);
		getContentPane().setBackground(backColor);
		
		setVisible(true);
	
	
	batting_one.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			batting_one();
			
		}
	});
	batting_two.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			batting_two();
			
		}
	});
	
	batting_three.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			batting_three();
			
		}
	});
	
	button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new Main();		
			dispose();
		}
	});
	
}
	
	
	public void batting_one() {
		Values.gold=350;
		setVisible(false);
		new Game_Screen1(); //게임화면으로 넘어감
	}
	public void batting_two() {
		Values.gold=700;
		setVisible(false);
		new Game_Screen1(); //게임화면으로 넘어감
	}
	public void batting_three() {
		Values.gold=1500;
		setVisible(false);
		new Game_Screen1(); //게임화면으로 넘어감
	}
}
