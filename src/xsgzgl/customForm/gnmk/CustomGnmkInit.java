package xsgzgl.customForm.gnmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaService;
import xsgzgl.rcsw.qjgl.RcswQjglForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Զ����_�Զ��幦��_Init��
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

public class CustomGnmkInit {

	/**
	 * �Զ��幦��_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initCustomGnmk(RequestForm rForm, CustomGnmkForm model,
			User user, HttpServletRequest request) {

		CustomGnmkDAO dao = new CustomGnmkDAO();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "customGnmk.do?method=customGnmkManage";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, user);
		// ID
		String gnmkdm = request.getParameter("gnmkdm");
		gnmkdm = Base.isNull(gnmkdm) ? model.getGnmkdm() : gnmkdm;
		path += "&gnmkdm=" + gnmkdm;
		// ����ģ����Ϣ
		initCustomGnmkModel(model, user);
		// ������Ŀ��
		String xmb = model.getXmb();

		// �����ֶ�
		String[] qtzd = new String[] { "gnmkdm", "xmb" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { gnmkdm, xmb };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

		// ��ѯ�����б�
		List<HashMap<String, String>> searchContentList = dao
				.getSearchContentList(model, user);

		model.setSearchContentList(searchContentList);
	}

	/**
	 * ��ʼ��������
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getDefaultValue(CustomGnmkForm model,
			User user) {

		CustomGnmkDAO customGnmkDAO = new CustomGnmkDAO();

		DAO dao = DAO.getInstance();

		// ��ͷ�б�
		List<HashMap<String, String>> topList = customGnmkDAO.getGnmkTopList(
				model, user);

		List<String> enList = new ArrayList<String>();
		List<String> cnList = new ArrayList<String>();

		if (topList != null && topList.size() > 0) {
			for (int i = 0; i < topList.size(); i++) {
				HashMap<String, String> top = topList.get(i);
				enList.add(top.get("dm"));
				cnList.add(top.get("mc"));
			}
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(enList
				.toArray(new String[] {}), cnList.toArray(new String[] {}));

		return topTr;
	}
	
	/**
	 * ��ù���ģ����Ϣ
	 * 
	 * @author ΰ�����
	 */
	private HashMap<String, String> getGnmkInfo(String gnmkdm) {

		String tableName = "xg_custom_gnmkb";
		String[] colList = new String[] { "id", "tablename" };
		String pk = "gnmkdm";
		String pkValue = gnmkdm;

		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * ��ʼ��������
	 * 
	 * @author ΰ�����
	 */
	private void initCustomGnmkModel(CustomGnmkForm model, User user) {
		//����ģ�����
		String gnmkdm = model.getGnmkdm();

		HashMap<String, String> gnmkInfo = getGnmkInfo(gnmkdm);
		
		String gnmk_id = gnmkInfo.get("id");
		String xmb = gnmkInfo.get("tablename");
		
		model.setXmb(xmb);

	}
}
