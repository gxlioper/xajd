/**
 * @部门:学工产品事业部
 * @日期：2014-7-8 下午04:32:29 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-8 下午04:32:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxXgSqDao extends SuperDAOImpl<BysXxForm> {
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BysXxForm t)
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
	public List<HashMap<String, String>> getPageList(BysXxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:查询申请记录
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午06:43:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	// public List<HashMap<String, String>> getXgSqPageList(BysXxForm model,
	// User user) throws Exception {
	//
	// StringBuilder sb = new StringBuilder();
	// sb.append("select * from (");
	// sb.append("select  c.*,a.sqid,a.xgsj,a.shjg ");
	// sb
	// .append(", b.guid spclid,b.lcid,b.ywid,row_number() over(partition by b.ywid order by b.shsj desc) as rn ");
	// sb.append(" from xg_bysxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx  c ");
	// sb.append(" where a.xh=c.xh  and a.sqid=b.ywid");
	// sb.append(") a where  rn=1 ");
	// return getPageList(model, sb.toString(), new String[] {});
	// }
	/**
	 * 
	 * @描述:查询申请记录
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午04:35:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageList(BysXxForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select  b.*,a.sqid,a.xgsj,a.shjg,decode(a.shjg,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','未申请') shjgmc,a.splc ");
		sql.append(" from xg_bysxx_xxxgsqb a,view_xsjbxx  b ");
		sql.append(" where a.xh=b.xh");
		sql.append(") c where  1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:删除原有未进审批流的申请记录
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午04:29:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean deleteXgsq(BysXxXgSqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_bysxx_xxxgsqb ");
		sql.append(" where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("' ");
		String[] inputValue = { model.getXh() };
		return dao.runUpdate(sql.toString(), inputValue);

	}
	public boolean deleteShlc(BysXxXgSqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xtwh_shztb ");
		sql.append(" where ywid in(select sqid from xg_bysxx_xxxgsqb where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("') ");
		String[] inputValue = { model.getXh() };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}

	public boolean sqxxDel(String sqid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_bysxx_xxxgsqb ");
		sql.append(" where sqid=? ");
		String[] inputValue = { sqid };
		return dao.runUpdate(sql.toString(), inputValue);

	}

	/**
	 * 
	 * @描述:查询申请记录(学生)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午06:52:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageListByStu(BysXxForm model,
			User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select  b.*,a.sqid,a.xgsj,a.shjg,decode(a.shjg,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','未申请') shjgmc,a.splc ");
		sql.append(" from xg_bysxx_xxxgsqb a,view_xsjbxx  b ");
		sql.append(" where a.xh=b.xh and a.xh=?");
		sql.append(") c where  1=1 ");
		return getPageList(model, sql.toString(), new String[] { model.getXh() });
	}

	/**
	 * 
	 * @描述:查询毕业生
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 上午10:56:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBysXxList(BysXxForm model, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from ( ");
		sql.append("select b.*,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql
				.append(
						"a.bjmc,a.xydm,a.zydm,a.bjdm from XG_BYSXX_BYSXXB b join view_xsjbxx a ")
				.append(
						"on a.xh=b.xh where b.xh not in(select xh from xg_bysxx_xxxgsqb ) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and bynd = (select bynd from XG_BYSXX_XXXGCSSZB) ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:TODO保存修改字段及值
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午02:32:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean insertXgzd(List<BysXxXgZdForm> list, String sqid)
			throws Exception {
		int[] result = null;
		List<String[]> paramList = new ArrayList<String[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xgzdb(sqid,zd,zdz,xgqz) ");
		sb.append(" values(?,?,?,?)");
		for (BysXxXgZdForm xgzdForm : list) {
			String[] param = { sqid, xgzdForm.getZd(), xgzdForm.getZdz(),
					xgzdForm.getXgqz() };
			paramList.add(param);
		}
		result = dao.runBatch(sb.toString(), paramList);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:TODO更新修改字段
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-14 上午08:49:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateXgzd(List<BysXxXgZdForm> list, String sqid)
			throws Exception {
		int[] result = null;
		List<String[]> paramList = new ArrayList<String[]>();
		List<String[]> yxgParamList = new ArrayList<String[]>();
		StringBuffer insertSql = new StringBuffer();
		insertSql.append("insert into xg_xsxx_xgzdb(sqid,zd,zdz,xgqz) ");
		insertSql.append(" values(?,?,?,?)");
		StringBuffer updateSql = new StringBuffer();
		updateSql.append("update xg_xsxx_xgzdb set zdz=? where sqid=? and zd=?");
		for (BysXxXgZdForm xgzdForm : list) {
			String[] param = { sqid, xgzdForm.getZd(), xgzdForm.getZdz(),
					xgzdForm.getXgqz() };
			
			if (Integer.parseInt(isYxgZd(param))>0) {
				String[] yxgParam = { param[2], param[0], param[1] };
				yxgParamList.add(yxgParam);
			} else {
				paramList.add(param);
			}

		}
		if(yxgParamList.size()>0){
			result = dao.runBatch(updateSql.toString(), yxgParamList);
			}
		if(paramList.size()>0){
			result = dao.runBatch(insertSql.toString(), paramList);
		}
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:TODO判断字段已修改过
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-14 上午08:53:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xgzd
	 * @return boolean 返回类型
	 * @throws
	 */
	private String isYxgZd(String[] xgzd) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) count from xg_xsxx_xgzdb where sqid=? and zd=? ");
		return dao.getOneRs(sb.toString(), new String[] {xgzd[0], xgzd[1]},"count");
	}

	/**
	 * 
	 * @描述:TODO保存毕业生信息修改申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-9 下午02:36:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean insertXgsq(BysXxXgSqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_bysxx_xxxgsqb(sqid,xh,xgsj,shjg,splc) ");
		sql.append(" values(?,?,?,?,?) ");
		String[] inputValue = { model.getSqid(), model.getXh(),
				model.getXgsj(), model.getShjg(), model.getSplc() };
		return dao.runUpdate(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:修改字段删除
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午09:05:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean xgZdDel(String[] values) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_xsxx_xgzdb where sqid=?");
		return dao.runUpdate(sql.toString(), values);
	}

	public boolean updateXgSq(BysXxXgSqForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_bysxx_xxxgsqb set shjg=? where sqid=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getShjg(),
				model.getSqid() });
	}

	/**
	 * 
	 * @描述:TODO申请修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-14 上午08:44:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean sqXgUpdate(BysXxXgSqForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_bysxx_xxxgsqb set shjg=?,xgsj=? where sqid=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getShjg(),
				model.getXgsj(), model.getSqid() });
	}

	/**
	 * 
	 * @描述:TODO获取申请信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-11 下午04:44:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getSqXxByXh(String xh) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from xg_bysxx_xxxgsqb");
		sb.append("  where  xh=?");
		String[] input = { xh };
		return dao.getMapNotOut(sb.toString(), input);
	}

	public HashMap<String, String> getXhBySqid(String sqid) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from xg_bysxx_xxxgsqb");
		sb.append("  where  sqid=?");
		String[] input = { sqid };
		return dao.getMapNotOut(sb.toString(), input);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_bysxx_xxxgsqb");
	}

}
