package xsgzgl.xsxx.byjd.gdjs;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class XsxxByjdDAO extends BasicDAO{
	
	/**
	 * 获取毕业鉴定信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getByjdList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// 用户属性
		String userType=user.getUserType();
		
		//====================过滤条件===================================
		user.setUserStatus(userType);
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
		
		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xh pkValue,a.xh,a.xm, a.nj, a.xydm, a.xymc, a.zydm, a.zymc, a.bjdm, a.bjmc, ");
		sql.append(" case when b.xh is null then '未鉴定' else '详细' end sfyjd, ");
		sql.append(" case when b.xh is null then '否' else '是' end sf ");
		sql.append(" from view_xsjbxx a ");
		sql.append(" left join xg_xsxx_byjdb b on a.xh = b.xh)a ");
		
		sql.append(query);

		sql.append(" order by bjmc,xh");
		
		
		// ====================SQL拼装 end================================
		return  CommonQueryDAO.commonQuery(sql.toString(),"", inputV, colList, model);
	}
	
	/**
	 * 获取毕业鉴定详细信息
	 * @param myForm
	 * @return 
	 */
	public HashMap<String,String>getByjdDetail(XsxxByjdForm myForm){
		
		String xh=myForm.getXh();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xsxx_byjdb where xh=? ");
		
		DAO dao=DAO.getInstance();
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
		
	}
}
