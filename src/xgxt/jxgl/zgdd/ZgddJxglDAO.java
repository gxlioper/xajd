package xgxt.jxgl.zgdd;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base; 
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;


public class ZgddJxglDAO {
	DAO dao = DAO.getInstance();// 数据操作通用工具类
	/**
	 * 获取连队列表
	 */
	public List<HashMap<String, String>> getLdList(){
		String sql = "select xh dm,ldmc mc from zgdd_gfsldb order by ldmc";
		return dao.getList(sql, new String[]{},new String[]{"dm","mc"});
	}
	/**
	 * 获取早操情况评分列表
	 */
	public List<HashMap<String, String>> getZcPfList(){
		String sql = "select pfnr dm,pfnr mc from zgdd_pfqk order by pfdm";
		return dao.getList(sql, new String[]{},new String[]{"dm","mc"});
	}
	/**
	 * 获取相关信息
	 * @param table 表名
	 * @param querry 条件
	 * @return
	 */
	public HashMap<String,String> dao_getInfo(String table,String querry){
		DAO dao = DAO.getInstance();
		String[] colList =  dao.getColumnName("select * from "+table);
		for(int i=0;i<colList.length;i++){
			colList[i]=colList[i].toLowerCase();
		}
		String sql = "select * from "+table;
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	/**
	 * 出勤信息添加保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean gfscqAddSave(CqglModel model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xh     = model.getXh();
		String jcsj   = model.getJcsj();
		String bz     = model.getBz();
	    String cqqk   = model.getCqqk();
	    String lddm   = model.getLddm();
	    String zcqk   = model.getZcqk();
		String  pk    = "xh||jcsj";
		String sql    = "";		
		String tag = dao.getOneRs("select count(xh)cout from zgdzdx_gfscqb where "+pk+"=?",new String[]{xh+jcsj},"cout");
		String ss  = dao.getOneRs("select ldmc||qsh  ss from view_xszsxx where xh=? and rownum=1",new String[]{xh} ,"ss");
		if("0".equalsIgnoreCase(tag)){
			sql = " insert into zgdzdx_gfscqb(xh,jcsj,lddm,zcqk,cqqk,bz,szss)values(?,?,?,?,?,?,?) ";
			done = dao.runUpdate(sql, new String[]{xh,jcsj,lddm,zcqk,cqqk,bz,ss});
		}else{
			sql = " update zgdzdx_gfscqb set xh=?,jcsj=?,lddm=?,zcqk=?,cqqk=?,bz=? where "+pk+"='"+xh+jcsj+"'";
			done = dao.runUpdate(sql, new String[]{xh,jcsj,lddm,zcqk,cqqk,bz});
		}		
		return done;
	}
	/**
	 * 出勤信息修改保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean gfscqModiSave(CqglModel model,String pkValue) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String bz     = model.getBz();
	    String cqqk   = model.getCqqk();
	    String lddm   = model.getLddm();
	    String zcqk   = model.getZcqk();
		String  pk    = "xh||jcsj";
		String sql    = "";				
		sql = " update zgdzdx_gfscqb set lddm=?,zcqk=?,cqqk=?,bz=? where "+pk+"='"+pkValue+"'";
		done = dao.runUpdate(sql, new String[]{lddm,zcqk,cqqk,bz});				
		return done;
	}
	/**
	 *  出勤信息删除
	 */
	public boolean dao_gfscqDelete(String delPk) throws Exception{
		DAO dao = DAO.getInstance();	
		String pk = "xh||jcsj";
		String[] pkValueA = delPk.split("!!");		
		String[] delPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete zgdzdx_gfscqb  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(delPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	
	/**
	 * @author luo
	 * @describe 转换时间格式
	 */
	public String getTime(String sj) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select substr(?, 0, 4) || '年' || substr(?, 5, 2) || '月' ||"
			+ " substr(?, 7, 2) || '日' systime from dual";
		String systime = dao.getOneRs(sql, new String[] {sj,sj,sj}, "systime");
		return systime;
	}
	
	/**
	 * @author luo
	 * @describe 是否国防生
	 */
	public boolean isGfs(String xh) throws SQLException {

		boolean flg = false;
		String sql = "select isgfs from view_zgdd_gfsb where xh = ?";
		String isgfs = dao.getOneRs(sql, new String[] { xh }, "isgfs");

		if ("yes".equalsIgnoreCase(isgfs)) {
			flg = true;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 是否国防办
	 */
	public boolean isGfb(String zgh) throws SQLException {

		boolean flg = false;
		String sql = "select count(*) num from yhb a where a.yhm = ? and exists (select 1"
				+ " from yhzb b where zmc = '国防办' and a.zdm = b.zdm)";
		String num = dao.getOneRs(sql, new String[] { zgh }, "num");

		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}

	public ArrayList<String[]> getJxjffList(GfsModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ZgddJxglUnit unit= new ZgddJxglUnit();
		String kssj = myModel.getKssj();
		String jssj = myModel.getJssj();
		String cxzl = myModel.getCxzl();
		String xh = myModel.getXh();
		String xm = myModel.getXm();
		String[] colList   = new String[]{"pk","xm","xh","bjmc","zymc","jxjsfsj","jxjtfsj","jxjtfyy","jxjfhsj"};
		String[] inPutList = new String[]{};
		StringBuffer query = ZgddJxglUnit.makeQuery(new String[]{"xydm","zydm","bjdm","nj"},myModel);
		if(xh != null && !("".equalsIgnoreCase(xh.trim()))){
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if(xm != null && !("".equalsIgnoreCase(xm.trim()))){
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%' ");
		}
		if(cxzl != null && !("".equalsIgnoreCase(cxzl.trim()))){
			if(kssj != null && !("".equalsIgnoreCase(kssj.trim()))){
				query.append(" and ");
				query.append(cxzl);
				query.append(" >='");
				query.append(kssj);
				query.append("' ");
			}
			if(jssj != null && !("".equalsIgnoreCase(jssj.trim()))){
				query.append(" and ");
				query.append(cxzl);
				query.append(" <='");
				query.append(jssj);
				query.append("' ");
			}
		}
		String sql = "";
		return unit.commonQuery(tableName,query.toString(),inPutList,colList,sql,myModel);
	}

	public List<HashMap<String, String>> getJxjffTopTr(String tableName) {
		String[] colList   = new String[]{"pk","xm","xh","bjmc","zymc","jxjsfsj","jxjtfsj","jxjtfyy","jxjfhsj"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	public boolean GfsjxjglUpdate(String pk, GfsModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "zgdd_gfsjxjb";
		String pkComment = "xh||jxjsfsj";
		String[] colList = new String[] {"jxjfhsj","jxjsfsj","jxjtfsj","jxjtfyy","xh"};
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata= true;
		if(pk.equalsIgnoreCase("")){
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		}else{
			updata = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		}
		return updata;
	}

	public HashMap<String, String> getGfsjxjglOne(String pk, String xh) {
		HashMap<String, String> rs= new  HashMap<String, String>();
		ZgddJxglUnit unit= new ZgddJxglUnit();
		String tableName = "view_zgddgfsjxj";
		String[] colList   = new String[]{"bjdm","bjmc","jxjfhsj","jxjsfsj","jxjtfsj","jxjtfyy","nj","pk","xb","xh","xm","xydm","xymc","zydm","zymc"};
		rs = unit.commonQueryOne(tableName,colList,"pk",pk);
		colList = new String[] { "xh", "xm", "xb", "nj", "xydm", "xymc","zydm", "zymc", "bjdm", "bjmc" };
		if (!xh.equalsIgnoreCase("")) {
			rs = unit.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, rs, "");
		}
		return rs;
	}

	public boolean GfsjxjglDelete(String pk, HttpServletRequest request) throws Exception {
		String tableName = "zgdd_gfsjxjb";
		String pkComment = "xh||jxjsfsj";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk, request);
		return updata;
	}
	

	/**
	 * @author luo
	 * @describe 获得全部学生信息
	 */
	public List<HashMap<String, String>> getXsList(GfsModel model,
			String[] colList) throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
//		String xn = DealString.toGBK(model.getXn());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 是否国防生
		String isgfs = DealString.toGBK(model.getIsgfs());

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(nj) ? " and 1=1" : " and a.nj='" + nj + "'");
//		query.append(Base.isNull(xn) ? " and 1=1" : " and a.xn='" + xn + "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and a.xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and a.zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and a.bjdm='" + bjdm
				+ "'");
		query.append(Base.isNull(isgfs) ? " and 1=1" : " and a.isgfs='" + isgfs
				+ "'");

		String sql = "select * from (select a.*,rownum r from view_zgdd_gfsb a"
				+ query.toString()
				+ " and xh like ? and xm like ?) where r > "
				+ model.getPages().getStart()
				+ " and r <= "
				+ (model.getPages().getStart() + model.getPages().getPageSize());

		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] { xh, xm }, colList);
		// System.out.println(sql);
		sql = "select count(*) count from view_zgdd_gfsb a" + query.toString()
				+ " and xh like ? and xm like ?";

		model.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] { xh, xm },
						"count")));

		return rsList;
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存国防生
	 */
	public boolean saveGfs(GfsModel model) throws Exception {

		String sql = "";
		StringBuffer inssb = new StringBuffer();
		StringBuffer delsb = new StringBuffer();
		boolean flg = false;
		// 测评分
		String gfsXx = DealString.toGBK(model.getGfsxx());
		if (gfsXx != null && gfsXx.length() > 0) {
			// 单个学生信息
			String[] xs = gfsXx.split("!!@@!!");
			for (int i = 0; i < xs.length; i++) {
				String[] arrXx = xs[i].split("!@!");
				String xh = arrXx[0];
				String drzw = arrXx[1];
				String isgfs = arrXx[2];

				sql = "delete zgdd_gfs a where a.xh = '" + xh + "'";
				delsb.append(sql);
				delsb.append("!!#!!");

				sql = "insert into zgdd_gfs(xh, drzw, isgfs) values('" + xh
						+ "','" + drzw + "','" + isgfs + "')";
				inssb.append(sql);
				inssb.append("!!#!!");
			}
		}

		String[] delsql = delsb.toString().split("!!#!!");
		dao.runBatch(delsql);

		String[] inssql = inssb.toString().split("!!#!!");

		int[] res = dao.runBatch(inssql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 获得国防生卫生检查列表
	 */
	public List<HashMap<String, String>> getWsList(GfsModel model,
			String[] colList) throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		// 学号
		String xh = isGfs(model.getXh()) ? model.getXh() : DealString
				.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = isGfs(model.getXh()) ? model.getXm() : DealString
				.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 连队代码
		String lddm = DealString.toGBK(model.getLddm());
		// 卫生情况
		String wsqk = DealString.toGBK(model.getWsqk());
		// 周数
		String zs = DealString.toGBK(model.getZs());
		// 开始时间
		String kssj = DealString.toGBK(model.getKssj());
		// 结束时间
		String jssj = DealString.toGBK(model.getJssj());

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");

		query.append(Base.isNull(nj) ? " and 1=1" : " and a.nj='" + nj + "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and a.xn='" + xn + "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and a.xq='" + xq + "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and a.xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and a.zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and a.bjdm='" + bjdm
				+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and a.lddm='" + lddm
				+ "'");
		query.append(Base.isNull(wsqk) ? " and 1=1" : " and a.wsqk='" + wsqk
				+ "'");
		query.append(Base.isNull(zs) ? " and 1=1" : " and a.zs='" + zs + "'");
		query.append(Base.isNull(kssj) ? " and 1=1" : " and a.jcsj>='" + kssj
				+ "'");
		query.append(Base.isNull(jssj) ? " and 1=1" : " and a.jcsj<='" + jssj
				+ "'");

		String sql = "select * from (select a.*,rownum r from view_zgdd_gfswsb a"
				+ query.toString()
				+ " and xh like ? and xm like ?) where r > "
				+ model.getPages().getStart()
				+ " and r <= "
				+ (model.getPages().getStart() + model.getPages().getPageSize());

		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] { xh, xm }, colList);
		// System.out.println(sql);
		sql = "select count(*) count from view_zgdd_gfswsb a"
				+ query.toString() + " and xh like ? and xm like ?";

		model.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] { xh, xm },
						"count")));

		return rsList;
	}
	
	/**
	 * @author luo
	 * @describe 获得国防生信息
	 */
	public HashMap<String, String> getWsListXx(String pk) throws SQLException {

		String sql = "select * from view_xsjbxx a where a.xh= ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc","ssbh" };

		HashMap<String, String> rs = dao.getMap(sql, new String[] { pk },
				colList);

		return rs;
	}
	
	/**
	 * @author luo
	 * @describe 获得国防生卫生检查信息
	 */
	public HashMap<String, String> getWsXx(String pk) throws SQLException {

		String sql = "select xh, xm, xb, nj, xymc,zymc, bjmc,ssbh,zs,lddm,wsqk,jcsj,wsbz bz from view_zgdd_gfswsb a where a.xh||a.xn||a.xqmc||a.zs= ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "ssbh", "zs", "lddm", "wsqk", "jcsj", "bz" };

		HashMap<String, String> rs = dao.getMap(sql, new String[] { pk },
				colList);

		return rs;
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 保存卫生检查信息
	 */
	boolean saveWs(GfsModel model, HttpServletRequest request) throws Exception {

		String xh = DealString.toGBK(model.getXh());
		String lddm = DealString.toGBK(model.getLddm());
		String wsqk = DealString.toGBK(model.getWsqk());
		String ssbh = DealString.toGBK(model.getSsbh());
		String bz = DealString.toGBK(model.getBz());
		String zs = DealString.toGBK(model.getZs());
		String jcsj = model.getJcsj();
		String pk = xh + Base.currXn + Base.currXq + zs;

		boolean flg = StandardOperation.delete("zgdd_gfswsb", "xh||xn||xq||zs",
				pk, request);
		if (flg) {
			flg = StandardOperation.insert("zgdd_gfswsb", new String[] { "xh",
					"lddm", "wsqk", "ssbh", "bz", "xn", "xq", "zs", "jcsj" },
					new String[] { xh, lddm, wsqk, ssbh, bz, Base.currXn,
							Base.currXq, zs, jcsj }, request);
		}
		return flg;
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量删除国防生卫生信息
	 */
	public boolean delWs(String[] key) throws Exception {

		String sql = "";
		StringBuffer sb = new StringBuffer();

		boolean flg = false;

		if (key.length != 0) {
			for (int i = 0; i < key.length; i++) {
				sql = "delete zgdd_gfswsb a where a.xh||a.xn||a.xq||a.zs = '" + key[i] + "'";
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		
		String[] delsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(delsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 获得周数
	 */
	public List<HashMap<String, String>> getZsList(){

		String sql ="select xqzs from xtszb";
		String zs = dao.getOneRs(sql, new String[]{}, "xqzs");
		List<HashMap<String, String>> zsList = new ArrayList<HashMap<String, String>>();
		if (zs != null && !"".equalsIgnoreCase(zs)) {
			int zsV = Integer.parseInt(zs);
			for(int i=1;i<=zsV;i++){
				HashMap<String, String> zsMap = new HashMap<String, String>();
				zsMap.put("dm",String.valueOf(i));
				zsMap.put("mc","第"+String.valueOf(i)+"周");
				zsList.add(zsMap);
			}
		}					

		return zsList;
	}
	
	/**
	 * @author luo
	 * @describe 获得学期
	 */
	public String getXq(String xq){
		String sql ="select xqmc from xqdzb where xqdm=?";
		String xqmc = dao.getOneRs(sql, new String[]{xq}, "xqmc");
		return xqmc;
	}
	
	/**
	 * @author luo
	 * @describe 获得国防生卫生统计打印列表
	 */
	public List<HashMap<String, String>> getWsList(String xn, String xq,
			String zs, String xh, String nj, String xm, String xydm,
			String zydm, String bjdm, String lddm, String wsqk,String kssj,String jssj) {

		StringBuffer query = new StringBuffer();

		xh = Base.isNull(xh) ? "%" : xh;
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";

		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='" + nj + "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='" + bjdm
				+ "'");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='" + lddm
				+ "'");
		query.append(Base.isNull(wsqk) ? " and 1=1" : " and wsqk='"
				+ DealString.toGBK(wsqk) + "'");
		query.append(Base.isNull(kssj) ? " and 1=1" : " and jcsj>'" + kssj + "'");
		query.append(Base.isNull(jssj) ? " and 1=1" : " and jcsj<'" + jssj + "'");

		String sql = "select * from view_zgdd_gfswsb where xn||xq||zs=? and xh like ? and xm like ? "
				+ query.toString();
		System.out.print(sql);
		return dao.getList(sql, new String[] { xn + xq + zs, xh, xm },
				new String[] { "xh", "xm", "ldmc", "wsss", "wsqk", "wsbz" });
	}
	/**
	 * 传入二个数组返回LIST查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTitle(String[] enList,
			String[] cnList) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		if (enList != null && cnList != null) {
			for (int i = 0; i < enList.length; i++) {
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("en", enList[i]);// 英文名称
				tmpMap.put("cn", cnList[i]);// 中文名称
				topList.add(tmpMap);
			}
		}
		return topList;
	}
	/**
	 * @author 
	 * @describe 国防生成绩以及综合测评成绩的统计
	 */
	public ArrayList<HashMap<String, String>> getCjtj(ZgddJxglForm myForm,GfsModel model) throws SQLException {

		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xydm = model.getXydm();
		String xh = DealString.toGBK(model.getXh()).trim();
		String xm   =DealString.toGBK(model.getXm()).trim();
		String xn = model.getXn();
		String xq = model.getXq();
		String sztj = model.getSztj();
		String tjlx = model.getTjlx();
		String szsz = model.getSzsz();
		myForm.setXh(xh);
		myForm.setXm(xm);
		StringBuffer querry = new StringBuffer(" and 1=1 ");
		StringBuffer querry1 = new StringBuffer(" and 1=1 ");
		StringBuffer querry2 = new StringBuffer(" and 1=1 ");
		querry2.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry2.append(" and isgfs ='yes' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":"and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xydm)?"":"and xydm='"+xydm+"' ");
		querry.append(Base.isNull(xm)?"":"and xm='"+xm+"' ");
		querry.append(Base.isNull(xm)?"":"and xm='"+xm+"' ");
		querry.append(Base.isNull(sztj)?"":"and "+sztj+tjlx+szsz);
		
		querry1.append(Base.isNull(xn)?"":"and xn='"+xn+"' ");
		querry1.append(Base.isNull(xq)?"":"and xq='"+xq+"' ");
		

		// 学生基本信息
		String sql = "select * from (select rownum r,a.* from (select a.* from (select a.*,b.zpf cpzf from (select '"+xn+"'xn, " 
			+"(select xqmc from xqdzb where xqdm='"+xq+"') xq,a.xh,xm,xb,bjdm,bjmc,zydm,zymc,xydm,xymc, " 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh "+querry1+") zkcs," 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh and cj<60 "+querry1+") gkms," 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh and cj>80 "+querry1+") yxms," 
			+"(select round(sum(cj)/count(b.kcmc),1) from view_zhhcjb b where a.xh=b.xh "+querry1+") pjf," 
			+"(select sum(jd) from cjb b where a.xh=b.xh "+querry1+") jd from view_zgdd_gfsb a " 
			+"where 1=1 "+querry2+") a left outer join  view_zhszcp b on a.xh=b.xh"
			+ " ) a ) a where 1=1 "+querry+") a where a.r>"
			+ myForm.getPages().getStart()
			+ " and a.r<="
			+ (myForm.getPages().getStart() + myForm.getPages().getPageSize());
		System.out.println(sql);
		String[] colList = new String[] {"xh","xm","xb","bjmc","zymc","xn","xq","zkcs","gkms","yxms","pjf","jd","cpzf"};

		ArrayList<HashMap<String, String>> rs = dao.getArrayList2(sql, new String[] {}, colList);
		return rs;
	}
	/**
	 * @author 
	 * @describe 国防生成绩以及综合测评成绩的统计分页
	 */
	public String getCjtjfy(ZgddJxglForm myForm,GfsModel model) throws SQLException {

		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xydm = model.getXydm();
		String xh = model.getXh();
		String xm   = model.getXm();
		String xn = model.getXn();
		String xq = model.getXq();
		String sztj = model.getSztj();
		String tjlx = model.getTjlx();
		String szsz = model.getSzsz();
		
		StringBuffer querry = new StringBuffer(" and 1=1 ");
		StringBuffer querry1 = new StringBuffer(" and 1=1 ");
		StringBuffer querry2 = new StringBuffer(" and 1=1 ");
		querry2.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry2.append(" and isgfs ='yes' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":"and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xydm)?"":"and xydm='"+xydm+"' ");
		querry.append(Base.isNull(xm)?"":"and xm='"+xm+"' ");
		querry.append(Base.isNull(xm)?"":"and xm='"+xm+"' ");
		querry.append(Base.isNull(sztj)?"":"and "+sztj+tjlx+szsz);
		
		querry1.append(Base.isNull(xn)?"":"and xn='"+xn+"' ");
		querry1.append(Base.isNull(xq)?"":"and xq='"+xq+"' ");

		String sql = "select count(*) count from (select rownum r,a.* from (select a.* from (select a.*,b.cpzf from ( select '"+xn+"'xn, " 
			+"(select distinct xq from cjb where 1=1 "+querry1+") xq,a.xh,xm,xb,bjdm,bjmc,zydm,zymc,xydm,xymc, " 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh "+querry1+") zkcs," 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh and cj<60 "+querry1+") gkms," 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh and cj<80 "+querry1+") yxms," 
			+"(select round(sum(cj)/count(b.kcmc),1) from view_zhhcjb b where a.xh=b.xh "+querry1+") pjf," 
			+"(select sum(jd) from cjb b where a.xh=b.xh "+querry1+") jd from view_zgdd_gfsb a " 
			+"where 1=1 "+querry2+") a left outer join  view_zhszcp b on a.xh=b.xh"
			+ " ) a ) a where 1=1 "+querry+") a";
		//String[] colList = new String[] {"count"};
		//ArrayList<HashMap<String, String>> rs = dao.getArrayList2(sql, new String[] {}, colList);
		String count = dao.getOneRs(sql,  new String[] {}, "count");
		return count;
	}
	/**
	 * @author 
	 * @describe 国防生成绩以及综合测评成绩的统计 导出
	 */
	public ArrayList<HashMap<String, String>> getCjtjexp(ZgddJxglForm myForm,GfsModel model) throws SQLException {

		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xydm = model.getXydm();
		String xh = model.getXh();
		String xm   = model.getXm();
		String xn = model.getXn();
		String xq = model.getXq();
		String sztj = model.getSztj();
		String tjlx = model.getTjlx();
		String szsz = model.getSzsz();
		
		StringBuffer querry = new StringBuffer(" and 1=1 ");
		StringBuffer querry1 = new StringBuffer(" and 1=1 ");
		StringBuffer querry2 = new StringBuffer(" and 1=1 ");
		querry2.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry2.append(" and isgfs ='yes' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":"and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xydm)?"":"and xydm='"+xydm+"' ");
		querry.append(Base.isNull(xm)?"":"and xm='"+xm+"' ");
		querry.append(Base.isNull(xm)?"":"and xm='"+xm+"' ");
		querry.append(Base.isNull(sztj)?"":"and "+sztj+tjlx+szsz);
		
		querry1.append(Base.isNull(xn)?"":"and xn='"+xn+"' ");
		querry1.append(Base.isNull(xq)?"":"and xq='"+xq+"' ");

		// 学生基本信息
		String sql = "select * from (select rownum r,a.* from (select a.* from (select a.*,b.cpzf from ( select '"+xn+"'xn, " 
			+"(select xqmc from xqdzb where xqdm='"+xq+"') xq,a.xh,xm,xb,bjdm,bjmc,zydm,zymc,xydm,xymc, " 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh "+querry1+") zkcs," 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh and cj<60 "+querry1+") gkms," 
			+"(select count(b.kcmc) from cjb b where a.xh=b.xh and cj<80 "+querry1+") yxms," 
			+"(select round(sum(cj)/count(b.kcmc),1) from view_zhhcjb b where a.xh=b.xh "+querry1+") pjf," 
			+"(select sum(jd) from cjb b where a.xh=b.xh "+querry1+") jd from view_zgdd_gfsb a " 
			+"where 1=1 "+querry2+") a left outer join  view_zhszcp b on a.xh=b.xh"
			+ " ) a ) a where 1=1 "+querry+") a";
//		System.out.println(sql);
		String[] colList = new String[] {"xh","xm","xb","xn","xq","bjmc","zymc","zkcs","gkms","yxms","pjf","jd","cpzf"};

		ArrayList<HashMap<String, String>> rs = dao.getArrayList2(sql, new String[] {}, colList);
		return rs;
	}
	
	/**
	 * 获取查询表头
	 * @param colListCN
	 * @return
	 */
	public ArrayList<HashMap<String, String>> dao_getSearchTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
	
		String[] opCols = { "pk","jcsj","xh","xm","xb", "xymc","bjmc", "ldmc","zcqk"};
		String[] cnCols = { "pk","检查时间","学号","姓名","性别","院系","班级","连队","早操情况"};
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", opCols[i]);
			map.put("cn", cnCols[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**
	 * 出勤信息查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> dao_cqqkDefault(CqglModel model){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			querry.append(" and jcsj between '"+kssj+"' and '"+jssj+"' ");
		}else if(!Base.isNull(kssj)){
			querry.append(" and jcsj = '"+kssj+"' ");
		}else if(!Base.isNull(jssj)){
			querry.append(" and jcsj = '"+jssj+"' ");
		}
		
		String[] colList = new String[]{ "pk","jcsj","xh","xm","xb", "xymc","bjmc", "ldmc","zcqk"};
		String  sql = " select xh||jcsj pk,jcsj,xh,xm,xb, xymc,bjmc, ldmc,zcqk from view_zgdzdx_gfscqxx  " +querry;
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	/**
	 * 导出数据
	 */
	public List<HashMap<String, String>>dao_expData(CqglModel model){
		DAO  dao  = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh   = model.getXh();
		String xm   = model.getXm();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		sql.append(" select xh,xm,ldmc,szss,cqqk,zcqk,bz from view_zgdzdx_gfscqxx where 1=1 ");
		sql.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		sql.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		sql.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		sql.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		sql.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		sql.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			sql.append(" and jcsj between '"+kssj+"' and '"+jssj+"' ");
		}else if(!Base.isNull(kssj)){
			sql.append(" and jcsj = '"+kssj+"' ");
		}else if(!Base.isNull(jssj)){
			sql.append(" and jcsj = '"+jssj+"' ");
		}
		return dao.getList(sql.toString(),new String[]{},new String[]{ "xh","xm","ldmc","szss","cqqk","zcqk","bz"});
	}
}
