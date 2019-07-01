package xsgzgl.qgzx.xsgw;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

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
 * Time:2012-7-23 下午14:19:22
 * </p>
 */

public class QgzxXsgwcxService extends BasicService {

	/**
	 * 查询我的勤工岗位首页面头部
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "xn", "gwmc", "gwxz", "yrbm", "zje" };
		String[] cn = new String[] { "", "行号", "学年", "岗位名称", "岗位性质", "用人部门", "总酬金"};
		if("gwxxCx".equalsIgnoreCase(type)){
			en = new String[] { "", "r", "xn", "yrbm", "gwmc", "xqrs", "knsrs", "ylyrs" };
			cn = new String[] { "", "行号", "学年",  "用人部门", "岗位名称", "需求人数", "困难生人数","已录用人数" };
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * 查询我的勤工岗位首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWdqggwCx(QgzxXsgwcxForm myForm,String username) throws Exception {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.getWdqggwCx(myForm,username);
	}

	/**
	 * 拼接我的勤工岗位首页面表格
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
	 * 查看我的勤工岗位首页面一条数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsgwCkmxMap(QgzxXsgwcxForm myForm,String userName) {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.getXsgwCkmxMap(myForm,userName);
	}

	/**
	 * 查看我的勤工岗位首页面一条数据（酬金发放明细部分）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getXsgwCkcjmxMap(QgzxXsgwcxForm myForm,String userName) throws Exception {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.getXsgwCkcjmxMap(myForm,userName);
	}

	/**
	 * 岗位信息查询
	 * @param myForm
	 * @param userName
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> gwxxCx(QgzxXsgwcxForm myForm, String userName) throws Exception {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.gwxxCx(myForm);
	}
	
	/**
	 * 岗位信息查看
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	public HashMap<String, String> gwxxCk(QgzxXsgwcxForm model) throws SQLException {
		//岗位信息
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		return dao.gwxxCk(model);
	}
	
	/**
	 * 通过学号查询学生岗位及酬金信息列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuGwxxCjxxList(String xh) {
		QgzxXsgwcxDAO dao = new QgzxXsgwcxDAO();
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		//岗位录用情况 
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String[]> list = new ArrayList<String[]>();
		
		list.add(new String[]{"用人单位", "岗位名称", "岗位性质", "上岗时间", "在岗状态", "退岗时间", "退岗原因"});
		list.addAll(dao.getStuQgzxXsgwxxList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "岗位录用情况");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		//酬金发放情况 
		map = new HashMap<String, Object>();
		list = new ArrayList<String[]>();
		list.add(new String[]{"用人单位","岗位名称","发放年月",  "发放金额", "工时数(小时)"});
		list.addAll(dao.getStuQgzxCjffList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "酬金发放情况");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}
}