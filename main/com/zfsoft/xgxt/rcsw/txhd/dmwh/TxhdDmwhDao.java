/**
 * @部门:学工产品事业部
 * @日期：2014-6-20 上午11:23:55 
 */
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-学团活动管理模块
 * @类功能描述: 学团活动管理
 * @作者： cq [工号:785]
 * @时间： 2014-6-20 上午11:23:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdDmwhDao extends SuperDAOImpl<TxhdDmwhForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdDmwhForm t)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(case when a.lbdm in (select lbdm from xg_rcsw_txhd_xmwh) or a.lbdm in ");
		sql.append("(select lbdm from xg_rcsw_txhd_jgb)then '是' else '否' end ) sfsy from xg_rcsw_txhd_lbdm a where 1=1 ");
		if (!StringUtil.isNull(t.getLbmc())) {
			params.add(t.getLbmc());
			sql.append(" and lbmc like '%'||?||'%'");
		}

		sql.append(" order by to_number(lbdm) ");

		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdDmwhForm t, User user)
			throws Exception {
		// 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {

		super.setTableName("xg_rcsw_txhd_lbdm");
		super.setKey("lbdm");

	}

	/**
	 * 
	 * @描述: 判断类别名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午01:44:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param txhdDmwhForm
	 * @return String 返回类型
	 * @throws
	 */
	public String lbmcCheckExist(TxhdDmwhForm form) {

		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_txhd_lbdm where lbmc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { form
				.getLbmc() }, "num");
		return num;
	}

	/**
	 * 
	 * @描述: 判断类别代码是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午01:53:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param txhdDmwhForm
	 * @return String 返回类型
	 * @throws
	 */
	public String lbdmCheckExist(TxhdDmwhForm form) {

		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_txhd_lbdm where lbdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { form
				.getLbdm() }, "num");
		return num;
	}

	/**
	 * 
	 * @描述:查询最大的类别代码
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午02:10:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException int 返回类型
	 * @throws
	 */
	public int getMaxLbdm() throws SQLException {

		String sql = "select nvl(max(to_number(lbdm)),0) lbdm from xg_rcsw_txhd_lbdm";

		return dao.getOneRsint(sql);
	}

	/**
	 * 
	 * @描述:查询申请表当中是否已使用
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午02:15:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 *             String[] 返回类型
	 * @throws
	 */
	public String[] lbCheckExistForXmwh(String value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" select distinct b.lbmc from xg_rcsw_txhd_xmwh a left join xg_rcsw_txhd_lbdm b on a.lbdm=b.lbdm ");
		sql.append(" where b.lbdm in ( " + value + " ) ");
		String[] lbmc = dao.getRs(sql.toString(), new String[] {}, "lbmc");

		return lbmc;
	}

	/**
	 * 
	 * @描述:查询结果表当中是否已使用
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午02:36:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 *             String[] 返回类型
	 * @throws
	 */
	public String[] lbCheckExistForJg(String value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" select distinct b.lbmc from xg_rcsw_txhd_jgb a left join xg_rcsw_txhd_lbdm b on a.lbdm=b.lbdm ");
		sql.append(" where b.lbdm in ( " + value + " ) ");
		String[] lbmc = dao.getRs(sql.toString(), new String[] {}, "lbmc");

		return lbmc;
	}

	/** 
	 * @描述:获取类别代码List
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 上午11:59:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLblist() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select lbdm,lbmc from xg_rcsw_txhd_lbdm order by lbdm ");
		String[] input = {};
		
		return dao.getListNotOut(sb.toString(), input);
	}
	
	public List<HashMap<String, String>> getHdggList(TxhdDmwhForm t)
	   throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(case when a.hdggdm in (select hdggdm from xg_rcsw_txhd_xmwh)" +
				" or a.hdggdm in(select hdggdm from xg_rcsw_txhd_xmsq) ");
		sql.append("then '是' else '否' end ) sfsy from XG_RCSW_TXHD_HDGG a where 1=1 ");
		if (!StringUtil.isNull(t.getHdggmc())) {
			params.add(t.getHdggmc());
			sql.append(" and hdggmc like '%'||?||'%'");
		}
		
		sql.append(" order by to_number(a.hdggdm) asc ");
		
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
   }
	
	//活动规格新增保存
	public boolean saveHdgg(TxhdDmwhForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into XG_RCSW_TXHD_HDGG values(SEQ_HDGG.nextval,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getHdggmc()});
	}
	
	//活动规格新增保存
	public boolean saveUpdateHdgg(TxhdDmwhForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update XG_RCSW_TXHD_HDGG set hdggmc = ? where hdggdm = ?");
		return dao.runUpdate(sql.toString(), new String[]{t.getHdggmc(),t.getHdggdm()});
	}
	
	//活动规格重复验证
	public boolean checkIsExits(TxhdDmwhForm t){
		StringBuilder sql = new StringBuilder();
		String[] inputValue = new String[]{t.getHdggmc()};
		sql.append("select count(1) num from XG_RCSW_TXHD_HDGG where hdggmc = ? ");
		if(t.getHdggdm() != null && !t.getHdggdm().equals("")){
			sql.append(" and hdggdm <> ?");
			inputValue = new String[]{t.getHdggmc(),t.getHdggdm()};
		}
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		return Integer.parseInt(num)>0;
	}
	
	//删除
	public int delHdgg(String[] hdggdms) throws Exception{
		StringBuilder sql = new StringBuilder();
		int num = hdggdms.length;
		boolean result = true;
		sql.append("delete from XG_RCSW_TXHD_HDGG where hdggdm in(");
		for(int i=0;i < num;i++){
			if(i != num-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		result = dao.runUpdate(sql.toString(), hdggdms);
		return result ? num : 0 ;
	}
	
	//获取活动规格列表
	public List<HashMap<String, String>> getHdggList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select hdggdm dm,hdggmc mc from  xg_rcsw_txhd_hdgg ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//获取活动规格名称
	public String getHdggmc(String hdggdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select hdggmc hdgg from xg_rcsw_txhd_hdgg where hdggdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{hdggdm}, "hdgg");
	}

}
