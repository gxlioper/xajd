/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 下午04:41:49 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 计分项目(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 下午04:41:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfJfxmwhDao extends SuperDAOImpl<ZhfJfxmwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfJfxmwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfJfxmwhForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,t2.xmmkmc,");
		sql.append(" decode(t1.sfxf,'0','不限分','1','限分') sfxfmc,");
		sql.append(" decode(t1.sfbt,'0','非必修','1','必修') sfbtmc,");
		sql.append(" t3.num bms ");
		sql.append(" from xg_zjly_zhszf_jfxmb t1 left join xg_zjly_zhszf_mkxmb t2 on t1.xmmkdm = t2.xmmkdm");
		sql.append(" left join (select count(1) num,jfxmdm from xg_zjly_zhszf_xmsqb group by jfxmdm) t3 on t1.jfxmdm = t3.jfxmdm ");
		sql.append(" ) t where 1 = 1 ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(ZhfJfxmwhForm.class);
		this.setKey("jfxmdm");
		this.setTableName("xg_zjly_zhszf_jfxmb");
	}
	
	/** 
	 * @描述:统计计分项目名称(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 下午05:18:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int count(ZhfJfxmwhForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjly_zhszf_jfxmb where jfxmmc = ? ");
		if(null != t.getJfxmmc() && !"".equals(t.getJfxmmc()) && "" != t.getJfxmmc()){
			sql.append(" and jfxmdm <> ? ");
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getJfxmmc(),t.getJfxmdm()}, "num"));
		}else{
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getJfxmmc()}, "num"));
		}
	}
	
	/** 
	 * @描述:项目授权(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午03:27:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean jfxmSq(String[]bmdms,String[]jfxms) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zjly_zhszf_xmsqb ");
		for(int i = 0;i<jfxms.length;i++){
			for(int j=0;j<bmdms.length;j++){
				if(i==0){					
					if(j>0){
						sql.append(" union all ");
					}
					sql.append(" select '"+jfxms[i]+"','"+bmdms[j]+"' from dual");
				}else{
					sql.append(" union all select '"+jfxms[i]+"','"+bmdms[j]+"' from dual");
				}
			}			
		}
		return dao.runUpdate(sql.toString(), new String[]{});
		
	}
	
	/** 
	 * @描述:删除项目授权(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午03:41:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deljfxmSq(String[]bmdms,String[]jfxms) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjly_zhszf_xmsqb where jfxmdm in (");
		if(jfxms.length==1){
			sql.append("'"+jfxms[0]+"')");
		}else{			
			for(int i = 0;i<jfxms.length;i++){
				if(i==jfxms.length-1){
					sql.append("'"+jfxms[i]+"'");
				}else{
					sql.append("'"+jfxms[i]+"',");
				}
			}
			sql.append(")");
		}
		sql.append(" and bmdm in (");
		if(bmdms.length==1){
			sql.append("'"+bmdms[0]+"')");
		}else{			
			for(int i = 0;i<bmdms.length;i++){
				if(i==bmdms.length-1){
					sql.append("'"+bmdms[i]+"'");
				}else{
					sql.append("'"+bmdms[i]+"',");
				}
			}
			sql.append(")");
		}
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/** 
	 * @描述:获取部门列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午04:04:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cxzd
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListForSq(ZhfJfxmwhForm t) throws Exception{
//		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
//		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.bmdm,a.bmmc from ZXBZ_XXBMDM a ");
		if(("wsq").equals(t.getCxzd())){
			sql.append(" where not exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm and b.jfxmdm='"+t.getJfxmdm()+"')) ");
		}else if(("ysq").equals(t.getCxzd())){
			sql.append(" where exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm and b.jfxmdm='"+t.getJfxmdm()+"')) ");
		}
		sql.append(" where 1= 1 ");
		if(null != t.getCxmc() && !"".equals(t.getCxmc())){
			sql.append(" and bmmc like '%'||?||'%'" );
			return getPageList(t, sql.toString(), new String[]{t.getCxmc()});
		}else{
			return getPageList(t, sql.toString(), new String[]{});
		}
		
		
	}
	
	/** 
	 * @描述:获取部门列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午04:04:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cxzd
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmList(String cxzd){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.bmdm,a.bmmc from ZXBZ_XXBMDM a ");
		if(cxzd.equals("wsq")){
			sql.append(" where not exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm)) ");
		}else if(cxzd.equals("ysq")){
			sql.append(" where exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm)) ");
		}
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/** 
	 * @描述:保存兼得设置(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-29 上午09:15:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean jdsz(List<HashMap<String, String>> list) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zjly_zhszf_jdszb (jfxmdm,jfxmmc)");
		for(int i = 0;i<list.size();i++){
			if(i>0){
				sql.append(" union all ");
			}
			sql.append(" select '"+ list.get(i).get("jfxmdm")+"','"+ list.get(i).get("jfxmmc") +"' from dual");
		}
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/** 
	 * @描述:根据计分项目名称得到计分项目列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-29 上午09:22:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jfxmmcs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmList(String[] jfxmmcs){
		StringBuilder sql = new StringBuilder();
		sql.append(" select jfxmdm,jfxmmc from xg_zjly_zhszf_jfxmb where jfxmmc in (");
		for(int i = 0;i<jfxmmcs.length;i++){
			if(i==jfxmmcs.length-1){
				sql.append("'"+jfxmmcs[i]+"'");
			}else{
				sql.append("'"+jfxmmcs[i]+"',");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-1-16 下午03:06:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getnewjfxmdm() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select  max(to_number(jfxmdm))+1 jfxmdm from xg_zjly_zhszf_jfxmb  ");
		return dao.getOneRs(sb.toString(), new String[] {}, "jfxmdm");
	}
	
}
