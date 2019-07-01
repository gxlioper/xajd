package xsgzgl.gygl.gyjlxxglnew;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglForm;

public class GyjlxxglNewService extends CommService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	GyjlxxglNewDao GyjlxxglDAO = new GyjlxxglNewDao();
	
	/**
	 * 获得表头
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","actionLink"};
		String[] cn = new String[] { "", "学号", "姓名", "性别", "班级", "住宿寝室", "违纪时间","纪律类别","处理结果" ,"审核状态", "操作"};
		if("12309".equals(Base.xxdm)){
			en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","czrxm","czrbm","czsj","actionLink"};
			cn = new String[] { "", "学号", "姓名", "性别", "班级", "住宿寝室", "违纪时间","纪律类别","处理结果" ,"审核状态","检查人","检查部门","检查时间", "操作"};
		}
		//重庆邮电大学个性化
		if("13627".equals(Base.xxdm)){
			 en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","fdyxm","actionLink"};
			 cn = new String[] { "", "学号", "姓名", "性别", "班级", "住宿寝室", "违纪时间","纪律类别","处理结果" ,"审核状态","辅导员", "操作"};
		}
		//重庆工商大学个性化
		if("11799".equals(Base.xxdm)){
			 en = new String[] { "", "xh", "xm", "xb", "bjmc", "gwxzmc", "zsqs", "wjsj", "wjlb","cljg","shztmc","actionLink"};
			 cn = new String[] { "", "学号", "姓名", "性别", "班级", "住宿寝室", "违纪时间","奖惩类别","处理结果" ,"审核状态", "操作"};
		}
		if("gyjlxscx".equalsIgnoreCase(type)){
			en = new String[] { "xh","xm","xb","nj","xy","bj","ldmc","qsh","cwh",""};
			cn = new String[] { "学号","姓名","性别","年级",Base.YXPZXY_KEY,"班级","楼栋名称","寝室号","床位号","操作" };
		}else if("gyjlxxcl".equalsIgnoreCase(type)){
			//重庆邮电大学
			if("13627".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt","ylzd3","fdyxm" };
				cn = new String[] { "","学号","姓名","性别","住宿寝室","违纪时间","纪律类别","处理结果","审核状态" ,"处理时间","辅导员"};
			}else if("11799".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","学号","姓名","性别","住宿寝室","违纪时间","奖惩类别","处理结果","审核状态" };
			}else{
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","学号","姓名","性别","住宿寝室","违纪时间","纪律类别","处理结果","审核状态" };
			}
		}else if("gyjlxxsh".equalsIgnoreCase(type)){
			//重庆邮电大学个性化
			if("13627".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt","ylzd3","shsj","fdyxm" };
				cn = new String[] { "","学号","姓名","性别","住宿寝室","违纪时间","纪律类别","处理结果","审核状态","处理时间","审核时间","辅导员" };
			}else if("11799".equals(Base.xxdm)){
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","学号","姓名","性别","住宿寝室","违纪时间","奖惩类别","处理结果","审核状态" };
			}else{
				en = new String[] { "","xh","xm","xb","zsqs","wjsj","wjlb","cljg","shzt" };
				cn = new String[] { "","学号","姓名","性别","住宿寝室","违纪时间","纪律类别","处理结果","审核状态" };
			}
		}else if("xsjlxxcx".equalsIgnoreCase(type)){
			if("13033".equals(Base.xxdm)){
				en = new String[] { "wjxn","xqmc","gyjllbdlmc","gjllbmc","wjsj","gyjlcfmc","ylzd1",""};
				cn = new String[] { "学年","学期","纪律大类","纪律类别","违纪时间","处理结果","赔偿金额","操作" };
			}
			else{
			en = new String[] { "wjxn","xqmc","gyjllbdlmc","gjllbmc","wjsj","gyjlcfmc",""};
			cn = new String[] { "学年","学期","纪律大类","纪律类别","违纪时间","处理结果","操作" };
			}
		}
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * 查询违纪学生
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxwhCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxwhCx(myForm,request);
	}
	
	
	
	
	/**
	 * 
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE版本
		String ie = rsModel.getIe();
		// V4路径
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"zjBcStu();\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	public String createSearchHTMLgyjlxxwh(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE版本
		String ie = rsModel.getIe();
		// V4路径
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				String shzt = rs[8];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"ShowView();\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				if(shzt.equals("未处理")||shzt.equals("退回")){
					html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
					html.append("value=\"" + pk + "\" />");
				}else{
					html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
					html.append("value=\"" + pk + "\" disabled=\"disabled\" />");
				}
				
				html.append("</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append("<a class=\"name\" href=\"#\" onclick=\"zxsxxView('"+rs[1]+"')\">");
				html.append(rs[1]);
				html.append("</a>");
				html.append("</td>");
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				html.append("<td>").append("<a href=\"#\" onclick=\"ShowView();return false;\"  >").append("查看").append("</a>").append("</td>");
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	public String createSearchHTML2(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE版本
		String ie = rsModel.getIe();
		// V4路径
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				String shzt = rs[8];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"ShowView();\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				html.append("value=\"" + pk + "\" />");
				
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	/**
	 * 查询学生信息结果集
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLXscx(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE版本
		String ie = rsModel.getIe();
		// V4路径
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"zjBcStu();\">");				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");
					html.append("<input type=\"hidden\" name=\"div_pkValue\" ");
					html.append("value=\"" + pk + "\"/>");
					html.append(rs[j]);
					html.append("</td>");
				}
				html.append("<td width=\"10%\">");
				html.append("<button type=\"button\" id=\"select\" onclick=\"cz(this);\" style=\"cursor:hand\"  class=\"btn_01\" >选择</button>");
				html.append("</td>");
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 增加页面的默认参数
	 * @param request
	 * @return
	 */
	public HashMap<String, String> setZjmrCs(HttpServletRequest request) {
		HashMap<String,String> rs = new HashMap<String,String>();
		rs.put("xn", Base.currXn);
		rs.put("xq", Base.currXq);
		return rs;
	}

	/**
	 * 获得纪律大类list
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getJldlList(HttpServletRequest request){
		return GyjlxxglDAO.getJldlList(request);
	}

	/**
	 * 查询住宿学生
	 * @param myForm 
	 * @return
	 */
	public ArrayList<String[]> gyjlxscx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxscx(myForm,request);
	}
	
	
	/**
	 * 获得学生住宿信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getStuInfo(GyjlxxglNewForm myForm) throws Exception{
		return GyjlxxglDAO.getStuInfo(myForm);
	
	}

	/**
	 * 公寓纪律信息处理
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxclCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxclCx(myForm,request);
	}

	/**
	 * 公寓纪律信息审核
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> gyjlxxshCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxshCx(myForm,request);
	}

	/**
	 * 公寓纪律历史信息
	 * @param myForm
	 * @param request
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWjxxList(GyjlxxglNewForm myForm) throws Exception {
		return GyjlxxglDAO.getWjxxList(myForm);
	}


	/**
	 * 公寓纪律批量处理
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String gyjlxxPlcl(GyjlxxglNewForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@");
		String cljg = model.getCljg();
		String dcqk = model.getDcqk();
		String clsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
		List<String[]> params = new ArrayList<String[]>();
		if(pkValue.length!=0){
			for(int i = 0; i < pkValue.length; i++){
				String[] el = new String[]{cljg,dcqk,model.getYlzd1(),pkValue[i]};
				//重庆邮电大学移通学院
				if("13627".equals(Base.xxdm)){
					el = new String[]{cljg,dcqk,model.getYlzd1(),clsj,pkValue[i]};
				}
				params.add(el);
			}
		}
		return GyjlxxglDAO.gyjlxxPlcl(params)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}

	/**
	 * 删除公寓纪律信息
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	public boolean gyjlSc(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException {
		return GyjlxxglDAO.gyjlSc(myForm,valArr,username);
	}
	/**
	 * 撤销公寓纪律处理
	 */
	public boolean gyjlCancelCl(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException {
		return GyjlxxglDAO.gyjlCancelCl(myForm,valArr,username);
	}
	/**
	 * 撤销公寓纪律处理
	 */
	public boolean gyjlCancelSh(GyjlxxglForm myForm, String[] valArr, String username) throws SQLException {
		return GyjlxxglDAO.gyjlCancelSh(myForm,valArr,username);
	}

	/**
	 * 学生纪律信息查询
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> xsjlxxcx(GyjlxxglNewForm myForm) throws Exception {
		return GyjlxxglDAO.xsjlxxcx(myForm);
	}


	public String createSearchHTMLXsjl(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		// IE版本
		String ie = rsModel.getIe();
		// V4路径
//		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];
				html.append("<tr onclick=\"rowOnClick(this);\" >");
/*				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"zjBcStu();\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");*/
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");

				html.append("<a href=\"#\"  onclick=\"ckxsjl('"+rs[0]+"');return false;\"><font color=\"blue\">查看</font></a>");
				html.append("</td>");
				
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 公寓纪律信息批量审核
	 * @param model
	 * @return
	 */
	public String gyjlxxPlsh(GyjlxxglNewForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@");
		String shzt = model.getShzt();
		String shyj = model.getShyj();
		String shr = model.getShr();
		String shsj = model.getShsj();
		List<String[]> params = new ArrayList<String[]>();
		if(pkValue.length!=0){
			for(int i = 0; i < pkValue.length; i++){
				String[] el = new String[]{shzt,shyj,shr,shsj,pkValue[i]};
				params.add(el);
			}
		}
		return GyjlxxglDAO.gyjlxxPlsh(params)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	/**
	 * 公寓纪律信息管理自定义设置
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> gyjlxxwhExportCx(GyjlxxglNewForm myForm,HttpServletRequest request) throws Exception {
		return GyjlxxglDAO.gyjlxxwhExportCx(myForm,request);
	}


	/**
	 * @throws Exception  
	 * @描述:公寓纪律信息审核  自定义导出
	 * @作者：cq [工号：785]
	 * @日期：2013-12-30 下午03:29:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> gyjlxxshExportCx(
			GyjlxxglNewForm model, HttpServletRequest request) throws Exception {

		return GyjlxxglDAO.gyjlxxshExportCx(model,request);
	}
}
