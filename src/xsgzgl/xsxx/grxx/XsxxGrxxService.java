package xsgzgl.xsxx.grxx;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.jygl.njjs.NjjsJyglService;
import xsgzgl.xsxx.cssz.grxx.XsxxCsszService;
import xsgzgl.xsxx.model.CsszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个人信息_Service类
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

public class XsxxGrxxService extends CommService {
	
	XsxxGrxxDAO dao = new XsxxGrxxDAO();
	
	/**
	 * 获得学生信息
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public HashMap<String, String> getXsxxInfo(XsxxGrxxForm model, User user) {

		XsxxCsszService csszService = new XsxxCsszService();

		// 字段列表
		List<HashMap<String, String>> zdList = csszService.getZdszList("",user);

		String userType = user.getUserType();

		if ("stu".equalsIgnoreCase(userType)) {
			model.setXh(user.getUserName());
		}

		//学生信息
		HashMap<String, String> xsInfo = dao.getXsxxInfo(model, zdList);

		HashMap<String, String> map = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {
				
				HashMap<String, String> zdInfo = zdList.get(i);
				// 字段
				String zd = zdInfo.get("zd");
				// 字段类型
				String zdlx = zdInfo.get("zdlx");
				// 字段验证
				String checked = zdInfo.get("checked");
				// 表
				String source_table = zdInfo.get("source_table");
				// 表字段
				String select_dm = zdInfo.get("select_dm");
				// 表名称
				String select_mc = zdInfo.get("select_mc");
				// 学生权限
				String xsqx = zdInfo.get("xsqx");
				// 老师权限
				String lsqx = zdInfo.get("lsqx");
				// 可否修改颜色
				String kfxg = "";
				if ("stu".equalsIgnoreCase(userType)) {
					if ("1".equalsIgnoreCase(xsqx)) {
						kfxg = "<a href=\"#\" onclick=\"showEditDiv('"+zd+"');return false;\"><font color=\"blue\">修改</font></a>";
					}
				} else {
					if ("1".equalsIgnoreCase(lsqx)) {
						kfxg = "<a href=\"#\" onclick=\"showEditDiv('"+zd+"');return false;\"><font color=\"blue\">修改</font></a>";
					}
				}
				
				map.put(zd, xsInfo.get(zd));
				map.put(zd + "_zdlx", zdlx);
				map.put(zd + "_checked", checked);
				map.put(zd + "_source_table", source_table);
				map.put(zd + "_select_dm", select_dm);
				map.put(zd + "_select_mc", select_mc);
				map.put(zd + "_kfxg", kfxg);
				
				
			}
		}
		
		return map;
	}
	
	/**
	 * 显示字段设置Div
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void showZdxgDiv(String zd, User user,HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		XsxxCsszService csszService = new XsxxCsszService();

		// 字段列表
		List<HashMap<String, String>> zdList = csszService
				.getZdszList(zd, user);

		HashMap<String, String> zdInfo = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			zdInfo = zdList.get(0);
		}
		
		// 字段名
		String zdm = zdInfo.get("zdm");
		// 字段类型
		String zdlx = zdInfo.get("zdlx");
		// 字段验证
		String checked = zdInfo.get("checked");
		// 数据表
		String source_table = zdInfo.get("source_table");
		// 表代码
		String select_dm = zdInfo.get("select_dm");
		// 表名称
		String select_mc = zdInfo.get("select_mc");
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		
		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>学生信息修改</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"30%\">");
		html.append(zdm);
		html.append("</th>");
		html.append("<td>");
		
		if("text".equalsIgnoreCase(zdlx)){//文本框 
			html.append("<input type=\"text\" name=\""+zd+"_name\" id=\""+zd+"_id\" ");	
			if (!Base.isNull(checked)) {
				String[] validate = checked.split("!!luojw!!");
				
				//最大字符数
				String maxlength = ("0".equalsIgnoreCase(validate[0])) ? ""
						: "maxlength=\"" + validate[0] + "\"";
				
				html.append(maxlength);
				
				// 特殊验证
				String sp_validate = validate[1];
				
				if("sfzh".equalsIgnoreCase(sp_validate)){//身份证号
					html.append("onblur=\"checkSfzh(this)\" ");
				}else if("num".equalsIgnoreCase(sp_validate)){//数字验证
					html.append("onkeydown=\"return onlyNum(this,"+validate[0]+")\" ");
					html.append("onmousedown=\"return onlyNum(this,"+validate[0]+")\" ");
					html.append("style=\"ime-mode:disabled\" ");
				}else if("dzyx".equalsIgnoreCase(sp_validate)){//电子邮箱
					html.append("onblur=\"if(isEmail(this.value)){}else{this.value='';alertInfo('电子邮箱格式不正确，请确认')}\" ");
				}else if("money".equalsIgnoreCase(sp_validate)){//金额验证
					html.append("onkeyup=\"checkInputNum(this)\"");
					html.append("onblur=\"checkInputNum(this)\"");
					html.append("style=\"ime-mode:disabled;\"");
				}
			}
			html.append(">");
		}else if("calendar".equalsIgnoreCase(zdlx)){//日期控件
			html.append("<input type=\"text\" name=\""+zd+"_name\" id=\""+zd+"_id\" ");
			html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
			html.append("onclick=\"return showCalendar('" + zd + "_id','yyyyMMdd');\" ");
			html.append(">");
		}else if("select".equalsIgnoreCase(zdlx)){//下拉列表
			if(Base.isNull(source_table)){
				
				String[] dm = select_dm.split("!!luojw!!");
				String[] mc = select_mc.split("!!luojw!!");
				
				html.append("<select name=\""+zd+"_name\" id=\""+zd+"_id\"> ");
				html.append("<option value=\"\"></option>");
				for(int i=0;i<dm.length;i++){
					html.append("<option value=\"" + dm[i] + "\">" + mc[i] + "</option>");
				}
				html.append("</select> ");
				
			} else {
				List<HashMap<String, String>> optionList = getWhList(
						source_table, select_dm, select_mc, "", "", "", true);
				
				html.append("<select name=\""+zd+"_name\" id=\""+zd+"_id\"> ");
				
				if(optionList!=null && optionList.size()>0){
					for(int i=0;i<optionList.size();i++){
						
						String dm = optionList.get(i).get("dm");
						String mc = optionList.get(i).get("mc");
						
						html.append("<option value=\"" + dm + "\">" + mc + "</option>");
					}
				}
				
				html.append("</select> ");
			}
		}else if("ssx".equalsIgnoreCase(zdlx)){//省市县
			NjjsJyglService ssxService = new NjjsJyglService();
			//省列表
			List<HashMap<String, String>> shenList = ssxService.getShenList();
			
			html.append("<select name=\""+zd+"_shen_name\" id=\"shen\" style=\"width:200px\" onchange=\"changeShen()\"> ");
			html.append("<option value=\"\"></option>");
			
			if(shenList!=null && shenList.size()>0){
				for(int i=0;i<shenList.size();i++){
					
					String dm = shenList.get(i).get("dm");
					String mc = shenList.get(i).get("mc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			
			html.append("</select> ");
			
			html.append("<span id=\"span_shi\" style=\"display:none\"><br/><br/></span>");
			html.append("<select name=\""+zd+"_shi_name\" id=\"shi\" style=\"width:200px;display:none\" onchange=\"changeShi()\"> ");
			html.append("<option value=\"\"></option>");		
			html.append("</select>");
			
			html.append("<span id=\"span_xian\" style=\"display:none\"><br/><br/></span>");
			html.append("<select name=\""+zd+"_xian_name\" id=\"xian\"  style=\"width:200px;display:none\"> ");
			html.append("<option value=\"\"></option>");		
			html.append("</select> ");	
		}else if("szbm".equalsIgnoreCase(zdlx)){//所在部门
			
			// 年级列表
			List<HashMap<String, String>> njList = Base.getNjList();
			html.append("<select name=\"nj\" id=\"nj\"  style=\"width:180px;\" onchange=\"initZyList();initBjList();\"> ");
			html.append("<option value=\"\"></option>");	
			if(njList!=null && njList.size()>0){
				for(int i=0;i<njList.size();i++){
					
					String dm = njList.get(i).get("nj");
					String mc = njList.get(i).get("nj");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(年级)");
			
			// 学院列表
			List<HashMap<String, String>> xyList = Base.getXyList();
			html.append("<br/><br/>");
			html.append("<select name=\"xy\" id=\"xy\"  style=\"width:180px;\" onchange=\"initZyList();initBjList();\"> ");
			html.append("<option value=\"\"></option>");	
			if(xyList!=null && xyList.size()>0){
				for(int i=0;i<xyList.size();i++){
					
					String dm = xyList.get(i).get("xydm");
					String mc = xyList.get(i).get("xymc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(" + Base.YXPZXY_KEY + ")");
			
			// 专业列表
			List<HashMap<String, String>> zyList = (Base.getZyMap()).get("");
			
			html.append("<br/><br/>");
			html.append("<select name=\"zy\" id=\"zy\"  style=\"width:180px;\" onchange=\"initBjList();\"> ");
			html.append("<option value=\"\"></option>");	
			if(zyList!=null && zyList.size()>0){
				for(int i=0;i<zyList.size();i++){
					
					String dm = zyList.get(i).get("zydm");
					String mc = zyList.get(i).get("zymc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(专业)");
			
			// 班级列表
			List<HashMap<String, String>> bjList = (Base.getBjMap()).get("");
			
			html.append("<br/><br/>");
			html.append("<select name=\"bj\" id=\"bj\"  style=\"width:180px;\" onchange=\"\"> ");
			html.append("<option value=\"\"></option>");	
			if(bjList!=null && bjList.size()>0){
				for(int i=0;i<bjList.size();i++){
					
					String dm = bjList.get(i).get("bjdm");
					String mc = bjList.get(i).get("bjmc");
					
					html.append("<option value=\"" + dm + "\">" + mc + "</option>");
				}
			}
			html.append("</select> ");
			html.append("(班级)");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
	
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td width=\"30%\" colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"saveZdxg('"+zd+"','"+zdm+"','"+zdlx+"');return false;\">");
		html.append("确 认");
		html.append("</button>");
		
		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		
		html.append("</table>");
		html.append("</div>");
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 保存字段修改
	 * 
	 * @author luojw
	 */
	public boolean saveZdxg(XsxxGrxxForm model, User user) {
		return dao.saveZdxg(model, user);
	}
	
