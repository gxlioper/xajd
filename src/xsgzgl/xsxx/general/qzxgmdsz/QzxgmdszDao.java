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
	 * 强制修改 查询
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
	
	// 权限过滤
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
			"xydm", "bjdm");
	
	// 高级查询条件
	String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
	String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	String query = " where 1 = 1 ";
	query += searchTj;
	
	StringBuilder sql = new StringBuilder();
	sql.append("select rownum r,a.* from (");
	sql.append("select a.*,case when b.xgzt is null then '未分配' when b.xgzt ='wxg' then '未修改' else '已修改' end xgztmc," +
				" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt,(case when (c.shjg='wsh' or c.shjg='0') then '未审核' when (c.shjg='tg'  or c.shjg='1') then '通过' when (c.shjg='shz'  or c.shjg='5') then '审核中' when (c.shjg='th' or c.shjg='3') then '退回' else '' end) shzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc   from view_xsxxcxjg where nvl(sfzx, '在校') = '在校') a " +
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
	 * @描述:强制修改名单导出查询列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-25 下午02:04:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param model
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> 返回类型 
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
	sql.append("select a.*,case when b.xgzt is null then '未分配' when b.xgzt ='wxg' then '未修改' else '已修改' end xgztmc," +
				" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt,(case when (c.shjg='wsh' or c.shjg='0') then '未审核' when (c.shjg='tg'  or c.shjg='1') then '通过' when (c.shjg='shz'  or c.shjg='5') then '审核中' when (c.shjg='th' or c.shjg='5') then '退回' else '' end) shzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc   from view_xsxxcxjg where nvl(sfzx, '在校') = '在校') a " +
				" left join xg_xsxx_xxxgryb b on a.xh = b.xh left join (select a.xh,a.xgsj,a.shjg from xg_xsxx_xxxgsqb a,(select xh,max(xgsj) xgsj from xg_xsxx_xxxgsqb group by xh) b where a.xh=b.xh and a.xgsj=b.xgsj) c on a.xh=c.xh");
	sql.append("  order by nj desc nulls last,a.xydm, a.zydm, a.bjdm, a.xh) a ");
	sql.append(" where 1=1  ");
	sql.append(searchTjByUser);
	sql.append(searchTj);
	return CommonQueryDAO.commonPageQueryForExportMap(t.getPages(),sql.toString(),inputV);
	}
	
	/**
	 * 
	 * 检测是否可修改或者删除
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
			message="当前共选择"+pk.length+"个学生,其中有"+num+"个学生信息处于审核中或未审核。本次操作的学生数为"+(Integer.valueOf(pk.length)-Integer.valueOf(num))+"人";
		}else{
			// 权限过滤
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
			
			// 高级查询条件
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String query = " where 1 = 1 ";
			query += searchTj;
			
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) num from (select a.* from (");
			sql.append("select a.*,case when b.xgzt is null then '未分配' when b.xgzt ='wxg' then '未修改' else '已修改' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '在校') = '在校') a " +
						" left join xg_xsxx_xxxgryb b on a.xh = b.xh");
			sql.append(") a )");
			sql.append(query);
			sql.append(searchTjByUser);
			
			String totalnum = dao.getOneRs(sql.toString(), inputV, "num");//总数
			
			StringBuilder shzsql = new StringBuilder();
			shzsql.append("select count(*) num from (select a.* from (");
			shzsql.append("select a.*,case when b.xgzt is null then '未分配' when b.xgzt ='wxg' then '未修改' else '已修改' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '在校') = '在校') a " +
						" left join xg_xsxx_xxxgryb b on a.xh = b.xh");
			shzsql.append(") a where exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg='wsh' or b.shjg = '5' or b.shjg='0')))");
			shzsql.append(query);
			shzsql.append(searchTjByUser);
			
			String shznum = dao.getOneRs(shzsql.toString(), inputV, "num");
			
			message="当前共选择"+totalnum+"个学生,其中有"+shznum+"个学生信息处于审核中或未审核。本次操作的学生数为"+(Integer.valueOf(totalnum)-(Integer.valueOf(shznum)))+"人";
		}
		return message;
	}
	
	/**
	 * 
	 * 删除
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
			// 权限过滤
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
			
			// 高级查询条件
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String query = " where 1 = 1 and a.xh=b.xh ";
			query += searchTj;
			
			StringBuilder shzsql = new StringBuilder();
			shzsql.append(" delete from xg_xsxx_xxxgryb a where exists (select 1 from (select a.*  from (");
			shzsql.append("select a.*,case when b.xgzt is null then '未分配' when b.xgzt ='wxg' then '未修改' else '已修改' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '在校') = '在校') a " +
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
	 * 检测是否可修改或者删除
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
			// 权限过滤
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
			
			// 高级查询条件
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String query = " where 1 = 1 ";
			query += searchTj;
			
			StringBuilder shzsql = new StringBuilder();
			shzsql.append(" insert into xg_xsxx_xxxgryb(xh,xgzt) select xh,'wxg' xgzt from ( select a.* from (");
			shzsql.append("select a.*,case when b.xgzt is null then '未分配' when b.xgzt ='wxg' then '未修改' else '已修改' end xgztmc," +
						" case when b.xgzt is null then 'wfp' else b.xgzt end qzxgzt from (select xh pk, xh, xm, nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_xsxxb where nvl(sfzx, '在校') = '在校') a " +
						" left join xg_xsxx_xxxgryb b on a.xh = b.xh");
			shzsql.append(") a where not exists (select 1 from xg_xsxx_xxxgsqb  b where a.xh = b.xh and  (b.shjg = 'shz' or b.shjg = 'wsh' or b.shjg = '5' or b.shjg='0')))");
			shzsql.append(query);
			shzsql.append(searchTjByUser);
		
			flag = dao.runUpdate(shzsql.toString(), inputV);
		}
		return flag;
	}
	
	/**
	 * 判断学生是否强制修改
	 * @param xh
	 * @return
	 */
	public boolean Sfqzxg(String xh){
		boolean flag = false;
		String sql = "select xh from xg_xsxx_xxxgryb  where xh=? and xgzt = 'wxg'";
		HashMap<String, String> map = dao.getMapNotOut(sql, new String[]{xh});
		if(!Base.isNull(map.get("xh"))){//包含未修改的记录
			sql = "select xh,shjg from xg_xsxx_xxxgsqb where xh=? order by xgsj desc";
			map = dao.getMapNotOut(sql, new String[]{xh});
			if(!Base.isNull(map.get("shjg")) && (map.get("shjg").equals("th") || map.get("shjg").equals("3"))){//为已退回状态
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
	 * 修改学生是否强制修改状态
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public boolean xgQzxgzt(String xh) throws Exception{
		String sql = "update xg_xsxx_xxxgryb a set a.xgzt = 'yxg' where xh=?";
		return dao.runUpdate(sql, new String[]{xh});
	}
}
