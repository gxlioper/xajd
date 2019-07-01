package xgxt.xljk.hzny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-4 下午04:35:06</p>
 */
public class HznyXljkZxzxDAO extends BasicDAO{
	
	// ---------------------------咨询师管理-------------------------------------
	/**
	 * 获取咨询师管理查询列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxsglList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// 用户属性
		String userType=user.getUserType();
		
		//====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
//				"xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
//		query += searchTjByUser;
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.*,rownum r from (select a.zgh pkValue, a.zgh,b.xm,b.xb, ");
		sql.append(" b.bmdm,b.bmmc,a.zxsbh,a.jj,a.bz,a.zxszg, ");
		sql.append(" case when length(a.zxszg)>10 then  ");
		sql.append(" substr(a.zxszg,0,10)||'...' else a.zxszg end zxszg_info ");
		sql.append(" from xg_xljk_zxsxxb a, view_fdyxx b ");
		sql.append(" where a.zgh = b.zgh)a ");

		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTeaInfo(String zgh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.*,b.zxsbh,b.jj,b.zxszg," );
		sql.append(" b.bz from view_fdyxx a left join xg_xljk_zxsxxb b" ); 
		sql.append(" on a.zgh=b.zgh where a.zgh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
//	 ---------------------------咨询师管理-------------------------------------
	
	
	// -----------------------获取特殊学生信息 begin--------------------------------------
	
	/**
	 * 获取特殊学生管理查询列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getTsxsglList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
	
		//====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select a.xh pkValue,a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc, ");
		sql.append(" a.tbgxxslb,a.sfzy,a.xswjbx,a.tbgxcs,a.xyclgc,a.xsdqzk,a.bz ");
		sql.append("  from XG_XLJK_TSXSXXB a ,view_xsjbxx b where a.xh=b.xh ");
		sql.append(" )a ");

		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * 获取需要特别关心学生类别信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getTbgxxslb(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select tbgxxslbdm dm,tbgxxslbmc mc from tbgxxslbdmb " );
		
		
		return dao.getList(sql.toString(), new String[] {},
				new String[] { "dm", "mc" });
	}
	
	/**
	 * 获取特殊学生信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getTsxsInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		String sql="  select a.*,b.xm sbrxm from xg_xljk_tsxsxxb a,yhb b where a.sbr=b.yhm and  xh= ? ";
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	// -----------------------获取特殊学生信息 end--------------------------------------

    // -----------------------获取特殊学生信息 begin--------------------------------------
	
	/**
	 * 获取在线咨询信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxzxglList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// 用户属性
		String userType=user.getUserType();
		
		String userName=user.getUserName();
		
		//====================过滤条件===================================
//		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		
		if(!"stu".equalsIgnoreCase(userType)){
			user.setUserStatus("xlzxs");
		}
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
//				"xydm", "bjdm");
//		query += searchTjByUser;
		
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.guid pkValue,a.*,rownum r from ( ");
		sql.append(" select a.*,c.xm zxsxm  ");
		if(!"stu".equalsIgnoreCase(userType)){
			
			sql.append(" ,case when d.xh is not null then a.xm||'(特殊)'  else a.xm end xsxm");
			sql.append(" ,case when d.xh is not null then '是'  else '否' end sftsxs");
		}
		sql.append(" from ( select a.*, b.xm, b.nj, b.xydm, b.xymc,  ");
		sql.append(" b.zydm, b.zymc, b.bjdm, b.bjmc, ");
		sql.append(" case when a.zgh is null then '否' else '是' end sfhf, ");
		sql.append(" case when length(zxwt)>10 then substr(zxwt,0,10)||'...' else zxwt end zxwt_info ");
		sql.append("  from xg_xljk_zxzxb a, view_xsjbxx b  where a.xh = b.xh)a left join  ");
		sql.append("  fdyxxb c  on a.zgh=c.zgh  left join");
		sql.append(" xg_xljk_tsxsxxb d on a.xh=d.xh ");
		sql.append(" )a ");

		sql.append(query);
		
		if(!"stu".equalsIgnoreCase(userType)
				&& !"admin".equalsIgnoreCase(userType) 
				&& !"xx".equalsIgnoreCase(userType)){
			
			sql.append(" and (  sfhf='否' or  zgh='"+userName+"') ");
			
			sql.append(" order by sfhf asc, zxsj desc ");
		}else if("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			
		}else if("stu".equalsIgnoreCase(userType)){
			sql.append(" and xh='"+userName+"' ");
		}else{
			sql.append(" order by sfhf desc, zxsj desc ");
			
		}
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	

	/**
	 * 获取学生在线咨询信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getZxzxInfo(String guid){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.*, c.xm zxsxm ");
		sql.append(" from (select a.*, b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append(" b.zydm,b.zymc,b.bjdm,b.bjmc, ");
		sql.append(" case  when a.zgh is null then '否' else '是' end sfhf ");
		sql.append(" from xg_xljk_zxzxb a, view_xsjbxx b  where a.xh = b.xh) a ");
		sql.append(" left join fdyxxb c on a.zgh = c.zgh where guid=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/**
	 * 获取学生在线咨询信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZxzxInfo(String[] pkValue){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select 1 ");
		sql.append(" from (select a.*, b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append(" b.zydm,b.zymc,b.bjdm,b.bjmc, ");
		sql.append(" case  when a.zgh is null then '否' else '是' end sfhf ");
		sql.append(" from xg_xljk_zxzxb a, view_xsjbxx b  where a.xh = b.xh) a ");
		sql.append(" left join fdyxxb c on a.zgh = c.zgh where ");
		sql.append(" guid in (");
		
		for(int i=0;i<pkValue.length;i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("'"+pkValue[i]+"'");
		}
		
		sql.append(")");
		
		sql.append(" and sfhf='是'");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	// -----------------------获取特殊学生信息 end--------------------------------------
	
	/**
	 * 获取在线咨询信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXszxList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// 用户属性
		String userType=user.getUserType();
		
		String userNmae=user.getUserName();
		
		//====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;

		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.guid pkValue,a.*,rownum r from ( ");
		sql.append(" select a.guid,a.zgh,a.xh,a.zxnrjyj,a.zxsj, ");
		sql.append(" a.zxsfk,a.fksj,b.xm,b.xb,c.xm zxsxm, ");
		sql.append(" case when fksj is null then '否' else '是' end sffk ");
		if(!"stu".equalsIgnoreCase(userType)){
			sql.append(" ,case when a.zgh='"+userNmae+"'  then '是' else '否' end sfzdbr ");
		}else{
			sql.append(" ,case when a.xh='"+userNmae+"'  then '是' else '否' end sfbrpj ");
		}
		sql.append(" from xg_xljk_xszxfkb a, view_xsjbxx b, xg_view_xljk_zxsxx c ");
		sql.append(" where a.xh = b.xh ");
		sql.append(" and a.zgh = c.zgh ");
		sql.append(" )a ");

		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getXlzxsInfo(String zgh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("select b.*, a.zxsbh, a.jj, a.zxszg, a.bz ");
		sql.append("from xg_xljk_zxsxxb a ");
		sql.append("left join view_fdyxx b on a.zgh = b.zgh where a.zgh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getXszxInfo(String guid){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("select a.guid,a.zgh,a.xh,a.zxnrjyj,a.zxsj, ");
		sql.append("a.zxsfk,a.fksj,b.xm xsxm,b.xb,c.*,c.xm zxsxm, ");
		sql.append("case when fksj is null then '否' else '是' end sffk ");
		sql.append("from xg_xljk_xszxfkb a, view_xsjbxx b, xg_view_xljk_zxsxx c ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.zgh = c.zgh ");
		sql.append("and a.guid=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/**
	 * 获取学生在线咨询信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getXszxInfo(String[] pkValue){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("select 1 from ");
		sql.append("(select a.guid,a.zgh,a.xh,a.zxnrjyj,a.zxsj, ");
		sql.append("a.zxsfk,a.fksj,b.xm xsxm,b.xb,c.*,c.xm zxsxm, ");
		sql.append("case when fksj is null then '否' else '是' end sffk ");
		sql.append("from xg_xljk_xszxfkb a, view_xsjbxx b, xg_view_xljk_zxsxx c ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.zgh = c.zgh ) where ");
		
		sql.append(" guid in (");
		
		for(int i=0;i<pkValue.length;i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("'"+pkValue[i]+"'");
		}
		
		sql.append(")");
		
		sql.append(" and sffk='是'");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 获取咨询师管理查询列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxjlList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// 用户属性
		String userType=user.getUserType();
		
		String userName=user.getUserName();
		
		//====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
//				"xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
//		query += searchTjByUser;
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.*,rownum r,a.guid pkValue ");
		
		sql.append(" from ( select a.*,case when b.xh is null then '否' else '是' end sftsxs   ");
		
		if(!"stu".equalsIgnoreCase(userType)){
			
			sql.append(" ,case when b.xh is null then xm else xm||'(特殊)' end xsxm ");
		}
		
		sql.append("  from(select b.*, a.guid, a.zgh, a.zxsj, a.zxnr, a.zxshf,c.xm zxsxm ");
		sql.append(" from xg_xljk_xlzxjlb a, view_xsjbxx b,fdyxxb c  ");
		sql.append(" where a.xh = b.xh and a.zgh=c.zgh ");
		
		if(!"xx".equalsIgnoreCase(userType) && !"admin".equalsIgnoreCase(userType)){
			sql.append(" and a.zgh='"+userName+"' ");
		}
		
		sql.append(" )a left join xg_xljk_tsxsxxb b on a.xh=b.xh)a ");

		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getZxjlInfo(String guid){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.*,rownum r,a.guid pkValue from ( ");
		sql.append(" select a.*,case when b.xh is null then '否' else '是' end sftsxs from   ");
		sql.append(" (select b.*, a.guid, a.zgh, a.zxsj, a.zxnr, a.zxshf,c.xm zxsxm ");
		sql.append(" from xg_xljk_xlzxjlb a, view_xsjbxx b,fdyxxb c  ");
		sql.append(" where a.xh = b.xh and a.zgh=c.zgh)a left join xg_xljk_tsxsxxb b on a.xh=b.xh ");
		sql.append(" )a ");
		sql.append(" where a.guid=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
}
