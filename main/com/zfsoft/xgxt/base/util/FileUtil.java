/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-16 ����09:00:54 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ļ�������ع�����
 * @���ߣ� Penghui.Qu
 * @ʱ�䣺 2013-5-16 ����09:00:54
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
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
	 * @����: ��ȡ�ļ�����BASE64����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-16 ����09:03:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param file
	 * @return
	 * @throws IOException
	 *             String ��������
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
	 * @����: ��ȡ�ļ�����BASE64����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-29 ����10:16:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @return
	 * @throws IOException
	 *             String ��������
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
	 * @����: ������ת�ļ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-29 ����10:48:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ins
	 * @param file
	 * void �������� 
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
	 * @����:���Word��ʽ�ļ�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-16 ����02:13:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void ��������
	 * @throws
	 */
	public static void outputWord(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, WORD);
	}

	/**
	 * 
	 * @����:���Excel��ʽ�ļ�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-16 ����02:20:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void ��������
	 * @throws
	 */
	public static void outputExcel(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, EXCEL);
	}

	/**
	 * 
	 * @����:���pdf��ʽ�ļ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-21 ����02:03:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void ��������
	 * @throws
	 */
	public static void outputPdf(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, PDF);
	}

	/**
	 * 
	 * @����: ���ZIP�ļ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-21 ����05:18:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void ��������
	 * @throws
	 */
	public static void outputZip(HttpServletResponse response, File file)
			throws IOException {
		outputFile(response, file, ZIP);
	}

	/**
	 * 
	 * @����: ����ļ���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-24 ����10:36:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param file
	 * @throws IOException
	 *             void ��������
	 * @throws
	 */
	public static void outputFile(HttpServletResponse response, File file)
			throws IOException {
		outputFileStream(new FileInputStream(file), response.getOutputStream());
	}

	/**
	 * 
	 * @����: ����ļ���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-20 ����02:22:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param in
	 * @param out
	 * @throws IOException
	 *             void ��������
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
	 * @����: ����ļ���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-21 ����02:16:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param file
	 * @param type
	 * @throws IOException
	 *             void ��������
	 * @throws
	 */
	public static void outputFile(HttpServletResponse response, File file,
			String type) throws IOException {

		String fileName = new String(file.getName().getBytes("GBK"),
				"ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		
		//////��ԭ�л���������DBF������ʽ
		//��˷����ѱ��ദ���ã�Ϊ����DBF������ʽ���ʴ����ļ���չ�������ж�//20140506 by ligl//////
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
	 * @����: ��struts1 FormFile ����ת��Ϊ File
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-29 ����11:28:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param formFile
	 * @return
	 * @throws IOException
	 *             File ��������
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
	 * @����: ѧ����Ϣ����byte[]תinputStream
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-17 ����03:20:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param buf
	 * @return
	 * InputStream �������� 
	 * @throws
	 */
	public static final InputStream byteToInput(byte[] buf) {  
	        return new ByteArrayInputStream(buf);  
	}  
	
	/**
	 * 
	 * @����:ѧ����Ϣ����inputStreamתbyte[]
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-17 ����03:22:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param inStream
	 * @return
	 * @throws IOException
	 * byte[] �������� 
	 * @throws
	 */
    public static final byte[] inputTobyte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        /**
         * ��ȡ�ֽ��� ��д��ByteArrayOutputStream����
         */
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        /**
         * ת��byte[]����
         */
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    }  
    
    /**
     * 
     * @����:ɾ��ָ���ļ����µ������ļ�,���ɾ���ļ���
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-8-24 ����09:50:32
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param folderPath
     * void �������� 
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
     * @����: ѭ�������ļ��������ļ����ļ��У�����ɾ��
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-8-24 ����10:08:27
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param path
     * @return
     * boolean �������� 
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
	    delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
	    delFolder(path + "/" + tempList[i]);//��ɾ�����ļ���
	    flag = true;
	    }
	    }
	    return flag;
    }
    

}
