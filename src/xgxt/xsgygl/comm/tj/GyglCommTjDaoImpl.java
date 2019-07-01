package xgxt.xsgygl.comm.tj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
/**
 * author lyl
 * Dao层的实现
 */
public class GyglCommTjDaoImpl implements GyglCommTjDao{
	/**入住情况统计*/
	@SuppressWarnings("unchecked")
	public List dao_FpDeTailView( String userDep,String nj,String xydm,String zydm,String bjdm){
		DAO dao         = DAO.getInstance();
		List list       = new ArrayList();
		//nj = "";
//		xydm = "106";
//		zydm = "414";
//		bjdm = "2004414660401";//
		String userType = dao.getUserType(userDep);
		
		if(userType.equalsIgnoreCase("xy")){
			xydm=userDep;
		}
		//maxNj 当前年  2011
		String maxNj = dao.getOneRs("select to_char(sysdate,'yyyy')maxnj  from dual ", new String[]{}, "maxnj");
		
		StringBuffer querrT = new StringBuffer();
		querrT.append(Base.isNull(nj)?" nj>='"+(Integer.parseInt(maxNj)-11)+"' ":" nj='"+nj+"' ");
		querrT.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querrT.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querrT.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		
		StringBuffer sql    = new StringBuffer();		
		sql.append("select a.xydm,a.xymc,xyzcout,nvl(xyyzcout,0)xyyzcout,nvl(xywzcout,0)xywaizcout,xyzcout-(nvl(xyyzcout,0))-(nvl(xywzcout,0))xywzcout,round((xyzcout-(nvl(xyyzcout,0))) / xyzcout * 100,2) zwzbl,round((nvl(xyyzcout,0)) / xyzcout * 100,2) rzbl," );
		sql.append("(select count(distinct bjdm) from view_xsjbxx b where a.xydm=b.xydm and "+querrT+" group by xydm)bjcout  ");
		sql.append("from (select xydm,xymc,count(xh)xyzcout from view_xsjbxx where "+querrT+" group by xydm,xymc order by xydm )a left join  ");
		sql.append("(select xydm,xymc, count(xh)xyyzcout from view_xszsxx where "+querrT+" group by xydm,xymc ) b on a.xydm=b.xydm  ");			
		sql.append("left join (select xydm,xymc, count(xh)xywzcout from view_gygl_xswzxx where  "+querrT+"  group by xydm,xymc) d  on a.xydm = d.xydm  ");	
		List<HashMap<String,String>>  xyList = dao.getList(sql.toString(), new String[]{},new String[]{"xydm","xymc","xywzcout","xyzcout","xyyzcout","bjcout","zwzbl"});
//        int j=0;
        if(xyList!=null){
        	StringBuffer querry   = new StringBuffer();
    		querry.append(Base.isNull(nj)?" a.nj>='"+(Integer.parseInt(maxNj)-11)+"' ":" a.nj='"+nj+"' ");   //传第一个参数     	
        	querry.append(Base.isNull(zydm)?"":" and a.zydm='"+zydm+"' ");
        	querry.append(Base.isNull(bjdm)?"":" and a.bjdm='"+bjdm+"' "); 	
        	
        	StringBuffer querryP   = new StringBuffer();  //传第二个参数
        	querryP.append(Base.isNull(nj)?" p.nj>='"+(Integer.parseInt(maxNj)-11)+"' ":" p.nj='"+nj+"' ");
        	querryP.append(Base.isNull(zydm)?"":" and p.zydm='"+zydm+"' ");
        	querryP.append(Base.isNull(bjdm)?"":" and p.bjdm='"+bjdm+"' "); 	
        	for(int i=0;i<xyList.size();i++){        		
        		StringBuffer sqlV    = new StringBuffer();
        		HashMap map = new HashMap();
        		map.put("xymc",xyList.get(i).get("xymc"));
        		map.put("xyzcout",xyList.get(i).get("xyzcout"));//学院总人数
        		map.put("xyyzcout",xyList.get(i).get("xyyzcout"));//学院已住人数
        		map.put("xywzcout",xyList.get(i).get("xywzcout"));//学院未住人数
        		map.put("bjcout",xyList.get(i).get("bjcout"));//班级总数
        		map.put("zwzbl",xyList.get(i).get("zwzbl"));//未入住比例
        		BigDecimal   b   =   new   BigDecimal(100-Double.valueOf(xyList.get(i).get("zwzbl")));
        		double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        		map.put("zrzbl",f1);//未入住比例
        		
        		map.put("xywaizcout",Integer.valueOf(xyList.get(i).get("xyzcout"))-Integer.valueOf(xyList.get(i).get("xywzcout"))-Integer.valueOf(xyList.get(i).get("xyyzcout")));//学院总人数        		        		          		
        		sqlV.append("select zcout,nj,bjmc,zymc,bjdm,zydm,xydm, nvl(yzcout, 0) yzcout,nvl(waizcout, 0)waizcout,xydm,bjdm,nvl(zcout,0) -nvl(yzcout,0)-nvl(waizcout,0) wzcout,round((nvl(zcout, 0) - nvl(yzcout, 0)) / nvl(zcout, 0) * 100, 2) wzbl,100-(round((nvl(zcout, 0) - nvl(yzcout, 0)) / nvl(zcout, 0) * 100, 2)) rzbl  from (  select zcout,p.nj,p.zymc,p.bjmc,p.zydm,p.bjdm,p.xydm,p.yzcout,q.waizcout from (select zcout,a.nj,a.zymc,a.bjmc,a.zydm,a.bjdm,a.xydm,yzcout from (select count(*) zcout, bjdm, xydm,nj,zydm,bjmc,zymc from view_xsjbxx  group by bjdm, bjmc,xydm,nj,zydm,zymc)a left join(select count(*) yzcout, bjdm, xydm,nj,zydm from view_xszsxx group by bjdm,xydm,nj,zydm) b on a.bjdm = b.bjdm and a.xydm=b.xydm and a.zydm=b.zydm and a.nj=b.nj where  "+querry+"  and a.xydm=?  order by zydm,bjdm) p left join(select count(*) waizcout, bjdm, xydm,nj,zydm from view_gygl_xswzxx group by bjdm,xydm,nj,zydm) q on p.bjdm = q.bjdm and p.xydm=q.xydm and p.zydm=q.zydm and p.nj=q.nj where "+querryP+"  and p.xydm=?  order by  p.zydm desc)");
        		List listTem = dao.getTjList(sqlV.toString(),new String[]{xyList.get(i).get("xydm")},new String[]{"nj","zymc","bjmc","zcout","yzcout","waizcout","wzcout","wzbl","rzbl"});//总人数、已住人数、未住人数、未住比例       		
        		
        		map.put("coutinfo",listTem);
        		list.add(map);          		       		
        	}       	
        }
        return list;
	}
}
