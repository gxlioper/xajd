/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-6 ����09:37:37 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sh;

import java.util.ArrayList;
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
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqDao;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: ѧ����λ��� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-6 ����09:37:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsgwshAction extends SuperAction<XsgwshnewForm, XsgwshService>{
	private XsgwshService service = new XsgwshService();
	private XsgwsqService sqservice = new XsgwsqService();
	private static final String XSGW = "xsgw";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XSGW);
	}
	private static final String url = "qgzx_xsgwshnew_sh.do";
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-6 ����11:02:14
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
	@SystemAuth(url = url)
	public ActionForward getXsgwShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_xsgwshnew_sh.do";
		// ���һ��������˺���õ�·��
		request.setAttribute("cancelPath", "xsgwshnew_sh.do?method=cancel");
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXsgwShList");
		
	}
	
	/**
	 * 
	 * @����: ���һ������  
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-7 ����05:33:03
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		
		HashMap<String, String> shxx = ShlcUtil.getShxx(model.getShid());
		// ҵ��ع�
		boolean result = service.cancel(model.getSplc(), shxx.get("ywid"));
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-8 ����08:20:33
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
	public ActionForward cxRollBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String shid = request.getParameter("shid");
		try {
			service.cxRollBack(user.getUserName(), shid);
		} catch (Exception e) {
			throw new Exception("�ع���˸�λ����" + e.getMessage());
		}
		return null;	
	}
	
	/**
	 * 
	 * @����: ���
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:24:22
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
	public ActionForward xsgwSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		String type = model.getType();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// �������϶���Ϣ
		KnsjgDao knsjgDao = new KnsjgDao();
		List<KnsjgForm> knslist = new ArrayList<KnsjgForm>();
		List<String[]> list = new ArrayList<String[]>();

		list.addAll(knsjgDao.getKnsjgList(model.getXh()));

		if (list != null && list.size() > 0) {
			for (String[] kns : list) {
				KnsjgForm knsjg = new KnsjgForm();
				knsjg.setXn(kns[0]);
				knsjg.setXqmc(kns[1]);
				knsjg.setDcmc(kns[2]);
				knsjg.setSqsj(kns[3]);
				knslist.add(knsjg);
			}
		}
		
		// ����ѧ��������Ϣ
		szXsxx(request, model.getXh());
		XsgwshnewForm modelN = service.getModel(model);
		modelN.setShid(model.getShid());
		request.setAttribute("model", modelN);
		request.setAttribute("knslist", knslist);
		
		request.setAttribute("kzyz", modelN.getGwdm());
		
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());
		request.setAttribute("qgmxList", new WdgwsqService().getQgmxList(model.getXh()));
		//��������
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", StringUtils.formatData(csszService.setZjmrCs(request)));
		
		request.setAttribute("xxdm", Base.xxdm);
		if ("ck".equalsIgnoreCase(type)) {
			return mapping.findForward("xsgwCk");
		}	
		return mapping.findForward("xsgwsh");
		
	}
	
	/**
	 * 
	 * @����: ѧ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����09:49:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param xh
	 * void �������� 
	 * @throws
	 */
	private void szXsxx(HttpServletRequest request, String xh) {
		// ��ѯѧ����Ϣ
		if (xh != null && !"".equals(xh)) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * 
	 * @����: �����֤
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:24:46
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
	public ActionForward yzgwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		// �����������Ƽ����趨 ��֤
		String message = service.yzjb(model,false);
		if (message.equals("true")) {
			response.getWriter().print(service.yzsh(model));
		}else if(message.equals("LastLc")) {
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("message", "��ѡ���ѧ����λ��");
			response.getWriter().print(JSONObject.fromObject(resultmap));		
		}else {
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("message", message);
			response.getWriter().print(JSONObject.fromObject(resultmap));
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		String ids=request.getParameter("ids");
		String shyj=request.getParameter("shyj");
		String shgws=request.getParameter("shgws");
		String shzt=request.getParameter("shzt");
		String message=request.getParameter("message");
		String gwdm = request.getParameter("gwdm");
		String xh = request.getParameter("xh");
		String splc = request.getParameter("splc");
		
		request.setAttribute("ids", ids);
		request.setAttribute("shgws", shgws);
		request.setAttribute("message", message);
		if("null".equals(gwdm)) {
			request.setAttribute("gwdms", null);
		}else {
			request.setAttribute("gwdms", gwdm);
		}
		request.setAttribute("xh", xh);
		request.setAttribute("splc", splc);
		
		return mapping.findForward("plsh");
		
	}
	
	
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		XsgwsqDao sqDao = new XsgwsqDao();
		HashMap<String, String> gwxx = sqDao.getGwxx(model.getGwdm());
		String xqrsString = gwxx.get("xqrs");// ����������
		Integer xqrs = StringUtils.paseStringToInteger(xqrsString);
		XsgwshDao shDao = new XsgwshDao();
		Integer yzrs = shDao.getRskz(model.getGwdm());// �ڸ�����
		Integer shrs = model.getXhs().length;// �������
		String message = "";
		if(shrs + yzrs > xqrs) {
			message = "��ǰ�������������������Ѿ������ø�λ��������������ȷ�ϣ�";
		}else {
			message = service.savePlsh(model, user);
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
		
	}
}
