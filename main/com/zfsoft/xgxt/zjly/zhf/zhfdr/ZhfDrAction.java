/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-20 ����10:07:42 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfdr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.zhf.comm.CommUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-20 ����10:07:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfDrAction extends SuperAction<ZhfDrForm, ZhfDrService> {
	private ZhfDrService service = new ZhfDrService();
//	private ZhfCsszService csszService = new ZhfCsszService();
	private static List<HashMap<String, String>> jbxxList = null;
	public static final String ZJLY_ZHF = "zjly_zhf";
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ������¼.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "û���ļ���";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZJLY_ZHF);
	}
	
	private static final String url = "xg_zjly_zhfdr.do";
	
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����11:28:03
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
	public ActionForward getZhfdrCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm model = (ZhfDrForm) form;
		String lb = request.getParameter("lb");
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
			request.setAttribute("searchTj", searchModel);
			if ("dr".equals(lb)) {
				request.setAttribute("path", url);
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("getZhfdrCx");
			}else if ("hzdc".equals(lb)) {
				request.setAttribute("path", "xg_zjly_hzbdc.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("getHzxxdc");
			}else {
				request.setAttribute("path", "xg_zjly_xxsxdc.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("getXxsxdc");
			}
	}
	
	/**
	 * 
	 * @����:����ҳ����ת
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����01:36:23
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
	public ActionForward importJfxmPrepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		//�Ʒִ���
		request.setAttribute("dxlist", new CommUtil().getDxList(user.getUserDep(),user.getUserName()));
		//�Ʒ�С��
		request.setAttribute("xxlist", new CommUtil().getXxList(user.getUserDep(),user.getUserName()));
		return mapping.findForward("drprepare");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-27 ����05:28:53
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
	public ActionForward exportJfxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			ZhfDrForm model = (ZhfDrForm) form;
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			File file = service.getZhfFile(model,user);
			//�����ļ�
			FileUtil.outputExcel(response, file);
		return null;
}
	/**
	 * ��ϸ���� �ϲ����ർ��
	 */
	public ActionForward exportXxsx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm model = (ZhfDrForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXXsxList(model, user);
		File file = service.getZhfXxsx(resultList,model,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
	return null;
}	
	/**
	 * 
	 * @����:���ܱ� �ϲ����ർ��
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-16 ����02:53:42
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
	public ActionForward exportHZsx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm model = (ZhfDrForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.getXXsxList(model, user);
		File file = service.getZhfHzsx(resultList,model,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
	return null;
}	
	/**
	 * 
	 * @����:����ģ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����02:11:25
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
			throws Exception {
		String path = request.getSession().getServletContext().getRealPath(
				"/temp/mb/")+"/zhfdrmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("zhfdrmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @����:���뱣��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-22 ����11:15:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		ZhfDrForm model = (ZhfDrForm) form;
		FormFile file = model.getDrmb();
		model.setUser(getUser(request));
		if(file != null){
			try {
				model.setFilepath(servlet.getServletContext().getRealPath(
				"/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model);
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
				}
				else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
				
			} catch (FileNotFoundException e) {
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
	 * 
	 * @����:���ش�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����02:11:25
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
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
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
	 * 
	 * @����: ɾ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-23 ����11:21:43
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
	public ActionForward delRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		User user  = getUser(request);
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//���ɾ����־
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			service.addlog(time,user,ids);
			int num = service.runDelete(ids);
			String message;
			boolean result = num > 0;
			if (result) {
				message = MessageUtil.getText(MessageKey.SYS_DEL_NUM, num);
			}else {
				message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				service.dellog(ids);
			}
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}  
	
	/**
	 * 
	 * @����:�޸ĵ����¼��δ�󶨵Ĳ����޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-23 ����02:01:41
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
	public ActionForward updateJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm myForm = (ZhfDrForm) form;
		ZhfDrForm model = service.getModel(myForm);
		User user = getUser(request);
		if(null!=model && !"save".equals(myForm.getType())){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if("save".equals(myForm.getType())){
//			myForm
			if(service.checkNotExists(myForm)){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String message = "ѧ�ţ��Ʒ���Ŀ���룬����˵��������ʱ�䲻���ظ���";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "zjly_zhfdr.do?method=updateJg";
		request.setAttribute("path", path);
		request.setAttribute("lrr", user.getUserName());
		request.setAttribute("lrsj", GetTime.getTimeByFormat("yyyy-mm-dd"));
		request.setAttribute("lrrname", user.getRealName());
		//�Ʒִ���
		request.setAttribute("dxlist", new CommUtil().getDxList(user.getUserDep(),user.getUserName()));
		//�Ʒ�С��
		request.setAttribute("xxlist", new CommUtil().getXxList(user.getUserDep(),user.getUserName()));
		request.setAttribute("jfxmdm", model.getJfxmdm());
		request.setAttribute("xmmkdm", model.getXmmkdm());
		return mapping.findForward("updateJg");
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-23 ����04:27:31
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
	public ActionForward viewJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm myForm = (ZhfDrForm) form;
		ZhfDrForm model = service.getModel(myForm);
		if(null!=model && !"save".equals(myForm.getType())){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", service.viewJg(model.getId()));
		String path = "zjly_zhfdr.do?method=viewJg";
		request.setAttribute("path", path);
		return mapping.findForward("viewJg");

	}
	
}
