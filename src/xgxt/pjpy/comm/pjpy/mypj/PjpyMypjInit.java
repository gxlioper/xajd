package xgxt.pjpy.comm.pjpy.mypj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_Init类
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
	 * 我的评奖_初始化数据（老师版）
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getMypjForTeaRForm(RequestForm rForm, PjpyMypjForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "pjxmsb";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_mypj_tea.do";
		// 表头
		List<HashMap<String, String>> topTr =getDefaultValue(model);

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

	}
	
	/**
	 * 我的评奖_初始化数据（学生版）
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getMypjForStuRForm(RequestForm rForm, PjpyMypjForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 功能模块
		String gnmk = "pjpy";
		// 系统字段设置
		String menu = "pjxmsb";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_mypj_stu.do";
		// 表头
		List<HashMap<String, String>> topTr =getDefaultValue(model);

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

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

		//版本类型
		String bblx = model.getBblx();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		
		if ("tea".equalsIgnoreCase(bblx)) {//老师版
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "项目名称");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sbrs");
			map.put("cn", "我上报的人数");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "shrs");
			map.put("cn", "我需审核的人数");
			topTr.add(map);
		}if ("stu".equalsIgnoreCase(bblx)) {// 学生版
			map = new HashMap<String, String>();
			map.put("en", "xmmc");
			map.put("cn", "我申请的项目");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "目前审核状态");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "操作");
			topTr.add(map);
		}
		
		return topTr;
	}
}
