/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:10:30 
 */  
package com.zfsoft.xgxt.xlzx.tsxsgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsAction extends SuperAction {
	
	private static final String url = "xlzx_tsxs_tsxs.do";
	
	private static TsxsService service = new TsxsService();

	/**
	 * ��������ѧ�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String THJL = "thjl";
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * ��ע״̬��1[��ע]
	 */
	private static String GZZT_GZ = "1";
	
	@SystemAuth(url = url)
	public ActionForward tsxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsForm myForm = (TsxsForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> tsxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(tsxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xlzx_tsxs_tsxs.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsxsManage");
	}
	
	@SystemAuth(url = url)
	public ActionForward tsxsDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsForm myForm = (TsxsForm) form;
		String doType = request.getParameter("doType");
	 	HashMap<String, String> tsxsInfoList=new HashMap<String,String>();
	 	if(!StringUtil.isNull(myForm.getXh())){
	 		if("11527".equals(Base.xxdm)){
	 			if(doType != null){
	 				tsxsInfoList= service.getTsxsDetailByXhZc(myForm);
	 			}else{
	 				tsxsInfoList=service.getZcTsxsInfo(myForm);
	 			}
	 			
	 		}else{
	 			tsxsInfoList= service.getTsxsDetailByXh(myForm.getXh());
	 		}
	 		if(tsxsInfoList.get("knlxdm")!=null && !"".equals(tsxsInfoList.get("knlxdm"))){
	 			tsxsInfoList.put("knlxmc", service.getKnlxMc(tsxsInfoList.get("knlxdm")));
	 		}
	 	}
	 	//���ϳ���ѧԺ����ѧ����׷��
	 	if(!"11527".equals(Base.xxdm)){
	 		if(doType==null && tsxsInfoList.size()>0){//����ʱѡ���Ѵ��ڵ�����ѧ������ɶ����޸�
		 		doType = "update";
	 		}
	 	}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		List<HashMap<String,String>>  knlxList = service.getKnlxList();
		request.setAttribute("knlxList", knlxList);
		request.setAttribute("path", "xlzx_tsxs.do?method=tsxsDetail");
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("doType", doType);
		request.setAttribute("tsxsInfoList", StringUtils.formatData(tsxsInfoList));
		request.setAttribute("nowTime", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("tsxsDetail");
	}
	
	
	public ActionForward getKnlxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsForm myForm = (TsxsForm) form;
		String knlxdms = myForm.getKnlxdm();
		String knlxmc = "";
		if(knlxdms!=null){
			knlxmc =service.getKnlxMc(knlxdms);
		}
		response.getWriter().print(knlxmc);
		return null;
	}
	
	/**
	 * 
	 * @����:ȡ��ѧ����Ϣ�б�(�ܹ�ע��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-23 ����05:37:37
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
	public ActionForward getTsxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsForm myForm = (TsxsForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		String gotoPath = request.getParameter("gotoPath");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			// ��ע״̬����ע
			myForm.setGzzt(GZZT_GZ);
			//��ѯ
			List<HashMap<String,String>> tsxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(tsxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		gotoPath = gotoPath.replaceAll("[$]", "&");
		request.setAttribute("gotoPath", gotoPath);	
		request.setAttribute("path", "xlzx_tsxs_tsxs.do");	
		return mapping.findForward("getTsxsInfo");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setTsxsGzzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dealTsxs = request.getParameter("dealTsxs");
		String doType = request.getParameter("doType");
		if(UPDATE.equals(doType)){
		 	if(!StringUtil.isNull(dealTsxs)){
		 		String[] id = dealTsxs.split(",");
				String gzzt = request.getParameter("gzzt");
				boolean flag = service.updateBatchGzStatus(id, gzzt);

				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS	
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		 	}
		}else if(!StringUtils.isNull(dealTsxs)){
				String[] id = dealTsxs.split(",");
				if(id!=null && id.length == 1){
					HashMap<String,String> xsInfoList = service.getTsxsInfoById(dealTsxs);
					String gzzt = xsInfoList.get("gzzt");
					request.setAttribute("gzzt", gzzt);
				}
			
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("dealTsxs", dealTsxs);
		return mapping.findForward("setBatchGzzt");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveTsxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			TsxsForm model = (TsxsForm) form;
			try {
				boolean flag = service.saveTsxsInfo(model);
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				response.getWriter().print(false);
				return null;
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("tsxsDetail");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateTsxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsForm model = (TsxsForm) form;

		try {
			boolean flag = service.updateTsxsInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteTsxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dealTsxs = request.getParameter("dealTsxs");
		String[] id = dealTsxs.split(",");
		int count = 0;
		try {
			count = service.delTsxsById(id);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}

		//ѧ��������Ϣ��ʾ����
		jbxxList = new BdpzService().getJbxxpz(THJL);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * @����:����ѧ����Ϣ�б���
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-10-11 ����10:25:25
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
	public ActionForward exportTsxsData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsForm myForm=(TsxsForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		for(int i=0;i<resultList.size();i++){
			String knlxdm = resultList.get(i).get("knlxdm");
			if(!StringUtil.isNull(knlxdm)){
				resultList.get(i).put("knlxmc", service.getKnlxMc(knlxdm));
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
