/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:13:59 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwsq.ZwsqForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwsq.ZwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�����ְ�����
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-9 ����5:00:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZwshAction extends SuperAction {
	
	private ZwshService service = TransactionControlProxy.newProxy(new ZwshService());

	private static final String  CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "szdw_zwsh.do?method=zwshList";
	/**
	 * @����:ְ������б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-9 ����5:02:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward zwshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwshForm myForm = (ZwshForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_zwsh.do?method=zwshList");
		return mapping.findForward("list");
	}
	/**
	 * @����:ְ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-9 ����5:02:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwshForm myForm = (ZwshForm) form;
		ZwsqService sqservice = new ZwsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		//��ȡ������Ϣ
		ZwsqForm model = sqservice.getModel(myForm);

		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//������˽��
			myForm.setSplc(model.getSplc());
			myForm.setXh(model.getXh());
			myForm.setZwid(model.getZwid());
			boolean result = service.zwsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//����ְ����ϸ��Ϣ
		setZwinfoModel(request, model);
		//����ѧ����Ϣ
		szXsxx(request,myForm.getXh());
		request.setAttribute("myForm", StringUtils.formatData(myForm));
		if("ck".equalsIgnoreCase(myForm.getType())){
			return mapping.findForward("zwck");
		}
		return mapping.findForward("zwsh");
	}
	/**
	 * @����:����ְ����ϸ��Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����9:54:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param model
	 * @throws Exception
	 * void ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private void setZwinfoModel(HttpServletRequest request,ZwsqForm model) throws Exception{
		//��ѯְ����Ϣ
		ZwwhForm m = new ZwwhForm();
		m.setZwid(model.getZwid());
		ZwwhForm zwmodel= new ZwwhService().getModel(m);
		//��ѯְ������
		ZwlxForm zwlx = new ZwlxForm();
		if(zwmodel!=null){
			zwlx.setLxdm(zwmodel.getLxdm());
		}
		ZwlxForm zwlxmodel= new ZwlxService().getModel(zwlx);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("zwmodel", StringUtils.formatData(zwmodel));
		request.setAttribute("zwlxmodel", StringUtils.formatData(zwlxmodel));
				
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelZwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZwshForm model = (ZwshForm) form;
		String sqid = request.getParameter("sqid");
	    String shzt = request.getParameter("shzt");
		model.setSqid(sqid);
		model.setShzt(shzt);
		//FdypxXmshService service = new FdypxXmshService();
		//�����ճ���Ϊ��ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-30 ����09:22:22
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
	public ActionForward zwPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwshForm myForm = (ZwshForm) form;
		User user = getUser(request);
		
		if("save".equalsIgnoreCase(myForm.getType())){
			
			String message = service.savePlsh(myForm,user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("zwPlsh");
	}
	
	/**
	 * 
	 * @����:��д��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-31 ����04:39:48
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
		ZwshForm model =new ZwshForm();
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
}
