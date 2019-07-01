/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午02:11:15 
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车区间填写管理模块
 * @类功能描述: TODO(火车区间填写) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-26 上午09:40:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjtxDao extends SuperDAOImpl<HcccqjtxForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo(){
		
		super.setKey("ccqjtxid");
		super.setTableName("xg_rcsw_hcyhk_hcccqjtxb");
		
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjtxForm t)
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
	public List<HashMap<String, String>> getPageList(HcccqjtxForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm");
		}
		sql.append(" from (");
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,t1.bz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		sql.append(" t1.shzt,t1.splc,t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t2.jtdz,t6.bjmc,t2.lxdh,t6.xydm,t6.zydm,t2.bjdm,t6.xymc,t6.zymc,t2.nj,t3.xqmc, ");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过','2','不通过', '3','已退回', '4','需重审', ");
		sql.append(" '5', '审核中', '','无需审核',t1.shzt) shztmc from xg_rcsw_hcyhk_hcccqjtxb ");
		sql.append(" t1  left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t6 on t2.bjdm = t6.bjdm ");
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm ) a left join  xg_rcsw_hcyhk_jcszb b on 1=1 ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on a.bjdm = bzr.bjdm");
		}
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	public HcccqjtxForm getModel(HcccqjtxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.lxmc hcyhklxmc,c.xqmc from xg_rcsw_hcyhk_hcccqjtxb a ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx b on a.hcyhklx = b.lxdm ");
		sql.append(" left join xqdzb c on a.xq=c.xqdm");
		sql.append(" where a.ccqjtxid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getCcqjtxid() });
	}

	/**
	 * 
	 * @描述:TODO(获取审批流程ID)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午02:19:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param Rcxwlbdldm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcsw_hcyhk_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public HashMap<String, String> getHcccqjtx(String ccqjtxid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_hcyhk_hcccqjtxb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.ccqjtxid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{ccqjtxid});
	}
	
	
	
	public boolean isCanDel(String ccqjtxid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_hcyhk_hcccqjtxb where ccqjtxid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{ccqjtxid});
		String bbsqzt=map.get("shzt");
		//如果未提交才可以提交
		return null==bbsqzt||bbsqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午04:41:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccqjtxid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateHcccqjtx(HcccqjtxForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_hcyhk_hcccqjtxb ");
		sql.append(" set ");
		sql.append(" shzt = ? ,");
		sql.append(" splc = ? ");
		sql.append(" where ccqjtxid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getCcqjtxid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(查看火车区间填写信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 下午04:45:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getHcccqjtxInfo(HcccqjtxForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,t1.bz, ");
		sql.append(" (t1.ccqdz || '-' ||t1.cczdz) hcccqjmc,t3.xqmc,t4.lxmc hcyhklxmc ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjtxb t1 left join view_xsxxb t2 ");
		sql.append("  on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t4 on t1.hcyhklx=t4.lxdm ");
		sql.append("  where t1.ccqjtxid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getCcqjtxid()});
	}
	
	/**
	 * 
	 * @描述:TODO(按照学号, 学年,学期判断火车乘车区间填写表中是否已经存在该学生)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-13 下午05:25:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForHcccqjtxSave(HcccqjtxForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_hcyhk_hcccqjtxb where xh=? and xn = ?  and xq = ? and shzt <> '2' ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @描述:TODO(按照学号, 学年,学期,区间填写id判断火车乘车区间填写表中是否已经存在该学生)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-17 下午03:14:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForHcccqjtxUpdate(HcccqjtxForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_hcyhk_hcccqjtxb where xh=? and xn = ?  and xq = ? and shzt <> '2' and ccqjtxid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq(),model.getCcqjtxid()}, "num");
		return num;
	}
	
	public int getDshCount() throws SQLException{
		String sql = "select count(1) num from xg_rcsw_hcyhk_hcccqjtxb where shzt='0' or shzt='5'  ";
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:TODO(获取基础设置默认的乘车起点站)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-19 上午11:29:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXxccqdz() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select ccqdz from xg_rcsw_hcyhk_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "ccqdz");
		
	}
	
	
	/**
	 * 
	 * @描述:根据学号获取最新乘车区间结果
	 * @作者：cq [工号：785]
	 * @日期：2014-8-28 上午11:51:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getHcccqj(String xh){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t1.*,(t1.ccqdz || '-' || t1.cczdz) hcccqjmc,row_number() over (partition by xh order by xn desc,xq desc) rm ");
		sql.append("from xg_rcsw_hcyhk_hcccqjjgb t1 ) where rm =1 and xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
		
	}
	
	/*
	 * 取学期名称
	 */
	public String getXqmc(String xq){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xqmc from xqdzb where xqdm = ? ");
		
		return dao.getOneRs(sql.toString(), new String[] {xq}, "xqmc");
		
	}

}
