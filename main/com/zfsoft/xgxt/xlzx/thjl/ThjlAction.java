/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:11:45 
 */  
package com.zfsoft.xgxt.xlzx.thjl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xlzx.tsxsgl.TsxsService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
  * @���ߣ�wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ThjlAction extends SuperAction {
	
	private static final String url = "xlzx_thjl_thjl.do";
	
	private static ThjlService service = new ThjlService();
	private static TsxsService tsxsSv = new TsxsService();

	/**
	 * ��������ѧ�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String TSXS = "tsxs";
	private List<HashMap<String,String>> jbxxList = null;
	
	/** 
	 * @����:̸����¼��ѯ�б�
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward thjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> thjlInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(thjlInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xlzx_thjl_thjl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("thjlManage");
	}
	
	/**
	 * 
	 * @����: ̸����¼����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-25 ����10:02:58
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
	public ActionForward thjlzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ThjlForm myForm = (ThjlForm) form;
		// ��ȡְ����
		User user = getUser(request);
		String zgh = user.getUserName();

	 	if(StringUtils.isNotNull(myForm.getXh())){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(myForm.getXh());
 			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "xlzx_thjl.do?method=thjlzj";
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("thjlzj");
	}
	

	/**
	 * 
	 * @����: ̸����¼����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-25 ����10:02:58
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
	public ActionForward thjlxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ThjlForm myForm = (ThjlForm) form;
		// ��ȡְ����
		User user = getUser(request);
		String zgh = user.getUserName();

	 	if(StringUtils.isNotNull(myForm.getXh())){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(myForm.getXh());
 			request.setAttribute("jbxx", xsjbxx);
		}
	 	
		if(StringUtils.isNotNull(zgh)){
			HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
			request.setAttribute("thjlInfo", StringUtils.formatData(jsInfoList));
		}
		String path = "xlzx_thjl.do?method=thjlxg";
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("thjlxg");
	}
	
	/** 
	 * @����:̸����¼�����������ѯ
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward thjlDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
		User user = getUser(request);
		String xh = request.getParameter("xh");
		if(StringUtil.isNull(xh) ){
			xh = myForm.getXh();
		}
		String zgh = request.getParameter("zgh");
		if(StringUtil.isNull(zgh)){
			zgh = myForm.getZgh();
		}
		String doType = request.getParameter("doType");
	 	HashMap<String, String> thjlInfo=new HashMap<String,String>();
		if(doType==null || "add".equals(doType)){//����̸����¼
			// ѧ�Ŵ��� ��ȡѧ����Ϣ
			if(user.getUserStatus().equals("stu") && StringUtil.isNull(xh)){
	 			xh = user.getUserName();
 			}
			// ְ���Ŵ��� ��ȡ��ʦ��Ϣ
 			if(StringUtil.isNull(zgh)){
	 			zgh = user.getUserName();
 			}

 			// ְ������Ϣ
 			if(StringUtils.isNotNull(zgh)){
 				HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
 				if(jsInfoList!=null && jsInfoList.size()>0){
 		 			thjlInfo.put("zgh", zgh);
 		 			thjlInfo.put("jsxb", jsInfoList.get("xb"));
 		 			thjlInfo.put("jsxm", jsInfoList.get("xm"));
 		 			thjlInfo.put("jsbmmc", jsInfoList.get("bmmc"));
 				}
 			}
		}else if("update".equals(doType) || "view".equals(doType)){
			if(!StringUtil.isNull(myForm.getId())){
				thjlInfo = service.getThjlListById(myForm.getId());
				if(thjlInfo!=null && thjlInfo.size()>0){
		 			//��ȡ������������
					if(thjlInfo.get("knlxdm")!=null && !"".equals(thjlInfo.get("knlxdm"))){
						String knlxmc = tsxsSv.getKnlxMc(thjlInfo.get("knlxdm"));
						thjlInfo.put("knlxmc", knlxmc);
			 		}
					
					if(StringUtils.isNotNull(thjlInfo.get("xh"))){
						xh = thjlInfo.get("xh");
					}
				}
				
	 		}
		}
		// ѧ����Ϣ
	 	if(StringUtils.isNotNull(xh)){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(xh);
 			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "xlzx_thjl.do?method=thjlDetail";
		//ѧ��������Ϣ��ʾ����
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String,String>>  knlxList = tsxsSv.getKnlxList();
		request.setAttribute("knlxList", knlxList);
		request.setAttribute("doType", doType);
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("userStatus", user.getUserStatus());
		return mapping.findForward("thjlDetail");
	}
	
	/** 
	 * @����:����ѧ�Ų�ѯ̸����¼
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward thjlDetailByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
 		List<HashMap<String,String>> thjlList = service.getThjlListByXh(myForm.getXh());
 		HashMap<String,String> thjlInfo = new HashMap<String,String>();
 		
		// ѧ����Ϣ
	 	if(StringUtils.isNotNull(myForm.getXh())){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(myForm.getXh());
 			request.setAttribute("jbxx", xsjbxx);
		}
	 	
		String path = "xlzx_thjl_thjl.do";
		//ѧ��������Ϣ��ʾ����
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//�������ά����һ��̸����¼
		if(thjlList!=null && thjlList.size()>0){
			thjlInfo = thjlList.get(0);
			//��ȡ������������
			if(thjlInfo.get("knlxdm")!=null && !"".equals(thjlInfo.get("knlxdm"))){
				String knlxmc = tsxsSv.getKnlxMc(thjlInfo.get("knlxdm"));
				thjlInfo.put("knlxmc", knlxmc);
			}
			thjlList.remove(0);//��ȡ��ʷ̸����¼
		}
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("hisThjlList", thjlList);
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("thjlDetailByXh");
		
	}
	
	/**
	 * @����:̸����¼����
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����4:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			
			ThjlForm model = (ThjlForm) form;
			try {
				boolean flag = service.saveThjlInfo(model);
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("thjlDetail");
		
	}
	/**
	 * @����:̸����¼�޸�
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����5:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm model = (ThjlForm) form;

		try {
			boolean flag = service.updateThjlInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/**
	 * @����:�Ƿ���̸����¼
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-10-09 ����10:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward haveThjlFlagByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm model = (ThjlForm) form;
		boolean flag = false;
		try {
			List<HashMap<String,String>> thjlList = service.getThjlListByXh(model.getXh());
			if(thjlList!=null && thjlList.size()>0){
				flag = true;
			}
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @����:ɾ��̸����¼
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����5:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dealThjl = request.getParameter("dealThjl");
		String[] id = dealThjl.split(",");
		int count = 0;
		try {
			count = service.delThjlById(id);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	/** 
	 * @����:ѡ���ʦ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-24
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public ActionForward getJsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
		String doType = request.getParameter("doType");
		String gotoPath = request.getParameter("gotoPath");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> jsInfoList = service.getJsInfoList(myForm);
			JSONArray dataList = JSONArray.fromObject(jsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		gotoPath = gotoPath.replaceAll("[$]", "&");
		request.setAttribute("path", "xlzx_thjl.do?method=getJsInfo");
		request.setAttribute("gotoPath", gotoPath);
		
		return mapping.findForward("getJsInfo");
	}
	
	/**
	 * @����:̸����¼����
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-26 ����4:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportThjlData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm=(ThjlForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		
		TsxsService tsxsSv = new TsxsService();
		for(int i=0;i<resultList.size();i++){
			String knlxdm = resultList.get(i).get("knlxdm");
			if(!StringUtil.isNull(knlxdm)){
				resultList.get(i).put("knlxmc", tsxsSv.getKnlxMc(knlxdm));
			}
		}
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
