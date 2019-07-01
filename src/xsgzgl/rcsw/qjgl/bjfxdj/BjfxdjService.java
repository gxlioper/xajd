package xsgzgl.rcsw.qjgl.bjfxdj;

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

public class BjfxdjService extends BasicService {

	/**
	 * 病假返校登记首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjfxdjCx(BjfxdjForm myForm) throws Exception {
		BjfxdjDAO dao = new BjfxdjDAO();
		return dao.getBjfxdjCx(myForm);
	}

	/**
	 * 病假返校登记首页查询头部
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
		String[] en = new String[] { "", "xh", "xm", "nj", "bj", "qjkssj", "qjjssj", "qjts","sffx","djr","djsj" };
		String[] cn = new String[] { "", "学号", "姓名", "年级", "班级", "请假开始时间", "请假结束时间", "请假天数","是否返校","登记人","登记时间" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * 病假返校登记首页查询表单
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
	 * 病假返校登记单条记录查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCkfxMap(BjfxdjForm myForm) {
		BjfxdjDAO dao = new BjfxdjDAO();
		return dao.getCkfxMap(myForm);
	}

	/**
	 * 病假返校登记信息登记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean bjfxdjDj(BjfxdjForm myForm, String str, String sffx, String bz, String username) throws Exception {
		BjfxdjDAO dao = new BjfxdjDAO();
		return dao.bjfxdjDj(myForm,str,sffx,bz,username);
	}

	/**
	 * 病假返校登记信息取消
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean bjfxdjQx(BjfxdjForm myForm, String str) throws Exception {
		BjfxdjDAO dao = new BjfxdjDAO();
		return dao.bjfxdjQx(myForm,str);
	}
}