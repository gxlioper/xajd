package xsgzgl.jxgl.general.jxbxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class JxglJxbxglDao extends DAO{

	
	
	/**
	 * 军训表现查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> jxbxCx(JxglJxbxglForm model) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "pkValue", "jxmc", "bxlbmc", "jtbxmc", "hdrys" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from view_xg_jxgl_jxbxgl a where 1=1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, colList, model);
	}

	
	/**
	 * 获得军训表现信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxbx(JxglJxbxglForm model) {
		String sql = "select * from view_xg_jxgl_jxbxgl where pkValue = ?";
		String[] inputValue = new String[]{model.getPkValue()};
		return getMapNotOut(sql, inputValue);
	}


	
	/**
	 * 获得军训等级list
	 * @return
	 */
	public List<HashMap<String, String>> getJxdjList() {
		String sql = "select * from xg_jxgl_jxjzdjb order by to_number(dj)";
		return getListNotOut(sql, new String[]{});
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
			sql = "select * from xg_jxgl_jxcjpzb where jxid = 'comm'";
			inputValue = new String[]{};
		}else{
			sql = "select * from xg_jxgl_jxcjpzb where jxid = ?";
		}
		return getListNotOut(sql, inputValue);
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> bxmdCx(JxglJxbxglForm model, HttpServletRequest request,List<HashMap<String, String>> djList) throws Exception{
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
		String[] colList = new String[] { "xh", "xh", "xm", "xb", "nj", "xymc","zymc","bjmc","szjzs","sfhd"};
		StringBuilder sql = new StringBuilder(" select rownum r,a.* from (select a.mdid,c.*,");
		sql.append("b.szjz,"+djzdSql+" case when length(b.szjz)>12 then substr(b.szjz,0,12)||'...' else b.szjz end szjzs, case when (select count(1) from xg_jxgl_jxbxwhb where a.xh = xh and jxid = '");
		sql.append(model.getJxid()+"' and bxlb = '"+model.getBxlb()+"' and jtbx = '");
		sql.append(model.getJtbx()+"') = 0 then '否' else '是' end as sfhd from xg_jxgl_jxjzmdb a ");
		sql.append(" left join (select jzid,"+djsql+" sys_connect_by_path(jzmc,' ') szjz from xg_jxgl_jxjzwhb start with ");
		sql.append("sjid = '"+model.getJxid()+"' connect by sjid = prior jzid) b on a.jzid = b.jzid ");
		sql.append(" left join (select a.xh, a.xm,a.nj,a.bjdm, a.xb,b.bjmc,b.xymc, b.zymc from xsxxb a left join view_njxyzybj_all b on a.bjdm = b.bjdm) c on a.xh = c.xh where b.szjz is not null ");
		sql.append(" order by c.bjdm,c.xh)a where 1=1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);

	}


	/**
	 * 表现名单增加
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean bxmdZj(List<String[]> params) throws Exception {
		String sql = "insert into xg_jxgl_jxbxwhb(jxid,bxlb,jtbx,xh)values(?,?,?,?)";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}
	
	
	/**
	 * 表现名单删除
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean bxmdSc(List<String[]> params) throws Exception {
		String sql = "delete from xg_jxgl_jxbxwhb where jxid = ? and bxlb = ? and jtbx = ? and xh = ? ";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}
	
	/**
	 * 查询军训表现   用于接口
	 * @return
	 */
	public List<HashMap<String, String>> getJxbxByXhAndJxid(String[] xh,String jxid){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		String[] inputValue = new String[xh.length+1];

		sql.append(" select bxid, xh, bxlb, jtbx, jxid from xg_jxgl_jxbxwhb ");
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

		String[] outputValue = new String[] { "bxid", "xh", "bxlb", "jtbx", "jxid" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	} 

	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getAllbxmd(JxglJxbxglForm model, HttpServletRequest request,List<HashMap<String, String>> djList) throws Exception{
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
		String[] colList = new String[] { "xh", "xh", "xm", "xb", "nj", "xymc","zymc","bjmc","szjzs","sfhd"};
		StringBuilder sql = new StringBuilder(" select rownum r,a.* from (select a.mdid,c.*,");
		sql.append("b.szjz,"+djzdSql+" case when length(b.szjz)>12 then substr(b.szjz,0,12)||'...' else b.szjz end szjzs, case when (select count(1) from xg_jxgl_jxbxwhb where a.xh = xh and jxid = '");
		sql.append(model.getJxid()+"' and bxlb = '"+model.getBxlb()+"' and jtbx = '");
		sql.append(model.getJtbx()+"') = 0 then '否' else '是' end as sfhd from xg_jxgl_jxjzmdb a ");
		sql.append(" left join (select jzid,"+djsql+" sys_connect_by_path(jzmc,' ') szjz from xg_jxgl_jxjzwhb start with ");
		sql.append("sjid = '"+model.getJxid()+"' connect by sjid = prior jzid) b on a.jzid = b.jzid ");
		sql.append(" left join (select a.xh, a.xm,a.nj,a.bjdm, a.xb,b.bjmc,b.xymc, b.zymc from xsxxb a left join view_njxyzybj_all b on a.bjdm = b.bjdm) c on a.xh = c.xh where b.szjz is not null ");
		sql.append(" order by c.bjdm,c.xh)a where 1=1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}


}
