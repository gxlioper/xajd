package xgxt.comm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchDAO;
import xgxt.comm.xml.XMLReader;
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.studentInfo.ynys.XsxxYnysService;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyService;

import common.Globals;
import common.GlobalsVariable;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 页面初始化列表类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class CommList {

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		// 功能模块
		String gnmk = rForm.getGnmk();

		// =====================通用=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm", "zzmm","yjbm" }, request);// 自定义(目前：部门代码,政治面貌)
		setCommOptionList(model, rForm, request);
		// =====================通用OVER=============================

		// =====================各个功能模块=============================
		if ("xtwh".equalsIgnoreCase(gnmk)) {// 系统维护
			setXtwhOptionList(model, rForm, request);
		} else if ("xszz".equalsIgnoreCase(gnmk)) {// 学生资助
			setXszzOptionList(model, rForm, request);
		} else if ("gygl".equalsIgnoreCase(gnmk)) {// 公寓管理
			setGyglOptionList(model, rForm, request);
		} else if ("pjpy".equalsIgnoreCase(gnmk)) {// 评奖评优
			setPjpyOptionList(model, rForm, request);
		} else if ("xsxx".equalsIgnoreCase(gnmk)) {// 学生信息
			setXsxxOptionList(model, rForm, request);
		} else if ("jxgl".equalsIgnoreCase(gnmk)) {// 军训管理
			setJxglOptionList(model, rForm, request);
		}
		// =====================各个功能模块OVER=============================
	}
	
	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author 
	 * @throws Exception
	 */
	public void setAllList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		// 功能模块
		String gnmk = rForm.getGnmk();

		// =====================通用=============================
		FormModleCommon.setAllNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm", "zzmm" }, request);// 自定义(目前：部门代码,政治面貌)
		setCommOptionList(model, rForm, request);
		// =====================通用OVER=============================

		// =====================各个功能模块=============================
		if ("xtwh".equalsIgnoreCase(gnmk)) {// 系统维护
			setXtwhOptionList(model, rForm, request);
		} else if ("xszz".equalsIgnoreCase(gnmk)) {// 学生资助
			setXszzOptionList(model, rForm, request);
		} else if ("gygl".equalsIgnoreCase(gnmk)) {// 公寓管理
			setGyglOptionList(model, rForm, request);
		} else if ("pjpy".equalsIgnoreCase(gnmk)) {// 评奖评优
			setPjpyOptionList(model, rForm, request);
		} else if ("xsxx".equalsIgnoreCase(gnmk)) {// 学生信息
			setXsxxOptionList(model, rForm, request);
		} else if ("jxgl".equalsIgnoreCase(gnmk)) {// 军训管理
			setJxglOptionList(model, rForm, request);
		}
		// =====================各个功能模块OVER=============================
	}

	/**
	 * 页面下拉列表初始化（通用）
	 * 
	 * @author luojw
	 */
	private void setCommOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		// 性别
		List<HashMap<String, String>> xbList = getCommSelectList("xblx");
		request.setAttribute("xbList", xbList);

		// 是否
		List<HashMap<String, String>> sfList = getCommSelectList("sflx");
		request.setAttribute("sfList", sfList);
	}

	/**
	 * 页面下拉列表初始化（系统维护）
	 * 
	 * @author luojw
	 */
	private void setXtwhOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// 功能模块
		String gnmk = "xtwh";
		// 菜单
		String menu = rForm.getMenu();

		if ("xzzq".equalsIgnoreCase(menu)) {// 下载专区

			// 文件类型列表
			List<HashMap<String, String>> filelxList = dao.getWhList(
					"xg_xtwh_szzqlxb", "dm", "mc", "", "", "");
			request.setAttribute("filelxList", filelxList);

			// 所属类型列表
			List<HashMap<String, String>> sslxList = dao.getWhList(
					"xg_xtwh_szzqssb", "dm", "mc", "", "", "");
			request.setAttribute("sslxList", sslxList);

			// 下载对象列表
			List<HashMap<String, String>> xzdzList = getSelectList(gnmk, "xzdx");
			request.setAttribute("xzdzList", xzdzList);

		} else if ("sydc".equalsIgnoreCase(menu)) {// 首页调查

			// 是否启用
			List<HashMap<String, String>> sfqyList = getCommSelectList("sflx");
			request.setAttribute("sfqyList", sfqyList);
		} else if("qxgl".equalsIgnoreCase(menu)){
			List<HashMap<String, String>> yhzList 
				= dao.getList("select distinct zdm dm,zmc mc from yhzb where zdm<>'6727'",
						new String[] {}, new String[] { "dm", "mc" });
			request.setAttribute("yhzList", yhzList);
			
			List<HashMap<String, String>> dwList 
			= dao.getList("select distinct dwdm dm,dwmc mc from bks_dwdmb order by dwdm ",
					new String[] {}, new String[] { "dm", "mc" });
			request.setAttribute("dwList", dwList);
		}
	}

	/**
	 * 页面下拉列表初始化（学生资助）
	 * 
	 * @author luojw
	 */
	private void setXszzOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// 功能模块
		String gnmk = "xszz";
		// 菜单
		String menu = rForm.getMenu();

		if ("xmtj".equalsIgnoreCase(menu)) {// 资助统计
			// 项目类别列表
			List<HashMap<String, String>> xmlbList = getSelectList(gnmk, "xmlb");
			request.setAttribute("xmlbList", xmlbList);

			// 所有项目列表
			List<HashMap<String, String>> xmList = dao.getWhList("xszz_zzxmb",
					"xmdm", "xmmc", "", "", "");
			request.setAttribute("xmList", xmList);
		} else if ("zdsz".equalsIgnoreCase(menu)) {// 字段设置

			// 所有项目列表
			List<HashMap<String, String>> xmList = dao.getWhList("xszz_zzxmb",
					"xmdm", "xmmc", "", "mrxm", "是");

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "ws");
			map.put("mc", "其他");

			xmList.add(map);

			request.setAttribute("xmList", xmList);
		}
	}

	/**
	 * 页面下拉列表初始化（公寓管理）
	 * 
	 * @author luojw
	 */
	private void setGyglOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		GyglTyService service = new GyglTyService();
		GyglTyDAO gyDao = new GyglTyDAO();
		DAO dao = DAO.getInstance();

		// 学校代码
		String xxdm = Base.xxdm;
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = rForm.getMenu();
		// =================判断是否公寓管理员==========================
		// 用户名
		String userName = rForm.getUserName();
		// 楼栋代码
		String lddm = model.getLddm();
		lddm = Base.isNull(lddm) ? model.getQueryequals_lddm() : lddm;
		// 层数
		String cs = model.getCs();
		cs = Base.isNull(cs) ? model.getQueryequals_cs() : cs;

		// ====================通用==========================
		// 校区
		List<HashMap<String, String>> xqdmList = dao.getWhList("dm_zju_xq",
				"dm", "xqmc", "", "", "");
		request.setAttribute("xqdmList", xqdmList);

		// 园区
		List<HashMap<String, String>> yqList = dao.getWhList("yqdmb", "yqdm",
				"yqmc", "", "", "");
		request.setAttribute("yqList", yqList);

		// 楼栋
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
			List<HashMap<String, String>> ldList = dao.getWhList(
					"view_bjlh_ldxx", "lddm", "yqmc||'/'||ldmc", "", "", "");
			request.setAttribute("ldList", ldList);
		} else {
			List<HashMap<String, String>> ldList = dao.getWhList("sslddmb",
					"lddm", "ldmc", "", "", "");
			request.setAttribute("ldList", ldList);
		}

		// 是否公寓辅导员
		if (service.isGyfdy(userName)) {
			service.initGyglList(userName, request);
		}

		// 层数
		List<HashMap<String, String>> csList = gyDao.getCsList(lddm);
		request.setAttribute("csList", csList);

		// 寝室
		List<HashMap<String, String>> qsList = gyDao.getQsList(lddm, cs, "");
		request.setAttribute("qsList", qsList);

		// 周次
		List<HashMap<String, String>> zcList = CommService.getZcList(
				"gygl_wsjc_csszb", "zgzc");
		request.setAttribute("zcList", zcList);
		// ====================通用OVER==========================

		// ====================新版本 通用==========================
		String edition = XMLReader.getFlowControl("gygl", "edition");

		if ("new".equalsIgnoreCase(edition)) {
			String sql = "select lddm,ldmc from sslddmb order by lddm";
			List<HashMap<String, String>> ldList = dao.getList(sql,
					new String[] {}, new String[] { "lddm", "ldmc" });
			request.setAttribute("ldList", ldList);

			if ("qszdfp".equalsIgnoreCase(menu)) {// 寝室自动分配

				SearchDAO searchDAO = new SearchDAO();

				List<HashMap<String, String>> ldQsList = searchDAO
						.getLdForWfpQssList(null, null, null, null, null);

				request.setAttribute("ldQsList", ldQsList);
			} else if ("qsfp".equalsIgnoreCase(menu)) {// 寝室分配
//				SearchDAO searchDAO = new SearchDAO();
//
//				List<HashMap<String, String>> ldQsList = searchDAO
//						.getLdForWfpQssList(null, null, rForm.getGyglyQx(), userName, null);
//
//				request.setAttribute("ldQsList", ldQsList);
			}
		}
		// ====================新版本 通用 end==========================
		
		if ("wsjc_cssz".equalsIgnoreCase(menu)) {// 卫生检查_参数设置

			// 关联等级
			List<HashMap<String, String>> gldjTopList = getSelectList(gnmk,
					"gldjTop");
			request.setAttribute("gldjTopList", gldjTopList);

			// 关联分数
			List<HashMap<String, String>> glfsTopList = getSelectList(gnmk,
					"glfsTop");
			request.setAttribute("glfsTopList", glfsTopList);

			// 不关联分数
			List<HashMap<String, String>> bglfsTopList = getSelectList(gnmk,
					"bglfsTop");
			request.setAttribute("bglfsTopList", bglfsTopList);
		} else if ("wsjc_fs".equalsIgnoreCase(menu)) {// 卫生检查_分数

			// 检查部门
			List<HashMap<String, String>> jcbmList = dao.getWhList("gywsjcbmb",
					"bmdm", "bmmc", "", "", "");
			request.setAttribute("jcbmList", jcbmList);

			// 统计方式
			List<HashMap<String, String>> tjfsList = getSelectList(gnmk,
					"wsf_tjfs");
			request.setAttribute("tjfsList", tjfsList);

			// 统计范围
			List<HashMap<String, String>> tjfwList = getSelectList(gnmk,
					"wsf_tjfw");
			request.setAttribute("tjfwList", tjfwList);
		}

	}

	/**
	 * 页面下拉列表初始化（评奖评优）
	 * 
	 * @author luojw
	 */
	private void setPjpyOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// 学校代码
		String xxdm = Base.xxdm;
		// 功能模块
		String gnmk = "pjpy";
		// 菜单
		String menu = rForm.getMenu();
		// 特殊sql
		String sql = "";

		PjpyCommService pjpyService = new PjpyCommService();

		// 项目性质列表
		List<HashMap<String, String>> pjpyXmxzList = pjpyService.getXmxzList();
		request.setAttribute("xmxzList", pjpyXmxzList);

		// 项目范围列表
		List<HashMap<String, String>> pjpyXmfwList = pjpyService.getXmfwList();
		request.setAttribute("xmfwList", pjpyXmfwList);

		// 项目类型列表
		List<HashMap<String, String>> pjpyXmlxList = getSelectList(gnmk, "xmlx");
		request.setAttribute("xmlxList", pjpyXmlxList);
		
		if ("pjjbsz".equalsIgnoreCase(menu)) {// 评奖基本设置

			// 人数分配方式
			List<HashMap<String, String>> rsfpfsList = getSelectList(gnmk,
					"rsfpfs");
			request.setAttribute("rsfpfsList", rsfpfsList);
			request.setAttribute("fpfsNum", rsfpfsList.size());

			sql = "select tjdm,tjms,tjmc,sfqy from xg_pjpy_pjtjkb order by tjdm";
			// 评奖条件库
			List<HashMap<String, String>> tjkList = dao.getList(sql,
					new String[] {}, new String[] { "tjdm", "tjms", "tjmc",
							"sfqy" });
			if (tjkList != null && tjkList.size() > 0) {

				int tjkNum = tjkList.size();
				int nullNum = 4 - tjkNum % 4;
				for (int i = 0; i < nullNum; i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					tjkList.add(map);
				}

				int rowNum = tjkList.size() / 4;
				if (rowNum < 5) {
					for (int i = 0; i < (5 - rowNum) * 4; i++) {
						HashMap<String, String> map = new HashMap<String, String>();
						tjkList.add(map);
					}
				}
				request.setAttribute("tjkNum", tjkNum);
				request.setAttribute("tjkList", tjkList);
			}
		} else if ("pjlcsz".equalsIgnoreCase(menu)) {// 评奖流程设置

		}
	}

	/**
	 * 页面下拉列表初始化（学生信息）
	 * 
	 * @author luojw
	 */
	private void setXsxxOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// 学校代码
		String xxdm = Base.xxdm;
		// 功能模块
		String gnmk = "xsxx";
		// 菜单
		String menu = rForm.getMenu();

		if ("jcsjsz".equalsIgnoreCase(menu)) {// 数据源_基础数据设置

			// 数据源列表
			List<HashMap<String, String>> jbszSjyList = getSelectList(gnmk,
					"jbsz_sjy");
			request.setAttribute("jbszSjyList", jbszSjyList);

			// 录入限制列表
			List<HashMap<String, String>> jbszLrxzList = getSelectList(gnmk,
					"jbsz_lrxz");
			request.setAttribute("jbszLrxzList", jbszLrxzList);

			// 为空限制列表
			List<HashMap<String, String>> jbszWkxzList = getSelectList(gnmk,
					"jbsz_wkxz");
			request.setAttribute("jbszWkxzList", jbszWkxzList);

			// 录入形式列表
			List<HashMap<String, String>> jbszLrxsList = getSelectList(gnmk,
					"jbsz_lrxs");
			request.setAttribute("jbszLrxsList", jbszLrxsList);
		} else if ("zxsxx".equalsIgnoreCase(menu)) {//在校生信息
			try {
				FormModleCommon.requestSetList(new String[] { "mz" }, request);
				CommanForm dataSearchForm =new CommanForm();
				XsxxYnysService ynysService = new XsxxYnysService();
				XsxxglService service = new XsxxglService();
				StuInfoDAO stuInfoDao = new StuInfoDAO();
				HashMap<String, String> map = request.getAttribute("rs") != null ? (HashMap<String, String>)
						request.getAttribute("rs") : new HashMap<String, String>();
				request.setAttribute("ssList", ynysService.getSsList());//省列表
		        request.setAttribute("jgshiList", ynysService.getShiList(map.get("jgshen") == null ? "9999999" : map.get("jgshen")).get("shiList"));//市列表
				request.setAttribute("jgxianList",  ynysService.getShiListNew(map.get("jgshi") == null ? ( map.get("jgshen") != null ? map.get("jgshen").substring(0, 2) :"9999999"): map.get("jgshi")).get("xianList"));//县列表
				
				request.setAttribute("sydshiList", ynysService.getShiList(map.get("syds") == null ? "9999999" : map.get("syds")).get("shiList"));//市列表
				request.setAttribute("sydxianList",  ynysService.getShiListNew(map.get("sydshi") == null ? (map.get("syds") !=null ? map.get("syds").substring(0, 2) :"9999999"): map.get("sydshi")).get("xianList"));//县列表
					
				request.setAttribute("hkshiList", ynysService.getShiList(map.get("hkshen") == null ? "9999999" : map.get("hkshen")).get("shiList"));//市列表
				request.setAttribute("hkxianList",  ynysService.getShiListNew(map.get("hkshi") == null ? ( map.get("hkshen")!=null?map.get("hkshen").substring(0, 2) :"9999999"): map.get("hkshi")).get("xianList"));//县列表
				
				request.setAttribute("xjztList", stuInfoDao.getXjztList());//学籍状态
				request.setAttribute("pyccList", service.getList(GlobalsVariable.XTWH_PYCC_LIST));//培养层次
				request.setAttribute("kslbList", service.getList(GlobalsVariable.XTWH_KSLB_LIST));//考生类别
				request.setAttribute("rxfsList", service.getList(GlobalsVariable.XTWH_RXFS_LIST));//入学方式
				request.setAttribute("pyfsList", service.getList(GlobalsVariable.XTWH_PYFS_LIST));//培养方式
				request.setAttribute("jtgxList", service.getJtgxList());//家庭关系 
				request.setAttribute("yhmcList", service.getList(GlobalsVariable.XTWH_YH_LIST));//银行列表
				
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}// 自定义(目前：部门代码,政治面貌)
		}
	}

	/**
	 * 页面下拉列表初始化（军训管理）
	 * 
	 * @author luojw
	 */
	private void setJxglOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// 学校代码
		String xxdm = Base.xxdm;
		// 功能模块
		String gnmk = "jxgl";
		// 菜单
		String menu = rForm.getMenu();

		if ("jxbz".equalsIgnoreCase(menu)) {// 军训编制

			// 军训编制等级列表
			//List<HashMap<String, String>> jxbzdjList = dao.getWhList("jxjzdj",
			//		"jzdm", "jzmc", "", "", "");
			//request.setAttribute("jxbzdjList", jxbzdjList);
		}
	}
	
	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String gnmk, String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if (Base.isNull(gnmk)) {// 公用
			return getCommSelectList(lx);
		} else if ("xszz".equalsIgnoreCase(gnmk)) {// 学生资助
			return getXszzSelectList(lx);
		} else if ("gygl".equalsIgnoreCase(gnmk)) {// 公寓管理
			return getGyglSelectList(lx);
		} else if ("pjpy".equalsIgnoreCase(gnmk)) {// 评奖评优
			return getPjpySelectList(lx);
		} else if ("xtwh".equalsIgnoreCase(gnmk)) {// 系统维护
			return getXtwhSelectList(lx);
		} else if ("xsxx".equalsIgnoreCase(gnmk)) {// 学生信息
			return getXsxxSelectList(lx);
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 获得无需维护下拉框值（公用）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCommSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "男", "女" };
			mc = new String[] { "男", "女" };
		} else if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 获得无需维护下拉框值（学生资助）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xmlb".equalsIgnoreCase(lx)) {
			dm = new String[] { "奖学金", "助学金", "困难补助", "其他" };
			mc = new String[] { "奖学金", "助学金", "困难补助", "其他" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 获得无需维护下拉框值（公寓管理）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGyglSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("gldjTop".equalsIgnoreCase(lx)) {// 卫生检查_参数设置_分数关联等级top
			dm = new String[] { "", "卫生分下限", "卫生分上限", "对应等级" };
			mc = new String[] { "", "卫生分下限", "卫生分上限", "对应等级" };
		} else if ("glfsTop".equalsIgnoreCase(lx)) {// 卫生检查_参数设置_等级关联分数top
			dm = new String[] { "", "卫生分等级", "对应分数" };
			mc = new String[] { "", "卫生分等级", "对应分数" };
		} else if ("bglfsTop".equalsIgnoreCase(lx)) {// 卫生检查_参数设置_等级不关联分数top
			dm = new String[] { "", "卫生分等级" };
			mc = new String[] { "", "卫生分等级" };
		} else if ("wsf_tjfs".equalsIgnoreCase(lx)) {// 卫生检查_卫生分统计方式
			dm = new String[] { "qs", "xs" };
			mc = new String[] { "寝室", "学生" };
		} else if ("wsf_tjfw".equalsIgnoreCase(lx)) {// 卫生检查_卫生分统计范围
			dm = new String[] { "nj", "xy", "zy", "bj" };
			mc = new String[] { "年级", Base.YXPZXY_KEY, "专业", "班级" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 获得无需维护下拉框值（评奖评优）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPjpySelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("rsfpfs".equalsIgnoreCase(lx)) {// 评奖评优_基本设置_人数分配方式
//			dm = new String[] { "xx", "xxxy", "xxfpxy" };
//			mc = new String[] { "仅学校设置", "学校,学院都可设置", "学校设置学院，学院设置下属专业班级" };
			
			dm = new String[] { "xx" };
			mc = new String[] { "仅学校设置" };
		} else if ("xmlx".equalsIgnoreCase(lx)) {// 评奖评优_项目类型
			dm = new String[] { "01", "02" };
			mc = new String[] { "奖学金", "荣誉称号" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 获得无需维护下拉框值（系统维护）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXtwhSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xzdx".equalsIgnoreCase(lx)) {// 首页设置_下载对象
			dm = new String[] { "全部", "老师", "学生" };
			mc = new String[] { "全部", "老师", "学生" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 获得无需维护下拉框值（学生信息）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("jbsz_sjy".equalsIgnoreCase(lx)) {// 基本设置_数据源
			dm = new String[] { "学工", "接口" };
			mc = new String[] { "学工", "接口" };
		} else if ("jbsz_lrxz".equalsIgnoreCase(lx)) {// 基本设置_录入限制
			dm = new String[] { "无限制", "整数限制", "数字限制(可带小数)", "英数字限制", "中文限制" };
			mc = new String[] { "无限制", "整数限制", "数字限制(可带小数)", "英数字限制", "中文限制" };
		} else if ("jbsz_wkxz".equalsIgnoreCase(lx)) {// 基本设置_为空限制
			dm = new String[] { "可以为空", "不可为空" };
			mc = new String[] { "可以为空", "不可为空" };
		} else if ("jbsz_wkxz".equalsIgnoreCase(lx)) {// 基本设置_为空限制
			dm = new String[] { "可以为空", "不可为空" };
			mc = new String[] { "可以为空", "不可为空" };
		} else if ("jbsz_lrxs".equalsIgnoreCase(lx)) {// 基本设置_录入形式
			dm = new String[] { "输入框","时间格式", "文本域", "下拉列表", "单选框" };
			mc = new String[] { "输入框","时间格式", "文本域", "下拉列表", "单选框" };
		}

		return dao.arrayToList(dm, mc);
	}
}
