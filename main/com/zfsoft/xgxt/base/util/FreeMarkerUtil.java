/**
 * @部门:学工产品事业部
 * @日期：2013-5-15 上午10:55:01 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: FreeMarker填充模版 
 * @作者： Penghui.Qu 
 * @时间： 2013-5-15 上午10:55:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
	 * @描述: 生成word文档
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 上午09:42:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @param templateDirectory
	 * @param templateName
	 * @param fileName
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public static synchronized File getWordFile(Map<String,Object> data,String templateDirectory,String templateName,String fileName){
		Writer out = null;
		try {
			Template template = null;
			//设置模版位置
			configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile(templateDirectory));
			//模版文件名
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
	 * @描述:生成word文件
	 * @作者：Penghui.Qu
	 * @日期：2013-5-15 上午10:58:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @param template
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	
	public static File getWordFile(Map<String,Object> data,String templateDirectory,String templateName){
		
		String fileName = String.valueOf(System.currentTimeMillis());
		
		return getWordFile(data, templateDirectory, templateName, fileName);
	}
	
	
	
	/**
	 * 
	 * @描述:针对下载登记表，修改文件名。文件名统一增加日期后缀，判断当前文件是否存在，若存在，则文件名拼接编号，1,2,3,。。。。
	 * @作者：ligl
	 * @日期：2013-11-8 下午03:14:40
	 * @修改记录: 
	 * @param xh
	 * @param xm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getFileName(String fileName){
		File wordFile = null;
		//文件名统一增加日期后缀，判断当前文件是否存在，若存在，则文件名拼接编号，1,2,3,。。。。
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String curDate = df.format(new Date());
		fileName = fileName + "_"+curDate;
		String fileFullName = System.getProperty(TEMP_DIR)+"/"+fileName + WORD_TYPE;
		String bs = "";//后缀标识
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
	 * @描述:生成excel文件
	 * @作者：wanghj
	 * @日期：2014-1-13  下午17:03:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @param template
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	
	public static File getExcelFile(Map<String,Object> data,String templateDirectory,String templateName){
		
		String fileName = String.valueOf(System.currentTimeMillis());
		
		return getExcelFile(data, templateDirectory, templateName, fileName);
	}
	/**
	 * 
	 * @描述:生成excel文件
	 * @作者：wanghj
	 * @日期：2014-1-13  下午17:03:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @param templateDirectory
	 * @param templateName
	 * @param fileName
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public static synchronized File getExcelFile(Map<String,Object> data,String templateDirectory,String templateName,String fileName){
		Writer out = null;
		try {
			Template template = null;
			//设置模版位置
			configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile(templateDirectory));
			//模版文件名
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
	 * @描述:针对下载登记表，修改文件名。文件名统一增加日期后缀，判断当前文件是否存在，若存在，则文件名拼接编号，1,2,3,。。。。
	 * @作者：ligl
	 * @日期：2013-11-8 下午03:14:40
	 * @修改记录: 
	 * @param xh
	 * @param xm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getExcelFileName(String fileName){
		File excelFile = null;
		//文件名统一增加日期后缀，判断当前文件是否存在，若存在，则文件名拼接编号，1,2,3,。。。。
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String curDate = df.format(new Date());
		fileName = fileName + "_"+curDate;
		String fileFullName = System.getProperty(TEMP_DIR)+"/"+fileName + EXCEL_TYPE;
		String bs = "";//后缀标识
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
