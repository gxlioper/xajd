package xsgzgl.gygl.jqlx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.db.GetSysData;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class GyglJqlxInit extends BasicService{
	
	GyglJqlxService service = new GyglJqlxService();
	/**
	 * 初始化假期留校设置信息
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initLxszManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {
		
		XtwhShlcService shlcService =new XtwhShlcService();
		
		// ----------设置表只存在一条记录 故修改整张表 begin----------------
		// 主键
		model.setPk(new String[]{"1"});
		// 主键值
		model.setPkValue(new String[]{"1"});
		// ----------设置表只存在一条记录 故修改整张表 end----------------
		
		// 表名
		model.setTableName("xg_gygl_jqlxszb");
		
		// 审核流程
		List<HashMap<String,String>>shlc=shlcService.getShlcByDjlx("gygl");
		
		request.setAttribute("shlcList", shlc);
	}
	
	/**
	 * 初始化假期留校设置保存
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initLxszSave(BasicModel model,
			HttpServletRequest request) {
		
		// 主键
		String [] pk={"1"};
		// 主键
		String [] pkValue={"1"};
		
		// 保存字段
		String []save={"lcid","sqkg"};
		
		// --------------表名------------
		model.setTableName("xg_gygl_jqlxszb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		model.setValueMap(valueMap);
		
		model.setPk(pk);
		
		model.setPkValue(pkValue);
	}
	
	/**
	 * 初始化假期留校设置保存
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 * @throws Exception 
	 */
	public void initLxsqSave(BasicModel model,
			HttpServletRequest request) throws Exception {
		GyglJqlxForm myForm=new GyglJqlxForm();
		
		User user=model.getUser();
		// 保存字段
		String []save={"sqsj","kssj","jssj","sqly","lcid","sqjg"};
		
		// --------------表名------------
		model.setTableName("xg_gygl_jqlxsqb");
		// --------------需要保存的值--------------------		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		valueMap.put("id", GetSysData.getGuid());
		
		valueMap.put("sqsj", GetTime.getNowTime2());
		
		valueMap.put("xh", user.getUserName());
		
		service.setJbszInfo(myForm);
		
		if("no".equalsIgnoreCase(myForm.getLcid())){
			
			valueMap.put("sqjg", "wxsh");
		}else {
		
			valueMap.put("sqjg", "wsh");
		}
		
		model.setValueMap(valueMap);
		
	}
	
	/**
	 * 初始化假期留校设置保存
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initJqlxSh(BasicModel model,
			HttpServletRequest request) {
		
		User user=model.getUser();
		// --------------需要保存的值--------------------		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		valueMap.put("xmb", "xg_gygl_jqlxszb");
		
		model.setValueMap(valueMap);
		
	}
	
	
	/**
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initLxsqSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("jqlx_lxsq.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList = {"id","sqsj","shzt","kssj","jssj"}; 
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
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initLxshSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("jqlx_lxsh.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList = {"id","xh","xm","nj","bjmc","sqsj","shzt"}; 
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
		
		HashMap<String,String>valueMap=new HashMap<String,String>();
		valueMap.put("sqb", "xg_gygl_jqlxsqb");
		valueMap.put("shb", "xg_gygl_jqlxshb");
		valueMap.put("xmb", "xg_gygl_jqlxszb");
		valueMap.put("ryb", "view_xsjbxx");
		model.setValueMap(valueMap);
		
		model.setSqbPk(new String[]{"id"});
		model.setShbPk(new String[]{"sqid"});
	
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
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initLxjgSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("jqlx_lxjg.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList = {"id", "xh", "xm", "nj","bjmc", "yjzxsj",
				"sqsj", "sqjg" }; 
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
		
		HashMap<String,String>valueMap=new HashMap<String,String>();
		valueMap.put("sqb", "xg_gygl_jqlxsqb");
		valueMap.put("shb", "xg_gygl_jqlxshb");
		valueMap.put("xmb", "xg_gygl_jqlxszb");
		valueMap.put("ryb", "view_xsjbxx");
		model.setValueMap(valueMap);
		
		model.setSqbPk(new String[]{"id"});
		model.setShbPk(new String[]{"sqid"});
	
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
		if ("jqlx_lxsq.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","申请时间","目前进度", "预计开始时间",  "预计结束时间"}; 
		
		}else if("jqlx_lxsh.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"","学号","姓名","年级","班级","申请","审核状态"}; 
		}else if("jqlx_lxjg.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"", "学号", "姓名", "年级","班级", "预计住校时间",
					"申请时间", "申请结果"}; 
		}
		
		return dao.arrayToList(en, cn);
	}
}
