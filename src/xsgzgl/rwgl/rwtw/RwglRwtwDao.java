package xsgzgl.rwgl.rwtw;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.rwgl.mbxx.RwglMbxxForm;

public class RwglRwtwDao extends DAO{

	
	/**
	 * 民兵信息查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> rwdjCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "xh", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "rwsj", "rwdmc" , "rwfsmc"};
		if("10351".equals(Base.xxdm)){
			colList = new String[] { "xh", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "rwsj", "rwdwdmc" , "rwfsmc"};
		}
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		SearchService searchService=new SearchService();
		searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.*,case when length(rwd)>10 then substr(rwd,0,10)||'...' else rwd end as rwdmc from VIEW_XG_RWGL_RWXXB a where a.twsj is null ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}
	
	
	/**
	 * 在校学生查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> zxxsCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "xh", "xh", "xb", "xm", "nj", "xymc", "zymc", "bjmc" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		SearchService searchService=new SearchService();
		searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from view_xsjbxx a where 1 = 1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}

	
	/**
	 * 入伍登记验证
	 * @param model
	 * @return
	 */
	public String rwdjYz(RwglRwtwForm model) {
		String xh = model.getXh();
		String[] inputValue = new String[]{xh};
		String sql = "select count(1) num from XG_RWGL_RWXXB where xh = ?";
		return getOneRs(sql, inputValue, "num");
	}
	
	
	/**
	 * 民兵信息增加保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean rwdjBc(RwglRwtwForm model) throws Exception {
		String doType = model.getDoType();
		String xh = model.getXh();
		String rwsj = model.getRwsj();
		String rwd = model.getRwd();
		String rwdwd = model.getRwdwd();
		String rwxn = model.getRwxn();
		String rwfs = model.getRwfs();
		String sg = model.getSg();
		String tz = model.getTz();
		String zysl = model.getZysl();
		String yysl = model.getYysl();
		//update
		String[] input = new String[]{rwsj,rwd,rwxn,rwfs,sg,tz,zysl,yysl,rwdwd,xh};
		String sql = "update XG_RWGL_RWXXB set rwsj=?,rwd=?,rwxn=?,rwfs=?,sg=?,tz=?,zysl=?,yysl=?,rwdwd=? where xh = ?";
		
		if("add".equalsIgnoreCase(doType)){
			sql = "insert into XG_RWGL_RWXXB(rwsj,rwd,rwxn,rwfs,sg,tz,zysl,yysl,rwdwd,xh)values(?,?,?,?,?,?,?,?,?,?)";
		}
		return runUpdate(sql, input);
	}


	/**
	 * 民兵信息删除
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean rwdjSc(List<String[]> params) throws SQLException {
		String sql = "delete from XG_RWGL_RWXXB where xh = ?";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}


	/**
	 * 获得一条民兵信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getrwdj(RwglMbxxForm model) {
		String sql="select * from view_xg_rwgl_mbxxb where sfzh = ? ";
		String[] inputValue = new String[]{model.getPkValue()};
		return getMapNotOut(sql, inputValue);
	}


	/**
	 * 获得学生详细信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> xsxxCk(RwglRwtwForm model) {
		String xh = model.getXh();
		String sql = "select a.xh,a.xm,a.xb,a.csrq,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.sfzh,a.xz,a.mz,a.mzmc,a.zzmm,a.zzmmmc,b.jtszd,b.sjhm,a.jtdh from view_xsxxb a left join xsfzxxb b on a.xh=b.xh where a.xh = ?";
		String[] inputValue = new String[]{xh};
		return getMapNotOut(sql, inputValue);
	}


	/**
	 * 获得入伍信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getRwxx(RwglRwtwForm model) {
		String xh = model.getPkValue();
		String sql = "select * from VIEW_XG_RWGL_RWXXB where xh = ?";
		String[] inputValue = new String[]{xh};
		return getMapNotOut(sql, inputValue);
	}


	/**
	 * 退伍登记查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> twdjCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "xh", "xh", "xm", "nj", "xymc", "bjmc", "rwsj",  "rwfsmc","twsj"  };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		SearchService searchService=new SearchService();
		searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from VIEW_XG_RWGL_RWXXB a where twsj is not null");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}


	/**
	 * 入伍学生查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> rwxsCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		User user = model.getUser();
		String[] colList = new String[] { "xh", "xh", "xb", "xm", "nj", "xymc", "zymc", "bjmc","rwsj" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		SearchService searchService=new SearchService();
		searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from (select b.*,a.rwsj from VIEW_XG_RWGL_RWXXB a left join view_xsbfxx b on a.xh = b.xh where a.twsj is null) a where 1=1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}


	/**
	 * 退伍登记验证
	 * @param model
	 * @return
	 */
	public String twdjYz(RwglRwtwForm model) {
		String xh = model.getXh();
		String[] inputValue = new String[]{xh};
		String sql = "select count(1) num from XG_RWGL_RWXXB where xh = ? and twsj is not null";
		return getOneRs(sql, inputValue, "num");
	}


	/**
	 * 退伍登记保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean twdjBc(RwglRwtwForm model) throws Exception {
		String xh = model.getXh();
		String rwzh = model.getRwzh();
		String twsj = model.getTwsj();
		String yzy = model.getYzy();
		String ybj = model.getYbj();
		String hjgx = model.getHjgx();
		String hkszd = model.getHkszd();
		String bz = model.getBz();
		String[] input = new String[]{rwzh,twsj,yzy,ybj,hjgx,hkszd,bz,xh};
		String sql = "update XG_RWGL_RWXXB set rwzh=?,twsj=?,yzy=?,ybj=?,hjgx=?,hkszd=?,bz=? where xh = ?";
		return runUpdate(sql, input);
	}


	/**
	 * 退伍登记删除
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean twdjSc(List<String[]> params) throws SQLException {
		String sql = "update XG_RWGL_RWXXB set rwzh='',twsj='',yzy='',ybj='',hjgx='',hkszd='',bz='' where xh = ?";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}


	/**
	 * 入伍登记管理自定义导出
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> rwdjExportCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "xh", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "rwsj", "rwdmc","rwd" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		SearchService searchService=new SearchService();
		searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from VIEW_XG_RWGL_RWXXB a where a.twsj is null ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}

	/**
	 * 退伍登记管理自定义导出
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> twdjExportCx(RwglRwtwForm model,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "xh", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "rwsj", "twsj" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		SearchService searchService=new SearchService();
		searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from VIEW_XG_RWGL_RWXXB a where twsj is not null");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}


	/**
	 * 
	 * @描述:查询入伍方式列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-3 上午11:44:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> rwfsList() throws Exception{
		String sql = "select mc , dm from dmk_rwfsb order by mc";
		return getListNotOut(sql, new String[]{});
	}
	/**
	 * 查询入伍途径列表
	 */
	public List<HashMap<String , String>> rwtjList() throws Exception{
		String sql = "select mc , dm from dmk_rwtjb order by mc";
		return getListNotOut(sql, new String[]{});
	}


}
