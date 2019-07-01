
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * �ճ���Ϊ ����ά��
 */
public class RcxwdmwhService extends SuperServiceImpl<RcxwdmwhForm, RcxwdmwhDao>  {

	private RcxwdmwhDao dao = new RcxwdmwhDao();
	public RcxwdmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ô�����Ϣ
	 */
	public List<HashMap<String, String>>  getRcxwdlPageList(RcxwdmwhForm model, User user) throws Exception{
		return dao.getRcxwdlPageList(model, user);
	}
	
	/**
	 * ���ݴ�������ѯ��������
	 */
	public String getRcxwlbdlmcById(String id) throws Exception {
		return dao.getRcxwlbdlmcById(id);
	}
	/**
	 * ������Ϊ������Ϣ
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
	 * ������ΪС����Ϣ
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
	 * ɾ����Ϊ����
	 */
	public int deleteRcxwdlInfo(String values) throws Exception{
		return dao.deleteRcxwdlInfo(values);
	}
	/**
	 * ������Ϊ��������Ƿ���ʹ��
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
	 * ��ȡ�����δ�����Ĵ���
	 */
	public List<HashMap<String, String>> getRcxwdlShwjs(String values) throws Exception{
		return dao.getRcxwdlShwjs(values);
	}
	/** 
	 * ��ȡ�����δ���������
	 */
	public List<HashMap<String,String>> getRcxwlbShwjs(String values) throws Exception{
		return dao.getRcxwlbShwjs(values);
	}
	/** 
	 * �������
	 */
	public boolean updateRcxwlbSfqy(RcxwdmwhForm model) throws Exception{
		return dao.updateRcxwlbSfqy(model);
	}
	/**
	 * ��������Ϣ
	 */
	public List<HashMap<String, String>>  getRcxwlbList(RcxwdmwhForm model) throws Exception{
		return dao.getRcxwlbList(model);
	}
	/**
	 * �����û���Ȩ��ô���
	 */
	public List<HashMap<String, String>>  getRcxwlbdlListByYhsq(User user) throws Exception {
		return dao.getRcxwlbdlListByYhsq(user);
	}
	/**
	 * �����������ѯ����
	 */
	public List<HashMap<String, String>>  queryRcxwlbdlListByLbdm(String rcxwlbdm,User user) throws Exception {
		return dao.queryRcxwlbdlListByLbdm(rcxwlbdm,user);
	}
	/**
	 * ���ݴ�������ѯС��
	 */
	public List<HashMap<String, String>>  queryRcxwlbxlListByDldm(String rcxwlbdldm) throws Exception {
		return dao.queryRcxwlbxlListByDldm(rcxwlbdldm);
	}
	/**
	 * �����û���Ȩ������
	 */
	public List<HashMap<String, String>>  getRcxwlbListByYhsq(User user) throws Exception{
		return dao.getRcxwlbListByYhsq(user);
	}
	/**
	 * ��������ŵ��ճ���Ϊ������
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
	 * ��������ŵ��ճ���Ϊ�������
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
	 * ��������ŵ��ճ���ΪС�����
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
	 * ������Ϊ���
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
	 * ��ΪС��
	 */
	public List<HashMap<String,String>> getRcxwxlList(RcxwdmwhForm model, User user) throws Exception{
		return dao.getRcxwxlList(model, user);
	}
	/**
	 * ��ѯ��Ϊ���
	 */
	public RcxwdmwhForm getRcxwlbForm(RcxwdmwhForm t) throws Exception{
		return dao.getRcxwlbForm(t);
	}
	/**
	 * ��ѯ��ΪС��
	 */
	public RcxwdmwhForm getRcxwxlForm(RcxwdmwhForm t) throws Exception{
		return dao.getRcxwxlForm(t);
	}
	/**
	 * ɾ����Ϊ�����Ϣ
	 */
	public int deleteRcxwlbInfo(String values) throws Exception{
		int i = dao.deleteRcxwlbInfo(values);
		dao.deleteRcxwsqbInfo(values);
		return i;
	}
	/**
	 * ɾ����ΪС����Ϣ
	 */
	public int deleteRcxwxlInfo(String values) throws Exception{
		return dao.deleteRcxwxlInfo(values);
	}
	/**
	 * �ж���Ϊ����Ƿ���Ϊά��Ӧ��
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
	 * �ж���ΪС���Ƿ���Ϊά��Ӧ��
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
	 * ��ȡ�ճ���Ϊ�����б�
	 */
	public List<HashMap<String,String>> getRcxwdlList(){
		return dao.getRcxwdlList();
	}
	/** 
	 * ��ȡ�����δ������С��
	 */
	public List<HashMap<String,String>> getRcxwxlShwjs(String values) throws Exception{
		return dao.getRcxwxlShwjs(values);
	}
	/** 
	 * ����С��
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
