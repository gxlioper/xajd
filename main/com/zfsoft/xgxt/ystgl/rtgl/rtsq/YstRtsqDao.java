/**
 * @部门:学工产品事业部
 * @日期：2014-12-5 下午02:21:41 
 */
package com.zfsoft.xgxt.ystgl.rtgl.rtsq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间：2015-8-6 下午02:21:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YstRtsqDao extends SuperDAOImpl<YstRtsqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(YstRtsqForm model) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YstRtsqForm model, User user) throws Exception {

		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,");
		sql.append("t2.xm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.xydm,t2.xymc,t2.nj,t2.xb,t3.xn yxxn,t3.splc,t3.xmlbdm,t3.ystlbdm,t3.ystxmmc,t4.xmlbmc,t5.ystlbmc,t6.xm zdlsxm,");
		sql.append(" case when t3.sqkg = 1 and sysdate between to_date(nvl(t3.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
		sql.append(" and to_date(nvl(t3.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg, ");
		sql.append(" decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc");
		sql.append(" from xg_ystgl_rtsqb t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join XG_YSTGL_YSTJGB t3 on t1.ystid=t3.ystid");
		sql.append(" left join XG_YSTGL_XMLB t4 on t3.xmlbdm = t4.xmlbdm left join XG_YSTGL_YSTLB t5 on t3.ystlbdm=t5.ystlbdm");
		sql.append(" left join yhb t6 on t3.zdls = t6.yhm");
		sql.append(" )t where 1= 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	
	}


	@Override
	protected void setTableInfo() {
		super.setClass(YstRtsqForm.class);
		super.setKey("rtid");
		super.setTableName("xg_ystgl_rtsqb");
	}
	
	public HashMap<String, String> getYstxxMap(YstRtsqForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.rtid,t.xh,t.rtxn,t.rtxq,t.sqly,t.tc,t1.*,t2.ystlbmc,t3.xmlbmc,t4.xm jtrxm,t5.xm stfzrxm,t6.xm zdlsxm,t7.bmmc ssbmmc,t8.zcmc ");
		sql.append(",t9.bmdm ssbm,t9.lxdh zdlslxfs,t10.gkdwmc ");
		sql.append(" from xg_ystgl_rtsqb t left join");
		sql.append(" XG_YSTGL_YSTJGB t1  on t.ystid=t1.ystid left join XG_YSTGL_YSTLB t2 on t1.ystlbdm=t2.ystlbdm");
		sql.append(" left join XG_YSTGL_XMLB t3 on t1.xmlbdm=t3.xmlbdm ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t4 on t1.jtr=t4.yhm ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t5 on t1.fzr=t5.yhm");
		sql.append(" left join yhb t6 on t1.zdls = t6.yhm ");
		sql.append(" left join zcb t8 on t1.zdlszc = t8.zcdm");
		sql.append(" left join fdyxxb t9 on t1.zdls=t9.zgh");
		sql.append(" left join zxbz_xxbmdm t7 on  t9.bmdm = t7.bmdm");
		sql.append(" left join XG_YSTGL_GKDW t10 on t1.gkdwdm=t10.gkdwdm");
		sql.append(" where t.rtid=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getRtid()});
	}
	
	//获取社团成员信息
	public HashMap<String, String> getStxxCyMap(YstRtsqForm YstRtsq,User user){
		StringBuilder sql = new StringBuilder();
		String[]inputValue = new String[]{};
		sql.append("select t1.*,t5.ystxmmc, t3.xmlbdm, t2.xm,t2.zydm, t2.zymc, t2.bjdm, t2.bjmc,");
		sql.append("t2.xydm, t2.xymc, t2.nj, t2.xb, t3.xmlbmc, t4.ystlbdm, t4.ystlbmc,");
		sql.append("t5.zdls, t5.xn, t5.jtr, t5.fzr, t5.kssj, t5.jssj,t6.xm zdlsxm ");
		sql.append(" from xg_ystgl_rtsqb t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join XG_YSTGL_YSTJGB t5");
		sql.append(" on t1.ystid = t5.ystid");
		sql.append(" left join XG_YSTGL_XMLB t3");
		sql.append(" on t5.xmlbdm = t3.xmlbdm");
		sql.append(" left join yhb t6 on t5.zdls = t6.yhm");
		sql.append(" left join XG_YSTGL_YSTLB t4");
		sql.append(" on t3.ystlbdm = t4.ystlbdm");
		sql.append(" where  t1.rtid = ? ");
		inputValue = new String[]{YstRtsq.getRtid()};
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	//项目选择(版本升级后kssj和jssj判断用xn【有效学年代替】)
	public List<HashMap<String, String>> getStxmList(YstRtsqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String xh = model.getXh();
		StringBuilder sql = new StringBuilder("select * from(");
		sql.append(" select t.*, t2.xmlbmc,t3.ystlbmc,t5.xm stfzrxm,t6.xm zdlsxm,t6.lxdh zdlslxfs,t7.zcmc,t8.gkdwmc,t11.bmmc ssbmmc,");
		sql.append(" case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkgzt ");
		sql.append("  from XG_YSTGL_YSTJGB t left join XG_YSTGL_XMLB t2  on t.xmlbdm = t2.xmlbdm");
		sql.append(" left join XG_YSTGL_YSTLB t3 on t.ystlbdm = t3.ystlbdm");
		sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) t5 on t.fzr = t5.yhm");
		sql.append(" left join fdyxxb t6 on t.zdls = t6.zgh ");
		sql.append(" left join zcb t7 on t.zdlszc = t7.zcdm left join XG_YSTGL_GKDW t8 on t.gkdwdm=t8.gkdwdm");
		sql.append(" left join zxbz_xxbmdm t11 on t6.bmdm=t11.bmdm");
		sql.append(" where t.ystid not in (select ystid from (select ystid,xh from xg_ystgl_rtjgb ) t1 where t1.xh = ");
		sql.append("'"+xh+"'");
		sql.append(")");
		sql.append(")a where a.sqkgzt = '1'  and 1=1 ");
		sql.append(" and a.xn>='"+Base.currXn+"'");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//获取sqid
	public String getSqid(YstRtsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select rtid from xg_ystgl_rtsqb where xh = ?  and stid = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getYstid()}, "rtid");
	}
	
	//重复判断
	public boolean checkExistForSave(YstRtsqForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select max(rtid) rtid from");
		sql.append("(select count(rtid) rtid from xg_ystgl_rtsqb t where t.xh = ? and t.ystid = ? and t.shzt <> '2'");
		sql.append(" union select count(rtid) rtid from xg_ystgl_rtjgb t where t.xh = ? and t.ystid = ? )");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getYstid(),
			model.getXh(),model.getYstid()}, "rtid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	public String getsplc(YstRtsqForm model){
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.splc from XG_YSTGL_YSTJGB t where ystid = ? ");
		return dao.getOneRs(sql.toString(), new String[]{model.getYstid()}, "splc");
	}
	
	//版本升级后另起学生页面--查询
	public List<HashMap<String, String>> getStuYstRtsqList(YstRtsqForm model, User user)  throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,");
		sql.append("t1.rtid,");
		sql.append("t1.stid,");
		sql.append("t1.sqly,");
		sql.append("t1.tc,");
		sql.append("t1.rtxn,");
		sql.append("t1.rtxq,");
		sql.append("t1.shzt,");
		sql.append("t5.stxmmc,");
		sql.append("t3.xmlbdm,");
		sql.append("t1.sqsj,");
		sql.append("t1.splc,");
		sql.append("t2.zydm,");
		sql.append("t2.zymc,");
		sql.append("t2.bjdm,");
		sql.append("t2.bjmc,");
		sql.append("t2.xydm,");
		sql.append("t2.xymc,");
		sql.append("t2.nj,");
		sql.append("t2.xb,");
		sql.append("t3.xmlbmc,");
		sql.append("t4.stlbdm,");
		sql.append("t4.stlbmc,");
		sql.append("t5.zdls,");
		sql.append("t5.xn, ");
		sql.append("t5.kssj, ");
		sql.append("t5.jssj, ");
		sql.append("t5.stfzr, ");
		sql.append("t5.jtr, ");
		sql.append(" case when t5.sqkg = 1 and sysdate between to_date(nvl(t5.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
		sql.append(" and to_date(nvl(t5.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg, ");
		sql.append(" decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc,");
		sql.append(" t6.xm zdlsxm");
		sql.append(" from xg_ystgl_rtsqb t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join XG_YSTGL_YSTJGB t5");
		sql.append(" on t1.stid = t5.stid");
		sql.append(" left join XG_YSTGL_XMLB t3");
		sql.append(" on t5.xmlbdm = t3.xmlbdm");
		sql.append(" left join XG_YSTGL_YSTLB t4");
		sql.append(" on t3.ystlbdm = t4.ystlbdm");
		sql.append(" left join yhb t6");
		sql.append(" on t5.zdls = t6.yhm");
		sql.append(" )t where 1= 1");
		if(model.getFlag().equalsIgnoreCase("wsq")){
			sql.append(" and t.shzt <> 1");
		}else{
			sql.append(" and t.shzt = 1");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
}
