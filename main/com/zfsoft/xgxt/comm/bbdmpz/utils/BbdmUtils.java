/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-26 ����02:31:27 
 */  
package com.zfsoft.xgxt.comm.bbdmpz.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.comm.bbdmpz.service.BbdmService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-26 ����02:31:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbdmUtils implements Constants{

	public static final String BASE_DIR_TEMPLATE = "�������Ϊ�գ�";
	
	public static final String BASE_DIR_TP = "�������Ϊ�գ�";
	
	public  static final String BB_NOT_FOUND = "��񲻴��ڣ��������ã�";
	
	public static final String BBDM_IS_NULL = "�������Ϊ�գ�";
	 
	public static final String GUID_IS_NULL = "guidΪ�գ�";
	
	
	private static final BbdmService bbdmService = new BbdmService();
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-2 ����04:36:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File �������� 
	 * @throws
	 */
	public static File getSigleFile(String guid , Map<String , Object> data) throws FileNotFoundException{
		HashMap<String , String> bbdmInfo = bbdmService.getBbmbInfo(guid);
		String templatePath = Constants.TEP_DIR +bbdmInfo.get("mblj") + "/" + bbdmInfo.get("mbmc") ;
		return getWord(data, templatePath, bbdmInfo);
	}
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-2 ����04:36:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @param datas
	 * @return
	 * @throws FileNotFoundException
	 * List<File> �������� 
	 * @throws
	 */
	public static List<File> getMutiFiles(String guid , List<HashMap<String , Object>> datas) throws FileNotFoundException{
		HashMap<String , String> bbdmInfo = bbdmService.getBbmbInfo(guid);
		String templatePath = Constants.TEP_DIR +bbdmInfo.get("mblj") + "/" + bbdmInfo.get("mbmc") ;
		
		List<File> files = new ArrayList<File>();
		
		for (HashMap<String, Object> data : datas) {
			files.add(getWord(data, templatePath, bbdmInfo));
		}
		
		return files;
	}
	
	
	
	
	private static File getWord(Map<String, Object> data , String templatePath , HashMap<String , String> bbdmInfo) throws FileNotFoundException {
		

		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, TEP_DIR + bbdmInfo.get("mblj"), bbdmInfo.get("mbmc"), FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}

		return wordFile;
	
	}
	
}
