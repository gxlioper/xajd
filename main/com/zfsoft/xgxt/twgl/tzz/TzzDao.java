/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.twgl.tzz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @className	： TzzDao
 * @description	： 团组织dao(描述这个类的作用)
 * @author 		：lj（1282）
 * @date		： 2018-5-14 上午11:50:02
 * @version 	V1.0 
 */

public class TzzDao extends SuperDAOImpl<TzzModel>{

	/**
	 * @description	： TODO
	 * @author 		：lj（1282）
	 * @date 		：2018-5-14 上午11:54:44
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TzzModel t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	： 查询列表
	 * @author 		：lj（1282）
	 * @date 		：2018-5-14 上午11:54:44
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TzzModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select * from xg_zzgl_zzxxb order by zzmc desc) t");
//		sql.append(" ");
//		sql.append(" ");
//		sql.append(" ");
//		sql.append(" ");
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		if(!StringUtil.isNull(t.getXh())){
			sql.append(" and not exists (select 1 from xg_tgbgl_tgbjgb a where a.rzzz = t.zzid and a.xh ='"+t.getXh()+"')");
		}
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @description	： TODO
	 * @author 		：lj（1282）
	 * @date 		：2018-5-14 上午11:54:44
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(TzzModel.class);
		super.setKey("zzid");
		super.setTableName("xg_zzgl_zzxxb");
		
	}
	
	/**
	 * @description	： 验证名称
	 * @author 		： lj（1282）
	 * @date 		：2018-5-15 上午10:01:37
	 * @param tzz
	 * @return
	 */
	public int countMc(TzzModel tzz){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) rn from xg_zzgl_zzxxb where zzmc = ?");
		String[] params = null;
		params = new String[]{tzz.getZzmc()};
		if(StringUtils.isNotNull(tzz.getZzid())){
			sb.append(" and zzid <> ?");
			params = new String[]{tzz.getZzmc(),tzz.getZzid()};
		}
		return Integer.valueOf(dao.getOneRs(sb.toString(), params, "rn"));
	}
	
}
