/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:30:49 
 */  
package com.zfsoft.xgxt.xpjpy.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-��������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:30:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszDao extends SuperDAOImpl<CsszModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(CsszModel.class);
		setTableName("xg_pjpy_new_csszb");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @����: ��ѯ����������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����08:58:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * CsszModel ��������
	 */
	public CsszModel getModel() throws Exception{
		
		String sql = "select t1.*,t1.xn||' '||t2.xqmc zqmc from xg_pjpy_new_csszb t1 left join xqdzb t2 on t1.xq=t2.xqdm where rownum=1";
		
		return getModel(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����11:14:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pjzq
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updatePjzq(String[] pjzq) throws Exception{
		
		String sql = "update xg_pjpy_new_csszb set xn=? , xq=? where rownum=1";
		
		return dao.runUpdate(sql, pjzq);
	}
	
	
	/**
	 * 
	 * @����: ���²�������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����11:15:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateCssz(String key,String value) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_csszb set ");
		sql.append(key);
		sql.append("=? where rownum=1");
		
		return dao.runUpdate(sql.toString(), new String[]{value});
	}

	/** 
	 * @����:��ѯ���ñ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-7-28 ����11:24:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public String getCsz(String csdm) {

		String sql = "select csz from xg_pjpy_new_cspzb where csdm = ? ";
		
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
		
	}
	
}
