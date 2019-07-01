package xsgzgl.pjpy.general.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import common.Globals;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_首页_通用_Service类
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

public class PjpyIndexService extends CommService implements PjpyIndexInterface {

	PjpyIndexDAO dao = new PjpyIndexDAO();

	/**
	 * 初始化已定制评奖流程
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void defaultCustomPjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 评价等级列表
		List<HashMap<String, String>> pjdjList = dao.getPjdjList(model, user);
		// 评价流程列表
		List<HashMap<String, String>> pjlcList = dao.getCustomPjlcList(model,
				user);
		
		String stylePath = model.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if(pjdjList!=null && pjdjList.size()>0){
			
			html.append("<div class=\"awards_process\">");
			
			html.append("<h3 class=\"awards_process_h3\">");
			html.append("评奖基本设置流程");
			html.append("</h3>");
			
			//---------------開始新評獎 begin-------------
			HashMap<String, String> startMap = pjlcList.get(0);
			String method = startMap.get("method");// 方法
			String lcdm = startMap.get("lcdm");// 流程代码
			String lcmc = startMap.get("lcmc");// 流程名称
			String lcdj = startMap.get("lcdj");// 流程等级
			String used = startMap.get("used");// 是否使用
			String picname = startMap.get("picname");// 图片名
			
			html.append("<div class=\"begin_bt_box\">");
			html.append("<button type=\"button\"  class=\"bt_blue\"");
			html.append("onclick=\"" + method + ";return false;\"");
			html.append(">");
			html.append(lcmc);
			html.append("</button>");
			html.append("</div>");
			
			pjlcList.remove(0);
			//---------------開始新評獎end-------------
			
			//---------------評獎流程 begin-------------
			html.append("<div class=\"awards_process_inbox\">");
			html.append("<ul>");
			
			boolean the_end = false;
			
			for (int i = 1; i < pjdjList.size() - 1; i++) {
				
				String pjdj = pjdjList.get(i).get("lcdj");// 评奖等级
				String sftj = pjdjList.get(i).get("sftj");// 是否提交
				String kfcz = pjdjList.get(i).get("kfcz");// 可否操作
				
				String div_class = "";
				
				if ("yes".equalsIgnoreCase(sftj)) {
					div_class = "processbox_submit processbox_submited";
				} else if ("no".equalsIgnoreCase(sftj)) {
					if ("yes".equalsIgnoreCase(kfcz)) {
						div_class = "processbox_submit processbox_submitting";
					} else {
						div_class = "processbox_submit processbox_unsubmit";
					}
				}
				
				html.append("<li>");
				html.append("<div class=\"" + div_class + "\">");
				html.append("<span>step" + i + "</span>");
				
				if (pjlcList != null && pjlcList.size() > 0) {
					
					for (int j = 0; j < pjlcList.size(); j++) {
						HashMap<String, String> map = pjlcList.get(j);
						lcdm = map.get("lcdm");// 流程代码
						lcmc = map.get("lcmc");// 流程名称
						lcdj = map.get("lcdj");// 流程等级
						method = map.get("method");// 方法
						picname = map.get("picname");// 图片名
						used = map.get("used");// 是否使用
						String hjtj = map.get("sftj");// 是否使用
						
						String imgage = "";
						
						if ("no".equalsIgnoreCase(sftj)
								&& "yes".equalsIgnoreCase(kfcz)) {
							imgage = picname + "_blue.png";
						} else {
							imgage = picname + "_gray.png";
						}
						
						if (lcdj.equalsIgnoreCase(pjdj)) {
							html.append("<a href=\"#\" ");
							html.append("onclick=\"");
							if ("no".equalsIgnoreCase(sftj)
									&& "yes".equalsIgnoreCase(kfcz)) {
								html.append(method);
							}
							html.append(";return false;\" ");
							html.append(">");
							html.append("<img src=\"" + stylePath + "images/"
									+ imgage + "\" ");
							html.append("wdith=\"24\" height=\"24\" />");
							html.append(lcmc);
							html.append("</a>");
						}
					}
				}
				
				html.append("<button type=\"button\"  id=\"a_"+pjdj+"\" ");
				
				if(!"processbox_submit processbox_unsubmit".equalsIgnoreCase(div_class)){
					html.append("onclick=\"checkSubmitPjlc(this);return false;\"");
				}
				if ("yes".equalsIgnoreCase(sftj)) {
					html.append("disabled=\"true\"");
				}else{
					
				}
				html.append(">");
				html.append("</button>");
				html.append("</div>");
				
				//------------箭头begin-------------
				if (i != pjdjList.size() - 2) {
					html.append("<div class=\"");
					if ("yes".equalsIgnoreCase(sftj)) {
						html.append("ico_list_submit ico_list_submited");
					} else if ("no".equalsIgnoreCase(sftj)) {
						if ("yes".equalsIgnoreCase(kfcz)) {
							html.append("ico_list_submit ico_list_submitting");
						} else {
							html.append("ico_list_submit ico_list_unsubmit");
						}
					}
					html.append("\">");
					html.append("</div>");
				}

				if ("yes".equalsIgnoreCase(sftj) && i == pjdjList.size() - 2) {
					the_end = true;
				}
				//------------箭头end---------------
				
				html.append("</li>");
			}
			
			html.append("</ul>");
			html.append("</div>");
			//---------------評獎流程 end-------------
			
			// ---------------结束本次评奖 begin-------------
			HashMap<String, String> endMap = pjlcList.get(pjlcList.size() - 1);
			method = endMap.get("method");// 方法
			lcmc = endMap.get("lcmc");// 流程名称
			String sftj = endMap.get("sftj");// 是否提交

			html.append("<div class=\"over_bt_box\">");
			html.append("<button  type=\"button\" "); 
			if ("no".equalsIgnoreCase(sftj) && the_end) {
				html.append("class=\"bt_blue\" ");
				html.append("onclick=\"" + method + ";return false;\"");
			} else {
				html.append("class=\"bt_blue bt_gray\" ");
				html.append("onclick=\"return false;\"");
			} 
			html.append(">");
			html.append(lcmc);
			html.append("</button>");
			html.append("</div>");
			//---------------结束本次评奖end-------------
			
			html.append("</div>");
//			// 下一级别标志位
//			boolean nextFlag = true;
//			
//			for(int i=0;i<pjdjList.size();i++){
//				
//				html.append("<table class=\"formlist\" align=\"center\" width=\"100%\">");
//				html.append("<thead>");
//				html.append("<tr>");
//				html.append("<td>");							
//				html.append("第"+(i+1)+"步");
//				html.append("</td>");
//				html.append("</tr>");
//				html.append("</thead>");
//				
//				String pjdj = pjdjList.get(i).get("lcdj");// 评奖等级
//				String sftj = pjdjList.get(i).get("sftj");// 是否提交
//				boolean not_start = true;// 非开始新评奖
//
//				if (pjlcList != null && pjlcList.size() > 0) {
//					
//					html.append("<tbody>");
//					html.append("<tr>");
//					html.append("<td>");
//					
//					html.append("<span style=\"float:left\">");
//					for (int j = 0; j < pjlcList.size(); j++) {
//						HashMap<String, String> map = pjlcList.get(j);
//						String lcdm = map.get("lcdm");// 流程代码
//						String lcmc = map.get("lcmc");// 流程名称
//						String lcdj = map.get("lcdj");// 流程等级
//						String method = map.get("method");// 方法
//						String used = map.get("used");// 是否使用
//						
//						if(!"yes".equalsIgnoreCase(used)){
//							if(pjdj.equalsIgnoreCase(lcdj)){
//								html.append("<button  type=\"button\"  id=\"btn_" + lcdm + "\" onclick=\""+method+"\" style=\"width:130px\"");
//								//非开始新评奖
//								if (!"101".equalsIgnoreCase(lcdm)) {
//									// 本级别是否提交
//									if ("yes".equalsIgnoreCase(sftj)) {
//										html.append("disabled=\"true\"");
//									} else if (!nextFlag) {
//										html.append("disabled=\"true\"");
//									}
//								}
//								html.append(">");
//								html.append(lcmc);
//								html.append("</button>");
//								pjlcList.get(j).put("used", "yes");
//								
//								if ("101".equalsIgnoreCase(lcdm)
//										|| "999".equalsIgnoreCase(lcdm)) {
//									not_start = false;
//								}
//							}
//						}
//					}
//					html.append("</span>");
//					
//					html.append("<span style=\"float:right\">");
//					
//					//判断是否新评奖
//					if(not_start){
//						//判断本级别是否提交
//						if("no".equalsIgnoreCase(sftj)){
//							//判断本级别可否提交
//							if(nextFlag){
//								html.append("<a href=\"#\" id=\"a_"+pjdj+"\" onclick=\"checkSubmitPjlc(this);return false;\">");
//								html.append("<font color=\"blue\">");
//								html.append("提交");
//								html.append("</font>");
//								html.append("</a>");
//							}else{
//								html.append("<a href=\"#\" onclick=\"return false;\">");
//								html.append("<font color=\"#888888\">");
//								html.append("提交");
//								html.append("</font>");
//								html.append("</a>");
//							}
//						}else{
//							html.append("<a href=\"#\" onclick=\"return false;\">");
//							html.append("<font color=\"#888888\">");
//							html.append("已提交");
//							html.append("</font>");
//							html.append("</a>");
//						}
//						
//						html.append("</span>");
//					}
//					
//					html.append("</td>");
//					html.append("</tr>");
//					html.append("</tbody>");
//				}
//				
//				nextFlag = "yes".equalsIgnoreCase(sftj) ? true : false;
//				
//				html.append("</table>");
//			}	
		}else{
			html.append("<table class=\"dateline\" align=\"center\" width=\"100%\">");
			html.append("<thead>");
			html.append("<tr>");
			html.append("<td>");							
			html.append("第1步");
			html.append("</td>");
			html.append("</tr>");
			html.append("</thead>");
			html.append("<tbody>");
			html.append("<tr>");
			html.append("<td>");
			html.append("<button  type=\"button\"  id=\"\" onclick=\"showLcdy();return false;\">");
			html.append("评奖流程定义");
			html.append("</button>");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tbody>");
			html.append("</table>");
		}

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 初始化自由流程
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void defaultFreePjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 评价流程列表
		List<HashMap<String, String>> pjlcList = dao.getFreePjlcList(model,
				user);

		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"1\">");
		html.append("<thead onclick=\"\" style=\"\">");
		html.append("<tr>");
		html.append("<td bgcolor=\"#CCFFFF\">");
		html.append("流程操作");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody id=\"\">");
		html.append("<tr>");
		html.append("<td>");

		html.append("<a href=\"#\" onclick=\"addPjlc();return false;\">");
		html.append("<font color=\"blue\">");
		html.append("增加流程");
		html.append("</font>");
		html.append("</a>");

		html.append("&nbsp;&nbsp;&nbsp;&nbsp");

		html.append("<a href=\"#\" onclick=\"return false;\">");
		html.append("<font color=\"blue\">");
		html.append("删除流程");
		html.append("</font>");
		html.append("</a>");

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<thead onclick=\"\" style=\"\">");
		html.append("<tr>");
		html.append("<td bgcolor=\"#CCFFFF\">");
		html.append("评奖流程");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody id=\"\">");
		if (pjlcList != null && pjlcList.size() > 0) {
			for (int i = 0; i < pjlcList.size(); i++) {
				HashMap<String, String> map = pjlcList.get(i);
				String lcdm = map.get("lcdm");// 流程代码
				String lcmc = map.get("lcmc");// 流程名称
				html.append("<tr id=\"tr_free_pjlc_" + lcdm
						+ "\" style=\"cursor:hand;\">");
				html.append("<td>");
				html.append("<span style=\"float:left\">");
				html.append(lcmc);
				html.append("</span>");

				html.append("<span style=\"float:right\">");

				html.append("<a href=\"#\" id=\"a_" + lcdm
						+ "_view\" onclick=\"addStep('" + lcdm + "','" + lcmc
						+ "');return false;\">");
				html.append("<font id=\"font_" + lcdm + "\" color=\"blue\">");
				html.append("添加");
				html.append("</font>");
				html.append("</a>");

				html.append("<a href=\"#\" id=\"a_"
								+ lcdm
								+ "_none\" onclick=\"return false;\" style=\"display:none\">");
				html.append("<font id=\"font_" + lcdm
								+ "\" color=\"#888888\">");
				html.append("已添加");
				html.append("</font>");
				html.append("</a>");

				html.append("</span>");

				html.append("</td>");
				html.append("</tr>");
			}
		}
		html.append("</tbody>");
		html.append("</table>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 保存评奖流程
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjlc(PjpyIndexModel model, User user) {

		boolean flag = false;

		try {
			// 保存评奖流程
			flag = dao.savePjlcb(model, user);

			// 删除评奖流程等级
			if (flag) {
				flag = dao.delPjlcdjb(model, user);
			}

			// 保存评奖流程等级
			if (flag) {
				flag = dao.savePjlcdjb(model, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存开始新评奖
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveStart(PjpyIndexModel model, User user) {
		
		boolean flag = false;
		
		try {
			//保存评奖系统设置
			flag=dao.delXtszb(model, user);
			if(flag){
				flag = dao.saveXtszb(model, user);
			}
			
			//执行初始化操作
			if (flag) {
				dao.initPjlcdjb(model, user);
				dao.initPjlcb(model, user);
			}
			
			//执行提交操作
			if(flag){
				flag = submitPjlc("1", user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		//重置系统设置
		if(flag){
			PjpyGeneralForm.setJbszModel();
			initPjpy(model, user);
		}
		
		return flag;
	}

	/**
	 * 提交评奖流程
	 * 
	 * @author 伟大的骆
	 */
	public Boolean submitPjlc(String lcdj, User user) {

		boolean flag = false;

		try {
			//提交评奖流程
			flag = dao.updatePjlcdjb(lcdj, user);
			
			if(flag){
				flag = dao.updatePjlcb(user);
			}
			// 初始化各项业务
			if (flag) {
				// 初始化本级别业务
				initThisPjlcInfo(lcdj, user);
				// 初始化下一级别业务
				initNextPjlcInfo(lcdj, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 初始化评奖流程信息(本级别)
	 * 
	 * @author 伟大的骆
	 */
	public void initThisPjlcInfo(String lcdj, User user) {

		// 获得本评奖等级列表
		List<HashMap<String, String>> pjdjList = dao.getPjlcList(lcdj, user);

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		//是否需要参评组
		String cpz = jbszModel.getCpz();
		
		if (pjdjList != null && pjdjList.size() > 0) {
			for (int i = 0; i < pjdjList.size(); i++) {
				HashMap<String, String> map = pjdjList.get(i);
				String lcdm = map.get("lcdm");

				try {
					if ("103".equalsIgnoreCase(lcdm)) {// 参评小组设置
						if("yes".equalsIgnoreCase(cpz)){
							dao.initCpxzRy(user);
						}
					} else if ("104".equalsIgnoreCase(lcdm)) {// 综测项目维护

						PjpyZhcpDAO zhcpDAO = new PjpyZhcpDAO();

						// 评奖学年
						String pjxn = jbszModel.getPjxn();
						// 评奖学期
						String pjxq = jbszModel.getPjxq();
						// 评奖年度
						String pjnd = jbszModel.getPjnd();

						// 本周期的综测项目
						List<HashMap<String, String>> zcxmList = zhcpDAO
								.getZcxmList(pjxn, pjxq, pjnd);
						
						// 综测扩展字段
						List<HashMap<String, String>> kzzdList = zhcpDAO
						.getKzzdList(user);

						dao.initComments(zcxmList, user);
						dao.initDrb(zcxmList,kzzdList, user);
						dao.initDcb(zcxmList,kzzdList, user);
					} else if ("116".equalsIgnoreCase(lcdm)) {// 综测分结果
						// 综合分计算
						PjpyZhcpService zhcpService = new PjpyZhcpService();
						PjpyGeneralForm myForm = new PjpyGeneralForm();
						zhcpService.account(myForm, user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 初始化评奖流程信息(下一级别)
	 * 
	 * @author 伟大的骆
	 */
	public void initNextPjlcInfo(String lcdj, User user) {

		// 下一级别
		String next_lcdj = String.valueOf(Integer.parseInt(lcdj) + 1);
		
		// 获得下一评奖等级列表
		List<HashMap<String, String>> pjdjList = dao.getPjlcList(next_lcdj,
				user);

		if (pjdjList != null && pjdjList.size() > 0) {
			for (int i = 0; i < pjdjList.size(); i++) {
				HashMap<String, String> map = pjdjList.get(i);
				String lcdm = map.get("lcdm");

				try {
					if ("102".equalsIgnoreCase(lcdm)) {// 评奖人员库设置
						// dao.initPjry(user);
					} else if ("112".equalsIgnoreCase(lcdm)) {// 综合测评维护
						dao.initZhcp(user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 初始化选择项
	 * 
	 * @author 伟大的骆
	 */
	private void initPjpy(PjpyIndexModel model, User user) {

		// 开始新评奖
		String start = model.getStart();

		try {
			if ("yes".equalsIgnoreCase(start)) {
				// 备份记录
				//dao.backUpTable(user);
				// 将评奖结果迁入历史信息
				//dao.initLsxx(user);
				// 初始化评奖人员库
				dao.initPjry(user);
				// 复制综测项目
				dao.initZcxm(user);
				// 复制评奖项目
				dao.initPjxm(user);
				// 清空评奖参评小组
				dao.initCpxz(user);
				// 北京联合品德表现互评
				if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
					dao.initPdbx(user);
				}
				// 清空评奖条件
				// dao.initPjtj(user);
				// 清空项目兼得
				// dao.initXmjd(user);
			}
		} catch (Exception e) {

		}
	}

	// ======================结束本次评奖=========================
	/**
	 * 获取本次评奖统计信息
	 * author qlj
	 */
	public List<HashMap<String, String>> getBcpjtjInfo(User user)
			throws Exception {

		return dao.getBcpjtjInfo(user);
	}
	
	/**
	 * 将评奖数据转入历史库
	 * author qlj
	 */
	public void  theEnd(User user){
		
		try {
			//备份数据
			dao.backUpTable(user);
			//初始化历史库
			dao.initLsxx(user);
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		
	}
	
}
