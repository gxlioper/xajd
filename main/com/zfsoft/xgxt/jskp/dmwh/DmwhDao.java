package com.zfsoft.xgxt.jskp.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class DmwhDao extends SuperDAOImpl<DmwhForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm dmwh)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from xg_jskp_xmlbb");
		sql.append(" where 1=1");
		if(StringUtils.isNotNull(dmwh.getXmlbmc())){
			sql.append(" and xmlbmc like ?");
			paraList.add("%"+dmwh.getXmlbmc()+"%");
		}
		return getPageList(dmwh, sql.toString(),paraList.toArray(new String[]{}));
	}

	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(DmwhForm.class);
		this.setKey("xmlbdm");
		this.setTableName("xg_jskp_xmlbb");
	}
	
	/**
	 * 
	 * @描述: 项目类别List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 下午03:46:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb where xmlbmc like '%能力素养%' order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 验证代码是否已被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-21 下午03:15:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsDmIsNotUserd(String[] xmlbdms){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from (");
		sql.append(" select xmlb from xg_jskp_xmsqb");
		sql.append(" union");
		sql.append(" select xmlb from xg_jskp_xmjgb)");
		sql.append(" where xmlb in(");
		for (int i = 0; i < xmlbdms.length; i++) {
			sql.append("?");
			if(i != xmlbdms.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(), xmlbdms, "cnt");
		return "0".equals(rs)?true:false;
	}
	
	/**
	 * 
	 * @描述: 验证项目类别名称是否被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-21 下午04:14:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlbmc
	 * @param xmlbdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkMcIsNotUserd(String xmlbmc,String xmlbdm){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_jskp_xmlbb where xmlbmc = ? ");
		paraList.add(xmlbmc);
		if(StringUtils.isNotNull(xmlbdm)){
			sql.append(" and xmlbdm != ?");
			paraList.add(xmlbdm);
		}
		String rs = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "cnt");
		return "0".equals(rs) ? true :false;
	}
	
	/**
	 * @描述: 项目类别数据-所有
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-29 下午04:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByAll(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 项目类别数据-能力素养
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-29 下午04:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByNlsy(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb where xmlbmc like '%能力素养%' order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 项目类别数据-思想政治素质
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-29 下午04:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListBySzsz(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb where xmlbmc not like '%能力素养%' order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
