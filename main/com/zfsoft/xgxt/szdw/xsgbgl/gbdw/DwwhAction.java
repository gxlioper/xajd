/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-12 ����10:05:35 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.gbdw;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.szdw.jfxxwh.JfxxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ����� ����ά��
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-12 ����10:05:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DwwhAction  extends SuperAction{

	private static final String  CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "szdw_xsgb_zwlx.do?method=zwlxList";

	private DwwhService service = new DwwhService();
	/**
	 * @����:������ά��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����10:13:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward dwwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DwwhForm myForm = (DwwhForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "szdw_xsgb_dwwh.do?method=dwwhList");
		return mapping.findForward("list");
	}
	/**
	 * @����:
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����2:08:06
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
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�����ά��-����XH:{xh},LXDM:{lxdm},ZWID:{zwid},ZZZT:{zzzt},RZSJ:{rzsj},LZSJ:{lzsj}")
	public ActionForward dwwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DwwhForm myForm = (DwwhForm) form;
//		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//ͬһ����λ�������������ڸڼ�¼
			/*if("1".equals(myForm.getZzzt())&&service.getZfmcExits(myForm)){
				Map<String, String> map = new HashMap<String, String>();
				map.put("message", "��ǰְ������һ����ְ��¼");
				response.getWriter().print(JSONObject.fromMap(map));
				return null;
			}
			myForm.setSqr(user.getUserName());*/

			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//����ѧ��������Ϣ
		/*if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
		}else{
			szXsxx(request,myForm.getXh());
		}*/
		String path = "szdw_xsgb_dwwh.do?method=dwwhAdd";
		request.setAttribute("path", path);
		request.setAttribute("model", myForm);
		request.setAttribute("xn",Base.currXn);
		return mapping.findForward("add");
	}

	public ActionForward getZwxx(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwwhService zwwhService = new ZwwhService();
		List<HashMap<String,String>> data = zwwhService.getAllList(new ZwwhForm());
		response.getWriter().print(JSONArray.fromObject(data));
		return null;
	}
	public ActionForward getBgbData(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String bjdm = request.getParameter("bjdm");
		List<HashMap<String,String>> data = service.getBgbData(bjdm);
		response.getWriter().print(JSONArray.fromObject(data));
		return null;
	}

	public ActionForward getXsxxList(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DwwhForm myForm = (DwwhForm) form;
//		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getXsxxList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_xsgb_dwwh.do?method=getXsxxList");
		request.setAttribute("id",request.getParameter("id"));
		request.setAttribute("bjdm",myForm.getBjdm());
		return mapping.findForward("xsxxList");
	}
	/**
	 * @����:ѧ���ɲ�����ά���޸�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����4:24:36
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
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�����ά��-�޸�DWID:{dwid},XH:{xh},LXDM:{lxdm},ZWID:{zwid},ZZZT:{zzzt},RZSJ:{rzsj},LZSJ:{lzsj}")
	public ActionForward dwwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm myForm = (DwwhForm) form;
//		DwwhForm model = service.getModel(myForm);
//		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//ͬһ����λ�������������ڸڼ�¼
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		HashMap<String,String> bjxx = service.getBjxx(myForm.getBjdm());
		request.setAttribute("bjxx", bjxx);
		request.setAttribute("xn",Base.currXn);
		return mapping.findForward("update");
	}
	/**
	 * @����:ѧ���ɲ�ά������鿴
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����4:24:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward dwwhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm myForm = (DwwhForm) form;
		HashMap<String,String> bjxx = service.getBjxx(myForm.getBjdm());
		request.setAttribute("bjxx", bjxx);
		request.setAttribute("xn",Base.currXn);
		return mapping.findForward("view");
	}
	/**
	 * @����:����ά������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����4:25:25
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
	public ActionForward dwwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm myForm=(DwwhForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
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
	/**
	 * @����:����ά��ɾ��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����4:52:43
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
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�����ά��-ɾ��DWID:{values}")
	public ActionForward dwwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)){
			boolean result = service.batchDelete(values.split(","));
			message = result ? MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS)
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
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
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}
	public ActionForward dwwhxsList(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DwwhForm myForm = (DwwhForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.export(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "szdw_xsgb_dwwh.do?method=dwwhxsList");
		return mapping.findForward("dwwhxsList");
	}
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm model = (DwwhForm) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setMaxPage(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.export(model,user);

		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
