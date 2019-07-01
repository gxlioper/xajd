package xgxt.comm.chart.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;

public class ChartDAO extends DAO {

	/**
	 * ��ʱ ��-����jfreeChart����
	 * @return List<HashMap<String,String>>
	 */
	protected List<HashMap<String,String>> getStudentByXyAndXb(){
		String sql = "select xymc,nvl(xb,'δȷ��') xb,count(*) count from view_xsjbxx group by xymc,xb order by xymc,xb";
		
		return getList(sql, new String[] {}, new String[] {"xymc","xb","count"});
	}
	
	
	
	/**
	 * ѧ����Ϣ����ͳ�ƹ���SQL����ѯ�����ڴ˴���
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
		
		// Ȩ�޹���
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sb.append("select * from view_xsxxb where nvl(sfzx,'��У')='��У' ");
		sb.append(searchTj);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("sql", sb.toString());
		map.put("inputV", inputV);
		
		return map;
	}
	
	
	/**
	 * ���ͳ�ơ����ͳ�Ƶ����ֵΪ��������Ϊ��δȷ����
	 * @param model
	 * @return List<HashMap<String,String>>
	 * @throws Exception 
	 */
	protected List<HashMap<String,String>> getTjDataAllowNull(ChartForm model) throws Exception{
		
		String tjzd = model.getTjzd();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(")
		   .append(tjzd)
		   .append(",'δȷ��') key")
		   .append(" ,count(*) value from (")
		   .append(getStuTjSql(model).get("sql"))
		   .append(") group by ")
		   .append(tjzd)
		   .append(" order by ")
		   .append(tjzd);
		
		return getList(sql.toString(), (String[])getStuTjSql(model).get("inputV"), new String[] {"key","value"});
	}
	
	
	/**
	 * �����ͳ�ơ�ͳ�Ƶ����ֵ����Ϊ��
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
