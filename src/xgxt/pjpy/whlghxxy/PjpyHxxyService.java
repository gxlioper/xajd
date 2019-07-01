package xgxt.pjpy.whlghxxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.utils.ModelToStrings;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
public class PjpyHxxyService {

	private static PjpyHxxyService service = new PjpyHxxyService();
	
	public static PjpyHxxyService getInstance() {
		return service;
	}
	
	private PjpyHxxyDAO dao = PjpyHxxyDAO.getInstance();
	
	/**
	 * 查询综合素质表头
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryZhszcpTitle() {
		String[] enList = new String[]{"pk", "rownum", "xn","xh", "xm", "bjmc", "dcj", "zcj", "tcj", "mcj", "xf", "jlf", "cff"};
		String[] cnList = new String[]{"pk", "行号", "学年","学号", "姓名", "班级", "德育", "智育", "体育", "美育", "学分绩", "奖励分", "惩罚分"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * 荣誉称号审核
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryRychshTitle() {
		String[] enList = new String[]{"pk", "rownum", "xn","xh", "xm", "xn","xysh", "xxsh"};
		String[] cnList = new String[]{"pk", "行号", "学年","学号", "姓名", "学年", "学院审核", "学校审核"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * 查询综合素质结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryrychshList(PjpyHxxyModel model) throws Exception {
		return dao.queryrychshResultByxy(model);
	}
	/**
	 * 荣誉称号审核
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpList(PjpyHxxyModel model, PjpyHxxyActionForm dataSearchForm) throws Exception {
		return dao.queryZhszcpList(model, dataSearchForm);
	}
	
	public int queryZhszcpListNum(PjpyHxxyModel model) throws Exception {
		return dao.queryZhszcpListNum(model);
	}
	
	/**
	 * 删除综合素质分
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteZhszf(String[] keys) throws Exception {
		return dao.deleteZhszf(keys);
	}
	
	/**
	 * 获取个人奖学金评定信息
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(PjpyHxxyModel model) throws Exception {
		return dao.getJxjpdxx(model);
	}
	
	public boolean chksqtj(String xh) {
		return dao.chksqtj(xh);
	}
	
	/**
	 * 奖学金申请保存
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(PjpyHxxyModel model, HttpServletRequest request) throws Exception {
		return dao.jxjsqSave(model, request);
	}
	
	/**
	 * 传入表名返回LIST查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String tableName) throws Exception {
		String[] enList = null;
		String[] cnList = null;
		if (StringUtils.isEqual(tableName, "xsjxjb")) {
			enList = new String[]{"pk", "rownum", "nd", "xn", "xh", "xq", "xm", "bjmc","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk", "行号", "年度", "学年", "学号", "学期", "姓名", "班级名称", "奖学金名称", "学院审核", "学校审核"};
		}
		if (StringUtils.isEqual(tableName, "stusqxx")) {
			enList = new String[]{"xh||xn||xq||nd||jxjdm", "rownum","xn", "xq","nd","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk","行号", "学年","学期","年度", "奖学金", "学院审核", "学校审核"};
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * 学生奖学金查询信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuJxjSqxx(String xh) throws Exception {
		return dao.stuJxjSqxx(xh);
	}
	
	/**
	 * 自动计算综合分
	 * @param xn
	 * @param nd
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean autoCount(String xn, String nd, String xydm) throws Exception{
		return dao.autoCount(xn, nd, xydm);
	}
	
	/**
	 * 显示综合素质测评分
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewZhszcp(String pkValue) {
		return dao.viewZhszcp(pkValue);
	}
	
	/**
	 * 修改综合分
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcp(String pkValue, PjpyHxxyModel model, HttpServletRequest request) throws Exception{
		return dao.updateZhszcp(pkValue, model, request);
	}
	
	
	public List<String[]> queryJxjshResult(PjpyHxxyModel model, String userType)
	throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryJxjshResultByxy(model);
		} else {
			return dao.queryJxjshResultByxx(model);
		}
	}
	
	/**
	 * 审核
	 * @param key
	 * @param jg
	 * @param userType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	public String jxjplsh(String[] key,String jg, String userType, HttpServletRequest request) throws Exception {
		return dao.jxjplsh(key, jg, userType, request);
	}
	
	public HashMap<String, String> viewJxjshOne(String pkValue, String userType) throws Exception {
		return dao.viewJxjshOne(pkValue, userType);
	}
	
	public boolean jxjshOne(String pkValue, String userType, String sh, String yj, String fdyyj) throws Exception {
		return dao.jxjshOne(pkValue, userType, sh, yj, fdyyj);
	}
	
	public List<HashMap<String, String>> queryJxjshTitle(String userType) {
		String[] enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
				"xm", "bjmc", "jxjmc", "xf", "xfpm", "zf", "zfpm", "xysh" };
		String[] cnList = new String[] { "pk", "dis", "行号", "学年", "学号","姓名", "班级",
				"奖学金", "学分绩", "学分绩排名", "综合素质总分", "总分排名" , "学院审核"};
		if (!"xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
					"xm", "bjmc", "jxjmc", "xf", "xfpm", "zf", "zfpm", "xysh" };
			cnList = new String[] { "pk", "dis", "行号", "学年", "学号","姓名", "班级",
					"奖学金", "学分绩", "学分绩排名", "综合素质总分", "总分排名" , "学校审核"};
		}
		return dao.getTitleList(enList, cnList);
	}
	
	/**
	 * @describe 荣誉称号保存到数据库
	 * @author luo
	 */
	public boolean updataRych(PjpyHxxyModel myModel, String pk,
			HttpServletRequest request, String type, String oldpk) throws Exception {

		String tableName = "XSRYCHB";
		String pkComment = "xh||xn||rychdm";
		
		String[] colList = new String[] { "xn", "nd", "xh", "rychdm", "zysj",
				"drshgzqk","wydj" };
		
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		// boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
		// request);
		boolean inserted = false;
		if ("yes".equalsIgnoreCase(type)) {
			inserted = StandardOperation.update(tableName, colList, inputList, pkComment, oldpk, request);
		} else {
		inserted = StandardOperation.insert(tableName, colList,
				inputList, request);
		}

		return inserted;
	}
	/**
	 * @describe 取得荣誉称号表头
	 * @author luo
	 */
	public List getRychTopTr() {

		DAO dao = DAO.getInstance();
		String tableName = "view_xsrychb";
		String[] colList = new String[] { "pk","nd", "xn","xh", "xm", "bjmc",
				"rychmc", "xysh", "xxsh" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}
	

	/**
	 * @describe 取得荣誉称号列表
	 * @author luo
	 */
	public ArrayList<String[]> getRychList(PjpyHxxyModel myModel,
			PjpyHxxyActionForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String xn = DealString.toGBK(myModel.getXn());
		String nd = DealString.toGBK(myModel.getNd());
		String xq = DealString.toGBK(myModel.getXq());
		String rychdm = DealString.toGBK(myModel.getRychdm());
//		String xxsh = DealString.toGBK(myModel.getXxsh());
		String xysh = DealString.toGBK(myModel.getXysh());

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, nd, xq, xn));

