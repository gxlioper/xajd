/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-19 ����05:00:12 
 */  
package xsgzgl.gygl.lfryxxgl;

import java.io.File;
import java.net.URLDecoder;
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
import xsgzgl.gygl.qsgl.QsglService;

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
 * @�๦������: ������Ա�Ǽǹ���
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2014-8-19 ����05:00:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LfrydjAction extends SuperAction {
	
	private static final String url = "gygl_lfryxxgl.do";
	
	/**
	 * 
	 * @����: ���õǼǹ����б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-20 ����11:22:00
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
	public ActionForward lfrydjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service  = new LfrydjService();
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
		request.setAttribute("path", "gygl_lfryxxgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lfrydjManage");
	}
	
	/**
	 * 
	 * @����: ������ת
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-21 ����08:45:59
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
	public ActionForward lfrydjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
//		HashMap<String, String> lfryxx = service.getXsxxOne(model.getXh());
		List<HashMap<String,String>> lfsyList = service.getLfsyList();	//��ѯ���������б�����select�����б����ʾ
		
//		request.setAttribute("lfryxx", lfryxx);
		request.setAttribute("lfsyList", lfsyList);
		request.setAttribute("ldList", new QsglService().getLdList());
		
		request.setAttribute("path", URLEncoder.encode("gygl_lfrydj.do?method=lfrydjAdd" , "gbk"));
		return mapping.findForward("lfrydjAdd");
	}
	
	/**
	 * 
	 * @����: �޸���ת
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-25 ����11:49:11
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
	public ActionForward lfrydjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		String lfrdjid = model.getLfrdjid();
		List<HashMap<String,String>> lfsyList = service.getLfsyList();	//��ѯ���������б�����select�����б����ʾ
		
		request.setAttribute("lfsyList", lfsyList);
		request.setAttribute("ldList", new QsglService().getLdList());
		
		if(StringUtils.isNotBlank(lfrdjid)){

			HashMap<String ,String> data = service.getLfrydjxx(lfrdjid);
			request.setAttribute("lfrydjxx", xgxt.utils.String.StringUtils.formatData(data));
		}
				
		
		return mapping.findForward("lfrydjUpdate");
	}
	
	
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-25 ����03:10:01
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
	@SystemLog(description="���ʹ�Ԣ����-������Ա�Ǽǹ���-������Ա�Ǽ�-����XH:{xh},LFRXM:{lfrxm},LFRXB:{lfrxb},LFRSFZH:{lfrsfzh},LFSJ:{lfsj},ZBRY:{zbry}")
	public ActionForward lfrydjAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		boolean result = service.runInsert(model);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		
		return null;
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-25 ����03:10:21
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
	@SystemLog(description="���ʹ�Ԣ����-������Ա�Ǽǹ���-������Ա�Ǽ�-�޸�PK:{lfrdjid},XH:{xh},LFRXM:{lfrxm},LFRXB:{lfrxb},LFRSFZH:{lfrsfzh},LFSJ:{lfsj},ZBRY:{zbry}")
	public ActionForward lfrydjUpdateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-21 ����10:51:47
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
	@SystemLog(description="���ʹ�Ԣ����-������Ա�Ǽǹ���-������Ա�Ǽ�-ɾ��PK:{pks}")
	public ActionForward lfrydjDeleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjService service = new LfrydjService();
		
		String pks = request.getParameter("pks");
		
		if(StringUtils.isNotBlank(pks)){
			String[] pkArr = pks.split(",");
			List<String[]> pkList = new ArrayList<String[]>();
			for (String string : pkArr) {
				pkList.add(string.split(","));
			}
		service.deleteLfrydjxxPl(pkList);
		String messageKey =MessageKey.SYS_DEL_SUCCESS;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		}
		
		return null;
		
	}
	
	
	
	
	/**
	 * 
	 * @����: ����ѧ�Ź�����ѯ����Ҫ�����ݲ���ת
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-27 ����09:08:52
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
		
		String elementIds = request.getParameter("elementIds");
		String lddm = request.getParameter("lddm");
		
		String path = "gygl_lfrydj_showStudents.do";
		request.setAttribute("path", path);
		request.setAttribute("elementIds", elementIds);
		request.setAttribute("lddm", lddm);
		return mapping.findForward("showStudents");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-27 ����02:23:57
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
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service  = new LfrydjService();
		
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
	
	
	
	/**
	 * 
	 * @����: �鿴��ϸ��Ϣ��������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-27 ����03:49:25
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
	public ActionForward viewLfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		String lfrdjid = model.getLfrdjid();
		
		if(StringUtils.isNotBlank(lfrdjid)){

			HashMap<String ,String> data = service.getLfrydjxx(lfrdjid);
			request.setAttribute("lfrydjxx", data);
		}
		
		String path = "gygl_lfrydj.do?method=viewLfxx";
		request.setAttribute("path", path);
		return mapping.findForward("viewLfxx");
	}
	
	/**
	 * 
	 * @����:��У����ϸ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-2 ����02:38:12
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
	public ActionForward xsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LfrydjForm model = (LfrydjForm) form;
		LfrydjDao dao = new LfrydjDao();
		String pk = URLDecoder.decode(URLDecoder.decode(request.getParameter("pk"),"UTF-8"),"UTF-8");
		String userType = request.getParameter("userType");
		if (QUERY.equals(model.getType())){
			//��ѯ
			List<HashMap<String,String>> resultList = dao.getXsxx(model,pk,userType);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "gygl_lfrydj.do?method=xsxxView";
		request.setAttribute("path", path);
		request.setAttribute("pk", pk);
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xsxxView");
	}
	
}
