package xgxt.studentInfo.jqlx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.FormModleCommon;


public class JqlxService {
	
	JqlxDAO dao = new JqlxDAO();
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);
	}
	
	/**
	 * ��ȡѧ��������У������Ϣ
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 */
	public HashMap<String, String> getJqsqInfo(String pk){
		return dao.getJqsqInfo(pk);
	}
	
	/**
	 * ��ȡѧ��������������
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 */
	public String getCont(String pk){
		return dao.getCont(pk);
	}
	
	/**
	 * ��ȡѧ������
	 * @return
	 */
	public String getXqmcFromXqdm(){
		return dao.getXqmcFromXqdm();
	}
	
	/**
	 * �����б�
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		
		if ("sh".equalsIgnoreCase(flg)) {
			//����ԭ��
			List shztList = dao.getSelectList("shzt");
			request.setAttribute("shztList", shztList);
		} else if ("xq".equals(flg)) {
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
	}
}
