/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-11-17 下午02:42:16</p>
 */
package xgxt.xsgygl.zjcmxy;

import java.sql.SQLException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class GyglZjcmxyDAO.
 */
public class GyglZjcmxyDAO {

    /**
     *住宿纪律处理查询
	 */
	public  ArrayList<String[]> dao_getZsjlClQue(GyglZsjlClModel model){
		DAO dao = DAO.getInstance();
		String xydm = DealString.toGBK(model.getXydm());
		String nj = DealString.toGBK(model.getNj());
		String xn = DealString.toGBK(model.getXn());
		String xq = DealString.toGBK(model.getXq());
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String lc = model.getLc();
		String lddm   = model.getLddm();
		String qsh = model.getQsh();
		String sfcf = model.getSfcf();
		String xycljg = model.getXycljg();
		String xh = model.getXh();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String pk ="ssbh||wjsj||wjlbdm";
		xh = Base.isNull(xh) ? "%":"%"+xh+"%";
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");	
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");	
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");	
		query.append(Base.isNull(lc) ? " and 1=1" : " and cs='"+lc+ "'");	
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");	
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh+ "'");	
		query.append(Base.isNull(sfcf) ? " and 1=1" : " and sfcf='"+sfcf+ "'");			
		query.append(Base.isNull(xycljg) ? " and 1=1" : " and xycljg='"+xycljg+ "'");	
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			query.append(" and wjsj between '"+kssj+"' and '"+jssj+"' ");
		}else if(!Base.isNull(kssj)){
			query.append(" and wjsj='"+kssj+"' ");
		}else if(!Base.isNull(jssj)){
			query.append(" and wjsj='"+jssj+"' ");
		}
		
		String[] colList = new String[]{"pk","xn","xqmc","wjsj","ldmc","qsh","wjlbmc","lrrxm","sfcf","xycljgmc","xxsh","xxcljg"};
		StringBuffer sql = new StringBuffer();
		sql.append("select "+pk+" pk,(select xn from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xn,  ");
		sql.append("(select d.xqmc from zjcm_zsjlb b,xqdzb d where b.xq=d.xqdm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xqmc,  ");
		sql.append("(select d.ldmc from zjcm_zsjlb b,view_ssxx d where b.ssbh=d.ssbh and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) ldmc,  ");
		sql.append("(select d.qsh from zjcm_zsjlb b,ssxxb d where b.ssbh=d.ssbh and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) qsh, wjsj, ");
		sql.append("(select d.wjlbmc from zjcm_zsjlb b,gygl_zswjlbdmb d where b.wjlbdm=d.wjlbdm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) wjlbmc,  ");
		sql.append("(select sfcf from zjcm_zsjlb b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm  and sfcf is not null and  rownum=1) sfcf,  ");
		sql.append("(select d.xm lrrxm from zjcm_zsjlb b,yhb d where b.lrr=d.yhm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) lrrxm,  ");
		sql.append("(select d.mc xycljgmc from zjcm_zsjlb b,zsjlcllxb d where b.xycljg=d.dm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and sfcf is not null and rownum=1) xycljgmc, ");
		sql.append("(select xxsh from zjcm_zsjlb b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xxsh,   ");
		sql.append("(select xxcljg from zjcm_zsjlb b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xxcljg   ");
		sql.append("from (select distinct ssbh,wjsj,wjlbdm from view_zjcm_zsjlxx "+query+" and xh like ? and xm like ? ) a order by wjsj desc,ssbh ");

