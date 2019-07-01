package com.zfsoft.xgxt.jskp.lxsh;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;

public class LxshAction extends SuperAction<LxshForm, LxshService> {
	private LxshService service = new LxshService();
	private final String url = "pjpy_jskp_lxsh.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 
	 * @����: ������˲�ѯ��ת
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-18 ����11:58:39
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
	public ActionForward getLxshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxshcx");
	}
	
	/**
	 * 
	 * @����: ������˲�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-18 ����01:48:34
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
	public ActionForward searchForLxshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxshForm model = (LxshForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @����: �������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-18 ����11:59:28
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
	public ActionForward lxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxshForm model = (LxshForm)form;
		LxshForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		request.setAttribute("xmlbmc",new DmwhService().getModel(lxsq.getXmlb()).getXmlbmc());
		//����ʱ�����ֵ��ȡϵͳ��ǰ����
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = new LxsqService().getXmcyryXhs(lxsq.getSqid());
		request.setAttribute("xhList", xhList);
	    request.setAttribute("bmmc", new LxsqService().getBmmc(lxsq.getZdbm()).get("bmmc"));
	    //��ȡ����ֶ�
	    HashMap<String,String> shzdxx = service.getLastshzd(lxsq.getSqid());
	    if(  shzdxx == null || shzdxx.isEmpty()){
	    	shzdxx = new HashMap<String, String>();
	    	shzdxx.put("zxf", lxsq.getZxf());
	    	shzdxx.put("zdf", lxsq.getZdf());
	    }else{
	    	shzdxx.put("zxf", shzdxx.get("zd3").split("-")[0]);
	    	shzdxx.put("zdf", shzdxx.get("zd3").split("-")[1]);
	    }
	    request.setAttribute("shzdxx",shzdxx);
		return mapping.findForward("lxsh");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-18 ����05:08:06
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
	public ActionForward saveLxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxshForm model = (LxshForm)form;
		User user = getUser(request);
		// ���浥�����
		LxshService Transervice = TransactionControlProxy.newProxy(new LxshService());
		boolean result = Transervice.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����10:23:40
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
	public ActionForward lxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("zsplsh");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����10:44:21
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
		LxshForm model = (LxshForm)form;
		User user = getUser(request);
		LxshService tranService = TransactionControlProxy.newProxy(new LxshService());
		String message = tranService.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����04:08:06
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxshForm model = (LxshForm) form;
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
	 * 
	 * @����:��˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-20 ����06:50:51
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxshForm model =new LxshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		//��֤�Ƿ���ѧ���ύ�ϱ����ݣ��еĻ������أ���ʾ�޷�����
		if(service.isStuSbTj(shxx.get("ywid"))){
			throw new SystemException(MessageKey.XG_ZJDX_JSKP_XMCX_CANCEL_CX);
		}
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
	
}
