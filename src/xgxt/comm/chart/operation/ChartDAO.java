package xgxt.comm.chart.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;

public class ChartDAO extends DAO {

	/**
	 * 临时 的-测试jfreeChart数据
	 * @return List<HashMap<String,String>>
	 */
	protected List<HashMap<String,String>> getStudentByXyAndXb(){
		String sql = "select xymc,nvl(xb,'未确定') xb,count(*) count from view_xsjbxx group by xymc,xb order by xymc,xb";
		
		return getList(sql, new String[] {}, new String[] {"xymc","xb","count"});
	}
	
	
	
	/**
	 * 学生信息分类统计公用SQL，查询条件在此处加
	 * @param model
	 * @return String
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	private HashMap<String,Object> getStuTjSql(ChartForm model) throws Exception {
		
		StringBuilder sb = new StringBuilder(); 
		
		// 权限过滤
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sb.append("select * from view_xsxxb where nvl(sfzx,'在校')='在校' ");
		sb.append(searchTj);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("sql", sb.toString());
		map.put("inputV", inputV);
		
		return map;
	}
	
	
	/**
	 * 类别统计。如果统计的类别值为空则命名为“未确定”
	 * @param model
	 * @return List<HashMap<String,String>>
	 * @throws Exception 
	 */
	protected List<HashMap<String,String>> getTjDataAllowNull(ChartForm model) throws Exception{
		
		String tjzd = model.getTjzd();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(")
		   .append(tjzd)
		   .append(",'未确定') key")
		   .append(" ,count(*) value from (")
		   .append(getStuTjSql(model).get("sql"))
		   .append(") group by ")
		   .append(tjzd)
		   .append(" order by ")
		   .append(tjzd);
		
		return getList(sql.toString(), (String[])getStuTjSql(model).get("inputV"), new String[] {"key","value"});
	}
	
	
	/**
	 * 按类别统计。统计的类别值不能为空
	 * @param model
	 * @return List<HashMap<String,String>>
	 * @throws Exception 
	 */
	protected List<HashMap<String,String>> getTjDataNotNull(ChartForm model) throws Exception{
		
		String tjzd = model.getTjzd();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select ")
		   .append(tjzd)
		   .append(" key ,count(*) value from (")
		   .append(getStuTjSql(model).get("sql"))
		   .append(") group by ")
		   .append(tjzd)
		   .append(" order by ")
		   .append(tjzd);
		
		return getList(sql.toString(), (String[])getStuTjSql(model).get("inputV"), new String[] {"key","value"});
	}
	
}
