package com.zfsoft.xgxt.base.export.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import xgxt.action.BaseAction;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.model.ImportModel;
import com.zfsoft.xgxt.base.export.service.IImportService;
import com.zfsoft.xgxt.base.export.service.imp.ImportServiceImpl;
import com.zfsoft.xgxt.base.export.util.ImportConfig;

/**
 * ��������
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ImportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ValueStack vs = null;
	private ImportModel model = new ImportModel();
	private IImportService importService = new ImportServiceImpl();
	private File file;
	protected Log logger = LogFactory.getLog(ImportAction.class);

	public void setImportService(IImportService importService) {
		this.importService = importService;
	}

	public ImportModel getModel() {
		return model;
	}

	private ValueStack getValueStack(HttpServletRequest request,
			HttpServletResponse response) {
		vs = new ValueStack(request, response);
		return vs;
	}

	private ValueStack getValueStack(HttpServletRequest request,
			HttpServletResponse response, ActionServlet servlet) {
		//����ʹ���˾�̬�����洢�˵�ǰActionServlet����
		//��Ϊ��ϵͳ�˶����Ǵ�service��ȡ����action
		//�ɰ�ϵͳ��Ҳ����servletContext����ֻ����aciton���ܻ�ȡ��
		//����ʹ�ÿ��ܻ���ڲ������⣬�ĳɴ��ݣ����ӷ������������Խ����������ʱ�������Ƚϴ�
		//�ӳ�
		vs = getValueStack(request, response);
		vs.servlet = servlet;
		return vs;
	}

	/**
	 * ���뵼������ҳ��
	 * 
	 * @return
	 */
	public ActionForward toImportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ImportModel myForm = (ImportModel) form;
		try {
			ValueStack vs = getValueStack(request, response);
			// ���õ������
			List<ImportModel> impTableList = importService
					.getImportTableListByDrmkdm(myForm.getDrmkdm());
			vs.set("impTableList", impTableList);
			vs.set("drmkdm", model.getDrmkdm());
		} catch (Exception e) {
			throw new RuntimeException("���õ����д���",e);
		}
		return mapping.findForward("toImportData");
	}
	/**
	 * 
	 * @����: �Զ����õ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-26 ����11:34:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * ActionForward �������� 
	 */
	public ActionForward autoset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tableName=request.getParameter("tableName");
		String key=request.getParameter("key");
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		if(SAVE.equals(type)){
			ImportConfig ic=new ImportConfig();
			ic.config(key,name,tableName);
			Map<String,String> data=new HashMap<String, String>();
			data.put("message", "�Զ����óɹ���");
			response.getWriter().print(JSONObject.fromObject(data));
			return null;
		}
		return mapping.findForward("autoset");
	}
	public ActionForward autosetExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tableName=request.getParameter("tableName");
		String bh=request.getParameter("bh");
		String mc=request.getParameter("mc");
		String type=request.getParameter("type");
		if(SAVE.equals(type)){
			ImportConfig ic=new ImportConfig();
			ic.configExport(bh, mc, tableName);
			Map<String,String> data=new HashMap<String, String>();
			data.put("message", "�Զ����óɹ���");
			response.getWriter().print(JSONObject.fromObject(data));
			return null;
		}
		return mapping.findForward("autosetexport");
	}
	/**
	 * ��������
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ValueStack vs = getValueStack(request, response,servlet);
		User user = getUser(request);
		ImportModel myForm = (ImportModel) form;
		try {
			if(myForm.getDrms() != null && myForm.getDrms().equals("1")){
				// ��ȡ������ʱĿ¼
				String path = servlet.getServletContext().getRealPath(
						IImportService.IMPORT_TEMP_DIRPATH);

				StringBuffer filePath = new StringBuffer(path);
				filePath.append("\\");
				filePath.append(IImportService.IMPORT_ERROR);
				filePath.append("_");
				filePath.append(myForm.getDrmkdm());
				// ������������ļ����Ƽ����û�
				if (!StringUtils.isNull(user.getUserName())) {
					filePath.append("_");
					filePath.append(user.getUserName());
				}
				// ��ʱֵ֧��xls�ļ�����
				filePath.append(".");
				filePath.append(IImportService.IMPORT_SUFFLX);
				// �����ļ���� ����ʱĿ
				file = new File(filePath.toString());
				myForm.setUpdateFile(file);
			}

			HashMap<String, Object> importReslult = importService
					.importData(myForm, user);

			// ����ɹ�����
			if (importReslult != null
					&& importReslult.get("succeedList") != null) {
				/*
				 * logger.update(user, getText("log.message.ywmc", new String[]
				 * { "ͨ�õ���", model.getDrbm().toUpperCase() }), "ͨ�õ���",
				 * getText("log.message.czms", new String[] { "ͨ�õ���", "����ģ�����",
				 * model.getDrmkdm() }));
				 */
			}
			// ���ɴ����ļ�
			if (importReslult != null && importReslult.get("errorList") != null) {
				importService.getImportErrortData(myForm.getDrmkdm(), myForm
						.getDrbm(), (List<String[]>) importReslult
						.get("errorList"), user);
			}
			vs.set(ValueStack.JSON, importReslult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����ģ��
	 * 
	 * @return
	 */
	public ActionForward downloadTemplate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ValueStack vs = getValueStack(request, response,servlet);
		ImportModel myForm = (ImportModel) form;
		try {
			file = importService.getImportTemplate(myForm);
			vs.attachmentStream(file);
		} catch (Exception e) {
			throw new RuntimeException("����ģ�����!", e);
		}
		return null;
	}

	/**
	 * ���ش�������
	 * 
	 * @return
	 */
	public ActionForward downloadErrorData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ValueStack vs = getValueStack(request, response);
		ImportModel myForm = (ImportModel) form;
		try {
			// ��ȡ������ʱĿ¼
			String path = servlet.getServletContext().getRealPath(
					IImportService.IMPORT_TEMP_DIRPATH);
			// ��ȡ��ǰ�����û�
			User user = getUser(request);

			StringBuffer filePath = new StringBuffer(path);
			filePath.append("\\");
			filePath.append(IImportService.IMPORT_ERROR);
			filePath.append("_");
			filePath.append(myForm.getDrmkdm());
			// ������������ļ����Ƽ����û�
			if (!StringUtils.isNull(user.getUserName())) {
				filePath.append("_");
				filePath.append(user.getUserName());
			}
			// ��ʱֵ֧��xls�ļ�����
			filePath.append(".");
			filePath.append(IImportService.IMPORT_SUFFLX);
			// �����ļ���� ����ʱĿ
			file = new File(filePath.toString());
			vs.attachmentStream(file);
		} catch (Exception e) {

		}
		return null;
	}

}
