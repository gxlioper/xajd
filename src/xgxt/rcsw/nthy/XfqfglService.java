package xgxt.rcsw.nthy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.FormModleCommon;


public class XfqfglService {
	
	XfqfglDAO dao = new XfqfglDAO();
	/**
	 * �����б�
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		
		if ("sfqf".equalsIgnoreCase(flg)) {
			//�Ƿ�Ƿ��
			List sfqfList = dao.getSelectList("sfqf");
			request.setAttribute("sfqfList", sfqfList);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
	}
}
