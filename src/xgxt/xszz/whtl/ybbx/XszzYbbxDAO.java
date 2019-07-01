package xgxt.xszz.whtl.ybbx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class XszzYbbxDAO extends BasicDAO{
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getYbbxcxList(BasicModel model) throws Exception{
		
		
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
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		
		String ryk=jbszModel.getRyk();
		
		sql.append(" select rownum r,xh,xm,xb, nj,xydm,  ");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,xysh, ");
		sql.append(" xyshsj,xyshyj,xxsh,xxshsj,xxshyj, ");
		sql.append(" xn||'!!@@!!'||xh pkValue ");
		
		sql.append(" from ( ");
		
		sql.append(" select a.xn,b.xh,b.xm,b.xb, b.nj,b.xydm,");
		sql.append(" b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.xysh,");
		sql.append(" a.xyshsj,a.xyshyj,a.xxsh,a.xxshsj,a.xxshyj");
		sql.append(" from xg_xszz_ybbxsqb a, view_xsjbxx b");
		sql.append(" where a.xh = b.xh ");
       
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
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getYbbxshList(BasicModel model) throws Exception{
		
		
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
		String []shzt4=searchModel.getSearch_tj_shzt4();
		searchModel.setSearch_tj_shzt4(null);
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
		
		sql.append(" select rownum r,xh,xm,xb, nj,xydm,  ");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,xysh, ");
		sql.append(" xyshsj,xyshyj,xxsh,xxshsj,xxshyj, ");
		sql.append(" xn||'!!@@!!'||xh pkValue ");
		
		sql.append(" from ( ");
		
		sql.append(" select a.xn,b.xh,b.xm,b.xb, b.nj,b.xydm,");
		sql.append(" b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.xysh,");
		sql.append(" a.xyshsj,a.xyshyj,a.xxsh,a.xxshsj,a.xxshyj");
		sql.append(" from xg_xszz_ybbxsqb a, view_xsjbxx b");
		sql.append(" where a.xh = b.xh ");
       
		sql.append(" )a ");
		
		sql.append(query);
		
		if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			sql.append(" and  xysh='通过' ");
			if(shzt4!=null && shzt4.length>0){
				sql.append(" and ( ");
				for(int i=0;i<shzt4.length;i++){
					
					if(i!=0){
						sql.append(" or ");
					}
					sql.append(" xxsh = '"+shzt4[i]+"' ");
				}
				
				sql.append(" ) ");
			}
		}else if("xy".equalsIgnoreCase(userType)){
			if(shzt4!=null && shzt4.length>0){
				sql.append(" and ( ");
				for(int i=0;i<shzt4.length;i++){
					
					if(i!=0){
						sql.append(" or ");
					}
					sql.append(" xysh = '"+shzt4[i]+"' ");
				}
				
				sql.append(" ) ");
			}
		}
		
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
	
	public HashMap<String,String>getYbbxSqbInfo(XszzYbbxForm myForm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xszz_ybbxsqb where xn||'!!@@!!'||xh=?  ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getPkValue()});
		
	}
	
	public boolean deleteYbbxInfo() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" delete from xg_xszz_ybbxxxb a where not exists ");
		sql.append(" (select 1 from xg_xszz_ybbxsqb b where a.xh=b.xh and a.xn=b.xn) ");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	public boolean updateYbbx(BasicModel model) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		BasicService service=new BasicService();
		
		String[]pk=model.getPk();
		
		String[]pkValue=model.getPkValue();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_xszz_ybbxxxb set shje=bxje ");
		
		sql.append(" where ");
		
		String pks=service.ArrayToStr(pk, "||'!!@@!!'||");
		
		sql.append(pks);
		sql.append(" in ");
		sql.append("(");
		
		boolean bool=false;
		for(int i=0;i<pkValue.length;i++){
			
			pkValue[i]=pkValue[i].trim();
			
			if(!Base.isNull(pkValue[i])){
				
				if(bool){
					sql.append(",");
				}
				sql.append("'"+service.unicode2Gbk(pkValue[i])+"'");
				bool=true;
			}
			
		}
		sql.append(")");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	public HashMap<String,String>checkInfo(BasicModel model){
		
		BasicService service=new BasicService();
		
		String[]pk=model.getPk();
		
		String[]pkValue=model.getPkValue();
		
		StringBuilder query=new StringBuilder();
		
		String pks=service.ArrayToStr(pk, "||'!!@@!!'||");
	
		query.append(" and ");
		query.append(pks);
		query.append(" in ");
		query.append("(");
		
		boolean bool=false;
		for(int i=0;i<pkValue.length;i++){
			
			pkValue[i]=pkValue[i].trim();
			
			if(!Base.isNull(pkValue[i])){
				
				if(bool){
					query.append(",");
				}
				query.append("'"+service.unicode2Gbk(pkValue[i])+"'");
				bool=true;
			}
			
		}
		query.append(")");
		query.append(" and (xysh <> '未审核' and xysh <> '退回') ");
		
		return checkInfo(query, model);
	
	}
	
	public HashMap<String,String>checkInfo(StringBuilder query,BasicModel model){
		
		DAO dao=DAO.getInstance();
	
		String tableName=model.getTableName();
		
		StringBuilder sql=new StringBuilder();
	
		sql.append(" select count(1)num from ");
		sql.append(tableName);
		sql.append(" where 1=1 ");
		
		sql.append(query);
		
		return dao.getMap(sql.toString(), new String[]{},new String[]{"num"});
	
	}
	
	public String countXh(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select count(1)num from xg_view_xszz_dxsybbx where xh= ? ");
		
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num");
	}
}
