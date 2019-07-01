package xgxt.dtjs.czxx.tyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.dtjs.czxx.CzxxDtjsDAO;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;


public class TyxxDAO extends CzxxDtjsDAO {
	
	/**
	 * 团员信息查询
	 * @param String tableName
	 * @param TyxxModel model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> selectTyxxList(String tableName,TyxxModel model,String[] colList) throws Exception{
		String[] queryList = new String[] { "xb", "xydm", "zydm", "bjdm","nj","sfty","nd" };
		String[] queryLikeList = new String[] { "xh", "xm"};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString() + "";
		return CommonQueryDAO.commonQuery(tableName, query, myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * 团员信息查询导出
	 * @param String tableName
	 * @param TyxxModel model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> selectTyxxForExp(String tableName,TyxxModel model,String[] colList) throws Exception{
		String[] queryList = new String[] { "xb", "xydm", "zydm", "bjdm","nj","sfty" };
		String[] queryLikeList = new String[] { "xh", "xm"};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString() + "";
		return CommonQueryDAO.commonQueryNotFy(tableName, query, myQuery.getInputList(), colList, "");		
	}
	
	/**
	 * 非团员培训信息查询
	 * @param String tableName
	 * @param CzxxDtjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> selectFtypxxxList(String tableName,CzxxDtjsForm model,String[] colList) throws Exception{
		String[] queryList = new String[] { "xb", "xydm", "zydm", "bjdm","nj","nd","xn","xq","bzrsh","xysh","xxsh"};
		String[] queryLikeList = new String[] { "xh", "xm"};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String queryStr = !Base.isNull(model.getIsBzr()) && "true".equalsIgnoreCase(model.getIsBzr())?" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + model.getUserName() + "')":"";
		String query = myQuery.getQueryString() + queryStr;
		return CommonQueryDAO.commonQuery(tableName, query, myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * 根据主键查询非团员培训信息 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> selectFtypxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 查询非团员培训项目代码表信息
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectFtypxxmdmb(){
		DAO dao = DAO.getInstance();		
		String sql = "select distinct pxxmdm dm ,pxxmmc mc from pxxmdmb order by pxxmdm";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 查询团员培训项目代码表信息
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectTypxxmdmb(){
		DAO dao = DAO.getInstance();		
		String sql = "select distinct pxxmdm dm ,pxxmmc mc from typxxmdmb order by pxxmdm";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 检测学生是否是团员
	 * @param String[] xhArr
	 * @return String
	 * */
	public String checkSfsty(String[] pkArr){
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<pkArr.length; i++){
			HashMap<String, String> map = dao.getMap("select xh,sfty from view_czxx_tyxx a where exists(select 1 from view_yxtyxxb b where a.xh=b.xh and b.xh||b.xn||b.nd||b.xq=?)", new String[]{pkArr[i]}, new String[]{"xh","sfty"});
			if(!Base.isNull(map.get("sfty"))&& "否".equalsIgnoreCase(map.get("sfty"))){
				sb.append("学号为'");
				sb.append(map.get("xh"));
				sb.append("'的同学不是团员！\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 删除团员注册信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delTyzc(CzxxDtjsForm myForm) throws Exception {

		String[] pkValue = myForm.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		for (int i = 0; i < pkValue.length; i++) {

			sql[i] = " delete from xg_dtjs_tyzcb where xh||'!!@@!!'||xn='"
					+ pkValue[i] + "'  ";
		}

		return dao.saveArrDate(sql);
	}
	
	/**
	 * 团员注册信息列表(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getTyzcList(CzxxDtjsForm myForm)
			throws Exception {

		String[] colList = { "pkValue", "xn", "xh", "xm","nj", "xymc", "zymc",
				"bjmc","zcsj"};
		String[] col = { "xn", "nj", "xydm", "zydm", "bjdm" };
		String[] colLike = { "xh", "xm" };
		StringBuilder sql = new StringBuilder();
		MakeQuery mQuery = new MakeQuery();

		mQuery.makeQuery(col, colLike, myForm);
		sql.append("  select rownum r,xh||'!!@@!!'||xn pkValue,xh,xm,xn,zcsj, ");
		sql.append("  xydm,zydm,bjdm,xymc,zymc,bjmc,nj from xg_view_dtjs_tyzc a ");
		
		User user=myForm.getUser();
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		String userType=user.getUserType();
		StringBuilder userQuery=new StringBuilder();
		
		if(myForm.isFdyQx() && myForm.isBzrQx()){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if(myForm.isFdyQx()){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if(myForm.isBzrQx()){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("xy".equalsIgnoreCase(userType)){
			userQuery.append(" and xydm='"+userDep+"' ");
		}

		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString()+userQuery, mQuery.getInputList(), colList, myForm);
	}
	
	/**
	 * 保存团员注册(2011.11.9 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveTyzc(CzxxDtjsForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String xh = myForm.getXh();
		String xn = myForm.getXn();
		String zcsj=myForm.getZcsj();
		String bz = myForm.getBz();

		sql.append(" insert into xg_dtjs_tyzcb(xh,xn,zcsj,bz)  ");
		sql.append(" values(?,?,?,?)");
		String[] inputV = { xh, xn, zcsj,bz };
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 修改生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateTyzc(CzxxDtjsForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String bz=myForm.getBz();
		String pkValue=myForm.getPkV()[0];
		
		sql.append(" update xg_dtjs_tyzcb  ");
		sql.append(" set bz=? ");
		sql.append(" where xh||'!!@@!!'||xn= ? ");
		
		String[] inputV = { bz, pkValue};
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	public HashMap<String, String> getOneTyzc(CzxxDtjsForm myForm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String pkValue = myForm.getPkV()[0];
		sql.append(" select xh||'!!@@!!'||xn pkValue,xh,xm,xn,xb,zcsj,nj,xymc,zymc,bjmc,bz from xg_view_dtjs_tyzc");
		sql.append(" where xh||'!!@@!!'||xn= ? ");
		String colList[] = { "pkValue","xh","xm", "xn", "xb", "zcsj","bz", "xymc", "nj", "zymc", "bjmc" };
		return dao.getMap(sql.toString(), new String[] { pkValue }, colList);
	}
	
}
