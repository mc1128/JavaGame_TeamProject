package Game;

// 수정 15:36

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Test_Game_Screen1 extends JFrame {

	int comHPData = 10;
	int userHPData = 10;
	int comDefenseData = 0;
	int userDefenseData = 0;
	int comDiceData = 15;
	int userDiceData = 15;

	int[] diceValue;

	String[] comDiceName = new String[5];
	String[] userDiceName = new String[5];

	String userDefenseG = "○○○○○○";
	String comDefenseG = "○○○○○○";

	static JLabel comDice; // 컴퓨터 주사위 변수
	static JLabel comDefense; // 컴퓨터 디펜스 변수
	static JLabel comHP; // 컴퓨터 HP 변수
	static JLabel userDice; // 유저 주사위 변수
	static JLabel userDefense; // 유저 디펜스 변수
	static JLabel userHP; // 유저 HP 변수
	static JLabel comResult1; // 컴퓨터 결과 1
	static JLabel comResult2; // 컴퓨터 결과 2
	static JLabel comResult3; // 컴퓨터 결과 3
	static JLabel comResult4; // 컴퓨터 결과 4
	static JLabel comResult5; // 컴퓨터 결과 5
	static JLabel userResult1; // 유저 결과 1
	static JLabel userResult2; // 유저 결과 2
	static JLabel userResult3; // 유저 결과 3
	static JLabel userResult4; // 유저 결과 4
	static JLabel userResult5; // 유저 결과 5
	static JButton throwDice;

	static JLabel userDiceGif; // 유저 주사위 이미지
	static JLabel comDiceGif; // 컴퓨터 주사위 이미지

	String path;
	
	TimerTask task_delay1;
	Timer timer_delay1;
	
	public Test_Game_Screen1() {
		System.out.println(Game_Screen1.class.getResource("").getPath());

		try {
			path = URLDecoder.decode(Game_Screen1.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;
		String com_path = path + "image/sample04.gif";
		String user_path = path + "image/sample03.gif";

		setTitle("게임 화면");

		// 기본 화면 틀
		getContentPane().setLayout(null);
		setBounds(100, 100, 600, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// 컴퓨터 남은 주사위 - comDiceTitle / comDiceX / comDice
		// 컴퓨터 남은 주사위 값 출력 컴포넌트 : comDice
		JLabel comDiceTitle = new JLabel("남은 주사위");
		JLabel comDiceX = new JLabel("x");
		comDice = new JLabel("15");

		comDiceX.setBounds(5, 5, 19, 15);
		comDice.setBounds(12, 5, 57, 15);
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp2 = new JPanel();
		jp2.setLayout(null);

		jp1.add(comDiceTitle);
		jp2.add(comDiceX);
		jp2.add(comDice);

		JPanel comDices = new JPanel(new BorderLayout());
		comDices.add(jp1, BorderLayout.NORTH);
		comDices.add(jp2, BorderLayout.CENTER);

		getContentPane().add(comDices);
		comDices.setBounds(10, 20, 100, 50);

		// comStatus - comDefense, comHP
		// 컴퓨터 방어 값 출력 컴포넌트 : comDefense
		// 컴퓨터 체력 값 출력 컴포넌트 : comHP
		comDefense = new JLabel(comDefenseG);
		comHP = new JLabel("10");
		JLabel comMaxHP = new JLabel("HP      /10");

		comHP.setBounds(35, 5, 35, 15);
		comHP.setHorizontalAlignment(SwingConstants.CENTER);
		comMaxHP.setBounds(22, 5, 58, 15);
		comMaxHP.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel jp4 = new JPanel();
		jp4.setLayout(null);

		jp3.add(comDefense);
		jp4.add(comHP);
		jp4.add(comMaxHP);

		JPanel comStatus = new JPanel(new BorderLayout());
		comStatus.add(jp3, BorderLayout.NORTH);
		comStatus.add(jp4, BorderLayout.CENTER);

		getContentPane().add(comStatus);
		comStatus.setBounds(245, 20, 100, 50);

		// 유저 남은 주사위 - userDiceTitle / userDiceX / userDice
		// 유저 남은 주사위 값 출력 컴포넌트 : userDice
		JLabel userDiceTitle = new JLabel("남은 주사위");
		JLabel userDiceX = new JLabel("x");
		userDice = new JLabel("15");

		userDice.setBounds(75, 5, 19, 15);
		userDiceX.setBounds(68, 5, 19, 15);
		userDice.setHorizontalAlignment(SwingConstants.RIGHT);
		userDiceX.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel jp5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp6 = new JPanel();
		jp6.setLayout(null);
		jp5.add(userDiceTitle);
		jp6.add(userDice);
		jp6.add(userDiceX);

		JPanel userDices = new JPanel(new BorderLayout());
		userDices.add(jp5, BorderLayout.NORTH);
		userDices.add(jp6, BorderLayout.CENTER);

		getContentPane().add(userDices);
		userDices.setBounds(480, 280, 100, 50);

		// userStatus - userDefense, userHP
		// 유저 방어 값 출력 컴포넌트 : userDefense
		// 유저 체력 값 출력 컴포넌트 : userHP
		userDefense = new JLabel(userDefenseG);
		userHP = new JLabel("10");
		JLabel userMaxHP = new JLabel("HP      /10");

		userHP.setBounds(34, 5, 35, 15);
		userMaxHP.setBounds(22, 5, 58, 15);
		userHP.setHorizontalAlignment(SwingConstants.CENTER);
		userMaxHP.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel jp8 = new JPanel();
		jp8.setLayout(null);
		JPanel jp7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp7.add(userDefense);
		jp8.add(userMaxHP);
		jp8.add(userHP);

		JPanel userStatus = new JPanel(new BorderLayout());
		userStatus.add(jp7, BorderLayout.NORTH);
		userStatus.add(jp8, BorderLayout.CENTER);

		getContentPane().add(userStatus);
		userStatus.setBounds(245, 280, 100, 50);

		// 던지기 버튼 : throwDice / 그만 버튼 : stopGame
		throwDice = new JButton("던지기");
		JButton stopGame = new JButton("그만");

		getContentPane().add(throwDice);
		getContentPane().add(stopGame);
		throwDice.setBounds(146, 280, 100, 50);
		stopGame.setBounds(344, 280, 100, 50);

		// 주사위 결과 출력 comResult1~5
		comResult1 = new JLabel("주사위1");
		comResult2 = new JLabel("주사위2");
		comResult3 = new JLabel("주사위3");
		comResult4 = new JLabel("주사위4");
		comResult5 = new JLabel("주사위5");

		// 주사위 결과 출력 userResult1~5
		userResult1 = new JLabel("주사위1");
		userResult2 = new JLabel("주사위2");
		userResult3 = new JLabel("주사위3");
		userResult4 = new JLabel("주사위4");
		userResult5 = new JLabel("주사위5");

		comResult1.setBounds(30, 10, 140, 15);
		comResult2.setBounds(30, 35, 140, 15);
		comResult3.setBounds(30, 60, 140, 15);
		comResult4.setBounds(30, 85, 140, 15);
		comResult5.setBounds(30, 110, 140, 15);

		comResult1.setHorizontalAlignment(SwingConstants.CENTER);
		comResult2.setHorizontalAlignment(SwingConstants.CENTER);
		comResult3.setHorizontalAlignment(SwingConstants.CENTER);
		comResult4.setHorizontalAlignment(SwingConstants.CENTER);
		comResult5.setHorizontalAlignment(SwingConstants.CENTER);

		userResult1.setBounds(29, 11, 135, 15);
		userResult2.setBounds(29, 36, 135, 15);
		userResult3.setBounds(29, 61, 135, 15);
		userResult4.setBounds(29, 86, 135, 15);
		userResult5.setBounds(29, 111, 135, 15);

		userResult1.setHorizontalAlignment(SwingConstants.CENTER);
		userResult2.setHorizontalAlignment(SwingConstants.CENTER);
		userResult3.setHorizontalAlignment(SwingConstants.CENTER);
		userResult4.setHorizontalAlignment(SwingConstants.CENTER);
		userResult5.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel comResult = new JPanel();
		comResult.setLayout(null);
		comResult.setBackground(Color.LIGHT_GRAY);
		comResult.add(comResult1);
		comResult.add(comResult2);
		comResult.add(comResult3);
		comResult.add(comResult4);
		comResult.add(comResult5);

		ImageIcon ii2 = new ImageIcon(com_path);
		System.out.println(ii2);
		comDiceGif = new JLabel(ii2);
		comDiceGif.setBounds(0, 0, 193, 147);
		comResult.add(comDiceGif);
		comDiceGif.setVisible(false);

		// 유저 주사위 이미지
		JPanel userResult = new JPanel();
		userResult.setLayout(null);
		userResult.setBackground(new Color(140, 117, 90));

		// 유저 주사위 이미지
		System.out.println(path);
		ImageIcon ii = new ImageIcon(user_path);

		userDiceGif = new JLabel(ii);
		userDiceGif.setBounds(0, 0, 193, 147);
		userResult.add(userDiceGif);
		userDiceGif.setVisible(false);

		userResult.setLayout(null);
		userResult.setBackground(Color.WHITE);
		userResult.add(userResult1);
		userResult.add(userResult2);
		userResult.add(userResult3);
		userResult.add(userResult4);
		userResult.add(userResult5);

		getContentPane().add(comResult);
		getContentPane().add(userResult);
		comResult.setBounds(50, 102, 193, 147);
		userResult.setBounds(344, 102, 193, 147);

		// comDice userDice / comHP userHP / comDefense userDefense
		// 이벤트 처리 - throwDice, stopGame
		clearResult();
		int turn;
		turn = (int) (Math.random() * 2);

		if (turn == 0) {
			throwDice.setEnabled(false);
			JOptionPane.showMessageDialog(null, "후공입니다.");
			DiceImage(comDiceGif);
			Timer timer_delay = new Timer();
			TimerTask task_delay = new TimerTask() {

				@Override
				public void run() {
					comRoll();
					throwDice.setEnabled(true);
				}
			};
			(timer_delay).schedule(task_delay, 2500);
		} else if (turn == 1) {
			JOptionPane.showMessageDialog(null, "선공입니다.");
		}

		throwDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				throwDice.setEnabled(false);

				Timer timer_delay1 = new Timer();
				TimerTask task_delay1 = new TimerTask() {

					@Override
					public void run() {
						DiceImage(userDiceGif);
						userRoll();
						DiceImage(comDiceGif);

					}
				};
				(timer_delay1).schedule(task_delay1, 2000);

				// 주사위 굴려서 값 저장

				Timer timer_delay = new Timer();
				TimerTask task_delay = new TimerTask() {

					@Override
					public void run() {
						comRoll();

					}
				};
				(timer_delay).schedule(task_delay, 11000);

				/*
				 * if(userDiceData<=0) { throwDice.setEnabled(false);
				 * 
				 * while (comDiceData<=0) {
				 * 
				 * Timer timer_delay2 = new Timer(); TimerTask task_delay2 = new TimerTask() {
				 * 
				 * @Override public void run() { comRoll();
				 * 
				 * } }; (timer_delay1).schedule(task_delay1, 2500);
				 * 
				 * 
				 * } }
				 */

			}
		});

		stopGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				userDiceData = 0;
				changeText();
				// 컴퓨터의 남은 주사위가 0이 될 때까지 컴퓨터만 롤
//				new Result(userHPData, comHPData);

				// new Result();
				if (userDiceData <= 0) {
					throwDice.setEnabled(false);

					

						timer_delay1 = new Timer();
						task_delay1 = new TimerTask() {

							@Override
							public void run() {
								comRoll();

							}
						};
						(timer_delay1).schedule(task_delay1, 2500, 500);

					
				}

			}
		});

	}

	// Text값을 바꾸는 메서드.
	void changeText() {
		// 1. 유저 주사위 결과 값 출력
		userResult1.setText(userDiceName[0]);
		userResult2.setText(userDiceName[1]);
		userResult3.setText(userDiceName[2]);
		userResult4.setText(userDiceName[3]);
		userResult5.setText(userDiceName[4]);

		// 2. 유저 주사위 값, status(HP, 방어) 출력
		userDice.setText(String.valueOf(userDiceData));
		userHP.setText(String.valueOf(userHPData));
		userDefense.setText(String.valueOf(userDefenseData));

		// 3. 유저 디펜스 그래픽
		userDefenseG = "";

		for (int i = 0; i < userDefenseData; i++) {
			userDefenseG += "●";
		}
		for (int i = 0; i < 6 - userDefenseData; i++) {
			userDefenseG += "○";
		}

		userDefense.setText(userDefenseG);

		// 3. 컴퓨터 주사위 결과 값 출력
		comResult1.setText(comDiceName[0]);
		comResult2.setText(comDiceName[1]);
		comResult3.setText(comDiceName[2]);
		comResult4.setText(comDiceName[3]);
		comResult5.setText(comDiceName[4]);

		// 4. 컴퓨터 주사위 값, status(HP, 방어) 출력
		comDice.setText(String.valueOf(comDiceData));
		comHP.setText(String.valueOf(comHPData));
		comDefense.setText(String.valueOf(comDefenseData));

		comDefenseG = "";

		for (int i = 0; i < comDefenseData; i++) {
			comDefenseG += "●";
		}
		for (int i = 0; i < 6 - comDefenseData; i++) {
			comDefenseG += "○";
		}

		comDefense.setText(comDefenseG);

	}

	// 유저,컴퓨터의 HP=0 이면 게임 종료
	void continueGame() {
		if (userHPData <= 0 || comHPData <= 0 || (userDiceData <= 0 && comDiceData <= 0)) {
			JOptionPane.showMessageDialog(null, "게임이 종료되었습니다!");
			dispose();
			timer_delay1.cancel();
			new Result();

//			new Result();
		} else {
			return;
		}
	}

	void clearResult() {
		for (int i = 0; i < comDiceName.length; i++) {
			comDiceName[i] = "";
			userDiceName[i] = "";
		}
	}

