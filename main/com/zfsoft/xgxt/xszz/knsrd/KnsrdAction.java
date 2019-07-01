package com.zfsoft.xgxt.xszz.knsrd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: ѧ������2013�� �������϶� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-20 ����11:38:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdAction extends SuperAction {

	private static final String KNSRD = "knsrd";
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	private static final String OPEN = "1";
	private KnsrdService service = new KnsrdService();
	private static final String url = "xszz_knsrd_sq.do";
	
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

		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
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
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		//���������϶����ڲ�Ϊ��
		if(jcszModel != null && jcszModel.getRdxn() != null) {
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getRdxn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getRdxq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		//��������������
		if(jcszModel != null){
			request.setAttribute("sfysq", service.getExists(model, user.getUserName()));
		}
		
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("jcszModel", jcszModel);
		String path = "xszz_knsrd_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
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
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		request.setAttribute("type", model.getType());
		
		KnsdcService knsdcSerivce = new KnsdcService();
		List<HashMap<String, String>> knyyList=knsdcSerivce.getKnyyList();
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			JcszService jcszService = new JcszService();
			JcszForm jcszModel = jcszService.getModel();
			if(!"update".equals(model.getType())){
				model.setXn(jcszModel.getRdxn());
			}
			if("xn".equals(SQZQ)){
				model.setXq("on");
			}else{
				if(!"update".equals(model.getType())){
					model.setXq(jcszModel.getRdxq());
				}
			}
			KnsrdForm knsrdModel = service.getModelByXnXq(model);
			
			if (knsrdModel != null){
				BeanUtils.copyProperties(model, StringUtils.formatData(knsrdModel));
			}
			
			if (jcszModel != null && OPEN.equals(jcszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				request.setAttribute("openJtqk", jtqkModel == null);
			}
			request.setAttribute("ishave",service.getExists(model, model.getXh()));
			if("10277".equals(Base.xxdm)) {
				knyyList=new KnsrdService().getKnnyList(model.getYlzd5());	
			}
		}
		//��ȡ��������������
		JcszService jcszService=new JcszService();
		JcszForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		request.setAttribute("isopen", jcszForm.getIsopen());
		JcszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		//�����������б�
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		request.setAttribute("knyyList", knyyList);
		//�������϶���ʾ����
		mkxxList = bdpzService.getBdpz(KNSRD);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_knsrd.do?method=knssq";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		if("10742".equals(Base.xxdm)) {
			request.setAttribute("sqlymcList", service.getSqlymcList());		
		}
		if("12389".equals(Base.xxdm)) {
			List<HashMap<String, String>> sqlyList = service.getSqlyList();
			request.setAttribute("sqlyList", sqlyList);
		}
		//����ʦ����ѧ���ؼ�ͥ�������ͺ͸ߵ�����Ʒ���� 
		if("10346".equals(Base.xxdm)){
			request.setAttribute("jtknlxList", service.getJtknlxList());
			request.setAttribute("gdxfplxList", service.getGdxfpLxList());
		}
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("knssq");
	}
	
	/**
	 * @����:�������϶������Ϊ�첽����ʱ���������ж��Ƿ�������ķ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��21�� ����2:08:53
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
	public ActionForward isHaveKnsrd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		request.setAttribute("type", model.getType());
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		boolean ishave = service.getExists(model, model.getXh());
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("ishave", ishave);
		JSONObject data = JSONObject.fromMap(map);
		response.getWriter().print(data);
		
		return null;
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
	@SystemLog(description="����ѧ������-�������϶�-����������-���ӻ��޸����뱣��-XH:{xh},SQLY:{sqly}")
	public ActionForward saveKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
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
		KnsrdService service = new KnsrdService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String shzt = request.getParameter("shzt");
		
		if(!Constants.YW_YTH.equalsIgnoreCase(shzt)){
			JcszDao jcszDao = new JcszDao();
			JcszForm jcszModel = jcszDao.getModel();
			lcid = jcszModel.getSplc();
		}
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			xh=user.getUserName();
		}
		boolean result = service.submitRecord(values,lcid,xh);
		if(result){
			//����ҵ��״̬Ϊ'�����'
			KnsrdForm model = new KnsrdForm();
			model.setGuid(values);
			model.setShzt(Constants.YW_SHZ);
			model.setShlc(lcid);
			service.updateModel(model);
		}
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
		KnsrdService service = new KnsrdService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			KnsrdForm model = new KnsrdForm();
			model.setGuid(values);
			ShlcDao shlcDao = new ShlcDao();
			if(Integer.valueOf(shlcDao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateModel(model);
		}
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
		
		KnsrdService service = new KnsrdService();
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
	@SystemAuth(url = url)
	public ActionForward getSplcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		List<HashMap<String,String>> infoList = service.getSplcInfo(model);
		
		request.setAttribute("infoList", infoList);
		return mapping.findForward("knsrdLcgz");
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
	@SystemAuth(url = "xszz_knsrd_sh.do")
	public ActionForward knsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
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
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		//���������϶����ڲ�Ϊ��
		if(jcszModel != null && jcszModel.getRdxn() != null) {
			
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getRdxn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getRdxq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("jcszModel", jcszModel);
		request.setAttribute("xxdm", Base.xxdm);
		String path = "xszz_knsrd_sh.do";
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		String xtgwid = myForm.getXtgwid();
		KnsrdForm model = service.getModel(myForm);
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
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		myForm.setXtgwid(xtgwid);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		
		//��ȡ��������������
		JcszService jcszService=new JcszService();
		JcszForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		
		KnsdcService knsdcSerivce = new KnsdcService();
		
		//�����������б�
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		//�����Ի��޳���������б�
		request.setAttribute("wczzjeList", knsdcSerivce.getWczzList());
		
		KnsjgService knsjgService = new KnsjgService();
		//���������϶���¼
		request.setAttribute("rdlsjlList", knsjgService.getKnsInfoList(model.getXh()));		 
		
		//�����������ͨ������������
		HashMap<String, String> newKnsdc = service.getNewKnsdc(myForm);
		myForm.setRddc(newKnsdc.get("zd2"));
		//�������ջ�д��������
		if("13871".equals(Base.xxdm)){
			myForm.setKnpx(newKnsdc.get("zd6"));
		}
		//�㽭����ְҵ����ѧԺ ����������
		if("12866".equals(Base.xxdm)) {
			myForm.setYlzd4(service.getNewBjpjjg(myForm).get("zd5"));
		}
		
//		String knyy = service.getNewKnyy(myForm).get("zd10");
//		myForm.setYlzd10(null==knyy?myForm.getYlzd5():knyy); //ȡ�Ƽ�ԭ��
		//�Ϻ�����ѧԺ���Ի�������ԭ���б�
		if("10277".equals(Base.xxdm)) {
        	String knyydm=myForm.getYlzd10();
			request.setAttribute("knyyList", new KnsrdService().getKnnyList(knyydm));	
		}
		
		//�������϶���ʾ����
		mkxxList = bdpzService.getBdpz(KNSRD);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		if("11998".equals(Base.xxdm)){
			request.setAttribute("zf",request.getParameter("zf"));
		}
		if("12389".equals(Base.xxdm)){
			if(!StringUtils.isNull(myForm.getYlzd9())){
				String[] sqlyList  = service.getSqlyListByDms(myForm.getYlzd9().split(","));
				request.setAttribute("sqlyList",sqlyList);
			}
		}
		return mapping.findForward("knsrdDgsh");
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-���������-��˱���-GUID:{guid}")
	public ActionForward saveKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm myForm = (KnsrdForm) StringUtils.formatBean(form);
		KnsrdService service = TransactionControlProxy.newProxy(new KnsrdService());
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
//		myForm.setYlzd10(myForm.getYlzd5()); //�Ƽ�ԭ��
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-���������-����GUID:{guid}")
	public ActionForward cancelKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcService knsdcSerivce = new KnsdcService();
		//�����������б�
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		//�����Ի��޳���������б�
		request.setAttribute("wczzjeList", knsdcSerivce.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("sjdcmc",request.getParameter("sjdcmc"));
		
		return mapping.findForward("knsrdPlsh");
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
	@SystemAuth(url = "xszz_knsrd_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = TransactionControlProxy.newProxy(new KnsrdService());
		
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
	public ActionForward knsrdView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		KnsrdForm model = service.getModel(myForm);
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			request.setAttribute("infoList", infoList);
			
			BeanUtils.copyProperties(myForm, model);
		}
		JcszService jcszService=new JcszService();
		JcszForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());

		//�������϶���ʾ����
		mkxxList = bdpzService.getBdpz(KNSRD);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("mkxxForm", myForm);
		KnsjgService knsjgService = new KnsjgService();
		List<HashMap<String, String>> knsInfoList = knsjgService
				.getKnsInfoList(myForm.getXh());
		request.setAttribute("knsInfoList", knsInfoList);
		if("11998".equals(Base.xxdm)){
			request.setAttribute("zf",request.getParameter("zf"));
		}
		if("12389".equals(Base.xxdm)){
			if(!StringUtils.isNull(myForm.getYlzd9())){
				String[] sqlyList  = service.getSqlyListByDms(myForm.getYlzd9().split(","));
				request.setAttribute("sqlyList",sqlyList);
			}
		}
		return mapping.findForward("knsrdView");
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
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
	private File print(KnsrdForm myForm,String guid,HttpServletRequest request) throws Exception{
		myForm.setGuid(guid);
		KnsrdService service = new KnsrdService();
		File file=null;
		KnsrdForm model = service.getModel(myForm);
		if(StringUtils.equals("12389", Base.xxdm)){
			String ylzd9 = model.getYlzd9();
			if(!StringUtils.isNull(ylzd9)){
				String[] temp = ylzd9.split(",");
				String[] sqlyArr = service.getSqlyListByDms(temp);
				String sqly = Arrays.toString(sqlyArr);
				sqly=sqly.substring(1,sqly.lastIndexOf("]"));
				model.setSqly(sqly);
			}
		}
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
			KnsdcService knsdcService = new KnsdcService();
			file=service.printForWord(xsjbxx,infoList,model,knsdcService.getKnsdcList(), model.getRddc(),request);
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
	public ActionForward knsrdShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		User user = getUser(request);
		KnsrdService service = new KnsrdService();
		Map<String,Object> shqkInfo = service.getShqkInfo(user);
		
		request.setAttribute("shqkInfo", shqkInfo);
		return mapping.findForward("knsrdShqk");
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
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		if(StringUtils.equals("12389", Base.xxdm)){
			for(int i=0;i<resultList.size();i++){
				HashMap<String,String> map = resultList.get(i);
				String ylzd9 = map.get("ylzd9");
				if(!StringUtils.isNull(ylzd9)){
					String[] temp = ylzd9.split(",");
					String[] sqlyArr = service.getSqlyListByDms(temp);
					String sqly = Arrays.toString(sqlyArr);
					sqly=sqly.substring(1,sqly.lastIndexOf("]"));
					map.put("sqly",sqly);
				}
				resultList.set(i,map);
			}
		}
		
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
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		model.setShzt(shlx);
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListSh(model,user);//��ѯ�����м�¼������ҳ
		if(StringUtils.equals("12389", Base.xxdm)){
			for(int i=0;i<resultList.size();i++){
				HashMap<String,String> map = resultList.get(i);
				String ylzd9 = map.get("ylzd9");
				if(!StringUtils.isNull(ylzd9)){
					String[] temp = ylzd9.split(",");
					String[] sqlyArr = service.getSqlyListByDms(temp);
					String sqly = Arrays.toString(sqlyArr);
					sqly=sqly.substring(1,sqly.lastIndexOf("]"));
					map.put("sqly",sqly);
				}
				resultList.set(i,map);
			}
		}
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
		
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		
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
		
		request.setAttribute("path", "xszz_knsrd_shtj.do");
		return mapping.findForward("studentsList");
	}
	
	/**
	 * 
	 * @����:��������������ͼ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-19 ����10:41:50
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
	public ActionForward printLct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		File file=print(request);
		FileUtil.outputWord(response, file);
				
		return null;
	}
	
	
	private synchronized File print(HttpServletRequest request) throws Exception{
		return FreeMarkerUtil.getWordFile(null, Constants.TEP_DIR + "xszz", "knssqlct.xml", FreeMarkerUtil.getFileName("��������ͼ"));
	}
	
	/**
	 * @����: �����Ի�_������һ�����ͨ�����Ժϵ���������������ı���
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-10-19 ����08:18:37
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
	public ActionForward viewYxKnsfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm model = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			List<HashMap<String,String>> resultList = service.getXyKnsfp (model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("viewYxKnsfp");
	}
	
	/**
	 * 
	 * @����: ��ʦ����Ի��ǼǱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-16 ����11:24:18
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
	public ActionForward printDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		File wordFile = printWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����: ��ʦ����Ի���ȡwordb���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-16 ����11:39:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File printWord(KnsrdForm myForm,HttpServletRequest request) throws Exception{
		String lx = request.getParameter("lx");
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		KnsrdForm model = service.getModel(myForm);
		data.put("rs",model );
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(model.getXh());
		data.putAll(xsxxMap);
		data.put("nowdate", GetTime.getTimeByFormat("yyyy-mm-dd"));
		try{
			if("bgs".equals(lx)){
				ResourceUtils.getFile(Constants.TEP_DIR+"xszz/jtbgs_10346.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xszz","jtbgs_10346.xml",FreeMarkerUtil.getFileName(xsxxMap.get("xm")+"_���ⱨ����"));
			}else if("lxs".equals(lx)){
				ResourceUtils.getFile(Constants.TEP_DIR+"xszz/lxsbs_10346.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xszz","lxsbs_10346.xml",FreeMarkerUtil.getFileName(xsxxMap.get("xm")+"_�����걨��"));
			}else{
				logger.info("��ʦ���޴����͵ǼǱ�");
				return null;
			}
			
		}catch (Exception e) {
			logger.info(e);
		}
		return file;
	}
	
	/**
	 * 
	 * @����: ��ʦ����Ի��ǼǱ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-16 ����11:25:37
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
	public ActionForward printDjbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setGuid(values[i]);
				File file = printWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:��ӡ������������Ϣ���㽭ͬ�ÿƼ�ְҵѧԺ��
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
	public ActionForward viewPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdForm myForm = (KnsrdForm) form;
		KnsrdService service = new KnsrdService();
		KnsrdForm model = service.getModel(myForm);
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("xsxx", xsjbxx);
			request.setAttribute("csrq", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));
			
			//������Ϣ
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getGuid());
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkmodel = jtqkService.getModel(model.getXh());
			request.setAttribute("jtrjsr", jtqkmodel==null ? "" : jtqkmodel.getJtrjsr());
			request.setAttribute("knsmodel", model);
			request.setAttribute("sqrdly", HtmlUtil.xmlZy(model.getSqly()));
			request.setAttribute("spxxInfo", spxxInfo);
			BeanUtils.copyProperties(myForm, model);
		}
		return mapping.findForward("viewPrint");
	}
	
}
