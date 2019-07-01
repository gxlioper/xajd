/*
 * 创建日期 2006-10-18
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForward;

import xgxt.DAO.DAO;
import xgxt.DAO.Bjlh.SynchronizeBaseDataTask;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.CommanForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaDAO;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;

import com.zfsoft.license.LicenseOps;
import common.Globals;

/**
 * @author bat_zzj
 * 
 * 公用类： session超时验证 空字符串检测 用户身份识别 写日志
 * 
 */
public class Base {
	
	public static String currXn;
	
	public static String beforXn;
	
	public static String afterXn;

	public static String currNd;
	
	public static String currYf;
	
	public static String fwqNd;

	public static String currXq;

	public static String sydm;

	public static String xydm;
	
	public static String xxdm;
	
	public static String xxmc;

	public static String sButton; 
	
	private static String endPointUri;
	
	private static String bjlhBaseDataEndPointUri;
	
	private static String jwflag;//教务版本
	
	//webservice的远端服务器用户名与密码
	private static String webserviceUser;
	private static String webservicePass;
	
	public static HashMap<String, String> initProperties = new HashMap<String, String>();
	
	private static String jxjsqxn;
	private static String jxjsqnd;
	private static String jxjsqxq;
	private static String jxjsqxqmc;
	public static String dqxqmc;
	private static String pjzq;//评奖周期
	private static String zczq;//综测周期
	
	protected static List<HashMap<String, String>> njList = null;
	protected static List<HashMap<String, String>> njallList = null;
	private static List<HashMap<String, String>> xnndList = null;
	private static List<HashMap<String, String>> xnndList2 = null;
	private static List<HashMap<String, String>> xqList = null;
	private static List<HashMap<String, String>> yfList = null;

	
	protected static List<HashMap<String, String>> xyList = null;
	public static HashMap<String, List<HashMap<String, String>>> bjMap = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> zyMap = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> cwfpZyMap = new HashMap<String, List<HashMap<String,String>>>();

	protected static List<HashMap<String, String>> xyallList = null;
	protected static List<HashMap<String, String>> syallList = null;
	public static HashMap<String, List<HashMap<String, String>>> bjallMap = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> zyallMap = new HashMap<String, List<HashMap<String,String>>>();
	

	// ====================年级，学院，专业，班级START 显示所有【学籍版追加】=================================
	protected static List<HashMap<String, String>> njNewList = null;
	protected static List<HashMap<String, String>> xyNewList = null;
	protected static HashMap<String, List<HashMap<String, String>>> bjNewList = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> zyNewList = new HashMap<String, List<HashMap<String,String>>>();
	// ====================年级，学院，专业，班级END 显示所有【学籍版追加】=================================
	
	private final static String XN_KEY = "xn";//学年KEY
	private final static String ND_KEY = "nd";//年度KEY 
	private final static String XQ_KEY = "xq";//学期KEY 
	private final static String ZCXN_KEY = "zcxn"; //综测学年KEY
	private final static String ZCXQ_KEY = "zcxq"; //综测学期KEY
	private final static String ZCND_KEY = "zcnd"; //综测年度KEY
	
	//通用化设置
	public static String edition;//版本：new or old
	public static String superSearch;//高级查询：yes or no
	public static String newsQuery;//新闻列表 new or old;
	public static String xsxxwh;//版本：kwh or bkwh
	public static String userLogin;//登陆类型:xs or ls
	public static String YXPZXY_KEY ;
	public static String GYPZYQ_KEY;
	public static String userChange;//<!-- 通用：0 金华职业：1 -->
	
	public static String getUserChange() {
		if (Base.isNull(userChange)) {
			userChange = XMLReader.getFlowControl("comm", "userChange");
			setUserChange(userChange);
		}

		return userChange;
	}

	public static void setUserChange(String userChange) {	
		Base.userChange = userChange;
	}

	/**
	 * 用于性能优化，不进行代码表的关联(在校生)
	 */
/*	public static String xsxxb="( select a.xh,a.xm,(case a.xb when '1' then '男' when '2' then '女' else a.xb end) xb, " +
								"a.nj,a.xydm,a.zydm,a.bjdm from (select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm from bks_xsjbxx a " +
								"where not exists (select 1 from xsxxb b where a.xh = b.xh) "+
							   "union all "+
							   "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm from xsxxb a " +
							   "where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null)) a )";*/
	public static String xsxxb=" ( select a.xh,a.xm,(case a.xb when '1' then '男' when '2' then '女' else a.xb end) xb,a.nj,a.xydm,a.zydm,a.bjdm," +
			"(select c.qxmc from dmk_qx c where c.qxdm = substr(a.syd, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(a.syd, 0, 4) || '00' " +
			"and a.syd <> substr(a.syd, 0, 2) || '0000') sydqmc from view_xsbfxx a ) ";
	
	
	/**
	 * 用于性能优化，不进行代码表的关联
	 */
//	public static String xsxxbAll="( select a.xh,a.xm,(case a.xb when '1' then '男' when '2' then '女' else a.xb end) xb, " +
//								"a.nj,a.xydm,a.zydm,a.bjdm from (select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm from bks_xsjbxx a " +
//								"where not exists (select 1 from xsxxb b where a.xh = b.xh) "+
//							   "union all "+
//							   "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm from xsxxb a) a )";
	//使用新版本学生信息后BKS_XSJBXX不再使用
	public static String xsxxbAll="( select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.yhkh from view_xsxxb a )";
	
