/**
 * @部门:学工产品事业部
 * @日期：2015-1-20 上午11:39:31 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 早晚自习考勤管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-20 上午11:39:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZwzxKqjgDao extends SuperDAOImpl<ZwzxKqjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqjgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select t1.*,");
		sql.append(" (select xqmc from xqdzb b where t1.xq = b.xqdm) xqmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.lxmc cclxmc,");
		sql.append(" t4.xm jlrxm,");
		sql.append(" t5.bmmc jlrbmmc");
		if("2297".equals(Base.xxdm)){
			sql.append(" , nvl(t6.rs,0) ydrsszly");
		}
		/**
		 * 西安科技
		 */
		if("10704".equals(Base.xxdm)){
			sql.append(" ,c.dbfdy ");
		}
		sql.append(" from XG_RCSW_ZWZXKQ_JGB t1");
		sql.append(" left join VIEW_NJXYZYBJ_all t2");
		sql.append(" on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_RCSW_ZWZXKQ_CCLXB t3");
		sql.append(" on t1.cclxdm = t3.lxdm");
		sql.append(" left join fdyxxb t4");
		sql.append(" on t1.jlr = t4.zgh");
		sql.append(" left join zxbz_xxbmdm t5");
		sql.append(" on t5.bmdm = t4.bmdm");
		if("2297".equals(Base.xxdm)){
			sql.append(" left join (select count(1) rs, bjdm from view_xsxxb group by bjdm) t6");
			sql.append(" on t1.bjdm = t6.bjdm");
		}
		//西安科技
		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		}
		sql.append(" order by t1.ccrq desc) t");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:查询缺勤学生列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 下午04:07:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(ZwzxKqjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.qqlxmc,t5.xm,t5.xb");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm left join XG_RCSW_ZWZXKQ_QQLXB t4 on t1.qqlxdm = t4.qqlxdm left join view_xsjbxx t5 on ");
		sql.append(" t1.xh = t5.xh where (t1.sjzt='1' or t1.sjzt is null) ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:获取缺勤学生信息列表（不分页）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-28 下午01:53:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getAllQqxsList(ZwzxKqjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.qqlxmc,t5.xm,t5.xb");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm left join XG_RCSW_ZWZXKQ_QQLXB t4 on t1.qqlxdm = t4.qqlxdm left join view_xsjbxx t5 on ");
		sql.append(" t1.xh = t5.xh where (t1.sjzt='1' or t1.sjzt is null) ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:获取考勤结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 下午04:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public ZwzxKqjgForm getKqjg(ZwzxKqjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.xm jlrxm,c.dbfdy ");
		sql.append(" from XG_RCSW_ZWZXKQ_JGB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm");
		sql.append(" left join fdyxxb t4 on t1.jlr=t4.zgh ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" where t1.jgid=? ");
		return super.getModel(sql.toString(), new String[] { t.getJgid() });

	}

	/**
	 * 
	 * @描述:获取缺勤学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 下午04:50:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,t1.*,(case when length(t1.ylzd1)>10 then substr(t1.ylzd1,0, 10)||'...' else t1.ylzd1 end)  ylzd1str, ");
		sql.append(" t2.xm,t3.qqlxmc from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join view_xsjbxx t2 on t1.xh=t2.xh");
		sql.append(" left join XG_RCSW_ZWZXKQ_QQLXB t3 on t1.qqlxdm = t3.qqlxdm");
		sql.append(" where t1.id=? ");
		return dao.getListNotOut(sql.toString(), new String[] { id });

	}

	/**
	 *查询缺勤类型
	 */
	public List<HashMap<String, String>> getQqlxList() {
		String sql = "select * from  XG_RCSW_ZWZXKQ_QQLXB";
		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @描述:学生缺勤信息详情查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 下午05:09:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getQqxsxx(ZwzxKqjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.qqlxmc,t5.xm,t5.xb");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm left join XG_RCSW_ZWZXKQ_QQLXB t4 on t1.qqlxdm = t4.qqlxdm left join view_xsjbxx t5 on ");
		sql.append(" t1.xh = t5.xh where t1.id=? and t1.xh=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { model.getSqid(), model.getXh() });
	}

	/**
	 *判断班级考勤结果是否已存在
	 */
	public boolean isHaveKgjg(ZwzxKqjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_RCSW_ZWZXKQ_JGB where xn=? and xq=? and bjdm=? and ccrq=? and cclxdm=?");
		if (null != model.getJgid()) {
			sql.append(" and jgid<>'" + model.getJgid() + "' ");
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(), model.getXq(), model.getBjdm(), model.getCcrq(), model.getCclxdm() }, "num");
		return Integer.parseInt(num) > 0;
	}

	/**
	 * 
	 * @描述:获取班级信息列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 上午09:32:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListNew(ZwzxKqjgForm t, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();

		sql.append("select t.* from (");
		sql.append("select distinct a.nj,a.bjdm,a.bjmc,a.zydm,a.zymc,a.xydm,a.xymc,nvl(b.bjrs,0) bjrs,c.dbfdy from view_njxyzybj_all a");
		sql.append(" left join (select count(xh) bjrs,bjdm from view_xsjbxx group by bjdm)b on a.bjdm=b.bjdm ");
		sql.append("LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

	}

	/**
	 * 
	 * @描述:获取学生信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 上午11:03:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(ZwzxKqjgForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhArr();
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where a.bjdm='" + model.getBjdm() + "'");
		if(xhs.length>0){
		sql.append(" and a.xh not in(");
		for (int i = 0; i < xhs.length; i++) {
			if(i!=0){
				sql.append(", ");
			}
			sql.append("'"+xhs[i]+"' ");
		}
		sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:缺勤学生批量保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 下午03:33:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException boolean 返回类型
	 * @throws
	 */
	public boolean qqxsPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into XG_RCSW_ZWZXKQ_XSKQXXB(id,xh,xn,xq,lrsj,cclxdm,qqlxdm,kkjs,bjdm,ccrq,jlf,jlr,ylzd1,ylzd2,ylzd3,sjzt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:删除缺勤学生信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 上午09:26:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * @throws SQLException boolean 返回类型
	 * @throws
	 */
	public boolean delQqxs(String jgid) throws Exception {
		String sql = "delete from XG_RCSW_ZWZXKQ_XSKQXXB where id = ?";
		return dao.runUpdate(sql, new String[] { jgid });
	}

	/**
	 * 
	 * 删除考勤结果
	 */
	public boolean delKqjgBySqid(String sqid) throws Exception {
		String sql = "delete from XG_RCSW_ZWZXKQ_JGB where jgid=?";
		return dao.runUpdate(sql, new String[] { sqid });
	}
	/**
	 * 
	 * 获取学期名称
	 */
	public String getXqmc(String xq) throws Exception {
		String sql = "select xqmc from xqdzb  where xqdm=?";
		return dao.getOneRs(sql, new String[]{xq}, "xqmc");

	}

	/**
	 * 
	 * @描述:根据jgid删除批量缺勤学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 上午11:39:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean delPlQqxs(List<String[]> params) throws Exception {
		String sql = "delete from XG_RCSW_ZWZXKQ_XSKQXXB where id = ?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:更新学生缺勤信息数据状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 上午11:03:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean updateQqxs(String id, String sjzt) throws Exception {
		String sql = "update XG_RCSW_ZWZXKQ_XSKQXXB set sjzt=? where id = ?";
		return dao.runUpdate(sql, new String[] { sjzt, id });
	}

	@Override
	protected void setTableInfo() {
		super.setClass(ZwzxKqjgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_RCSW_ZWZXKQ_JGB");

	}

}
