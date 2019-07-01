/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����10:57:51 
 */
package xsgzgl.rcsw.wsbz.yyrq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2016-3-8 ����10:57:51
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YyrqDao extends SuperDAOImpl<YyrqForm> {
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YyrqForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from yyrqb");
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YyrqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("yyrqb");
		super.setKey("id");
	}

	/**
	 * @����: ����
	 */
	public boolean addLxInfo(YyrqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into yyrqb(yyrq) values(?)");
		return dao.runUpdate(sql.toString(), new String[] { model.getYyrq() });
	}

	/**
	 * @����:�޸�
	 */
	public boolean updateLxInfo(YyrqForm model) throws Exception {

			StringBuilder sql = new StringBuilder();
			sql.append("update yyrqb set yyrq=? where id=?");
			return dao.runUpdate(sql.toString(), new String[] { model.getYyrq(),model.getId() });
		
	}

	/** 
	 * @����:��֤Ψһ��
	 */
	public boolean isExist(YyrqForm model) {
		String sql = "select count(1) num from yyrqb where yyrq=?  " ;
		String num = dao.getOneRs(sql, new String[]{ model.getYyrq()}, "num");
		return Integer.valueOf(num)>0;
	}
	public boolean isxgExist(YyrqForm model) {
		String sql = "select count(1) num from yyrqb where yyrq=? and id<>?" ;
		String num = dao.getOneRs(sql, new String[]{ model.getYyrq(),model.getId() }, "num");
		return Integer.valueOf(num)>0;
	}

}