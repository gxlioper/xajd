/**
 * @部门:学工产品事业部
 * @日期：2016-10-28 下午02:45:49 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import java.io.File;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-10-28 下午02:45:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwmarkDao extends SuperDAOImpl<RcxwmarkForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RcxwmarkForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RcxwmarkForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append(" select t.id rcxwjgid,");
		sql.append(" '' id,");
		sql.append(" t.xh,");
		sql.append(" t3.xm,");
		sql.append(" t3.xb,");
		sql.append(" t3.xydm,");
		sql.append(" t3.nj,");
		sql.append(" t3.zydm,");
		sql.append(" t3.bjdm,");
		sql.append(" t3.bjmc,");
		sql.append(" t.xn,");
		sql.append(" t.rcxwlbdldm,");
		sql.append(" t.rcxwlbdm,");
		sql.append(" t1.rcxwlbdlmc,");
		sql.append(" t2.rcxwlbmc,");
		sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz");
		sql.append(" from xg_rcsw_rcxwjg t");
		sql.append(" left join xg_rcsw_rcxwdbdlb t1");
		sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
		sql.append(" left join xg_rcsw_rcxwlbdmb t2");
		sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
		sql.append(" left join view_xsjbxx t3");
		sql.append(" on t.xh = t3.xh");
		sql.append(" where not exists");
		sql.append(" (select 1 from xg_rcsw_new_shxj_qtjxszb x where t.id = x.rcxwjgid)");
		sql.append(" )t");
		sql.append(" where 1=1");
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
		this.setTableName("xg_rcsw_new_shxj_qtjxszb");
		this.setClass(RcxwmarkForm.class);
		this.setKey("id");
	}
	
	/**
	 *
	 * @描述: 已处理查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-31 上午10:54:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYclList(RcxwmarkForm t, User user)
	throws Exception {
        // TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append(" select t.id rcxwjgid,t4.id,");
		sql.append(" t.xh,");
		sql.append(" t3.xm,");
		sql.append(" t3.xb,");
		sql.append(" t3.xydm,");
		sql.append(" t3.nj,");
		sql.append(" t3.zydm,");
		sql.append(" t3.bjdm,");
		sql.append(" t3.bjmc,");
		sql.append(" t4.pjxn xn,");
		sql.append(" t.rcxwlbdldm,");
		sql.append(" t.rcxwlbdm,");
		sql.append(" t1.rcxwlbdlmc,");
		sql.append(" t2.rcxwlbmc,");
		sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz,");
		sql.append(" t4.jxdm,");
		sql.append(" decode(t4.jxdm,'1','专业奖学金','2','新生奖学金') xmmc");
		sql.append(" from xg_rcsw_new_shxj_qtjxszb t4 ");
		sql.append(" left join xg_rcsw_rcxwjg t ");
		sql.append(" on t.id = t4.rcxwjgid");
		sql.append(" left join  xg_rcsw_rcxwdbdlb t1");
		sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
		sql.append(" left join xg_rcsw_rcxwlbdmb t2");
		sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
		sql.append(" left join view_xsjbxx t3");
		sql.append(" on t.xh = t3.xh");
		
//		sql.append(" left join xg_pjpy_new_pjxmb t5");
//		sql.append(" on t4.jxdm = t5.xmdm");
//		sql.append(" where  exists");
//		sql.append(" (select 1 from xg_rcsw_new_shxj_qtjxszb x where t.id = x.rcxwjgid)");
		sql.append(" ) t");
		sql.append(" where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
     }
	 
	/**
	 * 
	 * @描述: 取标题名和读写权限
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-1 下午02:20:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param username
	 * @param dyym
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	 public HashMap<String, String> getWriteAble(String username,String dyym){
		 StringBuffer sql = new StringBuffer();
		 sql.append(" select nvl(dxq, '0') dxq,");
		 sql.append(" (select gnmkmc");
		 sql.append(" from gnmkdmb");
		 sql.append(" where gnmkdm = substr(a.gnmkdm, 0, 3)) || '-' ||");
		 sql.append(" (select gnmkmc");
		 sql.append(" from gnmkdmb");
		 sql.append(" where gnmkdm = substr(a.gnmkdm, 0, 5)) || '-' || gnmkmc title,");
		 sql.append(" gnmkmc");
		 sql.append(" from view_yhqx a");
		 sql.append(" where yhm = ?");
		 sql.append(" and dyym = ?");
		 return dao.getMapNotOut(sql.toString(), new String[]{username,dyym});
	 }
	 
	 /**
	  * 
	  * @描述:获取查看数据
	  * @作者：yxy[工号：1206]
	  * @日期：2016-11-1 下午06:56:53
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param id
	  * @return
	  * HashMap<String,String> 返回类型 
	  * @throws
	  */
	 public HashMap<String, String> getCkData(String id){
		 StringBuffer sql = new StringBuffer();
			sql.append(" select t.id rcxwjgid,");
			sql.append(" t.xh,");
			sql.append(" t3.xm,");
			sql.append(" t3.xydm,");
			sql.append(" t3.xymc,");
			sql.append(" t3.nj,");
			sql.append(" t3.zydm,");
			sql.append(" t3.zymc,");
			sql.append(" t3.bjdm,");
			sql.append(" t3.bjmc,");
			sql.append(" t4.pjxn xn,");
			sql.append(" t.rcxwlbdldm,");
			sql.append(" t.rcxwlbdm,");
			sql.append(" t1.rcxwlbdlmc,");
			sql.append(" t2.rcxwlbmc,");
			sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz,");
			sql.append(" t4.jxdm,");
			sql.append(" t4.bz,");
			sql.append(" decode(t4.jxdm,'1','专业奖学金','2','新生奖学金') xmmc");
			sql.append(" from xg_rcsw_new_shxj_qtjxszb t4 ");
			sql.append(" left join xg_rcsw_rcxwjg t ");
			sql.append(" on t.id = t4.rcxwjgid");
			sql.append(" left join  xg_rcsw_rcxwdbdlb t1");
			sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
			sql.append(" left join xg_rcsw_rcxwlbdmb t2");
			sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
			sql.append(" left join view_xsjbxx t3");
			sql.append(" on t.xh = t3.xh");
			sql.append(" where t4.id = ?");
		 return dao.getMapNotOut(sql.toString(), new String[]{id});
	 }
	 
	 /**
	  * 
	  * @描述: 批量设置保存
	  * @作者：yxy[工号：1206]
	  * @日期：2016-11-1 下午07:00:03
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * boolean 返回类型 
	  * @throws
	  */
	 public boolean insertData(String[] rcxwjgids,String jxdm,String pjxn,String bz,String czr){
		 StringBuffer sql = new StringBuffer();
		 List<String[]> params = new ArrayList<String[]>();
		 String czsj = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		 for (String strings : rcxwjgids) {
			 params.add(new String[]{strings,jxdm,pjxn,bz,czr,czsj});
		 }
		 sql.append(" insert into xg_rcsw_new_shxj_qtjxszb(rcxwjgid,jxdm,pjxn,bz,czr,czsj) values(?,?,?,?,?,?)");
		 boolean flag = true;
		 try {
			int[] lenArr = dao.runBatch(sql.toString(), params);
			for (int i = 0; i < lenArr.length; i++) {
				flag = (lenArr[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
			return flag;
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}
	 }
	 
	 /**
	  * 
	  * @描述:获取查看数据
	  * @作者：yxy[工号：1206]
	  * @日期：2016-11-1 下午06:56:53
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param id
	  * @return
	  * HashMap<String,String> 返回类型 
	  * @throws
	  */
	 public HashMap<String, String> getCkDataWcl(String rcxwjgid){
		 StringBuffer sql = new StringBuffer();
			sql.append(" select t.id rcxwjgid,");
			sql.append(" t.xh,");
			sql.append(" t3.xm,");
			sql.append(" t3.xydm,");
			sql.append(" t3.xymc,");
			sql.append(" t3.nj,");
			sql.append(" t3.zydm,");
			sql.append(" t3.zymc,");
			sql.append(" t3.bjdm,");
			sql.append(" t3.bjmc,");
			sql.append(" t.xn,");
			sql.append(" t.rcxwlbdldm,");
			sql.append(" t.rcxwlbdm,");
			sql.append(" t1.rcxwlbdlmc,");
			sql.append(" t2.rcxwlbmc,");
			sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz");
			sql.append(" from ");
			sql.append(" xg_rcsw_rcxwjg t ");
			sql.append(" left join  xg_rcsw_rcxwdbdlb t1");
			sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
			sql.append(" left join xg_rcsw_rcxwlbdmb t2");
			sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
			sql.append(" left join view_xsjbxx t3");
			sql.append(" on t.xh = t3.xh");
			sql.append(" where t.id = ?");
		 return dao.getMapNotOut(sql.toString(), new String[]{rcxwjgid});
	 }
	 
}