	/**
	 * 用于性能优化，不进行代码表的关联(南通职业大学-个性化)-增加了一个入学方式
	 */
	public static String xsxxb_ntzydx="( select a.xh,a.xm,(case a.xb when '1' then '男' when '2' then '女' else a.xb end) xb, " +
								"a.nj,a.xydm,a.zydm,a.bjdm,a.rxfs rxfsdm from (" +
								"select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm,rxfs from bks_xsjbxx a " +
								"where not exists (select 1 from xsxxb b where a.xh = b.xh) "+
							   "union all "+
							   "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,rxfs from xsxxb a " +
							   "where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null)) a )";
	
	private static boolean isHistory = false;

	//public static boolean initalDoneFlag = true; 
	static {//初始化
		//初始化基础数据
		DAO dao = DAO.getInstance();
		
		String sql;
		sql = "select dqxn,dqnd,dqxq,(select xqmc from xqdzb b where a.dqxq=b.xqdm) dqxqmc,nvl(sydm,' ') sydm,nvl(xydm,' ') xydm,jxjsqxn,jxjsqnd,jxjsqxq,(select xqmc from xqdzb b where a.jxjsqxq=b.xqdm) jxjsqxqmc,xxdm,xxmc,jwflag from xtszb a where rownum=1";
		String[] resultArr = dao.getOneRs(sql, new String[]{}, new String[]{"dqxn","dqnd","dqxq","sydm","xydm","jxjsqxn","jxjsqnd","jxjsqxq","xxdm","xxmc", "dqxqmc", "jxjsqxqmc", "jwflag"});
		Base.currXn = resultArr[0];
		String sT = Base.currXn.substring(0,4);
		Base.beforXn = String.valueOf(Integer.parseInt(sT)-1) + "-" + sT;
		String eT = Base.currXn.substring(5, 9);
		Base.afterXn = eT + "-" + String.valueOf(Integer.parseInt(eT)+1);
		Base.currNd = resultArr[1];
		// ========== 当前月份 < ============
		Calendar calendar = Calendar.getInstance();
		String currYf = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if(currYf.length() < 2){
			currYf = "0" + currYf;
		}
		Base.currYf = currYf;
		// ========== 当前月份 > ============
		Base.currXq = resultArr[2];
		Base.sydm = resultArr[3];
		Base.xydm = resultArr[4];
		Base.xxdm = resultArr[8];
		Base.xxmc = resultArr[9];
		//学校代码名称改为从授权文件获取
		/*try {
			//非开发者模式即正式版，学校代码名称改为取licenes数据
			if(LicenseOps.getInstance().isNativeLibLoadSuccess()
					&& LicenseOps.getInstance().isLicenseOpsEnabled()&&!"1".equals(LicenseOps.getInstance().getDevMode())){
				Base.xxmc = new String(new Base64().decode(LicenseOps.getInstance().getAuthUser()),"utf-8");
				Base.xxdm =	LicenseOps.getInstance().getAuthUserCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		Base.dqxqmc = resultArr[10];
		Base.jxjsqxqmc = resultArr[11];
		Base.jwflag = resultArr[12];
//		long t3 = System.currentTimeMillis();
//		System.out.println("查询学年学期："+(t3-t2));
		Base.setJxjsqxn(resultArr[5]);
		Base.setJxjsqnd(resultArr[6]);
		Base.setJxjsqxq(resultArr[7]);
		Base.setXnndList(dao.getXnndList());
		Base.setXnndList2(dao.getXnndList2());
		Base.setNjList(dao.getNjList());
		Base.setNjallList(dao.getNjallList());
		Base.setXyList(dao.getXyList());
		Base.setXyallList(dao.getXyallList());
		Base.setSyallList(dao.getSyallList());
		Base.setXqList(dao.getXqList());
		Base.setYfList(dao.getYfList());
		
		
		Base.fwqNd = dao.getOneRs("select to_char(sysdate,'yyyy') nd from dual", new String[]{}, "nd");
		
		Timer timer = new Timer();
		Timer nextTime = new Timer();
		TTask tTask = new TTask(true);
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//中国地质大学
			QgzxZgdzdxTask qzTask = new QgzxZgdzdxTask();
			timer.schedule(tTask, new Date());
			nextTime.schedule(qzTask, new Date());
		}
		
		//初始化评奖周期及综测周期开始
//		HashMap<String, String> pjzqMap = dao.getMapNotOut("select xn,nd,xq,zcxn,zcnd,zcxq from pjpy_pjzqb", new String[]{});
//		Base.setPjzq(queryPjzq(pjzqMap, XN_KEY, XQ_KEY, ND_KEY));
//		Base.setZczq(queryPjzq(pjzqMap, ZCXN_KEY, ZCXN_KEY, ZCND_KEY));
		//初始化评奖周期及综测周期结束
		
//		long t2 = System.currentTimeMillis();
		
		
//		同步数据操作
		Timer synchronizeTimer = new Timer();
		//是北京联合大学时才进行同步检查操作
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
			SynchronizeBaseDataTask synchronizeTask = new SynchronizeBaseDataTask();
			synchronizeTimer.schedule(synchronizeTask, new Date());
		}		
		
//		long t4 = System.currentTimeMillis();
//		System.out.println("设定学年年度："+(t4-t3));
		String xydm;
//		String zydm;
		//初始化zyMap
		
		/*
		 *  xy = xy==null ? "":xy;
			zy = zy==null ? "":zy;
		    nj = nj==null ? "":nj;
		    String bjKey = xy+"!!"+zy+"!!"+nj;
		 *如果要选择全部的专业，就是当不选学院的时候，map中对应的key值为"!!";
		 *否则对应的key值为相应的学院代码 
		 */
		List<HashMap<String, String>> nullZyList = dao.getZyList("");
		(Base.zyMap).put("", nullZyList);
		for(HashMap<String, String> xy : Base.xyList){
			xydm = xy.get("xydm");
			List<HashMap<String, String>> subZyList = dao.getZyList(xydm);
			(Base.zyMap).put(xydm, subZyList);
		}
		
		List<HashMap<String, String>> allZyList = dao.getZyallList("");
		(Base.zyallMap).put("", allZyList);
		for(HashMap<String, String> xy : Base.xyallList){
			xydm = xy.get("xydm");
			List<HashMap<String, String>> subZyList = dao.getZyallList(xydm);
			(Base.zyallMap).put(xydm, subZyList);
		}
		
//		long t5 = System.currentTimeMillis();
//		System.out.println("设定专业："+(t5-t4));
//		初始化bjMap
		new Thread(new Base.initialBj()).start();
//		long t6 = System.currentTimeMillis();
//		System.out.println("设定班级："+(t6-t5));
		tTask.setFlag(false);
		timer.cancel();
		nextTime.cancel();
//		
//		SearchService search = new SearchService();
//		// 学院
//		List<HashMap<String, Object>> xyTjList = search.getNewXyPxList("","","");
//		// 专业
//		List<HashMap<String, Object>> zyTjList = search.getNewZyPxList(null,"","","");
//		// 班级
//		List<HashMap<String, Object>> bjTjList = search.getNewBjPxList(null,"","","");
//		
//		SearchService.setXyTjList(xyTjList);
//		SearchService.setZyTjList(zyTjList);
//		SearchService.setBjTjList(bjTjList);
		
		//苏州工业园区综合测评定期计算分数排名
		if(Globals.XXDM_SZGYYQZYJSXY.equals(Base.xxdm)){
			new Thread(new SzgyyqJsZhcpFspm()).start();
		}
	}//完成初始化
	public static String getJsxmForzgh(String zgh){
		String sql = "select xm from fdyxxb where zgh=?";
		return DAO.getInstance().getOneRs(sql, new String[]{zgh},"xm");
	}
	/**
	 * 
	 * @描述: 根据学期代码获取学期名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-24 下午02:49:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 */
	public static String getXqmcForXqdm(String xqdm){
		String sql = "select xqdm,xqmc from xqdzb where xqdm=?";
		return DAO.getInstance().getOneRs(sql, new String[]{xqdm},"xqmc");
	}
	public static List<HashMap<String, String>> getNjList() {
		return njList;
	}

