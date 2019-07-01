/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-8 ����11:56:10 
 */
package com.zfsoft.xgxt.zxdk.tyxs.zzsq;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.sun.jmx.snmp.Timestamp;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq.KnsrdsqForm;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq.KnsrdsqService;
import com.zfsoft.xgxt.zxdk.tyxs.cssz.TyxsCssz;
import com.zfsoft.xgxt.zxdk.tyxs.cssz.TyxsCsszService;
import com.zfsoft.xgxt.zxdk.tyxs.shjg.TyxsZzjg;
import com.zfsoft.xgxt.zxdk.tyxs.shjg.TyxsZzjgService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ѧ����ѧ����X����ģ��
 * @�๦������: ������˲���
 * @���ߣ� ����Ӣ[����:1177]
 * @ʱ�䣺 2015-4-19����03:53:15
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TyxsZzsqAction extends SuperAuditAction<TyxsZzsq, TyxsZzsqService> {
	private Log logger = LogFactory.getLog(TyxsZzsqAction.class);
	// �ֶβɼ����ñ�
	private static final String KNSRD = "knsrd";	
	// ��������
	private static final String ZZPZ = "tyxs";
	// ����߼���ѯ
	private static final String GJCXPATH = "zxdk_tyxs_zzsq.do";
	// ѧ��ϵͳ������������
	private static final String XSPZ = "tyxs";
	// ��������
	private static final String SQDC = "tyxs_zzsq.do";

	// ��������

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param gnid
	 * @param squrl
	 * @param shurl
	 */
	protected TyxsZzsqAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
		// TODO �Զ����ɹ��캯�����
	}

	public TyxsZzsqAction() {
		this("tyxs_zzsq", "zxdk_tyxs_zzsq.do", "zxdk_tyxs_zzsh.do");
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:53:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	@SystemLog(description = "����ѧ����ѧѧ������-ѧ������")
	public ActionForward tyxsZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz model = csszService.getModel();
		request.setAttribute("cssz", model);
		
		request.setAttribute("path", "zxdk_tyxs_zzsq.do");
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		logger.info("searchTj" + searchModel);
		return mapping.findForward("zzsqList");
	}

	/**
	 * 
	 * @����:ajax��ȡ�����б�
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:53:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward getZzsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzsqService service = getService();
		TyxsZzsq model = (TyxsZzsq) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		logger.info("model" + searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		logger.info("list" + resultList);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;

	}

	/**
	 * 
	 * @����:�鿴����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:52:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward ckZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzsqService service = getService();
		TyxsZzsq myForm = (TyxsZzsq) form;

		TyxsZzsq model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// �鿴��ʾ���ñ�
		List<HashMap<String, String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService()
				.getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", myForm); // ѧ��������Ϣ��ʾ���� BdpzService
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		logger.info("jbxxList" + jbxxList);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhmc", service.getYhListByYhdm(model.getYhdm()));
			request.setAttribute("ndjelist", service.getNdJe(myForm.getId()));
		}
		return mapping.findForward("ckZzsq");
	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:52:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ѧ����-����ѧ����ѧѧ�ѹ���-ѧ������-ɾ��values:{id}")
	public ActionForward delZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzsq model = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		boolean result = service.runDelete(new String[] { model.getId() }) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;

	}

	/**
	 * 
	 * @����:��д����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:51:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsq model = (TyxsZzsq) form;
		TyxsCsszService csszService = new TyxsCsszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", new TyxsZzsqService().getYhList());
		}

		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xlccList", csszService.getXlccList());
		List<HashMap<String, String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("mkxxForm", model);
		String path = "tyxs_zzsq.do?method=zzsq";
		request.setAttribute("path", path);	
		this.saveToken(request);
		return mapping.findForward("zzsq");
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:51:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward dcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsqService servive = getService();
		TyxsZzsq model = (TyxsZzsq) form;
		// ���ɸ߼���ѯ����
		CommService commService = new CommService();
		logger.info("request="+request);
		SearchModel searchModel = commService.getSearchModel(request);	
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ���м�¼
		List<HashMap<String, String>> resultList = servive.getAllList(model,
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
	 * @����:��ӡ�����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:50:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String[] ids = request.getParameter("ids").split(",");

		if (null != ids && ids.length == 1) {// һ������
			File file = print(ids[0], request);
			FileUtil.outputWord(response, file);
		} else {// ��������
			List<File> files = new ArrayList<File>();
			for (String id : ids) {
				File file = print(id, request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

	/**
	 * 
	 * @����:��ӡ
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:50:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	private synchronized File print(String id, HttpServletRequest request)
			throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();

		TyxsZzsqService service = getService();
		TyxsZzsq model = service.getModel(id);
		data.put("m", model);
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
				.getXh());
		data.put("j", xsjbxx);
		if(Base.xxdm.equals("10704")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk",
					"tyxs_zzsq_10704.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh")
							+ "[" + xsjbxx.get("xm") + "]"));
		}else{
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk",
					"tyxs_zzsq.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh")
							+ "[" + xsjbxx.get("xm") + "]"));
		}
	}

	/**
	 * 
	 * @����:����ID��ѯ������Ϣ
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:49:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public ActionForward zzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		TyxsZzsqService service = getService();

		HashMap<String, String> dkxxMap = service.getSqxxByID(id);
		logger.info("dkxxMap" + dkxxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}

	/**
	 * 
	 * @����:�޸�����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:48:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward editZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("path = " + request.getParameter("path"));

		String path = "tyxs_zzsh.do?method=zzsq";
		request.setAttribute("path", path);
		return mapping.findForward("zzsq");
		// TODO �Զ����ɷ������

	}

	/**
	 * 
	 * @����:��ѧ��ѧ���ѯ��¼����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:48:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public ActionForward getCountByXhAndXn(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TyxsZzsqService service = getService();
		TyxsZzsq model = (TyxsZzsq) form;
		String count = service.getCountByXhAndXn(model);
		logger.info("count = "+count);
		response.getWriter().print(count);
		return null;
	}

	/**
	 * 
	 * @����:�޸�����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:48:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsqService service = getService();
		TyxsZzsq myForm = (TyxsZzsq) form;
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsZzsq model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			/*
			 * ��ͥ��� TyxsCssz csszModel = new TyxsCssz(); csszModel=
			 * csszService.getModel(); if (csszModel != null &&
			 * "1".equals(csszModel.getSfwcjtdc()) ){ JtqkdcService jtqkService
			 * = new JtqkdcService(); JtqkdcForm jtqkForm = new JtqkdcForm();
			 * jtqkForm.setXh(model.getXh()); JtqkdcForm jtqkModel =
			 * jtqkService.getModel(jtqkForm);
			 * 
			 * request.setAttribute("openJtqk", jtqkModel == null); }
			 */
		}
		List<HashMap<String, String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xlccList", csszService.getXlccList());
		request.setAttribute("xn",Base.currXn);
		logger.info("xn= "+Base.getXnndList()+"\r\n"+Base.currXn);
		request.setAttribute("mkxxForm", model);
		String path = "tyxs_zzsq.do?method=xgZzsq";
		request.setAttribute("path", path);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", service.getYhList());
			request.setAttribute("ndjelist", service.getNdJe(myForm.getId()));
		}
		request.setAttribute("cssz", csszService.getModel());
		this.saveToken(request);
		return mapping.findForward("xgZzsq");
	}

	/**
	 * 
	 * @����:����б�
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:47:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsh.do")
	public ActionForward tyxsZzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz model = csszService.getModel();
		request.setAttribute("cssz", model);
		request.setAttribute("path", "zxdk_tyxs_zzsh.do");
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);

		return mapping.findForward("zzshList");

	}

	/**
	 * @����: ajax��������б�
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2014-9-25 ����03:39:06
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
	@SystemAuth(url = "zxdk_tyxs_zzsh.do")
	public ActionForward getZzshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("getZzshList");
		TyxsZzsq myform = (TyxsZzsq) form;
		User user = getUser(request);

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myform.setSearchModel(searchModel);

		// ��ѯ
		List<HashMap<String, String>> resultList = getService().getAudingList(
				myform, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		logger.info(resultList);
		return null;
	}

	/**
	 * 
	 * @����: �������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2014-9-25 ����03:40:39
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
	@SystemAuth(url = "zxdk_tyxs_zzsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsqService service = getService();
		TyxsZzsq myForm = (TyxsZzsq) form;

		TyxsZzsq model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();

		List<HashMap<String, String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService()
				.getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);

		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("jbxxList", jbxxList);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhmc", service.getYhListByYhdm(model.getYhdm()));
			request.setAttribute("ndjelist", service.getNdJe(myForm.getId()));
		}
		return mapping.findForward("zzsh");
		
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-23 ����11:42:17
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TyxsZzsq t = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		ShlcInterface shlc = new CommShlcImpl();		
		TyxsZzsq model = service.getModel(t.getId());		
		boolean isSuccess = shlc.firstStepCancle(model.getId(), model.getSplcid());
		
		if(isSuccess){
			//����ҵ��״̬Ϊ'δ�ύ'
			model.setShzt(Constants.YW_WTJ);
			model.setId(model.getId());
			isSuccess = service.runUpdate(model);
			if(isSuccess){
				//ɾ�������Ϣ				
				shlc.deleteShjl(model.getId());
			}
		}		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		logger.info("sdag");
		return null;
	}
	
	/**
	 * 
	 * @����:���
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-24 ����09:20:58
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
	@SystemAuth(url = "zxdk_tyxs_zzsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitAuditSH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TyxsZzsq t  = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		ShlcInterface shlc = new CommShlcImpl();
		User user = getUser(request);
		
		ShxxModel shxxModel = new ShxxModel();
		BeanUtils.copyProperties(shxxModel, t);
		
		TyxsZzsq model = service.getModel(t.getId());
				
		shxxModel.setYwid(t.getId());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShzt(t.getShjg());
		shxxModel.setSqrid(model.getXh());

//		shxxModel.setShyj(t.getShyj());
//		shxxModel.setThgw(t.getThgw());
//		shxxModel.setGwid(t.getGwid());
		shxxModel.setTzlj("tyxs_zzsh");
		shxxModel.setTzljsq("tyxs_zzsq");
		
		try {
			//��˲���{����һ�����ݵ���˱���}
			String zhzt  = shlc.runAuditing(shxxModel); 
			model.setShzt(zhzt);//��ȡ���״̬��־��������Model
			
			boolean result = service.runUpdate(model);//���������{�������������״̬��Ϣ}
			
			//������ͨ�� ����һ�����ݵ������
			if(result && Constants.SH_TG.equals(zhzt)){ 
				BeanUtils.copyProperties(model, shxxModel);
				result = service.afterLastAuditself(model);		
				if(result){
					TyxsZzjg JgModel = new TyxsZzjg();
					TyxsZzjgService tyjgService = new TyxsZzjgService();
					// ����ѧ�š�ѧ�ꡢ��Ŀ����ɾ�������������
					//tyjgService.delWpjgxx(model.getXh(), model.getXn(), model.getXmdm());
					//BeanUtils.copyProperties(JgModel, StringUtils.formatData(wpsqForm));
					JgModel.setSqid(model.getId());
					JgModel.setSqly(model.getSqly());
					JgModel.setSqxfzj(model.getSqxfzj());
					JgModel.setZzzje(model.getSqxfzj());
					JgModel.setSqsj(model.getSqsj());
					JgModel.setXn(model.getXn());
					JgModel.setXh(model.getXh());
					JgModel.setFilepath(model.getFilepath());
					JgModel.setDkbj(model.getDkbj());
					JgModel.setYhdm(model.getYhdm());
					JgModel.setDkhth(model.getDkhth());
					JgModel.setDkkssj(model.getDkkssj());
					JgModel.setDkjssj(model.getDkjssj());
					JgModel.setDklx(model.getDklx());
					logger.info("=="+JgModel);
					result = tyjgService.runInsert(JgModel);
					logger.info(result);
				}
			}/*else if(result && Constants.SH_BTG.equals(zhzt)){
				
					TyxsZzjg JgModel = new TyxsZzjg();
					TyxsZzjgService tyjgService = new TyxsZzjgService();
					// ����ѧ�š�ѧ�ꡢ��Ŀ����ɾ�������������
					//tyjgService.delWpjgxx(model.getXh(), model.getXn(), model.getXmdm());
					//BeanUtils.copyProperties(JgModel, StringUtils.formatData(wpsqForm));
					JgModel.setSqid(model.getId());
					JgModel.setSqly(model.getSqly());
					JgModel.setSqxfzj(model.getSqxfzj());
					JgModel.setZzzje("0");
					JgModel.setSqsj(model.getSqsj());
					JgModel.setXn(model.getXn());
					JgModel.setXh(model.getXh());
					logger.info("=="+JgModel);
					result = tyjgService.runInsert(JgModel);
					logger.info(result);
				
			}*/
			
			
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-27 ����01:24:53
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz cssz = csszService.getModel();		
		TyxsZzsqService service  = new TyxsZzsqService();
		TyxsZzsq myform = (TyxsZzsq)form;		
		TyxsZzsq model = service.getModel(myform.getId());					
		model.setId(myform.getId());
		model.setSplcid(cssz.getXfzzshlc());
		service.runUpdate(model);
		TyxsZzsq tyForm = service.getModel(myform.getId());		
		 return submit(mapping, tyForm, request, response);
		
	}

	/**
	 * 
	 * @����:ajax�޸Ĳ���
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-27 ����01:24:53
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz cssz = csszService.getModel();		
		TyxsZzsqService service  = new TyxsZzsqService();
		TyxsZzsq model = (TyxsZzsq)form;		
		
		model.setSplcid(cssz.getXfzzshlc());
		boolean result=service.runUpdate(model);
		if(Base.xxdm.equals("10511")){
			String[] nds = request.getParameterValues("nd");
			String[] jes = request.getParameterValues("je");
			service.delNdJe(model.getId());
			result = service.saveNdJeHsd(nds, jes, model.getId());
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	
		
	}

	/**
	 * 
	 * @����:�������ύ
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-27 ����01:24:53
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz cssz = csszService.getModel();		
		TyxsZzsqService service  = new TyxsZzsqService();
		TyxsZzsq myform = (TyxsZzsq)form;		
		myform.setSplcid(cssz.getXfzzshlc());
		if(Base.xxdm.equals("10511") && StringUtils.isNull(myform.getId())){
			String guid =  UniqID.getInstance().getUniqIDHash().toUpperCase();
			myform.setId(guid);
			service.runInsert(myform);
		}
		
		if(Base.xxdm.equals("10511")){
			service.delNdJe(myform.getId());
			String[] nds = request.getParameterValues("nd");
			String[] jes = request.getParameterValues("je");
			service.saveNdJeHsd(nds, jes, myform.getId());
		}
		return super.saveAndSubmit(mapping, myform, request, response);
		
	}
	
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TyxsZzsq model = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		String  guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setId(guid);
		boolean isSuccess = service.runInsert(model);
		if(Base.xxdm.equals("10511")){
			String[] nds = request.getParameterValues("nd");
			String[] jes = request.getParameterValues("je");
			isSuccess = service.saveNdJeHsd(nds, jes, guid);
		}
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
}
