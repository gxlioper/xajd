
package xgxt.pjpy.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学评奖评优Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class PjpyJgsdxService {

	PjpyJgsdxDAO dao = null;//数据操作DAO
	
	/**
	 * 获取专业奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZyjxjList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getZyjxjList();
	}
	
	/**
	 * 通过奖学金代码获取奖学金额
	 * getJxjJeByJxjdm ---- 通过奖学金代码获取奖学金额 
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String getJxjJeByJxjdm(String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjJeByJxjdm(jxjdm);
	}
	
	/**
	 * 专业奖学金查询表头
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZyjxjSbTitle(String isFdy, String userType) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();		
			
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			topList = dao.fdyZyjxjsbTitle();
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			/*if (StringUtils.isEqual(isFdy, "true")) {
				topList = dao.fdyZyjxjsbTitle();
			}else {
				topList = dao.xyZyjxjsbTitle();
			}*/
			topList = dao.xyZyjxjsbTitle();
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			topList = dao.xxZyjxjsbTitle();
		}
		return topList;
	}
	
	/**
	 * 专业奖学金查询结果
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZyjxjSbResult(String isFdy, String userType, ZyjxjQryModel zyjxjModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			resList = dao.fdyZyjxjsbResult(zyjxjModel);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			resList = dao.xyZyjxjsbResult(zyjxjModel);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			resList = dao.xxZyjxjsbResult(zyjxjModel);
		}
		return resList;
	}
	
	/**
	 * 通过主键获取综合素质和学习成绩
	 * getZhszandXxcjByPk ---- 通过主键获取综合素质和学习成绩
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszandXxcjByPk(String pkValue, String xh) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getZhszandXxcjByPk(pkValue, xh);
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
		dao = new PjpyJgsdxDAO();
		return dao.zhszandxxcjSave(zxsaveModel, request);
	}
	
	/**
	 * 专业奖学金审核辅导员，学院，学校
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean zyjxjSh(String isFdy, String userType, ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			dao.zyjxjShByFdy(zyjxjModel, request);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			dao.zyjxjShByXy(zyjxjModel, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			dao.zyjxjShByXx(zyjxjModel, request);
		}
		return bFlag;
	}
	
	/**
	 * 返回审核列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List getChkList(int type) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getChkList(type);
	}
	
	/**
	 * 专业奖学金批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String zyjxjblDel(String[] keys) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.zyjxjblDel(keys);
	}
	
	/**
	 * 专业奖学金打印列表
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zyjxjPrint(ZyjxjQryModel zyjxjModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		resList = dao.zyjxjPrint(zyjxjModel);
		return resList;
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
			en = new String[] {"wmbj" , "grrych"};
			cn = new String[] {"文明班级" ,"个人荣誉称号"};
		}else if (type == 2) {
			en = new String[] {"xjbj" };
			cn = new String[] {"文明班级"};
		}else if (type == 3) {
			en = new String[] {"wmbj" , "grrych", "grrych3"};
			cn = new String[] {"文明班级" ,"个人荣誉称号二级审核", "个人荣誉称号三级审核"};
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
	 * 验证数据是否重复
	 * chkDataByXjbj ---- 验证先进班级数据是否重复 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByWmbj(WmbjSqModel wmbjsqModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.chkDataByWmbj(wmbjsqModel);
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
		dao = new PjpyJgsdxDAO();
		return dao.saveWmBjInfo(xjbjSqModel, request);
	}
	
	/**
	 * 文明班级申请查询结果
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> wmbjcxTitle() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.wmbjCxJg();
	}
	
	/**
	 * 文明班级申请查询结果
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> wmbjsqCxJg(WmbjSqModel xjbjqryModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.wmbjsqCxJg(xjbjqryModel);
	}
	
	/**
	 * 文明班级批量删除
	 * delxjbjxx ---- 先进班级信息批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delWmbjXx(String[] keys) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.delWmbjXx(keys);
	}
	
	/**
	 * 通过主键获取文明班级信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjByPk(String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getWmbjByPk(pkValue);
	}
	
	/**
	 * 专业奖学金审核辅导员，学院，学校
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> wmbjShTitle(String isFdy, String userType, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			topList = dao.fdyQryWmbjTitle();
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			topList = dao.xyQryWmbjTitle();
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			topList = dao.xxQryWmbjTitle();
		}
		return topList;
	}
	
	/**
	 * 专业奖学金审核辅导员，学院，学校
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> wmbjShResult(String isFdy, String userType, WmbjSqModel wmbjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			resList = dao.fdyQryWmbjResult(wmbjModel);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			resList = dao.xyQryWmbjResult(wmbjModel);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			resList = dao.xxQryWmbjResult(wmbjModel);
		}
		return resList;
	}
	
	/**
	 * 获取文明班级单个审核显示信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShView(String isFdy, String userType,String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			resMap = dao.getWmbjShViewByFdy(pkValue);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			resMap = dao.getWmbjShViewByXy(pkValue);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			resMap = dao.getWmbjShViewByXx(pkValue);
		}
		return resMap;
	}
	
	/**
	 * 文明班级单个审核
	 * @param isFdy
	 * @param userType
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public boolean wmbjShByOne(String isFdy, String userType,WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			bFlag = dao.fdyShWmbjByone(wmbjModel, request);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			bFlag = dao.xyShWmbjByone(wmbjModel, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			bFlag = dao.xxShWmbjByone(wmbjModel, request);
		}
		return bFlag;
	}
	
	/**
	 * 文明班级集体审核
	 * @param isFdy
	 * @param userType
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean wmbjJtSh(String isFdy, String userType,String[] keys, String res, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")) {//辅导员
			dao.fdyWmbjjtSh(keys, res, request);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			dao.xyWmbjjtSh(keys, res, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			dao.xxWmbjjtSh(keys, res, request);
		}
		return bFlag;
	}
	
	/**
	 * 获取荣誉称号列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getRychList();
	}
	
	/**
	 * 获取奖学金申请学年年度
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqXnNd() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjsqXnNd();
	}
	
	/**
	 * 荣誉称号查询表头(辅导员，学院，学校)
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitle(String isFdy, String userType, String rychdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(rychdm);
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "文明大学生")
						|| StringUtils.isEqual(rychmc, "优秀毕业生")
						|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
						.isEqual(rychmc, "三好学生") || StringUtils.isEqual(rychmc, "优秀学生干部"))) {//辅导员
			topList = dao.getRychQryTitleByFdy();
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			topList = dao.getRychQryTitleByXy();
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			topList = dao.getRychQryTitleByXx();
		} else {//其它用户取学院表头
			topList = dao.getRychQryTitleByXy();
		}
		return topList;
	}
	
	/**
	 *  荣誉称号查询结果(辅导员，学院，学校)
	 * @param isFdy
	 * @param userType
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResult (String isFdy, String userType, RychQryModel rychModel, String rychdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(rychdm);
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "文明大学生")
						|| StringUtils.isEqual(rychmc, "优秀毕业生")
						|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
						.isEqual(rychmc, "三好学生") || StringUtils.isEqual(rychmc, "优秀学生干部"))) {//辅导员
			resList = dao.getRychQryResultByFdy(rychModel);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			if (StringUtils.isEqual(rychmc, "文明大学生")
					|| StringUtils.isEqual(rychmc, "优秀毕业生")
					|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
					.isEqual(rychmc, "三好学生") || StringUtils.isEqual(rychmc, "优秀学生干部")) {//三级审核
				resList = dao.getRychQryResultByXy3(rychModel);
			}else {//二级审核
				resList = dao.getRychQryResultByXy(rychModel);
			}
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			if (StringUtils.isEqual(rychmc, "文明大学生")
					|| StringUtils.isEqual(rychmc, "优秀毕业生")
					|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
					.isEqual(rychmc, "三好学生") || StringUtils.isEqual(rychmc, "优秀学生干部")) {//三级审核
				resList = dao.getRychQryResultByXx3(rychModel);
			}else {//二级审核
				resList = dao.getRychQryResultByXx(rychModel);
			}
		} else {//其它用户取学院结果
			resList = dao.getRychQryResultByXy(rychModel);
		}
		return resList;
	}
	
	/**
	 * 个人荣誉称号审核(辅导员，学院，学校)
	 * @param isFdy
	 * @param userType
	 * @param cbv
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public boolean grrychSh(String isFdy, String userType, GrrychShModel grrychModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		String rychmc = dao.getRychmc(grrychModel.getRychdm());
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "文明大学生")
						|| StringUtils.isEqual(rychmc, "优秀毕业生")
						|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
						.isEqual(rychmc, "三好学生") || StringUtils.isEqual(rychmc, "优秀学生干部"))) {//辅导员
			dao.fdyshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			dao.xyshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			dao.xxshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		} else {//其它用户取学院
			dao.xyshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		}
		return bFlag;
	}
	
	/**
	 * 获取荣誉称号相关信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfo(String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getRychInfo(pkValue);
	}
	
	/**
	 * 获取荣誉称号名称
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public String getRychMc(String rychdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getRychmc(rychdm);
	}
	
	/**
	 * 荣誉称号审核显示详细信息
	 * @param isFdy
	 * @param userType
	 * @param rychdm
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychshView(String isFdy, String userType, String rychdm, String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(rychdm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "文明大学生")
						|| StringUtils.isEqual(rychmc, "优秀毕业生")
						|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
						.isEqual(rychmc, "三好学生") || StringUtils.isEqual(rychmc, "优秀学生干部"))) {//辅导员
			resMap = dao.getRychInfoByFdy(pkValue);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			resMap = dao.getRychInfoByXy(pkValue);
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			resMap =  dao.getRychInfoByXx(pkValue);
		} else {//其它用户取学院
			resMap = dao.getRychInfoByXy(pkValue);
		}
		return resMap;
	}
	
	/**
	 * 个人荣誉称号单个审核
	 * @param isFdy
	 * @param userType
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean grrychModi(String isFdy, String userType, GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(grrychModel.getRychdm());
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "文明大学生")
						|| StringUtils.isEqual(rychmc, "优秀毕业生")
						|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
						.isEqual(rychmc, "三好学生") || StringUtils.isEqual(rychmc, "优秀学生干部"))) {//辅导员
			bFlag = dao.rychShByFdy(grrychModel, request);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//学院
			bFlag = dao.rychShByXy(grrychModel, request);
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//学校
			bFlag = dao.rychShByXx(grrychModel, request);
		} else {//其它用户取学院
			bFlag = dao.rychShByXy(grrychModel, request);
		}
		return bFlag;
	}
	
	/**
	 * 查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> qryRychTitle() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.qryRychTitle();
	}
	
	/**
	 * 查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> qryRychResult(RychSjQryModel rychModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.qryRychResult(rychModel);
	}
	
	/**
	 * 获取荣誉称号信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> rychInfoByPk(String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.rychInfoByPk(pkValue);
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
		dao = new PjpyJgsdxDAO();
		return dao.rychInfoModi(pkValue, rychdm, drzw, xh, request);
	}
	
	/**
	 * 荣誉称号信息批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychInfoDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.rychInfoDel(keys, request);
	}
	
	/**
	 * 获取奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjList();
	}
	
	/**
	 * 获取字段列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getZdList();
	}
	
	/**
	 * 奖学金对应条件
	 * @param xn
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxjsztj(String xn, String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjsztj(xn, jxjdm);
	}
	
	/**
	 * 奖学金条件查询 表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjsztjTitle() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjsztjTitle();
	}
	
	/**
	 * 奖学金条件设置删除
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjDel(String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.jxjsztjDel(pkValue, request);
	}
	
	/**
	 * 奖学金条件设置保存
	 * @param tjszModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjSave(JxjtjszSaveModel tjszModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.jxjsztjSave(tjszModel, request);
	}
	
	/**
	 * 条件设置单个详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTjInfo(String pkValue, String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getTjInfo(pkValue, jxjdm);
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
		dao = new PjpyJgsdxDAO();
		return dao.tjEditResult(pkValue, tjszModel, request);
	}
	
	/**
	 * 获取字段名
	 * @param tjzdm
	 * @return
	 * @throws Exception
	 */
	public String getZdm(String tjzdm) throws Exception{
		String zdm = "";
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "zhszcpzf")) {
			zdm = "综合素质测评总分";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "rownum")) {
			zdm = "综合素质测评排名(名)";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "cj")) {
			zdm = "课程单科成绩";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "pjf")) {
			zdm = "课程平均成绩";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "dcj")) {
			zdm = "德成绩";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "zcj")) {
			zdm = "智成绩";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "tcj")) {
			zdm = "体成绩";
		}
		return zdm;
	}
	
	/**
	 * 获取字段操作
	 * @param zdcz
	 * @return
	 * @throws Exception
	 */
	public String getZdcz(String zdcz) throws Exception {
		String cz = "";
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "sum")) {
			cz = "总和";
		}
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "avg")) {
			cz = "平均值";
		}
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "max")) {
			cz = "最大值";
		}
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "min")) {
			cz = "最小值";
		}
		return cz;
	}
	
	/**
	 * 获取条件及值
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String[] getTjValue(String tj) throws Exception {
		String[] tjValue = new String[2];
		if (!StringUtils.isNull(tj)) {
			tjValue[0] = tj.substring(0, tj.indexOf("'"));
			tjValue[1] = tj.substring(tj.indexOf("'") + 1, tj.lastIndexOf("'"));
		}
		return tjValue;
	}
	
	/**
	 * 获取条件
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String getTj(String tj) throws Exception {
		String tjen = "";
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, "=")) {
			tjen = "=";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, ">=")) {
			tjen = "&gt;=";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, ">")) {
			tjen = "&gt;";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, "<=")) {
			tjen = "&lt;=";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, "<")) {
			tjen = "&lt;";
		}
		return tjen;
	}
	
	public String getJxjTjsz(String xn, String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjTjsz(xn, jxjdm);
	}
	
	/**
	 * 获取奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList1() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjList1(dao.getJxjList());
	}
	
	/**
	 * 专业奖学金自动审核
	 * @param zyjxjModel
	 * @throws Exception
	 */
	public void zyjxjAutoSh(ZyjxjAutoshModel zyjxjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		String[] jxjsqxnxq = dao.getJxjSqxnxq();
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			zyjxjModel.setXn(jxjsqxnxq[0]);
			zyjxjModel.setXq(jxjsqxnxq[1]);
		}
		dao.zyjxjAutoSh(zyjxjModel, request);
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getStuInfo(xh);
	}

}
