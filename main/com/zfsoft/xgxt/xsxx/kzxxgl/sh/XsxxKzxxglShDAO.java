/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午09:34:23 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-19 上午09:34:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglShDAO extends SuperDAOImpl<XsxxKzxxglShForm> {
	/**
	 * @属性： KEY_NAME 主键名
	 */
	private static final String KEY_NAME = "sqid";
	/**
	 * @属性： TABLE_NAME 表名
	 */
	private static final String TABLE_NAME = "ZFXG_XSXX_KZXX_SQ";
	/**
	 * @属性： MODEL_CLAZZ class 类型
	 */
	private static final Class<XsxxKzxxglShForm> MODEL_CLAZZ = XsxxKzxxglShForm.class;
	
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsxxKzxxglShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsxxKzxxglShForm t,
			User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,row_number() over(partition by sqid order by shsj desc) rn  from ")
		.append("(select a.* , ")
		.append("'[' || b.mc || ':' || decode(a.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',a.shzt) || ']' shztmc, b.gwz from ")
		.append("(select a.sqid, a.xh , a.sqsj , a.splc, a.shzt as sqshzt , ")
		.append("b.xm,")
		.append("b.xb,")
		.append("b.nj,")
		.append("b.xydm,")
		.append("b.xymc,")
		.append("b.zydm,")
		.append("b.zymc,")
		.append("b.bjdm,")
		.append("b.bjmc,")
		.append("b.pycc,")
		.append("c.gwid as xtgwid , ")
		.append("c.shzt, ")
		.append("c.shsj, ")
		.append("c.guid as shid, ")
		.append("(select pyccmc from xg_xsxx_pyccdmb where pyccdm = b.pycc ) pyccmc ")
		.append(" from ZFXG_XSXX_KZXX_SQ a")
		.append(" left join view_xsjbxx b ")
		.append(" on a.xh = b.xh ")
		.append(" left join xg_xtwh_shztb c on a.sqid = c.ywid) a ")
		.append(" left join xg_xtwh_spgw b on a.xtgwid = b.id ")
		.append(" where a.xtgwid in (select spgw from xg_xtwh_spgwyh where spyh = '")
		.append(user.getUserName());
		
		if(DSH.equals(t.getType()))
			sql.append("') and a.shzt in ('0', '4')) t1) t2 where rn = 1  ");
		else if(YSH.equals(t.getType()))
			sql.append("') and a.shzt not in ('0', '4')) t1) t2 where rn = 1  ");
		
		sql.append(searchTjByUser)
		.append(" ")
		.append(searchTj)
		.append(" ")
		.append(shgwzByUser);
		
		return  getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(MODEL_CLAZZ);
		super.setKey(KEY_NAME);
		super.setTableName(TABLE_NAME);
	}

}
