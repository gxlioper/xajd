/**
 * @部门:学工产品事业部
 * @日期：2016-7-6 上午11:05:50 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxlb;

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
 * @类功能描述: 代码维护-奖项类别 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-6 上午11:05:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhJxlbDao extends SuperDAOImpl<DmwhJxlbForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxlbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select * from xg_hjxxgl_jxlb where 1=1 ");
		
		if (!StringUtil.isNull(t.getJxlbmc())){
			params.add(t.getJxlbmc());
			sql.append(" and jxlbmc like '%'||?||'%'");
		}
			
		sql.append(" order by to_number(jxlbdm) ");
			
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxlbForm t, User user)
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
		super.setTableName("xg_hjxxgl_jxlb");
		super.setKey("jxlbdm");
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
		String jxlbdm=dao.getOneRs("select max(jxlbdm) jxlbdm from xg_hjxxgl_jxlb ",new String[]{},"jxlbdm");
		//如果不存在数据，默认为00
		if(StringUtils.isNull(jxlbdm)){jxlbdm="00";}
		String newId=String.valueOf(Integer.parseInt(jxlbdm)+1);
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
	public String checkExistForSave(DmwhJxlbForm model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxlb where jxlbmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxlbmc().trim()}, "num");
		
		return num;		
	}
	
	/**
	 * 
	 * @描述: 修改重复验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午03:06:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(DmwhJxlbForm model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxlb where jxlbmc= ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getJxlbmc().trim()}, "num");
		
		return num;
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
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_sqb a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
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
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_jgb a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
	}
	
	/**
	 * 
	 * @描述:奖项等级判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-19 上午09:05:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkJxdj(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_jxdj a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
	}
	
	/**
	 * 
	 * @描述:奖项名次判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-19 上午09:05:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkJxmc(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_jxmc a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
	}
	
}
