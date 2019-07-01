/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-20 ����11:23:55 
 */
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-ѧ�Ż����ģ��
 * @�๦������: ѧ�Ż����
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-20 ����11:23:55
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdDmwhDao extends SuperDAOImpl<TxhdDmwhForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdDmwhForm t)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(case when a.lbdm in (select lbdm from xg_rcsw_txhd_xmwh) or a.lbdm in ");
		sql.append("(select lbdm from xg_rcsw_txhd_jgb)then '��' else '��' end ) sfsy from xg_rcsw_txhd_lbdm a where 1=1 ");
		if (!StringUtil.isNull(t.getLbmc())) {
			params.add(t.getLbmc());
			sql.append(" and lbmc like '%'||?||'%'");
		}

		sql.append(" order by to_number(lbdm) ");

		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdDmwhForm t, User user)
			throws Exception {
		// �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {

		super.setTableName("xg_rcsw_txhd_lbdm");
		super.setKey("lbdm");

	}

	/**
	 * 
	 * @����: �ж���������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����01:44:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param txhdDmwhForm
	 * @return String ��������
	 * @throws
	 */
	public String lbmcCheckExist(TxhdDmwhForm form) {

		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_txhd_lbdm where lbmc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { form
				.getLbmc() }, "num");
		return num;
	}

	/**
	 * 
	 * @����: �ж��������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����01:53:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param txhdDmwhForm
	 * @return String ��������
	 * @throws
	 */
	public String lbdmCheckExist(TxhdDmwhForm form) {

		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_txhd_lbdm where lbdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { form
				.getLbdm() }, "num");
		return num;
	}

	/**
	 * 
	 * @����:��ѯ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����02:10:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException int ��������
	 * @throws
	 */
	public int getMaxLbdm() throws SQLException {

		String sql = "select nvl(max(to_number(lbdm)),0) lbdm from xg_rcsw_txhd_lbdm";

		return dao.getOneRsint(sql);
	}

	/**
	 * 
	 * @����:��ѯ��������Ƿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����02:15:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 *             String[] ��������
	 * @throws
	 */
	public String[] lbCheckExistForXmwh(String value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" select distinct b.lbmc from xg_rcsw_txhd_xmwh a left join xg_rcsw_txhd_lbdm b on a.lbdm=b.lbdm ");
		sql.append(" where b.lbdm in ( " + value + " ) ");
		String[] lbmc = dao.getRs(sql.toString(), new String[] {}, "lbmc");

		return lbmc;
	}

	/**
	 * 
	 * @����:��ѯ��������Ƿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����02:36:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 *             String[] ��������
	 * @throws
	 */
	public String[] lbCheckExistForJg(String value) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" select distinct b.lbmc from xg_rcsw_txhd_jgb a left join xg_rcsw_txhd_lbdm b on a.lbdm=b.lbdm ");
		sql.append(" where b.lbdm in ( " + value + " ) ");
		String[] lbmc = dao.getRs(sql.toString(), new String[] {}, "lbmc");

		return lbmc;
	}

	/** 
	 * @����:��ȡ������List
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����11:59:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLblist() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select lbdm,lbmc from xg_rcsw_txhd_lbdm order by lbdm ");
		String[] input = {};
		
		return dao.getListNotOut(sb.toString(), input);
	}
	
	public List<HashMap<String, String>> getHdggList(TxhdDmwhForm t)
	   throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(case when a.hdggdm in (select hdggdm from xg_rcsw_txhd_xmwh)" +
				" or a.hdggdm in(select hdggdm from xg_rcsw_txhd_xmsq) ");
		sql.append("then '��' else '��' end ) sfsy from XG_RCSW_TXHD_HDGG a where 1=1 ");
		if (!StringUtil.isNull(t.getHdggmc())) {
			params.add(t.getHdggmc());
			sql.append(" and hdggmc like '%'||?||'%'");
		}
		
		sql.append(" order by to_number(a.hdggdm) asc ");
		
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
   }
	
	//������������
	public boolean saveHdgg(TxhdDmwhForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into XG_RCSW_TXHD_HDGG values(SEQ_HDGG.nextval,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getHdggmc()});
	}
	
	//������������
	public boolean saveUpdateHdgg(TxhdDmwhForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update XG_RCSW_TXHD_HDGG set hdggmc = ? where hdggdm = ?");
		return dao.runUpdate(sql.toString(), new String[]{t.getHdggmc(),t.getHdggdm()});
	}
	
	//�����ظ���֤
	public boolean checkIsExits(TxhdDmwhForm t){
		StringBuilder sql = new StringBuilder();
		String[] inputValue = new String[]{t.getHdggmc()};
		sql.append("select count(1) num from XG_RCSW_TXHD_HDGG where hdggmc = ? ");
		if(t.getHdggdm() != null && !t.getHdggdm().equals("")){
			sql.append(" and hdggdm <> ?");
			inputValue = new String[]{t.getHdggmc(),t.getHdggdm()};
		}
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		return Integer.parseInt(num)>0;
	}
	
	//ɾ��
	public int delHdgg(String[] hdggdms) throws Exception{
		StringBuilder sql = new StringBuilder();
		int num = hdggdms.length;
		boolean result = true;
		sql.append("delete from XG_RCSW_TXHD_HDGG where hdggdm in(");
		for(int i=0;i < num;i++){
			if(i != num-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		result = dao.runUpdate(sql.toString(), hdggdms);
		return result ? num : 0 ;
	}
	
	//��ȡ�����б�
	public List<HashMap<String, String>> getHdggList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select hdggdm dm,hdggmc mc from  xg_rcsw_txhd_hdgg ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//��ȡ��������
	public String getHdggmc(String hdggdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select hdggmc hdgg from xg_rcsw_txhd_hdgg where hdggdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{hdggdm}, "hdgg");
	}

}
