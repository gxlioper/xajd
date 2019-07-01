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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_Ajax_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxAjaxService extends XsxxCommService {

	XsxxAjaxDAO dao = new XsxxAjaxDAO();

	/**
	 * ���ݱ�ǩ��ѧ�Ż�ȡ����
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getQtxxInfo(String mklx, String xh) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Jsp����
		String jspName = dao.getJspName(mklx);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("jspName", jspName);
		list.add(map);

		if ("jtxx".equalsIgnoreCase(mklx)) {// ��ͥ��Ϣ
			getJtxxInfo(list, jspName, xh);
		} else if ("dtjs".equalsIgnoreCase(mklx)) {// ���Ž���
			getDtjsInfo(list, jspName, xh);
		} else if ("pjpy".equalsIgnoreCase(mklx)) {// ��������
			getPjpyInfo(list, jspName, xh);
		} else if ("xscj".equalsIgnoreCase(mklx)) {// ѧ���ɼ�
			getXscjInfo(list, jspName, xh);
		} else if ("qgzx".equalsIgnoreCase(mklx)) {// �ڹ���ѧ
			getQgzxInfo(list, jspName, xh);
		} else if ("gygl".equalsIgnoreCase(mklx)) {// ��Ԣ����
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
	 * ��ȡ��ͥ��Ϣ����
	 * 
	 * @author ΰ�����
	 */
	public void getJtxxInfo(
			List<HashMap<String, String>> list, String jspName, String xh) {

		HashMap<String, String> map = dao.getJtxxInfo(xh);
		list.add(map);
	}
	
	/**
	 * ��ȡ���Ž�������
	 * 
	 * @author ΰ�����
	 */
	public void getDtjsInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		// ��Ա��Ϣ
		HashMap<String, String> dyxxMap = dao.getDyxxInfo(xh);

		dyxxMap.put("dyxxPage", "yes");

		list.add(dyxxMap);

		if ("zsdyAndYbdy.jsp".equalsIgnoreCase(jspName)) {
			// Ԥ����Ա
			HashMap<String, String> ybdyMap = dao.getYbdyInfo(xh);

			ybdyMap.put("ybdyPage", "yes");

			list.add(ybdyMap);
		}
	}
	
	/**
	 * ��ȡ������������
	 * 
	 * @author ΰ�����
	 */
	public void getPjpyInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		List<HashMap<String, String>> pjpyList = dao.getPjpyInfo(xh,jspName);

		list.addAll(pjpyList);
	}
	
	/**
	 * ��ȡ�ڹ���ѧ����
	 * 
	 * @author ΰ�����
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
	 * ��ȡѧ���ɼ�����
	 * 
	 * @author ΰ�����
	 */
	public void getXscjInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		List<HashMap<String, String>> pjpyList = dao.getXscjInfo(xh, jspName);

		list.addAll(pjpyList);
	}

	/**
	 * ��ȡ��Ԣ��������
	 * 
	 * @author ΰ�����
	 */
	public void getGyglInfo(List<HashMap<String, String>> list, String jspName,
			String xh) {

		HashMap<String, String> map = dao.getXszsInfo(xh);
		list.add(map);
	}
	
	/**
	 * ����¼���ȡ�����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBmOption(XsxxAjaxModel model,
			User user) {
		return dao.getBmOption(model, user);
	}
	
	/**
	 * �ж��Ƿ����
	 * 
	 * @author ΰ�����
	 */
	public boolean checkIsExists(XsxxAjaxModel model) {
		return dao.checkIsExists(model);
	}
	
	/**
	 * ����û���
	 * 
	 * @author ΰ�����
	 */
	public String checkUserName(String userName) {
		return dao.checkUserName(userName);
	}
	// ===============����made by �����������======================
	/**
	 * ��ȡѧ��������ϸ��Ϣ�б�
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
	 * ��ȡΥ�ʹ�����ϸ��Ϣ�б�
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
	 * ��ȡ��������Ϣ
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
	 * ��ȡ��ҵ������Ϣ
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
	 * ��ȡ��ͥ�����Ϣ
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
	 * ���������ֶ�TABLE
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void createXscjHtml(String xh, User user,
			HttpServletResponse response) throws Exception {

		// �ֶ��б�
		List<HashMap<String, String>> list = dao.getXscjList(xh);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<table class=\"formlist\" ");
		html.append("id=\"xscj\" ");
		html.append("style=\"width: 100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td>ѧ��</td>");
		html.append("<td>ѧ��</td>");
		html.append("<td>�γ�����</td>");
		html.append("<td>�γ�����</td>");
		html.append("<td>�ɼ�</td>");
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
