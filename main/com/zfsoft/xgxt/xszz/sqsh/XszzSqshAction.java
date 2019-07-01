/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-25 ����02:27:41 
 */
package com.zfsoft.xgxt.xszz.sqsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
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
import com.zfsoft.xgxt.xszz.xmwh.jdsz.XmwhJdszService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszService;
import com.zfsoft.xgxt.xszz.xmwh.shsz.XmwhShszService;
import com.zfsoft.xgxt.xszz.xmwh.tjsz.XmwhTjszService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhForm;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.xszzbjpy.jglr.JglrService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��--��Ŀ�������
 * @���ߣ� Penghui.Qu
 * @ʱ�䣺 2013-4-25 ����02:27:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XszzSqshAction extends SuperAction {
	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xszz.sfbjpy"));
	private static final String ZZXM = "zzxm";
	private static List<HashMap<String, String>> mkxxList = null;
	private static List<HashMap<String, String>> jbxxList = null;
	private static int CYSIZE = 5;// ��ͥ��Ա���֧������

	static {
		BdpzService bdpzService = new BdpzService();
		// ������Ŀ��ʾ����
		mkxxList = bdpzService.getBdpz(ZZXM);
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZZXM);
	}

	private static final String url = "xszz_sqsh_xmsq.do";

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��Ŀ����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����04:48:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward xszzXmsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
//		 XszzSqshService service = new XszzSqshService();

		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// ����ѧ����������
			KnsjgService knsjgService = new KnsjgService();
			List<HashMap<String, String>> knsInfoList = knsjgService
					.getKnsInfoList(myForm.getXh());
			request.setAttribute("knsInfoList", knsInfoList);
		}

		request.setAttribute("mkxxForm", myForm);

		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.getDqxqmc());
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_sqsh.do?method=xszzXmsq";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		this.saveToken(request);
		return mapping.findForward("xszzXmsq");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:������Ŀ����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����09:59:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemLog(description = "����ѧ������-��������-��������-����-XH:{xh},SQLY:{sqly},XMDMARRAY:{xmdmArray}")
	public ActionForward saveXmsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)) {
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		XszzSqshForm myForm = (XszzSqshForm) StringUtils.formatBean(form);
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (SFBJPY_Y && "submit".equals(myForm.getType())) {
			// ����С����Ա�������룡
			JglrService jglrService = new JglrService();
			HashMap<String, String> bjpyxzcyMap = jglrService.queryBjpyxzcy(myForm.getXh());
			if (bjpyxzcyMap.get("xh") != null) {
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		super.resetToken(request);
		XszzSqshService service = new XszzSqshService();

		String[] xmdmArray = request.getParameterValues("xmdmArray");
		//ѧ����������������Ŀ������ѧ��������
		String[] ylzd1 = request.getParameterValues("ylzd1");
		// ������Ŀ����
		boolean result = service.saveXmsq(myForm, xmdmArray, ylzd1);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(myForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:�����¼�ύ
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-4 ����05:19:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemLog(description = "����ѧ������-��������-��������-�ύ-VALUES:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshService service = new XszzSqshService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
		}
		if (SFBJPY_Y) {
			// ����С����Ա�������룡
			JglrService jglrService = new JglrService();
			HashMap<String, String> bjpyxzcyMap = jglrService.queryBjpyxzcy(xh);
			if (bjpyxzcyMap.get("xh") != null) {
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}

		XmwhTjszService tjszService = new XmwhTjszService();
		XszzSqshForm myForm = service.getModel(values);

		// ========== ��֤�������� begin ============
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(myForm.getXmdm(), myForm.getXh());
		// У���������������ز�������������Ŀ���ơ�
		CheckCondition checkCondition = new CheckStudentCondition();
		// �Ƿ�����ȫ����������
		boolean conditionsAllOk = checkCondition.checkConditionBoolean(myForm.getXh(), conditions);

		if (!conditionsAllOk) {
			response.getWriter().print(getJsonMessage("��������������ȷ�ϣ�"));
			return null;
		}
		// ========== ��֤�������� end ============

		boolean result = true;

		if (!SFBJPY_Y) {
			HashMap<String, String> falseXmdm = service.getFalseXmdm(values);
			if (falseXmdm != null && StringUtils.isNotNull(falseXmdm.get("xmdm"))) {
				response.getWriter().print(getJsonMessage(falseXmdm.get("jgsqzq")));
				return null;
			}
			result = service.submitRecord(values, lcid, xh);
		}
		if (result) {
			//����ҵ��״̬Ϊ'�����'
			XszzSqshForm model = new XszzSqshForm();
			model.setGuid(values);
			if (SFBJPY_Y) {
				model.setShzt(Constants.YW_BJPYZ);
			} else {
				model.setShzt(Constants.YW_SHZ);
			}
			//���µ�ǰʱ��
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(dateFormat.format(new Date()));
			//����values ��ѯ jgsqzq��
			String jgsqzq = service.getJgsqzq(values);
			model.setJgsqzq(jgsqzq);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	@SystemLog(description = "����ѧ������-��������-��������-����-VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshService service = new XszzSqshService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		if (!SFBJPY_Y) {
			result = service.cancleRecord(values, lcid);
		}
		if (result) {

			//����guid ��ѯjgsqzq 
			String jgsqzq = service.getJgsqzq(values);

			//����ҵ��״̬Ϊ'δ�ύ'
			XszzSqshForm model = new XszzSqshForm();
			model.setGuid(values);
			model.setShzt(Constants.YW_WTJ);
			model.setJgsqzq(jgsqzq);

			service.updateModel(model);

			if (SFBJPY_Y) {
				XszzSqshForm modelNew = service.getModel(model);
				JgcxDao jgcxDao = new JgcxDao();
				String guid = modelNew.getGuid();
				String xn = modelNew.getXn();
				String xq = modelNew.getXq();
				String xmdm = modelNew.getXmdm();
				String sqr = modelNew.getXh();
				// ���°༶�����
				boolean rs = jgcxDao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				if (rs) {
					// ���½����ѯ��
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(guid);
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:������Ŀѡ��ҳ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-27 ����09:50:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward getXmsqInfo(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshService service = new XszzSqshService();

		// ��������Ŀ��Ϣ
		List<HashMap<String, String>> xmsqInfoList = service.getXmsqInfoList(myForm.getXh());
		request.setAttribute("xmsqInfoList", xmsqInfoList);

		// δ������Ŀ��Ϣ
		List<HashMap<String, String>> wsqList = service.getWsqList(myForm.getXh());
		request.setAttribute("wsqList", wsqList);

		return mapping.findForward("getXmsqInfo");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:����������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����04:49:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do")
	public ActionForward xmsqManage(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);

		String path = "xszz_sqsh_xmsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("xmsqManage");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @����:������Ŀ���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����10:32:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do")
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getShjlList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);

		String path = "xszz_sqsh_xmsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmshManage");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:������Ŀ�������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����11:24:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward zzxmDgsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshService service = new XszzSqshService();

		String xtgwid = myForm.getXtgwid();
		XszzSqshForm model = service.getModel(myForm);
		model.setShid(myForm.getShid());

		if (model != null) {
			// ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			// ����ѧ������������������Num��1529 name:JZ
			if (Base.xxdm.equalsIgnoreCase("11799")) {
				KnsjgService knsjgService = new KnsjgService();
				List<HashMap<String, String>> knsInfoList = knsjgService.getKnsInfoList(model.getXh());
				request.setAttribute("knsInfoList", knsInfoList);
			}


			//��ȡ������Ŀ��Ϣ
			XmwhService xmService = new XmwhService();
			XmwhForm xmwhForm = xmService.getModel(model.getDqxmdm());
			//���ɵ�����Ŀ��ʾ������
			if ("1".equalsIgnoreCase(xmwhForm.getJesfxssq())) {
				model.setJe(model.getYlzd1());
			}
			request.setAttribute("xmwhForm", StringUtils.formatData(xmwhForm));
			//�ж����뽱������������Ƿ���ͬ
			if (model.getXmdm().equals(xmwhForm.getXmdm())) {
				request.setAttribute("isSame", true);
			} else {
				request.setAttribute("isSame", false);
			}
			// ��������б�
			//List<HashMap<String, String>> infoList = service.getSplcInfo(model);
			//request.setAttribute("infoList", infoList);
			BeanUtils.copyProperties(myForm, model);

			// ���ʱ�ɵ�����Ŀ�б�
			XmwhShszService xmwhService = new XmwhShszService();
			List<HashMap<String, String>> tzxmList = xmwhService.getKshxm(model.getDqxmdm());
			request.setAttribute("tzxmList", tzxmList);

			// ͨ��ѧ�Ų�ѯ������Ŀ���
			List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService().getZzxmjgInfoList(model.getXh());
			request.setAttribute("zzxmjgInfoList", zzxmjgInfoList);
		}

		myForm.setXtgwid(xtgwid);
		request.setAttribute("mkxxForm", myForm);

		// �Ű�����
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("zzxmDgsh");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��֤������Ŀ����Ƿ�����޸�
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-5 ����02:09:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward jeSfkt(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		XmwhService xmwhService = new XmwhService();
		XmwhForm model = xmwhService.getModel(myForm.getXmdm());
		response.getWriter().print(JSONObject.fromObject(model));
		return null;
	}


	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:������Ŀ���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����02:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-�������-����-GUID��{guid}")
	public ActionForward saveXmsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) StringUtils.formatBean(form);
		XszzSqshService service = new XszzSqshService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		// ������Ŀ���
		boolean isSuccess = service.saveXmsh(myForm, user);
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL_XSZZ;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:���һ���������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����02:09:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward cancelXmsh(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		// �������
		boolean isSuccess = service.cancelXmsh(myForm);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:ɾ����Ŀ����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����03:41:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemLog(description = "����ѧ������-��������-��������-ɾ��-VALUES��{values}")
	public ActionForward delXmsq(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshService service = new XszzSqshService();
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// ɾ��
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
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
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:���̸���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����03:42:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@Deprecated
	public ActionForward getSplcInfo(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		// ��ȡ���������Ϣ
		List<HashMap<String, String>> infoList = service.getSplcInfo(myForm);

		request.setAttribute("infoList", infoList);
		return mapping.findForward("xmsqLcgz");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:������Ŀ�������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����05:07:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-�������-�������-ID:{id}")
	public ActionForward zzxmPlsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;

		if (SAVE.equals(myForm.getType())) {
			XszzSqshService service = new XszzSqshService();

			User user = getUser(request);
			// �������
			String message = service.savePlsh(myForm, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("xxdm", Base.xxdm);

		return mapping.findForward("zzxmPlsh");

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:�޸���Ŀ����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����06:54:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward updateXmsq(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshService service = new XszzSqshService();

		XszzSqshForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// ����ѧ����������
			KnsjgService knsjgService = new KnsjgService();
			List<HashMap<String, String>> knsInfoList = knsjgService
					.getKnsInfoList(myForm.getXh());
			request.setAttribute("knsInfoList", knsInfoList);
		}
		//��ȡ������Ŀ��Ϣ
		XmwhService xmService = new XmwhService();
		XmwhForm xmwhForm = xmService.getModel(model.getXmdm());
		xmwhForm.setPdxqmc(Base.getXqmcForXqdm(xmwhForm.getPdxq()));

		boolean isopen = false;
		//��Ŀʱ�俪���ж�
		if ("1".equals(xmwhForm.getSqkg())) {

			//ȥ��ʱ��������ʼʱ��Ϊ�ճ�ʼΪ��
			String kssj = null == xmwhForm.getSqkssj() ? "0000-00-00" : xmwhForm.getSqkssj();
			String jssj = null == xmwhForm.getSqjssj() ? "9999-99-99" : xmwhForm.getSqjssj();
			int ks = Integer.parseInt(kssj.replace("-", ""));
			int js = Integer.parseInt(jssj.replace("-", ""));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			int dq = Integer.parseInt(sdf.format(new Date()));
			if (dq >= ks && dq <= js) {
				isopen = true;
			}
		}

		request.setAttribute("isopen", isopen);
		request.setAttribute("xmwhForm", StringUtils.formatData(xmwhForm));
		request.setAttribute("type", UPDATE);
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateXmsq");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:���������޸�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����06:55:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemLog(description = "����ѧ������-��������-��������-����GUID:{guid},XH:{xh},SQLY:{sqly}")
	public ActionForward saveSqxg(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XmwhTjszService tjszService = new XmwhTjszService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		if ("submit".equals(myForm.getType())) {
			if (SFBJPY_Y) {
				// ����С����Ա�������룡
				JglrService jglrService = new JglrService();
				HashMap<String, String> bjpyxzcyMap = jglrService.queryBjpyxzcy(myForm.getXh());
				if (bjpyxzcyMap.get("xh") != null) {
					response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
					return null;
				}
			}

			// ========== ��֤�������� begin ============
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(myForm.getXmdm(), myForm.getXh());
			// У���������������ز�������������Ŀ���ơ�
			CheckCondition checkCondition = new CheckStudentCondition();
			// �Ƿ�����ȫ����������
			boolean conditionsAllOk = checkCondition.checkConditionBoolean(myForm.getXh(), conditions);

			if (!conditionsAllOk) {
				response.getWriter().print(getJsonMessage("��������������ȷ�ϣ�"));
				return null;
			}
			// ========== ��֤�������� end ============
		}
		XszzSqshService service = new XszzSqshService();
		// ִ���޸Ĳ���
		boolean result = service.bcxgXmsq(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(myForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:�鿴������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-27 ����02:09:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward viewXmsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		XszzSqshForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// ����ѧ����������
			KnsjgService knsjgService = new KnsjgService();
			List<HashMap<String, String>> knsInfoList = knsjgService
					.getKnsInfoList(myForm.getXh());
			request.setAttribute("knsInfoList", knsInfoList);

			//List<HashMap<String, String>> infoList = service
			//		.getSplcInfo(myForm);
			//request.setAttribute("infoList", infoList);
		}
		if (Base.xxdm.equalsIgnoreCase("10718")) {
			KnsjgService knsjgservice = new KnsjgService();
			HashMap<String, String> knsrd = knsjgservice.getKnspd(model.getXh(), model.getXn());
			request.setAttribute("knsrd", knsrd);
			request.setAttribute("usertype", getUser(request).getUserType());
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("viewXmsq");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����: �������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-2 ����01:39:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward checkCondition(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XmwhTjszService tjszService = new XmwhTjszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(myForm.getXmdm(), myForm.getXh());
		// У�����������ز�������������Ŀ���ơ�
		CheckCondition check = new CheckStudentCondition();
		List<HashMap<String, String>> faileds = check.checkCondition(myForm
				.getXh(), conditions);

		JSONArray jsonArray = JSONArray.fromObject(faileds);
		response.getWriter().print(jsonArray);

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����: ѧ������������ͳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-23 ����10:56:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward zzxmShqk(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		XmwhRsszService xmwhrsszservice = new XmwhRsszService();
		request.setAttribute("rsfpfs", xmwhrsszservice.getRsfpfs(myForm.getXmdm()));

		if (StringUtils.isNotNull(myForm.getXmdm())) {
			Map<String, Object> shqkInfo = service.getShqkInfo(user, myForm.getXmdm());

			request.setAttribute("shqkInfo", shqkInfo);
		}
		return mapping.findForward("zzxmShqk");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����: ����������Ŀ���ͳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-23 ����05:11:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward zzxmShtj(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhService xmwhService = new XmwhService();
		// xmwhService.get
		List<HashMap<String, String>> zzxmList = xmwhService.getOthers(" ");

		request.setAttribute("zzxmList", zzxmList);
		return mapping.findForward("zzxmShtj");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��ӡ������������Ϣ
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-3
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshForm model = service.getModel(myForm);
		myForm.setXn(model.getXn());
		myForm.setXq(model.getXq());
		// ������Ŀ���ƻ�ȡ������Ϣ,�Լ���Ŀ��Ϣ
		if (!StringUtil.isNull(myForm.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			String xsxmdm = xmwhService.getXszzdm(myForm.getXmmc(), myForm.getXn(), myForm.getXq());
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
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}

		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("xmxx", model);// ����ѧ����Ŀ��Ϣ
		return new ActionForward(forward, false);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-22 ����10:44:47
	 * @�޸ļ�¼:
	 */

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getSqExportList(myForm, user);

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����: ��˵���
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-10-16 ����02:32:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url)
	public ActionForward exportDataSh(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getShExportList(myForm, user);

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}


	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����: ������ͳ�Ʋ�ѯ����ѧ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-4 ����10:17:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward getStudentsByShqk(ActionMapping mapping, ActionForm form,
										   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm model = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		if (QUERY.equalsIgnoreCase(model.getType())) {
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//��ѯ
			model.setShid(request.getParameter("xtgwid"));
			List<HashMap<String, String>> resultList = service.getStudentsList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xszz_sqsh_shtj.do");
		return mapping.findForward("studentsList");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��ӡword
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-31 ����11:32:52
	 * @�޸ļ�¼:
	 */

	public ActionForward downloadWord(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		String guid = myForm.getGuid();
		File wordFile = getWord(guid);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:����ZIP
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-31 ����11:32:26
	 * @�޸ļ�¼:
	 */

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
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

	// ���ģ����������word�ļ�
	private File getWord(String guid) throws Exception {
		XszzSqshService service = new XszzSqshService();
		ZzxmjgService zzxmjgService = new ZzxmjgService();
		XszzSqshForm myForm = new XszzSqshForm();
		myForm.setGuid(guid);
		XszzSqshForm model = service.getModel(myForm);//
		HashMap<String, String> bbMap = null;// ����

		// ������Ŀ��ȡ������Ϣ
		XmwhService xmwhService = new XmwhService();
		XmwhForm xmwhModel = xmwhService.getModel(model.getXmdm());
		BbwhService bbwhService = new BbwhService();
		bbMap = bbwhService.getDataById(xmwhModel.getDybb());
		model.setXmmc(xmwhModel.getXmmc());

		// ��ѯ���������������Ϣ
		if (bbMap == null || bbMap.isEmpty()) {
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}

		String xh = model.getXh();

		if (!StringUtil.isNull(xh)) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//ת��Ϊ�������ڸ�ʽ
			xsjbxx.put("rxrq", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"), FomartDateType.month));
			// �ֽ����֤��begin
			String xssfzh = StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
			for (int i = 0, j = xssfzh.length(); i < j; i++) {
				xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
			}

			//ѧ����Ƭ
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);

			// ����ѧ����ͥ������Ϣ
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkmodel = jtqkService.getModel(xh);

			// ��ѧ�ż��س�Ա�б�
			JtqkdcService jtqkdcService = new JtqkdcService();
			List<HashMap<String, String>> cyList = jtqkdcService
					.getJtcyList(model.getXh());
			if (cyList != null && cyList.size() > 0) {
				for (HashMap<String, String> cyMap : cyList) {
					cyMap.put("cyxm", HtmlUtil.xmlZy(cyMap.get("cyxm")));
					cyMap.put("cygxmc", HtmlUtil.xmlZy(cyMap.get("cygxmc")));
					cyMap.put("cyxxdw", HtmlUtil.xmlZy(cyMap.get("cyxxdw")));
				}
			}
			List<HashMap<String, String>> cyList4line = jtqkdcService.getJtcyList(xh, 4); //ȡ4����ͥ��Ա�б�����4�����
			List<HashMap<String, String>> cyList5line = jtqkdcService.getJtcyList(xh, 5); //ȡ4����ͥ��Ա�б�����4�����
			// ����ѧ�������϶����
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(model
					.getXh(), model.getXn(), model.getXq());
			KnsdcService knsdcService = new KnsdcService();
			Map<String, Object> data = new HashMap<String, Object>();
			
			
			//����ʦ��ѧԺ���Ի�
			if ("10402".equals(Base.xxdm)) {
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList_10402 = xsxxglService.getHjqkNewList(xh);

				data.put("hjqkList_10402", hjqkList_10402);
				int hjqkSize = (4 - hjqkList_10402.size()) < 0 ? 0 : (4 - hjqkList_10402.size());
				data.put("hjqkBlankList_10402", jtqkdcService.getBlankList(hjqkSize));
			}
			//���ص�ǰѧ��ѧ�ڵ�������
			ZcfsService zcfsServcie = new ZcfsService();
			List<HashMap<String, String>> zyfsInfo = zcfsServcie.getZyfListByXh(model.getXh(), model.getPdxn(), model.getPdxq());
			if (zyfsInfo != null && zyfsInfo.size() > 0) {
				data.put("zyf", zyfsInfo.get(0).get("fs"));
			}
			//������Ŀ����
			data.put("xmmc", model.getXmmc());

			String theSameXmmcNum = service.getTheSameZzxmNumber(bbMap.get("bbmc"), xh);  //��ǰ������ͬ��Ŀ������
			//����������׼ͨ������ͬ��Ŀ������
			data.put("theSameXmmcNum", theSameXmmcNum);

			List<HashMap<String, String>> ywqtjxj = service.getYwqtjxList(bbMap.get("bbmc"), xh);
			//��ѧ�ڼ�ͬʱ����������(��)ѧ��
			data.put("ywqtjxj", ywqtjxj == null ? new ArrayList<HashMap<String, String>>() : ywqtjxj);

			//�������������Ϣ
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh, 4);  //��ȡ����4���������
			String xmmcs = pjjgService.getXmmcAllByPjjg(xh);

			//����������Ϣ
			List<HashMap<String, String>> zzjg = zzxmjgService.getZzxmjgInfoList(xh);

			data.put("pjjg", pjjg);
			data.put("pjjgList4line", pjjgList4line);
			data.put("xmmcs", xmmcs);
			data.put("zzjgList", zzjg);
			// ��ͥ��Ա�б����
			data.put("pjjgblankList", CYSIZE > pjjg.size() ? jtqkdcService
					.getBlankList(CYSIZE - pjjg.size()) : jtqkdcService
					.getBlankList(0));
			data.put("sqzzje", model.getYlzd1()); // �����������
			data.put("zzjg", model);
			data.putAll(xsjbxx);// ѧ��������Ϣ
			data.put("jtqk", jtqkmodel == null ? new JtqkdcForm() : jtqkmodel);// ��ͥ���
			if (jtqkmodel != null && jtqkmodel.getJtnzsr() != null) {
				data.put("jtnsr",jtqkmodel.getJtnzsr());//��ͥ������
				data.put("jtyzsr", (Double.parseDouble(jtqkmodel.getJtnzsr()) / 12));//��ͥ��������
				data.put("jtrjysr", jtqkmodel.getJtrs() == null ? "" : (Double.parseDouble(jtqkmodel.getJtnzsr()) / 12 / Integer.parseInt(jtqkmodel.getJtrs())));//��ͥ�˾�������
			} else {
				data.put("jtyzsr", "");
				data.put("jtrjysr", "");
			}
			
			/*��ͥ���--��ͥ�˾�������(ͨ��)*/
			if (jtqkmodel != null) {
				String jtrjsr = jtqkmodel.getJtrjsr();
				if (jtrjsr != null) {
					data.put("jtrjsr", jtrjsr);
				} else {
					data.put("jtrjsr", "");
				}
			} else {
				data.put("jtrjsr", "");
			}

			data.put("cyList", cyList);// ��ͥ��Ա�б�
			data.put("cyList4line", cyList4line);
			data.put("cyList5line", cyList5line);
			// ��ͥ��Ա�б����
			data.put("blankList", CYSIZE > cyList.size() ? jtqkdcService
					.getBlankList(CYSIZE - cyList.size()) : jtqkdcService
					.getBlankList(0));
			//Σ���������ݲ�ѯ
			WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
			List<HashMap<String, String>> wjList = wjcfcfsbService.getYscfqk(xh);
			data.put("wjList", wjList);

			//ɽ���ƾ���ѧ���Ի�
			if ("10125".equals(Base.xxdm)) {
				List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map = null;
				int pjjgsize = 4;
				if (pjjg.size() > pjjgsize) {
					for (int i = 0; i < pjjgsize; i++) {
						map = new HashMap<String, String>();
						map.put("sqsjs", pjjg.get(i).get("sqsjs"));
						map.put("xmmc", pjjg.get(i).get("xmmc"));
						map.put("bjdw", pjjg.get(i).get("bjdw"));
						pjjgList.add(map);
					}
					data.put("pjjgblankList", pjjgList);
				} else {
					data.put("pjjgblankList", pjjg);
				}
				int size2 = (4 - pjjg.size()) <= 0 ? 0 : (4 - pjjg.size());
				data.put("blankList2", jtqkdcService.getBlankList(size2));
				data.put("cyList", cyList);
				int size1 = (4 - cyList.size()) <= 0 ? 0 : (4 - cyList.size());
				data.put("blankList1", jtqkdcService.getBlankList(size1));
			}
			//�㽭����ѧԺ ���Ի�
			if ("12869".equals(Base.xxdm)) {
				List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map = null;
				int pjjgsize = 4;
				if (pjjg.size() > pjjgsize) {
					for (int i = 0; i < pjjgsize; i++) {
						map = new HashMap<String, String>();
						map.put("sqsjs", pjjg.get(i).get("sqsjs"));
						map.put("xmmc", pjjg.get(i).get("xmmc"));
						map.put("bjdw", pjjg.get(i).get("bjdw"));
						pjjgList.add(map);
					}
					data.put("pjjgblankList", pjjgList);
				} else {
					data.put("pjjgblankList", pjjg);
				}
				int size2 = (4 - pjjg.size()) <= 0 ? 0 : (4 - pjjg.size());
				data.put("blankList2", jtqkdcService.getBlankList(size2));
				data.put("cyList", cyList);
				int size1 = (4 - cyList.size()) <= 0 ? 0 : (4 - cyList.size());
				data.put("blankList1", jtqkdcService.getBlankList(size1));
				HashMap<String, String> sfkns = zzxmjgService.getSfkns(xh, model.getXn());
				data.put("jgxyrddc", sfkns.get("dcmc"));
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
			}
			//�ӱ�����
			if ("12389".equals(Base.xxdm)) {
				data.put("xn",model.getXn());
				int bxks = zcfsServcie.getCjListByXhXn(xh,model.getXn(),"����").size();
				String yxkcs = service.getYxksByXh_12389(model.getXh(),model.getXn(),"����");//����γ���
				String lkcs = service.getLkcsByXh_12389(model.getXh(),model.getXn(),"����");//���γ���
				String cjpm = service.getCjpmXy(model.getXn(), xh, xsjbxx.get("zydm"));//�ɼ�רҵ����
				String zyzrs = service.getZyzrs(xsjbxx.get("zydm"));//רҵ������
				HashMap<String, String> zcf = zcfsServcie.getZczfByXh(xh, model.getXn(), model.getXq());
				String bjrs = zcfsServcie.getBjrs(xh);
				int sfzcf = 1;
				if(StringUtils.isNull( zcf.get("fs"))){
					sfzcf = 0;
				}
				data.put("sfzcf", sfzcf);
				data.put("zcfs", zcf.get("fs"));
				data.put("zcbjpm", zcf.get("bjpm"));
				data.put("bxks",bxks);
				data.put("yxkcs",yxkcs);
				data.put("lkcs",lkcs);
				data.put("cjpm",cjpm);
				data.put("bjrs", bjrs);
				data.put("zyzrs", zyzrs);

			}
			//������ũ��ְҵѧԺ���Ի�
			if ("12727".equals(Base.xxdm)) {
				// ======== ������ֽ��� begin============
				StringBuffer chhzjlBuffer = new StringBuffer();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if (StringUtils.isNotNull(xmmc)) {
						chhzjlBuffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(xmmc).append("��");
					}
				}
				String chhzjlmc = chhzjlBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("chhzjlmc", chhzjlmc);
				// ======== ������ֽ��� end============
				// ��ͥ���
				JtqkdcForm jtqkdcForm = new JtqkdcForm();
				jtqkdcForm.setXh(xh);
				JtqkdcForm jtqkmodel_12727 = jtqkService.getModel(jtqkdcForm);
				if (jtqkmodel_12727 == null) {
					jtqkmodel_12727 = new JtqkdcForm();
				}
				data.put("jtqkmodel_12727", jtqkmodel_12727);
				String jthk = jtqkmodel.getJthk();
				boolean jthkCzFlag = false;
				if (jthk != null && jthk.contains("����")) {
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// ��ͥ��Ա
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				pjjgService.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
				// ========= ѧϰ�ɼ� begin ============
				ZcfsService zcfService = new ZcfsService();
				List<HashMap<String, String>> cjXnxqList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "");
				StringBuffer bxnxxcjBuffer = new StringBuffer();
				for (int i = 0; i < cjXnxqList.size(); i++) {
					HashMap<String, String> cjXnxqMap = cjXnxqList.get(i);
					bxnxxcjBuffer.append(cjXnxqMap.get("kcmc")).append("��").append(cjXnxqMap.get("cj")).append("��");
				}
				String bxnxxcjmc = bxnxxcjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("bxnxxcjmc", bxnxxcjmc);

				List<HashMap<String, String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "");
				String bxn_cjSxqPjf = pjjgService.getPjf(bxn_cjSxqBxList, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("bxn_cjSxqPjf", bxn_cjSxqPjf);
				List<HashMap<String, String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				String bxn_cjXxqPjf = pjjgService.getPjf(bxn_cjXxqBxList, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("bxn_cjXxqPjf", bxn_cjXxqPjf);

				String bxn_bjgcjsSxq = pjjgService.getBjgcjNum(xh, model.getXn(), "01"); //��ѧ����ѧ�ڲ�����ɼ�����
				data.put("bxn_bjgcjsSxq", "".equals(bxn_bjgcjsSxq) ? "0" : bxn_bjgcjsSxq);
				String bxn_bjgcjsXxq = pjjgService.getBjgcjNum(xh, model.getXn(), "02"); //��ѧ����ѧ�ڲ�����ɼ�����
				data.put("bxn_bjgcjsXxq", "".equals(bxn_bjgcjsXxq) ? "0" : bxn_bjgcjsXxq);
				// ========= ѧϰ�ɼ� end ============
			}
			//����ְҵѧԺ���Ի�
			if("11773".equals(Base.xxdm)){
				// ======== ������ֽ��� begin============
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
			
			//����ʦ�����Ի�
			if("10346".equals(Base.xxdm)){
				ZcfsService zcfService = new ZcfsService();
				String bjrs = zcfService.getBjrs(xh);
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
				List<HashMap<String, String>> zzdwlist = zzxmjgService.getZzxmjgInfoList(xh);
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
			
			data.put("knsdcList", knsdcService.getKnsdcList());// ����������list
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//�϶�����
			data.put("rddcmc", knsjg.get("dcmc") == null ? "" : knsjg.get("dcmc"));//�϶���������
			data.put("xxmc", Base.xxmc);// ѧУ����
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
			data.put("currXnNow", Base.currXn);// ��ǰѧ��
			data.put("currXn", model.getPdxn());// ��ǰ��Ŀ����ѧ��
			//data.put("xn", HtmlUtil.xmlZy(model.getSqly()));// ��������
			//ת��Ϊ�������ڸ�ʽ
			data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month));// ��������
			data.put("photo", photo);// ѧ����Ƭ

			String jtsfyqk = "";

			if (jtqkmodel != null
					&& jtqkmodel.getJtqzqk() != null
					&& jtqkmodel.getJtqzqk().indexOf("��") == -1
					&& jtqkmodel.getJtqzqk().indexOf("û��") == -1) {
				jtsfyqk = "��";
			} else {
				jtsfyqk = "��";
			}
			data.put("jtsfyqk", jtsfyqk);// ��ͥ�Ƿ���Ƿ��
			//ѧ������
			String xsnl = " ";
			try {
				String csn = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.year).replaceAll("��", "");
				Calendar cal = Calendar.getInstance();
				int currn = cal.get(Calendar.YEAR); //��ǰ��
				xsnl = String.valueOf(currn - Integer.valueOf(csn));
			} catch (Exception e) {
				//e.printStackTrace();
			}
			data.put("xsnl", xsnl);// ѧ������

			//����ʯ��
			if ("10220".equals(Base.xxdm)) {
				String zwmc = zzxmjgService.getZwForXh(xh);
				data.put("zwmc", zwmc);
				if (zwmc == "") {
					data.put("sfyzw", "0");
				}
				String csrq = xsjbxx.get("csrq");
				data.put("csrq", csrq);
				if (jtqkmodel != null) {
					data.put("sfpkx", jtqkmodel.getSfpkx());// �Ƿ�ƶ����
					data.put("pkxjb", jtqkmodel.getPkxjb());// ƶ���ؼ���
					data.put("fmjk", jtqkmodel.getFmjk());// ��ĸ�Ƿ��в���м�
					data.put("fmjz", jtqkmodel.getFmjz());// ��ĸ�������
				} else {
					data.put("sfpkx", "");// �Ƿ�ƶ����
					data.put("pkxjb", "");// ƶ���ؼ���
					data.put("fmjk", "");// ��ĸ�Ƿ��в���м�
					data.put("fmjz", "");// ��ĸ�������
				}
				HashMap<String, String> fqxxInfo = jtqkdcService.getfqInfo(xh);
				HashMap<String, String> mqxxInfo = jtqkdcService.getmqInfo(xh);
				data.put("fqzy", fqxxInfo.get("cyzy"));
				data.put("mqzy", mqxxInfo.get("cyzy"));
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}

			//������Ͽ�ߵ�ҽҩר��ѧУ
			if ("14008".equals(Base.xxdm)) {
				YlbxjgService ylbxjgService = new YlbxjgService();
				HashMap<String, String> jfdcInfo = ylbxjgService.getJfdcRylbInfo(xh);
				data.put("yljfdc", jfdcInfo.get("dcmc")); // ҽ�ƽɷѵ���	
				data.put("rylb", jfdcInfo.get("rylb")); // ��Ա���
			}

			//�Ϻ�Ϸ��
			if ("10279".equals(Base.xxdm)) {
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month));// ��������
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxrq = xsjbxx.get("rxrq") == null ? "" : xsjbxx.get("rxrq");
				data.put("csny_n", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				data.put("rxny_n", rxrq.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}

			//�ӱ���ҵ��ѧ
			if("10080".equals(Base.xxdm)){
				List<HashMap<String,String>> zzxmList = zzxmjgService.getZzxmInfoByXhXn(model.getXh(),model.getXn(),model.getXq());//��ѧ�������ܵ�������Ŀ
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
			data.put("lxdh",xsjbxx.get("sjhm"));
			//���ݴ�ѧ
			if ("10351".equals(Base.xxdm)) {
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList = xsxxglService.getHjqkNewFourList(xh);
				data.put("pjjg", hjqkList);
				int hjqkSize = (4 - hjqkList.size()) < 0 ? 0 : (4 - hjqkList.size());
				data.put("pjjgblankList", jtqkdcService.getBlankList(hjqkSize));
				String csny = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month);
				String rxrq = xsjbxx.get("rxrq");
				data.put("csny", csny);
				data.put("rxrq", rxrq);
				String xz = "";
				if (StringUtils.isNotNull(xsjbxx.get("xz"))) {
					xz = DateUtils.numToZh(xsjbxx.get("xz")) + "��";
				}
				data.put("xz", xz);
				HashMap<String, String> bxk = pjjgService.getBxk(model.getXh(), model.getPdxn());
				HashMap<String, String> pm = pjjgService.getCjPm(model.getXh(), model.getPdxn());
				data.put("bxk", bxk);
				data.put("pm", pm);

			}

			if ("12867".equals(Base.xxdm)) {
				ZzxmjgService zzService = new ZzxmjgService();
				XsxxtyglService service1 = new XsxxtyglService();
				XsxxglService xsxxglService = new XsxxglService();
				List cyList1 = xsxxglService.getJtcyxxXsList(xh);
				data.put("jtcyxxList", cyList1);
				int size1 = 5 - cyList1.size() <= 0 ? 0 : 5 - cyList1.size();
				data.put("cyblankList", service1.getBlankList(size1));

				Map jtqkmap = jtqkdcService.getJtqkInfo(xh);
				data.put("jtqkmap", jtqkmap);

				List zzxx = zzService.getZzxmjgInfoList(xh);
				HashMap zxjxxmap = new HashMap();
				for (int i = 0; i < zzxx.size(); i++) {
					if (((String) ((HashMap) zzxx.get(i)).get("xmmc")).equals(model.getXmmc())) {
						zxjxxmap = (HashMap) zzxx.get(i);
					}
				}
				data.put("zxjxxmap", zxjxxmap);
				HashMap<String, String> axjjmap = zzService.getZjlyAxjjMap(xh, model.getXn());
				data.put("axjjmap", axjjmap);
				if (model.getXmmc().indexOf("���Ļ���") != -1) {
					//���Ļ���
					HashMap<String, String> axjjmap1 = zzService.getZjlyAxjjMap(xh, model.getXn());
					data.put("axjjmap", axjjmap1);
				} else if (model.getXmmc().indexOf("�¶����Ĳ���") != -1) {
					//�¶����Ĳ���
					HashMap<String, String> geaxmap = zzService.getZjlyGeaxMap(xh, model.getXn());
					data.put("geaxmap", geaxmap);
				} else if (model.getXmmc().indexOf("У�����ü���") != -1) {
					//����У�����ü���
					HashMap<String, String> xfjmmap = zzService.getZjlyXfjmMap(xh, model.getXn());
					data.put("xfjmmap", xfjmmap);
				} else if (model.getXmmc().indexOf("������Ʒ���ü���") != -1) {
					//������Ʒ���ü���
					HashMap<String, String> shfjmmap = zzService.getZjlyShfjmMap(xh, model.getXn());
					data.put("shfjmmap", shfjmmap);
				} else if (model.getXmmc().indexOf("��ҵ���ղ���") != -1) {
					//������Ʒ���ü���
					HashMap<String, String> sybxbzmmap = zzService.getZjlySybxbzMap(xh, model.getXn());
					data.put("sybxbzmmap", sybxbzmmap);
				} else if (model.getXmmc().indexOf("��ͨ����") != -1) {
					//��ͨ����
					HashMap<String, String> jtbzmmap = zzService.getZjlyJtbzMap(xh, model.getXn());
					data.put("jtbzmmap", jtbzmmap);
				} else if (model.getXmmc().indexOf("���ܿ�֤") != -1) {
					//���ܿ�֤�Ѳ���
					HashMap<String, String> jnkzfbzmap = zzService.getZjlyJnkzfbzmapMap(xh, model.getXn());
					data.put("jnkzfbzmap", jnkzfbzmap);
				} else if (model.getXmmc().indexOf("ѧ�Ѽ���") != -1) {
					//ѧ�Ѽ���
					HashMap<String, String> xfjmmap = zzService.getZjlyXfjmmapMap(xh, model.getXn());
					data.put("xfjmmap", xfjmmap);
				}
			}
			//����ʦ����ѧ ���Ի�
			if (StringUtils.isEqual(Base.xxdm, "10602")) {
				//ѧ����ͥ��Ա���
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				data.put("dqxmmc", model.getXmmc());
				data.put("photo", photo);
				data.put("jtcyxxList", jtcyxxList);
				int size1 = (3 - jtcyxxList.size()) <= 0 ? 0 : (3 - jtcyxxList.size());
				data.put("cyblankList", jtqkdcService.getBlankList(size1));
				//�������������Ϣ
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjglist = pjjgservice.getPjpyInfoList(xh);
				data.put("pjjg1", pjjglist);
				//����������Ϣ
				List<HashMap<String, String>> zzjglist = zzxmjgService.getZzxmjgInfoList(xh);
				data.put("zzjglist", zzjglist);
			}
			//���������ѧ
			if ("10742".equals(Base.xxdm)) {
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

			//���������ѧ
			if ("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(xh);
				String qsbh = null;
				if (null == qsxx.get("ldmc") || null == qsxx.get("qsh")) {
					qsbh = "";
				} else {
					qsbh = qsxx.get("ldmc") + "-" + qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month));// ��������
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.day));// ��������
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
				List<HashMap<String, String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "����");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList);
				String xmXnxqBxCjPjf = servicePjPy.getPjf(xmXnxqBxCjList, 2); // ƽ���ɼ�
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				// ���Ž���
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (StringUtils.isNotNull(zzmmmc)) {
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if ((zzmmmc.contains("��Ա") && "02".equals(jddm))
								|| (zzmmmc.contains("Ԥ����Ա") && "09".equals(jddm))
								|| (zzmmmc.contains("��Ա") && !zzmmmc.contains("Ԥ����Ա") && "11".equals(jddm))) {
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj, FomartDateType.day));
				// �ѻ���
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjg1 = servicePjPy.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg1.size(); i++) {
					HashMap<String, String> pj = pjjg1.get(i);
					String xmmc = pj.get("xmmc");
					if (StringUtils.isNotNull(xmmc)) {
						yhjxBuffer.append(xmmc).append("��");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if (yhjxStr.length() > 1) {
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
			}

			//������·ְҵ����ѧԺ���Ի�
			if ("13943".equals(Base.xxdm)) {
				String sqly_13943 = HtmlUtil.xmlZy(model.getSqly());
				String sqly_13943_1 = sqly_13943;
				String sqly_13943_2 = "";
				if (sqly_13943.length() > 363) {
					sqly_13943_1 = sqly_13943.substring(0, 363);
					sqly_13943_2 = sqly_13943.substring(363);
				}
				data.put("sqly_13943_1", sqly_13943_1);// ��������(��һ����)
				data.put("sqly_13943_2", sqly_13943_2);// ��������(�ڶ�����)
				String xymc_13943 = xsjbxx.get("xymc");
				if (xymc_13943.length() < 39) {
					int max = 39 - xymc_13943.length();
					for (int i = 0; i < max; i++) {
						xymc_13943 += " ";
					}
				}
				data.put("xymc_13943", xymc_13943);
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(xh);
				String qsbh = null;
				if (null == qsxx.get("ldmc") || null == qsxx.get("qsh")) {
					qsbh = "";
				} else {
					qsbh = qsxx.get("ldmc") + "-" + qsxx.get("qsh");
				}
				data.put("qsbh_13943", qsbh);
				// ����ְ��
				DwwhService dwwhService = new DwwhService();
				data.put("zwmc", dwwhService.getZwForXh(xh));
				ZcfsService zcfService = new ZcfsService();
				// ��ѧ����ѧ�ڳɼ�
				PjjgService servicePjPy = new PjjgService();
				List<HashMap<String, String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "");
				servicePjPy.addBlankList(bxn_cjSxqBxList, 12 - bxn_cjSxqBxList.size());
				data.put("bxn_cjSxqBxList", bxn_cjSxqBxList);
				HashMap<String, String> bxn_zcfSxq = zcfService.getZczfByXh(xh, model.getXn(), "01");
				data.put("bxn_zcfSxq", bxn_zcfSxq);
				// ��ѧ����ѧ�ڳɼ�
				List<HashMap<String, String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				servicePjPy.addBlankList(bxn_cjXxqBxList, 12 - bxn_cjXxqBxList.size());
				data.put("bxn_cjXxqBxList", bxn_cjXxqBxList);
				HashMap<String, String> bxn_zcfXxq = zcfService.getZczfByXh(xh, model.getXn(), "02");
				data.put("bxn_zcfXxq", bxn_zcfXxq);
				// ����ѧ��
				String pdxq = model.getPdxq();
				if (StringUtils.isNull(pdxq)) {
					pdxq = CsszService.XQKG;
				}
				// ��Ŀѧ��ѧ�ڳɼ�
				HashMap<String, String> xmZcf = zcfService.getZczfByXh(xh, model.getPdxn(), pdxq);
				data.put("xmZcf", xmZcf);
				// �༶����
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getPdxn(), pdxq);
				data.put("bjbjrs", bjrs);
				int yjNum = 0; // Ժ��
				int xjNum = 0; // У��
				int xjysNum = 0; // У������
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pjjgMap = pjjg.get(i);
					String xmxzmc = pjjgMap.get("xmxzmc");
					if (StringUtils.isNotNull(xmxzmc)) {
						if (xmxzmc.contains("Ժ��")) {
							yjNum++;
						} else if (xmxzmc.contains("У������")) {
							xjysNum++;
						} else if (xmxzmc.contains("У��")) {
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
			if ("10718".equals(Base.xxdm)) {
				data.put("rxrq", (String) xsjbxx.get("rxrq").subSequence(0, 4) + xsjbxx.get("rxrq").subSequence(5, 7));
				data.put("csny", (String) xsjbxx.get("csrq").subSequence(0, 4) + xsjbxx.get("csrq").subSequence(5, 7));
				PjjgModel form = new PjjgModel();
				form.setXn(model.getXn());
				form.setXh(model.getXh());
				form.setCpbjdm(xsjbxx.get("bjdm"));
				form.setCpzydm(xsjbxx.get("zydm"));
				data.put("xn1", model.getXn().trim().substring(0, 4));
				data.put("xn2", model.getXn().trim().substring(5, 9));
				String sqsj = model.getSqsj();
				data.put("year", sqsj.trim().substring(0, 4));
				data.put("mon", sqsj.trim().substring(5, 7));
				List<HashMap<String, String>> qgzxlist = zzxmjgService.getQgzxList(model.getXh());
				data.put("qgzxlist", qgzxlist);
				data.put("mdjlist", zzxmjgService.getMjxList(xh, "���½�ѧ��"));
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = format.format(new java.util.Date());
				data.put("y", time.trim().substring(0, 4));
				data.put("m", time.trim().substring(5, 7));
				data.put("d", time.trim().substring(8, 10));
				if (zzxmjgService.getSfzxDk(xh).get("cs").equals("0")) {
					data.put("zxdk", "��");
				} else {
					data.put("zxdk", "��");
				}
				String sxn = (Integer.parseInt(model.getXn().trim().substring(0, 4)) - 1) + "-" + (Integer.parseInt(model.getXn().trim().substring(5, 9)) - 1);
				HashMap<String, String> sfkns = zzxmjgService.getSfkns(xh, model.getXn());
				if (sfkns.get("dcmc") == null) {
					sfkns.put("dcmc", "��ͥ���ò�����");
				}
				HashMap<String, String> sfxs = zzxmjgService.getSfxs();
				data.putAll(sfkns);
				if ((sfxs.get("dqnd").trim()).equals(xsjbxx.get("rxrq").trim().substring(0, 4))) {
					sfkns.put("isxs", "��");
				} else {
					sfkns.put("isxs", "��");
				}
				List<HashMap<String, String>> pjjgList = pjjgService.getPjjgGroupByXn(xh);
				if (pjjgList.size() == 0) {
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				JtqkdcDao jtqkDao = new JtqkdcDao();
				List<HashMap<String, String>> cyListSXSD = jtqkDao.getJtcyListFour(model.getXh());
				data.put("cyListSXSD", cyListSXSD);//��ͥ��Ա�б�
				data.put("blankList", 4 > cyList.size() ? jtqkdcService
						.getBlankList(4 - cyList.size()) : jtqkdcService
						.getBlankList(0));

				data.put("jejg", model.getYlzd1());

				ZzxmjgDao zzjgDao = new ZzxmjgDao();
				data.put("zypm", zzjgDao.getXsZyBxPm(sxn, xsjbxx.get("zydm"), xh));
				data.put("zyrs", zzjgDao.getZyrs(xsjbxx.get("zydm")));
				data.put("pjcj", zzjgDao.getBxPjcj(xh, sxn));
				data.put("zdf", zzjgDao.getZdf(xh, sxn));
				data.put("zdfkmmc", zzjgDao.getZdfkmmc(xh, sxn));
			}
			//����ũҵ��ѧ
			if ("10504".equals(Base.xxdm)) {
				int size6 = (4 - pjjg.size()) <= 0 ? 0 : (4 - pjjg.size());
				data.put("blankList1", jtqkdcService.getBlankList(size6));
				int size7 = (4 - cyList.size()) <= 0 ? 0 : (4 - cyList.size());
				data.put("blankList2", jtqkdcService.getBlankList(size7));

			}

			//��ͥ�˾�������
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
			data.put("jtrjysrtemp", jtrjysrtemp);// ��ͥ�˾�������

			String szbbjrs = service.countBjRs(xh);

			data.put("szbbjrs", szbbjrs);  //���ڰ�༶����

			data.put("je", xmwhModel.getJe());// ������

			data.put("zzsqsj", model.getSqsj() == null ? "" : model.getSqsj().substring(0, model.getSqsj().indexOf(" ")));// ����ʱ�� yyyy-MM-dd

			if ("10335".equals(Base.xxdm)) {
				//ѧ������������ʱ�䣨����ʱ��ȡXXXX��XX�£�
				HashMap<String, String> knsxx = knsjgService.getKnsxx(model.getXh());
				data.put("knssqsj", DateTranCnDate.fomartDateToCn(knsxx.get("sqsj"), FomartDateType.month));
				//ȡѧ�����뽱��ʱ��
				xsjbxx.put("zzxmsqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(), FomartDateType.day));
				//��ʦ���ͨ�������ս��
				String zzxmshje = service.zzxmshtgJe(xh, model.getXn(), model.getXq());
				data.put("zzxmshje", zzxmshje);
			}
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
				String bjrs = zcfService.getBjrs(xh);
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
					data.put("jtrjysr","");
				}
				String rxrq = xsjbxx.get("rxrq");
				data.put("rxrq", rxrq);
			}
			//�㽭��ְͨҵ����ѧԺ
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getGuid());
				for (int i = 0; i < shyjjtzyList.size(); i++) {
					data.put("shyj"+i, shyjjtzyList.get(i).get("shyj"));
				}
				String qsbh = null;
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(xh);
				if (null == qsxx.get("ldmc") || null == qsxx.get("qsh")) {
					qsbh = "";
				} else {
					qsbh = qsxx.get("ldmc") + "-" + qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
			}
			//ͨ��������1-7��
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(myForm.getGuid());
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			// ��ѧ�ż���ѧ��ס����Ϣ
			HashMap<String, String> gyxx = service.getGyxx(xh);
			data.putAll(gyxx);
			File wordFile = null;
			if ("12867".equals(Base.xxdm)) {
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(xsjbxx.get("bjmc") + "" + xsjbxx.get("xh") + "[" + xsjbxx.get("xm") + "]"));
			} else {
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(model.getXh() + "[" + xsjbxx.get("xm") + "]"));// "classpath://templates//" +  "xszz"// +"gjjxjb.xml"
			}
			return wordFile;
		}
		
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��˳���
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-16 ����02:49:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-�������-����SHID:{shid}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm model = new XszzSqshForm();
		XszzSqshService service = new XszzSqshService();

		String shid = request.getParameter("shid");
		String shlc = request.getParameter("shlc");
		String xh = request.getParameter("xh");
		String xtgwid = request.getParameter("xtgwid");
		model.setShlc(shlc);
		model.setShid(shid);
		model.setXh(xh);
		model.setXtgwid(xtgwid);
		User user = getUser(request);
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);

		String cancelFlg = service.cxshnew(shxx.get("ywid"), model, user);


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


	//=========================ѧ�����벿�ֿ�ʼ==============================//

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:ѧ����������ҳ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-6 ����10:33:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq_stu.do")
	public ActionForward xmsqStuManage(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm model = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		User user = getUser(request); //��ǰ��¼ѧ��

		String userType = user.getUserType();//��ģ��ֻ����ѧ������
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String actionType = model.getType(); //�Ƿ��ѯ
		if (StringUtils.isEqual(QUERY, actionType)) {
			String xh = user.getUserName();
			model.setXh(xh);
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			List<HashMap<String, Object>> data = service.getXmsqInfoList(model);
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		}

		List<HashMap<String, String>> xmlbList = service.getXmlb();// ��Ŀ���
		request.setAttribute("xmlbList", xmlbList);

		request.setAttribute("xnxqmc", Base.currXn + " " + DAO.getInstance().getXqmcForXqdm(Base.currXq));
		String path = "xszz_sqsh_xmsq_stu.do";
		request.setAttribute("path", path);
		String currDate = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		request.setAttribute("currDate", currDate);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("xmsqStuManage");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:ѧ����������ҳ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-6 ����02:34:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq_stu.do")
	public ActionForward xszzXmsqStu(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		User user = getUser(request);
		myForm.setXh(user.getUserName());

		List<HashMap<String, String>> xmlbList = service.getXmlb();// ��Ŀ���
		request.setAttribute("xmlbList", xmlbList);

		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.getDqxqmc());
		request.setAttribute("xh", getUser(request).getUserName());
		request.setAttribute("mkxxList", mkxxList);
		//request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_sqsh.do?method=xszzXmsqStu";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		request.setAttribute("xmdm", myForm.getXmdm());
		this.saveToken(request);
		return mapping.findForward("xszzXmsqStu");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��Ŀ������ü��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-7 ����09:22:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */

	public ActionForward xszzXmsqChkJdStu(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhJdszService service = new XmwhJdszService();
		XszzSqshService xszzXmsqService = new XszzSqshService();
		XszzSqshForm model = (XszzSqshForm) form;
		model.setXh(getUser(request).getUserName());
		model.setSqType("ysq");

		StringBuffer msg = new StringBuffer();

		boolean isSuccess = true;

		String[] xmdmArr = request.getParameter("xmdmids").split(",");                    //======������Ŀ����

		for (String xmdm : xmdmArr) {
			List<HashMap<String, String>> ysqList = xszzXmsqService.getAllYsqXmList(model); //=====��������Ŀ�б�
			List<HashMap<String, String>> resultList = service.getKjdxm(xmdm);                //=====�����Ŀ�б�

			XmwhDao xmwhDao = new XmwhDao();
			HashMap<String, String> data = xmwhDao.getDataById(xmdm);
			String jdkg = "";
			if (data != null) {
				jdkg = data.get("jdkg");
			}

			if (StringUtils.isEqual("1", jdkg)) {
				if (resultList != null && resultList.size() != 0) {
					for (HashMap<String, String> bkjdxmdm : resultList) {
						for (HashMap<String, String> ysqxm : ysqList) {
							if (StringUtils.isEqual(ysqxm.get("xmdm"), bkjdxmdm.get("dm"))) {
								isSuccess = false;
								msg.append(bkjdxmdm.get("mc")).append("������,").append(bkjdxmdm.get("sqxmmc")).append("��������,����ܼ��;");
								break;
							}
						}
					}
					if (isSuccess) {
						for (HashMap<String, String> bkjdxmdm : resultList) {
							for (String sqxmdm : xmdmArr) {
								if (StringUtils.isEqual(sqxmdm, bkjdxmdm.get("dm"))) {
									isSuccess = false;
									msg.append(bkjdxmdm.get("mc")).append(" ������ �� ").append(bkjdxmdm.get("sqxmmc")).append(" ��������,���ܼ��;");
									break;
								}
							}
						}
					}
				}
			}

		}

		response.getWriter().print(getJsonByMsg(msg.toString(), isSuccess));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:������Ŀ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-7 ����01:20:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq_stu.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward updateXmsqStu(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		XszzSqshForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		}
		//��ȡ������Ŀ��Ϣ
		XmwhService xmService = new XmwhService();
		XmwhForm xmwhForm = xmService.getModel(model.getXmdm());
		request.setAttribute("xmwhForm", xmwhForm);
		request.setAttribute("type", UPDATE);
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("mkxxList", mkxxList);
		return mapping.findForward("updateXmsqStu");
	}

	//=========================ѧ�����벿�ֽ���==============================//

	protected JSONObject getJsonByMsg(String msg, boolean success) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("message", msg);
		map.put("success", String.valueOf(success));
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}

	public ActionForward showXmxx_10335(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhService service = new XmwhService();
		HashMap<String, String> xmxxmap = service.showXmxx_10335(xmdm);
		request.setAttribute("xmxx", xmxxmap);
		return mapping.findForward("showxmxx");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��������������ͼ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-19 ����10:41:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward printLct(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		File file = print(request);
		FileUtil.outputWord(response, file);

		return null;
	}

	private synchronized File print(HttpServletRequest request) throws Exception {
		return FreeMarkerUtil.getWordFile(null, Constants.TEP_DIR + "xszz", "xszzsqlct.xml", FreeMarkerUtil.getFileName("��������ͼ"));
	}

	/**
	 *  ѧ�����������ύ�쳣���ݴ���.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-17 17:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw Exception
	 */
	public ActionForward exceptionDataResolve(ActionMapping mapping, ActionForm form,
											  HttpServletRequest request, HttpServletResponse response) throws Exception {

		XszzSqshService service = new XszzSqshService();
		Map<String, String> map = service.exceptionDataResolve();

		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
}
