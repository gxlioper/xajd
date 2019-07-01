package xsgzgl.szdw.general.dwwh;

import java.util.List;

import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 教职工信息
 */
public class DwwhJzgDAO extends SuperDAOImpl{
	
	/** 修改教职工信息
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
		// TODO 自动生成方法存根
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getPageList(Object t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

}
