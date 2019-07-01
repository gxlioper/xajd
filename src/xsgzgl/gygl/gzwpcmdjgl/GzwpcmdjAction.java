/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-27 ����04:01:28 
 */  
package xsgzgl.gygl.gzwpcmdjgl;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ������Ʒ���ŵǼǹ��� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2014-8-27 ����04:01:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzwpcmdjAction extends SuperAction {
	
	private static final String url = "gygl_gzwpcmdjgl.do";
	
	/**
	 * 
	 * @����: ������Ʒ���ŵǼ��б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����01:42:21
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
	public ActionForward gzwpcmdjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
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
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_gzwpcmdjgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gzwpcmdjManage");
	}
	
	/**
	 * 
	 * @����: ������ת
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����01:57:50
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
	public ActionForward gzwpcmdjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		
		HashMap<String, String> gzwpxx = service.getXsxxOne(model.getXh());
		request.setAttribute("gzwpxx", gzwpxx);
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		
		}
		request.setAttribute("path", URLEncoder.encode("gygl_gzwpcmdj.do?method=gzwpcmdjAdd" , "gbk"));
		return mapping.findForward("gzwpcmdjAdd");
	}
	
	/**
	 * 
	 * @����: �޸���ת
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����02:04:56
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
	public ActionForward gzwpcmdjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		
		String gzwpdjid = model.getGzwpdjid();
		
		if(StringUtils.isNotBlank(gzwpdjid)){

			HashMap<String ,String> data = service.getGzwpcmdjxx(gzwpdjid);
			request.setAttribute("gzwpcmdjxx", data);
		}
				
		
		return mapping.findForward("gzwpcmdjUpdate");
	}
	
	
	/**
	 * 
	 * @����: ����ѧ�Ź�����ѯ����Ҫ�����ݲ���ת
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����03:16:42
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		String path = "gygl_gzwpcmdj_showStudents.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}
	
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����03:26:59
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
	@SystemLog(description="���ʹ�Ԣ����-������Ʒ���ŵǼǹ���-������Ʒ���ŵǼ�-����XH:{xh},CMSJ:{cmsj},WPMC:{wpmc},ZBRY:{zbry}")
	public ActionForward gzwpcmdjAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
	
		String wpmc = model.getWpmc();
		String cmsj = model.getCmsj();
		String zbry = model.getZbry();
		String xh = model.getXh();
		
		if(StringUtils.isNotBlank(zbry) && StringUtils.isNotBlank(xh) && StringUtils.isNotBlank(wpmc) && StringUtils.isNotBlank(cmsj)){
			boolean isSuccess = service.saveGzwpcmdjxx(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
	
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����03:33:49
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
	@SystemLog(description="���ʹ�Ԣ����-������Ʒ���ŵǼǹ���-������Ʒ���ŵǼ�-�޸�PK:{gzwpdjid},XH:{xh},CMSJ:{cmsj},WPMC:{wpmc},ZBRY:{zbry}")
	public ActionForward gzwpcmdjUpdateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
		
		boolean isSuccess = service.saveGzwpcmdjxx(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����03:41:13
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
	@SystemLog(description="���ʹ�Ԣ����-������Ʒ���ŵǼǹ���-������Ʒ���ŵǼ�-ɾ��PK:{pks}")
	public ActionForward gzwpcmdjDeleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		
		GzwpcmdjService service = new GzwpcmdjService();
		
		String pks = request.getParameter("pks"); 
		
		if(StringUtils.isNotBlank(pks)){
			String[] pkArr = pks.split(",");
			List<String[]> pkList = new ArrayList<String[]>();
			for (String string : pkArr) {
				pkList.add(string.split(","));
			}
		service.deleteGzwpcmdjxxPl(pkList);
		String messageKey =MessageKey.SYS_DEL_SUCCESS;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴��ϸ��Ϣ��������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����04:50:17
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
	public ActionForward viewWpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
		
		String gzwpdjid = model.getGzwpdjid();
		
		if(StringUtils.isNotBlank(gzwpdjid)){

			HashMap<String ,String> data = service.getGzwpcmdjxx(gzwpdjid);
			request.setAttribute("gzwpcmdjxx", data);
		}
		
		String path = "gygl_gzwpcmdj.do?method=viewWpxx";
		request.setAttribute("path", path);
		return mapping.findForward("viewWpxx");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-29 ����09:34:44
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
		
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
