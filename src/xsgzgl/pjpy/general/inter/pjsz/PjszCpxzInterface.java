package xsgzgl.pjpy.general.inter.pjsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.cpxz.PjszCpxzModel;

public interface PjszCpxzInterface {
	
	// 获得表头文件(评奖设置_参评小组)
	public List<HashMap<String, String>> getPjszCpxzTop(PjszCpxzModel model,
			User user);

	// 获得结果集(评奖设置_参评小组)
	public ArrayList<String[]> getPjszCpxzList(PjpyGeneralForm myForm,
			PjszCpxzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(评奖设置_参评小组)
	public String createPjszCpxzHTML(SearchRsModel rsModel,
			PjszCpxzModel model, ArrayList<String[]> rsArrList, User user);
	
	// 保存参评小组
	public Boolean saveCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx);
	
	// 删除参评小组
	public Boolean deleteCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx);

	// 保存参评小组自动设置
	public boolean saveCpxzZdsz(PjszCpxzModel model, User user);
	
	// 检测参评小组提交
	public String checkCpxzSubmit(PjszCpxzModel model, User user);
}
