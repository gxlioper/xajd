
package xgxt.pjpy.shList;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Observer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;
import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.hzy.HzyPjpyDAO;
import xgxt.pjpy.hzy.HzyPjpyShList;
import xgxt.utils.String.StringUtils;


public class ShListAction extends DispatchAction{
	public ActionForward dispatch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//subject-observer
		 
		 HttpSession session = request.getSession();
		 String xxdm = session.getAttribute("xxdm").toString();
		 String userType = session.getAttribute("userType") != null ? session.getAttribute("userType").toString() : "";
		 if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType) || GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)) {
			 request.setAttribute("message", "当前用户无权访问该页面！");
			return new ActionForward("/prompt.do", false);
		 }
		 ShListModel shListModel = new ShListModel();
		 @SuppressWarnings("unused")
		Observer observer =  null;
		 if(Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)){
			 return new ActionForward("/pjpyHzyXjchChkCx.do?method=xjchChkQuerry", false);
		 }
		 List<HashMap<String, String>> shList = new ArrayList<HashMap<String,String>>();
		 boolean ndFlag = false;//是否在页面显示年度
		 boolean xqFlag = false;//是否在页面显示学期
		 switch(Integer.parseInt(xxdm)){//根据不同的学校，获得相应的审核列表
		 case 12872:{//杭职院 
			 observer = new HzyPjpyShList(shListModel);//shListModel是观察者，observer是主管
			 shListModel.setXxdm(xxdm);
			 shList = shListModel.getShList();
			 xqFlag = true;
			 break;
		 }
		 case 10690:{
			 return new ActionForward("/ynys_xjbjsh.do", false);
		 }
		 default:break;	 		
		 }
		 if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {//宁波职业
			 HashMap<String, String> tmp = new HashMap<String, String>();
			 tmp.put("shxm", "view_pjpy_xjbjandwmsq");
			 tmp.put("shxmmc", "先进班级");
			 shList.add(tmp);
		 } else {
			 shList = new ArrayList<HashMap<String,String>>();
			 HashMap<String, String> tmp = new HashMap<String, String>();
			 tmp.put("shxm", "00001");
			 tmp.put("shxmmc", "先进班级");
			 shList.add(tmp);
			 HashMap<String, String> tmp1 = new HashMap<String, String>();
			 tmp1.put("shxm", "00002");
			 tmp1.put("shxmmc", "文明班级");
			 shList.add(tmp1);
		 }
		 ShListForm shForm = (ShListForm) form;
		// String userType = request.getSession().getAttribute("userType").toString();
		 if (StringUtils.isEqual(userType, "xy")) {
			shForm.setXydm(request.getSession().getAttribute("userDep").toString()); 
		 }
		 request.setAttribute("shList", shList);
		 request.setAttribute("xyList", Base.getXyList());
		 request.setAttribute("zyList", Base.getZyMap().get(""));
		 request.setAttribute("xnList", Base.getXnndList());
		 request.setAttribute("ndList", ndFlag?Base.getXnndList():null);
		 request.setAttribute("njList", Base.getNjList());
		 request.setAttribute("xqList", xqFlag?Base.getXqList():null);
		 request.setAttribute("writeAble", Base.getWriteAble(request));
		 request.setAttribute("userType", request.getSession().getAttribute("userType").toString());
		 return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		 String xxdm = session.getAttribute("xxdm").toString();
		 ShListModel shListModel = new ShListModel();
		 @SuppressWarnings("unused")
		Observer observer =  null;
		 List<HashMap<String, String>> shList = new ArrayList<HashMap<String,String>>();
