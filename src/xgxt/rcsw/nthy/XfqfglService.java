package xgxt.rcsw.nthy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.FormModleCommon;


public class XfqfglService {
	
	XfqfglDAO dao = new XfqfglDAO();
	/**
	 * 下拉列表
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		
		if ("sfqf".equalsIgnoreCase(flg)) {
			//是否欠费
			List sfqfList = dao.getSelectList("sfqf");
			request.setAttribute("sfqfList", sfqfList);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
	}
}
