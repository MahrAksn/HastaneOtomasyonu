package Helper;

import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;

public class Helper {

	public static void showMsg(String str) {
		String msg;

		switch (str) {
		case "Fill":
			msg = "Bos alanlari doldurunuz...";
			break;
		case "succes":
			msg = "Islem basarili...";
			break;
		case "error":
			msg = "TC Numarasi ya da sifrenizi yanlis.Lutfen kontrol ediniz.";
			break;
		default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confrim(String str) {
		String msg;

		switch (str) {
		case "sure": {
			msg = "Bu islemi yapmak istiyor musunuz?";
			break;
		}
		default:
			msg = str;
			break;
		}
		int result = JOptionPane.showConfirmDialog(null, msg, "Dikkat !", JOptionPane.YES_NO_OPTION);
		if (result == 0)
			return true;
		else
			return false;
	}

}
