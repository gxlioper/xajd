/**
 * @部门:学工产品事业部
 * @日期：2016-7-20 下午04:41:26 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 代码维护-奖项名次
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-20 下午04:41:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhJxmcDao extends SuperDAOImpl<DmwhJxmcForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxmcForm t)
			throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select t.* from (select a.*,b.jxlbmc,(case a.jsfs when '1' then '个人' when '2' then '团体' else a.jsfs end) jsfsmc,c.jxdjmc from xg_hjxxgl_jxmc a left join xg_hjxxgl_jxlb b on a.jxlbdm = b.jxlbdm left join xg_hjxxgl_jxdj c on a.jxdjdm = c.jxdjdm) t where 1 = 1 ");
		
		if (!StringUtil.isNull(t.getJxmcmc())){
			params.add(t.getJxmcmc());
			sql.append(" and jxmcmc like '%'||?||'%'");
		}
			
		sql.append(" order by to_number(jxmcdm) ");
			
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxmcForm t, User user)
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
		super.setTableName("xg_hjxxgl_jxmc");
		super.setKey("jxmcdm");
	}
	
	
	/**
	 * 
	 * @描述: id递增
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午01:50:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getNextId(){
		String jxmcdm=dao.getOneRs("select max(jxmcdm) jxmcdm from xg_hjxxgl_jxmc ",new String[]{},"jxmcdm");
		//如果不存在数据，默认为00
		if(StringUtils.isNull(jxmcdm)){jxmcdm="00";}
		String newId=String.valueOf(Integer.parseInt(jxmcdm)+1);
		if(newId.length()==1){
			newId="0"+newId;
		}
		return newId;
	}
	
	/**
	 * 
	 * @描述: 增加重复验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午03:01:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(DmwhJxmcForm model) {		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxmc where jxmcmc = ? and jxdjdm = ? and jsfs = ? and jxlbdm = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxmcmc().trim(), model.getJxdjdm(), model.getJsfs(), model.getJxlbdm()}, "num");
		
		return num;	
	}
	
	/**
	 * 
	 * @描述: 修改重复验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午03:01:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(DmwhJxmcForm model) {		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxmc where jxmcmc = ? and jxdjdm = ? and jsfs = ? and jxlbdm = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxmcmc().trim(), model.getJxdjdm(), model.getJsfs(), model.getJxlbdm()}, "num");
		
		return num;	
	}
	
	/**
	 * 
	 * @描述: 奖项等级LIST
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-21 上午10:14:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxlbdm
	 * @param jsfs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxdjList(String jxlbdm, String jsfs) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.jxdjdm,t.jxdjmc from xg_hjxxgl_jxdj t ");
		
		sql.append(" where t.jxlbdm = '"+jxlbdm+"' and t.jsfs = '"+jsfs+"'"); 
		
		sql.append(" order by t.jxdjdm ");
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"jxdjdm","jxdjmc"});
	}
	
	
	/**
	 * 
	 * @描述: 申请判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 下午05:26:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkSq(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxmcmc from xg_hjxxgl_sqb a,xg_hjxxgl_jxmc b where a.jxmcdm = to_char(b.jxmcdm) and a.jxmcdm in (" +value+") ");
		String[] jxmcmc = dao.getRs(sql.toString(), new String[]{}, "jxmcmc");
		
		return jxmcmc;
	}
	
	/**
	 * 
	 * @描述: 结果判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 下午05:27:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkJg(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxmcmc from xg_hjxxgl_jgb a,xg_hjxxgl_jxmc b where a.jxmcdm = to_char(b.jxmcdm) and a.jxmcdm in (" +value+") ");
		String[] jxmcmc = dao.getRs(sql.toString(), new String[]{}, "jxmcmc");
		
		return jxmcmc;
	}
	
}
