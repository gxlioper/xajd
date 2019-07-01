/**
 * @部门:学工产品事业部
 * @日期：2014-4-2 上午09:40:48 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-费用发放-基础数据维护-发放途径
 * @类功能描述: 
 * @作者： cq [工号:785]
 * @时间： 2014-4-2 上午09:40:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyfftjDao extends SuperDAOImpl<FyfftjForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyfftjForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.fftjdm,a.fftj,(case when ffzh = '0' then '需填写' else '无需填写' end ) ffzh from xg_rcsw_fyff_ffltj a where 1=1 ");
		
		if (!StringUtil.isNull(t.getFftj())){
			params.add(t.getFftj());
			sql.append(" and fftj like '%'||?||'%'");
		}
		
		sql.append(" order by to_number(fftj) ");
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyfftjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	protected void setTableInfo() {

		super.setTableName("xg_rcsw_fyff_ffltj");
		super.setKey("fftjdm");

	}
	
	/**
	 * 
	 * @描述:查询发放途径是否已经存在
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午02:56:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t fyfftjForm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String fftjCkeckExist(FyfftjForm t){
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_rcsw_fyff_ffltj where fftj = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{t.getFftj()}, "num");
		
		return num;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:获取最大途径代码
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午03:01:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int getMaxTjdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(fftjdm)),1) ffxmdm from xg_rcsw_fyff_ffltj ";
		
		return dao.getOneRsint(sql);
		
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 途径是否存在发放结果
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午03:09:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String[] tjCheckExistForFfjg(String value) throws Exception{
		
		StringBuilder sql = new StringBuilder(" select distinct b.fftj from xg_rcsw_fyff_ffjgb a,xg_rcsw_fyff_ffltj b where a.fftjdm = b.fftjdm and a.fftjdm in (" +value +") ");
		String[] fftj = dao.getRs(sql.toString(), new String[]{}, "fftj");
		
		return fftj;
		
	}
	
	
	/**
	 * 
	 * @描述:获取发放途径list
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午02:48:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFyfftj() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select fftjdm,fftj,ffzh from xg_rcsw_fyff_ffltj ");
		sb.append(" order by to_number(fftjdm) ");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	

}
