package xgxt.pjpy.tyb.zhszcp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.utils.String.StringUtils;

public class PjpyZctjszDAO {

	DAO dao = DAO.getInstance();

	/**
	 * 查询综测二级代码表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryEjTitle() {
		return dao.getList("select dm en,mc cn from zhszcpdmb order by dm",
				new String[] {}, new String[] { "en", "cn" });
	}

	/**
	 * 通过综测二级代码查询综测信息
	 * 
	 * @param dm
	 * @return
	 */
	public HashMap<String, String> queryZcejdmxx(String dm) {
		return dao
				.getMapNotOut(
						"select a.mc,a.dm,b.bl,b.lb,b.mrf,b.xzf,b.bm,b.zd,b.sfplzj,b.shjb,b.fdm,b.tj from zhszcpdmb a left "
								+ "join pjpy_zctjszb b on a.dm=b.dm where a.dm=?",
						new String[] { dm });
	}

	/**
	 * 保存2级综测代码设置条件信息 先删除后增加
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean save2jZhszcpdmxx(PjpyZctjszModel model) throws Exception {
		boolean result = dao.runUpdate("delete from pjpy_zctjszb where dm=?",
				new String[] { model.getDm() });
		return result ? dao
				.runUpdate(
						"insert into pjpy_zctjszb(dm,mc,bl,xzf,lb,bm,zd,fdm,tj,sfplzj,shjb,dmjb) values (?,?,?,?,?,?,?,?,?,?,?,?)",
						new String[] { model.getDm(), model.getMc(),
								model.getBl(), model.getXzf(), model.getLb(),
								model.getBm(), model.getZd(), model.getFdm(),
								model.getTj(), model.getSfplzj(),
								model.getShjb(), model.getJb() })
				: false;
	}

	/**
	 * 通过二级代码查询三级子代码信息
	 * 
	 * @param dm
	 * @return
	 */
	public List<HashMap<String, String>> query3jZcdm(String dm) {
		return dao.getList("select dm,mc from pjpy_zctjszb where fdm=?",
				new String[] { dm }, new String[] { "dm", "mc" });
	}

	/**
	 * 检测数据库代码是否存在 存在返回TRUE，反之FALSE
	 * 
	 * @param dm
	 * @return
	 */
	public boolean checkDmDataUnion(String dm) {
		if (StringUtils.isNull(dm)) {
			return false;
		} else {
			String count = dao.getOneRs(
					"select count(dm) cnt from pjpy_zctjszb where dm=?",
					new String[] { dm }, "cnt");
			return "0".equalsIgnoreCase(count) || StringUtils.isNull(count) ? false
					: true;
		}
	}

	/**
	 * 通过代码级别查询代码名称信息
	 * 
	 * @param dmjb
	 *            分为1，2，3，4级
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryZcsjdmxx(String dmjb, boolean isSfwh)
			throws Exception {
		String whereSql = isSfwh ? " and sfwh='1'" : "";
		return dao.getList("select bm dm,mc from pjpy_zctjszb where dmjb = ?" + whereSql,
				new String[] { dmjb }, new String[] { "dm", "mc" });
	}
	
	

	/**
	 * 通过父代码查询子代码信息
	 * 
	 * @param dmlb
	 * @param dm
	 * @return
	 */
	public List<HashMap<String, String>> queryZcdmxxList(String dm) {
		//对于不需要维护的数据只能进行查询
		String[] dmArray = StringUtils.isNotNull(dm) ? dm.split("!@") : new String[]{};
		StringBuilder sql = new StringBuilder("select dm,mc,bm,sfwh,shjb from pjpy_zctjszb where 1=1 and sfwh='1'");
		if (dmArray != null && dmArray.length > 0) {
			sql.append(" and fdm in (");
			for (int i=0;i<dmArray.length;i++) {
				sql.append("'");
				sql.append(dmArray[i]);
				sql.append("',");
			}
			sql.delete(sql.length() - 1, sql.length());
			sql.append(") order by dm asc");
		}
		return dao
				.getList(
						//"select dm,mc,bm from pjpy_zctjszb where fdm=? and sfwh='1' order by dm asc",
						sql.toString(),
						new String[] {}, new String[] {
								"dm", "mc", "bm", "sfwh", "shjb" });
	}
	
	/**
	 * 通过父代码和表名查询审核级别
	 * 
	 * @param dm
	 * @param tableName
	 * @returnString
	 */
	public String queryZhszcpxmShjb(String dm,String tableName) {
		return dao
				.getOneRs(
						"select shjb from pjpy_zctjszb where fdm=? and bm=?  order by dm asc",
						new String[] {dm,tableName },"shjb");
	}
	
