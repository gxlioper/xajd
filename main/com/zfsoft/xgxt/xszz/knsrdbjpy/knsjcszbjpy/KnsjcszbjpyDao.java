package com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyForm;


public class KnsjcszbjpyDao extends SuperDAOImpl<KnsjcszbjpyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(KnsjcszbjpyForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = null;
		String rskzfw = model.getRskzfw();
		sql = new StringBuilder("SELECT * FROM (SELECT  M.*,N.BL,N.GUID,N.ZZRS,(CASE WHEN N.ZZRS IS NULL THEN '0' ELSE '1' END) SFYSZ");
		if (rskzfw != null) {
			if (rskzfw.equals(Constants.RSKZFW_BJ)) {// �༶
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC,B.BJMC ");
				sql.append(" FROM (SELECT XYDM,NJ,ZYDM,BJDM,COUNT(*) ZRS ");
				sql.append(" FROM VIEW_XSJBXX  ");
				sql
						.append(" GROUP BY XYDM,NJ,ZYDM,BJDM  HAVING XYDM IS NOT NULL AND NJ IS NOT NULL AND ZYDM IS NOT NULL AND BJDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.BJDM=B.BJDM) M  ");
				sql
						.append(" LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.BJDM=N.BMDM  AND M.NJ=N.NJ");
			}else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶+ѧԺ
						sql
						.append("  FROM (SELECT DISTINCT A.*,B.XYMC FROM (SELECT XYDM,NJ,COUNT(*) ZRS FROM VIEW_XSJBXX  GROUP BY XYDM,NJ  HAVING XYDM IS NOT NULL AND NJ IS NOT NULL) A ");
				sql
						.append("LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.XYDM=B.XYDM ) M   ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.XYDM=N.BMDM AND M.NJ=N.NJ");
		
			} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶 + רҵ
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC ");
				sql.append(" FROM (");
				sql.append(" SELECT XYDM,ZYDM,NJ,COUNT(*) ZRS ");
				sql.append(" FROM VIEW_XSJBXX  ");
				sql
						.append(" GROUP BY XYDM,ZYDM,NJ HAVING XYDM IS NOT NULL AND NJ IS NOT NULL AND ZYDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON  A.ZYDM=B.ZYDM ) M  ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.ZYDM=N.BMDM  AND M.NJ=N.NJ");
			} else if (rskzfw.equals(Constants.RSKZFW_XY)){//ѧԺ
				sql
				.append("  FROM (SELECT DISTINCT A.*,B.XYMC FROM (SELECT XYDM,COUNT(*) ZRS FROM VIEW_XSJBXX ");

				String rskznj = getRskznj();
				if (rskznj != null && !rskznj.trim().equals("")) {
					sql.append(" where NJ in(");
					sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
					sql.append(")");
				}
				sql.append("  GROUP BY XYDM  HAVING XYDM IS NOT NULL) A ");
				sql
						.append("LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.XYDM=B.XYDM ) M   ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.XYDM=N.BMDM ");
			} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC ");
				sql.append(" FROM (");
				sql.append(" SELECT XYDM,ZYDM,COUNT(*) ZRS ");
				sql.append(" FROM VIEW_XSJBXX  ");
				sql
						.append(" GROUP BY XYDM,ZYDM HAVING XYDM IS NOT NULL  AND ZYDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON  A.ZYDM=B.ZYDM ) M  ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.ZYDM=N.BMDM ");
			}
		}
		sql.append(" AND N.XN='");
		sql.append(model.getXn());
		sql.append("' AND N.XQ='");
		sql.append(model.getXq());
		sql.append("'");
		sql.append(")");
		sql.append(" where 1=1 ");
		
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND SFYSZ='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getNjq())){
			sql.append(" AND NJ='"+model.getNjq()+"'");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" AND XYDM='"+model.getXydm()+"'");
		}
		if (!StringUtil.isNull(model.getZydm())){
			sql.append(" AND ZYDM='"+model.getZydm()+"'");
		}
		if (!StringUtil.isNull(model.getBjdm())){
			sql.append(" AND BJDM='"+model.getBjdm()+"'");
		}
		List<HashMap<String, String>> pageList = getPageList(model, sql
				.toString(), params.toArray(new String[] {}));
		return pageList;
	}

	/** 
	 * @����:��ȡ���������꼶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-10 ����11:20:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	private String getRskznj() {
		String sql="select rskznj from xg_xszz_new_knsrd_knsjbszb";
		return dao.getOneRs(sql, new String[]{}, "rskznj");
	}

	@Override
	public List<HashMap<String, String>> getPageList(KnsjcszbjpyForm t, User user)
			throws Exception {
		
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_knsrd_knsjbszb");
		super.setClass(KnsjcszbjpyForm.class);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public KnsjcszbjpyForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, ");
		sql.append(" case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen, ");
		sql.append(" case when bjpykg=1 and sysdate between to_date(nvl(bjpykssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append(" and to_date(nvl(bjpyjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end bjpyisopen ");
		sql.append(" from xg_xszz_new_knsrd_knsjbszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * ɾ������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteJcsz(KnsjcszbjpyForm model) throws Exception {
		boolean flag = false;
		String sql = "delete from xg_xszz_new_knsrd_knsjbszb";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	/**
	 * ֻ���¿���
	 */
	public boolean updateJcszSqkg(KnsjcszbjpyForm model) throws Exception {
		String sql = " update xg_xszz_new_knsrd_knsjbszb set sqkg=?,bjpykg=?,bjpykssj=?,bjpyjssj=?,xzrsxx=? where rownum=1 ";
		return dao.runUpdate(sql, new String[]{model.getSqkg(),model.getBjpykg(),model.getBjpykssj(),model.getBjpyjssj(),model.getXzrsxx()});
	}
	
	/**
	 * 
	 * @����:��ȡ���а���ѧ�����꼶
	 * @���ߣ�cmj
	 * @���ڣ�2013-7-5 ����08:37:45
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = null;
		StringBuilder sql = new StringBuilder();
		sql
				.append("select distinct nj from VIEW_XSJBXX where nj is not null order by nj ");
		String[] inputValue = {};
		result = dao.getList(sql.toString(), inputValue, "nj");
		return result;
	}

	/** 
	 * @����:��������������������
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-12-9 ����06:57:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param allList
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runBlszAll(KnsjcszbjpyForm model,
			List<HashMap<String, String>> list) throws Exception{
		int[] result = null;
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from XG_XSZZ_NEW_KNSRSSZB ");
		dao.runDelete(sb.toString(), new String[]{});// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into XG_XSZZ_NEW_KNSRSSZB(bl,bmdm,nj,xn,xq,zzrs) ");
		sb.append(" values(?,?,?,?,?,?)");
		
		for (HashMap<String, String> map : list) {
			String[] param = { model.getBl(), map.get("bmdm"), map.get("nj"),
					 model.getXn(), model.getXq(),
					map.get("zzrs") };
			paramList.add(param);
		}
		result = dao.runBatch(sb.toString(), paramList);
		return dao.checkBatch(result);
	}

	/** 
	 * @����:���ݹ�ѡ��¼���ö�Ӧ����
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-12-10 ����09:07:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runBlsz(KnsjcszbjpyForm model, List<KnsjcszbjpyForm> list) throws Exception{
		int[] result = null;

		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = null;
		String guid = null;
		sb = new StringBuffer();
		for (KnsjcszbjpyForm model1 : list) {
			guid = model1.getGuid();
			if (guid != null && !guid.trim().equals("")
					&& !guid.trim().equals("null")) {
				sb = new StringBuffer();
				sb.append("update XG_XSZZ_NEW_KNSRSSZB set bl='");
				sb.append(model.getBl());
				sb.append("',zzrs=");
				sb.append(model1.getZzrs());
				sb.append(" where guid='");
				sb.append(guid);
				sb.append("'");
				sqlList.add(sb.toString());
			} else {
				sb = new StringBuffer();
				sb
						.append("insert into XG_XSZZ_NEW_KNSRSSZB(bl,bmdm,nj,xn,xq,zzrs) ");
				sb.append(" values('");
				sb.append(model.getBl());
				sb.append("','");
				sb.append(model1.getBmdm());
				sb.append("','");
				sb.append(model1.getNj());
				sb.append("','");
				sb.append(model.getXn());
				sb.append("','");
				sb.append(model.getXq());
				sb.append("',");
				sb.append(model1.getZzrs());
				sb.append(")");
				sqlList.add(sb.toString());
			}
		}
		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}

		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @����:��ȡ������
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-29 ����10:55:23
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception int ��������
	 * @throws
	 */
	public int getZrs(KnsjcszbjpyForm model, String rskznj) throws Exception {
		String rskzfw = model.getRskzfw();

		int num = 0;
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(*) num from view_xsjbxx where xydm is not null ");
		if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql.append(" and NJ in(");
				sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
				sql.append(")");
			}
		}

		String[] input = {};
		String[] output = { "num" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				try {
					num = Integer.parseInt(oneRs[0]);
				} catch (Exception e) {
				}
			}
		}
		return num;
	}
	
	/**
	 * @����:��ʽ��rskznj������''���Ա�ƴ��sql���
	 * @���ߣ�cmj
	 * @���ڣ�2013-7-5 ����09:26:19
	 * @�޸ļ�¼:
	 * @param rskznj
	 * @return String ��������
	 * @throws
	 */
	private String setRskznj(String rskznj) {
		if (rskznj != null) {
			rskznj = rskznj.replaceAll(",", "','");
			rskznj = "'" + rskznj + "'";
		}
		return rskznj;
	}

	/** 
	 * @����:�����������ò���
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-12-10 ����11:02:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @param rskznj
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean setRsszcs(String zme, String rskznj, KnsjcszbjpyForm myForm) throws Exception{
		String sql=" update xg_xszz_new_knsrd_knsjbszb set zme=?,rskznj=?,rskzfw=?";
		
		return dao.runUpdate(sql, new String[]{zme,rskznj,myForm.getRskzfw()});
	}

	/** 
	 * @����:����ѧ�ţ���ȡ��Ӧ��λ����������������Ϣ
	 * @���ߣ�������
	 * @���ڣ�2013-12-10 ����04:39:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rskzfw
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getRsszOne(String rskzfw, String xh, KnsrdbjpyForm knsrdbjpyForm) {
		StringBuilder sb = new StringBuilder();
		sb
				.append(" select a.zzrs,a.xn,a.xq  from xg_xszz_new_knsrsszb a,view_xsjbxx b  ");
		sb.append(" where 1=1  ");
		if (rskzfw.equals(Constants.RSKZFW_BJ)) {// �༶
			sb.append("  and a.bmdm=b.bjdm ");
		} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶רҵ
			sb.append("  and a.bmdm=b.zydm ");
			sb.append(" and a.nj=b.nj ");
		} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶ѧԺ
			sb.append("  and a.bmdm=b.xydm ");
			sb.append(" and a.nj=b.nj ");
		} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
			sb.append("  and a.bmdm=b.xydm ");
		} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
			sb.append("  and a.bmdm=b.zydm ");
		}
		sb.append("  and b.xh=? ");
		sb.append(" and a.xn=? ");
		sb.append(" and a.xq=? ");
		String[] inputValue = new String[] {  xh,knsrdbjpyForm.getXn(),knsrdbjpyForm.getXq() };
		String[] outputValue = new String[] { "zzrs","xn","xq" };
		return dao.getMap(sb.toString(), inputValue, outputValue);
	}

	/** 
	 * @����:�޸��������������÷�Χ
	 * @���ߣ�������
	 * @���ڣ�2013-12-11 ����01:40:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateRskzfw(KnsjcszbjpyForm myForm) throws Exception{
		String sql="update xg_xszz_new_knsrd_knsjbszb set rskzfw=?";
		
		return dao.runUpdate(sql, new String[]{myForm.getRskzfw()});
	}

	/** 
	 * @����:ɾ����������
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-11 ����02:21:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteRssz() throws Exception{
		String sql="delete xg_xszz_new_knsrsszb";
		return dao.runDelete(sql, new String[]{})>0;
	}

	/** 
	 * @����:��ȡ����������
	 * @���ߣ�������
	 * @���ڣ�2013-12-11 ����02:58:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getYszrs(KnsjcszbjpyForm myForm) {
		String sql="select sum(zzrs) num from XG_XSZZ_NEW_KNSRSSZB where xn=? and xq=?";
		return dao.getMapNotOut(sql, new String[]{myForm.getXn(),myForm.getXq()}).get("num");
	}

	/** 
	 * @����:������������
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-11 ����04:11:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runZzrs(KnsjcszbjpyForm model) throws Exception{
		int[] result = null;

		StringBuffer sb = null;
		List<String> sqlList = new ArrayList<String>();
		String guid = null;
		String zzrs = null;
		String bl = null;
		String nj = "";
		String bmdm = null;
		String rskzfw = null;

		String[] guids = model.getGuids();
		String[] zzrss = model.getZzrss();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] bls = model.getBls();

		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				guid = guids[i];
				if (!StringUtil.isNull(guid)) {
					sqlList
							.add("delete from XG_XSZZ_NEW_KNSRSSZB where guid='"
									+ guid + "'");
				}
			}
		}
		if (zzrss != null && zzrss.length > 0) {
			for (int i = 0; i < zzrss.length; i++) {
				zzrs = zzrss[i];
				if (zzrs != null) {
					zzrs = zzrs.trim();
				}
				int iZzrs = 0;
				try {
					iZzrs = Integer.parseInt(zzrs);
				} catch (Exception e) {
				}
				bl = bls[i];
				if (njs != null) {
					nj = njs[i];
				}
				rskzfw = model.getRskzfw();
				if (rskzfw != null) {
					if (rskzfw.equals(Constants.RSKZFW_BJ)) {
						bmdm = bjdms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
						bmdm = zydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_XY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {
						bmdm = zydms[i];
					}
				}
				sb = new StringBuffer();
				sb
						.append("insert into XG_XSZZ_NEW_KNSRSSZB(bl,bmdm,nj,xn,xq,zzrs) ");
				sb.append(" values('" + bl + "','" + bmdm + "','" + nj + "'");
				sb.append(",'" + model.getXn()+ "','" + model.getXq() + "'," + iZzrs + ")");
				sqlList.add(sb.toString());
			}
		}

		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}
		return dao.checkBatch(result);
	}

	/** 
	 * @����:��ȡ�����������ĵ�λ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-11 ����04:50:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getYszrsNum(KnsjcszbjpyForm myForm) {
		StringBuilder sql=new StringBuilder();
		sql.append("select count(*) num from XG_XSZZ_NEW_KNSRSSZB t1 where exists(select 1 from xsxxb t0 where 1=1 ");
		String rskzfw = myForm.getRskzfw();
		if (rskzfw != null &&!"".equals(rskzfw)) {
			if (rskzfw.equals(Constants.RSKZFW_BJ)) {
				sql.append(" and t1.bmdm=t0.bjdm)");
			} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
				sql.append(" and t1.bmdm=t0.zydm and t1.nj=t0.nj)");
			} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
				sql.append(" and t1.bmdm=t0.xydm and t1.nj=t0.nj)");
			} else if (rskzfw.equals(Constants.RSKZFW_XY)) {
				sql.append(" and t1.bmdm=t0.xydm )");
			} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {
				sql.append(" and t1.bmdm=t0.zydm )");
			}
			return dao.getMapNotOut(sql.toString(), new String[]{}).get("num");
		}else{
			return "0";
		}
	}

}
