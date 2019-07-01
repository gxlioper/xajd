/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:06:04 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-28 下午05:06:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzSqDao extends SuperDAOImpl<JcftzSqForm>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcftzSqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * @修改 by yxy sql文格式进行修改，方便查看和修改
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcftzSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String userName = user.getUserName();
		StringBuilder sql = new StringBuilder();
//		sql.append(" select t.* from ( ");
//		sql.append(" select t1.*,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc, nvl(t6.xm,t7.xm) sbrxm,nvl(t8.num,0)ybjrs,");
//		sql.append(" decode(t1.xfrdsqzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.xfrdsqzt) shztmc, ");
//		sql.append(" case when ((t1.xfrdsqzt = '0' and t9.num > 0)) then '0' else '1' end rdzt ");
//		//sql.append("case when '"+user.getUserName()+"' in (select yhm from xg_sztz_glyb) then 'true' when sbr ='"+user.getUserName()+"' then 'true' else 'false' end sfkxg");
//		sql.append(" from xg_sztz_xmjg t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
//		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm left join yhb t6 on t1.sbr= t6.yhm left join xsxxb t7 on t1.sbr=t7.xh");
//		sql.append(" left join(select count(xh)num,xmdm from XG_SZTZ_XS_SQJG group by xmdm)t8 on t1.xmdm=t8.xmdm ");
//		sql.append(" left join(select count(xh)num,xmdm from XG_SZTZ_JCFTZ_JG group by xmdm)t9 on t1.xmdm=t9.xmdm ");
//		sql.append(" order by t1.xfrdsqzt desc,t1.xmkssj desc) t where 1=1 and t.sbr = '"+ userName +"' and t.ybjrs <> 0 and t.rdzt = '1' ");
		sql.append(" select t.*");
		sql.append(" from (select t1.*,");
		sql.append(" decode(t1.csms,1,'个人',2,'团体',t1.csms) csmsmc,");
		sql.append(" t2.xmjbmc,");
		sql.append(" t3.sskmmc,");
		sql.append(" t4.bmmc sbbmmc,");
		sql.append(" t5.xqmc,");
		sql.append(" nvl(t6.xm, t7.xm) sbrxm,");
		sql.append(" nvl(t8.num, 0) ybjrs,");
		sql.append(" decode(t1.xfrdsqzt,");
		sql.append(" '0',");
		sql.append(" '未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '退回',");
		sql.append(" '5',");
		sql.append(" '审核中',");
		sql.append(" t1.xfrdsqzt) shztmc,");
		sql.append(" case");
		sql.append(" when ((t1.xfrdsqzt = '0' and t9.num > 0)) then");
		sql.append(" '0'");
		sql.append(" else");
		sql.append(" '1'");
		sql.append(" end rdzt");
		sql.append(" from xg_sztz_xmjg t1");
		sql.append(" left join xg_sztz_xmjb t2");
		sql.append(" on t1.xmjbdm = t2.xmjbdm");
		sql.append(" left join xg_sztz_sskm t3");
		sql.append(" on t1.sskmdm = t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4");
		sql.append(" on t1.sbbmdm = t4.bmdm");
		sql.append(" left join xqdzb t5");
		sql.append(" on t1.xq = t5.xqdm");
		sql.append(" left join yhb t6");
		sql.append(" on t1.sbr = t6.yhm");
		sql.append(" left join xsxxb t7");
		sql.append(" on t1.sbr = t7.xh");
		sql.append(" left join (select count(xh) num, xmdm");
		sql.append(" from XG_SZTZ_XS_SQJG");
		sql.append(" group by xmdm");
		sql.append(" union all");
		sql.append(" select count(ttjgid) num,xmdm ");
		sql.append(" from xg_sztz_ttxmjg  group by xmdm ");
		sql.append(" ) t8");
		sql.append(" on t1.xmdm = t8.xmdm");
		sql.append(" left join (select count(xh) num, xmdm");
		sql.append(" from XG_SZTZ_JCFTZ_JG");
		sql.append(" group by xmdm");
		sql.append(" ) t9");
		sql.append(" on t1.xmdm = t9.xmdm");
		sql.append(" order by t1.xfrdsqzt desc, t1.xmkssj desc) t");
		sql.append(" where 1 = 1");
		sql.append(" and t.sbr = '"+userName+"'");
		sql.append(" and t.ybjrs <> 0");
		sql.append(" and t.rdzt = '1'");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getPageListForRenDing(JcftzSqForm t, User user)
		throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,t7.xm,t10.bjmc,t8.jcxf,t8.xfrdsqzt,t1.ylzd1 jxdm,t1.ylzd3 tzhjcf,nvl(t1.ylzd2,'0') sfqq,t3.jxmc, ");
		sql.append(" (case nvl(t1.ylzd3,'0') when '0' then (0+to_number(nvl(t3.fjxf,'0'))) else (to_number(t1.ylzd3)+to_number(nvl(t3.fjxf,'0'))) end) zf, ");
		sql.append(" decode(t1.ylzd2,'0','否','1','是','否') sfqqmc, ");
		sql.append(" decode(t1.ylzd2,'0','否','1','是',ylzd2) sfqqrs ");
		sql.append(" from xg_sztz_xs_sqjg t1 ");
		sql.append(" left join xg_sztz_xm_jx t3 on t1.ylzd1 = t3.jgid ");
		sql.append(" left join xsxxb t7 on t1.xh = t7.xh ");
		sql.append(" left join view_njxyzybj t10 on t7.bjdm = t10.bjdm ");
		sql.append(" left join xg_sztz_xmjg t8 on t1.xmdm = t8.xmdm ");		
		sql.append(" where t1.xmdm = ?");
		if(null != t.getCxmc()&& !"".equals(t.getCxmc())){
			sql.append(" and (t1.xh like '"+ t.getCxmc() +"%' ");
			sql.append(" or t7.xm like '" + t.getCxmc() +"%') ");
		}
		sql.append(") t");
		if(null!=t.getWay()){
			sql.append(" order by t.zf desc,t.tzhjcf desc,t.xh ");
		}
		return getPageList(t, sql.toString(), new String[]{t.getXmdm()});
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(JcftzSqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_SZTZ_XMJG");		
	}
	
	public HashMap<String, String> getXmxx(String xmdm){
		String sql = "select t1.jgid,t1.csms,t1.xn,t2.xqmc,t1.xmmc,t1.jcxf from xg_sztz_xmjg t1 left join xqdzb t2 on t1.xq = t2.xqdm where t1.xmdm = ? ";
		return dao.getMapNotOut(sql, new String []{xmdm});
	}
	
	public HashMap<String, String> getXmxxByjgid(String jgid){
		String sql = "select t1.jgid,t1.xn,t2.xqmc,t1.xmmc,t1.jcxf from xg_sztz_xmjg t1 left join xqdzb t2 on t1.xq = t2.xqdm where t1.jgid = ? ";
		return dao.getMapNotOut(sql, new String []{jgid});
	} 
	
	/** 
	 * @描述:实时保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午02:27:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveJcftzSq(JcftzSqForm t) throws Exception{
		if(null != t.getTzhjcf()&& !"".equals(t.getTzhjcf())){
			String sql = "update xg_sztz_xs_sqjg set ylzd3 = ? where jgid = ?";
			dao.runUpdate(sql, new String[]{t.getTzhjcf(),t.getJgid()});
		}
		if(null != t.getSfqq()&& !"".equals(t.getSfqq())){
			String sql = "update xg_sztz_xs_sqjg set ylzd2 = ? where jgid = ?";
			dao.runUpdate(sql, new String[]{t.getSfqq(),t.getJgid()});
		}
		if(null != t.getJxdm()&& !"".equals(t.getJxdm())){
			String sql = "update xg_sztz_xs_sqjg set ylzd1 = ? where jgid = ?";
			dao.runUpdate(sql, new String[]{t.getJxdm(),t.getJgid()});
		}
		if("saveJxdm".equalsIgnoreCase(t.getType())){
			String sql = "update xg_sztz_xs_sqjg set ylzd1 = '' where jgid = ?";
			dao.runUpdate(sql, new String[]{t.getJgid()});
		}
		//临时将xm设定为要修改的列名，bz1为修改的值
		if(StringUtils.isNotNull(t.getXm())){
			String sql="update xg_sztz_xs_sqjg set "+t.getXm()+"=? where jgid = ?";
			dao.runUpdate(sql, new String[]{t.getBz1(),t.getJgid()});
		}
		return true;	
	}
	
	public boolean checkFz(String jgid) throws Exception{
		String sql = "select ylzd3 fz from xg_sztz_xs_sqjg where jgid = ?";
		String fz =  dao.getOneRs(sql, new String[]{jgid}, "fz");
		return (fz != null && !fz.equalsIgnoreCase(""));
	}
	
	public boolean checkQq(String jgid) throws Exception{
		String sql = "select ylzd2 qq from xg_sztz_xs_sqjg where jgid = ?";
		String fz =  dao.getOneRs(sql, new String[]{jgid}, "qq");
		return (fz != null && !fz.equalsIgnoreCase(""));
	}
	
	public boolean tj(String jgid, User user,JcftzSqForm t) throws Exception{
		String tjsj = GetTime.getTimeByFormat(DATE_FORMAT);
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_sztz_xmjg set xfrdsqzt = ?,tjsj ='" + tjsj +"',rdsplc = ?,rdsqid = ? ");
		sql.append("where jgid = ?");
		return dao.runUpdate(sql.toString(), new String[]{t.getXfrdsqzt(),t.getRdsplc(),t.getSqid(),jgid} );
	}
	
	public List<HashMap<String, String>> getJcfTzRyxx(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.xmdm,t1.jgid,t1.xh,t1.ylzd3 tzhjcf,t1.ylzd1 jxdm,t1.ylzd2 sfqq,t1.bz1,t1.bz2,t1.bz3,t1.bz4,t1.bz5 from xg_sztz_xs_sqjg t1 where t1.xmdm = ? ");
		sql.append(" ) t ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	public String getShlc() {
		String sql = "select splc from xg_sztz_jcftz_sq_cssz ";
		return dao.getOneRs(sql, new String[]{}, "splc");
	}
	
	public String getFs(String jxdm){
		String sql = "select fjxf from xg_sztz_xm_jx where jgid = ?";
		return dao.getOneRs(sql, new String[]{jxdm}, "fjxf");
	}
	
	/**
	 * 
	 * @描述:团体项目上报查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午01:42:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForTtRenDing(JcftzSqForm t, User user)
	throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select t1.*,");
		sql.append(" t7.xm,");
		sql.append(" t10.bjmc, ");
	    sql.append(" t10.xymc,");
		sql.append(" t8.jcxf,");
		sql.append(" t8.xfrdsqzt,");
		sql.append(" t1.ylzd1 jxdm,");
		sql.append(" t1.ylzd3 tzhjcf,");
		sql.append(" nvl(t1.ylzd2, '0') sfqq,");
		sql.append(" t3.jxmc,");
		sql.append(" (case nvl(t1.ylzd3, '0')");
		sql.append(" when '0' then");
		sql.append(" (0 + to_number(nvl(t3.fjxf, '0')))");
		sql.append(" else");
		sql.append(" (to_number(t1.ylzd3) + to_number(nvl(t3.fjxf, '0')))");
		sql.append(" end) zf,");
		sql.append(" decode(t1.ylzd2, '0', '否', '1', '是', '否') sfqqmc,");
		sql.append(" decode(t1.ylzd2, '0', '否', '1', '是', ylzd2) sfqqrs,");
		sql.append(" nvl(t11.num,0) cys");
		sql.append(" from xg_sztz_ttxmjg t1");
		sql.append(" left join xg_sztz_xm_jx t3");
		sql.append(" on t1.ylzd1 = t3.jgid");
		sql.append(" left join xsxxb t7");
		sql.append(" on t1.dzxh = t7.xh");
		sql.append(" left join view_njxyzybj t10");
		sql.append(" on t7.bjdm = t10.bjdm");
		sql.append(" left join xg_sztz_xmjg t8");
		sql.append(" on t1.xmdm = t8.xmdm");
		sql.append(" left join (select count(1) num,ttsqid ttjgid from xg_sztz_ttcy group by ttsqid ) t11");
		sql.append(" on t1.ttjgid = t11.ttjgid");
		sql.append("  where t1.xmdm = ?");
		if(null != t.getCxmc()&& !"".equals(t.getCxmc())){
			sql.append(" and (t1.dzxh like '"+ t.getCxmc() +"%' ");
			sql.append(" or t7.xm like '" + t.getCxmc() +"%') ");
		}
		sql.append(") t");
		if(null!=t.getWay()){
			sql.append(" order by t.zf desc,t.tzhjcf desc,t.xh ");
		}
		return getPageList(t, sql.toString(), new String[]{t.getXmdm()});
   }
	
	/** 
	 * @描述:团体项目实时保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午02:27:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveJcftzTtSq(JcftzSqForm t) throws Exception{
		if(null != t.getTzhjcf()&& !"".equals(t.getTzhjcf())){
			String sql = "update xg_sztz_ttxmjg set ylzd3 = ? where ttjgid = ?";
			dao.runUpdate(sql, new String[]{t.getTzhjcf(),t.getJgid()});
		}
		if(null != t.getSfqq()&& !"".equals(t.getSfqq())){
			String sql = "update xg_sztz_ttxmjg set ylzd2 = ? where ttjgid = ?";
			dao.runUpdate(sql, new String[]{t.getSfqq(),t.getJgid()});
		}
		if(null != t.getJxdm()&& !"".equals(t.getJxdm())){
			String sql = "update xg_sztz_ttxmjg set ylzd1 = ? where ttjgid = ?";
			dao.runUpdate(sql, new String[]{t.getJxdm(),t.getJgid()});
		}
		if("saveJxdm".equalsIgnoreCase(t.getType())){
			String sql = "update xg_sztz_ttxmjg set ylzd1 = '' where ttjgid = ?";
			dao.runUpdate(sql, new String[]{t.getJgid()});
		}
		//临时将xm设定为要修改的列名，bz1为修改的值
		if(StringUtils.isNotNull(t.getXm())){
			String sql="update xg_sztz_ttxmjg set "+t.getXm()+"=? where ttjgid = ?";
			dao.runUpdate(sql, new String[]{t.getBz1(),t.getJgid()});
		}
		return true;	
	}
	
	/**
	 * 
	 * @描述: 获取团队信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午04:19:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcfTzTtxx(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.xmdm,t1.ttjgid jgid,t1.dzxh xh,t1.ylzd3 tzhjcf,t1.ylzd1 jxdm,t1.ylzd2 sfqq from xg_sztz_ttxmjg t1 where t1.xmdm = ? ");
		sql.append(" ) t ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * 
	 * @描述: 检查团队分值
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午04:24:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkTtFz(String jgid) throws Exception{
		String sql = "select ylzd3 fz from xg_sztz_ttxmjg where ttjgid = ?";
		String fz =  dao.getOneRs(sql, new String[]{jgid}, "fz");
		return (fz != null && !fz.equalsIgnoreCase(""));
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午04:32:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkTtQq(String jgid) throws Exception{
		String sql = "select ylzd2 qq from xg_sztz_ttxmjg where ttjgid = ?";
		String fz =  dao.getOneRs(sql, new String[]{jgid}, "qq");
		return (fz != null && !fz.equalsIgnoreCase(""));
	}
	 
	/**
	 * 
	 * @描述: 获取团队成员信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午04:19:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcfTzTtCyxx(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.xmdm,t1.ttjgid jgid,t2.xh,t1.ylzd3 tzhjcf,t1.ylzd1 jxdm,t1.ylzd2 sfqq,t1.bz1,t1.bz2,t1.bz3,t1.bz4,t1.bz5 from xg_sztz_ttxmjg t1 " +
				" left join xg_sztz_ttcy t2" +
				" on t1.xmdm = t2.xmdm and t1.ttjgid = t2.ttsqid" +
				" where t1.xmdm = ? ");
		sql.append(" ) t ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
}
