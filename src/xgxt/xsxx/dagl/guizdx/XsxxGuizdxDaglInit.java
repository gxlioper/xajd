package xgxt.xsxx.dagl.guizdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.bjlhly.zhcp.BjlhlyZhcpForm;

public class XsxxGuizdxDaglInit {
	
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initDalxwhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "zhcp";
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "guizdx_dagl_dalxwh.do";
		// 输出字段
		String[] colList = new String[] {"disabled","dm","mc","lx"}; 
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
		String showNum = "2";
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
	 * 新生档案维护初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXsdawhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "zhcp";
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "guizdx_dagl_xsda.do";
		// 输出字段
		String[] colList = new String[] {"xh", "xm", "nj","bjmc","mc" }; 
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
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "5";
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
	 * 在校档案维护初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxdawhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

//		 功能模块
		String gnmk = "zhcp";
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "guizdx_dagl_zxda.do";
		// 输出字段
		String[] colList = new String[] {"xh", "xm", "nj","bjmc","mc" }; 
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
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "5";
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
	 * 毕业转档维护初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initByzdwhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

//		 功能模块
		String gnmk = "zhcp";
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "guizdx_dagl_byzd.do";
		// 输出字段
		String[] colList = new String[] {"xh", "xm", "nj", 
				"bjmc","mc","sfbrtj","datddz","jsr" }; 
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
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "13";
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
	 * 档案信息擦寻初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initDacxInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "xsda";
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "guizdx_dagl_dacx.do";
		// 输出字段
		String[] colList = new String[] {"xh","xm","nj","bjmc",
				"zxda","xsda","byda","sfbrtj","datddz","jsr"}; 
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
		
		if ("guizdx_dagl_dalxwh.do".equalsIgnoreCase(path)) {
			cn= new String[] {"disabled","代码", "名称", "类型" }; 
		}else if("guizdx_dagl_xsda.do".equalsIgnoreCase(path)){
			cn=new String[]{"学号", "姓名", "年级", "班级","档案信息"};
			
		}else if("guizdx_dagl_zxda.do".equalsIgnoreCase(path)){
			
			cn=new String[]{"学号", "姓名", "年级", "班级","档案信息"};
				
		}else if("guizdx_dagl_byzd.do".equalsIgnoreCase(path)){
			
			cn=new String[]{"学号", "姓名", "年级", 
					"班级","毕业转档","是否本人提档","档案提档地址","经手人"};
		}else if("guizdx_dagl_dacx.do".equalsIgnoreCase(path)){
			
			cn=new String[]{"学号","姓名","年级","班级",
					"在校档案","新生档案","毕业档案","是否本人提档","档案投递地址","经手人"};
		}
		
		return dao.arrayToList(en, cn);
	}

}
