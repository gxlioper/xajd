
package xgxt.pjpy.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学评奖评优DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class PjpyJgsdxDAO {

	DAO dao = DAO.getInstance();//公用数据操作DAO
	List<String> values = new ArrayList<String>();//存储查询条件值
	String rownumzd = "";//自动审核时的字段
	
	/**
	 * 获取查询条件及值
	 * getwheresql ---- 获取查询条件 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(WmbjSqModel xjbjQryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xjbjQryModel.getXn();
		String shxm = xjbjQryModel.getShxm();
		String xydm = xjbjQryModel.getXydm();
		String zydm = xjbjQryModel.getZydm();
		String bjdm = xjbjQryModel.getBjdm();
		String xq = xjbjQryModel.getXq();
		String rychdm = getRychdm("文明班级");//获取荣誉称号代码
		shxm = !StringUtils.isNull(shxm) && StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(shxm)) {
			whereSql.append(" and rychdm = ?");
			values.add(shxm);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}//end if
		return whereSql;
	}
	
	/**
	 * 获取专业奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZyjxjList() throws Exception {
		List<HashMap<String, String>> zyjxjList = new ArrayList<HashMap<String, String>>();
		String sql = "select jxjdm,jxjmc from zyjxjdmb";
		zyjxjList = dao.getList(sql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
		return zyjxjList;
	}
	
	/**
	 * 通过奖学金代码获取奖学金额
	 * getJxjJeByJxjdm ---- 通过奖学金代码获取奖学金额 
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String getJxjJeByJxjdm(String jxjdm) throws Exception {
		String jlje = "";
		String sql = "select jlje from zyjxjdmb where jxjdm = ?";
		String[] tmpList = dao.getOneRs(sql, new String[]{jxjdm}, new String[]{"jlje"});
		if (tmpList != null && tmpList.length > 0) {
			jlje = tmpList[0];
		}//end if
		return jlje;
	}
	
	/**
	 * 辅导员查询专业奖学金表头
	 * fdyZyjxjsbTitle ---- 辅导员查询专业奖学金表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyZyjxjsbTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] opCols = {"jxjdm","xh||xn||jxjdm", "xh", "xm", "xn","xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "fdysh"};
		String[] cnCols = {"jxjdm","主键", "学号", "姓名","学年", "学院名称", "专业名称", "班级名称", "综合素质排名","学习成绩排名","奖学金级别","辅导员审核" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			topList.add(temp);
		}
		return topList;
	}
	
	/**
	 * 学院查询专业奖学金表头
	 * XyZyjxjsbTitle ---- 学院查询专业奖学金表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyZyjxjsbTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] opCols = {"jxjdm","xh||xn||jxjdm", "xh", "xm", "xn", "xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xysh"};
		String[] cnCols = {"jxjdm","主键", "学号", "姓名", "学年", "学院名称", "专业名称", "班级名称", "综合素质排名","学习成绩排名","奖学金级别","学院审核" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			topList.add(temp);
		}
		return topList;
	}
	
	/**
	 * 学校查询专业奖学金表头
	 * XxZyjxjsbTitle ---- 学校查询专业奖学金表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xxZyjxjsbTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] opCols = {"jxjdm","xh||xn||jxjdm", "xh", "xm", "xn","xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xxsh"};
		String[] cnCols = {"jxjdm","主键", "学号", "姓名","学年", "学院名称", "专业名称", "班级名称", "综合素质排名","学习成绩排名","奖学金级别","学校审核" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			topList.add(temp);
		}
		return topList;
	}
	
	/**
	 * 辅导员查询专业奖学金结果
	 * fdyZyjxjsbResult ---- 辅导员查询专业奖学金结果 
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> fdyZyjxjsbResult(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opCols = {"jxjdm","key", "xh", "xm","xn", "xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "fdysh"};
		String sql = "select '"+zyjxjModel.getJxjdm()+"' jxjdm,a.xh||b.xn||b.jxjdm key,a.xh,a.xm,'"+zyjxjModel.getXn()+"' xn,a.xymc,a.zymc,a.bjmc,b.zhszpm,b.xxcjpm,b.jxjjb,b.fdysh from view_xsjbxx a left join view_xszyjxj b on a.xh=b.xh and b.xn=? and b.jxjdm=? where 1=1 and a.xydm='"+zyjxjModel.getXydm()+"'";
		StringBuffer whereSql = getWhereSql(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opCols);
		System.out.println(values.size());
		System.out.println(sql+whereSql);
		return resList;
	}
	
	/**
	 * 学院查询专业奖学金结果
	 * xyZyjxjsbResult ---- 学院查询专业奖学金结果 
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyZyjxjsbResult(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opCols = {"jxjdm","a.xh||a.xn||a.jxjdm", "xh", "xm","xn", "xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xysh"};
		String sql = "select a.jxjdm,a.xh||a.xn||a.jxjdm,a.xh,a.xm,a.xn,a.xymc,a.zymc,a.bjmc,a.zhszpm,a.xxcjpm,a.jxjjb,a.xysh from view_xszyjxj a where 1=1 and a.fdysh='通过' and a.xn=? and a.jxjdm=? ";
		StringBuffer whereSql = getWhereSql(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opCols);
		System.out.println(sql+whereSql);
		return resList;
	}
	
	/**
	 * 学校查询专业奖学金结果
	 * xyZyjxjsbResult ---- 学校查询专业奖学金结果 
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxZyjxjsbResult(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opCols = {"jxjdm","a.xh||a.xn||a.jxjdm", "xh", "xm", "xn","xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xxsh"};
		String sql = "select a.jxjdm,a.xh||a.xn||a.jxjdm,a.xh,a.xm,a.xn,a.xymc,a.zymc,a.bjmc,a.zhszpm,a.xxcjpm,a.jxjjb,a.xxsh from view_xszyjxj a where 1=1 and a.fdysh='通过' and a.xysh='通过' and a.xn=? and a.jxjdm=?";
		StringBuffer whereSql = getWhereSql(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opCols);
		return resList;
	}
	
	/**
	 * 公用方法用于获取查询条件值
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(ZyjxjQryModel zyjxjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = zyjxjModel.getXn();
		String nj = zyjxjModel.getNj();
		String jxjdm = zyjxjModel.getJxjdm();
		String bjdm = zyjxjModel.getBjdm();
		String xydm = zyjxjModel.getXydm();
		String zydm = zyjxjModel.getZydm();
		if (!StringUtils.isNull(xn)) {//学年必选项
			//whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(jxjdm)) {//学年必选项
			//whereSql.append(" and jxjdm = ?");
			values.add(jxjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and a.nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and a.bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and a.xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and a.zydm = ?");
			values.add(zydm);
		}
		return whereSql;
	}
	
	/**
	 * 通过主键获取综合素质和学习成绩(主键可能是学号，也可能是学号加学年加奖学金代码)
	 * getZhszandXxcjByPk ---- 通过主键获取综合素质和学习成绩
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszandXxcjByPk(String pkValue, String xh) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "";
		//检查表中是否存在相应记录
		boolean[] bExists = dao.checkExists("view_xszyjxj", "xh||xn||jxjdm", new String[]{pkValue});
		if (bExists != null && bExists.length > 0) {
			if (bExists[0]) {//存在该记录
				sql = "select xh,xm,zhszpm,xxcjpm,bz,sfsf,xn,xq,fdysh from view_xszyjxj where xh||xn||jxjdm=?";
				resMap = dao.getMapNotOut(sql, new String[]{pkValue});
			}else {//不存在则只取学号，姓名
				sql = "select xh,xm from view_xsjbxx where xh = ?";
				resMap = dao.getMapNotOut(sql, new String[]{xh});
			}
		}
		return resMap;
	}
	
	/**
	 * 综合素质和学习成绩保存
	 * zhszandxxcjSave ---- 综合素质和学习成绩保存
	 * @param zxsaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszandxxcjSave(ZhszandXxcjSaveModel zxsaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String zhszpm = DealString.toGBK(zxsaveModel.getZhszpm());
		String xxcjpm = DealString.toGBK(zxsaveModel.getXxcjpm());
		String bz = DealString.toGBK(zxsaveModel.getBz());
		String sfsf = DealString.toGBK(zxsaveModel.getSfsf());
		String xh = DealString.toGBK(zxsaveModel.getXh());
		String pkValue = DealString.toGBK(zxsaveModel.getPkValue());
		String xq = zxsaveModel.getXq();
		String xn = zxsaveModel.getXn();
		String jxjdm = zxsaveModel.getJxjdm();
		String shzt = DealString.toGBK(zxsaveModel.getShzt());
		boolean bDel = StandardOperation.delete("xszyjxjb", "xh||xn||jxjdm", pkValue, request);
		if (bDel) {
			bFlag = StandardOperation.insert("xszyjxjb", new String[] { "xh", "xn",
					"jxjdm", "xq", "zhszpm", "xxcjpm", "bz", "sfsf", "fdysh" },
					new String[]{xh,xn,jxjdm,xq,zhszpm,xxcjpm,bz,sfsf,shzt}, request);
		}
		return bFlag;
	}
	
	/**
	 * 辅导员审核专业奖学金
	 * @param zyjxjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] zyjxjShByFdy(ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		String[] cbv = zyjxjModel.getCbv();
		String res = zyjxjModel.getRes();
		String xn = zyjxjModel.getXn();
		String jxjdm = zyjxjModel.getJxjdm();
		res = StringUtils.isEqual(res, "tg") ? "通过" : (StringUtils.isEqual(res, "btg") ? "不通过" : "未审核");
		boolean[] result = new boolean[cbv.length];
		String tableName = "xszyjxjb";
		String pk = "xh||xn||jxjdm";
		String insertSql = "insert into " + tableName + " (xh,xn,jxjdm,fdysh) values (?,?,?,?)";
		String updateSql = "update " + tableName + " set fdysh = ? where " + pk + " = ?";
		boolean[] exists = dao.checkExists(tableName, pk, cbv);
		for (int i = 0 ; i < exists.length; i++) {
			if (exists[i]) {//存在记录进行修改
				String[] input = new String[]{res, cbv[i]};
				result[i] = dao.runUpdate(updateSql, input);
			}else {//不存在进行插入
				String[] input = {cbv[i],xn,jxjdm,res};
				result[i]      = dao.runUpdate(insertSql, input);
			}
		}
		return result;
	}
	
	/**
	 * 学院审核专业奖学金
	 * @param zyjxjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] zyjxjShByXy(ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		String[] cbv = zyjxjModel.getCbv();
		String res = zyjxjModel.getRes();
		res = StringUtils.isEqual(res, "tg") ? "通过" : (StringUtils.isEqual(res, "btg") ? "不通过" : "未审核");
		boolean[] result = new boolean[cbv.length];
		for (int i = 0; i < cbv.length; i++) {
			result[i] = StandardOperation.update("xszyjxjb", new String[]{"xysh"}, new String[]{res}, "xh||xn||jxjdm", cbv[i], request);
		}
		return result;
	}
	
	/**
	 * 学校审核专业奖学金
	 * @param zyjxjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] zyjxjShByXx(ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		String[] cbv = zyjxjModel.getCbv();
		String res = zyjxjModel.getRes();
		res = StringUtils.isEqual(res, "tg") ? "通过" : (StringUtils.isEqual(res, "btg") ? "不通过" : "未审核");
		boolean[] result = new boolean[cbv.length];
		for (int i = 0; i < cbv.length; i++) {
			result[i] = StandardOperation.update("xszyjxjb", new String[]{"xxsh"}, new String[]{res}, "xh||xn||jxjdm", cbv[i], request);
		}
		return result;
	}
	
	/**
	 * 返回审核列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List getChkList(int type) throws Exception {
		return dao.getChkList(type);
	}
	
	/**
	 * 专业奖学金批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String zyjxjblDel(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from xszyjxjb where xh||xn||jxjdm = '" + whichxh + "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * 获取查询列表
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(ZyjxjQryModel zyjxjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = zyjxjModel.getXn();
		String jxjdm = zyjxjModel.getJxjdm();
		String nj = zyjxjModel.getNj();
		String xydm = zyjxjModel.getXydm();
		String zydm = zyjxjModel.getZydm();
		String bjdm = zyjxjModel.getBjdm();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}if (!StringUtils.isNull(jxjdm)) {
			whereSql.append(" and jxjdm = ?");
			values.add(jxjdm);
		}if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		return whereSql;
	}
	
	/**
	 * 专业奖学金打印列表
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zyjxjPrint(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opList = new String[]{"xymc||zymc||bjmc","xh","xm","zhszpm","xxcjpm","jxjmc","jlje"};
		String sql = "select xymc||zymc||bjmc,xh,xm,zhszpm,xxcjpm,jxjmc,jlje from view_xszyjxj where 1=1 and fdysh='通过' and xysh='通过' and xxsh='通过' ";
		StringBuffer whereSql = getWhereSql1(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]): new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 通过荣誉称号名称获取荣誉称号代码
	 * getrychdm ---- 获取荣誉称号代码 
	 * @param rychmc
	 * @return
	 * @throws Exception
	 */
	public String getRychdm (String rychmc) throws Exception {
		String rychdm ="";
		String sql = "select rychdm from pjpy_jtrydmb where rychmc=? and rownum=1";
		String[] rychList = dao.getOneRs(sql, new String[]{rychmc}, new String[]{"rychdm"});
		if (rychList != null && rychList.length > 0) {
			rychdm = rychList[0];
		}
		return rychdm;
	}
	
	/**
	 * 验证数据是否重复
	 * chkDataByXjbj ---- 验证先进班级数据是否重复 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByWmbj(WmbjSqModel wmbjsqModel) throws Exception {
		boolean bFlag = false;
		String xn = wmbjsqModel.getXn();
		String xq = wmbjsqModel.getXq();
		String bjdm = wmbjsqModel.getBjdm();
		String rychdm = getRychdm("文明班级");//获取荣誉称号代码;
		String sql = "select xn,xq,bjdm,rychdm from pjpy_xjbjandwmsqb where xn=? and xq=? and bjdm=? and rychdm=?";
		String[] temList = dao.getOneRs(sql, new String[]{xn, xq, bjdm, rychdm}, new String[]{"xn", "xq", "bjdm", "rychdm"});
		if (temList != null && temList.length > 0) {//存在
			bFlag = true;
		}//end if
		return bFlag;
	}
	
	/**
	 * 保存先进班级信息
	 * savexjbjinfo ---- 保存先进班级信息 
	 * @param xjbjSqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWmBjInfo(WmbjSqModel xjbjSqModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = xjbjSqModel.getXn();
		String xq = xjbjSqModel.getXq();
		String bjdm = xjbjSqModel.getBjdm();
		String bzxm = DealString.toGBK(xjbjSqModel.getBzxm());
		String bzr = DealString.toGBK(xjbjSqModel.getBzr());
		String xsrs = xjbjSqModel.getXsrs();
		String zysj = DealString.toGBK(xjbjSqModel.getZysj());
		String[] inList = new String[]{"xn", "xq", "rychdm", "bjdm", "bzxm", "bzr", "xsrs", "zysj"};
		String rychdm = getRychdm("文明班级");//获取荣誉称号代码
		bFlag = StandardOperation.insert("pjpy_xjbjandwmsqb", inList, new String[]{xn, xq, rychdm, bjdm, bzxm, bzr, xsrs, zysj}, request);
		
		return bFlag;
	}
	
	/**
	 * 文明班级申请查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> wmbjCxJg() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "fdysh","xysh","xxsh"};
		String[] cnList = new String[]{"主键", "bgcolor", "行号", "学年", "学院名称", "专业名称", "班级名称", "荣誉称号名称","学生人数", "辅导员","辅导员审核","学院审核", "学校审核"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 文明班级申请查询结果
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> wmbjsqCxJg(WmbjSqModel xjbjqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='未审核' and fdysh='未审核' and xysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,replace(fdysh,' ','') fdysh,replace(xysh,' ','') xysh,replace(xxsh,' ','') xxsh from view_pjpy_xjbjandwmsq where 1=1 ";
		StringBuffer whereSql = getWhereSql2(xjbjqryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr","fdysh","xysh", "xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 文明班级批量删除
	 * delxjbjxx ---- 先进班级信息批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delWmbjXx(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from pjpy_xjbjandwmsqb where xn||xq||bjdm||rychdm = '" + whichxh + "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * 通过主键获取文明班级信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xymc,a.zymc,a.bjmc,a.zysj,a.fdyyj,a.xyyj,a.xxyj,a.bjdm,b.nj from view_pjpy_xjbjandwmsq a left join view_njxyzybj b on a.bjdm=b.bjdm where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 辅导员查询文明班级表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyQryWmbjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "rychmc", "bzxm" , "xsrs", "bzr", "fdysh"};
		String[] cnList = new String[]{"主键", "bgcolor", "行号", "学年", "学院名称", "专业名称", "班级名称", "荣誉称号名称", "学生人数", "辅导员", "辅导员审核"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 辅导员查询文明班级结果
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> fdyQryWmbjResult(WmbjSqModel wmbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		
		String sql = "select xn||xq||bjdm||rychdm,(case when(fdysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,fdysh from view_pjpy_xjbjandwmsq where 1=1 ";
		StringBuffer whereSql = getWhereSql2(wmbjModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "fdysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 学院查询文明班级表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyQryWmbjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "rychmc", "bzxm" , "xsrs", "bzr", "xysh"};
		String[] cnList = new String[]{"主键", "bgcolor", "行号", "学年", "学院名称", "专业名称", "班级名称", "荣誉称号名称", "学生人数", "辅导员", "学院审核"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 学院查询文明班级结果
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyQryWmbjResult(WmbjSqModel wmbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		
		String sql = "select xn||xq||bjdm||rychdm,(case when(xysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xysh from view_pjpy_xjbjandwmsq where 1=1 and fdysh='通过' ";
		StringBuffer whereSql = getWhereSql2(wmbjModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 学校查询文明班级表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xxQryWmbjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "rychmc", "bzxm" , "xsrs", "bzr", "xxsh"};
		String[] cnList = new String[]{"主键", "bgcolor", "行号", "学年", "学院名称", "专业名称", "班级名称", "荣誉称号名称", "学生人数", "辅导员", "学校审核"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 学校查询文明班级结果
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxQryWmbjResult(WmbjSqModel wmbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xxsh from view_pjpy_xjbjandwmsq where 1=1 and fdysh='通过' and xysh='通过' ";
		StringBuffer whereSql = getWhereSql2(wmbjModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 辅导员获取文明班级单个审核显示信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShViewByFdy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xn,a.xq,a.xymc,a.bjmc,a.zymc,a.rychmc,a.xsrs,a.bzr,a.zysj,a.fdysh sh,a.fdyyj shyj,(select count(*) from view_wjcf b where a.bjdm=b.bjdm and a.xn=b.xn and b.xxsh='通过' and (b.cflbmc='留校察看' or b.cflbmc='开除学籍')) bjcfrs from view_pjpy_xjbjandwmsq a where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 学院获取文明班级单个审核显示信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShViewByXy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xn,a.xq,a.xymc,a.bjmc,a.zymc,a.rychmc,a.xsrs,a.bzr,a.zysj,a.xysh sh,a.xyyj shyj,(select count(*) from view_wjcf b where a.bjdm=b.bjdm and a.xn=b.xn and b.xxsh='通过' and (b.cflbmc='留校察看' or b.cflbmc='开除学籍')) bjcfrs from view_pjpy_xjbjandwmsq a where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 学校获取文明班级单个审核显示信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShViewByXx(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xn,a.xq,a.xymc,a.bjmc,a.zymc,a.rychmc,a.xsrs,a.bzr,a.zysj,a.xxsh sh,a.xxyj shyj,(select count(*) from view_wjcf b where a.bjdm=b.bjdm and a.xn=b.xn and b.xxsh='通过' and (b.cflbmc='留校察看' or b.cflbmc='开除学籍')) bjcfrs from view_pjpy_xjbjandwmsq a where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 辅导员审核单个文明班级
	 * @param wmbjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean fdyShWmbjByone(WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = wmbjModel.getPkValue();
		String sh = DealString.toGBK(wmbjModel.getShxm());
		String shyj = DealString.toGBK(wmbjModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"fdysh","fdyyj"}, new String[]{sh,shyj}, "xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 学院审核单个文明班级
	 * @param wmbjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xyShWmbjByone(WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = wmbjModel.getPkValue();
		String sh = DealString.toGBK(wmbjModel.getShxm());
		String shyj = DealString.toGBK(wmbjModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh","xyyj"}, new String[]{sh,shyj}, "xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 学校审核单个文明班级
	 * @param wmbjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xxShWmbjByone(WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = wmbjModel.getPkValue();
		String sh = DealString.toGBK(wmbjModel.getShxm());
		String shyj = DealString.toGBK(wmbjModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh","xxyj"}, new String[]{sh,shyj}, "xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 辅导员审核集体文明班级
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] fdyWmbjjtSh(String[] keys, String res, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String rss = "tg".equalsIgnoreCase(res) ? "通过" : ("btg".equalsIgnoreCase(res) ? "不通过" : "未审核");
		for (int i = 0; i < keys.length; i++) {
			result[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"fdysh"}, new String[]{rss}, "xn||xq||bjdm||rychdm", keys[i], request);
		}
		return result;
	}
	
	/**
	 * 学院审核集体文明班级
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] xyWmbjjtSh(String[] keys, String res, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String rss = "tg".equalsIgnoreCase(res) ? "通过" : ("btg".equalsIgnoreCase(res) ? "不通过" : "未审核");
		for (int i = 0; i < keys.length; i++) {
			result[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh"}, new String[]{rss}, "xn||xq||bjdm||rychdm", keys[i], request);
		}
		return result;
	}
	
	/**
	 * 学校审核集体文明班级
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] xxWmbjjtSh(String[] keys, String res, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String rss = "tg".equalsIgnoreCase(res) ? "通过" : ("btg".equalsIgnoreCase(res) ? "不通过" : "未审核");
		for (int i = 0; i < keys.length; i++) {
			result[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh"}, new String[]{rss}, "xn||xq||bjdm||rychdm", keys[i], request);
		}
		return result;
	}
	
	/**
	 * 获取荣誉称号列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		String sql = "select rychdm,rychmc from rychdmb";
		rychList = dao.getList(sql, new String[]{}, new String[]{"rychdm", "rychmc"});
		return rychList;
	}
	
	/**
	 * 获取奖学金申请学年年度
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqXnNd() throws Exception {
		String[] jxjsqxnndList = new String[]{};
		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		jxjsqxnndList = dao.getOneRs(sql, new String[]{}, new String[]{"jxjsqxn", "jxjsqnd"});
		return jxjsqxnndList;
	}
	
	/**
	 * 辅导员查询荣誉称号表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitleByFdy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "fdysh"};//字段列表
		String[] cnList = new String[]{"主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "荣誉称号", "辅导员审核" };//中文列表
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 学院查询荣誉称号表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitleByXy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xysh"};//字段列表
		String[] cnList = new String[]{"主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "荣誉称号", "学院审核" };//中文列表
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 学校查询荣誉称号表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitleByXx() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xxsh"};//字段列表
		String[] cnList = new String[]{"主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "荣誉称号", "学校审核" };//中文列表
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 辅导员查询荣誉称号结果
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByFdy(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,fdysh from view_xsrychb where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "fdysh"};//字段列表
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//查询条件
		values = common.values;//查询条件值
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 学院查询荣誉称号结果二级审核
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXy(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xysh from view_xsrychb where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xysh"};//字段列表
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//查询条件
		values = common.values;//查询条件值
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 学院查询荣誉称号结果三级审核
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXy3(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xysh from view_xsrychb where 1=1 and fdysh='通过'";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xysh"};//字段列表
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//查询条件
		values = common.values;//查询条件值
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 学校查询荣誉称号结果二级审核
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXx(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xxsh from view_xsrychb where 1=1 and xysh='通过' ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xxsh"};//字段列表
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//查询条件
		values = common.values;//查询条件值
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 学校查询荣誉称号结果三级审核
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXx3(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xxsh from view_xsrychb where 1=1 and fdysh='通过' and xysh='通过' ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xxsh"};//字段列表
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//查询条件
		values = common.values;//查询条件值
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 获取荣誉称号名称
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public String getRychmc(String rychdm) throws Exception {
		String sql = "select rychmc from rychdmb where rychdm=? and rownum=1";
		String[] resList = dao.getOneRs(sql, new String[]{rychdm}, new String[]{"rychmc"});
		if (resList != null && resList.length == 1) {
			return resList[0];
		}
		return "";
	}
	
	/**
	 * 辅导员审核个人荣誉称号结果
	 * @param keys
	 * @param shjg
	 * @return
	 * @throws Exception
	 */
	public boolean[] fdyshResult(String[] keys, String shjg, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String res = !StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "tg") ? "通过" : (!StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "btg") ? "不通过" : "未审核");
		boolean[] exists = dao.checkExists("xsrychb", "xn||nd||xh||rychdm", keys);//检查数据是否存在
		for (int i = 0; i < exists.length; i++) {
			if (exists[i]) {//如果存在就更新审核状态
				result[i] = StandardOperation.update("xsrychb", new String[]{"fdysh"}, new String[]{res}, "xn||nd||xh||rychdm", keys[i], request);
			}
		}
		return result;
	}
	
	/**
	 * 学院审核个人荣誉称号结果
	 * @param keys
	 * @param shjg
	 * @return
	 * @throws Exception
	 */
	public boolean[] xyshResult(String[] keys, String shjg, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String res = !StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "tg") ? "通过" : (!StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "btg") ? "不通过" : "未审核");
		boolean[] exists = dao.checkExists("xsrychb", "xn||nd||xh||rychdm", keys);//检查数据是否存在
		for (int i = 0; i < exists.length; i++) {
			if (exists[i]) {//如果存在就更新审核状态
				result[i] = StandardOperation.update("xsrychb", new String[]{"xysh"}, new String[]{res}, "xn||nd||xh||rychdm", keys[i], request);
			}
		}
		return result;
	}
	
	/**
	 * 学校审核个人荣誉称号结果
	 * @param keys
	 * @param shjg
	 * @return
	 * @throws Exception
	 */
	public boolean[] xxshResult(String[] keys, String shjg, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String res = !StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "tg") ? "通过" : (!StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "btg") ? "不通过" : "未审核");
		boolean[] exists = dao.checkExists("xsrychb", "xn||nd||xh||rychdm", keys);//检查数据是否存在
		for (int i = 0; i < exists.length; i++) {
			if (exists[i]) {//如果存在就更新审核状态
				result[i] = StandardOperation.update("xsrychb", new String[]{"xxsh"}, new String[]{res}, "xn||nd||xh||rychdm", keys[i], request);
			}
		}
		return result;
	}
	
	/**
	 * 获取荣誉称号相关信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfo(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh,a.xn,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xn,a.nd,a.rychmc,a.fdyyj,a.xyyj,a.xxyj,(select round(months_between(to_date(to_char(sysdate,'yyyymmdd'),'yyyy-mm-dd'),to_date(b.csrq,'yyyy-mm-dd'))/12) from view_xsxxb b where a.xh=b.xh) nl,(select c.jg from view_xsxxb c where a.xh=c.xh) jg,(select e.zzmmmc from view_xsxxb e where a.xh=e.xh) zzmm,d.jlqk,d.drzw,d.zysj,d.hjqk from view_xsrychb a left join xsrychxxb d on a.xh=d.xh where a.xn||a.nd||a.xh||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * 辅导员审核查询结果
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfoByFdy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,dcj,zcj,tcj,rychmc,fdysh yesno,fdyyj shyj from view_xsrychb where xn||nd||xh||rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * 学院审核查询结果
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfoByXy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,dcj,zcj,tcj,rychmc,xysh yesno,xyyj shyj from view_xsrychb where xn||nd||xh||rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * 学校审核查询结果
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfoByXx(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,dcj,zcj,tcj,rychmc,xxsh yesno,xxyj shyj from view_xsrychb where xn||nd||xh||rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * 辅导员审核单个荣誉称号
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean rychShByFdy(GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = grrychModel.getPkValue();
		String sh = grrychModel.getSh();
		String shyj = grrychModel.getShyj();
		bFlag = StandardOperation.update("xsrychb", new String[]{"fdysh", "fdyyj"}, new String[]{sh, shyj}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 学院审核单个荣誉称号
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean rychShByXy(GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = grrychModel.getPkValue();
		String sh = grrychModel.getSh();
		String shyj = grrychModel.getShyj();
		bFlag = StandardOperation.update("xsrychb", new String[]{"xysh", "xyyj"}, new String[]{sh, shyj}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 学校审核单个荣誉称号
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean rychShByXx(GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = grrychModel.getPkValue();
		String sh = grrychModel.getSh();
		String shyj = grrychModel.getShyj();
		bFlag = StandardOperation.update("xsrychb", new String[]{"xxsh", "xxyj"}, new String[]{sh, shyj}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> qryRychTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "nd", "xn", "xh", "xm", "bjmc", "rychmc", "fdysh", "xysh", "xxsh"};//字段列表
		String[] cnList = new String[]{"主键", "年度", "学年", "学号", "姓名", "班级名称", "荣誉称号", "辅导员审核" , "学院审核", "学校审核"};//中文列表
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> qryRychResult(RychSjQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSql1(rychModel);//查询条件
		values = common.values;//查询条件值
		String sql = "select xn||nd||xh||rychdm pk,nd,xn,xh,xm,bjmc,rychmc,fdysh,xysh,xxsh from view_xsrychb where 1=1 and (rychmc like '文明大学生' or rychmc like '三好学生标兵' or rychmc like '三好学生' or rychmc like '优秀学生干部' or rychmc like '优秀毕业生') ";
		String[] opList = new String[]{"pk","nd","xn","xh","xm","bjmc","rychmc","fdysh","xysh","xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 获取荣誉称号信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> rychInfoByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.nd,a.xn,a.rychdm,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw from view_xsrychb a where a.xn||a.nd||a.xh||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 荣誉称号申请修改
	 * @param pkValue
	 * @param rychdm
	 * @param drzw
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean rychInfoModi(String pkValue, String rychdm, String drzw, String xh, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		bFlag = StandardOperation.update("xsrychb", new String[]{"rychdm"}, new String[]{rychdm}, "xn||nd||xh||rychdm", pkValue, request);
		if (bFlag) {
			StandardOperation.update("xsrychxxb", new String[]{"drzw"}, new String[]{drzw}, "xh", xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 荣誉称号信息批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychInfoDel(String[] keys, HttpServletRequest request) throws Exception {
		int del = 0;//删除记录数
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// 得到主键
			boolean bFlag = StandardOperation.delete("xsrychb", "xn||nd||xh||rychdm", whichpk, request);
			if (bFlag){//删除成功
				del++;
			}
		}
		return String.format("%1$s 条记录成功删除!", del);
	}
	
	/**
	 * 获取奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		DAO dao = DAO.getInstance();//公用数据操作DAO
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		String sql = "select jxjdm,jxjmc from jxjdmb";
		jxjList = dao.getList(sql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
		return jxjList;
	}
	
	/**
	 * 获取奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList1(List<HashMap<String, String>> jxjList) throws Exception {
		List<HashMap<String, String>> jxjList1 = new ArrayList<HashMap<String,String>>();
		String sql = "select jxjdm,jxjmc from zyjxjdmb";
		jxjList1 = dao.getList(sql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
		for (int i=0;i<jxjList1.size();i++) {
			jxjList.add(jxjList1.get(i));
		}
		return jxjList;
	}
	
	/**
	 * 获取字段列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdList() throws Exception {
		List<HashMap<String, String>> zdList = new ArrayList<HashMap<String,String>>();
		/*String[] enList = new String[]{"zhszcpzf", "zhszcppm" ,"bxkdkcj" ,"bxkpjcj", "dcj", "zcj", "tcj"};
		String[] cnList = new String[]{"综合素质测评总分", "综合素质测评排名", "必修课单科成绩", "必修课平均成绩", "德成绩", "智成绩" ,"体成绩"};*/
		String[] enList = new String[]{ "rownum" ,"cj" ,"pjf"};
		String[] cnList = new String[]{ "综合素质测评排名(名)", "课程单科成绩", "课程平均成绩"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			zdList.add(tmp);
		}
		return zdList;
	}
	
	/**
	 * 奖学金条件查询 表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjsztjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "jxjdm", "rownum", "xn","tjzdm", "tj", };
		String[] cnList = new String[]{"pk", "jxjdm", "行号","学年", "条件字段名", "条件"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * 奖学金对应条件
	 * @param xn
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxjsztj(String xn, String jxjdm) throws Exception {
		List<String[]> jxjsztjList = new ArrayList<String[]>();
		String sql = "select xn||jxjdm||tjzdm pk,jxjdm,rownum,xn,(case when tjzdm='zhszcpzf' then '综合素质测评总分' when tjzdm='rownum' then '综合素质测评排名(名)' when tjzdm='cj' then '课程单科成绩' when tjzdm='pjf' then '课程平均成绩' when tjzdm='dcj' then '德成绩' when tjzdm='zcj' then '智成绩' when tjzdm='tcj' then '体成绩' end) tjzdm,tj from pjpy_jxjtjszb where 1=1 ";
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn='");
			whereSql.append(xn);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjdm)) {
			whereSql.append(" and jxjdm='");
			whereSql.append(jxjdm);
			whereSql.append("'");
		}
		jxjsztjList = dao.rsToVator(sql + whereSql, new String[]{}, new String[]{"pk", "jxjdm" , "rownum","xn", "tjzdm", "tj"});
		return jxjsztjList;
	}
	
	/**
	 * 奖学金条件设置删除
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjDel(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("pjpy_jxjtjszb", "xn||jxjdm||tjzdm", pkValue, request);
	}
	
	/**
	 * 奖学金条件设置保存
	 * @param tjszModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjSave(JxjtjszSaveModel tjszModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = StandardOperation.delete("pjpy_jxjtjszb",
				"xn||jxjdm||tjzdm", tjszModel.getXn() + tjszModel.getJxjdm()
						+ tjszModel.getZdm(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("pjpy_jxjtjszb", new String[] {
					"xn", "jxjdm", "tjzdm", "tj"}, new String[] {
					tjszModel.getXn(), tjszModel.getJxjdm(),
					tjszModel.getZdm(),
					tjszModel.getYsf() + "'" +tjszModel.getVal() + "'",
					}, request);
			StandardOperation.delete("pjpy_jxjtjwjszb", "xn||jxjdm", tjszModel.getXn() + tjszModel.getJxjdm(), request);
			StandardOperation.insert("pjpy_jxjtjwjszb", new String[] { "xn",
					"jxjdm", "sfwj" }, new String[] { tjszModel.getXn(),
					tjszModel.getJxjdm(), tjszModel.getSfwj() }, request);
		}
		return bFlag;
	}
	
	/**
	 * 条件设置单个详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTjInfo(String pkValue, String jxjdm) throws Exception {
		HashMap<String, String> tjMap = new HashMap<String, String>();
		String sql = "select a.*,b.jxjmc from pjpy_jxjtjszb a left join jxjdmb b on a.jxjdm=b.jxjdm where a.xn||a.jxjdm||a.tjzdm=?";
		tjMap = dao.getMapNotOut(sql, new String[]{pkValue});
		if (StringUtils.isNull(tjMap.get("jxjmc"))) {
			sql = "select jxjmc from zyjxjdmb where jxjdm = ?";
			String jxjmc = dao.getOneRs(sql, new String[]{jxjdm}, "jxjmc");
			tjMap.put("jxjmc", jxjmc);
		}
		return tjMap;
	}
	
	/**
	 * 条件设置单个修改
	 * @param pkValue
	 * @param tjszModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean tjEditResult(String pkValue, JxjtjszSaveModel tjszModel,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_jxjtjszb", new String[] {
				"tj"}, new String[] {tjszModel.getYsf() + "'" + tjszModel.getVal() + "'"},
				"xn||jxjdm||tjzdm", pkValue, request);
	} 
	
	/**
	 * 获取奖学金是否违纪条件
	 * @param xn
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String getJxjTjsz(String xn ,String jxjdm) throws Exception {
		return dao.getOneRs(
				"select sfwj from pjpy_jxjtjwjszb where xn=? and jxjdm=?",
				new String[] { xn, jxjdm }, "sfwj");
	}
	
	/**
	 * 获取奖学金审核学年学期
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjSqxnxq() throws Exception {
		String[] jxjsqxnxq = new String[2];
		String sql = "select jxjsqxn,jxjsqxq from xtszb where rownum=1";
		jxjsqxnxq = dao.getOneRs(sql, new String[]{}, new String[]{"jxjsqxn", "jxjsqxq"});
		return jxjsqxnxq;
	}
	
	/**
	 * 奖学金自动审核
	 * @param zyjxjModel
	 * @param request
	 * @throws Exception
	 */
	public void zyjxjAutoSh(ZyjxjAutoshModel zyjxjModel, HttpServletRequest request) throws Exception {
		String xn = zyjxjModel.getXn();
		String xq = zyjxjModel.getXq();
		String jxjdm = zyjxjModel.getJxjdm();
		String sql = "delete from cjlsb where xn = ?";
		StringBuffer strsql = null;
		boolean bDel = dao.runUpdate(sql, new String[]{xn});
		if (bDel) {//避免成绩重复，删除本学年学生成绩
			sql = "insert into cjlsb (xn,xq,xh,xkkh,kcdm,kcmc,kcxz,xf,cj,cjlx)" +
			" select a.xn,a.xq,a.xh,a.xkkh,a.kcdm,a.kcmc,a.kcxz,a.xf," +
			"(case when (select to_char(b.dycj) from cjdzb@dblink_jw b" +
			" where to_char(a.cj)=to_char(b.cj)) is not null then " +
			"(select to_char(b.dycj) from cjdzb@dblink_jw b " +
			" where to_char(a.cj)=to_char(b.cj)) else a.cj end) cj," +
			"'正考' from cjb@dblink_jw a where a.bkcj is null and" +
			" a.cxcj is null and a.kcxz like '%必修课%' and (a.cxbj is null or a.cxbj='0')" +
			" and xn=?";
			dao.runUpdate(sql, new String[]{xn});//获取学生正考单科成绩
			sql = "delete from xspjpylsb where xn = ?";
			bDel = dao.runUpdate(sql, new String[]{xn});
			String[] jxjtj = new String[3];
			if (bDel) {//获取学生单科成绩，平均分，排名
				List<String[]> jxjtjList = dao
						.rsToVator(
								"select tjzdm,tj from pjpy_jxjtjszb where xn=? and jxjdm=?",
								new String[] {xn, jxjdm}, new String[] {"tjzdm", "tj"});
				if (jxjtjList != null && jxjtjList.size() > 0) {
					String[] tmp = new String[]{};
					for (int i = 0; i < jxjtjList.size(); i++) {
						 tmp = jxjtjList.get(i);
						 String zdmandtj = "";
						 for (int j = 0; j < tmp.length; j++) {
							 zdmandtj += tmp[j];
						 }
						 jxjtj[i] = zdmandtj;
					}
				}
				jxjtj = getClearResult(jxjtj);//清除条件里面的单引号
				String sfwj = dao
						.getOneRs(
								"select sfwj from pjpy_jxjtjwjszb where xn=? and jxjdm=?",
								new String[] { xn, jxjdm }, "sfwj");//条件中是否带有违纪处分
				if (!StringUtils.isNull(sfwj) && StringUtils.isEqual(sfwj, "0")) {//不包含
					strsql = new StringBuffer("insert into xspjpylsb (xn,xq,xh,cjpjf,zhcpzf,zhpm) " +
					"select distinct '"+ xn +"','"+ xq +"',a.xh,a.pjf,''," +
					"(select l.zhszpm from xszyjxjb l where l.xh=a.xh " +
					"and l.xq='"+ xq +"' and l.xn='"+ xn +"') zhpm from " +
					"(select xh,round(avg(cj)) pjf from cjlsb where " +
					"xn='"+ xn +"'  group by xh ) a," +
					"(select xh,min(to_number(cj)) cj from cjlsb " +
					"where xn=? group by xh ) " +
					"b where a.xh=b.xh and ");
					if (jxjtj != null && jxjtj.length > 0) {
						for (int i = 0 ; i < jxjtj.length;i++) {
							strsql.append(jxjtj[i]);
							strsql.append(" and ");
						}
					}
					strsql.append(" a.xh not in (select xh from view_wjcf) ");
					
				} else {//包含
					strsql = new StringBuffer("insert into xspjpylsb (xn,xq,xh,cjpjf,zhcpzf,zhpm) " +
							"select distinct '"+ xn +"','"+ xq +"',a.xh,a.pjf,''," +
							"(select l.zhszpm from xszyjxjb l where l.xh=a.xh " +
							"and l.xq='"+ xq +"' and l.xn='"+ xn +"') zhpm from " +
							"(select xh,round(avg(cj)) pjf from cjlsb where " +
							"xn='"+ xn +"' group by xh ) a," +
							"(select xh,min(to_number(cj)) cj from cjlsb " +
							"where xn=? group by xh ) " +
							"b where a.xh=b.xh and ");
							if (jxjtj != null && jxjtj.length > 0) {
								for (int i = 0 ; i < jxjtj.length;i++) {
									strsql.append(jxjtj[i]);
									strsql.append(" and ");
								}
							}
					strsql.append("1=1 ");
				}
				dao.runUpdate(strsql.toString(), new String[]{xn});//自动过滤条件（违纪，平均分，单科）
				strsql = new StringBuffer("update xszyjxjb set xysh='通过'," +
						"xxsh='通过',fdyyj='自动审核'||to_char(SYSDATE,'yyyymmddhh24miss')," +
						"xyyj='自动审核'||to_char(SYSDATE,'yyyymmddhh24miss')," +
						"xxyj='自动审核'||to_char(SYSDATE,'yyyymmddhh24miss') " +
						"where exists (select xh from (select xh from (select a.xh from view_xsjbxx a," +
						"xspjpylsb b where a.xh=b.xh and bjdm in (");
				String[] bjList = getBjList(zyjxjModel);
				if (bjList != null && bjList.length > 0) {
					for (int i = 0; i < bjList.length; i++) {
						strsql.append("'");
						strsql.append(bjList[i]);
						strsql.append("',");
					}
					strsql = new StringBuffer(strsql.substring(0, strsql.length() - 1));
					strsql.append(") ");
				} else {
					strsql.append("'') ");
				}
				strsql.append(" order by to_number(case when zhpm is null then '200' else zhpm end) asc) where "+ rownumzd +") c where " +
						" c.xh=xszyjxjb.xh and xszyjxjb.xn='");
				strsql.append(xn);
				strsql.append("' and xszyjxjb.xq='");
				strsql.append(xq);
				strsql.append("' and xszyjxjb.jxjdm='");
				strsql.append(jxjdm);
				strsql.append("' and  fdysh='通过')");
				dao.runUpdate(strsql.toString(), new String[]{});//执行自动审核
			}
		}
	}
	
	/**
	 * 清除条件里面的单引号
	 * @param jxjtj
	 * @return
	 * @throws Exception
	 */
	public String[] getClearResult(String[] jxjtj) throws Exception {
		String[] tjtmp = new String[jxjtj.length]; 
		if (jxjtj != null && jxjtj.length > 0) {
			for (int i = 0; i < jxjtj.length; i++) {
				String tmp = jxjtj[i];
				//清除条件里面的单引号
				tmp = !StringUtils.isNull(tmp) ? tmp.replaceAll("'", "") : "";
				if (!StringUtils.isNull(tmp) && tmp.indexOf("ownum") > 0) {
					rownumzd = tmp;
					tmp = " 1=1 ";
				}
				tjtmp[i] = tmp;
			}
		}
		return tjtmp;
	}
	
	/**
	 * 获取班级列表
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public String[] getBjList(ZyjxjAutoshModel zyjxjModel) throws Exception {
		String[] bjList = null;
		String sql = "";
		String xydm = zyjxjModel.getXydm();
		String zydm = zyjxjModel.getZydm();
		String bjdm = zyjxjModel.getBjdm();
		if (!StringUtils.isNull(xydm)) {//学院非空时
			if (!StringUtils.isNull(bjdm)) {
				bjList = new String[1];
				bjList[0] = bjdm;
			} else if (!StringUtils.isNull(zydm)) {//专业非空时
				if (!StringUtils.isNull(bjdm)) {//班级非空时
					bjList = new String[1];
					bjList[0] = bjdm;
				} else {//专业所对应的班级
					bjList = new String[]{};
					sql = "select distinct bjdm from view_njxyzybj where zydm = ?";
					String[] zydybjList= dao.getRs(sql, new String[]{zydm}, "bjdm");
					bjList = zydybjList;
				}
			} else {//专业为空，班级为空
				bjList = new String[]{};
				sql = "select distinct zydm from view_njxyzybj where xydm = ?";
				String[] zyList =  dao.getRs(sql, new String[]{xydm}, "zydm");
				if (zyList != null && zyList.length > 0) {
					StringBuffer strsql = null;
					strsql = new StringBuffer("select distinct bjdm from view_njxyzybj where zydm in (");
					for (int i = 0; i < zyList.length; i++) {
						strsql.append("'");
						strsql.append(zyList[i]);
						strsql.append("',");
					}
					sql = strsql.substring(0, strsql.length() - 1);
					sql += ") ";
					bjList = dao.getRs(sql, new String[]{}, "bjdm");
				}
			}
		}
		if (!StringUtils.isNull(zydm)) {//专业非空时
			if (!StringUtils.isNull(bjdm)) {//班级非空时
				bjList = new String[1];
				bjList[0] = bjdm;
			} else {//专业所对应的班级
				bjList = new String[]{};
				sql = "select distinct bjdm from view_njxyzybj where zydm = ?";
				String[] zydybjList= dao.getRs(sql, new String[]{zydm}, "bjdm");
				bjList = zydybjList;
			}
		}
		if (!StringUtils.isNull(bjdm)) {//班级非空时
			bjList = new String[1];
			bjList[0] = bjdm;
		}
		return bjList;
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_xsjbxx where xh=?",
						new String[] { xh });
	}
}
