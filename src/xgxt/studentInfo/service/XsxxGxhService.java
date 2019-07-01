package xgxt.studentInfo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XsxxGxhService extends BasicExtendService {
	private DAO dao = DAO.getInstance();
	
	/**
	 * 获取学生与财物编号信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getInfo(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj,a.xydm,a.zydm,a.bjdm,")
			.append("(select b.cwbh from xg_xsxx_xscwbhb b where a.xh=b.xh)cwbh from view_xsjbxx a where xh=?");
			
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 保存财物信息
	 * @param model
	 * @return
	 */
	public boolean saveCwbh(StudentInfoForm model){
		boolean flag = false;
		
		String xh = model.getXh();
		String cwbh = model.getCwbh();
		
		String[] sqlArr = new String[2];
		sqlArr[0] = "delete from xg_xsxx_xscwbhb where xh='" + xh + "'";
		sqlArr[1] = "insert into xg_xsxx_xscwbhb(xh,cwbh) values('" + xh + "','" + cwbh + "')";
		
		try {
			flag = dao.checkBatch(dao.runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获取查询信息
	 * @param model
	 * @return
	 */
	public List<String[]> getRs(StudentInfoForm model){
		String[] colLikeList = new String[]{"xh", "xm", "cwbh"};
		String[] colList = new String[]{"xydm", "zydm", "bjdm"};
		List<String[]> rs = new ArrayList<String[]>();
		
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			
			String sql = "select rownum r,a.* from (select a.*,b.cwbh from view_xsbfxx a,xg_xsxx_xscwbhb b where a.xh=b.xh) a";
			
			rs = CommonQueryDAO.commonQuery(sql, query, inputs,new String[]{"xh","xm","xymc","zymc","bjmc","cwbh"}, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * 删除记录
	 * @param params
	 * @return
	 */
	public boolean delRs(String[] pkValus){
		StringBuilder sql = new StringBuilder();
		boolean flag = false;
		
		List<String[]> params = new ArrayList<String[]>();
		
		for(int i=0; i<pkValus.length; i++){
			params.add(new String[]{pkValus[i]});
		}
		
		sql.append("delete xg_xsxx_xscwbhb where xh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