	/**
	 * 通过表名查询代码详细信息
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpxm(String tableName) {
		return dao
				.getList(
						"select zdid,zdmc,tabname,zdlx,zdcd,cxxs,cxxspx,zdpx,nvl(sfnum,'') sfnum,xzf,"
								+ "nvl(sfnull,'') sfnull,(case when sfnull='0' then zdmc||'" +
										"<font color=red>*</font>' else zdmc end ) sfnullmc from py_bdszb a where tabname=? order by zdpx",
						new String[] { tableName },
						new String[] { "zdid", "zdmc", "tabname", "zdlx",
								"zdcd", "cxxs", "cxxspx", "zdpx", "sfnum","xzf", 
								"sfnull", "sfnullmc" });
	}
	
	/**
	 * 查询综测代码信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpDmList() {
		return dao.getList(
				"select dm,mc,dmjb from pjpy_zctjszb order by dmjb,dm asc",
				new String[] {}, new String[] { "dm", "mc", "dmjb" });
	}
	
	/**
	 * 查询综合测评项目信息
	 * @param jb
	 * @param dm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryZhcpXmxx(String jb, String[] dm){
		String[] input = new String[]{jb};
		String sql = "select dm,mc,dmjb,fdm from pjpy_zctjszb where dmjb=?";
		
		if(dm != null && dm.length > 0){
			sql = "select dm,mc,dmjb,fdm from pjpy_zctjszb where dmjb=? and dm in ('";
			for (int i=0;i<dm.length;i++) {
				sql += dm[i];
				sql += "','";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += ")";
			input = new String[]{jb};
		}
		return dao.getList(sql, 
						  input, 
				          new String[]{"dm", "mc", "dmjb", "fdm"});
	}
	
	/**
	 * 修改父代码表名和审核级别信息
	 * @param dmArray
	 * @param bmArray
	 * @param fdmArray
	 * @return
	 */
	public boolean updateFdmbmAndShjb(PjpyZhszcpModel model) {
		String[] sqlArr = new String[model.getFdm().length];
		for (int i = 0; i < model.getFdm().length; i++) {
			StringBuilder sql = new StringBuilder(
					"update pjpy_zctjszb set (bm,shjb,sfwh)=(select bm,shjb,sfwh from pjpy_zctjszb where dm='");
			sql.append(model.getDm()[i]);
			sql.append("' and bm='");
			sql.append(model.getBm()[i]);
			sql.append("' and rownum='1') where dm='");
			sql.append(model.getFdm()[i]);
			sql.append("'");

			sqlArr[i] = sql.toString();
		}
		try {
			return dao.checkBatch(dao.runBatch(sqlArr));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 综测条件配置检测查询结果
	 * @return
	 */
	public List<String[]> queryZctjjcResult() {
		return dao
				.rsToVator(
						"select msg,dm,mc,bm,zd,fmc,sfwh,shjb,tj from pjpy_zctjjcb order by msg,dm",
						new String[] {}, new String[] { "msg", "dm", "mc",
								"bm", "zd", "fmc", "sfwh", "shjb", "tj" });
	}
	
	/**
	 * 执行综测配置条件检测
	 * @return
	 * @throws Exception
	 */
	public boolean zctjSjCheckResult() throws Exception{
		return dao.runProcuder("{call PROC_PJPY_ZCTJPZJC()}", new String[]{});
	}
	
	/**
	 * 插入一条错误检测信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean insertSjjcErrorMsg(Map<String, String> map) throws Exception{
		return dao.insert("insert into pjpy_zctjjcb (msg) values (?)", new String[]{map.get("msg")});
	}
	
	/**
	 * 删除综测配置检测信息表中的所有数据
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAllZcsjjcResult() throws Exception{
		return dao.runUpdate("delete from pjpy_zctjjcb", new String[]{});
	}
	
	/**
	 * 查询三级要进行数据维护的代码信息
	 * @return
	 */
	public List<HashMap<String, String>> query3jwhdmxx() {
		return dao.getList(
						"select dm,bm,shjb,sfwh,fdm from pjpy_zctjszb where dmjb='3' and sfwh='1' order by dm",
						new String[] {}, new String[] { "dm", "bm", "shjb",
								"sfwh", "fdm" });
	}
	
	/**
	 * 修改二级综测代码信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update2jZcdmxx(PjpyZctjszModel model) throws Exception{
		return dao.runUpdate(
				"update pjpy_zctjszb set bl=?,xzf=?,mrf=?,lb=? where dm=?",
				new String[] { model.getBl(), model.getXzf(), model.getMrf(),model.getLb(),
						model.getDm() });
	}
	
	/**
	 * 修改三级综测代三信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update3jZcdmxx(PjpyZctjszModel model) throws Exception{
		String[] sqlArr = new String[model.getEjdm().length];
		StringBuilder sql = new StringBuilder("");
		for (int i=0;i<sqlArr.length;i++) {
			sql = new StringBuilder("");
			sql.append("update pjpy_zctjszb set bl='");
			sql.append(model.getEjbl()[i]);
			sql.append("',xzf='");
			sql.append(model.getEjxzf()[i]);
			sql.append("',mrf='");
			sql.append(model.getEjmrf()[i]);
			sql.append("',lb='");
			sql.append(model.getEjlb()[i]);
			sql.append("' where dm='");
			sql.append(model.getEjdm()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		return dao.checkBatch(dao.runBatch(sqlArr));
	}

	/**
	 * 修改四级综测代码信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update4jZcdmxx(PjpyZctjszModel model) throws Exception{
		String[] sqlArr = new String[model.getSjdm().length];
		StringBuilder sql = new StringBuilder("");
		for (int i=0;i<sqlArr.length;i++) {
			sql = new StringBuilder("");
			sql.append("update pjpy_zctjszb set bl='");
			sql.append(model.getSjbl()[i]);
			sql.append("',xzf='");
			sql.append(model.getSjxzf()[i]);
			sql.append("',mrf='");
			sql.append(model.getSjmrf()[i]);
			sql.append("',lb='");
			sql.append(model.getSjlb()[i]);
			sql.append("' where dm='");
			sql.append(model.getSjdm()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		return dao.checkBatch(dao.runBatch(sqlArr));
	}
}
