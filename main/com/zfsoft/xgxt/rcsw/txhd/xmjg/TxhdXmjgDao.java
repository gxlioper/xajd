/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:39:48 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-6-24 上午09:39:48
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXmjgDao extends SuperDAOImpl<TxhdXmjgForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmjgForm t)
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
	public List<HashMap<String, String>> getPageList(TxhdXmjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( ");
		sql.append("select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc, ");
		sql.append("(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm)lbmc, ");
		sql.append("hdkssj||' 至 '||hdjssj hdsj, ");
		sql.append("b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from XG_RCSW_TXHD_JGB a ");
		sql.append("left join view_xsbfxx b on b.xh=a.xh ) where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:活动增加判断
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-09-25 上午09:12:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean isAdd(TxhdXmjgForm myForm) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from xg_rcsw_txhd_jgb where xh=? and xn=? and xq=? and xmmc=?");
		Map<String, String> map = dao.getMapNotOut(sb.toString(), new String[] {
				myForm.getXh(), myForm.getXn(), myForm.getXq(),
				myForm.getXmmc() });
		String xh = map.get("xh");
		if (xh == null) {
			return true;
		}
		return xh.equals(myForm.getXh()) ? false : true;
	}
	/**
	 * 删除对应申请信息
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-6-26 下午04:27:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * int 返回类型
	 */
	public int deleteForSh(String sqid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_rcsw_txhd_jgb where sqid=?");
		return dao.runDelete(sb.toString(), new String[] { sqid });
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		this.setKey("guid");
		this.setTableName("xg_rcsw_txhd_jgb");
		this.setClass(TxhdXmjgForm.class);

	}
	
	/**
	 * 
	 * @描述:单个查看活动结果
	 * @作者：cq [工号：785]
	 * @日期：2014-6-29 下午05:56:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getOneHdjgList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,c.xm,(case when a.xckssj is null then a.xckssj else substr(a.xckssj,length(a.xckssj)-2,length(a.xckssj)-1)||'时' end)kshour,");
		sql.append("(case when a.xcjssj is null then a.xcjssj else substr(a.xcjssj,length(a.xcjssj)-2,length(a.xcjssj)-1)||'时' end)jshour,");
		sql.append("(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm = b.lbdm) lbmc,");
		sql.append("(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc ");
		sql.append("from xg_rcsw_txhd_jgb a left join xsxxb c on a.xh=c.xh where guid= ?  ");
		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}

}
