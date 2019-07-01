package xgxt.comm.search;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 高级查询_Option_list类
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

public class SearchOptionList extends SearchService{

	/********************************************************************************************************************************/
	/**************************************************为解决65535问题，高级查询重构，优化**************************************************/
	/********************************************************************************************************************************/

	/**
	 * 根据条件获得相关列表(点击查询)
	 * 
	 * @author 伟大的骆
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getSearchOptionList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {

		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		// 特殊条件
		String[] spTj = searchOptionModel.getSpTj();
		// 联动条件
		String[] ldTj = searchOptionModel.getLdTj();
		// path
		String path = searchOptionModel.getPath();
		
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// 类型
				String lx = tjMap.get("lx");
				// 条件
				String tj = tjMap.get("tj");
				// 名称
				String mc = tjMap.get("mc");
				
				// 点击查询 AND 非联动条件
				if ("djcx".equalsIgnoreCase(lx) && !isExistInArr(ldTj, tj)) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					Class myClass = searchOptionModel.getClass();

					// 取值方法
					String getMethod = "get" + tj.substring(0, 1).toUpperCase()
							+ tj.substring(1) + "TjList";

					try {
						// Option 列表
						List<HashMap<String, String>> list = (List<HashMap<String, String>>) myClass
								.getMethod(getMethod, (Class[]) null).invoke(
										searchOptionModel, (Object[]) null);

//						if (list != null && list.size() > 0) {
//
//						} else {
							String setMethod = "set"
									+ tj.substring(0, 1).toUpperCase()
									+ tj.substring(1) + "TjList";

							Method methodName = myClass.getMethod(setMethod,
									new Class[] {});

							methodName.invoke(searchOptionModel, null);

							list = (List<HashMap<String, String>>) myClass
									.getMethod(getMethod, (Class[]) null)
									.invoke(searchOptionModel, (Object[]) null);
//						}

						map.put("tj", tj);
						map.put("mc", mc);
						map.put("tjLit", list);

						searchList.add(map);

					} catch (IllegalArgumentException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				} 
				// 联动字段
				else if ("djcx".equalsIgnoreCase(lx) && isExistInArr(ldTj, tj)) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					Class myClass = searchOptionModel.getClass();

					// 取值方法
					String getMethod = "get" + tj.substring(0, 1).toUpperCase()
							+ tj.substring(1) + "TjList";

					// Option 列表
					List<HashMap<String, Object>> list;
					try {
						list = (List<HashMap<String, Object>>) myClass
								.getMethod(getMethod, (Class[]) null).invoke(
										searchOptionModel, (Object[]) null);
						
					
						map.put("tj", tj);
						map.put("mc", mc);
						map.put("tjLit", list);

						searchList.add(map);
					} catch (IllegalArgumentException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}

				}
				// 自定义高级查询
				else if ("zdycx".equalsIgnoreCase(lx)){
					
					HashMap<String, Object> map = new HashMap<String, Object>();
					SearchForm searchForm = new SearchForm();
					List<HashMap<String, String>> list = searchForm.getZdycxTjList(tj,path);
					
					map.put("tj", tj);
					map.put("lx", lx);
					map.put("mc", mc);
					map.put("tjLit", list);
					searchList.add(map);
					
				}
			}
		}

		return searchList;
	}
	
	/**
	 * 根据条件获得相关列表(时间)
	 * 
	 * @author 伟大的骆
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getSearchTimeList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {

		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// 类型
				String lx = tjMap.get("lx");
				// 条件
				String tj = tjMap.get("tj");
				// 名称
				String mc = tjMap.get("mc");
				//格式化
				String gshlx = tjMap.get("gshlx");
				
				// 时间查询 AND 非联动条件
				if ("sjcx".equalsIgnoreCase(lx)) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("tj", tj);
					map.put("mc", mc);
					map.put("gshlx", gshlx);
					searchList.add(map);

				}
				
				if ("dgsjcx".equalsIgnoreCase(lx)) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("tj", tj);
					map.put("mc", mc);
					map.put("gshlx", gshlx);
					map.put("sjlx", "dg");

					searchList.add(map);

				}
			}
		}

		return searchList;
	}
	/**
	 * 
	 * @描述:批量查询条件
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-5-16 下午07:23:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param searchOptionModel
	 * @param tjList
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getSearchBatchList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {

		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// 类型
				String lx = tjMap.get("lx");
				// 条件
				String tj = tjMap.get("tj");
				// 名称
				String mc = tjMap.get("mc");
				if ("plcx".equalsIgnoreCase(lx)) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("tj", tj);
					map.put("mc", mc);
					searchList.add(map);
				}
			}
		}

		return searchList;
	}
	/**
	 * 根据条件获得数值区间
	 */
	public List<HashMap<String, Object>> getSearchNumList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {
		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// 类型
				String lx = tjMap.get("lx");
				// 条件
				String tj = tjMap.get("tj");
				// 名称
				String mc = tjMap.get("mc");
				// 数值区间 AND 非联动条件
				if ("numcx".equalsIgnoreCase(lx)) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("tj", tj);
					map.put("mc", mc);
					searchList.add(map);
				}
			}
		}
		return searchList;
	}
	
	/**
	 * 创建高级查询Div（点击查询）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void createSearchDiv(SearchForm searchOptionModel,
			List<HashMap<String, Object>> optionList,List<HashMap<String, Object>> batchList,
			List<HashMap<String, Object>> timeList,
			List<HashMap<String, Object>> numList, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		// 特殊条件
		String[] spTj = searchOptionModel.getSpTj();
		// 联动条件
		String[] ldTj = searchOptionModel.getLdTj();
		// 样式包路径
		String stylePath = searchOptionModel.getStylePath();
		//批量查询条件
		if(batchList != null && batchList.size() > 0){
			html.append(createBatchHtml(batchList));
			
		}
		if (optionList != null && optionList.size() > 0) {
			for (int i = 0; i < optionList.size(); i++) {
				
				HashMap<String, Object> tjMap = optionList.get(i);
				// 条件
				String tj = (String) tjMap.get("tj");	
				// 类型
				String lx = (String) tjMap.get("lx");
				
				// 自定义高级查询条件
				if (!StringUtils.isBlank(lx) && "zdycx".equalsIgnoreCase(lx)){
					html.append(createPtHtml(tjMap));
				} else if(!isExistInArr(ldTj, tj) && !isExistInArr(spTj, tj)) { // 非联动条件 AND 非特殊条件
					html.append(createPtHtml(tjMap));
				} else if (isExistInArr(spTj, tj)) {// 特殊字段
					html.append(createSpHtml(tjMap));
				} else if (isExistInArr(ldTj, tj)) {// 联动字段
					
 					if ("nj".equalsIgnoreCase(tj)) {// 年级
						html.append(createNjHtml(tjMap));
					} else if ("xy".equalsIgnoreCase(tj)) {// 学院
						html.append(createXyHtml(tjMap, stylePath));
					} else if ("zy".equalsIgnoreCase(tj)) {// 专业
						html.append(createZyHtml(tjMap, stylePath));
					} else if ("sy".equalsIgnoreCase(tj)) {// 书院
						html.append(createSyHtml(tjMap));
					} else if ("bj".equalsIgnoreCase(tj)) {// 行政班级
						html.append(createBjHtml(tjMap, stylePath));
					} else if ("zybj".equalsIgnoreCase(tj)){//专业班级
						html.append(createZybjHtml(tjMap, stylePath));
					}else if ("njNew".equalsIgnoreCase(tj)) {// 年级[全]
						html.append(createNjNewHtml(tjMap));
					} else if ("xyNew".equalsIgnoreCase(tj)) {// 学院[全]
						html.append(createXyNewHtml(tjMap, stylePath));
					} else if ("zyNew".equalsIgnoreCase(tj)) {// 专业[全]
						html.append(createZyNewHtml(tjMap, stylePath));
					} else if ("bjNew".equalsIgnoreCase(tj)) {// 班级[全]
						html.append(createBjNewHtml(tjMap, stylePath));
					} else if ("xqdm".equalsIgnoreCase(tj)) {// 校区
						html.append(createXqdmHtml(tjMap));					
					} else if ("yqdm".equalsIgnoreCase(tj)) {// 园区
						html.append(createYqdmHtml(tjMap));
					} else if ("ld".equalsIgnoreCase(tj)
							|| "ch".equalsIgnoreCase(tj)) {// 楼栋,层号
						html.append(createLdChHtml(tjMap));
					} else if ("qsh".equalsIgnoreCase(tj)) {// 寝室号
						html.append(createQshHtml(tjMap, stylePath));
					} else if ("qgbm".equalsIgnoreCase(tj)){// 勤工部门
						html.append(createQgbmHtml(tjMap));
					} else if ("tid".equalsIgnoreCase(tj)) {// 团id
						html.append(createTidHtml(tjMap));
					} else if ("yid".equalsIgnoreCase(tj)) {// 营id
						html.append(createYidHtml(tjMap));
					} else if ("lid".equalsIgnoreCase(tj)) {// 连id
						html.append(createLidHtml(tjMap));
					} else if ("pid".equalsIgnoreCase(tj)) {// 排id
						html.append(createPidHtml(tjMap));
					} else if ("bid".equalsIgnoreCase(tj)) {
						html.append(createBidHtml(tjMap));
					} else if ("ssid".equalsIgnoreCase(tj)) {
						html.append(createSsidHtml(tjMap));
					} else if ("gyjllbdldm".equalsIgnoreCase(tj)){//违纪类别大类代码
						html.append(createTylddmHtml(tjMap,"gyjllbdldm","clickGyjllbdldm"));
					} else if ("gyjllbdm".equalsIgnoreCase(tj)){//违纪类别类别代码
						html.append(createTylddmHtml(tjMap,"gyjllbdm","clickGyjllbdm"));
					} else if("sheng".equalsIgnoreCase(tj)){
						html.append(createTylddmHtml(tjMap,"sheng","clickSHENG"));//省
					} else if("shi".equalsIgnoreCase(tj)){
						html.append(createTylddmHtml(tjMap,"shi","clickSHI"));//市
					} else if("qu".equalsIgnoreCase(tj)){
						html.append(createTylddmHtml(tjMap,"qu","clickQU"));//区
					} else if("xxmlx".equalsIgnoreCase(tj)){	//项目类型
						html.append(createXxmlxHtml(tjMap));
					} else if("xxmxz".equalsIgnoreCase(tj)){	//项目性质
						html.append(createXxmxzHtml(tjMap));
					} else if ("xmmc".equalsIgnoreCase(tj)){	//评奖项目名称
						html.append(createXmmcHtml(tjMap, stylePath));
					} else {
						html.append(createPtHtml(tjMap));
					}
				}
			}
		}
		
		if (timeList != null && timeList.size() > 0) {
			for (int i = 0; i < timeList.size(); i++) {
				HashMap<String, Object> tjMap = timeList.get(i);
				// 条件
				String tj = (String) tjMap.get("tj");
				// 条件名称
				String mc = (String) tjMap.get("mc");
				
				String sjlx = (String) tjMap.get("sjlx");
				String gshlx=(String)tjMap.get("gshlx");
				if(Base.isNull(gshlx)) {
					gshlx = "ymmdd";
				}
				
				if("dg".equalsIgnoreCase(sjlx)){
					html.append("<dl>");
					html.append("<dt>"+mc+"：</dt>");
					html.append("<dd>");
					html.append("<ul>");
					html.append("<li>");
					html.append("<input type=\"text\" name=\"searchModel.search_tj_kssj\" class=\"jssj\" ");
					html.append("onclick=\"return showCalendar('"+tj+"_kssj','"+gshlx+"');\" ");
					html.append("id=\""+tj+"_kssj\" readonly=\"readonly\"/>");
					html.append("</li>");
					html.append("</ul>");
					html.append("<input type=\"hidden\" name=\"search_tj_sjmc\" value=\""+mc+"\">");
					html.append("</dd>");
					html.append("</dl>");
				}else{				
					html.append("<dl>");
					html.append("<dt>"+mc+"：</dt>");
					html.append("<dd>");
					html.append("<ul>");
					html.append("<li>");
					html.append("  ");
					html.append("<input type=\"text\" name=\"searchModel.search_tj_kssj\" class=\"jssj\" ");
					html.append("onclick=\"return showCalendar('"+tj+"_kssj','"+gshlx+"');\" ");
					html.append("id=\""+tj+"_kssj\" ");
					html.append("onchange=\"createKssjOrJssj()\" readonly=\"readonly\"/>");
					html.append("</li>");
					html.append("<li>");
					html.append("至 ");
					html.append("<input type=\"text\" name=\"searchModel.search_tj_jssj\" class=\"jssj\" ");
					html.append("onclick=\"return showCalendar('"+tj+"_jssj','"+gshlx+"');\" ");
					html.append("id=\""+tj+"_jssj\" ");
					html.append("onchange=\"createKssjOrJssj()\" readonly=\"readonly\"/> ");
					html.append("</li>");
					html.append("</ul>");
					html.append("<input type=\"hidden\" name=\"search_tj_sjmc\" value=\""+mc+"\">");
					html.append("</dd>");
					html.append("</dl>");
				}
			}
		}
		
		if (numList != null && numList.size() > 0) {
			for (int i = 0; i < numList.size(); i++) {
				HashMap<String, Object> tjMap = numList.get(i);
				// 条件
				String tj = (String) tjMap.get("tj");
				// 条件名称
				String mc = (String) tjMap.get("mc");
				
				html.append("<dl>");
				html.append("<dt>"+mc+"：</dt>");
				html.append("<dd>");
				html.append("<ul>");
				html.append("<li>");
				html.append("  ");
				html.append("<input type=\"text\" name=\"searchModel.search_tj_ksnum\" class=\"\" ");
				html.append("onkeyup=\"value=value.replace(/(?:\\D*)?(\\d*)?(\\.)?(\\d*)?/ig,'$1$2$3');createKsnumOrJsnum();\" ");
				html.append("id=\""+tj+"_ksnum\" maxlength='10'/>");
				html.append("</li>");
				html.append("<li>");
				html.append("至 ");
				html.append("<input type=\"text\" name=\"searchModel.search_tj_jsnum\" class=\"\" ");
				html.append("onkeyup=\"value=value.replace(/(?:\\D*)?(\\d*)?(\\.)?(\\d*)?/ig,'$1$2$3');createKsnumOrJsnum();\" ");
				html.append("id=\""+tj+"_jsnum\" maxlength='10'/>");
				html.append("</li>");
				html.append("</ul>");
				html.append("<input type=\"hidden\" name=\"search_tj_nummc\" value=\""+mc+"\">");
				html.append("</dd>");
				html.append("</dl>");
			}
		}

		// =========================标题 end===========================
	
		//System.out.println(html.toString());
		response.getWriter().print(html.toString());
	}

	/**
	 * 创建高级查询Div（校区）
	 * @author zhanghui
	 */
	private String createXqdmHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("xqdm_ul") ? "" : " id=\"xqdm_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_xqdm\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickXq(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"xqdm_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 创建高级查询Div（园区）
	 * @author zhanghui
	 */
	private String createYqdmHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("yqdm_ul") ? "" : " id=\"yqdm_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_yqdm\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickYq(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"yqdm_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 创建高级查询Div（楼栋,层号条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createLdChHtml(HashMap<String, Object> tjMap) {
		
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull(ul_id) ? "" : " id=\"" + ul_id + "\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				if("ld".equalsIgnoreCase(tj)){
					html.append("style=\"" + display + "\" ");
					if(Base.isNull(display)){
						html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
					}else{
						html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
					}
				}else{
					html.append("id=\"tj_ch_" + tjdm + "\" ");
				}
				
				html.append("name=\"" + a_name + "\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append(onclick + "(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag && ("ld".equalsIgnoreCase(tj))) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\""+more_id+"\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 创建高级查询Div（寝室号条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createQshHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 寝室号数目
		int qsh_num = 0;
		// 寝室号拼音数目
		int qsh_py_num = 0;

		if (tjLit != null && tjLit.size() > 0) {

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 寝室列表
				List<HashMap<String, String>> qshList = (List<HashMap<String, String>>) map
						.get("qsList");

				if (qshList != null && qshList.size() > 0) {

					html.append("<li>");

					html.append("<h5 ");
					if (qsh_num >= 12) {
						html.append("id=\"qsh_py_yc_" + qsh_py_num + "\" ");
						html.append("style=\"display: none\" ");
					} else {
						html.append("id=\"qsh_py_xs_" + qsh_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					qsh_py_num++;
					
					for (int k = 0; k < qshList.size(); k++) {
						
						HashMap<String, String> qsMap = qshList.get(k);
						// 寝室代码
						String qshdm = qsMap.get("qshdm");
						// 寝室名称
						String qshmc = qsMap.get("qshmc");
						
						if(!"其他".equalsIgnoreCase(qshmc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (qsh_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"qsh_mc_yc_" + qshdm + "\" ");
							} else {
								html.append("id=\"qsh_mc_xs_" + qshdm + "\" ");
							}
							html.append("name=\"a_qsh_mc\" ");
							html.append("onclick=\"");
							html.append("clickQsh(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+qshdm+"','"+qshmc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+qshmc+"\" ");
							html.append("> ");
							html.append(qshmc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (qsh_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"qsh_qt_" + qshdm + "\" ");
							} else {
								html.append("id=\"qsh_hidv_qt_" + qshdm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickQshQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (qsh_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"qsh_hidv_yc\" "); 
							html.append("id=\"qsh_hidv_yc_"+qshdm+"\" "); 
							html.append("value=\""+qshdm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"qsh_hidv_qt\" ");
							html.append("id=\"qsh_hidv_qt_" + qshdm + "\" ");
							html.append("value=\"" + qshdm + "\"/> ");															
						}
						
						qsh_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"qsh_py_num\" value=\""+qsh_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"qsh_num\" value=\""+qsh_num+"\"/> ");
		
		html.append("</ul>");
		
		if (qsh_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"qsh_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * 创建高级查询Div（班级条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createBjHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 班级数目
		int bj_num = 0;
		// 班级拼音数目
		int bj_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 班级列表
				List<HashMap<String, String>> bjList = (List<HashMap<String, String>>) map
						.get("bjList");

				if (bjList != null && bjList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (bj_num >= 12) {
						html.append("id=\"bj_py_yc_" + bj_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"bj_py_xs_" + bj_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					bj_py_num++;
					
					for (int k = 0; k < bjList.size(); k++) {
						
						HashMap<String, String> bjMap = bjList.get(k);
						// 班级代码
						String bjdm = bjMap.get("bjdm");
						// 班级名称
						String bjmc = bjMap.get("bjmc");
						
						if(!"其他".equalsIgnoreCase(bjmc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"bj_mc_yc_" + bjdm + "\" ");
							} else {
								html.append("id=\"bj_mc_xs_" + bjdm + "\" ");
							}
							html.append("name=\"a_bj_mc\" ");
							html.append("onclick=\"");
							html.append("clickBj(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+bjdm+"','"+bjmc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+bjmc+"\" ");
							html.append("> ");
							html.append(bjmc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"bj_qt_" + bjdm + "\" ");
							} else {
								html.append("id=\"bj_hidv_qt_" + bjdm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickBjQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (bj_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"bj_hidv_yc\" "); 
							html.append("id=\"bj_hidv_yc_"+bjdm+"\" "); 
							html.append("value=\""+bjdm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"bj_hidv_qt\" ");
							html.append("id=\"bj_hidv_qt_" + bjdm + "\" ");
							html.append("value=\"" + bjdm + "\"/> ");															
						}
						
						bj_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"bj_py_num\" value=\""+bj_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"bj_num\" value=\""+bj_num+"\"/> ");
		
		html.append("</ul>");
		
		if (bj_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"bj_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	private String createZybjHtml(HashMap<String, Object> tjMap,String stylePath) {


		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");

		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");

		// 班级数目
		int bj_num = 0;
		// 班级拼音数目
		int bj_py_num = 0;

		if (tjLit != null && tjLit.size() > 0) {

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("zybjpy");
				// 班级列表
				List<HashMap<String, String>> bjList = (List<HashMap<String, String>>) map
						.get("zybjList");

				if (bjList != null && bjList.size() > 0) {

					html.append("<li>");

					html.append("<h5 ");
					if (bj_num >= 12) {
						html.append("id=\"zybj_py_yc_" + bj_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"zybj_py_xs_" + bj_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");

					bj_py_num++;

					for (int k = 0; k < bjList.size(); k++) {

						HashMap<String, String> bjMap = bjList.get(k);
						// 班级代码
						String bjdm = bjMap.get("bjdm");
						// 班级名称
						String bjmc = bjMap.get("bjmc");

						if(!"其他".equalsIgnoreCase(bjmc)){
							html.append("<a ");
							html.append("href=\"#\" ");
							html.append("class=\"\" ");
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"zybj_mc_yc_" + bjdm + "\" ");
							} else {
								html.append("id=\"zybj_mc_xs_" + bjdm + "\" ");
							}
							html.append("name=\"a_zybj_mc\" ");
							html.append("onclick=\"");
							html.append("clickBj(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+bjdm+"','"+bjmc+"',this);");
							html.append("return false;\" ");
							html.append("title=\""+bjmc+"\" ");
							html.append("> ");
							html.append(bjmc);
							html.append("</a> ");

						}else{
							html.append("<a ");
							html.append("href=\"#\" ");
							html.append("class=\"moreValue_click\" ");
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"zybj_qt_" + bjdm + "\" ");
							} else {
								html.append("id=\"zybj_hidv_qt_" + bjdm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickBjQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}

						if (bj_num >= 12) {
							html.append("<input type=\"hidden\" ");
							html.append("name=\"zybj_hidv_yc\" ");
							html.append("id=\"zybj_hidv_yc_"+bjdm+"\" ");
							html.append("value=\""+bjdm+"\"/> ");

							html.append("<input type=\"hidden\" ");
							html.append("name=\"zybj_hidv_qt\" ");
							html.append("id=\"zybj_hidv_qt_" + bjdm + "\" ");
							html.append("value=\"" + bjdm + "\"/> ");
						}

						bj_num++;
					}

					html.append("</li>");
				}
			}
		}

		html.append("<input type=\"hidden\" id=\"zybj_py_num\" value=\""+bj_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"zybj_num\" value=\""+bj_num+"\"/> ");

		html.append("</ul>");

		if (bj_num >= 12) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"zybj_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}

		html.append("</dd>");
		html.append("</dl>");

		return html.toString();
	}
	/**
	 * 创建高级查询Div（专业条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createZyHtml(HashMap<String, Object> tjMap, String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 专业数目
		int zy_num = 0;
		// 专业拼音数目
		int zy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 专业列表
				List<HashMap<String, String>> zyList = (List<HashMap<String, String>>) map
						.get("zyList");

				if (zyList != null && zyList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (zy_num >= 12) {
						html.append("id=\"zy_py_yc_" + zy_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"zy_py_xs_" + zy_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					zy_py_num++;
					
					for (int k = 0; k < zyList.size(); k++) {
						
						HashMap<String, String> zyMap = zyList.get(k);
						// 专业代码
						String zydm = zyMap.get("zydm");
						// 专业名称
						String zymc = zyMap.get("zymc");
						
						if(!"其他".equalsIgnoreCase(zymc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (zy_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"zy_mc_yc_" + zydm + "\" ");
							} else {
								html.append("id=\"zy_mc_xs_" + zydm + "\" ");
							}
							html.append("name=\"a_zy_mc\" ");
							html.append("onclick=\"");
							html.append("clickZy(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+zydm+"','"+zymc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+zymc+"\" ");
							html.append("> ");
							html.append(zymc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (zy_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"zy_qt_" + zydm + "\" ");
							} else {
								html.append("id=\"zy_hidv_qt_" + zydm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickZyQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (zy_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"zy_hidv_yc\" "); 
							html.append("id=\"zy_hidv_yc_"+zydm+"\" "); 
							html.append("value=\""+zydm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"zy_hidv_qt\" ");
							html.append("id=\"zy_hidv_qt_" + zydm + "\" ");
							html.append("value=\"" + zydm + "\"/> ");															
						}
						
						zy_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"zy_py_num\" value=\""+zy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"zy_num\" value=\""+zy_num+"\"/> ");
		
		html.append("</ul>");
		
		if (zy_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"zy_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * 创建高级查询Div（学院条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createXyHtml(HashMap<String, Object> tjMap, String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 学院数目
		int xy_num = 0;
		// 学院拼音数目
		int xy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 学院列表
				List<HashMap<String, String>> xyList = (List<HashMap<String, String>>) map
						.get("xyList");

				if (xyList != null && xyList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (xy_num >= 8) {
						html.append("id=\"xy_py_yc_" + xy_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"xy_py_xs_" + xy_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					xy_py_num++;
					
					for (int k = 0; k < xyList.size(); k++) {
						
						HashMap<String, String> xyMap = xyList.get(k);
						// 学院代码
						String xydm = xyMap.get("xydm");
						// 学院名称
						String xymc = xyMap.get("xymc");
						
						html.append("<a ");
						html.append("href=\"#\" "); 
						html.append("class=\"\" "); 
						if (xy_num >= 8) {
							html.append("style=\"display: none\" ");
							html.append("id=\"xy_mc_yc_" + xydm + "\" ");
						} else {
							html.append("id=\"xy_mc_xs_" + xydm + "\" ");
						}
						html.append("name=\"a_xy_mc\" ");
						html.append("onclick=\"");
						html.append("clickXy(this);");
						html.append("creatClickedTj('"+tj+"','"+mc+"','"+xydm+"','"+xymc+"',this);");
						html.append("return false;\" "); 
						html.append("title=\""+xymc+"\" ");
						html.append("> ");
						html.append(xymc);
						html.append("</a> ");
						
						if (xy_num >= 8) {
							html.append("<input type=\"hidden\" ");
							html.append("name=\"xy_hidv_yc\" ");
							html.append("id=\"xy_hidv_yc_" + xydm + "\" ");
							html.append("value=\"" + xydm + "\" ");
							html.append("/> ");
						}
						
						xy_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"xy_py_num\" value=\""+xy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xy_num\" value=\""+xy_num+"\"/> ");
		
		html.append("</ul>");
		
		if (xy_num >= 8) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"xy_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * 创建高级查询Div（年级条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createNjHtml(HashMap<String, Object> tjMap) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("nj");
				// 条件名称
				String tjmc = (String) map.get("nj");
				
				html.append("<li>");
				html.append("<a ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("id=\"tj_" + tj + "_" + tjdm + "\" ");
				html.append("name=\"tj_" + tj + "\" ");
				html.append("onclick=\"");
				html.append("clickNj(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * 创建高级查询书院
	 * @param tjMap
	 * @return
	 */
	private String createSyHtml(HashMap<String, Object> tjMap) {


		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");

		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");

		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				// 条件名称
				String tjmc = (String) map.get("mc");

				html.append("<li>");
				html.append("<a ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("id=\"tj_" + tj + "_" + tjdm + "\" ");
				html.append("name=\"tj_" + tj + "\" ");
				html.append("onclick=\"");
				html.append("clickSy(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				html.append("</li>");
			}
		}

		html.append("</ul>");
		html.append("</dd>");
		html.append("</dl>");

		return html.toString();
	}

	/**
	 * 创建高级查询Div（特殊条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createSpHtml(HashMap<String, Object> tjMap) {
		
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul>");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				html.append("name=\"tj_" + tj + "\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickOtherTj('"+tj+"','"+tjdm+"');");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if(moreFlag){
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * 创建高级查询Div（普通条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createPtHtml(HashMap<String, Object> tjMap) {
		
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul>");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				html.append("<a ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("id=\"tj_" + tj + "_" + tjdm + "\" ");
				html.append("name=\"tj_" + tj + "\" ");
				html.append("onclick=\"");
				html.append("clickTj('"+tj+"','"+tjdm+"');");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	/*
	 * 创建批量查询条件div
	 */
	@SuppressWarnings("unchecked")
	private String createBatchHtml(List<HashMap<String, Object>> batchList) {
			
			StringBuilder html = new StringBuilder();
			
			
			html.append("<dl>");
			html.append("<dt>");
			html.append("批量查询条件");
			html.append("：</dt>");
			html.append("<dd>");
			html.append("<ul>");
			
				for (int j = 0; j < batchList.size(); j++) {
					HashMap<String, Object> map = batchList.get(j);
					// 条件代码
					String tjdm = (String) map.get("tj");
					tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
					// 条件名称
					String tjmc = (String) map.get("mc");
					tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
					
					html.append("<li>");
					html.append("<a ");
					html.append("href=\"#\" ");
					html.append("class=\"\" ");
					html.append("id=\"tj_" + tjdm + "_" + tjdm + "\" ");
					html.append("name=\"tj_" + tjdm + "\" ");
					html.append("onclick=\"");
					html.append("clickPlcxTj('"+tjdm+"PlcxDiv'"+",this);");
					
					html.append("return false;\" ");
					html.append(">");
					html.append(tjmc);
					html.append("</a>");
					html.append("</li>");
				}
			
			
			html.append("</ul>");
			html.append("</dd>");
			html.append("</dl>");
			
			return html.toString();
		}
	
	/**
	 * 创建联动Div（专业，班级）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void creatBmHtml(String[] nj, String[] xy, String[] zy, String bmlx,
			String stylePath, User user, HttpServletResponse response,String sfzxs)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 用户身份
		String userStatus = user.getUserStatus();
		// 用户名
		String userName = user.getUserName();
		// 用户所在部门
		String userDep = user.getUserDep();
		
		StringBuilder html = new StringBuilder();

		if ("zy".equalsIgnoreCase(bmlx)) {// 联动专业
			List<HashMap<String, String>> zyList = getZyInfoByXy(nj, xy,
					userStatus, userName, userDep,sfzxs);
			html.append(creatZyHtml(nj, xy, zyList, stylePath));
		} else if ("zybj".equalsIgnoreCase(bmlx)) {// 联动班级
			List<HashMap<String, String>> bjList = getBjInfoByTj("", nj, xy,
					zy, userStatus, userName, userDep,sfzxs);
			html.append(creatBjHtml(nj, xy, zy, bjList, stylePath));
		}

		response.getWriter().print(html.toString());
	}

	/**
	 * 创建联动div（书院、行政班级）
	 * @param sy
	 * @param bmlx
	 * @param stylePath
	 * @param user
	 * @param response
	 * @param sfzxs
	 * @throws IOException
	 */
	public void creatSyHtml(String[] sy, String bmlx,
							String stylePath, User user, HttpServletResponse response,String sfzxs)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 用户身份
		String userStatus = user.getUserStatus();
		// 用户名
		String userName = user.getUserName();
		// 用户所在部门
		String userDep = user.getUserSyDep();

		StringBuilder html = new StringBuilder();

		if ("bj".equalsIgnoreCase(bmlx)) {// 联动班级
			List<HashMap<String, String>> bjList = getXzbjInfoByTj("", sy, userStatus, userName, userDep,sfzxs);
			html.append(creatXzbjHtml(sy, bjList, stylePath));
		}

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 获得专业过滤条件
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	private String creatZyHtml(String[] nj, String[] xy,
			List<HashMap<String, String>> zyList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// 专业数目
		int zy_num = 0;
		// 专业拼音数目
		int zy_py_num = 0;

		if (zyList != null && zyList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// 是否拼音
				boolean py_flag = true;
				// 是否结束
				boolean over_flag = false;
				// 是否“其他”
				boolean qt_flag = true;
				// “其他”数
				int qt_num = 0;
				// 其他ID
				String qi_id = "zy_qt_" + PY_BIG[i];
				
				for (int j = 0; j < zyList.size(); j++) {
					
					HashMap<String, String> map = zyList.get(j);
					
					// 专业代码
					String zydm = map.get("zydm");
					// 专业名称
					String zymc = map.get("zymc");
					// 专业拼音
					String zypy = map.get("zypy");

					if (PY_BIG[i].equalsIgnoreCase(zypy)) {

						if (xy != null && xy.length > 0) {// 选中学院

							if (zy_num < 12) {//专业数未超过12个
								
								if (py_flag) {

									html.append("<li>");

									html.append("<h5 id=\"zy_py_xs_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\"" + stylePath
											+ "images/num_" + PY_BIG[i]
											+ ".gif\" /></h5>");
									// divHtml += PY_BIG[i];
									html.append("</h5>");

									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								} 
								
								html.append(" <a href=\"#\" class=\"\" name=\"a_zy_mc\" ");
								html.append(" id=\"zy_mc_xs_");
								html.append(zydm);
								html.append("\"");
								html.append("onclick=\"clickZy(this);");
								html.append("creatClickedTj('zy','专业','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
								
							} else {//专业数超过12个
								
								if(py_flag){
									html.append("<li>");
												
									html.append("<h5 style=\"display:none\" id=\"zy_py_yc_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
									html.append("</h5>");
									
									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								}					
								
								html.append(" <a href=\"#\" class=\"\" name=\"a_zy_mc\" ");
								html.append(" id=\"zy_mc_yc_");
								html.append(zydm);
								html.append("\" style=\"display:none;\"");
								html.append("onclick=\"clickZy(this);");
								html.append("creatClickedTj('zy','专业','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
							}

						} else {// 未选中学院
							if(zy_num < 12){//专业数未超过12个
								
								if (py_flag) {

									html.append("<li>");

									html.append("<h5 id=\"zy_py_xs_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\"" + stylePath
											+ "images/num_" + PY_BIG[i]
											+ ".gif\" /></h5>");
									// divHtml += PY_BIG[i];
									html.append("</h5>");

									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								} 
								
								if(qt_flag){
									
									html.append(" <a href=\"#\" class=\"\" name=\"a_zy_mc\" ");
									html.append(" id=\"zy_mc_xs_");
									html.append(zydm);
									html.append("\"");
									html.append("onclick=\"clickZy(this);");
									html.append("creatClickedTj('zy','专业','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
									
								}
								
								// 其他
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickZyQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">更多</a>");
									
									qt_flag = false;
									qt_num ++;
								
								}
							}else{//专业超过12个
								
								if(py_flag){
									
									html.append("<li>");
												
									html.append("<h5 style=\"display:none\" id=\"zy_py_yc_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
									html.append("</h5>");
									
									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								}					
								
								if(qt_flag){
									html.append(" <a href=\"#\" class=\"\" name=\"a_zy_mc\" ");
									html.append(" id=\"zy_mc_yc_");
									html.append(zydm);
									html.append("\" style=\"display:none;\"");
									html.append("onclick=\"clickZy(this);");
									html.append("creatClickedTj('zy','专业','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
								}
								
								// 其他
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickZyQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">更多</a>");
									
									qt_flag = false;
									qt_num ++;
									
									html.append("<input type=\"hidden\" name=\"zy_hidv_qt\"");
									html.append("id=\"zy_hidv_qt_");
									html.append(zydm);
									html.append("\" value=\"");
									html.append(PY_BIG[i]);
									html.append("\"/>");
								
								}
							}
						}
						
						if(qt_flag){
							zy_num++;
						}
					}
				}
				
				if(over_flag){
					html.append("</li>");
					over_flag = false;
				}	
			}

		}

		html.append("<input type=\"hidden\" id=\"zy_py_num\" value=\""+zy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"zy_num\" value=\""+zy_num+"\"/> ");

		return html.toString();
	}
	
	/**
	 * 获得班级过滤条件（专业班级）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	private String creatBjHtml(String[] nj, String[] xy,String[] zy,
			List<HashMap<String, String>> bjList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// 班级数目
		int bj_num = 0;
		// 班级拼音数目
		int bj_py_num = 0;

		if (bjList != null && bjList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// 是否拼音
				boolean py_flag = true;
				// 是否结束
				boolean over_flag = false;
				// 是否“其他”
				boolean qt_flag = true;
				// “其他”数
				int qt_num = 0;
				// 其他ID
				String qi_id = "zybj_qt_" + PY_BIG[i];
				
				for (int j = 0; j < bjList.size(); j++) {
					
					HashMap<String, String> map = bjList.get(j);
					
					// 班级代码
					String bjdm = map.get("bjdm");
					// 班级名称
					String bjmc = map.get("bjmc");
					// 班级拼音
					String bjpy = map.get("bjpy");

					if (PY_BIG[i].equalsIgnoreCase(bjpy)) {

						if (bj_num < 12) {//班级数未超过12个
							
							if (py_flag) {

								html.append("<li>");

								html.append("<h5 id=\"zybj_py_xs_");
								html.append(bj_py_num);
								html.append("\">");
								html.append("<img src=\"" + stylePath + "images/num_" + PY_BIG[i] + ".gif\" /></h5>");
								html.append("</h5>");

								over_flag = true;
								py_flag = false;
								
								bj_py_num++;
							} 
							
							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_zybj_mc\" ");
								html.append(" id=\"zybj_mc_xs_");
								html.append(bjdm);
								html.append("\"");
								html.append("onclick=\"clickBj(this);");
								html.append("creatClickedTj('bj','班级','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");
								
								qt_flag = false;
								qt_num ++;
							
							}
							
						} else {//班级数超过12个
							
							if(py_flag){
								html.append("<li>");
											
								html.append("<h5 style=\"display:none\" id=\"zybj_py_yc_");
								html.append(bj_py_num);
								html.append("\">");
								html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
								html.append("</h5>");
								
								over_flag = true;
								py_flag = false;
								
								bj_py_num++;
							}					
							
							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_zybj_mc\" ");
								html.append(" id=\"zybj_mc_yc_");
								html.append(bjdm);
								html.append("\" style=\"display:none;\"");
								html.append("onclick=\"clickBj(this);");
								html.append("creatClickedTj('bj','班级','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");
								
								qt_flag = false;
								qt_num ++;
								
								html.append("<input type=\"hidden\" name=\"zybj_hidv_qt\"");
								html.append("id=\"zybj_hidv_qt_");
								html.append(bjdm);
								html.append("\" value=\"");
								html.append(PY_BIG[i]);
								html.append("\"/>");
							
							}
						}
					}
				}
				
				if(over_flag){
					html.append("</li>");
					over_flag = false;
				}	
			}

		}

		html.append("<input type=\"hidden\" id=\"zybj_py_num\" value=\""+bj_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"zybj_num\" value=\""+bj_num+"\"/> ");
		//System.out.println(html.toString());
		return html.toString();
	
	}

	/**
	 * 获得班级过滤条件（行政班级）
	 * @param sy
	 * @param bjList
	 * @param stylePath
	 * @return
	 */
	private String creatXzbjHtml(String[] sy,
							   List<HashMap<String, String>> bjList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// 班级数目
		int bj_num = 0;
		// 班级拼音数目
		int bj_py_num = 0;

		if (bjList != null && bjList.size() > 0) {

			for (int i = 0; i < PY_BIG.length; i++) {

				// 是否拼音
				boolean py_flag = true;
				// 是否结束
				boolean over_flag = false;
				// 是否“其他”
				boolean qt_flag = true;
				// “其他”数
				int qt_num = 0;
				// 其他ID
				String qi_id = "bj_qt_" + PY_BIG[i];

				for (int j = 0; j < bjList.size(); j++) {

					HashMap<String, String> map = bjList.get(j);

					// 班级代码
					String bjdm = map.get("bjdm");
					// 班级名称
					String bjmc = map.get("bjmc");
					// 班级拼音
					String bjpy = map.get("bjpy");

					if (PY_BIG[i].equalsIgnoreCase(bjpy)) {

						if (bj_num < 12) {//班级数未超过12个

							if (py_flag) {

								html.append("<li>");

								html.append("<h5 id=\"bj_py_xs_");
								html.append(bj_py_num);
								html.append("\">");
								html.append("<img src=\"" + stylePath + "images/num_" + PY_BIG[i] + ".gif\" /></h5>");
								html.append("</h5>");

								over_flag = true;
								py_flag = false;

								bj_py_num++;
							}

							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_bj_mc\" ");
								html.append(" id=\"bj_mc_xs_");
								html.append(bjdm);
								html.append("\"");
								html.append("onclick=\"clickBj(this);");
								html.append("creatClickedTj('bj','班级','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");

								bj_num++;
								qt_num++;
							}

							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");

								qt_flag = false;
								qt_num ++;

							}

						} else {//班级数超过12个

							if(py_flag){
								html.append("<li>");

								html.append("<h5 style=\"display:none\" id=\"bj_py_yc_");
								html.append(bj_py_num);
								html.append("\">");
								html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
								html.append("</h5>");

								over_flag = true;
								py_flag = false;

								bj_py_num++;
							}

							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_bj_mc\" ");
								html.append(" id=\"bj_mc_yc_");
								html.append(bjdm);
								html.append("\" style=\"display:none;\"");
								html.append("onclick=\"clickBj(this);");
								html.append("creatClickedTj('bj','班级','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");

								bj_num++;
								qt_num++;
							}

							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");

								qt_flag = false;
								qt_num ++;

								html.append("<input type=\"hidden\" name=\"bj_hidv_qt\"");
								html.append("id=\"bj_hidv_qt_");
								html.append(bjdm);
								html.append("\" value=\"");
								html.append(PY_BIG[i]);
								html.append("\"/>");

							}
						}
					}
				}

				if(over_flag){
					html.append("</li>");
					over_flag = false;
				}
			}

		}

		html.append("<input type=\"hidden\" id=\"bj_py_num\" value=\""+bj_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"bj_num\" value=\""+bj_num+"\"/> ");
		//System.out.println(html.toString());
		return html.toString();

	}

	/**
	 * 创建联动Div（楼栋，层号，寝室号）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void creatGyHtml(String[] xq, String[] yq, String[] ld, String[] ch,
			String bmlx, String stylePath, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 用户身份
		String userStatus = user.getUserStatus();
		// 用户名
		String userName = user.getUserName();
		// 用户所在部门
		String userDep = user.getUserDep();
		
		StringBuilder html = new StringBuilder();
		if ("yqdm".equalsIgnoreCase(bmlx)){
			List<HashMap<String, Object>> yqList = getYqdmList(xq, user);
			html.append(creatYqHtml(yqList));
		} else if ("ld".equalsIgnoreCase(bmlx)){
			List<HashMap<String, Object>> ldList = getLdList(xq, yq, user);
			html.append(creatLdHtml(ldList));
		} else if ("ch".equalsIgnoreCase(bmlx)) {// 联动层号
			List<HashMap<String, Object>> chList = getChList(xq, yq, ld);
			html.append(creatChHtml(chList));
		} else if ("qsh".equalsIgnoreCase(bmlx)) {// 联动寝室号
			List<HashMap<String, Object>> qshList = getQsList(xq, yq, ld, ch);
			html.append(creatQshHtml(qshList, stylePath));
		}
		//联动年级
		else if("nj".equalsIgnoreCase(bmlx)){
			List<HashMap<String, String>> njList = getNewNjListByYq(userStatus, userName, userDep, xq, yq);
			html.append(createNjNewHtmlByYq(njList, stylePath));
			
		}
		//联动学院
		else if("xy".equalsIgnoreCase(bmlx)){ 
			List<HashMap<String, String>> xyList = getNewXyListByYq(userStatus, userName, userDep, xq, yq);
			html.append(createXyNewHtmlByYq(xyList, stylePath));
		}

		response.getWriter().print(html.toString());
	}
	
	/** 
	 * @描述:浙江旅游---计分项目过滤
	 * @作者：cp[工号：1352]
	 * @日期：2017-1-4 下午04:05:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jfxmList
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public void creatJfxmHtml(String[] xmmkdm, String bmlx,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=gbk");
		StringBuilder html = new StringBuilder();
		if ("jfxmdm".equalsIgnoreCase(bmlx)){
			List<HashMap<String, Object>> jfxmList = getJfxmList(xmmkdm);
			html.append(creatJfxmHtml(jfxmList));
		} 

		response.getWriter().print(html.toString());
	}
	/**
	 * 	获得园区过滤条件
	 */
	private String creatYqHtml(List<HashMap<String, Object>> yqList) {
		
		StringBuilder html = new StringBuilder();
		
		//园区数量
		int yq_num = 0;
				
		if (yqList != null && yqList.size() > 0) {
						
			for (int j = 0; j < yqList.size(); j++) {
				HashMap<String, Object> map = yqList.get(j);
				
				// 园区代码
				String yqdm = (String) map.get("dm");
				// 园区名称
				String yqmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"yqdm_mc_xs_" + yqdm + "\" ");
				}else{
					html.append("id=\"yqdm_mc_yc_" + yqdm + "\" ");
				}
					
				html.append("name=\"tj_yqdm\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickYq(this);");
				html.append("creatClickedTj('yqdm','园区','" + yqdm + "','" + yqmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(yqmc);
				html.append("</a>");
				
				
				html.append("</li>");
				yq_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"yqdm_num\" value=\""+yq_num+"\"/> ");
		return html.toString();
	}
	
	/**
	 * 	获得楼栋过滤条件
	 */
	private String creatLdHtml(List<HashMap<String, Object>> ldList) {
		
		StringBuilder html = new StringBuilder();
		
		//楼栋数量
		int ld_num = 0;
				
		if (ldList != null && ldList.size() > 0) {
						
			for (int j = 0; j < ldList.size(); j++) {
				HashMap<String, Object> map = ldList.get(j);
				
				// 楼栋代码
				String lddm = (String) map.get("dm");
				// 楼栋名称
				String ldmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"ld_mc_xs_" + lddm + "\" ");
				}else{
					html.append("id=\"ld_mc_yc_" + lddm + "\" ");
				}
					
				html.append("name=\"tj_ld\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickLd(this);");
				html.append("creatClickedTj('ld','楼栋','" + lddm + "','" + ldmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(ldmc);
				html.append("</a>");
				
				
				html.append("</li>");
				ld_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"ld_num\" value=\""+ld_num+"\"/> ");
		return html.toString();
	}
	
	
	/** 
	 * @描述:html生成计分项目信息
	 * @作者：cp[工号：1352]
	 * @日期：2017-1-4 下午04:05:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jfxmList
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	private Object creatJfxmHtml(List<HashMap<String, Object>> jfxmList) {
		StringBuilder html = new StringBuilder();
		if (jfxmList != null && jfxmList.size() > 0) {
			for (int j = 0; j < jfxmList.size(); j++) {
				HashMap<String, Object> map = jfxmList.get(j);
				String chdm = (String) map.get("dm");
				String chmc = (String) map.get("mc");
				html.append("<li>");
				html.append(" <a href=\"#\" class=\"\" name=\"tj_jfxmdm\" ");
				html.append(" id=\"tj_jfxmdm_");
				html.append(chdm);
				html.append("\"");
				html.append("onclick=\"clickTj('jfxmdm','"+chdm+"');");
				html.append("creatClickedTj('jfxmdm','计分项目','" + chdm + "','" + chmc + "',this);return false;\">");
				html.append(chmc);
				html.append("</a>");
				html.append("</li>");
			}
		}
		
		return html.toString();
	}
	
	/**
	 * 获得层号过滤条件
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	private String creatChHtml(List<HashMap<String, Object>> chList) {
		
		StringBuilder html = new StringBuilder();

		// 层号数目
		int ch_num = 0;

		if (chList != null && chList.size() > 0) {
			
			for (int j = 0; j < chList.size(); j++) {
				
				HashMap<String, Object> map = chList.get(j);
				
				// 层号代码
				String chdm = (String) map.get("dm");
				// 层号名称
				String chmc = (String) map.get("mc");

				html.append("<li>");
					
				html.append(" <a href=\"#\" class=\"\" name=\"tj_ch\" ");
				html.append(" id=\"tj_ch_");
				html.append(chdm);
				html.append("\"");
				html.append("onclick=\"clickCs(this);");
				html.append("creatClickedTj('ch','层号','" + chdm + "','" + chmc + "',this);return false;\">");
				html.append(chmc);
				html.append("</a>");
				
				html.append("</li>");
			}
		}
		
		return html.toString();
	}
	
	/**
	 * 获得寝室过滤条件
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String creatQshHtml(
			List<HashMap<String, Object>> qsList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// 寝室数目
		int qsh_num = 0;
		// 寝室拼音数目
		int qsh_py_num = 0;

		if (qsList != null && qsList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// 是否拼音
				boolean py_flag = true;
				// 是否结束
				boolean over_flag = false;
				// 是否“其他”
				boolean qt_flag = true;
				// “其他”数
				int qt_num = 0;
				// 其他ID
				String qi_id = "qsh_qt_" + PY_BIG[i];
				
				HashMap<String, Object> map = qsList.get(i);
				
				// 寝室号拼音
				String qshpy = (String) map.get("py");

				List<HashMap<String, String>> qshList = (List<HashMap<String, String>>) map
						.get("qsList");
				
				if (qshList != null && qshList.size() > 0) {
					
					for (int j = 0; j < qshList.size(); j++) {

						HashMap<String, String> qsMap = qshList.get(j);

						// 寝室号代码
						String qshdm = qsMap.get("qshdm");
						// 寝室号名称
						String qshmc = qsMap.get("qshmc");

						if (PY_BIG[i].equalsIgnoreCase(qshpy)) {

							if (qsh_num < 12) {// 寝室数未超过12个

								if (py_flag) {

									html.append("<li>");

									html.append("<h5 id=\"qsh_py_xs_");
									html.append(qsh_py_num);
									html.append("\">"); 
									html.append("<img src=\"" + stylePath
											+ "images/num_" + PY_BIG[i]
											+ ".gif\" /></h5>");
									html.append("</h5>");

									over_flag = true;
									py_flag = false;

									qsh_py_num++;
								}

								if (qt_flag) {
									html
											.append(" <a href=\"#\" class=\"\" name=\"a_qsh_mc\" ");
									html.append(" id=\"qsh_mc_xs_");
									html.append(qshdm);
									html.append("\"");
									html.append("onclick=\"clickQsh(this);");
									html.append("creatClickedTj('qsh','寝室号','"
											+ qshdm + "','" + qshmc
											+ "',this);return false;\">");
									html.append(qshmc);
									html.append("</a>");

									qsh_num++;
									qt_num++;
								}

								// 其他
								if (qt_num == 3) {

									html
											.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickQshQt(\'");
									html.append(qshpy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">更多</a>");

									qt_flag = false;
									qt_num++;

								}

							} else {// 寝室数超过12个

								if (py_flag) {
									html.append("<li>");

									html
											.append("<h5 style=\"display:none\" id=\"qsh_py_yc_");
									html.append(qsh_py_num);
									html.append("\">");
									html.append("<img src=\"" + stylePath
											+ "images/num_" + PY_BIG[i]
											+ ".gif\" /></h5>");
									html.append("</h5>");

									over_flag = true;
									py_flag = false;

									qsh_py_num++;
								}

								if (qt_flag) {
									html
											.append(" <a href=\"#\" class=\"\" name=\"a_qsh_mc\" ");
									html.append(" id=\"qsh_mc_yc_");
									html.append(qshdm);
									html.append("\" style=\"display:none;\"");
									html.append("onclick=\"clickQsh(this);");
									html.append("creatClickedTj('qsh','寝室号','"
											+ qshdm + "','" + qshmc
											+ "',this);return false;\">");
									html.append(qshmc);
									html.append("</a>");

									qsh_num++;
									qt_num++;
								}

								// 其他
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickQshQt(\'");
									html.append(qshpy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">更多</a>");

									qt_flag = false;
									qt_num++;

									html.append("<input type=\"hidden\" name=\"qsh_hidv_qt\"");
									html.append("id=\"qsh_hidv_qt_");
									html.append(qshdm);
									html.append("\" value=\"");
									html.append(PY_BIG[i]);
									html.append("\"/>");

								}
							}
						}
					}
					if (over_flag) {
						html.append("</li>");
						over_flag = false;
					}
				}
			}
		}

		html.append("<input type=\"hidden\" id=\"qsh_py_num\" value=\""+qsh_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"qsh_num\" value=\""+qsh_num+"\"/> ");
		//System.out.println(html.toString());
		return html.toString();
	
	}
	
	/**
	 * 创建高级查询Div（勤工部门）
	 * @author yeyipin
	 */
	private String createQgbmHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("qgbm_ul") ? "" : " id=\"qgbm_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_qgbm\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickQgbm(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"qgbm_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/********************************************************************************
	 * ********************************军训建制联动html建设***************************
	 * ******************************************************************************
	 */
	/**
	 * 创建高级查询Div（团）
	 * @author 易江东
	 */
	private String createTidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("tid_ul") ? "" : " id=\"tid_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_tid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickTid(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"tid_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 创建高级查询Div（营）
	 * @author 易江东
	 */
	private String createYidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("yid_ul") ? "" : " id=\"yid_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_yid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickYid(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"yid_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 创建高级查询Div（连）
	 * @author 易江东
	 */
	private String createLidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("lid_ul") ? "" : " id=\"lid_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_lid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickLid(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"lid_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 创建高级查询Div（排）
	 * @author 易江东
	 */
	private String createPidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("pid_ul") ? "" : " id=\"pid_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_pid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickPid(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"pid_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	
	private String createBidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("bid_ul") ? "" : " id=\"bid_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_bid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickBid(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"bid_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	private String createSsidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("ssid_ul") ? "" : " id=\"ssid_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_ssid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickSsid(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"ssid_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	
	
	
	/**
	 * 	获得团id过滤条件
	 */
	private String createTidHtml(List<HashMap<String, Object>> tidList) {
		
		StringBuilder html = new StringBuilder();
		
		//团数量
		int tid_num = 0;
				
		if (tidList != null && tidList.size() > 0) {
						
			for (int j = 0; j < tidList.size(); j++) {
				HashMap<String, Object> map = tidList.get(j);
				
				// 团id
				String tid = (String) map.get("dm");
				// 团名称
				String tmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"tid_mc_xs_" + tid + "\" ");
				}else{
					html.append("id=\"tid_mc_yc_" + tid + "\" ");
				}
					
				html.append("name=\"tj_tid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickTid(this);");
				html.append("creatClickedTj('tid','团','" + tid + "','" + tmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tmc);
				html.append("</a>");
				
				
				html.append("</li>");
				tid_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"tid_num\" value=\""+tid_num+"\"/> ");
		return html.toString();
	}
	
	/**
	 * 	获得营id过滤条件
	 */
	private String createYidHtml(List<HashMap<String, Object>> yidList) {
		
		StringBuilder html = new StringBuilder();
		
		//营数量
		int yid_num = 0;
				
		if (yidList != null && yidList.size() > 0) {
						
			for (int j = 0; j < yidList.size(); j++) {
				HashMap<String, Object> map = yidList.get(j);
				
				// 营id
				String yid = (String) map.get("dm");
				// 营名称
				String ymc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"yid_mc_xs_" + yid + "\" ");
				}else{
					html.append("id=\"yid_mc_yc_" + yid + "\" ");
				}
					
				html.append("name=\"tj_yid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickYid(this);");
				html.append("creatClickedTj('yid','营','" + yid + "','" + ymc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(ymc);
				html.append("</a>");
				
				
				html.append("</li>");
				yid_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"yid_num\" value=\""+yid_num+"\"/> ");
		return html.toString();
	}
	
	/**
	 * 	获得连id过滤条件
	 */
	private String createLidHtml(List<HashMap<String, Object>> lidList) {
		
		StringBuilder html = new StringBuilder();
		
		//连数量
		int lid_num = 0;
				
		if (lidList != null && lidList.size() > 0) {
						
			for (int j = 0; j < lidList.size(); j++) {
				HashMap<String, Object> map = lidList.get(j);
				
				// 连id
				String lid = (String) map.get("dm");
				// 连名称
				String lmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"lid_mc_xs_" + lid + "\" ");
				}else{
					html.append("id=\"lid_mc_yc_" + lid + "\" ");
				}
					
				html.append("name=\"tj_lid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickLid(this);");
				html.append("creatClickedTj('lid','连','" + lid + "','" + lmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(lmc);
				html.append("</a>");
				
				
				html.append("</li>");
				lid_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"lid_num\" value=\""+lid_num+"\"/> ");
		return html.toString();
	}
	
	/**
	 * 	获得排id过滤条件
	 */
	private String createPidHtml(List<HashMap<String, Object>> pidList) {
		
		StringBuilder html = new StringBuilder();
		
		//排数量
		int tid_num = 0;
				
		if (pidList != null && pidList.size() > 0) {
						
			for (int j = 0; j < pidList.size(); j++) {
				HashMap<String, Object> map = pidList.get(j);
				
				// 排id
				String pid = (String) map.get("dm");
				// 排名称
				String pmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"pid_mc_xs_" + pid + "\" ");
				}else{
					html.append("id=\"pid_mc_yc_" + pid + "\" ");
				}
					
				html.append("name=\"tj_pid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickPid(this);");
				html.append("creatClickedTj('pid','排','" + pid + "','" + pmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(pmc);
				html.append("</a>");
				
				
				html.append("</li>");
				tid_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"tid_num\" value=\""+tid_num+"\"/> ");
		return html.toString();
	}
	
	
	private String createBidHtml(List<HashMap<String, Object>> bidList) {
		
		StringBuilder html = new StringBuilder();
		
		//班数量
		int bid_num = 0;
				
		if (bidList != null && bidList.size() > 0) {
						
			for (int j = 0; j < bidList.size(); j++) {
				HashMap<String, Object> map = bidList.get(j);
				
				// 排id
				String bid = (String) map.get("dm");
				// 排名称
				String bmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"bid_mc_xs_" + bid + "\" ");
				}else{
					html.append("id=\"bid_mc_yc_" + bid + "\" ");
				}
					
				html.append("name=\"tj_bid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickBid(this);");
				html.append("creatClickedTj('bid','班','" + bid + "','" + bmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(bmc);
				html.append("</a>");
				
				
				html.append("</li>");
				bid_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"bid_num\" value=\""+bid_num+"\"/> ");
		return html.toString();
	}
	
	
	private String createSsidHtml(List<HashMap<String, Object>> ssidList) {
		
		StringBuilder html = new StringBuilder();
		
		//班数量
		int ssid_num = 0;
				
		if (ssidList != null && ssidList.size() > 0) {
						
			for (int j = 0; j < ssidList.size(); j++) {
				HashMap<String, Object> map = ssidList.get(j);
				
				// 排id
				String ssid = (String) map.get("dm");
				// 排名称
				String ssmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"ssid_mc_xs_" + ssid + "\" ");
				}else{
					html.append("id=\"ssid_mc_yc_" + ssid + "\" ");
				}
					
				html.append("name=\"tj_ssid\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"clickSsid(this);");
				html.append("creatClickedTj('ssid','宿舍','" + ssid + "','" + ssmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(ssmc);
				html.append("</a>");
				
				
				html.append("</li>");
				ssid_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"ssid_num\" value=\""+ssid_num+"\"/> ");
		return html.toString();
	}
	
	
	/**
	 * 创建联动Div（团、营、连、排）
	 * 
	 * @author 易江东
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void createJxjzHtml(String[] tid, String[] yid, String[] lid, String[] pid,String[] bid,String[] ssid,
			String ldlx, String stylePath, User user, String jxid,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		List<String[]> list=new ArrayList<String[]>();
		list.add(tid);
		list.add(yid);
		list.add(lid);
		list.add(pid);
		list.add(bid);
		list.add(ssid);
		
		StringBuilder html = new StringBuilder();
		if ("tid".equalsIgnoreCase(ldlx)){//点击团联动
			List<HashMap<String, Object>> tidList = getTidList(list, jxid);
			html.append(createTidHtml(tidList));
		} else if ("yid".equalsIgnoreCase(ldlx)){//点击营联动
			List<HashMap<String, Object>> yidList = getYidList(list, jxid);
			html.append(createYidHtml(yidList));
		} else if ("lid".equalsIgnoreCase(ldlx)) {// 点击连联动
			List<HashMap<String, Object>> lidList = getLidList(list, jxid);
			html.append(createLidHtml(lidList));
		} else if ("pid".equalsIgnoreCase(ldlx)) {// 点击排联动
			List<HashMap<String, Object>> pidList = getPidList(list, jxid);
			html.append(createPidHtml(pidList));
		} else if ("bid".equalsIgnoreCase(ldlx)) {// 点击班联动
			List<HashMap<String, Object>> bidList = getBidList(list, jxid);
			html.append(createBidHtml(bidList));
		} else if ("ssid".equalsIgnoreCase(ldlx)) {// 点击宿舍联动
			List<HashMap<String, Object>> ssidList = getSsidList(list, jxid);
			html.append(createSsidHtml(ssidList));
		}

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 	
	 * @param tj
	 * @param ldlx
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void createWjHtml(String[] tj, String ldlx,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=gbk");
				
		StringBuilder html = new StringBuilder();
		if ("gyjllbdm".equalsIgnoreCase(ldlx)){//点击团联动
			List<HashMap<String, Object>> gyjllbdmList = getGyjllbdmList(tj);
			html.append(createTylddmHtml(gyjllbdmList,"gyjllbdm","纪律类别","clickGyjllbdm"));
		}

		response.getWriter().print(html.toString());
	}
	/**
	 * 省市区县 三级联动
	 */
	public void createQxHtml(List<String> tj, String jb,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=gbk");
		String mc ="";
		if(jb!=null && "shi".equals(jb)){
			mc ="市";
		}else{
			mc ="区县";
		}		
		StringBuilder html = new StringBuilder();
		List<HashMap<String, Object>> qxList = getQxList(tj,jb);
		html.append(createQxHtml(qxList,jb,mc,"click"+jb.toUpperCase()));
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 创建高级查询Div（通用）
	 * @author zhanghui
	 */
	private String createTylddmHtml(HashMap<String, Object> tjMap,String dm,String function) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull(dm+"_ul") ? "" : " id=\""+dm+"_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_"+dm+"\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append(function+"(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\""+dm+"_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 创建高级查询通用联动代码
	 * @author zhanghui
	 * @param tjMap
	 * @param dm		联动代码
	 * @param function	click操作方法名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createTylddmHtml(List<HashMap<String, Object>> tjMap,String dm,String mc,String function) {
		
		StringBuilder html = new StringBuilder();
		
		//数量
		int dm_num = 0;
				
		if (tjMap != null && tjMap.size() > 0) {
						
			for (int j = 0; j < tjMap.size(); j++) {
				HashMap<String, Object> map = tjMap.get(j);
				
				// 联动代码
				String lddm = (String) map.get("dm");
				// 联动名称
				String ldmc = (String) map.get("mc");

				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\""+dm+"_mc_xs_" + lddm + "\" ");
				}else{
					html.append("id=\""+dm+"_mc_yc_" + lddm + "\" ");
				}
					
				html.append("name=\"tj_id\"");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append(function+"(this);");
				html.append("creatClickedTj('" + dm + "','" + mc + "','" + lddm + "','" + ldmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(ldmc);
				html.append("</a>");
				
				
				html.append("</li>");
				dm_num++;
			}
		}

		html.append("<input type=\"hidden\" id=\"ld_num\" value=\""+dm_num+"\"/> ");
		return html.toString();
	}
	/**
	 * @描述:省县市三级联动
	 * @作者：zhangjw
	 * @日期：2013-5-21 下午05:04:57
	 * @修改记录: 
	 * @param tjMap
	 * @param dm
	 * @param mc
	 * @param function
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	/*public String createQxHtml(List<HashMap<String, Object>> tjMap,String dm,String mc,String function) {
			
			StringBuilder html = new StringBuilder();
			
			//数量
			int dm_num = 0;
					
			if (tjMap != null && tjMap.size() > 0) {
				
				for (int j = 0; j < tjMap.size(); j++) {
					HashMap<String, Object> map = tjMap.get(j);
					
					// 联动代码
					String lddm = (String) map.get("dm");
					// 联动名称
					String ldmc = (String) map.get("mc");
	
					html.append("<li>");
					
					// 是否显示
					String display = (j < 8) ? "" : "display: none;";
					
					html.append("<a ");
					
					html.append("style=\"" + display + "\" ");
					if(Base.isNull(display)){
						html.append("id=\""+dm+"_mc_xs_" + lddm + "\" ");
					}else{
						html.append("id=\""+dm+"_mc_yc_" + lddm + "\" ");
					}
						
					html.append("name=\"tj_"+dm+"\"");
					html.append("href=\"#\" ");
					html.append("class=\"\" ");
					html.append("onclick=\"");
					html.append(function+"(this);");
					html.append("creatClickedTj('" + dm + "','" + mc + "','" + lddm + "','" + ldmc + "',this);");
					html.append("return false;\" ");
					html.append(">");
					html.append(ldmc);
					html.append("</a>");
					
					
					html.append("</li>");
					dm_num++;
				}
			}
	
			html.append("<input type=\"hidden\" id=\"ld_num\" value=\""+dm_num+"\"/> ");
			return html.toString();
		}*/
	private String createQxHtml(List<HashMap<String, Object>> tjLit,String dm,String mc,String function) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = dm;
		
	
		html.append("<ul");
		html.append(Base.isNull(dm+"_ul") ? "" : " id=\""+dm+"_ul\"");
		html.append(">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				
				// 联动代码
				String lddm = (String) map.get("dm");
				// 联动名称
				String ldmc = (String) map.get("mc");
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\""+dm+"_mc_xs_" + lddm + "\" ");
				}else{
					html.append("id=\""+dm+"_mc_yc_" + lddm + "\" ");
				}
					
				html.append("name=\"tj_"+dm+"\"");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append(function+"(this);");
				html.append("creatClickedTj('" + dm + "','" + mc + "','" + lddm + "','" + ldmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(ldmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\""+dm+"_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}

		
		return html.toString();
	}
	


	/**
	 * 
	 * @描述: 创建高级查询Div（年级条件）【全】
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午04:23:05
	 * @param tjMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createNjNewHtml(HashMap<String, Object> tjMap) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("nj");
				// 条件名称
				String tjmc = (String) map.get("nj");
				
				html.append("<li>");
				html.append("<a ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("id=\"tj_" + tj + "_" + tjdm + "\" ");
				html.append("name=\"tj_" + tj + "\" ");
				html.append("onclick=\"");
				html.append("clickNjNew(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
private String createXsNjHtml(HashMap<String, Object> tjMap) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("nj");
				// 条件名称
				String tjmc = (String) map.get("nj");
				
				html.append("<li>");
				html.append("<a ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("id=\"tj_" + tj + "_" + tjdm + "\" ");
				html.append("name=\"tj_" + tj + "\" ");
				html.append("onclick=\"");
				html.append("clickXsNj(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	
	/**
	 * 
	 * @描述:根据园区重新加载学院高级查询条件
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-12 下午04:58:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjMap
	 * @param stylePath
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	
	private String createXyNewHtmlByYq(List<HashMap<String, String>> xyList, String stylePath) {
		

		StringBuilder html = new StringBuilder();
	
		// 学院数目
		int xy_num = 0;
		// 学院拼音数目
		int xy_py_num = 0;
		
		if (xyList != null && xyList.size() > 0) {	
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// 是否拼音
				boolean py_flag = true;
				// 是否结束
			for (int j = 0; j < xyList.size(); j++) {
				HashMap<String, String> map = xyList.get(j);
				// 拼音
				String py = (String) map.get("xypy");
				if (PY_BIG[i].equalsIgnoreCase(py)) {
				if (py_flag) {
					html.append("<li>");
					html.append("<h5 ");
					if (xy_num >= 8) {
						html.append("id=\"xy_py_yc_" + xy_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"xy_py_xs_" + xy_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					py_flag = false;
					xy_py_num++;
				}
						HashMap<String, String> xyMap = xyList.get(j);
						// 学院代码
						String xydm = xyMap.get("xydm");
						// 学院名称
						String xymc = xyMap.get("xymc");
						
						html.append("<a ");
						html.append("href=\"#\" "); 
						html.append("class=\"\" "); 
						if (xy_num >= 8) {
							html.append("style=\"display: none\" ");
							html.append("id=\"xy_mc_yc_" + xydm + "\" ");
						} else {
							html.append("id=\"xy_mc_xs_" + xydm + "\" ");
						}
						html.append("name=\"a_xy_mc\" ");
						html.append("onclick=\"");
						html.append("clickXy(this);");
						html.append("creatClickedTj('xy','学院','"+xydm+"','"+xymc+"',this);");
						html.append("return false;\" "); 
						html.append("title=\""+xymc+"\" ");
						html.append("> ");
						html.append(xymc);
						html.append("</a> ");
						
						if (xy_num >= 8) {
							html.append("<input type=\"hidden\" ");
							html.append("name=\"xy_hidv_yc\" ");
							html.append("id=\"xy_hidv_yc_" + xydm + "\" ");
							html.append("value=\"" + xydm + "\" ");
							html.append("/> ");
						}
						
						xy_num++;
						
					}
				
			}
			html.append("</li>");	
				}
			
		html.append("<input type=\"hidden\" id=\"xy_py_num\" value=\""+xy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xy_num\" value=\""+xy_num+"\"/> ");
		
		html.append("</dd>");
		html.append("</dl>");
		}
		return html.toString();
	}
	/**
	 * 
	 * @描述:根据园区重新加载年级高级查询条件
	 * @作者：cq [工号：785]
	 * @日期：2014-11-13 下午04:26:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createNjNewHtmlByYq(List<HashMap<String, String>> njList,String stylePath) {
		

		StringBuilder html = new StringBuilder();
	
		
		if (njList != null && njList.size() > 0) {
			for (int j = 0; j < njList.size(); j++) {
				HashMap<String, String> map = njList.get(j);
				// 条件代码
				String tjdm = (String) map.get("nj");
				// 条件名称
				String tjmc = (String) map.get("nj");
				
				html.append("<li>");
				html.append("<a ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("id=\"tj_" + "nj" + "_" + tjdm + "\" ");
				html.append("name=\"tj_" + "nj" + "\" ");
				html.append("onclick=\"");
				html.append("clickNj(this);");
				html.append("creatClickedTj('" + "nj" + "','" + "年级" + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	@SuppressWarnings("unchecked")
	private String createXyNewHtml(HashMap<String, Object> tjMap, String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 学院数目
		int xy_num = 0;
		// 学院拼音数目
		int xy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 学院列表
				List<HashMap<String, String>> xyList = (List<HashMap<String, String>>) map
						.get("xyList");

				if (xyList != null && xyList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (xy_num >= 8) {
						html.append("id=\"xyNew_py_yc_" + xy_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"xyNew_py_xs_" + xy_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					xy_py_num++;
					
					for (int k = 0; k < xyList.size(); k++) {
						
						HashMap<String, String> xyMap = xyList.get(k);
						// 学院代码
						String xydm = xyMap.get("xydm");
						// 学院名称
						String xymc = xyMap.get("xymc");
						
						html.append("<a ");
						html.append("href=\"#\" "); 
						html.append("class=\"\" "); 
						if (xy_num >= 8) {
							html.append("style=\"display: none\" ");
							html.append("id=\"xyNew_mc_yc_" + xydm + "\" ");
						} else {
							html.append("id=\"xyNew_mc_xs_" + xydm + "\" ");
						}
						html.append("name=\"a_xyNew_mc\" ");
						html.append("onclick=\"");
						html.append("clickXyNew(this);");
						html.append("creatClickedTj('"+tj+"','"+mc+"','"+xydm+"','"+xymc+"',this);");
						html.append("return false;\" "); 
						html.append("title=\""+xymc+"\" ");
						html.append("> ");
						html.append(xymc);
						html.append("</a> ");
						
						if (xy_num >= 8) {
							html.append("<input type=\"hidden\" ");
							html.append("name=\"xyNew_hidv_yc\" ");
							html.append("id=\"xyNew_hidv_yc_" + xydm + "\" ");
							html.append("value=\"" + xydm + "\" ");
							html.append("/> ");
						}
						
						xy_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"xyNew_py_num\" value=\""+xy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xyNew_num\" value=\""+xy_num+"\"/> ");
		
		html.append("</ul>");
		
		if (xy_num >= 8) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"xyNew_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	@SuppressWarnings("unchecked")
	private String createXsXyHtml(HashMap<String, Object> tjMap, String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 学院数目
		int xy_num = 0;
		// 学院拼音数目
		int xy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 学院列表
				List<HashMap<String, String>> xyList = (List<HashMap<String, String>>) map
						.get("xyList");

				if (xyList != null && xyList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (xy_num >= 8) {
						html.append("id=\"xsxy_py_yc_" + xy_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"xsxy_py_xs_" + xy_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					xy_py_num++;
					
					for (int k = 0; k < xyList.size(); k++) {
						
						HashMap<String, String> xyMap = xyList.get(k);
						// 学院代码
						String xydm = xyMap.get("xydm");
						// 学院名称
						String xymc = xyMap.get("xymc");
						
						html.append("<a ");
						html.append("href=\"#\" "); 
						html.append("class=\"\" "); 
						if (xy_num >= 8) {
							html.append("style=\"display: none\" ");
							html.append("id=\"xsxy_mc_yc_" + xydm + "\" ");
						} else {
							html.append("id=\"xsxy_mc_xs_" + xydm + "\" ");
						}
						html.append("name=\"a_xsxy_mc\" ");
						html.append("onclick=\"");
						html.append("clickXsXy(this);");
						html.append("creatClickedTj('"+tj+"','"+mc+"','"+xydm+"','"+xymc+"',this);");
						html.append("return false;\" "); 
						html.append("title=\""+xymc+"\" ");
						html.append("> ");
						html.append(xymc);
						html.append("</a> ");
						
						if (xy_num >= 8) {
							html.append("<input type=\"hidden\" ");
							html.append("name=\"xsxy_hidv_yc\" ");
							html.append("id=\"xsxy_hidv_yc_" + xydm + "\" ");
							html.append("value=\"" + xydm + "\" ");
							html.append("/> ");
						}
						
						xy_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"xsxy_py_num\" value=\""+xy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xsxy_num\" value=\""+xy_num+"\"/> ");
		
		html.append("</ul>");
		
		if (xy_num >= 8) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"xsxy_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	

	/**
	 * 
	 * @描述: 创建高级查询Div（专业条件）【全】
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午04:24:12
	 * @param tjMap
	 * @param stylePath
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createZyNewHtml(HashMap<String, Object> tjMap, String stylePath) {

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 专业数目
		int zy_num = 0;
		// 专业拼音数目
		int zy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 专业列表
				List<HashMap<String, String>> zyList = (List<HashMap<String, String>>) map
						.get("zyList");

				if (zyList != null && zyList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (zy_num >= 12) {
						html.append("id=\"zyNew_py_yc_" + zy_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"zyNew_py_xs_" + zy_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					zy_py_num++;
					
					for (int k = 0; k < zyList.size(); k++) {
						
						HashMap<String, String> zyMap = zyList.get(k);
						// 专业代码
						String zydm = zyMap.get("zydm");
						// 专业名称
						String zymc = zyMap.get("zymc");
						
						if(!"其他".equalsIgnoreCase(zymc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (zy_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"zyNew_mc_yc_" + zydm + "\" ");
							} else {
								html.append("id=\"zyNew_mc_xs_" + zydm + "\" ");
							}
							html.append("name=\"a_zyNew_mc\" ");
							html.append("onclick=\"");
							html.append("clickZyNew(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+zydm+"','"+zymc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+zymc+"\" ");
							html.append("> ");
							html.append(zymc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (zy_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"zyNew_qt_" + zydm + "\" ");
							} else {
								html.append("id=\"zyNew_hidv_qt_" + zydm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickZyNewQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (zy_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"zyNew_hidv_yc\" "); 
							html.append("id=\"zyNew_hidv_yc_"+zydm+"\" "); 
							html.append("value=\""+zydm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"zyNew_hidv_qt\" ");
							html.append("id=\"zyNew_hidv_qt_" + zydm + "\" ");
							html.append("value=\"" + zydm + "\"/> ");															
						}
						
						zy_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"zyNew_py_num\" value=\""+zy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"zyNew_num\" value=\""+zy_num+"\"/> ");
		
		html.append("</ul>");
		
		if (zy_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"zyNew_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	private String createXsZyHtml(HashMap<String, Object> tjMap, String stylePath) {

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 专业数目
		int zy_num = 0;
		// 专业拼音数目
		int zy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 专业列表
				List<HashMap<String, String>> zyList = (List<HashMap<String, String>>) map
						.get("zyList");

				if (zyList != null && zyList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (zy_num >= 12) {
						html.append("id=\"xszy_py_yc_" + zy_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"xszy_py_xs_" + zy_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					zy_py_num++;
					
					for (int k = 0; k < zyList.size(); k++) {
						
						HashMap<String, String> zyMap = zyList.get(k);
						// 专业代码
						String zydm = zyMap.get("zydm");
						// 专业名称
						String zymc = zyMap.get("zymc");
						
						if(!"其他".equalsIgnoreCase(zymc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (zy_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"xszy_mc_yc_" + zydm + "\" ");
							} else {
								html.append("id=\"xszy_mc_xs_" + zydm + "\" ");
							}
							html.append("name=\"a_xszy_mc\" ");
							html.append("onclick=\"");
							html.append("clickXsZy(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+zydm+"','"+zymc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+zymc+"\" ");
							html.append("> ");
							html.append(zymc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (zy_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"xszy_qt_" + zydm + "\" ");
							} else {
								html.append("id=\"xszy_hidv_qt_" + zydm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickXsZyQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (zy_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"xszy_hidv_yc\" "); 
							html.append("id=\"xszy_hidv_yc_"+zydm+"\" "); 
							html.append("value=\""+zydm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"xszy_hidv_qt\" ");
							html.append("id=\"xszy_hidv_qt_" + zydm + "\" ");
							html.append("value=\"" + zydm + "\"/> ");															
						}
						
						zy_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"xszy_py_num\" value=\""+zy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xszy_num\" value=\""+zy_num+"\"/> ");
		
		html.append("</ul>");
		
		if (zy_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"xszy_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * 
	 * @描述: 创建高级查询Div（班级条件）【全】
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午04:38:33
	 * @param tjMap
	 * @param stylePath
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createBjNewHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 班级数目
		int bj_num = 0;
		// 班级拼音数目
		int bj_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 班级列表
				List<HashMap<String, String>> bjList = (List<HashMap<String, String>>) map
						.get("bjList");

				if (bjList != null && bjList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (bj_num >= 12) {
						html.append("id=\"bjNew_py_yc_" + bj_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"bjNew_py_xs_" + bj_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					bj_py_num++;
					
					for (int k = 0; k < bjList.size(); k++) {
						
						HashMap<String, String> bjMap = bjList.get(k);
						// 班级代码
						String bjdm = bjMap.get("bjdm");
						// 班级名称
						String bjmc = bjMap.get("bjmc");
						
						if(!"其他".equalsIgnoreCase(bjmc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"bjNew_mc_yc_" + bjdm + "\" ");
							} else {
								html.append("id=\"bjNew_mc_xs_" + bjdm + "\" ");
							}
							html.append("name=\"a_bjNew_mc\" ");
							html.append("onclick=\"");
							html.append("clickBj(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+bjdm+"','"+bjmc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+bjmc+"\" ");
							html.append("> ");
							html.append(bjmc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"bjNew_qt_" + bjdm + "\" ");
							} else {
								html.append("id=\"bjNew_hidv_qt_" + bjdm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickBjNewQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (bj_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"bjNew_hidv_yc\" "); 
							html.append("id=\"bjNew_hidv_yc_"+bjdm+"\" "); 
							html.append("value=\""+bjdm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"bjNew_hidv_qt\" ");
							html.append("id=\"bjNew_hidv_qt_" + bjdm + "\" ");
							html.append("value=\"" + bjdm + "\"/> ");															
						}
						
						bj_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"bjNew_py_num\" value=\""+bj_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"bjNew_num\" value=\""+bj_num+"\"/> ");
		
		html.append("</ul>");
		
		if (bj_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"bjNew_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
private String createXsBjHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 班级数目
		int bj_num = 0;
		// 班级拼音数目
		int bj_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 班级列表
				List<HashMap<String, String>> bjList = (List<HashMap<String, String>>) map
						.get("bjList");

				if (bjList != null && bjList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (bj_num >= 12) {
						html.append("id=\"xsbj_py_yc_" + bj_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"xsbj_py_xs_" + bj_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					bj_py_num++;
					
					for (int k = 0; k < bjList.size(); k++) {
						
						HashMap<String, String> bjMap = bjList.get(k);
						// 班级代码
						String bjdm = bjMap.get("bjdm");
						// 班级名称
						String bjmc = bjMap.get("bjmc");
						
						if(!"其他".equalsIgnoreCase(bjmc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"xsbj_mc_yc_" + bjdm + "\" ");
							} else {
								html.append("id=\"xsbj_mc_xs_" + bjdm + "\" ");
							}
							html.append("name=\"a_xsbj_mc\" ");
							html.append("onclick=\"");
							html.append("clickBj(this);");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+bjdm+"','"+bjmc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+bjmc+"\" ");
							html.append("> ");
							html.append(bjmc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (bj_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"xsbj_qt_" + bjdm + "\" ");
							} else {
								html.append("id=\"xsbj_hidv_qt_" + bjdm + "\" ");
							}
							html.append("onclick=\"");
							html.append("clickXsBjQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (bj_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"xsbj_hidv_yc\" "); 
							html.append("id=\"xsbj_hidv_yc_"+bjdm+"\" "); 
							html.append("value=\""+bjdm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"xsbj_hidv_qt\" ");
							html.append("id=\"xsbj_hidv_qt_" + bjdm + "\" ");
							html.append("value=\"" + bjdm + "\"/> ");															
						}
						
						bj_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"xsbj_py_num\" value=\""+bj_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xsbj_num\" value=\""+bj_num+"\"/> ");
		
		html.append("</ul>");
		
		if (bj_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"xsbj_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	

	/**
	 * 创建联动Div（专业，班级）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void creatBmNewHtml(String[] nj, String[] xy, String[] zy, String bmlx,
			String stylePath, User user, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 用户身份
		String userStatus = user.getUserStatus();
		// 用户名
		String userName = user.getUserName();
		// 用户所在部门
		String userDep = user.getUserDep();
		
		StringBuilder html = new StringBuilder();

		if ("zyNew".equalsIgnoreCase(bmlx)) {// 联动专业
			List<HashMap<String, String>> zyList = getZyNewByXy(nj, xy,
					userStatus, userName, userDep);

			html.append(creatZyNewHtml(nj, xy, zyList, stylePath));
		} else if ("bjNew".equalsIgnoreCase(bmlx)) {// 联动班级
			List<HashMap<String, String>> bjList = getBjNewByTj("", nj, xy,
					zy, userStatus, userName, userDep);
			html.append(creatBjNewHtml(nj, xy, zy, bjList, stylePath));
		}

		response.getWriter().print(html.toString());
	}
	

	/**
	 * 获得专业过滤条件
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	private String creatZyNewHtml(String[] nj, String[] xy,
			List<HashMap<String, String>> zyList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// 专业数目
		int zy_num = 0;
		// 专业拼音数目
		int zy_py_num = 0;

		if (zyList != null && zyList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// 是否拼音
				boolean py_flag = true;
				// 是否结束
				boolean over_flag = false;
				// 是否“其他”
				boolean qt_flag = true;
				// “其他”数
				int qt_num = 0;
				// 其他ID
				String qi_id = "zyNew_qt_" + PY_BIG[i];
				
				for (int j = 0; j < zyList.size(); j++) {
					
					HashMap<String, String> map = zyList.get(j);
					
					// 专业代码
					String zydm = map.get("zydm");
					// 专业名称
					String zymc = map.get("zymc");
					// 专业拼音
					String zypy = map.get("zypy");

					if (PY_BIG[i].equalsIgnoreCase(zypy)) {

						if (xy != null && xy.length > 0) {// 选中学院

							if (zy_num < 12) {//专业数未超过12个
								
								if (py_flag) {

									html.append("<li>");

									html.append("<h5 id=\"zyNew_py_xs_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\"" + stylePath
											+ "images/num_" + PY_BIG[i]
											+ ".gif\" /></h5>");
									// divHtml += PY_BIG[i];
									html.append("</h5>");

									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								} 
								
								html.append(" <a href=\"#\" class=\"\" name=\"a_zyNew_mc\" ");
								html.append(" id=\"zyNew_mc_xs_");
								html.append(zydm);
								html.append("\"");
								html.append("onclick=\"clickZyNew(this);");
								html.append("creatClickedTj('zyNew','专业','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
								
							} else {//专业数超过12个
								
								if(py_flag){
									html.append("<li>");
												
									html.append("<h5 style=\"display:none\" id=\"zyNew_py_yc_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
									html.append("</h5>");
									
									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								}					
								
								html.append(" <a href=\"#\" class=\"\" name=\"a_zyNew_mc\" ");
								html.append(" id=\"zyNew_mc_yc_");
								html.append(zydm);
								html.append("\" style=\"display:none;\"");
								html.append("onclick=\"clickZyNew(this);");
								html.append("creatClickedTj('zyNew','专业','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
							}

						} else {// 未选中学院
							if(zy_num < 12){//专业数未超过12个
								
								if (py_flag) {

									html.append("<li>");

									html.append("<h5 id=\"zyNew_py_xs_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\"" + stylePath
											+ "images/num_" + PY_BIG[i]
											+ ".gif\" /></h5>");
									// divHtml += PY_BIG[i];
									html.append("</h5>");

									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								} 
								
								if(qt_flag){
									
									html.append(" <a href=\"#\" class=\"\" name=\"a_zyNew_mc\" ");
									html.append(" id=\"zyNew_mc_xs_");
									html.append(zydm);
									html.append("\"");
									html.append("onclick=\"clickZyNew(this);");
									html.append("creatClickedTj('zyNew','专业','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
									
								}
								
								// 其他
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickZyNewQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">更多</a>");
									
									qt_flag = false;
									qt_num ++;
								
								}
							}else{//专业超过12个
								
								if(py_flag){
									
									html.append("<li>");
												
									html.append("<h5 style=\"display:none\" id=\"zyNew_py_yc_");
									html.append(zy_py_num);
									html.append("\">");
									html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
									html.append("</h5>");
									
									over_flag = true;
									py_flag = false;
									
									zy_py_num++;
								}					
								
								if(qt_flag){
									html.append(" <a href=\"#\" class=\"\" name=\"a_zyNew_mc\" ");
									html.append(" id=\"zyNew_mc_yc_");
									html.append(zydm);
									html.append("\" style=\"display:none;\"");
									html.append("onclick=\"clickZyNew(this);");
									html.append("creatClickedTj('zyNew','专业','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
								}
								
								// 其他
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickZyNewQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">更多</a>");
									
									qt_flag = false;
									qt_num ++;
									
									html.append("<input type=\"hidden\" name=\"zyNew_hidv_qt\"");
									html.append("id=\"zyNew_hidv_qt_");
									html.append(zydm);
									html.append("\" value=\"");
									html.append(PY_BIG[i]);
									html.append("\"/>");
								
								}
							}
						}
						
						if(qt_flag){
							zy_num++;
						}
					}
				}
				
				if(over_flag){
					html.append("</li>");
					over_flag = false;
				}	
			}

		}

		html.append("<input type=\"hidden\" id=\"zyNew_py_num\" value=\""+zy_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"zyNew_num\" value=\""+zy_num+"\"/> ");

		return html.toString();
	}
	

	/**
	 * 获得班级过滤条件
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	private String creatBjNewHtml(String[] nj, String[] xy,String[] zy,
			List<HashMap<String, String>> bjList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// 班级数目
		int bj_num = 0;
		// 班级拼音数目
		int bj_py_num = 0;

		if (bjList != null && bjList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// 是否拼音
				boolean py_flag = true;
				// 是否结束
				boolean over_flag = false;
				// 是否“其他”
				boolean qt_flag = true;
				// “其他”数
				int qt_num = 0;
				// 其他ID
				String qi_id = "bjNew_qt_" + PY_BIG[i];
				
				for (int j = 0; j < bjList.size(); j++) {
					
					HashMap<String, String> map = bjList.get(j);
					
					// 班级代码
					String bjdm = map.get("bjdm");
					// 班级名称
					String bjmc = map.get("bjmc");
					// 班级拼音
					String bjpy = map.get("bjpy");

					if (PY_BIG[i].equalsIgnoreCase(bjpy)) {

						if (bj_num < 12) {//班级数未超过12个
							
							if (py_flag) {

								html.append("<li>");

								html.append("<h5 id=\"bjNew_py_xs_");
								html.append(bj_py_num);
								html.append("\">");
								html.append("<img src=\"" + stylePath + "images/num_" + PY_BIG[i] + ".gif\" /></h5>");
								html.append("</h5>");

								over_flag = true;
								py_flag = false;
								
								bj_py_num++;
							} 
							
							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_bjNew_mc\" ");
								html.append(" id=\"bjNew_mc_xs_");
								html.append(bjdm);
								html.append("\"");
								html.append("onclick=\"clickBjNew(this);");
								html.append("creatClickedTj('bjNew','班级','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickBjNewQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");
								
								qt_flag = false;
								qt_num ++;
							
							}
							
						} else {//班级数超过12个
							
							if(py_flag){
								html.append("<li>");
											
								html.append("<h5 style=\"display:none\" id=\"bjNew_py_yc_");
								html.append(bj_py_num);
								html.append("\">");
								html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
								html.append("</h5>");
								
								over_flag = true;
								py_flag = false;
								
								bj_py_num++;
							}					
							
							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_bjNew_mc\" ");
								html.append(" id=\"bjNew_mc_yc_");
								html.append(bjdm);
								html.append("\" style=\"display:none;\"");
								html.append("onclick=\"clickBjNew(this);");
								html.append("creatClickedTj('bjNew','班级','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickBjNewQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");
								
								qt_flag = false;
								qt_num ++;
								
								html.append("<input type=\"hidden\" name=\"bjNew_hidv_qt\"");
								html.append("id=\"bjNew_hidv_qt_");
								html.append(bjdm);
								html.append("\" value=\"");
								html.append(PY_BIG[i]);
								html.append("\"/>");
							
							}
						}
					}
				}
				
				if(over_flag){
					html.append("</li>");
					over_flag = false;
				}	
			}

		}

		html.append("<input type=\"hidden\" id=\"bjNew_py_num\" value=\""+bj_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"bjNew_num\" value=\""+bj_num+"\"/> ");
		//System.out.println(html.toString());
		return html.toString();
	
	}
	
	/**
	 * 
	 * @描述:评奖项目类型
	 * @作者：cq [工号：785]
	 * @日期：2015-4-14 下午04:18:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private String createXxmlxHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\"xxmlx_ul\">");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_xxmlx\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickPjxmmc(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"xxmlx_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 
	 * @描述:评奖项目性质
	 * @作者：cq [工号：785]
	 * @日期：2015-4-14 下午04:18:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private String createXxmxzHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\"xxmxz_ul\" >");
		
		// 是否需要更多
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 条件代码
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// 条件名称
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// 是否显示
				String display = (j < 8) ? "" : "display: none;";
				
				html.append("<a ");
				
				html.append("style=\"" + display + "\" ");
				if(Base.isNull(display)){
					html.append("id=\"" + tj + "_mc_xs_" + tjdm + "\" ");
				}else{
					html.append("id=\"" + tj + "_mc_yc_" + tjdm + "\" ");
				}
				
				html.append("name=\"tj_xxmxz\" ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("onclick=\"");
				html.append("clickPjxmmc(this);");
				html.append("creatClickedTj('" + tj + "','" + mc + "','" + tjdm + "','" + tjmc + "',this);");
				html.append("return false;\" ");
				html.append(">");
				html.append(tjmc);
				html.append("</a>");
				
				
				html.append("</li>");
			}
		}
		
		html.append("</ul>");
		
		if (moreFlag ) {
			html.append("<a ");
			html.append("href=\"#\" ");
			html.append("id=\"xxmxz_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/** 
	 * @描述:初始化评奖名称
	 * @作者：cq [工号：785]
	 * @日期：2015-4-15 上午10:12:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param searchOptionModel
	 * @param user
	 * void 返回类型 
	 * @throws 
	 */
	public void setPjmcList(SearchForm searchForm, User user,String xzdm)
			throws Exception {

		if(xgxt.utils.String.StringUtils.isNull(xzdm)){
			xzdm = "1";
		}
		
		//评奖类型列表
		List<HashMap<String, Object>> xmlxList = getXmlxList(xzdm);
		searchForm.setXxmlxTjList(xmlxList);
		
		//评奖性质列表
		List<HashMap<String, Object>> xmxzList = getXmxzList();
		searchForm.setXxmxzTjList(xmxzList);
		
		// 评奖项目列表
		List<HashMap<String, Object>> pjxmList = getPjxmList(null, new String[]{xzdm});
		searchForm.setXmmcTjList(pjxmList);

	}
	
	
	/**
	 * 创建高级查询Div（班级条件）
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createXmmcHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// 条件
		String tj = (String) tjMap.get("tj");
		// 条件名称
		String mc = (String) tjMap.get("mc");
		// 条件列表
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("：</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// 班级数目
		int xmmc_num = 0;
		// 班级拼音数目
		int xmmc_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// 拼音
				String py = (String) map.get("py");
				// 班级列表
				List<HashMap<String, String>> xmList = (List<HashMap<String, String>>) map
						.get("pjxmList");

				if (xmList != null && xmList.size() > 0) {
					
					html.append("<li>");
					
					html.append("<h5 ");
					if (xmmc_num >= 12) {
						html.append("id=\"xmmc_py_yc_" + xmmc_py_num + "\" ");
						html.append("style=\"display: none\" ");
					}else{
						html.append("id=\"xmmc_py_xs_" + xmmc_py_num + "\" ");
					}
					html.append(">");
					html.append("<img src=\""+stylePath+"images/num_" + py + ".gif\" />");
					html.append("</h5>");
					
					xmmc_py_num++;
					
					for (int k = 0; k < xmList.size(); k++) {
						
						HashMap<String, String> xmMap = xmList.get(k);
						// 班级代码
						String xmdm = xmMap.get("dm");
						// 班级名称
						String xmmc = xmMap.get("mc");
						
						if(!"其他".equalsIgnoreCase(xmmc)){
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"\" "); 
							if (xmmc_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"xmmc_mc_yc_" + xmdm + "\" ");
							} else {
								html.append("id=\"xmmc_mc_xs_" + xmdm + "\" ");
							}
							html.append("name=\"a_xmmc_mc\" ");
							html.append("onclick=\"");
							html.append("clickOtherTj('"+tj+"','"+xmdm+"');");
							html.append("creatClickedTj('"+tj+"','"+mc+"','"+xmdm+"','"+xmmc+"',this);");
							html.append("return false;\" "); 
							html.append("title=\""+xmmc+"\" ");
							html.append("> ");
							html.append(xmmc);
							html.append("</a> ");
							
						}else{
							html.append("<a ");
							html.append("href=\"#\" "); 
							html.append("class=\"moreValue_click\" "); 
							if (xmmc_num >= 12) {
								html.append("style=\"display: none\" ");
								html.append("id=\"xmmc_qt_" + xmdm + "\" ");
							} else {
								html.append("id=\"xmmc_hidv_qt_" + xmdm + "\" ");
							}
							html.append("onclick=\"");
							if(xmdm.split("_").length != 1){
								String firstletter = xmdm.split("_")[1];
								html.append("clickPjxmQt(\'" +	firstletter+ "\')"+
								" ;return false;\" ");
							}else{
								html.append("clickPjxmQt(\'" +	xmdm+ "\')"+
								" ;return false;\" ");
							}
							html.append("> ");
							html.append("更多");
							html.append("</a> ");
						}
						
						if (xmmc_num >= 12) {
							html.append("<input type=\"hidden\" "); 
							html.append("name=\"xmmc_hidv_yc\" "); 
							html.append("id=\"xmmc_hidv_yc_"+xmdm+"\" "); 
							html.append("value=\""+xmdm+"\"/> ");
						
							html.append("<input type=\"hidden\" ");
							html.append("name=\"xmmc_hidv_qt\" ");
							html.append("id=\"xmmc_hidv_qt_" + xmdm + "\" ");
							html.append("value=\"" + xmdm + "\"/> ");															
						}
						
						xmmc_num++;
					}
					
					html.append("</li>");
				}
			}
		}
		
		html.append("<input type=\"hidden\" id=\"xmmc_py_num\" value=\""+xmmc_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xmmc_num\" value=\""+xmmc_num+"\"/> ");
		
		html.append("</ul>");
		
		if (xmmc_num >= 12) {
			html.append("<a ");				
			html.append("href=\"#\" ");
			html.append("id=\"xmmc_more\" ");
			html.append("class=\"more_down\" ");
			html.append("onclick=\"");
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','收起','更多');");
			html.append("return false;\" ");
			html.append(">");
			html.append("更多");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	
	/**
	 * 创建联动Div（评奖项目）
	 * 
	 * @author 
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void creatPjxmHtml(String[] xmlx, String[] xmxz, String ldlx,
			String stylePath, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		
		List<HashMap<String, String>> xmList = getNewXmList(xmlx,xmxz);
		html.append(creatPjxmHtml(xmlx, xmxz, xmList, stylePath));
		
		response.getWriter().print(html.toString());
	}
	
	
	/**
	 * 
	 * @描述:获得评奖项目过滤条件
	 * @作者：cq [工号：785]
	 * @日期：2015-5-28 下午04:03:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlx
	 * @param xmxz
	 * @param xmList
	 * @param stylePath
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private String creatPjxmHtml(String[] xmlx, String[] xmxz,
			List<HashMap<String, String>> xmList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// 班级数目
		int xm_num = 0;
		// 班级拼音数目
		int xm_py_num = 0;

		if (xmList != null && xmList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// 是否拼音
				boolean py_flag = true;
				// 是否结束
				boolean over_flag = false;
				// 是否“其他”
				boolean qt_flag = true;
				// “其他”数
				int qt_num = 0;
				// 其他ID
				String qi_id = "xmmc_qt_" + PY_BIG[i];
				
				for (int j = 0; j < xmList.size(); j++) {
					
					HashMap<String, String> map = xmList.get(j);
					
					// 项目代码
					String xmdm = map.get("xmdm");
					// 项目名称
					String xmmc = map.get("xmmc");
					// 项目拼音
					String xmpy = map.get("xmpy");

					if (PY_BIG[i].equalsIgnoreCase(xmpy)) {

						if (xm_num < 12) {//班级数未超过12个
							
							if (py_flag) {

								html.append("<li>");

								html.append("<h5 id=\"xmmc_py_xs_");
								html.append(xm_py_num);
								html.append("\">");
								html.append("<img src=\"" + stylePath + "images/num_" + PY_BIG[i] + ".gif\" /></h5>");
								html.append("</h5>");

								over_flag = true;
								py_flag = false;
								
								xm_py_num++;
							} 
							
							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_xmmc_mc\" ");
								html.append(" id=\"xmmc_mc_xs_");
								html.append(xmdm);
								html.append("\"");
								html.append("onclick=\"clickBj(this);");
								html.append("creatClickedTj('xmmc','项目名称','"+xmdm+"','"+xmmc+"',this);return false;\">");
								html.append(xmmc);
								html.append("</a>");
								
								xm_num++;
								qt_num++;
							}
							
							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickPjxmQt(\'");
								html.append(xmpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");
								
								qt_flag = false;
								qt_num ++;
							
							}
							
						} else {//班级数超过12个
							
							if(py_flag){
								html.append("<li>");
											
								html.append("<h5 style=\"display:none\" id=\"xmmc_py_yc_");
								html.append(xm_py_num);
								html.append("\">");
								html.append("<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>");
								html.append("</h5>");
								
								over_flag = true;
								py_flag = false;
								
								xm_py_num++;
							}					
							
							if(qt_flag){
								html.append(" <a href=\"#\" class=\"\" name=\"a_xmmc_mc\" ");
								html.append(" id=\"xmmc_mc_yc_");
								html.append(xmdm);
								html.append("\" style=\"display:none;\"");
								html.append("onclick=\"clickBj(this);");
								html.append("creatClickedTj('xmmc','项目名称','"+xmdm+"','"+xmmc+"',this);return false;\">");
								html.append(xmmc);
								html.append("</a>");
								
								xm_num++;
								qt_num++;
							}
							
							System.out.println(xmmc);
							System.out.println(qt_num);
							// 其他
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickPjxmQt(\'");
								html.append(xmpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">更多</a>");
								
								qt_flag = false;
								qt_num ++;
								
								html.append("<input type=\"hidden\" name=\"xmmc_hidv_qt\"");
								html.append("id=\"xmmc_hidv_qt_");
								html.append(xmdm);
								html.append("\" value=\"");
								html.append(PY_BIG[i]);
								html.append("\"/>");
							
							}
						}
					}
				}
				
				if(over_flag){
					html.append("</li>");
					over_flag = false;
				}	
			}

		}

		html.append("<input type=\"hidden\" id=\"xmmc_py_num\" value=\""+xm_py_num+"\"/> ");
		html.append("<input type=\"hidden\" id=\"xmmc_num\" value=\""+xm_num+"\"/> ");
		//System.out.println(html.toString());
		return html.toString();
	
	}

}
