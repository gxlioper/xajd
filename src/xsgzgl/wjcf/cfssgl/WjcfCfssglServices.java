package xsgzgl.wjcf.cfssgl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.jcsz.WjcfJcszDao;

/**
 * 
* 
* 类名称：WjcfJcszServices 
* 类描述：违纪处分申诉管理Services
* 创建人：yijd 
* 创建时间：2012-6-19 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class WjcfCfssglServices extends CommService {
	private WjcfCfssglDao dao=new WjcfCfssglDao();
	
	private WjcfJcszDao jcszdao = new WjcfJcszDao();
	/**
	 * 违纪处分 处分申诉管理
	 * 
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfssglCx(WjcfCfssglForm model,User user) throws Exception {
		if(model==null){
			return null;
		}
		String userType=user.getUserType();
		String uType="";
		if("stu".equals(userType)){
			uType="xs";
		}else if("xy".equals(userType) || "xx".equals(userType) || "admin".equals(userType)){
			uType="js";
		}else{
			//用户类型异常查询数据不能申诉
			uType="bnss";
		}
		return dao.cfssglCx(model,uType,user);
	}
	
	/**
	 * 违纪处分 查看申诉信息 tl
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfssglCk(String pkValue) throws Exception {
		if(pkValue==null || "".equals(pkValue)){
			return null;
		}
		return dao.cfssglCk(pkValue);
	}
	
	/**
	 * 处分申诉 更新
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssglGx(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return false;
		}
		model.setSsjg("wsh");
		HashMap<String, String> cfssgl=dao.cfssCk(model.getCfid());
		InputStream is =model.getSsfj().getInputStream();
		String fjmc = StringUtils.isNotNull(model.getSsfjmc()) ? model.getSsfjmc()
				.substring(model.getSsfjmc().lastIndexOf("\\")+1,
						model.getSsfjmc().length()) : "";
		model.setSsfjmc(fjmc);
		
		if(cfssgl==null || cfssgl.isEmpty()){
			boolean flag = dao.cfssZj(model, is);
			//初始化申诉审核表
			if(flag){
				flag = cfssshZj(model);
			}
			
			return flag;
		}else{
			return dao.cfssXg(model, is);
		}
		
	}
	
	//获取审批注字符串
	public HashMap<String, String> getShlStr() {
		return dao.getMapNotOut("select * from xg_wjcf_ssjcsplb", new String[]{});
	}
	
	public HashMap<String, String> cxCfsswh(WjcfCfssglForm model) throws Exception{
		return dao.cfssCk(model.getCfid());
	}
	
	/**
	 * 违纪处分 查看申诉信息 tl
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshxxCk(String pkValue) throws Exception {
		if(pkValue==null || "".equals(pkValue)){
			return null;
		}
		return dao.ssshxxCk(pkValue);
	}
	
	/**
	 * 违纪处分 处分申诉 撤销
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean cfssSc(String pkValue) throws Exception{
		if(pkValue==null || "".equals(pkValue)){
			return false;
		}
		List<String[]> list=new ArrayList<String[]>();
		String[] pks=new String[]{pkValue};
		list.add(pks);
		//撤销申诉  也要删除违纪处分  申诉审核信息
		WjcfCfssglForm model=new WjcfCfssglForm();
		model.setCfid(pkValue);
		dao.cfssshSc(model);
		return dao.cfssSc(list);
	}
	
	/**
	 * 创建页面tableHtml
	 * @param rsModel
	 * @param model
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createTableHTML(SearchRsModel rsModel,
			WjcfCfssglForm model, List<String[]> rsArrList, User user) {
		// IE版本
		String ie = rsModel.getIe();
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowMoreClick(this,'',event);\" style=\"cursor:hand\" >");
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<input type=\"checkbox\" name=\"checkVal\" id=\"pkV\" value=\""+rs[10]+"\" />");
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[0]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[1]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[2]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[3]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[4]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[5]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[6]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[7]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[9]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				if(rs[11]!=null && rs[11].equals("y")){
					if(rs[8]==null || "".equals(rs[8])){
						html.append("<a href=\"#\"  onclick=\"cfss('"+rs[10]+"');return false;\"><font color=\"blue\">申诉</font></a>");
					}else if("wsh".equals(rs[8])){
						html.append("<a href=\"#\" onclick=\"cfssxg('"+rs[10]+"');return false;\"><font color=\"blue\">申诉修改</font></a>");
						html.append("&nbsp;&nbsp;");
						html.append("<a href=\"#\" onclick=\"cfssSc('"+rs[10]+"');return false;\"><font color=\"blue\">撤销</font></a>");
					}else{
						html.append(" <a href=\"#\" onclick='return false;' disabled=\"true\" style=\"color: #cccccc;\" >申诉</a>");
					}
				}else{
					html.append(" <a href=\"#\" onclick='return false;' disabled=\"true\" style=\"color: #cccccc;\">申诉</a>");
				}
				html.append("</td>");
				html.append("</tr>");
			}
		}else{
//			html.append("<div class=\"con_overlfow\" style=\"text-align: center; color: red;\" >");
//			html.append("当前搜索结果无数据。");
//			html.append("</div>");
		}

		return html.toString();
	}
	
	/**
	 * 增加处分申诉审核
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssshZj(WjcfCfssglForm model) throws Exception{
		String[] shlcList=dao.shlcCx();
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
		WjcfCfssglForm cf=null;
		for (int i=0;i< shlcList.length;i++) {
			cf=new WjcfCfssglForm();
			cf.setCfid(model.getCfid());
			cf.setXtgwid(shlcList[i]);
			cf.setShzt("wsh");
			modelList.add(cf);
		}
		return dao.cfssshZj(modelList);
	}
	
	/**
	 * 审批岗位查询   根据用户名
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> spgwCx(String yhm) throws Exception{
		if(yhm==null || "".equals(yhm)){
			return null;
		}
		return dao.spgwCx(yhm);
	}
	
	
	/**
	 * 工具类   获取列表头
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(
			WjcfCfssglForm model, User user,String dataType) {
		List<HashMap<String, String>> topTr=null;
		String[] en =null;
		String[] cn =null;
		if("sscx".equals(dataType)){
			en = new String[] {"pk", "xh", "xm", "xn","xq","cflbmc", "cfyymc", "sswh", "sssj", "ssjg","ssshzt" };
			cn = new String[] {"", "学号", "姓名", "处分学年", "处分学期", "处分类别","处分原因","申诉文号","申诉时间","审核状态","操作" };
		}else if("ssshcx".equals(dataType)){
			en = new String[] {"pk", "xh", "xm", "xn","xq","cflbmc", "cfyymc", "sswh", "sssj", "sszt", "ssjg" };
			cn = new String[] {"", "学年", "学期", "学号", "姓名", "处分类别","处分原因","申诉文号","申诉时间","审核结果","申诉结果"};
		}
		
		topTr = dao.arrayToList(en, cn);
		return topTr;
	}
	
	
	
	/**
	 * 工具类：设置列表头名
	 * 
	 * @param model
	 *            业务模型
	 * @return
	 */
	public String[] getTopTr(WjcfCfssglForm model) {
		String[] outTr = null;
		outTr = new String[] { "学号", "姓名", "处分学年", "处分学期", "处分类别","处分原因","申诉文号","申诉时间","操作","审核状态" };
		return outTr;

	}

	/**
	 * 工具类：显示的页面列表内容列名，也是数据查询出来的列值
	 * 
	 * @param model
	 *            业务模型
	 * @return
	 */
	public String[] getColnumName(WjcfCfssglForm model) {
		String[] colnumName = null;
		colnumName = new String[] { "xh", "xm", "xn","xq",
				"cflbmc", "cfyymc", "sswh", "sssj", "ssjg","ssshzt" };
		return colnumName;
	}
	
	/**
	 * 工具类   格式化文件路径名s   
	 * @param fileName
	 * @return
	 */
	public String formatFileName(String url){
		if(url==null || "".equals(url)){
			return "";
		}
		String fileName=url;
		if(url.lastIndexOf("\\") > -1){
			fileName=url.substring(url.lastIndexOf("\\")+1, url.length());
		}
		return fileName;
	}
	
	/**
	 * 工具类  确认文件格式化
	 * @param fileName
	 * @return
	 */
	public String isCriterionFile(String fileName){
		if(fileName==null || "".equals(fileName)){
			return "";
		}
		String[] suffix=new String[]{"doc","xls","rar","pdf","txt"};
		for (int i = 0; i < suffix.length; i++) {
			if(fileName.lastIndexOf(suffix[i]) == -1){
				//格式不合格
				return fileName;
			}
		}
		return "";
	}
	
	/**
	 * 初始化页面内容
	 * @param rForm
	 * @param model
	 * @param user
	 * @param request
	 * @throws Exception
	 */
	public void initPage(RequestForm rForm, WjcfCfssglForm model, User user,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		String tableName = "view_wjcf_sswh";
		rForm.setTableName(tableName);
		//====================初始化页面相关数据=====================
		// 高级查询
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { });
		request.setAttribute("searchTj", searchModel);
		
		//页面form
		request.setAttribute("rs", model);
		
	}
	
	
	/**
	 * 显示本用户岗位切换模式页面
	 * @author xucy
	 * @throws IOException 
	 */
	public void showShgwDiv(User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
		
		List<HashMap<String,String>> spgwList= spgwCx(user.getUserName());
		
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
		
		//html.append(" <input type=\"hidden\" name=\"text_xmdm\" id=\"text_xmdm\" value=\""+cflbdm+"\" /> ");
		for (int i = 0; i < spgwList.size(); i++) {
			HashMap<String,String> spgwMap=spgwList.get(i);
			html.append(" <input type=\"radio\" name=\"spgws\" ");
			if(i==0){
				html.append("  checked=\"true\" ");
			}
			html.append(" id=\"spgw_"+i+"\" value=\""+spgwMap.get("spgw")+"\">");
			html.append(spgwMap.get("gwmc") );
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
	 * 违纪处分 处分申诉审核查询
	 * 
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfssshCx(WjcfCfssglForm model,User user) throws Exception {
		if(model==null || user==null || model.getXtgwid()==null || "".equals(model.getXtgwid())){
			return null;
		}
		String userType=user.getUserType();
		String uType="";
		if("stu".equals(userType)){
			uType="xs";
		}else if("xy".equals(userType) || "xx".equals(userType) || "admin".equals(userType)){
			uType="js";
		}else{
			//用户类型异常查询数据不能申诉
			uType="bnss";
		}
		//获取上级审批流程
		HashMap<String, String> sjCfsplc=dao.splcSjSpgw(model);
		if(sjCfsplc!=null && sjCfsplc.get("spgw")!=null && !"".equals(sjCfsplc.get("spgw"))
				&& !sjCfsplc.get("spgw").equals(model.getXtgwid())){
			model.setSjSpgw(sjCfsplc.get("spgw"));
		}
		//获取下级审批流程
		HashMap<String, String> xjCfsplc=dao.splcXjSpgw(model);
		if(xjCfsplc!=null && xjCfsplc.get("spgw")!=null && !"".equals(xjCfsplc.get("spgw"))){
			if(xjCfsplc.get("spgw").equals(model.getXtgwid())){
				model.setXjSpgw("");
			}else{
				model.setXjSpgw(xjCfsplc.get("spgw"));
			}
		}else{
			//这里是控制数据  是否可以操作   程序错误数据不能再操作
			return null;
//			model.setXjSpgw("");
		}
		return dao.cfssshCx(model,uType,user);
	}
	
	
	/**
	 * 创建页面tableHtml
	 * @param rsModel
	 * @param model
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createHTMLCfssshCx(SearchRsModel rsModel,
			WjcfCfssglForm model, List<String[]> rsArrList, User user) throws Exception {
		// IE版本
		String ie = rsModel.getIe();
		String stylePath = rsModel.getStylePath();
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				//ondblclick=\"wjcfssglCk();return false;\"
				html.append("<tr onclick=\"rowMoreClick(this,'',event);\" style=\"cursor:hand\" >");
				html.append("<td  nowrap=\"nowrap\" >");
				html.append("<input type=\"checkbox\" name=\"checkVal\" id=\"pkV\" ");
				if("disabled".equals(rs[11])){
					html.append("disabled=\"disabled\"");
				}
				html.append(" value=\"");
				html.append(rs[9]);
				html.append("\" />");
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\" >");
				html.append(rs[0]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[1]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[2]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[3]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[4]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[5]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[6]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[7]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(getImg(rs[10],stylePath));
				html.append("</td>");
				html.append("<td>");
				if("wsh".equals(rs[8])){
					html.append("未审核");
				}else if("shz".equals(rs[8])){
					html.append("审核中");
				}else if("wcycf".equals(rs[8])){
					html.append("维持原处分");
				}else if("cxcf".equals(rs[8])){
					html.append("撤销处分");
				}else if("ggcf".equals(rs[8])){
					html.append("更改处分");
				}else{
					
				}
				html.append("</td>");
				html.append("</tr>");
			}
		}else{
//			html.append("<div class=\"con_overlfow\" style=\"text-align: center; color: red;\" >");
//			html.append("当前搜索结果无数据。");
//			html.append("</div>");
		}

		return html.toString();
	}
	
	/**
	 * 获取上级审核流程    根据当前审核流岗位  处分id   如：当前为一级则返回一级审核流程信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcSjsh(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcSjsh(model);
	}
	
	/**
	 * 获取当前审批流程的 上一个环节审批岗位，
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcSjSpgw(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcSjSpgw(model);
	}
	
	
	/**
	 * 获取下级审核流程    根据当前审核流岗位   处分id   如果当前审核流程为最后一级这则返回当前是好审核流程    待优化
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcXjsh(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcXjsh(model);
	}
	
	
	/**
	 * 获取当前审批流程的 下一个环节审批岗位，
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcXjSpgw(WjcfCfssglForm model)throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcXjSpgw(model);
	}
	/**
	 * 审批流程顶级     根据当前审批
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> spldjCx(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.spldjCx(model);
	}
	
	
	/**
	 * 审批流程一级     根据当前审批
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splyjCx(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splyjCx(model);
	}
	
	/**
	 * 申诉审核修改   单个审核
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean ssshXg(WjcfCfssglForm model)throws Exception{
		if(model==null){
			return false;
		}
		//当前审核状态为退回
		if("th".equals(model.getShzt())){
			HashMap<String, String> ssshSj=splcSjsh(model);
			WjcfCfssglForm sjModel=new WjcfCfssglForm();
			sjModel.setCfid(ssshSj.get("cfid"));
			sjModel.setXtgwid(ssshSj.get("spgw"));
			sjModel.setShzt("xcs");
			dao.ssshXgShzt(sjModel);
		}
		//当前审核状态为重新审核
		if("tg".equals(model.getShzt())){
			HashMap<String, String> ssshXj=splcXjsh(model);
			if("th".equals(ssshXj.get("shzt"))){
				WjcfCfssglForm xjModel=new WjcfCfssglForm();
				xjModel.setCfid(ssshXj.get("cfid"));
				xjModel.setXtgwid(ssshXj.get("spgw"));
				xjModel.setShzt("wsh");
				dao.ssshXgShzt(xjModel);
			}
		}
		//修改处分申诉信息  为审核中
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
		WjcfCfssglForm wcf=new WjcfCfssglForm();
		wcf.setCfid(model.getCfid());
		wcf.setSsjg("shz");
		wcf.setXtgwid(model.getXtgwid());
		modelList.add(wcf);
		cfssXgSsjg(modelList);
		
		return dao.ssshXg(model);
	}
	
	
	
	/**
	 * 申诉审核修改   批量审核
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean ssshPlxg(WjcfCfssglForm model,String pkValue)throws Exception{
		if(model==null || pkValue==null){
			return false;
		}
		String[] cfids=pkValue.split("@@");
		if(cfids.length==0){
			return false;
		}
		
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();//审核表数据
		WjcfCfssglForm wcf=null;
		List<WjcfCfssglForm> ssList=new ArrayList<WjcfCfssglForm>();//申诉表数据
		WjcfCfssglForm ssWcf=null;
		for (int i = 0; i < cfids.length; i++) {
			wcf=new WjcfCfssglForm();
			ssWcf=new WjcfCfssglForm();
			BeanUtils.copyProperties(wcf, model);
			BeanUtils.copyProperties(ssWcf, model);
			wcf.setCfid(cfids[i]);
			modelList.add(wcf);
			//申诉表修改
			ssWcf.setCfid(cfids[i]);
			ssWcf.setSsjg("shz");
			ssList.add(ssWcf);
		}
		
		//当前审核状态为退回
		if("th".equals(model.getShzt())){
			HashMap<String, String> ssshSj=splcSjSpgw(model);
			List<WjcfCfssglForm> sjModelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm sjModel=null;
			for (int i = 0; i < cfids.length; i++) {
			 	sjModel=new WjcfCfssglForm();
			 	sjModel.setCfid(cfids[i]);
				sjModel.setXtgwid(ssshSj.get("spgw"));
				sjModel.setShzt("xcs");
				sjModelList.add(sjModel);
			}
			
			dao.ssshPlxgShzt(sjModelList);
		}
		//当前审核状态为重新审核
		if("tg".equals(model.getShzt())){
			HashMap<String, String> ssshXj=splcXjSpgw(model);
			List<WjcfCfssglForm> xjModelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm xjModel=null;
			for (int i = 0; i < cfids.length; i++) {
				xjModel = new WjcfCfssglForm();
				xjModel.setCfid(cfids[i]);
				xjModel.setXtgwid(ssshXj.get("spgw"));
				xjModel.setShzt("wsh");
				xjModelList.add(xjModel);
			}
			dao.ssshPlxgShzt(xjModelList);
		}
		
		cfssXgSsjg(ssList);//申诉信息 审核状态修改
		return dao.ssshPlxg(modelList);
	}
	
	/**
	 * 查询申诉审核   根据处分id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshCxGjCfid(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.ssshCxGjCfid(model);
	}
	
	/**
	 * 查看申诉审核根据   处分id  岗位级别
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ssshCkGjCfidGwjb(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.ssshCkGjCfidGwjb(model);
	}
	
	/**
	 * 申诉审核统计
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshShjgTj() throws Exception{
		return dao.ssshTj();
	}
	
	/**
	 * 申诉审核提交 违纪处分   （数据提交）
	 * @return
	 * @throws Exception
	 */
	public boolean ssshTjWjcf() throws Exception{
		List<HashMap<String, String>> dtjSh=dao.ssshCxXtj();
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
		WjcfCfssglForm model=null;
		for (int i = 0; i < dtjSh.size(); i++) {
			model=new WjcfCfssglForm();
			model.setCfid(dtjSh.get(i).get("cfid"));
			model.setSftj("1");
			model.setSssj(dtjSh.get(i).get("sssj"));
			model.setSswh(dtjSh.get(i).get("sswh"));
			model.setSsjg(dtjSh.get(i).get("shzt"));
			model.setCfggw(dtjSh.get(i).get("cfggw"));
			modelList.add(model);
		}
		//申诉状态修改提交
		dao.cfssXgSsjg(modelList);
		//申诉审核修改提交
		dao.ssshXgTj(modelList);
		return dao.wjcfXgTj(modelList);
	}
	
	/**
	 * 申诉审核提交 违纪处分   （数据直接提交）
	 * @return
	 * @throws Exception
	 */
	public boolean ssshTj(String pk) throws Exception{
		boolean flag  = false;
		if(null!=pk){
			String params[] = pk.split("@@");
			List<HashMap<String, String>> dtjSh=dao.ssshXtj(params);
			List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm model=null;
			for (int i = 0; i < dtjSh.size(); i++) {
				model=new WjcfCfssglForm();
				model.setCfid(dtjSh.get(i).get("cfid"));
				model.setSftj("1");
				model.setSssj(dtjSh.get(i).get("sssj"));
				model.setSswh(dtjSh.get(i).get("sswh"));
				model.setSsjg(dtjSh.get(i).get("shzt"));
				model.setCfggw(dtjSh.get(i).get("cfggw"));
				modelList.add(model);
			}
			//申诉状态修改提交
			dao.cfssXgSsjg(modelList);
			//申诉审核修改提交
			dao.ssshXgTj(modelList);
			dao.wjcfXgTj(modelList);
		}
		return flag;
	}
	
	/**
	 * 处分申诉修改  申诉结果
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	public boolean cfssXgSsjg(List<WjcfCfssglForm> modelList)throws Exception{
		return dao.cfssXgSsjg(modelList);
	}
	
	/**
	 * 工具类    获取是审核状态图片
	 * @param state  审核状态
	 * @param stylePath  系统根目录
	 * @return
	 * @throws Exception
	 */
	public String getImg(String state,String stylePath)throws Exception{
		StringBuffer html=new StringBuffer();
		if ("未审核".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("审核通过".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("审核不通过".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("退回".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("重新".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");
		}else if ("wsh".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("tg".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("btg".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("th".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("xcs".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");
		}else if ("ggcf".equalsIgnoreCase(state)) {
			html.append("<p>更改处分</p>");
		}else if ("wcycf".equalsIgnoreCase(state)) {
			html.append("<p>维持原处分</p>");
		}else if ("cxcf".equalsIgnoreCase(state)) {
			html.append("<p>撤销处分</p>");
		}
		return html.toString();
	}
	
	/**
	 * 工具类 审批岗位权限  
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> spgwQx(HashMap<String, String> sjSp,HashMap<String, String> xjSp
			,HashMap<String, String> yjSp,HashMap<String, String> djSp,HashMap<String, String> dqSp)throws Exception{
		HashMap<String, String> spgwqx=new HashMap<String, String>();
		if(sjSp==null || xjSp==null || yjSp==null || djSp==null){
			return null;
		}
		String yj=yjSp.get("spgw");//第一级审批岗位
		String dj=djSp.get("spgw");//等级审批岗位
		String sj=sjSp.get("spgw");//上级审批岗位
		String xj=xjSp.get("spgw");//下级审批岗位
		String dq=dqSp.get("xtgwid");//当前浏览审批岗位
		//当前浏览审核环节   为第一级审批环节
		if(yj!=null && yj.equals(dq)){
			if("wsh".equals(xjSp.get("shzt")) || "th".equals(xjSp.get("shzt"))){
				spgwqx.put("tg", "");//有通过权限
			}else{
				spgwqx.put("tg", "disabled=\"disabled\"");//没有通过权限
			}
		}
		//当前浏览审核环节   为中间环节第一级审批环节
		if(yj!=null && !yj.equals(dq)){
			//通过按钮
			if("tg".equals(sjSp.get("shzt"))){
				if("wsh".equals(xjSp.get("shzt")) || "th".equals(xjSp.get("shzt"))){
					spgwqx.put("tg", "");//有通过权限
				}else{
					spgwqx.put("tg", "disabled=\"disabled\"");//没有通过权限
				}
			}else{
				spgwqx.put("tg", "disabled=\"disabled\"");//没有通过权限
			}
			//退回按钮
			if("tg".equals(sjSp.get("shzt"))){
				if("wsh".equals(xjSp.get("shzt")) || "th".equals(xjSp.get("shzt"))){
					spgwqx.put("th", "");//有按钮权限
				}else{
					spgwqx.put("th", "disabled=\"disabled\"");//没有按钮权限
				}
			}else{
				spgwqx.put("th", "disabled=\"disabled\"");//没有按钮权限
			}
		}
		
		//当前浏览审核环节   最后一个环节
		if(dj!=null && dj.equals(dq)){
			//退回按钮
			if("tg".equals(sjSp.get("shzt"))){
				spgwqx.put("th", "");//有按钮权限
			}else{
				spgwqx.put("th", "disabled=\"disabled\"");//没有按钮权限
			}
		}
		
		return spgwqx;
	}
	
	
	/**
	 * 查询申述附件信息
	 * @param form
	 * @return
	 */
	public InputStream fjCx(WjcfCfssglForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfssb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	
	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public InputStream cffjCx(WjcfCfssglForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	

	/**
	 * 判断是否最高级用户
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean isZgjyh(User user) throws Exception {
		
		return dao.isZgjyh(user);
	}
	
	
	/**
	 * 显示处分类别页面
	 * @author xucy
	 * @throws IOException 
	 */
	public void showCflbDiv(HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
		
		List<HashMap<String,String>> cflbList= jcszdao.cflbdmCx();
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>处分类别选择</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("处分类别选择");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");
		

		html.append(" <select name=\"cflbmcs\" id=\"cflbmcs\" style=\"width:150px\" ");
		for (int i = 0; i < cflbList.size(); i++) {
			HashMap<String,String> cflbMap=cflbList.get(i);
			html.append(" <option value=\""+cflbMap.get("mc")+"\" selected=\"selected\">"+cflbMap.get("mc")+"</option> ");
		}
		html.append("</select>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"checkCflb();return false;\">");
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
	
	
	public String[] getSsshSpgw(){
		try {
			return dao.shlcCx();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getSsjcSpgw(){
		
		try {
			return dao.getSsjcSpgw();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 *  处分申诉维护 自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> cfssglCxExport(WjcfCfssglForm model,User user) throws Exception {
		if(model==null){
			return null;
		}
		String userType=user.getUserType();
		String uType="";
		if("stu".equals(userType)){
			uType="xs";
		}else if("xy".equals(userType) || "xx".equals(userType) || "admin".equals(userType)){
			uType="js";
		}else{
			//用户类型异常查询数据不能申诉
			uType="bnss";
		}
		return dao.cfssglCxExport(model,uType,user);
	}
}
