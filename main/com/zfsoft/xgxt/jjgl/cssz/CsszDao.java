/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-21 ����04:25:14 
 */  
package com.zfsoft.xgxt.jjgl.cssz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ҽ�ģ��-��������-�������� DAO
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-21 ����04:25:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszDao extends SuperDAOImpl<CsszForm> {

	/**
	 * 
	 * @����: ��ȡ����������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-21 ����05:06:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String , String> getConfigMap(){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from XSGGFW_JJFW_XTSZ a  where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	
	
	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	*/
	
	@Override
	public int runDelete(String[] values) throws Exception {
		String sql = "delete from XSGGFW_JJFW_XTSZ";
		return dao.runDelete(sql, new String[]{});
	}


	/**
	 * ��ȡ�����ʸ��б�
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-22 ����01:50:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFbzgList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from XSGGFW_JJFW_FBZGDYB a");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CsszForm.class);
		super.setTableName("XSGGFW_JJFW_XTSZ");
	}

}
