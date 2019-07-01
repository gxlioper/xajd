/**
 * @部门:学工产品事业部
 * @日期：2015-1-6 下午02:43:14 
 */
package xsgzgl.qgzx.mrgzkh.khsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 每日工作考核管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-6 下午02:43:14
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class GzkhKhsqDao extends SuperDAOImpl<GzkhKhsqForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhsqForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc,t2.tc,t2.yhkh,t2.yhmc,t3.gwmc,nvl(t4.yrdwmc,t5.bmmc) yrdwmc,nvl(t4.xydm,t4.id) yrdwdm ");
		sql.append(",t6.gwxzmc,(t1.gzrq||' '||t1.gzkssj||'时'||'-'||t1.gzjssj||'时')gzsj,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t1.shzt) shztmc ");
		sql.append("from XG_QGZX_MRKH_SQB t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm");
		sql.append(" left join xg_qgzx_yrdwdmb t4 on t3.yrdwid=t4.id");
		sql.append(" left join ZXBZ_XXBMDM t5 on t4.xydm = t5.bmdm");
		sql.append(" left join XG_QGZX_GWXZDMB t6 on t3.gwlb = t6.gwxzdm");
		sql.append(" ) t where 1=1 ");
//		sql.append(searchTjByUser);
		if("stu".equals(user.getUserType())){
			sql.append(" and t.xh = '"+user.getUserName()+"' ");
		} else {//老师
			sql.append(" and t.yrdwdm = '"+user.getUserDep()+"' ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	public String getGwid(String splc){
		String sql = "select spgw gwid from XG_XTWH_SPBZ where splc=? and xh = '1'";
		return dao.getOneRs(sql,new String[]{splc},"gwid");
	}
	/**
	 * 
	 * @描述:获取学生勤工岗位所属部门
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午09:23:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getYrbm(GzkhKhsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct t4.bmdm, t4.bmmc ");
		sql.append("   from XG_QGZX_NEW_XSGWXXB t1 left join XG_QGZX_GWXXB t2 on t1.gwdm=t2.gwdm ");
		sql.append("   left join XG_QGZX_YRDWDMB t3 ");
		sql.append("     on t2.yrdwid = t3.id and t3.dwlb = '01'");//校内单位
		sql.append("   left join ZXBZ_XXBMDM t4 ");
		sql.append("     on t3.xydm = t4.bmdm ");
		sql.append(" where t1.xh=? and t1.zgzt='zg'");
		sql.append(" order by t4.bmdm ");
		return dao.getList(sql.toString(), new String[] { model.getXh() }, new String[] { "bmdm", "bmmc" });

	}

	/**
	 * 
	 * @描述:获取学生勤工岗位
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午09:33:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxx(GzkhKhsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("   select (t2.gwdm||','||t2.xn)gwdm,(t2.gwmc||'('||t2.xn||')')gwmc from XG_QGZX_NEW_XSGWXXB t1 left join XG_QGZX_GWXXB t2 on t1.gwdm=t2.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t3 on t2.yrdwid = t3.id");
		sql.append(" where t1.xh=? and t3.xydm=? and t1.zgzt='zg' order by t2.xn");
		return dao.getList(sql.toString(), new String[] { model.getXh(), model.getYrdw() }, new String[] { "gwdm", "gwmc" });
	}
	/**
	 * 
	 * @描述:获取在岗学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午10:13:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGzkhStuList(GzkhKhsqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from (select distinct t1.xh,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.zybj,t2.zybjmc,t2.nj");
		sql.append(" from xg_qgzx_new_xsgwxxb t1 left join view_xsjbxx t2 on t1.xh=t2.xh where t1.zgzt='zg'");
		sql.append(")a where  1=1");
//		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取审批流
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午11:03:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_QGZX_MRKH_JCSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	public GzkhKhsqForm getModel(GzkhKhsqForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.sqid,t1.xh,t1.xn,t1.sqsj,t1.yrdw,t2.bmmc,(t3.gwdm||','||t3.xn)gwdm,(t3.gwmc||'('||t3.xn||')')gwmc,t1.gs,t1.gzrq,t1.gzkssj,t1.gzjssj,t1.gzdd,");
		sql.append(" t1.gznr,t1.shzt,t1.splc,t1.bz from XG_QGZX_MRKH_SQB t1 left join view_xg_qgzx_yrdwdmb t2 on t1.yrdw=t2.bmdm left join");
		sql.append(" xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm where t1.sqid=?");
		return super.getModel(sql.toString(),new String[]{model.getSqid()});
	}
	/**
	 * 
	 * @描述:判断当前岗位是否有填写记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-8 上午11:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveSqJl(GzkhKhsqForm model,String czlx) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_QGZX_MRKH_SQB where xh=? and xn=? and gwdm=? and gzrq=?");
		if("update".equals(czlx)){
			sql.append(" and sqid<>'"+model.getSqid()+"' ");
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getGwdm(),model.getGzrq()}, "num");
		return Integer.parseInt(num)>0;
	}

	public HashMap<String,String> getCjffXxOfStu(String xh,String xn,String gzrq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(gs),0)gs,nvl(sum(je),0)je from xg_qgzx_cjff where xh=? and ffsj =? and zgzt='zg'");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,gzrq.substring(0, 7)});
	}
	
	public String getCjByGwdm(String xh,String xn,String gwdm,String gzrq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(je),0)ffje from xg_qgzx_cjff where xh=?  and gwdm||','||xn=? and ffsj =? and zgzt='zg'");
		return dao.getOneRs(sql.toString(),new String[]{xh,gwdm,gzrq.substring(0, 7)},"ffje");
	}
	/**
	 * 
	 * @描述:获取学生某一岗位某月总金额
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-16 上午09:20:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public String getKhsqOfYf(String xh,String xn,String gwdm,String gzrq,String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(gs),0)zgs,nvl(sum(zje),0)zje from (select nvl(yxgs,gs)gs,(to_number(nvl(yxgs,gs))*to_number(cjbz))zje from XG_QGZX_MRKH_SQB where shzt in('0','5') and xh=?  and gwdm||','||xn=? and gzrq like '%'||?||'%'");
		if(null!=sqid){
			sql.append(" and sqid<>'"+sqid+"'");
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(),new String[]{xh,gwdm,gzrq.substring(0, 7)},"zje");
	}
	/**
	 * 
	 * @描述:查询学生某月申请记录总工时总金额
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-20 下午03:49:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param gwdm
	 * @param gzrq
	 * @param sqid
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getsqjl(String xh,String xn,String gzrq,String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(gs),0)zgs,nvl(sum(zje),0)zje from (select nvl(yxgs,gs)gs,(to_number(nvl(yxgs,gs))*to_number(cjbz))zje from XG_QGZX_MRKH_SQB where shzt in('0','5') and xh=?  and gzrq like '%'||?||'%'");
		if(null!=sqid){
			sql.append(" and sqid<>'"+sqid+"'");
		}
		sql.append(")");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,gzrq.substring(0, 7)});
	}
	
	/**
	 * 获取岗位名称
	 * 
	 */
	public HashMap<String,String> getGwxx(String gwdm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select gwmc,gwcjsx from xg_qgzx_gwxxb where gwdm||','||xn = ?"); 
		return dao.getMapNotOut(sql.toString(), new String[]{gwdm});
	}

	@Override
	protected void setTableInfo() {
		super.setClass(GzkhKhsqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_QGZX_MRKH_SQB");

	}

}
