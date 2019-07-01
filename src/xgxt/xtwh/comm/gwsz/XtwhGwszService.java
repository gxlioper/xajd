package xgxt.xtwh.comm.gwsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;

public class XtwhGwszService {

	/**
	 * �����û�����ѯ���û���Ϣ
	 * @param user
	 * @return HashMap<String,String>
	 * author qlj
	 */
	public HashMap<String,String>getYhInfo(User user){
		XtwhGwszDAO gwszDao=new XtwhGwszDAO();
		return gwszDao.getYhInfo(user);
	}
	
	public void setRequest(HttpServletRequest request,XtwhGwszForm myForm){
		
		//�û���Ϣ
		request.setAttribute("userInfo", getYhInfo(myForm.getUser()));
		request.setAttribute("sfqyList",setList("sfqy"));
		
	}
	
	public List<HashMap<String, String>> setList(String lx){
		//��λ���� ����
		DAO dao = DAO.getInstance();
		
		String[]colListCN=null;
		String[]colListEN=null;
		if ("sfqy".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "��", "��" };
			colListEN = new String[] { "��", "��" };
		}
		
		return dao.arrayToList(colListEN, colListCN);
	}
}
