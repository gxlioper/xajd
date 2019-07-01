/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-27 ����02:56:41 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zhpxf;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.ylbx.YlbxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2017-6-27 ����02:56:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class zhpxfAction extends SuperAction<zhpxfForm, zhpxfService>{
	private zhpxfService service = new  zhpxfService();
	private static final String url = "pj_zcqkcx.do";
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ������¼.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "û���ļ���";
	public ActionForward getZhpxf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		zhpxfForm model = (zhpxfForm) form;
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
//			searchModel.setSearch_tj_xn(new String[] { Base.beforXn });
			request.setAttribute("searchTj", searchModel);
			request.setAttribute("path", url);
			request.setAttribute("userType", user.getUserType());
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("cx");
		}
	}
	
	public ActionForward importYlbx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		YlbxService service = new YlbxService();
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		return mapping.findForward("drjsp");
	}
	
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = request.getSession().getServletContext().getRealPath(
				"/temp/mb/")+"/zjlyzhpxfmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("zjlyzhpxfmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
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
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		zhpxfForm model = (zhpxfForm) form;
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
				    if ("0".equals(String.valueOf(resultMap.get("cwts")))) {
				    	map.put("message", "�ɹ����롾"+resultMap.get("zqts")+"���������¡�"+resultMap.get("gxts")+"����");
				    	map.put("gid", (String)resultMap.get("gid"));
				    	map.put("cw","none");
					}else {
						map.put("message", "�ɹ����롾"+resultMap.get("zqts")+"���������¡�"+resultMap.get("gxts")+"����������"+resultMap.get("cwts")+"����������ϸ�˶ԡ���������.xls��!");
				    	map.put("gid", (String)resultMap.get("gid"));
				    	map.put("cw","yes");
					}
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
	
	
	
	public ActionForward zhpxfck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		zhpxfForm myform = (zhpxfForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myform.setXh(user.getUserName());
		}
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
		HashMap<String, String> zhpxfmap = service.getAllData(myform);
		request.setAttribute("zhpxfmap", zhpxfmap);
		return mapping.findForward("ck");
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		zhpxfForm model = (zhpxfForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
}
