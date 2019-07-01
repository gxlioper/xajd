package xsgzgl.xszz.jhzy.jtqkdz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class XszzJtqkdzService extends CommService {
	
	private XszzJtqkdzDao dao = new XszzJtqkdzDao();

	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "xn", "xh", "xm", "nj","bjmc", "dzsj" };
		String[] cn = new String[] { "", "�к�", "ѧ��", "ѧ��", "����", "�꼶","�༶","����ʱ��" };
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯ��ͥ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJtqkdzList(XszzJtqkdzActionForm model) throws Exception{
		return dao.cxJtqkdzxx(model);
	}
	
	
	/**
	 * ����ҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					
					if(j==2){//ѧ�������� 2012.12.21 by qlj 
						
						html.append(" <input type='hidden' name='xn_array' value='"+rs[j]+"' > ");
					}else if(j==3){//ѧ��������
						html.append(" <input type='hidden' name='xh_array' value='"+rs[j]+"' > ");
					}
					
					
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * �����ֳ��õ�HTML�ַ�ת��Ϊת���
	 * @param html
	 * @return
	 */
	public String replaceHtml(String html){
		
		if(!Base.isNull(html)){
			
			html=html.replaceAll("\"", "&quot;");
			
			html=html.replaceAll("&", "&amp;");
			
			html=html.replaceAll("<", "&lt;");
			
			html=html.replaceAll(">", "&gt;");
			
		}
		return html;
	}
	
	/**
	 * ���������
	 * 
	 * @author lt
	 * 
	 * @throws IOException
	 */
	public void createRs(SearchRsModel rsModel, Pages pages,
			HttpServletResponse response) throws IOException {
		
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
		html.append("<span class=\"formbox\">");
		html.append("<table class=\"dateline\" width=\"100%\" id=\"table_rs\" style=\"\">");
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
				html.append("<td width=\"5px\" nowrap=\"nowrap\" ");
				
				//����
				String arrange = rsModel.getArrange();
				if("yes".equalsIgnoreCase(arrange)){
					html.append("id=\""+topTr.get(i).get("en")+"\"");
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
					html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

					if (Base.isNull(checkBoxRs)) {
						html.append("<td align=\"left\" width=\"5px\">");
						html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
						html.append("value=\"" + rs[0] + "\"/>");
						html.append("</td>");
					}

					for (int j = 1; j < rs.length; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" >");
						html.append(rs[j]);
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
			rows= Integer.parseInt(rsModel.getRows());
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
//					html.append("<input type=\"checkbox\" id=\"selall\" ");
//					html.append("name=\"selall\" onclick=\"selAll()\" />");
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
		html.append("<input type=\"hidden\" id=\"ajax_max_record\" value=\""+pages.getMaxRecord()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_page\" value=\""+pages.getMaxPage()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_current\" value=\""+pages.getCurrentPage()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_size\" value=\""+pages.getPageSize()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_maxpage\" value=\""+pages.getMaxPage()+"\"/>");
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * ��ʼ�� page��searchModel��user����
	 * 
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void commInit(RequestForm rForm, Object model,
			HttpServletRequest request, User user) throws SecurityException,
			IllegalArgumentException, UnsupportedEncodingException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		// ==================��ҳ begin===================
		Pages pages = setPages("", request);
		// ==================��ҳ end========================

		// ==================�߼���ѯ begin========================
		SearchModel searchModel = setSearchModel(rForm, request);
		// ==================�߼���ѯ end=======================

		// ---------------------�� page��searchModel��user�������model��
		// begin-------------------
		model.getClass().getMethod("setPages",
				(Class[]) new Class[] { Pages.class }).invoke(model, pages);

		model.getClass().getMethod("setSearchModel",
				(Class[]) new Class[] { SearchModel.class }).invoke(model,
				searchModel);

		model.getClass().getMethod("setUser",
				(Class[]) new Class[] { User.class }).invoke(model, user);
		// ---------------------�� page��searchModel��user�������model�� end-------------------
	}
	
	/**
	 * ѧ���Ƿ��н��й���ͥ�������
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean sfJtqkdz(String xh) throws Exception {
		XszzJtqkdzActionForm model = new XszzJtqkdzActionForm();
		model.setXh(xh);
		HashMap<String, String> rs = dao.ckJtqkdzxx(model);
		if (rs != null && !rs.isEmpty() && rs.get("xh") != null) {
			return true;
		} 
		return false;
	}
	
	/**
	 * �����ͥ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		boolean result = false;
		HashMap<String, String> rs = ckJtqkdzxx(model);
		if (rs != null && rs.get("xh") != null) {
			result = xgJtqkdzxx(model);
		} else {
			result = dao.bcJtqkdzxx(model);
			if (result) {
				result = bcJtcyxx(model);
			}
		}
		return result;
	}

	/**
	 * �����ͥ��Ա��Ϣ����ɾ��������
	 * @param model
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	private boolean bcJtcyxx(XszzJtqkdzActionForm model) throws Exception,
			SQLException {
		boolean result;
		result = dao.scJtcyxx(model);
		if (result) {
			String[] array = new String[model.getCyxmArr().length];
			for (int i=0;i<array.length;i++) {
				if (model.getCyxmArr() != null && StringUtils.isNotNull(model.getCyxmArr()[i])) {
					array[i] = "insert into xg_xszz_jhzy_jtcyqkb (xh,cyxm,cynl,cygx,cygzxxdw,cyzy,cylxdh," +
					"cynsr,cyjkzk) values ('"+model.getXh()+"','"+model.getCyxmArr()[i]+"','"+model.getCynlArr()[i]+"'," +
							"'"+model.getCygxArr()[i]+"','"+model.getCygzxxdwArr()[i]+"','"+model.getCyzyArr()[i]+"'," +
									"'"+model.getCylxdhArr()[i]+"','"+model.getCynsrArr()[i]+"','"+model.getCyjkzkArr()[i]+"')";
				}
			}
			DAO mydao = DAO.getInstance();
			result = mydao.checkBatch(mydao.runBatch(array));
		}
		return result;
	}
	/**
	 * �޸ļ�ͥ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		boolean result = dao.xgJtqkdzxx(model);
		if (result) {
			result = bcJtcyxx(model);
		}
		return result;
	}
	
	/**
	 * ɾ����ͥ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean scJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		if (StringUtils.isNotNull(model.getPkStr())) {
			List<String[]> params = new ArrayList<String[]>();	
			List<String[]> xhList = new ArrayList<String[]>();
			for(String str : model.getPkStr().split("!!@@!!")){
				if (StringUtils.isNull(str)) {continue;}
				else {
				params.add(new String[]{str});
				xhList.add(new String[]{str.substring(0, str.length()-9)});
				}
			}
			boolean result = dao.scJtqkdzxx(params);
			if (result) {
				result = dao.scJtcyxx(xhList);
			}
			return result;
		}
		return false;
		
	}
	
	/**
	 * ��ѯѧ����ͥ��Ա��Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxJtcyxxList(XszzJtqkdzActionForm model) throws Exception{
		return dao.cxJtcyxxList(model);
	}
	
	/**
	 * �鿴������ͥ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ckJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		if (StringUtils.isNull(model.getPkStr())) {
			model.setPkStr(model.getXh()+model.getXn());
		}
		HashMap<String, String> rs = dao.ckJtqkdzxx(model);
		
		if (rs != null && rs.get("szssx")!=null && rs.get("szssx").length() ==22) {
			rs.put("syshen", rs.get("szssx").substring(0, 6));
			rs.put("syshi", rs.get("szssx").substring(8, 14));
			rs.put("syxian", rs.get("szssx").substring(16, 22));
		}
		return rs;
	}
}
