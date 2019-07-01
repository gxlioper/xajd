/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-10-30 ����03:50:02 
 */  
package xgxt.dtjs.tsdzb;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;


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
import com.zfsoft.xgxt.gygl.zzdgl.sq.ZzdsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ɫ��֧��aciton(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-10-30 ����03:50:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsdzbAction extends SuperAction<TsdzbForm, TsdzbService>{
	private static final String url = "dtjs_tsdzbgl_tsdzb.do";
	
	private static final String STU = "stu";
	
	private static final String TEA = "tea";
	
	
	private TsdzbService service = new TsdzbService();
	
	/** 
	 * @����:��ȡ��ɫ��֧���б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����08:46:29
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
	public ActionForward getTsdzbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
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
		String path = "dtjs_tsdzbgl_tsdzb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsdzbList");
	}
	
	/** 
	 * @����:������ɫ��֧��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����01:46:55
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
	public ActionForward addTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		String cjsj = GetTime.getTimeByFormat("yyyy-mm-dd");
		String userName = user.getUserName();
		String realName = user.getRealName();
		model.setCjrzgh(userName);
		model.setCjsj(cjsj);
		model.setCjrxm(realName);
		if("stu".equalsIgnoreCase(user.getUserStatus())){
			model.setCjrlx(STU);
		}else{			
			model.setCjrlx(TEA);
		}
		if("xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus())){
			request.setAttribute("njList", service.getNjList());
			request.setAttribute("xyList", service.getXyList(user.getUserStatus(),user.getUserName()));
		}
		List<HashMap<String, String>> bjList = service.getBjList(user,null);
		request.setAttribute("bjList", bjList);
		return mapping.findForward("addTsdzb");
	}
	
	
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����03:34:41
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
	@SystemLog(description="���ʵ��Ž���-��ɫ��֧������-��ɫ��֧��ά��-����DZBMC:{dzbmc},FZR:{fzr},CJSJ:{cjsj}")
	public ActionForward saveJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		boolean result;
		if("save".equalsIgnoreCase(model.getType())){
			if(service.isExist(model.getDzbmc(), model.getDzbid())){				
				result = service.addTsdzb(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));			
			}else{
				String messageKey = MessageKey.XG_DTJS_TSDZB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
		}else{
			if(service.isExist(model.getDzbmc(), model.getDzbid())){
				result = service.updateTsdzb(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				String messageKey = MessageKey.XG_DTJS_TSDZB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}			
		}
		return null;
	}
	
	/** 
	 * @����:�޸ĵ�֧��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����03:44:57
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
	public ActionForward editTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		TsdzbForm editModel = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(editModel));
		List<String> list = service.getBjListBydzbId(model.getDzbid());
		if(null != list && list.size() > 0){
			model.setBjdms(list.toArray(new String[]{}));
		}
		if("xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus())){
			request.setAttribute("njList", service.getNjList());
			request.setAttribute("xyList", service.getXyList(user.getUserStatus(),user.getUserName()));
		}
		List<HashMap<String, String>> bjList = service.getBjList(user,model.getDzbid());
		request.setAttribute("bjList", bjList);
		return mapping.findForward("editTsdzb");
	}
	
	/** 
	 * @����:ɾ����֧��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����07:18:05
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
	public ActionForward delTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			service.delGlByIds(ids);
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
	
	/** 
	 * @����:�鿴(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����07:48:52
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
	public ActionForward viewTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		TsdzbForm editModel = service.getModel(model);
		BeanUtils.copyProperties(model, editModel);
		return mapping.findForward("viewTsdzb");
	}
	
	/**
	 * @description	����������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-2 ����09:11:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsdzbForm model = (TsdzbForm)form;
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
	
	/**
	 * @description	�� ��ȡרҵ�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����11:49:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		List<HashMap<String,String>> zyList=service.getZyList(model.getXydm(), user.getUserStatus(),user.getUserName());
		JSONArray jsonArr = JSONArray.fromArray(zyList.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;	
	}
	
	/**
	 * @description	�� ��ȡ�༶�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����01:42:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		List<HashMap<String,String>> bjList=service.getbjList(model.getDzbid(),model.getXydm(), model.getZydm(), model.getNjdm(), user.getUserStatus(), user.getUserName());
		JSONArray jsonArr = JSONArray.fromArray(bjList.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;	
	}
}
