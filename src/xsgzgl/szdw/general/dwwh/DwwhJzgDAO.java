package xsgzgl.szdw.general.dwwh;

import java.util.List;

import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * ��ְ����Ϣ
 */
public class DwwhJzgDAO extends SuperDAOImpl{
	
	/** �޸Ľ�ְ����Ϣ
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean updateJzgxx(DwwhJzgModle model) throws Exception{
		super.setTableName("fdyxxb");
		super.setKey("zgh");
		return runUpdate(model, model.getZgh());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getPageList(Object t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}

}