	public static void setNjList(List<HashMap<String, String>> lNjList) {
		Base.njList = lNjList;
	}

	public static List<HashMap<String, String>> getNjallList() {
		return njallList;
	}

	public static void setNjallList(List<HashMap<String, String>> NjallList) {
		Base.njallList = NjallList;
	}
	
	public static List<HashMap<String, String>> getXnndList() {
		return xnndList;
	}
	public static void setXnndList(List<HashMap<String, String>> lXnndList) {
		Base.xnndList = lXnndList;
	}
	
	public static List<HashMap<String, String>> getXnndList2() {
		return xnndList2;
	}
	public static void setXnndList2(List<HashMap<String, String>> lXnndList2) {
		Base.xnndList2 = lXnndList2;
	}
	
	public static String getJxjsqnd() {
		return jxjsqnd;
	}

	public static void setJxjsqnd(String sJxjsqnd) {
		jxjsqnd = sJxjsqnd;
	}

	public static String getJxjsqxn() {
		return jxjsqxn;
	}

	public static void setJxjsqxn(String sJxjsqxn) {
		jxjsqxn = sJxjsqxn;
	}

	public static String getJxjsqxq() {
		return jxjsqxq;
	}

	public static void setJxjsqxq(String sJxjsqxq) {
		jxjsqxq = sJxjsqxq;
	}
	
	/** session超时验证 */
	public static int chkTimeOut(HttpSession session) throws Exception {
		Enumeration sessionName = session.getAttributeNames();
		int i = 0;
		try {
			while (sessionName.hasMoreElements()) {
				sessionName.nextElement();
				i += 1;
			}
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
		}
		return i;
	}

