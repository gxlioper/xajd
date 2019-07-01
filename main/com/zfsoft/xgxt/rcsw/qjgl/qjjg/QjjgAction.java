/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjjg;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqService;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqCsszForm;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqcsszService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:03:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjjgAction extends SuperAction {
	//�����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWRCXW = "rcswqjgl_qjgl";
	private static int maxSize=5*1024*1024;
	private BdpzService bdpzService = new BdpzService();
	private QjsqService qjsqService = new QjsqService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "qjjgbase.do";

	/**
	 * 
	 * @����:��ٹ����б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		CommService cs = new CommService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("qjjgbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "qjjgbase.do";
		request.setAttribute("path", path);
		request.setAttribute("currTime", GetTime.getSystemTime());
		//������Ͽ��������
		request.setAttribute("xjts", MessageUtil.getText("xjts"));
		request.setAttribute("usertype",getUser(request).getUserType());
		XjsqCsszForm xjForm = new XjsqcsszService().getModel();
		if(xjForm != null && StringUtils.isNotNull(xjForm.getSplc())){
			request.setAttribute("xjadmit", true);
		}else{
			request.setAttribute("xjadmit", false);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	@SystemLog(description="�����ճ�����-��ٹ���-��ٽ��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����: ��ٹ���ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	@SystemLog(description="�����ճ�����-��ٹ���-��ٽ��-�޸�QJJGID:{qjjgid},XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},QJJS:{qjjs},SFLX:{sflx},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		if (!StringUtil.isNull(myForm.getMdd())) {
			myForm.setMdd(URLDecoder.decode(myForm.getMdd(),"UTF-8"));
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			/**
			 * ͨ�ù��ܱ�����ύʱ���ڼ��պ���ĩ���͵�ʱ��ѡ����ȷ����֤
			 */
			HashMap<String, String> dataMap = qjsqService.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			boolean result = service.update(myForm);
			QjsqForm qjsqForm = new QjsqForm();
			qjsqForm.setQjsqid(myForm.getQjsqid());
			new QjsqService().saveQjmx(qjsqForm, mxrq, mxxmList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		request.setAttribute("qjlxList",service.getQjlx());
		request.setAttribute("filepath", model.getFilepath());
		//ѧ��������Ϣ
		String path = "qjjg.do?method=update";
		request.setAttribute("path", path);
		//��ٿγ���Ϣ
		List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", new QjsqService().getQjmxList(myForm.getQjsqid()));
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("qjsjxsgz", service.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("isnotList", isnotList);
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//���������ѧ���Ի�
			request.setAttribute("dmList", djService.getDmList());
		}
		return mapping.findForward("update");
	}
	/**
	 * 
	 * @����:���ٴ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-12 ����09:49:20
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
	@SystemLog(description="�����ճ�����-��ٹ���-��ٽ��-����QJJGID:{qjjgid}")
	public ActionForward xjcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
 		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.xjcl(myForm,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		//��ٿγ���Ϣ
		List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		//�������
		QjlxService qjlx=new QjlxService();
		request.setAttribute("qjlxmc",qjlx.getModel(model.getQjlxid()).getQjlxmc());
		request.setAttribute("qjsjxsgz", service.getCssz().get("qjsjxsgz"));
		return mapping.findForward("xjcl");
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:49:02
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
	@SystemLog(description="�����ճ�����-��ٹ���-��ٽ��-����XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},QJJS:{qjjs},SFLX:{sflx},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		if (!StringUtil.isNull(myForm.getMdd())) {
			myForm.setMdd(URLDecoder.decode(myForm.getMdd(),"UTF-8"));
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			myForm.setQjzt("1");//���������
			myForm.setQjsqid(UniqID.getInstance().getUniqIDHash());
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			/**
			 * ͨ�ù��ܱ�����ύʱ���ڼ��պ���ĩ���͵�ʱ��ѡ����ȷ����֤
			 */
			HashMap<String, String> dataMap = qjsqService.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			super.resetToken(request);
			
			boolean result = service.save(myForm);
			
			QjsqForm qjsqForm = new QjsqForm();
			qjsqForm.setQjsqid(myForm.getQjsqid());
			new QjsqService().saveQjmx(qjsqForm, mxrq, mxxmList);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", new QjsqService().getQjmxList(myForm.getQjsqid()));
		request.setAttribute("qjlxList",service.getQjlx());
		//get student detail
		//ѧ��������Ϣ
		String path = "qjjg.do?method=add";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		myForm.setXq(Base.currXq);
		myForm.setXn(Base.currXn);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("qjsjxsgz", service.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("isnotList", isnotList);
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//���������ѧ���Ի�
			request.setAttribute("dmList", djService.getDmList());
		}
		this.saveToken(request);
		return mapping.findForward("add");
	}
	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-17 ����05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		QjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
 		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HashMap<String, String>  hm=service.getSplc(model,true);
		request.setAttribute("shztmc", hm.get("splcid"));
		//�������
		QjlxService qjlx=new QjlxService();
		request.setAttribute("qjlxmc",qjlx.getModel(model.getQjlxid()).getQjlxmc());
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", model);
		//��ٿγ���Ϣ
		List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", new QjsqService().getQjmxList(myForm.getQjsqid()));
		
		//������Ϣ
		request.setAttribute("xjxx", service.getXjxx(model.getQjsqid()));
		//��ʷ��ټ�¼
		request.setAttribute("history",service.getHistory(model));
		return mapping.findForward("show");
	}
	/**
	 * 
	 * @����:������������Ӧ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-12 ����10:11:54
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
	public ActionForward checkTs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		String sfjc=request.getParameter("sfjc");
		Map<String, String> map = new HashMap<String, String>();
		//�������Ϊ�����
		if(!StringUtil.isNull(sfjc)){
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		//��ȡ�������
		myForm.setQjts(request.getParameter("qjts"));
		boolean result = service.isHaveSplcForTs(myForm);
		if (!result) {
			map.put("success", "false");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	/**
	 * 
	 * @����:�ж��Ƿ������������¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-28 ����09:10:35
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
	public ActionForward haveNewSqjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		Map<String, String> map = new HashMap<String, String>();
		if(service.haveNewSqjl(myForm)){
			map.put("message", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;

	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר�������жϣ�����10�죨����10�죩��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-8 ����09:01:52
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
	public ActionForward xjrpdTenD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
		String yhm = user.getUserName();
		Map<String, String> map = new HashMap<String, String>();
		String sqid = myForm.getQjsqid();
		HashMap<String, String> lcxx = service.xjrpdTenD(sqid);
		String xjr = lcxx.get("shr"); // ��ȡ�����е����ڶ����̵������
		if(!yhm.equals(xjr)) {
			map.put("message", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
				
		return null;

	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר�������жϣ�10�����£�������10�죩��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-8 ����09:01:52
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
	public ActionForward xjrpdOneToN(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
		String yhm = user.getUserName();
		Map<String, String> map = new HashMap<String, String>();
		String sqid = myForm.getQjsqid();
		HashMap<String, String> lcxx = service.xjrpdOneToN(sqid);
		String xjr = lcxx.get("shr"); // ��ȡ���������һ�����̵������
		if(!yhm.equals(xjr)) {
			map.put("message", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
				
		return null;

	}
	
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgForm model = (QjjgForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		QjjgService service = new QjjgService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����:������ٽ����
	 * @���ߣ������[���ţ�1004]
	 * @���ڣ�2013-12-17 ����02:33:17
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
	public ActionForward printQjjgb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		XsxxService xsxxService = new XsxxService();
		if(StringUtils.isNotNull(myForm.getQjjgid())){
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjjgid().split(",");
			for (String id : qjjgids) {
				myForm.setQjjgid(id);
				HashMap<String,String> qjjgMap=service.getQjjgForPrint(id);
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(qjjgMap.get("xh"));
				
				File file = service.printForWord(xsjbxx,qjjgMap);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			}
		}
		return null;
	}
	/**
	 * 
	 * @����:��ʦ����Ի�����������ӡ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-22 ����04:01:52
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
	public ActionForward printQjjgbOfHs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> fdyData = new HashMap<String, Object>();
		QjjgService service = new QjjgService();
		XsxxService xsxxService = new XsxxService();
		QjjgForm myForm = (QjjgForm) form;
		if(StringUtils.isNotNull(myForm.getQjjgid())){
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjjgid().split(",");
			for (String id : qjjgids) {
				myForm.setQjjgid(id);
				QjjgForm model = service.getModel(myForm);
				// ==========��ѯ�����Ϣ���������ʱ�併�� begin=======
				Map<String, String> shMap0 = new HashMap<String, String>();
				ShlcDao splcdao = new ShlcDao();
				List<HashMap<String , String>> shyjList = splcdao.getShyjList(model.getQjsqid(), "desc");
				if(shyjList.size() >= 1){
					shMap0 = shyjList.get(0);
				}
				// ==========��ѯ�����Ϣ���������ʱ�併�� end=======
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				String qjkssj = DateTranCnDate.fomartDateToCn(model.getKssj(),FomartDateType.day);
				String qjjssj = DateTranCnDate.fomartDateToCn(model.getJssj(),FomartDateType.day);
				String sqsj = DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day);
				String qjbh = model.getSqsj().substring(0,10).replace("-", "")+model.getQjbh();
				xsjbxx.put("qjkssj", qjkssj.substring(qjkssj.indexOf("��")+1));
				xsjbxx.put("qjjssj", qjjssj.substring(qjjssj.indexOf("��")+1));
				xsjbxx.put("qjts", model.getQjts());
				xsjbxx.put("qjsy", HtmlUtil.xmlZy(model.getQjsy()));
				xsjbxx.put("sqsj", sqsj);
				xsjbxx.put("qjbh", qjbh);
				//��ٿγ���Ϣ
				xsjbxx.put("rkjs","�ον�ʦ");
				List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
				//�ο���ʦ������ٵ�
				File qjkcFile =null;
				for (HashMap<String, String> qjkc : qjkcList) {
					xsjbxx.put("kcmc", qjkc.get("kcmc"));
					xsjbxx.put("rkjs", "�ον�ʦ");
					xsjbxx.put("rklsxm", qjkc.get("rklsxm"));
					fdyData.putAll(xsjbxx);
					fdyData.put("shMap0", shMap0);
					qjkcFile =service.getWordOfFdy(fdyData);
					files.add(qjkcFile);
				}
				//�������⣬��һ�����ݵ�������
				if(qjkcList.size()>0){
					xsjbxx.put("kcmc1", qjkcList.get(0).get("kcmc"));
					xsjbxx.put("rkjs1", "�ον�ʦ");
					xsjbxx.put("rklsxm1", qjkcList.get(0).get("rklsxm"));
					qjkcList.remove(0);
					
				}
				data.putAll(xsjbxx);
				
				data.put("qjkcList", qjkcList);
				data.put("shMap0", shMap0);
				File file = service.getWord(data);
				
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-13 ����02:17:09
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
	public ActionForward getXsqjspbOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		QjjgForm model = (QjjgForm)form;
		/*��ȡurl����������ٽ��id*/
		String qjjgid = model.getQjjgid();
		/*��ȡ�ļ���Ϣ*/
		File wordFile = getWordForQjspb(qjjgid);
		/*����ļ�*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-13 ����02:17:26
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
	public ActionForward getXsqjspbZip (ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		/*��ȡurl��������Value*/
		String value = request.getParameter("value");
		/*�ж�value�Ƿ�Ϊ��*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForQjspb(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: ����������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-13 ����02:19:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjjgid
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForQjspb (String qjjgid) throws Exception{
		
		QjjgService service = new QjjgService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*����һ����file*/
		File file = null;
		/*������ѡqjsqid��ȡ�����Ϣ*/
		HashMap<String,String> qjxxInfo = service.getQjjgxxForQjsqid(qjjgid);
		String xh = qjxxInfo.get("xh");
		/*�������Ϣ*/
		data.put("qjxxInfo", qjxxInfo);
		
		XsxxService xsxxService = new XsxxService();
		/*����ѧ��������Ϣ*/
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		
		/*�ָ���ٿ�ʼ����ʱ�������ʱ*/
		String kssjMonth = qjxxInfo.get("kssj").substring(5, 7);
		String kssjDay = qjxxInfo.get("kssj").substring(8, 10);
		String kssjHour = qjxxInfo.get("kssj").substring(11, 13);
		data.put("kssjMonth", kssjMonth);
		data.put("kssjDay", kssjDay);
		data.put("kssjHour", kssjHour);
		String jssjMonth = qjxxInfo.get("jssj").substring(5, 7);
		String jssjDay = qjxxInfo.get("jssj").substring(8, 10);
		String jssjHour = qjxxInfo.get("jssj").substring(11, 13);
		data.put("jssjMonth", jssjMonth);
		data.put("jssjDay", jssjDay);
		data.put("jssjHour", jssjHour);
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw","qjspb_12869.xml", FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		return file;
	}

}

