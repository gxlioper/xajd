package xsgzgl.pjpy.general.wdpj.xssq;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_学生申请_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class WdpjXssqService extends BasicService implements WdpjXssqInterface {

	WdpjXssqDAO dao = new WdpjXssqDAO();

	/**
	 * 获得表头文件(我的评奖_学生申请)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getWdpjXssqTop(WdpjXssqModel model,
			User user) {
		
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "xmmc", "xmlx", "xmxz", "shqk", "cz" };
		String[] cn = new String[] { "项目名称", "项目类型", "项目性质", "目前审核情况", "操作" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * 查询数据(我的评奖_学生申请)
	 * 
	 * @author qlj
	 */
	public ArrayList<String[]> getWdpjXssqList(PjpyGeneralForm myForm,
			WdpjXssqModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getWdpjXssqList(myForm,model, user);
		
		return list;
	}

	/**
	 * 构建HTML(我的评奖_学生申请)
	 * 
	 * @author 伟大的骆
	 */
	public String createWdpjXssqHTML(SearchRsModel rsModel,
			WdpjXssqModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();
		
		if(rsArrList!=null && rsArrList.size()>0){
			
			for( int i=0;i<rsArrList.size();i++){
				
				String[]rs = rsArrList.get(i);
				
				String sqInfo=rs[rs.length-3];
				
				String xmdm=rs[rs.length-2];
				
				String pkValue=rs[rs.length-1];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				for( int j=0; j<rs.length-3;j++){
					
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:"+100/rs.length+"%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				
				}
				html.append("<td align=\"left\" style=\"width:"+100/rs.length+"%\" >");
				if("wsq".equalsIgnoreCase(sqInfo)){
					
					html.append("<a href=\"#\" onclick=\"checkXssqZg('add','"+xmdm+"');return false;\"><font color=\"blue\">申&nbsp;&nbsp;请</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">撤&nbsp;&nbsp;销</font></a>");
				
				}else if("ysq".equalsIgnoreCase(sqInfo)){
				
					html.append("<a href=\"#\" onclick=\"checkXssqZg('modi','"+xmdm+"');return false;\"><font color=\"blue\">修&nbsp;&nbsp;改</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"disfrockXssqInfo('"+pkValue+"');return false;\"><font color=\"blue\">撤&nbsp;&nbsp;销</font></a>");
				
				}else if("ysh".equalsIgnoreCase(sqInfo)){
					
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">已审核</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">撤&nbsp;&nbsp;销</font></a>");
				
				}
				html.append("</td>");
				html.append("</tr>");
			}
			
		}
		return html.toString();
	}

	/**
	 * 显示学生申请DIV
	 * 
	 * @author qlj
	 * @throws IOException 
	 * 
	 */
	public void showXssqDiv(String opera,String xmdm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		String xh=user.getUserName();
		HashMap<String,String>xssqMap=new HashMap<String,String>();
		if("modi".equalsIgnoreCase(opera)){
			xssqMap.putAll(dao.getXssqMap(xmdm, xh));
		}
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>学生申请</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("项目说明");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' colspan='3'>");
		// 项目说明
		html.append(Base.isNull(pjxmModel.getXmsm())?"":pjxmModel.getXmsm());
		html.append("</td>");
		html.append("</tr>");
		
		// ----------------------其他数据 加载 begin---------------------------
		HashMap<String, String> csMap = new HashMap<String, String>();
		csMap.put("xh", user.getUserName());
		String qtxx = createOtherInfo(csMap);
		if (!Base.isNull(qtxx)) {
			html.append(qtxx); // this write by 伪 shen 2013.1.9
		}
		// ----------------------其他数据 加载 end ---------------------------
		
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("申请理由");
		html.append("<br/><font color='blue'>(限输入500字)</font>");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' colspan='3'>");
	
		html.append("<textarea id=\"sqly\"  name=\"sqly\" onblur='chLeng(this,500);'  rows=\"8\" cols=\"\" style='word-break:break-all;width:96%'>");
		if("modi".equalsIgnoreCase(opera)){
			if (!Base.isNull(xssqMap.get("sqly"))) {
				html.append(xssqMap.get("sqly"));
			}
		}
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"4\">");
		html.append("<div class=\"btn\">");

		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"save('"+opera+"','"+xmdm+"');return false;\">");
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
	 * 显示学生申请DIV(北京联合)
	 * 
	 * @author qlj
	 * @throws IOException 
	 */
	public void showXssqDivForBJLH(String opera,String xmdm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);

		String xh=user.getUserName();
		HashMap<String,String>xssqMap=new HashMap<String,String>();
		if("modi".equalsIgnoreCase(opera)){
			xssqMap.putAll(dao.getXssqMap(xmdm, xh));
		}
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>学生申请</span>");
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
		html.append(Base.isNull(pjxmModel.getXmsm())?"":pjxmModel.getXmsm());
		
		html.append("</td>");
		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("申请理由");
		html.append("<br/><font color='blue'>(请输入300~500字)</font>");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
	
		html.append("<textarea id=\"sqly\"  name=\"sqly\"  rows=\"8\" cols=\"\" onblur=\"yzSqly();\" style='word-break:break-all;width:96%'>");
		if("modi".equalsIgnoreCase(opera)){
			if (!Base.isNull(xssqMap.get("sqly"))) {
				html.append(xssqMap.get("sqly"));
			}
		}
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"saveBJLH('"+opera+"','"+xmdm+"');return false;\">");
		html.append("保 存");
		html.append("</button>");

		html.append("<button type=\"button\" id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
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
	
	public Map<String, Object> defaultWdpjXssq(PjpyGeneralForm form, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	public boolean saveXssqInfo(BasicModel mode,HashMap<String, String> valueMap, User user) {

		PjszPjxmDAO pjxmDAO=new PjszPjxmDAO();
		
		HashMap<String,String>xmMap=pjxmDAO.getPjxmInfo(valueMap.get("xmdm"));
		
		if("no".equalsIgnoreCase(xmMap.get("sfsh"))){
			mode.getValueMap().put("sqjg", "wxsh");
		}else{
			mode.getValueMap().put("sqjg", "wsh");
		}
		
		return saveInfo(mode);
	}

	public boolean updateXssqInfo(BasicModel mode,HashMap<String, String> valueMap, User user) {
		
		HashMap<String,String>modelMap=mode.getValueMap();
		
		modelMap.putAll(valueMap);
		mode.setValueMap(modelMap);
		// TODO 自动生成方法存根
		return updateInfo(mode);
	}

	/**
	 * 学生申请by本周期
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXssqByZq(WdpjXssqModel model,
			User user) throws Exception {

		List<HashMap<String, String>> xssqList = dao.getXssqByZq(model, user);
		
		return xssqList;
	}

	/**
	 * 历史学生评奖信息
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getXssqInfo(WdpjXssqModel model,
			User user) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xn", "2011-2012");
		map.put("xmmc", "一等奖学金");
		map.put("xmlx", "奖学金");
		map.put("xmxz", "校内奖学金");
		map.put("xmje", "5000");

		list.add(map);

		map = new HashMap<String, String>();
		map.put("xn", "2011-2012");
		map.put("xmmc", "三好学生");
		map.put("xmlx", "荣誉称号");
		map.put("xmxz", "个人荣誉");
		map.put("xmje", "无");

		list.add(map);

		return list;
	}
	
	public boolean saveWdpjShInfo(String xmdm, User user) throws Exception{
		
		String xh=user.getUserName();
		
		return dao.saveWdpjShInfo(xmdm, xh);
		
	}

	/**
	 * 获取历年综测信息
	 */
	public List<String[]> getXszcInfo(WdpjXssqModel model, User user) {
		// TODO 自动生成方法存根
		String xh=user.getUserName();
		
		PjpyZhcpDAO dao=new PjpyZhcpDAO();
		
		return dao.getZcList(xh);
	}
	
	/**
	 * 获取学生本次综测信息
	 */
	public List<Object> getZcxxByZq(WdpjXssqModel model, User user) throws Exception {
		
		String xh=user.getUserName();
		
		PjpyZhcpDAO dao=new PjpyZhcpDAO();
		
		return dao.getZcByZqList(xh);
	}

	/**
	 * 获取其他数据
	 */
	public String createOtherInfo(HashMap<String,String>csMap) throws IOException {
		// TODO 自动生成方法存根
		return null;
	}
	

}
