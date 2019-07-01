/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:07:42
 */
package com.zfsoft.xgxt.xpjpy.xmsz.rssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��-��������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:07:42
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class RsszDao extends SuperDAOImpl<RsszModel> {
	
	
	
	/**
	 * 
	 * @����: ���༶�������ò�ѯ�б����������ڵ� getPageList��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����09:02:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjrsList(RsszModel model,User user) throws Exception{
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (");
		sql.append(" SELECT  M.*,N.FPBL,N.GUID,N.ZZME,(CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END) SFYSZ");
		sql.append(" FROM (select a.bjdm,a.zrs,case when c.ytjrs is null then 0 else c.ytjrs end ytjrs," );
		sql.append(" b.xydm,b.nj,b.zydm,b.xymc,b.zymc,b.bjmc ");
		sql.append(" FROM (select t1.bjdm,count(1) zrs from (" );
		sql.append(" select a.* from xg_pjpy_new_cpmdb a left join xsxxb b on b.xh=a.xh" );
		sql.append(" where a.bjdm is not null " );
		if(!StringUtil.isNull(model.getPycc()) && !"all".equals(model.getPycc())){
			sql.append(" and b.pycc= '");
			sql.append(model.getPycc());
			sql.append("' ");
		}
		sql.append(") t1 ");
		sql.append(" where exists (select 1 from Xg_Pjpy_New_Csszb t2 where t1.xn=t2.xn and t1.xq=t2.xq) ");
		sql.append(" group by bjdm) A ");
		sql.append(" left join ( select t1.bjdm,count(1) ytjrs from (select * from xg_pjpy_new_cpmdb where bjdm is not null) t1 ");
		sql.append(" where exists (select 1 from Xg_Pjpy_New_Csszb t2 ");
		sql.append(" left join xg_zhcp_fstjjlb t3 on t2.xn=t3.xn and t2.xq=t3.xq ");
		sql.append(" where t3.tjzt='1' and t1.bjdm=t3.bjdm and t1.xn=t2.xn and t1.xq=t2.xq) ");
		sql.append(" group by bjdm) c on a.bjdm=c.bjdm ");
		sql.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.BJDM=B.BJDM) M  ");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.BJDM=N.BMDM  AND M.NJ=N.NJ");
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
		if (!StringUtil.isNull(model.getZydm())){
			sql.append(" AND t.ZYDM='"+model.getZydm()+"'");
		}
		if (!StringUtil.isNull(model.getBjdm())){
			sql.append(" AND t.BJDM='"+model.getBjdm()+"'");
		}
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), new String[] {});
	}
	
	
	/**
	 * 
	 * @����: ���꼶ѧԺ��ѯ���������б����������ڵ� getPageList��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����09:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getNjxyrsList(RsszModel model,User user) throws Exception{
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (");
		sql.append(" SELECT  M.*,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS END YTJRS,N.FPBL,N.GUID,N.ZZME,");
		sql.append("(CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END) SFYSZ FROM (");
		sql.append(" select t2.xymc,t2.xydm,t2.nj,count(1) zrs from (select a.* from xg_pjpy_new_cpmdb a left join xsxxb b on b.xh=a.xh");
		sql.append(" where a.bjdm is not null ");
		if(!StringUtil.isNull(model.getPycc()) && !"all".equals(model.getPycc())){
			sql.append(" and b.pycc= '");
			sql.append(model.getPycc());
			sql.append("' ");
		}
		sql.append(") t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t3 where t1.xn=t3.xn and t1.xq=t3.xq)");
		sql.append(" and t2.xydm is not null and t2.nj is not null and t2.zydm is not null group by t2.xymc,t2.xydm,t2.nj");
		sql.append(" ) M ");
		sql.append(" left join (select t2.xymc,t2.xydm,t2.nj,count(1) ytjrs from (select * from xg_pjpy_new_cpmdb where bjdm is not null) t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm ");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t3 ");
		sql.append(" left join xg_zhcp_fstjjlb t4 on t3.xn = t4.xn and t3.xq = t4.xq where t4.tjzt='1' and t1.bjdm=t4.bjdm and ");
		sql.append(" t1.xn=t3.xn and t1.xq=t3.xq) ");
		sql.append(" group by t2.xymc,t2.xydm,t2.nj) O ON O.XYDM=M.XYDM AND M.NJ=O.NJ ");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.XYDM=N.BMDM AND M.NJ=N.NJ");
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
	 * 
	 * @����: ���꼶רҵ��ѯ���������б����������ڵ� getPageList��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����09:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getNjzyrsList(RsszModel model,User user) throws Exception{
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (");
		sql.append(" SELECT  M.*,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS END YTJRS,N.FPBL,N.GUID,N.ZZME,(CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END) SFYSZ FROM (");
		sql.append(" select count(1) zrs ,t2.xymc,t2.zymc,t2.xydm,t2.zydm,t2.nj from (");
		sql.append("select a.* from xg_pjpy_new_cpmdb a left join xsxxb b on b.xh=a.xh");
		sql.append(" where a.bjdm is not null ");
		if(!StringUtil.isNull(model.getPycc()) && !"all".equals(model.getPycc())){
			sql.append(" and b.pycc= '");
			sql.append(model.getPycc());
			sql.append("' ");
		}
		sql.append(") t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t3 where t1.xn=t3.xn and t1.xq=t3.xq)");
		sql.append(" group by t2.xymc,t2.zymc,t2.xydm,t2.zydm,t2.nj");
		sql.append(" ) M left join ( ");
		sql.append(" select count(1) ytjrs,t2.xymc,t2.zymc,t2.xydm,t2.zydm,t2.nj from (select * from xg_pjpy_new_cpmdb where bjdm is not null) t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm ");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t3  ");
		sql.append(" left join xg_zhcp_fstjjlb t4 on t3.xn = t4.xn and t3.xq = t4.xq ");
		sql.append(" where t4.tjzt='1' and t1.bjdm=t4.bjdm and t1.xn=t3.xn and t1.xq=t3.xq) and t2.xydm is not null and t2.nj is not null and t2.zydm is not null ");
		sql.append(" group by t2.xymc,t2.zymc,t2.xydm,t2.zydm,t2.nj) O ON M.XYDM=O.XYDM AND M.ZYDM=O.ZYDM AND M.NJ=O.NJ ");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.ZYDM=N.BMDM  AND M.NJ=N.NJ ");
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
		if (!StringUtil.isNull(model.getZydm())){
			sql.append(" AND t.ZYDM='"+model.getZydm()+"'");
		}
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), new String[] {});
	}
	
	
	/**
	 * 
	 * @����: ��ѧԺ��ѯ���������б����������ڵ� getPageList��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����09:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyrsList(RsszModel model,User user) throws Exception{
		String searchTjByUser = SearchService.getSearchQxTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (");
		sql.append(" SELECT  M.*,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS  END YTJRS,N.FPBL,N.GUID,");
		sql.append("N.ZZME");
		sql.append(",nvl(N.Zd3,0) zd3,(CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END) SFYSZ FROM (");
		sql.append(" select count(1) zrs , t2.xydm,t2.xymc from (");
		sql.append("select a.* from xg_pjpy_new_cpmdb a left join xsxxb b on b.xh=a.xh");
		sql.append(" where a.bjdm is not null ");
		if(!StringUtil.isNull(model.getPycc()) && !"all".equals(model.getPycc())){
			sql.append(" and b.pycc= '");
			sql.append(model.getPycc());
			sql.append("' ");
		}
		sql.append(") t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t3 where t1.xn=t3.xn and t1.xq=t3.xq)");
//		// ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���
		String rskznj = new XmwhDao().getRsfpnj(model.getXmdm());
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
			sql.append(")");
		}
		sql.append(" group by t2.xydm,t2.xymc ) M ");
		sql.append(" left join (select count(1) YTJRS , t2.xydm,t2.xymc from (select * from xg_pjpy_new_cpmdb where bjdm is not null) t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm where ");
		
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){ //�㽭��ѧ�ύ״̬�ǰ�����Ϊ��λ
			sql.append(" t1.tjzt='1' ");
		}else{
			sql.append(" exists (select 1 from xg_pjpy_new_csszb t3 ");
			sql.append(" left join xg_zhcp_fstjjlb t4 on t3.xn = t4.xn and t3.xq = t4.xq ");
			sql.append(" where t4.tjzt='1' and t1.bjdm=t4.bjdm and t1.xn=t3.xn and t1.xq=t3.xq) ");
			if(Base.xxdm.equals("10704") && model.getSfyxgb().equals("1")){
				sql.append(" and exists (select 1 from xg_xsxx_xskzxx_xsgbjlb b where t1.xh = b.xh) ");
			}
		}
		
		// ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
			sql.append(")");
		}
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){ 
			sql.append("and t1.xn || t1.xq in  (select xn || xq from xg_pjpy_new_csszb)");
		}
		sql.append(" group by t2.xydm,t2.xymc ) O ON M.XYDM = O.XYDM ");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.XYDM=N.BMDM ");
		sql.append(" AND N.XMDM='");
		sql.append(model.getXmdm());
		sql.append("'");
		sql.append(") t ");
		sql.append(" where 1=1 ");
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND t.SFYSZ='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" AND t.XYDM='"+model.getXydm()+"'");
		}
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), new String[] {});
	}
	/**
	 * 
	 * @����:�����Ի���ѧ����������֤
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-19 ����09:37:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjze(RsszModel model,User user) throws Exception{
		String searchTjByUser = SearchService.getSearchQxTjByUser(user, "t", "xydm", "");
		String csszSql = "select csz from xg_pjpy_new_cspzb where csdm='rsjsfs'";
		String rsjsfs = dao.getOneRs(csszSql, new String[]{}, "csz");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XS_PJPY_NEW_JXJRSSZB,");
		sql.append("(SELECT nvl(sum(nvl(zd3,0)*xmje),0) jxjze,nvl(sum(zzme * xmje),0) jxsjze FROM (");
		sql.append(" SELECT  M.*,t5.xmje,t5.xmmc,t5.xmdm,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS  END YTJRS,N.zd3,N.FPBL,N.GUID,(case when N.ZD1 is null then '0' else N.ZZME end) ZZME,(CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END) SFYSZ FROM (");
		sql.append(" select count(1) zrs , t2.xydm,t2.xymc from (select * from xg_pjpy_new_cpmdb where bjdm is not null) t1");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t3 where t1.xn=t3.xn and t1.xq=t3.xq)");
//		// ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���
		String rskznj = new XmwhDao().getRsfpnj(model.getXmdm());
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
			sql.append(")");
		}
		sql.append(" group by t2.xydm,t2.xymc ) M ");
		sql.append(" left join (select count(1) YTJRS , t2.xydm,t2.xymc from (select * from xg_pjpy_new_cpmdb where bjdm is not null) t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm ");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t3 ");
		sql.append(" left join xg_zhcp_fstjjlb t4 on t3.xn = t4.xn and t3.xq = t4.xq ");
		sql.append(" where t4.tjzt='1' and t1.bjdm=t4.bjdm and t1.xn=t3.xn and t1.xq=t3.xq) ");
		// ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�,���ѧԺ���
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
			sql.append(")");
		}
		sql.append(" group by t2.xydm,t2.xymc) O ON M.XYDM = O.XYDM");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.XYDM=N.BMDM  and N.nj is null ");
		sql.append(" left join xg_pjpy_new_pjxmb t5 on N.xmdm = t5.xmdm");
		sql.append(" where t5.xmmc in (select xmmc from XG_PJPY_NEW_JESXXMB) and t5.xn||t5.xq in (select xn||xq from xg_pjpy_new_csszb) )t");
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
	
	
	
	
	/**
	 * 
	 * @����: ���������ѯ���������б����������ڵ� getPageList��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����09:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCpzrsList(RsszModel model,User user) throws Exception{
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		CsszModel csszModel = new CsszDao().getModel();
		sql.append("SELECT  M.*,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS END YTJRS,N.FPBL,N.GUID,N.ZZME");
		sql.append(" FROM (select q.cpzdm,j.cpzmc,q.zrs   from (");
		sql.append(" select k.cpzdm,count(1) zrs ");
		sql.append(" from xg_zhcp_fstjjlb k,(select a.* from xg_pjpy_new_cpmdb a ");
		sql.append(" left join xsxxb b on b.xh=a.xh  ");
		sql.append(" where a.bjdm is not null  ");
		if(!StringUtil.isNull(model.getPycc()) && !"all".equals(model.getPycc())){
			sql.append(" and b.pycc= '");
			sql.append(model.getPycc());
			sql.append("' ");
		}
		
		sql.append(") p ");
		sql.append(" where k.bjdm=p.bjdm and p.xn='");
		sql.append(csszModel.getXn());
		sql.append("' and p.xq='");
		sql.append(csszModel.getXq());
		sql.append("' and k.xn='");
		sql.append(csszModel.getXn());
		sql.append("' and k.xq='" );
		sql.append(csszModel.getXq());
		sql.append("'   ");
		sql.append(" group by k.cpzdm  ");
		sql.append(" ) q ");
		sql.append(" left join xg_zhcp_cpzb j on j.cpzdm=q.cpzdm ) m ");
		sql.append(" left join ( ");
		sql.append(" select q.cpzdm,j.cpzmc,q.ytjrs   from (");
		sql.append(" select k.cpzdm,count(1) ytjrs ");
		sql.append(" from xg_zhcp_fstjjlb k,(select * from xg_pjpy_new_cpmdb where bjdm is not null) p  ");
		sql.append(" where k.bjdm=p.bjdm and p.xn='");
		sql.append(csszModel.getXn());
		sql.append("' and p.xq='");
		sql.append(csszModel.getXq());
		sql.append("' and k.xn='");
		sql.append(csszModel.getXn());
		sql.append("' and k.xq='" );
		sql.append(csszModel.getXq());
		sql.append("' and k.tjzt='1'  ");
		sql.append(" group by k.cpzdm  ");
		sql.append(" ) q ");
		sql.append(" left join xg_zhcp_cpzb j on j.cpzdm=q.cpzdm ");
		sql.append(" ) O ON O.CPZDM=M.CPZDM ");
		sql.append("  left join xg_pjpy_new_rsszb n on m.cpzdm=n.bmdm ");
		sql.append(" AND N.XMDM='");
		sql.append(model.getXmdm());
		sql.append("'");
		sql.append("where 1=1 and m.cpzdm is not null ");
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND (CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END)='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getCpzmc())){
			sql.append(" AND M.CPZMC like '%"+model.getCpzmc()+"%'");
		}
		//sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), new String[] {});
	}
	
	/**
	 * 
	 * @����: ��ѧУ��ѯ���������б����������ڵ� getPageList��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����09:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXxrsList(RsszModel model,User user) throws Exception{
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  M.*,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS END YTJRS,N.FPBL,N.GUID,N.ZZME");
		sql.append(" from (select '");
		sql.append(Constants.RSKZFW_XX);
		sql.append("' bmdm,count(1) zrs from (");
		sql.append("select a.* from xg_pjpy_new_cpmdb a left join xsxxb b on b.xh=a.xh");
		sql.append(" where a.bjdm is not null ");
		if(!StringUtil.isNull(model.getPycc()) && !"all".equals(model.getPycc())){
			sql.append(" and b.pycc= '");
			sql.append(model.getPycc());
			sql.append("' ");
		}
		sql.append(") t1 ");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t2 where t1.xn=t2.xn and t1.xq=t2.xq) ");
		sql.append(" ) M left join ( select '");
		sql.append(Constants.RSKZFW_XX);
		sql.append("' bmdm, count(1) ytjrs from (select * from xg_pjpy_new_cpmdb where bjdm is not null) t1 ");
		sql.append(" where exists (select 1 from xg_pjpy_new_csszb t2 ");
		sql.append(" LEFT JOIN xg_zhcp_fstjjlb t3 on t3.xn = t2.xn and t3.xq = t2.xq ");
		sql.append(" where t3.tjzt='1' and t3.bjdm=t1.bjdm and  t1.xn = t2.xn ");
		sql.append(" and t1.xq = t2.xq) ");
		sql.append(" ) O ON O.BMDM=M.BMDM");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.BMDM=N.BMDM ");
		sql.append(" AND N.XMDM='");
		sql.append(model.getXmdm());
		sql.append("'");
		//sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), new String[] {});
	}
	
	
	

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(RsszModel model)
			throws Exception {
		//�ѷֲ𵽸�����***��ѯ���������б�
		return null;
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
	 * 
	 * @����:�߼���ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(RsszModel model,
			User user) throws Exception {
		return null;
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean runBlsz(RsszModel model, List<RsszModel> list)
			throws Exception {
		int[] result = null;

		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = null;
		String guid = null;
		sb = new StringBuffer();
		for (RsszModel model1 : list) {
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
				sb
						.append("insert into xg_pjpy_new_rsszb(fpbl,bmdm,nj,xmdm,zzme) ");
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

	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean runBlszAll(RsszModel model,
			List<HashMap<String, String>> list) throws Exception {
		int[] result = null;
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_pjpy_new_rsszb ");
		sb.append(" where xmdm=?");
		String[] input = { model.getXmdm() };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

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
	 * 
	 * @����:������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean runZzme(RsszModel model,User user) throws Exception {
		int[] result = null;

		StringBuffer sb = null;
		List<String> sqlList = new ArrayList<String>();
		String guid = null;
		String zzme = null;
		String ffbl = null;
		String nj = "";
		String bmdm = null;
		String rskzfw = null;
		String jsrs=null;
		String[] zzmes = model.getZzmes();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] cpzdms = model.getCpzdms();
		String[] fpbls = model.getFpbls();
		String[] guids = model.getGuids();
		String[] jsrsHid=model.getJsrsHid();

		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				guid = guids[i];
				if (!StringUtil.isNull(guid)) {
					sqlList
							.add("delete from xg_pjpy_new_rsszb where guid='"
									+ guid + "'");
				}
			}
		}

		if (zzmes != null && zzmes.length > 0) {
			for (int i = 0; i < zzmes.length; i++) {
				zzme = zzmes[i];
				if (zzme != null) {
					zzme = zzme.trim();
				}
				try {
					zzme = Integer.parseInt(zzme) + "";
				} catch (Exception e) {
					zzme = "";
				}
				ffbl = fpbls[i];
				if (njs != null) {
					nj = njs[i];
				}
				if(jsrsHid!=null){
					jsrs=jsrsHid[i];
				}
				rskzfw = model.getRsfpfs();
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
					}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {
						bmdm = cpzdms[i];
					}
				}
				sb = new StringBuffer();
				sb.append("insert into xg_pjpy_new_rsszb");
				if(!StringUtils.isBlank(model.getCzfs())&&model.getCzfs().equals(RsszService.czfs)){
					sb.append("(fpbl,bmdm,nj,xmdm,zzme,zd1,zd2,zd3)");
					sb.append(" values('" + ffbl + "','" + bmdm + "','" + nj + "','"+ model.getXmdm() + "','" + zzme + "','"+user.getUserName()+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"+jsrs+"')");
				}else{
					sb.append("(fpbl,bmdm,nj,xmdm,zzme,zd3) ");
					sb.append(" values('" + ffbl + "','" + bmdm + "','" + nj + "','"+ model.getXmdm() + "','" + zzme + "','"+jsrs+"')");
				}
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
	 * @����:��ѯ��������������ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getRssz(String xmdm) throws Exception {
		HashMap<String, String> data = new XmwhDao().getDataById(xmdm);
		StringBuilder sql = new StringBuilder();
		sql
				.append("select a.*,b.rsfpfs from xg_pjpy_new_rsszb a,xg_pjpy_new_pjxmb b  ");
		sql.append(" where a.xmdm=b.xmdm ");
		sql.append(" and a.xmdm=? ");
		if (data.get("rskzfw").equals(Constants.RSKZFW_XY)) {// ѧԺ
			String rsfpnj = data.get("rsfpnj");
			if (rsfpnj != null && !rsfpnj.trim().equals("")) {
				sql
						.append(" and a.bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				sql.append(setRskznj(rsfpnj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
				sql.append("))");
			}
		}
		String[] inputValue = { xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @����:������Ŀ����ɾ����¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		String sql = "delete from xg_pjpy_new_rsszb where xmdm=? ";
		String[] inputValue = { xmdm };
		int result = dao.runDelete(sql, inputValue);
		return result > 0;
	}

	/**
	 * 
	 * @����:��ѯ�����õ�������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public int getRsszCount(String xmdm) throws Exception {
		HashMap<String, String> data = new XmwhDao().getDataById(xmdm);
		int count = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" select  count(*) count from xg_pjpy_new_rsszb a  ");
		sql.append(" where a.zzme is not null ");
		sql.append(" and a.xmdm=? ");
		if (data.get("rsfpfs").equals(Constants.RSKZFW_XY)) {// ѧԺ
			String rskznj = data.get("rsfpnj");
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql
						.append(" and a.bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
				sql.append("))");
			}
		}
		String[] input = { xmdm };
		String[] output = { "count" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);// ///�˷����쳣�ѱ���������������޷�����
		// ��������������////������������////
		if (oneRs != null && oneRs.length > 0) {
			try {
				count = Integer.parseInt(oneRs[0]);
			} catch (Exception e) {
			}
		}
		return count;
	}

	/**
	 * 
	 * @����:��ѯ��Ŀ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public int getYszrs(RsszModel model) throws Exception {
		HashMap<String, String> data = new XmwhDao().getDataById(model
				.getXmdm());
		int num = 0;
		StringBuilder sql = new StringBuilder();
		sql
				.append("  select sum(zzme) num from xg_pjpy_new_rsszb   where xmdm=? ");
		if (data.get("rsfpfs").equals(Constants.RSKZFW_XY)) {// ѧԺ
			String rsfpnj = data.get("rsfpnj");
			if (rsfpnj != null && !rsfpnj.trim().equals("")) {
				sql
						.append(" and bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				sql.append(setRskznj(rsfpnj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
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
	 * 
	 * @����:��ȡ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception int ��������
	 * @throws
	 */
	public int getZrs(RsszModel model, String rskznj) throws Exception {
		String rskzfw = model.getRsfpfs();

		int num = 0;
		StringBuilder sql = new StringBuilder();
		if(Base.xxdm.equals("10704") && model.getSfyxgb().equals("1")){
			sql.append("select count(*) num from (select a.* from view_xsjbxx a where exists (select 1 from xg_xsxx_xskzxx_xsgbjlb b where a.xh = b.xh)) where xydm is not null");
		}else{			
			sql
			.append("select count(*) num from view_xsjbxx where xydm is not null ");
		}
		if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql.append(" and NJ in(");
				sql.append(setRskznj(rskznj));// ��ʽ��rskznj������''���Ա�ƴ��sql���
				sql.append(")");
			}
		}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// ������
			CsszModel csszModel = new CsszDao().getModel();
			sql = new StringBuilder();
			if(Base.xxdm.equals("10704") && model.getSfyxgb().equals("1")){
				sql.append("select  count(*) num from (select a.* from xg_pjpy_new_cpmdb a where a.bjdm is not null and exists (select 1 from xg_xsxx_xskzxx_xsgbjlb b where a.xh = b.xh)) where");
			}else{			
				sql.append(" select  count(*) num from (select * from xg_pjpy_new_cpmdb where bjdm is not null) where ");
			}
			sql.append(" xn='");
			sql.append(csszModel.getXn());
			sql.append("' and xq='");
			sql.append(csszModel.getXq());
			sql.append("' ");			
			sql.append(" and bjdm in (select bjdm from xg_zhcp_fstjjlb where ");
			sql.append(" xn='");
			sql.append(csszModel.getXn());
			sql.append("' and xq='");
			sql.append(csszModel.getXq());
			sql.append("' ");
			sql.append(") ");
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
	 * 
	 * @����:������Ŀ��������ѯ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getRsszOne(String xmdm, String xh,
			String rskzfw, String xn, String xq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb
				.append(" select a.zzme  from xg_pjpy_new_rsszb a,( select a.*,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc from (select * from xg_pjpy_new_cpmdb where bjdm is not null) a left join view_njxyzybj_all b on a.bjdm=b.bjdm ) b  ");
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
		sb.append("  and a.xmdm=? ");
		sb.append("  and b.xh=? ");
		sb.append(" and b.xn = ?");
		sb.append(" and b.xq = ?");
		String[] inputValue = new String[] { xmdm, xh,xn,xq };
		
		if (rskzfw.equals(Constants.RSKZFW_CPZ)) {//������ 
			sb = new StringBuilder();
			sb.append(" select a.zzme  from xg_pjpy_new_rsszb a,(select * from xg_pjpy_new_cpmdb where bjdm is not null) b,xg_zhcp_fstjjlb c ");
			sb.append(" where a.bmdm=c.cpzdm and b.bjdm=c.bjdm and b.xn=c.xn and b.xq=c.xq ");			
			sb.append("  and a.xmdm=? ");
			sb.append("  and b.xh=? ");
			sb.append(" and b.xn = ? ");
			sb.append(" and b.xq = ? ");
			inputValue = new String[] {xmdm, xh ,xn, xq };
		}else if (rskzfw.equals(Constants.RSKZFW_XX)) {//ѧУ
			 sb = new StringBuilder();
				sb.append(" select zzme  from xg_pjpy_new_rsszb  ");
				sb.append("  where xmdm=? ");
				inputValue = new String[] { xmdm };
		}
		String[] outputValue = new String[] { "zzme"};
		
		String sql = sb.toString();
		HashMap<String, String> map = dao.getMap(sql, inputValue, outputValue);
		return map;
	}

	/**
	 * 
	 * @����:��ѯ����ѧԺ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSyxy() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xydm,xymc from view_njxyzybj_all  ");
		String[] inputValue = {};
		List<HashMap<String, String>> result = dao.getListNotOut(
				sql.toString(), inputValue);
		return result;
	}

	/**
	 * 
	 * @����:ͨ��ѧԺ����ȡ��ѧԺ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String getXymc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select xymc name from view_njxyzybj_all where xydm=? ");
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
	 * 
	 * @����:ͨ��רҵ����ȡ��רҵ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String getZymc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select zymc name from view_njxyzybj_all where zydm=? ");
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
	 * 
	 * @����:ͨ���༶����ȡ���༶����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String getBjmc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select bjmc name from view_njxyzybj_all where bjdm=? ");
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
	 * 
	 * @����:ͨ������������ȡ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param dm
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String getCpzmc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select cpzmc name from xg_zhcp_cpzb where cpzdm=? ");
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
	 * 
	 * @����:��ȡ���а���ѧ�����꼶
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
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
	 * 
	 * @����:��������������ѧУ��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-13 ����04:46:15
	 * @�޸ļ�¼: 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runZzmeForXx(RsszModel model) throws Exception {
		int[] result = null;

		StringBuffer sb = null;
		List<String> sqlList = new ArrayList<String>();
		String zzme = null;
		String bmdm = Constants.RSKZFW_XX;
		String xmdm = model.getXmdm();

		String[] zzmes = model.getZzmes();

		sqlList.add("delete from xg_pjpy_new_rsszb where xmdm='"
									+ xmdm + "'");
		if(zzmes != null && zzmes.length > 0){
			zzme = zzmes[0];
		}
		if (zzme != null) {
			zzme = zzme.trim();
		}
		int izzme = 0;
		try {
			izzme = Integer.parseInt(zzme);
		} catch (Exception e) {
		}
		sb = new StringBuffer();
		sb.append("insert into xg_pjpy_new_rsszb(bmdm,xmdm,zzme) ");
		sb.append(" values('" + bmdm + "'");
		sb.append(",'" + model.getXmdm() + "','" + izzme + "')");
		sqlList.add(sb.toString());
		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}
		return dao.checkBatch(result);
	}

	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_rsszb");
		super.setKey("guid");
	}
	
	/**
	 * 
	 * @����:������Ŀ�����ȡ�������䷽ʽ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-17 ����02:19:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getRsfpfs(String xmdm){
		StringBuilder sql =  new StringBuilder();
		sql.append("select rsfpfs,decode(rsfpfs,'xx','ѧУ','xy','ѧԺ','njxy','�꼶+ѧԺ',");
		sql.append("'njzy','�꼶+רҵ','bj','�༶','cpz','������') rsfpfsmc from xg_pjpy_new_pjxmb where xmdm = ?");
		return dao.getMap(sql.toString(), new String[]{xmdm}, new String[]{"rsfpfs","rsfpfsmc"});
	}
	
	

}
