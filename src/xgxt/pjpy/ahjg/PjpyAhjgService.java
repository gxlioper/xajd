
package xgxt.pjpy.ahjg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院评奖评优Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class PjpyAhjgService implements Serializable {
	
	private PjpyAhjgDAO dao = null;//数据库操作DAO
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 通过传入TYPE来选择不同的页面查询TITLE
	 * @param enCol
	 * @param cnCol
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(int iType) throws Exception {
		dao = new PjpyAhjgDAO();
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();
		String[] enCol = null;
		String[] cnCol = null;
		if (iType == 1) {//如果是1则输出学生成绩查询表头
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "kcsbm","kcxz", "cj"};
			cnCol = new String[]{"学年","学期","学号","姓名","年级",  Base.YXPZXY_KEY+"名称","班级名称","课程名称","课程性质","成绩"};
		}//end if
		else if (iType == 2) {//如果是2则输出班级补考率查询表头
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"主键", "bgcolor", "行号", "学年",  Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "班长姓名", "学生人数", "班主任", "班级补考率"};
		}//end if
		else if (iType == 3) {//如果是3则输出早操出勤率查询表头
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xq", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"主键", "bgcolor", "行号", "学年", "学期",  Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "班长姓名", "学生人数", "班主任", "早操出勤率"};
		}//end if
		else if (iType == 4) {//如果是4则输出先进集体评比分配表头
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"行号","荣誉称号代码","荣誉称号名称","评选比例", "评选班级总数"};
		}
		else if (iType == 5) {
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"行号","荣誉称号代码","荣誉称号名称","评选比例", "评选人数或所占比例"};
		}
		else if (iType == 6) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "djksmc", "ksrq", "cj"};
			cnCol = new String[]{"学年","学期","学号","姓名","年级",  Base.YXPZXY_KEY+"名称","班级名称","等级考试名称", "考试日期" , "成绩"};
		}
		else if (iType == 7) {//如果是1则输出学生成绩查询表头
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz","khfs","cj","xf","pjf"};
			cnCol = new String[]{"学年","学期","学号","姓名", Base.YXPZXY_KEY+"名称","班级名称","课程名称","课程性质","考核方式","成绩","学分", "总科平均分"};
		}
		else if (iType == 8) {//如果是1则输出学生成绩查询表头
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc","zymc", "bjmc", "kcsbm","khfs","cj","pjf"};
			cnCol = new String[]{"学年","学期","学号","姓名", Base.YXPZXY_KEY+"名称","专业名称","班级名称","课程名称","考核方式","成绩", "总科平均分"};
		} else if (iType==9) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz", "cj", "kscj", "kccj"};
			cnCol = new String[]{"学年","学期","学号","姓名", Base.YXPZXY_KEY+"名称","班级名称","课程名称","课程性质","成绩", "考试课平均成绩", "考查课平均成绩"};
		} else if (iType == 10) {
			enCol = new String[] { "xn", "xqmc", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "xf", "cj", "bkcx" };
			cnCol = new String[] { "学年", "学期", "学号", "姓名",  Base.YXPZXY_KEY+"名称", "班级名称",
					"课程名称", "课程性质", "学分", "成绩", "补考或重修" };
			// TODO
		}
		/*if (iType == 4) {
			enCol = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "fdysh"};
			cnCol = new String[]{"主键", "bgcolor", "行号", "学年", "学院名称", "专业名称", "班级名称", "班长姓名", "学生人数", "辅导员", "辅导员审核"};
		}*/
		listTopTr = dao.getSearchTitle(enCol, cnCol);
		return listTopTr;
	}
	
	/**
	 * 学生成绩查询结果
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getPjpyXscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm, String userType, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		List<String[]> listRs = dao.getPjpyXscjResult(pjpyahjgxscjqryModel,
				dataSearchForm,userType,userName);
		return listRs;
	}
	
	/**
	 * 学生成绩查询结果数
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public int getPjpyXscjResultNum(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel, String userType, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getPjpyXscjResultNum(pjpyahjgxscjqryModel,userType,userName);
	}
	
	/**
	 * 荣誉列表选择信息
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRysqList(int type) throws Exception {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		if (type == 1) {
			en = new String[] {"xjjt" , "xjgr"};
			cn = new String[] {"先进集体" ,"先进个人"};
		} else if (type == 2) {
			en = new String[] {"xjbj" , "wmss"};
			cn = new String[] {"先进班级" ,"文明宿舍"};
		} else if (type == 3) {
			en = new String[] {"xjbj" , "wmss" , "xjgr"};
			cn = new String[] {"先进班级" , "文明宿舍", "先进个人"};
		} else if (type == 4) {
			en = new String[] {"xjbj" };
			cn = new String[] {"先进班级"};
		}
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			list.add(tempMap);//荣誉申诉首页选择申请信息
		}//end for
		return list;
	}

	/**
	 * 班级补考率查询结果
	 * bjbklqryresult ---- 班级补考率查询结果
	 * @param pjpyahjgxscjqryModel(公用) 班级补考率查询MODEL
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjbklQryResult(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjbklQryResult(pjpyahjgxscjqryModel);
	}
	
	/**
	 * 保存班级补考率信息，成功返回TRUE，反之返回FALSE
	 * saveBjbkl ----  保存班级补考率
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveBjbkl(BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.saveBjbkl(bjbklSaveModel, request);
	}
	
	/**
	 * 保存前检查数据是否重复,存在返回TRUE，反之返回FALSE
	 * chkbjbkl ---- 检查班级补考率
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkBjbkl(BjbklSaveModel bjbklSaveModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.chkBjbkl(bjbklSaveModel);
	}

	/**
	 * 通过主键获取班级补考率信息
	 * getbjbklbypk ---- 通过主键获取班级补考率信息 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBjbklByPk(String sPk) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjbklByPk(sPk);
	}
	
	/**
	 * 修改班级补考信息
	 * updatebjbkl ---- 修改班级补考率 
	 * @param sPk
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateBjbkl(String sPk, BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception{
		dao = new PjpyAhjgDAO();
		return dao.updateBjbkl(sPk, bjbklSaveModel, request);
	}
	
	/**
	 * 班级补考率批量删除
	 * delbjbkl ---- 删除班级补考率
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delBjbkl(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.delBjbkl(keys);
	}

	/**
	 * 早操出勤率查询结果
	 * getzccqlresult ---- 获取早操出勤率查询结果 
	 * @param zccqlQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZccqlResult(ZccqlQueryModel zccqlQryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZccqlResult(zccqlQryModel);
	}

	/**
	 * 检查早操出勤是否重复，重复返回TURE，反之返回FALSE
	 * chkzccql ---- 检查早操出勤 
	 * @param zccqlSaveModel 早操出勤保存MODEL
	 * @return
	 * @throws Exception
	 */
	public boolean chkZccql(ZccqlSaveModel zccqlSaveModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.chkZccql(zccqlSaveModel);
	}

	/**
	 * 保存早操出勤信息,成功返回TRUE，反之返回FALSE
	 * saveZccql ---- 保存早操出勤 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveZccql(ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.saveZccql(zccqlSaveModel, request);
	}

	/**
	 * 通过主键获取早操出勤信息
	 * getzccqlbypk ---- 通过主键获取早操出勤 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZccqlByPk(String sPk) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZccqlByPk(sPk);
	}
	
	/**
	 * 修改早操出勤信息,成功返回TRUE，反之返回FALSE
	 * updatezccql ---- 修改早操出勤 
	 * @param sPk
	 * @param zccqlSaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateZccql(String sPk, ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.updateZccql(sPk, zccqlSaveModel, request);
	}
	
	/**
	 * 早操出勤率批量删除
	 * delbjbkl ---- 早操出勤率
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delZccql(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.delZccql(keys);
	}
	
	/**
	 * 保存先进班级信息
	 * savexjbjinfo ---- 保存先进班级信息 
	 * @param xjbjSqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjBjInfo(XjBjSqModel xjbjSqModel, HttpServletRequest request) throws Exception {
		 dao = new PjpyAhjgDAO();
		 return dao.saveXjBjInfo(xjbjSqModel, request);
	 }

	/**
	 * 验证数据是否重复
	 * chkDataByXjbj ---- 验证先进班级数据是否重复 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByXjbj(XjBjSqModel xjbjSqModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.chkDataByXjbj(xjbjSqModel);
	}
	
	/**
	 * 获取荣誉称号查询表头(辅导员，学院，学校)
	 * getxjbjtitle ---- 获取先进班级查询表头
	 * @param xjbjQryModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjBjTitle(XjBjQryModel xjbjQryModel, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyAhjgDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//topList = dao.getXjbjTitleByFdy();
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") ) {
			topList = dao.getXjbjTitleByXy();
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			topList = dao.getXjbjTitleByXx();
		}// end if 学校用户,管理员
		return topList;
	}
	
	/**
	 * 获取荣誉称号查询表头(辅导员，学院，学校)
	 * getxjbjresult ----  获取先进班级查询结果
	 * @param xjbjQryModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjBjResult(XjBjQryModel xjbjQryModel, String sUserType, String sIsFdy, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//resList = dao.getXjbjResultByFdy(xjbjQryModel, sIsFdy, userName);
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy")) {
			resList = dao.getXjbjResultByXy(xjbjQryModel);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resList = dao.getXjbjResultByXx(xjbjQryModel);
		}// end if 学校用户,管理员
		return resList;
	}
	
	/**
	 * 先进集体审核结果
	 * submitshresult ---- 提交审核结果
	 * @param sRes
	 * @param sUserType
	 * @param sIsFdy
	 * @param xjjtShModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String submitShResult(String sRes, String sUserType, String sIsFdy, XjjtShModel xjjtShModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		String bFlag = "";
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//dao.fdyshResult(xjjtShModel, sRes, request);
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") ) {
			bFlag = dao.xyshResult(xjjtShModel, sRes, request);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			bFlag = dao.xxshResult(xjjtShModel, sRes, request);
		}// end if 学校用户,管理员
		return bFlag;
	}
	
	/**
	 * 单个获取先进集体信息
	 * getxjbjresultbyone ---- 单个获取先进集体信息 
	 * @param sPkValue
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXjBjResultByOne(String sPkValue, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyAhjgDAO();
		HashMap<String, String> resList = new HashMap<String, String>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//resList = dao.getXsJtJgByFdy(sPkValue);
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy")) {
			resList = dao.getXsJtJgByXy(sPkValue);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resList = dao.getXsJtJgByXx(sPkValue);
		}// end if 学校用户,管理员
		return resList;
	}
	
	/**
	 * 获取列表值
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getChkList(iType);
	}
	
	/**
	 * 单个审核先进集体(辅导员，学院，学校)
	 * savexjjtone ---- 单个审核先进集体
	 * @param xjjtshModel
	 * @param sUserType
	 * @param sIsFdy
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjjtOne(XjjtShModel xjjtshModel, String sUserType, String sIsFdy, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		boolean bFlag = false;
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			//bFlag = dao.fdySaveXjjtOne(xjjtshModel, request);
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") ) {
			bFlag = dao.xySaveXjjtOne(xjjtshModel, request);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			bFlag = dao.xxSaveXjjtOne(xjjtshModel, request);
		}// end if 学校用户,管理员
		return bFlag;
	}
	
	/**
	 * 得到全校班级总数的6%
	 * getbjzs ---- 得到班级总数 
	 * @return
	 * @throws Exception
	 */
	public String getBjzs() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjzs();
	}
	
	/**
	 * 获取班级学生违纪总数
	 * getbjcfrs ---- 获取班级学生违纪人数 
	 * @param bjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String getBjcfRs(String bjdm, String xn) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjcfRs(bjdm, xn);
	}
	
	/**
	 * 先进班级查询结果表头
	 * xjbjjgcxbt ---- 先进班级查询结果表头 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xjbjJgCxBt() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xjbjJgCxBt();
	}
	
	/**
	 * 先进班级查询结果
	 * xjbjcxjg ---- 先进班级查询结果 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xjbjCxJg(XjBjQryModel xjbjqryModel, String isFdy, String userName) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xjbjCxJg(xjbjqryModel, isFdy, userName);
	}
	
	/**
	 * 通过主键获取先进班级信息
	 * xjbjxxbypk ---- 通过主键获取先进班级信息 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xjbjXxByPk(String sPk) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xjbjXxByPk(sPk);
	}
	
	/**
	 * 保存先进班级结果
	 * bcxjbjjg ---- 保存先进班级结果 
	 * @param xjbjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean bcxjbjJg (XjBjSqModel xjbjsqModel, String sPk, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.bcxjbjJg(xjbjsqModel, sPk, request);
	}
	
	/**
	 * 先进班级批量删除
	 * delxjbjxx ---- 先进班级信息批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delXjbjXx(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.delXjbjXx(keys);
	}
	
	/**
	 * 先进个人评比总数
	 * getbjzsbygr ---- 先进个人评比总数 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjzsByGr() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getBjzsByGr();
	}
	
	/**
	 * 获取荣誉称号列表
	 * getrychlist ---- 获取荣誉称号列表 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getRychList();
	}
	
	/**
	 * 获取先进个人查询表头
	 * getxjgrtitle ---- 获取先进个人查询表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjgrTitle(String sUserType, String sIsFdy)throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			en = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "fdysh" };
			cn = new String[] {"bgcolor" , "主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "荣誉称号", "辅导员审核"};
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") ) {
			en = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "xysh" };
			cn = new String[] {"bgcolor" , "主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "荣誉称号",  Base.YXPZXY_KEY+"审核"};
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			en = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "xxsh" };
			cn = new String[] {"bgcolor" , "主键", "行号", "学年", "年度", "学号", "姓名", "班级名称", "荣誉称号", "学校审核"};
		}// end if 学校用户,管理员
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", en[i]);
			resTemp.put("cn", cn[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 先进个人查询结果辅导员，学院，学校
	 * getxjgrresult ---- 先进个人查询结果 
	 * @param sUserType
	 * @param sIsFdy
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjgrResult(String sUserType, String sIsFdy, XjgrQryModel xjgrqryModel, String isFdy, String userName)throws Exception {
		dao = new PjpyAhjgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			resList = dao.fdyqryXjgrResult(xjgrqryModel, isFdy, userName);
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") ) {
			resList = dao.xyqryXjgrResult(xjgrqryModel);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resList = dao.xxqryXjgrResult(xjgrqryModel);
		}// end if 学校用户,管理员
		return resList;
	}
	
	/**
	 * 获取奖学金申请学年，年度
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqxn() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjsqxn();
	}
	
	/**
	 * 通过主键获取先进个人信息(辅导员,学院,学校)
	 * @param sUserType
	 * @param sIsFdy
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXjgrByPk(String sUserType, String sIsFdy, String pkValue) throws Exception {
		dao = new PjpyAhjgDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			resMap = dao.fdyqryXjgrByPk(pkValue);
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") ) {
			resMap = dao.xyqryXjgrByPk(pkValue);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			resMap = dao.xxqryXjgrByPk(pkValue);
		}// end if 学校用户,管理员
		return resMap;
	}
	
	/**
	 * 先进个人单个审核
	 * @param sUserType
	 * @param sIsFdy
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public String xjgrshByOne(String sUserType, String sIsFdy, String yesNo, String pkValue, HttpServletRequest request, String rychdm, String oldxh) throws Exception {
		dao = new PjpyAhjgDAO();
		String sFlag = "";
		/*if (StringUtils.isEqual(sIsFdy, "true")) {
			bFlag = dao.fdyshXjgr(yesNo, request, pkValue);
		}*///end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") ) {
			sFlag = dao.xyshXjgr(yesNo, request, pkValue, rychdm, oldxh);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			sFlag = dao.xxshXjgr(yesNo, request, pkValue, rychdm, oldxh);
		}// end if 学校用户,管理员
		return sFlag;
	}
	
	/**
	 * 获取学习竞赛查询表头
	 * getxxjstitle ---- 获取学习竞赛查询表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjsTitle() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjsTitle();
	}
	
	/**
	 * 获取学习竞赛查询结果
	 * getxxjsqryresult ---- 获取学习竞赛查询结果 
	 * @param xxjsqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXxjsResult(XxjsQryModel xxjsqryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjsResult(xxjsqryModel);
	}
	
	/**
	 * 学习竞赛列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjSList() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjSList();
	}
	
	/**
	 * 通过学号获取学生姓名，学院，专业，班级
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsInfo(String xh) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXsInfo(xh);
	}

	/**
	 * 保存学习竞赛信息
	 * @param xsjxsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxjsInfo(XsjxSaveModel xsjxsaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.saveXxjsInfo(xsjxsaveModel, request);
	}
	
	/**
	 * 通过主键获取学习竞赛信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxjsInfoByPk(String pkValue)throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXxjsInfoByPk(pkValue);
	}
	
	/**
	 * 学习竞赛批量删除
	 * xxjsdel ---- 学习竞赛批量删除 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String xxjsDel(String[] keys) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.xxjsDel(keys);
	}
	
	/**
	 * 获取学生学年内平均分及班级名次
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	/*public HashMap<String, String> getXsPjfMc(String xh, String xn) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXsPjfMc(xh, xn);
	}*/
	
	/**
	 * 学生评审核信息表
	 */
	public HashMap<String, String> getXsPsxxb(String xh, String xn) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXsPsxxb(xh, xn);
	}
	
	/**
	 * 学生证打印位置
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String[] getTopLeftStr(String page) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getTopLeftStr(page);
	}
	
	/**
	 * 等级考试成绩查询结果
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getPjpyDjkscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getPjpyDjkscjResult(pjpyahjgxscjqryModel, dataSearchForm);
	}
	
	public int getPjpyDjkscjResultNum(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getPjpyDjkscjResultNum(pjpyahjgxscjqryModel);
	}
	
	/**
	 * 成绩同步
	 * @param cjlx
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cjTb(String cjlx, String xn, String xq) throws Exception {
		dao = new PjpyAhjgDAO();
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "cjb")) {
			return dao.cjbTb(xn, xq);
		} else {
			return dao.djksCjTb(xn, xq);
		}
	}
	
	/**
	 * 奖学金代码列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjList();
	}
	
	/**
	 * 根据TYPE来输出不用的条件列表0奖学金,1荣誉称号
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjTjList(int type) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjTjList(type);
	}
	
	/**
	 * 根据TYPE来输出不用的条件列表0奖学金,1荣誉称号
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdczList(int type) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZdczList(type);
	}
	
	/**
	 * 根据TYPE来输出不用的条件列表0奖学金,1荣誉称号
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdbjList(int type) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getZdbjList(type);
	}
	
	public List<HashMap<String, String>> getRych() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getRych();
	}
	
	/**
	 * 条件设置保存
	 * @param tjzdModel
	 * @return
	 * @throws Exception
	 */
	public boolean tjSave(TjszModel tjzdModel, HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.tjSave(tjzdModel, request);
	}
	
	/**
	 * 条件设置查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTjResult(String xmdm) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getTjResult(xmdm);
	}
	
	/**
	 * 条件设置查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTjTitle() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getTjTitle();
	}
	
	/**
	 * 条件设置批量删除
	 * @return
	 * @throws Exception
	 */
	public boolean tjszplDel() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.tjszplDel();
	}
	
	/**
	 * 条件设置单个删除
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean tjszdgDel(String pkValue) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.tjszdgDel(pkValue);
	}
	
	/**
	 * 通过学号获取该生的十佳大学生得分及排名
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String[] getStudfAndPm(String xh) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getStudfAndPm(xh);
	}
	
	public int getXjbjShjg(HttpServletRequest request) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXjbjShjg(request);
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscj(String xh) throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getXscj(xh);
	}
	
	public String[] getJxjsqxnxq() throws Exception {
		dao = new PjpyAhjgDAO();
		return dao.getJxjsqxnxq();
	}
	
	/**
	 * 查询教务版本 1为学年,0为过渡,空则为其他公司教务
	 * @return
	 */
	public String getJwFlag() {
		dao = new PjpyAhjgDAO();
		return dao.getJwFlag();
	}
	
	/**
	 * 获得学生考试性质列表
	 * 
	 * @author luojw
	 * @return
	 */
	public List<HashMap<String, String>> getKsxzList() {
		dao = new PjpyAhjgDAO();
		return dao.getKsxzList();
	}
	
	public List<HashMap<String, String>> getDjksmc() {
		dao = new PjpyAhjgDAO();
		return dao.getDjksmc();
	}
}
