/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-7 ����10:19:15 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.bysxx;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.sql.SQL_Util;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-7 ����10:19:15
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxDao extends SuperDAOImpl<BysXxForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BysXxForm t)
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
	public List<HashMap<String, String>> getPageList(BysXxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from ( ");
		sql
				.append("select a.*,b.XJLBDM,b.ZZMM,b.xz,b.mz,b.JTDZ,b.JGMC,b.sydmc syd,b.hkszdmc hkszd,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.byny,b.csrq,b.sfzh,b.bjdm,b.bjmc,b.pycc,(select pyccmc from xg_xsxx_pyccdmb c where b.pycc=c.pyccdm) pyccmc from XG_BYSXX_BYSXXB a ");
		sql.append("left join view_xsxxb b on b.xh=a.xh left join xsxxb c on c.xh=a.xh where a.bynd in(select bynd from XG_BYSXX_XXXGCSSZB) ");
//		sql.append("left join view_xsxxb b on b.xh=a.xh left join xsxxb c on c.xh=a.xh  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(") a where 1=1 ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ȡѧ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-7 ����02:01:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(BysXxForm model, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder(
				"select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where a.xh not in(select xh from XG_BYSXX_BYSXXB)");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:�����ҵ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����09:37:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean save(List<String[]> params) throws Exception{
		boolean flag = false;
		if(params == null || params.size() == 0 || params.get(0)== null || params.get(0).length == 0){
			return false;
	    }
	    StringBuilder sql = new StringBuilder();
	    int len = params.get(0).length;
	    sql.append("insert into XG_BYSXX_BYSXXB values(");
        for(int i = 0;i <len;i++ ){
        	sql.append("?");
        	if(i != len-1){
        		sql.append(" ,");
        	}
        }
    	sql.append(" )");
		int[] result = dao.runBatch(sql.toString(), params);
		flag = dao.checkBatchResult(result);
		return flag;
		
	}
	/**
	 * 
	 * @����:�޸ı�ҵ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����10:08:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param valueMap
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateBysInfo(HashMap<String, String> valueMap)
			throws Exception {
		// ����SQL
		StringBuilder sql = new StringBuilder();
		// �ֶ���
		StringBuilder comments = new StringBuilder();
		// �޸�����
		StringBuilder query = new StringBuilder();
		// �޸��ֶ���ֵ
		List<String> inputV = new ArrayList<String>();
		// �޸���������ֵ
		List<String> queryV = new ArrayList<String>();
		// �����ֶ�
		String pk = "xh";
		sql.append(" update XG_BYSXX_BYSXXB set ");
		int n = 0;
		boolean flag = false;
		if (valueMap.get(pk) != null) {
			for (Map.Entry<String, String> entry : valueMap.entrySet()) {
				// ��(�ֶ���)
				String paramName = entry.getKey();
				// ֵ(�ֶ�ֵ)
				String paramValue = entry.getValue();
				// ------------------����ƴ�� begin --------------------
				if (pk.equalsIgnoreCase(paramName)) {
					// ------------�������� begin------------
					query.append(" and ");
					query.append(pk);
					query.append("=?");
					// ------------�������� end------------
					queryV.add(paramValue);
				} else {
					if (n != 0) {
						comments.append(",");
					}
					// -------�޸���Ϣ begin----------
					comments.append(paramName);
					comments.append("=?");
					// -------�޸���Ϣ end ----------
					inputV.add(paramValue);
					n++;
					// --------------�޸�ֵ------------------
				}
				// ---------....�����ֶ��ǲ���Ҫ�޸ĵ� end----------
			}
			// ������ֶ�
			sql.append(comments);
			sql.append(" where 1=1  ");
			sql.append(query);
			inputV.addAll(queryV);
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		}

		return flag;
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ż�ȡ��ҵ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����10:36:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBysXx(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append("d.*,a.ah,a.bjdm,a.bjyjl,a.bxlx,a.bxxs,a.byny,a.bysj,a.byxx,a.byzffztdm,a.byzh,a.bz,a.ccqj,a.csd,a.csds,a.csdshi,a.csdxian,a.csrq,a.cym,a.dah,a.dasfyl,a.daylyy,a.dqszj,a.dxhwp,a.dybj,a.dzyx,a.fdyxm,a.fxzy,a.fxzyfx,a.gj,a.gkcj,a.grjl,a.gzbx,a.hkshen,a.hkshi,a.hkszd,a.hkxian,a.hkxz,a.jg,a.jgs,a.jgshi,a.jgx,a.jlcfjl,a.jljn,a.jrgcdsj,a.jrgqtsj,a.jrzzmmrq,a.jsjdj,a.jtcygc,a.jtdh,a.jtdz,a.jtdzs,a.jtdzx,a.jtjjqk,a.jtqkjj,a.jtyb,a.jypx,a.kh,a.ksh,a.kslb,a.lxdh,a.lxdz,a.mz,a.nfby,a.nj,a.pycc,a.pyfs,a.pyfx,a.qqhm,a.qsdh,a.rxfs,a.rxnj,a.rxqdw,a.rxqdwdh,a.rxqdwdz,a.rxqdwyb,a.rxqwhcd,a.rxrq,a.rzrq,a.sfbys,a.sfcj,a.sfdk,a.sfdl,a.sfgat,a.sfhq,a.sfjh,a.sfsf,a.sfssmz,a.sfyby,a.sfzc,a.sfzd,a.sfzfx,a.sfzh,a.sfzsb,a.sfzx,a.sfzz,a.sg,a.shbj,a.shgxdwdh1,a.shgxdwdh2,a.shgxgx1,a.shgxgx2,a.shgxgzdw1,a.shgxgzdw2,a.shgxsjhm1,a.shgxsjhm2,a.shgxxm1,a.shgxxm2,a.shgxzw1,a.shgxzw2,a.shzw,a.sjhm,a.ssbh,a.ssch,a.ssld,a.ssyq,a.sybz1,a.sybz2,a.sybz3,a.syd,a.syds,a.sydshi,a.sydx,a.tc,a.thbs,a.tz,a.whcd,a.wydj,a.xh,a.xjh,a.xjlb,a.xjlbdm,a.xjztm,a.xldm,a.xm,a.xmpy,a.xmsj,a.xslb,a.xslx,a.xsqrxxbz,a.xszp,a.xw,a.xwzsbh,a.xwzsxlh,a.xwzsxxdz,a.xx,a.xxfx,a.xxszd,a.xxxs,a.xz,a.xzxm,a.yhdm,a.yhkh,a.ykth,a.ylbxh,a.yxdm,a.yzbm,a.zcsxhm,a.zd1,a.zd2,a.zd3,a.zd4,a.zd5,a.zgzs,a.zjdm,a.zkzh,a.zsbh,a.zsjj,a.zsjzrq,a.zslb,a.zsxlh,a.zw,a.zxwyyzdm,a.zyfx,a.zyjb,a.zylb,a.tbsj,a.bxxz,a.sftb,a.sfyqrzs,a.qtyy,a.sfsfs,a.zzmm");
		sb.append(",b.lxdh1,b.lxdh2,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_gzdz,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdz,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_gzdz,b.jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2,b.email,b.jtszd");
		// ========= ���� < =========
		sb.append(" ,(select c.qxmc ");
		sb.append(" from dmk_qx c ");
		sb.append(" where c.qxdm = substr(a.jg, 0, 2) || '0000') || ");
		sb.append(" (select d.qxmc ");
		sb.append(" from dmk_qx d ");
		sb.append(" where d.qxdm = substr(a.jg, 0, 4) || '00' ");
		sb.append(" and a.jg <> substr(a.jg, 0, 2) || '0000') || ");
		sb.append(" (select e.qxmc ");
		sb.append(" from dmk_qx e ");
		sb.append(" where e.qxdm = a.jg ");
		sb.append(" and a.jg <> substr(a.jg, 0, 2) || '0000' ");
		sb.append(" and a.jg <> substr(a.jg, 0, 4) || '00') jgmc ");
		// ========= ���� > =========
		// ========= ���� < =========
		sb.append(",(select mzmc from mzdmb where a.mz = mzdm) mzmc ");
		// ========= ���� > =========
		// ========= �Ա� < =========
		sb.append(",(case a.xb when '1' then '��' when '2' then 'Ů' else a.xb end) xb ");
		// ========= �Ա� > =========
		// ========= ����״�� < =========
		sb.append(",a.jkzk jkzkdm, (select mc from xg_xsxx_jkztb s where a.jkzk=s.dm) jkzk ");
		// ========= ����״�� > =========
		sb.append(",c.xymc xymc,c.xymc xy,c.zymc,c.bjmc,c.xydm,c.zydm,z8.mc,");
		sb.append(" z1.xwmlmc,");
		sb.append(" z2.gdlxmc,");
		sb.append(" z3.zyxwlbmc,");
		sb.append(" z4.zyxwlymc,");
		sb.append(" z5.jsxymc,");
		sb.append(" z6.flfxmc,");
		sb.append(" z7.xxfsmc");
		sb.append(" from XG_BYSXX_BYSXXB d ");
		sb.append(" left join xsxxb a ");
		sb.append("  on a.xh=d.xh ");
		sb.append(" left join xsfzxxb b ");
		sb.append(" on d.xh = b.xh ");
		sb.append(" left join view_njxyzybj c ");
		sb.append("  on a.bjdm=c.bjdm ");
	    sb.append(" left join ZXBZ_XWML z1");
	    sb.append(" on d.xwml = z1.xwmldm");
	    sb.append(" left join ZXBZ_GDLX z2");
	    sb.append(" on d.gdlx = z2.gdlxdm");
	    sb.append(" left join ZXBZ_ZYXWLB z3");
	    sb.append("  on d.zyxwlb = z3.zyxwlbdm");
	    sb.append(" left join ZXBZ_ZYXWLY z4");
	    sb.append(" on d.zyxwly = z4.zyxwlydm");
	    sb.append(" left join ZXBZ_JSXY z5");
	    sb.append(" on d.jsxy = z5.jxxydm");
	    sb.append(" left join ZXBZ_FLFX z6");
	    sb.append(" on d.flfx = z6.flfxdm");
	    sb.append(" left join dmk_xxfs z7");
	    sb.append(" on d.xxfs = z7.xxfsdm");
	    sb.append(" left join dmk_xwb z8");
	    sb.append(" on d.xws = z8.dm");
		sb.append(" where d.xh=? ");
		return dao.getMapNotOut(sb.toString(), new String[] { xh });

	}
	/**
	 * ͨ��ѧ�Ż�ȡ������Ϣ
	 */
	public HashMap<String, String> getSqXx(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_BYSXX_XXXGSQB ");
		sb.append(" where xh=? order by xgsj desc");
		return dao.getMapNotOut(sb.toString(), new String[] { xh });
	}

	/**
	 * 
	 * @����:��ȡ��Ƭ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����02:19:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param column
	 * @return Blob ��������
	 * @throws
	 */
	public Blob getZpMap(BysXxForm myForm, String column) {
		// TODO �Զ����ɷ������
		String sql = "select * from xszpb where xh = ?";
		DAO dao = DAO.getInstance();

		return dao.getOneBlob(sql, new String[] { myForm.getXh() }, column);
	}
	
	@Override
	protected void setTableInfo() {
		this.setKey("xh");
		this.setTableName("XG_BYSXX_BYSXXB");
		this.setClass(BysXxForm.class);
	}
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-20 ����10:43:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param bynd
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertSelect(BysXxForm model, User user, String bynd)
			throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ");
		sql.append("  XG_BYSXX_BYSXXB (xh,bynd) ");
		sql.append(" select t.xh,t.bynd from ");
		sql.append(" (select t.* from ");
		sql.append(" (select a.xh, '" + bynd
				+ "' as bynd,a.nj,a.xm,a.bjdm,a.xb,a.xydm,a.xz,a.zydm from view_xsjbxx a where a.xh not in( ");
		sql.append(" select xh from XG_BYSXX_BYSXXB)) t ");
		sql.append(" where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" ) t "); 

		return dao.runUpdate(sql.toString(), inputV);
	}

}
