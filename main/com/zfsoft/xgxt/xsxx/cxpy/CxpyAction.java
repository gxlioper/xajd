/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����04:52:48 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: TODO(�����������) 
 * @���ߣ� CMJ [���ţ�913]
 * @ʱ�䣺 2013-7-24 ����04:52:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpyAction extends SuperAction {
	
	private static final String url = "xsxx_gygl_cxcxpy.do?method=cxCxpyList";
	
	@SystemAuth(url = url)
	public ActionForward cxCxpyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
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
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		if(!("13943".equalsIgnoreCase(Base.xxdm))) {
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		String path = "xsxx_gygl_cxcxpy.do?method=cxCxpyList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxCxpyList");
	}
	
	/**
	 * 
	 * @����:(���Ӳ�������)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-9 ����10:46:24
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��������������-��������¼��-����XHS:{xhs}")
	public ActionForward addCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		HashMap<String, String> map=new HashMap<String, String>();
		String currxn=Base.currXn;
		String currxq=Base.currXq;
		map.put("xn", currxn);
		map.put("xq", currxq);
		String xhs=request.getParameter("xhs");
		if(xhs!=null&&!"".equals(xhs)){
			map.put("xn", model.getXn());
			map.put("xq", model.getXq());
			map.put("cxpy", model.getCxpy());
			List<HashMap<String, String>> xsList=service.getXzxsList(xhs);
			request.setAttribute("xsList", xsList);
			request.setAttribute("xhs", xhs);
		}
		map.put("xhs", xhs);
		User user = getUser(request);
		String name=user.getRealName();
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		List<HashMap<String, String>> cxdjList=service.getCxdjList();
		request.setAttribute("map", map);
		request.setAttribute("name", name);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		request.setAttribute("cxdjList", cxdjList);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		request.setAttribute("nowTime", time);
		
		return mapping.findForward("addCxpy");
	}
	/**
	 * 
	 * @����:TODO(��ȡѧ����Ϣ)
	 * @���ߣ�Cmj [���ţ�913]
	 * @���ڣ�2013-7-25 ����03:45:55
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
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getXsPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String xhs=request.getParameter("xhs");
		String xn=request.getParameter("xn");
		String xq=request.getParameter("xq");
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		String path = "xsxx_gygl_cxcxpy.do?method=getStu";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStu");
	}
	/**
	 * 
	 * @����:TODO(�������������Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-25 ����06:42:50
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��������������-��������¼��-����PK:{pk}")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		boolean result=service.save(model);
		String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		request.setAttribute("message", message);
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ��������������-��������¼��-�޸�PK:{pk}")
	public ActionForward updateCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		CxpyForm myForm=new CxpyForm();
		myForm=service.getModel(model);
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.runUpdate(model);
			String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		List<HashMap<String, String>> cxdjList=service.getCxdjList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		request.setAttribute("cxdjList", cxdjList);
		BeanUtils.copyProperties(model,StringUtils.formatData(myForm ));
		return mapping.findForward("updateCxpy");
	}
	
	/**
	 * ɾ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ��������������-��������¼��-ɾ��VALUES:{values}")
	public ActionForward delCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
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
	 * �鿴����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		CxpyForm myForm=new CxpyForm();
		myForm=service.getModel(model);
		BeanUtils.copyProperties(model,StringUtils.formatData(myForm ));
		return mapping.findForward("viewCxpy");
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�cmj
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;

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

	
	public ActionForward getExistsCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		
		String count = service.getCount(model);
		response.getWriter().print(count);
		
		return null;
	}
}
