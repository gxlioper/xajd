/**
 * @部门:学工产品事业部
 * @日期：2013-6-4 下午05:06:22 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块 
 * @类功能描述: TODO 辅导员任职管理 任职申请
 * @作者： zhangjw 
 * @时间： 2013-6-4 下午04:56:01 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyrzshDAO extends SuperDAOImpl<FdyrzsqForm>{

	/*
	 * 描述: 
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: 查询需要审核的任职申请
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" select * from ( ");
		sql.append("  select e.bmmc,a.zgh,e.xm,a.sqsj, a.sqdbgs ,a.sqid, b.guid shid,b.gwid,c.mc ||'['|| decode(b.shzt,0,'待审核',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')||']' shzt,a.splc,b.shsj,t.zjz,t.bmdm   ");
		sql.append(" ,row_number()over(partition by a.sqid order by b.shsj desc) rn ");
		sql.append(" from xg_szdw_fdyrzsqb a ");
		sql.append("  join xg_xtwh_shztb b on a.sqid = b.ywid  ");
		sql.append(" join xg_xtwh_spgw c  on b.gwid = c.id ");
		sql.append(" join xg_xtwh_spgwyh d on c.id = d.spgw join view_fdyxx e on a.zgh = e.zgh left join fdyxxb t on a.zgh = t.zgh");
		sql.append(" where d.spyh = '"+user.getUserName()+"' and a.shzt<>9  and b.shzt <>9");
		String shlx = model.getShlx();

		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}
		sql.append("  )a where rn = 1 ");
		sql.append(searchTj);
		if (!"xx".equalsIgnoreCase(user.getUserType())&&!"admin".equalsIgnoreCase(user.getUserType())) {

			sql.append(" and bmdm='" + user.getUserDep() + "' ");
		}
		sql.append(" order by shsj desc");
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	 * 描述: 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_szdw_fdyrzsqb");
		super.setKey("sqid");
	}
	
	/**
	 * @描述:查询班级列表根据申请编号
	 * @作者：zhangjw
	 * @日期：2013-6-7 下午02:12:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjListBysqid(String sqid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.nj,b.xymc,b.zymc,b.bjmc,count(a.xh) bjrs,b.bjdm from xg_szdw_fdyrzsqbjb c, view_njxyzybj_all b,xsxxb a  where c.bjdm=a.bjdm and b.bjdm = a.bjdm  ");
		sql.append(" and c.sqbh = ? ");
		sql.append(" group by b.nj,b.xymc,b.zymc,b.bjmc,b.bjdm ");
		return dao.getList(sql.toString(), new String[]{sqid}, new String[]{"nj","xymc","zymc","bjmc","bjrs"});
	}
	/**
	 * 
	 * @描述:TODO(更新)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-10 上午10:43:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int updateFdyrzsq(FdyrzsqForm model) throws Exception{
		String sql = " update xg_szdw_fdyrzsqb b set b.shzt=? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSqid()});
	}
	
}
