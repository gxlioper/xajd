/**

 * @部门:学工产品事业部
 * @日期：2014-4-24 上午11:01:37 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询-咨询师管理
 * @类功能描述: 
 * @作者：  王志刚[工号:1060]
 * @时间： 2014-4-24 上午11:01:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxsxxDao extends SuperDAOImpl<ZxsxxForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLZX_ZXSXXB");
		setKey("zgh");
		setClass(ZxsxxForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxsxxForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.zgh,t.zxslv,t.zxszg,t.kjdrs,t.status,t.createsj,t.createbh,t.createmc,t.zxsjj,t.datazt,t.address,t1.xm,t1.xb,t1.lxdh,trunc((to_char(sysdate, 'yyyymmdd') - (to_char(to_date(t1.csrq,'yyyy-mm-dd'), 'yyyymmdd')))/10000) age,t1.bmmc ")
		   .append("from XG_XLZX_ZXSXXB t ")
		   .append("left join VIEW_FDYXX t1 ")
		   .append("on t.zgh = t1.zgh ")
		   .append("where 1=1 ")
		   .append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxsxxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/** 
	 * @描述:根据职工号查询教师信息
	 * @作者：王志刚 [工号：1060]
	 * @日期：2014-4-24 下午02:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(String zgh){
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select b.zgh,b.xm,b.xb,b.csrq, trunc((to_char(sysdate, 'yyyymmdd') - (to_char(to_date(b.csrq,'yyyy-mm-dd'), 'yyyymmdd')))/10000) age, b.bmdm, b.bmmc,b.lxdh" );
		sql.append(" from view_fdyxx b" ); 
		sql.append(" where b.zgh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/** 
	 * @描述:(根据职工号查询咨询师信息是否存在)
	 * @作者：王志刚 [工号：1060]
	 * @日期：2014-4-24 下午03:35:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * boolean 返回类型 
	 */
	public boolean zxsxxIsExist(String zgh) {
		String sql="select count(t.zgh) num from XG_XLZX_ZXSXXB t where t.zgh=?";
		String num = dao.getOneRs(sql.toString(), new String[]{zgh}, "num");
		return !num.equals("0");
	}
	
	/**
	 * 
	 * @描述:查出所有咨询师   
	 *       数据 例：徐木兴 [男][高教发展研究中心][20020964] [上限10人][已预约5人]
	 * @作者：王志刚
	 * @日期：2014-4-30 下午04:36:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.zgh,t.lxdh,t.address,t1.xm,t1.xb,t1.bmmc,t.kjdrs,t.zxsjj,");
	    sql.append("(case when t.kjdrs is null then '安排无上限' else '安排上限'||t.kjdrs||'人' end) kjdrsms,");
	    sql.append("(select count(g.zxid) from XG_XLZX_XLZXB_WZDX g where g.zxs = t.zgh and g.zzaprq = to_char(sysdate, 'yyyy-mm-dd')) yaprs ");
		sql.append(" ,'' yyrq " );
	    
	    // sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a where to_number(a.kjdrs) > to_number(a.yaprs) or a.kjdrs is null ");
		sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) ");
			
		  List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @描述:查出所有咨询师  (根据预约时间) 
	 *       数据 例：徐木兴 [男][高教发展研究中心][20020964] [上限10人][已预约5人]
	 * @作者：王志刚
	 * @日期：2014-4-30 下午04:36:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllByYysjList(String yysj){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.zgh,t.lxdh,t.address,t1.xm,t1.xb,t1.bmmc,t.kjdrs,");
		sql.append("(case when t.kjdrs is null then '安排无上限' else '安排上限'||t.kjdrs||'人' end) kjdrsms,");
		sql.append(" (select count(zxid) ");
		sql.append("  from ((select zxid, zzaprq,zxs from XG_XLZX_XLZXB_WZDX where sqid is null) ");
		sql.append("         union (select a.zxid, a.zzaprq,a.zxs ");
		sql.append("                   from XG_XLZX_XLZXB_WZDX a ");
		sql.append("                  inner join XG_XLZX_YYSQB_WZDX b ");
		sql.append("                    on b.sqid = a.sqid	 ");
		sql.append("                 where b.yyzt = '2')) a ");
		sql.append("   where a.zzaprq = '" + yysj + "' and a.zxs = t.zgh ");
		sql.append("     ) yaprs ");
		sql.append(" ,'" +yysj + "' yyrq " );
		//sql.append("(select count(g.zxid) from XG_XLZX_XLZXB_WZDX g where g.zxs = t.zgh and g.zzaprq = '"+yysj+"') yaprs ");
		//sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a where to_number(a.kjdrs) > to_number(a.yaprs) or a.kjdrs is null ");
		sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a ");		
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @描述:设置咨询师在岗状态
	 * @作者：王志刚
	 * @日期：2014-4-25 上午09:14:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh 职工号
	 * @param status 在岗状态
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxsxxStatus(String zgh, String status) throws Exception{
		String sql = "update XG_XLZX_ZXSXXB set STATUS=? where ZGH in ("+zgh+")";
		boolean result = dao.runUpdate(sql, new String[] { status });
		return result;
	}
	
	/**
	 * 
	 * @描述:设置咨询预约说明
	 * @作者：王志刚
	 * @日期：2014-4-25 上午09:43:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zxyysm 咨询预约说明
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxyysm(String zxyysm) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("MERGE INTO xljk_zxyysm a ")
		   .append("USING (select count(1) count from xljk_zxyysm) b ")
		   .append("ON (b.count>0) ")
		   .append("when matched then ")
		   .append("update set zxyysm = ? ")
		   .append("when not matched then ")
		   .append("insert values( ? ) ");
		boolean result = dao.runUpdate(sql.toString(), new String[] { zxyysm,zxyysm });
		return result;
	}
	
	/**
	 * 
	 * @描述:查询咨询预约说明
	 * @作者：王志刚
	 * @日期：2014-4-25 上午10:14:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param 
	 * @return
	 * boolean 返回类型 
	 */
	public String getZxyysm() throws Exception{
		String sql="select t.zxyysm from xljk_zxyysm t ";
		String zxyysm = dao.getOneRs(sql.toString(), new String[]{}, "zxyysm");
		return zxyysm;
	}

}
