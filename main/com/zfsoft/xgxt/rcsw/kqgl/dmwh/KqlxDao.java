/**
 * @部门:学工产品事业部
 * @日期：2014-6-6 上午09:53:02 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考勤管理模块
 * @类功能描述: 考勤类型代码维护
 * @作者： 陶钢军[工号:1075]
 * @时间： 2014-6-6 上午09:53:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqlxDao extends SuperDAOImpl<KqlxForm> {

	/**
	 * 分页查询
	 */
	public List<HashMap<String, String>> getPageList(KqlxForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.kqlxdm,a.kqlxmc from xg_kqgl_kqlx a where 1=1 ");
		
			if (!StringUtil.isNull(t.getKqlxmc())){
				params.add(t.getKqlxmc());
				sql.append(" and kqlxmc like '%'||?||'%'");
			}
			
			sql.append(" order by to_number(kqlxdm) ");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqlxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	protected void setTableInfo() {
		super.setTableName("xg_kqgl_kqlx");
		super.setKey("kqlxdm");
	}
	
	
	/**
	 * 
	 * @描述:判断考勤类型名称是否已经存在
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 上午10:10:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String kqlxmcCheckExist(KqlxForm t) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_kqgl_kqlx where kqlxmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{t.getKqlxmc()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @描述:获取最大的考勤类型代码
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 上午10:11:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getMaxKqlxdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(kqlxdm)),0) kqlxdm from xg_kqgl_kqlx ";
		
		return dao.getOneRsint(sql);
	}
	
	
	/**
	 * 
	 * @描述:查询考勤登记表中是否存在
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 上午10:14:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] kqlxdmCheckExistForKqdj(String value) throws Exception{
		
		StringBuilder sql = new StringBuilder(" select distinct b.kqlxmc from xg_kqgl_kqdj a,xg_kqgl_kqlx b where a.kqlxdm = b.kqlxdm and a.kqlxdm in (" +value +") ");
		String[] xmmc=dao.getRs(sql.toString(), new String[]{}, "kqlxmc");
			
		return xmmc;
	}
	
	
	/**
	 * 
	 * @描述:获取考勤类型列表
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 上午10:18:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlxList() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select kqlxdm,kqlxmc from xg_kqgl_kqlx ");
		sb.append(" order by to_number(kqlxdm) ");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
}
