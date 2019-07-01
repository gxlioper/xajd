package xgxt.pjpy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PjpyFinalUtils {
	
	public final static String LX_JXJ = "jxj";
	public final static String LX_RYCH = "rych";
	
	public static List<HashMap<String, String>> getPjpyLxList() {
		String[] en = {"jxj", "rych"};
		String[] cn = {"½±Ñ§½ð", "ÈÙÓþ³ÆºÅ"};
		
		return arrayToList(en, cn);
	}
	
	public static List<HashMap<String, String>> arrayToList(String[] en,
			String[] cn) {
		List<HashMap<String, String>> rs = 
									new ArrayList<HashMap<String, String>>();
		for (int i=0;i<en.length;i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", en[i]);
			map.put("mc", cn[i]);
			rs.add(map);
		}
		return rs;
	}
}
