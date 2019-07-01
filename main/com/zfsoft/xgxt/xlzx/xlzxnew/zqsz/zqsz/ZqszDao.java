package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZqszDao extends SuperDAOImpl<ZqszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZqszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/**
	 * 周报查询
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZqszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t.*,a.xqmc,(select count(1) from ((select sbzbid");
		sql.append(" from XG_XLJK_XLWYGL_new_XSSBSQB) union all (select sbzbid from XG_XLJK_new_XLWYGL_XLSBJGB))");
		sql.append(" where sbzbid = t.zbid) sqcount from XG_XLJK_XLWYGL_new_ZBRCXXB t left join xqdzb a");
		sql.append(" on t.xq = a.xqdm ) where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" order by czsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(ZqszForm.class);
		this.setKey("zbid");
		this.setTableName("XG_XLJK_XLWYGL_new_ZBRCXXB");
	}
	
	/**
	 * 
	 * @描述: 查询月报周期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午01:48:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYbzqList(ZqszForm t, User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
//		sql.append(" select * from (");
//		sql.append(" select t.xydm,t.xymc,decode(nvl(t2.xn,0),'0','未上报','已上报') sbzt,t1.*,t3.xm txr");
//		sql.append(" from (select distinct xydm,xymc from view_njxyzybj) t");
//		sql.append(" left join xg_xljk_ybrcb t1  on 1 = 1  left join (");
//		sql.append(" select xn,xydm,txr from xg_xljk_xlwygl_ybsbb union");
//		sql.append(" select xn,xydm,txr from xg_xljk_xlwygl_ybsbjgb)t2");
//		sql.append(" left join yhb t3 on t2.txr = t3.yhm ");
//		sql.append(" on t.xydm = t2.xydm and t1.xn = t2.xn) where 1=1 ");
//		sql.append(searchTj);
//		sql.append(" order by czsj desc");
		sql.append(" select * from (");
		sql.append(" select t.xn,t.yf,t.ybid,count(t1.xydm) sbcnt from xg_xljk_ybrcb t");
		sql.append(" left join( select xn,yf,xydm from  xg_xljk_xlwygl_ybsbjgb");
		sql.append(" union select xn,yf,xydm from  xg_xljk_xlwygl_ybsbb)t1");
		sql.append(" on t.xn = t1.xn and t.yf = t1.yf");
		sql.append(" group by t.xn,t.yf,t.ybid)t where 1=1 ");
		sql.append(searchTj);
		sql.append(" ");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 验证周日常是否被使用过
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午01:58:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsZqNotUserd(String[] zbids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from (");
		sql.append(" select sbzbid from XG_XLJK_XLWYGL_new_XSSBSQB");
		sql.append(" union select sbzbid from XG_XLJK_new_XLWYGL_XLSBJGB) where sbzbid in (");
		for (int i = 0; i < zbids.length; i++) {
			sql.append("?");
			if(i != zbids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(), zbids, "cnt");
		return "0".equals(rs) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 验证周日程名称是否被使用过
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午02:25:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsZqMcNotUserd(String zbid,String zbzc,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from XG_XLJK_XLWYGL_New_ZBRCXXB where zbzc = ? and xn = ? and xq = ? ");
		paraList.add(zbzc);
		paraList.add(xn);
		paraList.add(xq);
		if(StringUtils.isNotNull(zbid)){
			sql.append(" and zbid != ?");
			paraList.add(zbid);
		}
		String rs = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"cnt");
		return "0".equals(rs) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 验证月周期是否被使用过
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午03:01:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsYzqNotUserd(String[] ybids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from (select xn,yf from xg_xljk_xlwygl_ybsbb union select xn,yf from xg_xljk_xlwygl_ybsbjgb) where xn || yf in(");
		sql.append(" select xn || yf from xg_xljk_ybrcb where ybid in(");
		for (int i = 0; i < ybids.length; i++) {
			sql.append("?");
			if(i != ybids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(),new String[]{},"cnt");
		return "0".equals(rs) ? true :false;
	}
	
	/**
	 * 
	 * @描述: 验证是否月周期名称可以使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午04:23:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param yf
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsYzqMcNotUsed(String xn,String yf,String ybid){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_xljk_ybrcb where xn = ? and yf = ? ");
		paraList.add(xn);
		paraList.add(yf);
		if(StringUtils.isNotNull(ybid)){
			sql.append(" and ybid != ? ");
			paraList.add(ybid);
		}
		String rs = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "cnt");
		return "0".equals(rs) ? true :false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存月周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 上午11:56:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveYzqsz(ZqszForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xljk_ybrcb(xn,yf) values(?,?)");
		return dao.runUpdate(sql.toString(),new String[]{t.getXn(),t.getYf()});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 修改周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午02:34:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYzqsz(ZqszForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xljk_ybrcb set xn = ?,yf = ? where ybid = ?");
		return dao.runUpdate(sql.toString(),new String[]{t.getXn(),t.getYf(),t.getYbid()});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取月周期Model
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午02:45:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ZqszForm 返回类型 
	 * @throws
	 */
	public ZqszForm getYzqModel(String ybid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xljk_ybrcb where ybid = ?");
		return getModel(sql.toString(), new String[]{ybid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除月周期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午04:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ybids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delYzqSz(String[] ybids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_ybrcb where ybid in(");
		for (int i = 0; i < ybids.length; i++) {
			sql.append("?");
			if(i != ybids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), ybids);
	}
	
	/**
	 * 
	 * @描述: 查询周期上报详细情况查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午05:50:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param sbbjlx
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZqsbxxqkCx(ZqszForm t, User user, String sbbjlx)throws Exception{
		StringBuilder sql = new StringBuilder();
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		List<String> params = new ArrayList<String>();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		sql.append(" select * from (select a.* from VIEW_NEW_DC_XSXX_JCSJWH_BJ a where ");
		if("ysb".equals(sbbjlx)){
			sql.append("exists");
		}else{
			sql.append("not exists");
		}
		sql.append("  (select 1");
		sql.append("  from (select bjdm from view_xsxxb b  where b.xh in");
		sql.append(" ((select xh from XG_XLJK_XLWYGL_new_XSSBSQB  where sbzbid = ? and shzt not in ('0', '3'))");
		sql.append(" union all (select xh from XG_XLJK_new_XLWYGL_XLSBJGB  where sbzbid = ?))) b");
		sql.append(" where b.bjdm = a.bjdm)");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		params.add(t.getZbid());
		params.add(t.getZbid());
		params.addAll(Arrays.asList(inputV));
		
		
		sql.append(" )");
		sql.append(" order by bjdm asc");
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 验证周次时间是否重合
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-24 下午03:40:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kssj
	 * @param jssj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsTimeNotRepeat(String kssj,String jssj,String zbid){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from XG_XLJK_XLWYGL_new_ZBRCXXB where zbksrq <= ? and zbjsrq >= ? ");
		paraList.add(jssj);
		paraList.add(kssj);
		if(StringUtils.isNotNull(zbid)){
			sql.append(" and zbid != ?");
			paraList.add(zbid);
		}
		String rs = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"cnt");
		return "0".equals(rs) ? true :false;
	}
}
