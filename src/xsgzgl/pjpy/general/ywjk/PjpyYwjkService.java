package xsgzgl.pjpy.general.ywjk;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import common.Globals;
import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyYwjkInterface;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 評獎評優_業務接口_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyYwjkService extends PjpyGeneralService implements
		PjpyYwjkInterface {

	PjpyYwjkDAO dao = new PjpyYwjkDAO();

	/**
	 * 获得學生評獎列表
	 * 
	 * @date 2013-01-23
	 * @author 伟大的骆
	 */
	public List<HashMap<String, Object>> getStuPjList(String xh) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		// 獎學金列表
		List<String[]> jxjList = dao.getJxjList(xh);

		String[] title = { "评奖周期", "项目名称", "项目金额", "获得时间" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(jxjList);

		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "奖学金");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);

		// 荣誉称号列表
		List<String[]> rychList = dao.getRychList(xh);

		title = new String[] { "评奖周期", "项目名称", "项目金额", "获得时间" };
		rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(rychList);

		map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "荣誉称号");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);
		
		// 综合测评列表
		
		if(Globals.XXDM_CZZYJSXY.equals(Base.xxdm)){
			//池州职业技术学院学生信息显示个性化
			List<HashMap<String, String>> zhcpTop = dao.getCzzyTop();
			List<String[]> zhcpList = dao.getCzzyList(xh,zhcpTop);

			title = getCzzyTop(zhcpTop);
			rs = new ArrayList<String[]>();
			rs.add(title);
			rs.addAll(zhcpList);

			map = new HashMap<String, Object>();
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "综合测评");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
			list.add(map);			
		}else{
			
			List<HashMap<String, String>> zhcpTop = dao.getZhcpTop();
			List<String[]> zhcpList = dao.getZhcpList(xh,zhcpTop);

			title = getZhcpTop(zhcpTop);
			rs = new ArrayList<String[]>();
			rs.add(title);
			rs.addAll(zhcpList);

			map = new HashMap<String, Object>();
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "综合测评");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
			list.add(map);
		}
		
		return list;
	}

	/**
	 * 获得综测表头
	 * 
	 * @date 2013-01-30
	 * @author 伟大的骆
	 */
	public String[] getZhcpTop(List<HashMap<String, String>> zhcpTop) {

		List<String> list = new ArrayList<String>();
		
		list.add("综测周期");
		
		if (zhcpTop != null && zhcpTop.size() > 0) {
			for (int i = 0; i < zhcpTop.size(); i++) {
				list.add(zhcpTop.get(i).get("xmmc"));
			}
		}

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String zcpm = jbszForm.getZcpm();
		
		if ("0".equalsIgnoreCase(zcpm)) {
			list.add("参评组排名");
		}

		if ("1".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			list.add("综测分年级学院排名");
		}

		if ("2".equalsIgnoreCase(zcpm) || "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			list.add("综测分年级专业排名");
		}

		if ("3".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			list.add("综测分班级排名");
		}
		
		return list.toArray(new String[] {});
	}
	
	/**
	 * 获得综测表头---池州职业技术学院学生信息显示个性化
	 * 
	 * @date 2013-5-30
	 * @author 程强
	 */
	public String[] getCzzyTop(List<HashMap<String, String>> zhcpTop) {

		List<String> list = new ArrayList<String>();
		
		list.add("综测周期");
		
		if (zhcpTop != null && zhcpTop.size() > 0) {
			for (int i = 0; i < zhcpTop.size(); i++) {
				list.add(zhcpTop.get(i).get("xmmc"));
			}
		}
		
		list.add("综测排名[上]");
		list.add("智育排名[上]");
		list.add("等级[上]");
		list.add("备注[上]");
		list.add("综测排名[下]");
		list.add("智育排名[下]");
		list.add("等级[下]");
		list.add("备注[下]");
		
		return list.toArray(new String[] {});
	}
}