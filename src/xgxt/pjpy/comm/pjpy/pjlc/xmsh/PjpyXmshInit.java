package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

public class PjpyXmshInit {

	/**
	 * 评奖项目审核_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getPjxmshRForm(RequestForm rForm, PjpyXmshForm model,
			HttpServletRequest request) {
		
		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "pjxmsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 主键值
		String pkValue = request.getParameter("pk");
		// 访问路径
		String path = "pjpy_pjlc_xmsh.do";
		// 输出字段
		String[] colList = new String[] { "pk", "xh", "xm", "nj", "showxy",
				"showzy", "showbj", "shzt", "sjzt","xymc","zymc","bjmc" };
		// 表头
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_pjpy_xmsh", colList, null);
		// 是否查询操作
		String search = !("go".equalsIgnoreCase(request.getParameter("go"))) ? "false"
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

		// 显示列数
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String shxm = model.getShxm();
		String spgw = model.getSpgw();
		String shjb = model.getShjb();
		String pjxn = model.getJbszModel().getPjxn();
		String pjxq = model.getJbszModel().getPjxq();
		String pjnd = model.getJbszModel().getPjnd();
		String xqmc = model.getJbszModel().getPjxqmc();
		
		String[] qtzd = new String[] { "shxm", "pjxn", "pjxq", "pjnd", "pjxqmc","spgw","shjb","pk" };
		String[] qtzdz = new String[] { shxm, pjxn, pjxq, pjnd, xqmc,spgw,shjb,pkValue };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setPages(pages);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPkValue(pkValue);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		
	}
}
