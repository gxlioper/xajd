package xgxt.audit.splc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.qsgl.GyglQsglForm;

public class SplcInit {
	
	public void initForSplc(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		
		
		// 功能模块
		String gnmk = "audit";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwhSplc.do";
		// 输出字段
		String[] colList = new String[] { "id", "djlx", "mc", "ms", "sfmr" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList);
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

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	
	private List<HashMap<String, String>> getTopTr(String[] colList) {

		String[] colListCN = null;

		colListCN = new String[] { "ID", "单据类型", "名称", "描述", "是否默认" };

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

}
