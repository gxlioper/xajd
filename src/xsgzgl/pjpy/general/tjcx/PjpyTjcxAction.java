package xsgzgl.pjpy.general.tjcx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_ͨ��_Action��
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

public class PjpyTjcxAction extends BasicAction {

	/**
	 * �۲�༶�������еȼ����ԡ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="������������-ͳ�ƹ���-�۲�ͳ��-��ѯ���еȼ����ԣ�")
	public ActionForward zcbjmdDjks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZcbjmdDjks(rForm, myForm, user, request);
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/zcbjmd/djks.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		request.setAttribute("searchTj", searchModel);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * �۲�༶�������޵ȼ����ԡ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="������������-ͳ�ƹ���-�۲�ͳ��-��ѯ���޵ȼ����ԣ�")
	public ActionForward zcbjmdNoDjks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZcbjmdNoDjks(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/zcbjmd/zcmd.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		request.setAttribute("searchTj", searchModel);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="������������-ͳ�ƹ���-�۲�ͳ��-����������")
	public ActionForward hjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initHjmdhz(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/hjmdhz/hjmdhz.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * �񽱽�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="������������-ͳ�ƹ���-�۲�ͳ��-�񽱽�����")
	public ActionForward hjjehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initHjjehz(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/hjjehz/hjjehz.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * �Ƽ��������ܱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author qlj
	 * @return
	 */
	@SystemLog(description="������������-ͳ�ƹ���-�۲�ͳ��-�Ƽ���������")
	public ActionForward tjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initTjmdhz(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/tjmdhz/tjmdhz.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������ѧ���Ƽ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author qlj
	 * @return
	 */
	@SystemLog(description="������������-ͳ�ƹ���-�۲�ͳ��-��������ѧ���Ƽ���������")
	public ActionForward knstjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initKnsTjmdhz(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/tjmdhz/knstjmdhz.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��ý����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="������������-ͳ�ƹ���-�۲�ͳ��-��ý����������")
	public ActionForward cmhjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initCmhjmdhz(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/hjjehz/cmhjmdhz.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
}
