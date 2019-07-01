/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-1 ����09:56:31 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import java.io.File;
import java.net.URLDecoder;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: ��������ѯ
	 * @�๦������: ������ѯ������
	 * @���ߣ� ����[����:1186]
	 * @ʱ�䣺 2016-7-1 ����09:56:31 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public class ZxzxthjlAction extends SuperAction{
		private static final String url = "xlzx_thjl_zxzxthjl.do";
	/**
	 * @����: ��ѯҳ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-7-1 ����02:38:02
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
		public ActionForward zxzxthjlList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlService service = new ZxzxthjlService();
			if (QUERY.equals(model.getType())){
				//���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				//��ѯ
				List<HashMap<String,String>> resultList = service.getPageList(model,user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			String path = "xlzx_thjl_zxzxthjl.do";
			request.setAttribute("path", path);
			request.setAttribute("realTable", "xlzx_thjl_zxzxthjl");
			FormModleCommon.commonRequestSet(request);//title��ʾ
			return mapping.findForward("zxzxthjlList");
		}
		
		
	/**
	 * @����: ����
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-7-5 ����10:48:26
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
		public ActionForward zxzxthjlAdd(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm myForm = (ZxzxthjlForm) form;
			ZxzxthjlService service = new ZxzxthjlService();
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			User user = getUser(request);
			HashMap<String, String> jsInfo = new HashMap<String,String>();
			// ְ������Ϣ
 			if(StringUtils.isNotNull(user.getUserName())){
 				jsInfo = service.getInfoByZgh(user.getUserName());
 			}
 			if (!StringUtil.isNull(myForm.getXh())){
 				//����ѧ��������Ϣ
 				XsxxService xsxxService = new XsxxService();
 				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
 				request.setAttribute("jbxx", xsjbxx);
 			}
 			if (SAVE.equalsIgnoreCase(myForm.getType())) {
 				myForm.setYbwtlb(URLDecoder.decode(myForm.getYbwtlb(),"UTF-8"));
 				myForm.setZajb(URLDecoder.decode(myForm.getZajb(),"UTF-8"));
 				// Ψһ���ж�
 				boolean isExist = service.addCheck(myForm);
 				if (!isExist) {
 					boolean result = service.runInsert(myForm);
 					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
 					response.getWriter().print(getJsonMessageByKey(messageKey));
 				} else {
 					response.getWriter().print(getJsonResult(MessageKey.XLZX_ZXZXTHJL_EXIST, false));//ͬһ��ͬһ��ѧ���������ж��̸����¼��
 				}
 				return null;
 			}
 			String path = "xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlAdd";
 			request.setAttribute("path", path);
 			request.setAttribute("jsInfo", jsInfo);//ְ����Ϣ
 			request.setAttribute("ybxlwtlbList", service.getYblxwtlbList());//һ�������������
 			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			return mapping.findForward("zxzxthjlAdd");
		}
		
		
	/**
	 * @����: �޸�
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-7-6 ����02:45:33
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
		public ActionForward zxzxthjlUpdate(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlService service = new ZxzxthjlService();
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlForm myForm = service.getModel(model);
			if (!StringUtil.isNull(myForm.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			User user = getUser(request);
			HashMap<String, String> jsInfo = new HashMap<String,String>();
			// ְ������Ϣ
 			if(StringUtils.isNotNull(user.getUserName())){
 				jsInfo = service.getInfoByZgh(user.getUserName());
 			}
 			request.setAttribute("jsInfo", jsInfo);//ְ����Ϣ
			if (SAVE.equalsIgnoreCase(model.getType())){
				model.setYbwtlb(URLDecoder.decode(model.getYbwtlb(),"UTF-8"));
				model.setZajb(URLDecoder.decode(model.getZajb(),"UTF-8"));
				boolean isExist = service.addCheck(model);
				if (!isExist) {
					boolean result = service.runUpdate(model);
					String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(message));
				} else {
					response.getWriter().print(
							getJsonResult(MessageKey.XLZX_ZXZXTHJL_EXIST, false));
				}
				return null;
			}
 			request.setAttribute("ybxlwtlbList", service.getYblxwtlbList());//һ�������������
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			return mapping.findForward("zxzxthjlUpdate");
		}
		
		
	/**
	 * @����: �鿴
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-6 ����11:01:13
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
		public ActionForward zxzxthjlView(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlService service = new ZxzxthjlService();
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlForm myForm = service.getModel(model);
			if (!StringUtil.isNull(myForm.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			User user = getUser(request);
			HashMap<String, String> jsInfo = new HashMap<String,String>();
 			if(StringUtils.isNotNull(user.getUserName())){
 				jsInfo = service.getInfoByZgh(user.getUserName());
 			}
 			HashMap<String,String> thjlxx = service.getThjlxx(myForm.getId());
 			request.setAttribute("thjlxx", StringUtils.formatData(thjlxx));
 			request.setAttribute("jsInfo", jsInfo);//ְ����Ϣ
			return mapping.findForward("zxzxthjlView");
			}
		
		
	/**
	 * @����: ɾ������
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-7-4 ����10:34:03
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
		public ActionForward zxzxthjlDelete(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlService service = new ZxzxthjlService();
			String ZxzxThjl = request.getParameter("ZxzxThjl");
			String[] id = ZxzxThjl.split(",");
			int count = 0;
			try {
				count = service.delZxzxthjlId(id);
				response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_DEL_FAIL);
			}
			return null;
		}
		
		
	/**
	 * @����: ������ѯ���
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-4 ����09:23:29
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
			ZxzxthjlForm model = (ZxzxthjlForm) form;
			ZxzxthjlService service = new ZxzxthjlService();
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
		 * @����: ������ӡ������ѯԼ̸��¼��
		 * @���ߣ� ����[���ţ�1186]
		 * @���ڣ�2016-7-13 ����02:33:08
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
		public ActionForward getZxzxthjl(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm myForm = (ZxzxthjlForm) form;
			File wordFile = getZxzxthjlWord(myForm,request);
			FileUtil.outputWord(response, wordFile);
			return null;
		}
		/**
		 * @����: ������ӡ������ѯԼ̸��¼��
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-7-13 ����02:29:34
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
		public ActionForward getZxzxthjlZip(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ZxzxthjlForm myForm = (ZxzxthjlForm) form;
			String value = request.getParameter("value");
			if (StringUtils.isNotNull(value)){
				String[] values = value.split(",");
				List<File> files = new ArrayList<File>();
				for (int i = 0 , n = values.length ; i < n ; i++){
					myForm.setId(values[i]);
					File file = getZxzxthjlWord(myForm,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
				for(int j=0;j<files.size();j++){
					files.get(j).delete();
				}
			}
			return null;
		}
		/**
		 * ���ģ����������word�ļ�
		 */
		private File getZxzxthjlWord (ZxzxthjlForm myForm,HttpServletRequest request) throws Exception{
			ZxzxthjlService service = new ZxzxthjlService();
			XsxxService xsxxService = new XsxxService();
			Map<String,Object> data = new HashMap<String,Object>();
			HashMap<String,	String> thjlList = service.getThjl(myForm.getId());
			// ����ѧ��������Ϣ
			HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(thjlList.get("xh"));
			data.putAll(xsxxMap);
			data.put("bz", HtmlUtil.xmlZy(thjlList.get("bz")));
			data.put("jbqkms", HtmlUtil.xmlZy(thjlList.get("jbqkms")));
			data.put("cbpgjg", HtmlUtil.xmlZy(thjlList.get("cbpgjg")));
			data.put("thjl",thjlList);
			data.put("thsj",DateTranCnDate.fomartDateToCn(thjlList.get("thsj"),FomartDateType.day));//̸��ʱ����XXXX��XX��XX��չ�ֳ���
			
			//�ָ�һ�������������
			List<HashMap<String, String>> ybxlwtlbList = service.getYblxwtlbList();
			Map<String,Boolean> ybwtlbMap = new HashMap<String,Boolean>();
			String[] ybxlwtlb = new String[ybxlwtlbList.size()];
			for(int i=0;i < ybxlwtlbList.size();i++){
				ybxlwtlb[i] = ybxlwtlbList.get(i).get("mc");
				ybwtlbMap.put("ybwtlb"+i, false);
			}
			String[] ybwtlbs=new String[]{};
			String ybwtlb = service.getYbwtlb(myForm.getId());
			if(StringUtils.isNotNull(ybwtlb)){
				ybwtlbs = ybwtlb.split(",");
				for(int i=0;i < ybwtlbs.length;i++){
					for(int j=0;j < ybxlwtlb.length;j++){
						if(ybxlwtlb[j].equals(ybwtlbs[i])){
							ybwtlbMap.put("ybwtlb"+j, true);
						}
					}
				}
			}
			data.put("ybwtlbMap", ybwtlbMap);
			//�����ϰ��;��񼲲�
			Map<String,Boolean> zajbMap = new HashMap<String,Boolean>();
			zajbMap.put("zajb1", false);
			zajbMap.put("zajb2", false);
			zajbMap.put("zajb3", false);
			String[] zajbs=new String[]{};
			String zajb = service.getZajb(myForm.getId());
			if(StringUtils.isNotNull(zajb)){
				zajbs = zajb.split(",");
				for(int i=0;i < zajbs.length;i++){
					if("��������".equals(zajbs[i])){
						zajbMap.put("zajb1", true);
					}
					if("������ѯ".equals(zajbs[i])){
						zajbMap.put("zajb2", true);
					}
					if("��������".equals(zajbs[i])){
						zajbMap.put("zajb3", true);
					}
				}
			}
			data.put("zajbMap", zajbMap);
			File file = null;
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xlzx","zxzxthjl.xml",FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			return file;
		}
		
	}
