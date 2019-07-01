package xgxt.sxjy.action;
/*
 * 创建日期 2007-7-9 ls_zzj
 *
 */

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.SxjyForm;
import xgxt.utils.GetTime;



public class RemindParameterSetupAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
    	throws Exception {	
	ActionForward myActFwd = null;
	String myAct = mapping.getParameter();
	
	try {
  	  int i = Base.chkTimeOut(request.getSession());
  	  if (i <= 2) {
  	  request.setAttribute("errMsg", "登陆超时，请重新登陆！");
  	  return new ActionForward("/login.jsp", false);
  	}
  	 if ("remindParameterSetupdis".equalsIgnoreCase(myAct)) {
	    // 提醒参数设置显示
	    myActFwd = remindParameterSetupdis(mapping, form, request, response);
	}else if("remindParameterSetupSave".equalsIgnoreCase(myAct)){
		 myActFwd = remindParameterSetupSave(mapping, form, request, response);
	}else if("turnInReport_Statistic".equalsIgnoreCase(myAct)){
		 myActFwd = turnInReport_Statistic(mapping, form, request, response);
	}else if("remindParameterTime".equalsIgnoreCase(myAct)){
		 myActFwd = remindParameterTime(mapping, form, request, response);
	}
	return myActFwd;
	}
	catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("errMsg", "数据连接中断，请重新登陆！");
	    return new ActionForward("/login.jsp", false);
	}
    }

	private ActionForward remindParameterTime(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 时间设置
		SxjyDAO dao = new SxjyDAO();
		boolean flag=false;
		String doType=request.getParameter("doType");
		 if("save".equalsIgnoreCase(doType)){
			 String xyjh = request.getParameter("xyjh");
			 String xyzj = request.getParameter("xyzj");
			 String hdch = request.getParameter("hdch");
			 String hdzj = request.getParameter("hdzj");
			 String zzxxnj = request.getParameter("zzxxnj");
			 String hdzjhb = request.getParameter("hdzjhb");
			 String xfjpjl = request.getParameter("xfjpjl");
			 String sql="delete from sxjyhz_sjszb";
			 flag=dao.runUpdate(sql, new String[]{});
			 if(flag){
				 String[] inputValues={xyjh,xyzj,hdch,hdzj,zzxxnj,hdzjhb,xfjpjl};
				 sql="insert into sxjyhz_sjszb(xyjh,xyzj,hdch,hdzj,zzxxnj,hdzjhb,xfjpjl) values(?,?,?,?,?,?,?)";
				 flag=dao.runUpdate(sql, inputValues);				 
			 }
			 request.setAttribute("oper", "save");
			 request.setAttribute("result", flag);
		 }
		 String tableName    = "sxjyhz_sjszb";
		 String [] colList = new String []{"xyjh","xyzj","hdch","hdzj","zzxxnj","hdzjhb","xfjpjl"};
		 HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "1", "1");
		 request.setAttribute("rs", rs);
		 return mapping.findForward("success");
	}

	private ActionForward turnInReport_Statistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SxjyDAO dao = new SxjyDAO();
//		HttpSession session = request.getSession();
//		DealString deal = new DealString();
//		String userType = session.getAttribute("userType").toString();
		String sql = "";
		Vector<Object> rs = new Vector<Object>(); 
