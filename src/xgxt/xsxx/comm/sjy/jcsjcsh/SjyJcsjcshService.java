package xgxt.xsxx.comm.sjy.jcsjcsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.utils.MakeQuery;
import xgxt.xsxx.comm.XsxxCommService;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.sjy.jcsjsz.SjyJcsjszForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_数据源_基础数据初始化_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SjyJcsjcshService extends XsxxCommService {

	SjyJcsjcshDAO dao = new SjyJcsjcshDAO();

	/**
	 * 获得初始化项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCshXmList(SjyJcsjcshForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", "xy");
		map.put("xmmc", "部门信息");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("xmdm", "zy");
		map.put("xmmc", "专业信息");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("xmdm", "bj");
		map.put("xmmc", "班级信息");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("xmdm", "xsjbxx");
		map.put("xmmc", "学生基本信息");
		list.add(map);

//		map = new HashMap<String, String>();
//		map.put("xmdm", "xsqtxx");
//		map.put("xmmc", "学生其他信息");
//		list.add(map);

		return list;
	}

	/**
	 * 获得初始化结果列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getCshInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = null;

		// 操作项目
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//学院
			list = getXyInfoList(model, user, colList);
		}else if ("zy".equalsIgnoreCase(czxm)) {//专业
			list = getZyInfoList(model, user, colList);
		}else if ("bj".equalsIgnoreCase(czxm)) {//班级
			list = getBjInfoList(model, user, colList);
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {//学生基本信息
			list = getStuInfoList(model, user, colList);
		}


		return list;
	}

	/**
	 * 获得学院信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXyInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "bmjb", "bmlb" };
		String[] queryLikeList = new String[] { "bmdm", "bmmc", "bmfdm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 表
		String tableName = "xg_view_jcsj_bmxx";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * 获得专业信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZyInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] {"bmdm"};
		String[] queryLikeList = new String[] { "zydm", "zymc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 表
		String tableName = "xg_view_jcsj_zyxx";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * 获得班级信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getBjInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "nj", "zydm" };
		String[] queryLikeList = new String[] { "bjdm", "bjmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 表
		String tableName = "xg_view_jcsj_bjxx";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * 获得学生基本信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getStuInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "nj", "xydm", "bjdm", "zydm" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 表
		String tableName = "xg_view_xsjbxx_temp";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * 同步信息
	 * 
	 * @throws Exception
	 */
	public boolean tbInfo(SjyJcsjcshForm model, User user) throws Exception {

		boolean flag = false;
		// 操作项目
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {// 学院
			flag = dao.tbBmInfo();
		} else if ("zy".equalsIgnoreCase(czxm)) {// 专业
			flag = dao.tbZyInfo();
		} else if ("bj".equalsIgnoreCase(czxm)) {// 班级
			flag = dao.tbBjInfo();
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {// 学生基本信息
			flag = dao.tbStuInfo();
		}

		return flag;
	}

	/**
	 * 整体提交
	 * 
	 * @throws Exception
	 */
	public boolean allSubmit(SjyJcsjcshForm model, User user) throws Exception {

		boolean flag = false;
		// 操作项目
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//部门信息
			flag = dao.allSubmitByXy();
		}else if ("zy".equalsIgnoreCase(czxm)) {//专业
			flag = dao.allSubmitByZy();
		}else if ("bj".equalsIgnoreCase(czxm)) {//班级
			flag = dao.allSubmitByBj();
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {//学生信息
			flag = dao.allSubmitByStu();
		}

		return flag;
	}
	
	/**
	 * 勾选提交
	 * 
	 * @throws Exception
	 */
	public boolean submitCheckInfo(SjyJcsjcshForm model, User user) throws Exception {

		boolean flag = false;
		// 操作项目
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//部门信息
			flag = dao.submitXyInfo(model);
		}else if ("zy".equalsIgnoreCase(czxm)) {//专业
			flag = dao.submitZyInfo(model);
		}else if ("bj".equalsIgnoreCase(czxm)) {//班级
			flag = dao.submitBjInfo(model);
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {//学生基本信息
			flag = dao.submitStuInfo(model);
		}

		return flag;
	}
	
	/**
	 * 执行规则
	 * 
	 * @throws Exception
	 */
	public boolean doRule(SjyJcsjcshForm model, User user) throws Exception {

		return dao.doRule();
	}
	
	/**
	 * 构建规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getRuleList(SjyJcsjcshForm model)
			throws Exception {

		List<HashMap<String, Object>> list = null;

		// 稍微处理一下数据
		List<HashMap<String, String>> oriList = new ArrayList<HashMap<String, String>>();

		// 操作项目
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//学院

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "bmdmb_bmlb");
			map.put("mc", "部门类别");
			map.put("color", judgeBmlb());// 判断部门类别是否合法

			oriList.add(map);

			list = createTableList(oriList, czxm);

		} else if ("zy".equalsIgnoreCase(czxm)) {//专业

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "zydmb_bmdm");
			map.put("mc", "上级院系");
			map.put("color", judgeSjxy());// 判断上级部门是否合法

			oriList.add(map);

			list = createTableList(oriList, czxm);
			
		}else if ("bj".equalsIgnoreCase(czxm)) {//班级

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "bjdmb_zydm");
			map.put("mc", "所属专业");
			map.put("color", judgeZydm());// 判断所属专业是否合法

			oriList.add(map);

			list = createTableList(oriList, czxm);
		} else if ("xsjbxx".equalsIgnoreCase(czxm)) {// 学生基本信息

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "xsxxb_bjdm");
			map.put("mc", "所属班级");
			map.put("color", judgeBjdm());// 判断学生性别是否合法

			oriList.add(map);

			map = new HashMap<String, String>();
			map.put("dm", "xsxxb_xb");
			map.put("mc", "性别");
			map.put("color", judgeXb());// 判断学生性别是否合法

			oriList.add(map);
			
			map = new HashMap<String, String>();
			map.put("dm", "xsxxb_xjztm");
			map.put("mc", "学籍状态");
			map.put("color", judgeXjzt());// 判断学生学籍是否合法

			oriList.add(map);
			
			map = new HashMap<String, String>();
			map.put("dm", "xsxxb_xzqkm");
			map.put("mc", "行政区块");
			map.put("color", judgeXzqk());// 判断行政区块是否合法

			oriList.add(map);
			
			list = createTableList(oriList, czxm);
		}


		return list;
	}

	/**
	 * 构建tanbleList
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, Object>> createTableList(
			List<HashMap<String, String>> oriList, String czxm) {

		// 处理后列表
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		if (oriList != null && oriList.size() > 0) {

			// 默认行数
			int defTrNum = 12;
			// 表单行数
			int trNum = 0;
			// 表单列数
			int tdNum = 4;
			// 补空格列
			int spaceNum = 0;
			// 有数据的最后行
			int lastTr = 0;
			// 代码
			String dm = "dm";
			// 名称
			String mc = "mc";

			if (oriList.size() % tdNum == 0) {
				trNum = oriList.size() / tdNum;
			} else {
				trNum = oriList.size() / tdNum + 1;
				spaceNum = tdNum - oriList.size() % tdNum;
			}

			lastTr = trNum;

			if (trNum < defTrNum) {
				trNum = defTrNum;
			}

			for (int i = 1; i <= trNum; i++) {

				HashMap<String, Object> trMap = new HashMap<String, Object>();

				List<HashMap<String, String>> tdList = new ArrayList<HashMap<String, String>>();

				int n = 0;

				for (int j = 0; j < oriList.size(); j++) {
					if (!"yes".equalsIgnoreCase(oriList.get(j).get("used"))) {
						if (n < tdNum) {
							tdList.add(oriList.get(j));
							oriList.get(j).put("used", "yes");
							n++;
						} else {
							break;
						}
					}
				}

				if (i == lastTr) {
					for (int j = 0; j < spaceNum; j++) {
						HashMap<String, String> spaceMap = new HashMap<String, String>();
						spaceMap.put(dm, "");
						spaceMap.put(mc, "&nbsp;");
						tdList.add(spaceMap);
					}
				}

				trMap.put("trNum", String.valueOf(i));

				if (tdList != null && tdList.size() > 0) {
					trMap.put("tdList", tdList);
				} else {
					for (int j = 0; j < tdNum; j++) {
						HashMap<String, String> spaceMap = new HashMap<String, String>();
						spaceMap.put(dm, "");
						spaceMap.put(mc, "&nbsp;");
						tdList.add(spaceMap);
					}
					trMap.put("tdList", tdList);
				}

				list.add(trMap);
			}

		}
		return list;
	}

	/**
	 * 保存规则制定
	 * 
	 * @throws Exception
	 */
	public boolean saveRule(SjyJcsjcshForm model, User user) throws Exception {

		// 宿舍信息表
		String tableName = "xg_jcsj_gzzdb";
		// 主键
		String pk = "zd||lyb||zdq";
		// 主键值
		List<String> pkList = new ArrayList<String>();
		// 单个字段
		String[] onezd = new String[] { "zd", "lyb" };
		// 批量字段
		String[] arrzd = new String[] { "zdq", "zdh" };
		// 不可为空
		String[] notnull = new String[] { "zdh" };

		boolean flag = true;
		
		// 指定前
		String[] zdq = model.getZdq();

		if (zdq != null && zdq.length > 0) {

			String pkValue = "";

			for (int i = 0; i < zdq.length; i++) {
				pkValue = model.getZd() + model.getLyb() + zdq[i];
				pkList.add(pkValue);
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkList.toArray(new String[] {}));
			saveForm.setOnezd(onezd);
			saveForm.setArrzd(arrzd);
			saveForm.setNotnull(notnull);

			flag = saveInfoToDb(saveForm, model, user);
		}
		
		return flag;
	}
	
	// ====================不同规则的正确与否判断=========================================
	
	/**
	 * 返回规则颜色（green：合法 red：非法）
	 * 
	 * @author 伟大的骆
	 */
	private String getRuleColor(String[] rule, List<HashMap<String, String>> list) {
		// 是否合法
		boolean isHf = true;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				String bmlb = list.get(i).get("ruledm");

				boolean flag = false;

				for (int j = 0; j < rule.length; j++) {
					if (bmlb.equalsIgnoreCase(rule[j])) {
						flag = true;
						break;
					}
				}

				if (!flag) {
					isHf = false;
				}
			}
		}

		if (isHf) {
			return "green";
		} else {
			return "red";
		}
	}
	
	/**
	 * 判断部门类别是否符合规则
	 * 
	 * @author 伟大的骆
	 */
	private String judgeBmlb() {

		// 合法部门类别(1:校级 5:院级)
		String[] rule = new String[] { "1", "5" };

		// 临时表中的部门类别
		List<HashMap<String, String>> list = dao.getBmlb();

		return getRuleColor(rule, list);
	}

	/**
	 * 判断上级院系是否符合规则
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	private String judgeSjxy() throws Exception {

		List<HashMap<String, String>> xyList = dao.getXyList();
		// 合法学院(数据源取自 xg_jcsj_bmdmb )
		List<String> rule = new ArrayList<String>();

		if (xyList != null && xyList.size() > 0) {
			for (int i = 0; i < xyList.size(); i++) {
				rule.add(xyList.get(i).get("bmdm"));
			}
		}
		// 临时表中的上级部门
		List<HashMap<String, String>> list = dao.getBmdm();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * 判断所属专业是否符合规则
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	private String judgeZydm() throws Exception {

		List<HashMap<String, String>> zyList = dao.getZyList();
		// 合法学院(数据源取自 xg_jcsj_zydmb )
		List<String> rule = new ArrayList<String>();

		if (zyList != null && zyList.size() > 0) {
			for (int i = 0; i < zyList.size(); i++) {
				rule.add(zyList.get(i).get("zydm"));
			}
		}
		// 临时表中的所属专业
		List<HashMap<String, String>> list = dao.getZydm();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * 判断所属班级是否符合规则
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	private String judgeBjdm() throws Exception {

		List<HashMap<String, String>> bjList = dao.getBjList();
		// 合法学院(数据源取自 xg_jcsj_bjdmb )
		List<String> rule = new ArrayList<String>();

		if (bjList != null && bjList.size() > 0) {
			for (int i = 0; i < bjList.size(); i++) {
				rule.add(bjList.get(i).get("bjdm"));
			}
		}
		// 临时表中的所属专业
		List<HashMap<String, String>> list = dao.getBjdm();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * 判断学生性别是否符合规则
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private String judgeXb() throws Exception {

		// 合法部门类别(男，女)
		String[] rule = new String[] { "男", "女" };

		// 临时表中的性别
		List<HashMap<String, String>> list = dao.getStuXb();

		return getRuleColor(rule, list);
	}
	
	/**
	 * 判断学籍状态是否符合规则
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	private String judgeXjzt() throws Exception {

		List<HashMap<String, String>> xjztList = dao.getXjztList();
		// 合法学院(数据源取自 dm_zju_xjzt )
		List<String> rule = new ArrayList<String>();

		if (xjztList != null && xjztList.size() > 0) {
			for (int i = 0; i < xjztList.size(); i++) {
				rule.add(xjztList.get(i).get("zxdmmc"));
			}
		}
		// 临时表中的学籍状态
		List<HashMap<String, String>> list = dao.getXjzt();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * 判断行政区块是否符合规则
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private String judgeXzqk() throws Exception {

		// 临时表中的行政区块
		List<HashMap<String, String>> list = dao.getXzqk();

		String colour = "green";

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// 省市县
				String ssx = map.get("ssx");
				// 省
				String sheng = map.get("sheng");
				String shengmc = map.get("shengmc");
				// 市
				String shi = map.get("shi");
				String shimc = map.get("shimc");
				// 县
				String xian = map.get("xian");
				String xianmc = map.get("xianmc");
				// 制定前
				String zdq = map.get("zdq");
				// 制定后
				String zdh = map.get("zdh");
				
				//未制定
				if(Base.isNull(zdh)){
					if(!Base.isNull(sheng) && Base.isNull(shengmc)){
						colour = "red";
						break;
					}else if(!Base.isNull(shi) && Base.isNull(shimc)){
						colour = "red";
						break;
					}else if(!Base.isNull(xian) && Base.isNull(xianmc)){
						colour = "red";
						break;
					}
				}
			}
		}

		return colour;
	}
	// ====================不同规则的正确与否判断end======================================

	// ====================不同规则的规则制定=========================================
	/**
	 * 构建规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRuleZdList(SjyJcsjcshForm model)
			throws Exception {

		List<HashMap<String, String>> list = null;

		// 规则
		String rule = model.getRule();

		if ("bmdmb_bmlb".equalsIgnoreCase(rule)) {// 部门类别
			list = getBmlbRuleList();
		} else if ("zydmb_bmdm".equalsIgnoreCase(rule)) {// 上级院系
			list = getBmdmRuleList();
		} else if ("bjdmb_zydm".equalsIgnoreCase(rule)) {// 专业代码
			list = getZydmRuleList();
		}else if ("xsxxb_xb".equalsIgnoreCase(rule)) {// 性别
			list = getXbRuleList();
		}else if ("xsxxb_bjdm".equalsIgnoreCase(rule)) {// 班级代码
			list = getBjdmRuleList();
		}else if ("xsxxb_xjztm".equalsIgnoreCase(rule)) {// 学籍状态
			list = getXjztRuleList();
		}else if ("xsxxb_xzqkm".equalsIgnoreCase(rule)) {// 行政区块
			list = getXzqkRuleList();
		}

		return list;
	}

	/**
	 * 构建规则列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> setRuleList(String[] hfdm,
			String[] hfmc, List<HashMap<String, String>> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				String ruledm = list.get(i).get("ruledm");

				boolean flag = false;

				for (int j = 0; j < hfdm.length; j++) {
					if (hfdm[j].equalsIgnoreCase(ruledm)) {
						list.get(i).put("mc", hfmc[j]);
						flag = true;
						break;
					}
				}

				if (flag) {
					list.get(i).put("isHf", "yes");
				} else {
					list.get(i).put("isHf", "no");
				}
			}
		}

		return list;
	}
	
	/**
	 * 获得部门类别规则列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getBmlbRuleList() {

		// 合法部门类别(1:校级 5:院级)
		String[] hfdm = new String[] { "1", "5" };
		String[] hfmc = new String[] { "校级(1)", "院级(5)" };
		
		// 临时表中的部门类别
		List<HashMap<String, String>> list = dao.getBmlb();

		return setRuleList(hfdm, hfmc, list);
	}
	
	/**
	 * 获得部门代码规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getBmdmRuleList() throws Exception {

		List<HashMap<String, String>> xyList = dao.getXyList();
		// 合法学院(数据源取自 xg_jcsj_bmdmb )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (xyList != null && xyList.size() > 0) {
			for (int i = 0; i < xyList.size(); i++) {
				hfdm.add(xyList.get(i).get("bmdm"));
				hfmc.add(xyList.get(i).get("bmmc"));
			}
		}

		// 临时表中的部门类别
		List<HashMap<String, String>> list = dao.getBmdm();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * 获得专业代码规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getZydmRuleList() throws Exception {

		List<HashMap<String, String>> zyList = dao.getZyList();
		// 合法学院(数据源取自 xg_jcsj_bmdmb )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (zyList != null && zyList.size() > 0) {
			for (int i = 0; i < zyList.size(); i++) {
				hfdm.add(zyList.get(i).get("zydm"));
				hfmc.add(zyList.get(i).get("zymc"));
			}
		}

		// 临时表中的专业列表
		List<HashMap<String, String>> list = dao.getZydm();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * 获得班级代码规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getBjdmRuleList() throws Exception {

		List<HashMap<String, String>> bjList = dao.getBjList();
		// 合法学院(数据源取自 xg_jcsj_bmdmb )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (bjList != null && bjList.size() > 0) {
			for (int i = 0; i < bjList.size(); i++) {
				hfdm.add(bjList.get(i).get("bjdm"));
				hfmc.add(bjList.get(i).get("bjmc"));
			}
		}

		// 临时表中的班级列表
		List<HashMap<String, String>> list = dao.getBjdm();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * 获得性别规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getXbRuleList() throws Exception {

		// 合法性别
		String[] hfdm = new String[] { "男", "女" };
		String[] hfmc = new String[] { "男", "女" };

		// 临时表中的部门类别
		List<HashMap<String, String>> list = dao.getStuXb();

		return setRuleList(hfdm, hfmc, list);
	}
	
	/**
	 * 获得学籍状态规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getXjztRuleList() throws Exception {

		List<HashMap<String, String>> xjztList = dao.getXjztList();
		// 合法学院(数据源取自 dm_zju_xjzt )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (xjztList != null && xjztList.size() > 0) {
			for (int i = 0; i < xjztList.size(); i++) {
				hfdm.add(xjztList.get(i).get("zxdmmc"));
				hfmc.add(xjztList.get(i).get("zxdmmc"));
			}
		}

		// 临时表中的学籍状态列表
		List<HashMap<String, String>> list = dao.getXjzt();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * 获得行政区块规则列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getXzqkRuleList() throws Exception {

		// 临时表中的行政区块
		List<HashMap<String, String>> list = dao.getXzqk();

		List<HashMap<String, String>> ruleList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// 省市县
				String ssx = map.get("ssx");
				// 省
				String sheng = map.get("sheng");
				String shengmc = map.get("shengmc");
				shengmc = Base.isNull(shengmc) ? "" : shengmc;
				// 市
				String shi = map.get("shi");
				String shimc = map.get("shimc");
				shimc = Base.isNull(shimc) ? "" : shimc;
				// 县
				String xian = map.get("xian");
				String xianmc = map.get("xianmc");
				xianmc = Base.isNull(xianmc) ? "" : xianmc;
				// 制定前
				String zdq = map.get("zdq");
				// 制定后
				String zdh = map.get("zdh");
				
				String zdsheng = map.get("zdsheng");
				zdsheng = Base.isNull(zdsheng) ? "" : zdsheng;
				String zdshi = map.get("zdshi");
				zdshi = Base.isNull(zdshi) ? "" : zdshi;
				String zdxian = map.get("zdxian");
				zdxian = Base.isNull(zdxian) ? "" : zdxian;
				
				boolean flag = true;
				
				String mc = shengmc + shimc + xianmc;
				
				// 未制定
				if (Base.isNull(zdh)) {
					
					boolean shen_flag = true;
					boolean shi_flag = true;
					boolean xian_flag = true;
					
					if (!Base.isNull(sheng) && Base.isNull(shengmc)) {
						shen_flag = false;
					}
					if (!Base.isNull(shi) && Base.isNull(shimc)) {
						shi_flag = false;
					}
					if (!Base.isNull(xian) && Base.isNull(xianmc)) {
						xian_flag = false;
					}
					
					if (shen_flag && shi_flag && xian_flag) {
						flag = true;
					}else{
						flag = false;
					}
					
					mc = shengmc + shimc + xianmc;
				}else{
					mc = zdsheng + zdshi + zdxian;
				}
				
				
				
				map.put("dm", ssx);
				map.put("mc", mc);
				map.put("isHf", flag ? "yes" : "no");
				
				if(!Base.isNull(ssx)){
					ruleList.add(map);
				}
			}
		}

		return ruleList;
	}
	// ====================不同规则的规则制定end======================================
	
	/**
	 * 根据录入获取部门列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmOption(String bmmc) {
		return dao.getBmOption(bmmc);
	}
	
	/**
	 * 根据录入获取年级列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getNjOption(String nj) {
		return dao.getNjOption(nj);
	}
	
	/**
	 * 根据录入获取专业列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZyOption(String zymc,
			String[] searchTj) {
		return dao.getZyOption(zymc, searchTj);
	}
	
	
	/**
	 * 根据录入获取班级列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBjOption(String bjmc,
			String[] searchTj) {
		return dao.getBjOption(bjmc, searchTj);
	}
	
	
	/**
	 * 根据录入获取学籍状态列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXjOption(String xjzt) {
		return dao.getXjOption(xjzt);
	}
	
	/**
	 * 根据录入获取行政区块列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXzqkOption(String xzqmc, String lx,
			String[] searchTj) {
		return dao.getXzqkOption(xzqmc, lx, searchTj);
	}
}