/**
 * @部门:学工产品事业部
 * @日期：2016-1-15 上午11:39:31 
 */
package com.zfsoft.xgxt.ystgl.jtgl.ystwh;


import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 艺术团管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2016-1-15 上午11:39:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YstwhDao extends SuperDAOImpl<YstwhForm> {

	@Override
	public List<HashMap<String, String>> getPageList(YstwhForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YstwhForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchSztzShTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xmlbmc,t3.ystlbmc,nvl(t4.xm,t1.jtr) jtrxm,t5.xm fzrxm,t6.xm zdlsxm,t7.gkdwmc,t9.lxdh zdlslxfs,t7.gkdwdm xydm,t10.zcmc ");
		sql.append("from XG_YSTGL_YSTJGB t1 left join XG_YSTGL_XMLB t2 on t1.xmlbdm = t2.xmlbdm left join XG_YSTGL_YSTLB t3 on t1.ystlbdm=t3.ystlbdm ");
		sql.append(" left join  ");
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t4  ");
			sql.append(" on t1.jtr=t4.yhm");
		}else{
			sql.append("  xsxxb t4");
			sql.append(" on t1.jtr=t4.xh");
		}
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t5");
		sql.append(" on t1.fzr = t5.yhm left join yhb t6 on t1.zdls = t6.yhm left join XG_YSTGL_GKDW t7 on t1.gkdwdm=t7.gkdwdm left join fdyxxb t9 on t1.zdls=t9.zgh");
		sql.append(" left join zcb t10 on t1.zdlszc=t10.zcdm) t where 1=1 ");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and t.jtr = '"+user.getUserName()+"' ");
		}
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(searchTjByUser);
		}
		sql.append(searchTj);
		sql.append(" order by sqsj desc ");
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * @throws Exceptionzil
	 * 
	 * @描述:获取艺术团结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-15 下午04:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getYstwh(YstwhForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.ystlbmc,t3.xmlbmc,t4.xm jtrxm,t5.xm fzrxm,t6.xm zdlsxm,t7.bmmc ssbmmc,t8.zcmc ");
		sql.append(",t9.bmdm ssbm,t9.lxdh zdlslxfs,t10.gkdwmc ");
		sql.append(" from XG_YSTGL_YSTJGB t1  left join XG_YSTGL_YSTLB t2 on t1.ystlbdm=t2.ystlbdm");
		sql.append(" left join XG_YSTGL_XMLB t3 on t1.xmlbdm=t3.xmlbdm ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t4 on t1.jtr=t4.yhm ");
		if(t.getFzrlb().equals("老师")){
			sql.append(" left join yhb t5 on t1.fzr = t5.yhm");
		}else{
			sql.append(" left join xsxxb t5 on t1.fzr = t5.xh ");
		}
		sql.append(" left join yhb t6 on t1.zdls = t6.yhm ");
		sql.append(" left join zxbz_xxbmdm t7 on  t5.szbm = t7.bmdm");
		sql.append(" left join zcb t8 on t1.zdlszc = t8.zcdm");
		sql.append(" left join fdyxxb t9 on t1.zdls=t9.zgh left join XG_YSTGL_GKDW t10 on t1.gkdwdm=t10.gkdwdm");
		sql.append(" where t1.ystid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getYstid() });
	}


	/**
	 *判断艺术团结果是否已存在, 大于等于相同学年的艺术团项目不能重复
	 */
	public boolean isHaveYstxx(YstwhForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_YSTGL_YSTJGB where  xn>=?  and ystxmmc=?");
		if(null!=model.getYstid()){
			sql.append(" and ystid<>'"+model.getYstid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getYstxmmc()}, "num");
		return Integer.parseInt(num)>0;
	}


	/**
	 * 
	 * 删除艺术团结果
	 */
	public boolean delYstwh(String id) throws Exception {
		String sql = "delete from XG_YSTGL_YSTJGB where ystid=?";
		return dao.runUpdate(sql, new String[] { id });
	}
	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from( select ystid from xg_ystgl_rtsqb where ystid in("+values+")");
		sql.append(" union all select ystid from xg_ystgl_rtjgb where ystid in("+values+"))");
		int result = dao.getOneRsint(sql.toString());
		return result > 0;
	}

	
	@Override
	protected void setTableInfo() {
		super.setClass(YstwhForm.class);
		super.setKey("ystid");
		super.setTableName("XG_YSTGL_YSTJGB");

	}
	
	//艺术团综合维护维护团内状态时更新cysl
	public boolean update_stcysl(String cysl,String ystid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_YSTGL_YSTJGB set cysl = ? where ystid = ?");
		return dao.runUpdate(sql.toString(), new String[]{cysl,ystid});
	}
	
}
