/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-23 ����03:20:21 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡���ѧ�
 * @�๦������: ��ѧ�Dao
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-23 ����03:20:21
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXmszDao extends SuperDAOImpl<TxhdXmszForm> {

	/**
	 * ��Ŀ��ѯlist
	 */
	public List<HashMap<String, String>> getPageList(TxhdXmszForm t)
			throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc, ");
		sql.append("hdkssj||' �� '||hdjssj hdsj from xg_rcsw_txhd_xmwh a where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmszForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc, ");
		sql.append("hdkssj||' �� '||hdjssj hdsj from xg_rcsw_txhd_xmwh a where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	
	protected void setTableInfo() {

		super.setClass(TxhdXmszForm.class);
		super.setTableName("xg_rcsw_txhd_xmwh");
		super.setKey("xmdm");

	}

	/**
	 * 
	 * @����:������Ŀ�����ѯ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-23 ����04:27:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getInfoById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.*,b.xh,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc from xg_rcsw_txhd_xmwh a ");
		sb.append(" left join xg_xtwh_spbz b on a.shlc=b.splc and a.rskzjb=b.spgw where xmdm = ?");
		String[] inputValue = { xmdm };
		return dao.getMapNotOut(sb.toString(), inputValue);

	}

	/**
	 * 
	 * @����:������Ŀ���Ʋ�ѯ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-23 ����04:27:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getDateByName(String xmmc) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb
				.append(" select a.*,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc from xg_rcsw_txhd_xmwh a where xmmc = ? ");
		String[] inputValue = { xmmc };
		return dao.getMapNotOut(sb.toString(), inputValue);

	}
	
	
	/**
	 * 
	 * @����:���Ӳ���Ψһ���ж�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����01:42:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(TxhdXmszForm form) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_txhd_xmwh where xn=? and xq=? and xmmc=? ");
		String num = dao.getOneRs(sql.toString(), new String[] { form.getXn(),form.getXq(),form.getXmmc() }, "num");
		return num;
	}

	
	
	/**
	 * @throws Exception  
	 * @����:�ж��Ƿ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����07:38:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isRalate(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		String sql = "select count(*)  from xg_rcsw_txhd_xmsq  ";
		sql += " where xmdm in("+values+")";
		int result = dao.getOneRsint(sql);
		return result > 0;
	}

	/** 
	 * @����:������Ŀ��������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����07:56:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getNameById(String xmdm) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmmc ");
		sb.append(" from  xg_rcsw_txhd_xmwh ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		String[] outputValue = { "xmmc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (oneRs != null && oneRs.length > 0) {
			xmmc = oneRs[0];
		}
		return xmmc;
	}

}
