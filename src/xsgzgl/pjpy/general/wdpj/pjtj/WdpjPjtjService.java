package xsgzgl.pjpy.general.wdpj.pjtj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Globals;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlService;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_评奖条件_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class WdpjPjtjService extends CommService implements WdpjPjtjInterface {

	WdpjPjtjDAO dao = new WdpjPjtjDAO();

	// ===============提供申请审核控制 begin===============================
	/**
	 * 检测申请资格
	 * 
	 * @author 伟大的骆
	 */
	public String checkSqzg(PjpyWdpjModel model, User user) throws Exception {

		// 学号
		String xh = model.getXh();
		// 项目代码
		String xmdm = model.getXmdm();

		// 评奖项目Service
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		// 提示信息
		String message = "";
		// ========== 检测学生是否在人员库中begin =====================

		message = checkRyk(xh);
		if (!Base.isNull(message)) {
			return message;
		}
		// ========== 检测学生是否在人员库中end========================
		
		
		// ==================检测申请时间begin========================

		// 项目时间信息
		HashMap<String, String> xmsjInfo = dao.getXmsjInfo(xmdm);

		message = checkSqsj(xmsjInfo);
		if (!Base.isNull(message)) {
			return message;
		}
		// ==================检测申请时间end========================

		// ==================检测评奖条件begin========================
		// 评奖条件列表
		List<HashMap<String, String>> pjtjList = getPjtjList(model);

		message = checkSqtj(xh, xmdm, pjtjList);
		if (!Base.isNull(message)) {
			return message;
		}
		// ==================检测评奖条件end========================

		// ==================检测人数控制 begin========================
		// 人数设置
		String rssz = pjxmModel.getRssz();
		// 人数控制
		String rskz = pjxmModel.getRskz();

		if ("yes".equalsIgnoreCase(rssz) && "sqsb".equalsIgnoreCase(rskz)) {
			message = checkRskz(xh, model, pjxmModel);

			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================检测人数控制end========================

		// ==================检测项目兼得begin========================
		// 兼得控制
		String jdkz = pjxmModel.getJdkz();

		if ("sqsb".equalsIgnoreCase(jdkz) || Base.isNull(jdkz)) {
			message = checkXmjd(xh, model);
			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================检测项目兼得end========================

		return message;
	}

	/**
	 * 检测审核资格
	 * 
	 * @author 伟大的骆
	 */
	public String checkShzg(PjpyWdpjModel model, User user) throws Exception {

		// 学号
		String[] xh = model.getSqr();
		// 项目代码
		String xmdm = model.getXmdm();
		// 审核岗位ID
		String gwid = model.getGwid();

		// 评奖项目Service
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		// 被审核人信息
		List<HashMap<String, String>> bshrList = dao.getBshrList(xh);
		
		// 提示信息
		String message = "";

		// ==================检测人数控制 begin========================
		// 人数设置
		String rssz = pjxmModel.getRssz();
		// 人数控制
		String rskz = pjxmModel.getRskz();

		if ("yes".equalsIgnoreCase(rssz) && gwid.equalsIgnoreCase(rskz)) {
			message = checkRskz(xh, bshrList, model, pjxmModel);
			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================检测人数控制end========================

		// ==================检测项目兼得begin========================
		// 兼得控制
		String jdkz = pjxmModel.getJdkz();

		if (gwid.equalsIgnoreCase(jdkz)) {
			message = checkXmjd(xh, bshrList, model, pjxmModel);
			if (!Base.isNull(message)) {
				return message;
			}
		}
		// ==================检测项目兼得end========================

		//message = "1";
		return message;
	}
	// ===============提供申请审核控制 end===============================

	
	/**
	 * 检测学生是否在人员库中
	 * 
	 * @author qlj
	 */
	private String checkRyk(String xh) {

		StringBuilder message=new StringBuilder();
		
		HashMap<String,String>rykMap=dao.checkRyk(xh);

		if (rykMap!=null && rykMap.size()>0) {// 指定学生在人员库中
			
		} else {
			message.append("对不起，您<font color=\"blue\">没有评奖评优参评资格</font>。如有问题请与管理员联系！");
		}

		return message.toString();
	}
	
	// ===============时间检测 begin===============================
	/**
	 * 检测申请时间
	 * 
	 * @author 伟大的骆
	 */
	private String checkSqsj(HashMap<String, String> xmsjInfo) {

		// 提示信息
		StringBuilder message = new StringBuilder();
		// 目前时间
		String nowtime = xmsjInfo.get("nowtime");
		// 开始时间
		String kssj = xmsjInfo.get("sqkssj");
		// 结束时间
		String jssj = xmsjInfo.get("sqjssj");
		// 控制开关
		String kzkg = xmsjInfo.get("sqkzkg");
		// 备注
		String bz = xmsjInfo.get("bz");

		if (!Base.isNull(kssj)
				&& (Integer.parseInt(kssj) > Integer.parseInt(nowtime))) {// 当前时间与开始时间比较
			message.append("该项目申请时间开始时间为");
			message.append(kssj);
			message.append(",目前无法申请");
		} else if (!Base.isNull(jssj)
				&& (Integer.parseInt(jssj) <Integer.parseInt(nowtime))) {// 当前时间与结束时间比较
			message.append("该项目申请截止时间为");
			message.append(jssj);
			message.append(",目前已无法申请");
		} else if (!"yes".equalsIgnoreCase(kzkg)) {// 开关控制

			message.append("该项目目前关闭申请中，无法进行申请");

			if (!Base.isNull(bz)) {// 判断备注
				message.append("<br/>");
				message.append("备注：");
				message.append(bz);
			}
		}

		return message.toString();
	}
	
	/**
	 * 检测审核时间
	 * 
	 * @author 伟大的骆
	 */
	private String checkShsj(HashMap<String, String> xmsjInfo) {

		// 提示信息
		StringBuilder message = new StringBuilder();
		// 目前时间
		String nowtime = xmsjInfo.get("nowtime");
		// 开始时间
		String kssj = xmsjInfo.get("shkssj");
		// 结束时间
		String jssj = xmsjInfo.get("shjssj");
		// 控制开关
		String kzkg = xmsjInfo.get("shkzkg");
		// 备注
		String bz = xmsjInfo.get("bz");

		if (!Base.isNull(kssj)
				&& (Float.parseFloat(kssj) > Float.parseFloat(nowtime))) {// 当前时间与开始时间比较
			message.append("该项目审核时间开始时间为");
			message.append(kssj);
			message.append(",目前无法审核");
		} else if (!Base.isNull(jssj)
				&& (Float.parseFloat(jssj) < Float.parseFloat(nowtime))) {// 当前时间与结束时间比较
			message.append("该项目审核截止开始时间为");
			message.append(jssj);
			message.append(",目前已无法申请");
		} else if (!"yes".equalsIgnoreCase(kzkg)) {// 开关控制

			message.append("该项目目前关闭审核中，无法进行审核");

			if (!Base.isNull(bz)) {// 判断备注
				message.append("<br/>");
				message.append("备注：");
				message.append(bz);
			}
		}

		return message.toString();
	}
	 
	/**
	 * 检测指定项目是否可审核
	 * @author qlj
	 */
	public boolean  checkShkz(String xmdm) {
		
		String message="";
		
		// 项目时间信息
		HashMap<String, String> xmsjInfo = dao.getXmsjInfo(xmdm);

		// 项目审核控制判断(审核开关、审核时间)
		message = checkShsj(xmsjInfo);
		if (!Base.isNull(message)) {
			return false;
		}
		
		return true;
		
	}
	// ===============时间检测 end===============================

	// ===============条件检测 begin===============================
	/**
	 * 获得评奖条件列表
	 * 
	 * @author 伟大的骆
	 */
	@SuppressWarnings("unchecked")
	private List<HashMap<String, String>> getPjtjList(PjpyWdpjModel model) {

		// 班级大类Service
		PjszBjdlService bjdlService = new PjszBjdlService();
		// 学号
		String xh = model.getXh();
		// 项目代码
		String xmdm = model.getXmdm();
		// 班级大类
		String bjdl = bjdlService.getBjdl(xh);

		List<HashMap<String, String>> pjtjList = dao.getPjtjList(xmdm);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (pjtjList != null && pjtjList.size() > 0) {

			HashMap<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < pjtjList.size(); i++) {
				HashMap<String, String> pjtjMap = pjtjList.get(i);
				String tjdm = pjtjMap.get("tjdm");
				String tjfw = pjtjMap.get("tjfw");

				if ("all".equalsIgnoreCase(tjfw)) {
					map.put(tjdm, pjtjMap);
				}

				if (tjfw.equalsIgnoreCase(bjdl)) {
					map.put(tjdm, pjtjMap);
				}

			}

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				@SuppressWarnings("unused")
				String paramName = entry.getKey();
				HashMap<String, String> paramValue = (HashMap<String, String>) entry
						.getValue();
				list.add(paramValue);
			}

		}

		return list;
	}

	/**
	 * 检测申请条件
	 * 
	 * @author 伟大的骆
	 */
	private String checkSqtj(String xh, String xmdm,
			List<HashMap<String, String>> pjtjList) {

		// 提示信息
		String message = "";

		if (pjtjList != null && pjtjList.size() > 0) {
			for (int i = 0; i < pjtjList.size(); i++) {
				HashMap<String, String> tjMap = pjtjList.get(i);
				message = checkSqtj(xh, xmdm, tjMap);
				if (!Base.isNull(message)) {
					break;
				}
			}
		}

		return message;
	}

	/**
	 * 检测申请条件【详细】
	 * 
	 * @author 伟大的骆
	 */
	@SuppressWarnings("unchecked")
	private String checkSqtj(String xh, String xmdm, Map<String, String> map) {

		// 提示信息
		String message = "";
		// 条件类型
		String tjlx = map.get("tjlx");
		// 条件名称
		String tjmc = map.get("tjmc");
		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs)?"":tsgs;
		
		if ("no".equalsIgnoreCase(tjms)) {
			tjms = tjmc + gx + tjz + tsgs;
			map.put("tjms", tjms);
		}
		
		if ("logicRelation".equalsIgnoreCase(tjlx)) {// 逻辑关系
			message = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// 反向逻辑关系
			message = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// 最小分关系
			message = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// 平均分关系
			message = judgeAvgRelation(xh, map);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// 某情况关系
			message = judgeInstanceReverse(xh, xmdm, map);
		} else {// 调用方法单独处理
			try {
				map.put("xh", xh);
				WdpjPjtjMethod tjszMethod = new WdpjPjtjMethod();
				Class myClass = tjszMethod.getClass();
				message = (String) myClass.getMethod(tjlx,
						(Class[]) new Class[] { HashMap.class }).invoke(
						tjszMethod, map);
			} catch (Exception e) {
				System.out.println("评奖条件设置,method:" + tjlx + "遇到问题;");
				e.printStackTrace();
			}
		}

		return message;
	}

	/**
	 * 判断逻辑关系
	 * 
	 * @author 伟大的骆
	 */
	private String judgeLogicRelation(String xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		String bjz = dao.getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		try {
			//////百分比情况，仅限素质测评排名。学校个性化，北京联合大学11417。将测评百分比替换为班级排名/////////////////////////////////////////////////////////
			if(Base.xxdm.equals(Globals.XXDM_BJLHDX) && tsgs.equals("%") && zd.split(",").length > 1){//11417
				message = judgeGxh(xh,map);
			}else{
				boolean flag = compareTo(bjz, tjz, gx, true);

				// 不满足条件的话
				if (!flag) {
					message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
							+ ",不满足申请条件！";
				}
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}

		return message;
	}
	
	/*
	 * 学校个性化处理
	 */
	private String judgeGxh(String xh, Map<String, String> map) {
		
		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		String bjz = dao.getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		

		String[] zds = zd.split(",");
		
		HashMap<String, String> bjzMap = dao.getBjzData(xh, map, "");
		String grpm = bjzMap.get(zds[0]);//个人排名
		grpm = Base.isNull(grpm) ? "0" : grpm;
		String num = bjzMap.get(zds[1]);//总数
		long tjzpm = Math.round(Double.parseDouble(tjz) * Integer.parseInt(num)/100);//条件值名次
		boolean flag = compareTo(grpm, tjzpm + "", gx, true);

		// 不满足条件的话
		if (!flag) {
			message = "申请该项目需要：" + tjms + "(即排名"+tjzpm+"),申请者为" + grpm
					+ ",不满足申请条件！";
		}
		return message;
	}
	
	/**
	 * 判断反向逻辑关系
	 * 
	 * @author 伟大的骆
	 */
	private String judgeLogicReverse(String xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		String bjz = dao.getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		try {			
			//////百分比情况，仅限素质测评排名。学校个性化，北京联合大学11417。将测评百分比替换为班级排名/////////////////////////////////////////////////////////
			if(Base.xxdm.equals(Globals.XXDM_BJLHDX) && tsgs.equals("%") && zd.split(",").length > 1){//11417
				message = judgeGxh(xh,map);
			}else{
				boolean flag = compareTo(bjz, tjz, gx, false);
	
				// 不满足条件的话
				if (!flag) {
					message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
							+ ",不满足申请条件！";
				}
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}

		return message;
	}

	/**
	 * 判断最小分关系
	 * 
	 * @author 伟大的骆
	 */
	private String judgeMinRelation(String xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		String bjz = dao.getBjz(xh, map, "Min");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		try {

			boolean flag = compareTo(bjz, tjz, gx, true);

			// 不满足条件的话
			if (!flag) {
				message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
						+ ",不满足申请条件！";
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}

		return message;
	}

	/**
	 * 判断平均分关系
	 * 
	 * @author 伟大的骆
	 */
	private String judgeAvgRelation(String xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		String bjz = dao.getBjz(xh, map, "AVG");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		bjz = String.valueOf(Math.round(Double.parseDouble(bjz)));

		try {

			boolean flag = compareTo(bjz, tjz, gx, true);

			// 不满足条件的话
			if (!flag) {
				message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
						+ ",不满足申请条件！";
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}

		return message;
	}

	/**
	 * 判断某情况关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String xh, String xmdm,
			Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		map.put("xmdm", xmdm);
		
		// 比较值
		String bjz = dao.getBjz(xh, map, "count");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		if (Integer.parseInt(bjz) > 0) {
			flag = true;
		}

		// 不满足条件的话
		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者不满足该条件！";
		}

		return message;
	}
	

	/**
	 * 目标值和确定值比较
	 * 
	 * @author 伟大的骆
	 */
	 public boolean compareTo(String bjz, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// 比较值和条件值非空
		if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
			// 关系为"="
			if ("=".equalsIgnoreCase(gx)) {
				flag = bjz.equalsIgnoreCase(tjz) ? true : false;
			}
			// 关系为">"
			else if (">".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0 ? true : false;
				}
			}
			// 关系为">="
			else if (">=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}
			}
			// 关系为"<"
			else if ("<".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0 ? true : false;
				}

			}
			// 关系为"<="
			else if ("<=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}

			}
		}

		return flag;
	}
	// ===============条件检测 end===============================

	// ===============人数检测 begin===============================
	/**
	 * 检测人数控制
	 * 
	 * @author 伟大的骆
	 */
	private String checkRskz(String xh, PjpyWdpjModel model,
			PjszPjxmModel pjxmModel) {

		// 提示信息
		StringBuilder message = new StringBuilder();
		// 控制范围
		String kzfw = pjxmModel.getKzfw();
		// 控制范围
		String xmdm = model.getXmdm();
		// 审核岗位ID
		String gwid = model.getGwid();
		// 设置人数
		String szrs = dao.getSzrs(xh, xmdm, kzfw);
		szrs = Base.isNull(szrs) ? "0" : szrs;
		// 通过人数
		String tgrs = dao.getTgrs(xh, xmdm, kzfw, gwid);
		tgrs = Base.isNull(tgrs) ? "0" : tgrs;
		
		if (Integer.parseInt(tgrs) >= Integer.parseInt(szrs)) {
			message.append("该项目人数上限为");
			message.append(szrs);
			message.append("，现已达到上限，无法再进行申请");
		}

		return message.toString();
	}

	/**
	 * 检测人数控制
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private String checkRskz(String[] xh,
			List<HashMap<String, String>> bshrList, PjpyWdpjModel model,
			PjszPjxmModel pjxmModel) throws Exception {

		// 控制范围
		String xmdm = model.getXmdm();
		// 控制范围
		String kzfw = pjxmModel.getKzfw();
		// 审核人岗位ID
		String gwid = model.getGwid();
		// 被审核人部门代码
		String[] bmdm = null;
		
		if ("cpz".equalsIgnoreCase(kzfw)) {
			bmdm = dao.getSqrCpz(bshrList);
		} else {
			bmdm = dao.getSqrbm(bshrList, kzfw);
		}
		
		// 项目通过人数
		List<HashMap<String, String>> bmrsList = dao.getBmrsList(xmdm, gwid,
				xh, bmdm, kzfw);

		// 判断项目人数限制
		String message = judgeRsxz(model, pjxmModel, bmrsList, bshrList);
		
		return message;
	}
	
	/**
	 * 判断项目人数限制
	 * 
	 * @author 伟大的骆
	 */
	private String judgeRsxz(PjpyWdpjModel model, PjszPjxmModel pjxmModel,
			List<HashMap<String, String>> bmrsList,
			List<HashMap<String, String>> bshrList) {

		// 控制范围
		String kzfw = pjxmModel.getKzfw();
		// 人数设置
		String rssz = pjxmModel.getRssz();

		if ("yes".equalsIgnoreCase(rssz)) {
			if (bmrsList != null && bmrsList.size() > 0) {
				for (int i = 0; i < bmrsList.size(); i++) {
					HashMap<String, String> bmInfo = bmrsList.get(i);
					// 部门代码
					String dm = bmInfo.get("bmdm");
					// 部门被设置人数
					int bmrs = Integer.parseInt(bmInfo.get("bmrs"));
					// 已通过人数
					int ytgrs = Integer.parseInt(bmInfo.get("ytgrs"));

					// 人数限制
					if (bshrList != null && bshrList.size() > 0) {
						for (int j = 0; j < bshrList.size(); j++) {
							HashMap<String, String> bshrInfo = bshrList.get(j);
							// 年级
							String nj = bshrInfo.get("nj");
							// 学院代码
							String xydm = bshrInfo.get("xydm");
							// 专业代码
							String zydm = bshrInfo.get("zydm");
							// 班级代码
							String bjdm = bshrInfo.get("bjdm");
							// 参评组代码
							String cpzdm = bshrInfo.get("cpzdm");
							// 学院名称
							String xymc = bshrInfo.get("xymc");
							// 专业名称
							String zymc = bshrInfo.get("zymc");
							// 班级名称
							String bjmc = bshrInfo.get("bjmc");
							// 参评组名称
							String cpzmc = bshrInfo.get("cpzmc");
							// 所属部门
							String sybm = "";
							// 部门名称
							String bmmc = "";
							// 控制范围
							if ("nj".equalsIgnoreCase(kzfw)) {
								sybm = nj;
								bmmc = nj + "级";
							} else if ("xy".equalsIgnoreCase(kzfw)) {
								sybm = xydm;
								bmmc = xymc;
							} else if ("njxy".equalsIgnoreCase(kzfw)) {
								sybm = nj + xydm;
								bmmc = nj + "级" + xymc;
							} else if ("njzy".equalsIgnoreCase(kzfw)) {
								sybm = nj + zydm;
								bmmc = nj + "级" + zymc;
							} else if ("bj".equalsIgnoreCase(kzfw)) {
								sybm = bjdm;
								bmmc = bjmc;
							} else if ("cpz".equalsIgnoreCase(kzfw)) {
								sybm = cpzdm;
								bmmc = cpzmc;
							}

							if (sybm.equalsIgnoreCase(dm)) {
								// 增加已通过人数
								ytgrs++;

								if (ytgrs > bmrs) {

									String xmmc = pjxmModel.getXmmc();

									StringBuilder message = new StringBuilder();
									message.append(bmmc);
									message.append("【" + xmmc + "】");
									message.append("最多通过人数为");
									message.append("<font color='blue'>");
									message.append(bmrs);
									message.append("</font>");
									message.append("人,\n");
									message.append("审核<font color='blue'>通过人数过多</font>，请确认！");

									return message.toString();
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	// ===============人数检测 end===============================

	// ===============检测项目兼得begin========================
	/**
	 * 检测项目兼得
	 * 
	 * @author 伟大的骆
	 */
	private String checkXmjd(String xh, PjpyWdpjModel model) {

		// 提示信息
		StringBuilder message = new StringBuilder();
		// 控制范围
		String xmdm = model.getXmdm();
		// 审核岗位ID
		String gwid = model.getGwid();
		// 非兼得项目列表
		List<HashMap<String, String>> jdxmList = dao.getJdxmList(xmdm);
		// 已获得项目列表
		List<HashMap<String, String>> hdxmList = dao.getHdxmList(xh, gwid);

		if (jdxmList != null && jdxmList.size() > 0) {
			for (int i = 0; i < jdxmList.size(); i++) {
				String fjdxm = jdxmList.get(i).get("fjddm");

				if (hdxmList != null && hdxmList.size() > 0) {
					for (int j = 0; j < hdxmList.size(); j++) {
						String hdxmdm = hdxmList.get(j).get("xmdm");
						String hdxmmc = hdxmList.get(j).get("xmmc");

						if (fjdxm.equalsIgnoreCase(hdxmdm)) {
							message.append("该项目与");
							message.append("【");
							message.append(hdxmmc);
							message.append("】");
							message.append("不可兼得，请您确认");
							return message.toString();
						}
					}
				}
			}
		}

		return message.toString();
	}

	/**
	 * 检测项目兼得
	 * 
	 * @author 伟大的骆
	 */
	private String checkXmjd(String[] xh,
			List<HashMap<String, String>> bshrList, PjpyWdpjModel model,
			PjszPjxmModel pjxmModel) {

		// 项目非兼得列表
		List<HashMap<String, String>> xmfjdList = dao.getXmjdList(pjxmModel);
		// 被审核人已获得奖学金列表
		List<HashMap<String, String>> yhdjxjList = dao.getYhdjxjList(pjxmModel,
				bshrList);

		// 判断项目兼得限制
		String message = judgeJdxz(pjxmModel, xmfjdList, yhdjxjList);

		return message;
	}

	/**
	 * 判断项目兼得限制
	 * 
	 * @author 伟大的骆
	 */
	private String judgeJdxz(PjszPjxmModel pjxmModel,
			List<HashMap<String, String>> xmfjdList,
			List<HashMap<String, String>> yhdjxjList) {

		// 项目代码
		String xmdm = pjxmModel.getXmdm();

		if (yhdjxjList != null && yhdjxjList.size() > 0) {
			for (int i = 0; i < yhdjxjList.size(); i++) {
				HashMap<String, String> yhInfo = yhdjxjList.get(i);
				// 已获得项目代码
				String yhddm = yhInfo.get("xmdm");
				// 被审核人学号
				String xh = yhInfo.get("xh");
				// 被审核人姓名
				String xm = yhInfo.get("xm");

				if (xmfjdList != null && xmfjdList.size() > 0) {
					for (int j = 0; j < xmfjdList.size(); j++) {
						HashMap<String, String> xmfjdInfo = xmfjdList.get(j);
						// 以获得项目代码
						String dm = xmfjdInfo.get("xmdm");
						// 非兼得代码
						String fjddm = xmfjdInfo.get("fjddm");
						// 项目名称
						String fjdmc = xmfjdInfo.get("fjdmc");

						if (xmdm.equalsIgnoreCase(dm)
								&& yhddm.equalsIgnoreCase(fjddm)) {

							StringBuilder message = new StringBuilder();
							message.append("<font color='blue'>");
							message.append(xh);
							message.append("(" + xm + ")");
							message.append("</font>");
							message.append("已获得");
							message.append("【" + fjdmc + "】,\n");
							message.append("与该审核项目");
							message.append("<font color='blue'>");
							message.append("不可兼得");
							message.append("</font>");
							
							return message.toString();
						}
					}
				}
			}
		}

		return null;
	}
	// ===============检测项目兼得end========================
}
