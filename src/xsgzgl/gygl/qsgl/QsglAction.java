package xsgzgl.gygl.qsgl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.gygl.cwgl.CwglForm;
import xsgzgl.gygl.cwgl.CwglService;
import xsgzgl.gygl.ldgl.LdglModel;
import xsgzgl.gygl.ldgl.LdglServices;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_����ά��action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class QsglAction extends BasicExtendAction{
	
	/**
	 * ���ҹ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-������Ϣ����-ɾ��PK:{primarykey_cbv}")
	public ActionForward qsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsglService service = new QsglService();
		
		String doType = request.getParameter("doType");
		QsglForm myForm = (QsglForm)form;
		RequestForm rForm = new RequestForm();
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String[] pk = request.getParameterValues("primarykey_cbv");
			String message = service.delQs(pk) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			
			request.setAttribute("message", message);
		}
		
		// ��ѯ����
		QsglModel model = new QsglModel();
		BeanUtils.copyProperties(model, myForm);
			
		request.setAttribute("rs", service.queryQs(model));
		request.setAttribute("searchTj", myForm.getSearchModel());	//�߼���ѯ����
		service.setRequestValue(rForm, request);					//������
		
		// write��titile
		setWriteAbleAndTitle(request, "gyglnew_qsgl_qsgl.do");

		request.setAttribute("topTr", service.getTopTr("qswh"));
		request.setAttribute("realTable", "xg_gygl_new_qsxxb");	// �����
		request.setAttribute("tableName", "view_xg_gygl_new_qsxx");	// ������
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		request.setAttribute("kcshqss", service.getKcshqss(model));//�ɳ�ʼ��������
		request.setAttribute("searchTjstr", service.getSearchTjstr(model));//��ѯ����
		
		return mapping.findForward("qsglManage");
	}
	
	/**
	 * ������Ϣ���� �Զ��� ���� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward qsxxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsglService service = new QsglService();
		QsglForm myForm = (QsglForm)form;
		QsglModel model = new QsglModel();
		BeanUtils.copyProperties(model, myForm);
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.qsxxglExportdata(model);
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
	 * ��������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-������Ϣ����-����LDDM:{lddm},CH:{ch},QSH:{qsh},CWS:{cws},SFBZ:{sfbz}")
	public ActionForward qswhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		QsglForm myForm = (QsglForm)form;
	
		QsglService service = new QsglService();

		// ��������������
		if("save".equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// copy����ֵ
			QsglModel model = new QsglModel();
			BeanUtils.copyProperties(model, myForm);
			
			String message = service.saveQswh(model);
			
			request.setAttribute("message", message);
		}
		
		request.setAttribute("ldList", service.getLdList());
		myForm.setYwkt("��");
		this.saveToken(request);
		return mapping.findForward("qswhAdd");
	}
	
	/**
	 * ������Ϣ�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-������Ϣ����-�޸�PKVALUE:{pkValue},SFBZ:{sfbz}")
	public ActionForward qswhModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		QsglForm myForm = (QsglForm)form;
		QsglService service = new QsglService();
		
		// copy����ֵ
		QsglModel model = new QsglModel();
		BeanUtils.copyProperties(model, myForm);
		
		if("save".equalsIgnoreCase(doType)){
			CwglService cwglService = new CwglService();
			cwglService.updateCwqsh(model.getQsh(), model.getYqsh() , model.getLddm());
			String message = service.updateQswh(pkValue, model);
			
			request.setAttribute("message", message);
		}
		
		// ������ϸ��Ϣ
		Map<String, String> rs = service.getQsForPk(pkValue);
		myForm.setQsxb(rs.get("qsxb"));
		
		request.setAttribute("rs", rs);
		request.setAttribute("qsqtxx", service.getQsfprzInfo(pkValue));
		
		return mapping.findForward("qswhModi");
	}
	
	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qswhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����
		String pkValue = request.getParameter("pkValue");
		
		QsglForm myForm = (QsglForm)form;
		QsglService service = new QsglService();
		
		// ������ϸ��Ϣ
		Map<String, String> rs = service.getQsForPk(pkValue);
		myForm.setQsxb(rs.get("qsxb"));
		//��ȡ����ѧ����Ϣ
		List<String[]> list = service.getCwForQs(pkValue);

		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		
		return mapping.findForward("qswhView");
	}
	
	/**
	 * ajax����¥����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadLdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		
		LdglServices service = new LdglServices();
		LdglModel model = new LdglModel();
		model.setLddm(lddm);
		
		Map<String, String> map = service.getLdxxOne(model);
		String json = JSONObject.fromObject(map).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);

		return null;
	}
	
	/**
	 * ajax����������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadQsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		
		QsglService service = new QsglService();
		Map<String, String> map = service.getMaxQsInfo(lddm, ch);
		Map<String, String> xb = service.getLcXb(lddm, ch);
		map.putAll(xb);
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * �������ݸ��� ���շѱ�׼�����ҵ绰
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-������Ϣ����-����tableName:{TABLENAME}")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
			String tableName = request.getParameter("tableName");//��ͼ��
			String realTable = request.getParameter("realTable");//����
			
			request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
			request.setAttribute("tableName", tableName);
			request.setAttribute("realTable", realTable);
			
			String act=request.getParameter("act");
			//��������
			if("import".equals(act)){
				uploadFile(mapping, form, request, response);
				
				QsglService service = new QsglService();
				String back= service.importData(request,response);//��������
				String sfdcExcel=(String)request.getAttribute("sfdcExcel");
				if("yes".equals(sfdcExcel)){
					return mapping.findForward("");
				}
				request.setAttribute("back", back);
				
			}
			return mapping.findForward("importData");
		}
	
	/**
	 * �ļ��ϴ� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-������Ϣ����-�ϴ�FNAME:{userName}")
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//�ô���Ҫ��֤��������ԱȨ��
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		QsglForm hff = (QsglForm) form;
		FormFile file = hff.getImpFilePath();	
//		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
//			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
//		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
	
	/**
	 * ����������ʼ��
	 * @author zhanghui
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-������Ϣ����-��ʼ��QSSTR:{qsStr}")
	public ActionForward qsglQsssInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		
		QsglService service = new QsglService();	
		QsglForm myForm = (QsglForm)form;
		
		if("init".equalsIgnoreCase(doType)){
			String message = service.initQsss(myForm,request);			
			request.setAttribute("message", message);
		}		
//		request.setAttribute("cshlxList", service.getCshlxList());
		return mapping.findForward("qsglQsssInit");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-26 ����05:42:29
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
	public ActionForward plwhTypeOfQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] primarykey_checkVal  = request.getParameter("primarykey_checkVal").split(",");
		request.setAttribute("primaryKeyArray",primarykey_checkVal);
		return mapping.findForward("plwhTypeOfQs");
	}
	
	/**
	 * 
	 * @����: ����ά��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-27 ����09:57:51
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
	public ActionForward saveplwhTypeOfQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QsglForm myForm = (QsglForm)form;
		QsglService service = new QsglService();
		boolean rs = service.saveplwhTypeOfQs(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
