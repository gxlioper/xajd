/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-25 ����09:33:48 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbjg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-25 ����09:33:48
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglFbjgDao extends FbglXsxxDao {
	@Override
	public List<HashMap<String, String>> getPageList(FbglXsxxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
	//	String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
	//			"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql
				.append("select a.*,t1.pyccmc,nss||'/'||nvs nvqk,wfb||'/'||yfb fbqk,wbxh||'/'||ybxh bxhqk from (");
		sql.append("select t.* ");
		// ��Ů���
		sql
				.append(",(select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='��' and tjzt='0') nss,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='Ů' and tjzt='0') nvs");
		// �ְ����
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is null and tjzt='0') wfb,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is not null and tjzt='0') yfb");
		// ��ѧ�����
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is not null and tjzt='0') ybxh,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is null and tjzt='0') wbxh,");
		sql
				.append(" (select count(*) from XG_XSXX_FBBXHGL_BJDM_LSB bj where bj.nj||'_'||bj.zydm||'_'||bj.pycc||'_'||bj.xz=t.nj||'_'||t.zydm||'_'||t.pycc||'_'||t.xz) bjs");
		sql.append(" from VIEW_FBGL_BBGL t");
		sql.append(" )a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" ) where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: ��ȡ���ύ�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-31 ����09:13:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListYtj(FbglXsxxForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
		//		"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql
				.append("select t.*,t1.pyccmc,nss||'/'||nvs nvqk from view_fbgl_xsxx_bak t ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����: ���ύ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-14 ����05:02:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return int ��������
	 */
	public int getWtj(String nj) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append("XG_XSXX_FBGL_XSXX_LSB");
		sql.append(" where nj=?");
		List<HashMap<String, String>> list = dao.getListNotOut(sql.toString(),
				new String[] { nj });
		return null == list ? 0 : list.size();
	}

	/**
	 * 
	 * @����: δ�ύ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-14 ����05:02:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getYtj(String nj) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append("XG_XSXX_FBGL_XSXX_LSB_BAK");
		sql.append(" where nj=?");
		List<HashMap<String, String>> list = dao.getListNotOut(sql.toString(),
				new String[] { nj });
		return null == list ? 0 : list.size();
	}

	/**
	 * 
	 * @����: ��ȡ�꼶list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-31 ����09:13:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getNjList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select nj from XG_XSXX_FBGL_XSXX_LSB group by nj");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����: ��ȡ���ύ�꼶�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-31 ����09:13:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getYtjNjList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select nj from Xg_Xsxx_Fbgl_Xsxx_Lsb_Bak group by nj");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����: �����꼶��ȡ��ʱ��ѧ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:00:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXsxxForNj(String nj) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct a.xh,a.ksh,a.sfzx,a.tdcj,a.nj,a.xm,a.xb,b.xymc,b.zymc,a.bjdm,a.bjmc,a.xydm,a.zydm,");
		sql.append("a.syd,a.zzmm,a.sfzh,a.rxrq,a.jg,a.hkszd,a.sjhm,a.qqhm,");
		sql.append("a.pycc,a.xz,a.zjdm,a.mz,a.csrq from XG_XSXX_FBGL_XSXX_LSB a left join view_njxyzybj_all b on a.zydm=b.zydm ");
		sql.append(" where a.bjdm is not null and a.xh is not null and a.nj=? and a.tjzt='0'");
		return dao.getListNotOut(sql.toString(), new String[] { nj });
	}

	/**
	 * 
	 * @����: ��ȡ��Ӧ�꼶��ʱ������ѧ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-31 ����09:14:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getLsbXsxxInsertForNj(String nj) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_XSXX_FBGL_XSXX_LSB_BAK where nj=?");
		return dao.getListNotOut(sql.toString(), new String[] { nj });
	}

	/**
	 * 
	 * @����: �����꼶��ȡ��ʱ��༶��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:00:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nj
	 * @param sfytj
	 *            </br>��ǲ�ѯ FbglBbglService._SFYTJ_YTJ ���ύ
	 *            </br>FbglBbglService._SFYTJ_WTJ δ�ύ </br> null ��ѯ����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBjxxForNj(String nj, String sfytj) {
		List<String> param = new ArrayList<String>();
		param.add(nj);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*");
		sql
				.append(",(select zymc from view_njxyzybj_all a where a.zydm = t.zydm and rownum = 1) zymc ");
		sql
				.append(",(select xymc from view_njxyzybj_all a where a.xydm = t.bmdm and rownum = 1) xymc ");
		sql
				.append(" from XG_XSXX_FBBXHGL_BJDM_LSB t where t.nj=? and bjdm is not null");
		if (StringUtils.isNotNull(sfytj)) {
			sql.append(" and t.sfytj=?");
			param.add(sfytj);
		}
		return dao.getListNotOut(sql.toString(), param.toArray(new String[] {}));
	}

	/**
	 * 
	 * @����: ��ȡ��ʱ��ĳ�꼶�ύ�İ༶����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:11:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @deprecated ������getBjxxForNj���������ֱ�ӴӰ༶��ʱ���ȡ��
	 * @param nj
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getLsbInsertBjxxForNj(String nj) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct bjdm from XG_XSXX_FBGL_XSXX_LSB_BAK");
		sql.append(" where nj=?");
		return dao.getListNotOut(sql.toString(), new String[] { nj });
	}

	/**
	 * 
	 * @����: ��ȡ�༶��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����02:00:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBjxx(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from bks_bjdm where bjdm=?");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}

	/**
	 * 
	 * @����: �ύ�༶��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����03:10:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hm
	 * @return boolean ��������
	 */
	public boolean saveBjxx(HashMap<String, String> hm) {
		// insert into bks_bjdm (BJDM, JZGH, ZYDM, BMDM, BJMC, NJ, YWXTBH,
		// ZHGXSJ, BJJC, ZCRS)
		// values ('304181102', null, '1811', null, '�ٴ�ҽѧ�������ƣ�һϵ0402', 2004,
		// null, null, null, null);
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into bks_bjdm (BJDM, JZGH, ZYDM, BMDM, BJMC, NJ, YWXTBH, ZHGXSJ, BJJC, ZCRS) values (?,?,?,?,?,?,?,?,?,?)");
		try {
			return dao.insert(sql.toString(), new String[] { hm.get("bjdm"),
					null, hm.get("zydm"), null, hm.get("bjmc"), hm.get("nj"),
					null, null, null, null });
		} catch (Exception e) {
			throw new RuntimeException("����༶��Ϣʧ��!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @����: ɾ���༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-26 ����04:00:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hm
	 * @return boolean ��������
	 */
	public boolean deleteBjxx(HashMap<String, String> hm) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete bks_bjdm where bjdm=?");
		try {
			return dao.runDelete(sql.toString(),
					new String[] { hm.get("bjdm") }) == 1;
		} catch (Exception e) {
			throw new RuntimeException("ɾ���༶��Ϣʧ��!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @����: ɾ��ѧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-31 ����09:35:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return int ��������
	 */
	public int deleteXsmm(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete xsmmb where xh=?");
		try {
			return dao.runDelete(sql.toString(), new String[] { xh });
		} catch (Exception e) {
			throw new RuntimeException("ɾ��ѧ������ʧ��!" + e.getMessage());
		}
	}
}
