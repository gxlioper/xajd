package xgxt.xsxx.comm.jbsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class XsxxJbszInit {

	/**
	 * 系统字段设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getXtzdszRForm(RequestForm rForm, XsxxJbszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "xtzdsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_jbsz_xtzdsz.do";
		// 表
		String tableName = "xg_view_xsxx_zdszb";
		// 输出字段
		String[] colList = new String[] { "search_zd", "search_zdm",
				"search_ymxs", "search_sjy", "search_xgwz", "search_lrxz",
				"search_wkxz", "search_lrxs", "search_lyb", "search_sfqy",
				"lybnum" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// 分页
		// Pages pages = model.getPages();

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
		
		//显示列数
		String showNum = "9";
		commSetting.setShowNum(showNum);
		
		// ===============通用设置 end ============

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
	 * 页面设置设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getYmjbszRForm(RequestForm rForm, HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsxx";
		// 页面基本设置
		String menu = "ymjbsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_jbsz_ymjbsz.do";

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);

	}
	
	/**
	 * 系统字段设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getXsqszRForm(RequestForm rForm, XsxxJbszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "xsqsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_jbsz_xsqsz.do";
		// 表
		String tableName = "xg_view_xsxx_xsqsz";
		// 输出字段
		String[] colList = new String[] { "xsqdm", "xsqmc", "xssx", "sfzk",
				"sfqy", "sfzd" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// 分页
		// Pages pages = model.getPages();

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
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
}
