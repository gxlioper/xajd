package xgxt.comm.xginfo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;

public class CommXgInfoService extends CommService {

	CommXgInfoDAO dao = new CommXgInfoDAO();

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXsxxList(CommForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getXsxxList(model, colList);
	}
	
	/**
	 * 获取学院List
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getXyList(CommXgInfoModel model) throws Exception{
		Pages pages = model.getPages();
	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select rownum r,a.* from (");
		sql.append("select distinct xydm,xymc from view_njxyzybj");
		
		MakeQuery makeQuery = new MakeQuery();
		String[] colList = new String[]{};
		String[] colLikeList = new String[]{"xydm", "xymc"};
		
		makeQuery.makeQuery(colList, colLikeList, model);
		
		sql.append(makeQuery.getQueryString());
		sql.append(") a )");
		sql.append(" where r>");
		sql.append(pages.getStart());
		sql.append(" and r<=");
		sql.append(pages.getStart()+pages.getPageSize());
		
		DAO dao = DAO.getInstance();
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from (");
		countSql.append("select distinct xydm,xymc from view_njxyzybj");
		countSql.append(makeQuery.getQueryString());
		countSql.append(")");
		
		String[] num = dao.getOneRs(countSql.toString(), makeQuery.getInputList(), new String[]{"num"});
		pages.setMaxRecord(Integer.parseInt(num[0]));
		
		return dao.rsToVator(sql.toString(), makeQuery.getInputList(), new String[]{"xydm", "xymc"});
	}
	
	/**
	 * 获取专业List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZyList(CommXgInfoModel model) throws Exception{
		Pages pages = model.getPages();
	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select rownum r,a.* from (");
		sql.append("select distinct zydm,zymc,xydm,xymc from view_njxyzybj");
		
		MakeQuery makeQuery = new MakeQuery();
		String[] colList = new String[]{"xydm"};
		String[] colLikeList = new String[]{"zydm", "zymc"};
		
		makeQuery.makeQuery(colList, colLikeList, model);
		
		sql.append(makeQuery.getQueryString());
		sql.append(") a )");
		sql.append(" where r>");
		sql.append(pages.getStart());
		sql.append(" and r<=");
		sql.append(pages.getStart()+pages.getPageSize());
		
		DAO dao = DAO.getInstance();
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from (");
		countSql.append("select distinct zymc,zydm from view_njxyzybj");
		countSql.append(makeQuery.getQueryString());
		countSql.append(")");
		
		String[] num = dao.getOneRs(countSql.toString(), makeQuery.getInputList(), new String[]{"num"});
		pages.setMaxRecord(Integer.parseInt(num[0]));
		
		return dao.rsToVator(sql.toString(), makeQuery.getInputList(), new String[]{"zydm", "zymc", "xydm", "xymc"});
	}
	
	/**
	 * 获取班级List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjList(CommXgInfoModel model) throws Exception{
		Pages pages = model.getPages();
	
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(model.getUser(), "a",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		if ("10698".equals(Base.xxdm)) {
			sql.append("select * from (select rownum r,a.* from (select distinct nj, a.bjdm, bjmc, zydm, zymc, xydm, xymc,c.sydm,symc from view_njxyzybj_all a ");
			sql.append(" left join xg_xtwh_sybjglb b on a.bjdm=b.bjdm");
			sql.append(" left join xg_xtwh_sydmb c on b.sydm=c.sydm");
		}else {
			sql.append("select * from (select rownum r,a.* from (select distinct nj,bjdm,bjmc,zydm,zymc,xydm,xymc from view_njxyzybj_all ");
		}
		
		MakeQuery makeQuery = new MakeQuery();
		String[] colList = new String[]{"xydm", "zydm", "nj"};
		String[] colLikeList = new String[]{"bjdm", "bjmc", "xymc", "zymc"};
		
		makeQuery.makeQuery(colList, colLikeList, model);
		
		sql.append(makeQuery.getQueryString());
		sql.append(") a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" )where r>");
		sql.append(pages.getStart());
		sql.append(" and r<=");
		sql.append(pages.getStart()+pages.getPageSize());
		
		DAO dao = DAO.getInstance();
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from (");
		countSql.append("select distinct bjmc,bjdm,xydm from view_njxyzybj_all");
		countSql.append(makeQuery.getQueryString());
		countSql.append(") a where 1=1 ");
		countSql.append(searchTjByUser);
		
		String[] num = dao.getOneRs(countSql.toString(), makeQuery.getInputList(), new String[]{"num"});
		pages.setMaxRecord(Integer.parseInt(num[0]));
		String[] query = new String[]{};
		if ("10698".equals(Base.xxdm)) {
			query= new String[]{"nj", "bjdm", "bjmc", "zydm", "zymc", "xydm", "xymc", "sydm", "symc"};
		}else {
			query= new String[]{"nj", "bjdm", "bjmc", "zydm", "zymc", "xydm", "xymc"};
		}
		return dao.rsToVator(sql.toString(), makeQuery.getInputList(), query);
	}
	
	/**
	 * 获取流程List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLcList(CommXgInfoModel model,String mkid) throws Exception{
		Pages pages = model.getPages();
	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select rownum r,a.* from (");
		sql.append("select guid lcid,lcmc,yxj,tjbcyfs from xg_xtwh_shlckxxb ");
		
		MakeQuery makeQuery = new MakeQuery();
		String[] colList = new String[]{"tjbcyfs"};
		String[] colLikeList = new String[]{"lcmc", "yxj"};
		
		makeQuery.makeQuery(colList, colLikeList, model);
		
		sql.append(makeQuery.getQueryString());
		sql.append(") a )");
		sql.append(" where r>");
		sql.append(pages.getStart());
		sql.append(" and r<=");
		sql.append(pages.getStart()+pages.getPageSize());
		
		DAO dao = DAO.getInstance();
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from xg_xtwh_shlckxxb ");
		countSql.append(makeQuery.getQueryString());
		
		String[] num = dao.getOneRs(countSql.toString(), makeQuery.getInputList(), new String[]{"num"});
		pages.setMaxRecord(Integer.parseInt(num[0]));
		System.out.println(sql.toString());
		return dao.rsToVator(sql.toString(), makeQuery.getInputList(), new String[]{"lcid","lcmc", "yxj", "tjbcyfs"});
	}
	
	
	/**
	 * 获取总条目
	 * @param tableName
	 * @param querryStr
	 * @param inputValue
	 * @return
	 */
	public String getCount(String tableName, String querryStr, String[] inputValue){
		StringBuilder countSql = new StringBuilder();
		DAO dao = DAO.getInstance();
		
		countSql.append("select count(*) num from ");
		countSql.append(tableName);
		countSql.append(querryStr);
		String[] rs = dao.getOneRs(countSql.toString(), inputValue, new String[]{"num"});
		return rs[0];
	}
	
	/**
	 * 获取参与方式列表
	 * */
	public List<HashMap<String, String>> getCyfsList(){
		DAO dao = DAO.getInstance();
		return dao.getChkList(31);//获取参与方式
	}
}
