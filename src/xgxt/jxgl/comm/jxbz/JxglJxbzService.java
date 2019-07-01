package xgxt.jxgl.comm.jxbz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.jxgl.comm.JxglCommService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训编制_Service类
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

public class JxglJxbzService extends JxglCommService {

	JxglJxbzDAO dao = new JxglJxbzDAO();

	/**
	 * 获得军训编制(最大)
	 * 
	 * @author 伟大的骆
	 */
	public String getBzdj(String bzlx) {
		return dao.getBzdj(bzlx);
	}
	
	/**
	 * 获得军训编制列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getJxbzList(JxglJxbzForm model,
			User user) {
		
		return dao.getJxbzList(model, user);
	}
	
	/**
	 * 获得编制相关信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getJxbzInfo(JxglJxbzForm model, User user) {

		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		// 军训编制等级列表
		List<HashMap<String, String>> jxbzdjList = jxszModel.getJxbzdjList();
		// 操作类型
		String czlx = model.getCzlx();
		//菜单ID
		String menuId = model.getMenuId();
		// 部门级别
		String bzdj = "";
		// 部门级别
		String bzdm = "";
		// 上级代码
		String sjdm = "";
		
		if ("same".equalsIgnoreCase(czlx)) {// 增加同级编制
			bzdj = menuId.split("_")[0];
			bzdm = menuId.split("_")[1];
			sjdm = getOneValue("jxbzdmb", "sjdm", "bzdm", bzdm);
		} else if ("next".equalsIgnoreCase(czlx)) {// 增加下级编制
			sjdm = menuId.split("_")[1];
			bzdj = menuId.split("_")[0];

			boolean flag = false;

			for (int i = 0; i < jxbzdjList.size(); i++) {
				HashMap<String, String> map = jxbzdjList.get(i);
				// 建制代码
				String jzdm = map.get("dm");

				if (flag) {
					bzdj = jzdm;
					break;
				}

				if (bzdj.equalsIgnoreCase(jzdm)) {
					flag = true;
				}
			}
		} else if ("self".equalsIgnoreCase(czlx) || "edit".equalsIgnoreCase(czlx)) {// 查看(修改)本级别
			bzdj = menuId.split("_")[0];
			bzdm = menuId.split("_")[1];
			sjdm = getOneValue("jxbzdmb", "sjdm", "bzdm", bzdm);
		}

		model.setBzdm(bzdm);
		model.setSjdm(sjdm);
		model.setBzdj(bzdj);

		return dao.getJxbzInfo(model, user);
	}
	
	/**
	 * 保存项目上报
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveJxbz(JxglJxbzForm model, User user,
			HttpServletRequest request) {

		String czlx = model.getCzlx();// 操作类型

		// 军训编制代码表
		String tableName = "jxbzdmb";
		// 主键
		String pk = "bzdm";
		// 主键值
		String[] pkValue = null;
		// 修改字段
		String[] onezd = null;

		if ("same".equalsIgnoreCase(czlx)) {
			pk = "1";
			pkValue = new String[] { "2" };
			onezd = new String[] { "bzmc", "bzdj", "jsdm", "jgbh", "sjdm", "xn" };
		}else if("next".equalsIgnoreCase(czlx)){
			pkValue = new String[] { "2" };
			onezd = new String[] { "bzmc", "bzdj", "jsdm", "jgbh", "sjdm", "xn" };
		} else if ("edit".equalsIgnoreCase(czlx)) {
			pkValue = new String[] { model.getBzdm() };
			onezd = new String[] { "bzdm", "bzmc", "bzdj", "jsdm", "jgbh",
					"sjdm", "xn" };
		}

		model.setBzmc(unicode2Gbk(model.getBzmc()).replace("%20", ""));
		model.setJgbh(unicode2Gbk(model.getJgbh()).replace("%20", ""))	;
		model.setJsdm(unicode2Gbk(model.getJsdm()).replace("%20", ""))	;
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 保存项目上报
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveBjfp(JxglJxbzForm model, User user) throws Exception {

		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		JxglJxbzModel saveModel = new JxglJxbzModel();

		// 军训编制代码表
		String tableName = "jxbzdmb";
		// 主键
		String pk = "1";
		// 主键值
		String[] pkValue = new String[] { "2" };
		// 批量字段
		String[] arrzd = new String[] { "bzdm", "bzmc" };
		// 单一字段
		String[] onezd = new String[] { "bzdj", "xn", "sjdm" };

		// 当前学年
		String xn = Base.currXn;
		saveModel.setXn(xn);
		// 最小建制
		String bzdj = jxszModel.getMinBz();
		saveModel.setBzdj(bzdj);
		// 上级代码
		String sjdm = model.getSjdm();
		saveModel.setSjdm(sjdm);
		// 编制代码
		String[] bzdm = model.getPk();
		saveModel.setBzdm(bzdm);
		// 编制名称
		String[] bzmc = dao.getBzmc(bzdm);
		saveModel.setBzmc(bzmc);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, saveModel, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 获得级分配列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getBjfpList(JxglJxbzForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		return dao.getBjfpList(model, user);
	}
	
	/**
	 * 保存项目上报
	 * 
	 * @author 伟大的骆
	 */
	public Boolean delJxbz(JxglJxbzForm model, User user) {
		

		// 军训编制代码表
		String tableName = "jxbzdmb";
		// 主键
		String pk = "bzdm";
		// 菜单ID
		String menuId = model.getMenuId();
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[]{menuId.split("_")[1]});
		
