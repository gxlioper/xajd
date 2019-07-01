/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-19 ����10:03:50 
 */  
package com.zfsoft.xgxt.zjly.ylbx;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ��չ���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-19 ����10:03:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YlbxAction extends SuperAction<YlbxForm,YlbxService> {
	private YlbxService service = new  YlbxService();
	private static final String url = "xg_zjly_ylbx.do";
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ������¼.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "û���ļ���";
	public ActionForward getYlbxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm model = (YlbxForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if ("stu".equals(user.getUserType())){
			// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
			return mapping.findForward("xscx");
		}else {
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
			return mapping.findForward("cx");
		}
	}
	
	
	
	/**
	 * @����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��9��12�� ����9:29:30
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
	@SystemLog(description="�����㽭����-ҽ�Ʊ���-ҽ�Ʊ���-����XH:{xh}")
	public ActionForward Ylbxadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm myForm = (YlbxForm) form;
		YlbxService service=new YlbxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// Ψһ���ж�
			boolean isExist = service.isExistSame(myForm);
			if (!isExist) {
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSXWKH_JBFGL_ADD_EXIST, false));
			}
			return null;
		}
		HashMap<String, String> map=new HashMap<String, String>();
		String currxn=Base.currXn;
		map.put("xn", currxn);
		List<HashMap<String, String>> xnList=Base.getXnndList();
		request.setAttribute("map", map);
		request.setAttribute("xnList", xnList);
		//ѧ��������Ϣ�б�
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		//ѧ����Ϣѡ������¼���
		String path = "zjly_ylbx.do?method=Ylbxadd";
		request.setAttribute("path", path);
		
		return mapping.findForward("add");
	}
	
	/**
	 * @�������༭
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��9��12�� ����6:00:58
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
	@SystemLog(description="�����㽭����-ҽ�Ʊ���-ҽ�Ʊ���-�༭XH:{xh}")
	public ActionForward Ylbxedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm myForm = (YlbxForm) form;
		YlbxService service=new YlbxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// Ψһ���ж�
			boolean isExist = service.isExistSame(myForm);
			if (!isExist) {
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSXWKH_JBFGL_ADD_EXIST, false));
			}
			return null;
		}
		//��ѯ
		YlbxForm model = service.getModel(myForm);
		//��������
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//ѧ��������Ϣ�б�
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String, String>> xnList=Base.getXnndList();
		request.setAttribute("xnList", xnList);
		
		return mapping.findForward("edit");
	}
	
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxForm model = (YlbxForm) form;

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
	
	
	
	
	
	
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_SAVE_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	public ActionForward ylbxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm myform = (YlbxForm) form;
		if(null!=myform){
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myform.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		//��ѯ
		YlbxForm model = service.getModel(myform);
		BeanUtils.copyProperties(myform,model);
		
		return mapping.findForward("ck");
	}
	
	/**
	 * @��������ȡ��ѧ�����²�������Ϣ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward loadXbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh=request.getParameter("xh");
		//��ѧ������ҽ����Ϣ��������
		List<HashMap<String, String>> resultList=service.getXbxxList(xh);
		if(null==resultList||0==resultList.size()){
			return null;
		}
		HashMap<String, String> xbxx=resultList.get(0);
		response.getWriter().print(JSONObject.fromObject(StringUtils.formatData(xbxx)));
		
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-5-3 ����09:16:25
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
	public ActionForward importYlbx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		return mapping.findForward("drjsp");
	}
	/**
	 * 
	 * @����:���ص���ģ��
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-5-3 ����09:37:39
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
				"/temp/mb/")+"/zjlyylbxmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("zjlyylbxmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
/**
 * 
 * @����:���뱣��
 * @���ߣ�CP[���ţ�1352]
 * @���ڣ�2017-5-3 ����10:33:05
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws IOException
 * ActionForward �������� 
 * @throws
 */
public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws IOException{
	YlbxForm model = (YlbxForm) form;
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
					map.put("message", "�ɹ����롾"+resultMap.get("zqts")+"����!");
					map.put("cw","none");
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
			}else if (resultMap.get("result").equals("sjcf")) {//�����������ظ����г��ظ�ѧ��
			    Map<String,String> map = new HashMap<String, String>();
				map.put("message", "����������ظ����ݡ�"+resultMap.get("cwts")+"��������Աȡ���������.xls�����ĺ����µ���!");
				map.put("gid", (String)resultMap.get("gid"));
				map.put("cw","yes");
				JSONObject json = JSONObject.fromObject(map); 
			    response.getWriter().print(json);
				return null;
			}
			else{
			    Map<String,String> map = new HashMap<String, String>();
				map.put("message", "�ɹ����롾"+resultMap.get("zqts")+"����������"+resultMap.get("cwts")+"����������ϸ�˶ԡ���������.xls��!");
				map.put("gid", (String)resultMap.get("gid"));
				map.put("cw","yes");
				JSONObject json = JSONObject.fromObject(map); 
			    response.getWriter().print(json);
				return null;
			}
			
		} catch (FileNotFoundException e) {
			logger.info("�����ļ�δ�ҵ���");
			e.printStackTrace();
		} catch (IOException e) {
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
 * @����:���ش�������
 */
public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
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


}