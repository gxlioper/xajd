/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:46:55 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:46:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmShDao extends SuperDAOImpl<TttzxmShForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmShForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getSearchSztzShTjByUser(user, "t", "sbbmdm", "sbr");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select ");
		sql.append(" t.*,");
		sql.append(" nvl(t1.tdnum,0) tdnum,");
		sql.append(" t2.xm dzxm,");
		sql.append(" t2.xb,");
		sql.append(" t2.xymc,");
		sql.append(" t2.xydm,");
		sql.append(" t2.nj,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.bjdm,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.xmmc,");
		sql.append(" t3.xmkssj,");
		sql.append(" t4.xmjbmc,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.gwid,");
		sql.append(" t5.shr,");
		sql.append(" t5.shyj,");
		sql.append(" t3.xfrdsqzt,");
		sql.append(" t3.xfrdjgzt,");
		sql.append(" t3.xn,");
		sql.append(" t3.xq,");
		sql.append(" t3.sbbmdm,");
		sql.append(" t3.sskmdm,");
		sql.append(" t3.xmjbdm,");
		sql.append(" t8.sskmmc,");
		sql.append(" t.dzxh xh,");
		sql.append(" t7.mc || '[' ||");
		sql.append(" decode(t5.shzt,");
		sql.append("  '0',");
		sql.append(" '未审核',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '退回',");
		sql.append(" '4',");
		sql.append(" '需重审',");
		sql.append(" '5',");
		sql.append(" '审核中') || ']' shztmc,");
		sql.append(" t7.gwz,");		
		sql.append(" row_number() over(partition by t.ttsqid order by t5.shsj desc) rn");
		sql.append(" from xg_sztz_ttxmsq t ");
		sql.append(" left join (select count(1) tdnum,xmdm,ttsqid from xg_sztz_ttcy group by xmdm,ttsqid) t1");
		sql.append(" on t.ttsqid = t1.ttsqid");
		sql.append(" left join  view_xsjbxx t2");
		sql.append(" on t.dzxh = t2.xh");
		sql.append(" left join xg_sztz_xmjg t3");
		sql.append(" on t.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_xmjb t4");
		sql.append(" on t3.xmjbdm = t4.xmjbdm");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.ttsqid = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7");
		sql.append(" on t5.gwid = t7.id");
		sql.append(" left join xg_sztz_sskm t8");
		sql.append(" on t3.sskmdm = t8.sskmdm");
		sql.append(" where t6.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
		}
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(") t where 1= 1  ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(TttzxmShForm.class);
		this.setTableName("xg_sztz_ttxmsq");
		this.setKey("ttsqid");
	}
	
   /**
    * 
    * @描述:获取项目中设置的人数控制级别
    * @作者：yxy[工号：1206]
    * @日期：2016-7-28 上午11:24:49
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param xmdm
    * @return
    * String 返回类型 
    * @throws
    */
   public String getRskzXh(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xh from xg_sztz_xmjg a left join XG_XTWH_SPBZ b on a.splc=b.splc and b.spgw=(replace(a.rskzjb,',','')) where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "xh");
	}
   	
   	/**
   	 * @描述:获取项目信息
   	 * @作者：yxy[工号：1206]
   	 * @日期：2016-7-28 上午11:28:04
   	 * @修改记录: 修改者名字-修改日期-修改内容
   	 * @param xmdm
   	 * @return
   	 * @throws Exception
   	 * HashMap<String,String> 返回类型 
   	 * @throws
   	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_sztz_xmjg ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @描述:获取人数级别控制的通过人数
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 上午11:28:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwid
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
    public String getTgrs( String gwid, String xmdm){
		
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.sqs + b.jgs count");
		sql.append("  from (select count(1) sqs");
		sql.append("  from (select t1.ttsqid,");
		sql.append("  t2.shzt,");
		sql.append("  t2.gwid,");
		sql.append("  t1.shzt flag,");
		sql.append("  t1.xmdm,");
		sql.append("  row_number() over(partition by t1.ttsqid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from xg_sztz_ttxmsq t1");
		sql.append("  left join xg_xtwh_shztb t2");
		sql.append("  on t1.ttsqid = t2.ywid)");
		sql.append("  where lvl = 1");
		sql.append("  and shzt = '1'");
		sql.append("  and gwid = ?");
		sql.append("  and xmdm = ?");
		sql.append("  and flag != '2' and flag != '3') a,");//过滤掉不通过，已退回的数据
		sql.append("  (select count(1) jgs");
		sql.append("   from xg_sztz_ttxmjg");
		sql.append("  where ttjgid not in");
		sql.append("  (select ttsqid from xg_sztz_ttxmsq where xmdm = ?)");
		sql.append("  and xmdm = ?) b");
		sql.append("   where 1 = 1");
		return dao.getOneRs(sql.toString(), new String[]{gwid,xmdm,xmdm,xmdm}, "count");
	}
}
