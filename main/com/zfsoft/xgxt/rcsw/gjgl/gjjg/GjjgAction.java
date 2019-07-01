/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-29 ����04:15:58 
 */  
package com.zfsoft.xgxt.rcsw.gjgl.gjjg;

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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


public class GjjgAction  extends SuperAction<GjjgForm,GjjgService>{
	private static final String url = "xsgjxxgl.do";//���ݿ����
	public static final String ZJLY_ZHF = "zjly_zhf";
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ������¼.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "û���ļ���";
	GjjgService service = new GjjgService();
	private final String RCSW="rcswrcxw";
	@SystemAuth(url = url)
	public ActionForward gjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GjjgForm model = (GjjgForm)form;
		request.setAttribute("path", url);
		model.setPath(url);
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
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjjglist");
	}
	@SystemAuth(url = url)
	public ActionForward gjxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GjjgForm model = (GjjgForm) form;
		GjjgForm myForm = service.getModel(model);
		request.setAttribute("bz", myForm.getBz());
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", myForm);
		return mapping.findForward("gjView");
	}
		
	/**
	 * @����: ��ת����ҳ��
	 */	
	public ActionForward importGjxxPrepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		return mapping.findForward("drjsp");
	}
	/**
	 * @����: ���ص���ģ��
	 */	
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = request.getSession().getServletContext().getRealPath(
				"/temp/mb/")+"/zjlygjdrmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("zjlygjdrmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @����: ���뱣��
	 */	
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		GjjgForm model = (GjjgForm) form;
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
	/**
	 * @����: ��������
	 */				
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GjjgForm model=(GjjgForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
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
	 * @����: ɾ��
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	public ActionForward GjxxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		
}
