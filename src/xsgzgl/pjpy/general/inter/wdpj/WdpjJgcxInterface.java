package xsgzgl.pjpy.general.inter.wdpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

public interface WdpjJgcxInterface {

	// 获得表头文件(我的评奖_本次评奖)
	public List<HashMap<String, String>> getWdpjBcpjTop(WdpjJgcxModel model,
			User user);

	// 获得结果集(我的评奖_本次评奖)
	public ArrayList<String[]> getWdpjBcpjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(我的评奖_本次评奖)
	public String createWdpjBcpjHTML(SearchRsModel rsModel,
			WdpjJgcxModel model, ArrayList<String[]> rsArrList, User user);
	
	// 获得评奖结果信息
	public HashMap<String, Object> getBcpjMap(WdpjJgcxModel model);

	//===================【我的评奖_历史评奖】begin=============================
	
	// 【我的评奖_历史评奖】获得表头文件
	public List<HashMap<String, String>> getWdpjLspjTop(WdpjJgcxModel model,
			User user);

	// 【我的评奖_历史评奖】获得结果集
	public ArrayList<String[]> getWdpjLspjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 【我的评奖_历史评奖】构建结果集
	public String createWdpjLspjHTML(SearchRsModel rsModel,
			WdpjJgcxModel model, ArrayList<String[]> rsArrList, User user);

	// 【我的评奖_历史评奖】获得详细信息
	public HashMap<String, String> getLspjMap(WdpjJgcxModel model)
			throws Exception;

	// 【我的评奖_历史评奖】保存评奖历史信息
	public boolean savePjlsxx(WdpjJgcxModel model, User user,
			HttpServletRequest request);
	
	// 【我的评奖_历史评奖】保存评奖历史信息
	public boolean deletePjlsxx(WdpjJgcxModel model, User user,
			HttpServletRequest request);
	
	// 【我的评奖_历史评奖】获得评奖历史信息
	public List<HashMap<String, String>> getLspjList(String xh);
	
	//===================【我的评奖_历史评奖】end=============================
	
	// 初始化评奖项目
	public List<HashMap<String, String>> getCshXmList(WdpjJgcxModel model,
			User user);

	// 获得表头文件(我的评奖_结果查询)
	public List<HashMap<String, String>> getWdpjJgcxTop(WdpjJgcxModel model,
			User user);

	// 获得结果集(我的评奖_结果查询)
	public ArrayList<String[]> getWdpjJgcxList(WdpjJgcxModel model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	// 构建结果集(我的评奖_结果查询)
	public String createWdpjJgcxHTML(WdpjJgcxModel model,
			ArrayList<String[]> rsArrList, User user);

	// 删除学生申请评奖信息(我的评奖_结果查询)
	public boolean deleteXssqInfo(WdpjJgcxModel model,
			HttpServletRequest request, User user);

	// 获取单个审核的详细信息
	public Map<String, Object> defaultWdpjXssq(WdpjJgcxModel form, User user);
}
