package xsgzgl.pjpy.bjlhdx.wdpj.lssb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.pjtj.WdpjPjtjDAO;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-11 上午10:27:12</p>
 */
public class WdpjLssbService extends xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService {

	WdpjLssbDAO dao= new WdpjLssbDAO();
	
	// --------------------------老师上报结果集相关信息 begin--------------------
	/**
	 * 获得表头文件(我的评奖_老师上报)
	 * @author qlj
	 */
	public List<HashMap<String, String>> getWdpjLssbTop(PjpyWdpjModel model,
			User user) {
		
		PjpyZhcpDAO zhcpDAO=new PjpyZhcpDAO();
		
		DAO dao = DAO.getInstance();
		
		// ----------------------老师上报 en begin-------------------------------------
		String[]en=new String[]{"xh","xm","nj","bjmc","zd3"};

		// 智育排名字段 根据基本设置中综测排名的设置出(1,2,3,4,5,6,7 种情况)
		String[]zypmList=zhcpDAO.getZyPmArr("en");
		
		en=dao.unionArray(en, zypmList);
		
		String[]other=new String[]{"zd1"};
		
		en=dao.unionArray(en, other);
		
		// 综测排名字段 根据基本设置中综测排名的设置出(1,2,3,4,5,6,7 种情况)
		String[]zcpmList=zhcpDAO.getZcPmArr("en");
		
		en=dao.unionArray(en, zcpmList);
		
		other=new String[]{"cz"};
		
		en=dao.unionArray(en, other);
		// ----------------------老师上报 en end-------------------------------------
		
		
		//  ----------------------老师上报 en begin-------------------------------------
		String[]cn=new String[]{"学号","姓名","年级","班级","智育分"};

		// 智育排名字段 根据基本设置中综测排名的设置出(1,2,3,4,5,6,7 种情况)
		String[]zypmcnList=zhcpDAO.getZyPmArr("cn");
		
		cn=dao.unionArray(cn, zypmcnList);
		
		String[]otherCn=new String[]{"综测分"};
		
		cn=dao.unionArray(cn, otherCn);
		
		// 综测排名字段 根据基本设置中综测排名的设置出(1,2,3,4,5,6,7 种情况)
		String[]zcpmcnList=zhcpDAO.getZcPmArr("cn");
		
		cn=dao.unionArray(cn, zcpmcnList);
		
		other=new String[]{"操作"};
		
		cn=dao.unionArray(cn, other);
		// ----------------------老师上报 en end-------------------------------------
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 查询数据(我的评奖_老师上报)
	 * @author qlj
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		// 指定评奖项目、评奖班级学生结果集
		ArrayList<String[]> list = dao.getWdpjLssbList(myForm, model, user);
	
		return list;
	}

