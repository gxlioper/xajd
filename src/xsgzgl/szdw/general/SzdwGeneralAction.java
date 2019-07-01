package xsgzgl.szdw.general;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.szdw.general.dwwh.DwwhInit;
import xsgzgl.szdw.general.szbb.SzbbInit;
import xsgzgl.szdw.general.szbb.SzbbService;
import xsgzgl.szdw.general.tjcx.bmqk.BmqkInit;
import xsgzgl.szdw.general.tjcx.bmqk.BmqkService;
import xsgzgl.szdw.general.tjcx.szry.SzryInit;
import xsgzgl.szdw.general.tjcx.szry.SzryService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszForm;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͨ��_Action��
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

public class SzdwGeneralAction extends BasicAction {

	// ================== ˼������ά�� begin ==============================

	/**
	 * ҳ����ת��˼������ά����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwDwwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		DwwhInit init = new DwwhInit();
		User user = getUser(request);// �û�����
		
		String userStatus=user.getUserStatus();
		
		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus) || "sy".equalsIgnoreCase(userStatus))) {
			String msg = "��ģ��ֻ����"+Base.YXPZXY_KEY+"����Ժ����ѧУ�û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= ��ʼ����������ֵ begin ============
		init.initDwwhSearch(rForm, myForm, user, request);
		init.initParameter(myForm);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		request.setAttribute("path", "szdw_general_dwwh.do");
		String url = "/xsgzgl/szdw/" + xxpymc + "/dwwh/dwwhSearch.jsp";
		request.setAttribute("tableName", "view_fdybbqk");
		request.setAttribute("realTable", "fdyxxb");
		
		//��ȡ���ʱ�����
		SzdwCsszService szdwCsszService = new SzdwCsszService();
		SzdwCsszForm bbsjModel = szdwCsszService.getModel(CsszUtil.SZDW_BBSJ);
		request.setAttribute("bbsjModel", bbsjModel);
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	public ActionForward fdyxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();

		User user = getUser(request);// �û�����
		
		String userStatus=user.getUserStatus();
		
		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus))) {
			String msg = "��ģ��ֻ����"+Base.YXPZXY_KEY+"����ѧУ�û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> list = service.getJsxxList(myForm,user);
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
		}
		request.setAttribute("path", "szdw_general_fdyxxwh.do");
		return mapping.findForward("jsxxList");
	}
	
	/**
	 * ҳ����ת��˼����Ա��ࡿ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwRybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		DwwhInit init = new DwwhInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initRybbSetting(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		//String url = "/xsgzgl/szdw/" + xxpymc + "/dwwh/rybbSetting.jsp";
		String url = "/xsgzgl/szdw/" + xxpymc + "/dwwh/rybbSetup.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// ================== ˼������ά�� end ================================

	// ==================˼�������� begin================================
	
	/**
	 * ��ѯ��˼�������ࡿ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwSzBzrbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		SzbbInit init = new SzbbInit();
		User user = getUser(request);// �û�����

		String userStatus=user.getUserStatus();
		
		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus) || "sy".equalsIgnoreCase(userStatus))) {
			String msg = "��ģ��ֻ����"+Base.YXPZXY_KEY+"����Ժ����ѧУ�û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= ��ʼ����������ֵ begin ============
		/*init.initSzbbSearch(rForm, myForm, user, request);
		
		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/szdw/" + xxpymc + "/szbb/szBzrbbSearch.jsp";
		//��ȡ���ʱ�����
		SzdwCsszService szdwCsszService = new SzdwCsszService();
		SzdwCsszForm bbsjModel = szdwCsszService.getModel(CsszUtil.SZDW_BBSJ);
		request.setAttribute("bbsjModel", bbsjModel);
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);*/
		// =================== end ===================
		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> list = service.getSzBzrbbList(myForm,user);
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
		}
		request.setAttribute("path", "szdw_general_szbzrbb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bzrBbList");
	}

	public ActionForward xsxxList(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();

		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> list = service.getXsxxList(myForm);
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
		}
		request.setAttribute("bbType",myForm.getBbType());
		request.setAttribute("bjdm",myForm.getBjdm());
		return mapping.findForward("xsxxList");
	}
	/**
	 * ��ѯ��˼�������ࡿ
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwSzbb(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		User user = getUser(request);// �û�����

		String userStatus=user.getUserStatus();

		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus) || "sy".equalsIgnoreCase(userStatus))) {
			String msg = "��ģ��ֻ����"+Base.YXPZXY_KEY+"����Ժ����ѧУ�û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= ��ʼ����������ֵ begin ============
//		init.initSzbbSearch(rForm, myForm, user, request);

		// ѧУƴ������
//		String xxpymc = myForm.getXxpymc();
		// ��ת·��
//		String url = "/xsgzgl/szdw/" + xxpymc + "/szbb/szbbSearch.jsp";
		//��ȡ���ʱ�����
		/*SzdwCsszService szdwCsszService = new SzdwCsszService();
		SzdwCsszForm bbsjModel = szdwCsszService.getModel(CsszUtil.SZDW_BBSJ);
		request.setAttribute("bbsjModel", bbsjModel);
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);*/
		// =================== end ===================
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			// ��ѯ
			List<HashMap<String, String>> resultList = service.getSzbbList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "szdw_general_szbb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdyBbList");
	}

	/**
	 * ˼�������죨����Ա�������Σ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szbbSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		SzbbInit init = new SzbbInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initSzbbSearch(rForm, myForm, user, request);

		// ѧУƴ������
//		String xxpymc = myForm.getXxpymc();
		// ��ת·��
//		String url = "/xsgzgl/szdw/" + xxpymc + "/szbb/szbbSetting.jsp";
		
		String fplx=request.getParameter("fplx");
		String sydm = request.getParameter("sydm");
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// �������
		request.setAttribute("fplx", fplx);
		request.setAttribute("sydm",sydm);
		FormModleCommon.setAllNjXyZyBjListForFdyBzr(request);
		// =================== end ===================

		return mapping.findForward("szbbSetting");
	}
	
	// ==================˼�������� end=======================================
	
	// ================== ͳ�Ʋ�ѯ begin =================================

	/**
	 * ҳ����ת��˼����Աͳ�ơ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tjcxSzry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		SzryInit init = new SzryInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initSzrySearch(rForm, myForm, user, request);
	
		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/szdw/" + xxpymc + "/tjcx/szry/szryStat.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		//searchModel.setSearch_tj_bbzt(new String[]{"1"});
		request.setAttribute("searchTj", searchModel);
		
		// =================== end ===================

		return new ActionForward(url, false);
	}
	

	/**
	 * ҳ����ת���������ͳ�ơ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tjcxBmqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		BmqkInit init = new BmqkInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initBmqkSearch(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/szdw/" + xxpymc + "/tjcx/bmqk/bmqkStat.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// ================== ͳ�Ʋ�ѯ end ===================================
	
	
	
	/**
	 * 
	 * �������ͳ�ƣ���ϸ��Ϣ��
	 * by : quph
	 */
	public ActionForward getBmtjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
