/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-18 ����06:15:55 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.twsj;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh.GrzyjwhForm;
import com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh.GrzyjwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-18 ����06:15:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TwsjAction extends SuperAction<TwsjForm, TwsjService>{
	private final static String url = "rcswx_sjgl_twsj.do";
	private static List<HashMap<String, String>> jbxxList = null;
	
	private final static String TWSJ = "twsj";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(TWSJ);
	}
	
	/** 
	 * @����:��ί���ݸ߼���ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-19 ����08:32:14
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
	public ActionForward twsjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TwsjForm model = (TwsjForm) form;
		TwsjService service = new TwsjService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ��ȡ�������
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcswx_sjgl_twsj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("twsjManage");

	}
	
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-19 ����08:39:14
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
	public ActionForward addTwsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		TwsjForm model = (TwsjForm) form;
		TwsjService service = new TwsjService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.checkExist(model);
			if(!isExist){
				boolean result = service.saveTwsj(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSWX_SJGL_TWSJ_REPEAT));
				return null;
			}			
		}		
		String path = "twsj.do?method=addTwsj";
		request.setAttribute("path", path);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		model.setXn(Base.currXn);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("twsjAdd");
	}
	
	
	/** 
	 * @����:�޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-20 ����03:21:11
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
	public ActionForward updateTwsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		TwsjForm model = (TwsjForm) form;
		TwsjService service = new TwsjService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.checkExist(model);
			if(!isExist){
				boolean result = service.updateTwsj(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSWX_SJGL_TWSJ_REPEAT));
				return null;
			}
					
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		TwsjForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("twsjUpdate");
	}
	
	
	/** 
	 * @����:ɾ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-20 ����03:21:49
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
	public ActionForward delTwsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TwsjService service = new TwsjService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.delTwsj(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",String.valueOf(num));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} 
		return null;
	}
	
	
	
	/** 
	 * @����:�鿴
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-20 ����03:26:38
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
	public ActionForward viewTwsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			TwsjForm model = (TwsjForm) form;
			TwsjService service = new TwsjService();
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
						.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			request.setAttribute("jbxxList", jbxxList);
			TwsjForm viewForm = service.getModel(model);
			BeanUtils.copyProperties(model, StringUtils.formatData(viewForm));
			request.setAttribute("xxdm", Base.xxdm);
			request.setAttribute("rs", StringUtils.formatData(model));
			return mapping.findForward("twsjView");
	}
	
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-20 ����03:28:03
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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TwsjForm model = (TwsjForm) form;
		TwsjService service = new TwsjService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
