package xsgzgl.xsxx.xsxxhz.zjkj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xsgzgl.xsxx.xsxxhz.XsxxXxhzbDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-25 下午03:25:19</p>
 */
public class XsxxZjkjDAO extends XsxxXxhzbDAO{
	
	/**
	 * 党员信息
	 */
	public HashMap<String,String>getDtInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select max(yydx)yydx,max(rdjjfz)rdjjfz,max(ybdy)ybdy");
		sql.append(" ,max(zsdy)zsdy from (  select  ");	
		sql.append("  case when jddm='09' then jssj else '' end yydx, ");
		sql.append("  case when jddm='05' then jssj else '' end rdjjfz, ");
		sql.append("  case when jddm='07' then jssj else '' end ybdy, ");
		sql.append("  case when jddm='08' then jssj else '' end zsdy ");
		
		sql.append(" from ( select jddm,jssj  from xg_dtjs_xsdtxxjlb a ");
		sql.append(" where xh = ?  and exists  ");
		sql.append(" (select 1 from xg_dtjs_xsdtxxjlb b  where xh = ? ");
		sql.append("  and dqjdbj = '1' and a.jddm <= b.jddm))) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xh});
	}
	
	/**
	 * 获取评奖评优信息
	 */
	public List<HashMap<String,String>>getPjInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
        sql.append(" select a.* from(");
		sql.append(" select rownum r,a.xn,b.zd1 zcf,b.zcfbjpm,b.zcfnjzypm,c.jxj,c.rych from ");
		sql.append(" (select xn from xg_pjpy_zhcpb a where xh=? ");
		sql.append(" union ");
		sql.append(" select xn from xg_pjpy_pjlsxxb b where xh=? ");
		sql.append(" union ");
		sql.append(" select pjxn xn from xg_pjpy_pjxmsqb c where ");
		sql.append(" sqjg='tg' and  ");
		sql.append(" over='yes' and xh=? )a ");
		sql.append(" left join ");
		sql.append(" (select xn,xh,zd1,zcfbjpm,zcfnjzypm from xg_pjpy_zhcpb where xh=?  ");                     
		sql.append(" )b  on a.xn=b.xn left join ");
		sql.append(" (select xn,max(jxj)jxj,max(rych)rych from ( ");
		sql.append(" select xn,to_char(WM_CONCAT(jxj) ");
		sql.append(" over(partition by xn, xh order by xh))jxj, ");
		sql.append(" to_char(WM_CONCAT(rych) ");
		sql.append(" over(partition by xn, xh order by xh))rych from ( ");
		sql.append(" select xh,xn,case when jxj is not null then jxj || xmje else '' end jxj, ");
		sql.append(" case when rych is not null then rych || xmje else '' end rych from ( ");
		sql.append(" select xh,xn, ");
		sql.append(" case  when xmlx = '01' then xmmc  else  ''  end jxj, ");
		sql.append(" case  when xmlx = '02' then xmmc  else  ''  end rych, ");
		sql.append(" case  when xmje is not null then  '(' || xmje || ')'  else   ''  end xmje ");
		sql.append(" from (select xh, xn,  xmlx, xmmc, xmje from ( ");
		sql.append(" select a.xh,a.pjxn xn,b.xmlx,b.xmmc,b.xmje from ( ");
		sql.append(" select * from xg_pjpy_pjxmsqb where sqjg='tg' and  ");
		sql.append(" over='yes' and xh=? )a,xg_pjpy_pjxmwhb b ");
		sql.append(" where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd and a.xmdm=b.xmdm)  union ");
		sql.append(" select xh, xn, xmlx, xmmc, xmje ");
		sql.append(" from xg_view_pjpy_pjlsxx ");
		sql.append(" where xh = ? ) ");
		sql.append(" ))) group by xn,jxj)c ");
		sql.append(" on a.xn=c.xn  ");
	    sql.append(" ) a where 1=1 ");
		 
			
		sql.append(" and rownum <= ? order by xn desc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh,xh,xh,xh,xh,length});
	}
	
	/**
	 * 获取其他奖项、团学干部信息
	 */
	public List<HashMap<String,String>>getQtInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.xn,jlnr,txgbjl from( ");
		sql.append(" select xn from xg_pjpy_qtjlb where xh=? ");
		sql.append(" union ");
		sql.append(" select xn from xg_szdw_txgbb  where xh=?)a ");
		sql.append(" left join ( ");
		sql.append(" select max(jlnr)jlnr,xn from(  ");
		sql.append(" select to_char(WM_CONCAT(jlnr) ");
		sql.append(" over(partition by xn, xh order by xh))jlnr,xn,xh  from ");
		sql.append(" xg_pjpy_qtjlb  where xh=? )group by xn,xh ");
		sql.append(" )b on a.xn=b.xn left join ( ");
		sql.append(" select max(txgbjl)txgbjl,xn from(  ");
		sql.append(" select to_char(WM_CONCAT(txgbjl) ");
		sql.append(" over(partition by xn, xh order by xh))txgbjl,xn,xh  from( ");
		sql.append(" select '担任'||zzmc||gbmc||'('||zzjb||')'txgbjl,xn,xh from xg_szdw_txgbb) ");
		sql.append("  where xh=? )group by xn,xh ");
		sql.append(" )c on a.xn=c.xn ");
		sql.append(" and rownum <= ? order by xn desc ");

		return dao.getListNotOut(sql.toString(), new String[]{xh,xh,xh,xh,length});
	}
	
	/**
	 * 获取实习就业信息
	 */
	public HashMap<String,String>getSxjyInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_jygl_sxjyb where xh=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
}
