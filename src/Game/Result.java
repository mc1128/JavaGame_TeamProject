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

	/**
	 * Create the frame.
	 */
	public Result() {
		
		setTitle("결과창");
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Result_Label = new JLabel("결과값");
		Result_Label.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		Result_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Result_Label.setBounds(230, 21, 129, 45);
		contentPane.add(Result_Label);
		
		JLabel Gold_Label = new JLabel("얻은 골드 값");
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
		
		
	}
}