	/**
	 * 构建HTML(我的评奖_老师上报) 
	 * @author qlj
	 */
	public String createWdpjLssbHTML(SearchRsModel rsModel,PjpyWdpjModel model,
			HashMap<String,String>qdrsMap,ArrayList<String[]> rsArrList, User user) {
	
		// IE版本
		String ie = rsModel.getIe();
		
		// ---------------指定项目 班级人数限定 begin------------------
		String str_qdrs=Base.isNull(qdrsMap.get("qdrs")) ? "0" : qdrsMap.get("qdrs");
		int qdrs=Integer.parseInt(str_qdrs);
		// ---------------指定项目 班级人数限定 end------------------
		
		
		// ------------------拼接html begin -------------------------
		StringBuilder html = new StringBuilder();
		
		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				int len=rs.length;
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<a href=\"#\" onclick=\"showXsxxDiv('"+rs[0]+"');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(rs[0]);
				html.append("</font>");
				html.append("</a>");
				
				// ------------------系统推荐人员 begin---------------
				if(i<qdrs){
					html.append("<font color=\"red\">★</font>");
				}
				// ------------------系统推荐人员 end---------------
				html.append("</td>");
				
				// -----------------常规字段显示 begin ----------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				// -----------------常规字段显示 end ----------------------			

				// -----------------审核结果 begin ----------------------
				if("wsq".equalsIgnoreCase(rs[len-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"checkXssqZg('add','"+rs[0]+"');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("上报");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}else if("ysq".equalsIgnoreCase(rs[len-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"disfrockXssqInfo('"+rs[len-1]+"');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("撤销");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}else if("wsh".equalsIgnoreCase(rs[len-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"disfrockXssqInfo('"+rs[len-1]+"');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("撤销");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}else if("xcs".equalsIgnoreCase(rs[len-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"checkXssqZg('modi','"+rs[0]+"');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("修改");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}else {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"return false;\">");
					html.append("<font color=\"#A8A7A7\">");
					html.append("撤销");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}
				// -----------------审核结果 end ----------------------				
				html.append("</tr>");
			}
		}
		// ------------------拼接html end -------------------------
		return html.toString();
		
	}

	/**
	 * 构建特殊隐藏区域
	 * @author qlj
	 */
	public String createKidneyDiv(SearchRsModel rsModel, PjpyWdpjModel model, HashMap<String, String> qdrsMap, ArrayList<String[]> rsArrList, User user) {

		StringBuilder html = new StringBuilder();
		
		StringBuilder message = new StringBuilder();
		// 项目名称
		String xmmc = qdrsMap.get("xmmc");
		// 确定人数
		String qdrs= qdrsMap.get("qdrs");
		
		// -------------------老师上报页面提示信息扩展 begin---------------------
		message.append("您正在上报:");
		message.append(xmmc);
		message.append("项目");
		
		// ---------------非空时显示 --------------------
		if(!Base.isNull(qdrs)){
			message.append(",该项目的人数限制为");
			message.append(qdrs);
			message.append("人。带<font color='red'>★</font>的学生为系统推荐学生。");
		}
		// -------------------老师上报页面提示信息扩展 end---------------------
		
		
		// --------------------特殊信息隐藏区域 begin -------------------------
		html.append("<div style=\"display:none\">");
		html.append(" <input type=\"hidden\" name=\"tsxxInfo\" id=\"tsxxInfo\" value=\"");
		html.append(message);
		html.append("\"/>");
		html.append("</div>");
		// --------------------特殊信息隐藏区域 end -------------------------	
		return html.toString();
	}
	// --------------------------老师上报结果集相关信息 end----------------------
	
	// ----------------------老师上报 begin------------------
	/**
	 * 保存老师上报审核表信息
	 * author qlj
	 */
	public boolean saveLssbShInfo(String xmdm, String xh) throws Exception {
		
		WdpjXssqDAO dao=new WdpjXssqDAO();
		// 审核表信息保存
		return dao.saveWdpjShInfo(xmdm, xh);
	}

	/**
	 * 保存项目上报信息
	 * author qlj
	 */
	public Boolean saveXmsb(BasicModel mode, User user) {
		
		BasicService service=new BasicService();
		
		PjszPjxmDAO pjxmDAO=new PjszPjxmDAO();
		
		HashMap<String,String>xmMap=pjxmDAO.getPjxmInfo(mode.getValueMap().get("xmdm"));
		
		if("no".equalsIgnoreCase(xmMap.get("sfsh"))){
			mode.getValueMap().put("sqjg", "wxsh");
		}else{
			mode.getValueMap().put("sqjg", "wsh");
		}
		// 申请表信息保存
		return service.saveInfo(mode);
	}
	// ----------------------老师上报 end------------------	
	
	// ----------------------老师上报结果集相关信息 begin------------------	
	/**
	 * 老师上报窗口
	 * author qlj
	 */
	public void showLssbDiv(User user, String opera, String xmdm, String xh, HttpServletResponse response) throws IOException {
		
		// 设置编码格式
		response.setContentType("text/html;charset=gbk");
		
		// ------------------评奖项目信息 begin -----------------------
		PjszPjxmService pjxmService = new PjszPjxmService();
		
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);
		// ------------------评奖项目信息 end  -----------------------
		HashMap<String,String>xssqMap=new HashMap<String,String>();
		
		// ------------------修改操作，查询已申请信息-----------------
		if("modi".equalsIgnoreCase(opera)){
			WdpjXssqDAO xssqDAO=new WdpjXssqDAO();
			xssqMap.putAll(xssqDAO.getXssqMap(xmdm, xh));
		}
		
		StringBuilder html = new StringBuilder();
		// ------------------上报或修改html的拼接--------------------
		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>学生上报</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("项目说明");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
		// 项目说明
		html.append(Base.isNull(pjxmModel.getXmsm())? "" : pjxmModel.getXmsm());
		
		html.append("</td>");
		html.append("</tr>");
		
		if("modi".equalsIgnoreCase(opera)){
			HashMap<String,String>xmshMap=dao.getXsshInfoMap(xmdm, xh);
			html.append("<tr>");
			html.append("<th width='25%'>");
			html.append("审核意见");
			html.append("</th>");
			html.append("<td style='word-break:break-all;width:96%'>");
			html.append(Base.isNull(xmshMap.get("shyj"))? "" : xmshMap.get("shyj") );
			html.append("</td>");
			html.append("</tr>");
		}
		
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("上报理由");
		html.append("<br/><font color=\"blue\">(限输入500字)</font>");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
	
		html.append("<textarea id=\"sqly\"  name=\"sqly\"  rows=\"8\" cols=\"\" style='word-break:break-all;width:96%'>");
		
		// 修改操作显示申请理由
		if("modi".equalsIgnoreCase(opera)){
			html.append(Base.isNull(xssqMap.get("sqly"))?"":xssqMap.get("sqly"));// 空字段处理
		}
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"save('"+opera+"','"+xmdm+"','"+xh+"');return false;\">");
		html.append("保 存");
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

