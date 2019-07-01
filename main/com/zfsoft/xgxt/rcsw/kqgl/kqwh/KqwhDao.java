/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-26 ����05:29:28 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡��������������ģ��
 * @�๦������: ����ά��Dao
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-26 ����05:29:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqwhDao extends SuperDAOImpl<KqwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqwhForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (select a.*,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',a.shzt) shztmc, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc, ");
		sql.append(" translate(to_number(a.yf),'0123456789','��һ�����������߰˾�')||'��' toyf, ");
		sql.append("  '��'||translate(to_number(a.zc),'0123456789','��һ�����������߰˾�')||'��' tozc, ");
		sql.append(" round((to_number(cqrs)-to_number(kkrs))/to_number(cqrs)*100,1) cql from ( ");
		sql.append(" select a.*,(select count(distinct xh) from XG_RCSW_KQGL_ZJSYKQXXB t1 ");
		sql.append(" where a.id = t1.kqid and bjcs <> '0') bjrs, (select count(distinct xh)");
		sql.append("  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid and sjcs <> '0') sjrs, ");
		sql.append(" (select count(distinct xh)  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid");
		sql.append("   and kkjs <> '0') kkrs from ");
		sql.append(" xg_rcsw_kqgl_zjsykqjgb a ) a left join view_njxyzybj_all");
		sql.append(" b on a.bjdm=b.bjdm left join xqdzb c on a.xq=c.xqdm ) s1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}


	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_kqgl_zjsykqjgb");
		super.setKey("id");
		super.setClass(KqwhForm.class);
	}
	
	/**
	 * ��ȡkqwhform
	 */
	public KqwhForm getModel(String id) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.bjmc,c.xqmc,b.xydm,b.zydm, ");
		sql.append(" translate(to_number(a.yf),'0123456789','��һ�����������߰˾�')||'��' toyf, ");
		sql.append("  '��'||translate(to_number(a.zc),'0123456789','��һ�����������߰˾�')||'��' tozc, ");
		sql.append(" round((to_number(cqrs)-to_number(kkrs))/to_number(cqrs)*100,1) cql from ( ");
		sql.append(" select a.*,(select count(distinct xh) from XG_RCSW_KQGL_ZJSYKQXXB t1 ");
		sql.append(" where a.id = t1.kqid and bjcs <> '0') bjrs, (select count(distinct xh)");
		sql.append("  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid and sjcs <> '0') sjrs, ");
		sql.append(" (select count(distinct xh)  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid");
		sql.append("   and kkjs <> '0') kkrs from ");
		sql.append(" xg_rcsw_kqgl_zjsykqjgb a ) a left join view_njxyzybj_all");
		sql.append(" b on a.bjdm=b.bjdm left join xqdzb c on a.xq=c.xqdm ) where id=? ");
		return getModel(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����:��ȡ��ǰѧ�ꡢѧ�ڡ��·�����ܴ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����10:57:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDqZc(){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(zc) zc  from xg_rcsw_kqgl_zjsykqjgb where xn=? ");
		sql.append(" and xq=? and yf = ?");
		return dao.getOneRs(sql.toString(), new String[]{Base.currXn,Base.currXq,
			DateUtils.getMonth()}, "zc");
	}

	/** 
	 * @����:��ȡ����info
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����02:16:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKqinfo(String kqid){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm from XG_RCSW_KQGL_ZJSYKQXXB a left ");
		sql.append(" join view_xsbfxx b on a.xh=b.xh where kqid=? ");
		return dao.getListNotOut(sql.toString(), new String[]{kqid});
	}

	/** 
	 * @����:ɾ��������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����02:22:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delKqInfo(String kqid) throws SQLException {
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[]{kqid});
		String sql = "delete from XG_RCSW_KQGL_ZJSYKQXXB where kqid=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @����:���濼����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����02:27:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveKqInfo(List<String[]> params) throws SQLException {
		String sql = "insert into XG_RCSW_KQGL_ZJSYKQXXB(id,kqid,xh,bjcs,sjcs,kkjs) values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @����:��ѯ�ж�������¼�����ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����07:40:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> isCanSubmit(String values, KqwhForm t, User user) throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String[] value = values.split(",");
		
		sql.append("select * from (select a.*, ");
		sql.append("decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',a.shzt) shztmc, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc from xg_rcsw_kqgl_zjsykqjgb a ");
		sql.append("left join  view_njxyzybj_all b on a.bjdm = b.bjdm left join xqdzb c on a.xq = c.xqdm) s1 ");
		sql.append("where 1=1 and (shzt in ('0','3') or shzt is null) ");
		if(""!=values && values.length()!=0){
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return dao.getListNotOut(sql.toString(), inputV);
	}


	/** 
	 * @����:�ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����08:40:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean submit(String values, KqwhForm t, User user) throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String[] value = values.split(",");
		
		sql.append("update xg_rcsw_kqgl_zjsykqjgb set shzt= ?,splc= ?,jlr=?, jlsj=? where id in ( ");
		sql.append("select id from (select a.*, ");
		sql.append("decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',a.shzt) shztmc, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc from xg_rcsw_kqgl_zjsykqjgb a ");
		sql.append("left join  view_njxyzybj_all b on a.bjdm = b.bjdm left join xqdzb c on a.xq = c.xqdm) s1 ");
		sql.append("where 1=1 and (shzt in ('0','3') or shzt is null)");
		if(""!=values && values.length()!=0){
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(")");
		
		//���Լ��Ĳ����͸߼���ѯ�����ϲ�
		String[] both = (String[]) ArrayUtils.addAll(new String[]{t.getShzt(),t.getSplc(),t.getJlr(),t.getJlsj()}, inputV);
		
		return dao.runUpdate(sql.toString(), both);
		
	}
}
