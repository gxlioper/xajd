package xgxt.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

/**
 * 该类属于对form.model,request 的公用方法
 */
public class FormModleCommon {
	
	public static void commonRequestSet(HttpServletRequest request, String tableName, 
			String realTable, ArrayList<String[]> rs, List topTr) {
		// Request存值的通用方法 区别是title从数据库里取
		String writeAble  = request.getParameter("writeAble");
		String title      = DealString.toGBK(request.getParameter("title"));
		
		if(Base.isNull(writeAble)) {
			String [] message = getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0] : "";
			title     = message != null && message.length >= 2 ? message[1] : "";
		}
		
		request.setAttribute("rsNum", rs == null ? 0 : rs.size());		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public static String [] getWriteAbleAndTitle(HttpServletRequest request){
		//传入request,返回页面标题（由功能模块名称生成）和读写权，返回的数组第一个值是读写权，第二个是标题
		//使用时页面上要加上name为title的隐藏域
		HttpSession session = request.getSession();
		//得到session
		String userType;
		boolean isStu = true;
		String sUName;
		String sPath;
		String qStr;
		String power;
		String writeAble = "";
		
		userType = (String) session.getAttribute("userType");   //得到用户类型
		isStu = ("stu".equalsIgnoreCase(userType));				 //判断是否是学生用户登录
		sUName = (String) session.getAttribute("userName");	//得到登录用户名
		
		if(!Base.isNull((String)session.getAttribute("type"))){
			String type = (String) session.getAttribute("type");
			if("fdy".equalsIgnoreCase(type)){
				sUName+="_fdy";
			}else if("bzr".equalsIgnoreCase(type)){
				sUName+="_bzr";
			}
		}
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
		if (messTemp != null && ("0").equals(messTemp[0])) {
			
			// 只读
			writeAble = "no";
		} else if (messTemp != null && ("1").equals(messTemp[0])) {
			
			// 可写
			writeAble = "yes";
		}
		String gnmkmc = messTemp != null && messTemp.length >= 3 ? messTemp[2] : "";
		return new String [] {writeAble,messTemp != null && messTemp.length >= 2 ? messTemp[1] : "",gnmkmc};
	}
	
	/**
	 * by like查询
	 * @param request
	 * @return
	 */
	public static String [] getWriteAbleAndTitleTwo(HttpServletRequest request){
		//传入request,返回页面标题（由功能模块名称生成）和读写权，返回的数组第一个值是读写权，第二个是标题
		//使用时页面上要加上name为title的隐藏域
		HttpSession session = request.getSession();
		//得到session
		String userType;
		boolean isStu = true;
		String sUName;
		String sPath;
		String qStr;
		String power;
		String writeAble = "";
		
		userType = session.getAttribute("userType").toString();   //得到用户类型
		isStu = (userType.equalsIgnoreCase("stu"));				 //判断是否是学生用户登录
		sUName = session.getAttribute("userName").toString();	//得到登录用户名
		
		if(!Base.isNull((String)session.getAttribute("type"))){
			String type = session.getAttribute("type").toString();
			if("fdy".equalsIgnoreCase(type)){
				sUName+="_fdy";
			}else if("bzr".equalsIgnoreCase(type)){
				sUName+="_bzr";
			}
		}
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
		
		String messTemp[] = chkUPowerByLike(sUName, power, isStu);
		if (messTemp != null && ("0").equals(messTemp[0])) {
			
			// 只读
			writeAble = "no";
		} else if (messTemp != null && ("1").equals(messTemp[0])) {
			
			// 可写
			writeAble = "yes";
		}
		return new String [] {writeAble,messTemp != null && messTemp.length == 2 ? messTemp[1] : ""};
	}
	
