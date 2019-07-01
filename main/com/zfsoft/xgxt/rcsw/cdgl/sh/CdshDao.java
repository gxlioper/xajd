/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 上午10:25:33 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgDao;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgForm;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShForm;



/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 在读证明审核
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 上午10:25:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdshDao extends SuperDAOImpl<CdshForm> {
	/**
	 * @属性： KEY_NAME 主键名
	 */
	private static final String KEY_NAME = "sqid";
	/**
	 * @属性： TABLE_NAME 表名
	 */
	private static final String TABLE_NAME = "XG_XLJK_CDGL_CDSQB";
	/**
	 * @属性： MODEL_CLAZZ class 类型
	 */
	private static final Class<CdshForm> MODEL_CLAZZ = CdshForm.class;
	
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CdshForm t)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CdshForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm", "bjdm");	
 		String qxfw = SearchService.getQxfw(user, "a.xtgwid", "a.qxfw", "a.bmlbdm",searchTjByUser);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.* from (select t1.*,row_number() over(partition by sqid order by shsj desc) rn  from ")
		.append("(select a.* , '[' || b.mc || ':' || decode(a.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',a.shzt) || ']' shztmc,b.gwz from (select a.sqid,")
		.append("       a.cdid,")
		.append("       a.xh,")
		.append("       a.fzrxm,")
		.append("       a.fzrlxfs,")
		.append("       a.bmlbdm,")
		.append("       e.bmmc bmlbmc,")
		.append("       a.qxfw,")
		.append("       to_char(to_date(a.sqsj , 'yyyy-MM-dd HH24:mi:ss') , 'yyyy-MM-dd') sqsj,")
		.append("       a.sqsjdkssj,")
		.append("       a.sqsjdjssj,")
		.append("       a.sqly,")
		.append("       a.splcid,")
		.append("       b.xm,")
		.append("       b.nj,")
		.append("       b.xydm,")
		.append("       b.xymc,")
		.append("       b.zydm,")
		.append("       b.zymc,")
		.append("       b.bjdm,")
		.append("       b.bjmc,")
		.append("       c.cdmc,")
		.append("       c.ld,")
		.append("       c.fj,")
		.append("       c.rnrs,")
		.append("       c.sfbz,")
		.append("       c.dwkfsjkssj,")
		.append("       c.dwkfsjjssj,")
		.append("       c.yt,")
		.append("       c.jbsbjs,")
		.append("       c.sfkfsq,")
		.append("		d.gwid as xtgwid,")
		.append("       d.shzt,")
		.append("       d.shsj,")
		.append("       d.guid as shid,")
		.append("       a.sqsjdkssj || '至' ||  a.sqsjdjssj sqsjd")
		.append("  from XG_XLJK_CDGL_CDSQB a")
		.append("  left join view_xsjbxx b")
		.append("    on a.xh = b.xh")
		.append("  left join zxbz_xxbmdm e on a.bmlbdm = e.bmdm ")
		.append("  left join XG_XLJK_CDGL_CDXXB c")
		.append("    on a.cdid = c.cdid ")
		.append("  left join xg_xtwh_shztb d on a.sqid = d.ywid")
		.append(" ) a  left join xg_xtwh_spgw b on a.xtgwid = b.id where 1=1")
		.append(qxfw);
		
		if(DSH.equals(t.getType()))
			sql.append(" and a.shzt in ('0', '4')) t1) t2 where rn = 1  ");
		else if(YSH.equals(t.getType()))
			sql.append(" and a.shzt not in ('0', '4')) t1) t2 where rn = 1  ");
		
		sql.append(" ")
		.append(searchTj)
		.append(shgwzByUser);
		
		return  getPageList(t, sql.toString(), inputV);
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
		super.setClass(CdshDao.MODEL_CLAZZ);
		super.setKey(CdshDao.KEY_NAME);
		super.setTableName(CdshDao.TABLE_NAME);
	}
	
	/**
	 * 
	 * @描述: 查看使用中的审批流程
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-10-23 下午01:50:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSplcing(String splcid, String cdid){
		StringBuffer sb=new StringBuffer();
		sb.append("select sqid as ywid from XG_XLJK_CDGL_CDSQB a left join XG_XLJK_CDGL_CDXXB b on a.cdid = b.cdid where shzt in(?,?) and a.splcid=? and b.cdid=?");
		return dao.getListNotOut(sb.toString(), new String[]{Constants.YW_SHZ,Constants.YW_YTH,splcid,cdid});
	}
	

}
