package xsgzgl.pjpy.general.inter.wdpj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbModel;
import xsgzgl.pjpy.general.wdpj.pjtj.WdpjPjtjDAO;

public interface WdpjLssbInterface {

	// ----------------------老师上报结果集相关信息 begin------------------
	
	// 获得表头文件(我的评奖_老师上报)
	public List<HashMap<String, String>> getWdpjLssbTop(PjpyWdpjModel model,
			User user);

	// 获得结果集(我的评奖_老师上报)
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(我的评奖_老师上报)
	public String createWdpjLssbHTML(SearchRsModel rsModel,
			PjpyWdpjModel model,HashMap<String,String>qdrsMap,
			ArrayList<String[]> rsArrList, User user);
	
	// 构建结果集(我的评奖_老师上报)
	public String createKidneyDiv(SearchRsModel rsModel,
			PjpyWdpjModel model,HashMap<String,String>qdrsMap,
			ArrayList<String[]> rsArrList, User user);
	
	// ----------------------老师上报结果集相关信息 end------------------
	
	
	// ----------------------老师上报 begin------------------	
	// 保存项目上报信息
	public Boolean saveXmsb(BasicModel mode,User user);
	
	// 修改上报信息
	public Boolean updateLssbInfo(BasicModel mode,User user);
	
	// 保存评奖审核表信息
	public boolean saveLssbShInfo(String xmdm, String xh) throws Exception;
	// ----------------------老师上报 end------------------	
	
	
	// ----------------------老师上报结果集相关信息 begin------------------
	// 显示老师上报信息
	public void showLssbDiv(User user,String opera,String xmdm, String xh,
			HttpServletResponse response)throws IOException;
	
//	 显示老师上报信息
	public List<HashMap<String,String>>  getXscjList(String xh) throws IOException;
	
	// 学生其他信息(课程成绩、综测成绩)
	public void showXsxxDiv(User user,String xmdm, String xh, HttpServletResponse response) throws IOException;

	// 获取人数控制范围
	public HashMap<String,String>getQdrsByBj(String xmdm,String bjdm);
	// ----------------------老师上报结果集相关信息 end------------------
}
