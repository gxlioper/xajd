/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政获取公寓管理相关信息</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-24 下午06:12:25</p>
 */
package xgxt.xsgygl.csmzzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GetGyglData {
	DAO dao=null;
	public List<HashMap<String, String>> getSsFpSsXxList(String xqdm,String xb,String lddm) {
		dao                 = DAO.getInstance();
		//String nj                  = "2008";
		StringBuffer sql    = new StringBuffer();
		StringBuffer querry = new StringBuffer("");		
		querry.append(Base.isNull(lddm)?" and 1=2 ":"  and lddm = '"+lddm+"' ");//只有选择楼栋才有效的查询宿舍信息		
//		querry.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm = '"+xqdm+"' ");
//		querry.append(Base.isNull(xb)?" and 1=1 ":" and xbxd = '"+xb+"' ");		
		
		//String querry2      = Base.isNull(nj)?" where 1=1 ":" where nj='"+nj+"' ";
//		sql ="select a.ssbh||'/'||a.sycws dm,a.ldmc||a.qsh||'/'||a.cws||'/'||a.sycws mc from "
//		    + "(select a.ldmc,a.qsh,a.ssbh,a.cws,a.cws-nvl(b.rs,0) sycws from view_ssxx a left join " 
//		    + "(select ssbh,count(*) rs from xszsxxb where jzrq is null group by ssbh order by ssbh) b " 
//		    + "on a.ssbh=b.ssbh where a.fpbj='一般' and a.ssbh not in (select ssbh from ssfpb) and "
//		    + querry + ") a where a.sycws>0  order by dm  ";		
		/**
		sql.append("select a.ssbh||'/'||a.sycws dm,a.ldmc||a.qsh||'/'||a.cws||'/'||a.sycws mc,a.rs,a.sycws,a.lddm  from( ");
		sql.append("select a.ssbh,a.cws, a.cws-nvl(b.rs,0)sycws,a.ldmc,a.qsh,b.rs,a.lddm   from  view_ssxx a left join ");
	    sql.append("(select ssbh ,max(rs)rs from(select ssbh,sum(cws) rs from ssfpb group by ssbh union select ssbh,count(*) rsa ");
	    sql.append("from xszsxxb group by ssbh )group by ssbh)b on a.ssbh=b.ssbh where a.fpbj='一般' ) a where a.sycws>0 ");
	    sql.append(querry+" order by dm ");
	    */
		sql.append(" select a.ssbh||'/'||a.sycws dm,(select d.ldmc  from ssxxb c,sslddmb d where c.ssbh = a.ssbh and c.lddm=d.lddm)||'/'|| cs || '层' || '/' || qsh || '/' ||a.cws||'/'||a.sycws mc from");
		sql.append(" (select a.ssbh,a.qsh,a.cs,a.cws,(a.cws-nvl(b.rsb,0)-(select count(*)from wxs_xszsxxb f where f.ssbh=a.ssbh)-(select nvl(sum(cws),0) from ssfpb c where c.ssbh=a.ssbh )) sycws from view_ssxx a left join ");
		sql.append(" (  select ssbh ,sum(rs)rsb from (select ssbh,count(xh)rs from (select distinct xh,ssbh from xszsxxb a ");		
		sql.append("  where not exists(select xh,ssbh from ( select  distinct a.xh,a.ssbh from (select b.ssbh,xh,nvl(nj,(select nj from view_newstuinfo a where a.ksh=b.xh ))nj," );
		sql.append("  nvl(xydm, (select xydm from view_newstuinfo a where a.ksh=b.xh ))xydm from (select b.ssbh,a.xh,b.nj,b.xydm from xszsxxb a  left join xsxxb b on a.xh=b.xh )b)  a, ");
		sql.append("  ssfpb b where  a.ssbh = b.ssbh  and a.nj = b.nj and a.xydm = b.xydm )b  where a.xh=b.xh and a.ssbh=b.ssbh) ) a group by ssbh ) group by ssbh ");
		sql.append("  ) b on a.ssbh=b.ssbh where a.fpbj='一般'");
		sql.append(querry + ") a where a.sycws>0  order by dm ");		
	    List<HashMap<String, String>> ssList = dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });   
		return ssList;
	}
   public List<HashMap<String,String>> getLdXbXdList(String xqdm){
	   dao = DAO.getInstance();
//	   StringBuffer querry = new StringBuffer();
//	   querry.append(" where 1=1 ");
//	   querry.append(Base.isNull(xqdm)?" and 1=1 ":"  and 1=1 "); 
	   String sql = "select '' dm,'--请选择--' mc from dual  union (select '男' dm,'男' mc from  dual) "
		          +" union (select '女' dm,'女' mc from  dual) " 
		          +" union (select '混合' dm,'混合' mc from  dual) order by dm desc  ";
		          
	   List<HashMap<String, String>> ldxbList = dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });   
	   return ldxbList;
   }
   /**
    * @param xqdm校区代码
    * @param xbxd 楼栋性别限定
    * @return 返回宿舍划分楼栋列表
    */
	public List<HashMap<String, String>> getSsFpLdList(String xqdm,String xbxd) {		
		dao = DAO.getInstance();
		String sql = "";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");		
		querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and xqdm = '"+xqdm+"' ");
		querry.append(Base.isNull(xbxd)?" and 1=2  ":" and xbxd like '%" + xbxd +"%' ");
	    sql = "select '' dm,'--请选择--' mc from sslddmb where rownum='1' union all select * from" 
	    	+ "(select distinct lddm dm,ldmc mc from sslddmb "+querry+" order by  lddm )";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
	}
	
	/**
	 * 
	 * @param lddm 楼栋代码
	 * @param xqdm 校区代码
	 * @param xydm 学院代码
	 * @param bjdm 班级代码
	 * @return 返回宿舍划分中已划分宿舍信息列表（长沙民政新生（考生号为关键字）宿舍划分）
	 */
	public List<HashMap<String, String>> getSsFpFpSsXxList(String lddm,String xqdm,String xydm,String nj) {
		dao = DAO.getInstance();
		String sql = "";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm = '"+lddm+"' ");
//		querry.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm = '" + xqdm +"' ");
		querry.append(Base.isNull(nj)?" and 1=2 ":" and nj = '" + nj +"' ");
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm = '"+ xydm +"' ");
			
		sql = "select  xydm||'/'||ssbh||'/'||fpcws||'/'||nj dm,bmmc||'/'||ldmc||qsh||'/'||cws||'/'||fpcws mc from "
			+"(select (case  when a.nj is null then ' ' else a.nj end ) nj,a.xydm,d.bmmc,a.ssbh,a.cws fpcws,b.cws,b.lddm,b.qsh,c.ldmc,c.xqdm,c.xbxd from ssfpb a,ssxxb b,sslddmb c,zxbz_xxbmdm d where a.ssbh=b.ssbh and "
			+"b.lddm=c.lddm and a.xydm=d.bmdm)" + querry + " order by xydm,lddm,qsh " ;
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
		
	}
	public List<HashMap<String, String>> getSsFpZyList(String xydm,String nj){
		dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj ='"+nj+"' ");		
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm = '"+xydm+"' ");		
		String sql = "select '' dm,'--请选择--'mc from dual union all select * from (select distinct zydm dm,zymc mc from view_newstuinfo "+querry+" order by zydm) ";
		return  dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	
	public List<HashMap<String,String>> getSsFpBjList(String zydm,String nj){
		dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj ='"+nj+"' ");
		querry.append(Base.isNull(zydm)?" and 1=1 ":" and zydm = '"+zydm+"' ");		
		String sql = "select '' dm,'--请选择--'mc from dual union all select * from (select distinct bjdm dm,bjmc mc from view_newstuinfo "+querry+" order by bjdm) ";
		return  dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	
	public List<HashMap<String,String>> getWfpXsList(String xydm,String zydm,String bjdm,String nj,String xb,String ksh){
		dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append(" and 1=1 ");
		if(Base.isNull(ksh)){
			querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm='"+xydm+"' ");
		}else{
			querry.append(Base.isNull(ksh)?" and 1=1 ":" and ksh='"+ksh+"' ");
		}
		querry.append(Base.isNull(zydm)?" and 1=1 ":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append((Base.isNull(xb)||xb.equals("null"))?"":" and xb='"+xb+"' ");			
//		String sql = "select ksh dm,ksh||'/'||xm||'/'||xb mc from view_newstuinfo a where  " 
//                   +" not exists (select count(b.xh) from xszsxxb b where a.ksh=b.xh) and not exists (select b.ksh from xszsxxb c ,view_xsxxb b where c.xh=b.xh  and a.ksh=b.ksh)  "+querry+"order by xb desc ,ksh ";
		String sql = "select a.ksh dm,a.ksh||'/'||xm||'/'||xb mc from view_newstuinfo a where ksh not in(select ksh from ((select b.xh ksh from xszsxxb b )union all(select b.ksh from xszsxxb c ,view_xsxxb b where c.xh=b.xh ))b  where a.ksh=b.ksh) "+querry+" order by xb desc,a.ksh ";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });		
	}	
	public List<HashMap<String,String>> getXiaoQuList(String xydm,String nj){
		dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append(" ");
		querry.append(Base.isNull(nj)?" and 1=2 ":" and a.nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and a.xydm='"+xydm+"'");
		String sql = "select 'xq' dm,'--请选择--' mc from dual union all select * from (select distinct b.dm dm,b.xqmc mc "
		           + " from ssfpb a, dm_zju_xq b, sslddmb c, ssxxb d where a.ssbh=d.ssbh and c.lddm=d.lddm and b.dm=c.xqdm "+querry+" )";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
		
	}
	public List<HashMap<String,String>>getXsCwFpLdList(String xydm,String nj,String xqdm,String xb){
		dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append(" where a.ssbh=b.ssbh ");
		querry.append(Base.isNull(xydm)?"":" and a.xydm='"+xydm+"' ");
		querry.append(Base.isNull(nj)?"":"  and a.nj='"+nj+"' ");
		querry.append(Base.isNull(xqdm)?"":" and b.xqdm='"+xqdm+"' ");
		querry.append(Base.isNull(xb)?" and 1=2 ":" and b.xbxd='"+xb+"' ");
		String sql = "select 'ld'dm,'--请选择--'mc from dual union all select * from" 
                   +"(select distinct b.lddm dm,b.ldmc mc from ssfpb a,view_ssxx b "+querry+" order by lddm)";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
		 
	}
	public List<HashMap<String,String>> getXsCwFpSsList(String lddm,String nj,String xydm ){
		dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(nj)?"":" and a.nj='"+nj+"' ");
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and b.lddm='"+lddm+"'");
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and a.xydm='"+xydm+"'");
//		querry.append(" order by dm");
		StringBuffer sql = new StringBuffer();
//		sql.append("select dm,mc from (select a.ssbh || '/' || c.cwh dm,b.ldmc||b.qsh || '/' || b.cws || '/' || c.cwh mc, ");
//		sql.append("a.ssbh,a.nj,a.xydm from ssfpb a, view_ssxx b, cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and a.cws=b.cws ");
//		sql.append("and a.ssbh||c.cwh not in (select distinct ssbh||cwh from xszsxxb) union select a.ssbh || '/' || a.cwh en,  ");
//		sql.append("b.ldmc || b.qsh || '/' || b.cws || '/' || a.cwh cn,a.ssbh ,a.nj,a.xydm from ( select a.ssbh,cwh,xydm,nj from ( ");
//		sql.append("select a.ssbh, rank() over ( partition by a.ssbh,xydm,nj order by to_number(a.cwh) ) px,a.cwh,cws,countcwh,xydm,nj from ( ");
//		sql.append("select ssbh,cws,cwh,countcwh,xydm,nj from (select distinct a.ssbh, c.cwh, a.cws,countcwh,d.xydm,d.nj from ssfpb a,cwxxb c,  ");
//		sql.append("(select ssbh,countcwh,xydm,nj from (select distinct a.ssbh,count(b.cwh) countcwh,a.xydm,a.nj from (select a.ssbh,a.xydm,a.nj ");
//		sql.append("from ssfpb a,view_ssxx b where a.cws <> b.cws and a.ssbh=b.ssbh ) a left join (select distinct a.ssbh,a.cwh,b.nj,b.xydm  ");
//		sql.append("from xszsxxb a,view_newstuinfo b where a.xh=b.ksh ) b on a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj group by a.ssbh,a.xydm,a.nj ");
//		sql.append(")) d where a.ssbh = c.ssbh and a.ssbh = d.ssbh and a.ssbh || c.cwh not in (select ssbh || cwh from xszsxxb) and d.countcwh<>a.cws ");
//		sql.append("and a.xydm=d.xydm and a.nj=d.nj )) a )a  where px between 1 and a.cws-a.countcwh ) a,view_ssxx b where a.ssbh = b.ssbh )a, ");
//		sql.append("view_ssxx b,ssfpb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and c.xydm=a.xydm and c.nj=a.nj ");
//		sql.append(querry);
		sql.append(" select dm,mc from(select a.ssbh||'/'||c.cwh dm,b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh mc,");
		sql.append(" to_number(b.cws) - to_number((select count(ssbh) from xszsxxb b where a.ssbh = b.ssbh)) leaveCws,");
		sql.append(" (select count(ssbh)from view_xszsxx b where a.ssbh = b.ssbh and a.nj=b.nj and a.xydm=b.xydm)rzs,");
		
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "c.cwh";
		}else{
			sb = "to_number(c.cwh)";
		}
		sql.append(" row_number() over( partition by a.ssbh order by a.ssbh,"+sb+") px,a.cws fps  from ssfpb a,view_ssxx b,");
		sql.append(" cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh");
		sql.append(querry.toString());
		sql.append(" and a.ssbh||c.cwh not in (select distinct ssbh||cwh from xszsxxb) and a.ssbh || c.cwh not in ( select distinct ssbh || cwh from wxs_xszsxxb) ");
		sql.append(" )a where px <=fps-rzs");
		List<HashMap<String,String>> aa = dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});
		return aa;
	}
	
	/**
	 * 
	 * @param xydm 学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @param cwfp 多余床位分配标志
	 * @return 获取床位分配中已分配床位信息列表
	 */
	public  List<HashMap<String,String>> getXsYFpCwList(String zydm,String nj,String xb,String bjdm,String xydm,String ksh){
		dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		String sql = "";
		querry.append("where 1=1 ");
		querry.append(Base.isNull(zydm)?" and 1=1 ":" and zydm = '"+zydm+"'");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and nj = '"+nj+"' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("null"))?" and 1=1 ":" and xb = '"+xb+"' ");
		if(Base.isNull(ksh)){
			querry.append((Base.isNull(xydm))?" and 1=2 ":" and xydm = '"+xydm+"' ");
		}else{
			querry.append((Base.isNull(xydm))?" and 1=1 ":" and xh = '"+ksh+"' ");
		}
