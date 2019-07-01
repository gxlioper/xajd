/**
 * @部门:学工产品事业部
 * @日期：2015-7-17 上午09:56:13 
 */  
package com.zfsoft.xgxt.xsztz.tzxmjg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-17 上午09:56:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsXmJgDao extends SuperDAOImpl<XsXmJgForm> {

	/*
    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
*/

	@Override
	public List<HashMap<String, String>> getPageList(XsXmJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(XsXmJgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.sskmdm,t2.sskmmc,t4.xmjbdm,t4.xmjbmc,t3.xmkssj,t3.jcxf,t3.xfrdsqzt,t3.xfrdjgzt,t5.bjdm,t5.bjmc,t6.bmmc sbbmmc,t5.xb,");
		sql.append("t5.nj,t5.zydm,t5.zymc,t5.xydm,t5.xymc,t5.xm,t3.xmmc xmmc1,  ");
		sql.append("  t7.xqmc   ");
		sql.append("  from XG_SZTZ_XS_SQJG t1");
		sql.append(" left join xg_sztz_xmjg t3");
		sql.append(" on t1.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_sskm t2");
		sql.append(" on t3.sskmdm = t2.sskmdm");
		sql.append(" left join xg_sztz_xmjb t4");
		sql.append(" on t3.xmjbdm = t4.xmjbdm ");
		sql.append(" left join view_xsjbxx t5");
		sql.append(" on t1.xh = t5.xh ");
		sql.append(" left join zxbz_xxbmdm t6");
		sql.append(" on t3.sbbmdm = t6.bmdm");
		sql.append(" left join xqdzb t7");
		sql.append(" on t1.xq = t7.xqdm ");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:学生个人学分查询
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-21 下午03:55:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> grxfcxList(XsXmJgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
//		sql.append("select * from (");
//		sql.append("select t.*,case when zxfs>100 then 100 else zxfs end zxf from("); 
//		sql.append("select t1.jgid,t2.jgid xmjgid,t1.xmdm,t1.xn,t1.xq,t1.xh,t1.sqsj,t1.sjly,t1.ylzd1,t1.ylzd2,t1.ylzd3,t1.ylzd4,t1.ylzd5,");
//		sql.append("t2.sfrm,t2.xmkssj,t2.jcxf,t2.xmmc,t2.xmjbdm,t2.sskmdm,t3.xmjbmc,t4.sskmmc,t5.xqmc,t6.xm,t6.nj,t6.xydm,t6.xymc,");
//		sql.append("t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,t6.xb,t7.jxmc,(nvl(to_number(t7.fjxf),0)+to_number(t2.jcxf))zxfs ");
//		sql.append(" from XG_SZTZ_XS_SQJG t1 left join xg_sztz_xmjg t2 on t1.xmdm=t2.xmdm left join xg_sztz_xmjb t3");
//		sql.append(" on t2.xmjbdm=t3.xmjbdm left join xg_sztz_sskm t4 on t2.sskmdm=t4.sskmdm left join xqdzb t5 on t1.xq=t5.xqdm ");
//		sql.append("left join view_xsbfxx t6 on t1.xh=t6.xh left join xg_sztz_xm_jx t7 on t1.ylzd1=t7.jgid");
//		sql.append(" union all");
//		sql.append(" select t1.sqid jgid,sqid xmjgid,'' xmdm,t1.xn,t1.xq,t1.xh,t1.sqsj,t1.sjly,t1.ylzd1,t1.ylzd2,t1.ylzd3,t1.ylzd4,t1.ylzd5,");
//		sql.append(" '' sfrm,t1.xmkssj,t1.zxf jcxf,t1.xmmc,t1.xmjbdm,t1.sskmdm,t2.xmjbmc,t3.sskmmc,t5.xqmc,t4.xm,t4.nj,t4.xydm,t4.xymc,");
//		sql.append("t4.zydm,t4.zymc,t4.bjdm,t4.bjmc,t4.xb,t1.hdjx jxmc,to_number(t1.zxf) zxf ");
//		sql.append(" from XG_SZTZ_XS_XWSQJG t1 ");
//		sql.append(" left join xg_sztz_xmjb t2 ");
//		sql.append(" on t1.xmjbdm = t2.xmjbdm ");
//		sql.append(" left join xg_sztz_sskm t3 ");
//		sql.append(" on t1.sskmdm = t3.sskmdm ");
//		sql.append(" left join view_xsjbxx t4 ");
//		sql.append(" on t1.xh = t4.xh ");
//		sql.append(" left join xqdzb t5 ");
//		sql.append("  on t1.xq = t5.xqdm ");
//		sql.append(" )t)t where 1= 1  ");
		sql.append(" select t.* from ( select t1.*,t2.xm,t2.xb,t2.nj,t2.xydm,t2.bjdm,t2.zydm,t11.jgid xsckjgid,t10.jgid xmckjgid,decode(t3.csms,'1','个人','2','团体') csmsmc,t3.csms,t3.jcxf, ");
		sql.append(" t3.xn,t3.xq,t3.xmmc,t3.sskmdm,t3.xmjbdm,t3.xmkssj,t4.xqmc,t5.sskmmc,t6.xmjbmc,t7.jxmc, ");
		sql.append(" (case nvl(t1.jxdm,'0') when '0' then (to_number(t1.tzhjcf)) else (to_number(t1.tzhjcf)+to_number(nvl(t7.fjxf,'0'))) end) zxf ");
		sql.append(" from xg_sztz_jcftz_jg t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_sztz_xmjg t3 on t1.xmdm = t3.xmdm ");
		sql.append(" left join xqdzb t4 on t3.xq = t4.xqdm ");
		sql.append(" left join xg_sztz_sskm t5 on t3.sskmdm = t5.sskmdm ");
		sql.append(" left join xg_sztz_xmjb t6 on t3.xmjbdm = t6.xmjbdm ");
		sql.append(" left join xg_sztz_xm_jx t7 on t1.jxdm = t7.jgid ");
		sql.append(" left join xg_sztz_xs_sqjg t11 on t1.xmdm = t11.xmdm and t1.xh = t11.xh ");
		sql.append(" left join xg_sztz_xmjg t10 on t1.xmdm = t10.xmdm ");
		sql.append(" ) t where 1=1 ");
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
		super.setClass(XsXmJgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_SZTZ_XS_SQJG");
	}
	
	
	/**
	* 
	* @描述:重复判断2
	* @作者：喻鑫源[工号：1206]
	* @日期：2015-7-10 下午04:37:04
	* @修改记录: 修改者名字-修改日期-修改内容
	* @param model
	* @return
	* boolean 返回类型 
	* @throws
	*/
	public boolean checkExistForSave(XsXmJgForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(jgid) sqid from xg_sztz_xs_sqjg t where t.xh = ? and t.xn = ? and t.xmdm = ? and t.xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	/**
	* 
	* @描述:TODO(这里用一句话描述这个方法的作用)
	* @作者：喻鑫源[工号：1206]
	* @日期：2015-7-13 上午11:03:05
	* @修改记录: 修改者名字-修改日期-修改内容
	* @param t
	* @param user
	* @return
	* @throws Exception
	* List<HashMap<String,String>> 返回类型 
	* @throws
	*/
	public List<HashMap<String, String>> getXmlist(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*, nvl(t2.sqrs,0) sqrs from (select t.*, nvl(t1.tgrs,0) tgrs");
		sql.append(" from (select t.*, t1.sskmmc, t2.xmjbmc, t3.bmmc sbbmmc, t4.xqmc, case  when exists (select 1 ");
        sql.append("   from xg_sztz_xs_sqjg c  where t.xmdm = c.xmdm  and xh = ?) then  '1' else '0'  end sfsq, ");       
        sql.append(" case when exists (select 1 from xg_sztz_xmjg t11 where t.xmdm = t11.xmdm and t11.xfrdsqzt in ('0','3') and t11.xfrdjgzt = '0') then '1' else '0' end sfrd ");              
		sql.append(" from xg_sztz_xmjg t");
		sql.append(" left join xg_sztz_sskm t1");
		sql.append(" on t1.sskmdm = t.sskmdm");
		sql.append(" left join xg_sztz_xmjb t2");
		sql.append(" on t.xmjbdm = t2.xmjbdm");
		sql.append(" left join zxbz_xxbmdm  t3");
		sql.append(" on t.sbbmdm = t3.bmdm");
		sql.append(" left join xqdzb t4");
		sql.append(" on  t.xq = t4.xqdm where  t.sqkg = 1 ) t");
		sql.append(" left join (select count(1) tgrs, t.xmdm");
		sql.append(" from xg_sztz_xs_sqjg t");
		sql.append(" where xn = ? and xq = ?  group by t.xmdm) t1");
		sql.append("  on t.xmdm = t1.xmdm) t1 left join");
		sql.append("  (select count(1) sqrs, t.xmdm");
		sql.append("  from xg_sztz_xsxmsq t");
		sql.append("  where xn = ? and xq = ? and shzt <> 3 and shzt <> 2 group by t.xmdm) t2 on t1.xmdm = t2.xmdm");
		sql.append(" )t where 1= 1  and csms = '1'");
		sql.append(" order by sfrm desc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,Base.currXn,Base.currXq,Base.currXn,Base.currXq});
	}
	
	
	
	/**
	* 
	* @描述:获取修改查看时的活动信息
	* @作者：喻鑫源[工号：1206]
	* @日期：2015-7-15 下午08:09:16
	* @修改记录: 修改者名字-修改日期-修改内容
	* @param xmdm
	* @return
	* HashMap<String,String> 返回类型 
	* @throws
	*/
	public HashMap<String, String> getHdMap(String xmdm,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		sql.append("  select t.*,nvl(t1.tgrs,0) tgrs from ");
		sql.append("  (select t.*,nvl(t1.sqrs,0) sqrs from ");
		sql.append("  (select t.*, t1.sskmmc, t2.xmjbmc, t3.bmmc sbbmmc,t4.xqmc xqmc ");
		sql.append("  from xg_sztz_xmjg t");
		sql.append("  left join xg_sztz_sskm t1" );
		sql.append("  on t1.sskmdm = t.sskmdm");
		sql.append("  left join xg_sztz_xmjb t2" );
		sql.append("  on t.xmjbdm = t2.xmjbdm");
		sql.append("  left join zxbz_xxbmdm t3 ");
		sql.append("  on t.sbbmdm = t3.bmdm ");
		sql.append("  left join xqdzb t4 ");
		sql.append("  on t4.xqdm = t.xq ");
		sql.append("  where  t.xmdm = ? ) t left join (select count(1) sqrs,xmdm  from xg_sztz_xsxmsq  where xmdm = ? and xn = ? and xq= ? group by xmdm ) t1 ");
		sql.append("  on t.xmdm = t1.xmdm)t ");
		sql.append("  left join (select count(1) tgrs,xmdm  from xg_sztz_xs_sqjg  where xmdm = ? and xn = ? and xq= ? group by xmdm )t1 on t.xmdm = t1.xmdm ");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,xn,xq,xmdm,xn,xq});
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除学生申请项目记录
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-20 下午02:06:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXssqjg(XsXmJgForm myForm) throws Exception{
		String sql="delete from XG_SZTZ_XS_SQJG where xmdm=? and (sjly <>'1'or sjly is null)";
		return dao.runUpdate(sql, new String[]{myForm.getXmdm()});
	}
	
	/**
	 * 
	 * @描述:更新学生获奖情况(流程数据)
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-21 上午09:03:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateJxxx(List<String[]> params) throws SQLException {
		String sql = "update XG_SZTZ_XS_SQJG set ylzd1=?,ylzd2=? where xmdm=? and xn=? and xq=? and xh=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	public boolean insertJxxx(List<String[]> params) throws SQLException {
		
		String sql = "insert into XG_SZTZ_XS_SQJG(ylzd1,ylzd2,xmdm,xn,xq,xh) values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * @描述: 获取项目级别、总学分、是否合格、统计判断
	 * @作者： 孟威[工号：1186]
	 * @日期：2015-11-24 下午05:27:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSztzhdxf(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmjbmc,zxf,(case when ((xmjbmc like '%A%' and zxf > 0) or(xmjbmc = '总计' and zxf >= 6) or ");
		sql.append(" (zxf >= 0.5 and xmjbmc not like '%A%' and xmjbmc <> '总计')) then '合格'else '不合格' end) as sfhg from (select xmjbmc, sum(zxf) zxf ");
		sql.append(" from (select t1.xh,t3.xmjbmc,(nvl(to_number(t7.fjxf), 0) + to_number(t2.jcxf)) zxf ");
		sql.append(" from XG_SZTZ_XS_SQJG t1 left join xg_sztz_xmjg t2 on t1.xmdm = t2.xmdm left join xg_sztz_xmjb t3 on t2.xmjbdm = t3.xmjbdm ");
		sql.append(" left join xg_sztz_sskm t4 on t2.sskmdm = t4.sskmdm left join xqdzb t5 on t1.xq = t5.xqdm left join view_xsbfxx t6 ");
		sql.append("  on t1.xh = t6.xh left join xg_sztz_xm_jx t7 on t1.ylzd1 = t7.jgid ");
		sql.append(" union all ");
		sql.append("  select t1.xh,t2.xmjbmc,to_number(t1.zxf) zxf ");
		sql.append(" from XG_SZTZ_XS_XWSQJG t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm = t3.sskmdm left join view_xsjbxx t4 on t1.xh = t4.xh left join xqdzb t5 ");
		sql.append(" on t1.xq = t5.xqdm ");
		sql.append(" union all ");
		sql.append(" select ? xh,xmjbmc,0 zxf from xg_sztz_xmjb ");
		sql.append(" ) t where xh = ? group by xmjbmc ");
		sql.append(" union all ");
		sql.append("  select '总计' as xmjbmc, sum(zxf) zxf from  ");
		sql.append("  (select t1.xh,t3.xmjbmc,(nvl(to_number(t7.fjxf), 0) + to_number(t2.jcxf)) zxf from XG_SZTZ_XS_SQJG t1 ");
		sql.append(" left join xg_sztz_xmjg t2 on t1.xmdm = t2.xmdm left join xg_sztz_xmjb t3 on t2.xmjbdm = t3.xmjbdm left join xg_sztz_sskm t4 on t2.sskmdm = t4.sskmdm left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" left join view_xsbfxx t6 on t1.xh = t6.xh left join xg_sztz_xm_jx t7 on t1.ylzd1 = t7.jgid union all ");
		sql.append(" select t1.xh,t2.xmjbmc,to_number(t1.zxf) zxf from XG_SZTZ_XS_XWSQJG t1 ");
		sql.append(" left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm = t3.sskmdm left join view_xsjbxx t4 on t1.xh = t4.xh left join xqdzb t5 on t1.xq = t5.xqdm ) t ");
		sql.append("  where xh = ?) order by xmjbmc ");
		String[] inputValue = new String[] { xh,xh,xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	public List<HashMap<String, String>> getTzcjList(ArrayList<HashMap<String, String>> aList,String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		for (int i = 0; i < aList.size(); i++) {
			sql.append(" case when t.xn||xq='"+aList.get(i).get("xnxq")+"' then t.zxf else 0 end xmfs"+i+",");
		}
		sql.append("  t.*,t2.sskmmc from(select sum(zxf) zxf,xh, xn,xq,sskmdm from(select t.*,case when zxfs>100 then 100 else zxfs end zxf from (select t1.xn,t1.xq,t3.sskmdm,");
		sql.append("t1.xh,(nvl(to_number(t8.fjxf),0)+to_number(t3.jcxf))zxfs from XG_SZTZ_XS_SQJG t1 left join xg_sztz_xmjg t3  on t1.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_xmjb t4");
		sql.append(" on t3.xmjbdm = t4.xmjbdm  left join view_xsjbxx t5 on t1.xh = t5.xh");
		sql.append("  left join zxbz_xxbmdm t6  on t3.sbbmdm = t6.bmdm  left join xqdzb t7");
		sql.append("  on t1.xq = t7.xqdm left join xg_sztz_xm_jx t8 on t1.ylzd1=t8.jgid)t where xh=?) group by xh,xn,xq,sskmdm)t");
		sql.append("  left join xg_sztz_sskm t2 on t.sskmdm = t2.sskmdm");
		return dao.getListNotOut(sql.toString(), new String[] { xh});
	}
	
	/**
	 * 
	 * @描述: 获取个人总学分查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-19 下午04:00:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getgrZxf(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(grzxf) grzxf from (");
		sql.append(" select t1.jgid,");
		sql.append(" xh,");
		sql.append(" (case nvl(t1.jxdm, '0')");
		sql.append(" when '0' then");
		sql.append(" (to_number(t1.tzhjcf))");
		sql.append(" else");
		sql.append(" (to_number(t1.tzhjcf) + to_number(nvl(t7.fjxf, '0')))");
		sql.append(" end) grzxf");
		sql.append(" from xg_sztz_jcftz_jg t1");
		sql.append(" left join xg_sztz_xm_jx t7");
		sql.append(" on t1.jxdm = t7.jgid");
		sql.append(" where t1.xh = ?) ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "grzxf");
	}
}
