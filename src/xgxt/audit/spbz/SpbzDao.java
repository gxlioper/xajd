package xgxt.audit.spbz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.audit.splc.SplcForm;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批步骤</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpbzDao extends CommDAO {
	
public List<String[]>getSpbzList(SpbzForm myForm) throws Exception{
		
		String[]colList = new String[]{"id", "spgw", "xh", "spgwmc"};
		
		String sql="select rownum r, a.id, a.splc, a.xh, a.spgw, b.mc as spgwmc from xg_xtwh_spbz a, xg_xtwh_spgw b " +
				"where a.spgw = b.id ";
		
		return CommonQueryDAO.commonQuery(sql, " and a.splc = '"+myForm.getSplc()+"'", new String[]{},colList, myForm);
	}
	
	public HashMap<String,String>getSpbz(SpbzForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql="select rownum r, a.id, splc, xh, spgw, b.mc as spgwmc from xg_xtwh_spbz a, xg_xtwh_spgw b " +
				"where a.spgw = b.id and a.id = ?";
		return dao.getMap(sql, new String[]{myForm.getId()}, new String[]{"id","splc","xh","spgw","spgwmc"});
	}
	
	public boolean addSpbz(SpbzForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" insert into xg_xtwh_spbz (splc, xh, spgw) values(?,?,?)";
		
		return dao.insert(sql, new String[]{myForm.getSplc(), String.valueOf(myForm.getXh()), myForm.getSpgw()});	
	}
	
	public boolean modiSpbz(SpbzForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update xg_xtwh_spbz set splc = ?,xh = ?,spbz.spgw = ? where spbz.id = ? ";
		
		return dao.runUpdate(sql, new String[]{myForm.getId(),myForm.getSplc(), String.valueOf(myForm.getXh()), myForm.getSpgw(), myForm.getId()});
	}
	
	public List<HashMap<String,String>>getSpgwList(){

		String sql="select id, mc from xg_xtwh_spgw  ";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"id","mc"});
	}
	
	public int[] delSpbz(SpbzForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		
		//表名
		String tableName = "xg_xtwh_spbz";
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

}
