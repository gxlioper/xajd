/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-20 ����09:29:57 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����ѧ������ά��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-20 ����09:29:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsDmDao extends SuperDAOImpl<TsxsDmForm> {

	/**
	 * ��ͨ��ѯ
	 */
	public List<HashMap<String, String>> getPageList(TsxsDmForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(" select lxdm,lxmc,lxsm,case lxsx when '1' then '����' when '2' then '��Χ' end lxsx from xg_pjpy_new_tslxdmb where 1=1  ");
		
		if(!StringUtil.isNull(t.getLxmc())){
			params.add(t.getLxmc());
			sql.append(" and lxmc like '%'||?||'%'");
		}
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * ��ȡ ��󵵴δ��룬��������
	 * @return
	 * @throws SQLException
	 */
	public int getMaxTsxsDm() throws SQLException{
		
		String sql = " select nvl(max(to_number(lxdm)),1) lxdm from xg_pjpy_new_tslxdmb ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @����:���Ӳ���Ψһ����֤
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����11:36:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(TsxsDmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_tslxdmb where lxmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getLxmc()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @����:�޸�Ψһ�Բ�����֤
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����11:38:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(TsxsDmForm model) {
		String sql=" select count(1) num from xg_pjpy_new_tslxdmb where lxmc = ? and lxdm<>?";
		String num=dao.getOneRs(sql.toString(), new String[]{model.getLxmc(),model.getLxdm()}, "num");
		return num;
		
	}
	

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsxsDmForm t, User user)
			throws Exception {
		//  �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_tslxdmb");
		super.setKey("lxdm");
	}
	
	
	/**
	 * 
	 * @����:����ѧ������list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����11:44:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxsDmList() {
		String sql = " select lxdm,lxmc from xg_pjpy_new_tslxdmb order by lxdm ";
		return dao.getList(sql, new String[]{},new String[]{"lxdm","lxmc"});
	}
	
	/**
	 * 
	 * @����:��������ѧ�������Ƿ�����ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����11:53:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkDcForTsxsb( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.lxmc from xg_pjpy_new_tsxsb a, xg_pjpy_new_tslxdmb b where a.lxdm=to_char(b.lxdm) and a.lxdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "lxmc");
		return dcmc;
	}

}
