package xsgzgl.pjpy.general.inter.wdpj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;

public interface WdpjXssqInterface {

	// 获得表头文件(我的评奖_学生申请)
	public List<HashMap<String, String>> getWdpjXssqTop(WdpjXssqModel model,
			User user);

	// 获得结果集(我的评奖_学生申请)
	public ArrayList<String[]> getWdpjXssqList(PjpyGeneralForm myForm,
			WdpjXssqModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(我的评奖_学生申请)
	public String createWdpjXssqHTML(SearchRsModel rsModel,
			WdpjXssqModel model, ArrayList<String[]> rsArrList, User user);

	// 显示学生申请（修改）DIV
	public void showXssqDiv(String opera,String xmdm, User user,
			HttpServletResponse response)throws IOException; 
	
	public String createOtherInfo(HashMap<String,String>csMap)throws IOException; 
	
	
//	// 撤销学生申请信息
//	public void disfrockXssqInfo(String message,
//			HttpServletResponse response)throws IOException; 

	
	// 初始化页面显示(我的评奖_学生申请)
	public Map<String,Object> defaultWdpjXssq(PjpyGeneralForm form,User user);
	
	// 保存学生申请我的评奖信息
	public boolean saveXssqInfo(BasicModel mode,HashMap<String,String>valueMap,User user);
	
	// 保存学生申请我的评奖信息
	public boolean updateXssqInfo(BasicModel mode,HashMap<String,String>valueMap,User user);
	
	// 当前周期学生申请信息查看
	public List<HashMap<String,String>>getXssqByZq(WdpjXssqModel model,
			User user)throws Exception;
	
	// 当前周期学生综测信息
	public List<Object>getZcxxByZq(WdpjXssqModel model,
			User user)throws Exception;
	
	// 历年奖学金申请情况查询
	public List<HashMap<String,String>>getXssqInfo(WdpjXssqModel model,
			User user);
	
	// 历年奖学金申请情况查询
	public List<String[]>getXszcInfo(WdpjXssqModel model,
			User user);
	
	public boolean saveWdpjShInfo(String xmdm, User user)throws Exception;

	// 显示学生申请（修改）DIV(北京联合)
	public void showXssqDivForBJLH(String opera, String xmdm, User user, HttpServletResponse response) throws IOException;
}
