/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CsszDao extends SuperDAOImpl<CsszForm>{

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public CsszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcpy_csszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcpy_csszb");
		super.setClass(CsszForm.class);
	}

	public boolean delCssz() throws Exception {
		String sql = " delete from xg_rcpy_csszb ";
		return dao.runUpdate(sql, new String[]{});
	}

}