		if(rychdm!=null&&!"".equals(rychdm)){
			query.append(" and rychdm = '");
			query.append(rychdm);
			query.append("'");
		}
		if(xysh!=null&&!"".equals(xysh)){
			query.append(" and xysh = '");
			query.append(xysh);
			query.append("'");
			query.append(" and xxsh = '");
			query.append(xysh);
			query.append("'");
		}
		
		String[] colList = new String[] { "pk","nd", "xn", "xh", "xm", "bjmc",
				"rychmc", "xysh", "xxsh" };

		String sql = "select * from (select * from (select xn||xh||rychdm pk,rownum r,nd,xn,xh,xm,bjmc,rychmc,xysh, xxsh from view_xsrychb "
				+ query
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ ") where r>"
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_xsrychb " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}
	/**
	 * @describe 得到荣誉称号详细信息
	 * @author luo
	 */
	public HashMap<String, String> getRychOne(String pk) {

		DAO daoC=new DAO();
		String[] colList = new String[] { "xh", "nd", "xm", "xn", "xb",
				"xymc", "zymc", "bjmc", "rychdm","rychmc","zysj","drshgzqk","nj","mzmc", "wydj", "csrq", "lxdh", "zzmmmc","rxrq"
				 };
		pk= pk.replace(" ", "");
		String sql = "select a.xh, a.nd, a.xm, a.xn, a.xb,a.xymc, a.zymc, a.bjmc, a.rychdm,a.rychmc,a.zysj,a.drshgzqk,"
				+ " a.nj,b.mzmc, a.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from view_xsrychb a"
				+ " ,view_xsxxb b  where a.xh=b.xh and a.xn ||a.xh||a.rychdm = ? ";
		HashMap<String, String> rs = daoC.getMap(sql, new String[] { pk },
				colList);
		return rs;
	}
	
