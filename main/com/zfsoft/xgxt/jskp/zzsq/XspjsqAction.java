/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-11 ����09:09:20 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
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
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
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
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧϰ���۹���ģ��
 * @�๦������: ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-11 ����09:09:20 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjsqAction extends SuperAction<XspjsqForm,XspjsqService>{
	private final String url = "xspj_xspj_xspjsq.do";
	private final String XSPJ = "xspj";
	private XspjsqService service = new XspjsqService();
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ���������.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "δ�ϴ��ļ���";
	public static final String EXCELREPEAT = "Excle�д����ظ�����(������(ѧ��)����Ŀ���ơ�����ʱ���ظ�)������ϸ�˶ԣ�";
	
	/**
	 * @����: ��ѯҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-11 ����10:30:46
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
	@SystemLog(description = "����ѧϰ����-��������")
	public ActionForward getXspjsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		User user = getUser(request);
		//ֻ��2017�����Ժ��ѧ���ſ�������
		boolean checkStuNj = service.getCheckStuNj(user);
		if(!checkStuNj){
			String msg = "��ģ�������2017�����Ժ��ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//����URL
		request.setAttribute("path", url);
		//ȡ��������,���ڿ���û�����������ʱ����ʾ������Ϣ
		String splc = service.getSplcForParam("sb").get("splc");
		request.setAttribute("splc", splc);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xspjsqList");
	}
	
	/**
	 * @����: ����json����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-11 ����11:58:00
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
	@SystemLog(description = "����ѧϰ����-��ѯ������������")
	public ActionForward seachForXspjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		XspjsqForm model = (XspjsqForm)form;
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
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-12 ����10:48:51
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
	@SystemLog(description = "����ѧϰ����-�������밴ť")
	public ActionForward xspjsqApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*ȡϵͳ��ǰʱ�䣬��ʽ��2018-01-01*/
		String DATE_FORMAT = "yyyy-MM-dd";
		String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
		request.setAttribute("maxtime", maxtime);
		
		/*��ȡ�û���Ϣ*/
		User user = getUser(request);
		request.setAttribute("userName", user.getUserName());
		
		/*��ȡ��Ŀ�����ϢList(ֻ������������)*/
		DmwhService dmwhService = new DmwhService();
		List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListByNlsy();
		request.setAttribute("xmlbList", xmlbList);
		
		/*��ȡ��ѧ����ϢList*/
		List<HashMap<String,String>> dxqList = service.getDxqInfoList();
		request.setAttribute("dxqList", dxqList);
		
		/*ѧ��list*/
		List<HashMap<String,String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		return mapping.findForward("xspjsqApply");
	}
	
	/**
	 * @����: ���뱣��(����ݸ塢�ύ����)
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-12 ����05:26:25
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
	@SystemLog(description="����ѧϰ����-��������-����:ѧ��:{xh},��Ŀ����:{xmmc},����ʱ��:{cysj}")
	public ActionForward xspjsqApplySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqForm model = (XspjsqForm)form;
		/**��ȡ�û�*/
		User user = getUser(request);
		
		boolean rs = true;
		try{
			rs = service.saveFormXspjsq(model,user);
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
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-13 ����09:41:36
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
	@SystemLog(description="����ѧϰ����-��������-ɾ��VALUES��{values}")
	public ActionForward xspjsqDelete(ActionMapping mapping, ActionForm form,
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
	 * @����: �޸�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-13 ����04:19:46
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
	@SystemLog(description = "����ѧϰ����-�����޸İ�ť")
	public ActionForward xspjsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqForm xspjsqForm = (XspjsqForm)form;
		XspjsqForm model = service.getModel(xspjsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(xspjsqForm, StringUtils.formatData(model));
			/*ȡϵͳ��ǰʱ�䣬��ʽ��2018-01-01*/
			String DATE_FORMAT = "yyyy-MM-dd";
			String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
			request.setAttribute("maxtime", maxtime);
			
			/*��ȡ�û���Ϣ*/
			User user = getUser(request);
			request.setAttribute("userName", user.getUserName());
			
			/*��ȡ��Ŀ�����ϢList(ֻ������������)*/
			DmwhService dmwhService = new DmwhService();
			List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListByNlsy();
			request.setAttribute("xmlbList", xmlbList);
			
			/*��ȡ��ѧ����ϢList*/
			List<HashMap<String,String>> dxqList = service.getDxqInfoList();
			request.setAttribute("dxqList", dxqList);
			
			/*ѧ��list*/
			List<HashMap<String,String>> xnList = Base.getXnndList();
			request.setAttribute("xnList", xnList);
		}
		
		/*����ָ�����Ŵ�����ָ����������*/
		String bmmc = service.getBmmcByZdbmdm(model.getZdbmdm());
		request.setAttribute("bmmc", bmmc);
		
		return mapping.findForward("xspjsqUpdate");
	}
	
	/**
	 * @����: �鿴
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-16 ����02:26:21
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
	@SystemLog(description = "����ѧϰ����-�鿴")
	public ActionForward xspjsqView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqForm myForm = (XspjsqForm)form;
		XspjsqForm model = service.getModel(myForm);
		
		if(null != model){
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		/*ѧ��������Ϣ��ʾ����*/
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSPJ);
		request.setAttribute("jbxxList", jbxxList);
		
		/*��������ID���ѧ��������Ϣ*/
		HashMap<String,String> xxckList = service.getInfoBySqid(model.getSqid());
		request.setAttribute("rs", xxckList);
		return mapping.findForward("xspjsqView");
	}
	
	
	/**
	 * @����: �����ύ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-13 ����11:58:20
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
	@SystemLog(description="����ѧϰ����-��������-�ύ VALUES��{values}")
	public ActionForward xspjsqSubmit (ActionMapping mapping, ActionForm form,
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
				String fzr = dataMap.get("xh");
				result = service.plSubmit(sqid,splcid,fzr);
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
	 * @���ڣ�2018-4-13 ����11:12:53
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
	@SystemLog(description="����ѧϰ����-��������-���� VALUES��{values}")
	public ActionForward xspjsqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*��ȡ��ѡֵ������id������id��*/
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		
		boolean result = true;
		
		/*ֻ�е�һ��δ��˵�δ�ύ״̬���ݣ������˿��Գ���*/
		result = service.cancelRecord(sqid, lcid);
		if(result){
			XspjsqForm model = new XspjsqForm();
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
	 * @���ڣ� 2018-4-16 ����10:23:23
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
	@SystemLog(description="����ѧϰ����-��������-����")
	public ActionForward xspjsqExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		XspjsqForm model = new XspjsqForm();
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
	
	/**
	 * @����: ѧ������������Ϣ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-21 ����11:10:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-ѧ������-��������-����ҳ��")
	public ActionForward xspjsqImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("xspjsqImport");
	}
	
	/**
	 * @����: ���ص���ģ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-22 ����08:49:44
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
	@SystemLog(description = "����ѧ������-ѧ������-��������-���ص���ģ��")
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("xspjsq_dataImport.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @����: ���ݵ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-22 ����09:35:48
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
	@SystemLog(description = "����ѧ������-ѧ������-��������-Excle���ݵ���")
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		XspjsqForm model = (XspjsqForm)form;
		/**request���ȡ�û�*/
		User user = getUser(request);
		/*�����˹���*/
		model.setSjlrr(user.getUserName());
		/*����¼��ʱ��*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		FormFile file = model.getDrmb();
		if(file != null){
			try{
				model.setFilepath(servlet.getServletContext().getRealPath("/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model,user);
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
			}catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				logger.info("�����ļ�δ�ҵ���");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				logger.info("IO�쳣��");
				e.printStackTrace();
			}
		}else{
			String message = KFILE;
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		return null;
	}
	
	/**
	 * @����: ������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-22 ����01:58:33
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
	@SystemLog(description = "����ѧ������-ѧ������-��������-������������")
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//�õ�tomcat/webapp/temp/importTemp�´����ļ���·��
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("��������.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
}
