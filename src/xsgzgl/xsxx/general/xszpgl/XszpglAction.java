/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-26 ����01:34:07 
 */  
package xsgzgl.xsxx.general.xszpgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.zpgl.FileOper;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy[����:754]
 * @ʱ�䣺 2013-9-26 ����01:34:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszpglAction extends SuperAction{
	
	private static final String url = "xsxx_tygl_xszpdr.do";

	//�б�
	@SystemAuth(url = url)
	public ActionForward xszpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszpglForm myForm = (XszpglForm) form;
		XszpglService service = new XszpglService();
		
		SearchModel searchModel=new SearchModel();
		searchModel.setPath("xsxx_tygl_xszpdr.do");
		request.setAttribute("searchTj", searchModel);
		User user = getUser(request);// �û�����
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_tygl_xszpdr.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszpManage");
	}
	
	//����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-ѧ����Ƭ����-����PHOTONAMETYPE:{photoNameType}")
	public ActionForward xszpdrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszpglForm myForm = (XszpglForm) form;
		List<HashMap<String, String>> zpscjgList = new ArrayList<HashMap<String, String>>();
		XszpglService service = new XszpglService();
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
	
	//����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xszpdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszpglForm myForm = (XszpglForm) form;
		XszpglService service = new XszpglService();
		SearchModel searchModel=new SearchModel();
		User user = getUser(request);// �û�����
		CommService comService = new CommService();
		searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		System.out.println("zpType:"+myForm.getZpType());
		
		if (EXP.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			service.zpdc(myForm, response,user);
			return null;
		}
		return mapping.findForward("zpdc");
	}
	
}