	public static String [] chkUPower(String uName, String modID, boolean isStu) {
		String sql = "";
		DAO daoBase = DAO.getInstance();
		
		if (!isStu) {
			//导航栏有待改为与无限级菜单配套
			sql = "SELECT nvl(dxq,0) dxq,(SELECT replace(WM_CONCAT(GNMKMC),';','-') title FROM (select GNMKMC from GNMKDMB_DJ " +
					"start with GNMKDM = (select GNMKDM from view_yhqx_dj a where a.yhm = ? and a.dyym = ?) " +
					"connect by  gnmkdm = prior FJGNDM ORDER BY GNMKDM)) title,gnmkmc FROM view_yhqx_dj where yhm = ? and dyym = ?";
		} else {
			//导航栏有待改为与无限级菜单配套
			sql = "SELECT nvl(dxq,0) dxq,(SELECT replace(WM_CONCAT(GNMKMC),';','-') title FROM (select GNMKMC from GNMKDMB_DJ " +
					"start with GNMKDM = (select GNMKDM from view_yhzqx_dj a where a.zdm = ? and a.dyym = ?) " +
					"connect by  gnmkdm = prior FJGNDM ORDER BY GNMKDM)) title,gnmkmc FROM view_yhzqx_dj where zdm = ? and dyym = ?";
			uName = "6727";
		}
		
		//修改java.lang.NullPointerException
		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID ,uName,modID }, new String[]{"dxq","title","gnmkmc"});
		return ress;
	}
	
	/**
	 * 采用like 判断
	 * @param uName
	 * @param modID
	 * @param isStu
	 * @return
	 */
	public static String [] chkUPowerByLike(String uName, String modID, boolean isStu) {
		String sql = "";
		DAO daoBase = DAO.getInstance();
		
		if (!isStu) {
			
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||" +
					"(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from" +
					" view_yhqx a where yhm=? and dyym like ?||'%'";
		} else {
			
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||" +
					"(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from" +
					" view_yhzqx a where zdm=? and dyym like ?||'%'";
			uName = "6727";
		}
		
		//修改java.lang.NullPointerException
		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID }, new String[]{"dxq","title"});
		return ress;
	}
	
	public static String[] modelToStrings(String[] colList, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 转换model里的数据到输出数组里
		String[] inputList = new String[colList.length];
		Class myClass = model.getClass();

		for (int i = 0; i < colList.length; i++) {

			String methodName = "get"
					+ colList[i].substring(0, 1).toUpperCase()
					+ colList[i].substring(1);
			inputList[i] = DealString.toGBK((String) myClass.getMethod(
					methodName, (Class[]) null).invoke(model, (Object[]) null));
		}

		return inputList;
	}

	public static Class formToGBK(Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 把form或者model里所有String类型的值转码一次返回页面
		Class myClass = model.getClass();
		Method[] methods = myClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodOne = methods[i].getName();
			String methodType = methods[i].getReturnType().getName();
			if (methodOne.length() > 3
					&& methodOne.substring(0, 3).equalsIgnoreCase("get")
					&& methodType.equalsIgnoreCase("java.lang.String")) {
				String setMethod = "set" + methodOne.substring(3);
				String newValue = DealString.toGBK((String) myClass.getMethod(
						methodOne, (Class[]) null).invoke(model,
						(Object[]) null));
				myClass.getMethod(setMethod, new Class[] { String.class })
						.invoke(model, newValue);
			}
		}
		return null;
	}	
	
	public static StringBuffer makeQuery(String[] colList, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 传入数组和model，根据传入信息返回查询条件语句
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		String queryTemp = "";
		Class myClass = model.getClass();
		for (int i = 0; i < colList.length; i++) {
			String methodName = "get"
					+ colList[i].substring(0, 1).toUpperCase()
					+ colList[i].substring(1);
			String sT = (String) myClass.getMethod(methodName, (Class[]) null)
					.invoke(model, (Object[]) null);
			queryTemp = DealString.toGBK(sT);
			if (queryTemp != null && !("".equalsIgnoreCase(queryTemp.trim()))) {
				sb.append(" and ");
				sb.append(colList[i]);
				sb.append(" ='");
				sb.append(queryTemp);
				sb.append("' ");
			}
		}
		return sb;
	}
	
	
	/**
	 * 获取年级，专业，班级列表
	 * @param request
	 */
	public static void setNjXyZyBjList(HttpServletRequest request) {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String xy = request.getParameter("xydm");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;
		}
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
	}
	/**
	 * 获取年级，专业，班级列表初始状态联动
	 * @param request
	 */
	public static void setCwfpNjXyZyBjList(HttpServletRequest request,String resultSet) {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();;
		String primarykey_checkVal= request.getParameter("primarykey_checkVal");
			
		String xy = request.getParameter("xydm");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp = primarykey_checkVal.split("_");
			
			// 学院结果集
			if("xyli".equalsIgnoreCase(resultSet)){
				xy=temp[0];
				nj=temp[1];
			
				
				// 专业结果集
			}else if("zyli".equalsIgnoreCase(resultSet)){				
				xy=temp[0];
				nj=temp[1];
				zy=temp[2];
				
				// 班级结果集
			}else if("bjli".equalsIgnoreCase(resultSet)){				
				xy=temp[0];
				nj=temp[1];
				zy=temp[2];
				bj=temp[3];
			}
		}
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;
		}
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String zyKey = xy +"!!" + nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjallList());
		request.setAttribute("xyList", Base.getXyallList());
		request.setAttribute("zyList", (Base.getCwfpZyMap()).get(zyKey));
		request.setAttribute("bjList", (Base.getBjallMap()).get(bjKey));
	}
	/**
	 * 获取all年级，专业，班级列表
	 * @param request
	 */
	public static void setAllNjXyZyBjList(HttpServletRequest request) {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String xy = request.getParameter("xydm");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;
		}
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjallList());
		request.setAttribute("xyList", Base.getXyallList());
		request.setAttribute("zyList", (Base.getZyallMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjallMap()).get(bjKey));
	}
	
	/**
	 * 获取年级，专业，班级列表
	 * 
	 * @param request
	 */
	public static void setNjXyZyBjListForFdyBzr(HttpServletRequest request) {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();

		String fdyQx =  session.getAttribute("fdyQx") != null ?session.getAttribute("fdyQx").toString():"";
		String bzrQx =  session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString():"";
		String userType =  session.getAttribute("userType") != null ? session.getAttribute("userType").toString():"";
		String userDep =  session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString():"";
		String userName =  session.getAttribute("userName") != null ?session.getAttribute("userName").toString():"";

		if (Boolean.parseBoolean(fdyQx) && Boolean.parseBoolean(bzrQx)) {
			request.setAttribute("bjList", Fdypd.getFdyBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyBzrZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyBzrXyList(userName));
		} else if (Boolean.parseBoolean(fdyQx)) {
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		} else if (Boolean.parseBoolean(bzrQx)) {
			request.setAttribute("bjList", Fdypd.getBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getBzrZyList(userName));
			request.setAttribute("xyList", Fdypd.getBzrXyList(userName));
		} else {

			String xy = request.getParameter("xydm");
			String zy = request.getParameter("zydm");
			String bj = request.getParameter("bjdm");
			String nj = request.getParameter("nj");
			if ("xy".equalsIgnoreCase(userType)) {
				xy = userDep;
			}

			// 由于使用通用的查询组件,所以这里要特殊处理
			xy = StringUtils.isNull(xy) ? request
					.getParameter("queryequals_xydm") : xy;
			zy = StringUtils.isNull(zy) ? request
					.getParameter("queryequals_zydm") : zy;
			bj = StringUtils.isNull(bj) ? request
					.getParameter("queryequals_bjdm") : bj;
			nj = StringUtils.isNull(nj) ? request
					.getParameter("queryequals_nj") : nj;

			xy = (xy == null) ? "" : xy;
			zy = (zy == null) ? "" : zy;
			bj = (bj == null) ? "" : bj;
			nj = (nj == null) ? "" : nj;
			String bjKey = xy + "!!" + zy + "!!" + nj;
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		}
		request.setAttribute("njList", Base.getNjList());
	}
	
	/**
	 * 获取所有年级，专业，班级列表
	 * 
	 * @param request
	 */
	public static void setAllNjXyZyBjListForFdyBzr(HttpServletRequest request) {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();

		String fdyQx =  session.getAttribute("fdyQx") != null ?session.getAttribute("fdyQx").toString():"";
		String bzrQx =  session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString():"";
		String userType =  session.getAttribute("userType") != null ? session.getAttribute("userType").toString():"";
		String userDep =  session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString():"";
		String userName =  session.getAttribute("userName") != null ?session.getAttribute("userName").toString():"";

		if (Boolean.parseBoolean(fdyQx) && Boolean.parseBoolean(bzrQx)) {
			request.setAttribute("bjList", Fdypd.getFdyBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyBzrZyallList(userName));
			request.setAttribute("xyList", Fdypd.getFdyBzrXyallList(userName));
		} else if (Boolean.parseBoolean(fdyQx)) {
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyallList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyallList(userName));
		} else if (Boolean.parseBoolean(bzrQx)) {
			request.setAttribute("bjList", Fdypd.getBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getBzrZyallList(userName));
			request.setAttribute("xyList", Fdypd.getBzrXyallList(userName));
		} else {

			String xy = request.getParameter("xydm");
			String zy = request.getParameter("zydm");
			String bj = request.getParameter("bjdm");
			String nj = request.getParameter("nj");
			if ("xy".equalsIgnoreCase(userType)) {
				xy = userDep;
			}

			// 由于使用通用的查询组件,所以这里要特殊处理
			xy = StringUtils.isNull(xy) ? request
					.getParameter("queryequals_xydm") : xy;
			zy = StringUtils.isNull(zy) ? request
					.getParameter("queryequals_zydm") : zy;
			bj = StringUtils.isNull(bj) ? request
					.getParameter("queryequals_bjdm") : bj;

			nj = StringUtils.isNull(nj) ? request
					.getParameter("queryequals_nj") : nj;

			xy = (xy == null) ? "" : xy;
			zy = (zy == null) ? "" : zy;
			bj = (bj == null) ? "" : bj;
			nj = (nj == null) ? "" : nj;
			String bjKey = xy + "!!" + zy + "!!" + nj;
			request.setAttribute("xyList", Base.getXyallList());
			request.setAttribute("zyList", (Base.getZyallMap()).get(xy));
			String fplx = request.getParameter("fplx");
			if("fdy".equals(fplx)){
				String sy = request.getParameter("sydm");
				bjKey = sy + "!!www" + nj;
				request.setAttribute("syList",Base.getSyallList());
			}
			request.setAttribute("bjList", (Base.getBjallMap()).get(bjKey));
		}
		request.setAttribute("njList", Base.getNjallList());
	}
	
	/**
	 * 获取年级，专业，班级列表(浙江理工党支部)
	 * 
	 * @author luojw
	 * @param request
	 */
	public static void setNjXyZyBjListForDzb(HttpServletRequest request) {

		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();

		String fdyQx = session.getAttribute("fdyQx") != null ? session.getAttribute("fdyQx").toString():"";
		String bzrQx = session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString():"";
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();

		// ====================2010.9.1 edit by 伟大的luo======================
		// 用户是否支部负责人
		String zbdm = dao.getOneValue("zjlg_zbmc", "zbdm", "zgh", userName);
		boolean isZbfzr = Base.isNull(zbdm) ? false : true;

		if (isZbfzr) {
			List<HashMap<String, String>> zbXyList = Fdypd
					.getZbXyList(userName);
			List<HashMap<String, String>> zbZyList = Fdypd.getZbZyList(
					userName, "%", "%");
			List<HashMap<String, String>> zbBjList = Fdypd.getZbBjList(
					userName, "%", "%", "%");

			List<HashMap<String, String>> jdXyList = Fdypd
					.getFdyBzrXyList(userName);
			List<HashMap<String, String>> jdZyList = Fdypd
					.getFdyBzrZyList(userName);
			List<HashMap<String, String>> jdBjList = Fdypd
					.getFdyBzrbjList(userName);

			List<HashMap<String, String>> fdyXyList = Fdypd
					.getFdyXyList(userName);
			List<HashMap<String, String>> fdyZyList = Fdypd
					.getFdyZyList(userName);
			List<HashMap<String, String>> fdyBjList = Fdypd
					.getFdybjList(userName);

			List<HashMap<String, String>> bzrXyList = Fdypd
					.getBzrXyList(userName);
			List<HashMap<String, String>> bzrZyList = Fdypd
					.getBzrZyList(userName);
			List<HashMap<String, String>> bzrBjList = Fdypd
					.getBzrbjList(userName);

			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			// 辅导员兼班主任
			if (Boolean.parseBoolean(fdyQx) && Boolean.parseBoolean(bzrQx)) {
				xyList = Fdypd.getZbXgList(zbXyList, jdXyList, "xydm");
				zyList = Fdypd.getZbXgList(zbZyList, jdZyList, "zydm");
				bjList = Fdypd.getZbXgList(zbBjList, jdBjList, "bjdm");
			}
			// 辅导员
			else if (Boolean.parseBoolean(fdyQx)) {
				xyList = Fdypd.getZbXgList(zbXyList, fdyXyList, "xydm");
				zyList = Fdypd.getZbXgList(zbZyList, fdyZyList, "zydm");
				bjList = Fdypd.getZbXgList(zbBjList, fdyBjList, "bjdm");
			}
			// 班主任
			else if (Boolean.parseBoolean(bzrQx)) {
				xyList = Fdypd.getZbXgList(zbXyList, bzrXyList, "xydm");
				zyList = Fdypd.getZbXgList(zbZyList, bzrZyList, "zydm");
				bjList = Fdypd.getZbXgList(zbBjList, bzrBjList, "bjdm");
			} else {
				xyList = zbXyList;
				zyList = zbZyList;
				bjList = zbBjList;
			}

			request.setAttribute("xyList", xyList);

			request.setAttribute("zyList", zyList);
			request.setAttribute("bjList", bjList);

		} else {
			if (Boolean.parseBoolean(fdyQx) && Boolean.parseBoolean(bzrQx)) {
				request.setAttribute("bjList", Fdypd.getFdyBzrbjList(userName));
				request.setAttribute("zyList", Fdypd.getFdyBzrZyList(userName));
				request.setAttribute("xyList", Fdypd.getFdyBzrXyList(userName));
			} else if (Boolean.parseBoolean(fdyQx)) {
				request.setAttribute("bjList", Fdypd.getFdybjList(userName));
				request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
				request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			} else if (Boolean.parseBoolean(bzrQx)) {
				request.setAttribute("bjList", Fdypd.getBzrbjList(userName));
				request.setAttribute("zyList", Fdypd.getBzrZyList(userName));
				request.setAttribute("xyList", Fdypd.getBzrXyList(userName));
			} else {

				String xy = request.getParameter("xydm");
				String zy = request.getParameter("zydm");
				String bj = request.getParameter("bjdm");
				String nj = request.getParameter("nj");
				if (userType.equalsIgnoreCase("xy")) {
					xy = userDep;
				}

				// 由于使用通用的查询组件,所以这里要特殊处理
				xy = StringUtils.isNull(xy) ? request
						.getParameter("queryequals_xydm") : xy;
				zy = StringUtils.isNull(zy) ? request
						.getParameter("queryequals_zydm") : zy;
				bj = StringUtils.isNull(bj) ? request
						.getParameter("queryequals_bjdm") : bj;
				nj = StringUtils.isNull(nj) ? request
						.getParameter("queryequals_nj") : nj;

				xy = (xy == null) ? "" : xy;
				zy = (zy == null) ? "" : zy;
				bj = (bj == null) ? "" : bj;
				nj = (nj == null) ? "" : nj;
				String bjKey = xy + "!!" + zy + "!!" + nj;
				request.setAttribute("xyList", Base.getXyList());
				request.setAttribute("zyList", (Base.getZyMap()).get(xy));
				request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
			}
		}
		request.setAttribute("isZbfzr", String.valueOf(isZbfzr));
		request.setAttribute("njList", Base.getNjList());
	}
	
	/**
	 * 获取年级，专业，班级列表
	 * 
	 * @param request
	 */
	public  static void setNjXyZyBjListForFdyBzr(HttpServletRequest request, String xy, String zy, String bj, String nj) {
		//	    在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String fdyQx = session.getAttribute("fdyQx") != null ? session.getAttribute("fdyQx").toString():"";
		String bzrQx = session.getAttribute("bzrQx") != null ?session.getAttribute("bzrQx").toString():"";
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		if(Boolean.parseBoolean(fdyQx)&&Boolean.parseBoolean(bzrQx)){
			request.setAttribute("bjList", Fdypd.getFdyBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyBzrZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyBzrXyList(userName));
		}else if(Boolean.parseBoolean(fdyQx)){
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		}else if(Boolean.parseBoolean(bzrQx)){
			request.setAttribute("bjList", Fdypd.getBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getBzrZyList(userName));
			request.setAttribute("xyList", Fdypd.getBzrXyList(userName));
		}else{
			if (userType.equalsIgnoreCase("xy")) {
				xy = userDep;
			}
			
			xy = (xy == null) ? "" : xy;
			zy = (zy == null) ? "" : zy;
			bj = (bj == null) ? "" : bj;
			nj = (nj == null) ? "" : nj;		
			String bjKey = xy + "!!" + zy + "!!" + nj;	
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		}
		request.setAttribute("njList", Base.getNjList());
	}
	
	/**
	 * 获取年度，学年，学期列表
	 * @param request
	 */
	public static void setNdXnXqList(HttpServletRequest request){
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
	}
	
	/**
	 * 只加载页面控制权限及表头
	 * @param request
	 */
	public static void commonRequestSet(HttpServletRequest request) {
		// Request存值的通用方法 区别是title从数据库里取
		String writeAble  = request.getParameter("writeAble");
		String title      = DealString.toGBK(request.getParameter("title"));
		String gnmkmc      = DealString.toGBK(request.getParameter("gnmkmc"));
		
		if(Base.isNull(writeAble)) {
			String [] message = getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0] : "";
			title     = message != null && message.length >= 2 ? message[1] : "";
			gnmkmc     = message != null && message.length >= 3 ? message[2] : "";
		}
		String czPath = (String) request.getAttribute("czpath");
		String path = (String) request.getAttribute("path");
		if(StringUtils.isNotNull(czPath) && !czPath.equals(path)){
			request.setAttribute("path", czPath);
		}
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("gnmkmc", gnmkmc);
	}
	
	// 通用往request里set列表的方法，传入数组中的String在拼装后通过反射调用RequestSetList的静态方法
	public static void requestSetList(String[] xmNameList,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		for (int i = 0; i < xmNameList.length; i++) {
			String methodName = "set"
					+ xmNameList[i].substring(0, 1).toUpperCase()
					+ xmNameList[i].substring(1) + "List";
			RequestSetList.class.getMethod(methodName,
					new Class[] { HttpServletRequest.class }).invoke(null,
					new Object[] { (Object) request });
		}
	}
	public static String[] getUserType(String userName,String userType){
		DAO dao = DAO.getInstance();
		String sql;
		String adminDep="";
		String[] userMsg =new String[2];
		if (userType.equalsIgnoreCase("teacher")) {
			String szbm = dao.getOneRs(
					"select xm,szbm from yhb where yhm=?",
					new String[] { userName }, "szbm");
			userMsg[0]=szbm;
			sql = "select xgbdm from xtszb where rownum=1";
			adminDep = dao.getOneRs(sql, new String[] {},
					new String[] { "xgbdm" })[0];
			
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] userTmp = dao.getOneRs(sql, new String[] { szbm },
					new String[] { "bmdm", "bmmc", "bmlb" });
			
			
			if (userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			} else if (userTmp[2].equalsIgnoreCase("5")) {
				userType = "xy";
			} else {
				userType = "xx";
			}
		} else {
			userType = "stu";
		}
		userMsg[1]=userType;
		return userMsg;
	}
	
}
