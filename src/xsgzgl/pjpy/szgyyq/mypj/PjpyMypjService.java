package xsgzgl.pjpy.szgyyq.mypj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.Pages;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_Service类
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

public class PjpyMypjService extends CommService {

	PjpyMypjDAO dao = new PjpyMypjDAO();
	
	/**
	 * 返回我的评奖用户类型
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public String getMypjYhlx(User user) {

		// 用户名
		String userName = user.getUserName();

		// 用户类型
		String userType = user.getUserType();
		
		// 班主任权限
		boolean bzrQx = Fdypd.isBzr(userName, "");
		
		String lx = "";
		
		if ("stu".equalsIgnoreCase(userType)) {// 判断是否学生
			// 判断是否班长
			if (isBz(userName)) {
				lx = "bz";
			} else {
				lx = "stu";
			}
		} else if (bzrQx) {// 判断是否班主任
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 判断是否院系老师
			lx = "xy";
		} else {
			lx = "xx";
		}

		return lx;
	}
	
	/**
	 * 获得我的评奖Html(学生)
	 * 
	 * @author 伟大的骆
	 */
	public String getMypjStuHtml(SearchRsModel rsModel, PjpyMypjForm model,
			User user) {

		// 已申请项目统计列表
		List<HashMap<String, String>> tjList = getStuTjList(model, user);

		// 分页
		Pages pages = model.getPages();
		tjList = getResultList(tjList, pages);

		StringBuilder spHtml = new StringBuilder();

		if (tjList != null && tjList.size() > 0) {

			// 行数
			model.setRows(String.valueOf(tjList.size()));

			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> rs = tjList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\"");

				String color = "";
				spHtml.append("/>");

				// 项目名称
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("xmmc"));
				spHtml.append("</td>");

