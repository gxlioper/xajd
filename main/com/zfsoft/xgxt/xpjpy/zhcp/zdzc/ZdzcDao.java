/**
 * @部门:学工产品事业部
 * @日期：2015-5-29 上午11:35:33 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.InputVerifier;

import org.mortbay.html.Input;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2015-5-29 上午11:35:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzcDao extends SuperDAOImpl<ZdzcForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZdzcForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZdzcForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根

	}

	/** 
	 * @描述:获得综测分项目
	 * @作者：cq [工号：785]
	 * @日期：2015-5-29 下午03:15:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getzcxmList() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists ( ");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("order by jktb nulls last,fjdm,xmmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @描述:浙大个性化综测维护
	 * @作者：cq [工号：785]
	 * @日期：2015-5-29 下午03:20:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdzcForm
	 * @param zcxmList
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZcwhList(ZdzcForm t,
			List<HashMap<String, String>> zcxmList, User user) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,xh,xm,tjzt, decode(tjzt,'1','已提交','未提交') tjztmc,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}
		
		sql.append(" from ( select t1.id,t1.xh,t1.xm,nvl(t1.tjzt,0) tjzt,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj");
		
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",case when t2.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then fs else '' end fs").append(i);
		}
		sql.append(" from (select t1.id,t1.xn,t1.xq,t1.xh,t1.xm,t1.tjzt,t2.* from xg_pjpy_new_cpmdb t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm where t1.xn||t1.xq =(select xn || xq from xg_pjpy_new_csszb) and t2.bjdm is not null ");
		sql.append(searchTjByUser);
		sql.append(" ) t1 left join xg_zhcp_zcfsb t2 on t1.xh = t2.xh and t1.xn = t2.xn and t1.xq = t2.xq ");
		sql.append("where t1.xn || t1.xq in (select xn || xq from xg_pjpy_new_csszb where rownum = 1)) where 1=1  ");
		sql.append(searchTj);
		sql.append(" group by id, xh,xm, tjzt,bjdm,bjmc,zydm,zymc,xydm,xymc,nj order by decode(tjzt, '1','1','0'),nj,xydm,zydm,bjdm desc");
		return getPageList(t, sql.toString(), inputV);
	}

	
	/*
	 * 描述: 取消参评人员调整记录
	 */
	
	public boolean insertTzjl(String id, User user) throws Exception {
		
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" select xn,xq,xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') tzsj,'"+user.getUserName()+"'yhm,");
		sql.append("  '从 '||(select b.bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm)|| ");
		sql.append(" ' 调整为 不参评' tzbz from xg_pjpy_new_cpmdb a  where id in ( " );
		
		String[] ids = id.split(",");
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/*
	 * 描述: 取消参评学生
	 */
	
	public boolean updateCpmd(String id,User user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();
		
		sql.append(" update xg_pjpy_new_cpmdb ");
		sql.append("set bjdm = '' ");
		sql.append(" where id in ( ");
		String[] ids = id.split(",");
		
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * @throws Exception  
	 * @描述:根据ID更新对应的学年综测
	 * @作者：cq [工号：785]
	 * @日期：2014-8-6 上午10:19:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * void 返回类型 
	 * @throws 
	 */
	public boolean updateXncpmd(String values, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("MERGE INTO xg_pjpy_new_cpmdb a ");
		sql.append("USING (select xn,'on' xq,xh,xm,bjdm from (select a.*,row_number()over(partition by xh order by xq desc) rn from xg_pjpy_new_cpmdb a ");
		sql.append("where xq <> 'on' and xn||xh in (select xn||xh from xg_pjpy_new_cpmdb where id in ( ");
		
		String[] ids = values.split(",");
		for(int i=0; i<ids.length; i++ ){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		
		sql.append(") )) ");
		sql.append("where rn =1) b ");
		sql.append("ON (a.xh = b.xh and a.xq = b.xq and a.xn = b.xn) ");
		sql.append("WHEN MATCHED THEN ");
		sql.append("UPDATE ");
		sql.append("SET a.bjdm = b.bjdm ");
		sql.append("WHERE a.xq = b.xq and a.xh = b.xh and a.xn = b.xn ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("INSERT (xn,xq,xh,xm,bjdm) ");
		sql.append("VALUES (b.xn,b.xq, b.xh,b.xm,b.bjdm) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	
	/**
	 * 
	 * @描述:查询可导入学生
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 下午02:46:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getStuById(ZdzcForm model, User user) throws Exception{
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select a.xn,a.xq,a.xh,a.xm,a.tjzt,a.tjr,a.tjsj,b.* from xg_pjpy_new_cpmdb a left join view_njxyzybj_all b on a.bjdm = b.bjdm) ");
		sql.append("where bjdm is not null and xn||xq in (select xn || xq from xg_pjpy_new_csszb) and nvl(tjzt,0) <>'"+ZdzcService.tjzt_tj+"' ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		System.out.println("----导入查询学生-------"+sql.toString()+"----------------");
		String input = "";
		if(null!=inputV&&inputV.length!=0){
			for (int i = 0; i < inputV.length; i++) {
				input+=inputV[i]+",";
			}
		}
		System.out.println("------条件字段---------"+input+"");
		return dao.getRs(sql.toString(),inputV, "xh");
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 下午02:55:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean batchInsertZcfs(List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("MERGE INTO xg_zhcp_zcfsb t1");
		sql.append(" USING (select ? xh, ? xn, ? xq, ? xmdm, ? fs , ? lrr , to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') lrsj from dual) t2");
		sql.append(" ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm)");
		sql.append(" WHEN MATCHED THEN");
		sql.append("   UPDATE");
		sql.append("     SET xn=t2.xn,xq=t2.xq,fs=t2.fs,lrr=t2.lrr,");
		sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
		sql.append("   WHERE xh=? and xmdm=?");
		sql.append("WHEN NOT MATCHED THEN");
		sql.append("  INSERT (xh, xn, xq, xmdm, fs, lrr, lrsj)");
		sql.append("  VALUES (t2.xh, t2.xn, t2.xq, t2.xmdm, t2.fs, t2.lrr, t2.lrsj)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @描述:提交参评人员状态
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 下午04:24:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean tjCpxs(String values, String tjzt,ZdzcForm model, User user) throws Exception {
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_pjpy_new_cpmdb a set tjzt = '"+tjzt+"',tjr='"+user.getUserName()+"',tjsj=to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') ");
		sql.append("where xn||xq = (select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and exists (select 1 from (select * from (select nvl(a.tjzt,0) tjzt,a.id,a.xn,a.xq,a.xh,a.xm,b.* ");
		sql.append("from xg_pjpy_new_cpmdb a left join view_njxyzybj_all b on a.bjdm=b.bjdm where a.xn||a.xq = (select xn||xq from xg_pjpy_new_csszb where rownum=1) and b.bjdm is not null)where 1=1 ");
		sql.append("  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		if(!StringUtil.isNull(values)){
			String[] value = values.split(",");
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(") b where a.xh=b.xh)");
		
		return dao.runUpdate(sql.toString(), inputV);
		
	}

	/** 
	 * @描述:检测是否可提交综测分
	 * @作者：cq [工号：785]
	 * @日期：2015-6-10 下午05:20:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param zdzcForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isCanSubmit(String values, ZdzcForm zdzcForm, User user) throws Exception{
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(zdzcForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(zdzcForm.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		String[] value = values.split(",");
		
		sql.append("select count(1) count from (");
		sql.append("select a.id,a.tjzt,a.xh,b.* from xg_zhcp_zcxmb d left join xg_pjpy_new_cpmdb a on a.xn=d.xn  and a.xq=d.xq ");
		sql.append("left join view_njxyzybj_all b on a.bjdm=b.bjdm left join xg_zhcp_zcfsb c on a.xh=c.xh and a.xn=c.xn and a.xq=c.xq and d.xmdm=c.xmdm where c.fs is null");
		sql.append(searchTjByUser);
		sql.append(" and d.xn||d.xq = (select xn||xq from xg_pjpy_new_csszb where rownum=1) and d.xmdm not in (select fjdm from xg_zhcp_zcxmb) ) where 1=1 ");
		if(""!=values && values.length()!=0){
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(searchTj);
		
		String num = dao.getOneRs(sql.toString(), inputV, "count");
		
		return Integer.parseInt(num)==0;
	}
	
	/**
	 * 
	 * @描述:允许录入综测分的综测项目
	 * @作者：cq [工号：785]
	 * @日期：2015-6-18 上午09:20:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists");
		sql.append(" (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm = t2.fjdm)");
		sql.append("and xn || xq = (select xn || xq from xg_pjpy_new_csszb where rownum = 1)");
		sql.append("order by jktb nulls last, fjdm, xmmc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/**
	 * @描述:通过id取得学号列表
	 * @作者：黄辰霁
	 * @日期：2015-12-3 下午04:44:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String[] getXhArray(String values) throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh from xg_pjpy_new_cpmdb where 1=1");

		if(!StringUtil.isNull(values)){
			String[] value = values.split(",");
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		
		return dao.getArray(sql.toString(), new String[]{}, "xh");
	}

}
