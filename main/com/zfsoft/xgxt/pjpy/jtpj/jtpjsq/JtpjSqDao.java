package com.zfsoft.xgxt.pjpy.jtpj.jtpjsq;

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-28 ����10:54:25 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-28 ����10:54:25
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjSqDao extends SuperDAOImplExtend<JtpjSqForm> {
	@Override
	public List<HashMap<String, String>> getPageList(JtpjSqForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();

		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JtpjSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		// �û�����
		String userType = user.getUserType();
		// jxpdzq
		// shlcmc
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select d.* ,n.pdxn,n.pdxq,n.jxmc,(select xqmc from xqdzb where xqdm=n.pdxq) pdxqmc ");
		sql.append(" from xg_pjpy_jtpj_jtjxsqb d ");			
		sql.append(" left join xg_pjpy_jtpj_jtjxsz n");
		sql.append(" on d.jxid=n.jxid)a ");
		sql.append(" where 1 = 1");
		sql.append(searchTj);
		if(!"admin".equalsIgnoreCase(userType)) {
			sql.append(" and a.sqr='"+user.getUserName()+"'");
		}
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: ��ȡ�༶������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-6 ����02:44:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param bjdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForQsxx(JtpjSqForm t,
			String bjdm) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.pylbmc,t1.ldmc || ' '|| t1.qsh qs,t2.rs,t2.bjdm,t1.xn || t1.xq xnxq from VIEW_XG_GYGL_PYXXWHB t1 ");
		sql.append(" left join  ");
		sql.append(" (select count(xh) rs , lddm, ch,qsh,bjdm ");
		sql.append("  from view_xg_gygl_new_cwxx ");
		sql.append("  group by  lddm, ch,qsh,bjdm ");
		sql.append("  ) ");
		sql.append("  t2 ");
		sql.append(" on t1.lddm = t2.lddm ");
		sql.append(" and t1.qsh = t2.qsh ");
		sql.append(" and t1.ch = t2.ch ");
		sql.append(" where t2.bjdm=?");
		
		return this.getPageList(t, sql.toString(), new String[] { bjdm });
	}
	
	public List<HashMap<String, String>> getQsxsxxList(JtpjSqForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.lddm,t1.qsh,t1.cwh,t1.xh,t2.xm,t2.bjmc,t2.zymc,t2.xymc  ");
		sql.append("from xg_gygl_new_cwxxb t1 left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append("where t1.xh is not null and t2.xm is not null and t1.lddm=? and t1.qsh=? ");
		return this.getPageList(t, sql.toString(), new String[] {t.getLddm(),t.getQsh()});
	}

	/**
	 * 
	 * @����: �༶����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:30:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return String ��������
	 */
	public String getBjrs(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select count(xh) rs,bjdm from view_xsbfxx a group by bjdm) where bjdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bjdm }, "rs");
	}

	/**
	 * 
	 * @����: ѧԺ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����01:41:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return String ��������
	 */
	public String getXyrs(String xydm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select count(xh) rs,xydm from view_xsbfxx a group by xydm) where xydm=?");
		return dao.getOneRs(sql.toString(), new String[] { xydm }, "rs");
	}

	/**
	 * 
	 * @����: ѧԺ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����01:41:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return String ��������
	 */
	public String getXyQss(String xydm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append("  select count(qs) qss, xydm");
		sql.append("   from (select distinct lddm || '&' || qsh qs, xydm");
		sql.append("   from VIEW_XG_GYGL_NEW_CWXX) a");
		sql.append("   group by xydm");
		sql.append(" ) where xydm=?");
		return dao.getOneRs(sql.toString(), new String[] { xydm }, "qss");
	}

	/**
	 * 
	 * @����: �༶������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:29:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return String ��������
	 */
	public String getQss(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select count(qs) qss, bjdm");
		sql.append(" from (select distinct lddm || '&' || qsh qs, bjdm");
		sql.append(" from VIEW_XG_GYGL_NEW_CWXX) a");
		sql.append(" group by bjdm )");
		sql.append(" where bjdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bjdm }, "qss");
	}

	/**
	 * 
	 * @����: �༶��������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:29:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBzrxx(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from BZRBBB a left join FDYXXB b on a.zgh=b.zgh where bjdm=?");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}

	/**
	 * 
	 * @����: �༶����Ա��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����11:29:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getFdyxx(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from FDYBJB a left join FDYXXB b on a.zgh=b.zgh where bjdm=?");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}

	@Override
	protected void setTableInfo() {
		setKey("sqid");
		setTableName("xg_pjpy_jtpj_jtjxsqb");
		setClass(JtpjSqForm.class);
	}

	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:25:35
	 * @param xjlbdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkSfktj(String jxid) {		
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from xg_pjpy_jtpj_jtjxsz t2 ");
		sql.append("  where t2.sfkfsq = '1'       ");
		sql.append("    and ((t2.SQKFKSSJ is not null and ");
		sql.append("        t2.SQKFKSSJ <= to_char(sysdate, 'yyyy-mm-dd')) or t2.SQKFKSSJ is null) ");		
		sql.append("    and ((t2.SQKFJSSJ is not null and ");
		sql.append("        t2.SQKFJSSJ >= to_char(sysdate, 'yyyy-mm-dd')) or t2.SQKFJSSJ is null) ");
		sql.append("    and t2.jxid = ? ");
		num = dao.getOneRs(sql.toString(), new String[] { jxid }, "num");
		return num;	
	}
	
	/**
	 * @����: ��ӡWord�ǼǱ��Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-4 ����11:30:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.sqid,a.sqxn, ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc, ");
		sql.append(" decode(a.jtpjdw,'bj',c.xm,'') bzrxm, ");
		sql.append(" a.jtpjdw,a.pjjtmc,a.sqly,d.jxmc from xg_pjpy_jtpj_jtjxsqb a ");		
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" left join ( ");
		sql.append(" select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm ");
		sql.append(" ) c on a.pjjtid=c.bjdm ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz d on a.jxid=d.jxid ");
		sql.append(" where a.sqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}

	/**
	 * @����: ��ӡWord�ǼǱ��ӱ���ҵ��ѧ��
	 * @���ߣ������
	 * @���ڣ�2018-7-24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 */
	public HashMap<String, String> getjtpjInfo(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.sqid,a.sqxn,a.pjjtid as bjdm,t4.mbmc, ");
		sql.append(" decode(a.jtpjdw,'xy',a.pjjtmc,'bj',b.xymc,'') xymc, ");
		sql.append(" decode(a.jtpjdw,'bj',a.pjjtmc,'') bjmc, ");
		sql.append(" decode(a.jtpjdw,'bj',c.xm,'') bzrxm, t3.xm as fdyxm,  (select count(*) from view_xsxxb y where y.BJDM = a.pjjtid)as bjrs ,");
		sql.append("( select x.xm from VIEW_NEW_DC_SZDW_XSDWWH x where x.BJDM= a.pjjtid and x.ZWMC='�೤')as bzmc, ");
		sql.append(" a.jtpjdw,a.pjjtmc,a.sqly,d.jxmc from xg_pjpy_jtpj_jtjxsqb a ");
		sql.append(" left join VIEW_NEW_DC_XSXX_JCSJWH_BJ b on a.pjjtmc=b.bjmc ");
		sql.append(" left join ( ");
		sql.append(" select wm_concat(b.xm) xm,a.bjdm from BZRBBB a left join FDYXXB b on a.zgh=b.zgh group by a.bjdm ");
		sql.append(" ) c on a.pjjtid=c.bjdm ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz d on a.jxid=d.jxid  ");
		sql.append("left join XG_PJPY_JTPJ_JTJXBBDMB t4  on d.dybbid=t4.bbdm ");
		sql.append(" left join (select t1.*,t2.xm from  fdybjb t1  left join fdyxxb t2 on t1.zgh = t2.zgh) t3 on a.pjjtid = t3.bjdm");
		sql.append(" where a.sqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}
	
	public HashMap<String, String> getDjb(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select  a.*, b.jxmc, b.dybbid, b.pdxn, c.bbmc, c.mbmc from XG_PJPY_JTPJ_JTJXSQB a ");
		sql.append(" left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid = b.jxid ");
		sql.append("left join XG_PJPY_JTPJ_JTJXBBDMB c  on b.dybbid=c.bbdm ");
		sql.append(" where a.sqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}
	
	public String getBjmc(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjmc from view_njxyzybj where bjdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{bjdm}, "bjmc");
	}
	
	/**
	 * 
	 * @����:�����Ƿ񲻴��������¼�������ڷ���true
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-16 ����11:20:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxid
	 * @param jtpjid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String jxid,String pjjtid,String pjjtmc,String sqid,String flag){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		String tablename = "";
		if("sq".equals(flag)){
			tablename = "xg_pjpy_jtpj_jtjxsqb";
		}else{
			tablename = "xg_pjpy_jtpj_jtpjjgb";
		}
		sql.append(" select count(1) num from "+ tablename +" t where jxid = ? ");
		paralist.add(jxid);
		if(StringUtils.isNotNull(pjjtid)){
			sql.append(" and pjjtid = ? ");
			paralist.add(pjjtid);
		}
		if(StringUtils.isNotNull(pjjtmc)){
			sql.append(" and pjjtmc = ? ");
			paralist.add(pjjtmc);
		}
		//�������id��Ϊ�գ���ʾ���޸ı��棬���������������sql
		if("sq".equals(flag) && StringUtils.isNotNull(sqid) ){
			sql.append(" and sqid != ?");
			paralist.add(sqid);
		}
		if("jg".equals(flag) && StringUtils.isNotNull(sqid) ){
			sql.append(" and jgid != ?");
			paralist.add(sqid);
		}
		String numstr = dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "num");
		if("0".equals(numstr)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @����: ��ȡ�԰༶Ϊ������λ����������������λ��Ϣ��ѧԺ���꼶�ȣ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-17 ����11:10:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjmc
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBjxgxx(String bjmc){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct * from view_njxyzybj t where t.bjmc = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{bjmc});
	}

	public List<HashMap<String,String>> getbjgbInfo(String bjdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.xm,a.zwmc from VIEW_NEW_DC_SZDW_XSDWWH a where a.zwmc!='�೤' and a.bjdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}
}
