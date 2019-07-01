/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:53:25 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

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
 * @�๦������: ����ת��-���
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:54:21 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcjgAction extends SuperAction<DazcjgForm,DazcjgService>{
	private final String url = "xsxx_dagl_dazcjg.do";
	private DazcjgService service = new DazcjgService();
	private final static String DAZC = "dazc";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*ѧ��������Ϣ��ʾ����*/
		jbxxList = bdpzService.getJbxxpz(DAZC);
	}
	
	/**
	 * @����: ���ز�ѯ�б�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����02:30:25
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
	@SystemLog(description = "����ѧ����Ϣ-��������-����ת�����-��ѯҳ��")
	public ActionForward getDazcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*����������Ϣ*/
		DazccsszService dazccsszService = new DazccsszService();
		DazccsszForm dazccsszForm = dazccsszService.getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		/*����path*/
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dazcjgList");
	}
	
	/**
	 * @����: ����ת���������Json����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����02:38:33
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת�����-��ѯ����")
	public ActionForward dazcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
		
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
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����02:53:43
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת�����-����")
	public ActionForward dazcjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
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
		
		String path = "dagl_dazcjg.do?method=dazcjgAdd";
		request.setAttribute("path", path);
		
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("dazcjgAdd");
	}
	
	/**
	 * @����: �޸�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����05:06:26
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת�����-�޸�")
	public ActionForward dazcjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm dazcjgForm = (DazcjgForm)form;
		DazcjgForm model = service.getModel(dazcjgForm);
		if(model != null){
			BeanUtils.copyProperties(dazcjgForm, StringUtils.formatData(model));
			
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
			request.setAttribute("zcfs", dazcjgForm.getZcfs());
			
			/*�����ʼ�״̬*/
			String yjzt = null;
			if(StringUtils.isNull(dazcjgForm.getYjzt())){
				/*���5��ʵûʲô����
				 *��ת����ʽΪ�Դ�ʱ��jsp�ϵ�var yjzt = ${yjzt};ȡֵ��Ϊ�գ��ᵼ���޸ĳ����⣬����д�˸�ֵ*/
				yjzt = "5";
			}else{
				yjzt = dazcjgForm.getYjzt();
			}
			request.setAttribute("yjzt", yjzt);
		}
		return mapping.findForward("dazcjgUpdate");
	}
	
	/**
	 * @����: ����ת���������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����04:00:03
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת�����-����:ѧ��:{xh},ת����ʽ:{zcfs}")
	public ActionForward dazcjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
		/**��ȡ�û�*/
		User user = getUser(request);
		
		boolean rs = true;
		try{
			rs = service.saveFormDazcjg(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: ɾ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����05:05:07
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת�����-ɾ��-VALUES:{values}")
	public ActionForward dazcjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");

		if(!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����: �鿴
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����05:19:05
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
	@SystemLog(description = "����ѧ����Ϣ-����ת������-����ת�����-�鿴")
	public ActionForward dazcjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm myForm = (DazcjgForm)form;
		
		/*����ѧ��������Ϣ*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		/*���ݽ��ID���ѧ��������Ϣ*/
		HashMap<String,String> xxckList = service.getInfoByGuid(myForm.getXh());
		request.setAttribute("rs", xxckList);
		
		/*����ѧ�Ų鿴������Ƿ�������*/
		String countRs = service.getDazcjgRs(myForm.getXh());
		request.setAttribute("countRs", countRs);
		
		/*����������Ϣ*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		return mapping.findForward("dazcjgView");
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����05:08:55
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
	@SystemLog(description="����ѧ����Ϣ-����ת������-����ת�����-����")
	public ActionForward dazcjgExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
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
