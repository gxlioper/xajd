/**
 * @部门:学工产品事业部
 * @日期：2014-5-5 上午10:34:53 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-5 上午10:34:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjJgDao extends SuperDAOImplExtend<JtpjJgForm> {
	@Override
	protected void setTableInfo() {
		setKey("jgid");
		setTableName("xg_pjpy_jtpj_jtpjjgb");
		setClass(JtpjJgForm.class);
	}

	/**
	 * 
	 * @描述: 获取结果对象
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-5 上午10:50:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getJgModel(String jgid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from xg_pjpy_jtpj_jtpjjgb where jgid=?");
		return dao.getMapNotOut(sb.toString(), new String[] { jgid });
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JtpjJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JtpjJgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select t1.*,t2.sqsj from (");
		sql.append(" select * from (");
		sql.append(" SELECT D.JGID,");
		sql.append(" d.xydm,d.zydm,d.bjdm,");
		sql.append(" D.JXID,");
		sql.append(" D.SQXN,");
		sql.append(" D.SQXQ,");
		sql.append(" D.PDXN,");
		sql.append(" D.PDXQ,");
		sql.append(" D.JTPJDW,");
		sql.append(" D.PJJTID,");
		sql.append(" D.PJJTMC,");
		sql.append(" D.JTJJ,");
		sql.append(" nvl(D.RS,D.ZRS) RS,");
		sql.append(" D.SQRYLX,");
		sql.append(" D.SQR,");
		sql.append(" D.SQRXM,");
		sql.append(" D.SJLY,");
		sql.append(" D.SQID,");
		sql.append(" D.SQLY,");
		if("10704".equals(Base.xxdm)){//西安科技大学个性化
			sql.append(" D.RDFS,");
		}
		sql.append(" N.JXMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = N.PDXQ) PDXQMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = D.SQXQ) SQXQMC");
		sql.append(" from (");
		sql.append(" (select a.*, b.xydm, b.zydm, b.bjdm, m.zrs");
		sql.append("  from xg_pjpy_jtpj_jtpjjgb a");
		sql.append("  left join view_njxyzybj_all b");
		sql.append("    on a.pjjtid = b.bjdm");
		sql.append("  left join (select count(xh) zrs,bjdm from view_xsbfxx a group by bjdm) m");
		sql.append("    on a.pjjtid = m.bjdm");
		sql.append("   where  a.jtpjdw = 'bj' ");

		// 用户身份
		String userStatus = user.getUserStatus();

		// 用户名
		String userName = user.getUserName();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 访问用户为学院
		if ("xy".equalsIgnoreCase(userStatus)) {
				sql.append(" and b.xydm = '" + userDep + "' ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" and exists (select 1 from bzrbbb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" and exists (select 1 from fdybjb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// 访问用户为辅导员兼班主任
			sql.append(" and (exists (select 1 from bzrbbb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");
			
			sql.append(" or exists (select 1 from fdybjb z where ");
			sql.append(" b.bjdm =  z.bjdm ");
			sql.append(" and z.zgh = '" + userName + "')) ");

		}
		
		sql.append(" ) ");		
		
		sql.append(" union all");
		sql.append(" (select a.*,c.xydm, '' zydm, '' bjdm, m.zrs");
		sql.append("   from xg_pjpy_jtpj_jtpjjgb a");
		sql.append("   left join (select distinct xydm from view_njxyzybj_all) c");
		sql.append("     on a.pjjtid = c.xydm");
		sql.append("  left join (select count(xh) zrs,xydm from view_xsbfxx a group by xydm) m");
		sql.append("    on a.pjjtid = m.xydm");
		sql.append("    where a.jtpjdw = 'xy' ");

		// 访问用户为学院/班主任/辅导员/贱人
		if ("xy".equalsIgnoreCase(userStatus) || "bzr".equalsIgnoreCase(userStatus) 
				|| "fdy".equalsIgnoreCase(userStatus) || "jd".equalsIgnoreCase(userStatus)) {
			sql.append(" and c.xydm = '" + userDep + "' ");
		} 
		
		sql.append(" ) ");		

		sql.append(") D");
		sql.append(" LEFT JOIN XG_PJPY_JTPJ_JTJXSZ N");
		sql.append(" ON D.JXID = N.JXID");
		sql.append(")a where 1 = 1");
		sql.append(" union all ");
		sql.append(getQtSql(t, user));
		sql.append(") t1 left join xg_pjpy_jtpj_jtjxsqb t2 on t1.sqid=t2.sqid) t1 where 1=1");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述: 获取其他sql查询
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-23 上午09:21:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	private String getQtSql(JtpjJgForm t, User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append(" SELECT D.JGID,");
		sql.append(" '' xydm,'' zydm,'' bjdm,");
		sql.append(" D.JXID,");
		sql.append(" D.SQXN,");
		sql.append(" D.SQXQ,");
		sql.append(" D.PDXN,");
		sql.append(" D.PDXQ,");
		sql.append(" D.JTPJDW,");
		sql.append(" D.PJJTID,");
		sql.append(" D.PJJTMC,");
		sql.append(" D.JTJJ,");
		sql.append(" D.RS,");
		sql.append(" D.SQRYLX,");
		sql.append(" D.SQR,");
		sql.append(" D.SQRXM,");
		sql.append(" D.SJLY,");
		sql.append(" D.SQID,");
		sql.append(" D.SQLY,");
		if("10704".equals(Base.xxdm)){//西安科技大学个性化
			sql.append(" D.RDFS,");
		}
		sql.append(" N.JXMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = N.PDXQ) PDXQMC,");
		sql.append(" (SELECT XQMC FROM XQDZB WHERE XQDM = D.SQXQ) SQXQMC");
		sql.append(" from (");
		sql.append(" (select a.*, '' xydm, '' zydm, '' bjdm");
		sql.append("   from xg_pjpy_jtpj_jtpjjgb a");
		sql.append("    where a.jtpjdw = 'qt' or a.jtpjdw = 'qs')");
		sql.append(") D");
		sql.append(" LEFT JOIN XG_PJPY_JTPJ_JTJXSZ N");
		sql.append(" ON D.JXID = N.JXID");
		sql.append(")a");
		return sql.toString();
	}

	/**
	 * 
	 * @描述: 根据申请id删除结果数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-5 下午03:15:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean deleteJgForSqid(String sqid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_pjpy_jtpj_jtpjjgb where sqid=?");
		return dao.runDelete(sb.toString(), new String[] { sqid }) >= 0;
	}
	
	/**
	 * @描述: 打印Word登记表（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.jgid,a.sqxn, ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc, ");
		sql.append(" decode(a.jtpjdw,'bj',c.xm,'') bzrxm, ");
		sql.append(" a.jtpjdw,a.pjjtmc,a.sqly,d.jxmc ,a.rs, b.zymc from xg_pjpy_jtpj_jtpjjgb a ");		
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" left join ( ");
		sql.append(" select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm ");
		sql.append(" ) c on a.pjjtid=c.bjdm ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz d on a.jxid=d.jxid ");
		sql.append(" where a.jgid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { jgid });
	}
	
	public HashMap<String, String> getDjb(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select  a.*, b.jxmc, b.dybbid, b.pdxn, c.bbmc, c.mbmc from XG_PJPY_JTPJ_JTPJJGB a ");
		sql.append(" left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid = b.jxid ");
		sql.append("left join XG_PJPY_JTPJ_JTJXBBDMB c  on b.dybbid=c.bbdm ");
		sql.append(" where a.jgid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { jgid });
	}

	public HashMap<String, String> gethbgydxDjb(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append("	select  a.*, b.jxmc, b.dybbid, b.pdxn, c.bbmc,(select count(*) from view_xsxxb y where y.BJDM = a.pjjtid)as bjrs , ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc ");
		sql.append(" ,( select x.xm from VIEW_NEW_DC_SZDW_XSDWWH x where x.BJDM= a.pjjtid and x.ZWMC='班长')as bzmc, c.mbmc,decode(a.jtpjdw,'bj',c.xm,'') bzrxm, t3.xm as fdyxm from XG_PJPY_JTPJ_JTPJJGB a  ");
		sql.append("	left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid = b.jxid  ");
		sql.append("	left join XG_PJPY_JTPJ_JTJXBBDMB c  on b.dybbid=c.bbdm  ");
		sql.append("	left join (select t1.*,t2.xm from  fdybjb t1  left join fdyxxb t2 on t1.zgh = t2.zgh) t3 on a.pjjtid = t3.bjdm  ");
		sql.append("	left join (  ");
		sql.append("			select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm   ");
		sql.append("	 ) c on a.pjjtid=c.bjdm  ");
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" where a.jgid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { jgid });
	}


	//山东畜牧兽医先进班集体推荐表获得班级信息list
	public List<HashMap<String, String>> getBzrxxlist(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t.nj,t.xydm,t.xymc,t.zydm,t.zymc,t.bjdm,t.bjmc," +
				"(select rs from xg_pjpy_jtpj_jtpjjgb where pjjtid = t.bjdm) rs," +
				"(select xm from BZRTXLB a where a.bjdm = t.bjdm) xm  from view_njxyzybj_all t " +
				"where t.bjdm in(");
		sql.append("select pjjtid from xg_pjpy_jtpj_jtpjjgb where jgid in ( ");
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		sql.append(")");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//山东畜牧兽医先进班集体申请表获得入学日期
	public HashMap<String, String> getRxrq(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum, rxrq from xsxxb where bjdm = (select pjjtid from xg_pjpy_jtpj_jtpjjgb where jgid = ?) and rownum = 1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{jgid});
	}
	
	/**
	 * @描述：获取班级信息
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月7日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getBjxx(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("select m.*,n.bzrxms from VIEW_NJXYZYBJ m left join (select a.bjdm, WM_CONCAT(b.xm) bzrxms from bzrbbb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm )n on m.bjdm=n.bjdm where m.bjdm= ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @描述：获取学院信息
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月7日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getXyxx(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xymc,xydm from VIEW_NJXYZYBJ where xydm= ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	public boolean isBjjx(String jxid){
		String sql = "select jtpjdw from xg_pjpy_jtpj_jtjxsz where jxid = ?";
		return dao.getOneRs(sql, new String[]{jxid}, "jtpjdw").equalsIgnoreCase("bj")?true:false;
	}
	/**
	 * @描述：获取辅导员信息
	 * @作者：姜舟[工号:1529]
	 * @日期：2017年11月20日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getfdyxx(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("  select b.xm as fdyxm, b.lxdh as lxdh from fdyxxb b left join (select a.zgh, c.bjdm from bzrbbb a " +
				" left join VIEW_NJXYZYBJ c  on a.bjdm = c.bjdm) n on b.zgh = n.zgh where n.bjdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
}
