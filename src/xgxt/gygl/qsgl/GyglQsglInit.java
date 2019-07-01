package xgxt.gygl.qsgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.SearchUtils;

public class GyglQsglInit {

	/**
	 * 自动分配_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getQszdfpRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 分配对象
		String fpdx = model.getFpdx();

		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			initForXy(rForm, model, request);
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			initForNjXy(rForm, model, request);
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			initForNjZy(rForm, model, request);
		} else if ("bj".equalsIgnoreCase(fpdx)) {// 分配对象为班级
			initForBj(rForm, model, request);
		}
	}
	
	/**
	 * 分配对象学院
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initForXy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qszdfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_zdfp.do";
		// 查询类型
		String searchType = "xy";
		// 输出字段
		String[] colList = new String[] { "xydm", "xymc", "bmrs", "fpqss",
				"kzrcws", "xfprs" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "xy");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "4";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 分配对象年级+学院
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initForNjXy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qszdfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_zdfp.do";
		// 查询类型
		String searchType = "njxy";
		// 输出字段
		String[] colList = new String[] { "nj||'!!@@!!'||xydm", "nj", "xymc",
				"manNum", "manZqss", "manZcws", "womanNum", "womanZqss",
				"womanZcws" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "njxy");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "5";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 分配对象年级+专业
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initForNjZy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qszdfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_zdfp.do";
		// 查询类型
		String searchType = "njzy";
		// 输出字段
		String[] colList = new String[] { "nj||'!!@@!!'||zydm", "nj", "xymc",
				"zymc", "bmrs", "fpqss", "kzrcws", "xfprs" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "njzy");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "6";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 分配对象班级
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initForBj(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qszdfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_zdfp.do";
		// 查询类型
		String searchType = "bj";
		// 输出字段
		String[] colList = new String[] { "bjdm", "nj", "xymc", "zymc", "bjmc",
				"bmrs", "fpqss", "kzrcws", "xfprs" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "bj");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, String>> getTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;

		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院

			colListCN = new String[] { "主键",Base.YXPZXY_KEY+"名称", Base.YXPZXY_KEY+"人数", "分配寝室数", "可住人床位数" };

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院

			colListCN = new String[] { "主键", "年级", Base.YXPZXY_KEY+"名称", Base.YXPZXY_KEY+"人数", "分配寝室数",
					"可住人床位数" };

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业

			colListCN = new String[] { "主键", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "专业人数",
					"分配寝室数", "可住人床位数" };

		} else if ("bj".equalsIgnoreCase(fpdx)) {// 分配对象为班级

			colListCN = new String[] { "主键", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称",
					"班级人数", "分配寝室数", "可住人床位数" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

	/**
	 * 自动分配(结果)_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getQszdfpjgRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qszdfpjg";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_zdfpjg.do";
		// 输出字段
		String[] colList = new String[] { "ssbh", "ldmc", "cs", "qsh", "cws",
				"xb", "kfhz", "fpbm" };
		// 查询类型
		String searchType = model.getFpdx();
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_gygl_qsfp", colList, null);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String[] qtzd = new String[] { "fpdx" };
		String[] qtzdz = new String[] { model.getFpdx() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
	}
	
	/**
	 * 手动分配_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getQssdfpRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		
		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qssdfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_sdfp.do";
		// 输出字段
		String[] colList = new String[] { "ssbh", "ldmc", "cs", "qsh", "cws",
				"xb", "kfhz", "fpbm","sjly" };
		// 查询类型
		String searchType = model.getFpdx();
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_gygl_qsfp", colList, null);
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "10";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String[] qtzd = new String[] { "fpdx" };
		String[] qtzdz = new String[] { model.getFpdx() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		
	}
	
	/**
	 * 分配结果_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getQsfpjgRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		
		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qsfpjg";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_fpjg.do";
		// 输出字段
		String[] colList = new String[] { "ssbh", "ldmc", "cs", "qsh", "cws",
				"xb", "kfhz", "fpbm","sjly" };
		// 查询类型
		String searchType = model.getFpdx();
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_gygl_qsfp", colList, null);
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "10";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String[] qtzd = new String[] { "fpdx" };
		String[] qtzdz = new String[] { model.getFpdx() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		
	}

	// ====================2011.6.23 edit by luojw ========================
	// ====================ps:因领导需求进行修改============================
	/**
	 * 寝室分配_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getQsfpRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 分配对象
		String fpdx = model.getFpdx();

		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			initQsfpForXy(rForm, model, request);
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			initQsfpForNjXy(rForm, model, request);
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			initQsfpForNjZy(rForm, model, request);
		} else if ("bj".equalsIgnoreCase(fpdx)) {// 分配对象为班级
			initQsfpForBj(rForm, model, request);
		}
	}
	
	/**
	 * 分配对象学院
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initQsfpForXy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qsfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_qsfp.do";
		// 查询类型
		String searchType = "xy";
		// 输出字段
		String[] colList = new String[] { "xydm", "xymc", "bmrs", "manNum",
				"manZqss", "manZcws", "womanNum", "womanZqss", "womanZcws" };
		// 表头
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "xy");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 分配对象年级+学院
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initQsfpForNjXy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qsfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_qsfp.do";
		// 查询类型
		String searchType = "njxy";
		// 输出字段
		String[] colList = new String[] { "nj||'!!@@!!'||xydm", "nj", "xymc",
				"bmrs", "manNum", "manZqss", "manZcws", "womanNum",
				"womanZqss", "womanZcws" };
		// 表头
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "njxy");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "9";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 分配对象年级+专业
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initQsfpForNjZy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qsfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_qsfp.do";
		// 查询类型
		String searchType = "njzy";
		// 输出字段
		String[] colList = new String[] { "nj||'!!@@!!'||zydm", "nj", "zymc",
				"bmrs", "manNum", "manZqss", "manZcws", "womanNum",
				"womanZqss", "womanZcws" };
		// 表头
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "njzy");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "9";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 分配对象班级
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initQsfpForBj(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "qsfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_qsgl_qsfp.do";
		// 查询类型
		String searchType = "bj";
		// 输出字段
		String[] colList = new String[] { "bjdm", "bjmc", "bmrs", "manNum",
				"manZqss", "manZcws", "womanNum", "womanZqss", "womanZcws" };
		// 表头
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "bj");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, String>> getQsfpTopTr(String[] colList,
			String fpdx) {

		String[] colListCN = null;

		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院

			colListCN = new String[] { "主键", Base.YXPZXY_KEY+"名称", "总人数", "男生数", "已分配男寝室",
					"可入住男生数", "女生数", "已分配女寝室", "可入住女生数" };

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院

			colListCN = new String[] { "主键", "年级", Base.YXPZXY_KEY+"名称", "总人数", "男生数",
					"已分配男寝室", "可入住男生数", "女生数", "已分配女寝室", "可入住女生数" };

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业

			colListCN = new String[] { "主键", "年级", "专业名称", "总人数", "男生数",
					"已分配男寝室", "可入住男生数", "女生数", "已分配女寝室", "可入住女生数" };

		} else if ("bj".equalsIgnoreCase(fpdx)) {// 分配对象为班级

			colListCN = new String[] { "主键", "班级名称", "总人数", "男生数", "已分配男寝室",
					"可入住男生数", "女生数", "已分配女寝室", "可入住女生数" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
}
