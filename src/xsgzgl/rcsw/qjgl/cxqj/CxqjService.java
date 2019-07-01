package xsgzgl.rcsw.qjgl.cxqj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午13:13:22
 * </p>
 */

public class CxqjService extends BasicService {

	/**
	 * 撤销请假首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCxqjCx(CxqjForm myForm) throws Exception {
		CxqjDAO dao = new CxqjDAO();
		return dao.getCxqjCx(myForm);
	}

	/**
	 * 撤销请假首页查询头部
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xh", "xm", "nj", "bj", "qjlx", "qjkssj", "qjjssj", "qjts","sfcx","cxr","cxsj" };
		String[] cn = new String[] { "", "学号", "姓名", "年级", "班级", "请假类型", "请假开始时间", "请假结束时间", "请假天数","是否撤销","撤销人","撤销时间" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * 撤销请假首页查询表单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				for (int j = 1; j < rs.length-3; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 4) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * 撤销请假单条信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCkqjMap(CxqjForm myForm) {
		CxqjDAO dao = new CxqjDAO();
		return dao.getCkqjMap(myForm);
	}

	/**
	 * 撤销请假信息撤销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean cxqjChx(CxqjForm myForm, String str, String cxyy, String username) throws Exception {
		CxqjDAO dao = new CxqjDAO();
		return dao.cxqjChx(myForm,str,cxyy,username);
	}

	/**
	 * 撤销请假信息取消撤销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean cxqjQx(CxqjForm myForm, String str) throws Exception {
		CxqjDAO dao = new CxqjDAO();
		return dao.cxqjQx(myForm,str);
	}
}