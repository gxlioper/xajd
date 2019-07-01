package xgxt.comm.search;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CnToEnUtil;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.xsztz.xmsbgl.glygl.GlyglDao;
import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 高级查询_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SearchService extends CommService {

	SearchDAO dao = new SearchDAO();
	
	// 拼音首字母
	String[] pyszm = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l",
			"m", "n", "o", "p", "q", "r", "s", "t", "w", "x", "y", "z" };

	public static final String[] PY_BIG = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "-" };

	// 学院列表
	private static List<HashMap<String, Object>> xyTjList = new ArrayList<HashMap<String, Object>>();

	// 专业列表(拼音)
	private static List<HashMap<String, String>> zyPyList = new ArrayList<HashMap<String, String>>();

	// 班级列表(拼音)
	private static List<HashMap<String, String>> bjPyList = new ArrayList<HashMap<String, String>>();

	// 专业列表
	private static List<HashMap<String, Object>> zyTjList = new ArrayList<HashMap<String, Object>>();

	// 班级列表
	private static List<HashMap<String, Object>> bjTjList = new ArrayList<HashMap<String, Object>>();

	//--------------------------------学籍异动版-----START---------------------------------------------------
	// 年级列表
	private static List<HashMap<String, String>> njNewTjList = new ArrayList<HashMap<String, String>>();
	// 学院列表
	private static List<HashMap<String, Object>> xyNewTjList = new ArrayList<HashMap<String, Object>>();
	// 专业列表
	private static List<HashMap<String, Object>> zyNewTjList = new ArrayList<HashMap<String, Object>>();
	// 班级列表
	private static List<HashMap<String, Object>> bjNewTjList = new ArrayList<HashMap<String, Object>>();
	//--------------------------------学籍异动版-----END---------------------------------------------------
	
	/**
	 * 获得功能模块需要的查询条件
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getSearchInfo(SearchForm model, User user) {

		List<HashMap<String, String>> list = getSearchTjList(model, user);

		HashMap<String, String> map = new HashMap<String, String>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> rs = list.get(i);
				String tj = rs.get("tj");

				map.put(tj, "yes");
			}
		}

		return map;
	}

	/**
	 * 获得功能模块需要的查询条件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSearchTjList(SearchForm model,User user) {

		// 保存表
		String tableName = "xg_search_szb";
		// 主键
		String pk = "path";
		// 主键值
		String pkValue = model.getPath();
		// 输出字段
		String[] colList = new String[] { "tj", "mc", "lx", "ssmk", "zd","gshlx" };

		String query = " where 1 = 1 ";
		if (!Base.isNull(pkValue)) {
			query += " and " + pk + " = '" + pkValue + "'";
		}
		
		//用户类型
		String userType = user.getUserType();
		
		if("stu".equalsIgnoreCase(userType)){
			query += " and xszd = 'yes' ";
		}
		
		query += " order by to_number(xssx) ";

		return getRsList(tableName, query, new String[] {}, colList, "");
	}

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void setCommOptionList(SearchForm searchForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, searchForm);
		setList(model, rForm, request);
	}

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setSearchTj(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {
		
		// 所属模块
		String ssmk = searchForm.getSsmk();
		// 访问路径
		String path = searchForm.getPath();
		// 设置通用列表
		setCommOptionList(searchForm, rForm, request);
		
		// 设置年级学院专业班级列表
		setNjXyZyBjList(searchForm, rForm, user, request);

		// 设置年级学院专业班级列表【全】[学籍异动版]
		setNjXyZyBjNewList(searchForm, rForm, user, request);
		
		SearchTj searchTj = new SearchTj();
		
		searchTj.setCommListList(searchForm, rForm, user, PY_BIG, request);

		if ("xsxx".equalsIgnoreCase(ssmk)) {// 学生信息
			// 设置学生信息相关列表
			searchTj.setXsxxListList(searchForm, rForm, user, request);
		} else if ("rcsw".equalsIgnoreCase(ssmk)) {// 日常事务
			// 设置日常事务相关列表
			searchTj.setRcswListList(searchForm, rForm, user, request);
		} else if ("gygl".equalsIgnoreCase(ssmk)) {// 公寓管理
			// 设置公寓管理相关列表
			searchTj.setGyglListList(searchForm, rForm, user, PY_BIG, request);
		} else if ("gygl_third".equalsIgnoreCase(ssmk)) {// 公寓管理_第三版
			// 设置公寓管理(第三版)相关列表
			searchTj.setGyglListList_Third(searchForm, rForm, user, PY_BIG, request);
		} else if ("pjpy".equalsIgnoreCase(ssmk)) {// 评奖评优
			// 设置评奖评优相关列表
			searchTj.setPjpyListList(searchForm, rForm, user, request);
		} 
	}

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setNjXyZyBjList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		String sfzxs = "";
		
		// 用户身份
		String userStatus = "";
		// 用户名
		String userName = user.getUserName();
		// 用户部门
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // 使用用户角色
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// 用户角色
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// 用户身份
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}
	
		// 获得特殊的条件
		//getEspecialTj(searchForm, request);

		String path = searchForm.getPath();
		if(path.equals("xsxx_xsxxgl_cxfzxs.do")){
			sfzxs="0";
		}
		if(path.equals("xsxx_xsxxgl_cxzxs.do")){
			sfzxs="1";
		}
		
		boolean isHistory = false;
		
		// 历史学生信息  TODO 重新点击菜单后 isHistory值会被重置 判断失效
		if ("stu_info_query.do?method=stuInfo&searchType=lsxx"
				.equalsIgnoreCase(path)
				|| "stu_info_query.do?method=stuInfo&sfzxk=no"
						.equalsIgnoreCase(path)
				|| "stu_info_query.do?method=stuInfo&sfyby=yes"
						.equalsIgnoreCase(path)
				|| "xsxx_tygl_cxfzxs.do".equalsIgnoreCase(path)
				|| "xjyd_xjydjg.do".equalsIgnoreCase(path)
				|| "xjyd_bycl.do".equalsIgnoreCase(path)
				|| "gyglnew_cwfpgl_cwfp.do".equalsIgnoreCase(path)
				|| "szdw_general_szbb.do".equalsIgnoreCase(path)
				|| "pjpy_tjcx_zcbjmd_djks.do".equalsIgnoreCase(path)) {
			isHistory = true;
			session.setAttribute("userNjList", null);
			session.setAttribute("userXyList", null);
			session.setAttribute("userZyPyList", null);
			session.setAttribute("userZyList", null);
			session.setAttribute("userBjList", null);
		} else {
			isHistory = false;
		}

		Base.setHistory(isHistory);
		
		// 学校用户（管理员）
		if ("xx".equalsIgnoreCase(userStatus)) {

			// 年级列表
			List<HashMap<String, String>> njTjList = getNewNjList(userStatus,
					userName, userDep,sfzxs);
			
			searchForm.setNjTjList(njTjList);
			request.setAttribute("njTjList", njTjList);

			
				xyTjList = getNewXyPxList("", "", "",sfzxs);
			
			searchForm.setXyTjList(xyTjList);
			request.setAttribute("xyTjList", xyTjList);

			
				zyTjList = getNewZyPxList(null, "", "", "",sfzxs);
			
			searchForm.setZyTjList(zyTjList);
			request.setAttribute("zyTjList", zyTjList);

				bjTjList = getNewBjPxList(null, "", "", "",sfzxs);
			
			searchForm.setBjTjList(bjTjList);
			request.setAttribute("bjTjList", bjTjList);

		} else {

			// session中的用户年级列表
			List<HashMap<String, String>> userNjList = (List<HashMap<String, String>>) session
					.getAttribute("userNjList");

			if (userNjList != null && userNjList.size() > 0) {
				request.setAttribute("njTjList", userNjList);
			} else {
				userNjList = getNewNjList(userStatus, userName, userDep,sfzxs);
				session.setAttribute("userNjList", userNjList);
				request.setAttribute("njTjList", userNjList);
			}
			searchForm.setNjTjList(userNjList);
			
			// session中的用户学院列表
			List<HashMap<String, Object>> userXyList = (List<HashMap<String, Object>>) session
					.getAttribute("userXyList");

			if (userXyList != null && userXyList.size() > 0) {
				request.setAttribute("xyTjList", userXyList);
			} else {
				userXyList = getNewXyPxList(userStatus, userName, userDep,sfzxs);
				session.setAttribute("userXyList", userXyList);
				request.setAttribute("xyTjList", userXyList);
			}
			searchForm.setXyTjList(userXyList);
			
			// session中的用户专业列表
			List<HashMap<String, Object>> userZyList = (List<HashMap<String, Object>>) session
					.getAttribute("userZyList");
			// session中的专业列表(带拼音)
			List<HashMap<String, String>> userZyPyList = (List<HashMap<String, String>>) session
					.getAttribute("userZyPyList");

			if (userZyList != null && userZyList.size() > 0) {
				request.setAttribute("zyTjList", userZyList);
			} else {

				// 专业列表（带拼音）
				userZyPyList = getNewZyList(userStatus, userName, userDep, null,sfzxs);
				userZyList = getNewZyPxList(userZyPyList, userStatus, userName,
						userDep,sfzxs);

				session.setAttribute("userZyPyList", userZyPyList);
				session.setAttribute("userZyList", userZyList);
				request.setAttribute("zyTjList", userZyList);
			}
			searchForm.setZyTjList(userZyList);
			
			// session中的用户班级列表
			List<HashMap<String, Object>> userBjList = (List<HashMap<String, Object>>) session
					.getAttribute("userBjList");
			// session中的班级列表(带拼音)
			List<HashMap<String, String>> userBjPyList = (List<HashMap<String, String>>) session
					.getAttribute("userBjPyList");

			if (userBjList != null && userBjList.size() > 0) {
				request.setAttribute("bjTjList", userBjList);
			} else {
				userBjPyList = getNewBjList(userStatus, userName, userDep,sfzxs);
				userBjList = getNewBjPxList(userBjPyList, userStatus, userName,
						userDep,sfzxs);
				
				session.setAttribute("userBjList", userBjList);
				request.setAttribute("bjTjList", userBjList);
			}
			
			searchForm.setBjTjList(userBjList);
			request.setAttribute("bjTjList", userBjList);
		}
	}
	
	/**
	 * 获得新年级列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getNewNjList(String userStatus,
			String userName, String userDep,String sfzxs) {
		return dao.getNewNjList(userStatus, userName, userDep,sfzxs);
	}
	/**
	 * 
	 * @描述:根据园区获取学院
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-13 下午04:37:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getNewNjListByYq(String userStatus,
			String userName, String userDep,String[] xq,String[] yq) {
		return dao.getNewNjListByYq(userStatus, userName, userDep, xq, yq);
	}

	/**
	 * 获得新学院列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getNewXyList(String userStatus,
			String userName, String userDep,String sfzxs) {
		
		List<HashMap<String, String>> xyList = dao.getXyList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("需要配置的生僻字部门：" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}
	
	public List<HashMap<String, String>> getNewXyListByYq(String userStatus,
			String userName, String userDep,String[] xq,String[] yq) {
		
		List<HashMap<String, String>> xyList = dao.getXyListByYq(userStatus,
				userName, userDep,xq,yq);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("需要配置的生僻字部门：" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}

	/**
	 * 获得新专业列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getNewZyList(String userStatus,
			String userName, String userDep, String[] nj,String sfzxs) {

		List<HashMap<String, String>> zyList = dao.getZyList(userStatus,
				userName, userDep, nj,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < zyList.size(); i++) {

			HashMap<String, String> map = zyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String zydm = map.get("zydm");
			String zymc = map.get("zymc");
			String zypy = CnToEnUtil.getFirstLetter(zymc);
			
			if("-".equalsIgnoreCase(zypy)){
				zypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", zydm);
				zypy = Base.isNull(zypy) ? "-" : zypy;
				System.out.println("需要配置的生僻字部门：" + zymc + "(" + zydm + ")");
			}
			
			HashMap<String, String> zyMap = new HashMap<String, String>();
			zyMap.put("zypy", zypy.toUpperCase());
			zyMap.put("xydm", xydm);
			zyMap.put("xymc", xymc);
			zyMap.put("zydm", zydm);
			zyMap.put("zymc", zymc);

			list.add(zyMap);
		}

		return list;
	}

	/**
	 * 获得新班级列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getNewBjList(String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, String>> bjList = dao.getBjList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < bjList.size(); i++) {

			HashMap<String, String> map = bjList.get(i);

			String xydm = map.get("xydm");// 学院代码
			String xymc = map.get("xymc");// 学院名称
			String zydm = map.get("zydm");// 专业代码
			String zymc = map.get("zymc");// 专业名称
			String nj = map.get("nj");// 年级
			String bjdm = map.get("bjdm");// 班级代码
			String bjmc = map.get("bjmc");// 班级名称
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// 班级拼音
			
			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("需要配置的生僻字部门：" + bjmc + "(" + bjdm + ")");
			}
			
			HashMap<String, String> bjMap = new HashMap<String, String>();
			bjMap.put("bjpy", bjpy.toUpperCase());
			bjMap.put("xydm", xydm);
			bjMap.put("xymc", xymc);
			bjMap.put("zydm", zydm);
			bjMap.put("zymc", zymc);
			bjMap.put("nj", nj);
			bjMap.put("bjdm", bjdm);
			bjMap.put("bjmc", bjmc);

			list.add(bjMap);
		}

		return list;
	}

	/**
	 * 获得学院列表(根据拼音)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, Object>> getNewXyPxList(String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		List<HashMap<String, String>> newXylist = getNewXyList(userStatus,
				userName, userDep,sfzxs);

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newXylist.size(); j++) {

				HashMap<String, String> map = newXylist.get(j);

				String xypy = map.get("xypy");
				String xydm = map.get("xydm");
				String xymc = map.get("xymc");

				if (py.equalsIgnoreCase(xypy)) {

					HashMap<String, String> newXy = new HashMap<String, String>();
					newXy.put("xydm", xydm);
					newXy.put("xymc", xymc);
					xyList.add(newXy);

				}
			}

			HashMap<String, Object> xyMap = new HashMap<String, Object>();
			xyMap.put("py", py);
			xyMap.put("xyList", xyList);

			list.add(xyMap);
		}

		return list;

	}

	/**
	 * 获得专业列表(根据拼音)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, Object>> getNewZyPxList(
			List<HashMap<String, String>> userZyPyList, String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 专业列表（带拼音）
		List<HashMap<String, String>> newZylist = null;

		if (userZyPyList != null && userZyPyList.size() > 0) {
			newZylist = userZyPyList;
		} else {
			newZylist = getNewZyList(userStatus, userName, userDep, null,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newZylist.size(); j++) {

				HashMap<String, String> map = newZylist.get(j);

				String zypy = map.get("zypy");
				String zydm = map.get("zydm");
				String zymc = map.get("zymc");

				if (py.equalsIgnoreCase(zypy)) {

					HashMap<String, String> newZy = new HashMap<String, String>();

					if (zyList.size() < 3) {

						newZy.put("zydm", zydm);
						newZy.put("zymc", zymc);

						zyList.add(newZy);

					} else {
						newZy.put("zydm", "zy_" + zypy);
						newZy.put("zymc", "其他");

						zyList.add(newZy);

						break;
					}

				}
			}

			HashMap<String, Object> zyMap = new HashMap<String, Object>();
			zyMap.put("py", py);
			zyMap.put("zyList", zyList);

			list.add(zyMap);
		}

		return list;

	}

	/**
	 * 获得专业信息（根据拼音）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getZyInfoByPy(String py,
			String userStatus, String userName, String userDep,String sfzxs) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getNewZyList(userStatus,
				userName, userDep, null,sfzxs);

		for (int i = 0; i < zyPyList.size(); i++) {

			HashMap<String, String> zyMap = zyPyList.get(i);

			String zypy = zyMap.get("zypy");
			String zydm = zyMap.get("zydm");
			String zymc = zyMap.get("zymc");

			if (zypy.equalsIgnoreCase(py)) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zydm", zydm);
				map.put("zymc", zymc);

				zyList.add(map);

			}
		}

		return zyList;
	}

	/**
	 * 获得专业信息（根据学院）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getZyInfoByXy(String[] nj,
			String[] xy, String userStatus, String userName, String userDep,String sfzxs) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getNewZyList(userStatus,
				userName, userDep, nj,sfzxs);

		if (xy != null && xy.length > 0) {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xydm = zyMap.get("xydm");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < xy.length; j++) {

					if (xy[j].equalsIgnoreCase(xydm)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		} else {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < PY_BIG.length; j++) {

					if (PY_BIG[j].equalsIgnoreCase(zypy)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		}

		return zyList;
	}

	/**
	 * 获得班级列表(根据拼音)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, Object>> getNewBjPxList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getNewBjList(userStatus, userName, userDep,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "其他");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}

	/**
	 * 获得班级信息（根据拼音，年级，学院，专业）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getBjInfoByTj(String py, String[] nj,
			String[] xy, String[] zy, String userStatus, String userName,
			String userDep,String sfzxs) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> bjPyList = getZybjPyList(userStatus,
				userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// 班级拼音
			String bjdm = bjMap.get("bjdm");// 班级代码
			String bjmc = bjMap.get("bjmc");// 班级名称

			String njdm = bjMap.get("nj");// 年级
			String xydm = bjMap.get("xydm");// 学院
			String zydm = bjMap.get("zydm");// 专业

			// 判断拼音是否符合
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// 判断年级是否符合
			boolean njFlag = true;

			if (nj != null && nj.length > 0) {

				njFlag = false;

				for (int j = 0; j < nj.length; j++) {
					if (njdm.equalsIgnoreCase(nj[j])) {
						njFlag = true;
						break;
					}
				}
			}

			// 判断学院是否符合
			boolean xyFlag = true;

			if (xy != null && xy.length > 0) {

				xyFlag = false;

				for (int j = 0; j < xy.length; j++) {
					if (xydm.equalsIgnoreCase(xy[j])) {
						xyFlag = true;
						break;
					}
				}
			}

			// 判断专业是否符合
			boolean zyFlag = true;

			if (zy != null && zy.length > 0) {

				zyFlag = false;

				for (int j = 0; j < zy.length; j++) {
					if (zydm.equalsIgnoreCase(zy[j])) {
						zyFlag = true;
						break;
					}
				}
			}

			if (pyFlag && njFlag && xyFlag && zyFlag) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}

	/**
	 * 高级查询获取行政班级
	 * @param py
	 * @param sy
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @param sfzxs
	 * @return
	 */
	public List<HashMap<String, String>> getXzbjInfoByTj(String py, String[] sy, String userStatus, String userName,
													   String userDep,String sfzxs) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> bjPyList = getXzbjPyList(userStatus,userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// 班级拼音
			String bjdm = bjMap.get("bjdm");// 班级代码
			String bjmc = bjMap.get("bjmc");// 班级名称

			String sydm = bjMap.get("sydm");// 书院

			// 判断拼音是否符合
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// 判断书院是否符合
			boolean syFlag = true;

			if (sy != null && sy.length > 0) {

				syFlag = false;

				for (int j = 0; j < sy.length; j++) {
					if (sydm.equalsIgnoreCase(sy[j])) {
						syFlag = true;
						break;
					}
				}
			}

			if (pyFlag && syFlag ) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}

	/**
	 * 获得公寓管理相关信息
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getGyglInfo(String lx, String[] xqdm,
			String[] yqdm, String[] lddm, String[] cs, String gyglyQx,
			String userName, String userDep) {

		List<HashMap<String, String>> list = null;

		String tableName = "";
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		ArrayList<String> inPutList = new ArrayList<String>();
		String[] colList = null;

		// 园区
		if ("yq".equalsIgnoreCase(lx)) {

			tableName = "yqdmb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from sslddmb a,xg_gygl_ldglb b ");
				query.append("where yqdmb.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by yqdm ");
			
			colList = new String[] { "yqdm", "yqmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					"");
		}
		// 楼栋
		else if ("ld".equalsIgnoreCase(lx)) {

			tableName = "sslddmb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where sslddmb.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by lddm ");
			
			colList = new String[] { "lddm", "ldmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
			"");
		}
		//	层数
		else if ("cs".equalsIgnoreCase(lx)) {

			String sql = "select distinct cs from view_ssxx ";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_ssxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by to_number(cs) ");
			
			sql+=query;
			
			colList = new String[] { "cs", "cs" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);
		}
		//寝室号
		else if ("qsh".equalsIgnoreCase(lx)) {

			String sql = "select distinct qsh from view_ssxx ";
			
			//校区
			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			//园区
			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			//	楼栋
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			// 层数
			if (cs != null && cs.length > 0) {
				query.append(" and (");
				for (int i = 0; i < cs.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" cs = ? ");

					inPutList.add(cs[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_ssxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by qsh ");
			
			sql+=query;
			
			colList = new String[] { "qsh"};

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				list.get(i).put("qshpy", qshpy);
				list.get(i).put("qshdm", qsh);
				list.get(i).put("qshmc", qsh);
			}
		}
		// 楼栋(剩余寝室数)
		else if ("ldqs".equalsIgnoreCase(lx)) {
			list = dao.getLdForWfpQssList(xqdm, yqdm, gyglyQx, userName,
					userDep);
		}
		return list;
	}
	
	/**
	 * 获得寝室信息（根据拼音）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getQsInfoByPy(String py,
			String[] xqdm, String[] yqdm, String[] lddm, String[] cs,
			String userStatus, String userName, String userDep) {

		// 获得寝室列表
		List<HashMap<String, String>> qshList = dao.getQsList(xqdm, yqdm, lddm,
				cs, userStatus, userName, userDep);

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = CnToEnUtil.getFirstLetter(qsh);

			qshList.get(i).put("qshpy", qshpy);
		}

		List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = map.get("qshpy");

			if (qshpy.equalsIgnoreCase(py)) {

				HashMap<String, String> newQsMap = new HashMap<String, String>();
				newQsMap.put("qshdm", qsh);
				newQsMap.put("qshmc", qsh);

				qsList.add(newQsMap);

			}
		}

		return qsList;
	}
	
	/**
	 * 获得部门拼音
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String getBmpy(String bmdm, String bmlx) {

		DAO dao = DAO.getInstance();
		
		
		String dm = bmlx + "dm";
		String mc = bmlx + "mc";
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct ");
		sql.append(dm + "," + mc);
		sql.append(" from view_njxyzybj ");
		sql.append(" where " + dm + " = ? ");
		
		String bmmc = dao.getOneRs(sql.toString(), new String[]{bmdm}, mc);
		String bmpy = CnToEnUtil.getFirstLetter(bmmc);// 部门
		
		return bmpy;
	}
	
	/**
	 * 获得查询条件(用户身份)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String getSearchTjByUser(HttpServletRequest request, String tableBm,
			String xydm, String bjdm) {

		User user = getUser(request);

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();

		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}
		
		StringBuilder query = new StringBuilder();
		
		if (isXy && !Base.isNull(xydm)) {// 访问用户为学院
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");

		} else if (fdyqx && bzrqx && !Base.isNull(bjdm)) {// 访问用户为辅导员兼班主任

			query.append(" and (exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "')) ");

		} else if (fdyqx && !Base.isNull(bjdm)) {
			
			query.append(" and exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		} else if (bzrqx) {
			
			query.append(" and exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
		} 
		
		return query.toString();
	}
	
	/**
	 * 获得查询条件(用户身份)
	 * 
	 * @author zhangxiaobin
	 * 
	 */
	public String getSearchTjByUser2(User user2, String tableBm,
			String xydm, String bjdm) {

		// 用户名
		String userName = user2.getUserName();
		// 用户类型
		String userType = user2.getUserType();
		// 用户所在部门
		String userDep = user2.getUserDep();

		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user2.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user2.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// 学院用户
			isXy = true;
		}
		
		StringBuilder query = new StringBuilder();
		
		if (isXy && !Base.isNull(xydm)) {// 访问用户为学院
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");

		} else if (fdyqx && bzrqx && !Base.isNull(bjdm)) {// 访问用户为辅导员兼班主任

			query.append(" and (exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "')) ");

		} else if (fdyqx && !Base.isNull(bjdm)) {
			
			query.append(" and exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		} else if (bzrqx) {
			
			query.append(" and exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
		} 
		
		return query.toString();
	}
	
	/**
	 * 获得查询条件(用户身份)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public static String getSearchTjByUser(User user, String tableBm,
			String xydm, String bjdm) { 

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		// 书院用户
		String syQx = user.getSyQx();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// 访问用户为学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		} else if ("sy".equalsIgnoreCase(userStatus)) {

			query.append(" exists (select 1 from xg_xtwh_sybjglb xx join fdyxxb xy on xx.sydm = xy.sydm where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  xx.bjdm ");
			query.append(" and xy.zgh = '" + userName + "') ");
			
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// 访问用户为辅导员兼班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "')) ");

		}else if("stu".equalsIgnoreCase(userType)){
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+userName+"'");
		}else if("parent".equalsIgnoreCase(userType)){
			//家长用户按其子女过滤
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+user.getChildId()+"'");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}

		query.append(" )");
		
		/*if("yes".equalsIgnoreCase(syQx)){
			query.append(" and (");
			query.append(" exists (select 1 from xg_xtwh_sybjglb xx join fdyxxb xy on xx.sydm = xy.sydm where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  xx.bjdm ");
			query.append(" and xy.zgh = '" + userName + "') ");
			query.append(" )");
		}*/
		
		return query.toString();
	}

	/**
	 * 获取用户查询条件 参评组专用 20181009
	 */
	public static String getSearchTjByUserForCpz(User user, String tableBm,
										   String xydm, String bjdm) {

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		// 书院用户
		String syQx = user.getSyQx();

		StringBuilder query = new StringBuilder();

		query.append(" and (");


		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}

		if ("xy".equalsIgnoreCase(userStatus)) {// 访问用户为学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		} else if ("sy".equalsIgnoreCase(userStatus)) {

			query.append(" exists(select 1 from xg_zhcp_fstjjlb bb WHERE ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  bb.bjdm ");
			query.append(" and (bb.xn,bb.xq) = (select xn,xq from XG_PJPY_NEW_CSSZB) ");
			query.append(" AND exists(select 1 from fdycpzglb where cpzdm = bb.CPZDM and zgh = '"+userName+"')");
			query.append(" ) ");

		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// 访问用户为辅导员兼班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "')) ");

		}else if("stu".equalsIgnoreCase(userType)){
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+userName+"'");
		}else if("parent".equalsIgnoreCase(userType)){
			//家长用户按其子女过滤
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+user.getChildId()+"'");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}

		query.append(" )");

		return query.toString();
	}

	public static String getSearchTjByUserOfYdxg(User user, String tableBm,
			String xydm, String bjdm) { 

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// 访问用户为学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// 访问用户为辅导员兼班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "')) ");

		}else if("stu".equalsIgnoreCase(userType)){
			query.append(" exists (select 1 from xsxxb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.xh = '" + userName + "') ");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}

		query.append(" )");
		return query.toString();
	}

	/**
	 *  获得查询条件(用户身份).
	 *  学校用户不过滤，其他用户按用户部门做学院层面的过滤；
	 *  对应的功能菜单，一般开给学院用户。
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-04-17 11:10
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @return java.lang.String
	 * @throw
	 */
	public static String getSearchTjByUserOnlyXxXy(User user, String tableBm, String xydm) {

		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		StringBuilder query = new StringBuilder();

		if(!"xx".equalsIgnoreCase(userStatus)){
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");
		}
		return query.toString();
	}


	public static String getSearchTjByUser_jhzy(User user, String tableBm, String xydm) {

		StringBuilder query = new StringBuilder();
		String userStatus = user.getUserStatus();
		if(!"xx".equalsIgnoreCase(userStatus)){
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = (select bmdm from fdyxxb where zgh='"+user.getUserName()+"') ");
		}
		return query.toString();
	}


		/**
         *
         * @描述:浙大新生之友个性化权限控制
         * @作者：xiaxia[工号：1104]
         * @日期：2015-2-14 下午03:48:26
         * @修改记录: 修改者名字-修改日期-修改内容
         * @param user
         * @param tableBm
         * @param xydm
         * @param bjdm
         * @return
         * String 返回类型
         * @throws
         */
	public static String getSearchQxTjByUser(User user, String tableBm,
			String xydm,String flag) { 

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		
		if ("xy".equalsIgnoreCase(userStatus)) {// 访问用户为学院
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
		}else if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
		}
		if("true".equals(flag)){
			query.append(" or xydm='"+userDep+"'");
		}

		query.append(" )");
		return query.toString();
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:素质拓展权限
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-13 下午03:08:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getSearchTjOfSztzByUser(User user, String tableBm,
			String sbbmdm,String sbr) throws Exception { 

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		List<String> glyList = new GlyglDao().getGlyList();
		if(glyList.contains(userName)){
			query.append(" 1= 1 ");
		}
		
		else {
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(sbr + " = '" + userName + "' ");
		}

		query.append(" )");
		return query.toString();
	}
	public static String getSearchSztzShTjByUser(User user, String tableBm,
			String xydm,String sbr) { 

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		 if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
			}
			else{
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append(xydm + " = '" + userDep + "' ");
				}
			

		query.append(" )");
		return query.toString();
	}
	
	public static String getSearchTjOfKycxByUser(User user, String tableBm,
			String xh) { 

		// 用户名
		String userName = user.getUserName();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		 if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
			}
			else{
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append(xh + " = '" + userName + "' ");
				}
			

		query.append(" )");
		return query.toString();
	}
	public static String getSearchTjOfFycxByUser(User user, String tableBm,
			String xh) { 

		// 用户名
		String userName = user.getUserName();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		// 辅导员权限
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		query.append(" and (");
		
		 if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
			}
		 else if ("xy".equalsIgnoreCase(userStatus)||fdyqx||bzrqx) {// 访问用户为学院
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append("xmssdw= '" + user.getUserDep() + "' ");
				
			}
			else{
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append(xh + " = '" + userName + "' ");
				}
			

		query.append(" )");
		return query.toString();
	}
	/**
	 * 
	 * @描述:教师查看工作记录权限
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-3 上午11:06:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getSearchGzjlTjByUser(User user, String tableBm,
			String xydm,String bjdm) { 

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// 访问用户为学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		}else if("xx".equalsIgnoreCase(userStatus)){
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}else {
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".zgh=");
			query.append("'"+userName+"'");
		}

		query.append(" )");
		return query.toString();
	}
	
	/**
	 * 
	 * @描述:获得班主任/辅导员审核岗位组条件(用户身份)
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-7-4 上午09:32:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getShgwzByUser(User user, String tableBm,
			String xydm, String bjdm) {

		// 用户名
		String userName = user.getUserName();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		// 班主任
		query.append(" (gwz = 'bzr' and exists (select 1 from bzrbbb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append("zybj =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "')) ");

		// 辅导员
		query.append(" or (gwz = 'fdy' and exists (select 1 from fdybjb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append(bjdm + " =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "')) ");
		
		// 班主任+辅导员
		query.append(" or (gwz = 'bzrfdy' and ( exists (select 1 from bzrbbb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append("zybj =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "') or exists (select 1 from fdybjb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append(bjdm + " =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "'))) ");
		
		// 其他
		query.append(" or (gwz is null)");

		query.append(" )");
		return query.toString();
	}
		
	/**
	 * 获得查询条件(用户身份)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String getSearchTjByUser(String tableBm, User user) {

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 公寓管理员权限
		String gyglyQx = user.getGyglyQx();
		// 用户身份
		String userStatus = user.getUserStatus();
		// 不控制的身份
		HashMap<String, String> notCtrlStatus = user.getNotCtrlStatus();
		boolean xy_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("xy"))) {
			xy_flag = false;
		}

		boolean jd_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("jd"))) {
			jd_flag = false;
		}

		boolean fdy_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("fdy"))) {
			fdy_flag = false;
		}

		boolean bzr_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("bzr"))) {
			bzr_flag = false;
		}

		boolean gygly_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("gygly"))) {
			gygly_flag = false;
		}

		StringBuilder query = new StringBuilder();

		if ("xy".equalsIgnoreCase(userStatus) && xy_flag) {// 访问用户为学院
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xydm = '" + userDep + "' ");

		} else if ("jd".equalsIgnoreCase(userStatus) && jd_flag) {// 访问用户为辅导员兼班主任

			query.append(" and (exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

			query.append(" or exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "')) ");

		} else if ("fdy".equalsIgnoreCase(userStatus) && fdy_flag) {

			query.append(" and exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		} else if ("bzr".equalsIgnoreCase(userStatus) && bzr_flag) {

			query.append(" and exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(" bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		}

		if ("yes".equalsIgnoreCase(gyglyQx) && gygly_flag) {
			query.append(" and exists (select 1 from xg_gygl_ldglb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("lddm =b.lddm ");
			query.append(" and b.yhm='" + userName + "') ");
		}

		return query.toString();
	}
	
	/**
	 * 获得查询条件
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 
	 */
	public static String getSearchTj(SearchModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		boolean singlePlcx=true;
		SearchDAO dao = new SearchDAO();
		
		// 条件列表
		List<HashMap<String, String>> tjList = dao.getGnmkTjList(model);

		// 模糊查询类型
		String mhcx_lx = model.getMhcx_lx();
		// 模糊查询
		String input_mhcx = model.getInput_mhcx();
		if (!Base.isNull(input_mhcx)) {
			model.setSearch_tj_mhcx(input_mhcx);
		}
		//批量查询
		String plcx_xh = model.getPlcx_xh();
		if(!Base.isNull(plcx_xh)){
			model.setSearch_tj_xh(plcx_xh.replaceAll(" ", ",").replaceAll("\r", ",").replaceAll("\n",
			",").split(","));
		}
		String plcx_xm = model.getPlcx_xm();
		if(!Base.isNull(plcx_xm)){
			model.setSearch_tj_xm(plcx_xm.replaceAll(" ", ",").replaceAll("\r", ",").replaceAll("\n",
			",").split(","));
		}
		
		// 查询条件（模糊查询）
		String[] mhcx = model.getSearch_tj_mhcx();

		// 拼接查询条件
		StringBuilder query = new StringBuilder();
		// 模糊条件
		StringBuilder mhQuery = new StringBuilder();
		// 时间条件
		StringBuilder sjQuery = new StringBuilder();
		// 数值区间条件
		StringBuilder numQuery = new StringBuilder();
		
		Class myClass = model.getClass();

		if (tjList != null && tjList.size() > 0) {
			
			// 开始时间
			String[] input_kssj = model.getSearch_tj_kssj();
			// 结束时间
			String[] input_jssj = model.getSearch_tj_jssj();
			
			int kssjNum = 0;
			int jssjNum = 0;
			int count = 0;
			
			// 最小值
			String[] input_ksnum = model.getSearch_tj_ksnum();
			// 最大值
			String[] input_jsnum = model.getSearch_tj_jsnum();
			
			int ksnumNum = 0;
			int jsnumNum = 0;
			
			for (int i = 0; i<tjList.size() ; i++) {
				
				HashMap<String, String> map = tjList.get(i);
				// 类型
				String lx = map.get("lx");
				// 条件
				String tj = map.get("tj");
				// 字段
				String zd = Base.isNull(map.get("zd")) ? tj : map.get("zd");

				// 模糊查询
				if ("mhcx".equalsIgnoreCase(lx)
						&& "all".equalsIgnoreCase(mhcx_lx)) {
					
					if (mhcx != null && mhcx.length > 0) {
						if(Base.isNull(mhQuery.toString())){
							mhQuery.append(" and ((");
						}else{
							mhQuery.append(" or ( ");
						}
						for (int j = 0; j < mhcx.length; j++) {
							if (j == 0) {
								mhQuery.append(zd + " like '%' || ? || '%' ");
							} else {
								mhQuery.append(" or " + zd + " like '%' || ? || '%' ");
							}
						}
						mhQuery.append(")");
					}
				}
				// 时间查询
				else if ("sjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[kssjNum];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and (to_number("+replaceTime(zd)+") >= ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						kssjNum++;
					}
					
					if (input_jssj != null && input_jssj.length > 0) {
					
						String jssj = input_jssj[jssjNum];

						if (!Base.isNull(jssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(zd)+") <= ? )");
							//sjQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jssjNum++;
					}
				}
				// 单个时间查询
				else if ("dgsjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[count];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(zd)+") = ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}

						count++;
					}
				}
				// 数值区间查询
				else if ("numcx".equalsIgnoreCase(lx)) {

					if (input_ksnum != null && input_ksnum.length > 0) {
						
						String ksnum = input_ksnum[ksnumNum];

						if (!Base.isNull(ksnum)) {
							numQuery.append(" and (to_number("+zd+") >= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						ksnumNum++;
					}
					
					if (input_jsnum != null && input_jsnum.length > 0) {
					
						String jsnum = input_jsnum[jsnumNum];

						if (!Base.isNull(jsnum)) {
							numQuery.append(" and (to_number("+zd+") <= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jsnumNum++;
					}
				}
				// 点击查询
				else if ("djcx".equalsIgnoreCase(lx)||"zdycx".equalsIgnoreCase(lx)) {
					
					String methodName = "getSearch_tj_" + tj;

					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// 存在相关条件
					if (sT != null && sT.length > 0) {
						query.append(" and ( ");
						for (int j = 0; j < sT.length; j++) {
							if (j == 0) {
								query.append(zd + " = ? ");
							} else {
								query.append(" or " + zd + " = ? ");
							}
						}
						query.append(")");
					}
				}
					else if ("plcx".equalsIgnoreCase(lx)) {
										
										String methodName = "getSearch_tj_" + tj;
					
										String[] sT = (String[]) myClass.getMethod(methodName,
												(Class[]) null).invoke(model, (Object[]) null);
					
										// 存在相关条件
										if (sT != null && sT.length > 0) {
											if(singlePlcx){
												query.append(" and ( ");
												singlePlcx=false;
											}else{
												query.append(" or  ");
											}
											
											for (int j = 0; j < sT.length; j++) {
												if (j == 0) {
													query.append(zd + " = ? ");
												} else {
													query.append(" or " + zd + " = ? ");
												}
											}
											
										}
									}
			}
			if(!singlePlcx){
				query.append(")");
			}
			if (!"all".equalsIgnoreCase(mhcx_lx)) {
				if (mhcx != null && mhcx.length > 0) {
					mhcx_lx = mhcx_lx.replace("mhcx_", "");
					if (Base.isNull(mhQuery.toString())) {
						mhQuery.append(" and ((");
					} else {
						mhQuery.append(" or ( ");
					}
					for (int j = 0; j < mhcx.length; j++) {
						if (j == 0) {
							mhQuery.append(mhcx_lx + " like '%' || ? || '%' ");
						} else {
							mhQuery.append(" or " + mhcx_lx
									+ " like '%' || ? || '%' ");
						}
					}
					mhQuery.append(")");
				}
			}
		}

		if(!Base.isNull(mhQuery.toString())){
			mhQuery.append(")");
		}
		query.append(mhQuery);
		query.append(sjQuery);
		query.append(numQuery);
		
		// 输入学号
		String input_xh = model.getInput_xh();

		// 学号
		if (!Base.isNull(input_xh)) {
			query.append(" and xh = ? ");
		}
		
		return query.toString();
	}
	
	
	/**
	 * 
	 * @描述:获得查询条件
	 * @作者：陶钢军[工号：1075]
	 * @日期：2016-1-12 上午11:05:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param tableBm 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * String 返回类型 
	 * @throws
	 */
	public static String getSearchTj(SearchModel model, String tableBm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchDAO dao = new SearchDAO();
		
		// 条件列表
		List<HashMap<String, String>> tjList = dao.getGnmkTjList(model);

		// 模糊查询类型
		String mhcx_lx = model.getMhcx_lx();
		// 模糊查询
		String input_mhcx = model.getInput_mhcx();
		if (!Base.isNull(input_mhcx)) {
			model.setSearch_tj_mhcx(input_mhcx);
		}
		
		// 查询条件（模糊查询）
		String[] mhcx = model.getSearch_tj_mhcx();

		// 拼接查询条件
		StringBuilder query = new StringBuilder();
		// 模糊条件
		StringBuilder mhQuery = new StringBuilder();
		// 时间条件
		StringBuilder sjQuery = new StringBuilder();
		// 数值区间条件
		StringBuilder numQuery = new StringBuilder();
		
		Class myClass = model.getClass();

		if (tjList != null && tjList.size() > 0) {
			
			// 开始时间
			String[] input_kssj = model.getSearch_tj_kssj();
			// 结束时间
			String[] input_jssj = model.getSearch_tj_jssj();
			
			int kssjNum = 0;
			int jssjNum = 0;
			int count = 0;
			
			// 最小值
			String[] input_ksnum = model.getSearch_tj_ksnum();
			// 最大值
			String[] input_jsnum = model.getSearch_tj_jsnum();
			
			int ksnumNum = 0;
			int jsnumNum = 0;
			
			for (int i = 0; i<tjList.size() ; i++) {
				
				HashMap<String, String> map = tjList.get(i);
				// 类型
				String lx = map.get("lx");
				// 条件
				String tj = map.get("tj");
				// 字段
				String zd = Base.isNull(map.get("zd")) ? tj : map.get("zd");

				// 模糊查询
				if ("mhcx".equalsIgnoreCase(lx)
						&& "all".equalsIgnoreCase(mhcx_lx)) {
					
					if (mhcx != null && mhcx.length > 0) {
						if(Base.isNull(mhQuery.toString())){
							mhQuery.append(" and ((");
						}else{
							mhQuery.append(" or ( ");
						}
						for (int j = 0; j < mhcx.length; j++) {
							if (j == 0) {
								mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + ".");
								mhQuery.append(zd + " like '%' || ? || '%' ");
							} else {
								mhQuery.append(" or ");
								mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + ".");
								mhQuery.append(zd + " like '%' || ? || '%' ");
							}
						}
						mhQuery.append(")");
					}
				}
				// 时间查询
				else if ("sjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[kssjNum];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and (to_number("+replaceTime(Base.isNull(tableBm) ? "" : tableBm + "." + zd)+") >= ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						kssjNum++;
					}
					
					if (input_jssj != null && input_jssj.length > 0) {
					
						String jssj = input_jssj[jssjNum];

						if (!Base.isNull(jssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(Base.isNull(tableBm) ? "" : tableBm + "." + zd)+") <= ? )");
							//sjQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jssjNum++;
					}
				}
				// 单个时间查询
				else if ("dgsjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[count];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(Base.isNull(tableBm) ? "" : tableBm + "." + zd)+") = ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}

						count++;
					}
				}
				// 数值区间查询
				else if ("numcx".equalsIgnoreCase(lx)) {

					if (input_ksnum != null && input_ksnum.length > 0) {
						
						String ksnum = input_ksnum[ksnumNum];

						if (!Base.isNull(ksnum)) {
							numQuery.append(" and (to_number(" );
							numQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + ") >= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						ksnumNum++;
					}
					
					if (input_jsnum != null && input_jsnum.length > 0) {
					
						String jsnum = input_jsnum[jsnumNum];

						if (!Base.isNull(jsnum)) {
							numQuery.append(" and (to_number(" );
							numQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + ") <= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jsnumNum++;
					}
				}
				// 点击查询
				else if ("djcx".equalsIgnoreCase(lx)||"zdycx".equalsIgnoreCase(lx)) {
					
					String methodName = "getSearch_tj_" + tj;

					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// 存在相关条件
					if (sT != null && sT.length > 0) {
						query.append(" and ( ");
						for (int j = 0; j < sT.length; j++) {
							if (j == 0) {
								query.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + " = ? ");
							} else {
								query.append(" or ");
								query.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + " = ? ");
							}
						}
						query.append(")");
					}
				}
			}
			
			if (!"all".equalsIgnoreCase(mhcx_lx)) {
				if (mhcx != null && mhcx.length > 0) {
					mhcx_lx = mhcx_lx.replace("mhcx_", "");
					if (Base.isNull(mhQuery.toString())) {
						mhQuery.append(" and ((");
					} else {
						mhQuery.append(" or ( ");
					}
					for (int j = 0; j < mhcx.length; j++) {
						if (j == 0) {
							mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + mhcx_lx + " like '%' || ? || '%' ");
						} else {
							mhQuery.append(" or ");
							mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + mhcx_lx
									+ " like '%' || ? || '%' ");
						}
					}
					mhQuery.append(")");
				}
			}
		}

		if(!Base.isNull(mhQuery.toString())){
			mhQuery.append(")");
		}
		query.append(mhQuery);
		query.append(sjQuery);
		query.append(numQuery);
		
		// 输入学号
		String input_xh = model.getInput_xh();

		// 学号
		if (!Base.isNull(input_xh)) {
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + "." +"xh = ? ");
		}
		
		return query.toString();
	}
	
	/**
	 * 拼接日期控件格式规约语句
	 * @param time
	 * @return
	 */
	public static String replaceTime(String time){
		
		StringBuilder replaceTime = new StringBuilder();
		
		String[] replaceLx={"-","年","月","日","\\","/"," ",":"};
		
		for (int i=0;i<replaceLx.length;i++){
			
			if(i==0){
				
				replaceTime.append("  replace(" + time + ",'"+replaceLx[i]+"','') ");
			
			}else{
				
				replaceTime=new StringBuilder("  replace(" + replaceTime + ",'"+replaceLx[i]+"','') ");
				
			}
		
		}
		replaceTime=new StringBuilder("  substr(" + replaceTime + ",1,8) ");
		
		return replaceTime.toString();
		
	}

	/**
	 * 获得查询条件
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public static String[] getTjInput(SearchModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchDAO dao = new SearchDAO();
		// 条件列表
		List<HashMap<String, String>> tjList = dao.getGnmkTjList(model);
		
		Class myClass = model.getClass();

		// 模糊查询类型
		String mhcx_lx = model.getMhcx_lx();
		// 条件输入值
		ArrayList<String> inputValue = new ArrayList<String>();
		// 模糊条件
		ArrayList<String> mhValue = new ArrayList<String>();
		// 时间条件
		ArrayList<String> sjValue = new ArrayList<String>();
		// 数值区间条件
		ArrayList<String> numValue = new ArrayList<String>();
		
		if (tjList != null && tjList.size() > 0) {
			// 开始时间
			String[] input_kssj = model.getSearch_tj_kssj();
			// 结束时间
			String[] input_jssj = model.getSearch_tj_jssj();

			int count = 0;
			
			// 最小值
			String[] input_ksnum = model.getSearch_tj_ksnum();
			// 最大值
			String[] input_jsnum = model.getSearch_tj_jsnum();
			
			int numcount = 0;
			
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> map = tjList.get(i);
				// 类型
				String lx = map.get("lx");
				// 条件
				String tj = map.get("tj");

				// 模糊查询
				if ("mhcx".equalsIgnoreCase(lx)
						&& "all".equalsIgnoreCase(mhcx_lx)) {
					String methodName = "getSearch_tj_mhcx";
					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// 存在条件
					if (sT != null && sT.length > 0) {
						//num += sT.length;
						//colList.add(tj);
						for (int j = 0; j < sT.length; j++) {
							mhValue.add(sT[j]);
						}
					}
				}
				// 时间查询
				else if ("sjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0
							&& input_jssj != null && input_jssj.length > 0) {
						String kssj = input_kssj[count];
						String jssj = input_jssj[count];

						if (!Base.isNull(kssj)) {
							sjValue.add(kssj);
//							String kssjEsp = kssj.substring(0, 4) + "-"
//									+ kssj.substring(4, 6) + "-"
//									+ kssj.substring(6, 8);
//							sjValue.add(kssjEsp);
						}
						if (!Base.isNull(jssj)) {
							sjValue.add(jssj);
//							String jssjEsp = jssj.substring(0, 4) + "-"
//									+ jssj.substring(4, 6) + "-"
//									+ jssj.substring(6, 8);
//							sjValue.add(jssjEsp);
						}

						count++;
					}
				}
				// 单个时间查询
				else if ("dgsjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						String kssj = input_kssj[count];

						if (!Base.isNull(kssj)) {
							sjValue.add(kssj);
						}

						count++;
					}
				}
				// 数值区间查询
				else if ("numcx".equalsIgnoreCase(lx)) {

					if (input_ksnum != null && input_ksnum.length > 0
							&& input_jsnum != null && input_jsnum.length > 0) {
						String ksnum = input_ksnum[numcount];
						String jsnum = input_jsnum[numcount];

						if (!Base.isNull(ksnum)) {
							numValue.add(ksnum);
						}
						if (!Base.isNull(jsnum)) {
							numValue.add(jsnum);
						}

						numcount++;
					}
				}
				// 点击查询
				else if ("djcx".equalsIgnoreCase(lx)||"zdycx".equalsIgnoreCase(lx)||"plcx".equalsIgnoreCase(lx)) {
					String methodName = "getSearch_tj_" + tj;
					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// 存在条件
					if (sT != null && sT.length > 0) {
						//num += sT.length;
						//colList.add(tj);
						for (int j = 0; j < sT.length; j++) {
							inputValue.add(sT[j]);
						}
					}
				}	
			}
			
			if (!"all".equalsIgnoreCase(mhcx_lx)) {
				// 查询条件（模糊查询）
				String[] mhcx = model.getSearch_tj_mhcx();
				// 存在条件
				if (mhcx != null && mhcx.length > 0) {

					for (int j = 0; j < mhcx.length; j++) {
						mhValue.add(mhcx[j]);
					}
				}
			}
		}

		inputValue.addAll(mhValue);
		inputValue.addAll(sjValue);
		inputValue.addAll(numValue);
		
		// 输入学号
		String input_xh = model.getInput_xh();
		
		// 学号
		if (!Base.isNull(input_xh)) {
			inputValue.add(input_xh);
		}
		
		return inputValue.toArray(new String[]{});
	}

	/**
	 * 设置特殊模块的条件
	 * 
	 * @param searchForm
	 * @param request
	 * 
	 * @author 伟大的骆
	 */
	private void getEspecialTj(SearchForm searchForm, HttpServletRequest request) {
		// 模块路径
		String path = searchForm.getPath();

		if ("xtwhSysz.do?method=xzzqView".equalsIgnoreCase(path)) {// 下载专区

			// 文件类型列表
			List<HashMap<String, String>> filelxList = getWhList(
					"xg_xtwh_szzqlxb", "dm", "mc", "", "", "");
			filelxList.remove(0);
			request.setAttribute("filelxList", filelxList);
		}
	}

	public static List<HashMap<String, String>> getBjPyList() {
		return bjPyList;
	}

	public static void setBjPyList(List<HashMap<String, String>> bjPyList) {
		SearchService.bjPyList = bjPyList;
	}

	public static List<HashMap<String, Object>> getBjTjList() {
		return bjTjList;
	}

	public static void setBjTjList(List<HashMap<String, Object>> bjTjList) {
		SearchService.bjTjList = bjTjList;
	}

	public static List<HashMap<String, Object>> getXyTjList() {
		return xyTjList;
	}

	public static void setXyTjList(List<HashMap<String, Object>> xyTjList) {
		SearchService.xyTjList = xyTjList;
	}

	public static List<HashMap<String, String>> getZyPyList() {
		return zyPyList;
	}

	public static void setZyPyList(List<HashMap<String, String>> zyPyList) {
		SearchService.zyPyList = zyPyList;
	}

	public static List<HashMap<String, Object>> getZyTjList() {
		return zyTjList;
	}

	public static void setZyTjList(List<HashMap<String, Object>> zyTjList) {
		SearchService.zyTjList = zyTjList;
	}
	
	/********************************************************************************************************************************/
	/**************************************************公寓管理（第三版）高级查询条件**************************************************/
	/********************************************************************************************************************************/
	/**
	 * 获得公寓管理相关信息	  
	 * @author zhang	 
	 */
	public List<HashMap<String, String>> getGyglInfo_Third(String lx, String[] xqdm,
			String[] yqdm, String[] lddm, String[] ch, String gyglyQx,
			String userName, String userDep) {

		List<HashMap<String, String>> list = null;

		String tableName = "";
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		ArrayList<String> inPutList = new ArrayList<String>();
		String[] colList = null;

		// 园区
		if ("yq".equalsIgnoreCase(lx)) {

			tableName = "yqdmb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from sslddmb a,xg_gygl_ldglb b ");
				query.append("where yqdmb.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by yqdm ");
			
			colList = new String[] { "yqdm", "yqmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					"");
		}
		// 楼栋
		else if ("ld".equalsIgnoreCase(lx)) {

			tableName = "xg_gygl_new_ldxxb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			//用户权限限定判断，用户在楼栋管理表中有用户楼栋信息
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where xg_gygl_new_ldxxb.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by lddm ");
			
			colList = new String[] { "lddm", "ldmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,"");
		}
		//层号
		else if ("ch".equalsIgnoreCase(lx)) {

			String sql = "select distinct ch,chmc from view_xg_gygl_new_cwxx ";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_xg_gygl_new_cwxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by to_number(ch) ");
			
			sql+=query;
			
			colList = new String[] { "ch", "chmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);
		}		
		//寝室号
		else if ("qsh".equalsIgnoreCase(lx)) {

			String sql = "select distinct qsh,ch from view_xg_gygl_new_cwxx ";
			
			//校区
			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			//园区
			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			//	楼栋
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			// 层号
			if (ch != null && ch.length > 0) {
				query.append(" and (");
				for (int i = 0; i < ch.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" ch = ? ");

					inPutList.add(ch[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_xg_gygl_new_cwxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by to_number(ch),qsh ");
			
			sql+=query;
			
			colList = new String[] { "qsh"};

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				list.get(i).put("qshpy", qshpy.toUpperCase());
				list.get(i).put("qshdm", qsh);
				list.get(i).put("qshmc", qsh);
			}
		}
		return list;
	}
	
	/**
	 * 获得寝室信息（根据拼音）
	 * @author zhangh	 
	 */
	public List<HashMap<String, String>> getQsInfoByPy_Third(String py,
			String[] xqdm, String[] yqdm, String[] lddm, String[] ch,
			String userStatus, String userName, String userDep) {

		// 获得寝室列表
		List<HashMap<String, String>> qshList = dao.getQsList_Third(xqdm, yqdm, lddm,
				ch, userStatus, userName, userDep);

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = CnToEnUtil.getFirstLetter(qsh);

			qshList.get(i).put("qshpy", qshpy.toUpperCase());
		}

		List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = map.get("qshpy");

			if (qshpy.equalsIgnoreCase(py)) {

				HashMap<String, String> newQsMap = new HashMap<String, String>();
				newQsMap.put("qshdm", qsh);
				newQsMap.put("qshmc", qsh);

				qsList.add(newQsMap);

			}
		}

		return qsList;
	}
	
	/** ***************************************************************************************************************************** */
	/** ************************************************为解决65535问题，高级查询重构，优化************************************************* */
	/** ***************************************************************************************************************************** */

	/**
	 * 初始化列表（楼栋，层号，寝室号）
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setLdChQshList(SearchForm searchForm, User user)
			throws Exception {

		// 校区列表
		List<HashMap<String, Object>> xqdmTjList = getXqdmList(user);
		searchForm.setXqdmTjList(xqdmTjList);
		
		// 园区列表
		List<HashMap<String, Object>> yqdmTjList = getYqdmList(null,user);
		searchForm.setYqdmTjList(yqdmTjList);
		
		// 楼栋列表
		List<HashMap<String, Object>> ldList = getLdList(null, null,user);
		searchForm.setLdTjList(ldList);

		// 层号列表
		List<HashMap<String, Object>> chList = getChList(null, null, null);
		searchForm.setChTjList(chList);

		// 寝室列表
		List<HashMap<String, Object>> qsList = getQsList(null, null, null, null);
		searchForm.setQshTjList(qsList);
	}
	
	/**
	 * 初始化列表（勤工部门）
	 * @author yeyipin
	 * @param searchForm
	 * @param user
	 * @throws Exception
	 */
	public void setQgbmList(SearchForm searchForm, User user) throws Exception{
		List<HashMap<String, Object>> qgbmTjList = getQgbmList(user);
		searchForm.setQgbmTjList(qgbmTjList);
	}
	
	/**
	 * 初始化列表（违纪类别）
	 * @param searchForm
	 */
	public void setWjlbList(SearchForm searchForm){
		//违纪大类
		List<HashMap<String, Object>> gyjllbdldmTjList = getGyjllbdldmList();
		searchForm.setGyjllbdldmTjList(gyjllbdldmTjList);
		
		//违纪类别
		List<HashMap<String, Object>> gyjllbdmTjList = getGyjllbdmList(null);
		searchForm.setGyjllbdmTjList(gyjllbdmTjList);
	}
	
	/**
	 * 获得校区代码
	 * 
	 * @author zhanghui
	 * @date   20120301
	 */
	private List<HashMap<String, Object>> getXqdmList(User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT a.dm,a.xqmc mc from dm_zju_xq a left join xg_gygl_new_ldxxb b on a.dm = b.xqdm where 1=1 ");
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓管理员，限制楼栋
			sql.append(" and b.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')");
		}
		sql.append(" order by a.dm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> xqdmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				xqdmTjList.add(map);
			}
		}
		return xqdmTjList;
	}
	
	
	/**
	 * 获得园区代码
	 * 
	 * @author zhanghui
	 * @date   20120301
	 */
	public List<HashMap<String, Object>> getYqdmList(String[] xq,User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT a.yqdm dm,yqmc mc from zxbz_ssyqdm a left join xg_gygl_new_ldxxb b on a.yqdm = b.yqdm where 1=1 ");
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓管理员，限制楼栋
			sql.append(" and b.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')");
		}
		
		// 校区非空
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		sql.append(" order by dm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> yqdmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				yqdmTjList.add(map);
			}
		}
		return yqdmTjList;
	}	
	
	/**
	 * 获得楼栋列表
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, Object>> getLdList(String[] xq, String[] yq,User user) {

		DAO dao = DAO.getInstance();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t.lddm dm,ldmc mc ");
		sql.append("from xg_gygl_new_ldxxb t ");
		sql.append("where 1 = 1 ");
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓管理员，限制楼栋
			sql.append(" and lddm in(select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')");
		}
		// 校区非空
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// 园区非空
		if (yq != null && yq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < yq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" yqdm = '" + yq[i] + "' ");
			}
			sql.append(" )");
		}
		
		sql.append("order by t.lddm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> ldList = new ArrayList<HashMap<String, Object>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				ldList.add(map);
			}
		}

		return ldList;
	}
	
	
	
	/** 
	 * @描述:根据项目模块代码取得计分项目信息
	 * @作者：cp[工号：1352]
	 * @日期：2017-1-4 下午03:57:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmkdm
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws 
	 */
	protected List<HashMap<String, Object>> getJfxmList(String[] xmmkdm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t.jfxmdm dm,jfxmmc mc from XG_ZJLY_ZHSZF_JFXMB t  ");
		sql.append("where 1 = 1 ");
		if (xmmkdm != null && xmmkdm.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xmmkdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xmmkdm = '" + xmmkdm[i] + "' ");
			}
			sql.append(" )");
		}
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });
		List<HashMap<String, Object>> jfxmList = new ArrayList<HashMap<String, Object>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				jfxmList.add(map);
			}
		}
		return jfxmList;
	}
	/**
	 * 获得层号列表
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, Object>> getChList(String[] xq, String[] yq,
			String[] ld) {

		DAO dao = DAO.getInstance();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t.ch dm,chmc mc ");
		sql.append("from view_xg_gygl_new_cwxx t ");
		sql.append("where 1 = 1 ");
		
		// 校区非空
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// 园区非空
		if (yq != null && yq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < yq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" yqdm = '" + yq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// 楼栋非空
		if (ld != null && ld.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < ld.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" lddm = '" + ld[i] + "' ");
			}
			sql.append(" )");
		}
		sql.append("order by to_number(ch) ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });
		
		List<HashMap<String, Object>> chList = new ArrayList<HashMap<String, Object>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				chList.add(map);
			}
		}
		return chList;
	}
			
	/**
	 * 获得寝室列表（带拼音）
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, Object>> getQsList(String[] xq, String[] yq,
			String[] ld, String[] ch) {
		
		List<HashMap<String, Object>> gyglQshList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();

		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qsh from view_xg_gygl_new_qsxx t ");
		sql.append("where 1 = 1 ");
	
		// 校区非空
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// 园区非空
		if (yq != null && yq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < yq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" yqdm = '" + yq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// 楼栋非空
		if (ld != null && ld.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < ld.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" lddm = '" + ld[i] + "' ");
			}
			sql.append(" )");
		}
		// 层号非空
		if (ch != null && ch.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < ch.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" ch = '" + ch[i] + "' ");
			}
			sql.append(" )");
		}
		sql.append(" order by qsh ");

		List<HashMap<String, String>> qshList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "qsh" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < qshList.size(); j++) {

				HashMap<String, String> map = qshList.get(j);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				if (py.equalsIgnoreCase(qshpy)) {

					HashMap<String, String> newQsh = new HashMap<String, String>();

					if (qsList.size() < 3) {

						newQsh.put("qshdm", qsh);
						newQsh.put("qshmc", qsh);

						qsList.add(newQsh);

					} else {
						newQsh.put("qshdm", "qsh_" + qshpy);
						newQsh.put("qshmc", "其他");

						qsList.add(newQsh);

						break;
					}

				}
			}

			HashMap<String, Object> qsMap = new HashMap<String, Object>();
			qsMap.put("py", py);
			qsMap.put("qsList", qsList);

			gyglQshList.add(qsMap);
		}
		return gyglQshList;
	}
	
	/**
	 * 获得勤工部门列表
	 * yeyipin
	 * @param user
	 * @return
	 */
	private List<HashMap<String,Object>>getQgbmList(User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm dm,bmmc mc from view_xg_qgzx_yrdwdmb where bmmc<>'未确定' ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and bmdm = '"+user.getUserDep()+"' ");
		}
		sql.append(" order by dm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> qgbmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				qgbmTjList.add(map);
			}
		}
		return qgbmTjList;
	}
	
	/**
	 * 获得查询条件（勤工助学）
	 * yeyipin
	 * @param request
	 * @return
	 */
	public String getSearchTjByQgzx(HttpServletRequest request){
		User user = getUser(request);
		String searchTj = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			//searchTj+=" and yrdwdm = '"+user.getUserDep()+"' ";
			searchTj+=SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		}
		return searchTj;
	}
	
	/** ***************************************************************************************************************************** */
	/** ************************************************军训建制四级联动(团、营、连、排)************************************************* */
	/** ***************************************************************************************************************************** */

	/**
	 * 初始化列表（团、营、连、排）
	 * 
	 * @param request
	 * @author 易江东
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setJxjzList(SearchForm searchForm, User user,String jxid)
			throws Exception {
		List<String[]> sjidList=new ArrayList<String[]>();
		sjidList.add(new String[]{});//团参数
		sjidList.add(new String[]{});//营参数
		sjidList.add(new String[]{});//排参数
		sjidList.add(new String[]{});//排参数
		sjidList.add(new String[]{});//排参数
		sjidList.add(new String[]{});//排参数
		
		// 团列表
		List<HashMap<String, Object>> tidTjList = getTidList(sjidList, jxid);
		searchForm.setTidTjList(tidTjList);
		// 营列表
		List<HashMap<String, Object>> yidTjList = getYidList(sjidList, jxid);
		searchForm.setYidTjList(yidTjList);
		// 连列表
		List<HashMap<String, Object>> lidTjList = getLidList(sjidList, jxid);
		searchForm.setLidTjList(lidTjList);
		// 排列表
		List<HashMap<String, Object>> pidTjList = getPidList(sjidList, jxid);
		searchForm.setPidTjList(pidTjList);
		// 班列表
		List<HashMap<String, Object>> bidTjList = getBidList(sjidList, jxid);
		searchForm.setBidTjList(bidTjList);
		// 宿舍列表
		List<HashMap<String, Object>> ssidTjList = getSsidList(sjidList, jxid);
		searchForm.setSsidTjList(ssidTjList);
	}
	/**
	 * @描述:省市区 三级联动
	 * @作者：zhangjw
	 * @日期：2013-5-21 上午11:48:11
	 * @修改记录: 
	 * @param searchForm
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void setQxList(SearchForm searchForm, User user)
			throws Exception {
		List<String> qxList=new ArrayList<String>();
	
		// 省列表
		List<HashMap<String, Object>> shengTjList = getQxList(qxList, "sheng");
		// 市列表
		//List<HashMap<String, Object>> shiTjList = getQxList(qxList, "shi");
		// 区列表
		//List<HashMap<String, Object>> quTjList = getQxList(qxList, "qu");
		searchForm.setShengTjList(shengTjList);
		//searchForm.setShiTjList(shiTjList);
		//searchForm.setQuTjList(quTjList);
		}
	
	/**
	 * 获得军训建制团id
	 * 
	 * @author 易江东
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getTidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getTidList(jxid, sjidList);

		List<HashMap<String, Object>> tidTjList = listToList(list);
		return tidTjList;
	}
	/**
	 * @描述:省市区
	 * @作者：zhangjw
	 * @日期：2013-5-21 上午11:01:10
	 * @修改记录: 
	 * @param sjidList
	 * @param jxid
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getQxList(List<String> quList,String jb){
		if(jb == null || "".equals(jb)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getQxList(quList,jb);
		Iterator<HashMap<String, String>> ite = list.iterator();
		List<HashMap<String, String>> pylist = new ArrayList<HashMap<String, String>>();
		while(ite.hasNext()){
			HashMap<String, String> map = ite.next();
			map.put("jzid", map.get("jzid"));
			map.put("jzmc", map.get("jzmc"));
			pylist.add(map);
		}
		List<HashMap<String, Object>> qxTjList = listToList(pylist);
		
		return qxTjList;
	}
	
	/**
	 * 获得军训建制营 id
	 * 
	 * @author 易江东
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getYidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getYidList(jxid, sjidList);

		List<HashMap<String, Object>> yidTjList = listToList(list);
		return yidTjList;
	}
	
	
	/**
	 * 获得军训建制连id
	 * 
	 * @author 易江东
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getLidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getLidList(jxid, sjidList);

		List<HashMap<String, Object>> lidTjList = listToList(list);
		return lidTjList;
	}
	
	
	/**
	 * 获得军训建制排id
	 * 
	 * @author 易江东
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getPidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getPidList(jxid, sjidList);

		List<HashMap<String, Object>> pidTjList = listToList(list);
		return pidTjList;
	}
	
	/**
	 * 
	 * @描述: 获得军训建制班id
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-1 下午03:32:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sjidList
	 * @param jxid
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getBidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getBidList(jxid, sjidList);

		List<HashMap<String, Object>> bidTjList = listToList(list);
		return bidTjList;
	}
	
	/**
	 * 
	 * @描述: 获得军训建制宿舍id
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-1 下午03:32:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sjidList
	 * @param jxid
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getSsidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getSsidList(jxid, sjidList);

		List<HashMap<String, Object>> ssidTjList = listToList(list);
		return ssidTjList;
	}
	
	/**
	 * 类型转换  格式化数据
	 * @param strList
	 * @return
	 */
	private List<HashMap<String, Object>> listToList(List<HashMap<String, String>> list){
		List<HashMap<String, Object>> objList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("jzid");
				String mc = list.get(i).get("jzmc");

				map.put("dm", dm);
				map.put("mc", mc);

				objList.add(map);
			}
		}
		return objList;
	}

	/**
	 * 获得违纪大类代码
	 * 
	 * @author zhanghui
	 * @date   20121229
	 */
	private List<HashMap<String, Object>> getGyjllbdldmList(){
		return dao.getGyjllbdlList();
	}
	
	/**
	 * 获得违纪大类代码
	 * 
	 * @author zhanghui
	 * @date   20121229
	 */
	public List<HashMap<String, Object>> getGyjllbdmList(String[] gyjllbdldm){
		return dao.getGyjllbList(gyjllbdldm);
	}
	
	/**
	 * 
	 * @描述: 获得全学院列表(根据拼音)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午03:16:21
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getXyNewList(String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 获得全部的学院/部门列表
		List<HashMap<String, String>> newXylist = getXyAllList(userStatus,
				userName, userDep);

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newXylist.size(); j++) {

				HashMap<String, String> map = newXylist.get(j);

				String xypy = map.get("xypy");
				String xydm = map.get("xydm");
				String xymc = map.get("xymc");

				if (py.equalsIgnoreCase(xypy)) {

					HashMap<String, String> newXy = new HashMap<String, String>();
					newXy.put("xydm", xydm);
					newXy.put("xymc", xymc);
					xyList.add(newXy);

				}
			}

			HashMap<String, Object> xyMap = new HashMap<String, Object>();
			xyMap.put("py", py);
			xyMap.put("xyList", xyList);

			list.add(xyMap);
		}

		return list;

	}
	public List<HashMap<String, Object>> getXsXyList(String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 获得全部的学院/部门列表
		List<HashMap<String, String>> newXylist = getXsXyListOfXsxx(userStatus,
				userName, userDep,sfzxs);

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newXylist.size(); j++) {

				HashMap<String, String> map = newXylist.get(j);

				String xypy = map.get("xypy");
				String xydm = map.get("xydm");
				String xymc = map.get("xymc");

				if (py.equalsIgnoreCase(xypy)) {

					HashMap<String, String> newXy = new HashMap<String, String>();
					newXy.put("xydm", xydm);
					newXy.put("xymc", xymc);
					xyList.add(newXy);

				}
			}

			HashMap<String, Object> xyMap = new HashMap<String, Object>();
			xyMap.put("py", py);
			xyMap.put("xyList", xyList);

			list.add(xyMap);
		}

		return list;

	}
	/**
	 * 
	 * @描述:获得新全学院列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午03:08:45
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyAllList(String userStatus,
			String userName, String userDep) {
		
		List<HashMap<String, String>> xyList = dao.getXyNewList(userStatus,
				userName, userDep);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("需要配置的生僻字部门：" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}
	
	public List<HashMap<String, String>> getXsXyListOfXsxx(String userStatus,
			String userName, String userDep,boolean sfzxs) {
		
		List<HashMap<String, String>> xyList = dao.getXsXyList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("需要配置的生僻字部门：" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}

	
	/**
	 * 
	 * @描述:获得所有专业列表(根据拼音)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午02:57:36
	 * @param userZyPyList
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getZyNewList(
			List<HashMap<String, String>> userZyPyList, String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 专业列表（带拼音）
		List<HashMap<String, String>> newZylist = null;

		if (userZyPyList != null && userZyPyList.size() > 0) {
			newZylist = userZyPyList;
		} else {
			newZylist = getZyAllList(userStatus, userName, userDep, null);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newZylist.size(); j++) {

				HashMap<String, String> map = newZylist.get(j);

				String zypy = map.get("zypy");
				String zydm = map.get("zydm");
				String zymc = map.get("zymc");

				if (py.equalsIgnoreCase(zypy)) {

					HashMap<String, String> newZy = new HashMap<String, String>();

					if (zyList.size() < 3) {

						newZy.put("zydm", zydm);
						newZy.put("zymc", zymc);

						zyList.add(newZy);

					} else {
						newZy.put("zydm", "zy_" + zypy);
						newZy.put("zymc", "其他");

						zyList.add(newZy);

						break;
					}

				}
			}

			HashMap<String, Object> zyMap = new HashMap<String, Object>();
			zyMap.put("py", py);
			zyMap.put("zyList", zyList);

			list.add(zyMap);
		}

		return list;

	}
	public List<HashMap<String, Object>> getXsZyList(
			List<HashMap<String, String>> userZyPyList, String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 专业列表（带拼音）
		List<HashMap<String, String>> newZylist = null;

		if (userZyPyList != null && userZyPyList.size() > 0) {
			newZylist = userZyPyList;
		} else {
			newZylist = getXsZyList(userStatus, userName, userDep, null,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newZylist.size(); j++) {

				HashMap<String, String> map = newZylist.get(j);

				String zypy = map.get("zypy");
				String zydm = map.get("zydm");
				String zymc = map.get("zymc");

				if (py.equalsIgnoreCase(zypy)) {

					HashMap<String, String> newZy = new HashMap<String, String>();

					if (zyList.size() < 3) {

						newZy.put("zydm", zydm);
						newZy.put("zymc", zymc);

						zyList.add(newZy);

					} else {
						newZy.put("zydm", "zy_" + zypy);
						newZy.put("zymc", "其他");

						zyList.add(newZy);

						break;
					}

				}
			}

			HashMap<String, Object> zyMap = new HashMap<String, Object>();
			zyMap.put("py", py);
			zyMap.put("zyList", zyList);

			list.add(zyMap);
		}

		return list;

	}


	/**
	 * 
	 * @描述:获得新全专业列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午03:10:54
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @param nj
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyAllList(String userStatus,
			String userName, String userDep, String[] nj) {

		List<HashMap<String, String>> zyList = dao.getZyNewList(userStatus,
				userName, userDep, nj);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < zyList.size(); i++) {

			HashMap<String, String> map = zyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String zydm = map.get("zydm");
			String zymc = map.get("zymc");
			String zypy = CnToEnUtil.getFirstLetter(zymc);
			
			if("-".equalsIgnoreCase(zypy)){
				zypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", zydm);
				zypy = Base.isNull(zypy) ? "-" : zypy;
				System.out.println("需要配置的生僻字部门：" + zymc + "(" + zydm + ")");
			}
			
			HashMap<String, String> zyMap = new HashMap<String, String>();
			zyMap.put("zypy", zypy.toUpperCase());
			zyMap.put("xydm", xydm);
			zyMap.put("xymc", xymc);
			zyMap.put("zydm", zydm);
			zyMap.put("zymc", zymc);

			list.add(zyMap);
		}

		return list;
	}
	public List<HashMap<String, String>> getXsZyList(String userStatus,
			String userName, String userDep, String[] nj,boolean sfzxs) {

		List<HashMap<String, String>> zyList = dao.getXsZyList(userStatus,
				userName, userDep, nj,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < zyList.size(); i++) {

			HashMap<String, String> map = zyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String zydm = map.get("zydm");
			String zymc = map.get("zymc");
			String zypy = CnToEnUtil.getFirstLetter(zymc);
			
			if("-".equalsIgnoreCase(zypy)){
				zypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", zydm);
				zypy = Base.isNull(zypy) ? "-" : zypy;
				System.out.println("需要配置的生僻字部门：" + zymc + "(" + zydm + ")");
			}
			
			HashMap<String, String> zyMap = new HashMap<String, String>();
			zyMap.put("zypy", zypy.toUpperCase());
			zyMap.put("xydm", xydm);
			zyMap.put("xymc", xymc);
			zyMap.put("zydm", zydm);
			zyMap.put("zymc", zymc);

			list.add(zyMap);
		}

		return list;
	}

	/**
	 * 
	 * @描述:获得全部班级列表(根据拼音)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午03:07:31
	 * @param userBjPyList
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getBjNewList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getBjAllList(userStatus, userName, userDep);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "其他");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}
	public List<HashMap<String, Object>> getXsBjList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getXsBjListOfXsxx(userStatus, userName, userDep,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "其他");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}
	public List<HashMap<String, Object>> getZList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getBjAllList(userStatus, userName, userDep);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "其他");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}

	/**
	 * 
	 * @描述: 获得新全班级列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午03:11:57
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjAllList(String userStatus,
			String userName, String userDep) {

		List<HashMap<String, String>> bjList = dao.getBjNewList(userStatus,
				userName, userDep);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < bjList.size(); i++) {

			HashMap<String, String> map = bjList.get(i);

			String xydm = map.get("xydm");// 学院代码
			String xymc = map.get("xymc");// 学院名称
			String zydm = map.get("zydm");// 专业代码
			String zymc = map.get("zymc");// 专业名称
			String nj = map.get("nj");// 年级
			String bjdm = map.get("bjdm");// 班级代码
			String bjmc = map.get("bjmc");// 班级名称
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// 班级拼音
			
			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("需要配置的生僻字部门：" + bjmc + "(" + bjdm + ")");
			}
			
			HashMap<String, String> bjMap = new HashMap<String, String>();
			bjMap.put("bjpy", bjpy.toUpperCase());
			bjMap.put("xydm", xydm);
			bjMap.put("xymc", xymc);
			bjMap.put("zydm", zydm);
			bjMap.put("zymc", zymc);
			bjMap.put("nj", nj);
			bjMap.put("bjdm", bjdm);
			bjMap.put("bjmc", bjmc);

			list.add(bjMap);
		}

		return list;
	}
	public List<HashMap<String, String>> getXsBjListOfXsxx(String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, String>> bjList = dao.getXsBjList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < bjList.size(); i++) {

			HashMap<String, String> map = bjList.get(i);

			String xydm = map.get("xydm");// 学院代码
			String xymc = map.get("xymc");// 学院名称
			String zydm = map.get("zydm");// 专业代码
			String zymc = map.get("zymc");// 专业名称
			String nj = map.get("nj");// 年级
			String bjdm = map.get("bjdm");// 班级代码
			String bjmc = map.get("bjmc");// 班级名称
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// 班级拼音
			
			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("需要配置的生僻字部门：" + bjmc + "(" + bjdm + ")");
			}
			
			HashMap<String, String> bjMap = new HashMap<String, String>();
			bjMap.put("bjpy", bjpy.toUpperCase());
			bjMap.put("xydm", xydm);
			bjMap.put("xymc", xymc);
			bjMap.put("zydm", zydm);
			bjMap.put("zymc", zymc);
			bjMap.put("nj", nj);
			bjMap.put("bjdm", bjdm);
			bjMap.put("bjmc", bjmc);

			list.add(bjMap);
		}

		return list;
	}

	

	/**
	 * 
	 * @描述: 设置初始化年级学院专业班级[全]
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午03:27:12
	 * @param searchForm
	 * @param rForm
	 * @param user
	 * @param request
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void setNjXyZyBjNewList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		
		// 用户身份
		String userStatus = "";
		// 用户名
		String userName = user.getUserName();
		// 用户部门
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // 使用用户角色
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// 用户角色
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// 用户身份
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}
		// 年级列表
		List<HashMap<String, String>> njTjList = getNjNewTjList();	
		searchForm.setNjNewTjList(njTjList);	
		request.setAttribute("njNewTjList", njTjList);
		
		// 学院列表
		searchForm.setXyNewTjList(getXyNewList("", "", ""));
		request.setAttribute("xyNewTjList", getXyNewList("", "", ""));

		// 专业列表
		searchForm.setZyNewTjList(getNewZyPxList(null, "", "", "",""));
		request.setAttribute("zyNewTjList", getZyNewList(null, "", "", ""));

		// 班级列表
		searchForm.setBjNewTjList(getNewBjPxList(null, "", "", "",""));
		request.setAttribute("bjNewTjList", getBjNewList(null, "", "", ""));
		
	}
	
	public void setNjXyZyBjZxsList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request,boolean sfzxs) throws Exception {

		HttpSession session = request.getSession();
		
		// 用户身份
		String userStatus = "";
		// 用户名
		String userName = user.getUserName();
		// 用户部门
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // 使用用户角色
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// 用户角色
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// 用户身份
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}
		
		
		// 学院列表
		searchForm.setZxsXyTjList(getXsXyList("", "", "",sfzxs));
		request.setAttribute("zxsXyTjList", getXsXyList("", "", "",sfzxs));

		// 专业列表
		searchForm.setZxsZyTjList(getXsZyList(null, "", "", "",sfzxs));
		request.setAttribute("zxsZyTjList", getXsZyList(null, "", "", "",sfzxs));

		// 班级列表
		searchForm.setZxsBjTjList(getXsBjList(null, "", "", "",sfzxs));
		request.setAttribute("zxsBjTjList", getXsBjList(null, "", "", "",sfzxs));
		
	}
	public void setNjXyZyBjFzxsList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request,boolean sfzxs) throws Exception {

		HttpSession session = request.getSession();
		
		// 用户身份
		String userStatus = "";
		// 用户名
		String userName = user.getUserName();
		// 用户部门
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // 使用用户角色
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// 用户角色
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// 用户身份
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}

		// 学院列表
		searchForm.setFzxsXyTjList(getXsXyList("", "", "",sfzxs));
		request.setAttribute("fzxsXyTjList", getXsXyList("", "", "",sfzxs));

		// 专业列表
		searchForm.setFzxsZyTjList(getXsZyList(null, "", "", "",sfzxs));
		request.setAttribute("fzxsZyTjList", getXsZyList(null, "", "", "",sfzxs));

		// 班级列表
		searchForm.setFzxsBjTjList(getXsBjList(null, "", "", "",sfzxs));
		request.setAttribute("fzxsBjTjList", getXsBjList(null, "", "", "",sfzxs));
	}


	/**
	 * 获得专业信息（根据学院）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getZyNewByXy(String[] nj,
			String[] xy, String userStatus, String userName, String userDep) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getZyAllList(userStatus,
				userName, userDep, nj);

		if (xy != null && xy.length > 0) {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xydm = zyMap.get("xydm");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < xy.length; j++) {

					if (xy[j].equalsIgnoreCase(xydm)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		} else {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < PY_BIG.length; j++) {

					if (PY_BIG[j].equalsIgnoreCase(zypy)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		}

		return zyList;
	}


	/**
	 * 获得班级信息（根据拼音，年级，学院，专业）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getBjNewByTj(String py, String[] nj,
			String[] xy, String[] zy, String userStatus, String userName,
			String userDep) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> bjPyList = getBjAllList(userStatus,
				userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// 班级拼音
			String bjdm = bjMap.get("bjdm");// 班级代码
			String bjmc = bjMap.get("bjmc");// 班级名称

			String njdm = bjMap.get("nj");// 年级
			String xydm = bjMap.get("xydm");// 学院
			String zydm = bjMap.get("zydm");// 专业

			// 判断拼音是否符合
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// 判断年级是否符合
			boolean njFlag = true;

			if (nj != null && nj.length > 0) {

				njFlag = false;

				for (int j = 0; j < nj.length; j++) {
					if (njdm.equalsIgnoreCase(nj[j])) {
						njFlag = true;
						break;
					}
				}
			}

			// 判断学院是否符合
			boolean xyFlag = true;

			if (xy != null && xy.length > 0) {

				xyFlag = false;

				for (int j = 0; j < xy.length; j++) {
					if (xydm.equalsIgnoreCase(xy[j])) {
						xyFlag = true;
						break;
					}
				}
			}

			// 判断专业是否符合
			boolean zyFlag = true;

			if (zy != null && zy.length > 0) {

				zyFlag = false;

				for (int j = 0; j < zy.length; j++) {
					if (zydm.equalsIgnoreCase(zy[j])) {
						zyFlag = true;
						break;
					}
				}
			}

			if (pyFlag && njFlag && xyFlag && zyFlag) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}


	/**
	 * 
	 * @描述: 获得专业信息（根据拼音）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 上午08:53:01
	 * @param py
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyNewInfoByPy(String py,
			String userStatus, String userName, String userDep) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getZyAllList(userStatus,
				userName, userDep, null);

		for (int i = 0; i < zyPyList.size(); i++) {

			HashMap<String, String> zyMap = zyPyList.get(i);

			String zypy = zyMap.get("zypy");
			String zydm = zyMap.get("zydm");
			String zymc = zyMap.get("zymc");

			if (zypy.equalsIgnoreCase(py)) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zydm", zydm);
				map.put("zymc", zymc);

				zyList.add(map);

			}
		}

		return zyList;
	}

	/**
	 * 
	 * @描述: 获得班级信息（根据拼音，年级，学院，专业）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 上午08:55:30
	 * @param py
	 * @param nj
	 * @param xy
	 * @param zy
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjNewInfoByTj(String py, String[] nj,
			String[] xy, String[] zy, String userStatus, String userName,
			String userDep) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// 班级列表（带拼音）
		List<HashMap<String, String>> bjPyList = getBjAllList(userStatus,
				userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// 班级拼音
			String bjdm = bjMap.get("bjdm");// 班级代码
			String bjmc = bjMap.get("bjmc");// 班级名称

			String njdm = bjMap.get("nj");// 年级
			String xydm = bjMap.get("xydm");// 学院
			String zydm = bjMap.get("zydm");// 专业

			// 判断拼音是否符合
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// 判断年级是否符合
			boolean njFlag = true;

			if (nj != null && nj.length > 0) {

				njFlag = false;

				for (int j = 0; j < nj.length; j++) {
					if (njdm.equalsIgnoreCase(nj[j])) {
						njFlag = true;
						break;
					}
				}
			}

			// 判断学院是否符合
			boolean xyFlag = true;

			if (xy != null && xy.length > 0) {

				xyFlag = false;

				for (int j = 0; j < xy.length; j++) {
					if (xydm.equalsIgnoreCase(xy[j])) {
						xyFlag = true;
						break;
					}
				}
			}

			// 判断专业是否符合
			boolean zyFlag = true;

			if (zy != null && zy.length > 0) {

				zyFlag = false;

				for (int j = 0; j < zy.length; j++) {
					if (zydm.equalsIgnoreCase(zy[j])) {
						zyFlag = true;
						break;
					}
				}
			}

			if (pyFlag && njFlag && xyFlag && zyFlag) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}
	
	/**
	 * 
	 * @描述: 年级列表[全]
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 上午09:40:43
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getNjNewTjList() {

		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct(nj) from BKS_BJDM order by nj ");
		return DAO.getInstance().getListNotOut(sql.toString(),
				new String[] {});
	}
	
	public static void setNjNewTjList(List<HashMap<String, String>> njNewTjList) {
		SearchService.njNewTjList = njNewTjList;
	}

	public static List<HashMap<String, Object>> getXyNewTjList() {
		return xyNewTjList;
	}

	public static void setXyNewTjList(List<HashMap<String, Object>> xyNewTjList) {
		SearchService.xyNewTjList = xyNewTjList;
	}

	public static List<HashMap<String, Object>> getZyNewTjList() {
		return zyNewTjList;
	}

	public static void setZyNewTjList(List<HashMap<String, Object>> zyNewTjList) {
		SearchService.zyNewTjList = zyNewTjList;
	}

	public static List<HashMap<String, Object>> getBjNewTjList() {
		return bjNewTjList;
	}

	public static void setBjNewTjList(List<HashMap<String, Object>> bjNewTjList) {
		SearchService.bjNewTjList = bjNewTjList;
	}
	
	/**
	 * 
	 * @描述:权限范围
	 * @作者：cq [工号：785]
	 * @日期：2015-1-5 上午11:27:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param xtgwid
	 * @param qxfw
	 * @param bmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getQxfw(User user, String xtgwid,
			String qxfw, String bmdm, String searchTjByUser) { 

		// 用户名
		String userName = user.getUserName();
		// 用户所在部门
		String userDep = user.getUserDep();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		query.append(" case when "+qxfw+" like '%'||"+xtgwid+"||'%' then ");
		query.append(" (case when "+xtgwid+" in (select spgw from xg_xtwh_spgwyh where spyh='"+userName+"') ");
		query.append(" and "+bmdm+" = '"+userDep+"'");
		query.append(" then 'true' else 'false' end) else ");
		query.append(" (case when "+xtgwid+" in (select spgw from xg_xtwh_spgwyh where spyh='"+userName+"') ");
		query.append( searchTjByUser+"  then 'true' else 'false' end)  end ");
		query.append(" ) = 'true'"); 
		
		return query.toString();
	}
	
	
	/**
	 * 
	 * @描述:初始化评奖名称
	 * @作者：cq [工号：785]
	 * @日期：2015-4-15 上午10:16:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxdm
	 * @param xzdm
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getPjxmList(String[] lxdm, String[] xzdm) {

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, Object>> pjpyPjxmList = new ArrayList<HashMap<String, Object>>();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xmmc dm,xmmc mc from (select xmmc,xzdm,LXDM FROM xg_pjpy_new_pjjgb ");
		sql.append("union SELECT xmmc,xzdm,lxdm FROM (select xmmc,xzdm,lxdm from xg_pjpy_new_xmsq a ");
		sql.append("left join xg_pjpy_new_pjxmb b on a.xmdm = b.xmdm)) where xmmc is not null and 1 = 1 ");
		
		//类型代码非空
		if(lxdm != null && lxdm.length >0){
			sql.append(" and lxdm in ('");
			for (int i = 0; i < lxdm.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(lxdm[i]);
			}
			sql.append("')");
		}
		
		//性质代码非空
		if(xzdm != null && xzdm.length >0){
			sql.append(" and xzdm in ('");
			for (int i = 0; i < xzdm.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(xzdm[i]);
			}
			sql.append("')");
		}
		
		sql.append("order by xmmc ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });
		
		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> pjxmList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < list.size(); j++) {

				HashMap<String, String> map = list.get(j);
				
				String dm = map.get("dm");
				String mc = map.get("mc");
				String mcPy = CnToEnUtil.getFirstLetter(mc);

				if (py.equalsIgnoreCase(mcPy)) {

					HashMap<String, String> newPjxm = new HashMap<String, String>();

					if (pjxmList.size() < 3) {

						newPjxm.put("dm", dm);
						newPjxm.put("mc", mc);

						pjxmList.add(newPjxm);

					} else {
						newPjxm.put("dm", "pjxm_" + mcPy);
						newPjxm.put("mc", "其他");

						pjxmList.add(newPjxm);

						break;
					}

				}
			}

			HashMap<String, Object> pjxmMap = new HashMap<String, Object>();
			pjxmMap.put("py", py);
			pjxmMap.put("pjxmList", pjxmList);

			pjpyPjxmList.add(pjxmMap);
		}
			

		return pjpyPjxmList;
	}
	
	public List<HashMap<String, Object>> getXmlxList(String xzdm){
		DAO dao = DAO.getInstance();

		String table = "XG_PJPY_NEW_JXJXMLX";
		if("2".equals(xzdm)){
			table = "xg_pjpy_new_xmlx";
		}

		StringBuilder sql = new StringBuilder();
		sql.append("select xmlxdm dm,xmlxmc mc from ");
		sql.append(table);
		sql.append(" order by xmlxdm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> lxdmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				lxdmTjList.add(map);
			}
		}
		return lxdmTjList;
	}
	
	
	public List<HashMap<String, Object>> getXmxzList(){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select xmxzdm dm,xmxzmc mc from xg_pjpy_new_xmxz order by xmxzdm");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> xmxzTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				xmxzTjList.add(map);
			}
		}
		return xmxzTjList;
	}
	
	
	/**
	 * 获得项目信息（根据类型，性质）
	 * 
	 * @author 
	 * 
	 */
	public List<HashMap<String, String>> getXmInfoByTj(String py, String[] xmlx,
			String[] xmxz) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		// 项目列表（带拼音）
		List<HashMap<String, String>> xmPyList = getNewXmList(xmlx, xmxz);

		for (int i = 0; i < xmPyList.size(); i++) {

			HashMap<String, String> xmMap = xmPyList.get(i);

			String xmpy = xmMap.get("xmpy");// 项目拼音
			String xmdm = xmMap.get("xmdm");// 项目代码
			String xmmc = xmMap.get("xmmc");// 项目名称


			// 判断拼音是否符合
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (xmpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			if (pyFlag ) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xmpy", xmpy);
				map.put("xmdm", xmdm);
				map.put("xmmc", xmmc);

				xmList.add(map);

			}
		}

		return xmList;
	}
	
	
	/**
	 * 获得新评奖项目列表
	 * 
	 * @author
	 * 
	 */
	public List<HashMap<String, String>> getNewXmList(String[] xmlx, String[] xmxz) {

		List<HashMap<String, String>> xmList = dao.getPjxmList(xmlx,xmxz);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xmList.size(); i++) {

			HashMap<String, String> map = xmList.get(i);
			
			String xmdm = map.get("xmdm");	//项目代码
			String xmmc = map.get("xmmc");	//项目名称
			String xmpy = CnToEnUtil.getFirstLetter(xmmc);// 班级拼音
			
			if("-".equalsIgnoreCase(xmpy)){
				xmpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xmdm);
				xmpy = Base.isNull(xmpy) ? "-" : xmpy;
				System.out.println("需要配置的生僻字部门：" + xmmc + "(" + xmdm + ")");
			}
			
			HashMap<String, String> xmMap = new HashMap<String, String>();
			xmMap.put("xmpy", xmpy.toUpperCase());
			xmMap.put("xmdm", xmdm);
			xmMap.put("xmmc", xmmc);

			list.add(xmMap);
		}

		return list;
	}
	
	/**
	 * 
	 * @描述: 获取公寓管理员权限
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-16 下午01:48:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gyglyQx
	 * @param username
	 * @param path
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getGyglyQx(String gyglyQx,String username,String path) {
		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
		if(path!=null && gygly_path.contains(path) && "yes".equalsIgnoreCase(gyglyQx)){
			return " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+username+"')";
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @描述: 心理咨询师权限通用方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-28 下午02:28:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param username
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getSearchQxfwForXlzxs(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xlzx_glyb where zgh = ?");
		String cnt = DAO.getInstance().getOneRs(sql.toString(),new String[]{username}, "cnt");
		if("0".equals(cnt)){
			return " and zgh = '"+username+"' ";
		}else{
			return " ";
		}
	}
	
	/**
	 * 
	 * @描述:初始化书院列表
	 * @作者：taogj[工号：1075]
	 * @日期：2018-3-5 下午02:16:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param searchForm
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void setSymcList(SearchForm searchForm, User user) throws Exception{
		List<HashMap<String, String>> syTjList = getSyList(user);
		searchForm.setSyTjList(syTjList);
	}
	
	private List<HashMap<String, String>> getSyList(User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct a.sydm dm,a.symc mc from xg_xtwh_sydmb a left join fdyxxb b on a.sydm = b.sydm where 1=1 ");
		if("yes".equalsIgnoreCase(user.getSyQx())){
			sql.append(" and b.zgh = '"+user.getUserName()+"'");
		}
		sql.append(" order by a.sydm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	public void setZybjList(SearchForm searchForm, User user) throws Exception{
		List<HashMap<String,Object>> list = getZybjList(user);
		searchForm.setZybjTjList(list);
	}

	private List<HashMap<String, Object>> getZybjList(User user){

		List<HashMap<String,String>> zybjList = getZybjPyList(user.getUserStatus(),user.getUserName(),user.getUserDep());

		List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < zybjList.size(); j++) {

				HashMap<String, String> map = zybjList.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "其他");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("zybjpy", py);
				bjMap.put("zybjList", bjList);

				list.add(bjMap);
			}
		}
		return list;
	}

	private List<HashMap<String,String>> getZybjPyList(String userStatus,String userName,String userDep){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select distinct xydm,xymc,zydm,zymc,nj,bjdm,bjmc from view_njxyzybj_bzr a where 1 = 1 ");
		sql.append(" and (");
		if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			sql.append(" exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" a.xydm = '" + userDep + "' ");
		} else {
			sql.append(" 1=1 ");
		}
		sql.append(" ) ");
		List<HashMap<String,String>> zybjList = dao.getList(sql.toString(),new String[]{},new String[] { "xydm", "xymc", "zydm", "zymc", "nj", "bjdm",
				"bjmc" });

		for (int i = 0; i < zybjList.size(); i++) {

			HashMap<String, String> map = zybjList.get(i);

			String bjdm = map.get("bjdm");// 班级代码
			String bjmc = map.get("bjmc");// 班级名称
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// 班级拼音

			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("需要配置的生僻字部门：" + bjmc + "(" + bjdm + ")");
			}
			zybjList.get(i).put("bjpy", bjpy.toUpperCase());
		}
		return zybjList;
	}

	/**
	 * 获取行政班级
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 */
	private List<HashMap<String,String>> getXzbjPyList(String userStatus,String userName,String userDep){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select distinct * from (select a.sydm,b.symc,a.bjdm,c.bjmc,d.zgh,d.xm from  XG_XTWH_SYBJGLB a ");
		sql.append(" left join XG_XTWH_SYDMB b on a.sydm = b.sydm left join T_BJDMB c ");
		sql.append(" on a.bjdm = c.bjdm left join VIEW_FDYBBJ d on a.bjdm = d.bjdm where c.bjmc is not null)  where 1=1  ");
//		if ("fdy".equalsIgnoreCase(userStatus)) {
//			// 辅导员
//			sql.append(" and b.zgh = '"+userName+"' ) ");
//		} else if ("sy".equalsIgnoreCase(userStatus)) {
//			sql.append(" and a.sydm = '" + userDep + "' ");
//		} else {
//			sql.append("  ");
//		}
		List<HashMap<String,String>> xzbjList = dao.getList(sql.toString(),new String[]{},new String[] { "sydm", "symc", "bjdm", "bjmc", "zgh", "xm" });

		for (int i = 0; i < xzbjList.size(); i++) {

			HashMap<String, String> map = xzbjList.get(i);

			String bjdm = map.get("bjdm");// 班级代码
			String bjmc = map.get("bjmc");// 班级名称
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// 班级拼音

			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("需要配置的生僻字部门：" + bjmc + "(" + bjdm + ")");
			}
			xzbjList.get(i).put("bjpy", bjpy.toUpperCase());
		}
		return xzbjList;
	}
}