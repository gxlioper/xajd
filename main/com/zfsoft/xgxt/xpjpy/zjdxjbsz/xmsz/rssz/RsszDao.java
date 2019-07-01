/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����09:30:34 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-6-1 ����09:30:34 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RsszDao extends SuperDAOImpl<RsszForm>{

	/**
	 * ��ͨ��ѯ����
	 */
	@Override
	public List<HashMap<String, String>> getPageList(RsszForm t)
			throws Exception {
		return null;
	}

	/**
	 * �߼���ѯ����
	 */
	@Override
	public List<HashMap<String, String>> getPageList(RsszForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_rsszb");
		super.setKey("guid");
	}
	
	/**
	 * @����: ��ѧ����������֤
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����11:17:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjze(RsszForm model,User user) throws Exception{
		String searchTjByUser = SearchService.getSearchQxTjByUser(user, "t", "xydm", "");
		String csszSql = " select csz from xg_pjpy_new_cspzb where csdm = 'rsjsfs' ";
		String rsjsfs = dao.getOneRs(csszSql, new String[]{}, "csz");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xs_pjpy_new_jxjrsszb,");
		sql.append("(SELECT nvl(sum(nvl(zd3,0)*xmje),0) jxjze,nvl(sum(zzme * xmje),0) jxsjze FROM (");
		sql.append(" SELECT  M.*,t5.xmje,t5.xmmc,t5.xmdm,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS  END YTJRS,N.zd3,N.FPBL,N.GUID,(case when N.ZD1 is null then '0' else N.ZZME end) ZZME,(CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END) SFYSZ FROM (");
		sql.append(" select count(1) zrs , t2.xydm,t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm");
		sql.append(" where exists (select 1 from xg_zjdx_pjpy_csszb t3 where t1.xn=t3.xn)");
		/*ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���*/
		String rskznj = new XmwhDao().getRsfpnj(model.getXmdm());
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
			sql.append(")");
		}
		sql.append(" group by t2.xydm,t2.xymc ) M ");
		sql.append(" left join (select count(1) YTJRS , t2.xydm,t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm ");
		sql.append(" where exists (select 1 from xg_zjdx_pjpy_csszb t3 ");
		sql.append(" left join xg_zjdx_pjpy_fstjjlb t4 on t3.xn = t4.xn ");
		sql.append(" where t4.tjzt='1' and t4.xydm in (select distinct y.xydm from xg_zjdx_pjpy_cpmdb z left join view_njxyzybj_all y on z.bjdm = y.bjdm where z.bjdm is not null and z.xn = t3.xn ) and t1.xn=t3.xn ) ");
		// ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
			sql.append(")");
		}
		sql.append(" group by t2.xydm,t2.xymc) O ON M.XYDM = O.XYDM");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.XYDM=N.BMDM  and N.nj is null ");
		sql.append(" left join xg_zjdx_pjpy_pjxmb t5 on N.xmdm = t5.xmdm");
		sql.append(" where t5.xmmc in (select xmmc from XG_PJPY_NEW_JESXXMB) and t5.xn in (select xn from xg_zjdx_pjpy_csszb) )t");
		sql.append(" where 1=1 ");
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND t.SFYSZ='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" AND t.XYDM='"+model.getXydm()+"'");
		}
		sql.append(searchTjByUser);
		sql.append(")");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/*
	 * @����:��ʽ��rskznj������''���Ա�ƴ��sql���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
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
	 * @����: ��ȡ���а���ѧ�����꼶
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����01:49:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = null;
		String sql = "select distinct nj from view_xsjbxx where nj is not null order by nj";
		String[] inputValue = {};
		result = dao.getList(sql, inputValue, "nj");
		return result;
	}
	
	/**
	 * @����: ��ѧԺ��ѯ���������б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����03:06:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyrsList(RsszForm model,User user)
		throws Exception{
		String searchTjByUser = SearchService.getSearchQxTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select M.*,case when O.ytjrs is null then 0 else O.ytjrs end ytjrs,N.fpbl,N.guid, ");
		sql.append("N.zzme, ");
		sql.append("nvl(N.zd3, 0) zd3,(case when N.ZZME is null then '0' else '1' end) sfysz from ( ");
		sql.append("select count(1) zrs, t2.xydm, t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  where exists (select 1 ");
		sql.append("from xg_zjdx_pjpy_csszb t3 where t1.xn = t3.xn) ");
		/*ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���*/
		String rskznj = new XmwhDao().getRsfpnj(model.getXmdm());
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			/*��ʽ��rskznj������''���Ա�ƴ��sql���*/
			sql.append(setRskznj(rskznj));
			sql.append(")");
		}
		sql.append("group by t2.xydm, t2.xymc) M ");
		sql.append("left join (select count(1) ytjrs, t2.xydm, t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm where t1.tjzt = '1' ");
		// ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
			sql.append(")");
		}
		sql.append("and t1.xn in (select xn from xg_pjpy_new_csszb where rownum = 1) group by t2.xydm, t2.xymc)O ON M.xydm = O.xydm ");
		sql.append("left join xg_pjpy_new_rsszb N on m.xydm = N.bmdm ");
		sql.append("and N.xmdm='");
		sql.append(model.getXmdm());
		sql.append("'");
		sql.append(") t where 1=1 ");
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append("and t.sfysz = '"+model.getSfysz()+"' ");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append("and t.xydm = '"+model.getXydm()+"' ");
		}
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ���꼶ѧԺ��ѯ���������б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����04:27:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getNjxyrsList(RsszForm model,User user)
		throws Exception{
		/*Ȩ�޿���*/
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select M.*,case when O.ytjrs is null then 0 else O.ytjrs end ytjrs, ");
		sql.append("N.fpbl,N.guid,N.zzme,(case when N.zzme is null then '0' else '1' end) sfysz ");
		sql.append("from (select t2.xymc, t2.xydm, t2.nj, count(1) zrs ");
		sql.append("from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append("where exists (select 1 from xg_zjdx_pjpy_csszb t3 where t1.xn = t3.xn) ");
		sql.append("and t2.xydm is not null and t2.nj is not null and t2.zydm is not null group by t2.xymc, t2.xydm, t2.nj) M ");
		sql.append("left join ( select t2.xymc, t2.xydm, t2.nj, count(1) ytjrs from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		
		sql.append("where t1.tjzt = '1' and xn in (select xn from xg_pjpy_new_csszb where rownum = 1) group by t2.xymc, t2.xydm, t2.nj ) O ");
		sql.append("on O.xydm = M.xydm and M.nj = O.nj left join xg_pjpy_new_rsszb N on M.xydm = N.bmdm and M.nj = N.nj ");
		sql.append(" AND N.XMDM='");
		sql.append(model.getXmdm());
		sql.append("'");
		sql.append(") t ");
		sql.append(" where 1=1 ");
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND t.SFYSZ='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getNjq())){
			sql.append(" AND t.NJ='"+model.getNjq()+"'");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" AND t.XYDM='"+model.getXydm()+"'");
		}
		sql.append(searchTjByUser);
		
		return getPageList(model, sql.toString(), new String[] {});
	}
	
	/**
	 * @����: ��ѯ���ñ�
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����04:54:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsz(String csdm) {
		String sql = "select csz from xg_pjpy_new_cspzb where csdm = ? ";
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
	}
	
	/**
	 * @����: ��Ŀ�����õ�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-2 ����09:48:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int getYszrs(RsszForm model)throws Exception {
		/*������Ŀ�����ô���Ŀ����������*/
		HashMap<String, String> data = new XmwhDao().getDataById(model.getXmdm());
		/*����num*/
		int num = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(zzme) num from xg_pjpy_new_rsszb where xmdm = ? ");
		String rsfgfs = data.get("rsfpfs");
		/*ѧԺ*/
		if(rsfgfs.equals(Constants.RSKZFW_XY)) {
			String rsfpnj = data.get("rsfpnj");
			if (rsfpnj != null && !rsfpnj.trim().equals("")) {
				sql.append(" and bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				/*��ʽ��rskznj������''���Ա�ƴ��sql���*/
				sql.append(setRskznj(rsfpnj));
				sql.append("))");
			}
		}
		String[] input = { model.getXmdm() };
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
	 * @����: ���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����02:22:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param rskznj
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int getZrs(RsszForm model, String rskznj) throws Exception {
		String rskzfw = model.getRsfpfs();
		int num = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) num from view_xsjbxx where xydm is not null ");
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
	 * @����: ͨ��ѧԺ����ȡ��ѧԺ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����02:22:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXymc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select xymc name from view_njxyzybj_all where xydm = ? ");
		String[] input = { dm };
		String[] output = { "name" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				name = oneRs[0];
			}
		}
		return name;
	}
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����02:29:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runBlszAll(RsszForm model,List<HashMap<String, String>> list) throws Exception {
		int[] result = null;
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_pjpy_new_rsszb ");
		sb.append(" where xmdm = ?");
		String[] input = { model.getXmdm() };
		/*ɾ��ԭ��¼*/
		dao.runDelete(sb.toString(), input);
		sb = new StringBuffer();
		sb.append("insert into xg_pjpy_new_rsszb(fpbl,bmdm,nj,xmdm,zzme,zd3) ");
		sb.append(" values(?,?,?,?,?,?)");

		for (HashMap<String, String> map : list) {
			if(map.get("bmdm") != null){
				String[] param = { model.getFpbl(), map.get("bmdm"), map.get("nj"),
						model.getXmdm(),
						map.get("zzme"),
						map.get("zzme")};
				paramList.add(param);
			}
		}
		result = dao.runBatch(sb.toString(), paramList);
		return dao.checkBatch(result);
	}
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����02:51:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runBlsz(RsszForm model, List<RsszForm> list)
			throws Exception {
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = null;
		String guid = null;
		sb = new StringBuffer();
		for (RsszForm model1 : list) {
			guid = model1.getGuid();
			if (guid != null && !guid.trim().equals("")
					&& !guid.trim().equals("null")) {
				sb = new StringBuffer();
				sb.append("update xg_pjpy_new_rsszb set fpbl='");
				sb.append(model.getFpbl());
				sb.append("',zzme='");
				sb.append(model1.getZzme());
				sb.append("' where guid='");
				sb.append(guid);
				sb.append("'");
				sqlList.add(sb.toString());
			} else {
				sb = new StringBuffer();
				sb.append("insert into xg_pjpy_new_rsszb(fpbl,bmdm,nj,xmdm,zzme) ");
				sb.append(" values('");
				sb.append(model.getFpbl());
				sb.append("','");
				sb.append(model1.getBmdm());
				sb.append("','");
				sb.append(model1.getNj());
				sb.append("','");
				sb.append(model.getXmdm());
				sb.append("','");
				sb.append(model1.getZzme());
				sb.append("')");
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
}
