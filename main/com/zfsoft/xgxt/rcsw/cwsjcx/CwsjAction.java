/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-19 ����03:40:14 
 */  
package com.zfsoft.xgxt.rcsw.cwsjcx;

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
import xsgzgl.comm.BasicInit;
import xsgzgl.dtjs.dtxxgl.DtxxglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�������ݲ�ѯ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-6-19 ����03:40:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CwsjAction extends SuperAction {
	
	private static final String url = "rcsw_cwsj_qfsjcx.do";
	
	/**
	 * ��ѯѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getCwsjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CwsjForm cwsjForm=(CwsjForm)form;
		CwsjService service=new CwsjService();
		if (QUERY.equals(cwsjForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			cwsjForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(cwsjForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_cwsj_qfsjcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		//���Ի�ҳ����ת
		String xxpymc=getXxpymc(request);
		String returnType = "/xsgzgl/rcsw/cwsj/"+xxpymc+"/cwsjList.jsp";
		if(validateUrlIsExists(request,returnType)){
			return new ActionForward(returnType,false);
		}
		return mapping.findForward("cwsjlist");
	}
	/**
	 * 
	 * @����:TODO(��ȡѧУ����ƴ��)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-19 ����04:36:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * String �������� 
	 * @throws
	 */
	private String getXxpymc(HttpServletRequest request){
		// ѧУ����
		String xxdm = (String) request.getSession().getAttribute("xxdm");
		
		// ѧУƴ������
		String xxpymc = new BasicInit().getXxmc(xxdm, null);
		
		return xxpymc;
	}
	
	public boolean validateUrlIsExists(HttpServletRequest request, String jspUrl) {
		File tempFilePath  = new File(request.getRealPath(jspUrl));
		if (!tempFilePath.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CwsjForm model=(CwsjForm)form;
		CwsjService service=new CwsjService();
		
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
	

}
