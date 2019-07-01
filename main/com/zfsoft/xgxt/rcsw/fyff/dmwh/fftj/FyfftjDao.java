/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����09:40:48 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���÷���-��������ά��-����;��
 * @�๦������: 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����09:40:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyfftjDao extends SuperDAOImpl<FyfftjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyfftjForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.fftjdm,a.fftj,(case when ffzh = '0' then '����д' else '������д' end ) ffzh from xg_rcsw_fyff_ffltj a where 1=1 ");
		
		if (!StringUtil.isNull(t.getFftj())){
			params.add(t.getFftj());
			sql.append(" and fftj like '%'||?||'%'");
		}
		
		sql.append(" order by to_number(fftj) ");
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyfftjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	protected void setTableInfo() {

		super.setTableName("xg_rcsw_fyff_ffltj");
		super.setKey("fftjdm");

	}
	
	/**
	 * 
	 * @����:��ѯ����;���Ƿ��Ѿ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����02:56:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t fyfftjForm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String fftjCkeckExist(FyfftjForm t){
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_rcsw_fyff_ffltj where fftj = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{t.getFftj()}, "num");
		
		return num;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:��ȡ���;������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����03:01:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int getMaxTjdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(fftjdm)),1) ffxmdm from xg_rcsw_fyff_ffltj ";
		
		return dao.getOneRsint(sql);
		
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ;���Ƿ���ڷ��Ž��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����03:09:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String[] tjCheckExistForFfjg(String value) throws Exception{
		
		StringBuilder sql = new StringBuilder(" select distinct b.fftj from xg_rcsw_fyff_ffjgb a,xg_rcsw_fyff_ffltj b where a.fftjdm = b.fftjdm and a.fftjdm in (" +value +") ");
		String[] fftj = dao.getRs(sql.toString(), new String[]{}, "fftj");
		
		return fftj;
		
	}
	
	
	/**
	 * 
	 * @����:��ȡ����;��list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����02:48:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFyfftj() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select fftjdm,fftj,ffzh from xg_rcsw_fyff_ffltj ");
		sb.append(" order by to_number(fftjdm) ");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	

}
