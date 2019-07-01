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
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "xn", "xh", "xm", "nj","bjmc", "dzsj" };
		String[] cn = new String[] { "", "行号", "学年", "学号", "姓名", "年级","班级","调查时间" };
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询家庭情况调查信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJtqkdzList(XszzJtqkdzActionForm model) throws Exception{
		return dao.cxJtqkdzxx(model);
	}
	
	
	/**
	 * 创建页面
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
				// --------------------构建HTML扩展字段与分数除外------------------------
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
					
					if(j==2){//学号隐藏域 2012.12.21 by qlj 
						
						html.append(" <input type='hidden' name='xn_array' value='"+rs[j]+"' > ");
					}else if(j==3){//学年隐藏域
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
	 * 将几种常用的HTML字符转换为转义符
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
	 * 创建结果集
	 * 
	 * @author lt
	 * 
	 * @throws IOException
	 */
	public void createRs(SearchRsModel rsModel, Pages pages,
			HttpServletResponse response) throws IOException {
		
		// 多选框有无
		String checkBox = rsModel.getCheckBox();
		// 多选框有无(结果集)
		String checkBoxRs = rsModel.getCheckBoxRs();
		// 标题
		List<HashMap<String, String>> topTr = rsModel.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = rsModel.getRsArrList();
		// 构建语句
		String spHtml = rsModel.getSpHtml();
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		html.append("<span class=\"formbox\">");
		html.append("<table class=\"dateline\" width=\"100%\" id=\"table_rs\" style=\"\">");
		// =========================标题===========================
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
				
				//排序
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
		// =========================标题 end===========================
		
		// =========================结果集 =========================
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
		
		// 行数
		int rows = 0;

		if (rsArrList != null && rsArrList.size() > 0) {
			rows = rsArrList.size();
		} else if (!Base.isNull(rsModel.getRows())) {
			rows= Integer.parseInt(rsModel.getRows());
		}
		
		int spaceRow = pages.getPageSize();
		
		spaceRow = spaceRow - rows;
		
		String noSpace = rsModel.getNoSpace();
		
		// 补空行
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

					// IE版本
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
		// =========================结果集 end=========================
							
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
	 * 初始化 page、searchModel、user对象
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

		// ==================分页 begin===================
		Pages pages = setPages("", request);
		// ==================分页 end========================

		// ==================高级查询 begin========================
		SearchModel searchModel = setSearchModel(rForm, request);
		// ==================高级查询 end=======================

		// ---------------------将 page、searchModel、user对象放入model中
		// begin-------------------
		model.getClass().getMethod("setPages",
				(Class[]) new Class[] { Pages.class }).invoke(model, pages);

		model.getClass().getMethod("setSearchModel",
				(Class[]) new Class[] { SearchModel.class }).invoke(model,
				searchModel);

		model.getClass().getMethod("setUser",
				(Class[]) new Class[] { User.class }).invoke(model, user);
		// ---------------------将 page、searchModel、user对象放入model中 end-------------------
	}
	
	/**
	 * 学生是否有进行过家庭情况调查
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
	 * 保存家庭情况调查信息
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
	 * 保存家庭成员信息，先删除后增加
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
	 * 修改家庭情况调查信息
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
	 * 删除家庭情况调查信息
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
	 * 查询学生家庭成员信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxJtcyxxList(XszzJtqkdzActionForm model) throws Exception{
		return dao.cxJtcyxxList(model);
	}
	
	/**
	 * 查看单个家庭情况调查信息
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
