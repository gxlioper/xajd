/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:12:16 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-Ժ�轱ѧ���㽭��ѧ���Ի�����
 * @�๦������: TODO(�ʽ���Դ����) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:12:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhDao  extends SuperDAOImpl<DmwhForm>{
	
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.zjlydm, a.zjlymc from xg_zxdk_new_ysjxjdmb a where 1=1 ");
		if (!StringUtil.isNull(t.getZjlymc())){
			params.add(t.getZjlymc());
			sql.append(" and zjlymc like '%'||?||'%'");
		}
		sql.append(" order by to_number(zjlydm) ");
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}
	
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	protected void setTableInfo() {
		super.setTableName("xg_zxdk_new_ysjxjdmb");
		super.setKey("zjlydm");
	}
	
	/**
	 * @����:TODO(��ѯ�ʽ���Դ�����Ƿ��Ѿ�����)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����02:45:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String fftjCkeckExist(DmwhForm t){
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_zxdk_new_ysjxjdmb where zjlymc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{t.getZjlymc()}, "num");
		return num;
	}
	/**
	 * @����:TODO(��ȡ����ʽ���Դ����)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����02:48:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int maxZjlydm() throws SQLException{
		String sql = " select nvl(max(to_number(zjlydm)),1) zjlydm from xg_zxdk_new_ysjxjdmb ";
		return dao.getOneRsint(sql);
	}
	
	/**
	 * @����:TODO(�ж��ʽ���Դ������Ժ�轱ѧ�����Ƿ�ʹ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����03:55:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] tjcheckZjlymcForYsjxjjg(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.zjlymc from xg_zxdk_new_ysjxj a,xg_zxdk_new_ysjxjdmb b where a.zjly = b.zjlydm and a.zjly in (" +value +") ");
		String[] zjlymc = dao.getRs(sql.toString(), new String[]{}, "zjlymc");
		return zjlymc;
	}

}
