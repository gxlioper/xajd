/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-21 ����10:46:53 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-21 ����10:46:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxzdmDao extends SuperDAOImpl<PjxzdmForm>{

	/**
	 * ��ͨ��ѯ
	 */
	public List<HashMap<String, String>> getPageList(PjxzdmForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(" select xmxzdm,xmxzmc from xg_pjpy_new_xmxz where 1=1 ");
		
		if(!StringUtil.isNull(t.getXmxzmc())){
			params.add(t.getXmxzmc());
			sql.append(" and xmxzmc like '%'||?||'%'");
		}
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ ������ʴ��룬��������
	 * @return
	 * @throws SQLException
	 */
	public int getMaxXmxzdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(xmxzdm)),1) xmxzdm from xg_pjpy_new_xmxz ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @����:���Ӳ���Ψһ����֤
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����10:58:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(PjxzdmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXmxzmc()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @����:�޸�Ψһ��������֤
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����11:02:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(PjxzdmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXmxzmc()}, "num");
		return num;
		
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjxzdmForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_xmxz");
		super.setKey("xmxzdm");
	}
	
	/**
	 * 
	 * @����:��Ŀ���ʴ���List
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����11:11:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxzdmList() {
		String sql = " select xmxzdm,xmxzmc from xg_pjpy_new_xmxz order by xmxzdm ";
		return dao.getList(sql, new String[]{},new String[]{"xmxzdm","xmxzmc"});
	}
	
	
	/**
	 * 
	 * @����:��ѯ������Ŀ���Ƿ�����ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����11:18:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkXzForPjxm( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjpy_new_pjxmb a, xg_pjpy_new_xmxz b where a.xzdm=to_char(b.xmxzdm) and a.xzdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		return dcmc;
	}
	
	/**
	 * 
	 * @����:��ѯ������������Ƿ�����ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����11:19:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkXzForPjjg( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjpy_new_pjjgb a, xg_pjpy_new_xmxz b where a.xzdm=to_char(b.xmxzdm) and a.xzdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		return dcmc;
	}
}
