package xgxt.pjpy.bjlhly.zhcp;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.cwgl.GyglCwglForm;

public class BjlhlyZhcpInit {
	/**
	 * 初始化综测结果查询界面基本信息
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZcjgInfo(RequestForm rForm, BjlhlyZhcpForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "zhcp";
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_bjlhly_zcjg.do";
		// 输出字段
		String[] colList = new String[] {"xh", "xm", "njmc", "bjmc","dycj",
				"decj","dsscj","zpjf","bjpm","zypm" }; 
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList,path);
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

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 根据模块路径获取表头信息
	 * @param colList
	 * @param path
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String[] colList, String path) {

		DAO dao=DAO.getInstance();
		
		String[] en = colList;
		
		String[] cn = null;
		
		if ("zhcp_bjlhly_zcjg.do".equalsIgnoreCase(path)) {
			cn= new String[] {"学号", "姓名", "年级", "班级","大一综测成绩",
					"大二综测成绩","大三上成绩","总成绩","班级排名","专业排名" }; 
		}
		
		return dao.arrayToList(en, cn);
	}

}
