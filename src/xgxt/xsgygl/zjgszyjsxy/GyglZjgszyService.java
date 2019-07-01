/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-30 下午01:59:36</p>
 */
package xgxt.xsgygl.zjgszyjsxy;

import java.util.ArrayList;
import java.util.HashMap;

public class GyglZjgszyService {
	/**公寓团支书查询结果表头*/
	public ArrayList<HashMap<String, String>>  getTzzSearchTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		result = mydao.dao_gyTzzQueryTitle();
		return result;
	}
	public ArrayList<String[]> serv_gyTzzQuery(GyTzzModel tzzModel){
		ArrayList<String[]> result = new ArrayList<String[]>();
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		result = mydao.dao_gyTzzQuery(tzzModel);
		return result;
	}
	public boolean serv_gyTzzAdd(GyTzzModel tzzModel) throws Exception{
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_gyTzzAdd(tzzModel);
	}
	public HashMap<String,String> serv_getTzzXx(String pkValue){
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_getTzzXx(pkValue);
	}
	public boolean serv_gyTzzModi(GyTzzModel tzzModel,String pkValue) throws Exception{
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_gyTzzModi(tzzModel,pkValue);
	}
	public boolean serv_gyTzzDel(String pkValue) throws Exception{
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_gyTzzDel(pkValue);
	}
	public boolean serv_mjqsAdd(MjqsModel mjqsModel) throws Exception{
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_mjqsAdd(mjqsModel);
	}
	public ArrayList<HashMap<String, String>>  getmjqsSearchTitle(){
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		result = mydao.dao_getmjqsSearchTitle();
		return result;
	}
	public ArrayList<String[]> serv_mjqsQuery(MjqsModel mjqsModel){
		ArrayList<String[]> result = new ArrayList<String[]>();
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		result = mydao.dao_mjqsQuery(mjqsModel);
		return result;
	}
	public HashMap<String,String> serv_getMjqsXx(String pkValue){
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_getMjqsXx(pkValue);
	}
	public boolean serv_mjqsModi(MjqsModel mjqsModel,String pkValue) throws Exception{
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_mjqsModi(mjqsModel,pkValue);
	}
	public boolean serv_mjqsDel(String pkValue) throws Exception{
		GyglZjgszyDAO mydao = new GyglZjgszyDAO();
		return mydao.dao_mjqsDel(pkValue);
	}
	
}
