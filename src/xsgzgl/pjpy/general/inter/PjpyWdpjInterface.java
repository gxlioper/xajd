package xsgzgl.pjpy.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.szdw.general.dwwh.DwwhModel;

public interface PjpyWdpjInterface {

	// 获得表头文件(评奖评优_我的评奖)
	public List<HashMap<String, String>> getPjpyWdpjTop(PjpyWdpjModel model,
			User user);

	// 获得结果集(评奖评优_我的评奖)
	public ArrayList<String[]> getPjpyWdpjList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(评奖评优_我的评奖)
	public String createPjpyWdpjHTML(SearchRsModel rsModel,
			PjpyGeneralForm myForm, PjpyWdpjModel model,
			ArrayList<String[]> rsArrList, User user);

	// 显示我的评奖详细信息
	public void showWdpjView(SearchRsModel rsModel, String xmdm, User user,
			HttpServletResponse response) throws IOException;

	// 显示我的评奖详细信息
	public void showLnzcInfo(SearchRsModel rsModel, String xmdm, User user,
			HttpServletResponse response) throws IOException;

	// 
	public void showWdpjLssb(String xmdm, User user, String xh,
			HttpServletResponse response) throws IOException;

	// 创建我的审核DIV
	public void createWdshDiv(PjpyWdpjModel model, User user,
			HttpServletResponse response) throws Exception;

}
