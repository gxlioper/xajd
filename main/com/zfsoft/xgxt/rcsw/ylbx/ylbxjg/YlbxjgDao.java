/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午11:02:27 
 */  
package com.zfsoft.xgxt.rcsw.ylbx.ylbxjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道结果
 * @作者： cq [工号:785]
 * @时间： 2015-1-21 上午11:02:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlbxjgDao extends SuperDAOImpl<YlbxjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1, ");
		sql.append(" t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
		sql.append(" t5.xqmc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc, ");
		sql.append(" nvl2(t3.rddc, '是', '否') sfkns ");
		sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
		sql.append(" from xg_rcsw_ylbx_jgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		if("10335".equals(Base.xxdm)){
			sql.append(" left join view_knsjgb_fqxrd t3 on t1.xh = t3.xh ");
		}else{
			sql.append(" left join xg_xszz_new_knsjgb t3 on t1.xh = t3.xh and (t1.xn = t3.xn and decode(t3.xq,'on',nvl(t1.xq,1),t3.xq) = nvl(t1.xq,1)) ");
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getXjbxmdList(YlbxjgForm t, User user)
	throws Exception {
	//生成高级查询相关条件、条件值 
	String searchTj = SearchService.getSearchTj(t.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
	String[] inputV = SearchService.getTjInput(t.getSearchModel());
	StringBuilder sql = new StringBuilder();
	sql.append(" select a.* from ( ");
	sql.append(" select t1.zd2,(to_number(substr(t1.xn,1,4))+to_number(nvl(t1.zd2,0)))tbjzsj,(to_number(nvl(t2.nj,0)) + to_number(nvl(t2.xz,0))) njxz,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1, ");
	sql.append(" t2.zzmmmc,t2.xm,t2.xb,t2.xz,t2.ssbhzd,t2.jtdz,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
	sql.append(" t5.xqmc ");
	sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc, ");
	sql.append(" nvl2(t3.rddc, '是', '否') sfkns ");
	sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
	sql.append(" from xg_rcsw_ylbx_jgb t1 ");
	sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
	sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
	if("10335".equals(Base.xxdm)){
		sql.append(" left join view_knsjgb_fqxrd t3 on t1.xh = t3.xh ");
	}else{
		sql.append(" left join xg_xszz_new_knsjgb t3 on t1.xh = t3.xh and (t1.xn = t3.xn and decode(t3.xq,'on',nvl(t1.xq,1),t3.xq) = nvl(t1.xq,1)) ");
	}
	sql.append(" ) a where 1 = 1 and a.njxz>a.tbjzsj and  a.tbjzsj is not null");
	sql.append(searchTjByUser);
	sql.append(searchTj);
	return getPageList(t, sql.toString(), inputV);
}
	/** 
	 * 导出
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxjgForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1, ");
		sql.append(" t2.mzmc,t2.ssbhzd,t2.jgmc,t2.sydmc,t2.hkszdmc,t2.jtdz,t2.jtyb,t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj,t2.zd5 hkxzmc, ");
		sql.append(" t5.xqmc,t2.zd5 hjxz ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_ylczbzbsb where dm=t1.zd1) zd1mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd2) zd2mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd3) zd3mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd4) zd4mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" ,(select yhmc from dmk_yh where yhdm=t1.zd7) zd7mc ");
		sql.append(" ,nvl2(t3.rddc, '是', '否') sfkns ");
		sql.append(" ,(select mc from XG_RCSW_YLBX_CBLXB where dm=t1.zd12) zd12mc ");
		sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
		if("14008".equals(Base.xxdm)) {
			sql.append(",z.grsjje,z.mzzzje");
		}
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm ");
		}
		sql.append(" from xg_rcsw_ylbx_jgb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		if("10335".equals(Base.xxdm)){
			sql.append(" left join (select a.xh, a.rddc, a.xn, a.xq from view_knsjgb_fqxrd a) t3 on t1.xh = t3.xh ");
		}else{
			sql.append(" left join (select a.xh, a.rddc, a.xn, a.xq from xg_xszz_new_knsjgb a) t3 on t1.xh = t3.xh and t1.xn = t3.xn and t1.xq = t3.xq ");
		}
		if("14008".equals(Base.xxdm)) {
			sql.append(" left join xg_rcsw_ylbx_ylczbzbsb z on t1.zd1 = z.dm ");
		}	
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_ylbx_jgb");
		super.setKey("jgid");
	}
	
	/**
	 * 
	 * @描述:重复性判断
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午10:37:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(YlbxjgForm model) {
		String jgid = "-1";
		if(!StringUtil.isNull(model.getJgid())){
			jgid = model.getJgid();
		}
		String zd = "xn";
		String[] arr = new String[] { model.getXn(),model.getXh(),jgid};
		if("14073".equals(Base.xxdm)){
			zd = "zd5";
			arr = new String[] { model.getZd5(),model.getXh(),jgid};
		}
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_jgb where "+zd+" = ? and xh = ? and jgid <> ? ");
		String num = dao.getOneRs(sql.toString(), arr, "num");
		return num;
	}
	
	
	/**
	 * 
	 * @描述:流程通过的数据不可以修改
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午10:44:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_ylbx_jgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
		
	}
	
	
	/*
	 * 查找姓名
	 * 
	 */
	public HashMap<String, String> getBbjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_ylbx_jgb a");
		sb.append(",xsxxb xsxx where a.xh=xsxx.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}
	
	
	/**
	 * 
	 * @描述:结果单个查询
	 * @作者：cq [工号：785]
	 * @日期：2015-1-22 下午04:58:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	
	public Map<String, String> viewOneYlbxjgList(String jgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.filepath,t1.filepath filepathtd,t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1 ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_ylczbzbsb where dm=t1.zd1) zd1mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd2) zd2mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd3) zd3mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd4) zd4mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" ,(select yhmc from dmk_yh where yhdm=t1.zd7) zd7mc ");
		sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
		sql.append(" ,(select mc from XG_RCSW_YLBX_CBLXB where dm=t1.zd12) zd12mc, ");
		if("12688".equals(Base.xxdm)){
			sql.append(" t1.bxjg,t1.lpsx,  ");
		}
		sql.append(" t2.xymc,t2.zymc,t2.nj,t2.bjmc,t2.xm,t2.xb from xg_rcsw_ylbx_jgb t1 left join view_xsxxb t2 on t1.xh=t2.xh");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{jgId});
	}
	
	
	/**
	 * 
	 * @描述: 取医疗缴费档次和人员类别以供资助报表打印
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-26 上午10:40:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJfdcRylbInfo(String xh) {
		
		String sql = " select t2.mc dcmc, t3.mc rylb from xg_rcsw_ylbx_jgb t1 left join xg_rcsw_ylbx_ylczbzbsb t2 on t1.zd1 = t2.dm left join xg_rcsw_ylbx_bsddyljgb t3 on t1.zd2 = t3.dm where t1.xh = ? ";
	
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:查询结果集学生照片
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-2-18 上午10:56:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXszpList(YlbxjgForm t, User user) 
		throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder tableSql = new StringBuilder();
	
		tableSql.append("select * from (select a.*, c.XM, c.NJ, c.XYDM, c.ZYDM, c.BJDM,c.sfzh,case when b.zp is null then '否' else '是' end sfdr,case ");
		tableSql.append("when b.xszp is null then '否' else '是' end xssfdr, rownum r from (");
		tableSql.append("select a.* from xg_rcsw_ylbx_jgb a ");
		tableSql.append("where 1=1 ");
		
		tableSql.append(" ) a left join xszpb b on a.xh = b.xh left join view_xsbfxx c on a.xh = c.xh) a where 1=1  and sfdr='是' ");
		tableSql.append(searchTjByUser);
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ");
		String[] outputValue=new String[]{"xh", "xm", "zd30" , "sfzh" };
		return dao.getList(tableSql.toString(), inputV, outputValue);
	}

}
