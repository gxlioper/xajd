/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-2 ����02:33:17 
 */
package com.zfsoft.xgxt.axcs.axlb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-2 ����02:33:17
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class AxlbglDao extends SuperDAOImpl<AxlbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(AxlbglForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xszz_axcslbb a where 1=1");
		if (!StringUtil.isNull(model.getMc())) {
			params.add(model.getMc());
			sql.append(" and a.mc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * ��ȡ��������б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(AxlbglForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:���Ӱ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����03:52:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws
	 */
	public boolean addAxlb(AxlbglForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_xszz_axcslbb where dm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getDm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_xszz_axcslbb(DM,MC,SM) values(?,?,?)";
			flag = dao.runUpdate(sql, new String[] { model.getDm(), model.getMc(), model.getSm() });
		} else {
			throw new SystemException(MessageKey.AXCS_AXLB_AXLBYCZ);
		}

		return flag;

	}

	/**
	 *ɾ��
	 */
	public int deleteAxlb(String values) throws Exception {
		String sql = "delete from xg_xszz_axcslbb where dm =?";
		return dao.runDelete(sql, new String[] {values});

	}
	

	/**
	 *��ȡ�������
	 */
	public String getAxlbmc(String lbdm) throws Exception {
		String sql = "select mc axlbmc from xg_xszz_axcslbb where dm =?";
		return dao.getOneRs(sql, new String[]{lbdm}, "axlbmc");

	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		String sql ="select count(*) num from xg_xszz_axcsxmb where xmlb=? ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{lbdm}, "num"))>0;
		
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_axcslbb");
		super.setKey("dm");

	}

}
