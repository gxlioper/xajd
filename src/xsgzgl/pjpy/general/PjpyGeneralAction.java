package xsgzgl.pjpy.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.index.PjpyIndexInit;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlInit;
import xsgzgl.pjpy.general.pjsz.cpxz.PjszCpxzInit;
import xsgzgl.pjpy.general.pjsz.pjry.PjszPjryInit;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmInit;
import xsgzgl.pjpy.general.pjsz.zcxm.PjszZcxmInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.xmsz.pjtj.XmszPjtjInit;
import xsgzgl.pjpy.general.xmsz.rssz.XmszRsszInit;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͨ��_Action��
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

public class PjpyGeneralAction extends BasicAction {
	
	// =================������ҳ==================================
	
	/**
	 * ��������_��ҳ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjpyIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// �û�����

		String userType=user.getUserType();
		
		if(!"xx".equalsIgnoreCase(userType) && !"admin".equalsIgnoreCase(userType)){
			
			String msg = "��ģ��ֻ���ɹ���Ա����ѧУ�û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= ��ʼ����������ֵ begin ============
		init.initIndex(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjpyIndex.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ��������_��ҳ_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjlcSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initIndex(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjlcSetting.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ��������_��ҳ_��ʼ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjpyStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initIndex(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjpyStart.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ��������_��ҳ_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjpyEnd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjpyEnd(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjpyEnd.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================������ҳ end================================

	// =================�������� begin==================================

	/**
	 * ��������_��������_������Ա����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszPjry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjryInit init = new PjszPjryInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjry(rForm, myForm, user, request);
		
		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjry/pjryManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ��������_��������_����С������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszCpxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszCpxzInit init = new PjszCpxzInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initCpxz(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/cpxz/cpxzManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ��������_��������_�۲���Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszZcxmInit init = new PjszZcxmInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZcxm(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/zcxm/zcxmManage.jsp";
		// ============= ��ʼ����������ֵ end ==============
		
		// �۲���Ŀ�Ժ���չʾ
		if("2".equalsIgnoreCase(myForm.getZcxmzs())){
			url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/zcxm/zcxmByHx.jsp";
		}

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_��������_������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjxmInit init = new PjszPjxmInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjxm/pjxmManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_��������_�༶��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszBjdlInit init = new PjszBjdlInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initBjdl(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/bjdl/bjdlManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_��������_������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjxmSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjxmInit init = new PjszPjxmInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjxm/pjxmSetting.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_��������_������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjxmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjxmInit init = new PjszPjxmInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjxm/pjxmUpdate.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================�������� end==================================

	// =================��Ŀ���� begin================================
	
	/**
	 * ��������_��Ŀ����_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmszPjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		XmszPjtjInit init = new XmszPjtjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjtj(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/xmsz/pjtj/pjtjSetting.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_��Ŀ����_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmszRssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		XmszRsszInit init = new XmszRsszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initRssz(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/xmsz/rssz/rsszManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================��Ŀ���� end==================================
	
	// =================�ۺϲ���==================================
	
	/**
	 * ��������_�ۺϲ���_ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhcpMaintain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return zhcpZcxx(mapping, form, request, response);
	}
	
	/**
	 * ��������_�ۺϲ���_�۲��ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhcpZcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyZhcpInit init = new PjpyZhcpInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZhcp(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/zhcp/zhcpManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return new ActionForward(url, false);
	}
	
	/**
	 * �ۺϲ���_��������_�۲�ֽ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyZhcpInit init = new PjpyZhcpInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZhcpResult(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/zhcp/zhcpResult.jsp";
		
		// ============= ���ø߼���ѯ =============
		// ����ѧ��
		String pjxn = myForm.getPjxn();
		// ����ѧ��
		String pjxq = myForm.getPjxq();
		// �������
		String pjnd = myForm.getPjnd();
		// �߼���ѯ
		SearchModel searchModel = myForm.getSearchModel();
		searchModel.setSearch_tj_zczq(new String[] { pjxn+"luojw"+pjxq+"luojw"+pjnd});

		request.setAttribute("searchTj", searchModel);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		
		
		String tableName="";
		
		if("ss".equalsIgnoreCase(myForm.getRyk())){		
			tableName="xg_view_pjpy_zhcpresult_ss";
		}else{
			tableName="xg_view_pjpy_zhcpresult_ryk";
		}
		request.setAttribute("tableName", tableName);
		
		request.setAttribute("realTable", "xg_pjpy_zhcpb");
		// =================== end ===================

		return new ActionForward(url, false);
	
	}
	
	/**
	 * �ۺϲ���_��������_�۲��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kindChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyZhcpInit init = new PjpyZhcpInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		myForm.getSearchModel().setPath("pjpy_general_zhcp_result.do");

		init.initKindChoose(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/zhcp/kindChoose.jsp";
		// ============= ��ʼ����������ֵ end ============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================�ۺϲ��� end===============================
	
	// =================��ʷ���� begin ==============================
	
	/**
	 * ��������_�ҵ�����_��ʷ��������ѯ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjLspj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initLspj(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/lspjManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_�ҵ�����_��ʷ������ά����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward lspjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initLspjUpdate(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/lspjUpdate.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================��ʷ���� end ================================
	
	// =================�ҵ����� begin ================================
	
	/**
	 * ��������_�ҵ�����_�ҵ���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjMangage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initWdpj(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/wdpjManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_�ҵ�����_ѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjXssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����
		String userType=user.getUserType();

		// ============= ��ʼ����������ֵ begin ============
		init.initXssq(rForm, myForm, user, request);
		
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else if(!Base.isNull(rForm.getMessage())){
			String msg = rForm.getMessage();
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xssq/xssqManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_�ҵ�����_ѧ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjXssqJgcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXssqJgcx(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xssq/xssqResult.jsp";	
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_�ҵ�����_��ʦ�ϱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmsbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initLssb(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/lssb/xmsbManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_�ҵ�����_��Ŀ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXmsh(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xmsh/xmshManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ��������_�ҵ�����_��Ŀ��ˡ���ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXmshDetail(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xmsh/xmshDetail.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_�ҵ�����_��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjBcpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		request.setAttribute("tableName", "view_xg_pjpy_bcpjjg");
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initBcpj(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/bcpjManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * ��������_�ҵ�����_������������ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward bcpjDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initBcpjDetail(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/bcpjDetail.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================�ҵ����� end ==================================
	

}
