package xsgzgl.pjpy.xzgyzyjsxy.wdpj.lssb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.pjtj.WdpjPjtjDAO;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.xzgyzyjsxy.zhcp.PjpyZhcpService;

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
		
		String[]other=new String[]{"zd1","zd25"};
		
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
		
		String[]otherCn=new String[]{"综测分","综测等级"};
		
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

	// --------------------------老师上报结果集相关信息 end----------------------
	
	
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

}
