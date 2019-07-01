/**
 * @部门:学工产品事业部
 * @日期：2015-8-4 上午11:39:31 
 */
package com.zfsoft.xgxt.xstgl.stgl.stjg;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 社团管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-4 上午11:39:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StjgDao extends SuperDAOImpl<StjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(StjgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(StjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.stid," +
				"t1.stxmmc," +
				"t1.stlbdm," +
				"t1.xmlbdm," +
				"t1.xn," +
				"t1.stxj," +
				"t1.gkdw," +
				"t1.kssj," +
				"t1.jssj," +
				"t1.fzrlb," +
				"t1.stfzr," +
				"t1.zdls," +
				"t1.sqsj," +
				"t1.lxdh," +
				"t1.sqkssj," +
				"t1.sqjssj," +
				"t1.sqkg," +
				"t1.splc," +
				"t1.jtr," +
				"t1.stsm," +
				"t1.id," +
				"t1.sjly," +
				"t1.zdlszc," +
				"t1.zdlslxfs," +
				"t1.ssbm," +
				"t1.stclsj," +
				"t1.sthjqk," +
				"nvl(t1.cysl,0) cysl," +
				" t2.xmlbmc,t3.stlbmc,nvl(t4.xm,t1.jtr) jtrxm,t5.xm fzrxm,x.zdlsxms zdlsxm ");
		sql.append("from xg_stgl_jtjg t1 left join xg_stgl_xmlb t2 on t1.xmlbdm = t2.xmlbdm left join xg_stgl_stlb t3 on t2.stlbdm=t3.stlbdm " +
				"left join (select a.stid,WM_CONCAT(c.xm)  as zdlsxms from xg_stgl_jtjg a left join xg_stgl_zdlscy b on a.stid = b.stid left join fdyxxb c on b.zgh = c.zgh  group by a.stid) x on t1.stid = x.stid ");
		sql.append(" left join  ");
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t4  ");
			sql.append(" on t1.jtr=t4.yhm");
		}else{
			sql.append("  xsxxb t4");
			sql.append(" on t1.jtr=t4.xh");
		}
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t5");
		sql.append(" on t1.stfzr = t5.yhm left join yhb t6 on t1.zdls = t6.yhm) t where 1=1 ");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and t.jtr = '"+user.getUserName()+"' ");
		}
		if(!user.getUserType().equalsIgnoreCase("stu")){
			//sql.append(searchTjByUser);
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * @throws Exception
	 * 
	 * @描述:获取社团结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 下午04:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getStjg(StjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.*,t2.stlbmc,t3.xmlbmc,t4.xm jtrxm,t5.xm stfzrxm,t6.xm zdlsxm,t7.bmmc ssbmmc,t8.zcmc ");

		if(t.getFzrlb().equals("学生")){
			sql.append(",t5.bjmc fzrbj,t5.xy fzrxy");
		}
		sql.append(" from xg_stgl_jtjg t1  left join xg_stgl_stlb t2 on t1.stlbdm=t2.stlbdm");
		sql.append(" left join xg_stgl_xmlb t3 on t1.xmlbdm=t3.xmlbdm ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t4 on t1.jtr=t4.yhm ");
		if(t.getFzrlb().equals("老师")){
			sql.append(" left join yhb t5 on t1.stfzr = t5.yhm");
		}else{
			sql.append(" left join xsxxb t5 on t1.stfzr = t5.xh ");
		}
		sql.append(" left join yhb t6 on t1.zdls = t6.yhm ");
		sql.append(" left join zxbz_xxbmdm t7 on  t1.ssbm = t7.bmdm");
		sql.append(" left join zcb t8 on t1.zdlszc = t8.zcdm");
		sql.append(" where t1.stid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getStid() });
	}


	/**
	 *判断社团结果是否已存在, 大于等于相同学年的社团项目不能重复
	 */
	public boolean isHaveSbjg(StjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_stgl_jtjg where  xn>=?  and stxmmc=?");
		if(null!=model.getStid()){
			sql.append(" and stid<>'"+model.getStid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getStxmmc()}, "num");
		return Integer.parseInt(num)>0;
	}


	/**
	 * 
	 * 删除社团结果
	 */
	public boolean delStjg(String id) throws Exception {
		String sql = "delete from xg_stgl_jtjg where stid=?";
		return dao.runUpdate(sql, new String[] { id });
	}
	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from( select stid from xg_sthd_dj where stid in("+values+")");
		sql.append(" union all select stid from xg_sthd_hdjg where stid in("+values+"))");
		int result = dao.getOneRsint(sql.toString());
		return result > 0;
	}
	public List<HashMap<String, String>> getStxxCylist(StjgForm stjg,User user){
		StringBuilder sql = new StringBuilder();
		String[]inputValue = new String[]{stjg.getStid()};
		sql.append("select t1.xh,");
		sql.append("t1.rtid,");
		sql.append("t1.sjly,");
		sql.append("t2.xm,");
		sql.append("t2.xb,");
		sql.append("t2.bjmc,t3.ldmc,t3.qsh,t3.qsdh,");
		sql.append("t2.xymc,t2.bjmc,t2.sjhm,t2.dzyx,");
		sql.append("t1.sqly,");
		sql.append("t1.tc,");
		sql.append("t1.rylbdm");
		sql.append(" from XG_RTGL_RTJG t1");
		sql.append(" left join view_xsbfxx t2 left join view_xg_gygl_new_cwxx t3 on t2.xh=t3.xh");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" where t1.stid = ? ");
		sql.append(" and (t1.tnzt = '正常' or t1.tnzt is null)");
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(StjgForm.class);
		super.setKey("stid");
		super.setTableName("xg_stgl_jtjg");

	}
	
	//社团综合维护维护团内状态时更新cysl
	public boolean update_stcysl(String cysl,String stid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_stgl_jtjg set cysl = ? where stid = ?");
		return dao.runUpdate(sql.toString(), new String[]{cysl,stid});
	}
	
	public List<HashMap<String, String>> getBbdmlist(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmdm dm,bmmc mc from zxbz_xxbmdm where bmlb = '5' order by bmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public boolean saveZdls(String stid, String stid1, String xhs) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_stgl_ZDLSCY where  sqid = '"+stid+"'");
		ArrayList<String> sqlArr = new ArrayList<String>();
		sqlArr.add(sql.toString());
		String[] zgh = xhs.split(",");
		for (int i = 0; i < zgh.length; i++) {
			StringBuilder sqlLs = new StringBuilder();
			sqlLs.append(" insert into xg_stgl_ZDLSCY (stid,sqid,zgh) values(");
			sqlLs.append("'"+stid+"',");
			sqlLs.append("'"+stid+"',");
			sqlLs.append("'"+zgh[i]+"'");
			sqlLs.append(" )");
			sqlArr.add(sqlLs.toString());
		}

		int[] result =	dao.runBatch(sqlArr.toArray(new String[]{}));
		return result != null&&result.length == (zgh.length+1) ? true:false;
	}

	public List<HashMap<String,String>> getZdlsInfo(StjgForm myForm) {
		String sql ="select a.*,y.xm,y.lxdh,y.bmdm,t1.bmmc,y.zc,t2.zcmc from xg_stgl_zdlscy a left join fdyxxb y on  a.zgh = y.zgh " +
				" left join zxbz_xxbmdm t1 on y.bmdm = t1.bmdm left join zcb t2 on y.zc = t2.zcdm where a.stid = ?";
		return dao.getListNotOut(sql, new String[]{myForm.getStid()});
	}

    public List<HashMap<String,String>> getJgAll(StjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.stid," +
				"t1.stxmmc," +
				"t1.stlbdm," +
				"t1.xmlbdm," +
				"t1.xn," +
				"t1.stxj," +
				"t1.gkdw," +
				"t1.kssj," +
				"t1.jssj," +
				"t1.fzrlb," +
				"t1.stfzr," +
				"t1.zdls," +
				"t1.sqsj," +
				"t1.lxdh," +
				"t1.sqkssj," +
				"t1.sqjssj," +
				"t1.sqkg," +
				"t1.splc," +
				"t1.jtr," +
				"t1.stsm," +
				"t1.id," +
				"t1.sjly," +
				"t1.zdlszc," +
				"t1.zdlslxfs," +
				"t1.ssbm," +
				"t1.stclsj," +
				"t1.sthjqk," +
				"nvl(t1.cysl,0) cysl," +
				" t2.xmlbmc,t3.stlbmc,nvl(t4.xm,t1.jtr) jtrxm,t5.xm fzrxm,x.zdlsxms zdlsxm ");
		sql.append("from xg_stgl_jtjg t1 left join xg_stgl_xmlb t2 on t1.xmlbdm = t2.xmlbdm left join xg_stgl_stlb t3 on t2.stlbdm=t3.stlbdm " +
				"left join (select a.stid,WM_CONCAT(c.xm)  as zdlsxms from xg_stgl_jtjg a left join xg_stgl_zdlscy b on a.stid = b.stid left join fdyxxb c on b.zgh = c.zgh  group by a.stid) x on t1.stid = x.stid ");
		sql.append(" left join  ");
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t4  ");
			sql.append(" on t1.jtr=t4.yhm");
		}else{
			sql.append("  xsxxb t4");
			sql.append(" on t1.jtr=t4.xh");
		}
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t5");
		sql.append(" on t1.stfzr = t5.yhm left join yhb t6 on t1.zdls = t6.yhm) t where 1=1 ");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and t.jtr = '"+user.getUserName()+"' ");
		}
		if(!user.getUserType().equalsIgnoreCase("stu")){
			//sql.append(searchTjByUser);
		}
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
    }
}
