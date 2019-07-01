/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:32:06 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqDao;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地结果DAO 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-29 下午03:32:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdjgDao extends SuperDAOImpl<CdjgForm> {

	/**
	 * @属性： KEY_NAME 主键名
	 */
	private static final String KEY_NAME = "jgid";
	/**
	 * @属性： TABLE_NAME 表名
	 */
	private static final String TABLE_NAME = "XG_XLJK_CDGL_CDSQJGB";
	/**
	 * @属性： MODEL_CLAZZ class 类型
	 */
	private static final Class<CdjgForm> MODEL_CLAZZ = CdjgForm.class;
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdjgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.cyrs,a.fzrxm,a.fzrlxfs,a.jgid,")
		.append("       a.xh,")
		.append("       a.cdid,")
		.append("       a.bmlbdm,")
		.append("       d.bmmc bmlbmc,")
		.append("       to_char(to_date(a.sqsj , 'yyyy-MM-dd HH24:mi:ss') , 'yyyy-MM-dd') sqsj,")
		.append("       a.sqsjdkssj,")
		.append("       a.sqsjdjssj,")
		.append("       a.sqly,")
		.append("       a.shzt,")
		.append("       a.splcid,")
		.append("       a.sjly,")
		.append("       a.sqid,")
		.append("       b.xm,")
		.append("       b.xb,")
		.append("       b.nj,")
		.append("       b.xydm,")
		.append("       b.xymc,")
		.append("       b.zydm,")
		.append("       b.zymc,")
		.append("       b.bjdm,")
		.append("       b.bjmc,b.sjhm,")
		.append("       c.cdmc,c.lxr,c.lxfs,c.xfgfsyxy,")
		.append("       c.ld,")
		.append("       c.fj,")
		.append("       c.rnrs,")
		.append("       c.sfbz,")
		.append("       c.yt,")
		.append("       c.jbsbjs,")
		.append("       c.dwkfsjkssj || '-' || c.dwkfsjjssj dwkfsj, ")
		.append("       a.sqsjdkssj || '至' ||  a.sqsjdjssj sqsjd")
		.append("  from XG_XLJK_CDGL_CDSQJGB a")
		.append("  left join view_xsjbxx b")
		.append("    on a.xh = b.xh")
		.append("  left join zxbz_xxbmdm d on a.bmlbdm = d.bmdm ")
		.append("  left join XG_XLJK_CDGL_CDXXB c")
		.append("    on a.cdid = c.cdid) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 获取场地结果信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-29 上午10:19:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getCdjgxx(String jgid){
		StringBuffer sql = new StringBuffer();
		sql.append("select b.xfgfsyxy,a.*,b.cdmc , b.ld , b.fj , b.rnrs , b.sfbz , b.dwkfsjkssj, b.dwkfsjjssj , b.yt , b.jbsbjs , b.sfkfsq , b.splcid ,")
		.append("    b.lxr,b.lxfs,b.filepath, c.xb , c.xm , c.nj , c.xymc , c.zymc , c.bjmc , d.bmmc ")
		.append("  from XG_XLJK_CDGL_CDSQJGB a ")
		.append("  left join XG_XLJK_CDGL_CDXXB b ")
		.append("    on a.cdid = b.cdid ")
		.append("  left join view_xsjbxx c ")
		.append("    on a.xh = c.xh ")
		.append("  left join zxbz_xxbmdm d on a.bmlbdm = d.bmdm where jgid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{jgid});
	}
	
	/**
	 * 
	 * @描述:根据申请id删除结果表数据
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-30 上午09:11:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean deleteJgBySqid(String sqid) throws Exception{
		String sql = "delete from XG_XLJK_CDGL_CDSQJGB where sqid = ? ";
		return dao.runUpdate(sql, new String[]{sqid});
	}

	
	/**
	 * 
	 * @描述:获取已经在申请中的或者已经申请通过的场地申请列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 下午02:15:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getYxCdsq(String cdid){
		
		String sql = "select * from XG_XLJK_CDGL_CDSQJGB a WHERE a.cdid = ?";
		
		return dao.getListNotOut(sql, new String[]{cdid});
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CdjgDao.MODEL_CLAZZ);
		super.setKey(CdjgDao.KEY_NAME);
		super.setTableName(CdjgDao.TABLE_NAME);

	}

	public boolean updatePjxx(CdjgForm t) throws Exception {
		String sql = "update xg_xljk_cdgl_cdsqjgb set pj=?,pjbz=? where sqid=?";
		return dao.runUpdate(sql,new String[]{t.getPj(),t.getPjbz(),t.getSqid()});
	}

	public boolean isExistPj(CdjgForm model) {	
		String sql = "select count(1) num from xg_xljk_cdgl_cdsqjgb where sqid =? and pj is not null " ;
		String num = dao.getOneRs(sql, new String[]{model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

}
