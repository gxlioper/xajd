package xgxt.utils.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;



public class Tool {
    /*作者:张建军
     *功能:判断目录是否存在,不存在则进行创建
     *参数说明:
     *   path:绝对路径
     */
 	@SuppressWarnings("static-access")
	public void mkdirs(String path) {
		if((new File(path).isDirectory())){
			this.print("1");
	        try{
	            new File(path).mkdirs();
	        }catch(SecurityException e){
	        	e.getStackTrace();
	            System.out.println("can not make security direcoty");
	        }
	    }
	}

 
 	/*作者:张建军
     *功能:判断目录是否存在,不存在则进行创建
     *参数说明
     *	filepath 要得到文件名的路径;
     */
	public String GetFileName(String filepath)
	{
	    String returnstr = "*.*";
	    int length= filepath.trim().length();
	    
	    filepath = filepath.replace('\\', '/');
	    if(length >0)
	    {
	        int i = filepath.lastIndexOf("/");
	        if (i >= 0)
	        {
	            filepath  = filepath.substring(i + 1);
	            returnstr = filepath;
	        }
	    }
	    return returnstr;
	}

	/*作者:张建军
     *功能:输出对象
     *参数说明
     *	obj 想要输出的对象;
     */
	public static void print(Object obj) {
		System.out.println(obj);
	}
	
	/*作者:张建军
     *功能:删除文件夹
     *参数说明
     *	path 绝对路径;
     */
	public void path_delete(String path) {
		if((new File(path).isDirectory())){
		    try{
		        new File(path).delete();                    
		    }catch(SecurityException e){
		        System.out.println("can not delete security direcoty");
		    }
		   }
	}

	/*作者:张建军
     *功能:删除文件
     *参数说明
     *	path 绝对路径;
     */
	public void file_delete(String path) {
		if((new File(path).exists())){
		    try{
		        new File(path).delete();                    
		    }catch(SecurityException e){
		        System.out.println("can not delete security direcoty");
		    }
		   }
	}

	/*作者:张建军
     *功能:Form中Bean置空
     *参数说明
     *	request 输出对象;
     *  txt数组;
      */
	public void setNullBean(HttpServletRequest request, String[] txt) {
		for (int i = 0; i < txt.length; i++)
		{
			request.setAttribute(txt[i], "");
		}
	}

	public String[] getNewSz(String[] txt,int number) {
		String[] str=new String[number];
		for(int i=txt.length-number;i<txt.length;i++){
			str[i-(txt.length-number)]=txt[i];
		}
		return str;		
	}
	
	public String[] getNewSz(String[] txt,int start,int len) {
		String[] str=new String[len];
		int k=0;
		for(int i=start;i<len;i++){	
			str[k]=txt[i];
			k++;
		}
		return str;
		
	}
	
	public String getRecordid() {
		String recordid=new String();
		java.util.Date dt=new java.util.Date();
		long lg=dt.getTime();
		Long ld=new Long(lg);
		recordid=ld.toString();
		return recordid;
	}

	public String[] getBean(HttpServletRequest request, String[] txt) {
		String[] txtV=new String[txt.length];
		for (int i = 0; i < txt.length; i++) {
			txtV[i]=request.getParameter(txt[i]);/*
				txtV[i]=Base.isNull(txtV[i])?"":deal.toGBK(txtV[i]);*/
			}
		return txtV;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector parseCSV(String s)
    {
    	Vector v = new Vector();
    	  StringTokenizer st = new StringTokenizer(s,",");
    	     while (st.hasMoreTokens()) {
    	         v.add(st.nextToken());
    	     }
    	return v;
    }
    public static String delAInB(String a, String b)
    {
    	String s= b;
    	
    	return s;
    }
    public static String replaceAWithB(String str, String A, String B)
    {
     // if ( isStringNull(str) ) return "";

      StringBuffer res = new StringBuffer();
      int p = 0;
      int q = 0;
      while ((q = str.indexOf(A, p)) > -1) {
        res.append(str.substring(p, q));
        if ( B!= null )
          res.append(B);
        p = q + A.length();
      }
      res.append(str.substring(p));
      return res.toString();
    }
    
	 
	 public String getTime()
	 {
//		 Date dateresult=new Date();
//		 Date dd = new Date();  
		 Calendar calendar = Calendar.getInstance();
		 String hh="";
		 if(calendar.get(Calendar.HOUR_OF_DAY)<10)
		 {
			 hh=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			 hh="0"+hh;
		 }
		 else
		 {
			 hh=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		 }
		 String  ss=String.valueOf(calendar.get(Calendar.MINUTE));
		 String times = hh+":"+ss;
        return times;
	 }


	public String getMacAddressIP(String remotePcIP) {
	String str = "";
	String macAddress = "";
	try {
	    Process pp = Runtime.getRuntime().exec("nbtstat -A " + remotePcIP);
	    InputStreamReader ir = new InputStreamReader(pp.getInputStream());
	    LineNumberReader input = new LineNumberReader(ir);
	    for (int i = 1; i < 100; i++) {
		str = input.readLine();
		if (str != null) {
		    if (str.indexOf("MAC Address") > 1) {
			macAddress = str.substring(
				str.indexOf("MAC Address") + 14, str.length());
		    }
		}
	    }
	} catch (IOException ex) {
	
	} finally {
	
	}
	return macAddress;
	}

	
	
	/*
	public static String getUUID() {
		IdentifierGenerator gen = new UUIDHexGenerator();
		String uuid = "";
		try {
			uuid = (String) gen.generate(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uuid;
	}*/
	
	/**
	 * 功能：得到当前时间
	 * 
	 * @return 2006年1月5日 下午04时20分11秒
	 */
	public static String getCurrentTime() {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, Locale.CHINA);
		return df.format(new Date());
	}

