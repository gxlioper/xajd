/**
 * @���ţ�ѧ����Ʒ��ҵ��
 * @���ڣ�2016��11��17��
 */  
package com.zfsoft.xgxt.xstgl.stglzjsr;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.qgzx.jtff.JtffService;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl.StlbglService;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/**
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ����Ź���-���Ź���(�㽭����)-���Ź��� 
 * @�๦��������
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2017��4��24��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class StglAction extends SuperAction<StglForm,StglService> {
	private StglService service = new  StglService();
	private StlbglService stlbService = new StlbglService();
	private JtffService jtffService=new JtffService();
	private static final String url = "stgl_zjsr_stgl.do";
	
	/**
	 * @���������Ź���ҳ����ת
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	public ActionForward stglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stglList");
	}
	
	/**
	 * @���������Ź��� �б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	public ActionForward getStglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm model = (StglForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	public ActionForward stglAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("stlbList", stlbService.getStlbList());
		request.setAttribute("bmList", jtffService.getBmList());
		
		return mapping.findForward("stglAdd");
	}
	
	/**
	 * @�������༭
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	//@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	//@SystemLog(description="������������ѯ-��������ѵ-��������ѵά��-�༭PXID:{pxid}")
	public ActionForward stglEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		HashMap<String,String>stglMap = service.getStgl(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(stglMap));
		request.setAttribute("szxm", stglMap.get("szxm"));
		request.setAttribute("cwfzrxm", stglMap.get("cwfzrxm"));
		
		request.setAttribute("stlbList", stlbService.getStlbList());
		request.setAttribute("bmList", jtffService.getBmList());
		
		return mapping.findForward("stglEdit");
	}
	
	/**
	 * @����������/�༭ ����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	public ActionForward stglSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		// Ψһ���ж�
		if (!service.checkExist(myForm)) {
			boolean result =StringUtils.isNull(myForm.getId())?service.runInsert(myForm):service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_AUD_DOUBLE, false));
		}
		return null;
	}
	
	public ActionForward stglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		HashMap<String,String>stglMap = service.getStgl(myForm);
		request.setAttribute("stglMap", stglMap);
		
		return mapping.findForward("stglView");
	}
	
	/**
	 * @����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm model = (StglForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model);// ��ѯ�����м�¼������ҳ
		 
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @������ɾ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	//@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	//@SystemLog(description="������������ѯ-��������ѵ-��������ѵά��-ɾ��:PXID{values}")
	public ActionForward stglDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "stglStsq.do?method=getStu";
		request.setAttribute("path", path);
		request.setAttribute("type", request.getParameter("type"));
		return mapping.findForward("getStu");
	}
	
	public ActionForward getStuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	//�����������ӻ�ȡ��ʦ�б�
	
	public ActionForward getTea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "stglStsq.do?method=getTea";
		request.setAttribute("path", path);
		return mapping.findForward("getTea");
	}
	
	public ActionForward getTeaList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getTeaxxList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	
}