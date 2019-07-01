package xgxt.xljk.hzny;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.xszz.whtl.ybbx.XszzYbbxSaveModel;
import xgxt.xszz.whtl.ybbx.XszzYbbxService;
import xsgzgl.comm.BasicModel;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-4 下午04:34:46</p>
 */
public class HznyXljkZxzxInit {

	HznyXljkZxzxService service =new HznyXljkZxzxService();
	
	/**
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxsglManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = model.getPath();
		// 输出字段
		String[] colList = model.getColList(); 
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
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxsSave(BasicModel basicModel,
			HttpServletRequest request) {

		// 主键字段
		String []save={"zgh","zxszg","jj","bz"};
		
		// --------------表名------------
		basicModel.setTableName("xg_xljk_zxsxxb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxsModi(BasicModel basicModel,
			HttpServletRequest request) {

		XszzYbbxService service = new XszzYbbxService();

		String []save={"jj","zxszg","bz","zgh"};
		
		String []pk={"zgh"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_zxsxxb");
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	
	/**
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTsxsglManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = model.getPath();
		// 输出字段
		String[] colList = model.getColList(); 
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
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTsxsSave(BasicModel basicModel,
			HttpServletRequest request,User user) {

		// 需要保存的字段
		String []save={"xh","tbgxxslb","sfzy","xswjbx","tbgxcs",
				"xyclgc","xsdqzk","bz","sbr","sbsj","sbrlxdh"};
	
		String userName=user.getUserName();
		
		// --------------表名------------
		basicModel.setTableName("xg_xljk_tsxsxxb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		valueMap.put("sbr", userName);
		
		valueMap.put("sbsj", GetTime.getNowTime2());
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTsxsModi(BasicModel basicModel,
			HttpServletRequest request) {

		XszzYbbxService service = new XszzYbbxService();

		String []save={"xh","tbgxxslb","sfzy","xswjbx","tbgxcs","xyclgc","xsdqzk","bz","sbrlxdh"};
		
		String []pk={"xh"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_tsxsxxb");
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}

	/**
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxzxglManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = model.getPath();
		// 输出字段
		String[] colList = model.getColList(); 
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
				.parseInt(showNum) + 1)};

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
	 * 心理健康 在线咨询（学生提问）
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxzxSave(BasicModel basicModel,
			HttpServletRequest request) {
	
		// 需要保存的字段
		String []save=null;
		
		save=new String[]{"xh","zxwt"};
		
		
		// --------------表名------------
		basicModel.setTableName("xg_xljk_zxzxb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxzxModi(BasicModel basicModel,
			HttpServletRequest request,User user) {

		String userType=user.getUserType();
		
		String userName=user.getUserName();
	
		String []save=null;
		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		// 学生只能修改咨询的问题信息（老师回复后不允许修改）
		if("stu".equalsIgnoreCase(userType)){
			
			save=new String[]{"guid","xh","zxwt"};
	
		}else {//教师回复内容
			
			save=new String[]{"guid","xh","zgh","hfnr"};

		}
		
		String []pk={"guid"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_zxzxb");
		
		valueMap.putAll(service.getValueMap(request, save));
		
		if(!"stu".equalsIgnoreCase(userType)){//教师回复内容
			
			valueMap.put("zgh", userName);
			
			valueMap.put("hfsj", GetTime.getNowTime2());
		}
		
		basicModel.setValueMap(valueMap);
	}
	
	
	/**
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXszxManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = model.getPath();
		// 输出字段
		String[] colList = model.getColList(); 
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
	 * 心理健康 在线咨询（学生提问）
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXszxSave(BasicModel basicModel,
			HttpServletRequest request,User user) {
	
		// 需要保存的字段
		String []save=null;
		
		save=new String[]{"xh","zgh","zxsj","zxnrjyj"};
		
		// --------------表名------------
		basicModel.setTableName("xg_xljk_xszxfkb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		valueMap.put("xh", user.getUserName());
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXszxModi(BasicModel basicModel,
			HttpServletRequest request,User user) {

		String userType=user.getUserType();
		
		String []save=null;
		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		// 学生只能修改咨询的问题信息（老师回复后不允许修改）
		if("stu".equalsIgnoreCase(userType)){
			
			save=new String[]{"guid","zxnrjyj","zxsj"};
	
		}else {//教师回复内容
			
			save=new String[]{"guid","zxsfk","fksj"};

		}
		
		String []pk={"guid"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_xszxfkb");
		
		valueMap.putAll(service.getValueMap(request, save));
		
		if(!"stu".equalsIgnoreCase(userType)){//教师回复内容
			
			valueMap.put("fksj", GetTime.getNowTime2());
		}
		
		basicModel.setValueMap(valueMap);
	}
	

	/**
	 * 咨询师管理初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxjlManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xljk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = model.getPath();
		// 输出字段
		String[] colList = model.getColList(); 
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
	 * 心理健康 在线咨询（学生提问）
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxjlSave(BasicModel basicModel,
			HttpServletRequest request,User user) {
	
		// 需要保存的字段
		String []save=null;
		
		save=new String[]{"xh","zgh","zxsj","zxnr","zxshf"};
		
		// --------------表名------------
		basicModel.setTableName("xg_xljk_xlzxjlb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		valueMap.put("zgh", user.getUserName());
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxjlModi(BasicModel basicModel,
			HttpServletRequest request,User user) {

		String userType=user.getUserType();
		
		String []save=null;
		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		// 学生只能修改咨询的问题信息（老师回复后不允许修改）
		
		save=new String[]{"guid","zxsj","zxnr","zxshf"};
		
		String []pk={"guid"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_xlzxjlb");
		
		valueMap.putAll(service.getValueMap(request, save));
		
		basicModel.setValueMap(valueMap);
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
		if ("hn_xljk_zxsgl.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","职工号","姓名", "性别",  "部门","咨询师资格"}; 
		
		}else if ("hn_xljk_tsxsgl.do".equalsIgnoreCase(path)) {
		
			cn= new String[] {"","学号","姓名","年级","学院","专业","班级"}; 
		
		}else if ("hn_xljk_zxzx.do".equalsIgnoreCase(path)) {
		
			if("stu".equalsIgnoreCase(userType)){
				
				cn= new String[] {"","学号","姓名", "咨询时间", "咨询内容","是否回复"};
			}else {
				
				cn= new String[] {"","学号","姓名","年级","班级", "咨询时间","咨询内容", "是否回复","是否特殊学生"};
			}
		
		}else if ("hn_xljk_xszx.do".equalsIgnoreCase(path)) {
		
			
			cn= new String[] {"","咨询师","咨询时间", "咨询内容及意见", "是否回复","是否本人评价"};
			
		
		}else if("hn_xljk_xszx.do?searchType=zxs".equalsIgnoreCase(path)){
			
			cn= new String[] {"","咨询师","咨询时间","咨询内容及意见","是否回复", "是否针对本人意见"};
			
		}else if("hn_xljk_xszx.do?searchType=admin".equalsIgnoreCase(path)){
			
			cn= new String[] {"","咨询师职工号","咨询师姓名","咨询人学号","咨询人姓名","咨询时间", "是否反馈"};
			
		}else if ("hn_xljk_zxjl.do".equalsIgnoreCase(path)) {
		
			if("stu".equalsIgnoreCase(userType)){
				
				cn= new String[] {"","学号","姓名","年级","班级","咨询时间"};
			
			}else{
				
				cn= new String[] {"","学号","姓名","年级","班级","咨询时间", "是否特殊学生"};
				
			}
		}

		return dao.arrayToList(en, cn);
	}
	
}