//		if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_HENANGYDX)){
//			querry.append((Base.isNull(bjdm)||bjdm.equalsIgnoreCase("null"))?" and 1=2 ":" and bjdm = '"+bjdm+"' ");
//		}else{
			querry.append((Base.isNull(bjdm)||bjdm.equalsIgnoreCase("null"))?" and 1=1 ":" and bjdm = '"+bjdm+"' ");
//		}
		querry.append(" order by xb desc ,xh");
		sql = "select distinct (xh||'/'||ssbh||'/'||cwh||'/'||rzrq) dm , xh||'/'||xm||'/'||xb||'/'||ldmc||qsh||'/'||cws||'/'||cwh||'/'||rzrq mc, xb,xh  from view_newstuzsxx "+querry;
		List<HashMap<String,String>> fpCwList = dao.getList(sql, new String[] {}, new String[] {	"dm", "mc" });
		return fpCwList;
	}
	
	/**
	 * 检测输入考生号是否存在
	 * 
	 * @return boolean 
	 * */
	public boolean getStuKshExist(String tableName,String pk,String pkValue){
		dao           = DAO.getInstance();
		boolean flag = false;
		int result   = 0;
		String sql    ="select count(*) num from "+tableName+" where "+pk+"=? ";
		result        = Integer.parseInt(dao.getOneRs(sql, new String[]{pkValue}, "num"));
		flag          = (result>0)?true:false;
		return flag;
	}
	
	/**
	 * 检测符合条件数据是否存在
	 * @param 
	 * @return boolean 
	 * */
	public boolean getInfoExist(String tableName,String querry){
		dao           = DAO.getInstance();
		boolean flag = false;
		int result   = 0;
		String sql    ="select count(*) num from "+tableName+" where "+querry;
		result        = Integer.parseInt(dao.getOneRs(sql, new String[]{}, "num"));
		flag          = (result>0)?true:false;
		return flag;
	}
	
	 /** 返回公寓楼栋列表*/
	 public List getGyLdList(String xqdm){
		DAO dao            = DAO.getInstance();	
	    StringBuffer  sql  = new StringBuffer("select distinct  lddm dm, ldmc mc from sslddmb order by  lddm ");
	    sql.append(Base.isNull(xqdm)?"":" where xqdm='"+xqdm+"' ");
		List ldList        = dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
		return ldList;
	 }
	 /** 返回寝室号列表（宿舍划分中已经划分的寝室信息）*/
	 public List getQshList(String lddm,String xydm,String nj){
		 DAO dao             = DAO.getInstance();
		 StringBuffer querry = new StringBuffer(); 
		 querry.append(Base.isNull(lddm)?" and 1=2 ":" and  b.lddm='"+lddm+"' ");
		 querry.append(Base.isNull(xydm)?" and 1=1 ":" and  a.xydm='"+xydm+"' ");
		 querry.append(Base.isNull(nj)?" and 1=1 ":" and  a.nj='"+nj+"' ");
		 String sql          ="select ''dm,'--请选择--'mc from dual union (select a.ssbh dm ,b.qsh mc from ssfpb a,ssxxb b,sslddmb c where a.ssbh=b.ssbh and b.lddm=c.lddm  "+querry+" ) order by dm desc  ";			
		 List qshList        = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		 return qshList;

//		 DAO dao             = DAO.getInstance();
//		 StringBuffer querry = new StringBuffer(); 
//		 querry.append(Base.isNull(lddm)?" where 1=1 ":" where  lddm='"+lddm+"' ");
//		 String sql          ="select ''dm,'--请选择--'mc,' ' lddm,' ' qsh from dual union(select ssbh dm ,qsh mc,lddm,qsh from (select ssbh,qsh,lddm from ssxxb where fpbj='一般')"+querry+")order by lddm,qsh  ";			
//		 List qshList        = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
//		 return qshList;
	 }
	 public List getCwhList(String ssbh){
		 DAO dao   = DAO.getInstance();
		 List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();
		 String sql = " select cws  from ssxxb where ssbh = ? ";
		 String cws = dao.getOneRs(sql, new String[] { ssbh }, "cws");
		 cws = Base.isNull(cws)?"0":cws;
		 for (int i = 1; i <= Integer.parseInt(cws); i++) {
			 HashMap<String, String> mapt = new HashMap<String, String>();
			 mapt.put("dm", String.valueOf(i));	
			 mapt.put("mc", String.valueOf(i));
			 cwhList.add(mapt);
		 }
		 sql = " select cwh dm ,cwh mc from xszsxxb where ssbh = ? ";
		 List<HashMap<String, String>> sycwhList = dao.getList(sql, new String[] { ssbh },
				 new String[] { "dm","mc" });
		 cwhList.removeAll(sycwhList);
		 return cwhList;
	 }
     
