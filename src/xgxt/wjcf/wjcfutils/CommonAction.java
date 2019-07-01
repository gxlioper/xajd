
package xgxt.wjcf.wjcfutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.czxx.jxj.JxjService;
import xgxt.pjpy.jgsdx.PjpyJgsdxService;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.gdby.WjcfGdbyService;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import com.zfsoft.basic.BasicAction;
import common.GlobalsVariable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 评奖评优Action 通用的工具类,用于加载列表,存放页面列表等信息
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-31</p>
 */
public class CommonAction extends BasicAction {
	
	protected static final String QUERY = "qry";//页面查询操作判断标志
	
	protected static final String DELETE = "del";//页面删除操作判断标志
	
	protected static final String VIEW = "view";//页面单个显示详细操作判断标志
	
	protected static final String MODIFY = "modi";//页面修改操作判断标志

	protected static final String SAVE = "save";//页面保存操作判断标志
	
	protected static final String AUTOACCOUNT = "auto";//页面自动计算操作判断标志
	
	protected static final String SH_TG = "tg";//审核通过
	
	protected static final String SH_BTG = "btg";//审核不通过
	
	protected static final String SH = "sh";
	
	protected static final String EXPORT = "export";//导出
	
	protected static final String SB_TG = "sb";//上报
	
	protected static final String SB_QX = "qxsb";//取消上报

