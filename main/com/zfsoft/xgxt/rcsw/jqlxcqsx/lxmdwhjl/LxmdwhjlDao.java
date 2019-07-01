/**
 * @部门:学工产品事业部
 * @日期：2017年3月27日 下午1:50:43 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh.LxmdwhForm;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年3月27日 下午1:50:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhjlDao extends SuperDAOImpl<LxmdwhjlForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_cqsx_jqlx_mdwhczjlb");
		super.setKey("jlid");
		super.setClass(LxmdwhjlForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhjlForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhjlForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (");
		sql.append("SELECT t1.jlid,t1.czsj,t1.czlx,t1.xh,t1.xmid,t1.XGQLXQKSM,t1.XGHLXQKSM,");
		sql.append("y.xm czr,decode(t1.czlx,'1','增加','2','修改','3','删除') czlxmc,t2.XM,t2.bjdm,t2.xydm,t2.zydm,t2.nj,t2.bjmc,t2.zymc,t2.xymc,t3.XMMC,");
		sql.append("CASE t1.CZLX WHEN '2' THEN '【修改】留校情况说明：' || t1.xghlxqksm END czxq ");
		sql.append("FROM xg_cqsx_jqlx_mdwhczjlb t1 ");
		sql.append("LEFT JOIN view_xsbfxx t2 ON t1.XH = t2.XH ");
		sql.append("LEFT JOIN yhb y ON y.yhm = t1.czr ");
		sql.append("LEFT JOIN XG_CQSX_JQLX_XMSZ t3 ON t1.XMID = t3.XMID) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws SQLException  
	 * @描述:批量保存留校名单维护记录（与下面同名方法重载）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 下午12:00:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveLxmdwhjlList(LxmdwhForm t,String[] xhs,String czr) throws SQLException {
		String sql = "INSERT INTO xg_cqsx_jqlx_mdwhczjlb(czr,czlx,xh,xmid,xghlxqksm) "
				   + "VALUES (?,'1',?,?,?)";
		boolean result = true;
		List<String[]> paraList = new ArrayList<String[]>();
		for (String xh:xhs) {
			paraList.add(new String[]{czr,xh,t.getXmid(),t.getLxqksm()});
		}
		int[] rs = dao.runBatch(sql, paraList);
		for (int r:rs) {
			if (r == Statement.EXECUTE_FAILED){
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * @throws SQLException  
	 * @描述:批量保存留校名单维护记录（与上面同名方法重载）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 下午4:11:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxmdList
	 * @param czr
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveLxmdwhjlList(List<HashMap<String, String>> lxmdList, String czr) throws SQLException {
		String sql = "INSERT INTO xg_cqsx_jqlx_mdwhczjlb(czr,czlx,xh,xmid,xgqlxqksm) "
				   + "VALUES (?,'3',?,?,?)";
		boolean result = true;
		List<String[]> paraList = new ArrayList<String[]>();
		for (HashMap<String,String> map:lxmdList) {
			paraList.add(new String[]{czr,map.get("xh"),map.get("xmid"),map.get("lxqksm")});
		}
		int[] rs = dao.runBatch(sql, paraList);
		for (int r:rs) {
			if (r == Statement.EXECUTE_FAILED){
				result = false;
				break;
			}
		}
		return result;
	}

	/** 
	 * @描述:根据id查询留校名单维护记录信息（关联留校名单信息）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 下午5:12:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jlid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getLxmdwhjlById(String jlid) {
		StringBuilder sql = new StringBuilder("SELECT t1.*,decode(t1.czlx,'1','增加','2','修改','3','删除') czlxmc,t2.XM,t3.XMMC,");
		sql.append("t3.LXXMSM,t3.lxkssj || ' 至 ' || t3.lxjssj qzsj,y.xm czrmc,");
		sql.append("CASE t1.CZLX WHEN '2' THEN '【修改】留校情况说明：' || t1.xghlxqksm END czxq ");
		sql.append("FROM xg_cqsx_jqlx_mdwhczjlb t1 ");
		sql.append("LEFT JOIN XSXXB t2 ON t1.XH = t2.XH ");
		sql.append("LEFT JOIN yhb y ON y.yhm = t1.czr ");
		sql.append("LEFT JOIN XG_CQSX_JQLX_XMSZ t3 ON t1.XMID = t3.XMID ");
		sql.append("WHERE t1.jlid = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{jlid});
	}


}
