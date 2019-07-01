/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����02:11:15 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�����������ģ��
 * @�๦������: TODO(ѧ��֤��������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-17 ����02:11:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxsqDao extends SuperDAOImpl<YlbxsqForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("ylsqid");
		super.setTableName("xg_rcsw_ylbx_ylbxsqb");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxsqForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew from (select t1.ylsqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.cbsj,t1.zjsyrxm,t1.zjh,t5.xqmc, ");
		sql.append(" t1.cbzkdm,t1.sqly,t1.shzt,t1.splc,t2.xm,t2.xb,t2.bjmc, ");
		sql.append(" t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
		sql.append(" (t3.czqebzmc || '  ' || t7.czqebzmc || '  ' || t8.czqebzmc) bzlxmc, ");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc, ");
		sql.append(" ( case when t1.czqebzdm is not null  then '��' else '��' end ) czqebzmc, ");
		sql.append(" ( case when t1.cbzkdm is not null  then '�Ѳα�' else 'δ�α�' end ) cbzkmc ");
		sql.append(" from xg_rcsw_ylbx_ylbxsqb t1 left join ");
		sql.append(" (select t1.ylsqid,substr(t1.czqebzdm,'0','3') a1,substr(t1.czqebzdm,'5','3') a2,substr(t1.czqebzdm,'9','3') a3 from xg_rcsw_ylbx_ylbxsqb t1) t6 on t1.ylsqid=t6.ylsqid ");		
		sql.append(" left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xg_rcsw_ylbx_czqebzlxb t3 ");
		sql.append(" on t6.a1 = t3.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t7 on t6.a2 = t7.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t8 on t6.a3 = t8.czqebzdm left join xg_rcsw_ylbx_cbzklxb t4 ");
		sql.append(" on t1.cbzkdm = t4.cbzkdm  left join  xqdzb t5 on t1.xq = t5.xqdm ) a, xg_rcsw_ylbx_jcszb b where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @����:TODO()
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����02:19:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param Rcxwlbdldm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  splc from xg_rcsw_ylbx_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ����ȫ�����Ա��ݼ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����08:35:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCzqebzryList() {
		String sql = "select czqebzdm,czqebzmc from xg_rcsw_ylbx_czqebzlxb  order by czqebzdm ";
		return dao.getList(sql, new String[] {}, new String[] { "czqebzdm",
				"czqebzmc" });
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�α�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����08:35:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzkList() {
		String sql = " select cbzkdm,cbzkmc from xg_rcsw_ylbx_cbzklxb order by cbzkdm ";
		return dao.getList(sql, new String[] {}, new String[] { "cbzkdm","cbzkmc" });
	}
	
	/**
	 * 
	 * @����:TODO(�õ���ǰѧ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:16:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select xqmc from xqdzb where xqdm=? ");
		String xqmc = dao.getOneRs(sql.toString(), new String[] { model.getXq()}, "xqmc");
		return xqmc;
	}
	
	
	
	/**
	 * 
	 * @����:TODO(��ȡҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����10:40:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYlbxsq(String ylsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_ylbx_ylbxsqb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.ylsqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{ylsqid});
	}
	
	
	
	
	/**
	 * 
	 * @����:TODO(�鿴��������������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:57:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneBbsqList(String  bbsqId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc, ");
		sql.append(" decode(t1.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc,t3.xszbblxmc from xg_rcsw_xszbb_xszbbsqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
		sql.append(" on t1.xszbblxdm = t3.xszbblxdm  a  where select t1.bbsqid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{bbsqId});
	}
	

	
	/**
	 * 
	 * @����:TODO(��ȡ��������ά������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����02:46:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwdldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBblxwhList() {
		String sql = "select xszbblxdm,xszbblxmc from xg_rcsw_xszbb_bblxwhb ";
		return dao.getList(sql, new String[] {}, new String[] {"xszbblxdm", "xszbblxmc" });
	}
	
	
	public boolean isCanDel(String ylsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_ylbx_ylbxsqb where ylsqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{ylsqid});
		String ylbxsqzt=map.get("shzt");
		//���δ�ύ�ſ����ύ
		return null==ylbxsqzt||ylbxsqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(����ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����05:22:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ylsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYlbxsq(YlbxsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_ylbx_ylbxsqb ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where ylsqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getYlsqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:TODO(ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����04:34:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getYlbxsqInfo(YlbxsqForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.ylsqid,a.xh,a.xn,a.xq,a.sqsj,a.cbsj,a.zjsyrxm,a.zjh,a.qtcbzkval,b.xqmc, ");
		sql.append(" decode(a.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" a.shzt) shztmc,a.sqly from xg_rcsw_ylbx_ylbxsqb a ");
		sql.append(" left join xqdzb b on a.xq = b.xqdm where ylsqid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getYlsqid()});
		
	}
	
	/**
	 * 
	 * @����:TODO(����˵ļ�¼����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:34:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from xg_rcsw_ylbx_ylbxsqb where shzt='0' or shzt='5'  ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ��,ѧ�꣬ѧ���жϸ�ѧ��ҽ�Ʊ��������Ƿ��Ѿ�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����08:40:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(YlbxsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_ylbxsqb where xh=? and xn = ? and xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;

	}
	
	
	
	
}