	public static ActionForward chkSessionTimeOut(HttpSession session) throws Exception{
		CommanForm tempForm = new CommanForm();
		int i = chkTimeOut(session);
		if(i < 2){
			tempForm.setErrMsg("登陆超时，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
		return null;
	}

	/** 空字符串检测 */
	public static boolean isNull(String str) {
		return ((str == null||"null".equalsIgnoreCase(str)) || str.equalsIgnoreCase(""));
	}

	/** 用户身份识别：-1无权限 0只读权限 1可写权限 */
	public static int chkUPower(String uName, String modID, boolean isStu) {
		String sql = "";
		int power = -1;
		DAO daoBase = DAO.getInstance();
		if (!isStu) {
			sql = "select nvl(dxq,'0') dxq from view_yhqx where yhm=? and dyym=?";
		} else {
			sql = "select nvl(dxq,'0') dxq from view_yhzqx where zdm=? and dyym=?";
			//uName = "0002";
			uName = "6727";//ln update-time2009.1.9
		}
		//BEGIN ChenHuamao E-MAIL:chhuma@mail.china.com
		//修改java.lang.NullPointerException
		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID }, new String[]{"dxq"});
		String res = "";
		if (ress == null) {
			return -1;
		} else {
			res = ress[0];
		}
		//END

		if (!Base.isNull(res) && res.equals("0")) {
			// 只读
			power = 0;
		} else if (!Base.isNull(res) && res.equals("1")) {
			// 可写
			power = 1;
		}
		return power;
	}

	/** 写日志 */
	public static void log(HttpServletRequest request, String msg,
			String userName) {
		DAO dao = new DAO(request.getRemoteAddr());
		try {
			dao.writeLog("", null, null, msg, request);
		} catch (Exception e) {

		}
	}

	/**转换空字符串为指定的字符串，chgTag为1时转码*/
	public static String chgNull(String inStr, String toStr, int chgTag){
		if(chgTag == 1){
			inStr = DealString.toGBK(inStr);
		}	
		if(isNull(inStr)){
			inStr = toStr;
		}
		return inStr;
	}


	/**
	 * @param request 当前
	 * @return 用户读写权
	 */
	public static String getWriteAble(HttpServletRequest request){
		HttpSession session = request.getSession();						//得到session
		String userType = "";
		boolean isStu = true;
		String sUName = "";
		String sPath = "";
		String qStr = "";
		String power = "";
		userType = session.getAttribute("userType") == null ? "" : session.getAttribute("userType").toString();   //得到用户类型
		isStu = (userType.equalsIgnoreCase("stu"));				 //判断是否是学生用户登录
		sUName = session.getAttribute("userName") == null ? "" : session.getAttribute("userName").toString();	//得到登录用户名
		sPath = request.getServletPath().replace("/", "");
		qStr = request.getQueryString();		
		if("".equalsIgnoreCase(qStr) || qStr == null){
			power = sPath;
		}else{
			power = sPath + "?" + qStr;
		}
		String writeAble = (chkUPower(sUName, power, isStu) == 1) ? "yes" : "no";
		return writeAble;
	}

	public static List<HashMap<String, String>> getXyList() {
		return xyList;
	}

	public static void setXyList(List<HashMap<String, String>> lXyList) {
		Base.xyList = lXyList;
	}

	public static List<HashMap<String, String>> getXyallList() {
		return xyallList;
	}

	public static void setXyallList(List<HashMap<String, String>> XyallList) {
		Base.xyallList = XyallList;
	}
	public static List<HashMap<String, String>> getSyallList() {
		return syallList;
	}

	public static void setSyallList(List<HashMap<String, String>> syallList) {
		Base.syallList = syallList;
	}
	public static List<HashMap<String, String>> getXqList() {
		return xqList;
	}

	public static void setXqList(List<HashMap<String, String>> xqList) {
		Base.xqList = xqList;
	}
	
	public static List<HashMap<String, String>> getYfList() {
		return yfList;
	}

	public static void setYfList(List<HashMap<String, String>> yfList) {
		Base.yfList = yfList;
	}

	/**
	 * 调用这个方法获得map之前，先生成如下的key值
	 * String zykey（自己定义变量） = xy==null ? "":xy;
	 * */
	public static HashMap<String, List<HashMap<String, String>>> getZyMap() {
		return zyMap;
	}

	public static void setZyMap(HashMap<String, List<HashMap<String, String>>> zyMap) {
		Base.zyMap = zyMap;
	}

	/**
	 * @return the cwfpZyMap
	 */
	public static HashMap<String, List<HashMap<String, String>>> getCwfpZyMap() {
		return cwfpZyMap;
	}

	/**
	 * @param cwfpZyMap要设置的 cwfpZyMap
	 */
	public static void setCwfpZyMap(HashMap<String, List<HashMap<String, String>>> cwfpZyMap) {
		Base.cwfpZyMap = cwfpZyMap;
	}

	public static HashMap<String, List<HashMap<String, String>>> getZyallMap() {
		return zyallMap;
	}

	public static void setZyallMap(HashMap<String, List<HashMap<String, String>>> zyallMap) {
		Base.zyallMap = zyallMap;
	}
	
	/**
	 *  在使用这个方法获得map前，要如下设定相应的key值，以便取出不同的列表
	 *  xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey （自己定义变量） = xy+"!!"+zy+"!!"+nj;
	 * */
	public static HashMap<String, List<HashMap<String, String>>> getBjMap() {
		return bjMap;
	}

	public static void setBjMap(
			HashMap<String, List<HashMap<String, String>>> bjMap) {
		Base.bjMap = bjMap;
	}
	
	public static HashMap<String, List<HashMap<String, String>>> getBjallMap() {
		return bjallMap;
	}

