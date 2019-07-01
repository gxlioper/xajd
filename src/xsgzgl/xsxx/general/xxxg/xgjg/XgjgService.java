package xsgzgl.xsxx.general.xxxg.xgjg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgjgInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸ĽY��_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XgjgService extends XsxxGeneralService implements
		XxxgXgjgInterface {

	XgjgDAO dao = new XgjgDAO();

	/**
	 * ��ñ�ͷ�ļ����޸Č��ˡ�
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXgjgTop(XgjgModel model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjdm",
				"sqsj", "shzt" };
		String[] cn = new String[] { "", "ѧ��", "����", "�Ա�", "�꼶", "�༶",
				"�����޸�ʱ��", "���״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý�������޸Č��ˡ�
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXgjgList(XsxxGeneralForm myForm,
			XgjgModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXgjgList(myForm, model, user);
	}

	/**
	 * ������������޸Č��ˡ�
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public String createXgjgHTML(SearchRsModel rsModel, XgjgModel model,
			ArrayList<String[]> rsArrList, User user) {
		return null;
	}

	/**
	 * ��ʼ�����ܲ���
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	public void initParameter() {

		String path = "xsxx_general_xxxg_xgjg.do";

		// �߼���ѯ�Ƿ�����
		boolean isSearch = isExists("xg_search_szb", "path", path);

		if (!isSearch) {
			initSearch();
		}
	}

	/**
	 * ��ʼ����ѯ����
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	private void initSearch() {

		List<String[]> params = new ArrayList<String[]>();

		String path = "xsxx_general_xxxg_xgjg.do";
		String[] tj = new String[] { "xh", "xm", "nj", "xy", "zy", "bj", "shztOne" };
		String[] mc = new String[] { "ѧ��", "����", "�꼶", "Ժϵ", "רҵ", "�༶", "���״̬" };
		String[] lx = new String[] { "mhcx", "mhcx", "djcx", "djcx", "djcx",
				"djcx", "djcx" };
		String[] zd = new String[] { "xh", "xm", "nj", "xydm", "zydm", "bjdm",
				"shzt" };
		String[] ssmk = new String[] { "xsxx", "xsxx", "xsxx", "xsxx", "xsxx",
				"xsxx", "xsxx" };
		String[] xssx = new String[] { "1", "2", "1", "2", "3", "4", "5" };

		for (int i = 0; i < tj.length; i++) {
			String[] value = new String[] { path, tj[i], mc[i], lx[i], zd[i],
					ssmk[i], xssx[i] };
			params.add(value);
		}

		try {
			dao.initSearch(params);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
}