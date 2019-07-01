/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����03:15:18 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����03:15:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SfbzDao extends SuperDAOImpl<SfbzForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SfbzForm t)
			throws Exception {
		String sql = "select a.* , b.jjxkmc , c.jjnjmc from XSGGFW_JJFW_SFBZB a left join " +
				"	XSGGFW_JJFW_JJXKDMB b on a.jjxkdm = b.jjxkdm left join " +
				"	XSGGFW_JJFW_JJNJDMB c on a.jjnjdm = c.jjnjdm order by a.jjxkdm";
		return super.getPageList(t, sql, null);
	}

	/**
	 * 
	 * @����:����������ȡ��¼
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����09:23:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xkdm
	 * @param njdm
	 * @return
	 * @throws Exception
	 * SfbzForm �������� 
	 * @throws
	 */
	public SfbzForm getModelByXkdmAndNjdm(String xkdm , String njdm) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_SFBZB a where a.jjxkdm = ? and a.jjnjdm = ?";
		return getModel(sql, new String[]{xkdm , njdm});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����04:46:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jjnjdm
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int deleteSfbzByJJnjdm(String jjnjdm) throws Exception{
		String sql = "delete from XSGGFW_JJFW_SFBZB a where a.jjnjdm = ? ";
		
		return dao.update(sql, new String[]{jjnjdm});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����04:46:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jjnjdm
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int deleteSfbzByJJxkdm(String jjxkdm) throws Exception{
		String sql = "delete from XSGGFW_JJFW_SFBZB a where a.jjxkdm = ? ";
		
		return dao.update(sql, new String[]{jjxkdm});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SfbzForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(SfbzForm.class);
		super.setKey("ID");
		super.setTableName("XSGGFW_JJFW_SFBZB");
	}

}
