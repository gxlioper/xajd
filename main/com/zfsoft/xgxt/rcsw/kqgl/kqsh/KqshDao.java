/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-28 ����11:16:39 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����_����������ģ��
 * @�๦������: �������dao
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-28 ����11:16:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqshDao extends SuperDAOImpl<KqshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqshForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s5", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "s5", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( ");
		sql.append("select s1.*,s2.guid shid,s2.shzt shztx,s2.gwid, ");
		sql.append("s2.shr,s2.shyj,s4.mc || '[' ||decode(s2.shzt,'0','δ���','1', ");
		sql.append("'ͨ��','2','��ͨ��','3','�˻�','4','������','5', ");
		sql.append("'�����') || ']' shztmc,s4.gwz, ");
		sql.append("row_number() over(partition by s1.id order by s2.shsj desc) rn ");
		sql.append("from (select a.*, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc, ");
		sql.append(" translate(to_number(a.yf),'0123456789','��һ�����������߰˾�')||'��' toyf, ");
		sql.append("  '��'||translate(to_number(a.zc),'0123456789','��һ�����������߰˾�')||'��' tozc, ");
		sql.append(" round((to_number(cqrs)-to_number(kkrs))/to_number(cqrs)*100,1) cql from ( ");
		sql.append(" select a.*,(select count(distinct xh) from XG_RCSW_KQGL_ZJSYKQXXB t1 ");
		sql.append(" where a.id = t1.kqid and bjcs <> '0') bjrs, (select count(distinct xh)");
		sql.append("  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid and sjcs <> '0') sjrs, ");
		sql.append(" (select count(distinct xh)  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid");
		sql.append("   and kkjs <> '0') kkrs from ");
		sql.append(" xg_rcsw_kqgl_zjsykqjgb a where A.SHZT IS NOT NULL");
		sql.append(") a left join view_njxyzybj_all ");
		sql.append(" b on a.bjdm=b.bjdm left join xqdzb c on a.xq=c.xqdm ) s1 ");
		sql.append("left join xg_xtwh_shztb s2 on s1.id = s2.ywid ");
		sql.append("left join xg_xtwh_spgw s4 on s2.gwid = s4.id where ");
		if (!t.getShzt().equals("dsh")) {
			sql.append(" (s2.shzt<>0 and s2.shzt<>4) ");
		} else {
			sql.append(" (s2.shzt=0  or s2.shzt = 4 ) ");
		}
		sql.append(") s5 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setClass(KqshForm.class);
		super.setKey("id");
		super.setTableName("xg_rcsw_kqgl_zjsykqjgb");
	}

	/** 
	 * @����:��ѯ�ж����������Ա����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-29 ����11:01:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param kqshForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> checkShs(String values, KqshForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s5", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "s5", "xydm", "bjdm");
		String[] value = values.split(",");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( ");
		sql.append("select s1.*,s2.guid shid,s2.shzt shztx,s2.gwid, ");
		sql.append("s2.shr,s2.shyj,s4.mc || '[' ||decode(s2.shzt,'0','δ���','1', ");
		sql.append("'ͨ��','2','��ͨ��','3','�˻�','4','������','5', ");
		sql.append("'�����') || ']' shztmc,s4.gwz, ");
		sql.append("row_number() over(partition by s1.id order by s2.shsj desc) rn ");
		sql.append("from (select a.*, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc, ");
		sql.append(" translate(to_number(a.yf),'0123456789','��һ�����������߰˾�')||'��' toyf, ");
		sql.append("  '��'||translate(to_number(a.zc),'0123456789','��һ�����������߰˾�')||'��' tozc, ");
		sql.append(" round((to_number(cqrs)-to_number(kkrs))/to_number(cqrs)*100,1) cql from ( ");
		sql.append(" select a.*,(select count(distinct xh) from XG_RCSW_KQGL_ZJSYKQXXB t1 ");
		sql.append(" where a.id = t1.kqid and bjcs <> '0') bjrs, (select count(distinct xh)");
		sql.append("  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid and sjcs <> '0') sjrs, ");
		sql.append(" (select count(distinct xh)  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid");
		sql.append("   and kkjs <> '0') kkrs from ");
		sql.append(" xg_rcsw_kqgl_zjsykqjgb a where A.SHZT IS NOT NULL ");
		if(""!=values && values.length()!=0){
			sql.append(" and a.id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(") a left join view_njxyzybj_all ");
		sql.append(" b on a.bjdm=b.bjdm left join xqdzb c on a.xq=c.xqdm ) s1 ");
		sql.append("left join xg_xtwh_shztb s2 on s1.id = s2.ywid ");
		sql.append("left join xg_xtwh_spgw s4 on s2.gwid = s4.id where ");
		sql.append(" (s2.shzt=0  or s2.shzt = 4 ) ");
		sql.append(") s5 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

}
