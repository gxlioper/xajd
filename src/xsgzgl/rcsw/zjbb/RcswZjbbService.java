package xsgzgl.rcsw.zjbb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.utils.StringUtil;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

public class RcswZjbbService extends BasicService{
	
	RcswZjbbDAO dao=new RcswZjbbDAO();
	
	// ----------------------证件补办设置 begin--------------------------
	/**
	 * 证件补办设置结果集
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbszList(RcswZjbbForm myForm,BasicModel model) throws Exception{
		
		return dao.getBbszList(myForm,model);
	}
	
	
	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createBbszSearchHTML(SearchRsModel rsModel,
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
				if("否".equalsIgnoreCase(rs[rs.length-1])){
					html.append(" disabled ");
				}
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
					
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-2)+"%\" ");
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
	
	// ----------------------证件补办设置 end--------------------------
	
	
	// ----------------------证件补办审核 begin--------------------------	
	/**
	 * 证件补办审核结果集
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbshList(BasicModel model) throws Exception{
	
		return dao.getBbshList(model);
	}
	
	/**
	 * 构建补办审核结果集
	 * @author qlj
	 */
	public String createBbshSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();
		
		String stylePath=rsModel.getStylePath();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" "+rs[rs.length-1]+" ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-1)+"%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					
					html.append(replaceHtml(rs[j]));

