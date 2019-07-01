package xgxt.rcsw.bxlp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 保险、理赔DAO</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-03</p>
 */
public class BxlpDAO extends CommonQueryDAO{
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 获取当前系统时间
	 * @return
	 */
	public String getNow() {
		String sql = "select to_char(sysdate,'yyyymmdd')  now from dual";
		String now = dao.getOneRs(sql, new String[] {}, "now");
		
		return now;
	}
	
	
	
	
	/**
	 * 修改含自定义数据 
	 * @param realTable
	 * @param model
	 * @param pk
	 * @param pkValue
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public boolean updataZdyDdData(String tableName,String pkValue,
			HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql = "delete from ty_bdsz_bcnr where tabname = ? and zbid = ?";
		boolean del = dao.runUpdate(sql, new String[] { tableName, pkValue });
		if (del) {
			sql = "select zdid from ty_bdsz where  tabname = ?";
			String[] zdyzds = dao.getArray(sql, new String[] {tableName },
					"zdid");
			String[] inputList = new String[zdyzds.length];
			// 实际要修改或插入的sql数
			int j = 0;
			for (int i = 0; i < zdyzds.length; i++) {
				inputList[i] = request.getParameter(zdyzds[i]);
				if (inputList[i] != null) {
					j++;
				}
			}
			String[] sqlmap = new String[j];
			int k = 0;
			for (int i = 0; i < inputList.length; i++) {
				if (inputList[i] != null) {
					sqlmap[k] = "insert into ty_bdsz_bcnr values ('"
							+ zdyzds[i] + "','" + tableName + "','" + pkValue
							+ "','" + inputList[i] + "')";
					k++;
				}
			}
			dao.runBatch(sqlmap);
		}
		return del;
	}
	
	
	/**
	 * 查询含自定义字段数据
	 * @param tableName
	 * @param myModel
	 * @param colList
	 * @param zdyCol
	 * @param realTable
	 * @param pkKey
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getZdyQueryList(String tableName,
			Object myModel, String[] colList, String[] zdyCol,String realTable,
			String[] pkKey) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String[] queryList = new String[]{"nj","xydm","zydm","bjdm","xb","cxlx","xn","nd","xq","sfby"};
		String[] queryLikeList = new String[]{"xh","xm","sfzh"};
		String[] inputList = new String[] {};
		String query = "";
		if (queryList != null) {
			MakeQuery makeQuery = new MakeQuery();
			makeQuery.makeQuery(queryList, queryLikeList, myModel);
			inputList = makeQuery.getInputList();
			query = makeQuery.getQueryString();
		}
		int size = colList.length - 1;
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for (int i = 0; i < (size); i++) {
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
		}
		sqlBuffer.append(" rownum r,a.");
		sqlBuffer.append(colList[size]);

		for (int i = 0; i < zdyCol.length; i++) {
			sqlBuffer
					.append(",(select bcnr from ty_bdsz_bcnr where tabname = '");
			sqlBuffer.append(realTable);
			sqlBuffer.append("' and zdid = '");
			sqlBuffer.append(zdyCol[i]);
			sqlBuffer.append("' and zbid = ");
			for (int j = 0; j < pkKey.length; j++) {
				sqlBuffer.append("a.");
				sqlBuffer.append(pkKey[j]);
				if (j != pkKey.length - 1) {
					sqlBuffer.append("||");
				}
			}
			sqlBuffer.append(") ");
			sqlBuffer.append(zdyCol[i]);
		}
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" a ");
		String[] zcolList = new String[colList.length + zdyCol.length];
		for (int i = 0; i < colList.length; i++) {
			zcolList[i] = colList[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolList[colList.length + i] = zdyCol[i];
		}
		return CommonQueryDAO.commonQuery(sqlBuffer.toString(), query,
				inputList, zcolList, myModel);
	}
	
	
	/**
	 * 获取单条信息
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @param colList
	 * @return
	 */
	public HashMap<String, String> getInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	
	
	/**
	 * 下拉列表
	 * @param flg
	 * @return
	 */
	public List<HashMap<String,String>> getSelectList(String flg){
		
		String[] dm = null;
		List<HashMap<String,String>> selectList = null;
		
		
		if ("isNot".equals(flg)) {
			dm = new String[] { "是", "否" };
		} else if ("bxgs".equals(flg)){
			selectList = dao.getWhList("bxgsdmb", "bxgsdm", "bxgsmc", "", "", "");
		} else if ("bxxz".equals(flg)){
			selectList = dao.getWhList("bxxzb", "bxxzdm", "bxxzmc", "", "", "");
		} else {
			dm = new String[] {};
		}
		
		if (null != dm) {
			selectList = dao.arrayToList(dm, dm);
		}
		
		return selectList;
	}
	
