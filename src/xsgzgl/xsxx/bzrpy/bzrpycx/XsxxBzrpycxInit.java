package xsgzgl.xsxx.bzrpy.bzrpycx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.xsxx.dagl.guizdx.XsxxGuizdxDaglForm;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.xsxx.bzrpy.BasicModel;

public class XsxxBzrpycxInit {
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initBzrpy(RequestForm rForm, BasicModel model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = model.getPath();
		// 输出字段
		String[] colList = model.getColList(); 
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
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "10";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

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
		
		if ("xsxx_bzrpycx.do?method=bzrpycxManage".equalsIgnoreCase(path)) {
			cn= new String[] {"学年","学号", "姓名", "年级",  "班级","评语", "评议人","评议时间"}; 
		}
		
		return dao.arrayToList(en, cn);
	}

}
