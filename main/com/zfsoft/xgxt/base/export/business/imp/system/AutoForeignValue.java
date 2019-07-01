/**
 * @部门:学工产品事业部
 * @日期：2014-6-13 下午02:50:37 
 */  
 
package com.zfsoft.xgxt.base.export.business.imp.system;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2014-6-13 下午02:50:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class AutoForeignValue extends BusinessExtend{
	private final static String AUTOFOREIGNVALUE = "AutoForeignValue";

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param drmkdm
	 * @param kzdm
	 */
	public AutoForeignValue(String drmkdm) {
		super(drmkdm, AUTOFOREIGNVALUE);
	}

	@Override
	protected String check(String message, List<ImportModel> data) {
		return this._YZTG;
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.export.business.BusinessExtend#formartData(java.util.List, java.util.List)
	 */
	
	@Override
	public HashMap<String, Object> formartData(List<List<ImportModel>> success, List<String[]> succeedExcelDataList,
			List<String[]> error) {
		String xydm="";
		String zydm="";
		String syd="";
		for (List<ImportModel> list : success) {
			for(ImportModel im:list){
				//设置对应名称的代码
				if(im.getZdm().equalsIgnoreCase("xydm")){
					xydm=im.getValue().toString();
				}else if(im.getZdm().equalsIgnoreCase("zydm")){
					zydm=im.getValue().toString();
				}else if(im.getZdm().equalsIgnoreCase("syd")){
					syd=im.getValue().toString();
					im.setValue(getSyd(syd));
				}
				if(im.getZdm().equalsIgnoreCase("xy")){
					im.setValue(getXy(xydm));
				}else if(im.getZdm().equalsIgnoreCase("zymc")){
					im.setValue(getZymc(zydm));
				}
			}
		}
		return super.formartData(success, succeedExcelDataList, error);
	}
	public String getSyd(String syd){
		String sql = " select sydmc from SYDDMB where syddm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{syd}, "sydmc");
	}
	public String getXy(String xydm){
		String sql = " select bmmc from ZXBZ_XXBMDM where bmdm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{xydm}, "bmmc");
	}
	public String getZymc(String zydm){
		String sql = " select zymc from BKS_ZYDM where zydm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{zydm}, "zymc");
	}
}
