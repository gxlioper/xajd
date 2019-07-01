package com.zfsoft.xgxt.xsxx.xjyd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XjydService extends SuperServiceImpl<XjydForm, XjydDao> {

	private XjydDao dao = new XjydDao();
	
	public XjydService(){
		super.setDao(dao);
	}

	
	/**
	 * @throws Exception  
	 * @����:ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����10:10:08
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbPageList(XjydForm model) throws Exception {
		
		return dao.getXjlbPageList(model);
	}

	/**
	 * @throws Exception  
	 * @����:ѧ���춯����б�ȫ��/���趨��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����10:10:08
	 * @param flg : 0:ȫ���춯�б�1����������춯
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXjlbList(String flg, String ydlbdm) throws Exception {

		return dao.getXjlbList(flg, ydlbdm);
	}
	
	/** 
	 * @����:����ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����02:20:47
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveXjydlb(XjydForm model) {
		
		// �жϴ����Ƿ��Ѵ���
		if(!checkIsExist(model.getXjlbdm(), false)){
			return dao.saveXjydlb(model);
		}else{
			return false;
		}	
	}


	/**
	 * 
	 * @����:�жϴ����Ƿ��Ѵ���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����02:35:13
	 * @param xjlbdm ѧ��������
	 * @param shpzFlg �Ƿ���������ж� true:�ǣ�false����
	 * @return
	 * boolean true:���ڣ�false:������
	 * @throws
	 */
	private boolean checkIsExist(String xjlbdm , boolean shpzFlg){
		
		XjydForm model = new XjydForm();
		model.setXjlbdm(xjlbdm);
		try {
			
			List<HashMap<String,String>> resultList = new ArrayList<HashMap<String,String>>();
			
			if(shpzFlg){
				resultList = getXjlbShpzPageList(model);
			}else{
				resultList = getXjlbPageList(model);
			}
			if(resultList !=null && resultList.size()>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/** 
	 * @����:�޸�ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����02:20:47
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updXjydlb(XjydForm model) {
		String xjlbdmold = model.getXjlbdmold();
		String xjlbdm = model.getXjlbdm();
		boolean bolflg = false;
		// ͬ���Ĵ���
		if(xjlbdmold.equals(xjlbdm)){
			bolflg = dao.delXjydlb(xjlbdm) > 0 ? true:false;
			if(bolflg){
				bolflg = dao.saveXjydlb(model);
			}
		}else{

			// �жϴ����Ƿ��Ѵ���
			if(!checkIsExist(xjlbdm, false)){
				
				bolflg = dao.delXjydlb(xjlbdmold) > 0 ? true:false;
				if(bolflg){
					bolflg = dao.saveXjydlb(model);
				}				
			}else{
				return false;
			}
		}
		
		return bolflg;
	}
	
	/** 
	 * @����:�޸�ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����02:20:47
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public int delXjydlb(String values) {
		
		return	dao.delXjydlb(values);
	}


	/**
	 * @����:ѧ���춯�����������б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����03:47:33
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception  
	 */
	public List<HashMap<String, String>> getXjlbShpzPageList(XjydForm model) throws Exception {
		return dao.getXjlbShpzPageList(model);
	}


	/** 
	 * @����:�޸�ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:32:34
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveXjydlbShpz(XjydForm model) {
		
		if(!checkIsExist(model.getXjlbdm(), true)){
			return dao.saveXjydlbShpz(model);
		}else{
			return false;
		}
	}


	/**
	 * @throws Exception  
	 * @����:�޸�ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:47:06
	 * @param myForm
	 * @return
	 * XjydForm �������� 
	 * @throws 
	 */
	public XjydForm getModelShpz(XjydForm myForm) throws Exception {
		return dao.getModelShpz(myForm);
	}


	/** 
	 * @����:�޸�ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:54:47
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updXjydlbShpz(XjydForm myForm) {
		return dao.updXjydlbShpz(myForm);
	}


	/** 
	 * @����:ɾ��ѧ���춯����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-29 ����08:58:37
	 * @param values
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int delXjydlbShpz(String values) {
		return dao.delXjydlbShpz(values);
	}
	
	
	
	/**
	 * 
	 * @����:�ж��춯�������Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-29 ����05:32:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean lbdmExist(String lbdm){
		
		String sqNum = dao.lbdmExistXjydsqb(lbdm);
		String jgNum = dao.lbdmExistXjydjgb(lbdm);
		String xsxxNum = dao.lbdmExistXsxxb(lbdm);
		
		boolean lbdmExist = true;
		
		if("0".equals(sqNum)&&"0".equals(jgNum)&&"0".equals(xsxxNum)){
			
			lbdmExist = false; 
		}
		
		return lbdmExist;
		
	}
	
	
	/**
	 * 
	 * @����:��ѯ��������Ƿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-9 ����09:38:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbmc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean lbmcExist(String lbmc){
		
		String lbmcNum = dao.lbmcExistLbmc(lbmc);
		
		if("0".equals(lbmcNum)){
			return false;
		}
		
			return true;
	}
	
	/**
	 * 
	 * @����:��ѯ�������Ƿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-10 ����09:24:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lbdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean lbdmExists(String lbdm){
		
		String lbdmNum = dao.lbdmExists(lbdm);
		
		if("0".equals(lbdmNum)){
			return false;
		}
		
		return true;
		
	}
	

}
