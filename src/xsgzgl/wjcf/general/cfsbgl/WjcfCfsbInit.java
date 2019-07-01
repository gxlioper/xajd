package xsgzgl.wjcf.general.cfsbgl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.wjcf.general.GlobalsValue;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfshInterface;

public class WjcfCfsbInit {

	/**
	 * ��ʼ�����ݡ�Υ���ϱ���
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfsb(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		// ����·��
		String path = "wjcf_general_cfsb.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУ����
		String tableName = "view_wjcf_cfsb";
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
		// ѧУ,����Ա��ť����Ȩ�޿���
		request.setAttribute("czqx", "admin".equalsIgnoreCase(user.getUserType())
				|| "xx".equalsIgnoreCase(user.getUserType()) ? "yes" : "no");
		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * ��ʼ�����ݡ�Υ����ˡ�
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfsh(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfshInterface service = myService.getWjcfCfshService(myForm);
		
		SearchModel searchModel=new SearchModel();
		// ����·��
		String path = "wjcf_general_cfsh.do";
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
		
		//====================��ʼ��ҳ���������=====================
		// �߼���ѯ
		searchModel.setPath(path);
		searchModel.setSearch_tj_shzt(new String[] {"δ���","������","��ͨ��"});
		
		request.setAttribute("isZgjyh", service.isZgjyh(user));//�ж��Ƿ���߼��û������ύ��ť
		request.setAttribute("searchTj", searchModel);
	}
	
}
