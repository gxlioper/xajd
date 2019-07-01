/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-28 ����10:51:41 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import java.io.BufferedInputStream;
import java.io.InputStream;
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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (�����ϱ����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-28 ����10:51:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfshAction extends SuperAction {
	private final static String CFSBZDPZ="cfsbzdpz1";
	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "wjcf_cfsh.do?method=cxCfshList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "wjcf_cfsh.do?method=cxCfshList";
		request.setAttribute("cancelPath", "wjcf_cfsh.do?method=cancel");
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxCfshList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		CfjgService cfjgService =new CfjgService();
		CfjgForm  cfjgForm = new CfjgForm();
		
		//�Ƿ������һ�����
		boolean isZhgw=service.isZhgw(model);
		
		HashMap<String, String> map=service.getCfsbxx(model);	//�����ϱ���Ϣ
		//���ַ���Ȩ���ж� 0��δ��д��1���ȼ�����Ȩ�޸�λ��2���ȼ�����Ȩ�޸�λ��3�ȼ�����Ȩ�޸�λ
		int cffwqxPd = service.cffwqxPd(map.get("kzzd4"),model.getSplcid(),model.getGwid());
		
		//����Ȩ�ޣ�δ��д���������һ��������Ȩ�޸�λ
		if((isZhgw&&cffwqxPd==0)||cffwqxPd==2){
			//���ݹ�ҵ���һ��������ɴ����ĺ�
			if("12686".equals(Base.xxdm)){
				cfjgForm.setXn(map.get("xn"));
				cfjgForm.setXq(map.get("xq"));
				String cflsh = cfjgService.getLsh2(cfjgForm);
				String cfwh =  MessageUtil.getText(MessageKey.WJCF_CFWH_FORMAT, new String[] {Base.currNd,cflsh});
				
				map.put("kzzd2", cfwh);
			}
			
			//�ൺ�Ƶ����ְҵ����ѧԺ�����ɴ����ĺ�
			if("13011".equals(Base.xxdm)){
				String cfwh = cfjgService.getDefaultCfwhFor13011();
				map.put("kzzd2", cfwh);
			}
		}
		
		//���һ��
		if(isZhgw){
			//���ݹ�ҵ���һ��������ɴ����ĺ�
			if("12686".equals(Base.xxdm)){
				cfjgForm.setXn(map.get("xn"));
				cfjgForm.setXq(map.get("xq"));
				String cflsh = cfjgService.getLsh2(cfjgForm);
				model.setCflsh(cflsh);
			}
		}
		
		if(SAVE.equalsIgnoreCase(model.getType())){
			//������˽��
 			User user = getUser(request);
			boolean result = service.ydsh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		//����Υ�ʹ���
		WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
		request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(map.get("xh")));
		//��ѯѧ������
		if(StringUtils.isNotNull(map.get("xh"))){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMoreWithZSXX(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		
		model.setCflbdm(map.get("cflbdm"));
		
		request.setAttribute("map", map);
		request.setAttribute("cflbList", new WjcfJcszServices().cflbdmCx());//Υ�ʹ������
		request.setAttribute("cffwqxPd", cffwqxPd);
		
		request.setAttribute("cfshForm", StringUtils.formatData(model));
		request.setAttribute("isZhgw", String.valueOf(isZhgw));
		
		request.setAttribute("type", UPDATE);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cfsh");
	}
	/**
	 * ��˲鿴
	 */
	@SystemAuth(url = url)
	public ActionForward cfshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		
		HashMap<String, String> map=service.getCfsbxx(model);
		request.setAttribute("map", StringUtils.formatData(map));
		
		//����Υ�ʹ���
		WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
		request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(map.get("xh")));
		//��ѯѧ������
		if(StringUtils.isNotNull(map.get("xh"))){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMoreWithZSXX(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cfshForm", StringUtils.formatData(model));
		request.setAttribute("type", UPDATE);
		return mapping.findForward("cfshCk");
	}
	
	//�Ƿ���Գ������
	public ActionForward canCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		boolean result=service.canCancel(model);
		if(!result){
			String messageKey = MessageKey.WJCF_SBCX_BKCX;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
		
	
	//���һ���������
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		HashMap<String, String> shxx=ShlcUtil.getShxx(model.getShid());
		model.setSplcid(shxx.get("lcid"));
		model.setGwid(shxx.get("gwid"));
		boolean result = service.cancel(model, shxx.get("ywid"));
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * 
	 * @����:(��������)
	 * @���ߣ�cmj[���ţ�982]
	 * @���ڣ�2013-10-31 ����09:46:11
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
	public ActionForward fjxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model=(CfshForm)form;
		CfshService service=new CfshService();
		
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(model.getFjmc()));
		InputStream in = service.fjCx(model);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-23 ����03:16:22
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
	public ActionForward cfplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model= (CfshForm) form;
		CfshService service=new CfshService();
		String cffwqx = request.getParameter("cffw");
		String cflbdm = request.getParameter("cflbdm");
		if (SAVE.equals(model.getType())) {
			String cfwh = request.getParameter("cfwh");
			String cfsj = request.getParameter("cfsj");
			String cfdqsj = request.getParameter("cfdqsj");
			String ggqlb = request.getParameter("ggqlb");//����ǰ��
			String gghlb = request.getParameter("gghlb");//���ĺ��
			if ("1".equals(cffwqx)) {
				model.setCfwh(cfwh);
				model.setCfsj(cfsj);
				model.setCfdqsj(cfdqsj);
				model.setKzzd1(ggqlb);//ԭ����
				model.setCflbdm(gghlb);//���ĺ��
			}else {
				model.setKzzd1(ggqlb);//ԭ����
				model.setCflbdm(ggqlb);//���ĺ��
			}
			User user = getUser(request);
			// �������
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("cflbList", new WjcfJcszServices().cflbdmCx());//Υ�ʹ������
		request.setAttribute("cffwqx", cffwqx);
		request.setAttribute("cflbdm", cflbdm);
		return mapping.findForward("cfplsh");

	}
	
	/**
	 * @����:��ô��ֵ���ʱ��Ĭ��ֵ������ʱ��+��������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��25�� ����2:14:32
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
	public ActionForward defaultCfdqsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfshForm model= (CfshForm) form;
		CfshService service=new CfshService();
		String defaultCfdqsj = service.defaultCfdqsj(model);
		response.getWriter().print(getJsonMessage(defaultCfdqsj));
		return null;
	}

}
