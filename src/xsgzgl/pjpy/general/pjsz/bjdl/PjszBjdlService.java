package xsgzgl.pjpy.general.pjsz.bjdl;

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

public class PjszBjdlService extends CommService implements PjszBjdlInterface {

	PjszBjdlDAO dao = new PjszBjdlDAO();

	/**
	 * ��ñ�ͷ�ļ�(��������_�༶����)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjszBjdlTop(PjszBjdlModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"bjdlmc" };
		String[] cn = new String[] { "", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶",
				"�༶����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����(��������_�༶����)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getPjszBjdlList(PjpyGeneralForm myForm,
			PjszBjdlModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszBjdlList(myForm, user);
	}

	/**
	 * ���������(��������_�༶����)
	 * 
	 * @author ΰ�����
	 */
	public String createPjszBjdlHTML(SearchRsModel rsModel,
			PjszBjdlModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ����༶����
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
			User user, String saveLx) {

		SearchModel searchModel = myForm.getSearchModel();

		// �༶��������
		String bjdlmc = model.getBjdlmc();

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
			if ("checked".equalsIgnoreCase(saveLx)) {// ����༶�������ã���ѡ��
				flag = dao.deleteBjdlb(bjdm, user);
				if (flag) {
					flag = dao.insertBjdlb(bjdm, bjdlmc, user);
				}
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// ����༶�������ã�δ��ѡ��
				flag = dao.deleteBjdlb(nj, xy, zy, bj, user);
				if (flag) {
					flag = dao.insertBjdlb(nj, xy, zy, bj, bjdlmc, user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ȡ���༶����
	 * 
	 * @author ΰ�����
	 */
	public Boolean deleteBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
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
			if ("checked".equalsIgnoreCase(saveLx)) {// ȡ���༶�������ã���ѡ��
				flag = dao.deleteBjdlb(bjdm, user);
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// ȡ���༶�������ã�δ��ѡ��
				flag = dao.deleteBjdlb(nj, xy, zy, bj, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ��ѯ�༶�����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBjdlList() {
		return dao.getBjdlList();
	}
	
	/**
	 * ��ѯĳѧ���İ༶����
	 * 
	 * @author ΰ�����
	 */
	public String getBjdl(String xh) {
		return dao.getBjdl(xh);
	}
}