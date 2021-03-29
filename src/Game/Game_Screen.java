package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Game_Screen extends JFrame {
	
	public Game_Screen() {
		
		getContentPane().setLayout(null);
		
		// 컴퓨터 남은 주사위 - comDice
		JLabel comDiceTitle = new JLabel("남은 주사위");
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.add(comDiceTitle);
		JLabel comDiceX = new JLabel("x");
		comDiceX.setBounds(5, 5, 19, 15);
		JPanel jp2 = new JPanel();
		jp2.setLayout(null);
		jp2.add(comDiceX);
		
		JPanel comDices = new JPanel(new BorderLayout());
		comDices.add(jp1, BorderLayout.NORTH);
		comDices.add(jp2, BorderLayout.CENTER);
		
		JLabel comDice = new JLabel("15");
		comDice.setBounds(12, 5, 57, 15);
		jp2.add(comDice);
		comDices.setBackground(Color.BLUE);
		
		
		
		// comStatus - comDefense, comHP
		JLabel comDefense = new JLabel("●●●●○○");
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp3.setBackground(UIManager.getColor("Button.background"));
		jp3.add(comDefense);
		JPanel jp4 = new JPanel();
		jp4.setBackground(UIManager.getColor("Button.background"));
		
		JPanel comStatus = new JPanel(new BorderLayout());
		comStatus.setBackground(Color.WHITE);
		comStatus.add(jp3, BorderLayout.NORTH);
		comStatus.add(jp4, BorderLayout.CENTER);
		jp4.setLayout(null);
		
		JLabel comMaxHP = new JLabel("HP      /10");
		comMaxHP.setBounds(22, 5, 58, 15);
		comMaxHP.setHorizontalAlignment(SwingConstants.CENTER);
		jp4.add(comMaxHP);
		
		JLabel comHP = new JLabel("10");
		comHP.setBounds(35, 5, 35, 15);
		comHP.setHorizontalAlignment(SwingConstants.CENTER);
		jp4.add(comHP);
		
		
		
		// 유저 남은 주사위 - userDice
		JLabel userDiceTitle = new JLabel("남은 주사위");
		JPanel jp5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp5.add(userDiceTitle);
		JLabel userDice = new JLabel("10");
		userDice.setBounds(75, 5, 19, 15);
		userDice.setHorizontalAlignment(SwingConstants.RIGHT);
		JPanel jp6 = new JPanel();
		jp6.setLayout(null);
		jp6.add(userDice);		
		
		JPanel userDices = new JPanel(new BorderLayout());
		userDices.add(jp5, BorderLayout.NORTH);
		userDices.add(jp6, BorderLayout.CENTER);
		
		JLabel userDiceX = new JLabel("x");
		userDiceX.setHorizontalAlignment(SwingConstants.CENTER);
		userDiceX.setBounds(68, 5, 19, 15);
		jp6.add(userDiceX);
		
		
		// userStatus - userDefense, userHP
		JLabel userDefense = new JLabel("●●○○○○");
		JPanel jp7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp7.setBackground(UIManager.getColor("Button.background"));
		jp7.add(userDefense);
		JLabel userMaxHP = new JLabel("HP      /10");
		userMaxHP.setHorizontalAlignment(SwingConstants.CENTER);
		userMaxHP.setBounds(22, 5, 58, 15);
		JPanel jp8 = new JPanel();
		jp8.setBackground(UIManager.getColor("Button.background"));
		jp8.setLayout(null);
		jp8.add(userMaxHP);
		
		JPanel userStatus = new JPanel(new BorderLayout());
		userStatus.add(jp7, BorderLayout.NORTH);
		userStatus.add(jp8, BorderLayout.CENTER);
		
		JLabel userHP = new JLabel("10");
		userHP.setHorizontalAlignment(SwingConstants.CENTER);
		userHP.setBounds(34, 5, 35, 15);
		jp8.add(userHP);
		
		
		// 던지기, 그만 버튼 - throwDice, stopGame
		JButton throwDice = new JButton("던지기");
		JButton stopGame = new JButton("그만");
		
				
		// 주사위 결과 출력 comResult1~5
		JLabel comResult1 = new JLabel("주사위1");
		comResult1.setHorizontalAlignment(SwingConstants.CENTER);
		comResult1.setBounds(30, 10, 140, 15);
		JLabel comResult2 = new JLabel("주사위2");
		comResult2.setHorizontalAlignment(SwingConstants.CENTER);
		comResult2.setBounds(30, 35, 140, 15);
		JLabel comResult3 = new JLabel("주사위3");
		comResult3.setHorizontalAlignment(SwingConstants.CENTER);
		comResult3.setBounds(30, 60, 140, 15);
		JLabel comResult4 = new JLabel("주사위4");
		comResult4.setHorizontalAlignment(SwingConstants.CENTER);
		comResult4.setBounds(30, 85, 140, 15);
		JLabel comResult5 = new JLabel("주사위5");
		comResult5.setHorizontalAlignment(SwingConstants.CENTER);
		comResult5.setBounds(30, 110, 140, 15);
		JPanel jp9 = new JPanel();
		jp9.setBackground(Color.LIGHT_GRAY);
		jp9.setLayout(null);
		jp9.add(comResult1); jp9.add(comResult2); jp9.add(comResult3);
		jp9.add(comResult4); jp9.add(comResult5);
		
		
		// 주사위 결과 출력 userResult1~5
		JLabel userResult1 = new JLabel("주사위1");
		userResult1.setHorizontalAlignment(SwingConstants.CENTER);
		userResult1.setBounds(29, 11, 135, 15);
		JLabel userResult2 = new JLabel("주사위2");
		userResult2.setHorizontalAlignment(SwingConstants.CENTER);
		userResult2.setBounds(29, 36, 135, 15);
		JLabel userResult3 = new JLabel("주사위3");
		userResult3.setHorizontalAlignment(SwingConstants.CENTER);
		userResult3.setBounds(29, 61, 135, 15);
		JLabel userResult4 = new JLabel("주사위4");
		userResult4.setHorizontalAlignment(SwingConstants.CENTER);
		userResult4.setBounds(29, 86, 135, 15);
		JLabel userResult5 = new JLabel("주사위5");
		userResult5.setHorizontalAlignment(SwingConstants.CENTER);
		userResult5.setBounds(29, 111, 135, 15);
		JPanel jp10 = new JPanel();
		jp10.setBackground(Color.WHITE);
		jp10.setLayout(null);
		jp10.add(userResult1); jp10.add(userResult2); jp10.add(userResult3);
		jp10.add(userResult4); jp10.add(userResult5);
		
			
		getContentPane().setLayout(null);
		
		getContentPane().add(throwDice);
		throwDice.setBounds(146, 280, 100, 50);
		
		getContentPane().add(stopGame);
		stopGame.setBounds(344, 280, 100, 50);
		
		getContentPane().add(comDices);
		comDices.setBounds(10, 20, 100, 50);		
		
		getContentPane().add(comStatus);
		comStatus.setBounds(245, 20, 100, 50);
		
		getContentPane().add(userDices);
		userDices.setBounds(480, 280, 100, 50);
		
		getContentPane().add(userStatus);
		userStatus.setBounds(245, 280, 100, 50);
		
		getContentPane().add(jp9);
		jp9.setBounds(50, 102, 193, 147);
		
		getContentPane().add(jp10);
		jp10.setBounds(344, 102, 193, 147);
		
		
		setBounds(100, 100, 600, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setVisible(true);
	
		
		// comDice userDice / comHP userHP / comDefense userDefense
		
		// 이벤트 처리 - throwDice, stopGame
		
		throwDice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		stopGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		
		
	}

	public static void main(String[] args) {
		new Game_Screen();

	}
	
	public void Result() {
		new Result();
	}
}
