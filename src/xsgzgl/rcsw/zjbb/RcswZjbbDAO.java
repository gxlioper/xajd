package xsgzgl.rcsw.zjbb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
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
 * <p>Time:2012-7-19 上午10:02:11</p>
 */
public class RcswZjbbDAO extends BasicDAO{
	
	
	// ---------------------证件补办设置 begin -------------------	
	/**
	 * 证件补办设置结果集
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbszList(RcswZjbbForm myForm,BasicModel model) throws Exception{
		
		MakeQuery mQuery=new MakeQuery();
		
		BasicService basicService=new BasicService();
		
		String []colList=new String[] {"id","zjmc","lcmc","num","sfksc"};
		
		String[]queryList={"sfksc"};
		
		String[]queryLikeList={"zjmc"};
		
		mQuery.makeQuery(queryList, queryLikeList, myForm);
		
		//====================过滤条件===================================

		
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select a.id,nvl(mc,'无') lcmc,zjmc,nvl(b.num,0)num,  ");
		sql.append(" case when b.num>'0' then '否' else '是' end sfksc  ");
		sql.append(" from (select a.id,b.mc,a.zjmc from xg_rcsw_zjbbszb a left join ");
		sql.append(" xg_xtwh_splc b on a.lcid=b.id)a left join  ");
		sql.append(" (select xmid,count(1)num from  ");
		sql.append(" xg_rcsw_zjbbsqb where sqjg <>'btg'  ");
		sql.append(" and sqjg<>'tg' group by xmid)b on a.id=b.xmid ");
		sql.append(" )a ");

		sql.append(mQuery.getQueryString());
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", mQuery.getInputList(), colList, model);
		
		return list;
	}
	
	// ---------------------证件补办设置 end -------------------	
	public HashMap<String,String>getBbszInfo(RcswZjbbForm myForm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_rcsw_zjbbszb where id=? ");
		
		return  dao.getMapNotOut(sql.toString(), new String[]{myForm.getXmid()});
		
	}
	
	// ---------------------证件补办申请 begin -------------------	
	/**
	 * 获取可申请的补办项目列表
	 * @author qlj
	 */
	public List<HashMap<String,String>>getZjbbList(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select id,zjmc from xg_rcsw_zjbbszb where sqkg='yes' ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});		
	}
	
	/**
	 * 证件补办审核结果集
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbsqList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList={"id","zjmc","shzt", "shyj" , "sqsj","sqjg"};
		
		// 用户属性
		String userType=user.getUserType();
		
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
	//TODO 审核意见显示
		sql.append(" select a.*,rownum r from (  ");
		sql.append(" select xh,id,sqsj,max(shzt)shzt,zjmc ,sqjg , max(shyj) shyj from (  ");
		sql.append(" select xh,id,sqsj,zjmc,sqjg,to_char(  ");
		sql.append(" WM_CONCAT(mc||'('||nvl(shzt,'无需审核')||')')over( ");
		sql.append(" partition by id order by xsxh asc ))shzt,to_char(WM_CONCAT(mc || '(' || nvl(shyj, '无') || ')') over(partition by id order by xsxh asc)) shyj from (  ");
		sql.append(" select id,xh, sqsj,sqjg, zjmc, shzt,  xsxh,mc , shyj  from (  ");
		sql.append(" select a.id,a.xh,a.sqsj,a.sqjg,a.zjmc,b.shzt,b.xh xsxh,b.mc,b.shyj from(  ");
		sql.append(" select a.id,a.xh,a.sqsj,a.sqjg,e.lcid,e.zjmc from xg_rcsw_zjbbsqb a,xg_rcsw_zjbbszb e   ");
		sql.append(" where a.xmid=e.id)a left join   ");
		sql.append(" (select b.sqid, b.xtgwid, c.mc, case when b.shzt = 'wsh' then '未审核' ");
		sql.append(" when b.shzt = 'tg' then '通过'   ");
		sql.append(" when b.shzt = 'btg' then '不通过'   ");
		sql.append(" when b.shzt = 'xcs' then  '需重审'  ");
		sql.append(" when b.shzt = 'th' then '退回'  end shzt, ");
		sql.append(" d.splc,d.xh,b.shyj  from xg_rcsw_zjbbshb b,  ");
		sql.append(" xg_xtwh_spgw c, xg_xtwh_spbz d  where  ");
		sql.append(" b.xtgwid = c.id  and b.xtgwid = d.spgw)b  ");
		sql.append(" on a.id=b.sqid and a.lcid=b.splc  )) ) ");
		sql.append("  group by xh,id,sqsj,zjmc,sqjg)a ");
				        
		
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
	// ---------------------证件补办申请 end -------------------			
	
	
	// ---------------------证件补办审核 begin -------------------			
	/**
	 * 通用查询方法
	 * 方法介绍：该方法的查询条件来源于高级查询，不支持非高级查询
	 * model中的参数：该方法需要设置user对象、colList、viewName这三个参数
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBbshList(BasicModel model) throws Exception{
		
		DAO dao=DAO.getInstance();
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		HashMap<String,String>valueMap=model.getValueMap();
		
		HashMap<String,String> nextSpgw =getNextSpMap(model, user);
		
		String []pk=model.getPk();
		
		String []colList={ "id","xh","xm","nj","bjmc","sqsj","shzt","sfsh"};
		
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

		// 项目代码
		String xmdm=valueMap.get("xmdm");
		
		String spgw=valueMap.get("spgw");

		tableSql.append(" select a.*,rownum r from( "); 
		tableSql.append(" select a.*,case when b.sqid is not null then "); 
		tableSql.append(" 'disabled' else '' end sfsh from(  "); 
		tableSql.append(" select a.xh pkValue, a.xmid,a.id, "); 
		tableSql.append(" a.xh,a.sqsj,c.xydm, "); 
		tableSql.append(" case when b.shzt='wsh' then '未审核' ");
		tableSql.append(" when b.shzt='tg' then '通过' ");
		tableSql.append(" when b.shzt='btg' then '不通过' ");
		tableSql.append(" when b.shzt='xcs' then '需重审' ");
		tableSql.append(" when b.shzt='th' then '退回' end shzt, ");
		tableSql.append(" c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,c.nj,c.xm "); 
		tableSql.append(" from xg_rcsw_zjbbsqb a, xg_rcsw_zjbbshb b, "); 
		tableSql.append(" (select a.*,b.xymc,b.zymc,b.bjmc from "+Base.xsxxb+" ");
		tableSql.append(" a left join view_njxyzybj_all b on a.bjdm=b.bjdm) c ");
		tableSql.append(" where b.xtgwid =? "); 
		tableSql.append(" and a.xmid = ? "); 
		tableSql.append(" and a.id = b.sqid ");
		tableSql.append(" and a.xh = c.xh "); 
		
		HashMap<String,String>higherUpSpgw=getHigherUpSpMap(model, user);
		
		String higherUpgw=higherUpSpgw.get("spgw");
		if(!Base.isNull(higherUpgw)){
			tableSql.append(" and exists( select 1 from xg_rcsw_zjbbshb x where 1=1");
			tableSql.append(" and exists(select 1 from xg_rcsw_zjbbsqb y where x.sqid=y.id and y.xmid='"+xmdm+"' )");
			tableSql.append(" and x.xtgwid='"+higherUpgw+"' and x.sqid=b.sqid and shzt='tg' )");
		}
		
		// --------------------------下级审核是否已审核-------------------------------
		tableSql.append(" )a left join ( ");
		tableSql.append("  select sqid from xg_rcsw_zjbbshb x ");
		tableSql.append("  where xtgwid = ? ");
		tableSql.append("  and exists(select 1 from xg_rcsw_zjbbsqb y where x.sqid=y.id and y.xmid=? ) ");
		tableSql.append("  and (shzt='tg' or shzt='btg') )b on a.id=b.sqid) a ");
		// --------------------------下级审核是否已审核-------------------------------
		
		tableSql.append(query);
		
		// ====================SQL拼装 end================================

		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(tableSql.toString(),"",dao.unionArray(new String[]{spgw,xmdm,nextSpgw.get("spgw"),xmdm},inputV), colList, model);
		
		return list;
	}
	
	/**
	 * 根据项目代码、
	 * 审批岗位获取上级审核岗位
	 * @author qlj
	 */
	public HashMap<String,String> getHigherUpSpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		// 项目代码
		String xmdm = valueMap.get("xmdm");
		
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh+1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,spgw});
	}
	
	
	/**
	 * 根据项目代码、
	 * 审批岗位获取下级审核岗位
	 * @author qlj
	 */
	public HashMap<String,String> getNextSpMap(BasicModel model,User user) {

		HashMap<String,String>valueMap=model.getValueMap();
		// 项目代码
		String xmdm = valueMap.get("xmdm");
		// 审批岗位
		String spgw = valueMap.get("spgw");
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from  ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select lcid  from ");
		sql.append(" xg_rcsw_zjbbszb ");
		sql.append(" where id = ?  ");
		sql.append(" and spgw=? )  ");
		sql.append(" and a.xh-1=b.xh ) ");
		
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,spgw});
	}
	
	/**
	 * 获取项目最高审核岗位级别
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getMaxSpxh(RcswZjbbForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		// 项目代码
		String xmid=model.getXmid();
		
		StringBuilder sql=new StringBuilder();
	
		sql.append(" select splc,max(xh)xh ");
		sql.append(" from xg_xtwh_spbz ");
		sql.append(" where splc = (select lcid ");
		sql.append(" from xg_rcsw_zjbbszb where id=? ");
		sql.append(" ) ");
		sql.append(" group by splc");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xmid});
		
	}
	
	/**
	 * 初始化审核项目列表
	 * @author qlj 
	 */
	public List<HashMap<String,String>>getCshXmList(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select id xmdm ,zjmc xmmc from xg_rcsw_zjbbszb where lcid<>'no' ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});		
	}
	
	/**
	 * 初始化审核项目列表
	 * @author qlj 
	 */
	public List<HashMap<String,String>>getCshXmList(User user){
		
		String userName=user.getUserName();
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select id xmdm ,zjmc xmmc from xg_rcsw_zjbbszb a where lcid<>'no' ");
		sql.append(" and exists(select 1 from xg_xtwh_spbz b,xg_xtwh_spgwyh c where b.spgw=c.spgw ");
		sql.append(" and a.lcid=b.splc and c.spyh=? )");
		
		return dao.getListNotOut(sql.toString(), new String[]{userName});		
	}
	
	
	/**
	 * 重置审核状态
	 * @param model
	 * @param user
	 * @author qlj 
	 * @throws Exception
	 */
	// ----------------------保存审核表数据 begin ------------------------------
	
	public boolean resultShzt(RcswZjbbForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
	
		String[] sqid=model.getSqid();
		
		sql.append(" update xg_rcsw_zjbbshb set shzt='wsh'  ");
		sql.append(" where  shzt='th' ");
		sql.append(" and sqid in ( ");
		for(int i=0;i<sqid.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append("  ) ");
		
		return dao.runUpdate(sql.toString(),sqid);
	}
	
	/**
	 * 修改审核状态
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateShzt(RcswZjbbForm model,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		String shzt=model.getShzt();
		
		String shyj=model.getShyj();
		
		String userName=user.getUserName();

		String spgw=model.getSpgw();
		
		String[]sqid=model.getSqid();
		
		sql.append(" update xg_rcsw_zjbbshb set shzt=?,shr=?,shsj=?, ");
		sql.append(" shyj=? where  xtgwid=?  ");
		sql.append(" and sqid in ( ");
		
		inputV.add(shzt);
		inputV.add(userName);
		inputV.add(GetTime.getNowTime2());
		inputV.add(shyj);;
		inputV.add(spgw);
	
		
		for(int i=0;i<sqid.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(sqid[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), inputV.toArray(new String[]{}),
				"xg_rcsw_zjbbshb",user);
		
	}
	
	/**
	 * 审核时为退回
	 * 上级审核需改为需重审
	 * @param model
	 * @param user
	 * @author qlj
	 * @throws Exception
	 */
	public boolean updateThzt(RcswZjbbForm model,BasicModel basicModel,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		ArrayList<String>inputV=new ArrayList<String>();
	
		String spgw=getHigherUpSpMap(basicModel,user).get("spgw");
		
		String[]sqid=model.getSqid();
		
		sql.append(" update xg_rcsw_zjbbshb set shzt='xcs',shr='',shsj='', ");
		sql.append(" shyj='' where xtgwid=?  ");
		sql.append(" and sqid in ( ");

		inputV.add(spgw);

		for(int i=0;i<sqid.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			inputV.add(sqid[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), inputV);
		
	}
	

	/**
	 * 修改申请表中申请结果
	 * @param model
	 * @param user
	 * @author qlj
	 * @throws Exception
	 */
	public boolean updateSqjg(RcswZjbbForm model,BasicModel basicModel,User user) throws Exception{
		
		DAO dao=DAO.getInstance();
	
		HashMap<String,String>maxSp=getMaxSpxh(model, user);
		
		String maxxh=maxSp.get("xh");
		
		StringBuilder sql=new StringBuilder();
		// 评奖学年
		sql.append(" update xg_rcsw_zjbbsqb b set sqjg= ");
		sql.append(" (select case when tg = ? then 'tg' when btg >= 1 then 'btg'  ");
		sql.append(" when th >= 1 then 'shz'  when wsh = ? then 'wsh' else 'shz' end shzt ");
		sql.append(" from (select sqid,sum(tg)tg,sum(btg)btg,sum(wsh)wsh,sum(th)th from( ");
		sql.append(" select sqid,case when shzt='tg' then 1 else 0 end tg, ");
		sql.append(" case when shzt='btg' then 1 else 0  end btg, ");
		sql.append(" case when shzt='wsh' then 1 else 0  end wsh, ");
		sql.append(" case when shzt='th' then 1 else 0  end  th ");
		sql.append(" from xg_rcsw_zjbbshb  ");
		sql.append(" ) group by sqid)a where ");
		sql.append(" a.sqid=b.id) ");
		sql.append(" where exists(select 1 from xg_rcsw_zjbbshb  a where b.id=a.sqid) ");

		return dao.runUpdate(sql.toString(), new String[]{maxxh,maxxh});
		
	}
	// ---------------------证件补办审核 end -------------------
	
	/**
	 * 获取假期留校申请列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBbjgList(BasicModel model) throws Exception{
		
		
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
		sql.append(" select a.id,a.xh, ");
		sql.append(" substr(a.sqsj, 0, 4) || '年' || substr(a.sqsj, 5, 2) || '月' || ");
		sql.append(" substr(a.sqsj, 7, 2) || '日' sqsj, ");
		sql.append(" zjmc, ");
		sql.append(" case when sqjg='wsh' then '未审核'  ");
		sql.append(" when sqjg='shz' then '审核中' ");
		sql.append(" when sqjg='btg' then '不通过' ");
		sql.append(" when sqjg='tg' then '通过' ");
		sql.append(" when sqjg='wxsh' then '无需审核' ");
		sql.append(" else '未审核' ");
		sql.append(" end sqjg,b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append(" b.zydm,b.zymc, b.bjdm,b.bjmc from  ");
		sql.append(" (select a.*,b.zjmc from  xg_rcsw_zjbbsqb a, xg_rcsw_zjbbszb b where a.xmid=b.id)a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
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
	 * 保存假期留校审核表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean saveZjbbShb(String id,String xmid) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_rcsw_zjbbshb(sqid,xtgwid) ");
		sql.append(" select id,spgw from ( ");
		sql.append(" select * from xg_rcsw_zjbbsqb  where id=? ) ");
		sql.append(" a left join (select spgw from xg_xtwh_spbz b ");
		sql.append(" where splc=(select lcid from xg_rcsw_zjbbszb where id=? ))b ");
		sql.append(" on 1=1 ");
		
		return dao.runUpdate(sql.toString(), new String[]{id,xmid});
	}
	
	/**
	 * 获取本用户在指定评奖
	 * 项目中的审核岗位信息
	 * (考虑一人多岗)
	 * @author qlj
	 */
	public List<HashMap<String,String>> getSpgwList(String xmdm,User user) {

		String userName=user.getUserName();
	
		StringBuilder sql = new StringBuilder();

		sql.append("  select c.id,c.mc ");
		sql.append("  from xg_rcsw_zjbbszb a,xg_xtwh_spbz b ,xg_xtwh_spgw c,xg_xtwh_spgwyh d ");
		sql.append("  where a.lcid=b.splc and b.spgw=c.id ");
		sql.append("  and b.spgw=d.spgw and d.spyh =?  ");
		sql.append("  and  a.id = ?  ");
		sql.append("  order by b.xh");
		
		DAO dao = DAO.getInstance();

		return dao.getList(sql.toString(), new String[]{userName,xmdm}, new String[]{"id","mc"});
	}
	

	public HashMap<String,String>getZjbbSqInfo(String sqid){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select id,a.xh, substr(a.sqsj, 0, 4) || '年' || substr(a.sqsj, 5, 2) || '月' ||");
		sql.append(" substr(a.sqsj, 7, 2) || '日' sqsj,sqly,");
		sql.append(" xm,nj,xymc,zymc,bjmc,zjmc from   ");
		sql.append(" (select a.*,b.zjmc from xg_rcsw_zjbbsqb a,xg_rcsw_zjbbszb b where a.xmid=b.id )a");
		sql.append(" left join view_xsjbxx b on a.xh=b.xh )where id=? ");
	
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	public List<HashMap<String,String>>getZjbbShInfo(String xmid,String sqid,String spgw){
		
			StringBuilder sql = new StringBuilder();

			List<String>inputV=new ArrayList<String>();
			
			sql.append(" select spgw,mc gwmc,shzt,shsj,shyj,shr, ");
			sql.append(" (select xm from yhb t where b.shr=t.yhm)shrxm ");       
			sql.append(" from ( select spgw,mc from xg_xtwh_spbz a,xg_xtwh_spgw c where splc= ");             
			sql.append(" (select a.lcid from xg_rcsw_zjbbszb a,xg_rcsw_zjbbsqb b where b.id=?  ");   
			sql.append(" and a.id=b.xmid ) ");
			sql.append(" and (exists( select 1 from xg_xtwh_spbz b where splc= ");   
			sql.append(" (select a.lcid from xg_rcsw_zjbbszb a,xg_rcsw_zjbbsqb b where b.id=?    "); 
			sql.append(" and a.id=b.xmid ) ");
			
			inputV.add(sqid);
			inputV.add(sqid);
		
			if(!Base.isNull(spgw)){
				
				sql.append(" and spgw=? ");
				inputV.add(spgw);
			}
			
			sql.append(" and a.xh<=b.xh) ");  
			sql.append(" or exists ");  
			sql.append(" (select 1  from xg_rcsw_zjbbshb b "); 
			sql.append(" where c.id = b.xtgwid "); 
			sql.append(" and b.sqid = ? "); 
			sql.append(" and shzt='th')) "); 
			sql.append(" and a.spgw=c.id order by xh)a left join xg_rcsw_zjbbshb b ");   
			sql.append(" on a.spgw=b.xtgwid  where b.sqid=? ");   
	 
			DAO dao = DAO.getInstance();
			inputV.add(sqid);
			inputV.add(sqid);
		
			return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
		
	}
	
	public String countSpgw(String xmid,User user){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		String userName=user.getUserName();
		
		sql.append(" select count(1)num from xg_xtwh_spbz a where  ");
		sql.append(" a.splc=(select lcid from xg_rcsw_zjbbszb where id=? ) ");
		sql.append(" and exists(select 1 from xg_xtwh_spgwyh b  ");
		sql.append(" where spyh=? and a.spgw=b.spgw) ");
		
		return dao.getOneRs(sql.toString(), new String[]{xmid,userName}, "num");
	}
	
	// ----------------------保存审核表数据 end ------------------------------	
	
	
	public String[] getSpgwByXmid(String xmid) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select spgw from xg_xtwh_spbz where splc=");
		sql.append("(select lcid from xg_rcsw_zjbbszb where id=?) ");
		sql.append("order by xh");
		
		return DAO.getInstance().getArray(sql.toString(), new String[]{xmid}, "spgw");
	}
	
	
	/**
	 * 证件补办结果自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public  List<HashMap<String,String>> getBbjgExportList(BasicModel model,User user) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		//User user=model.getUser();
		
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
		sql.append(" select a.id,a.xh, ");
		sql.append(" substr(a.sqsj, 0, 4) || '年' || substr(a.sqsj, 5, 2) || '月' || ");
		sql.append(" substr(a.sqsj, 7, 2) || '日' sqsj, ");
		sql.append(" zjmc, ");
		sql.append(" case when sqjg='wsh' then '未审核'  ");
		sql.append(" when sqjg='shz' then '审核中' ");
		sql.append(" when sqjg='btg' then '不通过' ");
		sql.append(" when sqjg='tg' then '通过' ");
		sql.append(" when sqjg='wxsh' then '无需审核' ");
		sql.append(" else '未审核' ");
		sql.append(" end sqjg,b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append(" b.zydm,b.zymc, b.bjdm,b.bjmc from  ");
		sql.append(" (select a.*,b.zjmc from  xg_rcsw_zjbbsqb a, xg_rcsw_zjbbszb b where a.xmid=b.id)a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" )a ");

		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQL拼装 end================================
		List<HashMap<String,String>> list = (ArrayList<HashMap<String,String>>) CommonQueryDAO.
		commonQueryforExportList(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
}
