package xgxt.jxgl.jxglxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.jxgl.JxglDAO;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;

public class JxglwhService {
	JxglDAO dao = new JxglDAO();
	public ArrayList<String[]> getJxxsTjList(JxglwhModel myModel) {
		// 军训新生体检
			String tableName     = "view_jxxsttjg";
			String xydm         = DealString.toGBK(myModel.getXydm());
			String xm           = DealString.toGBK(myModel.getXm());
			String zydm         = DealString.toGBK(myModel.getZydm());
			String bjdm         = DealString.toGBK(myModel.getBjdm());
			String nj         = DealString.toGBK(myModel.getNj());
			String xh         = DealString.toGBK(myModel.getXh());
			String xq         = DealString.toGBK(myModel.getXq());
			String xn         = DealString.toGBK(myModel.getXn());
			String nd         = DealString.toGBK(myModel.getNd());
			StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"",xh,xm,nj,nd,xq,xn));
			String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","sfhg"}; 
			ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
			return rs;
	}
	
	public List getJxxsTjTopTr() {
			// 军训新生体检表头
		    DAO dao = DAO.getInstance();
			String tableName     = "view_jxxsttjg";
			String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","sfhg"}; 
			String[] colListCN     = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//表头 
			
			return topTr;
	}

	public HashMap<String, String> getJxglxsTjOne(String pk, String xh) {
			// 得到单个新生体检详细信息
			String tableName    = "view_jxxsttjg";
			String [] colList = new String []{"pk","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","nd","xn","xq","sfhg","tjqk","fjqk","lxdh"};
			HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
			colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"};
			if(!xh.equalsIgnoreCase("")) {
				rs = dao.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, rs, "");
			}
			if(pk.equalsIgnoreCase("")) {
				rs = inputXnNjXq(rs);
			}
			return rs;
	}
	
	public boolean updataXstj(JxglwhModel myModel,String pk, HttpServletRequest request) 
			throws Exception {
		//    保存单个新生体检
		String tableName       = "jxxstjgb";
		String pkComment       = "xh||xn||xq";
		String [] colList      = new String []{"xh","xn","xq","nd","sfhg","tjqk","lxdh","fjqk"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean deleteXstjOne(String pk, HttpServletRequest request) 
	throws Exception {
		//    删除学生体检
		String tableName       = "jxxstjgb";
		String pkComment       = "xh || xn || xq";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
}
	
	public HashMap<String, String> inputXnNjXq(HashMap<String, String> rs){
		//    判断rs里学年，年度，学期是否有值，无则放入当前学年，年度，学期
			String xn           = Base.currXn;
			String xq           = Base.currXq;
			String nd           = Base.currNd;
			if(rs.get("xn").equalsIgnoreCase("")){
				rs.put("xn", xn);
			}
			
			if(rs.get("nd").equalsIgnoreCase("")){
				rs.put("nd", nd);
			}
			
			if(rs.get("xq").equalsIgnoreCase("")){
				rs.put("xq", xq);
			}
			return rs;
		}
	
	/**
	 * 判断军训名单数据是否取共享数据
	 * @return boolean
	 * */
	public String checkJxmdsfqgx(){
		return dao.checkJxmdsfqgx();
	}
	
	/**
	 * 保存军训其它参数设置信息
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveDrillConfInfo(JxglwhForm model, HttpServletRequest request) throws Exception{
		String jxmdsfqgx = model.getJxmdsfqgx();
		
		return StandardOperation.update("jxglxtszb", "update jxglxtszb set JXMDSFQGX = '" + jxmdsfqgx + "' where 1=1", request);
	}
	
	/**
	 * 获取军训名单的表名
	 * @return String
	 * */
	public String getJxmdTableName(){
		return dao.getJxmdTableName();
	}
	
	/**
	 * 教师工号信息查询表头
	 * @return List
	 * */
	public List getJsghxxTopTr(){
		String[] col = {"zgh", "xm", "xb", "bmmc", "xl", "sfmc"};
        String[] colCN = dao.getColumnNameCN(col, "view_fdyxx");			
		return dao.arrayToList(col, colCN);
	}
	
	/**
	 * 教师工号信息查询
	 * */
	public List searchJsghInfo(JxglwhForm model){
		return dao.searchJsghInfo(model);
	}
	
	/**
	 * 根据工号查询教师信息
	 * @param jsid
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJsxx(String jsid){
		return dao.getJsxx(jsid);
	}
	
	/**
	 * 获取连队列表
	 * @param nd
	 * @param xb
	 * @return List
	 * */
	public List getLdList(String nd , String xb){
		return dao.getLdList(nd, xb);
	}
	
	/**
	 * 获取军训团队获奖信息表头
	 * @return List
	 * */
	public List getJxtdhjTopTr(){
		String[] col = {"xn", "nd", "xq", "ldmc", "zdy", "jxmc", "hjsj"};
        String[] colCN = dao.getColumnNameCN(col, "view_jxtdhj");			
		return dao.arrayToList(col, colCN);
	}
	
	/**
	 * 查询军训团队获奖信息
	 * @param model
	 * @return List
	 * */
	public List getJxtdhjInfo(JxglwhForm model){		
		return dao.searchJxtdhjInfo(model);
	}
	
	/**
	 * 获取军训奖项列表 
	 * @return List
	 * */
	public List getJxtdjxList(){
		return dao.selectJxtdjxList();
	}
	
	/**
	 * 根据主键查询军训团队获奖信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxtdhjByPk(String pkValue){
		return dao.getJxtdhjByPk(pkValue);
	}
	
	/**
	 * 保存军训团队获奖信息
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveJxtdhj(JxglwhForm model,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String doType = model.getDoType();
		String tableName = "jxtdhjb";
		String xn = DealString.toGBK(model.getXn());
		String nd = DealString.toGBK(model.getNd());
		String xq = DealString.toGBK(model.getXq());
		String lddm = DealString.toGBK(model.getLddm());
		String jxdm = DealString.toGBK(model.getJxdm());
		String hjsj = DealString.toGBK(model.getHjsj());
		
		String[] columns = {"xn", "nd", "xq", "lddm", "jxdm", "hjsj"};
		String[] values = {xn, nd ,xq, lddm, jxdm, hjsj};
		String primaryKey = "xn||nd||xq||lddm||jxdm";
		String pkValue = xn+nd+xq+lddm+jxdm;
		
		if("add".equalsIgnoreCase(doType)){//信息增加
			if(dao.checkExists(tableName, "xn||nd||xq||lddm||jxdm", xn+nd+xq+lddm+jxdm)){
				request.setAttribute("msg", "记录已经存在!");
			}else{
				flag = StandardOperation.insert(tableName,columns, values, request);
			}
		}else if("modi".equalsIgnoreCase(doType)){//信息修改 
			flag = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}
		
		return flag;
	}
	
	/**
	 * 军训团队获奖信息删除
	 * @throws Exception 
	 * */
	public boolean delJxtdhj(String pkString , HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pkString.split("!!");
		String tableName = "jxtdhjb";
		String primaryKey = "xn||nd||xq||lddm||jxdm";
		
		for(int i=0 ;i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, primaryKey, pkValues[i], request);
			if(!flag){
				request.setAttribute("msg", "第" + (i+1) + "条记录删除失败,操作终止！");
				break;
			}
		}
		return flag;		
	}
	
	/**
	 * 获取军训学生参训干部信息表头
	 * @return List
	 * */
	public List getJxxscxgbTopTr(){
		String[] col = {"xh", "xm", "xymc", "bjmc", "jxnd", "fzldmc", "zw"};
        String[] colCN = dao.getColumnNameCN(col, "view_jxxscxgb");			
		return dao.arrayToList(col, colCN);
	}
	
	/**
	 *获取军训学生参训干部管理
	 * @param model
	 * @return List
	 * */
	public List getJxxscxgbInfo(JxglwhForm model){
		return dao.getJxxscxgbInfo(model);
	}
	
	/**
	 * 根据主键查询军训学生参训干部信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxcxgbByPk(String pkValue){
		return dao.getJxxscxgb(pkValue);		
	}
	
	/**
	 * 保存军训学生参训干部信息
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveJxcxgbxx(JxglwhForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String doType = model.getDoType();
		String tableName = "jxxscxgbb";
		String pk = "xh||jxnd||fzlddm";
		
		String xh = DealString.toGBK(model.getXh());
		String jxnd = DealString.toGBK(model.getJxnd());
		String fzlddm = DealString.toGBK(model.getFzlddm());
		String zzdh = DealString.toGBK(model.getZzdh());
		String sjhm = DealString.toGBK(model.getSjhm());
		String zw = DealString.toGBK(model.getZw());
		String bz = DealString.toGBK(model.getBz());
		
		String[] columns = {"xh", "jxnd", "zzdh", "sjhm", "fzlddm", "zw", "bz"};
		String[] values = {xh, jxnd, zzdh, sjhm, fzlddm, zw, bz};
		
		if("add".equalsIgnoreCase(doType)){
			if(dao.checkExists(tableName, pk, xh+jxnd+fzlddm)){
				request.setAttribute("msg", "记录已经存在！");
			}else{
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
		}else if("modi".equalsIgnoreCase(doType)){
			flag = StandardOperation.update(tableName, columns, values, pk, xh+jxnd+fzlddm, request);
		}
		return flag;
	}
	
	/**
	 * 删除军训学生参训干部信息
	 * @param pkString
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean deleteJxcxgbxx(String pkString, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pkString.split("!!");
		String tableName = "jxxscxgbb";
		String primaryKey = "xh||jxnd||fzlddm";
		
		for(int i=0 ;i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, primaryKey, pkValues[i], request);
			if(!flag){
				request.setAttribute("msg", "第" + (i+1) + "条记录删除失败,操作终止！");
				break;
			}
		}
		return flag;	
	}
	
	/**
	 * 保存军训成绩比例设置信息
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * @author luo
	 */
	public boolean saveCjbl(String jxll, HttpServletRequest request)
			throws Exception {

		int jxsj = 100 - Integer.parseInt(jxll);
		String sql = "select count(*) count from zjcm_cjbl";
		String count = dao.getOneRs(sql, new String[] {}, "count");
		boolean flag = false;
		if ("0".equals(count)) {
			flag = StandardOperation.insert("zjcm_cjbl", new String[] { "jxll",
					"jxsj" }, new String[] { jxll, String.valueOf(jxsj) },
					request);
		} else {
			flag = StandardOperation.update("zjcm_cjbl",
					"update zjcm_cjbl set jxll = '" + jxll + "',jxsj = '"
							+ jxsj + "' where 1=1", request);
		}
		return flag;

	}
}
