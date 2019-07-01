/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����06:36:19 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsh;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.wjcf.cfsh.CfshService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (���ֽ�����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-30 ����06:36:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjcshAction extends SuperAction {

	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	
	private static final String url = "wjcf_cfjcsh.do?method=cxCfjcshList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfjcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
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
		
		String path = "wjcf_cfjcsh.do?method=cxCfjcshList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("cancelPath", "wjcf_cfjcsh.do?method=qxsh");
		
		return mapping.findForward("cxCfjcshList");
	}
	
	/**
	 * �������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=TransactionControlProxy.newProxy(new CfjcshService());
		CfshService cfshService=new CfshService();
		
		if(SAVE.equalsIgnoreCase(model.getType())){
			//������˽��
			User user = getUser(request);
			boolean result = service.jcsh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		
		//�Ƿ������һ�����
		CfshForm myForm=new CfshForm();
		myForm.setSplcid(model.getSplcid());
		myForm.setGwid(model.getGwid());
		boolean isZhgw=cfshService.isZhgw(myForm);
		//��ȡΥ����Ϣ
		myForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(myForm);
		request.setAttribute("map", map);
		//��ѯѧ������
		if(StringUtils.isNotNull(map.get("xh"))){
			List<HashMap<String, String>> hjqkList = new PjjgService().getHjqkList(map.get("xh"));// ����ѧ�Ų�ѯ�����
			request.setAttribute("hjqkList", hjqkList);
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("isZhgw", String.valueOf(isZhgw));
		request.setAttribute("type", UPDATE);
		//�����Ϣ
		//���ݹ�ҵ���һ��������ɴ����ĺ�
		if(isZhgw&&"12686".equals(Base.xxdm)){
			model.setXn(map.get("xn"));
			model.setXq(map.get("xq"));
			String jclsh = service.getJcLsh(model);
			String jcwh =  MessageUtil.getText(MessageKey.WJCF_CFJCWH_FORMAT, new String[] {Base.currNd,jclsh});
			model.setJcwh(jcwh);
		}
		request.setAttribute("cfjcshForm", StringUtils.formatData(model));
		CfjcshForm f = service.getModel(model); 
		request.setAttribute("jcxx", StringUtils.formatData(f));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jcsh");
		
	}
	/**
	 * ��˲鿴
	 */
	@SystemAuth(url = url)
	public ActionForward jcshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
		CfshService cfshService=new CfshService();
		
		CfshForm myForm=new CfshForm();
		myForm.setSplcid(model.getSplcid());
		myForm.setGwid(model.getGwid());
		//��ȡΥ����Ϣ
		myForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(myForm);
		request.setAttribute("map", map);
		//��ѯѧ������
		if(StringUtils.isNotNull(map.get("xh"))){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		//�����Ϣ
		request.setAttribute("cfjcshForm", StringUtils.formatData(model));
		CfjcshForm f = service.getModel(model); 
		request.setAttribute("jcxx", StringUtils.formatData(f));
		return mapping.findForward("jcshCk");
	}
	
	/**
	 * ȡ�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
		
		HashMap<String, String> shxx=ShlcUtil.getShxx(model.getShid());
		model.setYwid(shxx.get("ywid"));
		boolean result = service.cancel(model);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	/**
	 * 
	 * @����:��֤����ĺ��Ƿ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����09:52:44
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
	public ActionForward checkExistJcwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcshForm model=(CfjcshForm)form;
		CfjcshService service=new CfjcshService();
		// ȡ���Ƿ������֤
		boolean isExistJcwh = service.checkExistJcwh(model);
		response.getWriter().print(isExistJcwh);
		return null;
	}
}