	/**
	 * 学生信息(课程成绩)
	 * author qlj
	 */
	public List<HashMap<String,String>> getXscjList(String xh) throws IOException {
		return dao.getXscjList(xh);
	}
		
	
	/**
	 * 学生信息(课程成绩、综测成绩)
	 * author qlj
	 */
	public void showXsxxDiv(User user,String xmdm, String xh, HttpServletResponse response) throws IOException {
		
		XsxxglService stuService = new XsxxglService();
		
		response.setContentType("text/html;charset=gbk");
		
		PjpyZhcpService zhcpService = new PjpyZhcpService();
		
		HashMap<String,String>xsxxInfo=stuService.selectStuinfo(xh);

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\" style=\"overflow-x:hidden;overflow-y:auto;height:350px\" >");

		html.append("<table class=\"formlist\"  >");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>学生信息</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		// ---------------------学生基本信息 begin -----------------
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("学号");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:24%'>");
		html.append(xsxxInfo.get("xh"));
		html.append("</td>");
		html.append("<th width='25%'>");
		html.append("姓名");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:24%'>");
		html.append(xsxxInfo.get("xm"));
		html.append("</td>");
		html.append("</tr>");
		// ---------------------学生基本信息 end -----------------
		
		// ---------------------学生成绩信息 begin ---------------
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>学生成绩</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<table width=\"100%\">");
		html.append("<tr>");
		html.append("<th>");
		html.append("学年");
		html.append("</th>");
		html.append("<th>");
		html.append("学期");
		html.append("</th>");
		html.append("<th>");
		html.append("课程名称");
		html.append("</th>");
		html.append("<th>");
		html.append("课程性质");
		html.append("</th>");
		html.append("<th>");
		html.append("成绩");
		html.append("</th>");
		html.append("</tr>");
		
		List<HashMap<String,String>>xscjList=dao.getXscjList(xh);
		
		for(int i=0;i<xscjList.size();i++){
			
			HashMap<String,String>xscjMap=xscjList.get(i);
			html.append("<tr>");
			html.append("<td>");
			html.append(xscjMap.get("xn"));
			html.append("</td>");
			html.append("<td>");
			html.append(Base.isNull(xscjMap.get("xqmc"))?"":xscjMap.get("xqmc"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("kcmc"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("kcxz"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("cj"));
			html.append("</td>");
			html.append("</tr>");
		}
		html.append("</table>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ---------------------学生成绩信息 end -----------------
		
		// ---------------------本次综测成绩 begin----------------
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>综测成绩</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div style=\"overflow:auto;height:120px\">");
		html.append("<table width=\"100%\">");
		
		List<HashMap<String,Object>>bczcInfo=zhcpService.getBczcInfo(xh);
		for(int i=0;i<bczcInfo.size();i++){
			
			HashMap<String,Object>zccjMap=bczcInfo.get(i);
			
			HashMap<String,String>left=(HashMap<String,String>)zccjMap.get("left");
			
			HashMap<String,String>right=(HashMap<String,String>)zccjMap.get("right");
			html.append("<tr>");
			html.append("<th width='25%'>");
			html.append(Base.isNull(left.get("cn"))?"":left.get("cn"));
			html.append("</th>");
			html.append("<td width='25%'>");
			html.append(Base.isNull(left.get("zcinfo"))?"":left.get("zcinfo"));
			html.append("</td>");
			html.append("<th  width='25%'>");
			html.append(Base.isNull(right.get("cn"))?"":right.get("cn"));
			html.append("</th>");
			html.append("<td width='25%'>");
			html.append(Base.isNull(right.get("zcinfo"))?"":right.get("zcinfo"));
			html.append("</td>");
			html.append("</tr>");
			
		}
		html.append("</table>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ---------------------本次综测成绩 end-------------------
		

		html.append("</table>");
		html.append("</div>");
		
		html.append("<div class=\"open_win01\">");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\" align=\"right\">");
		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		
		
		response.getWriter().print(html.toString());
	}

	
	/**
	 * 获取人数控制范围
	 * 为班级的确定人数
	 * @param xmdm
	 * @param bjdm
	 * @return
	 */
	public HashMap<String,String>getQdrsByBj(String xmdm,String bjdm){
		
		WdpjPjtjDAO pjtjDAO = new WdpjPjtjDAO();
		
		return pjtjDAO.getQdrsByBj(xmdm,bjdm);
	}

	public Boolean updateLssbInfo(BasicModel mode, User user) {
		
		BasicService service=new BasicService();
		
		boolean flag=false;
		
		HashMap<String,String>modelMap=mode.getValueMap();
	
		mode.setValueMap(modelMap);
		
		flag= service.updateInfo(mode);
			
		if(flag){
			
			flag=dao.updateShzt(modelMap, user);
		}
		
		return flag;
	}
}
