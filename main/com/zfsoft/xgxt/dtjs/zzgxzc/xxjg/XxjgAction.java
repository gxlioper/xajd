/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-24 ����03:56:38 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.xxjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;

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
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.zzgxzc.gprz.GprzForm;
import com.zfsoft.xgxt.dtjs.zzgxzc.gprz.GprzService;
import com.zfsoft.xgxt.dtjs.zzgxzc.zcsq.ZcsqSERVICE;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���--��Ϣ���ģ��
 * @�๦������: ��Ϣ���Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��13�� ����10:37:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XxjgAction extends SuperAction<XxjgForm,XxjgService>{
	private static final String url = "dtjs_xxjg.do?method=xxjgList";//���ݿ����
	private final String ZCSQ ="zcsq";
	XxjgService xxjgService = new XxjgService();
	
	/**
	 * @����:��Ϣ�����ѯ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����10:39:21
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
	public ActionForward xxjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XxjgForm xxjgForm = (XxjgForm)form;
		
		if (QUERY.equalsIgnoreCase(xxjgForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			xxjgForm.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = xxjgService.getPageList(xxjgForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxjgList");
	}
	
	/**
	 * 
	 * @����:��Ϣ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����4:53:47
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ���-����XH:{xh},SZDZB:{szdzb},"
			+ "SFSN:{sfsn},JSDZZ:{jsdzz},SQDW:{sqdw},DFJZRQ:{dfjzrq},SFKJHYZM:{sfkjhyzm},JSXBH:{jsxbh}")
	public ActionForward xxjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XxjgForm xxjgForm = (XxjgForm)form;
		if (SAVE.equalsIgnoreCase(xxjgForm.getType())){
			if(xxjgService.isExist(xxjgForm)){
				//��ѧ���Ѵ��ڼ�¼
				String messageKey = MessageKey.DTJS_ZZGXZC_XXJG_EXIST;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			if(xxjgService.isJsxbhRepeat(xxjgForm)){
				//�����ű���ظ�ʹ��
				String messageKey = MessageKey.DTJS_ZZGXZC_XXJG_JSXBH_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			//���ѽ������ڴ���תΪÿ�µ����һ�죩
			xxjgForm.setDfjzrq(DateUtils.getLastDayOfMonth(xxjgForm.getDfjzrq()));
			
			boolean result = xxjgService.runInsert(xxjgForm);
			String messageKey = null;
			if(result){
				messageKey = MessageKey.SYS_SAVE_SUCCESS;
				
				//���������־
				GprzService gprzService = new GprzService();
				String xgsj = DateUtils.getCurrDate();
				String xgr = getUser(request).getUserName();
				String xghjl = gprzService.getXgqhjl(xxjgForm);
				GprzForm gprzForm = new GprzForm();
				gprzForm.setXgsj(xgsj);
				gprzForm.setXgr(xgr);
				gprzForm.setXh(xxjgForm.getXh());
				gprzForm.setXghjl(xghjl);
				gprzService.runInsert(gprzForm);
			}else{
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		if (!StringUtil.isNull(xxjgForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xxjgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		String path = "dtjs_xxjg.do?method=xxjgAdd";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",new ZcsqSERVICE().getDzbList());
		return mapping.findForward("xxjgAdd");
	}
	
	/**
	 * 
	 * @����:��Ϣ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����4:53:47
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ���-�޸�XH:{xh},SZDZB:{szdzb},"
			+ "SFSN:{sfsn},JSDZZ:{jsdzz},SQDW:{sqdw},DFJZRQ:{dfjzrq},SFKJHYZM:{sfkjhyzm},JSXBH:{jsxbh},GPYY:{gpyy}")
	public ActionForward xxjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XxjgForm xxjgForm = (XxjgForm)form;
		XxjgForm xf = xxjgService.getModel(xxjgForm.getJgid());
		
		if (UPDATE.equalsIgnoreCase(xxjgForm.getType())){
			if(xxjgService.isJsxbhRepeat(xxjgForm)){
				//�����ű���ظ�ʹ��
				String messageKey = MessageKey.DTJS_ZZGXZC_XXJG_JSXBH_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			//���ѽ������ڴ���תΪÿ�µ����һ�죩
			xxjgForm.setDfjzrq(DateUtils.getLastDayOfMonth(xxjgForm.getDfjzrq()));
			
			boolean result = xxjgService.runUpdate(xxjgForm);
			String messageKey = null;
			if(result){
				messageKey = MessageKey.SYS_SAVE_SUCCESS;
				
				//���������־
				GprzService gprzService = new GprzService();
				String xgsj = DateUtils.getCurrDate();
				String xgr = getUser(request).getUserName();
				String [] xgqhjl = gprzService.getXgqhjl(xf,xxjgForm);
				String xgqjl = xgqhjl[0];
				String xghjl = xgqhjl[1];
				
				GprzForm gprzForm = new GprzForm();
				gprzForm.setXgsj(xgsj);
				gprzForm.setXgr(xgr);
				gprzForm.setXh(xxjgForm.getXh());
				gprzForm.setXgqjl(xgqjl);
				gprzForm.setXghjl(xghjl);
				gprzForm.setGpyy(request.getParameter("gpyy"));
				
				if(!StringUtil.isNull(xghjl)){
					gprzService.runInsert(gprzForm);
				}
				
			}else{
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		if (!StringUtil.isNull(xxjgForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xxjgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		BeanUtils.copyProperties(xxjgForm, xf);
		
		//ѧ��������Ϣ��ʾ����
		String path = "dtjs_xxjg.do?method=xxjgUpdate";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",new ZcsqSERVICE().getDzbList());
		
		return mapping.findForward("xxjgUpdate");
	}
	
	/**
	 * 
	 * @����:��Ϣ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����4:53:47
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
	public ActionForward xxjgShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XxjgForm xxjgForm = (XxjgForm)form;
		XxjgForm xf = xxjgService.getModel(xxjgForm.getJgid());
		BeanUtils.copyProperties(xxjgForm, xf);
		
		if (!StringUtil.isNull(xxjgForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xxjgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		String path = "dtjs_xxjg.do?method=xxjgShow";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("xxjgShow");
	}
	
	/**
	 * 
	 * @����:��Ϣ���ɾ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����4:53:47
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ���-ɾ��VALUES:{values}")
	public ActionForward xxjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			String [] ids = values.split(",");
			
			//ɾ��ǰ��ѯ�����Ϣ�����ڲ��������־��
			GprzService gprzService = new GprzService();
			String xgsj = DateUtils.getCurrDate();
			String xgr = getUser(request).getUserName();
			List<XxjgForm> xxjgFormList = xxjgService.getXxjgFormList(ids);
			List<GprzForm> gprzFormList = new ArrayList<GprzForm>();
			for(XxjgForm xxjgForm:xxjgFormList){
				String xgqjl = gprzService.getXgqhjl(xxjgForm);
				GprzForm gprzForm = new GprzForm();
				gprzForm.setXgsj(xgsj);
				gprzForm.setXgr(xgr);
				gprzForm.setXgqjl(xgqjl);
				gprzForm.setXh(xxjgForm.getXh());
				gprzFormList.add(gprzForm );
			}
			//���� ����
			gprzService.saveGprzFormList(gprzFormList);
			
			int num = xxjgService.runDelete(ids);
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
	 * @����:��Ϣ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����4:57:05
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XxjgForm xxjgForm = (XxjgForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		xxjgForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = xxjgService.getAllList(xxjgForm,user);//��ѯ�����м�¼������ҳ
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = xxjgForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����: ����֯��ϵת�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����08:53:01
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
	public ActionForward zzgxdjbDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		File wordFile = getWord(id,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����: ����֯��ϵת�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����08:53:01
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
	public ActionForward zzgxdjbDcTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����: getword ��ȡword
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-19 ����08:58:11
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
	public File getWord(String id,HttpServletRequest request) throws Exception{
		XxjgService service = new XxjgService();
		HashMap<String,String> jgMap = service.getDzcgxJgMap(id);
		Map<String,Object> data = new HashMap<String,Object>();
		data.putAll(jgMap);
		File file = null;
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"dtxx/tzzgxzcb.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"dtxx","tzzgxzcb.xml",FreeMarkerUtil.getFileName(data.get("xm")+"_��������Ա��֯��ϵ������"));
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return file;
	}
	

}
