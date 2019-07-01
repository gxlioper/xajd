/**
 * @部门:学工产品事业部
 * @日期：2016-11-24 上午11:25:40 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-24 上午11:25:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhDao extends SuperDAOImpl<LxmdwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select ");
		sql.append(" t.id,");
		sql.append(" t.xmid,");
		sql.append(" t.xh,");
		sql.append(" t.lxqksm,");
		sql.append(" t1.xmmc,");
		sql.append(" t1.lxkssj,");
		sql.append(" t1.lxjssj,");
		sql.append(" t1.lxkssj || ' 至 ' || t1.lxjssj qzsj,");
		sql.append(" t1.lxxmsm,");
		sql.append(" t2.XM,");
		sql.append(" t2.BJDM,");
		sql.append(" t2.BJMC,");
		sql.append(" t2.XYDM,");
		sql.append(" t2.XYMC,");
		sql.append(" t2.NJ,");
		sql.append(" t2.XB,");
		sql.append(" t2.ZYDM,");
		sql.append(" t2.ZYMC,");
		sql.append(" t3.qsh,");
		sql.append(" t3.cwh,");
		sql.append(" t3.lddm,");
		sql.append(" t3.ldmc,");
		sql.append(" t3.ldmc || t3.qsh szqs");
		sql.append("  from ");
		sql.append(" xg_cqsx_jqlx_mdwh t");
		sql.append(" left join xg_cqsx_jqlx_xmsz t1");
		sql.append(" on t.xmid = t1.xmid");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t.xh = t2.XH");
		sql.append(" left join view_xg_gygl_new_cwxx t3");
		sql.append(" on  t.xh = t3.xh");
		sql.append(" ) t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(LxmdwhForm.class);
		this.setKey("id");
		this.setTableName("xg_cqsx_jqlx_mdwh");
	}
	
	/**
	 * 
	 * @描述: 获取留校项目名称List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:27:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmmcList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,t.lxkssj || t.lxkssj qzsj from xg_cqsx_jqlx_xmsz t ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 获取留校项目名称List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:27:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmmcMap(String xmid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,t.lxkssj || t.lxkssj qzsj from xg_cqsx_jqlx_xmsz t where t.xmid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xmid});
	}
	
	/**
	 * 
	 * @描述:批量维护时判断是否可以保存，判断依据xh,xmid为唯一键
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午11:15:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfCanSave(String[] xhs,String xmid){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num from xg_cqsx_jqlx_mdwh where xh in( ");
		for (int i = 0; i < xhs.length; i++) {
			sql.append("?");
			paraList.add(xhs[i]);
			if(i != xhs.length -1){
				sql.append(",");
			}
		}
		sql.append(" ) and xmid = ?");
		paraList.add(xmid);
		sql.append(" ");
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return "0".equals(num);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午11:32:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param xhs
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savePlwh(LxmdwhForm t,String[] xhs) throws SQLException{
		StringBuffer sql = new StringBuffer();
		boolean flag = true;
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < xhs.length; i++) {
			paraList.add(new String[]{xhs[i],t.getXmid(),t.getLxqksm()});
		}
		sql.append("insert into xg_cqsx_jqlx_mdwh(xh,xmid,lxqksm) values(?,?,?)");
		int[] res = dao.runBatch(sql.toString(), paraList);
		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
		
	}
	
	/**
	 * @throws Exception 
	 * @描述: 获取可以添加的学生List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-28 下午01:32:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCanAddStuList(LxmdwhForm t, User user,String xhs) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from(");
		sql.append("  select * ");
		sql.append("  from view_xsjbxx t");
		sql.append("  where t.xh not in");
		sql.append("  (select xh from xg_cqsx_jqlx_mdwh where xmid = '"+t.getXmid()+"')");
		if(StringUtils.isNotNull(xhs)){
			String[] xhArr = xhs.split(","); 
			sql.append("  and xh not in (");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"'");
				if(i != xhArr.length-1){
					sql.append(",");
				}
			}
			sql.append("  )");
		}
		sql.append(" )t where 1=1");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:根据id数组获取留校名单列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 下午3:49:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLxmdListByIds(String[] ids) {
		StringBuilder sql = new StringBuilder("select xh,xmid,lxqksm from xg_cqsx_jqlx_mdwh");
		sql.append(" where id = ");
		for(int i=0;i<ids.length;i++){
			sql.append(" ? ");
			if(i!=ids.length-1){
				sql.append(" or id = ");
			}
		}
		return dao.getListNotOut(sql.toString(),ids);
	}
}
