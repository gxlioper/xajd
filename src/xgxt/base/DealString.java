/*
 * �������� 2006-5-13
 * 
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.base;

import java.util.Date;
import java.util.Vector;



public class DealString {

	public static long makeID(int uid) {
		// �û�ռ5λ
		uid += 10000;
		
		// ʱ��ռ9λ:�룬ȥ����һλ
		java.util.Date time = new java.util.Date();
		long second = time.getTime() / 1000;
		String str = second + "";
		str = str.substring(1, str.length());

		// Ⱥ��ռ5λ
		str = uid + str + 10000;

		return Long.parseLong(str);
	}
	
	/** ��nullת��Ϊ"" */
	public static String toString(String str) {
		if (str == null) {
			str = "";
		}
		if (str.equals("null")) {
			str = "";
		}
		str = str.trim();
		return str;
	}

	/** ת������ */
	public static String toGBK(String str) {
		try {
			if (str == null || str == "") {
				str = "";
			} 
			/**
			 * Ŀǰ���ù���������ת�� ��ת�뷽��������
			 */
//			else {
//				str = new String(str.getBytes("ISO-8859-1"), "GBK");
//			}
		} catch (Exception e) {
			System.out.println("DealString::toGBK(String)����ʱ��������Ϊ��" + e);
		}
		return str;
	}

	public static String toMKByte(int size) {
		if (size > (1024 * 1024)) {
			return ((float) size / (1024 * 1024) + "").substring(0, 4) + "MB";
		} else if (size > 1024) {
			return ((float) size / 1024 + "").substring(0, 4) + "KB";
		} else {
			return size + "B";
		}
	}

	/** UTF8 */
	public static String toUtf8String(String src) {
		byte[] b = src.getBytes();
		char[] c = new char[b.length];
		for (int i = 0; i < b.length; i++) {
			c[i] = (char) (b[i] & 0x00FF);
		}
		return new String(c);
	}

	public static String toASCII(String str) {
		try {
			if (str == null) {
				str = "";
			} else {
				str = new String(str.getBytes("GBK"), "ISO-8859-1");
			}
		} catch (Exception e) {
			System.out.println("DealString::toGBK(String)����ʱ��������Ϊ��" + e);
		}
		return str;
	}

	/** �ָ��ַ��� */
	public static String[] splitStr(String str, char c) {
		str += c;
		int n = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				n++;
			}
		}

		String out[] = new String[n];

		for (int i = 0; i < n; i++) {
			int index = str.indexOf(c);
			out[i] = str.substring(0, index);
			str = str.substring(index + 1, str.length());
		}
		return out;
	}

	/** ȡ��ϵͳʱ�� */
	public static String getDateTime() {
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
		String time = f.format(new java.util.Date());
		return time;
	}

	/** �滻�ַ��� */
	public static String Replace(String source, String oldString, String newString) {
		StringBuffer output = new StringBuffer();

		int lengthOfSource = source.length(); // Դ�ַ�������
		int lengthOfOld = oldString.length(); // ���ַ�������
		int posStart = 0; // ��ʼ����λ��
		int pos; // ���������ַ�����λ��

		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengthOfOld;
		}
		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	/** ȡ���������ڵ�����֮�� */
	public static long getDaysInterval(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / 86400000;
	}

	/** ���ַ�����ʽ��Ϊ�̶����� */
	public static String toLengthStr(String instr, int len) {
		int n = instr.length();
		for (int i = 0; i < (len - n); i++) {
			instr = " " + instr;
		}
		return instr;
	}

	/** ���ַ�����ʽ��Ϊ�̶�����(�ұ߲��ո�) */
	public static String toLengthStrRight(String instr, int len) {
		int n = instr.length();
		for (int i = 0; i < (len - n); i++) {
			instr = instr + " ";
		}
		return instr;
	}

	/** Unicodeת����GB��Դ�� */
	public static String UnicodetoGB(String s) {
		StringBuffer sb = new StringBuffer();
		boolean escape = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\\':
				escape = true;
				break;
			case 'u':
			case 'U':
				if (escape) {
					try {
						sb.append((char) Integer.parseInt(s.substring(i + 1,
								i + 5), 16));
						escape = false;
					} catch (NumberFormatException e) {
						System.out.println("DealString::UnicodetoGB(String)����ʱ����"
								+ e
								+ "���׳��µ�IllegalArgumentException�쳣.");
						throw new IllegalArgumentException();
					}
					i += 4;
				} else {
					sb.append(c);
				}
				break;
			default:
				sb.append(c);
			break;
			}
		}
		return sb.toString();
	}

	/** ��str���ظ���ȥ�� */
	public static String strDistinct(String str) {
		String[] strArr = str.split(",");
		String strAim = ",";
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].equals("")) {
				continue;
			}
			if (strAim.indexOf("," + strArr[i] + ",") == -1) {
				strAim = strAim + strArr[i] + ",";
			}
		}
		if (!strAim.equals(",")) {
			strAim = strAim.substring(1, strAim.length() - 1);
		} else {
			strAim = "";
		}
		return strAim;
	}

	/** �ַ�ת��ΪASCII */
	public static int toASCII(char c) {
		int i = c;
		return i;
	}

	/** ȡ���ַ��ֽڳ��� */
	public static int byteLength(String str) {
		return ((str.getBytes()).length);
	}

	/** ȡ���ַ�����ͷ��ʼָ�������Ӵ� */
	public static String strByteCopy(String str, int nEnd) {
		if (nEnd == 0) {
			return "";
		}
		byte[] byteStr = str.getBytes();
		int k = byteStr.length;
		String strSub = new String(byteStr, 0, nEnd < k ? nEnd : k);
		if (strSub.length() == 0) {
			strSub = new String(byteStr, 0, nEnd - 1);
		}
		return strSub;
	}

	public static boolean strMatch(String motherStr, String childStr) {
		boolean matched = false;
		int mLength = motherStr.length();
		int cLength = childStr.length();
		int starWith;
		if (mLength >= cLength) {
			starWith = mLength - cLength;
			for (int i = 0; i <= starWith; i++) {
				matched = motherStr.startsWith(childStr, i);
				if (matched) {
					break;
				}
			}
		}
		return matched;
	}

	public static Vector<String> simplify(String[] str) {
		Vector<String> vect = new Vector<String>();
		for (int i = 0; i < str.length; i++) {
			vect.add(str[i]);
		}
		for (int i = 0; i < vect.size(); i++) {
			String[] s1 = vect.get(i).split("_");
			for (int j = i + 1; j < vect.size();) {
				boolean out = true;
				String[] s2 = vect.get(j).split("_");
				for (int k = 0; k < s1.length; k++) {
					if (!s1[k].equals(s2[k]) && !s1[k].equals("0")) {
						out = false;
						break;
					}
				}
				if (out) {
					vect.remove(j);
				} else {
					j++;
				}
			}
		}
		return vect;
	}

	public static Vector simplify(Vector vstr) {
		Vector<Object> vect = new Vector<Object>();
		for (int i = 0; i < vstr.size(); i++) {
			vect.add(vstr.get(i));
		}
		for (int i = 0; i < vect.size(); i++) {
			String[] s1 = ((String) vect.get(i)).split("_");
			for (int j = i + 1; j < vect.size();) {
				boolean out = true;
				String[] s2 = ((String) vect.get(j)).split("_");
				for (int k = 0; k < s1.length; k++) {
					if (!s1[k].equals(s2[k]) && !s1[k].equals("0")) {
						out = false;
						break;
					}
				}
				if (out) {
					vect.remove(j);
				} else {
					j++;
				}
			}
		}
		return vect;
	}

	public static String getString(String s1) {
		String temp = "\"" + s1 + "\"";
		return temp;
	}
    public static String numToChnum(String num){
    	String numStr = "";
    	int numV = Integer.parseInt(num);
    	switch (numV) {
    	case 0:
    		numStr = "��";
			break;
		case 1:
			numStr = "һ";
			break;
		case 2:
			numStr = "��";
			break;
		case 3:
			numStr = "��";
			break;
		case 4:
			numStr = "��";
			break;
		case 5:
			numStr = "��";
			break;
		case 6:
			numStr = "��";
			break;
		case 7:
			numStr = "��";
			break;
		case 8:
			numStr = "��";
			break;
		case 9:
			numStr = "��";
			break;
		case 100:
			numStr = "ʮ";
			break;
		default:
			break;
		}
    	return numStr;
    }
    
    /**
     * ��sqlע���ַ�ȥ��
     * @param str
     * @return String
     * */
    public static String replaceImmitStr(String str){
    	str = str.replaceAll("'", "");
    	return str;
    }
}

