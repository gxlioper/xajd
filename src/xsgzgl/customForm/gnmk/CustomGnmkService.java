package xsgzgl.customForm.gnmk;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.jygl.njjs.NjjsJyglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xsgzgl.customForm.model.TableContentModel;
import xsgzgl.customForm.model.TableModel;
import xsgzgl.customForm.table.CustomTableDAO;
import xsgzgl.customForm.table.CustomTableForm;
import xsgzgl.customForm.table.CustomTableService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.rcsw.qjgl.RcswQjglForm;
import xsgzgl.xsxx.cssz.grxx.XsxxCsszService;
import xsgzgl.xsxx.grxx.XsxxGrxxForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����_Service��
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

public class CustomGnmkService extends CommService {

	CustomGnmkDAO dao = new CustomGnmkDAO();

	// ===================��ѯ================================
	
	/**
	 * ��ý����(�Զ��幦��ģ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getGnmkManageList(CustomGnmkForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ��ѯ�����б�
		List<HashMap<String, String>> searchContentList = model
				.getSearchContentList();
		// ��ѯ�б�
		List<HashMap<String, String>> list = dao.getGnmkManageList(model, user);

		ArrayList<String[]> reslutList = null;
		
		if (list != null && list.size() > 0) {

			int column_num = list.size() / searchContentList.size();

			for (int i = 0; i < column_num; i++) {

				String[] value = new String[searchContentList.size()];

				// ������
				int count = 0;

				for (int j = (i * searchContentList.size()); j < list.size(); j++) {

					HashMap<String, String> rsMap = list.get(j);
					// �ֶ�ֵ
					String zdz = rsMap.get("zdz");

					value[count] = zdz;
					count++;
				}
				
				reslutList.add(value);
			}
		}

		return reslutList;
	}
	
	/**
	 * �Զ��幦�ܹ���Html
	 * 
	 * @author ΰ�����
	 */
	public String getGnmkManageHtml(SearchRsModel rsModel,
			CustomGnmkForm model, ArrayList<String[]> rsArrList, User user) {

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String id = rs[0];

				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 1; j < rs.length ; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					spHtml.append(">");

					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}		
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	// ===================��ѯ end================================

	// ===================��ϸ================================
	
