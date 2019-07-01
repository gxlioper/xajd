/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����04:16:50 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xsgzgl.xsxx.general.jcsz.XsxxJcszForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-18 ����04:16:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszDao extends SuperDAOImpl<JcszForm> {

	private DAO dao = DAO.getInstance();
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	public JcszForm getModel(JcszForm t) throws Exception{
		String sql = "select * from xg_xsxx_xnxjkgb ";
		
		return super.getModel(t, sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @����: ��ȡ����״̬
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-18 ����05:16:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getOneKgzt(){
		String sql = "select a.kg , a.spl,a.xjxn from xg_xsxx_xnxjkgb a";
		return dao.getMapNotOut(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @����:��ѯʹ���е������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-18 ����05:15:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> splCx() throws Exception{
		String sql=" select * from (select spl,rownum r," +
				"(select count(*) ssjg from xg_xsxx_xnxjb  " +
				" where shjg in ('5')) shjg " +
				" from xg_xsxx_xnxjkgb) a where a.r=1 ";
		return dao.getMap(sql, new String[]{}, new String[]{"spl","shjg"});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���ÿ���״̬
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-18 ����05:03:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kgzt
	 * @param spl
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean setupKgsz(String kgzt , String spl,String xn) throws Exception{
		String del = "delete from xg_xsxx_xnxjkgb";
		
		String insert = "insert into xg_xsxx_xnxjkgb(kg , spl,xjxn) values (? , ? , ?)";
		
		return dao.runUpdate(del, new String[]{}) && dao.runUpdate(insert, new String[]{kgzt , spl,xn});
		
	}
	
	@Override
	protected void setTableInfo() {
	}

}
