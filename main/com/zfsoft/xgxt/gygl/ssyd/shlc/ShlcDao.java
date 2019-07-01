/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */  
package com.zfsoft.xgxt.gygl.ssyd.shlc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����춯ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ��  2013-09-22 16:51
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ShlcDao extends SuperDAOImpl<ShlcForm> {

	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_gygl_new_shlc");
		this.setClass(ShlcForm.class);
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(ShlcForm t)
			throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_gygl_new_shlc");
		return dao.getListNotOut(sb.toString(), null);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ShlcForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	public HashMap<String,String> getXx(String splcid){
		String sql="select * " +
				" from XG_GYGL_NEW_SHLC where splcid = ? ";
		return dao.getMapNotOut(sql, new String[]{splcid});
	}

	public String checkSsydlx(String ssydlx) throws Exception {
		String sql="select count(*) count" +
		" from XG_GYGL_NEW_SHLC where ssydlx = ? ";
		return dao.getOneRs(sql, new String[]{ssydlx}, "count") ;
	}
	
	public HashMap<String,String> getXxBySsydlx(String ssydlx){
		String sql="select * " +
				" from XG_GYGL_NEW_SHLC where ssydlx = ? ";
		return dao.getMapNotOut(sql, new String[]{ssydlx});
	}

	public List<HashMap<String,String>> getShlcList(){
		String sql = "select * from XG_GYGL_NEW_SHLC";
		return dao.getListNotOut(sql, new String[]{});
	}

	public boolean delete() throws Exception{
		String sql = "delete from XG_GYGL_NEW_SHLC";
		return dao.runUpdate(sql, new String[]{});
	}
	
	public boolean insert(ShlcForm t) throws SQLException{
		List<String[]> list = t.getParamList();
		String sql = "insert into XG_GYGL_NEW_SHLC (ssydlx,splcid) values(?,?)";
		return dao.runBatchBoolean(sql, list);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-3-13 ����05:54:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ssydlx
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getSplcid(String ssydlx) {
		String sql="select splcid " +
		" from XG_GYGL_NEW_SHLC where ssydlx = ? ";
		return dao.getOneRs(sql, new String[]{ssydlx}, "splcid") ;
	}

}