	/**
	 * ��ù���ģ����ϸHtml
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public void createGnmkDetail(CustomGnmkForm model, 
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// ����ģ�����
		String gnmkdm = model.getGnmkdm();
		// ����ģ��ID
		String gnmk_id = getOneValue("xg_custom_gnmkb", "id", "gnmkdm", gnmkdm);

		CustomTableDAO tableDAO = new CustomTableDAO();
		
		List<HashMap<String, String>> tableList = tableDAO
				.getTableList(gnmk_id);
		List<HashMap<String, String>> tableContentList = tableDAO
				.getTableContentList(gnmk_id);
		
		StringBuilder html = new StringBuilder();
		
		//ϵͳʱ��
		String nowTime = getNowTime("YYYY��MM��DD��");
		String nowTimeMc = getNowTime("YYYYMMDD");
		
		if(tableList!=null && tableList.size()>0){
			
			for(int i=0;i<tableList.size();i++){
				
				HashMap<String, String> tableMap = tableList.get(i);
				
				//������
				String table_num = String.valueOf(i);
				// ����
				String title = tableMap.get("title");
				// ����
				String row = tableMap.get("row_all");
				// ��ID
				String table_id = tableMap.get("table_id");
				
				//��ͷ
				html.append("<table id=\"table_"+table_num+"\" class=\"formlist\" width=\"100%\">");
				html.append("<thead>");
				html.append("<tr>");
				html.append("<td colspan=\"4\">");
				html.append("<span>");
				html.append(title);	
				html.append("</span>");
				html.append("</td>");
				html.append("</tr>");
				html.append("</thead>");
				
				// ===============����================
				html.append("<tbody>");

				for (int j = 1; j <= Integer.parseInt(row); j++) {

					html.append("<tr>");

					if (tableContentList != null && tableContentList.size() > 0) {

						for (int k = 0; k < tableContentList.size(); k++) {

							HashMap<String, String> contentMap = tableContentList
									.get(k);

							// ����ID
							String id = contentMap.get("id");
							// ��ID
							String tableContent_id = contentMap.get("table_id");
							// ����
							String row_num = contentMap.get("row_num");
							// ����
							String column_num = contentMap.get("column_num");
							// ����
							String content = contentMap.get("content");
							// ���
							String width = contentMap.get("width");
							// �ı�������
							String textarea_rows = contentMap
									.get("textarea_rows");
							// ��׺
							String postfix = contentMap.get("postfix");
							// ����Դ��
							String source_table = contentMap
									.get("source_table");
							// �����
							String select_dm = contentMap.get("select_dm");
							// ������
							String select_mc = contentMap.get("select_mc");
							// �ϲ���Ԫ��
							String colspan = contentMap.get("colspan");
							// ��֤
							String checked = contentMap.get("checked");

							StringBuilder pHtml = new StringBuilder();
							
							// λ��
							String position = column_num.replace("_", "_" + i
									+ "_" + j + "_");
							
							if("xh".equalsIgnoreCase(content)){					
								pHtml.append("<input type=\"text\" id=\"input_xh\" style=\"width:100px\"/>");
								pHtml.append("<button type=\"button\" class=\"btn_01\" onclick=\"showChooseDiv();return false;\">ѡ��</button>");
							}else if("zgh".equalsIgnoreCase(content)){
								pHtml.append("<input type=\"text\" id=\"zgh\" style=\"width:80px\"/>");
								pHtml.append("<button type=\"button\"  onclick=\"alertInfo('����󽫿�ѡȡ��ʦ');return false;\" class=\"btn_01\">ѡ��</button>");
							}else if("xm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_xm\"></span>");
							}else if("xb".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_xb\"></span>");
							}else if("nj".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_nj\"></span>");
							}else if("xydm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_xydm\"></span>");
							}else if("zydm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_zydm\"></span>");
							}else if("bjdm".equalsIgnoreCase(content)){
								pHtml.append("<span id=\"input_bjdm\"></span>");
							}else if("text".equalsIgnoreCase(content)){
								String input_id = "input_"+position;
								pHtml.append("<input type=\"text\" id=\""+input_id+"\" style=\"width:"+width+"\"/>");
								if(!Base.isNull(postfix)){
									pHtml.append(postfix);
								}
							}else if("select".equalsIgnoreCase(content)){
								
								String select_id = "select_"+position;
								
								pHtml.append("<select id=\""+select_id+"\" style=\"width:"+width+"px\">");
								pHtml.append("<option value=\"\"></option>");
								
								if(Base.isNull(source_table.trim())){
									String[] dm = select_dm.split("!!luojw!!");
									String[] mc = select_mc.split("!!luojw!!");
									
									for(int m=0;m<dm.length;m++){			
										pHtml.append("<option value=\""+dm[m]+"\">"+mc[m]+"</option>");
									}
								} else {

									CustomTableService tableService = new CustomTableService();

									List<HashMap<String, String>> optionList = tableService
											.getListBySource(source_table,
													select_dm, select_mc, null);

									if (optionList != null
											&& optionList.size() > 0) {

										for (int m = 0; m < optionList.size(); m++) {
											String dm = optionList.get(m).get(
													"dm");
											String mc = optionList.get(m).get(
													"mc");

											pHtml.append("<option value=\""
													+ dm + "\">" + mc
													+ "</option>");
										}
									}
								}
								
								pHtml.append("</select>");
							}else if("nowTime".equalsIgnoreCase(content)){//ϵͳʱ��
								String nowTime_id = "nowTime_"+position;
								pHtml.append(nowTime);
								pHtml.append("<input type=\"hidden\" id=\""+nowTime_id+"\" style=\"\" value=\""+nowTimeMc+"\"/>");
							}else if("calendar".equalsIgnoreCase(content)){//���ڸ�ʽ
								String time_id = "time_"+position;
								pHtml.append("<input type=\"text\" id=\""+time_id+"\" style=\"width:150px\"");
								pHtml.append(" onclick=\"return showCalendar(this.id,'ymmdd');\"/>");
							}else if("textarea".equalsIgnoreCase(content)){
								String textarea_id = "textarea_"+position;
								pHtml.append("<textarea type=\"text\" id=\""+textarea_id+"\" style=\"width:"+width+"\" rows=\""+textarea_rows+"\"></textarea>");
							}else{
								pHtml.append(content);
							}
							
							if (tableContent_id.equalsIgnoreCase(table_id)
									&& row_num.equalsIgnoreCase(String
											.valueOf(j))) {

								boolean noColspan = "colspan".equalsIgnoreCase(content) ? false : true;
								
								if("1".equalsIgnoreCase(column_num) && noColspan){
									//��һ
									html.append("<th id=\"th_"+table_num+"_"+j+"_left\" style=\"width:20%;height:25px;\">");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</th>");
								}else if("2".equalsIgnoreCase(column_num) && noColspan){	
									String td_width = Base.isNull(colspan) ? "30%" : "80%";	
									// ���
									html.append("<td id=\"td_"+table_num+"_"+j+"_left\" style=\"width:"+td_width+";height:25px;\" colspan=\""+colspan+"\">");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</td>");
								}else if("3".equalsIgnoreCase(column_num) && noColspan){
									// ��һ
									html.append("<th id=\"th_"+table_num+"_"+j+"_right\" style=\"width:20%;height:25px;\" >");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</th>");
								}else if("4".equalsIgnoreCase(column_num) && noColspan){
									// �Ҷ�
									html.append("<td id=\"td_"+table_num+"_"+j+"_right\" style=\"width:30%;height:25px;\" >");
									html.append("<p id=\""+id+"\">&nbsp;"+pHtml+"</p>");
									html.append("</td>");
								}
							}
						}
					}

					html.append("</tr>");

				}

				html.append("</tbody>");
				// ===============���� end============
				
				html.append("</table>");
			}
		}

		response.getWriter().print(html.toString());
	}
	
	/**
	 * ���ѧ����Ϣ
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public void getXsInfo(CustomGnmkForm model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		List<HashMap<String, String>> xsList = null;

		try {
			xsList = dao.getXsInfoList(model, user);
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

		StringBuilder html = new StringBuilder();

		// ��ͷ
		html.append("<table class=\"formlist\" width=\"100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		html.append("<span>");
		html.append("ѧ����Ϣ");
		html.append("</span>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");

		// ===============����================
		html.append("<tbody>");
		
		if (xsList != null && xsList.size() > 0) {
			
			html.append("<tr>");
			html.append("<td style=\"width:10%\">���</td>");
			html.append("<td style=\"width:45%\">ѧ��</td>");
			html.append("<td style=\"width:45%\">����</td>");
			html.append("</tr>");
			
			for (int i = 0; i < xsList.size(); i++) {

				HashMap<String, String> xsInfo = xsList.get(i);

				String num = xsInfo.get("num");// ���
				String xh = xsInfo.get("xh");// ѧ��
				String xm = xsInfo.get("xm");// ����
				String xb = xsInfo.get("xb");// �Ա�
				String nj = xsInfo.get("nj");// �꼶
				String xymc = xsInfo.get("xymc");// ѧԺ
				String zymc = xsInfo.get("zymc");// רҵ
				String bjmc = xsInfo.get("bjmc");// �༶

				StringBuilder title = new StringBuilder();
				title.append("�Ա�");
				title.append(xb);
				title.append("	�꼶��");
				title.append(nj);
				title.append("	"+Base.YXPZXY_KEY+"��");
				title.append(xymc);
				title.append("	רҵ��");
				title.append(zymc);
				title.append("	�༶��");
				title.append(bjmc);
				
				html.append("<tr ");
				html.append("title=\"" + title + "\" ");
				html.append("onclick=\"rowOnClick(this);\" "); 
				html.append("ondblclick=\"chooseXs('"+xh+"')\" ");
				html.append("style=\"cursor:hand\"");
				html.append(">");
				
				html.append("<td>");
				html.append(num);
				html.append("</td>");

				html.append("<td>");
				html.append(xh);
				html.append("</td>");

				html.append("<td>");
				html.append(xm);
				html.append("</td>");

				html.append("</tr>");
			}
		}
		
		html.append("</tbody>");
		// ===============���� end============

		// ===============��ť================
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		html.append("<div class=\"btn\">");
		
		Pages page = model.getPages();
		int maxPage = page.getMaxPage();
		int currentPage = page.getCurrentPage();
		
		html.append("<input type=\"hidden\" id=\"max_xs_num\" value=\""+maxPage+"\">");
		
		if (currentPage != 1) {
			html.append("<button type=\"button\"  onclick=\"prePage();return false;\">");
			html.append("��һҳ");
			html.append("</button>");
		}

		if (currentPage != maxPage) {
			html.append("<button type=\"button\"  onclick=\"nextPage();return false;\">");
			html.append("��һҳ");
			html.append("</button>");
		}
		
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ===============��ť end============
		
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * ���湦��ģ��
	 * 
	 * @author ΰ�����
	 * 
	 */
	public boolean saveGnmk(CustomGnmkForm model, User user) {

		// ��ʼ������ģ��Model
		initGnmkModel(model, user);
		// ����ģ�����
		String gnmkdm = model.getGnmkdm();

		// ������Ŀ��
		String tableName = model.getXmb();
		// ����
		String pk = "gnmkdm||pk";
		// ����ֵ
		String[] pkValue = new String[] { gnmkdm + model.getPk() };
		// �����ֶ�
		String[] arrzd = new String[] { "zd", "zdz" };
		// ��һ�ֶ�
		String[] onezd = new String[] { "gnmkdm", "pk" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		model.getZdz().hashCode();
		
		boolean flag = false;

		try {
			flag = saveInfoToDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ��ʼ������ģ��Model
	 * 
	 * @author ΰ�����
	 * 
	 */
	private void initGnmkModel(CustomGnmkForm model, User user) {

		// �����б�
		List<HashMap<String, String>> pkList = dao.getGnmkPkList(model, user);

		// �ֶ�
		String[] zd = model.getZd();
		// �ֶ�ֵ
		String[] zdz = model.getZdz();

		if (zdz != null && zdz.length > 0) {
			List<String> valueList = new ArrayList<String>();
			for (int i = 0; i < zdz.length; i++) {
				valueList.add(unicode2Gbk(zdz[i]).trim());
			}
			model.setZdz(valueList.toArray(new String[] {}));
		}
		
		// ����
		String pk = "";

		if (pkList != null && pkList.size() > 0) {
			for (int i = 0; i < pkList.size(); i++) {
				HashMap<String, String> map = pkList.get(i);
				// ����ID
				String pk_id = map.get("pk_id");

				for (int j = 0; j < zd.length; j++) {

					if (pk_id.equalsIgnoreCase(zd[j])) {
						pk += zd[j] + zdz[j];
						;
					}
				}
			}
		}
		
		model.setPk(pk);
	}

	// ===================��ϸ end=============================
}