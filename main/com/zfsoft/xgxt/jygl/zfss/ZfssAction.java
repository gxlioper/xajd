/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-27 ����02:06:21 
 */  
package com.zfsoft.xgxt.jygl.zfss;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������:  
 * @���ߣ� huj
 * @ʱ�䣺 2013-5-27 ����02:06:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZfssAction extends SuperAction{
	
	private static final String url = "jygl_zfss_zfssMg.do";
	
	/**
	 * 
	 * @����:�߷õǼ��б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-30 ����02:18:44
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
	public ActionForward zfssManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		if (QUERY.equals(myForm.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jygl_zfss_zfssMg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zfssList");
	}
	
	/**
	 * 
	 * @����:�����߷õǼ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-30 ����02:19:33
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
	@SystemLog(description="���ʹ�Ԣ����-����Ա�߷ù���-����Ա�߷õǼ�-����ZGH:{zgh},ZBR:{zbr},JRSJ:{jrsj},FWLY:{fwly}")
	public ActionForward addZfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("zfssAdd");
	}
	
	/**
	 * 
	 * @����:�����߷õǼ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-30 ����02:19:55
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
	@SystemLog(description="���ʹ�Ԣ����-����Ա�߷ù���-����Ա�߷õǼ�-�޸�PK:{djid},ZGH:{zgh},ZBR:{zbr},JRSJ:{jrsj},LKSJ:{lksj},FWLY:{fwly}")
	public ActionForward updateZfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
//		RclfdjService rclfsjervice = new RclfdjService();
//		LdglServices lds=new LdglServices();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZfssForm model = service.getModel(myForm);
		
//		request.setAttribute("yqlist", lds.getYqList(""));//԰���б�
//		request.setAttribute("ldlist", rclfsjervice.getLdList(model.getBfwyq()));//¥���б�
//		request.setAttribute("cslist", rclfsjervice.getCsList(model.getBfwld()));//�����б�
//		request.setAttribute("qslist", rclfsjervice.getQsList(model.getBfwld(),model.getBfwcs()));//�����б�
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("zfssUpdate");
	}
	
	/**
	 * 
	 * @����:ɾ���߷õǼ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-30 ����02:20:08
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
	@SystemLog(description="���ʹ�Ԣ����-����Ա�߷ù���-����Ա�߷õǼ�-ɾ��VALUES:{values}")
	public ActionForward delZfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssService service = new ZfssService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����:�߷õǼǻ���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-30 ����02:20:19
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
	public ActionForward zfssCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		if (QUERY.equals(myForm.getType())){
			List<HashMap<String,String>> resultList = service.getZfssCountList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jygl_zfss_zfssCt.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zfssCountList");
	}

	/**
	 * @����:����Ա�߷õǼ��б���
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-11-27����5:25:25
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
	public ActionForward exportZfxxData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm);
		
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
	 * @����:����Ա�߷õǼ�ͳ�Ƶ���
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-10-27 ����7:11:41
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
	public ActionForward exportZfxxCountData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllZfssCountList(myForm);
		
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
}
