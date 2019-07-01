package com.zfsoft.xgxt.zxdk.tyxs.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ѧ����������ģ��
 * @�๦������:�������� 
 * @���ߣ� ����Ӣ[����:1177]
 * @ʱ�䣺 2015-4-8 ����10:17:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TyxsCsszDao extends SuperDAOImpl<TyxsCssz> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(TyxsCssz.class);
		super.setKey("id");
		super.setTableName("xg_tyxsrx_xstycsb");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */


	public TyxsCssz getModel() throws Exception{
		String sql = "select * from xg_tyxsrx_xstycsb where rownum=1";
		return super.getModel(sql, new String[]{});
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TyxsCssz t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TyxsCssz t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 * 
	 * @����:��ȡѧ�����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-24 ����05:52:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getXlccList(){
		String sql = "select xlccmc from xlccdmb";
		return dao.getListNotOut(sql, null);
	}
}
