/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.hczd;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @className	： HczdDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2017-11-22 下午04:01:21
 * @version 	V1.0 
 */

public class HczdDao extends SuperDAOImpl<HczdForm>{

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2017-11-22 下午04:01:45
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HczdForm t)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select zdmc,zdpy,zdjp,shenfen from xg_rcsw_hczdb where 1 = 1");
		String[] shen = null;
		String[] zd = null;
		if(StringUtils.isNotNull(t.getShenfen())){
			sb.append(" and shenfen like '%' || ? || '%'");
			shen = new String[]{t.getShenfen()};
		}
		if(StringUtils.isNotNull(t.getZdmc())){
			sb.append(" and zdmc like '%' || ? || '%'");
			shen = new String[]{t.getZdmc()};
		}
		sb.append(" order by zdjp asc");
		return getPageList(t, sb.toString(), StringUtils.joinStrArr(shen,zd));
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2017-11-22 下午04:01:45
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HczdForm t, User user)
			throws Exception {
		return null;
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2017-11-22 下午04:01:45
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(HczdForm.class);
		super.setKey("zdmc");
		super.setTableName("XG_RCSW_HCZDB");		
	}
	
	/**
	 * @description	： 统计数量
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-23 下午01:45:01
	 * @param t
	 * @return
	 */
	public int countSl(HczdForm t){
		String sql = "select count(1) rn from XG_RCSW_HCZDB where zdmc = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{t.getZdmc()}, "rn"));
	}
	
}
