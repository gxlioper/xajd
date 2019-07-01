package xgxt.pjpy.comm.pjpy.jdsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;

public class PjpyJdszDAO extends DAO {

	
	
	/**
	 * 删除项目兼得设置
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	protected boolean delJdsz(String xmdm) throws Exception{
		
		String sql = "delete from xg_pjpy_jdszb where xmdm = ?";
		
		return runUpdate(sql, new String[]{xmdm});
	}
	
	
	
	/**
	 * 批量保存兼得设置
	 * @param xmdm
	 * @param fjddm
	 * @return
	 * @throws SQLException
	 */
	protected boolean saveJdsz(String xmdm,String[] fjddm) throws SQLException{
		
		String[] sqlArr = new String[fjddm.length*3];
		int n = 0;
		
		for (int i = 0 ; i < fjddm.length ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into xg_pjpy_jdszb(xmdm,fjddm) values ('")
			   .append(xmdm)
			   .append("','")
			   .append(fjddm[i])
			   .append("')");
			
			sqlArr[n++] = sql.toString();
			sqlArr[n++] = "delete from xg_pjpy_jdszb where xmdm='"+fjddm[i]+"' and fjddm='"+xmdm+"'";
			sqlArr[n++] = "insert into xg_pjpy_jdszb(xmdm,fjddm) values ('"+fjddm[i]+"','"+xmdm+"')";
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	/**
	 * 查找非兼得项目代码
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 */
	protected String[] findFjdxm(String xmdm) throws SQLException{
		
		String sql = "select fjddm from xg_pjpy_jdszb where xmdm = ?";
		
		return getArray(sql, new String[]{xmdm}, "fjddm");
	}
	
	
	/**
	 * 非兼得项目设置查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected List<HashMap<String,String>> findJdszList(PjpyJdszForm model) throws Exception{
		String[] queryArr = new String[]{"pjxn","pjxq","pjnd","xmlx","xmxz","xmfw"};
		String[] queryLikeArr = new String[]{"xmmc"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		Pages pages = model.getPages();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm,xmmc,fjdxmmc,rownum r from (")
		   .append(" select xmdm,(select xmmc from xg_pjpy_pjxmwhb where xmdm=t2.xmdm and rownum=1) xmmc,")
		   .append(" ltrim(max(sys_connect_by_path(fjdxmmc, ',')), ',') as fjdxmmc")
		   .append(" from (select xmdm,fjdxmmc,rnFirst,")
		   .append(" lead(rnFirst) over(partition by xmdm order by rnFirst) rnNext")
		   .append(" from (select a.xmdm,")
		   .append(" (select xmmc from xg_pjpy_pjxmwhb where xmdm=a.fjddm and rownum=1) fjdxmmc,")
		   .append(" row_number() over(order by a.xmdm, a.fjddm desc) rnFirst")
		   .append(" from (select a.* from xg_pjpy_jdszb a where exists (select 1 from ")
		   .append("(select * from xg_pjpy_pjxmwhb a where exists ")
		   .append("(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd)")
		   .append(") b where a.xmdm=b.xmdm ")
		   .append(map.get("sql"))
		   .append(") and exists (select 1 from ")
		   .append("(select * from xg_pjpy_pjxmwhb a where exists ")
		   .append("(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd)")
		   .append(") b where a.fjddm=b.xmdm )")
		   
		   .append("  ) a) t1) t2  start with rnNext is null")
		   .append(" connect by rnNext = prior rnFirst")
		   .append(" group by xmdm )");
		
		
		String maxRecordSql = "select count(1) count "+ sql.substring(sql.indexOf("from"));
		pages.setMaxRecord(Integer.parseInt(getOneRs(maxRecordSql, (String[])map.get("input"), "count")));
		
		StringBuilder querySql = new StringBuilder();
		querySql.append("select xmdm,xmmc,fjdxmmc from (")
		   .append(sql)
		   .append(" where rownum <=")
		   .append((pages.getStart() + pages.getPageSize()))
		   .append(") where r > ")
		   .append(pages.getStart());

		return getListNotOut(querySql.toString(), (String[])map.get("input"));
	}
	
	
	/**
	 * 根据项目代码删除兼得设置
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public boolean delJdszByXmdm(String[] xmdm) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_pjpy_jdszb where xmdm in (");
		
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