//		sql.append("select "+pk+" pk,(select xn from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xn, ");
//		sql.append("(select xqmc from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xqmc, ");
//		sql.append("(select ldmc from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) ldmc, ");
//		sql.append("(select qsh from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) qsh, ");
//		sql.append("wjsj,(select wjlbmc from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) wjlbmc, ");
//		sql.append("(select sfcf from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) sfcf, ");		
//		sql.append("(select lrrxm from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) lrrxm, ");
//		sql.append("(select xycljgmc from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xycljgmc, ");
//		sql.append("(select xxsh from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xxsh ");
//		sql.append(" from (select distinct ssbh,wjsj,wjlbdm from view_zjcm_zsjlxx "+query+" and xh like ? and xm like ?) a order by wjsj desc,ssbh");		
		return dao.rsToVator(sql.toString(), new String[]{xh,xm}, colList);
	}
	public  List<HashMap<String, String>>  getCljgList(){
		DAO dao = DAO.getInstance();
		String sql = " select dm,mc from ZSJLCLLXB";
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	public  List<HashMap<String, String>> getZsjlXsList(String pkValue,String cfyf) {
	  DAO dao = DAO.getInstance();	
	  String sql = "select  xh||ssbh||wjsj||wjlbdm pks,xh,xm,xb,nj,xymc,zymc,bjmc from view_zjcm_zsjlxx where ssbh||wjsj||wjlbdm =? ";
	  if(!Base.isNull(cfyf)){
		  sql+=" and sfcf is not null";
	  }
	  return dao.getList(sql,new String[]{pkValue}, new String[]{"pks","xh","xm","xb","nj","xymc","zymc","bjmc"});
	}
	public String getZsjlCfXsList(String pkValue) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select  xh||ssbh||wjsj||wjlbdm pks from zjcm_zsjlb where ssbh||wjsj||wjlbdm =?  and sfcf is not null";
		String [] mkcds = dao.getArray(sql, new String []{pkValue}, "pks");
		StringBuffer mkcdTmp    =  new StringBuffer();
		for(int i = 0;i<mkcds.length;i++){
			mkcdTmp.append(mkcds[i]);
			if(i!=mkcds.length-1){
				mkcdTmp.append("!!");
				}
			}
		return mkcdTmp.toString();
	}
	
	public boolean disposeSave(String sfcf,String xycljg,String dcqk,String[] pks,String pkValue) throws Exception{	
		DAO dao = DAO.getInstance();
		String[] pkValueA = pks;
		StringBuffer sqlStr = new StringBuffer();
		String sql ="";
		if("不处分".equalsIgnoreCase(sfcf)){
			xycljg = "";
		}
		sqlStr.append("update zjcm_zsjlb set sfcf='',xycljg='',dcqk='',xxsh='未审核' where ssbh||wjsj||wjlbdm='"+pkValue+"' ").append("!!");
		for(int i=0;i<pkValueA.length;i++){
			sql = "update zjcm_zsjlb set sfcf='"+sfcf+"',xycljg='"+xycljg+"',dcqk='"+dcqk+"',xxsh='未审核' where xh||ssbh||wjsj||wjlbdm='"+pkValueA[i]+"' ";
			sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
		}
		
		boolean doFlag = false;
		int[] array = dao.runBatch(sqlStr.toString().split("!!"));
		doFlag = dao.checkBatch(array); 
		return doFlag;
//		DAO dao = DAO.getInstance();		   
//		String sql = "update zjcm_zsjlb set sfcf=?,xycljg=?,dcqk=?,xxsh='未审核' where ssbh||wjsj||wjlbdm=? ";
//		return dao.runUpdate(sql, new String[]{sfcf,xycljg,dcqk,pkValue});
	}
	/**
	 *住宿纪律处理查询
	 */
	public  ArrayList<String[]> dao_getZsjlChkQue(GyglZsjlClModel model){
		DAO dao = DAO.getInstance();
		String xydm = DealString.toGBK(model.getXydm());
		String nj = DealString.toGBK(model.getNj());
		String xn = DealString.toGBK(model.getXn());
		String xq = DealString.toGBK(model.getXq());
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String lc = model.getLc();
		String lddm   = model.getLddm();
		String qsh = model.getQsh();
		String sfcf = model.getSfcf();
		String xycljg = model.getXycljg();
		String yesNo = model.getYesNo();
		String xh = model.getXh();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String pk ="ssbh||wjsj||wjlbdm";
		xh = Base.isNull(xh) ? "%":"%"+xh+"%";
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");	
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");	
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");	
		query.append(Base.isNull(lc) ? " and 1=1" : " and cs='"+lc+ "'");	
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");	
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh+ "'");	
		query.append(Base.isNull(sfcf) ? " and 1=1" : " and sfcf='"+sfcf+ "'");	
		query.append(Base.isNull(xycljg) ? " and 1=1" : " and xycljg='"+xycljg+ "'");
		query.append(Base.isNull(yesNo) ? " and 1=1" : " and xxsh='"+yesNo+ "'");
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			query.append(" and wjsj between '"+kssj+"' and '"+jssj+"' ");
		}else if(!Base.isNull(kssj)){
			query.append(" and wjsj='"+kssj+"' ");
		}else if(!Base.isNull(jssj)){
			query.append(" and wjsj='"+jssj+"' ");
		}
		query.append(" and sfcf is not null ");
		String[] colList = new String[]{"pk","xn","xqmc","wjsj","ldmc","qsh","wjlbmc","lrrxm","sfcf","xycljgmc","xxsh"};
		StringBuffer sql = new StringBuffer();
		sql.append("select "+pk+" pk,(select xn from view_zjcm_zsjlxx b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xn,  ");
		sql.append("(select d.xqmc from zjcm_zsjlb b,xqdzb d where b.xq=d.xqdm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xqmc,  ");
		sql.append("(select d.ldmc from zjcm_zsjlb b,view_ssxx d where b.ssbh=d.ssbh and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) ldmc,  ");
		sql.append("(select d.qsh from zjcm_zsjlb b,ssxxb d where b.ssbh=d.ssbh and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) qsh, wjsj, ");
		sql.append("(select d.wjlbmc from zjcm_zsjlb b,gygl_zswjlbdmb d where b.wjlbdm=d.wjlbdm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) wjlbmc,  ");
		sql.append("(select sfcf from zjcm_zsjlb b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and sfcf is not null and rownum=1) sfcf,  ");
		sql.append("(select d.xm lrrxm from zjcm_zsjlb b,yhb d where b.lrr=d.yhm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) lrrxm,  ");
		sql.append("(select d.mc xycljgmc from zjcm_zsjlb b,zsjlcllxb d where b.xycljg=d.dm and a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and sfcf is not null and rownum=1) xycljgmc, ");
		sql.append("(select xxsh from zjcm_zsjlb b where a.ssbh||a.wjsj||a.wjlbdm=b.ssbh||b.wjsj||b.wjlbdm and rownum=1) xxsh   ");
		sql.append("from (select distinct ssbh,wjsj,wjlbdm from view_zjcm_zsjlxx "+query+" and xh like ? and xm like ? ) a order by wjsj desc,ssbh ");
		return dao.rsToVator(sql.toString(), new String[]{xh,xm}, colList);
	}
	public boolean doCheckSave(String xxcljg,String yesNo,String pkValue) throws Exception{		
		DAO dao = DAO.getInstance();		   
		String sql = "update zjcm_zsjlb set xxcljg=?,xxsh=? where ssbh||wjsj||wjlbdm=? ";
		return dao.runUpdate(sql, new String[]{xxcljg,yesNo,pkValue});
	}
	public boolean plCheckSave(String pkValue,String  check) throws SQLException{
		DAO dao = DAO.getInstance();
		String[] pkValueA = pkValue.split("!!");
		StringBuffer sqlStr = new StringBuffer();
		String sql ="";
		String shV     = "";
		if("yes".equalsIgnoreCase(check)){
			shV = "通过";
		}else if("no".equalsIgnoreCase(check)){
			shV = "不通过";
		}else{
			shV = "未审核";
		}
		for(int i=0;i<pkValueA.length;i++){
			sql = "update zjcm_zsjlb set xxsh='"+shV+"' where ssbh||wjsj||wjlbdm='"+pkValueA[i]+"' ";
			sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
		}
		boolean doFlag = false;
		int[] array = dao.runBatch(sqlStr.toString().split("!!"));
		doFlag = dao.checkBatch(array); 
		return doFlag;
	}
	/**
	 * Dao_set ld wjlb.获取文件类别
	 * 
	 * @param request the request
	 */
	public List<HashMap<String, String>> dao_setLdWjlb() {
		DAO dao = DAO.getInstance();
		String sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
		List<HashMap<String, String>> wjlbList = dao.getList(sql, new String[] {}, new String[] {"wjlbdm", "wjlbmc" });
		return wjlbList;
	}
	/**
	 * Dao_set ld wjlb.获取楼栋代码
	 * 
	 * @param request the request
	 */
	public List<HashMap<String, String>> dao_setLdlist() {
		DAO dao = DAO.getInstance();
		String sql = "select lddm,ldmc from sslddmb order by  lddm ";
		List<HashMap<String, String>> ldList = dao.getList(sql, new String[] {}, new String[] {"lddm", "ldmc" });
		return ldList;
	}
	/**
	 * Dao_set ld wjlb.
	 * 
	 * @param xh the xh
	 * 
	 * @return the hash map< string, string>
	 */
	public HashMap<String, String> dao_getXsInfo(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,ldmc,lddm,ssbh,qsh,cwh from view_xszsxx where xh=?";
		String[] outputValue = {"xh","xm","xb","nj","xymc","zymc","bjmc","ldmc","ssbh","qsh","cwh","lddm"};
		HashMap<String, String> map = dao.getMap(sql,  new String[]{xh.trim()}, outputValue);
		return map;
	}
	
	/**
	 * Dao_save xs info.
	 * 
	 * @param model the model
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dao_saveXsInfo(zsjlModel model,HttpServletRequest request,String pk) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = true;
		String xsxx = DealString.toGBK(request.getParameter("xsxx"));
		String[] rzStu = new String[1];
		String[] inserSql = new String[1];
		String[] delSql = new String[1];
		String[] colList = {"xh","xn","xq","wjsj","ssbh","wjsy","cwh","wjlbdm","lrr"};
		StringBuffer sqlCol = new StringBuffer(",");
		String lrr = model.getLrr();
		String[] inputList = FormModleCommon.modelToStrings(colList, model);
		for (int i = 1; i < inputList.length; i++) {
			if (i!= inputList.length-1) {
				sqlCol.append("'"+inputList[i].trim()+"'").append(",");
			}else {
				sqlCol.append("'"+lrr+"'");
			}
		} 
		if (!"".equalsIgnoreCase(xsxx)) {
			String[] xx = xsxx.split("!!SplitSignOne!!");
			if (xx.length > 0) {
				rzStu = new String[xx.length];
				inserSql = new String[rzStu.length+1];
				delSql = new String[rzStu.length+1];
				for (int i = 0; i < xx.length; i++) {
					String[] xx1 = xx[i].split(":");
					String xhV = xx1[1].replace("姓名", "").trim();
//					rzStu[i] = xhV;      XH, WJSJ, WJLBDM
					delSql[i] = "delete from zjcm_zsjlb where xh='"+xhV+"' and wjsj='"+model.getWjsj()+"' and wjlbdm='"+model.getWjlbdm()+"'";
					inserSql[i] = "insert into zjcm_zsjlb(xh,xn,xq,wjsj,ssbh,wjsy,cwh,wjlbdm,lrr) " +
						"values('"+xhV+"'"+sqlCol.toString()+")";
				}
			}
		}
		
		if (StringUtils.isNotNull(pk)) {
			inserSql[0] = "update zjcm_zsjlb set wjsj='"+model.getWjsj()+"',wjlbdm='"+model.getWjlbdm()+"',wjsy='"+DealString.toGBK(model.getWjsy())+"' where xh||wjsj||wjlbdm = '"+pk+"'";
		}else {
			if (StringUtils.isNull(xsxx)) {
				delSql[delSql.length-1] = "delete from zjcm_zsjlb where xh='"+model.getXh()+"' and wjsj='"+model.getWjsj()+"' and wjlbdm='"+model.getWjlbdm()+"'";
				inserSql[inserSql.length-1] = "insert into zjcm_zsjlb(xh,xn,xq,wjsj,ssbh,wjsy,cwh,wjlbdm,lrr) " +
					"values('"+model.getXh()+"'"+sqlCol.toString()+")";
			}else {
				delSql[delSql.length-1] = "delete from zjcm_zsjlb where xh='"+model.getXh()+"' and wjsj='"+model.getWjsj()+"' and wjlbdm='"+model.getWjlbdm()+"'";
				inserSql[inserSql.length-1] = "insert into zjcm_zsjlb(xh,xn,xq,wjsj,ssbh,wjsy,cwh,wjlbdm,lrr) " +
					"values('"+model.getXh()+"'"+sqlCol.toString()+")";
			}
		}
		String[] sqlArr = dao.unionArray(delSql, inserSql);
		int[] resust = dao.runBatch(sqlArr);
		for (int i = 0; i < resust.length; i++) {
			if(resust[i]<0){
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * 删除.
	 * 
	 * @param pks the pks
	 * @param tableName the table name
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String dao_AllDelList(String pks,String tableName)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from "+tableName+" where id = '" + pk + "'";
			sb.append(sql);
			sb.append("!!");
		}
		pksql = sb.toString().split("!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	/**
	 * Gets the toptr title.
	 * 
	 * @param tableName the table name
	 * @param colList the col list
	 * 
	 * @return the toptr title
	 */
	public List<HashMap<String, String>> getToptrTitle(String tableName,String[] colList) {
			DAO dao = DAO.getInstance();
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
			return topTr;
	}
	/**
	 * Dao_get query.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the array list< string[]>
	 */
	public ArrayList<String[]> dao_getQuery(zsjlModel model,String tableName) throws Exception, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String lddm = model.getLddm();
		String lc = model.getLc();
		String qsh = model.getQsh();
		String wjlbdm = model.getWjlbdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj ='"+nj.trim()+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn.trim()+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq.trim()+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm ='"+xydm.trim()+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and xq='"+bjdm.trim()+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm ='"+lddm.trim()+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and cs='"+lc.trim()+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh.trim()+ "'");
		query.append(Base.isNull(wjlbdm) ? " and 1=1" : " and wjlbdm ='"+wjlbdm.trim()+ "'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			query.append(" and wjsj between '"+kssj+"' and '"+jssj+"' ");
		}else if(!Base.isNull(kssj)){
			query.append(" and wjsj='"+kssj+"' ");
		}else if(!Base.isNull(jssj)){
			query.append(" and wjsj='"+jssj+"' ");
		}		
		String[] colList = null;
		String sql = "";
		colList = new String[]{"pk","xn","xqmc","xh","xm","xb","nj","bjmc","ldmc","qsh","cwh","wjsj","wjlbmc","lrrxm","sfcf","xxsh"};
		sql = "(select  rownum r,xh||wjsj||wjlbdm pk,a.* from "+tableName+" a "+query.toString()+") ";
		return CommonQueryDAO.commonQuery(sql, query.toString(), new String[] {}, colList,"", model);	
	}
	
	/**
	 * Dao_get del.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return the array list< string[]>
	 */
	public boolean dao_getDel(String pk,HttpServletRequest request) throws Exception{
		String sql = "delete from zjcm_zsjlb where xh||wjsj||wjlbdm ='"+pk+"'";
		boolean result = StandardOperation.delete(sql, "zjcm_zsjlb", request);
		return result;
	
	}
	/**
	 * Dao_set ld wjlb.根据主键获取学生信息
	 * 
	 * @param xh the xh
	 * 
	 * @return the hash map< string, string>
	 */
	public HashMap<String, String> dao_getpkForXsInfo(String pk) {
		DAO dao = DAO.getInstance();
		String sql = "select * from view_zjcm_zsjlxx where xh||wjsj||wjlbdm =?";
		String[] outputValue = null;
		outputValue = dao.getColumnName("select * from view_zjcm_zsjlxx");
		for (int i = 0; i < outputValue.length; i++) {
			outputValue[i] = outputValue[i].toLowerCase();
		}
		HashMap<String, String> map = dao.getMap(sql,  new String[]{pk.trim()}, outputValue);
		return map;
	}
	public List<HashMap<String, String>>  dao_wjlbList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
		List<HashMap<String, String>>  wjlbList = dao.getList(sql, new String[] {}, new String[] {"wjlbdm", "wjlbmc" });
		return wjlbList;
	}
	/**
	 * 导出数据
	 */
	public Vector<Object> dao_expData(String tabName,String[] ColumnName,zsjlModel model) throws Exception {
		DAO dao = DAO.getInstance();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String lddm = model.getLddm();
		String lc = model.getLc();
		String qsh = model.getQsh();
		String wjlbdm = model.getWjlbdm();
		String xh = model.getXh();
		String xm = model.getXm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj ='"+nj.trim()+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn.trim()+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq.trim()+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm ='"+xydm.trim()+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and xq='"+bjdm.trim()+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm ='"+lddm.trim()+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and cs='"+lc.trim()+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh.trim()+ "'");
		query.append(Base.isNull(wjlbdm) ? " and 1=1" : " and wjlbdm ='"+wjlbdm.trim()+ "'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		String sql = "";
		sql = "select * from view_zjcm_zsjlxx a "+query.toString() ;
		Vector<Object> vec = new Vector<Object>();
		vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
		return vec;
	}
	/**
	 * Dao_save xs info.查看学生违纪是否已审核
	 * 
	 * @param model the model
	 * @param request the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public String checkXsInfo(String xh,String xsxx,String wjsj,String wjlbdm,String pkValue) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer buffer = new StringBuffer();
		String sql = "";
		String pk = "";
		if (!"".equalsIgnoreCase(xsxx)) {
			String[] xx = xsxx.split("!!SplitSignOne!!");
			if (xx.length > 0) {
				for (int i = 0; i < xx.length; i++) {
					String[] xx1 = xx[i].split(":");
					String xhV = xx1[1].replace("姓名", "").trim();
					pk = xhV+wjsj+wjlbdm;
					if (StringUtils.isNotNull(pkValue)) {
						sql = "select xh from zjcm_zsjlb where xh||wjsj||wjlbdm = '"+pkValue+"' and ((xxsh = '通过' or xxsh = '不通过') or (sfcf='处分' or sfcf='不处分'))";
					} else {
						sql = "select xh from zjcm_zsjlb where xh||wjsj||wjlbdm = '"+pk+"' and ((xxsh = '通过' or xxsh = '不通过') or (sfcf='处分' or sfcf='不处分'))";
					}
					HashMap<String, String> rsmap = dao.getMap(sql, new String[]{}, new String[]{"xh"});
					if(rsmap != null && rsmap.size()>0){
						buffer.append(xhV).append(",");
					}
				}
			}
		}
		pk = xh+wjsj+wjlbdm;
		if (StringUtils.isNotNull(pkValue)) {
			sql = "select xh from zjcm_zsjlb where xh||wjsj||wjlbdm = '"+pkValue+"' and ((xxsh = '通过' or xxsh = '不通过') or (sfcf='处分' or sfcf='不处分'))";
		} else {
			sql = "select xh from zjcm_zsjlb where xh||wjsj||wjlbdm = '"+pk+"' and ((xxsh = '通过' or xxsh = '不通过') or (sfcf='处分' or sfcf='不处分'))";
		}
		HashMap<String, String> rsmap = dao.getMap(sql, new String[]{}, new String[]{"xh"});
		if(rsmap != null && rsmap.size()>0){
			buffer.append(xh).append("该生相关违纪信息已提交上级处理，不能进行操作");
		}
//		if (StringUtils.isNotNull(buffer.toString())) {
//			buffer = new StringBuffer("学号为 ").append(buffer);
//		}
		return buffer.toString();
	}
	public List<HashMap<String,String>> getWjlbList(){
		DAO dao = DAO.getInstance();
		String sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
		List<HashMap<String, String>> wjlbList = dao.getList(sql, new String[] {}, new String[] {"wjlbdm", "wjlbmc" });
	    return wjlbList;
	}
	public ArrayList<String[]> dao_zsjlStatQue(GyglZsjlClModel model) throws Exception, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String lddm = model.getLddm();
		String lc = model.getLc();
		String qsh = model.getQsh();
		String wjlbdm = model.getWjlbdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String xycljg = model.getXycljg();
		String sfcf = model.getSfcf();
		String kssj = model.getKssj();
		String jssj = model.getJssj();	
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj ='"+nj.trim()+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn.trim()+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq.trim()+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm ='"+xydm.trim()+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and xq='"+bjdm.trim()+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm ='"+lddm.trim()+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and cs='"+lc.trim()+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh.trim()+ "'");
		query.append(Base.isNull(wjlbdm) ? " and 1=1" : " and wjlbdm ='"+wjlbdm.trim()+ "'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		query.append(Base.isNull(xycljg) ? " and 1=1" : " and xycljg = '"+xycljg+ "'");
		query.append(Base.isNull(sfcf) ? " and 1=1" : " and sfcf = '"+sfcf+ "'");
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			query.append(" and wjsj between '"+kssj+"' and '"+jssj+"' ");
		}else if(!Base.isNull(kssj)){
			query.append(" and wjsj='"+kssj+"' ");
		}else if(!Base.isNull(jssj)){
			query.append(" and wjsj='"+jssj+"' ");
		}
		query.append(" and xxsh='通过' ");
		String[] colList = null;
		String sql = "";
		colList = new String[]{"r","xn","xqmc","xh","xm","xb","nj","bjmc","ldmc","qsh","cwh","wjsj","wjlbmc","lrrxm","sfcf","xycljgmc","xxsh"};
		sql = " select rownum r,a.* from (select  * from  view_zjcm_zsjlxx  "+query.toString()+"  order by  xh,xn,xq,wjsj)a ";
		return dao.rsToVator(sql,new String[]{},colList);
	}
	/**
	 * 导出数据
	 */
	public Vector<Object> dao_expStatData(GyglZsjlClModel model,String[] ColumnName) throws Exception {
		DAO dao = DAO.getInstance();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String lddm = model.getLddm();
		String lc = model.getLc();
		String qsh = model.getQsh();
		String wjlbdm = model.getWjlbdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String sfcf = model.getSfcf();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj ='"+nj.trim()+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn.trim()+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq.trim()+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm ='"+xydm.trim()+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and xq='"+bjdm.trim()+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm ='"+lddm.trim()+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and cs='"+lc.trim()+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh.trim()+ "'");
		query.append(Base.isNull(wjlbdm) ? " and 1=1" : " and wjlbdm ='"+wjlbdm.trim()+ "'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		query.append(Base.isNull(sfcf) ? " and 1=1" : " and sfcf ='"+sfcf+ "'");
		query.append(" and xxsh='通过' ");
		String sql = "";		
		sql = "select * from view_zjcm_zsjlxx a "+query.toString() ;
		Vector<Object> vec = new Vector<Object>();
		vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
		return vec;
	}
}