		boolean flag = false;

		try {
			flag = delInfoInDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		//删除下级编制
		if(flag){
			flag = dao.delXjbz();
		}
		
		return flag;
	}
	
	/**
	 * 创建最大编制Html
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createMaxbzHtml(JxglJxbzForm model, User user,
			HttpServletResponse response) throws IOException {
		
		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		
		//军训建制列表
		List<HashMap<String, String>> jxbzList = getJxbzList(model,
				user);
		// 最大等级编制
		String maxBz = jxszModel.getMaxBz();
		// 选中的编制
		String checkedBzdm = model.getCheckedBzdm();
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		html.append("<h3><span>编制</span></h3>");
		html.append("<ul>");
		
		if (jxbzList != null && jxbzList.size() > 0) {
			
			boolean flag = false;
			
			for (int i = 0; i < jxbzList.size(); i++) {
				HashMap<String, String> map = jxbzList.get(i);
				// 编制等级
				String bzdj = map.get("bzdj");
				// 编制代码
				String bzdm = map.get("bzdm");
				// 编制名称
				String bzmc = map.get("bzmc");

				if (maxBz.equalsIgnoreCase(bzdj)) {
					
					if(Base.isNull(checkedBzdm)){
						flag = true;
						checkedBzdm = bzdm;
					}else if(checkedBzdm.equalsIgnoreCase(bzdm)){
						flag = true;
					}
					
					if(flag){
						
						html.append("<li id=\"li_"+bzdm+"\" class=\"current\">");
						html.append("<input type=\"hidden\" id=\"checkedBzdm\" name=\"checkedBzdm\" value=\""+checkedBzdm+"\" />	");
						//html.append("<input type=\"hidden\" id=\"hadBz\" name=\"hadBz\" value=\"yes\" />	");
						flag=false;
					}else{
						html.append("<li id=\"li_"+bzdm+"\">");
					}
					
					html.append("<a href=\"#\" name=\"jxbz\" id=\"" + bzdj + "_" + bzdm + "\" onclick=\"clickBz(this);return false;\" >");
					html.append(bzmc);
					html.append("</a>");
					html.append("</li>");
				}
			}
		}
		
		html.append("</ul>");
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 创建最大编制Html
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createNextbzHtml(JxglJxbzForm model, User user,
			HttpServletResponse response) throws IOException {

		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;

		// 军训建制列表
		List<HashMap<String, String>> jxbzList = getJxbzList(model, user);
		// 最大等级编制
		String maxBz = jxszModel.getMaxBz();
		// 第二等级编制
		String secBz = jxszModel.getSecBz();
		// 第三等级编制
		String thiBz = jxszModel.getThiBz();
		// 第四等级编制
		String fouBz = jxszModel.getFouBz();
		
		// 二级建制列表
		List<HashMap<String, String>> secList = new ArrayList<HashMap<String, String>>();
		// 三级建制列表
		List<HashMap<String, String>> thiList = new ArrayList<HashMap<String, String>>();
		// 四级建制列表
		List<HashMap<String, String>> fouList = new ArrayList<HashMap<String, String>>();
		
		if (jxbzList != null && jxbzList.size() > 0) {

			for (int i = 0; i < jxbzList.size(); i++) {
				HashMap<String, String> map = jxbzList.get(i);
				// 编制等级
				String bzdj = map.get("bzdj");
				
				if("2".equalsIgnoreCase(bzdj)){
					secList.add(map);
				}else if("3".equalsIgnoreCase(bzdj)){
					thiList.add(map);
				}else if("4".equalsIgnoreCase(bzdj)){
					fouList.add(map);
				}
			}
		}
		
		// 选中的编制
		String checkedBzdm = model.getCheckedBzdm();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		html.append("<table width=\"100%\" border=\"0\">");

		if (jxbzList != null && jxbzList.size() > 0) {

			for (int i = 0; i < jxbzList.size(); i++) {
				HashMap<String, String> map = jxbzList.get(i);
				// 编制等级
				String bzdj = map.get("bzdj");
				// 编制代码
				String bzdm = map.get("bzdm");
				// 编制名称
				String bzmc = map.get("bzmc");
				// 上级代码
				String sjdm = map.get("sjdm");
				// 下级编制数量
				int num = Integer.parseInt(map.get("num"));

				if (secBz.equalsIgnoreCase(bzdj)
						&& checkedBzdm.equalsIgnoreCase(sjdm)) {

					html.append("<tbody id=\"tbody_" + bzdm + "\">");

					html.append("<tr id=\"" + bzdm + "\" ");
					html.append("class=\"tr_02\">");

					// 二级编制
					html.append("<td style=\"width:80px\" class=\"list_02\"");
					html.append("rowspan=\"" + num + "\" ");
					html.append(">");
					
					html.append("<label>");
					html.append("<a href=\"#\" name=\"jxbz\" id=\"" + bzdj + "_" + bzdm
							+ "\" onclick=\"return false;\">");
					html.append(bzmc);
					html.append("</a></label>");
					html.append("</td>");
					
					boolean thi_flag = true;
					boolean had_thi = false;
					boolean had_fou = false;
					
					if (thiList != null && thiList.size() > 0) {
						// 三级编制
						for (int j = 0; j < thiList.size(); j++) {
							HashMap<String, String> thiMap = thiList.get(j);
							// 编制等级
							String thi_bzdj = thiMap.get("bzdj");
							// 编制代码
							String thi_bzdm = thiMap.get("bzdm");
							// 编制名称
							String thi_bzmc = thiMap.get("bzmc");
							// 上级代码
							String thi_sjdm = thiMap.get("sjdm");
							//System.out.println("thi_sjdm("+thi_bzmc+"):"+thi_sjdm);
							if(thi_sjdm.equalsIgnoreCase(bzdm)){
								
								
								had_thi = true;
								boolean li_flag = false;
								
								if(thi_flag){
									html.append("<td style=\"width:100px\" class=\"list_03\">");
									html.append("<label>");
									html.append("<a href=\"#\" name=\"jxbz\" id=\"" + thi_bzdj + "_" + thi_bzdm
											+ "\" onclick=\"return false;\">");
									html.append(thi_bzmc);
									html.append("</a></label>");
									html.append("</td>");
									
									li_flag = true;
									thi_flag = false;
								}
								
								int four_num = 0;
								boolean four_flag = true;
								
								// 四级编制
								if (fouList != null && fouList.size() > 0) {
									had_fou = false;
									for (int k = 0; k < fouList.size(); k++) {
										HashMap<String, String> fouMap = fouList
												.get(k);
										// 编制等级
										String fou_bzdj = fouMap.get("bzdj");
										// 编制代码
										String fou_bzdm = fouMap.get("bzdm");
										// 编制名称
										String fou_bzmc = fouMap.get("bzmc");
										// 上级代码
										String fou_sjdm = fouMap.get("sjdm");
										
										if(fou_sjdm.equalsIgnoreCase(thi_bzdm) && li_flag){
											
											had_fou = true;
											
											if(four_flag){
												html.append("<td>");
												html.append("<ul>");
												
												four_flag = false;	
											}
											
											if(four_num<8){
												html.append("<li>");
												html.append("<a href=\"#\" name=\"minbz\" id=\"" + fou_bzdj + "_" + fou_bzdm
												+ "\" onclick=\"return false;\" title=\""+fou_bzmc+"\">");
												if(fou_bzmc.length()>5){
													html.append(fou_bzmc.substring(0, 5)+"...");
												}else{
													html.append(fou_bzmc);
												}
												html.append("</a>");
												html.append("</li>");
											}
											
											four_num++;
										}
									}
								}
								
								if(!four_flag){
									html.append("</ul>");
									html.append("</td>");
								}
							}
						}
					}
					
					if (!had_thi) {
						// 空行
						html.append("<td style=\"\">");
						html.append("<label>");
						html.append("<a>");
						html.append("</a></label>");
						html.append("</td>");
					}
					
					if (!had_fou) {
						// 空行
						html.append("<td style=\"\">");
						html.append("<label>");
						html.append("<a>");
						html.append("</a></label>");
						html.append("</td>");
					}
					
					html.append("</tr>");

					if (had_thi) {
						
						boolean thi_next_flag = false;
						
						if (thiList != null && thiList.size() > 0) {
							// 三级编制
							for (int j = 0; j < thiList.size(); j++) {
								HashMap<String, String> thiMap = thiList.get(j);
								// 编制等级
								String thi_bzdj = thiMap.get("bzdj");
								// 编制代码
								String thi_bzdm = thiMap.get("bzdm");
								// 编制名称
								String thi_bzmc = thiMap.get("bzmc");
								// 上级代码
								String thi_sjdm = thiMap.get("sjdm");
								
								if (thi_sjdm.equalsIgnoreCase(bzdm)) {			
									
									if(thi_next_flag){
										
										html.append("<tr id=\"" + bzdm + "\" ");
										html.append("class=\"tr_02\">");
										
										html.append("<td style=\"width:100px\" class=\"list_03\">");
										html.append("<label>");
										html.append("<a href=\"#\" name=\"jxbz\" id=\""+ thi_bzdj + "_" + thi_bzdm+ "\" onclick=\"return false;\">");
										html.append(thi_bzmc);
										html.append("</a>");
										html.append("</label>");
										html.append("</td>");
										
										int four_num = 0;
										boolean four_flag = true;
										
										// 四级编制
										if (fouList != null && fouList.size() > 0) {
											for (int k = 0; k < fouList.size(); k++) {
												HashMap<String, String> fouMap = fouList
														.get(k);
												// 编制等级
												String fou_bzdj = fouMap.get("bzdj");
												// 编制代码
												String fou_bzdm = fouMap.get("bzdm");
												// 编制名称
												String fou_bzmc = fouMap.get("bzmc");
												// 上级代码
												String fou_sjdm = fouMap.get("sjdm");
												
												if(fou_sjdm.equalsIgnoreCase(thi_bzdm)){
													
													if(four_flag){
														html.append("<td>");
														html.append("<ul>");
														
														four_flag = false;	
													}
													
													if(four_num<8){
														html.append("<li>");
														html.append("<a href=\"#\" name=\"minbz\" id=\"" + fou_bzdj + "_" + fou_bzdm
														+ "\" onclick=\"return false;\" title=\""+fou_bzmc+"\">");
														if(fou_bzmc.length()>5){
															html.append(fou_bzmc.substring(0, 5)+"...");
														}else{
															html.append(fou_bzmc);
														}
														html.append("</a>");
														html.append("</li>");
													}
													
													four_num++;
												}
											}
										}
										
										if(!four_flag){
											html.append("</ul>");
											html.append("</td>");
										}
										
										if (!had_fou) {
											html.append("<td style=\"\">");
											html.append("<label>");
											html.append("<a>");
											html.append("</a></label>");
											html.append("</td>");
										}
										
										html.append("</tr>");
									}
									
									thi_next_flag = true;
								}

								
							}
						}
					}
					
					html.append("</tbody>");

				}
			}
		}

		html.append("</table>");
		//System.out.print(html);
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 创建Js
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createJxbzJs(JxglJxbzForm model, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		html.append("<script defer=\"defer\">");
		// 右键菜单
		html.append("var imageMenuData = [");

		// ===============第一部分========================
		html.append("[");

		// 同级
		html.append("{");
		html.append("text: \"增加同级编制\",");
		html.append(" func:function(){");
		html.append("var id = jQuery(this).attr('id');");
		html.append("showJxbzUpdate(id,\"same\");");
		html.append("}");
		html.append("},");
		// 子级
		html.append("{");
		html.append("text: \"增加子级编制\",");
		html.append(" func:function(){");
		html.append("var id = jQuery(this).attr('id');");
		html.append("showJxbzUpdate(id,\"next\");");
		html.append("}");
		html.append("}");

		html.append("],");
		// ===============第一部分 end========================

		// ===============第二部分 ========================
		html.append("[");
		// 查看本级
		html.append("{");
		html.append("text: \"查看本编制\",");
		html.append("func:function(){");
		html.append("var id = jQuery(this).attr('id');");
		html.append("showJxbzUpdate(id,\"self\");");
		html.append(" }");
		html.append(" },");
		// 删除本级
		html.append("{");
		html.append("text: \"修改本编制\",");
		html.append("func:function(){");
		html.append("var id = jQuery(this).attr('id');");
		html.append("showJxbzUpdate(id,\"edit\");");
		html.append("}");
		html.append("},");
		// 删除本级
		html.append("{");
		html.append("text: \"删除本编制\",");
		html.append("func: function() {");
		html.append("var id = jQuery(this).attr('id');");
		html.append("$(\"menuId\").value=id;");
		html.append("confirmInfo('您确认要删除所选的编制吗？',delJxbz);");
		html.append("}");
		html.append("}");

		html.append(" ]");
		// ===============第二部分 end========================
		
		html.append("];");
		
		// 右键菜单(最小级)

		html.append("var rightMenuMin = [");

		html.append("[");
		// 删除
		html.append("{");
		html.append("text: \"删除本编制\",");
		html.append("func: function() {");
		html.append("var id = jQuery(this).attr('id');");
		html.append("$(\"menuId\").value=id;");
		html.append("confirmInfo('您确认要删除所选的编制吗？',delJxbz);");
		html.append("}");
		html.append("}");
		html.append("]");

		html.append("];");
		
		//html.append("jQuery.ajaxSetup({async: false});");
		html.append("jQuery(\"a[name=minbz]\").smartMenu(rightMenuMin, {name: \"a\"});");
		html.append("jQuery(\"a[name=jxbz]\").smartMenu(imageMenuData, {name: \"b\"});");
		//html.append("jQuery.ajaxSetup({async: true});");
		
		html.append("</script>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 创建Js
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public void createMinJxbzJs(JxglJxbzForm model, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		html.append("<script defer=\"defer\">");
		
		// 右键菜单(最小级)

		html.append("var rightMenuMin = [");

		html.append("[");
		// 删除
		html.append("{");
		html.append("text: \"删除本编制\",");
		html.append("func: function() {");
		html.append("var id = jQuery(this).attr('id');");
		html.append("$(\"menuId\").value=id;");
		html.append("confirmInfo('您确认要删除所选的编制吗？',delJxbz);");
		html.append("}");
		html.append("}");
		html.append("]");

		html.append("];");
		
		html.append("jQuery(\"a[name=minbz]\").smartMenu(rightMenuMin, {name: \"a\"});");
		
		html.append("</script>");

		response.getWriter().print(html.toString());
	}
}