package xsgzgl.pjpy.general.inter.pjsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjry.PjszPjryModel;

public interface PjszPjryInterface {

	// 获得表头文件(评奖设置_评奖人员)
	public List<HashMap<String, String>> getPjszPjryTop(PjszPjryModel model,
			User user);

	// 获得结果集(评奖设置_评奖人员)
	public ArrayList<String[]> getPjszPjryList(PjpyGeneralForm myForm,
			PjszPjryModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(评奖设置_评奖人员)
	public String createPjszPjryHTML(SearchRsModel rsModel,
			PjszPjryModel model, ArrayList<String[]> rsArrList, User user);

	// 保存评奖班级调整
	public Boolean saveBjtz(PjszPjryModel model, User user);

	// 撤销评奖班级调整
	public boolean disfrockPjry(PjszPjryModel model,User user) throws Exception;
	
	// 保存参评设置
	public Boolean saveSfcp(PjszPjryModel model, User user);
	
}
