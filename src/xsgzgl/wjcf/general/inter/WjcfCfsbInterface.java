package xsgzgl.wjcf.general.inter;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbModel;

public interface WjcfCfsbInterface {
	// 获得表头文件【处分上报】
	public List<HashMap<String, String>> getWjcfCfsbTop(WjcfCfsbModel model,
			User user);
	
	// 获得结果集【处分上报】
	public ArrayList<String[]> getWjcfCfsbfList(WjcfGeneralForm myForm,
			WjcfCfsbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【处分上报】
	public String createWjcfCfsbHTML(SearchRsModel rsModel,
			WjcfCfsbModel model, ArrayList<String[]> rsArrList, User user);
	
	//保存
	public String saveCfsb(WjcfGeneralForm form,User user) throws Exception;
	
	//修改保存
	public boolean updateCfsb(WjcfGeneralForm form,User user,String cflbdmXt) throws Exception;
	
	public  HashMap<String, String> getCfsb(String cfid) throws Exception;
	
	//获取学生已受处分情况列表
	public List<HashMap<String,String>> getYscfqk(String xh);
	
	public List<HashMap<String,String>> getCfsh(String cfid);
	
	//批量删除
	public boolean delWjsb(String[] pk);
	
	//获取处分审核数据列表
	public List<HashMap<String,String>> getCfshxxList(String cfid);
	
	public InputStream fjCx(WjcfGeneralForm form) throws Exception;
	
	public String checkCfsb(WjcfGeneralForm model);
		
	
	public String[] getSpgwByCflb(String cflbdm);
	
	// 自定义导出设置 
	public List<HashMap<String,String>> getWjcfCfsbfExportList(WjcfGeneralForm myForm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
}
