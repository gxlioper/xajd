/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-1 ����11:16:09 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sq;

import java.util.ArrayList;
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
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: ѧ����λ���� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-1 ����11:16:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsgwsqAction extends SuperAction<XsgwsqForm, XsgwsqService>{
	
	private XsgwsqService service = new XsgwsqService();
	private static final String XSGW = "xsgw";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XSGW);
	}
	private static final String url = "qgzx_xsgwsqnew_sq.do";
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-1 ����04:38:21
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
	public ActionForward getXsgwSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		
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
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));

		String path = "qgzx_xsgwsqnew_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if(Base.xxdm.equals("10704") && getUser(request).getUserStatus().equals("stu")){//�����Ƽ���ѧ���Ի�
			WdgwsqService gwsqService = new WdgwsqService();
			request.setAttribute("isTg", gwsqService.isTgInOneYear(getUser(request).getUserName())==true?"1":"0");
		}
		
		return mapping.findForward("getXsgwSqList");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����09:32:56
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
	public ActionForward addXsgwSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		String xh = request.getParameter("xh");
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			
			boolean result = service.saveSq(model);
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {	
			if(service.isHaveRecordForSq(model.getXh(), Base.currXn)) {
				String message = MessageUtil.getText(MessageKey.XLZXWZDX_XSSQ_EXIST);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}			
			String result = service.saveSq(model, "submit");
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}
			
			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result) ? MessageKey.SYS_SUBMIT_SUCCESS
							: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// ����ѧ��������Ϣ
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			szXsxx(request, user.getUserName());
		} else {
			szXsxx(request, xh);
		}
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));
		request.setAttribute("xxdm", Base.xxdm);
		String path = "xsgwsqnew_sq.do?method=addXsgwSq";
		if ( "yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))) {
			String qgzxPath = "xsgwsqnew_sq.do?method=QgzxStuSq";
			request.setAttribute("path", qgzxPath);
		} else {
			request.setAttribute("path", path);
		}
		List<HashMap<String, String>> qgxmList= service.getQgmxList(xh);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());//���ճ������еĿγ�ʱ��һ��
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		request.setAttribute("model", StringUtils.formatData(model));
		
		return mapping.findForward("addXsgwSq");
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
	 * @����: ɾ��
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����02:37:16
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
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		int result = service.runDelete(values.split(","));
		String messageKey = null;
		if (result > 0) {
			messageKey = ShlcUtil.deleteSpxx(values.split(",")) ? MessageKey.SYS_DEL_SUCCESS
					: MessageKey.SYS_DEL_FAIL;
		} else {
			messageKey = MessageKey.SYS_DEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;		
	}
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����03:26:31
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
	public ActionForward subBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		boolean result = service.submitRecord(values, lcid, xh);
		if (result) {
			// ����ҵ��״̬Ϊ'�����'
			XsgwsqForm model = new XsgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_SHZ);
			// ��ʱȥ��
			// ShlcDao shlcDao = new ShlcDao();
			// model.setShgw(shlcDao.getFirstGwid(lcid));
			// �����˸�λ
			model.setShgw("");
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����03:55:33
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
	public ActionForward cancle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = false;
		result = service.cancleRecord(values, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			XsgwsqForm model = new XsgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_WTJ);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-3 ����09:50:34
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
	public ActionForward ckXsgwSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
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
		XsgwsqForm modelN = service.getModel(model);
		request.setAttribute("model", modelN);
		request.setAttribute("knslist", knslist);
		
		request.setAttribute("kzyz", modelN.getGwdm());
		
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());
		request.setAttribute("qgmxList", service.getQgmxList(model.getXh()));
		//��������
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", StringUtils.formatData(csszService.setZjmrCs(request)));
		
		request.setAttribute("xxdm", Base.xxdm);
		
		return mapping.findForward("ckXsgwSq");
	}
	
	
	@SystemAuth(url = url)
	public ActionForward ckXsgwJgwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsgwsqForm myForm = (XsgwsqForm) form;
		String gwdm = request.getParameter("gwdm");
		String xh = request.getParameter("xh");		
		XsgwsqDao dao = new XsgwsqDao();
		HashMap<String,String> rs = dao.getGwxxMap(gwdm);
		request.setAttribute("rs", rs);
		// ����ѧ��������Ϣ
		szXsxx(request, xh);
		
		return mapping.findForward("ckXsgwJgwh");
	}
	
	/**
	 * 
	 * @����: ��λ��Ϣ��ѯ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:32:23
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
	public ActionForward wdgwxzCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String tzlx = "tz";
		XsgwsqForm model = (XsgwsqForm) form;
		String lx = request.getParameter("lx");
		String path = "xsgwsqnew_sq.do?method=wdgwxzCx";
		// ��־Ϊ��������
		if (StringUtils.isNotNull(lx) && lx.equals(tzlx)) {
			path += "&lx=" + tzlx;
			request.setAttribute("lx", tzlx);
			XsgwsqForm wf = service.getModel(model);
			model.setYrdwdm(wf.getYrdwdm());
		}else {	
			request.setAttribute("lx", null);
		}
		if (QUERY.equals(model.getType())) {

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getGwPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
//		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		

		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wdgwxzCx");
		
	}
	
	/**
	 * 
	 * @����: ��λ��Ϣ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:33:13
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
	public ActionForward gwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		int xszggwsl = service.getXszggwsl(model);
		int xssqsl = service.getXssqsl(model);
		String message = "";
		if (xszggwsl > 0) {
			message = "�˸�λ��ѧ���Ѿ��ڸ�";
		}
		if (xssqsl > 0) {
			message = "�˸�λ��ѧ���Ѿ�����";
		}
		// �������
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String, String> map = qgzxCsszService.getCssz();
		String isshow = "false";
		String sfsdgwcjsx = map.get("sfsdgwcjsx");
		model = service.getModel(model);
		// ��������˳������
		if ("yes".equals(sfsdgwcjsx)) {
			isshow = "true";
			// �����ǰ��λδ���ó�����ޣ�����ʾ�������õĳ������
			if (StringUtils.isNull(model.getGwcjsx())) {
				model.setGwcjsx(map.get("gwzgcjsx"));
			}
		}
		request.setAttribute("isshow", isshow);
		request.setAttribute("message", message);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("lx", request.getParameter("lx"));

		if (StringUtils.equals(request.getParameter("type"), "stu")) {
			request.setAttribute("userlx", "stu");
		}

		return mapping.findForward("gwxx");	
	}
	
}
