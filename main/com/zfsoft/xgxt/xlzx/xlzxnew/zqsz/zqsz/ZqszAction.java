package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq.XssqForm;
import common.newp.StringUtil;

public class ZqszAction extends SuperAction<ZqszForm, ZqszService> {
	private ZqszService service = new ZqszService();
	private final String url = "xg_xlzxnew_zqrcgl.do";
	/**
	 * 
	 * @����: ��ȡ���ճ�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-14 ����03:37:10
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
	public ActionForward getZrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbrc");
	}
	
	/**
	 * 
	 * @����: ��ѯ���ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-14 ����03:42:02
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
	public ActionForward searchZrcCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ��ȡ���ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-14 ����03:48:31
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
	public ActionForward getYrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_xlzxnew_zqrcgl.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "xlzxnew_zqrcgl.do?method=getYrcList");
		return mapping.findForward("ybrc");
	}
	
	/**
	 * 
	 * @����: ��ѯ���ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-14 ����03:50:03
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
	public ActionForward searchYrcCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getYbzqList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����02:26:31
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
	public ActionForward addZq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����02:39:23
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
	public ActionForward editZq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getModel(myForm.getZbid());
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("rs",model);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("edit");
	}
	
	/**
	 * 
	 * @����: �鿴��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����02:40:55
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
	public ActionForward ckZqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getModel(myForm.getZbid());
		request.setAttribute("rs",model);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
		return mapping.findForward("ckzqsz");
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����03:14:01
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
	public ActionForward  delzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("zbids");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.checkIsZqNotUserd(ids)){
				String message = "���ܴ����ڱ���ʹ�ã��޷�ɾ����";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����04:27:36
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
	public ActionForward saveZqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		if(!service.checkIsZqMcNotUserd(myForm.getZbid(),myForm.getZbzc(),myForm.getXn(),myForm.getXq())){
			String messageKey = "��ѧ��ѧ���ܴ��Ѵ��ڣ�";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		//��֤�����Ƿ��ص�
		if(!service.checkIsTimeNotRepeat(myForm.getZbksrq(),myForm.getZbjsrq(),myForm.getZbid())){
			String messageKey = "�����������ܴ��ص�����ȷ�ϣ�";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		boolean rs = true;
		if(StringUtils.isNotNull(myForm.getZbid())){
			rs = service.runUpdate(myForm);
		}else{
			rs = service.runInsert(myForm);
		}
		String messsageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messsageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����04:27:36
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
	public ActionForward saveYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		if(!service.checkIsYzqMcNotUsed(myForm.getXn(),myForm.getYf(),myForm.getYbid())){
			String messageKey = "��ѧ���·��Ѵ��ڣ�";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		boolean rs = true;
		if(StringUtils.isNull(myForm.getYbid())){
			rs = service.saveYzqsz(myForm);
		}else{
			rs = service.updateYzqsz(myForm);
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS :MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����04:31:15
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
	public ActionForward addYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		myForm.setXn(Base.currXn);
		request.setAttribute("xnList",Base.getXnndList());
		return mapping.findForward("addyzqsz");
	}
	
	/**
	 * 
	 * @����: �޸�����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����04:32:37
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
	public ActionForward editYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getYzqModel(myForm.getYbid());
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("xnList",Base.getXnndList());
		return mapping.findForward("edityzqsz");
	}
	
	/**
	 * 
	 * @����: �鿴����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����04:34:34
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
	public ActionForward ckYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getYzqModel(myForm.getYbid());
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("ckyzqsz");
	}
	
	/**
	 * 
	 * @����: �鿴���ճ���ϸ��Ϣ(���ϱ���δ�ϱ�)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����04:41:18
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
	public ActionForward ckZqDetailxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("sbbjlx", request.getParameter("sbbjlx"));
		request.setAttribute("zbid", request.getParameter("zbid"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ckzqdetailxx");
	}
	
	/**
	 * 
	 * @����: ��ѯ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-23 ����08:53:55
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
	public ActionForward searchSbxxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		String sbbjlx = request.getParameter("sbbjlx");
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getZqsbxxqkCx(myForm, user, sbbjlx);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-22 ����02:55:28
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
	public ActionForward delYzqSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//��֤�ܴ��Ƿ�ʹ��
			if(service.checkIsYzqNotUserd(ids)){
				String message = "���ܴ����ڱ���ʹ�ã��޷�ɾ����";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			boolean result = service.delYzqSz(ids);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
}
