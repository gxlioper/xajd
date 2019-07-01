package xgxt.xsxx.pdk;

import java.util.HashMap;
import java.util.List;

import common.exception.SystemException;


import xgxt.xsxx.comm.XsxxCommForm;


public class MoralCardService {

	private MoralCardDAO dao = new MoralCardDAO();
	
	
	/**
	 * 查询基本学生信息列表 
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
	 * 获取指定表的指定列
	 * @param realTable
	 * @param colList
	 * @return
	 */
	public String[] getColumn(String realTable,String[] colList){
		
		return dao.getColumnNameCN(colList, realTable);
	}
	
	
	/**
	 * 根据学号查询德育等第
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getDyddListByXh(String xh){
		
		return dao.getDyddListByXh(xh);
	}
	
	
	/**
	 * 保存德育等第
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
	 * 根据学号查询奖惩记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getJcjlListByXh(String xh){
		
		return dao.getJcjlListByXh(xh);
	}
	
	
	/**
	 * 保存奖惩记录
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
	 * 查找某学生的评奖记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyList(String xh){
		
		return dao.getPjpyList(xh);
	}
	
	
	/**
	 * 查找某学生的违纪记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getWjcfList(String xh){
		
		return dao.getWjcfList(xh);
	}
	
	
	/**
	 * 荣誉称号记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getRychList(String xh){
		
		return dao.getRychListByXh(xh);
	}
	
	/**
	 * 违纪处分记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getJcWjcfList(String xh){
		
		return dao.getJcWjcfByXh(xh);
	}
	
	/**
	 * 学生资助记录
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXszzList(String xh){
		
		return dao.getXszzByXh(xh);
	}
	
	
	/**
	 * 学生学籍信息
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
	 * 德育等第查询
	 * @param model
	 * @param query
	 * @param input
	 * @return
	 */
	public List<String[]> getDyddList(XsxxCommForm model,String query,String[] input){
		
		
		return dao.getDyddList(model, query, input);
	}
}


