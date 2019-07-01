package com.zfsoft.xgxt.rcsw.qjgl.xjsqsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb.YbsbForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh.YbshForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh.YbshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class XjsqshAction extends SuperAction<XjsqshForm,XjsqshService> {
	private XjsqshService service = new XjsqshService();
	private static final String RCSWRCXW = "rcswqjgl_qjgl";
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-21 ����03:01:59
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
	public ActionForward xjsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		QjjgForm qjjg = new QjjgForm();
		BeanUtils.copyProperties(qjjg, xjsqshForm);
		// ѧ����Ϣ
		if (!StringUtil.isNull(xjsqshForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xjsqshForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		
		request.setAttribute("jbxxList", jbxxList);
		XjsqshForm xssqshModel = service.getModelOfqjjgid(xjsqshForm.getQjjgid());

		if(xssqshModel != null){
			BeanUtils.copyProperties(xjsqshForm, xssqshModel);
		}
		QjjgForm dataForm =new QjjgService().getModel(qjjg);
		request.setAttribute("data",StringUtils.formatData(dataForm));
		//�������
		QjlxService qjlx=new QjlxService();

		request.setAttribute("qjlxmc",qjlx.getModel(dataForm.getQjlxid()).getQjlxmc());
		if(xssqshModel != null && (!"0".equals(xssqshModel.getShzt()) && !"3".equals(xssqshModel.getShzt()) ) ){
			return mapping.findForward("ckxjsh");
		}
		return mapping.findForward("xjsqAdd");
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-21 ����03:07:47
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
	public ActionForward saveXjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		User user = getUser(request);
		xjsqshForm.setXjr(user.getUserName());
		xjsqshForm.setXh(user.getUserName());
		boolean rs = service.saveForm(xjsqshForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 *
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-22 ����02:03:49
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
	public ActionForward dgXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		QjjgForm qjjg = new QjjgForm();
		XjsqshForm xssqshModel = service.getModel(xjsqshForm);
		qjjg.setQjjgid(xssqshModel.getQjjgid());
		// ѧ����Ϣ
		if (!StringUtil.isNull(xjsqshForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xjsqshForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		if(xssqshModel != null){
			BeanUtils.copyProperties(xjsqshForm, xssqshModel);
		}
		xjsqshForm.setShid(request.getParameter("shid"));
		// ѧ����Ϣ
		if (!StringUtil.isNull(xssqshModel.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xssqshModel.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		QjjgForm dataForm =new QjjgService().getModel(qjjg);
		request.setAttribute("data",StringUtils.formatData(dataForm));
		//�������
		QjlxService qjlx=new QjlxService();

		request.setAttribute("qjlxmc",qjlx.getModel(dataForm.getQjlxid()).getQjlxmc());
		return mapping.findForward("dgsh");
	}
	
	/**
	 * 
	 * @����: ������˱���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-22 ����01:54:53
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
	public ActionForward saveDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm model = (XjsqshForm)form;
		User user = getUser(request);
		// ���浥�����
		boolean result = service.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-22 ����02:05:00
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
	public ActionForward plXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("plsh");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-22 ����02:05:40
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm model = (XjsqshForm)form;
		User user = getUser(request);
		String message = service.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����: ���������ת
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-22 ����03:08:33
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
	public ActionForward xjShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_qjgl_xjsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjshlist");
	}
	
	/**
	 * 
	 * @����: ��ѯ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-22 ����03:10:26
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
	public ActionForward searchXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		xjsqshForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(xjsqshForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-26 ����04:13:06
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
	public ActionForward ckXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		// ѧ����Ϣ
		if (!StringUtil.isNull(xjsqshForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xjsqshForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		XjsqshForm xssqshModel = service.getModel(xjsqshForm);
		if(xssqshModel != null){
			BeanUtils.copyProperties(xjsqshForm, xssqshModel);
		}
		request.setAttribute("data", StringUtils.formatData(new QjjgService().getModel(xjsqshForm.getQjjgid())));
		return mapping.findForward("ckxjsh");
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-26 ����04:23:06
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
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qjjgid = request.getParameter("qjjgid");
		XjsqshForm myForm = service.getModelOfqjjgid(qjjgid);
		String message ="";
		if(myForm == null || StringUtil.isNull(myForm.getShzt())){
			message = "�����������¼���޷�������";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if(myForm != null && !"5".equals(myForm.getShzt()) ){
			message = "ֻ�ܳ������ύ��δ����˵ļ�¼��";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(myForm.getYwid(), myForm.getSplc());
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(myForm.getYwid())) > 0) {
				myForm.setShzt(Constants.YW_YTH);
			} else {
				myForm.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(myForm);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��ʦ�����һ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-26 ����04:50:55
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XjsqshForm model = (XjsqshForm) form;
		String sqid = request.getParameter("sqid");
		model.setYwid(sqid);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��ʦ�˳���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-26 ����04:58:19
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjsqshForm model =new XjsqshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		

		// ��˳����ɹ�
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-27 ����05:10:32
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.isCanDelete(ids)){
				String message = "ֻ��ɾ��δ�ύ�����˻ص����룡";
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
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-27 ����05:21:41
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
		XjsqshForm myForm = (XjsqshForm)form;
		XjsqshForm model = service.getModelOfqjjgid(myForm.getQjjgid());
		String message= "";
		if(model == null || StringUtil.isNull(model.getShzt())){
			message = "����δ�Դ�����������٣��������ύ������";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if(model != null && (!"0".equals(model.getShzt()) && !"3".equals(model.getShzt()))){
			message = "ֻ���ύδ�ύ�����˻صļ�¼��";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ���̸�����д
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-4 ����04:32:58
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
	public ActionForward lcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		XjsqshForm xssqshModel = service.getModelOfqjjgid(sqid);
		if(xssqshModel == null || StringUtils.isNull(xssqshModel.getYwid())){
			request.setAttribute("message", "������¼��δ��������");
			return mapping.findForward("error");
		}
		sqid = xssqshModel.getYwid();
		String splc = xssqshModel.getSplc();
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(sqid);
		List<HashMap<String,String>> gwList = ShlcUtil.getSpbzBySplc(splc);
		request.setAttribute("infoList", infoList);
		request.setAttribute("gwList", gwList);
		HashMap<String,String> dqgw = ShlcUtil.getDqGwbz(splc,sqid);
		request.setAttribute("dqgw", dqgw);
		request.setAttribute("gwListSize", infoList.size()-1);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("lcgz");
	}
}
