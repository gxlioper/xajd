package com.zfsoft.xgxt.jskp.cssz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CsszDao extends SuperDAOImpl<CsszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(CsszForm.class);
		this.setKey("id");
		this.setTableName("xg_jskp_csszb");
	}
	
	/**
	 * 
	 * @描述： 获取审批流程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-4 下午04:26:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSplc(String lx){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_csszb where lx = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{lx});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-4 下午04:47:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cssz
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveData(CsszForm cssz) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String[]> paraList = new ArrayList<String[]>();
		if(cssz.getIds() == null || cssz.getIds().length == 0 ||StringUtils.isNull(cssz.getIds()[0]) ){
			sql.append(" insert into xg_jskp_csszb(splc,lx,sfsh)values(?,?,?)");
			paraList.add(new String[]{cssz.getSplcs()[0],cssz.getLxs()[0],cssz.getSfsh()});
			paraList.add(new String[]{cssz.getSplcs()[1],cssz.getLxs()[1],cssz.getSfsh()});
		}else{
			sql.append(" update xg_jskp_csszb set splc = ?,sfsh = ? where id = ?");
			paraList.add(new String[]{cssz.getSplcs()[0],cssz.getSfsh(),cssz.getIds()[0]});
			paraList.add(new String[]{cssz.getSplcs()[1],cssz.getSfsh(),cssz.getIds()[1]});
		}
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	/**
	 * @描述: 取参数设置表中的是否审核
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-20 下午08:04:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSfsh(){
		String sql = " select distinct sfsh from xg_jskp_csszb ";
		return dao.getOneRs(sql, new String[]{}, "sfsh");
	}
}
