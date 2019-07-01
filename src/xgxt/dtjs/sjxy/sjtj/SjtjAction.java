package xgxt.dtjs.sjxy.sjtj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.dtjs.server.DtjsService;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.utils.SearchUtils;

public class SjtjAction extends DispatchAction{
	/**
	 * ��Ա�������ͳ��
	 * */
	public ActionForward dyxgsjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtjsService service = new DtjsService();
		request.setAttribute("tableName", "");
		request.setAttribute("realTable", "");
		request.setAttribute("title", "���Ž��� - ֧������ - ����ͳ��");
		request.setAttribute("xsccList", service.queryDtjsXspyccList());
		appendProperties(request, "", "", "");		
		return mapping.findForward("dyxgsjtj");
	}
	
	/**
	 * ��Ա�������ͳ�Ʋ�ѯ
	 * */
	public ActionForward dyxgsjtjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SjxyDtjsForm model = (SjxyDtjsForm)form;
		String[] colList = new String[] { "nj", "pycc", "qxxss","zsdy","zsdygirl","ybdy","jjfz","rdsq"};
		DtjsService service = new DtjsService();
		String tableName = "view_dyxgxx";

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName, colList, null);
		List<String[]> rs = service.queryDyxgsjtjForExport("view_dyxgxx",model,colList);//��ѯ��Ա�������ͳ����Ϣ
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", "");
		request.setAttribute("title", "���Ž��� - ֧������ - ����ͳ��");
		request.setAttribute("xsccList", service.queryDtjsXspyccList());
		appendProperties(request, "", "", "");		
		return mapping.findForward("dyxgsjtj");
	}
	
	/**
	 * ������Ա�������ͳ����Ϣ
	 * */
	public ActionForward expDyxgxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SjxyDtjsForm model = (SjxyDtjsForm)form;
		DtjsService service = new DtjsService();
		model.setNj(request.getParameter("nj"));
		model.setPycc(request.getParameter("pycc"));
		
		String[] colList = new String[] { "nj", "pycc", "qxxss","zsdy","zsdygirl","ybdy","jjfz","rdsq"};
		String[] colListCN = SearchUtils.getColumnNameCN("view_dyxgxx",colList);
		List<String[]> rs = service.queryDyxgsjtjForExport("view_dyxgxx",model,colList);//��ѯ��Ա�������ͳ����Ϣ
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/***
	 * ����ѧ����Ϣ�б�
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ		
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
	}

}
