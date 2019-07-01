/**
 * @部门:学工产品事业部
 * @日期：2017-3-9 上午09:40:47 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 代码维护_项目类型
 * @作者：Meng.Wei[工号:1186]
 * @时间： 2017-3-9 上午09:39:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmlxDao extends SuperDAOImpl<XmlxForm>{
	
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XmlxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_xmlx");
		super.setKey("lxdm");
	}

	/**
	 * 查询
	 */
	public List<HashMap<String, String>> getPageList(XmlxForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		/*不分页*/
		t.getPages().setPageSize(Integer.MAX_VALUE);
		StringBuilder sql = new StringBuilder(" select lxdm,lxmc from xg_zjdx_pjpy_xmlx where 1 = 1 ");
		
		if(!StringUtil.isNull(t.getLxmc())){
			params.add(t.getLxmc());
			sql.append(" and lxmc like '%'||?||'%'");
		}
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}
	
	/**
	 * @描述: 新增_查询类型名称是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午07:49:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String isExistLxmc(XmlxForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_zjdx_pjpy_xmlx where lxmc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getLxmc()}, "num");
		return num;
	}
	
	/**
	 * @描述: 查看项目类型在评奖结果中是否已被使用
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-10 上午09:53:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] lxCheckExistForPjjg( String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.lxmc ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a,xg_zjdx_pjpy_xmlx b ");
		sql.append(" where a.lxdm = to_char(b.lxdm) and a.lxdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
				sql.append(" ? ");
				params.add(tableArr[i]);
				if(i!= tableArr.length -1){
					sql.append(" , ");
				}
		}
		sql.append(" ) ");
		String[] lxmc = dao.getRs(sql.toString(), params.toArray(new String[]{}), "lxmc");
		return lxmc;
	}
	
	/**
	 * @描述: 查看项目类型在评奖项目中是否已被使用
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-10 上午09:53:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] lxCheckExistForPjxm(String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.lxmc ");
		sql.append(" from xg_zjdx_pjpy_pjxmb a,xg_zjdx_pjpy_xmlx b ");
		sql.append(" where a.lxdm = to_char(b.lxdm) and a.lxdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
			sql.append(" ? ");
			params.add(tableArr[i]);
			if(i!= tableArr.length -1){
				sql.append(" , ");
			}
		}
		sql.append(" ) ");
		String[] lxmc = dao.getRs(sql.toString(),params.toArray(new String[]{}), "lxmc");
		return lxmc;
	}
	
	/**
	 * @描述: 获取项目类型
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-10 上午11:32:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmlx() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select lxdm dm,lxmc mc");
		sql.append(" from xg_zjdx_pjpy_xmlx order by lxdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
