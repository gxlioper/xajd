/**
 * @����: ѧ����Ʒ(1)��
 * @���ڣ� 2018-7-3 ����05:01:26 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.rysq;

import java.io.File;
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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����_��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-7-3 ����05:01:26 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RysqAction extends SuperAction{
	private static final String url = "xpjpy_wdpj_rysq.do";
	private static final String PJPY = "pjpy";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*ѧ��������Ϣ��ʾ����*/
		jbxxList = bdpzService.getJbxxpz(PJPY);
	}
	private RysqService service = new RysqService();
	
	/**
	 * @����: �����������ҳ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-3 ����05:38:53
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
	@SystemLog(description = "��������������-�ҵ�����-��������-��ѯҳ��")
	public ActionForward getRysqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		/*����������Ϣ*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		
		/*Ĭ�ϲ�ѯ����{xn}*/
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		request.setAttribute("searchTj", searchModel);
		
		/*����path*/
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rysqList");
	}
	
	/**
	 * @����: ��ѯ�б���Json����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-3 ����08:27:31
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
	@SystemLog(description = "��������������-�ҵ�����-��������-��ѯҳ��Json����")
	public ActionForward getRysqListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RysqForm model = (RysqForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		/*��ѯ������JSON����*/
		List<HashMap<String, String>> resultList = service.getPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: ����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����09:28:08
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
	@SystemLog(description = "��������������-�ҵ�����-��������-����ҳ��")
	public ActionForward rysqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RysqForm model = (RysqForm)form;
		/*�û�����*/
		User user = getUser(request);
		
		/*�����ѧ���û�ȡ��¼�û����������ѧ���û�����ѧ��ѡ��*/
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		/*ѧ��������Ϣ*/
		if (!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			
			/*��ȡ��ѧ�����һ��������Ϣ*/
			HashMap<String,String> latestSqxx = service.getLatestSqxx(xh);
			request.setAttribute("latestSqxx",latestSqxx);
		}
		/*�Զ����*/
		request.setAttribute("jbxxList", jbxxList);
		/*����������Ϣ*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		/*����path*/
		String path = "xpjpy_rysq.do?method=rysqAdd";
		request.setAttribute("path", path);
		return mapping.findForward("rysqAdd");
	}
	
	
	/**
	 * @����: ��������_���Զ����ؿ��ŵ����������ߡ����ѡ�����������ƺš�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����11:54:32
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
	@SystemLog(description = "��������������-�ҵ�����-��������-����ѡ��")
	public ActionForward selectRyxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm model = (RysqForm)form;
		Map<String,Object> resultMap = service.getRysqInfoList(model.getXh());
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("selectRyxm");
	}
	
	
	/**
	 * @����: �������뱣�桢�ύ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����04:19:44
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
	@SystemLog(description="������������-�ҵ�����-��������-������Ŀ����:{xmdms},ѧ��:{xh},����ˮƽ:{wysp},����绰:{ssdh},������Ṥ��ְ��:{gzzw}")
	public ActionForward saveRysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm model = (RysqForm)form;
		String[] xmdm = request.getParameterValues("xmdms");
		/*��֤����*/
		if("submit".equals(model.getType())){
			HashMap<String,String> checkMap = new HashMap<String,String>();
			for (int i = 0; i < xmdm.length; i++) {
				model.setXmdm(xmdm[i]);
				checkMap = checkRs(model);
			}
			if("true".equals(checkMap.get("isXmrsOut"))){
				response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
				return null;
			}
		}
		/*�û�*/
		User user = getUser(request);
		model.setSqr(user.getUserName());
		/*��������*/
		boolean result = service.saveRysq(xmdm, model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �������뱣��checkRs,��֤����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����04:30:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	@SystemLog(description="������������-�ҵ�����-��������-������֤����")
	private HashMap<String, String> checkRs (RysqForm model)throws Exception{
		XmsqService xmsqService = new XmsqService();
		/*����������Ϣ*/
		CsszDao csszDao = new CsszDao();
		CsszForm csszModel = csszDao.getModel();
		/*��ȡ�������õ�ѧ�꣬����model*/
		model.setXn(csszModel.getXn());
		String isXmrsOut = "false";
		String rsOutMsg = "";
		HashMap<String, String> checkMap = new HashMap<String, String>();
		/*������Ŀ�����ѯ1����¼*/
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> xmMap = xmwhService.getDataById(model.getXmdm());
		/*��ȡ1��ѧ����ѧ�š�ѧԺ����*/
		HashMap<String, String> xsxxMap = service.getPjxmXsxxMap(model);
		
		/* ��Ϊ�����������������ͽ����������в�ͬ����ѯ�����������������ƺ���Ŀ
		 * 1����ѧ��������Ŀ
		 * 2�����������ƺ�
		 * 3�����������ñ������zzme��Ϊ��*/
		List<String> rychList = service.getRychList(model);
		if (rychList.contains(xmMap.get("xmmc"))) {
			/*ѧ�������xmdm��ѧ�������xydmƴ����Ϊ������xmdm+xydm*/
			String xmxx = xmMap.get("xmdm") + xsxxMap.get("xydm");
			/*��Ŀ����������*/
			String ysqrs = xmsqService.getYsqXs(xmxx);
			/*��Ŀ��������*/
			String rssx = xmsqService.getPjxmRsxxsx(xmxx);
			if (Integer.parseInt(ysqrs) + 1 > Integer.parseInt(rssx)) {
				isXmrsOut = "true";
				rsOutMsg += xmMap.get("xmmc") + " ������������" + rssx + "��<br/>";
			}
		}
		checkMap.put("isXmrsOut", isXmrsOut);
		checkMap.put("rsOutMsg", rsOutMsg);
		return checkMap;
	}
	
	
	
	/**
	 * @����: ���������޸�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-6 ����09:42:34
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
	@SystemLog(description = "��������������-�ҵ�����-��������-�޸�ҳ��")
	public ActionForward rysqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm rysqForm = (RysqForm)form;
		RysqForm model = service.getModel(rysqForm);
		XmsqService xmsqServcie = new XmsqService();
		
		if(model != null){
			BeanUtils.copyProperties(rysqForm, StringUtils.formatData(model));
			/*ѧ��������Ϣ*/
			HashMap<String,String> xsjbxx = xmsqServcie.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*����ѧ�Ų�ѯѧ������������Ϣ*/
			HashMap<String, String> cpbjxx = xmsqServcie.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*������Ŀ��Ϣ*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		}
		request.setAttribute("jbxxList", jbxxList);	
		return mapping.findForward("rysqUpdate");
	}
	
	/**
	 * @����: �޸ı���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-7 ����02:47:16
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
	@SystemLog(description="��������������-�ҵ�����-��������-�����޸�-��Ŀ����:{xmdm},����ˮƽ:{wysp},����绰:{ssdh}")
	public ActionForward saveRysqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RysqForm model = (RysqForm)form;
		TjszService tjszService = new TjszService();
		boolean result = false;
		if("submit".equalsIgnoreCase(model.getType())||"tj".equalsIgnoreCase(model.getType())){
			/*��֤����*/
			HashMap<String,String> checkMap = checkRs(model);
			if("true".equals(checkMap.get("isXmrsOut"))){
				response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
				return null;
			}
			
			/*ѧ�������ύ*/ 
			if("tj".equalsIgnoreCase(model.getType())){
				String values = request.getParameter("values");
				model.setId(values);
			}
			
			/*���״̬������ˡ�*/
			model.setShzt(Constants.YW_SHZ);
			
			/*======��֤����Begin======*/
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(), model.getXh());
			/*У���������������ز�������������Ŀ���ơ�*/
			CheckCondition checkCondition = new CheckStudentCondition();
			/*�Ƿ�����ȫ����������*/
			boolean conditionsAllOk = checkCondition.checkConditionBoolean(model.getXh(), conditions);
			if(!conditionsAllOk){
				response.getWriter().print(getJsonMessage("��������������ȷ�ϣ�"));
				return null;
			}
			/*======��֤����End======*/
			
			result = service.runUpdate(model);
			
			XmwhDao xmwhDao = new XmwhDao();
			XmwhForm xmwhModel = xmwhDao.getModel(model.getXmdm());
			/*��ȡ�������*/
			String splc = xmwhModel.getShlc();
			ShlcInterface shlc = new CommShlcImpl();
			if(result){
				result = shlc.runSubmit(model.getId(), splc,model.getXh(),"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
			}
		}else{
			 result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if(!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ��������ɾ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-10 ����09:37:12
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
	@SystemLog(description="��������������-�ҵ�����-��������-ɾ��VALUES��{values}")
	public ActionForward rysqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*��ȡֵ*/
		String values = request.getParameter("values");
		/*���ж�*/
		if(!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			/*�׳������Ѿ���JS������*/
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����: �鿴
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-10 ����09:54:24
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
	@SystemLog(description="��������������-�ҵ�����-��������-�鿴")
	public ActionForward rysqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm rysqForm = (RysqForm)form;
		RysqForm model = service.getModel(rysqForm);
		if(model != null){
			XmsqService xmsqService = new XmsqService();
			BeanUtils.copyProperties(rysqForm, StringUtils.formatBean(model));
			/*ѧ��������Ϣ*/
			HashMap<String,String> xsjbxx = xmsqService.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*����ѧ�Ų�ѯѧ������������Ϣ*/
			HashMap<String, String> cpbjxx = xmsqService.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*������Ŀ��Ϣ*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			/*��˼�¼�б�*/
			List<HashMap<String,String>> spjlList = xmsqService.getSpjlList(model.getId());
			request.setAttribute("spjlList", spjlList);
			/*��˼�¼*/
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
			//У������������У����
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
			request.setAttribute("checkResult", results);
			
			request.setAttribute("mkxxForm", StringUtils.formatData(model));
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("rysqView");
	}
	
	/**
	 * @����: �������볷��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-10 ����10:40:51
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
	@SystemLog(description="��������������-�ҵ�����-��������-����VALUES��{values}")
	public ActionForward rysqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		result = service.cancleRecord(values,lcid);
		if(result){
			RysqForm model = (RysqForm)form;
			model.setId(values);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �������뵼��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-10 ����03:19:49
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
	@SystemLog(description="��������������-�ҵ�����-��������-���� ")
	public ActionForward rysqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm model = (RysqForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		/*��ѯ�����м�¼������ҳ*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
		/*�������ܴ���*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*��ǰ����Ա*/
		exportModel.setZgh(user.getUserName());
		/*��������*/
		exportModel.setDataList(resultList);
		/*���õ�ǰ�������ܱ��*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*���ɵ����ļ�*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
}
