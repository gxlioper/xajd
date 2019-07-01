package xsgzgl.jxgl.general.jxjzgl.jxjzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts.util.MessageResources;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.db.GetSysData;
import xsgzgl.comm.BasicService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训建制管理_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 易江东
 * @version 1.0
 */

public class JxjzglService extends CommService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	JxjzglDAO dao=new JxjzglDAO();
	/**
	 * 增加保存编制节点
	 * @param model
	 * @return
	 */
	public boolean zjBcBzjd(JxjzglFrom model,User user){
		if(model == null){
			return false;
		}
		String guid = GetSysData.getGuid();
		model.setJzid(guid);
		boolean result=false;
		try {
			result = dao.insertJxjzwh(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改保存编制节点
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean xgBcBzjd(JxjzglFrom model,User user){
		boolean result=false;
		try {
			result=dao.updateJxjzwh(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除编制节点  和  当前节点的  字节点
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean scBzjd(JxjzglFrom model,User user){
		boolean result = false;
		try {
			//删除建制名单根据建制id
			dao.deleteJzmdByJzid(model, user);
			//删除建制维护
			result=dao.deleteJxjzwh(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 查询军训等级列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxdjList(JxjzglFrom model){
		List<HashMap<String, String>> jxdjList=null;
		try {
			jxdjList=dao.getJxdjList(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return jxdjList;
	}
	
	/**
	 * 查看军训最低信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> ckJxdjZdModel(){
		HashMap<String, String> jxdjZdModel = null;
		try {
			jxdjZdModel = dao.getJxdjZdModel();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return jxdjZdModel;
	}
	
	/**
	 * 查询军训建制节点列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxjzNodeList(JxjzglFrom model){
		List<HashMap<String, String>> jxjzList=null;
		try {
			jxjzList = dao.getJxjzNextNodeList(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return jxjzList;
	}
	
	/**
	 * 查询军训建制model
	 * @param model
	 * @return
	 */
	public HashMap<String, String> ckJxjzModel(JxjzglFrom model){
		
		HashMap<String, String> jxjzModel = null;
		try {
			jxjzModel = dao.getJxjzModel(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return jxjzModel;
	}
	
	
	/**
	 * 查询军训建制更上级id和建制id
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ckJxjzModelBySjid(JxjzglFrom model)
			throws Exception {
		if(model == null){
			return new HashMap<String, String>();
		}
		if(model.getJzid() !=null){
			if(model.getJzid().equals(model.getSjid())){
				return dao.getJxjzModel(model);
			}
		}
		return dao.getJxjzModelBySjid(model);
	}
	
	/**
	 * 查询军训建制学生信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxjzXsxxList(JxjzglFrom model){
		
		return null;
	}
	
	
	/**
	 * 查询第一级节点
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxOneNode(JxjzglFrom model){
		List<HashMap<String, String>> jxjzglList=cxJxjzNodeList(model);
		if(jxjzglList != null){
			for (int i = 0; i < jxjzglList.size(); i++) {
				jxjzglList.get(i).put("djjb", "0");
			}
			
		}
		return jxjzglList;
		
	}
	
	/**
	 * 查询军训等级更具节点
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxdjByNode(JxjzglFrom model) {
		List<HashMap<String, String>> djList=new ArrayList<HashMap<String,String>>();
		HashMap<String, String> djModel=new HashMap<String, String>();
		try {
			List<HashMap<String, String>> jxdjList = dao.getJxdjList(model);
			if (model.getJzjb() == null || "".equals(model.getJzjb())) {
				List<HashMap<String, String>> nextJxjzNodeList=dao.getJxjzNextNodeList(model);
				if(nextJxjzNodeList == null || nextJxjzNodeList.size() == 0){
					djList = jxdjList;
				}else{
					for (int i = 0; i < jxdjList.size(); i++) {
						if(nextJxjzNodeList.get(0).get("djdm").equals(jxdjList.get(i).get("djdm"))){
							djModel.put("djdm", jxdjList.get(i).get("djdm"));
							djModel.put("djmc", jxdjList.get(i).get("djmc"));
							djModel.put("djdj", jxdjList.get(i).get("djdj"));
							djList.add(djModel);
							break;
						}
					}
				}
			}else{
				for (int i = 0; i < jxdjList.size(); i++) {
					if(model.getJzjb().equals(jxdjList.get(i).get("djdm"))){
						if(i == (jxdjList.size()-1)){
							djModel.put("djdm", jxdjList.get(i).get("djdm"));
							djModel.put("djmc", jxdjList.get(i).get("djmc"));
							djModel.put("djdj", jxdjList.get(i).get("djdj"));
						}else{
							djModel.put("djdm", jxdjList.get(i+1).get("djdm"));
							djModel.put("djmc", jxdjList.get(i+1).get("djmc"));
							djModel.put("djdj", jxdjList.get(i+1).get("djdj"));
						}
						djList.add(djModel);
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return djList;
	}
	
	/**
	 * 增加建制维护返回html
	 * @return
	 */
	public String zjJzwhHtml(JxjzglFrom model){
		List<HashMap<String, String>> djList=cxJxdjByNode(model);
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>增加下级建制</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("建制级别");
		html.append("</th>");
		html.append("<td width=\"\">");
		if ("10704".equals(Base.xxdm)) {
			html.append("<select id=\"djdm\" name=\"djdm\" style=\"width:145px;\" onchange=\"qhShow(this)\" >");
		}else{
			html.append("<select id=\"djdm\" name=\"djdm\" style=\"width:145px;\">");
		}
		if(djList != null){
			for (int i = 0; i < djList.size(); i++) {
				html.append("<option value=\""+djList.get(i).get("djdm")+"\">"+djList.get(i).get("djmc")+"</option>");
			}
		}
		html.append("</select>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("<span style=\"color:red;\">*</span>建制名称");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jzmc\" name=\"jzmc\" onchange=\"checkOnlyJzmc(this);\" maxlength=\"20\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th1\" width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "营".equals(djList.get(0).get("djmc"))) {
				html.append("营长");
			}else if (djList.size()==1 && "连".equals(djList.get(0).get("djmc"))) {
				html.append("连长");
			}else {
				html.append("教官姓名");
			}
		}else {
			html.append("教官姓名");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jgmc\" name=\"jgmc\" maxlength=\"50\" />");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th2\"width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "营".equals(djList.get(0).get("djmc"))) {
				html.append("营长电话");
			}else if (djList.size()==1 && "连".equals(djList.get(0).get("djmc"))) {
				html.append("连长电话");
			}else {
				html.append("教官电话");
			}
		}else {
			html.append("教官电话");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jgdh\" name=\"jgdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th3\" width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "营".equals(djList.get(0).get("djmc"))) {
				html.append("教导员");
			}else if (djList.size()==1 && "连".equals(djList.get(0).get("djmc"))) {
				html.append("指导员");
			}else {
				html.append("教师姓名");
			}
		}else {
			html.append("教师姓名");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jsmc\" name=\"jsmc\" maxlength=\"50\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th id=\"th4\" width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if (djList.size()==1 && "营".equals(djList.get(0).get("djmc"))) {
				html.append("教导员电话");
			}else if (djList.size()==1 && "连".equals(djList.get(0).get("djmc"))) {
				html.append("指导员电话");
			}else {
				html.append("教师电话");
			}
		}else {
			html.append("教师电话");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" id=\"jsdh\" name=\"jsdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\">");
		html.append("</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  onclick=\"saveZjBcXjjz();return false;\">");
		html.append("确 定");
		html.append("</button>");
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		return html.toString();
	}
	
	/**
	 * 修改建制维护返回html
	 * @return
	 */
	public String xgJzwhHtml(JxjzglFrom model){
		//List<HashMap<String, String>> djList=cxJxdjByNode(model);
		HashMap<String, String> jxjzModel=null;
		try {
			jxjzModel = dao.getJxjzModel(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(jxjzModel == null){
			return "";
		}
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>修改下级建制</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("建制级别");
		html.append("</th>");
		html.append("<td width=\"\">");
		
		html.append("<select id=\"djdm\" name=\"djdm\" style=\"width:145px;\">");
		html.append("<option value=\""+jxjzModel.get("djdm")+"\">"+jxjzModel.get("djmc")+"</option>");
		html.append("</select>");
		html.append("<input type=\"hidden\" name=\"jzid\" id=\"jzid\" value=\""+jxjzModel.get("jzid")+"\">");
		
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		html.append("<span style=\"color:red;\">*</span>建制名称");
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jzmc") == null){
			html.append("<input type=\"text\" id=\"jzmc\" name=\"jzmc\" onchange=\"checkOnlyJzmc(this);\" maxlength=\"20\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jzmc\" name=\"jzmc\" onchange=\"checkOnlyJzmc(this);\" maxlength=\"20\" value=\""+jxjzModel.get("jzmc")+"\"/>");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("营".equals(jxjzModel.get("djmc"))) {
				html.append("营长");
			}else if ("连".equals(jxjzModel.get("djmc"))) {
				html.append("连长");
			}else {
				html.append("教官姓名");
			}
		}else {
			html.append("教官姓名");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jgmc") == null){
			html.append("<input type=\"text\" id=\"jgmc\" name=\"jgmc\" maxlength=\"50\" value=\"\" />");
		}else{
			html.append("<input type=\"text\" id=\"jgmc\" name=\"jgmc\" maxlength=\"50\" value=\""+jxjzModel.get("jgmc")+"\" />");
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("营".equals(jxjzModel.get("djmc"))) {
				html.append("营长电话");
			}else if ("连".equals(jxjzModel.get("djmc"))) {
				html.append("连长电话");
			}else {
				html.append("教官电话");
			}
		}else {
			html.append("教官电话");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jgdh") == null){
			html.append("<input type=\"text\" id=\"jgdh\" name=\"jgdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jgdh\" name=\"jgdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\""+jxjzModel.get("jgdh")+"\"/>");
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("营".equals(jxjzModel.get("djmc"))) {
				html.append("教导员");
			}else if ("连".equals(jxjzModel.get("djmc"))) {
				html.append("指导员");
			}else {
				html.append("教师姓名");
			}
		}else {
			html.append("教师姓名");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jsmc") == null){
			html.append("<input type=\"text\" id=\"jsmc\" name=\"jsmc\" maxlength=\"50\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jsmc\" name=\"jsmc\" maxlength=\"50\" value=\""+jxjzModel.get("jsmc")+"\"/>");
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr id=\"tr_old\" style=\"\">");
		html.append("<th width=\"30%\">");
		if ("10704".equals(Base.xxdm)) {
			if ("营".equals(jxjzModel.get("djmc"))) {
				html.append("教导员电话");
			}else if ("连".equals(jxjzModel.get("djmc"))) {
				html.append("指导员电话");
			}else {
				html.append("教师电话");
			}
		}else {
			html.append("教师电话");
		}
		html.append("</th>");
		html.append("<td width=\"\">");
		if(jxjzModel.get("jsdh") == null){
			html.append("<input type=\"text\" id=\"jsdh\" name=\"jsdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\"\"/>");
		}else{
			html.append("<input type=\"text\" id=\"jsdh\" name=\"jsdh\" onkeyup=\"cehckInt(this);\" maxlength=\"50\" value=\""+jxjzModel.get("jsdh")+"\"/>");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\">");
		html.append("</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  onclick=\"saveXgBcXjjz();return false;\">");
		html.append("确 定");
		html.append("</button>");
		html.append("<button type=\"button\" onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		return html.toString();
	}
	
	/**
	 * 验证建制名称
	 * @param model
	 * @return
	 */
	public boolean checkJzmc(JxjzglFrom model){
		if(model == null){
			return false;
		}
		HashMap<String, String> jxjzModel=null;;
		try {
			jxjzModel = dao.getJxjzglModelByJzmcAndJzid(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jxjzModel == null || jxjzModel.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * 查询军训建制管理学生信息列表 分页
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjzXsxxList(JxjzglFrom model) throws Exception{
		return  dao.getJxjzXsxxList(model);
	}
	
	/**
	 * 军训建制表条
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type){
		DAO da = DAO.getInstance();
		String[] en = new String[]{};
		String[] cn = new String[]{};
		if("whjzmd".equalsIgnoreCase(type)){
			en = new String[]{"", "xh", "xm","xb", "nj","xymc", "zymc","bjmc","treejzmc"};
			cn = new String[] { "", "学号", "姓名","性别", "年级","院系", "专业","班级","所在建制"};
		}else if("jxjzxsxx".equalsIgnoreCase(type)){
			en = new String[]{ "", "xh", "xm","xb", "bjmc","treejzmc"};
			cn = new String[] { "", "学号", "姓名","性别", "所在班级","所在建制"};
		}
		return da.arrayToList(en, cn);
		
	}
	
	/**
	 * 创建页面查询结果
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		BasicService basicService=new BasicService();
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + basicService.replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(basicService.replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * 查询建制学生信息列表 带分页
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxJxjzXsxxListByJzid(JxjzglFrom model) throws Exception{
		
		//return dao.getJxjzXsxxListByJzid(model);
		return null;
	}
	
	/**
	 * 查询建制学生信息列表 带分页
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> cxJxjzXsxxListByJzid1(JxjzglFrom model) throws Exception{
		
		return dao.getJxjzXsxxListByJzid(model);
	}
	
	/**
	 * 创建查询html
	 * @param contentList
	 * @return
	 */
	public String createSearchHtml(List<HashMap<String, String>> contentList){
		StringBuffer html=new StringBuffer();
		
		html.append(" <table width=\"100%\" class=\"dateline nowrap\"> ");
		html.append(" <thead> ");
		html.append(" <tr> ");
		html.append(" <td> ");
		html.append(" 学号 ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" 姓名 ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" 性别 ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" 所在编制 ");
		html.append(" </td> ");
		html.append(" <td> ");
		html.append(" 所在建制 ");
		html.append(" </td> ");
		html.append(" </tr> ");
		html.append(" </thead> ");
		html.append(" <tbody> ");
		for (int i = 0; i < contentList.size(); i++) {
			html.append(" <tr> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("xh"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("xm"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("xb"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("bjmc"));
			html.append(" </td> ");
			html.append(" <td> ");
			html.append(contentList.get(i).get("treejzmc"));
			html.append(" </td> ");
			html.append(" </tr> ");
		}
		html.append(" </tbody> ");
		html.append(" </table> ");
		
		return html.toString();
	}
	
	/**
	 * 增加保存军训建制名单
	 * @param pks
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean zjBcJxjzmd(String pks,JxjzglFrom model,User user){
		//实现删除已被编制的学生信息
		scJxjzmd(pks, user);
		
		List<JxjzglFrom> list=new ArrayList<JxjzglFrom>();
		String[] pk=pks.split("@@##");
		JxjzglFrom jxjzglFrom=null;
		for (int i = 0; i < pk.length; i++) {
			jxjzglFrom=new JxjzglFrom();
			jxjzglFrom.setXh(pk[i]);
			jxjzglFrom.setJzid(model.getJzid());
			list.add(jxjzglFrom);
		}
		boolean result = false;
		try {
			result = dao.insertJxjzmd(list, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除军训建制名单
	 * @param pks
	 * @return
	 */
	public boolean scJxjzmd(String pks, User user){
		List<JxjzglFrom> list=new ArrayList<JxjzglFrom>();
		String[] pk=pks.split("@@##");
		JxjzglFrom jxjzglFrom=null;
		for (int i = 0; i < pk.length; i++) {
			jxjzglFrom=new JxjzglFrom();
			jxjzglFrom.setXh(pk[i]);
			list.add(jxjzglFrom);
		}
		boolean result=false;
		try{
			result=dao.deleteJxjzmd(list, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * 查询军训建制统计表
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> cxJxjzTjb(JxjzglFrom model) throws Exception{
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		
		if(jzdjList == null || jzdjList.size() == 0){
			return null;
		}
		List<HashMap<String, String>> jxjzList=dao.getJxjzTjb(jzdjList, model);
		return jxjzList;
	}
	
	/**
	 * 创建军训建制统计表html
	 * @param jxjzList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createJxjzTjxHtml(List<HashMap<String, String>> jxjzList,JxjzglFrom model) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		if(jxjzList == null){
			return "";
		}
		//获取合并项数值设置
		List<Object> jxjzNnion=setJxjzTjxNnionTable(jxjzList, jzdjList);
		HashMap<String, Integer> jzNnion=(HashMap<String, Integer>)jxjzNnion.get(0);
		HashMap<String, Integer> jzRs=(HashMap<String, Integer>)jxjzNnion.get(1);
		HashMap<String, String> jzXymc=(HashMap<String, String>)jxjzNnion.get(2);
		HashMap<String, Integer> jzBjNnion=(HashMap<String, Integer>)jxjzNnion.get(3);
		
		//合并使用参数
		StringBuffer html=new StringBuffer();
		HashMap<String, String> unionMapMc=new HashMap<String, String>();
		HashMap<String, String> unionMapDm=new HashMap<String, String>();
		HashMap<String, Integer> addUpMap=new HashMap<String, Integer>();
		HashMap<String, String> joinMap=new HashMap<String, String>();
		String rowspanNum=new String("");
		String jzmcBh="";
		String key="";
		String bjdm="";
		//HashMap<String, Integer> rowspanNums=new HashMap<String, Integer>();
		
		html.append("<span class=\"formbox\">");
		html.append("<table id=\"table_rs\" class=\"dateline\" width=\"100%\">");
		html.append("<thead><tr>");
		//生成头头部
		for (int i = 0; i < jzdjList.size(); i++) {
			html.append("<td width=\"5\" noWrap>");
			html.append(jzdjList.get(i).get("djmc"));
			html.append("</td>");
			
			html.append("<td width=\"5\" noWrap>");
			html.append(jzdjList.get(i).get("djmc"));
			html.append("长");
			html.append("</td>");
			
			html.append("<td width=\"5\" noWrap>老师</td>");
			
			html.append("<td width=\"5\" noWrap>人数</td>");
			
			if(i==0){
				html.append("<td width=\"40px\" noWrap>"+Base.YXPZXY_KEY+"</td>");
			}
			
			if(i==(jzdjList.size()-1)){
				html.append("<td width=\"5\" noWrap>班级</td>");
				html.append("<td width=\"5\" noWrap>人数</td>");
			}
		}
		html.append("</tr></thead>");
		//生成列表
		html.append("<tbody>");
		for (int i = 0; i < jxjzList.size(); i++) {
			html.append("<tr>");
			for (int j = 0; j < jzdjList.size(); j++) {
				jzmcBh="jzmc"+jzdjList.get(j).get("djdm");
				key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
				bjdm=jxjzList.get(i).get("bjdm");
				if((unionMapMc.get(jzmcBh) == null || !unionMapMc.get(jzmcBh).equals(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))))
						|| (unionMapDm.get(jzmcBh) == null || !unionMapDm.get(jzmcBh).equals(key))){
					//rowspanNums.put(jzmcBh, Integer.valueOf(1));
					//初始化
					//建制名称
					rowspanNum=new String();
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					
					//教官名称
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jgmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					//教师名称
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append(nullToString(jxjzList.get(i).get("jsmc"+jzdjList.get(j).get("djdm"))));
					html.append("</td>");
					
					//人数
					html.append("<td height=\"28\" noWrap align=\"left\"");
					html.append(" rowspan=\"");
					html.append(jzNnion.get(key));
					html.append("\">");
					html.append("<a href=\"javascript:void(0);\" onclick=\"cxJzjzmdByJzid('");
					html.append(jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm")));
					html.append("')\" class=\"name\">");
					html.append(jzRs.get(key));
					html.append("</a>");
					html.append("人");
					html.append("</td>");
					
					//院系
					if(j == 0){
						html.append("<td height=\"28\" style=\"width:80px;\" noWrap align=\"left\"");
						html.append(" rowspan=\"");
						html.append(jzNnion.get(key));
						html.append("\">");
						html.append(nullToString(jzXymc.get(key)));
						html.append("</td>");
					}
				}
//				else{
//					rowspanNums.get(jzmcBh);
//					rowspanNums.put(jzmcBh, rowspanNums.put(jzmcBh, Integer.valueOf(1)).intValue()+1);
//				}
				//最后一级建制
				if(j == (jzdjList.size()-1) && !Base.isNull(bjdm)){
					//班级
					if(jzBjNnion.get(key+bjdm) != null){
						html.append("<td height=\"28\" noWrap align=\"left\"");
						html.append(" rowspan=\"");
						html.append(jzBjNnion.get(key+bjdm));
						html.append("\">");
						html.append(nullToString(jxjzList.get(i).get("bjmc")));
						html.append("</td>");
						jzBjNnion.remove(key+bjdm);
					}
					
					//人数
					html.append("<td height=\"28\" noWrap align=\"left\">");
					html.append("<a href=\"#\" class=\"name\"  onclick=\"cxJxjzmdByJzidAndBjdm('");
					html.append(jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm")));
					html.append("','");
					html.append(bjdm);
					html.append("','");
					html.append(jxjzList.get(i).get("xb"));
					html.append("')\" >");
					html.append(jxjzList.get(i).get("jzrs"));
					html.append("</a>");
					html.append("(");
					html.append(nullToString(jxjzList.get(i).get("xb")));
					html.append(")");
					html.append("</td>");
				}else if(j == (jzdjList.size()-1) && Base.isNull(bjdm)){
					
					html.append("<td ></td>");
					html.append("<td ></td>");
				}
				
				//
				unionMapMc.put(jzmcBh, jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")));
				unionMapDm.put(jzmcBh, key);
			}
			html.append("</tr>");
		}
		html.append("</tbody>");
		html.append("</table>");
		html.append("</span>");
		return html.toString();
	}
	
	/**
	 * 将null转空字符
	 * @param value
	 * @return
	 */
	private String nullToString(String value){
		if(value == null){
			return "";
		}
		return value;
	}
	
	/**
	 * 设置军训统计表
	 * @param jxjzList
	 * @param jzdjList
	 * @return
	 */
	private List<Object> setJxjzTjxNnionTable(List<HashMap<String, String>> jxjzList
			,List<HashMap<String, String>> jzdjList){
		HashMap<String, Integer> mapNnion=new HashMap<String, Integer>();//合并列数
		HashMap<String, Integer> mapRs=new HashMap<String, Integer>();//合并人数
		HashMap<String, String> mapXymc=new HashMap<String, String>();//合并学院名称
		HashMap<String, Integer> mapBjNnion=new HashMap<String, Integer>();//合并班级列数
		List<Object> jxjzNnion = new ArrayList<Object>();
		
		HashMap<String, String> unionMap=new HashMap<String, String>();
		Integer time=0;
		String key="";
		Integer rs=0;
		String xymc="";
		String bjdm="";
		for (int i = 0; i < jxjzList.size(); i++) {
			for (int j = 0; j < jzdjList.size(); j++) {
				key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
				rs=Integer.valueOf(jxjzList.get(i).get("jzrs"));
				xymc=jxjzList.get(i).get("xymc");
				bjdm=jxjzList.get(i).get("bjdm");
				if(mapNnion.get(key) != null){
					time=mapNnion.get(key);
					mapNnion.put(key, 
							(time.intValue()+1));
					
				}else{
					//第一出出现当前建制
					if(key != null){
						mapNnion.put(key, 
								Integer.valueOf("1"));
					}
				}
					
					
				unionMap.put(key, 
						"true");
				//人数纪录
				if(mapRs.get(key) != null){
					mapRs.put(key, Integer.valueOf(mapRs.get(key))+rs);
				}else{
					mapRs.put(key,rs);
				}
				
				//学院纪录
				if(mapXymc.get(key) != null){
					if(xymc != null && mapXymc.get(key).indexOf(xymc) <0){
						//mapXymc.put(key,mapXymc.get(key)+",<br/>"+xymc);
						mapXymc.put(key,mapXymc.get(key)+","+xymc);
					}
				}else{
					mapXymc.put(key,xymc);
				}
				//班级记录
				if(mapBjNnion.get(key+bjdm) != null){
					mapBjNnion.put(key+bjdm, mapBjNnion.get(key+bjdm)+1);
				}else{
					mapBjNnion.put(key+bjdm,1);
				}
				
			}
		}
		
		jxjzNnion.add(mapNnion);
		jxjzNnion.add(mapRs);
		jxjzNnion.add(mapXymc);
		jxjzNnion.add(mapBjNnion);
		return jxjzNnion;
	}
	
	/**
	 * 查询人数总数统计表
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRszjTjb(JxjzglFrom model) throws Exception{
		
		return dao.getRszjTjb(model);
	}
	
	/**
	 * 查询建制学生信息列表 带分页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjzmdList(JxjzglFrom model)
			throws Exception {
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		return dao.getJxjzmdList(model,jxjzdjList);
	}
	
	/**
	 * 导出建制名称
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void dcJzTjb(ServletOutputStream os, JxjzglFrom model,HashMap<String, String> title,
			List<HashMap<String, String>> jxjzList) throws Exception {
		List<HashMap<String, String>> jzdjList=dao.getJxJzdj(model);
		//获取合并项数值设置
		List<Object> jxjzNnion=setJxjzTjxNnionTable(jxjzList, jzdjList);
		HashMap<String, Integer> jzNnion=(HashMap<String, Integer>)jxjzNnion.get(0);//合并列数
		HashMap<String, Integer> jzRs=(HashMap<String, Integer>)jxjzNnion.get(1);//人数统计
		HashMap<String, String> jzXymc=(HashMap<String, String>)jxjzNnion.get(2);//学院名称
		HashMap<String, Integer> jzBjNnion=(HashMap<String, Integer>)jxjzNnion.get(3);//班级名称合并
		
		//合并使用参数
		HashMap<String, String> unionMapMc=new HashMap<String, String>(); //用于记录相同项名称
		HashMap<String, String> unionMapDm=new HashMap<String, String>(); //用于记录相同项代码
		
		String tjxx="";
		// 创建excel对象
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("学生人数统计", 0);
		//设置单元个宽度
		ws.setColumnView(3, 35);
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			WritableCellFormat titleStyle = ExcelMB.getWritableCellFormat(16,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			WritableCellFormat leftTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			// 标题
			ws.mergeCells(0, 0, (jzdjList.size()*4+2), 0); 
			ws.addCell(new Label(0, 0,title.get("jxmc")+"统配表", titleStyle));
			tjxx=title.get("jxmc")+"(参训人数:"+title.get("cxrs")+",已编制人数:"+title.get("ybzrs")+",未编制人数:"+title.get("wbzrs")+")";
			ws.mergeCells(0, 1, (jzdjList.size()*4+2), 1); 
			ws.addCell(new Label(0, 1,tjxx, wcfTytle));
			
			//生成头部
			int index=0;
			for (int i = 0; i < jzdjList.size(); i++) {
				//等级名称
				ws.addCell(new Label(index, 2,jzdjList.get(i).get("djmc"), wcfTytle));
				index++;
				//等级教官名称
				ws.addCell(new Label(index, 2,jzdjList.get(i).get("djmc")+"长", wcfTytle));
				index++;
				//老师名称
				ws.addCell(new Label(index, 2,"老师", wcfTytle));
				index++;
				//人数
				ws.addCell(new Label(index, 2,"人数", wcfTytle));
				index++;
				
				if(i==0){
					//学院
					Base.YXPZXY_KEY = message.getMessage("lable.xb");
					ws.addCell(new Label(index, 2,Base.YXPZXY_KEY, wcfTytle));
					index++;
				}
				
				if(i==(jzdjList.size()-1)){
					//班级
					ws.addCell(new Label(index, 2,"班级", wcfTytle));
					index++;
					//人数
					ws.addCell(new Label(index, 2,"人数", wcfTytle));
					index++;
				}
			}
			
			String jzmcBh="";
			String key="";
			String bjdm="";
			//生成内容
			for (int i = 0; i < jxjzList.size(); i++) {
				index=0;
				for (int j = 0; j < jzdjList.size(); j++) {
					jzmcBh="jzmc"+jzdjList.get(j).get("djdm");
					key=jxjzList.get(i).get("jzid"+jzdjList.get(j).get("djdm"));
					bjdm=jxjzList.get(i).get("bjdm");
					
					if((unionMapMc.get(jzmcBh) == null || !unionMapMc.get(jzmcBh).equals(jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm"))))
							|| (unionMapDm.get(jzmcBh) == null || !unionMapDm.get(jzmcBh).equals(key))){
						//加入数据
						//建制名称
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1); 
						//ws.setRowView(0, 400); // 设置指定行高
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//教官名称
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jgmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//老师名称
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("lsmc"+jzdjList.get(j).get("djdm")), wcfTytle));
						index++;
						
						//人数
						ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
						ws.addCell(new Label(index, 3+i,String.valueOf(jzRs.get(key)), wcfTytle));
						index++;
						
						//学院
						if(j == 0){
							ws.mergeCells(index, 3+i, index, 3+i+jzNnion.get(key).intValue()-1);  
							ws.addCell(new Label(index, 3+i,jzXymc.get(key), wcfTytle));
							index++;
						}
					}else{
						index=index+4;
						if(j == 0){
							index++;
						}
					}
						
					
					//班级、人数
					if(j == (jzdjList.size()-1)){
						//班级
						if(jzBjNnion.get(key+bjdm) != null){
							ws.mergeCells(index, 3+i, index, 3+i+jzBjNnion.get(key+bjdm)-1);  
							ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("bjmc"), wcfTytle));
						}
						index++;
						jzBjNnion.remove(key+bjdm);
						
						
						ws.addCell(new Label(index, 3+i,jxjzList.get(i).get("jzrs")+"("+jxjzList.get(i).get("xb")+")", wcfTytle));
						index++;
					}
					
					//
					unionMapMc.put(jzmcBh, jxjzList.get(i).get("jzmc"+jzdjList.get(j).get("djdm")));
					unionMapDm.put(jzmcBh, key);
				}
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * 初始化军训建制根节点
	 * @param model
	 * @return
	 */
	public boolean initJxjzglRootNode(JxjzglFrom model , User user) throws Exception{
		boolean result=false;
		HashMap<String, String> jxjzglModel=dao.getJxjzModel(model);
		try {
			if(jxjzglModel == null || jxjzglModel.isEmpty()){
				result = dao.insertJxjzwh(model, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 设置默认查询条件
	 * @return
	 */
	public SearchModel setDefaultSearchModel(String jzid,String bjdm) throws Exception{
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		HashMap<String, String> jxjzModel= dao.getJxjzModelByJzid(jxjzdjList,jzid);
		SearchModel searchModel = new SearchModel();
		
		if(jxjzModel !=null){
			if(jxjzModel.get("tid") !=null && !"".equals(jxjzModel.get("tid"))){
				searchModel.setSearch_tj_tid(new String[]{jxjzModel.get("tid")});
			}
			
			if(jxjzModel.get("yid") !=null && !"".equals(jxjzModel.get("yid"))){
				searchModel.setSearch_tj_yid(new String[]{jxjzModel.get("yid")});
			}
			
			if(jxjzModel.get("lid") !=null && !"".equals(jxjzModel.get("lid"))){
				searchModel.setSearch_tj_lid(new String[]{jxjzModel.get("lid")});
			}
			
			if(jxjzModel.get("pid") !=null && !"".equals(jxjzModel.get("pid"))){
				searchModel.setSearch_tj_pid(new String[]{jxjzModel.get("pid")});
			}

			if(jxjzModel.get("bid") !=null && !"".equals(jxjzModel.get("bid"))){
				searchModel.setSearch_tj_bid(new String[]{jxjzModel.get("bid")});
			}
			
			if(jxjzModel.get("ssid") !=null && !"".equals(jxjzModel.get("ssid"))){
				searchModel.setSearch_tj_ssid(new String[]{jxjzModel.get("ssid")});
			}
		}
		
		if(bjdm !=null && !"".equals(bjdm)){
			searchModel.setSearch_tj_bj(new String[]{bjdm});
		}
		
		return searchModel;
	}
	
	/**
	 * 删除学生编制
	 * @return
	 */
	public String scXsjz(boolean isJxcj,boolean isJxbx,String xhs,String jzids, User user) throws Exception{
		String message="";
		if(xhs == null || "".equals(xhs) || jzids==null || "".equals(jzids)){
			message="学号或者建制编号为空!";
			return message;
		}
		if(isJxcj){
			message="学生已获取成绩,不能取消编制!";
			return message;
		}
		
		if(isJxbx){
			message="学生已获取军训表现，不能取消编制!";
			return message;
		}
		String[] xh=xhs.split("!!@@!!");
		String[] jzid=jzids.split("!!@@!!");
		boolean result=dao.deleteJxjzglMd(xh, jzid, user);
		if(result){
			message="取消编制成功!";
		}else{
			message="取消编制失败!";
		}
		return message;
	}
	
	/**
	 * 查询军训建制学生名单列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjzXsmdList(JxjzglFrom model){
		
		return dao.getJxjzXsmdList(model);
	}
	
	/**
	 * 设置学生学号根据listmap
	 * @param list
	 * @return  xh!!@@!!xh
	 */
	public String setXsxhByListMap(List<HashMap<String, String>> list){
		String xhs="";
		if(list == null){
			return xhs;
		}
		for (int i = 0; i < list.size(); i++) {
			if("".equals(xhs)){
				xhs=list.get(i).get("xh");
			}else{
				xhs=xhs+"!!@@!!"+list.get(i).get("xh");
			}
		}
		return xhs;
	}
	
	/**
	 * 创建当前军训建制Html
	 * @return
	 */
	public String createDqjxjzHtml(HashMap<String, String> jxxxwhModel,HashMap<String, String> rs){
		StringBuffer html=new StringBuffer();
		html.append(" 军训名称：");
		html.append(jxxxwhModel.get("jxmc"));
		html.append("&nbsp;&nbsp;");
		html.append("参训人数：");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPage()\">");
		html.append(rs.get("cxrs"));
		html.append("</a>");
		html.append("人&nbsp;&nbsp;");
		html.append("已编制人数：");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPageYbz()\">");
		html.append(rs.get("ybzrs"));
		html.append("</a>");
		html.append("人&nbsp;&nbsp;");
		html.append("尚未编制人数：");
		html.append("<a href=\"javascript:void(0);\" style=\"color: blue;text-decoration:underline;\" class=\"name\" onclick=\"cxJzjzmdPageWbz()\">");
		html.append(rs.get("wbzrs"));
		html.append("</a>");
		html.append("人");
		return html.toString();
	}
	
	/**
	 * 军训建制管理自定义导出 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjzmdExportDataList(JxjzglFrom model)
			throws Exception {
		List<HashMap<String, String>> jxjzdjList = dao.getJxdjList(new JxjzglFrom());
		return dao.getJxjzmdExportDataList(model,jxjzdjList);
	}
	
}