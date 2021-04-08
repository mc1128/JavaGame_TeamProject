package Game;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Loading extends JFrame {

	private JPanel loading_panel;
	static JProgressBar progressBar;
	static JLabel Game_Tip;
	// int num = 0;
	int p = 0;
	String path;

	public static void main(String[] args) {
		new Loading();
	}

	public Loading() {
		
		try { // path 기본 설정
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;

		String back_path = path + "image/loadingback.png";
		String tip_path = path + "image/white.png";		

		loading_panel = new JPanel();
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(45, 280, 500, 50);
		getContentPane().add(progressBar);

		Game_Tip = new JLabel("게입 팁 1");
		Game_Tip.setHorizontalAlignment(SwingConstants.CENTER);
		Game_Tip.setBounds(45, 27, 500, 227);
		getContentPane().add(Game_Tip);

		progress_start();
		
		JLabel tip_back = new JLabel(new ImageIcon(tip_path));
		tip_back.setBounds(45, 27, 500, 227);
		getContentPane().add(tip_back);

		JLabel back = new JLabel(new ImageIcon(back_path));
		back.setLocation(0, 0);
		back.setSize(594, 371);
		getContentPane().add(back);		

		setVisible(true); // GUI 최하단에 두기

	}

	/*
	 * public void progress_start() { int i; try { for (i = 0; i <= 100; i++) {
	 * progressBar.setValue(i); Thread.sleep(37);// 밀리세컨드 단위로 // 지연시간을 설정. if(i ==
	 * 100) { Batting(); } } } catch (InterruptedException ie) {
	 * ie.printStackTrace(); } }
	 */

	public void progress_start() {

		Timer timer_progress = new Timer();
		TimerTask task_progress = new TimerTask() {

			void progress_value() {
				if (p == 100) {
					timer_progress.cancel();
					Batting();
				} else if (p == 33) {
					Game_Tip.setText("그만하기도 하나의 전략입니다.");
					p++;
				} else if (p == 66) {
					Game_Tip.setText("모든 값은 랜덤으로 진행됩니다.");
					p++;
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
		(timer_progress).scheduleAtFixedRate(task_progress, 0, 40);
	}

	public void Batting() {
		setVisible(false);
		new Batting();
	}
}
