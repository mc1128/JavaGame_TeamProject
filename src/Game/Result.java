package Game;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.JButton;

public class Result extends JFrame {

	public JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

			new Result();

	}

	static JLabel Result_Label;
	static JLabel Gold_Label;
	String resultText;	// 결과값 메시지
	int goldResult;		// 골드값 메시지
	int result;		// 결과(승리/패배/무승부) 
	
	
	public Result() {
		
		setTitle("결과창");

		setVisible(true);

		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Result_Label = new JLabel("결과값");
		Result_Label.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		Result_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Result_Label.setBounds(230, 21, 129, 45);
		contentPane.add(Result_Label);
		
		Gold_Label = new JLabel("얻은 골드 값");
		Gold_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Gold_Label.setBounds(237, 76, 114, 21);
		contentPane.add(Gold_Label);
		
		JLabel Gold_Image = new JLabel("골드 이미지");
		Gold_Image.setHorizontalAlignment(SwingConstants.CENTER);
		Gold_Image.setBounds(223, 120, 150, 150);
		contentPane.add(Gold_Image);
		
		JButton Replay_Button = new JButton("다시 플레이");
		Replay_Button.setBounds(100, 300, 150, 35);
		contentPane.add(Replay_Button);
		
		JButton BackMain_Button = new JButton("나가기");
		BackMain_Button.setBounds(335, 300, 150, 35);
		contentPane.add(BackMain_Button);
		
		Replay_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Batting();
				
			}
		});
		
		// 결과 처리
		result();
		
		// 결과값 출력
		printResult();
		
		
		// 얻은 골드 값 출력
		printGold();
		
		
		// 이벤트 처리
		// 다시 플레이 버튼
		Replay_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
						
				new Game_Screen1();
			}
		});
		
		// 나가기 버튼
		BackMain_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Main();
			}
		});
				
	}// 생성자 end
	
	void result() {
		
		if(Values.userHPData > Values.comHPDate) {
			result = 0;
			resultText = "이겼습니다!";
		}else if(Values.userHPData > Values.comHPDate) {
			result = 1;
			resultText = "졌습니다!";
		}else {
			result = 2;
			resultText = "비겼습니다!";
		}

	}
	

	public void Batting() {
		setVisible(false);
		new Batting(); 
	}
	public void Main() {
		setVisible(false);
		new Main(); 
	}
	
	

	void printResult() {	// 결과값 출력 메서드
		if(Values.userHPData > Values.comHPDate) {
			resultText = "이겼습니다!";
		}else if(Values.userHPData > Values.comHPDate) {
			resultText = "졌습니다!";
		}else {
			resultText = "비겼습니다!";
		}
		
		Result_Label.setText(resultText);
		
	}
	
	void printGold() {	// 골드값 출력 메서드
		
		goldMath();
		Gold_Label.setText(String.valueOf(Values.gold));
	}
	
	void goldMath() {
		switch(result) {
			case 0 :
				break;
			case 1 :
				break;
			case 2 :
				break;
		}
	}
	
}// 클래스 end
