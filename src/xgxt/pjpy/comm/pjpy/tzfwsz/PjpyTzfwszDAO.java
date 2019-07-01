package xgxt.pjpy.comm.pjpy.tzfwsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;

public class PjpyTzfwszDAO extends DAO {

	/**
	 * ɾ����Ŀ�ĵ�����Χ����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	protected boolean delTzfwsz(String xmdm) throws Exception{
		
		String sql = "delete from xg_pjpy_pjfwtzb where xmdm = ?";
		
		return runUpdate(sql, new String[]{xmdm});
	}
	
	
	/**
	 * ������Ŀ������Χ����
	 * @param xmdm
	 * @param tzfwxm
	 * @return
	 * @throws SQLException
	 */
	protected boolean saveTzfwsz(String xmdm,String[] tzfwxm) throws SQLException{
		
		String[] sqlArr = new String[tzfwxm.length];
		
		for (int i = 0 ; i < tzfwxm.length ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into xg_pjpy_pjfwtzb(xmdm,tzxmdm) values ('")
			   .append(xmdm)
			   .append("','")
			   .append(tzfwxm[i])
			   .append("')");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}


	
	/**
	 * ����ĳ��Ŀ�ĵ�����Χ��Ŀ
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 */
	protected String[] findTzfwxm(String xmdm) throws SQLException {

		String sql = "select tzxmdm from xg_pjpy_pjfwtzb where xmdm = ?";
		
		return getArray(sql, new String[]{xmdm}, "tzxmdm");
		
	}
	
	
	
	/**
	 * ��Ŀ������Χ��ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected List<HashMap<String,String>> findTzfwxmList(PjpyTzfwszForm model) throws Exception{
		String[] queryArr = new String[]{"pjxn","pjxq","pjnd","xmlx","xmxz","xmfw"};
		String[] queryLikeArr = new String[]{"xmmc"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		Pages pages = model.getPages();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm,xmmc,tzxmmc,rownum r from (")
		   .append(" select xmdm,(select xmmc from xg_pjpy_pjxmwhb where xmdm=t2.xmdm and rownum=1) xmmc,")
		   .append(" ltrim(max(sys_connect_by_path(tzxmmc, ',')), ',') as tzxmmc")
		   .append(" from (select xmdm,tzxmmc,rnFirst,")
		   .append(" lead(rnFirst) over(partition by xmdm order by rnFirst) rnNext")
		   .append(" from (select a.xmdm,")
		   .append(" (select xmmc from xg_pjpy_pjxmwhb where xmdm=a.tzxmdm and rownum=1) tzxmmc,")
		   .append(" row_number() over(order by a.xmdm, a.tzxmdm desc) rnFirst")
		   .append(" from (select a.* from xg_pjpy_pjfwtzb a where exists ")
		   .append("(select 1 from ( select * from xg_pjpy_pjxmwhb a where exists ")
		   .append("(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd)) b where a.xmdm=b.xmdm ")
		   .append(map.get("sql"))
		   .append(") and exists  (select 1 from ( select * from xg_pjpy_pjxmwhb a where exists ")
		   .append("(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd)) b where a.tzxmdm=b.xmdm)")
		   .append(" ) a) t1) t2  start with rnNext is null")
		   .append(" connect by rnNext = prior rnFirst")
		   .append(" group by xmdm )");
		
		
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(getOneRs(maxRecordSql, (String[])map.get("input"), "count")));
		
		StringBuilder querySql = new StringBuilder();
		querySql.append("select xmdm,xmmc,tzxmmc from (")
		   .append(sql)
		   .append(" where rownum <=")
		   .append((pages.getStart() + pages.getPageSize()))
		   .append(") where r > ")
		   .append(pages.getStart());

		return getListNotOut(querySql.toString(), (String[])map.get("input"));
	}
	
	
	/**
	 * ɾ��ָ����Ŀ�ĵ�����Χ����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public boolean delTzszByXmdm(String[] xmdm) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_pjpy_pjfwtzb where xmdm in (");
		
		for (int i = 0 ; i < xmdm.length ; i++){
			sql.append("'")
			   .append(xmdm[i])
			   .append("'");
			
			if (i != xmdm.length-1){
				sql.append(",");
			}
		}
		
		sql.append(")");
		return runUpdate(sql.toString(), new String[]{});
	}
}
