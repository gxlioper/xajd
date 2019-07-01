/**
 * @部门:学工产品事业部
 * @日期：2017-3-9 上午09:43:34 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmxz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 代码维护_项目性质
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-3-9 上午09:43:34 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmxzDao extends SuperDAOImpl<XmxzForm>{
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XmxzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_xmxz");
		super.setKey("xzdm");
	}

	/**
	 * @描述: 普通查询
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-10 上午11:47:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmxzForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		/*不分页*/
		t.getPages().setPageSize(Integer.MAX_VALUE);
		StringBuilder sql = new StringBuilder(" select xzdm,xzmc from xg_zjdx_pjpy_xmxz where 1=1 ");
		
		if(!StringUtil.isNull(t.getXzmc())){
			params.add(t.getXzmc());
			sql.append(" and xzmc like '%'||?||'%'");
		}
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}

	/**
	 * 
	 * @描述: 新增_查询性质名称是否存在
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-3-10 上午11:47:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String isExistXzmc(XmxzForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_zjdx_pjpy_xmxz where xzmc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXzmc()}, "num");
		return num;
	}

	/**
	 * @描述: 查看项目性质在评奖结果中是否已被使用
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-3-10 上午09:53:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] xzCheckExistForPjjg( String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.xzmc ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a,xg_zjdx_pjpy_xmxz b ");
		sql.append(" where a.xzdm = to_char(b.xzdm) and a.xzdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
			sql.append(" ? ");
			params.add(tableArr[i]);
			if(i!= tableArr.length -1){
				sql.append(" , ");
			}
		}
		sql.append(" ) ");
		String[] xzmc = dao.getRs(sql.toString(), params.toArray(new String[]{}), "xzmc");
		return xzmc;
}

	/**
	 * @描述: 查看项目性质在评奖项目中是否已被使用
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-3-10 上午09:53:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] xzCheckExistForPjxm(String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.xzmc ");
		sql.append(" from xg_zjdx_pjpy_pjxmb a,xg_zjdx_pjpy_xmxz b ");
		sql.append(" where a.xzdm = to_char(b.xzdm) and a.xzdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
			sql.append(" ? ");
			params.add(tableArr[i]);
			if(i!= tableArr.length -1){
				sql.append(" , ");
			}
		}
		sql.append(" ) ");
		String[] xzmc = dao.getRs(sql.toString(), params.toArray(new String[]{}), "xzmc");
		return xzmc;
		}
	}
