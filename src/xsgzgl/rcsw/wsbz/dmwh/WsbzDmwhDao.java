/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:56:53 
 */  
package xsgzgl.rcsw.wsbz.dmwh;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.gyjldmgl.GyjldmglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-5-5 上午09:56:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzDmwhDao extends SuperDAOImpl<WsbzDmwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsbzDmwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsbzDmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from rcsw_wsbz_dmwh t");
		if(StringUtils.isNotNull(t.getFdmc())){
			sql.append(" where t.fdmc like '%' || ? || '%'");
			paralist.add(t.getFdmc());
		}
		return getPageList(t, sql.toString(), paralist.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setTableName("rcsw_wsbz_dmwh");
		this.setKey("fddm");
		this.setClass(WsbzDmwhForm.class);
	}
	
	//检验是否该项目代码或该项目名称是否存在
	public boolean checkIsExists(WsbzDmwhForm t){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameterlist = new ArrayList<String>();
		sql.append("  select count(1) num");
		sql.append(" from rcsw_wsbz_dmwh t");
		sql.append(" where t.fdmc = ?");
		parameterlist.add(t.getFdmc().trim());
		if(StringUtils.isNotNull(t.getFddm())){
			sql.append(" and t.fddm != ?");
			parameterlist.add(t.getFddm());
		}
		
		String num = dao.getOneRs(sql.toString(),parameterlist.toArray(new String[]{}) , "num");
	    if(num.equals("0")){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	//删除时检查倍删除的项目是否在使用中
	public boolean checkIsUsingNow(String[]para){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from rcsw_wsbz_sq where fddm in");
		sql.append(" (");
		for (int i = 0; i < para.length; i++) {
			sql.append("?");
			if(i !=  para.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		String num = dao.getOneRs(sql.toString(), para, "num");
		if(num.equals("0")){
			return false;
		}else{
			return true;
		}
	}
	
	public HashMap<String, String> getWsbzCk(String fddm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select fdmc,");
		sql.append(" decode(hdpl, '1', '天', '2', '周', hdpl) hdpl,");
		sql.append(" sj,");
		sql.append(" dd,");
		sql.append(" rs,");
		sql.append(" gzzz,");
		sql.append(" fwyq");
		sql.append(" from rcsw_wsbz_dmwh");
		sql.append(" where fddm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{fddm});
	}

	public HashMap<String, String> getQjcs() {
		StringBuilder sql = new StringBuilder();
		sql.append("select bmcs,jzts,jzsj from rcsw_wsbz_qjcswh");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}

	public boolean runUpdateQjcs(String bmcs, String jzts, String jzsj) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" update rcsw_wsbz_qjcswh ");
		sb.append(" set bmcs=?,jzts=?,jzsj=? where 1=1");
		return  dao.runUpdate(sb.toString(), new String[] { bmcs, jzts, jzsj });
	}

}
