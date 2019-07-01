/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2010-1-20 下午01:51:24</p>
 */
package xgxt.xsgygl.czxxzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;

public class CzxxzyGyglDAO {
	public List<HashMap<String,String>> dormcheckQery(WsjcModel model){
		DAO dao = DAO.getInstance();
		String lddm = model.getLddm();
		String lc = model.getLc();
		String qsh = model.getQsh();
		String xn  = model.getXn();
		String xq  = model.getXq();
		String jcsj = model.getJcsj();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lc)?"":" and cs='"+lc+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		String sql=" select rownum r,a.*,(select count(xh) from xszsxxb b where a.ssbh=b.ssbh  )zrrs,"
			+"(select count(xh) from czxxgywsjcb b where xn='"+xn+"' and xq='"+xq+"' and jcsj='"+jcsj+"' and a.ssbh=b.ssbh)jcszrrs "
			+" from (select  a.ssbh,lddm,ldmc,cs,qsh,fs  from (select ssbh,lddm,ldmc,cs,qsh from view_ssxx "+querry+") a " 
			+" left join (select distinct ssbh,jcsj,fs from czxxgywsjcb where xn='"+xn+"' and xq='"+xq+"' and jcsj='"+jcsj+"' ) b " 
			+"on a.ssbh=b.ssbh  order by lddm,qsh) a";
		return dao.getList(sql,new String[]{},new String[]{"r","ssbh","ldmc","cs","qsh","fs","zrrs","jcszrrs"});
	}
	public boolean dormCheckSave(WsjcModel model){
		DAO dao  = DAO.getInstance();
		boolean done = false;
		String[] pkvs = model.getPkvs();
		String[] fs = model.getFs();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String jcsj = model.getJcsj();
		
		//StringBuffer delsql = new StringBuffer();
		StringBuffer inssql = new StringBuffer();
		StringBuffer upsql = new StringBuffer();
//		for(int i=0;i<pkvs.length;i++){
//			if(!Base.isNull(fs[i])){
//				upsql.append("update czxxgywsjcb set fs='"+fs[i]+"' where xh||ssbh||jcsj=(select xh||ssbh||jcsj from czxxgywsjcb where  ) ");
//			}
//		}
		for(int i=0;i<pkvs.length;i++){
			if(!Base.isNull(fs[i])){
				//delsql.append(" delete from czxxgywsjcb where ssbh='"+pkvs[i]+"' and xn='"+xn+"' and xq='"+xq+"' and jcsj='"+jcsj+"' and xh is not " ).append("!!");			
				inssql.append("  insert into czxxgywsjcb(xn,xq,ssbh,xh,jcsj,fs)select '"+xn+"'xn,'"+xq+"'xq, ");
				inssql.append(" '"+pkvs[i]+"'ssbh,xh,'"+jcsj+"'jcsj,'"+fs[i]+"'fs from ( ");
				inssql.append(" select distinct xh from (select distinct xh from xszsxxb where ssbh='"+pkvs[i]+"'   ");
				inssql.append(" and  (select count(xh) from czxxgywsjcb where ssbh='"+pkvs[i]+"'and  xn='"+xn+"' and xq='"+xq+"'  and jcsj='"+jcsj+"')=0)");
				inssql.append(" )").append("!!") ;
				upsql.append("  update czxxgywsjcb set fs='"+fs[i]+"' where ssbh='"+pkvs[i]+"' and  xn='"+xn+"' and xq='"+xq+"'  and jcsj='"+jcsj+"' ").append("!!");
			}
		}
		String[] upSql = upsql.toString().split("!!");
		String[] insSql = inssql.toString().split("!!");
		String[] exesql = dao.unionArray(upSql, insSql);
		int[] array = null;
		try {
			array = dao.runBatch(exesql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		done = dao.checkBatch(array);				
		return done;
	}
	public List<HashMap<String,String>>dormcheckResult(WsjcModel model){
		
		DAO dao = DAO.getInstance();
		String lddm = model.getLddm();
		String lc = model.getLc();
		String qsh = model.getQsh();
		String xn  = model.getXn();
		String xq  = model.getXq();
		String jcsj = model.getJcsj();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lc)?"":" and cs='"+lc+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(" order by a.ssbh ");
		StringBuffer querry2 = new StringBuffer(" where 1=1 ");
		querry2.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry2.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry2.append(Base.isNull(jcsj)?"":" and jcsj='"+jcsj+"' ");
		
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select ldmc,cs,qsh,jcjg from view_ssxx a left join(  ");
		sql.append(" select distinct ssbh,max(ltrim(sys_connect_by_path('('||pno||'、分数：'||fs||' ，检查时间：'||jcsj||')','  ')))jcjg from (  ");
		sql.append(" select ssbh,xn,xq,fs,jcsj,row_number() over (partition by ssbh order by ssbh,jcsj) pno,  ");
		sql.append(" row_number() over (partition by ssbh order by ssbh,jcsj)-1 sno  ");
		sql.append(" from czxxgywsjcb "+querry2+"  group by ssbh,xn,xq,fs,jcsj) start with pno=1 connect by prior pno=sno  ");
		sql.append(" and prior ssbh=ssbh  group by ssbh)b on a.ssbh=b.ssbh  ").append(querry);
		List<HashMap<String,String>>  result = dao.getArrayList(sql.toString(),new String[]{},new String[]{"ldmc","cs","qsh","jcjg"});
		
		return result;
	}
	public boolean autocount(SsgffModel model){
		DAO dao = DAO.getInstance();
		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		if(Base.isNull(xydm)){
			xydm="%";
		}
		try {
			dao.runProcuder("{call czxx_ssgff_autocount(?,?,?)}", new String[] {xn,xq,xydm});// 验证床位信息是否正确
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public ArrayList<String[]>dpointResearch(SsgffModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String xydm = DealString.toGBK(model.getXydm());
		String nj = DealString.toGBK(model.getNj());
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xq = model.getXq();
		String xn = DealString.toGBK(model.getXn());
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%": "%" + xh.trim() + "%";
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm.trim() + "%";		
		StringBuffer query = new StringBuffer(" where 1=1");				
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");		
		query.append(Base.isNull(zydm)?"":" and zydm = '"+zydm+"' ");	
		query.append(Base.isNull(bjdm)?"":" and bjdm ='"+bjdm+"' ");	
		query.append(Base.isNull(xh)?"":" and xh like '"+xh+"' ");	
		query.append(Base.isNull(xm)?"":" and xm  like '"+xm+"' ");	
		String[] colList = new String[]{"xh","xm","xymc","bjmc","nwf","glf","gff"};
//		String sql = "(select  rownum r,id,xn,nj, lzm,qss,xss,xydm,wshgl,wsyxl,xjqsl,xxsh from jhzy_wmgylsqb a "+query.toString()+") ";
		StringBuffer sql = new StringBuffer();sql.append("");
		sql.append("(select nj,xydm,zydm,bjdm,a.xh,xm,xymc,bjmc,nvl(b.nwf,0)nwf,nvl(b.jlf,0)glf,nvl(b.gff,0)gff  ");
		sql.append("from view_xsjbxx a left join (select * from ssshgff where xn='"+xn+"' and xq='"+xq+"' ) b on a.xh=b.xh )");
		//sql.append(query);
		return CommonQueryDAO.commonQuery(sql.toString(), query.toString(), new String[] {}, colList,"", model);	
	}
	public List<HashMap<String,String>> dao_expShGFF(SsgffModel model) {
		DAO dao  = DAO.getInstance();
		String xydm = DealString.toGBK(model.getXydm());
		String nj = DealString.toGBK(model.getNj());
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xq = model.getXq();
		String xn = DealString.toGBK(model.getXn());
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%": "%" + xh.trim() + "%";
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm.trim() + "%";			
		StringBuffer query = new StringBuffer(" where 1=1");				
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");		
		query.append(Base.isNull(zydm)?"":" and zydm = '"+zydm+"' ");	
		query.append(Base.isNull(bjdm)?"":" and bjdm ='"+bjdm+"' ");	
		query.append(Base.isNull(xh)?"":" and a.xh like '"+xh+"' ");	
		query.append(Base.isNull(xm)?"":" and xm  like '"+xm+"' ");	
		String[] colList = new String[]{"xh","xm","xymc","bjmc","nwf","glf","gff"};

		StringBuffer sql = new StringBuffer();sql.append("");
		sql.append("select nj,xydm,zydm,bjdm,a.xh,xm,xymc,bjmc,nvl(b.nwf,0)nwf,nvl(b.jlf,0)glf,nvl(b.gff,0)gff  ");
		sql.append("from view_xsjbxx a left join (select * from ssshgff where xn='"+xn+"' and xq='"+xq+"' ) b on a.xh=b.xh ");
		query.append(" order by xh");
		return dao.getList(sql+query.toString(),new String[]{},colList);
	}
}
