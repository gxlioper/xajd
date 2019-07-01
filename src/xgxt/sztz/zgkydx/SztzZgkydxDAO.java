/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-4-3 上午09:41:03</p>
 */
package xgxt.sztz.zgkydx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class SztzZgkydxDAO {
	public HashMap<String,String> dao_getInfo(String table,String pk,String pkValue){
		DAO dao = DAO.getInstance();
		String[] colList =  dao.getColumnName("select * from "+table);
		for(int i=0;i<colList.length;i++){
			colList[i]=colList[i].toLowerCase();
		}
		String sql = "select * from "+table;
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		if(Base.isNull(pk)){
			querry.append(" and 1=1 ");
		}else{
			querry.append(" and "+pk+"='"+pkValue+"'");
		}
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	public List<HashMap<String, String>> dao_getTzKm(){
		DAO dao = DAO.getInstance();
		String[] colList = new String[]{"kmdm","kmm","sm"};		
		String sql = "select * from sztz_kmdmb order by kmdm ";
		return  dao.getList(sql,new String[]{},colList);
	}
	public String dao_TimeLim() throws SQLException{
		DAO dao = DAO.getInstance();
		String sql = "select * from xnmz_sztz_bjsqsjb where to_char(sysdate,'yyyymmddhh24miss') between kssj and jssj";
		String returntag = dao.returntag(sql, new String[] {});
		return returntag;
	}
	public HashMap<String,String> dao_getLimXnSq(){
		DAO dao = DAO.getInstance();
		String sql = "select xn,xq from xnmz_sztz_bjsqsjb ";
		return dao.getMap(sql, new String[] {},new String[]{"xn","xq"});
	}
	public Vector<Object>  dao_infoInpQuer(String xn,String xq,String xh) {
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String sql    =  "";				
		String[] colList = new String[]{"r","xmmc","js","jb","cj","cjsj"};//查询显示字段 
		sql = "select kmdm,kmm,(case when sm is not null then '('||sm||')' else sm end )sm from sztz_kmdmb order by kmdm ";
		List<HashMap<String, String>>  kmmap = dao.getList(sql,new String[]{},new String[]{"kmdm","kmm","sm"});
		for(int i=0;i<kmmap.size();i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			String kmdm = Base.isNull(kmmap.get(i).get("kmdm"))?"":kmmap.get(i).get("kmdm").toString();
			String kmm = Base.isNull(kmmap.get(i).get("kmm"))?"":kmmap.get(i).get("kmm").toString();
			String sm = Base.isNull(kmmap.get(i).get("sm"))?"":kmmap.get(i).get("sm").toString();
			sql     = " select rownum r,xmmc,js,jb,cj,cjsj from zgkd_tzcjb where xn=? and xq=? and xh=? and kmdm=?";  
			List xmList = dao.getList(sql, new String[] { xn, xq, xh, kmdm },
					colList);
			// ===========begin luojw 2009/6/5==================
			List<HashMap<String, String>> rsList = dao_gethdnrmc(kmdm);

			map.put("rsList", rsList);

			// ===========end luojw 2009/6/5==================
			map.put("xmList",xmList);
			map.put("kmdm",kmdm);
			map.put("kmm",kmm);
			map.put("sm",sm);
			rs.add(map);
		}
		return rs;
	}
	public Vector<Object>  dao_infoInpQuer(String pkValue) {
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String sql    =  "";				
		String[] colList = new String[]{"r","xmmc","js","jb","cj","cjsj"};//查询显示字段 
		sql = "select kmdm,kmm,sm from sztz_kmdmb order by kmdm ";
		List<HashMap<String, String>>  kmmap = dao.getList(sql,new String[]{},new String[]{"kmdm","kmm","sm"});
		for(int i=0;i<kmmap.size();i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			String kmdm = Base.isNull(kmmap.get(i).get("kmdm"))?"":kmmap.get(i).get("kmdm").toString();
			String kmm =  Base.isNull(kmmap.get(i).get("kmm"))?"":kmmap.get(i).get("kmm").toString();
			String sm =  Base.isNull(kmmap.get(i).get("sm"))?"":kmmap.get(i).get("sm").toString();
			sql     = " select rownum r,xmmc,js,jb,cj,cjsj from zgkd_tzcjb where xn||xq||xh=? and kmdm=?";  
			List xmList = dao.getList(sql,new String[]{pkValue,kmdm},colList);
			map.put("xmList",xmList);
			map.put("kmdm",kmdm);
			map.put("kmm",kmm);
			map.put("sm",sm);
			rs.add(map);
		}
		return rs;
	}
	public boolean dao_infoSave(StringBuffer delSqlV,StringBuffer inSqlV ) throws SQLException{
		DAO dao = DAO.getInstance();		
		String[] delSql    = delSqlV.toString().split("!!");
		String[] inserSql  = inSqlV.toString().split("!!");        
		String[] exesql = dao.unionArray(delSql, inserSql);
		int[] array = null;
		array = dao.runBatch(exesql);
		boolean doFlag = dao.checkBatch(array);			
        return doFlag;
	}
	public ArrayList<String[]>  dao_infoQuerry(TzInfoCxModel model){
		DAO dao = DAO.getInstance();
		ArrayList<String[]>  rs = new ArrayList<String[]>();
		String sql    =  "";
		String pk     = "xn||xq||xh";
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"'");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"'");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"'");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"'");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"'");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"'");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%'");		 
		String[] colList = new String[]{"主键","xn","xqmc","xh","xm","nj","bjmc","xxsh","xysh"};
		// =============begin luojw 2009/6/9 ==================
		//sql = " select " + pk + " 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_tzcjxx ";
		sql = " select " + pk + " 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_grsztzxx ";
		// =============end luojw 2009/6/9 ==================
   //	执行查询
	    rs=dao.rsToVator(sql + querry.toString(), new String[]{},colList);
	    return rs;
	}
	public ArrayList<HashMap<String, String>> dao_infoTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList = new String[]{"主键","xn","xqmc","xh","xm","nj","bjmc","xxsh","xysh"};//查询显示字段 
		String[] colListCN = new String[]{ "主键","学年","学期","学号","姓名","年级","班级","学校审核","院系审核"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	public boolean dao_infoDel(String pk,String pkValue) throws Exception{
		DAO  dao = DAO.getInstance();		
		String[] pkValueA = pkValue.split("!!");		
		String[] delPkSql  = new String[pkValueA.length];		
	    for (int i = 0; i < pkValueA.length; i++) {
	    	//===========begin luojw 2009/6/5==================
	    	//delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete zgkd_tzcjb where "+pk+"= '"+pkValueA[i]+"'";		
	    	delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete zgkd_grsztzxxb where "+pk+"= '"+pkValueA[i]+"'";		
	    	//===========end luojw 2009/6/5==================
	    }              	
	    boolean doFlag = false;       
        int[] array = dao.runBatch(delPkSql);
        doFlag = dao.checkBatch(array);           
		return doFlag;
	}
	public ArrayList<HashMap<String, String>> dao_infoChkTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList = new String[]{"主键","xn","xqmc","xh","xm","nj","bjmc","xxsh","xysh"};//查询显示字段 
		String[] colListCN = new String[]{ "主键","学年","学期","学号","姓名","年级","班级","学校审核","院系审核"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]>  dao_infoChkQuerry(TzInfoCxModel model,String userType){
		DAO dao = DAO.getInstance();
		ArrayList<String[]>  rs = new ArrayList<String[]>();
		String sql    =  "";
		String pk     = "xn||xq||xh";
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"'");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"'");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"'");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"'");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"'");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"'");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%'");
		//	===========begin luojw 2009/6/5==================
		if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			querry.append(" and xysh='通过' ");
			sql     = " select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"+pk+" 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_grsztzxx ";
			//sql     = " select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"+pk+" 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_tzcjxx ";
		}else{
			sql     = " select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"+pk+" 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_grsztzxx ";
			//sql     = " select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"+pk+" 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_tzcjxx ";
		}
		String[] colList = new String[]{"bgcolor","主键","xn","xqmc","xh","xm","nj","bjmc","xxsh","xysh"};
		//	===========end luojw 2009/6/5==================
   //	执行查询
	    rs=dao.rsToVator(sql + querry.toString(), new String[]{},colList);
	    return rs;
	}
	public boolean dao_infoChk(String pkValue,String userType,String check) throws SQLException{
		DAO dao = DAO.getInstance();
		String shV     = "";
		if("yes".equalsIgnoreCase(check)){
             shV = "通过";
		}else if("no".equalsIgnoreCase(check)){
			 shV = "不通过";
		}else{
			 shV = "未审核";
		}
		String pk ="xn||xq||xh";
		String[] pkValueA = pkValue.split("!!");
		String sql = "";
		StringBuffer sqlStr = new StringBuffer();
		//	===========begin luojw 2009/6/5==================
		for(int i=0;i<pkValueA.length;i++){
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				sql = "update ZGKD_GRSZTZXXB set xxsh='"+shV+"' where "+pk+"='"+pkValueA[i]+"' ";
				//sql = "update zgkd_tzcjb set xxsh='"+shV+"' where "+pk+"='"+pkValueA[i]+"' ";								
			} else if ("xy".equalsIgnoreCase(userType)) {
				sql = "update ZGKD_GRSZTZXXB set xysh='"+shV+"' where "+pk+"='"+pkValueA[i]+"' ";		
				//sql = "update zgkd_tzcjb set xysh='"+shV+"' where "+pk+"='"+pkValueA[i]+"' ";				
			}
			sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
		}	
		//	===========end luojw 2009/6/5==================
	    int[] array = dao.runBatch(sqlStr.toString().split("!!"));
	    return dao.checkBatch(array); 	    
	}
	public ArrayList<HashMap<String, String>> dao_infoPrintTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList = new String[]{"主键","xn","xqmc","xh","xm","nj","bjmc"};//查询显示字段 
		String[] colListCN = new String[]{ "主键","学年","学期","学号","姓名","年级","班级"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]>  dao_infoPrintQuerry(TzInfoCxModel model){
		DAO dao = DAO.getInstance();
		ArrayList<String[]>  rs = new ArrayList<String[]>();
		String sql    =  "";
		String pk     = "xn||xq||xh";
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"'");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"'");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"'");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"'");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"'");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"'");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%'");
		querry.append(" and xxsh='通过' ");
		String[] colList = new String[]{"主键","xn","xqmc","xh","xm","nj","bjmc"};
		//sql     = " select "+pk+" 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_tzcjxx ";  
		sql     = " select "+pk+" 主键,xn,xqmc,xh,xm,nj,bjmc,xxsh,xysh from view_zgkd_grsztzxx ";  
   //	执行查询
	    rs=dao.rsToVator(sql + querry.toString(), new String[]{},colList);
	    return rs;
	}
	// =============begin luojw 2009/6/4 =======================
	public List<HashMap<String, String>> dao_getKmList() {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "kmdm", "kmm" };
		String sql = "select '00' kmdm, ' ' kmm from dual union select kmdm, kmm from sztz_kmdmb order by kmdm";
		return dao.getList(sql, new String[] {}, colList);
	}
	
	public List<HashMap<String, String>> dao_getHdList(String kmdm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "hdnrdm", "hdnrmc" };
		String sql = "select '00' hdnrdm, ' ' hdnrmc from dual union select hdnrdm,hdnrmc from zgkd_hdnrb where kmdm = ? order by hdnrdm";
		return dao.getList(sql, new String[] {kmdm}, colList);
	}
	
	public List<HashMap<String, String>> dao_getSxList(String kmdm, String hddm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "sxdm", "sxmc" };
		String query = "where 1=1";
		if (!"".equalsIgnoreCase(kmdm)) {
			query += " and kmdm = '" + kmdm + "'";
		}
		if (!"".equalsIgnoreCase(hddm)) {
			query += " and hdnrdm = '" + hddm + "'";
		}
		String sql = "select '00' sxdm, ' ' sxmc from dual union select sxdm,sxmc from zgkd_hdnrsxb " + query
				+ " order by sxdm";
		return dao.getList(sql, new String[] {}, colList);
	}
	
	public List<HashMap<String, String>> dao_gethdnrmc(String kmdm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "hdnrdm","hdnrmc" };
		String sql = "select hdnrdm,hdnrmc from zgkd_hdnrb where kmdm=? order by kmdm, hdnrdm";
		return dao.getList(sql, new String[] {kmdm}, colList);
	}
	
	public List<String> getHdnrsx(String kmm,String hdnrmc) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select a.sxdm,a.sxmc from zgkd_hdnrsxb a, sztz_kmdmb b, zgkd_hdnrb c"
				+ " where a.kmdm = c.kmdm and a.hdnrdm = c.hdnrdm and a.kmdm = b.kmdm and b.kmm = ?"
				+ " and c.hdnrmc = ? order by a.sxdm";
		return dao.getList(sql, new String[] {kmm,hdnrmc}, "sxmc");
	}
	
	public List<String> getXlsxmc(String hdnrdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select e.hdnrdm, e.sxdm, e.sxmc, max(ltrim(sys_connect_by_path((e.xlsxmc) || '', '!@!'), '!@!')) xlsxmc"
				+ " from (select c.hdnrdm, c.sxdm, c.sxmc, c.xlsxmc, row_number() over(partition by d.sxmc order by c.sxmc) pno,"
				+ " row_number() over(partition by d.sxmc order by c.sxmc) - 1 sno from (select b.hdnrdm, b.sxdm, b.sxmc, a.sxmc xlsxmc"
				+ " from zgkd_hdnrsxb b left join zgkd_xlcdsxb a on a.hdnrsxdm = b.sxdm where b.hdnrdm = ? order by b.sxdm) c,"
				+ " (select b.hdnrdm, b.sxdm, b.sxmc, a.sxmc xlsxmc from zgkd_hdnrsxb b left join zgkd_xlcdsxb a on a.hdnrsxdm = b.sxdm"
				+ " where b.hdnrdm = ? order by b.sxdm) d where c.sxmc = d.sxmc group by c.hdnrdm, c.sxdm, c.sxmc, c.xlsxmc, d.sxmc) e"
				+ " start with pno = 1 connect by prior pno = sno and prior e.sxmc = e.sxmc group by e.hdnrdm, e.sxdm, e.sxmc"
				+ " order by sxdm";
		return dao.getList(sql, new String[] {hdnrdm,hdnrdm}, "xlsxmc");
	}
	
	public List<String> getSxmc(String hdnrdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select sxmc from zgkd_hdnrsxb where hdnrdm=? order by sxdm";
		return dao.getList(sql, new String[] {hdnrdm}, "sxmc");
	}
	
	public List<String> getGrSztzNr(String xn,String xq,String xh,String hdnrdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select nr from ZGKD_GRSZTZXXB where xn = ? and xq = ? and xh = ? and hdnrdm=? order by xh,xn,xq,hdnrdm";
		return dao.getList(sql, new String[] { xn, xq, xh, hdnrdm }, "nr");
	}
	
	public List<HashMap<String, String>> getXlsxmc1(String hdnrdm, String sxmc)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "dm", "mc" };
		String sql = "select a.sxdm dm, a.sxmc mc from zgkd_xlcdsxb a, zgkd_hdnrsxb b where a.hdnrdm = ?"
				+ " and b.sxmc = ? and b.hdnrdm = a.hdnrdm and b.sxdm = a.hdnrsxdm order by a.sxdm";
		return dao.getList(sql, new String[] { hdnrdm, sxmc }, colList);
	}
	
	public Vector<Object>  dao_infoInpQuer1(String xn,String xq,String xh,String km) {
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String sql    =  "";				
		String[] colList = new String[]{"r","xmmc","js","jb","cj","cjsj"};//查询显示字段 
		sql = "select kmdm,kmm,(case when sm is not null then '('||sm||')' else sm end)sm from sztz_kmdmb order by kmdm ";
		List<HashMap<String, String>>  kmmap = dao.getList(sql,new String[]{},new String[]{"kmdm","kmm","sm"});
		String hddm="";
		for(int i=0;i<kmmap.size();i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			String kmdm = Base.isNull(kmmap.get(i).get("kmdm"))?"":kmmap.get(i).get("kmdm").toString();
			String kmm = Base.isNull(kmmap.get(i).get("kmm"))?"":kmmap.get(i).get("kmm").toString();
			String sm = Base.isNull(kmmap.get(i).get("sm"))?"":kmmap.get(i).get("sm").toString();
			sql     = " select rownum r,xmmc,js,jb,cj,cjsj from zgkd_tzcjb where xn=? and xq=? and xh=? and kmdm=?";  
			List xmList = dao.getList(sql, new String[] { xn, xq, xh, kmdm },
					colList);
			// ===========begin luojw 2009/6/5==================
			List<HashMap<String, String>>  rsList =dao_gethdnrmc(kmdm);
			
			for (int j = 0; j < rsList.size(); j++) {
				HashMap<String, String> hdMap = rsList.get(j);
				hddm += hdMap.get("hdnrdm") + "-";
			}
			
			String isHdnr = "0";
			if(rsList.size()!=0){
				isHdnr = "1";
			}
			map.put("rsList", rsList);
			map.put("isHdnr",isHdnr);
			// ===========end luojw 2009/6/5==================
			map.put("xmList",xmList);
			map.put("kmdm",kmdm);
			map.put("kmm",kmm);
			map.put("sm",sm);
			map.put("num", kmmap.size());
			rs.add(map);
		}
		rs.add(hddm);
		return rs;
	}
	
	public Vector<Object> dao_infoInpQuer1(String pkValue) throws SQLException {
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String sql = "";
		String hddm = "";
		String[] colList = new String[] { "r", "xmmc", "js", "jb", "cj", "cjsj" };// 查询显示字段
		sql = "select kmdm,kmm,(case when sm is not null then '('||sm||')' else sm end )sm from sztz_kmdmb order by kmdm ";
		List<HashMap<String, String>> kmmap = dao.getList(sql, new String[] {},
				new String[] { "kmdm", "kmm", "sm" });
		for (int i = 0; i < kmmap.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String kmdm = Base.isNull(kmmap.get(i).get("kmdm")) ? "" : kmmap
					.get(i).get("kmdm").toString();
			String kmm = Base.isNull(kmmap.get(i).get("kmm")) ? "" : kmmap.get(
					i).get("kmm").toString();
			String sm = Base.isNull(kmmap.get(i).get("sm")) ? "" : kmmap.get(i)
					.get("sm").toString();
			sql = " select rownum r,xmmc,js,jb,cj,cjsj from zgkd_tzcjb where xn||xq||xh=? and kmdm=?";
			List xmList = dao.getList(sql, new String[] { pkValue, kmdm },
					colList);
			// ===========begin luojw 2009/6/5==================
			List<HashMap<String, String>> rsList = dao_gethdnrmc(kmdm);
			
			for (int j = 0; j < rsList.size(); j++) {
				HashMap<String, String> hdMap = rsList.get(j);
				hddm += hdMap.get("hdnrdm") + "-";			
			}

			map.put("rsList", rsList);

			// ===========end luojw 2009/6/5==================
			map.put("xmList", xmList);
			map.put("kmdm", kmdm);
			map.put("kmm", kmm);
			map.put("sm", sm);
			map.put("num", kmmap.size());
			rs.add(map);
		}
		rs.add(hddm);
		return rs;
	}
	
	public boolean dao_addSztzInfo(String[] pk, String[] hdnrdm, String xh,
			String xn, String xq) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sqldel = "";
		String sqladd = "";
		for (int i = 0; i < pk.length; i++) {
			String pkV = xh + xn + xq + hdnrdm[i];// 得到主键
			sqldel = "delete from ZGKD_GRSZTZXXB where xh||xn||xq||hdnrdm = '" + pkV
					+ "' and nr = '"+ pk[i] + "'";
			
			sb.append(sqldel);
			sb.append("!!#!!");
			
			sqladd = "insert into ZGKD_GRSZTZXXB (xh,xn,xq,hdnrdm,nr) values('"
				+ xh + "','" + xn + "','" + xq + "','" +  hdnrdm[i] + "','"
					+ pk[i] + "')";
			
			sb.append(sqladd);
			sb.append("!!#!!");
			
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		dao.runBatch(pksql);

		return true;
	}
	
	public boolean dao_editSztzInfo(String[] pk, String[] hdnrdm, String [] delHddm,String xh,
			String xn, String xq) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sbdel = new StringBuffer();
		StringBuffer delsb = new StringBuffer();
		StringBuffer sbadd = new StringBuffer();
		String[] delsql = new String[] {};
		String[] addsql = new String[] {};
		String sqldel = "";
		String sqladd = "";

		if (delHddm[0] != null && !"".equals(delHddm[0])) {
			for (int i = 0; i < delHddm.length; i++) {
				String pkV = xh + xn + xq + delHddm[i];// 得到主键
				sqldel = "delete from ZGKD_GRSZTZXXB where xh||xn||xq||hdnrdm = '"
						+ pkV + "'";
				delsb.append(sqldel);
				delsb.append("!!#!!");
			}// end for
			// sql语句拆分成数组
			delsql = delsb.toString().split("!!#!!");
			dao.runBatch(delsql);
		}

		for (int i = 0; i < hdnrdm.length; i++) {
			String pkV = xh + xn + xq + hdnrdm[i];// 得到主键
			sqldel = "delete from ZGKD_GRSZTZXXB where xh||xn||xq||hdnrdm = '"
					+ pkV + "'";
			sbdel.append(sqldel);
			sbdel.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		delsql = sbdel.toString().split("!!#!!");
		dao.runBatch(delsql);

		for (int i = 0; i < pk.length; i++) {
			sqladd = "insert into ZGKD_GRSZTZXXB (xh,xn,xq,hdnrdm,nr) values('"
					+ xh + "','" + xn + "','" + xq + "','" + hdnrdm[i] + "','"
					+ pk[i] + "')";

			sbadd.append(sqladd);
			sbadd.append("!!#!!");
		}
		addsql = sbadd.toString().split("!!#!!");
		dao.runBatch(addsql);

		return true;
	}
	
	public List<String> dao_getNr(String kmmc,String hdnrdm,String xh,String xn,String xq) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select c.nr from sztz_kmdmb a, zgkd_hdnrb b, ZGKD_GRSZTZXXB c where b.kmdm = a.kmdm"
				+ " and c.hdnrdm = b.hdnrdm and a.kmm = ? and b.hdnrdm = ? and c.xh = ? and c.xn = ? and c.xq = ? order by c.hdnrdm";
		return dao.getList(sql, new String[] { kmmc, hdnrdm, xh, xn, xq }, "nr");
	}
	
	public List<String> dao_getHdnrmc(String hdnrdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select hdnrmc from zgkd_hdnrb where hdnrdm = ?";
		return dao.getList(sql, new String[] { hdnrdm }, "hdnrmc");
	}
	
	public List<String> dao_getKmmc() throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select kmm from sztz_kmdmb order by kmdm";
		return dao.getList(sql, new String[] {  }, "kmm");
	}
	
	public List<String> dao_getKmdm() throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select kmdm from sztz_kmdmb order by kmdm";
		return dao.getList(sql, new String[] {  }, "kmdm");
	}
	
	public List<String> dao_getHdmc(String kmdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select hdnrmc from zgkd_hdnrb where kmdm=? order by hdnrdm";
		return dao.getList(sql, new String[] { kmdm }, "hdnrmc");
	}
	
	public List<String> dao_getHddm(String kmdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select hdnrdm from zgkd_hdnrb where kmdm=? order by hdnrdm";
		return dao.getList(sql, new String[] { kmdm }, "hdnrdm");
	}
	
	public List<String> dao_getSxmc(String kmdm,String hdnrdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select sxmc from zgkd_hdnrsxb where kmdm = ? and hdnrdm = ? order by sxdm";
		return dao.getList(sql, new String[] { kmdm,hdnrdm }, "sxmc");
	}
	
	public List<String> dao_getNrmc(String pk,String hdnrdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select nr from ZGKD_GRSZTZXXB where xn||xq||xh = ? and hdnrdm = ?";
		return dao.getList(sql, new String[] { pk,hdnrdm }, "nr");
	}
	
	public List<String> dao_getXlmc(String sxdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select sxmc from zgkd_xlcdsxb where sxdm = ?";
		return dao.getList(sql, new String[] { sxdm }, "sxmc");
	}
	// =============end luojw 2009/6/4 =======================
}
