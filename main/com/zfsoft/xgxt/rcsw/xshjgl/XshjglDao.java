/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午04:49:53 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.fyff.ffjg.FyffjgForm;
import com.zfsoft.xgxt.rcsw.xshjgl.XshjglForm;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2015-9-14 上午09:34:02 
 * @版本： V5.17
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XshjglDao extends SuperDAOImpl<XshjglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XshjglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XshjglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from (select t.*, ");
		sql.append(" t1.XB, ");
		sql.append(" t1.BJDM, ");
		sql.append(" t1.BJMC, ");
		sql.append(" t1.NJ, ");
		sql.append(" t1.XYDM, ");
		sql.append(" t1.XYMC, ");
		sql.append(" t1.XM, ");
		sql.append(" decode(t.qyzt, '0', '迁入', '1', '迁出') qyztmc ");
		sql.append(" from xg_rcsw_hjgl t ");
		sql.append(" left join view_xsbfxx t1 ");
		sql.append(" on t.xh = t1.XH) a ");
		sql.append(" where 1 = 1 ");
	    sql.append(searchTjByUser);
	    sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_hjgl");
		super.setKey("hjglid");
		super.setClass(XshjglForm.class);
	}
	
	//单个查询
	public HashMap<String, String> getOneInfo(XshjglForm t) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(" from (select t.*,t1.XM,t1.XB,t1.LXDH,t1.NJ,t1.XYMC,t1.ZYMC,t1.BJMC,t1.SFZH,t1.JG, ");
		sql.append(" decode(t.qyzt, '0', '迁入', '1', '迁出') qyztmc ");
		sql.append(" from xg_rcsw_hjgl t ");
		sql.append(" left join view_xsbfxx t1 ");
		sql.append(" on t.xh = t1.XH) a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
public String checkExistForSave(XshjglForm form){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String hjglid =  form.getHjglid();
		sql.append(" select count(1) num from xg_rcsw_hjgl ");
		sql.append(" where xh = ? and qyzt = ?  and qysj = ? ");
		params.add(form.getXh());
		params.add(form.getQyzt());
		params.add(form.getQysj());
		if(!StringUtils.isEmpty(hjglid)){
			sql.append(" and hjglid <> ? ");
			params.add(hjglid);
		}
		
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}


/**
 * @描述: 导出功能
 * @作者：孟威[工号：1186]
 * @日期：2015-10-14 上午08:30:10
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param t
 * @param user
 * @return
 * @throws Exception
 * List<HashMap<String,String>> 返回类型 
 * @throws
 */
public List<HashMap<String, String>> getXshjgldcList(XshjglForm t, User user)
throws Exception {
String searchTj = SearchService.getSearchTj(t.getSearchModel());
String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
String[] inputV = SearchService.getTjInput(t.getSearchModel());

StringBuilder sql = new StringBuilder();
sql.append(" select * ");
sql.append(" from (select t.*, ");
sql.append(" t1.XB, ");
sql.append(" t1.BJDM, ");
sql.append(" t1.BJMC, ");
sql.append(" t1.NJ, ");
sql.append(" t1.XYDM, ");
sql.append(" t1.XYMC, ");
sql.append(" t1.XM, ");
sql.append(" t1.zymc, ");
sql.append(" (select t2.qxmc from dmk_qx t2 where t1.jg=t2.qxdm) jgmc, ");
sql.append(" t1.lxdh, ");
sql.append(" decode(t.qyzt, '0', '迁入', '1', '迁出') qyztmc ");
sql.append(" from xg_rcsw_hjgl t ");
sql.append(" left join view_xsbfxx t1 ");
sql.append(" on t.xh = t1.XH) a ");
sql.append(" where 1 = 1 ");
sql.append(searchTjByUser);
sql.append(searchTj);

return dao.getListNotOut(sql.toString(), inputV);
}

}
