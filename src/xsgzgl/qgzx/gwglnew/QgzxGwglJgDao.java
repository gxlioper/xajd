package xsgzgl.qgzx.gwglnew;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class QgzxGwglJgDao extends SuperDAOImpl<QgzxGwglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(QgzxGwglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(QgzxGwglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(QgzxGwglForm.class);
		this.setTableName("xg_qgzx_gwxxb");
		this.setKey("gwdm");
	}

}
