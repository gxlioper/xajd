package xsgzgl.pjpy.general.inter.pjsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlModel;

public interface PjszBjdlInterface {

	// 获得表头文件(评奖设置_班级大类)
	public List<HashMap<String, String>> getPjszBjdlTop(PjszBjdlModel model,
			User user);

	// 获得结果集(评奖设置_班级大类)
	public ArrayList<String[]> getPjszBjdlList(PjpyGeneralForm myForm,
			PjszBjdlModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(评奖设置_班级大类)
	public String createPjszBjdlHTML(SearchRsModel rsModel,
			PjszBjdlModel model, ArrayList<String[]> rsArrList, User user);

	// 保存班级大类
	public Boolean saveBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
			User user, String saveLx);

	// 删除班级大类
	public Boolean deleteBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
			User user, String saveLx);

}
