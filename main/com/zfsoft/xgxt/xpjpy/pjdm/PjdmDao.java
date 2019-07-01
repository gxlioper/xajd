/**
 * @部门:学工产品事业部
 * @日期：2013-8-16 上午09:06:55 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优_代码维护（项目类型和性质）
 * @作者：CQ [工号：785]
 * @时间： 2013-8-16 上午09:06:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjdmDao extends SuperDAOImpl<PjdmModel>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjdmModel t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select * from ");
		String xmxz = t.getXmxz();
		if("2".equals(xmxz))
		{
			sql.append("  xg_pjpy_new_xmlx where 1=1 ");
		}
		else{
			sql.append("  xg_pjpy_new_jxjxmlx where 1=1 ");
		}
			if (!StringUtil.isNull(t.getXmlxmc())){
				params.add(t.getXmlxmc());
				sql.append(" and xmlxmc like '%'||?||'%'");
			}
			
			sql.append(" order by to_number(xmlxdm) ");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjdmModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_xmlx");
		super.setKey("xmlxdm");
	}

	/** 
	 * @描述:新增_查询类型代码是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-16 上午11:43:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String lxCheckExistForSave(PjdmModel model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmlx where xmlxmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXmlxmc()}, "num");
		
		return num;
	}
	
	/**
	 * 
	 * @描述:修改_查询类型代码是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 上午11:11:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String lxCheckExistForUpdate(PjdmModel model){
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmlx where xmlxmc= ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXmlxmc()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @描述:新增_查询性质代码是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 上午11:18:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String xzCheckExistForSave(PjdmModel model){
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXmxzdm()}, "num");
		
		return num ;
	}
	
	/**
	 * 
	 * @描述:修改_查询性质代码是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 上午11:24:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String xzCheckExistForUpdate(PjdmModel model){
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzmc= ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXmxzmc()}, "num");
		
		return num ;
	}

	/**
	 * @throws SQLException  
	 * @描述:获取最大项目类型代码
	 * @作者：cq [工号：785]
	 * @日期：2013-8-17 下午02:59:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int getMaxXmlxdm() throws SQLException {
		
		String sql = "select nvl(max(to_number(xmlxdm)),1) xmlxdm from xg_pjpy_new_xmlx";
		
		return dao.getOneRsint(sql);
	}

	/**
	 * 
	 * @描述：查看项目类型在评奖结果中是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 下午04:19:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	
	public String[] lxCheckExistForPjjg( String value) throws Exception{
			StringBuilder sql = new StringBuilder(" select distinct b.xmlxmc from xg_pjpy_new_pjjgb a,xg_pjpy_new_xmlx b where a.lxdm = to_char(b.xmlxdm) and a.lxdm in (" +value +") ");
			String[] xmlxmc=dao.getRs(sql.toString(), new String[]{}, "xmlxmc");
		return xmlxmc;
	}
	
	/**
	 * 
	 * @描述: 查看项目性质在评奖结果中是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 上午08:41:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String[] xzCheckExistForPjjg(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjpy_new_pjjgb a,xg_pjpy_new_xmxz b where a.xzdm = to_char(b.xmxzdm) and a.xzdm in (" +value +") ");
		String[] xmxzmc = dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		
		return xmxzmc;
	}

	/**
	 * 
	 * @描述:查看项目类型在评奖项目中是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 下午05:02:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] lxCheckExistForPjxm(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmlxmc from xg_pjpy_new_pjxmb a,xg_pjpy_new_xmlx b where a.lxdm = to_char(b.xmlxdm) and a.lxdm in (" +value+") ");
		String[] xmlxmc = dao.getRs(sql.toString(), new String[]{}, "xmlxmc");
		
		return xmlxmc;
	}
	
	/**
	 * 
	 * @描述:查看项目性质在评奖项目中是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 下午05:06:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] xzCheckExistForPjxm(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjy_new_pjxmb a,xg_pjpy_new_xmxz b where a.xzdm = to_char(b.xmxzmc) and a.xzdm in (" +value+") ");
		String[] xmxzmc = dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		
		return xmxzmc;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:根据类型代码获得名称
	 * @作者：cq [工号：785]
	 * @日期：2015-5-6 上午09:03:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getXmlxmc(String[] xmlxdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();	
		
		sql.append("select xmlxmc from xg_pjpy_new_xmlx where xmlxdm in (");
		
		for (int i = 0; i < xmlxdm.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmlxdm[i]);
		}
		sql.append(")");
		
		return dao.getList(sql.toString(), params.toArray(new String[]{}), "xmlxmc");
		
	}

}
