package xsgzgl.xsxx.bzrpy.sjsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.xsxx.bzrpy.BasicModel;

public class XsxxPysjDAO extends CommDAO{
	
	/**
	 * 通用查询方法
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String viewName=model.getViewName();
		
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

		sql.append(" select a.*,rownum r from ( ");
		sql.append(viewName);
		sql.append(" )a ");
		
		sql.append(query);
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);

		return list;
	}
	
	public HashMap<String,String>getBasicMap(BasicModel model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		String tableName=model.getViewName();
		//条件
		String[]queryV=new String[]{};
		String[]inputV=new String[]{};
		
		if(model.getQueryV()!=null){
			queryV=model.getQueryV();
			inputV=model.getInputV();
		}
		
		String[]outPut=model.getOutPut();
		
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where 1=1 ");
		
		sql.append(searchQuery(queryV));
		
		return dao.getMap(sql.toString(), inputV, outPut);
		
	}
	
	public String searchQuery(String[]queryV){
		
		StringBuilder query=new StringBuilder();
		
		if(queryV!=null && queryV.length>0){
			
			for(int i=0;i<queryV.length;i++){
				
				query.append(" and ");
				query.append(queryV);
				query.append(" = ? ");
				
			}
			
		}
		
		return query.toString();
	}
	

	public HashMap<String,String>getOneBzrpy(XsxxPysjModel model){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select xh,xn,pyr,pyyj,pysj from xg_xsxx_bzrpyb ");
		sql.append(" where 1=1 and xh='"+model.getXh()+"' ");
		sql.append(" and xn='"+model.getXn()+"'");
		
		return dao.getMap(sql.toString(), new String[]{},
				new String[]{"xh","xn","pyr","pyyj","pysj"});
		
	}
	
	/**
	 * 根据传入得 键、值形式表字段信息进行修改
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
		
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String tableName=model.getTableName();
		
		CommService service=new CommService();
		
		DAO dao=DAO.getInstance();
		//保存SQL
		StringBuilder sql=new StringBuilder();
		//字段名
		StringBuilder comments=new StringBuilder();
		//pkValue
		StringBuilder pkValue=new StringBuilder();
		//pkS
		StringBuilder pks=new StringBuilder();
		//值
		List<String> inputV=new ArrayList<String>();
		
		String[]pkArr=model.getPk();
		
		sql.append(" update  ");
		sql.append( tableName );
		sql.append(" set ");
		
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue= entry.getValue();
			if(n!=0){
				comments.append(",");
				
			}
			
			comments.append(paramName);
			comments.append("=?");

			for(int i=0;i<pkArr.length;i++){
				if(pkArr[i].equalsIgnoreCase(paramName)){
					if(i!=0){
						
						pkValue.append("!!@@!!");
						pks.append("||'!!@@!!'||");
					}
					pkValue.append(paramValue);
					pks.append(pkArr[i]);
				}
			}
			
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		inputV.add(pkValue.toString());
		//插入的字段
		sql.append(comments);
		sql.append(" where ");
		sql.append(pks);
		sql.append(" = ? ");
		boolean flag = false;
		
		try {
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		} catch (Exception e) {
			
			flag = false;
			System.out.println("<------- 修改失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	} 
	
	/**
	 * 根据传入得 键、值形式表字段信息进行保存
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model){
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String tableName=model.getTableName();
		
		CommService service=new CommService();
		
		DAO dao=DAO.getInstance();
		//保存SQL
		StringBuilder sql=new StringBuilder();
		//字段名
		StringBuilder comments=new StringBuilder();
		//字段值
		StringBuilder commentsValue=new StringBuilder();
		//值
		List<String> inputV=new ArrayList<String>();
		sql.append(" insert into ");
		sql.append(tableName);
		sql.append(" ( ");
		
		commentsValue.append(" values( ");
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue= entry.getValue();
			if(n!=0){
				comments.append(",");
				commentsValue.append(",");
			}
			comments.append(paramName);
			commentsValue.append("?");
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		commentsValue.append(" ) ");
		//插入的字段
		sql.append(comments);
		sql.append(") ");
		//插入值
		sql.append(commentsValue);
		
		boolean flag = false;
		
		try {
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		} catch (Exception e) {
			
			flag = false;
			System.out.println("<------- 申请保存失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
