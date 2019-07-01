/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����05:15:46 
 */  
package com.zfsoft.xgxt.rcsw.xbzj;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(ѧ��ϵ��֧�̹���--Ϋ��ѧԺ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-5 ����05:15:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XbzjAction extends SuperAction {
	
	private static final String url = "rcsw_xbzj.do?method=cxXbzjList";
	
	@SystemAuth(url = url)
	public ActionForward cxXbzjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
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
		request.setAttribute("realTable", "rcsw_xbzj");
		String path = "rcsw_xbzj.do?method=cxXbzjList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxXbzjList");
	}
	
	
	/**
	 * 
	 * @����:(����֧����Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-8 ����09:03:31
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
	@SystemLog(description="�����ճ�����-ϵ��֧��-ϵ��֧��-����")
	public ActionForward addXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
		
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
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		if (SAVE.equals(model.getType())){
			boolean isExist=service.isExist(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_REPEAT_ERROR));
				return null;
			}
		}
		String path = "rcsw_xbzj.do?method=addXbzjxs";
		request.setAttribute("path", path);
		return mapping.findForward("addXbzjxs");
	}
	/**
	 * 
	 * @����:(�޸�֧����Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-8 ����09:02:44
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
	@SystemLog(description="�����ճ�����-ϵ��֧��-ϵ��֧��-�޸�ID:{id}")
	public ActionForward updateXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())){

			boolean result = service.runUpdate(model);
			String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		XbzjForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateXbzjxs");
	}
	/**
	 * 
	 * @����:(�鿴ѧ��֧����Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-8 ����09:02:09
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
	public ActionForward viewXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
		
		XbzjForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("viewXbzjxs");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-ϵ��֧��-ϵ��֧��-ɾ��VALUES:{values}")
	public ActionForward delXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		String values = request.getParameter("values");
		
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
								: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
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
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;

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
