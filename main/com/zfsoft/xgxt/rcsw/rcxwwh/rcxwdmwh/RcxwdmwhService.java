/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-31 ����08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;




/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ ����ά��
 * @���ߣ� dlq [���ţ�995]
 * @ʱ�䣺 2013-7-31 ����08:27:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwdmwhService extends SuperServiceImpl<RcxwdmwhForm, RcxwdmwhDao>  {

	private RcxwdmwhDao dao = new RcxwdmwhDao();
	public RcxwdmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ô�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>>  getXwdlPageList(RcxwdmwhForm model) throws Exception{
		return dao.getXwdlPageList(model);
	}
	
	/**
	 * 
	 * @����:��������ŵ��ճ���Ϊ�������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����12:28:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
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
	 * ���ݴ�������ѯ��������
	 */
	public String getRcxwlbdlmcById(String id) throws Exception {
		return dao.getRcxwlbdlmcById(id);
	}
	
	/**
	 * ������Ϊ������Ϣ
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
	 * ɾ����Ϊ����
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-7 ����09:05:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
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
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>>  getXwlbList(RcxwdmwhForm model) throws Exception{
		return dao.getXwlbList(model);
	}
	

	/**
	 * 
	 * @����:��������ŵ��ճ���Ϊ�������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����12:28:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
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
	 * @����:������Ϊ���
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����04:04:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXwlbInfo(RcxwdmwhForm model,String type) throws Exception{
		boolean b = false;
//		String rcxwlbfz = model.getRcxwlbfz();
//		String  charone = rcxwlbfz.charAt(0) + "";
//		if(Constants.JIANFEN.equals(model.getRcxwfzlx())){
//			//��Ϊ��ֵ����Ϊ����
//			if(charone.equalsIgnoreCase("-") || charone.equalsIgnoreCase("+")){
//				String rcxwlbfzjian = rcxwlbfz.substring(1, rcxwlbfz.length());
//				model.setRcxwlbfz("-" + rcxwlbfzjian);
//			}else{
//				model.setRcxwlbfz("-"+model.getRcxwlbfz());
//			}
//		}else{
//			//��Ϊ��ֵ����Ϊ�ӷ�
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
	 * ��Ϊ��𼯺�
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����03:30:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
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
	 * ɾ����Ϊ����
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-7 ����09:05:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public int deleteXwlbInfo(String values) throws Exception{
		
		return dao.deleteXwlbInfo(values);
	}
	
	/**
	 * 
	 * �ж���Ϊ����Ƿ���Ϊά��Ӧ��
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����03:53:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * String �������� 
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
	 * @����:��ȡ�ճ���Ϊ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-14 ����09:00:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcxwdlList(){
		return dao.getRcxwdlList();
	}
	
}
