/**
 * @部门:学工产品事业部
 * @日期：2013-7-23 下午01:35:20 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import net.fckeditor.tool.Utils;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分数
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-23 下午01:35:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcfsDao extends SuperDAOImpl<ZcfsModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZcfsModel.class);
		super.setTableName("xg_zhcp_zcfsb");
		super.setKey("id");

	}

	public List<HashMap<String, String>> getPageList(ZcfsModel t, List<HashMap<String, String>> zcxmList, User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		String[] ids = t.getId().split(",");

		sql.append("select id,xh,xm,bjdm,");
		sql.append("'' tjrxm, '0' tkzt,"); // 这里纯粹为了高级查询条件的字段存在

		for (int i = 0; i < zcxmList.size(); i++) {
			if ("4".equals(zcxmList.get(i).get("xmlx")) && !"edit".equals(t.getEditType())) {
				sql.append("(select pjdjmc from xg_pjpy_new_pjdj b where to_char(a.fs" + i + ")=b.pjdjdm and b.pjxmmc='" + zcxmList.get(i).get("xmmc") + "') fs" + i + ",");
			} else {
				sql.append("fs" + i + ",");
			}
		}

		sql.append("(select bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm) bjmc from (");
		sql.append("select id,xh,xm,bjdm");

		for (int i = 0; i < zcxmList.size(); i++) {
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}

		sql.append(" from (");
		sql.append("select t1.id,t1.xh,t1.xm,t1.bjdm ");
		for (int i = 0; i < zcxmList.size(); i++) {
			sql.append(",case when t2.xmdm='").append(zcxmList.get(i).get("xmdm")).append("' then fs else '' end fs").append(i);
		}
		sql.append(" from ( select t1.id,t1.xn,t1.xq,t1.xh,t1.xm,t2.*,t4.tjzt from xg_pjpy_new_cpmdb t1 left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" left join  xg_zhcp_fstjjlb t4 on t1.bjdm = t4.bjdm where t4.xn||t4.xq = (select xn||xq from xg_pjpy_new_csszb)");
		// 综测分只能录入未提交
		if (StringUtils.isNotNull(t.getEditType())) {
			sql.append(" and t4.tjzt <> '1' and t4.bjdm is not null ");
		}
		sql.append(" ) t1 left join xg_zhcp_zcfsb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq where 1=1 ");
		if (!StringUtils.isNull(t.getId()) && null != ids && 0 != ids.length) {
			sql.append("and t1.bjdm in (select bjdm from xg_zhcp_fstjjlb where id in ('");
			for (int i = 0; i < ids.length; i++) {
				if (0 == i) {
					sql.append(ids[i]);
				} else {
					sql.append("','" + ids[i]);
				}
			}
			sql.append("')) ");
		}
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append("and t1.xn||t1.xq in (select xn||xq from xg_pjpy_new_csszb where rownum=1)");

		if (!StringUtil.isNull(t.getXh())) {
			sql.append(" and (t1.xh like '%'||'" + t.getXh() + "'||'%' or t1.xm like '%'||'" + t.getXh() + "'||'%') ");
		}

		sql.append(") group by id,xh,xm,bjdm order by bjdm,xh) a  ");
		if(StringUtils.isNotNull(t.getCxlx())){
			return dao.getListNotOut(sql.toString(), inputV);
		}else{
			return super.getPageList(t, sql.toString(), inputV);
		}
		
	}
	
	public List<HashMap<String, String>> getPageListOfYf(ZcfsModel t, List<HashMap<String, String>> zcxmList, User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String zcfsTable = "xg_zhcp_zcfsb";
		if ( !"all".equals(t.getZcyf())) {
			zcfsTable = "(select * from xg_zhcp_zcfsb_yf  where yf='" + t.getZcyf() + "')";
		}
		StringBuilder sql = new StringBuilder();
		String[] ids = t.getId().split(",");

		sql.append("select id,xh,xm,bjdm,");
		if (!"all".equals(t.getZcyf())) {
			String zcyf = t.getZcyf().substring(0, 4) + "年" + t.getZcyf().substring(4, 6) + "月";
			sql.append("'" + zcyf + "' zcyf,");
		}
		sql.append("'' tjrxm, '0' tkzt,"); // 这里纯粹为了高级查询条件的字段存在

		for (int i = 0; i < zcxmList.size(); i++) {
			if ("4".equals(zcxmList.get(i).get("xmlx")) && !"edit".equals(t.getEditType())) {
				sql.append("(select pjdjmc from xg_pjpy_new_pjdj b where to_char(a.fs" + i + ")=b.pjdjdm and b.pjxmmc='" + zcxmList.get(i).get("xmmc") + "') fs" + i + ",");
			} else {
				sql.append("fs" + i + ",");
			}
		}

		sql.append("(select bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm) bjmc from (");
		sql.append("select id,xh,xm,bjdm");

		for (int i = 0; i < zcxmList.size(); i++) {
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}

		sql.append(" from (");
		sql.append("select t1.id,t1.xh,t1.xm,t1.bjdm ");

		for (int i = 0; i < zcxmList.size(); i++) {
			sql.append(",case when t2.xmdm='").append(zcxmList.get(i).get("xmdm")).append("' then fs else '' end fs").append(i);
		}
		sql.append(" from ( select t1.id,t1.xn,t1.xq,t1.xh,t1.xm,t2.*,t4.tjzt from xg_pjpy_new_cpmdb t1 left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" left join  xg_zhcp_fstjjlb t4 on t1.bjdm = t4.bjdm where t4.xn||t4.xq = (select xn||xq from xg_pjpy_new_csszb)");
		// 综测分只能录入未提交 and t4.tjzt <> '1'
		if (StringUtils.isNotNull(t.getEditType())) {
			sql.append("  and t4.bjdm is not null ");
		}
		sql.append(" ) t1 left join " + zcfsTable + " t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq where 1=1 ");
		if (!StringUtils.isNull(t.getId()) && null != ids && 0 != ids.length) {
			if(("aytjzcf").equalsIgnoreCase(t.getDoType()) ){
				sql.append("and t1.bjdm in (select bjdm from xg_zhcp_fstjjlb_yf where id in ('");

			}else{
				sql.append("and t1.bjdm in (select bjdm from xg_zhcp_fstjjlb where id in ('");

			}
			for (int i = 0; i < ids.length; i++) {
				if (0 == i) {
					sql.append(ids[i]);
				} else {
					sql.append("','" + ids[i]);
				}
			}
			sql.append("')) ");
		}
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append("and t1.xn||t1.xq in (select xn||xq from xg_pjpy_new_csszb where rownum=1)");

		if (!StringUtil.isNull(t.getXh())) {
			sql.append(" and (t1.xh like '%'||'" + t.getXh() + "'||'%' or t1.xm like '%'||'" + t.getXh() + "'||'%') ");
		}

		sql.append(") group by id,xh,xm,bjdm");
		sql.append(" order by xh) a  ");

		return super.getPageList(t, sql.toString(), inputV);
	}


	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZcfsModel t, User user)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 综测班级提交查询
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午10:16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getZcbjList(ZcfsModel t, User user)
		throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select distinct t1.id,t1.bjdm,t3.bjdm zybj,t1.tjr,t2.xm tjrxm,t1.tjsj,nvl(tjzt,'0') tjzt,");
		sql.append("decode(t1.tjzt,'0','未提交','1','已提交','2','取消提交','','未提交') tjztmc,");
		sql.append("t3.nj,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjmc,t4.bjrs,t7.sydm,t7.symc ");
		sql.append("from xg_zhcp_fstjjlb t1 left join yhb t2 on t1.tjr=t2.yhm ");
		sql.append("left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm ");
		sql.append("left join XG_XTWH_SYBJGLB t6 on t1.bjdm = t6.bjdm ");
		sql.append("left join XG_XTWH_SYDMB t7 on t6.sydm = t7.sydm ");
		sql.append(" left join VIEW_XSJBXX t9 on t9.BJDM=t1.BJDM ");
		sql.append("left join (select bjdm,count(1) bjrs from xg_pjpy_new_cpmdb ");
		sql.append(" where xn||xq in (select xn||xq from xg_pjpy_new_csszb where rownum=1) group by bjdm) t4 on t1.bjdm=t4.bjdm ");
		sql.append("where  exists (select 1 from xg_pjpy_new_csszb t5 where t1.xn||t1.xq=t5.xn||t5.xq) ");
		sql.append(" and t9.pycc=3 ) t1 where 1=1 and bjrs is not null ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}
	
	
	/**
	 * 
	 * @描述: 查询已提交综测分的班级数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午01:45:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型
	 */
	public String getYtjZcfNum(String xn , String xq){
		
		String sql = "select count(1) num from xg_zhcp_fstjjlb where xn=? and xq=? and tjzt='1'";
		
		return dao.getOneRs(sql, new String[]{xn,xq}, "num");
	}
	
	
	/**
	 * 
	 * @描述: 删除总测分数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午02:16:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型
	 * @throws Exception 
	 */
	public boolean delZcfs(String xn,String xq ,String xmdm) throws Exception{
		
		String sql = "delete from xg_zhcp_zcfsb t1 where xn=? and xq=? and (xmdm=? or xmdm in (select fjdm from xg_zhcp_zcxmb where xmdm=?))";
		
		return dao.runUpdate(sql, new String[]{xn,xq,xmdm,xmdm});
	}
	
	/**
	 * @描述：添加已提交数据到临时表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月16日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean bakTjjl(String xn,String xq,User user) throws Exception{
		dao.runUpdate("delete from XG_ZHCP_FSTJLSB ", new String[]{});
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_ZHCP_FSTJLSB(id,xn,xq,bjdm,cpzdm,tjr,tjsj,tjzt) select id,xn,xq,bjdm,cpzdm,tjr,tjsj,tjzt ");
		sql.append(" from xg_zhcp_fstjjlb t1 where t1.xn=? and t1.xq=? ");
		if ("xy".equals(user.getUserType())){
			sql.append(" and exists (select 1 from view_njxyzybj t2 where t1.bjdm=t2.bjdm and t2.xydm='")
			   .append(user.getUserDep())
			   .append("')");
		}
		sql.append(" and t1.tjzt='1' ");
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}
	
	/**
	 * @描述：更新本周期已提交分数记录为取消提交
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月16日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updTjjl() throws Exception{
		String sql1=" update  XG_ZHCP_FSTJJLB set tjzt='2' where bjdm||cpzdm||xn||xq in (select bjdm||cpzdm||xn||xq from XG_ZHCP_FSTJLSB t ) ";
		String sql2=" insert into xg_zhcp_qxtjjlb(xn,xq,bjdm,qxr,qxsj,qxyy,ytjr,ytjsj) select xn,xq,bjdm,'zf01',to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'初始化',tjr,tjsj from XG_ZHCP_FSTJLSB ";
		String sql3=" delete from XG_ZHCP_FSTJLSB ";
		String[] sqlArr=new String[]{sql1,sql2,sql3};
		return dao.runBatch(sqlArr)[0]!=-1;
	}
	
	/**
	 * 
	 * @描述: 删除指定周期的分数提交记录
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午04:17:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型
	 * @throws Exception 
	 */
	public boolean delTjjl(String xn,String xq,User user) throws Exception{
		StringBuilder sql = new StringBuilder("delete from xg_zhcp_fstjjlb t1 where t1.xn=? and t1.xq=? ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and exists (select 1 from view_njxyzybj t2 where t1.bjdm=t2.bjdm and t2.xydm='")
			   .append(user.getUserDep())
			   .append("')");
		}
		
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcfsModel t)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 查询学生是否有某的项目分数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午03:03:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型
	 */
	public HashMap<String, String> getFsid(ZcfsModel t){
		
		String sql = "select id,nvl(fs,0)fs from xg_zhcp_zcfsb where xmdm=? and xh=?";
		
		return dao.getMapNotOut(sql, new String[]{t.getXmdm(),t.getXh()});
	}
	public HashMap<String, String> getFsSum(ZcfsModel t){
		
		String sql = "select sum(fs), from xg_zhcp_zcfsb_yf where xmdm=? and xh=? and xn=? and xq=?";
		
		return dao.getMapNotOut(sql, new String[]{t.getXmdm(),t.getXh()});
	}
	
	
	public HashMap<String, String> getFsOfYf(ZcfsModel t){
		String sql = "select guid,nvl(fs,0)fs from xg_zhcp_zcfsb_yf where xmdm=? and xh=? and yf=?";
		return dao.getMapNotOut(sql,new String[]{t.getXmdm(),t.getXh(),t.getZcyf()});
	}
	
	
	/**
	 * 
	 * @描述: 分值 为NULL的记录
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午05:22:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型
	 */
	public String getNullZcf(ZcfsModel t){
	
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count from xg_zhcp_zcfsb t1 where exists (");
		sql.append(" select 1 from Xg_Pjpy_New_Cpmdb t2 where t2.xn=? and t2.xq=? ");
		sql.append(" and t2.bjdm=(select bjdm from xg_zhcp_fstjjlb where id=?)");
		sql.append(" and t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" ) and fs is null");
		sql.append(" and t1.xmdm in (select xmdm from xg_zhcp_zcxmb where xn = ? and xq = ? and fjdm <> 'N') ");
		
		return dao.getOneRs(sql.toString(), new String[]{t.getXn(),t.getXq(),t.getId(),t.getXn(),t.getXq()}, "count");
	}
	
	
	/**
	 * 
	 * @描述: 查询是否有未录入综测分项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午02:24:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型
	 */
	public String getSfyWlr(ZcfsModel t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select case when (");
		sql.append(" select count(1) xms from xg_zhcp_zcxmb t1 ");
		sql.append(" where xn=? and xq=? and not exists (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm = t2.fjdm)");
		sql.append(" ) * (select count(1) xss from xg_pjpy_new_cpmdb t4 where t4.xn=? and t4.xq=? and ");
		sql.append(" t4.bjdm = (select bjdm from xg_zhcp_fstjjlb where id = ?)");
		sql.append(" ) <=( select count(1) count from xg_zhcp_zcfsb t1 where xn=? and xq=? and  exists (");
		sql.append(" select 1 from xg_pjpy_new_cpmdb t2 where t1.xh=t2.xh and t1.xn = t2.xn and t1.xq = t2.xq ");
		sql.append("  and t2.bjdm=(select bjdm from xg_zhcp_fstjjlb where id =?)");
		sql.append(" ) and not exists (select 1 from xg_zhcp_zcxmb t3 where t1.xmdm = t3.xmdm and t3.fjdm='N') ");
		sql.append(" ) then 'true' else 'false' end flg from dual");
		
		return dao.getOneRs(sql.toString(), new String[]{t.getXn(),t.getXq(),t.getXn(),t.getXq(),t.getId(),t.getXn(),t.getXq(),t.getId()}, "flg");
	}
	
	
	/**
	 * 
	 * @描述: 提交班级综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午06:50:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param tjr
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean submitBjzcf(String id, String tjr) throws Exception{
		
		String sql = "update xg_zhcp_fstjjlb set tjr=? ,tjsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),tjzt='1' where id=?";
		
		return dao.runUpdate(sql, new String[]{tjr,id});
	}


	/**
	 * 
	 * @描述: 将默认项目的分数插入分数记录表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:41:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean insertDefaultZcxmf(String xn , String xq ,String bjdm) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,t2.mrfs,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 ");
		sql.append(" where t1.xn=? and t1.xq=? and t2.xmlx='3' ");
		sql.append(" and t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=?");
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh=t3.xh and t2.xmdm=t3.xmdm)");
		
		return dao.runUpdate(sql.toString(), new String[]{xn,xq,xn,xq,bjdm});
	}
	
	
	/**
	 * 
	 * @描述: 将默认项目的分数插入分数记录表(支持增加批量操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:41:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean insertDefaultZcxmf(String xn,String xq ,List<HashMap<String, String>> bjxxMap,ZcfsModel model, User user) 
		throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t3", "xydm", "bjdm");
		
		params.add(xn);
		params.add(xq);
		params.add(xn);
		params.add(xq);
		
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,t2.mrfs,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_pjpy_new_cpmdb t1 left join xg_zhcp_zcxmb t2 on t1.xn=t2.xn and t1.xq=t2.xq ");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t2.xmlx='3' ");
		if(getIsNotblank(bjxxMap,model)){
			sql.append(" and t1.bjdm in ( ");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(")");
		}
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh=t3.xh and t2.xmdm=t3.xmdm)");
		sql.append(searchTjByUser);
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	
	/**
	 * 
	 * @描述: 需要接口同步分数的项目将空分值记录插入
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:44:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean insertTbxmf(String xn,String xq ,String bjdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 ");
		sql.append(" where t1.xn=? and t1.xq=? and t2.jktb is not null ");
		sql.append(" and t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=?");
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh=t3.xh and t2.xmdm=t3.xmdm)");
		return dao.runUpdate(sql.toString(), new String[]{xn,xq,xn,xq,bjdm});
	};
	
	
	/**
	 * 
	 * @描述: 需要接口同步分数的项目将空分值记录插入(为增加批量操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:44:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean insertTbxmf(String xn,String xq ,List<HashMap<String, String>> bjxxMap, ZcfsModel model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		params.add(xn);
		params.add(xq);
		params.add(xn);
		params.add(xq);
		
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 ");
		sql.append(" where t1.xn=? and t1.xq=? and t2.jktb is not null ");
		sql.append(" and t1.xn=t2.xn and t1.xq=t2.xq ");
		if(getIsNotblank(bjxxMap,model)){
			sql.append(" and t1.bjdm in ( ");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(")");
		}
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh=t3.xh and t2.xmdm=t3.xmdm)");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	};
	
	
	/**
	 * 
	 * @描述: 按年级插入默认分
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 下午03:26:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertMrfsByNj(String xn,String xq ,String bjdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,'0','auto',");
		sql.append(" to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') lrsj ");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 where exists ( ");
		sql.append(" select 1 from ( ");
		sql.append(" select t1.xmdm,t1.bmdm,t2.bjdm from xg_zhcp_zcblb t1 ");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.nj");
		sql.append(" where t1.qzbl='0'");
		sql.append(" ) t3 where t1.bjdm = t3.bjdm and t2.xmdm = t3.xmdm");
		sql.append(" ) and t1.bjdm=? and t1.xn=? ");
		sql.append(" and t1.xq=? and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh = t3.xh and t2.xmdm = t3.xmdm)");
		return dao.runUpdate(sql.toString(), new String[]{xn,xq,bjdm,xn,xq});
	};
	
	
	/**
	 * 
	 * @描述: 按年级插入默认分(为增加批量操作
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 下午03:26:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertMrfsByNj(String xn,String xq ,List<HashMap<String, String>> bjxxMap,ZcfsModel model) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		params.add(xn);
		params.add(xq);
		params.add(xn);
		params.add(xq);
		
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,'0','auto',");
		sql.append(" to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') lrsj ");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 where exists ( ");
		sql.append(" select 1 from ( ");
		sql.append(" select t1.xmdm,t1.bmdm,t2.bjdm from xg_zhcp_zcblb t1 ");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.nj");
		sql.append(" where t1.qzbl='0'");
		sql.append(" ) t3 where t1.bjdm = t3.bjdm and t2.xmdm = t3.xmdm");
		sql.append(" ) and t1.xn=? and t1.xq=? ");
		if(getIsNotblank(bjxxMap,model)){
			sql.append(" and t1.bjdm in ( ");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(")");
		}
		sql.append(" and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh = t3.xh and t2.xmdm = t3.xmdm)");
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	};
	
	
	/**
	 * 
	 * @描述: 按学院插入默认分
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 下午03:26:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertMrfsByXy(String xn,String xq ,String bjdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,'0','auto',");
		sql.append(" to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') lrsj ");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 where exists ( ");
		sql.append(" select 1 from ( ");
		sql.append(" select t1.xmdm,t1.bmdm,t2.bjdm from xg_zhcp_zcblb t1 ");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.xydm");
		sql.append(" where t1.qzbl='0'");
		sql.append(" ) t3 where t1.bjdm = t3.bjdm and t2.xmdm = t3.xmdm");
		sql.append(" ) and t1.bjdm=? and t1.xn=? ");
		sql.append(" and t1.xq=? and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh = t3.xh and t2.xmdm = t3.xmdm)");
		return dao.runUpdate(sql.toString(), new String[]{xn,xq,bjdm,xn,xq});
	};
	
	/**
	 * 
	 * @描述: 按学院插入默认分(可以多个班级
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 下午03:26:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertMrfsByXy(String xn,String xq ,List<HashMap<String, String>> bjxxMap, ZcfsModel model) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		params.add(xn);
		params.add(xq);
		params.add(xn);
		params.add(xq);
		
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,'0','auto',");
		sql.append(" to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') lrsj ");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 where exists ( ");
		sql.append(" select 1 from ( ");
		sql.append(" select t1.xmdm,t1.bmdm,t2.bjdm from xg_zhcp_zcblb t1 ");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.xydm");
		sql.append(" where t1.qzbl='0'");
		sql.append(" ) t3 where t1.bjdm = t3.bjdm and t2.xmdm = t3.xmdm");
		sql.append(" ) and t1.xn=? and t1.xq=? ");
		if(getIsNotblank(bjxxMap,model)){
			sql.append(" and t1.bjdm in ( ");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(")");
		}
		sql.append(" and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh = t3.xh and t2.xmdm = t3.xmdm)");
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	};
	
	
	/**
	 * 
	 * @描述: 按ID取班级信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public HashMap<String,String> getBjxxById(String id){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.bjdm,t2.bjmc,t1.xn,t1.xq from xg_zhcp_fstjjlb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where t1.id=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
	/**
	 * 
	 * @描述: 按ID取班级信息 ( 可以多个班级
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String,String>> getBjxxById(ZcfsModel model, User user) throws Exception {
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		
		String[] ids = model.getId().split(",");
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select t1.bjdm,t2.bjmc,t1.xn,t1.xq from xg_zhcp_fstjjlb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where ");
		sql.append("xn||xq = (select xn||xq from xg_pjpy_new_csszb where rownum = 1 ) and t1.bjdm is not null ");
		if(!Utils.isBlank(model.getId())){
			sql.append(" and t1.id in ( ");
			for (int i = 0; i < ids.length; i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(ids[i]);
			}
			sql.append(") ");
			
		}
		
		sql.append(searchTjByUser);
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	
	/**
	 * 
	 * @描述: 批量取班级信息 
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String,String>> getBjxxByIds(ZcfsModel model, User user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		String [] ids = model.getId().split(",");
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append("select t1.* from (");
		sql.append("select t1.bjdm,t1.bjmc,nvl(tjzt, '0') tjzt,(select xm from yhb t3 where t1.tjr=t3.yhm) tjrxm, ");
		sql.append(" t1.nj,t1.xydm,t1.xymc,t1.zydm,t1.zymc from ");
		sql.append("(select t1.id,t1.xn,t1.xq,t1.cpzdm,t1.tjr,t1.tjsj,t1.tjzt,t2.* from xg_zhcp_fstjjlb t1 left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm ) t1 ");
		sql.append("where t1.xn='"+model.getXn()+"' and t1.xq= '"+model.getXq()+"' ");
		if(!StringUtil.isNull(model.getId())&&null!=ids&&0!=ids.length){
			sql.append(" and t1.id in ( '");
			 for (int i=0;  i<ids.length; i++) {
				if(0==i){
					sql.append(ids[i]);
				}else{
					sql.append("','"+ids[i]);
				}
			}
			sql.append("')");
		}
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return dao.getListNotOut(sql.toString(), inputV);
		
	}
	
	/**
	 * 
	 * @描述: 按ID取班级信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午01:50:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String, String>> getBjxxByIdpl(ZcfsModel model){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.bjdm,t2.bjmc,t1.xn,t1.xq from xg_zhcp_fstjjlb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where t1.id=?");
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 计算综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-26 下午02:33:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean computeZcpm(String xn ,String xq ,String bjdm) throws Exception{
		return dao.runProcuder("{call pro_xg_zhcp_jspm(?,?,?)}", 
				new String[]{xn,xq,bjdm});
	}
	
	/**
	 * 
	 * @描述: 分学院比例计算综测分
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 下午05:00:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean computeZcpmByXy(String xn ,String xq ,String bjdm) throws Exception{
		return dao.runProcuder("{call pro_xg_zhcp_jspm_xy(?,?,?)}", 
				new String[]{xn,xq,bjdm});
	}
	
	
	/**
	 * 
	 * @描述: 分年级比例计算综测分
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 下午05:01:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean computeZcpmByNj(String xn ,String xq ,String bjdm) throws Exception{
		return dao.runProcuder("{call pro_xg_zhcp_jspm_nj(?,?,?)}", 
				new String[]{xn,xq,bjdm});
	}
	/**
	 * 计算平均分名次（浙江机电职业技术学院）
	 */
	public boolean computeZYPDB_ZJJDZYJSXY(String xn ,String xq ,String bjdm) throws Exception{
		return dao.runProcuder("{call PRO_XG_ZHCP_ZYPDB_ZJJDZYJSXY(?,?,?)}", 
				new String[]{xn,xq,bjdm});
	}
	
	/**
	 * 
	 * @描述: 重新计算参评组、年级专业排名
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-9 下午03:59:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean cxJspm(String xn ,String xq) throws Exception{
		return dao.runProcuder("{call pro_xg_zhcp_cxpm(?,?)}", 
				new String[]{xn,xq});
	}
	
	
	
	
	/**
	 * 
	 * @描述: 根据ID查询参评班学生
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 下午02:48:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String[] getStuById(String id) throws Exception{
		
		String sql = "select xh from xg_pjpy_new_cpmdb where bjdm=(select bjdm from xg_zhcp_fstjjlb where id=?)";
		
		return dao.getRs(sql, new String[]{id}, "xh");
	}
	
	
	/**
	 * 
	 * @描述: 根据ID查询参评班学生  （可多选
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 下午02:48:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String[] getStuById(ZcfsModel model, User user) throws Exception{
		
		//用户权限
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		String[] ids = model.getId().split(",");
		
		sql.append("select xh from xg_pjpy_new_cpmdb where bjdm in (select a.bjdm from ");
		sql.append("(select a.id,a.xn,a.xq,a.cpzdm,a.tjr,a.tjsj,a.tjzt,b.* from xg_zhcp_fstjjlb a left join view_njxyzybj_all b on a.bjdm=b.bjdm ) a where a.bjdm is not null ");
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
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" )");
		sql.append(" and xn||xq in (select xn||xq from xg_pjpy_new_csszb) ");
		
		System.out.println("----导入查询学生-------"+sql.toString()+"----------------");
		System.out.println("------条件字段---------"+inputV.toString()+"");
		return dao.getRs(sql.toString(),inputV, "xh");
	}
	
	
	/**
	 * 
	 * @描述: 批量插入综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 下午03:27:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
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
	 * 测试测试测试（实际生产环境发生锁表问题进行测试）
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean batchWhZcfs(List<String[]> params) throws Exception{

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
		boolean result = true;
		for(int i =0;i<params.size();i++){
			result = dao.runUpdate(sql.toString(),params.get(i));
		}
		return result;
	}

	public boolean updateZcfs(ZcfsModel t) throws Exception{
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO xg_zhcp_zcfsb t1");
			sql.append(" USING (select a.*,? lrr,?lrsj from(select xh,xn,xq,xmdm,sum(fs) fs from xg_zhcp_zcfsb_yf where xh=? and xmdm=? and xn=?and xq=? group by xh,xn,xq,xmdm)a) t2");
			sql.append(" ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm)");
			sql.append(" WHEN MATCHED THEN");
			sql.append("   UPDATE");
			sql.append("     SET xn=t2.xn,xq=t2.xq,fs=t2.fs,lrr=t2.lrr,");
			sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
			sql.append("   WHERE xh=? and xmdm=?");
			sql.append("WHEN NOT MATCHED THEN");
			sql.append("  INSERT (xh, xn, xq, xmdm, fs, lrr, lrsj)");
			sql.append("  VALUES (t2.xh, t2.xn, t2.xq, t2.xmdm, t2.fs, t2.lrr, t2.lrsj)");
			return dao.runUpdate(sql.toString(), new String[]{t.getLrr(),t.getLrsj(),t.getXh(),t.getXmdm(),t.getXn(),t.getXq(),t.getXh(),t.getXmdm()});
		}
	public boolean batchInsertZcfsYf(List<String[]> params) throws SQLException{
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO xg_zhcp_zcfsb_yf t1");
			sql.append(" USING (select ? xh, ? xn, ? xq,? yf, ? xmdm, ? fs , ? lrr , to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') lrsj from dual) t2");
			sql.append(" ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm and t1.yf=t2.yf)");
			sql.append(" WHEN MATCHED THEN");
			sql.append("   UPDATE");
			sql.append("     SET xn=t2.xn,xq=t2.xq,fs=t2.fs,lrr=t2.lrr,");
			sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
			sql.append("   WHERE xh=? and xmdm=? and yf=?");
			sql.append("WHEN NOT MATCHED THEN");
			sql.append("  INSERT (xh, xn, xq, yf,xmdm, fs, lrr, lrsj)");
			sql.append("  VALUES (t2.xh, t2.xn, t2.xq,t2.yf, t2.xmdm, t2.fs, t2.lrr, t2.lrsj)");
			
			int[] result = dao.runBatch(sql.toString(), params);
			return dao.checkBatchResult(result);
		}
	
	/**
	 * 
	 * @描述: 批量更新综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 下午03:28:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
	 */
	public boolean batchUpdateZcfs(List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_zhcp_zcfsb set xh=?,xn=?,xq=?,xmdm=?,fs=?,lrr=?,");
		sql.append("lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where xh=? and xmdm=?");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @描述: 同步接口数据
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 下午06:45:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param bjdm
	 * @param proName
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean getIntefaceData(String xmdm, String bjdm,String proName) throws Exception{
		
		StringBuilder sql = new StringBuilder("{call ").append(proName).append("(?,?)}");
		return dao.runProcuder(sql.toString(), new String[]{xmdm,bjdm});
	}
	
	/**
	 * 
	 * @描述:
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-2 上午10:09:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param bjdm
	 * @param proName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getIntefaceDataZdxm(String bjdm,String proName) throws Exception{
		
		StringBuilder sql = new StringBuilder("{call ").append(proName).append("(?)}");
		return dao.runProcuder(sql.toString(), new String[]{bjdm});
	}
	
	
	/**
	 * 
	 * @描述:取消提交综测分查询
	 * @作者：cq [工号：785]
	 * @日期：2013-7-30 上午10:21:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcfqxList(ZcfsModel t, User user)
	throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select a.id,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.bjrs,a.tjr,t7.sydm,t7.symc, ");
		sql.append(" (select xm from yhb b where a.tjr=b.yhm)tjrxm,a.tjsj  from xg_zhcp_fstjjlb a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm  ");
		sql.append(" left join XG_XTWH_SYBJGLB t6 on a.bjdm = t6.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t7 on t6.sydm = t7.sydm ");
		sql.append(" left join (select xn,xq, bjdm,count(1)bjrs from xg_pjpy_new_cpmdb group by bjdm,xn,xq) c ");
		sql.append(" on a.bjdm=c.bjdm and a.xn=c.xn and a.xq=c.xq");
		sql.append(" where tjzt = '1' and exists (select 1 from xg_pjpy_new_csszb b where a.xn=b.xn and a.xq = b.xq and rownum = 1) ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}


	/** 
	 * @描述: 综测结果查询
	 * @作者：cq [工号：785]
	 * @日期：2013-7-31 下午04:16:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param zcfpm
	 * @param zcxmList
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
		
	public List<HashMap<String, String>> getZcfjgList(ZcfsModel t, User user, String pmfs ,List<HashMap<String,String>> zcxmList)
		throws Exception {
		
		if("xn".equals(t.getZczq())){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		if("11483".equals(Base.xxdm)){
			sql.append(" select * from (select r11.dddm,r12.ddmc,t1.*,t22.mz,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc,t4.cpzdm,t4.cpzmc,");
		}else{
			sql.append(" select * from (select t1.*,t22.mz,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc,t4.cpzdm,t4.cpzmc,t10.bjmc zybjmc,t11.sydm sy, ");
		}
		sql.append("  case when t5.xh is null then '否' else '是' end sfwtgg");
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			if(StringUtils.isNotNull(zcxmList.get(i).get("xmlx"))&&zcxmList.get(i).get("xmlx").equals("4")){
				sql.append(", (select pjdjmc from xg_pjpy_new_pjdj b where b.pjdjdm=t2.fs").append(i).append(" and b.pjxmmc ='"+zcxmList.get(i).get("xmmc")+"') fs").append(i);
			}else{
				sql.append(", t2.fs").append(i);
			}
			sql.append(", t2.pm").append(i).append(" || '/' || t6.rs pm").append(i);
		}
		
		if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
			sql.append(",t1.xn||' '||(select xqmc from xqdzb b where t2.xq=b.xqdm) zczq");
		}else{
			sql.append(" ,t1.xn zczq ");
		}
		sql.append(" from xg_pjpy_new_cpmdb t1 left join (");
		sql.append(" select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
		}
		
		sql.append(" xh,xn,xq from ( select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(" case when t.xmmc='").append(zcxmList.get(i).get("xmmc"))
			   .append("' then t.fs else '' end fs")
			   .append(i).append(",");
			sql.append(" case when t.xmmc='").append(zcxmList.get(i).get("xmmc"))
			   .append("' then t.").append(pmfs).append(" else '' end pm")
			   .append(i).append(",");
		}
		
		sql.append(" t.xh,t.xn,t.xq from (select a.*,b.xmmc from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm ) ");
		sql.append(" t ) group by xh,xn,xq ) t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join xsxxb t22 on t1.xh = t22.xh");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm");
		sql.append(" left join view_njxyzybj_all t10 on t1.zybj=t10.bjdm");
		sql.append(" left join VIEW_NJSYBJ t11 on t1.BJDM=t11.BJDM ");

		sql.append(" left join (select a.xn, a.xq, a.bjdm, a.cpzdm, b.cpzmc");
		sql.append("  from XG_ZHCP_FSTJJLB a");
		sql.append(" left join XG_ZHCP_CPZB b");
		sql.append(" on a.cpzdm = b.cpzdm) t4 on t1.xn=t4.xn and t1.xq=t4.xq and t1.bjdm=t4.bjdm");
		if("11483".equals(Base.xxdm)){
			sql.append(" left join xg_xsxx_zjjcxy_ddwhb r11 on t22.bjdm=r11.qddm ");
			sql.append("  left join xg_xsxx_zjjcxy_dddmb r12 on r11.dddm=r12.dddm ");
		}
		sql.append(" left join (select * from xg_pjpy_new_tsxsb where lxdm = '6') t5 on t1.xn = t5.xn and t1.xq = t5.xq and t1.xh = t5.xh ");
		String xnxqTjSql = "";
		if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
			xnxqTjSql = " and xq <> 'on' ";
		}else{
			xnxqTjSql = " and xq = 'on' ";
		}
		if(StringUtils.isNull(pmfs)||pmfs.equals("cpzpm")){
			sql.append("  left join (");
			sql.append(" select count(xh) rs,cpzdm,xn,xq from");
			sql.append(" (select distinct t.cpzdm,t1.xh,t.xn,t.xq from xg_zhcp_fstjjlb t");
			sql.append(" left join xg_pjpy_new_cpmdb t1");
			sql.append(" on t.bjdm = t1.bjdm and t.xn = t1.xn and t.xq = t1.xq where t.tjzt = '1') group by cpzdm,xq,xn");
			sql.append(" )t6 on t4.cpzdm = t6.cpzdm and t4.xn = t6.xn and t4.xq = t6.xq");
			sql.append(" ");
		}else if(pmfs.equals("bjpm")){
			sql.append("  left join (");
			sql.append(" select count(xh) rs,bjdm,xn,xq from");
			sql.append(" (select distinct t.bjdm,t1.xh,t.xn,t.xq from xg_zhcp_fstjjlb t");
			sql.append(" left join xg_pjpy_new_cpmdb t1");
			sql.append(" on t.bjdm = t1.bjdm and t.xn = t1.xn and t.xq = t1.xq where t.tjzt = '1') group by bjdm,xq,xn");
			sql.append(" )t6 on t4.bjdm = t6.bjdm and t4.xn = t6.xn and t4.xq = t6.xq");
			sql.append(" ");
		}else if(pmfs.equals("njzypm")){
			sql.append("  left join (");
			sql.append("  select count(xh) rs,zydm,nj,xn,xq from");
			sql.append(" (select distinct t2.nj,t2.zydm,t1.xh,t.xn,t.xq from xg_zhcp_fstjjlb t");
			sql.append(" left join xg_pjpy_new_cpmdb t1");
			sql.append(" on t.bjdm = t1.bjdm and t.xn = t1.xn and t.xq = t1.xq");
			sql.append(" left join view_njxyzybj_all t2");
			sql.append(" on t1.bjdm = t2.bjdm");
			sql.append(" where t.tjzt = '1') group by zydm,nj,xq,xn");
			sql.append(" ");
			sql.append(" )t6 on t3.zydm = t6.zydm and t1.xn = t6.xn and t1.xq = t6.xq and t3.nj = t6.nj");
			sql.append(" ");
		}else if(pmfs.equals("zybjpm")){
			sql.append("  left join (");
			sql.append("  select count(xh) rs, t1.ZYBJ,t1.XN,t1.XQ");
			sql.append("  from XG_PJPY_NEW_CPMDB t1 ");
			sql.append("  left join XG_ZHCP_FSTJJLB t2 ON t2.BJDM=t1.BJDM and t2.XN=t1.XN and t2.XQ=t1.XQ ");
			sql.append("  where t2.TJZT='1'");
			sql.append(" GROUP BY t1.ZYBJ,t1.XN,t1.XQ )t6 ON t6.ZYBJ=t1.ZYBJ and t1.XN=t6.XN and t1.XQ=t6.XQ");
			sql.append(" ");
		}
		sql.append(" where");
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){ //浙江大学按照单个人提交综测
			sql.append(" t1.tjzt='1'");
		} else{
			sql.append(" exists (select 1 from xg_zhcp_fstjjlb t3 where t1.bjdm=t3.bjdm ");
			sql.append(" and t1.xn=t3.xn and t1.xq=t3.xq and t3.tjzt='1') ");
		}
		sql.append(" order by ");
		if(StringUtils.isNull(pmfs)||pmfs.equals("cpzpm")){
			sql.append("t4.cpzdm");
		}else if(pmfs.equals("bjpm")){
			sql.append("t1.bjdm");
		}else if(pmfs.equals("njzypm")){
			sql.append("t3.zydm");
		}else if(pmfs.equals("zybjpm")){
			sql.append(" t1.zybj ");
		}
		if (zcxmList.size() > 0){
			sql.append(" ,t2.fs0 desc ");
		}
		sql.append("  ) t where 1=1 ");
		sql.append(xnxqTjSql);
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{},inputValue));
	}
	/** 
	 * 浙江机电职业技术学院 导出综测详表
	 */
	public List<HashMap<String, String>> getZcfjgList_12861(ZcfsModel t, User user, String pmfs ,List<HashMap<String,String>> zcxmList)
		throws Exception {
		if("xn".equals(t.getZczq())){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select t1.*,t22.mz,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc,t4.cpzdm,t4.cpzmc,");
		sql.append("  case when t5.xh is null then '否' else '是' end sfwtgg");
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			if(StringUtils.isNotNull(zcxmList.get(i).get("xmlx"))&&zcxmList.get(i).get("xmlx").equals("4")){
				sql.append(", (select pjdjmc from xg_pjpy_new_pjdj b where b.pjdjdm=t2.fs").append(i).append(" and b.pjxmmc ='"+zcxmList.get(i).get("xmmc")+"') fs").append(i);
			}else{
				sql.append(", t2.fs").append(i);
				if("N1".equals(zcxmList.get(i).get("zfflag"))){
					String qzbl = zcxmList.get(i).get("qzbl");
					qzbl = StringUtils.isNull(qzbl) ? "100" : qzbl;
					sql.append(", cast(t2.fs").append(i).append("*"+qzbl+"/100 as decimal(10,4)) fsn").append(i);
				}else if("N0".equals(zcxmList.get(i).get("zfflag"))){
					sql.append(", t2.bjpm");
				}
			}
		}
		// ============ 智育评定表（浙江机电职业技术学院） begin ==========
		sql.append(" ,t6.zcj,t6.kcms,t6.pjcj,t6.mc,t6.bjgms,t6.yyjqt ");
		// ============ 智育评定表（浙江机电职业技术学院） end ==========
		if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
			sql.append(",t1.xn||' '||(select xqmc from xqdzb b where t2.xq=b.xqdm) zczq");
		}else{
			sql.append(" ,t1.xn zczq ");
		}
		sql.append(" from xg_pjpy_new_cpmdb t1 left join (");
		sql.append(" select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",");
			if("N0".equals(zcxmList.get(i).get("zfflag"))){
				sql.append(" sum(bjpm) bjpm, ");
			}
		}
		
		sql.append(" xh,xn,xq from ( select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(" case when t.xmmc='").append(zcxmList.get(i).get("xmmc"))
			.append("' then t.fs else '' end fs")
			.append(i).append(",");
			if("N0".equals(zcxmList.get(i).get("zfflag"))){
				sql.append(" case when t.xmmc='"+zcxmList.get(i).get("xmmc")+"' then t.bjpm else '' end bjpm, ");
			}
		}
		
		sql.append(" t.xh,t.xn,t.xq from (select a.*,b.xmmc from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm ) ");
		sql.append(" t ) group by xh,xn,xq ) t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join xsxxb t22 on t1.xh = t22.xh");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm");
		sql.append(" left join (select a.xn, a.xq, a.bjdm, a.cpzdm, b.cpzmc");
		sql.append("  from XG_ZHCP_FSTJJLB a");
		sql.append(" left join XG_ZHCP_CPZB b");
		sql.append(" on a.cpzdm = b.cpzdm) t4 on t1.xn=t4.xn and t1.xq=t4.xq and t1.bjdm=t4.bjdm");
		sql.append(" left join (select * from xg_pjpy_new_tsxsb where lxdm = '6') t5 on t1.xn = t5.xn and t1.xq = t5.xq and t1.xh = t5.xh ");
		// ============ 智育评定表（浙江机电职业技术学院） begin ==========
		sql.append(" left join xg_zhcp_zypdb_zjjdzyjsxy t6 on t1.xn = t6.xn and t1.xq = t6.xq and t1.xh = t6.xh ");
		// ============ 智育评定表（浙江机电职业技术学院） end ==========
		sql.append(" where exists (select 1 from xg_zhcp_fstjjlb t3 where t1.bjdm=t3.bjdm ");
		sql.append(" and t1.xn=t3.xn and t1.xq=t3.xq and t3.tjzt='1') ");
		sql.append(" order by t1.bjdm,t2.bjpm ");
		sql.append("  ) t where 1=1 ");
		if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
			sql.append(" and xq <> 'on' ");
		}else{
			sql.append(" and xq = 'on' ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{},inputValue));
	}
	
	public List<HashMap<String, String>> getZcfjgList(String bjdms, String xn,String xq,String pmfs ,List<HashMap<String,String>> zcxmList){

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select t1.*,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc,t4.cpzdm,t4.cpzmc");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(", t2.fs").append(i).append(", t2.pm").append(i);
		}
		
		sql.append(" from xg_pjpy_new_cpmdb t1 left join (");
		sql.append(" select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
		}
		
		sql.append(" xh from ( select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t.fs else '0' end fs")
			   .append(i).append(",");
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t.").append(pmfs).append(" else '' end pm")
			   .append(i).append(",");
		}
		
		sql.append(" t.xh from xg_zhcp_zcfsb t ) group by xh ) t2 on t1.xh=t2.xh");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm");
		sql.append(" left join (select a.xn, a.xq, a.bjdm, a.cpzdm, b.cpzmc");
		sql.append("  from XG_ZHCP_FSTJJLB a");
		sql.append(" left join XG_ZHCP_CPZB b");
		sql.append(" on a.cpzdm = b.cpzdm) t4 on t1.xn=t4.xn and t1.xq=t4.xq and t1.bjdm=t4.bjdm");
		sql.append(" where exists (select 1 from xg_zhcp_fstjjlb t3 where t1.bjdm=t3.bjdm ");
		sql.append(" and t1.xn=t3.xn and t1.xq=t3.xq and t3.tjzt='1') ");
		sql.append(" order by ");
		if(StringUtils.isNull(pmfs)||pmfs.equals("cpzpm")){
			sql.append("t4.cpzdm,");
		}else if(pmfs.equals("bjpm")){
			sql.append("t3.bjdm,");
		}else if(pmfs.equals("njzypm")){
			sql.append("t3.zydm,");
		}
		sql.append(" t2.pm0 ) t where 1=1  and xn=? and xq=? and bjdm in ("+bjdms+") ");
		//上海戏剧个性化判断
		if("10279".equalsIgnoreCase(Base.xxdm)){
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				if("平均绩点".equals(zcxmList.get(i).get("xmmc"))){
					sql.append("order by pm").append(i).append(" asc");
				}
			}
		}		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq});
	}

	/**
	 * @throws Exception  
	 * @描述:保存综测分取消记录
	 * @作者：cq [工号：785]
	 * @日期：2013-8-1 上午09:46:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * @param qxyy
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertTzjl(String id, User user, ZcfsModel t, String xn, String xq) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		String[] params = new String[3];
		
		params[0] = xn;
		params[1] = xq;
		params[2] = t.getQxyy();
		
		sql.append(" insert into xg_zhcp_qxtjjlb(xn,xq,bjdm,qxr,qxsj,qxyy,ytjr,ytjsj) ");
		sql.append(" select ?,?,bjdm,'"+user.getUserName()+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') qxsj,?,tjr,tjsj from xg_zhcp_fstjjlb where id in (");
		
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
	 * @throws Exception  
	 * @描述:更新评奖人员库
	 * @作者：cq [工号：785]
	 * @日期：2013-8-1 上午09:47:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateCpmd(String id, User user, String defaultQxtj) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" update xg_zhcp_fstjjlb set tjzt = '"+defaultQxtj+"',tjr = '',tjsj='' where id in ( ");
		
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
	 * @描述:查询综测分取消记录
	 * @作者：cq [工号：785]
	 * @日期：2013-8-1 下午03:08:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcfsForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZcfqxjlList(ZcfsModel t,
			User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		CsszService csszService = new CsszService();
		String szyf = csszService.getCsz("szyf");
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select * from ( ");
		sql.append(" select a.id,b.nj, b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.qxr,t7.sydm,t7.symc, ");
		sql.append(" (select xm from yhb b where a.qxr = b.yhm) qxrxm,a.qxsj,a.qxyy ");
		if("1".equals(szyf)){
			sql.append(" ,a.yf, to_char(to_date(a.yf,'yyyy-MM'),'yyyy') nd,to_char(to_date(a.yf,'yyyy-MM'),'mm') mon from xg_zhcp_qxtjjlb_yf a  ");
		}else{
			sql.append(" from xg_zhcp_qxtjjlb a  ");
		}
		
		sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		sql.append(" left join XG_XTWH_SYBJGLB t6 on a.bjdm = t6.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t7 on t6.sydm = t7.sydm ");
		sql.append(" where a.xn in (select xn from xg_pjpy_new_csszb where rownum = 1) ");
		sql.append(" and a.xq in (select xq from xg_pjpy_new_csszb where rownum =1)) t1 ");
		sql.append(" where qxr = '"+user.getUserName()+"' or (1=1 ");
		sql.append(searchTjByUser);
		sql.append(" )) where 1=1 ");
		sql.append(searchTj);
		
		
		return getPageList(t, sql.toString(), inputValue);
	}
	

	
	/**
	 * 
	 * @描述: 按学号查询指定评奖周期综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午10:14:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getZcfListByXh(String xh ,String xn ,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.fs,t1.cpzpm,t1.njzypm,t1.bjpm,");
		sql.append(" t1.xmdm,t2.xmmc from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t3 where t2.xmdm=t3.xmdm ");
		sql.append(" and t2.xn=t3.xn and t2.xq=t3.xq and ");
		sql.append(" (t3.fjdm='N' or t3.fjdm in (select xmdm from xg_zhcp_zcxmb where fjdm='N'))");
		sql.append(" ) and t1.xn=? and t1.xq=? and t1.xh=? order by xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xh});
	}
	/**
	 * 按学年、学期、学号查询所有综测项目分
	 */
	public List<HashMap<String,String>> getZcfListAllByXh(String xh,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.fs,t1.cpzpm,t1.njzypm,t1.bjpm,");
		sql.append(" t1.xmdm,t2.fjdm,t2.xmmc from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xh=? order by xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xh});
	}
	/**
	 * 按学年、学期、(班级代码或学号)查询所有综测项目分（江苏信息职业技术学院）
	 */
	public List<HashMap<String,String>> getZcfListAllByBjdm_13108(String bjdm,String xh,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		String[] params = null;
		sql.append(" select a.*,b.xm,b.xb from ( ");
		sql.append(" select ");
		sql.append(" a.xh, ");
		sql.append(" sum(zczf_fs) zczf_fs, "); // 综测总分
		sql.append(" sum(zczf_bjpm) zczf_bjpm, "); // 综测总分班级排名
		sql.append(" sum(zczf_njzypm) zczf_njzypm, "); // 综测总分专业排名
		sql.append(" sum(stsz_fs) stsz_fs, "); // 身体素质
		sql.append(" sum(zyxlsz_fs) zyxlsz_fs, "); // 职业心理素质
		sql.append(" sum(nltzsz_fs) nltzsz_fs, "); // 能力拓展素质
		sql.append(" sum(sxpdsz_fs) sxpdsz_fs, "); // 思想品德素质
		sql.append(" sum(sxpdszjs_fs) sxpdszjs_fs, "); // 思想品德素质纪实
		sql.append(" sum(sxpdszpy_fs) sxpdszpy_fs, "); // 思想品德素质评议
		sql.append(" sum(xysz_fs) xysz_fs, "); // 学业素质
		sql.append(" sum(xysz_bjpm) xysz_bjpm, "); // 学业素质班级排名
		sql.append(" sum(sxsz_fs) sxsz_fs "); // 身心素质
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(" a.xh, ");
		sql.append(" case when a.fjdm='N' then a.fs else 0 end zczf_fs, ");
		sql.append(" case when a.fjdm='N' then a.bjpm else 0 end zczf_bjpm, ");
		sql.append(" case when a.fjdm='N' then a.njzypm else 0 end zczf_njzypm, ");
		sql.append(" case when instr(a.xmmc,'身体素质')>0 then a.fs else 0 end stsz_fs, ");
		sql.append(" case when instr(a.xmmc,'职业心理素质')>0 then a.fs else 0 end zyxlsz_fs, ");
		sql.append(" case when instr(a.xmmc,'能力拓展素质')>0 then a.fs else 0 end nltzsz_fs, ");
		sql.append(" case when instr(a.xmmc,'思想品德素质')>0 and instr(a.xmmc,'思想品德素质纪实')=0 and instr(a.xmmc,'思想品德素质评议')=0 then a.fs else 0 end sxpdsz_fs, ");
		sql.append(" case when instr(a.xmmc,'思想品德素质纪实')>0 then a.fs else 0 end sxpdszjs_fs, ");
		sql.append(" case when instr(a.xmmc,'思想品德素质评议')>0 then a.fs else 0 end sxpdszpy_fs, ");
		sql.append(" case when instr(a.xmmc,'学业素质')>0 then a.fs else 0 end xysz_fs, ");
		sql.append(" case when instr(a.xmmc,'学业素质')>0 then a.bjpm else 0 end xysz_bjpm, ");
		sql.append(" case when instr(a.xmmc,'身心素质')>0 then a.fs else 0 end sxsz_fs ");
		sql.append(" from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,to_number(t1.fs) fs,to_number(t1.cpzpm) cpzpm,to_number(t1.njzypm) njzypm,to_number(t1.bjpm) bjpm, ");
		sql.append(" t1.xmdm,t2.fjdm,t2.xmmc,t3.bjdm ");
		sql.append(" from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm ");
		sql.append(" left join xg_pjpy_new_cpmdb t3 on t1.xn=t3.xn and t1.xq=t3.xq and t1.xh=t3.xh ");
		sql.append(" where t1.xn=? and t1.xq=? ");
		if(!"".equals(bjdm)){
			sql.append(" and t3.bjdm=? ");
			params = new String[]{xn,xq,bjdm};
		}
		if(!"".equals(xh)){
			sql.append(" and t1.xh=? ");
			params = new String[]{xn,xq,xh};
		}
		sql.append(" ) a ");
		sql.append(" ) a group by a.xh ");
		sql.append(" ) a ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh ");
		sql.append(" order by a.zczf_fs desc ");

		return dao.getListNotOut(sql.toString(), params);
	}
	/**
	 * 按学年、学期、班级代码查询综测项目平均分（江苏信息职业技术学院）
	 */
	public HashMap<String,String> getZcfAvgByBjdm_13108(String bjdm,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		String[] params = new String[]{xn,xq,bjdm};
		sql.append(" select ");
		sql.append(" cast(avg(zczf_fs) as decimal(10,2)) zczf_fs, ");// 综测总分
		sql.append(" cast(avg(stsz_fs) as decimal(10,2)) stsz_fs, ");// 身体素质
		sql.append(" cast(avg(zyxlsz_fs) as decimal(10,2)) zyxlsz_fs, ");// 职业心理素质
		sql.append(" cast(avg(nltzsz_fs) as decimal(10,2)) nltzsz_fs, ");// 能力拓展素质
		sql.append(" cast(avg(sxpdsz_fs) as decimal(10,2)) sxpdsz_fs, ");// 思想品德素质
		sql.append(" cast(avg(sxpdszjs_fs) as decimal(10,2)) sxpdszjs_fs, ");// 思想品德素质纪实
		sql.append(" cast(avg(sxpdszpy_fs) as decimal(10,2)) sxpdszpy_fs, ");// 思想品德素质评议
		sql.append(" cast(avg(xysz_fs) as decimal(10,2)) xysz_fs, ");// 学业素质
		sql.append(" cast(avg(sxsz_fs) as decimal(10,2)) sxsz_fs ");// 身心素质
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(" a.xh, ");
		sql.append(" sum(zczf_fs) zczf_fs, ");
		sql.append(" sum(stsz_fs) stsz_fs, ");
		sql.append(" sum(zyxlsz_fs) zyxlsz_fs, ");
		sql.append(" sum(nltzsz_fs) nltzsz_fs, ");
		sql.append(" sum(sxpdsz_fs) sxpdsz_fs, ");
		sql.append(" sum(sxpdszjs_fs) sxpdszjs_fs, ");
		sql.append(" sum(sxpdszpy_fs) sxpdszpy_fs, ");
		sql.append(" sum(xysz_fs) xysz_fs, ");
		sql.append(" sum(sxsz_fs) sxsz_fs ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(" a.xh, ");
		sql.append(" case when a.fjdm='N' then a.fs else 0 end zczf_fs, ");
		sql.append(" case when instr(a.xmmc,'身体素质')>0 then a.fs else 0 end stsz_fs, ");
		sql.append(" case when instr(a.xmmc,'职业心理素质')>0 then a.fs else 0 end zyxlsz_fs, ");
		sql.append(" case when instr(a.xmmc,'能力拓展素质')>0 then a.fs else 0 end nltzsz_fs, ");
		sql.append(" case when instr(a.xmmc,'思想品德素质')>0 and instr(a.xmmc,'思想品德素质纪实')=0 and instr(a.xmmc,'思想品德素质评议')=0 then a.fs else 0 end sxpdsz_fs, ");
		sql.append(" case when instr(a.xmmc,'思想品德素质纪实')>0 then a.fs else 0 end sxpdszjs_fs, ");
		sql.append(" case when instr(a.xmmc,'思想品德素质评议')>0 then a.fs else 0 end sxpdszpy_fs, ");
		sql.append(" case when instr(a.xmmc,'学业素质')>0 then a.fs else 0 end xysz_fs, ");
		sql.append(" case when instr(a.xmmc,'身心素质')>0 then a.fs else 0 end sxsz_fs ");
		sql.append(" from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,to_number(t1.fs) fs,to_number(t1.cpzpm) cpzpm,to_number(t1.njzypm) njzypm,to_number(t1.bjpm) bjpm, ");
		sql.append(" t1.xmdm,t2.fjdm,t2.xmmc,t3.bjdm ");
		sql.append(" from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm ");
		sql.append(" left join xg_pjpy_new_cpmdb t3 on t1.xn=t3.xn and t1.xq=t3.xq and t1.xh=t3.xh ");
		sql.append(" where t1.xn=? and t1.xq=? ");
		sql.append(" and t3.bjdm=? ");
		sql.append(" ) a ");
		sql.append(" ) a group by a.xh ");
		sql.append(" ) a ");
		
		return dao.getMapNotOut(sql.toString(), params);
	}
	/**
	 * 按学年、学期、班级代码查询班级信息
	 */
	public HashMap<String,String> getBjxx(String bjdm,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		String[] params = new String[]{xn,xq,bjdm};
		sql.append(" select a.xn,a.xq,b.xymc,b.zymc,b.nj,b.bjmc,count(1) zyrs ");
		sql.append(" from xg_pjpy_new_cpmdb a ");
		sql.append(" left join view_njxyzybj_all b ");
		sql.append(" on a.bjdm = b.bjdm ");
		sql.append(" where a.xn=? and a.xq=? and a.bjdm=? ");
		sql.append(" group by a.xn,a.xq,b.xymc,b.zymc,b.nj,b.bjmc ");

		return dao.getMapNotOut(sql.toString(), params);
	}
	/**
	 * 按学年、学号查询，返回上、下学期综测分
	 */
	public List<HashMap<String,String>> getZcfListByXnXh(String xh,String xn){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.fs,t1.cpzpm,t1.njzypm,t1.bjpm,");
		sql.append(" t1.xmdm,t2.xmmc,t2.fjdm from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t3 where t2.xmdm=t3.xmdm ");
		sql.append(" and t2.xn=t3.xn and t2.xq=t3.xq and ");
		sql.append(" (t3.fjdm='N' or t3.fjdm in (select xmdm from xg_zhcp_zcxmb where fjdm='N'))");
		sql.append(" ) and t1.xn=? and t1.xh=? order by xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xh});
	}
	/** 
	 * 获得学生成绩
	 * @param kcxz 选修/必修 
	 */
	public List<HashMap<String,String>> getCjListByXhXnXq(String xh,String xn,String xq,String kcxz){
		StringBuilder sql = new StringBuilder();
		//判断如果model中没有学期信息，则SQL查时不要学期条件
		if("on".equals(xq)){
			sql.append(" select * from cjb where xh=? and xn=? and kcxz like ? order by kcxz,kcmc ");
			return dao.getListNotOut(sql.toString(), new String[]{xh,xn,"%"+kcxz+"%"});
		} else {
			sql.append(" select * from cjb where xh=? and xn=? and xq=? and kcxz like ? order by kcxz,kcmc ");
			return dao.getListNotOut(sql.toString(), new String[]{xh,xn,xq,"%"+kcxz+"%"});
		}
		
	}
	/** 
	 * 获得学生成绩
	 * @param kcxz 选修/必修 
	 */
	public List<HashMap<String,String>> getCjListByXhXn(String xh,String xn,String kcxz){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from cjb where xh=? and xn=?  and kcxz like ? order by kcxz,kcmc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn,"%"+kcxz+"%"});
	}
	/** 
	 * 获得学生成绩
	 * @param kcxz 选修/必修 
	 */
	public List<HashMap<String,String>> getCjListByXh(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from cjb where xh=? order by kcxz,kcmc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述: 按学号查询指定评奖周期智育分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午10:14:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getZyfListByXh(String xh ,String xn ,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.fs,t1.cpzpm,t1.njzypm,t1.bjpm,");
		sql.append(" t1.xmdm,t2.xmmc from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t3 where t2.xmdm=t3.xmdm ");
		sql.append(" and t2.xn=t3.xn and t2.xq=t3.xq and ");
		sql.append(" (t3.fjdm='N' or t3.fjdm in (select xmdm from xg_zhcp_zcxmb where fjdm='N'))");
		sql.append(" ) and xmmc = '智育分' and t1.xn=? and t1.xq=? and t1.xh=? order by xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xh});
	}
	
	
	/**
	 * 
	 * @描述: 按学号查询综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午10:15:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getAllZcfListByXh(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.fs,t1.cpzpm,t1.njzypm,t1.bjpm,");
		sql.append(" t1.xmdm,t2.xmmc from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t3 where t2.xmdm=t3.xmdm ");
		sql.append(" and t2.xn=t3.xn and t2.xq=t3.xq and ");
		sql.append(" (t3.fjdm='N' or t3.fjdm in (select xmdm from xg_zhcp_zcxmb where fjdm='N'))");
		sql.append(" ) and t1.xh=? order by xn,xq,xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	/** 
	 * @描述:根据ID查询取消记录
	 * @作者：cq [工号：785]
	 * @日期：2013-8-15 上午09:32:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getQxjl(String id) {

		StringBuilder sql = new StringBuilder();
		CsszService csszService = new CsszService();
		String szyf = csszService.getCsz("szyf");
		sql.append(" select a.xn,a.xq,a.qxr,(select xm from yhb b where a.qxr=b.yhm)qxrxm, ");
		sql.append(" a.qxsj,a.qxyy,a.ytjr,(select xm from yhb b where a.ytjr=b.yhm) ytjrxm, ");
		sql.append(" a.ytjsj,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,b.nj,b.xydm, ");
		sql.append(" b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc ");
		if("1".equals(szyf)){
			sql.append(",a.yf from xg_zhcp_qxtjjlb_yf a left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		}else{
			sql.append(" from xg_zhcp_qxtjjlb a left join view_njxyzybj_all b on a.bjdm=b.bjdm ");

		}
		sql.append("  where id = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
	/**
	 * 
	 * @描述: 查询学生在指定周期综测总分信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-29 上午11:06:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZczfByXh(String xh ,String xn ,String xq){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		sql.append(" select t1.*,t2.xmmc from xg_zhcp_zcfsb t1 left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.xmdm and t2.fjdm='N')");
		sql.append(" and t1.xh=?");
		
		// 学期
		if(StringUtils.isNotNull(xq)){
			sql.append(" and t1.xq = ? ");
			params.add(xq);
		}else{
			sql.append(" and t1.xq = ? ");
			params.add(CsszService.XQKG);
		}
		
		//学年
		if(StringUtils.isNotNull(xn)){
			sql.append(" and t1.xn = ? ");
			params.add(xn);
		}
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String [params.size()]));
	}


	
	/**
	 * 
	 * @描述: 按学生查询综测分数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-9-3 下午02:46:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<String[]> getZcfsByXh(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xn,t2.xqmc,t3.xmmc,t1.fs,t1.cpzpm,");
		sql.append(" t1.njzypm,t1.bjpm from xg_zhcp_zcfsb t1 left join xqdzb t2 ");
		sql.append(" on t1.xq=t2.xqdm left join xg_zhcp_zcxmb t3 on t1.xmdm=t3.xmdm where t1.xh=?");
		
		return dao.rsToListNotOut(sql.toString(), new String[]{xh});
	}

	/** 
	 * @描述:根据学号查询学生的参评班级
	 * @作者：cq [工号：785]
	 * @日期：2013-11-10 上午11:27:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getCpbjListByXh(String xh, String xn,
			String xq) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xn,a.xq,a.xh,a.bjdm,b.bjmc,b.zydm,b.zymc,b.xydm,b.xymc,b.nj from xg_pjpy_new_cpmdb a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm  ");
		sql.append(" where a.xn = ? and a.xq = ? and a.xh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xq,xh});
	}


	
	
	/**
	 * 
	 * @描述:通过学号查询终测分数
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcfsList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xqmc,t1.xn||' '||t2.xqmc zczq ,t3.xmmc from xg_zhcp_zcfsb t1 left join xqdzb t2 ");
		sql.append(" on t1.xq=t2.xqdm left join xg_zhcp_zcxmb t3 on t1.xmdm=t3.xmdm where t1.xh=? order by t1.xn desc,t1.xq desc,t3.xssx ");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	public List<HashMap<String,String>> getZcfsListForWord(String xh,String rownum) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, ");
		sql.append(" (select count(0) from view_xsjbxx a where bjdm=");
		sql.append(" (select bjdm from view_xsjbxx where xh='"+xh+"')) bjrs");
		sql.append(" from (select t1.*,t2.xqmc,t1.xn||' '||t2.xqmc zczq ,t3.xmmc ");
		sql.append(" from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xqdzb t2  on t1.xq=t2.xqdm ");
		sql.append(" left join xg_zhcp_zcxmb t3 on t1.xmdm=t3.xmdm ");
		sql.append(" where t1.xh=? order by t1.xn desc,t1.xq desc,t3.xssx) t where rownum<= ?");
		return dao.getListNotOut(sql.toString(), new String[] { xh,rownum });
	}
	
	/**
	 * @描述:通过学号查询综测分数
	 */
	public List<HashMap<String,String>> getZcfsNList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xqmc,t1.xn||' '||t2.xqmc zczq ,t3.xmmc from xg_zhcp_zcfsb t1 left join xqdzb t2 ");
		sql.append(" on t1.xq=t2.xqdm left join xg_zhcp_zcxmb t3 on t1.xmdm=t3.xmdm where t1.xh=?");
		sql.append(" and exists (select 1 from xg_zhcp_zcxmb t4 where t1.xmdm=t4.xmdm and t4.fjdm='N') ");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	
	/**
	 * 
	 * @描述:通过学号查询终测分数(老版本，池州学院个性化SQL)
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcfsListOld(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xn,t1.zd20 zcfbjpm1, t1.zd22 zcfbjpm2, zd21 zyfbjpm1, zd23 zyfbjpm2,zd28 dj1,zd27 dj2,zd30 bz1,zd29 bz2 ");
		sql.append("from xg_pjpy_zhcpb t1 where  t1.xh= ? and xq='no' and xn<>'no' order by t1.xn desc ");
	
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	/** 
	 * @描述:获取当前周期项目信息
	 * @作者：cq [工号：785]
	 * @日期：2014-7-22 下午04:49:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDqxmInfo() {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_pjxmb c where xn||xq in (select xn||xq from xg_pjpy_new_csszb where rownum =1)");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/**
	 * @throws Exception  
	 * @描述:学校人数分配方式更改
	 * @作者：cq [工号：785]
	 * @日期：2014-7-23 上午09:49:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateXxRssz(String xmdm) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_rsszb a set a.zzme = nvl(round(a.fpbl * 0.01 * (select sum(ytjrs) ytjrs ");
		sql.append("from (select b.*, a.ytjrs from (select t1.bjdm, count(1) ytjrs from xg_pjpy_new_cpmdb t1 ");
		sql.append("where exists (select 1 from Xg_Pjpy_New_Csszb t2 left join xg_zhcp_fstjjlb t3 ");
		sql.append("on t2.xn = t3.xn and t2.xq = t3.xq where t3.tjzt = '1' and t1.bjdm = t3.bjdm ");
		sql.append("and t1.xn = t2.xn and t1.xq = t2.xq) group by bjdm) a left join view_njxyzybj_all b ");
		sql.append("on a.bjdm = b.bjdm))),0) where a.xmdm = ? and a.bmdm = 'xx' and a.fpbl is not null ");
		
		try {
			dao.update(sql.toString(), new String[]{xmdm});
			
		} catch (Exception e) {
			//错误只返回false
			return false;
		}
		
		return true;
	}

	
	/**
	 * @throws Exception  
	 * @描述:年级+专业人数分配方式更改
	 * @作者：cq [工号：785]
	 * @日期：2014-7-23 上午11:04:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param string2
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateNjZyRssz(String bjdm, String xmdm) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_rsszb a set a.zzme = nvl(round(a.fpbl * 0.01 * (select ytjrs from ( ");
		sql.append("select count(1) ytjrs,t2.xymc,t2.zymc,t2.xydm, t2.zydm, t2.nj from xg_pjpy_new_cpmdb t1 ");
		sql.append("left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm = t2.bjdm where exists (select 1 from xg_pjpy_new_csszb t3 ");
		sql.append("left join xg_zhcp_fstjjlb t4 on t3.xn = t4.xn and t3.xq = t4.xq where t4.tjzt = '1' and t1.bjdm = t4.bjdm ");
		sql.append("and t1.xn = t3.xn and t1.xq = t3.xq) group by t2.xymc, t2.zymc, t2.xydm, t2.zydm, t2.nj) b ");
		sql.append("where a.bmdm=b.zydm and a.nj=b.nj ");
		sql.append(")),0) where a.xmdm = ? and a.bmdm||nj in (select zydm||nj from view_njxyzybj_all where bjdm = ? and rownum =1) ");
		sql.append("and a.fpbl is not null ");

		try {
			dao.update(sql.toString(), new String[]{xmdm,bjdm});
		} catch (Exception e) {
			//错误只返回false
			return false;
		}
		
		return true;
	}

	/**
	 * @throws Exception  
	 * @描述:班级人数分配方式更改
	 * @作者：cq [工号：785]
	 * @日期：2014-7-23 上午11:14:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param string2
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateBjRssz(String bjdm, String xmdm) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_rsszb a set a.zzme = nvl(round(a.fpbl * 0.01 * (select ytjrs from ( ");
		sql.append("select t1.bjdm, count(1) ytjrs from xg_pjpy_new_cpmdb t1 where exists (select 1 ");
		sql.append("from Xg_Pjpy_New_Csszb t2 left join xg_zhcp_fstjjlb t3 on t2.xn = t3.xn ");
		sql.append("and t2.xq = t3.xq where t3.tjzt = '1' and t1.bjdm = t3.bjdm and t1.xn = t2.xn ");
		sql.append("and t1.xq = t2.xq) group by bjdm) b where a.bmdm=b.bjdm ");
		sql.append(")),0) where a.xmdm = ? and a.bmdm = ? and a.fpbl is not null");
 
		try {
			dao.update(sql.toString(), new String[]{xmdm,bjdm});
		} catch (Exception e) {
			//错误只返回false
			return false;
		}
		
		return true;
		
	}

	/**
	 * @throws Exception  
	 * @描述:年级+学院人数分配方式更改
	 * @作者：cq [工号：785]
	 * @日期：2014-7-23 上午11:52:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param string2
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateNjxyRssz(String bjdm, String xmdm) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_rsszb a set a.zzme = nvl(round(a.fpbl * 0.01 * (select ytjrs from ( ");
		sql.append("select t2.xymc, t2.xydm, t2.nj, count(1) ytjrs from xg_pjpy_new_cpmdb t1 ");
		sql.append("left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm = t2.bjdm where exists (select 1 ");
		sql.append("from xg_pjpy_new_csszb t3 left join xg_zhcp_fstjjlb t4 on t3.xn = t4.xn ");
		sql.append("and t3.xq = t4.xq where t4.tjzt = '1' and t1.bjdm = t4.bjdm and t1.xn = t3.xn ");
		sql.append("and t1.xq = t3.xq) group by t2.xymc, t2.xydm, t2.nj) b where a.bmdm=b.xydm and a.nj=b.nj ");
		sql.append(")),0) where a.xmdm = ? and a.bmdm||nj in (select xydm||nj from view_njxyzybj_all where bjdm = ? and rownum =1) ");
		sql.append("and a.fpbl is not null ");
		
		try {
			dao.update(sql.toString(), new String[]{xmdm,bjdm});
		} catch (Exception e) {
			//错误只返回false
			return false;
		}
		
		return true;
	}

	/**
	 * @throws Exception  
	 * @描述:学院人数分配方式更改
	 * @作者：cq [工号：785]
	 * @日期：2014-7-23 上午11:56:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param string2
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateXyRssz(String bjdm, String xmdm) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_rsszb a set a.zzme = nvl(round(a.fpbl * 0.01 * (select ytjrs from ( ");
		sql.append("select count(1) YTJRS, t2.xydm from xg_pjpy_new_cpmdb t1 left join VIEW_NJXYZYBJ_ALL t2 ");
		sql.append("on t1.bjdm = t2.bjdm where exists (select 1 from xg_pjpy_new_csszb t3 ");
		sql.append("left join xg_zhcp_fstjjlb t4 on t3.xn = t4.xn and t3.xq = t4.xq where t4.tjzt = '1' ");
		sql.append("and t1.bjdm = t4.bjdm and t1.xn = t3.xn and t1.xq = t3.xq) group by t2.xydm, t2.xymc) b where a.bmdm=b.xydm ");
		sql.append(")),0) where a.xmdm = ? and a.bmdm = (select xydm from view_njxyzybj_all where bjdm = ? and rownum=1) and a.fpbl is not null");

		try {
			dao.update(sql.toString(), new String[]{xmdm,bjdm});
		} catch (Exception e) {
			//错误只返回false
			return false;
		}
		
		return true;
		
	}

	/**
	 * @throws Exception  
	 * @描述:参评组人数分配方式更改
	 * @作者：cq [工号：785]
	 * @日期：2014-7-23 下午02:03:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param string2
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateCpzRssz(String bjdm, String xmdm) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_rsszb a set a.zzme = nvl(round(a.fpbl * 0.01 * (select ytjrs from ( ");
		sql.append("select q.cpzdm, j.cpzmc, q.ytjrs from (select k.cpzdm, count(1) ytjrs ");
		sql.append("from xg_zhcp_fstjjlb k, xg_pjpy_new_cpmdb p where k.bjdm = p.bjdm ");
		sql.append("and p.xn = (select xn from xg_pjpy_new_csszb where rownum =1) and p.xq = (select xq from xg_pjpy_new_csszb where rownum =1) ");
		sql.append("and k.xn = (select xn from xg_pjpy_new_csszb where rownum =1) and k.xq = (select xq from xg_pjpy_new_csszb where rownum =1) ");
		sql.append("and k.tjzt = '1' group by k.cpzdm) q left join xg_zhcp_cpzb j on j.cpzdm = q.cpzdm) b ");
		sql.append("where a.bmdm=b.cpzdm ");
		sql.append(")),0) where a.xmdm = ? and a.bmdm in (select cpzdm from xg_zhcp_fstjjlb where bjdm = ? and xn||xq in (select xn||xq from xg_pjpy_new_csszb)) ");
		sql.append("and a.fpbl is not null ");
		
		try {
			dao.update(sql.toString(), new String []{xmdm,bjdm});
		} catch (Exception e) {
			//错误只返回false
			return false;
		}
		
		return true;
		
	}

	/**
	 * @throws Exception  
	 * @描述:计算学年综测分
	 * @作者：cq [工号：785]
	 * @日期：2014-8-7 上午11:12:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param bjdm
	 * void 返回类型 
	 * @throws 
	 */
	public boolean computeXnpm(String xn, String bjdm) throws Exception {
		String xxdm = Base.xxdm;
		String pro = "pro_xg_zhcp_xnzcf";
		if(Globals.existsPjpyXnpm(xxdm)){
			pro += "_"+xxdm;
		}
		return dao.runProcuder("{call "+pro+"(?,?)}", new String[]{xn,bjdm});
		
	}

	/** 
	 * @描述:根据ID查询班级信息
	 * @作者：cq [工号：785]
	 * @日期：2015-2-5 下午03:59:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getBjInfo(String id) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t3.nj, t3.xydm, t3.xymc, t3.zydm, t3.zymc, t3.bjdm, t3.bjmc from xg_zhcp_fstjjlb t1 ");
		sql.append("left join yhb t2 on t1.tjr = t2.yhm left join view_njxyzybj_all t3 on t1.bjdm = t3.bjdm ");
		sql.append("where t1.id = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
		
	}
	
	/**
	 * 
	 * 判断是否为空
	 * 
	 */
	public Boolean getIsNotblank(List<HashMap<String, String>> bjxxMap,ZcfsModel model){
		
		boolean isBlank = false;
		
		if(!org.apache.commons.lang.StringUtils.isBlank(model.getId())&&null!=bjxxMap&&0!=bjxxMap.size()){
			isBlank = true;
		}
		
		return isBlank;
	}

	/** 
	 * @描述:查看是否还有未提交记录
	 * @作者：cq [工号：785]
	 * @日期：2015-2-9 下午04:07:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcfsForm
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String isSubmitInfo(ZcfsModel t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select count(1) count from (select t1.id,t1.bjdm,t1.tjr,t2.xm tjrxm,");
		sql.append("t1.tjsj,nvl(tjzt, '0') tjzt,decode(t1.tjzt, '0','未提交', '1', '已提交','2',");
		sql.append(" '取消提交', '','未提交') tjztmc,t3.nj,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjmc,t4.bjrs");
		sql.append(" from xg_zhcp_fstjjlb t1 left join yhb t2 on t1.tjr = t2.yhm");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm = t3.bjdm");
		sql.append(" left join (select bjdm, count(1) bjrs from xg_pjpy_new_cpmdb");
		sql.append(" where xn || xq in (select xn || xq from xg_pjpy_new_csszb where rownum = 1) group by bjdm) t4 on t1.bjdm = t4.bjdm");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t5 where t1.xn || t1.xq = t5.xn || t5.xq) and t1.tjzt = '0') where 1=1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return dao.getOneRs(sql.toString(), inputValue, "count");
		
	}

	/** 
	 * @描述:获得等级list
	 * @作者：cq [工号：785]
	 * @日期：2015-2-13 下午03:39:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDj() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select pjdjdm dm,pjdjmc mc,pjxmmc xmmc ");
		sb.append(" from  xg_pjpy_new_pjdj ");
		sb.append(" order by pjdjdm desc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
		
	}

	/**
	 * @throws SQLException  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2015-5-7 下午01:48:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<String> 返回类型 
	 * @throws 
	 */
	public List<String> getDjmc() throws SQLException {
		
		String sql = "select distinct pjxmmc from xg_pjpy_new_pjdj";
		
		return dao.getList(sql, new String[]{}, "pjxmmc");
	}
	/**
	 * 
	 * @描述:班级人数
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-9 上午09:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws
	 */
	public String  getBjrs(String xh) throws SQLException {
		
		String sql = "select count(1) bjrs from xsxxb where bjdm in(select bjdm from xsxxb where xh=?)";
		
		return dao.getOneRs(sql, new String[]{xh}, "bjrs");
	}
	
	/**
	 * 
	 * @描述:获取学生的所有综测排名
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-29 下午03:21:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllZcpmWithXh(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xn, decode(a.xq,'on','','01','第一学期','02', ");
		sql.append(" '第二学期') xq, a.xh, a.bjpm || '/' || nvl(n.num, 0) pm ");
		sql.append(" from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b  on a.xmdm = b.xmdm ");
		sql.append(" left join xg_pjpy_new_cpmdb m on a.xn = m.xn and a.xq = m.xq");
		sql.append(" and a.xh = m.xh  left join (select xn, xq, bjdm, count(1) num ");
		sql.append(" from xg_pjpy_new_cpmdb  group by xn, xq, bjdm) n on n.bjdm = m.bjdm and  ");
		sql.append(" n.xn=m.xn and n.xq=m.xq where b.fjdm = 'N' order by a.xn, a.xq) where xh = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
		
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:综测分数录入（按月录入）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-10-28 下午01:59:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertZcfsYf(ZcfsModel model) throws Exception{
		String sql = "insert into xg_zhcp_zcfsb_yf(xh,xn,xq,yf,xmdm,fs,lrr,lrsj) values(?,?,?,?,?,?,?,?)";
		return dao.runUpdate(sql, new String[]{model.getXh(),model.getXn(),model.getXq(),model.getZcyf(),model.getXmdm(),model.getFs(),model.getLrr(),model.getLrsj()});
	}
	
	public boolean updateZcfsYf(ZcfsModel model) throws Exception{
		String sql = "update xg_zhcp_zcfsb_yf set yf=?,fs=?,lrr=?,lrsj=? where xh=? and xn=? and xq=? and xmdm=? and yf=?";
		return dao.runUpdate(sql, new String[]{model.getZcyf(),model.getFs(),model.getLrr(),model.getLrsj(),model.getXh(),model.getXn(),model.getXq(),model.getXmdm(),model.getZcyf()});
		
	}
	
	/**
	 * 
	 * @描述: 月份提交状态
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-5 下午03:17:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYfTjzt(String bjdm,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xn, t.xq, t.yf, nvl(t1.tjzt, '0') tjzt");
		sql.append(" from xg_pjpy_new_yfsz t");
		sql.append(" left join (select distinct xn, xq, yf, tjzt");
		sql.append(" from XG_ZHCP_FSTJJLB_yf");
		sql.append(" where bjdm = ? and xn = ? and xq = ?) t1");
		sql.append(" on (t.xn = t1.xn and t.xq = t1.xq and t.yf = t1.yf)");
		sql.append(" where t.xn = ? and t.xq = ?");
		return dao.getListNotOut(sql.toString(), new String[]{bjdm,xn,xq,xn,xq});
	}
	
	/**
	 * 
	 * @描述: 月提交状态
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-5 下午04:55:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param yf
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBjzcfYf(String id, String yf,String username) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" insert");
		sql.append(" into xg_zhcp_fstjjlb_yf(xn, xq, bjdm, cpzdm, tjr, tjsj, tjzt, yf)");
		sql.append(" select xn, xq, bjdm, cpzdm, '"+username+"', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), tjzt, ?	");
		sql.append(" from xg_zhcp_fstjjlb");
		sql.append(" where id = ?");
		sql.append(" ");
		
		return dao.runUpdate(sql.toString(), new String[]{yf,id});
	}
	
	/**
	 * 
	 * @描述: 成都市技师学院按月提交检验是否有未录入记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-6 上午10:41:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
    public String getSfyWlrYf(ZcfsModel t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select case");
		sql.append(" when (select count(1) xms");
		sql.append(" from xg_zhcp_zcxmb t1");
		sql.append(" where xn = ?");
		sql.append(" and xq = ?");
		sql.append(" and xmlx != '3' ");
		sql.append(" and not exists");
		sql.append(" (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm = t2.fjdm)) *");
		sql.append(" (select count(1) xss");
		sql.append(" from xg_pjpy_new_cpmdb t4");
		sql.append(" where t4.xn = ?");
		sql.append(" and t4.xq = ?");
		sql.append(" and t4.bjdm =");
		sql.append(" (select bjdm from xg_zhcp_fstjjlb where id = ?)) <=");
		sql.append(" (select count(1) count");
		sql.append(" from xg_zhcp_zcfsb_yf t1");
		sql.append(" where xn = ?");
		sql.append(" and xq = ?");
		sql.append(" and yf = ?");
		sql.append(" and exists");
		sql.append(" (select 1");
		sql.append(" from xg_pjpy_new_cpmdb t2");
		sql.append(" where t1.xh = t2.xh");
		sql.append(" and t1.xn = t2.xn");
		sql.append(" and t1.xq = t2.xq");
		sql.append(" and t2.bjdm =");
		sql.append(" (select bjdm from xg_zhcp_fstjjlb where id =?))) then");
		sql.append(" 'true'");
		sql.append(" else");
		sql.append(" 'false'");
		sql.append(" end flg");
		sql.append(" from dual");
		sql.append(" ");
		
		return dao.getOneRs(sql.toString(), new String[]{t.getXn(),t.getXq(),t.getXn(),t.getXq(),t.getId(),t.getXn(),t.getXq(),t.getZcyf() ,t.getId()}, "flg");
	}
    
    /**
     * 
     * @描述:成都市技师学院按月提交检验是否有空值
     * @作者：yxy[工号：1206]
     * @日期：2016-9-6 上午11:09:58
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param t
     * @return
     * String 返回类型 
     * @throws
     */
    public String getNullZcfYf(ZcfsModel t){
    	
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count");
		sql.append(" from xg_zhcp_zcfsb_yf t1");
		sql.append(" where exists");
		sql.append(" (select 1");
		sql.append(" from Xg_Pjpy_New_Cpmdb t2");
		sql.append(" where t2.xn = ?");
		sql.append(" and t2.xq = ?");
		sql.append(" and t2.bjdm = (select bjdm from xg_zhcp_fstjjlb where id = ?)");
		sql.append(" and t1.xh = t2.xh");
		sql.append(" and t1.xn = t2.xn");
		sql.append(" and t1.xq = t2.xq)");
		sql.append(" and fs is null and t1.yf = ?");
		sql.append(" and t1.xmdm in (select xmdm");
		sql.append(" from xg_zhcp_zcxmb");
		sql.append(" where xn = ?");
		sql.append(" and xq = ?");
		sql.append(" and fjdm <> 'N')");
		sql.append(" ");
		sql.append(" ");
		
		
		return dao.getOneRs(sql.toString(), new String[]{t.getXn(),t.getXq(),t.getId(),t.getZcyf(),t.getXn(),t.getXq()}, "count");
	}
    
    /**
     * 
     * @描述: 成都市技师学院按月提交综测分-综测分取消查询
     * @作者：yxy[工号：1206]
     * @日期：2016-9-6 下午02:05:18
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param t
     * @param user
     * @return
     * @throws Exception
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getZcfqxListYf(ZcfsModel t, User user)
	throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select *");
		sql.append(" from (select a.id,");
		sql.append(" b.nj,");
		sql.append(" b.xydm,");
		sql.append(" b.xymc,");
		sql.append(" b.zydm,");
		sql.append(" b.zymc,");
		sql.append(" b.bjdm,");
		sql.append(" b.bjmc,");
		sql.append(" c.bjrs,");
		sql.append(" a.tjr,");
		sql.append(" (select xm from yhb b where a.tjr = b.yhm) tjrxm,");
		sql.append(" a.tjsj,");
		sql.append(" substr(a.yf,0,4) nd,");
		sql.append(" substr (a.yf,5,6) yf,");
		sql.append(" substr(a.yf,0,4) || '年' || substr (a.yf,5,6) || '月' ndyfname,");
		sql.append(" a.yf ndyf");
		sql.append(" from xg_zhcp_fstjjlb_yf a");
		sql.append(" left join view_njxyzybj_all b");
		sql.append(" on a.bjdm = b.bjdm");
		sql.append(" left join (select xn, xq, bjdm, count(1) bjrs");
		sql.append(" from xg_pjpy_new_cpmdb");
		sql.append(" group by bjdm, xn, xq) c");
		sql.append(" on a.bjdm = c.bjdm");
		sql.append(" and a.xn = c.xn");
		sql.append(" and a.xq = c.xq");
		sql.append(" where tjzt = '1'");
		sql.append(" and exists (select 1");
		sql.append(" from xg_pjpy_new_csszb b");
		sql.append(" where a.xn = b.xn");
		sql.append(" and a.xq = b.xq");
		sql.append(" and rownum = 1)) t1");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}
    
   /**
    * 
    * @描述:成都技师按月提交综测分
    * @作者：yxy[工号：1206]
    * @日期：2016-9-6 下午04:02:57
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param id
    * @param user
    * @param t
    * @param xn
    * @param xq
    * @return
    * @throws Exception
    * boolean 返回类型 
    * @throws
    */
	public boolean insertTzjlOfYf(String id, User user, ZcfsModel t, String xn, String xq) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		String[] params = new String[3];
		
		params[0] = xn;
		params[1] = xq;
		params[2] = t.getQxyy();
		
		sql.append(" insert into XG_ZHCP_QXTJJLB_yf(xn,xq,bjdm,qxr,qxsj,qxyy,ytjr,ytjsj,yf) ");
		sql.append(" select ?,?,bjdm,'"+user.getUserName()+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') qxsj,?,tjr,tjsj,yf from xg_zhcp_fstjjlb_yf where id in (");
		
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
	 * 
	 * @描述: 成都市技师学院-修改参评名单
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-6 下午04:22:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param user
	 * @param defaultQxtj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCpmdOfYf(String id, User user, String defaultQxtj) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" update xg_zhcp_fstjjlb_yf set tjzt = '"+defaultQxtj+"'  where id in ( ");
		
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
   * 
   * @描述: 成都市技师学院取消综测分获取班级信息
   * @作者：yxy[工号：1206]
   * @日期：2016-9-6 下午04:30:27
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param id
   * @return
   * HashMap<String,String> 返回类型 
   * @throws
   */
  public List<HashMap<String,String>> getBjxxByIdOfYf(String id){
		
		StringBuilder sql = new StringBuilder();
		String[] ids = id.split(",");
		sql.append("select t1.bjdm,t2.bjmc,t1.xn,t1.xq from xg_zhcp_fstjjlb_yf t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where t1.id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
  
  /**
 * @throws Exception 
   * 
   * @描述:成都市技师学院，某班级单位在该综测周期内所有月份记录都取消提交，更新xg_zhcp_fstjjlb表
   * @作者：yxy[工号：1206]
   * @日期：2016-9-6 下午05:52:26
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param id
   * @param xn
   * @param xq
   * @return
   * boolean 返回类型 
   * @throws
   */
  public boolean updateAytjzcfLastOne(String id,String xn,String xq) throws Exception{
	  StringBuilder sql = new StringBuilder();
	  ArrayList<String> paralist = new ArrayList<String>();
	  sql.append(" update xg_zhcp_fstjjlb");
	  sql.append(" set tjzt = '2' where xn = ? and xq = ? and bjdm in(");
	  paralist.add(xn);
	  paralist.add(xq);
	  sql.append(" select bjdm from (");
	  sql.append(" select t.yqxzs-t1.cs flag,t.bjdm  ");
	  sql.append(" from (select count(1) yqxzs,bjdm ");
	  sql.append(" from xg_zhcp_fstjjlb_yf");
	  sql.append("  where bjdm in");
	  sql.append("  (select distinct bjdm from xg_zhcp_fstjjlb_yf where id in(");
	  String[] ids = id.split(",");
	  for (int i = 0; i < ids.length; i++) {
		sql.append(" ?");
		paralist.add(ids[i]);
		if(i != ids.length-1){
			sql.append(" ,");
		}
	  }
	  
	  sql.append(" )");
	  sql.append(" ) and xn = ? and xq = ?");
	  paralist.add(xn);
	  paralist.add(xq);
	  sql.append("group by bjdm  ) t");
	  sql.append(" left join ");
	  sql.append(" (select count(1) cs, bjdm");
	  sql.append(" from xg_zhcp_fstjjlb_yf");
	  sql.append("  where bjdm in");
	  sql.append("  (select distinct bjdm from xg_zhcp_fstjjlb_yf where id in(");
	  for (int i = 0; i < ids.length; i++) {
		sql.append(" ?");
		paralist.add(ids[i]);
		if(i != ids.length-1){
			sql.append(" ,");
		}
	  }
	  
	  sql.append(" )");
	  sql.append(" ) and xn = ? and xq = ?");
	  paralist.add(xn);
	  paralist.add(xq);
	  sql.append(" and tjzt != '1'");
	  sql.append(" group by bjdm) t1");
	  sql.append("  on t.bjdm=t1.bjdm) a");
	  sql.append(" where flag = 0");
	  sql.append(" 　 )");
	  return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
  }
  
  /**
   * 
   * @描述: 成都市技师学院按月提交统计查询
   * @作者：yxy[工号：1206]
   * @日期：2016-9-7 上午11:33:19
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param xn
   * @param xq
   * @return
   * List<HashMap<String,String>> 返回类型 
   * @throws
   */
  public List<HashMap<String, String>> getZcfAyTjTjcxList(String xn,String xq){
	  StringBuilder sql = new StringBuilder();
	  ArrayList<String> paralist = new ArrayList<String>();
	  sql.append(" select a.xn, a.xq, a.yf, b.zbjs - nvl(c.tjbjs,0) wtjbjs,nvl(c.tjbjs,0) tjbjs");
	  sql.append(" from (select *");
	  sql.append(" from xg_pjpy_new_yfsz");
	  sql.append(" where xn = ?");
	  sql.append(" and xq = ?) a");
	  paralist.add(xn);
	  paralist.add(xq);
	  sql.append(" left join (select count(1) zbjs from xg_zhcp_fstjjlb where xn = ? and xq= ?" +
	  		" and  bjdm in("+
            " select bjdm"+
            " from xg_pjpy_new_cpmdb"+
            " where xn || xq in"+
            " (select xn || xq"+
            " from xg_pjpy_new_csszb"+
            " where rownum = 1)"+
            " group by bjdm)" +
	  		") b");
	  paralist.add(xn);
	  paralist.add(xq);
	  sql.append(" on 1 = 1");
	  sql.append(" left join (select count(1) tjbjs, yf");
	  sql.append(" from xg_zhcp_fstjjlb_yf");
	  sql.append(" where xn = ?");
	  sql.append(" and xq = ?");
	  paralist.add(xn);
	  paralist.add(xq);
	  sql.append(" and tjzt = '1'");
	  sql.append(" group by yf) c");
	  sql.append(" on a.yf = c.yf order by to_number(a.yf) asc");
	  return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
	  
  }
  
  /**
	 * 
	 * @描述: 综测班级提交查询
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午10:16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getZcbjListCk(ZcfsModel t, User user,String tjzt,String yf)
		throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.id,t1.bjdm,t1.tjr,t2.xm tjrxm,t1.tjsj,nvl(tjzt,'0') tjzt,");
		sql.append("decode(t1.tjzt,'0','未提交','1','已提交','2','取消提交','','未提交') tjztmc,");
		sql.append("t3.nj,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjmc,t4.bjrs ");
		sql.append("from xg_zhcp_fstjjlb t1 left join yhb t2 on t1.tjr=t2.yhm ");
		sql.append("left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm ");
		sql.append("left join (select bjdm,count(1) bjrs from xg_pjpy_new_cpmdb ");
		sql.append(" where xn||xq in (select xn||xq from xg_pjpy_new_csszb where rownum=1) group by bjdm) t4 on t1.bjdm=t4.bjdm ");
		sql.append("where  exists (select 1 from xg_pjpy_new_csszb t5 where t1.xn||t1.xq=t5.xn||t5.xq) ");
		sql.append(") t1 where 1=1 and bjrs is not null ");
		if("1".equals(tjzt)){
			sql.append(" and bjdm in (");
			sql.append(" select bjdm from xg_zhcp_fstjjlb_yf where yf = '"+yf+"' and tjzt = '1'");
			sql.append(" )");
		}else{
			sql.append(" and bjdm not in (");
			sql.append(" select bjdm from xg_zhcp_fstjjlb_yf where yf = '"+yf+"' and tjzt = '1'");
			sql.append(" )");
		}
		
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @描述: 成都市技师学院检验提交记录是否已存在
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-10 下午01:02:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExistsInYfTjb(String id,String yf){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) count");
		sql.append(" from XG_ZHCP_FSTJJLB_yf");
		sql.append(" where xn || xq || bjdm =");
		sql.append(" (select xn || xq || bjdm from XG_ZHCP_FSTJJLB where id = ?)");
		sql.append(" and yf = ?");
		String count = dao.getOneRs(sql.toString(), new String[]{id,yf} , "count");
		return "0".equals(count) ? true :false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 成都市技师学院修改月份提交表状态
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-10 下午01:16:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param bjdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean udpateYfTjb(String id,String yf) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZHCP_FSTJJLB_yf ");
		sql.append(" set tjzt = '1' ");
		sql.append(" where xn || xq || bjdm =");
		sql.append(" (select xn || xq || bjdm from XG_ZHCP_FSTJJLB where id = ?)");
		sql.append(" and yf = ?");
		return dao.runUpdate(sql.toString(), new String[]{id,yf});
	}
	
	/**
	 * @描述：删除多余记录
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月23日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param yf
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean delDyjl() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_ZHCP_ZCFSB where id in");
		sql.append(" (select id from (select * from XG_ZHCP_ZCFSB  where xn || xq = (select xn || xq from xg_pjpy_new_csszb where rownum = 1)) t");
		sql.append(" where not exists (select 1 from xsxxb where xh = t.xh))");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述：同步综测分_只传入bjdm
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年2月28日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param proName
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean getIntefaceData(String bjdm,String proName) throws Exception{
		StringBuilder sql = new StringBuilder("{call ").append(proName).append("(?)}");
		return dao.runProcuder(sql.toString(), new String[]{bjdm});
	}
	
	/**
	 * 
	 * @描述: 根据项目名称学年学期获取综测分数[通用]
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-15 上午11:37:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZcfsByXmXnXqXh(String xmmc,String xn,String xq,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.fs from xg_zhcp_zcfsb t left join XG_ZHCP_ZCXMB t1");
		sql.append(" on t.xmdm = t1.xmdm  where t1.xmmc like ? and t.xn = ?  and (t.xq = ? or  t.xq = 'on') and t.xh=? and rownum = 1");
		return dao.getMapNotOut(sql.toString(),new String[]{"%"+xmmc+"%",xn,xq,xh});
	}
	/**
	 * @description	： 江西陶瓷个性化  非同步项目置0
	 * @author 		： CP（1352）
	 * @date 		：2017-12-4 下午05:07:55
	 * @param xn
	 * @param xq
	 * @param bjxxMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean insertFtbf(String xn, String xq,
			List<HashMap<String, String>> bjxxMap, ZcfsModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xn);
		params.add(xq);
		params.add(xn);
		params.add(xq);
		sql.append(" insert into xg_zhcp_zcfsb(xh,xmdm,xn,xq,fs,lrr,lrsj) ");
		sql.append(" select t1.xh,t2.xmdm,?,?,0,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_pjpy_new_cpmdb t1,xg_zhcp_zcxmb t2 ");
		sql.append(" where t1.xn=? and t1.xq=? and t2.jktb is null ");
		sql.append(" and t1.xn=t2.xn and t1.xq=t2.xq ");
		if(getIsNotblank(bjxxMap,model)){
			sql.append(" and t1.bjdm in ( ");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(")");
		}
		sql.append(" and not exists (select 1 from xg_zhcp_zcfsb t3 where t1.xh=t3.xh and t2.xmdm=t3.xmdm)");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 *  获取各科成绩、总分及排名信息列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-06 16:08
	 * @param zcfsForm
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
    public List<HashMap<String,String>> getXscjList(ZcfsModel zcfsForm, User user) throws Exception {

		SearchModel searchModel = zcfsForm.getSearchModel();

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(zcfsForm.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM (");
		sql.append("SELECT c.KCMC,c.CJ,c.KCLX,t.* FROM CJB c LEFT JOIN (");
		sql.append("SELECT t.*,dense_rank() OVER(PARTITION BY t.xn,t.xq,t.bjdm order by t.zhf DESC) zhfpm,");
		sql.append("dense_rank() OVER(PARTITION BY t.xn,t.xq,t.bjdm order by t.pjf DESC ) pjfpm,");
		sql.append("dense_rank() OVER(PARTITION BY t.xn,t.xq,t.bjdm order by t.DYF DESC) dyfpm FROM (");
		sql.append("SELECT t1.*,nvl(t2.DYF,0) DYF,round(t1.pjf*0.6+nvl(t2.DYF,0)*0.4,2) ZHF,t3.BZR FROM (");
		sql.append("SELECT c.XH,x.XM,x.XB,x.BJDM,x.BJMC,x.ZYDM,x.XYDM,x.MZ,x.NJ,c.XQ,q.XQMC,c.XN,round(avg(nvl(c.CJ,0)),2) pjf FROM CJB c ");
		sql.append("LEFT JOIN VIEW_XSBFXX x ON c.XH = x.XH ");
		sql.append("LEFT JOIN XQDZB q ON c.XQ = q.XQDM ");
		sql.append("GROUP BY c.XH,x.XM,x.XB,x.BJDM,x.BJMC,x.ZYDM,x.XYDM,x.MZ,x.NJ,c.XN,c.XQ,q.XQMC) t1 ");
		sql.append("LEFT JOIN (SELECT f.XH,f.XN,f.XQ,round(nvl(f.FS,0),2) DYF FROM XG_ZHCP_ZCFSB f ");
		sql.append("LEFT JOIN XG_ZHCP_ZCXMB x ON x.XMDM = f.XMDM AND x.XN = f.XN AND x.XQ = f.XQ ");
		// 提交前将德育分改为德育学分
		sql.append("WHERE x.XMMC = '德育学分') t2 ON t1.XH = t2.XH AND t1.XN = t2.XN AND t1.XQ = t2.XQ ");
		sql.append("LEFT JOIN (SELECT WM_CONCAT(f.XM) BZR,b.BJDM FROM BZRBBB b LEFT JOIN FDYXXB f ON b.ZGH = f.ZGH GROUP BY b.BJDM) t3 ");
		sql.append("ON t1.BJDM = t3.BJDM) t) t ");
		sql.append("ON c.XH = t.XH AND c.XN = t.XN AND c.XQ = t.XQ ");
		sql.append(") t where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
    }
    
    /**
	 *  获取总成绩排名.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-06 16:08
	 * @param zcfsForm
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
    public  String getCjpm(String xn,String xh,String bjmc){
    	StringBuilder sql = new StringBuilder();
		sql.append("select b.mc from");
		sql.append("(select a.* ,ROWNUM as mc from"); 
		sql.append("(select t.* from (SELECT t.xn,t.xh,t1.bjmc,sum(t.cj) as cj FROM CJB t ");
		sql.append("left join view_xsjbxx t1 ");
		sql.append(" on t.xh=t1.xh ");
		sql.append(" GROUP BY t.xn,t.xh,t1.bjmc ");
		sql.append(" order by t.xn,cj  ) t ");
		sql.append(" where xn like ? and bjmc=? order by cj desc) a ) b where b.xh=?");
    	return dao.getOneRs(sql.toString(),new String[]{"%"+xn+"%",bjmc,xh},"mc");
    }
    /**
	 *  获取必修课数.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-06 16:08
	 * @param zcfsForm
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
    public  String getBxks(String xn,String xh){
    	String sql = "select count(1) bxks from CJB where xh=? and kcxz like '%必修%' and xn like ?";
    	return dao.getOneRs(sql,new String[]{xh,"%"+xn+"%"},"bxks");
    }
    
    /**
	 *  获取必修课及格数.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-06 16:08
	 * @param zcfsForm
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
    public  String getBxkjgs(String xn,String xh){
    	String sql = "select count(1) bxkjgs from CJB where xh=? and kcxz like '%必修%' and  cj>=60  and xn like ? ";
    	return dao.getOneRs(sql,new String[]{xh,"%"+xn+"%"},"bxkjgs");
    }

	/**
	 * 根据名称获得id
	 * @param mc
	 * @param xn
	 * @return
	 */

    public String getZcxmIdByMc(String mc,String xn){
    	String sql="select XMDM from XG_ZHCP_ZCXMB where xmmc=? and xn=?";
    	return dao.getOneRs(sql,new String[]{mc,xn},"xmdm");
	}

	/**
	 * 获得学生的学分成绩
	 * @param xn
	 * @param xq
	 * @return
	 */
	public List<HashMap<String,String>> getXfcj(String xn,String xq){
    	String sql="select xn,xh,xfcj from XFCJB where xn=? and xq is null";
    	return dao.getListNotOut(sql,new String[]{xn});
	}
}
