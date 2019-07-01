package xsgzgl.xtwh.general.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.SearchUtil;
import com.zfsoft.xgxt.base.util.UniqID;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class NewsManageService extends CommService {
	public final static String _TYPE_TZTG = "-1";
	private NewsManageDao dao = new NewsManageDao();

	public List<HashMap<String, String>> getNewsTop() {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "NEWSID", "NEWSTITLE", "PUBTIME", "PUBER", "FBRBMMC",
				"TOWHO", "SFFB", "SFZD","YYDMD" };
		String[] cn = new String[] { "", "����", "����ʱ��", "������", "��������", "���ն���", "�Ƿ񷢲�",
				"�Ƿ��ö�" ,"���Ķ�����"};

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	// ��������
	public List<String[]> getNewsInfo(NewsManageForm myForm) throws Exception {
		// TODO �Զ����ɷ������
		return dao.getNewsList(myForm);
	}
	
	public List<HashMap<String, String>> getNewsPageList(NewsManageForm model,
			User user) {
		return dao.getNewsPageList(model, user);
	}

	// �����б�
	public boolean delNews(NewsManageForm myForm) throws Exception {
		// TODO �Զ����ɷ������

		return dao.delNews(myForm);
	}

	// ���ŷ���
	public boolean newsFb(NewsManageForm myForm) {
		// TODO �Զ����ɷ������
		return dao.newFb(myForm);
	}

	// ����ȡ������
	public boolean newsQxfb(NewsManageForm myForm) {
		// TODO �Զ����ɷ������
		return dao.newsQxfb(myForm);
	}

	// �ö�
	public boolean newsZd(NewsManageForm myForm) {
		// TODO �Զ����ɷ������
		return dao.newsZd(myForm);
	}

	// ȡ���ö�
	public boolean newsQxzd(NewsManageForm myForm) {
		// TODO �Զ����ɷ������
		return dao.newsQxzd(myForm);
	}

	public void myCreateRs(SearchRsModel rsModel, Pages pages,
			HttpServletResponse response) throws IOException {
		// TODO �Զ����ɷ������
		// ��ѡ������
		String checkBox = rsModel.getCheckBox();
		// ��ѡ������(�����)
		String checkBoxRs = rsModel.getCheckBoxRs();
		// ����
		List<HashMap<String, String>> topTr = rsModel.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = rsModel.getRsArrList();
		// �������
		String spHtml = rsModel.getSpHtml();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<h3 class=\"datetitle_01\">");
		html.append("<span id=\"span_note\" width=\"100%\">");
		html.append("��ѯ���&nbsp;&nbsp;");
		html.append("</span>");
		html.append("</h3>");

		html.append("<span class=\"formbox\">");
		html
				.append("<table class=\"dateline\" width=\"100%\" id=\"table_rs\" style=\"\">");
		// =========================����===========================
		html.append("<thead>");
		html.append("<tr>");

		if ("yes".equalsIgnoreCase(checkBox)) {
			html.append("<td width=\"5px\">");
			
			html.append("<input type=\"checkbox\" id=\"selall\" ");
			html.append("name=\"selall\" onclick=\"selAll()\" />");
		
			html.append("</td>");
		}

		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {

				if (i == 0) {
					html.append("<td width=\"5px\" nowrap=\"nowrap\" ");
				} else {
					html.append("<td style=\"width:"
							+ (100 / (topTr.size() - 1))
							+ "%\"  nowrap=\"nowrap\" ");
				}

				// ����
				String arrange = rsModel.getArrange();
				if ("yes".equalsIgnoreCase(arrange)) {
					html.append("id=\"" + topTr.get(i).get("en") + "\"");
					html.append("onclick=\"arrangeRs(this)\"");
				}
				html.append(">");
				html.append(topTr.get(i).get("cn"));
				html.append("</td>");
			}
		}

		html.append("</tr>");
		html.append("</thead>");
		// =========================���� end===========================

		// =========================����� =========================
		html.append("<tbody>");
		if (!Base.isNull(spHtml)) {
			html.append(spHtml);
		} else {
			if (rsArrList != null && rsArrList.size() > 0) {
				for (int i = 0; i < rsArrList.size(); i++) {
					String[] rs = rsArrList.get(i);
					html
							.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

					if (Base.isNull(checkBoxRs)) {
						html.append("<td align=\"left\" width=\"5px\">");
						html
								.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
						html.append("value=\"" + rs[0] + "\"/>");
						html.append("<input type=\"hidden\" id=\"tablename\" name=\"tablename\" value=\"" + rs[rs.length-1] + "\"/>");
						html.append("</td>");
					}
					html.append("<td >");
					// html.append("<a href=\"xtwh_news.do?method=newsInfo&newsId="+rs[0]+"\"target=\"_blank\" title=\""+rs[1]+"\"");
					html
							.append("<a style=\"width:300px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;float:left;\" href=\"xtwh_news.do?method=newsInfo&newsId="
									+ rs[0]
									+ "\" target=\"_blank\" title=\""
									+ rs[1] + "\">");
					html.append(rs[1]);
					html.append("</a></td>");
					for (int j = 3; j < rs.length-1; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" >");
						if(j==rs.length-2){
							html.append("<a style='color:red' href='javascript:void(0);' value=\""+rs[0]+"\" class='yydmd' onclick='yydmdView(\""+rs[0]+"\",\""+rs[rs.length-1]+"\");'>���Ķ����� </a>");
						}
						else{
						 html.append(rs[j]);
						}
						html.append("</td>");
					}
					html.append("</tr>");
				}
			}
		}

		// ����
		int rows = 0;

		if (rsArrList != null && rsArrList.size() > 0) {
			rows = rsArrList.size();
		} else if (!Base.isNull(rsModel.getRows())) {
			rows = Integer.parseInt(rsModel.getRows());
		}

		int spaceRow = pages.getPageSize();

		spaceRow = spaceRow - rows;

		String noSpace = rsModel.getNoSpace();

		// ������
		if (spaceRow != 0 && !"no".equalsIgnoreCase(noSpace)) {

			for (int i = 0; i < spaceRow; i++) {
				html.append("<tr>");

				if ("yes".equalsIgnoreCase(checkBox)) {
					html.append("<td width=\"5px\">");
					// html.append("<input type=\"checkbox\" id=\"selall\" ");
					// html.append("name=\"selall\" onclick=\"selAll()\" />");
					html.append("</td>");
				}

				if (topTr != null && topTr.size() > 0) {

					// IE�汾
					String ie = rsModel.getIe();

					for (int j = 0; j < topTr.size(); j++) {

						html.append("<td width=\"5px\"");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}

						html.append(">");
						html.append("&nbsp;");
						html.append("</td>");
					}
				}

				html.append("</tr>");
			}
		}

		html.append("</tbody>");
		// =========================����� end=========================

		html.append("</table>");
		html.append("</span>");
		html
				.append("<script language=\"javascript\">jQuery(\"#div_rs\").height(jQuery(\"#table_rs\").height()+50);</script>");
		html.append("<input type=\"hidden\" id=\"ajax_max_record\" value=\""
				+ pages.getMaxRecord() + "\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_page\" value=\""
				+ pages.getMaxPage() + "\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_current\" value=\""
				+ pages.getCurrentPage() + "\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_size\" value=\""
				+ pages.getPageSize() + "\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_maxpage\" value=\""
				+ pages.getMaxPage() + "\"/>");

		response.getWriter().print(html.toString());

	}

	public void newsInfo(HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		dao.newsIfo(request);
	}

	public void newsUpdate(HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		dao.newsUpdate(request);

	}

	/**
	 * �����޸Ľ��
	 * 
	 * @param request
	 * @author MJ
	 */
	public boolean updateNews(HttpServletRequest request) throws Exception {
		boolean b = dao.updateNews(request);
		String toWho = request.getParameter("toWho");
		String newsId = request.getParameter("newsId");
		SearchUtil su=SearchUtil.getInstance();
		if(b && toWho.contains("some")){
			String qxfwid = request.getParameter("qxfwid");
			if(StringUtils.isNotNull(qxfwid)){
				b = su.updateSearchModel(request, newsId);
			}else{
				b = su.saveSearchModel(request, newsId);
			}
		}else{
			su.deleteSearch(request, newsId);
		}
		return b;
	}

	/**
	 * ������ӽ��
	 * 
	 * @param request
	 * @author MJ
	 */
	public boolean saveNews(HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		String tableName=null;
		String newsId = UniqID.getInstance().getUniqIDHash();
		request.setAttribute("newsId", newsId);
		List<HashMap<String,String>> tableList=dao.getTableList();
		HashMap<String,String> tableMap=dao.getTableName(tableList);
		if(null==tableMap||"".equals(tableMap.get("table_name"))||null==tableMap.get("table_name")){
			tableName="xg_tzgglljlb_1";
		}else if(Integer.parseInt(tableMap.get("con"))>100000&&dao.getTzggCount(tableMap.get("table_name"))){
			tableName=tableMap.get("table_name").substring(0,tableMap.get("table_name").length()-1)+(Integer.parseInt(tableMap.get("table_name").substring(tableMap.get("table_name").length()-1))+tableList.size());
			dao.createIableInfo(tableName);
		}else{
			tableName=tableMap.get("table_name");
		}
		request.setAttribute("tableName", tableName);
		boolean b = dao.saveNews(request);
		String toWho = request.getParameter("toWho");
		if(b && toWho.contains("some")){
			SearchUtil su=SearchUtil.getInstance();
			b = su.saveSearchModel(request, newsId);
		}
		return b;
	}

	/**
	 * ��ѯ�����б�������ҳ����
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getNewsList(NewsManageForm model,
			User user) {
		int size = model.getPages().getPageSize();
		List<HashMap<String, String>> listNew=new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> list = dao.getNewsList(model,user);
		for(HashMap<String, String> hm:list){
			String newsId=hm.get("newsid");
			String toWho=hm.get("towho");
			if(SearchUtil.getInstance().isHaveQx(user, newsId, toWho)){
				listNew.add(hm);
			}
		}
		size = size - listNew.size();
		for (int i = 0; i < size; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("newsid", " ");
			map.put("newstitle", " ");
			map.put("title", " ");
			map.put("SFZD", " ");
			map.put("newstitle", " ");
			map.put("pubtime", " ");
			map.put("sfnew", " ");
			listNew.add(map);
		}
		return listNew;
	}
	
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-18 ����10:20:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getNewsListType() {
		List<HashMap<String, String>> list = dao.getNewsListType();
		//ΪӦ���ϰ汾û�����ͱ�����
		if(null==list||list.size()<=0){
			list=new ArrayList<HashMap<String,String>>();
		}
		HashMap<String, String> map = new HashMap<String, String>();
		// ����ԭϵͳĬ��֪ͨͨ������
		map.put("typename", "֪ͨͨ��");
		map.put("typeid", _TYPE_TZTG);//
		list.add(map);
		Collections.reverse(list);
		return list;
	}
	public List<HashMap<String, String>> getYydryList(NewsManageForm model) throws Exception {
		YydmdDao yydmdDao = new YydmdDao();
		return yydmdDao.getPageList(model);
	}
}
