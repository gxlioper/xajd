package xsgzgl.xsxx.general.qzxgmdsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class QzxgmdszDao extends DAO{
	DAO dao = DAO.getInstance();	
	/**
	 * ǿ���޸� ��ѯ
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getQzxgmdList(QzxgmdszForm myForm,QzxgmdszForm model,User user) 
	throws IllegalArgumentException, SecurityException, 
	IllegalAccessException, InvocationTargetException, 
	NoSuchMethodException{
	List<String> colList = new ArrayList<String>();
	String[] colArr = new String[] {"pk","xh","xm","nj","xymc","zymc","bjmc","xgztmc", "shzt"};
	
	// Ȩ�޹���
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
			"xydm", "bjdm");
	
	// �߼���ѯ����
	String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
	String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	String query = " where 1 = 1 ";
	query += searchTj;
	
	StringBuilder sql = new StringBuilder();
	sql.append("select rownum r,a.* from (");
	sql.append("select a.*,case when b.xgzt is null then 'δ����' when b.xgzt ='wxg' then 'δ�޸�' else '���޸�' end xgztmc," +
				" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt,(case when (c.shjg='wsh' or c.shjg='0') then 'δ���' when (c.shjg='tg'  or c.shjg='1') then 'ͨ��' when (c.shjg='shz'  or c.shjg='5') then '�����' when (c.shjg='th' or c.shjg='3') then '�˻�' else '' end) shzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc   from view_xsxxcxjg where nvl(sfzx, '��У') = '��У') a " +
				" left join xg_xsxx_xxxgryb b on a.xh = b.xh left join (select a.xh,a.xgsj,a.shjg from xg_xsxx_xxxgsqb a,(select xh,max(xgsj) xgsj from xg_xsxx_xxxgsqb where shjg != '0' group by xh) b where a.xh=b.xh and a.xgsj=b.xgsj) c on a.xh=c.xh");
	sql.append("  order by nj desc nulls last,a.xydm, a.zydm, a.bjdm, a.xh) a ");
	sql.append(query);
	
	sql.append(searchTjByUser);
	ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
	.commonPageQuery(myForm.getPages(), sql.toString(), inputV, dao
		.unionArray(colArr, colList.toArray(new String[] {})));
	return list;
	}
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @����:ǿ���޸�����������ѯ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-25 ����02:04:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param model
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQzxgmdListForDc(QzxgmdszForm t,User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException 
	{
	t.getPages().setPageSize(Integer.MAX_VALUE);
	String searchTj = SearchService.getSearchTj(t.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
	String[] inputV = SearchService.getTjInput(t.getSearchModel());
	StringBuilder sql = new StringBuilder();
	sql.append("select rownum r,a.* from (");
	sql.append("select a.*,case when b.xgzt is null then 'δ����' when b.xgzt ='wxg' then 'δ�޸�' else '���޸�' end xgztmc," +
				" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt,(case when (c.shjg='wsh' or c.shjg='0') then 'δ���' when (c.shjg='tg'  or c.shjg='1') then 'ͨ��' when (c.shjg='shz'  or c.shjg='5') then '�����' when (c.shjg='th' or c.shjg='5') then '�˻�' else '' end) shzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc   from view_xsxxcxjg where nvl(sfzx, '��У') = '��У') a " +
				" left join xg_xsxx_xxxgryb b on a.xh = b.xh left join (select a.xh,a.xgsj,a.shjg from xg_xsxx_xxxgsqb a,(select xh,max(xgsj) xgsj from xg_xsxx_xxxgsqb group by xh) b where a.xh=b.xh and a.xgsj=b.xgsj) c on a.xh=c.xh");
	sql.append("  order by nj desc nulls last,a.xydm, a.zydm, a.bjdm, a.xh) a ");
	sql.append(" where 1=1  ");
	sql.append(searchTjByUser);
	sql.append(searchTj);
	return CommonQueryDAO.commonPageQueryForExportMap(t.getPages(),sql.toString(),inputV);
	}
	
	/**
	 * 
	 * ����Ƿ���޸Ļ���ɾ��
	 * 
	 * @author xucy
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public String checkQzxg(QzxgmdszForm model,User user) throws Exception {
		String pk[] = model.getPrimarykey_checkVal();
		String message = "";
		if (null != pk && pk.length > 0&& !"".equals(pk[0])) {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(a.xh) num from (select * from view_xsxxb a where a.xh in( ");
			for (int i = 0; i < pk.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("'"+pk[i]+"'");
			}
			sql.append(" ) ");
			sql.append(" and exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg='wsh' or b.shjg = '5' or b.shjg='0'))) a ");
			String num = dao.getOneRs(sql.toString(), new String[]{}, "num");
			message="��ǰ��ѡ��"+pk.length+"��ѧ��,������"+num+"��ѧ����Ϣ��������л�δ��ˡ����β�����ѧ����Ϊ"+(Integer.valueOf(pk.length)-Integer.valueOf(num))+"��";
		}else{
			// Ȩ�޹���
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
			
			// �߼���ѯ����
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String query = " where 1 = 1 ";
			query += searchTj;
			
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) num from (select a.* from (");
			sql.append("select a.*,case when b.xgzt is null then 'δ����' when b.xgzt ='wxg' then 'δ�޸�' else '���޸�' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '��У') = '��У') a " +
						" left join xg_xsxx_xxxgryb b on a.xh = b.xh");
			sql.append(") a )");
			sql.append(query);
			sql.append(searchTjByUser);
			
			String totalnum = dao.getOneRs(sql.toString(), inputV, "num");//����
			
			StringBuilder shzsql = new StringBuilder();
			shzsql.append("select count(*) num from (select a.* from (");
			shzsql.append("select a.*,case when b.xgzt is null then 'δ����' when b.xgzt ='wxg' then 'δ�޸�' else '���޸�' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '��У') = '��У') a " +
						" left join xg_xsxx_xxxgryb b on a.xh = b.xh");
			shzsql.append(") a where exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg='wsh' or b.shjg = '5' or b.shjg='0')))");
			shzsql.append(query);
			shzsql.append(searchTjByUser);
			
			String shznum = dao.getOneRs(shzsql.toString(), inputV, "num");
			
			message="��ǰ��ѡ��"+totalnum+"��ѧ��,������"+shznum+"��ѧ����Ϣ��������л�δ��ˡ����β�����ѧ����Ϊ"+(Integer.valueOf(totalnum)-(Integer.valueOf(shznum)))+"��";
		}
		return message;
	}
	
	/**
	 * 
	 * ɾ��
	 * 
	 * @author xucy
	 * @throws Exception 
	 */
	public boolean qxQzxg(QzxgmdszForm model,User user) throws Exception {
		boolean flag=true;
		String pk[] = model.getPrimarykey_checkVal();
		if (null != pk && pk.length > 0&& !"".equals(pk[0])) {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from xg_xsxx_xxxgryb  a where exists (select 1 from ( select a.xh from view_xsxxb a where a.xh in( ");
			for (int i = 0; i < pk.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("'"+pk[i]+"'");
			}
			sql.append(" ) ");
			sql.append(" and not exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg = 'wsh' or b.shjg = '5' or b.shjg='0'))) b where a.xh = b.xh) ");
			flag = dao.runUpdate(sql.toString(), new String[]{});
		}else{
			// Ȩ�޹���
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
			
			// �߼���ѯ����
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String query = " where 1 = 1 and a.xh=b.xh ";
			query += searchTj;
			
			StringBuilder shzsql = new StringBuilder();
			shzsql.append(" delete from xg_xsxx_xxxgryb a where exists (select 1 from (select a.*  from (");
			shzsql.append("select a.*,case when b.xgzt is null then 'δ����' when b.xgzt ='wxg' then 'δ�޸�' else '���޸�' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '��У') = '��У') a " +
						" left join xg_xsxx_xxxgryb b on a.xh = b.xh");
			shzsql.append(") a where not exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg = 'wsh' or b.shjg = '5' or b.shjg='0')) )b");
			shzsql.append(query);
			shzsql.append(searchTjByUser);
			shzsql.append(")");
			flag = dao.runUpdate(shzsql.toString(), inputV);
		}
		return flag;
	}
	
	/**
	 * 
	 * ����Ƿ���޸Ļ���ɾ��
	 * 
	 * @author xucy
	 * @throws Exception 
	 */
	public boolean szQzxg(QzxgmdszForm model,User user) throws Exception {
		boolean flag=true;
		String pk[] = model.getPrimarykey_checkVal();
		if (null != pk && pk.length > 0&& !"".equals(pk[0])) {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_xsxx_xxxgryb(xh,xgzt) select a.xh,'wxg' xgzt from view_xsxxb a where a.xh in( ");
			for (int i = 0; i < pk.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("'"+pk[i]+"'");
			}
			sql.append(" ) ");
			sql.append(" and not exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg = 'wsh' or b.shjg = '5' or b.shjg='0')) ");
			flag = dao.runUpdate(sql.toString(), new String[]{});
		}else{
			// Ȩ�޹���
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
			
			// �߼���ѯ����
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String query = " where 1 = 1 ";
			query += searchTj;
			
			StringBuilder shzsql = new StringBuilder();
			shzsql.append(" insert into xg_xsxx_xxxgryb(xh,xgzt) select xh,'wxg' xgzt from ( select a.* from (");
			shzsql.append("select a.*,case when b.xgzt is null then 'δ����' when b.xgzt ='wxg' then 'δ�޸�' else '���޸�' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '��У') = '��У') a " +
						" left join xg_xsxx_xxxgryb b on a.xh = b.xh");
			shzsql.append(") a where not exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg = 'wsh' or b.shjg = '5' or b.shjg='0')))");
			shzsql.append(query);
			shzsql.append(searchTjByUser);
		
			flag = dao.runUpdate(shzsql.toString(), inputV);
		}
		return flag;
	}
	
	/**
	 * �ж�ѧ���Ƿ�ǿ���޸�
	 * @param xh
	 * @return
	 */
	public boolean Sfqzxg(String xh){
		boolean flag = false;
		String sql = "select xh from xg_xsxx_xxxgryb  where xh=? and xgzt = 'wxg'";
		HashMap<String, String> map = dao.getMapNotOut(sql, new String[]{xh});
		if(!Base.isNull(map.get("xh"))){//����δ�޸ĵļ�¼
			sql = "select xh,shjg from xg_xsxx_xxxgsqb where xh=? order by xgsj desc";
			map = dao.getMapNotOut(sql, new String[]{xh});
			if(!Base.isNull(map.get("shjg")) && (map.get("shjg").equals("th") || map.get("shjg").equals("3"))){//Ϊ���˻�״̬
				flag=true;
			}else{
				sql = "select a.kgzt from xg_xsxx_xxxgcsszb a";
				String kgzt =  dao.getOneRs(sql, new String[]{}, "kgzt");
				if("".equals(kgzt)){
					flag=true;
				}else{
					flag = "y".equals(kgzt)?true:false;
				}
			}			
		}
		return flag;
	}
	
	/**
	 * �޸�ѧ���Ƿ�ǿ���޸�״̬
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public boolean xgQzxgzt(String xh) throws Exception{
		String sql = "update xg_xsxx_xxxgryb a set a.xgzt = 'yxg' where xh=?";
		return dao.runUpdate(sql, new String[]{xh});
	}
}