	/**
	 * 返回用户类型
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserType(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		return session.getAttribute("userType") == null ? "" : session
				.getAttribute("userType").toString();
	} 
	
	/**
	 * 返回用户部门
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserDep(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		return session.getAttribute("userDep") == null ? "" : session
				.getAttribute("userDep").toString();
	}
	
	/**
	 * 公用方法:在REQUEST中存放页面所要加载的LIST属性
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		WjcfGdbyService service = new WjcfGdbyService();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm;
		if (StringUtils.isNull(xy)) {
			xy = "";
		}
		if (StringUtils.isNull(zy)) {
			zy = "";
		}
		njLocal = nj==null ? "": nj;
		if (StringUtils.isNull(njLocal)) {
			njLocal = "";
		}
//		String zyKey = xy==null ? "":xy; 
//		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String userType = request.getSession().getAttribute("userType") == null ? ""
				: request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", service.getZyList(xydm));//专业列表
		request.setAttribute("bjList", service.getBjList(xydm, zydm, nj));//班级列表
		request.setAttribute("xnList2", makeXnndListWithoutNullOption());//学年没有第一行空值列表
		//request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		//request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
		if (Fdypd.isFdy(userName) && flg) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送专业列表
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));// 发送班级列表
		}
	}
	
	//单独加载学年，学期，年度列表
	public void appendXnxqndList(HttpServletRequest request) {
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年,年度列表 key:xn,nd
	}

	private List<HashMap<String, String>> makeXnndListWithoutNullOption() {
		List<HashMap<String, String>> result =  new ArrayList<HashMap<String,String>>();
		result.addAll(Base.getXnndList());
		if(result.size()!=0 && Base.isNull(result.get(0).get("xn"))){
			result.remove(0);
		}
		return result;
	}
	
	/**
	 * 公用方法
	 *    如果用户是辅导员,则只获取所带班级
	 * @param request
	 * @throws Exception
	 */
	public void appendFdybjList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName") == null ? ""
				: session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy") == null ? "" : session
				.getAttribute("isFdy").toString();
		if ("true".equalsIgnoreCase(isFdy)) {
			WjcfGdbyService service = new WjcfGdbyService();
			request.setAttribute("bjList", service.getFdybjList(userName));
		}
	}
	
	/**
	 * 获取处分类别，处分原因列表
	 * @param request
	 * @throws Exception
	 */
	public void appendCflbCfyy(HttpServletRequest request) throws Exception {
		WjcfGdbyService service = new WjcfGdbyService();
		List<HashMap<String, String>> cflbList = service.getCflbList();//获取处分类别列表
		List<HashMap<String, String>> cfyyList = service.getCfyyList();//获取处分原因列表
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("cfyyList", cfyyList);
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @param topList
	 * @param resList
	 * @throws Exception
	 */
	public void appendResult(HttpServletRequest request,
			List<HashMap<String, String>> topList, List<String[]> resList)
			throws Exception {
		request.setAttribute("topTr", topList);// 表头
		request.setAttribute("rs", resList);// 结果
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);// 记录数
	}
	
	/**
	 * 加载主键
	 * @param request
	 * @param pkValue
	 * @throws Exception
	 */
	public void appendPkValue(HttpServletRequest request, String pkValue)
			throws Exception {
		request.setAttribute("pkValue", pkValue);
	}
	
	/**
	 * 审核时显示详细信息
	 * @param request
	 * @param resMap
	 * @throws Exception
	 */
	public void appendViewMap(HttpServletRequest request,
			HashMap<String, String> resMap) throws Exception {
		request.setAttribute("rs", resMap);
	}
	
	/**
	 * 获取审核列表:通过,不通过,未审核
	 * @param request
	 * @throws Exception
	 */
	public void appendChkList(HttpServletRequest request) throws Exception {
		WjcfGdbyService service = new WjcfGdbyService();
		List<HashMap<String, String>> chkList = service.getChkList(3);
		request.setAttribute("chkList", chkList);
	}
	
	/**
	 * 获取奖学金列表
	 * @param request
	 * @throws Exception
	 */
	public void appendJxjList(HttpServletRequest request) throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> jxjList = service.getJxjList();
		request.setAttribute("jxjList", jxjList);
	} 
	
	/**
	 * 获取荣誉称号列表
	 * @param request
	 * @throws Exception
	 */
	public void appendRychList(HttpServletRequest request) throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> rychList = service.getRychList();
		request.setAttribute("rychList", rychList);
	}
	
	/**
	 * 查询结果
	 * @param request
	 * @param titList
	 * @param rsList
	 * @throws Exception
	 */
	public void appendQryResult(HttpServletRequest request,
			List<HashMap<String, String>> titList, List<String[]> rsList){
		request.setAttribute("topTr", titList);//表头
		request.setAttribute("rs", rsList);//结果
		request.setAttribute("rsNum", rsList==null ? 0 : rsList.size());//记录数
	}
	
	/**
	 * 存入表名和视图名
	 * @param request
	 * @param tableName
	 * @param realTable
	 * @throws Exception
	 */
	public void appendTableXx(HttpServletRequest request, String tableName,
			String realTable) {
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		return service.getStuInfo(xh);
	}
	
	/**
	 * 加载奖学金申请学年，学期，年度
	 * @param request
	 */
	public void appendPjxnxqnd(HttpServletRequest request) {
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xqmc", Base.getJxjsqxqmc());
	}
	
	/**
	 * 加载军训奖项代码列表
	 * @param request
	 */
	public void appendJxjxdmList(HttpServletRequest request) throws Exception{
		WjcfGdbyService service = new WjcfGdbyService();
		request.setAttribute("jxjxdmList", service.getJxjxdmList());
	}
	
	public void commonRequestSet(HttpServletRequest request, String tableName,
			String realTable, ArrayList<String[]> rs, List topTr) {
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
		if (messTemp == null 
				||(!Base.isNull(messTemp[0]) && messTemp[0].equals("0"))) {
			// 只读
			writeAble = "no";
		} else if (!Base.isNull(messTemp[0]) && messTemp[0].equals("1")) {
			// 可写
			writeAble = "yes";
		}
		return new String [] {writeAble,messTemp != null && messTemp.length>0 ? messTemp[1] : null};
	}
	
	public String [] chkUPower(String uName, String modID, boolean isStu) {
		String sql = "";
		DAO daoBase = DAO.getInstance();
		if (!isStu) {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhqx a where yhm=? and dyym=?";
		} else {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhzqx a where zdm=? and dyym=?";
			uName = "6727";
		}

		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID }, new String[]{"dxq","title"});
		return ress;
	}
	
	public void appendOperResult(HttpServletRequest request, boolean result) {
		String res = result ? "yes" : "no";
		request.setAttribute("inserted", res);
	}
	
	public void appendOperQx(HttpServletRequest request, String path) {
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
	}
	
	/**
	 * 查询学生处分信息
	 * @param request
	 * @param paramMap
	 */
	public void setStuWjcfxx(HttpServletRequest request, HashMap<String, String> paramMap) {
		JxjService service = new JxjService();
		request.setAttribute("cfList", service.queryStuWjcfxx(paramMap));
	}
	
	/**
	 * 查询学生成绩信息
	 * @param request
	 * @param paramMap
	 */
	public void setStuKccjxx(HttpServletRequest request, HashMap<String, String> paramMap) {
		JxjService service = new JxjService();
		request.setAttribute("cjList", service.queryStuKccjxx(paramMap));
	}
	
	/**
	 * 获取SESSION对象
	 * @param request
	 * @return
	 */
	public HttpSession getSession(HttpServletRequest request) {
		return request.getSession(); 
	}
	
	/**
	 * 传入SESSION里面的某个属性名称，获里属性值
	 * @param attrName
	 * @return
	 */
	public String getSessionAttributeValue(HttpServletRequest request,
			String attrName) {
		HttpSession sessiones = getSession(request);
		return sessiones == null ? ""
				: (sessiones.getAttribute(attrName) != null ? sessiones
						.getAttribute(attrName).toString() : "");
	}
	
	/**
	 * 获取审核结果字符串
	 * @param request
	 * @return
	 */
	public String getShjgString(HttpServletRequest request) {
		String jg = request.getParameter("jg");
		return SH_TG.equalsIgnoreCase(jg) ? "通过"
				: (SH_BTG.equalsIgnoreCase(jg) ? "不通过" : "未审核");
	}
	
	public void appendOperResultMes(HttpServletRequest request, boolean result) {
		request.setAttribute("result", result);
	}
	
	/**
	 * 开关控制，关闭转到提示信息页面
	 * @param request
	 * @param gndm
	 * */	
	public ActionForward controlOnOffToPage(HttpServletRequest request,String gndm){
		User user = getUser(request);
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyTyService tyService = new PjpyZjkjxyService();
		
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType())
				&&!xtwhService.gnkgFlag(gndm)
				&& tyService.checkKgflag()){
			String msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return null;
		}
	}
}