	public static Timestamp getSqlTime(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Timestamp getSystemTime() {
		return getSqlTime(new Date());
	}

	public static Timestamp getSqlTime1(String dateStr) {
		if(isNull(dateStr)) return null;
		return getSqlTime(getStringTime1(dateStr));
	}

	public static Timestamp getSqlTime2(String dateStr) {
		return getSqlTime(getStringTime2(dateStr));
	}

	public static Timestamp getSqlTime3(String dateStr) {
		if (isNull(dateStr))
			return null;
		return getSqlTime(getStringTime3(dateStr));
	}

	/**
	 * 功能：判断字符串p是否为空
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isNull(String p) {
		if (p == null || p.equalsIgnoreCase("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(int p) {
		int i = 0;
		return p != i;
	}

	public static boolean isNull(long p) {
		long i = 0;
		return p != i;
	}
	
	public static void write(String path, String add_Str, boolean accpet) {
		BufferedWriter bw = null;
		File f = null;
		FileWriter fw = null;

		f = new File(path.substring(0, path.lastIndexOf("\\")));
		if(!f.exists())
		f.mkdirs();
		f = new File(path);
		try {
			if (!f.exists()) {
				f.createNewFile();
				f = new File(path);
			}
			fw = new FileWriter(f, accpet);
			bw = new BufferedWriter(fw);
			bw.write(add_Str);
			bw.flush();
		} catch (IOException e) {
			System.out.println("写文件失败:" + e.getMessage());
		} finally {
			try {
				if (bw != null) {
					bw.close();
					bw = null;
				}
				if (fw != null) {
					fw.close();
					fw = null;
				}
			} catch (IOException e1) {
				System.out.println("写入文件失败，关闭文件流失败：" + e1.getMessage());
			}
		}
	}
	
	
	
	/**
	 * 功能:得到长度为length的随机字符串.
	 * 
	 * @param length
	 * @return
	 */
	public static String random(int length) {
		int range = 10;
		String ret = "";
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			int temp = rand.nextInt(range);
			ret = ret + temp;
		}
		return ret;
	}

	/**
	 * 功能:在0-range范围内,给到count个随机数 *
	 * 
	 * @param range
	 *            随机数的范围
	 * @param count
	 *            随机数的个数 例如: range=20 在0-19内随机生成2个数
	 */
	@SuppressWarnings("unchecked")
	public static String[] random(int range, int count) {
		if (count > range) {
			return null;
		}
		String[] randstr = new String[count];
		List list = new ArrayList();
		for (int i = 0; i < range; i++) {
			list.add(String.valueOf(i));
		}
		Random rand = new Random();
		int n = 0;
		for (int i = 0; i < count; i++) {
			int j = rand.nextInt(list.size());
			randstr[n] = (String) list.get(j);
			list.remove(list.get(j));
			n++;
		}

		return randstr;
	}

