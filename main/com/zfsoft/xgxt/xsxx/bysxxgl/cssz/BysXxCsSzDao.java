/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����07:36:23 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import java.util.HashMap;

import xgxt.DAO.DAO;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����07:36:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysXxCsSzDao extends DAO{
	
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����08:04:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCssz()throws Exception{
		String sql = "select a.bynd,a.kgzt,a.shlid from XG_BYSXX_XXXGCSSZB a";
		String[] inputValue = new String[] { };
		return getMapNotOut(sql, inputValue);
	}
	
	public boolean csSzAdd(BysXxCsSzForm model) throws Exception {
		String sql = " insert into XG_BYSXX_XXXGCSSZB(bynd,kgzt,shlid) values(?,?,?) ";
		return runUpdate(sql, new String[] { model.getBynd(),model.getKgzt(), model.getShlid()});
	}
	
	public boolean csSzDel(BysXxCsSzForm model) throws Exception {
		String sql = " delete from XG_BYSXX_XXXGCSSZB ";
		return runUpdate(sql, new String[] {});
	}
	
	/**
	 * ��ѯʹ���е������
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splCx() throws Exception{
		String sql=" select * from (select shlid,rownum r," +
				"(select count(*) ssjg from xg_bysxx_xxxgsqb  " +
				" where shjg <> '1') shjg " +
				" from XG_BYSXX_XXXGCSSZB) a where a.r=1 ";
		return getMap(sql, new String[]{}, new String[]{"shlid","shjg"});
	}


}
