/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyjcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz.HcyhkJcszForm;

public class JcszDao extends SuperDAOImpl<JcszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_gfjy_gfjyjcszb");
		super.setClass(JcszForm.class);
	}

	public JcszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_gfjy_gfjyjcszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}

	public boolean deleteJcsz(JcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_gfjy_gfjyjcszb ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}

}
