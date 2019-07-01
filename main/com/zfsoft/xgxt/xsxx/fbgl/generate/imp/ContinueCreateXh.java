/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 下午02:56:13 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: 继续生成学号
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-24 下午02:56:13
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ContinueCreateXh extends CreateXh {
	@Override
	public String getBaseSql() {
		sb.append("select * from (");
		sb.append("select t.*,t.nj||'_'||t.ksh pk from xg_xsxx_fbgl_xsxx_lsb t) where 1=1");
		String pk=getParam().get(0);
		if (StringUtils.isNotNull(pk)) {
			sb.append(" and pk in (");
			for(String id:pk.split(",")){
				sb.append("'");
				sb.append(id);
				sb.append("',");
			}
			sb.append("'-1')");
		}
		sb.append(" and xh is null");
		getParam().remove(0);
		return sb.toString();
	}
	/*
	      描述: @see com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateXh#getValue(java.lang.String, java.lang.String)
	 */
	
	@Override
	protected String getValue(String zd, String pk) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select " + zd + " from XG_XSXX_FBGL_XSXX_LSB where nj||'_'||ksh=?");
			return DAO.getInstance().getOneRs(sb.toString(),
					new String[] { pk }, zd);
		} catch (Exception e) {
			// 如果查询无此字段 则无默认值
			return null;
		}
	}
}
