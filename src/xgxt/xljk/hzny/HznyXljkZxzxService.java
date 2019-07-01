package xgxt.xljk.hzny;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-4 下午04:35:02</p>
 */
public class HznyXljkZxzxService extends BasicService{

	HznyXljkZxzxDAO dao=new HznyXljkZxzxDAO();
	
	/**
	 * 获取咨询师管理查询列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxsglList(BasicModel model) throws Exception{
		
		return dao.getZxsglList(model);
	}
	
	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createZxsglHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < 6; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					
					if(j==5){
						html.append(" title=\""+rs[6]+"\"");
					}
					
					html.append(">");
					
					html.append(replaceHtml(rs[j]));

					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}
	
	
	/**
	 * 获取咨询师管理查询列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getTsxsglList(BasicModel model) throws Exception{
		
		return dao.getTsxsglList(model);
	}
	
	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-1)+"%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					
//					if(j==rs.length-1 && rs[j].equalsIgnoreCase("详细")){
//					
//						html.append("<a href=\"#\" onclick=\"showDetailDiv('"+rs[0]+"');return false;\" ><font color=\"blue\">详细</font></a>");
//					
//					}else{
						
						html.append(replaceHtml(rs[j]));
//					}
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getTeaInfo(String zgh){
		
		
		return dao.getTeaInfo(zgh);
	}
	
	/**
	 * 获取需要特别关心学生类别信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getTbgxxslb(){
		
		return dao.getTbgxxslb();
	}
	
	
	/**
	 * 获取特殊学生信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getTsxsInfo(String xh){
		
		return dao.getTsxsInfo(xh);
	}

	/**
	 * 获取在线咨询信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxzxglList(BasicModel model) throws Exception{
		
		return dao.getZxzxglList(model);
	}
	

	/**
	 * 构建结果集(在线咨询)
	 * 
	 * @author qlj
	 */
	public String createZxzxHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();
		
		String userType=user.getUserType();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				if("stu".equalsIgnoreCase(userType)){
					for (int j = 1; j < 6; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						
						if(j==4){
							html.append(" title=\""+rs[6]+"\"");
						}
						
						html.append(">");
						
						html.append(replaceHtml(rs[j]));
	
						html.append("</td>");
					}
				}else{
					
					for (int j = 1; j < 9; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						
						if(j==6){
							html.append(" title=\""+rs[9]+"\"");
						}
						
						html.append(">");
						
						html.append(replaceHtml(rs[j]));
	
						html.append("</td>");
					}
					
				}
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}
	
	/**
	 * 获取学生在线咨询信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getZxzxInfo(String guid){
		
		return dao.getZxzxInfo(guid);
	}
	
	/**
	 * 获取学生在线咨询信息
	 * @param model
	 * @return
	 */
	public boolean checkZxzx(String guid){
		
		Map<String,String>zxzxInfo=dao.getZxzxInfo(guid);

		if(zxzxInfo==null || zxzxInfo.size()==0){
			
			return false;
		
		}else if(!Base.isNull(zxzxInfo.get("zgh"))){
				
			return false;	
		}
		
		return true;
	}

	/**
	 * 获取学生在线咨询信息
	 * @param model
	 * @return
	 */
	public boolean checkZxzx(String[] guid){
		
		List<HashMap<String,String>>zxzxInfo=dao.getZxzxInfo(guid);

		if(zxzxInfo==null || zxzxInfo.size()==0){
			
			return true;
		
		}
		return false;
	}
	
	/**
	 * 判断用户是否是心理咨询师
	 * @param model
	 * @return
	 */
	public boolean checkZxs(String zgh){
		
		HashMap<String,String>teaInfo=dao.getTeaInfo(zgh);

		if(teaInfo==null || teaInfo.size()==0){
			
			return false;
		
		}
		return true;
	}
	
	/**
	 * 获取在线咨询信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXszxList(BasicModel model) throws Exception{
		
		return dao.getXszxList(model);
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getXlzxsInfo(String zgh){
		
	
		return dao.getXlzxsInfo(zgh);
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getXszxInfo(String guid){
		
		return dao.getXszxInfo(guid);
	}
	
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public boolean checkXszx(String guid){
		
		Map<String,String>xszxInfo= dao.getXszxInfo(guid);
		
		if(xszxInfo==null || xszxInfo.size()==0){
			
			return false;
			
		}else if(!Base.isNull(xszxInfo.get("fksj"))){
			
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 获取学生在线咨询信息
	 * @param model
	 * @return
	 */
	public boolean checkXszx(String[] guid){
		
		List<HashMap<String,String>>xszxInfo=dao.getXszxInfo(guid);

		if(xszxInfo==null || xszxInfo.size()==0){
			
			return true;
		
		}
		return false;
	}

	/**
	 * 获取咨询师管理查询列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZxjlList(BasicModel model) throws Exception{
		
		return dao.getZxjlList(model);
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getZxjlInfo(String guid){
		
		return dao.getZxjlInfo(guid);
	}
}
