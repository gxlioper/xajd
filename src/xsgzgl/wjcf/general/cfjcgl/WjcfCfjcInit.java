package xsgzgl.wjcf.general.cfjcgl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.wjcf.general.GlobalsValue;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;

public class WjcfCfjcInit {

	/**
	 * ��ʼ�����ݡ�Υ�ͽ����
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfjc(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		// ����·��
		String path = "wjcf_general_cfjc.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУ����
		String tableName = "view_wjcf_cfjcsq";
		String realTable = "xg_wjcf_wjcfsbb";
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
		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { });

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * ��ʼ�����ݡ�Υ�ͽ����ˡ�
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfjcsh(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfjcshInterface service = myService.getWjcfCfjcshService(myForm);
		// ����·��
		String path = "wjcf_general_cfjcsh.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУ����
		String tableName = "xg_wjcf_wjcfsbb";
		String realTable = "xg_wjcf_wjcfsbb";
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
//		List<HashMap<String,String>> spgwList = service.getSpgwList(user);
//		
//		request.setAttribute("spgwList", spgwList);
		
		request.setAttribute("isZgjyh", service.isZgjyh(user));
		
		//====================��ʼ��ҳ���������=====================
		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		searchModel.setPath(path);
		searchModel.setSearch_tj_shzt(new String[] {"δ���","������","��ͨ��"});
		// ѧУ,����Ա��ť����Ȩ�޿���
		request.setAttribute("czqx", "admin".equalsIgnoreCase(user.getUserType())
				|| "xx".equalsIgnoreCase(user.getUserType()) ? "yes" : "no");
		request.setAttribute("searchTj", searchModel);
	}
}
