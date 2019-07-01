/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-10 ����11:39:31 
 */
package com.zfsoft.xgxt.khgl.khdxgl.pfzgl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.khgl.khdxgl.khdxgl.KhdxglDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-10 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class PfzglDao extends SuperDAOImpl<PfzglForm> {
	private KhdxglDao khdxdao = new KhdxglDao();

	@Override
	public List<HashMap<String, String>> getPageList(PfzglForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(PfzglForm t, User user) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.khlx,t2.sfnz khdxsfnz,t2.khdxmc,nvl(t3.yhs,0)khdxrs,(to_number(nvl(t3.yhs,0))-to_number(nvl(t4.yfpyhs,0))) wfprs,case when t1.pflx='1' then 'ѧ��' when t1.pflx='2' then '��ʦ' else '����' end pflxmc");
		sql.append("  from xg_khgl_pfz t1 left join xg_khgl_khdx t2 on t1.khdxid=t2.khdxid left join ");
		sql.append(" (select count(a.zgh) yhs, '������' yhlx from fdyxxb a  where a.zgh in (select zgh from bzrbbb where bjdm in (select bjdm from view_njxyzybj))");
		sql.append(" union all select count(a.zgh) yhs, '����Ա' yhlx from fdyxxb a  where a.zgh in (select zgh from fdybjb where bjdm in (select bjdm from view_njxyzybj))");
		sql.append(" union all select count(a.xh) yhs, 'ѧ��' yhlx from view_xsjbxx a union all");
		sql.append("  select count(1) yhs,khdxid yhlx from xg_khgl_khdx_xs group by khdxid");
		sql.append(" union all select count(1) yhs,khdxid yhlx from xg_khgl_khdx_js group by khdxid) t3");
		sql.append(" on (case when t2.sfnz = '2' then  t2.khdxmc else t2.khdxid  end )= t3.yhlx  ");
		sql.append(" left join( select count(distinct khdxr) yfpyhs,pfzid yhlx from xg_khgl_pfz_xs group by pfzid");
		sql.append(" union all select count(distinct khdxr) yfpyhs,pfzid yhlx from xg_khgl_pfz_js group by pfzid) t4");
		sql.append(" on t1.pfzid= t4.yhlx  ");
		sql.append("order by t1.sfnz desc) t where 1=1 and sfnz='1'");
		if (!StringUtil.isNull(t.getPfzmc())) {
			params.add(t.getPfzmc());
			sql.append(" and t.pfzmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 *�жϿ��˶����Ƿ���ʹ��
	 */
	public boolean isUsed(String value) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num from xg_khgl_khxm_sz where pfzid =?");
 		String num = dao.getOneRs(sql.toString(), new String[]{value}, "num");
		return Integer.parseInt(num)>0;
	}
	public List<HashMap<String, String>> getStuList(PfzglForm model, User user)
			throws Exception {
		HashMap<String,String> khdxMap = khdxdao.getKhdx(model.getKhdxid());
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		if ("2".equals(khdxMap.get("sfnz"))) {
			sql.append("select nvl(c.pfcys,0)pfcys,a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,nvl2(d.xh, '1', '0') as sfbgb, nvl2(d.xh, '��', '��') as sfbgbmc,");
			sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm,case when (a.sfzx='����У' and c.pfzid is not null) or a.sfzx='��У'");
			sql.append(" then '1' else '0' end sfxs from view_xsbfxx a left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH");
			sql.append(" where zzzt = '1') d on a.xh = d.xh");
		} else {
			sql.append(" select '1' sfxs,nvl(c.pfcys,0)pfcys,a.xh ,nvl2(d.xh, '1', '0') as sfbgb, nvl2(d.xh, '��', '��') as sfbgbmc, b.xm,b.nj,b.xz,b.xb,b.xymc,b.zymc,b.bjmc,b.xydm,b.zydm,b.bjdm from(select a.khxh xh from xg_khgl_khdx_xs a");
			sql.append(" where a.khdxid='"+model.getKhdxid()+"')a left join view_xsjbxx b on a.xh=b.xh  left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH ");
			sql.append(" where zzzt = '1') d on b.xh = d.xh");
		}
		if("1".equals(model.getPflx())){
			sql.append(" left join (select count(1) pfcys,pfzid,khdxr from xg_khgl_pfz_xs where pfzid='"+model.getPfzid()+"' group by pfzid,khdxr) c on a.xh=c.khdxr");
		}else{
			sql.append(" left join (select count(1) pfcys,pfzid,khdxr from xg_khgl_pfz_js where pfzid='"+model.getPfzid()+"' group by pfzid,khdxr) c on a.xh=c.khdxr");
		}
		sql.append(")a where 1=1 and a.sfxs='1' ");
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by sfbgb desc");
		return getPageList(model, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getPfcyList(PfzglForm model, User user)
	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String khdxr="";
		if("1".equals(model.getKhlx())){
			khdxr=model.getXh();
		}else{
			khdxr=model.getZgh();
		}
		sql.append("select * from (");
		if("1".equals(model.getPflx())){
			sql.append("select nvl2(e.pfr,'1','0')sfydf,nvl2(t1.pfzid,'��','��') sfsypfz,nvl2(d.xh, '1', '0') as sfbgb, nvl2(d.xh, '��', '��') as sfbgbmc,");
			sql.append(" a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,v.ldmc,v.lddm,v.ch,v.qsh");
			sql.append(" from view_xsjbxx a ");
			sql.append("left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH");
			sql.append(" where zzzt = '1') d on a.xh = d.xh ");
			sql.append(" left join (select * from xg_khgl_pfz_xs where pfzid= '"+model.getPfzid()+"' and khdxr='"+khdxr+"') t1 on t1.pfzxh=a.xh ");
			sql.append(" left join (select distinct(a.pfr) pfr from xg_khgl_khpf a where a.xmid in(select xmid from xg_khgl_khxm_sz where pfzid='"+model.getPfzid()+"'))e");
			sql.append(" on a.xh=e.pfr left join view_xg_gygl_new_cwxx v on a.xh=v.xh where 1=1");
		}else{
			sql.append("select a.zgh,decode(a.xb,'1','��','2','Ů',a.xb) xb,a.xm,b.bmdm,b.bmmc,nvl2(t1.pfzid,'��','��') sfsypfz,nvl2(e.pfr,'1','0')sfydf from fdyxxb a ");
			sql.append(" left join zxbz_xxbmdm b on a.bmdm= b.bmdm");
			sql.append(" left join (select * from xg_khgl_pfz_js where pfzid= '"+model.getPfzid()+"' and khdxr='"+khdxr+"') t1 on t1.pfzgh=a.zgh  ");
			sql.append(" left join (select distinct(a.pfr) pfr from xg_khgl_khpf a where a.xmid in(select xmid from xg_khgl_khxm_sz where pfzid='"+model.getPfzid()+"'))e");
			sql.append(" on a.zgh=e.pfr where 1=1");
		}
		sql.append(")a where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}	
	
	/**
	 * 
	 * @����:���ֳ�Ա�༶
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��29�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param zgh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfcybjList(PfzglForm model) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bjdm, ");
		sql.append("        a.zgh, ");
		sql.append("        b.nj, ");
		sql.append("        b.xydm, ");
		sql.append("        b.xymc, ");
		sql.append("        b.zydm, ");
		sql.append("        b.zymc, ");
		sql.append("        b.bjmc, ");
		sql.append("        nvl(c.bjrs, 0) bjrs, ");
		sql.append("        nvl(d.pfzrs, 0) pfzrs ");
		sql.append("  from (select * from bzrbbb ");
		sql.append("        union ");
		sql.append("        select * from fdybjb) a ");
		sql.append("  join view_njxyzybj b ");
		sql.append("    on a.bjdm = b.bjdm ");
		sql.append("  left join (select count(1) bjrs, bjdm ");
		sql.append("               from xsxxb a ");
		sql.append("              where (a.sfzx is null or a.sfzx = '��У') ");
		sql.append("              group by a.bjdm) c ");
		sql.append("    on a.bjdm = c.bjdm ");
		sql.append("  left join (select count(1) pfzrs, m.bjdm ");
		sql.append("               from xsxxb m, xg_khgl_pfz_xs n ");
		sql.append("              where m.xh = n.pfzxh ");
		sql.append("                and n.khdxr = ? ");
		sql.append("                and n.pfzid = ? ");
		sql.append("              group by m.bjdm) d ");
		sql.append("    on a.bjdm = d.bjdm ");
		sql.append(" where a.zgh = ? ");
		sql.append(" order by b.nj, b.xydm, b.zydm, b.bjdm ");

		return getPageList(model,sql.toString(), new String[]{model.getZgh(),model.getPfzid(),model.getZgh()});
	}
	
	public List<HashMap<String, String>> getViewPfcyList(PfzglForm model, User user)
	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String khdxr="";
		if("1".equals(model.getKhlx())){
			khdxr=model.getXh();
		}else{
			khdxr=model.getZgh();
		}
		sql.append("select * from (");
		if("1".equals(model.getPflx())){
			sql.append("select nvl2(t1.pfzid,'��','��') sfsypfz,nvl2(d.xh, '1', '0') as sfbgb, nvl2(d.xh, '��', '��') as sfbgbmc,");
			sql.append(" a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm");
			sql.append(" from (select * from xg_khgl_pfz_xs where pfzid= '"+model.getPfzid()+"' and khdxr='"+khdxr+"') t1 left join view_xsjbxx a ");
			sql.append("on  a.xh = t1.pfzxh left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH");
			sql.append(" where zzzt = '1') d  ");
			sql.append("  on a.xh=d.xh ");
			
		}else{
			sql.append("select a.zgh,decode(a.xb,'1','��','2','Ů',a.xb) xb,a.xm,b.bmdm,b.bmmc,nvl2(t1.pfzid,'��','��') sfsypfz ");
			sql.append(" from (select * from xg_khgl_pfz_js where pfzid= '"+model.getPfzid()+"' and khdxr='"+khdxr+"') t1");
			sql.append(" left join fdyxxb a on t1.pfzgh=a.zgh left join zxbz_xxbmdm b on a.bmdm= b.bmdm");
		}
		sql.append(")a where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
}

	public List<HashMap<String, String>> getTeaList(PfzglForm model, User user)
			throws Exception {
		HashMap<String,String> khdxMap = khdxdao.getKhdx(model.getKhdxid());
		StringBuffer sql = new StringBuffer();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql.append("select a.* ,");
		sql.append("case ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = 'У��' then 'У���û�' ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = 'Ժ��' then 'Ժ���û�' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = 'У��' then '����ѧУ�û�' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = 'Ժ��' then '����Ժϵ�û�' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = 'У��' then '�༶�û�' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = 'Ժ��' then '�༶�û�' ");
		sql.append("end yhsf, ");
		sql.append("decode(a.fdydbs,'0','��','��') sffdy, ");
		sql.append("decode(a.bzrdbs,'0','��','��') sfbzr ");
		sql.append("from ( ");
		sql.append("select nvl(t1.pfcys,0)pfcys,a.sfbl,a.zgh,a.xm,a.zw,a.lxdh,a.bmdm,a.kzzd5,b.bmmc,a.xl,a.zc,a.zzmm, ");
		sql.append("decode(a.xb,'1','��','2','Ů',a.xb) xb, ");
		sql.append("decode(b.bmlb,'5','Ժ��','У��') bmjb, ");
		sql.append("nvl(c.num,0) fdydbs,nvl(d.num,0) bzrdbs,");
		sql.append("decode(e.yhm,null,'��','��') sfyh, a.sfjryx ");
		sql.append("from fdyxxb a ");
		if("1".equals(model.getPflx())){
			sql.append(" left join (select count(1) pfcys,pfzid,khdxr from xg_khgl_pfz_xs where pfzid= '"+model.getPfzid()+"' group by pfzid,khdxr) t1 on t1.khdxr=a.zgh ");
		}else{
			sql.append(" left join (select count(1) pfcys,pfzid,khdxr from xg_khgl_pfz_js where pfzid= '"+model.getPfzid()+"' group by pfzid,khdxr) t1 on t1.khdxr=a.zgh ");
		}
		sql.append("left join zxbz_xxbmdm b ");
		sql.append("on a.bmdm=b.bmdm ");
		sql.append(" left join (select c.zgh, count(distinct bjdm) num ");
		sql.append("   from fdybjb c ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by c.zgh) c ");
		sql.append("on a.zgh=c.zgh ");
		sql.append(" left join (select d.zgh, count(distinct bjdm) num ");
		sql.append("  from bzrbbb d ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by d.zgh) d ");
		sql.append("on a.zgh=d.zgh ");
		sql.append("left join yhb e ");
		sql.append("on a.zgh=e.yhm ");
		sql.append(") a ");
		sql.append("where 1=1 ");
		if ("2".equals(khdxMap.get("sfnz"))) {
			if("������".equals(khdxMap.get("khdxmc"))){
				sql.append(" and a.zgh in (select zgh from bzrbbb where bjdm in (select bjdm from view_njxyzybj))");
			}else if("����Ա".equals(khdxMap.get("khdxmc"))){
				sql.append(" and a.zgh in (select zgh from fdybjb where bjdm in (select bjdm from view_njxyzybj))");
			}else{
				sql.append(" and a.zgh in (select a.zgh from fdyxxb a  where a.zgh in (select zgh from fdybjb where bjdm in (select bjdm from view_njxyzybj))");
				sql.append(" or a.zgh in (select zgh from bzrbbb where bjdm in (select bjdm from view_njxyzybj)))");
			}
		}else{
			sql.append(" and a.zgh in(select khzgh from xg_khgl_khdx_js where khdxid='"+model.getKhdxid()+"' )");
		}
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getPfzgl(PfzglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,case when t1.sfsljx='1' then '��' else '��' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc ");
		sql.append("from xg_khgl_pfz t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm");
		sql.append(" where t1.pfzid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getPfzid() });
	}


	/**
	 *�ж��������Ƿ��Ѵ���
	 */
	public boolean isHave(PfzglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_khgl_pfz where Pfzmc=? and pflx=?");
		if(null!=model.getPfzid()){
			sql.append(" and pfzid<>'"+model.getPfzid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getPfzmc(),model.getPflx()}, "num");
		return Integer.parseInt(num)>0;
	}


	/**
	 * 
	 * ɾ��������
	 */
	public boolean delPfzgl(String pfzid) throws Exception {
		String sql = "delete from xg_khgl_pfz where pfzid=?";
		return dao.runUpdate(sql, new String[] { pfzid });
	}
	
	/**
	 * 
	 * @����:�޸�ɾ��״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����04:00:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delPfz(List<String[]> params) throws Exception {
		String sql = "update xg_khgl_pfz set sczt=? where pfzid=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:��������ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����09:08:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pfxsInsert(List<String[]> params) throws Exception {
		String sql = "insert into xg_khgl_pfz_xs(pfzid,pfzxh,khdxr) select ?,xh pfzxh,? khdxr from VIEW_XSJBXX where xh=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:�������ֽ�ʦ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����09:08:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pfjsInsert(List<String[]> params) throws Exception {
		String sql = "insert into xg_khgl_pfz_js(pfzid,khdxr,pfzgh) values(?,?,?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @����:����ѧ��ȡ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����09:35:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pfxsDel(List<String[]> params) throws Exception {
		String sql = "delete from xg_khgl_pfz_xs where pfzid=? and khdxr=? and pfzxh=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:���˶����ѡɾ����Ӧ���˶�������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-12 ����04:49:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pfxsPlDel(List<String[]> params) throws Exception {
		StringBuffer sql  = new StringBuffer();
		sql.append("delete from xg_khgl_pfz_xs where pfzid=?  and khdxr=?");
		sql.append(" and pfzxh not in(select a.pfr from xg_khgl_khpf a where a.xmid in(select xmid from xg_khgl_khxm_sz where pfzid=?))");
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:���ֽ�ʦȡ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����09:08:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pfjsDel(List<String[]> params) throws Exception {
		String sql = "delete from xg_khgl_pfz_js where pfzid=? and khdxr=? and pfzgh=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:���˶����ѡɾ����Ӧ���˶�������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-12 ����04:49:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pfjsPlDel(List<String[]> params) throws Exception {
		StringBuffer sql  = new StringBuffer();
		sql.append( "delete from xg_khgl_pfz_js where pfzid=? and khdxr=? ");
		sql.append(" and pfzgh not in(select a.pfr from xg_khgl_khpf a where a.xmid in(select xmid from xg_khgl_khxm_sz where pfzid=?))");
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:��ȡ��Ŀ����ѧ���б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-16 ����05:19:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSqxsList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bjmc,t2.xm,case when t1.ylzd2='1' then '��' else '��' end sfqqmc,t4.jxmc,(nvl(to_number(t4.fjxf),0)+to_number(t3.jcxf))zxf");
		sql.append(" from xg_sztz_xs_sqjg t1 left join view_xsjbxx t2 on t1.xh=t2.xh left join xg_khgl_pfz t3 on t1.xmdm=t3.xmdm");
		sql.append(" left join xg_sztz_xm_jx t4 on t1.ylzd1=t4.pfzid where t1.xmdm=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	/**
	 * 
	 * @����:�������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����01:09:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPfzList(String khdxid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_khgl_pfz where (khdxid=? or khdxid='����') order by sfnz desc");
		return dao.getListNotOut(sql.toString(), new String[] {khdxid});
	}
	@Override
	public PfzglForm getModel(PfzglForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,t1.khdxmc,case when t.pflx='1' then 'ѧ��' else '��ʦ' end khlxmc from xg_khgl_pfz t");
		sql.append(" left join xg_khgl_khdx t1 on t.khdxid=t1.khdxid where t.pfzid=?");
		return getModel(sql.toString(), new String[]{model.getPfzid()});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(PfzglForm.class);
		super.setKey("pfzid");
		super.setTableName("xg_khgl_pfz");

	}
	
	public boolean pfxsbjDel(String pfzid,String khdxr,String classid) throws Exception {
		StringBuilder sql =new StringBuilder();
		sql.append("delete from xg_khgl_pfz_xs where pfzid = ?  and khdxr= ?  and pfzxh in (");
		sql.append("select xh from VIEW_XSJBXX where bjdm in (");
		sql.append(classid);
		sql.append("))");
		return dao.runUpdate(sql.toString(),new String[] {pfzid,khdxr});
	}
	
	
	public boolean pfxsbjInsert(String pfzid,String khdxr,String classid) throws Exception {
		StringBuilder sql =new StringBuilder();
		sql.append("insert into xg_khgl_pfz_xs(pfzid,pfzxh,khdxr) select ? pfzid,xh pfzxh,? khdxr ");
		sql.append("from VIEW_XSJBXX where bjdm in (");
		sql.append(classid);
		sql.append(")");
		return dao.runUpdate(sql.toString(),new String[] {pfzid,khdxr});
	}
	
}
