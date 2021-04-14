package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
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
import javax.swing.SwingConstants;

public class Game_Screen extends JFrame {

	int comHPData = 10;
	int userHPData = 10;
	int comDefenseData = 0;
	int userDefenseData = 0;
	int comDiceData = 15;
	int userDiceData = 15;

	int onestart = 0;

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
	static JButton stopGame;

	static JLabel userDiceGif; // 유저 주사위 이미지
	static JLabel comDiceGif; // 컴퓨터 주사위 이미지

	String path;

	TimerTask throwDice_task;
	Timer throwDice_delay;

	TimerTask stopGame_task;
	Timer stopGame_delay;

	public Game_Screen() {

		System.out.println(Game_Screen.class.getResource("").getPath());

		try {
			path = URLDecoder.decode(Game_Screen.class.getResource("").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.out.println("경로설정 오류");
		}
		;
		String com_path = path + "image/dice.gif";
		String user_path = path + "image/dice.gif";
		String back_path = path + "image/gamescreen.png"; // 배경화면 이미지

//		Color backColor = new Color(210, 180, 145);
//		Color lineColor = new Color(252, 247, 222);
//		Color buttonColor = new Color(121, 117, 117);

		// button 이미지
		String button_path = path + "image/button.png";
		ImageIcon originIcon = new ImageIcon(button_path); // ImageIcon객체를 생성
		Image originImg = originIcon.getImage(); // ImageIcon에서 Image를 추출
		Image changedImg = originImg.getScaledInstance(100, 50, Image.SCALE_SMOOTH);

		String button_path_b = path + "image/button_b.png";
		ImageIcon originIcon_b = new ImageIcon(button_path_b); // ImageIcon객체를 생성
		Image originImg_b = originIcon_b.getImage(); // ImageIcon에서 Image를 추출
		Image changedImg_b = originImg_b.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		ImageIcon b = new ImageIcon(changedImg_b);
		
		setTitle("게임 화면");

		// 기본 화면 틀
		getContentPane().setLayout(null);
		setBounds(100, 100, 600, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 컴퓨터 남은 주사위 - comDiceTitle / comDiceX / comDice
		// 컴퓨터 남은 주사위 값 출력 컴포넌트 : comDice
		JLabel comDiceTitle = new JLabel("남은 주사위");
		JLabel comDiceX = new JLabel("x");
		comDice = new JLabel("15");
		comDiceTitle.setForeground(Color.WHITE);
		comDiceX.setForeground(Color.WHITE);
		comDice.setForeground(Color.WHITE);
		comDiceTitle.setBounds(12, 27, 85, 15);
		comDiceX.setBounds(12, 40, 19, 15);
		comDice.setBounds(22, 41, 57, 15);
		getContentPane().add(comDiceTitle);
		getContentPane().add(comDiceX);
		getContentPane().add(comDice);

		// comStatus - comDefense, comHP
		// 컴퓨터 방어 값 출력 컴포넌트 : comDefense
		// 컴퓨터 체력 값 출력 컴포넌트 : comHP
		comDefense = new JLabel(comDefenseG);
		JLabel comMaxHP = new JLabel("HP      /10");
		comHP = new JLabel("10");
		comHP.setFont(new Font("굴림", Font.BOLD, 14));
		comMaxHP.setFont(new Font("굴림", Font.BOLD, 14));
		comDefense.setBounds(262, 28, 72, 15);
		comHP.setBounds(282, 50, 35, 15);
		comMaxHP.setBounds(247, 50, 100, 15);
		comHP.setHorizontalAlignment(SwingConstants.CENTER);
		comMaxHP.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(comDefense);
		getContentPane().add(comHP);
		getContentPane().add(comMaxHP);

		// 유저 남은 주사위 - userDiceTitle / userDiceX / userDice
		// 유저 남은 주사위 값 출력 컴포넌트 : userDice
		JLabel userDiceTitle = new JLabel("남은 주사위");
		JLabel userDiceX = new JLabel("x");
		userDice = new JLabel("15");
		userDiceTitle.setForeground(Color.WHITE);
		userDice.setForeground(Color.WHITE);
		userDiceX.setForeground(Color.WHITE);
		userDiceTitle.setBounds(500, 310, 79, 15);
		userDice.setBounds(558, 326, 19, 15);
		userDiceX.setBounds(545, 326, 19, 15);
		userDiceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		userDice.setHorizontalAlignment(SwingConstants.RIGHT);
		userDiceX.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(userDiceTitle);
		getContentPane().add(userDice);
		getContentPane().add(userDiceX);

		// 던지기 버튼 : throwDice / 그만 버튼 : stopGame
		throwDice = new JButton("던지기", new ImageIcon(changedImg));
		throwDice.setForeground(Color.WHITE); // 글씨색 흰색
		throwDice.setHorizontalTextPosition(JButton.CENTER); // 글자 중앙정렬
		throwDice.setContentAreaFilled(false); // 버튼 배경 투명화
		throwDice.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		throwDice.setRolloverIcon(b); // 호버효과
		throwDice.setBounds(114, 292, 100, 50);
		getContentPane().add(throwDice);
		
		stopGame = new JButton("그만", new ImageIcon(changedImg));	
		stopGame.setForeground(Color.WHITE); // 글씨색 흰색
		stopGame.setHorizontalTextPosition(JButton.CENTER); // 글자 중앙정렬
		stopGame.setContentAreaFilled(false); // 버튼 배경 투명화
		stopGame.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		stopGame.setRolloverIcon(b); // 호버효과
		stopGame.setBounds(380, 292, 100, 50);		
		getContentPane().add(stopGame);		

		// userStatus - userDefense, userHP
		// 유저 방어 값 출력 컴포넌트 : userDefense
		// 유저 체력 값 출력 컴포넌트 : userHP
		userDefense = new JLabel(userDefenseG);
		userHP = new JLabel("10");
		JLabel userMaxHP = new JLabel("HP      /10");
		userHP.setFont(new Font("굴림", Font.BOLD, 14));
		userMaxHP.setFont(new Font("굴림", Font.BOLD, 14));
		userDefense.setBounds(262, 288, 72, 15);
		userHP.setBounds(282, 308, 35, 15);
		userMaxHP.setBounds(247, 308, 100, 15);
		userHP.setHorizontalAlignment(SwingConstants.CENTER);
		userMaxHP.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(userDefense);
		getContentPane().add(userHP);
		getContentPane().add(userMaxHP);

		// 주사위 결과 출력 comResult1~5
		comResult1 = new JLabel("");
		comResult2 = new JLabel("");
		comResult3 = new JLabel("");
		comResult4 = new JLabel("");
		comResult5 = new JLabel("");
		comResult1.setForeground(Color.LIGHT_GRAY);
		comResult2.setForeground(Color.LIGHT_GRAY);
		comResult3.setForeground(Color.LIGHT_GRAY);
		comResult4.setForeground(Color.LIGHT_GRAY);
		comResult1.setBounds(84, 127, 140, 15);
		comResult2.setBounds(84, 152, 140, 15);
		comResult3.setBounds(84, 177, 140, 15);
		comResult4.setBounds(84, 202, 140, 15);
		comResult5.setBounds(84, 227, 140, 15);
		comResult5.setForeground(Color.LIGHT_GRAY);
		comResult1.setHorizontalAlignment(SwingConstants.CENTER);
		comResult2.setHorizontalAlignment(SwingConstants.CENTER);
		comResult3.setHorizontalAlignment(SwingConstants.CENTER);
		comResult4.setHorizontalAlignment(SwingConstants.CENTER);
		comResult5.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(comResult1);
		getContentPane().add(comResult2);
		getContentPane().add(comResult3);
		getContentPane().add(comResult4);
		getContentPane().add(comResult5);

		// 주사위 결과 출력 userResult1~5
		userResult1 = new JLabel("");
		userResult2 = new JLabel("");
		userResult3 = new JLabel("");
		userResult4 = new JLabel("");
		userResult5 = new JLabel("");
		userResult1.setForeground(Color.WHITE);
		userResult2.setForeground(Color.WHITE);
		userResult3.setForeground(Color.WHITE);
		userResult4.setForeground(Color.WHITE);
		userResult5.setForeground(Color.WHITE);
		userResult1.setBounds(380, 127, 135, 15);
		userResult2.setBounds(380, 152, 135, 15);
		userResult3.setBounds(380, 177, 135, 15);
		userResult4.setBounds(380, 202, 135, 15);
		userResult5.setBounds(380, 227, 135, 15);
		userResult1.setHorizontalAlignment(SwingConstants.CENTER);
		userResult2.setHorizontalAlignment(SwingConstants.CENTER);
		userResult3.setHorizontalAlignment(SwingConstants.CENTER);
		userResult4.setHorizontalAlignment(SwingConstants.CENTER);
		userResult5.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(userResult1);
		getContentPane().add(userResult2);
		getContentPane().add(userResult3);
		getContentPane().add(userResult4);
		getContentPane().add(userResult5);

		// 주사위 이미지
		ImageIcon ii2 = new ImageIcon(com_path);
		ImageIcon ii = new ImageIcon(user_path);
		System.out.println(ii2);
		System.out.println(path);
		comDiceGif = new JLabel(ii2);
		userDiceGif = new JLabel(ii);
		comDiceGif.setBounds(57, 112, 190, 145);
		userDiceGif.setBounds(348, 112, 190, 145);
		getContentPane().add(comDiceGif);
		getContentPane().add(userDiceGif);
		comDiceGif.setVisible(false);
		userDiceGif.setVisible(false);

		// 배경화면 이미지
		JLabel back = new JLabel(new ImageIcon(back_path));
		back.setLocation(0, 0);
		back.setSize(594, 371);
		getContentPane().add(back);

		setVisible(true); // GUI 최하단에 두기
		// comDice userDice / comHP userHP / comDefense userDefense
		// 이벤트 처리 - throwDice, stopGame

		int turn;
		turn = (int) (Math.random() * 2);

		if (turn == 0) { // 유저 후공
			JOptionPane.showMessageDialog(null, "후공입니다.");

			DiceImage(comDiceGif); // 주사위 이미지

			throwDice.setEnabled(false); // 던지기 버튼 비활성화
			stopGame.setEnabled(false); // 그만 버튼 비활성화

			Timer timer_delay = new Timer();
			TimerTask task_delay = new TimerTask() {

				@Override
				public void run() {
					comRoll();
					timer_delay.cancel();
				}
			};
			(timer_delay).schedule(task_delay, 2000);
		} else if (turn == 1) { // 유저 선공
			JOptionPane.showMessageDialog(null, "선공입니다.");
		}

		throwDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				throwDice.setEnabled(false); // 던지기 버튼 비활성화
				stopGame.setEnabled(false); // 그만 버튼 비활성화
				DiceImage(userDiceGif); // 유저 던지기 시 유저의 주사위 이미지 출력

				Timer timer_delay = new Timer();
				TimerTask task_delay = new TimerTask() {

					@Override
					public void run() {
						userRoll();
						if (comDiceData == 0) {
							System.out.println("이미지 실행 X");
						} else {
							DiceImage(comDiceGif); // 유저의 던지기가 끝난 후 컴퓨터의 주사위 이미지 출력
						}
						timer_delay.cancel();

					}
				};
				(timer_delay).schedule(task_delay, 2000);

				// 주사위 굴려서 값 저장

				throwDice_delay = new Timer();
				throwDice_task = new TimerTask() {

					@Override
					public void run() {

						comRoll();

					}
				};
				if (userDiceData < 5) {
					(throwDice_delay).schedule(throwDice_task, 2500 + (1700 * userDiceData));
				} else {
					(throwDice_delay).schedule(throwDice_task, 11000);
				}

			}
		});

		stopGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				userDiceData = 0;
				changeText();
				autoCom();
			}
		});

		throwDice.requestFocus();

		throwDice.setMnemonic(KeyEvent.VK_SPACE);

		throwDice.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println("포커스 잃음");

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("포커스 획득");

			}
		});

	}

	// 유저가 그만하거나 주사위가 0개일 때 컴퓨터 혼자 돌아가는 메서드
	void autoCom() {
		stopGame_delay = new Timer();
		stopGame_task = new TimerTask() {
			@Override
			public void run() {
				comRoll();
				System.out.println("굴러가는지 테스트");
			}
		};
		(stopGame_delay).schedule(stopGame_task, 2500, 2000); // 2.5초 후 run()의 내용을 1초마다 반복하기.
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
			// JOptionPane.showMessageDialog(null, "게임이 종료되었습니다!");

			dispose();
			System.out.println(throwDice_delay);
			System.out.println(stopGame_delay);
			if (throwDice_delay != null) {
				throwDice_delay.cancel(); // throwDice timer 종료 (유저턴에서 유저가 죽었을 때 컴퓨터가 다음 다이스를 돌리지 않도록 하기 위함.
				System.out.println("throwDice_delay 종료확인");
				System.out.println(throwDice_delay);
			}
			if (stopGame_delay != null) {
				stopGame_delay.cancel(); // stopGame timer 종료
				System.out.println("stopGame_delay 종료확인");
				System.out.println(stopGame_delay);
			}
			if (comHPData < 0) {
				Values.comHPData = 0;
			} else {
				Values.comHPData = comHPData;
			}

			if (userHPData < 0) {
				Values.userHPData = 0;
			} else {
				Values.userHPData = userHPData;
			}

			onestart = 0;
			new Result();
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
		Timer timer_delay = new Timer();
		TimerTask task_delay = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.red);
			}
		};
		(timer_delay).schedule(task_delay, 1000);

		// 1.3초 후 검정색으로 변경
		Timer timer_delay0 = new Timer();

		TimerTask task_delay0 = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.white);
			}
		};
		(timer_delay0).schedule(task_delay0, 1300);
	}

	void changeColorB(JLabel label) { // +되는 값을 파란색으로 변경

		// 1초 후 +되는 값을 파란색으로 변경
		Timer timer_delay = new Timer();
		TimerTask task_delay = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.blue);
			}
		};
		(timer_delay).schedule(task_delay, 1000);

		// 1.3초 후 -되는 값을 검정색으로 변경
		Timer timer_delay0 = new Timer();
		TimerTask task_delay0 = new TimerTask() {

			@Override
			public void run() {
				label.setForeground(Color.white);
			}
		};
		(timer_delay0).schedule(task_delay0, 1300);
	}

	void userRoll() { // 유저 주사위 굴리기
		System.out.println();
		throwDice.setEnabled(false); // 던지기 버튼 비활성화
		stopGame.setEnabled(false); // 그만 버튼 비활성화

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
				if (userHPData < 0) {
					userHPData = 0;
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
				if (comHPData < 0) {
					comHPData = 0;
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
		} // for문 end

		continueGame();

		if (comDiceData == 0) {
			throwDice.setEnabled(true);
			stopGame.setEnabled(true);
		}

		if (diceValue.length < 5) {
			for (int i = 4; i >= diceValue.length; i--) {
				userDiceName[i] = "";
			}
		}

		throwDice.setFocusable(true);
		throwDice.requestFocus();

	}

	void comRoll() { // 컴퓨터 주사위 굴리기

		throwDice.setEnabled(false); // 던지기 버튼 비활성화
		stopGame.setEnabled(false); // 그만 버튼 비활성화

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
				if (userHPData < 0) {
					userHPData = 0;
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

		} // for문 end

		continueGame();
		if (diceValue.length < 5) {
			for (int i = 4; i >= diceValue.length; i--) {
				comDiceName[i] = "";
			}
		}

		if (userDiceData == 0 && comDiceData > 0) {
			comRoll();
		}

		throwDice.setEnabled(true);
		stopGame.setEnabled(true);

		throwDice.setFocusable(true);
		throwDice.requestFocus();

	}

	void DiceImage(JLabel image) {

		image.setVisible(true);

		Timer timer_delay = new Timer();
		TimerTask task_delay = new TimerTask() {

			@Override
			public void run() {
				image.setVisible(false);
				System.out.println("다이스이미지");
				timer_delay.cancel();
			}
		};
		(timer_delay).schedule(task_delay, 1000);

	}

	public static void main(String[] args) {
		new Game_Screen();
	}
}