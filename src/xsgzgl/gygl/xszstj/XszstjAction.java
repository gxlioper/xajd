package xsgzgl.gygl.xszstj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class XszstjAction extends BasicExtendAction{

	public ActionForward xszstjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_xszstj_xszstj.do");
		User user=getUser(request);
		
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		if(fdyqx||bzrqx){
			request.setAttribute("fdybzr", "true");
		}
		XszstjForm myForm=(XszstjForm)form;
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> list=service.getXszstjxxList(myForm,request);
		request.setAttribute("rs", list);
		request.setAttribute("searchTj", myForm.getSearchModel());

		// write��titile
		setWriteAbleAndTitle(request, "gyglnew_xszstj_xszstj.do");
		return mapping.findForward("xxzstjManage");
	}
	
	
	public ActionForward zstjExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjForm model=(XszstjForm)form;
		XszstjService service=new XszstjService();
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList=service.getXszstjxxExportList(model,request);
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
	 * �������ҷֲ�ͳ�Ʊ�
	 * @author wujian
	 */
	public ActionForward qsfbbTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjService service=new XszstjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("gyglnew_xszstj_xszstj.do");
		service.setRequestValue(rForm, user, request);
		// ��ȡѧԺ�����б��
		request.setAttribute("xyList", service.getXyList(request));
		return mapping.findForward("qsfbbTj");
	}
	
	public ActionForward qsfbDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjForm myForm=(XszstjForm)form;
		XszstjService service=new XszstjService();
		String xymc= request.getParameter("xymc");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String(xymc.getBytes("gb2312"),"iso-8859-1")+"qsfbdc.xls");
		service.expQsfbb(myForm,response.getOutputStream(),xymc);
		return null;
	}
	public ActionForward xszstjDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjForm myForm=(XszstjForm)form;
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> list=service.getXszstjDetailxxList(myForm);
		request.setAttribute("rs", list);
		request.setAttribute("path", "gyglnew_xszstj_detail.do");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xxzstjDetail");
	}
	
	public ActionForward xszstjXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjForm myForm = (XszstjForm) form;
		XszstjService service=new XszstjService();
//		RequestForm rForm = new RequestForm();
//		User user = getUser(request);// �û�����
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] colList = new String[]{"ѧ��","����","�꼶",Base.YXPZXY_KEY,"�Ա�","¥������","���Һ�","��λ��"};

		// =============== ִ�в�ѯ���� ===========
		rsArrList = service.getXszstjXsxxList(myForm,request);
//		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
//		service.setRequestValue(rForm, user,request);
		request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");
		
		request.setAttribute("path", "gyglnew_xszsgl_xszsgl.do");
		request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");	// ������
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("xxzstjXsxx");
	}
	
	
	/**
	 * 
	 * @����:ͼ��ͳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-5-17 ����04:06:22
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
	public ActionForward ztqksyt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> listTbtj = service.getTbtj();
		HashMap<String, String> zsgktj = service.getZsgktj();
		
		StringBuffer lddm = new StringBuffer();
		StringBuffer ldmc = new StringBuffer();
		StringBuffer yrz = new StringBuffer();
		StringBuffer kcw = new StringBuffer();
		StringBuffer rzl = new StringBuffer();
		
		
		for (HashMap<String, String> tbtj : listTbtj) {
			lddm.append(tbtj.get("lddm")+",");
			ldmc.append(tbtj.get("ldmc")+",");
			yrz.append(tbtj.get("yrz")+",");
			kcw.append(tbtj.get("kcw")+",");
			rzl.append(tbtj.get("rzl")+",");
		}
		if (lddm.length() > 1 && ',' == lddm.charAt(lddm.length() - 1)){ 
			request.setAttribute("lddm", lddm.deleteCharAt(lddm.length()-1));
			request.setAttribute("ldmc", ldmc.deleteCharAt(ldmc.length()-1));
			request.setAttribute("yrz", yrz.deleteCharAt(yrz.length()-1));
			request.setAttribute("kcw", kcw.deleteCharAt(kcw.length()-1));
			request.setAttribute("rzl", rzl.deleteCharAt(rzl.length()-1));
		 }
		request.setAttribute("path", "gyglnew_xszstj_ztqksyt.do");
		request.setAttribute("zsgktj", zsgktj);
		return mapping.findForward("ztqksyt");
	}
	
	
	/**
	 * 
	 * @����:����¥������Ϣͼ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-5-19 ����10:28:28
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
	public ActionForward ssljtxxt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> SslInfo = service.getSslInfo();
		List<HashMap<String, String>> SslcInfo = service.getSslcInfo();

		request.setAttribute("SslInfo", SslInfo);
		request.setAttribute("SslcInfo", SslcInfo);
		request.setAttribute("path", "gyglnew_xszstj_ssljtxxt.do");
		
		return mapping.findForward("ssljtxxt");
	}
	
	
	/**
	 * 
	 * @����:��ϵ����ֲ�ͼ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-6-1 ����10:25:43
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
	public ActionForward gxssfbt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> gxsstj = service.getGxsstj();

		request.setAttribute("gxsstj", gxsstj);
		request.setAttribute("path", "gyglnew_xszstj_gxssfbt.do");
		
		return mapping.findForward("gxssfbt");
	}
	
	
	/*
	 * ��ϵ�մ�λ��Ϣ
	 */
	public ActionForward gxkcwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> gxkcwxx = service.getGxkcwxx();

		request.setAttribute("gxkcwxx", gxkcwxx);
		request.setAttribute("path", "gyglnew_xszstj_gxkcwxx.do");
		
		return mapping.findForward("gxkcwxx");
	}
	
}
