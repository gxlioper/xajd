package xsgzgl.jxgl.general.jxcjgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;


/**
 * 军训管理-军训考核管理-军训成绩管理
 * @author yeyipin
 * @since 2012.10.13
 */
public class JxglJxcjglDao extends DAO{
	
	
	/**
	 * 获得军训信息(jxid为空取运行中的)
	 * @return
	 */
	public HashMap<String,String> getJxxx(String jxid) {
		String sql = "select * from xg_jxgl_jxxxwhb where jxid = ? ";
		String[] inputValue = new String[]{jxid};
		if(Base.isNull(jxid)){
			sql = "select * from xg_jxgl_jxxxwhb where jxzt = ?";
			inputValue = new String[]{"start"};
		}
		return getMapNotOut(sql, inputValue);
	}
	
	/**
	 * 获得所有的军训信息
	 * @return
	 */
	public List<HashMap<String, String>> getJxxxList() {
		String sql = "select * from xg_jxgl_jxxxwhb order by cjsj";
		String[] inputValue = new String[]{};
		return getListNotOut(sql, inputValue);
	}

	/**
	 * 获得军训等级list
	 * @return
	 */
	public List<HashMap<String, String>> getJxdjList() {
		String sql = "select * from xg_jxgl_jxjzdjb order by to_number(dj) asc";
		return getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 军训成绩查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> jxcjCx(JxglJxcjglForm model, HttpServletRequest request,List<HashMap<String, String>> djList) throws Exception{
		//军训建制高级查询等级sql
		StringBuffer djsql=new StringBuffer();
		StringBuffer djzdSql=new StringBuffer();
		//因为内容是数据库传过来的,所以不会sql注入
		if(djList != null){
			for (int i = 0; i < djList.size(); i++) {
				djsql.append(" func_getjxjzid(jzid,'");
				djsql.append(djList.get(i).get("dm"));
				djsql.append("') ");
				djsql.append(djList.get(i).get("bm"));
				djsql.append(",");
				
				djzdSql.append(" b.");
				djzdSql.append(djList.get(i).get("bm"));
				djzdSql.append(", ");
			}
		}
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","xydm", null); 	//学院用户;	
		
		String[] cjzd = model.getZd().split("!!@@!!");
		String[] colList = new String[] { "xh", "xh", "xm", "nj", "bjmc", "szjzs"};
		colList = unionArray(colList, cjzd);
		String zd = "";
		for(String cjzds:cjzd){
			zd+=","+cjzds;
		}
		StringBuilder sql = new StringBuilder(" select rownum r,a.* from (select a.mdid,b.jxid,");
		sql.append(" b.szjz,"+djzdSql+" b.szjz szjzs, d.*"+zd+",bjmc from xg_jxgl_jxjzmdb a left join (select jzid,connect_by_root(sjid) jxid,"+djsql+" sys_connect_by_path(jzmc,' ') szjz from xg_jxgl_jxjzwhb ");
		sql.append("start with sjid in(select jxid from xg_jxgl_jxxxwhb) connect by sjid = prior jzid) b ");
		sql.append("on a.jzid = b.jzid left join xg_jxgl_jxcjwhb c on a.xh = c.xh left join "+Base.xsxxb+" d on a.xh = d.xh left join VIEW_NJXYZYBJ e on d.bjdm = e.bjdm where b.szjz is not null order by d.bjdm,d.xh)a where 1=1 and jxid = '"+model.getJzid()+"' ");
		// ====================SQL拼装 end================================
   		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);

	}

	
	/**
	 * 获得军训成绩配置数据
	 * @return
	 */
	public List<HashMap<String, String>> getJxcjpz(String jzid) {
		String sql = "select count(1) num from xg_jxgl_jxcjpzb where jxid = ?";
		String[] inputValue = new String[]{jzid};
		String num = getOneRs(sql, inputValue, "num");
		if("0".equalsIgnoreCase(num)){
			sql = "select * from xg_jxgl_jxcjpzb where jxid = 'comm' order by xssx";
			inputValue = new String[]{};
		}else{
			sql = "select * from xg_jxgl_jxcjpzb where jxid = ? order by xssx";
		}
		return getListNotOut(sql, inputValue);
	}

