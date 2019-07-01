package xgxt.xtwh.comm.gwsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;

public class XtwhGwszService {

	/**
	 * 根据用户名查询出用户信息
	 * @param user
	 * @return HashMap<String,String>
	 * author qlj
	 */
	public HashMap<String,String>getYhInfo(User user){
		XtwhGwszDAO gwszDao=new XtwhGwszDAO();
		return gwszDao.getYhInfo(user);
	}
	
	public void setRequest(HttpServletRequest request,XtwhGwszForm myForm){
		
		//用户信息
		request.setAttribute("userInfo", getYhInfo(myForm.getUser()));
		request.setAttribute("sfqyList",setList("sfqy"));
		
	}
	
	public List<HashMap<String, String>> setList(String lx){
		//岗位设置 增加
		DAO dao = DAO.getInstance();
		
		String[]colListCN=null;
		String[]colListEN=null;
		if ("sfqy".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "是", "否" };
			colListEN = new String[] { "是", "否" };
		}
		
		return dao.arrayToList(colListEN, colListCN);
	}
}