//	获得未分配学生总数数(新生（只有考生号）宿舍、床位分配)
	 public static String[] getXsWFPrs(String nj,String xydm,String zydm,String bjdm){
		 String query = "";
		 if(nj != null && !nj.equals("")){
			 query += " and nj='"+nj+"' ";
		 }
		 if(xydm != null && !xydm.equals("")){
			 query += " and xydm='"+xydm+"' ";
		 }
		 if(zydm != null && !zydm.equals("")&&!zydm.equals("null")){
			 query += " and zydm='"+zydm+"' ";
		 }
		 if(bjdm != null && !bjdm.equals("")&&!bjdm.equals("null")){
			 query += " and bjdm='"+bjdm+"' ";
		 }
		 DAO dao    = DAO.getInstance();
		 String sql = "select boys+girls total,boys,girls from (select (select count(ksh) from view_newstuinfo where xb='男' "+query+" and ksh not in (select xh from " 
		 		     +"xszsxxb)) boys,(select count(ksh) from view_newstuinfo where xb='女' "+query+" and ksh not in (select xh from xszsxxb)) girls from dual)";
		 return dao.getOneRs(sql, new String[]{}, new String[]{"total","boys","girls"});
	 }	 
	 //获得已分配床位数(新生（只有考生号）宿舍分配)
	 public static String[] getXSYFPcws(String nj,String xydm){
		 String query = "";
		 if(nj != null && !nj.equals("")){
			 query += " and a.nj='"+nj+"' ";
		 }
		 if(xydm != null && !xydm.equals("")){
			 query += " and a.xydm='"+xydm+"' ";
		 }
		 DAO dao    = DAO.getInstance();
		 String sql = "select boycws+girlcws+bgcws total,boycws,girlcws,bgcws from (select (select nvl(sum(a.cws),0) boycws "
		 		      +"from ssfpb a,ssxxb b,sslddmb c where a.ssbh=b.ssbh and b.lddm=c.lddm and b.fpbj='一般' and c.xbxd='男' "+query+") boycws,(select nvl(sum(a.cws),0)" 
		 		      +" girlcws from ssfpb a,ssxxb b,sslddmb c where a.ssbh=b.ssbh and b.lddm=c.lddm and b.fpbj='一般' and c.xbxd='女' "+query+") girlcws,(select nvl(sum(a.cws),0)"
		 		      +" bgcws from ssfpb a,ssxxb b,sslddmb c where a.ssbh=b.ssbh and b.lddm=c.lddm and b.fpbj='一般' and c.xbxd='混合' "+query+") bgcws from dual)";
		 return dao.getOneRs(sql, new String[]{}, new String[]{"total","boycws","girlcws","bgcws"});
	 }
