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
import xsgzgl.wjcf.general.cfsbgl.WjcfCfshModel;

public interface WjcfCfshInterface {
	
	
	// 获得表头文件【处分审核】
	public List<HashMap<String, String>> getWjcfCfshTop(WjcfCfshModel model,
			User user);
	
	// 获得结果集【处分审核】
	public ArrayList<String[]> getWjcfCfshList(WjcfGeneralForm myForm,
			WjcfCfshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【处分审核】
	public String createWjcfCfshHTML(SearchRsModel rsModel,
			WjcfCfshModel model, ArrayList<String[]> rsArrList, User user);
	
	public  HashMap<String, String> getCfsb(String cfid) throws Exception;
	
	//获取学生已受处分情况列表
	public List<HashMap<String,String>> getYscfqk(String xh);
	
	public List<HashMap<String,String>> getCfsh(String cfid);

	//获取处分类别代码
	public List<HashMap<String, String>> getCflbList(WjcfCfshModel model,
			User user);
	
	//根据处分类别代码查询岗位
	public void showShgwDiv(String cflbdm, User user,
			HttpServletResponse response) throws IOException ;
	
	//获取上一级岗位
	public HashMap<String,String> getHigherUpSpMap(WjcfGeneralForm model,User user);
	
	
	//判断是否可以修改
	public boolean sfKyxg (WjcfGeneralForm model,User user);
	
	//获取最高级岗位
	public HashMap<String,String> getMaxSpgw(WjcfGeneralForm model,User user) throws Exception;
	
	//获取一条审核数据
	public HashMap<String, String> getOnesCfsh(String cfid,String spgw) throws Exception;
	
	//获取处分审核数据列表
	public List<HashMap<String,String>> getCfshxx(String cfid,String spgw);
	
	//批量审核处分上报
	public boolean saveCfsh (WjcfGeneralForm form,User user) throws Exception;
	
	//提交时统计
	public List<HashMap<String,String>> tongjiList();
	
	//提交
	public boolean tjSh() throws Exception;
	
	//提交
	public boolean zjtjSh(WjcfGeneralForm form) throws Exception;
	
	//判断是否最高级用户
	public boolean isZgjyh(User user) throws Exception;
}
