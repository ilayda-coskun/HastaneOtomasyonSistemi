package DatabaseConnection;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Mesaj {

	protected final static int errorNumberOne = 1;
	protected final static int errorNumberTwo = 2;

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanlarý doldurunuz! " + "(Hata " + errorNumberOne + ")";
			break;
		case "success":
			msg = "Ýþlem Baþarýlý ";
			break;
		case "error":
			msg = "Bir hata gerçekleþti! " + "(Hata " + errorNumberTwo + ")";
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
			msg = "Bu iþlemi gerçekleþtirmek istiyor musunuz?";
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
