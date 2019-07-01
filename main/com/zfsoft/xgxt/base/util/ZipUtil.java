/**
 * @部门:学工产品事业部
 * @日期：2013-5-21 下午02:26:59 
 */  
package com.zfsoft.xgxt.base.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;



/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: ZIP工具类
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-5-21 下午02:26:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZipUtil {

	private static final String ZIP = ".zip";
	private static Log logger = LogFactory.getLog(ZipUtil.class);
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	
	
	/**
	 * @描述: 指定目录压缩
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-21 下午05:11:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param files
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public static File zip(String srcPath){
		
		File srcDir = new File(srcPath);
		if (!srcDir.exists()){
			logger.error("srcPath not exists !");
			return null;
		}
		
		Project project = new Project();
		
		Zip zip = new Zip();
		zip.setProject(project);
		//生成的ZIP文件
		File destFile = new File(TEMP_DIR + File.separator + System.currentTimeMillis()+ ZIP);
		
		zip.setDestFile(destFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(project);
		fileSet.setDir(srcDir);
		//fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
		//fileSet.setExcludes(...); 排除哪些文件或文件夹
		
		zip.addFileset(fileSet);
		zip.execute();
		
		return destFile;
	}

	
	/**
	 * 
	 * @描述: 多个文件压缩
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-21 下午05:11:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param files
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public synchronized static File zip(File... files){
		
		if (files.length == 0){
			logger.error("files can't null ");
			return null;
		}
		
		Project project = new Project();
		Zip zip = new Zip();
		zip.setProject(project);
		//生成的ZIP文件
		File destFile = new File(TEMP_DIR + File.separator + System.currentTimeMillis()+ ZIP);
		
		zip.setDestFile(destFile);
		
		for (File file : files){
			FileSet fileSet = new FileSet();
			fileSet.setProject(project);
			fileSet.setFile(file);
			//fileSet.set
			zip.addFileset(fileSet);
		}
		zip.execute();
		return destFile;
	}
	
}
