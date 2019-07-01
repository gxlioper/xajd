package xsgzgl.pjpy.general.inter.wdpj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

public interface WdpjXmshInterface {
	
	// ===========================评奖项目审核 结果集 begin =============================
	
	// 初始化评奖项目
	public List<HashMap<String, String>> getCshXmList(WdpjXmshModel model,
			User user);
	
	// 初始化评奖项目(考虑审核开关及时间控制)
	public List<HashMap<String, String>> getShxmList(WdpjXmshModel model,
			User user);
	
	// 获得表头文件(我的评奖_学生申请)
	public List<HashMap<String, String>> getWdpjXmshTop(WdpjXmshModel model,
			User user);

	// 获得结果集(我的评奖_学生申请)
	public ArrayList<String[]> getWdpjXmshList(PjpyGeneralForm myForm,
			WdpjXmshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(我的评奖_学生申请)
	public String createWdpjXmshHTML(SearchRsModel rsModel,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user);
	
	// 特殊字段(隐藏域数据)
	public String createKidneyDiv(SearchRsModel rsModel,RequestForm rForm,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user) throws Exception;
	
	// ===========================评奖项目审核 结果集 end =============================
	
	
	// 保存审核状态
	public boolean updateShzt(WdpjXmshModel model,HttpServletRequest request,  User user)throws Exception;
	
	// 获取单个审核的详细信息
	public HashMap<String,Object>defaultWdpjXmsh(WdpjXmshModel form,User user)throws Exception;
	
	// 获得项目审核岗位
	public List<HashMap<String,String>> getSpgwList(WdpjXmshModel model,User user);
	
	public boolean checkFirstSpgw(WdpjXmshModel model, User user);
	
	//获得项目审核岗位 
	public void showShgwDiv(WdpjXmshModel model,User user,HttpServletResponse response) throws IOException;
}
