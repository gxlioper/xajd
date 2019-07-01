package xgxt.xsxx.comm.sjy.jcsjcsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class SjyJcsjcshInit {

	/**
	 * 数据源_基础数据初始化
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJcsjcshRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
		
		//操作项目
		String czxm = model.getCzxm();
		
		if ("xy".equalsIgnoreCase(czxm)) {
			getJcsjcshByXyRForm(rForm, model, request);
		} else if ("zy".equalsIgnoreCase(czxm)) {
			getJcsjcshByZyRForm(rForm, model, request);
		} else if ("bj".equalsIgnoreCase(czxm)) {
			getJcsjcshByBjRForm(rForm, model, request);
		} else if ("xsjbxx".equalsIgnoreCase(czxm)) {
			getJcsjcshByStuRForm(rForm, model, request);
		}

	}
	
	/**
	 * 数据源_基础数据初始化(学院)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJcsjcshByXyRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "jcsjcsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_sjy_jcsjcsh.do";
		// 视图
		String tableName = "xg_view_jcsj_bmxx";
		// 表
		String realTable = "xg_jcsj_bmdmb_temp";
		// 输出字段
		String[] colList = new String[] { "bmdm", "bmmc", "fbmmc", "lbmc"};
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//操作项目
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// 规则
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// 来源表
		String lyb = request.getParameter("lyb");
		// 颜色
		String color = request.getParameter("color");
		// 字段
		String zd = rule;
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
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color" };
		String[] qtzdz = new String[] { czxm, rule, "xg_jcsj_bmdmb", zd, color };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
	 * 数据源_基础数据初始化(专业)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJcsjcshByZyRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
		
		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "jcsjcsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_sjy_jcsjcsh.do";
		// 视图
		String tableName = "xg_view_jcsj_zyxx";
		// 表
		String realTable = "xg_jcsj_zydmb_temp";
		// 输出字段
		String[] colList = new String[] { "zydm", "zymc", "bmmc"};
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//操作项目
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// 规则
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// 来源表
		String lyb = request.getParameter("lyb");
		// 颜色
		String color = request.getParameter("color");
		// 字段
		String zd = rule;
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
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color","bm" };
		String[] qtzdz = new String[] { czxm, rule, "xg_jcsj_zydmb", zd, color,model.getBmdm() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
	 * 数据源_基础数据初始化(班级)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJcsjcshByBjRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
	
		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "jcsjcsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_sjy_jcsjcsh.do";
		// 视图
		String tableName = "view_njxyzybj_all";
		// 表
		String realTable = "xg_jcsj_bjdmb_temp";
		// 输出字段
		String[] colList = new String[] { "bjdm", "bjmc", "nj", "zymc" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//操作项目
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// 规则
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// 来源表
		String lyb = request.getParameter("lyb");
		// 颜色
		String color = request.getParameter("color");
		// 字段
		String zd = rule;
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
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color","nj","zy" };
		String[] qtzdz = new String[] { czxm, rule, "xg_jcsj_bjdmb", zd, color,model.getNj(),model.getZydm() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
	 * 数据源_基础数据初始化(学生基本信息)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJcsjcshByStuRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
	
		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "jcsjcsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_sjy_jcsjcsh.do";
		// 视图
		String tableName = "xg_view_xsjbxx_temp";
		// 表
		String realTable = "xg_jcsj_xsxxb_temp";
		// 输出字段
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//操作项目
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// 规则
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// 来源表
		String lyb = request.getParameter("lyb");
		// 颜色
		String color = request.getParameter("color");
		// 字段
		String zd = rule;
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
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color",
				"nj", "xy", "zy", "bj" };
		String[] qtzdz = new String[] { czxm, rule, "xsxxb", zd, color,
				model.getNj(), model.getXydm(), model.getZydm(),
				model.getBjdm() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
