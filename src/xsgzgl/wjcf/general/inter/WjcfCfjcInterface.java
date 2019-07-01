package xsgzgl.wjcf.general.inter;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.cfjcgl.WjcfCfjcModel;

public interface WjcfCfjcInterface {
	// 获得表头文件【处分解除】
	public List<HashMap<String, String>> getWjcfCfjcTop(WjcfCfjcModel model,
			User user);
	
	// 获得结果集【处分解除】
	public ArrayList<String[]> getWjcfCfjcList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【处分解除】
	public String createWjcfCfjcHTML(SearchRsModel rsModel,
			WjcfCfjcModel model, ArrayList<String[]> rsArrList, User user);
	
	//保存
	public boolean saveJcSq(WjcfGeneralForm form) throws Exception;
	
	//修改
	public boolean updateJcSq(WjcfGeneralForm form) throws Exception;
	
	//撤销删除
	public boolean delJcSq(String cfId) throws Exception;
	
	public HashMap<String, String> getJcSq(String cfId) ;
	
	public InputStream fjCx(WjcfGeneralForm form) throws Exception;
	
	// 自定义导出【处分解除维护】
	public List<HashMap<String,String>> getWjcfCfjcExportList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	/** 
	 * @描述:(下载申诉附件)
	 * @作者：cmj[工号：913]
	 * @日期：2013-8-30 下午02:37:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * InputStream 返回类型 
	 * @throws 
	 */
	public InputStream ssFjxz(WjcfGeneralForm myForm) throws Exception;
}
