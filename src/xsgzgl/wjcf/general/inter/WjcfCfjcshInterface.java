package xsgzgl.wjcf.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.cfjcgl.WjcfCfjcshModel;

public interface WjcfCfjcshInterface {
	// 获得表头文件【处分解除】
	public List<HashMap<String, String>> getWjcfCfjcshTop(WjcfCfjcshModel model,
			User user);
	
	// 获得结果集【处分解除】
	public ArrayList<String[]> getWjcfCfjcshList(WjcfGeneralForm myForm,
			WjcfCfjcshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【处分解除】
	public String createWjcfCfjcshHTML(SearchRsModel rsModel,
			WjcfCfjcshModel model, ArrayList<String[]> rsArrList, User user);

	//根据处分类别代码查询岗位
	public void showShgwDiv(User user,
			HttpServletResponse response) throws IOException ;

	//获取上一级岗位
	public HashMap<String,String> getHigherUpSpMap(WjcfGeneralForm model,User user);
	
	//获取处分审核数据列表
	public List<HashMap<String,String>> getCfjcshxx(String cfid,String spgw);
	
	//获取一条处分解除审核数据
	public HashMap<String, String> getOnesCfjcsh(String cfid,String spgw) throws Exception;
	
	//获取最高级岗位
	public HashMap<String,String> getMaxSpgw(WjcfGeneralForm model,User user) throws Exception;
	
	//获取处分信息
	public HashMap<String,String> getCfxx(String cfId) throws Exception;
	
	//获取处分申述申请信息
	public HashMap<String,String> getOnesCfss(String cfId) throws Exception;
	
	//获取处分解除申请信息
	public HashMap<String,String> getOnesCfjc(String cfId) throws Exception;
	
	//批量审核处分上报
	public boolean saveCfjcsh (WjcfGeneralForm form,User user) throws Exception;
	
	//提交时统计
	public List<HashMap<String,String>> tongjiList();
	
	//提交
	public boolean tjSh() throws Exception;
	
	//获取处分审核数据列表
	public List<HashMap<String,String>> getCfjcshxxList(String cfid);
	
	
	//查询处分审核岗位
	public List<HashMap<String,String>> getSpgwList(User user);
	
	//是否最高级用户
	public boolean isZgjyh(User user) throws Exception ;
	
	//提交
	public boolean zjtjSh(WjcfGeneralForm form) throws Exception;

}