	/**
	 * @describe 得到荣誉称号详细信息
	 * @author luo
	 */
	public HashMap<String, String> getRychStu(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO daoc=new DAO();
		String sql = "select b.xh,b.xm,b.xb,b.xymc, b.zymc,b.bjmc,b.nj,b.mzmc,"
				+ " b.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from "
				+ " view_xsxxb b where b.xh=?";
		
		map = daoc.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc", "mzmc", "wydj", "zzmmmc",
				"csrq", "lxdh", "rxrq" });
		
		return map;
	}
	
	/**
	 * 荣誉称号批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String rychDel(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from xsrychb where xn||xh||rychdm = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * @describe 已经申请的荣誉称号
	 * @author luo
	 */
	public HashMap<String, String> getRych(String pk,
			HttpServletRequest request) throws Exception {

		DAO daoc = new DAO();
		String sql = "select t.xh,t.xb,t.xm,t.nj,t.xymc,t.rychdm,t.zymc,t.bjmc, b.mzmc, b.wydj, b.csrq, b.lxdh, b.rxrq from "
			+ " view_xsrychb t, view_xsxxb b where t.xh = b.xh and t.xh||t.xn||t.rychdm=?";
		String[] colList = new String[] { "xh",
				"xm", "xb", "nj", "xymc", "rychdm","zymc", "bjmc", "mzmc", "wydj",
				"csrq", "lxdh", "rxrq" };
		HashMap<String, String> map = daoc.getMap(sql, new String[]{pk}, colList);
		

		return map;
	}
	
	
	public List<String[]> jxjsqjgQuery(PjpyHxxyModel model,PjpyHxxyActionForm dataSearchForm) throws Exception {
		return dao.jxjsqjgQuery(model, dataSearchForm);
	}
	
	public int jxjsqjgQueryNum(PjpyHxxyModel model, PjpyHxxyActionForm dataSearchForm) throws Exception {
		return dao.jxjsqjgQueryNum(model, dataSearchForm);
	}
	
	public List<HashMap<String, String>> jxjsqTitlequery(String userType) {
		String[] enList = new String[]{"pk", "rownum", "xn", "xh", "xm", "bjmc", "xysh", "xxsh"};
		String[] cnList = new String[] { "pk", "行号", "学年", "学号","姓名", "班级",
				 "学院审核", "学校审核"};
		
		return dao.getTitleList(enList, cnList);
	}
	
	public String jxjsqDelete(String[] keys) throws Exception {
		return dao.jxjsqDelete(keys);
	}
	
	public boolean jxjsqUpdate(String pkValue, PjpyHxxyModel model, HttpServletRequest request) throws Exception {
		return dao.jxjsqUpdate(pkValue, model, request);
	}
	
	public HashMap<String, String> jxjsqView(String pkValue) {
		return dao.jxjsqView(pkValue);
	}
	
	/**
	 * 审核
	 * @param key
	 * @param jg
	 * @param userType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryrychshResult(PjpyHxxyModel model, String userType)
	throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryrychshResultByxy(model);
		} else {
			return dao.queryrychshResultByxx(model);
		}
	}
	
	public String ruchplsh(String[] key,String jg, String userType, HttpServletRequest request) throws Exception {
		return dao.rychplsh(key, jg, userType, request);
	} 
	
	public HashMap<String, String> viewrychshOne(String pkValue, String userType) throws Exception {
		return dao.viewrychshOne(pkValue, userType);
	}
	
	public boolean rychshOne(String pkValue, String userType, String sh, String yj, String fdyyj) throws Exception {
		return dao.rychshOne(pkValue, userType, sh, yj, fdyyj);
	}
	
	public List<HashMap<String, String>> queryrychshTitle(String userType) {
		String[] enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
				"xm", "xb", "rychdm","xysh"};
		String[] cnList = new String[] { "pk", "dis", "行号", "学年", "学号","姓名", "性别",
				"荣誉称号","学院审核"};
		if (!"xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
					"xm", "xb", "rychdm","xysh","xxsh"};
			cnList = new String[] { "pk", "dis", "行号", "学年", "学号","姓名", "性别",
					"荣誉称号","学院审核","学校审核"};
		}
		return dao.getTitleList(enList, cnList);
	}
}
