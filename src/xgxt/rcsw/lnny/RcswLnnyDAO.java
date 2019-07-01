package xgxt.rcsw.lnny;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class RcswLnnyDAO {
	
	/**
	 * 项目类型
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXmlxList(){
		DAO dao=DAO.getInstance();
		String sql=" select xmlxdm dm,xmlxmc mc from xg_xtwh_yrgflxb a ";
		sql+="  where exists (select 1 from xg_xtwh_yrgfdmb b where a.xmlxdm=b.xmlxdm) ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 项目
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXmList(){
		DAO dao=DAO.getInstance();
		String sql=" select xmdm dm,xmmc mc,xmlxdm lx from xg_xtwh_yrgfdmb order by xmdm ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc","lx"});
	}
	
	public List<HashMap<String,String>>getXmList(RcswLnnyForm myForm){
		DAO dao=DAO.getInstance();
		String sql=" select xmdm dm,xmmc mc,xmlxdm lx,xmxz,fz from xg_xtwh_yrgfdmb where xmlxdm=? order by xmdm";
		return dao.getList(sql, new String[]{myForm.getXmlxdm()}, new String[]{"dm","mc","lx","fz","xmxz"});
	}
	
	/**
	 * 显示已保存项目分
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * @throws Exception
	 */
	public ArrayList<String[]>getXmFzList(RcswLnnyForm myForm,HttpServletRequest request) throws Exception{
	
		StringBuilder sql=new StringBuilder();
		StringBuilder firstFloor=new StringBuilder();
		StringBuilder query=new StringBuilder();
		User user=myForm.getUser();
		MakeQuery mQuery=new MakeQuery();
		
		//获取特定类型的项目信息
		List<HashMap<String,String>>xmList=getXmList(myForm);
		
		//Group By字段
		String []groupBy=new String[xmList.size()];
		//输出字段,根据统计结果拼接
		String []outPut=new String[4+4*xmList.size()];
		//输出字段
		outPut[0]="xh";
		outPut[1]="xm";
		outPut[2]="bjmc";
		outPut[3]="zf";
		//表头
		if("xy".equalsIgnoreCase(user.getUserType())){
			myForm.setXydm(user.getUserDep());
		}
		
		String[]colList={"nj","xydm","zydm","bjdm"};
		String[]colLikeList={"xh","xm"};
		
		mQuery.makeQuery(colList, colLikeList, myForm);
		
		sql.append(" select rownum r,xh,xm,bjmc,bjdm,xydm,zydm,nj,dfsj  ");
		for(int i=0;i<xmList.size();i++){
			HashMap<String,String>xmMap=xmList.get(i);
			sql.append(",xmmc"+xmMap.get("dm"));
			sql.append(",xmxz"+xmMap.get("dm"));
			sql.append(",fz"+xmMap.get("dm"));
			sql.append(" , nvl(case when instr(to_char(xmdm"+xmMap.get("dm")+"),'.',1,1)=1 then '0'||to_char(to_char(xmdm"+xmMap.get("dm")+")) ");
			sql.append(" else (case when instr(to_char(xmdm"+xmMap.get("dm")+"),'.',1,1)=2 and instr(to_char(xmdm"+xmMap.get("dm")+"),'-',1,1)=1 then '-0.'||substr(to_char(to_char(xmdm"+xmMap.get("dm")+")),3,length(to_char(to_char(xmdm"+xmMap.get("dm")+")))) else to_char(to_char(xmdm"+xmMap.get("dm")+")) end ) end,0)xmdm"+xmMap.get("dm"));
		}
		sql.append(" , nvl(case when instr(zf,'.',1,1)=1 then '0'||to_char(zf) ");
		sql.append(" else (case when instr(zf,'.',1,1)=2 and instr(zf,'-',1,1)=1 then '-0.'||substr(to_char(zf),3,length(to_char(zf)))else to_char(zf) end) end,0)zf  ");
		sql.append(" from( select a.* ");
		firstFloor.append(" select xh,xm,bjmc,bjdm,xydm,zydm,nj,case when dfsj is null then '"+myForm.getDfsj()+"' else dfsj end dfsj ");
		int m=0;
		String zf="";
		for(int i=0;i<xmList.size();i++){
			HashMap<String,String>xmMap=xmList.get(i);
			firstFloor.append("  ,sum((case  when xmdm = '"+xmMap.get("dm")+"' ");
			firstFloor.append(" then  fz else '0' end))xmdm"+xmMap.get("dm")+"  ");
			firstFloor.append(",xmmc"+xmMap.get("dm"));
			firstFloor.append(",xmxz"+xmMap.get("dm"));
			firstFloor.append(",fz"+xmMap.get("dm"));
			if(i==0){
				zf+="xmdm"+xmMap.get("dm");
			}else{
				zf+="+ xmdm"+xmMap.get("dm");
			}
			//输出字段
			outPut[m+4]="xmdm"+xmMap.get("dm");
			m++;
			outPut[m+4]="xmmc"+xmMap.get("dm");
			m++;
			outPut[m+4]="xmxz"+xmMap.get("dm");
			m++;
			outPut[m+4]="fz"+xmMap.get("dm");
			m++;
		}

		sql.append(","+zf+" zf from(");
		sql.append(firstFloor);
		sql.append(" from (select a.xh,a.xm,a.bjmc,b.xmdm,b.fz,b.dfsj,bjdm, xydm, zydm, nj  ");
		
		for(int i=0;i<xmList.size();i++){
			HashMap<String,String>xmMap=xmList.get(i);
			sql.append("  ,'"+xmMap.get("mc")+"' xmmc"+xmMap.get("dm"));
			sql.append("  ,'"+xmMap.get("xmxz")+"' xmxz"+xmMap.get("dm"));
			sql.append("  ,'"+xmMap.get("fz")+"' fz"+xmMap.get("dm"));
			groupBy[i]="xmmc"+xmMap.get("dm");
			
		}
		sql.append(" from view_xsjbxx a left join ");
		sql.append(" (select xh,xmdm,fz,dfsj from xg_rcsw_yrgfb where dfsj='"+myForm.getDfsj()+"')b on a.xh=b.xh) group by xh, xm,bjmc, dfsj,bjdm,xydm,zydm,nj ");
		for(int i=0;i<xmList.size();i++){
			
			sql.append(","+groupBy[i]);
			
		}
		sql.append(" )a )a ");
		query.append(mQuery.getQueryString());
		query.append(" and dfsj='"+myForm.getDfsj()+"'   ");
		
		if("true".equalsIgnoreCase(user.getFdyQx()) && "true".equalsIgnoreCase(user.getBzrQx())){
			query.append(" and exists(select 1 from bzrbjb b where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ");
			query.append(" or select 1 from fdybjb a where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ");
		}else if("true".equalsIgnoreCase(user.getBzrQx())){
			query.append(" and exists(select 1 from bzrbjb b where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ");
		}else if("true".equalsIgnoreCase(user.getFdyQx())){
			query.append(" and exists(select 1 from fdybjb b where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ");
		}	
		System.out.println(sql.toString()+ mQuery.getQueryString());
		
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(), mQuery.getInputList(), outPut, myForm);
	}
	
	public List<String[]>getTjxx(RcswLnnyForm myForm,HttpServletRequest request) throws Exception{
		
		DAO dao=DAO.getInstance();
		
		User user=myForm.getUser();
		StringBuilder firstFloor=new StringBuilder();
		StringBuilder secoundFloor=new StringBuilder();
		
		//项目类型列表
		List<HashMap<String,String>>xmlxList=getXmlxList();
		//统计字段
		String[]tjzd=new String[xmlxList.size()*2];
		String[]tjzdbt=new String[xmlxList.size()*2];
		String[]outPut=new String[4+tjzd.length];
		String[]colListCN=new String[4+tjzd.length];
		//查询条件
		MakeQuery mQuery=new MakeQuery();
		String [] colList={"xn","xq","nd","nj","xydm","zydm","bjdm"};
		String [] colLikeList={"xh","xm"};
		
		if("xy".equalsIgnoreCase(user.getUserType())){
			myForm.setXydm(user.getUserDep());
		}
		mQuery.makeQuery(colList, colLikeList, myForm);
		// -------------------第一层--------------------------
		//统计字段拼接
		firstFloor.append("select xh,xm, xn,xq,nd,xydm,xymc,zydm,zymc,bjdm,bjmc,nj ");
		int m=0;//计数
		for(int i=0;i<xmlxList.size();i++){
			HashMap<String,String>xmlxMap=xmlxList.get(i);
			firstFloor.append(", case when xmlxdm = '"+xmlxMap.get("dm")+"' and xmxz = '加分' ");
			firstFloor.append(" then sum(fz)  end xmlx"+xmlxMap.get("dm")+"jia ");
			firstFloor.append(", case when xmlxdm = '"+xmlxMap.get("dm")+"' and xmxz = '减分' ");
			firstFloor.append(" then sum(fz)  end xmlx"+xmlxMap.get("dm")+"jian ");
			tjzd[m]="xmlx"+xmlxMap.get("dm")+"jia";
			tjzdbt[m]=xmlxMap.get("mc")+"(加分)";
			tjzd[++m]="xmlx"+xmlxMap.get("dm")+"jian";
			tjzdbt[m]=xmlxMap.get("mc")+"(减分)";
			m++;
		}
		
		String query="";
		//打分时间
		if(!"".equalsIgnoreCase(myForm.getKssj())
			&& null!=myForm.getKssj()){
			query+=" and dfsj>="+myForm.getKssj();
		}
		if(!"".equalsIgnoreCase(myForm.getJssj())
				&& null!=myForm.getJssj()){
				query+=" and dfsj<="+myForm.getJssj();
		}
		//查询条件
		query=mQuery.getQueryString()+query;
		if ("true".equalsIgnoreCase(user.getFdyQx())
				&& "true".equalsIgnoreCase(user.getBzrQx())) {
			query += " and exists(select 1 from bzrbjb a where b.bjdm=a.bjdm and zgh='"
					+ user.getUserName() + "') ";
			query += " or select 1 from fdybjb a where b.bjdm=a.bjdm and zgh='"
					+ user.getUserName() + "') ";
		} else if ("true".equalsIgnoreCase(user.getBzrQx())) {
			query += " and exists(select 1 from bzrbjb a where b.bjdm=a.bjdm and zgh='"
					+ user.getUserName() + "') ";
		} else if ("true".equalsIgnoreCase(user.getFdyQx())) {
			query += " and exists(select 1 from fdybjb a where b.bjdm=a.bjdm and zgh='"
					+ user.getUserName() + "') ";
		}	
			
		firstFloor.append(" from xg_view_rcsw_yrgf b "+query+" group by xm,xn, xq, nd, xh, xmlxdm, xmxz ");
		firstFloor.append(" ,xydm,xymc,zydm,zymc,bjdm,bjmc,nj ");
		// -------------------第一层结束--------------------------
		
		// -------------------第二层--------------------------
		secoundFloor.append(" select  xm,xn, xq,nd, xh ,xydm,xymc,zydm,zymc,bjdm,bjmc,nj ");
		String zf="";
		for(int i=0;i<tjzd.length;i++){
			outPut[3+i]=tjzd[i];
			colListCN[3+i]=tjzdbt[i];
			secoundFloor.append(" , sum(case when "+tjzd[i]+" is null then 0 else  "+tjzd[i]+" end)"+tjzd[i]);
			if(i==0){
				zf+=tjzd[i];
			}else{
				zf+="+"+tjzd[i];
			}
		}
		outPut[3+tjzd.length]="zf";
		colListCN[3+tjzd.length]="总分";
		secoundFloor.append(" from (");
		//将第一层嵌套在第二层内部
		secoundFloor.append(firstFloor);
		secoundFloor.append(" ) group by xn, xq, nd,xm, xh ,xydm,xymc,zydm,zymc,bjdm,bjmc,nj ");
		// -------------------第二层结束--------------------------
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select rownum r,xn,xq,nd,xh,xm,xydm,xymc,zydm,zymc,bjdm,bjmc,nj");
		for(int i=0;i<tjzd.length;i++){
			outPut[3+i]=tjzd[i];
			colListCN[3+i]=tjzdbt[i];
			sql.append(" , nvl(case when instr("+tjzd[i]+",'.',1,1)=1 then '0'||to_char("+tjzd[i]+") ");
			sql.append(" else (case when instr("+tjzd[i]+",'.',1,1)=2 and instr("+tjzd[i]+",'-',1,1)=1 then '-0.'||substr(to_char("+tjzd[i]+"),3,length(to_char("+tjzd[i]+"))) else to_char("+tjzd[i]+") end ) end,0)"+tjzd[i]);
		}
		sql.append(" , nvl(case when instr(zf,'.',1,1)=1 then '0'||to_char(zf) ");
		sql.append(" else (case when instr(zf,'.',1,1)=2 and instr(zf,'-',1,1)=1 then '-0.'||substr(to_char(zf),3,length(to_char(zf)))else to_char(zf) end) end,0)zf  ");
		sql.append("from ( ");
		sql.append(" select  a.*,"+zf+" zf from  (");
		sql.append(secoundFloor);
		sql.append(")a) ");
		
		outPut[0]="xh";
		outPut[1]="xm";
		outPut[2]="bjmc";
		colListCN[0]="学号";
		colListCN[1]="姓名";
		colListCN[2]="班级";
		request.setAttribute("topTr", dao.arrayToList(colListCN, colListCN));
		return CommonQueryDAO.commonQuery(sql.toString(), "", mQuery.getInputList(),outPut , myForm);
	}
	
	/**
	 * 获取统计项目类型
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getTjXmlx(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("  select a.xmlxdm,b.xmlxmc,a.jls from  ");
		sql.append(" (select distinct(xmlxdm)xmlxdm,count(1)jls from xg_xtwh_yrgfdmb group by xmlxdm)a ");
		sql.append(" left join xg_xtwh_yrgflxb b on a.xmlxdm = b.xmlxdm order by a.xmlxdm ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmlxdm","xmlxmc","jls"});
	}
	
	/**
	 * 获取统计项目
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getTjXm(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select xmlxdm,xmdm,xmmc,xmxz,fz from xg_xtwh_yrgfdmb order by xmlxdm,xmdm ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmlxdm","xmdm","xmmc","xmxz","fz"});
	}
	
	/**
	 * 获取统计信息
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws  Exception 
	 */
	public List<String[]>getTjxx(RcswLnnyForm myForm) throws  Exception{
		
		StringBuilder sql=new StringBuilder();
		StringBuilder hzl=new StringBuilder();
		StringBuilder firstFloor=new StringBuilder();
		StringBuilder secoundFloor=new StringBuilder();
		User user=myForm.getUser();
		//项目列表
		List<HashMap<String,String>>xmList=getTjXm();
		//输出字段
		String[]outPut=new String[4+xmList.size()];
		
		// ====================行转列======================
		hzl.append(" select xh,xm,xn,xq,nd,bjmc ");
		// ====================行转列end===================
		
		// ==============第一层================
		MakeQuery mQuery=new MakeQuery();
		String [] colList={"xn","xq","nd","nj","xydm","zydm","bjdm"};
		String [] colLikeList={"xh","xm"};
		mQuery.makeQuery(colList, colLikeList, myForm);
		firstFloor.append(" select xh,xm, xn, xq, nd, dfsj,xmlxdm, xmdm,bjmc,xmxz, sum(fz) fz ");
		String query="";
		//打分时间
		if(!"".equalsIgnoreCase(myForm.getKssj())
			&& null!=myForm.getKssj()){
			query+=" and dfsj>="+myForm.getKssj();
		}
		if(!"".equalsIgnoreCase(myForm.getJssj())
				&& null!=myForm.getJssj()){
				query+=" and dfsj<="+myForm.getJssj();
		}
		if("true".equalsIgnoreCase(user.getFdyQx()) && "true".equalsIgnoreCase(user.getBzrQx())){
			query+=" and exists(select 1 from bzrbjb a where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ";
			query+=" or select 1 from fdybjb a where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ";
		}else if("true".equalsIgnoreCase(user.getBzrQx())){
			query+=" and exists(select 1 from bzrbjb a where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ";
		}else if("true".equalsIgnoreCase(user.getFdyQx())){
			query+=" and exists(select 1 from fdybjb a where b.bjdm=a.bjdm and zgh='"+user.getUserName()+"') ";
		}	
		//查询条件
		query=mQuery.getQueryString()+query;
		firstFloor.append(" from xg_view_rcsw_yrgf b "+query);
		firstFloor.append(" group by xh, xn, xq, nd, dfsj, xmdm,xmlxdm,xm,bjmc,xmxz order by xmlxdm,xmdm,xmxz ");
		// ==============第一层end ================
		
		// ==============第二层================
		secoundFloor.append("select  xh,xm, xn, xq,nd, dfsj,xmdm,bjmc ");
		
		StringBuilder tjfxx=new StringBuilder();
		StringBuilder zf=new StringBuilder();
		for(int i=0;i<xmList.size();i++){
			
			
			HashMap<String,String>hashMap=xmList.get(i);
			//统计项目分信息
			tjfxx.append(" ,(case when xmdm='"+hashMap.get("xmdm")+"' then ");
			tjfxx.append(" (case when fz is null or fz='' then 0 else fz end) end)xmdm"+hashMap.get("xmdm"));
			
			//行转列
			hzl.append("  ,sum(case when xmdm"+hashMap.get("xmdm")+" is null ");
			hzl.append(" then 0 else xmdm"+hashMap.get("xmdm")+" end )xmdm"+hashMap.get("xmdm") );
			//输出字段
			outPut[3+i]="xmdm"+hashMap.get("xmdm");
			
			//计算总
			if(i==0){
				zf.append(outPut[3+i]);
			}else{
				zf.append("+"+outPut[3+i]);
			}
		}
		secoundFloor.append(tjfxx);
		secoundFloor.append(" from( ");
		secoundFloor.append(firstFloor);
		secoundFloor.append(" ) ");
		// ==============第二层end ================
		
		// ====================行转列======================
		hzl.append("  from( ");
		hzl.append(secoundFloor);
		hzl.append(" )group by xh,xm,xn,xq,nd,bjmc ");
		// ====================行转列end===================
		
		outPut[0]="xh";
		outPut[1]="xm";
		outPut[2]="bjmc";
		outPut[outPut.length-1]="zf";
		
		sql.append("select xh,xm,xn,xq, nd, bjmc" );
		for(int i=0;i<xmList.size();i++){
			HashMap<String,String>hashMap=xmList.get(i);
			sql.append(" , nvl(case when instr(to_char(xmdm"+hashMap.get("xmdm")+"),'.',1,1)=1 then '0'||to_char(to_char(xmdm"+hashMap.get("xmdm")+")) ");
			sql.append(" else (case when instr(to_char(xmdm"+hashMap.get("xmdm")+"),'.',1,1)=2 and instr(to_char(xmdm"+hashMap.get("xmdm")+"),'-',1,1)=1 then '-0.'||substr(to_char(to_char(xmdm"+hashMap.get("xmdm")+")),3,length(to_char(to_char(xmdm"+hashMap.get("xmdm")+")))) else to_char(to_char(xmdm"+hashMap.get("xmdm")+")) end ) end,0)xmdm"+hashMap.get("xmdm"));
		}
		sql.append(" , nvl(case when instr(zf,'.',1,1)=1 then '0'||to_char(zf) ");
		sql.append(" else (case when instr(zf,'.',1,1)=2 and instr(zf,'-',1,1)=1 then '-0.'||substr(to_char(zf),3,length(to_char(zf)))else to_char(zf) end) end,0)zf  ");
		
		sql.append(" from( select a.*,"+zf+" zf from ( ");
		sql.append(hzl);
		sql.append(" )a) ");
		System.out.println(sql);
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", mQuery.getInputList(), outPut, myForm);
	}
	
	/**
	 * 根据学号获取学生所有的项目类型
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * author qlj
	 */
	public List<HashMap<String,String>>getXsXmlxList(RcswLnnyForm myForm){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select distinct(xmlxdm)xmlxdm,xmlxmc from xg_view_rcsw_yrgf where xh=? ");
		return dao.getList(sql.toString(), new String[]{myForm.getXh()}, new String[]{"xmlxdm","xmlxmc"});
	}
	
	/**
	 * 根据学号获取学生信息
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * author qlj
	 */
	public List<HashMap<String,String>>getXsXmList(RcswLnnyForm myForm)throws  Exception{
		DAO dao=DAO.getInstance();
		MakeQuery mQuery=new MakeQuery();
		String[] colList={"xn","xq","nd"};
		StringBuilder query=new StringBuilder();
		if(!"".equalsIgnoreCase(myForm.getKssj())){
			query.append(" and dfsj>='"+myForm.getKssj()+"'");
		}
		if(!"".equalsIgnoreCase(myForm.getJssj())){
			query.append(" and dfsj>='"+myForm.getJssj()+"'");
		}
		mQuery.makeQuery(colList, new String[]{}, myForm);
		StringBuilder sql=new StringBuilder();
		
		query.append(" and xh='"+myForm.getXh()+"'"); 
		sql.append("select xmlxdm,xmmc,xn,xq,xqmc,nd,dfsj,fz from xg_view_rcsw_yrgf "+mQuery.getQueryString()+query );
		System.out.println(sql);
		return dao.getList(sql.toString(), mQuery.getInputList(), new String[]{"xmlxdm","xmmc","xn","xq","xqmc","nd","dfsj","fz"});
	}
	
	public String[] checkExistsXm(){
		DAO dao=DAO.getInstance();
		String sql=" select count(1)num from xg_xtwh_yrgflxb";
		return dao.getOneRs(sql, new String[]{}, new String[]{"num"});
	}
}
