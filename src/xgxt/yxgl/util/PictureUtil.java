package xgxt.yxgl.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import xgxt.utils.String.StringUtils;

public class PictureUtil {

	private static String[] PICTURES = new String[] { "gif", "jpg", "jpeg",
			"png", "bmp", };

	private static PictureUtil pictureUtil = null;

	// get instance
	public static PictureUtil getInstance() {
		if (pictureUtil == null) {
			pictureUtil = new PictureUtil();
		}
		return pictureUtil;
	}

	private static final String ROOTPATH = "pictures";

	/**
	 * 根据唯一标识返回学生的信息
	 * 
	 * @param id
	 *            <font>unique,可以是学号，或者是考生号，或者是身份证号</font>
	 * @param filePath
	 * @return String
	 * @throws IOException
	 */
	public static String getPicture(String[] id, String dir, String subPath) {
		if (StringUtils.isNotNull(subPath)) {
			File subFile = new File(subPath);
			if (!subFile.exists()) {
				subFile.mkdirs();
			}
		} else {
			subPath = "";
		}
		String fileRoot = dir + "\\" + ROOTPATH + "\\" + subPath + "\\";
		if (get_OS().equals("Linux")) {
			fileRoot = fileRoot.replaceAll("\\", "/");
		}
		for (String subFormat : PICTURES) {
			for (int i = 0; i < id.length; i++) {
				if (StringUtils.isNotNull(id[i])) {
					String filePath = fileRoot + id[i] + "." + subFormat;
					// System.out.println(filePath);
					File file = new File(filePath);
					if (file.exists() && file.isFile()) {
						String anonicalPath = "";
						try {
							anonicalPath = file.getCanonicalPath();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return ROOTPATH
								+ "/"
								+ (StringUtils.isNotNull(subPath) ? (subPath + "/")
										: "")
								+ id[i]
								+ anonicalPath.substring(anonicalPath
										.lastIndexOf("."));
					}
				}
			}
		}
		return "";
	}

	public static void main(String[] args) {
		/*
		 * Map<String,String> map = System.getenv(); for(Map.Entry<String,String>
		 * entry : map.entrySet()){ System.out.println(entry.getKey() + " ----- " +
		 * entry.getValue()); }
		 */

		// getInstance().get_OS();
	}

	private static String get_OS() {
		Map<String, String> map = System.getenv();
		// System.out.println(map.get("OS"));
		if (map.get("OS").contains("Windows")) {
			return "Windows";
		} else {
			return "Linux";
		}
	}

}
