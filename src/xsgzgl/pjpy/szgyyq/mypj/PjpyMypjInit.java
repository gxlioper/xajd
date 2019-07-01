package xsgzgl.pjpy.szgyyq.mypj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_Init类
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

public class PjpyMypjInit {

	/**
	 * 我的评奖_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initMypj(RequestForm rForm, PjpyMypjForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "szgyyq";
		// 系统字段设置
		String menu = "pjxmsb";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_szgyyq_mypj.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model);

		// 用户类型
		String yhlx = model.getYhlx();
		// 操作项目
		String czxm = model.getCzxm();
		czxm = Base.isNull(czxm) ? "szyq_dshdjzb" : czxm;

		// 其他字段
		String[] qtzd = new String[] { "yhlx", "czxm" };
		// 其他字段值
		String[] qtzdz = new String[] { yhlx, czxm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

	}

	private List<HashMap<String, String>> getDefaultValue(PjpyMypjForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// 用户类型
		String yhlx = model.getYhlx();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {// 学生
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "项目名称");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqfs");
			map.put("cn", "申请分数");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "shqk");
			map.put("cn", "审核情况");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "zzfs");
			map.put("cn", "最终分数");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "操作");
			topTr.add(map);
		}

		return topTr;
	}

	/**
	 * 我的评奖_初始化数据(申请信息详细)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSqxxDetail(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);
		
		// 学年
		String xn = request.getParameter("xn");
		model.setXn(xn);

		// 学期
		String xq = request.getParameter("xq");
		model.setXq(xq);

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// 项目名称
		String xmmc = getXmmc(xmdm);

		// 表头
		List<HashMap<String, String>> topTr = getSqxxTopTr(model);

		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xqmc", "xh", "xmdm", "xmmc" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xqmc, xh, xmdm, xmmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);
	}

	/**
	 * 获得项目代码对应的名称
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private String getXmmc(String xmdm) {

		// 项目代码
		String xmmc = "";

		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
			xmmc = "读书活动";
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达
			xmmc = "语言表达";
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
			xmmc = "IVT论坛";
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
			xmmc = "文体活动";
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
			xmmc = "组织活动";
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
			xmmc = "社会实践";
		}

		return xmmc;
	}

	/**
	 * 获得申请项目详细的表头
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, String>> getSqxxTopTr(PjpyStuForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// 项目代码
		String xmdm = model.getXmdm();
		// 用户类型
		String yhlx = model.getYhlx();
		
		HashMap<String, String> map = new HashMap<String, String>();
		if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			map = new HashMap<String, String>();
			map.put("en", "fzxm");
			map.put("cn", "分值项目");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jjf");
			map.put("cn", "加减分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "fz");
			map.put("cn", "分值");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jfrq");
			map.put("cn", "日期");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "yy");
			map.put("cn", "原因");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"审核分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "学校审核分");
			topTr.add(map);

			if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "cz");
				map.put("cn", "操作");
				topTr.add(map);
			}

		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {
			
			map = new HashMap<String, String>();
			map.put("en", "mkmc");
			map.put("cn", "项目名");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "申请分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", "班主任审核分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"审核分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "学校审核分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "mkf");
			map.put("cn", "分值");
			topTr.add(map);
			
		} else {

			if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
				map = new HashMap<String, String>();
				map.put("en", "dsmc");
				map.put("cn", "书名");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "dsrq");
				map.put("cn", "读书日期");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "dsxd");
				map.put("cn", "读书心得");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "sfhj");
				map.put("cn", "是否获奖");
				topTr.add(map);

			} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达
				map = new HashMap<String, String>();
				map.put("en", "yybdnr");
				map.put("cn", "语言表达内容");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "日期");
				topTr.add(map);

			} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
				map = new HashMap<String, String>();
				map.put("en", "jztm");
				map.put("cn", "讲座题目");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "日期");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "jcdj");
				map.put("cn", "进场登记");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "出场登记");
				topTr.add(map);

			} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
				map = new HashMap<String, String>();
				map.put("en", "hdnr");
				map.put("cn", "活动内容");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "xthdrq");
				map.put("cn", "日期");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "jldj");
				map.put("cn", "奖励等级");
				topTr.add(map);

			} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
				map = new HashMap<String, String>();
				map.put("en", "hdzt");
				map.put("cn", "活动内容");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdrq");
				map.put("cn", "活动日期");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hddj");
				map.put("cn", "活动等级");
				topTr.add(map);

			} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
				map = new HashMap<String, String>();
				map.put("en", "hddd");
				map.put("cn", "活动地点");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdrq");
				map.put("cn", "活动日期");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdnr");
				map.put("cn", "活动内容");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "hdsj");
				map.put("cn", "活动时间");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "申请分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", "班主任审核分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", "院系审核分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "学校审核分");
			topTr.add(map);

			if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "cz");
				map.put("cn", "操作");
				topTr.add(map);
			}
		}
		return topTr;
	}

	/**
	 * 我的评奖_初始化数据(结果查询)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initJgcx(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// 学年
		String xn = Base.currXn;
		model.setXn(xn);

		// 学期
		String xq = Base.currXq;
		model.setXq(xq);

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 项目代码
		String xmdm = Base.isNull(request.getParameter("xmdm")) ? model
				.getXmdm() : request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// 项目名称
		String xmmc = getXmmc(xmdm);

		// 表头
		List<HashMap<String, String>> topTr = getJgcxTopTr(model);

		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xqmc", "xh", "xmdm", "xmmc" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xqmc, xh, xmdm, xmmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================个性化高级查询========================
		// 学年
		List<HashMap<String, String>> xnList = Base.getXnndList();
		request.setAttribute("xnTjList", xnList);

		// 学期
		List<HashMap<String, String>> xqList = Base.getXqList();
		request.setAttribute("xqTjList", xqList);

		// 操作
		List<HashMap<String, String>> czList = getSelectList("stu_jgcx_czlx");
		request.setAttribute("czTjList", czList);

	}

	/**
	 * 获得结果查询的表头
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, String>> getJgcxTopTr(PjpyStuForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// 项目代码
		String xmdm = model.getXmdm();

		HashMap<String, String> map = new HashMap<String, String>();

		// 用户类型
		String yhlx = model.getYhlx();

		map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "学号");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "姓名");
		topTr.add(map);
		if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {
			
				map = new HashMap<String, String>();
				map.put("en", "zhszf");
				map.put("cn", "综合素质分");
				topTr.add(map);
				
				map = new HashMap<String, String>();
				map.put("en", "zhszfpm");
				map.put("cn", "综合素质排名");
				topTr.add(map);
			 
		} else {
			if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);
			} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);
			} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);
			} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);
			} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);
			} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);
			} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 社会实践
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "录入分");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "zzf");
			map.put("cn", "最终分");
			topTr.add(map);

			
		}
		
		map = new HashMap<String, String>();
		map.put("en", "cz");
		map.put("cn", "操作");
		topTr.add(map);
		return topTr;
	}

	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	private List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("stu_jgcx_czlx".equalsIgnoreCase(lx)) {// 操作类型（学生_结果查询）
			dm = new String[] { "查看", "可投诉", "已投诉", "已处理" };
			mc = new String[] { "查看", "可投诉", "已投诉", "已处理" };
		} else if ("stu_myss_sszt".equalsIgnoreCase(lx)) {// 申诉状态（学生_我的申诉）
			dm = new String[] { "未处理", "已处理" };
			mc = new String[] { "未处理", "已处理" };
		} else if ("stu_myss_ssxm".equalsIgnoreCase(lx)) {// 申诉项目（学生_我的申诉）
			dm = new String[] { "szyq_dshdjzb", "szyq_yybdjzb", "szyq_ivtltb",
					"szyq_xthddjb", "szyc_zznlfzb", "szyc_shsjfzb","szyc_5sb" };
			mc = new String[] { "读书活动", "语言表达", "IVT论坛", "文体活动", "组织能力", "社会实践", "5S"  };
		} else if ("stu_myss_tsxm".equalsIgnoreCase(lx)) {// 投诉项目（学生_我的投诉）
			dm = new String[] { "szyq_dshdjzb", "szyq_yybdjzb", "szyq_ivtltb",
					"szyq_xthddjb", "szyc_zznlfzb", "szyc_shsjfzb", "szyc_5sb" };
			mc = new String[] { "读书活动", "语言表达", "IVT论坛", "文体活动", "组织能力",
					"社会实践", "5S" };
		} else if ("fssh_shzt".equalsIgnoreCase(lx)) {// 审核状态）
			dm = new String[] { "通过", "不通过", "未审核", "退回", "需重审" };
			mc = new String[] { "通过", "不通过", "未审核", "退回", "需重审" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 我的评奖_初始化数据(我的申诉)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initMySs(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// 学年
		String xn = Base.currXn;
		model.setXn(xn);

		// 学期
		String xq = Base.currXq;
		model.setXq(xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 表头
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "myss");

		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================个性化高级查询========================
		// 学年
		List<HashMap<String, String>> xnList = Base.getXnndList();
		request.setAttribute("xnTjList", xnList);

		// 学期
		List<HashMap<String, String>> xqList = Base.getXqList();
		request.setAttribute("xqTjList", xqList);

		// 申诉状态
		List<HashMap<String, String>> ssztList = getSelectList("stu_myss_sszt");
		request.setAttribute("lxTjList", ssztList);

		// 申诉项目
		List<HashMap<String, String>> xmdmList = getSelectList("stu_myss_ssxm");
		request.setAttribute("xmdmTjList", xmdmList);

	}

	/**
	 * 我的评奖_初始化数据(我的投诉)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initMyTs(RequestForm rForm, PjpyStuForm model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PjpyStuService service = new PjpyStuService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// 学年
		String xn = Base.currXn;
		model.setXn(xn);

		// 学期
		String xq = Base.currXq;
		model.setXq(xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// 表头
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "myts");

		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================个性化高级查询========================
		// 学年
		List<HashMap<String, String>> xnList = Base.getXnndList();
		request.setAttribute("xnTjList", xnList);

		// 学期
		List<HashMap<String, String>> xqList = Base.getXqList();
		request.setAttribute("xqTjList", xqList);

		// 投诉状态
		List<HashMap<String, String>> tsztList = getSelectList("stu_myss_sszt");
		request.setAttribute("lxTjList", tsztList);

		// 投诉项目
		List<HashMap<String, String>> xmdmList = getSelectList("stu_myss_tsxm");
		request.setAttribute("xmdmTjList", xmdmList);

	}

	/**
	 * 我的评奖_初始化数据(5S分维护)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initFiveS(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		rForm.setPath("pjpy_szgyyq_fives.do");
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// 学年
		String xn = Base.currXn;
		model.setXn(xn);

		// 学期
		String xq = Base.currXq;
		model.setXq(xq);

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 表头
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "5s");

		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================个性化高级查询========================
		// 学年
		List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		request.setAttribute("xnTjList", xnList);

		// 学期
		List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xqMap = new HashMap<String, String>();
		xqMap.put("xqdm", xq);
		xqMap.put("xqmc", xqmc);
		xqList.add(xqMap);

		request.setAttribute("xqTjList", xqList);

		// 申诉状态
		List<HashMap<String, String>> ssztList = getSelectList("stu_myss_sszt");
		request.setAttribute("lxTjList", ssztList);

		// 申诉项目
		List<HashMap<String, String>> xmdmList = getSelectList("stu_myss_ssxm");
		request.setAttribute("xmdmTjList", xmdmList);

	}

	/**
	 * 我的评奖_初始化数据(5S分详细)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initFiveSDetail(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// 学年
		String xn = Base.currXn;
		model.setXn(xn);

		// 学期
		String xq = Base.currXq;
		model.setXq(xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 表头
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getOtherTopTr(map, "5s_xx");

		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// 加分原因
		List<HashMap<String, String>> yyList = service.getYyList();
		request.setAttribute("yyList", yyList);
	}

	/**
	 * 获得结果查询的表头
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, String>> getOtherTopTr(
			HashMap<String, String> value, String lx) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("myss".equalsIgnoreCase(lx)) {// 我的申诉
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "项目名称");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "ssnr");
			map.put("cn", "申诉内容");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sssj");
			map.put("cn", "申诉时间");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "clyj");
			map.put("cn", "处理意见");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "clr");
			map.put("cn", "处理人");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "lx");
			map.put("cn", "申诉状态");
			topTr.add(map);
		} else if ("myts".equalsIgnoreCase(lx)) {// 我的投诉
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "项目名称");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "被投诉人学号");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "被投诉人姓名");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "ssnr");
			map.put("cn", "投诉内容");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sssj");
			map.put("cn", "投诉时间");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "clr");
			map.put("cn", "处理人");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "lx");
			map.put("cn", "投诉状态");
			topTr.add(map);
		} else if ("5s".equalsIgnoreCase(lx)) {// 5S分维护
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "学号");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "姓名");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "班主任录入分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"审核分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "学校审核分");
			topTr.add(map);
		} else if ("5s_xx".equalsIgnoreCase(lx)) {// 5S分详细

			map = new HashMap<String, String>();
			map.put("en", "fzxm");
			map.put("cn", "项目");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jjf");
			map.put("cn", "加减分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "分值");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jfrq");
			map.put("cn", "日期");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "yy");
			map.put("cn", "原因");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"审核分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "学校审核分");
			topTr.add(map);
		}

		return topTr;
	}

	/**
	 * 我的评奖_初始化数据(分数审核)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initFssh(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		String yhlx = (String) session.getAttribute("yhlx");
		rForm.setPath("pjpy_szgyyq_fssh.do");
		model.setYhlx(yhlx);

		// 学年
		String xn = Base.currXn;
		model.setXn(xn);

		// 学期
		String xq = Base.currXq;
		model.setXq(xq);

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 表头
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = getFsshTopTr(model);

		String xmdm = request.getParameter("xmdm");
		
		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xh", "czxm" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xh, xmdm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);

		// =================个性化高级查询========================
		// 学年
		List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		xnMap = new HashMap<String, String>();
		xnMap.put("xn", xn);
		xnList.add(xnMap);

		request.setAttribute("xnTjList", xnList);

		// 学期
		List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xqMap = new HashMap<String, String>();
		xqMap.put("xqdm", xq);
		xqMap.put("xqmc", xqmc);
		xqList.add(xqMap);

		request.setAttribute("xqTjList", xqList);

		// 审核状态
		List<HashMap<String, String>> shztList = getSelectList("fssh_shzt");
		request.setAttribute("shztTjList", shztList);
	}

	/**
	 * 我的评奖_初始化数据(分数审核详细)
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initFsshDetail(RequestForm rForm, PjpyTeaForm model,
			HttpServletRequest request) {

		PjpyTeaService service = new PjpyTeaService();

		HttpSession session = request.getSession();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 用户类型
		String yhlx = (String) session.getAttribute("yhlx");
		model.setYhlx(yhlx);

		// 学年
		String xn = Base.currXn;
		model.setXn(xn);

		// 学期
		String xq = Base.currXq;
		model.setXq(xq);

		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// 学号
		String xh = request.getParameter("xh");
		model.setXh(xh);

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		
		// 项目名称
		String xmmc = getXmmc(xmdm);
		
		// 表头
		List<HashMap<String, String>> topTr = getShxxTopTr(model);
		
		// 其他字段
		String[] qtzd = new String[] { "xn", "xq", "xqmc", "xmdm", "xmmc", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xq, xqmc, xmdm, xmmc, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTopTr(topTr);
	}

	/**
	 * 获得分数审核的表头
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, String>> getFsshTopTr(PjpyTeaForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// 项目代码
		String xmdm = model.getXmdm();
		// 用户类型
		String yhlx = model.getYhlx();

		HashMap<String, String> map = new HashMap<String, String>();
		
		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班主任或班长
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "学号");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "姓名");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bjmc");
			map.put("cn", "班级名称");
			topTr.add(map);

			if(!"szyc_5sb".equals(xmdm)){
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);
				
				map = new HashMap<String, String>();
				map.put("en", "bzrshf");
				map.put("cn", "班主任审核分");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "审核状态");
			topTr.add(map);
		} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "学号");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "姓名");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bjmc");
			map.put("cn", "班级名称");
			topTr.add(map);
			
			if(!"szyc_5sb".equals(xmdm)){
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "bzrshf");
				map.put("cn", "班主任审核分");
				topTr.add(map);
			}else {
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "录入分");
				topTr.add(map);
			}

			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", Base.YXPZXY_KEY+"审核分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "审核状态");
			topTr.add(map);
			
			if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
				map = new HashMap<String, String>();
				map.put("en", "xsts");
				map.put("cn", "投诉");
				topTr.add(map);
			}
			
		} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
			map = new HashMap<String, String>();
			map.put("en", "xh");
			map.put("cn", "学号");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xm");
			map.put("cn", "姓名");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "bjmc");
			map.put("cn", "班级名称");
			topTr.add(map);

			if(!"szyc_5sb".equals(xmdm)){
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "申请分");
				topTr.add(map);

				map = new HashMap<String, String>();
				map.put("en", "bzrshf");
				map.put("cn", "班主任<br/>审核分");
				topTr.add(map);
			}else {
				map = new HashMap<String, String>();
				map.put("en", "sqf");
				map.put("cn", "录入分");
				topTr.add(map);
			}
			
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", Base.YXPZXY_KEY+"<br/>审核分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "学校<br/>审核分");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "审核状态");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xsts");
			map.put("cn", "投诉");
			topTr.add(map);
		}

		return topTr;
	}
	
	/**
	 * 获得审核信息详细的表头
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	private List<HashMap<String, String>> getShxxTopTr(PjpyTeaForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// 项目代码
		String xmdm = model.getXmdm();
		// 用户类型
		String yhlx = model.getYhlx();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
			map = new HashMap<String, String>();
			map.put("en", "dsmc");
			map.put("cn", "书名");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "dsrq");
			map.put("cn", "读书日期");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "dsxd");
			map.put("cn", "读书心得");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sfhj");
			map.put("cn", "是否获奖");
			topTr.add(map);

		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达
			map = new HashMap<String, String>();
			map.put("en", "yybdnr");
			map.put("cn", "语言表达内容");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xthdrq");
			map.put("cn", "日期");
			topTr.add(map);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
			map = new HashMap<String, String>();
			map.put("en", "jztm");
			map.put("cn", "讲座题目");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xthdrq");
			map.put("cn", "日期");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jcdj");
			map.put("cn", "进场登记");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "ccdj");
			map.put("cn", "出场登记");
			topTr.add(map);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
			map = new HashMap<String, String>();
			map.put("en", "hdnr");
			map.put("cn", "活动内容");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xthdrq");
			map.put("cn", "日期");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "jldj");
			map.put("cn", "奖励等级");
			topTr.add(map);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
			map = new HashMap<String, String>();
			map.put("en", "hdzt");
			map.put("cn", "活动主题");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdrq");
			map.put("cn", "活动日期");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "hddj");
			map.put("cn", "活动等级");
			topTr.add(map);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
			map = new HashMap<String, String>();
			map.put("en", "hddd");
			map.put("cn", "活动地点");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdrq");
			map.put("cn", "活动日期");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdnr");
			map.put("cn", "活动内容");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "hdsj");
			map.put("cn", "活动时间");
			topTr.add(map);
		}else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5S
			map = new HashMap<String, String>();
			map.put("en", "fzxm");
			map.put("cn", "分值项目");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "jjf");
			map.put("cn", "加减分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "fz");
			map.put("cn", "分值");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "rq");
			map.put("cn", "日期");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "yy");
			map.put("cn", "原因");
			topTr.add(map);
		}
		
		if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
			map = new HashMap<String, String>();
			map.put("en", "sqf");
			map.put("cn", "申请分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "bzrshf");
			map.put("cn", "班主任审核分");
			topTr.add(map);
		}

		if("xy".equalsIgnoreCase(yhlx)){
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", "院系审核分");
			topTr.add(map);
		}

		if("xx".equalsIgnoreCase(yhlx)){
			map = new HashMap<String, String>();
			map.put("en", "xyshf");
			map.put("cn", "院系审核分");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "xxshf");
			map.put("cn", "学校审核分");
			topTr.add(map);
		}

		map = new HashMap<String, String>();
		map.put("en", "shzt");
		map.put("cn", "审核状态");
		topTr.add(map);

		if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
			if ("xy".equalsIgnoreCase(yhlx) || "xx".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "xsss");
				map.put("cn", "申诉");
				topTr.add(map);
			}
		} else {
			if ("xx".equalsIgnoreCase(yhlx)) {
				map = new HashMap<String, String>();
				map.put("en", "xsss");
				map.put("cn", "申诉");
				topTr.add(map);
			}
		}
		
		return topTr;
	}
}
