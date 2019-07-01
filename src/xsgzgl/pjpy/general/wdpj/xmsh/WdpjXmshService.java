package xsgzgl.pjpy.general.wdpj.xmsh;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_项目审核_通用_Service类
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

public class WdpjXmshService extends CommService implements WdpjXmshInterface {

	WdpjXmshDAO dao = new WdpjXmshDAO();
	
	
	// ===========================评奖项目审核 结果集 begin =============================
	/**
	 * 获得需本用户审核项目
	 * @author qlj
	 */
	public List<HashMap<String, String>> getCshXmList(WdpjXmshModel model,
			User user) {

		List<HashMap<String, String>> list = dao.getCshXmList(model, user);
		
		return list;
	}
	
	/**
	 * 获得需本用户审核项目
	 * (考虑审核开关及时间控制)
	 * @author qlj
	 */
	public List<HashMap<String, String>> getShxmList(WdpjXmshModel model, User user) {
		
		return dao.getShxmList(model, user);
	}
	
	/**
	 * 获得表头(我的评奖_项目审核)
	 * @author qlj
	 */
	public List<HashMap<String, String>> getWdpjXmshTop(WdpjXmshModel model,
			User user) {
		
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "nj", "bjmc", "sqsj",
				"shzt" };
		String[] cn = new String[] { "", "学号", "姓名", "年级", "班级", "申请时间", "审核状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * 获取本级岗位审核信息
	 * @author qlj
	 */
	public ArrayList<String[]> getWdpjXmshList(PjpyGeneralForm myForm,
			WdpjXmshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		ArrayList<String[]> list = dao.getWdpjXmshList(myForm,model, user);
		
		return list;
	}

	/**
	 * 构建结果集(我的评奖_项目审核)
	 * @author qlj
	 */
	public String createWdpjXmshHTML(SearchRsModel rsModel,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user) {
	
		// IE版本
		String ie = rsModel.getIe();
		// V4路径
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String disabled = rs[0];
				String pk = rs[1];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				
				if(!Base.isNull(disabled)){
					html.append(" disabled=\"true\"");
				}
				
				html.append( "value=\"" + pk + "\"/>");
				html.append("</td>");
				
				//------------2013.01.05 begin-----------------
				//------------edit by 伟大的骆-----------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");

				html.append("<a href=\"#\" ");
				html.append("onclick=\"showXsxxDetail('" + rs[2] + "')\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append(rs[2]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");
				
				for (int j = 3; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}

				//------------2013.01.05 end-----------------
				
				if("未审核".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("通过".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("不通过".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("退回".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("需重审".equalsIgnoreCase(rs[rs.length-1])){
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
	 * 加载其它数据(我的评奖_项目审核)
	 * @author qlj
	 * @throws Exception 
	 */
	public String createKidneyDiv(SearchRsModel rsModel,RequestForm rForm,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user) throws Exception {
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(jbszModel);
		
		StringBuilder html=new StringBuilder();
		
		String[]qtzd=rForm.getQtzd();
		
		String[]qtzdz=rForm.getQtzdz();
		
		for(int i=0;i<qtzd.length;i++){
			
			html.append(" <input type=\"hidden\" name=\"hid_"+qtzd[i]+"\" id =\"hid_"+qtzd[i]+"\" value=\""+qtzdz[i]+"\"  >");
			
		}

		boolean checkShkz=pjtjService.checkShkz(model.getXmdm());
		
		html.append(" <input type=\"hidden\" name=\"shkz\" id =\"shkz\" value=\""+checkShkz+"\"  >");
		return html.toString();
		
	}

	// ===========================评奖项目审核 结果集 end =============================
	
	
	// ===========================评奖岗位信息 begin===============================
	/**
	 * 获得项目审核岗位
	 * 
	 * @author qlj
	 */
	public List<HashMap<String,String>> getSpgwList(WdpjXmshModel model,User user) {

		return dao.getSpgwList(model,user);
	}
	
	/**
	 * 显示本用户岗位切换模式窗口
	 * @author qlj
	 * @throws IOException 
	 */
	public void showShgwDiv(WdpjXmshModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		List<HashMap<String,String>>spgwList=dao.getSpgwList(model, user);
		
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
		
		html.append(" <input type=\"hidden\" name=\"text_xmdm\" id=\"text_xmdm\" value=\""+model.getXmdm()+"\" /> ");
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
	
	
	/**
	 * 获得项目审核岗位
	 * 
	 * @author qlj
	 */
	public boolean checkFirstSpgw(WdpjXmshModel model, User user) {
		
		// 获取上级审核岗位
		HashMap<String, String> higherUp = dao.getHigherUpSpMap(model, user);

		boolean bool = true;

		if (!(higherUp != null && higherUp.size() > 0)) {
			
			bool = false;
			
		}
		// true:存在上级
		// false:不存在上级
		// 不存在上级审核则本级审核为第一级审核岗位
		return bool;
	}
	// ===========================评奖岗位信息 end===============================	
	
	
	// ===========================评奖项目审核详细 begin===============================
	public HashMap<String, Object> defaultWdpjXmsh(WdpjXmshModel model, User user) throws Exception {

		HashMap<String,Object>rs=new HashMap<String,Object>();
		
		WdpjLssbService xmsbService=new WdpjLssbService();
		
		PjpyZhcpService zhcpService=new PjpyZhcpService();
		
		String xmdm=model.getXmdm();
		
		String xh=model.getXh()[0];
		
		// 评奖评优学生申请信息（学生基本信息）
		rs.putAll(dao.getXmsqInfo(model, user));
		// 评奖项目审核信息
		rs.put("xmshInfo",dao.getXmshInfo(model, user));
		// 学生课程成绩
		rs.put("kccjInfo",xmsbService.getXscjList(xh));
		// 学生综测成绩
		rs.put("zccjInfo",zhcpService.getBczcInfo(xh));
		return rs;
	}
	// ===========================评奖项目审核详细 end===============================

	
	// ===========================审核状态修改 begin===============================
	public boolean updateShzt(WdpjXmshModel model, HttpServletRequest request, User user) throws Exception {

		getModelValue(model, request);
		
		String shzt=model.getShzt();
		
		dao.resultShzt(model, user);
		
		boolean flag=dao.updateShzt(model, user);
		
		if("th".equalsIgnoreCase(shzt) && flag){
			
			 flag=dao.updateThzt(model, user);
		}
		
		flag=dao.updateSqjg(model, user);
		
		return  flag;
	}
	// ===========================审核状态修改 end===============================	
	
	
	
}