	/**
	 * 获得成绩类别
	 * @param jxcjpz
	 * @return
	 */
	public List<HashMap<String, String>> getCjlb(HashMap<String, String> jxcjpz) {
		String table = jxcjpz.get("source_table");
		String dm = jxcjpz.get("option_dm");
		String mc = jxcjpz.get("option_mc");
		String sql = "select * from "+table+" order by "+dm;
		return getList(sql, new String[]{}, new String[]{dm,mc});
	}

	/**
	 * 军训成绩删除
	 * @param delParams
	 * @return
	 * @throws SQLException 
	 */
	public boolean jxcjDel(List<String[]> delParams) throws SQLException {
		String sql = "delete from xg_jxgl_jxcjwhb where jxid=? and xh = ?";
		int[] result = runBatch(sql, delParams);
		return checkBatchResult(result);
	}

	/**
	 * 军训成绩保存
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean jxcjBc(List<String[]> params,List<HashMap<String,String>> jxcjpzList) throws SQLException {
		String cjzd = "";
		String num = "";
		for(int i =0; i< jxcjpzList.size();i++){
			cjzd+=","+jxcjpzList.get(i).get("zd");
			num+=",?";
		}
		String sql = "insert into xg_jxgl_jxcjwhb(jxid,xh"+cjzd+")values(?,?"+num+")";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}

	/**
	 * 查询军训成绩    用于接口
	 * @return
	 */
	public List<HashMap<String, String>> getJxcjByXhAndJxid(String[] xh,String jxid){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		String[] inputValue = new String[xh.length+1];

		sql.append(" select cjid, jxid, xh, cj1, cj2, cj3, cj4, cj5 ");
		sql.append(" from xg_jxgl_jxcjwhb ");
		sql.append(" where jxid=? ");
		
		inputValue[0]=jxid;
		for (int i = 0; i < xh.length; i++) {
			if(i == 0){
				sql.append(" and ( ");
				sql.append(" xh = ? ");
			}else{
				sql.append(" or xh = ? ");
			}
			
			if(i == (xh.length-1)){
				sql.append(" ) ");
			}
			
			inputValue[(i+1)]=xh[i];
		}

		String[] outputValue = new String[] { "cjid", "jxid", "xh", "cj1", "cj2", "cj3", "cj4", "cj5" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	} 

	/**
	 * 军训管理成绩自定义导出
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> jxcjExportDataCx(JxglJxcjglForm model, HttpServletRequest request,List<HashMap<String, String>> djList) throws Exception{
		//军训建制高级查询等级sql
		StringBuffer djsql=new StringBuffer();
		StringBuffer djzdSql=new StringBuffer();
		//因为内容是数据库传过来的,所以不会sql注入
		if(djList != null){
			for (int i = 0; i < djList.size(); i++) {
				djsql.append(" func_getjxjzid(jzid,'");
				djsql.append(djList.get(i).get("dm"));
				djsql.append("') ");
				djsql.append(djList.get(i).get("bm"));
				djsql.append(",");
				
				djzdSql.append(" b.");
				djzdSql.append(djList.get(i).get("bm"));
				djzdSql.append(", ");
			}
		}
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","xydm", null); 	//学院用户;	
		
		String[] cjzd = model.getZd().split("!!@@!!");
		String[] colList = new String[] { "xh", "xh", "xm", "nj", "bjmc", "szjzs"};
		colList = unionArray(colList, cjzd);
		String zd = "";
		for(String cjzds:cjzd){
			zd+=","+cjzds;
		}//case when length(b.szjz)>12 then substr(b.szjz,0,12)||'...' else b.szjz end
		StringBuilder sql = new StringBuilder(" select rownum r,a.* from (select a.mdid,b.jxid,");
		sql.append(" b.szjz,"+djzdSql+" b.szjz szjzs,d.*"+zd+",bjmc from xg_jxgl_jxjzmdb a left join (select jzid,connect_by_root(sjid) jxid,"+djsql+" sys_connect_by_path(jzmc,' ') szjz from xg_jxgl_jxjzwhb ");
		sql.append("start with sjid in(select jxid from xg_jxgl_jxxxwhb) connect by sjid = prior jzid) b ");
		sql.append("on a.jzid = b.jzid left join xg_jxgl_jxcjwhb c on a.xh = c.xh left join "+Base.xsxxb+" d on a.xh = d.xh left join VIEW_NJXYZYBJ e on d.bjdm = e.bjdm where b.szjz is not null order by d.bjdm,d.xh)a where 1=1 and jxid = '"+model.getJzid()+"' ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforList(sql.toString(), searchTj+searchTjQx, inputV, colList, model);

	}

}
