package xsgzgl.qgzx.glygl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxGlyglDAO {
	DAO dao = DAO.getInstance();
	
	
	/**
	 * 获得勤工管理员list
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGlyList(QgzxGlyglForm model) throws Exception{
		String sql = "select rownum r,a.* from (select a.yhm,b.xm,b.zdm,b.szbm,c.bmmc,d.zmc from XG_QGZX_QGGLYB a " +
				"left join yhb b on a.yhm = b.yhm left join zxbz_xxbmdm c on b.szbm = c.bmdm " +
				"left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm order by yhm,zdm,szbm) a where 1=1 ";
		String[] output = new String[] {"yhm","r","yhm","xm","zmc","bmmc"};
		return CommonQueryDAO.commonQuery(sql, "", new String[]{},
				output, model);
	}
	
	
	/**
	 * 获得勤工用户list
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> getYhList(QgzxGlyglForm model) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		
		String sql = "select rownum r,a.* from (select b.yhm,b.xm,b.zdm,b.szbm bmdm,c.bmmc,d.zmc,c.bmlb from yhb b " +
				"left join zxbz_xxbmdm c on b.szbm = c.bmdm left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm where c.bmlb = '1' " +
				"and not exists(select 1 from XG_QGZX_QGGLYB a where a.yhm = b.yhm))a where 1=1 ";
		String[] output = new String[] {"yhm","r","yhm","xm","zmc","bmmc"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV,
				output, model);
	}
	
	
	/**
	 * 管理员批量增加保存
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean glyZjbc(List<String[]> params) throws SQLException {
		String sql = "insert into XG_QGZX_QGGLYB(yhm)values(?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 管理员批量删除
	 * @param params
	 * @return
	 */
	public boolean glySc(List<String[]> params) throws SQLException {
		String sql = "delete from XG_QGZX_QGGLYB where yhm = ? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 是否勤工管理员
	 * @param yhm
	 * @return
	 */
	public boolean sfQggly(String yhm) {
		String sql = "select count(1) num from XG_QGZX_QGGLYB where yhm = ? ";
		String num = dao.getOneRs(sql, new String[]{yhm}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}

}
