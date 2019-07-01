package com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jglr.JglrService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: ѧ������2013�� �������϶� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-20 ����11:38:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdbjpyAction extends SuperAction {

	private static final String KNSRD = "knsrd";
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	private static final String OPEN = "1";
	
	private static final String url = "xszz_knsrdbjpy_sq.do";
	
	/**
	 * 
	 * @����:�������������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����10:25:18
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
	public ActionForward knssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "��������δ��ʼ��������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("jcszModel", jcszModel);
		
		//���������϶����ڲ�Ϊ��
		if(jcszModel != null && jcszModel.getXn() != null) {
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getXn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getXq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		//��������������
		if(jcszModel != null){
			request.setAttribute("sfysq", service.getExists(model, user.getUserName()));
		}
		
		request.setAttribute("sqzq", SQZQ);
		String path = "xszz_knsrdbjpy_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knssqManage");
	}
	
	
	
	/**
	 * 
	 * @����:�������϶�--����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-20 ����11:38:24
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
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		request.setAttribute("type", model.getType());
		
		if (!StringUtil.isNull(model.getXh())){
			KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
			KnsjcszbjpyForm jcszModel = jcszService.getModel();
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if(!"update".equals(model.getType())){
				model.setXn(jcszModel.getXn());
			}
			if("xn".equals(SQZQ)){
				model.setXq("on");
			}
			else{
				if(!"update".equals(model.getType())){
					model.setXq(jcszModel.getXq());
				}
			}
			KnsrdbjpyForm knsrdbjpyModel = service.getModelByXnXq(model);
			
			if (knsrdbjpyModel != null){
				BeanUtils.copyProperties(model, StringUtils.formatData(knsrdbjpyModel));
			}
			
			
			
			if (jcszModel != null && OPEN.equals(jcszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				request.setAttribute("openJtqk", jtqkModel == null);
			}
			request.setAttribute("ishave",service.getExists(model, model.getXh()));
		}
		//��ȡ��������������
		KnsjcszbjpyService jcszService=new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		request.setAttribute("isopen", jcszForm.getIsopen());
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		//�����������б�
		KnsdcService knsdcbjpySerivce = new KnsdcService();
		request.setAttribute("knsdcbjpyList", knsdcbjpySerivce.getKnsdcList());
		//�������϶���ʾ����
		mkxxList = bdpzService.getBdpz(KNSRD);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_knsrdbjpy.do?method=knssq";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("knssq");
	}
	
	
	
	/**
	 * 
	 * @����:�������϶����� 
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����08:43:38
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
	@SystemLog(description="����ѧ������-�������϶�-����������-������޸ı���-XH:{xh},SQLY:{sqly}")
	public ActionForward saveKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if(model.getType().equals("submit")){
			// ����С����Ա�������룡
			JglrService jglrService = new JglrService();
			HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(model.getXh());
			if(bjpyxzcyMap.get("xh") != null){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		super.resetToken(request);
		boolean result = service.saveKnssq(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-����������-�ύVALUES:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyService service = new KnsrdbjpyService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String shzt = request.getParameter("shzt");
		
		if(!Constants.YW_YTH.equalsIgnoreCase(shzt)){
			KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
			KnsjcszbjpyForm jcszModel = jcszDao.getModel();
			lcid = jcszModel.getSplc();
		}
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			xh=user.getUserName();
		}
		// ����С����Ա�������룡
		JglrService jglrService = new JglrService();
		HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(xh);
		if(bjpyxzcyMap.get("xh") != null){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
			return null;
		}
		
		KnsrdbjpyForm model = new KnsrdbjpyForm();
		model.setGuid(values);
		model.setShzt(Constants.YW_BJPYZ);
		model.setShlc(lcid);
		boolean result = service.updateModel(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-����������-����VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyService service = new KnsrdbjpyService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
//		boolean result = service.cancleRecord(values,lcid);
//		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			KnsrdbjpyForm model = new KnsrdbjpyForm();
			model.setGuid(values);
			ShlcDao shlcDao = new ShlcDao();
			if(Integer.valueOf(shlcDao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			model.setPddc("");
			boolean result = service.updateModel(model);
			
			KnsrdbjpyForm modelNew = service.getModel(model);
			JgcxDao jgcxDao = new JgcxDao();
			String guid = modelNew.getGuid();
			String xn = modelNew.getXn();
			String xq = modelNew.getXq();
			String sqr = modelNew.getXh();
			// ���°༶�����
			boolean rs = jgcxDao.cxBjpyxzpy(xn, xq, sqr);
			if(rs){
				// ���½����ѯ��
				JgcxForm jgcxForm = new JgcxForm();
				jgcxForm.setSqid(guid);
				jgcxForm.setTjzt("0");
				jgcxForm.setTjsj(" ");
				jgcxForm.setShzt(" ");
				jgcxDao.runUpdate(jgcxForm);
			}
//		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:ɾ������������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����01:14:49
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
	@SystemLog(description="����ѧ������-�������϶�-����������-ɾ��VALUES:{values}")
	public ActionForward delKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyService service = new KnsrdbjpyService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @����:���̸���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����01:53:42
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
	public ActionForward getSplcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		List<HashMap<String,String>> infoList = service.getSplcInfo(model);
		
		request.setAttribute("infoList", infoList);
		return mapping.findForward("knsrdbjpyLcgz");
	}
	
	/**
	 * 
	 * @����:�������϶����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-20 ����11:39:03
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
	public ActionForward knsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getShjlList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "��������δ��ʼ��������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//���������϶����ڲ�Ϊ��
		if(jcszModel != null && jcszModel.getXn() != null) {
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getXn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getXq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		request.setAttribute("sqzq", SQZQ);
		String path = "xszz_knsrdbjpy_sh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("knsshManage");
	}
	
	
	
	/**
	 * 
	 * @����:�������϶�--�������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-20 ����11:39:33
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
	public ActionForward knsrdbjpyDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		String xtgwid = myForm.getXtgwid();
		KnsrdbjpyForm model = service.getModel(myForm);
		model.setShid(myForm.getShid());
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			request.setAttribute("infoList", infoList);
			BeanUtils.copyProperties(myForm, model);
		}
		
		myForm.setXtgwid(xtgwid);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		
		//��ȡ��������������
		KnsjcszbjpyService jcszService=new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		
		KnsdcService knsdcbjpySerivce = new KnsdcService();
		
		//�����������б�
		request.setAttribute("knsdcbjpyList", knsdcbjpySerivce.getKnsdcList());
		//�����Ի��޳���������б�
		request.setAttribute("wczzjeList", knsdcbjpySerivce.getWczzList());
		//���������϶���¼
		request.setAttribute("rdlsjlList", new KnsjgService().getKnsInfoList(model.getXh()));
		//�����������ͨ������������
		String zd2 = service.getNewKnsdcbjpy(myForm).get("zd2");
		if(StringUtil.isNull(zd2)){
			zd2 = model.getPddc();
		}
		myForm.setRddc(zd2);
		//�������϶���ʾ����
		mkxxList = bdpzService.getBdpz(KNSRD);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("knsrdbjpyDgsh");
	}
	
	

	/**
	 * 
	 * @����:�������϶��������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����11:35:24
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
	@SystemLog(description="����ѧ������-�������϶�-���������-��˱���-GUID:{guid}")
	public ActionForward saveKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) StringUtils.formatBean(form);
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		boolean isSuccess = service.saveKnssh(myForm, user);
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:�������������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����10:22:56
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
	@SystemLog(description="����ѧ������-�������϶�-���������-����GUID:{guid}")
	public ActionForward cancelKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		//HashMap<String,String> shxx = ShlcUtil.getShxx(myForm.getShid());	
		// ҵ��ع�
		boolean result = service.cancelKnssh(myForm.getGuid());
		// ҵ��ع��ɹ�
		//boolean isSuccess = service.cancelKnssh(myForm);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:�������϶�--�������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����11:41:21
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
	public ActionForward knsrdbjpyPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcService knsdcbjpySerivce = new KnsdcService();
		//�����������б�
		request.setAttribute("knsdcbjpyList", knsdcbjpySerivce.getKnsdcList());
		//�����Ի��޳���������б�
		request.setAttribute("wczzjeList", knsdcbjpySerivce.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		
		return mapping.findForward("knsrdbjpyPlsh");
	}
	
	
	
	/**
	 * 
	 * @����:���������--�������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����02:04:34
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * 
	 * @����:�������϶��鿴
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-27 ����02:04:07
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
	public ActionForward knsrdbjpyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		KnsrdbjpyForm model = service.getModel(myForm);
		User user = getUser(request);
//		if ("stu".equals(user.getUserType())){
//			model.setXh(user.getUserName());
//		}
		if (model != null){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			request.setAttribute("infoList", infoList);
			
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		KnsjcszbjpyService jcszService=new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());

		//�������϶���ʾ����
		mkxxList = bdpzService.getBdpz(KNSRD);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		return mapping.findForward("knsrdbjpyView");
	}
	
	
	
	/**
	 * 
	 * @����:��ӡ������������Ϣ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-26 ����02:19:37
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		String guids[]=myForm.getGuid().split(",");
		if(null!=guids&&guids.length==1){//һ������
			if (myForm != null){
				File file=print(myForm,guids[0],request);
				FileUtil.outputWord(response, file);
			}
		}else{//��������
			List<File> files = new ArrayList<File>();
			for(String guid:guids){
				File file=print(myForm,guid,request);
				files.add(file);
				}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	/**
	 * 
	 * @����:��ȡ��ӡFile
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-20 ����05:33:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File print(KnsrdbjpyForm myForm,String guid,HttpServletRequest request) throws Exception{
		myForm.setGuid(guid);
		KnsrdbjpyService service = new KnsrdbjpyService();
		File file=null;
		KnsrdbjpyForm model = service.getModel(myForm);
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			//������Ϣ
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			
			BeanUtils.copyProperties(myForm, model);
			 //����������list
			KnsdcService knsdcbjpyService = new KnsdcService();
			file=service.printForWord(xsjbxx,infoList,model,knsdcbjpyService.getKnsdcList(), model.getRddc(),request);
		}
		return file;
}

	/**
	 * 
	 * @����: �������϶�������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-22 ����10:23:32
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
	public ActionForward knsrdbjpyShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		User user = getUser(request);
		KnsrdbjpyService service = new KnsrdbjpyService();
		Map<String,Object> shqkInfo = service.getShqkInfo(user);
		
		request.setAttribute("shqkInfo", shqkInfo);
		return mapping.findForward("knsrdbjpyShqk");
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
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//��ѯ
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
	 * @����: ��˵���(�Զ��嵼������)
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-15 ����01:53:50
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
	public ActionForward exportDataSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		model.setShzt(shlx);
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListSh(model,user);//��ѯ�����м�¼������ҳ
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
	 * @����: ����������ѯ����ѧ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-3 ����04:27:45
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
	public ActionForward getStudentsByShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getStudentsList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "xszz_knsrdbjpy_shtj.do");
		return mapping.findForward("studentsList");
	}
}
