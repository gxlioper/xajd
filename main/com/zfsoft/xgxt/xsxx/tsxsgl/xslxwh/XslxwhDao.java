/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:48:39 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:48:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XslxwhDao extends SuperDAOImpl<XslxwhForm>{

	@Override
	public List<HashMap<String, String>> getPageList(XslxwhForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxx_xslxb a where 1=1");
		if (!StringUtil.isNull(model.getXslxmc())) {
			params.add(model.getXslxmc());
			sql.append(" and a.xslxmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * 获取学生类别列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XslxwhForm model, User user) throws Exception {
		return null;
	}

    /**
     * 
     * @描述:增加学生类别
     * @作者：夏夏[工号：1104]
     * @日期：2015-5-15 下午01:17:57
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param model
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
	public boolean addXslx(XslxwhForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_xsxx_xslxb where xslxdm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getXslxdm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_xsxx_xslxb(XSLXDM,XSLXMC) values(?,?)";
			flag = dao.runUpdate(sql, new String[] { model.getXslxdm(), model.getXslxmc() });
		} else {
			throw new SystemException(MessageKey.SYS_SAVE_DM_REPEAT);
		}

		return flag;

	}

	/**
	 *删除
	 */
	public int deleteXslx(String values) throws Exception {
		String sql = "delete from xg_xsxx_xslxb where xslxdm =?";
		return dao.runDelete(sql, new String[] {values});

	}
	

	/**
	 *获取学生类别
	 */
	public String getXslxmc(String lbdm) throws Exception {
		String sql = "select mc Xslxmc from xg_xsxx_xslxb where xslxdm =?";
		return dao.getOneRs(sql, new String[]{lbdm}, "Xslxmc");

	}
	
	public List<String> getTsxslxData() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select  distinct xslxdm from (select substr(t.xslxdm,instr(t.xslxdm, ',', 1, c.lv) + 1,");
	    sql.append("instr(t.xslxdm, ',', 1, c.lv + 1) -(instr(t.xslxdm, ',', 1, c.lv) + 1)) AS xslxdm from (select ");
	    sql.append("',' || xslxdm || ',' AS xslxdm,length(xslxdm || ',') - nvl(length(REPLACE(xslxdm, ',')), 0) AS cnt");
	    sql.append(" FROM (select xslxdm from xg_xsxx_tsxsb)) t,(select LEVEL lv from dual CONNECT BY LEVEL <= 4000) c");
	    sql.append(" where c.lv <= t.cnt) order by  xslxdm");
		return dao.getList(sql.toString(), new String[]{}, "xslxdm");
		
	}

	public List<HashMap<String, String>> getXslxList(){
		
		String sql = "select xslxdm,xslxmc from xg_xsxx_xslxb";
		
		return dao.getListNotOut(sql, new String[]{});
		
	}
	public List<HashMap<String, String>> getXslxList(String[] xslxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xslxdm,xslxmc from xg_xsxx_xslxb where ");
		
		for (int i = 0 , n = xslxdm.length ; i < n ; i++){
			sql.append("  xslxdm =? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), xslxdm);
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(XslxwhForm.class);
		super.setKey("xslxdm");
		super.setTableName("xg_xsxx_xslxb");
	}

	

}
