package xgxt.xtwh.xtwhOther;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.sun.jmx.snmp.Timestamp;
import com.zfsoft.basic.BasicService;
import com.zfsoft.util.JdbcUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.PictureUtil;
import xgxt.utils.ZipUtils;
import xgxt.xsxx.comm.ajax.XsxxAjaxService;

/**
 * ϵͳά����ص������Ӽ��޸ĵĹ���
 */
public class XtwhOtherAction extends SuperAction {
	/**
	 * @describe ��Ƭ�����ϴ�����
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward picPlsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XtwhOtherService myService = new XtwhOtherService();
		try {
			HttpSession session = request.getSession();
			String act = request.getParameter("isFile");
			//���뷽ʽ
			String drfs = request.getParameter("drfs");
			
			String sUName = session.getAttribute("userName").toString();
			if("yes".equalsIgnoreCase(act)){
				String dir = servlet.getServletContext().getRealPath("/zip");
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdir();
				}
				String fName = sUName;
				//�ļ��ϴ�
				Timestamp date = new Timestamp(System.currentTimeMillis());
				String czsj = date.toString();
				fName += date.toString().substring(0, 19);
				fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
				XtwhOtherForm dataImp = (XtwhOtherForm) form;
				FormFile file = dataImp.getFile();
				if (file == null) {
					request.setAttribute("errMsg", "�ļ���ȡʧ��,�����ԣ�");
					return new ActionForward("/errMsg.do", false);
				}
				String fType1 = file.getFileName().substring(
						file.getFileName().length() - 4, file.getFileName().length());
				int size1 = file.getFileSize();
				InputStream in1 = file.getInputStream();
				String filePath1 = dir + "/" + fName + fType1;
				OutputStream out1 = new FileOutputStream(filePath1);
				int bytesRead1 = 0;
				byte[] buffer1 = new byte[size1];
				while ((bytesRead1 = in1.read(buffer1, 0, size1)) != -1) {
					out1.write(buffer1, 0, bytesRead1);
				}
				out1.close();
				in1.close();
				
				//�ļ����н�ѹ
				ZipUtils zipUtils = new ZipUtils();
				zipUtils.unzip(filePath1,dir+"/"+fName);

				//��ѹ�������ϴ�
				//String filepath = Base.chgNull(request.getParameter("filepath"), "", 1);
				List<String> rsMap = PictureUtil.uploadPhoto(dir+"/"+fName+"/");
				request.setAttribute("sb", rsMap.get(0));
				request.setAttribute("sb1", rsMap.get(1));
				request.setAttribute("sb2", rsMap.get(2));
				request.setAttribute("rsMap", rsMap);
				request.setAttribute("path", "xsxxpicpldr.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("picpldr");
			}else {
				request.setAttribute("path", "xsxxpicpldr.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("picpldr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("picpldr");
	}
	
	/**
	 * �°����ά��
	 */
	public ActionForward xtDmwhNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		    XtwhOtherForm myForm = (XtwhOtherForm)form;
		    
			//�õ�ģ������,ѧУ����
			String ssmk = request.getParameter("ssmk");
			String xxdm = Base.xxdm;
			String tableName = request.getParameter("tName");
			
			//service
			XtwhOtherService myService = new XtwhOtherService();
			
			//���б�
			List<HashMap<String, String>> tableArray = myService.getDmwhTable(ssmk,xxdm);
			
			//�ж����tableArray��sizeΪ0���Ͳ������Ժ�Ĳ���������errMessage
			if(tableArray==null||tableArray.size()==0){
				request.setAttribute("errMessage", "��û���κο�ά���Ĵ�������");
				return mapping.findForward("xtDmwhNew");
			}
			if(tableName==null||tableName.equalsIgnoreCase("")){
				tableName = tableArray.get(0).get("whdmb");
			}
			myService.getDmwhZdForCx(tableName,xxdm,ssmk,myForm);
			