	public static void setBjallMap(
			HashMap<String, List<HashMap<String, String>>> bjallMap) {
		Base.bjallMap = bjallMap;
	}

	public static String getEndPointUri() {
		return endPointUri;
	}

	public static void setEndPointUri(String endPointUri) {
		Base.endPointUri = endPointUri;
	}

	public static String getBjlhBaseDataEndPointUri() {
		return bjlhBaseDataEndPointUri;
	}

	public static void setBjlhBaseDataEndPointUri(String bjlhBaseDataEndPointUri) {
		Base.bjlhBaseDataEndPointUri = bjlhBaseDataEndPointUri;
	}
	
	public static class initialBj implements Runnable {
		public void run() {
//			初始化bjMap
			/*
			 * 在学院，专业，年级为空时，map中的key就是!!!!;如果只有学院代码,假设学院代码为001，
			 * 则map中的key为001!!!!;以此类推。
			 */
			String xydm;
			String zydm;
			String sydm;
			DAO dao = DAO.getInstance();

			/*
			 *  xy = xy==null ? "":xy;
				zy = zy==null ? "":zy;
			    nj = nj==null ? "":nj;
			    String bjKey = xy+"!!"+zy+"!!"+nj;
			 *如果要选择全部的专业，就是当不选学院的时候，map中对应的key值为"!!";
			 *否则对应的key值为相应的学院代码 
			 */
			List<HashMap<String, String>> nullZyList = dao.getZyList("");
			(Base.zyMap).put("", nullZyList);
			for(HashMap<String, String> xy : Base.xyList){
				xydm = xy.get("xydm");
				List<HashMap<String, String>> subZyList = dao.getZyList(xydm);
				(Base.zyMap).put(xydm, subZyList);
			}
			
			List<HashMap<String, String>> allZyList = dao.getZyallList("");
			(Base.zyallMap).put("", allZyList);
			for(HashMap<String, String> xy : Base.xyallList){
				xydm = xy.get("xydm");
				List<HashMap<String, String>> subZyList = dao.getZyallList(xydm);
				(Base.zyallMap).put(xydm, subZyList);
			}
			
			List<HashMap<String, String>> allBjList = dao.getAllBjList();
			(Base.bjMap).put("!!!!", allBjList);
			
			//床位分配学院专业班级年级联动处理 modify by xiaxia
			
			List<HashMap<String, String>> cwfpZyList = dao.getAllZyNewList();
			(Base.cwfpZyMap).put("!!", cwfpZyList);

			//2018/12/02辅导员编班书院班级相关start
			List<HashMap<String,String>> njBjList = dao.getNjBjForSy();
			String synjkey;
			String syKey;
			//优化下方注释掉的三层循环
			for(HashMap<String,String> njbj : njBjList){
				synjkey = njbj.get("sydm") + "!!www" + njbj.get("nj");
				if((Base.bjallMap).containsKey(synjkey)){
					(Base.bjallMap).get(synjkey).add(njbj);
				} else {
					List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
					list.add(njbj);
					(Base.bjallMap).put(synjkey,list);
				}
				syKey = njbj.get("sydm") + "!!www";
				if((Base.bjallMap).containsKey(syKey)){
					(Base.bjallMap).get(syKey).add(njbj);
				} else {
					List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
					list.add(njbj);
					(Base.bjallMap).put(syKey,list);
				}
			}
			/*for(HashMap<String,String> sy : Base.syallList){
				sydm = sy.get("sydm");
				for(HashMap<String, String> njMap : Base.njallList){
					String nj = njMap.get("nj");
					List<HashMap<String,String>> njbjListForSy = new ArrayList<HashMap<String, String>>();
					for(HashMap<String,String> njbj : njBjList){
						if(sydm.equalsIgnoreCase(njbj.get("sydm"))
								&& nj.equalsIgnoreCase(njbj.get("nj"))){
							njbjListForSy.add(njbj);
						}
					}
					(Base.bjallMap).put(sydm+"!!www"+nj,njbjListForSy);
				}
			}*/
			(Base.bjallMap).put("!!www",njBjList);
			//2018/12/02辅导员编班书院班级相关end
			for (HashMap<String,String> xy : Base.xyallList) {
				//xydm!! 对应的专业列表
				xydm = xy.get("xydm");
				List<HashMap<String,String>> zyList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : cwfpZyList){
					if (xydm.equalsIgnoreCase(map.get("xydm"))){
						zyList.add(map);
					}
				}
				(Base.cwfpZyMap).put(xydm+"!!", zyList);
				
				for(HashMap<String, String> njMap : Base.njallList){
					String nj = njMap.get("nj");
					 List<HashMap<String, String>> zyListForNj = new ArrayList<HashMap<String,String>>();                   
					 List<HashMap<String, String>> zyListForNjXy = new ArrayList<HashMap<String,String>>(); 
					 for (HashMap<String,String> map : cwfpZyList){
							if (nj.equalsIgnoreCase(map.get("nj"))){
								zyListForNj.add(map);
							}
							if (nj.equalsIgnoreCase(map.get("nj"))
									&& xydm.equalsIgnoreCase(map.get("xydm"))){
								zyListForNjXy.add(map);
							}
						}
					 (Base.cwfpZyMap).put("!!"+nj, zyListForNj);
					 (Base.cwfpZyMap).put(xydm+"!!"+nj, zyListForNjXy);
				}
			}
			//--------2011.9.16 优化加载------Penghui.Qu----------------
			for (HashMap<String,String> xy : Base.xyList){
				
				//xydm!!!! 对应的班级列表
				xydm = xy.get("xydm");
				List<HashMap<String,String>> bjList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : allBjList){
					if (xydm.equalsIgnoreCase(map.get("xydm"))){
						bjList.add(map);
					}
				}
				(Base.bjMap).put(xydm+"!!!!", bjList);
				
				
				for(HashMap<String, String> zyMap : Base.zyMap.get(xydm)){
					//xydm!!zydm!! 对应的班级列表
					zydm = zyMap.get("zydm");
					List<HashMap<String, String>> subBjList = new ArrayList<HashMap<String,String>>(); 
					for (HashMap<String,String> map : allBjList){
						if (xydm.equalsIgnoreCase(map.get("xydm")) && zydm.equalsIgnoreCase(map.get("zydm"))){
							subBjList.add(map);
						}
					}
					(Base.bjMap).put(xydm+"!!"+zydm+"!!", subBjList);
					(Base.bjMap).put("!!"+zydm+"!!", subBjList);
					
					for(HashMap<String, String> njMap : Base.njList){
						
						String nj = njMap.get("nj");
						 List<HashMap<String, String>> bjListForNj = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXy = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXyZy = new ArrayList<HashMap<String,String>>(); 
						 for (HashMap<String, String> map : allBjList) {
							
							if (nj.equalsIgnoreCase(map.get("nj"))){
								bjListForNj.add(map);
							} 
							
							if (nj.equalsIgnoreCase(map.get("nj"))
									&& xydm.equalsIgnoreCase(map.get("xydm"))){
								bjListForNjXy.add(map);
							}
							
							if (xydm.equalsIgnoreCase(map.get("xydm"))
									&& zydm.equalsIgnoreCase(map.get("zydm"))
									&& nj.equalsIgnoreCase(map.get("nj"))) {
								bjListForNjXyZy.add(map);
							}
						}
						 //xydm!!zydm!!nj 对应的班级列表
						(Base.bjMap).put(xydm+"!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!zydm!!nj 对应的班级列表
						(Base.bjMap).put("!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!!!nj 对应的班级列表
						(Base.bjMap).put("!!!!"+nj, bjListForNj);
						//xydm!!!!nj  对应的班级列表
						(Base.bjMap).put(xydm+"!!!!"+nj, bjListForNjXy);
					}
				}
				
			}
			
			List<HashMap<String, String>> BjallList = dao.getBjAllList();
			(Base.bjallMap).put("!!!!", BjallList);
			
			
			//--------2011.9.16 优化加载------Penghui.Qu----------------
			for (HashMap<String,String> xy : Base.xyallList){
				
				//xydm!!!! 对应的班级列表
				xydm = xy.get("xydm");
				List<HashMap<String,String>> bjList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : BjallList){
					if (xydm.equalsIgnoreCase(map.get("xydm"))){
						bjList.add(map);
					}
				}
				(Base.bjallMap).put(xydm+"!!!!", bjList);
				
				
				for(HashMap<String, String> zyMap : Base.zyallMap.get(xydm)){
					//xydm!!zydm!! 对应的班级列表
					zydm = zyMap.get("zydm");
					List<HashMap<String, String>> subBjList = new ArrayList<HashMap<String,String>>(); 
					for (HashMap<String,String> map : BjallList){
						if (xydm.equalsIgnoreCase(map.get("xydm")) && zydm.equalsIgnoreCase(map.get("zydm"))){
							subBjList.add(map);
						}
					}
					(Base.bjallMap).put(xydm+"!!"+zydm+"!!", subBjList);
					(Base.bjallMap).put("!!"+zydm+"!!", subBjList);
					
					for(HashMap<String, String> njMap : Base.njallList){
						
						String nj = njMap.get("nj");
						 List<HashMap<String, String>> bjListForNj = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXy = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXyZy = new ArrayList<HashMap<String,String>>(); 
						 for (HashMap<String, String> map : BjallList) {
							
							if (nj.equalsIgnoreCase(map.get("nj"))){
								bjListForNj.add(map);
							} 
							
							if (nj.equalsIgnoreCase(map.get("nj"))
									&& xydm.equalsIgnoreCase(map.get("xydm"))){
								bjListForNjXy.add(map);
							}
							
							if (xydm.equalsIgnoreCase(map.get("xydm"))
									&& zydm.equalsIgnoreCase(map.get("zydm"))
									&& nj.equalsIgnoreCase(map.get("nj"))) {
								bjListForNjXyZy.add(map);
							}
						}
						 //xydm!!zydm!!nj 对应的班级列表
						(Base.bjallMap).put(xydm+"!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!zydm!!nj 对应的班级列表
						(Base.bjallMap).put("!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!!!nj 对应的班级列表
						(Base.bjallMap).put("!!!!"+nj, bjListForNj);
						//xydm!!!!nj  对应的班级列表
						(Base.bjallMap).put(xydm+"!!!!"+nj, bjListForNjXy);
					}
				}
				
			}
			
			
			Base.setXnndList(dao.getXnndList());
			Base.setXnndList2(dao.getXnndList2());
			Base.setNjList(dao.getNjList());
			Base.setNjallList(dao.getNjallList());
			Base.setXyList(dao.getXyList());
			Base.setXyallList(dao.getXyallList());
			Base.setSyallList(dao.getSyallList());
			Base.setXqList(dao.getXqList());
			Base.setYfList(dao.getYfList());
			
			// ====================年级，学院，专业，班级START 显示所有【学籍版追加】=================================
			Base.setNjNewList(dao.getNjNewList());
			Base.setXyNewList(dao.getXyNewList());
//			Base.setZyNewList(dao.getZyNewList(""));
//			Base.setBjNewList(dao.getBjNewList());

			// ====================年级，学院，专业，班级 END 显示所有【学籍版追加】=================================
			
			SearchService search = new SearchService();
			// 学院
			List<HashMap<String, Object>> xyTjList = search.getNewXyPxList("","","","");
			// 专业
			List<HashMap<String, Object>> zyTjList = search.getNewZyPxList(null,"","","","");
			// 班级
			List<HashMap<String, Object>> bjTjList = search.getNewBjPxList(null,"","","","");
			
			SearchService.setXyTjList(xyTjList);
			SearchService.setZyTjList(zyTjList);
			SearchService.setBjTjList(bjTjList);

			// 年级[全]
			List<HashMap<String, String>> njNewTjList = search.getNjNewTjList();
			
			// 学院[全]
			List<HashMap<String, Object>> xyNewTjList = search.getXyNewList("","","");
			// 专业[全]
			List<HashMap<String, Object>> zyNewTjList = search.getZyNewList(null,"","","");
			// 班级[全]
			List<HashMap<String, Object>> bjNewTjList = search.getBjNewList(null,"","","");

			SearchService.setNjNewTjList(njNewTjList);
			SearchService.setXyNewTjList(xyNewTjList);
			SearchService.setZyNewTjList(zyNewTjList);
			SearchService.setBjNewTjList(bjNewTjList);
			
		/*	for(HashMap<String, String> xy : Base.xyList){
				xydm = xy.get("xydm");
				List<HashMap<String, String>> BjList = dao.getBjList(xydm,null);
				(Base.bjMap).put(xydm+"!!!!", BjList);
				for(HashMap<String, String> zy : Base.zyMap.get(xy.get("xydm"))){
					zydm = zy.get("zydm");
					List<HashMap<String, String>> subBjList = dao.getBjList(xydm,zydm);
					(Base.bjMap).put(xydm+"!!"+zydm+"!!", subBjList);
					(Base.bjMap).put("!!"+zydm+"!!", subBjList);
					for(HashMap<String, String> nj : Base.njList){
						 List<HashMap<String, String>> subsubBjList = dao.getBjList(xydm,zydm,nj.get("nj"));
						(Base.bjMap).put(xydm+"!!"+zydm+"!!"+nj.get("nj"), subsubBjList);
						(Base.bjMap).put("!!"+zydm+"!!"+nj.get("nj"), subsubBjList);
						(Base.bjMap).put("!!!!"+nj.get("nj"), dao.getBjList(null, null, nj.get("nj")));
						(Base.bjMap).put(xydm+"!!!!"+nj.get("nj"), dao.getBjList(xydm, null, nj.get("nj")));
					}
				}
			}*/
		}
		
	}
	
	
//	public static String queryPjzq(HashMap<String, String> map, String xnKey,
//			String xqKey, String ndKey) {
//		String result = "";
//		if (map != null) {
//			if ("checked".equalsIgnoreCase(map.get(xqKey))) {
//				result = "xq";
//			} else if ("checked".equalsIgnoreCase(map.get(xnKey))) {
//				result = "xn";
//			} else if ("checked".equalsIgnoreCase(map.get(ndKey))) {
//				result = "nd";
//			} else {
//				System.out.println("评奖周期或综测周期初始化失败.");
//			}
//		}
//		return result;
//	}