//		 DealString deal = new DealString();
		ShListForm myForm = (ShListForm) form;
		ShListDAO dao = new ShListDAO();
		DAO utilDao = DAO.getInstance(); 
		String searchTable = myForm.getShxm();//获得要查询的表名
		searchTable = "view_pjpy_xjbjandwmsq";
		String pass = DealString.toGBK(request.getParameter("pass"));
		String userType = session.getAttribute("userType").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString()); 
		 }
		boolean ndFlag = false;//是否在页面显示年度
		 boolean xqFlag = false;//是否在页面显示学期
		 switch(Integer.parseInt(xxdm)){//根据不同的学校，获得相应的审核列表
		 case 12872:{//杭职院 
			 observer = new HzyPjpyShList(shListModel);//shListModel是观察者，observer是主管
			 shListModel.setXxdm(xxdm);
			 shList = shListModel.getShList();
			 xqFlag = true;
			 break;
		 }
		 default:break;	 		
		 }
		 if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {//宁波职业
			 HashMap<String, String> tmp = new HashMap<String, String>();
			 tmp.put("shxm", "view_pjpy_xjbjandwmsq");
			 tmp.put("shxmmc", "先进班级");
			 shList.add(tmp);
		 } else {
			 shList = new ArrayList<HashMap<String,String>>();
			 
			 HashMap<String, String> tmp = new HashMap<String, String>();
			 tmp.put("shxm", "00001");
			 tmp.put("shxmmc", "先进班级");
			 shList.add(tmp);
			 HashMap<String, String> tmp1 = new HashMap<String, String>();
			 tmp1.put("shxm", "00002");
			 tmp1.put("shxmmc", "文明班级");
			 shList.add(tmp1);
		 }
		//获取条件－－生成条件
		StringBuffer conditions = new StringBuffer(" where 1=1 ");//conditions 中已经加入了where
		ArrayList<String> values = new ArrayList<String>();
		String xn = myForm.getXn();
		String nd = myForm.getNd();
		String xq = myForm.getXq();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String shxm = request.getParameter("shxm");
		conditions.append((xn==null || "".equals(xn.trim()))? "" : " and xn=?");
		values.add((xn==null || "".equals(xn.trim()))? "" : xn);
		conditions.append((nd==null || "".equals(nd.trim()))? "" : " and nd=?");
		values.add((nd==null || "".equals(nd.trim()))? "" : nd);
		conditions.append((xq==null || "".equals(xq.trim()))? "" : " and xq=?");
		values.add((xq==null || "".equals(xq.trim()))? "" : xq);
		conditions.append((xydm==null || "".equals(xydm.trim()))? "" : " and xydm=?");
		values.add((xydm==null || "".equals(xydm.trim()))? "" : xydm);
		conditions.append((zydm==null || "".equals(zydm.trim()))? "" : " and zydm=?");
		values.add((zydm==null || "".equals(zydm.trim()))? "" : zydm);
		conditions.append((nj==null || "".equals(nj.trim()))? "" : " and nj=?");
		values.add((nj==null || "".equals(nj.trim()))? "" : nj);
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			conditions.append(" and rychdm='00001' ");
		} else {
			if (!StringUtils.isNull(shxm) && "00001".equalsIgnoreCase(shxm)) {
				conditions.append(" and rychdm='00001' ");
			} else if (!StringUtils.isNull(shxm) && "00002".equalsIgnoreCase(shxm)) {
				conditions.append(" and rychdm='00002' ");
			}  
		}
		HashMap<String , String[]> shENColumns = (new HzyPjpyDAO()).hzyShENColumns(userType);
		HashMap<String , String[]> shCNColumns = (new HzyPjpyDAO()).hzyShCNColumns(userType);
		HashMap<String , String> shsqlColumn = (new HzyPjpyDAO()).hzyShSqlColumn(userType);
		String[] encols = shENColumns.get(searchTable);
		String[] cncols = shCNColumns.get(searchTable);
		String sqlColumn = shsqlColumn.get(searchTable);
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<cncols.length;i++){
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", encols[i+2]);
			tempMap.put("cn", cncols[i]);
			topTr.add(tempMap);
		}
		ArrayList<String> subValArr = new ArrayList<String>();
		for(String temp : values){
			if(temp != null && temp.trim().length()>0)
				subValArr.add(temp);
		}
		
		 
		
		ArrayList<String[]> rs = dao.searchRS(utilDao, sqlColumn,searchTable, conditions.toString(), subValArr.toArray(new String[0]), encols, userType, pass);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs!=null?rs.size():0);
		request.setAttribute("rs", rs); 
		request.setAttribute("shList", shList);
		 request.setAttribute("xyList", Base.getXyList());
		 request.setAttribute("zyList", Base.getZyMap().get(""));
		 request.setAttribute("xnList", Base.getXnndList());
		 request.setAttribute("ndList", ndFlag?Base.getXnndList():null);
		 request.setAttribute("njList", Base.getNjList());
		 request.setAttribute("xqList", xqFlag?Base.getXqList():null);
		 request.setAttribute("writeAble", "yes");
		 request.setAttribute("userType", request.getSession().getAttribute("userType").toString());
		 request.setAttribute("pass", pass);
		return mapping.findForward("success");
	}
	
	public ActionForward saveShResult(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//subject-observer
		//根据不同的学校进行不同审核操作
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		DAO dao = DAO.getInstance();
		ShListForm myForm = (ShListForm) form;
		String searchTable = myForm.getShxm();//获得要查询的表名
		searchTable = "view_pjpy_xjbjandwmsq";
		String tableName = (new HzyPjpyDAO()).hzyShViewVsTable().get(searchTable);
		String primaryKey = (new HzyPjpyDAO()).hzyShTablePrimaryKey().get(searchTable);
		String[] checkArr = myForm.getCheckval();
		 if (StringUtils.isEqual(userType, "xy")) {
				myForm.setXydm(request.getSession().getAttribute("userDep").toString()); 
			 }
		String shjg = "shtgY".equals(request.getParameter("shjg"))?"通过":("shtgN".equals(request.getParameter("shjg"))?"不通过":"未审核");
		String[] sqlArr = new String[checkArr.length];
		if("xy".equalsIgnoreCase(userType)){
			for(int i=0;i<sqlArr.length;i++){
				sqlArr[i] = "update "+tableName+" set xysh='"+shjg+"' where "+primaryKey+"='"+checkArr[i]+"'";
			}
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			for(int i=0;i<sqlArr.length;i++){
				sqlArr[i] = "update "+tableName+" set xxsh='"+shjg+"' where "+primaryKey+"='"+checkArr[i]+"'";
			}
		}
		try{
		  int[] res = dao.runBatch(sqlArr);
		  boolean result = true;
		  for(int i=0;i<res.length;i++){
			  if(res[i]<0) result = false;
		  }
		  request.setAttribute("result", result);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("userType", request.getSession().getAttribute("userType").toString());
		
		return new ActionForward("/dispatch.do?method=search");
	}
	
	/**
	 * 先进班级文明班级单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xjbjandwmbjShone(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = new HashMap<String, String>();
		ShListForm shForm = (ShListForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		 if (StringUtils.isEqual(userType, "xy")) {
			shForm.setXydm(request.getSession().getAttribute("userDep").toString()); 
		 }
		resMap = dao
				.getMap(
						"select * from view_pjpy_xjbjandwmsq where xn||xq||rychdm||bjdm=?",
						new String[] { pkValue }, new String[] {"bjmc","tzs","bhgqs","ywcf","bjry","yxdzbyj", "xymc","zymc","bzxm", "bzr", "xsrs", "zysj","rychmc","xyyj", "xn", "xq","xxyj","xxsh" ,"xyqm", "xysh", "xyyj","bz", "jtch"});
		String xq = resMap.get("xq");
		String bhgqs = StringUtils.isNull(resMap.get("bhgqs")) ? "" : ("0".equalsIgnoreCase(resMap.get("bhgqs")) ? "有" : "无");
		String ywcf = StringUtils.isNull(resMap.get("ywcf")) ? "" : ("0".equalsIgnoreCase(resMap.get("ywcf")) ? "有" : "无");
		resMap.put("ywcf", ywcf);
		resMap.put("bhgqs", bhgqs);
		resMap.put("xq", StringUtils.isNull(xq) ? "" : (StringUtils.isEqual(xq, "03") ? "第一学期" : "第二学期"));
		request.setAttribute("shList", dao.getChkList(3));
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", request.getSession().getAttribute("userType").toString());
		request.setAttribute("xyyj", resMap.get("xyyj"));
		request.setAttribute("xxyj", resMap.get("xxyj"));
		shForm.setXyyj(resMap.get("xyyj"));
		shForm.setXxyj(resMap.get("xxyj"));
		return mapping.findForward("shone");
	}

	public ActionForward xjbjwmbjSave (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String pkValue = request.getParameter("pkValue");
		String xxyj = DealString.toGBK(request.getParameter("xxyj"));
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xyyj = DealString.toGBK(request.getParameter("xyyj"));
		String xysh =  DealString.toGBK(request.getParameter("xysh"));
		String userType = request.getSession().getAttribute("userType").toString();
		String xyqm = DealString.toGBK(request.getParameter("xyqm"));
		String yxdzbyj = DealString.toGBK(request.getParameter("yxdzbyj"));
		 
		boolean bFlag = false;
		if (StringUtils.isEqual(userType, "xy")) {
			if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
				bFlag = dao.runUpdate("update pjpy_xjbjandwmsqb set xysh=?,yxdzbyj=? where xn||xq||rychdm||bjdm=?", new String[]{xysh, yxdzbyj, pkValue});
			} else {
				bFlag = dao.runUpdate("update pjpy_xjbjandwmsqb set xysh=?,xyyj=?,xyqm=? where xn||xq||rychdm||bjdm=?", new String[]{xysh, xyyj,xyqm, pkValue});
			}
		} else {
			if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
				bFlag = dao.runUpdate("update pjpy_xjbjandwmsqb set xxsh=?,xxyj=?,xyyj=? where xn||xq||rychdm||bjdm=?", new String[]{xxsh, xxyj,xyyj, pkValue});
			} else {
				bFlag = dao.runUpdate("update pjpy_xjbjandwmsqb set xxsh=?,xxyj=? where xn||xq||rychdm||bjdm=?", new String[]{xxsh, xxyj, pkValue});
			}
		}
		if (bFlag) {
			request.setAttribute("result", "view");
		} else {
			request.setAttribute("result", "no");
		}
		request.setAttribute("shList", dao.getChkList(3));
		request.setAttribute("rs", new HashMap<String, String>());
		request.setAttribute("userType", request.getSession().getAttribute("userType").toString());
		request.setAttribute("xyyj", "");
		request.setAttribute("xxyj", "");
		ShListForm shForm = (ShListForm) form;
		// String userType = request.getSession().getAttribute("userType").toString();
		 if (StringUtils.isEqual(userType, "xy")) {
			shForm.setXydm(request.getSession().getAttribute("userDep").toString()); 
		 }
		 shForm.setXyyj("");
		 shForm.setXxyj("");
		return mapping.findForward("shone");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjDel (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
		 String xxdm = session.getAttribute("xxdm").toString();
		 ShListModel shListModel = new ShListModel();
		 ShListForm myForm = (ShListForm) form;
		 String[] pk = myForm.getCheckval();
		 @SuppressWarnings("unused")
		Observer observer =  null;
		 List<HashMap<String, String>> shList = new ArrayList<HashMap<String,String>>();
		 boolean ndFlag = false;//是否在页面显示年度
		 boolean xqFlag = false;//是否在页面显示学期
		 switch(Integer.parseInt(xxdm)){//根据不同的学校，获得相应的审核列表
		 case 12872:{//杭职院 
			 observer = new HzyPjpyShList(shListModel);//shListModel是观察者，observer是主管
			 shListModel.setXxdm(xxdm);
			 shList = shListModel.getShList();
			 xqFlag = true;
			 break;
		 }
		 default:break;	 		
		 }
		 
		 String sFlag = xjbjDels(pk, request);
		 if (StringUtils.isNull(sFlag)) {
			 request.setAttribute("deleted", "yes");
		 } else {
			 request.setAttribute("failinfo", String.format("其中第 %1$s 条数据删除失败", sFlag));
			 request.setAttribute("deleted", "no");
		 }
		 request.setAttribute("shList", shList);
		 request.setAttribute("xyList", Base.getXyList());
		 request.setAttribute("zyList", Base.getZyMap().get(""));
		 request.setAttribute("xnList", Base.getXnndList());
		 request.setAttribute("ndList", ndFlag?Base.getXnndList():null);
		 request.setAttribute("njList", Base.getNjList());
		 request.setAttribute("xqList", xqFlag?Base.getXqList():null);
		 request.setAttribute("writeAble", Base.getWriteAble(request));
		 request.setAttribute("userType", request.getSession().getAttribute("userType").toString());
		// ShListForm shForm = (ShListForm) form;
		 String userType = request.getSession().getAttribute("userType").toString();
		 if (StringUtils.isEqual(userType, "xy")) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString()); 
		 }
		return mapping.findForward("success");
	}
	
	
	public String xjbjDels(String[] keys, HttpServletRequest request) throws Exception {
		String sFlag = "";
		
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("pjpy_xjbjandwmsqb", "xn||xq||rychdm||bjdm", keys[i], request);
			if (!bFlag) {//失败后记录所在行数
				sFlag += (i+1);
				sFlag += ",";
			}
		}
		return StringUtils.isNull(sFlag) ? "" : sFlag.substring(0, sFlag.length() - 1);
	}
}
