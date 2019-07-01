package xsgzgl.dtjs.general.tyjf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.DtjsGeneralService;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���Ž���_��Ա�ɷ�_ͨ��_Init��
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

public class DtjsTyjfInit {

	/**
	 * ��ʼ�����ݡ���Ա�ɷѡ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initTyjf(RequestForm rForm, DtjsGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);

		// ����·��
		String path = "dtjs_general_tyjf.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУ����
		String tableName = "xg_xsxx_xjydb";
		String realTable = "xg_dtjs_tyjfb";
		// ѧУƴ������
		String xxpymc = (Base.isNull(myForm.getXxpymc()))?GlobalsValue.getXxpymc(xxdm):myForm.getXxpymc();
		myForm.setXxpymc(xxpymc);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		
		//====================��ʼ��ҳ���������=====================
		String xn = Base.currXn;
		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { xn });

		request.setAttribute("searchTj", searchModel);
	}
}
