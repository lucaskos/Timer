package gui;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = Utils.getExtension(f);

		if (extension != null) {
			if (extension.equals(Utils.db) || extension.equals(Utils.db1)) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	@Override
	//get description under the save/open panel
	public String getDescription() {
		return "*." + Utils.db + ", *." + Utils.db1;
	}

}

class Utils {
	public final static String db = "db";
	public final static String db1 = "db1";

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		//get the number of characters after dot '.'
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
