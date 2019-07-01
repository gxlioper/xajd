package xgxt.xsxx.comm.sjy.jcsjsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class SjyJcsjszInit {

	/**
	 * 数据源_基础数据设置
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJcsjszRForm(RequestForm rForm, SjyJcsjszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "jcsjsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_sjy_jcsjsz.do";
		// 表
		String tableName = "xg_view_xsxx_zdszb";
		// 输出字段
		String[] colList = new String[] { "search_zd", "search_zdm",
				"search_ymxs", "search_xgwz", "search_lrxz", "search_wkxz",
				"search_lrxs", "search_lybm", "search_sfqy" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// 是否查询操作
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		// 分页
		// Pages pages = model.getPages();

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
		
		//显示列数
		String showNum = "8";
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
	 * 数据源_基础数据设置
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getJcsjszGuideRForm(RequestForm rForm, SjyJcsjszForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsxx";
		// 系统字段设置
		String menu = "jcsjsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_sjy_jcsjsz.do";
		// 操作步骤
		String step = request.getParameter("step");
		// 最终步
		String step_last = "step" + model.getStepNum();

		String[] qtzd = new String[] { "step", "step_last" };
		String[] qtzdz = new String[] { step, step_last };
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);

	}
}