//		RequestForm rForm = new RequestForm();
//		
//		// ==================�߼���ѯ���========================
//		rForm.setPath("szdw_general_tjcx_bmqk.do");
//		SearchModel searchModel = service.setSearchModel(rForm, request);
//		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================
		
		List<HashMap<String, String>> bjqkList = service.getBmtjInfo(myForm);
		
		request.setAttribute("bjqkList", bjqkList);
		return mapping.findForward("getBjqkInfo");
	}
	
	/**
	 * 
	 * ���ศ��Ա���ͳ�ƣ���ϸ��Ϣ��
	 * by : Huang Chenji
	 */
	public ActionForward getFdyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		List<HashMap<String, String>> fdyList = service.getFdyInfo(myForm);
		
		request.setAttribute("fdyList", fdyList);
		return mapping.findForward("getFdyInfo");
	}
	
	/**
	 * 
	 * ������������ͳ�ƣ���ϸ��Ϣ��
	 * by : Huang Chenji
	 */
	public ActionForward getBzrInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		List<HashMap<String, String>> bzrList = service.getBzrInfo(myForm);
		
		request.setAttribute("bzrList", bzrList);
		return mapping.findForward("getBzrInfo");
	}

	/**
	 * 
	 * �������ͳ�ƣ���ϸ��Ϣ������
	 * by : quph
	 */
	public ActionForward exportBmtjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportBmtjInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	
	/**
	 * 
	 * ��ศ��Ա���ͳ�ƣ���ϸ��Ϣ������
	 * by : Huang chenji
	 */
	public ActionForward exportFdyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportFdyInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	
	/**
	 * 
	 * �����������ͳ�ƣ���ϸ��Ϣ������
	 * by : Huang chenji
	 */
	public ActionForward exportBzrInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportBzrInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	
	/**
	 * ˼����Աͳ��--��ϸ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSzryInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzryService service = new SzryService();
		RequestForm rForm = new RequestForm();
		
		if (!StringUtil.isNull(myForm.getXb())){
			myForm.setXb(new String(myForm.getXb().getBytes("ISO-8859-1"),"GBK"));
		}
		
		// ==================�߼���ѯ���========================
		rForm.setPath("szdw_general_tjcx_szry.do");
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		List<HashMap<String, String>> szryList = service.getSzryInfo(myForm);
		
		request.setAttribute("szryList", szryList);
		return mapping.findForward("getSzryInfo");
	}
	
	
	/**
	 * 
	 * ˼����Աͳ�ƣ���ϸ��Ϣ������
	 * by : quph
	 */
	public ActionForward exportSzryInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzryService service = new SzryService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportSzrytjInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	public ActionForward setQQqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm model = (SzdwGeneralForm)form;
		SzbbService service = new SzbbService();
		String bjdm = request.getParameter("bjdm");
		String nj1 =  request.getParameter("nj");
		String xymc1 =  request.getParameter("xymc");
		String zymc1 =  request.getParameter("zymc");
		String rs1 =  request.getParameter("rs");
		/*String fdyxm1 = request.getParameter("fdyxm");
		String bzrxm1 = request.getParameter("bzrxm");*/
		String bjmc = service.getBjmcForBjdm(bjdm);
		String qqqh = service.getCxQQqh(bjdm);
		request.setAttribute("bjdm", bjdm);
		request.setAttribute("bjmc", bjmc);
		request.setAttribute("qqqh", qqqh);
		request.setAttribute("nj", nj1);
		request.setAttribute("xymc", xymc1);
		request.setAttribute("zymc", zymc1);
		request.setAttribute("rs", rs1);
		/*request.setAttribute("fdyxm", fdyxm1);
		request.setAttribute("bzrxm", bzrxm1);	*/	
		if (SAVE.equals(model.getType())) {
			boolean result = service.setQQqh(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		
		return mapping.findForward("setQQqh");
	}
}
