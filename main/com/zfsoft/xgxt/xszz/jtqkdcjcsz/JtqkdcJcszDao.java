/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-25 ����08:48:38 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��--��ͥ������� ��������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-25 ����08:48:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtqkdcJcszDao extends SuperDAOImpl<JtqkdcJcszForm> {

	public List<HashMap<String, String>> getPageList(JtqkdcJcszForm t)
			throws Exception {
		return null;
	}

	public List<HashMap<String, String>> getPageList(JtqkdcJcszForm t, User user)
			throws Exception {
		return null;
	}


	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_jtqkjbszb");
	}
	
	
	/**
	 * 
	 * @����:��ѯ��ͥ������� ��������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-25 ����09:02:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * JtqkdcJcszForm �������� 
	 * @throws
	 */
	public JtqkdcJcszForm getModel() throws Exception{
		
		String sql = "select * from xg_xszz_new_jtqkjbszb where rownum=1";
		
		return super.getModel(new JtqkdcJcszForm(), sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-25 ����09:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJcsz() throws Exception{
		
		String sql = "delete from xg_xszz_new_jtqkjbszb";
		
		return dao.runUpdate(sql, new String[]{});
	}

}
