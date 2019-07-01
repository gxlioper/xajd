package xsgzgl.gygl.rcjc.wsjc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
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
 * Time:2012-7-9 下午14:19:22
 * </p>
 */

public class WsjcService extends BasicService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	/**
	 * 获取卫生检查信息头部
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr() {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xy", "ld", "bj", "qs", "xb", "sgxfdy", "wsdj", "dgldq", "jcsj" };
		String[] cn = new String[] { "", Base.YXPZXY_KEY, "楼栋", "班级", "寝室", "性别", "所管辖辅导员", "卫生等级", "大功率电器", "检查时间" };
		return dao.arrayToList(en, cn);
	}

	/**
	 * 创建卫生检查信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTML(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
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
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
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
	 * 卫生检查信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsjcCx(WsjcForm myForm) throws Exception {
		WsjcDAO dao = new WsjcDAO();
		return dao.getWsjcCx(myForm);
	}

	/**
	 * 级联查询-获取所有楼栋号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllLd() {
		WsjcDAO dao = new WsjcDAO();
		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "ldmc", "lddm" };
		return dao.getAllLd(inputValue, outputValue);
	}

	/**
	 * 级联查询-由楼栋号获得寝室号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQsForLd(String lddm) {
		WsjcDAO dao = new WsjcDAO();
		String[] inputValue = new String[] { lddm };
		String[] outputValue = new String[] { "qsh", "qsh" };
		return dao.getQsForLd(inputValue, outputValue);
	}

	/**
	 * 级联查询-由楼栋号和寝室号获得其他信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getInfo(String lddm, String qsh) {
		WsjcDAO dao = new WsjcDAO();
		String[] inputValue = new String[] { lddm, qsh };
		String[] outputValue = new String[] { "xy", "bj", "xb", "sgxfdy" };
		return dao.getInfo(inputValue, outputValue);
	}

	/**
	 * 卫生检查信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean wsjcBc(WsjcForm myForm, String username) throws Exception {
		WsjcDAO dao = new WsjcDAO();
		return dao.wsjcBc(myForm, username);
	}

	/**
	 * 卫生检查信息删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean wsjcSc(WsjcForm myForm, String[] valArr, String username) throws SQLException {
		WsjcDAO dao = new WsjcDAO();
		return dao.wsjcSc(myForm, valArr, username);
	}

	/**
	 * 获取卫生检查信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWsjcMap(WsjcForm wsjcForm) {
		WsjcDAO dao = new WsjcDAO();
		return dao.getWsjcMap(wsjcForm);
	}

	/**
	 * 卫生检查信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean wsjcXg(WsjcForm myForm, String username) throws Exception {
		WsjcDAO dao = new WsjcDAO();
		return dao.wsjcXg(myForm, username);
	}
}