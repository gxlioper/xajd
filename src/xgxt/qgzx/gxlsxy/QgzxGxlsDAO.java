package xgxt.qgzx.gxlsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.CommanForm;
import xsgzgl.comm.BasicDAO;

public class QgzxGxlsDAO extends BasicDAO{
	
	/**
	 * 根据勤工助学主键获取学生勤工信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String,String>getQgzxInfo(String pkValue){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select  *  from view_xsgwxx  ");
		sql.append(" where xh||gwdm||sqsj= ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pkValue});
		
	}
	
	/**
	 * 获取勤工助学岗位信息
	 * @param qsxn
	 * @param zzxn
	 * @return
	 */
	public List<HashMap<String,String>>getQgzxGwInfo(CommanForm model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		String qsxn=model.getQsxn();
		
		String zzxn=model.getZzxn();
		
		sql.append(" select  gwdm,gzsj,gznr,gwtsyq,xyrs,bz ");
		sql.append("  from gwxxb a where 1=1 ");
		
		if(!Base.isNull(qsxn)){
			
			sql.append(" and xn>= ? ");
			
			inputV.add(qsxn);
		}
		
		if(!Base.isNull(zzxn)){
			
			sql.append(" and xn<= ? ");
			
			inputV.add(zzxn);
		}
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	 
	}
}
