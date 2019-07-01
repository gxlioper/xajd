/**
 * @部门:学工产品事业部
 * @日期：2016-12-28 上午11:53:54 
 */  
package com.zfsoft.xgxt.rcsw.zyfw.zyfwhz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿者服务模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cp[工号：1352]
 * @时间： 2016-12-28 上午11:53:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwDao extends SuperDAOImpl<ZyfwForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cp[工号：1352]
	 * @日期：2016-12-28 下午02:46:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXsxx(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xm,a.xb,a.xymc,a.zymc,a.bjmc ");
		sql.append(" from view_xsxxb a ");
		sql.append(" where a.xh=? ");
		return DAO.getInstance().getMap(sql.toString(), new String[]{xh}, new String[]{"xm","xb","xymc","zymc","bjmc"});
	}

	public List<HashMap<String, String>> getZyfwList(String xh, ZyfwForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select* from (select a.f_xm xm,a.f_xh xh,a.f_xb xb,a.f_yx xymc,a.f_bj bjmc,to_char(a.f_rq,'yyyy-MM-dd') rq,to_char(a.f_zyzfwnr) zyfwnr ,to_char(a.f_gzdd) gzdd,a.f_gzsc gzsc,a.f_xzhdzgbmmc bmmc,a.f_xzshrid bmshr "); 
		sql.append(" from zfsoft_bpmx.w_xszyfwqkdjb a left join ");
		sql.append(" zfsoft_bpmx.bpm_pro_run_his b on a.id=b.businesskey where b.status='2' and a.f_xh =?) t where 1=1 ");
		if (StringUtils.isNotNull(model.getQsrq())) {
			sql.append(" and rq>='"+model.getQsrq()+"'");
		}
		if (StringUtils.isNotNull(model.getJsrq())) {
			sql.append(" and rq<='"+model.getJsrq()+"'");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	/**
	 * @param model  
	 * @描述:TODO总工作时长
	 * @作者：cp[工号：1352]
	 * @日期：2016-12-28 下午05:44:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws 
	 */
	public HashMap<String, String> getZsc(String xh, ZyfwForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select mod(sum(fz),60) fz,case when sum(fz)>60 then sum(xs)+floor(sum(fz)/60)" +
				" else sum(xs)  end xs from(select to_number(substr(a.f_gzsc,0,instr(a.f_gzsc,'小')-1 )) xs ," +
				" to_number(substr(a.f_gzsc,instr(a.f_gzsc,'时')+1,instr(a.f_gzsc,'分')-5)) fz from zfsoft_bpmx.w_xszyfwqkdjb " +
				" a left join zfsoft_bpmx.bpm_pro_run_his b on a.id=b.businesskey where b.status ='2'  and  a.f_xh=? ");
		if (StringUtils.isNotNull(model.getQsrq())) {
			sql.append(" and to_char(a.f_rq,'yyyy-MM-dd')>='"+model.getQsrq()+"'");
		}
		if (StringUtils.isNotNull(model.getJsrq())) {
			sql.append(" and to_char(a.f_rq,'yyyy-MM-dd')<='"+model.getJsrq()+"'");
		}
		sql.append(") ");
		return DAO.getInstance().getMap(sql.toString(), new String[]{xh}, new String[]{"fz","xs"});
	}

}
