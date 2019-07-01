/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.action.zjly;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XnxqwhDao extends SuperDAOImpl<XnxqwhForm>{

	@Override
	public List<HashMap<String, String>> getPageList(XnxqwhForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XnxqwhForm t, User user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_xtwh_bsdtxnxqwhb");
		super.setClass(XnxqwhForm.class);
	}

	public XnxqwhForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xtwh_bsdtxnxqwhb");
		return super.getModel(sql.toString(), new String[]{});
	}

	public boolean delXnxq() throws Exception {
		String sql = " delete from xg_xtwh_bsdtxnxqwhb ";
		return dao.runUpdate(sql, new String[]{});
	}

}
