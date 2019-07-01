/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����03:10:51 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszForm;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����03:13:09 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcsqAction extends SuperAction<DazcsqForm,DazcsqService>{
	private final String url = "xsxx_dagl_dazcsq.do";
	private DazcsqService service = new DazcsqService();
	
	private final static String DAZC = "dazc";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*ѧ��������Ϣ��ʾ����*/
		jbxxList = bdpzService.getJbxxpz(DAZC);
	}
	
	/**
	 * @����: ���ز�ѯҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-8 ����11:47:49
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
	@SystemLog(description = "����ѧ����Ϣ-��������-����ת������-��ѯҳ��")
	public ActionForward getDazcsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*����������Ϣ*/
		DazccsszService dazccsszService = new DazccsszService();
		DazccsszForm dazccsszForm = dazccsszService.getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		/*ȡ�������*/
		String splc = service.getSplc().get("splc");
		request.setAttribute("splc", splc);
		
		/*����path*/
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dazcsqList");
	}
	
	/**
	 * @����: ����ת�����뷵��Json����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-8 ����02:09:35
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת������-��ѯ����")
	public ActionForward dazcsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm model = (DazcsqForm)form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//��ѯ
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: ����ҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-8 ����03:52:29
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת������-����")
	public ActionForward dazcsqApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm model = (DazcsqForm)form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		/*ȡϵͳ��ǰʱ�䣬��ʽ��2018-01-01*/
		String DATE_FORMAT = "yyyy-MM-dd";
		String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
		request.setAttribute("maxtime", maxtime);
		
		/*����������Ϣ*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		/*ѧ��������Ϣ��ʾ����*/
		request.setAttribute("jbxxList", jbxxList);
		
		String path = "dagl_dazcsq.do?method=dazcsqApply";
		request.setAttribute("path", path);
		
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("dazcsqApply");
	}
	
	/**
	 * @����: �޸�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����03:36:49
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת������-�޸�")
	public ActionForward dazcsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm dazcsqForm = (DazcsqForm)form;
		DazcsqForm model = service.getModel(dazcsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(dazcsqForm, StringUtils.formatData(model));
			
			if (!StringUtil.isNull(model.getXh())){
				/*����ѧ��������Ϣ*/
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			
			/*ȡϵͳ��ǰʱ�䣬��ʽ��2018-01-01*/
			String DATE_FORMAT = "yyyy-MM-dd";
			String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
			request.setAttribute("maxtime", maxtime);
			
			/*����������Ϣ*/
			DazccsszForm dazccsszForm = new DazccsszService().getModel();
			request.setAttribute("dazccsszForm", dazccsszForm);
			
			/*ѧ��������Ϣ��ʾ����*/
			request.setAttribute("jbxxList", jbxxList);
			
			String path = "dagl_dazcsq.do?method=dazcsqUpdate";
			request.setAttribute("path", path);
			
			/*����ת����ʽ*/
			request.setAttribute("zcfs", dazcsqForm.getZcfs());
		}
		return mapping.findForward("dazcsqUpdate");
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����01:49:06
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת������-����:ѧ��:{xh}")
	public ActionForward dazcsqApplySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm  model = (DazcsqForm)form;
		/**��ȡ�û�*/
		User user = getUser(request);
		boolean rs = true;
		try{
			rs = service.saveFormDazcsq(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �鿴
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����05:42:34
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת������-�鿴")
	public ActionForward dazcsqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm myForm = (DazcsqForm)form;
		DazcsqForm model = service.getModel(myForm);
		
		if(null != model){
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		/*ѧ��������Ϣ��ʾ����*/
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(DAZC);
		request.setAttribute("jbxxList", jbxxList);
		
		/*��������ID���ѧ��������Ϣ*/
		HashMap<String,String> xxckList = service.getInfoBySqid(model.getSqid());
		request.setAttribute("rs", xxckList);
		
		/*����������Ϣ*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		return mapping.findForward("dazcsqView");
	}
	
	/**
	 * @����: ɾ��
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-5-9 ����02:24:55
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת������-ɾ��VALUES��{values}")
	public ActionForward dazcsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				/*������������������ݶ༶�˻�ʱ���û��޸� ���˻����ݲ�������ݸ������Ȼ�����ɾ��������xg_xtwh_shztb�оͲ�������������*/
				service.delShztbData(ids);
			}
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����: �ύ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:33:50
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת������-�ύ VALUES��{values}")
	public ActionForward dazcsqSubmit (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			boolean result = false;
			int okNum = 0;
			//����ID��ѯѧ���걨��Ŀ����Ϣ
			List<HashMap<String,String>> dataList = service.getInfoBySqid(values.split(","));
			for(int i = 0; i < dataList.size(); i++){
				HashMap<String,String> dataMap = dataList.get(i);
				String sqid = dataMap.get("sqid");
				String splcid = dataMap.get("splcid");
				String xh = dataMap.get("xh");
				result = service.plSubmit(sqid,splcid,xh);
				if (result) {
					okNum++;
				}
			}
			String resultMsg = "�ύ�ɹ�"+okNum+"����";
			response.getWriter().print(getJsonMessage(resultMsg));
		}
		return null;
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:43:43
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת������-���� VALUES��{values}")
	public ActionForward dazcsqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*��ȡ��ѡֵ������id������id��*/
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		
		boolean result = true;
		
		/*ֻ�е�һ��δ��˵�δ�ύ״̬���ݣ������˿��Գ���*/
		result = service.cancelRecord(sqid, lcid);
		if(result){
			DazcsqForm model = new DazcsqForm();
			model.setSqid(sqid);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-9 ����02:49:07
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת������-����")
	public ActionForward dazcsqExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		DazcsqForm model = new DazcsqForm();
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*��ѯ�����м�¼������ҳ*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);

		/*�������ܴ���*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*��ǰ����Ա*/
		exportModel.setZgh(user.getUserName());
		/*��������*/
		exportModel.setDataList(resultList);
		/*���õ�ǰ�������ܱ��*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*���ɵ����ļ�*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}

}