//		printResult();

//		time();	

//		printResult();

	void printResult() {

	}

	void changeColorR(JLabel label) { // -되는 값을 빨간색으로 변경

		// 1초 후 -되는 값을 빨간색으로 변경
		Timer timer_delay1 = new Timer();
		TimerTask task_delay1 = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.red);
			}
		};
		(timer_delay1).schedule(task_delay1, 1000);

		// 1.3초 후 검정색으로 변경
		Timer timer_delay2 = new Timer();

		TimerTask task_delay2 = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.black);
			}
		};
		(timer_delay2).schedule(task_delay2, 1300);
	}

	void changeColorB(JLabel label) { // +되는 값을 파란색으로 변경

		// 1초 후 +되는 값을 파란색으로 변경
		Timer timer_delay1 = new Timer();
		TimerTask task_delay1 = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.blue);
			}
		};
		(timer_delay1).schedule(task_delay1, 1000);

		// 1.3초 후 -되는 값을 검정색으로 변경
		Timer timer_delay2 = new Timer();
		TimerTask task_delay2 = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.black);
			}
		};
		(timer_delay2).schedule(task_delay2, 1300);
	}

	void userRoll() { // 유저 주사위 굴리기
		
		int DiceData = 0;
		if (userDiceData >= 5) {
			DiceData = 5;
		} else if (userDiceData < 5) {
			DiceData = userDiceData;
		}
		diceValue = new int[DiceData];

		userDiceData -= DiceData; // 주사위 갯수 판별 후 바로 차감

		for (int i = 0; i < diceValue.length; i++) {
			diceValue[i] = (int) (Math.random() * 6);
		}

		// 랜덤값 오름차순 정렬
		Arrays.sort(diceValue);

		for (int i = 0; i < diceValue.length; i++) {

			switch (diceValue[i]) {
			case 0:// 주사위
			case 1:// 주사위
				userDiceName[i] = "주사위 추가";
				break;
			case 2: // 디펜스
				userDiceName[i] = "디펜스";
				break;
			case 3: // 데스
				userDiceName[i] = "데스";
				break;
			case 4:// 어택
			case 5:// 어택
				userDiceName[i] = "어택";
				break;
			}// 스위치 end
			changeText();

		} // for문 end
		
		for (int i = 0; i < diceValue.length; i++) {

			userDiceName[i] = "";

			switch (diceValue[i]) {
			case 0:// 주사위
			case 1:// 주사위
				userDiceData++;
				changeColorB(userDice);
				break;

			case 2: // 디펜스
				if (userDefenseData < 6) {
					changeColorB(userDefense);
					userDefenseData++;
				}
				break;
				
			case 3: // 데스
				if (userDefenseData > 0) {
					changeColorR(userDefense);
					userDefenseData--;
				} else {
					changeColorR(userHP);
					userHPData--;
				}
				break;
				
			case 4:// 어택
			case 5:// 어택
				if (comDefenseData > 0) {
					changeColorR(comDefense);
					comDefenseData--;
				} else {
					changeColorR(comHP);
					comHPData--;
				}
				break;
				
			} // 스위치 end

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			changeText();
			continueGame();
		} // for문 end

		if (comDiceData == 0) {
			throwDice.setEnabled(true);
		}

		changeColor1();

		if (diceValue.length < 5) {
			for (int i = 4; i >= diceValue.length; i--) {
				userDiceName[i] = "";
			}
		}
	}

	// Text 색상을 변경하는 메서드
	void changeColor1() {

		userResult1.setForeground(Color.red);
		userResult2.setForeground(Color.red);
		userResult3.setForeground(Color.red);
		userResult4.setForeground(Color.red);
		userResult5.setForeground(Color.red);

		Timer timer_delay = new Timer();
		TimerTask task_delay = new TimerTask() {

			@Override
			public void run() {
				userResult1.setForeground(Color.black);
				userResult2.setForeground(Color.black);
				userResult3.setForeground(Color.black);
				userResult4.setForeground(Color.black);
				userResult5.setForeground(Color.black);
			}
		};
		timer_delay.schedule(task_delay, 500);

	}

	// Text 색상을 변경하는 메서드
	void changeColor2() {

		comResult1.setForeground(Color.red);
		comResult2.setForeground(Color.red);
		comResult3.setForeground(Color.red);
		comResult4.setForeground(Color.red);
		comResult5.setForeground(Color.red);

		Timer timer_delay = new Timer();
		TimerTask task_delay = new TimerTask() {

			@Override
			public void run() {
				comResult1.setForeground(Color.black);
				comResult2.setForeground(Color.black);
				comResult3.setForeground(Color.black);
				comResult4.setForeground(Color.black);
				comResult5.setForeground(Color.black);

				System.out.println("유저결과1 색상변경");
			}
		};
		timer_delay.schedule(task_delay, 500);

	}

	void comRoll() { // 컴퓨터 주사위 굴리기

		int DiceData = 0;
		if (comDiceData >= 5) {
			DiceData = 5;
		} else if (comDiceData < 5) {
			DiceData = comDiceData;
		}

		diceValue = new int[DiceData];

		comDiceData -= DiceData;

		System.out.println("실행확인");

		for (int i = 0; i < diceValue.length; i++) {
			diceValue[i] = (int) (Math.random() * 6);
		} // 랜덤값 오름차순 정렬
		Arrays.sort(diceValue);

		for (int i = 0; i < diceValue.length; i++) {
			switch (diceValue[i]) {
			case 0: // 주사위
			case 1:
				comDiceName[i] = "주사위 추가";
				break;
			case 2: // 디펜스
				comDiceName[i] = "디펜스";
				break;
			case 3: // 데스
				comDiceName[i] = "데스";
				break;
			case 4:
			case 5:// 어택
				comDiceName[i] = "어택";
				break;
			}// 스위치문 end
			changeText();

		} // for문 end

		for (int i = 0; i < diceValue.length; i++) {
			comDiceName[i] = "";
			switch (diceValue[i]) {
			case 0: // 주사위
			case 1:
				changeColorB(comDice);
				comDiceData++;
				break;
			case 2: // 디펜스
				if (comDefenseData < 6) {
					changeColorB(comDefense);
					comDefenseData++;
				}
				break;
			case 3: // 데스
				if (comDefenseData > 0) {
					comDefenseData--;
					changeColorR(comDefense);
				} else {
					changeColorR(comHP);
					comHPData--;
				}
				break;
			case 4:
			case 5:// 어택
				if (userDefenseData > 0) {
					changeColorR(userDefense);
					userDefenseData--;
				} else {
					changeColorR(userHP);
					userHPData--;
				}
				break;

			}// 스위치문 end

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			changeText();
			continueGame();

		} // for문 end

		if (diceValue.length < 5) {
			for (int i = 4; i >= diceValue.length; i--) {
				comDiceName[i] = "";
			}
		}

		throwDice.setEnabled(true);
	}

	void DiceImage(JLabel image) {

		image.setVisible(true);

		Timer timer_delay1 = new Timer();
		TimerTask task_delay1 = new TimerTask() {

			@Override
			public void run() {
				image.setVisible(false);
				System.out.println("다이스이미지");
				timer_delay1.cancel();
			}
		};
		(timer_delay1).schedule(task_delay1, 1000);

	}

	public static void main(String[] args) {
		new Test_Game_Screen1();

	}
}