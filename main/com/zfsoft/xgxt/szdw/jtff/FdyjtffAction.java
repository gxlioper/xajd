/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:07:43 
 */  
package com.zfsoft.xgxt.szdw.jtff;

import java.io.File;
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
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(����Ա��������--ɽ��Ϋ��) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-5 ����11:07:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyjtffAction extends SuperAction {

	private static final String url = "szdw_fdyjtff.do?method=cxFdyjtffList";
	
	@SystemAuth(url = url)
	public ActionForward cxFdyjtffList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
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
		
		String path = "szdw_fdyjtff.do?method=cxFdyjtffList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxFdyjtffList");
	}
	/**
	 * 
	 * @����:TODO(���Ӹ���Ա��������)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����01:51:46
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
	@SystemLog(description = "����˼������-��������-��������-����ZGH:{zgh},BZLX:{bzlx},XN:{xn},XQ:{xq},BZJE:{bzje}")
	public ActionForward addFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		HashMap<String, String> map=new HashMap<String, String>();
		String currxn=Base.currXn;
		String currxq=Base.currXq;
		map.put("xn", currxn);
		map.put("xq", currxq);
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("map", map);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		if (!StringUtil.isNull(model.getZgh())){
			//����f����Ա������Ϣ
			HashMap<String,String> jbxx = service.getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
		
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ȼ������Ƿ����
			boolean isExist=service.isExist(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.SZDW_REPEAT_ERROR));
				return null;
				
			}
			
		}
		String path = "szdw_fdyjtff.do?method=addFdyjtff";
		request.setAttribute("path", path);
		return mapping.findForward("addFdyjtff");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-��������-��������-�޸�ID:{id},ZGH:{zgh},BZLX:{bzlx},XN:{xn},XQ:{xq},BZJE:{bzje}")
	public ActionForward updateFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffForm myForm = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			// Ϋ��ѧԺ �����
			if(!"11067".equals(Base.xxdm) && "0".equalsIgnoreCase(myForm.getBzlx())){
				myForm.setKpdj("");
			}
			boolean result = service.runUpdate(myForm);
			String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		FdyjtffForm model=service.getModel(myForm);
		if (!StringUtil.isNull(model.getZgh())){
			//����f����Ա������Ϣ
			HashMap<String,String> jbxx = service.getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
			
		}
		BeanUtils.copyProperties(myForm, model);
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		return mapping.findForward("updateFdyjtff");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-��������-��������-ɾ��ID:{values}")
	public ActionForward delFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffService service = new FdyjtffService();
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
	
	@SystemAuth(url = url)
	public ActionForward viewFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffForm myForm = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		FdyjtffForm model=service.getModel(myForm);
		if (!StringUtil.isNull(model.getZgh())){
			//����f����Ա������Ϣ
			HashMap<String,String> jbxx = service.getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
			
		}
		BeanUtils.copyProperties(myForm, model);
		
		return mapping.findForward("viewFdyjtff");
		
	}
	
	
	/**
	 * 
	 * @����:TODO(��ȡ����Աlist)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����02:44:45
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
	
	public ActionForward showFdys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("fdyjtfflx", model.getFdyjtfflx());
		return mapping.findForward("showFdys");
	}
	
	/**
	 * 
	 * @����:��ȡ����Ա����ˢ�¸�ҳ�棩
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����08:46:34
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
	
	public ActionForward showFdysNotF5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		return mapping.findForward("showFdysNotF5");
	}
	
	public ActionForward showFdysAnother(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		return mapping.findForward("showFdysAnother");
	}
	
	/**
	 * 
	 * @����:��ȡ����Ա�����ҵ�ʦ������ˢ�¸�ҳ�棩
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����08:46:34
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
	
	public ActionForward showFdysQsdsNotF5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getFdyQsdsPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		return mapping.findForward("showFdysQsdsNotF5");
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
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();

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
