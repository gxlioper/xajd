/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DtxxshDao extends SuperDAOImpl<DtxxshForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DtxxshForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append("select * from (select t1.*,b.jdmc,t2.xm,t2.xb,t2.nj,t2.xymc,t2.xydm,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,tt.sydm,tt.symc,t2.zybj,t2.zybjmc,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中') || ']' shztmc");
		sql.append(" , d.guid shid ");
		sql.append(" ,row_number() over(partition by t1.dtxxsqid order by d.shsj desc) rn ");
		sql.append(" from XG_DTJS_DTXXSQ t1");
		//学生信息
		sql.append(" left join view_xsxxb t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join XG_XTWH_SYBJGLB tx on t2.bjdm = tx.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB tt on tx.sydm = tt.sydm ");
		//阶段信息
		sql.append(" left join XG_DTJS_JBSZB b on t1.jddm=b.jddm");
		//审核状态信息
		sql.append(" left join xg_xtwh_shztb d");
		sql.append(" on t1.dtxxsqid = d.ywid");
		//审核岗位新信息
		sql.append(" left join xg_xtwh_spgwyh e");
		sql.append(" on d.gwid = e.spgw");
		sql.append(" left join xg_xtwh_spgw f");
		sql.append(" on d.gwid = f.id");
		sql.append(" where e.spyh = '"+user.getUserName()+"' and t1.shzt<>9 and d.shzt<>9 ");

		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		}else{
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}	
		sql.append(" ) a where rn = 1 )a ");
		sql.append(" where 1 = 1");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获得对应审核状态
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-12 上午10:06:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * String 返回类型 
	 */
	public String getShid(String ywid,String gwid){
		StringBuffer sql=new StringBuffer();
		sql.append("select guid from xg_xtwh_shztb sh where sh.ywid=? and sh.gwid=?");
		return dao.getOneRs(sql.toString(), new String[]{ywid,gwid}, "guid");
	}
	/**
	 * 
	 * @描述:修改申请信息状态（原更改方法不支持继承关系不能使用runUpdate(T t) 方法）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-24 下午07:22:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param zt
	 * @return boolean
	 * @throws Exception
	 * int 返回类型 
	 */
	public boolean updateSqxx(String id,String zt) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("update XG_DTJS_DTXXSQ set shzt=? where dtxxsqid=?");
		return dao.update(sql.toString(), new String[]{zt,id})>0?true:false;
	}
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DtxxshForm t)
			throws Exception {
		return null;
	}
	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("dtxxsqid");
		this.setTableName("XG_DTJS_DTXXSQ");
		this.setClass(DtxxshForm.class);
	}
	/**
	 * 
	 * @描述:删除对应党团信息申请的结果
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 上午09:43:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid 申请id
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 */
	public int deleteDtxxjgForDtxxsqId(String sqid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete XG_DTJS_XSDTXXJLB dtxxjg where dtxxjg.sqid=?");
		return dao.runDelete(sql.toString(),new String[]{sqid});
	}
}
