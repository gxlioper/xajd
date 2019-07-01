/**
 * @部门:学工产品事业部
 * @日期：2013-4-24 下午01:41:30 
 */
package com.zfsoft.xgxt.xszz.zzxmjg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: DAO
 * @作者： maxh
 * @时间： 2013-4-24 下午01:41:30
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZzxmjgDao extends SuperDAOImpl<ZzxmjgForm> {

	// 是否班级评议.1：是，0：否
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xszz.sfbjpy"));
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzxmjgForm t)
			throws Exception {
		
		return null;
		// TODO 自动生成方法存根
	}

	/**
	 * 使用高级查询
	 * 
	 * @param
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZzxmjgForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		//改用视图
		sql.append(" select * from ( ");

		sql.append(" select m.*,t2.sydm,t3.symc ");
		sql.append(" from VIEW_NEW_DC_XSZZ_ZZMXJG m ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=m.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append(" ) a  where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by xn desc,xq,sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 学院获奖人数
	 */
	public List<String[]> getXyList(String xn, String xq ,String[] xmlb, String[] xmmc, User user) {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if(!StringUtil.isNull(xq)){
			params.add(xq);
		}
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.rs rs");
		sql.append(" from (select a.xmmc, a.xymc, count(1) rs");
		sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,(select bjdm from xsxxb t2 where a.xh=t2.xh)");
		sql.append(" bjdm from xg_xszz_new_zzxmjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_xszz_new_zzxmlbb b");
		sql.append(" on a.lbdm = b.lbdm");
		
		sql.append(" where a.xn = ? ");
		if(!StringUtil.isNull(xq)){
			sql.append(" and a.xq = ? ");
		}
		
		sql.append("and  b.lbdm in ( ");
		for (int i = 0; i < xmlb.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlb[i]);
		}
		sql.append(")");
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" group by a.xymc, a.xmmc) b").append(" where rs is not null and rs<>0 ");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] { "xymc" , "xmmc", "rs"});
	}

	/**
	 * 项目类别获奖人数
	 */
	public List<String[]> getXmleList(String xn , String xq , String[] xmlb , String[] xmmc, User user){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if(!StringUtil.isNull(xq)){
			params.add(xq);
		}
		sql.append("select b.xmmc xmmc , b.rs rs from  (");
		sql.append("select a.xmmc ,a.lbdm,");
		sql.append(" count(1) rs ");
	 	sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,(select bjdm from xsxxb t2 where a.xh=t2.xh)");
		sql.append(" bjdm from xg_xszz_new_zzxmjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join  xg_xszz_new_zzxmlbb b on a.lbdm = b.lbdm");
		sql.append(" where a.xn = ? ");
		if(!StringUtil.isNull(xq)){
			sql.append(" and a.xq = ? ");
		}
		sql.append("and  b.lbdm in( ");
		for (int i = 0; i < xmlb.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlb[i]);
		}
		sql.append(")");
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append("group by a.xmmc,a.lbdm) b order by lbdm");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] { "xmmc", "rs"});
	}
	
	/**
	 * 获奖人员姓名
	 */
	public List<String[]> getHjmdList(String xn , String xq , String[] xmlb , String[] xmmc){
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if(!StringUtil.isNull(xq)){
			params.add(xq);
		}
		
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.xm xm");
		sql.append(" from (select a.xmmc, a.xymc, c.xm");
		
		sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,(select bjdm from xsxxb t2 where a.xh=t2.xh)");
		sql.append(" bjdm from xg_xszz_new_zzxmjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
//		sql.append(searchTjByUser);
		sql.append(" )a");
		
		sql.append(" inner join xg_xszz_new_zzxmlbb b");
		sql.append(" on a.lbdm = b.lbdm");
		sql.append("  inner join xsxxb c on a.xh=c.xh ");
		sql.append(" where a.xn = ? ");
		if(!StringUtil.isNull(xq)){
			sql.append(" and a.xq = ? ");
		}
		sql.append("and  b.lbdm in( ");
		for (int i = 0; i < xmlb.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlb[i]);
		}
		sql.append(")");
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" ) b order by length(xm) ");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] {"xymc" , "xmmc", "xm"});
	}
	
	/**
	 * 根据代码获得名称
	 */
	public List<String> getXmlbmc(String[] xmlbdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();	
		
		sql.append("select lbmc from xg_xszz_new_zzxmlbb where lbdm in (");
		
		for (int i = 0; i < xmlbdm.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmlbdm[i]);
		}
		sql.append(")");
		
		return dao.getList(sql.toString(), params.toArray(new String[]{}), "lbmc");
		
	}

	/**
	 * 分页条件查询资助项目汇总信息
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> zzxmhzView(ZzxmjgForm t, User user,Boolean fyFlag)
			throws Exception {
		// 生成高级查询相关条件、条件值
		
		String searchTj = " and lbdm=? and xn=? and xq=? and xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};

		StringBuilder sql = new StringBuilder();

		//改用视图
		sql.append("select * from VIEW_NEW_DC_XSZZ_ZZMXJG a where 1=1 ");

		sql.append(searchTj);
		if(StringUtils.equals("12036", Base.xxdm) && StringUtils.equals("excelByXy", t.getType())){
			sql.append(" and xydm='"+t.getXydm()+"'");
		}
		sql.append(searchTjByUser);
		if(fyFlag){
			return getPageList(t, sql.toString(), inputV);
		}else{
			return dao.getListNotOut(sql.toString(), inputV);
		}
	}
	/**
	 * 分页条件查询资助项目汇总信息（闽南师范学院个性化）
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> zzxmhzView_10402(ZzxmjgForm t, User user)
	throws Exception {
		// ============== 获奖情况 行转列 begin ======================
		StringBuilder hjqkSql = new StringBuilder(); 
		StringBuilder concatSql = new StringBuilder(); 
		StringBuilder caseSql = new StringBuilder(); 
		for (int i = 1; i < 5; i++) {
			hjqkSql.append(" g.hjsj"+i+", ");
			hjqkSql.append(" g.hjmc"+i+", ");
			hjqkSql.append(" g.fjdw"+i+" ");
			
			concatSql.append(" wm_concat(a.hjsj"+i+") hjsj"+i+", ");
			concatSql.append(" wm_concat(a.hjmc"+i+") hjmc"+i+", ");
			concatSql.append(" wm_concat(a.fjdw"+i+") fjdw"+i+" ");
			
			caseSql.append(" case when a.hjnum='"+i+"' then a.hjsj else '' end hjsj"+i+", ");
			caseSql.append(" case when a.hjnum='"+i+"' then a.hjmc else '' end hjmc"+i+", ");
			caseSql.append(" case when a.hjnum='"+i+"' then a.fjdw else '' end fjdw"+i+" ");
			if(i < 4){
				hjqkSql.append(", ");
				concatSql.append(", ");
				caseSql.append(", ");
			}
		}
		// ============== 获奖情况 行转列 end ======================
		// 生成高级查询相关条件、条件值
		
		String searchTj = " and a.lbdm=? and a.xn=? and a.xq=? and a.xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.*, ");
		sql.append(" b.xm,b.sfzh,b.mzmc,a.xh,b.xymc,b.zymc,b.bjmc,b.xz,substr(b.rxrq,0,4) rxrq,b.zzmmmc,b.sjhm, ");
		sql.append(" c.bxkcs,d.jgkcs,e.cpzpm,f.cpzrs, ");
		sql.append(hjqkSql.toString());
		sql.append(" from xg_xszz_new_zzxmjgb a ");
		sql.append(" left join view_xsxxb b on a.xh=b.xh ");
		sql.append(" left join ( ");
		// ============== 必修课数量 begin ======================
		sql.append(" select xn,xq,xh,count(1) bxkcs from cjb ");
		sql.append(" where kclx='必修' ");
		sql.append(" group by xn,xq,xh ");
		// ============== 必修课数量 end ======================
		sql.append(" ) c on (a.xn=c.xn and a.xq=c.xq and a.xh=c.xh) ");
		sql.append(" left join ( ");
		// ============== 必修课及格数量 begin ======================
		sql.append(" select xn,xq,xh,count(1) jgkcs from cjb ");
		sql.append(" where cj >= 60 and kclx='必修' ");
		sql.append(" group by xn,xq,xh ");
		// ============== 必修课及格数量 end ======================
		sql.append(" ) d on (a.xn=d.xn and a.xq=d.xq and a.xh=d.xh) ");
		sql.append(" left join ( ");
		// ============== 综合考评排名 begin ======================
		sql.append(" select a.cpzpm,a.xh,a.xn,a.xq from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b ");
		sql.append(" on (a.xn=b.xn and a.xq=b.xq and a.xmdm=b.xmdm) ");
		sql.append(" where b.fjdm='N' ");
		// ============== 综合考评排名 end ======================
		sql.append(" ) e on (a.xn=e.xn and a.xq=e.xq and a.xh=e.xh) ");
		sql.append(" left join ( ");
		// ============== 综合考评排名总人数 begin ======================
		sql.append(" select count(1) cpzrs,a.xn,a.xq from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b ");
		sql.append(" on (a.xn=b.xn and a.xq=b.xq and a.xmdm=b.xmdm) ");
		sql.append(" where b.fjdm='N' group by a.xn,a.xq ");
		// ============== 综合考评排名总人数 end ======================
		sql.append(" ) f on (a.xn=f.xn and a.xq=f.xq) ");
		sql.append(" left join ( ");
		// ============== 获奖情况 begin ======================
		sql.append(" select a.xh, ");
		sql.append(concatSql.toString());
		sql.append(" from ( ");
		sql.append(" select a.xh, ");
		sql.append(caseSql.toString());
		sql.append(" from ( ");
		sql.append(" select a.xh,a.hjsj,a.hjmc,a.fjdw,(rank() over(partition by a.xh order by a.hjsj desc nulls last)) hjnum ");
		sql.append(" from xg_xsxx_new_hjqkb a ");
		sql.append(" ) a ) a group by a.xh ");
		// ============== 获奖情况 end ======================
		sql.append(" ) g on a.xh=g.xh ");
		sql.append(" where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:增加操作唯一性判断（学号，学年，学期,项目名称,申请时间）
	 * @作者：maxh
	 * @日期：2013-4-24 下午04:55:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return boolean 返回类型
	 * @throws
	 */
	public String checkExistForSave(ZzxmjgForm model) {
		String num = "";
		
		if(StringUtils.isBlank(model.getSqsj())){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
		}
		
		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_xszz_new_zzxmjgb where xh=? and pdxn=? and xmmc=? ");
		sql.append("and to_char(to_date(sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') = to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') ");
		
		if(model.getPdxq()!=null && !"".equals(model.getPdxq())){
			sql.append(" and pdxq=? ");
			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(),model.getSqsj(), model.getPdxq() }, "num");
		}else{
			sql.append(" and pdxq is null ");
			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(),model.getSqsj() }, "num");
		}
		return num;
	}

	/**
	 * 
	 * @描述:修改操作唯一性判断（学号，学年，学期,项目名称）
	 * @作者：maxh
	 * @日期：2013-4-24 下午04:55:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return boolean 返回类型
	 * @throws
	 */
	public String checkExistForUpdate(ZzxmjgForm model) {

		String num = "";
		
		if(StringUtils.isBlank(model.getSqsj())){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
		}
		
		StringBuilder sql = new StringBuilder("select count(1) num from xg_xszz_new_zzxmjgb where xh=? and pdxn=?  and xmmc=?  and guid<>?");
		sql.append(" and to_char(to_date(sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') = to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') ");
		if(model.getPdxq()!=null && !"".equals(model.getPdxq())){
			sql.append(" and pdxq=? ");
			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(), model.getGuid(),model.getSqsj(), model.getPdxq() }, "num");
		}else{

			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(), model.getGuid(),model.getSqsj() }, "num");
		}
		return num;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmjgb");
		super.setKey("guid");
		super.setClass(ZzxmjgForm.class);
	}

	/**
	 * 
	 * @描述:查看单个资助项目结果信息
	 * @作者：Administrator
	 * @日期：2013-4-25 下午06:39:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return Map<String,String> 返回类型
	 * @throws
	 */
	public Map<String, String> getOneZzxmjgList(String guid) {
		StringBuilder sql = new StringBuilder(); 
		 sql.append(" select ylzd1,ylzd2,ylzd3,ylzd4,ylzd5,lbmc,sqly,sqsj,xqmc,xn,pdxn,(select xqmc from xqdzb b where a.pdxq=b.xqdm) pdxqmc, xmmc,je ");
		 if("11799".equals(Base.xxdm)){
			sql.append(",(select shsj from (SELECT shsj, ywid FROM (SELECT ROW_NUMBER() OVER(PARTITION BY ywid ORDER BY shsj desc) r,a.* FROM xg_xtwh_shztb a)WHERE r = 1)");
			sql.append("  where ywid = a.LYLCYWID) lastshsj ");
		 }
		 sql.append(" from xg_view_xszz_new_zzxmjgb a where guid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
	}

	/**
	 * 通过学号查询资助项目结果
	 * 
	 * @param xh
	 * @return
	 */
	public List<String[]> getZzxmjgList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql
				.append("select a.xn,b.xqmc,a.xmmc,a.je,a.sqsj from xg_xszz_new_zzxmjgb a   ");
		sql.append("  left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xh=? ");
		sql.append(" order by xn desc,xq");
		String[] input = new String[] { xh };
		String[] output = new String[] { "xn", "xqmc", "xmmc", "je", "sqsj" };
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), input, output);
		return rs;
	}

	/**
	 * 
	 * @描述:根据项目名称，按学年、学期统计资助人数
	 * @作者：ligl
	 * @日期：2013-5-28 下午02:48:35
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select xydm,xn,xq,count(*) rs from (  ");
		sql
				.append(" select x.*,y.xydm from xg_xszz_new_zzxmjgb x,view_xsjbxx y where  x.xh=y.xh and x.xmmc=? ");
		sql.append(" ) group by xydm,xn,xq order by xn desc,xq desc");
		String[] inputValue = { xmmc };
		List<HashMap<String, String>> result = dao.getListNotOut(
				sql.toString(), inputValue);
		return result;
	}
	
	/**
	 * 
	 * @描述:通过学号查询资助项目结果
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgInfoList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc ");
		if ("12867".equals(Base.xxdm)) {
			sql.append(",to_char(to_date(a.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy\"年\"mm\"月\"') zzsj");
		}else {
			sql.append(",substr(a.sqsj,0,10) zzsj");
		}
		sql.append(" from xg_xszz_new_zzxmjgb a left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xh=? ");
		sql.append(" order by xn desc,xq desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	/** 
	 * @描述:根据业务id删除结果库记录
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-5 上午11:20:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * void 返回类型 
	 * @throws 
	 */
	public int delByYwid(String guid) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("delete from xg_xszz_new_zzxmjgb where sjly='1' and lylcywid=?");
		return dao.runDelete(sql.toString(), new String[]{guid});
		
	}
	/**
	 * 
	 * @描述:查询华中师范 大学个性化证书编码记录
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-4下午11:57:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZsbm(ZzxmjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select substr(zsbm,length(zsbm)-3,4) zsbm from VIEW_NEW_DC_XSZZ_ZZMXJG")
		.append(" where nj=? and xydm=? and pdxn=? and zsbm is not null order by zsbm desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getNj(),model.getXydm(),model.getPdxn()});
	}
	/**
	 * 
	 * @描述:根据项目名称获取项目代码
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-4 下午11:23:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXmdm(ZzxmjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select xmdm from xg_xszz_new_zzxmdmb where xmmc=?");
		return dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "xmdm");
	}
	
	/** 
	 * @描述:资助项目汇总信息列表
	 * @作者：wanghj
	 * @日期：2014-1-10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * void 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzxmhzList(ZzxmjgForm model, User user)	throws Exception {
		// 生成高级查询相关条件、条件值
		StringBuilder sql = new StringBuilder();
		SearchModel searchModel = model.getSearchModel();
		String[] xydm = searchModel.getSearch_tj_xy();
		searchModel.setSearch_tj_xy(new String[]{});
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		//浙江大学个性化
		if("10335".equals(Base.xxdm)){
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
			sql.append(" select * from (select t.xmmc,t.lbdm,(select lbmc from xg_xszz_new_zzxmlbb where lbdm = t.lbdm) lbmc,t.xn,t.xq,(select xqmc from xqdzb where xqdm = t.xq) xqmc,count(*) hjrs ");
			sql.append(" from (select * from (select t1.xmmc, t1.lbdm, t1.xn, t1.xq, t2.xydm from xg_xszz_new_zzxmjgb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh) a  where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ) t ");
			sql.append(" group by (t.xmmc, t.lbdm, t.xn, t.xq)) t3 where 1 = 1 ");	
		}else if("12036".equals(Base.xxdm)){
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
			sql.append(" select * from (select t.xmmc,t.lbdm,(select lbmc from xg_xszz_new_zzxmlbb where lbdm = t.lbdm) lbmc,t.xn,t.xq,(select xqmc from xqdzb where xqdm = t.xq) xqmc,count(*) hjrs ");
			sql.append(" from (select * from (select t1.xmmc, t1.lbdm, t1.xn, t1.xq, t2.xydm from xg_xszz_new_zzxmjgb t1 ");
			sql.append(" left join view_xsjbxx t2 on t1.xh = t2.xh) a  where 1 = 1 ");
			if(xydm !=null && xydm.length> 0){
				StringBuffer xySql = new StringBuffer();
				int length = xydm.length;
				xySql.append(" and a.xydm in ( ");
				for(int i=0;i<length-1;i++ ){
					xySql.append("'"+xydm[i]+"',");
				}
				xySql.append("'"+xydm[length-1]+"')");
				sql.append(xySql);
			}
			sql.append(searchTjByUser);
			sql.append(" ) t ");
			sql.append(" group by (t.xmmc, t.lbdm, t.xn, t.xq)) t3 where 1 = 1 ");
		}else{
			sql.append("select * from (select t.xmmc,t.lbdm,(select lbmc from xg_xszz_new_zzxmlbb where lbdm = t.lbdm) lbmc,t.xn,t.xq,(select xqmc from xqdzb where xqdm=t.xq) xqmc,count(*) hjrs");
			sql.append(" from xg_xszz_new_zzxmjgb t group by (t.xmmc,t.lbdm,t.xn,t.xq)) a where 1=1");
		}
			sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//查询学生职务
	public String getZwForXh(String xh){
		String sql = "select a.xh,wm_concat(zwmc) zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid where a.zzzt='1' and xh = ? group by a.xh";
		return dao.getOneRs(sql, new String[]{xh}, "zwmc");
	}
	/**
	 * 
	 * @描述:查询资助结果周期列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-18 下午03:18:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getzzjgZqList() {

		StringBuilder sb = new StringBuilder();
		sb.append(" select xn ");
		sb.append(" from (select  distinct a.xn from xg_xszz_new_zzxmjgb a order by a.xn) a ");
		return dao.getListNotOut(sb.toString(), new String[]{});		
	}
	/**
	 * 
	 * @描述:查询指定学年学生资助项目结果列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-18 下午03:43:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgOfXnList(String lyxn,String mbxn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc from xg_xszz_new_zzxmjgb a   ");
		sql.append("  left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xn=? ");
		sql.append(" and   not exists (select 1 from xg_xszz_new_zzxmjgb c");
		sql.append(" where c.xn=? and a.xh=c.xh and a.xq=c.xq and a.xmmc=c.xmmc ) ");
		sql.append(" order by xn desc,xq");
		return dao.getListNotOut(sql.toString(), new String[] { lyxn,mbxn });
	}
	
	public ZzxmjgForm getModel(String guid) throws Exception {
		ZzxmjgForm zzxmjgForm = new ZzxmjgForm();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.xqmc,c.lbmc ");
		// =============== 班级评议 < =============
		if(SFBJPY_Y){
			sql.append(" ,decode(t3.shzt,'1', '同意申请', '0', '不同意申请','') bjpyjgshztmc,t3.pyhsj bjpyjgpyhsj,t3.pyhdd bjpyjgpyhdd,t3.pyyj bjpyjgpyyj, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd1 end) bjpyxzcyxms, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd2 end) bjpyxzdbxms ");			
		}
		// =============== 班级评议 > =============		
		sql.append("  from xg_xszz_new_zzxmjgb a   ");
		sql.append("  left join xqdzb b on a.xq=b.xqdm left join xg_xszz_new_zzxmlbb c on a.lbdm=c.lbdm ");

		// =============== 班级评议 < =============
		if(SFBJPY_Y){
			sql.append(" left join (select * from xg_xszz_new_xszz_bjpyxzpyjg where tjzt='1') t3 on a.lylcywid=t3.sqid ");
		}
		// =============== 班级评议 > =============
		sql.append(" where a.guid=? ");
		sql.append(" order by a.xn desc,a.xq");
		return getModel(zzxmjgForm, sql.toString(), new String[]{guid});
	}
	
	/**
	 * 查询教职工信息
	 */
	public Map<String, String> getJzgInfo(User user){
		String sql = "select a.*,(select bmmc from zxbz_xxbmdm_ls b where a.szbm=b.bmdm) bmmc from yhb a where yhm = ? ";
		return dao.getMapNotOut(sql, new String[]{user.getUserName()});
	}
	
	public List<HashMap<String,String>> getZzxmjgHzList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh, t2.xm, t1.xmmc,t1.zje, t2.nj,t2.xymc,   ");
		sql.append("  t2.zymc, t2.bjmc,t2.sfzh,t2.MZ,t2.sjhm, yhkh  ");
		sql.append("   from (select xh, replace(wm_concat(a.pdxn || '学年' ||  ");
		sql.append("  b.xqmc || '获得' || a.xmmc), ';', ',') xmmc, sum(nvl(je, 0)) zje ");
		sql.append("  from xg_xszz_new_zzxmjgb a left join xqdzb b on a.pdxq = b.xqdm ");
		sql.append("  group by xh) t1 inner join view_xsjbxx t2 on t1.xh = t2.xh  ");
		sql.append(" order by xh ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	//山东畜牧兽医(國家助學金等）汇总获取学生信息以及项目金额list
	public List<HashMap<String, String>> getShzxjHzbxxList(String values,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t1.xm,decode(t1.xb, '1', '男', '2', '女', '男', '男', '女', '女') xb,");
		sql.append(" t.je xmje,t3.bjmc, t3.xymc xy,(select xxmc from xtszb) xxmc,");
		sql.append(" decode(t1.hkxz, 1, '城镇', 2, '农村') hk, decode(substr(t.xmmc, 0, 2), '一等', '1', '') szdc1, ");
		sql.append(" decode(substr(t.xmmc, 0, 2), '二等', '1', '') szdc2, decode(substr(t.xmmc, 0, 2), '三等', '1', '') szdc3,");
		sql.append(" t1.sfzh,t3.zymc,t1.xh, t1.rxrq,(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_xszz_new_zzxmjgb t,xsxxb t1 , view_njxyzybj t3");
		sql.append(" where  t.xh = t1.xh and t1.bjdm = t3.bjdm  and t.xmmc = ? and t.xn = ? and t.xq = ?");	
		return dao.getListNotOut(sql.toString(), new String[]{values});
	}
	
	//山东畜牧兽医社会助学金获取总人数，总金额 map
	public HashMap<String, String> getshzxjTotal(String values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select  count(*) zrs,sum(t1.je) xmze  from xg_xszz_new_zzxmlbb t,xg_xszz_new_zzxmjgb t1 ");
		sql.append(" where t.lbdm = t1.lbdm and t.lbmc = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{values});
	}
	
	//山东畜牧兽医国家励志奖学金获取联系电话 map
	public HashMap<String, String> getlxfs(String value){
		StringBuffer sql = new StringBuffer();
		sql.append("select lxdh from fdyxxb where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{value});
	}
	
	//国家励志奖学金申请表汇总模板
	public List<HashMap<String, String>> getLzjHzXsxx(String[] values){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,t1.hjqk from");
		sql.append("(select t1.xh,t1.xm, decode(t1.xb, '1', '男', '2', '女', '男', '男', '女', '女') xb,t1.csrq," +
				"(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc,");
		sql.append("t1.rxrq, t1.sfzh, t1.lxdh,(select t3.yhmc from DMK_YH t3 where t3.yhdm = t1.yhdm) khyh, " +
				"t1.yhkh,t1.xymc xy,t1.zymc,t1.bjmc,t1.zzmmmc" +
				",t5.jthk,");
		sql.append(" t5.jtrs,(t5.jtrjysr) * (t5.jtrs) jtyzsr,t5.jtrjysr, t5.jtsrly,t5.jtdz,t1.yzbm, t.sqly,");
		sql.append("t1.xm sqr, to_char(to_date(t.sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') sqsj");
		sql.append(" from xg_xszz_new_zzxmjgb t, view_xsjbxx t1, XG_XSZZ_NEW_JTQKDCB t5 ");
		sql.append("where t.xh = t1.xh  and t1.xh = t5.xh  and t.guid in (");
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append("))t left join ");
		sql.append("(select WM_CONCAT(xmmcs) hjqk, xh from (select a.xqmc || '：' || replace(a.xmmcsTemp, ';', '；') xmmcs,xh");
		sql.append(" from (select xqmc,xh,WM_CONCAT(xmmc) over(partition by xqmc order by xqmc) xmmcsTemp,"
                            + " row_number() over(partition by xqmc order by xqmc desc) rn");
		sql.append(" from (select t1.xn || t2.xqmc xqmc, t1.xmmc, t1.xh from xg_pjpy_new_pjjgb t1 left join xqdzb t2 "
                                 +  "on t1.xq = t2.xqdm order by t1.xn asc, sqsj asc) a) a  where rn = 1)");
		sql.append(" group by xh) t1");
		sql.append("   on t.xh = t1.xh ");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//山东畜牧兽医(社會助學金等）汇总获取学生信息以及项目金额list
	public HashMap<String, String> getShzxjhz(String values,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		String[]inputvalues=new String[]{values};
		sql.append("select rownum bh, t1.xm,decode(t1.xb, '1', '男', '2', '女', '男', '男', '女', '女') xb,");
		sql.append(" t.je xmje,t3.bjmc, t3.xymc xy,(select xxmc from xtszb) xxmc,");
		sql.append(" decode(t1.hkxz, 1, '城镇', 2, '农村') hk, decode(substr(t.xmmc, 0, 2), '一等', '1', '') szdc1, ");
		sql.append(" decode(substr(t.xmmc, 0, 2), '二等', '1', '') szdc2, decode(substr(t.xmmc, 0, 2), '三等', '1', '') szdc3,");
		sql.append(" t1.sfzh,t3.zymc,t1.xh, t1.rxrq,(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_xszz_new_zzxmjgb t,xsxxb t1 , view_njxyzybj t3,xg_xszz_new_zzxmlbb t4");
		sql.append(" where  t.xh = t1.xh and t1.bjdm = t3.bjdm  and  t4.lbdm = t.lbdm  and t4.lbmc  = ?  ");	
		if(!xn.trim().equals("")){
			sql.append("and t.xn = ?");
		}
		
		if(!xq.trim().equals("")){
			sql.append("and t.xq = ?");
		}
		if(xn.trim().equals("")&&!xq.trim().equals("")){
			return dao.getMapNotOut(sql.toString(), new String[]{values,xq});
		}else if(!xn.trim().equals("")&& xq.trim().equals("")){
			return dao.getMapNotOut(sql.toString(), new String[]{values,xn});
		}else if(xn.trim().equals("")&& xq.trim().equals("")){
			return dao.getMapNotOut(sql.toString(), new String[]{values});
		}
		return dao.getMapNotOut(sql.toString(), new String[]{values,xn,xq});
	}
	
	//社会助学金汇总导出
	public List<HashMap<String, String>> getDcList(ZzxmjgForm t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum bh, t1.xm,decode(t1.xb, '1', '男', '2', '女', '男', '男', '女', '女') xb,");
		sql.append(" t.je xmje,t3.bjmc, t3.xymc xy,(select xxmc from xtszb) xxmc,");
		sql.append(" decode(t1.hkxz, 1, '城镇', 2, '农村') hk, decode(substr(t.xmmc, 0, 2), '一等', '1', '') szdc1, ");
		sql.append(" decode(substr(t.xmmc, 0, 2), '二等', '1', '') szdc2, decode(substr(t.xmmc, 0, 2), '三等', '1', '') szdc3,");
		sql.append(" t1.sfzh,t3.zymc,t1.xh, t1.rxrq,(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_xszz_new_zzxmjgb t,xsxxb t1 , view_njxyzybj t3,xg_xszz_new_zzxmlbb t4");
		sql.append(" where  t.xh = t1.xh and t1.bjdm = t3.bjdm  and  t4.lbdm = t.lbdm   ");	
		sql.append(searchTjByUser);
		sql.append("");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	// TODO 自动生成方法存根
   }
	
	/**
	 * 
	 * @描述:获取勤工助学list
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-6 上午11:53:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQgzxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.gwmc, t2.yrdwmc,t1.xn ");
		sql.append("from xg_qgzx_xsgwxxb t, xg_qgzx_gwxxb t1, xg_qgzx_yrdwdmb t2 ");
		sql.append(" where t.gwdm = t1.gwdm ");
		sql.append(" and t.xh = ?  and t1.yrdwdm = t2.yrdwdm order by t2.yrdwmc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:获取项目奖项list
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-6 下午03:11:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getMjxList(String xh,String xmmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select xn,xmmc from xg_xszz_new_zzxmjgb where xmmc like '%' || ? || '%' ");
		sql.append("and xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xmmc,xh});
	}
	
	/**
	 * 
	 * @描述:是否办理助学贷款
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-7 下午02:15:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSfzxDk(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) cs from xg_zxdk_xydkjgb where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	public HashMap<String, String> getSfkns(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.dcmc  from xg_xszz_new_knsjgb t,xg_xszz_new_kndcdmb t1 where t.rddc = t1.dcdm and t.xh = ? and t.xn = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	public HashMap<String, String>  getSfxs(){
		StringBuilder sql = new StringBuilder();
		sql.append("select dqnd from xtszb");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:获取拼音码
	 * @作者：张昌路[工号：1206]
	 * @日期：2015-7-8 上午09:53:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPinyin(String str){
		StringBuilder sql = new StringBuilder();
		sql.append("select  GETHZPY.GETHZFULLPY(?) py FROM DUAL");
		return  dao.getOneRs(sql.toString(), new String[]{str}, "py");
	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专资助项目汇总
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-27 下午04:40:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDxscbInfoList(ZzxmjgForm t, User user) throws Exception {
		
		String searchTj = " and a.lbdm=? and a.xn=? and a.xq=? and a.xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.*,t3.xm,t3.xb,t3.sfzh,t3.xymc,t3.bjmc,t3.xydm,t3.bjdm,t3.zd5,t2.mc jfdcmc,t2.grsjje,t2.mzzzje, ");
		sql.append(" (select mc from XG_RCSW_YLBX_CBLXB where dm = t1.zd12) cblxmc, ");
		sql.append(" (select mc from xg_rcsw_ylbx_bsddyljgb where dm = t1.zd2) zzlxmc ");
		sql.append(" from xg_xszz_new_zzxmjgb a ");
		sql.append(" left join xg_rcsw_ylbx_jgb t1 ");
		sql.append(" on a.xh = t1.xh ");
		sql.append(" left join xg_rcsw_ylbx_ylczbzbsb t2 ");
		sql.append(" on t1.zd1 = t2.dm ");
		sql.append(" left join view_xsxxb t3 ");
		sql.append(" on t1.xh = t3.xh )a");
		sql.append(" where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	public HashMap<String, String> getZzxmNumTotalMoney(String xh){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paramater = new ArrayList<String>();
		sql.append("select sum(nvl(t.je,0)) zje,count(1) sl from  xg_xszz_new_zzxmjgb t where t.xh = ?");
		paramater.add(xh);
		return dao.getMapNotOut(sql.toString(), paramater.toArray(new String[]{}));
	}
	/**
	 * @描述:TODO(辽宁机电职业技术学院个性化)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-8-5 下午03:32:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getLnjdzyjsxyList(ZzxmjgForm t, User user) throws Exception {
		String searchTj = " and a.lbdm=? and a.xn=? and a.xq=? and a.xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.xmmc,t1.je,t1.sqsj,t1.sqly,t1.lbdm,t1.pdxn,t2.xm,t2.SFZH,t2.xydm,t2.bjdm,t2.xymc,t2.zymc,t2.bjmc,t2.xb,t2.nj,t2.mzmc,t2.RXRQ,t2.jtdzxx jtdz,t2.SJHM,t2.yhkh, ");
		sql.append(" case when xmmc like '%一等%' then '1'  when xmmc like '%二等%' then '2' else '' end zzdj ");
		sql.append(" from xg_xszz_new_zzxmjgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh )a where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 专业排名
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-14 上午09:11:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sxn
	 * @param zydm
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXsZyBxPm(String sxn, String zydm, String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select a.*, rank() over(order by to_number(nvl(a.fs, 0)) desc) pm ");
        sql.append(" from (select a.* from (select 0 fs, a.zydm, a.xh from xsxxb a ");
        sql.append(" where not exists (select 1 from cjb b where a.xh = b.xh and b.xn = ?) union all select sum(nvl(cj, 0)) fs, d.zydm, c.xh ");
        sql.append(" from cjb c left join xsxxb d on c.xh = d.xh where c.xn = ? and c.kcxz like '%必修%' group by c.xh, d.zydm) a where a.zydm = ?) a) a ");
        sql.append(" where a.xh = ? ");
		return  dao.getOneRs(sql.toString(), new String[]{sxn,sxn,zydm,xh}, "pm");
	}
	
	/**
	 * 
	 * @描述: 专业总人数
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-14 上午09:16:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZyrs(String zydm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) n from xsxxb where zydm = ? ");
		return  dao.getOneRs(sql.toString(), new String[]{zydm}, "n");
	}
	
	/**
	 * 
	 * @描述: 必修课平均分
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-14 上午09:27:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sxn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBxPjcj(String xh, String sxn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(avg(cj),0) pjcj from cjb where xh = ? and xn = ? and kcxz like '%必修%' ");
		return  dao.getOneRs(sql.toString(), new String[]{xh,sxn}, "pjcj");
	}
	
	/**
	 * 
	 * @描述: 最低分
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-14 上午09:38:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sxn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZdf(String xh, String sxn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select min(to_number(cj)) minCj from cjb where xh = ? and xn = ? ");
		return  dao.getOneRs(sql.toString(), new String[]{xh,sxn}, "minCj");
	}
	
	/**
	 * 
	 * @描述: 最低分课程名称
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-14 上午10:00:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sxn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZdfkmmc(String xh, String sxn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select kcmc from cjb where xh = ? and xn = ? and cj = (select min(to_number(cj)) minCj from cjb where xh = ? and xn = ?) ");
		return  dao.getOneRs(sql.toString(), new String[]{xh,sxn,xh,sxn}, "kcmc");
	}
	
	/**
	 * 
	 * @描述: 上海体育资助项目汇总表总金额
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-14 下午05:14:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String[] getZje_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append("  select to_char(nvl(sum(je),0),'9999999999990.99') zje,count(1) zrs from xg_xszz_new_zzxmjgb  where regexp_replace(je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null");
		sql.append(" and xn||xq||xmmc||lbdm in(");
		for (int i = 0; i < param.length; i++) {
			paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), new String[]{"zje","zrs"});
	}
	
	/**
	 * 
	 * @描述:上海体育资助项目汇总表专业金额
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-14 下午05:34:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getzyhz_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select a.zydm,nvl(b.zyje,'0.00' ) zyje,nvl(c.zyrs,0) zyrs from ");
		sql.append(" (select distinct zydm from view_njxyzybj )a");
		sql.append(" left join");
		sql.append(" (select to_char(sum(nvl(a.je,0)),'9999999999990.99')  zyje,a.zydm from ");
		sql.append(" (select t1.zydm,t.je from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh where regexp_replace(t.je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null");
		
	    sql.append(" and t.xn||t.xq||t.xmmc||t.lbdm in(");
	    for (int i = 0; i < param.length; i++) {
	    	paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
	    sql.append(")");
		sql.append(" ) a  group by a.zydm) b");
		sql.append(" on a.zydm = b.zydm");
		sql.append(" left join");
		sql.append(" (select a.zydm,count(1) zyrs from ");
		sql.append(" (select  t1.zydm from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" where t.xn||t.xq||t.xmmc||t.lbdm in(");
		for (int i = 0; i < param.length; i++) {
		    	paraList.add(param[i]);
				sql.append("?");
				if(i != param.length-1){
					sql.append(",");
				}
		}
		sql.append(")");
		sql.append(" ) a  group by a.zydm)c");
		sql.append(" on a.zydm = c.zydm");
		sql.append(" order by a.zydm");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:上海体育资助项目汇总表年级金额
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午09:25:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjhz_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select a.nj,nvl(b.njje,'0.00' ) njje,nvl(c.njrs,0) njrs from ");
		sql.append(" (select distinct nj from view_njxyzybj )a");
		sql.append(" left join");
		sql.append(" (select to_char(sum(nvl(a.je,0)),'9999999999990.99')  njje,a.nj from ");
		sql.append(" (select t1.nj,t.je from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh where regexp_replace(t.je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null ");
	    sql.append(" and t.xn||t.xq||t.xmmc||t.lbdm in(");
	    for (int i = 0; i < param.length; i++) {
	    	paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
	    sql.append(" )");
		sql.append(" ) a  group by a.nj) b");
		sql.append(" on a.nj = b.nj");
		sql.append(" left join");
		sql.append(" (select a.nj,count(1) njrs from ");
		sql.append(" (select t1.nj from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh ");
		sql.append(" where t.xn||t.xq||t.xmmc||t.lbdm in(");
	    for (int i = 0; i < param.length; i++) {
	    	paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		sql.append(" ) a  group by a.nj)c");
		sql.append(" on a.nj = c.nj");
		sql.append(" order by a.nj");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 上海体育学院专业列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午10:13:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getxyzyList_shty(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.zygs from ");
		sql.append(" (");
		sql.append(" select distinct t.xymc,t.xydm,t.zydm,t.zymc  from view_njxyzybj t  order by t.xydm,t.zydm)a");
		sql.append(" left join");
		sql.append(" (");
		sql.append(" select xymc,xydm,count(1) zygs from (select distinct t.xymc,t.xydm,t.zydm,t.zymc  from view_njxyzybj t  order by t.xydm,t.zydm) t group by xymc,xydm)b");
		sql.append(" on a.xydm = b.xydm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:上海体育学院年级列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午10:18:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct nj from view_njxyzybj order by nj");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 上海体育年级学院资助项目金额汇总主查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午10:21:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjxyzy_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select a.*,nvl(b.mhje,'0.00' ) mhje,nvl(c.mhrs,0) mhrs from ");
		sql.append(" (select distinct t.xymc,t.xydm,t.nj,t.zydm,t.zymc  from view_njxyzybj t)a");
		sql.append(" left join ");
		sql.append(" (select to_char(sum(nvl(a.je,0)),'9999999999990.99')  mhje,a.xydm,a.nj,a.zydm from ");
		sql.append(" (select t1.xymc,t1.xydm,t1.nj,t1.zymc,t1.zydm,t.je from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh where regexp_replace(t.je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null ");
		sql.append(" and t.xn||t.xq||t.xmmc||t.lbdm in(");
		 for (int i = 0; i < param.length; i++) {
		    	paraList.add(param[i]);
				sql.append("?");
				if(i != param.length-1){
					sql.append(",");
				}
		}
		sql.append(" )");
		
		sql.append(" ) a  group by a.xymc,a.xydm,a.nj,a.zymc,a.zydm )b");
		sql.append(" on a.nj = b.nj and a.zydm = b.zydm");
		sql.append(" left join");
		sql.append(" (select a.nj,a.xydm,a.zydm,count(1) mhrs from ");
		sql.append(" (select t1.nj,t1.xydm,t1.zydm from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh ");
		sql.append(" where t.xn||t.xq||t.xmmc||t.lbdm in(");
		 for (int i = 0; i < param.length; i++) {
		    	paraList.add(param[i]);
				sql.append("?");
				if(i != param.length-1){
					sql.append(",");
				}
		}
		sql.append(" )");
		sql.append(" ) a  group by a.nj,a.xydm,a.zydm )c");
		sql.append(" on a.nj = c.nj and a.zydm =c.zydm");
		sql.append(" order by a.nj,a.xydm,a.zydm");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}) );
	}
	
	/**
	 * 
	 * @描述: 上海体育学校获取学院列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 下午02:20:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.xymc, a.xydm, a.zygs");
		sql.append(" from (select a.*, b.zygs");
		sql.append(" from (select distinct t.xymc, t.xydm, t.zydm, t.zymc");
		sql.append(" from view_njxyzybj t");
		sql.append(" order by t.xydm, t.zydm) a");
		sql.append(" left join (select xymc, xydm, count(1) zygs");
		sql.append(" from (select distinct t.xymc, t.xydm, t.zydm, t.zymc");
		sql.append(" from view_njxyzybj t");
		sql.append(" order by t.xydm, t.zydm) t");
		sql.append(" group by xymc, xydm) b");
		sql.append(" on a.xydm = b.xydm) a where a.zygs >0");
		sql.append(" order by xydm");
	    return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 资助项目名称
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-17 下午04:19:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZzxmMc(String paras){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmmc from xg_xszz_new_zzxmjgb t where  t.xn || t.xq || t.xmmc || t.lbdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{paras}, "xmmc");
	}
	
	/** 
	 * @描述:导入插入数据库(青岛酒店职业管理技术学院)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-2 下午05:53:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * int[] 返回类型 
	 * @throws 
	 */
	public int[] DrForInsert(List<String[]> params) throws SQLException{
		String sql = "insert into xg_xszz_new_zzxmjgb(xh,xn,xq,pdxn,pdxq,lbdm,xmmc,je) values(?,?,?,?,?,?,?,?)";
		return dao.runBatch(sql, params);
	}
	
	/** 
	 * @描述:验证学号是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-2 下午05:54:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkXhExist(ZzxmjgForm form) {
		String sql = "select count(xh) num from view_xsbfxx where xh = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{form.getXh()}, "num"))>0;
	}
	
	/** 
	 * @描述:验证身份证号(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 上午08:36:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkSfz(ZzxmjgForm form){
		boolean result = true;
		String sql = "selecct count(sfzh) num from view_xsbfxx where sfzh = ?";
		result = Integer.valueOf(dao.getOneRs(sql, new String[]{form.getSfzh()}, "num"))>0;
		if(result){
			String sqll = "select xh from view_xsbfxx where sfzh = ?";
			String xh = dao.getOneRs(sqll, new String[]{form.getSfzh()}, "xh");
			form.setXh(xh);
			return true;
		}else{
			return false;
		}
	}
	
	/** 
	 * @描述:验证项目类别名称(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 上午08:44:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkXmlb(ZzxmjgForm form){
		String sql = "select count(1)num from xg_xszz_new_zzxmlbb where lbmc = ?";
		boolean result = Integer.valueOf(dao.getOneRs(sql, new String[]{form.getLbmc()}, "num"))>0;
		if(result){
			String sqll = "select lbdm from xg_xszz_new_zzxmlbb where lbmc = ?";
			String lbdm = dao.getOneRs(sqll, new String[]{form.getLbmc()}, "lbdm");
			form.setLbdm(lbdm);
			return true;
		} else{
		  return false;
		}
	}
	
	/** 
	 * @描述:验证学号和身份证是否相符(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 上午10:34:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkXhAndSfzh(ZzxmjgForm form){
		String sql = "select count(1) num from view_xsbfxx where xh = ? and sfzh = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{form.getXh(),form.getSfzh()}, "num"))>0;
	}
	
	/** 
	 * @描述:验证学期(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 上午10:55:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkXq(ZzxmjgForm form,String xqType){
		String sql = "select count(1) num from xqdzb where xqmc = ?";
		String mc;
		if(xqType == "xq"){
			 mc = form.getXqmc();
		}else{
			 mc = form.getPdxqmc();
		}
		boolean result = Integer.valueOf(dao.getOneRs(sql, new String[]{mc}, "num"))>0;
		if(result) {
			String sqll = "select xqdm from xqdzb where xqmc = ?";
			String xqdm = dao.getOneRs(sqll, new String[]{mc}, "xqdm");
			if(xqType == "xq"){
				form.setXq(xqdm);
			}else{
				form.setPdxq(xqdm);
			}
			return true;
		}else {
			return false;
		}		
	}
	
	public List<HashMap<String,String>> getZzjgList(String xh,String n){
		String sql = "select * from (select * from xg_xszz_new_zzxmjgb where xh=? order by xn desc,xq desc,sqsj desc) where rownum <=? ";
		return dao.getListNotOut(sql, new String[]{xh,n});
	}

	/** 
	 * 浙江旅游爱心基金
	 */
	public HashMap<String, String> getZjlyAxjjMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_axjjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * 浙江旅游-孤儿爱心补助
	 */
	public HashMap<String, String> getZjlyGeaxMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_geaxbzjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * 浙江旅游新生校服费用减免
	 */
	public HashMap<String, String> getZjlyXfjmMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_xffyjmjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * 浙江旅游新生生活用品费用减免
	 */
	public HashMap<String, String> getZjlyShfjmMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_shybjmjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * 浙江旅游-商业保险补助
	 */
	public HashMap<String, String> getZjlySybxbzMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_sybxbzjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * 浙江旅游交通补助
	 */
	public HashMap<String, String> getZjlyJtbzMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_jtbzjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * 浙江旅游-技能考证费补助
	 */
	public HashMap<String, String> getZjlyJnkzfbzmapMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,to_char(a.f_fzsj1,'yyyy-mm-dd') fzsj1,to_char(a.f_fzsj2,'yyyy-mm-dd') fzsj2,to_char(a.f_fzsj3,'yyyy-mm-dd') fzsj3 from zfsoft_bpmx.view_xszz_jnkzfybzjgb a where a.f_xh=? and a.f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * 浙江旅游-学费减免
	 */
	public HashMap<String, String> getZjlyXfjmmapMap(String xh, String xn) {	
			StringBuilder sql = new StringBuilder();
			sql.append("select * from zfsoft_bpmx.view_xszz_xfjmjgb where f_xh=? and f_xn=?");
			return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
		}
	/**
	 *浙江旅游--国家或学院助学金
	 */
	public HashMap<String, String> getZjlyZxjmapMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_knzz_zxjpdjg where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//温州大学及格课程
	public HashMap<String,String> getWzdxbxkms(String xh,String xn ){
		StringBuilder sql =  new StringBuilder();
		sql.append("select nvl(round(sum(case when cj >=60  then '1' end),0),0)bxkjgms ,count(xh) as bxkms from cjb  where " +
				" kcxz like '%必修%'and xh = ? and xn = ?   group by xh");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	/**
	 * @description	： 江西陶瓷个性化
	 * @author 		： CP（1352）
	 * @date 		：2017-12-5 下午04:43:41
	 * @param t
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getJxtczzxmList(ZzxmjgForm t,
			User user) {
		// 生成高级查询相关条件、条件值
		String searchTj = " and lbdm=? and xn=? and xq=? and xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.fdyxmdh,decode(c.ylzd8,1,'是',0,'否') sfjdlk from VIEW_NEW_DC_XSZZ_ZZMXJG a  ");
		sql.append(" left join (select bjdm,wm_concat(fdyxmdh) fdyxmdh from (select a.zgh, b.xm|| b.lxdh fdyxmdh, a.bjdm from fdybjb a left join fdyxxb b on a.zgh = b.zgh) group by bjdm) b on a.bjdm=b.bjdm ");
		sql.append(" left join xg_xszz_new_jtqkdcb c on a.xh=c.xh");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	/**
	 * @description	： 温州大学个性化
	 * @author 		： JZ（1529）
	 * @date 		：2017-12-5 下午04:43:41
	 * @param t
	 * @param user
	 * @return
	 */
		public List<HashMap<String, String>> getWzdxzzxmList(ZzxmjgForm t,
				User user) {
			// 生成高级查询相关条件、条件值
			String searchTj = "  and xn=?  and xmmc=? ";
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
					"xydm", "bjdm");
			String[] inputV = {t.getXn(),t.getXmmc()};
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select a.*,c.bxkcs,d.jgkcs,f.xnzypm,f.xnzfpm,f.zrs from VIEW_NEW_DC_XSZZ_ZZMXJG a  ");
			sql.append(" left join ( ");
			// ============== 必修课数量 begin ======================
			sql.append(" select xn,xh,count(1) bxkcs from cjb ");
			sql.append(" where kclx='必修' or kcxz like '%必修%' ");
			sql.append(" group by xn,xh ");
			// ============== 必修课数量 end ======================
			sql.append(" ) c on (a.xn=c.xn and a.xh=c.xh) ");
			sql.append(" left join ( ");
			// ============== 必修课及格数量 begin ======================
			sql.append(" select xn,xh,count(1) jgkcs from cjb ");
			sql.append(" where cj >= 60 and (kclx='必修' or kcxz like '%必修%') ");
			sql.append(" group by xn,xh ");
			// ============== 必修课及格数量 end ======================
			sql.append(" ) d on (a.xn=d.xn  and a.xh=d.xh) ");
			sql.append(" left join ( ");
			// ============== 综合考评排名总人数 brgin ======================
			sql.append("select a.xn,a.xh,a.bjdm,");
			sql.append("  nvl(min(to_number(a.xnzfpm)), '') xnzfpm,");
			sql.append(" nvl(min(to_number(a.xnzypm)), '') xnzypm,");
			sql.append(" nvl(max(to_number(b.zrs)), '') zrs");
			sql.append("  from (select a.xn,");
			sql.append("               a.xq,");
			sql.append("               b.xh,");
			sql.append("               c.bjdm,");
			sql.append("               min(case");
			sql.append("                     when a.xmmc = '综测总分' then");
			sql.append("                      b.bjpm");
			sql.append("                     else");
			sql.append("                      null");
			sql.append("                   end) xnzfpm,");
			sql.append("               min(case");
			sql.append("                     when a.xmmc = '智育分' then"); 
			sql.append("                      b.bjpm");
			sql.append("                     else");
			sql.append("                      null");
			sql.append("                   end) xnzypm");
			sql.append("          from xg_zhcp_zcxmb a");
			sql.append("          left join xg_zhcp_zcfsb b");
			sql.append("            on a.xmdm = b.xmdm");
			sql.append("          left join xsxxb c");
			sql.append("            on b.xh = c.xh");
			sql.append("         where  a.xq <> 'on'");
			sql.append("           and (a.xmmc = '综测总分' or a.xmmc = '智育分')");
			sql.append("         group by a.xn, a.xq,b.xh,c.bjdm) a");
			sql.append("  left join (select xn, xq ,bjdm,count(1) zrs");
			sql.append("               from xg_pjpy_new_cpmdb");
			sql.append("              where xn || xq || bjdm in");
			sql.append("                    (select xn || xq || bjdm");
			sql.append("                       from xg_pjpy_new_cpmdb )");
			sql.append("              group by xn, xq,bjdm) b");
			sql.append("    on b.xn = a.xn");
			sql.append("   and b.xq = a.xq");
			sql.append("   and a.bjdm = b.bjdm");
			sql.append(" group by a.xn,a.xh,a.bjdm");
			sql.append("");
			sql.append("");
			sql.append("");
			// ============== 综合考评排名总人数 end ======================
			sql.append(" ) f on (a.xn=f.xn and a.xh=f.xh) ");
			sql.append(" ) t where 1=1 ");
			sql.append(searchTj);
			sql.append(searchTjByUser);
			return dao.getListNotOut(sql.toString(), inputV);
		}
	/**
	 * @description	： 重庆工商,取其他资助金额（个性化）
	 * @author 		： JZ（1529）
	 * @date 		：2017-12-13 下午05:13:41
	 * @param t
	 * @param user
	 * @return
	 */
	public HashMap<String,String> getQtzzje(String xh,String xn ){
		StringBuilder sql =  new StringBuilder();
		sql.append("select sum(je) as qtzzje from XG_XSZZ_NEW_ZZXMJGB where xh = ? and xn = ?  and  " +
				"(xmmc = '国家奖学金'  or xmmc = '国家励志奖学金' or xmmc ='国家助学金（一等）'or xmmc = '国家助学金（二等）' or xmmc = '国家助学金（三等）')");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	public HashMap<String,String> getKncsAndJe(String xh,String xn ){
		StringBuilder sql =  new StringBuilder();
		sql.append("select count(xmmc)as knbzcs,sum(je)as knbzje from XG_XSZZ_NEW_ZZXMJGB where xmmc  = '临时困难补助' and xh = ?and xn =?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//江西陶瓷的审核意见
	public List<HashMap<String, String>> getShyjList(String xh, String xn, String xq, String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select shyj, xm, shsj, mc from (select t1.xh,t1.xn, t1.xq, t1.xmmc, t2.shyj, t2.shsj, t2.xm, " +
				"t2.shr, t3.xmdm, t2.mc, row_number() over(partition by t1.xh, t1.xn, t1.xq, t3.xmdm, t1.xmmc," +
				" t2.mc order by t2.shsj desc) rn  from xg_xszz_new_zzxmjgb t1 left join (select t2.*, t5.xm, t3.* " +
				"from xg_xtwh_shztb t2");
		sql.append(" left join fdyxxb t5 on t2.shr = t5.zgh   " +
				"left join xg_xtwh_spgw t3  on t2.gwid = t3.id) t2 on t2.ywid = t1.lylcywid " +
				" left join xg_xszz_new_zzxmsqb t3  on t3.guid = t1.lylcywid  where t1.xh = ?" +
				" and t1.xn = ?  and t1.xq = ? and t3.xmdm = ?) where rn = 1 order by shsj");
		String[] inputValue = { xh,xn,xq,xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @description	： TODO
	 * @author 		： lj（1282）
	 * @date 		：2018-4-18 下午03:55:28
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getGjjxjdc(ZzxmjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select a.*,'居民身份证' sfzlxmc,d.rs cjrs,b.pm cjpm,d.rs zhrs,c.pm zhpm,'' cjbz,'' bxkjgs,'' bxkms,'' sfzhkp,'' ffrq from view_new_dc_xszz_zzmxjg a");
		sql.append(" left join view_grzyfpm b on a.xh = b.xh and a.xn = b.xn");
		sql.append(" left join view_grzhfpm c on a.xh = c.xh and a.xn = c.xn");
		sql.append(" left join (select bjdm,count(1) rs from view_xsbfxx group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" where a.xmmc like '%国家奖学金%'");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @description	： 国家助学金导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-19 上午10:42:07
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjzxjdc(ZzxmjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select a.*,'居民身份证' sfzlxmc,'' zzbz,'' yfje,'' ffrq,a.sqly sqlv,'' bzsm from view_new_dc_xszz_zzmxjg a");
		sql.append(" where a.xmmc like '%国家助学金%'");
		sql.append(" ) a where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @description	： 国家励志奖学金导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-24 下午04:04:33
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjlzjdc(ZzxmjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select a.*,'居民身份证' sfzlxmc,d.rs cjrs,b.pm cjpm,d.rs zhrs,c.pm zhpm,'' cjbz,'' bxkjgs,'' bxkms,'' sfzhkp,'' ffrq from view_new_dc_xszz_zzmxjg a");
		sql.append(" left join view_grzyfpm b on a.xh = b.xh and a.xn = b.xn");
		sql.append(" left join view_grzhfpm c on a.xh = c.xh and a.xn = c.xn");
		sql.append(" left join (select bjdm,count(1) rs from view_xsbfxx group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" where a.xmmc like '%国家励志奖学金%'");
		sql.append(" ) a where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}


	public List<HashMap<String,String>> getXmzzqkhz(ZzxmjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String sxxm = t.getSxxm();//所选项目
		String[] sxxms =sxxm.split(",");
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (select a.*,'居民身份证' sfzlxmc,d.rs cjrs,b.pm cjpm,d.rs zhrs,c.pm zhpm,'' cjbz,'' bxkjgs,'' bxkms,'' sfzhkp,'' ffrq from view_new_dc_xszz_zzmxjg a");
		sql.append(" left join view_grzyfpm b on a.xh = b.xh and a.xn = b.xn");
		sql.append(" left join view_grzhfpm c on a.xh = c.xh and a.xn = c.xn");
		sql.append(" left join (select bjdm,count(1) rs from view_xsbfxx group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" where 1=1 ");
		for (int i = 0; i < sxxms.length; i++) {
			if(i==0)
			{
				sql.append(" and a.xmmc like '%"+sxxms[i]+"%' ");
			}
			else {
				sql.append(" or a.xmmc like '%" + sxxms[i] + "%' ");
			}
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}


	/**
	 *
	 * @描述:通过学号学期学年查询该学生已获得资助项目
	 * @作者：lyx
	 * @日期：2018-07-24
	 * @修改记录:
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgByXhXnList(String xh,String xn,String xq) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc ");
		sql.append(",substr(a.sqsj,0,10) zzsj");
		sql.append(" from xg_xszz_new_zzxmjgb a left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xh=? and a.xq = ? and xn =?");
		sql.append(" order by xn desc,xq desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh ,xq, xn });
	}
	/**
	 * 
	 * @描述:TODO(根据guid从xg_xszz_new_zzxmjgb取业务流程id,对应申请表xg_xszz_new_zzxmsqb里的guid)
	 * @作者：陈春雷[工号：1620]
	 * @日期：2018-9-5 下午03:48:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public String getSqbIdByYwid(String ywid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select lylcywid from xg_xszz_new_zzxmjgb where guid = ?");
		return dao.getOneRs(sql.toString(), new String[]{ywid},"lylcywid");
	}

    public List<HashMap<String,String>> getJgExportList(ZzxmjgForm t, User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum rn,t.* from (");

		//改用视图
		sql.append(" select * from ( ");

		sql.append(" select m.*,t2.sydm,t3.symc ");
		sql.append(" from VIEW_NEW_DC_XSZZ_ZZMXJG m ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=m.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append(" ) a  where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by symc,xmmc,xh");

		sql.append(" ) t ");
		return getPageList(t, sql.toString(), inputV);
    }
}
