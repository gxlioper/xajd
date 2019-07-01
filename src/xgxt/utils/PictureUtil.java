package xgxt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
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
	public static String getPicPath( String dir,String[] id, String subPath) {
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

	private static String get_OS() {
		Map<String, String> map = System.getenv();
		// System.out.println(map.get("OS"));
		if (map.get("OS").contains("Windows")) {
			return "Windows";
		} else {
			return "Linux";
		}
	}
	
//
//	private static String[] PICTURES = new String[]{"PNG","JPG","GIF","BMP","JPEG","png","jpg","jpeg","bmp","gif"};
//	private static PictureUtil pictureUtil = null;
//	//get instance
//	public static PictureUtil getInstance(){
//		if(pictureUtil == null){
//			pictureUtil = new PictureUtil();
//		}
//		return pictureUtil;
//	}
//
//	private static final String rootPath = "pictures\\";
//	/**
//	 * 根据唯一标识返回学生的信息
//	 * @param id  <font>unique,可以是学号，或者是考生号，或者是身份证号</font>
//	 * @param filePath
//	 * @return String
//	 * @throws IOException 
//	 */
//	public String getPicture(String[] id,String dir) throws IOException{
//		//create dir
//		File rootFile = new File(dir);
//		if(!rootFile.exists()){
//			rootFile.mkdirs();
//		}	
//		for(String subFormat : PICTURES){
//			for(int i=0;i<id.length;i++){
//				if(!StringUtils.isNull(id[i])){	
//					String filePath = dir + id[i] + "." +  subFormat;
//					if(new File(filePath).exists()){
//						File file = new File(filePath);
//						String str = file.getCanonicalPath();
//						return  rootPath + id[i]+str.substring(str.lastIndexOf("."),str.length());
//					}
//				}	
//			}	
//		}
//		return null;
//	}
//	public String getPicPath(String realpath,String[] id,String subPath) throws IOException{
//		//String[] id = new String[]{ksh,map.get("xh"),map.get("sfzh")};
//		//String filepath = request.getSession().getServletContext().getRealPath("pictures\\yxgl");
//		String filepath = getPicture(id, realpath + "\\" + rootPath + (StringUtils.isNull(subPath) ? "" : subPath));	
//		return StringUtils.isNull(filepath) ? "" : filepath;		
//	}
//	
	
	/**图片批量上传*/
	public  static List<String> uploadPhoto(String path) throws Exception{
//		String path = "E:\\Media";
//		String[] str = FindFile.getFileName(path);
		List<String> rs =new java.util.ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
//		HashMap<String,String> rsMap = new HashMap<String,String>();
//		HashMap<String,String> rsMap1 = new HashMap<String,String>();
//		HashMap<String,String> rsMap2 = new HashMap<String,String>();
		File fileOne = new File(path); 
		String[] str = fileOne.list();
		String xh = "";
		FileInputStream inStream = null;
		String sql = "";
		DAO dao = new DAO();
		ZipUtils zipUtil = new ZipUtils();
		String fileType = ".jpg.gif.png.bmp.JPG.GJF.PNG.BMP";
		for(int i=0; i<str.length; i++){
			if(str[i].length()<4||fileType.indexOf(str[i].substring(str[i].length()-4,str[i].length()))==-1){
				sb2.append(str[i]+" 不是图片或者图片格式不匹配\n");
				continue;
			}
			File file = new File(path+str[i]);
			if(file.length()>=100*1024){
				sb1.append(str[i]+" 导入失败：文件过大(大于100K)\n");
				continue;
			}
			inStream = new FileInputStream(path+str[i]);
			xh = str[i].substring(0,str[i].indexOf(".")).replaceAll(" ", "");
			xh =xh.toLowerCase();
			List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
			sql = "select xh bmh from view_xsjbxx where xh = ? ";
			list=dao.getListNotOut(sql, new String[]{xh});
			  
			if(list==null||list.size()==0||list.size()!=1){
				sb2.append(str[i]+" 导入失败：因为数据库中没有该学号\n");
			}else {
			xh = list.get(0).get("bmh");
			sql = "select count(*) num  from XSZPB where xh = ?";
			String count = dao.getOneRs(sql, new String[]{xh},"num");
			if("1".equalsIgnoreCase(count)){
				try {
					dao.upBlob("update XSZPB set zp = ? where xh = ?", xh, inStream.available(), inStream);
//					System.out.println(xh);
					sb.append(str[i]+"\n");
				} catch (Exception e) {
					sb1.append(str[i]+"\n");
					e.printStackTrace();
				}
			}else if("0".equalsIgnoreCase(count)){
				try {
					dao.upBlob("insert into XSZPB(zp,xh) values(?,?)",xh, inStream.available(),inStream);
//					System.out.println(xh);
					sb.append(str[i]+"\n");
				} catch (Exception e) {
					sb1.append(str[i]+"\n");
					e.printStackTrace();
				}
			}else{
				sb1.append(str[i]+" 导入失败：因为数据库中有多个重复数据\n");
			}
			}
		}
		rs.add(sb.toString());
		rs.add(sb1.toString());
		rs.add(sb2.toString());
		
		if(inStream!=null){
			inStream.close();
		}
		
		zipUtil.deleteFile(fileOne.getParent());
		return rs;
	}
}
