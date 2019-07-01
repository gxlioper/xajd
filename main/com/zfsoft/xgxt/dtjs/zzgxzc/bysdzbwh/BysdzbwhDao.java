/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��4�� ����2:31:33 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת������ģ��
 * @�๦������: ��ҵ����֧������ά��Dao
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��4�� ����2:31:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysdzbwhDao extends SuperDAOImpl<BysdzbwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setTableName("xg_dtjs_zzgxzc_dzbdmb");
		this.setKey("dzbdm");
		this.setClass(BysdzbwhForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BysdzbwhForm t) throws Exception {
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select * from xg_dtjs_zzgxzc_dzbdmb where 1=1 ");
		
		if (!StringUtil.isNull(t.getDzbmc())){
			params.add(t.getDzbmc());
			sql.append(" and dzbmc like '%'||?||'%'");
		}
		
		sql.append(" order by to_number(dzbdm) ");
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BysdzbwhForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/** 
	 * @����:����ʱ���жϵ�֧������������Ƿ��Ѿ�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:46:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bysdzbwhForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistForAdd(BysdzbwhForm bysdzbwhForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_dzbdmb where dzbdm = ? or dzbmc = ? ";
		String dzbdm = bysdzbwhForm.getDzbdm();
		String dzbmc = bysdzbwhForm.getDzbmc();
		
		String [] inputValue = {dzbdm,dzbmc};
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}
	
	/** 
	 * @����:����ʱ���жϵ�֧������������Ƿ��Ѿ�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:46:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bysdzbwhForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistForUpdate(BysdzbwhForm bysdzbwhForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_dzbdmb where dzbmc = ? and dzbdm != ? ";
		String dzbdm = bysdzbwhForm.getDzbdm();
		String dzbmc = bysdzbwhForm.getDzbmc();
		
		String [] inputValue = {dzbmc,dzbdm};
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}

	/** 
	 * @����:����id���鷵���Ѿ���ʹ�õ�֧�������������Ϣ�б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��16�� ����1:15:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getUsedList(String [] ids) {
		StringBuilder sql = new StringBuilder("SELECT DZBDM,DZBMC FROM ");
		sql.append(" ((SELECT szdzb FROM xg_dtjs_zzgxzc_zzgxzcsqb UNION SELECT szdzb FROM xg_dtjs_zzgxzc_zzgxzcjgb)) t1 ");
		sql.append(" LEFT JOIN XG_DTJS_ZZGXZC_DZBDMB t2 ON t1.SZDZB = t2.DZBDM WHERE dzbdm = ");
		for(int i=0;i<ids.length;i++){
			sql.append(" ? ");
			if(i!=ids.length-1){
				sql.append(" or dzbdm = ");
			}
		}
		return dao.getListNotOut(sql.toString(),ids);
	}

}
