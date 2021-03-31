package Game;

// 수정 15:36

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import java.util.*;

import javax.swing.*;

public class Thread_Test extends JFrame {

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

	
	static JLabel comHP;
	static JLabel comDiceTitle;
	
	Boolean turn;
	
	//게임이 진행중인지 확인.
	Boolean game_start;
	

	public Thread_Test() {

		turn = true;
		
		if(turn = true) {
			JOptionPane.showMessageDialog(null, "선공입니다!");
		}else{
			JOptionPane.showMessageDialog(null, "후공입니다!");
		}
		
		setTitle("게임 화면");

		// 기본 화면 틀
		getContentPane().setLayout(null);
		setBounds(100, 100, 600, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// 컴퓨터 남은 주사위 - comDiceTitle / comDiceX / comDice
		// 컴퓨터 남은 주사위 값 출력 컴포넌트 : comDice
		comDiceTitle = new JLabel("남은 주사위");
		JLabel comDiceX = new JLabel("x");
		JLabel comDice = new JLabel("15");

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
		JLabel comDefense = new JLabel(comDefenseG);
		comHP = new JLabel("");
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
		JLabel userDice = new JLabel("15");

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
		JLabel userDefense = new JLabel(userDefenseG);
		JLabel userHP = new JLabel("10");
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
		JButton throwDice = new JButton("던지기");
		JButton stopGame = new JButton("그만");

		getContentPane().add(throwDice);
		getContentPane().add(stopGame);
		throwDice.setBounds(146, 280, 100, 50);
		stopGame.setBounds(344, 280, 100, 50);

		// 주사위 결과 출력 comResult1~5
		JLabel comResult1 = new JLabel("주사위1");
		JLabel comResult2 = new JLabel("주사위2");
		JLabel comResult3 = new JLabel("주사위3");
		JLabel comResult4 = new JLabel("주사위4");
		JLabel comResult5 = new JLabel("주사위5");

		// 주사위 결과 출력 userResult1~5
		JLabel userResult1 = new JLabel("주사위1");
		JLabel userResult2 = new JLabel("주사위2");
		JLabel userResult3 = new JLabel("주사위3");
		JLabel userResult4 = new JLabel("주사위4");
		JLabel userResult5 = new JLabel("주사위5");

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

		JPanel userResult = new JPanel();
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
		testtest(); 
		throwDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				userRoll(); // 주사위 굴려서 값 저장

				// roll()의 저장된 값 출력
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
				//comHP.setText(String.valueOf(comHPData));
				comDefense.setText(String.valueOf(comDefenseData));

				comDefense.setText(String.valueOf(comDefenseData));

				comDefenseG = "";

				comDefenseG = "";

				for (int i = 0; i < comDefenseData; i++) {
					comDefenseG += "●";
				}
				for (int i = 0; i < 6 - comDefenseData; i++) {
					comDefenseG += "○";
				}

				comDefense.setText(comDefenseG);

				Timer timer_delay = new Timer();
				TimerTask task_delay = new TimerTask() {

					@Override
					public void run() {
						comRoll();
						
						//컴퓨터 글자 바꾸고
						comResult1.setText(comDiceName[0]);
						comResult2.setText(comDiceName[1]);
						comResult3.setText(comDiceName[2]);
						comResult4.setText(comDiceName[3]);
						comResult5.setText(comDiceName[4]);
						
						
						//컴퓨터 주사위 값을 받아서 유저 데이터 처리
						userDice.setText(String.valueOf(userDiceData));
						userHP.setText(String.valueOf(userHPData));
						userDefense.setText(String.valueOf(userDefenseData));
						
						userDefenseG = "";

						for (int i = 0; i < userDefenseData; i++) {
							userDefenseG += "●";
						}
						for (int i = 0; i < 6 - userDefenseData; i++) {
							userDefenseG += "○";
						}
						userDefense.setText(userDefenseG);
						
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
				};
				(timer_delay).schedule(task_delay, 2500);
				
				// 유저,컴퓨터의 HP=0 또는 유저,컴퓨터의 주사위값=0 이면 게임 종료
				continueGame();

			}
		});

		stopGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				userDiceData = 0;

				// 컴퓨터의 남은 주사위가 0이 될 때까지 컴퓨터만 롤
				while (comDiceData > 0) {

					comRoll();

					// roll()의 저장된 값 출력
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

//					time();

				}

				JOptionPane.showMessageDialog(null, "게임이 종료되었습니다!");
//				new Result(userHPData, comHPData);

