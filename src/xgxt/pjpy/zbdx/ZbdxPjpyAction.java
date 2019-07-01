package xgxt.pjpy.zbdx;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CheckPower;

public class ZbdxPjpyAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response ) throws Exception{
		String parameter = mapping.getParameter();
		HttpSession session = request.getSession();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String usrName = session.getAttribute("userName").toString();
		ActionForward af = new ActionForward();
		if("pjpy_zbdx_tyrwcxsjcjwh".equalsIgnoreCase(parameter)){
			// TODO  加入权限限制
			if(CheckPower.checkUsrPageAccessPower(userOnLine, usrName, "pjpy_zbdx_tyrwcxsjcjwh.do")){
				af = pjpy_zbdx_tyrwcxsjcjwh(mapping,form,request,response);
			}
		} else if("exptoexcel".equalsIgnoreCase(parameter)){
			af = exptoexcel(mapping,form,request,response);
		}
		else if("pjpy_zbdx_weihu_one".equalsIgnoreCase(parameter))
		{
			af = pjpy_zbdx_weihu_one(mapping,form,request,response);
		}
		
		return af;
	}
	
	private ActionForward pjpy_zbdx_tyrwcxsjcjwh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		// TODO 分为维护学生信息和无维护
		DAO	 dao = DAO.getInstance();
		ZbdxPjpyForm myform = (ZbdxPjpyForm) form;
		String action = request.getParameter("act");
		HttpSession session = request.getSession();
		String dataTable = request.getParameter("dataTable");//获得要操作的数据表
		String xydm = (myform.getXydm()!=null)?myform.getXydm():CheckPower.getDepartmentId(session);
		String zydm = myform.getZydm();
		String xn = myform.getXn();
		String xq = myform.getXqdm();//学期代码
		String nj = myform.getNj();
		String bjdm = myform.getBjdm();
		String go = request.getParameter("go");
		List<HashMap<String, String>> rs = new Vector<HashMap<String,String>>();
		if(action == null && dataTable != null && !dataTable.trim().equals("") && "go".equalsIgnoreCase(go) ) {//选择相应的数据表进行操作
			String[] title = null;
			String[] condition = {"xydm","zydm","bjdm","xn","xq","nj"};
			String[] val = {xydm,zydm,bjdm,xn,xq,nj};
			ZbdxPjpyDAO zbdao = new ZbdxPjpyDAO();
			String dataTableTmp = dataTable.trim();
			title = zbdao.getTableCol(dataTableTmp);
			rs = zbdao.getTableData(dataTableTmp, condition, val);
			request.setAttribute("title", title);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		if("save".equalsIgnoreCase(action)){
			//保存学生的相关信息
			boolean doresult = false;
			try{
				if(Pattern.matches("\\w*_tycjb", dataTable)){
					String[] xhArr = myform.getXh();
					String[] stszcpcjArr = myform.getStszcpcj();
					for(int i=0;i<xhArr.length;i++){
						StandardOperation.delete("tycjb", "xn||xq||xh", xn+xq+xhArr[i], request);
						StandardOperation.insert("tycjb", new String[]{"xn","xq","xh","stszcpcj"}, new String[]{xn,xq,xhArr[i],stszcpcjArr[i]}, request);
					}
					doresult = true;
				} else if(Pattern.matches("\\w*_rwszcjb", dataTable)){
					String[] xhArr = myform.getXh();
					String[] rwcjArr = myform.getRwcj();
					for(int i=0;i<xhArr.length;i++){
						StandardOperation.delete("rwszcjb", "xn||xq||xh", xn+xq+xhArr[i], request);
						StandardOperation.insert("rwszcjb", new String[]{"xn","xq","xh","rwcj"}, new String[]{xn,xq,xhArr[i],rwcjArr[i]}, request);
					}
					doresult = true;
				} else {
					String[] xhArr = myform.getXh();
					String[] cxrjcjArr = myform.getCxrjcj();
					for(int i=0;i<xhArr.length;i++){
						StandardOperation.delete("cxysjcjb", "xn||xq||xh", xn+xq+xhArr[i], request);
						StandardOperation.insert("cxysjcjb", new String[]{"xn","xq","xh","cxrjcj"}, new String[]{xn,xq,xhArr[i],cxrjcjArr[i]}, request);
					}
					doresult = true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("doresult", doresult);
		}
		request.setAttribute("tableName", (dataTable!=null)?dataTable.toLowerCase():" ");
		request.setAttribute("realTable", (dataTable!=null)?dataTable.substring(dataTable.indexOf("_")+1, dataTable.length()):"");
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
		request.setAttribute("xqList", dao.getXqList());
//		System.out.println(request.getAttribute("tableName"));
		return mapping.findForward("success");
	}
	
	private ActionForward exptoexcel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String tableName = request.getParameter("tableName");
		ZbdxPjpyForm myform = (ZbdxPjpyForm) form;
		String xn = myform.getXn();
		String xq = myform.getXqdm();
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		String bjdm = myform.getBjdm();
		String nj = myform.getNj();
		String[] val = {xn,xq,xydm,zydm,bjdm,nj};
		String[] condition = {"xn","xq","xydm","zydm","bjdm","nj"};
		StringBuffer sql = new StringBuffer(" select xn,xq,xh,xm,bjmc,");
		if(Pattern.matches("\\w*_tycjb", tableName)){
			sql.append("stszcpcj from ");
		} else if(Pattern.matches("\\w*_rwszcjb", tableName)){
			sql.append("rwcj from ");
		} else {
			sql.append("cxrjcj from ");
		}
		sql.append(tableName);
		StringBuffer tmpsb = new StringBuffer();
		for(int i=0;i<val.length;i++){
			if(val[i]!=null && !val[i].trim().equals("")){
				tmpsb.append(condition[i]);
				tmpsb.append("='");
				tmpsb.append(val[i]);
				tmpsb.append("' @ ");
			}
		}
		if(tmpsb.indexOf("@")>0){
			sql.append(" where ");
			sql.append(tmpsb.deleteCharAt(tmpsb.lastIndexOf("@")).toString().replace("@", "and"));
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try{
			Excel2Oracle.exportData(sql.toString(), tableName, response.getOutputStream());
		} catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	//中北大学综合测评加分维护
	public String zbdxZhcpJfwh(String xh,String xn,String tag,String xmdm,String df){
		// TODO 通过dwr调用直接在客户端实现学生分数记录的增加
		HttpServletRequest request = null;
		DAO dao = DAO.getInstance();
		boolean result = false;
		String nd = dao.getConf(3);
		// TODO 完成维护操作
		String tableName = "";
		String[] columns = null;
		String[] values = new String[]{xh,xn,nd,xmdm,df,"1"};
		String specialcol = "";
		if(tag.equalsIgnoreCase("tyjsdm")){
			columns = new String[]{"xh","xn","nd",tag,"tybsjf","xq"};
			tableName = "zhszcp_tycp";
			specialcol = "tyjsdm";
		} else if(tag.equalsIgnoreCase("zyjsdm")){
			columns = new String[]{"xh","xn","nd",tag,"hjjf","xq"};//人文素质得分对应zhszcp_dyszjfwh中的zyjsdm(智育竞赛代码),hjjf(获奖加分)
			tableName = "zhszcp_zyrwcp";
			specialcol = "zyjsdm";
		} else {
			columns = new String[]{"xh","xn","nd",tag,"cxdf","xq"};
			tableName = "zhszcp_cxcp";
			specialcol = "cxxmdm";
		}
		String sql = "select * from "+tableName+" where xh=? and xn=? and nd=? and "+specialcol+"=?";
		try{
			if("empty".equalsIgnoreCase(dao.returntag(sql,new String[]{xh,xn,nd,xmdm}))){
				result = StandardOperation.insert(tableName, columns, values, request);
			} else {
				String primaryKey = "xh||xn||nd";
				String pkValue = xh+xn+nd;
				result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result?"true":"false";
	}
	public String zbdxZhcpDeleteAddedData(String xh,String xn,String awardxmdm,String awardkind,HttpServletRequest request){
		boolean result = false;
		String tableName = "";
		String primaryKey = "xh||xn||"+awardkind;
		if("tyjsdm".equalsIgnoreCase(awardkind)){
		 tableName = "zhszcp_tycp";
		} else if("zyjsdm".equalsIgnoreCase(awardkind)){
			tableName = "zhszcp_zyrwcp";
		} else {
			tableName = "zhszcp_cxcp";
		}
		try{
			result = StandardOperation.delete(tableName, primaryKey, xh+xn+awardxmdm, request);
		} catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		return result?"true":"false";
	}
	
	
	private ActionForward pjpy_zbdx_weihu_one(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		    DAO dao = DAO.getInstance();
			ZbdxPjpyForm myform = (ZbdxPjpyForm) form;
			ZbdxPjpyDAO myutil=new ZbdxPjpyDAO();
		    String xh=request.getParameter("xh");
		    String xn=request.getParameter("xn");
		    String nd=request.getParameter("nd");
		    String doType=request.getParameter("doType");
//		    int a=0;
//		    int b=0;
		    if(null==doType||"".equalsIgnoreCase(doType))
		    {
		    }
		    else if("save".equalsIgnoreCase(doType))
		    {
		    	myform=myutil.deal_gbk_notnull_form(myform);
		    	String message=myutil.Zbdx_save_zhszcpcj(myform);
		    	request.setAttribute("message", message);
		    	myform=myutil.find_stu_zhszcp_detail(myform);
		    	request.setAttribute("rs", myform);
		    }
		    else if("view".equalsIgnoreCase(doType))
		    {
		    	myform.setXh_l(xh);
		    	myform.setXn(xn);
		    	myform.setNd(nd);
		    	myform=myutil.find_stu_zhszcp_detail(myform);
		    	request.setAttribute("rs", myform);
		    }
		    else
		    {
		    	
		    }
		    String tips="评奖评优－信息维护－综合素质测评";
		    request.setAttribute("njList", dao.getNjList());// 发送年级列表
			request.setAttribute("xnList", dao.getXnndList());// 发送学年列表
			request.setAttribute("xqList", dao.getXqList());// 发送学期列表
			request.setAttribute("tips",tips);
		    return mapping.findForward("success");
	}
	

}