					html.append("</td>");
				}
				
				if("未审核".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("通过".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("不通过".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("退回".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("需重审".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}
	
	/**
	 * 构建补办审核结果集
	 * @author qlj
	 */
	public String createBbsqSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();
		
		String stylePath=rsModel.getStylePath();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				if("wsh".equalsIgnoreCase(rs[rs.length-1]) || "wxsh".equalsIgnoreCase(rs[rs.length-1])){
					
				}else{
					html.append(" disabled ");
				}
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-2)+"%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
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
	// ----------------------证件补办审核 begin--------------------------	
	
	
	
	
	
	// ----------------------证件补办结果查询 begin--------------------------	
	/**
	 * 证件补办申请结果集
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbsqList(BasicModel model) throws Exception{
		
		return dao.getBbsqList(model);
	}
	// ----------------------证件补办结果查询 end --------------------------		
	/**
	 * 证件补办结果集
	 * @auther qlj
	 * @throws Exception
	 */
	public List<String[]>getBbjgList(BasicModel model) throws Exception{
		
		return dao.getBbjgList(model);
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
	 * 显示本用户岗位切换模式窗口
	 * @author qlj
	 * @throws IOException 
	 */
	public String showZjbbDiv(User user,
			HttpServletResponse response,String option,BasicModel model) throws IOException {

		XtwhShlcService service =new XtwhShlcService();
		
		response.setContentType("text/html;charset=gbk");
		
		List<HashMap<String,String>>shlcList=service.getShlcByDjlx("rcsw");
		
		
		String zjmc="";
		String lcid="";
		String id="";
		HashMap<String,String>viewMap=viewInfo(model);
		if(viewMap!=null && viewMap.size()>0){
			zjmc=viewMap.get("zjmc");
			lcid=viewMap.get("lcid");
			id=viewMap.get("id");
		}
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		
		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>证件补办设置</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		
		// 证件名称
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("<font color=\"red\">*</font>证件名称");
		html.append("</th>");
		
		html.append("<td width='25%'>");
		html.append("<input type=\"text\" name=\"zjmc\" id=\"zjmc\" ");
		html.append("value=\""+zjmc+"\" maxlength=\"20\"/>");
		html.append("</td>");
		html.append("</tr>");
		
		// 审核流程
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("<font color=\"red\">*</font>审核流程");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");
		//html.append(" <input type=\"hidden\" name=\"text_xmdm\" id=\"text_xmdm\" value=\""+model.getXmdm()+"\" /> ");
		html.append("<div style=\"width:100%;height:130px;overflow-x:hidden;overflow-y:auto;\">");
		html.append(" <input type=\"radio\" name=\"lcid\" ");
		if("no".equalsIgnoreCase(lcid) || Base.isNull(lcid)){
			html.append("  checked=\"true\" ");
		}
		//html.append(" id=\"lcid_0\" value=\"no\" />无<br/>");
		for(int i=0;i<shlcList.size();i++){
			
			HashMap<String,String>shlcMap=shlcList.get(i);
			html.append(" <input type=\"radio\" name=\"lcid\" ");
			if(lcid.equalsIgnoreCase(shlcMap.get("splc"))){
				html.append(" checked=\"true\" ");
			}
			html.append(" id=\"lcid_"+(i+1)+"\" value=\""+shlcMap.get("splc")+"\">");
			html.append(shlcMap.get("lcxx") );
			html.append("<br/>");
		}
		html.append("<div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		if("add".equalsIgnoreCase(option)){
			
			html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"saveBbsz();return false;\">");
		}else{
			
			html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"modiBbsz('"+id+"');return false;\">");
		}
		html.append("确 定");
		html.append("</button>");

		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"iFClose();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");

		html.append("</table>");
		html.append("</div>");

		return html.toString();
		//response.getWriter().print(html.toString());
	}
	
	public List<HashMap<String,String>>getZjbbList(){
		
		return dao.getZjbbList();
	}
	
	/** 保存假期留校审核表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean saveZjbbShb(String id,String xmid) throws Exception{
		
		return dao.saveZjbbShb(id,xmid);
	}
	
	public List<HashMap<String,String>>getCshXmList(){
		
	
		return dao.getCshXmList();		
	}
	
	public List<HashMap<String,String>>getCshXmList(User user){
		
		
		return dao.getCshXmList(user);		
	}
	
	
	/**
	 * 显示本用户岗位切换模式窗口
	 * @author qlj
	 * @throws IOException 
	 */
	public void showShgwDiv(RcswZjbbForm model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		String xmdm=model.getXmid();
		
		List<HashMap<String,String>>spgwList=dao.getSpgwList(xmdm, user);

		String spgwNum=countSpgw(xmdm,user);
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>审核岗位选择</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("岗位选择");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");
		html.append("<input type=\"hidden\" name=\"spgwNum\" id=\"spgwNum\" value=\""+spgwNum+"\"/>");
		
		html.append(" <input type=\"hidden\" name=\"text_xmdm\" id=\"text_xmdm\" value=\""+xmdm+"\" /> ");
		for(int i=0;i<spgwList.size();i++){
			
			HashMap<String,String>spgwMap=spgwList.get(i);
			html.append(" <input type=\"radio\" name=\"spgw\" ");
			if(i==0){
				html.append("  checked=\"true\" ");
			}
			html.append(" id=\"spgw_"+i+"\" value=\""+spgwMap.get("id")+"\">");
			html.append(spgwMap.get("mc") );
			html.append("<br/>");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"checkSpgw();return false;\">");
		html.append("确 定");
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
	
	public boolean updateShzt(RcswZjbbForm model,BasicModel basicModel, HttpServletRequest request, User user) throws Exception {

		getModelValue(model, request);
		
		String shzt=model.getShzt();
		
		basicModel.getValueMap().put("spgw", model.getSpgw());
		
		basicModel.getValueMap().put("xmid", model.getXmid());
		
		dao.resultShzt(model, user);
		
		boolean flag=dao.updateShzt(model, user);
		
		
		if("th".equalsIgnoreCase(shzt) && flag){
			
			 flag=dao.updateThzt(model,basicModel, user);
		}
		
		flag=dao.updateSqjg(model,basicModel, user);
		
		return  flag;
	}
	
	public HashMap<String,String>getZjbbSqInfo(String sqid){
		
		return dao.getZjbbSqInfo(sqid);
	}
	
	public List<HashMap<String,String>>getZjbbShInfo(String xmid,String sqid,String spgw){
		
		return dao.getZjbbShInfo(xmid,sqid, spgw);
	}
	
	public String countSpgw(String xmid,User user){
		
		return dao.countSpgw(xmid,user);
		
	}
	
	public HashMap<String,String>getBbszInfo(RcswZjbbForm myForm){
		
		
		return  dao.getBbszInfo(myForm);
		
	}
	
	
	
	public String[] getSpgwByXmid(String xmid){
		
		try {
			return dao.getSpgwByXmid(xmid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 证件补办结果自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbjgExportList(BasicModel model,User user) throws Exception{
		
		return dao.getBbjgExportList(model,user);
	}
	
}
