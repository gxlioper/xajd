/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-17 ����09:09:24 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ѧ����λ-�ҵĸ�λ����
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-6-17 ����09:09:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WdgwsqAction extends SuperAction {

	private static final String CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;

	
	
	/**
	 * @����:TODO�ҵĸ�λ���� �߼���ѯ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-17 ����11:49:19
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	
	public ActionForward wdgwsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

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
		if(Base.xxdm.equals("10704") && getUser(request).getUserStatus().equals("stu")){//�����Ƽ���ѧ���Ի���ѧ���û���¼
			request.setAttribute("isTg", service.isTgInOneYear(getUser(request).getUserName())== true?"1":"0");//�ж�ѧ���û��Ƿ���һ�����Ƿ���һ����
		}
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));

		String path = "qgzx_wdgwsq.do?method=wdgwsqCx";
		request.setAttribute("path", path);
		QgCommUtilf.setCzpath(request, null);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wdgwsqCx");
	}

	public ActionForward wdgwsqjlCx(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getWdgwsqjlList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_wdgwsq.do?method=wdgwsqjlCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		HashMap<String,String> map = new QgzxCsszService().getKfsqMap();
		request.setAttribute("sfkfsq",map.get("sfkfxndwgwsq"));
		return mapping.findForward("gwsqjlCxStu");
	}

	public ActionForward wdqggwCx(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getWdGwList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_wdgwsq.do?method=wdqggwCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wdqggwCx");
	}
	public ActionForward lzsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		if(SAVE.equals(model.getType())){
			User user = getUser(request);
			model.setXh(user.getUserName());
			boolean flag = service.saveLzSq(model);
			String msg = flag ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(msg));
			return null;
		}
		request.setAttribute("model",service.getGwxxByGwdm(model.getGwdm()));
		return mapping.findForward("lzsq");
	}
	public ActionForward gwmx(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		User user = getUser(request);
		WdgwsqService service = new WdgwsqService();
		request.setAttribute("model",service.getGwxxByXhAndGwdm(model.getGwdm(),user.getUserName()));
		return mapping.findForward("gwmx");
	}
	public ActionForward gzmx(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		User user = getUser(request);
		WdgwsqService service = new WdgwsqService();
		request.setAttribute("model",service.getGwxxByXhAndGwdm(model.getGwdm(),user.getUserName()));
		request.setAttribute("gzmxList",service.getGzmxList(model.getGwdm(),user.getUserName()));
		return mapping.findForward("gzmx");
	}
	/**
	 * 
	 * @����:��ѯ�ڹ���ѧѧ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-30 ����08:42:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward showQgzxStudents(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getQgzxStuList(
				model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	// =============================ѧ������=========================//
	/**
	 * 
	 * @����:ѧ����λ���� ѧ��ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 */
	
	public ActionForward wdgwsqCxStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		User user = getUser(request); // ��ǰ��¼ѧ��

		String userType = user.getUserType();// ��ģ��ֻ����ѧ������
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		/*if ("yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))
				&& !service.isQgzxStu(user.getUserName())) {
			request.setAttribute("isQgzxStu", "no");
		}*/
		request.setAttribute("curXn", Base.currXn);
		request.setAttribute("curXq", Base.getXqmcForXqdm(Base.currXq));
		String path = "qgzx_wdgwsq.do?method=wdgwsqCxStu";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));
		HashMap<String,String> map = new QgzxCsszService().getKfsqMap();
		request.setAttribute("sfkfsq",map.get("sfkfxsgwsq"));
		return mapping.findForward("wdgwsqCxStu");
	}

	/**
	 * 
	 * @����:���ݲ�ѯ
	 * @���ߣ���С��[���ţ�1036]
	 */
	
	public ActionForward wdgwxzCxAjaxStu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		User user = getUser(request); // ��ǰ��¼ѧ��

		String type = model.getType();

		if (org.apache.commons.lang.StringUtils.equals(type, "wsq")) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> data = service.getGwsqPageListStu(
					model, user);
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		} else if (org.apache.commons.lang.StringUtils.equals(type, "ysq")) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> data = service.getGwsqPageListStuYsq(
					model, user);
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		}

		return null;
	}
	public ActionForward getGwList(ActionMapping mapping,
										 ActionForm form, HttpServletRequest request,
										 HttpServletResponse response) throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		User user = getUser(request); // ��ǰ��¼ѧ��
		// ==================�߼���ѯ���========================
		CommService cs = new CommService();
		SearchModel searchModel = cs.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================
		List<HashMap<String, String>> data = service.getGwList(
				model, user);
		JSONArray dataList = JSONArray.fromObject(data);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @����: ѧ������
	 * @���ߣ���С��[���ţ�1036]
	 */
	
	public ActionForward wdgwSqStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		User user = getUser(request);
		szXsxx(request, user.getUserName());
		String path = "qgzx_wdgwsq.do?method=wdgwSqStu";
		request.setAttribute("path", path);
		model.setXh(user.getUserName());
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		
		WdgwsqService service = new WdgwsqService();
		List<HashMap<String, String>> qgxmList= service.getQgmxList(model.getXh());
		request.setAttribute("qgxmList", new QjjgService().getQjxmList()); //���ճ������еĿγ�ʱ��һֱ
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", StringUtils.formatData(csszService.setZjmrCs(request)));
		this.saveToken(request);
		return mapping.findForward("wdgwSqStu");

	}
	public ActionForward xsgwxxCk(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String gwdm = request.getParameter("gwdm");
		WdgwsqService service = new WdgwsqService();
		request.setAttribute("model",service.getGwxxByGwdm(gwdm));
		return mapping.findForward("xsgwxxCk");
	}
	// =============================ѧ������=========================//
	/**
	 * @����: �ҵĸ�λ���루TODO����ֵ�����������QgzxStuSq����ͬ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-17 ����02:30:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-ѧ����λ����-����:XH:{xh},GWDM:{gwdm},SQLY:{sqly}")
	public ActionForward wdgwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String xh = request.getParameter("xh");
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
		if(SAVE.equalsIgnoreCase(model.getType()) || "submit".equalsIgnoreCase(model.getType())){
//			if (!isTokenValid(request)){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//				return null;
//			} else {
//				super.resetToken(request);
//			}
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveSq(model);
			/*String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();

			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(mxrq != null &&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}*/
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {
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
		String path = "qgzx_wdgwsq.do?method=wdgwSq";
		if ( "yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))) {
			String qgzxPath = "qgzx_wdgwsq.do?method=QgzxStuSq";
			request.setAttribute("path", qgzxPath);
		} else {
			request.setAttribute("path", path);
		}
		List<HashMap<String, String>> qgxmList= service.getQgmxList(xh);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());//���ճ������еĿγ�ʱ��һ��
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		request.setAttribute("model", StringUtils.formatData(model));
		/*if("10344".equals(Base.xxdm)){
			if(StringUtils.isNotNull(xh)){
				KnsjgService knsjgService = new KnsjgService();
				List<HashMap<String, String>> knsInfoList = knsjgService
						.getKnsInfoList(xh);
				request.setAttribute("knsInfoList", knsInfoList);
			}
		}*/
		/**
		 * ���´�����Ϊ�˷�ֹ���ظ��ύ�����Ĵ���
		 */
		/*********************start**********************************/
		//�û�������ʸ�����ҳ��ʱ����ҳ����һ��ʱ������
		this.saveToken(request);
		/*********************start**********************************/
		return mapping.findForward("wdgwSq");
	}

	/**
	 * 
	 * @����: ��Ҫ�ڹ��ʸ����ѧ����λ����ѡ��ѧ��ר�ã�TODO����ֵ�����������wdgwSq����һ�£�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-30 ����11:48:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	
	public ActionForward QgzxStuSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String xh = request.getParameter("xh");
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
//		if (SAVE.equalsIgnoreCase(model.getType())) {
//			boolean result = service.saveSq(model);
//			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
//					: MessageKey.SYS_SAVE_FAIL;
//			response.getWriter().print(getJsonMessageByKey(messageKey));
//			return null;
//		} else if ("submit".equalsIgnoreCase(model.getType())) {
//			String result = service.saveSq(model, "submit");
//			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
//					: "true".equals(result) ? MessageKey.SYS_SUBMIT_SUCCESS
//							: MessageKey.SYS_SUBMIT_FAIL;
//			response.getWriter().print(getJsonMessageByKey(messageKey));
//			return null;
//		}

		// ����ѧ��������Ϣ
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			szXsxx(request, user.getUserName());
		} else {
			if (service.isQgzxStu(xh)) {
				szXsxx(request, xh);
			}
			else{
				szXsxx(request, "");
			}
		}
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));
		request.setAttribute("xxdm", Base.xxdm);

		List<HashMap<String, String>> qgxmList= service.getQgmxList(xh);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());//���ճ������еĿγ�ʱ��һ��
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		
		String qgzxPath = "qgzx_wdgwsq.do?method=QgzxStuSq";
		request.setAttribute("path", qgzxPath);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("wdgwSq");
	}

	/**
	 * 
	 * @����:�޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-11 ����02:35:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-ѧ����λ����-����:XH:{xh},GWDM:{gwdm},SQLY:{sqly}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		User user = getUser(request);
		String xh = request.getParameter("xh");
		String xssqkg = request.getParameter("xssqkg");

		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_WTJ);
			boolean result = service.update(model);
			/*String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();

			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}

			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}*/
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);
			boolean result = service.update(model);
			/*String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();

			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}*/
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// ����ѧ��������Ϣ
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			szXsxx(request, user.getUserName());
		} else {
			szXsxx(request, xh);
		}
		WdgwsqForm modelN = service.getModel(model);
		if("10344".equals(Base.xxdm) || "11488".equals(Base.xxdm)){
			WdgwsqForm tempmodel = new WdgwsqDAO().getModel(model.getSqbh());
			request.setAttribute("tempmodel", StringUtils.formatData(tempmodel));
		}
		
		// 982
		// �������
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
//		HashMap<String, String> map = qgzxCsszService.getCssz();
		String isshow = "false";