	public static HashMap<String, String> getInitProperties() {
		return initProperties;
	}

	public static void setInitProperties(String initName,String initValue) {
		Base.initProperties.put(initName, initValue);
	}

	public static String getDqxqmc() {
		return dqxqmc;
	}

	public static void setDqxqmc(String dqxqmc) {
		Base.dqxqmc = dqxqmc;
	}

	public static String getJxjsqxqmc() {
		return jxjsqxqmc;
	}

	public static void setJxjsqxqmc(String jxjsqxqmc) {
		Base.jxjsqxqmc = jxjsqxqmc;
	}

	public static String getWebservicePass() {
		return webservicePass;
	}

	public static void setWebservicePass(String webservicePass) {
		Base.webservicePass = webservicePass;
	}

	public static String getWebserviceUser() {
		return webserviceUser;
	}

	public static void setWebserviceUser(String webserviceUser) {
		Base.webserviceUser = webserviceUser;
	}

	public static String getJwflag() {
		return jwflag;
	}

	public static void setJwflag(String jwflag) {
		Base.jwflag = jwflag;
	}

	public static String getPjzq() {
		return pjzq;
	}

	public static void setPjzq(String pjzq) {
		Base.pjzq = pjzq;
	}

	public static String getZczq() {
		return zczq;
	}

