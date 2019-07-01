/**
 * @部门:学工产品事业部
 * @日期：2016-6-17 下午04:14:42 
 */  
package com.zfsoft.xgxt.zjly.zhf.sq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 综合分(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-17 下午04:14:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfService extends SuperServiceImpl<ZhfForm, ZhfDao>{
	private ZhfDao dao = new ZhfDao();
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/** 
	 * @描述:得到项目模块列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-20 上午09:54:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmmkList(){
		return dao.getXmmkList();
	}
	
	/** 
	 * @描述:验证存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-20 上午10:49:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(List<ZhfForm> list){
		return dao.countZhf(list)>0;
	}
	
	/** 
	 * @描述:得到计分项目列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-20 下午01:34:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmListByMkid(String mkid){
		return dao.getJfxmListByMkid(mkid);
	}
	
	/** 
	 * @描述:得到分数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：12282]
	 * @日期：2016-6-20 下午03:12:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getFs(String id){
		return dao.getFs(id);
	}
	
	/** 
	 * @描述:保存(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-21 下午03:18:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveZhf(ZhfForm model) throws Exception{
		List<ZhfForm> list = new ArrayList<ZhfForm>();
		//附件
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		String[] jfxms = model.getJfxmdms();
		String[] xmdms = model.getXmmkdms();
		String[] sxsms = model.getSxsms();
		String[] cysjs = model.getCysjs();
		for(int i = 0;i<jfxms.length;i++){
			ZhfForm form = new ZhfForm();
			form.setXh(model.getXh());
			form.setXmmkdm(xmdms[i]);
			form.setJfxmdm(jfxms[i]);
			form.setSxsm(sxsms[i]);
			form.setCysj(cysjs[i]);
			form.setLrr(model.getXh());
			form.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			
			FormFile file = (FormFile) files.get("fj"+i);
			if (file != null && !StringUtil.isNull(file.getFileName())){
				String basePath = resource.getString("filesys.local.dir");
				//如果没有给定文件存储路径，就默认给系统用户目录
				if(StringUtils.isNull(basePath)){
					basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
				}
				String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
				
				if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
					File srcFile = FileUtil.conversionFormFile(file);
					File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
					FileUtils.copyFile(srcFile, destFile);
					form.setFj(destFile.getAbsolutePath());
					form.setFjmc(file.getFileName());
				}
			}
			else{
				form.setFj("");
				form.setFjmc("");
			}
			list.add(form);
		}
		if(isExist(list)){
			return false;
		}else{
			return dao.plSave(list);			
		}		
	}
	
	
	public ZhfForm getModel(ZhfForm t) throws Exception{
		ZhfForm form = super.getModel(t);
		HashMap<String,String> map = dao.getResult(t.getId());
		form.setJfxmmc(map.get("jfxmmc"));
		form.setXmmkmc(map.get("xmmkmc"));
		form.setFj(map.get("fj"));
		form.setFs(map.get("fs"));
		form.setKhyd(map.get("khyd"));
		return form;
	}
	
	/** 
	 * @描述:获取考核要点(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-21 下午03:19:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getKhyd(String id){
		return dao.getKhyd(id);
	}
	
	/** 
	 * @描述:判断是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-21 下午03:54:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistForUpdate(ZhfForm t){
		return Integer.valueOf(dao.countZhfSq(t))>0;
	}
}
