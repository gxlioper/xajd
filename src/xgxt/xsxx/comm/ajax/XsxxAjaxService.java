package xgxt.xsxx.comm.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.xsxx.comm.XsxxCommService;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_Ajax_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxAjaxService extends XsxxCommService {

	XsxxAjaxDAO dao = new XsxxAjaxDAO();

	/**
	 * 根据标签和学号获取内容
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getQtxxInfo(String mklx, String xh) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Jsp名称
		String jspName = dao.getJspName(mklx);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("jspName", jspName);
		list.add(map);

		if ("jtxx".equalsIgnoreCase(mklx)) {// 家庭信息
			getJtxxInfo(list, jspName, xh);
		} else if ("dtjs".equalsIgnoreCase(mklx)) {// 党团建设
			getDtjsInfo(list, jspName, xh);
		} else if ("pjpy".equalsIgnoreCase(mklx)) {// 评奖评优
			getPjpyInfo(list, jspName, xh);
		} else if ("xscj".equalsIgnoreCase(mklx)) {// 学生成绩
			getXscjInfo(list, jspName, xh);
		} else if ("qgzx".equalsIgnoreCase(mklx)) {// 勤工助学
			getQgzxInfo(list, jspName, xh);
		} else if ("gygl".equalsIgnoreCase(mklx)) {// 公寓管理
			getGyglInfo(list, jspName, xh);
		} else if ("xszz".equalsIgnoreCase(mklx)) {
			getZzxxInfo(list, jspName, xh);
		} else if ("wjcf".equalsIgnoreCase(mklx)) {
			getWjxxInfo(list, jspName, xh);
		} else if ("xljk".equalsIgnoreCase(mklx)) {
			getXljkInfo(list, jspName, xh);
		} else if ("jygl".equalsIgnoreCase(mklx)) {
			getJyglInfo(list, jspName, xh);
		}

		return list;
	}

	/**
	 * 获取家庭信息内容
	 * 
	 * @author 伟大的骆
	 */
	public void getJtxxInfo(
			List<HashMap<String, String>> list, String jspName, String xh) {

		HashMap<String, String> map = dao.getJtxxInfo(xh);
		list.add(map);
	}
	
	/**
	 * 获取党团建设内容
	 * 
	 * @author 伟大的骆
	 */
	public void getDtjsInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		// 党员信息
		HashMap<String, String> dyxxMap = dao.getDyxxInfo(xh);

		dyxxMap.put("dyxxPage", "yes");

		list.add(dyxxMap);

		if ("zsdyAndYbdy.jsp".equalsIgnoreCase(jspName)) {
			// 预备党员
			HashMap<String, String> ybdyMap = dao.getYbdyInfo(xh);

			ybdyMap.put("ybdyPage", "yes");

			list.add(ybdyMap);
		}
	}
	
	/**
	 * 获取评奖评优内容
	 * 
	 * @author 伟大的骆
	 */
	public void getPjpyInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		List<HashMap<String, String>> pjpyList = dao.getPjpyInfo(xh,jspName);

		list.addAll(pjpyList);
	}
	
	/**
	 * 获取勤工助学内容
	 * 
	 * @author 伟大的骆
	 */
	public void getQgzxInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		if ("qggwOnly.jsp".equalsIgnoreCase(jspName)) {
			List<HashMap<String, String>> qggwList = dao.getQggwInfo(xh, jspName);
			list.addAll(qggwList);
		}else if("cjffOnly.jsp".equalsIgnoreCase(jspName)){
			List<HashMap<String, String>> cjffList = dao.getCjffInfo(xh, jspName);
			list.addAll(cjffList);
		}
	}
	
	/**
	 * 获取学生成绩内容
	 * 
	 * @author 伟大的骆
	 */
	public void getXscjInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		List<HashMap<String, String>> pjpyList = dao.getXscjInfo(xh, jspName);

		list.addAll(pjpyList);
	}

	/**
	 * 获取公寓管理内容
	 * 
	 * @author 伟大的骆
	 */
	public void getGyglInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		HashMap<String, String> map = dao.getXszsInfo(xh);
		list.add(map);
	}
	
	/**
	 * 根据录入获取部门列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmOption(XsxxAjaxModel model,
			User user) {
		return dao.getBmOption(model, user);
	}
	
	/**
	 * 判断是否存在
	 * 
	 * @author 伟大的骆
	 */
	public boolean checkIsExists(XsxxAjaxModel model) {
		return dao.checkIsExists(model);
	}
	
	/**
	 * 检测用户名
	 * 
	 * @author 伟大的骆
	 */
	public String checkUserName(String userName) {
		return dao.checkUserName(userName);
	}
	// ===============以下made by 今天中五百万======================
	/**
	 * 获取学生资助详细信息列表
	 * @author qlj
	 */
	public void getZzxxInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {
		HashMap<String,String>zzxxMap=new HashMap<String,String>();
		
		if("zzxxInfo.jsp".equalsIgnoreCase(jspName)){
			zzxxMap.put("zzxxPage","yes");
			list.add(zzxxMap);
		}else if("zzglInfo.jsp".equalsIgnoreCase(jspName)){
			zzxxMap.put("zzglPage","yes");
			list.add(zzxxMap);
		}
		list.addAll(dao.getZzxxInfo(xh,jspName));
	}
	
	/**
	 * 获取违纪处分详细信息列表
	 * @author qlj
	 */
	public void getWjxxInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {
		HashMap<String,String>wjxxMap=new HashMap<String,String>();
		
		if("wjxxInfo.jsp".equalsIgnoreCase(jspName)){
			wjxxMap.put("wjxxPage","yes");
			list.add(wjxxMap);
		}else if("wjglInfo.jsp".equalsIgnoreCase(jspName)){
			wjxxMap.put("wjglPage","yes");
			list.add(wjxxMap);
		}
		list.addAll(dao.getWjxxInfo(xh,jspName));
	}
	
	/**
	 * 获取心理健康信息
	 * @author qlj
	 */
	public void getXljkInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {
		HashMap<String,String>xljkMap=new HashMap<String,String>();
		if("xlcsInfo.jsp".equalsIgnoreCase(jspName)){
			xljkMap.put("xlcsPage", "yes");
			list.add(xljkMap);
			list.addAll(dao.getXlcsInfo(xh, jspName));
		}else if("tsxsInfo.jsp".equalsIgnoreCase(jspName)){
			xljkMap.put("tsxsPage", "yes");
			list.add(xljkMap);
			list.addAll(dao.getTsxsInfo(xh, jspName));
		}else if("xlcsAndtsxs.jsp".equalsIgnoreCase(jspName)){
			xljkMap.put("xlcsPage", "yes");
			xljkMap.put("tsxsPage", "yes");
			list.add(xljkMap);
			list.addAll(dao.getXlcsInfo(xh, jspName));
			list.addAll(dao.getTsxsInfo(xh, jspName));
		}
	}
	
	/**
	 * 获取就业管理信息
	 * @author qlj
	 */
	public void getJyglInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {
		HashMap<String,String>gyglMap=new HashMap<String,String>();
		if("bysInfo.jsp".equalsIgnoreCase(jspName)){
			gyglMap.put("bysPage", "yes");
			gyglMap.putAll(dao.getBysInfo(xh, jspName));
			list.add(gyglMap);
		}else if("jyxyInfo.jsp".equalsIgnoreCase(jspName)){
			gyglMap.put("jyxyPage", "yes");
			gyglMap.putAll(dao.getJyxyInfo(xh, jspName));
			list.add(gyglMap);
		}else if("bysAndJyxy.jsp".equalsIgnoreCase(jspName)){
			gyglMap.put("bysPage", "yes");
			gyglMap.put("jyxyPage", "yes");
			gyglMap.putAll(dao.getBysInfo(xh, jspName));
			gyglMap.putAll(dao.getJyxyInfo(xh, jspName));
			list.add(gyglMap);
			
		}
	}
	
	/**
	 * 获取家庭情况信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getJdqkInfo(String xh){
		HashMap<String,String>map= dao.getJdqkInfo(xh);
		
		if(map!=null){
			
			String sfdb=Base.isNull(map.get("sfdb"))?" ":map.get("sfdb");
			String sfdq=Base.isNull(map.get("sfdq"))?" ":map.get("sfdq");
			String lszn=Base.isNull(map.get("lszn"))?" ":map.get("lszn");
			String kzzd1=Base.isNull(map.get("kzzd1"))?" ":map.get("kzzd1");
			String kzzd2=Base.isNull(map.get("kzzd2"))?" ":map.get("kzzd2");
			String kzzd3=Base.isNull(map.get("kzzd3"))?" ":map.get("kzzd3");
			String kzzd4=Base.isNull(map.get("kzzd4"))?" ":map.get("kzzd4");
			String kzzd5=Base.isNull(map.get("kzzd5"))?" ":map.get("kzzd5");
			String kzzd6=Base.isNull(map.get("kzzd6"))?" ":map.get("kzzd6");
			String kzzd7=Base.isNull(map.get("kzzd7"))?" ":map.get("kzzd7");
//			"sfdb","sfdq","lszn","kzzd1","kzzd2"
//			,"kzzd3","kzzd4","kzzd5","kzzd6","kzzd7
			map.put("sfdb", sfdb);
			map.put("sfdq", sfdq);
			map.put("lszn", lszn);
			map.put("kzzd1", kzzd1);
			map.put("kzzd2", kzzd2);
			map.put("kzzd3", kzzd3);
			map.put("kzzd4", kzzd4);
			map.put("kzzd5", kzzd5);
			map.put("kzzd6", kzzd6);
			map.put("kzzd7", kzzd7);
			
		}else{
			map.put("sfdb", " ");
			map.put("sfdq", " ");
			map.put("lszn", " ");
			map.put("kzzd1", " ");
			map.put("kzzd2", " ");
			map.put("kzzd3", " ");
			map.put("kzzd4", " ");
			map.put("kzzd5", " ");
			map.put("kzzd6", " ");
			map.put("kzzd7", " ");
			
		}
		
		return map;
	}
	// =======================end==========================
	
	/**
	 * 创建操作字段TABLE
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void createXscjHtml(String xh, User user,
			HttpServletResponse response) throws Exception {

		// 字段列表
		List<HashMap<String, String>> list = dao.getXscjList(xh);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<table class=\"formlist\" ");
		html.append("id=\"xscj\" ");
		html.append("style=\"width: 100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td>学年</td>");
		html.append("<td>学期</td>");
		html.append("<td>课程名称</td>");
		html.append("<td>课程性质</td>");
		html.append("<td>成绩</td>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				html.append("<tr>");
				html.append("<td>");
				html.append(map.get("xn"));
				html.append("</td>");
				html.append("<td>");
				html.append(map.get("xqmc"));
				html.append("</td>");
				html.append("<td>");
				html.append(map.get("kcmc"));
				html.append("</td>");
				html.append("<td>");
				html.append(map.get("kcxz"));
				html.append("</td>");
				html.append("<td>");
				html.append(map.get("cj"));
				html.append("</td>");
				html.append("</tr>");
			}
		}
		html.append("</tbody>");
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
}
