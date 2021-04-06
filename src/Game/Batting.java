package Game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

public class Batting extends JFrame{
	
	
	
	public Batting() {
				
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("배팅을 하세요");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(235, 75, 130, 50);
		getContentPane().add(lblNewLabel);
		
		JButton batting_one = new JButton("350");
		batting_one.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_one.setBounds(100, 200, 100, 50);
		getContentPane().add(batting_one);
		
		JButton batting_two = new JButton("700");
		batting_two.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_two.setBounds(250, 200, 100, 50);
		getContentPane().add(batting_two);
		
		JButton batting_three = new JButton("1500");
		batting_three.setFont(new Font("굴림", Font.PLAIN, 15));
		batting_three.setBounds(400, 200, 100, 50);
		getContentPane().add(batting_three);
		
		setBounds(200, 200, 600, 400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		JButton button = new JButton("돌아가기");
		button.setFont(new Font("굴림", Font.PLAIN, 20));
		button.setBounds(235, 320, 130, 25);
		getContentPane().add(button);
	
	
		
		
		
		
		
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
	
	batting_one.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseEntered(java.awt.event.MouseEvent evt) {
			batting_one.setBackground(Color.GREEN);
		}
		
		public void mouseExited(java.awt.event.MouseEvent evt) {
			batting_one.setBackground(UIManager.getColor(""));
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
