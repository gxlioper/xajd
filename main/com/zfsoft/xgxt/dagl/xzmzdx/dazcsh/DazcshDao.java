/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午03:08:39 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-5-14 下午03:28:27 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DazcshDao extends SuperDAOImpl<DazcshForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.sqid,t.splcid,");
		sql.append("t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs, '1', '邮寄', '2', '自带', t.zcfs) zcfsmc,t.zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq,t.sjlrr,t.sjlrsj, ");
		sql.append("t2.guid shid,t2.gwid,t2.shr,t2.shyj,t2.shzt,t4.mc || '[' ||");
		sql.append("decode(t2.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t4.gwz,");
		sql.append("row_number() over(partition by t.sqid order by t2.shsj desc) rn ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append("left join xg_xtwh_shztb t2 on t.sqid = t2.ywid ");
		sql.append("left join xg_xtwh_spgwyh t3 on t2.gwid = t3.spgw ");
		sql.append("left join xg_xtwh_spgw t4 on t2.gwid = t4.id ");
		sql.append("where t3.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t2.shzt <> '0' and t2.shzt <> '4')");
		}else{
			sql.append(" and (t2.shzt = '0' or t2.shzt = '4' )");
		}
		sql.append(" )a where 1 = 1 and rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(DazcshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_xsxx_dagl_dazcsqb");
	}
	
	/**
	 * @描述: 审核个人信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午05:49:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDazcshInfo(DazcshForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc, ");
		sql.append("decode(t.zcfs,'1','邮寄','2','自带',t.zcfs)zcfsmc,t.zcfs, ");
		sql.append("t.yjdz,t.yzbm,t.sjr,t.sjrdh,t.dwmc,t.dwdz,t.zddacn,t.yqtdrq, ");
		sql.append("t.sjlrr,t.sjlrsj,t.splcid, ");
		sql.append("t.shzt,decode(t.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t.shzt) shztmc ");
		sql.append("from xg_xsxx_dagl_dazcsqb t ");
		sql.append("left join view_xsbfxx t1 on t.xh = t1.xh ");
		sql.append(")a where 1 = 1 and sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
}
