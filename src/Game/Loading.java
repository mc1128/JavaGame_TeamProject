package Game;

import java.util.Timer;
import java.util.TimerTask;

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

	public static void main(String[] args) {
		new Loading();
	}

	public Loading() {
		System.out.println("아이디값 :  "+ Values.user_id);
		System.out.println("골드값 : "+ Values.gold_save);
		setVisible(true);
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

		test();
		progress_start();
	}

	/*
	 * public void progress_start() { int i; try { for (i = 0; i <= 100; i++) {
	 * progressBar.setValue(i); Thread.sleep(37);// 밀리세컨드 단위로 // 지연시간을 설정. if(i ==
	 * 100) { Batting(); } } } catch (InterruptedException ie) {
	 * ie.printStackTrace(); } }
	 */

	private void test() {
		

		try {
			String test = "SELECT user_id FROM PROFILE";
			
			DBConnection.pstm = DBConnection.dbConn.prepareStatement(test);
			DBConnection.rs = DBConnection.pstm.executeQuery();

			while (DBConnection.rs.next()) {
				String user_id = DBConnection.rs.getString(1);
				System.out.println(user_id);
			}
			DBConnection.rs.close(); 
			DBConnection.pstm.close();
		}
		catch(Exception e) {
				e.printStackTrace();
			}
	}

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
