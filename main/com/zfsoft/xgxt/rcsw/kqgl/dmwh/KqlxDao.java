/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����09:53:02 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: �������ʹ���ά��
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����09:53:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqlxDao extends SuperDAOImpl<KqlxForm> {

	/**
	 * ��ҳ��ѯ
	 */
	public List<HashMap<String, String>> getPageList(KqlxForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.kqlxdm,a.kqlxmc from xg_kqgl_kqlx a where 1=1 ");
		
			if (!StringUtil.isNull(t.getKqlxmc())){
				params.add(t.getKqlxmc());
				sql.append(" and kqlxmc like '%'||?||'%'");
			}
			
			sql.append(" order by to_number(kqlxdm) ");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqlxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	protected void setTableInfo() {
		super.setTableName("xg_kqgl_kqlx");
		super.setKey("kqlxdm");
	}
	
	
	/**
	 * 
	 * @����:�жϿ������������Ƿ��Ѿ�����
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����10:10:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String kqlxmcCheckExist(KqlxForm t) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_kqgl_kqlx where kqlxmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{t.getKqlxmc()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @����:��ȡ���Ŀ������ʹ���
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����10:11:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getMaxKqlxdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(kqlxdm)),0) kqlxdm from xg_kqgl_kqlx ";
		
		return dao.getOneRsint(sql);
	}
	
	
	/**
	 * 
	 * @����:��ѯ���ڵǼǱ����Ƿ����
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����10:14:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] kqlxdmCheckExistForKqdj(String value) throws Exception{
		
		StringBuilder sql = new StringBuilder(" select distinct b.kqlxmc from xg_kqgl_kqdj a,xg_kqgl_kqlx b where a.kqlxdm = b.kqlxdm and a.kqlxdm in (" +value +") ");
		String[] xmmc=dao.getRs(sql.toString(), new String[]{}, "kqlxmc");
			
		return xmmc;
	}
	
	
	/**
	 * 
	 * @����:��ȡ���������б�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����10:18:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlxList() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select kqlxdm,kqlxmc from xg_kqgl_kqlx ");
		sb.append(" order by to_number(kqlxdm) ");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
}
