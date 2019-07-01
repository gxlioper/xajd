/**
 * @部门:学工产品事业部
 * @日期：2013-7-31 上午08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;




/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为 代码维护
 * @作者： dlq [工号：995]
 * @时间： 2013-7-31 上午08:27:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwdmwhService extends SuperServiceImpl<RcxwdmwhForm, RcxwdmwhDao>  {

	private RcxwdmwhDao dao = new RcxwdmwhDao();
	public RcxwdmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 获得大类信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>>  getXwdlPageList(RcxwdmwhForm model) throws Exception{
		return dao.getXwdlPageList(model);
	}
	
	/**
	 * 
	 * @描述:获得最大序号的日常行为大类代码
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午12:28:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private String changeXwdldm() {
		String max = dao.getMaxXwdldm();
		if(Base.isNull(max)){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	
	/**
	 * 根据大类代码查询大类名称
	 */
	public String getRcxwlbdlmcById(String id) throws Exception {
		return dao.getRcxwlbdlmcById(id);
	}
	
	/**
	 * 保存行为大类信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveXwdlInfo(RcxwdmwhForm model,String type) throws Exception{
		//return dao.saveXwdlInfo(model, type);
		boolean result = false;
		if ("add".equals(type)) {
			String rcxwlbdldm = changeXwdldm();
			model.setRcxwlbdldm(rcxwlbdldm);
			return dao.addXwdlInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateXwdlInfo(model);
		}
		
		
		return result;
		
	}
	
	/**
	 * 
	 * 删除行为大类
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-7 上午09:05:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public int deleteXwdlInfo(String values) throws Exception{
		return dao.deleteXwdlInfo(values);
	}
	
	public String  checkXwdlForXwlb(String values)throws Exception{
    	String resultLbdlmc="";
    	String[] rcxwlbdlmc=dao.checkXwdlForXwlb(values);
    	for(int i=0;i<rcxwlbdlmc.length;i++){
			if(i==rcxwlbdlmc.length-1){
				resultLbdlmc+=rcxwlbdlmc[i];
			}else{
				resultLbdlmc+=rcxwlbdlmc[i]+",";
			}
			
		}
		return resultLbdlmc;
	}

	/** 
	 * 获取审核流未结束的大类
	 */
	public List<HashMap<String, String>> getRcxwdlShwjs(String values) throws Exception{
		return dao.getRcxwdlShwjs(values);
	}
	
	/** 
	 * 获取审核流未结束的类别
	 */
	public List<HashMap<String,String>> getRcxwlbShwjs(String values) throws Exception{
		return dao.getRcxwlbShwjs(values);
	}
	/** 
	 * 更新类别
	 */
	public boolean updateRcxwlbSfqy(RcxwdmwhForm model) throws Exception{
		return dao.updateRcxwlbSfqy(model);
	}
	
	/**
	 * 获得类别信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>>  getXwlbList(RcxwdmwhForm model) throws Exception{
		return dao.getXwlbList(model);
	}
	

	/**
	 * 
	 * @描述:获得最大序号的日常行为大类代码
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午12:28:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private String changeXwlbdm() {
		String max = dao.getMaxXwlbdm();
		if(Base.isNull(max)){
			return "0001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 4-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	
	
	/**
	 * 
	 * @描述:保存行为类别
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午04:04:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXwlbInfo(RcxwdmwhForm model,String type) throws Exception{
		boolean b = false;
//		String rcxwlbfz = model.getRcxwlbfz();
//		String  charone = rcxwlbfz.charAt(0) + "";
//		if(Constants.JIANFEN.equals(model.getRcxwfzlx())){
//			//行为分值类型为减分
//			if(charone.equalsIgnoreCase("-") || charone.equalsIgnoreCase("+")){
//				String rcxwlbfzjian = rcxwlbfz.substring(1, rcxwlbfz.length());
//				model.setRcxwlbfz("-" + rcxwlbfzjian);
//			}else{
//				model.setRcxwlbfz("-"+model.getRcxwlbfz());
//			}
//		}else{
//			//行为分值类型为加分
//			if(charone.equalsIgnoreCase("-") || charone.equalsIgnoreCase("+")){
//				String rcxwlbfzjia =  rcxwlbfz.substring(1, rcxwlbfz.length());
//				model.setRcxwlbfz("+" + rcxwlbfzjia);
//			}else{
//				model.setRcxwlbfz("+" + model.getRcxwlbfz());
//			}
//		}
		if("add".equals(type)){
			String rcxwlbdm = changeXwlbdm();
			model.setRcxwlbdm(rcxwlbdm);
			b= dao.addXwlbInfo(model, type);
		}else if("update".equals(type)){
			b= dao.updateXwlbInfo(model, type);
		}
		
		
		return b;
		
	}
	
	
	/**
	 * 
	 * 行为类别集合
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午03:30:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXwlbListMap(RcxwdmwhForm model) throws Exception{
		return dao.getXwlbListMap(model);
	}
	
	public RcxwdmwhForm getRcxwdmwhForm(RcxwdmwhForm t ,String rcxwlbdm) throws Exception{
		
		return dao.getRcxwdmwhForm(t,rcxwlbdm);
	}
	
	
	/**
	 * 
	 * 删除行为大类
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-7 上午09:05:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public int deleteXwlbInfo(String values) throws Exception{
		
		return dao.deleteXwlbInfo(values);
	}
	
	/**
	 * 
	 * 判断行为类别是否被行为维护应用
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午03:53:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String  checkXwlbForXwwh(String values)throws Exception{
    	String resultLbmc="";
    	String[] rcxwlbmc=dao.checkXwlbForXwwh(values);
    	for(int i=0;i<rcxwlbmc.length;i++){
			if(i==rcxwlbmc.length-1){
				resultLbmc+=rcxwlbmc[i];
			}else{
				resultLbmc+=rcxwlbmc[i]+",";
			}
			
		}
		return resultLbmc;
	}
	
	/**
	 * 
	 * @描述:获取日常行为大类列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-14 上午09:00:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcxwdlList(){
		return dao.getRcxwdlList();
	}
	
}
