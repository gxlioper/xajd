/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.ctc.wstx.util.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqForm;
import com.zfsoft.xgxt.sztz.zyszpj.ZyszpjForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjshDao extends SuperDAOImpl<QjshForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjshForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		//浙江经济职业个性化（判断请假开始时间是否超时）
		if("12866".equals(Base.xxdm)){
			sql.append(" select a.*, case when a.shzt = '0' and sysdate > to_date(a.cssj,'yyyy/mm/dd hh24:mi') then '1' when a.shzt = '5' and sysdate > to_date(a.cssj,'yyyy/mm/dd HH24:mi') then '1' else '0' end sfcs");
			sql.append(" from (select t1.*,(select xqmc from xqdzb where xqdm=t1.xq) xqmc,qjjg.xjzt,b.qjlxmc,t2.xm,t2.xb,t2.bjmc, t2.lxdh,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中') || ']' shztmc");
		}else{			
			sql.append(" select * from (select t1.*,(select xqmc from xqdzb where xqdm=t1.xq) xqmc,qjjg.xjzt,b.qjlxmc,t2.xm,t2.xb,t2.bjmc, t2.lxdh,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中') || ']' shztmc");
		}
		sql.append(" , d.guid shid , f.gwz ");
		sql.append(" ,row_number() over(partition by t1.qjsqid order by d.shsj desc) rn ");
		//浙江经济职业个性化（判断请假开始时间是否超时）
		if("12866".equals(Base.xxdm)){
			sql.append(", case when instr(t1.kssj,':') = '0' then  to_char((to_date(t1.kssj,'yyyy/mm/dd HH24:mi') + 1),'yyyy/mm/dd HH24:mi') else  to_char((to_date(t1.kssj,'yyyy/mm/dd HH24:mi')+1 / 24 / 60),'yyyy/mm/dd HH24:mi') end cssj");			
		}
		//湖南涉外经济学院个性化（增加楼栋，寝室号）
		if("12303".equals(Base.xxdm)){
			sql.append(" , cwxx.lddm,cwxx.ldmc,cwxx.qsh");
		}
		sql.append(" from xg_rcsw_qjgl_qjsq t1");
		//审核状态
		//sql.append(" join xg_xtwh_shztb sh on t1.qjsqid = sh.ywid");

		//学生信息
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		//湖南涉外个性化增加楼栋，寝室号
		if("12303".equals(Base.xxdm)){
			sql.append(" left join view_xg_gygl_new_cwxx cwxx on t1.xh = cwxx.xh ");
		}
		//审核状态信息
		sql.append(" left join xg_xtwh_shztb d");
		sql.append(" on t1.qjsqid = d.ywid");
		//查询审核状态
//		String shlx = t.getShzt();
//		if(!shlx.equals("dsh")){
//			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
//		}else{
//			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
//		}	
		//销假信息
		sql.append(" left join xg_rcsw_qjgl_qjjg qjjg on qjjg.qjsqid=t1.qjsqid");
		//请假类型信息
		sql.append(" left join xg_rcsw_qjgl_qjlx b on t1.qjlxid=b.qjlxid ");
		//审核岗位新信息
		sql.append(" left join xg_xtwh_spgwyh e");
		sql.append(" on d.gwid = e.spgw");
		sql.append(" left join xg_xtwh_spgw f");
		sql.append(" on d.gwid = f.id");
		sql.append(" where e.spyh = '"+user.getUserName()+"' and t1.shzt<>9 and d.shzt<>9 ");

		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		}else{
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}	
		sql.append(" ) a where rn = 1 )a ");
		sql.append(" where 1 = 1 ");
//		sql.append(" and qjzt=1 ");  modify by zhuon
		sql.append(searchTjByUser);
		sql.append(shgwzByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获得对应请假审核状态
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-12 上午10:06:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqId
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShid(String qjsqId,String gwid){
		StringBuffer sql=new StringBuffer();
		sql.append("select guid from xg_xtwh_shztb sh where sh.ywid=? and sh.gwid=?");
		return dao.getOneRs(sql.toString(), new String[]{qjsqId,gwid}, "guid");
	}
	/**
	 * 
	 * @描述:获取对应流程信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-11 上午11:58:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getSplcInfo(QjshForm t){
		StringBuilder sql = new StringBuilder();
		sql
				.append("select * from (select t1.*,b.qjlxmc,t2.xm,t2.xb,t2.bjmc, t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.nj,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中') || ']' shztmc");
		sql.append(" from xg_rcsw_qjgl_qjsq t1");
		//学生信息
		sql.append(" left join view_xsxxb t2");
		sql.append(" on t1.xh = t2.xh");
		//审核状态信息
		sql.append(" left join xg_xtwh_shztb d");
		sql.append(" on t1.qjsqid = d.ywid");
		//查询审核状态
		String shlx = t.getShzt();
		if(null!=shlx&&shlx.equals("dsh")){
			sql.append(" and d.shzt=0  ");
		}else{
			sql.append(" and (d.shzt=1 or d.shzt=2) ");
		}
		//请假类型信息
		sql.append(" left join xg_rcsw_qjgl_qjlx b on 1=1 ");
		sql.append(" and  t1.qjlxid=b.qjlxid");
		//审核岗位新信息
		sql.append(" left join xg_xtwh_spgwyh e");
		sql.append(" on d.gwid = e.spgw");
		sql.append(" left join xg_xtwh_spgw f");
		sql.append(" on d.gwid = f.id");
		sql.append(" where e.spyh = 'zf01') t5");
		sql.append(" where 1 = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getQjsqid()});
	}
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjshForm t)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述：获取下一个请假类型id
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:54:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 * @throws
	 */
	public String getNextId() {
		String qjlxid = dao.getOneRs(
				"select max(qjlxid)qjlxid from xg_rcsw_qjgl_qjlx ",
				new String[] {}, "qjlxid");
		String newId = String.valueOf(Integer.parseInt(qjlxid) + 1);
		if (newId.length() == 1) {
			newId = "0" + newId;
		}
		return newId;
	}

	/**
	 * 
	 * @描述:验证请假类型，如果已经添加过不允再进行添加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午07:18:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkQjlx(QjshForm qf) {
		// String
		// qjlxid=dao.getOneRs("select qjlxid from xg_rcsw_qjgl_qjgz where kssj<? and ?<=jssj",new
		// String[]{qf.getQjlxmc()},"qjlxid");
		// 如果获取到对应的id则不运行再添加
		return true;
	}

	public List<HashMap<String, String>> getQjlx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select qjlxid,qjlxmc from xg_rcsw_qjgl_qjlx order by qjlxid asc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	/**
	 * 
	 * @描述:删除对应请假结果
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-24 下午05:56:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqId
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 */
	public int deleteQjjgForQjsqId(String qjsqId) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete xg_rcsw_qjgl_qjjg qjjg where qjjg.qjsqid=?");
		return dao.runDelete(sql.toString(),new String[]{qjsqId});
	}
	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjsqid");
		this.setTableName("xg_rcsw_qjgl_qjsq");
		this.setClass(QjshForm.class);
	}
	
	/* (non-Javadoc)
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	@Override
	public QjshForm getModel(QjshForm t) throws Exception {
		String sql = "select t1.*,t2.qjlxmc,decode(t1.sflx,'1','是','0','否','') sflxmc from xg_rcsw_qjgl_qjsq t1 left join xg_rcsw_qjgl_qjlx t2 on t1.qjlxid=t2.qjlxid where t1.qjsqid=?";
		return super.getModel(sql, new String[]{t.getQjsqid()});
	}

}
