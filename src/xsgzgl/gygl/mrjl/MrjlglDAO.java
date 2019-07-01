package xsgzgl.gygl.mrjl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class MrjlglDAO {
	DAO dao = DAO.getInstance();
	
	/**
	 * 获取值班人员
	 * @return
	 */
	public List<HashMap<String, String>> getZbryList(){
		String sql = "select zbrydm,zbrymc from zbrydmb";
		
		return dao.getList(sql, new String[] {}, new String[] {"zbrydm", "zbrymc" });
	}
	
	/**
	 * 每日记录查询
	 * @param model
	 * @return
	 */
	public List<String[]> mrjlQuery(MrjlglForm model){
		List<String[]> list = null;
		
		try {
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			
			// 高级查询输入值
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
//			String[] colList = new String[]{"zbrydm", "lddm"};
//			MakeQuery makeQuery = new MakeQuery();
//			makeQuery.makeQuery(colList, new String[]{}, model);
//			
//			String[] inputs = makeQuery.getInputList();
//			String query = makeQuery.getQueryString();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select rownum r,a.sj||a.zbrydm pk,a.* from xg_view_gygl_mrzbjl a where 1=1 ")
				.append(searchTj)
				.append(" order by sj DESC,lddm");
			
			String[] outputs = new String[]{"pk", "sj", "ldmc","zbrymc", "tq"};
			list = CommonQueryDAO.commonQuery(sql.toString(), "", inputV, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 保存每日记录
	 * @param model
	 * @return
	 */
	public boolean saveMrjl(MrjlglForm model){
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into mrzbjlb(sj,lddm,zbrydm,tq,zbjl,tfsjjcl) values(?,?,?,?,?,?)");
		String[] inputs = new String[]{model.getSj(),model.getLddm(),model.getZbrydm(),model.getTq(),
								model.getZbjl(),model.getTfsjjcl()};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 修改每日记录
	 * @param model
	 * @return
	 */
	public boolean updateMrjl(MrjlglForm model){
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		
		sql.append("update mrzbjlb set lddm=?,tq=?,zbjl=?,tfsjjcl=? where sj||zbrydm=?");
		String[] inputs = new String[]{model.getLddm(),model.getTq(),
								model.getZbjl(),model.getTfsjjcl(), model.getPkValue()};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean delMrjl(List<String[]> params){
		boolean flag = false;
		String sql = "delete mrzbjlb where sj||zbrydm=?";
		
		try {
			int[] rs = dao.runBatch(sql, params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public Map<String, String> getMrjl(String pk){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.* from xg_view_gygl_mrzbjl a where sj||zbrydm=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}
}
