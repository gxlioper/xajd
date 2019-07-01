package xsgzgl.gygl.ntzd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

public class NtzdDAO extends SuperDAOImpl<NtzdForm>{
	DAO dao = DAO.getInstance();

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(NtzdForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(NtzdForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */	
	@Override
	protected void setTableInfo() {
		super.setKey("ny");
		super.setTableName("XG_GYGL_NTZD_YKHXS");
		
	}

	/**
	 * @throws Exception  
	 * @描述:取得月考核系数管理 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getNykhxsPageList(NtzdForm model) throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" select t.NY,t.KHXS,t.DYSSKHFZ from XG_GYGL_NTZD_YKHXS t ");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @throws SQLException  
	 * @描述:判断该年月数据库是否存在
	 * @作者：qilm
	 * @日期：2013-9-26 
	 * @param ny
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int getCountNykhxs(String ny) throws SQLException {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT  ");
		sql.append(" 	count(*) count ");
		sql.append(" FROM XG_GYGL_NTZD_YKHXS t ");
		sql.append(" WHERE ny = '" + ny + "' ");
		return dao.getOneRsint(sql.toString());
	}

	
	/** 
	 * @描述:取得班级月考核得分 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getBjydfPageList(NtzdForm model) throws Exception {
		return getBjydfPageList(model,false);
	}
	/**
	 * 
	 * @描述:取得班级月考核得分 列表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-11 上午09:35:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param isDc 是否导出操作
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getBjydfPageList(NtzdForm model,boolean isDc) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT  ");
		sql.append(" 	t.NY, ");
		sql.append(" 	t.BJDM, ");
		sql.append(" 	t.ZYDM, ");
		sql.append(" 	t.XYDM, ");
		sql.append(" 	t.NJ, ");
		sql.append(" 	t.YKHDF, ");
		sql.append(" 	t.XYPM, ");
		sql.append(" 	t.XXPM, ");
		sql.append(" 	t.BJMC, ");
		sql.append(" 	t.ZYMC, ");
		sql.append(" 	t.XYMC, ");
		sql.append(" 	t.XYBJS, ");
		sql.append(" 	t.XXBJS, ");
		sql.append(" 	t.XN, ");
		sql.append(" 	t.XQ, ");
		sql.append(" 	(select xqmc from XQDZB where xqdm = t.xq) XQMC ");
		sql.append(" FROM XG_GYGL_NTZD_BJYDF t ");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		if(isDc){
			//导出所有 982
			return dao.getListNotOut(sql.toString(), inputV);
		}
		return getPageList(model, sql.toString(), inputV);
	}
	/** 
	 * @描述:取得班级月考核得分 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXyydfPageList(NtzdForm model) throws Exception {
		return getXyydfPageList(model, false);
	}
	public List<HashMap<String, String>> getXyydfPageList(NtzdForm model,boolean isDc) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT  ");
		sql.append(" 	t.NY, ");
		sql.append(" 	t.XYDM, ");
		sql.append(" 	t.XYMC, ");
		sql.append(" 	t.YKHDF, ");
		sql.append(" 	t.XXPM, ");
		sql.append(" 	t.XXXYS, ");
		sql.append(" 	t.XN, ");
		sql.append(" 	t.XQ, ");
		sql.append(" 	(select xqmc from XQDZB where xqdm = t.xq) XQMC ");
		sql.append(" FROM XG_GYGL_NTZD_XYYDF t ");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		if(isDc){
			return dao.getListNotOut(sql.toString(), inputV);
		}
		return getPageList(model, sql.toString(), inputV);
	}
	/** 
	 * @描述:取得特殊宿舍 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getTsqstjPageList(NtzdForm model) throws Exception {
		return getTsqstjPageList(model, false);
	}
	public List<HashMap<String, String>> getTsqstjPageList(NtzdForm model,boolean isDc) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT  ");
		sql.append(" 	t.NY, ");
		sql.append(" 	t.QSH, ");
		sql.append(" 	t.LDDM, ");
		sql.append(" 	t.LDMC, ");
		sql.append(" 	t.CH, ");
		sql.append(" 	t.QSXB, ");
		sql.append(" 	t.BJDM, ");
		sql.append(" 	t.ZYDM, ");
		sql.append(" 	t.XYDM, ");
		sql.append(" 	t.BJMC, ");
		sql.append(" 	t.ZYMC, ");
		sql.append(" 	t.XYMC, ");		
		sql.append(" 	t.NJ, ");
		sql.append(" 	t.ZGH, ");
		sql.append(" 	t.BZR, ");
		sql.append(" 	t.FS, ");
		sql.append(" 	t.LDPM, ");
		sql.append(" 	t.LDQSS, ");
		sql.append(" 	t.QSLX, ");
		sql.append(" 	case  when t.QSLX ='wmqs' then '文明寢室' ");
		sql.append(" 	when t.QSLX ='bhgqs' then '不合格寢室' end as QSLXMC, ");
		sql.append(" 	t.XN, ");
		sql.append(" 	t.XQ, ");
		sql.append(" 	(select xqmc from XQDZB where xqdm = t.xq) XQMC ");
		sql.append(" FROM XG_GYGL_NTZD_TSSS t ");
		sql.append(" where 1=1 ");
		if(model.getQslx()==null || "".equals(model.getQslx())){
			sql.append(" and qslx = 'wmqs' ");
		}else{
			sql.append(" and qslx = '" + model.getQslx()+"' ");
		}
		sql.append(searchTj);
		if(isDc){
			return dao.getListNotOut(sql.toString(), inputV);
		}
		return getPageList(model, sql.toString(), inputV);
	}
}
