package xsgzgl.szdw.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.dwwh.DwwhJzgModle;
import xsgzgl.szdw.general.dwwh.DwwhModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_隊伍維護_Interface类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public interface SzdwDwwhInterface {

	// 初始化参数
	public void initParameter();

	// 获得表头文件
	public List<HashMap<String, String>> getDwwhTop(DwwhModel model, User user);

	// 获得结果集
	public ArrayList<String[]> getDwwhList(SzdwGeneralForm myForm,
			DwwhModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集
	public String createDwwhHTML(SearchRsModel rsModel, DwwhModel model,
			ArrayList<String[]> rsArrList, User user);
	
	// 辅导员带班数
	public String countFdyDbj(String zgh);
	
	// 班主任带班数
	public String countBzrDbj(String zgh);

	// 创建队伍维护DIV
	public void createDwwhDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;
	// 创建队伍维护DIV
	public String createDwwhDivStr(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;
	// 保存队伍维护
	public boolean saveDwwh(DwwhModel model, User user);

	// 删除队伍维护
	public boolean deleteDwwh(DwwhModel model, User user);

	// 创建用户库DIV
	public void createYhkDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// 保存用户到用户库
	public boolean saveYhk(DwwhModel model, User user);

	// 创建院校兼任DIV
	public String createYxjrDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// 创建班级信息DIV
	public void createBjxxDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// 保存院校兼任
	public boolean saveYxjr(DwwhModel model, User user);

	// 获取思政队伍信息
	public HashMap<String, String> getDwwh(DwwhModel model, User user);

	// 创建年级Div
	public void createNjLvDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// 创建其他级别Div
	public void createOtherLvDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// 保存分配班级
	public boolean saveFpbj(DwwhModel model, User user);
	
	// 撤销分配班级
	public boolean disfrockFpbj(DwwhModel model, User user);

	// 获得部门列表
	public List<HashMap<String, String>> getBmList();

	// 创建教职工Option
	public String createJzgOption(DwwhModel model) throws Exception;
	
	// 获得表头文件【编班】
	public List<HashMap<String, String>> getSetupTop(DwwhModel model, User user);

	// 获得结果集【编班】
	public ArrayList<String[]> getSetupList(SzdwGeneralForm myForm,
			DwwhModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【编班】
	public String createSetupHTML(SearchRsModel rsModel, DwwhModel model,
			ArrayList<String[]> rsArrList, User user);
	
	// 创建教职工信息DIV
	public void createJzgxxDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;
	//查询教职工信息
	public DwwhJzgModle cxJzgxx(DwwhJzgModle model, User user,HttpServletRequest request) throws Exception;
	//修改教职工信息
	public Boolean updateJzgxx(HttpServletRequest request) throws Exception;
	
	// 辅导员信息维护自定义导出
	public List<HashMap<String,String>> getDwwhExportList(SzdwGeneralForm myForm,User user) throws Exception;
	//修改弹出层页面
	public ArrayList<String[]>  createBjxx(DwwhModel model, User user)throws Exception ;

	/** 
	 * @描述:(查看教职工信息)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-23 下午04:37:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> ckJzgxx(String zgh);
	
	/** 
	 * (查看教职工信息)浙江医学高等专科学校
	 */
	public HashMap<String, String> ckJzgxx_13023(String zgh);
	/**
	 * 
	 * @描述:TODO(查看教职工信息)江西师范
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-12 上午11:28:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> ckJzgxxJxsf(String zgh);
}