//		String moduleName = "";
		SxjyForm myForm = (SxjyForm) form;
		
		
		
		//从条件表里查询可搜索模块的查询条件
		sql = "select b.gnmkmc,a.dybm from sxjy_jyhdcsszb a,gnmkdmb b where a.gnmkdm = b.gnmkdm";
		List mkList = dao.getList(sql, new String[] {}, new String[] { "gnmkmc", "dybm" });
		String realTable = DealString.toGBK(request.getParameter("belongToItem"));
		String sum = DealString.toGBK(request.getParameter("sum"));
		String month = myForm.getMonth();
		String year = myForm.getYear();
		String compare = myForm.getCompare();
		String time = "";
		if(realTable.equalsIgnoreCase("sxjy_zzxxtljlb")||realTable.equalsIgnoreCase("sxjy_hdzjhbb")){
			time = GetTime.getTime(year, month);
		}else{
			time = GetTime.getTime2(year, month);
		}
		if(realTable!=null&&!realTable.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer(" select xydm,xymc,sum from (select a.xydm,a.xymc,count(b.xydm) sum from ");
			sqlBuffer.append("(select distinct xydm,xymc from view_njxyzybj order by xydm) a left join (select xydm from ");
			sqlBuffer.append(realTable);
			sqlBuffer.append(" where lrrq like ? )");
			sqlBuffer.append("  b on a.xydm = b.xydm group by a.xydm,a.xymc) where sum ");
			sqlBuffer.append(compare);
			sqlBuffer.append(" ? order by sum desc");
			sql =sqlBuffer.toString();
			rs.addAll(dao.rsToVator(sql, new String []{time,sum}, new String []{"xydm","xymc","sum"}));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("yearList", GetTime.getYearList(5));
		request.setAttribute("mkList",mkList);
		request.setAttribute("chkList", dao.getChkList(6));
		request.setAttribute("dxList", dao.getChkList(10));
		request.setAttribute("rsNum", rs.size());// 发送记录数
		
		return mapping.findForward("success");
	}

	private ActionForward remindParameterSetupSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SxjyDAO dao = new SxjyDAO();
		
		String xyjhSbntxrq = DealString.toGBK(request.getParameter("xyjhSbntxrq"));
		String xyjhXbntxrq = DealString.toGBK(request.getParameter("xyjhXbntxrq"));
		String xyjhTJSJD = DealString.toGBK(request.getParameter("xyjhTJSJD"));
		String xyjhzjSbntxrq = DealString.toGBK(request.getParameter("xyjhzjSbntxrq"));
		String xyjhzjXbntxrq = DealString.toGBK(request.getParameter("xyjhzjXbntxrq"));
		String xyjhzjTJSJD = DealString.toGBK(request.getParameter("xyjhzjTJSJD"));
		String hdchTXRQ = DealString.toGBK(request.getParameter("hdchTXRQ"));
		String hdchTJSJD = DealString.toGBK(request.getParameter("hdchTJSJD"));
		String hdzjTXRQ = DealString.toGBK(request.getParameter("hdzjTXRQ"));
		String hdzjTJSJD = DealString.toGBK(request.getParameter("hdzjTJSJD"));
		String hdzjhbTXRQ = DealString.toGBK(request.getParameter("hdzjhbTXRQ"));
		String hdzjhbTJSJD = DealString.toGBK(request.getParameter("hdzjhbTJSJD"));
		String zzxxjlTJSJD = DealString.toGBK(request.getParameter("zzxxjlTJSJD"));
		String zzxxjlTXRQ = DealString.toGBK(request.getParameter("zzxxjlTXRQ"));
		
		boolean temp1 = dao.SxjyInsertSetup(new String[]{xyjhTJSJD,xyjhSbntxrq,xyjhXbntxrq,"N020403"});	
		boolean temp2 = dao.SxjyInsertSetup(new String[]{xyjhzjTJSJD,xyjhzjSbntxrq,xyjhzjXbntxrq,"N020404"});	
		boolean temp3 = dao.SxjyInsertSetup(new String[]{hdchTXRQ,hdchTJSJD,"N020407"});	
		boolean temp4 = dao.SxjyInsertSetup(new String[]{hdzjTXRQ,hdzjTJSJD,"N020408"});	
		boolean temp5 = dao.SxjyInsertSetup(new String[]{hdzjhbTXRQ,hdzjhbTJSJD,"N020409"});	
		boolean temp6 = dao.SxjyInsertSetup(new String[]{zzxxjlTXRQ,zzxxjlTJSJD,"N020411"});
		
		if(temp1==true&&temp2==true&&temp3==true&&temp4==true&&temp5==true&&temp6==true){
			request.setAttribute("ok","ok");
		}else{
			request.setAttribute("ok","no");
		}
		return mapping.findForward("success");
	}

	private ActionForward remindParameterSetupdis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SxjyDAO dao = new SxjyDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		Vector<Object> rs = new Vector<Object>(); 
    	String sql = "";
    	SxjyForm myForm = (SxjyForm) form;
    		
    	sql ="select a.txzl,a.gnmkdm,b.gnmkmc,a.dybm,a.txrq,a.tjsjd,a.sbntxrq,a.xbntxrq from sxjy_jyhdcsszb a,gnmkdmb b where a.gnmkdm = b.gnmkdm order by a.gnmkdm";
    	rs.addAll(dao.rsToVator(sql, new String[]{}, new String[]{"txzl","gnmkdm","gnmkmc","dybm","txrq","tjsjd","sbntxrq","xbntxrq"}));
    	myForm.setXyjhTJSJD(((String[])rs.get(0))[5]);
    	map.put("xyjhSbntxrq", ((String[])rs.get(0))[6]);
    	map.put("xyjhXbntxrq", ((String[])rs.get(0))[7]);
    	
    	myForm.setXyjhzjTJSJD(((String[])rs.get(1))[5]);
    	map.put("xyjhzjSbntxrq", ((String[])rs.get(0))[6]);
    	map.put("xyjhzjXbntxrq", ((String[])rs.get(0))[7]);
    	
    	map.put("hdchTXRQ", ((String[])rs.get(2))[4]);
    	myForm.setHdchTJSJD(((String[])rs.get(2))[5]);
    	
    	map.put("hdzjTXRQ", ((String[])rs.get(3))[4]);
    	myForm.setHdzjTJSJD(((String[])rs.get(3))[5]);
    	
    	map.put("hdzjhbTXRQ", ((String[])rs.get(4))[4]);
    	myForm.setHdzjhbTJSJD(((String[])rs.get(4))[5]);
    	
    	map.put("zzxxjlTXRQ", ((String[])rs.get(5))[4]);
    	myForm.setZzxxjlTJSJD(((String[])rs.get(5))[5]);
    
    	request.setAttribute("rs", map);
    	request.setAttribute("chkList", dao.getChkList(9));
    	
    	return mapping.findForward("success");
	}
}
