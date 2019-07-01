package xgxt.pjpy.comm.zhcp.zczf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_综测总分_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpZczfDAO extends PjpyCommDAO {

	/**
	 * 获取综测总分表头字段
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZczfTop(ZhcpZczfForm model, User user) {
		DAO dao = DAO.getInstance();
		
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm,xmmc,sjdm from xg_pjpy_zcxmb  ");
		sql.append("  where xn=? and xq=? and nd=?  ");
		sql.append(" order by xmmc,sjdm ");
		return dao.getList(sql.toString(), new String[] {zcxn,zcxq,zcnd}, new String[] {
				"xmdm", "xmmc" });
	}
	
	/**
	 * 获取综测显示信息
	 * @param model
	 * @param user
	 * @param colList
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getZhcpZczf(ZhcpZczfForm model,User user,String[] colList) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		SearchService searchService=new SearchService();
		//不需要考虑的权限
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = searchService.getSearchTjByUser("a", user);	
		searchTj+=searchTjByUser;
		String[] inputSearch = SearchService.getTjInput(model.getSearchModel());
		
		List<HashMap<String, String>>top=getZczfTop(model, user);
		 
		StringBuilder sql=new StringBuilder();
		
		String zcXn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcXq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcNd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ==================显示字段===================
		StringBuilder xszd=new StringBuilder();
		for(int i=0;i<top.size();i++){
			HashMap<String,String>topMap=top.get(i);
			
			xszd.append(",");
		
			xszd.append(topMap.get("xmdm"));
		}
		// ==================显示字段end===================
		
		sql.append(" select rownum r,a.*,xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xh pkValue from( ");
		sql.append(" select xn,xq,nd,xh,xm,nj,xymc,xydm,zymc,zydm,bjdm,bjmc ");
		sql.append(" ,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8,zd9,zd10,zd11,zd12,zd13");
		sql.append(" ,zd14,zd15,zd16,zd17,zd18,zd19,zd20,zd21,zd22,zd23,zd24,zd25,zd26,zd27,zd28,zd29,zd30");
		sql.append(" ,zcfnjxypm,zcfnjzypm,zcfbjpm,zyfnjxypm,zyfnjzypm,zyfbjpm from( select  a.*" );
		sql.append(" ,b.nj,b.xymc,b.zymc,b.bjmc ");
		sql.append(" ,xydm,zydm,bjdm,xm ");
		sql.append("  from(select * from xg_pjpy_zhcpb where xn=? and xq=? and nd=?)  ");
		sql.append(" a left join ");
		
		if("ss".equalsIgnoreCase(model.getRyk())){
			sql.append(" view_xsjbxx ");
		}else{
			sql.append(model.getPjry_sql());
		}
		
		sql.append(" b on a.xh=b.xh ");
		sql.append(" order by bjdm  ,zd1 desc ");
		sql.append(" )a)a  where 1=1 ");
		sql.append(searchTj);
		
		String[]inPutList={zcXn,zcXq,zcNd};
		return CommonQueryDAO.commonQuery(sql.toString(),"",  dao.unionArray(inPutList, inputSearch), colList, model);
	}
	
	/**
	 * 先删后增列选
	 * @param yhm
	 * @param yhlx
	 * @param xszd
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public int[] saveKindChoose(String yhm,String yhlx,String xszd,String path) throws Exception{
		DAO dao=DAO.getInstance();
		String[]sql=new String[2];
		sql[0]=" delete from xg_pjpy_zczf_xszdszb where yhm='"+yhm+"' and yhlx='"+yhlx+"' and path='"+path+"' ";
		sql[1]=" insert into xg_pjpy_zczf_xszdszb(yhm,yhlx,path,xxszd) values('"+yhm+"','"+yhlx+"','"+path+"','"+xszd+"') ";
		//只需判断是否成功插入
		int[]result=dao.runBatch(sql);
		return  new int[]{result[1]};
	}
	
	/**
	 * 获取指定用户选中的列
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public HashMap<String, String> getCheckKind(ZhcpZczfForm model, User user) {
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		String yhlx="tea";
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			yhlx="stu";
		}
		
		sql.append("  select * from xg_pjpy_zczf_xszdszb where 1=1 ");
		sql.append(" and yhm=? ");
		sql.append(" and yhlx=? ");
		sql.append(" and path=?");
		sql.append(" and rownum=1 ");
		return dao.getMap(sql.toString(), new String[]{user.getUserName(),yhlx,model.getPath()}, new String[]{"xxszd"});
		
	}
	
	/**
	 * 获取最底级项目信息
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getDjxmList(ZhcpZczfForm model,User user){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		
		sql.append(" select  xmdm,lyb,zd,glxn,glxq,glnd,condition from xg_pjpy_zcxmb a where xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"' ");
		sql.append(" and not exists (select 1  from xg_pjpy_zcxmb b ");
		sql.append(" where  xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"' and a.xmdm = b.sjdm)  ");
		sql.append(" and lyb is not null order by xmdm ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmdm","lyb","zd","glxn","glxq","glnd","condition"});
	}
	
	/**
	 * 最底级项目计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean djxmjs(ZhcpZczfForm model,User user) throws Exception{
		
		SearchService searchService=new SearchService();
		
		//构建过滤条件
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		
		String searchTjByUser = searchService.getSearchTjByUser("c", user);	
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		
		String xmdm=model.getXmdm();
		String lyb=model.getLyb();
		String zd=model.getZd();
		String glxn=model.getGlxn();
		String glxq=model.getGlxq();
		String glnd=model.getGlnd();
		
		if(!Base.isNull(glxn)){
			query.append(" and xn= ? ");
			if("pjxn".equalsIgnoreCase(glxn)){
				inputV.add(model.getPjxn());
			}else{
				inputV.add(Base.currXn);
			}
		}
		if(!Base.isNull(glxq)){
			query.append(" and xq= ? ");
			if("pjxq".equalsIgnoreCase(glxq)){
				inputV.add(model.getPjxq());
			}else{
				inputV.add(Base.currXq);
			}
		}
		if(!Base.isNull(glnd)){
			query.append(" and nd= ? ");
			if("pjnd".equalsIgnoreCase(glnd)){
				inputV.add(model.getPjnd());
			}else{
				inputV.add(Base.currNd);
			}
		}
		
		sql.append(" update xg_pjpy_zhcpb a set "+xmdm+"=(select "+zd+" from "+lyb+" b  ");
		sql.append(" where a.xh=b.xh   ");
		sql.append(query);
		sql.append(" ) ");
		sql.append(" where  1=1 ");
		sql.append(query);
		sql.append(" and exists(select 1 from view_xsjbxx c where 1=1 ");
		sql.append(" and a.xh=c.xh ");
		sql.append(searchTjByUser);
		sql.append(" ) ");
		inputV.addAll(inputV);
		return dao.runUpdate(sql.toString(),inputV.toArray(new String[]{}));
	}
	
	/**
	 * 获取综测比例代码表中比列类型
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBldmList(ZhcpZczfForm model,User user){
		DAO dao=DAO.getInstance();
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select bldm from xg_pjpy_zcbldmb where 1=1 ");
		sql.append(" and xn=? and xq=? and nd=? ");
		
		return dao.getList(sql.toString(), new String[]{zcxn,zcxq,zcnd}, new String[]{"bldm"});
	}
	
	/**
	 * 获取有子项目的项目列表
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXjsxmList(ZhcpZczfForm model,User user){
		DAO dao=DAO.getInstance();
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xmdm from xg_pjpy_zcxmb a ");
		sql.append(" where not exists (select xmdm ");
		sql.append(" from (select * from xg_pjpy_zcxmb a where not exists ");
		sql.append(" (select 1 from xg_pjpy_zcxmb b where a.xmdm = b.sjdm) ");
		sql.append(" and xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"' ) b ");
		sql.append(" where a.xmdm = b.xmdm and a.xn=b.xn) ");
		//倒顺排列用于计算
		sql.append(" and xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"' order by xmjb desc ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmdm"});
	}
	
	/**
	 * 获取综测项目详细配置信息
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getZcxmInfo(ZhcpZczfForm model,User user){
		
		DAO dao=DAO.getInstance();
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.xmdm,sjdm,a.bldm,a.bl,lyb,zd,condition,xmjb ");
		sql.append(" from( select a.xn,a.xq,a.nd,a.xmdm,sjdm,bldm,bl,xmjb ");
		sql.append(" from xg_pjpy_zcxmb a  left join xg_pjpy_zcblb b  ");
		sql.append(" on a.xmdm = b.xmdm and a.xn = b.xn and a.xq = b.xq  and a.nd = b.nd ");
		sql.append(" where a.xn='"+zcxn+"' and a.xq='"+zcxq+"' and a.nd='"+zcnd+"')a ");
		sql.append(" left join xg_pjpy_zcbldmb b on a.bldm=b.bldm and a.xn=b.xn");
		sql.append(" and a.xq=b.xq and a.nd=b.nd ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmdm","sjdm","bldm","bl","lyb","zd","condition","xmjb"});
		
	}
	
	public List<HashMap<String,String>>getZcjsSql(ZhcpZczfForm model,User user){
		//比例代码列表
		List<HashMap<String,String>>bldmList=getBldmList(model,user);
		// 获取需要计算的项目
		List<HashMap<String,String>>xjsxmList=getXjsxmList(model,user);
		// 获取综测项目列表
		List<HashMap<String,String>>zcxmList=getZcxmInfo(model,user);
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		List<HashMap<String,String>>zcjsList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<bldmList.size();i++){
			HashMap<String,String>bldmMap=bldmList.get(i);
			for(int j=0;j<xjsxmList.size();j++){
				HashMap<String,String>xjsxmMap=xjsxmList.get(j);
				HashMap<String,String>zcjsMap= new HashMap<String,String>();
				StringBuilder querySql=new StringBuilder();
				StringBuilder sumZd=new StringBuilder();
				StringBuilder updateSql=new StringBuilder();
				
				updateSql.append("  update xg_pjpy_zhcpb a set ");
				boolean first=true;
				String lyb=null;
				for(int z=0;z<zcxmList.size();z++){
					// 综测项目详细配置信息
					HashMap<String,String>zcxmMap=zcxmList.get(z);
					
					if(bldmMap.get("bldm").equalsIgnoreCase(zcxmMap.get("bldm"))
							&& xjsxmMap.get("xmdm").equalsIgnoreCase(zcxmMap.get("sjdm"))){
						if(!first){
							sumZd.append("+");
						}else {
							updateSql.append(zcxmMap.get("sjdm"));
							updateSql.append(" = ");
							lyb=zcxmMap.get("lyb");
							querySql.append(" and exists(select 1 from "+zcxmMap.get("lyb")+"  b where ");
							querySql.append(zcxmMap.get("condition"));
							querySql.append("  and a.xh=b.xh)  ");
							
							zcjsMap.put("xmjb", zcxmMap.get("xmjb"));
							
							first=false;
						}
						sumZd.append(" to_number(nvl(");
						sumZd.append(zcxmMap.get("xmdm"));
						sumZd.append(" ,0))* to_number(nvl(");
						sumZd.append(zcxmMap.get("bl"));
						sumZd.append(" ,0))/100 ");
					}
				}
				updateSql.append(" ( ");
				updateSql.append(sumZd);
				updateSql.append(" ) where 1=1 ");
				if(!Base.isNull(lyb)){
					updateSql.append(querySql);
				}
				updateSql.append(" and xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' ");
				zcjsMap.put("sql", updateSql.toString());
				zcjsList.add(zcjsMap);
			}
			
		}
		
		return zcjsList;
	}
	
	/**
	 * 获取项目级别列表
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXmjbList(){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		sql.append(" select xmjb from xg_pjpy_zcxmb where 1=1 ");
		sql.append(" and xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"' ");
		sql.append("  group by xmjb  order by xmjb desc ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmjb"});
	}
	
	/**
	 * 计算班级排名,并且放入临时表
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean bjpmjs(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '"+zcxn+"','"+zcxq+"','"+zcnd+"',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" bjdm order by to_number(zd1) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"'  ");
		sql.append(" ) b) a left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from xg_pjpy_zhcplsb ";
		sqlArr[1]=sql.toString();
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新班级排名
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean updateBjpm(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb  a set zcfbjpm=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=sql.toString();
		sqlArr[1]=" delete from xg_pjpy_zhcplsb ";
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 计算年级学院排名,并且放入临时表
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean njxypmjs(ZhcpZczfForm model,User user) throws Exception{

		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '"+zcxn+"','"+zcxq+"','"+zcnd+"',a.xh,a.xypm from( ");
		sql.append(" select a.xh, a.xypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" nj,xydm order by to_number(zd1) desc nulls last)) xypm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"'  ");
		sql.append(" ) b) a left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from xg_pjpy_zhcplsb ";
		sqlArr[1]=sql.toString();
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 计算年级学院智育排名,并且放入临时表
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean njxyZypmjs(ZhcpZczfForm model,User user) throws Exception{

		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '"+zcxn+"','"+zcxq+"','"+zcnd+"',a.xh,a.xypm from( ");
		sql.append(" select a.xh, a.xypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" nj,xydm order by to_number(zd3) desc nulls last)) xypm ");
		sql.append(" from (select a.xh, zd3, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"'  ");
		sql.append(" ) b) a left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from xg_pjpy_zhcplsb ";
		sqlArr[1]=sql.toString();
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 计算年级专业排名,并且放入临时表
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean njzyZypmjs(ZhcpZczfForm model,User user) throws Exception{

		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '"+zcxn+"','"+zcxq+"','"+zcnd+"',a.xh,a.zypm from( ");
		sql.append(" select a.xh, a.zypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" nj,zydm order by to_number(zd3) desc nulls last)) zypm ");
		sql.append(" from (select a.xh, zd3, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"'  ");
		sql.append(" ) b) a left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from xg_pjpy_zhcplsb ";
		sqlArr[1]=sql.toString();
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新年级学院排名
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean updateXypm(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb  a set zcfnjxypm=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=sql.toString();
		sqlArr[1]=" delete from xg_pjpy_zhcplsb ";
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新年级学院智育排名
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean updateNjXyZypm(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb  a set zyfnjxypm=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=sql.toString();
		sqlArr[1]=" delete from xg_pjpy_zhcplsb ";
		
		return saveArrDate(sqlArr);
	}
	
	
	/**
	 * 计算年级专业排名,并且放入临时表
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean njzypmjs(ZhcpZczfForm model,User user) throws Exception{

		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '"+zcxn+"','"+zcxq+"','"+zcnd+"',a.xh,a.zypm from( ");
		sql.append(" select a.xh, a.zypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" nj,zydm order by to_number(zd1) desc nulls last)) zypm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"'  ");
		sql.append(" ) b) a left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from xg_pjpy_zhcplsb ";
		sqlArr[1]=sql.toString();
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 获取综测分相关信息
	 * 
	 * @param xh
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZcfInfoList(String xh) {

		DAO dao = DAO.getInstance();
		String zcpm = ZhcpJbszForm.zcjbszModel.getZcpm();
		String zcxn = ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq = ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd = ZhcpJbszForm.zcjbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm,xmmc from xg_pjpy_zcxmb ");
		sql.append("where xmjb = '2' ");
		sql.append("and xn = ? ");
		sql.append("and xq = ? ");
		sql.append("and nd = ? ");
		sql.append("union all ");
		sql.append("select xmdm,xmmc from xg_pjpy_zcxmb ");
		sql.append("where xmjb = '1' ");
		sql.append("and xn = ? ");
		sql.append("and xq = ? ");
		sql.append("and nd = ? ");

		List<HashMap<String, String>> xmList = dao.getList(sql.toString(),
				new String[] { zcxn, zcxq, zcnd, zcxn, zcxq, zcnd },
				new String[] { "xmdm", "xmmc" });
		HashMap<String,String> map = new HashMap<String,String>();
		
		//综测分排名，根据flowControl.xml配置取相应的排名
		if ("3".equalsIgnoreCase(zcpm)){
			map.put("xmdm", "zcfbjpm");
		} else if ("2".equalsIgnoreCase(zcpm)){
			map.put("xmdm", "zcfnjzypm");
		} else {
			map.put("xmdm", "zcfnjxypm");
		}
		
		map.put("xmmc", "综合测评排名");
		xmList.add(map);
		
		sql = new StringBuilder();
		
		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				if(i!=0){
					sql.append(" union all ");
				}
				sql.append("select '"+xmList.get(i).get("xmmc")+"' mc, ");
				sql.append(xmList.get(i).get("xmdm")+" fs ");
				sql.append("from xg_pjpy_zhcpb ");
				sql.append("where xh = '"+xh+"' ");
				sql.append("and xn = '"+zcxn+"' ");
				sql.append("and xq = '"+zcxq+"' ");
				sql.append("and nd = '"+zcnd+"' ");
			}
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {  },
				new String[] { "mc", "fs" });

		return list;
	}
	
	/**
	 * 更新年级专业排名
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean updateZypm(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb  a set zcfnjzypm=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=sql.toString();
		sqlArr[1]=" delete from xg_pjpy_zhcplsb ";
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新年级专业智育排名
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean updateNjZyZypm(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb  a set zyfnjzypm=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=sql.toString();
		sqlArr[1]=" delete from xg_pjpy_zhcplsb ";
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 计算班级智育排名,并且放入临时表
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean bjZypmjs(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '"+zcxn+"','"+zcxq+"','"+zcnd+"',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" bjdm order by to_number(zd3) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd3, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '"+zcxn+"' and xq = '"+zcxq+"' and nd = '"+zcnd+"'  ");
		sql.append(" ) b) a left join ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			sql.append(" view_xsjbxx ");
		}else{
			
			sql.append(" (select * from xg_pjpy_xsb where sfysz='是' ");
			sql.append("and  pjxn=(select pjxn from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjxq=(select pjxq from xg_pjpy_xtszb where rownum=1) ");
			sql.append("and  pjnd=(select pjnd from xg_pjpy_xtszb where rownum=1)) ");
		}
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from xg_pjpy_zhcplsb ";
		sqlArr[1]=sql.toString();
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新年级专业智育排名
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception 
	 */
	public boolean updateBjZypm(ZhcpZczfForm model,User user) throws Exception{
		
		// ===========综测周期=============
		String zcxn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String zcxq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String zcnd=ZhcpJbszForm.zcjbszModel.getPjnd();
		// ===========综测周期end=============
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb  a set zyfbjpm=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='"+zcxn+"' and xq='"+zcxq+"' and nd='"+zcnd+"' ");
		
		String[]sqlArr=new String[2];
		sqlArr[0]=sql.toString();
		sqlArr[1]=" delete from xg_pjpy_zhcplsb ";
		
		return saveArrDate(sqlArr);
	}
	
	/**
	 * 获得综测项目扩展字段信息（本评奖学期）
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getKzzdList(User user) {

		DAO dao = DAO.getInstance();
		PjpyGeneralForm model=PjpyGeneralForm.getJbszModel();
		
		model.getZczq();
		model.getPjxn();
		model.getPjxq();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.kzzd,a.xsmc,a.zdlx,a.checked ");
		sql.append(" ,a.source_table,a.select_dm,a.select_mc ");
		sql.append(" from xg_pjpy_zckzzdb a ");
		sql.append(" where 1=1 ");
		sql.append(" and sfxs='1' ");
		
		if("xn".equalsIgnoreCase(model.getZczq())){
			
			sql.append(" and a.xn='"+model.getPjxn()+"' ");
		
		}else{
			sql.append(" and a.xn='"+model.getPjxn()+"' ");
			sql.append(" and a.xq='"+model.getPjxq()+"' ");
		}
		
		System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "kzzd",
						"xsmc", "zdlx", "checked", "source_table", "select_dm",
						"select_mc" });

		return list;
	}
	

	
	
	/**
	 * 江苏信息综合测评汇总取必修课成绩
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 */
	public List<String[]> getBxcjList(String xn,String xq,String bjdm){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh, xm, kcmc, cj, xymc, bjmc  from (")
		   .append("select a.xh,b.xm,b.nj,b.xydm,b.zydm,b.bjdm,")
		   .append("a.kcmc,a.cj,b.xymc,b.bjmc from cjb a,view_xsjbxx b ")
		   .append("where a.xh=b.xh and (a.kcxz !='3' or a.kcxz is null) ")
		   .append("and a.xn =? and a.xq=?) where bjdm=?");
		
		DAO dao = DAO.getInstance();
		return dao.rsToVator(sql.toString(), new String[]{xn,xq,bjdm}, 
				new String[]{"xh", "xm", "kcmc", "cj", "xymc", "bjmc" });
	}
	
	
	/**
	 * 江苏信息综合测评汇总取必修课程名称
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public String[] getBxkcArr(String xn,String xq,String bjdm) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct a.kcmc from cjb a where ")
		   .append("(kcxz !='3' or kcxz is null ) ")
		   .append("and exists (select 1 from view_xsjbxx b ")
		   .append("where a.xh=b.xh and b.bjdm = ? ) and a.xn = ? ")
		   .append("and a.xq = ? order by a.kcmc");
		
		DAO dao = DAO.getInstance();
		return dao.getRs(sql.toString(), new String []{bjdm,xn,xq}, "kcmc");
	}
	
	
	
	/**
	 * 江苏信息综合测评汇总取综合分成绩
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 */
	public List<String[]> getZhcpList(String xn ,String xq ,String bjdm){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,zd2 dcjzf,zd3 zcjzf,zd4 tcjzf,zd1 kpf,")
		   .append("zd5 cxdj,zcfbjpm bjpm from xg_pjpy_zhcpb a where a.xn=? ")
		   .append("and a.xq=? and exists (select 1 from view_xsjbxx b ")
		   .append("where b.bjdm=? and a.xh=b.xh)");
		
		DAO dao = DAO.getInstance();
		return dao.rsToVator(sql.toString(), new String[]{xn,xq,bjdm}, 
				new String[]{"xh","zcjzf", "tcjzf", "dcjzf", "kpf", "cxdj","bjpm" });
	}
	
	
	/**
	 * 根据班级查找学生
	 * @param bjdm
	 * @return
	 * @throws Exception 
	 */
	public String[] getStuByBjdm(String bjdm) throws Exception{
		String sql = "select xh from view_xsjbxx where bjdm = ?";
		DAO dao = DAO.getInstance();
		return dao.getRs(sql.toString(), new String []{bjdm}, "xh");
	}
}
