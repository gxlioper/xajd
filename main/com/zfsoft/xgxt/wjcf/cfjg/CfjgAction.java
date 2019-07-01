/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-5 ����11:40:39 
 */
package com.zfsoft.xgxt.wjcf.cfjg;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jcp.xml.dsig.internal.dom.Utils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ���ֽ����
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-8-5 ����11:40:39
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CfjgAction extends SuperAction {
	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	
	private static final String url = "wjcf_cfjg_new.do";
	CfjgService service = new CfjgService();
	/**
	 * 
	 * @����:���ֽ����ѯ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-5 ����12:51:01
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
	public ActionForward cxCfjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		CfjgForm myForm = (CfjgForm) form;
		CommService cs = new CommService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("wjcf_cfjg_new.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "wjcf_cfjg_new.do";
		request.setAttribute("path", path);
		request.setAttribute("cflbsList", wjcfjcszService.cflbmcCx());// Υ�ʹ������
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxCfjgList");
	}

	/**
	 * ���Ӵ�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfjgZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		CfjgForm myForm = (CfjgForm) form;
		
		//�ൺ�Ƶ����ְҵ����ѧԺ�����ɴ����ĺ�
		if("13011".equals(Base.xxdm)){
			String cfwh = service.getDefaultCfwhFor13011();
			myForm.setCfwh(cfwh);
		}
		
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		//ѧ��������Ϣ��ʾ����
		if (!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		String path = "wjcf_cfjg.do?method=cfjgZj";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());// Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());// Υ�ʹ���ԭ��
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(xh));// ����Υ�ʹ���
		// ����ѧ��ѧ���б�
		FormModleCommon.setNdXnXqList(request);
		
		User user = getUser(request);
		request.setAttribute("sbbxm", user.getRealName());
		request.setAttribute("xnndList", service.getXnndList());
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cfjgZj");

	}

	/**
	 * 
	 * @����:�������ӱ���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-6 ����02:13:55
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
	@SystemLog(description = "����Υ�ʹ���-������ʽ��-���ֽ��-����XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward saveCfjgZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		CfjgForm myForm = (CfjgForm) form;
		User user = getUser(request);// �û�����
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(GetTime.getNowTime4());
		if("12915".equals(Base.xxdm)){
			myForm.setCflsh(service.getLsh(myForm));
		}
		else{
			myForm.setCflsh("");
		}
		if(!service.checkIsNotRepeat(myForm)){
			String message = "��ѧ�걾ѧ�����д��ּ�¼�������ظ���д��¼��";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean flag = service.saveCfsb(myForm);
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * �޸Ĵ�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfjgXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		String sjly = request.getParameter("sjly");//������Դ  1�������0���������
		CfjgForm myForm = (CfjgForm) form;
		myForm.setCfid(request.getParameter("cfid"));
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		// ��ѯ�������
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());
		//ѧ��������Ϣ��ʾ����
		if (!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		String path = "wjcf_cfjg.do?method=cfjgXg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", StringUtils.formatData(rs));
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());// Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyymcCx());// Υ�ʹ���ԭ��
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(rs.get("xh")));// ����Υ�ʹ���
		request.setAttribute("type", UPDATE);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("xnndList", service.getXnndList());
		// ����ѧ��ѧ���б�
		FormModleCommon.setNdXnXqList(request);
		if ("1".equals(sjly)) {//��������ĺͲ����������ҳ�治ͬ��
			return mapping.findForward("cfjgshlcXg");
		}else {
			return mapping.findForward("cfjgXg");
		}

	}

	/**
	 * 
	 * @����:�������̵Ĵ����޸ı���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-6 ����02:15:22
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
	@SystemLog(description = "����Υ�ʹ���-������ʽ��-���ֽ��-�޸�CFID:{cfid},XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward saveCfjgXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		User user = getUser(request);// �û�����
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(DealString.getDateTime());
		if(!service.checkIsNotRepeat(myForm)){
			String message = "��ѧ�걾ѧ�����д��ּ�¼�������ظ���д��¼��";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean flag = service.cfsjwhXg(myForm);
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:�����̵Ĵ����޸ı���
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-16 ����11:21:34
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
	@SystemLog(description = "����Υ�ʹ���-������ʽ��-���ֽ��-�޸�CFID:{cfid},XH:{xh},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward saveCfjgshlcXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		User user = getUser(request);// �û�����
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(DealString.getDateTime());
		boolean flag = service.cfsjshlcXg(myForm);
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * �鿴������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward cfjgCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		myForm.setCfid(request.getParameter("cfid"));

		// ��ѯ�������
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(rs.get("xh")));// ����Υ�ʹ���
		request.setAttribute("rs", StringUtils.formatData(rs));
		CflbdmwhService cflbdmwhSv = new CflbdmwhService();
		String cflbmc = rs.get("cflbmc");
		String cfqx = cflbdmwhSv.getCfqxByMc(cflbmc);
		request.setAttribute("cfqx", cfqx);
		return mapping.findForward("cfjgCk");
	}

	/**
	 * 
	 * @����:ɾ��������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-6 ����03:51:00
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
	@SystemLog(description = "����Υ�ʹ���-������ʽ��-���ֽ��-ɾ��VALUES:{values}")
	public ActionForward cfjgSc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		String values = request.getParameter("values");
		String[] valueList = values.split(",");
		String messageKey = service.cfsjwhSc(valueList) ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * ���洦�����߽��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfssjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfssjgBc(myForm);
		request.setAttribute("isSuccess", flag == true ? "true" : "false");
		request.setAttribute("message", flag ? "�����ɹ���" : "����ʧ�ܣ�");
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @����:�������������㱣��
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-8-30 ����03:23:39
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
	public ActionForward saveWjcfss(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfssjgBc(myForm);

		request.setAttribute("isSuccess", flag == true ? "true" : "false");
		String message = flag ? "�����ɹ���" : "����ʧ�ܣ�";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-6 ����02:03:19
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
	public ActionForward cfjgSs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			CflbdmwhService cflbdmwhSv = new CflbdmwhService();
			WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
			String cfqx = "";
			if (!StringUtils.isNull(result.get("cflbmc"))) {
				cfqx = cflbdmwhSv.getCfqxByMc(result.get("cflbmc"));
			}
			request.setAttribute("cfqx", cfqx);
			request.setAttribute("wjcfss", result);
			request.setAttribute("cflbsList", wjcfjcszService.cflbmcCx());
		}
		return mapping.findForward("cfjgSs");
	}

	/**
	 * �鿴������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward cfsssjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(StringUtils.formatData(result)));
		return null;

	}

	/**
	 * ���洦�ֽ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfjcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfjcjgBc(myForm);
		request.setAttribute("message", flag ? "�����ɹ���" : "����ʧ�ܣ�");
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @����:���ֽ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-13 ����10:49:18
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
	public ActionForward cfjgJc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			myForm.setJcsj(result.get("jcsj"));
			myForm.setJcwh(result.get("jcwh"));
			myForm.setJcyj(result.get("jcyj"));
		}
		return mapping.findForward("cfjgJc");
	}

	/**
	 * 
	 * @����:������ֹ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-13 ����10:49:01
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
	public ActionForward cfjgZz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;

		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			myForm.setZzsj(result.get("zzsj"));
			myForm.setZzwh(result.get("zzwh"));
			myForm.setZzyj(result.get("zzyj"));
		}
		request.setAttribute("cfid", request.getParameter("cfid"));
		return mapping.findForward("cfjgZz");
	}

	public ActionForward getKzzFlag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CflbdmwhService cflbdmwhSv = new CflbdmwhService();
		String message = "";
		if (StringUtils.isNotNull(myForm.getCflbmc())) {
			boolean flag = cflbdmwhSv.zzwjcfFlag(myForm.getCflbmc(), myForm.getCfsj());
			if (!flag) {
				message = MessageUtil.getText(MessageKey.WJCF_CFLBMC_BKZZ, myForm.getCflbmc());
			}
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	/**
	 * 
	 * @����:���ֽ������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-13 ����10:48:39
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
	public ActionForward saveWjcfjc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfjcjgBc(myForm);
		String message = flag ? "�����ɹ���" : "����ʧ�ܣ�";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		
		return null;

	}

	/**
	 * 
	 * @����:������ֹ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-13 ����10:48:07
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
	public ActionForward saveWjcfzz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfzzjgBc(myForm);
		String message = flag ? "�����ɹ���" : "����ʧ�ܣ�";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;

	}

	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward fjxz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + DealString.toUtf8String(myForm.getFjmc()));
		InputStream in = service.fjCx(myForm);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}

	/**
	 * 
	 * @����:����������
	 * @���ߣ�zhangxiaobin[���ţ�1036]
	 * @���ڣ�2014-3-28 ����02:11:52
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
	public ActionForward doPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		// ��ѯ�������
		HashMap<String, String> data = service.cfsjwhCk(myForm.getCfid());
		HashMap<String, Object> objectData = new HashMap<String, Object>();
		objectData.putAll(data);
		File file = null;
		String guid = "wjcf_jccfsq_10351"; // �ļ�����id
		objectData.put("xxmc", Base.xxmc);
		file = BbdmUtils.getSigleFile(guid, objectData);
		FileUtil.outputWord(response, file);
		return null;
	}

	/**
	 * 
	 * @����:��ӡΥ�ʹ���֪ͨ��(�Ϻ�����ְҵ����ѧԺ)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-21 ����11:51:05
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
	public ActionForward doPrintWjcfWord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		File wordFile = getWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	private File getWord(CfjgForm myForm, HttpServletRequest request) throws Exception {
		CfjgService service = new CfjgService();
		// ��ѯ�������
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfsjwhCk(myForm.getCfid());
		String cfsjFormat = cfData.get("cfsj");
		cfsjFormat = cfsjFormat.replace("-", "/");
		data.putAll(cfData);
		String formatTime = DateTranCnDate.fomartDateToCn(cfData.get("cfsj"), FomartDateType.day);
		String cfbh=Base.currNd+cfData.get("xq")+cfData.get("cflsh");
		data.put("cfsjFormatCN", formatTime);
		data.put("cfsjFormat", cfsjFormat);
		data.put("tzsbh", cfbh);
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", "wjcftzs_12915.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;

	}

	/**
	 * 
	 * @����:���־�����������ӡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-6 ����10:20:37
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
	public ActionForward doPrintCfjdWordZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		String value = request.getParameter("cfid");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				myForm.setCfid(values[i]);
				File file = getcfjdWord(myForm, request);
				if(null!=file){
				files.add(file);
				}
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
		
	}
	/**
	 * 
	 * @����:���־���������(�㽭��ҵְҵ����ѧԺ)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-1 ����11:00:03
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
	public ActionForward doPrintCfjdWord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		File wordFile = getcfjdWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	private File getcfjdWord(CfjgForm myForm, HttpServletRequest request) throws Exception {
		CfjgService service = new CfjgService();
		// ��ѯ�������
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfsjwhCk(myForm.getCfid());
		data.putAll(cfData);
		// ��ѯ�Ƿ���Ա��ְ������
		HashMap<String, String> zwAndZzmm = service.getZwAndZzmm(cfData.get("xh"));
		data.put("sfdty", zwAndZzmm.get("sfdty"));
		data.put("zw", zwAndZzmm.get("zwmc"));
		if(null==zwAndZzmm.get("ldmc")||null==zwAndZzmm.get("qsh")){
			data.put("qsbh", "");
		}else{
			data.put("qsbh", zwAndZzmm.get("ldmc") + "-" + zwAndZzmm.get("qsh"));
		}
		
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", "cfjds_12865.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;

	}

	/**
	 * ����ά���Զ��嵼��
	 * 
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
	public ActionForward sjkwhExportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		CfjgForm model = (CfjgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String, String>> resultList = (List<HashMap<String, String>>) service.getCfjgExportList(model, user);
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
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-5 ����09:22:12
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
	public ActionForward initCfwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		CfjgForm model = (CfjgForm) form;
		String cflsh = service.getLsh2(model);
		String cfwh =  MessageUtil.getText(MessageKey.WJCF_CFWH_FORMAT, new String[] {model.getNd(),cflsh});
		response.getWriter().print(getJsonMessage(cfwh));
		return null;
	}
	
	/**
	 * @����:���־��������أ��ں�ְҵ����ѧԺ��ͬ���ͬ�ĺŶ�����¼�ϲ�Ϊ�����ļ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��15�� ����9:45:36
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
	public ActionForward cfjdsDownload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		//��ȡcfids
		String cfidsStr = request.getParameter("cfids");
		String [] cfids = null;
		
		if(StringUtils.isNotNull(cfidsStr)){
			cfids = cfidsStr.split(",");
		}
		
		//��ѯ���ֽ����Ϣ���б����ٴ����ȡ�ļ��ķ���
		List<HashMap<String,String>> cfjgList = service.getCfjgList(cfids);
		//���ݴ������ʹ����ĺŽ��м�¼�ϲ�����
		Map<String,List<HashMap<String,String>>> cfjgListMap = service.getCfjgListMap(cfjgList);
		//����word�ļ�����
		File[] files = service.getCfjdsFiles(cfjgListMap);
		
		if(files.length>1){
			File zipFile = ZipUtil.zip(files);
			FileUtil.outputZip(response, zipFile);
		}else{
			FileUtil.outputWord(response, files[0]);
		}
		
		return null;
	}

	/**
	 * @����:�������������أ��ں�ְҵ����ѧԺ����Ҫȡ���������Ϣ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��15�� ����9:45:36
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
	public ActionForward cfspbDownload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		//��ȡcfids
		String cfidsStr = request.getParameter("cfids");
		String [] cfids = null;
		
		if(StringUtils.isNotNull(cfidsStr)){
			cfids = cfidsStr.split(",");
		}
		
		//��ѯ���ֽ����Ϣ���б����ٴ����ȡ�ļ��ķ���
		List<HashMap<String,String>> cfjgList = service.getCfjgMoreList(cfids);
		//����word�ļ�����
		File[] files = service.getCfspbFiles(cfjgList);
		
		if(files.length>1){
			File zipFile = ZipUtil.zip(files);
			FileUtil.outputZip(response, zipFile);
		}else{
			FileUtil.outputWord(response, files[0]);
		}
		
		return null;
	}
	
	/**
	 * @����: ʯ��ׯ��·ְҵ����ѧԺ��ѧ�����־�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-2 ����10:08:26
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
	public ActionForward getCfjdsDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CfjgForm model = (CfjgForm)form;
		String cfid = model.getCfid();
		File wordFile = getCfsWord(cfid);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: ѧ�����־�����������ӡ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-2 ����10:19:49
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
	public ActionForward getCfjdsDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getCfsWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: ѧ�����־������ӡ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-8-2 ����10:22:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getCfsWord (String cfid) throws Exception {
		
		CfjgService service = new CfjgService();
		/*��ô�����Ϣ*/
		//HashMap<String, String> cfxx = service.cfsjwhCk(cfid);
		HashMap<String, String> cfxx = service.getCfxxByCfid(cfid);
		String xh = cfxx.get("xh");
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		
		/*����ѧ��������Ϣ*/
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		
		/*��ô�ӡ����*/
		String dyrq = xsxxglService.getDqrq(xh);
		data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
	
		/*����ԭ�����ݡ����*/
		data.put("wjssjg", HtmlUtil.xmlZy(cfxx.get("wjssjg")));
		data.put("cfyj", HtmlUtil.xmlZy(cfxx.get("cfyj")));
		data.put("cflbmc", cfxx.get("cflbmc"));
		
		/*����ѧ�Ų�ѯ����Υ�ʹ���*/
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		List<HashMap<String,String>> yscfqkList = wjcfcfsbService.getYscfqkNotId(xh, cfid);
		data.put("yscfqkList", yscfqkList);
		
		/*����ѧ�Ų�ѯ�ѻ���Ϣ*/
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String,String>> pjjgList = pjjgService.getPjpyInfoList(xh);
		data.put("pjjgList", pjjgList);
		
		File file = null;
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//wjcf","cfjds_12424.xml",FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		return file;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡΥ�ʹ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-13 ����05:14:52
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
	public ActionForward getWjcldOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CfjgForm model = (CfjgForm)form;
		/*��ȡurl���������������id*/
		String cfid = request.getParameter("id");
		/*��ȡ�ļ���Ϣ*/
		File wordFile = getWordForWjcld(cfid);
		/*����ļ�*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡΥ�ʹ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-13 ����05:23:09
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
	public ActionForward getWjcldZip (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*��ȡurl��������Value*/
		String value = request.getParameter("value");
		/*�ж�value�Ƿ�Ϊ��*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForWjcld(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: Υ�ʹ����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-13 ����05:45:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForWjcld (String id) throws Exception{
		
		CfjgService service = new CfjgService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*����һ����file*/
		File file = null;
		
		/*������ѡid��ȡѧ��Υ����Ϣ*/
		RcxwjgService rcxwjgService = new RcxwjgService();
		HashMap<String, String> rs = rcxwjgService.getKptzsForId(id);
		data.put("rs", rs);
		
		/*����ѧ��������Ϣ*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(rs.get("xh"));
		data.putAll(xsxxMap);
		
		/*�ָ��ʱ���ꡢ�¡��ա�ʱ*/
		String cfsjYear = rs.get("fssj").substring(0, 4);
		String cfsjMonth = rs.get("fssj").substring(5, 7);
		String cfsjDay = rs.get("fssj").substring(8, 10);
		data.put("cfsjYear", cfsjYear);
		data.put("cfsjMonth", cfsjMonth);
		data.put("cfsjDay", cfsjDay);
		
		/*ȡ����ʱ��ĺ����죬2�·�Ҳ�������*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date nextTwoDay = com.zfsoft.xgxt.base.export.util.DateUtils.addDays(format.parse(rs.get("fssj")), 2);
		String nextTwoDayForString = format.format(nextTwoDay);
		
		/*ȡ��������¡���*/
		String nextMonth = nextTwoDayForString.substring(5, 7);
		String nextDay = nextTwoDayForString.substring(8, 10);
		data.put("nextMonth", nextMonth);
		data.put("nextDay", nextDay);
		
		/*ȡ��ֵ�ľ���ֵ*/
		Float fz = Float.parseFloat(rs.get("fz"));
		data.put("fz", Math.abs(fz));
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf","wjcld_12869.xml", FreeMarkerUtil.getFileName(rs.get("xh")+"-"+xsxxMap.get("xm")));
		return file;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡѧ������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-2 ����07:56:34
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
	public ActionForward getXscfspbOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CfjgForm model = (CfjgForm)form;
		/*��ȡurl�������Ĵ���id*/
		String id = request.getParameter("id");
		/*��ȡ�ļ���Ϣ*/
		File wordFile = getWordForXscfspb(id);
		/*����ļ�*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡѧ������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-2 ����07:57:01
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
	public ActionForward getXscfspbZip (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*��ȡurl��������Value*/
		String value = request.getParameter("value");
		/*�ж�value�Ƿ�Ϊ��*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForXscfspb(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: ѧ�������������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-2 ����06:58:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForXscfspb (String id) throws Exception{
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*����һ����file*/
		File file = null;
		
		CfjgService service = new CfjgService();
		/*��ô�����Ϣ*/
		HashMap<String, String> cfxx = service.getCfxxByCfid(id);
		data.put("cfxx", cfxx);
		
		/*����ѧ��������Ϣ*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(cfxx.get("xh"));
		data.putAll(xsxxMap);
		
		/*ȡѧ�ź�����*/
		String xh = xsxxMap.get("xh");
		String xm = xsxxMap.get("xm");
		/*������������,���磺2017��11��*/
		data.put("csny",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf","xscfspb_12869.xml", FreeMarkerUtil.getFileName(xh+"-"+xm));
		return file;
	}
	
	/**
	 * @����: �Ϻ�Ϸ��ѧԺ��ѧ�����־����飬�����ʹ���
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ� 2018-2-5 ����9:18:03
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
	public ActionForward getCfForShxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CfjgForm model = (CfjgForm)form;
		File wordFile = getCfWord(model.getCfid(),model.getType());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: �Ϻ�Ϸ��ѧԺ������ӡ
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ� 2018-2-5 ����9:20:37
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
	public ActionForward getCfForShxjZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getCfWord(values[i],type);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: ѧ�����־������ӡ
	 * @���ߣ� lgx[���ţ�1553]
	 * @���ڣ�2018-2-5 ����9:23:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getCfWord (String cfid,String type) throws Exception {
		
		CfjgService service = new CfjgService();
		/*��ô�����Ϣ*/
		HashMap<String, String> cfxx = service.getCfxxByCfid(cfid);
		String xh = cfxx.get("xh");
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		
		/*����ѧ��������Ϣ*/
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		
		/*��ô�ӡ����*/
		String dyrq = xsxxglService.getDqrq(xh);
		data.put("dysj", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
		String xq = "";
		if("01".equals(cfxx.get("xq"))){
			xq="��һѧ��";
		}else{
			xq="�ڶ�ѧ��";
		}
		data.put("xq", xq);
		data.put("cfyymc", cfxx.get("cfyymc"));
		data.put("cfyj", HtmlUtil.xmlZy(cfxx.get("cfyj")));
		data.put("cflbmc", cfxx.get("cflbmc"));
		data.put("xn", cfxx.get("xn"));
		data.put("cfwh", cfxx.get("cfwh"));
		data.put("bz", HtmlUtil.xmlZy(cfxx.get("bz")));
		
		File file = null;
		if("cfjds".equals(type)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//wjcf","cfjds_10279.xml",FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		}
		if("cfsds".equals(type)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//wjcf","cfsds_10279.xml",FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		}
		
		
		return file;
	}
}
