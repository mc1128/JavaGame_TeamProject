package Game;

import java.util.Timer;
import java.util.TimerTask;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import javax.swing.*;

public class Loading extends JFrame {

	private JPanel loading_panel;
	static JProgressBar progressBar;
	static JLabel Game_Tip;
	static JLabel tip_back;
	// int num = 0;
	int p = 0;

	String path;
	
	public static void main(String[] args) {
		new Loading();
	}

	public Loading() {

		setTitle("로딩창");
		
		try { // path 기본 설정
			path = URLDecoder.decode(Game_Screen.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		};
		
		setIconImage(Main.img);
		
		String back_path = path + "image/loadingback.png";
		String tip_path = path + "image/white.png";	
		String tip1_path = path + "image/tip1.png";	
		String tip2_path = path + "image/white.png";	
		String tip3_path = path + "image/white.png";	
		
		System.out.println("아이디값 :  "+ Values.user_id);
		System.out.println("골드값 : "+ Values.gold_save);
		loading_panel = new JPanel();
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(45, 280, 500, 50);
		getContentPane().add(progressBar);
		
		tip_back = new JLabel(new ImageIcon(tip1_path));
		tip_back.setBounds(45, 27, 500, 227);
		getContentPane().add(tip_back);
		
//		Game_Tip = new JLabel("게입 팁 1");
//		Game_Tip.setHorizontalAlignment(SwingConstants.CENTER);
//		Game_Tip.setBounds(45, 27, 500, 227);
//		getContentPane().add(Game_Tip);
		
		JLabel back = new JLabel(new ImageIcon(back_path));
		back.setLocation(0, 0);
		back.setSize(594, 371);
		getContentPane().add(back);		

		
		progress_start();
		
		setVisible(true);
	}

	/*
	 * public void progress_start() { int i; try { for (i = 0; i <= 100; i++) {
	 * progressBar.setValue(i); Thread.sleep(37);// 밀리세컨드 단위로 // 지연시간을 설정. if(i ==
	 * 100) { Batting(); } } } catch (InterruptedException ie) {
	 * ie.printStackTrace(); } }
	 */


	public void progress_start() {
		
		try { // path 기본 설정
			path = URLDecoder.decode(Game_Screen.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		};
		
		ImageIcon tip1 = new ImageIcon(Main.path + "image/tip1.png");
		ImageIcon tip2 = new ImageIcon(Main.path + "image/tip2.png");
		ImageIcon tip3 = new ImageIcon(Main.path + "image/tip3.png");

		Timer timer_progress = new Timer();
		TimerTask task_progress = new TimerTask() {

			void progress_value() {
				if (p == 100) {
					timer_progress.cancel();
					Batting();
				} else if (p == 30) {
//					Game_Tip.setText("그만하기도 하나의 전략입니다.");
					tip_back.setIcon(tip2);
					p++;
//				} else if (p == 66) {
//					Game_Tip.setText("모든 값은 랜덤으로 진행됩니다.");
//					p++;
//					tip_back.setIcon(tip2);
				} else {
					p++;
				}
			}

			@Override
			public void run() {
				progress_value();
				progressBar.setValue(p);

			}
		};
		(timer_progress).scheduleAtFixedRate(task_progress, 0, 55);
	}

	public void Batting() {
		setVisible(false);
		new Profile();
	}
}
