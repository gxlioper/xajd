/**
 * @部门:学工产品(1)部
 * @日期：2017-6-22 上午09:42:09 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-6-22 上午09:41:52 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcwhDao extends SuperDAOImpl<ZcwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcwhForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select t1.id,t1.xn,t1.xydm,t1.tjr,t2.xm tjrxm,t1.tjsj,nvl(t1.tjzt, '0') tjzt,");
		sql.append("decode(t1.tjzt,'0','未提交','1','已提交','2','取消提交','', '未提交') tjztmc,");
		sql.append("t3.xymc,t3.xyrs,t4.xmdm,t4.xmmc ");
		sql.append("from xg_zjdx_pjpy_fstjjlb t1 left join yhb t2 on t1.tjr = t2.yhm ");
		sql.append("left join (select xydm, xymc, count(*) xyrs from (select a.xh, a.bjdm, b.xydm, b.xymc from xg_zjdx_pjpy_cpmdb a left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		sql.append("where a.xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1)) group by xydm, xymc) t3 on t1.xydm = t3.xydm ");
		sql.append("left join view_xg_zjdx_pjpy_cssz t4 on t4.xmdm = t1.xmdm ");
		sql.append("where exists (select 1 from xg_zjdx_pjpy_csszb t5 where t1.xn = t5.xn)");
		sql.append(")t where 1 = 1 and xyrs is not null");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputValue);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(ZcwhForm.class);
		super.setTableName("xg_zjdx_pjpy_zcfsb");
		super.setKey("id");
	}
	
	/**
	 * @描述: 查看是否还有未提交记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-23 上午11:39:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String isSubmitInfo(ZcwhForm t, User user) throws Exception {
		/*生成高级查询相关条件、条件值 */
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) count from ( ");
		sql.append("select t1.* from xg_zjdx_pjpy_fstjjlb t1 ");
		sql.append("where xn = (select xn from xg_zjdx_pjpy_csszb) and t1.tjzt = '0' ) where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), inputValue, "count");
	}
	
	/**
	 * @描述: 获得等级List
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-23 上午11:39:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDj() {
		StringBuilder sb = new StringBuilder();
		sb.append("select pjdjdm dm,pjdjmc mc,pjxmmc xmmc ");
		sb.append("from  xg_pjpy_new_pjdj ");
		sb.append("order by pjdjdm desc");
		return dao.getListNotOut(sb.toString(), new String[]{});
	}
	
	/**
	 * @描述: 获得综测分项目(顶级项目不取)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 上午10:49:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcxmList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.xmdm,a.xn,a.xmmc,a.fjdm,px,");
		sql.append("case when xmlx = '0' then result  end result,");
//		sql.append(" when xmlx = '1' then zxfz || '-' || zdfz");
		sql.append(" zxfz,zdfz,");
		sql.append("decode(a.xmlx, '0', '等级', '1', '分值', a.xmlx) xmlx ");
		sql.append("from xg_zjdx_pjpy_zcxmb a ");
		sql.append("left join (select xmdm, max(result) result from (select xmdm,mc,");
		sql.append("wmsys.wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append("from xg_zjdx_pjpy_zcxmxxb t) s group by xmdm) b on a.xmdm = b.xmdm ");
		sql.append("START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px)) t1 ");
		sql.append("where not exists (select 1 from xg_zjdx_pjpy_zcxmb t2 where t1.xmdm = t2.fjdm) ");
		sql.append("and xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) order by fjdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 参评名单列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-10 下午03:19:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param zcxmList
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcwhList(ZcwhForm t,List<HashMap<String, String>> zcxmList, User user,String zcxmdmForTop)
		throws Exception{
		StringBuilder sql = new StringBuilder();
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] ids = t.getId().split(",");
		sql.append("select * from ( ");
		sql.append("select id,xh,xm,tjzt, decode(tjzt,'1','已提交','未提交') tjztmc,bjmc,xydm ");
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}
		
		sql.append(" from ( select t1.id,t1.xh,t1.xm,nvl(t1.tjzt,0) tjzt,t1.bjmc,t1.xydm ");
		
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",case when t2.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then fs else '' end fs").append(i);
		}
		sql.append(" from (select t1.id,t1.xn,t1.xh,t3.xm,t1.tjzt,t2.* from xg_zjdx_pjpy_cpmdb t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  ");
		sql.append("left join view_xsbfxx t3 on t1.xh = t3.xh ");
		sql.append("left join xg_zjdx_pjpy_fstjjlb t4 on t2.xydm = t4.xydm ");
		sql.append("where t4.xn =(select xn from xg_zjdx_pjpy_csszb) and t4.xmdm = ? ");
		// 综测分只能录入未提交
		if (StringUtils.isNotNull(t.getEditType())) {
			sql.append(" and t4.tjzt <> '1' and t4.xydm is not null ");
		}
		sql.append(" ) t1 left join xg_zjdx_pjpy_zcfsb t2 on t1.xh = t2.xh and t1.xn = t2.xn where 1 = 1 ");
		if (!StringUtils.isNull(t.getId()) && null != ids && 0 != ids.length) {
			sql.append("and t1.xydm in (select xydm from xg_zjdx_pjpy_fstjjlb where id in ('");
			for (int i = 0; i < ids.length; i++) {
				if (0 == i) {
					sql.append(ids[i]);
				} else {
					sql.append("','" + ids[i]);
				}
			}
			sql.append("')) ");
		}
		sql.append("and t1.xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1))");
		sql.append(" group by id,xh,xm,tjzt,bjmc,xydm ");
		
		sql.append(") t2 where 1= 1 ");
		if (!StringUtil.isNull(t.getXh())) {
			sql.append(" and (t2.xh like '%'||'" + t.getXh() + "'||'%' or t2.xm like '%'||'" + t.getXh() + "'||'%') ");
		}
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{zcxmdmForTop},inputV));
	}
	
	/**
	 * @描述: 查询学生是否有某个项目分数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 下午04:03:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFsid (ZcwhForm t){
		String sql = "select id,nvl(fs,0)fs from xg_zjdx_pjpy_zcfsb where xmdm = ? and xh = ? ";
		return dao.getMapNotOut(sql, new String[]{t.getXmdm(),t.getXh()});
	}
	
	/**
	 * @描述: 取消参评人员调整记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-30 上午11:01:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertTzjl(String id, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" select xn,'on' xq,xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') tzsj,'"+user.getUserName()+"'yhm,");
		sql.append("  '从 '||(select b.bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm)|| ");
		sql.append(" ' 调整为 不参评' tzbz from xg_zjdx_pjpy_cpmdb a  where id in ( " );
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
	 * @描述: 综测名单删除后，更新学生在参评名单表的bjdm为空（取消资格）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-6-30 上午11:06:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCpmd(String id,User user) throws Exception {
			
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" update xg_zjdx_pjpy_cpmdb ");
		sql.append("set bjdm = '' ");
		sql.append(" where id in ( ");
		String[] ids = id.split(",");
		/*循环ID*/
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
	 * @描述: 查询增加参评学生列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 上午10:20:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAddCpxsList(ZcwhForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t3.xh,t3.xm,t4.bjdm cpbjdm,t4.bjmc cpbjmc,t4.nj cpnj,t4.xydm cpxydm,t4.xymc cpxymc,t4.zydm cpzydm,t4.zymc cpzymc,");
		sql.append("case when t3.cpbjdm is null then '否' else '是' end sfcp,");
		sql.append("case when t3.cpbjdm is null then '0' else '1' end tjzt from (");
		sql.append("select t1.xh,t1.xm,nvl(t2.bjdm, t1.bjdm) bjdm,t2.bjdm cpbjdm,t2.xn from xsxxb t1 left join xg_zjdx_pjpy_cpmdb t2 ");
		sql.append("on t1.xh = t2.xh) t3 ");
		sql.append("left join view_njxyzybj_all t4 on t4.bjdm = t3.bjdm ");
		//sql.append("left join xg_zjdx_pjpy_fstjjlb t5 on t3.xn = t5.xn and t5.xydm = t4.xydm ");
		sql.append("where t3.xn = (select xn from xg_zjdx_pjpy_csszb)) a ");
		sql.append("left join xg_xtwh_bmsxb b on a.cpxydm = b.bmdm where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" order by b.sx, a.cpxydm, a.cpzydm, a.cpbjdm");
		return super.getPageList(model, sql.toString(), StringUtils.joinStrArr(new String[]{},inputV));
	}
	
	/**
	 * @描述: 判断当前是否存在当前周期评奖人员库内
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午03:10:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHavePjry(String xh, String xn) {
		String sql = " select count(1) num from xg_zjdx_pjpy_cpmdb where xh = ? and xn = ? ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh,xn}, "num")) > 0;
	}
	
	/**
	 * @描述: 记录班级调整,从一个班级调整到另一个班级
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午03:17:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh,xn,tzhbjdm,user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertbjTzjl(String xh, String xn,String tzhbjdm, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" select xn,'on' xq,xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') tzsj,'"+user.getUserName()+"'yhm, ");
		sql.append(" '从 '||(select b.bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm)|| ");
		sql.append("  ' 调整为 '||(select bjmc from view_njxyzybj_all b where b.bjdm= ? ) tzbz from xg_zjdx_pjpy_cpmdb a " );
		sql.append(" where xh = ? and xn = ?");
		return dao.runUpdate(sql.toString(),new String[]{tzhbjdm,xh,xn});
	}
	
	/**
	 * @描述: 记录班级调整,从不参评到另一个班级
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午03:24:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * boolean 返回类型 
	 */
	public boolean insertbjTzjl1(String xn,String xh, String tzhbjdm, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" values(?,'on',?,to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'),'"+user.getUserName()+"', ");
		sql.append(" '从 不参评 调整为 '||(select bjmc from view_njxyzybj_all b where b.bjdm = ?))");
		return dao.runUpdate(sql.toString(),new String[]{xn,xh,tzhbjdm});
	}
	
	/**
	 * @描述: 参评学生从一个班级更新到另一个班级
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午03:36:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBjtzCpmd(String xh, String xn,String tzhbjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjdx_pjpy_cpmdb set bjdm = ? where xh in ( ? ) and xn = ?");
		return dao.runUpdate(sql.toString(), new String[]{tzhbjdm,xh,xn});
	}
	
	/**
	 * @描述: 增加参评人员（包括不在校生）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午03:53:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertBjtzCpmd(String xn,String xh, String tzhbjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zjdx_pjpy_cpmdb(xn,xq,xh,xm,bjdm) ");
		sql.append(" values( ?,'on',? ,(select xm from xsxxb where xh = ?),? ) ");
		return dao.runUpdate(sql.toString(),new String[]{xn,xh,xh,tzhbjdm});
	}
	
	/**
	 * @描述: 检测是否可提交综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午05:08:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param zdzcForm
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanSubmit(String values, ZcwhForm zcwhForm, User user) throws Exception{
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(zcwhForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(zcwhForm.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		String[] value = values.split(",");
		
		sql.append("select count(1) count ");
		sql.append("from (select a.id, a.tjzt, a.xh, b.* from (select * from (select a.xmdm,a.xn,a.xmmc,a.fjdm,px,case when xmlx = '0' then ");
		sql.append("result end result,zxfz,zdfz,decode(a.xmlx, '0', '等级', '1', '分值', a.xmlx) xmlx ");
		sql.append("from xg_zjdx_pjpy_zcxmb a left join (select xmdm, max(result) result from (select xmdm,mc,wmsys.wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append("from xg_zjdx_pjpy_zcxmxxb t) s group by xmdm) b ");
		sql.append("on a.xmdm = b.xmdm START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px)) t1 ");
		sql.append("where not exists (select 1 from xg_zjdx_pjpy_zcxmb t2 where t1.xmdm = t2.fjdm) and xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1)) d ");
		sql.append("left join xg_zjdx_pjpy_cpmdb a on a.xn = d.xn ");
		sql.append("left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		sql.append("left join xg_zjdx_pjpy_zcfsb c on a.xh = c.xh and a.xn = c.xn and d.xmdm = c.xmdm where c.fs is null ");
		sql.append(searchTjByUser);
		sql.append(" and d.xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1)) where 1 = 1 ");
		
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
	 * @描述: 提交参评人员状态
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-6 上午09:38:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param tjzt
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean tjCpxs(String values, String tjzt,ZcwhForm model, User user) throws Exception {
		
		/*用户权限*/
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuffer sql = new StringBuffer();
		
		sql.append("update xg_zjdx_pjpy_cpmdb a set tjzt = '"+tjzt+"',tjr='"+user.getUserName()+"',tjsj=to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') ");
		sql.append("where xn = (select xn from xg_zjdx_pjpy_csszb where rownum=1) ");
		sql.append("and exists (select 1 from (select * from (select nvl(a.tjzt,0) tjzt,a.id,a.xn,a.xh,b.* ");
		sql.append("from xg_zjdx_pjpy_cpmdb a left join view_njxyzybj_all b on a.bjdm = b.bjdm where a.xn = (select xn from xg_zjdx_pjpy_csszb where rownum=1) and b.bjdm is not null)where 1=1 ");

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
	 * @描述: 通过id取得学号列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-6 上午09:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws SQLException
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getXhArray(String values) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select xh from xg_zjdx_pjpy_cpmdb where 1 = 1");
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
	
	/**
	 * @描述: 取消综测分提交查询列表
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-7-7 下午04:27:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcfqxList(ZcwhForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select t1.id,t1.xn,t1.xydm,t1.tjr,t2.xm tjrxm,t1.tjsj,nvl(t1.tjzt, '0') tjzt,");
		sql.append("decode(t1.tjzt,'0','未提交','1','已提交','2','取消提交','', '未提交') tjztmc,");
		sql.append("t3.xymc,t3.xyrs ");
		sql.append("from xg_zjdx_pjpy_fstjjlb t1 left join yhb t2 on t1.tjr = t2.yhm ");
		sql.append("left join (select xydm, xymc, count(*) xyrs from (select a.xh, a.bjdm, b.xydm, b.xymc from xg_zjdx_pjpy_cpmdb a left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		sql.append("where a.xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1)) group by xydm, xymc) t3 on t1.xydm = t3.xydm ");
		sql.append("where exists (select 1 from xg_zjdx_pjpy_csszb t5 where t1.xn = t5.xn) and t1.tjzt = '1' ");
		sql.append(")t where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputValue);
	}
	
	/**
	 * @描述: 综测分导出
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-7-10 下午03:20:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param zcxmList
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCpxsExportList(ZcwhForm t,List<HashMap<String, String>> zcxmList, User user, String zcxmdmForTop)
		throws Exception{
		StringBuilder sql = new StringBuilder();
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] ids = t.getId().split(",");
		sql.append("select ");
		
		for (int i = 0; i < zcxmList.size(); i++) {
			if ("等级".equals(zcxmList.get(i).get("xmlx"))) {
				sql.append("(select pjdjmc from xg_pjpy_new_pjdj b where to_char(t2.fs" + i + ")=b.pjdjdm and b.pjxmmc='" + zcxmList.get(i).get("xmmc") + "') fs" + i + ",");
			}else{
				sql.append("fs" + i + ",");
			}
		}
		
		sql.append("t2.xh,t2.xm,t2.bjmc ");
		sql.append("from (select id,xh,xm,tjzt, decode(tjzt,'1','已提交','未提交') tjztmc,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
		
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}
		
		sql.append(" from ( select t1.id,t1.xh,t1.xm,nvl(t1.tjzt,0) tjzt,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj");
		
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",case when t2.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then fs else '' end fs").append(i);
		}
		sql.append(" from (select t1.id,t1.xn,t1.xh,t3.xm,t1.tjzt,t2.* from xg_zjdx_pjpy_cpmdb t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  ");
		sql.append("left join view_xsbfxx t3 on t1.xh = t3.xh ");
		sql.append("left join xg_zjdx_pjpy_fstjjlb t4 on t2.xydm = t4.xydm ");
		sql.append("where t4.xn =(select xn from xg_zjdx_pjpy_csszb) and t4.xmdm = ? ");
		// 综测分只能录入未提交
		if (StringUtils.isNotNull(t.getEditType())) {
			sql.append(" and t4.tjzt <> '1' and t4.xydm is not null ");
		}
		sql.append(" ) t1 left join xg_zjdx_pjpy_zcfsb t2 on t1.xh = t2.xh and t1.xn = t2.xn where 1 = 1 ");
		if (!StringUtils.isNull(t.getId()) && null != ids && 0 != ids.length) {
			sql.append("and t1.xydm in (select xydm from xg_zjdx_pjpy_fstjjlb where id in ('");
			for (int i = 0; i < ids.length; i++) {
				if (0 == i) {
					sql.append(ids[i]);
				} else {
					sql.append("','" + ids[i]);
				}
			}
			sql.append("')) ");
		}
		sql.append("and t1.xn = (select xn from xg_zjdx_pjpy_csszb where rownum=1))");
		sql.append(" group by id, xh,xm, tjzt,bjdm,bjmc,zydm,zymc,xydm,xymc,nj order by decode(tjzt, '1','1','0'),nj,xydm,zydm,bjdm desc");
		
		sql.append(") t2 where 1= 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{zcxmdmForTop},inputV));
	}
	
	/**
	 * @描述: 取项目等级名称
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-11 下午03:16:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getDjmc() throws SQLException {
		String sql = "select distinct pjxmmc from xg_pjpy_new_pjdj";
		return dao.getList(sql, new String[]{}, "pjxmmc");
	}
	
	/**
	 * 根据ID查询参学院学生（可多选）
	 */
	public String[] getStuById(ZcwhForm model,User user) throws Exception{
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t4", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		String[] ids = model.getId().split(",");
		
		sql.append("select xh ");
		sql.append("from (select t4.id, t1.xn, t1.xh,t1.tjzt, t2.* ");
		sql.append("from xg_zjdx_pjpy_cpmdb t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append("left join xg_zjdx_pjpy_fstjjlb t4 on t2.xydm = t4.xydm ");
		sql.append("where t1.xn = (select xn from xg_zjdx_pjpy_csszb) ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(") a ");
		sql.append("where a.xydm is not null ");
		if(!org.apache.commons.lang.StringUtils.isBlank(model.getId())){
			sql.append(" and a.id in ( ");
			for (int i = 0; i < ids.length; i++) {
				if(0==i){
					sql.append("'"+ids[i]+"'");
				}else{
					sql.append(",'"+ids[i]+"'");
				}
			}
			sql.append(") ");
			
		}
		return dao.getRs(sql.toString(),inputV, "xh");
	}
	
	/**
	 * @描述: 导入综测分（插入、更新）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-11 下午05:46:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean batchInsertZcfs(List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("MERGE INTO xg_zjdx_pjpy_zcfsb t1");
		sql.append(" USING (select ? xh, ? xn, ? xmdm, ? fs , ? lrr , to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') lrsj from dual) t2");
		sql.append(" ON (t1.xh = t2.xh and t1.xmdm = t2.xmdm)");
		sql.append(" WHEN MATCHED THEN");
		sql.append("   UPDATE");
		sql.append("     SET xn=t2.xn,fs=t2.fs,lrr=t2.lrr,");
		sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
		sql.append("   WHERE xh=? and xmdm=?");
		sql.append("WHEN NOT MATCHED THEN");
		sql.append("  INSERT (xh, xn, xmdm, fs, lrr, lrsj)");
		sql.append("  VALUES (t2.xh, t2.xn, t2.xmdm, t2.fs, t2.lrr, t2.lrsj)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * @描述: 根据id获取学院信息
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-7-12 下午03:52:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXyxxById(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct a.xydm, b.xymc, a.xn from xg_zjdx_pjpy_fstjjlb a ");
		sql.append("left join view_njxyzybj_all b on a.xydm = b.xydm where a.id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @描述: 将默认项目的分数插入分数记录表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-12 下午04:17:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertDefaultZcxmf(String xn,String id,String xydm,String zcxmdmForTop) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_zjdx_pjpy_zcfsb(xh,xn,fs,lrr,lrsj,xmdm) ");
		sql.append("select t1.xh,t1.xn,''fs,'auto'lrr,to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') lrsj,t2.xmdm ");
		sql.append("from xg_zjdx_pjpy_cpmdb t1 ");
		sql.append("left join (select * from view_xg_zjdx_pjpy_cssz where fjdm = ? and xn = ?) t2 ");
		sql.append("on t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t3 on t1.bjdm = t3.bjdm ");
		sql.append("left join (select xydm from xg_zjdx_pjpy_fstjjlb where id = ?) t4 on t3.xydm = t4.xydm ");
		sql.append("where t1.xn = ? ");
		sql.append("and t4.xydm = ? ");
		sql.append("and not exists (select 1 from xg_zjdx_pjpy_zcfsb t5 where t1.xh = t5.xh and t2.xmdm = t5.xmdm) ");
		return dao.runUpdate(sql.toString(), new String[]{zcxmdmForTop,xn,id,xn,xydm});
	}
	
	/**
	 * @描述: 查询是否有未录入综测分项目
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-12 下午05:35:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSfyWlr (ZcwhForm t){
		StringBuilder sql = new StringBuilder();
		sql.append("select case when (select count(*) xms from view_xg_zjdx_pjpy_cssz t1 ");
		sql.append("where t1.xn = ? and fjdm = ?) * ");
		sql.append("(select count(*) xss from xg_zjdx_pjpy_cpmdb t2 left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append("where t2.xn = ? and t3.xydm in (select xydm from xg_zjdx_pjpy_fstjjlb where id = ?)) <=  ");
		sql.append("(select count(*) fsjls from xg_zjdx_pjpy_zcfsb t7 where t7.xn = ? and exists ");
		sql.append("(select * from xg_zjdx_pjpy_cpmdb t5 left join view_njxyzybj_all t6 on t5.bjdm = t6.bjdm ");
		sql.append("where t6.xydm = (select xydm from xg_zjdx_pjpy_fstjjlb where id = ?) and t5.xn = ?)) then ");
		sql.append("'true' else 'false' end flg from dual ");
		return dao.getOneRs(sql.toString(), new String[]{t.getXn(),t.getZcxmdmForTop(),t.getXn(),t.getId(),t.getXn(),t.getId(),t.getXn()}, "flg");
	}
	
	/**
	 * @描述: 分值 为NULL的记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-12 下午05:44:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getNullZcf (ZcwhForm t){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)count from xg_zjdx_pjpy_zcfsb t where exists ");
		sql.append("(select 1 from xg_zjdx_pjpy_cpmdb t1 left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm where t1.xn = ? ");
		sql.append("and t2.xydm = (select xydm from xg_zjdx_pjpy_fstjjlb where id = ?) and t.xh = t1.xh and t.xn = t1.xn) ");
		sql.append("and fs is null and t.xmdm in (select xmdm from view_xg_zjdx_pjpy_cssz where xn = ? and fjdm = ?) ");
		sql.append("and xn = ? ");
		return dao.getOneRs(sql.toString(), new String[]{t.getXn(),t.getId(),t.getXn(),t.getZcxmdmForTop(),t.getXn()}, "count");
	}
	
	/**
	 * @描述: 提交班级综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 上午10:08:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param tjr
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitXyzcf(String id, String tjr) throws Exception{
		String sql = "update xg_zjdx_pjpy_fstjjlb set tjr = ? ,tjsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),tjzt='1' where id = ?";
		return dao.runUpdate(sql, new String[]{tjr,id});
	}
	
	/**
	 * @描述: 取消提交综测记录
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-3 上午11:09:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getCancelSubmit(String id) throws Exception{
		String sql = "update xg_zjdx_pjpy_fstjjlb set tjr = '', tjsj = '', tjzt = '0' where id = ?";
		return dao.runUpdate(sql,new String[]{id});
	}
	
	
	/**
	 * @描述: 计算排名
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 上午09:44:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xydm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean computeZcpm(String xn ,String xydm) throws Exception{
		return dao.runProcuder("{call pro_xg_zcwh_pmjs(?,?)}", 
				new String[]{xn,xydm});
	}
	
	/**
	 * @描述: 按学年查询参评学生列表 
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-13 上午10:02:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydmArr
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCpmdList(String[] xydmArr , String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh, t3.xm, t1.bjdm, t2.zydm, t2.xydm ");
		sql.append("from xg_zjdx_pjpy_cpmdb t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append("left join view_xsbfxx t3 on t1.xh = t3.xh");
		sql.append("where t1.xn = ? and (");
		for (int i = 0 , j = xydmArr.length ; i < j; i ++){
			sql.append("t2.xydm = ? ");
			if (i+1 != j){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), StringUtils.joinStrArr(new String[]{xn},xydmArr));
	}
	
	/**
	 * @描述: 取消提交综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 上午10:55:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param user
	 * @param t
	 * @param xn
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertTzjl (String id, User user, ZcwhForm t,String xn) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] params = new String[2];
		
		params[0] = xn;
		params[1] = t.getQxyy();
		
		sql.append(" insert into xg_zjdx_pjpy_qxtjjlb(xn,xydm,qxr,qxsj,qxyy,ytjr,ytjsj) ");
		sql.append(" select ?,xydm,'"+user.getUserName()+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') qxsj,?,tjr,tjsj from xg_zjdx_pjpy_fstjjlb where id in (");
		String[] ids = id.split(",");
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("'");
			sql.append(ids[i]);
			sql.append("'");
			
		}
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(),params );
	}
	
	/**
	 * @描述: 取消综测分提交更新xg_zjdx_pjpy_fstjjlb数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 上午11:00:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param user
	 * @param defaultQxtj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCpmd(String id, User user, String defaultQxtj) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" update xg_zjdx_pjpy_fstjjlb set tjzt = '"+defaultQxtj+"',tjr = '',tjsj='' where id in ( ");
		
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
	 * @描述: 综测维护查看
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 下午04:26:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param zcxmList
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcwhView(ZcwhForm t,List<HashMap<String, String>> zcxmList, User user,String zcxmdmForTop)
		throws Exception{
		StringBuilder sql = new StringBuilder();
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] ids = t.getId().split(",");
		sql.append("select ");
		
		for (int i = 0; i < zcxmList.size(); i++) {
			if ("等级".equals(zcxmList.get(i).get("xmlx"))) {
				sql.append("(select pjdjmc from xg_pjpy_new_pjdj b where to_char(t2.fs" + i + ")=b.pjdjdm and b.pjxmmc='" + zcxmList.get(i).get("xmmc") + "') fs" + i + ",");
			}else{
				sql.append("fs" + i + ",");
			}
		}
		
		sql.append("t2.xh,t2.xm,t2.bjmc ");
		sql.append("from (select id,xh,xm,tjzt, decode(tjzt,'1','已提交','未提交') tjztmc,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
		
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}
		
		sql.append(" from ( select t1.id,t1.xh,t1.xm,nvl(t1.tjzt,0) tjzt,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj");
		
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",case when t2.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then fs else '' end fs").append(i);
		}
		sql.append(" from (select t1.id,t1.xn,t1.xh,t3.xm,t1.tjzt,t2.* from xg_zjdx_pjpy_cpmdb t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  ");
		sql.append("left join view_xsbfxx t3 on t1.xh = t3.xh ");
		sql.append("left join xg_zjdx_pjpy_fstjjlb t4 on t2.xydm = t4.xydm ");
		sql.append("where t4.xn =(select xn from xg_zjdx_pjpy_csszb) and t4.xmdm = ? ");
		// 综测分只能录入未提交
		if (StringUtils.isNotNull(t.getEditType())) {
			sql.append(" and t4.tjzt <> '1' and t4.xydm is not null ");
		}
		sql.append(" ) t1 left join xg_zjdx_pjpy_zcfsb t2 on t1.xh = t2.xh and t1.xn = t2.xn where 1 = 1 ");
		if (!StringUtils.isNull(t.getId()) && null != ids && 0 != ids.length) {
			sql.append("and t1.xydm in (select xydm from xg_zjdx_pjpy_fstjjlb where id in ('");
			for (int i = 0; i < ids.length; i++) {
				if (0 == i) {
					sql.append(ids[i]);
				} else {
					sql.append("','" + ids[i]);
				}
			}
			sql.append("')) ");
		}
		sql.append("and t1.xn = (select xn from xg_zjdx_pjpy_csszb where rownum=1))");
		sql.append(" group by id, xh,xm, tjzt,bjdm,bjmc,zydm,zymc,xydm,xymc,nj order by decode(tjzt, '1','1','0'),nj,xydm,zydm,bjdm desc");
		
		sql.append(") t2 where 1= 1 ");
		if (!StringUtil.isNull(t.getXh())) {
			sql.append(" and (t2.xh like '%'||'" + t.getXh() + "'||'%' or t2.xm like '%'||'" + t.getXh() + "'||'%') ");
		}
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{zcxmdmForTop},inputV));
	}
	
	/**
	 * @描述: 同步综测分（纪实考评五个类别分）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-7 上午11:07:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param proName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getIntefaceData(String proName) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("call ");
		sql.append(proName);
		sql.append("()");
		return dao.runProcuder(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 查询学生在指定周期综测总分信息(浙大往年没有)
	 * @日期： 2018-1-3 下午02:46:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZczfByXh (String xh ,String xn){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		sql.append("select t1.*,t2.xmmc from xg_zjdx_pjpy_zcfsb t1 left join ( ");
		sql.append("select a.xmdm,a.xn,/*RPAD(' ', 2 * (LEVEL - 1)) || */a.xmmc xmmc,a.fjdm,px,");
		sql.append("case when xmlx = '0' then result when xmlx = '1' then zxfz || '-' || zdfz end result, ");
		sql.append("decode(a.xmlx, '0', '等级', '1', '分值', a.xmlx) xmlx from xg_zjdx_pjpy_zcxmb a ");
		sql.append("left join (select xmdm, max(result) result ");
		sql.append("from (select xmdm, mc, wmsys.wm_concat(mc) over(partition by xmdm order by px) result from xg_zjdx_pjpy_zcxmxxb t) s group by xmdm) b ");
		sql.append("on a.xmdm = b.xmdm START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px) )t2 on t1.xmdm = t2.xmdm ");
		sql.append("where exists (select 1 from (select a.xmdm,a.xn,/*RPAD(' ', 2 * (LEVEL - 1)) || */a.xmmc xmmc,a.fjdm,px, ");
		sql.append("case when xmlx = '0' then result when xmlx = '1' then zxfz || '-' || zdfz end result, decode(a.xmlx, '0', '等级', '1', '分值', a.xmlx) xmlx ");
		sql.append("from xg_zjdx_pjpy_zcxmb a left join (select xmdm, max(result) result ");
		sql.append("from (select xmdm, mc, wmsys.wm_concat(mc) over(partition by xmdm order by px) result from xg_zjdx_pjpy_zcxmxxb t) s ");
		sql.append("group by xmdm) b on a.xmdm = b.xmdm START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px)) t3 where t1.xmdm = t3.xmdm and t3.fjdm = 'top') ");
		sql.append("and t1.xh = ?");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and t1.xn = ? ");
			params.add(xn);
		}
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String [params.size()]));
	}
	
	/**
	 * @描述: 获得参数设置-综测项目列表，根据项目综测项目大类的xmdm,匹配fjdm为大类的xmdm的小项
	 * @作者：MengWei[工号：1186]
	 * @日期：2018-6-27 上午11:57:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcxmdmForTop
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcxmListByFjdmisTop(String zcxmdmForTop){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.xmdm,a.xn,a.xmmc,a.fjdm,px,");
		sql.append("case when xmlx = '0' then result  end result,");
		sql.append(" zxfz,zdfz,");
		sql.append("decode(a.xmlx, '0', '等级', '1', '分值', a.xmlx) xmlx ");
		sql.append("from xg_zjdx_pjpy_zcxmb a ");
		sql.append("left join (select xmdm, max(result) result from (select xmdm,mc,");
		sql.append("wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append("from xg_zjdx_pjpy_zcxmxxb t) s group by xmdm) b on a.xmdm = b.xmdm ");
		sql.append("START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px)) t1 ");
		sql.append("where not exists (select 1 from xg_zjdx_pjpy_zcxmb t2 where t1.xmdm = t2.fjdm) ");
		sql.append("and xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) ");
		sql.append("and fjdm = ?");
		sql.append("order by fjdm ");
		return dao.getListNotOut(sql.toString(), new String[]{zcxmdmForTop});
	}
}
