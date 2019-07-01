package xsgzgl.rcsw.zjbb;

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

public class RcswZjbbInit extends BasicService{
	
	RcswZjbbService service=new RcswZjbbService();
	/**
	 * 初始化假期留校设置信息
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbszSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "rcsw";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("rcsw_zjbb_bbsz.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList = {"id","zjmc","lcmc","num"}; 
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
	 * 初始化假期留校设置保存
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbszSave(BasicModel model,
			HttpServletRequest request) {
		
	
		// 保存字段
		String []save={"lcid","zjmc"};
		
		// --------------表名------------
		model.setTableName("xg_rcsw_zjbbszb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		model.setValueMap(valueMap);
	
	}
	
	/**
	 * 初始化假期留校设置保存
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbszModi(BasicModel model,
			HttpServletRequest request) {
		
		String[]pk=new String[]{"id"};
		
		String pkValue=request.getParameter("id");
		// 保存字段
		String []save={"lcid","zjmc"};
		
		// --------------表名------------
		model.setTableName("xg_rcsw_zjbbszb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);

		valueMap.put("id", pkValue);
		
		model.setValueMap(valueMap);
		
		model.setPk(pk);
		
	}
	
	/**
	 * 初始化假期留校设置保存
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbsqSave(BasicModel model,
			HttpServletRequest request) {

		RcswZjbbForm myForm =new RcswZjbbForm();
		
		User user=model.getUser();
		
		String xmid=request.getParameter("xmid");
		
		myForm.setXmid(xmid);
		
		HashMap<String,String>zjbbMap=service.getBbszInfo(myForm);
		
		// 保存字段
		String []save={"id","xmid","xh","sqsj","sqly"};
		
		if("no".equalsIgnoreCase(zjbbMap.get("lcid"))){
			
			save=new String[]{"id","xmid","xh","sqsj","sqly","sqjg"};
		}
		
		// --------------表名------------
		model.setTableName("xg_rcsw_zjbbsqb");
		// --------------需要保存的值--------------------		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		valueMap.put("id", GetSysData.getGuid());
		
		valueMap.put("sqsj", GetTime.getNowTime2());
		
		valueMap.put("xh", user.getUserName());
		
		valueMap.put("sqjg", "wsh");
		
		model.setValueMap(valueMap);
		
	}
	
	/**
	 * 初始化假期留校设置保存
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initZjbbSh(BasicModel model,
			HttpServletRequest request) {
		
		User user=model.getUser();
		// --------------需要保存的值--------------------		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		valueMap.put("xmb", "xg_rcsw_zjbbszb");
		
		model.setValueMap(valueMap);
		
	}
	
	
	/**
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initBbsqSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "rcsw";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("rcsw_zjbb_bbsq.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList = {"id","zjmc","shzt","shyj","sqsj"}; 
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
	public void initBbshSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "rcsw";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("rcsw_zjbb_bbsh.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList = {"id", "xh", "xm", "nj","bjmc","sqsj", "shzt"}; 
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
	public void initBbjgSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "rcsw";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		model.setPath("rcsw_zjbb_bbjg.do");
		String path = model.getPath();
		
		// 输出字段
		String[] colList = {"id", "xh", "xm", "nj","bjmc","sqsj",
				"zjmc","sqjg" }; 
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
		if ("rcsw_zjbb_bbsz.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","证件名称","审核流程","未审核完成记录数"}; 
		
		}else if("rcsw_zjbb_bbsq.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"","证件名称","审核进度", "审核意见" ,"申请时间"};  //新增审核意见
		}else if("rcsw_zjbb_bbsh.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"", "学号", "姓名", "年级","班级","申请时间", "审核状态"}; 
		}else if("rcsw_zjbb_bbjg.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"", "学号", "姓名", "年级","班级","申请时间",
					"补办证件","申请结果"}; 
		}
		
		return dao.arrayToList(en, cn);
	}
}
