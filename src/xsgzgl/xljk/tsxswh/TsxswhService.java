package xsgzgl.xljk.tsxswh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.szdw.bjlh.fdyzp.BjlhFdyzpDAO;

public class TsxswhService {
	TsxswhDAO myDao = new TsxswhDAO();

	/**
	 * 获取topTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		if("tsxswh".equals(mk)){
			topTr=new String[]{"学号","姓名","学年","学期","年级","班级","申报时间"};
		}
		return topTr;
	}
	/**
	 * 获得特殊学生信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTableList(TsxswhForm myForm, HttpServletRequest request) throws Exception {
		return myDao.getTableList(myForm,request);
	}
	/**
	 * 保存特殊学生信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean saveTsxsXX(TsxswhForm myForm, HttpServletRequest request) throws Exception {
		return myDao.saveTsxsXX(myForm, request);
	}
	/**
	 * 删除特殊学生信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean deleteTsxsXX(String[] getCheckVal) throws Exception{
		List<String[]> params = new ArrayList<String[]>(); 
		for(int i = 0; i < getCheckVal.length; i++){
			params.add(getCheckVal[i].split("!!!SplitOne!!!"));
		}
		return myDao.deleteTsxsXX(params);
	}
	/**
	 *  获得一个学生信息hashmap
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> getOneTsxsXX(String[] pkValue) {
		return myDao.getOneTsxsXX(pkValue);
	}
	public HashMap<String, String> getXsxx(String xh) {
		return myDao.getXsxx(xh);
	}

}
