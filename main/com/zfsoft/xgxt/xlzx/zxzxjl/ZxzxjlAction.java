/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-22 ����01:36:03 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-12-22 ����01:36:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxjlAction extends SuperAction<ZxzxjlModel, ZxzxjlService>{
	private ZxzxjlService service = new ZxzxjlService();
	private static List<HashMap<String, String>> jbxxList = null;
	public static String ZXZXJL = "zxzxjl";
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZXZXJL);
	}
	
	private static final String url = "xlzx_zxzx_zxzxjl.do";
	
	/** 
	 * @����:�õ�������ѯ�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����01:59:24
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
	public ActionForward getZxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
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
		String path = "xlzx_zxzx_zxzxjl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZxList");
	}
	
	/** 
	 * @����:������ѯ������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:59:18
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
	public ActionForward addZxJbxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		//���Ϊ����
		if("save".equalsIgnoreCase(model.getType())){
			boolean result = false;
			if(service.isExists(model)){
 				String messageKey = MessageKey.XLZX_ZXZXJL_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
			User user = getUser(request);
			model.setTxr(user.getUserName());
			result = service.addZxjbxx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{			
			User user = getUser(request);
			if ("stu".equals(user.getUserType())) {
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
						.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}		
			String path = "zxzx_zxzxjl.do?method=addZxJbxx&type=add";
			request.setAttribute("path", path);
			request.setAttribute("jbxxList", jbxxList);
			setXxList(request,service);
			return mapping.findForward("addZxJbxx");
		}
	}
	
	/** 
	 * @����:�޸���ѯ������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����04:24:51
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
	public ActionForward updateZxJbxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		if("save".equalsIgnoreCase(model.getType())){			
			boolean result = service.updateZxjbxx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{			
			User user = getUser(request);
			if ("stu".equals(user.getUserType())) {
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
						.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			ZxzxjlModel myform = service.getModel(model);
			if (null != myform) {
				BeanUtils.copyProperties(model, StringUtils.formatData(myform));
				if(null != myform.getYzxwt() && !"".equalsIgnoreCase(myform.getYzxwt())){
					String[] wts = myform.getYzxwt().split(",");
					List<String> list = new ArrayList<String>();
					for(int i = 0;i<wts.length;i++){
						list.add(wts[i]);
						request.setAttribute("wtList", list);
					}
					model.setYzxwts(wts);
				}								
			}
			String path = "zxzx_zxzxjl.do?method=updateZxJbxx&type=update";
			request.setAttribute("path", path);
			request.setAttribute("jbxxList", jbxxList);
			setXxList(request,service);
			return mapping.findForward("updateZxJbxx");
		}
	}
	
	/** 
	 * @����:ɾ����ѯ��¼������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����04:39:16
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
	public ActionForward delZxJbxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.deleteZxjbxx(ids);
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
	
	/** 
	 * @����:ά������������ѯ��¼(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-23 ����09:29:06
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
	public ActionForward whbcZxzxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		String[] zjs = model.getZjs();//��ȡ��������
		String[] xgs = model.getXgs();//��ȡ�޸�����
		String[] delIds = model.getDelIds();//��ȡɾ������
		ZxzxjlService jlService = new ZxzxjlService();
		User user = getUser(request);
		boolean result = jlService.whZxzxJl(zjs, xgs, delIds, model.getXh(), user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:ά��������ѯ��¼(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-23 ����02:00:38
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
	public ActionForward whZxzxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		ZxzxjlModel myform = service.getModel(model);
		if (null != myform) {
			BeanUtils.copyProperties(model, myform);								
		}
		User user = getUser(request);
		List<HashMap<String,String>> jlList = service.getZxjlList(model.getXh(),user,false);
		if(null != jlList && jlList.size()>0){
			request.setAttribute("jlList", jlList);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("userNameReal", user.getRealName());
		request.setAttribute("rs", model);
		return mapping.findForward("whZxzxjl");
		
	} 
	
	
	/** 
	 * @����:���ø���ѡ����ӣ��޸�ҳ��ʹ�ã�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:49:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param service
	 * void �������� 
	 * @throws 
	 */
	public static void setXxList(HttpServletRequest request,ZxzxjlService service){
		List<HashMap<String, String>> sfList = service.getSfList();//�Ƿ�
		List<HashMap<String, String>> ywList = service.getYwList();//����
		List<HashMap<String, String>> jjzkList = service.getJjzkList();//����״��
		List<HashMap<String, String>> jtszdList = service.getJtszdList();//��ͥ���ڵ�
		List<HashMap<String, String>> hyzkList = service.getHyzkList();//����״��
		List<HashMap<String, String>> zxwtList = service.getZxwtList();//��ѯ����
		List<HashMap<String, String>> whcdList = service.getWhcdList();//�Ļ��̶�
		List<HashMap<String, String>> xhcdList = service.getXhcdList();//ϲ���̶�
		request.setAttribute("sfList", sfList);
		request.setAttribute("ywList", ywList);
		request.setAttribute("jjzkList", jjzkList);
		request.setAttribute("jtszdList", jtszdList);
		request.setAttribute("hyzkList", hyzkList);
		request.setAttribute("zxwtList", zxwtList);
		request.setAttribute("whcdList", whcdList);
		request.setAttribute("xhcdList", xhcdList);	
	}
	
	/** 
	 * @����:�鿴(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-29 ����01:48:39
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
	public ActionForward ckZxzxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		request.setAttribute("jbxxList", jbxxList);
		ZxzxjlModel myform = service.getModel(model);
		BeanUtils.copyProperties(model,myform);
		request.setAttribute("rs", model);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			User user = getUser(request);
			List<HashMap<String,String>> jlList = service.getZxjlList(model.getXh(),user,true);
			if(null != jlList && jlList.size()>0){
				request.setAttribute("jlList", jlList);
			}
		}
		return mapping.findForward("ckZxzxjl");
		
	}
	
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-29 ����04:00:13
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
		ZxzxjlModel model = (ZxzxjlModel) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
	 * @throws Exception 
	 * 
	 * @����:����������ѯ�ǼǱ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-5 ����10:43:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ExportxlzxDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		File wordFile = getDjbWord(xh,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����������ѯ�ǼǱ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-5 ����10:43:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ExportxlzxDjbPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getDjbWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ�ǼǱ�word
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-5 ����11:37:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getDjbWord(String xh,HttpServletRequest request) throws Exception{

		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String, Object>();
		HashMap<String, String> dataMap = service.getExportData(xh, user);
		dataMap.put("wtbc", HtmlUtil.xmlZy(dataMap.get("wtbc")));
		dataMap.put("zxqw", HtmlUtil.xmlZy(dataMap.get("zxqw")));
		data.putAll(dataMap);
		
		//�ָ�����ѯ����
		List<HashMap<String, String>> zxwtList = service.getZxwtList();
		Map<String,Boolean> zxwtMap = new HashMap<String,Boolean>();
		String[] zxwtlb = new String[zxwtList.size()];
		for(int i=0;i < zxwtList.size();i++){
			zxwtlb[i] = zxwtList.get(i).get("mc");
			zxwtMap.put("zxwt"+i, false);
		}
		String[] zxwts=new String[]{};
		String yzxwt = dataMap.get("yzxwt");
		if(StringUtils.isNotNull(yzxwt)){
			zxwts = yzxwt.split(",");
			for(int i=0;i < zxwts.length;i++){
				for(int j=0;j < zxwtlb.length;j++){
					if(zxwtlb[j].equals(zxwts[i])){
						zxwtMap.put("zxwt"+j, true);
						break;
					}
				}
			}
		}
		data.put("zxwtMap", zxwtMap);
		
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xlzx","xlzxdcb.xml",dataMap.get("xh")+dataMap.get("xm"));
		return file;
	}

	
	/**
	 * 
	 * @����:��ӡ��ѯ��¼��(����)
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-6-5 ����02:21:40
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
	public ActionForward ExportxlzxjlDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		File wordFile = getZxjlDjbWord(xh,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����:��ӡ��ѯ��¼��(����)
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-6-5 ����02:22:41
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
	public ActionForward ExportxlzxjlDjbPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getZxjlDjbWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ��ѯ��¼
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-6-5 ����02:23:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getZxjlDjbWord(String xh,HttpServletRequest request) throws Exception{

		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String, Object>();
		
		List<HashMap<String,String>> zxjlList = service.getZxjlList(xh,user,false);

		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> mapp;
		for(HashMap<String, String> map:zxjlList){
			mapp = HtmlUtil.formatXmlMap(map);
			mapList.add(mapp);
		}
		data.put("zxjlList", mapList);
		String fileName = null;
		if(null == zxjlList || zxjlList.size()==0){
			fileName = xh + "����ѯ��¼";
		} else {
			fileName = zxjlList.get(0).get("xh")+zxjlList.get(0).get("xsxm");
		}
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xlzx","xlzxjldcb.xml", fileName);
		return file;
	}
	
	/** 
	 * @����:��ȡ��¼id(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-27 ����03:04:35
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
	public ActionForward getIds(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZxzxjlModel model = (ZxzxjlModel) form;
		List<HashMap<String,String>> list = service.getZxIdList(model.getXh());
		if(null != list && list.size()>0){
			response.getWriter().print(JSONArray.fromObject(list));
		}
		return null;
	}
}
