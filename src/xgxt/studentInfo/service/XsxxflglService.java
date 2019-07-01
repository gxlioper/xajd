package xgxt.studentInfo.service;

import xgxt.studentInfo.dao.XsxxflglDAO;
import xgxt.studentInfo.model.XsxxflForm;


public class XsxxflglService {
	XsxxflglDAO dao = new XsxxflglDAO();
	
	/**
	 * ���������Ϣ
	 * @param model
	 * @return boolean
	 * */
	public boolean saveXsxxflOne(XsxxflForm model){
		return dao.saveXsxxfl(model);
	}
	
	/**
	 * ���������Ϣ
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
