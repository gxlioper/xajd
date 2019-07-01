package xgxt.audit.spgw;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.audit.splc.SplcForm;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批岗位</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpgwDao extends CommDAO {
	
	public List<String[]>getSpgwList(SpgwForm myForm) throws Exception{
		
		String[]colList = new String[]{"id", "mc", "ms", "sfzz"};
		
		String sql="select rownum r, id, mc, ms, sfzz, zzjs from xg_xtwh_spgw ";
		
		return CommonQueryDAO.commonQuery(sql, " where 1=1", new String[]{},colList, myForm);
	}
	
	public HashMap<String,String>getSpgw(SpgwForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql="select rownum r, id, mc, ms, sfzz, zzjs from xg_xtwh_spgw where id=? ";
		return dao.getMap(sql, new String[]{myForm.getId()}, new String[]{"id", "mc", "ms", "sfzz", "zzjs"});
	}
	
	public boolean addSpgw(SpgwForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" insert into xg_xtwh_spgw (mc, ms, sfzz, zzjs) " +
				"values(?,?,?,?)";
		
		return dao.insert(sql, new String[]{myForm.getMc(),myForm.getMs(), myForm.getSfzz(), myForm.getZzjs()});	
	}
	
	public boolean modiSpgw(SpgwForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update xg_xtwh_spgw set mc = ?, ms = ?, sfzz = ?, zzjs = ? where id = ? ";
		
		return dao.runUpdate(sql, new String[]{myForm.getMc(),myForm.getMs(), myForm.getSfzz(), myForm.getZzjs(), myForm.getId()});
	}
	
	public int[] delSpgw(SpgwForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		
		//表名
		String tableName = "xg_xtwh_spgw";
		//条件
		String query = " where id= ";
		//主键
		String[]checkVal=myForm.getCheckVal();
		
		String[]sqlArr=new String[checkVal.length];
		for(int i=0;i<sqlArr.length;i++){
			sqlArr[i] = " delete from " + tableName + query+"'"+checkVal[i]+"'";
		}
		return dao.runBatch(sqlArr);
	}
	
	public List<String[]>getSpgwYhList(SpgwForm myForm) throws Exception{
		
		String[]colList = new String[]{"yhm", "xm", "dwdm", "xq"};
		
		String sql="select rownum r, yhb.yhm, yhb.xm, yhb.dwdm, yhb.xq " +
				"from yhb, xg_xtwh_spgwyh gwyh " +
				"where yhb.yhm = gwyh.spyh ";
		
		String query = " and gwyh.spgw = " + myForm.getSpgw();
		
		return CommonQueryDAO.commonQuery(sql, query, new String[]{},colList, myForm);
	}
	
	
	public int[] addSpgwYh(SpgwForm myForm) throws Exception{
		
		DAO dao = DAO.getInstance();
		//主键
		String[]checkVal=myForm.getCheckVal();
		
		String[]sqlArr=new String[checkVal.length];
		
		for(int i=0;i<sqlArr.length;i++){
			sqlArr[i] = " insert into xg_xtwh_spgwyh (spgw, spyh) values('"+myForm.getSpgw()+"','"+checkVal[i]+"')";
		}
		
		return dao.runBatch(sqlArr);	
	}
	
	
	public int[] delSpgwYh(SpgwForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		
		//表名
		String tableName = "xg_xtwh_spgwyh";
		//条件
		String query = " where spgw = ";

		String[]sqlArr=new String[1];
		sqlArr[0] = " delete from " + tableName + query+"'"+myForm.getSpgw()+"'";
		return dao.runBatch(sqlArr);
	}
	
	public List<HashMap<String,String>>getYhList(SpgwForm myForm){

		String sql="select yhm, xm from yhb ";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"yhm","xm"});
	}
	
	//可以添加的岗位用户
	public List<HashMap<String,String>>getNotExistedYhList(SpgwForm myForm){

		String sql="select yhm, xm from yhb " +
				"where not exists( " +
				"select * from xg_xtwh_spgwyh gwyh " +
				"where gwyh.spgw = '" + myForm.getSpgw()+"' and gwyh.spyh = yhb.yhm" +
				")";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"yhm","xm"});
	}
	
	public List<HashMap<String,String>>getGwyhList(SpgwForm myForm){

		String sql="select yhm, xm from yhb, xg_xtwh_spgwyh gwyh " +
				"where yhb.yhm = gwyh.spyh  and gwyh.spgw = '" + myForm.getSpgw()+"'";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"yhm","xm"});
	}
	
	public List<String[]>getGwyhLists(SpgwForm myForm) throws Exception{

		
		String[]colList = new String[]{"yhm","xm"};
		
		String sql="select rownum r, yhm, xm from yhb, xg_xtwh_spgwyh gwyh " +
		"where yhb.yhm = gwyh.spyh  and gwyh.spgw = '" + myForm.getSpgw()+"'";
		
		return CommonQueryDAO.commonQuery(sql, "", new String[]{},colList, myForm);
	}
	
	
	public List<HashMap<String,String>>getQueryYhList(String spgw, String query){

		String sql="select yhm, xm from yhb " +
				"where not exists( " +
				"select * from xg_xtwh_spgwyh gwyh " +
				"where gwyh.spgw = '" + spgw+"' and gwyh.spyh = yhb.yhm" +
				")" + query;
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"yhm","xm"});
	}

}
