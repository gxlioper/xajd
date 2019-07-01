/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 下午06:52:18 
 */
package com.zfsoft.xgxt.axcs.wpjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 下午06:52:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpjgDao extends SuperDAOImpl<WpjgForm> {



	@Override
	public List<HashMap<String, String>> getPageList(WpjgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}



	@Override
	public List<HashMap<String, String>> getPageList(WpjgForm t, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (select t1.*,t2.xmmc,t2.xmlb,t2.xmxxjs,t3.mc xmlbmc,t4.xm, t4.xb,t4.bjmc,t4.xydm,t4.xymc,t4.zydm,t4.zymc,t4.zzmmmc, t4.bjdm, t4.nj ");
		sql.append(" from xg_axjz_axcsxmjgb t1 left join xg_xszz_axcsxmb t2 on t1.xmdm=t2.xmdm left join xg_xszz_axcslbb t3 on t2.xmlb = t3.dm");
		sql.append(" left join view_xsbfxx t4 on t1.xh = t4.xh)t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:根据学年、学号、项目代码删除结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午07:23:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean delWpjgxx(String xh, String xn, String xmdm) throws Exception {
		String sql = "delete from xg_axjz_axcsxmjgb where xh = ? and xn = ? and xmdm = ?";
		return dao.runUpdate(sql, new String[] { xh, xn, xmdm });
	}
	
	public boolean delWpjgBySqid(String sqid) throws Exception {
		String sql = "delete from xg_axjz_axcsxmjgb where sqid = ?";
		return dao.runUpdate(sql, new String[] { sqid});
	}

	@Override
	protected void setTableInfo() {
		super.setClass(WpjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_axjz_axcsxmjgb");

	}

	/**
	 * 
	 * @描述:判断结果表当中的学年是否已申请
	 * @作者：cq [工号：785]
	 * @日期：2014-12-9 下午04:33:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(WpjgForm model) {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select count(1) num from xg_axjz_axcsxmjgb where xh= ? and xn= ? and xmdm= ?");
		params.add(model.getXh());
		params.add(model.getXn());
		params.add(model.getXmdm());
		
		if(!StringUtils.isBlank(model.getJgid())){
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");

	}
	
	
	/**
	 * 
	 * @描述:流程通过的数据不可以修改
	 * @作者：cq [工号：785]
	 * @日期：2014-12-9 下午04:33:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_axjz_axcsxmjgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
		
	}
	
	
	/**
	 * 
	 * @描述:结果单个查询
	 * @作者：cq [工号：785]
	 * @日期：2014-12-9 下午04:34:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> viewOneWpjgList(String jgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jgid,t1.sqid,t1.xn,t1.xh,t1.sqsj,t1.sqly,t1.sjly,t1.xn,t2.*,t3.*,t3.mc xmlbmc ");
		sql.append(" from xg_axjz_axcsxmjgb  t1 left join xg_xszz_axcsxmb t2 on t1.xmdm=t2.xmdm left join xg_xszz_axcslbb t3  on t2.xmlb = t3.dm ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{jgId});
	}
	
	
	public HashMap<String, String> getWpjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_axjz_axcsxmjgb a");
		sb.append(",view_xsbfxx xsxx where a.xh=xsxx.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}
	
	/**
	 * @throws Exception  
	 * @描述:根据学号学年,学年,项目代码删除
	 * @作者：cq [工号：785]
	 * @日期：2014-12-4 上午11:19:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int delForXhxnxmdm(String xh, String xn, String xmdm) throws Exception {
		
		String sql = "delete from xg_rcsw_lstd_jgb where xh = ? and xn = ? and xmdm = ?" ; 
		
		return dao.runDelete(sql, new String[]{xh,xn,xmdm});
	}


}
