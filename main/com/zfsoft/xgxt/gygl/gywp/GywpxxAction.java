/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-6 ����03:36:37 
 */  
package com.zfsoft.xgxt.gygl.gywp;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(��Ԣ��Ʒ����) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-6 ����03:36:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GywpxxAction extends SuperAction {
	
	private static final String url = "gygl_qywpxx.do?method=cxGywpxxList";

	/**
	 * 
	 * @����:(��ѯ��Ԣ����¥�����Ʒά����Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-6 ����04:20:52
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
	@SystemAuth(url = "gygl_qywpxx.do?method=cxGywplrxxList")
	public ActionForward cxGywplrxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getGywplrxxList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "gygl_qywpxx.do?method=cxGywplrxxList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxGywplrxxList");
	}
	/**
	 * 
	 * @����:(��ѯά����Ʒ��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-6 ����04:31:36
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
	public ActionForward cxGywpxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String pk=request.getParameter("pk");
		//����ѡ�����ҳ�ʼ��ѯ����
		if(pk!=null&&!"".equalsIgnoreCase(pk)){
			String[] ldqs=pk.split("@@");
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_ld(new String[]{ldqs[0]});
			searchModel.setSearch_tj_qsh(new String[]{ldqs[1]});
			request.setAttribute("searchTj", searchModel);
		}
		
		String path = "gygl_qywpxx.do?method=cxGywpxxList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxGywpxxList");
	}
	/**
	 * 
	 * @����:(���ӹ�Ԣ��Ʒ��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-6 ����05:12:39
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
	public ActionForward addGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		String ids=request.getParameter("ids");
		int num=ids.split(",").length;
		List<HashMap<String, String>> wpdlList=service.getWpdlList();
		List<HashMap<String, String>> wplbList=service.getWplbList();
		request.setAttribute("ids", ids);
		request.setAttribute("num", num);
		request.setAttribute("wpdlList", wpdlList);
		request.setAttribute("wplbList", wplbList);
		return mapping.findForward("addGywpxx");
	}
	
	/**
	 * 
	 * @����:(�������ӹ�Ԣ��Ʒ��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����10:36:43
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
	@SystemLog(description="���ʹ�Ԣ����-��Ʒά��-��Ʒ�Ǽ�-����WPMC:{wpmc},WPDLDM:{wpdldm},WPLBDM:{wplbdm},SL:{sl}")
	public ActionForward saveAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		boolean result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:(�޸Ĺ�Ԣ��Ʒ��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����10:38:12
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
	public ActionForward updateGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		GywpxxForm myForm=service.getModel(model);
		if(myForm.getHhyy()!=null){
			myForm.setHhyy(myForm.getHhyy().replaceAll("<br/>", "\n"));
		}
		if(myForm.getBz()!=null){
			myForm.setBz(myForm.getBz().replaceAll("<br/>", "\n"));
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		List<HashMap<String, String>> wpdlList=service.getWpdlList();
		List<HashMap<String, String>> wplbList=service.getWplbList();
		request.setAttribute("wpdlList", wpdlList);
		request.setAttribute("wplbList", wplbList);
		return mapping.findForward("updateGywpxx");
	}
	
	/**
	 * 
	 * @����:(�����޸Ĺ�Ԣ��Ʒ��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����10:50:11
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
	@SystemLog(description="���ʹ�Ԣ����-��Ʒά��-��Ʒ�Ǽ�-�޸�PK:{id},WPDLDM:{wpdldm},WPLBDM:{wplbdm},SL:{sl}")
	public ActionForward saveUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		boolean result=service.runUpdate(model);
		String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	/**
	 * 
	 * @����:(�鿴ά����Ʒ��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����11:25:07
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
	public ActionForward viewGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		GywpxxForm myForm=service.getModel(model);
		if(myForm.getHhyy()!=null){
			myForm.setHhyy(myForm.getHhyy().replaceAll("<br/>", "\n"));
		}
		if(myForm.getBz()!=null){
			myForm.setBz(myForm.getBz().replaceAll("<br/>", "\n"));
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("viewGywpxx");
	}
	
	
	
	/**
	 * 
	 * @����:(ɾ����Ԣ��Ʒ��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����10:49:07
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
	@SystemLog(description="���ʹ�Ԣ����-��Ʒά��-��Ʒ�Ǽ�-ɾ��VALUES:{values}")
	public ActionForward delGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		String values=request.getParameter("values");
		int num=service.runDelete(values.split(","));
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
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		for (HashMap<String, String> hashMap : resultList) {
			String hhyy=hashMap.get("hhyy");
			String bz=hashMap.get("bz");
			if(hhyy!=null){
				hashMap.put("hhyy", hhyy.replaceAll("<br/>", "\n"));
			}
			if(bz!=null){
				hashMap.put("bz", bz.replaceAll("<br/>", "\n"));
			}
			
		}
		
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
