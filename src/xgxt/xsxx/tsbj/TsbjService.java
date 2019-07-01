package xgxt.xsxx.tsbj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class TsbjService {

	private DAO dao = DAO.getInstance();
	
	/**
	 * 查询在校生
	 */
	public List<String[]> getStudents(TsbjForm model,String query,String[] input,String[] colList) throws Exception{
		
		String zxxs = "and (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null)";//在校生过滤条件
		
		//查询结果集
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,xh,xm,nj,xymc,zymc,bjmc,xz from (select a.xh,a.xm,a.nj,a.xymc,");
		sql.append("a.zymc,a.bjmc,a.xz from view_xsbfxx a where 1=1");
		sql.append(query);
		sql.append(zxxs);
		sql.append(") where 1=1 ");
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), input, colList);
	}
	
	
	/**
	 * 查询特色班级学生
	 */
	public List<String[]> getTsbjStu(TsbjForm model,String query,String[] input,String[] colList) throws Exception{
		
		String zxxs = " (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null)";//在校生过滤条件
		
		//查询结果集
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.tsbjdm||a.xh pkValue,a.tsbjdm,a.tsbjmc,a.xh,a.xm,a.jrsj from xg_view_xsxx_tsbjxs a ")
		   .append(" where exists (select 1 from (select xh from view_xsbfxx where ")
		   .append(zxxs)
		   .append(" ) b where a.xh=b.xh) ")
		   .append(query);
		
		if (StringUtils.isNotNull(model.getTsbjdm())){
			sql.append(" and tsbjdm=?");
			input = StringUtils.joinStrArr(input,new String[]{model.getTsbjdm()});
		}
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), input, colList);
	}
	
	
	
	/**
	 * 查询特色班级列表
	 * @return
	 */
	public List<HashMap<String,String>> getTsbjList(){
		
		String sql = "select tsbjdm,tsbjmc from xg_xsxx_tsbjb order by tsbjdm";
		
		return dao.getListNotOut(sql, new String[]{});
	}
	
	
	
	/**
	 * 特色班级学生
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public boolean saveTsbjStu(TsbjForm model) throws SQLException{
		
		String tsbjXh = model.getTsbjXh();
		String tsbjdm = model.getTsbjdm();
		
		if (StringUtils.isNotNull(tsbjXh) && StringUtils.isNotNull(tsbjdm)){
			String[] xh = tsbjXh.split(",");
			String[] sqlArr = new String[xh.length*2];
			int n = 0;
			
			for (int i = 0 ; i < xh.length ; i++){
				
				String delSQL = "delete from xg_xsxx_tsbjxsb where tsbjdm='"+tsbjdm+"' and xh='"+xh[i]+"'";
				sqlArr[n] = delSQL.toString();
				n++;
				
				StringBuilder sql = new StringBuilder();
				sql.append(" insert into xg_xsxx_tsbjxsb(tsbjdm,xh)")
				   .append(" values ('").append(tsbjdm)
				   .append("','").append(xh[i]).append("')");
				sqlArr[n] = sql.toString();
				n++;
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
		return false;
	}
	
	
	private HashMap<String,Object> getCjsjSql(TsbjForm model){
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		if (StringUtils.isNotNull(model.getCjkssj()) && StringUtils.isNull(model.getCjjssj())) {
			sql.append(" and to_date(cjsj,'yyyy-mm-dd') >= to_date(?,'yyyy-mm-dd')");
			input.add(model.getCjkssj());
		}
		
		if (StringUtils.isNotNull(model.getCjjssj()) && StringUtils.isNull(model.getCjkssj()) ) {
			sql.append(" and to_date(cjsj,'yyyy-mm-dd') <= to_date(?,'yyyy-mm-dd')");
			input.add(model.getCjjssj());
		}
		
		if (StringUtils.isNotNull(model.getCjkssj()) && StringUtils.isNotNull(model.getCjjssj())) {
			sql.append(" and to_date(cjsj,'yyyy-mm-dd') between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')+1");
			input.add(model.getCjkssj());
			input.add(model.getCjjssj());
		}
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql.toString());
		map.put("input", input.toArray(new String[]{}));
		return map;
	}
	
	/**
	 * 特色班级查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsbj(TsbjForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"dis","tsbjdm","tsbjmc","cjrxm","cjsj","tsbjrs"};
		String[] queryLikeArr = new String[]{"tsbjdm","tsbjmc","cjrxm"};
		String[] queryArr = new String[]{};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		HashMap<String,Object> cjsj = getCjsjSql(model);
		
		sql.append(" select tsbjdm,tsbjmc,cjrxm,cjsj,tsbjrs,rownum r,")
		   .append(" case when tsbjrs > 0 then 'disabled' else '' end dis")
		   .append(" from xg_view_xsxx_tsbj where 1=1 ")
		   .append(map.get("sql"))
		   .append(cjsj.get("sql"));
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), StringUtils.joinStrArr((String[])map.get("input"),(String[])cjsj.get("input")), colList);
	}
}