	/**
	 * 获得所在部门
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String getSzbm(String bjdm) {
		return dao.getSzbm(bjdm);
	}
	
	/**
	 * 获得省市县
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String getSsx(String ssx) {

		List<HashMap<String, String>> list = dao.getSsx(ssx);

		String qxmc = "";

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				qxmc += " ";
				qxmc += list.get(i).get("qxmc");
			}
		}

		return qxmc;
	}
	
	/**
	 * 初始化字段修改表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public static Boolean initZdxgb(String xh) throws Exception {
		return XsxxGrxxDAO.initZdxgb(xh);
	}
	
	/**
	 * 申请控制
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String checkSqzg(CsszModel csszModel) {

		String message = "";

		String sfsh = csszModel.getSfsh();// '是否审核';

		String lcid = csszModel.getLcid();// '流程ID';

		String sqkssj = csszModel.getSqkssj();// '申请开始时间';

		String sqjssj = csszModel.getSqjssj();// '申请结束时间';

		String shkssj = csszModel.getShkssj();// '审核开始时间';

		String shjssj = csszModel.getShjssj();// '审核结束时间';

		String nowTime = getNowTime("YYYYMMDD");// 当前时间

		if (Base.isNull(sfsh)) {
			message = "个人信息修改相关参数尚未设置，无法修改，请联系相关管理人员";
		} else if (Integer.parseInt(sqkssj) > Integer.parseInt(nowTime)) {
			message = "尚未开放申请，需要到" + sqkssj + "才开放修改";
		} else if (Integer.parseInt(sqjssj) < Integer.parseInt(nowTime)) {
			message = "本次申请时间已结束，如果仍需要修改，请联系相关管理人员";
		}

		return message;
	}
	
	/**
	 * 审核控制
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String checkShzg(CsszModel csszModel) {

		String message = "";

		String sfsh = csszModel.getSfsh();// '是否审核';

		String lcid = csszModel.getLcid();// '流程ID';

		String sqkssj = csszModel.getSqkssj();// '申请开始时间';

		String sqjssj = csszModel.getSqjssj();// '申请结束时间';

		String shkssj = csszModel.getShkssj();// '审核开始时间';

		String shjssj = csszModel.getShjssj();// '审核结束时间';

		String nowTime = getNowTime("YYYYMMDD");// 当前时间

		if (Base.isNull(sfsh)) {
			message = "个人信息修改相关参数尚未设置，无法修改，请联系相关管理人员";
		} else if ("否".equalsIgnoreCase(sfsh)) {
			message = "个人信息修改无需审核，如有异议请联系管理人员";
		} else if (Integer.parseInt(shkssj) > Integer.parseInt(nowTime)) {
			message = "尚未开放审核，需要到" + shkssj + "才开放修改";
		} else if (Integer.parseInt(shjssj) < Integer.parseInt(nowTime)) {
			message = "本次审核时间已结束，如果仍需要修改，请联系相关管理人员";
		}

		return message;
	}
	
	/**
	 * 保存字段修改
	 * 
	 * @author luojw
	 */
	public String saveXgsq(XsxxGrxxForm model, User user) {

		boolean flag = false;

		// 申请ID
		String sqid = model.getSqid();

		// 申请ID为空（新的申请）
		if (Base.isNull(sqid)) {
			try {
				flag = dao.insertXgsq(model, user);

				// 获得申请ID
				sqid = getSqid(model, user);

				model.setSqid(sqid);

				if (flag) {
					//添加审核记录
					flag = dao.insertXgsh(model, user);
				}
				
				if (flag) {
					//保存申请ID
					dao.saveSqid(model);
				}
				
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} else {
			try {
				
				// 修改修改申请
				flag = dao.updateXgsq(model, user);

				if (flag) {
					// 修改修改审核
					flag = dao.updateXgsh(model, user);
				}

				if (flag) {
					// 保存申请ID
					dao.saveSqid(model);
				}
				
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return sqid;
	}
	
	/**
	 * 获得申请ID
	 * 
	 * @author luojw
	 */
	public String getSqid(XsxxGrxxForm model, User user) {
		return dao.getSqid(model, user);
	}
	
	/**
	 * 获得审核信息列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getShInfoList(XsxxGrxxForm model) {

		CsszModel csszModel = model.getCsszModel();

		// 岗位列表
		List<HashMap<String, String>> gwList = csszModel.getGwList();
		// 审核列表
		List<HashMap<String, String>> shList = dao.getShInfoList(model);
		
		if (gwList != null && gwList.size() > 0) {
			for (int i = 0; i < gwList.size(); i++) {
				
				if (shList != null && shList.size() > 0) {
					for (int j = 0; j < shList.size(); j++) {
						if(gwList.get(i).get("gwid").equalsIgnoreCase(shList.get(j).get("gwid"))){
							gwList.get(i).put("shzt", shList.get(j).get("shzt"));
							gwList.get(i).put("shyj", shList.get(j).get("shyj"));
						}
					}
				}
			}
		}

		return gwList;
	}
	
	/**
	 * 获得字段修改信息列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXgInfoList(XsxxGrxxForm model) {
		return dao.getXgInfoList(model);
	}
	
	/**
	 * 提交个人信息修改
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public boolean submitGrxx(XsxxGrxxForm model) {

		// 字段修改列表
		List<HashMap<String, String>> zdxgList = dao.getXgzdList(model);

		// 是否在学生信息表中存在
		boolean isXsxxExists = isExists("xsxxb", "xh", model.getXh());
		// 是否在学生辅助信息表中存在
		boolean isFzxxExists = isExists("xsfzxxb", "xh", model.getXh());
		
		boolean flag = true;
		
		if (!isXsxxExists) {
			try {
				flag = dao.copyToXsxxb(model.getXh());
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		
		if (!isFzxxExists) {
			try {
				flag = dao.copyToFzxxb(model.getXh());
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		
		flag = dao.submitGrxx(model, zdxgList);
		
		return flag;
	}
	
	/**
	 * 获得修改审核列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXgshList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getXgshList(model, user);
	}
	
	/**
	 * 获得修改审核Html
	 * 
	 * @author 伟大的骆
	 */
	public String getXgshHtml(SearchRsModel rsModel, XsxxGrxxForm model,
			ArrayList<String[]> rsArrList, User user) {
		
		// IE版本
		String ie = rsModel.getIe();
		// 样式路径
		String stylePath=rsModel.getStylePath();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String sqid = rs[0];
				String xh = rs[1];
				String xm = rs[2];
				String bjmc = rs[3];
				String sqsj = rs[4];
				String shzt = rs[5];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqsj);
				spHtml.append("</td>");
				
				String pic_name = "";
				
				if ("未审核".equalsIgnoreCase(shzt)) {
					pic_name = "dsh";
				} else if ("通过".equalsIgnoreCase(shzt)) {
					pic_name = "shtg";
				} else if ("不通过".equalsIgnoreCase(shzt)) {
					pic_name = "shbtg";
				} else if ("退回".equalsIgnoreCase(shzt)) {
					pic_name = "shth";
				} else if ("需重审".equalsIgnoreCase(shzt)) {
					pic_name = "shxcs";
				}
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append("<p><img src=\""+stylePath+"images/ico_"+pic_name+".gif\" width=\"52\" height=\"18\" /></p>");
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * 获得学生信息
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public HashMap<String, String> getXgxxInfo(XsxxGrxxForm model, User user) {

		XsxxCsszService csszService = new XsxxCsszService();

		// 字段列表
		List<HashMap<String, String>> zdList = csszService.getZdszList("",user);

		String userType = user.getUserType();

		//学生信息
		HashMap<String, String> xsInfo = dao.getXsxxInfo(model, zdList);

		HashMap<String, String> map = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {
				
				HashMap<String, String> zdInfo = zdList.get(i);
				// 字段
				String zd = zdInfo.get("zd");
				// 字段类型
				String zdlx = zdInfo.get("zdlx");
				// 字段验证
				String checked = zdInfo.get("checked");
				// 表
				String source_table = zdInfo.get("source_table");
				// 表字段
				String select_dm = zdInfo.get("select_dm");
				// 表名称
				String select_mc = zdInfo.get("select_mc");
				// 学生权限
				String xsqx = zdInfo.get("xsqx");
				// 老师权限
				String lsqx = zdInfo.get("lsqx");
				
				map.put(zd, xsInfo.get(zd));
			}
		}
		
		return map;
	}
	
	/**
	 * 保存审核状态
	 * 
	 * @author luojw
	 */
	public boolean saveShzt(XsxxGrxxForm model, User user) {

		boolean flag = false;

		CsszModel csszModel = model.getCsszModel();

		List<HashMap<String, String>> gwList = csszModel.getGwList();

		// 审核状态
		String shzt = model.getShzt();
		// 审核岗位
		String shgw = model.getShgw();
		// 审核岗位名称
		String shgwmc = "";
		// 上级岗位ID
		String pre_gwid = "";
		// 下级岗位ID
		String next_gwid = "";
		// 是否最小
		boolean isMin = false;
		// 是否最大
		boolean isMax = false;
		
		if (gwList != null && gwList.size() > 0) {
			for (int i = 0; i < gwList.size(); i++) {
				HashMap<String, String> gwInfo = gwList.get(i);
				String gwid = gwInfo.get("gwid");
				String gwmc = gwInfo.get("gwmc");
				String lv = gwInfo.get("lv");
				String maxlv = gwInfo.get("maxlv");
				
				if (shgw.equalsIgnoreCase(gwid)) {
					
					if ("1".equalsIgnoreCase(lv)) {
						isMin = true;
					}

					if (lv.equalsIgnoreCase(maxlv)) {
						isMax = true;
					} 
					
					shgwmc = gwmc;
					
					if (!isMin) {
						pre_gwid = gwList.get(i - 1).get("gwid");
					}

					if (!isMax && !"1".equalsIgnoreCase(maxlv)) {
						next_gwid = gwList.get(i + 1).get("gwid");
					}
					
					break;
				}
			}
		}
		
		model.setMax(isMax);
		model.setMin(isMin);
		model.setShgwmc(shgwmc);
		model.setPre_gwid(pre_gwid);
		model.setNext_gwid(next_gwid);
		
		try {

			// 修改审核状态
			flag = updateShzt(model, user);

			if ("退回".equalsIgnoreCase(shzt)) {		
				dao.updateCszt(model, user);
			}
					
			if ("通过".equalsIgnoreCase(shzt)) {

				if (isMax) {
					submitGrxx(model);
				}else{
					dao.updateTgzt(model, user);
				}
			}
			
			if (flag) {
				// 保存申请结果
				flag = dao.updateSqjg(model, user);
			}
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 修改审核状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public boolean updateShzt(XsxxGrxxForm model, User user) throws Exception {

		// 申请ID
		String sqid = model.getSqid();
		// 岗位ID
		String gwid = model.getShgw();

		// 个人信息修改审核表
		String tableName = "xg_xsxx_grxx_xgshb";
		// 主键
		String pk = "sqid||gwid";
		// 主键值
		String[] pkValue = new String[] { sqid + gwid };
		// 修改字段
		String[] onezd = new String[] { "shr", "shzt", "shsj", "shyj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// 审核人
		String shr = user.getUserName();
		model.setShr(shr);

		// 处理时间
		String shsj = dao.getNowTime("YYYYMMDD");
		model.setShsj(shsj);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * 获得结果查询列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getJgcxList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getJgcxList(model, user);
	}
	
	/**
	 * 获得修改结果Html
	 * 
	 * @author 伟大的骆
	 */
	public String getJgcxHtml(SearchRsModel rsModel, XsxxGrxxForm model,
			ArrayList<String[]> rsArrList, User user) {
		
		// IE版本
		String ie = rsModel.getIe();
		// 样式路径
		String stylePath=rsModel.getStylePath();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String sqid = rs[0];
				String xh = rs[1];
				String xm = rs[2];
				String bjmc = rs[3];
				String sqsj = rs[4];
				String sqjg = rs[5];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqsj);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(sqjg);
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * 获得审核人信息
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getShrList(XsxxGrxxForm model,
			SearchRsModel rsModel) {

		CsszModel csszModel = model.getCsszModel();

		// 岗位列表
		List<HashMap<String, String>> gwList = csszModel.getGwList();
		// 审核人列表
		List<HashMap<String, String>> shrList = dao.getShrList(model);

		if (gwList != null && gwList.size() > 0) {
			for (int i = 0; i < gwList.size(); i++) {
				HashMap<String, String> gwInfo = gwList.get(i);

				for (int j = 0; j < shrList.size(); j++) {
					HashMap<String, String> shrInfo = shrList.get(j);

					if (gwInfo.get("gwid")
							.equalsIgnoreCase(shrInfo.get("gwid"))) {

						String shzt = shrInfo.get("shzt");
						String pic_name = "";

						if ("未审核".equalsIgnoreCase(shzt)) {
							pic_name = "dsh";
						} else if ("通过".equalsIgnoreCase(shzt)) {
							pic_name = "shtg";
						} else if ("不通过".equalsIgnoreCase(shzt)) {
							pic_name = "shbtg";
						} else if ("退回".equalsIgnoreCase(shzt)) {
							pic_name = "shth";
						} else if ("需重审".equalsIgnoreCase(shzt)) {
							pic_name = "shxcs";
						}

						String pic = "<p><img src=\"" + rsModel.getStylePath()
								+ "images/ico_" + pic_name
								+ ".gif\" width=\"52\" height=\"18\" /></p>";

						gwList.get(i).put("xm", shrInfo.get("xm"));
						gwList.get(i).put("gwmc", shrInfo.get("gwmc"));
						gwList.get(i).put("shzt", pic);
						gwList.get(i).put("shsj", shrInfo.get("shsj"));
						gwList.get(i).put("shyj", shrInfo.get("shyj"));
					}

				}
			}
		}

		return gwList;
	}
}
