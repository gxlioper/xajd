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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �߼���ѯ_Option_list��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SearchOptionList extends SearchService{

	/********************************************************************************************************************************/
	/**************************************************Ϊ���65535���⣬�߼���ѯ�ع����Ż�**************************************************/
	/********************************************************************************************************************************/

	/**
	 * ���������������б�(�����ѯ)
	 * 
	 * @author ΰ�����
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getSearchOptionList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {

		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		// ��������
		String[] spTj = searchOptionModel.getSpTj();
		// ��������
		String[] ldTj = searchOptionModel.getLdTj();
		// path
		String path = searchOptionModel.getPath();
		
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// ����
				String lx = tjMap.get("lx");
				// ����
				String tj = tjMap.get("tj");
				// ����
				String mc = tjMap.get("mc");
				
				// �����ѯ AND ����������
				if ("djcx".equalsIgnoreCase(lx) && !isExistInArr(ldTj, tj)) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					Class myClass = searchOptionModel.getClass();

					// ȡֵ����
					String getMethod = "get" + tj.substring(0, 1).toUpperCase()
							+ tj.substring(1) + "TjList";

					try {
						// Option �б�
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
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}
				} 
				// �����ֶ�
				else if ("djcx".equalsIgnoreCase(lx) && isExistInArr(ldTj, tj)) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					Class myClass = searchOptionModel.getClass();

					// ȡֵ����
					String getMethod = "get" + tj.substring(0, 1).toUpperCase()
							+ tj.substring(1) + "TjList";

					// Option �б�
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
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}

				}
				// �Զ���߼���ѯ
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
	 * ���������������б�(ʱ��)
	 * 
	 * @author ΰ�����
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getSearchTimeList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {

		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// ����
				String lx = tjMap.get("lx");
				// ����
				String tj = tjMap.get("tj");
				// ����
				String mc = tjMap.get("mc");
				//��ʽ��
				String gshlx = tjMap.get("gshlx");
				
				// ʱ���ѯ AND ����������
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
	 * @����:������ѯ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-16 ����07:23:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param searchOptionModel
	 * @param tjList
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getSearchBatchList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {

		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// ����
				String lx = tjMap.get("lx");
				// ����
				String tj = tjMap.get("tj");
				// ����
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
	 * �������������ֵ����
	 */
	public List<HashMap<String, Object>> getSearchNumList(SearchForm searchOptionModel,
			List<HashMap<String, String>> tjList) {
		List<HashMap<String, Object>> searchList = new ArrayList<HashMap<String, Object>>();
		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tjMap = tjList.get(i);
				// ����
				String lx = tjMap.get("lx");
				// ����
				String tj = tjMap.get("tj");
				// ����
				String mc = tjMap.get("mc");
				// ��ֵ���� AND ����������
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
	 * �����߼���ѯDiv�������ѯ��
	 * 
	 * @author ΰ�����
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

		// ��������
		String[] spTj = searchOptionModel.getSpTj();
		// ��������
		String[] ldTj = searchOptionModel.getLdTj();
		// ��ʽ��·��
		String stylePath = searchOptionModel.getStylePath();
		//������ѯ����
		if(batchList != null && batchList.size() > 0){
			html.append(createBatchHtml(batchList));
			
		}
		if (optionList != null && optionList.size() > 0) {
			for (int i = 0; i < optionList.size(); i++) {
				
				HashMap<String, Object> tjMap = optionList.get(i);
				// ����
				String tj = (String) tjMap.get("tj");	
				// ����
				String lx = (String) tjMap.get("lx");
				
				// �Զ���߼���ѯ����
				if (!StringUtils.isBlank(lx) && "zdycx".equalsIgnoreCase(lx)){
					html.append(createPtHtml(tjMap));
				} else if(!isExistInArr(ldTj, tj) && !isExistInArr(spTj, tj)) { // ���������� AND ����������
					html.append(createPtHtml(tjMap));
				} else if (isExistInArr(spTj, tj)) {// �����ֶ�
					html.append(createSpHtml(tjMap));
				} else if (isExistInArr(ldTj, tj)) {// �����ֶ�
					
 					if ("nj".equalsIgnoreCase(tj)) {// �꼶
						html.append(createNjHtml(tjMap));
					} else if ("xy".equalsIgnoreCase(tj)) {// ѧԺ
						html.append(createXyHtml(tjMap, stylePath));
					} else if ("zy".equalsIgnoreCase(tj)) {// רҵ
						html.append(createZyHtml(tjMap, stylePath));
					} else if ("sy".equalsIgnoreCase(tj)) {// ��Ժ
						html.append(createSyHtml(tjMap));
					} else if ("bj".equalsIgnoreCase(tj)) {// �����༶
						html.append(createBjHtml(tjMap, stylePath));
					} else if ("zybj".equalsIgnoreCase(tj)){//רҵ�༶
						html.append(createZybjHtml(tjMap, stylePath));
					}else if ("njNew".equalsIgnoreCase(tj)) {// �꼶[ȫ]
						html.append(createNjNewHtml(tjMap));
					} else if ("xyNew".equalsIgnoreCase(tj)) {// ѧԺ[ȫ]
						html.append(createXyNewHtml(tjMap, stylePath));
					} else if ("zyNew".equalsIgnoreCase(tj)) {// רҵ[ȫ]
						html.append(createZyNewHtml(tjMap, stylePath));
					} else if ("bjNew".equalsIgnoreCase(tj)) {// �༶[ȫ]
						html.append(createBjNewHtml(tjMap, stylePath));
					} else if ("xqdm".equalsIgnoreCase(tj)) {// У��
						html.append(createXqdmHtml(tjMap));					
					} else if ("yqdm".equalsIgnoreCase(tj)) {// ԰��
						html.append(createYqdmHtml(tjMap));
					} else if ("ld".equalsIgnoreCase(tj)
							|| "ch".equalsIgnoreCase(tj)) {// ¥��,���
						html.append(createLdChHtml(tjMap));
					} else if ("qsh".equalsIgnoreCase(tj)) {// ���Һ�
						html.append(createQshHtml(tjMap, stylePath));
					} else if ("qgbm".equalsIgnoreCase(tj)){// �ڹ�����
						html.append(createQgbmHtml(tjMap));
					} else if ("tid".equalsIgnoreCase(tj)) {// ��id
						html.append(createTidHtml(tjMap));
					} else if ("yid".equalsIgnoreCase(tj)) {// Ӫid
						html.append(createYidHtml(tjMap));
					} else if ("lid".equalsIgnoreCase(tj)) {// ��id
						html.append(createLidHtml(tjMap));
					} else if ("pid".equalsIgnoreCase(tj)) {// ��id
						html.append(createPidHtml(tjMap));
					} else if ("bid".equalsIgnoreCase(tj)) {
						html.append(createBidHtml(tjMap));
					} else if ("ssid".equalsIgnoreCase(tj)) {
						html.append(createSsidHtml(tjMap));
					} else if ("gyjllbdldm".equalsIgnoreCase(tj)){//Υ�����������
						html.append(createTylddmHtml(tjMap,"gyjllbdldm","clickGyjllbdldm"));
					} else if ("gyjllbdm".equalsIgnoreCase(tj)){//Υ�����������
						html.append(createTylddmHtml(tjMap,"gyjllbdm","clickGyjllbdm"));
					} else if("sheng".equalsIgnoreCase(tj)){
						html.append(createTylddmHtml(tjMap,"sheng","clickSHENG"));//ʡ
					} else if("shi".equalsIgnoreCase(tj)){
						html.append(createTylddmHtml(tjMap,"shi","clickSHI"));//��
					} else if("qu".equalsIgnoreCase(tj)){
						html.append(createTylddmHtml(tjMap,"qu","clickQU"));//��
					} else if("xxmlx".equalsIgnoreCase(tj)){	//��Ŀ����
						html.append(createXxmlxHtml(tjMap));
					} else if("xxmxz".equalsIgnoreCase(tj)){	//��Ŀ����
						html.append(createXxmxzHtml(tjMap));
					} else if ("xmmc".equalsIgnoreCase(tj)){	//������Ŀ����
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
				// ����
				String tj = (String) tjMap.get("tj");
				// ��������
				String mc = (String) tjMap.get("mc");
				
				String sjlx = (String) tjMap.get("sjlx");
				String gshlx=(String)tjMap.get("gshlx");
				if(Base.isNull(gshlx)) {
					gshlx = "ymmdd";
				}
				
				if("dg".equalsIgnoreCase(sjlx)){
					html.append("<dl>");
					html.append("<dt>"+mc+"��</dt>");
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
					html.append("<dt>"+mc+"��</dt>");
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
					html.append("�� ");
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
				// ����
				String tj = (String) tjMap.get("tj");
				// ��������
				String mc = (String) tjMap.get("mc");
				
				html.append("<dl>");
				html.append("<dt>"+mc+"��</dt>");
				html.append("<dd>");
				html.append("<ul>");
				html.append("<li>");
				html.append("  ");
				html.append("<input type=\"text\" name=\"searchModel.search_tj_ksnum\" class=\"\" ");
				html.append("onkeyup=\"value=value.replace(/(?:\\D*)?(\\d*)?(\\.)?(\\d*)?/ig,'$1$2$3');createKsnumOrJsnum();\" ");
				html.append("id=\""+tj+"_ksnum\" maxlength='10'/>");
				html.append("</li>");
				html.append("<li>");
				html.append("�� ");
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

		// =========================���� end===========================
	
		//System.out.println(html.toString());
		response.getWriter().print(html.toString());
	}

	/**
	 * �����߼���ѯDiv��У����
	 * @author zhanghui
	 */
	private String createXqdmHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("xqdm_ul") ? "" : " id=\"xqdm_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * �����߼���ѯDiv��԰����
	 * @author zhanghui
	 */
	private String createYqdmHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("yqdm_ul") ? "" : " id=\"yqdm_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * �����߼���ѯDiv��¥��,���������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createLdChHtml(HashMap<String, Object> tjMap) {
		
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull(ul_id) ? "" : " id=\"" + ul_id + "\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * �����߼���ѯDiv�����Һ�������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createQshHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// ���Һ���Ŀ
		int qsh_num = 0;
		// ���Һ�ƴ����Ŀ
		int qsh_py_num = 0;

		if (tjLit != null && tjLit.size() > 0) {

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// �����б�
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
						// ���Ҵ���
						String qshdm = qsMap.get("qshdm");
						// ��������
						String qshmc = qsMap.get("qshmc");
						
						if(!"����".equalsIgnoreCase(qshmc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * �����߼���ѯDiv���༶������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createBjHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// �༶��Ŀ
		int bj_num = 0;
		// �༶ƴ����Ŀ
		int bj_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// �༶�б�
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
						// �༶����
						String bjdm = bjMap.get("bjdm");
						// �༶����
						String bjmc = bjMap.get("bjmc");
						
						if(!"����".equalsIgnoreCase(bjmc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	private String createZybjHtml(HashMap<String, Object> tjMap,String stylePath) {


		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");

		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");

		// �༶��Ŀ
		int bj_num = 0;
		// �༶ƴ����Ŀ
		int bj_py_num = 0;

		if (tjLit != null && tjLit.size() > 0) {

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("zybjpy");
				// �༶�б�
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
						// �༶����
						String bjdm = bjMap.get("bjdm");
						// �༶����
						String bjmc = bjMap.get("bjmc");

						if(!"����".equalsIgnoreCase(bjmc)){
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
							html.append("clickZyBjQt('" + py + "');");
							html.append("return false;\" ");
							html.append("> ");
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}

		html.append("</dd>");
		html.append("</dl>");

		return html.toString();
	}
	/**
	 * �����߼���ѯDiv��רҵ������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createZyHtml(HashMap<String, Object> tjMap, String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// רҵ��Ŀ
		int zy_num = 0;
		// רҵƴ����Ŀ
		int zy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// רҵ�б�
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
						// רҵ����
						String zydm = zyMap.get("zydm");
						// רҵ����
						String zymc = zyMap.get("zymc");
						
						if(!"����".equalsIgnoreCase(zymc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * �����߼���ѯDiv��ѧԺ������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createXyHtml(HashMap<String, Object> tjMap, String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// ѧԺ��Ŀ
		int xy_num = 0;
		// ѧԺƴ����Ŀ
		int xy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// ѧԺ�б�
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
						// ѧԺ����
						String xydm = xyMap.get("xydm");
						// ѧԺ����
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * �����߼���ѯDiv���꼶������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createNjHtml(HashMap<String, Object> tjMap) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("nj");
				// ��������
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
	 * �����߼���ѯ��Ժ
	 * @param tjMap
	 * @return
	 */
	private String createSyHtml(HashMap<String, Object> tjMap) {


		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");

		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");

		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				// ��������
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
	 * �����߼���ѯDiv������������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createSpHtml(HashMap<String, Object> tjMap) {
		
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul>");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * �����߼���ѯDiv����ͨ������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createPtHtml(HashMap<String, Object> tjMap) {
		
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul>");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
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
	 * ����������ѯ����div
	 */
	@SuppressWarnings("unchecked")
	private String createBatchHtml(List<HashMap<String, Object>> batchList) {
			
			StringBuilder html = new StringBuilder();
			
			
			html.append("<dl>");
			html.append("<dt>");
			html.append("������ѯ����");
			html.append("��</dt>");
			html.append("<dd>");
			html.append("<ul>");
			
				for (int j = 0; j < batchList.size(); j++) {
					HashMap<String, Object> map = batchList.get(j);
					// ��������
					String tjdm = (String) map.get("tj");
					tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
					// ��������
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
	 * ��������Div��רҵ���༶��
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void creatBmHtml(String[] nj, String[] xy, String[] zy, String bmlx,
			String stylePath, User user, HttpServletResponse response,String sfzxs)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// �û����
		String userStatus = user.getUserStatus();
		// �û���
		String userName = user.getUserName();
		// �û����ڲ���
		String userDep = user.getUserDep();
		
		StringBuilder html = new StringBuilder();

		if ("zy".equalsIgnoreCase(bmlx)) {// ����רҵ
			List<HashMap<String, String>> zyList = getZyInfoByXy(nj, xy,
					userStatus, userName, userDep,sfzxs);
			html.append(creatZyHtml(nj, xy, zyList, stylePath));
		} else if ("zybj".equalsIgnoreCase(bmlx)) {// �����༶
			List<HashMap<String, String>> bjList = getBjInfoByTj("", nj, xy,
					zy, userStatus, userName, userDep,sfzxs);
			html.append(creatBjHtml(nj, xy, zy, bjList, stylePath));
		}

		response.getWriter().print(html.toString());
	}

	/**
	 * ��������div����Ժ�������༶��
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

		// �û����
		String userStatus = user.getUserStatus();
		// �û���
		String userName = user.getUserName();
		// �û����ڲ���
		String userDep = user.getUserSyDep();

		StringBuilder html = new StringBuilder();

		if ("bj".equalsIgnoreCase(bmlx)) {// �����༶
			List<HashMap<String, String>> bjList = getXzbjInfoByTj("", sy, userStatus, userName, userDep,sfzxs);
			html.append(creatXzbjHtml(sy, bjList, stylePath));
		}

		response.getWriter().print(html.toString());
	}
	
	/**
	 * ���רҵ��������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	private String creatZyHtml(String[] nj, String[] xy,
			List<HashMap<String, String>> zyList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// רҵ��Ŀ
		int zy_num = 0;
		// רҵƴ����Ŀ
		int zy_py_num = 0;

		if (zyList != null && zyList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
				boolean over_flag = false;
				// �Ƿ�������
				boolean qt_flag = true;
				// ����������
				int qt_num = 0;
				// ����ID
				String qi_id = "zy_qt_" + PY_BIG[i];
				
				for (int j = 0; j < zyList.size(); j++) {
					
					HashMap<String, String> map = zyList.get(j);
					
					// רҵ����
					String zydm = map.get("zydm");
					// רҵ����
					String zymc = map.get("zymc");
					// רҵƴ��
					String zypy = map.get("zypy");

					if (PY_BIG[i].equalsIgnoreCase(zypy)) {

						if (xy != null && xy.length > 0) {// ѡ��ѧԺ

							if (zy_num < 12) {//רҵ��δ����12��
								
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
								html.append("creatClickedTj('zy','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
								
							} else {//רҵ������12��
								
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
								html.append("creatClickedTj('zy','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
							}

						} else {// δѡ��ѧԺ
							if(zy_num < 12){//רҵ��δ����12��
								
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
									html.append("creatClickedTj('zy','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
									
								}
								
								// ����
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickZyQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">����</a>");
									
									qt_flag = false;
									qt_num ++;
								
								}
							}else{//רҵ����12��
								
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
									html.append("creatClickedTj('zy','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
								}
								
								// ����
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickZyQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">����</a>");
									
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
	 * ��ð༶����������רҵ�༶��
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	private String creatBjHtml(String[] nj, String[] xy,String[] zy,
			List<HashMap<String, String>> bjList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// �༶��Ŀ
		int bj_num = 0;
		// �༶ƴ����Ŀ
		int bj_py_num = 0;

		if (bjList != null && bjList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
				boolean over_flag = false;
				// �Ƿ�������
				boolean qt_flag = true;
				// ����������
				int qt_num = 0;
				// ����ID
				String qi_id = "zybj_qt_" + PY_BIG[i];
				
				for (int j = 0; j < bjList.size(); j++) {
					
					HashMap<String, String> map = bjList.get(j);
					
					// �༶����
					String bjdm = map.get("bjdm");
					// �༶����
					String bjmc = map.get("bjmc");
					// �༶ƴ��
					String bjpy = map.get("bjpy");

					if (PY_BIG[i].equalsIgnoreCase(bjpy)) {

						if (bj_num < 12) {//�༶��δ����12��
							
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
								html.append("creatClickedTj('zybj','�༶','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickZyBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");
								
								qt_flag = false;
								qt_num ++;
							
							}
							
						} else {//�༶������12��
							
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
								html.append("creatClickedTj('zybj','�༶','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickZyBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");
								
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
	 * ��ð༶���������������༶��
	 * @param sy
	 * @param bjList
	 * @param stylePath
	 * @return
	 */
	private String creatXzbjHtml(String[] sy,
							   List<HashMap<String, String>> bjList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// �༶��Ŀ
		int bj_num = 0;
		// �༶ƴ����Ŀ
		int bj_py_num = 0;

		if (bjList != null && bjList.size() > 0) {

			for (int i = 0; i < PY_BIG.length; i++) {

				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
				boolean over_flag = false;
				// �Ƿ�������
				boolean qt_flag = true;
				// ����������
				int qt_num = 0;
				// ����ID
				String qi_id = "bj_qt_" + PY_BIG[i];

				for (int j = 0; j < bjList.size(); j++) {

					HashMap<String, String> map = bjList.get(j);

					// �༶����
					String bjdm = map.get("bjdm");
					// �༶����
					String bjmc = map.get("bjmc");
					// �༶ƴ��
					String bjpy = map.get("bjpy");

					if (PY_BIG[i].equalsIgnoreCase(bjpy)) {

						if (bj_num < 12) {//�༶��δ����12��

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
								html.append("creatClickedTj('bj','�༶','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");

								bj_num++;
								qt_num++;
							}

							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");

								qt_flag = false;
								qt_num ++;

							}

						} else {//�༶������12��

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
								html.append("creatClickedTj('bj','�༶','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");

								bj_num++;
								qt_num++;
							}

							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickBjQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");

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
	 * ��������Div��¥������ţ����Һţ�
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void creatGyHtml(String[] xq, String[] yq, String[] ld, String[] ch,
			String bmlx, String stylePath, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// �û����
		String userStatus = user.getUserStatus();
		// �û���
		String userName = user.getUserName();
		// �û����ڲ���
		String userDep = user.getUserDep();
		
		StringBuilder html = new StringBuilder();
		if ("yqdm".equalsIgnoreCase(bmlx)){
			List<HashMap<String, Object>> yqList = getYqdmList(xq, user);
			html.append(creatYqHtml(yqList));
		} else if ("ld".equalsIgnoreCase(bmlx)){
			List<HashMap<String, Object>> ldList = getLdList(xq, yq, user);
			html.append(creatLdHtml(ldList));
		} else if ("ch".equalsIgnoreCase(bmlx)) {// �������
			List<HashMap<String, Object>> chList = getChList(xq, yq, ld);
			html.append(creatChHtml(chList));
		} else if ("qsh".equalsIgnoreCase(bmlx)) {// �������Һ�
			List<HashMap<String, Object>> qshList = getQsList(xq, yq, ld, ch);
			html.append(creatQshHtml(qshList, stylePath));
		}
		//�����꼶
		else if("nj".equalsIgnoreCase(bmlx)){
			List<HashMap<String, String>> njList = getNewNjListByYq(userStatus, userName, userDep, xq, yq);
			html.append(createNjNewHtmlByYq(njList, stylePath));
			
		}
		//����ѧԺ
		else if("xy".equalsIgnoreCase(bmlx)){ 
			List<HashMap<String, String>> xyList = getNewXyListByYq(userStatus, userName, userDep, xq, yq);
			html.append(createXyNewHtmlByYq(xyList, stylePath));
		}

		response.getWriter().print(html.toString());
	}
	
	/** 
	 * @����:�㽭����---�Ʒ���Ŀ����
	 * @���ߣ�cp[���ţ�1352]
	 * @���ڣ�2017-1-4 ����04:05:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jfxmList
	 * @return
	 * Object �������� 
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
	 * 	���԰����������
	 */
	private String creatYqHtml(List<HashMap<String, Object>> yqList) {
		
		StringBuilder html = new StringBuilder();
		
		//԰������
		int yq_num = 0;
				
		if (yqList != null && yqList.size() > 0) {
						
			for (int j = 0; j < yqList.size(); j++) {
				HashMap<String, Object> map = yqList.get(j);
				
				// ԰������
				String yqdm = (String) map.get("dm");
				// ԰������
				String yqmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('yqdm','԰��','" + yqdm + "','" + yqmc + "',this);");
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
	 * 	���¥����������
	 */
	private String creatLdHtml(List<HashMap<String, Object>> ldList) {
		
		StringBuilder html = new StringBuilder();
		
		//¥������
		int ld_num = 0;
				
		if (ldList != null && ldList.size() > 0) {
						
			for (int j = 0; j < ldList.size(); j++) {
				HashMap<String, Object> map = ldList.get(j);
				
				// ¥������
				String lddm = (String) map.get("dm");
				// ¥������
				String ldmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('ld','¥��','" + lddm + "','" + ldmc + "',this);");
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
	 * @����:html���ɼƷ���Ŀ��Ϣ
	 * @���ߣ�cp[���ţ�1352]
	 * @���ڣ�2017-1-4 ����04:05:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jfxmList
	 * @return
	 * Object �������� 
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
				html.append("creatClickedTj('jfxmdm','�Ʒ���Ŀ','" + chdm + "','" + chmc + "',this);return false;\">");
				html.append(chmc);
				html.append("</a>");
				html.append("</li>");
			}
		}
		
		return html.toString();
	}
	
	/**
	 * ��ò�Ź�������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	private String creatChHtml(List<HashMap<String, Object>> chList) {
		
		StringBuilder html = new StringBuilder();

		// �����Ŀ
		int ch_num = 0;

		if (chList != null && chList.size() > 0) {
			
			for (int j = 0; j < chList.size(); j++) {
				
				HashMap<String, Object> map = chList.get(j);
				
				// ��Ŵ���
				String chdm = (String) map.get("dm");
				// �������
				String chmc = (String) map.get("mc");

				html.append("<li>");
					
				html.append(" <a href=\"#\" class=\"\" name=\"tj_ch\" ");
				html.append(" id=\"tj_ch_");
				html.append(chdm);
				html.append("\"");
				html.append("onclick=\"clickCs(this);");
				html.append("creatClickedTj('ch','���','" + chdm + "','" + chmc + "',this);return false;\">");
				html.append(chmc);
				html.append("</a>");
				
				html.append("</li>");
			}
		}
		
		return html.toString();
	}
	
	/**
	 * ������ҹ�������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String creatQshHtml(
			List<HashMap<String, Object>> qsList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// ������Ŀ
		int qsh_num = 0;
		// ����ƴ����Ŀ
		int qsh_py_num = 0;

		if (qsList != null && qsList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
				boolean over_flag = false;
				// �Ƿ�������
				boolean qt_flag = true;
				// ����������
				int qt_num = 0;
				// ����ID
				String qi_id = "qsh_qt_" + PY_BIG[i];
				
				HashMap<String, Object> map = qsList.get(i);
				
				// ���Һ�ƴ��
				String qshpy = (String) map.get("py");

				List<HashMap<String, String>> qshList = (List<HashMap<String, String>>) map
						.get("qsList");
				
				if (qshList != null && qshList.size() > 0) {
					
					for (int j = 0; j < qshList.size(); j++) {

						HashMap<String, String> qsMap = qshList.get(j);

						// ���ҺŴ���
						String qshdm = qsMap.get("qshdm");
						// ���Һ�����
						String qshmc = qsMap.get("qshmc");

						if (PY_BIG[i].equalsIgnoreCase(qshpy)) {

							if (qsh_num < 12) {// ������δ����12��

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
									html.append("creatClickedTj('qsh','���Һ�','"
											+ qshdm + "','" + qshmc
											+ "',this);return false;\">");
									html.append(qshmc);
									html.append("</a>");

									qsh_num++;
									qt_num++;
								}

								// ����
								if (qt_num == 3) {

									html
											.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickQshQt(\'");
									html.append(qshpy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">����</a>");

									qt_flag = false;
									qt_num++;

								}

							} else {// ����������12��

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
									html.append("creatClickedTj('qsh','���Һ�','"
											+ qshdm + "','" + qshmc
											+ "',this);return false;\">");
									html.append(qshmc);
									html.append("</a>");

									qsh_num++;
									qt_num++;
								}

								// ����
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickQshQt(\'");
									html.append(qshpy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">����</a>");

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
	 * �����߼���ѯDiv���ڹ����ţ�
	 * @author yeyipin
	 */
	private String createQgbmHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("qgbm_ul") ? "" : " id=\"qgbm_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/********************************************************************************
	 * ********************************��ѵ��������html����***************************
	 * ******************************************************************************
	 */
	/**
	 * �����߼���ѯDiv���ţ�
	 * @author �׽���
	 */
	private String createTidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("tid_ul") ? "" : " id=\"tid_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * �����߼���ѯDiv��Ӫ��
	 * @author �׽���
	 */
	private String createYidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("yid_ul") ? "" : " id=\"yid_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * �����߼���ѯDiv������
	 * @author �׽���
	 */
	private String createLidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("lid_ul") ? "" : " id=\"lid_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * �����߼���ѯDiv���ţ�
	 * @author �׽���
	 */
	private String createPidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("pid_ul") ? "" : " id=\"pid_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	
	private String createBidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("bid_ul") ? "" : " id=\"bid_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	private String createSsidHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull("ssid_ul") ? "" : " id=\"ssid_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	
	
	
	/**
	 * 	�����id��������
	 */
	private String createTidHtml(List<HashMap<String, Object>> tidList) {
		
		StringBuilder html = new StringBuilder();
		
		//������
		int tid_num = 0;
				
		if (tidList != null && tidList.size() > 0) {
						
			for (int j = 0; j < tidList.size(); j++) {
				HashMap<String, Object> map = tidList.get(j);
				
				// ��id
				String tid = (String) map.get("dm");
				// ������
				String tmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('tid','��','" + tid + "','" + tmc + "',this);");
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
	 * 	���Ӫid��������
	 */
	private String createYidHtml(List<HashMap<String, Object>> yidList) {
		
		StringBuilder html = new StringBuilder();
		
		//Ӫ����
		int yid_num = 0;
				
		if (yidList != null && yidList.size() > 0) {
						
			for (int j = 0; j < yidList.size(); j++) {
				HashMap<String, Object> map = yidList.get(j);
				
				// Ӫid
				String yid = (String) map.get("dm");
				// Ӫ����
				String ymc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('yid','Ӫ','" + yid + "','" + ymc + "',this);");
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
	 * 	�����id��������
	 */
	private String createLidHtml(List<HashMap<String, Object>> lidList) {
		
		StringBuilder html = new StringBuilder();
		
		//������
		int lid_num = 0;
				
		if (lidList != null && lidList.size() > 0) {
						
			for (int j = 0; j < lidList.size(); j++) {
				HashMap<String, Object> map = lidList.get(j);
				
				// ��id
				String lid = (String) map.get("dm");
				// ������
				String lmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('lid','��','" + lid + "','" + lmc + "',this);");
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
	 * 	�����id��������
	 */
	private String createPidHtml(List<HashMap<String, Object>> pidList) {
		
		StringBuilder html = new StringBuilder();
		
		//������
		int tid_num = 0;
				
		if (pidList != null && pidList.size() > 0) {
						
			for (int j = 0; j < pidList.size(); j++) {
				HashMap<String, Object> map = pidList.get(j);
				
				// ��id
				String pid = (String) map.get("dm");
				// ������
				String pmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('pid','��','" + pid + "','" + pmc + "',this);");
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
		
		//������
		int bid_num = 0;
				
		if (bidList != null && bidList.size() > 0) {
						
			for (int j = 0; j < bidList.size(); j++) {
				HashMap<String, Object> map = bidList.get(j);
				
				// ��id
				String bid = (String) map.get("dm");
				// ������
				String bmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('bid','��','" + bid + "','" + bmc + "',this);");
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
		
		//������
		int ssid_num = 0;
				
		if (ssidList != null && ssidList.size() > 0) {
						
			for (int j = 0; j < ssidList.size(); j++) {
				HashMap<String, Object> map = ssidList.get(j);
				
				// ��id
				String ssid = (String) map.get("dm");
				// ������
				String ssmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
				html.append("creatClickedTj('ssid','����','" + ssid + "','" + ssmc + "',this);");
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
	 * ��������Div���š�Ӫ�������ţ�
	 * 
	 * @author �׽���
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
		if ("tid".equalsIgnoreCase(ldlx)){//���������
			List<HashMap<String, Object>> tidList = getTidList(list, jxid);
			html.append(createTidHtml(tidList));
		} else if ("yid".equalsIgnoreCase(ldlx)){//���Ӫ����
			List<HashMap<String, Object>> yidList = getYidList(list, jxid);
			html.append(createYidHtml(yidList));
		} else if ("lid".equalsIgnoreCase(ldlx)) {// ���������
			List<HashMap<String, Object>> lidList = getLidList(list, jxid);
			html.append(createLidHtml(lidList));
		} else if ("pid".equalsIgnoreCase(ldlx)) {// ���������
			List<HashMap<String, Object>> pidList = getPidList(list, jxid);
			html.append(createPidHtml(pidList));
		} else if ("bid".equalsIgnoreCase(ldlx)) {// ���������
			List<HashMap<String, Object>> bidList = getBidList(list, jxid);
			html.append(createBidHtml(bidList));
		} else if ("ssid".equalsIgnoreCase(ldlx)) {// �����������
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
		if ("gyjllbdm".equalsIgnoreCase(ldlx)){//���������
			List<HashMap<String, Object>> gyjllbdmList = getGyjllbdmList(tj);
			html.append(createTylddmHtml(gyjllbdmList,"gyjllbdm","�������","clickGyjllbdm"));
		}

		response.getWriter().print(html.toString());
	}
	/**
	 * ʡ������ ��������
	 */
	public void createQxHtml(List<String> tj, String jb,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=gbk");
		String mc ="";
		if(jb!=null && "shi".equals(jb)){
			mc ="��";
		}else{
			mc ="����";
		}		
		StringBuilder html = new StringBuilder();
		List<HashMap<String, Object>> qxList = getQxList(tj,jb);
		html.append(createQxHtml(qxList,jb,mc,"click"+jb.toUpperCase()));
		response.getWriter().print(html.toString());
	}
	
	/**
	 * �����߼���ѯDiv��ͨ�ã�
	 * @author zhanghui
	 */
	private String createTylddmHtml(HashMap<String, Object> tjMap,String dm,String function) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul");
		html.append(Base.isNull(dm+"_ul") ? "" : " id=\""+dm+"_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * �����߼���ѯͨ����������
	 * @author zhanghui
	 * @param tjMap
	 * @param dm		��������
	 * @param function	click����������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createTylddmHtml(List<HashMap<String, Object>> tjMap,String dm,String mc,String function) {
		
		StringBuilder html = new StringBuilder();
		
		//����
		int dm_num = 0;
				
		if (tjMap != null && tjMap.size() > 0) {
						
			for (int j = 0; j < tjMap.size(); j++) {
				HashMap<String, Object> map = tjMap.get(j);
				
				// ��������
				String lddm = (String) map.get("dm");
				// ��������
				String ldmc = (String) map.get("mc");

				html.append("<li>");
				
				// �Ƿ���ʾ
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
	 * @����:ʡ������������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-21 ����05:04:57
	 * @�޸ļ�¼: 
	 * @param tjMap
	 * @param dm
	 * @param mc
	 * @param function
	 * @return
	 * String �������� 
	 * @throws
	 */
	/*public String createQxHtml(List<HashMap<String, Object>> tjMap,String dm,String mc,String function) {
			
			StringBuilder html = new StringBuilder();
			
			//����
			int dm_num = 0;
					
			if (tjMap != null && tjMap.size() > 0) {
				
				for (int j = 0; j < tjMap.size(); j++) {
					HashMap<String, Object> map = tjMap.get(j);
					
					// ��������
					String lddm = (String) map.get("dm");
					// ��������
					String ldmc = (String) map.get("mc");
	
					html.append("<li>");
					
					// �Ƿ���ʾ
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
		// ����
		String tj = dm;
		
	
		html.append("<ul");
		html.append(Base.isNull(dm+"_ul") ? "" : " id=\""+dm+"_ul\"");
		html.append(">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				
				// ��������
				String lddm = (String) map.get("dm");
				// ��������
				String ldmc = (String) map.get("mc");
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}

		
		return html.toString();
	}
	


	/**
	 * 
	 * @����: �����߼���ѯDiv���꼶��������ȫ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����04:23:05
	 * @param tjMap
	 * @return
	 * String �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createNjNewHtml(HashMap<String, Object> tjMap) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("nj");
				// ��������
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
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		if (tjLit != null && tjLit.size() > 0) {
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("nj");
				// ��������
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
	 * @����:����԰�����¼���ѧԺ�߼���ѯ����
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-12 ����04:58:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjMap
	 * @param stylePath
	 * @return
	 * String �������� 
	 * @throws
	 */
	
	private String createXyNewHtmlByYq(List<HashMap<String, String>> xyList, String stylePath) {
		

		StringBuilder html = new StringBuilder();
	
		// ѧԺ��Ŀ
		int xy_num = 0;
		// ѧԺƴ����Ŀ
		int xy_py_num = 0;
		
		if (xyList != null && xyList.size() > 0) {	
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
			for (int j = 0; j < xyList.size(); j++) {
				HashMap<String, String> map = xyList.get(j);
				// ƴ��
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
						// ѧԺ����
						String xydm = xyMap.get("xydm");
						// ѧԺ����
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
						html.append("creatClickedTj('xy','ѧԺ','"+xydm+"','"+xymc+"',this);");
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
	 * @����:����԰�����¼����꼶�߼���ѯ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-13 ����04:26:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjMap
	 * @return
	 * String �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createNjNewHtmlByYq(List<HashMap<String, String>> njList,String stylePath) {
		

		StringBuilder html = new StringBuilder();
	
		
		if (njList != null && njList.size() > 0) {
			for (int j = 0; j < njList.size(); j++) {
				HashMap<String, String> map = njList.get(j);
				// ��������
				String tjdm = (String) map.get("nj");
				// ��������
				String tjmc = (String) map.get("nj");
				
				html.append("<li>");
				html.append("<a ");
				html.append("href=\"#\" ");
				html.append("class=\"\" ");
				html.append("id=\"tj_" + "nj" + "_" + tjdm + "\" ");
				html.append("name=\"tj_" + "nj" + "\" ");
				html.append("onclick=\"");
				html.append("clickNj(this);");
				html.append("creatClickedTj('" + "nj" + "','" + "�꼶" + "','" + tjdm + "','" + tjmc + "',this);");
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
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// ѧԺ��Ŀ
		int xy_num = 0;
		// ѧԺƴ����Ŀ
		int xy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// ѧԺ�б�
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
						// ѧԺ����
						String xydm = xyMap.get("xydm");
						// ѧԺ����
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	@SuppressWarnings("unchecked")
	private String createXsXyHtml(HashMap<String, Object> tjMap, String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// ѧԺ��Ŀ
		int xy_num = 0;
		// ѧԺƴ����Ŀ
		int xy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// ѧԺ�б�
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
						// ѧԺ����
						String xydm = xyMap.get("xydm");
						// ѧԺ����
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	

	/**
	 * 
	 * @����: �����߼���ѯDiv��רҵ��������ȫ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����04:24:12
	 * @param tjMap
	 * @param stylePath
	 * @return
	 * String �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createZyNewHtml(HashMap<String, Object> tjMap, String stylePath) {

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// רҵ��Ŀ
		int zy_num = 0;
		// רҵƴ����Ŀ
		int zy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// רҵ�б�
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
						// רҵ����
						String zydm = zyMap.get("zydm");
						// רҵ����
						String zymc = zyMap.get("zymc");
						
						if(!"����".equalsIgnoreCase(zymc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	private String createXsZyHtml(HashMap<String, Object> tjMap, String stylePath) {

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// רҵ��Ŀ
		int zy_num = 0;
		// רҵƴ����Ŀ
		int zy_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// רҵ�б�
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
						// רҵ����
						String zydm = zyMap.get("zydm");
						// רҵ����
						String zymc = zyMap.get("zymc");
						
						if(!"����".equalsIgnoreCase(zymc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/**
	 * 
	 * @����: �����߼���ѯDiv���༶��������ȫ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����04:38:33
	 * @param tjMap
	 * @param stylePath
	 * @return
	 * String �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private String createBjNewHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// �༶��Ŀ
		int bj_num = 0;
		// �༶ƴ����Ŀ
		int bj_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// �༶�б�
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
						// �༶����
						String bjdm = bjMap.get("bjdm");
						// �༶����
						String bjmc = bjMap.get("bjmc");
						
						if(!"����".equalsIgnoreCase(bjmc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
private String createXsBjHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// �༶��Ŀ
		int bj_num = 0;
		// �༶ƴ����Ŀ
		int bj_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// �༶�б�
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
						// �༶����
						String bjdm = bjMap.get("bjdm");
						// �༶����
						String bjmc = bjMap.get("bjmc");
						
						if(!"����".equalsIgnoreCase(bjmc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	

	/**
	 * ��������Div��רҵ���༶��
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void creatBmNewHtml(String[] nj, String[] xy, String[] zy, String bmlx,
			String stylePath, User user, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// �û����
		String userStatus = user.getUserStatus();
		// �û���
		String userName = user.getUserName();
		// �û����ڲ���
		String userDep = user.getUserDep();
		
		StringBuilder html = new StringBuilder();

		if ("zyNew".equalsIgnoreCase(bmlx)) {// ����רҵ
			List<HashMap<String, String>> zyList = getZyNewByXy(nj, xy,
					userStatus, userName, userDep);

			html.append(creatZyNewHtml(nj, xy, zyList, stylePath));
		} else if ("bjNew".equalsIgnoreCase(bmlx)) {// �����༶
			List<HashMap<String, String>> bjList = getBjNewByTj("", nj, xy,
					zy, userStatus, userName, userDep);
			html.append(creatBjNewHtml(nj, xy, zy, bjList, stylePath));
		}

		response.getWriter().print(html.toString());
	}
	

	/**
	 * ���רҵ��������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	private String creatZyNewHtml(String[] nj, String[] xy,
			List<HashMap<String, String>> zyList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// רҵ��Ŀ
		int zy_num = 0;
		// רҵƴ����Ŀ
		int zy_py_num = 0;

		if (zyList != null && zyList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
				boolean over_flag = false;
				// �Ƿ�������
				boolean qt_flag = true;
				// ����������
				int qt_num = 0;
				// ����ID
				String qi_id = "zyNew_qt_" + PY_BIG[i];
				
				for (int j = 0; j < zyList.size(); j++) {
					
					HashMap<String, String> map = zyList.get(j);
					
					// רҵ����
					String zydm = map.get("zydm");
					// רҵ����
					String zymc = map.get("zymc");
					// רҵƴ��
					String zypy = map.get("zypy");

					if (PY_BIG[i].equalsIgnoreCase(zypy)) {

						if (xy != null && xy.length > 0) {// ѡ��ѧԺ

							if (zy_num < 12) {//רҵ��δ����12��
								
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
								html.append("creatClickedTj('zyNew','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
								
							} else {//רҵ������12��
								
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
								html.append("creatClickedTj('zyNew','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
								html.append(zymc);
								html.append("</a>");
							}

						} else {// δѡ��ѧԺ
							if(zy_num < 12){//רҵ��δ����12��
								
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
									html.append("creatClickedTj('zyNew','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
									
								}
								
								// ����
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickZyNewQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">����</a>");
									
									qt_flag = false;
									qt_num ++;
								
								}
							}else{//רҵ����12��
								
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
									html.append("creatClickedTj('zyNew','רҵ','"+zydm+"','"+zymc+"',this);return false;\">");
									html.append(zymc);
									html.append("</a>");
									
									zy_num++;
									qt_num++;
								}
								
								// ����
								if (qt_num == 3) {

									html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickZyNewQt(\'");
									html.append(zypy);
									html.append("\')\"");
									html.append("id=\"");
									html.append(qi_id);
									html.append("\">����</a>");
									
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
	 * ��ð༶��������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	private String creatBjNewHtml(String[] nj, String[] xy,String[] zy,
			List<HashMap<String, String>> bjList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// �༶��Ŀ
		int bj_num = 0;
		// �༶ƴ����Ŀ
		int bj_py_num = 0;

		if (bjList != null && bjList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
				boolean over_flag = false;
				// �Ƿ�������
				boolean qt_flag = true;
				// ����������
				int qt_num = 0;
				// ����ID
				String qi_id = "bjNew_qt_" + PY_BIG[i];
				
				for (int j = 0; j < bjList.size(); j++) {
					
					HashMap<String, String> map = bjList.get(j);
					
					// �༶����
					String bjdm = map.get("bjdm");
					// �༶����
					String bjmc = map.get("bjmc");
					// �༶ƴ��
					String bjpy = map.get("bjpy");

					if (PY_BIG[i].equalsIgnoreCase(bjpy)) {

						if (bj_num < 12) {//�༶��δ����12��
							
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
								html.append("creatClickedTj('bjNew','�༶','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickBjNewQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");
								
								qt_flag = false;
								qt_num ++;
							
							}
							
						} else {//�༶������12��
							
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
								html.append("creatClickedTj('bjNew','�༶','"+bjdm+"','"+bjmc+"',this);return false;\">");
								html.append(bjmc);
								html.append("</a>");
								
								bj_num++;
								qt_num++;
							}
							
							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickBjNewQt(\'");
								html.append(bjpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");
								
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
	 * @����:������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-14 ����04:18:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjMap
	 * @return
	 * String �������� 
	 * @throws
	 */
	private String createXxmlxHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\"xxmlx_ul\">");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-14 ����04:18:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjMap
	 * @return
	 * String �������� 
	 * @throws
	 */
	private String createXxmxzHtml(HashMap<String, Object> tjMap) {
		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
//		String onclick = ("ld".equalsIgnoreCase(tj))?"clickLd":"clickCs";
//		String ul_id = ("ld".equalsIgnoreCase(tj))?"ld_ul":"ch_ul";
//		String more_id = ("ld".equalsIgnoreCase(tj)) ? "ld_more" : "ch_more";
//		String a_name = ("ld".equalsIgnoreCase(tj)) ? "tj_ld" : "tj_ch"; 
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\"xxmxz_ul\" >");
		
		// �Ƿ���Ҫ����
		boolean moreFlag = false;
		
		if (tjLit != null && tjLit.size() > 0) {	
			
			moreFlag = (tjLit.size()>8)?true :false;
			
			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ��������
				String tjdm = (String) map.get("dm");
				tjdm = Base.isNull(tjdm) ? (String) map.get("en") : tjdm;
				// ��������
				String tjmc = (String) map.get("mc");
				tjmc = Base.isNull(tjmc) ? (String) map.get("cn") : tjmc;
				
				html.append("<li>");
				
				// �Ƿ���ʾ
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
			html.append("showHiddenOther(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}

	/** 
	 * @����:��ʼ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-15 ����10:12:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param searchOptionModel
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public void setPjmcList(SearchForm searchForm, User user,String xzdm)
			throws Exception {

		if(xgxt.utils.String.StringUtils.isNull(xzdm)){
			xzdm = "1";
		}
		
		//���������б�
		List<HashMap<String, Object>> xmlxList = getXmlxList(xzdm);
		searchForm.setXxmlxTjList(xmlxList);
		
		//���������б�
		List<HashMap<String, Object>> xmxzList = getXmxzList();
		searchForm.setXxmxzTjList(xmxzList);
		
		// ������Ŀ�б�
		List<HashMap<String, Object>> pjxmList = getPjxmList(null, new String[]{xzdm});
		searchForm.setXmmcTjList(pjxmList);

	}
	
	
	/**
	 * �����߼���ѯDiv���༶������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private String createXmmcHtml(HashMap<String, Object> tjMap,String stylePath) {
		

		StringBuilder html = new StringBuilder();
		// ����
		String tj = (String) tjMap.get("tj");
		// ��������
		String mc = (String) tjMap.get("mc");
		// �����б�
		List<HashMap<String, Object>> tjLit = (List<HashMap<String, Object>>) tjMap
				.get("tjLit");
		
		html.append("<dl>");
		html.append("<dt>");
		html.append(mc);
		html.append("��</dt>");
		html.append("<dd>");
		html.append("<ul id=\""+tj+"_ul\">");
		
		// �༶��Ŀ
		int xmmc_num = 0;
		// �༶ƴ����Ŀ
		int xmmc_py_num = 0;
		
		if (tjLit != null && tjLit.size() > 0) {	

			for (int j = 0; j < tjLit.size(); j++) {
				HashMap<String, Object> map = tjLit.get(j);
				// ƴ��
				String py = (String) map.get("py");
				// �༶�б�
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
						// �༶����
						String xmdm = xmMap.get("dm");
						// �༶����
						String xmmc = xmMap.get("mc");
						
						if(!"����".equalsIgnoreCase(xmmc)){
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
							html.append("����");
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
			html.append("showHiddenBm(this,'"+tj+"','more_up','more_down','����','����');");
			html.append("return false;\" ");
			html.append(">");
			html.append("����");
			html.append("</a>");
		}
		
		html.append("</dd>");
		html.append("</dl>");
		
		return html.toString();
	}
	
	
	
	/**
	 * ��������Div��������Ŀ��
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
	 * @����:���������Ŀ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-28 ����04:03:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmlx
	 * @param xmxz
	 * @param xmList
	 * @param stylePath
	 * @return
	 * String �������� 
	 * @throws
	 */
	private String creatPjxmHtml(String[] xmlx, String[] xmxz,
			List<HashMap<String, String>> xmList, String stylePath) {

		StringBuilder html = new StringBuilder();

		// �༶��Ŀ
		int xm_num = 0;
		// �༶ƴ����Ŀ
		int xm_py_num = 0;

		if (xmList != null && xmList.size() > 0) {
			
			for (int i = 0; i < PY_BIG.length; i++) {
				
				// �Ƿ�ƴ��
				boolean py_flag = true;
				// �Ƿ����
				boolean over_flag = false;
				// �Ƿ�������
				boolean qt_flag = true;
				// ����������
				int qt_num = 0;
				// ����ID
				String qi_id = "xmmc_qt_" + PY_BIG[i];
				
				for (int j = 0; j < xmList.size(); j++) {
					
					HashMap<String, String> map = xmList.get(j);
					
					// ��Ŀ����
					String xmdm = map.get("xmdm");
					// ��Ŀ����
					String xmmc = map.get("xmmc");
					// ��Ŀƴ��
					String xmpy = map.get("xmpy");

					if (PY_BIG[i].equalsIgnoreCase(xmpy)) {

						if (xm_num < 12) {//�༶��δ����12��
							
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
								html.append("creatClickedTj('xmmc','��Ŀ����','"+xmdm+"','"+xmmc+"',this);return false;\">");
								html.append(xmmc);
								html.append("</a>");
								
								xm_num++;
								qt_num++;
							}
							
							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" onclick=\"clickPjxmQt(\'");
								html.append(xmpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");
								
								qt_flag = false;
								qt_num ++;
							
							}
							
						} else {//�༶������12��
							
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
								html.append("creatClickedTj('xmmc','��Ŀ����','"+xmdm+"','"+xmmc+"',this);return false;\">");
								html.append(xmmc);
								html.append("</a>");
								
								xm_num++;
								qt_num++;
							}
							
							System.out.println(xmmc);
							System.out.println(qt_num);
							// ����
							if (qt_num == 3) {

								html.append("<a href=\"#\" class=\"moreValue_click\" style=\"display:none\" onclick=\"clickPjxmQt(\'");
								html.append(xmpy);
								html.append("\')\"");
								html.append("id=\"");
								html.append(qi_id);
								html.append("\">����</a>");
								
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
