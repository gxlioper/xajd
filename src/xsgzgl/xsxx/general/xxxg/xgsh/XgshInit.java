package xsgzgl.xsxx.general.xxxg.xgsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸Č���_Init��
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

public class XgshInit extends BasicInit {

	/**
	 * ��Ϣ�޸�_�޸Č��ˡ���ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initXgshSearch(RequestForm rForm, XsxxGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_general_xxxg_xgsh.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_shztTwo(new String[] { "δ���", "������" });
		request.setAttribute("searchTj", searchModel);
	}

	/**
	 * ��Ϣ�޸�_�޸Č��ˡ�Ԕ����
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initXgshDetail(RequestForm rForm, XsxxGeneralForm myForm,
			XgshModel model, User user, HttpServletRequest request)
			throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// ��ՈID
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		// ��λID
		String gwid = request.getParameter("gwid");
		model.setGwid(gwid);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_general_xxxg_xgsh.do";
		// �����ֶ�
		String[] qtzd = new String[] { "sqid", "gwid" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { sqid, gwid };

		HashMap<String, String> rs = service.getXgshInfo(model, user);
		//zd4�����Ի����� �������ƣ�2��
		if(Base.xxdm.equals("12036")&&null!=rs.get("zd4")){
			//������ʾ�����д���Ϊ���ж�Ӧ����
			XsxxtyglService xsxxService = new XsxxtyglService();
			rs.put("zd4", xsxxService.getYhmc(rs.get("zd4")));
		}	
		List<HashMap<String, String>> rsList = service.getXgshList(model, user);
		rForm.setRsList(rsList);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	/**
	 * ��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initParameter(XsxxGeneralForm myForm) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		service.initParameter();
	}
}
