package xsgzgl.xsxx.general.xxxg.xgjg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgjgInterface;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸ĽY��_Init��
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

public class XgjgInit extends BasicInit {

	/**
	 * ��Ϣ�޸�_�޸ĽY������ѯ��
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	public void initXgjgSearch(RequestForm rForm, XsxxGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_general_xxxg_xgjg.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * ��Ϣ�޸�_�޸Č��ˡ�Ԕ����
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	public void initXgjgDetail(RequestForm rForm, XsxxGeneralForm myForm,
			XgjgModel model, User user, HttpServletRequest request)
			throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface xgshService = myService.getXxxgXgshService(myForm);
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// ��ՈID
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_general_xxxg_xgsh.do";
		// �����ֶ�
		String[] qtzd = new String[] { "sqid" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { sqid };

		// -----------------�@�ÌW����Ϣ begin-------------------
		XgshModel xgshModel = new XgshModel();
		xgshModel.setSqid(sqid);

		HashMap<String, String> rs = xgshService.getXgshInfo(xgshModel, user);
		
		//zd4�����Ի����� �������ƣ�2��
		if(Base.xxdm.equals("12036")&&null!=rs.get("zd4")){
			//������ʾ�����д���Ϊ���ж�Ӧ����
			XsxxtyglService xsxxService = new XsxxtyglService();
			rs.put("zd4", xsxxService.getYhmc(rs.get("zd4")));
		}
		
		List<HashMap<String, String>> rsList = xgshService.getXgshList(
				xgshModel, user);
		// -----------------�@�ÌW����Ϣ end-------------------

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
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	public void initParameter(XsxxGeneralForm myForm) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgjgInterface service = myService.getXxxgXgjgService(myForm);

		service.initParameter();
	}
}
