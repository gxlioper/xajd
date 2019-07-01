/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午02:11:15 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办申请管理模块
 * @类功能描述: TODO(学生证补办申请) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-17 下午02:11:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxsqDao extends SuperDAOImpl<YlbxsqForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setKey("ylsqid");
		super.setTableName("xg_rcsw_ylbx_ylbxsqb");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxsqForm t)
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
	public List<HashMap<String, String>> getPageList(YlbxsqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew from (select t1.ylsqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.cbsj,t1.zjsyrxm,t1.zjh,t5.xqmc, ");
		sql.append(" t1.cbzkdm,t1.sqly,t1.shzt,t1.splc,t2.xm,t2.xb,t2.bjmc, ");
		sql.append(" t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
		sql.append(" (t3.czqebzmc || '  ' || t7.czqebzmc || '  ' || t8.czqebzmc) bzlxmc, ");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '','无需审核', t1.shzt) shztmc, ");
		sql.append(" ( case when t1.czqebzdm is not null  then '有' else '无' end ) czqebzmc, ");
		sql.append(" ( case when t1.cbzkdm is not null  then '已参保' else '未参保' end ) cbzkmc ");
		sql.append(" from xg_rcsw_ylbx_ylbxsqb t1 left join ");
		sql.append(" (select t1.ylsqid,substr(t1.czqebzdm,'0','3') a1,substr(t1.czqebzdm,'5','3') a2,substr(t1.czqebzdm,'9','3') a3 from xg_rcsw_ylbx_ylbxsqb t1) t6 on t1.ylsqid=t6.ylsqid ");		
		sql.append(" left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xg_rcsw_ylbx_czqebzlxb t3 ");
		sql.append(" on t6.a1 = t3.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t7 on t6.a2 = t7.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t8 on t6.a3 = t8.czqebzdm left join xg_rcsw_ylbx_cbzklxb t4 ");
		sql.append(" on t1.cbzkdm = t4.cbzkdm  left join  xqdzb t5 on t1.xq = t5.xqdm ) a, xg_rcsw_ylbx_jcszb b where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @描述:TODO()
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
		sql.append(" select  splc from xg_rcsw_ylbx_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * 
	 * @描述:TODO(获取财政全额补助人员身份集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 上午08:35:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCzqebzryList() {
		String sql = "select czqebzdm,czqebzmc from xg_rcsw_ylbx_czqebzlxb  order by czqebzdm ";
		return dao.getList(sql, new String[] {}, new String[] { "czqebzdm",
				"czqebzmc" });
	}
	
	/**
	 * 
	 * @描述:TODO(获取参保状况集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 上午08:35:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzkList() {
		String sql = " select cbzkdm,cbzkmc from xg_rcsw_ylbx_cbzklxb order by cbzkdm ";
		return dao.getList(sql, new String[] {}, new String[] { "cbzkdm","cbzkmc" });
	}
	
	/**
	 * 
	 * @描述:TODO(得到当前学期名称)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 下午03:16:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select xqmc from xqdzb where xqdm=? ");
		String xqmc = dao.getOneRs(sql.toString(), new String[] { model.getXq()}, "xqmc");
		return xqmc;
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(获取医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午10:40:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYlbxsq(String ylsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_ylbx_ylbxsqb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.ylsqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{ylsqid});
	}
	
	
	
	
	/**
	 * 
	 * @描述:TODO(查看单条补办类型信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:57:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneBbsqList(String  bbsqId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','是', 'n', '否', t1.sfbbhcyhk) sfbbhcyhk, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc, ");
		sql.append(" decode(t1.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc,t3.xszbblxmc from xg_rcsw_xszbb_xszbbsqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
		sql.append(" on t1.xszbblxdm = t3.xszbblxdm  a  where select t1.bbsqid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{bbsqId});
	}
	

	
	/**
	 * 
	 * @描述:TODO(获取补办类型维护集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午02:46:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwdldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBblxwhList() {
		String sql = "select xszbblxdm,xszbblxmc from xg_rcsw_xszbb_bblxwhb ";
		return dao.getList(sql, new String[] {}, new String[] {"xszbblxdm", "xszbblxmc" });
	}
	
	
	public boolean isCanDel(String ylsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_ylbx_ylbxsqb where ylsqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{ylsqid});
		String ylbxsqzt=map.get("shzt");
		//如果未提交才可以提交
		return null==ylbxsqzt||ylbxsqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(更新医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午05:22:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ylsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYlbxsq(YlbxsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_ylbx_ylbxsqb ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where ylsqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getYlsqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午04:34:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getYlbxsqInfo(YlbxsqForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.ylsqid,a.xh,a.xn,a.xq,a.sqsj,a.cbsj,a.zjsyrxm,a.zjh,a.qtcbzkval,b.xqmc, ");
		sql.append(" decode(a.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" a.shzt) shztmc,a.sqly from xg_rcsw_ylbx_ylbxsqb a ");
		sql.append(" left join xqdzb b on a.xq = b.xqdm where ylsqid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getYlsqid()});
		
	}
	
	/**
	 * 
	 * @描述:TODO(待审核的记录总数)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:34:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from xg_rcsw_ylbx_ylbxsqb where shzt='0' or shzt='5'  ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:TODO(按照学号,学年，学期判断该学生医疗保险申请是否已经存在)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午08:40:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(YlbxsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_ylbxsqb where xh=? and xn = ? and xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;

	}
	
	
	
	
}
