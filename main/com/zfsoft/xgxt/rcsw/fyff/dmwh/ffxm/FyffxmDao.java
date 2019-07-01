/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����01:44:53 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���÷���-��������ά��-������Ŀ
 * @�๦������: 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����01:44:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyffxmDao extends SuperDAOImpl<FyffxmForm> {

	/**
	 * ��ѯ
	 */
	public List<HashMap<String, String>> getPageList(FyffxmForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.ffxmdm,a.ffxmmc,a.mrffje,(case when fffs = '1' then '����' else '����' end) fffs  from xg_rcsw_fyff_ffxmdmb a where 1=1 ");
		
			if (!StringUtil.isNull(t.getFfxmmc())){
				params.add(t.getFfxmmc());
				sql.append(" and ffxmmc like '%'||?||'%'");
			}
			
			sql.append(" order by to_number(ffxmdm) ");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyffxmForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_fyff_ffxmdmb");
		super.setKey("ffxmdm");
	}
	
	
	/**
	 * 
	 * @����:�ж���Ŀ�����Ƿ��Ѿ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����02:27:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xmmcCheckExist(FyffxmForm t) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_rcsw_fyff_ffxmdmb where ffxmmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{t.getFfxmmc()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @����:��ȡ������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����02:35:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getMaxXmdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(ffxmdm)),1) ffxmdm from xg_rcsw_fyff_ffxmdmb ";
		
		return dao.getOneRsint(sql);
	}
	
	
	/**
	 * 
	 * @����:��ѯ���Ž�������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����02:42:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] xmdmCheckExistForFfjg( String value) throws Exception{
		
		StringBuilder sql = new StringBuilder(" select distinct b.ffxmmc from xg_rcsw_fyff_ffjgb a,xg_rcsw_fyff_ffxmdmb b where a.ffxmdm = b.ffxmdm and a.ffxmdm in (" +value +") ");
		String[] xmmc=dao.getRs(sql.toString(), new String[]{}, "ffxmmc");
			
		return xmmc;
	}
	
	
	/**
	 * 
	 * @����:��ȡ������Ŀlist
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����02:48:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFyffxm() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ffxmdm,ffxmmc,mrffje,fffs from xg_rcsw_fyff_ffxmdmb ");
		sb.append(" order by to_number(ffxmdm) ");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	
}
