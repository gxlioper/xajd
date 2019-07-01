package xgxt.xljk.zxzx.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import xgxt.DAO.DBPool;
import xgxt.DAO.TranscationManager;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CustomException;
import xgxt.utils.New_Random_ID;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.zxzx.form.xljk_xssqyy_Form;
import xgxt.xljk.zxzx.form.xljk_zxszy_Form;
import xgxt.xsxx.cslg.xsjl.XsxxCslgXsjlForm;
import xgxt.DAO.DAO;

public class Xljk_DAO {
	// 连接池
	private final DataSource db = DBPool.getPool();

	private final DAO dao = DAO.getInstance();
	protected TranscationManager transcationManager = new TranscationManager();

	private static Xljk_DAO xjlk_Dao = null;

	public static Xljk_DAO getXLJK_DAO() {
		if (xjlk_Dao == null) {
			xjlk_Dao = new Xljk_DAO();
		}
		return xjlk_Dao;
	}

	public Xljk_DAO() {
	}

	/**
	 * batch insert data
	 * 
	 * @param columns
	 * @param myform
	 * @return
	 * @throws Exception
	 */
	public boolean batchInsert(String[] columns, xljk_zxszy_Form myform,
			String doType) throws Exception {

		// Date now = new Date();
		XLJK_ZXZX_Util util = XLJK_ZXZX_Util.getXLJK_ZXZX_Util();
		String[] ddVector = util.get_Vector2("zxszy_dd"); // 地段集合
		String[] sjdVector = util.get_Vector2("zxszy_sjd"); // 时间段集合
		int ddLen = ddVector.length;
		int sjdLen = sjdVector.length;
		int totalNum = ddLen * sjdLen;
		int count = 0;
		int BATCH_NUM = 50; // 批量处理的数据量大小
		int cycle_Num = totalNum / BATCH_NUM;
		int cycle_Num_Count = 0;
		// int pl = 1;
		String sql = "";
		String xn_id = ""; // 虚拟主键
		StringBuffer sColumn = new StringBuffer();
		StringBuffer sqlBf = new StringBuffer();
		New_Random_ID newid = new New_Random_ID();

		String rq = myform.getRq(); // 得到日期
		String tableName = myform.getTableName();
		// 处理columns
		for (int i = 0; i < columns.length; i++) {
			sColumn.append(columns[i] + ((i == columns.length - 1) ? "" : ","));
		}
		sqlBf.append("insert into ");
		sqlBf.append(tableName);
		sqlBf.append("(");
		sqlBf.append(sColumn.toString());
		sqlBf.append(")");
		sqlBf.append(" values(");

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = transcationManager.getConnection();;
			stat = conn.createStatement();
			conn.setAutoCommit(false);
			if (!StringUtils.isNull(doType)
					&& doType.equalsIgnoreCase("Add_mt")) {
				for (int j = 0; j < ddLen; j++) {
					for (int k = 0; k < sjdLen; k++) {
						xn_id = newid.new_xnid(tableName);
						String[] values = { xn_id, rq, "0", sjdVector[k],
								ddVector[j] };
						sql = sqlBf.toString() + this.DealArrayToStr(values)
								+ ")"; // 生成SQl语句
						stat.addBatch(sql);
						count++;
						if (cycle_Num == 0) { // 1
							if (totalNum == count)
								stat.executeBatch();
						} else {
							if (count == BATCH_NUM) { // 
								stat.executeBatch();
								stat.clearBatch();
								count = 0;
								cycle_Num_Count++;
							} else if (cycle_Num_Count == cycle_Num
									&& count == count % BATCH_NUM) { // last
								stat.executeBatch();
								stat.clearBatch();
							}
						}
					}
				}
			} else if (!StringUtils.isNull(doType)
					&& doType.equalsIgnoreCase("Add_pl")) {
				dealDate datedeal = new dealDate();
				String fromrq = myform.getFromrq();
				String monthday = myform.getMonthday();
				String torq = myform.getTorq();
//				String xxdm = StandardOperation.getXxdm();
				String[] dates;
//				if(Globals.XXDM_ZGKYDX.equals(xxdm)){
					dates = datedeal.getDateBounds3(fromrq, torq,monthday);
//				}else{
//					dates = datedeal.getDateBounds2(fromrq, torq);
//				}
				int datelen = dates.length;
				totalNum = datelen * totalNum;
				cycle_Num = totalNum / BATCH_NUM;
				for (int i = 0; i < datelen; i++) {
					for (int j = 0; j < ddLen; j++) {
						for (int k = 0; k < sjdLen; k++) {
							xn_id = newid.new_xnid("xljk_zxszyb");
							String[] values = { xn_id, dates[i], "0",
									sjdVector[k], ddVector[j] };
							sql = sqlBf.toString()
									+ this.DealArrayToStr(values) + ")"; // 生成SQl语句
							stat.addBatch(sql);
							count++;
							if (cycle_Num == 0 && totalNum == count) { // 1
								stat.executeBatch();
							} else {
								if (count == BATCH_NUM) { // n
									stat.executeBatch();
									stat.clearBatch();
									count = 0;
									cycle_Num_Count++;
								} else if (cycle_Num_Count == cycle_Num
										&& count == count % BATCH_NUM) { // last
									stat.executeBatch();
									stat.clearBatch();
								}
							}
						}
					}
				}
			}
			// Date finishDate = new Date();
			// System.out.println((finishDate.getTime() - now.getTime())/1000 +
			// "秒");
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			dao.closeAllStmtAndRs(rs, null, stat, null);
			//dao.closeConnection(conn);
		}
		return false;
	}

	/**
	 * trans array to string
	 * 
	 * @param inputValues
	 * @return String
	 */
	private String DealArrayToStr(String[] inputValues) {
		StringBuffer existValues = new StringBuffer();
		for (int i = 0; i < inputValues.length; i++) {
			existValues.append("'" + inputValues[i] + "'");
			existValues.append((i == inputValues.length - 1) ? "" : ",");
		}
		return existValues.toString();
	}

	/**
	 * 生成试卷试题表的批量插入(Batch)
	 */
	public boolean sjstbBatchAddRecord(String sjlsh, String stlshParams)
			throws Exception {
		// delete all by sjlsh
		String tableName = "sjstb"; // 试卷试题表
		String sql = "delete from " + tableName + " where sjlsh=?";
		String[] stlshArray = stlshParams.split("-");
		int totalNum = stlshArray.length;
		int count = 0;
		int BATCH_NUM = 50; // 批量处理的数据量大小
		int cycle_Num = totalNum / BATCH_NUM;
		int cycle_Num_Count = 0;
		StringBuffer sColumn = new StringBuffer();
		StringBuffer sqlBf = new StringBuffer();
		New_Random_ID newid = new New_Random_ID(); // 虚拟主键
		String[] columns = new String[] { "sjlsh", "stlsh", "stxh", "xn_id" };
		// 处理columns
		for (int i = 0; i < columns.length; i++) {
			sColumn.append(columns[i] + ((i == columns.length - 1) ? "" : ","));
		}
		sqlBf.append("insert into ");
		sqlBf.append(tableName);
		sqlBf.append("(");
		sqlBf.append(sColumn.toString());
		sqlBf.append(")");
		sqlBf.append(" values(");

		Connection conn = null;
		Statement stat = null;
		try {
			boolean flag = dao.runUpdate(sql, new String[] { sjlsh });
			if(Base.isNull(stlshParams)){
				return flag;
			}
			conn = db.getConnection();
			stat = conn.createStatement();
			conn.setAutoCommit(false);
			String xn_id = "";
			for (int i = 1; i < stlshArray.length + 1; i++) {
				xn_id = newid.new_xnid(tableName);
				String[] values = { sjlsh, stlshArray[i - 1],
						String.valueOf(i), xn_id };
				sql = sqlBf.toString() + this.DealArrayToStr(values) + ")"; // 生成SQl语句
				stat.addBatch(sql);
				count++;
				if (cycle_Num == 0) { // 1
					if (totalNum == count)
						stat.executeBatch();
				} else {
					if (count == BATCH_NUM) { // n
						stat.executeBatch();
						stat.clearBatch();
						count = 0;
						cycle_Num_Count++;
					} else if (cycle_Num_Count == cycle_Num
							&& count == count % BATCH_NUM) { // last
						stat.executeBatch();
						stat.clearBatch();
					}
				}
			}
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			dao.closeAllStmtAndRs(null, null, stat, null);
			dao.closeConnection(conn);
		}
		return false;
	}
	
	/**
	 * 保存匹配咨询师
	 * 
	 * @author luojw
	 */
	public boolean savePpzxs(String xn_id, String zxxbh) throws Exception {

		DAO dao = DAO.getInstance();

		boolean flag = false;

		String[] id = null;

		if (!Base.isNull(xn_id)) {
			id = xn_id.split("!!@@!!");
		}

		if (id != null && id.length > 0) {
			StringBuffer sql = new StringBuffer();
			sql.append("update xljk_zxszyb set zxxbh = '" + zxxbh + "' ");
			for (int i = 0; i < id.length; i++) {
				if (i == 0) {
					sql.append("where xn_id = ? ");
				} else {
					sql.append("or xn_id = ? ");
				}
			}

			flag = dao.runUpdate(sql.toString(), id);
		}

		return flag;
	}
	
	/**
	 * 批量保存
	 * 
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql) throws Exception {

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
	 * 批量保存
	 * 
	 * @author luojw
	 */
	public boolean saveYydjb(String[] values,String xh) throws Exception {

		boolean flag = false;

		String[] exec = new String[values.length];

		for (int i = 0; i < exec.length; i++) {
			String sql = "insert into xljk_yydjb_jjcy(XN_ID, XH) values('"
					+ values[i] + "','" + xh + "')";
			exec[i] = sql;
		}

		flag = saveArrDate(exec);

		return flag;
	}
	
	/**
	 * 批量保存
	 * 
	 * @author luojw
	 */
	public boolean saveYydjb(String[] gxArr, String[] csnyArr, String[] xlArr,
			String[] zysfArr, String[] xn_id) throws Exception {


		String[] sqlArr = new String[xn_id.length];

		for (int i = 0; i < xn_id.length; i++) {

			String gx = gxArr[i];
			String csny = csnyArr[i];
			String xl = xlArr[i];
			String zysf = zysfArr[i];

			String[] cols = { "GX", "CSNY", "XL", "ZYSF" };
			String[] vs = { gx, csny, xl, zysf };

			StringBuffer sql = new StringBuffer();
			sql.append("update xljk_yydjb_jjcy ");

			for (int j = 0; j < cols.length; j++) {
				if (j == 0) {
					sql.append("set " + cols[j] + " = '" + vs[j] + "' ");
				} else {
					sql.append("," + cols[j] + " = '" + vs[j] + "' ");
				}
			}

			sql.append("where XN_ID = '" + xn_id[i] + "'");

			sqlArr[i] = sql.toString();
		}

		int[] result = dao.runBatch(sqlArr);

		return dao.checkBatch(result);
	}
	
	/**
	 * 辅导员列表2010.10.21 qlj
	 */
	public List<HashMap<String,String>>getFdyList(){
		DAO dao=DAO.getInstance();
		String sql=" select (zgh||','||xm) fdy from fdyxxb  ";
		return dao.getList(sql, new String[]{}, new String[]{"fdy","fdy"});
	}
	
	/**
	 *  咨询师列表2010.10.21 qlj
	 */
	public List<HashMap<String,String>>getZxsList(){
		DAO dao=DAO.getInstance();
		String sql=" select (zxxbh||','||zxxxm) zxs from xljk_zxsxxb  ";
		return dao.getList(sql, new String[]{}, new String[]{"zxs","zxs"});
	}
	
	/**
	 * 获取已匹配职工信息的咨询师信息
	 * 2010.10.21 qlj
	 */
	public List<HashMap<String,String>>getPpzxsList(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("   select (zgh||','||xm||'!!SplitTwo!!'||zxxbh||','||zxxxm)dm,(zgh||','||xm||'------'||zxxbh||','||zxxxm ) mc" );
		sql.append(" from xljk_zxsxxb where zxxbh is not null and zxxxm is not null and zgh is not null and xm is not null  ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 根据ZGH，XM，BMDM查询FDY信息2010.10.21 qlj
	 */
	public List<HashMap<String,String>>getFdyListByTj(String zgh,String xm,String bmdm){
		DAO dao=DAO.getInstance();
		String sql=" select (zgh||','||xm) fdy from fdyxxb where 1=1 ";
		String query="";
		if(!"".equalsIgnoreCase(zgh) && zgh!=null){
			query+=" and zgh like '%"+zgh+"%'";
		}
		if(!"".equalsIgnoreCase(xm) && xm!=null){
			query+=" and xm like '%"+xm+"%'";
		}
		if(!"".equalsIgnoreCase(bmdm) && bmdm!=null){
			query+=" and bmdm like '%"+bmdm+"%'";
		}
		return dao.getList(sql+query, new String[]{}, new String[]{"fdy","fdy"});
	}
	
	/**
	 * 根据zxxxm,zxxbh查询咨询师信息2010.10.21 qlj
	 */
	public List<HashMap<String,String>>getZxsListByTj(String zxxbh,String zxxxm){
		DAO dao=DAO.getInstance();
		String sql=" select (zxxbh||','||zxxxm) zxs from xljk_zxsxxb where 1=1 ";
		String query="";
		if(!"".equalsIgnoreCase(zxxbh) && zxxbh!=null){
			query+=" and zxxbh like '%"+zxxbh+"%'";
		}
		if(!"".equalsIgnoreCase(zxxxm) && zxxxm!=null){
			query+=" and zxxxm like '%"+zxxxm+"%'";
		}
		System.out.println(sql+query);
		return dao.getList(sql+query, new String[]{}, new String[]{"zxs","zxs"});
	}
	
	/**
	 * 批量更新，咨询师匹配信息
	 * @throws Exception 
	 * @throws Exception 
	 */
	public boolean  plUpdate(List<HashMap<String,String>>list) throws Exception{
		DAO dao=DAO.getInstance();
		boolean result=true;
		//先将所有FDY信息置空
	    dao.runUpdate("update xljk_zxsxxb set zgh='',xm=''", new String[]{});	
		if(list.size()>0){
			String []sql=new String[list.size()];
			for(int i=0 ;i<list.size();i++){
				HashMap<String,String>hashMap=list.get(i);
				sql[i]="update xljk_zxsxxb set zgh='"+hashMap.get("firstinput")+"',xm='"+hashMap.get("secundinput")+"' where zxxbh='"+hashMap.get("firstquery")+"' and zxxxm='"+hashMap.get("secundquery")+"'";
			}
			try {
				dao.runBatch(sql);
			} catch (SQLException e) {
				result=false;
				e.printStackTrace();
			}
			finally{
				return result;
			}
		}
		return result;
		
	}
	
	/**
	 * 判断是否是心理咨询师
	 */
	public boolean checkXlzxs(String zgh){
		DAO dao=DAO.getInstance();
		String sql=" select count(*)num from xljk_zxsxxb where zgh=? ";
		HashMap<String,String>hashMap=dao.getMap(sql, new String[]{zgh}, new String[]{"num"});
		int num=Integer.parseInt(hashMap.get("num"));
		if(num>0){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 咨询师专长列表
	 * 2010.12.2qlj
	 */
	public List<HashMap<String,String>>getZxszcList(){
		DAO dao=DAO.getInstance();
		String sql="select zcdm dm,zcmc mc from xljk_mjxy_zcdmb";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	
	/**
	 * 拼接SQL
	 */
	public StringBuilder getSql(xljk_xssqyy_Form myform){
		String userType=myform.getUserType();
		String isFdy=myform.getIsFdy();
		String isBzr=myform.getIsBzr();
		String userName=myform.getUserName();
		String userDep=myform.getUserDep();
		  // 用户为辅导员
		StringBuilder sql=new StringBuilder();
		if ("true".equalsIgnoreCase(isFdy) && !"true".equalsIgnoreCase(isBzr)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
		}
		// 用户为班主任
		if ("true".equalsIgnoreCase(isBzr) && !"true".equalsIgnoreCase(isFdy)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
		}
		// 用户为班主任兼辅导员
		if ("true".equalsIgnoreCase(isBzr) && "true".equalsIgnoreCase(isFdy)) {
			sql.append(" and (exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
			sql.append(" or exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "'))) ");
		}
		// 用户学院用户
		if ("xy".equalsIgnoreCase(userType)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and b.xydm = '" + userDep + "') ");
		}
		return sql;
		

	}
	
	public List<HashMap<String,String>>getTjList(String[]input,String query){
		DAO dao=DAO.getInstance();
		String[]outPut={"xm","zymc","nj","xymc","lxdh","zxxxm","rq","sjd"};
		String sql="select * from ( select b.xm,b.zymc,b.nj,b.xymc,b.lxdh,c.zxxxm,a.rq,a.sjd  from xljk_zxszyb a,view_xsjbxx b,xljk_zxsxxb c where a.xh=b.xh and a.zxxbh=c.zxxbh) a  ";
		return dao.getList(sql+query, input, outPut);
	}
	
}