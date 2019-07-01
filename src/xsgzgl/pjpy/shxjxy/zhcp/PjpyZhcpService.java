package xsgzgl.pjpy.shxjxy.zhcp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_通用_Service类
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

public class PjpyZhcpService  extends CommService implements PjpyZhcpInterface{

	PjpyZhcpDAO dao = new PjpyZhcpDAO();
	/**
	 * 初始化综测项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCshXmList(User user) {
		
		List<HashMap<String, String>>cshXmList=dao.getCshXmList(user);
		
		return cshXmList;
		
	}

	/**
	 * 获得表头信息(综合测评_综测信息)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZhcpZcxxTop(PjpyZhcpModel model,
			User user) {

		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 综测周期
		String zczq = jbszModel.getZczq();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目名称
		String xmmc = model.getXmmc();
		// 来源表
		String lyb =model.getLyb();
		// 项目级别
		String xmjb=model.getXmjb();

		// 扩展字段列表
		List<HashMap<String, String>> kzzdList = null;
		
		//综测子项
		List<HashMap<String, String>> zczxList = null;

		// 综测扩展信息
		kzzdList = dao.getKzzdList(xmdm, user);
		
		// 获取综测子项信息
		zczxList = dao.getZczxList(xmdm, user);

		String[] en = new String[] { "xh", "xm", "nj", "bjmc" };
		String[] cn = new String[] { "学号", "姓名", "年级", "班级名称" };

		ArrayList<String> enList = new ArrayList<String>(Arrays.asList(en));
		ArrayList<String> cnList = new ArrayList<String>(Arrays.asList(cn));
		
		// ----------------综测字项 begin-----------------
		if (zczxList != null && zczxList.size() > 0) {

			for (int i = 0; i < zczxList.size(); i++) {
				
				
					HashMap<String, String> map = zczxList.get(i);
					// 子项目代码
					String zxmdm = map.get("xmdm");
					// 子项目名
					String zxmmc = map.get("xmmc");
	
					enList.add(zxmdm);
					cnList.add(zxmmc);
				
			}
		}
		// ----------------综测字项 end-----------------
		
		if (kzzdList != null && kzzdList.size() > 0) {

			for (int i = 0; i < kzzdList.size(); i++) {
				
				
					HashMap<String, String> map = kzzdList.get(i);
					// 扩展字段
					String kzzd = map.get("kzzd");
					// 显示名称
					String xsmc = map.get("xsmc");
	
					enList.add(kzzd);
					cnList.add(xsmc);
				
			}
		}

		en = enList.toArray(new String[] {});
		cn = cnList.toArray(new String[] {});

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 查询结果集(综合测评_综测信息)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getZhcpZcxxList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 综测周期
		String zczq = jbszModel.getZczq();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目名称
		String xmmc = model.getXmmc();

		// 扩展字段列表
		List<HashMap<String, String>> kzzdList = null;
		
		// 综测子项
		List<HashMap<String, String>> zczxList = null;

		// 综测扩展信息
		kzzdList = dao.getKzzdList(xmdm, user);
		// 获取综测子项信息
		zczxList = dao.getZczxList(xmdm, user);

		ArrayList<String[]> list = dao.getZhcpZcxxList(myForm, model, user,
				kzzdList,zczxList);
		// 扩展信息
		// List<HashMap<String, String>> kzxxList =new
		// ArrayList<HashMap<String,String>>();

		return list;
	}

	/**
	 * 构建结果集(综合测评_综测信息)
	 * 
	 * @author 伟大的骆
	 */
	public String createZhcpZcxxHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user) {
		
		BasicService service =new BasicService();
		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// IE版本
		String ie = rsModel.getIe();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 综测周期
		String zczq = jbszModel.getZczq();
		// 项目代码
		String xmdm = model.getXmdm();
		// 来源表
		String lyb = model.getLyb();
		// 项目级别
		String xmjb = model.getXmjb();
		
		StringBuilder html = new StringBuilder();

		List<HashMap<String, String>> kzzdList = null;
		
		List<HashMap<String, String>> zczxList = null;
		// 扩展字段数
		int KzzdLen = 0;
		
		// 综测扩展信息
		kzzdList = dao.getKzzdList(xmdm, user);
		// 获取综测子项信息
		zczxList = dao.getZczxList(xmdm, user);

		if (kzzdList != null && kzzdList.size() > 0) {
			KzzdLen = kzzdList.size();
		}

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String xh = rs[0];// 学号
				String fs = rs[4];// 分数

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"display:none\">");
				html.append("<input type='hidden' name='div_pkValue'  style='width:80px' ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" + service.replaceHtml(rs[0]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 0; j < 4; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(service.replaceHtml(rs[j]));
					html.append("</td>");
				}
				
				for(int j=0;j<zczxList.size();j++){
					
					HashMap<String,String>zczxMap=zczxList.get(j);
					
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\">");
					
					String result = rs[5 + j];
					String result_fs = result.split("luojw")[0];
					String result_lyb = result.split("luojw")[1];
					
					if(!Base.isNull(zczxMap.get("lyb"))){
						html.append(service.replaceHtml(result_fs));
					}else{
						html.append("<input type='text'   style='width:50px' ");
						html.append("  id='"+zczxMap.get("xmdm")+"_" + i + "' ");
						html.append("  name='"+zczxMap.get("xmdm")+"' ");
						html.append(" onkeyup=\"checkInputNum(this);setHadEdit();\" ");
						html.append(" maxlength=\"5\" ");
						html.append(" onblur=\"checkInputNum(this)\" ");
						html.append(" value='" + service.replaceHtml(result_fs) + "'/> ");
					}
					html.append("</td>");
					
				}
			
				// ---------------扩展字段begin-----------------------
				for (int j = 0; j < KzzdLen; j++) {
					HashMap<String, String> kzzdMap = kzzdList.get(j);
					String kzzd = kzzdMap.get("kzzd");
					String checked = kzzdMap.get("checked");
					// 长度限制
					String maxlength = "";
					// 事件
					String event = "";
					// 数据表
					String source_table = "";
					// 关联表代码
					String select_dm = kzzdMap.get("select_dm");
					// 关联表名称
					String select_mc = kzzdMap.get("select_mc");

					if (!Base.isNull(checked)) {
						// 文本最大长度
						maxlength = checked.split("!!luojw!!")[0];
						// 是否有事件加载
						event = checked.split("!!luojw!!")[1];
					}

					// --------将关联表代码、名称构建为数组 begin--------
					String[] dm = null;
					String[] mc = null;
					if (!Base.isNull(select_dm)) {
						dm = select_dm.split("!!luojw!!");
						mc = select_mc.split("!!luojw!!");
					}
					// --------将关联表代码、名称构建为数组 end--------

					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					if ("text".equalsIgnoreCase(kzzdMap.get("zdlx"))) {// text文本框加载
						html.append("<input type='text' name='"+kzzd+"' ");
						html.append("  id='"+kzzd +"_"+i
								+ "' ");
						html.append(" maxlength='" + maxlength + "' ");
						html.append(" value='" + service.replaceHtml(rs[zczxList.size()+j + 5])
								+ "' style='width:80px'/> ");
					} else if ("select".equalsIgnoreCase(kzzdMap.get("zdlx"))) {// select下拉框加载
						
						html.append("<select  name='"+kzzd+"'  ");
						html.append("  id='" + kzzd + "_" + i
								+ "' > ");
						html.append("<option value=''></option>");
						for (int z = 0; z < dm.length; z++) {
							html.append("<option value='"+dm[z]+"' ");
							if(rs[j + 5].equalsIgnoreCase(dm[z])){
								
								html.append(" selected='true' ");
									
							}
							html.append(" >" + mc[z] + "</option>");
						
						}
						html.append(" </select> ");
					} else if ("textArea".equalsIgnoreCase(kzzdMap.get("zdlx"))) {// text文本框加载
						html.append("");
						html.append("<input type='hidden' name='" + kzzd
										+ "' ");
						html.append("  id='" + kzzd + "_" + i
								+ "' ");
						html.append(" maxlength='" + maxlength + "'  ");
						html.append(" value='" + service.replaceHtml(rs[zczxList.size()+j + 5]) + "'/> ");
						html.append(" <a href='#' title='点击可查看并修改信息' onclick=\"showEditDiv('"
										+ kzzd
										+ "','"
										+ kzzdMap.get("kzzd")
										+ "_" + i + "');return false;\"><font color=\"blue\">详细</font></a>");
					}

					html.append("</td>");
					
				}
				
				html.append("</tr>");
			}
		}
		
		return html.toString();

	}

	/**
	 * 显示字段设置Div
	 * 
	 * @author qlj
	 * 
	 * @throws IOException
	 */
	public void showZdxgDiv(String zd, String zdid, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 字段列表
		List<HashMap<String, String>> zdList = dao.getZdszList(zd);

		HashMap<String, String> zdInfo = new HashMap<String, String>();

		if (zdList != null && zdList.size() > 0) {
			zdInfo = zdList.get(0);
		}

		// 字段名
		String xsmc = zdInfo.get("xsmc");
		// 字段类型
		String zdlx = zdInfo.get("zdlx");
		// 字段验证
		String checked = zdInfo.get("checked");
		// 数据表
		String source_table = zdInfo.get("source_table");
		// 表代码
		String select_dm = zdInfo.get("select_dm");
		// 表名称
		String select_mc = zdInfo.get("select_mc");
		
		String length=checked.split("!!luojw!!")[0];

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>信息修改</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append(xsmc);
		html.append("</th>");
		html.append("<td>");

		if ("textArea".equalsIgnoreCase(zdlx)) {// 文本框

			html.append(" <textArea name=\"" + zd + "_name\" id=\"" + zd
					+ "_id\" ");
			html.append(" rows='4' style='word-break:break-all;width:96%' onblur=\"chLeng(this,"+length+")\" ;>  ");
			html.append(" </textArea>");
		}

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"affirmValue('" + zd
				+ "','" + zdid + "');return false;\">");
		html.append("确 认");
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
	 * 获取扩展字段信息
	 */
	public List<HashMap<String, String>> getKzzdList(PjpyGeneralForm model,
			User user) {
		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 综测周期
		String zczq = jbszModel.getZczq();
		// 项目代码
		String xmdm = model.getXmdm();

		// 扩展字段列表
		List<HashMap<String, String>> kzzdList = null;
		// 综测扩展信息
		kzzdList = dao.getKzzdList(xmdm, user);

		return kzzdList;
	}
	
	/**
	 * 获取扩展字段信息
	 */
	public List<HashMap<String, String>> getZczxList(PjpyGeneralForm model,
			User user) {
		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 综测周期
		String zczq = jbszModel.getZczq();
		// 项目代码
		String xmdm = model.getXmdm();

		// 扩展字段列表
		List<HashMap<String, String>> zczxList = null;
		// 获取综测子项信息
		zczxList = dao.getZczxList(xmdm, user);

		return zczxList;
	}
	
	/**
	 * 获取扩展字段信息
	 */
	public List<HashMap<String, String>> getDdwhList(PjpyGeneralForm model,
			User user) {
		
		// 项目代码
		String xmdm = model.getXmdm();

		// 扩展字段列表
		List<HashMap<String, String>> ddwhxmList = null;
		// 单独维护项目列表
		ddwhxmList = dao.getDdwhList(xmdm, user);
		
		return ddwhxmList;
	}

	/**
	 * 获得字段设置列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZdszList(String zd, User user) {
		return dao.getZdszList(zd);
	}

	public String createZhcpMaintainRs(SearchRsModel rsModel,
			PjpyGeneralForm model, ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	public ArrayList<String[]> getZhcpMaintainInfo(PjpyGeneralForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// TODO 自动生成方法存根
		return null;
	}

	public List<HashMap<String, String>> getZhcpMaintainTop(
			PjpyGeneralForm model, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 保存综合测评信息
	 */
	public boolean saveZhcpInfo(PjpyGeneralForm model,HttpServletRequest request, User user) {
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		//
		String pjzq = jbszModel.getPjzq();

		// 当前页的学号数组(先删后增将该页信息删除时)
		String xh =request.getParameter("pkValue");
		String fs=request.getParameter("fs");

		//项目代码
		String xmdm=request.getParameter("xmdm");
		model.setXmdm(xmdm);
		model.setPjxn(pjxn);
		model.setPjxq(pjxq);
		
		
		// ------------------扩展字段 begin--------------------------
		List<HashMap<String,String>>kzzdList=getKzzdList(model, user);
		
		String[]kzzdInfo=new String[kzzdList.size()];
		for(int i=0;i<kzzdList.size();i++){
			
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			String kzzd=kzzdMap.get("kzzd");
			String kzzdValue=Base.isNull(request.getParameter(kzzd))?"":request.getParameter(kzzd);
			kzzdInfo[i]=unicode2Gbk(kzzdValue);
			
		}   
		// ------------------扩展字段 end--------------------------

		// ------------------扩展字段 begin--------------------------
		List<HashMap<String,String>>zczxList=dao.getBczdList(xmdm, user);
		
		String[]zczxInfo=new String[zczxList.size()];
		for(int i=0;i<zczxList.size();i++){
			
			HashMap<String,String>zczxMap=zczxList.get(i);
			String zxmdm=zczxMap.get("xmdm");
			String zxmdmValue=request.getParameter(zxmdm);
			zczxInfo[i]=unicode2Gbk(zxmdmValue);
		}   
		// ------------------扩展字段 end--------------------------
		
		boolean flag=false;
		if("xq".equalsIgnoreCase(pjzq)){
			
			flag=dao.saveZhcpByXq(kzzdInfo,zczxInfo,xh.split("!!@@!!"), model,user);
		}else if("xn".equalsIgnoreCase(pjzq)) {
			
			flag=dao.saveZhcpByXn(kzzdInfo, zczxInfo,xh.split("!!@@!!"), model,user);
		}
		
		return flag;
	}
	
	public String createKidneyDiv(SearchRsModel rsModel, PjpyZhcpModel model,
			ArrayList<String[]> rsArrList, User user) {

		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// IE版本
		String ie = rsModel.getIe();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 综测周期
		String zczq = jbszModel.getZczq();
		// 项目代码
		String xmdm = model.getXmdm();
		StringBuilder html = new StringBuilder();
		
		jbszModel.setXmdm(xmdm);
		HashMap<String,String>butMap=getButMap(jbszModel, user);

		List<HashMap<String, String>> kzzdList = null;
		// 综测扩展信息
		kzzdList = dao.getKzzdList(xmdm, user);
		
		List<HashMap<String, String>> zczxList = null;
		// 获取综测子项信息
		zczxList = dao.getZczxList(xmdm, user);
		// 综测保存字段
		List<HashMap<String,String>>bczdList=dao.getBczdList(xmdm, user);
		
		
		html.append(" <div name='div_kidneyDiv'>");
		
		// -----------------扩展字段 begin-----------------------
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			html.append(" <input type='hidden' name='kzzdArr' value="+kzzdMap.get("kzzd")+" />");
		}
		// -----------------扩展字段 end-----------------------
		
		// -----------------综测子项 begin-----------------------
		for(int i=0;i<zczxList.size();i++){
			HashMap<String,String>zczxMap=zczxList.get(i);
			html.append(" <input type='hidden' name='zczxArr' value="+zczxMap.get("xmdm")+" />");
		}
		
		// 保存字段信息
		for(int i=0;i<bczdList.size();i++){
			HashMap<String,String>bczdMap=bczdList.get(i);
			html.append(" <input type='hidden' name='bczdArr' value="+bczdMap.get("xmdm")+" />");
		}
		// -----------------综测子项 end-----------------------
		
		html.append("<input type=\"hidden\" name =\"hid_lrf\" id=\"hid_lrf\" value=\""+butMap.get("lrf")+"\"/> ");
		html.append("<input type=\"hidden\" name =\"hid_lyf\" id=\"hid_lyf\" value=\""+butMap.get("lyf")+"\"/> ");
		
		html.append(" </div> ");
		
		return html.toString();
	}
	
	/**
	 * 综合分计算
	 * 综测分、综测排名、智育排名计算
	 * @throws Exception 
	 */
	public boolean account(PjpyGeneralForm myForm, User user) throws Exception {
		
		boolean flag=false;
		
		//综测分数计算(综测分、以及系项分计算)
		flag = zcxmjs(myForm, user);
		
		if (flag) {
			//综测总分排名
			flag =getPlace(myForm, user);
		}
				
		if(flag){
			//智育分排名计算
			flag = getZyPlace(myForm, user);
		}
		
		if(flag){
			//德育分排名计算
			flag = getDyPlace(myForm, user);
		}
		
		return flag;
	}
	
	
	/**
	 * 综测项目分计算
	 * 综测总分、综测系项分计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zcxmjs(PjpyGeneralForm model,User user) throws Exception {
		
		boolean blog=true;

		// =============有子项目的项目分计算 begin===================
		List<HashMap<String,String>>zcjsList=dao.getZcjsSql(model, user);
		// =============有子项目的项目分计算 end===================
		
		// -----------执行 begin---------------
		blog=zcxmfjs(zcjsList);
		// -----------执行 end---------------
		
		return blog;
	}
	
	/**
	 * 计算综测排名
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getPlace(PjpyGeneralForm model,User user) throws Exception{
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 根据所选择的排名计算方式计算排名
		String zcpm = jbszModel.getZcpm();
		
		boolean blog=false;
		
		// 参评组排名
		if("0".equalsIgnoreCase(zcpm)){
			blog=cpzpmjs(model,user);
		}else{
		
			//年级学院排名
			blog=xypmjs(model,user);
		
			//年级专业排名
			blog=zypmjs(model,user);
			
			//班级排名
			blog=bjpmjs(model,user);
		}
		
		return blog;
	}
	
	/**
	 * 计算智育排名
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getZyPlace(PjpyGeneralForm model,User user) throws Exception{

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 根据所选择的排名计算方式计算排名
		String zypm = jbszModel.getZypm();
		
		boolean blog=false;
		
		
		if("0".equalsIgnoreCase(zypm)){
			blog=cpzZypmjs(model,user);
		}else {
		
			// 年级学院排名
			blog=xyZypmjs(model,user);
		
			//年级专业排名
			blog=zyZypmjs(model,user);
		
			//班级排名
			blog=bjZypmjs(model,user);
		}
		
		return blog;
		
	}
	
	/**
	 * 计算德育排名
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getDyPlace(PjpyGeneralForm model,User user) throws Exception{

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 根据所选择的排名计算方式计算排名
		String zypm = jbszModel.getZypm();
		
		boolean blog=false;
		
		
		if("0".equalsIgnoreCase(zypm)){
			blog=cpzZypmjs(model,user);
		}else {
		
//			// 年级学院排名
//			blog=xyZypmjs(model,user);
//		
//			//年级专业排名
//			blog=zyZypmjs(model,user);
		
			//班级排名
			blog=bjDypmjs(model,user);
		}
		
		return blog;
		
	}
	
	/**
	 * 班级排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjpmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.bjpmjs(model, user);
		if (flag) {
			flag = dao.updateBjpm(model, user);
		}
		return flag;
	}
	
	/**
	 * 年级学院排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njxypmjs(model, user);
		if (flag) {
			flag = dao.updateXypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 参评组排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean cpzpmjs(PjpyGeneralForm model, User user) throws Exception {

		// 参评组排名计算
		boolean flag = dao.cpzpmjs(model, user);
		if (flag) {
			flag = dao.updateCpzpm(model, user);
		}
		return flag;
	}
	
	/**
	 * 年级专业排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njzypmjs(model, user);
		if (flag) {
			flag = dao.updateZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 年级学院排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njxyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjXyZypm(model, user);
		}
		return flag;
	}
	

	/**
	 * 班级排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.bjZypmjs(model, user);
		if (flag) {
			flag = dao.updateBjZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 班级排名计算【德育分】
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjDypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.bjDypmjs(model, user);
		if (flag) {
			flag = dao.updateBjDypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 年级专业排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njzyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjZyZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 参评组排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean cpzZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 参评组排名计算
		boolean flag = dao.cpzZypmjs(model, user);
		if (flag) {
			flag = dao.updateCpzZypm(model, user);
		}
		return flag;
	}

	public String createZhcpResultHTML(SearchRsModel rsModel, PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user) {
		
		BasicService basicService=new BasicService();
		
		// IE版本
		String ie = rsModel.getIe();
		
		StringBuilder html = new StringBuilder();
		
			if (rsArrList != null && rsArrList.size() > 0) {
	
				for (int i = 0; i < rsArrList.size(); i++) {
	
					String[] rs = rsArrList.get(i);
	
					html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
					
//					html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");
//					
//					html.append("<input type='hidden' name='pkValue'   ");
//					html.append("  id='pkValue_"+i+"' ");
//					html.append(" value='" + rs[0] + "'/> ");	
//					
//					html.append("</td>");
					
					
					// --------------------构建HTML扩展字段与分数除外------------------------
					for (int j = 0; j < rs.length; j++) {
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
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

	public ArrayList<String[]> getZhcpResultList(PjpyGeneralForm myForm, PjpyZhcpModel model, User user) throws Exception{
		// TODO 自动生成方法存根
		String[]colList=severTop(getZhcpResultTop(myForm,user),"en");
		
		return (ArrayList<String[]>)dao.getZhcpResultList(myForm, user, colList);
	}
	
	/**
	 * 分离top列表 根据获取类型获取（en,cn）
	 * 
	 * @param topList
	 * @param hqlx
	 * @return List<HashMap<String, String>>
	 */
	public String[] severTop(List<HashMap<String, String>> topList, String hqlx) {

		List<String> outList = new ArrayList<String>();
		for (int i = 0; i < topList.size(); i++) {
			HashMap<String, String> topMap = topList.get(i);
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}

	public List<HashMap<String, String>> getZhcpResultTop( User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String isCpz=jbszModel.getCpz();
		
		String zcpm=jbszModel.getZcpm();
		String zypm=jbszModel.getZypm();
		DAO commDao=DAO.getInstance();
		//获取配置表中配置的字段、字段名称
		List<HashMap<String,String>>colList=dao.getZhcpResultTop(user);
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		//获取扩展字段信息
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		
		colArr.add("xh");
		topArr.add("学号");
		
		colArr.add("xm");
		topArr.add("姓名");
		
		colArr.add("nj");
		topArr.add("年级");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("专业");
		
		colArr.add("bjmc");
		topArr.add("班级");
		
		// 参评组
		if("yes".equalsIgnoreCase(isCpz)){
			
			colArr.add("cpzmc");
			topArr.add("参评组");
		}
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		// 参评组排名
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("cpzpm");
			topArr.add("综合分参评组排名");
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("综合分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("综合分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("综合分班级排名");
		}
		
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("zyfcpzpm");
			topArr.add("智育分参评组排名");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("智育分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("智育分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("智育分班级排名");
		}
		
		//德育分
		if("0".equalsIgnoreCase(zypm)){
//			colArr.add("zyfcpzpm");
//			topArr.add("智育分参评组排名");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjxypm");
			topArr.add("德育分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjzypm");
			topArr.add("德育分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfbjpm");
			topArr.add("德育分班级排名");
		}
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	public List<HashMap<String, String>> getZcResultTop(PjpyGeneralForm myForm, User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String isCpz=jbszModel.getCpz();
		
		SearchModel searchModel=myForm.getSearchModel();
		
		String[]search_tj_zczq=searchModel.getSearch_tj_zczq();
		
		String tjxn = search_tj_zczq[0];
		String tjxq = search_tj_zczq[1];
		String tjnd = search_tj_zczq[2];
		
		String pjxn = jbszModel.getPjxn();	
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();
		
		String zcpm=jbszModel.getZcpm();
		String zypm=jbszModel.getZypm();
		DAO commDao=DAO.getInstance();
		//获取配置表中配置的字段、字段名称
		List<HashMap<String,String>>colList=new ArrayList<HashMap<String,String>>();
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		//获取扩展字段信息
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		
		colArr.add("xh");
		topArr.add("学号");
		
		colArr.add("xm");
		topArr.add("姓名");
		
		colArr.add("nj");
		topArr.add("年级");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("专业");
		
		colArr.add("bjmc");
		topArr.add("班级");
		
		//  参评组
		if("yes".equalsIgnoreCase(isCpz)){
			
			colArr.add("cpzmc");
			topArr.add("参评组");
		}
		
		if(tjxn.equalsIgnoreCase(pjxn) 
				&& tjxq.equalsIgnoreCase(pjxq) 
				&& tjnd.equalsIgnoreCase(pjnd) ){
			
			colList=dao.getZhcpResultTop(user);
			
			for(int i=0;i<colList.size();i++){
				HashMap<String,String>outPutMap=colList.get(i);
				colArr.add(outPutMap.get("xmdm"));
				topArr.add(outPutMap.get("xmmc"));
			}
			
			for(int i=0;i<kzzdList.size();i++){
				HashMap<String,String>kzzdMap=kzzdList.get(i);
				colArr.add(kzzdMap.get("kzzd"));
				topArr.add(kzzdMap.get("xsmc"));
			}
		}else{
			colList=dao.getZcxmByHistory(user);
		}
		
		// 参评组排名
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("cpzpm");
			topArr.add("参评组排名");
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("班级排名");
		}
		
		// 参评组排名
		if("0".equalsIgnoreCase(zypm)){
			colArr.add("zyfcpzpm");
			topArr.add("智育分参评组排名");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("智育分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("智育分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("智育分班级排名");
		}
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * 获得综测项目扩展字段信息（本评奖周期）
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getKzzdList(User user) {

		return dao.getKzzdList(user);
	}
	
	/**
	 * 获取表头显示字段
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getZhcpResultTop(PjpyGeneralForm model, User user) {
		//获取指定用户已设置显示列
		List<HashMap<String, String>> kindList = getCheckKind(model, user);
		List<HashMap<String, String>> top=getZhcpResultTop(user);
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		HashMap<String, String>map=new  HashMap<String, String>();
		
		
		//取交集
		if (kindList != null && kindList.size() > 0) {
			for (int i = 0; i < kindList.size(); i++) {
				HashMap<String, String> kindMap = kindList.get(i);
				HashMap<String, String> topTrMap = new HashMap<String, String>();
				for (int j = 0; j < top.size(); j++) {
					HashMap<String, String> topMap = top.get(j);

					if (kindMap.get("zd").equalsIgnoreCase(topMap.get("en"))) {
						topTrMap.put("en", topMap.get("en"));
						topTrMap.put("cn", topMap.get("cn"));
						topTr.add(topTrMap);
						break;
					}
				}
				
			}
			
		}else{
			
			return top;
		}
		
		return topTr;
	}
	
	/**
	 * 获取指定用户已设置显示列
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getCheckKind(PjpyGeneralForm model, User user) {
		// 获取指定用户选中的列
		HashMap<String, String> kind = dao.getCheckKind(model, user);
		List<HashMap<String, String>> checkKind = new ArrayList<HashMap<String, String>>();
		// 需显示字段
		String xszd = kind.get("xxszd");
		String[] xszdArr = null;
		if (!Base.isNull(xszd)) {
			xszdArr = xszd.split(",");
			for (int i = 0; i < xszdArr.length; i++) {
				HashMap<String, String> kindMap = new HashMap<String, String>();
				kindMap.put("zd", xszdArr[i]);
				checkKind.add(kindMap);
			}
		}
		return checkKind;
	}

	
	/**
	 * 获取综测分模块列选字段
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getKindChoose(PjpyGeneralForm model, User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String isCpz=jbszModel.getCpz();
		
		String zcpm=jbszModel.getZcpm();
		String zypm=jbszModel.getZypm();
		DAO commDao=DAO.getInstance();
		//获取配置表中配置的字段、字段名称
		List<HashMap<String,String>>colList=dao.getZczfTop(model, user);
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		//获取扩展字段信息
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		// ------------------------默认显示字段 begin------------------------------
		colArr.add("xh");
		topArr.add("学号");
		
		colArr.add("xm");
		topArr.add("姓名");
		
		colArr.add("nj");
		topArr.add("年级");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("专业");
		
		colArr.add("bjmc");
		topArr.add("班级");	

		//  参评组
		if("yes".equalsIgnoreCase(isCpz)){
			
			colArr.add("cpzmc");
			topArr.add("参评组");
		}
		
		// ------------------------默认显示字段 end------------------------------
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		// ---------------------综测排名信息 根据flowControl.xml文件配置取 begin---------------------
		if("0".equalsIgnoreCase(zcpm)){
			colArr.add("cpzpm");
			topArr.add("综合分参评组排名");
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("综合分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("综合分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("综合分班级排名");
		}
		
		if("0".equalsIgnoreCase(zypm)){
			colArr.add("zyfcpzpm");
			topArr.add("智育分参评组排名");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("智育分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("智育分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("智育分班级排名");
		}
		
		//德育分
		if("0".equalsIgnoreCase(zypm)){
//			colArr.add("zyfcpzpm");
//			topArr.add("智育分参评组排名");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjxypm");
			topArr.add("德育分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfnjzypm");
			topArr.add("德育分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("dyfbjpm");
			topArr.add("德育分班级排名");
		}
		// ---------------------综测排名信息 根据flowControl.xml文件配置取 end---------------------
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	
	/**
	 * 保存列选
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveKindChoose(PjpyZhcpModel model,User user) throws Exception{
	
		DAO commDao=DAO.getInstance();
		String yhm=user.getUserName();
		String path="pjpy_general_zhcp_result.do";
		String yhlx="tea";
		StringBuilder xszd=new StringBuilder();
		
		String []bxszd={"xh","xm"};
		String []xszdArr=null;
		if("stu".equalsIgnoreCase(user.getUserType())){
			yhlx="stu";
		}
		
		xszdArr=commDao.unionArray(bxszd, model.getXszdArr());
		for(int i=0;i<xszdArr.length;i++){
			if(i!=0){
				xszd.append(",");
			}
			xszd.append(xszdArr[i]);
		}
		
		return checkBoolean(dao.saveKindChoose(yhm, yhlx, xszd.toString(), path));
	}
	
	/**
	 * 判断批量操作是否成功
	 * @param returnV
	 * @return
	 */
	public boolean checkBoolean(int []returnV){
		boolean blog=true;
		for(int i=0;i<returnV.length;i++){
			if(returnV[i]==0){
				blog=false;
			}
		}
		return blog;	
	}
	
	/**
	 * 有子项目的项目分计算
	 * @param zcjsList
	 * @return boolean
	 */
	public boolean zcxmfjs(List<HashMap<String,String>>zcjsList){
		
		List<HashMap<String, String>> xmjbList = dao.getXmjbList();
		
		boolean flag = false;
		
		for (int i = 0; i < xmjbList.size(); i++) {

			HashMap<String, String> xmjbMap = xmjbList.get(i);
			List<String> sql = new ArrayList<String>();
			for (int j = 0; j < zcjsList.size(); j++) {

				
				HashMap<String, String> zcjsMap = zcjsList.get(j);

				if (xmjbMap.get("xmjb").equalsIgnoreCase(zcjsMap.get("xmjb"))
						&& Base.isNull(zcjsMap.get("ytj"))) {
					zcjsMap.put("ytj", "yes");
					sql.add(zcjsMap.get("sql"));
				}
			}

			try {
				System.out.println(sql);
				flag = dao.saveArrDate(sql.toArray(new String[] {}));

			} catch (Exception e) {

				return false;

			}

		}

		return flag;	
	}
	
	/**
	 * 获取来源数据基本设置
	 * @return 
	 */
	public List<HashMap<String,String>>getLybInfo(){
		
		return dao.getLybInfo();
		 
	}
	
	/**
	 * 获取来源数据基本设置
	 * @return 
	 * @throws Exception 
	 */
	public boolean updateLybInfo(PjpyGeneralForm model,User user) throws Exception{
		
		return dao.updateLybInfo(model, user);
	}
	
	/**
	 * 区分项目功能按钮
	 * @author qlj
	 */
	public HashMap<String, String> getButMap(PjpyGeneralForm model,
			User user) {

		return dao.getButMap(model, user);
	}
	
	public List<HashMap<String,String>>getZcInfo(String xh,String xn,String xq,String nd){
		
		// 综测排名信息(en、cn)
		List<HashMap<String,String>>zcpmList=dao.getZcxxpmList();
		
		// 综测项目信息(en、cn)
		List<HashMap<String,String>>zcxmList=dao.getZcxmList(xn,xq,nd);
		
		zcxmList.addAll(zcpmList);
		
		// ---------2012.01.08 begin --------------------
		// ---------edit by 伟大的骆 --------------------
		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		String zcxn = jbszForm.getPjxn();
		String zcxq = jbszForm.getPjxq();
		String zcnd = jbszForm.getPjnd();

		if (!Base.isNull(xn)) {
			zcxn = xn;
		}

		if (!Base.isNull(xq)) {
			zcxq = xq;
		}

		if (!Base.isNull(nd)) {
			zcnd = nd;
		}
		// ---------2012.01.08 end --------------------
		
		HashMap<String,String>zhcpMap=dao.getZhcpInfo(xh,zcxn,zcxq,zcnd);
		
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		
		for(int i=0;i<zcxmList.size();i++){
			
			HashMap<String,String>zcxmMap=zcxmList.get(i);
			
			String xmdm=zcxmMap.get("en");
			
			String zcinfo=zhcpMap.get(xmdm);
			
			zcxmMap.put("zcinfo", zcinfo);
			
			rs.add(zcxmMap);
			
		}
		
		return rs;
	}
	
	
	public List<HashMap<String,Object>>getZclnInfo(String xh,String xn,String xq,String nd){
		
		List<HashMap<String,String>>zcInfo=getZcInfo(xh, xn, xq, nd);
		
		List<HashMap<String,Object>>zclnInfo=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<zcInfo.size();i+=2){
			
			HashMap<String,Object>zcMap=new HashMap<String,Object>();
			
			HashMap<String,String>leftMap=new HashMap<String,String>();
			HashMap<String,String>rightMap=new HashMap<String,String>();
			
			if(zcInfo.size()-1>=i){
				leftMap.putAll(zcInfo.get(i));
			}
			
			if(zcInfo.size()-1>=i+1){
				rightMap.putAll(zcInfo.get(i+1));
			}
			
			zcMap.put("left", leftMap);
			zcMap.put("right", rightMap);
			
			zclnInfo.add(zcMap);
		}
		
		return zclnInfo;
	}
	
	/**
	 * 获取本次综测基本信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,Object>>getBczcInfo(String xh){
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		String xn=jbszModel.getPjxn();
		
		String xq=jbszModel.getPjxq();
		
		String nd=jbszModel.getPjnd();
		
		List<HashMap<String,String>>zcInfo=getZcInfo(xh, xn, xq, nd);
		
		List<HashMap<String,Object>>zclnInfo=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<zcInfo.size();i+=2){
			
			HashMap<String,Object>zcMap=new HashMap<String,Object>();
			
			HashMap<String,String>leftMap=new HashMap<String,String>();
			HashMap<String,String>rightMap=new HashMap<String,String>();
			
			if(zcInfo.size()-1>=i){
				leftMap.putAll(zcInfo.get(i));
			}
			
			if(zcInfo.size()-1>=i+1){
				rightMap.putAll(zcInfo.get(i+1));
			}
			
			zcMap.put("left", leftMap);
			zcMap.put("right", rightMap);
			
			zclnInfo.add(zcMap);
		}
		
		return zclnInfo;
	}


}
