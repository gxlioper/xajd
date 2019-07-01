/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-24 ����01:38:38 
 */
package com.zfsoft.xgxt.xszz.zzxmjg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgAction;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgModel;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.bbwh.BbwhService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcDao;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmlbwh.XmlbwhService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ŀ�������ģ��
 * @�๦������: action
 * @���ߣ� maxh
 * @ʱ�䣺 2013-4-24 ����01:38:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZzxmjgAction extends SuperAction {

	private static final String ZZXM = "zzxm";
	private static List<HashMap<String, String>> jbxxList = null;
	private static int CYSIZE = 4;// ��ͥ��Ա���֧������
	private static int ZZJGSIZE = 15;// ��������ϱ��������ʾ����
	private static String CG = "����ɹ���";
	private static String KONG = "���ϴ��ļ�";
	private static String SB = "����ʧ��,����ϸ�˶ԡ������¼.xls����";
	private static String NOCONTENT = "�ϴ��ļ�������";
	BdpzService bdpzService = new BdpzService();
		
	private static final String url = "xszz_zzxmjg_cx.do";
		

	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-24 ����02:25:14
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
	@SystemAuth(url = url)
	public ActionForward getZzxmjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

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
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "xszz_zzxmjg_cx.do";
		request.setAttribute("path", path);
		List<HashMap<String, String>> list=service.getkfzZqList(Base.currXn);
		request.setAttribute("iscanCopy", null==list||list.size()<=0?"0":"1");
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("zzxmjg");
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-24 ����03:13:17
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-�������-����XMMC:{xmmc},XH:{xh},LBDM:{lbdm},PDXN:{pdxn}")
	public ActionForward addZzxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;

		ZzxmjgService service = new ZzxmjgService();
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

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ��,��Ŀ���ƣ�
			boolean isExist = service.isExistByZzxmjg(model, SAVE);
			if (!isExist) {
				super.resetToken(request);
				// ���������Ϣ
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setZsbm(service.getZsbm(model));
				}
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}

		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);

		// ��Ŀ���
		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);
		// ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		// ѧ��list
		request.setAttribute("xqList", Base.getXqList());

		String path = "xszz_zzxmjg.do?method=addZzxmjg";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZZXM);
		request.setAttribute("jbxxList", jbxxList);
		this.saveToken(request);
		return mapping.findForward("addzzxmjg");
	}

	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�Administrator
	 * @���ڣ�2013-4-25 ����04:14:09
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-�������-�޸�GUID:{guid},XMMC:{xmmc},XH:{xh},LBDM:{lbdm},PDXN:{pdxn}")
	public ActionForward updateZzxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;

		ZzxmjgService service = new ZzxmjgService();
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

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ��,��Ŀ���ƣ�
			boolean isExist = service.isExistByZzxmjg(model, UPDATE);
			if (!isExist) {
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setZsbm(service.getZsbm(model));
					
				}
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		// ��Ŀ���
		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);
		// ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		// ѧ��list
		request.setAttribute("xqList", Base.getXqList());

		String path = "xszz_zzxmjg.do?method=addZzxmjg";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZZXM);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);

		ZzxmjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));

		return mapping.findForward("updateZzxmjg");
	}

	/**
	 * 
	 * @����:ɾ���������
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-25 ����04:41:32
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-�������-ɾ��-VALUES:{values}")
	public ActionForward delZzxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgService service = new ZzxmjgService();

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;

			if (result) {
				XszzSqshService sqshService = new XszzSqshService();
				sqshService.delXmsqFromZzjg(values.split(","));
			}

			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}

	/**
	 * 
	 * @����:�鿴����������Ŀ��Ϣ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-25 ����06:37:05
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
	@SystemAuth(url = url)
	public ActionForward zzxmjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (model != null) {

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// ��ѯ���
			request.setAttribute("rs", StringUtils.formatData(service
					.getOneZzxmjgList(model.getGuid())));
			// ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(ZZXM);
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewZzxmjg");
		} else {
			return updateZzxmjg(mapping, form, request, response);
		}

	}

	/**
	 * 
	 * @����:������Ŀ������Ϣ�б�
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-10
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
	@SystemAuth(url = url)
	public ActionForward zzxmhzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getZzxmhzList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		//searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "xszz_zzxmjg.do?method=zzxmhzList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zzxmhzList");
	}
	
	/**
	 * ����������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward expHjmdtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		ZzxmjgService service = new ZzxmjgService();
		CommService comService = new CommService();
		ZzxmjgExportModel exporModel = new ZzxmjgExportModel();
		comService.getModelValue(exporModel, request);
		String array_xmlb = request.getParameter("array_xmlb");
		array_xmlb = java.net.URLDecoder.decode(array_xmlb,"UTF-8");
		String xmlbs[] = array_xmlb.split("!!array!!");
		exporModel.setXmlb(xmlbs);

		response.setContentType("application/vnd.ms-excel");
		
		service.exportHjmdtj(exporModel, response.getOutputStream(),user);
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward zzxmhzView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		if (QUERY.equals(model.getType())) {
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.zzxmhzView(model, user, true);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("model", model);
		return mapping.findForward("zzxmhzView");
	}
	
	/**
	 * 
	 * @����:��ӡ������������Ϣ
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-3
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm myForm = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		ZzxmjgForm model = service.getModel(myForm);
		myForm.setXn(model.getXn());
		myForm.setXq(model.getXq());
		// ������Ŀ���ƻ�ȡ������Ϣ
		if (!StringUtil.isNull(myForm.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			String xsxmdm = xmwhService.getXszzdm(myForm.getXmmc(),myForm.getXn(),myForm.getXq());
			myForm.setXmdm(xsxmdm);
		}
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			// �ֽ����֤��begin
			String xssfzh = xsjbxx.get("sfzh") == null || "".equals("sfzh") ? ""
					: xsjbxx.get("sfzh");
			int sylen = 18 - xssfzh.length();
			for (int i = 0; i < xssfzh.length(); i++) {
				xsjbxx.put("sfzh" + i, xssfzh.charAt(i) + "");
			}
			for (int i = 0; i < sylen; i++) {
				xsjbxx.put("sfzh" + (xssfzh.length() + i), "");
			}
			// �ֽ����֤��end
			request.setAttribute("jbxx", xsjbxx);

			// ����ѧ����ͥ������Ϣ
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(myForm.getXh());
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			request.setAttribute("jtqk", jtqkmodel);

			// ��ѧ�ż��س�Ա�б�
			JtqkdcService jtqkdcService = new JtqkdcService();
			List<HashMap<String, String>> cyList = jtqkdcService
					.getJtcyList(myForm.getXh());
			request.setAttribute("blankList",
					CYSIZE > cyList.size() ? jtqkdcService.getBlankList(CYSIZE
							- cyList.size()) : jtqkdcService.getBlankList(0));
			request.setAttribute("cyList", cyList);
			request.setAttribute("cysize", CYSIZE > cyList.size() ? CYSIZE
					: cyList.size());

			// ����ѧ�������϶����
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(myForm
					.getXh(), myForm.getXn(), myForm.getXq());
			request.setAttribute("rddc", knsjg.get("rddc") == null ? "" : knsjg
					.get("rddc"));
		}
		// ѧУ����
		request.setAttribute("xxmc", Base.xxmc);
		KnsdcService knsdcService = new KnsdcService();
		// ����������list
		request.setAttribute("knsdc", knsdcService.getKnsdcList());
		// ��ת
		String forward = "";
		if ("gjjxjb".equalsIgnoreCase(myForm.getXmdm())) {// ���ҽ�ѧ������������
			forward = "/xsgzgl/xszz/print/gjjxjbDefault.jsp";
		} else if ("gjlzjxjb".equalsIgnoreCase(myForm.getXmdm())) {// ������־��ѧ������������
			forward = "/xsgzgl/xszz/print/gjlzjxjbDefault.jsp";
		} else if ("gjzxjb".equalsIgnoreCase(myForm.getXmdm())) {// ������ѧ������������
			forward = "/xsgzgl/xszz/print/gjzxjbDefault.jsp";
		} else {
			forward = "/xsgzgl/xszz/print/gjzxjbDefault.jsp";
			// throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("xmxx", model);// ����ѧ����Ŀ��Ϣ
		return new ActionForward(forward, false);
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-22 ����10:44:47
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getJgExportList(model, user);

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
	 * @����:�㽭������ܵ�������
	 * @���ߣ�ChenQ
	 * @���ڣ�2013-5-22 ����10:44:47
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportHzData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getZzxmjgHzList();

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
	 * @����:��ӡword
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-31 ����11:32:52
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward downloadWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm myForm = (ZzxmjgForm) form;
		String guid = myForm.getGuid();
		File wordFile = getWord(guid);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * 
	 * @����:����ZIP
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-31 ����11:32:26
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward downloadZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

	// ���ģ����������word�ļ�
	@SuppressWarnings("unchecked")
	private File getWord(String guid) throws Exception {
		ZzxmjgService service = new ZzxmjgService();
		ZzxmjgForm myForm = new ZzxmjgForm();
		myForm.setGuid(guid);
		ZzxmjgForm model = service.getModel(guid);//������Ŀ���
		HashMap<String, String> bbMap = null;// ����
		// ������Ŀ���ƻ�ȡ������Ϣ
		if (!StringUtil.isNull(model.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> xmMap = xmwhService.getDataByName(model
					.getXmmc(),model.getXn(),model.getXq());//��ѯ��Ŀ��¼
			if (xmMap != null) {
				model.setXmdm(xmMap.get("xmdm"));//��Ŀ����
				BbwhService bbwhService = new BbwhService();
				bbMap = bbwhService.getDataById(xmMap.get("dybb"));
			}
		}
		if (bbMap == null || bbMap.size() == 0) {//��ѯ���������������Ϣ
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		String xh = model.getXh();
		if (!StringUtil.isNull(xh)) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//ת��Ϊ�������ڸ�ʽ
			xsjbxx.put("rxrq", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));
			// �ֽ����֤��begin
			String xssfzh = xsjbxx.get("sfzh") == null || "".equals("sfzh") ? ""
					: xsjbxx.get("sfzh");
			int sylen = 18 - xssfzh.length();
			for (int i = 0; i < xssfzh.length(); i++) {
				xsjbxx.put("sfzh" + i, xssfzh.charAt(i) + "");
			}
			for (int i = 0; i < sylen; i++) {
				xsjbxx.put("sfzh" + (xssfzh.length() + i), "");
			}
			//ѧ����Ƭ
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			// ����ѧ����ͥ������Ϣ
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}

			// ��ѧ�ż��س�Ա�б�
			JtqkdcService jtqkdcService = new JtqkdcService();
			List<HashMap<String, String>> cyList = jtqkdcService
					.getJtcyList(model.getXh());
			Map<String, Object> data = new HashMap<String, Object>();
			if(cyList != null && cyList.size() > 0){
				for (HashMap<String, String> cyMap : cyList) {
					cyMap.put("cyxm",HtmlUtil.xmlZy(cyMap.get("cyxm")));
					cyMap.put("cygxmc",HtmlUtil.xmlZy(cyMap.get("cygxmc")));
					cyMap.put("cyxxdw",HtmlUtil.xmlZy(cyMap.get("cyxxdw")));
				}
				data.put("cyMap", cyList.get(0));
			}
			else{
				data.put("cyMap", new HashMap<String,String>());
			}
			List<HashMap<String, String>> cyList4line = jtqkdcService.getJtcyList(xh,4); //ȡ4����ͥ��Ա�б�����4�����
			List<HashMap<String, String>> cyList5line = jtqkdcService.getJtcyList(xh,5);
			// ����ѧ�������϶����
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(model
					.getXh(), model.getPdxn(), model.getPdxq());
			KnsdcService knsdcService = new KnsdcService();
			// ����Υ�ʹ���
			WjcfCfsbService wjcfcfsbService =  new WjcfCfsbService();
			List<HashMap<String , String>> wjList = wjcfcfsbService.getYscfqk(xh);
			data.put("wjList", wjList);
			  if ("12867".equals(Base.xxdm)){
		        XsxxtyglService service1 = new XsxxtyglService();
		        XsxxglService xsxxglService = new XsxxglService();
		        List cyList1 = xsxxglService.getJtcyxxXsList(xh);
		        data.put("jtcyxxList", cyList1);
		        int size1 = 5 - cyList1.size() <= 0 ? 0 : 5 - cyList1.size();
		        data.put("cyblankList", service1.getBlankList(size1));

		        Map jtqkmap = jtqkdcService.getJtqkInfo(xh);
		        data.put("jtqkmap", jtqkmap);

		        List zzxx = service.getZzxmjgInfoList(xh);
		        HashMap zxjxxmap = new HashMap();
		        for (int i = 0; i < zzxx.size(); i++) {
		          if (((String)((HashMap)zzxx.get(i)).get("xmmc")).equals(model.getXmmc())) {
		            zxjxxmap = (HashMap)zzxx.get(i);
		          }
		        }
		        data.put("zxjxxmap", zxjxxmap);
		        
		        if (model.getXmmc().indexOf("���Ļ���")!=-1) {
		        	//���Ļ���
		        	HashMap<String,String> axjjmap = service.getZjlyAxjjMap(xh, model.getXn());
		        	data.put("axjjmap", axjjmap);
				}else if (model.getXmmc().indexOf("�¶����Ĳ���")!=-1) {
					//�¶����Ĳ���
					HashMap<String,String> geaxmap = service.getZjlyGeaxMap(xh, model.getXn());
		        	data.put("geaxmap", geaxmap);
				}else if (model.getXmmc().indexOf("У�����ü���")!=-1) {
					//����У�����ü���
					HashMap<String,String> xfjmmap = service.getZjlyXfjmMap(xh, model.getXn());
		        	data.put("xfjmmap", xfjmmap);
				}else if (model.getXmmc().indexOf("������Ʒ���ü���")!=-1) {
					//������Ʒ���ü���
					HashMap<String,String> shfjmmap = service.getZjlyShfjmMap(xh, model.getXn());
		        	data.put("shfjmmap", shfjmmap);
				}else if (model.getXmmc().indexOf("��ҵ���ղ���")!=-1) {
					//������Ʒ���ü���
					HashMap<String,String> sybxbzmmap = service.getZjlySybxbzMap(xh, model.getXn());
		        	data.put("sybxbzmmap", sybxbzmmap);
				}else if (model.getXmmc().indexOf("��ͨ����")!=-1) {
					//��ͨ����
					HashMap<String,String> jtbzmmap = service.getZjlyJtbzMap(xh, model.getXn());
		        	data.put("jtbzmmap", jtbzmmap);
				}else if (model.getXmmc().indexOf("���ܿ�֤")!=-1) {
					//���ܿ�֤�Ѳ���
					HashMap<String,String> jnkzfbzmap = service.getZjlyJnkzfbzmapMap(xh, model.getXn());
		        	data.put("jnkzfbzmap", jnkzfbzmap);
				}else if (model.getXmmc().indexOf("ѧ�Ѽ���")!=-1) {
					//ѧ�Ѽ���
					HashMap<String,String> xfjmmap = service.getZjlyXfjmmapMap(xh, model.getXn());
		        	data.put("xfjmmap", xfjmmap);
				}else if (model.getXmmc().indexOf("��ѧ��")!=-1) {
					//ѧ�Ѽ���
					HashMap<String,String> zxjmap = service.getZjlyZxjmapMap(xh, model.getXn());
		        	data.put("zxjmap", zxjmap);
				}          
		      }
			//����ʦ����ѧ ���Ի�
			if(StringUtils.isEqual(Base.xxdm, "10602")){
				XsxxtyglService service1 = new XsxxtyglService();
				//ѧ����ͥ��Ա���
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				data.put("dqxmmc", model.getXmmc());
				data.put("photo", photo);
				data.put("jtcyxxList", jtcyxxList);
				int size1=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
				data.put("cyblankList", service1.getBlankList(size1));
				//�������������Ϣ
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoList(xh);
				data.put("pjjg1", pjjg);
				//����������Ϣ
				List<HashMap<String, String>> zzjg = service.getZzxmjgInfoList(xh);
				data.put("zzjglist", zzjg);
			}
			//����ְҵѧԺ���Ի�
			if("11773".equals(Base.xxdm)){
				// ======== ������ֽ��� begin============
				PjjgService pjjgService = new PjjgService();
				ZzxmjgService zzxmjgService = new ZzxmjgService();
				List<HashMap<String, String>> pjjgList3line = pjjgService.getHjqkByXhMap(xh,3);
				data.put("pjjgList3line", pjjgList3line);
				List<HashMap<String, String>> zzjgList4line = zzxmjgService.getZzjgList(xh,4);
				data.put("zzjgList4line", zzjgList4line);
				// ======== ������ֽ��� end============
				// ========= ��ѧ�����ѧϰ�ɼ� begin ============
				ZcfsService zcfService = new ZcfsService();
				String sT = model.getXn().substring(0,4);
				Base.beforXn = String.valueOf(Integer.parseInt(sT)-1) + "-" + sT;
				List<HashMap<String, String>> cjXnxqList = zcfService.getCjListByXhXn(xh,Base.beforXn, "");
				data.put("cjXnxqList", cjXnxqList);
				// ========= ѧϰ�ɼ� end ============
			}
			
			//����ʦ��ѧԺ���Ի�
			if("10402".equals(Base.xxdm)){
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList_10402 = xsxxglService.getHjqkNewList(xh);
				
				data.put("hjqkList_10402", hjqkList_10402);
				int hjqkSize=(4 - hjqkList_10402.size())<0?0:(4 - hjqkList_10402.size());
				data.put("hjqkBlankList_10402", jtqkdcService.getBlankList(hjqkSize));
				
			}
			if("11799".equals(Base.xxdm)){			
				ZzxmjgService service_11799 = new ZzxmjgService();
				String rq = model.getXn();
				String[] xnArr = rq.split("-");
				String sxn = Integer.parseInt(xnArr[0])-1+"-"+Integer.parseInt(xnArr[1]);
				HashMap<String,String> xfjmmap = service_11799.getQtzzje(xh, sxn);
	        	data.put("qtzzje", xfjmmap.get("qtzzje"));
	        	HashMap<String,String> knbzcsje = service_11799.getKncsAndJe(xh, sxn);
	        	data.put("knbzcs", knbzcsje.get("knbzcs"));
	        	data.put("knbzje", knbzcsje.get("knbzje"));
			}
			data.putAll(xsjbxx);//ѧ��������Ϣ
			
			//����ѧ��ְ��
			data.put("zwmc", service.getZwForXh(xh));
			data.put("lxdh",xsjbxx.get("sjhm"));
			
			//�������������Ϣ
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh,4);  //��ȡ����4���������
			String xmmcs = pjjgService.getXmmcAllByPjjg(xh);
			//����������Ϣ
			List<HashMap<String, String>> zzjg = service.getZzxmjgInfoList(xh);
			// ��ͥ��Ա�б����
			data.put("pjjgblankList", CYSIZE > pjjg.size() ? jtqkdcService
					.getBlankList(CYSIZE - pjjg.size()) : jtqkdcService
					.getBlankList(0));
			
			data.put("pjjg", pjjg);
			data.put("pjjgList4line", pjjgList4line);
			data.put("xmmcs", xmmcs);
			data.put("zzjgList", zzjg);
			data.put("sqzzje",model.getJe()); // �����������
			data.put("lbmc",model.getLbmc());
			data.put("zzjg",model);//�������
			data.put("jtqk", jtqkmodel);//��ͥ���
			//��ͥ��������
			if(jtqkmodel.getJtnzsr() != null) {
				data.put("jtnzsr",Double.parseDouble(jtqkmodel.getJtnzsr()));//��ͥ��������
				data.put("jtnsr",Double.parseDouble(jtqkmodel.getJtnzsr()));//��ͥ������
				data.put("jtyzsr",(Double.parseDouble(jtqkmodel.getJtnzsr())/12));//��ͥ��������
				data.put("jtrjysr",jtqkmodel.getJtrs() == null? "":(Double.parseDouble(jtqkmodel.getJtnzsr())/12/(Integer.parseInt(jtqkmodel.getJtrs()))));//��ͥ�˾�������
			}else {
				data.put("jtyzsr","");
				data.put("jtrjysr","");
			}
			
			/*��ͥ���--��ͥ�˾�������(ͨ��)*/
			if(jtqkmodel != null){
				String jtrjsr = jtqkmodel.getJtrjsr();
				if(jtrjsr != null){
					data.put("jtrjsr", jtrjsr);
				}else{
					data.put("jtrjsr", "");
				}
			}else{
				data.put("jtrjsr", "");
			}
			
			data.put("cyList", cyList);//��ͥ��Ա�б�
			data.put("cyList4line", cyList4line);
			data.put("cyList5line", cyList5line);
			//����ũҵ��ѧ
			if("10504".equals(Base.xxdm)){
				int size6=(4 - pjjg.size())<=0?0:(4 - pjjg.size());
				data.put("blankList1", jtqkdcService.getBlankList(size6));
				int size7=(4 - cyList.size())<=0?0:(4 - cyList.size());
				data.put("blankList2", jtqkdcService.getBlankList(size7));
				
			}
			//�㽭��ý||������Ͽҽҩ||̫ԭ����ְҵѧԺ||����ʦ����ѧ||����ʯ��
			if("11647".equals(Base.xxdm) || "14008".equals(Base.xxdm) || "13696".equals(Base.xxdm) || "10718".equals(Base.xxdm) || "10220".equals(Base.xxdm)) {
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			//����ְҵ
			}else if("10956".equals(Base.xxdm)) {
				data.put("blankList", 3 > cyList.size() ? jtqkdcService
						.getBlankList(3 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}else {
				// ��ͥ��Ա�б����
				data.put("blankList", CYSIZE > cyList.size() ? jtqkdcService
						.getBlankList(CYSIZE - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}
			//ɽ���ƾ���ѧ���Ի�
			if("10125".equals(Base.xxdm)){
				List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map = null;
				int pjjgsize = 4;
				if(pjjg.size() > pjjgsize){
					for (int i = 0;i < pjjgsize;i++){
						map = new HashMap<String,String>();
						map.put("sqsjs", pjjg.get(i).get("sqsjs"));
						map.put("xmmc", pjjg.get(i).get("xmmc"));
						map.put("bjdw", pjjg.get(i).get("bjdw"));
						pjjgList.add(map);
					}
					data.put("pjjgblankList",pjjgList);
				} else {
					data.put("pjjgblankList", pjjg);
				}
				int size2=(4 - pjjg.size())<=0?0:(4 - pjjg.size());
				data.put("blankList2", jtqkdcService.getBlankList(size2));
				data.put("cyList", cyList);
				int size1=(4 - cyList.size())<=0?0:(4 - cyList.size());
				data.put("blankList1", jtqkdcService.getBlankList(size1));
			}
			 //�㽭����ѧԺ ���Ի�
			  if ("12869".equals(Base.xxdm)){
					List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
					HashMap<String,String> map = null;
					int pjjgsize = 4;
					if(pjjg.size() > pjjgsize){
						for (int i = 0;i < pjjgsize;i++){
							map = new HashMap<String,String>();
							map.put("sqsjs", pjjg.get(i).get("sqsjs"));
							map.put("xmmc", pjjg.get(i).get("xmmc"));
							map.put("bjdw", pjjg.get(i).get("bjdw"));
							pjjgList.add(map);
						}
						data.put("pjjgblankList",pjjgList);
					} else {
						data.put("pjjgblankList", pjjg);
					}
					int size2=(4 - pjjg.size())<=0?0:(4 - pjjg.size());
					data.put("blankList2", jtqkdcService.getBlankList(size2));
					data.put("cyList", cyList);
					int size1=(4 - cyList.size())<=0?0:(4 - cyList.size());
					data.put("blankList1", jtqkdcService.getBlankList(size1));
					HashMap<String, String> sfkns = service.getSfkns(xh, model.getXn());
				    data.put("jgxyrddc", sfkns.get("dcmc"));
					data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				}
			  
			//������ũ��ְҵѧԺ���Ի�
			if("12727".equals(Base.xxdm)){
				// ======== ������ֽ��� begin============
				StringBuffer chhzjlBuffer = new StringBuffer();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						chhzjlBuffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(xmmc).append("��");
					}
				}
				String chhzjlmc = chhzjlBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("chhzjlmc", chhzjlmc);
				// ======== ������ֽ��� end============
				// ��ͥ���
				JtqkdcForm jtqkmodel_12727 = jtqkService.getModel(jtqkdcForm); 
				if (jtqkmodel_12727 == null) {
					jtqkmodel_12727 = new JtqkdcForm();
				}
				data.put("jtqkmodel_12727", jtqkmodel_12727);
				String jthk = jtqkmodel.getJthk();
				boolean jthkCzFlag = false;
				if(jthk != null && jthk.contains("����")){
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// ��ͥ��Ա
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				pjjgService.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
				// ========= ѧϰ�ɼ� begin ============
				ZcfsService zcfService = new ZcfsService();
				List<HashMap<String,String>> cjXnxqList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "");
				StringBuffer bxnxxcjBuffer = new StringBuffer();
				for (int i = 0; i < cjXnxqList.size(); i++) {
					HashMap<String, String> cjXnxqMap = cjXnxqList.get(i);
					bxnxxcjBuffer.append(cjXnxqMap.get("kcmc")).append("��").append(cjXnxqMap.get("cj")).append("��");
				}
				String bxnxxcjmc = bxnxxcjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("bxnxxcjmc", bxnxxcjmc);
				
				List<HashMap<String,String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "");
				String bxn_cjSxqPjf = pjjgService.getPjf(bxn_cjSxqBxList, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("bxn_cjSxqPjf", bxn_cjSxqPjf);
				List<HashMap<String,String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				String bxn_cjXxqPjf = pjjgService.getPjf(bxn_cjXxqBxList, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("bxn_cjXxqPjf", bxn_cjXxqPjf);
				
				String bxn_bjgcjsSxq = pjjgService.getBjgcjNum(xh, model.getXn(), "01"); //��ѧ����ѧ�ڲ�����ɼ�����
				data.put("bxn_bjgcjsSxq", "".equals(bxn_bjgcjsSxq) ? "0" : bxn_bjgcjsSxq); 
				String bxn_bjgcjsXxq = pjjgService.getBjgcjNum(xh, model.getXn(), "02"); //��ѧ����ѧ�ڲ�����ɼ�����
				data.put("bxn_bjgcjsXxq", "".equals(bxn_bjgcjsXxq) ? "0" : bxn_bjgcjsXxq);
				// ========= ѧϰ�ɼ� end ============
			}
			data.put("knsdcList", knsdcService.getKnsdcList());// ����������list
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//�϶�����
			data.put("rddcmc", knsjg.get("dcmc")==null?"":knsjg.get("dcmc"));//�϶���������
			
			data.put("xxmc", Base.xxmc);// ѧУ����
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));//��������
			data.put("currXnNow", Base.currXn);//��ǰѧ��
			data.put("currXn", model.getPdxn());// ��ǰ��Ŀ����ѧ��---��2014-10-17-�������޸ģ�֮ǰcurrXn���ֵΪ��ǰѧ�꣩
			//�۲������
			HashMap<String,String> zcfMap = new ZcfsService().getZczfByXh(xh, Base.currXn, Base.currXq);
			String bjrs = new ZcfsService().getBjrs(xh);
			//̫ԭ����ְҵѧԺ
			if("13696".equals(Base.xxdm)) {
				//��ǰѧ��
				if("02".equals(Base.currXq)) {
					data.put("currXqNow", "һ");
				}else if("01".equals(Base.currXq)) {
					data.put("currXqNow", "��");
				}
				data.put("zcfMap", zcfMap);
				data.put("bjrs", bjrs);
				
			}
			
			//ɽ��������ҽְҵ
			if("12947".equals(Base.xxdm)) {
				//��������
				String  Jtyzsr = Float.parseFloat(jtqkmodel.getJtrjysr())*Integer.parseInt(jtqkmodel.getJtrs())+"";
				data.put("jtyzsr", Jtyzsr);
				data.put("xn", model.getXn());
			}
				
			//������Ͽ�ߵ�ҽҩר��ѧУ
			if("14008".equals(Base.xxdm)) {
				YlbxjgService ylbxjgService = new YlbxjgService();
				HashMap<String, String> jfdcInfo = ylbxjgService.getJfdcRylbInfo(xh);
				data.put("yljfdc", jfdcInfo.get("dcmc")); // ҽ�ƽɷѵ���	
				data.put("rylb", jfdcInfo.get("rylb")); // ��Ա���
			}
			
			//�Ϻ�Ϸ��
			if("10279".equals(Base.xxdm)) {
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxrq = xsjbxx.get("rxrq") == null ? "" : xsjbxx.get("rxrq");
				data.put("csny_n", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				data.put("rxny_n", rxrq.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}
			//�㽭��ѧ
			if ("10335".equals(Base.xxdm)) {
				String csrq = xsjbxx.get("csrq");
				String nf="" ;
				String yf="" ;
				String rq="" ;
				if (csrq!=null) {
					if (csrq.length()==8) {
						nf = xsjbxx.get("csrq").substring(0, 4);
						yf = xsjbxx.get("csrq").substring(4, 6);
						rq = xsjbxx.get("csrq").substring(6, 8);
					}else if (csrq.length()==10) {
						nf = xsjbxx.get("csrq").substring(0, 4);
						yf = xsjbxx.get("csrq").substring(5, 7);
						rq = xsjbxx.get("csrq").substring(8, 10);
					}
				}
					data.put("nf", nf);
					data.put("yf", yf);
					data.put("rq", rq);
				data.put("blankList", 3 > cyList.size() ? jtqkdcService
						.getBlankList(3 - cyList.size()) : jtqkdcService
						.getBlankList(0));
				 SimpleDateFormat datenow = new SimpleDateFormat("yyyy-MM-dd");
				    String date = datenow.format(new Date());
				    String year = date.substring(0, 4);
				    String month = date.substring(5, 7);
				    String day = date.substring(8, 10);
				    data.put("today", date);
				    data.put("year", year);
				    data.put("month", month);
				    data.put("day", day);
				
				//����������Ϣ
				List<HashMap<String, String>> zzjg1 = service.getZzxmjgInfoList(xh);
				data.put("zzjglist", zzjg1);
				//ѧ����ͥ��Ա��Ϣ
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				XsxxtyglService service1 = new XsxxtyglService();
				int size1=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
				data.put("cyblankList", service1.getBlankList(size1));
				data.put("jtcyxxList", jtcyxxList);
				data.put("jtcyxxList1", jtcyxxList != null && jtcyxxList.size() > 0 ? jtcyxxList.get(0) : new HashMap<String, String>());
				data.put("jtcyxxList2", jtcyxxList != null && jtcyxxList.size() > 1 ? jtcyxxList.get(1) : new HashMap<String, String>());
				data.put("jtcyxxList3", jtcyxxList != null && jtcyxxList.size() > 2 ? jtcyxxList.get(2) : new HashMap<String, String>());
				data.put("jtcyxxList4", jtcyxxList != null && jtcyxxList.size() > 3 ? jtcyxxList.get(3) : new HashMap<String, String>());
				data.put("jtcyxxList5", jtcyxxList != null && jtcyxxList.size() > 4 ? jtcyxxList.get(4) : new HashMap<String, String>());
				data.put("jtcyxxList6", jtcyxxList != null && jtcyxxList.size() > 5 ? jtcyxxList.get(5) : new HashMap<String, String>());
			
				
				HashMap<String,String> knsxx = knsjgService.getKnsxx(model.getXh());
				data.put("knssqsj", DateTranCnDate.fomartDateToCn(knsxx.get("sqsj"),FomartDateType.month));//ѧ������������ʱ�䣨����ʱ��ȡXXXX��XX�£�
				xsjbxx.put("zzxmsqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day));//ȡѧ�����뽱��ʱ��
				XszzSqshService xszzSqshservice = new XszzSqshService();
				String zzxmshje = xszzSqshservice.zzxmshtgJe(xh,model.getXn(),model.getXq());
				data.put("zzxmshje", zzxmshje);
			}
			
			//���ݴ�ѧ
			if("10351".equals(Base.xxdm)) {
					
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList = xsxxglService.getHjqkNewFourList(xh);
				data.put("pjjg", hjqkList);
				int hjqkSize=(4 - hjqkList.size())<0?0:(4 - hjqkList.size());
				data.put("pjjgblankList", jtqkdcService.getBlankList(hjqkSize));
				String csny = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month);
				String rxrq = xsjbxx.get("rxrq");
				data.put("csny", csny);
				data.put("rxrq", rxrq);
				String xz = "";
				if(StringUtils.isNotNull(xsjbxx.get("xz"))){
					xz = DateUtils.numToZh(xsjbxx.get("xz"))+"��";
				}
				data.put("xz", xz);
				HashMap<String, String> bxk = pjjgService.getBxk(model.getXh(),model.getPdxn());
				HashMap<String, String> pm = pjjgService.getCjPm(model.getXh(),model.getPdxn());
				data.put("bxk", bxk);
				data.put("pm", pm);
			}
			
			//���������ѧ
			if("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// ��������
				xsjbxx.put("rxny", xsjbxx.get("rxrq"));// ��ѧ����
				// ��ͥ��Ա
				PjjgService servicePjPy = new PjjgService();
				List<HashMap<String, String>> jtcyxxList_zymzdx = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_zymzdx, 5 - jtcyxxList_zymzdx.size());
				data.put("jtcyxxList_zymzdx", jtcyxxList_zymzdx);
				// ��Ŀѧ��ѧ�ڱ��޿γɼ�
				ZcfsService zcfService = new ZcfsService();
				HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
				data.put("zcf", zcf);
				List<HashMap<String,String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "����");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList); 
				String xmXnxqBxCjPjf = servicePjPy.getPjf(xmXnxqBxCjList, 2); // ƽ���ɼ�
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				// ���Ž���
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if(StringUtils.isNotNull(zzmmmc)){
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if((zzmmmc.contains("��Ա") && "02".equals(jddm))
							|| (zzmmmc.contains("Ԥ����Ա") && "09".equals(jddm))
							|| (zzmmmc.contains("��Ա") && !zzmmmc.contains("Ԥ����Ա") && "11".equals(jddm))){
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj,FomartDateType.day));
				// �ѻ���
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjg1 =  servicePjPy.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg1.size(); i++) {
					HashMap<String, String> pj = pjjg1.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("��");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
			}

			//�ӱ���ҵ��ѧ
			if("10080".equals(Base.xxdm)){
				List<HashMap<String,String>> zzxmList = service.getZzxmInfoByXhXn(model.getXh(),model.getXn(),model.getXq());//��ѧ�������ܵ�������Ŀ
				String yxszzxm = "";
				if(zzxmList.size()>0) {
					for (int i = 0; i < zzxmList.size(); i++) {
						HashMap<String, String> zzxmmap = zzxmList.get(i);
						yxszzxm += zzxmmap.get("xmmc");
						if (i<zzxmList.size()-1)
						{
							yxszzxm +=",";
						}
					}
					data.put("yxszzxm",yxszzxm);
				}

			}
			
			//���������ѧ
			if("10742".equals(Base.xxdm)) {
				data.put("xn1", model.getXn().trim().substring(0, 4));
		        data.put("xn2", model.getXn().trim().substring(5, 9));
		        String tbrq = model.getSqsj();// ����ʱ��
		        data.put("y", tbrq.trim().substring(0, 4));
		        data.put("m", tbrq.trim().substring(5, 7));
		        data.put("d", tbrq.trim().substring(8, 10));
		        CxpyService cxpyService = new CxpyService();
		        XsxxglService xsxxglService = new XsxxglService();
		        HashMap<String, String> cxpyDj = cxpyService.getCxpyByXhXnXq(xh, model.getXn(), model.getXq());
		        data.put("cxpyDj", cxpyDj.get("cxdjmc"));//��������
		        
		        // ========== ��ѧ��ɼ���� begin ============
		        List<HashMap<String, String>> kcxxlist = xsxxglService.getStuCjOfXnList(xh, model.getXn());
		 
		        pjjgService.addBlankList(kcxxlist, 30 - kcxxlist.size());
				data.put("kcxxlist", kcxxlist); // ��ѧ��ɼ����γ���Ϣ��
					
				List<HashMap<String, String>> xfjdlist = xsxxglService.getStuXFJDOfXnList(xh, model.getXn());
		        data.put("xfjdlist", xfjdlist); // ��ѧ�ּ��㡢ƽ��ѧ�ּ���
		        // ========== ��ѧ��ɼ���� end ============
			}
			
			//����ʯ��
			if("10220".equals(Base.xxdm)) {
				String zwmc = service.getZwForXh(xh);
				data.put("zwmc", zwmc);
				if(zwmc == "") {
					data.put("sfyzw", "0");
				}
				String csrq = xsjbxx.get("csrq");
				data.put("csrq", csrq);
				String jtsfyqk = "";		
				if(jtqkmodel!=null 
						&& jtqkmodel.getJtqzqk()!=null 
						&& jtqkmodel.getJtqzqk().indexOf("��")==-1 
						&& jtqkmodel.getJtqzqk().indexOf("û��")==-1){
					jtsfyqk = "��";
				}else{
					jtsfyqk = "��";
				}
				
				data.put("jtsfyqk", jtsfyqk);// ��ͥ�Ƿ���Ƿ��
				
				if(jtqkmodel != null){
					data.put("sfpkx", jtqkmodel.getSfpkx());// �Ƿ�ƶ����
					data.put("pkxjb", jtqkmodel.getPkxjb());// ƶ���ؼ���
					data.put("fmjk", jtqkmodel.getFmjk());// ��ĸ�Ƿ��в���м�
					data.put("fmjz", jtqkmodel.getFmjz());// ��ĸ�������
				}else{  
					data.put("sfpkx", "");// �Ƿ�ƶ����
					data.put("pkxjb", "");// ƶ���ؼ���
					data.put("fmjk", "");// ��ĸ�Ƿ��в���м�
					data.put("fmjz", "");// ��ĸ�������
				}
				HashMap<String, String> fqxxInfo = jtqkdcService.getfqInfo(xh);
				HashMap<String, String> mqxxInfo = jtqkdcService.getmqInfo(xh);
				data.put("fqzy", fqxxInfo.get("cyzy"));
				data.put("mqzy", mqxxInfo.get("cyzy"));
										
			}
			//������·ְҵ����ѧԺ���Ի�
			if("13943".equals(Base.xxdm)){
				String sqly_13943 = HtmlUtil.xmlZy(model.getSqly());
				String sqly_13943_1 = sqly_13943;
				String sqly_13943_2 = "";
				if(sqly_13943.length() > 363){
					sqly_13943_1 = sqly_13943.substring(0, 363);
					sqly_13943_2 = sqly_13943.substring(363);
				}
				data.put("sqly_13943_1", sqly_13943_1);// ��������(��һ����)
				data.put("sqly_13943_2", sqly_13943_2);// ��������(�ڶ�����)
				String xymc_13943 = xsjbxx.get("xymc");
				if(xymc_13943.length() < 39){
					int max = 39 - xymc_13943.length();
					for (int i = 0; i < max; i++) {
						xymc_13943 += " ";
					}
				}
				data.put("xymc_13943", xymc_13943);
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh_13943", qsbh);
				// ����ְ��
				DwwhService dwwhService = new DwwhService();
				data.put("zwmc", dwwhService.getZwForXh(xh));
				ZcfsService zcfService = new ZcfsService();
				// ��ѧ����ѧ�ڳɼ�
				PjjgService servicePjPy = new PjjgService();
				List<HashMap<String,String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", ""); 
				servicePjPy.addBlankList(bxn_cjSxqBxList, 12 - bxn_cjSxqBxList.size());
				data.put("bxn_cjSxqBxList", bxn_cjSxqBxList);
				HashMap<String, String> bxn_zcfSxq = zcfService.getZczfByXh(xh, model.getXn(), "01");
				data.put("bxn_zcfSxq", bxn_zcfSxq);
				// ��ѧ����ѧ�ڳɼ�
				List<HashMap<String,String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				servicePjPy.addBlankList(bxn_cjXxqBxList, 12 - bxn_cjXxqBxList.size());
				data.put("bxn_cjXxqBxList", bxn_cjXxqBxList);
				HashMap<String, String> bxn_zcfXxq = zcfService.getZczfByXh(xh, model.getXn(), "02");
				data.put("bxn_zcfXxq", bxn_zcfXxq);
				// ����ѧ��
				String pdxq = model.getPdxq();
				if(StringUtils.isNull(pdxq)){
					pdxq = CsszService.XQKG;
				}
				// ��Ŀѧ��ѧ�ڳɼ�
				HashMap<String, String> xmZcf = zcfService.getZczfByXh(xh, model.getPdxn(), pdxq);
				data.put("xmZcf", xmZcf);
				// �༶����
				CpmdService cpmdService = new CpmdService();
				String bjbjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getPdxn(), pdxq);
				data.put("bjbjrs", bjbjrs);
				int yjNum = 0; // Ժ��
				int xjNum = 0; // У��
				int xjysNum = 0; // У������
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pjjgMap = pjjg.get(i);
					String xmxzmc = pjjgMap.get("xmxzmc");
					if(StringUtils.isNotNull(xmxzmc)){
						if(xmxzmc.contains("Ժ��")){
							yjNum++;
						}else if(xmxzmc.contains("У������")){
							xjysNum++;
						}else if(xmxzmc.contains("У��")){
							xjNum++;
						}
					}
				}
				data.put("yjNum", String.valueOf(yjNum));
				data.put("xjNum", String.valueOf(xjNum));
				data.put("xjysNum", String.valueOf(xjysNum));
				// ��ѧ����ѧ�ڰ༶����
				String bxn_cjSxqBjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), "01");
				data.put("bxn_cjSxqBjrs", bxn_cjSxqBjrs);
				// ��ѧ����ѧ�ڰ༶����
				String bxn_cjXxqBjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), "02");
				data.put("bxn_cjXxqBjrs", bxn_cjXxqBjrs);
			}
			//����ʦ��
			if("10718".equals(Base.xxdm)){
				PjjgModel form = new PjjgModel();
				form.setXn(model.getXn());
				form.setXh(model.getXh());
				form.setCpbjdm(xsjbxx.get("bjdm"));
				form.setCpzydm(xsjbxx.get("zydm"));
				data.put("jejg", model.getJe());
		        data.put("xn1", model.getXn().trim().substring(0, 4));
		        data.put("xn2", model.getXn().trim().substring(5, 9));
		        String sqsj = model.getSqsj();
		        data.put("year", sqsj.trim().substring(0, 4));
		        data.put("mon",  sqsj.trim().substring(5, 7));
		        List<HashMap<String, String>> qgzxlist = service.getQgzxList(model.getXh());
		        data.put("qgzxlist", qgzxlist);
		        data.put("mdjlist", service.getMjxList(xh, "���½�ѧ��"));
		        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time=format.format(new java.util.Date());
		        data.put("y", time.trim().substring(0, 4));
		        data.put("m", time.trim().substring(5, 7));
		        data.put("d", time.trim().substring(8, 10));
		        if(service.getSfzxDk(xh).get("cs").equals("0")){
		        	data.put("zxdk", "��");
		        }else{
		        	data.put("zxdk", "��");
		        }
		        String sxn = (Integer.parseInt(model.getXn().trim().substring(0, 4))-1)+"-"+(Integer.parseInt(model.getXn().trim().substring(5, 9))-1);
		        HashMap<String, String> sfkns = service.getSfkns(xh, model.getXn());
		        if(sfkns.get("dcmc") == null){
		        	sfkns.put("dcmc", "��ͥ���ò�����");
		        }
		        List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
		        HashMap<String,String> sfxs = service.getSfxs();
		        data.putAll(sfkns);
		        if((sfxs.get("dqnd").trim()).equals(xsjbxx.get("rxrq").trim().substring(0, 4))){
		        	sfkns.put("isxs", "��");
		        }else{
		        	sfkns.put("isxs", "��");
		        }
		        JtqkdcDao jtqkDao = new JtqkdcDao();
		        List<HashMap<String, String>> cyListSXSD = jtqkDao.getJtcyListFour(model.getXh());
		        data.put("cyListSXSD", cyListSXSD);//��ͥ��Ա�б�
		        data.put("blankList", 4 > cyList.size() ? jtqkdcService
						.getBlankList(4 - cyList.size()) : jtqkdcService
						.getBlankList(0));
		        ZzxmjgDao zzjgDao = new ZzxmjgDao();
		        data.put("zypm", zzjgDao.getXsZyBxPm(sxn, xsjbxx.get("zydm"), xh));
		        data.put("zyrs", zzjgDao.getZyrs(xsjbxx.get("zydm")));
		        data.put("pjcj", zzjgDao.getBxPjcj(xh, sxn));
		        data.put("zdf", zzjgDao.getZdf(xh, sxn));
		        data.put("zdfkmmc", zzjgDao.getZdfkmmc(xh, sxn));    
			}
			
			//�����մ�
			if("12930".equals(Base.xxdm)) {
				HashMap<String, String> cjpm = pjjgService.getCjPm(model.getXh(),model.getXn());
				data.put("cjpm", cjpm);
				HashMap<String, String> bxkms_12930 = pjjgService.getXakjdxylzjbxkms(model.getXh(),model.getXn());
				//���޿μ�������
				data.put("bxkjgms", bxkms_12930.get("bxkjgms"));
				data.put("bxkms", bxkms_12930.get("bxkms"));
				PjjgService servicePjPy = new PjjgService();
				PjjgAction pjjgAction = new PjjgAction();
				List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
				data.put("pjjgListhjqk", pjjgListhjqk);
				int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
				data.put("blankListhjqk", pjjgAction.getBlankList(size1));
				//��־�õ�����
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 3 - pjjgList.size());
				data.put("pjjgList1", pjjgList);
				int size=(3 - pjjgList.size())<0?0:(3 - pjjgList.size());
				data.put("blankList1", pjjgAction.getBlankList(size));
				ZzxmjgService zzxmjgservice_12930=new ZzxmjgService();
				List<HashMap<String, String>> shyjList = zzxmjgservice_12930.getShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+i, shyjList.get(i).get("shyj"));
				}
			}
			String jtrjysrtemp = " ";
			try {
				jtqkmodel = jtqkmodel == null ? new JtqkdcForm() : jtqkmodel;
				String jtnzsr = jtqkmodel.getJtnzsr() != null ? jtqkmodel.getJtnzsr() : "0";
				String jtrs = jtqkmodel.getJtrs() != null ? jtqkmodel.getJtrs() : "0";
				if (!jtrs.equals("0")) {
					jtrjysrtemp = String.valueOf((int) (Double.valueOf(jtnzsr) / Double.valueOf(jtrs) / 12));
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
			data.put("jtrjysrtemp", jtrjysrtemp);// ��ͥ�˾�������
			//������Ϣְҵ����ѧԺ
			if ("12755".equals(Base.xxdm)) {
				List<HashMap<String, String>> cyList3line = jtqkdcService.getJtcyList(xh, 3); //ȡ3����ͥ��Ա�б�����3�����
				data.put("cyList3line", cyList3line);
				data.put("hkxian", xsjbxx.get("hkxian"));//������
				String tbrq = model.getSqsj();// ����ʱ��
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				ZcfsService zcfService = new ZcfsService();
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// �༶����
				data.put("cjpm", cjpm);// �ܳɼ�����
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����
				String mzmc = xsjbxx.get("mzmc").trim();
				String ssmz = "";
				if("����".equals(mzmc)){
					ssmz="��";
				}else if(!"����".equals(mzmc) && !"".equals(mzmc) && mzmc != null){
					ssmz="��";
				}
				data.put("ssmz", ssmz);
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjg4_12755 = pjjgservice.getHjqkByXhMap(xh, 4);  //��ȡ����4���������
				for (HashMap<String, String> hashMap : pjjg4_12755) {
					String xmmc = hashMap.get("xmmc");
					if(xmmc!=null && !"".equals(xmmc)){
						hashMap.put("bjdw","������Ϣ����ְҵѧԺ ");
					}
				}
				data.put("pjjg4_12755", pjjg4_12755);
				if (jtqkmodel != null && jtqkmodel.getJtnzsr() != null) {
					data.put("jtrjysr12755", jtqkmodel.getJtrs() == null ? "" :  String.format("%.2f",Double.parseDouble(jtqkmodel.getJtnzsr()) / 12 / Integer.parseInt(jtqkmodel.getJtrs())));//��ͥ�˾�������
					data.put("jtyzsr12755", String.format("%.2f", Double.parseDouble(jtqkmodel.getJtnzsr()) / 12));//��ͥ��������
				} else {
					data.put("jtyzsr12755", "");
					data.put("jtrjysr12755","");
				}
			}
			//����ʦ�����Ի�
			if("10346".equals(Base.xxdm)){
				ZcfsService zcfService = new ZcfsService();
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// �༶����
				data.put("cjpm", cjpm);// �ܳɼ�����
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����

				//�������������Ϣ
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjg4line = pjjgservice.getHjqkByXhMap(xh, 4);  //��ȡ����4���������
				for (HashMap<String, String> hashMap : pjjg4line) {
					String xmmc = hashMap.get("xmmc");
					if(xmmc!=null && !"".equals(xmmc)){
						hashMap.put("bjdw","����ʦ����ѧ");
					}
				}
				data.put("pjjg4_hzsf", pjjg4line);
				
				//����������Ϣ��3����
				List<HashMap<String, String>> zzdwlist = service.getZzxmjgInfoList(xh);
				int m=3-zzdwlist.size();
				for (int i = 0; i <m; i++) {
					zzdwlist.add(new HashMap<String, String>());
				}
				List<HashMap<String, String>> pjjg3line = pjjgservice.getHjqkByXhMap(xh, 3);  //��ȡ����3���������
				//��������Ϣ������������У�����xml�ļ���ȡ
				for (int i = 0; i < pjjg3line.size(); i++) {
					pjjg3line.get(i).put("zzxmmc", zzdwlist.get(i).get("xmmc"));
					pjjg3line.get(i).put("zzje", zzdwlist.get(i).get("je"));
				}
				data.put("pjjg3_hzsf", pjjg3line);
				//��ѧ������Ϣ
				XszzSqshService xszzSqshService = new XszzSqshService();
				String dkxx = xszzSqshService.getXsDkxx(Base.currXn, xh);
				data.put("zxdkxx", dkxx);
				
			}
			XszzSqshService xszzSqshservice = new XszzSqshService();
			
			String theSameXmmcNum = xszzSqshservice.getTheSameZzxmNumber(bbMap.get("bbmc"), xh);  //��ǰ������ͬ��Ŀ������
			//����������׼ͨ������ͬ��Ŀ������
			data.put("theSameXmmcNum", theSameXmmcNum);
			
			List<HashMap<String,String>> ywqtjxj = xszzSqshservice.getYwqtjxList(bbMap.get("bbmc"), xh);
			//��ѧ�ڼ�ͬʱ����������(��)ѧ��
			data.put("ywqtjxj", ywqtjxj==null?new ArrayList<HashMap<String,String>>():ywqtjxj);
			
			String szbbjrs = xszzSqshservice.countBjRs(xh);
			
			data.put("szbbjrs", szbbjrs);  //���ڰ�༶����
			//������Ŀ����
			data.put("bbmc", bbMap.get("bbmc"));
			data.put("xmmc", model.getXmmc());
			//�㽭��ְͨҵ����ѧԺ
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getGuid());
				for (int i = 0; i < shyjjtzyList.size(); i++) {
					data.put("shyj"+(i+1), shyjjtzyList.get(i).get("shyj"));
				}
			}
			WdgwsqService wdgwsqService = new WdgwsqService();
			HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
			String qsh=qsxx.get("qsh");
			data.put("qsh", qsh);
			
			
			//ͨ��������1-7��
			//�ȸ���guidȡ��ҵ������id,Ȼ�����ҵ������idȡ��������
			String shyjGuid = service.getSqbIdByYwid(myForm.getGuid());
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(shyjGuid);
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			/*String csny = xsjbxx.get("csrq");//��������
			if (csny != null) {
				csny = csny.replace("-","");
				if(csny.length() >= 6){
					csny = csny.substring(0, 6);
				}
			}
			
			data.put("csny", csny);// ��������,���ݳ������ڽ�ȡ
			 */
			data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
			data.put("photo", photo);// ѧ����Ƭ
			//File wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR
				//	+ bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]"+"-"+model.getXmdm());//"classpath://templates//" + "xszz" + "gjjxjb.xml"
			File wordFile =null;
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(xsjbxx.get("bjmc")+""+xsjbxx.get("xh")+"["+xsjbxx.get("xm")+"]"));
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(model.getXh()+"["+xsjbxx.get("xm")+"]"));
			}
			return wordFile;
		}

		return null;
	}
	
	
	/**
	 * 
	 * @����:��ӡexcel-֧�ֶ�ѡ
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-14 ����11:23:46
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadMultiExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String rows =  request.getParameter("rows");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		//---------��ȡģ�����ݺ�����-----------------------------------------------------
		HashMap<String, String> bbMap = null;
		User user = getUser(request);
		ZzxmjgService service = new ZzxmjgService();
		List<HashMap<String, String>> zzxmjgLists = new ArrayList<HashMap<String, String>>();
		int count=1;
		if(rowsList!=null && rowsList.size()>0){
			for (Object object : rowsList) {
				MorphDynaBean rowBean = (MorphDynaBean) object;
				ZzxmjgForm model = new ZzxmjgForm();
				model.setLbdm((String) rowBean.get("lbdm"));
				model.setXn((String) rowBean.get("xn"));
				model.setXq((String) rowBean.get("xq"));
				model.setXmmc((String) rowBean.get("xmmc"));
				if(rowBean.get("xmmc")!=null){
					if(bbMap==null){
						bbMap = this.getBbMap(model);
					}
				}
				List<HashMap<String, String>> zzxmjgList = new ArrayList<HashMap<String, String>>();
				
				//����ʦ��ѧԺ���Ի�
				if("10402".equals(Base.xxdm)){
					zzxmjgList = service.zzxmhzView_10402(model, user);//����ҳ
				}else if("11483".equals(Base.xxdm)){
					zzxmjgList = service.getXmGxhDy_12947_gjzxjbahzexcel(model.getXmmc(),model.getXn(),model.getXq());
				}else if("14008".equals(Base.xxdm)){
					zzxmjgList = service.getDxscbInfoList(model, user); // ����ҳ
				}else if("12898".equals(Base.xxdm)){
					zzxmjgList = service.getLnjdzyjsxyList(model, user); //��������ְҵ����ѧԺ
				}else if("12930".equals(Base.xxdm)){
					zzxmjgList = service.getJxtczzxmList(model, user); //�����մ�
				}else if("10351".equals(Base.xxdm)){
					zzxmjgList = service.getWzdxzzxmList(model, user);
				}else{
					// ͨ��
					zzxmjgList = service.zzxmhzView(model, user, false);//����ҳ
				}
				
				for(HashMap<String, String> map:zzxmjgList){
					map.put("sqly",map.get("sqly"));
					map.put("row",String.valueOf(count));//�Ӹ����
					map.put("rxny",DateTranCnDate.fomartDateToCn(map.get("rxrq"),FomartDateType.month));//��ѧ����   XXXX��XX��
					zzxmjgLists.add(map);
					count ++;
				}
			}
		}
		//--------------------------------------------------------------------------------
		if (bbMap == null || bbMap.size() == 0) {//��ѯ���������������Ϣ
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		
		if(zzxmjgLists!=null && zzxmjgLists.size()>0){
			File excelFile = getExcel(zzxmjgLists,bbMap,user);
			if(excelFile!=null){
				FileUtil.outputExcel(response, excelFile);
			}
		}
		return null;
	}


	/**
	 * @����:��Ŀ�����ϱ����ӡ��ѡ����ѧԺ�ģ�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/8/27 17:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadMultiExcelByXy(ActionMapping mapping, ActionForm form,
											HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String rows =  request.getParameter("rows");
		String[] xydms =  request.getParameter("xydms").split(",");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		User user = getUser(request);
		ZzxmjgService service = new ZzxmjgService();
		List<File> files = new ArrayList<File>();
		String xmmc = "";
		//---------��ȡģ�����ݺ�����-----------------------------------------------------

		for(String xydm : xydms){
			int count=1;
			HashMap<String, String> bbMap = null;
			List<HashMap<String, String>> zzxmjgLists = new ArrayList<HashMap<String, String>>();
			if(rowsList!=null && rowsList.size()>0){
				for (Object object : rowsList) {
					MorphDynaBean rowBean = (MorphDynaBean) object;
					ZzxmjgForm model = new ZzxmjgForm();
					model.setType("excelByXy");
					model.setXydm(xydm);
					model.setLbdm((String) rowBean.get("lbdm"));
					model.setXn((String) rowBean.get("xn"));
					model.setXq((String) rowBean.get("xq"));
					model.setXmmc((String) rowBean.get("xmmc"));
					xmmc=(String) rowBean.get("xmmc");
					if(rowBean.get("xmmc")!=null){
						if(bbMap==null){
							bbMap = this.getBbMap(model);
						}
					}
					List<HashMap<String, String>> zzxmjgList  = service.zzxmhzView(model, user, false);//����ҳ
					for(HashMap<String, String> map:zzxmjgList){
						map.put("sqly",map.get("sqly"));
						map.put("row",String.valueOf(count));//�Ӹ����
						map.put("rxny",DateTranCnDate.fomartDateToCn(map.get("rxrq"),FomartDateType.month));//��ѧ����   XXXX��XX��
						zzxmjgLists.add(map);
						count ++;
					}
				}
			}
			//--------------------------------------------------------------------------------
			if (bbMap == null || bbMap.size() == 0) {//��ѯ���������������Ϣ
				throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
			}
			if(zzxmjgLists!=null && zzxmjgLists.size()>0){
				bbMap.put("type","excelByXy");
				File excelFile = getExcel(zzxmjgLists,bbMap,user);
				if(excelFile!=null){
					files.add(excelFile);
				}
			}else{
				//��ȡ�յ�Excel
				String xymc = new PjjgService().getXymcBydm(xydm);
				String fileName = FreeMarkerUtil.getFileName(xmmc+"_"+xymc);
				File file = new File(System.getProperty("java.io.tmpdir"),fileName+".xls");
				if(!file.exists()){
					file.createNewFile();
				}
				WritableWorkbook wwb = Workbook.createWorkbook(file);
				WritableSheet sheetNull = wwb.createSheet("���ε�������Ϊ��", 0);
				sheetNull.setColumnView(0, 15);
				Label msg = new Label(0, 0,"�������ݣ�");
				sheetNull.addCell(msg);
				wwb.write();
				wwb.close();
				files.add(file);
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}


	// ���ģ����������excel�ļ�
	private File getExcel(List<HashMap<String, String>> zzxmjgLists,HashMap<String, String> bbMap, User user) throws Exception {
		double zje = 0;
		String xymc = "";
		if(zzxmjgLists != null && !zzxmjgLists.isEmpty()){
			for (int i = 0; i < zzxmjgLists.size(); i++) {
				if(!"".equals(zzxmjgLists.get(i).get("je"))&&StringUtils.isNotNull(zzxmjgLists.get(i).get("je"))){
					zje = zje + Double.parseDouble(zzxmjgLists.get(i).get("je")) ;
				}
			}
			xymc = zzxmjgLists.get(0).get("xymc");
		}
		String xn = zzxmjgLists.get(0).get("xn");
		String xmmc = zzxmjgLists.get(0).get("xmmc");
		String pdxn = zzxmjgLists.get(0).get("pdxn");//����ѧ��
		String xh = zzxmjgLists.get(0).get("xh");
		String xq = zzxmjgLists.get(0).get("xq");
		Map<String, Object> data = new HashMap<String, Object>();
		ZzxmjgService service = new ZzxmjgService();
		Map<String, String> jzgInfo = service.getJzgInfo(user); //��ְ����Ϣ
		//���ݴ�ѧ
		if("10351".equals(Base.xxdm)) {
			HashMap<String, String> bxkms_10351 = service.getWzdxbxkms(xh, xn);
			data.put("bxkjgms", bxkms_10351.get("bxkjgms"));
			data.put("bxkms", bxkms_10351.get("bxkms"));
			ZcfsService zcfService = new ZcfsService();
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, xn, xq);
			
			HashMap<String, String> pm = pjjgService.getCjPm(xh,xn);
			data.put("pm", pm);
		}
		// ������Ͽҽҩ�ߵ�ר��ѧУ
		if("14008".equals(Base.xxdm)) {
			String beforXn = xn.substring(0, 4);
			String afterXn = xn.substring(5, 9);
			data.put("beforXn", beforXn);
			data.put("afterXn", afterXn);
		}
		
		JtqkdcService jtqkdcService = new JtqkdcService();
		
		data.put("blankList", ZZJGSIZE > zzxmjgLists.size() ? jtqkdcService.getBlankList(ZZJGSIZE - zzxmjgLists.size()) : jtqkdcService.getBlankList(0));
		data.put("zzxmjgList", zzxmjgLists);//������Ŀ����б�
		data.put("bbMap", bbMap);
		data.put("xxmc", Base.xxmc);// ѧУ����
		data.put("xn", xn);
		data.put("dqrq", DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));//ɽ���ƾ���ѧ��ȡ��ǰ��ӡ����
		data.put("currxn", Base.currNd);
		data.put("xmmc", xmmc);
		data.put("pdxn", pdxn);//����ѧ��
		data.put("today", DateUtils.getLyr());
		data.put("month", DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.month)); //�·�
		data.put("jzgInfo", jzgInfo);
		data.put("zje",zje);
		String templateDirectory = Constants.TEP_DIR + bbMap.get("mblj");
		String templateName = bbMap.get("mbmc");
		String fileName = FreeMarkerUtil.getFileName(xmmc);
		if(StringUtils.equals("12036", Base.xxdm) && StringUtils.equals("excelByXy",bbMap.get("type"))){
			fileName = FreeMarkerUtil.getFileName(xmmc+"_"+xymc);
		}
		File wordFile = FreeMarkerUtil.getExcelFile(data, templateDirectory, templateName, fileName);
		return wordFile;
	}

	
	// ������Ŀ���ƻ�ȡ������Ϣ
	public HashMap<String, String> getBbMap(ZzxmjgForm model) throws Exception{
		HashMap<String, String> bbMap = null;// ����
		
		if (!StringUtil.isNull(model.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(),model.getXn(),model.getXq());//��ѯ��Ŀ��¼
			if (xmMap != null) {
				//model.setXmdm(xmMap.get("xmdm"));//��Ŀ����
				BbwhService bbwhService = new BbwhService();
				bbMap = bbwhService.getDataById(xmMap.get("dysbbb"));//��ȡ��Ӧ�ϱ�����
			}
		}
		if (bbMap == null || bbMap.size() == 0) {//��ѯ���������������Ϣ
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		return bbMap;
	}
	
	/*public List getNewZzjgLists(List<HashMap<String, String>> zzxmjgLists){
		List<HashMap<String, String>> newzZxmjgList = new ArrayList<HashMap<String, String>>();
		boolean flag = true;
		for(int i=0;i<zzxmjgLists.size()-1;i++){
			newzZxmjgList.add(zzxmjgLists.get(i));
			for(int j=zzxmjgLists.size();j>i;j--){
				if(zzxmjgLists.get().get("xmmc")==zzxmjgLists.get(i).get("xmmc")){
					
				}
			}
			if(zzxmjgLists.get(i+1).get("xmmc")!=zzxmjgLists.get(i).get("xmmc")){
				flag = false;
			}else{
				newzZxmjgList.add(zzxmjgLists.get(i+1));
			}
		}
		
		return null;
		
	}*/
	/**
	 * �����������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zzjgCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgService service = new ZzxmjgService();
		request.setAttribute("kfzZqList", service.getkfzZqList(Base.currXn));
		request.setAttribute("xn",Base.currXn);
		this.saveToken(request);
		return mapping.findForward("zzjgCopy");
	}
	/**
	 * 
	 * @����:���˸��Ʊ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-18 ����03:35:56
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
	public ActionForward saveCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		ZzxmjgService service = new ZzxmjgService();
		String lyxn=request.getParameter("lyxn");
		String mbxn=request.getParameter("mbxn");
		boolean result = service.copy(lyxn,mbxn);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//ɽ��������ҽְҵѧԺ���Ի����������ѧ����ܱ�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getSdxm_shzxjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
			 {
		    ZzxmjgForm model = (ZzxmjgForm) form;
			ZzxmjgDao dao = new ZzxmjgDao();
			Map<String,Object> data = new HashMap<String,Object>();
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = dao.getDcList(model,user);
			String zrs = "";
			float xmze1 = 0;
			for (HashMap<String, String> hashMap : resultList) {
				if(zrs.equals("")&&hashMap.get("total")!= null){
					zrs = hashMap.get("total");
				}
				if(hashMap.get("xmje")!= null){
					xmze1 = xmze1+ Float.parseFloat(hashMap.get("xmje"));
				}
			}
			if(zrs.equals("")){
				zrs = "0";
			}
			String xmze = xmze1+"";
			data.put("xmze",xmze);
			data.put("zrs", zrs);
			data.put("xsxxlist",resultList);
			String xmlb1 = request.getParameter("value");
			String[] xmlb = xmlb1.split(",");
			data.put("xmlb", xmlb[0]);
			File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_shzxjhzb.xml", "�����ѧ��ѧ�����ܱ�");
			FileUtil.outputWord(response, excelFile);
			return null;
	}
	
	/*ɽ��������ҽְҵѧԺ���Ի����󣨹�����־��ѧ����ܱ�
	public ActionForward getSdxm_gjlzjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		User user = getUser(request);
		ZzxmjgService pjjgserice = new ZzxmjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_gjlzjhzexcel(values,user);
		FileUtil.outputWord(response, excelFile);
		return null;
	}*/
	
	/*ɽ��������ҽְҵѧԺ���Ի����󣨹�����ѧ����ܱ�
	public ActionForward getSdxm_gjzxjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		ZzxmjgService pjjgserice = new ZzxmjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_gjzxjbahzexcel(values);
		FileUtil.outputWord(response, excelFile);
		return null;
	}*/
	
	//ɽ��������ҽְҵѧԺ���Ի����󣨹�����ѧ����ܱ�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getSdxm_gjlzjhzmbexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		ZzxmjgService pjjgserice = new ZzxmjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_gjlzjhzmbexcel(values);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	/**
	 * 
	 * @����: ������Ŀ���ܵ���������ʦ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-8 ����01:19:07
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
	public ActionForward exportDataSx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.zzxmhzView(model, user, false);// ��ѯ�����м�¼������ҳ

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
     * @throws Exception 
     * @throws IOException 
     * 
     * @����:�Ϻ�����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-10-15 ����10:46:58
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * ActionForwad �������� 
     * @throws
     */
	public ActionForward exportShty_hzdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		String[] params = request.getParameterValues("params");
		ZzxmjgService service = new ZzxmjgService();
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("shty_zzxmjehz_tjdc.xls".getBytes(), "GBK") + "\"");
		service.createWwb(params,response.getOutputStream());
		return null;
	}
	
	/** 
	 * @����:�ൺ�Ƶ������Ի�����(�������֤�Ÿ��Ի�����)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-2 ����03:28:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward drForQdjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("drForQdjd");
	}
	
	/**
	 * @throws IOException  
	 * @����:���ص���ģ��(�ൺ�Ƶ����ְҵ����ѧԺ���Ի�)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-2 ����03:45:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡ�ൺ�Ƶ�ְҵ����ѧԺ����ģ��
		String temPath = request.getSession().getServletContext().getRealPath("/temp/mb/zzjgmb_13011.xls");
		File file = new File(temPath);
		if(file.exists()){
			response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("�����������ģ��.xls","utf-8")); 
			FileUtil.outputFile(response, file);
		}
		return null;		
	}
	
	/** 
	 * @����:���浼��(�ൺ�Ƶ����ְҵ����ѧԺ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-2 ����04:26:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward saveDrForQdjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ZzxmjgForm model = (ZzxmjgForm)form;
		FormFile file = model.getDrmb();
		ZzxmjgService service = new ZzxmjgService();
		if(file != null){
			try {
				model.setFilepath(servlet.getServletContext().getRealPath(
				"/temp/importTemp/")+"/");
				try {
					HashMap<String,Object> resultMap = service.saveDrForQdjd(model);
					String message = CG;
					if(resultMap.get("result").equals("true")){
						 Map<String,String> map = new HashMap<String, String>();
							map.put("message", message);
							JSONObject json = JSONObject.fromObject(map);
							response.getWriter().print(json);
							return null;
					}else if(resultMap.get("result").equals("null")){
						 message = NOCONTENT;
						 Map<String,String> map = new HashMap<String, String>();
							map.put("message", message);
							JSONObject json = JSONObject.fromObject(map);
							response.getWriter().print(json);
							return null;
					}
					else{
					    message = SB;
					    Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						map.put("gid", (String)resultMap.get("gid"));
						if(resultMap.get("cgs") != null){							
							map.put("cgs", (String)resultMap.get("cgs"));
						}else{
							map.put("cgs", "0");
						}
						map.put("cws", (String)resultMap.get("cws"));
						JSONObject json = JSONObject.fromObject(map); 
					    response.getWriter().print(json);
						return null;
					}
				} catch (BiffException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				logger.info("�����ļ�δ�ҵ���");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				logger.info("IO�쳣��");
				e.printStackTrace();
			}
		}else{
			    String message = KONG;
			    Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				try {
					response.getWriter().print(json);
				} catch (IOException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				return null;
		}
		
		return null;		
	}
	
	/** 
	 * @����:���ش�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����03:21:19
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
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//�õ�tomcat/webapp/temp/importTemp�´����ļ���·��
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("��������.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @description	�� ���ҽ����
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-24 ����03:14:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxdc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		
		List<HashMap<String, String>> resultList = null;
		
		// ��ѯ
		String dcclbh = request.getParameter("dcclbh");
		//���ҽ�ѧ�𵼳�
		if(dcclbh.equalsIgnoreCase("xg_xszz_gjjxz.do")){			
			resultList = service.getGjjxjdc(model,user);// ��ѯ�����м�¼������ҳ
		}else if(dcclbh.equalsIgnoreCase("xg_xszz_gjzxz.do")){//������ѧ�𵼳�
			resultList = service.getGjzxjdc(model,user);
		}else if(dcclbh.equalsIgnoreCase("xg_xszz_gjlzj.do")){
			resultList = service.getGjlzjdc(model,user);
		}else if(dcclbh.equalsIgnoreCase("xg_xszz_xmzzqkhz.do")){
			resultList = service.getXmzzqkhz(model,user);
		}

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(dcclbh);// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}


