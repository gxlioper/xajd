/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-18 ����11:03:39 
 */  
package com.zfsoft.xgxt.jskp.pjjg;

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
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.zzsq.XspjsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ�����۹���ģ��
 * @�๦������: ѧ�����۽��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-18 ����11:03:39 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjjgAction extends SuperAction<XspjjgForm,XspjjgService>{
	private final String url = "xspj_xspj_xspjjg.do";
	private final String XSPJ = "xspj";
	private XspjjgService service = new XspjjgService();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ���������.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "δ�ϴ��ļ���";
	public static final String EXCELREPEAT = "Excle�д����ظ�����(������(ѧ��)����Ŀ���ơ�����ʱ���ظ�)������ϸ�˶ԣ�";
	
	/**
	 * @����: ��ѯҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-18 ����11:41:53
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
	@SystemLog(description = "����ѧϰ����-���۽��")
	public ActionForward getXspjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		User user = getUser(request);
		//ֻ��2017�����Ժ��ѧ���ſ�������
		XspjsqService xspjsqService = new XspjsqService();
		boolean checkStuNj = xspjsqService.getCheckStuNj(user);
		if(!checkStuNj){
			String msg = "��ģ�������2017�����Ժ��ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//����URL
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xspjjgList");
	}
	
	/**
	 * @����: ����json����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-19 ����10:49:33
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
	public ActionForward seachForXspjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		XspjjgForm model = (XspjjgForm)form;
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
	 * @����: ���Ի�����ҳ��
	 * @���ߣ�  Meng.Wei[���ţ�1186]
	 * @���ڣ�  2018-4-19 ����05:13:09
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
	public ActionForward xspjjgImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("xspjjgImport");
	}
	
	/**
	 * @����: ģ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-20 ����08:54:55
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("xspj_dataImport.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @����: ���뱣��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-20 ����02:03:01
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
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		XspjjgForm model = (XspjjgForm)form;
		/**request���ȡ�û�*/
		User user = getUser(request);
		/*�����˹���*/
		model.setSjlrr(user.getUserName());
		/*����¼��ʱ��*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		/*������Դ��1:������ˡ�2:������ӡ�3:���롿*/
		model.setSjly("3");
		
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
	 * @���ڣ� 2018-4-20 ����05:34:19
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
	
	/**
	 * @����: ���ӷ���ҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-23 ����07:42:08
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
	public ActionForward xspjjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqService xspjsqService = new XspjsqService();
		
		/*ȡϵͳ��ǰʱ�䣬��ʽ��2018-01-01*/
		String DATE_FORMAT = "yyyy-MM-dd";
		String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
		request.setAttribute("maxtime", maxtime);
		
		/*��ȡ�û���Ϣ*/
		User user = getUser(request);
		request.setAttribute("userName", user.getUserName());
		
		/*��ȡ��Ŀ�����ϢList(˼����������)*/
		DmwhService dmwhService = new DmwhService();
		List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListBySzsz();
		request.setAttribute("xmlbList", xmlbList);
		
		/*��ȡ��ѧ����ϢList*/
		List<HashMap<String,String>> dxqList = xspjsqService.getDxqInfoList();
		request.setAttribute("dxqList", dxqList);
		
		/*ѧ��list*/
		List<HashMap<String,String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		
		return mapping.findForward("xspjjgAdd");
	}
	
	/**
	 * @����: �����޸ı���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-24 ����09:44:03
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
	@SystemLog(description="����ѧϰ����-���۽��-����:ѧ��:{xh},��Ŀ����:{xmmc},����ʱ��:{cysj}")
	public ActionForward xspjjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm model = (XspjjgForm)form;
		/**��ȡ�û�*/
		User user = getUser(request);
		
		boolean rs = true;
		try{
			rs = service.saveFormXspjjg(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �޸�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-24 ����11:18:53
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
	@SystemLog(description = "�������۽��-�����޸İ�ť")
	public ActionForward xspjjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm xspjjgForm = (XspjjgForm)form;
		XspjjgForm model = service.getModel(xspjjgForm);
		XspjsqService xspjsqService = new XspjsqService();
		
		if(model != null){
			BeanUtils.copyProperties(xspjjgForm, StringUtils.formatData(model));
			
			/*ȡϵͳ��ǰʱ�䣬��ʽ��2018-01-01*/
			String DATE_FORMAT = "yyyy-MM-dd";
			String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
			request.setAttribute("maxtime", maxtime);
			
			/*��ȡ�û���Ϣ*/
			User user = getUser(request);
			request.setAttribute("userName", user.getUserName());
			
			/*��ȡ��Ŀ�����ϢList(ֻ��˼����������)*/
			DmwhService dmwhService = new DmwhService();
			List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListBySzsz();
			request.setAttribute("xmlbList", xmlbList);
			
			/*��ȡ��ѧ����ϢList*/
			List<HashMap<String,String>> dxqList = xspjsqService.getDxqInfoList();
			request.setAttribute("dxqList", dxqList);
			
			/*ѧ��list*/
			List<HashMap<String,String>> xnList = Base.getXnndList();
			request.setAttribute("xnList", xnList);
		}
		/*����ָ�����Ŵ�����ָ����������*/
		String bmmc = xspjsqService.getBmmcByZdbmdm(model.getZdbmdm());
		request.setAttribute("bmmc", bmmc);
		
		return mapping.findForward("xspjjgUpdate");
	}
	
	/**
	 * @����: ɾ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-24 ����02:16:54
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
	@SystemLog(description="����ѧ������-���۽��-ɾ��-VALUES:{values}")
	public ActionForward xspjjgDelete(ActionMapping mapping, ActionForm form,
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
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-24 ����03:01:30
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
	@SystemLog(description="����ѧϰ����-���۽��-����")
	public ActionForward xspjjgExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm model = (XspjjgForm)form;
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
	 * @����: �鿴
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-24 ����08:12:10
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
	@SystemLog(description = "�������۽��-�鿴")
	public ActionForward xspjjgView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm myForm = (XspjjgForm)form;
		XspjjgForm model = service.getModel(myForm);
		
		if(null != model){
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String, String>> singleSummary = service.getSingleSummary(model.getXh());
			request.setAttribute("singleSummary", singleSummary);
		}
		
		/*ѧ��������Ϣ��ʾ����*/
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSPJ);
		request.setAttribute("jbxxList", jbxxList);
		
		/*��������ID���ѧ��������Ϣ*/
		HashMap<String,String> xxckList = service.getInfoByGuid(model.getGuid());
		request.setAttribute("rs", xxckList);
		return mapping.findForward("xspjjgView");
	}
	
	/**
	 * @����: ���۽������ͳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-25 ����02:17:02
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
	public ActionForward dataStatistics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XspjjgForm model = new XspjjgForm();
		
		/*���ɸ߼���ѯ����	*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		User user = getUser(request);
		/*��ѯ�����м�¼������ҳ*/
		List<HashMap<String,String>> resultList = service.getDateForSearchXn(model,user, xn);
		/*���ɵ����ļ�*/
		File file = service.getDataStatisticsFile(resultList,xn);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
