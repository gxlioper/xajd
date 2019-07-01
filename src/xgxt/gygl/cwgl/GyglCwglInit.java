package xgxt.gygl.cwgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.utils.SearchUtils;

public class GyglCwglInit {
	/**
	 * 自动分配_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getQszdfpRForm(RequestForm rForm, GyglCwglForm model,
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
	public void initForXy(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "cwzdfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_cwgl_zdfp.do";
		// 查询类型
		String searchType = "xy";
		// 输出字段
		String[] colList = new String[] { "xydm", "xymc", "bmrs", "yzrcws",
				"wzrcws" };
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
	public void initForNjXy(RequestForm rForm, GyglCwglForm model,
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
				"bmrs", "yzrcws","wzrcws" };
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
	public void initForNjZy(RequestForm rForm, GyglCwglForm model,
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
				"zymc", "bmrs","yzrcws","wzrcws"};
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
	public void initForBj(RequestForm rForm, GyglCwglForm model,
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
				"bmrs", "yzrcws","wzrcws" };
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
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院

			colListCN = new String[] { "主键", Base.YXPZXY_KEY+"名称", "部门人数", "已分配床位数", "未分配床位数" };

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院

			colListCN = new String[] { "主键", "年级", Base.YXPZXY_KEY+"名称", "部门人数", "已分配床位数",
					"未分配床位数" };

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业

			colListCN = new String[] { "主键", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "部门人数",
					"已分配床位数", "未分配床位数" };

		} else if ("bj".equalsIgnoreCase(fpdx)) {// 分配对象为班级

			colListCN = new String[] { "主键", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称",
					"部门人数", "已分配床位数", "未分配床位数" };
		
		} else if ("sdcw".equalsIgnoreCase(fpdx)) {
			
			colListCN = new String[] {"禁用", "主键", "学号", "楼栋", "层数", "寝室号", "寝室性别", "床位号",
					"入住学生", "寝室分配对象", "入住状态" };
		
		} else if ("axsfp".equalsIgnoreCase(fpdx)) {
			
			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级", "班级" };
		
		} else if ("xscwfp".equalsIgnoreCase(fpdx)) {
			
			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级", "班级" };
		
		} else if("cwfpxxcx".equalsIgnoreCase(fpdx)){
			
			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级",Base.YXPZXY_KEY,
					"专业", "班级","宿舍编号","楼栋","层数","寝室号","床位号","入住时间" };
			
		}
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getCwjgTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "主键", "学号", "姓名", "性别", Base.YXPZXY_KEY,
					"宿舍编号","楼栋","层数","寝室号","床位号","入住日期"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级",Base.YXPZXY_KEY,
					"宿舍编号","楼栋","层数","寝室号","床位号","入住日期"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级",Base.YXPZXY_KEY,
					"专业","宿舍编号","楼栋","层数","寝室号","床位号","入住日期"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级",Base.YXPZXY_KEY,
						"专业", "班级","宿舍编号","楼栋","层数","寝室号","床位号","入住日期"};
		}
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getCwtjTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键",Base.YXPZXY_KEY,"部门人数(男/女)","分配寝室数(男/女)","可住人床位(男/女)","未住人床位(男/女)","已住人床位(男/女)"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键","年级",Base.YXPZXY_KEY,"部门人数(男/女)","分配寝室数(男/女)","可住人床位(男/女)","未住人床位(男/女)","已住人床位(男/女)"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键","年级","专业","部门人数(男/女)","分配寝室数(男/女)","可住人床位(男/女)","未住人床位(男/女)","已住人床位(男/女)"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键","班级","部门人数(男/女)","分配寝室数(男/女)","可住人床位(男/女)","未住人床位(男/女)","已住人床位(男/女)"};
		}
		
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 分配对象学院
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniCwsdfpRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "cwsdfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_cwgl_sdfp.do";
		// 查询类型
		String searchType = model.getFpdx();
		// 输出字段
		String[] colList = new String[] {"disabled","pkValue","xh","ldmc","cs","qsh","xb","cwh","rzxs","qsfpdx","cwfp"};
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "sdcw");
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
		String startNum = "3";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(colList.length-3);
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
	 * 按学生分配
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniAxsfpRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "axsfpcw";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_cwgl_sdfp.do";
		// 查询类型
		String searchType = "axsfpcw";
		// 输出字段
		String[] colList = new String[] {"pkValue","xh","xm","xb","nj","bjmc"};
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "axsfp");
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
		String showNum =String.valueOf(colList.length-1);
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
	 * 按学生分配
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniXscwfpRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "xscwfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_cwgl_sdfp.do";
		// 查询类型
		String searchType = "xscwfp";
		// 输出字段
		String[] colList = new String[] {"xh","xm"};
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "xscwfp");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(colList.length);
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
	 * 按学生分配
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniSdfpcwRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "xscwfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_cwgl_sdfp.do";
		// 查询类型
		String searchType = "xscwfp";
		// 输出字段
		String[] colList = new String[] {"pkValue","xh","xm","xb","nj","bjmc"};
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "xscwfp");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(colList.length);
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
	 * @author qlj
	 * 
	 */
	public void getCwfpxxcxRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "cwfpjg";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_cwgl_fpjg.do";
		//查询对象
		String searchType = model.getFpdx(); 
		String fpdx= model.getFpdx();
		// 输出字段
		String[] colList =null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colList = new String[] { "pkValue", "xh", "xm", "xb", "xymc",
					"ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colList = new String[] { "pkValue", "xh", "xm", "xb", "nj","xymc",
					"ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colList = new String[] { "pkValue", "xh", "xm", "xb", "nj","xymc",
					"zymc","ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			 colList = new String[] { "pkValue", "xh", "xm", "xb", "nj","xymc",
						"zymc", "bjmc","ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}

		// 表头
		List<HashMap<String, String>> topTr = getCwjgTopTr(colList, fpdx);
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
		String showNum =String.valueOf(colList.length-1);
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setSearchType(searchType);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
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
	 * 按学生分配
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniCwtjRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "cwfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_cwgl_cwfp.do";
		// 查询类型
		String searchType = model.getFpdx();
		// 输出字段
		
		String[] colList =null;
		if("xy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}else if("njxy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}else if("njzy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","zymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}else if("bj".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","bjmc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}
		
		// 表头
		List<HashMap<String, String>> topTr = getCwtjTopTr(colList, searchType);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(colList.length);
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
	
}