	public static void setZczq(String zczq) {
		Base.zczq = zczq;
	}

	public static String getSuperSearch() {

		if (Base.isNull(superSearch)) {

			superSearch = XMLReader.getFlowControl("comm", "superSearch");

			setSuperSearch(superSearch);
		}

		return superSearch;
	}

	public static void setSuperSearch(String superSearch) {
		Base.superSearch = superSearch;
	}

	public static String getEdition() {

		if (Base.isNull(edition)) {

			edition = Base.initProperties.get("edition");

			setEdition(edition);
		}

		return edition;
	}

	public static void setEdition(String edition) {
		Base.edition = edition;
	}
	
	public static String getXsxxwh() {

		if (Base.isNull(xsxxwh)) {

			xsxxwh = XMLReader.getFlowControl("xsxx", "xsxxwh");

			setXsxxwh(xsxxwh);
		}

		return xsxxwh;
	}

	public static void setXsxxwh(String xsxxwh) {
		Base.edition = edition;
	}
	
	public static void setUserLogin(String userLogin) {
		Base.userLogin = userLogin;
	}
	
	public static String getUserLogin() {
		if (Base.isNull(userLogin)) {
			
			userLogin = XMLReader.getFlowControl("login", "userLogin");

			setUserLogin(userLogin);
		}

		return userLogin;
	}
	
