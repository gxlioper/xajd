package xsgzgl.qgzx.cjffjg;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cjffsq.QgzxCjffsqService;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * �ڹ���ѧ-������-�����Ϣ����
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffjgAction extends BasicAction {
	/**
	 * �����Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String qgzq = QgCommUtilf.getQgzq();
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("qgzx_cjffjg_cjxxgl.do");
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cjffjg_cjxxgl.do");
		service.setRequestValue(rForm, user, request);
		if("xq".equals(qgzq)){
			request.setAttribute("path", "qgzx_cjffjg_cjxxgl_xq.do");
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		
		HashMap<String,String> rs = service.setCxmrCs(request);
		request.setAttribute("rs", rs);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_jcffb");
		
		//��������
		request.setAttribute("jftx", service.getJftx(user));
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("cjxxCx");
		}
	}
	
	
	/**
	 * �����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjxxFf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffsqService qgzxCjffsqService = new QgzxCjffsqService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_cjgl_cjxxgl.do");
		// 2014-04-20 �����б�ֻ��ʾ�и�λ��
		//HashMap<String,String> rs = service.setFfmrCs(request,model);
		HashMap<String,String> rs = service.setFfmrCsNew(request,model);
		request.setAttribute("rs", rs);
		request.setAttribute("xxdm", Base.xxdm);
		service.setRequestValue(rForm, user, request);
		if("10351".equals(Base.xxdm)){
			request.setAttribute("cs",new QgzxCsszService().getCssz());
		}
		/*ȡ�������ñ��еġ���𷢷Ź�ʱ��ʾ����0:����ʾ,1:��ʾ*/
		String csz = qgzxCjffsqService.getCspzxx("cjffgs");
		request.setAttribute("csz", csz);
		this.saveToken(request);
		return mapping.findForward("cjxxFf");
	}
	
	
	/**
	 * �����Ϣ�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjxxCk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_cjgl_cjxxgl.do");
		HashMap<String,String> rs = service.cjxxCk(model);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", model.getPkValue());
		/*ȡ�������ñ��еġ���𷢷Ź�ʱ��ʾ����0:����ʾ,1:��ʾ*/
		QgzxCjffsqService qgzxCjffsqService = new QgzxCjffsqService();
		String csz = qgzxCjffsqService.getCspzxx("cjffgs");
		request.setAttribute("csz", csz);
		service.setRequestValue(rForm, user, request);
		QgCommUtilf.setCssz(request);
		return mapping.findForward("cjxxCk");
	}
	
	/**
	 * @�����������ϸҳ�����ѧ������ģ����ѯ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward cjxxCkByxhxm(ActionMapping mapping,ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		HashMap<String,String> rs = service.cjxxCk(model);
		response.getWriter().print(rs.get("cjmxHtml"));
		return null;
	}
	
	/**
	 * @����:���鿴��һ����𷢷���Ϣ����ΪExcel�ļ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��6�� ����7:59:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws Exception
	 */
	public ActionForward cjxxExportExcel(ActionMapping mapping,ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		
		//����ļ���ʱ·��
		String path = servlet.getServletContext().getRealPath("/temp/mb/");
		
		//����excel�ļ�
		File file = service.getCjxxExcel(path,model);
		
		//��Ӧ����
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(file.getName(),"utf-8")); 
		FileUtil.outputFile(response, file);
		
		//������ɺ�ɾ�����ɵ��ļ�
		if(file.exists()){
			file.delete();
		}
		return null;
		
	}

	/**
	 *
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-21 ����06:02:27
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
	@SystemLog(description = "�ڹ���ѧ-��𷢷�-����")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getCjffList(model, user);
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

}
