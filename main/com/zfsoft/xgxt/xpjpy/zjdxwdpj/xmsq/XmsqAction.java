/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-5-12 ����04:59:38 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import xgxt.utils.date.DateUtils;

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
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.bbwh.BbwhService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh.ZcwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����_��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-5-12 ����05:00:34 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: 2018-01-04 
 */

public class XmsqAction extends SuperAction {
	private static final String url = "xpjpy_wdpj_pjsq.do";
	private static final String PJPY = "pjpy";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*ѧ��������Ϣ��ʾ����*/
		jbxxList = bdpzService.getJbxxpz(PJPY);
	}
	private XmsqService service = new XmsqService();
	
	/**
	 * @����: ��ת����Ŀ�����б�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2017-5-12 ����06:01:44
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
	@SystemLog(description = "��������������-�ҵ�����-��Ŀ����-��ѯҳ��")
	public ActionForward getXmsqList(ActionMapping mapping, ActionForm form,
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
		String path = "xpjpy_wdpj_pjsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsqList");
	}
	
	/**
	 * @����: ��ȡ��Ŀ�����б�JSON����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-15 ����11:11:25
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
	@SystemLog(description = "��������������-�ҵ�����-��Ŀ����-��ѯҳ��Json����")
	public ActionForward getXmsqListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm) form;
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
	 * @����: ��Ŀ����ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-15 ����07:33:23
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
	@SystemLog(description = "��������������-�ҵ�����-��Ŀ����-����ҳ��")
	public ActionForward xmsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		/*�û�����*/
		User user = getUser(request);
		/*�����ѧ���û�ȡ��¼�û����������ѧ���û�����ѧ��ѡ��*/
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
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
		String path = "xpjpy_xmsq.do?method=xmsqAdd";
		request.setAttribute("path", path);
		return mapping.findForward("xmsqAdd");
	}
	
	/**
	 * @����: ��Ŀ����_���Զ����ؿ��ŵĽ�����ߡ����ѡ�����뽱�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-15 ����08:22:38
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
	public ActionForward selectPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		Map<String,Object> resultMap = service.getXmsqInfoList(model.getXh());
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("selectPjxm");
	}
	
	/**
	 * @����: ������Ŀ���룬��ȡ���ɼ��������Ľ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����11:23:12
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
	@SystemLog(description = "��������������-�ҵ�����-��������-��ò�ѯ")
	public ActionForward getBjdxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xmdm = request.getParameter("xmdm");
		List<HashMap<String, String>> resultList = service.getBjdxm(xmdm);
		JSONArray data = JSONArray.fromObject(resultList);
		response.getWriter().print(data);
		return null;
	}
	
	/**
	 * @����: ��Ŀ���뱣��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����03:10:23
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
	public ActionForward saveXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		String[] xmdm = request.getParameterValues("xmdms");
		/*��֤����*/
		if("submit".equals(model.getType())){
			HashMap<String,String> checkMap = new HashMap<String,String>();
			for (int i = 0; i < xmdm.length; i++) {
				model.setXmdm(xmdm[i]);
				checkMap = checkRs(model);
				/*���һ��ѧ��һ�����������ֻҪ��һ�������Ϲ���ģ���ô���н�����Ϣ�����ܽ��б���*/
				if("true".equals(checkMap.get("isXmrsOut"))){
					response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
					return null;
				}
			}
			
		}
		
		if("dgtj".equals(request.getParameter("flag"))){
			CsszService csszService = new CsszService();
			CsszForm csszForm = csszService.getCsszModel();
			/*�ж��Ƿ񲻿ɼ��*/
			boolean result = service.checkIsNotbkjd(xmdm[0], csszForm.getXn(),model.getXh());
			if(!result){
				/*���������*/
				String bkjdmc = service.getbkjdMc(xmdm[0]);
				String message = "����Ŀ��["+bkjdmc+"]���ɼ�ã�";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		/*�û�*/
		User user = getUser(request);
		model.setSqr(user.getUserName());
		/*��������*/
		boolean result = service.saveXmsq(xmdm, model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ��Ŀ���뱣��checkRs,��֤����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����03:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	private HashMap<String, String> checkRs (XmsqForm model)throws Exception{
		/*����������Ϣ*/
		CsszDao csszDao = new CsszDao();
		CsszForm csszModel = csszDao.getModel();
		/*��ȡ�������õ�ѧ�꣬����model*/
		model.setXn(csszModel.getXn());
		String isXmrsOut = "false";
		String rsOutMsg = "";
		HashMap<String, String> checkMap = new HashMap<String, String>();
		/*������Ŀ�����ѯ��¼*/
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> xmMap = xmwhService.getDataById(model.getXmdm());
		/*������ȡ������Ϣ��ѧ����Ϣ*/
		HashMap<String, String> xsxxMap = service.getPjxmXsxxMap(model);
		/*��ȡ���������Ŀ������{xg_pjpy_new_jesxxmb}*/
		List<String> xzjxList = service.getXzjx();
		/*��ѯ���������Ŀ�������Ŀ�Ƿ������Ŀ������������*/
		if (xzjxList.contains(xmMap.get("xmmc"))) {
			/*��Ŀ����������*/
			String xmxx = xmMap.get("xmdm")+xsxxMap.get("xydm");
			String ysqrs = service.getYsqXs(xmxx);
			/*��Ŀ��������*/
			String rssx = service.getPjxmRsxxsx(xmxx);
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
	 * @����: ��Ŀ�����޸�ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����01:45:53
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
	public ActionForward xmsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm xmsqForm = (XmsqForm)form;
		XmsqForm model = service.getModel(xmsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(xmsqForm, StringUtils.formatData(model));
			/*ѧ��������Ϣ*/
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*����ѧ�Ų�ѯѧ������������Ϣ*/
			HashMap<String, String> cpbjxx = service.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*������Ŀ��Ϣ*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		}
		request.setAttribute("jbxxList", jbxxList);	
		return mapping.findForward("xmsqUpdate");
	}
	
	/**
	 * @����: �޸ı���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2017-5-19 ����03:11:07
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
	public ActionForward saveJxsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
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
			if (result) {
				result = shlc.runSubmit(model.getId(), splc,model.getXh(),"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
			}
		}else{
			 result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ɾ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-18 ����10:27:14
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
	@SystemLog(description="������������-�ҵ�����-��������-ɾ��VALUES��{values}")
	public ActionForward xmsqDelete(ActionMapping mapping, ActionForm form,
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
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-18 ����12:22:28
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
	public ActionForward xmsqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XmsqForm xmsqForm = (XmsqForm)form;
		XmsqForm model = service.getModel(xmsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(xmsqForm, StringUtils.formatBean(model));
			/*ѧ��������Ϣ*/
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*����ѧ�Ų�ѯѧ���Ĳ����༶*/
			HashMap<String, String> cpbjxx = service.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*������Ŀ��Ϣ*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			/*��˼�¼�б�*/
			List<HashMap<String,String>> spjlList = service.getSpjlList(model.getId());
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
		return mapping.findForward("xmsqView");
	}
	
	
	/**
	 * @����: ��Ŀ�����ύ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-22 ����09:09:14
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
	@SystemLog(description="������������-�ҵ�����-��������-�����ύ����-VALUES:{values}")
	public ActionForward xmsqSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		TjszService tjszService = new TjszService();
		ShlcInterface shlcInterface = new CommShlcImpl();
		boolean result = true;
		/*������������*/
		boolean isXmrsOut = false;
		String rsOutMsg = "";
		/*��ȡֵ*/
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			int okNum = 0;
			/*������������ѧ��*/
			StringBuilder notOkStu = new StringBuilder();
			HashMap<String, List<HashMap<String,String>>> notOkStuMap = new HashMap<String, List<HashMap<String,String>>>();
			/*����������Ϣ��id��ȡѧ��������Ϣ��id��xmdm��xh��xm��xmmc��shlc*/
			List<HashMap<String,String>> dataList = service.getPjxmXsxxList(values.split(","));
			/*�ύ�ĸ�ѧԺ����Ŀ����������{δ�ύ�����ύ}*/
			List<HashMap<String,String>> xmrsList = service.getPjxmRsxx(values.split(","));
			/*��ѯ��ѧ��������*/
			List<String> xzjxList = service.getXzjx();
			String[] xmArr = new String[xmrsList.size()];
			for (int i = 0; i < xmrsList.size(); i++) {
				xmArr[i]=xmrsList.get(i).get("xmdm")+xmrsList.get(i).get("xydm");
			}
			
			for (int i = 0; i < xmrsList.size(); i++) {
				/*�ύ����*/
				if(xzjxList.contains(xmrsList.get(i).get("xmmc"))){
					String xmtjrs = xmrsList.get(i).get("xmtjrs");
					/*��Ŀ����������*/
					String ysqrs = service.getYsqXs(xmArr[i]);
					/*��Ŀ��������*/
					String rssx = service.getPjxmRsxxsx(xmArr[i]);
					if(Integer.parseInt(ysqrs)+Integer.parseInt(xmtjrs)>Integer.parseInt(rssx)){
						isXmrsOut=true;
						rsOutMsg+=xmrsList.get(i).get("xmmc")+" ������������"+rssx+"��<br/>";
					}
				}
			}
			
			if(isXmrsOut){
				response.getWriter().print(getJsonMessage(rsOutMsg));
				return null;
			}
			
			/*��ѯ�ύ��Ŀ����*/
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> dataMap = dataList.get(i);
				String id = dataMap.get("id");
				String xmdm = dataMap.get("xmdm");
				String xmmc = dataMap.get("xmmc");
				String xh = dataMap.get("xh");
				String xm = dataMap.get("xm");
				String splc = dataMap.get("shlc");
				
				/*========== ��֤�������� begin ============*/
				List<HashMap<String, String>> conditions = tjszService.getTjszGl(xmdm, xh);
				/*У���������������ز�������������Ŀ���ơ�*/
				CheckCondition checkCondition = new CheckStudentCondition();
				/*�Ƿ�����ȫ����������*/
				boolean conditionsAllOk = checkCondition.checkConditionBoolean(xh, conditions);
				/*========== ��֤�������� end ============*/
				/*========== ���ɼ�ý��� begin ============*/
				List<HashMap<String, String>> bjdxmList = service.getBjdxm(xmdm);
				if(conditionsAllOk && bjdxmList.size() > 0){
					/*=========== ������XX������������ý��� begin ==========*/
					Map<String,Object> xmsqInfoMap = service.getXmsqInfoList(xh);
					List<HashMap<String, String>> ysqList = (List<HashMap<String, String>>) xmsqInfoMap.get("ysqList");
					if(ysqList != null && ysqList.size() > 0){
						for (HashMap<String, String> bjdxmMap : bjdxmList) {
							for (HashMap<String, String> ysqMap : ysqList) {
								if(bjdxmMap.get("xmdm").equals(ysqMap.get("xmdm"))){
									conditionsAllOk = false;
									break;
								}
							}
							if(!conditionsAllOk){
								break;
							}
						}
					}
					/*=========== ������XX������������ý��� end ==========*/
					/*=========== �ý�����XX����ͬʱ���� begin ==========*/
					if(conditionsAllOk){
						for (int j = i + 1; j < dataList.size(); j++) {
							HashMap<String, String> dataJ = dataList.get(j);
							String xhJ = dataJ.get("xh");
							String xmdmJ = dataJ.get("xmdm");
							if(xh.equals(xhJ)){
								for (HashMap<String, String> bjdxmMap : bjdxmList) {
									if(bjdxmMap.get("xmdm").equals(xmdmJ)){
										conditionsAllOk = false;
										break;
									}
								}
								if(!conditionsAllOk){
									break;
								}
							}
						}
					}
					/*=========== �ý�����XX����ͬʱ���� end ==========*/
				}
				/*========== ���ɼ�ý��� end ============*/
				
				
				if(conditionsAllOk){
					model.setId(id);
					model.setShzt(Constants.YW_SHZ);
					result = service.runUpdate(model);
					if (result) {
						result = shlcInterface.runSubmit(id,splc,xh,"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
					}
					if (result) {
						okNum++;
					}
				}else{
					HashMap<String,String> mapTemp = new HashMap<String,String>();
					mapTemp.put("xh", xh);
					mapTemp.put("xm", xm);
					mapTemp.put("xmmc", xmmc);
					List<HashMap<String,String>> listTemp = notOkStuMap.get(xmdm);
					if(listTemp == null){
						listTemp = new ArrayList<HashMap<String,String>>();
						notOkStuMap.put(xmdm, listTemp);
					}
					listTemp.add(mapTemp);
				}
			}
			
			Set<String> keys = notOkStuMap.keySet();
			for(String key : keys){
				List<HashMap<String,String>> temp = notOkStuMap.get(key);
				for (int i = 0; i < temp.size(); i++) {
					if(i == 0){
						notOkStu.append(temp.get(0).get("xmmc") + "��<br/>");
					}
					notOkStu.append(temp.get(i).get("xh") + " " + temp.get(i).get("xm") + "<br/>");
				}
			}
			String resultMsg = "�ύ�ɹ�"+okNum+"����";
			if(dataList.size() - okNum > 0){
				resultMsg += "�����������ļ�¼��<br/>" + notOkStu.toString().substring(0, notOkStu.toString().length() - 1);
			}
			String message = result ? resultMsg : MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	
	/**
	 * @����: ��Ŀ���볷��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����04:35:32
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
	@SystemLog(description="������������-�ҵ�����-��������-����VALUES��{values}")
	public ActionForward xmsqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		result = service.cancleRecord(values,lcid);
		if(result){
			/*����ҵ��״̬Ϊ��δ�ύ��*/
			XmsqForm model = new XmsqForm();
			model.setId(values);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����05:44:58
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
	@SystemLog(description="������������-�ҵ�����-��������-����")
	public ActionForward xmsqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);User user = getUser(request);
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
	
	/**
	 * @����: ������ӡ����ǼǱ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-13 ����11:49:08
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
	@SystemLog(description="������������-�ҵ�����-��������-���صǼǱ�")
	public ActionForward getWordForid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XmsqForm xmsqForm = (XmsqForm)form;
		XmsqService service = new XmsqService();
		XmsqForm model = service.getModel(xmsqForm);
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: ������ӡ����ǼǱ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-14 ����08:33:56
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
	@SystemLog(description="������������-�ҵ�����-��������-�������صǼǱ�")
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String value = request.getParameter("value");
		XmsqService service = new XmsqService();
		XmsqForm model = null;
		XmsqForm modelForm = null;
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				modelForm = new XmsqForm();
				modelForm.setId(values[i]);
				model = service.getModel(modelForm);
				File file = getWord(model);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: ���ģ����������word�ļ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-14 ����09:03:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmsqForm
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getWord(XmsqForm xmsqForm) throws Exception {
		
		/**����һЩ��Ҫ�õ�*/
		Map<String,Object> data = new HashMap<String,Object>();
		XmsqForm model = xmsqForm;
		XmsqService service = new XmsqService();
		PjjgService pjjgService = new PjjgService();
		
		/**��model�л�ȡѧ�ţ���������ʹ��*/
		String xh = model.getXh();
		/**����*/
		HashMap<String, String> bbMap = null;
		
		/**��model��Ϊ�յ�ʱ����������в���*/
		if(model != null){
			
			/*��ȡѡȡ���ݵ���Ŀ����*/
			String xmdm = model.getXmdm();
			/*�ж�xmdm�Ƿ�Ϊ��*/
			if(StringUtils.isNull(xmdm)){
				throw new SystemException("��ǰ��Ŀ����Ϊ�գ����������صǼǱ�");
			}
			
			/**ȡ������Ϣ*/
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				/*������Ŀ���ƺ�ѧ���ѯ��¼��ϸ��Ϣ*/
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn());
				/*�ж��ǻ������Ŀ������*/
				if(xmMap != null){
					model.setXmdm(xmMap.get("xmdm"));
					/*��Ϊ�����ͱ�һ����new��ǰ��BbwhService�Ϳ����ˣ�*/
					BbwhService bbwhService = new BbwhService();
					/*ȡ��Ӧ�ĵǼǱ�*/
					bbMap = bbwhService.getDataById(xmMap.get("djb"));
				}
			}
			
			if(null == bbMap || 0 == bbMap.size()){
				/*��ѯ���������������Ϣ*/
				throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
			}
			
			/**����ѧ�Ų�ѯѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.put("rs", xsjbxx);
			/*�����������£����磺2017��01��*/
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));
			/*�����������£����磺2017.01*/
			String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
			xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));
			/*��ѧ�������£����磺2017��12��*/
			xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));

			//����Ա
			String zzmmmc = xsjbxx.get("zzmmmc");
			if(zzmmmc != null){
				if (zzmmmc.equals("�й���������Ա") || zzmmmc.equals("�й�������Ԥ����Ա")) {
					data.put("dty", "��Ա");
				}else if(zzmmmc.equals("�й�����������������Ա")){
					data.put("dty", "��Ա");
				}
			}

			/**�꼶רҵ����*/
			String njzyrs = service.getNjzyrs(xsjbxx.get("nj"),xsjbxx.get("zydm"));
			data.put("njzyrs",njzyrs);

			/**ѧ����Ƭ��Ϣ*/
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			data.put("photo", photo);
			
			/**�ֽ����֤��begin*/
			String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
			for (int i = 0,j = xssfzh.length(); i < j; i++) {
				xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
			}
			
			/**form�����ֵ*/
			/*��ѡ��Ŀ����������*/
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));
			data.put("grxxjl",HtmlUtil.xmlZy(model.getGrxxjl()));
			data.put("cjkyqk",HtmlUtil.xmlZy(model.getCjkyqk()));
			data.put("dwrs",HtmlUtil.xmlZy(model.getDwrs()));

			/*��ѡ��Ŀ����Ŀ����*/
			data.put("xmmc", model.getXmmc());
			/*��ѡ��Ŀ������ѧ��*/
			data.put("currXn", model.getXn());
			/*��ѡ��Ŀ��������Ϣ*/
			data.put("xmxx", model);
			/*��ǰѧУ����*/
			data.put("xxmc", Base.xxmc);
			
			/**��ǰ�ꡢ�¡���*/
			/*��ǰ��,���磺2017*/
			data.put("currY", DateUtils.getYear());
			/*��ǰ��,���磺02*/
			data.put("currM", DateUtils.getMonth());
			/*��ǰ��,���磺01*/
			data.put("currD",DateUtils.getDayOfMonth());
			
			/**����ְ��*/
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			
			/**����ѧ����ͥ������Ϣ*/
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			data.put("jtqk", jtqkmodel);//��ͥ���
			
			/**���ؼ�ͥ��Ա��Ϣ*/
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
			data.put("jtcyxxList", jtcyxxList);
			int size=(5 - jtcyxxList.size())<0?0:(5 - jtcyxxList.size());
			data.put("blankList", getBlankList(size));
			
			/**�������϶�����Ϣ*/
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
			/*�϶�����*/
			data.put("rddc", knsjg.get("rddc") == null ? "" : knsjg.get("rddc"));
			
			/**�������϶����Σ������ѡ�����������*/
			KnsdcService knsdcService = new KnsdcService();
			data.put("knsdcList", knsdcService.getKnsdcList());
			
			/**��Ŀ����ѭ����Ŀǰ��û��������ã�ͨ��ת�����ģ�*/
			data.put("xmxzmc", "");
			XmwhService xmwhService = new XmwhService();
			List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
			for (HashMap<String, String> xmxzMap : xmxzList) {
				if (xmxzMap.get("dm").equals(model.getXzdm())){
					data.put("xmxzmc", xmxzMap.get("mc"));
					break;
				}
			}
			
			/**�۲��*/
			ZcwhService zcwhService = new ZcwhService();
			HashMap<String, String> zcf = zcwhService.getZczfByXh(xh, model.getXn());
			data.put("zcf", zcf);
			
			/**����ѧ�ż���ѧ��ѧ��ɼ�*/
			List<HashMap<String,String>> cjList = xsxxService.getCjList(xh,model.getXn(),model.getXq());
			/*��ʽ������*/
			cjList = pjjgService.formatForDjb(cjList);
			data.put("cjList", cjList);
			
			/**��ȡƽ������*/
			String pjfs = pjjgService.getAverage(cjList);
			xsjbxx.put("pjfs",pjfs );
			
			/**��ȡ������ɼ�����*/
			String bjgcjs = pjjgService.getBjgcjNum(xh, model.getXn(), model.getXq());
			xsjbxx.put("bjgcjs",bjgcjs );

			/**�������ڣ��������ƣ��佱��λ�õ�̫�࣬������ͨ��*/
			List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
			pjjgService.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
			data.put("pjjgListhjqk", pjjgListhjqk);
			int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
			data.put("blankListhjqk", getBlankList(size1));
			
			String[] xnArr = model.getXn().split("-");
			if(xsjbxx.get("csrq")!=null){
				String[] csArr = xsjbxx.get("csrq").split("\\D");
				if(csArr != null&&csArr.length == 3){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
					data.put("csr1", csArr[2]);
				}else if (csArr != null&&csArr.length ==2){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
				}else if (csArr != null&&csArr.length ==1){
					data.put("csn1", csArr[0]);
				}
				if(xnArr.length == 2){
					data.put("qsxn", xnArr[0]);
					data.put("zhxn", xnArr[1]);
				}else if (xnArr.length == 1){
					data.put("qsxn", xnArr[0]);
				}
				if(csArr != null&&csArr.length == 2){
					data.put("csn", csArr[0]);
					data.put("csy", csArr[1]);
				}else if (csArr != null&&csArr.length == 1){
					data.put("csn", csArr[0]);
				}
			}

			//ѧ��ƴ�ӣ����
			int uqsnx = Integer.parseInt(xnArr[0])-1;//such as:2016->2015
			int uzhxn = Integer.parseInt(xnArr[1])-1;//such as:2017->2016
			StringBuilder usxn = new StringBuilder();
			String upsxn1 = usxn.append(uqsnx+"-"+uzhxn).toString();//such as:2016-2017 ->2015-2016
			String sxnhlw = xnArr[0].substring(xnArr[0].lastIndexOf("/")+3, xnArr[0].lastIndexOf("/")+5);//such as:2016->16
			String zxnhlw = xnArr[1].substring(xnArr[1].lastIndexOf("/")+3, xnArr[1].lastIndexOf("/")+5);//such as:2017->17
			data.put("sxnhlw", sxnhlw);
			data.put("zxnhlw", zxnhlw);
			int ssxnhlw = Integer.parseInt(sxnhlw)-1;//such as:16->15
			int zxxnhlw = Integer.parseInt(zxnhlw)-1;//such as:17->16
			data.put("upqsxn", ssxnhlw);
			data.put("upzhxn", zxxnhlw);
			
			/**�༶����*/
			String bjrs = service.getBjrs(xsjbxx.get("bjdm"), model.getXn());
			xsjbxx.put("bjrs", bjrs);		
			
			/**��������Ϣ*/
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getId());
			data.put("yjshyj", spxxInfo.get("yjshyj"));
			data.put("ejshyj", spxxInfo.get("ejshyj"));
			data.put("sjshyj", spxxInfo.get("sjshyj"));
			
			/**����ѧ�Ų�ѯ*/
			List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoMapForDjb(xh);
			data.put("pjjg", pjjg);
			List<HashMap<String, String>> pjjgAll = pjjgService.getPjpyInfoList(xh);
			data.put("pjjgAll",pjjgAll);
			
			/**��ѯ�ɼ���Ϣ*/
			HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
			if(xxcjMap == null){
				xxcjMap = new HashMap<String,String>();
			}
			data.put("xxcjMap", xxcjMap);
			
			/**����ļ���ʽ������*/
			File wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			return wordFile;
		}
		return null;
	}

	/**
	 * @����: �����list
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-14 ����06:07:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param size
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBlankList(int size){
			
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
}
