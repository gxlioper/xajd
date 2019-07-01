/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-6 ����02:43:14 
 */
package xsgzgl.qgzx.mrgzkh.khsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-6 ����02:43:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class GzkhKhsqDao extends SuperDAOImpl<GzkhKhsqForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhsqForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc,t2.tc,t2.yhkh,t2.yhmc,t3.gwmc,nvl(t4.yrdwmc,t5.bmmc) yrdwmc,nvl(t4.xydm,t4.id) yrdwdm ");
		sql.append(",t6.gwxzmc,(t1.gzrq||' '||t1.gzkssj||'ʱ'||'-'||t1.gzjssj||'ʱ')gzsj,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t1.shzt) shztmc ");
		sql.append("from XG_QGZX_MRKH_SQB t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm");
		sql.append(" left join xg_qgzx_yrdwdmb t4 on t3.yrdwid=t4.id");
		sql.append(" left join ZXBZ_XXBMDM t5 on t4.xydm = t5.bmdm");
		sql.append(" left join XG_QGZX_GWXZDMB t6 on t3.gwlb = t6.gwxzdm");
		sql.append(" ) t where 1=1 ");
//		sql.append(searchTjByUser);
		if("stu".equals(user.getUserType())){
			sql.append(" and t.xh = '"+user.getUserName()+"' ");
		} else {//��ʦ
			sql.append(" and t.yrdwdm = '"+user.getUserDep()+"' ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	public String getGwid(String splc){
		String sql = "select spgw gwid from XG_XTWH_SPBZ where splc=? and xh = '1'";
		return dao.getOneRs(sql,new String[]{splc},"gwid");
	}
	/**
	 * 
	 * @����:��ȡѧ���ڹ���λ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����09:23:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getYrbm(GzkhKhsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct t4.bmdm, t4.bmmc ");
		sql.append("   from XG_QGZX_NEW_XSGWXXB t1 left join XG_QGZX_GWXXB t2 on t1.gwdm=t2.gwdm ");
		sql.append("   left join XG_QGZX_YRDWDMB t3 ");
		sql.append("     on t2.yrdwid = t3.id and t3.dwlb = '01'");//У�ڵ�λ
		sql.append("   left join ZXBZ_XXBMDM t4 ");
		sql.append("     on t3.xydm = t4.bmdm ");
		sql.append(" where t1.xh=? and t1.zgzt='zg'");
		sql.append(" order by t4.bmdm ");
		return dao.getList(sql.toString(), new String[] { model.getXh() }, new String[] { "bmdm", "bmmc" });

	}

	/**
	 * 
	 * @����:��ȡѧ���ڹ���λ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����09:33:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxx(GzkhKhsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("   select (t2.gwdm||','||t2.xn)gwdm,(t2.gwmc||'('||t2.xn||')')gwmc from XG_QGZX_NEW_XSGWXXB t1 left join XG_QGZX_GWXXB t2 on t1.gwdm=t2.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB t3 on t2.yrdwid = t3.id");
		sql.append(" where t1.xh=? and t3.xydm=? and t1.zgzt='zg' order by t2.xn");
		return dao.getList(sql.toString(), new String[] { model.getXh(), model.getYrdw() }, new String[] { "gwdm", "gwmc" });
	}
	/**
	 * 
	 * @����:��ȡ�ڸ�ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����10:13:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGzkhStuList(GzkhKhsqForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from (select distinct t1.xh,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.zybj,t2.zybjmc,t2.nj");
		sql.append(" from xg_qgzx_new_xsgwxxb t1 left join view_xsjbxx t2 on t1.xh=t2.xh where t1.zgzt='zg'");
		sql.append(")a where  1=1");
//		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����11:03:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_QGZX_MRKH_JCSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	public GzkhKhsqForm getModel(GzkhKhsqForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.sqid,t1.xh,t1.xn,t1.sqsj,t1.yrdw,t2.bmmc,(t3.gwdm||','||t3.xn)gwdm,(t3.gwmc||'('||t3.xn||')')gwmc,t1.gs,t1.gzrq,t1.gzkssj,t1.gzjssj,t1.gzdd,");
		sql.append(" t1.gznr,t1.shzt,t1.splc,t1.bz from XG_QGZX_MRKH_SQB t1 left join view_xg_qgzx_yrdwdmb t2 on t1.yrdw=t2.bmdm left join");
		sql.append(" xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm where t1.sqid=?");
		return super.getModel(sql.toString(),new String[]{model.getSqid()});
	}
	/**
	 * 
	 * @����:�жϵ�ǰ��λ�Ƿ�����д��¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����11:05:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveSqJl(GzkhKhsqForm model,String czlx) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_QGZX_MRKH_SQB where xh=? and xn=? and gwdm=? and gzrq=?");
		if("update".equals(czlx)){
			sql.append(" and sqid<>'"+model.getSqid()+"' ");
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getGwdm(),model.getGzrq()}, "num");
		return Integer.parseInt(num)>0;
	}

	public HashMap<String,String> getCjffXxOfStu(String xh,String xn,String gzrq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(gs),0)gs,nvl(sum(je),0)je from xg_qgzx_cjff where xh=? and ffsj =? and zgzt='zg'");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,gzrq.substring(0, 7)});
	}
	
	public String getCjByGwdm(String xh,String xn,String gwdm,String gzrq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(je),0)ffje from xg_qgzx_cjff where xh=?  and gwdm||','||xn=? and ffsj =? and zgzt='zg'");
		return dao.getOneRs(sql.toString(),new String[]{xh,gwdm,gzrq.substring(0, 7)},"ffje");
	}
	/**
	 * 
	 * @����:��ȡѧ��ĳһ��λĳ���ܽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-16 ����09:20:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public String getKhsqOfYf(String xh,String xn,String gwdm,String gzrq,String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(gs),0)zgs,nvl(sum(zje),0)zje from (select nvl(yxgs,gs)gs,(to_number(nvl(yxgs,gs))*to_number(cjbz))zje from XG_QGZX_MRKH_SQB where shzt in('0','5') and xh=?  and gwdm||','||xn=? and gzrq like '%'||?||'%'");
		if(null!=sqid){
			sql.append(" and sqid<>'"+sqid+"'");
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(),new String[]{xh,gwdm,gzrq.substring(0, 7)},"zje");
	}
	/**
	 * 
	 * @����:��ѯѧ��ĳ�������¼�ܹ�ʱ�ܽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-20 ����03:49:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param gwdm
	 * @param gzrq
	 * @param sqid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public HashMap<String,String> getsqjl(String xh,String xn,String gzrq,String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select nvl(sum(gs),0)zgs,nvl(sum(zje),0)zje from (select nvl(yxgs,gs)gs,(to_number(nvl(yxgs,gs))*to_number(cjbz))zje from XG_QGZX_MRKH_SQB where shzt in('0','5') and xh=?  and gzrq like '%'||?||'%'");
		if(null!=sqid){
			sql.append(" and sqid<>'"+sqid+"'");
		}
		sql.append(")");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,gzrq.substring(0, 7)});
	}
	
	/**
	 * ��ȡ��λ����
	 * 
	 */
	public HashMap<String,String> getGwxx(String gwdm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select gwmc,gwcjsx from xg_qgzx_gwxxb where gwdm||','||xn = ?"); 
		return dao.getMapNotOut(sql.toString(), new String[]{gwdm});
	}

	@Override
	protected void setTableInfo() {
		super.setClass(GzkhKhsqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_QGZX_MRKH_SQB");

	}

}
