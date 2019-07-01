package xsgzgl.pjpy.general.pjsz.cpxz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;

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

public class PjszCpxzService extends CommService implements PjszCpxzInterface {

	PjszCpxzDAO dao = new PjszCpxzDAO();

	/**
	 * ��ñ�ͷ�ļ�(��������_����С��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjszCpxzTop(PjszCpxzModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"cpzmc" };
		String[] cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶",
				"������" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����(��������_����С��)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getPjszCpxzList(PjpyGeneralForm myForm,
			PjszCpxzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszCpxzList(myForm, user);
	}

	/**
	 * ���������(��������_����С��)
	 * 
	 * @author ΰ�����
	 */
	public String createPjszCpxzHTML(SearchRsModel rsModel,
			PjszCpxzModel model, ArrayList<String[]> rsArrList, User user) {

		return null;
	}

	/**
	 * �������С��
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx) {

		SearchModel searchModel =myForm.getSearchModel();
		
		// ����������
		String cpzmc = model.getCpzmc();

		// �༶����
		String[] bjdm = model.getBjdm();

		// �꼶
		String[] nj = searchModel.getSearch_tj_nj();
		// ѧԺ
		String[] xy = searchModel.getSearch_tj_xy();
		// רҵ
		String[] zy = searchModel.getSearch_tj_zy();
		// �༶
		String[] bj = searchModel.getSearch_tj_bj();
		
		boolean flag = false;

		try {
			if ("checked".equalsIgnoreCase(saveLx)) {// �������С�����ã���ѡ��
				flag = dao.deleteCpzb(bjdm, user);
				if (flag) {
					flag = dao.insertCpzb(bjdm, cpzmc, user);
				}
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// �������С�����ã�δ��ѡ��
				flag = dao.deleteCpzb(nj, xy, zy, bj, user);
				if (flag) {
					flag = dao.insertCpzb(nj, xy, zy, bjdm, cpzmc, user);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ȡ������С��
	 * 
	 * @author ΰ�����
	 */
	public Boolean deleteCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx) {

		SearchModel searchModel = myForm.getSearchModel();

		// �༶����
		String[] bjdm = model.getBjdm();

		// �꼶
		String[] nj = searchModel.getSearch_tj_nj();
		// ѧԺ
		String[] xy = searchModel.getSearch_tj_xy();
		// רҵ
		String[] zy = searchModel.getSearch_tj_zy();
		// �༶
		String[] bj = searchModel.getSearch_tj_bj();

		boolean flag = false;

		try {
			if ("checked".equalsIgnoreCase(saveLx)) {// ȡ������С�����ã���ѡ��
				flag = dao.deleteCpzb(bjdm, user);
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// ȡ������С�����ã�δ��ѡ��
				flag = dao.deleteCpzb(nj, xy, zy, bj, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �������С���Զ�����
	 * 
	 * @author ΰ�����
	 */
	public boolean saveCpxzZdsz(PjszCpxzModel model, User user) {

		// ���������
		String cpzgz = model.getCpzgz();
		// ����������
		String cpzmc = "";

		if ("bj".equalsIgnoreCase(cpzgz)) {// �༶
			cpzmc = "bjmc";
		} else if ("njzy".equalsIgnoreCase(cpzgz)) {// �꼶רҵ
			cpzmc = "nj||zymc";
		} else if ("xy".equalsIgnoreCase(cpzgz)) {// ѧԺ
			cpzmc = "xymc";
		} else if ("njxy".equalsIgnoreCase(cpzgz)) {// �꼶ѧԺ
			cpzmc = "nj||xymc";
		} else if ("nj".equalsIgnoreCase(cpzgz)) {// �꼶
			cpzmc = "nj";
		}
		
		boolean flag = false;

		try {
			flag = dao.insertCpzb(cpzmc, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ������С���ύ
	 * 
	 * @author ΰ�����
	 */
	public String checkCpxzSubmit(PjszCpxzModel model, User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// �Ƿ���Ҫ������
		String cpz = jbszModel.getCpz();

		String message = "";

		if ("yes".equalsIgnoreCase(cpz)) {
			// ���δά��������İ༶��
			String num = dao.getNoCpzNum(model, user);

			if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
				message = "����" + num + "���༶δ���ò����飬��ǰ��������С�����á����д���";
			}
		}

		return message;
	}
}