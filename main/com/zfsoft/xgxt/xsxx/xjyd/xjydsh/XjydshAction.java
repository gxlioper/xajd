package com.zfsoft.xgxt.xsxx.xjyd.xjydsh;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-�����춯
 * @�๦������: ѧ���춯���
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-12-2 ����03:31:05 
 * @�汾�� V1.0
 */
public class XjydshAction extends SuperAction {
	
	private XjydshService service = new XjydshService();
	
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xjyd_xjydsh.do";
	
	/**
	 * 
	 * @����:ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-2 ����03:31:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xjydshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydshForm myForm = (XjydshForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			request.setAttribute("path", "xjyd_xjydsh.do");
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//���һ��������˺���õ�·��
		request.setAttribute("cancelPath", "xjydsh.do?method=cancel");
		request.setAttribute("path", "xjyd_xjydsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydshList");
	}

	/**
	 * 
	 * @����:ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-2 ����03:30:48
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
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯���-���XJYDSQID:{xjydsqid}")
	public ActionForward xjydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydshForm myForm = (XjydshForm) form;
		XjydsqService sqservice = new XjydsqService();
		//��ȡ������Ϣ
		XjydsqForm model = sqservice.getModel(myForm.getXjydsqid());
		
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			
			//������˽��
			myForm.setSplcid(model.getSplcid());
			myForm.setXh(model.getXh());
			myForm.setXjydsqid(model.getXjydsqid());
			
			boolean result = service.ydsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѧ����Ϣ��ʾ�������ã�
		szXsxx(request,myForm.getXh());

		//�Ƿ������һ�����
		boolean isZhgw = service.isZhgw(myForm.getGwid(), model.getSplcid());
		myForm.setIsZhgw(String.valueOf(isZhgw));
		request.setAttribute("myForm", myForm);
		
		// �춯��ֹʱ�䣨Ĭ��ֵ��
		myForm.setSqkssj(model.getSqkssj());
		myForm.setSqjssj(model.getSqjssj());
		
		
		//�춯������Ϣ
		request.setAttribute("data", model);
		
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setAllNjXyZyBjList(request);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//�춯ԭ��
			List<HashMap<String,String>> ydyyList = sqservice.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//ά����ԴѧУ/ȥ��ѧУ
			List<HashMap<String,String>> lyqxxxList = sqservice.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
			
			//��ǰ״̬
			List<HashMap<String,String>> dqztList = sqservice.getDqztList();
			request.setAttribute("dqztList", dqztList);
		}
		
		//request.setAttribute("isZhgw", String.valueOf(isZhgw));
		return mapping.findForward("xjydsh");
	}
	/**
	 * 
	 * @����:ѧ���춯���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-2 ����03:30:48
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
	public ActionForward xjydshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydshForm myForm = (XjydshForm) form;
		XjydsqService sqservice = new XjydsqService();
		
		//��ȡ������Ϣ
		XjydsqForm model = sqservice.getModel(myForm.getXjydsqid());

		//ѧ����Ϣ��ʾ�������ã�
		szXsxx(request,model.getXh());

		request.setAttribute("myForm", myForm);
		
		// �춯��ֹʱ�䣨Ĭ��ֵ��
		myForm.setSqkssj(model.getSqkssj());
		myForm.setSqjssj(model.getSqjssj());
		
		
		//�춯������Ϣ
		request.setAttribute("data", model);	
		request.setAttribute("shzt", model.getShzt());	
		
		XjydjgForm xjydjg = new XjydjgForm();
		XjydjgService xjydjgService = new XjydjgService();
		xjydjg = xjydjgService.getModelBySqid(myForm.getXjydsqid());
		request.setAttribute("xjydjg", xjydjg);
		
		return mapping.findForward("xjydshCk");
	}
	
	/**
	 * 
	 * @����: ѧ��������Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2014-3-12 ����04:58:07
	 * @param request
	 * @param xh
	 * void �������� 
	 * @throws
	 */
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

		//ѧ��������Ϣ��ʾ���ã�ͬ���룩
		jbxxList = new BdpzService().getJbxxpz("xjydsq");
		request.setAttribute("jbxxList", jbxxList);
	}
	

	/**
	 * @����:���һ���������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯���-����SHID:{shid}")
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydshForm model = (XjydshForm) form;
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());	
		// ҵ��ع�
		boolean result = service.cancel(model.getSplcid(), shxx.get("ywid"));
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @����:������˲�������Ҫ��������ҵ��
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧ���춯���-����SHID:{shid}")
	public ActionForward cxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydshForm model = (XjydshForm) form;
		ShlcInterface service = new CommShlcImpl();
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());
		boolean result = service.runCancel(user.getUserName(), shxx.get("ywid"), model.getSplcid(), shxx.get("gwid"));
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
