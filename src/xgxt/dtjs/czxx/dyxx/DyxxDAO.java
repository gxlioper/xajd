package xgxt.dtjs.czxx.dyxx;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.dtjs.czxx.CzxxDtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;


public class DyxxDAO extends CzxxDtjsDAO {
	
	public StringBuffer getQueryCondition(DyxxModel model){
		StringBuffer sb = new StringBuffer();
		StringBuffer tmp = new StringBuffer();
		if(!Base.isNull(model.getZd())){
			String[] zdArr = model.getZd().split("-");
			//----------2010/6/9 edit by luojw ---------
			// 通过“-”分割的话，没办法处理类似2009-2010的学年条件
			String[] zdVArr = Base.isNull(model.getZdValue()) ? new String[] {}
					: model.getZdValue().split("!!@@!!");
			// ----------end ---------
			for(int i=0; i<zdArr.length; i++){
				if(!Base.isNull(zdArr[i])){
					tmp = new StringBuffer();
					tmp.append(" and ");
					tmp.append(zdArr[i]);
					tmp.append(" = '");
					tmp.append(zdVArr.length>=i && !Base.isNull(zdVArr[i]) ? zdVArr[i] : "");
					tmp.append("'");
					sb.append(tmp);
				}
			}
		}
		return sb;
	}

	/**
	 * 获得党员信息相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, DyxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq","zzzt" ,"kssj","pxsj"};
		String[] queryLikeList = new String[] { "xh", "xm", "pxmc" };
		MakeQuery myQuery = new MakeQuery();
		
		myQuery.makeQuery(queryList, queryLikeList, model);
		StringBuffer queryStr = getQueryCondition(model);
		
		String query = myQuery.getQueryString()+queryStr;

		String searchTj = SearchService.getSearchTj(model.searchModel);
		String[] inputV = SearchService.getTjInput(model.searchModel);

		query += searchTj;
		String[] inputValue = myQuery.getInputList();

		String[] input = new String[] {};
		int num = 0;
		if (inputValue != null && inputValue.length > 0) {
			num += inputValue.length;
		}
		if (inputV != null && inputV.length > 0) {
			num += inputV.length;
		}

		// 条件非空
		if (num != 0) {
			int n = 0;

			input = new String[num];

			if (inputValue != null && inputValue.length > 0) {
				for (int i = 0; i < inputValue.length; i++) {
					input[n] = inputValue[i];
					n++;
				}
			}

			if (inputV != null && inputV.length > 0) {
				for (int i = 0; i < inputV.length; i++) {
					input[n] = inputV[i];
					n++;
				}
			}
		}
		
		return CommonQueryDAO.commonQuery(tableName, query, input, colList, "", model);
//		return CommonQueryDAO.commonQuery(tableName, query, myQuery
//				.getInputList(), colList, "", model);
	}

	/**
	 * 获得党员相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, String[] zd,
			String[] zdz, String[] colList) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		if (zd != null && zd.length > 0) {
			for (int i = 0; i < zd.length; i++) {
				if (i == 0) {
					sql.append(" where ");
					sql.append(zd[i] + "='" + zdz[i] + "'");
				} else {
					sql.append(" and ");
					sql.append(zd[i] + "='" + zdz[i] + "'");
				}
			}
		}

		return dao.rsToVator(sql.toString(), new String[] {}, colList);
	}
	
	/**
	 * 获得党员信息相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDyxxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 获得党员信息人数
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public String getDyxxRs(String tableName,String pk,String pkValue,String query){
		DAO dao = new DAO();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) rs from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk + " = '" + pkValue + "'");
		if(!Base.isNull(query)){
			sql.append(" and " + query);
		}
		String num = dao.getOneRs(sql.toString(), new String[] {}, "rs");
		return num;
	}
	
	/**
	 * 修改党员类型
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveOtherDylx(String[] tableName, String xh)
			throws Exception {
		boolean flag = true;
		if (tableName != null && tableName.length > 0) {
			String[] sql = new String[tableName.length];
			for (int i = 0; i < tableName.length; i++) {
				sql[i] = "update " + tableName[i] + " set zzzt='no' where xh='" + xh + "'";
			}
			
			flag = saveArrDate(sql);
		}
		return flag;
	}

	/**
	 * 修改党员类型
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveOtherDylx(String[] tableName, String pk, String[] pkValue)
			throws Exception {
		boolean flag = true;
		int n = 0;

		if ((tableName != null && tableName.length > 0) && (pkValue != null && pkValue.length > 0)) {
			String[] sql = new String[tableName.length * pkValue.length];
			for (int i = 0; i < tableName.length; i++) {
				for (int j = 0; j < pkValue.length; j++) {
					sql[n] = "update " + tableName[i] + " set zzzt='no' where "+pk+"='" + pkValue[j] + "'";
					n++;
				}
			}
			flag = saveArrDate(sql);
		}
		return flag;
	}
	
	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 获得党员信息
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDyxx(String[] key,
			String[] colList, String tableName, String pk,String distinct) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		if (colList != null && colList.length > 0) {
			sql.append("select ");
			for (int i = 0; i < colList.length; i++) {
				if (i == 0) {
					if(!Base.isNull(distinct)){
						sql.append("distinct("+colList[i]+")");
					}else{
						sql.append(colList[i]);
					}
				} else {
					sql.append("," + colList[i]);
				}
			}
			sql.append(" from " + tableName);
		}
		
		for (int i = 0; i < key.length; i++) {
			for (int j = i + 1; j < key.length; j++) {
				if (key[i].equalsIgnoreCase(key[j])) {
					key[j] = "";
				}
			}
		}
		
		StringBuffer query = new StringBuffer(" where ");
		for (int i = 0; i < key.length; i++) {
			if (i == 0) {
				query.append(pk + "='" + key[i] + "' ");
			} else {
				if(!Base.isNull( key[i])){
					query.append(" or " + pk + "='" + key[i] + "' ");
				}
			}
		}
		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, colList);
		return list;
	}
	
	/**
	 * @describe 删除所上传文件
	 * @author luo
	 * @throws Exception 
	 */
	public boolean fileDel(String tableName,String scdz,String pk,String pkValue) throws Exception{
		DAO dao = new DAO();
		
		boolean flag = true;
		String sql = "select " + scdz + " scdz from " + tableName + " where " + pk + " = ?";
		String wjlj = dao.getOneRs(sql, new String[]{pkValue}, "scdz");
		if(wjlj!=null){
			File file = new File(wjlj);
	    	if (file.exists()) {
	    		file.delete();
	    		sql = "update " + tableName + " set " + scdz + "='' where " + pk + " = ?";
	    		flag = dao.runUpdate(sql, new String[]{pkValue});
	    	}
		}
		return flag;
	}
	
	
	/**
	 * 获得思想汇报次数
	 * 
	 * @author luo
	 */
	public String getSxhb(String xh, String xn, String xq) {
		DAO dao = DAO.getInstance();
		String sql = "select xn, xq, xh, count(*) num from czxx_sxhbb"
				+ " where xh = ? and xn = ? and xq = ?  group by xn, xq, xh";
		String num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
		return num;
	}
	
