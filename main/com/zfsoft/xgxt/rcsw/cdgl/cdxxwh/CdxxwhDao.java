/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 上午10:09:46 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地信息维护Dao
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 上午10:09:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdxxwhDao extends SuperDAOImpl<CdxxwhForm> {

	/**
	 * @属性： KEY_NAME 主键名
	 */
	private static final String KEY_NAME = "cdid";
	/**
	 * @属性： TABLE_NAME 表名
	 */
	private static final String TABLE_NAME = "XG_XLJK_CDGL_CDXXB";
	/**
	 * @属性： MODEL_CLAZZ class 类型
	 */
	private static final Class<CdxxwhForm> MODEL_CLAZZ = CdxxwhForm.class;
	
	/*
	 * 
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 *
	 * 分页查询
	 */
	@Override
	public List<HashMap<String, String>> getPageList(CdxxwhForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 
	 * 分页查询
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdxxwhForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select a.cdid,a.xfgfsyxy,")
		.append("       a.cdmc,")
		.append("       a.ld,")
		.append("       a.fj,")
		.append("       a.rnrs,")
		.append("       a.sfbz,")
		.append("       a.dwkfsjkssj,")
		.append("       a.dwkfsjjssj,")
		.append("       a.yt,")
		.append("       a.jbsbjs,")
		.append("       a.sfkfsq,")
		.append("       a.splcid,a.lxr,a.lxfs,")
		.append("       b.mc || '：' || replace(b.lcxx, ';', '->') lcxx,")
		.append("       a.dwkfsjkssj || '-' || a.dwkfsjjssj dwkfsj, ")
		.append("       decode(a.sfkfsq , '0' , '否' , '1' , '是' , '未知') sfkfsqmc ")
		.append("  from XG_XLJK_CDGL_CDXXB a")
		.append("  left join (select splc, mc, lcxx")
		.append("               from (select splc,")
		.append("                            a.mc,")
		.append("                            to_char(WM_CONCAT(c.mc)")
		.append("                                    over(partition by splc order by xh)) lcxx,")
		.append("                            row_number() over(partition by splc order by xh desc) lcxh")
		.append("                       from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c")
		.append("                      where a.id = b.splc")
		.append("                        and b.spgw = c.id)")
		.append("              where lcxh = 1) b")
		.append("    on a.splcid = b.splc where 1=1 ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws Exception 
	 * 
	 * @描述:获取popup场地信息列表信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午01:45:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPopupCdxx(CdxxwhForm model) throws Exception{
		StringBuffer searchTj = new StringBuffer();	
		
		//生成高级查询相关条件、条件值 
		String search_cdmc = model.getSearch_cdmc();
		String search_rnrsmin = model.getSearch_rnrsmin();
		String search_rnrsmax = model.getSearch_rnrsmax();
		String search_yt = model.getSearch_yt();
		String search_dwkfsjkssj = model.getSearch_dwkfsjkssj();
		String search_dwkfsjjssj = model.getSearch_dwkfsjjssj();
		
		String search_sfkfsq  = model.getSearch_sfkfsq();
		
		if(StringUtils.isNotBlank(search_cdmc)){
			searchTj.append(" and cdmc like '%").append(search_cdmc).append("%' ");
		}
		if(StringUtils.isNotBlank(search_rnrsmin)){
			searchTj.append(" and to_number(a.rnrs) >= ").append(search_rnrsmin);
		}
		if(StringUtils.isNotBlank(search_rnrsmax)){
			searchTj.append(" and to_number(a.rnrs) <= ").append(search_rnrsmax);
		}
		if(StringUtils.isNotBlank(search_yt)){
			searchTj.append(" and a.yt like '%").append(search_yt).append("%' ");
		}
		if(StringUtils.isNotBlank(search_dwkfsjkssj)){
			searchTj.append(" and a.dwkfsjkssj <= '").append(search_dwkfsjkssj).append("' ");
		}
		if(StringUtils.isNotBlank(search_dwkfsjjssj)){
			searchTj.append(" and a.dwkfsjjssj >= '").append(search_dwkfsjjssj).append("' ");
		}
		if(StringUtils.isNotBlank(search_sfkfsq) && StringUtils.equals("2", search_sfkfsq)){
			searchTj.append(" and 1 = 1 ");
		}else if(StringUtils.isNotBlank(search_sfkfsq) && StringUtils.equals("1", search_sfkfsq)){
			searchTj.append(" and a.sfkfsq = '1' ");
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.cdid,")
		.append("       a.cdmc,")
		.append("       a.ld,")
		.append("       a.fj,")
		.append("       a.rnrs,")
		.append("       a.sfbz,")
		.append("       a.dwkfsjkssj,")
		.append("       a.dwkfsjjssj,")
		.append("       a.yt,")
		.append("       a.jbsbjs,")
		.append("       a.sfkfsq,")
		.append("       a.splcid,a.lxr,a.lxfs,")
		.append("       b.mc || '：' || replace(b.lcxx, ';', '->') lcxx,")
		.append("       a.dwkfsjkssj || '-' || a.dwkfsjjssj dwkfsj, ")
		.append("       decode(a.sfkfsq , '0' , '否' , '1' , '是' , '未知') sfkfsqmc ")
		.append("  from XG_XLJK_CDGL_CDXXB a")
		.append("  left join (select splc, mc, lcxx")
		.append("               from (select splc,")
		.append("                            a.mc,")
		.append("                            to_char(WM_CONCAT(c.mc)")
		.append("                                    over(partition by splc order by xh)) lcxx,")
		.append("                            row_number() over(partition by splc order by xh desc) lcxh")
		.append("                       from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c")
		.append("                      where a.id = b.splc")
		.append("                        and b.spgw = c.id)")
		.append("              where lcxh = 1) b")
		.append("    on a.splcid = b.splc where  1 = 1 ")
		.append(searchTj);
		
		return getPageList(model, sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:根据场地ID获取场地信息Map
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午04:21:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getCdxxByCdid(String cdid){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.cdid,a.xfgfsyxy,")
		.append("       a.cdmc,")
		.append("       a.ld,")
		.append("       a.fj,")
		.append("       a.rnrs,")
		.append("       a.sfbz,")
		.append("       a.dwkfsjkssj,")
		.append("       a.dwkfsjjssj,")
		.append("       a.yt,")
		.append("       a.jbsbjs,")
		.append("       a.sfkfsq,")
		.append("       a.splcid,")
		.append("       a.qxfw,a.lxr,a.lxfs,a.filepath,a.xysfilepath,")
		.append("       b.mc || '：' || replace(b.lcxx, ';', '->') lcxx,")
		.append("       a.dwkfsjkssj || '-' || a.dwkfsjjssj dwkfsj, ")
		.append("       decode(a.sfkfsq , '0' , '否' , '1' , '是' , '未知') sfkfsqmc ")
		.append("  from XG_XLJK_CDGL_CDXXB a")
		.append("  left join (select splc, mc, lcxx")
		.append("               from (select splc,")
		.append("                            a.mc,")
		.append("                            to_char(WM_CONCAT(c.mc)")
		.append("                                    over(partition by splc order by xh)) lcxx,")
		.append("                            row_number() over(partition by splc order by xh desc) lcxh")
		.append("                       from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c")
		.append("                      where a.id = b.splc")
		.append("                        and b.spgw = c.id)")
		.append("              where lcxh = 1) b")
		.append("    on a.splcid = b.splc where 1=1 ")
		.append(" and cdid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{cdid});
	}
	
	/**
	 * 
	 * @描述:根据场地id检查是否被申请
	 */
	public boolean check(String cdid){
		String sql1 = "select * from XG_XLJK_CDGL_CDSQJGB a where a.cdid = ?";
		String sql2 = "select * from XG_XLJK_CDGL_CDSQB a where a.cdid = ?";
		return dao.getListNotOut(sql1, new String[]{cdid}).size() + dao.getListNotOut(sql2, new String[]{cdid}).size() >0 ? true : false;
	}
	
	/**
	 * 
	 * @描述:根据场地名称获取场地数
	 */
	public int getCdsByCdmc(String cdmc){
		String sql = "select count(1) cds from XG_XLJK_CDGL_CDXXB a where a.cdmc = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{cdmc}, new String[]{"cds"})[0]);
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CdxxwhDao.MODEL_CLAZZ);
		super.setKey(CdxxwhDao.KEY_NAME);
		super.setTableName(CdxxwhDao.TABLE_NAME);
	}
	
	/**
	 * 
	 * @描述: 获取场地信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-17 下午03:44:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCdxx(String sqid,String tableName){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.cdmc, t1.jbsbjs,t2.bmmc");
		sql.append(" from "+tableName+" t");
		sql.append(" left join XG_XLJK_CDGL_CDXXB t1");
		sql.append(" on t.cdid = t1.cdid");
		sql.append(" left join ZXBZ_XXBMDM t2");
		sql.append(" on t.bmlbdm = t2.bmdm");
		if(tableName.toUpperCase().equals("XG_XLJK_CDGL_CDSQB")){
			sql.append(" where t.sqid = ?");
		}else{
			sql.append(" where t.jgid = ?");
		}
		
		return dao.getMapNotOut(sql.toString(),new String[]{sqid});
	}
	
	/**
	 * 
	 * @描述: 获取审核意见
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-17 下午04:24:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjList(String ywid){
		StringBuilder sql = new StringBuilder();
		sql.append("  select shyj from xg_xtwh_shztb where ywid = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{ywid});
	}

}
