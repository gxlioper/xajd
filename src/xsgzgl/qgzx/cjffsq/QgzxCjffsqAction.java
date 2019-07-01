package xsgzgl.qgzx.cjffsq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
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
public class QgzxCjffsqAction extends SuperAction {
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String qgzq = QgCommUtilf.getQgzq();
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("qgzx_cjffsq_cjxxgl.do");
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cjffsq_cjxxgl.do");
		service.setRequestValue(rForm, user, request);
		if("xq".equals(qgzq)){
			request.setAttribute("path", "qgzx_cjffsq_cjxxgl_xq.do");
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		
		HashMap<String,String> rs = service.setCxmrCs(request);
		request.setAttribute("rs", rs);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_jcffsqb");
		
		//��������
		request.setAttribute("jftx", service.getJftx(user));
		String sqkg=new QgzxCsszService().getSqkg();
		request.setAttribute("sqkg", sqkg);
		HashMap<String,String> map = new QgzxCsszService().getKfsqMap();
		request.setAttribute("sfkfsq",map.get("sfkfgzsq"));
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
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
		String csz = service.getCspzxx("cjffgs");
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_cjgl_cjxxgl.do");
		HashMap<String,String> rs = service.cjxxCk(model);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", model.getPkValue());
		String shck=request.getParameter("shck");
		request.setAttribute("shck", "1".equals(shck)?"1":"0");
		request.setAttribute("sqid", model.getSqid());
		/*ȡ�������ñ��еġ���𷢷Ź�ʱ��ʾ����0:����ʾ,1:��ʾ*/
		String csz = service.getCspzxx("cjffgs");
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
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
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		
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
	
	public ActionForward cjxxSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "qgzx_cjffsh_cjxxgl.do";
		request.setAttribute("path", path);
		request.setAttribute("qgzq", QgCommUtilf.getQgzq());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cjxxSh");
	}
	
	public ActionForward cjxxShCk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCjffsqService service = new QgzxCjffsqService();
		QgzxCjffsqForm model = (QgzxCjffsqForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_cjgl_cjxxgl.do");
		HashMap<String,String> rs = service.cjxxCk(model);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", model.getPkValue());
		String shid=request.getParameter("shid");
		request.setAttribute("shid", shid);
		service.setRequestValue(rForm, user, request);
		QgCommUtilf.setCssz(request);
		/*ȡ�������ñ��еġ���𷢷Ź�ʱ��ʾ����0:����ʾ,1:��ʾ*/
		String csz = service.getCspzxx("cjffgs");
		request.setAttribute("csz", csz);
		return mapping.findForward("cjxxShCk");
	}
	
	public ActionForward cjxxPlsh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "qgzx_cjffsh_cjxxgl.do";
		request.setAttribute("path", path);
		return mapping.findForward("cjxxPlsh");
	}

	public ActionForward gzsqImport(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response) throws Exception {

		ZdydrService zdydrService = new ZdydrService();
		//��ȡ����ģ�����
		String drmkdm = request.getParameter("drmkdm");
		//��ѯģ����Ϣ
		HashMap<String,String> drmbxx = zdydrService.getDrmbxx(drmkdm);
		//��ѯ���������Ϣ
		List<HashMap<String,String>>  drgzxxList =  zdydrService.getDrgzxxList(drmkdm);

		request.setAttribute("drmbxx", drmbxx);
		request.setAttribute("drgzxxList", drgzxxList);
		return mapping.findForward("gzsqImport");
	}
}
