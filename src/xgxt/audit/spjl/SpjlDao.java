package xgxt.audit.spjl;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.audit.spgw.SpgwForm;
import xgxt.audit.spjl.SpjlForm;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批记录</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpjlDao extends CommDAO {
	
	public List<String[]>getSpjlList(SpjlForm myForm) throws Exception{
		
		String[]colList = new String[]{"sprq", "sprmc", "sftg","thgwmc","spyj"};
		
		
		String sql="select rownum r, spjl.id, djlx, spbz, djh, psfs, spyj, psry, spr, " +
			"sprq, sftg, thgw, yhb.xm as sprmc, spgw.mc as thgwmc " +
        	"from xg_xtwh_spjl spjl, xg_xtwh_spbz spbz, xg_xtwh_spgw spgw, yhb " +
        	"where spjl.spbz = spbz.id and spjl.thgw = spgw.id(+) and spjl.spr = yhb.yhm ";
		
		return CommonQueryDAO.commonQuery(sql, "and djh='"+myForm.getSqdh()+"'", new String[]{},colList, myForm);
	}
	
	public HashMap<String,String>getSpjl(SpjlForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql="select rownum r, id, djlx, spbz, djh, psfs, spyj, psry, spr, " +
		"sprq, sftg, thgw, yhb.yhmc as sprmc, spgw.mc as thgwmc " +
    	"from xg_xtwh_spjl spjl, xg_xtwh_spbz spbz, xg_xtwh_spgw spgw, yhb " +
    	"where spjl.spbz = spbz.id and spjl.thgw = spgw.id(+) and spjl.spr = yhb.yhm " +
    	"and spjl.id = ?";
		return dao.getMap(sql, new String[]{myForm.getId()}, new String[]{"id", "djlx", 
				"spbz", "djh", "psfs", "spyj", "psry", "spr", "sprq", "sftg", "thgw", "sprmc", "thgwmc"});
	}
	
	public boolean addSpjl(SpjlForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" insert into xg_xtwh_spjl (djlx, spbz, djh, psfs, spyj, psry, spr, sprq, sftg, thgw) " +
				"values(?,?,?,?,?,?,?,?,?,?)";
		
		return dao.insert(sql, new String[]{myForm.getDjlx(),myForm.getSpbz(), myForm.getDjh(), myForm.getPsfs(), myForm.getSpyj(), 
				myForm.getPsry(), myForm.getSpr(), String.valueOf(myForm.getSprq()), myForm.getSftg(), myForm.getThgw() });	
	}
	
	public boolean modiSpjl(SpjlForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update xg_xtwh_spjl set id = ?, djlx = ?, spbz = ?, djh = ?, psfs = ?, spyj = ?, psry = ?, " +
				"spr = ?, sprq = ?, sftg = ?, thgw = ? where id = ? ";
		
		return dao.runUpdate(sql, new String[]{myForm.getId(),myForm.getDjlx(),myForm.getSpbz(), myForm.getDjh(), myForm.getPsfs(), myForm.getSpyj(), 
				myForm.getPsry(), myForm.getSpr(), String.valueOf(myForm.getSprq()), myForm.getSftg(), myForm.getThgw(), myForm.getId()});
	}
	
	public int[] delSpjl(SpjlForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		
		//表名
		String tableName = "xg_xtwh_spjl";
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
	
	public List<HashMap<String,String>>getSpbzList(SpjlForm myForm){

		String sql="select spbz.id, spbz.xh, spbz.spgw, spgw.mc as spgwmc " +
				"from xg_xtwh_spbz spbz, xg_xtwh_spgw spgw " +
				"where spbz.spgw = spgw.id and spbz.splc = '" +myForm.getSplc()+ "'" +
				"order by spbz.xh asc";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"id","xh","spgw","spgwmc"});
	}

}
