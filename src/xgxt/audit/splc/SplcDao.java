package xgxt.audit.splc;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.gygl.gywh.GyglGywhForm;
import xgxt.utils.CommonQueryDAO;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��������</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SplcDao extends CommDAO {
	
	
	public List<String[]>getSplcList(SplcForm myForm) throws Exception{
		
		String[]colList = new String[]{"id", "djlx", "mc", "ms", "sfmr"};
		
		String sql="select rownum r, id, djlx, mc, ms, sfmr from xg_xtwh_splc ";
		
		return CommonQueryDAO.commonQuery(sql, " where 1=1", new String[]{},colList, myForm);
	}
	
	public HashMap<String,String>getSplc(SplcForm myForm){
		DAO dao=DAO.getInstance();
		
		String sql="select rownum r, id, djlx, mc, ms, sfmr from xg_xtwh_splc where id=? ";
		return dao.getMap(sql, new String[]{myForm.getId()}, new String[]{"id", "djlx", "mc", "ms", "sfmr"});
	}
	
	public boolean addSplc(SplcForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" insert into xg_xtwh_splc (djlx, mc, ms, sfmr) " +
				"values(?,?,?,?)";
		
		return dao.insert(sql, new String[]{myForm.getDjlx(),myForm.getMc(),myForm.getMs(), myForm.getSfmr()});	
	}
	
	public boolean modiSplc(SplcForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		
		String sql=" update xg_xtwh_splc set djlx = ?,mc = ?,ms = ?,sfmr = ? where id = ? ";
		
		return dao.runUpdate(sql, new String[]{myForm.getDjlx(),myForm.getMc(),myForm.getMs(), myForm.getSfmr(), myForm.getId()});
	}
	
	public int[] delSplc(SplcForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		
		//����
		String tableName = "xg_xtwh_splc";
		//����
		String query = " where id= ";
		//����
		String[]checkVal=myForm.getCheckVal();
		
		String[]sqlArr=new String[checkVal.length];
		for(int i=0;i<sqlArr.length;i++){
			sqlArr[i] = " delete from " + tableName + query+"'"+checkVal[i]+"'";
		}
		return dao.runBatch(sqlArr);
	}

}
