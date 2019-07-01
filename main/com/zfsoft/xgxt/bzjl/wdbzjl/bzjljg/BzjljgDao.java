/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:33:34 
 */  
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖-评奖结果
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 上午10:33:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BzjljgDao extends SuperDAOImpl<BzjljgModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_pjjgb");
		super.setKey("id");
		super.setClass(BzjljgModel.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BzjljgModel t)
			throws Exception {
	
		
		return null;
		// TODO 自动生成方法存根
	}

	/**
	 * 获奖名单结果查询
	 */
	public List<HashMap<String, String>> getPageList(BzjljgModel t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		SearchModel searchModel = t.getSearchModel();
		String[] xntj = searchModel.getSearch_tj_xn();
		String[] xqtj = searchModel.getSearch_tj_xq();

		sql.append("select * from (select t2.yhmc yhmc2,t1.*,t1.xn||' '||t1.xqmc pjzq,case when t11.xh is null then '否' else '是' end sfwtgg from VIEW_NEW_DC_PJPY_PJJG t1 ");
		sql.append(" left join dmk_yh t2 on t1.shgxzw2=t2.yhdm left join(select * from xg_pjpy_new_tsxsb where lxdm='6') t11 on t1.xn=t11.xn and t1.xq=t11.xq and t1.xh=t11.xh)t1 where 1=1 ");

		sql.append(" and xzdm = '2' ");

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by xn,xq,sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:获取同级专业人数
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-29 下午05:08:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTjzyrs(String xh , String xn ,String xq){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		sql.append("select a.xh, a.xn, a.xq, c.zyrs").
			append("  from xg_pjpy_new_cpmdb a").
			append("  left join view_njxyzybj_all b").
			append("    on a.bjdm = b.bjdm").
			append("  left join (").
			append("    select count(xh) zyrs,zydm,nj,xn,xq from").
			append("    (select distinct t2.nj,t2.zydm,t1.xh,t.xn,t.xq from xg_zhcp_fstjjlb t").
			append("    left join xg_pjpy_new_cpmdb t1").
			append("      on t.bjdm = t1.bjdm and t.xn = t1.xn and t.xq = t1.xq").
			append("    left join view_njxyzybj_all t2").
			append("      on t1.bjdm = t2.bjdm").
			append("    where t.tjzt = '1' and t.bjdm is not null) group by xn,xq,nj,zydm ").
			append("  ) c ").			
			append("    on b.nj = c.nj").
			append("   and b.zydm = c.zydm").
			append("   and a.xn=c.xn").
			append("   and a.xq=c.xq").
			append(" where a.xh = ?");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and a.xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and a.xq = ? ");
			params.add(xq);
		}else{
			sql.append(" and a.xq = ? ");
			params.add(CsszService.XQKG);
		}
			
		return dao.getMap(sql.toString(), params.toArray(new String[params.size()]), new String[]{"zyrs"}).get("zyrs");
	}

	
	public String getTbjrs(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) bjrs from xsxxb a where a.bjdm = (select bjdm from xsxxb where xh = ? )");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "bjrs");
	}
	
	/**
	 * 生成优秀奖学金（浙江大学）
	 */
	public boolean scyxjxj(User user) throws Exception {
		return dao.runProcuder("{call XG_PJ_PROC_AUTOGENERATEJXJ(?,?)}", new String[]{ user.getUserDep(), user.getUserName() });
	}
	
	/** 
	 * @描述:增加操作唯一性判断（学号，学年，学期,项目名称）
	 * @作者：cq [工号：785]
	 * @日期：2013-8-7 上午11:12:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForSave(BzjljgModel model) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_pjpy_new_pjjgb where xh=? and xn=? and xmmc=? ");
		String[] inputV = null;
		if(model.getXq() == null) {
			sql.append(" and xq is null ");
			inputV = new String[] { model.getXh(),
					model.getXn(), model.getXmmc().trim() };
		} else {
			sql.append(" and xq=? ");
			inputV = new String[] { model.getXh(),
					model.getXn(), model.getXmmc().trim(), model.getXq() };
		}
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2013-8-7 上午11:15:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForUpdate(BzjljgModel model) {

		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_pjpy_new_pjjgb where xh=? and xn=?  and xmmc=?  and id<>?");
		String[] inputV = null;
		if(model.getXq() == null) {
			sql.append(" and xq is null ");
			inputV = new String[] { model.getXh(), model.getXn(),
					model.getXmmc().trim(), model.getId()};
		} else {
			sql.append(" and xq=? ");
			inputV = new String[] { model.getXh(), model.getXn(),
					model.getXmmc().trim(), model.getId(), model.getXq()};
		}
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/** 
	 * @描述:评奖结果单个查看
	 * @作者：cq [工号：785]
	 * @日期：2013-8-8 下午02:37:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getOnePjxmjgList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bjdw,a.ylzd1,a.ylzd2,a.ylzd3,a.ylzd4,a.ylzd5,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc, ");
		sql.append(" case when a.lxdm not in (select xmlxdm from xg_pjpy_new_xmlx) then lxdm else ");
		sql.append(" (select xmlxmc from xg_pjpy_new_xmlx b where a.lxdm=b.xmlxdm) end xmlxmc, ");
		sql.append(" case when a.xzdm not in (select xmxzdm from xg_pjpy_new_xmxz) then xzdm else ");
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz b where a.xzdm=b.xmxzdm) end xmxzdm, ");
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz b where a.xzdm=b.xmxzdm)xmxzmc, ");
		sql.append("  a.xmmc,a.xmje,a.sqsj,a.sqly,a.cpnj,a.cpxymc,a.cpzymc,a.cpbjmc, ");
		//徐州医药高等专科学校个性化
		if("70002".equals(Base.xxdm)){
			sql.append(" a.djjl,");
		}
		sql.append(" a.cpxydm,a.cpzydm,a.cpbjdm from xg_pjpy_new_pjjgb a where id = ? ");

		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}

//	/** 
//	 * @描述:查询项目代码、名称List
//	 * @作者：cq [工号：785]
//	 * @日期：2013-8-14 上午09:37:45
//	 * @修改记录: 修改者名字-修改日期-修改内容
//	 * @return
//	 * List<HashMap<String,String>> 返回类型 
//	 * @throws 
//	 */
//	public List<HashMap<String, String>> getxmmc() {
//		
//		StringBuilder sql = new StringBuilder();
//		
//		sql.append("  select xmdm, xmmc from xg_pjpy_new_pjxmb ");
//		sql.append(" where xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
//		
//		return dao.getListNotOut(sql.toString(), null);
//	}

	/** 
	 * @描述: 按年级学院查询指定周期项目的获得名额
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:22:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByNjxy(String xmdm, String xn,
			String xq) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.nj,t3.xydm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.nj,t3.xydm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @描述: 按年级专业查询指定周期项目的获得名额
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:32:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByNjzy(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.nj,t3.zydm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.nj,t3.zydm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @描述: 按学院查询指定周期项目的获得名额
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:33:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByXy(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.xydm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.xydm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @描述: 按参评小组查询指定周期项目的获得名额
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:34:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByCpz(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.cpzdm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join xg_zhcp_fstjjlb t3 on t2.bjdm=t3.bjdm and t1.xn=t3.xn and t1.xq=t3.xq");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.cpzdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @描述: 按班级查询指定周期项目的获得名额
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:36:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByBj(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.bjdm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.bjdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @描述: 按全校查询指定周期项目的获得名额
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:37:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByQx(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs from xg_pjpy_new_pjjgb t1");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @描述:
	 * @作者：ligl
	 * @日期：2013-8-14 下午03:33:44
	 * @修改记录: 
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select xydm,xn,xq,count(*) rs from (  ");
		sql
				.append(" select x.*,y.xydm from xg_pjpy_new_pjjgb x,view_xsjbxx y where  x.xh=y.xh and x.xmmc=? ");
		sql.append(" ) group by xydm,xn,xq order by xn desc,xq desc");
		String[] inputValue = { xmmc };
		List<HashMap<String, String>> result = dao.getListNotOut(
				sql.toString(), inputValue);
		return result;
	}

	
	/**
	 * 
	 * @描述: 根据学生查询获奖情况
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-9-3 下午02:36:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<String[]> getHjqkByXh(String xh){
		
		StringBuilder sql =  new StringBuilder();
		
		sql.append("select t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.sqsj desc");
		
		return dao.rsToListNotOut(sql.toString(), new String[]{xh});
	} 
	/**
	 * 
	 * @描述: 根据学生查询获奖情况
	 * @作者：cmj[工号：913]
	 * @日期：2013-9-3 下午02:36:39
	 * @修改记录: 孟威-20160627-山西财经大学报表奖项获得只能取四行
	 * @param xh
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.bjdw,(select xmxzmc from xg_pjpy_new_xmxz b where t1.xzdm=b.xmxzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj" , "sqsjs"});
	}
	
	public List<HashMap<String, String>> getHjqkByXhXnMap(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.bjdw,(select xmxzmc from xg_pjpy_new_xmxz b where t1.xzdm=b.xmxzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? and t1.xn=? order by t1.xn desc,t1.xq desc");
		return dao.getArrayList(sql.toString(), new String[]{xh,xn}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj" , "sqsjs"});
	}
	/**
	 * 根据学生查询学生获奖情况（根据学年分组）
	 */
	public List<HashMap<String, String>> getPjjgGroupByXn(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append(" select a.xn,replace(a.xmmcsTemp,';','；') xmmcs from ( ");
		sql.append(" select xn,WM_CONCAT(xmmc) over (partition by xn order by xn) xmmcsTemp, ");
		sql.append(" row_number() over (partition by xn order by xn desc) rn ");
		sql.append(" from ( ");
		sql.append(" select t1.xn,t2.xqmc,t1.xmmc ");
		sql.append(" from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append(" on t1.xq=t2.xqdm where t1.xh=? order by t1.xn asc,sqsj asc ");
		sql.append(" ) a ");
		sql.append(" ) a where rn=1 ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:根据学号查询获奖情况
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getHjqkList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc,t1.xn||' '||t2.xqmc pjzq ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ,t1.sqsj desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	
	
	/**
	 * 
	 * @描述:获取平均成绩
	 * @作者：HongLin[工号：707]
	 * @日期：2013-11-21 上午09:33:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param Map
	 * @return
	 * Integer 返回类型 
	 */
	public Map<String, String> getBjgcjNum(String xh,String xn,String xq){
		StringBuilder sql =  new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		sql.append("select count(*) num from view_zhhcjb  where cj <60 and xh=?");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ?");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?");
			params.add(xq);
		}
		
		return dao.getMap(sql.toString(), params.toArray(new String[params.size()]), new String[]{"num"});
	}
	/**
	 * 
	 * @描述:查询华中师范 大学个性化证书编码记录
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-3 下午01:57:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZsbm(BzjljgModel model){
		StringBuffer sql= new StringBuffer();
		sql.append("select substr(ylzd1,length(ylzd1)-3,4) zsbm from xg_pjpy_new_pjjgb")
		.append(" where cpnj=? and cpxydm=? and xn=? and ylzd1 is not null order by zsbm desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getCpnj(),model.getCpxydm(),model.getXn()});
	}
	/**
	 * 
	 * @描述:根据项目名称获取项目代码
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-3 下午03:23:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXmdm(BzjljgModel model){
		StringBuffer sql= new StringBuffer();
		sql.append("select xmdm from xg_pjpy_new_pjxmb where xmmc=?");
		return dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "xmdm");
	}
	
	/**
	 * 
	 * @描述:回滚结果表的申请记录
	 * @作者：945
	 * @日期：2013-12-5 上午09:41:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delJgb(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{ywid};
		sql.append("delete from ");
		sql.append(" xg_pjpy_new_pjjgb ");
		sql.append(" where sjly='1' and ");
		sql.append(" lylcywid = ? ");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * @描述: 查询荣誉证书模板
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-21 上午10:13:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @param user
	 * @return
	 * HashMap 返回类型 
	 */
	public HashMap<String, String> cxRyzs(String csdm, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.csdm,replace(decode(b.csz, null, a.csz, b.csz),'LODOP.PRINT_INITA(0,0,794,1123,\"打印\");','') csz from ");
		sql.append(" (select csdm,csz from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = 'public') a left join ( ");
		sql.append(" select csdm,csz from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = ?) b on (a.csdm=b.csdm) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{csdm, csdm, user.getUserName()});
	}
	
	/**
	 * @描述: 保存荣誉证书模板
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-21 上午10:13:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @param csz
	 * @param user
	 * @return
	 * boolean 返回类型 
	 */
	public boolean bcRyzs(String csdm, String csz, User user) throws Exception{
		String deleteSql = " delete from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = ? ";
		dao.runDelete(deleteSql, new String[]{csdm, user.getUserName()});
		
		String insertSql = " insert into xg_pjpy_new_ryzsmbszb(csdm,zgh,csz) values(?,?,?) ";
		return dao.runUpdate(insertSql, new String[]{csdm, user.getUserName(), csz});
	}
	
	/**
	 * 
	 * @描述: 根据学号查询奖项汇总
	 * @作者：cq [工号：785]
	 * @日期：2014-8-25 下午05:19:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJxSum(String xh){
		
		String sql = "select count(1) js,sum(xmje) zje from xg_pjpy_new_pjjgb t1 where t1.xh = ?";
		
		return dao.getMapNotOut(sql, new String[]{xh});
			
	}
	/**
	 * @描述: 浙江大学获奖情况总金额显示（个性化）
	 * @作者：孟威[工号：1186]
	 * @日期：2016-6-12 上午10:01:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPjzje(String xh){
		String sql = " select sum(jjze) pjzje from xg_pjpy_new_jxjhzb where xh = ? ";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	/**
	 * 
	 * @描述:获取班级人数，辅导员，班主任信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-20 下午04:10:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBjxx(String bjdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from(select  count(a.xh) bjrs from view_xsxxb a where a.bjdm=?),");
		sql.append("(select wm_concat(c.xm) fdyxm from fdybjb b left join");
		sql.append(" yhb c on b.zgh=c.yhm where b.bjdm=?),(select wm_concat(e.xm) bzrxm");
		sql.append(" from bzrbbb d left join yhb e on d.zgh=e.yhm where d.bjdm=?)");
		return dao.getMapNotOut(sql.toString(), new String[]{bjdm,bjdm,bjdm});
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2015-5-22 下午04:07:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPriJg(String[] values) {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("select b.xm, decode(b.xb, '1', '男', '2', '女', b.xb) xb,b.xh,b.sfzh,b.rxrq,b.byny,c.xymc,a.xmmc,");
		sql.append("a.xn,(select xqmc from xqdzb t where a.xq=t.xqdm) xqmc,to_char(to_date(rxrq,'yyyy-mm-dd'),'yyyy') rxn, ");
		sql.append("to_char(to_date(rxrq,'yyyy-mm-dd'),'mm') rxy, ");
		sql.append("substr(nvl(byny,to_char(sysdate,'yyyy-mm-dd hh24')),0,4) byn,");
		sql.append("substr(nvl(byny,to_char(sysdate,'yyyy-mm-dd hh24')),6,2) byy ");
		sql.append("from xg_pjpy_new_pjjgb a left join xsxxb b on a.xh=b.xh ");
		sql.append("left join view_njxyzybj_all c on b.bjdm=c.bjdm where id in (");
		for (int i = 0; i < values.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(values[i]);
		}
		sql.append(")");
		sql.append("order by a.xh,a.xn,a.xq,a.xmmc ");

		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	//山东畜牧兽医优秀毕业生推荐表获得学生信息
	public List<HashMap<String, String>> getXsxxlist(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t.xm,t.bjmc,t.xh,t.zymc,t.xymc xy,");
		sql.append("decode(t.xb,'1','男','2','女','男','男','女','女') xb ");
		sql.append("from  view_xsjbxx t where t.xh in(");
		
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//山东畜牧兽医社会奖学金汇总获取学生信息以及项目金额list
	public List<HashMap<String, String>> getShjxjHzbxxList(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t1.xm,decode(t1.xb,'1','男','2','女','男','男','女','女') xb,t.xmje,t1.bjmc,t1.xymc xy,  ");
		sql.append("(select xxmc from xtszb) xxmc,t1.sfzh,t1.zymc, t1.xh,  t1.rxrq, (select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_pjpy_new_pjjgb t join view_xsjbxx t1 on t.xh = t1.xh where t.id in(");
		
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//山东畜牧兽医社会奖学金获取总人数，总金额 map
	public HashMap<String, String> getshjxjTotal(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select count(*) zrs,sum(xmje) xmze from xg_pjpy_new_pjjgb where id in(");
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getMapNotOut(sql.toString(), values);
	}
	
	//山东畜牧兽医省励志奖学金获取联系电话 map
	public HashMap<String, String> getlxfs(String value){
		StringBuffer sql = new StringBuffer();
		sql.append("select lxdh from fdyxxb where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{value});
	}
	
	//山东畜牧兽医省政府奖学金获取综测分数排名map
	public HashMap<String, String> getzccj(BzjljgModel model){
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.fs, t1.xmdm, t1.bjpm   ");
		sql.append("from Xg_Zhcp_Zcxmb t, XG_ZHCP_ZCFSB t1  ");
		sql.append("  where t.xmdm = t1.xmdm  and t.xn = t1.xn    and t.xn = ? and t1.xh = ? and t.xmdm = ? and t.xq = 'on' and t.xq = t1.xq  ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXn(),model.getXh(),model.getXmdm()});
	}
	
	//山东畜牧兽毕业生获取综测成绩分数排名
	public List<HashMap<String, String>> getzccjList(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct t1.xn  xnxq,t.fs,t.bjpm  from  XG_ZHCP_ZCFSB t,Xg_Zhcp_Zcxmb t1 ");
		sql.append(" where t.xn = t1.xn and t.xmdm = t1.xmdm and t.xq = t1.xq and t.xq = 'on'");
		sql.append(" and t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	//社会奖学金，推荐表汇总导出
	public List<HashMap<String, String>> getDclist(BzjljgModel t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum bh,t2.yhmc yhmc2,t1.*,t1.xn||' '||t1.xqmc pjzq from VIEW_NEW_DC_PJPY_PJJG t1 left join dmk_yh t2 on t1.shgxzw2=t2.yhdm where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return dao.getListNotOut(sql.toString(), inputV);
		// TODO 自动生成方法存根
	}
	/**
	 * 
	 * @描述:获取审核信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-17 上午08:42:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSpxxInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.shyj ejshyj,b.shzt ejshzt,b.zd2 ejtjdcdm ,b.zd3 ertjdcmc, c.shyj sjshyj,c.shzt sjshzt,c.zd2 sjtjdcdm,c.zd3 sjtjdcmc from(select a.* from(select a.ywid,a.shyj yjshyj,a.shzt yjshzt,a.zd2 yjtjdc,row_number() over(partition by a.ywid order by a.shsj desc) rn from xg_xtwh_shztb a   where a.ywid=?)a where a.rn=3)a left join");
		sql.append("  (select b.* ,row_number() over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b  where b.ywid=?)b on a.ywid=b.ywid and b.rn=2");
		sql.append(" left join (select  c.*,row_number() over(partition by c.ywid order by c.shsj desc) rn from xg_xtwh_shztb c   where c.ywid=?)c on a.ywid=c.ywid and c.rn=1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{guid,guid,guid});
	}
	
	/**
	 * @描述：二级审核时的审核意见
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年8月11日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public HashMap<String, String> getSpxxInfo2ji(String ywid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.shyj ejshyj, b.shzt ejshzt, b.zd2 ejtjdcdm, b.zd3 ertjdcmc from ( ");
		sql.append("select a.* from (select a.ywid, a.shyj yjshyj, a.shzt yjshzt, a.zd2 yjtjdc, row_number() over(partition by a.ywid order by a.shsj desc) ");
		sql.append("rn from xg_xtwh_shztb a where a.ywid = ?) a where a.rn = 2) a left join (select b.*, row_number() ");
		sql.append("over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b where b.ywid = ?) b on a.ywid = b.ywid and b.rn = 1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{ywid,ywid});
	}
	
	
	public String getPjxxByXhXnXq(String xh,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,wm_concat(xmmc) xmmc from xg_pjpy_new_pjjgb where ");
		sql.append(" xh=? and xn=? and xq=? group by xh");
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "xmmc");
	}
	
	
	/**
	 * 传媒个性化 取学生必修课情况
	 */
	
	public HashMap<String, String> getBxk(String xh,String xn){
		
		String sql = "select count(1) count,nvl(round(sum(case when cj >=60 then '1' else '0' end),0),0) hg from view_zhhcjb where (kcxz like '%必修课%' or kcxz like '%必选课%') and xh = ? and xn = ? ";
		
		return dao.getMapNotOut(sql, new String[]{xh,xn});
	}
	
	public HashMap<String, String> getPm(String xh,String xn){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.*, round((select count(1)/2 zrs from xg_pjpy_new_cpmdb ");
		sql.append("where xn||xq||bjdm in (select xn||xq||bjdm from xg_pjpy_new_cpmdb ");
		sql.append("where xh = ? ) and xn = ? and xq <> 'on'),0) zrs from ( ");
		sql.append("select nvl(round(sum(case when a.xmmc='综测总分' then b.bjpm else '0' end)/2,0),0) xnzfpm, ");
		sql.append("nvl(round(sum(case when a.xmmc='智育分' then b.bjpm else '0' end)/2,0),0) xnzypm ");
		sql.append("from xg_zhcp_zcxmb a left join xg_zhcp_zcfsb b on a.xmdm=b.xmdm ");
		sql.append("where a.xn = ? and a.xq <>'on' ");
		sql.append("and (a.xmmc ='综测总分' or a.xmmc ='智育分') and xh = ? )a ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn,xn,xh});
	}

	/**
	 * 
	 * @描述: 温州大学资助登记表取排名(取学年2个学期中排名最高的)
	 * @作者：夏夏[工号：1104]
	 * @日期：2016-10-18 下午02:39:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCjPm(String xh,String xn){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xn, ");
		sql.append("       nvl(min(to_number(a.xnzfpm)),'') xnzfpm, ");
		sql.append("       nvl(min(to_number(a.xnzypm)),'') xnzypm, ");
		sql.append("       nvl(max(to_number(b.zrs)),'') zrs ");
		sql.append("  from (select a.xn, ");
		sql.append("               a.xq, ");
		sql.append("               min(case ");
		sql.append("                     when a.xmmc = '综测总分' then ");
		sql.append("                      b.bjpm ");
		sql.append("                     else ");
		sql.append("                      null ");
		sql.append("                   end) xnzfpm, ");
		sql.append("               min(case ");
		sql.append("                     when a.xmmc = '智育分' then ");
		sql.append("                      b.bjpm ");
		sql.append("                     else ");
		sql.append("                      null ");
		sql.append("                   end) xnzypm ");
		sql.append("          from xg_zhcp_zcxmb a ");
		sql.append("          left join xg_zhcp_zcfsb b ");
		sql.append("            on a.xmdm = b.xmdm ");
		sql.append("         where a.xn = ? ");
		sql.append("           and a.xq <> 'on' ");
		sql.append("           and (a.xmmc = '综测总分' or a.xmmc = '智育分') ");
		sql.append("           and xh = ? ");
		sql.append("         group by a.xn, a.xq) a ");
		sql.append("  left join (select xn, xq, count(1) zrs ");
		sql.append("               from xg_pjpy_new_cpmdb ");
		sql.append("              where xn || xq || bjdm in ");
		sql.append("                    (select xn || xq || bjdm ");
		sql.append("                       from xg_pjpy_new_cpmdb ");
		sql.append("                      where xh = ?) ");
		sql.append("              group by xn, xq) b ");
		sql.append("    on b.xn = a.xn ");
		sql.append("   and b.xq = a.xq ");
		sql.append(" group by a.xn ");

		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh,xh});
	}
	
	
	/**
	 * 
	 * @描述: 评奖信息单独查询
	 * @作者：cq [工号：785]
	 * @日期：2015-11-20 下午04:05:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh,String xn,String xq){
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.XH,a.XM,a.XB,nvl(c.CPXYDM, a.XYDM) XYDM,nvl(c.CPXYMC, a.XYMC) XYMC,nvl(c.CPZYDM, a.ZYDM) ZYDM,nvl(c.CPZYMC, a.ZYMC) ZYMC, ");
		sql.append("nvl(c.CPBJDM, a.BJDM) BJDM, nvl(c.CPBJMC, a.BJMC) BJMC,nvl(c.CPNJ, a.NJ) NJ, ");
		sql.append("a.SFZH,a.LXDH,a.XZ,a.DZYX,a.LXDZXX,a.SJHM,a.MM,a.SSBHZD,a.SSCHZD,a.SSBH,a.QSDH,a.SSCH,a.ZSRQ,a.ZSJZRQ, ");
		sql.append("a.SFXYQGZX,a.XGYX,a.YHKH,a.YKTH,a.XMPY,a.SYD,a.CSRQ,a.MZ,a.MZMC,a.ZZMM,a.ZZMMMC,a.CYM,a.SG,a.TZ,a.TC, ");
		sql.append("a.KSLB,a.RXFS,a.PYFS,a.PYCC,a.BZ,a.XSZP,a.WHCD,a.RXQDW,a.JTDH,a.JRGQTSJ,a.JRGCDSJ,a.JTCYGC,a.JG,a.JLCFJL, ");
		sql.append("a.JTDZ,a.JTYB,a.XX,a.JKZKDM,a.JKZK,a.AH,a.SFDK,a.JTJJQK,a.SHGXXM1,a.SHGXGX1,a.SHGXGZDW1,a.SHGXZW1,a.SHGXDWDH1, ");
		sql.append("a.SHGXSJHM1,a.SHGXXM2,a.SHGXGX2,a.SHGXGZDW2,a.SHGXZW2,a.SHGXDWDH2,a.SHGXSJHM2,a.JTQKJJ,a.BYXX,a.KH,a.RXRQ, ");
		sql.append("a.FDYXM,a.GKCJ,a.QQHM,a.HKXZ,a.HKSZD,a.ZYJB,a.SSYQ,a.SSYQMC,a.SSLD,a.JTDZS,a.JTDZX,a.SFZSB,a.SFZFX,a.ZJDM, ");
		sql.append("a.ZJMC,a.SFYBY,a.BYNY,a.ZW,a.XXFX,a.THBS,a.DYBJ,a.SHBJ,a.XZXM,a.XXSZD,a.XW,a.XWZSBH,a.XWZSXLH,a.BJYJL,a.ZSBH, ");
		sql.append("a.ZSXLH,a.ZYFX,a.ZYLB,a.FXZY,a.FXZYFX,a.BXXS,a.BXLX,a.XXXS,a.CSD,a.PYFX,a.DQSZJ,a.ZSJJ,a.KSH,a.ZSLB,a.GJ, ");
		sql.append("a.SFJH,a.CCQJ,a.BYZFFZTDM,a.BYZFFZTMC,a.XWZSXXDZ,a.JGS,a.JGSHI,a.JGX,a.RXNJ,a.NFBY,a.SFZC,a.DASFYL,a.DAYLYY, ");
		sql.append("a.YXDM,a.SFZZ,a.SFSF,a.SFDL,a.DXHWP,a.BYSJ,a.ZXWYYZDM,a.WYDJ,a.JSJDJ,a.YZBM,a.SHZW,a.JYPX,a.XMSJ,a.ZGZS, ");
		sql.append("a.LXDZ,a.JLJN,a.SYBZ1,a.SYBZ2,a.SYBZ3,a.XLDM,a.ZKZH,a.SFCJ,a.GRJL,a.XSLB,a.XSLBMC,a.XSLX,a.XSLXMC,a.SFBYS, ");
		sql.append("a.YHDM,a.YHMC,a.DAH,a.YLBXH,a.RXQDWDH,a.RXQDWDZ,a.RXQDWYB,a.GZBX,a.SFGAT,a.SFSSMZ,a.SFZD,a.HKSHEN,a.HKSHI, ");
		sql.append("a.HKXIAN,a.ZCSXHM,a.RXQWHCD,a.TBSJ,a.BXXZ,a.SFTB,a.SFYQRZS,a.QTYY,a.SFSFS,a.BYZH,a.SYDS,a.XJH,a.JRZZMMRQ,a.SFHQ, ");
		sql.append("a.CSDS,a.CSDSHI,a.CSDXIAN,a.ZD1,a.ZD2,a.ZD3,a.ZD4,a.ZD5,a.ZD6,a.SYDMC,a.YXMC,a.XWMC,a.ZXWYYZMC,a.XLMC,a.ZSLBMC, ");
		sql.append("a.PYFSMC,a.SFZBLX,a.XJLBDM,a.XJLB,a.XJLBMC,a.XJZTM,a.SFZX,a.ZYFXMC,a.SYDSMC,a.YDLBM,a.YDLBMC,a.SYDQMC,a.JTDZXX,a.JGMC,");
		sql.append("a.HKSZDMC,a.CSDMC,k.ldmc||k.qsh qsh from view_xsxxb a left join (select * from ( ");
		sql.append("select a.*,row_number()over(partition by xh,xn,xq order by sqsj desc) rn from xg_pjpy_new_pjjgb a where (xn,nvl(xq,'on')) ");
		sql.append("= (select xn,xq from xg_pjpy_new_csszb)) where rn =1 ) c ");
		sql.append("on a.xh=c.xh left join view_xg_gygl_new_cwxx k on a.xh=k.xh where rownum =1 and a.xh = ? ");
		
		if(!StringUtil.isNull(xn)){
			params.add(xn);
			sql.append(" and c.xn = ? ");
		}
		
		if(!StringUtil.isNull(xq)){
			params.add(xq);
			sql.append(" and c.xq = ? ");
		}
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * 
	 * @描述:結果增加學生
	 * @作者：cq [工号：785]
	 * @日期：2015-11-20 下午06:14:44
	 * @修改记录: Meng.Wei-2017-03-05-nvl的时候，bjdm写为bjmc了
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjXs(BzjljgModel model, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from ( ");
		
		sql.append(" select a.XH,a.XM,a.XB,nvl(c.XYDM, a.XYDM) XYDM,nvl(c.XYMC, a.XYMC) XYMC, ");
		sql.append(" nvl(c.ZYDM, a.ZYDM) ZYDM,nvl(c.ZYMC, a.ZYMC) ZYMC, ");
		sql.append(" nvl(c.bjdm,a.bjdm) bjdm, nvl(c.BJMC, a.BJMC) BJMC,nvl(c.NJ, a.NJ) NJ, ");
		sql.append(" a.SFZH,a.LXDH,a.XZ,a.SJHM,a.RXRQ, ");
		sql.append(" a.YHKH,a.YHDM,a1.YHMC,a.CSRQ,a.MZ,a.MZMC,a.ZZMM,a.ZZMMMC ");
		sql.append(" from view_xsbfxx a left join dmk_yh a1 on a.yhdm = a1.yhdm ");
		sql.append(" join (select a.xh,b.* from xg_pjpY_new_cpmdb a left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" where (xn,xq) in (select xn,xq from xg_pjpy_new_csszb)) c ");
		sql.append(" on a.xh=c.xh ) a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:根据学号查询老版评奖信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-18 上午11:09:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getHjqkListOld(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc,t1.xn||' '||t2.xqmc pjzq ");
		sql.append("from xg_pjpy_pjlsxxb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ,t1.hdsj desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	
	/**
	 * 
	 * @描述:德育等第信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-21 上午10:15:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDyddList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh,t1.xqmc,t1.pjjg,t1.xssx from xg_xsxx_dyddb t1 where t1.xh = ? order by xssx ");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	
	/**
	 * 
	 * @描述: 所有审核级别审核意见汇总
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-16 上午08:36:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmmc) {
		
		StringBuffer sql = new StringBuffer();	
		sql.append(" select t1.shyj from xg_xtwh_shztb t1 left join xg_pjpy_new_pjjgb t2 on t1.ywid = t2.id ");
		sql.append(" where xh = ? and xn = ? and xq = ? and xmmc = ? order by shsj asc ");
		
		String[] inputValue = { xh,xn,xq,xmmc };
		return dao.getListNotOut(sql.toString(), inputValue);
		
	}
	/**
	 * @描述: 北京林业大学学生获奖数据
	 * @作者：孟威[工号：1186]
	 * @日期：2016-5-11 下午09:30:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgList(String xh,String xn) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xn,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc,a.xmmc ");
		sql.append(" from xg_pjpy_new_pjjgb a ");
		sql.append(" where a.xh = ? and a.xn = ? order by xq");
		return dao.getListNotOut(sql.toString(), new String[] { xh,xn });
	}
	
	/**
	 * 
	 * @描述: 取该学生评奖所有奖项（湘潭等级报表需要）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-27 上午09:19:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXmmcAllByPjjg(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct WM_CONCAT(replace(t1.xmmc,';',',')) over(partition by t1.xh order by t1.xh) xmmc ");
		sql.append(" from (select distinct xh, t1.xn || ' ' || WM_CONCAT(t1.xmmc) over(partition by t1.xh, t1.xn order by t1.xh, t1.xn) xmmc ");
		sql.append(" from (select xh, xn, xmmc from xg_pjpy_new_pjjgb where xh = ? ) t1) t1 ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xmmc");
	}
	
	/**
	 * 
	 * @描述:必修课平均成绩排名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-29 下午03:36:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPjfRank(BzjljgModel model,String xq,String kcxz){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum no,t.* from (select avg(decode(t.cj, '及格', 60, t.cj)) pjf,min(decode(t.cj, '及格', 60, t.cj)) zdf ");
		sql.append(", xh from cjb t where kcxz = ?  and xh in (select xh from xsxxb where bjdm = ? ) ");
		if(xq != null){
			sql.append(" and xq = ? ");
		}
		sql.append("  and xn = ? group by xh order by avg(decode(t.cj, '及格', 60, t.cj)))t ) t1 where t1.xh = ? ");
		if(xq != null){
			return dao.getMapNotOut(sql.toString(), new String[]{kcxz,model.getCpbjdm(),xq,model.getXn(),model.getXh()});
		}else{
			return dao.getMapNotOut(sql.toString(), new String[]{kcxz,model.getCpbjdm(),model.getXn(),model.getXh()});
		}
	}
	
	/**
	 * 
	 * @描述:综测平均成绩排名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-29 下午03:36:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZcPjfRank(BzjljgModel model,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum zcpm,t.* from (select avg(t.fs) zcpjf ");
		sql.append(", xh from xg_zhcp_zcfsb t where  xh in (select xh from xsxxb where bjdm = ?) and xq = ? and xn = ? ");
		sql.append(" group by xh order by avg(t.fs))t ) t1 where t1.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getCpbjdm(),xq,model.getXn(),model.getXh()});
	}
	
	/**
	 * 
	 * @描述:获取学生四六考试
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-29 下午05:14:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getDjksMap(String xh,String ksmc,String ksmc1){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum,t.* from ");
		sql.append("(select  djksmc,cj,xn sj from XSDJKSB where xh = ? and (djksmc like '%' || lower(?) || '%' or djksmc like ?  or djksmc like '%' || upper(?) || '%' )order by cj desc ) t");
		sql.append(" where rownum=1");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,ksmc,ksmc1,ksmc});
	}
	
	/**
	 * 
	 * @描述:根据学年取出该学生在该学年的获奖情况
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-30 上午10:40:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgByxn(BzjljgModel model){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum no, t.xmmc,to_char(to_date(t.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj,t.xmje from xg_pjpy_new_pjjgb t  ");
		sql.append("where t.xh = ? and  t.xn = ?  ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXn()});
	}
	
	public List<HashMap<String, String>> getXnXqlist(String xh,String lx){
		StringBuilder sql = new StringBuilder();
		if("bxk".equals(lx)){
			sql.append("select t.*,rownum no from ");
			sql.append("(select distinct xn,xq from cjb where xh = ? order by xn,xq) t");
		}else{
			sql.append("select t.*,rownum no from ");
			sql.append("(select distinct xn from xg_zhcp_zcfsb where xh = ? order by xn) t");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-30 下午04:08:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJsjdjkslist(String xh,String ksmc,String ksmc1){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct djksmc,cj,xn tgsj from XSDJKSB where cj in   ");
		sql.append("(select max(cj) from XSDJKSB t where xh = ?  and djksmc like '%'|| ? ||'%' or djksmc like '%' || ? || '%' group by djksmc) ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,ksmc,ksmc1});
	}
	
	public List<HashMap<String, String>>getHjrycssj(String xh,String xmmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) cs,xmmc,xn hjsj from xg_pjpy_new_pjjgb where xh = ? and xmmc like '%' || ? || '%'  group by xmmc,xn ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xmmc});
	}
	
	/**
	 * 
	 * @描述:必修课平均成绩排名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-29 下午03:36:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZyPjfRank(BzjljgModel model,String kcxz){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum no,t.* from (select avg(decode(t.cj, '及格', 60, t.cj)) pjf,min(decode(t.cj, '及格', 60, t.cj)) zdf ");
		sql.append(", xh from cjb t where kcxz = ?  and xh in (select xh from xsxxb where zydm = ? ) ");
		sql.append("  and xn = ?  group by xh order by avg(decode(t.cj, '及格', 60, t.cj)))t ) t1 where t1.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{kcxz,model.getCpzydm(),model.getXn(),model.getXh()});
	}
	
	/**
	 * 
	 * @描述:获取专业人数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-1 下午02:55:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getZyRs(String zydm){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) rs from xsxxb where zydm = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{zydm});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-1 下午05:21:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型  
	 * @throws
	 */
	public List<HashMap<String, String>>  getzzxmjg(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum no,t.xmmc,t.je,to_char(to_date(t.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj from xg_xszz_new_zzxmjgb t where xn = ? and xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xh});
	}
	
	/**
	 * 
	 * @描述:获取奖项申请次数
	 * @日期：2015-7-2 下午03:11:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSqcs(BzjljgModel model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) sqcs from xg_pjpy_new_xmsq where xh = ? and xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh(),model.getXmdm()});
	}
	
	/**
	 * 
	 * @描述:获取奖项获得次数
	 * @作者：喻鑫源[工号：982]
	 * @日期：2015-7-2 下午03:18:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJxcs(BzjljgModel model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) jxcs from xg_pjpy_new_pjjgb where xh = ? and xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh(),model.getXmdm()});
	}
	
	/**
	 * 
	 * @描述:获取学年学期list
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-2 下午03:42:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXnList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,rownum no from ");
		sql.append("(select distinct xn from cjb where xh = ? order by xn) t");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:必修课平均成绩排名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-3  下午03:36:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getNjPjfRank(BzjljgModel model,String nj,String kcxz){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum njno,t.* from (select avg(decode(t.cj, '及格', 60, t.cj)) pjf,min(decode(t.cj, '及格', 60, t.cj)) zdf ");
		sql.append(", xh from cjb t where kcxz = ?  and xh in (select xh from xsxxb where nj = ?  ) ");
		sql.append("  and xn = ? group by xh order by avg(decode(t.cj, '及格', 60, t.cj)))t ) t1 where t1.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{kcxz,nj,model.getXn(),model.getXh()});
	}
	
	/**
	 * 
	 * @描述:获取年级人数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-3 下午02:55:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getNjRs(String nj){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) njrs from xsxxb where nj = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{nj});
	}
	
	/*
	 * @描述: 华中农业评奖取最新四条
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-10-26 上午10:00:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getHznydxPjpyMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append(" select sqsjs,xmmc,bjdw from ( select t1.bjdw,(select xmxzmc from xg_pjpy_new_xmxz b where t1.xzdm=b.xmxzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ) where ROWNUM <= 4 ");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"sqsjs","xmmc","bjdw"});
	}
	
	/**
	 * @描述：通用sql方法，取最新n条获奖信息
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月6日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh,String n){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t.* from (select t1.bjdw, t3.xmxzmc, t1.xn, t2.xqmc, t1.xmmc, t1.xmje, t1.sqsj, to_char(to_date(t1.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy-MM-dd') ");
		sql.append(" sqsjs from xg_pjpy_new_pjjgb t1 left join xqdzb t2 on t1.xq = t2.xqdm left join xg_pjpy_new_xmxz t3 on t1.xzdm = t3.xmxzdm ");
		sql.append(" where t1.xh = ? order by t1.xn desc, t1.xq desc,t1.sqsj desc)t where rownum<= ? ");
		return dao.getArrayList(sql.toString(), new String[]{xh,n}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj" , "sqsjs"});
	}
	
	/**
	 * @描述：结果列表,用于导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPjjgList_10279(String xn,String xmmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* ,rownum R from( ");
		sql.append(" select t1.xn, t1.xq, t1.xmdm,t1.xmmc,t4.xymc,t4.bjmc,t1.xh,t4.xm,replace(t1.ylzd1,'<br/>','&'||chr(35)||'10;') ylzd1, ");
		sql.append( "replace(t1.ylzd3,'<br/>','&'||chr(35)||'10;') ylzd3,replace(t1.ylzd4,'<br/>','&'||chr(35)||'10;') ylzd4,t1.ylzd5,t8.fs zczf, ");
		sql.append(" case when t5.bjgs>0 then '不及格' else '及格' end jgqk ,case when t6.cfs>0 then '有' else '无' end wjqk,nvl(t7.hjqk,'无') hjqk ");
		sql.append("   from xg_pjpy_new_pjjgb t1 ");
		sql.append("   left join view_xsbfxx t4 ");
		sql.append("    on t1.xh=t4.XH ");
		sql.append("   left join (select fs,xh from XG_ZHCP_ZCFSB where xmdm in(select max(xmdm) from xg_zhcp_zcxmb where xn =? and fjdm='N')) t8 ");
		sql.append("    on t1.xh=t8.xh ");
		sql.append("   left join (select count(1) bjgs,xh from view_zhhcjb where cj < 60 and kcxz like '%必修%' group by xh) t5 ");
		sql.append("    on t1.xh=t5.xh ");
		sql.append("   left join (select count(1) cfs,xh from xg_wjcf_wjcfb where nvl(ssjg,0) <> '撤消处分' or nvl(ssjg,0) <> '撤销处分' and jcwh is null group by xh)t6 ");
		sql.append("    on t1.xh=t6.xh ");
		sql.append("   left join (select WM_CONCAT(xmmc) hjqk,xh from (select distinct xh,xmmc from xg_pjpy_new_pjjgb) group by xh) t7 ");
		sql.append("    on t1.xh=t7.xh ");
		sql.append("  where  t1.xn= ? and t1.xmmc= ? ");
		sql.append(" )t ");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xn,xmmc});
	}

	/** 
	 * @描述：评奖结果上报奖项列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月10日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getPjjghzList(BzjljgModel model,User user) throws Exception {
		SearchModel searchModel = model.getSearchModel();
		String[] xydm = searchModel.getSearch_tj_xy();
		searchModel.setSearch_tj_xy(new String[]{});
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		String userSql = "";
		if(StringUtils.equals("12036",Base.xxdm)){
			userSql = SearchService.getSearchTjByUser(user,"t5","xydm","bjdm");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		sql.append("(select t.*,count(1) hjrs from(select t1.xmmc,t1.lxdm,t2.xmlxmc,t1.xzdm,t3.xmxzmc,t1.xn,t1.xq,t4.xqmc from XG_PJPY_NEW_PJJGB t1 ");
		sql.append("left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm ");
		sql.append("left join XG_PJPY_NEW_XMXZ t3 on t1.xzdm=t3.xmxzdm ");
		sql.append("left join xqdzb t4 on t1.xq=t4.xqdm");
		sql.append(" left join view_xsjbxx t5 on t5.xh=t1.xh where 1=1 ");
		if(xydm !=null && xydm.length> 0){
			StringBuffer xySql = new StringBuffer();
			int length = xydm.length;
			xySql.append(" and t5.xydm in ( ");
			for(int i=0;i<length-1;i++ ){
				xySql.append("'"+xydm[i]+"',");
			}
			xySql.append("'"+xydm[length-1]+"')");
			sql.append(xySql);
		}
		sql.append(userSql);
		sql.append(" )t group by xmmc,lxdm,xmlxmc,xzdm,xmxzmc,xn,xq,xqmc ");
		sql.append(") where 1=1  ");
		sql.append(searchTj);
		return getPageList(model,sql.toString(), inputV);
	}
	
	/**
	 * @描述：评奖结果上报汇总名单
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPjjghzMdList(BzjljgModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		SearchModel searchModel = model.getSearchModel();
		searchModel.setSearch_tj_xy(new String[]{});
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		sql.append("select t.* ,rownum R from (");
		sql.append("select t1.*,t5.sydmc,t5.zzmmmc,t5.xlmc,t5.xm,t5.nj,t5.bjdm,t5.bjmc,t5.zydm,t5.zymc,t5.xydm, ");
		sql.append("t5.xymc,t5.mzmc,t5.xb,substr(t5.rxrq,0,7) rxny,substr(t5.csrq,0,7) csny,t5.sjhm,t5.sfzh,t5.yhkh,t2.xmlxmc,t3.xmxzmc,t4.xqmc,t5.jgmc  ");
		sql.append("from XG_PJPY_NEW_PJJGB t1 ");
		sql.append("left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm  ");
		sql.append("left join XG_PJPY_NEW_XMXZ t3 on t1.xzdm=t3.xmxzdm  ");
		sql.append("left join xqdzb t4 on t1.xq=t4.xqdm ");
		sql.append("left join view_xsxxb t5 on t1.xh=t5.xh ");
		sql.append(")t where 1=1 and xn=? ");
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			sql.append("and xq= ? ");
		}
		if(StringUtils.equals("12036",Base.xxdm) &&
				StringUtils.equals("getPjjghzMdListByXy",model.getType())){
			if(!"null".equals(model.getXydm()) && StringUtils.isNotNull(model.getXydm())){
				sql.append("and xydm= '"+model.getXydm()+"' ");
			}
		}

		sql.append("and xmmc=? and lxdm=? and xzdm=? ");
		sql.append(searchTj);
		String[] strs=null;
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			strs=new String[]{model.getXn(),model.getXq(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}else{
			strs=new String[]{model.getXn(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}
		
		return getPageList(model,sql.toString(),StringUtils.joinStrArr(strs,inputV));
	}
	
	/**
	 * @描述：通用sql方法，取出存储的获奖信息
	 * @作者：姜舟[工号:1529]
	 * @日期：2017年9月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */

		
	public HashMap<String, String> getZjlyByXhMap(String xh,String xn){
		//浙江旅游国家奖学金查询
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_gjjxjjg where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//浙江旅游的综测排名查询
	public HashMap<String, String> getZjlyByPm(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from VIEW_ZHSZFPM where  xh =? and xn=? ");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//取励志奖学金中的所有信息
	public HashMap<String,String> getZjlylzByXhMap(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_gjlzjxjjg where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//励志奖学金中的学习情况查询
	public HashMap<String,String> getZjlyXxqkCj(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from VIEW_SYS_XSCJXX where xh=? and xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//浙江省政府奖学金修改里面的信息（国奖字段）
	public HashMap<String, String> getZjszfByXhMap(String xh,String xn){
		//浙江旅游国家奖学金查询
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_zfjxjjg where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	
	}
	//浙江旅游职业学院奖学金申请表（国奖字段）
	public HashMap<String, String> getZjlyzyxyfByXhMap(String xh,String xn){		
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_xyjxjjgb where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//省级优秀毕业生
	public HashMap<String,String> getZjlySjyxbys(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select a.*,to_char(a.f_sqsj,'yyyy-mm-dd') sqsj from zfsoft_bpmx.view_bpmx_sjyxbysjgb a where a.f_xh=? and a.f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//浙江旅游学院优秀毕业生
	public HashMap<String,String> getZjlyxyyxbys(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select a.*,to_char(a.f_sqsj,'yyyy-mm-dd') sqsj from zfsoft_bpmx.view_bpmx_yjyxbysjgb a where a.f_xh=? and a.f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	public boolean isExist(String xh, String xn) {
		String sql = "select count(1) num from zfsoft_bpmx.view_bpmx_yjyxbysjgb where f_xh = ? and f_sqxn=?" ;
		String num = dao.getOneRs(sql, new String[]{xh,xn}, "num");
		return Integer.valueOf(num)>0;
	}
	//浙江中医药 获得奖学金等级
	public String getJxjmcByXhXn(String xh, String xn) {
		StringBuilder sql =  new StringBuilder();
		sql.append("SELECT xmmc FROM XG_PJPY_NEW_PJJGB WHERE xh = ? AND xn = ?");
		sql.append("AND (XMMC LIKE '%一等%' OR XMMC LIKE '%二等%' OR XMMC LIKE '%三等%')");
		return dao.getOneRs(sql.toString(),new String[]{xh,xn},"xmmc");
	}
	
	/**
	 * @描述: 青岛滨海学院个性化取-学年综合成绩
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-10-16 下午06:40:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXnzhcj(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select fs,bjpm from xg_zhcp_zcfsb where xmdm in ");
		sql.append("(select xmdm from xg_zhcp_zcxmb where xn = ? and xmmc = '学年综合成绩') and xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh});
	}
	
	/**
	 * @描述: 根据学号取当前评奖周期的参评班级人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-18 下午07:05:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXsszcpbjRsForxh (String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select count(*)bjzrs from xg_pjpy_new_cpmdb where xn = ? and bjdm in ");
		sql.append("(select bjdm from xg_pjpy_new_cpmdb where xh = ? and xn = ?) ");
		return dao.getOneRs(sql.toString(), new String[]{xn,xh,xn}, "bjzrs");
	}
	
	/**
	 * @描述: 算表格中出的金额总数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-3 下午08:41:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getExcleZje(BzjljgModel model) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql.append("select sum(nvl(t.xmje,0))zje from (");
		sql.append("select t1.*,t2.xmlxmc,t3.xmxzmc,t4.xqmc ");
		sql.append("from XG_PJPY_NEW_PJJGB t1 ");
		sql.append("left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm  ");
		sql.append("left join XG_PJPY_NEW_XMXZ t3 on t1.xzdm=t3.xmxzdm  ");
		sql.append("left join xqdzb t4 on t1.xq=t4.xqdm ");
		sql.append(")t where 1=1 and xn=? ");
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			sql.append("and xq= ? ");
		}
		sql.append("and xmmc=? and lxdm=? and xzdm=? ");
		sql.append(searchTj);
		String[] strs=null;
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			strs=new String[]{model.getXn(),model.getXq(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}else{
			strs=new String[]{model.getXn(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}
		return dao.getOneRs(sql.toString(), StringUtils.joinStrArr(strs,inputV), "zje");
	}
	/**
	 * @描述：通用sql，查询课程中优秀、良好、中等、及格课程各多少门
	 * @作者：姜舟[工号:1529]
	 * @日期：2017年11月9日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	//西安科技大学，优良中及以及必修课门数
	public HashMap<String,String> getXakjdxylzjbxkms(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select  nvl(round(sum(case when cj >=60 and cj<70 then '1' end),0),0)jgms," +
				"nvl(round(sum(case when cj >=70 and cj<80 then '1' end),0),0)zdms ," +
				"nvl(round(sum(case when cj >=80 and cj<90 then '1' end),0),0)lhms," +
				"nvl(round(sum(case when cj >=90 and cj<=100 then '1' end),0),0)yxms," +
				"nvl(round(sum(case when cj >=60  then '1' end),0),0)bxkjgms , " +
				"count(xh) as bxkms from cjb  where  kcxz like '%必修%'and xh = ? and xn = ? group by xh");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//西安科技大学，本学年获取获奖情况
	public List<HashMap<String, String>> getHjqk(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.xn,t1.xmmc from xg_pjpy_new_pjjgb t1 left join xqdzb t2 on t1.xq=t2.xqdm where t1.xh=?and xn =?");
		return dao.getListNotOut(sql.toString(), new String[] {xh,xn});
	}
	//重庆工商大学
	public List<HashMap<String, String>> getCjsxqList(String xn, String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select kcmc,cj from view_zhhcjb where xn = ? and xq like '%1%' and  xh = ? order by kcmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xh});
	}
	public List<HashMap<String, String>> getCjxsqList(String xn, String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select kcmc,cj from view_zhhcjb where xn = ? and xq like '%2%' and  xh = ? order by kcmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xh});
	}
	//徐州医药--1529
	public String getkssj( String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select kssj from VIEW_NEW_DC_DTXX_JG where xh = ? and jdmc like '%团员%'  ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "rtsj");
	}
	public String gettyrs( String cpbjdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(xh) as gs from view_xsbfxx where zzmmmc like '%团员%' and bjdm =? ");
		return dao.getOneRs(sql.toString(), new String[]{cpbjdm}, "gs");
	}

	public List<HashMap<String, String>> getHjqkInfoMap(String xh) {
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from xg_xsxx_new_hjqkb where xh=? order by hjsj");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"hjsj","fjdw","hjmc","bz"});
	}
	//中央民族大学,取单科最低最高分
	public  HashMap<String,String> getCjfsList(String xh, String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select min(cj) as min  , max(cj) as max, round(avg(cj),2) as pjcjjdfs  from cjb where xh = ?  and xn = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}

	//重庆工商，取审核人,审核时间（个性化，三级审核）
	public List<HashMap<String, String>> getShrList(String xh, String xn, String xq, String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select to_char(to_date(shsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyyMMdd')  shsj , xm ,shyj from (select t4.id, t4.xh, t4.xn, t4.xq,t4.xmdm, t4.xmmc, t.xm, t.shyj, t.shr, t.shsj, " +
				"t.mc, row_number() over(partition by t4.id, t4.xh, t4.xn, t4.xq, t4.xmdm, t4.xmmc, " +
				"t.mc order by t.shsj desc) rn from XG_PJPY_NEW_PJJGB t4 left join (select t1.*, t2.sqid, t3.*," +
				"t5.xm from xg_xtwh_shztb t1 left join xg_pjpy_new_xmsq t2 on t1.ywid = t2.sqid left join " +
				"xg_xtwh_spgw t3 on t1.gwid = t3.id left join fdyxxb t5 on t1.shr = t5.zgh order by shsj desc) t " +
				"on t4.id = t.ywid where t4.xh = ? and t4.xn = ? and t4.xq = ? and t4.xmdm = ? group by t4.id, t4.xh," +
				" t4.xn, t4.xq, t4.xmdm, t4.xmmc, t.xm, t.shyj, t.shr, t.shsj, t.mc) where rn = 1");
		String[] inputValue = { xh,xn,xq,xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * @description	： 获取表彰单名list
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-27 下午02:56:32
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getBzmdList(BzjljgModel t) throws Exception{
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,nvl(c.xmmc,a.xmmc) xmmc");
		sql.append(" from xg_pjpy_new_pjjgb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_pjpy_new_pjxmb c on a.xmdm = c.xmdm");
		
		//sql.append(" where a.xmdm is not null and c.xmmc is not null");
		
		//sql.append(" group by a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,a.xmdm,c.xmmc order by xymc,xmmc,bjmc asc");
		sql.append(" ) where 1=1");
		sql.append(searchTj);
		sql.append(" group by xn,xq,xh,xm,xymc,xydm,bjmc,bjdm,xmmc order by xymc,xmmc,bjmc asc");
		return dao.getListNotOut(sql.toString(), inputV);		
	}
	
	/**
	 * @description	： 获取学院获奖人数列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-28 上午09:10:46
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXyhjrsList(BzjljgModel t) throws Exception{
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xn,xqmc,xydm,xymc,xmmc,count(1) xyhjrs from (select * from (");
		sql.append(" select a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,nvl(c.xmmc,a.xmmc) xmmc,d.xqmc");
		sql.append(" from xg_pjpy_new_pjjgb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_pjpy_new_pjxmb c on a.xmdm = c.xmdm");
		sql.append(" left join xqdzb d on a.xq = d.xqdm");
		
		//sql.append(" where a.xmdm is not null and c.xmmc is not null");
		
		//sql.append(" group by a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,a.xmdm,c.xmmc,d.xqmc");
		sql.append(" ) where 1=1");
		sql.append(searchTj);
		sql.append(" group by xn,xq,xh,xm,xymc,xydm,bjmc,bjdm,xmmc,xqmc");
		sql.append(" ) group by xn,xqmc,xydm,xymc,xmmc order by xymc,xmmc asc");
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/**
	 * @description	： 获取班级获奖人数列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-28 上午09:16:33
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getBjhjrsList(BzjljgModel t) throws Exception{
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xydm,bjdm,bjmc,xmmc,count(1) bjhjrs from (select * from (");
		sql.append(" select a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,nvl(c.xmmc,a.xmmc) xmmc");
		sql.append(" from xg_pjpy_new_pjjgb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_pjpy_new_pjxmb c on a.xmdm = c.xmdm");
		
		//sql.append(" where a.xmdm is not null and c.xmmc is not null");
		
		//sql.append(" group by a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,a.xmdm,c.xmmc");
		sql.append(" ) where 1=1");
		sql.append(searchTj);
		sql.append(" group by xn,xq,xh,xm,xymc,xydm,bjmc,bjdm,xmmc");
		sql.append(" ) group by xydm,bjdm,bjmc,xmmc order by xmmc,bjmc asc");
		return dao.getListNotOut(sql.toString(), inputV);
	} 
	
	/**
	 * 
	 * @描述: 奖学金汇总总和List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-24 上午11:48:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjhzzhList(BzjljgModel t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum rn from (");
		sql.append(" select t.xh,");
		sql.append(" t1.XM,");
		sql.append(" t1.SFZH,");
		sql.append(" t1.BJMC,");
		sql.append(" t.xmmc,");
		sql.append(" t.xmje,");
		sql.append(" t.sqly,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t1.XB,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.MZ,");
		sql.append(" t1.XZ,");
		sql.append(" t1.XJZTM,");
		sql.append(" t.lxdm,");
		sql.append(" t.xzdm");
		sql.append(" from xg_pjpy_new_pjjgb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" order by t1.XYDM,t1.BJDM,t1.XH");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}

	/**
	 * @throws Exception 
	 * @描述:获取财务用表所需信息(苏州卫生职业技术学院)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-3-9 下午03:20:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCwybList(BzjljgModel model, User user) throws Exception {
		
		SearchModel searchModel = model.getSearchModel();
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*  from VIEW_NEW_DC_PJPY_PJJG t1  where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	/**
	 * @throws Exception 
	 * @描述:获取财务用表所需信息合计(苏州卫生职业技术学院)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-3-9 下午03:20:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public String getCwybSum(BzjljgModel t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(xmje) hj from ( ");
		sql.append("select t1.*  from VIEW_NEW_DC_PJPY_PJJG t1  where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" )");
		
		
		/*SearchModel searchModel = model.getSearchModel();
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT sum(xmje) hj FROM (");
		sql.append("select t1.xn,t1.xmje,t1.xmmc,t1.xh,t2.xm,t2.zymc, t2.sfzh ");
		sql.append(" from xg_pjpy_new_pjjgb t1");
		sql.append("  left join VIEW_XSXXB   t2 on t1.xh = t2.xh");
		sql.append(") t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);*/
		return dao.getOneRs(sql.toString(), inputV,"hj");
	}
	
	/**
	 * 
	 * @描述: 中国美术学院个性化奖项List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-21 下午05:01:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param n
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getHjxxList(String xh,String xn,String xq,String n){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.hjmc,t1.hjsj,t1.fjdw from xg_pjpy_hjxxjl t ");
		sql.append(" left join xg_xsxx_new_hjqk_jgb t1 on t.id = t1.id where t.xn = ? and t.xq = ? and t.xh = ? and rownum <= ?");
		sql.append(" order by t1.hjsj desc");
		return dao.getListNotOut(sql.toString(),new String[]{xn,xq,xh,n});
	}
	
	/**
	 * 
	 * @描述: 获取审核时间
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-21 下午05:49:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getHjshsjList(String xmdm,String days){
		StringBuilder sql = new StringBuilder();
		sql.append(" select rq,substr(rq,0,4) years,substr(rq,6,2) mon,substr(rq,9,2) days  from");
		sql.append(" (select to_char(( to_date(xxtbsj,'yyyy-mm-dd hh24:mi:ss') - ?),'yyyy-mm-dd') rq from xg_pjpy_new_pjxmb where xmdm = ?)");
		return dao.getMapNotOut(sql.toString(), new String[]{days,xmdm});
		
	}

	public String getXymcBydm(String xydm) {
		String sql = "select xymc from VIEW_NJXYZYBJ_ALL where xydm=?";
		return dao.getOneRs(sql,new String[]{xydm},"xymc");
	}
	
	//中国美院 学生旷课补考违纪信息
	public  HashMap<String,String> getKkbkxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select kkjl,bkjl,bkjg,wjqk from xsjxj_wx where xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
}
	

