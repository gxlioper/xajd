/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午02:11:15 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsq;

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
 * @模块名称: 学生证补办申请管理模块
 * @类功能描述: TODO(学生证补办申请) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-17 下午02:11:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbsqDao extends SuperDAOImpl<XszbbsqForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setKey("bbsqid");
		super.setTableName("xg_rcsw_xszbb_xszbbsqb");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbsqForm t)
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
	public List<HashMap<String, String>> getPageList(XszbbsqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		if("11400".equals(Base.xxdm)){
			sql.append(" select a.*,b.splc splcidnew from (");
			sql.append(" select t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','是', 'n', '否', t1.sfbbhcyhk) sfbbhcyhk, ");
			sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.xymc,t2.zymc,t2.zydm, t2.bjdm, t2.nj, t2.sfzh,");
			sql.append(" decode(t1.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
			sql.append(" '已退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
			sql.append(" t1.shzt) shztmc,t3.xszbblxmc,t4.fdyxm,t4.ylbxh from xg_rcsw_xszbb_xszbbsqb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
			sql.append(" on t1.xszbblxdm = t3.xszbblxdm  " +
					" left join xsxxb t4 on t1.xh = t4.xh" +
					" ) a, xg_rcsw_xszbb_jcszb b where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}else{
			sql.append(" select a.*,b.splc splcidnew");
			//江西航空职业技术学院
			if("13871".equals(Base.xxdm)){
				sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm");
			}
			sql.append("  from (");
			sql.append(" select t1.dd,t1.sj,t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','是', 'n', '否', t1.sfbbhcyhk) sfbbhcyhk, ");
			sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.xymc,t2.zymc,t2.zydm, t2.bjdm, t2.nj, t2.sfzh,");
			sql.append(" decode(t1.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
			sql.append(" '已退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
			sql.append(" t1.shzt) shztmc,t3.xszbblxmc,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc from xg_rcsw_xszbb_xszbbsqb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
			sql.append(" on t1.xszbblxdm = t3.xszbblxdm  ) a left join  xg_rcsw_xszbb_jcszb b on 1 = 1  ");
			//江西航空职业技术学院
			if("13871".equals(Base.xxdm)){
				sql.append(" left join view_xg_bzrxx bzr on a.bjdm = bzr.bjdm");
			}
			sql.append(" where 1=1");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}
		return getPageList(t, sql.toString(), inputV);
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
		sql.append(" on t1.xszbblxdm = t3.xszbblxdm where t1.bbsqid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{bbsqId});
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
		sql.append(" select  splc from xg_rcsw_xszbb_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public HashMap<String, String> getBbsq(String bbsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_xszbb_xszbbsqb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.bbsqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{bbsqid});
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
		String sql = "select xszbblxdm,xszbblxmc from xg_rcsw_xszbb_bblxwhb where shlc is not null order by xszbblxdm ";
		return dao.getList(sql, new String[] {}, new String[] {"xszbblxdm", "xszbblxmc" });
	}
	
	
	public boolean isCanDel(String bbsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_xszbb_xszbbsqb where bbsqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{bbsqid});
		String bbsqzt=map.get("shzt");
		//如果未提交才可以提交
		return null==bbsqzt||bbsqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:13:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBbsq(XszbbsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_xszbb_xszbbsqb ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where bbsqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getBbsqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 下午04:45:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getXszbbsqInfo(XszbbsqForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sj,t1.dd,t1.filepath,t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','是', 'n', '否', t1.sfbbhcyhk) sfbbhcyhk, ");
		if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//青岛酒店个性化
			sql.append("t1.ccqdz,t1.cczdz,t1.sfbbhcyhk sfbbhcyhkmc,");
		}
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj, ");
		sql.append(" decode(t1.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc,t3.xszbblxmc from xg_rcsw_xszbb_xszbbsqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
		sql.append(" on t1.xszbblxdm = t3.xszbblxdm   where t1.bbsqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getBbsqid()});
	}
	
	/**
	 * 
	 * @描述:TODO(待审核的记录总数)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 上午10:10:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from xg_rcsw_xszbb_xszbbsqb where shzt='0' or shzt='5'  ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:TODO(按照学号判断该学生学生证补办是否已经存在)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午08:40:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(XszbbsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_xszbb_xszbbsqb where xh=? and ( shzt<>'1' and shzt<>'2') and xszbblxdm = ?");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXszbblxdm()}, "num");
		return num;

	}
	
	/** 
	 * @描述:获取火车乘车区间数据(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-23 下午05:06:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getHcccqj(String xh) {
		String sql = "select a.* from (select xh,ccqdz,cczdz,xn,xq,rownum rn from xg_rcsw_hcyhk_hcccqjjgb where xh = ? order by txsj desc) a where a.rn = 1";
		return dao.getMapNotOut(sql, new String[]{xh});
	}

}
