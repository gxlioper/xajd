/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-16 ����09:06:55 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������_����ά������Ŀ���ͺ����ʣ�
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-16 ����09:06:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjdmDao extends SuperDAOImpl<PjdmModel>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjdmModel t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select * from ");
		String xmxz = t.getXmxz();
		if("2".equals(xmxz))
		{
			sql.append("  xg_pjpy_new_xmlx where 1=1 ");
		}
		else{
			sql.append("  xg_pjpy_new_jxjxmlx where 1=1 ");
		}
			if (!StringUtil.isNull(t.getXmlxmc())){
				params.add(t.getXmlxmc());
				sql.append(" and xmlxmc like '%'||?||'%'");
			}
			
			sql.append(" order by to_number(xmlxdm) ");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjdmModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_xmlx");
		super.setKey("xmlxdm");
	}

	/** 
	 * @����:����_��ѯ���ʹ����Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-16 ����11:43:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String lxCheckExistForSave(PjdmModel model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmlx where xmlxmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXmlxmc()}, "num");
		
		return num;
	}
	
	/**
	 * 
	 * @����:�޸�_��ѯ���ʹ����Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����11:11:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String lxCheckExistForUpdate(PjdmModel model){
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmlx where xmlxmc= ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXmlxmc()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @����:����_��ѯ���ʴ����Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����11:18:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xzCheckExistForSave(PjdmModel model){
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXmxzdm()}, "num");
		
		return num ;
	}
	
	/**
	 * 
	 * @����:�޸�_��ѯ���ʴ����Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����11:24:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xzCheckExistForUpdate(PjdmModel model){
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzmc= ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXmxzmc()}, "num");
		
		return num ;
	}

	/**
	 * @throws SQLException  
	 * @����:��ȡ�����Ŀ���ʹ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-17 ����02:59:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int getMaxXmlxdm() throws SQLException {
		
		String sql = "select nvl(max(to_number(xmlxdm)),1) xmlxdm from xg_pjpy_new_xmlx";
		
		return dao.getOneRsint(sql);
	}

	/**
	 * 
	 * @�������鿴��Ŀ����������������Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����04:19:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	
	public String[] lxCheckExistForPjjg( String value) throws Exception{
			StringBuilder sql = new StringBuilder(" select distinct b.xmlxmc from xg_pjpy_new_pjjgb a,xg_pjpy_new_xmlx b where a.lxdm = to_char(b.xmlxdm) and a.lxdm in (" +value +") ");
			String[] xmlxmc=dao.getRs(sql.toString(), new String[]{}, "xmlxmc");
		return xmlxmc;
	}
	
	/**
	 * 
	 * @����: �鿴��Ŀ����������������Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����08:41:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String[] xzCheckExistForPjjg(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjpy_new_pjjgb a,xg_pjpy_new_xmxz b where a.xzdm = to_char(b.xmxzdm) and a.xzdm in (" +value +") ");
		String[] xmxzmc = dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		
		return xmxzmc;
	}

	/**
	 * 
	 * @����:�鿴��Ŀ������������Ŀ���Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����05:02:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] lxCheckExistForPjxm(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmlxmc from xg_pjpy_new_pjxmb a,xg_pjpy_new_xmlx b where a.lxdm = to_char(b.xmlxdm) and a.lxdm in (" +value+") ");
		String[] xmlxmc = dao.getRs(sql.toString(), new String[]{}, "xmlxmc");
		
		return xmlxmc;
	}
	
	/**
	 * 
	 * @����:�鿴��Ŀ������������Ŀ���Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����05:06:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] xzCheckExistForPjxm(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjy_new_pjxmb a,xg_pjpy_new_xmxz b where a.xzdm = to_char(b.xmxzmc) and a.xzdm in (" +value+") ");
		String[] xmxzmc = dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		
		return xmxzmc;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:�������ʹ���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-6 ����09:03:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getXmlxmc(String[] xmlxdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();	
		
		sql.append("select xmlxmc from xg_pjpy_new_xmlx where xmlxdm in (");
		
		for (int i = 0; i < xmlxdm.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmlxdm[i]);
		}
		sql.append(")");
		
		return dao.getList(sql.toString(), params.toArray(new String[]{}), "xmlxmc");
		
	}

}
