package xgxt.xtwh.zpgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

public class XtwhZpglAction extends BasicAction {

	/**
	 * ��Ƭ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zpglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhZpglForm myForm = (XtwhZpglForm) form;
		XtwhZpglService service = new XtwhZpglService();
		RequestForm rForm = new RequestForm();
		XtwhZpglInit init = new XtwhZpglInit();
		User user = getUser(request);

		init.initXszpInfo(rForm, myForm, request);

		ArrayList<String[]> rs = (ArrayList<String[]>) service.getZpxxList(
				myForm, rForm.getColList(),user);

		rForm.setRsArrList(rs);
		service.setRequestValue(rForm, user, request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("zpglManage");
	}
	/**
	 * ��Ƭ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zpdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("zpdc");
	}
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-ѧ����Ƭ����-����PHOTONAMETYPE:{photoNameType}")
	public ActionForward xszpdrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XtwhZpglForm myForm = (XtwhZpglForm) form;
		List<HashMap<String, String>> zpscjgList = new ArrayList<HashMap<String, String>>();
		XtwhZpglService service = new XtwhZpglService();
		String doType = request.getParameter("doType");
		String photoNameType = request.getParameter("photoNameType");
		if ("save".equalsIgnoreCase(doType)) {
			
			String tempDir = servlet.getServletContext().getRealPath("/zip");
			// ���ļ��ϴ��������ļ�����·��
			String tempFilePath = service.fileUp(tempDir, myForm.getFile(),
					myForm.getFile().getFileName(), false);
			// ��ѹzip
			String photoPath = service.unZip(tempFilePath, tempDir);
			// ��Ƭ���
			zpscjgList = service.savePhoto(photoPath, request, photoNameType);
			// �ϴ���Ϻ�ɾ����Ƭ���ϴ���zip
			FileOper.delFile(tempFilePath);
			FileOper.delFiles(photoPath);
		}

		if (zpscjgList != null && zpscjgList.size() > 0) {

			String modelPath = servlet.getServletContext().getRealPath("")
					+ "/print/gygl/gygl_ssnjfbtj.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());

			service.printZpdr(myForm, photoNameType, request, zpscjgList, wwb);
			return null;

		}
		request.setAttribute("zpType", myForm.getZpType());
		return mapping.findForward("xszpdrManage");

	}

	/**
	 * zpdc ����
	 * <p>
	 * ����˵��: ��Ƭ����
	 * </p>
	 * 
	 * @return String
	 * @author hhy
	 * @date 2011-8-30
	 */
	public ActionForward xszpdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhZpglService service = new XtwhZpglService();
		XtwhZpglForm myForm = (XtwhZpglForm) form;
		service.zpdc(myForm, response);
		return null;
	}

}
