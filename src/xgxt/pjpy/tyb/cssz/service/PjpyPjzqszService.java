package xgxt.pjpy.tyb.cssz.service;

import java.util.HashMap;

import common.Globals;

import xgxt.action.Base;
import xgxt.pjpy.tyb.cssz.dao.PjpyPjzqszDAO;
import xgxt.pjpy.tyb.cssz.model.PjpyPjzqszModel;
import xgxt.utils.String.StringUtils;

public class PjpyPjzqszService {

	final String CHECKED = "checked";//是否选中
	
	PjpyPjzqszDAO dao = new PjpyPjzqszDAO();

	/**
	 * 保存评奖周期信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePjzqxx(PjpyPjzqszModel model) throws Exception{
		return dao.savePjzqxx(model);
	}
	
	/**
	 * 删除评奖周期信息
	 * @return
	 * @throws Exception
	 */
	public boolean delPjzqxx() throws Exception {
		return dao.delPjzqxx();
	}
	
	/**
	 * 修改奖学金评奖时间
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjpjsj(PjpyPjzqszModel model) throws Exception{
		return dao.updateJxjpjsj(model);
	}
	

	/**
	 * 查询评奖时间,周期
	 * @return
	 */
	public HashMap<String, String> queryPjpysj() {
		return dao.queryPjpysj();
	}
	
	/**
	 * 修改奖学金评奖时间,周期
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updatePjsj(PjpyPjzqszModel model) throws Exception {
		boolean result = updateJxjpjsj(model);
		
		result = result ? delPjzqxx() : false;
		
		return result ? savePjzqxx(model) : false;
	}
	
	/**
	 * 获取参评周期
	 * @return
	 */
	public String getPjzq() {
		HashMap<String, String> rs = queryPjpysj();
		String xn = rs.get("xn");
		String xq = rs.get("xq");
		String result = "";

		if (CHECKED.equalsIgnoreCase(rs.get("nd"))) {// 按年度参评
			result = "nd";
		} else if (CHECKED.equalsIgnoreCase(xq)) {//按学年,学期
			result = "xq";
		} else if (CHECKED.equalsIgnoreCase(xn)
				&& !CHECKED.equalsIgnoreCase(xq)) {// 按学年
			result = "xn";
		}
		return result;
	} 
	
	/**
	 * 获取综测周期
	 * @return
	 */
	public String getZczq() {
		HashMap<String, String> rs = queryPjpysj();
		String xn = rs.get("zcxn");
		String xq = rs.get("zcxq");
		String nd = rs.get("zcnd");
		String result = "";
		
		//没有设置综测周期，则综测周期同评奖周期
		if(StringUtils.isNull(xn) 
				&& StringUtils.isNull(nd)
				&& StringUtils.isNull(xq)){
			xn = rs.get("xn");
			xq = rs.get("xq");
			nd = rs.get("nd");
		}

		if (CHECKED.equalsIgnoreCase(nd)) {// 按年度参评
			result = "nd";
		} else if (CHECKED.equalsIgnoreCase(xq)) {//按学年,学期
			result = "xq";
		} else if (CHECKED.equalsIgnoreCase(xn)
				&& !CHECKED.equalsIgnoreCase(xq)) {// 按学年
			result = "xn";
		}
		return result;
	} 
	
	public HashMap<String, String> queryXtszbxx() {
		return dao.queryXtszbxx();
	}
	
	/**
	 * 是否设置综合测评周期
	 * @return boolean
	 * */
	public boolean hasZczq(){
		boolean flag = false;
		if(Globals.XXDM_LSXY.equalsIgnoreCase(Base.xxdm)){
			//丽水学院
			flag = true;
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//南通职业大学
			flag = true;
		}
		
		return flag;
	}
}