	public static String getNewsQuery() {
		if (Base.isNull(newsQuery)) {

			edition = Base.initProperties.get("newsQuery");

			setNewsQuery(newsQuery);
		}

		return edition;
	}

	public static void setNewsQuery(String newsQuery) {
		Base.newsQuery = newsQuery;
	}

	public static boolean isHistory() {
		return isHistory;
	}

	public static void setHistory(boolean isHistory) {
		Base.isHistory = isHistory;
	}

	public static List<HashMap<String, String>> getNjNewList() {
		return njNewList;
	}

	public static void setNjNewList(List<HashMap<String, String>> njNewList) {
		Base.njNewList = njNewList;
	}

	public static List<HashMap<String, String>> getXyNewList() {
		return xyNewList;
	}

	public static void setXyNewList(List<HashMap<String, String>> xyNewList) {
		Base.xyNewList = xyNewList;
	}

	public static HashMap<String, List<HashMap<String, String>>> getBjNewList() {
		return bjNewList;
	}

	public static void setBjNewList(
			HashMap<String, List<HashMap<String, String>>> bjNewList) {
		Base.bjNewList = bjNewList;
	}

	public static HashMap<String, List<HashMap<String, String>>> getZyNewList() {
		return zyNewList;
	}

	public static void setZyNewList(
			HashMap<String, List<HashMap<String, String>>> zyNewList) {
		Base.zyNewList = zyNewList;
	}
}

class TTask extends TimerTask{
	private boolean flag;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public TTask(boolean f){
		this.flag = f;
	}
	public void run() {
		try{
			//System.out.println("正在初始化银行，开始数钱啦！！^_^");
			//int i=1;
			while(isFlag()){
				//System.out.print(".");//
				Thread.sleep(1000);
				//i++;
				//if((i=(i%20)) == 1) System.out.println("银行的被抢劫啦，重新来吧！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}			
}

class QgzxZgdzdxTask extends TimerTask{
	DAO dao = DAO.getInstance();
	public void run() {
		try{			
			while(true){
				String sql = "select shjssj, to_char(sysdate,'YYYYMMDDHH24MISS')nowtime from gwsqsjb";
				HashMap<String, String> timeMap = new HashMap<String, String>();
				timeMap = dao.getMap(sql, new String[]{}, new String[]{"shjssj", "nowtime" });
				String shjssj = timeMap.get("shjssj");
				String nowTime = timeMap.get("nowtime");
				if(shjssj != null || "".equalsIgnoreCase(shjssj)){
					if(Double.parseDouble(shjssj)<Double.parseDouble(nowTime)){
						dao.runUpdate("delete from xsgwxxb where xyyj='未审核'",new String[]{});
					}
				}
				Thread.sleep(1000);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

/**
 * 苏州工业园区计算综合测评分数排名
 * @author gaobb
 */
class SzgyyqJsZhcpFspm extends TimerTask{

	@Override
	public void run() {
		 try {
			 while(true){
				 Calendar lastDate = Calendar.getInstance(); 
				 
				 lastDate.add(Calendar.DATE, 1);
				 
//				 lastDate.add(Calendar.MONTH,1);//加一个月    
//				 lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天     
				 lastDate.set(Calendar.AM_PM, 0);
				 lastDate.set(Calendar.HOUR, 0);
				 lastDate.set(Calendar.MINUTE, 0);
				 lastDate.set(Calendar.SECOND, 0);
				 lastDate.set(Calendar.MILLISECOND, 0);
				 
//				 
				 System.out.println("自动计算时间:"+(lastDate.getTimeInMillis()-System.currentTimeMillis()));
				 
				 Thread.sleep(lastDate.getTimeInMillis()-System.currentTimeMillis());//休眠至下月的一号的凌晨

				 new PjpyTeaDAO().jsfspm(new PjpyTeaForm(), null);//开始计算综合测分和排名
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
