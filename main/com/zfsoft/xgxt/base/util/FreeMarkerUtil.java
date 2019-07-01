/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-15 ����10:55:01 
 */  
package com.zfsoft.xgxt.base.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: FreeMarker���ģ�� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-5-15 ����10:55:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FreeMarkerUtil {

	
	private static final String ENCODING = "UTF-8";
	private static final String WORD_TYPE = ".doc";
	private static final String EXCEL_TYPE = ".xls";
	private static final String TEMP_DIR = "java.io.tmpdir";
	private static Configuration configuration = new Configuration();
	
	
	static {
		configuration.setDefaultEncoding(ENCODING);
	}
	
	
	
	
	
	/**
	 * 
	 * @����: ����word�ĵ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-23 ����09:42:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @param templateDirectory
	 * @param templateName
	 * @param fileName
	 * @return
	 * File �������� 
	 * @throws
	 */
	public static synchronized File getWordFile(Map<String,Object> data,String templateDirectory,String templateName,String fileName){
		Writer out = null;
		try {
			Template template = null;
			//����ģ��λ��
			configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile(templateDirectory));
			//ģ���ļ���
			template=configuration.getTemplate(templateName);
			File wordFile = new File(System.getProperty(TEMP_DIR)+"/"+fileName+ WORD_TYPE);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(wordFile), ENCODING));
			template.process(data, out);
			return wordFile;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @����:����word�ļ�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-15 ����10:58:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @param template
	 * @return
	 * File �������� 
	 * @throws
	 */
	
	public static File getWordFile(Map<String,Object> data,String templateDirectory,String templateName){
		
		String fileName = String.valueOf(System.currentTimeMillis());
		
		return getWordFile(data, templateDirectory, templateName, fileName);
	}
	
	
	
	/**
	 * 
	 * @����:������صǼǱ��޸��ļ������ļ���ͳһ�������ں�׺���жϵ�ǰ�ļ��Ƿ���ڣ������ڣ����ļ���ƴ�ӱ�ţ�1,2,3,��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-8 ����03:14:40
	 * @�޸ļ�¼: 
	 * @param xh
	 * @param xm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getFileName(String fileName){
		File wordFile = null;
		//�ļ���ͳһ�������ں�׺���жϵ�ǰ�ļ��Ƿ���ڣ������ڣ����ļ���ƴ�ӱ�ţ�1,2,3,��������
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String curDate = df.format(new Date());
		fileName = fileName + "_"+curDate;
		String fileFullName = System.getProperty(TEMP_DIR)+"/"+fileName + WORD_TYPE;
		String bs = "";//��׺��ʶ
		int i = 1;
		while(true){
			wordFile = new File(fileFullName);
			if(wordFile.exists()){
				bs = "_" +  i;
				fileFullName = System.getProperty(TEMP_DIR)+"/"+fileName  + bs+ WORD_TYPE;
				i++;
			}else{
				break;
			}
		}
		return fileName + bs;
	}
	

	
	/**
	 * 
	 * @����:����excel�ļ�
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-13  ����17:03:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @param template
	 * @return
	 * File �������� 
	 * @throws
	 */
	
	public static File getExcelFile(Map<String,Object> data,String templateDirectory,String templateName){
		
		String fileName = String.valueOf(System.currentTimeMillis());
		
		return getExcelFile(data, templateDirectory, templateName, fileName);
	}
	/**
	 * 
	 * @����:����excel�ļ�
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-13  ����17:03:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @param templateDirectory
	 * @param templateName
	 * @param fileName
	 * @return
	 * File �������� 
	 * @throws
	 */
	public static synchronized File getExcelFile(Map<String,Object> data,String templateDirectory,String templateName,String fileName){
		Writer out = null;
		try {
			Template template = null;
			//����ģ��λ��
			configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile(templateDirectory));
			//ģ���ļ���
			template=configuration.getTemplate(templateName);
			File excelFile = new File(System.getProperty(TEMP_DIR)+"/"+fileName+ EXCEL_TYPE);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(excelFile), ENCODING));
			template.process(data, out);
			return excelFile;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * @����:������صǼǱ��޸��ļ������ļ���ͳһ�������ں�׺���жϵ�ǰ�ļ��Ƿ���ڣ������ڣ����ļ���ƴ�ӱ�ţ�1,2,3,��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-8 ����03:14:40
	 * @�޸ļ�¼: 
	 * @param xh
	 * @param xm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getExcelFileName(String fileName){
		File excelFile = null;
		//�ļ���ͳһ�������ں�׺���жϵ�ǰ�ļ��Ƿ���ڣ������ڣ����ļ���ƴ�ӱ�ţ�1,2,3,��������
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String curDate = df.format(new Date());
		fileName = fileName + "_"+curDate;
		String fileFullName = System.getProperty(TEMP_DIR)+"/"+fileName + EXCEL_TYPE;
		String bs = "";//��׺��ʶ
		int i = 1;
		while(true){
			excelFile = new File(fileFullName);
			if(excelFile.exists()){
				bs = "_" +  i;
				fileFullName = System.getProperty(TEMP_DIR)+"/"+fileName  + bs+ EXCEL_TYPE;
				i++;
			}else{
				break;
			}
		}
		return fileName + bs;
	}
}
