package xgxt.studentInfo.service;

import xgxt.studentInfo.dao.XsxxflglDAO;
import xgxt.studentInfo.model.XsxxflForm;


public class XsxxflglService {
	XsxxflglDAO dao = new XsxxflglDAO();
	
	/**
	 * 保存分流信息
	 * @param model
	 * @return boolean
	 * */
	public boolean saveXsxxflOne(XsxxflForm model){
		return dao.saveXsxxfl(model);
	}
	
	/**
	 * 保存分流信息
	 * @param xhArr
	 * @param model
	 * @return boolean
	 * */
	public boolean saveXsxxflBatch(String[] xhArr, XsxxflForm model){
		boolean result = false;
		try{
			result = dao.saveXsxxflBatch(xhArr, model);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
}
