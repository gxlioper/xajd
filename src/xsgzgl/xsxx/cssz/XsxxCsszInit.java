package xsgzgl.xsxx.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.rcsw.qjgl.RcswQjglForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������_Init��
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

public class XsxxCsszInit {

	/**
	 * �����������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public XsxxCsszInterface getCsszService(String gnmk) throws Exception {

		String className = "xsgzgl.xsxx.cssz." + gnmk + ".XsxxCsszService";

		Class interFaceClass = Class.forName(className);

		XsxxCsszInterface service = (XsxxCsszInterface) interFaceClass
				.getConstructor(null).newInstance(null);

		return service;
	}
	
	/**
	 * ��������_������Ϣ_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initGrxx(RequestForm rForm, XsxxCsszForm model,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_cssz_grxx.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		
		//��������List
		List<HashMap<String, String>> splcList = XtwhShlcService.getSplcList("xsxx");
		request.setAttribute("splcList", splcList);
		
//		// ��List
//		List<HashMap<String, String>> monthList = getOptionList("month");
//		request.setAttribute("monthList", monthList);
//
//		// ��List
//		List<HashMap<String, String>> dayList = getOptionList("day");
//		request.setAttribute("dayList", dayList);
	}
	
	private List<HashMap<String, String>> getDefaultValue(XsxxCsszForm model,
			String path) {

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		if ("rcsw_qjgl_cssz_qjlc.do".equalsIgnoreCase(path)) {// �������
			
			String[] en = new String[] { "xh" };
			String[] cn = new String[] { "ѧ��" };
			
			topTr=dao.arrayToList(en, cn);
		}

		return topTr;
	}
	
	/**
	 * ��������б�ֵ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getOptionList(String lx) {

		DAO dao = DAO.getInstance();

		// ��˽��������
		String[] en = null;
		String[] cn = null;

		if ("month".equalsIgnoreCase(lx)) {
			en = new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12" };
			cn = new String[] { "01��", "02��", "03��", "04��", "05��", "06��",
					"07��", "08��", "09��", "10��", "11��", "12��" };
		} else if ("day".equalsIgnoreCase(lx)) {
			en = new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
					"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
					"29", "30", "31" };
			cn = new String[] { "01��", "02��", "03��", "04��", "05��", "06��",
					"07��", "08��", "09��", "10��", "11��", "12��", "13��", "14��",
					"15��", "16��", "17��", "18��", "19��", "20��", "21��", "22��",
					"23��", "24��", "25��", "26��", "27��", "28��", "29��", "30��",
					"31��" };
		}

		return dao.arrayToList(en, cn);
	}
}