	/**
	 * 功能:图片按比例压缩.
	 * 
	 * @param sw
	 * @param sh
	 * @param w
	 * @param h
	 * @return
	 */
	final public static int[] getImageSize(int sw, int sh, int w, int h) {
		float f_h = h;
		float f_w = w;
		float f_sh = sh;
		float f_sw = sw;

		if ((f_sh / f_sw) * f_w > f_h) {
			f_w = (f_sw / f_sh) * f_h;
		} else {
			f_h = (f_sh / f_sw) * f_w;
		}
		String _tmp = f_w + "";
		int i = _tmp.indexOf(".");
		int ii_w = Integer.parseInt(_tmp.substring(0, i));
		_tmp = f_h + "";
		i = _tmp.indexOf(".");
		int ii_h = Integer.parseInt(_tmp.substring(0, i));

		int[] s = { ii_w, ii_h };
		if (sw < w && sh < h) {
			s[0] = sw;
			s[1] = sh;
		}
		return s;
	}

	/**
	 * 功能:字符串转换行数
	 * 
	 * @param srcStr
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String replaceAll(String srcStr, String s1, String s2) {
		StringReader reader = new StringReader(srcStr);
		StringBuffer _buffer = new StringBuffer();
		StringBuffer buffer = new StringBuffer();

		char[] c = new char[1];
		try {
			while (reader.read(c) >= 0) {

				_buffer.append(c[0]);
				if (s1.startsWith(_buffer.toString())) {
					if (s1.equalsIgnoreCase(_buffer.toString())) {
						_buffer = new StringBuffer();
						buffer.append(s2);

					}
				} else {
					buffer.append(_buffer.toString());
					_buffer = new StringBuffer();
				}

			}

		} catch (IOException e) {
			return null;
		}
		return buffer.append(_buffer).toString();
	}

	/**
	 * 功能:将str的第一个字母大写.
	 * 
	 * @param str
	 * @return
	 */
	final public static String toUpcaseFirstChar(String str) {
		if (str == null || str.length() < 1)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String getTimeString1(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static Date getStringTime1(String dateStr) {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getTimeString2(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	public static Date getStringTime2(String dateStr) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getTimeString3(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static Date getStringTime3(String dateStr) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 功能:在list页面中截取前面几个字符.
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getIndexString(String str, int len) {
		if (str == null)
			return "";
		String indexString = str;
		byte[] b = str.getBytes();

		if (b.length > len) {
			int i = len / 2;
			while ((str.substring(0, i) + "...").getBytes().length < len) {
				i++;
			}
			indexString = str.substring(0, i - 1) + "...";
		}
		return indexString;
	}
	
	
	public static String getsubString(String str, int len) {
		String indexString = str;
		if(indexString==null) indexString = "";
		if(indexString.length()>len){
			indexString = indexString.substring(0,len);
		}
		return indexString;
	}

	public static String arrayToString(String[] strs, String split) {
		if (strs == null)
			return "";
		String tmp = "";
		for (int i = 0; i < strs.length; i++) {
			tmp += strs[i] + ",";
		}
		if (tmp.endsWith(","))
			tmp = tmp.substring(0, tmp.length() - 1);
		return tmp;
	}

	//来自smartupload
	public static String toUtf8String(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= '\377') {
				stringbuffer.append(c);
			} else {
				byte abyte0[];
				try {
					abyte0 = String.valueOf(c).getBytes("utf-8");
				} catch (Exception exception) {
					abyte0 = new byte[0];
				}
				for (int j = 0; j < abyte0.length; j++) {
					int k = abyte0[j];
					if (k < 0) {
						k += 256;
					}
					stringbuffer.append("%"
							+ Integer.toHexString(k).toUpperCase());
				}

			}
		}

		return stringbuffer.toString();
	}

	public static String EncMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}

	}

	public static String getShowString(String str) {
		return str == null ? "" : str;

	}

	public static void fileCopy(String srcFileName, String tarFileName)
			throws IOException {
		File srcFile = new File(srcFileName);
		File tarFile = new File(tarFileName);
		InputStream inputStream = new FileInputStream(srcFile);
		OutputStream outputStream = new FileOutputStream(tarFile);
		byte[] b = new byte[1024];
		int i = -1;
		while ((i = inputStream.read(b)) > 0) {
			outputStream.write(b, 0, i);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}

	public String getRealPath(HttpServletRequest request, String urlStr) {
		if (urlStr == null)
			return "";
		return request.getSession().getServletContext().getRealPath(urlStr);
	}

	/**
	 * 随机生成6位数的验证码
	 * @return
	 */
	public static int getRandNum() {
		int randNum = 100000 + (int)(Math.random() * ((999999 - 100000) + 1));
		return randNum;
	}
}
