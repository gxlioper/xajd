/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-1-19 ����02:02:56 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.wmqs;

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
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-1-19 ����02:02:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WmqsDao  extends SuperDAOImpl<WmqsForm>{
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_wmqsb");
		super.setKey("guid");
		super.setClass(WmqsForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.*,a.xqmc FROM xg_zjdx_pjpy_wmqsb t left join DM_ZJU_XQ a on  t.xqdm=a.dm");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-19 ����05:46:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForAddSave(WmqsForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) num from ( SELECT t.*,a.xqmc FROM xg_zjdx_pjpy_wmqsb t " +
				"left join DM_ZJU_XQ a " +
				"on  t.xqdm=a.dm where xn=? and xqmc=? and ldmc=? and qsh=?)"); 
		String[] inputV = new String[] {model.getXn(),model.getXqmc().trim(), model.getLdmc().trim(),model.getQsh()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-19 ����05:46:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForUpdate(WmqsForm model) {
		StringBuilder sql = new StringBuilder(
				"select count(1) num from ( SELECT t.*,a.xqmc FROM xg_zjdx_pjpy_wmqsb t " +
				" left join DM_ZJU_XQ a on  t.xqdm=a.dm )" +
				"  where xn=? and xqmc=? and ldmc=? and qsh=?  and guid<>?");
		String[] inputV = {model.getXn(),model.getXqmc().trim(),model.getLdmc().trim(),model.getQsh().trim(),model.getGuid()};
		
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/**
	 * @throws SQLException  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-22 ����09:21:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXqmcList() throws SQLException {
		String sql = "select distinct dm,xqmc from dm_zju_xq order by dm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "xqmc" });
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-22 ����10:22:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getLdSum(WmqsForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) num from  XG_GYGL_NEW_LDXXB where ldmc=? "); 
		String[] inputV = new String[] {model.getLdmc().trim()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-22 ����02:28:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqmc
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXqdm(String xqmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select dm  from  dm_zju_xq where xqmc=?"); 
		String[] inputV = new String[] {xqmc.trim()};
		String xqdm = dao.getOneRs(sql.toString(), inputV, "dm");
		return xqdm;
	}

}
