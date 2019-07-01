package xgxt.pjpy.comm.zhcp.zcjf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_综测加分_Init类
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
public class ZhcpZcjfInit {

	/**
	 * 综测加分申请_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getZcjfsqRForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 功能模块
		String gnmk = "pjpy";
		// 评奖基本设置
		String menu = "zcjfsq";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_zcjf_jfsq.do";

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();
		// 综测周期
		String zczq = model.getZczq();
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc", "zczq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}

	/**
	 * 综测加分审核_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getZcjfshRForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		// 综测周期
		String zczq = jbszModel.getZczq();

		if ("xn".equalsIgnoreCase(zczq)) {// 综测周期为学年
			initJfshForXn(rForm, model, request);
		} else if ("xq".equalsIgnoreCase(zczq)) {// 综测周期为学期
			initJfshForXq(rForm, model, request);
		}
	}

	/**
	 * 综测加分审核_初始化数据(学年)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initJfshForXn(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "zcjfsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_zcjf_jfsh.do";
		// 输出值
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// 项目列表
		List<HashMap<String, String>> xmList = model.getXmList();
		// 表头
		List<HashMap<String, String>> topTr = setDefaultValueByJfsh(colList,
				xmList);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(topTr.size() + 1);
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] {  };
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 综测加分审核_初始化数据(学期)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initJfshForXq(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "zcjfsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_zcjf_jfsh.do";
		// 输出值
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// 项目列表
		List<HashMap<String, String>> xmList = model.getXmList();
		// 表头
		List<HashMap<String, String>> topTr = setDefaultValueByJfsh(colList,
				xmList);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] {};
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * 综测加分审核_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> setDefaultValueByJfsh(
			String[] colList, List<HashMap<String, String>> xmList) {

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"view_xsjbxx", colList, null);

		HashMap<String, String> map = new HashMap<String, String>();

		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				// 二级项目信息
				HashMap<String, String> lv2Info = xmList.get(i);
				String xmdm = lv2Info.get("xmdm");// 项目代码
				String xmmc = lv2Info.get("xmmc");// 项目名称

				map = new HashMap<String, String>();
				map.put("en", xmdm);
				map.put("cn", xmmc);
				topTr.add(map);
			}
		}

		map = new HashMap<String, String>();
		map.put("en", "shr1");
		map.put("cn", "审核人");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "sftj1");
		map.put("cn", "是否提交");
		topTr.add(map);

		return topTr;
	}
	
	/**
	 * 综测加分审核（详细）_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getZcjfshDetailForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 功能模块
		String gnmk = "pjpy";
		// 评奖基本设置
		String menu = "zcjfsq";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_zcjf_jfsh.do";

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();
		// 综测周期
		String zczq = model.getZczq();
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc", "zczq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
	
	/**
	 * 综测加分查询_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getZcjfcxRForm(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		// 综测周期
		String zczq = jbszModel.getZczq();

		if ("xn".equalsIgnoreCase(zczq)) {// 综测周期为学年
			initJfcxForXn(rForm, model, request);
		} else if ("xq".equalsIgnoreCase(zczq)) {// 综测周期为学期

		}
	}
	
	/**
	 * 综测加分查询_初始化数据(学年)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initJfcxForXn(RequestForm rForm, ZhcpZcjfForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "zcjfsh";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_zcjf_jfcx.do";
		// 模块类型
		String mklx = "jg";
		// 输出值
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// 项目列表
		List<HashMap<String, String>> xmList = model.getXmList();
		// 表头
		List<HashMap<String, String>> topTr = setDefaultValueByJfsh(colList,
				xmList);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(topTr.size() + 1);
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] {};
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
}
