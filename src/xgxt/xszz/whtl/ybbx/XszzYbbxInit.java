package xgxt.xszz.whtl.ybbx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.comm.BasicModel;

public class XszzYbbxInit {
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initYbbxcx(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "pjpy";
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
	public void initYbbxsh(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// 功能模块
		String gnmk = "xszz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = model.getPath();
		// 输出字段
		String[] colList =new String[]{"pkValue","xh","xm", "nj",  "bjmc","xysh"};
		
		String userType=user.getUserType();
		if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			colList =new String[]{"pkValue","xh","xm", "nj",  "bjmc","xxsh"};
		}
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
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initDgshInfo(XszzYbbxSaveModel saveModel, BasicModel basicModel,
			HttpServletRequest request,User user) {

		XszzYbbxService service = new XszzYbbxService();

		// 主键字段
		String []save={"xn","xh"};
		// 主键字段
		String []pk={"xn","xh"};
		// 单个保存字段
		String []oneZd={"xn","xh","czr","czsj"};
		// 批量保存字段
		String []arrayZd={"bxje","mzyy","wzsj","ylyt","shje"};
		
		basicModel.setPk(pk);
	
		service.getModelValue(saveModel, request);
		
		// ---------------------固定的保存值 begin------------
		saveModel.setCzr(user.getUserName());
		
		saveModel.setCzsj(GetTime.getNowTime2());
	
		saveModel.setXn(Base.currXn);
		
		// ---------------------固定的保存值 end--------------
		basicModel.setPkValue(saveModel.getPkValue());
		
		basicModel.setOneZd(oneZd);
		
		basicModel.setArrayZd(arrayZd);
		
		// --------------表名------------
		basicModel.setTableName("xg_xszz_ybbxxxb");
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
	public void initShInfo(XszzYbbxSaveModel saveModel, BasicModel basicModel,
			HttpServletRequest request,User user) {

		XszzYbbxService service = new XszzYbbxService();

		basicModel.setPk(new String[]{"xn","xh"});
		
		String[]update=null;
		
		String userType=user.getUserType();
		if("xy".equalsIgnoreCase(userType)){
			
			update=new String[]{"xn","xh","xysh","xyshyj"};
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			update=new String[]{"xn","xh","xxsh","xxshyj"};
			
		}
		
		HashMap<String,String>valueMap=service.getValueMap(request, update);
		
		// ---------------------固定的保存值 begin------------
		if("xy".equalsIgnoreCase(userType)){
			
			valueMap.put("xyshsj", GetTime.getNowTime2());
			
			valueMap.put("xyshr", user.getUserName());
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			valueMap.put("xxshsj", GetTime.getNowTime2());
			
			valueMap.put("xxshr", user.getUserName());
			
			if("退回".equalsIgnoreCase(service.unicode2Gbk(valueMap.get("xxsh")))){
				valueMap.put("xysh","需重审");
				update=new String[]{"xn","xh","xysh","xxsh","xxshyj"};
			}
			
		}
		
		basicModel.setValueMap(valueMap);
		
		basicModel.setTableName("xg_xszz_ybbxsqb");
	}
	
	/**
	 * 档案类型初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initPlsh(XszzYbbxSaveModel saveModel, BasicModel basicModel,
			HttpServletRequest request,User user) {

		XszzYbbxService service = new XszzYbbxService();

		basicModel.setPk(new String[]{"xn","xh"});
		
		service.getModelValue(basicModel, request);
		
		String[]update=null;
		
		String userType=user.getUserType();
		
		if("xy".equalsIgnoreCase(userType)){
			
			update=new String[]{"xysh","xyshyj"};
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			update=new String[]{"xxsh","xxshyj"};
			
		}
		
		HashMap<String,String>valueMap=service.getValueMap(request, update);
		
		// ---------------------固定的保存值 begin------------
		if("xy".equalsIgnoreCase(userType)){
			
			valueMap.put("xyshsj", GetTime.getNowTime2());
			
			valueMap.put("xyshr", user.getUserName());
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			valueMap.put("xxshsj", GetTime.getNowTime2());
			
			valueMap.put("xxshr", user.getUserName());
			
			if("退回".equalsIgnoreCase(service.unicode2Gbk(valueMap.get("xxsh")))){
				valueMap.put("xysh","需重审");
			}
			
		}
		
		basicModel.setValueMap(valueMap);
		
		basicModel.setTableName("xg_xszz_ybbxsqb");
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
		
		if ("xszz_ybbx_cx.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","学号","姓名", "年级",  "班级","学院审核","学校审核"}; 
		
		}else if("xszz_ybbx_sh.do".equalsIgnoreCase(path)) {
			
			if("xy".equalsIgnoreCase(userType)){
			
				cn= new String[] {"","学号","姓名", "年级",  "班级","学院审核"}; 
			}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
				cn= new String[] {"","学号","姓名", "年级",  "班级","学校审核"}; 
			}
		}
		return dao.arrayToList(en, cn);
	}
}