//		获得已分配学生总数数(新生（只有考生号）床位分配)
	 public static String[] getXsYFPrs(String nj,String xydm,String zydm,String bjdm){
		 DAO dao    = DAO.getInstance();
		 String query = "";
		 if(nj != null && !nj.equals("")){
			 query += " and nj='"+nj+"' ";
		 }
		 if(xydm != null && !xydm.equals("")){
			 query += " and xydm='"+xydm+"' ";
		 }
		 if(zydm != null && !zydm.equals("")&&!zydm.equals("null")){
			 query += " and zydm='"+zydm+"' ";
		 }
		 if(bjdm != null && !bjdm.equals("")&&!bjdm.equals("null")){
			 query += " and bjdm='"+bjdm+"' ";
		 }
		 String sql = "select boys+girls total,boys,girls from (select (select count(ksh) from view_newstuinfo where xb='男' "+query+" and ksh in (select xh from " 
		     +"xszsxxb)) boys,(select count(ksh) from view_newstuinfo where xb='女' "+query+" and ksh in (select xh from xszsxxb)) girls from dual)";
         return dao.getOneRs(sql, new String[]{}, new String[]{"total","boys","girls"});
	 }
	 
	 /** 返回公寓楼栋列表（传入参数 学院代码、年级）*/
	 public List getGyLdList(String xydm,String nj,String xb){
		DAO dao            = DAO.getInstance();	
	    StringBuffer  sql  = new StringBuffer(" select ''dm,'--请选择--'mc from dual union all select dm,mc from ( select distinct c.lddm dm,c.ldmc mc from ssfpb a,ssxxb b,sslddmb c where a.ssbh=b.ssbh and b.lddm=c.lddm ");
	    sql.append(Base.isNull(xydm)?"and 1=2 ":" and xydm='"+xydm+"' ");
	    sql.append(Base.isNull(xydm)?"and 1=2 ":" and nj='"+nj+"' ");
	    sql.append(Base.isNull(xb)?"and 1=1 ":" and ( xbxd='"+xb+"'or xbxd='混合')");
	    sql.append(" order by to_number(c.lddm) asc ) ");
		List ldList  = dao.getList(sql.toString(), new String[] {}, new String[] {"dm", "mc" });
		return ldList;
	 }
	public String xhKshSynchro(String kshArr,String querry){
		DAO dao = DAO.getInstance();
		String sql = "";
		int kshNum =  kshArr.split(",").length;
		sql = " select max(num)num from (";
		sql +=" select count(xh)num from xsxxb where ksh in ("+kshArr+") and "+querry+" union  ";
		sql +=" select count(xh)num  from (select bmdm xydm,a.* from bks_xsjbxx a) where ksh in ("+kshArr+") and "+querry+") ";
		String xhNum = dao.getOneRs(sql,new String[]{},"num");
		int notExistXh = kshNum-Integer.parseInt(xhNum);
		return notExistXh+"";
	}
}

