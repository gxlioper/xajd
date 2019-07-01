package xgxt.jygl.tables;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;

public class JyglTablesDAO extends DAO {

	/**
	 * 查询学生基本信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStudents(JyglTablesForm model,String query,String[] input) throws Exception{
		
		String[] colList = new String[]{"xh","xm","nj","xymc","zymc","bjmc"};
		
		//查询结果集
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,")
		   .append(" a.xz,rownum r from view_xsjbxx a where 1=1 ")
		   .append(query);
		
		return CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), input, colList);
	}
	
	
	/**
	 * 查询课程列表
	 * @return
	 */
	public List<HashMap<String,String>> getKcList(){
		
		String sql = "select a.xkkh,a.kcmc from cjb a where exists ( select 1 from view_xsjbxx b where a.xh = b.xh ) group by xkkh,kcmc";
		
		return getListNotOut(sql, new String[]{});
	}
	

	/**
	 * 查询课程列表
	 * @return
	 */
	public List<HashMap<String,String>> getKcListByXh(String xh){
		
		String sql = "select xkkh,kcmc from cjb where xh = '"+xh+"' group by xkkh,kcmc ";
		
		return getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 查询课程列表
	 * @return
	 */
	public List<HashMap<String,String>> getKcListByBjdm(String bjdm){
		
		String sql = "select a.xkkh,a.kcmc from cjb a where exists ( select 1 from view_xsjbxx b where a.xh = b.xh and bjdm = '"+bjdm+"' ) group by xkkh,kcmc";
		
		return getListNotOut(sql, new String[]{});
	}
	
	
	
	/**
	 * 打量设置打印课程
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePlszDykc(JyglTablesForm model) throws Exception{
		
		String[] xkkh = model.getXkkh();
	    String[] sqlArr = new String[xkkh.length];
	    
	    for (int i = 0 ; i < xkkh.length ; i++){
	    	
	    	StringBuilder sql = new StringBuilder();
	    	
	    	sql.append("insert into xg_jygl_njjs_dykc select xh,'")
	    	   .append(xkkh[i])
	    	   .append("' from view_xsjbxx where bjdm='")
	    	   .append(model.getBjdm())
	    	   .append("'");
	    	
	    	sqlArr[i] = sql.toString();
	    }
	    
	    int[] result = runBatch(sqlArr);
	    return checkBatch(result);
	}
	
	
	/**
	 * 根据班级代码删除某班设置的打印课程
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public boolean delDykcByBjdm(String  bjdm) throws Exception{
		String sql = "delete from xg_jygl_njjs_dykc a where exists (select 1 from view_xsjbxx b where b.bjdm=? and a.xh=b.xh)";
		
		return runUpdate(sql, new String[]{bjdm});
	}
	
	
	
	/**
	 * 单个设置打印课程
	 * @param xh
	 * @param xkkh
	 * @return
	 * @throws SQLException
	 */
	public boolean saveDgszDykc(String xh,String[] xkkh) throws SQLException{
		
		String[] sqlArr = new String[xkkh.length];
		
		for (int i = 0 ; i < xkkh.length ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append(" insert into xg_jygl_njjs_dykc (xh,xkkh) values ('")
			   .append(xh)
			   .append("','")
			   .append(xkkh[i])
			   .append("')");
			sqlArr[i] = sql.toString();
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	
	/**
	 * 根据学号删除设置的打印课程
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean delDykcByXh(String  xh) throws Exception{
		String sql = "delete from xg_jygl_njjs_dykc a where a.xh=?";
		
		return runUpdate(sql, new String[]{xh});
	}
	
	
	/**
	 * 根据学号查询学生所设置的打印课程对应的课程名称、成绩
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXscjByXh(String xh){
		
		String sql = "select a.xkkh,nvl((select kcmc from cjb where xkkh=a.xkkh and rownum=1),'') kcmc," +
				"nvl(b.cj,'') cj from xg_jygl_njjs_dykc a left join view_zhhcjb b on a.xkkh=b.xkkh and a.xh=b.xh  where a.xh=?";
		
		return getListNotOut(sql, new String[]{xh});
	}
	
	
	/**
	 * 查询学生职务
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	public String[] getXszwByXh(String xh) throws SQLException{
		
		String sql = "select a.xh,a.bjgbdm,b.bjgbmc from sxjy_bjgbxxb a,sxjy_bjgbzlb b where a.bjgbdm=b.bjgbdm and a.xh=?";
		
		return getArray(sql, new String[]{xh}, "bjgbmc");
	}
	
}
