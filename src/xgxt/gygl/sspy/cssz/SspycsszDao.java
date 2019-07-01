/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class SspycsszDao extends SuperDAOImpl<SspycsszForm>{
	@Override
	public List<HashMap<String, String>> getPageList(SspycsszForm t)
			throws Exception {
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(SspycsszForm t, User user)
			throws Exception {
		return null;
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_pyxmcsszb");
		super.setClass(SspycsszForm.class);
	}
	//取到参数设置表信息
	public SspycsszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_gygl_pyxmcsszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	public boolean deleteCssz(SspycsszForm myForm) throws Exception {
		String sql = " delete from xg_gygl_pyxmcsszb ";
		return dao.runUpdate(sql, new String[]{});
	}

}