//		String sfsdgwcjsx = map.get("sfsdgwcjsx");
		// ��������˳������
//		if ("yes".equals(sfsdgwcjsx)) {
//			isshow = "true";
//			// �����ǰ��λδ���ó�����ޣ�����ʾ�������õĳ������
//			if (StringUtils.isNull(modelN.getGwcjsx())) {
//				modelN.setGwcjsx(map.get("gwzgcjsx"));
//			}
//		}
		QgzxCsszService cssz = new QgzxCsszService();
		request.setAttribute("cssz", cssz.getCssz());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("isshow", isshow);
		request.setAttribute("model", StringUtils.formatData(modelN));
		request.setAttribute("xssqkg", xssqkg);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());
		request.setAttribute("qgmxList", service.getQgmxList(xh));
		if("10344".equals(Base.xxdm)){
			if(StringUtils.isNotNull(xh)){
				KnsjgService knsjgService = new KnsjgService();
				List<HashMap<String, String>> knsInfoList = knsjgService
						.getKnsInfoList(xh);
				request.setAttribute("knsInfoList", knsInfoList);
			}
		}
		QgCommUtilf.setCssz(request);
		return mapping.findForward("wdgwxg");
	}

	public ActionForward qglsjl(ActionMapping mapping, ActionForm form,
							 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		WdgwsqService service = new WdgwsqService();
		List<HashMap<String,String>> list = service.getQglsjl(xh);
		request.setAttribute("qglsjlList",list);
		return mapping.findForward("qglsjl");
	}
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-ѧ����λ����-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqService service = new WdgwsqService();
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
	 * @����:TODOѧ����λ���� ������֤
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-19 ����04:11:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward yzgwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		response.getWriter().print(service.yzcssz(model));
		return null;
	}

	/**
	 * @����:TODO��ȡѧ��������Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-17 ����02:30:28
	 * @param request
	 * @param xh
	 *            void ��������
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
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}

	/**
	 * @����:TODOѡ���λ�߼���ѯ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-18 ����08:56:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	
	public ActionForward wdgwxzCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String tzlx = "tz";
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String lx = request.getParameter("lx");
		String path = "qgzx_wdgwsq.do?method=wdgwxzCx";
		// ��־Ϊ��������
		if (StringUtils.isNotNull(lx) && lx.equals(tzlx)) {
			path += "&lx=" + tzlx;
			request.setAttribute("lx", tzlx);
			WdgwsqForm wf = service.getModel(model);
			model.setYrdwdm(wf.getYrdwdm());
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
	 * @����:TODO��λ��Ϣչʾ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-18 ����08:55:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	
	public ActionForward gwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		int xszggwsl = service.getXszggwsl(model);
		int xssqsl = service.getXssqsl(model);
		String message = "";
		if (xszggwsl > 0) {
			message = "�˸�λ��ѧ���Ѿ��ڸ�";
		}
		if (xssqsl > 0) {
			message = "�˸�λ��ѧ���Ѿ�����";
		}
		// 982
		// �������
//		QgzxCsszService qgzxCsszService = new QgzxCsszService();
//		HashMap<String, String> map = qgzxCsszService.getCssz();
//		request.setAttribute("cssz", map);
//		String isshow = "false";
//		String sfsdgwcjsx = map.get("sfsdgwcjsx");
		xsgzgl.qgzx.gwglnew.QgzxGwglService qgzxGwglService = new xsgzgl.qgzx.gwglnew.QgzxGwglService();
		HashMap<String,String> gwxx = qgzxGwglService.getGwxxByGwdm(model.getGwdm());
		/*if("10351".equals(Base.xxdm)){//���ݴ�ѧ���Ի�
			model.setSqxyms(service.getXyms(model));
		}*/
		/*// ��������˳������
		if ("yes".equals(sfsdgwcjsx)) {
			isshow = "true";
			// �����ǰ��λδ���ó�����ޣ�����ʾ�������õĳ������
			if (StringUtils.isNull(model.getGwcjsx())) {
				model.setGwcjsx(map.get("gwzgcjsx"));
			}
		}*/
