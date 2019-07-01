/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-6-1 下午02:24:18</p>
 */
package xgxt.xsgygl.whlghxxy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GyglWhlghxxyDAO {
	public List<HashMap<String,String>> findLdList(){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		String sql = "select lddm,ldmc from sslddmb order by  lddm "; 
		list = dao.getList(sql, new String[] {}, new String[] {"lddm", "ldmc" });
		return list;
	}
	public List<HashMap<String,String>> findYfList(){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		list = dao.getScore(1);
		return list;
	}
	public List<HashMap<String,String>> findXnList(){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		list = dao.getXnndList();
		return list;
	}
	public List<HashMap<String,String>> findXqList(){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		list = dao.getXqList();
		return list;
	}
	public String getLdmc(String lddm){
		DAO dao = DAO.getInstance();
		String ldmc = dao.getOneRs("select ldmc from sslddmb where lddm=?",new String[]{lddm}, "ldmc");
		return ldmc;
	}
	public String getXqmc(String xqdm){
		DAO dao = DAO.getInstance();
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm=?",new String[]{xqdm}, "xqmc");
		return xqmc;
	}
	public List<HashMap<String,String>> expDataList(){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select  zymc,bjdm,bjmc,xm,ssbh,cwh,(select count(xh) from xszsxxb d where d.ssbh=a.ssbh) rs,");
		sb.append("max(ltrim(sys_connect_by_path( fdyxm,'、'),'、')) cy from (");
		sb.append("select  distinct b.zymc,b.bjdm,b.bjmc,b.xm,b.ssbh,b.cwh,c.xm fdyxm, ");
		sb.append("row_number() over (partition by b.ssbh,b.xm order by b.ssbh) pno,");
		sb.append("row_number() over (partition by b.ssbh,b.xm order by b.ssbh)-1 sno");
		sb.append(" from fdybjb a,(select a.ssbh,a.zymc,a.bjdm,a.bjmc,a.cwh,a.xm,a.rzrq from view_xszsxx a  order by a.ssbh) b,fdyxxb c ");
		sb.append("where a.bjdm=b.bjdm ");
		sb.append("and a.zgh = c.zgh  group by b.zymc,b.bjdm,b.bjmc,b.ssbh,b.xm,c.xm,b.cwh ");
		sb.append(")a start with pno=1 connect by prior pno=sno");
		sb.append("	and prior ssbh=ssbh  group by ssbh,zymc,bjdm,bjmc,xm,fdyxm,cwh");
		list = dao.getList(sb.toString(), new String[] {}, new String[] {"ssbh", "zymc","bjmc","xm","rs","cwh" });
		return list;
	}
	public List<HashMap<String,String>> findQsbj(GyglWhlghxxyForm form){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(ssbh) cs from (select distinct a.xn,");
		sb.append("a.xq,b.lddm,b.ldmc,b.ssbh,b.bjmc,a.zs from gywsjcb a,view_xszsxx b ");
		sb.append("where a.ssbh=b.ssbh) where 1=1 ");
		if(!Base.isNull(form.getXn())){
			sb.append(" and xn='" + form.getXn() + "'");
		}
		if(!Base.isNull(form.getXq())){
			sb.append(" and xq=" + form.getXq());
		}
		if(!Base.isNull(form.getLddm())){
			sb.append(" and lddm=" + form.getLddm());
		}
		sb.append(" group by ssbh,bjmc order by cs desc");
		list = dao.getList(sb.toString(), new String[]{}, new String[]{"cs"});
		return list;
	}
	public List<HashMap<String,String>> findCs(){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select zs cs from gywsjcb  where zs is not null order by to_number(zs) desc");
		list = dao.getList(sb.toString(), new String[]{}, new String[]{"cs"});
		return list;
	}
	public List<HashMap<String,String>> azExpDataList(GyglWhlghxxyForm form){
		DAO dao = DAO.getInstance();
		List<HashMap<String,String>> list = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select xn,xq,fs,lddm,ldmc,ssbh,bjmc,zs ,(select count(ssbh) from ");
		sb.append("(select distinct a.xn,a.xq,b.ldmc,b.ssbh,b.bjmc,a.zs from gywsjcb a,view_xszsxx b ");
		sb.append("where a.ssbh=b.ssbh) d where d.ssbh=a.ssbh and d.bjmc=a.bjmc ) cs from ( ");
		sb.append("select distinct a.xn,a.xq,a.fs,b.lddm,b.ldmc,b.ssbh,b.bjmc,a.zs from gywsjcb a,view_xszsxx b ");
		sb.append("where a.ssbh=b.ssbh ) a where 1=1 ");
		if(!Base.isNull(form.getXn())){
			sb.append(" and xn='" + form.getXn() + "'");
		}
		if(!Base.isNull(form.getXq())){
			sb.append(" and xq=" + form.getXq());
		}
		if(!Base.isNull(form.getLddm())){
			sb.append(" and lddm=" + form.getLddm());
		}
		sb.append(" order by ssbh,bjmc");
		list = dao.getList(sb.toString(),new String[]{}, new String[]{"xn","xq","fs","lddm","ldmc","ssbh","bjmc","zs","cs"});
		return list;
		//sb.append("");
	}
	public List<HashMap<String, String>> dao_Nwwsdj(){
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> nwwsdj = dao.getList("select nwwsdjdm,nwwsdjmc,nwwsdjfs from nwwsdjdmb order by nwwsdjdm",
				new String[]{},new String[]{"nwwsdjdm","nwwsdjmc","nwwsdjfs"});
		return nwwsdj;
	}
	public boolean createZXFdytjb(String nj,String xn,String xq,String zs) throws Exception{
		DAO dao = DAO.getInstance();
		boolean run = false;
		String sqlT = "{call proc_whlgdxhxxy_azxf(?,?,?,?)}";
		run = dao.runProcuder(sqlT, new String[] {nj,xn,xq,zs});
		return run;
	}
	public String[] getTjbCol(String tableName){
		DAO dao = DAO.getInstance();
		return dao.getColumnName("select * from "+tableName);
	}
	public List<HashMap<String, String>> getAzxfTjValue(){
		DAO dao = DAO.getInstance();
		String[] collist = getTjbCol("whlgdxhxxy_azxfdytjb");
        return dao.getList("select * from whlgdxhxxy_azxfdytjb order by yx", new String[]{},collist);
	}
	public List<HashMap<String, String>> getMergeCol(String tableName){
		DAO dao = DAO.getInstance();
		String sql = "select count(yx)cout,yx from "+tableName+" group by yx order by yx";
		return	 dao.getList(sql,new String[]{},new String[]{"cout","yx"});
	}
	public boolean createZXtjb(String xn,String xq,String zs) throws Exception{
		DAO dao = DAO.getInstance();
		boolean run = false;
		String sqlT = "{call proc_whlgdxhxxy_azx(?,?,?)}";
		run = dao.runProcuder(sqlT, new String[] {xn,xq,zs});
		return run;
	}
	public List<HashMap<String, String>> getAzxTjValue(){
		DAO dao = DAO.getInstance();
		String[] collist = getTjbCol("whlgdxhxxy_azxtjb");
        return dao.getList("select * from whlgdxhxxy_azxtjb order by yx", new String[]{},collist);
	}
	public boolean delTable(String tableName) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.runUpdateTab(" drop table  "+tableName);
	}
	public String getCws(){
		DAO dao = DAO.getInstance();
		return dao.getOneRs(" select max(cwh)cwnum from cwxxb",new String[]{}, "cwnum");
	}
	public boolean createAytjb(String lddm,String nd,String yf) throws Exception{
		DAO dao = DAO.getInstance();
		boolean run = false;
		String sqlT = "{call proc_whlgdxhxxy_wsjc_aytj(?,?,?)}";
		run = dao.runProcuder(sqlT, new String[] {lddm,nd,yf});
		return run;
	}
	public List<HashMap<String, String>> getAYTjValue(){
		DAO dao = DAO.getInstance();
		String[] collist = getTjbCol("whlgdxhxxy_wsjc_aytjb");
        return dao.getList("select * from whlgdxhxxy_wsjc_aytjb order by qs,bj", new String[]{},collist);
	}
	public boolean createZtjb(String xn,String xq,String ld) throws Exception{
		DAO dao = DAO.getInstance();
		boolean run = false;
		String sqlT = "{call proc_whlgdxhxxy_wsjc_aztj(?,?,?)}";
		run = dao.runProcuder(sqlT, new String[] {xn,xq,ld});
		return run;
	}
	public List<HashMap<String, String>> getAZTjValue(){
		DAO dao = DAO.getInstance();
		String[] collist = getTjbCol("whlgdxhxxy_wsjc_aztjb");
        return dao.getList("select * from whlgdxhxxy_wsjc_aztjb order by ssbh", new String[]{},collist);
	}
	public List<HashMap<String, String>> dao_listvalue(GyglWhlghxxyForm form){
		DAO dao = DAO.getInstance();
		String lddm = form.getLddm();
		String lc   = form.getLc();
		String qsh  = form.getQsh();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lc)?"":" and cs='"+lc+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(" order by lddm,cs,qsh ");
		String sql = "select rownum r,a.* from(select ssbh,ldmc,cs,qsh from view_ssxx"+querry+") a";
		return dao.getList(sql,new String[]{},new String[]{"r","ssbh","ldmc","cs","qsh"});
	}
	public List<HashMap<String, String>> dao_getDjList(){
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> djList = dao.getList("select * from nwwsdjdmb order by nwwsdjfs desc",new String[]{},new String[]{"nwwsdjdm","nwwsdjmc","nwwsdjfs"});
	    return djList;
	}
	public List<HashMap<String,String>> dao_getJcBmList(){
		DAO dao = DAO.getInstance();
		String sql = "select bmdm,bmmc from gywsjcbmb";
		List<HashMap<String,String>> bmList = dao.getList(sql, new String[] {}, new String[] {
				"bmdm", "bmmc" });
		return bmList;
	}
	public boolean dao_inputSave(GyglWhlghxxyForm form) throws SQLException{
		String xn = form.getXn();
		String xq = form.getXq();
		String[] jcz=form.getJcz();
		String[] jcsj=form.getJcsj();
		String[] dj=form.getDj();
		String[] fs=form.getFs();
		String[] jcbm=form.getJcbm();
		String[] ssbhv=form.getSsbhv();
		String[] delSql    = new String[jcsj.length];
		String[] inserSql  = new String[jcsj.length];
		for(int i=0;i<jcsj.length;i++){
			if(!Base.isNull(jcsj[i])){
				delSql[i]= "delete from gywsjcb where ssbh='"+ssbhv[i]+"' and jcsj='"+jcsj[i]+"'";
				inserSql[i]="insert into gywsjcb(xn,xq,jcbm,jcsj,fs,dj,ssbh,zs) values('"+xn+"','"+xq+"','"+jcbm[i]+"','"+jcsj[i]+"','"+fs[i]+"','"+dj[i]+"','"+ssbhv[i]+"','"+jcz[i]+"')";
			}
		}
		DAO dao = DAO.getInstance();
		String[] exesql = dao.unionArray(delSql, inserSql);
		int[] array = dao.runBatch(exesql);
		boolean doFlag = dao.checkBatch(array);
		return doFlag;
	}
}
