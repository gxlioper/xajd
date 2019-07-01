/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 上午09:00:54 
 */
package com.zfsoft.xgxt.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import sun.misc.BASE64Encoder;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 文件操作相关工具类
 * @作者： Penghui.Qu
 * @时间： 2013-5-16 上午09:00:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FileUtil {

	public static final String WORD = "application/vnd.ms-word";
	public static final String EXCEL = "application/vnd.ms-excel";
	public static final String PDF = "application/pdf";
	public static final String ZIP = "application/zip";
	public static final String DBF = "application/dbf";
	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

	/**
	 * 
	 * @描述: 获取文件流的BASE64编码
	 * @作者：Penghui.Qu
	 * @日期：2013-5-16 上午09:03:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param file
	 * @return
	 * @throws IOException
	 *             String 返回类型
	 * @throws IOException
	 */
	public static String getBASE64(File file) throws IOException {

		if (file == null || !file.exists()) {
			return null;
		}
		InputStream in = new FileInputStream(file);
		return getBASE64(in);
	}

	/**
	 * 
	 * @描述: 获取文件流的BASE64编码
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-29 上午10:16:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @return
	 * @throws IOException
	 *             String 返回类型
	 * @throws
	 */
	public static String getBASE64(InputStream is) throws IOException {

		if (is == null) {
			return null;
		}

		byte[] bytes = null;
		try {
			bytes = new byte[is.available()];
			is.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			is.close();
		}

		BASE64Encoder encoder = new BASE64Encoder();
		return bytes != null ? encoder.encode(bytes) : null;

	}

	
	/**
	 * 
	 * @描述: 输入流转文件
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-29 上午10:48:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ins
	 * @param file
	 * void 返回类型 
	 * @throws
	 */
	public static File inputstreamToFile(InputStream in,String type) {
		
		if (in == null){
			return null;
		}
		
		File file = new File(TEMP_DIR +"/temp."+type);
		
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}

	/**
	 * 
	 * @描述:输出Word格式文件
	 * @作者：Penghui.Qu
	 * @日期：2013-5-16 下午02:13:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void 返回类型
	 * @throws
	 */
	public static void outputWord(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, WORD);
	}

	/**
	 * 
	 * @描述:输出Excel格式文件
	 * @作者：Penghui.Qu
	 * @日期：2013-5-16 下午02:20:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void 返回类型
	 * @throws
	 */
	public static void outputExcel(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, EXCEL);
	}

	/**
	 * 
	 * @描述:输出pdf格式文件
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-21 下午02:03:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void 返回类型
	 * @throws
	 */
	public static void outputPdf(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, PDF);
	}

	/**
	 * 
	 * @描述: 输出ZIP文件
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-21 下午05:18:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void 返回类型
	 * @throws
	 */
	public static void outputZip(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, ZIP);
	}

	/**
	 * 
	 * @描述: 输出文件流
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-24 上午10:36:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void 返回类型
	 * @throws
	 */
	public static void outputFile(HttpServletResponse response, File file)
			throws IOException {
		outputFileStream(new FileInputStream(file), response.getOutputStream());
	}

	/**
	 * 
	 * @描述: 输出文件流
	 * @作者：Penghui.Qu
	 * @日期：2013-5-20 下午02:22:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param in
	 * @param out
	 * @throws IOException
	 *             void 返回类型
	 * @throws
	 */
	public static void outputFileStream(InputStream in, OutputStream out)
			throws IOException {
		try {

			int len;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * 
	 * @描述: 输出文件流
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-21 下午02:16:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param response
	 * @param file
	 * @param type
	 * @throws IOException
	 *             void 返回类型
	 * @throws
	 */
	public static void outputFile(HttpServletResponse response, File file,
			String type) throws IOException {

		String fileName = new String(file.getName().getBytes("GBK"),
				"ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		
		//////在原有基础上增加DBF导出方式
		//因此方法已被多处调用，为增加DBF导出方式，故此用文件扩展名进行判断//20140506 by ligl//////
		String[] fileNames = fileName.split("[.]");
		String wjlx = fileNames[fileNames.length - 1].trim();
		if(wjlx.equalsIgnoreCase("dbf")){
			type = DBF;
		}
		///////////////////////////////////////////////////////////////
		
		response.setContentType(type);

		OutputStream out = null;
		InputStream in = null;

		out = response.getOutputStream();
		in = new FileInputStream(file);
		outputFileStream(in, out);

	}

	/**
	 * 
	 * @描述: 将struts1 FormFile 对象转换为 File
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 上午11:28:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param formFile
	 * @return
	 * @throws IOException
	 *             File 返回类型
	 */
	public static File conversionFormFile(FormFile formFile) throws IOException {

		if (formFile == null)
			return null;

		int bytesRead = 0;
		int size = formFile.getFileSize();
		InputStream in = formFile.getInputStream();
		File newFile = new File(TEMP_DIR + "/" + formFile.getFileName());
		OutputStream out = new FileOutputStream(newFile);

		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}

		return newFile;
	}
	
	/**
	 * 
	 * @描述: 学生信息导出byte[]转inputStream
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-17 下午03:20:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param buf
	 * @return
	 * InputStream 返回类型 
	 * @throws
	 */
	public static final InputStream byteToInput(byte[] buf) {  
	        return new ByteArrayInputStream(buf);  
	}  
	
	/**
	 * 
	 * @描述:学生信息导出inputStream转byte[]
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-17 下午03:22:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param inStream
	 * @return
	 * @throws IOException
	 * byte[] 返回类型 
	 * @throws
	 */
    public static final byte[] inputTobyte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        /**
         * 读取字节流 ，写入ByteArrayOutputStream对象
         */
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        /**
         * 转成byte[]数组
         */
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    }  
    
    /**
     * 
     * @描述:删除指定文件夹下的所有文件,最后删除文件夹
     * @作者：yxy[工号：1206]
     * @日期：2016-8-24 上午09:50:32
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param folderPath
     * void 返回类型 
     * @throws
     */
    public static void delFolder(String folderPath) {
	    try {
		    delAllFile(folderPath);
		    File fileforder = new File(folderPath);
		    if(fileforder.exists()){
		    	fileforder.delete();
		    }
	    } catch (Exception e) {
	    	e.printStackTrace(); 
	    }
    }

    /**
     * 
     * @描述: 循环遍历文件下所有文件和文件夹，进行删除
     * @作者：yxy[工号：1206]
     * @日期：2016-8-24 上午10:08:27
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param path
     * @return
     * boolean 返回类型 
     * @throws
     */
    public static boolean delAllFile(String path) {
	    boolean flag = false;
	    File file = new File(path);
	    if (!file.exists()) {
	    return flag;
	    }
	    if (!file.isDirectory()) {
	    return flag;
	    }
	    String[] tempList = file.list();
	    File temp = null;
	    for (int i = 0; i < tempList.length; i++) {
	    if (path.endsWith(File.separator)) {
	    temp = new File(path + tempList[i]);
	    } else {
	    temp = new File(path + File.separator + tempList[i]);
	    }
	    if (temp.isFile()) {
	    temp.delete();
	    }
	    if (temp.isDirectory()) {
	    delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	    delFolder(path + "/" + tempList[i]);//再删除空文件夹
	    flag = true;
	    }
	    }
	    return flag;
    }
    

}
