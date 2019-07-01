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
	 * 党员相关数据统计
	 * */
	public ActionForward dyxgsjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtjsService service = new DtjsService();
		request.setAttribute("tableName", "");
		request.setAttribute("realTable", "");
		request.setAttribute("title", "党团建设 - 支部管理 - 数据统计");
		request.setAttribute("xsccList", service.queryDtjsXspyccList());
		appendProperties(request, "", "", "");		
		return mapping.findForward("dyxgsjtj");
	}
	
	/**
	 * 党员相关数据统计查询
	 * */
	public ActionForward dyxgsjtjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SjxyDtjsForm model = (SjxyDtjsForm)form;
		String[] colList = new String[] { "nj", "pycc", "qxxss","zsdy","zsdygirl","ybdy","jjfz","rdsq"};
		DtjsService service = new DtjsService();
		String tableName = "view_dyxgxx";

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName, colList, null);
		List<String[]> rs = service.queryDyxgsjtjForExport("view_dyxgxx",model,colList);//查询党员相关数据统计信息
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", "");
		request.setAttribute("title", "党团建设 - 支部管理 - 数据统计");
		request.setAttribute("xsccList", service.queryDtjsXspyccList());
		appendProperties(request, "", "", "");		
		return mapping.findForward("dyxgsjtj");
	}
	
	/**
	 * 导出党员相关数据统计信息
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
		List<String[]> rs = service.queryDyxgsjtjForExport("view_dyxgxx",model,colList);//查询党员相关数据统计信息
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/***
	 * 加载学籍信息列表
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
		
		request.setAttribute("writeAble", "yes");//判断用户读写权		
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
	}

}
