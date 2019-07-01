package xsgzgl.rcsw.qjgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 日常事务_请假管理_Init类
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

public class RcswQjglInit {

	/**
	 * 请假流程_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initQjlc(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "rcsw_qjgl_cssz_qjlc.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}

	/**
	 * 我的请假_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initMyqj(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "rcsw_qjgl_mygz_stu.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * 我的工作_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initMygz(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "rcsw_qjgl_mygz_tea.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	

	/**
	 * 我的审核_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initMysh(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "rcsw_qjgl_mysh.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * 结果查询_初始化数据
	 * 
	 * @param request
	 * @author 抵制下流的LUO
	 * 
	 */
	public void initMycx(RequestForm rForm, RcswQjglForm model,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "rcsw_qjgl_jgcx.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ID
		String id = request.getParameter("id");
		model.setId(id);
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	private List<HashMap<String, String>> getDefaultValue(RcswQjglForm model,
			String path) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("rcsw_qjgl_cssz_qjlc.do".equalsIgnoreCase(path)) {// 请假流程
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "请假名称");
			topTr.add(map);

//			map = new HashMap<String, String>();
//			map.put("en", "qjlx");
//			map.put("cn", "请假类型");
//			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "lcmc");
			map.put("cn", "流程名称");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "qjts");
			map.put("cn", "请假天数");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "wshwcrs");
			map.put("cn", "未审核完成人数");
			topTr.add(map);

		}else if("rcsw_qjgl_mygz_stu.do".equalsIgnoreCase(path)){//我的请假
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "请假名称");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "qjts");
			map.put("cn", "请假天数");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "sqsj");
			map.put("cn", "申请时间");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqjg");
			map.put("cn", "审核状态");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "操作");
			topTr.add(map);
			
		}else if("rcsw_qjgl_mygz_tea.do".equalsIgnoreCase(path)){//我的工作
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "请假类型");
			topTr.add(map);

			map = new HashMap<String, String>();
			map.put("en", "xshrs");
			map.put("cn", "需审核人数");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "cz");
			map.put("cn", "操作");
			topTr.add(map);
		}else if("rcsw_qjgl_mysh.do".equalsIgnoreCase(path)){//我的审核
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
			
			map = new HashMap<String, String>();
			map.put("en", "sqts");
			map.put("cn", "申请天数");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "shzt");
			map.put("cn", "审核状态");
			topTr.add(map);
		}else if("rcsw_qjgl_jgcx.do".equalsIgnoreCase(path)){//结果查询
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
			
			map = new HashMap<String, String>();
			map.put("en", "lxmc");
			map.put("cn", "请假名称");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqsj");
			map.put("cn", "申请时间");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqts");
			map.put("cn", "申请天数");
			topTr.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "sqjg");
			map.put("cn", "审核状态");
			topTr.add(map);
		}


		return topTr;
	}
}
