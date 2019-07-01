/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-15 下午02:30:37</p>
 */
package xgxt.xsgygl.jhzyjsxy.yxlcqsz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;

public class YxlcqszDAO {

	/**
	 * 百佳寝室长申请保存
	 * @throws Exception 
	 */
	public boolean bjqszSqSave(YxlcqszModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xn = DealString.toGBK(model.getXn());
		String xh = DealString.toGBK(model.getXh());
		String lxdh = DealString.toGBK(model.getLxdh());
		String qsjsqk = DealString.toGBK(model.getQsjsqk());
        String ssbh = dao.getOneRs("select ssbh from xszsxxb where xh=?", new String[]{xh},"ssbh");
		boolean done = false;
		String flag = dao.returntag("select * from bjqszxxb where xh||xn=?", new String[]{xh+xn});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into bjqszxxb(xn,xh,ssbh,lxdh,qsjsqk)values(?,?,?,?,?)  ";
			done = dao.runUpdate(sql,new String[]{xn,xh,ssbh,lxdh,qsjsqk});
		}else{
			sql="update bjqszxxb set lxdh=?,qsjsqk=?,fdysh='未审核',xysh='未审核',xxsh='未审核' where xh||xn=? ";
			done = dao.runUpdate(sql,new String[]{lxdh,qsjsqk,xh+xn});			
		}
		return done;
	}
	/**
	 *百佳寝室长查询
	 */
	public  ArrayList<String[]> getBjqszCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = DealString.toGBK(model.getZydm());
		String bjdm = DealString.toGBK(model.getBjdm());
		String nj = DealString.toGBK(model.getNj());
		String xn = DealString.toGBK(model.getXn());
		String lddm = DealString.toGBK(model.getLddm());
		String lc = DealString.toGBK(model.getLc());
		String qsh = DealString.toGBK(model.getQsh());
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and lc='"+lc+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh+ "'");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and ssbh in (select ssbh from jhzy_gyfdyb b where yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		query.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"' and xysh='"+yesNo+"' and xxsh='"+yesNo+"' ");	
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","qsh","fdysh","xysh","xxsh"}; 		
		return CommonQueryDAO.commonQuery("view_bjqszxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 *百佳寝室长审核查询
	 */
	public  ArrayList<String[]> getBjqszShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();

		String xydm = DealString.toGBK(model.getXydm());	
		String zydm = DealString.toGBK(model.getZydm());		
		String bjdm = DealString.toGBK(model.getBjdm());	
		String nj = DealString.toGBK(model.getNj());		
		String xn = DealString.toGBK(model.getXn());
		String lddm = DealString.toGBK(model.getLddm());
		String lc = DealString.toGBK(model.getLc());
		String qsh = DealString.toGBK(model.getQsh());
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and lc='"+lc+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh+ "'");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and ssbh in (select ssbh from jhzy_gyfdyb b where yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		if("xy".equalsIgnoreCase(userType)){		
			query.append(Base.isNull(yesNo)?"":" and xysh = '"+yesNo+"' ");	
			query.append(" and fdysh = '通过' ");	
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			query.append(Base.isNull(yesNo)?"":" and xxsh = '"+yesNo+"' ");
			query.append(" and xysh = '通过' ");	
		}else {
			query.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"' ");
		}
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","qsh","fdysh","xysh","xxsh"}; 
		return dao.rsToVator("select * from view_bjqszxx"+query.toString(), new String[] {xh,xm}, colList);	
	}
	/**
	 * 百佳寝室长申请保存
	 * @throws Exception 
	 */
	public boolean esjczSqSave(YxlcqszModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xn = DealString.toGBK(model.getXn());
		String xh = DealString.toGBK(model.getXh());
		String lxdh = DealString.toGBK(model.getLxdh());
		String zz = DealString.toGBK(model.getZz());
		String szlcqk = DealString.toGBK(model.getSzlcqk());
		String lddm = model.getLddm();
		String lc   = model.getLc();     
		boolean done = false;
		String flag = dao.returntag("select * from esjczxxb where xh||xn=?", new String[]{xh+xn});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into esjczxxb(xn,xh,lddm,lc,zz,lxdh,szlcqk)values(?,?,?,?,?,?,?)  ";
			done = dao.runUpdate(sql,new String[]{xn,xh,lddm,lc,zz,lxdh,szlcqk});
		}else{
			sql="update esjczxxb set lxdh=?,szlcqk=?,fdysh='未审核',xysh='未审核',xxsh='未审核' where xh||xn=? ";
			done = dao.runUpdate(sql,new String[]{lxdh,szlcqk,xh+xn});			
		}
		return done;
	}
	
	/**
	 *二十佳层长查询
	 */
	public  ArrayList<String[]> getEsjczCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = DealString.toGBK(model.getZydm());
		String bjdm = DealString.toGBK(model.getBjdm());
		String nj = DealString.toGBK(model.getNj());
		String xn = DealString.toGBK(model.getXn());
		String lddm = DealString.toGBK(model.getLddm());
		String lc = DealString.toGBK(model.getLc());
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and lc='"+lc+ "'");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and lc in (select cs from jhzy_gyfdyb b,ssxxb c  where c.ssbh=b.ssbh and yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		query.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"' and xysh='"+yesNo+"' and xxsh='"+yesNo+"' ");	
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","lc","fdysh","xysh","xxsh"}; 		
		return CommonQueryDAO.commonQuery("view_esjczxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	
	/**
	 *二十佳层长审核查询
	 */
	public  ArrayList<String[]> getEsjczShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();

		String xydm = DealString.toGBK(model.getXydm());	
		String zydm = DealString.toGBK(model.getZydm());		
		String bjdm = DealString.toGBK(model.getBjdm());	
		String nj = DealString.toGBK(model.getNj());		
		String xn = DealString.toGBK(model.getXn());		
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		String lddm = DealString.toGBK(model.getLddm());
		String lc = DealString.toGBK(model.getLc());
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and lc='"+lc+ "'");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and lc in (select cs from jhzy_gyfdyb b,ssxxb c  where c.ssbh=b.ssbh and yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		if("xy".equalsIgnoreCase(userType)){		
			query.append(Base.isNull(yesNo)?"":" and xysh = '"+yesNo+"' ");	
			query.append(" and fdysh = '通过' ");	
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			query.append(Base.isNull(yesNo)?"":" and xxsh = '"+yesNo+"' ");
			query.append(" and xysh = '通过' ");	
		}else {
			query.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"' ");
		}
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","lc","fdysh","xysh","xxsh"}; 
		return dao.rsToVator("select * from view_esjczxx"+query.toString(), new String[] {xh,xm}, colList);	
	}				
	/**
	 * 五佳楼长申请保存
	 * @throws Exception 
	 */
	public boolean wjlzSqSave(YxlcqszModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xn = DealString.toGBK(model.getXn());
		String xh = DealString.toGBK(model.getXh());
		String lxdh = DealString.toGBK(model.getLxdh());
		String zz = DealString.toGBK(model.getZz());
		String ldqk = DealString.toGBK(model.getLdqk());
		String lddm = model.getLddm();  
		boolean done = false;
		String flag = dao.returntag("select * from wjlzxxb where xh||xn=?", new String[]{xh+xn});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into wjlzxxb(xn,xh,lddm,zz,lxdh,ldqk)values(?,?,?,?,?,?)  ";
			done = dao.runUpdate(sql,new String[]{xn,xh,lddm,zz,lxdh,ldqk});
		}else{
			sql="update wjlzxxb set lxdh=?,ldqk=?,fdysh='未审核',xysh='未审核',xxsh='未审核' where xh||xn=? ";
			done = dao.runUpdate(sql,new String[]{lxdh,ldqk,xh+xn});			
		}
		return done;
	}	
	/**
	 *五佳楼长查询
	 */
	public  ArrayList<String[]> getWjlzCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = DealString.toGBK(model.getZydm());
		String bjdm = DealString.toGBK(model.getBjdm());
		String nj = DealString.toGBK(model.getNj());
		String xn = DealString.toGBK(model.getXn());
		String lddm = DealString.toGBK(model.getLddm());

		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and lddm in (select c.lddm from jhzy_gyfdyb b,ssxxb c  where c.ssbh=b.ssbh and yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		query.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"' and xysh='"+yesNo+"' and xxsh='"+yesNo+"' ");	
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","fdysh","xysh","xxsh"}; 		
		return CommonQueryDAO.commonQuery("view_wjlzxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}	
	/**
	 *五佳楼长审核查询
	 */
	public  ArrayList<String[]> getWjlzShCxList(String yesNo,String userType,String userName, YxlcqszModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
		String xydm = DealString.toGBK(model.getXydm());	
		String zydm = DealString.toGBK(model.getZydm());		
		String bjdm = DealString.toGBK(model.getBjdm());	
		String nj = DealString.toGBK(model.getNj());		
		String xn = DealString.toGBK(model.getXn());
		String lddm = DealString.toGBK(model.getLddm());
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and lddm in (select c.lddm from jhzy_gyfdyb b,ssxxb c  where c.ssbh=b.ssbh and yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		if("xy".equalsIgnoreCase(userType)){		
			query.append(Base.isNull(yesNo)?"":" and xysh = '"+yesNo+"' ");	
			query.append(" and fdysh = '通过' ");	
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			query.append(Base.isNull(yesNo)?"":" and xxsh = '"+yesNo+"' ");
			query.append(" and xysh = '通过' ");	
		}else {
			query.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"' ");
		}
		String[] colList = new String[]{"pk","xn","xh","xm","xymc","bjmc","ldmc","fdysh","xysh","xxsh"}; 
		return dao.rsToVator("select * from view_wjlzxx"+query.toString(), new String[] {xh,xm}, colList);	
	}		
	/**
	 *优秀楼层寝室长批量删除
	 */
	public boolean dao_yxlcqszDel(String userType,String xmk,String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();
		String pk = "";
//		String shType = "";
		String realTable= "";
		if("bjqsz".equalsIgnoreCase(xmk)){
			realTable= "bjqszxxb";
			pk = "xh||xn";
		}else if("esjcz".equalsIgnoreCase(xmk)){
			realTable= "esjczxxb";
			pk = "xh||xn";
		}else if("wjlz".equalsIgnoreCase(xmk)){
			realTable= "wjlzxxb";
			pk = "xh||xn";
		}
		String querry="";
		if("xy".equalsIgnoreCase(userType)){
			querry=" and xxsh='未审核'";
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			
		}else{
			querry="and xysh='未审核' and xxsh='未审核'";
		}
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete "+realTable+" where "+pk+"= '"+pkValueA[i]+"'  "+querry;							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 优秀楼层寝室长批量审核
	 */
	public boolean dao_yxlcqszCk(String userType,String check,String xmk,String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();
		String pk = "";
		String shType = "";
		String realTable= "";
		if("bjqsz".equalsIgnoreCase(xmk)){
			realTable= "bjqszxxb";
			pk = "xh||xn";
		}else if("esjcz".equalsIgnoreCase(xmk)){
			realTable= "esjczxxb";
			pk = "xh||xn";			
		}else if("wjlz".equalsIgnoreCase(xmk)){
			realTable= "wjlzxxb";
			pk = "xh||xn";	
		}
		check = "yes".equalsIgnoreCase(check)?"通过":"不通过";
		if("xy".equalsIgnoreCase(userType)){//院系用户审核时，进行审核荣誉称号数限制控制
			shType = "xysh";		
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){	
			shType = "xxsh";
		}else{
			shType = "fdysh";
		}			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update "+realTable+" set "+shType+"='"+check+"'  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 优秀楼层寝室长单个审核
	 * @throws Exception 
	 */
	public boolean dao_yxlcqszChk(String userType,String pkValue,String xmk,String yesNo) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "";
		boolean doFlag=false;
		String realTable="";
		String pk="";
		if("bjqsz".equalsIgnoreCase(xmk)){			
			realTable="bjqszxxb";
			pk = "xh||xn";
		}else if("esjcz".equalsIgnoreCase(xmk)){
			realTable="esjczxxb";
			pk="xh||xn";
		}else if("wjlz".equalsIgnoreCase(xmk)){
			realTable="wjlzxxb";
			pk="xh||xn";			
		}
		if("xy".equalsIgnoreCase(userType)){
			sql=" update "+realTable+" set xysh=?  where "+pk+"=? ";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,pkValue});
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sql=" update "+realTable+" set xxsh=? where "+pk+"=?";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,pkValue});
		}else{
			sql=" update "+realTable+" set fdysh=?  where "+pk+"=?";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,pkValue});
		}
		return doFlag;
	}
	/**
	 * 判断是否担任楼长，层长，寝室长
	 * @param type："lz","cz","qsz" 
	 * @param xh
	 * @return
	 */
	public boolean lcqszPd(String type,String xh){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "";
		if("lz".equalsIgnoreCase(type)){
			sql = "select count(*)cout from lzxxb where lz=?  and rownum=1";
		
		}else if("cz".equalsIgnoreCase(type)){			
			sql = "select count(*)cout from czxxb where cz=?  and rownum=1";			
		}else if("qsz".equalsIgnoreCase(type)){
			sql = "select count(*)cout from qszxxb where qsz=?  and rownum=1";	
		}
		String cout = dao.getOneRs(sql,new String[]{xh},"cout");	
		if(Integer.parseInt(cout)>0){
			flag= true;
		}
		return flag;
	}
	/**
	 * 百佳寝室长学生查询
	 */
	public  ArrayList<String[]> getBjqszList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select xn,xh,xm,bjmc,fdysh,xysh,xxsh from view_bjqszxx where xh=? order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}
	/**
	 * 二十佳层长学生学生查询
	 */
	public  ArrayList<String[]> getEsjczList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select xn,xh,xm,bjmc,fdysh,xysh,xxsh from view_esjczxx where xh=? order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}
	/**
	 * 五佳楼长学生查询
	 */
	public  ArrayList<String[]> getWjlzList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select xn,xh,xm,bjmc,fdysh,xysh,xxsh from view_wjlzxx where xh=? order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}	
}
