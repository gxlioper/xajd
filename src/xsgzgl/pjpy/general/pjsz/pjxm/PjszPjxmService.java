package xsgzgl.pjpy.general.pjsz.pjxm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖项目_Service类
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

public class PjszPjxmService extends CommService implements PjszPjxmInterface {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	PjszPjxmDAO dao = new PjszPjxmDAO();

	/**
	 * 获得表头文件(评奖设置_评奖项目)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjszPjxmTop(PjszPjxmModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xmmc", "xmlx", "xmxz","sfysq", "tjsz",
				"rskz", "jdkz", "sqzt", "shzt" };
		String[] cn = new String[] { "", "项目名称", "项目类型", "项目性质","是否已申请", "条件设置",
				"人数设置", "兼得设置", "申请状态", "审核状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集(评奖设置_评奖项目)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getPjszPjxmList(PjpyGeneralForm myForm,
			PjszPjxmModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszPjxmList(myForm, user);
	}

	/**
	 * 构建结果集(评奖设置_评奖项目)
	 * 
	 * @author 伟大的骆
	 */
	public String createPjszPjxmHTML(SearchRsModel rsModel,
			PjszPjxmModel model, ArrayList<String[]> rsArrList, User user) {
	
		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					
					if(j!=10){
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						
	//					if(!"未调换".equalsIgnoreCase(pjbjmc)){
	//						html.append("bgcolor=\"#CCFFFF\"");
	//					}
						
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						
						if (j==1) {
							html.append("title=\""+rs[10]+"\"");
						} 
						
						html.append(">");
						
							
						if(j>4){
							html.append("<a href=\"#\"  onclick=\"");
							switch(j){
								case 5: html.append("showXmszPjtj('"+pk+"')");break; 
								case 6: html.append("showXmszRssz('"+pk+"')");break; 
								case 7: html.append("showXmszXmjd('"+pk+"')");break; 
								case 8: html.append("showXmszSjsz('"+pk+"')");break; 
								case 9: html.append("showXmszSjsz('"+pk+"')");break; 
							}
							html.append("\" >");
							html.append("<font ");
							if ("未设置".equalsIgnoreCase(rs[j])
									|| "关闭申请".equalsIgnoreCase(rs[j])
									|| "关闭审核".equalsIgnoreCase(rs[j])) {
								html.append("color=\"red\" ");
							} else if ("已设置".equalsIgnoreCase(rs[j])
									|| "开放申请".equalsIgnoreCase(rs[j])
									|| "开放审核".equalsIgnoreCase(rs[j])) {
								html.append("color=\"green\" ");
							}else{
								html.append("color=\"black\" ");
							}
							html.append(">");
							html.append(rs[j]);
							html.append("</font>");
							html.append("</a>");
						}else {
						
							html.append(rs[j]);
						}
						html.append("</td>");
					}
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	
	}
	
