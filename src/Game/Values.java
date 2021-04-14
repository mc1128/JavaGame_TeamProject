package Game;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Values {
	
	static int gold;
	static int userHPData;
	static int comHPData;
	static int reward;
	static int gold_save;
	static int user_win;
	static int user_defeat;
	static int user_draw;
	static String user_id;
	static String user_name;

	static ImageIcon imgResize(String path, int width, int heigh){
		ImageIcon originIcon = new ImageIcon(path); // ImageIcon객체를 생성
		Image originImg = originIcon.getImage(); // ImageIcon에서 Image를 추출
		Image changedImg = originImg.getScaledInstance(width, heigh, Image.SCALE_SMOOTH);
		ImageIcon changedImgIcon = new ImageIcon(changedImg);
		return changedImgIcon;
	}
	
	static void setButton(JButton button, ImageIcon imageicon) {
		button.setForeground(Color.WHITE); // 글씨색 흰색
		button.setHorizontalTextPosition(JButton.CENTER); // 글자 중앙정렬
		button.setContentAreaFilled(false); // 버튼 배경 투명화
		button.setBorderPainted(false); // 버튼 테두리 외곽선 지우기
		button.setRolloverIcon(imageicon); // 호버효과
	}
	
}