			String[] zdList = myService.getWhzds();
			
			request.setAttribute("zdList", zdList);
			request.setAttribute("tName", tableName);
			request.setAttribute("ssmk", ssmk);
			request.setAttribute("rs", myService.getQueryRs());
			request.setAttribute("topTr", myService.getToptr());
			//�����ֶκͱ���ȥƴsql���
		    //�������Ͷ�дȨ
			request.setAttribute("tableArray", tableArray);
			FormModleCommon.commonRequestSet(request);
			
			//-----2011.1.9 qph--------------------------
			if ("jyweb".equals(ssmk)){
				request.setAttribute("title", "��Ŀ����");
				request.setAttribute("writeAble", "yes");
			}
			//-----------end --------------------------
			
			request.setAttribute("maxNum", myForm.getPages().getPageSize());
			return mapping.findForward("xtDmwhNew");
	}
	
	
	
	/**
	 * �°����ά��
	 */
	@SystemLog(description="���ʴ���ά��-ɾ��SSMK��{ssmk} TNAME:{tName} PKVALUE:{pkValue}")
	public ActionForward xtDmwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    
			//�õ�ģ������,ѧУ����
		    String xxdm = Base.xxdm;
		    String ssmk = request.getParameter("ssmk");
			String tableName = request.getParameter("tName");
			String pkValue = request.getParameter("pkValue");
			//service
			XtwhOtherService myService = new XtwhOtherService();
			boolean del = myService.dmwhDel(tableName, pkValue,xxdm,ssmk,request);
			if(del){
				request.setAttribute("result", "OK");
			}else{
				request.setAttribute("result", "NO");
			}
			return xtDmwhNew(mapping,form,request,response);
	}
	
	
	/**
	 *�°����ά���Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xtwhdmwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XtwhOtherForm model = (XtwhOtherForm)form;	    
		//�õ�ģ������,ѧУ����
		String tablename = request.getParameter("tName");		
		XtwhOtherService service = new XtwhOtherService();
		
		//���ɸ߼���ѯ����		
		//CommService comService = new CommService();
		//SearchModel searchModel = comService.getSearchModel(request);
		//model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getDmwhExport(tablename);
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
	 * �°����ά��
	 */
	public ActionForward xtDmwhOneNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			XtwhOtherForm myFrom = (XtwhOtherForm)form;
			//�õ�ģ������,ѧУ����
			String type = request.getParameter("Type");
			String ssmk = request.getParameter("ssmk");
			String xxdm = Base.xxdm;
			String tableName = request.getParameter("tName");
			String pkValue = request.getParameter("pkValue");
			//service
			XtwhOtherService myService = new XtwhOtherService();
			
			//�����ʾ��ṹ��������������ֵ
			myService.getDmwhZdForOneDis(tableName,xxdm,ssmk,pkValue);
			myService.getDmwhZd(tableName, xxdm, ssmk, request, myFrom);
			if(type!=null&&type.equalsIgnoreCase("ref")){
				String [] whzds = myService.getWhzds();
				for(int i = 0;i<whzds.length;i++){
					request.setAttribute(whzds[i], request.getParameter(whzds[i]));
				}
			}
			if(pkValue==null){
				request.setAttribute("newId", tableName);
			}
			request.setAttribute("opMap", myService.getZdOpMap());
			request.setAttribute("tName", tableName);
			request.setAttribute("pkValue", pkValue);
			request.setAttribute("ssmk", ssmk);
			request.setAttribute("opTwoAssociate", myService.getOpTwoAssociate());
			request.setAttribute("rs", myService.getOneRsAllAttribute());
			//�����ֶκͱ���ȥƴsql���
		    //�������Ͷ�дȨ
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("xtDmwhOne");
	}
	
	/**
	 * �°���뱣��
	 */
	@SystemLog(description="���ʴ���ά��-���ӻ��޸ĵı���SSMK:{ssmk} TNAME��{tName} PKVALUE:{DM} ")
	public ActionForward xtDmwhSaveNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			//�õ�ģ������,ѧУ����
			String xxdm = Base.xxdm;
			String tableName = request.getParameter("tName");
			String ssmk = request.getParameter("ssmk");
			String pkValue = request.getParameter("pkValue");
			//service
			XtwhOtherService myService = new XtwhOtherService();
			
			//�����ʾ��ṹ��������������ֵ
			Boolean update = myService.dmwhZdForOneSave(tableName,xxdm,ssmk,pkValue,request);
			
			String message = update ? "����ɹ���":"����������Ѵ��ڣ�";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			/*if(update){
				request.setAttribute("result", "OK");
			}else{
				request.setAttribute("result", "NO");
			}*/
			return null;
			//return mapping.findForward("xtDmwhOne");
			
	}
	
	/**
	 * ���ܿ��ع���
	 * */
	public ActionForward gnkgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			XtwhOtherForm myForm = (XtwhOtherForm)form;
			HttpSession session = request.getSession();
			//�õ�ģ������,ѧУ����
			String xxdm = Base.xxdm;
			String ssmk = request.getParameter("ssmk");//����ģ��
			String doType = request.getParameter("doType");
			//�û�����
			String userType = session.getAttribute("userType").toString();
			//����Ա
			boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
			//������
			boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
			//��ѯ�ı���
			String tableName = "xgxtgy_gnkgb";//ѧ������ģ�鿪�ؿ��Ʊ�
			// ����·��
			String path = "gnkgsz.do?ssmk="+ssmk;
			//service
			XtwhOtherService service = new XtwhOtherService();			
			
			// �Ƿ��ѯ����
			boolean search = Base.isNull(request.getParameter("go")) ? false : true;
			
			// ==================��½�û���� ==================
			if (bzrQx || fdyQx || "stu".equalsIgnoreCase(userType) || "xy".equalsIgnoreCase(userType)) {
				String msg = "��ģ��ֻ����ѧУ�û����в�������ȷ�ϣ�";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
			// ==================ִ�б������ ==================
			if ("save".equalsIgnoreCase(doType)) {
				boolean result = service.updateKgzt(myForm);
				String msg = result ? "�����ɹ���" : "����ʧ�ܣ�";
				request.setAttribute("message", msg);
			}
			// =================end ===================
			// ==================ִ�е������� ==================
			if ("exp".equalsIgnoreCase(doType)) {
				String[] outputColumn = null;
				expPageData(request, response, tableName, tableName, outputColumn);
				return mapping.findForward("");
			}
			// =================end ===================
			// ==================ִ�в�ѯ���� ==================
			if (search) {
				String[] outputColumn = { "gndm", "gnmc", "gnlb", "kgzt"};
				this.selectPageDataByPagination(request, myForm, "", tableName,
						outputColumn);
			}
			// =================end ===================
			//����б�
			request.setAttribute("gnlbList", service.getList(tableName,"gnlb", "gnlb", "ssmk",ssmk));
			//����״̬
			request.setAttribute("kgztList", service.getDefaultList("kgzt"));
			//����ģ��
			request.setAttribute("ssmk", ssmk);
			//ģ���дȨ��·��
			request.setAttribute("path", path);
			
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("gnkgManage");
	}
	
	public ActionForward showExportPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JdbcUtil util = new JdbcUtil();
		BasicService service = new BasicService();
		String viewName = request.getParameter("viewName");
		String[] column =service.getTableColumn(viewName);
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap());
		}
		//�������Ϊmultipart/form-data ��ȡ�������ݵ�����
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		Map<String, Object> sqlMap = util.loadQuerySql(viewName, 
						paramMap, 
		                new HashMap<String, String>(), 
		                false);//��ҳ��ѯ
		String sql = "";//��ѯ�ֶ���Ϣ��sql
		String[] inputValue = {};//����ֵ
		for(Map.Entry<String, Object> entry : sqlMap.entrySet()){
			String key = entry.getKey();
			if("sql".equalsIgnoreCase(key)){
				sql = entry.getValue().toString();
			}
			if("value".equalsIgnoreCase(key)){
				inputValue = entry.getValue() != null 
				    ? (String[])entry.getValue() 
				    : new String[]{};
			}
		}
		
		request.setAttribute("list", service.getColumnList(viewName, column));
		request.setAttribute("tableName", request.getParameter("tableName"));
		request.setAttribute("viewName", viewName);
		request.setAttribute("sql", sql);
		request.setAttribute("value", Arrays.toString(inputValue).replace("[", "").replace("]", ""));
		return mapping.findForward("showexport");
	}
	
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BasicService basicService = new BasicService();
		XtwhOtherService service = new XtwhOtherService();	
		String[] outputColumn = request.getParameter("mappingItems").split("!!SplitOne!!");//�����û�ѡ��ĵ����ֶ�
		
		
		try {
			List<String[]> list = service.selectExportData(request.getParameter("viewName"),
					                                       request.getParameter("value").split(","),
					                                       outputColumn,
					                                       request.getParameter("sql"));
			String[] colListCN = basicService.getColumnComment(request.getParameter("viewName"), outputColumn);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			request.setAttribute("errMsg", "����ʧ�ܣ�");
			return mapping.findForward("false");
		}
		return mapping.findForward("");
	}
	
	public ActionForward getXxdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			String xxmc = request.getParameter("xxmc");
			//xxmc = new String(xxmc.getBytes("ISO8859-1"), "gbk");
			XtwhOtherService service = new XtwhOtherService();	
			
			String xxdm = service.getXxdm(xxmc);
		
			response.setContentType("text/html;charset=gbk");

			try {
				response.getWriter().print(xxdm);
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
	}
	
	public ActionForward getXxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			String srz = request.getParameter("srz");
			
			XtwhOtherService service = new XtwhOtherService();	
			
			String xxdm = service.getXxmc(srz);
			if(xxdm==null){
				xxdm="";
			}
			response.setContentType("text/html;charset=gbk");

			try {
				response.getWriter().print(xxdm);
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return null;
	}

	/**
	 * @����:����ģ����ѯ����
	 * @���ߣ�qilm
	 * @���ڣ�2013-8-15 ����11:18:47
	 * @throws
	 */
	public ActionForward getAutocomplete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// ����
		String dataTable = request.getParameter("dataTable");

		// �ֶ���
		String dataField = request.getParameter("dataField");
		
		// �ֶ�Key
		String dataFieldKey = request.getParameter("dataFieldKey");

		// �ֶ�����Ϊ����
		String param = request.getParameter("param");
		// ���Ի�sql����
		String sqlTj = request.getParameter("sqlTj");
		
		XtwhOtherService service = new XtwhOtherService();

		// ȡ�ý����
		List<HashMap<String, String>> lstAutocomplete = service
		.getAutocomplete(dataTable, dataField, dataFieldKey, param, sqlTj);

		try {
			response.setContentType("text/html;charset=gbk");
			JSONArray dataList = JSONArray.fromObject(lstAutocomplete);
			response.getWriter().print(dataList);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @����:��վ���б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-19 ����03:58:17
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
	public ActionForward getHczd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		XtwhOtherService service = new XtwhOtherService();
		// ȡ�ý����
		List<String> cityList = service.getHczd();
		try {
			response.setContentType("text/html;charset=gbk");
		
			JSONArray dataList = JSONArray.fromObject(cityList.toArray());
			response.getWriter().print(dataList);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ��������֤����ֵ�Ƿ���Ч
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-17 ����04:08:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param input
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getCheckYz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String inputvalue = request.getParameter("inputValue");
		boolean result = new XtwhOtherService().isInputValueExist(inputvalue);
		response.getWriter().print(result);
		return null;
	}
	
}