//		request.setAttribute("isshow", isshow);
		request.setAttribute("message", message);
		request.setAttribute("model", gwxx);
//		request.setAttribute("lx", request.getParameter("lx"));
	//	request.setAttribute("xxdm", Base.xxdm);
//		if (StringUtils.equals(request.getParameter("type"), "stu")) {
//			request.setAttribute("userlx", "stu");
//		}
		QgCommUtilf.setCssz(request);
		return mapping.findForward("gwxx");
	}

	/**
	 * @����:TODOȡ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-18 ����08:59:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	
	public ActionForward qxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqbhs = request.getParameter("sqbhs");
		String[] sqbh = sqbhs.split(",");
		if (sqbhs == null || "".equals(sqbhs) || sqbh.length < 1) {
			response.getWriter().print(
					getJsonMessageByKey(MessageKey.SYS_CANCEL_NULL));
		} else {
			WdgwsqService service = new WdgwsqService();
			WdgwsqForm model = (WdgwsqForm) form;
			boolean result = false;
			for (String sqid : sqbh) {
				model.setShzt("9");
				model.setSqbh(sqid);
				ShlcInterface shlc = new CommShlcImpl();
				result = shlc.deleteShjl(sqid);
				int i = service.runDelete(new String[] { model.getSqbh() });
				if (i > 0) {
					result = true;
				}
			}
			String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
					: MessageKey.SYS_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		return null;
	}

	
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqService service = new WdgwsqService();

		String values = request.getParameter("values");
		//String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String gwdm = request.getParameter("gwdm");
		// ��ȡ��������
		/*String splc = service.getShlcID();
		String bmlb = service.getYrdwlb(gwdm);
		if("5".equals(bmlb))
			splc = service.getYjShlcID();*/
		String splc = "";
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if(service.isXndw(gwdm)){
			splc = splcMap.get("xsgwsqsplc");
		} else {
			splc = splcMap.get("xwxsgwsqsplc");
		}
		boolean result = service.submitRecord(values, splc, xh);
		if (result) {
			// ����ҵ��״̬Ϊ'�����'
			WdgwsqForm model = new WdgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_SHZ);
			model.setSplc(splc);
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

	
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		WdgwsqService service = new WdgwsqService();
		boolean result = false;
		result = service.cancleRecord(values, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			WdgwsqForm model = new WdgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_WTJ);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward sqlyModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("sqlyModel");
	}
	

	/**
	 * 
	 * @����:�ڹ���ѧѧ������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-10-16 ����04:36:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	
	public ActionForward qgsqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// �����
		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * 
	 * @����:�ڹ���ѧ��λ����������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-6-20 ����09:50:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	
	public ActionForward printGwbmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqService service = new WdgwsqService();
		WdgwsqForm myForm = (WdgwsqForm) form;

		XsxxService xsxxService = new XsxxService();
		if (StringUtils.isNotNull(myForm.getSqbh())) {
			List<File> files = new ArrayList<File>();
			String[] sqbhs = myForm.getSqbh().split(",");
			for (String sqbhid : sqbhs) {
				myForm.setSqbh(sqbhid);
				WdgwsqForm model = service.getModel(myForm);
				Map<String, Object> data = new HashMap<String, Object>();
				// ����ѧ��������Ϣ
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				data.putAll(xsjbxx);
				HashMap<String, String> qsxx= service.getQsxx(model.getXh());
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				//��ѯƶ����
				String pkxdj=service.getPkxjb(model.getXh());
				data.put("qsbh", qsbh);
				data.put("gwmc", model.getGwmc());
				data.put("pkxdj", pkxdj);
				
				//Ϋ��ѧԺ���Ի�
				if("11067".equals(Base.xxdm)){
					data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
					data.put("sqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day));// ����ʱ��
					//ת��Ϊ�������ڸ�ʽ
					data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
					// ����ѧ����������
					KnsjgService knsjgService = new KnsjgService();
					List<HashMap<String, String>> knsInfoList = knsjgService.getKnsInfoList(xsjbxx.get("xh"));
					if(knsInfoList.size() > 0){
						data.put("knsdcmc", knsInfoList.get(0).get("dcmc"));
					}else{
						data.put("knsdcmc", "");
					}
				}
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("wdgwsq_sqly", sqly);// ��������
				//ѧ����Ƭ
				InputStream is = xsxxService.getPhotoStream(model.getXh());
				File photoFile = FileUtil.inputstreamToFile(is, "doc");
				String photo = FileUtil.getBASE64(photoFile);
				if (StringUtil.isNull(photo)){
					//ȡĬ����Ƭ
					photo = xsxxService.getDefaultPhotoBase64(request);
				}
				if(photo == null){
					photo = "";
				}
				data.put("xsxx_photo", photo);
				// ����ְ��
				DwwhService dwwhService = new DwwhService();
				data.put("zwmc", dwwhService.getZwForXh(model.getXh()));
				// ========== �ɲμ��ڹ���ѧʱ�� begin ========
				WdgwsqService wdgwsqService = new WdgwsqService();
				String[] qgrqArr = new String[]{ "һ", "��", "��", "��", "��" }; // ����һ, ��, ��, ��, ��
				String[] qgmxArr = new String[]{ "2", "3", "4", "5" }; // 1-2�ڡ�3-4�ڡ�5-6�ڡ�7-8��
				HashMap<String, String> qgzxsjMap = wdgwsqService.getQgmxByQgrqQgmx(qgrqArr, qgmxArr, model.getXh());
				data.putAll(qgzxsjMap);
				// ========== �ɲμ��ڹ���ѧʱ�� end ========
				// ========= ��ͥ�����Ϣ begin ========
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkdcForm = new JtqkdcForm();
				jtqkdcForm.setXh(model.getXh());
				JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
				String jtqk_xx = "";
				if (jtqkmodel != null) {
					jtqk_xx = "��ͥ�˾�������: " + jtqkmodel.getJtrjysr()
								+ " Ԫ�� ��ͥ��������: " + jtqkmodel.getSnjtsr()
								+ " Ԫ�� ��ͥ�˾�������: " + jtqkmodel.getJtrjsr()
								+ " Ԫ�� ��ͥ��������: " + jtqkmodel.getJtnzsr()
								+ " Ԫ�� ��ͥ������Դ: " + jtqkmodel.getJtsrly();
					data.put("jtqk", jtqkmodel);
				}
				data.put("jtqk_xx", jtqk_xx); 
				
				List<HashMap<String, String>> cyList4line = jtqkService.getJtcyList(model.getXh(),4);
				data.put("cyList4line", cyList4line);
				
				// ========= ��ͥ�����Ϣ end ========
				File file = service.printForWord(data);
				files.add(file);
			}
			if (files != null && files.size() > 1) {
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			} else {
				FileUtil.outputWord(response, files.get(0));
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @����:�㽭��ҽҩ�ڹ��������ӡ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����11:54:18
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
	public ActionForward getQgbmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm myForm = (WdgwsqForm) form;
		File wordFile = getQgbmWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����:�ڹ��������ӡ��ȡword
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����11:54:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getQgbmWord(WdgwsqForm myForm,HttpServletRequest request) throws Exception{

		//String xh = myForm.getXh();
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
	    WdgwsqService service = new WdgwsqService();
	    String xh = service.getModel(myForm.getSqbh()).getXh();
		List<HashMap<String, String>> qgmxList = new ArrayList<HashMap<String,String>>();;//service.getKxsjMx(xh);
		if(null == qgmxList || qgmxList.size()<7){
			
//			int kswz = 0;
//			if(null ==qgmxList){
//				kswz = 1;
//			}else{
//				 kswz = qgmxList.size()+1;
//			}
			for (int i = 1; i <= 7; i++) {
				HashMap<String, String> lsmnap = new HashMap<String, String>();
				lsmnap.put("qgrq", StringUtils.numberToChinese(i));
				lsmnap.put("sw", "0");
				lsmnap.put("xw", "0");
				lsmnap.put("ws", "0");
				qgmxList.add(lsmnap);
			}
			
			
		}
		HashMap<String, String> bmbxx = service.getBmbxx(myForm.getSqbh());
		data.put("qgmxList", qgmxList);
		
		bmbxx.put("yhtc", HtmlUtil.xmlZy(bmbxx.get("yhtc")));
		bmbxx.put("jtqk", HtmlUtil.xmlZy(bmbxx.get("jtqk")));
		bmbxx.put("qgzxrs", HtmlUtil.xmlZy(bmbxx.get("qgzxrs")));
		bmbxx.put("year", GetTime.getTimeByFormat("yyyy"));
		bmbxx.put("mon", GetTime.getTimeByFormat("mm"));
		bmbxx.put("day", GetTime.getTimeByFormat("dd"));
		data.putAll(bmbxx);
//		data.put("xyzsjg", myForm);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx","qgzxgwbmb.xml",xh+bmbxx.get("xm"));
		
		return file;
	}
	
	/**
	 * 
	 * @����:�㽭��ҽҩ������ӡ�ڹ�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����11:55:18
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
	public ActionForward getQgbmbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm myForm = (WdgwsqForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqbh(values[i]);
				File file = getQgbmWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/** 
	 * @����:����ѧ���Ƿ��˸���һ��(�����Ƽ���ѧר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-20 ����05:37:06
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
	public ActionForward checkSfTg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		WdgwsqForm myForm = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String result = service.isTgInOneYear(myForm.getXh())==true?"1":"0";//1��δ�˸���һ��  0���˸���һ��
		response.getWriter().print(getJsonMessage(result));
		return null;
	}

}
