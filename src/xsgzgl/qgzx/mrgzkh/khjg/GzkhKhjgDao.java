package xsgzgl.qgzx.mrgzkh.khjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GzkhKhjgDao extends SuperDAOImpl<GzkhKhjgForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xb,t2.zzmmmc,t2.mzmc,t2.yhkh,t2.tc,t2.yhmc,t3.gwmc,case when t5.xm is not null then t5.xm else t6.xm end czyhxm,substr(t1.gzrq,0,4) nd,substr(t1.gzrq,6,2) yf, ");
		sql.append("(t1.gzrq||' '||t1.gzkssj||'ʱ'||'-'||t1.gzjssj||'ʱ')gzsj,nvl(t4.yrdwmc,t6.bmmc) yrdwmc ");
		sql.append(" from XG_QGZX_MRKH_JGB t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm");
		sql.append(" left join xg_qgzx_yrdwdmb t4 on t3.yrdwid=t4.id ");
		sql.append(" left join ZXBZ_XXBMDM t6 on t4.xydm = t6.bmdm");
		sql.append(" left join FDYXXB t5 on t1.czyh=t5.zgh left join xsxxb t6 on t1.czyh = t6.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * ��ȡѧ���ڹ���λ��������
	 */
	public List<HashMap<String, String>> getYrbm(GzkhKhjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct t3.bmdm, t3.bmmc ");
		sql.append("   from xg_qgzx_xsgwxxb t1 left join XG_QGZX_GWXXB t2 on t1.gwdm=t2.gwdm ");
		sql.append("   left join view_xg_qgzx_yrdwdmb t3 ");
		sql.append("     on t2.yrdwdm = t3.bmdm where t1.xh=? and t1.zgzt='zg'");
		sql.append(" order by t3.bmdm  ");
		return dao.getList(sql.toString(), new String[] { model.getXh() }, new String[] { "bmdm", "bmmc" });

	}

	/**
	 * ��ȡѧ���ڹ���λ
	 */
	public List<HashMap<String, String>> getGwxx(GzkhKhjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("   select (t2.gwdm||','||t2.xn)gwdm,(t2.gwmc||'('||t2.xn||')')gwmc from xg_qgzx_xsgwxxb t1 left join XG_QGZX_GWXXB t2 on t1.gwdm=t2.gwdm ");
		sql.append(" where t1.xh=? and t2.yrdwdm=? and t1.zgzt='zg' order by t2.xn ");
		return dao.getList(sql.toString(), new String[] { model.getXh(), model.getYrdw() }, new String[] { "gwdm", "gwmc" });
	}
	
	public GzkhKhjgForm getModel(GzkhKhjgForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.id,t1.xh,t1.xn,t1.sqsj,t1.yrdw,t2.bmmc,(t3.gwdm||','||t3.xn)gwdm,(t3.gwmc||'('||t3.xn||')')gwmc,t1.gs,t1.gzrq,t1.gzkssj,t1.gzjssj,t1.gzdd,");
		sql.append(" t1.gznr,t1.bz,case when t5.xm is not null then t5.xm else t6.xm end czyhxm,t1.cjsj from XG_QGZX_MRKH_JGB t1 left join view_xg_qgzx_yrdwdmb t2 on t1.yrdw=t2.bmdm left join");
		sql.append(" xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm ");
		sql.append(" left join FDYXXB t5 on t1.czyh=t5.zgh left join xsxxb t6 on t1.czyh = t6.xh ");
		sql.append(" where t1.id=? ");
		return super.getModel(sql.toString(),new String[]{model.getId()});
	}
	/**
	 * ���ͨ��ʱ�жϽ�����Ƿ��м�¼
	 */
	public GzkhKhjgForm getKhjg(GzkhKhjgForm model)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_QGZX_MRKH_JGB where xh=? and xn=? and gwdm=? and gzrq=? ");
		return super.getModel(sql.toString(),new String[]{model.getXh(),model.getXn(),model.getGwdm(),model.getGzrq()});
	}
	
	/**
	 * ͨ��id��ȡ�������
	 */
	public GzkhKhjgForm getKhjgById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_QGZX_MRKH_JGB where sqid=? ");
		return super.getModel(sql.toString(),new String[]{id});
	}
	/**
	 * ��ȡѧ����𷢷���Ϣ
	 */
	public GzkhKhjgForm getCjffXx(GzkhKhjgForm model)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_qgzx_cjff where xh=? and xn=? and gwdm=? and ffsj =?");
		return super.getModel(sql.toString(),new String[]{model.getXh(),model.getXn(),model.getGwdm(),model.getGzrq().substring(0, 7)});
	}
	/**
	 * ɾ��������д��¼
	 */
	public boolean delKhjg(GzkhKhjgForm model)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_QGZX_MRKH_JGB where xh=? and xn=? and gwdm=? and gzrq=? ");
		return dao.runUpdate(sql.toString(),new String[]{model.getXh(),model.getXn(),model.getGwdm(),model.getGzrq()});
	}
	/**
	 * 
	 * @����:ͨ������idɾ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����03:29:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delKhjgById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_QGZX_MRKH_JGB where sqid=? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}
	/**
	 * 
	 * @����:ѧ�������Ϣ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����02:55:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCjxx(String id,String je,GzkhKhjgForm model)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("insert into xg_qgzx_cjff (XH, WBH, XN, YRBM, GWDM, ZGZT, GS, JE, FFSJ, BZ, SJBS, KHDJ) values(?,?,?,?,?,?,?,?,?,?,?,?) ");
		return dao.runUpdate(sql.toString(),new String[]{model.getXh(),id,model.getXn(),model.getYrdw(),model.getGwdm(),"zg",model.getGs(),je,model.getGzrq().substring(0, 7),model.getBz(),"",""});
	}
	
	/**
	 * �жϵ�ǰ��λ�Ƿ�����д��¼
	 */
	public boolean checkExistForSave(GzkhKhjgForm model) {
		String id = model.getId() == null ? "-1" : model.getId();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_QGZX_MRKH_JGB where xh=? and (GWDM||','||xn)=? and gzrq=? and id<>? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getGwdm(),model.getGzrq(),id}, "num");
		return Integer.valueOf(num) > 0;
	}
	/**
	 * 
	 * @����: �޸��ų������ʱ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-20 ����01:53:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> checkExistForUpdate(String id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select gs,to_number(gs*cjbz) je from XG_QGZX_MRKH_JGB where id = ? "); 
		return dao.getMapNotOut(sql.toString(), new String[]{id});
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
		super.setClass(GzkhKhjgForm.class);
		super.setKey("id");
		super.setTableName("XG_QGZX_MRKH_JGB");

	}
	
}
