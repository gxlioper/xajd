/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-21 ����02:26:59 
 */  
package com.zfsoft.xgxt.base.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ZIP������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-5-21 ����02:26:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZipUtil {

	private static final String ZIP = ".zip";
	private static Log logger = LogFactory.getLog(ZipUtil.class);
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	
	
	/**
	 * @����: ָ��Ŀ¼ѹ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-21 ����05:11:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param files
	 * @return
	 * File �������� 
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
		//���ɵ�ZIP�ļ�
		File destFile = new File(TEMP_DIR + File.separator + System.currentTimeMillis()+ ZIP);
		
		zip.setDestFile(destFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(project);
		fileSet.setDir(srcDir);
		//fileSet.setIncludes("**/*.java"); ������Щ�ļ����ļ��� eg:zip.setIncludes("*.java");
		//fileSet.setExcludes(...); �ų���Щ�ļ����ļ���
		
		zip.addFileset(fileSet);
		zip.execute();
		
		return destFile;
	}

	
	/**
	 * 
	 * @����: ����ļ�ѹ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-21 ����05:11:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param files
	 * @return
	 * File �������� 
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
		//���ɵ�ZIP�ļ�
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
