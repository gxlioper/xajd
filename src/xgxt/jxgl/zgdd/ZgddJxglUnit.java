package xgxt.jxgl.zgdd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.Fdypd;
import xgxt.utils.Pages;

public class ZgddJxglUnit {
	
	public void setFormForXnNdXqNj(ZgddJxglForm myForm) {
		//评奖评优模块
		String xn = Base.getJxjsqxn();
		String nd = Base.getJxjsqnd();
		String xq = Base.getJxjsqxq();

		if ((DealString.toGBK(myForm.getXn())).equalsIgnoreCase("")) {
			myForm.setXn(xn);
		}

		if ((DealString.toGBK(myForm.getXq())).equalsIgnoreCase("")) {
			myForm.setXq(xq);
		}

		if ((DealString.toGBK(myForm.getNd())).equalsIgnoreCase("")) {
			myForm.setNd(nd);
		}
	}
	
	public void commonRequestSet(HttpServletRequest request, String tableName, String realTable, ArrayList<String[]> rs, List topTr) {
		// Request存值的通用方法 区别是title从数据库里取
		String writeAble  = request.getParameter("writeAble");
		String title      = DealString.toGBK(request.getParameter("title"));
		if(Base.isNull(writeAble)) {
			String [] message = getWriteAbleAndTitle(request);
			writeAble = message[0];
			title     = message[1];
		}
		
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public String [] getWriteAbleAndTitle(HttpServletRequest request){
		//传入request,返回页面标题（由功能模块名称生成）和读写权，返回的数组第一个值是读写权，第二个是标题
		//使用时页面上要加上name为title的隐藏域
		HttpSession session = request.getSession();						//得到session
		String userType = "";
		boolean isStu = true;
		String sUName = "";
		String sPath = "";
		String qStr = "";
		String power = "";
		String writeAble = "";
		userType = session.getAttribute("userType").toString();   //得到用户类型
		isStu = (userType.equalsIgnoreCase("stu"));				 //判断是否是学生用户登录
		sUName = session.getAttribute("userName").toString();	//得到登录用户名
		if(request.getAttribute("path")!=null){
			power = (String)request.getAttribute("path");
		}else{
			sPath = request.getServletPath().replace("/", "");
			qStr = request.getQueryString();		
			if("".equalsIgnoreCase(qStr) || qStr == null){
				power = sPath;
			}else{
				power = sPath + "?" + qStr;
			}
		}
		String messTemp[] = chkUPower(sUName, power, isStu);
		if (!Base.isNull(messTemp[0]) && messTemp[0].equals("0")) {
			// 只读
			writeAble = "no";
		} else if (!Base.isNull(messTemp[0]) && messTemp[0].equals("1")) {
			// 可写
			writeAble = "yes";
		}
		return new String [] {writeAble,messTemp[1]};
	}
	
	public String [] chkUPower(String uName, String modID, boolean isStu) {
		String sql = "";
		DAO daoBase = DAO.getInstance();
		if (!isStu) {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhqx a where yhm=? and dyym=?";
		} else {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhzqx a where zdm=? and dyym=?";
			uName = "6727";
		}
		//BEGIN ChenHuamao E-MAIL:chhuma@mail.china.com
		//修改java.lang.NullPointerException
		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID }, new String[]{"dxq","title"});
		return ress;
		//END
	}
	
	public static String [] modelToStrings(String [] colList,Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	//    转换model里的数据到输出数组里
		String [] inputList = new String [colList.length];
		Class myClass = model.getClass();
		for(int i = 0 ;i<colList.length;i++){
			String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
			inputList[i] 	     = DealString.toGBK((String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null));
		}
		return inputList;	
	}

	public static Class formToGBK(Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	//   把form或者model里所有String类型的值转码一次返回页面
		Class myClass = model.getClass();
		Method[] methods=myClass.getMethods();
		for(int i = 0 ;i<methods.length;i++){
			String methodOne = methods[i].getName();
			String methodType = methods[i].getReturnType().getName();
		if(methodOne.length()>3&&methodOne.substring(0, 3).equalsIgnoreCase("get")&&methodType.equalsIgnoreCase("java.lang.String")){
			String setMethod    = "set"+methodOne.substring(3);
			String newValue = DealString.toGBK((String) myClass.getMethod(methodOne,(Class[])null).invoke(model,(Object[]) null));
			myClass.getMethod(setMethod, new Class[]{String.class}).invoke(model, newValue);
		}
	}
	return null;	
	}	
	
	public static StringBuffer makeQuery(String [] colList,Object model) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    传入数组和model，根据传入信息返回查询条件语句
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		Class myClass = model.getClass();
		for(int i = 0 ;i<colList.length;i++){
			String methodName    = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
			String sT = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
			queryTemp 	     = DealString.toGBK(sT);
			if(queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))){
				sb.append(" and ");
				sb.append(colList[i]);
				sb.append(" ='");
				sb.append(queryTemp);
				sb.append("' ");
			}
		}
		return sb;	
	}
	
	
	public void setNjXyZyBjList(HttpServletRequest request,ZgddJxglForm myForm) throws IllegalArgumentException,
		SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    在request保存年级学院专业班级List的方法
		HttpSession session =request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList (userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList (userName));// 发送班级列表
		}
	}
	
	public ArrayList<String[]> commonQuery(String tableName,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		Class myClass = model.getClass();
		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from "+tableName+query.toString(), new String[] {}, "count")));
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select * from (select rownum r,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			sqlBuffer.append(" ) where r > "); 
			sqlBuffer.append(pages.getStart());
			sqlBuffer.append(" and r <= ");
			sqlBuffer.append((pages.getStart() + pages.getPageSize()));
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	public HashMap<String, String> commonQueryOne(String tableName,String [] colList,String pk,String pkValue) {
	    //    通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
			DAO dao = DAO.getInstance();
			HashMap<String, String> map = new HashMap<String, String>();
			int size = colList.length-1;
			if(pkValue.equalsIgnoreCase("")){
				for(int i=0;i<size;i++){
					map.put(colList[i], "");
				}
			}else{
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<size;i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
				}
				sqlBuffer.append(colList[size]);
				sqlBuffer.append(" from ");
				sqlBuffer.append(tableName); 
				sqlBuffer.append(" where ");
				sqlBuffer.append(pk);
				sqlBuffer.append("=?");
				String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
				for(int i=0;i<=size;i++){
					map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
				}
			}
			return map;
	}
	
	public HashMap<String, String> sxjyQueryOne3(String tableName,String [] colList,String pk,
			String pkValue,HashMap<String, String> map,String sql) {
		DAO dao = DAO.getInstance();
    //    通过主键查询单个数据用 获得HashMap<String, String>形式,并覆盖之前传入的HashMap里的值的通用方法
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
		if(pkValue.equalsIgnoreCase("")){
			for(int i=0;i<size;i++){
				map.put(colList[i], "");
			}
		}else{
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<size;i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		}else{
			String [] rsTmp = dao.getOneRs(sql, new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		return map;
	}
}
