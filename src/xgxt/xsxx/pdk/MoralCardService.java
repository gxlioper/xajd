package xgxt.xsxx.pdk;

import java.util.HashMap;
import java.util.List;

import common.exception.SystemException;


import xgxt.xsxx.comm.XsxxCommForm;


public class MoralCardService {

	private MoralCardDAO dao = new MoralCardDAO();
	
	
	/**
	 * ��ѯ����ѧ����Ϣ�б� 
	 * @param model
	 * @return
	 */
	public List<String[]> getStudents(XsxxCommForm model,String query,String[] input,String[] colList){
		
		try {
			return dao.getStudents(model,query,input,colList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * ��ȡָ�����ָ����
	 * @param realTable
	 * @param colList
	 * @return
	 */
	public String[] getColumn(String realTable,String[] colList){
		
		return dao.getColumnNameCN(colList, realTable);
	}
	
	
	/**
	 * ����ѧ�Ų�ѯ�����ȵ�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getDyddListByXh(String xh){
		
		return dao.getDyddListByXh(xh);
	}
	
	
	/**
	 * ��������ȵ�
	 * @param xh
	 * @param xqmc
	 * @param pjjg
	 * @return
	 */
	public boolean saveDydd(String xh,String[] xqmc,String[] pjjg,String[] xssx){
		
		try {
			if (dao.clearDyddByXh(xh)){
				if (null != xqmc && null != pjjg && xqmc.length == pjjg.length){
					return dao.saveDydd(xh, xqmc, pjjg,xssx);
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	/**
	 * ����ѧ�Ų�ѯ���ͼ�¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getJcjlListByXh(String xh){
		
		return dao.getJcjlListByXh(xh);
	}
	
	
	/**
	 * ���潱�ͼ�¼
	 * @param xh
	 * @param rq
	 * @param zy
	 * @param bz
	 * @return
	 */
	public boolean saveJcjl(String xh, String[] rq, String[] zy, String[] bz) {

		if (null != rq && null != zy && null != bz && rq.length == zy.length
				&& rq.length == bz.length) {

			try {
				if (dao.clearJcjlByXh(xh)) {
					return dao.saveJcjl(xh, rq, zy, bz);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * ����ĳѧ����������¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyList(String xh){
		
		return dao.getPjpyList(xh);
	}
	
	
	/**
	 * ����ĳѧ����Υ�ͼ�¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getWjcfList(String xh){
		
		return dao.getWjcfList(xh);
	}
	
	
	/**
	 * �����ƺż�¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getRychList(String xh){
		
		return dao.getRychListByXh(xh);
	}
	
	/**
	 * Υ�ʹ��ּ�¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getJcWjcfList(String xh){
		
		return dao.getJcWjcfByXh(xh);
	}
	
	/**
	 * ѧ��������¼
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXszzList(String xh){
		
		return dao.getXszzByXh(xh);
	}
	
	
	/**
	 * ѧ��ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXjxxList(String[] xh){
		
		if (null != xh && xh.length > 0){
			return dao.getXjxxList(xh);
		} else {
			throw new SystemException();
		}
	}
	
	
	
	/**
	 * �����ȵڲ�ѯ
	 * @param model
	 * @param query
	 * @param input
	 * @return
	 */
	public List<String[]> getDyddList(XsxxCommForm model,String query,String[] input){
		
		
		return dao.getDyddList(model, query, input);
	}
}


