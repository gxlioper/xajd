package xgxt.xtwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;
import xgxt.comm.CommSetting;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护初始化类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * 
 * @version 1.0
 */

public class XtwhInit {

	/**
	 * 快捷方式_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getXtwhKjfsInit(RequestForm rForm, HttpServletRequest request) {

		// 功能模块
		String gnmk = "xtwh";
		// 评奖基本设置
		String menu = "kjfssz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = request.getParameter("userType");
		// 访问路径
		String path = "";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setUserType(userType);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}

	/**
	 * 首页设置_下载专区_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getXtwhXzzqInit(RequestForm rForm, SyszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xtwh";
		// 评奖基本设置
		String menu = "xzzq";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = request.getParameter("userType");
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "xtwh_xzzqwh.do";
		// 视图
		String tableName = "xg_view_xtwh_xzzq";
		// 输出字段
		String[] colList = new String[] { "pk", "filemc", "ss", "lx", "towho", "scr",
				"scsj", "filepath" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// 分页
		Pages pages = model.getPages();

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
		// ===============通用设置 end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPk(pk);
		rForm.setUserType(userType);
		rForm.setTableName(tableName);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);
	}

	/**
	 * 首页设置_下载专区(首页)_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getMainXzzqInit(RequestForm rForm, SyszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xtwh";
		// 评奖基本设置
		String menu = "xzzq";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = request.getParameter("userType");
		// 主键
		String pk = request.getParameter("pk");
		// 文件所属
		String filess = request.getParameter("filess");
		// 访问路径
		String path = "xtwhSysz.do?method=xzzqView";
		// 视图
		String tableName = "xg_view_xtwh_xzzq";
		// 输出字段
		String[] colList = new String[] { "filepath", "filesm", "filemc", "ss",
				"lx" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		// 其他字段
		String[] qtzd = new String[] { "filess" };
		// 其他字段值
		String[] qtzdz = new String[] { filess };

		// 分页
		Pages pages = model.getPages();

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
		// ===============通用设置 end =================

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPk(pk);
		rForm.setUserType(userType);
		rForm.setTableName(tableName);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
	}

	/**
	 * 首页设置_首页调查_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getXtwhSydcInit(RequestForm rForm, SyszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xtwh";
		// 评奖基本设置
		String menu = "sydc";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = request.getParameter("userType");
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "xtwh_sydcwh.do";
		// 视图
		String tableName = "xg_view_xtwh_sydc";
		// 输出字段
		String[] colList = new String[] { "dcid", "shownr", "dcr", "dcsj",
				"sfqy", "dcnr" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
	
		// 分页
		Pages pages = model.getPages();

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

		// 显示的数量
		String showNum = "4";
		commSetting.setShowNum(showNum);
		// ===============通用设置 end =================

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPk(pk);
		rForm.setUserType(userType);
		rForm.setTableName(tableName);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);

	}
}
