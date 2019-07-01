/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����02:04:16 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-6-25 ����02:04:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

@SuppressWarnings("unchecked")
public class ZjsySxhbAction extends SuperAction {
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String SXHB = "sxhb";
	BdpzService bdpzService = new BdpzService();
	
	private static final String url = "zjsy_sxhbList.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsySxhbService service = new ZjsySxhbService();
		ZjsySxhbForm myForm = (ZjsySxhbForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zjsy_sxhbList.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sxhbjg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addSxhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsySxhbForm model = (ZjsySxhbForm) form;
		ZjsySxhbService service = new ZjsySxhbService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(SXHB);
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(model.getXh())) {
			// ��ѧ�Ų�Ϊ�գ�����ѧ��������Ϣ
			HashMap<String, String> xsjbxx = service.getXsdtxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if (SAVE.equalsIgnoreCase(model.getType())) {
				model.setSxhbid(UniqID.getInstance().getUniqIDHash());
				model.setJddm(xsjbxx.get("jddm"));
				boolean flag = service.isHasExists(model);
				if(!flag&&!StringUtil.isNull(model.getJddm())){
					boolean result = service.runInsert(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
				}else{
					response.getWriter().print(
							getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
				}
			    return null;
		   }
		}
		String path = "zjsy_sxhb.do?method=addSxhb";
		request.setAttribute("path", path);
		return mapping.findForward("addSxhb");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward modSxhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsySxhbForm model = (ZjsySxhbForm) form;
		ZjsySxhbService service = new ZjsySxhbService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			HashMap<String, String> xsjbxx =service.getXsdtxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
				boolean result = service.runUpdate(model);
				if(result){
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
				}else{
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
				}
			    return null;
		   }
		ZjsySxhbForm myForm = service.getModel(model.getSxhbid());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(SXHB);
		request.setAttribute("jbxxList", jbxxList);
		String path = "zjsy_sxhb.do?method=updateSxhb";
		request.setAttribute("path", path);
		return mapping.findForward("modSxhb");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delSxhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZjsySxhbService service = new ZjsySxhbService();
		// ���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjsySxhbForm model = (ZjsySxhbForm) form;
		ZjsySxhbService service = new ZjsySxhbService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ

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
}
