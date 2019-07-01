package xsgzgl.jxgl.general.jxjzgl.lsjzgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.jxgl.general.jxjzgl.jxjzgl.JxjzglFrom;
import xsgzgl.jxgl.general.jxjzgl.jxjzgl.JxjzglService;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhForm;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ʷ���ƹ���_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author �׽���
 * @version 1.0
 */

public class LsjzglAction extends BasicAction {
	/**
	 * ��ѯ��ʷ��ѵ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom lsjzModel=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		RequestForm rForm = new RequestForm();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		
		//��ѯ��ѵ��Ϣ�б�
		List<HashMap<String, String>> jxxxList=jxxxwhService.getJxxxList();
		if(jxxxList == null || jxxxList.size() == 0){
			//���ز��ܽ��Ƶ�ҳ��
			String msg = "��ǰ��δ�������κξ�ѵ,����ʷ��ѵ��Ϣ��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//��ѯͳ������
		lsjzModel.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(lsjzModel);
		//���ò�ѯ����
		request.setAttribute("rsTj", rsTj);
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		request.setAttribute("jxxxList", jxxxList);
		
		// ����·��
		String path = "jxjzgl_lsjzgl_cxLsjz.do";
		rForm.setPath(path);
		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return mapping.findForward("cxLsjz");
	}
	
	/**
	 * ��ѯ����ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjzTjbAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		
		String jxjzTjbHtml=service.createJxjzTjxHtml(jxjzList, model);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(jxjzTjbHtml));
		return null;
	}
	
	/**
	 * ��ѯ����ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjxxxAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		JxglJxxxwhForm jxxxModle=new JxglJxxxwhForm();
		jxxxModle.setJxid(model.getSjid());
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhByJxidModel(jxxxModle);
		//��ѯͳ������
		HashMap<String, String> rsTj=service.getRszjTjb(model);
		
		String lsjxxxHtml=service.createLsjxjzHtml(jxxxwhModel, rsTj);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(lsjxxxHtml));
		return null;
	}
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dcJzTjb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		JxglJxxxwhForm jxxxModel=new JxglJxxxwhForm();
		jxxxModel.setJxid(model.getSjid());
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhByJxidModel(jxxxModel);
		//��������ͳ��
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		//��ѯͳ������
		HashMap<String, String> title=service.getRszjTjb(model);
		title.put("jxmc", jxxxwhModel.get("jxmc"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dcJzTjb(response.getOutputStream(),model,title,jxjzList);
		return mapping.findForward("");
	}
	
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LsjzglService service=new LsjzglService();
		RequestForm rForm = new RequestForm();
		LsjzglFrom model=(LsjzglFrom)form;
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		User user=getUser(request);
		
		JxglJxxxwhForm jxxxModel=new JxglJxxxwhForm();
		jxxxModel.setJxid(model.getSjid());
		//��ȡ��ǰ��ѵ��Ϣ
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhByJxidModel(jxxxModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		request.setAttribute("path", "jxjzgl_lsjzgl_cxLsjz.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("lsjzgl_cxLsjz_ajax.do?method=cxLsjzmdAjax");
		// ----------------����PATH end-----------------------
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("model", model);
		
		//���õ���
		request.setAttribute("tableName", "xg_view_jxgl_jxjzmdb");//һ��Ϊ��ѯ��ͼ
		request.setAttribute("jxxxwhModel", jxxxwhModel);//��ѯ��ѵ��Ϣ
		
		//Ĭ�ϸ߼���ѯ����
		String jzid=request.getParameter("jzid");
		String bjdm=request.getParameter("bjdm");
		if(jzid != null && !"".equals(jzid)){
			SearchModel searchModel = service.setDefaultSearchModel(jzid, bjdm);
			request.setAttribute("searchTj", searchModel);
		}
		String sjjz=request.getParameter("sjjz");
		if(sjjz != null && !"".equals(sjjz)){
			SearchModel searchModel = new SearchModel();
			if("1".equals(sjjz)){
				searchModel.setSearch_tj_sfybz(new String[]{"��"});
			}else{
				searchModel.setSearch_tj_sfybz(new String[]{"��"});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		return mapping.findForward("cxLsjzmd");
	}
	
	/**
	 * ��ѯά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjzmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//��ѯ·��
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("lsjzgl_cxLsjz_ajax.do?method=cxLsjzmdAjax");
		//model.getSearchModel().setPath("jxjzgl_lsjzgl_cxLsjz.do");
		// IE�汾
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		rsModel.setIe(otherValue[0]);
		//��������
		model.setSjid(otherValue[1]);
		model.setJxid(otherValue[1]);
		
		//��ȡ��ǰ��ѵ��Ϣ
		ArrayList<String[]> rsArrList=service.getJxjzmdList(model);
		//��ͷ
		List<HashMap<String, String>> topTr = service.getTopTr("whjzmd");
		
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ����ά����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dcLsjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		
		//��������ͳ��
		List<HashMap<String, String>> lsjzmdList=service.getJxjzmdNoPageList(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dcLsjzmd(response.getOutputStream(), lsjzmdList);
		return mapping.findForward("");
	}
	
	/**
	 * ��ʷ���ƹ����Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward lsjzglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();		
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getJxjzmdExportDataList(model);
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
