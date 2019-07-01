/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-17 ����10:05:56 
 */  
package com.zfsoft.xgxt.rcsw.cxda;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.rzkh.rzkhjgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-6-17 ����10:05:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxdaAction extends SuperAction<CxdaForm,CxdaService> {
	private CxdaService service = new CxdaService();
	private final String CXDA ="cxda";
	private DAO Dao = DAO.getInstance();
	
	private static final String url = "rcsw_cxda_cxda.do";
	
	@SystemAuth(url = url)
	public ActionForward  getCxdaList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		CxdaForm model = (CxdaForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList); 
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_cxda_cxda.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxdalist");
	}
	
	/**
	 * ���ӳ��ŵ���
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxdaForm model = (CxdaForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDA);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Dao.getXnndList());
		request.setAttribute("xqList", Dao.getXqList());
		String path = "rcsw_cxda.do?method=add";
		request.setAttribute("path", path);
		//������Ϣ����
		return mapping.findForward("add");
	}
	
	//ɾ�����ŵ���
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ճ�����-���ŵ���-���ŵ���-ɾ��VALUES:{values}")
	public ActionForward delCxda(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	 //���ŵ����鿴
	@SystemAuth(url = url)
	public ActionForward CxdaView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxdaForm myForm = (CxdaForm) form;
		CxdaForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDA);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cxdaxx", StringUtils.formatData(model));
	    String xqmc = service.getxqdz(model.getXq()).get("xqmc");
	    request.setAttribute("xqmc", xqmc);
		return mapping.findForward("view");
	}
	
	//���ŵ������ŵ����޸�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editCxda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxdaForm myForm = (CxdaForm) form;
		CxdaForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDA);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cxdaxx", StringUtils.formatData(model));
		request.setAttribute("xnList", Dao.getXnndList());
		request.setAttribute("xqList", Dao.getXqList());
		String path = "rcsw_cxda.do?method=editCxda";
		request.setAttribute("path", path);
		return mapping.findForward("edit");
	}
	
	//���ŵ��������޸Ľ������
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ճ�����-���ŵ���-���ŵ���-�޸�XN:{xn},XQ:{xq},XH:{xh},CXFS:{cxfs},ID:{id}")
	public ActionForward saveCxda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxdaForm model = (CxdaForm) form;
		
		boolean result = false;
	    User user = getUser(request);
		result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//���ŵ����������
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxdaForm model = (CxdaForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		
		//��Ϊѧ�����ƺ������סԭ��ֻ�ܻ�ȡ����ֵ���������ѭ������setֵ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ճ�����-���ŵ���-���ŵ���-����XN:{xn},XQ:{xq},XH:{xh},CXFS:{cxfs}")
	public ActionForward saveCxdaAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] xh = request.getParameterValues("xh");
		String[] cxfs = request.getParameterValues("cxfs");
		String[] bz = request.getParameterValues("bz");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String message=null;
		int xhlen = xh.length;
		ArrayList list = new ArrayList();
		for(int i=0;i<xhlen;i++){
			CxdaForm model = new CxdaForm();
			model.setXh(xh[i]);
			model.setCxfs(cxfs[i]);
			model.setXn(xn);
			model.setXq(xq);
			model.setBz(bz[i]);
			boolean flag = this.saveAddEve(model);
			if(flag == false){
				list.add(model.getXh());
			}
		}
		if(list.size()==0){
			String messageKey = MessageKey.SYS_SAVE_SUCCESS;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			String xhs = "";
			for(int i = 0;i<list.size();i++){
				xhs += list.get(i).toString()+",";
			}
			message = "ѧ��"+xhs+"��"+xn+"ѧ�����м�¼�������ظ���д��";
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	public boolean saveAddEve(CxdaForm model) throws Exception{
		boolean flag = false;
		boolean isExist = service.checkExistForSave(model);
		if (!isExist) {
			boolean result = service.runInsert(model);
		    if(result){
		    	flag = true;
		    }
		}
		
		return flag;
	}

		
	
}


