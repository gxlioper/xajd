package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg.ZbjgService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszService;

public class ZbsbAction extends SuperAction<ZbsbForm,ZbsbService> {
	private ZbsbService service = new ZbsbService();
	private final String url = "xg_xlzxnew_zbsb.do";
	/**
	 * 
	 * @����: �ܱ��ϱ���ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����09:05:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getZbsbListCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		User user = getUser(request);
		if(!"stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String xssq = service.xssqCheck(user.getUserName());
		if("N".equals(xssq)){
			String msg = "��û�и���ҳ�ķ���Ȩ�ޣ�����ϵ����Ա��";
			request.setAttribute("message",msg);
			return mapping.findForward("error");
		}
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("nowtime",GetTime.getTimeByFormat("yyyy-mm-dd"));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		//������������ɶ�ӿ�����ɾ�İ�ť
		request.setAttribute("cssz", new XlzxSbService().getModel("zb"));
		request.setAttribute("path", url);
		request.setAttribute("rzflag",service.checkRzrq(user.getUserName())+"");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbsbcx");
	}
	
	/**
	 * 
	 * @����: �ܱ��ϱ���ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����10:18:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward searchZbsbCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: �ܱ��ϱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����01:49:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward addZbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		ZqszForm zbrc = new ZqszService().getModel(myForm.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		User user = getUser(request);
		request.setAttribute("bjxx", service.getBjxx(user.getUserName()));
		return mapping.findForward("zbsb");
	}
	
	/**
	 * 
	 * @����: �ܱ��ϱ��޸�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:00:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward editZbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		ZbsbForm model = service.getModel(myForm.getSbsqid());
		BeanUtils.copyProperties(myForm, model);
		ZqszForm zbrc = new ZqszService().getModel(model.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		User user = getUser(request);
		request.setAttribute("bjxx", service.getBjxx(user.getUserName()));
		request.setAttribute("wtryInfo", service.getZbWtryInfo(myForm.getSbsqid()));
		return mapping.findForward("zbsbedit");
	}
	
	/**
	 * 
	 * @����: �����ϱ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:33:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public ActionForward saveSbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		String[] xhArray = request.getParameterValues("xh");
		String[] zbwtmsArray = request.getParameterValues("zbwtms");
		myForm.setXhArray(xhArray);
		myForm.setZbwtmsArray(zbwtmsArray);
		myForm.setXh(getUser(request).getUserName());
		ZbsbService tranService = TransactionControlProxy.newProxy(new ZbsbService());
		boolean rs = tranService.saveZbsb(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
	    return null;
	}
	
	/**
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����10:10:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		String value = request.getParameter("sbsqid");
		myForm.setSbsqid(value);
		ZbsbForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����10:28:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("sbsqid");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			ZbsbForm model = new ZbsbForm();
			model.setSbsqid(sqid);
			model.setSplcid(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴�ܱ��ϱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����11:12:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ckZbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		ZbsbForm model = service.getModel(myForm.getSbsqid());
		BeanUtils.copyProperties(myForm, model);
		ZqszForm zbrc = new ZqszService().getModel(model.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		request.setAttribute("bjxx", new ZbjgService().getTeaBjxx(model.getBjdm()));
		request.setAttribute("wtryInfo", service.getZbWtryInfo(myForm.getSbsqid()));
		return mapping.findForward("zbsbview");
	}
	
	/**
	 * 
	 * @����: ����ѧ����תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����11:23:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward addStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setAttribute("xhs",request.getParameter("xhs"));
		request.setAttribute("bjdm",request.getParameter("bjdm"));
		request.setAttribute("path","xg_xlzxnew_addBjStu.do");
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("addstu");
	}
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����11:43:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward searchForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		String xhs = request.getParameter("xhs");
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getStuCx(myForm, user, xhs);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
}
