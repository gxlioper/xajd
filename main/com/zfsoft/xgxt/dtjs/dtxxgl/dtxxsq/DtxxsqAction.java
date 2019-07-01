/**

 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq;

import java.io.File;
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
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgDao;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-10-25 ����10:37:06
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DtxxsqAction extends SuperAction {
	//�ӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String XSXXPZ = "dtxxXsxxpz";
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String SUBMIT = "submit";
	
	private static final String url = "dtxxsqbase.do";

	/**
	 * 
	 * @����:�б��ѯ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����10:37:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("dtxxsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "dtxxsqbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dtxxsqlb");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-10-24 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if (null == mess || mess.length != 2) {
				String message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", mess[0]);
			map.put("nodel", mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����:�޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����10:38:19
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
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		DtxxsqForm model = service.getModel(myForm);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService
					.getXsjbxxMoreForDtgl(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType()) ||SUBMIT.equalsIgnoreCase(myForm.getType())) {

			DtxxsqForm modelGet = service.getModel(myForm.getDtxxsqid());			
			
			// ���˻صļ�¼ȡ�ϵ��������ID;�����˻ؼ�¼����ȥȡ�������
			if(!Constants.YW_YTH.equals(modelGet.getShzt())){

				ShlcpzService shlcService = new ShlcpzService();
				ShlcpzForm shlcpz = new ShlcpzForm();
				shlcpz.setJddm(modelGet.getJddm());
				shlcpz = shlcService.getModel(shlcpz);
				if(shlcpz!=null){
					myForm.setSplc(shlcpz.getSplc());
				}
			}else{
				myForm.setJddm(modelGet.getJddm());
				myForm.setSplc(modelGet.getSplc());
				//�����棬�����״̬��δ���˻�
				if(SAVE.equalsIgnoreCase(myForm.getType())){
					myForm.setShzt(Constants.YW_YTH);
				}
			}

			
			boolean result = service.update(myForm);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
							: MessageKey.SYS_SUBMIT_FAIL;
			}else{
				 messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
			}
			
			
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		// ѧ��������Ϣ
		String path = "dtxxsq.do?method=update";
		request.setAttribute("path", path);
		
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XSXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		
		// �׶���Ϣ
		List<HashMap<String, String>> sqJdlist = null;
		
		// ���˻�
		if(Constants.YW_YTH.equals(model.getShzt())){

			// �׶���Ϣ
			sqJdlist = service.getSqJdList(myForm.getXh(),model.getJddm());
		}else{
			// �׶���Ϣ
			sqJdlist = service.getSqJdList(myForm.getXh(),"");
		}
		request.setAttribute("sqJdlist", sqJdlist);
		
		return mapping.findForward("dtxxsqxg");
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����10:44:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService
			.getXsjbxxMoreForDtgl(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())||SUBMIT.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
						: MessageKey.SYS_SUBMIT_FAIL;
			}else{
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			}
			
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> sqJdlist = service.getSqJdList(myForm.getXh(),"");
		request.setAttribute("sqJdlist", sqJdlist);
		// get student detail
		// ѧ��������Ϣ
		String path = "dtxxsq.do?method=add";
		request.setAttribute("path", path);

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XSXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("dtxxsqzj");
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitDtxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm model = (DtxxsqForm) form;
		User user = getUser(request);
		String dtxxsqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		String splc = request.getParameter("splc");
		model.setDtxxsqid(dtxxsqid);
		model.setXh(xh);
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm t = service.getModel(dtxxsqid);
		String jdmc = service.getJdmc(t.getJddm());
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService
				.getXsjbxxMoreForDtgl(xh);
		int age = service.getAge(xsjbxx.get("csrq"));
		if(StringUtils.equals(jdmc,"�뵳��������") && age<18){
			String message ="�ύʧ�ܣ��뵳�������������������18�꣡";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if(StringUtils.equals(jdmc,"Ԥ����Ա") && age<19){
			String message ="�ύʧ�ܣ�Ԥ����Ա�����������19�꣡";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.submitDtxxsq(model);
    	String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(����������Ϣ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-9 ����10:07:32
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
	public ActionForward cancelDtxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		DtxxsqService service = new DtxxsqService();
		String dtxxsqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(dtxxsqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			DtxxsqForm model = new DtxxsqForm();
			model.setDtxxsqid(dtxxsqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(dtxxsqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateDtxxsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-24 ����05:23:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		DtxxsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService
					.getXsjbxxMoreForDtgl(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XSXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dtxxsqck");
	}
	
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		// �Ƿ��������
		boolean result = service.isCanAdd(myForm);
		Map<String, String> map = new HashMap<String, String>();
		if (!result) {// ������
			map.put("success", "false");
			map.put("message", "��ѧ�����е��������������������!");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			/*response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));*/
		} else {

			//������ ѧ��������֤
			XsxxService xsxxService = new XsxxService();
			map.put("success", "true");
			String jdmc = service.getJdmc(myForm.getJddm());
			HashMap<String, String> xsjbxx = xsxxService
					.getXsjbxxMoreForDtgl(myForm.getXh());
			int age = service.getAge(xsjbxx.get("csrq"));
			if(StringUtils.equals(jdmc,"�뵳��������") && age<18){
				map.put("success", "false");
				map.put("message", "�뵳�������������������18�꣡");
			}
			if(StringUtils.equals(jdmc,"Ԥ����Ա") && age<19){
				map.put("success", "false");
				map.put("message", "Ԥ����Ա�����������19�꣡");
			}

			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	} 
	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����10:43:40
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm model = (DtxxsqForm) form;
		DtxxsqService service = new DtxxsqService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ

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
	 * @����: ��֤�Ƿ���ύ
	 * @���ߣ�qilm
	 * @���ڣ�2014-5-26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward checkSfktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtxxsqForm model = (DtxxsqForm) form;
		DtxxsqService service = new DtxxsqService();
		String jddm = request.getParameter("jddm");
		model.setJddm(jddm);
		
		// ȡ���Ƿ������֤(�����춯���) true:���ύ/false�������ύ
		boolean isSfktj = service.checkSfktj(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	
	/**
	 * 
	 * @����: ���������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����11:27:19
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
	public ActionForward getRtsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm myForm = (DtxxsqForm) form;
		File wordFile = getWord(myForm,request);		
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����: ��ȡwordģ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����11:27:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getWord(DtxxsqForm myForm,HttpServletRequest request) throws Exception{
		DtxxsqService service = new DtxxsqService();
		
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsjbxxMap = service.getXsjbxxMap(myForm.getDtxxsqid(),myForm.getFlag());
		String xh = xsjbxxMap.get("xh");
//		data.put("xyzsjg", myForm);
		data.putAll(xsjbxxMap);
		List<HashMap<String,String>> pjjgList = service.getPjjgList(xh); 
		List<HashMap<String,String>> wjcfList = service.getWjcfList(xh);
		data.put("pjjgList", pjjgList);
		data.put("wjcfList", wjcfList);
		String bjrs = new CxddJgDao().getBjrs(xsjbxxMap.get("bjdm"));
		data.put("bjrs",bjrs);
	
		//��ǰ����ʱ��
		String rq = GetTime.getTimeByFormat("yyyy-mm-dd");
		String[] xnArr = rq.split("-");
		data.put("nowTime", xnArr[0]+"��"+xnArr[1]+"��"+xnArr[2]+"��");

		
		//ְ��
		HashMap<String, String> zw = service.getZwmc(xh);
		data.put("zwmc", zw.get("zwmc"));
		//���޲�����γ�
		HashMap<String, String> bjgkcs = service.getBjgkc(xh);	
		String bjgkc = bjgkcs.get("num");
		if("0".equals(bjgkc)){ // ��ѧ�����޲�����γ�
			data.put("bjgkc", "��");
		}else{
			data.put("bjgkc", "��");
		}
		
		String dyxq = "01";
		String dexq = "02";
		//�����ǰѧ���ǵ�һѧ��
		if(dyxq.equals(Base.currXq)){
			//ȡ��ѧ���һѧ�ڵ��۲��
			List<HashMap<String,String>> zcfList1 = service.getZcfListByXh(myForm.getXh(),Base.beforXn, dyxq);
			for (HashMap<String, String> zcfMap : zcfList1) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("����")){
					data.put("dypm_01", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("�۲��ܷ�")){
					data.put("zhpm_01", zcfMap.get("bjpm"));
				}
			}
			List<HashMap<String,String>> zcfList2 = service.getZcfListByXh(myForm.getXh(),Base.beforXn, dexq);
			for (HashMap<String, String> zcfMap : zcfList2) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("����")){
					data.put("dypm_02", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("�۲��ܷ�")){
					data.put("zhpm_02", zcfMap.get("bjpm"));
				}
			}
		}
		//�����ǰѧ���ǵڶ�ѧ��
		if(dexq.equals(Base.currXq)){
			//ȡ��ѧ��ڶ�ѧ�ڳɼ�
			List<HashMap<String,String>> zcfList1 = service.getZcfListByXh(myForm.getXh(),Base.beforXn, dexq);
			for (HashMap<String, String> zcfMap : zcfList1) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("����")){
					data.put("dypm_01", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("�۲��ܷ�")){
					data.put("zhpm_01", zcfMap.get("bjpm"));
				}
			}
			List<HashMap<String,String>> zcfList2 = service.getZcfListByXh(myForm.getXh(),Base.currXn, dyxq);
			for (HashMap<String, String> zcfMap : zcfList2) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("����")){
					data.put("dypm_02", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("�۲��ܷ�")){
					data.put("zhpm_02", zcfMap.get("bjpm"));
				}
			}
		}
		if("��������".equals(myForm.getJdmc())){
			try{
				ResourceUtils.getFile(Constants.TEP_DIR+"dtxx/rtsqb.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"dtxx","rtsqb.xml",FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_��Ա��չ�����"));
			}catch (Exception e) {
				file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"dtxx","rtsqb.xml", FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_��Ա��չ�����"));
			}
		}else{
			try{
				ResourceUtils.getFile(Constants.TEP_DIR+"dtxx/tyrd.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"dtxx","tyrd.xml",FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_�뵳���������Ƽ���"));
			}catch (Exception e) {
				file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"dtxx","tyrd.xml", FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_�뵳���������Ƽ���"));
			}
		}
		return file;
	}
	
	/**
	 * 
	 * @����: ���������zip
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����11:41:18
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
	public ActionForward getRtsqbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm myForm = (DtxxsqForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setDtxxsqid(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
}
