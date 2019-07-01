/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����11:43:44 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-24 ����11:43:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyyjxdmwhDao extends SuperDAOImpl<GyyjxForm> {

	
	/**
	 * ��ȡ ������
	 * @return
	 * @throws SQLException
	 */
	public int getMaxdm() throws SQLException{
		
		String sql = "select nvl(max(to_number(YJFLDM)),0) YJFLDM from XG_GYGL_GYYJX_YJFL";
		
		return dao.getOneRsint(sql);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* , decode(t.txs , '0' , 'w'  , 'y') as txsmc from (select to_number(a.yjfldm) yjfldm , a.yjflmc , (select count(1) from XG_GYGL_GYYJX_GYYJ b where b.yjfldm = a.yjfldm) txs from XG_GYGL_GYYJX_YJFL a ) t where 1=1 ").append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	//���Ψһ��
	public boolean checkExist(String dmmc) throws Exception {
		String sql = "select * from XG_GYGL_GYYJX_YJFL where yjflmc = ? ";
		return dao.getListNotOut(sql, new String[]{dmmc}).size() > 0 ? true : false;
	}
	
	//���Ψһ���޸�
	public boolean checkExist(String dmmc , String dm) throws Exception {
		String sql = "select * from XG_GYGL_GYYJX_YJFL where yjflmc = ? and yjfldm <> ?";
		return dao.getListNotOut(sql, new String[]{dmmc , dm}).size() > 0 ? true : false;
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(GyyjxForm.class);
		setKey("YJFLDM");
		setTableName("XG_GYGL_GYYJX_YJFL");
	}

}
