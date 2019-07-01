/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-17 ����09:43:56 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.generate.FbCheck;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-2-17 ����09:43:56
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglBbglDao extends SuperDAOImplExtend<FbglBbglForm> {
	private FbCheck fbCheck = new FbCheck();

	@Override
	public List<HashMap<String, String>> getPageList(FbglBbglForm t)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @����: ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-8 ����04:49:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException boolean ��������
	 */
	public boolean batchInsert(List<String[]> params) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(this.getTableName());
		sb.append("(pzgzid,bjbh,bjdm,bjmc,bjrssx,nj,bmdm,pycc,xz,zydm)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?)");
		return dao.runBatch(sb.toString(), params).length == params.size();
	}

	/**
	 * 
	 * @����: ���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����05:04:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fbf
	 * @return String �������� ��ʾ��Ϣkey
	 */
	public String checkBb(FbglBbglForm fbf) {
		if (!fbCheck.checkUniqeKey(getTableName(), "bjdm", fbf.getBjdm())) {
			System.out.println(fbf.getBjdm());
			return MessageKey.FBGL_CHECK_BJDM_UNIQUE;

		} else if (!fbCheck.checkTableColumLength(getTableName(), "bjmc", fbf
				.getBjmc())) {
			return MessageKey.FBGL_CHECK_BJMC;
		} else if (!fbCheck.checkTableColumLength(getTableName(), "bjdm", fbf
				.getBjdm())) {
			return MessageKey.FBGL_CHECK_BJDM;
		}
		return MessageKey.FBGL_CHECK_OK;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglBbglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,t1.pyccmc,a.nss||'/'||a.nvs xbsl from (");
		sql
				.append("select t.nj,t.xydm,t.xydm bmdm,t.zydm,t.pycc,t.xz,count(t.nj) rs,");
		sql
				.append("t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz pk,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xb='��' and n.tjzt='0') nss,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xb='Ů' and n.tjzt='0') nvs,");
		sql.append(" (select zymc");
		sql.append(" from BKS_ZYDM a");
		sql.append(" where a.zydm = t.zydm");
		sql.append(" and rownum = 1) zymc,");
		sql.append(" (select bmmc");
		sql.append("  from ZXBZ_XXBMDM a");
		sql.append("  where a.bmdm = t.xydm");
		sql.append("  and rownum = 1) xy");
		sql.append("  from XG_XSXX_FBGL_XSXX_LSB t");
		sql.append("  where t.bjdm is null and t.tjzt='0'");
		sql.append(" group by t.nj, t.xydm, t.zydm, t.PYCC, t.xz");
		sql.append(" order by t.nj ) a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		sql
				.append(" and pk not in(select b.nj || '-' || b.bmdm || '-' || b.zydm || '-' || b.PYCC || '-' || b.xz pk from XG_XSXX_FBBXHGL_BJDM_LSB b where b.sfytj='0')");
		sql.append(" ");
		sql.append(searchTj);
		sql.append("   order by nj,xy,zydm desc");
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: ɾ����Ӧ�ְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����09:47:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 *            void ��������
	 */
	public void delAllFbxx(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("delete xg_xsxx_fbbxhgl_bjdm_lsb b where b.nj||'-'||b.bmdm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz=?");
		try {
			dao.runDelete(sb.toString(), new String[] { pk });
		} catch (Exception e) {
			throw new RuntimeException("ɾ���ְ���Ϣ����" + e.getMessage());
		}
	}

	/**
	 * 
	 * @����:ɾ�����зְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����10:03:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 */
	public int delFbxx() {
		StringBuffer sb = new StringBuffer();
		sb
				.append("update XG_XSXX_FBGL_XSXX_LSB b set bjdm='',bjmc='',fbgz='' where bjdm is not null");
		try {
			return dao.update(sb.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("ɾ���ְ���Ϣʧ�ܣ�");
		}
	}

	/**
	 * 
	 * @����: �ѱ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-3 ����03:10:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForYbb(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*,t1.pyccmc,(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.pk= b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz) rs ");
		sql
				.append(",(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.BJDM =b.bjdm) xss ");
		sql.append(" from VIEW_FBGL_YBBXX a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		sql.append("   order by nj,xy,zydm,bjdm desc");
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: δ��ѧ���б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-11 ����10:14:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForBxh(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		/*
		 * String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
		 * "xydm", "bjdm");
		 */
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*,t1.pyccmc,a.wfb||'/'||a.yfb fbqk,a.wbxh||'/'||a.ybxh bxhqk from (");
		// �ְ����
		sql
				.append("select t.*,(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and bjdm is null and n.tjzt='0') wfb,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and bjdm is not null and n.tjzt='0') yfb");

		// ��ѧ�����
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is not null and n.tjzt='0') ybxh,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is null and n.tjzt='0') wbxh");
		sql.append(" from VIEW_FBGL_BBGL t) a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		// sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: �ѱ�ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-11 ����10:14:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForYbxh(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql
				.append("select t.*,t1.pyccmc,t0.pzgzmc bxhgzmc,t1.zymc zymc1,t2.bmmc xy1,");
		sql.append("   (select d.qxmc from dmk_qx d where d.qxdm = substr(t.SYD, 0, 4) || '00' and t.SYD <> substr(t.SYD, 0, 2) || '0000') ||");
		sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.SYD and t.SYD <> substr(t.SYD, 0, 2) || '0000'");
        sql.append("  and t.SYD <> substr(t.SYD, 0, 4) || '00') sydmc,");
		// ��ѧ�����
		sql.append(" (case");
		sql.append(" when t.xh is not null then");
		sql.append(" '0'");
		sql.append(" else");
		sql.append(" '1'");
		sql.append(" end) xhqk,");
		sql.append(" (case");
		sql.append(" when t.xh is not null then");
		sql.append(" 'δ��ѧ��'");
		sql.append(" else");
		sql.append(" '�ѱ�ѧ��'");
		sql.append(" end) xhqkmc,");
		sql.append("t.nj||'" + FbglXsxxDao._NJ_KSH_FGF
				+ "'||t.ksh pk from xg_xsxx_fbgl_xsxx_lsb t");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm  ");
		sql.append(" left join xg_xsxx_fbbxhgl_tjgzpzb t0");
		sql.append(" on t.bxhgz= t0.pzgzid");
		sql.append(" left join BKS_ZYDM t1 on t.zydm=t1.zydm");
		sql.append(" left join ZXBZ_XXBMDM t2 on t.xydm=t2.bmdm)a");
		// sql.append(" where t.xh is not null and t.bjdm is not null");
		sql.append(" where 1=1 and tjzt='0'");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append("order by a.xhqk desc,a.xydm,a.zydm,a.bjdm ");
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: �ְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����03:21:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForFb(FbglBbglForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*,t1.pyccmc,nss||'/'||nvs nvqk,wfb||'/'||yfb fbqk from (");
		sql.append("select t.*");
		// ��Ů���
		sql
				.append(",(select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='��' and n.tjzt='0') nss,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='Ů' and n.tjzt='0') nvs");
		// �ְ����
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is null and n.tjzt='0') wfb,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is not null and n.tjzt='0' ) yfb");
		sql.append(" from VIEW_FBGL_BBGL t ) a");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ȡ�ְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����03:07:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getFbList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from VIEW_FBGL_BBGL");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����: �ѷְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����04:26:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForYfb(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		/*
		 * String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
		 * "xydm", "bjdm");
		 */
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from(");
		sql
				.append("select t.XH,t.BJMC,t.BJDM,t.ZYDM,t.tjzt,t.XYDM bmdm,t.BZ,t.XM,t.XMPY,t.NJ,t.SYD,t.CSRQ,t.SFZH,t.XB,t.MZ,t.ZZMM,t.LXDH,t.DZYX,t.CYM,t.SG,t.TZ,t.TC,t.KSLB,t.RXFS,t.PYFS,t.PYCC,t.XJLB,t.XSZP,t.XJZTM,t.XZ,t.WHCD,t.RXQDW,t.JTDH,t.JRGQTSJ,t.JRGCDSJ,t.JTCYGC,t.JLCFJL,t.JKZK,t.JTDZ,t.JTYB,t.JG,t.XX,t.AH,t.SFDK,t.SHGXXM1,t.SHGXGX1,t.SHGXGZDW1,t.SHGXZW1,t.SHGXDWDH1,t.SHGXSJHM1,t.SHGXXM2,t.SHGXGX2,t.SHGXGZDW2,t.SHGXZW2,t.SHGXDWDH2,t.SHGXSJHM2,t.JTQKJJ,t.JTJJQK,t.SJHM,t.BYXX,t.KH,t.RXRQ,t.FDYXM,t.GKCJ,t.QQHM,t.HKXZ,t.ZYJB,t.HKSZD,t.SSYQ,t.SSLD,t.JTDZS,t.JTDZX,t.SFZSB,t.SFZFX,t.ZJDM,t.SFYBY,t.BYNY,t.SFZX,t.ZW,t.THBS,t.DYBJ,t.SHBJ,t.XWZSXLH,t.XWZSBH,t.XW,t.XZXM,t.ZSXLH,t.ZSBH,t.BJYJL,t.CSD,t.ZSJJ,t.XXXS,t.BXLX,t.BXXS,t.FXZYFX,t.FXZY,t.ZYLB,t.DQSZJ,t.PYFX,t.ZYFX,t.XXSZD,t.KSH,t.XXFX,t.ZSLB,t.GJ,t.SFJH,t.CCQJ,t.BYZFFZTDM,t.XWZSXXDZ,t.JGS,t.JGSHI,t.JGX,t.SSBH,t.RXNJ,t.NFBY,t.SFZC,t.DASFYL,t.DAYLYY,t.YXDM,t.SFZZ,t.SFSF,t.SFDL,t.DXHWP,t.BYSJ,t.ZXWYYZDM,t.WYDJ,t.JSJDJ,t.LXDZ,t.YZBM,t.SHZW,t.JYPX,t.XMSJ,t.ZGZS,t.JLJN,t.SYBZ1,t.SYBZ2,t.SYBZ3,t.XLDM,t.ZKZH,t.GRJL,t.SFCJ,t.SSCH,t.RZRQ,t.ZSJZRQ,t.QSDH,t.YKTH,t.YHKH,t.XSLB,t.XSLX,t.SFBYS,t.YHDM,t.HKSHEN,t.HKSHI,t.HKXIAN,t.ZCSXHM,t.RXQWHCD,t.XSQRXXBZ,t.DAH,t.YLBXH,t.RXQDWDZ,t.RXQDWYB,t.RXQDWDH,t.GZBX,t.SFGAT,t.SFSSMZ,t.SFZD,t.SYDS,t.SYDSHI,t.SYDX,t.BYZH,t.XJH,t.JRZZMMRQ,t.SFHQ,t.CSDS,t.CSDSHI,t.CSDXIAN,t.ZD1,t.ZD2,t.ZD3,t.ZD4,t.ZD5,t.XJLBDM,t.BXHGZ,t.FBGZ,t.TDCJ,t.LSH,t.PZGZID,t1.pyccmc,t0.pzgzmc fbgzmc,t0.gzdm gzlx,");
		 sql.append("(select c.qxmc from dmk_qx c where c.qxdm = substr(t.jg, 0, 2) || '0000') ||");
			sql.append("   (select d.qxmc from dmk_qx d where d.qxdm = substr(t.SYD, 0, 4) || '00' and t.SYD <> substr(t.SYD, 0, 2) || '0000') ||");
			sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.SYD and t.SYD <> substr(t.SYD, 0, 2) || '0000'");
	        sql.append("  and t.SYD <> substr(t.SYD, 0, 4) || '00') sydmc,");
		sql.append("(case");
		sql.append("	when t.bjdm is not null then");
		sql.append(" '1'");
		sql.append(" else");
		sql.append(" '0'");
		sql.append("	end) fbqk,");
		sql.append(" (case");
		sql.append(" when t.bjdm is not null then");
		sql.append(" '�ѱ��'");
		sql.append(" else");
		sql.append(" 'δ���'");
		sql.append(" end) fbqkmc,");
		sql.append(" (case");
		sql.append(" when t.bjdm is not null then");
		sql.append(" '��'");
		sql.append(" else");
		sql.append(" '��'");
		sql.append(" end) sfyfb,");
		sql.append(" (select zymc");
		sql.append(" from BKS_ZYDM a");
		sql.append(" where a.zydm = t.zydm");
		sql.append(" and rownum = 1) zymc,");
		sql.append(" (select bmmc");
		sql.append(" from ZXBZ_XXBMDM a");
		sql.append(" where a.bmdm = t.xydm");
		sql.append(" and rownum = 1) bmmc,");
		sql.append("t.nj||'" + FbglXsxxDao._NJ_KSH_FGF
				+ "'||t.ksh pk from XG_XSXX_FBGL_XSXX_LSB t");
		sql.append(" left join XG_XSXX_FBBXHGL_TJGZPZB t0");
		sql.append(" on t.fbgz = t0.pzgzid ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm  ");

		// sql.append(" where bjdm is not null");
		sql.append(" order by t.fbgz,t.xydm,t.zydm,t.bjdm ) where 1=1 and tjzt='0' ");
		// sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: ��ȡ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����10:22:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBbxx(FbglBbglForm t) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select t0.*, t1.*, t2.*,t3.pyccmc from VIEW_FBGL_BBGL t0 ");
			sql.append(" left join  ZXBZ_XXBMDM t1");
			sql.append(" on t0.xydm = t1.bmdm");
			sql.append(" left join bks_zydm t2 ");
			sql.append(" on t1.bmdm = t2.zydm left join xg_xsxx_pyccdmb t3 on t0.pycc=t3.pyccdm where 1=1 and t0.pk not in(select pk from VIEW_FBGL_YBBXX)");
			// �����Ϊ�գ���ȡ����pk������
			if (StringUtils.isNotNull(t.getPk())) {
				sql.append("  and t0.pk in(");
				String[] ids = t.getPk().split(",");
				int i = 0;
				for (String id : ids) {
					sql.append("'");
					sql.append(id);
					sql.append("'");
					if (i + 1 < ids.length) {
						sql.append(",");
					}
					i++;
				}
				sql.append(")");
			}
			
			return dao.getListNotOut(sql.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("��ȡ����б����");
		}
	}

	/**
	 * 
	 * @����: �@ȡ���������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-26 ����05:28:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return HashMap<String,String> ��������
	 */
	public HashMap<String, String> getBbxx(String pk) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from view_fbgl_bbgl where pk=?");
		return dao.getMapNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @����: ��ȡ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����10:23:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getTbxx(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select a.*,t1.pyccmc,(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.pk= b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz) rs");
		sb.append("  from view_fbgl_bbgl a ");
		sb.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sb.append("  where pk=?");
		return dao.getMapNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @����: ��ȡ����������༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����10:29:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTbJtBj(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select a.*,(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.bjdm=b.bjdm) xss");
		sb
				.append(" from view_fbgl_ybbxx a where a.pk=? order by to_number(a.bjbh)");
		return dao.getListNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @����: pk��Ӧ�İ༶����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����05:27:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return String ��������
	 */
	public List<HashMap<String, String>> getSelectZy(String[] pk) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from (");
		sb.append("select distinct zydm from view_fbgl_bbgl where pk in(");
		for (String str : pk) {
			sb.append("'");
			sb.append(str);
			sb.append("',");
		}
		sb.append("'-1'))");
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 
	 * @����: ��pk�µ����а༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����03:10:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPkBj(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from XG_XSXX_FBBXHGL_BJDM_LSB t where t.nj || '-' || t.bmdm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz=? order by to_number(t.bjbh) asc");
		return dao.getListNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @����: ��ȡĳ�꼶רҵ�����а༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����03:10:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPkBj(String pk, String nj) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from XG_XSXX_FBBXHGL_BJDM_LSB t where  t.nj || '-' || t.bmdm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz=? and nj=? order by to_number(bjbh) asc");
		return dao.getListNotOut(sb.toString(), new String[] { pk, nj });
	}

	/**
	 * 
	 * @����: ��ȡ��רҵ���꼶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����11:48:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getNj() {
		StringBuffer sb = new StringBuffer();
		sb.append("select  distinct nj  from XG_XSXX_FBBXHGL_BJDM_LSB");
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 
	 * @����: ��ȡ��ǰ�༶�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����03:09:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getDqBjList(FbglBbglForm myForm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select bjdm,bjmc from XG_XSXX_FBBXHGL_BJDM_LSB where zydm=?");
		return dao.getListNotOut(sb.toString(), new String[] {myForm.getZydm()});
	}

	/**
	 * 
	 * @����: ��ȡ�ְ���Ϣ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����05:41:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sfyfb
	 *            true ��ȡ�ѷְ�������false��ȡδ�ְ�����
	 * @param pk
	 *            ѡ�е�����key����
	 * @return String ��������
	 */
	public String getFbxx(boolean sfyfb, String[] pk) {
		StringBuffer sb = new StringBuffer();
		// ԭרҵ
		/*
		 * sb.append("select b.*from (");
		 * sb.append("select zydm from view_fbgl_bbgl where pk in("); for
		 * (String str : pk) { sb.append("'"); sb.append(str); sb.append("',");
		 * } sb.append("'-1')group by zydm)a"); sb.append(
		 * " left join XG_XSXX_FBGL_XSXX_LSB b on a.zydm=b.zydm where b.bjdm");
		 */
		sb.append("select * from (");
		sb
				.append("select t.*,t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz pk");
		sb.append(" from XG_XSXX_FBGL_XSXX_LSB t)");
		sb.append(" where pk in (");
		for (String str : pk) {
			sb.append("'");
			sb.append(str);
			sb.append("',");
		}
		sb.append("'-1')");
		sb.append(" and bjdm ");
		sb.append(sfyfb ? " is not null" : " is null");
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] {});
		return null == list || list.size() <= 0 ? "0" : String.valueOf(list
				.size());
	}

	/**
	 * 
	 * @����: ���ݹ����ȡ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-1 ����10:00:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBbxxForPzgz(String pzgzid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_XSXX_FBBXHGL_BJDM_LSB where pzgzid=?");
		return dao.getListNotOut(sb.toString(), new String[] { pzgzid });
	}

	@Override
	protected void setTableInfo() {
		this.setKey("bjdm");
		this.setTableName("xg_xsxx_fbbxhgl_bjdm_lsb");
		this.setClass(FbglBbglForm.class);
	}
}
