package xsgzgl.xtwh.general.homepage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.customForm.demo.DemoFormModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.pjsz.PjszBjdlInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_����С��_Service��
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

public class HomePageService extends CommService {

	HomePageDAO dao = new HomePageDAO();
	
	/**
	 * ��ʼ���ҵĹ�������
	 * 
	 * @author ΰ�����
	 */
	public boolean defaultWdgzInfo(HomePageModel model, User user) {

		DAO dao = DAO.getInstance();

		//�Ñ���
		String userName = user.getUserName();
		//�Ñ����
		String userStatus = user.getUserStatus();
		//�û�����
		String userDep = user.getUserDep();

		boolean flag = false;

		try {
			flag = dao.runProcuder("{call pro_xg_xtwh_wdgz(?,?,?)}",
					new String[] { userName, userStatus, userDep });
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �@ʾ�ҵĹ�����Ϣ
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void showWdgzInfo(HomePageModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// �Ñ���
		String userName = user.getUserName();
		// �Ñ����
		String userType = user.getUserType();
		// ����Ñ��ҵĹ����б�
		List<HashMap<String, String>> wdgzList = getWdgzList(model, user);

		StringBuilder html = new StringBuilder();

		html.append("<h3>");
		
		int nr_size = 0;
		if ("stu".equalsIgnoreCase(userType)) {
			html.append("<span>�ҵ���Ո</span>");
			nr_size = 15;
		} else {
			html.append("<span>�ҵĹ���</span>");
			nr_size = 30;
		}
		html.append("</h3>");
//		html.append("<div class=\"index_list_01\">");
		html.append("<ul ");
		if (!"stu".equalsIgnoreCase(userType)) {
			html.append("class=\"index_list_01\"");
		}
		html.append(">");
		for (int i = 0; i < wdgzList.size(); i++) {
			HashMap<String, String> map = wdgzList.get(i);
			// ����ģ�K���
			String gnmklx = map.get("gnmklx");
			// ��������
			String gznr = map.get("gznr");
			// ·��
			String gnmkpath = map.get("gnmkpath");
			
			html.append("<li>");
			if (!Base.isNull(gznr)) {
				html.append("<a ");
				html.append("href=\"" + gnmkpath + "\"");
				html.append("title=\"" + gznr + "\"");
				html.append(">");
				html.append("��");
				html.append(gnmklx);
				html.append("��");
				if (gznr.length() > nr_size) {
					html.append(gznr.substring(0, nr_size));
				} else {
					html.append(gznr);
				}
				html.append("</a>");
			}
			html.append("</li>");
		}
		html.append("</ul>");
//		html.append("</div>");
	
		response.getWriter().print(html.toString());
	}
	
	/**
	 * ��ԃ����Ñ��ҵĹ����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWdgzList(HomePageModel model,
			User user) {
		
		// �Ñ���
		String userName = user.getUserName();
		// �Ñ����
		String userType = user.getUserStatus();
		// ����Ñ��ҵĹ����б�
		List<HashMap<String, String>> wdgzList = dao.getWdgzList(userName,
				userType);
		// ����Ñ��ҵĹ����б�
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		int row = "stu".equalsIgnoreCase(userType) ? 6 : 10;
		int space = 0;
		
		if (wdgzList != null && wdgzList.size() < row) {
			space = row - wdgzList.size();
		}
			
		list.addAll(wdgzList);
		
		for (int i = 0; i < space; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			list.add(map);
		}
		
		return list;
	}
}