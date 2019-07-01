/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����05:03:37 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsb;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����05:03:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmsbDao extends SuperDAOImpl<XmsbForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(XmsbForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XmsbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfSztzByUser(user, "t", "sbbmdm", "sbr");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc,nvl(t6.xm,t7.xm) sbrxm,decode(t1.csms,'1','����','2','����') csmsmc, ");
		sql.append("case when '"+user.getUserName()+"' in (select yhm from xg_sztz_glyb) then 'true' when sbr ='"+user.getUserName()+"' then 'true' else 'false' end sfkxg,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc ");
		sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm left join yhb t6 on t1.sbr=t6.yhm left join xsxxb t7 on t1.sbr=t7.xh");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ��Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����02:03:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmjxList(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_sztz_xm_jx where xmdm=? order by to_number(xssx) asc");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	public List<HashMap<String, String>> getCyxyListForView(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.bmmc xymc from XG_SZTZ_XMCYXYB t1 left join zxbz_xxbmdm t2 on t1.xydm=t2.bmdm where t1.xmdm=? ");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	/**
	 * 
	 * @����:��ȡ��Ŀ��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����09:23:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getXmxx(String xmdm) {
		StringBuffer sql = new StringBuffer();
		if("13627".equals(Base.xxdm)){
			sql.append("select t1.*,nvl(t8.xm,t10.xm) sbrxm,case when t1.sfsljx='1' then '��' else '��' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc,a.mc bkgsmc ");
			sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
			sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm ");
			sql.append(" left join yhb t8 on t1.sbr=t8.yhm left join xsxxb t10 on t1.sbr=t10.xh left join XG_SZTZ_BKGS a on a.dm=t1.bkgs ");
			sql.append(" where t1.xmdm=?");
		}else{
			sql.append("select t1.*,nvl(t8.xm,t10.xm) sbrxm,case when t1.sfsljx='1' then '��' else '��' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc ");
			sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
			sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm ");
			sql.append(" left join yhb t8 on t1.sbr=t8.yhm left join xsxxb t10 on t1.sbr=t10.xh ");
			sql.append(" where t1.xmdm=?");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { xmdm });
	}
	/**
	 * 
	 * @����:��Ŀ������Ϣ��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����03:00:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean jxxxPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_sztz_xm_jx(jgid,xmdm,fjxf,jxmc,xssx) values(?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	public boolean cyxyPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into XG_SZTZ_XMCYXYB(xmdm,xydm) values(?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public boolean delCyxy(String xmdm) throws Exception {
		String sql = "delete from XG_SZTZ_XMCYXYB where xmdm= ?";
		return dao.runUpdate(sql, new String[] { xmdm });
	}
	/**
	 * 
	 * @����:ɾ��������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����03:12:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXmjx(String xmdm) throws Exception {
		String sql = "delete from xg_sztz_xm_jx where xmdm= ?";
		return dao.runUpdate(sql, new String[] { xmdm });
	}
	
	
	/**
	 * 
	 * @����:��ȡ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����11:03:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_sztz_xmsb_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
//	public XmsbForm getModel(XmsbForm model) throws Exception{
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select * from xg_sztz_xmsq t1 left join view_xg_qgzx_Sbbmdmdmb t2 on t1.Sbbmdm=t2.bmdm left join");
//		sql.append(" xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm where t1.xmdm=?");
//		return super.getModel(sql.toString(),new String[]{model.getXmdm()});
//	}
	
	
	/**
	 * 
	 * @����:�жϵ�ǰ��λ�Ƿ�����д��¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����11:05:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveSbJl(XmsbForm model,String czlx) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_sztz_xmsq where xn=? and xq=? and xmmc=?");
		if("update".equals(czlx)){
			sql.append(" and xmdm<>'"+model.getXmdm()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getXq(),model.getXmmc()}, "num");
		return Integer.parseInt(num)>0;
	}
	/**
	 * 
	 * @����:��Ŀ��Ŀ�б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����10:24:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmkmList()throws Exception{
		String sql ="select * from xg_sztz_sskm";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @����:��Ŀ�����б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����10:24:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmjbList()throws Exception{
		String sql ="select * from xg_sztz_xmjb";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @����:��ȡ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����03:16:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBmList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select bmdm xydm,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append("from zxbz_xxbmdm ");
		sql.append(") order by xypy ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xydm", "xymc" });
	}
	/**
	 * 
	 * @����: ��ȡ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����08:52:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdm
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String getBmmc(String bmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm,bmmc from zxbz_xxbmdm where bmdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bmdm }, "bmmc");
	}
	/**
	 * 
	 * @����:����ɾ����Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-13 ����05:27:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delPlXmjx(List<String[]> params) throws Exception {
		String sql = "delete from xg_sztz_xm_jx where xmdm = ?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	@Override
	protected void setTableInfo() {
		super.setClass(XmsbForm.class);
		super.setKey("xmdm");
		super.setTableName("xg_sztz_xmsq");

	}
	
	public List<HashMap<String, String>> getXmForJcfrd(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select jgid from xg_sztz_xs_sqjg where xmdm = ? and (ylzd1 is not null or ylzd3 is not null) union select jgid from xg_sztz_jcftz_jg where xmdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xmdm});
	}
	
	/**
	 * @�������������б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��2��4�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBkgsList()throws Exception{
		String sql ="select * from  XG_SZTZ_BKGS ";
		return dao.getListNotOut(sql, new String[]{});
	}
	
}
