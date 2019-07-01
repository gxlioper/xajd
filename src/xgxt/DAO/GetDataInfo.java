package xgxt.DAO;

import java.sql.SQLException;
import java.util.*;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.SearchUtils;

import common.Globals;

public class GetDataInfo {
	/**
	 * @公共变量定义区
	 *
	 */
	DAO dao = DAO.getInstance();
	
	/**
	 * @return 用人单位详细信息
	 */
	public String[] getYrdwInfo(String setPara){
		String []getPara={"lxr","lxdh"};
		String sql = "select lxr,lxdh from yrdwdmb where yrdwdm=?";
		String []reVal = dao.getOneRs(sql, new String []{setPara}, getPara);
		return reVal;
	}
	
	/**
	 * 勤工助学根据岗位名称查询所属单位
	 * @param pkValue
	 * @return String[]
	 * */
	public String[] getDwmc(String pkValue){
		String xxdm = StandardOperation.getXxdm();		
		String[] pk = pkValue.split("-");
		pkValue = pk[0]+pk[1];
		HashMap<String, String> map = new HashMap<String, String>();
		String[] dwInfo = new String[6];
		String sql = "select yrdwmc from yrdwdmb where yrdwdm = (select sqdw from gwxxb where gwdm||gwsbsj=?)";
		dwInfo[0] = dao.getOneRs(sql, new String[]{pkValue}, "yrdwmc");
		sql = "select fzr,lxdh,gzsj,gznr,ryyq from gwxxb where gwdm||gwsbsj=?";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			sql = "select (select lxr from yrdwdmb b where b.yrdwdm=a.sqdw)fzr, (select lxdh from yrdwdmb b where a.sqdw=b.yrdwdm)lxdh," +
				  "gzsj,gznr,ryyq from view_gwxx a where gwdm||gwsbsj=?";
		}
		map = dao.getMap(sql, new String[]{pkValue}, new String[]{"fzr","lxdh","gzsj","gznr","ryyq"});
		String fzr = map.get("fzr");
		String lxdh = map.get("lxdh");
		String gzsj = map.get("gzsj");
		String gznr = map.get("gznr");
		String ryyq = map.get("ryyq");
		dwInfo[1] = (fzr == null || "".equalsIgnoreCase(fzr))? "" : fzr ;
		dwInfo[2] = (lxdh == null || "".equalsIgnoreCase(lxdh))? "" : lxdh;
		dwInfo[3] = (gzsj == null || "".equalsIgnoreCase(gzsj))? "" : gzsj;
		dwInfo[4] = (gznr == null || "".equalsIgnoreCase(gznr))? "" : gznr;
		dwInfo[5] = (ryyq == null || "".equalsIgnoreCase(ryyq)) ? "" : ryyq;
		return dwInfo;
	}
	
	/**
	 * @param query　查询参数
	 * @return 宿舍信息列表
	 */
	public List getDormInfo(String query){
		String [] setpara = query.split("!!-!!");
		String sql = "";
		if(setpara != null){
			if("ass".equalsIgnoreCase(setpara[0])){
				sql = "select a.ssbh||'/'||a.sycws en,a.ssbh||'/'||a.cws||'/'||a.sycws cn from (select a.ssbh,a.cws,a.cws-nvl(b.rs,0) sycws from ssxxb a left join (select ssbh,count(*) rs from xszsxxb where jzrq is null group by ssbh order by ssbh) b on a.ssbh=b.ssbh where a.fpbj='一般' and a.ssbh not in (select ssbh from ssfpb) and "
					+ "a.lddm like ? and a.cs like ?) a where a.sycws>0";
			}else{
				sql = "select b.ssbh,b.cwh,b.ssbh||'/'||b.cws||'/'||b.cwh en,b.ssbh||'/'||b.cws||'/'||b.sycws||'/'||b.cwh cn from (select a.*,a.cws-nvl(b.rs,0) sycws from ("
					+ "select a.*,b.cws from (select a.ssbh,a.cwh from cwxxb a where not exists (select * from ssfpb b where a.ssbh=b.ssbh and a.cwh=b.cwh )"
					+ ") a left join ssxxb b on a.ssbh=b.ssbh and b.fpbj='一般') a left join (select ssbh,count(*) rs from ssfpb group by ssbh order by ssbh) b on a.ssbh=b.ssbh) "
					+ " b,ssxxb a where a.ssbh=b.ssbh and "
					+ "a.lddm like ? and a.cs like ? order by b.ssbh,b.cwh";
			}
		}
		List reVal = dao.getList(sql, new String[]{setpara[1],setpara[2]}, new String[] { "en","cn" });
		return reVal;
	}
	
	/**
	 * @param query 查询参数
	 * @return 返回数据是否存在 存在=1 不存在=0 存在且已经通过审核=2
	 * @throws SQLException
	 */
	public String IsDataExist(String query) throws SQLException{
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String [] setPara = null;
		String flag = "";
		
		sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=?";
		setPara = query.split("-");
		flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});
		
		if("empty".equalsIgnoreCase(flag)){
			return "0";
		}else{
			sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=? and xxyj='通过'";
			flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});	
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//中国地质大学
				sql = "select * from view_xsgwxx where xh=?";
				flag = dao.returntag(sql, new String []{setPara[0]});
				if("empty".equalsIgnoreCase(flag)){
					return "";
				}else{
					return "3";
				}				
			}
			if("empty".equalsIgnoreCase(flag)){
				return "1";
			}else{
				return "2";
			}
		}
	}
	
	/**
	 * @param query 查询参数
	 * @return 返回数据是否存在 1表示该学生已经申请勤工助学并且已经通过审核，证明学生在岗 0表示学生不在岗
	 * @throws SQLException
	 */
	public String zgdzdx_hg_IsDataExist(String query) throws SQLException{
		
		String sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=? and xxyj='通过'";		
		String [] setPara = query.split("-");
		String flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});
		if("empty".equalsIgnoreCase(flag)){//如果在岗位信息视图中查询返回0，学生将不能申请换岗
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * @param query 查询参数
	 * @return 返回数据是否存在 1表示该学生已经申请勤工助学并且已经通过审核，证明学生在岗 0表示学生不在岗
	 * @throws SQLException
	 */
	public String zgdzdx_sqgw_IsDataExist(String query) throws SQLException{
		
		String sql = "select * from view_xsgwxx where xh=? and gwdm=? and gwsbsj=? and xxyj='通过'";		
		String [] setPara = query.split("-");
		String flag = dao.returntag(sql, new String []{setPara[0],setPara[1],setPara[2]});
		if("empty".equalsIgnoreCase(flag)){//如果在岗位信息视图中查询返回0，学生将不能申请换岗，返回1则不能申请该岗位
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * @param obj
	 * @param xymc 学院名称
	 * @return 返回学院列表xymc-xydm
	 */
	public List getXyInfo(String obj,String xymc){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		String sql = "select distinct xymc||'-'||xydm dm from view_njxyzybj where xymc like ? order by dm";
		List rsList = dao.getList(sql, new String[]{xymc}, new String[]{"dm"});
		arrayList.add(0, obj);
		arrayList.add(1,rsList);
		return arrayList;
	}
	
	/**
	 * @param obj
	 * @param xy 学院
	 * @param zymc 专业
	 * @return 返回专业列表zymc-zydm
	 */
	public List getZyInfo(String obj,String xy,String zymc){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		String sql = "select distinct zymc||'-'||zydm dm from view_njxyzybj where xydm like ? and zymc like ? order by dm";
		List rsList = dao.getList(sql, new String[]{xy,zymc}, new String[]{"dm"});
		arrayList.add(0, obj);
		arrayList.add(1,rsList);
		return arrayList;
	}
	
	/**
	 * @param obj
	 * @param nj 年级
	 * @param xy 学院
	 * @param zy 专业
	 * @param bjmc 班级名称
	 * @return 返回班级列表bjmc-bjdm
	 */
	public List getBjInfo(String obj, String nj, String xy, String zy, String bjmc){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		String sql = "select distinct bjmc||'-'||bjdm dm from view_njxyzybj where nj like ? and xydm like ? and zydm like ? and bjmc like ? order by dm";
		List rsList = dao.getList(sql, new String[]{nj,xy,zy,bjmc}, new String[]{"dm"});
		arrayList.add(0, obj);
		arrayList.add(1,rsList);
		return arrayList;
	}
	
	/**
	 * @param xn 学年
	 * @param nd 年度
	 * @param xq 学期
	 * @param yrdwdm 用人单位代码
	 * @return 返回岗位详细信息
	 */
	public String [] getGwDetInfo(String xn,String nd,String xq,String yrdwdm){
		String sql = "select a.xydm,a.gws,a.syrs,a.gwxz,a.hbzje,nvl(b.cjje,0) cjje,nvl(a.hbzje,0)-nvl(b.cjje,0) jyje from (select a.yrdwdm,a.xydm,b.gws,b.syrs,b.gwxz,c.hbzje from view_yrdwdmb a,(" +
				" select sqdw,gwxz,count(*) gws,sum(nvl(sqsyrs,0)) syrs from gwxxb a where shjg='通过' and xn=? and xueqi=? and nd=? and sqdw=?" +
				" group by sqdw,gwxz) b,(select yrdwdm,sum(hbje) hbzje from jfhbb where xn=? and xq=? and nd=? and yrdwdm=? group by yrdwdm) c where a.yrdwdm=b.sqdw and a.yrdwdm=c.yrdwdm) a left join " +
				"(select sqdw,sum(cjje) cjje from (" +
				"select a.sqdw,a.xn,a.xueqi,a.nd,b.xh,b.cjje from gwxxb a,(" +
				"select a.xh,a.xn,a.xq,a.nd,a.gwdm,a.gwsbsj,b.cjje from xsgwxxb a,xscjffb b " +
				"where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) b " +
				"where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj) where xn=? and xueqi=? and nd=? and sqdw=? group by sqdw) b on a.yrdwdm=b.sqdw";
		String [] reValue = dao.getOneRs(sql, new String []{xn,xq,nd,yrdwdm,xn,xq,nd,yrdwdm,xn,xq,nd,yrdwdm}, new String[]{"xydm","gws","syrs","gwxz","hbzje","cjje","jyje"});
		return reValue;
	}
    /**
     * 
     * @param xqdm校区代码
     * @param xbxd 楼栋性别限定
     * @return 返回宿舍划分楼栋列表
     */
	public List<HashMap<String, String>> getSsFpLdList(String xqdm,String xbxd) {		
		String sql = "";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and xqdm like '%"+xqdm+"%' ");
		querry.append(Base.isNull(xbxd)?" and 1=2  ":" and xbxd like '%" + xbxd +"%' ");
	    sql = "select '' dm,'--请选择--' mc from sslddmb where rownum='1' union all select * from" +
	    		" (select distinct lddm dm,ldmc mc from sslddmb "+querry+" order by lddm)";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
	}
	/**
	 * 
	 * @param xqdm校区代码
	 * @param xb性别
	 * @param lddm楼栋代码
	 * @param cs 层数
	 * @param fpfs 划分方式
	 * @return 返回宿舍划分中宿舍信息列表
	 */
	public List<HashMap<String, String>> getSsFpSsXxList2(String xqdm,String xb,String lddm,String cs) {		
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm = '"+lddm+"' ");
		querry.append(Base.isNull(cs)?" and 1=1 ":" and cs = '"+cs+"' ");
		querry.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm = '"+xqdm+"' ");
		querry.append(Base.isNull(xb)?" and 1=1 ":" and xbxd = '"+xb+"' ");
		return ass_SsXsList(querry.toString());     		 
	}
	
	public List<HashMap<String, String>> getSsFpSsXxList(String xqdm,String xb,String lddm,String cs,String fpfs) {
		List<HashMap<String, String>> ssList = new ArrayList<HashMap<String, String>>() ;
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm = '"+lddm+"' ");
		querry.append(Base.isNull(cs)?" and 1=1 ":" and cs = '"+cs+"' ");
		querry.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm = '"+xqdm+"' ");
		querry.append(Base.isNull(xb)?" and 1=1 ":" and xbxd = '"+xb+"' ");
		if(!Base.isNull(lddm) && !Base.isNull(cs) 
				&& !Base.isNull(xqdm) && !Base.isNull(xb) ){
			if(!Base.isNull(fpfs)&&fpfs.equalsIgnoreCase("acw")){
				ssList = acw_SsXsList(querry.toString());
			}else {
				ssList = ass_SsXsList(querry.toString());
			}
		}
		return ssList;
	}
	
	public List<HashMap<String, String>>acw_SsXsList(String querry){
		dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();		

		sql.append(" select ssbh || '/' || cws || '/' || cwh dm,ldmc || '/' || cs || '层' || '/' || qsh || '/' || cws || '/' ||sycws || '/' || cwh mc "); 
		sql.append(" from (select a.ssbh, a.cwh,a.cws,a.cs,a.lddm,a.ldmc,a.xbxd,a.xqdm,a.cws - nvl(a.rs, 0) sycws,a.qsh from (select a.ssbh,a.cwh, "); 
		sql.append(" b.cws,b.cs,b.lddm,b.qsh,( case when nvl((select count(xh)  from xszsxxb c where a.ssbh=c.ssbh),0)>=nvl((select sum(cws)  from ssfpb "); 
		sql.append(" d where a.ssbh=d.ssbh),0) then nvl((select count(xh)  from xszsxxb c where a.ssbh=c.ssbh),0) else nvl((select sum(cws)  from ssfpb d "); 
		sql.append(" where a.ssbh=d.ssbh),0) end ) rs,(select xbxd from sslddmb d where d.lddm = b.lddm) xbxd, "); 
		sql.append(" (select xqdm from sslddmb d where d.lddm = b.lddm) xqdm,(select ldmc from sslddmb d where d.lddm = b.lddm) ldmc from ( "); 
		sql.append(" select distinct a.ssbh, a.cwh from cwxxb a where not exists  (  select ssbh ,cwh from ( select distinct ssbh,cwh  "); 
		sql.append(" from xszsxxb union all select distinct ssbh, cwh from ssfpb ) b where a.ssbh=b.ssbh and a.cwh=b.cwh ) ) a "); 
		sql.append(" , ssxxb b where a.ssbh = b.ssbh and b.fpbj = '一般'    ) a where 1=1   "+querry+" order by ssbh, cwh) "); 
		
		List<HashMap<String, String>> ssList = dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });
		return ssList;
	}
	public List<HashMap<String, String>>ass_SsXsList(String querry){
		StringBuffer sql = new StringBuffer();
		String xxdm = dao.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			sql.append(" select a.ssbh||'/'||a.sycws dm,(select d.ldmc  from ssxxb c,sslddmb d where c.ssbh = a.ssbh and c.lddm=d.lddm)||'/'|| cs || '层' || '/' || qsh || '/' ||a.cws||'/'||a.sycws mc from");
			sql.append(" (select a.ssbh,a.qsh,a.cs,a.cws,a.cws-nvl(b.rs,0) sycws from view_ssxx a left join ");
			sql.append(" ( select distinct a.ssbh,count( ssbh)rs from cwxxb a where  exists  (select ssbh ,cwh from (select distinct ssbh,cwh from xszsxxb ");
			sql.append("  union all select distinct ssbh, cwh from ssfpb ) b where a.ssbh=b.ssbh and a.cwh=b.cwh )group by ssbh ");
			sql.append("  ) b on a.ssbh=b.ssbh where a.fpbj='一般'");
			sql.append(querry + ") a where a.sycws>0  order by dm ");
		}else{
			//(select yqmc from yqdmb b,ssxxb c,sslddmb d where c.ssbh = a.ssbh and c.lddm=d.lddm and d.yqdm=b.yqdm)||
			
			sql.append(" select a.ssbh||'/'||a.sycws dm,(select d.ldmc  from ssxxb c,sslddmb d where c.ssbh = a.ssbh and c.lddm=d.lddm)||'/'|| cs || '层' || '/' || qsh || '/' ||a.cws||'/'||a.sycws mc from");
			sql.append(" (select a.ssbh,a.qsh,a.cs,a.cws,(a.cws-nvl(b.rsb,0)-(select count(*)from wxs_xszsxxb f where f.ssbh=a.ssbh)-(select nvl(sum(cws),0) from ssfpb c where c.ssbh=a.ssbh )) sycws from view_ssxx a left join ");
			sql.append(" (  select ssbh ,sum(rs)rsb from (select ssbh,count(xh)rs from (select distinct xh,ssbh from xszsxxb a ");		
			sql.append("  where not exists(select xh,ssbh from ( select  distinct a.xh,a.ssbh from (select b.ssbh,xh,nvl(nj,(select nj from newstusinfo a where a.ksh=b.xh ))nj," );
			sql.append("  nvl(xydm, (select xydm from newstusinfo a where a.ksh=b.xh ))xydm from (select a.ssbh,a.xh,(select nj from view_xsjbxx b where a.xh=b.xh)nj,(select xydm from view_xsjbxx b where a.xh=b.xh)xydm from xszsxxb a ");	
			sql.append(" where exists(select 1 from view_ssxx c where a.ssbh = c.ssbh "+ querry+")");
			sql.append(" )b) a,ssfpb b where  a.ssbh = b.ssbh  and a.nj = b.nj and a.xydm = b.xydm )b  where a.xh=b.xh and a.ssbh=b.ssbh) ) a group by ssbh ) group by ssbh ");
			sql.append("  ) b on a.ssbh=b.ssbh where a.fpbj='一般'");
			sql.append(querry + ") a where a.sycws>0  order by dm ");	
		}
		List<HashMap<String, String>> ssList = dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });
		return ssList;
	}
	
	/**
	 * 
	 * @param lddm 楼栋代码
	 * @param xqdm 校区代码
	 * @param xydm 学院代码
	 * @param bjdm 班级代码
	 * @return 返回宿舍划分中已划分宿舍信息列表
	 */
	public List<HashMap<String, String>> getSsFpFpSsXxList(String lddm,String cs,String nj,String xydm,String bjdm) {
		StringBuffer sql = new StringBuffer();
		String xxdm = dao.getXxdm();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		if(lddm==null){
			querry.append(" and 1=2 ");
		}
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm = '"+lddm+"' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and nj = '"+ nj +"' ");		
		querry.append(Base.isNull(xydm)?" and 1=1 ":" and xydm = '"+ xydm +"' ");
		querry.append(Base.isNull(cs)?" and 1=1 ":" and cs = '"+ cs +"' ");	
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			querry.append(Base.isNull(bjdm)||bjdm.equalsIgnoreCase("bj")?" and 1=1 ":" and bjdm = '"+bjdm+"' ");			
			sql.append("select nj||'/'||xydm||'/'||bjdm||'/'||ssbh||'/'||sycws||'/'||cwh dm,bjmc||'/'||ldmc||'/'||cs||'层'||'/'||qsh||'/'||cws||'/'||sycws||'/'||cwh mc from ( ");
			sql.append("select distinct a.*,a.cws-nvl(a.rs,0)sycws from( select a.nj,a.xydm,a.bjdm,a.ssbh,a.cwh,b.cws,b.cs,b.qsh,c.lddm,c.ldmc,c.xqdm,d.zymc,d.bjmc,");
			sql.append(" (select count(distinct xydm||ssbh||cws||bjdm||cwh||nj) rs from ssfpb b where a.ssbh = b.ssbh and b.bjdm in(select bjdm from view_njxyzybj))rs from ssfpb a,ssxxb b,sslddmb  ");
			sql.append(" c,view_njxyzybj d where a.ssbh=b.ssbh and b.lddm=c.lddm and a.xydm=d.xydm and a.bjdm=d.bjdm )a)"+querry+"  order by lddm,cs,qsh,to_number(cwh)");			
		}else{
			sql.append("select  xydm||'/'||ssbh||'/'||fpcws||'/'||nj dm,nj||'/'||bmmc||'/'||ldmc||'/'||cs||'层/'||qsh||'/'||cws||'/'||fpcws mc from ");
			sql.append("(select (case  when a.nj is null then ' ' else a.nj end ) nj,a.xydm,d.bmmc,a.ssbh,a.cws fpcws,b.cs,b.cws,");
			sql.append("b.lddm,b.qsh,c.ldmc,c.xqdm,c.xbxd from ssfpb a,ssxxb b,sslddmb c,zxbz_xxbmdm d where a.ssbh=b.ssbh and ");
		    sql.append("b.lddm=c.lddm and a.xydm=d.bmdm)" + querry + " order by xydm,lddm,qsh ") ;
		}
		return dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });
	}
	/**
	 * 
	 * @param xqdm校区代码
	 * @param xydm学院代码
	 * @param bjdm班级代码
	 * @param nj年级
	 * @return  返回宿舍划分数据统计结果
	 */
	 //获得已划分床位数
	 public  String[] getSsFpYhfcws(String nj,String xydm,String bjdm){
		 String query = "";
		 if(nj != null && !nj.equals("")){
			 query += " and a.nj='"+nj+"' ";
		 }
		 if(!Base.isNull(xydm)&&!xydm.equalsIgnoreCase("null")){
			 query += " and a.xydm='"+xydm+"' ";
		 }
		 if(!Base.isNull(bjdm)&&!bjdm.equalsIgnoreCase("null")){
			 query += " and a.bjdm='"+bjdm+"' ";
		 }
		 DAO dao    = DAO.getInstance();		
		 String querr2 = " b.fpbj ='一般' ";
		 String sql = "select boycws+girlcws+bgcws total,boycws,girlcws,bgcws from (select (select nvl(sum(a.cws),0) boycws "
		 		      +"from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='男' "+query+") boycws,(select nvl(sum(a.cws),0)" 
		 		      +" girlcws from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='女' "+query+") girlcws,(select nvl(sum(a.cws),0)"
		 		      +" bgcws from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='混合' "+query+") bgcws from dual)";
		 return dao.getOneRs(sql, new String[]{}, new String[]{"total","boycws","girlcws","bgcws"});
	 }
	/**
	 * 
	 * @param lddm 楼栋代码
	 * @return 获取宿舍划分中楼层列表
	 */
	public List<HashMap<String, String>> getSsFpCsList(String lddm) {
		String sql = "";
		sql = "select ''dm , '--请选择--'mc from dual union all select dm,'第'||mc||'层'  from (select distinct cs dm,cs mc from ssxxb where lddm=? order by to_number(cs))";
		List<HashMap<String, String>> csList = dao.getList(sql, new String[] { lddm },
				new String[] { "dm","mc" });
		return csList;
	} 
	/**
	 * 
	 * @param xydm学院代码 
	 * @param cwfp 多余床位分配标志
	 * @return 获取床位划分中校区列表
	 */
	public List<HashMap<String, String>> getCwFpXqList(String xydm,String cwfp,String nj,String bjdm){
		StringBuffer querry = new StringBuffer();
		String sql = ""; 
		//String clin = "--请选择年级、院系--";
		String clin = "--请选择--";
		querry.append(" where a.ssbh=b.ssbh ");
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and a.xydm='" + xydm + "' ");  //学院
		querry.append(Base.isNull(nj)?" and 1=1 ":" and a.nj='"+nj+"' ");		     //年级
		if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_HZZY)){//杭州职业
			querry.append(Base.isNull(bjdm)?" and 1=2 ":" and a.bjdm='" + bjdm + "' ");
			clin = "请选择年级,院系,班级";
		}
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
			sql = " select '' dm,'"+clin+"' mc from dual union all select * from  (select distinct b.xqdm dm,b.xqmc mc from ssfpb a,view_ssxx b "+querry+" )";
		}else{
			sql = "select '' dm,'"+clin+"' mc from dual union all select * from (select distinct b.xqdm dm,b.xqmc mc from ssfpb a,view_ssxx b "+querry+" )";
		}
		//List aa = dao.getList(sql,new String[]{},new String[]{"dm","mc"});
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	/**
	 * 
	 * @param xqdm校区代码
	 * @param xydm学院代码
	 * @param xb性别
	 * @param cwfp 多余床位分配标志
	 * @return获取床位分配中楼栋列表
	 */
	public List<HashMap<String,String>>getCwFpLdList(String xqdm,String xydm,String xb,String cwfp,String nj){
		StringBuffer querry = new StringBuffer();
		querry.append(" where a.ssbh=b.ssbh ");	
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
			querry.append(Base.isNull(xb)?" and 1=1 ":" and b.xbxd='" + xb + "' ");				
		}else{			
			querry.append(Base.isNull(xb)?" and 1=2 ":" and b.xbxd='" + xb + "' ");
			querry.append(Base.isNull(xydm)?" and 1=2 ":" and a.xydm='" + xydm + "' ");
			querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and b.xqdm='" + xqdm + "' ");
			querry.append(Base.isNull(nj)?" and 1=2 ":"  and a.nj='" + nj + "' ");			
		}
		String sql = "select ''dm,'--请选择--'mc from dual union all select * from (select distinct b.lddm dm,b.ldmc mc from ssfpb a,view_ssxx b "+querry+" )";
		return dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
	}
	
	/**
	 * 
	 * @param xqdm校区代码
	 * @param xb性别
	 * @return获取床位分配中楼栋列表
	 */
	public List<HashMap<String,String>>getLdList(String xqdm,String xb){
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");	
		querry.append(Base.isNull(xb)?" and 1=2 ":" and xbxd='" + xb + "' ");
		querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and xqdm='" + xqdm + "' ");
		String sql = "select '' dm,'--请选择--' mc from dual union all select * from " +
					"(select distinct lddm dm,ldmc mc from view_ssxx "
					+ querry +" order by to_number(dm))";
		return dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
	}
	/**
	 * 
	 * @param xqdm学期代码
	 * @param xydm学院代码
	 * @param lddm楼栋代码
	 * @param xb性别
	 * @param cs层数
	 * @param cwfp多余床位分配标志
	 * @return获取床位分配中宿舍床位信息列表
	 */
	public synchronized List<HashMap<String,String>>getCwFpSsCwXxList(String xqdm,String xydm,String lddm,String xb,String cs,String cwfp,String nj,String bjdm){
		StringBuffer sql = new StringBuffer();
		String xxdm = dao.getXxdm();
		StringBuffer querry = new StringBuffer();
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
			querry.append(Base.isNull(lddm)?" and 1=2 ":"  and b.lddm='" + lddm + "'");    //楼栋代码
			querry.append(Base.isNull(xb)?" and 1=1 ":"  and b.xbxd='" + xb + "' ");       //性别
			querry.append(Base.isNull(cs)?" and 1=1 ":"  and b.cs='" + cs + "' ");         // 层数
			//querry.append(Base.isNull(nj)?" and 1=1 ":" and a.nj='"+nj+"' ");
			querry.append(" order by dm");
			//querry.append(" order by to_number(Replace(Replace(dm,'-',''),'/',''))");
			sql.append( "select distinct(a.ssbh||'/'||c.cwh) dm,b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh mc from ssfpb a,view_ssxx b,cwxxb c where a.ssbh=b.ssbh and a.ssbh=c.ssbh and a.ssbh||'-'||c.cwh not in (select ssbh||'-'||cwh from xszsxxb)"+querry);
		}else{
			querry.append(Base.isNull(xydm)?" and 1=2 ":"  and a.xydm='" + xydm + "'");
			querry.append((Base.isNull(xqdm)||xqdm.equalsIgnoreCase("null"))?" and 1=2 ":" and b.xqdm='" + xqdm + "'");
			querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" and 1=2 ":"  and b.lddm='" + lddm + "'");
			querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("null"))?" and 1=2 ":"  and b.xbxd='" + xb + "' ");
			querry.append((Base.isNull(cs)||cs.equalsIgnoreCase("null"))?" and 1=1 ":"  and b.cs='" + cs + "' ");
			querry.append((Base.isNull(nj))?" and 1=2 ":" and a.nj='"+nj+"' ");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {//杭州职业技术学院				
				querry.append((Base.isNull(bjdm)||bjdm.equalsIgnoreCase("null"))?" and 1=1 ":" and a.bjdm='"+bjdm+"' ");			
				querry.append(" order by dm ");	
				sql.append(" select a.ssbh||'/'||a.cwh dm,b.ldmc||b.qsh||'/'||b.cws||'/'||a.cwh mc from ssfpb a,view_ssxx b where ");
				sql.append("a.ssbh=b.ssbh and not exists (select * from xszsxxb b where a.ssbh=b.ssbh and a.cwh=b.cwh) "+querry );
			} else {				
//				querry.append(" order by dm");
//				sql.append("select dm,mc from (select a.ssbh || '/' || c.cwh dm,b.ldmc||b.qsh || '/' || b.cws || '/' || c.cwh mc, ");
//				sql.append("a.ssbh,a.nj,a.xydm from ssfpb a, view_ssxx b, cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and a.cws=b.cws ");
//				sql.append("and a.ssbh||c.cwh not in (select distinct ssbh||cwh from xszsxxb) union select a.ssbh || '/' || a.cwh en,  ");
//				sql.append("b.ldmc || b.qsh || '/' || b.cws || '/' || a.cwh cn,a.ssbh ,a.nj,a.xydm from ( select a.ssbh,cwh,xydm,nj from ( ");
//				sql.append("select a.ssbh, rank() over ( partition by a.ssbh,xydm,nj order by to_number(a.cwh) ) px,a.cwh,cws,countcwh,xydm,nj from ( ");
//				sql.append("select ssbh,cws,cwh,countcwh,xydm,nj from (select distinct a.ssbh, c.cwh, a.cws,countcwh,d.xydm,d.nj from ssfpb a,cwxxb c,  ");
//				sql.append("(select ssbh,countcwh,xydm,nj from (select distinct a.ssbh,count(b.cwh) countcwh,a.xydm,a.nj from (select a.ssbh,a.xydm,a.nj ");
//				sql.append("from ssfpb a,view_ssxx b where a.cws <> b.cws and a.ssbh=b.ssbh ) a left join (select distinct a.ssbh,a.cwh,b.nj,b.xydm  ");
//				sql.append("from xszsxxb a,view_xsjbxx b where a.xh=b.xh ) b on a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj group by a.ssbh,a.xydm,a.nj ");
//				sql.append(")) d where a.ssbh = c.ssbh and a.ssbh = d.ssbh and a.ssbh || c.cwh not in (select ssbh || cwh from xszsxxb) and d.countcwh<>a.cws ");
//				sql.append("and a.xydm=d.xydm and a.nj=d.nj )) a )a  where px between 1 and a.cws-a.countcwh ) a,view_ssxx b where a.ssbh = b.ssbh )a, ");
//				sql.append("view_ssxx b,ssfpb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and c.xydm=a.xydm and c.nj=a.nj ");
//				sql.append(querry);
				//辽宁机电职业技术学院 床位号存在中文，个性化修改
				String sb = "";
				if("12898".equals(Base.xxdm)){
					sb = "c.cwh";
				}else{
					sb = "to_number(c.cwh)";
				}
				sql.append(" select dm,mc from(select a.ssbh||'/'||c.cwh dm,b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh mc,");
				sql.append(" to_number(b.cws) - to_number((select count(ssbh) from xszsxxb b where a.ssbh = b.ssbh)) leaveCws,");
				sql.append("  (select count(ssbh)from view_xszsxx b where a.ssbh = b.ssbh and a.nj=b.nj and a.xydm=b.xydm)rzs,");			
   				sql.append(" row_number() over( partition by a.ssbh order by a.ssbh,"+sb+") px,a.cws fps  from ssfpb a,view_ssxx b,");
   				sql.append(" cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh");
   				sql.append(querry.toString());
   				sql.append(" and not exists (select 1 from (select distinct ssbh,cwh from xszsxxb) m where a.ssbh=m.ssbh and c.cwh=m.cwh) ");
   				sql.append(" and not exists (select 1 from wxs_xszsxxb n where a.ssbh=n.ssbh and c.cwh = n.cwh) ");
   				sql.append(")a where px <=fps-rzs");
   			
			}
		}
	    //System.out.println(sql.toString());
		return dao.getList(sql.toString(), new String[] {}, new String[] {"dm", "mc" });
	}
	
	
	/**
	 * 
	 * @param xqdm学期代码
	 * @param lddm楼栋代码
	 * @param xb性别
	 * @param cs层数
	 * @return获取床位分配中宿舍床位信息列表
	 */
	public synchronized List<HashMap<String,String>>getWfpcwxxList(String xqdm,String lddm,String xb,String cs){
		StringBuffer sql = new StringBuffer();
//		String xxdm = dao.getXxdm();
		StringBuffer querry = new StringBuffer();
		querry.append((Base.isNull(xqdm)||xqdm.equalsIgnoreCase("null"))?" and 1=2 ":" and a.xqdm='" + xqdm + "'");
		querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" and 1=2 ":" and a.lddm='" + lddm + "'");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("null"))?" and 1=2 ":" and a.xbxd='" + xb + "' ");
		querry.append((Base.isNull(cs)||cs.equalsIgnoreCase("null"))?" and 1=1 ":" and a.cs='" + cs + "' ");
		sql.append("select a.ssbh||'/'||c.cwh dm,a.ldmc||a.qsh||'/'||a.cws||'/'||c.cwh mc ,a.ssbh||c.cwh sx ");
		sql.append("from  view_ssxx a,cwxxb c where  a.ssbh=c.ssbh ");
		sql.append(querry.toString());
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "c.cwh";
		}else{
			sb = "to_number(c.cwh)";
		}
		sql.append(" and a.ssbh||c.cwh not in (select distinct ssbh||cwh from gygl_jqlxxxb where ssbh is not null) order by a.ssbh,"+sb+" ");
	    //System.out.println(sql.toString());
		return dao.getList(sql.toString(), new String[] {}, new String[] {"dm", "mc" });
	}
	/**
	 * 
	 * @param xydm学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @return 获取床位分配中宿舍床位分配数据统计结果
	 */
	public String[] getCwFpZsData(String xydm,String nj,String bjdm){
		String[] dataTem = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(xydm)?"and 1=1 ":" and xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and nj='" + nj + "' ");
		querry.append((Base.isNull(bjdm)||bjdm.equalsIgnoreCase("bj"))?" and 1=1 ":" and bjdm='" + bjdm + "' ");
	    sql.append("select boys+girls total,boys,girls from (select (select count(xh) from view_xsjbxx where xb='男' "+querry);
	    sql.append(" and xh not in (select xh from  xszsxxb)) boys ,(select count(xh) from view_xsjbxx where xb='女' "+querry);
	    sql.append("and xh not in (select xh from xszsxxb)) girls from dual)");
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boys", "girls" });
		return dataTem;
	}
	/**
	 * 
	 * @param xydm学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @return 获取床位分配中未分配宿舍床位分配数据统计结果
	 */
	public String[] getWfpcwzsData(String xn,String xq,String nj,String xydm){
		String[] dataTem = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer();
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"and 1=1 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?" and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and b.nj='" + nj + "' ");
		querry.append(Base.isNull(xydm)?"and 1=1 ":" and b.xydm='" + xydm + "' ");
		sql.append(" select boys+girls total,boys,girls from ");
		sql.append(" (select ");
		sql.append(" (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b where a.xh=b.xh and b.xb='男' and a.ssbh is null " + querry);
		sql.append(" ) boys, (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b where a.xh=b.xh and b.xb='女' and a.ssbh is null " + querry);
		sql.append(" ) girls  from dual)");
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boys", "girls" });
		return dataTem;
	}
	/**
	 * 
	 * @param xydm学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @return 获取床位分配中已分配宿舍床位分配数据统计结果
	 */
	public String[] getYfpcwzsData(String xn,String xq,String nj,String xydm){
		String[] dataTem = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer();
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"and 1=1 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?" and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and b.nj='" + nj + "' ");
		querry.append(Base.isNull(xydm)?"and 1=1 ":" and b.xydm='" + xydm + "' ");
		sql.append(" select boys+girls total,boys,girls from ");
		sql.append(" (select  (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b ");
		sql.append(" where a.xh=b.xh and b.xb='男' and a.ssbh is not null " + querry);
		sql.append(" ) boys, (select count(a.xh)  from gygl_jqlxxxb a ,view_xsxxb b ");
		sql.append(" where a.xh=b.xh and b.xb='女' and a.ssbh is  not null " + querry);
		sql.append(" ) girls  from dual)");
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boys", "girls" });
		return dataTem;
	}
	/**
	 * 
	 * @param xydm学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @param xb性别
	 * @return获取床位分配中学生列表
	 */
	public  List<HashMap<String,String>> getCwFpSsXsList(String xydm,String nj,String bjdm,String xb){
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(xydm)?"  and 1=2 ":" and xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?"  and 1=1 ":" and nj='" + nj + "' ");
		querry.append(Base.isNull(bjdm) || "bj".equalsIgnoreCase(bjdm) ? " and 1=1 "
						: " and bjdm='" + bjdm + "' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("混合"))?" and 1=1 ":" and xb='" + xb + "' ");
 	    String sql = " select xh dm,xh||'/'||xm||'/'||xb mc from view_xsjbxx where xh not in (select xh from xszsxxb)"
 	    	       + querry+" order by xb desc ,xh ";
 	    List<HashMap<String,String>> xsList = dao.getList(sql, new String[] {}, new String[] {
					"dm", "mc" });
		return xsList;
	}
	
	/**
	 * 
	 * @param xydm学院代码
	 * @param nj年级
	 * @param xn学年
	 * @param xq学期
	 * @param xb性别
	 * @return获取床位分配中学生列表
	 */
	public  List<HashMap<String,String>> getWfpxsxxList(String xn,String xq,String nj,String xydm,String xb){
		StringBuffer querry = new StringBuffer();
		String[] dataTem = null;
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"  and 1=2 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?"  and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(xydm)?"  and 1=1 ":" and b.xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?"  and 1=2 ":" and b.nj='" + nj + "' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("混合"))?" and 1=1 ":" and b.xb='" + xb + "' ");
 	    String sql = " select a.xh dm ,a.xh||'/'||b.xm||'/'||b.xb mc from gygl_jqlxxxb a ,view_xsxxb b where a.xh=b.xh and a.ssbh is null "
 	    	       + querry+" order by b.xb desc ";
 	    List<HashMap<String,String>> xsList = dao.getList(sql, new String[] {}, new String[] {
					"dm", "mc" });
		return xsList;
	}
	/**
	 * 
	 * @param xydm 学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @param cwfp 多余床位分配标志
	 * @return 获取床位分配中已分配床位信息列表
	 */
	public  List<HashMap<String,String>> getCwFpFpCwList(String xydm,String nj,String bjdm,String xb,String cwfp,String lddm){
	   StringBuffer querry = new StringBuffer();
	   String sql = "";
	   querry.append("where 1=1 ");
	   querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm = '"+xydm+"'");
	   querry.append(Base.isNull(nj)?" and 1=1 ":" and nj = '"+nj+"' ");
	   querry.append(Base.isNull(bjdm) || "bj".equalsIgnoreCase(bjdm) ? " and 1=1 "
						: " and bjdm = '" + bjdm + "' ");
	   querry.append((Base.isNull(xb)||"混合".equalsIgnoreCase(xb))?" ":" and xb = '"+xb+"' ");
	   querry.append(Base.isNull(lddm)?"":" and lddm = '"+lddm+"' ");
	   if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
		   sql = "select ''dm,''mc from dual";
	   } else {	   
		   sql = "select distinct (xh||'/'||ssbh||'/'||cwh||'/'||nvl(rzrq,' ')) dm , xh||'/'||xm||'/'||xb||'/'||ldmc||qsh||'/'||cws||'/'||cwh||'/'||nvl(rzrq,' ') mc from view_xszsxx "+querry;
	   }
	   List<HashMap<String,String>> fpCwList = dao.getList(sql, new String[] {}, new String[] {	"dm", "mc" });
		return fpCwList;
	}
	
	/**
	 * 
	 * @param xydm 学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @param cwfp 多余床位分配标志
	 * @return 获取床位分配中已分配床位信息列表
	 */
	public  List<HashMap<String,String>> getYfpqkxxList(String xn,String xq,String nj,String xydm,String xb){
	   StringBuffer querry = new StringBuffer();
	   String sql = "";
	   String[] dataTem = null;
		dataTem = dao.getOneRs("select xqdm,xqmc from xqdzb where xqmc=?",new String[]{xq} , new String[]{"xqdm"});
		querry.append(Base.isNull(xn)?"  and 1=2 ":" and a.xn='" + xn + "' ");
		querry.append(Base.isNull(xq)?"  and 1=1 ":" and a.xq='" + dataTem[0] + "' ");
		querry.append(Base.isNull(xydm)?"  and 1=1 ":" and b.xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?"  and 1=2 ":" and b.nj='" + nj + "' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("混合"))?" and 1=1 ":" and b.xb='" + xb + "' ");
	   sql = "select dm,mc from ( select distinct (a.xh||'/'||a.ssbh||'/'||a.cwh) dm , a.xh||'/'||" +
	   		"b.xm||'/'||b.xb||'/'||c.ldmc||c.qsh||'/'||c.cws||'/'||cwh mc,b.xb from " +
	   		"gygl_jqlxxxb a,view_xsxxb b,view_ssxx c where a.xh=b.xh and a.ssbh=c.ssbh " +
	   		"and a.ssbh is not null "+querry + ") order by xb desc";
	   List<HashMap<String,String>> fpCwList = dao.getList(sql, new String[] {}, new String[] {	"dm", "mc" });
		return fpCwList;
	}
	/**
	 * 
	 * @param xydm 学院代码
	 * @param nj年级
	 * @param bjdm班级代码
	 * @param cwfp 多余床位分配标志
	 * @return 获取床位分配中已分配床位信息列表
	 */
	public String[] getCwYfpZsData(String xydm,String nj,String bjdm){
		String[] dataTem = null;
		StringBuffer querry = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		querry.append(Base.isNull(xydm)?" and 1=1 ":" and xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":"and nj='" + nj + "' ");
		querry.append(Base.isNull(bjdm)?" and 1=1 ":" and bjdm='" + bjdm + "' ");	
		sql.append("select boy+girl total,boy,girl from (select (select count(xh) from view_xszsxx where xb='男'"+querry+" )boy,");
		sql.append("(select count(xh) from view_xszsxx where xb='女' "+querry+" ) girl from dual)");
		//System.out.println(sql.toString());
		dataTem = dao.getOneRs(sql.toString(), new String[] {}, new String[] {"total", "boy", "girl" });
		return dataTem;
	}
	
	public List<HashMap<String,String>> GegGyWxNrFzBmList(String wxnr){
		String sql = "select b.fzbmdm dm,b.fzbmmc mc from csmz_gywxnrdmb a ,csmz_gywxfzbmdmb b where a.fzbmdm=b.fzbmdm and a.nrdm=?";
		return dao.getList(sql, new String []{wxnr}, new String []{"dm","mc"});		
	}
	/**
	 * 
	 * @param lddm 楼栋代码,nj 年级
	 * @return 获取床位分配中楼层列表
	 */
	public List getcwFpCsList(String lddm,String nj,String xydm,String cwfp){
		String sql = "";
		StringBuffer querry = new StringBuffer();
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){			
			sql = "select distinct cs dm,'第'||cs||'层' mc from ssxxb  where ssbh not in(select ssbh from ssfpb) order by to_number(cs) ";
		}else{
			querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
			querry.append(Base.isNull(nj)?" and 1=2 ":" and nj='"+nj+"' ");
			querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm='"+xydm+"' ");
			sql = " select ''dm,'--请选择--'mc  from dual union select distinct cs dm,'第'||cs||'层' mc from ssfpb a,ssxxb b where a.ssbh=b.ssbh "+querry+"  order by dm desc";
		}
		List<HashMap<String, String>> csList = dao.getList(sql, new String[] {},
				new String[] { "dm","mc" });
		return csList;
	}
	
	/**
	 * 
	 * @param lddm 楼栋代码
	 * @return 获取床位分配中楼层列表
	 */
	public List getLcList(String lddm){
		String sql = "";
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
		sql = " select '' dm,'--请选择--' mc from dual union all select dm,mc from " +
				"(select distinct cs dm,'第'||cs||'层' mc from ssxxb a where 1=1 "
				+ querry + "  order by to_number(dm) desc)";
		List<HashMap<String, String>> csList = dao.getList(sql, new String[] {},
				new String[] { "dm","mc" });
		return csList;
	}
	/**
	 * 
	 * @param jzdm
	 * @return 获取军训建制详细信息
	 */
	public String[] getJxjzDate(String jzdm, String nj) {
		String[] dataTem = null;
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			sql = "select NVL(bzdm,'') bzdm,bzmc,bzdj,(case bzdj when '1' then '营级' when '2' then '连级' when '3' then '班级1' when '4' then '班级2' else bzdj end) bzdjmc,NVL(zdy,' ') zdy,NVL(jgmc,' ') jgmc,NVL((select getXbmzJxbzss(?,?,?) sT from dual),' ') ssjz,NVL(bz,' ') bz,xb from XBMZ_JXBZDMB a where bzdm=? and nj=? and xb=?";
			dataTem = dao.getOneRs(sql, new String[] { jzdm, nj, "女", jzdm, nj,
					"女" }, new String[] { "bzdm", "bzmc", "bzdj", "bzdjmc",
					"zdy", "jgmc", "ssjz", "bz", "xb" });
		} else {
			sql = "select xn,nj,bzdm,bzmc,bzdj,(select b.jzmc from jxjzdj b where a.bzdj = b.jzdm) bzdjmc,"
					+ " NVL((select b.xm from jxgl_jgxxb b where a.jgbh=b.jgbh),'-') jgmc,NVL((select b.xm from jxgl_ddjsxxb b where a.jsdm=b.jsdm),'-') zdy,sjdm,NVL((select getJxglbzss(?,?) sT from dual),'-') ssjz,NVL(bz,'-') bz,"
					+ " case when xb = '1' then '男' when xb = '2' then '女' else '-' end xb from jxbzdmb a where bzdm=? and nj=?";
			dataTem = dao.getOneRs(sql, new String[] { jzdm, nj, jzdm, nj, },
					new String[] { "bzdm", "bzmc", "bzdj", "bzdjmc", "zdy",
							"jgmc", "ssjz", "bz", "xb","xn" });
		}

		return dataTem;
	}
	
	public String[] getXbmzJxjzDate(String jzdm,String nj,String sjdm){
		String[] dataTem = null;
		String sql = "";
		sql = "select bzdj from XBMZ_JXBZDMB where bzdm=?";
		String bzdj = dao.getOneRs(sql, new String[] { jzdm }, "bzdj");
		sql = "select xb from XBMZ_JXBZDMB where bzdm=?";
		String xb = dao.getOneRs(sql, new String[] { sjdm }, "xb");
		sql = "select NVL(bzdm,'') bzdm,bzmc,bzdj,(case bzdj when '1' then '营级' when '2' then '连级' when '4' then '班级' else bzdj end) bzdjmc,NVL(zdy,' ') zdy,NVL(jgmc,' ') jgmc,NVL((select getXbmzJxbzss(?,?,?) sT from dual),' ') ssjz,NVL(bz,' ') bz,xb from XBMZ_JXBZDMB a where bzdm=? and nj=? ";
		if ("4".equals(bzdj)) {
			sql += "and xb=?";
			dataTem = dao.getOneRs(sql, new String[] { jzdm, nj, xb, jzdm, nj,
					xb }, new String[] { "bzdm", "bzmc", "bzdj", "bzdjmc",
					"zdy", "jgmc", "ssjz", "bz", "xb" });
		} else {
			dataTem = dao.getOneRs(sql,
					new String[] { jzdm, nj, xb, jzdm, nj, }, new String[] {
							"bzdm", "bzmc", "bzdj", "bzdjmc", "zdy", "jgmc",
							"ssjz", "bz", "xb" });
		}
			
		return dataTem;
	}
	
	/**
	 * 
	 * @return  中国地质大学国家助学贷款——通过展期年限得到展期后贷款结束日期
	 */
	public String getZgdzdxGjzxdkZqqx(String xh,String zqnx){
		String zqhdkqx = "null";
		String num = dao.getOneRs("select count(*) num from zdgzgx_gjzxdk where shjg='通过' and xh=?", new String[]{xh}, "num");
		
		if (!"0".equalsIgnoreCase(num)) {
			zqhdkqx = dao.getOneRs(
					"select nvl((substr(b.dkqx,10,4)+"+zqnx+"),substr(b.dkqx,10,4))||substr(b.dkqx,14,4) zqrq from view_zdgzgx_gjzxdk b where b.xh=?",
					new String[] { xh }, "zqrq");
		}
		return zqhdkqx;
	}
	
	/**
	 * 
	 * @return 奖助学金——得到批量审核具体人数
	 */
	public String getJzxjPlshNum(String pk,String userType){
		String[] pkT = pk.split("!#!");
		int i = 0;
		
		if (pkT != null){
			String sql = "select count(*) num from jzxj_xssqb where xn||xq||xmdm||xh=?";
			if ("xx".equalsIgnoreCase(userType)){
				sql += " and xxsh<>'通过'";
			} else {
				sql += " and xysh<>'通过'";
			}
			for (int j = 0; j < pkT.length; j++){
				String numT = dao.getOneRs(sql, new String[]{pkT[j]}, "num");
				if (!"0".equalsIgnoreCase(numT)){
					i++;
				}
			}
		}
		
		return String.valueOf(i);
	}
	
	/**
	 * 
	 * @return  北京联合大学---通过助学金代码得到助学金相关信息
	 */
	public String[] getBjlhdxZxjxx(String zxjdm){
		String[] sT = dao.getOneRs("select zxjmc,zxjje from xszz_bjlh_gjzxjdmb where zxjdm=?", new String[]{zxjdm}, new String[]{"zxjmc", "zxjje"});
		
		return sT;
	}
	
	/**中国地质大学用人单位岗位更换学生临时表
	 * @throws Exception */
	public String insertYrdwghxslsb(String xh,String gwdm,String gwsbsj,String lxdh,String cz,String userName,String table) throws Exception{
		boolean res = false;
		String sql=null;
		try{
			if("table1".equalsIgnoreCase(table)){
				sql = "insert into zgdzdx_yrdwghxslsb (xh,gwdm,gwsbsj,lxdh,bj,yhm)values(?,?,?,?,?,?)";
				res = dao.insert(sql, new String[]{xh,gwdm,gwsbsj,lxdh,cz,userName});
			}else{
				
				sql = "insert into zgdzdx_yrdwghxslsb (xh,gwdm,gwsbsj,lxdh,bj,yhm)values(?,?,?,?,'申请',?)";
				res = dao.insert(sql, new String[]{xh,gwdm,gwsbsj,lxdh,userName});
			}
		} catch(Exception e){
			res = false;
		}
		if(res){
			return "true";
		}
		return "学生数据重复或无效,请检查!";
	}
	
	/**中国地质大学用人单位岗位更换学生临时表记录删除
	 * @throws Exception */
	public String deleteYrdwghxslsb(String xh,String gwdm,String gwsbsj) throws Exception{
		boolean res = false;
		String sql = "delete zgdzdx_yrdwghxslsb where xh=? and gwdm=? and gwsbsj=?";
		res = dao.runUpdate(sql, new String[]{xh,gwdm,gwsbsj});
		if(res){
			return "ture";
		}
		return "学生数据异常,请检查!";
	}
	
	/**
	 * 获得指定表相关信息(数组)
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public String[] getTableInfo(String tableName, String[] colList, String pk,
			String pkValue, String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk);
		sql.append(" = ? ");
		sql.append(query);

		String[] rs = dao.getOneRs(sql.toString(), new String[] { pkValue },
				colList);

		return rs;
	}
	
	/**
	 * 获得指定表相关信息(列表)
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTableListInfo(String tableName,
			String[] colList, String pk, String pkValue, String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where 1 = 1 ");
		if (!Base.isNull(pk) && !Base.isNull(pkValue)) {
			sql.append(" and ");
			sql.append(pk);
			sql.append(" = '");
			sql.append(pkValue);
			sql.append("'");
		}
		sql.append(query);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}

	/**
	 * 获得指定表相关信息(数组列表)
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public List<String[]> getTableListArrInfo(String tableName,
			String[] colList, String pk, String pkValue, String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where 1 = 1 ");
		if (!Base.isNull(pk) && !Base.isNull(pkValue)) {
			sql.append(" and ");
			sql.append(pk);
			sql.append(" = '");
			sql.append(pkValue);
			sql.append("'");
		}
		sql.append(query);

		List<String[]> list = dao.rsToVator(sql.toString(), new String[] {},
				colList);

		return list;
	}
	
	/**
	 * 根据DWR获得查询内容(数组列表)
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public List<String[]> getRsListForPage(String tableName, String[] colList,
			String pk, String pkValue, String query, String[] pages) {

		DAO dao = DAO.getInstance();

		String pageSize = pages[0];
		String currentPage = pages[1];
		
		String start = String.valueOf((Integer.parseInt(currentPage) - 1)
				* Integer.parseInt(pageSize) + 1);

		String end = String.valueOf(Integer.parseInt(start)
				+ Integer.parseInt(pageSize));
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from (");
		sql.append(" select rownum r,t.* from ");
		sql.append(tableName);
		sql.append(" t where 1 = 1 ");

		if (!Base.isNull(pk) && !Base.isNull(pkValue)) {
			sql.append(" and ");
			sql.append(pk);
			sql.append(" = '");
			sql.append(pkValue);
			sql.append("'");
		}

		sql.append(query);
		sql.append(" ) ");
		
		String countSql = "select count(1) num from (" + sql.toString() + ")";
		String count = dao.getOneRs(countSql, new String[] {}, "num");
			
		sql.append(" where r >=" + start);
		sql.append(" and r <" + end);
		
		List<String[]> list = dao.rsToVator(sql.toString(), new String[] {},
				colList);

		List<String[]> rsList = new ArrayList<String[]>();
		rsList.add(new String[] { count });
		rsList.addAll(list);
		
		return rsList;
	}
	
	/**
	 * 获得指定表的字段
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct zd from (select lower(COLUMN_NAME) zd ");
		sql.append(",length(lower(COLUMN_NAME)) cd ");
		sql.append("from user_tab_cols where table_name ");
		sql.append("in (upper('" + tableName + "'), upper('" + tableName
				+ "'))) order by cd");

		String[] zd = dao.getRs(sql.toString(), new String[] {}, "zd");

		CommService service = new CommService();
		zd=service.getOutValue(zd);
		
		return zd;
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	/**
	 * 获得指定模块的结果集显示表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList, String path) {
		
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		return topTr;
	}
}