	/**
	 * 初始化评奖项目设置
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void defaultPjxmSetting(PjszPjxmModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
	
		//操作步骤
		String step = model.getStep();
		StringBuilder html = new StringBuilder();
		
		if (Base.isNull(step)) {// 评奖基本设置
			html.append(getPjjbszHTML(model, user));
		} else if ("shlcsz".equalsIgnoreCase(step)) {// 审核流程设置
			html.append(getShlcszHTML(model, user));
		} else if ("rskzsz".equalsIgnoreCase(step)) {// 人数控制设置
			html.append(getRskzszHTML(model, user));
		}
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 初始化评奖项目设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	public void defaultPjxmUpdate(PjszPjxmModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
	
		StringBuilder html = new StringBuilder();
		
		html.append(getPjxmUpdateHTML(model, user));
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 获得评奖基本设置HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private String getPjjbszHTML(PjszPjxmModel model, User user) {

		// 项目性质列表
		List<HashMap<String, String>> xmxzList = getWhList("xg_pjpy_xmxzb",
				"xzdm", "xzmc", "", "", "", false);

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:99%;height:310px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"99%\" border=\"0\" class=\"formlist\">");
		// -------------------------表头---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>评奖项目设置</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		// -------------------------内容---------------------------
		html.append("<tbody>");
		// 项目名称
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("<font color=\"red\">*</font>");
		html.append("项目名称");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<input type=\"text\" name=\"xmmc\"");
		html.append("style=\"width:95%\" onblur=\"checkXmmc(this.value)\" ");
		html.append("id=\"xmmc\" maxlength=\"25\"/>");
		html.append("</td>");
		html.append("</tr>");
		// 项目类型
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("项目类型");
		html.append("</th>");
		html.append("<td width=\"25%\">");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_01\" value=\"01\"");
		html.append("checked=\"checked\"");
		html.append("/>");
		html.append("奖学金");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_02\" value=\"02\"");
		html.append("/>");
		html.append("荣誉称号");
		html.append("</td>");
		// 申请方式
		html.append("<th width=\"20%\">");
		html.append("申请方式");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_xssq\" value=\"xssq\"");
		html.append("checked=\"checked\"");
		html.append("/>");
		html.append("学生申请");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_lssb\" value=\"lssb\"");
		html.append("/>");
		html.append("老师上报");
		html.append("</td>");
		html.append("</tr>");
		// 项目性质
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("项目性质");
		html.append("</th>");
		html.append("<td width=\"\"");
		html.append(">");
		html.append("<select id=\"xmxz\">");
		if (xmxzList != null && xmxzList.size() > 0) {
			for (int i = 0; i < xmxzList.size(); i++) {
				String xmxzdm = xmxzList.get(i).get("dm");
				String xmxzmc = xmxzList.get(i).get("mc");
				html.append("<option value=\"" + xmxzdm + "\">");
				if (xmxzmc.length() < 6) {
					html.append(xmxzmc);
				} else {
					html.append(xmxzmc.substring(0, 6) + "...");
				}
				html.append("</option>");
			}
		}
		html.append("</select>");
		html.append("</td>");
		// 项目金额
		html.append("<th width=\"\">");
		html.append("项目金额");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" name=\"xmje\" id=\"xmje\" ");
		html.append("onkeydown=\"return onlyNum(this,5)\" ");
		html.append("onmousedown=\"return onlyNum(this,5)\" ");
		html.append("maxlength=\"5\"");
		html.append("style=\"width:50px;ime-mode:disabled\" ");
		html.append("/>(元)");
		html.append("</td>");
		html.append("</tr>");	
		// 显示顺序
//		html.append("<tr>");
//		html.append("<th width=\"\">");
//		html.append("显示顺序");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"text\" name=\"xssx\" id=\"xssx\" ");
//		html.append("onkeydown=\"return onlyNum(this,3)\" ");
//		html.append("onmousedown=\"return onlyNum(this,3)\" ");
//		html.append("maxlength=\"3\"");
//		html.append("style=\"width:50px;ime-mode:disabled\" ");
//		html.append("/>");
//		html.append("</td>");
		// 是否启用
//		html.append("<th>");
//		html.append("是否启用");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_yes\" value=\"yes\"");
//		html.append("checked=\"checked\"");
//		html.append("/>");
//		html.append("是");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_no\" value=\"no\"");
//		html.append("/>");
//		html.append("否");
//		html.append("</td>");
//		html.append("</tr>");	
		// 是否需要审核
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("是否需要审核");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_yes\" value=\"yes\"");
		html.append("checked=\"checked\" onclick=\"checkBtn();\" ");
		html.append("/>");
		html.append("是");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_no\" value=\"no\"");
		html.append("onclick=\"checkBtn();\"/>");
		html.append("否");
		html.append("</td>");
		// 是否需要人数控制
		html.append("<th width=\"\">");
		html.append("是否需要人数控制");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_yes\" value=\"yes\"");
		html.append("checked=\"checked\" onclick=\"checkBtn();\" ");
		html.append("/>");
		html.append("是");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_no\" value=\"no\"");
		html.append("onclick=\"checkBtn();\" />");
		html.append("否");
		html.append("</td>");
		html.append("</tr>");
		// 项目说明
		html.append("<tr>");
		html.append("<th>");
		html.append("项目说明");
		html.append("<br/>");
		html.append("<font color=\"blue\">(限制录入500字)</font>");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<textarea id=\"xmsm\" rows=\"5\"");
		html.append("onblur=\"chLeng(this,500)\"");
		html.append("  style=\"word-break:break-all;width:95%\" ");
		html.append("/>");
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------内容 end---------------------------
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------按钮---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"下一步\" id=\"btn_next\" onclick=\"nextStep('');return false;\" disabled=\"true\">下一步</button>");
		html.append("<button type=\"button\"  name=\"保存\" id=\"btn_bc\" onclick=\"checkSavePjxm();return false;\" disabled=\"true\" style=\"display:none\">保 存</button>");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"Close();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------按钮 end---------------------------
		
		return html.toString();
	}
	
	/**
	 * 获得审核流程设置HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private String getShlcszHTML(PjszPjxmModel model, User user) {

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:310px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");

		// -------------------------审核流选择---------------------------
		html.append("<tbody>");
		
		// 审核流选择
		html.append("<tr>");
		html.append("<td width=\"\">");
		
		html.append("<table width=\"100%\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th>");
		html.append("<span>请选择该评奖项目的审核流程</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// 获得审核流程列表
		List<HashMap<String, String>> shlcList = dao.getShlcList();
		if (shlcList != null && shlcList.size() > 0) {
			for (int i = 0; i < shlcList.size(); i++) {
				html.append("<tr>");
				html.append("<td>");

				String lcid = shlcList.get(i).get("lcid");
				String lcmc = shlcList.get(i).get("lcmc");
				String gzgw = shlcList.get(i).get("gzgw");

				html.append("<input type=\"radio\" name=\"lcid\" id=\"lcid_"
						+ lcid + "\" ");
				html.append("value=\"" + lcid + "\" ");
				html.append("onclick=\"clickShlc('" + lcid + "')\" ");
				html.append("/>");
				html.append(lcmc);
				html.append("：");
				String[] arr_gw = gzgw.split(",");
				if (arr_gw != null && arr_gw.length > 0) {
					for (int j = 0; j < arr_gw.length; j++) {
						if (j != 0) {
							html.append("-->");
						}
						html.append(arr_gw[j]);
					}
				}

				html.append("</td>");
				html.append("</tr>");
			}
		}else{
			html.append("<tr>");
			html.append("<td>");
			html.append("系统尚未给评奖评优模块定制审核流程");
			html.append("<br/>");
			html.append("请前往");
			html.append("<font color=\"blue\">");
			html.append("系统维护 - 审批流程维护 - 审批流程");
			html.append("</font>");
			html.append("进行设置");
			html.append("<br/>");
			html.append("注：若您没有相应的权限，请联系管理员");
			html.append("</td>");
			html.append("<tr>");
		}
		html.append("</table>");

		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		// -------------------------审核流选择 end---------------------------
		html.append("</table>");
		
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");	
		// -------------------------级别控制选择---------------------------
		html.append("<tbody>");
		
		// 审核流选择
		html.append("<tr>");
		html.append("<td width=\"\">");
		html.append("<div id=\"div_shgw\">");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		// -------------------------级别控制选择 end---------------------------
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------按钮---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"上一步\" onclick=\"previousStep('shlcsz');return false;\">上一步</button>");
		html.append("<button type=\"button\"  name=\"下一步\" id=\"btn_next\" onclick=\"nextStep('shlcsz');return false;\">下一步</button>");
		html.append("<button type=\"button\"  name=\"保存\" id=\"btn_bc\" onclick=\"checkSavePjxm();return false;\" style=\"display:none\">保 存</button>");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"Close();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------按钮 end---------------------------
		
		return html.toString();
	}

	/**
	 * 获得人数控制设置HTML
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	private String getRskzszHTML(PjszPjxmModel model, User user) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		//是否需要参评组
		String cpz = jbszModel.getCpz();
			
		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:310px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");

		// -------------------------表头---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>人数控制范围</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		html.append("<tbody>");
		
		// 人数控制级别选择
		html.append("<tr>");
		
		if("yes".equalsIgnoreCase(cpz)){
			html.append("<td width=\"\">");
			html.append("<table width=\"100%\">");
			html.append("<tr>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("年级人数");
			html.append("</td>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append(Base.YXPZXY_KEY);
			html.append("人数");
			html.append("</td>");
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("年级+").append(Base.YXPZXY_KEY).append("人数");
			html.append("</td>");
			html.append("</tr>");
			html.append("<tr>");	
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("年级+专业人数");
			html.append("</td>");
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("班级人数");
			html.append("</td>");
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_cpz\" value=\"cpz\" checked=\"true\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("参评组人数");
			html.append("</td>");
		}else{
			html.append("<td width=\"\">");
			html.append("<table width=\"100%\">");
			html.append("<tr>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" ");
			html.append("/>");
			html.append("年级人数");
			html.append("</td>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append(Base.YXPZXY_KEY);
			html.append("人数");
			html.append("</td>");
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("年级+").append(Base.YXPZXY_KEY).append("人数");
			html.append("</td>");
			html.append("</tr>");
			html.append("<tr>");	
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("年级+专业人数");
			html.append("</td>");
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\"  checked=\"true\"  ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("班级人数");
			html.append("</td>");
			html.append("<td>");
		
			html.append("</td>");
		}
		
		html.append("</tr>");
		html.append("</table>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		
		// -------------------------表头---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>特殊人群选择</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		//特殊人群列表
		List<HashMap<String,String>> tsrqList = jbszModel.getTsrqList();
		
		html.append("<tbody>");
		
		// 人数控制级别选择
		html.append("<tr>");
		html.append("<td width=\"\">");
		
		html.append("<table width=\"100%\">");
		html.append("<tr>");	
		for (int i = 0; i < tsrqList.size(); i++) {
			HashMap<String, String> tsrq = tsrqList.get(i);
			html.append("<td width=\"33%\">");
			if(!Base.isNull(tsrq.get("tsrqdm"))){
				html.append("<input type=\"radio\" name=\"tsrq\" id=\"tsrq_"
						+ tsrq.get("tsrqdm") + "\" ");
				html.append("value=\"" + tsrq.get("tsrqdm") + "\" ");
				html.append("onclick=\"$('hidden_tsrq').value=this.value\"");
				html.append("/>");
				html.append(tsrq.get("tsrqmc"));
			}
			html.append("</td>");
		}
		html.append("</tr>");
		html.append("</table>");
		
//		for(int i=0;i<tsrqList.size();i++){
//			HashMap<String,String> tsrq = tsrqList.get(i);
//			html.append("<input type=\"radio\" name=\"tsrq\" id=\"tsrq_"+tsrq.get("tsrqdm")+"\" ");
//			html.append("value=\""+tsrq.get("tsrqdm")+"\" ");
//			html.append("onclick=\"$('hidden_tsrq').value=this.value\"");
//			html.append("/>");
//			html.append(tsrq.get("tsrqmc"));
//			html.append("<br/><br/>");
//		}
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------按钮---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"上一步\" onclick=\"previousStep('rskzsz');return false;\">上一步</button>");
		html.append("<button type=\"button\"  name=\"保存\" onclick=\"checkSavePjxm();return false;\">保 存</button>");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"Close();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------按钮 end---------------------------
		
		return html.toString();
	}
	
	/**
	 * 初始化审核流程岗位信息
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void defaultShlcGwxx(PjszPjxmModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
	
		// 流程ID
		String lcid = model.getLcid();
		// 人数设置
		String rssz = model.getRssz();
		
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		// -------------------------表头---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		//html.append("<th colspan=\"4\">	");
		//html.append("<span>评奖项目设置</span>");
		//html.append("</th>");
		
		html.append("<th>");
		html.append("控制流程");
		html.append("</th>");
		html.append("<th>");
		html.append("条件控制");
		html.append("</th>");
		if ("yes".equalsIgnoreCase(rssz)) {
			html.append("<th>");
			html.append("人数控制");
			html.append("</th>");
		}
		html.append("<th>");
		html.append("兼得控制");
		html.append("</th>");
		
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		// -------------------------内容---------------------------
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("申请/上报");
		html.append("</th>");
		//条件控制
		html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
		html.append("<input type=\"radio\" ");
		html.append("checked=\"checked\" ");
		html.append("/>");
		html.append("</td>");
		
		if ("yes".equalsIgnoreCase(rssz)) {
			// 人数控制
			html.append("<td width=\"\" bgcolor=\"#FFF5EE\">");
			html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_sqsb\" ");
			html.append("value=\"sqsb\" ");
			html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
			html.append("/>");
			html.append("</td>");
		}
		
		//兼得控制
		html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
		html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_sqsb\" ");
		html.append("value=\"sqsb\" ");
		html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
		html.append("/>");
		html.append("</td>");
		
		html.append("</tr>");
		
		List<HashMap<String, String>> gwxxList = dao.getGwxxList(lcid);
		if (gwxxList != null && gwxxList.size() > 0) {
			for (int i = 0; i < gwxxList.size(); i++) {
				HashMap<String, String> map = gwxxList.get(i);
				String gwdm = map.get("gwdm");
				String gwmc = map.get("gwmc");
				
				html.append("<tr>");
				html.append("<th width=\"25%\">");
				html.append(gwmc);
				html.append("</th>");
				
				//条件控制
				html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
				html.append("<input type=\"radio\" ");
				html.append("disabled=\"true\" ");
				html.append("/>");
				html.append("</td>");
				
				if ("yes".equalsIgnoreCase(rssz)) {
					//人数控制
					html.append("<td width=\"\" bgcolor=\"#FFF5EE\">");
					html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_"+gwdm+"\" ");
					html.append("value=\""+gwdm+"\" ");
					html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
					if (i == gwxxList.size() - 1) {
						html.append("checked=\"checked\" ");
					}
					html.append("/>");
					html.append("</td>");
				}
				
				//兼得控制
				html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
				html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_"+gwdm+"\" ");
				html.append("value=\""+gwdm+"\" ");
				html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
				if (i == gwxxList.size() - 1) {
					html.append("checked=\"checked\" ");
				}
				html.append("/>");
				html.append("</td>");
			}
		} else {

		}
		
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------内容 end---------------------------
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * 初始化审核流程岗位信息
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public String defaultShlcGwxx(PjszPjxmModel model,HashMap<String,String>pjxmMap, User user) throws IOException {

		// 流程ID
		String lcid = model.getLcid();
		// 人数设置
		String rssz = pjxmMap.get("rssz");
		// 判断项目是否做过人数设置
		String checkRssz=pjxmMap.get("checkRssz");
		// 判断
		String checkXssq=pjxmMap.get("checkXssq");
		
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		// -------------------------表头---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		//html.append("<th colspan=\"4\">	");
		//html.append("<span>评奖项目设置</span>");
		//html.append("</th>");
		
		html.append("<th>");
		html.append("控制流程");
		html.append("</th>");
		html.append("<th>");
		html.append("条件控制");
		html.append("</th>");
		
		
		html.append("<th name=\"rskzArr\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(" >人数控制");
		html.append("</th>");
		
		html.append("<th>");
		html.append("兼得控制");
		html.append("</th>");
		
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		// -------------------------内容---------------------------
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("申请/上报");
		html.append("</th>");
		//条件控制
		html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
		html.append("<input type=\"radio\" ");
		html.append("checked=\"checked\" ");
		html.append("/>");
		//html.append("条件控制");
		html.append("</td>");
		
		String rskz=pjxmMap.get("rskz");
		String jdkz=pjxmMap.get("jdkz");
		
		//人数控制
		
		html.append("<td width=\"\" bgcolor=\"#FFF5EE\" name=\"rskzArr\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");
		html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_sqsb\" ");
		html.append("value=\"sqsb\" ");
		html.append((Boolean.parseBoolean(checkXssq) || Boolean
				.parseBoolean(checkRssz)) ? "" : " disabled ");
		html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
		if ("sqsb".equals(rskz)) {
			html.append("checked=\"checked\" ");
		}
		html.append("/>");
		html.append("</td>");
		
		
		//兼得控制
		html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
		html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_sqsb\" ");
		html.append("value=\"sqsb\" ");
		html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
		if ("sqsb".equals(jdkz)) {
			html.append("checked=\"checked\" ");
		}
		html.append(Boolean.parseBoolean(checkXssq)? "": " disabled " );
		html.append("/>");
		//html.append("兼得控制");
		html.append("</td>");
		
//		html.append("<td width=\"\" colspan=\"3\">");
//		// 人数设置
//		if ("yes".equalsIgnoreCase(rssz)) {
//			html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_sqsb\" ");
//			html.append("value=\"sqsb\" ");
//			html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
//			html.append("/>");
//			html.append("人数控制");
//		}
//		
//		html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_sqsb\" ");
//		html.append("value=\"sqsb\" ");
//		html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
//		html.append("/>");
//		html.append("兼得控制");
//		
//		html.append("<input type=\"radio\" name=\"xmsy\" id=\"xmsy_sqsb\" ");
//		html.append("value=\"sqsb\" ");
//		html.append("onclick=\"$('hidden_xmsy').value=this.value\" ");
//		html.append("/>");
//		html.append("项目顺延");
//		html.append("</td>");
		html.append("</tr>");
		
		
		List<HashMap<String, String>> gwxxList = dao.getGwxxList(lcid);
		if (gwxxList != null && gwxxList.size() > 0) {
			for (int i = 0; i < gwxxList.size(); i++) {
				HashMap<String, String> map = gwxxList.get(i);
				String gwdm = map.get("gwdm");
				String gwmc = map.get("gwmc");
				
				html.append("<tr>");
				html.append("<th width=\"25%\">");
				html.append(gwmc);
				html.append("</th>");
				
				//条件控制
				html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
				html.append("<input type=\"radio\" ");
				html.append("disabled=\"true\" ");
				html.append("/>");
				//html.append("条件控制");
				html.append("</td>");
				
				//人数控制
				
				html.append("<td width=\"\" bgcolor=\"#FFF5EE\" name=\"rskzArr\" ");
				if("no".equalsIgnoreCase(rssz)){
					html.append(" style=\"display:none\" ");
				}
				html.append(">");
				html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_"+gwdm+"\" ");
				html.append("value=\""+gwdm+"\" ");
				html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
				if (gwdm.equals(rskz)) {
					html.append("checked=\"checked\" ");
				}
				html.append((Boolean.parseBoolean(checkXssq) || Boolean
						.parseBoolean(checkRssz)) ? "" : " disabled ");
				html.append("/>");
				//html.append("人数控制");
				html.append("</td>");
				
				
				//兼得控制
				html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
				html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_"+gwdm+"\" ");
				html.append("value=\""+gwdm+"\" ");
				html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
				if (gwdm.equals(jdkz)) {
					html.append("checked=\"checked\" ");
				}
				html.append(Boolean.parseBoolean(checkXssq)? "" : " disabled " );
				html.append("/>");
				//html.append("兼得控制");
				html.append("</td>");
			}
		} else {

		}
		
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------内容 end---------------------------
		html.append("</table>");

		return html.toString();
	}

		/**
	 * 保存评奖项目
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjxm(PjszPjxmModel model, User user) {

		boolean flag = false;

		String lcid = model.getLcid();// 流程ID

		String rssz = model.getRssz();// 人数设置

		String xmmc = model.getXmmc();// 项目名称

		String xmlx = model.getXmlx();// 项目类型

		String xmxz = model.getXmxz();// 项目性质

		String sqfs = model.getSqfs();// 申请方式

		String xmje = model.getXmje();// 项目金额

		String xssx = model.getXssx();// 显示顺序

		String sfsh = model.getSfsh();// 是否审核

		String sfqy = model.getSfqy();// 是否启用
		
		String xmsm = model.getXmsm();// 项目说明

		String rskz = model.getRskz();// 人数控制

		String jdkz = model.getJdkz();// 兼得控制

		String xmsy = model.getXmsy();// 项目顺延

		String kzfw = model.getKzfw();// 控制范围
		
		String tsrq = model.getTsrq();// 特殊人群
		
		try {
			flag = dao.insertPjxmwhb(xmmc, xmlx, xmxz, sqfs, xmje, lcid, rssz,
					xssx, sfsh, sfqy, xmsm, rskz, jdkz, xmsy, kzfw, tsrq, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 保存评奖项目
	 * 
	 * @author 伟大的骆
	 */
	public Boolean updatePjxm(PjszPjxmModel model, User user) {

		boolean flag = false;

		String lcid = model.getLcid();// 流程ID

		String rssz = model.getRssz();// 人数设置
		
		String xmdm = model.getXmdm();// 项目名称

		String xmmc = model.getXmmc();// 项目名称

		String xmlx = model.getXmlx();// 项目类型

		String xmxz = model.getXmxz();// 项目性质

		String sqfs = model.getSqfs();// 申请方式

		String xmje = model.getXmje();// 项目金额

		String xssx = model.getXssx();// 显示顺序

		String sfsh = model.getSfsh();// 是否审核

		String sfqy = model.getSfqy();// 申请方式

		String xmsm = model.getXmsm();// 项目说明

		String rskz = model.getRskz();// 人数控制

		String jdkz = model.getJdkz();// 兼得控制

		String xmsy = model.getXmsy();// 项目顺延

		String kzfw = model.getKzfw();// 控制范围
		
		String tsrq = model.getTsrq();// 特殊人群
		
		if ("no".equalsIgnoreCase(sfsh)) {
			lcid = "";
		}
		
		try {
			flag = dao.updatePjxmwhb(xmdm,xmmc, xmlx, xmxz,
					sqfs, xmje, lcid, rssz, xssx,
					sfsh, sfqy, xmsm, rskz, jdkz,
					xmsy, kzfw, tsrq,  user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 删除评奖项目
	 * 
	 * @author qlj
	 */
	public Boolean deletePjxm(PjszPjxmModel model, User user) {

		boolean flag = false;
		
		String[]pkValue=model.getPkValue();
		try {
			
			flag = dao.deletePjxmwhb(pkValue, user);
		
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 删除评奖项目
	 * 
	 * @author qlj
	 */
	public Boolean checkSfsq(PjszPjxmModel model, User user) {

		boolean flag = false;
		
		String[]pkValue=model.getPkValue();
		try {
			
			flag = dao.deletePjxmwhb(pkValue, user);
		
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 获得评奖项目相关信息(Model)
	 * 
	 * @author 伟大的骆
	 */
	public PjszPjxmModel getPjxmModel(String xmdm, User user) {
		
		PjszPjxmModel model = new PjszPjxmModel();
		HashMap<String, String> map = dao.getPjxmInfo(xmdm);
			
		try {
			getModel(model, map);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return model;
	}

	/**
	 * 获得评奖项目相关信息(Map)
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getPjxmMap(String xmdm, User user) {
		HashMap<String, String> map = dao.getPjxmInfo(xmdm);
		return map;
	}

	/**
	 * 检测项目名称
	 * 
	 * @author 伟大的骆
	 */
	public boolean checkXmmc(PjszPjxmModel model) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 项目名称
		String xmmc = model.getXmmc();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		String tableName = "xg_pjpy_pjxmwhb";
		String dm = "xmdm";
		String pk = "xmmc||pjxn||pjxq||pjnd";
		String pkValue = xmmc + pjxn + pjxq + pjnd;

		String xmdm = getOneValue(tableName, dm, pk, pkValue);

		boolean flag = Base.isNull(xmdm) ? true : false;

		return flag;
	}

	/**
	 * 获得老师评奖项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getLssbXmList() {
		List<HashMap<String, String>> list = dao.getLssbXmList();
		return list;
	}
	
	/**
	 * 获得评奖基本设置HTML
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 * @throws IOException
	 */
	private String getPjxmUpdateHTML(PjszPjxmModel model, User user) throws Exception {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String xmdm=model.getXmdm();
		
		HashMap<String,String>pjxmMap=dao.getPjxmInfo(xmdm);
		
		boolean checkRssz=checkRssz(model, user);
		
		boolean checkXssq=checkXssq(model, user);
		
		// 项目性质列表
		List<HashMap<String, String>> xmxzList = getWhList("xg_pjpy_xmxzb",
				"xzdm", "xzmc", "", "", "", false);

		StringBuilder html = new StringBuilder();

//		html.append("<div style=\"width:100%;height:430px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\">");
		// -------------------------表头---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>评奖项目设置</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		// -------------------------内容---------------------------
		html.append("<tbody>");
		// 项目名称
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("<font color=\"red\">*</font>");
		html.append("项目名称");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<input type=\"text\" name=\"xmmc\"");
		html.append("style=\"width:95%\" onblur=\"checkXmmc(this.value)\" ");
		html.append(" id=\"xmmc\" ");
		html.append(" value=\""+pjxmMap.get("xmmc")+"\"");;
		html.append(" maxlength=\"25\"/>");
		
		html.append("<input type=\"hidden\" name=\"hid_xmmc\"");
		html.append(" id=\"hid_xmmc\" ");
		html.append(" value=\""+pjxmMap.get("xmmc")+"\" />");
		html.append("</td></tr>");
		// 项目类型
		String xmlx=pjxmMap.get("xmlx");
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("项目类型");
		html.append("</th>");
		html.append("<td width=\"25%\">");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_01\" value=\"01\"");
		html.append("01".equalsIgnoreCase(xmlx)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled" );
		html.append("/>");
		html.append("奖学金");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_02\" value=\"02\"");
		html.append("02".equalsIgnoreCase(xmlx)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("荣誉称号");
		html.append("</td>");
		// 申请方式
		String sqfs=pjxmMap.get("sqfs");
		html.append("<th width=\"20%\">");
		html.append("申请方式");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_xssq\" value=\"xssq\"");
		html.append("xssq".equalsIgnoreCase(sqfs)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("学生申请");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_lssb\" value=\"lssb\"");
		html.append("lssb".equalsIgnoreCase(sqfs)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("老师上报");
		html.append("</td>");
		html.append("</tr>");
		// 项目性质
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("项目性质");
		html.append("</th>");
		html.append("<td width=\"\"");
		html.append(">");
		
		String xmxz=pjxmMap.get("xmxz");
		html.append("<select id=\"xmxz\" ");
		html.append(checkXssq ? "" : "disabled");
		html.append(" >");
		if (xmxzList != null && xmxzList.size() > 0) {
			for (int i = 0; i < xmxzList.size(); i++) {
				String xmxzdm = xmxzList.get(i).get("dm");
				String xmxzmc = xmxzList.get(i).get("mc");
				html.append("<option value=\"" + xmxzdm + "\" ");
				if(xmxz.equalsIgnoreCase(xmxzdm)){
					html.append(" selected=\"selected\" ");
				}
				html.append(">");
				if (xmxzmc.length() < 6) {
					html.append(xmxzmc);
				} else {
					html.append(xmxzmc.substring(0, 6) + "...");
				}
				html.append("</option>");
			}
		}
		html.append("</select>");
		html.append("</td>");
		// 项目金额
		String xmje = Base.isNull(pjxmMap.get("xmje")) ? "" : pjxmMap
				.get("xmje");
		html.append("<th width=\"\">");
		html.append("项目金额");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" name=\"xmje\" id=\"xmje\" ");
		html.append("onkeydown=\"return onlyNum(this,5)\" ");
		html.append("onmousedown=\"return onlyNum(this,5)\" ");
		html.append(checkXssq ? "" : "disabled ");
		html.append("maxlength=\"5\"");
		html.append("style=\"width:50px;ime-mode:disabled\" value=\""+xmje+"\" ");
		html.append("/>(元)");
		html.append("</td>");
		html.append("</tr>");	
		// 显示顺序
//		html.append("<tr>");
//		html.append("<th width=\"\">");
//		html.append("显示顺序");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"text\" name=\"xssx\" id=\"xssx\" ");
//		html.append("onkeydown=\"return onlyNum(this,3)\" ");
//		html.append("onmousedown=\"return onlyNum(this,3)\" ");
//		html.append("maxlength=\"3\"");
//		html.append("style=\"width:50px;ime-mode:disabled\" ");
//		html.append("/>");
//		html.append("</td>");
		// 是否启用
//		html.append("<th>");
//		html.append("是否启用");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_yes\" value=\"yes\"");
//		html.append("checked=\"checked\"");
//		html.append("/>");
//		html.append("是");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_no\" value=\"no\"");
//		html.append("/>");
//		html.append("否");
//		html.append("</td>");
//		html.append("</tr>");
		
		// 是否需要审核
		String sfsh=pjxmMap.get("sfsh");
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("是否需要审核");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_yes\" value=\"yes\"");
		html.append("yes".equalsIgnoreCase(sfsh)? "checked=\"checked\"" : "" );
		html.append(" onclick=\"checkSfsh();\" ");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("是");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_no\" value=\"no\"");
		html.append("no".equalsIgnoreCase(sfsh)? "checked=\"checked\"" : "" );
		html.append("onclick=\"checkSfsh();\" ");
		html.append(checkXssq ? "" : "disabled");
		html.append(" />否");
		html.append("</td>");
		// 是否需要人数控制
		String rssz=pjxmMap.get("rssz");
		html.append("<th width=\"\">");
		html.append("是否需要人数控制");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_yes\" value=\"yes\"");
		html.append("yes".equalsIgnoreCase(rssz)? "checked=\"checked\"" : "" );
		html.append(" onclick=\"checkRssz();\" ");
		html.append((checkXssq || checkRssz) ? "" : "disabled");
		html.append("/>");
		html.append("是");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_no\" value=\"no\"");
		html.append("no".equalsIgnoreCase(rssz)? "checked=\"checked\"" : "" );
		html.append("onclick=\"checkRssz();\" ");
		html.append((checkXssq || checkRssz )? "" : "disabled");
		html.append(" />否");
		html.append("</td>");
		html.append("</tr>");
		// 项目说明
		String xmsm=Base.isNull(pjxmMap.get("xmsm"))? "" : pjxmMap.get("xmsm");
		html.append("<tr>");
		html.append("<th>");
		html.append("项目说明");
		html.append("<br/>");
		html.append("<font color=\"blue\">(限制录入500字)</font>");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<textarea id=\"xmsm\" rows=\"5\"");
		html.append("onblur=\"chLeng(this,500)\"");
		html.append("  style=\"word-break:break-all;width:95%\" ");
		html.append(">");
		html.append(xmsm);
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------内容 end---------------------------
		html.append("</table>");
		
	
		// ==============================审核流选择 begin=================================
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\" id=\"tab_shlc\" ");
		if("no".equalsIgnoreCase(sfsh)){
			html.append("  style=\"display:none\" ");
		}
		html.append(">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th >	");
		html.append("<span>审核流程</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		
			html.append("<tr>");
			html.append("<td width=\"\">");
			//获得审核流程列表
			String shlcid=Base.isNull(pjxmMap.get("lcid"))? "" : pjxmMap.get("lcid");
			List<HashMap<String,String>> shlcList = dao.getShlcList();
			if (shlcList != null && shlcList.size() > 0) {
				for (int i = 0; i < shlcList.size(); i++) {
					String lcid = shlcList.get(i).get("lcid");
					String lcmc = shlcList.get(i).get("lcmc");
					String gzgw = shlcList.get(i).get("gzgw");
					
					html.append("<input type=\"radio\" name=\"lcid\" id=\"lcid_"+lcid+"\" ");
					html.append("value=\""+lcid+"\" ");
					html.append(checkXssq ? "" : "disabled ");
					html.append("onclick=\"clickShlc('"+lcid+"')\" ");
					if(lcid.equalsIgnoreCase(shlcid)){
						html.append("checked=\"checked\" ");
					}
					html.append("/>");
					html.append(lcmc);
					html.append("：");
					String[] arr_gw = gzgw.split(",");
					if (arr_gw != null && arr_gw.length > 0) {
						for (int j = 0; j < arr_gw.length; j++) {
							if (j != 0) {
								html.append("-->");
							}
							html.append(arr_gw[j]);
						}
					}	
					html.append("<br/>");
				}
			}
			html.append("</select>");
			html.append("</td>");
			html.append("</tr>");
			
			html.append("</tbody>");
			html.append("</table>");
		
		// ==============================审核流选择 end=================================
		
		
			
		// ==============================级别控制选择 begin==============================
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\"  id=\"tab_shgw\" ");
		if("no".equalsIgnoreCase(sfsh)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");	
	
		html.append("<tbody>");
		
		// 审核流选择
		html.append("<tr>");
		html.append("<td width=\"\">");
		html.append("<div id=\"div_shgw\">");
		
		model.setLcid(pjxmMap.get("lcid"));
		try {
			
			pjxmMap.put("checkRssz", String.valueOf(checkRssz));
			pjxmMap.put("checkXssq", String.valueOf(checkXssq));
			
			html.append(defaultShlcGwxx(model,pjxmMap, user));
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
		
		// ==============================级别控制选择 end==============================
			
		String cpz = jbszModel.getCpz();
		
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\" id=\"tab_rssz\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");	
		// ==============================人数控制范围 begin==============================	
		
		// -------------------------表头---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"5\">	");
		html.append("<span>人数控制范围</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		html.append("<tbody>");
		
		// 人数控制级别选择
		String kzfw=Base.isNull(pjxmMap.get("kzfw")) ?  "" : pjxmMap.get("kzfw");
		html.append("<tr>");
		// ----------------需要参评组控制 begin -----------------
		if("yes".equalsIgnoreCase(cpz)){
			html.append("<td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("nj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("年级人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("xy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append(Base.YXPZXY_KEY);
			html.append("人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("njxy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("年级+").append(Base.YXPZXY_KEY).append("人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("njzy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("年级+专业人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\" ");
			html.append("bj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("班级人数");
			html.append("</td></tr>");
			html.append("<tr><td colspan=\"5\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_cpz\" value=\"cpz\"");
			html.append("cpz".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("参评组人数");
			html.append("<br/>");

			html.append("</td>");
		}else{
			
			html.append("<td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("nj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("年级人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("xy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			html.append(Base.YXPZXY_KEY);
			html.append("人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("njxy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("年级+").append(Base.YXPZXY_KEY).append("人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("njzy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("年级+专业人数");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\" ");
			html.append("bj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("班级人数");
			html.append("</td>");
		}
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
		// ==============================人数控制范围 end==============================	
		
		
		// -------------------------表头---------------------------
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"5\">	");
		html.append("<span>特殊人群选择</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------表头 end---------------------------
		
		//特殊人群列表
		List<HashMap<String,String>> tsrqList = jbszModel.getTsrqList();
		
		html.append("<tbody>");
		
		// 人数控制级别选择
		html.append("<tr style=\"height:22px\">");
		
		String tsrqxx=Base.isNull(pjxmMap.get("tsrq")) ?  "" : pjxmMap.get("tsrq");
		for(int i=0;i<tsrqList.size();i++){
			
			html.append("<td width=\"20%\">");
			HashMap<String,String> tsrq = tsrqList.get(i);
			if(!Base.isNull(tsrq.get("tsrqdm"))){
				html.append("<input type=\"radio\" name=\"tsrq\" id=\"tsrq_"+tsrq.get("tsrqdm")+"\" ");
				html.append("value=\""+tsrq.get("tsrqdm")+"\" ");
				html.append(tsrq.get("tsrqdm").equalsIgnoreCase(tsrqxx)? "checked=\"checked\"" : "" );
				html.append(checkXssq ? "" : " disabled ");
				html.append("onclick=\"$('hidden_tsrq').value=this.value\"");
			html.append("/>");
			}
			html.append(tsrq.get("tsrqmc"));
			html.append("</td>");
			if((i+1)%5==0){
				html.append("</tr><tr style=\"height:22px\">");
			}
		}
		
		if (tsrqList.size() % 5 != 0) {
			for(int i=0;i<5-tsrqList.size() % 5;i++){
				html.append("<td>&nbsp;");
				html.append("</td>");
			}
			html.append("</tr>");
		}
		
		html.append("</tbody>");
		html.append("</table>");

		
		return html.toString();
	}
	
	/**
	 * 判断是否做过人数设置
	 * @author qlj
	 * flase: 已有申请 true: 无申请
	 * @throws Exception
	 */
	public boolean checkRssz(PjszPjxmModel model, User user) throws Exception {
		
		String xmdm=model.getXmdm();
		
		List<HashMap<String,String>>rsszList=new ArrayList<HashMap<String,String>>();
		
		rsszList=dao.getRsszList(xmdm, user);
		
		if(rsszList!=null && rsszList.size()>0){
			
			return false;
		
		}
		
		return true;
	}
	
	/**
	 * 判断是否做过人数设置
	 * @author qlj
	 * flase: 已有申请 true: 无申请
	 * @throws Exception
	 */
	public String showRsszMessage(PjszPjxmModel model, User user) throws Exception {
		
		String[]pkValue=model.getPkValue();
		
		List<HashMap<String,String>>rsszList=new ArrayList<HashMap<String,String>>();
		
		rsszList=dao.getRsszList(pkValue, user);
		
		String message="";
		
		if(rsszList!=null && rsszList.size()>0){
			
			HashMap<String,String>yszrsMap=rsszList.get(0);
			
			message=yszrsMap.get("xmmc")+"项目已设置人数不可删除！";
		}
		
		return message;
	}
	
	/**
	 * 判断是否做过人数设置
	 * @author qlj
	 * flase: 已有申请 true: 无申请
	 * @throws Exception
	 */
	public String showXssqMessage(PjszPjxmModel model, User user) throws Exception {
		
		WdpjXssqDAO xssqDAO=new WdpjXssqDAO();
		
		String[]pkValue=model.getPkValue();
		
		List<HashMap<String,String>>xssqList=new ArrayList<HashMap<String,String>>();
		
		xssqList=xssqDAO.getXssqList(pkValue, user);
		
		StringBuilder message=new StringBuilder();
		
		if(xssqList!=null && xssqList.size()>0){
			
			HashMap<String,String>ysqMap=xssqList.get(0);
			message.append("【<font color='blue'>");
			message.append(ysqMap.get("xmmc"));
			message.append("</font>】");
			message.append("已有学生申请，不可删除！");
		}
		
		return message.toString();
	}
	
	public String checkDelete(PjszPjxmModel model, User user) throws Exception {

		String message = showXssqMessage(model, user);

		return message;
	}
	
	/**
	 * 判断是否有学生申请过此项目
	 * flase: 已有申请 true: 无申请
	 * @author qlj
	 * @throws Exception
	 */
	public boolean checkXssq(PjszPjxmModel model, User user) throws Exception {
		
		WdpjXssqDAO xssqDAO=new WdpjXssqDAO();
		
		String xmdm=model.getXmdm();
		
		// 根据项目代码或学生申请列表
		List<HashMap<String,String>>xssqList=xssqDAO.getXssqList(xmdm, user);
		
		xssqList=xssqDAO.getXssqList(xmdm, user);
		
		if(xssqList!=null && xssqList.size()>0){
			
			return false;	
		}
		
		return true;
	}
	
	
	/**
	 * 根据项目代码查找审批岗位
	 */
	public String[] getSpgwByXmdm(String xmdm){
		
		try {
			return dao.getSpgwByXmdm(xmdm);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public HashMap<String, String> getPjxmInfo(String xmdm){
		return dao.getPjxmInfo(xmdm);
	}
}