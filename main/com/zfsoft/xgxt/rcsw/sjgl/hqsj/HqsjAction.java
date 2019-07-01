/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-21 ����03:01:21 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.hqsj;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.sjgl.kqsj.KqsjForm;
import com.zfsoft.xgxt.rcsw.sjgl.kqsj.KqsjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-21 ����03:01:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HqsjAction extends SuperAction<HqsjForm, HqsjService> {
	private final static String url = "rcswx_sjgl_hqsj.do";
	private static List<HashMap<String, String>> jbxxList = null;
	private final static String HQSJ = "hqsj";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HQSJ);
	}
	
	/** 
	 * @����:�������ݲ�ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-21 ����03:06:51
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
	public ActionForward hqsjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HqsjForm model = (HqsjForm) form;
		HqsjService service = new HqsjService();
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
		String path = "rcswx_sjgl_hqsj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("hqsjManage");
	}
	
	/** 
	 * @����:������������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-21 ����04:07:03
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
	public ActionForward hqsjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HqsjForm model = (HqsjForm) form;
		HqsjService service = new HqsjService();
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
				boolean result = service.saveHqsj(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSWX_SJGL_HQSJ_REPEAT));
				return null;
			}			
		}
		String path = "hqsj.do?method=hqsjAdd";
		request.setAttribute("path", path);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("jbxxList", jbxxList);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hqsjAdd");
	}
	
	/** 
	 * @����:�������ݲ鿴
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-22 ����08:36:17
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
	public ActionForward hqsjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			HqsjForm model = (HqsjForm) form;
			HqsjService service = new HqsjService();
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
						.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			request.setAttribute("jbxxList", jbxxList);
			HqsjForm viewForm = service.getModel(model);
			BeanUtils.copyProperties(model, StringUtils.formatData(viewForm));
			request.setAttribute("xxdm", Base.xxdm);
			request.setAttribute("rs", StringUtils.formatData(model));
			return mapping.findForward("hqsjView");
	}
	
	
	/** 
	 * @����:��������ɾ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-22 ����08:37:03
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
	public ActionForward hqsjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HqsjService service = new HqsjService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.delHqsj(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",String.valueOf(num));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} 
		return null;
	} 
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-22 ����08:57:58
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
	public ActionForward hqsjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		HqsjForm model = (HqsjForm) form;
		HqsjService service = new HqsjService();
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
				boolean result = service.updateHqsj(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSWX_SJGL_HQSJ_REPEAT));
				return null;
			}
					
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		HqsjForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("hqsjUpdate");
	}
	
	
	/** 
	 * @����:��������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-22 ����09:53:13
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
		HqsjForm model = (HqsjForm) form;
		HqsjService service = new HqsjService();
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
