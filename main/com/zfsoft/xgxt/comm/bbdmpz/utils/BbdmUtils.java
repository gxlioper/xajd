/**
 * @部门:学工产品事业部
 * @日期：2013-12-26 下午02:31:27 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-26 下午02:31:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbdmUtils implements Constants{

	public static final String BASE_DIR_TEMPLATE = "报表代码为空！";
	
	public static final String BASE_DIR_TP = "报表代码为空！";
	
	public  static final String BB_NOT_FOUND = "表格不存在，请先配置！";
	
	public static final String BBDM_IS_NULL = "报表代码为空！";
	 
	public static final String GUID_IS_NULL = "guid为空！";
	
	
	private static final BbdmService bbdmService = new BbdmService();
	
	/**
	 * 
	 * @描述:单个表格下载
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-2 下午04:36:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File 返回类型 
	 * @throws
	 */
	public static File getSigleFile(String guid , Map<String , Object> data) throws FileNotFoundException{
		HashMap<String , String> bbdmInfo = bbdmService.getBbmbInfo(guid);
		String templatePath = Constants.TEP_DIR +bbdmInfo.get("mblj") + "/" + bbdmInfo.get("mbmc") ;
		return getWord(data, templatePath, bbdmInfo);
	}
	
	/**
	 * 
	 * @描述:多个表格下载
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-2 下午04:36:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @param datas
	 * @return
	 * @throws FileNotFoundException
	 * List<File> 返回类型 
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
