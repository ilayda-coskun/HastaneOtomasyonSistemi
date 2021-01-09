package DatabaseConnection;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Mesaj {

	protected final static int errorNumberOne = 1;
	protected final static int errorNumberTwo = 2;

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "L�tfen t�m alanlar� doldurunuz! " + "(Hata " + errorNumberOne + ")";
			break;
		case "success":
			msg = "��lem Ba�ar�l� ";
			break;
		case "error":
			msg = "Bir hata ger�ekle�ti! " + "(Hata " + errorNumberTwo + ")";
			break;
		default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "sure":
			msg = "Bu i�lemi ger�ekle�tirmek istiyor musunuz?";
			break;
		default:
			msg = str;
			break;
		}

		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			return true;
		} else {
			return false;
		}

	}

}