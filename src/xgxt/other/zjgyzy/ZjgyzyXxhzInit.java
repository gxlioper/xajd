package xgxt.other.zjgyzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;

public class ZjgyzyXxhzInit {

	ZjgyzyXxhzService service =new ZjgyzyXxhzService();
	
	/**
	 * 学生人数信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initXsrsManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
	
		// 功能模块
		String gnmk = "xsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("xsrsPrint.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		model.setColList(colList);
		
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
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
		String showNum = "8";
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
	 * 学生住宿信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initXszsManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		
		// 功能模块
		String gnmk = "gygl";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("xszsPrint.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs","rznsrs","rznvsrs","wrznsrs","wrznvsrs"}; 
		model.setColList(colList);
		
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
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
		String showNum = "8";
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
	 * 学生档案信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initXsdaManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {

		// 功能模块
		String gnmk = "xsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("xsdaPrint.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		model.setColList(colList);
		
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
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
		String showNum = "8";
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
	 * 辅导员信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initFdyManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		

		// 功能模块
		String gnmk = "xsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("fdyPrint.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList ={"r","xm","xb","jg","zzmm","dwlbdm","csrq","xl","xw","sxzy",
				"byyx","zc","zwmc","cjgzrq","szgzsj","bmmc","kzzd4","fblw"}; 
		model.setColList(colList);
		
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
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
		String showNum = "8";
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
	 * 辅导员班主任聘用一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initPyqkManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		

		// 功能模块
		String gnmk = "xsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("prqkPrint.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList ={"r","nd","xn","xqmc","xymc","bjmc","fdy","bzr"}; 
		
		model.setColList(colList);
		
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
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
		String showNum = "8";
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
	 * 违纪处分信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initWjcfManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		

		// 功能模块
		String gnmk = "xsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("wjcfPrint.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList ={"r","nd","xn","xqmc","bjmc","xh","xm","cflbmc","cfsj","cfyymc","cfwh"}; 
		model.setColList(colList);
		
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
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
		String showNum = "8";
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
	public List<HashMap<String, String>> getTopTr(String[] colList,User user, String path) {

		DAO dao=DAO.getInstance();
		
		String[] en = colList;
		
		String[] cn = null;
		
		String userType=user.getUserType();
		
		// --------------------咨询师管理 --------------------------
		if ("xsrsPrint.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"序号","分院","班级","人数","男生","女生"}; 
		
		}else if("xszsPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"序号","分院","班级","人数","男生","女生","入住男生","入住女生","未入住男生","未入住女生"}; 
		}else if("xsdaPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"序号","分院","班级","人数","男生","女生"}; 
		}else if("fdyPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"序号","姓名","性别","籍贯","政治面貌","婚姻状况","出生年月","学历","学位","所学专业",
					"毕业院校","职称","职务","参加工作时间","从事辅导员工作时间","所在分院","兼课情况","科研情况"}; 
		}else if("prqkPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"序号","年度","学年","学期","所在分院","班级","辅导员","班主任"};

		}else if("wjcfPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"序号","年度","学年","学期","班级","学号","姓名","处分名称","处分日期","处分原因","处分文号"}; 
		}
		
		return dao.arrayToList(en, cn);
	}
}
