
package xgxt.sxjy.action.szdw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SxjyForm;
import xgxt.utils.CheckPower;

public class FdydjAction extends DispatchAction{
	SxjyDAO dao = new SxjyDAO();
	String[] colList = null;
	String[] colListCN = null;
	ArrayList<String[]> rs = new ArrayList<String[]>();
	String sql =null;
	String writeAble = null;
	
	public ActionForward fdydj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
	HttpSession session = request.getSession();
	writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "counsellorLeadTeach.do?method=fdydj")?"yes":"no";
	String userType = session.getAttribute("userType").toString();
	String userDep = session.getAttribute("userDep").toString();
	String query = "";
	SxjyForm sxjyForm = (SxjyForm) form;
	String tableName = "view_fdydjjl";
	if(userType.equalsIgnoreCase("xy")){
		query = " where xydm = '"+userDep+"'";
		sxjyForm.setXydm(userDep);
	}else{
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		if(!xydm.equalsIgnoreCase("")){
		query = " where xydm = '"+xydm+"'";
		}
	}
	colList = new String[] { "zgh||bdjzgh","zgh","bdjzgh","xm","bdjxm","kssj","jssj","xymc"};
	colListCN = dao.getColumnNameCN(colList, tableName);
	List topTr = dao.arrayToList(colList, colListCN);//表头 
	if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
		rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
		request.setAttribute("rsNum", rs.size());
	}
	request.setAttribute("writeAble", writeAble);
	request.setAttribute("rs", rs);// 发送数据集
	request.setAttribute("xyList", Base.getXyList());
	request.setAttribute("topTr", topTr);// 发送表头
	request.setAttribute("realTable", "fdydjjlb");
	request.setAttribute("tableName", "view_fdydjjl");
	return mapping.findForward("success");
}
	public ActionForward modiOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
	String pk = DealString.toGBK(request.getParameter("pk"));
	HttpSession session = request.getSession();
	String userType = session.getAttribute("userType").toString();
	SxjyForm sxjyForm = (SxjyForm) form;
	String xydm = "";
	String zghTmp = "";
	if(userType.equalsIgnoreCase("xy")){
		xydm = session.getAttribute("userDep").toString();
		sxjyForm.setXydm(xydm);
	}else{
		xydm = DealString.toGBK(request.getParameter("xydm"));
	}
	String tableName = "view_fdydjjl";
	HashMap<String, String> map = new HashMap<String, String>();
	String type = DealString.toGBK(request.getParameter("type"));
	String kssj = DealString.toGBK(request.getParameter("kssj"));
	String jssj = DealString.toGBK(request.getParameter("jssj"));
	String jl1 = DealString.toGBK(request.getParameter("jl1"));
	String jl2 = DealString.toGBK(request.getParameter("jl2"));
	String jl3 = DealString.toGBK(request.getParameter("jl3"));
	String jl4 = DealString.toGBK(request.getParameter("jl4"));
	String zgh = DealString.toGBK(request.getParameter("zgh"));
	String bdjzgh = DealString.toGBK(request.getParameter("bdjzgh"));
	if(type.equalsIgnoreCase("refresh")){
		xydm = DealString.toGBK(request.getParameter("xydm"));
		zghTmp = zgh;
		if(zghTmp.equalsIgnoreCase("")){
			zghTmp = bdjzgh;
		}
		if(!zghTmp.equalsIgnoreCase("")){
			xydm = dao.sxjyQueryOne2("view_fdyxx",new String []{"bmdm"},"zgh",zghTmp)[0];
		}
		map.put("zgh", zgh);
		map.put("bdjzgh",bdjzgh);
		map.put("kssj",kssj);
		map.put("jssj",jssj);
		map.put("jl1",jl1);
		map.put("jl2",jl2);
		map.put("jl3",jl3);
		map.put("jl4",jl4);
		map.put("xydm",xydm);
	}else if(type.equalsIgnoreCase("refresh2")){
		xydm = DealString.toGBK(request.getParameter("xydm"));
		map.put("zgh", zgh);
		map.put("bdjzgh",bdjzgh);
		map.put("kssj",kssj);
		map.put("jssj",jssj);
		map.put("jl1",jl1);
		map.put("jl2",jl2);
		map.put("jl3",jl3);
		map.put("jl4",jl4);
		map.put("xydm",xydm);
	}else if(type.equalsIgnoreCase("save")){
		colList = new String[] { "zgh","bdjzgh","kssj","jssj","jl1","jl2","jl3","jl4"};
		String [] inputList =  new String[] { zgh,bdjzgh,kssj,jssj,jl1,jl2,jl3,jl4};
		boolean inserted = StandardOperation.delete("fdydjjlb", "zgh||bdjzgh",pk, request);
		if(inserted){
			inserted = StandardOperation.insert("fdydjjlb", colList, inputList, request);
		}
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		map = dao.sxjyQueryOne(tableName,colList, "zgh||bdjzgh","");
	}else{
	colList = new String[] { "zgh","bdjzgh","kssj","jssj","xydm","jl1","jl2","jl3","jl4"};
	map = dao.sxjyQueryOne(tableName,colList, "zgh||bdjzgh",pk);
	}
	request.setAttribute("rs", map);// 发送数据集
	request.setAttribute("tableName", "view_fdydjjl");// 发送表名
	request.setAttribute("pk", pk);// 发送表名
	request.setAttribute("fdyList", dao.getFdyList2(xydm,zghTmp));
	request.setAttribute("xyList", Base.getXyList());
	return mapping.findForward("modiOne");
}
	
	public ActionForward deleteOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		StandardOperation.delete("fdydjjlb", "zgh||bdjzgh",pk, request);
		return fdydj(mapping,form,request,response);
	}
}