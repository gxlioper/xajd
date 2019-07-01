/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 下午04:40:31 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg;
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
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-31 下午04:40:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxqybjgDao extends SuperDAOImpl<BjxqybjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BjxqybjgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from (");
		sql.append(" select t1.jgid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd,substr(t1.yf,6,2)yf,t1.bygzkzqk,t1.xsgzrd, ");
		sql.append(" t1.xssxdt,t1.xstsjgzjy,t1.txsj,t1.txr,t1.bjdm,t1.sjly, t2.nj,t2.xymc,t2.xydm,t2.bjmc,t2.zymc,t2.zydm,t7.xqmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_jg t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  left join fdyxxb t3 on t1.txr = t3.zgh  ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm  ");
		sql.append(" ) a where 1 = 1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-审核通过，先删除旧数据)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午01:57:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteExist(BjxqybjgForm model) throws Exception {
		
		StringBuffer sql = new StringBuffer(
		" delete from xg_bjzyy_xqyb_bjyb_jg where xn=? and xq = ? and yf = ? and bjdm = ? ");
		return dao.runUpdate(sql.toString(), new String[] { model.getXn(),model.getXq(),model.getYf(),model.getBjdm()});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setKey("jgid");
		super.setTableName("xg_bjzyy_xqyb_bjyb_jg");

	}
	
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-按照班级代码, 学年,月份,填写人判断班级学情月报结果表中是否已经存在该班)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-7 下午04:34:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForBjxqybjgSave(BjxqybjgForm model) {
		StringBuffer sql = new StringBuffer(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_jg where xn = ?  and xq = ? and yf = ? and bjdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getXq(),model.getYf(),model.getBjdm()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @描述:TODO(按照班级代码, 学年,月份,填写人,申请id判断班级学情月报结果表中是否已经存在该班)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-7 下午04:50:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForBjxqybjgUpdate(BjxqybjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_jg where txr = ? and xn = ?  and xq = ? and yf = ?  and jgid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getTxr(),model.getXn(),
			model.getXq(),model.getYf(),model.getJgid()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-获取单个班级学情月报结果详情)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 上午09:21:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybjgInfo(BjxqybjgForm model){
		
		StringBuffer sql = new StringBuffer();				
		
		sql.append(" select t1.jgid,t1.xn,t1.xq,t1.yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy, ");		
		sql.append("  t1.txsj,t1.txr,t1.sjly,t1.lylcywid,t1.bjdm,t2.xymc,t2.bjmc,t3.xqmc,t4.xm ");		
		sql.append(" from xg_bjzyy_xqyb_bjyb_jg t1 left join view_njxyzybj_all t2 ");		
		sql.append(" on t1.bjdm = t2.bjdm left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" left join fdyxxb t4 on t1.txr = t4.zgh ");
						
		sql.append("  where t1.jgid  = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{model.getJgid()});
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-按照数据来源判断是否是走审核流数据)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 上午10:05:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select sjly from xg_bjzyy_xqyb_bjyb_jg where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级学情结果-获取要删除的班级学情月报结果表中的班级名称)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 上午10:11:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBjxqybjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.bjdm bjdm,b.bjmc bjmc from xg_bjzyy_xqyb_bjyb_jg a,view_njxyzybj_all b where a.bjdm = b.bjdm ");
		sb.append(" and a.jgid = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}
	
	/** 
	 * @描述:获取合并导出list(北京中医药个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-30 上午09:08:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(BjxqybjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from (select * from (");
		sql.append(" select t1.jgid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd,substr(t1.yf,6,2)yf,t1.bygzkzqk,t1.xsgzrd, ");
		sql.append(" t1.xssxdt,t1.xstsjgzjy,t1.txsj,t1.txr,t1.bjdm,t1.sjly, t2.nj,t2.xymc,t2.xydm,t2.bjmc,t2.zymc,t2.zydm,t7.xqmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_jg t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  left join fdyxxb t3 on t1.txr = t3.zgh  ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm  ");
		sql.append(" ) b group by nd,yf,xymc,bjmc,xn,xq,ny,jgid,bygzkzqk,xsgzrd,xssxdt,xstsjgzjy,txsj,txr,bjdm,sjly,nj,xydm,zydm,zymc,xqmc,lrrxm order by nd,yf,xymc,bjmc asc) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
}
