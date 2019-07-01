/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-20 ����03:54:47 
 */
package com.zfsoft.xgxt.xsztz.tzxmsh;

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
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqService;
import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-20 ����03:54:47
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsXmShAction extends SuperAction<XsXmShForm, XsXmShService> {
	private XsXmShService service = new XsXmShService();
	private final String TZXMSQ ="tzxmsq";
	
	private static final String url = "sztz_xmsqgl_xmsh.do";
	
	@SystemAuth(url = url)
	public ActionForward getXsXmShList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmShForm model = (XsXmShForm) form;
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
		request.setAttribute("path", "sztz_xmsqgl_xmsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxmshlist");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward XsXmDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmShForm model = (XsXmShForm) form;
		boolean result = false;
		//GzkhKhsqService khsqService = new GzkhKhsqService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
//			String message = khsqService.checkZjeAndGs(model.getXh(),model.getXn(),model.getYxgs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
//			if(!"true".equals(message)){
//				 response.getWriter().print(getJsonMessage(message));
//				 return null;
//			}
			User user = getUser(request);
			// ���浥�����
			String flag = service.saveSh(model, user);
			if(flag.equals("true")){
				result = true;
			}else if(flag.equals("false")){
				result = false;
			}else{
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", flag);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("form", model);
		XsXmSqService service = new XsXmSqService();
		HashMap<String, String> hdmap = service.getHdMap(model.getXmdm(),model.getXn(),model.getXq());
		request.setAttribute("hdmap", StringUtils.formatData(hdmap));
		return mapping.findForward("xsxmDgsh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward  XsXmPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmShForm model = (XsXmShForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("xsxmPlsh");
	}
	
	/**
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: XXXX����ģ��
	 * @�๦������: TODO(������һ�仰��������������)
	 * @���ߣ� ����Դ[����:1206]
	 * @ʱ�䣺 2015-7-20 ����03:54:47
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmShForm model = (XsXmShForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
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
	 * @���ߣ� ����Դ[����:1206]
	 * @ʱ�䣺 2015-7-20 ����03:54:47
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsXmShForm model =new XsXmShForm();
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
	public ActionForward XmjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmShForm myForm = (XsXmShForm) form;
		XsXmShForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XsXmSqService service = new XsXmSqService();
		HashMap<String, String> hdmap = service.getHdMap(model.getXmdm(),model.getXn(),model.getXq());
		request.setAttribute("hdmap", StringUtils.formatData(hdmap));
		request.setAttribute("form",model);
		return mapping.findForward("view");
	}
}
