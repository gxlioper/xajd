/**
 * @部门:学工产品事业部
 * @日期：2017年2月4日 下午2:31:33 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出管理模块
 * @类功能描述: 毕业生党支部代码维护Dao
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月4日 下午2:31:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysdzbwhDao extends SuperDAOImpl<BysdzbwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setTableName("xg_dtjs_zzgxzc_dzbdmb");
		this.setKey("dzbdm");
		this.setClass(BysdzbwhForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BysdzbwhForm t) throws Exception {
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select * from xg_dtjs_zzgxzc_dzbdmb where 1=1 ");
		
		if (!StringUtil.isNull(t.getDzbmc())){
			params.add(t.getDzbmc());
			sql.append(" and dzbmc like '%'||?||'%'");
		}
		
		sql.append(" order by to_number(dzbdm) ");
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BysdzbwhForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/** 
	 * @描述:新增时：判断党支部代码或名称是否已经存在
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:46:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bysdzbwhForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistForAdd(BysdzbwhForm bysdzbwhForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_dzbdmb where dzbdm = ? or dzbmc = ? ";
		String dzbdm = bysdzbwhForm.getDzbdm();
		String dzbmc = bysdzbwhForm.getDzbmc();
		
		String [] inputValue = {dzbdm,dzbmc};
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}
	
	/** 
	 * @描述:更新时：判断党支部代码或名称是否已经存在
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:46:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bysdzbwhForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistForUpdate(BysdzbwhForm bysdzbwhForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_dzbdmb where dzbmc = ? and dzbdm != ? ";
		String dzbdm = bysdzbwhForm.getDzbdm();
		String dzbmc = bysdzbwhForm.getDzbmc();
		
		String [] inputValue = {dzbmc,dzbdm};
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}

	/** 
	 * @描述:根据id数组返回已经被使用党支部代码或名称信息列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月16日 下午1:15:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getUsedList(String [] ids) {
		StringBuilder sql = new StringBuilder("SELECT DZBDM,DZBMC FROM ");
		sql.append(" ((SELECT szdzb FROM xg_dtjs_zzgxzc_zzgxzcsqb UNION SELECT szdzb FROM xg_dtjs_zzgxzc_zzgxzcjgb)) t1 ");
		sql.append(" LEFT JOIN XG_DTJS_ZZGXZC_DZBDMB t2 ON t1.SZDZB = t2.DZBDM WHERE dzbdm = ");
		for(int i=0;i<ids.length;i++){
			sql.append(" ? ");
			if(i!=ids.length-1){
				sql.append(" or dzbdm = ");
			}
		}
		return dao.getListNotOut(sql.toString(),ids);
	}

}
