
package xgxt.pjpy.zjjd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电评奖评优DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class PjpyZjjdDAO {

	ArrayList<String> values = new ArrayList<String>();//查询条件值
	DAO dao = DAO.getInstance();//数据操作通用工具类
	
	/**
	 * 通过学期获取年月列表
	 * @return
	 */
	public List<HashMap<String, String>> getYfList(String xq) throws Exception {
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;//存放月份代码列表
		if (!StringUtils.isNull(xq)) {
			if (StringUtils.isEqual(xq, "01") || StringUtils.isEqual(xq, "1")) {
				enList = new String[]{"2", "3", "4", "5", "6"};//第一学期
			} else {
				enList = new String[]{"9", "10", "11", "12", "1"};//第二学期
			}
			yfList = dao.arrayToList(enList, enList);
		}
		return yfList;
	}
	
	/**
	 * 查询条件值
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(XybxfModel xybxfModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xybxfModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(xybxfModel.getBjdm());
		}
		if (!StringUtils.isNull(xybxfModel.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(xybxfModel.getXh());
		}
		if (!StringUtils.isNull(xybxfModel.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + xybxfModel.toString() + "%");
		}
		if (!StringUtils.isNull(xybxfModel.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(xybxfModel.getXn());
		}
		if (!StringUtils.isNull(xybxfModel.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(xybxfModel.getXq());
		}
		if (!StringUtils.isNull(xybxfModel.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(xybxfModel.getXydm());
		}
		if (!StringUtils.isNull(xybxfModel.getYf())) {
			whereSql.append(" and yf = ?");
			values.add(xybxfModel.getYf());
		}
		if (!StringUtils.isNull(xybxfModel.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(xybxfModel.getZydm());
		}
		if (!StringUtils.isNull(xybxfModel.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(xybxfModel.getNj());
		}
		return whereSql;
	}
	
	/**
	 * 查询条件值
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(XsgbdyfjfModel xybxfModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xybxfModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(xybxfModel.getBjdm());
		}
		if (!StringUtils.isNull(xybxfModel.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(xybxfModel.getXh());
		}
		if (!StringUtils.isNull(xybxfModel.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(xybxfModel.getXm()) + "%");
		}
		if (!StringUtils.isNull(xybxfModel.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(xybxfModel.getXn());
		}
		if (!StringUtils.isNull(xybxfModel.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(xybxfModel.getXq());
		}
		if (!StringUtils.isNull(xybxfModel.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(xybxfModel.getXydm());
		}
		if (!StringUtils.isNull(xybxfModel.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(xybxfModel.getZydm());
		}
		if (!StringUtils.isNull(xybxfModel.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(xybxfModel.getNj());
		}
		return whereSql;
	}
	
	/**
	 * 查询条件值
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(ZhszcpModel zhszcpModel) throws Exception{
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(zhszcpModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(zhszcpModel.getBjdm());
		}
		if (!StringUtils.isNull(zhszcpModel.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(zhszcpModel.getXh());
		}
		if (!StringUtils.isNull(zhszcpModel.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(zhszcpModel.getXn());
		}
		if (!StringUtils.isNull(zhszcpModel.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(zhszcpModel.getXq());
		}
		if (!StringUtils.isNull(zhszcpModel.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(zhszcpModel.getXydm());
		}
		if (!StringUtils.isNull(zhszcpModel.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(zhszcpModel.getZydm());
		}
		if (!StringUtils.isNull(zhszcpModel.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(zhszcpModel.getNd());
		}
		if (!StringUtils.isNull(zhszcpModel.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(zhszcpModel.getNj());
		}
		return whereSql;
	}
	
	/**
	 * 查询结果及值
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql3(JxjpdxxModel jxjpdModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(jxjpdModel.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(jxjpdModel.getXn());
		}
		if (!StringUtils.isNull(jxjpdModel.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(jxjpdModel.getXh());
		}
		if (!StringUtils.isNull(jxjpdModel.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(jxjpdModel.getXq());
		}
		if (!StringUtils.isNull(jxjpdModel.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(jxjpdModel.getNj());
		}
		if (!StringUtils.isNull(jxjpdModel.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(jxjpdModel.getXydm());
		}
		if (!StringUtils.isNull(jxjpdModel.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(jxjpdModel.getZydm());
		}
		if (!StringUtils.isNull(jxjpdModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(jxjpdModel.getBjdm());
		}
		return whereSql;
	}
	
	public StringBuffer getWhereSql5(JxjpdxxModel jxjpdModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(jxjpdModel.getXn())) {
			whereSql.append(" and a.xn = ?");
			values.add(jxjpdModel.getXn());
		}
		if (!StringUtils.isNull(jxjpdModel.getXh())) {
			whereSql.append(" and a.xh = ?");
			values.add(jxjpdModel.getXh());
		}
		if (!StringUtils.isNull(jxjpdModel.getXq())) {
			whereSql.append(" and a.xq = ?");
			values.add(jxjpdModel.getXq());
		}
		if (!StringUtils.isNull(jxjpdModel.getNj())) {
			whereSql.append(" and a.nj = ?");
			values.add(jxjpdModel.getNj());
		}
		if (!StringUtils.isNull(jxjpdModel.getXydm())) {
			whereSql.append(" and a.xydm = ?");
			values.add(jxjpdModel.getXydm());
		}
		if (!StringUtils.isNull(jxjpdModel.getZydm())) {
			whereSql.append(" and a.zydm = ?");
			values.add(jxjpdModel.getZydm());
		}
		if (!StringUtils.isNull(jxjpdModel.getBjdm())) {
			whereSql.append(" and a.bjdm = ?");
			values.add(jxjpdModel.getBjdm());
		}
		return whereSql;
	}
	
	public StringBuffer getWhereSql4(RychModel rychModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(rychModel.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(rychModel.getXn());
		}
		if (!StringUtils.isNull(rychModel.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(rychModel.getXh());
		}
		if (!StringUtils.isNull(rychModel.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(rychModel.getXq());
		}
		if (!StringUtils.isNull(rychModel.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(rychModel.getNj());
		}
		if (!StringUtils.isNull(rychModel.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(rychModel.getXydm());
		}
		if (!StringUtils.isNull(rychModel.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(rychModel.getZydm());
		}
		if (!StringUtils.isNull(rychModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(rychModel.getBjdm());
		}
		return whereSql;
	}
	
	/**
	 * 校园表现分查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXybxfTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xh", "xn", "xqmc" ,"xm", "bjmc", "yf", "jf", "kf"};
		String[] cnList = new String[]{"pk", "行号", "学号", "学年", "学期", "姓名", "班级", "月份","加分", "扣分"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * 校园表现分查询结果
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXybxfResult(XybxfModel xybxfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(xybxfModel);
		String[] opList = new String[]{"pk", "rownum", "xh", "xn", "xqmc", "xm", "bjmc", "yf", "jf", "kf"};
		String sql = "select xh||xn||xq||yf pk,rownum,xh,xn,xqmc,xq,xm,bjmc,yf,jf,kf from view_xybxfcj where 1=1 ";
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 校园表现分的单个保存,先删除后插入
	 * @param xybxfModel
	 * @return 
	 * @throws Exception
	 */
	public boolean xybxfSave(XybxfModel xybxfModel, HttpServletRequest request) throws Exception {
		boolean bDel = StandardOperation.delete("xybxfcjb", "xh||xn||xq||yf",
				xybxfModel.getXh() + xybxfModel.getXn() + xybxfModel.getXq()
						+ xybxfModel.getYf(), request);
		if (bDel) {
			return StandardOperation.insert("xybxfcjb", new String[] { "xh", "xn",
					"xq", "yf", "jf", "kf", "sx" }, new String[] {
					xybxfModel.getXh(), xybxfModel.getXn(), xybxfModel.getXq(),
					xybxfModel.getYf(), xybxfModel.getJf(), xybxfModel.getKf(),
					DealString.toGBK(xybxfModel.getSx()) }, request);
		}
		return false;
	}
	
	/**
	 * 通过主键获取学生校园表现分信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXybxfInfo(String pkValue) throws Exception {
		String[] opList = new String[] { "xh", "xn", "xq", "bjmc", "jf",
				"kf", "nj", "xb", "xm", "xymc", "zymc", "bjmc", "sx","yf" };
		return dao.getMap(
				"select * from view_xybxfcj where xh||xn||xq||yf = ?",
				new String[] { pkValue }, opList);
	}
	
	/**
	 * 校园表现分的单个修改分为修改主键，未修改主键
	 * @param xybxfModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xybxfModi(XybxfModel xybxfModel, String pkValue, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
//		boolean bDel = false;
		if (StringUtils.isEqual(pkValue, xybxfModel.getXh()
				+ xybxfModel.getXn() + xybxfModel.getXq() + xybxfModel.getYf())) {//没有修改主键
			StandardOperation.delete("xybxfcjb", "xh||xn||xq||yf",
					pkValue, request);
		} else {//修改过主键
			StandardOperation.delete("xybxfcjb", "xh||xn||xq||yf",
					xybxfModel.getXh() + xybxfModel.getXn() + xybxfModel.getXq()
							+ xybxfModel.getYf(), request);
			StandardOperation.delete("xybxfcjb", "xh||xn||xq||yf",
					pkValue, request);
		}
		bFlag = StandardOperation.insert("xybxfcjb", new String[] { "xh", "xn",
				"xq", "yf", "jf", "kf", "sx" }, new String[] {
				xybxfModel.getXh(), xybxfModel.getXn(), xybxfModel.getXq(),
				xybxfModel.getYf(), xybxfModel.getJf(), xybxfModel.getKf(),
				DealString.toGBK(xybxfModel.getSx()) }, request);
		return bFlag;
	}
	
	/**
	 * 校园表现分的批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xybxfDel(String[] keys, HttpServletRequest request) throws Exception {
		String del = "";//删除未成功记录数
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// 得到主键
			boolean bFlag = StandardOperation.delete("xybxfcjb", "xh||xn||xq||yf", whichpk, request);
			if (!bFlag){//删除不成功
				del += (i+1);
				del += ",";
			}
		}
		return StringUtils.isNull(del) ? "" : del.substring(0, del.length() - 1);
	}
	
	/**
	 * 校园表现分学院审核结果
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xybxfShByxy(String[] keys, String sJg, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("xybxfcjb",
					new String[] { "xysh" }, new String[] { sJg },
					"xh||xn||xq||yf", whichpk, request);
			if (!bFlag) {
				shRes += (i+1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes.length() - 1);
	}
	
	/**
	 * 校园表现分学校审核结果
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xybxfShByxx(String[] keys, String sJg, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("xybxfcjb",
					new String[] { "xxsh" }, new String[] { sJg },
					"xh||xn||xq||yf", whichpk, request);
			if (!bFlag) {
				shRes += (i+1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes.length() - 1);
	}
	
	/**
	 * 学院校园表现分审核查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyXybxfshQryTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[] { "pk", "rownum", "xh", "xn", "xq",
				"xm", "bjmc", "yf", "jf", "kf", "xysh" };
		String[] cnList = new String[] { "pk", "行号", "学号", "学年", "学期", "姓名",
				"班级", "月份", "加分", "扣分", "学院审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * 学院校园表现分审核查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xxXybxfshQryTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[] { "pk", "rownum", "xh", "xn", "xq",
				"xm", "bjmc", "yf", "jf", "kf", "xxsh" };
		String[] cnList = new String[] { "pk", "行号", "学号", "学年", "学期", "姓名",
				"班级", "月份", "加分", "扣分", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * 学院校园表现分审核查询结果
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyXybxfshQryResult(XybxfModel xybxfModel) throws Exception {
		List<String[]> resList = new  ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(xybxfModel);
		String[] opList = new String[]{"pk", "rownum", "xh", "xn", "xq", "xm", "bjmc", "yf", "jf", "kf", "sh"};
		String sql = "select xh||xn||xq||yf pk,rownum,xh,xn,(select xqmc from xqdzb where xq=xqdm)xq,xm,bjmc,yf,jf,kf,xysh sh from view_xybxfcj where 1=1 ";
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 学校校园表现分审核查询结果
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxXybxfshQryResult(XybxfModel xybxfModel) throws Exception {
		List<String[]> resList = new  ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(xybxfModel);
		String[] opList = new String[]{"pk", "rownum", "xh", "xn", "xq", "xm", "bjmc", "yf", "jf", "kf", "sh"};
		String sql = "select xh||xn||xq||yf pk,rownum,xh,xn,(select xqmc from xqdzb where xq=xqdm)xq,xm,bjmc,yf,jf,kf,xxsh sh from view_xybxfcj where 1=1 and xysh='通过' ";
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 获取审核列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int type) throws Exception {
		return dao.getChkList(type);
	}
	
	/**
	 * 学院校园表现分审核查询
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xyXybxfshRes(String pkValue) throws Exception {
		String[] opList = new String[] { "xh", "xn", "xq", "bjmc", "jf",
				"kf", "nj", "xb", "xm", "xymc", "zymc", "bjmc", "sx","yf" ,"sh", "yj"};
		return dao
				.getMap(
						"select xh,xn,xq,xm,bjmc,jf,kf,nj,xb,xymc,zymc,bjmc,sx,yf,xysh sh,xyyj yj from view_xybxfcj where xh||xn||xq||yf=?",
						new String[] { pkValue }, opList);
	}
	
	/**
	 * 学校校园表现分审核查询
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xxXybxfshRes(String pkValue) throws Exception {
		String[] opList = new String[] { "xh", "xn", "xq", "xm", "bjmc", "jf",
				"kf", "nj", "xb", "xymc", "zymc", "bjmc", "sx","yf" ,"sh", "yj","xyyj"};
		return dao
				.getMap(
						"select xh,xn,xq,xm,bjmc,jf,kf,nj,xb,xymc,zymc,bjmc,sx,yf,xxsh sh,xyyj,xxyj yj from view_xybxfcj where xh||xn||xq||yf=?",
						new String[] { pkValue }, opList);
	}
	
	/**
	 * 学院单个审核校园表现分
	 * @param pkValue
	 * @param sh
	 * @param yj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xybxfShOneByXy(String pkValue, String sh, String yj,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("xybxfcjb", new String[] { "xysh",
				"xyyj" }, new String[] { sh, yj }, "xh||xn||xq||yf", pkValue,
				request);
	}
	
	/**
	 * 学校单个审核校园表现分
	 * @param pkValue
	 * @param sh
	 * @param yj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xybxfShOneByXx(String pkValue, String sh, String yj,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("xybxfcjb", new String[] { "xxsh",
				"xxyj" }, new String[] { sh, yj }, "xh||xn||xq||yf", pkValue,
				request);
	}
	
	/**
	 * 获取学生干部德育附加分查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsgbdyQryTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xh", "xn", "xq" ,"xm", "bjmc", "drzw", "jf"};
		String[] cnList = new String[]{"pk", "行号", "学号", "学年", "学期", "姓名", "班级", "担任职务", "加分"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * 获取学生干部德育附加分查询结果
	 * @param xsgbModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsgbdyQryResult(XsgbdyfjfModel xsgbModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql1(xsgbModel);
		String[] opList = new String[]{"pk", "rownum", "xh", "xn", "xq" ,"xm", "bjmc", "drzw", "jf"};
		String sql = "select xh||xn||xq||drzw pk,rownum,xh,xn,xq,xm,bjmc,drzw,jf from view_xsgbdyfjf where 1=1 ";
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 获取等级列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjList(int type) throws Exception {
		List<HashMap<String, String>> djList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		if (type == 1) {
			enList = new String[]{"A", "B", "C", "D", "E"};
		}
		djList = dao.arrayToList(enList, enList);
		return djList;
	}
	
	/**
	 * 学生干部德育附加分单个保存
	 * @param xsgbModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsgbdyfjf(XsgbdyfjfModel xsgbModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = StandardOperation.delete("xsgbdyfjfb",
				"xh||xn||xq||drzw", xsgbModel.getXh() + xsgbModel.getXn()
						+ xsgbModel.getXq() + xsgbModel.getDrzw(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("xsgbdyfjfb", new String[] { "xh",
					"xn", "xq", "drzw", "rzsj", "khdj", "jf", "bz" },
					new String[] { xsgbModel.getXh(), xsgbModel.getXn(),
							xsgbModel.getXq(),
							DealString.toGBK(xsgbModel.getDrzw()),
							xsgbModel.getRzsj(), xsgbModel.getKhdj(),
							xsgbModel.getJf(),
							DealString.toGBK(xsgbModel.getBz()) }, request);
		}
		return bFlag;
	}
	
	/**
	 * 获取学生干部德育分信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsgbdyfxx(String pkValue) throws Exception {
		String[] opList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "xq", "drzw", "rzsj", "khdj", "jf", "bz", "xn" };
		return dao
				.getMap(
						"select xh,xn,xm,xb,nj,xymc,zymc,bjmc,xq,xq,drzw,rzsj,khdj,jf,bz from view_xsgbdyfjf where xh||xn||xq||drzw = ?",
						new String[] { pkValue }, opList);
	}
	
	/**
	 * 学生干部德育分修改保存
	 * @param pkValue
	 * @param xsgbModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xsgbdyfModi(String pkValue, XsgbdyfjfModel xsgbModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = false;
		if (StringUtils.isEqual(pkValue, xsgbModel.getXh() + xsgbModel.getXn()
				+ xsgbModel.getXq() + DealString.toGBK(xsgbModel.getDrzw()))) {
			bDel = StandardOperation.delete("xsgbdyfjfb", "xh||xn||xq||drzw", pkValue, request);
		} else {
			bDel = StandardOperation.delete("xsgbdyfjfb", "xh||xn||xq||drzw", pkValue, request);
			bDel = StandardOperation.delete("xsgbdyfjfb", "xh||xn||xq||drzw",
					xsgbModel.getXh() + xsgbModel.getXn() + xsgbModel.getXq()
							+ DealString.toGBK(xsgbModel.getDrzw()), request);
		}
		if (bDel) {
			bFlag = StandardOperation.insert("xsgbdyfjfb", new String[] { "xh",
					"xn", "xq", "drzw", "rzsj", "khdj", "jf", "bz" },
					new String[] { xsgbModel.getXh(), xsgbModel.getXn(),
							xsgbModel.getXq(), DealString.toGBK(xsgbModel.getDrzw()),
							xsgbModel.getRzsj(), xsgbModel.getKhdj(),
							xsgbModel.getJf(), DealString.toGBK(xsgbModel.getBz()) }, request);
			
		}
		return bFlag;
	}
	
	/**
	 * 学生干部德育分批量删除
	 * @param keys
	 * @param request
	 * @return 记录删除不成功所返回的行数
	 * @throws Exception
	 */
	public String xsgbdyDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("xsgbdyfjfb",
					"xh||xn||xq||drzw", DealString.toGBK(keys[i]), request);
			if (!bFlag) {//删除不成功
				sDel += (i+1);
				sDel += ",";
			}
		}	
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
	
	/**
	 * 传入二个数组返回LIST查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String[] enList, String[] cnList) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (enList != null && cnList != null) {
			for (int i = 0; i < enList.length; i++) {
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("en", enList[i]);//英文名称
				tmpMap.put("cn", cnList[i]);//中文名称
				topList.add(tmpMap);
			}
		}
		return topList;
	}
	
	/**
	 * 综合素质测评查询结果
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpQryResult(ZhszcpModel zhszcpModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql2(zhszcpModel);
		String[] opList = new String[] { "pk", "rownum", "xh", "xn", "xq",
				"xm", "bjmc", "dyzf", "zyzf", "tyzf", "zhszcpzf", "zhszmc" };
		String sql = "select xh||xn||xq||nd pk,rownum,xh,xn,(select xqmc from xqdzb where xq=xqdm) xq,nd,xm,bjmc,nj,bjdm,zydm,xydm,dyzf,zyzf,tyzf,zhszcpzf,zhszmc from (select distinct xh,xn,xq,xm,nd,bjmc,dyzf,zyzf,tyzf,zhszcpzf,bjdm,nj,zydm,xydm,(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last)) zhszmc from view_zjjd_zhszcp) where 1=1 ";
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);

		return resList;
	}
	
//	获取校园表现分
	public String[] getXybxf(String xh, String[] jxjsqxnxq) throws Exception {
		String[] xybxf = null;
		String sql = "select (to_number(zjf)-to_number(zkf)) df,zjf,zkf from (select xh,xn,xq,sum(nvl(jf,'0')) zjf,sum(nvl(kf,'0')) zkf  from view_xybxfcj where xn=? and xq=? and xh=? and xysh='通过' and xxsh='通过' group by xh,xn,xq)";
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			xybxf = dao.getOneRs(sql, new String[] {
					(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
					 : "",(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
                     : "" , xh},new String[]{"df","zjf","zkf"});
		}
		return xybxf;
	}

//	获取公寓表现分
	public String[] getGybxf(String xh, String[] jxjsqxnxq) throws Exception {
		String[] gybxf = null;
		String sql = "select (to_number(zjf)-to_number(zkf)) df,zjf,zkf from (select xh,xn,xq,sum(nvl(jf,'0')) zjf,sum(nvl(kf,'0')) zkf  from view_pjpy_gybxf  where xn=? and xq=? and xh=? group by xh,xn,xq)";
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			gybxf = dao.getOneRs(sql, new String[] {
					(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
					 : "",(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
                     : "" , xh}, new String[]{"df","zjf","zkf"});
		}
		return gybxf;
	}	
	
//	德育附加分
	public String getDyfjf(String xh, String[] jxjsqxnxq) throws Exception {
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			return dao.getOneRs(
					"select jf from view_xsgbdyfjf where xn=? and xq=? and xh=? and rownum=1",
					new String[] {
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
									: "",
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
									: "", xh }, "jf");
		}
		return "";
	}
	
	//德育加分
	public String getDyjf(String xh, String[] jxjsqxnxq) throws Exception {
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			return dao.getOneRs(
					"select xh,xn,xq,sum(ryjf) jf from xsjlb where xn=? and xq=? and xh=? group by xh,xn,xq",
					new String[] {
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
									: "",
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
									: "", xh }, "jf");
		}
		return "";
	}
	
	//德育扣分
	public String getDykf(String xh, String[] jxjsqxnxq) throws Exception {
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			return dao.getOneRs(
					"select xh,xn,xq,sum(rykf) kf from xscfb where xn=? and xq=? and xh=? group by xh,xn,xq",
					new String[] {
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
									: "",
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
									: "", xh }, "kf");
		}
		return "";
	}
	
	//平均成绩和名次
	public String[] getPjcjandMc(String xh, String[] jxjsqxnxq) throws Exception {
		String[] pjcjandmc = new String[2];
//		非体育,选修课不及格扣2分
		String kf = dao.getOneRs("select count(zpcj2)*2 kf from cjb where to_number(zpcj2)<60 and kcmc not in (select distinct kcmc from cjb where kcmc like '%体育%' or kclx like '%选修%' ) and xn=? and xq=? and xh=?", new String[] {
				(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
						: "",
				(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
						: "", xh }, "kf");
		double zzkf = StringUtils.isNull(kf) ? 0 : Integer.parseInt(kf);
		String sql = "select xh, xn, xq, bjmc, pjcj, pjcjmc "
			+ "from (select xh,xn,xq,bjmc,pjcj,(dense_rank() over (partition by bjmc, xn, xq order by pjcj desc nulls last)) pjcjmc "
			+ "from (select xh,xn,xq,bjmc,trunc(avg(zpcj2),1) pjcj from (select a.xh,"
			+ "a.xn,a.xq,(select bjmc from view_xsxxb b where a.xh=b.xh )bjmc,a.kcmc,a.zpcj2,a.kclx " 
			+ "from cjb a where not exists (select distinct b.kcmc "
			+ "from cjb b where (b.kcmc like '%体育%' or b.kclx like '%选修%') and a.kcmc=b.kcmc) "
			+ ") group by xh,xn,xq,bjmc)) where xn=? and xq=? and xh=?";
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			pjcjandmc = dao.getOneRs(sql, new String[] {
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
									: "",
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
									: "", xh }, new String[]{"pjcj", "pjcjmc"});
		}
		
		if (pjcjandmc != null && pjcjandmc.length == 2) {
			double pjcj = StringUtils.isNull(pjcjandmc[0]) ? 0 : Double.parseDouble(pjcjandmc[0]);
			pjcjandmc[0] = (pjcj - zzkf) + ""; 
		}
		pjcjandmc = stringTo2bit(pjcjandmc);
		return pjcjandmc;
	}
	
	/**
	 * 计算德育总分和小计
	 * @param xybxf
	 * @param dyfjf
	 * @param dyjf
	 * @param dykf
	 * @return
	 * @throws Exception
	 */
	public String[] getDyzfandxj(String xybxzf, String gybxzf, String dyfjf) throws Exception {
		String[] dyzfandxj = new String[3];
		double fXybxf = 0;
		double fGybxzf = 0;
		double fDyfjf = 0;
		
		if (!StringUtils.isNull(xybxzf)) {
			fXybxf = Double.parseDouble(xybxzf);
		}
		if (!StringUtils.isNull(gybxzf)) {
			fGybxzf += Double.parseDouble(gybxzf);
		}
		if (!StringUtils.isNull(dyfjf)) {
			fDyfjf = Double.parseDouble(dyfjf);
		}
		
		dyzfandxj[0] = (fXybxf + fGybxzf + fDyfjf) + "";// 德育总分
		dyzfandxj[1] = ((fXybxf + fGybxzf + fDyfjf) * 30 / 100) + "";// 德育小计
		dyzfandxj[2] = "";
		dyzfandxj = stringTo2bit(dyzfandxj);
		return dyzfandxj;
	}
	
	public String[] stringTo2bit(String[] list) throws Exception {
		if (list != null && list.length>0) {
			for(int i=0;i<list.length;i++) {
				String s = list[i];
				if (!StringUtils.isNull(s) && s.indexOf(".")>0 && (s.substring(s.indexOf(".")+1, s.length())).length()>2) {
					s = s.substring(0, s.indexOf(".")+3);
					list[i] = s;
				}
			} 
		}
		return list;
	}
	
	public String stringTo2bits(String s) throws Exception {
		if (!StringUtils.isNull(s) && s.indexOf(".")>0 && (s.substring(s.indexOf(".")+1, s.length())).length()>2) {
			s = s.substring(0, s.indexOf(".")+3);
		}
		return s;
	}
	
	/**
	 * 获取学生学年学期相关表现分
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpf(String xh, String xn, String xq) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnxq = new String[]{xn,xq};
		resMap = dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh = ?",new String[] { xh });
		String[] xybxf = getXybxf(xh, jxjsqxnxq);//获取校园表现分
		String[] gybxf = getGybxf(xh, jxjsqxnxq);//公寓表现分
		String xybxzf = "";//校园表现总分
		String xybxjf = "";//校园表现加分
		String xybxkf = "";//校园表现扣分
		String gybxzf = "";//公寓表现总分
		String gybxjf = "";//公寓表现加分
		String gybxkf = "";//公寓表现扣分
		if(xybxf!=null){
			xybxzf = xybxf[0];
			xybxjf = xybxf[1]; 
			xybxkf = xybxf[2];
		}
		resMap.put("xybxf", xybxzf);
		resMap.put("xybxkf", xybxkf);
		resMap.put("xybxjf", xybxjf);
		if(gybxf!=null){
			gybxzf = gybxf[0];
			gybxjf = gybxf[1];
			gybxkf = gybxf[2];
		}
		resMap.put("gybxf", gybxzf);
		resMap.put("gybxkf", gybxkf);
		resMap.put("gybxjf", gybxjf);
		String dyfjf = getDyfjf(xh, jxjsqxnxq);//德育附加分
		//String dyjf = "0";getDyjf(xh, jxjsqxnxq);//德育加分
		//String dykf = "0";getDykf(xh, jxjsqxnxq);//德育扣分
		String[] pjcjandmc = getPjcjandMc(xh, jxjsqxnxq);//平均成绩和名次
		String[] dyzfandxj = getDyzfandxj(xybxzf, gybxzf, dyfjf);// 获取德育总分和名次
		//TODO

		resMap.put("dyfjf", dyfjf);
		if (dyzfandxj != null && dyzfandxj.length == 3) {
			resMap.put("dyzf", dyzfandxj[0]);
			resMap.put("dyxj", dyzfandxj[1]);
			//resMap.put("gybxf", dyzfandxj[2]);
		}
		if (pjcjandmc != null && pjcjandmc.length == 2) {
			resMap.put("pjcj", pjcjandmc[0]);
			resMap.put("pjcjmc", pjcjandmc[1]);
		}
		return resMap;
	}
	
	public List<String[]> getcjList(String xh, String xn, String xq) throws Exception {
		String sql = "select kcmc,zpcj2 from cjb where to_number(zpcj2)<60 " +
				"and kcmc not in (select distinct kcmc from cjb where kcmc like '%体育%' " +
				"or kclx like '%选修%' ) and xn=? and xq=? and xh=?";
		return dao.rsToVator(sql, new String[]{xn,xq,xh}, new String[]{"kcmc", "zpcj2"});
	}
	
	/**
	 * 综合素质测评保存
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpModel zhszcpModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		double fZhszcpzf = 0;
		fZhszcpzf = (!StringUtils.isNull(zhszcpModel.getDyxj()) ? Float
				.parseFloat(zhszcpModel.getDyxj()) : 0)
				+ (!StringUtils.isNull(zhszcpModel.getZyxj()) ? Float
						.parseFloat(zhszcpModel.getZyxj()) : 0)
				+ (!StringUtils.isNull(zhszcpModel.getTyxj()) ? Float
						.parseFloat(zhszcpModel.getTyxj()) : 0);
		String s = fZhszcpzf+"";
		s = stringTo2bits(s);
		zhszcpModel.setZhszcpzf(s + "");//综合素质测评总分
		boolean bDel = StandardOperation.delete("zjjd_zhszcp", "xh||xn||xq||nd",
				zhszcpModel.getXh() + zhszcpModel.getXn() + zhszcpModel.getXq()
						+ zhszcpModel.getNd(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("zjjd_zhszcp", new String[] {
					"xh", "xn", "xq", "nd", "xm", "xybxf", "gybxf", "dyfjf",
					"dyzf", "dyxj", "pjcj", "pjcjmc", "zyfjf", "zyzf", "zyxj", "tycj",
					"tyfjf", "tyzf", "tyxj", "zhszcpzf", "zhszcpmc", "bz","xybxjf","xybxkf","gybxjf","gybxkf" },
					new String[] { zhszcpModel.getXh(), zhszcpModel.getXn(),
							zhszcpModel.getXq(), zhszcpModel.getNd(), "",
							zhszcpModel.getXybxf(), zhszcpModel.getGybxf(),
							zhszcpModel.getDyfjf(), zhszcpModel.getDyzf(),
							zhszcpModel.getDyxj(), zhszcpModel.getPjcj(),
							zhszcpModel.getPjcjmc(), zhszcpModel.getZyfjf(),
							zhszcpModel.getZyzf(), zhszcpModel.getZyxj(),
							zhszcpModel.getTycj(), zhszcpModel.getTyfjf(),
							zhszcpModel.getTyzf(), zhszcpModel.getTyxj(),
							zhszcpModel.getZhszcpzf(),
							zhszcpModel.getZhszcpmc(),
							DealString.toGBK(zhszcpModel.getBz()),zhszcpModel.getXybxjf(),
							zhszcpModel.getXybxkf(),zhszcpModel.getGybxjf(),zhszcpModel.getGybxkf()},
					request);
		}
		return bFlag;
	}
	
	/**
	 * 综合素质批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("zjjd_zhszcp", "xh||xn||xq||nd", keys[i], request);
			if (!bFlag) {
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
	
	/**
	 * 综合素质测评修改显示详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpInfo(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] opList = new String[] { "xh", "xn", "xq", "nj", "xb", "zymc",
				"xymc", "xm", "bjmc", "dyxj", "zyxj", "tyxj", "zhszcpzf",
				"xybxf", "gybxf", "dyfjf", "dyzf", "pjcj", "nd",
				"pjcjmc", "zyfjf", "zyzf", "tycj", "tyfjf", "tyzf", "bz","xybxjf","xybxkf","gybxjf","gybxkf" };
		resMap = dao.getMap(
				"select * from view_zjjd_zhszcp where xh||xn||xq||nd = ?",
				new String[] { pkValue }, opList);
		return resMap;
	}
	
	/**
	 * 综合素质测评单个修改
	 * @param zhszcpModel
	 * @param requet
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpModi(String pkValue, ZhszcpModel zhszcpModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
//		boolean bDel = false;
//		if (StringUtils.isEqual(pkValue, zhszcpModel.getXh()
//				+ zhszcpModel.getXn() + zhszcpModel.getXq()
//				+ zhszcpModel.getNd())) {//未修改主键
//			bDel = StandardOperation.delete("zjjd_zhszcp", "xh||xn||xq||nd", pkValue, request);
//		} else {//修改主键
//			bDel = StandardOperation.delete("zjjd_zhszcp", "xh||xn||xq||nd", pkValue, request);
//			bDel = StandardOperation.delete("zjjd_zhszcp", "xh||xn||xq||nd", zhszcpModel.getXh()
//					+ zhszcpModel.getXn() + zhszcpModel.getXq()
//					+ zhszcpModel.getNd(), request);
//		}
//		if (bDel) {
			double fZhszcpzf = 0;
			fZhszcpzf = (!StringUtils.isNull(zhszcpModel.getDyxj()) ? Float
					.parseFloat(zhszcpModel.getDyxj()) : 0)
					+ (!StringUtils.isNull(zhszcpModel.getZyxj()) ? Float
							.parseFloat(zhszcpModel.getZyxj()) : 0)
					+ (!StringUtils.isNull(zhszcpModel.getTyxj()) ? Float
							.parseFloat(zhszcpModel.getTyxj()) : 0);
			String s = fZhszcpzf+ "";
			s= stringTo2bits(s);
			zhszcpModel.setZhszcpzf(s + "");//综合素质测评总分
			String tycj = zhszcpModel.getTycj();
			String tyfjf=zhszcpModel.getTyfjf();
			tycj=Base.isNull(tycj)?"0":tycj;
			tyfjf=Base.isNull(tyfjf)?"0":tyfjf;
			String tyzf = String.valueOf(Integer.parseInt(tycj)+ Integer.parseInt(tyfjf));
			bFlag = StandardOperation.update("zjjd_zhszcp", new String[] {"zyfjf", "tycj","tyfjf","bz","tyzf" },
					new String[] { zhszcpModel.getZyfjf(),zhszcpModel.getTycj(), zhszcpModel.getTyfjf(),
							DealString.toGBK(zhszcpModel.getBz()),tyzf },"xh||xn||xq||nd",pkValue,request);
//		}
		return bFlag;
	}
	
	/**
	 * 综合素质测评数据导出查询结果
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zhszcpDataExpQry(ZhszcpModel zhszcpModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql2(zhszcpModel);
		String[] opList = new String[] { "xn", "xq", "bjmc", "xh", "xm",
				"xybxf", "gybxf", "dyfjf", "dyzf", "dyxj", "pjcj", "pjcjmc",
				"zyfjf", "zyzf", "zyxj", "tycj", "tyfjf", "tyzf", "tyxj",
				"zhszcpzf", "zhszcpmc", "bz" };
		resList = dao
				.rsToVator(
						"select distinct nd,xn,xq,bjmc,xh,xm,xybxf,gybxf,dyfjf,dyzf,dyxj,pjcj,pjcjmc,zyfjf,zyzf,zyxj,tycj,tyfjf,tyzf,tyxj,zhszcpzf,(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last)) zhszcpmc,bz from view_zjjd_zhszcp where 1=1 "
				+ whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}

	/**
	 * 获取个人奖学金评定信息
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = dao
				.getMap(
						"select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
						new String[] { jxjpdModel.getXh() },
						new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
								"bjmc" });
		
		String[] opList = new String[] { "zhszcpzf", "tycj", "dyzf", "zhszmc"};
		String sql = "select tycj,dyzf,zhszcpzf,zhszmc from (select xh,xn,xq,nd,tycj,dyzf,zhszcpzf,(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last) ) zhszmc from view_zjjd_zhszcp ) where xh=? and xn=? and xq=? and nd=?";
		String[] tempList = dao.getOneRs(sql, new String[] { jxjpdModel.getXh(),
				jxjpdModel.getXn(), jxjpdModel.getXq(), jxjpdModel.getNd()}, opList);
		if (tempList != null && tempList.length == 4) {
			resMap.put("tycj", tempList[1]);
			resMap.put("dyzf", tempList[2]);
			resMap.put("zhszcpzf", tempList[0]);
			resMap.put("zhszmc", tempList[3]);
		}
		
		String dkzdcj = dao
				.getOneRs(
						"select xh,xn,xq,min(zpcj2) dkzdcj from cjb where kcmc not in (select distinct kcmc from cjb where kcmc like '%体育%' or kclx like '%选修%') and xh=? and xn=? and xq=? group by xh,xn,xq",
						new String[] {jxjpdModel.getXh(),
								jxjpdModel.getXn(), jxjpdModel.getXq()}, "dkzdcj");
		resMap.put("dkzdcj", dkzdcj);
		return resMap;
	}
	
	/**
	 * 获取学期代码
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) throws Exception {
		String sql= "select xqdm from xqdzb where xqmc = ?";
		return dao.getOneRs(sql,new String[]{xqdm},"xqdm");
	}
	/**
	 * 奖学金申请保存
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjpdxxModel jxjpdModel, HttpServletRequest request) throws Exception {
		boolean bDel = StandardOperation.delete("xsjxjb", "xh||xn||xq||nd||jxjdm",
				jxjpdModel.getXh() + jxjpdModel.getXn() + jxjpdModel.getXq()
						+ jxjpdModel.getNd() + jxjpdModel.getJxjdm(), request);
		if (bDel) {
			return StandardOperation.insert("xsjxjb", new String[] { "xh",
					"xn", "xq", "nd", "jxjdm", "tzjkbzdj", "bjpddj", "szxyj",
					"fdyyj" },
					new String[] { jxjpdModel.getXh(), jxjpdModel.getXn(),
							jxjpdModel.getXq(), jxjpdModel.getNd(),
					jxjpdModel.getJxjdm(), DealString.toGBK(jxjpdModel.getTzjkbzdj()),
					jxjpdModel.getBjpddj(), DealString.toGBK(jxjpdModel.getSzxyj()),
					DealString.toGBK(jxjpdModel.getFdyyj()) }, request);
		} 
		return false;
	}
	
	/**
	 * 获取奖学金申请学年，年度，学期
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqxnxqnd() throws Exception {
		return dao.getOneRs("select jxjsqxn,jxjsqxq,jxjsqnd from xtszb where rownum=1", new String[] {},
				new String[] { "jxjsqxn", "jxjsqxq", "jxjsqnd" });
	}
	
	/**
	 * 奖学金查询结果
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxjsqQryResult(JxjpdxxModel jxjpdModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opList = new String[] { "pk", "rownum", "nd", "xn", "xh",
				"xq", "xm", "bjmc", "jxjmc", "xysh", "xxsh" };
		String sql = "select xh||xn||xq||nd||jxjdm pk,rownum,nd,xn,xh,(select xqmc from xqdzb where xq = xqdm) xq,xm,bjmc,jxjmc,xysh,xxsh from view_xsjxjb where 1=1 ";
		StringBuffer whereSql = getWhereSql3(jxjpdModel);
		resList = dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 奖学金批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String jxjDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("xsjxjb", "xh||xn||xq||nd||jxjdm", keys[i], request);
			if (!bFlag) {//记录删除失败的数据行
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
	
	/**
	 * 奖学金修改显示详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjModixx(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = dao
				.getMap(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,(select xqmc from xqdzb where xq = xqdm) xq,nd,xn,jxjdm,bjpddj,szxyj,fdyyj,tzjkbzdj from view_xsjxjb where xh||xn||xq||nd||jxjdm=?",
						new String[] { pkValue },
						new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
								"bjmc","xn","xq","nd","jxjdm","bjpddj","szxyj","fdyyj","tzjkbzdj" });
		
		String[] opList = new String[] { "zhszcpzf", "tycj", "dyzf", "zhszmc"};
		String sql = "select tycj,dyzf,zhszcpzf,zhszmc from (select xh,xn,xq,nd,tycj,dyzf,zhszcpzf,(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last) ) zhszmc from view_zjjd_zhszcp ) where xh=? and xn=? and xq=? and nd=?";
		String[] tempList = dao.getOneRs(sql, new String[] { resMap.get("xh"),
				resMap.get("xn"), resMap.get("xq"), resMap.get("nd")}, opList);
		if (tempList != null && tempList.length == 4) {
			resMap.put("tycj", tempList[1]);
			resMap.put("dyzf", tempList[2]);
			resMap.put("zhszcpzf", tempList[0]);
			resMap.put("zhszmc", tempList[3]);
		}
		String dkzdcj = dao
		.getOneRs(
				"select xh,xn,xq,min(zpcj2) dkzdcj from cjb where kcmc not in (select distinct kcmc from cjb where kcmc like '%体育%' or kclx like '%选修%') and xh=? and xn=? and xq=? group by xh,xn,xq",
				new String[] {resMap.get("xh"),
						resMap.get("xn"), resMap.get("xq")}, "dkzdcj");
		resMap.put("dkzdcj", dkzdcj);
		return resMap;
	}
	
	/**
	 * 奖学金修改保存
	 * @param pkValue
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjModi(String pkValue, JxjpdxxModel jxjpdModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = false;
		if (StringUtils.isEqual(pkValue, jxjpdModel.getXh()
				+ jxjpdModel.getXn() + jxjpdModel.getXq() + jxjpdModel.getNd()
				+ jxjpdModel.getJxjdm())) {
			bDel = StandardOperation.delete("xsjxjb", "xh||xn||xq||nd||jxjdm", pkValue, request);
		} else {
			bDel = StandardOperation.delete("xsjxjb", "xh||xn||xq||nd||jxjdm", pkValue, request);
			bDel = StandardOperation.delete("xsjxjb", "xh||xn||xq||nd||jxjdm", jxjpdModel.getXh()
					+ jxjpdModel.getXn() + jxjpdModel.getXq() + jxjpdModel.getNd()
					+ jxjpdModel.getJxjdm(), request);
		}
		if (bDel) {
			bFlag = StandardOperation.insert("xsjxjb", new String[] { "xh",
					"xn", "xq", "nd", "jxjdm", "tzjkbzdj", "bjpddj", "szxyj",
			"fdyyj" },
			new String[] { jxjpdModel.getXh(), jxjpdModel.getXn(),
					jxjpdModel.getXq(), jxjpdModel.getNd(),
			jxjpdModel.getJxjdm(), DealString.toGBK(jxjpdModel.getTzjkbzdj()),
			jxjpdModel.getBjpddj(), DealString.toGBK(jxjpdModel.getSzxyj()),
			DealString.toGBK(jxjpdModel.getFdyyj()) }, request);
		}
		return bFlag;
	}
	
	/**
	 * 奖学金导出数据
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> jxjExpData(JxjpdxxModel jxjpdModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql5(jxjpdModel);
		// String[] opList = new String[] { "bjmc", "xn", "xq", "xm",
		// "zhszcpzf",
		// "zhszmc", "dkzdcj", "tycj", "tzjkbzdj", "dyzf", "jxjmc",
		// "bjpddj", "szxyj", "fdyyj" };
		String[] opList = new String[] { "bjmc", "xn", "xq", "xm", "zhszcpzf",
				"zhszmc", "dkzdcj", "tycj", "tzjkbzdj", "dyzf", "jxjmc",
				"bjpddj", "szxyj", "yhmc", "yhkh","bz" };
		
		String sql = "select '' bz,(select v.yhmc from view_xsxxb v where a.xh = v.xh) yhmc,"
				+ "(select v.yhkh from view_xsxxb v where a.xh = v.xh) yhkh,"
				+ " a.jxjmc,a.xh,a.xq,a.nd,a.zydm,a.bjdm,a.xydm,a.xn,a.xm,a.bjmc,"
				+ "a.tzjkbzdj,a.bjpddj,a.szxyj,a.fdyyj,b.zhszcpzf,b.zhszmc,b.dkzdcj,b.tycj,"
				+ "b.dyzf from view_xsjxjb a left join (select jxjmc,xh,xq,nd,zydm,bjdm,xydm,"
				+ "bjmc,xn,xm,zhszcpzf,zhszmc,dkzdcj,tycj,tzjkbzdj,dyzf,bjpddj,szxyj,fdyyj "
				+ "from (select a.nj,a.xydm,a.xh,a.zydm,a.nd,a.bjmc,a.xn,a.xq,a.xm,a.tzjkbzdj,"
				+ "a.jxjmc,a.bjpddj,a.szxyj,a.fdyyj,a.bjdm,b.tycj,b.dyzf,b.zhszcpzf,"
				+ "(select zhszmc from (select bjdm,xn,xq,nd,xh,"
				+ "(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last)) zhszmc "
				+ "from view_zjjd_zhszcp) b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) zhszmc,"
				+ "(select min(b.zpcj2) dkzdcj from cjb b "
				+ "where b.kcmc not in (select distinct kcmc from cjb where kcmc like '%体育%' or kclx like '%选修%') "
				+ "and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq group by b.xh,b.xn,b.xq) dkzdcj "
				+ "from view_xsjxjb a,view_zjjd_zhszcp b where a.xh=b.xh and a.xn=b.xn "
				+ "and a.xq=b.xq and a.nd=b.nd)) b on a.xh=b.xh and a.xq=b.xq and a.nd=b.nd and a.xn=b.xn where 1=1 ";
		resList = dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 获取班级人数
	 * @param bjmc
	 * @return
	 * @throws Exception
	 */
	public String getBjrs(String bjmc) throws Exception {
		return dao.getOneRs("select count(*) num from view_xsjbxx where bjmc = ?", new String[]{bjmc}, "num");
	}
	
	/**
	 * 奖学金审核是否通过
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public String[] jxjshResult(String pkValue) throws Exception {
		return dao.getOneRs("select xysh,xxsh from xsjxjb where xh||xn||xq||nd||jxjdm = ?", new String[]{pkValue}, new String[]{"xysh", "xxsh"});
	}
	
	/**
	 * 荣誉称号评选条件
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxtj(String xh) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnndxq = getJxjsqxnxqnd();
		String[] opList = new String[] { "xh",
				"xm", "xb", "nj", "xymc", "zymc", "bjmc" };
		resMap = dao.getMap("select xh,xm,nj,xymc,xb,zymc,bjmc from view_xsjbxx where xh=?", new String[]{xh}, opList);
		String[] dztList = dao.getOneRs(
						"select b.zhszcpzf,b.tycj,b.zhszcpcjpm zhszpm  from view_zjjd_zhszcp b where b.xh=? and b.xn=? and b.xq=? and b.nd=?",
						new String[] { xh,
								jxjsqxnndxq != null ? jxjsqxnndxq[0] : "",
								jxjsqxnndxq != null ? jxjsqxnndxq[1] : "",
								jxjsqxnndxq != null ? jxjsqxnndxq[2] : "" },
						new String[]{"zhszcpzf", "tycj", "zhszpm"});
		if (dztList != null) {
			resMap.put("zhszcpzf", dztList[0]);
			resMap.put("tycj", dztList[1]);
			resMap.put("zhszpm", dztList[2]);
		}
		String dyzypjcj = dao.getOneRs("select xh,xn,trunc(avg(dyzf),1) dypjcj from view_zjjd_zhszcp where xh=? and xn=? group by xh,xn", new String[] { xh,
				jxjsqxnndxq != null ? jxjsqxnndxq[0] : "" }, "dypjcj");
		resMap.put("dypjcj", dyzypjcj);//学年内德育平均成绩
		String zypjcj = dao.getOneRs("select xh,xn,trunc(avg(zpcj2),1) zypjcj from cjb where xh=? and xn=? group by xh,xn", new String[]{xh,
				jxjsqxnndxq != null ? jxjsqxnndxq[0] : ""}, "zypjcj");
		resMap.put("zypjcj", zypjcj);//学年内智育平均成绩
		return resMap;
	}
	
	/**
	 * 荣誉称号保存
	 */
	public boolean rychSave(RychModel rychModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = StandardOperation.delete("xsrychb",
				"xh||xn||xq||nd||rychdm", rychModel.getXh() + rychModel.getXn()
						+ rychModel.getXq() + rychModel.getNd()
						+ rychModel.getRychdm(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("xsrychb",
					new String[] { "xh", "xn", "xq", "nd", "rychdm", "wydj",
							"jsjdj", "bjpddj", "bz" }, new String[] {
							rychModel.getXh(), rychModel.getXn(),
							rychModel.getXq(), rychModel.getNd(),
							rychModel.getRychdm(),
							DealString.toGBK(rychModel.getWydj()),
							DealString.toGBK(rychModel.getJsjdj()),
							rychModel.getBjpddj(),
							DealString.toGBK(rychModel.getBz()) }, request);
		}
		return bFlag;
	}
	
	/**
	 * 荣誉称号查询结果
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> rychQryResult(RychModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql4(rychModel);
		String sql = "select xh||xn||xq||nd||rychdm pk,rownum,nd,xn,xh,(select xqmc from xqdzb where xq = xqdm) xq,xm,bjmc,rychmc,xysh,xxsh from view_xsrychb where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "nd", "xn", "xh", "xq", "xm", "bjmc","rychmc", "xysh", "xxsh"};
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 荣誉称号修改显示详细信息
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychXx(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] opList = new String[]{"xh", "xn", "xq", "nd", "rychdm", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "wydj", "jsjdj", "bjpddj", "bz"};
		resMap = dao
				.getMap(
						"select xh,xn,(select xqmc from xqdzb where xq = xqdm) xq,nd,rychdm,xm,xb,nj,xymc,zymc,bjmc,wydj,jsjdj,bjpddj,bz from view_xsrychb where xh||xn||xq||nd||rychdm=?",
						new String[] { pkValue }, opList);
		if (resMap != null) {
			String[] tmpList = dao
					.getOneRs(
							"select xh,xn,xq,nd,zhszcpzf,zhszpm,tycj from (select xh,xn,xq,nd,zhszcpzf,tycj,(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last)) zhszpm from view_zjjd_zhszcp) where xh=? and xn=? and xq=? and nd=?",
							new String[] { resMap.get("xh"), resMap.get("xn"),
									resMap.get("xq"), resMap.get("nd") },
							new String[] { "zhszcpzf", "zhszpm", "tycj" });
			if (tmpList != null && tmpList.length == 3) {
				resMap.put("zhszcpzf", tmpList[0]);
				resMap.put("zhszpm", tmpList[1]);
				resMap.put("tycj", tmpList[2]);
			}
			String dyzypjcj = dao.getOneRs("select xh,xn,trunc(avg(dyzf),1) dypjcj from view_zjjd_zhszcp where xh=? and xn=? group by xh,xn", new String[] { resMap.get("xh"),
					resMap.get("xn") }, "dypjcj");
			resMap.put("dypjcj", dyzypjcj);//学年内德育平均成绩
			String zypjcj = dao.getOneRs("select xh,xn,trunc(avg(zpcj2),1) zypjcj from cjb where xh=? and xn=? group by xh,xn", new String[]{resMap.get("xh"),
					resMap.get("xn")}, "zypjcj");
			resMap.put("zypjcj", zypjcj);//学年内智育平均成绩
		}
		return resMap;
	}
	
	/**
	 * 荣誉称号修改
	 * @param rychModel
	 * @param pkVlue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean rychModi(RychModel rychModel, String pkValue ,HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = false;
		if (StringUtils
				.isEqual(pkValue, rychModel.getXh() + rychModel.getXn()
						+ rychModel.getXq() + rychModel.getNd()
						+ rychModel.getRychdm())) {
			bDel = StandardOperation.delete("xsrychb", "xh||xn||xq||nd||rychdm", pkValue, request);
		} else {
			bDel = StandardOperation.delete("xsrychb",
					"xh||xn||xq||nd||rychdm", pkValue, request);
			bDel = StandardOperation.delete("xsrychb",
					"xh||xn||xq||nd||rychdm", rychModel.getXh()
							+ rychModel.getXn() + rychModel.getXq()
							+ rychModel.getNd() + rychModel.getRychdm(),
					request);
		}
		if (bDel) {
			bFlag = StandardOperation.insert("xsrychb",
					new String[] { "xh", "xn", "xq", "nd", "rychdm", "wydj",
							"jsjdj", "bjpddj", "bz" }, new String[] {
							rychModel.getXh(), rychModel.getXn(),
							rychModel.getXq(), rychModel.getNd(),
							rychModel.getRychdm(),
							DealString.toGBK(rychModel.getWydj()),
							DealString.toGBK(rychModel.getJsjdj()),
							rychModel.getBjpddj(),
							DealString.toGBK(rychModel.getBz()) }, request);
		}
		return bFlag;
	}
	
	/**
	 * 荣誉称号批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bDel = StandardOperation.delete("xsrychb", "xh||xn||xq||nd||rychdm", keys[i], request);
			if (!bDel) {
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}

	
	/**
	 * 学生奖学金查询信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuJxjSqxx(String xh) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||xn||xq||nd||jxjdm pk, rownum,xn,xq,nd,jxjmc,xysh,xxsh from view_xsjxjb where xh= ?";
		resList = dao.rsToVator(sql, new String[]{xh}, new String[]{"pk","rownum", "xn", "xq", "nd", "jxjmc", "xysh", "xxsh"});
		return resList;
	}
	
	//是否存学生成绩
	public boolean stuCjFlag(String xh, String xn, String xq) throws Exception {
		boolean bFlag = false;
		String[] input = {xh};
		String sql = "select count(*) num from cjb where xh=?";		
		if(StringUtils.isNotNull(xn)){
			 sql += " and xn=?";
			 input = StringUtils.joinStrArr(input,new String[]{xn});
		}
		if(StringUtils.isNotNull(xq)){
			 sql += " and xq=?";
			 input = StringUtils.joinStrArr(input,new String[]{xq});
		}
		
		String num = dao.getOneRs(sql, input, "num");
		if (!StringUtils.isNull(num) && !StringUtils.isEqual(num, "0")) {
			bFlag = true;
		}
		return bFlag;
	}
	
	//保存公寓表现分
	public boolean saveGybxf(PjpyZjjdActionForm myForm,
			HttpServletRequest request) throws Exception {

		// 学号
		String xh = myForm.getXh();
		// 学年
		String xn = myForm.getXn();
		// 学期
		String xq = myForm.getXq();
		// 日期
		String rq = myForm.getRq();
		// 加分
		String jf = Base.isNull(myForm.getJf())?"0":myForm.getJf();
		// 扣分
		String kf = Base.isNull(myForm.getKf())?"0":myForm.getKf();
		// 事项
		String sx = DealString.toGBK(myForm.getSx());

		String tableName = "gybxfcjb";
		String primaryKey = "xh||xn||xq||rq";
		String value = xh + xn + xq + rq;

		boolean bFlag = StandardOperation.delete(tableName, primaryKey, value,
				request);
		if (bFlag) {
			StandardOperation.insert(tableName, new String[] { "xh", "xn",
					"xq", "rq", "jf", "kf", "sx" }, new String[] { xh, xn, xq,
					rq, jf, kf, sx }, request);
		}
		return bFlag;
	}
	
	//	公寓表现分信息List
	public Vector<Object> getGybxfList(PjpyZjjdActionForm myForm) {

		DAO dao = DAO.getInstance();
		Vector<Object> vector = new Vector<Object>();
		
		String nj = myForm.getNj();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String xh = DealString.toGBK(myForm.getXh());
		String xm = DealString.toGBK(myForm.getXm());
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String bjdm = myForm.getBjdm();

		StringBuffer query = new StringBuffer();
		
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj)?" and 1=1":" and nj = '"+nj+"'");
		query.append("".equalsIgnoreCase(xn)?" and 1=1":" and xn = '"+xn+"'");
		query.append("".equalsIgnoreCase(xq)?" and 1=1":" and xq = '"+xq+"'");
		query.append("".equalsIgnoreCase(xh)?" and 1=1":" and xh = '"+xh+"'");
		query.append("".equalsIgnoreCase(xm)?" and 1=1":" and xm like '%"+xm+"%'");
		query.append("".equalsIgnoreCase(xydm)?" and 1=1":" and xydm = '"+xydm+"'");
		query.append("".equalsIgnoreCase(zydm)?" and 1=1":" and zydm = '"+zydm+"'");
		query.append("".equalsIgnoreCase(bjdm)?" and 1=1":" and bjdm = '"+bjdm+"'");
		
		String[] colList = new String[] { "pk","xn","xqmc", "xh", "xm", "xymc", "zymc",
				"bjmc", "rq" };

		String sql = "select * from (select * from (select distinct xh||xn||xq||rq pk,rownum r, xn,xqmc,xh,xm,xymc,zymc,bjmc,rq"
				+ " from view_pjpy_gybxf "
				+ query
				+ " ) where r<="
				+ (myForm.getPages().getStart() + myForm.getPages()
						.getPageSize())
				+ ") where r>"
				+ myForm.getPages().getStart();

		vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
		return vector;
	}
	
	// 删除公寓表现分
	public boolean delGybxf(String pk, HttpServletRequest request)
			throws Exception {
		String tableName = "gybxfcjb";
		String primaryKey = "xh||xn||xq||rq";
		boolean bFlag = StandardOperation.delete(tableName, primaryKey, pk,
				request);

		return bFlag;
	}
	
	//	获取寓表现分
	public HashMap<String, String> getGybxfOne(String pk) throws Exception {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "xh", "xn", "xq", "xb", "xm", "xymc",
				"zymc", "bjmc", "rq", "jf", "kf", "sx","nj" };
		String sql = "select * from view_pjpy_gybxf where xh || xn || xq || rq = ?";
		HashMap<String, String> rs = dao.getMap(sql, new String[] { pk },
				colList);
		return rs;
	}
}
