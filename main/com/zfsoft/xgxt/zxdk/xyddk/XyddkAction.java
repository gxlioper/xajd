/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-25 ����03:29:36 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.syddk.SydkSqshService;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-������ѧ����
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-25 ����03:29:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyddkAction extends SuperAuditAction<XyddkModel, XyddkService> {
	 
	private static final String KNSRD = "knsrd";
	private static final String GJZXDK = "gjzxdk";
	private static final String GJZXDK_XAKJ = "gjzxdk_xakj";
	private ShlcInterface shlc = new CommShlcImpl();

	
	protected XyddkAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public XyddkAction(){
		this("zxdk_xyddk", "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
	}
	
	/**
	 * 
	 * @����: ���������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:37:15
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward dksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_gjdk_dksq.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		if(Globals.XXDM_ZJJDZYJSXY.equals(Base.xxdm)){
			searchModel.setSearch_tj_xn(new String[]{Base.afterXn});
		}else{
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("dksqList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ش��������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:38:22
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward getDksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:41:01
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel model = (XyddkModel) form;
		ZxdkCsszService csszService = new ZxdkCsszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			ZxdkCssz csszModel = new ZxdkCssz();
			csszModel= csszService.getModel();
			if (csszModel != null && "1".equals(csszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				
				request.setAttribute("openJtqk", jtqkModel == null);
				
			}
			//�����Ƽ���ѧ
			if("10704".equalsIgnoreCase(Base.xxdm)){								
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
				JtqkdcService jtqkService = new JtqkdcService();
				List<HashMap<String,String>> list = jtqkService.getJtcyList(model.getXh());
				xsjbxx.put("cys", String.valueOf(list.size()));
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		
		List<HashMap<String, String>> jbxxList;
		
		//�����Ƽ���ѧ���Ի�������Ϣ��ʾ�ֶ�
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		List<HashMap<String,String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("afterxn", Base.afterXn);
		request.setAttribute("mkxxForm", model);
		Calendar a=Calendar.getInstance();
		request.setAttribute("qsxn", a.get(Calendar.YEAR)+"");
		String path = "zxdkXyddk.do?method=dksq";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("dksq");
	}
	
	
	
	/**
	 * 
	 * @����: �޸Ĵ�������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-26 ����02:08:25
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		ZxdkCsszService csszService = new ZxdkCsszService();
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			ZxdkCssz csszModel = new ZxdkCssz();
			csszModel= csszService.getModel();
			if (csszModel != null && "1".equals(csszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				
				request.setAttribute("openJtqk", jtqkModel == null);
			}
			//�����Ƽ���ѧ
			if("10704".equalsIgnoreCase(Base.xxdm)){								
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
				
			}
		}
		List<HashMap<String,String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		
		List<HashMap<String, String>> jbxxList;
		//�����Ƽ���ѧ���Ի�������Ϣ��ʾ�ֶ�
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("mkxxForm", model);
		String path = "zxdkXyddk.do?method=xgDksq";
		request.setAttribute("path", path);
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("afterxn", Base.afterXn);
		request.setAttribute("cssz", csszService.getModel());
		/**
		 * ��ʦ����Ի�����
		 */
		if("10511".equals(Base.xxdm)){
			List<HashMap<String, String>> nddkList = service.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
			HashMap<String, String> jesxMap = service.getXsxxByHsd(model.getXh());
			request.setAttribute("jesx", jesxMap.get("jesx"));
		}
		this.saveToken(request);
		return mapping.findForward("xgDksq");
	}
	
	
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward ckDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//�����Ƽ���ѧ
			if("10704".equalsIgnoreCase(Base.xxdm)){
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		//�������϶���ʾ����
		
		List<HashMap<String,String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", myForm);
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		
		List<HashMap<String, String>> jbxxList;
		//�����Ƽ���ѧ���Ի�������Ϣ��ʾ�ֶ�
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		request.setAttribute("jbxxList", jbxxList);
		/**
		 * ��ʦ����Ի�����
		 */
		if("10511".equals(Base.xxdm)){
			List<HashMap<String, String>> nddkList = service.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
		}
		return mapping.findForward("ckDksq");
	}
	
	
	
	/**
	 * 
	 * @����: ��������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:37:30
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
	@SystemAuth(url = "zxdk_gjdk_dksh.do")
	public ActionForward dkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_gjdk_dksh.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		if(Globals.XXDM_ZJJDZYJSXY.equals(Base.xxdm)){
			searchModel.setSearch_tj_xn(new String[]{Base.afterXn});
		}else{
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("dkshList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ش�������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:39:06
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
	@SystemAuth(url = "zxdk_gjdk_dksh.do")
	public ActionForward getDkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:40:39
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
	@SystemAuth(url = "zxdk_gjdk_dksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		request.setAttribute("sjxx", service.getShlcInfo(myForm.getId(), myForm.getGwid(),  model.getSplcid()));

		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx;
			xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			//�����Ƽ���ѧ
			if("10704".equalsIgnoreCase(Base.xxdm)){
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
			}
			
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
        //�������϶���ʾ����
		List<HashMap<String,String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		
		
		List<HashMap<String, String>> jbxxList;
		//�����Ƽ���ѧ���Ի�������Ϣ��ʾ�ֶ�
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("jbxxList", jbxxList);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("dksh_zjdx");
		}
		/**
		 * ��ʦ����Ի�����
		 */
		if("10511".equals(Base.xxdm)){
			List<HashMap<String, String>> nddkList = service.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
			HashMap<String, String> jesxMap = service.getXsxxByHsd(model.getXh());
			request.setAttribute("jesx", jesxMap.get("jesx"));
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("dksh");
		
	}
	
	
	
	/**
	 * 
	 * @����: ɾ����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-26 ����03:30:45
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "��ѧ����-������ѧ����-��������-ɾ��values:{id}")
	public ActionForward delDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		boolean result = service.runDelete(new String[]{myForm.getId()}) > 0;
		/**
		 * ����ǻ���ʦ����ѧ,ɾ��xg_hsd_zxdk_ndkb�е�����
		 */
		if("10511".equals(Base.xxdm)){
			service.delNdkb(new String[]{myForm.getId()});
			//service.delFdb(new String[]{myForm.getId()});
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**��ѧ��ѧ���ѯ��¼����**/
	public ActionForward getCountByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;
		
		String count = service.getCountByXhAndXn(model);
		response.getWriter().print(count);
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-26 ����04:05:10
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward dcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ

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
	
	/**��ӡ�����**/
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String[] ids = request.getParameter("ids").split(",");
			
			if(null!=ids && ids.length == 1){//һ������
				File file=print(ids[0],request);
				FileUtil.outputWord(response, file);
			}else{//��������
				List<File> files = new ArrayList<File>();
				for(String id : ids){
					File file=print(id,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	
	
	private synchronized File print(String id,HttpServletRequest request) throws Exception{

		Map<String, Object> data = new HashMap<String, Object>();
		
		XyddkService service = getService();
		XyddkModel model = service.getModel(id);
		if(Base.xxdm.equalsIgnoreCase("10335") && xgxt.utils.String.StringUtils.isNotNull(model.getXzf())){
			model.setZsfdks(Integer.parseInt(model.getZsfdks())*Integer.parseInt(model.getXzf())+"");
			model.setXfdks(Integer.parseInt(model.getXfdks())*Integer.parseInt(model.getXzf())+"");
		}
		data.put("m", model);
		
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		if(Base.xxdm.equalsIgnoreCase("10335")){
			DkjgAction dkjg = new DkjgAction();
			data = dkjg.returnData_10335(data, xsjbxx, model);
			dkjg = null;
		}
		data.put("j", xsjbxx);
		if(Base.xxdm.equalsIgnoreCase("10335")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "zxdksqb_10335.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}
		return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "shhy_gjdk.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
	}
	
	
	
	/**
	 * 
	 * @����:����ID��ѯ������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-25 ����03:16:14
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
	public ActionForward dkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		XyddkService service = getService();
		
		HashMap<String, String> dkxxMap = service.getDkxxSq(id);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}
	
	/**
	 * ��ҪΪ���������ĸ��Ի�
	 */
	public ActionForward checkDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;
		
		//�鿴ѧ���Ƿ�������
		String count = service.getCountByXhAndXn(model);
		
		//��֤���������ѧ���Ƿ�������
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz cssz = csszService.getModel();
		if("1"==cssz.getXdKg()){
			model.setXn("");
			count = service.getCountByXhAndXn(model);
		}
		
		if("0".equals(count)){
			//�㽭��ѧ���Ի�
			if(StringUtils.isBlank(model.getXz())){
				model.setXz("0");
			}
			//�㽭��ѧ���Ի� ���򣺴�������=ʣ����ݣ��꼶-������ݣ�+ѧ��+13��     ������ܴ���20 ����20ȡ20
			
			//ʣ����� ������Ϊ����
			 Calendar a=Calendar.getInstance();
			int synf = Integer.parseInt(model.getNj())-Integer.parseInt(Calendar.getInstance().get(Calendar.YEAR)+"");
			int dkqx = (synf+Integer.parseInt(model.getXz())+13)>20?20:(synf+Integer.parseInt(model.getXz())+13);
//			int dkqx = (synf+13)>20?20:(synf+Integer.parseInt(model.getXz())+13);
			if(Globals.XXDM_ZJDX.equals(Base.xxdm)&&Integer.parseInt(model.getDkqx())>dkqx){
				
				response.getWriter().print(getJsonMessageResult("�ܴ������޲�������ڡ�"+dkqx+"���꣡", false));
				
			}else{
				response.getWriter().print(getJsonMessageResult("û�¶��������ܴ�������û��Ҫ��,��Ҳ������ʾ��", true));
			}
			
		}else{
			response.getWriter().print(getJsonMessageResult("��ѧ���Ѿ������������ѧ�����ȷ�ϣ�", false));
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����: 
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-8 ����04:23:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getHsdDkqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String xh = request.getParameter("xh");
		XyddkService service = new XyddkService();
		HashMap<String, String> xsxx = service.getXsxxByHsd(xh);
		String  currnd = Base.currXn.split("-")[0];
	    String nj = xsxx.get("nj");
	    String xz = xsxx.get("xz");
	    String jesx = xsxx.get("jesx");
	    int njNum = 0 ;
	    int xzNum = 0 ;
	    int jesxNum = 0;
	    String message = "";
	    String rs = "true";
	    HashMap<String, String> rsMap = new HashMap<String, String>();
	    /**
	     * ���´�����Ϊ�˷�ֹ��Ҫ����Ϊ�ն�����ļ������
	     */
		try {
			njNum = Integer.parseInt(nj);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				message = "��ѧ���꼶Ϊ�գ����ý��д������룡";
				rsMap.put("message", message);
				rs = "false";
				rsMap.put("rs", rs);
				JSONObject json = JSONObject.fromObject(rsMap);
				response.getWriter().print(json);
				
				return null;
			} catch (IOException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			xzNum = Integer.parseInt(xz);
		} catch (NumberFormatException e) {
			// TODO �Զ����� catch ��
			try {
				message = "��ѧ��ѧ��Ϊ�գ����ý��д������룡";
				rsMap.put("message", message);
				rs = "false";
				rsMap.put("rs", rs);
				JSONObject json = JSONObject.fromObject(rsMap);
				response.getWriter().print(json);
				return null;
			} catch (IOException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			jesxNum = Integer.parseInt(jesx);
		} catch (NumberFormatException e) {
			// TODO �Զ����� catch ��
			try {
				message = "��ѧ��ѧ�����Ϊ�գ����ý��д������룡";
				rsMap.put("message", message);
				rs = "false";
				rsMap.put("rs", rs);
				JSONObject json = JSONObject.fromObject(rsMap);
				response.getWriter().print(json);
				return null;
			} catch (IOException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		int dkqx = xzNum+njNum-Integer.parseInt(currnd);
		rsMap.put("dkqs", dkqx+"");
		rsMap.put("dkqx", (dkqx*12)+"" );
		rsMap.put("jesx", jesx);
		rsMap.put("currxn", Base.currXn);
		JSONObject json = JSONObject.fromObject(rsMap);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ʦ����Ի����뱣��ݸ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����10:29:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveDksqForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel)form;
		/**
		 * xns:ѧ������
		 * nzsfdks:ÿ��ס�޷Ѵ�������
		 * nxfdks:ÿ��ѧ�Ѵ�������
		 * nshfdks:ÿ������Ѵ�������
		 * nzsfyjes:ÿ��ס�޷�Ӧ�ɶ�����
		 * nxfyjes:ÿ��ѧ��Ӧ�ɶ�����
		 * filepath:�ϴ�gid
		 * xh:ѧ��
		 * rs:���
		 * message:������Ϣ��ʾ�ַ���
		 */
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * ����������Ƿ���ͬ���ļ�
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				return null;
			}
		}
		
		/**
		 * �Ƿ������ͬ�ļ���,�������ֱ�ӷ���
		 */
	    boolean isFileExist = service.getWjmSameRs(xh, filepath);
		if(isFileExist){
			try {
				message = "��ʷ��¼����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * �Ƿ�������ͬѧ��������¼�������ֱ�ӷ���
		 */
		boolean isRecordSameXn = service.getXnXhSameRs(xns, xh,null);
		if(isRecordSameXn){
			try {
				message = "�Ѵ�����ͬѧ��������¼����ȷ�ϣ�";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
		}
		String id =  UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setId(id);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		try {
			rs = service.runInsert(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		if(!rs){
			/**
			 * ���Ϊfalse;ֱ�ӷ���
			 */
			try {
				response.getWriter().print(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL);
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 *��������xg_hsd_zxdk_ndkb��
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
		/**
		 * ����ʧ�ܣ�ɾ��ԭ�ȱ���ļ�¼
		 */
	    if(!rs){
	    	try {
				service.runDelete(new String[]{id});
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
	    }
	    /**
	     * ����������ύ����
	     */
	    if("submit".equals(model.getType())){
	    	if (rs) {
				try {
					rs = shlc.runSubmit(id, model.getSplcid(), model.getXh(), "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
	    }
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ��ʦ����Ի��޸ı���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����10:31:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveAndSubmitForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XyddkModel model = (XyddkModel)form;
		/**
		 * id:����id
		 * xns:ѧ������
		 * nzsfdks:ÿ��ס�޷Ѵ�������
		 * nxfdks:ÿ��ѧ�Ѵ�������
		 * nshfdks:ÿ������Ѵ�������
		 * nzsfyjes:ÿ��ס�޷�Ӧ�ɶ�����
		 * nxfyjes:ÿ��ѧ��Ӧ�ɶ�����
		 * filepath:�ϴ�gid
		 * xh:ѧ��
		 * rs:���
		 * message:������Ϣ��ʾ�ַ���
		 */
		String id = request.getParameter("id");
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * ����������Ƿ���ͬ���ļ�
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				return null;
			}
		}
		/**
		 * �Ƿ������ͬ�ļ���,�������ֱ�ӷ���
		 */
	    boolean isFileExist = service.getWjmSameRsUpdate(xh, filepath);
		if(isFileExist){
			try {
				message = "��ʷ��¼����ͬ����pdf�ļ��������pdf�ļ����ƣ�";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
		}
		
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		try {
			rs = service.runUpdate(model);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			try {
				try {
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
				} catch (IOException e1) {
					// TODO �Զ����� catch ��
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
			return null;
		}
		if(!rs){
			/**
			 * ���Ϊfalse;ֱ�ӷ���
			 */
			try {
				response.getWriter().print(getJsonMessageByKey(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL));
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 * �޸ı���ǰ��Ҫ��ɾ��ԭ��xg_hsd_zxdk_ndkb������,Ȼ���ٲ������
		 */
		try {
			rs = service.delRs(id);
			if(!rs){
				message = "ɾ��xg_hsd_zxdk_ndkbʧ�ܣ�ɾ��id="+id+"��";
				response.getWriter().print(getJsonMessage(message));
			}
		} catch (Exception e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		/**
		 *��������xg_hsd_zxdk_ndkb��
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
	    /**
	     * ����������ύ����
	     */
	    if("submit".equals(model.getType())){
	    	if (rs) {
				try {
					rs = shlc.runSubmit(id, service.getModel(id).getSplcid(), model.getXh(), "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
	    }
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ���೷��������д
	 */
	@Override
	public ActionForward cancelAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel t  = (XyddkModel) form;
		XyddkService service = getService();
		User user = getUser(request);
		/**
		 * ��ʦ���ж��Ƿ��зŴ���¼���зŴ���¼�ļ�¼����������ֱ�ӷ���
		 */
		XyddkModel jgModel = new DkjgService().getModel(t.getId());
		if("10511".equals(Base.xxdm)  &&  null != jgModel){
			HsdxdService xdservice = new HsdxdService();
			if(!"0".equals(xdservice.getfdNum(jgModel.getHtbh()))){
				String message = "���зŴ���¼�� ����������";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		//���� �����¼��Ӧ���״̬Ϊ������С�
		t.setShzt(Constants.YW_SHZ);
		XyddkModel model = service.getModel( t);
		
		boolean isSuccess = new CommShlcImpl().runCancel(user.getUserName(), t.getId(), model.getSplcid(), t.getGwid());
		
		if(isSuccess){
			isSuccess = service.runUpdate(t); 
			service.deleteResult(t); 
			//������ѧ�������һ������ɾ���Ŵ���
			if("10511".equals(Base.xxdm)){
				service.delFdb(new String[]{t.getId()});
			}
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	/**
	 * ��ʦ�����ǰ����
	 */
	public ActionForward checkHtbhIsExists(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XyddkModel t  = (XyddkModel) form;
		String message = "true";
		if(!t.getShzt().equals("1")){
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean flag = new DkjgService().checkHtbhIsNotExists(null, t.getHtbh());
		if(!flag){
			message = "��ͬ����Ѵ��ڣ���ȷ�ϣ�";
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����:��д���淽��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-2-22 ����01:08:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel) form;
		XyddkService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:��д�ύ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-2-22 ����01:08:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel) form;
		XyddkService service = getService();
		boolean isSuccess = false;
		//���������¼��Ӧ�����״̬������С�
		model.setShzt(Constants.YW_SHZ);
		
		//��֤����ID�����ID��һ���ԣ���ϵͳ����ΨһID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//���������¼
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//�ύ���뵽�������
			message = submit("zxdk_xyddk" , model.getId(), "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:��д�ύ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-2-22 ����01:08:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
	throws Exception {

		ShlcInterface shlc = new CommShlcImpl();
		
		XyddkService service = getService();
		//��ѯ�����¼
		XyddkModel model = service.getModel(id);
		String splcid = model.getSplcid();
		//�ύ��������
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//���������¼״̬
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		
		return getJsonMessage(message);
	}
	 /**
	 * @����:����
	 * @���ߣ�lgx[����:1553]
	 * @���ڣ�2018-5-15 ����17:33:55
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
	
	public ActionForward printJsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkService service = new XyddkService();
		XyddkModel modelForm = (XyddkModel) form;
		XyddkModel model = service.getModel(modelForm.getId());
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	//���ģ����������word�ļ�
	private File getWord(XyddkModel xyddkModel)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		XyddkModel model = xyddkModel;
		String xh = model.getXh();
		//������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		String rxny = xsjbxx.get("rxrq");
		if(rxny.length() >= 7) 
			rxny = rxny.substring(0, 7);
		String dkqx = model.getDkqx();
		if(StringUtil.isNull(dkqx)) {
			data.put("dknx", "");
		}else{
			double dknx = Double.parseDouble(dkqx)/12.0;
			data.put("dknx", dknx);
		}
		
		xsjbxx.put("rxny", rxny);
		data.put("rs", xsjbxx);
		data.put("model", model);
		//����������Ϣ
		KnsjgService knservice = new KnsjgService();
		HashMap<String, String> knmap = knservice.getKnsInfo(model.getXh(),model.getXn());
		data.put("knlx", knmap.get("dcmc"));
		data.put("knyy", knmap.get("sqly"));
		
		File wordFile = null;
		wordFile = FreeMarkerUtil.getWordFile(data,  Constants.TEP_DIR + "zxdk","jsdkxxcjb_12688.xml", model.getXh() +"["+xsjbxx.get("xm")+"]" + "-");
		return wordFile;
		
	}

}
