package xsgzgl.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xsxx.dagl.guizdx.XsxxGuizdxDaglForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

public class BasicDAO extends CommDAO{
	
	/**
	 * 通用查询方法
	 * 方法介绍：该方法的查询条件来源于高级查询，不支持非高级查询
	 * model中的参数：该方法需要设置user对象、colList、viewName这三个参数
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
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
	 * 通用查询方法
	 * 方法介绍：该方法的查询条件来源于高级查询，不支持非高级查询
	 * model中的参数：该方法需要设置user对象、colList、viewName这三个参数
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getMakeQueryList(BasicModel model,Object obj) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		MakeQuery makeQuery=new MakeQuery();
		//用户对象
		User user=model.getUser();
		
		String viewName=model.getViewName();
		
		String []colList=model.getColList();
		
		String []queryV=model.getQueryV();
		
		String []queryLikeV=model.getQueryLikeV();
		
		
		
		//====================过滤条件===================================
		
		makeQuery.makeQuery(queryV, queryLikeV, obj);
		
		String searchTj = makeQuery.getQueryString();
		// 权限过滤
		String searchUser = getUserSql(user);
		// 高级查询输入值
		String[] inputV =  makeQuery.getInputList();

		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		query.append(searchTj);
		query.append(searchUser);
		
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(viewName);
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
		int m=0;
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
					if(m!=0){
						
						pkValue.append("!!@@!!");
						pks.append("||'!!@@!!'||");
					}
					pkValue.append(paramValue);
					pks.append(pkArr[i]);
					m++;
				}
			}
			
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		inputV.add(service.unicode2Gbk(pkValue.toString()));
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
	 * 批量修改功能
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean batchUpdate(BasicModel model){
		
		BasicService service=new BasicService();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String[]pkValue=model.getPkValue();
		
		String[]pkArr=model.getPk();
		
		String tableName=model.getTableName();
		
		DAO dao=DAO.getInstance();
		//保存SQL
		StringBuilder sql=new StringBuilder();
		//字段名
		StringBuilder comments=new StringBuilder();
		//pkValue
		StringBuilder pkStr=new StringBuilder();
		//pkS
		String pks=service.ArrayToStr(pkArr, "||'!!@@!!'||");
		//值
		List<String> inputV=new ArrayList<String>();
		
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

			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		
		
		boolean bool=false;
		
		pkStr.append("(");
		for(int i=0;i<pkValue.length;i++){
			
			pkValue[i]=pkValue[i].trim();
			
			if(!Base.isNull(pkValue[i])){
				
				if(bool){
					pkStr.append(",");
				}
				pkStr.append("'"+service.unicode2Gbk(pkValue[i])+"'");
				bool=true;
			}
			
		}
		pkStr.append(")");
		
		//插入的字段
		sql.append(comments);
		sql.append(" where ");
		sql.append(pks);
		sql.append(" in ");
		sql.append(pkStr);
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
	 * 批量删除功能
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean batchDelete(BasicModel model){
		
		BasicService service=new BasicService();
		
		String[]pkValue=model.getPkValue();
		
		String[]pkArr=model.getPk();
		
		String tableName=model.getTableName();
		
		DAO dao=DAO.getInstance();
		//保存SQL
		StringBuilder sql=new StringBuilder();
		//字段名
		StringBuilder comments=new StringBuilder();
		//pkValue
		StringBuilder pkStr=new StringBuilder();
		//pkS
		String pks=service.ArrayToStr(pkArr, "||'!!@@!!'||");
		//值
		List<String> inputV=new ArrayList<String>();
		
		sql.append(" delete from  ");
		sql.append( tableName );
		
		
		boolean bool=false;
		
		pkStr.append("(");
		for(int i=0;i<pkValue.length;i++){
			
			pkValue[i]=pkValue[i].trim();
			
			if(!Base.isNull(pkValue[i])){
				
				if(bool){
					pkStr.append(",");
				}
				pkStr.append("'"+service.unicode2Gbk(pkValue[i])+"'");
				bool=true;
			}
			
		}
		pkStr.append(")");
		
		//插入的字段
		sql.append(comments);
		sql.append(" where ");
		sql.append(pks);
		sql.append(" in ");
		sql.append(pkStr);
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
	 * @param model
	 * (tablename:查询表或视图名、
	 *  pkValue：主键值、
	 *  pk:主键)
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String>viewInfo(BasicModel model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		String tableName=model.getTableName();
		
		String[]pkValue=model.getPkValue();
		
		String[]pk=model.getPk();
		
		sql.append(" select * from ");
		
		sql.append(tableName);
		
		sql.append(" where 1=1 ");
		
		if(pkValue!=null && pk.length>0){
		
			for(int i=0;i<pk.length;i++){
				sql.append(" and ");
				
				sql.append(pk[i]);
				
				sql.append(" = ? ");
			
			}
		}
		
		return dao.getMapNotOut(sql.toString(), pkValue);
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
	
	/**
	 * 获取用户权限判断SQL
	 * @param model
	 * @return
	 */
	public String getUserSql(User user){
		
		String fdyQx=user.getFdyQx();
		String bzrQx=user.getBzrQx();
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		String userType=user.getUserType();
		StringBuilder userQuery=new StringBuilder();
		
		if("true".equalsIgnoreCase(fdyQx) && "true".equalsIgnoreCase(bzrQx)){
			
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(fdyQx)){
			
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(bzrQx)){
			
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("xy".equalsIgnoreCase(userType)){
			
			userQuery.append(" and xydm='"+userDep+"' ");
		}
		return userQuery.toString();
	}

	// -------------------------------审核流相关方法 begin-------------------------------
	
	public List<HashMap<String,String>>getShxmList(BasicModel model){
		
		User user=model.getUser();
		
		String xmb=model.getXmb();
		
		String userName=user.getUserName();
	
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xmdm, a.xmmc ");
		sql.append(" from "+xmb+" a, xg_xtwh_spbz b, xg_xtwh_spgwyh c ");
		sql.append(" where a.lcid = b.splc ");
		sql.append(" and b.spgw = c.spgw ");
		sql.append(" and c.spyh = ? ");
		sql.append(" group by a.xmdm,a.xmmc  ");
		
		DAO dao = DAO.getInstance();

		return dao.getList(sql.toString(), new String[]{userName}, new String[]{"xmdm","xmmc"});
	}
	
	/**
	 * 根据评奖项目、当前审核
	 * 岗位获取下级审批岗位
	 * @author qlj
	 */
	public HashMap<String,String> getHigherUpSpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		// 项目代码
		String xmdm = valueMap.get("xmdm");
		
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();

		String xmb=valueMap.get("xmb");
		
		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  ");
		sql.append(xmb);
		sql.append(" where xmdm = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(xmb);
		sql.append(" where xmdm = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh+1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,spgw});
	}
	
	/**
	 * 根据评奖项目、当前审核
	 * 岗位获取下级审批岗位
	 * @author qlj
	 */
	public HashMap<String,String> getHigherUpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();

		String xmb=valueMap.get("xmb");
		
		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  ");
		sql.append(xmb);
		sql.append("  ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(xmb);
		sql.append("  )  ");
		sql.append(" and spgw=? ");
		sql.append(" and a.xh+1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{spgw});
	}
	
	/**
	 * 根据评奖项目、当前审核
	 * 岗位获取下级审批岗位
	 * @author qlj
	 */
	public HashMap<String,String> getNextSpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		// 项目代码
		String xmdm = valueMap.get("xmdm");
		
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();

		String xmb=valueMap.get("xmb");
		
		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from "+xmb+" ");
		sql.append(" where xmdm = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  "+xmb+" ");
		sql.append(" where xmdm = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh-1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,spgw});
	}
	
	/**
	 * 通用查询方法
	 * 方法介绍：该方法的查询条件来源于高级查询，不支持非高级查询
	 * model中的参数：该方法需要设置user对象、colList、viewName这三个参数
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXmshListByXmb(BasicModel model) throws Exception{
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String []pk=model.getPk();
		
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
		StringBuilder tableSql = new StringBuilder();
		// 申请表
		String sqb=valueMap.get("sqb");
		// 审核表
		String shb=valueMap.get("shb");
		// 人员表
		String ryb=valueMap.get("ryb");
		// 项目代码
		String xmdm=valueMap.get("xmdm");

		tableSql.append(" select a.*,rownum r from( "); 
		tableSql.append(" select a.*,case when b.xh is not null then "); 
		tableSql.append(" 'disabled' else '' end sfsh from(  "); 
		tableSql.append(" select a.xh pkValue, a.xmdm, "); 
		tableSql.append(" a.xh,a.sqsj,c.xydm, "); 
		tableSql.append(" case when b.shzt='wsh' then '未审核' ");
		tableSql.append(" when b.shzt='tg' then '通过' ");
		tableSql.append(" when b.shzt='btg' then '不通过' ");
		tableSql.append(" when b.shzt='xcs' then '需重审' ");
		tableSql.append(" when b.shzt='th' then '退回' end shzt, ");
		tableSql.append(" c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,c.nj,c.xm "); 
		tableSql.append(" from "+sqb+" a, "+shb+" b, "+ryb+" c "); 
		tableSql.append(" where b.xtgwid =? "); 
		tableSql.append(" and b.xmdm = ? "); 
		tableSql.append(getPkQuery(pk, pk, "a", "b"));
		tableSql.append(" and a.xh = c.xh "); 
		
		HashMap<String,String>higherUpSpgw=getHigherUpSpMap(model, user);
		
		String higherUpgw=higherUpSpgw.get("spgw");
		if(!Base.isNull(higherUpgw)){
			tableSql.append(" and exists( select 1 from "+shb+" x where 1=1 and x.xmdm='"+xmdm+"'");
			tableSql.append(getPkQuery(pk, pk, "b", "x"));
			tableSql.append(" and x.xtgwid='"+higherUpgw+"' and shzt='tg' )");
		}
		
		// --------------------------下级审核是否已审核-------------------------------
		tableSql.append(" )a left join ( ");
		tableSql.append("  select xh from "+shb+" ");
		tableSql.append("  where xtgwid = ? ");
		tableSql.append("  and xmdm = ? ");
		tableSql.append("  and (shzt='tg' or shzt='btg') )b on a.xh=b.xh) a ");
		// --------------------------下级审核是否已审核-------------------------------
		
		tableSql.append(query);
		
		// ====================SQL拼装 end================================

		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(tableSql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * 通用查询方法
	 * 方法介绍：该方法的查询条件来源于高级查询，不支持非高级查询
	 * model中的参数：该方法需要设置user对象、colList、viewName这三个参数
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXmshListBySzb(BasicModel model) throws Exception{
		
//		高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String []pk=model.getPk();
		
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
		StringBuilder tableSql = new StringBuilder();
		// 申请表
		String sqb=valueMap.get("sqb");
		// 审核表
		String shb=valueMap.get("shb");
		// 人员表
		String ryb=valueMap.get("ryb");

		tableSql.append(" select a.*,rownum r from( "); 
		tableSql.append(" select a.*,case when b.xh is not null then "); 
		tableSql.append(" 'disabled' else '' end sfsh from(  "); 
		tableSql.append(" select a.xh pkValue, a.xmdm, "); 
		tableSql.append(" a.xh,a.sqsj,c.xydm, "); 
		tableSql.append(" case when b.shzt='wsh' then '未审核' ");
		tableSql.append("  when b.shzt='tg' then '通过' ");
		tableSql.append("  when b.shzt='btg' then '不通过' ");
		tableSql.append("  when b.shzt='xcs' then '需重审' ");
		tableSql.append("  when b.shzt='th' then '退回' end shzt, ");
		tableSql.append(" c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,c.nj,c.xm "); 
		tableSql.append(" from "+sqb+" a, "+shb+" b, "+ryb+" c "); 
		tableSql.append(" where b.xtgwid =? "); 
		tableSql.append(getPkQuery(pk, pk, "a", "b"));
		tableSql.append(" and a.xh = c.xh "); 
		
		HashMap<String,String>higherUpSpgw=getHigherUpSpMap(model, user);
		
		String higherUpgw=higherUpSpgw.get("spgw");
		if(!Base.isNull(higherUpgw)){
			tableSql.append(" and exists( select 1 from "+shb+" x where 1=1 ");
			tableSql.append(getPkQuery(pk, pk, "b", "x"));
			tableSql.append(" and x.xtgwid='"+higherUpgw+"' and shzt='tg' )");
		}
		
		// --------------------------下级审核是否已审核-------------------------------
		tableSql.append(" )a left join ( ");
		tableSql.append("  select xh from "+shb+" ");
		tableSql.append("  where xtgwid = ? ");
		tableSql.append("  and (shzt='tg' or shzt='btg') )b on a.xh=b.xh) a ");
		// --------------------------下级审核是否已审核-------------------------------
		
		tableSql.append(query);
		
		// ====================SQL拼装 end================================

		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(tableSql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	
	public String getPkQuery(String[]firstPk,String[]secoundPk,String first,String secound){
		
		StringBuilder sql= new StringBuilder();
		
		for(int i=0;i<firstPk.length;i++){
			
			sql.append(" and ");
			sql.append(first);
			sql.append(".");
			sql.append(firstPk[i]);
			sql.append(" = ");
			
			sql.append(secound);
			sql.append(".");
			sql.append(secoundPk[i]);
		}
		
		return sql.toString();
	}

	
	// -----------------------------BASICDAO 第二期 begin----------------------------------------
	/**
	 * 通用修改方法（带写日志）
	 * @param BasicModel model
	 * model 中需包含 valueMap、user等信息
	 * user 对象用于写日志
	 * tableName 指明修改的表名
	 * pk 主键字段
	 * valueMap 主键值与修改字段信息
	 * @return
	 * @throws Exception 
	 */
	public boolean update(BasicModel model){

		CommService service=new CommService();
		
		DAO dao=DAO.getInstance();
		
		// 用户对象 写日志相关
		User user=model.getUser();
		// 修改值
		HashMap<String,String>valueMap=model.getValueMap();
		// 表名
		String tableName=model.getTableName();
		//保存SQL
		StringBuilder sql=new StringBuilder();
		//字段名
		StringBuilder comments=new StringBuilder();
		// 修改条件
		StringBuilder query=new StringBuilder();
		// 修改字段数值
		List<String> inputV=new ArrayList<String>();
		// 修改数据主键值
		List<String> queryV=new ArrayList<String>();
		
		// 主键字段
		String[]pkArr=model.getPk();
		
		sql.append(" update  ");
		sql.append( tableName );
		sql.append(" set ");
		
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			
			// 键(字段名)
			String paramName = entry.getKey();
			// 值(字段值)
			String paramValue= entry.getValue();
			
			boolean flag=true;// 判断该字段是否为主键字段
			
			// ------------------主键拼接 begin --------------------
			for(int i=0;i<pkArr.length;i++){
				
				if(pkArr[i].equalsIgnoreCase(paramName)){
					flag=false;
					// ------------主键条件 begin------------
					query.append(" and ");
					query.append(paramValue);
					query.append("=?");
					// ------------主键条件 end------------
					
					// -------------er~ 主键值 begin--------
					queryV.add(service.unicode2Gbk(pkArr[i]));
					// -------------er~ 主键值 end----------
					break;
				}
			}
			// ------------------主键拼接 end --------------------
			
			// ---------....主键字段是不需要修改的 begin----------
			if(flag){
				
				if(n!=0){
					
					comments.append(",");
				}
				// -------修改信息 begin----------
				comments.append(paramName);
				comments.append("=?");
				// -------修改信息 end ----------
				n++;
				
				// --------------修改值------------------
				inputV.add(service.unicode2Gbk(paramValue));
			}	
			// ---------....主键字段是不需要修改的 end----------
		}
		
		//插入的字段
		sql.append(comments);
		sql.append(" where 1=1  ");
		sql.append(query);
		
		boolean flag = false;
		
		inputV.addAll(queryV);
		
		try {
			// ---------------执行修改 SQL语句----------------------
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}),tableName,user);
		} catch (Exception e) {
			
			flag = false;
			System.out.println("<------- 修改失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	} 
	
	
	/**
	 * 通用增加方法（带写日志）
	 * @param BasicModel model
	 * model 中需包含 valueMap、user等信息
	 * user 对象用于写日志
	 * tableName 指明修改的表名
	 * pk 主键字段
	 * valueMap 主键值与修改字段信息
	 * @return
	 * @throws Exception 
	 */
	public boolean insert(BasicModel model){

		User user=model.getUser();
		
		CommService service=new CommService();
		
		DAO dao=DAO.getInstance();
		
		HashMap<String,String>valueMap=model.getValueMap();
		// 表名
		String tableName=model.getTableName();
		// SQL
		StringBuilder sql=new StringBuilder();
		// 字段名
		StringBuilder comments=new StringBuilder();
		// 字段值
		StringBuilder commentsValue=new StringBuilder();
		// 值
		List<String> inputV=new ArrayList<String>();
		sql.append(" insert into ");
		sql.append(tableName);
		sql.append(" ( ");
		
		commentsValue.append(" values( ");
		int n=0;
		for(Map.Entry<String, String>entry:valueMap.entrySet()){
			// 键(字段名)
			String paramName = entry.getKey();
			// 值(保存字段值)
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
					.toArray(new String[] {}),tableName,user);
		} catch (Exception e) {
			
			flag = false;
			System.out.println("<------- 申请保存失败 ------>");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	/**
	 * 批量修改功能
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean runBatch(BasicModel model){
		
		User user=model.getUser();
		
		BasicService service=new BasicService();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String[]pkValue=model.getPkValue();
		
		String[]pkArr=model.getPk();
		
		String tableName=model.getTableName();
		
		DAO dao=DAO.getInstance();
		//保存SQL
		StringBuilder sql=new StringBuilder();
		//字段名
		StringBuilder comments=new StringBuilder();
		//pkValue
		StringBuilder pkStr=new StringBuilder();
		//pkS
		String pks=service.ArrayToStr(pkArr, "||'!!@@!!'||");
		//值
		List<String> inputV=new ArrayList<String>();
		
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

			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		
		
		boolean bool=false;
		
		pkStr.append("(");
		for(int i=0;i<pkValue.length;i++){
			
			pkValue[i]=pkValue[i].trim();
			
			if(!Base.isNull(pkValue[i])){
				
				if(bool){
					pkStr.append(",");
				}
				pkStr.append("'"+service.unicode2Gbk(pkValue[i])+"'");
				bool=true;
			}
			
		}
		pkStr.append(")");
		
		//插入的字段
		sql.append(comments);
		sql.append(" where ");
		sql.append(pks);
		sql.append(" in ");
		sql.append(pkStr);
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
	 * 获取指定表中的数据
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getBasicData(BasicModel model){
		
		DAO dao=DAO.getInstance();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		String tableName=model.getTableName();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where 1=1 ");
		sql.append(valueMap.get("query"));
		
		return dao.getMapNotOut(sql.toString(), model.getQueryV());
	}
	
	// -----------------------------BASICDAO 第二期 end----------------------------------------
}
