package xsgzgl.pjpy.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;

public interface PjpyZhcpInterface {

	// 获得初始化项目
	public List<HashMap<String, String>> getCshXmList(User user);

	// 获得表头文件(综合测评_综测信息)
	public List<HashMap<String, String>> getZhcpZcxxTop(PjpyZhcpModel model,
			User user);

	// 获得结果集(综合测评_综测信息)
	public ArrayList<String[]> getZhcpZcxxList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(综合测评_综测信息)
	public String createZhcpZcxxHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user);
	
	// 保存综合测评信息
	public boolean saveZhcpInfo(PjpyGeneralForm model,HttpServletRequest request, User user);
	
	//	构建学校个性化隐藏信息（隐藏域）
	public String createKidneyDiv(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user);
	
	public boolean account(PjpyGeneralForm myForm,User user) throws Exception;
	
	// ---------------------综合测评结果查询 2012.4.11 qlj begin-------------------------------
	
	// 获得表头文件(综合测评_综测信息)
	public List<HashMap<String, String>> getZhcpResultTop(PjpyGeneralForm myForm,User user);

	// 获得结果集(综合测评_综测信息)
	public ArrayList<String[]> getZhcpResultList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws Exception;

	// 构建结果集(综合测评_综测信息)
	public String createZhcpResultHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user);
	
	// ---------------------综合测评结果查询 2012.4.11 qlj end-------------------------------
	
	// ---------------------综合测评列选 2012.4.11 qlj begin-------------------------------
	public List<HashMap<String, String>> getKindChoose(PjpyGeneralForm model, User user);
	
	public List<HashMap<String, String>> getCheckKind(PjpyGeneralForm model, User user);
	
	public boolean saveKindChoose(PjpyZhcpModel model, User user)throws Exception;
	
	public boolean updateLybInfo(PjpyGeneralForm model, User user)throws Exception;
	
	public List<HashMap<String,String>>getLybInfo();
	
	public void showZdxgDiv(String zd, String zdid, User user,
			HttpServletResponse response) throws IOException ;
	
	// ---------------------综合测评列选 2012.4.11 qlj end-------------------------------
	
	// ---------------------初始版本1.0----------------------------------------
	// 获得表头文件(综合测评维护)
	public List<HashMap<String, String>> getZhcpMaintainTop(
			PjpyGeneralForm model, User user);

	// 获得结果集(综合测评维护)
	public ArrayList<String[]> getZhcpMaintainInfo(PjpyGeneralForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	// 构建结果集(综合测评维护)
	public String createZhcpMaintainRs(SearchRsModel rsModel,
			PjpyGeneralForm model, ArrayList<String[]> rsArrList, User user);
	// ---------------------初始版本1.0 end----------------------------------------

}
