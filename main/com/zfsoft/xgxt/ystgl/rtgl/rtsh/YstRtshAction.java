/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-14 ����08:38:54 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtsh;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;

import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.ystgl.rtgl.rtsq.YstRtsqForm;
import com.zfsoft.xgxt.ystgl.rtgl.rtsq.YstRtsqService;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺2016-02-19 ����08:38:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YstRtshAction extends SuperAction<YstRtshForm, YstRtshService> {
	private YstRtshService service = new YstRtshService();
	private final String YstRtsq ="rtsq";
	YstRtsqService rtsqService = new YstRtsqService();
	
	private static final String url = "ystgl_rtgl_rtsh.do";
	
	@SystemAuth(url = url)
	public ActionForward getYstRtshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtshForm model = (YstRtshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "ystgl_rtgl_rtsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getYstRtshList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward Rtdgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtshForm model = (YstRtshForm) form;
		boolean result = false;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ���浥�����
			result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtsq);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("form", model);
		YstRtsqForm rtForm = rtsqService.getModel(model.getRtid());
		HashMap<String, String> ystxx = rtsqService.getYstxxMap(rtForm);
		request.setAttribute("ystxx", StringUtils.formatData(ystxx));
		return mapping.findForward("rtDgsh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward  Rtplsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtshForm model = (YstRtshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("rtPlsh");
	}
	
	/**
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: XXXX����ģ��
	 * @�๦������: TODO(������һ�仰��������������)
	 * @���ߣ� xiaxia[����:1104]
	 * @ʱ�䣺 2016-02-19 ����03:54:47
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtshForm model = (YstRtshForm) form;
		String rtid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model = service.getModel(rtid);
		model.setShzt(shzt);
		model.setRtid(rtid);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: XXXX����ģ��
	 * @�๦������: TODO(������һ�仰��������������)
	 * @���ߣ� xiaxia[����:1104]
	 * @ʱ�䣺 2016-02-19 ����03:54:47
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YstRtshForm model =new YstRtshForm();
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
	
	@SystemAuth(url = url)
	public ActionForward YstRtshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtshForm myForm = (YstRtshForm) form;
		YstRtshForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtsq);
		request.setAttribute("jbxxList", jbxxList);
		YstRtsqForm YstRtsq = rtsqService.getModel(model.getRtid());
		HashMap<String, String> ystxx = rtsqService.getYstxxMap(YstRtsq);
		request.setAttribute("ystxx", StringUtils.formatData(ystxx));
		request.setAttribute("form",model);
		return mapping.findForward("view");
	}
}
