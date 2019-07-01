/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JcszDao extends SuperDAOImpl<JcsszForm>{

	@Override
	public List<HashMap<String, String>> getPageList(JcsszForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JcsszForm t, User user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public JcsszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_zzyr_csszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_zzyr_csszb");
		super.setClass(JcsszForm.class);
	}

	public boolean deleteJcsz(JcsszForm myForm) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_zzyr_csszb ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}

}
