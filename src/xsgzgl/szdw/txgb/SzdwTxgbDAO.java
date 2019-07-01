package xsgzgl.szdw.txgb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-21 上午09:02:20</p>
 */
public class SzdwTxgbDAO extends CommDAO{
	
	//	 ---------------------------卫生检查检查日程管理 begin-------------------------------------
	/**
	 * 团学干部
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getTxgbList(SzdwTxgbForm model) throws Exception{
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=new String[]{"pkValue","xn","xh","xm","nj","bjmc","zzmc","gbmc","zzjb"};
		
		//====================过滤条件===================================
	
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();
	
		sql.append(" select  xn||'!!@@!!'||a.xh||'!!@@!!'||zzmc||'!!@@!!'||zzjb||'!!@@!!'||gbmc ");
		sql.append(" pkValue,a.* from (select a.*, ");
		sql.append(" b.xydm,b.zydm,b.bjdm,b.xm,b.xb,b.xymc,b.zymc,b.bjmc,b.nj,rownum r from   ");
		sql.append(" xg_szdw_txgbb a left join view_xsjbxx b on a.xh=b.xh)a ");

		sql.append(query);
		
			
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * 查询单条团学干部信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getTxgbMap(SzdwTxgbForm model){
		
		DAO dao=DAO.getInstance();
		
		String pkValue=model.getPkValue();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("  select * from xg_szdw_txgbb ");
		sql.append("  where xn||'!!@@!!'||xh||'!!@@!!'||zzmc||'!!@@!!'||zzjb||'!!@@!!'||gbmc=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pkValue});
		
	}
	
}