				// new Result();
				dispose();

			}
		});
	}

	public void testtest() {
		comHP.setText("test");
		comDiceTitle.setText("test");
	}
	
	void continueGame() {
		if (userHPData <= 0 || comHPData <= 0 || comDiceData <= 0 || userDiceData <= 0) {
			JOptionPane.showMessageDialog(null, "게임이 종료되었습니다!");
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

	void userRoll() { // 유저 주사위 굴리기

		int a = 0;
		if (userDiceData >= 5) {
			a = 5;
		} else if (userDiceData < 5) {
			a = userDiceData;
		}
		diceValue = new int[a];

		for (int i = 0; i < diceValue.length; i++) {
			diceValue[i] = (int) (Math.random() * 4);
		}
		// 랜덤값 오름차순 정렬
		Arrays.sort(diceValue);

		for (int i = 0; i < diceValue.length; i++) {
			
			switch (diceValue[i]) {

			case 0: // 주사위
				userDiceName[i] = "주사위 추가";
				userDiceData++;
				break;
			case 1: // 디펜스
				userDiceName[i] = "디펜스";
				if (userDefenseData < 6) {
					userDefenseData++;
				}
				break;
			case 2: // 데스
				userDiceName[i] = "데스";
				if (userDefenseData > 0) {
					userDefenseData--;
				} else {
					userHPData--;
				}
				break;
			case 3: // 어택
				userDiceName[i] = "어택";
				if (comDefenseData > 0) {
					comDefenseData--;
				} else {
					comHPData--;
				}
				break;
			}
			userDiceData--;
			continueGame();
		}
		
		//주사위 텍스트 처리
		if (diceValue.length < 5) {
			for (int i = 4; i >= diceValue.length; i--) {
				userDiceName[i] = "";
			}
		}
	}

	void comRoll() { // 컴퓨터 주사위 굴리기

		int a = 0;
		if (comDiceData >= 5) {
			a = 5;
		} else if (comDiceData < 5) {
			a = comDiceData;
		}
		diceValue = new int[a];
		

		System.out.println("실행확인");
		

		for (int i = 0; i < diceValue.length; i++) {
			diceValue[i] = (int) (Math.random() * 4);
		} // 랜덤값 오름차순 정렬
		Arrays.sort(diceValue);

		for (int i = 0; i < diceValue.length; i++) {
			switch (diceValue[i]) {
			case 0: // 주사위
				comDiceName[i] = "주사위 추가";
				System.out.println("주사위");
				comDiceData++;
				break;
			case 1: // 디펜스
				comDiceName[i] = "디펜스";
				System.out.println("디펜스");
				if (comDefenseData < 6) {
					comDefenseData++;
				}
				break;
			case 2: // 데스
				comDiceName[i] = "데스";
				System.out.println("데스");
				if (comDefenseData > 0) {
					comDefenseData--;
				} else {
					comHPData--;
				}
				break;
			case 3: // 어택
				comDiceName[i] = "어택";
				System.out.println("어택");
				if (userDefenseData > 0) {
					userDefenseData--;
				} else {
					userHPData--;
				}
				break;

			}
			continueGame();
			comDiceData--;
		}

		if (diceValue.length < 5) {
			for (int i = 4; i >= diceValue.length; i--) {
				comDiceName[i] = "";
			}
		}


	}

	// 컴퓨터의 남은 주사위 값이 4이하일 경우

	/*
	 * void comRoll4() {
	 * 
	 * diceValue = new int[comDiceData];
	 * 
	 * for (int i = 0; i < diceValue.length; i++) { diceValue[i] = (int)
	 * (Math.random() * 4); } // 랜덤값 오름차순 정렬 // Arrays.sort(diceValue); for (int i =
	 * 0; i < diceValue.length; i++) { switch (diceValue[i]) { case 0: // 주사위
	 * comDiceName[i] = "주사위 추가"; comDiceData++; break; case 1: // 디펜스
	 * comDiceName[i] = "디펜스"; if (comDefenseData < 6) { comDefenseData++; } break;
	 * case 2: // 데스 comDiceName[i] = "데스"; if (comDefenseData > 0) {
	 * comDefenseData--; } else { userHPData--; } break; case 3: // 어택
	 * comDiceName[i] = "어택"; if (userDefenseData > 0) { userDefenseData--; } else {
	 * userHPData--; } break;
	 * 
	 * } // switch문 end
	 * 
	 * comDiceData--; } // for문 end
	 * 
	 * // 남은 주사위 개수 이상의 출력문 제거 for (int i = 4; i >= diceValue.length; i--) {
	 * comDiceName[i] = ""; }
	 * 
	 * }
	 */

	
	
	public static void main(String[] args) {
		new Thread_Test();
	}
}
