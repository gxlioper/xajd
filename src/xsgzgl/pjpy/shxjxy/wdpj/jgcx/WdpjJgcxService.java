package xsgzgl.pjpy.shxjxy.wdpj.jgcx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxDAO;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_�����ѯ_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class WdpjJgcxService extends BasicService implements WdpjJgcxInterface {

	WdpjJgcxDAO dao = new WdpjJgcxDAO();
	
	/**
	 * ��ñ�ͷ�ļ�(�ҵ�����_��������)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWdpjBcpjTop(WdpjJgcxModel model,
			User user) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "pk", "xh", "xm", "nj", "bjmc", "yhkh","sqsj",
				"xmmc", "shzt" };
		String[] cn = new String[] { "", "ѧ��", "����", "�꼶", "�༶", "���п���","����ʱ��",
				"��Ŀ����", "���״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ѯ����(�ҵ�����_��������)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getWdpjBcpjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getWdpjBcpjList(myForm, model, user);
		
		return list;
	}

	/**
	 * ����HTML(�ҵ�����_��������)
	 * 
	 * @author ΰ�����
	 */
	public String createWdpjBcpjHTML(SearchRsModel rsModel,
			WdpjJgcxModel model, ArrayList<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * ������������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, Object> getBcpjMap(WdpjJgcxModel model) {
		// TODO �Զ����ɷ������
		return null;
	}
	
	//===================���ҵ�����_��ʷ������begin=============================
	
	/**
	 * ���ҵ�����_��ʷ��������ñ�ͷ�ļ�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWdpjLspjTop(WdpjJgcxModel model,
			User user) {
		DAO dao = DAO.getInstance();
		String tableName = model.getSearch_table();
		String[] en = model.getSearch_zd();
		String[] cn = getTableComment(tableName, en);

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ���ҵ�����_��ʷ��������ѯ����
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getWdpjLspjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws 

			IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getLspjList(myForm, model, user);

		return list;
	}

	/**
	 * ���ҵ�����_��ʷ����������HTML
	 * 
	 * @author ΰ�����
	 */
	public String createWdpjLspjHTML(SearchRsModel rsModel,
			WdpjJgcxModel model, ArrayList<String[]> rsArrList, User user) {

		return null;
	}
	
	/**
	 * ���ҵ�����_��ʷ�����������ϸ��Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public HashMap<String, String> getLspjMap(WdpjJgcxModel model) throws

	Exception {
		HashMap<String, String> map = getDetail(model);
		return map;
	}

	/**
	 * ���ҵ�����_��ʷ����������������ʷ��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public boolean savePjlsxx(WdpjJgcxModel model, User user,
			HttpServletRequest request) {

		String[] save_string_zd = model.getSave_string_zd();
		String[] save_array_zd = model.getSave_array_zd();

		// ����Map
		HashMap<String, Object> saveMap = getValueMapByObj(request,

		unionArray(save_string_zd, save_array_zd));

		boolean flag = false;

		try {
			flag = saveTable(model, saveMap, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ���ҵ�����_��ʷ������ɾ��������ʷ��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public boolean deletePjlsxx(WdpjJgcxModel model, User user,
			HttpServletRequest request) {

		// ����Map
		HashMap<String, Object> saveMap = getValueMapByObj(request,
				new String[] { "pkValue" });

		String[] pkValue = (String[]) saveMap.get("pkValue");
		model.setPkValue(pkValue);

		boolean flag = false;

		try {
			flag = deleteTable(model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ���ҵ�����_��ʷ���������������ʷ��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLspjList(String xh) {

		List<HashMap<String, String>> list = dao.getLspjList(xh);

		return list;
	}
	
	//===================���ҵ�����_��ʷ������end=============================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String createWdpjJgcxHTML(SearchRsModel rsModel,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User 

user) {
		// TODO �Զ����ɷ������
		return null;
	}

	public Map<String, Object> defaultWdpjXssq(WdpjJgcxModel form, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	public boolean deleteXssqInfo(WdpjJgcxModel model,
			HttpServletRequest request, User user) {
		// TODO �Զ����ɷ������
		return false;
	}

	public List<HashMap<String, String>> getCshXmList(WdpjJgcxModel model,
			User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	public ArrayList<String[]> getWdpjJgcxList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws 

IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return null;
	}

	public List<HashMap<String, String>> getWdpjJgcxTop(WdpjJgcxModel model,
			User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	public String createWdpjJgcxHTML(WdpjJgcxModel model,
			ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	public ArrayList<String[]> getWdpjJgcxList(WdpjJgcxModel model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// TODO �Զ����ɷ������
		return null;
	}



}
