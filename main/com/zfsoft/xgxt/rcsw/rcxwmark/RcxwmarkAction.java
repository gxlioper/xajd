/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-28 ����02:44:12 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-10-28 ����02:44:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwmarkAction extends SuperAction<RcxwmarkForm, RcxwmarkService> {
	
	RcxwmarkService service = new RcxwmarkService();
	/**
	 * 
	 * @����:δ�����ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-31 ����09:03:09
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
	public ActionForward  getWclCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwmarkForm markform = (RcxwmarkForm)form;
		if (QUERY.equalsIgnoreCase(markform.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			markform.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(markform, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("cxlx", "wclcx");
		String path = "xg_rcsw_rcxwmark_wcl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wclcx");
	}
	
	/**
	 * 
	 * @����:�Ѵ����ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-31 ����09:03:16
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
	public ActionForward  getYclCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwmarkForm markform = (RcxwmarkForm)form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(markform.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			markform.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getYclList(markform, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "xg_rcsw_rcxwmark_ycl.do";
		
		request.setAttribute("cxlx", "yclcx");
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		String writeAble = ("1").equals(service.getWriteAble(user.getUserName(), "xg_rcsw_rcxwmark_wcl.do").get("dxq")) ? "yes" : "no";
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("title","��������-�ۺϲ���-������������" );
		return mapping.findForward("yclcx");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-1 ����06:46:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward Sz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("xnndList",Base.getXnndList());
		request.setAttribute("rcxwjgids", request.getParameter("rcxwjgids"));
		return mapping.findForward("sz");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-1 ����06:48:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		RcxwmarkForm markform = (RcxwmarkForm)form;
		RcxwmarkForm model = service.getModel(markform);
		request.setAttribute("xnndList",Base.getXnndList());
		request.setAttribute("rcxwjgid",markform.getRcxwjgid());
		BeanUtils.copyProperties(markform, xgxt.utils.String.StringUtils.formatData(model));
		return mapping.findForward("xg");
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-1 ����06:49:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String type =  request.getParameter("type");
		HashMap<String, String> rs = new HashMap<String, String>();
		if(("yclcx").equals(type)){
			String id  = request.getParameter("id");
			rs = service.getCkData(id);
			
		}else{
			String rcxwjgid = request.getParameter("rcxwjgid");
			rs = service.getCkDataWcl(rcxwjgid);
		}
		request.setAttribute("type", type);
		request.setAttribute("rs", rs);
		return mapping.findForward("ck");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ȡ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-1 ����06:50:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward qxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.PJPY_QTSZ_QXCG) : MessageUtil
					.getText(MessageKey.PJPY_QTSZ_QXSB);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-2 ����09:05:07
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		RcxwmarkForm markform = (RcxwmarkForm)form;
		String type = markform.getType();
		User user = getUser(request);
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		markform.setSearchModel(searchModel);
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String,String>>();
		//�Ա�־λtype�����֣�wclcx:δ����yclcx:�Ѵ���
		if("wclcx".equals(type)){
			 dataList = service.getAllList(markform, user);
		}else{
			 markform.getPages().setPageSize(Integer.MAX_VALUE);
			 dataList = service.getYclList(markform, user);
		}
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = markform.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(dataList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �������ý��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-2 ����11:34:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveSzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		RcxwmarkForm markform = (RcxwmarkForm)form;
		String type = markform.getType();
		User user = getUser(request);
		boolean flag = true;
		if("add".equals(type)){
			String[] rcxwjgids = request.getParameter("rcxwjgids").split(",");
			if(null != rcxwjgids ){
				if(rcxwjgids.length == 1){
					markform.setRcxwjgid(rcxwjgids[0]);
					markform.setCzr(user.getUserName());
					flag = service.runInsert(markform);
				}else{
					flag = service.insertData(rcxwjgids, markform.getJxdm(), markform.getPjxn(), markform.getBz(), user.getUserName());
				}
			}
		}else{
			flag = service.runUpdate(markform);
		}
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