	/**
	 * 获取保险审核
	 * @param tableName
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getBxshList(BxlpForm model,User user,
			String[]colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		StringBuilder sql=new StringBuilder();
		//查询条件
		String[] queryList = new String[] {"xb","nj","xydm","zydm","bjdm","nd",
				"fdysh","xysh","xxsh","xn","sfby"};
		//模糊查询
		String[] queryLikeList = new String[] { "xh", "xm","sfzh"};
		String userType=user.getUserType();
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		String isFdy=user.getFdyQx();
		String isBzr=user.getBzrQx();
		
		
		sql.append(" select rownum r,pk pkValue,a.*, ");
		if("true".equalsIgnoreCase(isFdy)){
			sql.append(" case when xysh='通过' then 'disabled' else '' end dis  ");
		}else if("xy".equalsIgnoreCase(userType)){
			sql.append(" case when xxsh='通过' then 'disabled' else '' end dis ");
		}else if("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			sql.append(" '' dis  ");
			
		}
		sql.append(" from view_rcsw_bxwh a ");
		
		
		
		MakeQuery myQuery = new MakeQuery();
		StringBuilder query = new StringBuilder();
		myQuery.makeQuery(queryList, queryLikeList, model);
		query.append(myQuery.getQueryString());
		
		List<String>queryV=new ArrayList<String>();
		if("true".equalsIgnoreCase(isFdy) || "true".equalsIgnoreCase(isBzr)){
			
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh=? union ");
			query.append(" select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh=? ) ");
			queryV.add(userName);
			queryV.add(userName);
		}else if("true".equalsIgnoreCase(isFdy)){
				
			query.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh=? )");
			queryV.add(userName);
		}else if("true".equalsIgnoreCase(isBzr)){
				
			query.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh=? )");
			queryV.add(userName);
		}else if("xy".equalsIgnoreCase(userType)){
			
			query.append(" and xydm=? ");
			query.append(" and fdysh='通过' ");
			queryV.add(userDep);
		}else if("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			
			query.append(" and xysh='通过' ");
		}
		
		
		DAO dao=DAO.getInstance();
		return CommonQueryDAO.commonQuery(sql.toString(),query.toString(),dao.unionArray(myQuery.getInputList(),
				queryV.toArray(new String[]{})), colList, model);

	}
	
	public boolean savePlsh(BxlpForm myForm,User user) throws Exception{
		
		CommDAO dao=new CommDAO();
		String[]pkValue=myForm.getCheckVal();
		String[]sql=new String[pkValue.length];
		String shzd="";
		String shyjzd="";
		String shr="";
		String shsj="";
		String isFdy=user.getFdyQx();
		String userType=user.getUserType();
		String userName=user.getUserName();
		
		if("true".equalsIgnoreCase(isFdy)){
			
			shzd="fdysh";
			shyjzd="fdyshyj";
			shr="fdyshr";
			shsj="fdyshsj";
			if(!Base.isNull(myForm.getFdyshyj())){
				myForm.setShyj(myForm.getFdyshyj());
			}
		}else if("xy".equalsIgnoreCase(userType)){
			shzd="xysh";
			shyjzd="xyshyj";
			shr="xyshr";
			shsj="xyshsj";
			if(!Base.isNull(myForm.getXyshyj())){
				myForm.setShyj(myForm.getXyshyj());
			}
		}else if("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			shzd="xxsh";
			shyjzd="xxshyj";
			shr="xxshr";
			shsj="xxshsj";
			if(!Base.isNull(myForm.getXxshyj())){
				myForm.setShyj(myForm.getXxshyj());
			}
		}
		
		for(int i=0;i<pkValue.length;i++){
			sql[i]=" update rcsw_bxwh set  ";
			sql[i]+=shzd+"='"+myForm.getShzt()+"', ";
			if(!Base.isNull(myForm.getShyj())){
				sql[i]+=shyjzd+"='"+myForm.getShyj()+"', ";
			}
			sql[i]+=shr+"='"+userName+"', ";
			sql[i]+=shsj+"=to_char(sysdate,'yyyyMMdd') ";
			sql[i]+=" where xh||rq='"+pkValue[i]+"' ";
		}
		
		return dao.saveArrDate(sql);
	}
}
