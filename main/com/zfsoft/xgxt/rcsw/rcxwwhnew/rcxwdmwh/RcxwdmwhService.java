
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * 日常行为 代码维护
 */
public class RcxwdmwhService extends SuperServiceImpl<RcxwdmwhForm, RcxwdmwhDao>  {

	private RcxwdmwhDao dao = new RcxwdmwhDao();
	public RcxwdmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 获得大类信息
	 */
	public List<HashMap<String, String>>  getRcxwdlPageList(RcxwdmwhForm model, User user) throws Exception{
		return dao.getRcxwdlPageList(model, user);
	}
	
	/**
	 * 根据大类代码查询大类名称
	 */
	public String getRcxwlbdlmcById(String id) throws Exception {
		return dao.getRcxwlbdlmcById(id);
	}
	/**
	 * 保存行为大类信息
	 */
	public boolean saveRcxwdlInfo(RcxwdmwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String rcxwlbdldm = changeRcxwdldm();
			model.setRcxwlbdldm(rcxwlbdldm);
			return dao.addRcxwdlInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateRcxwdlInfo(model);
		}
		return result;
		
	}
	/**
	 * 保存行为小类信息
	 */
	public boolean saveRcxwxlInfo(RcxwdmwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String rcxwlbxldm = changeRcxwxldm();
			model.setRcxwlbxldm(rcxwlbxldm);
			return dao.addRcxwxlInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateRcxwxlInfo(model);
		}
		return result;
		
	}
	/**
	 * 删除行为大类
	 */
	public int deleteRcxwdlInfo(String values) throws Exception{
		return dao.deleteRcxwdlInfo(values);
	}
	/**
	 * 检验行为大类代码是否已使用
	 */
	public String  checkRcxwdl(String values)throws Exception{
    	String resultLbdlmc="";
    	String[] rcxwlbdlmc=dao.checkRcxwdl(values);
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
	 */
	public List<HashMap<String, String>>  getRcxwlbList(RcxwdmwhForm model) throws Exception{
		return dao.getRcxwlbList(model);
	}
	/**
	 * 根据用户授权获得大类
	 */
	public List<HashMap<String, String>>  getRcxwlbdlListByYhsq(User user) throws Exception {
		return dao.getRcxwlbdlListByYhsq(user);
	}
	/**
	 * 根据类别代码查询大类
	 */
	public List<HashMap<String, String>>  queryRcxwlbdlListByLbdm(String rcxwlbdm,User user) throws Exception {
		return dao.queryRcxwlbdlListByLbdm(rcxwlbdm,user);
	}
	/**
	 * 根据大类代码查询小类
	 */
	public List<HashMap<String, String>>  queryRcxwlbxlListByDldm(String rcxwlbdldm) throws Exception {
		return dao.queryRcxwlbxlListByDldm(rcxwlbdldm);
	}
	/**
	 * 根据用户授权获得类别
	 */
	public List<HashMap<String, String>>  getRcxwlbListByYhsq(User user) throws Exception{
		return dao.getRcxwlbListByYhsq(user);
	}
	/**
	 * 获得最大序号的日常行为类别代码
	 */
	private String changeRcxwlbdm() {
		String max = dao.getMaxRcxwlbdm();
		if(Base.isNull(max)){
			return "01";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 2-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	/**
	 * 获得最大序号的日常行为大类代码
	 */
	private String changeRcxwdldm() {
		String max = dao.getMaxRcxwdldm();
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
	 * 获得最大序号的日常行为小类代码
	 */
	private String changeRcxwxldm() {
		String max = dao.getMaxRcxwxldm();
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
	 * 保存行为类别
	 */
	public boolean saveRcxwlbInfo(RcxwdmwhForm model,String type) throws Exception{
		boolean b = false;
		if("add".equals(type)){
			String rcxwlbdm = changeRcxwlbdm();
			model.setRcxwlbdm(rcxwlbdm);
			b= dao.addRcxwlbInfo(model, type);
		}else if("update".equals(type)){
			b= dao.updateRcxwlbInfo(model, type);
		}
		return b;
	}
	/**
	 * 行为小类
	 */
	public List<HashMap<String,String>> getRcxwxlList(RcxwdmwhForm model, User user) throws Exception{
		return dao.getRcxwxlList(model, user);
	}
	/**
	 * 查询行为类别
	 */
	public RcxwdmwhForm getRcxwlbForm(RcxwdmwhForm t) throws Exception{
		return dao.getRcxwlbForm(t);
	}
	/**
	 * 查询行为小类
	 */
	public RcxwdmwhForm getRcxwxlForm(RcxwdmwhForm t) throws Exception{
		return dao.getRcxwxlForm(t);
	}
	/**
	 * 删除行为类别信息
	 */
	public int deleteRcxwlbInfo(String values) throws Exception{
		int i = dao.deleteRcxwlbInfo(values);
		dao.deleteRcxwsqbInfo(values);
		return i;
	}
	/**
	 * 删除行为小类信息
	 */
	public int deleteRcxwxlInfo(String values) throws Exception{
		return dao.deleteRcxwxlInfo(values);
	}
	/**
	 * 判断行为类别是否被行为维护应用
	 */
	public String  checkRcxwlb(String values)throws Exception{
    	String resultLbmc="";
    	String[] rcxwlbmc=dao.checkRcxwlb(values);
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
	 * 判断行为小类是否被行为维护应用
	 */
	public String  checkRcxwxl(String values)throws Exception{
    	String resultLbmc="";
    	String[] rcxwlbmc=dao.checkRcxwxl(values);
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
	 * 获取日常行为大类列表
	 */
	public List<HashMap<String,String>> getRcxwdlList(){
		return dao.getRcxwdlList();
	}
	/** 
	 * 获取审核流未结束的小类
	 */
	public List<HashMap<String,String>> getRcxwxlShwjs(String values) throws Exception{
		return dao.getRcxwxlShwjs(values);
	}
	/** 
	 * 更新小类
	 */
	public boolean updateRcxwxlSfqy(RcxwdmwhForm model) throws Exception{
		return dao.updateRcxwxlSfqy(model);
	}

    public List<HashMap<String,String>> getBmlist(User user) {
		return dao.getBmlist(user);
    }

	public String getSsxy(String rcxwlbdldm) {
		return dao.getSsxy(rcxwlbdldm);
	}
}