	/**
	 * 获得培训成绩列表
	 * 
	 * @author luo
	 */
	public List<HashMap<String,String>> getPxcjList(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select pxsj, dkcj pxcj from czxx_dkpxmdb where pxmdxh = ? order by pxsj";
		List<HashMap<String,String>> list= dao.getList(sql, new String[]{xh},  new String[]{"pxsj","pxcj"});
		return list;
	}
	
	/**
	 * 判断是否优秀团员
	 * 
	 * @author luojw
	 */
	public boolean isYxty(String xh) {

		DAO dao = DAO.getInstance();
		boolean flag = false;

		String sql = "select count(*) num from yxtyxxb where xh = ? and xxsh = '通过'";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取入党积极份子基本信息和入党申请时间
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectRdsqjbxx(String pk, String pkValue){
		DAO dao = DAO.getInstance();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,(select count(*) from view_xsjbxx b where a.bjdm=b.bjdm)bjrs,(select csrq from view_xsxxb b where a.xh=b.xh)csrq,(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,(select djsqsj from rdsqb b where a.xn=b.xn and a.xq=b.xq and a.xh=b.xh )sqrdsj from view_rdjjfzxx a where " + pk + "=?";
		return dao.getMap(sql, new String[]{pkValue}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","bjrs","csrq","rxrq","sqrdsj"});
	}
	
	public boolean gxzJbxx() throws Exception{
		DAO dao = DAO.getInstance();
		String sql="update xsxxb a set a.zzmm='01',a.jrzzmmrq=(select b.rdsj from dyxxb b where b.xh=a.xh) "+
					"where exists (select xh,count(1) from dyxxb b where a.xh=b.xh group by xh having count(1)=1)";
		return dao.runUpdate(sql, new String[]{});
	}
}