				// 申请分数
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("sqfs"));
				spHtml.append("</td>");

				// 审核情况
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("shqk"));
				spHtml.append("</td>");

				// 最终分数
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\">");
				spHtml.append(rs.get("zzfs"));
				spHtml.append("</td>");

				// 操作
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%\"> ");
				if("无".equalsIgnoreCase(rs.get("xmdm"))){
					spHtml.append("无");
				}else{
					
					String xn = Base.currXn;
					String xq = Base.currXq;
					String xh = user.getUserName();
					String xmdm = rs.get("xmdm");
					
					spHtml.append("<a href=\"#\" onclick=\"showSqxxDetail('"+xn+"','"+xq+"','"+xh+"','"+xmdm+"','edit');return false;\">");
					spHtml.append("<font color=\"blue\">");
					spHtml.append("<font color=\"blue\">点击查看详细</font>");
					spHtml.append("</font>");
					spHtml.append("</a>");
				}
				spHtml.append("</td>");

				spHtml.append("</tr>");

			}
		}

		return spHtml.toString();
	}

	/**
	 * 获得统计列表(学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuTjList(PjpyMypjForm model,
			User user) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 读书活动统计列表
		List<HashMap<String, String>> dshdList = dao.getStuDshdTjList(model,
				user);

		HashMap<String, String> map = new HashMap<String, String>();

		if (dshdList != null && dshdList.size() > 0) {

			setStuTjMap(dshdList, map, "szyq_dshdjzb", "读书活动");

		} else {
			map.put("xmmc", "读书活动");
			map.put("sqfs", "0");
			map.put("shqk", "无");
			map.put("zzfs", "0");
			map.put("xmdm", "无");
		}

		list.add(map);

		// 语言表达统计列表
		List<HashMap<String, String>> yybdList = dao.getStuYybdTjList(model,
				user);

		map = new HashMap<String, String>();

		if (yybdList != null && yybdList.size() > 0) {

			setStuTjMap(yybdList, map, "szyq_yybdjzb", "语言表达");

		} else {
			map.put("xmmc", "语言表达");
			map.put("sqfs", "0");
			map.put("shqk", "无");
			map.put("zzfs", "0");
			map.put("xmdm", "无");
		}

		list.add(map);

		// Ivt论坛统计列表
		List<HashMap<String, String>> ivtltList = dao.getStuIvtltTjList(model,
				user);

		map = new HashMap<String, String>();

		if (ivtltList != null && ivtltList.size() > 0) {

			setStuTjMap(ivtltList, map, "szyq_ivtltb", "IVT论坛");

		} else {
			map.put("xmmc", "IVT论坛");
			map.put("sqfs", "0");
			map.put("shqk", "无");
			map.put("zzfs", "0");
			map.put("xmdm", "无");
		}

		list.add(map);

		// 文体活动统计列表
		List<HashMap<String, String>> wthdList = dao.getStuWthdTjList(model,
				user);

		map = new HashMap<String, String>();

		if (wthdList != null && wthdList.size() > 0) {

			setStuTjMap(wthdList, map, "szyq_xthddjb", "文体活动");

		} else {
			map.put("xmmc", "文体活动");
			map.put("sqfs", "0");
			map.put("shqk", "无");
			map.put("zzfs", "0");
			map.put("xmdm", "无");
		}

		list.add(map);

		// 组织能力统计列表
		List<HashMap<String, String>> zznlList = dao.getStuZznlTjList(model,
				user);

		map = new HashMap<String, String>();

		if (zznlList != null && zznlList.size() > 0) {

			setStuTjMap(zznlList, map, "szyc_zznlfzb", "组织能力");

		} else {
			map.put("xmmc", "组织能力");
			map.put("sqfs", "0");
			map.put("shqk", "无");
			map.put("zzfs", "0");
			map.put("xmdm", "无");
		}

		list.add(map);

		// 社会实践统计列表
		List<HashMap<String, String>> shsjList = dao.getStuShsjTjList(model,
				user);

		map = new HashMap<String, String>();

		if (shsjList != null && shsjList.size() > 0) {

			setStuTjMap(shsjList, map, "szyc_shsjfzb", "社会实践");

		} else {
			map.put("xmmc", "社会实践");
			map.put("sqfs", "0");
			map.put("shqk", "无");
			map.put("zzfs", "0");
			map.put("xmdm", "无");
		}

		list.add(map);

		// 5S统计列表
		List<HashMap<String, String>> fiveSList = dao.getStuFiveSTjList(model,
				user);

		map = new HashMap<String, String>();

		if (fiveSList != null && fiveSList.size() > 0) {

			setStuTjMap(fiveSList, map, "szyc_5sb", "5S");

		} else {
			map.put("xmmc", "5S");
			map.put("sqfs", "0");
			map.put("shqk", "无");
			map.put("zzfs", "0");
			map.put("xmdm", "无");
		}

		list.add(map);

		return list;
	}

	/**
	 * 设置统计Map(学生)
	 * 
	 * @author 伟大的骆
	 */
	private void setStuTjMap(List<HashMap<String, String>> list,
			HashMap<String, String> map, String xmdm, String xmmc) {
		
		float sqfs = 0;
		float zzfs = 0;
		String jcf = "";
		String zgf = "";

		int bzrsh_tg = 0;
		int bzrsh_btg = 0;
		int bzrsh_wsh = 0;

		int xysh_tg = 0;
		int xysh_btg = 0;
		int xysh_wsh = 0;

		int xxsh_tg = 0;
		int xxsh_btg = 0;
		int xxsh_wsh = 0;

		String shqk = "";
		String bzrshqk = "";
		String xyshqk = "";
		String xxshqk = "";
		String zzfqk = "";

		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> rs = list.get(i);
			String sqf = rs.get("sqf");
			String zzf = rs.get("zzf");
			String bzrsh = rs.get("bzrsh");
			String xysh = rs.get("xysh");
			String xxsh = rs.get("xxsh");
			String jjf = rs.get("jjf");
			jcf = rs.get("jcf");
			zgf = rs.get("zgf");

			if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
				sqfs += Float.parseFloat(sqf);
				zzfs += Float.parseFloat(zzf);
			}else{
				if("加分".equalsIgnoreCase(jjf)){
					sqfs += Float.parseFloat(sqf);
					zzfs += Float.parseFloat(zzf);
				}else{
					sqfs -= Float.parseFloat(sqf);
					zzfs -= Float.parseFloat(zzf);
				}
			}

			if ("通过".equalsIgnoreCase(bzrsh)) {
				bzrsh_tg++;
			} else if ("不通过".equalsIgnoreCase(bzrsh)) {
				bzrsh_btg++;
			} else if ("未审核".equalsIgnoreCase(bzrsh)) {
				bzrsh_wsh++;
			}

			if ("通过".equalsIgnoreCase(xysh)) {
				xysh_tg++;
			} else if ("不通过".equalsIgnoreCase(xysh)) {
				xysh_btg++;
			} else if ("未审核".equalsIgnoreCase(xysh)) {
				xysh_wsh++;
			}

			if ("通过".equalsIgnoreCase(xxsh)) {
				xxsh_tg++;
			} else if ("不通过".equalsIgnoreCase(xxsh)) {
				xxsh_btg++;
			} else if ("未审核".equalsIgnoreCase(xxsh)) {
				xxsh_wsh++;
			}
		}

		if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
			if (bzrsh_tg == list.size()) {
				bzrshqk = "班主任全部审核通过";
			} else if (bzrsh_btg == list.size()) {
				bzrshqk = "班主任全部审核不通过";
			} else if (bzrsh_wsh == list.size()) {
				bzrshqk = "班主任尚未审核";
			} else if (bzrsh_tg != 0) {
				bzrshqk = "班主任部分审核通过";
			} else if (bzrsh_btg != 0) {
				bzrshqk = "班主任部分审核不通过，其余未审核";
			}
		}

		if (xysh_tg == list.size()) {
			xyshqk = Base.YXPZXY_KEY+"全部审核通过";
		} else if (xysh_btg == list.size()) {
			xyshqk = Base.YXPZXY_KEY+"全部审核不通过";
		} else if (xysh_wsh == list.size()) {
			xyshqk = Base.YXPZXY_KEY+"尚未审核";
		} else if (xysh_tg != 0) {
			xyshqk = Base.YXPZXY_KEY+"部分审核通过";
		} else if (xysh_btg != 0) {
			xyshqk = Base.YXPZXY_KEY+"部分审核不通过，其余未审核";
		}

		if (xxsh_tg == list.size()) {
			xxshqk = "学校全部审核通过";
		} else if (xxsh_btg == list.size()) {
			xxshqk = "学校全部审核不通过";
		} else if (xxsh_wsh == list.size()) {
			xxshqk = "学校尚未审核";
		} else if (xxsh_tg != 0) {
			xxshqk = "学校部分审核通过";
		} else if (xxsh_btg != 0) {
			xxshqk = "学校部分审核不通过，其余未审核";
		}

		shqk += bzrshqk + "<br/>";
		shqk += xyshqk + "<br/>";
		shqk += xxshqk;

		zzfqk += "最终分数:" + zzfs + "</br>";
		zzfqk += "基础分:" + jcf + "</br>";
		zzfqk += "最高分:" + zgf;

		map.put("xmmc", xmmc);
		map.put("sqfs", String.valueOf(sqfs));
		map.put("shqk", shqk);
		map.put("zzfs", zzfqk);
		map.put("xmdm", xmdm);
	}
	
	/**
	 * 获取学校统计列表
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getXxMyPjTjList(User user){
		return dao.xxbzrMyPjCheckEmpty(dao.getXxMyPjTjList(user),"xx");
	}
	
	/**
	 * 获取班主任统计列表
	 * @return
	 * @author gaobb
	 */
	public List<HashMap<String,String>> getBzrMyPjTjList(User user){
		return dao.xxbzrMyPjCheckEmpty(dao.getBzrMyPjTjList(user),"bzr");
	}
	
}