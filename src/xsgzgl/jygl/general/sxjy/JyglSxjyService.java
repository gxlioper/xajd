package xsgzgl.jygl.general.sxjy;

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
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.inter.JyglSxjyInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ҵ����_ʵϰ��ҵ_ͨ��_Service��
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

public class JyglSxjyService extends BasicService implements JyglSxjyInterface {

	JyglSxjyDAO dao = new JyglSxjyDAO();

	/**
	 * ��ʵϰ��ҵ����ñ�ͷ��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSxjyTop(JyglSxjyModel model,
			User user) {
		DAO dao = DAO.getInstance();
		String tableName = model.getSearch_table();
		String[] en = model.getSearch_zd();
		String[] cn = getTableComment(tableName, en);

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}

	/**
	 * ��ʵϰ��ҵ����ѯ����
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getSxjyList(JyglGeneralForm myForm,
			JyglSxjyModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		ArrayList<String[]> list = dao.getSxjyList(myForm, model, user);
		return list;
	}

	/**
	 * ��ʵϰ��ҵ������HTML
	 * 
	 * @author ΰ�����
	 */
	public String createSxjyHTML(SearchRsModel rsModel, JyglSxjyModel model,
			ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ��ʵϰ��ҵ�������ϸ��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getSxjyMap(JyglSxjyModel model)
			throws Exception {
		HashMap<String, String> map = getDetail(model);
		return map;
	}

	/**
	 * ��ʵϰ��ҵ��������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public boolean saveSxjy(JyglSxjyModel model, User user,
			HttpServletRequest request) {

		String[] save_string_zd = model.getSave_string_zd();
		String[] save_array_zd = model.getSave_array_zd();

		//����Map
		HashMap<String, Object> saveMap = getValueMapByObj(request, unionArray(
				save_string_zd, save_array_zd));

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
	 * ��ʵϰ��ҵ��ɾ����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public boolean deleteSxjy(JyglSxjyModel model, User user,
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
}
