package xsgzgl.qgzx.gwgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.conditions.KnsrdCondition;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglService extends BasicService {
	
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	QgzxGwglDAO qgzxGwglDAO = new QgzxGwglDAO();
	
	
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = null;
		String[] cn = null;
		if("10052".equals(Base.xxdm)){
			 en = new String[] { "", "r", "xn", "bmmc","gwxh", "gwmc", "gwxzmc", "xqrs", "zgrs", "tgrs" };
			 cn = new String[] { "", "行号", "学年", "用人部门", "岗位序号","岗位名称", "岗位性质", "需求人数","在岗人数","退岗人数" };
		}
		else{
		 en = new String[] { "", "r", "xn", "bmmc", "gwmc", "gwxzmc", "xqrs", "zgrs", "tgrs" };
		 cn = new String[] { "", "行号", "学年", "用人部门", "岗位名称", "岗位性质", "需求人数","在岗人数","退岗人数" };
		}
		if("stu".equalsIgnoreCase(type)){
			en = new String[]{"", "r", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sfkns" };
			cn = new String[]{"", "行号", "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY, "专业", "班级","是否困难生" };
		}else if("gwsq".equalsIgnoreCase(type)){
			//浙江交通职业
			if("12036".equals(Base.xxdm)){
				en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
				cn = new String[]{"", "行号", "学年", "用人部门", "岗位名称","岗位性质", "需求人数", "需求困难生数", "申请时间", "审核状态" };
			}
			else{
			en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
			cn = new String[]{"", "行号", "学年", "用人部门", "岗位名称","岗位性质", "需求人数", "需求困难生数", "申请时间", "审核状态" };
			}
		}else if("gwsh".equalsIgnoreCase(type)){
			en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
			cn = new String[]{"", "行号", "学年", "用人部门", "岗位名称","岗位性质", "需求人数", "需求困难生数", "申请时间", "审核状态" };
		}
		
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwsqList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwsqList(model);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwshList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwshList(model);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwxxList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwxxList(model);
	}
	
	
	/**
	 * 获得学生信息List
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getStuList(QgzxGwglForm model,HttpServletRequest request) throws Exception {
		
		return qgzxGwglDAO.getStuList(model,request);
	}

	
	/**
	 * 获得岗位信息Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getGwxxMap(QgzxGwglForm myForm) {
		return qgzxGwglDAO.getGwxxMap(myForm);
	}
	
	
	/**
	 * 获得岗位及人员信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getGwryxx(QgzxGwglForm model,HttpServletRequest request)throws SQLException{
		//岗位信息
		HashMap<String,String> rs = qgzxGwglDAO.getGwxxMap(model);
		//获得在岗人员并生成html
		model.setZgzt("zg");
		//在岗人员学号
		String[] zgryxh = qgzxGwglDAO.getRyxhList(model);
		//发送在岗学生学号值到前台
		setRequest(request,zgryxh,rs);
		List<HashMap<String, String>> zgryxxList = getGwxsxxList(zgryxh,model);
		rs.put("zgryHtml", createRyxxHtml(zgryxxList,model));
		//获得退岗人员并生成html
		model.setZgzt("tg");
		String[] tgryxh = qgzxGwglDAO.getRyxhList(model);
		List<HashMap<String, String>> tgryxxList = getGwxsxxList(tgryxh,model);
		rs.put("tgryHtml", createRyxxHtml(tgryxxList,model));
		//当前时间 yyyyMMdd
		DAO dao = DAO.getInstance();
		String currTime = dao.getNowTime("yyyyMMdd");
		rs.put("tgsj", currTime);
		return rs;
	}
	
	
	/**
	 * 获得岗位申请MAP
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getGwsqMap(QgzxGwglForm model){
		return qgzxGwglDAO.getGwsqMap(model);
	}
	
	/**
	 * 发送在岗学生学号+已经达到最大勤工数 的学号值到前台
	 * @param request
	 * @param zgryxh
	 * @throws SQLException 
	 */
	private void setRequest(HttpServletRequest request, String[] zgrypk, HashMap<String,String> gwxx) throws SQLException {
		String xn = gwxx.get("xn");
		int count = 0;
		//获得学年已经到达上限的学生学号
		String[] sxxhs = getGwxsxh(xn); 
		StringBuilder xhs = new StringBuilder();
		for(int i = 0;i<zgrypk.length;i++){
			String zgryxh = zgrypk[i].split("!!@@!!")[0];
			//判断是否是困难生
			KnsrdCondition knsrd = new KnsrdCondition();
			String knsCount = knsrd.getKnsrdInfo(zgryxh, new HashMap<String,String>());
			if(knsCount==null && "".equals(knsCount)){
				knsCount="0";
			}	
			if(Integer.parseInt(knsCount)>0){
				count++;
			}
			xhs.append(zgryxh+"!!@@!!");
		}
		for(int i = 0;i<sxxhs.length;i++){
			String sxxh = sxxhs[i].split("!!@@!!")[0];
			xhs.append(sxxh+"!!@@!!");
		}
		request.setAttribute("yykns", count);
		request.setAttribute("xhs", xhs.toString());
	}


	/**
	 * 获得该学年已经到达上限的学生学号
	 * @param xn
	 * @return
	 * @throws SQLException 
	 */
	private String[] getGwxsxh(String xn) throws SQLException {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		String xsgws = qgzxCsszService.getCssz().get("xsgws");
		if("0".equalsIgnoreCase(xsgws)){
			return new String[]{};
		}
		return qgzxGwglDAO.getGwxsxh(xn,xsgws);
	}


	/**
	 * 获得岗位学生信息
	 * @param myForm
	 * @return
	 */
	private List<HashMap<String, String>> getGwxsxxList(String[] pks,QgzxGwglForm model) {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < pks.length;i++){
			String[] pkV = pks[i].split("!!@@!!");
			model.setXh(pkV[0]);
			model.setSgsj(pkV[1]);
			//model.setSqbhs(pkV[3]);
			HashMap<String, String> rs = qgzxGwglDAO.getGwxsxx(model);
			KnsrdCondition knsrd = new KnsrdCondition();
			//设置获取是否困难生增加 学年条件
			HashMap<String,String> param= new HashMap<String,String>();
			HashMap<String, String> gwxx=qgzxGwglDAO.getGwxxMap(model);
			param.put("xn", gwxx.get("xn"));
			String knsCount = knsrd.getKnsrdInfo(pkV[0],param);
			if(knsCount==null && "".equals(knsCount)){
				knsCount="0";
			}	
			if(rs!=null&&rs.size()!=0){
				rs.put("r", String.valueOf(i+1));
				//判断是否困难生
				if(Integer.parseInt(knsCount)>0){
					rs.put("sfkns", "是");
				}else{
					rs.put("sfkns", "否");
				}
				ryxxList.add(rs);
			}
		}
		return ryxxList;
	}
	
	
	/**
	 * 获得学生信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getXsxxList(QgzxGwglForm model) {
		DAO dao = DAO.getInstance();
		String[] xhs = model.getPkValue().split("!!@@!!");
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < xhs.length;i++){
			model.setXh(xhs[i]);
			HashMap<String, String> rs = qgzxGwglDAO.getXsxx(model);
			if(rs!=null&&rs.size()!=0){
				rs.put("r", String.valueOf(i+1));
				//判断是否困难生
				rs.put("sfkns", dao.isKns(xhs[i])?"是":"否");
				rs.put("sgsj", dao.getNowTime("yyyyMMdd"));
				ryxxList.add(rs);
			}
			
		}
		return ryxxList;
	}
	
	
	/**
	 * 创建人员信息html
	 * @param tgryxxList
	 * @return
	 */
	private String createRyxxHtml(List<HashMap<String, String>> rsArrList,QgzxGwglForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String,String> rs = rsArrList.get(i);
				if("ryxxZj".equalsIgnoreCase(model.getDoType()) || "ryxxTg".equalsIgnoreCase(model.getDoType())){
					html.append("<tr><td><input type='checkbox' value='"+rs.get("xh")+"!!@@!!"+rs.get("sgsj")+"!!@@!!"+rs.get("sjly")+"!!@@!!"+rs.get("sqbh")+"'/></td>");
				}else{
					html.append("<tr><td>"+rs.get("r")+"</td>");
				}
				html.append("<td><a href='javascript:void(0);' class='name' onclick='zxsxxView(&quot;"
						+ rs.get("xh")
						+ "&quot;);return false;'>"
						+ rs.get("xh") + "</a></td>");
//				html.append("<td>"+rs.get("xh")+"</td>");
				html.append("<td>"+rs.get("xm")+"</td>");
				html.append("<td>"+rs.get("bjmc")+"</td>");
				html.append("<td>"+rs.get("sfkns")+"</td>");
				if("ryxxZj".equalsIgnoreCase(model.getDoType())||"ryxxTg".equalsIgnoreCase(model.getDoType())){
					html.append("<td>");
					html.append("<a href=\"#\" class=\"name\" onclick=\"showXsxx('"+replaceHtml(rs.get("xh"))+"');return false;\" value=\"" +replaceHtml(rs.get("xh"))+"\">");
					html.append(replaceHtml(rs.get("qggws")));
					html.append("</a></td>");
				}
				if("gwxxCk".equalsIgnoreCase(model.getDoType())){
					html.append("<td>"+rs.get("sgsj")+"</td>");
					if("tg".equalsIgnoreCase(model.getZgzt())){
						html.append("<td>"+rs.get("tgsj")+"</td>");
					}
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 保存新增加的岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String gwxxBc(QgzxGwglForm model) throws Exception {
		String gwxh="";
		if("10052".equals(Base.xxdm)){
			gwxh=qgzxGwglDAO.GetGwxh(model.getYrbm(), model.getXn());
		}
		model.setGwxh(gwxh);
		return qgzxGwglDAO.gwxxBc(model)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	private boolean isExist(QgzxGwglForm model) {
		return qgzxGwglDAO.isExist(model);
	}
	
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	private boolean checkZjGwsqInfoExist(QgzxGwglForm model) {
		return qgzxGwglDAO.checkZjGwsqInfoExist(model);
	}
	
	
	
	
	/**
	 * 保存修改岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String gwxxXg(QgzxGwglForm myForm) throws Exception {
		boolean flag = qgzxGwglDAO.gwxxXg(myForm);
		if(flag){
			flag = qgzxGwglDAO.bcXgGwsqByJg(myForm);
		}
		return flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 保存修改岗位申请
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String bcXgGwsq(QgzxGwglForm myForm) throws Exception {
		return qgzxGwglDAO.bcXgGwsq(myForm)? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 岗位信息复制
	 * @param xn
	 * @param myForm
	 * @return
	 * @throws SQLException 
	 */
	public String gwxxFz(String xn, QgzxGwglForm myForm) throws SQLException {
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] input = {xn,pkValue[i]};
			params.add(input);
		}
		return qgzxGwglDAO.gwxxSave(params)? MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	
	/**
	 * 岗位信息删除
	 * @param myForm
	 * @return
	 * @throws SQLException 
	 */
	public String gwxxSc(QgzxGwglForm myForm) throws SQLException {
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			if(isUsed(pkValue[i])){
				return "岗位已被使用,不能被删除!请确认";
			}
			params.add(new String[]{pkValue[i]});
		}
		return qgzxGwglDAO.gwxxSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	
	
	
	/**
	 * 岗位信息删除
	 * @param myForm
	 * @return
	 * @throws SQLException 
	 */
	public String gwsqSc(QgzxGwglForm myForm) throws SQLException {
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
		}
		return qgzxGwglDAO.gwsqSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	
	
	
	/**
	 * 判断岗位是否已被使用
	 * @param pkValue
	 * @return
	 */
	private boolean isUsed(String pkValue) {
		return qgzxGwglDAO.isUsed(pkValue);
	}
	
	
	/**
	 * 获得用人单位List
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxGwglForm myForm) {
		return qgzxGwglDAO.getYrbm(myForm);
	}
	
	
	/**
	 * 获得用人单位名称
	 * @param yrdwdm
	 * @return
	 */
	public String getYrdwmc(String yrdwdm){
		return qgzxGwglDAO.getYrdwmc(yrdwdm);
	}
	
	
	/**
	 * 生成岗位代码（学年+4位流水号）
	 * @param xn
	 * @return
	 */
	public String getGwdm(String xn,int start) {
		String max = qgzxGwglDAO.getMaxGwdm();
		if(max==null){
			return xn+"0001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+start+1));
			String pre = "";
			for(int i = 0; i < 4-max.length();i ++){
				pre+="0";
			}
			return xn+pre+max;
		}
	}
	/**
	 * 增加页面的默认参数
	 * @param request
	 * @return
	 */
	public HashMap<String, String> setZjmrCs(HttpServletRequest request) {
		HashMap<String,String> rs = new HashMap<String,String>();
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		//如果不是勤工管理员
		User user = getUser(request);
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			rs.put("dis", "true");
		}
		DAO dao = DAO.getInstance();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		String sfkfgwsq = cs.get("sfkfgwsq");
		String gwsqkssj = cs.get("gwsqkssj");
		String gwsqjssj = cs.get("gwsqjssj");
		String sfkfxsgwsq = cs.get("sfkfxsgwsq");
		String xsgwsqkssj = cs.get("xsgwsqkssj");
		String xsgwsqjssj = cs.get("xsgwsqjssj");
		String dsfxy = cs.get("dsfxy");
		String kcxs = cs.get("kcxs");
		if("10125".equals(Base.xxdm)) {
			String gwxx = cs.get("gwxx");
			rs.put("gwxx", gwxx);
		}
		String dqsj = dao.getNowTime("yyyyMMdd");
		if("on".equalsIgnoreCase(sfkfgwsq) 
				&& (null==gwsqkssj || Integer.valueOf(dqsj) >= Integer.valueOf(gwsqkssj))
				&& (null==gwsqjssj || Integer.valueOf(dqsj) <= Integer.valueOf(gwsqjssj))){
			rs.put("kycz", "true");
		}
		if("on".equalsIgnoreCase(sfkfxsgwsq) 
				&& (null==xsgwsqkssj || Integer.valueOf(dqsj) >= Integer.valueOf(xsgwsqkssj))
				&& (null==xsgwsqjssj || Integer.valueOf(dqsj) <= Integer.valueOf(xsgwsqjssj))){
			rs.put("xssqkg", "true");
		}else{
			rs.put("xssqkg", "false");
		}
		String yrbm = user.getUserDep();
		rs.put("yrbm", yrbm);
		rs.put("yrdwmc", getYrdwmc(yrbm));
		rs.put("xn", Base.currXn);
		rs.put("dsfxy", dsfxy);
		rs.put("kcxs", kcxs);
		rs.put("qgzq", cs.get("qgzq"));
		return rs;
	}
	
	
	/**
	 * 创建页面
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:2.5%\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
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
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 创建页面
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByGwsh(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "' "+replaceHtml(rs[1])+"/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 2; j < rs.length; j++) {
				
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					if(j == 5) {
						html.append("<a href='javascript:void(0);' class='name' onclick='showViewLink(&quot;"
								+ replaceHtml(rs[0])
								+ "&quot;);return false;'>"
								+ replaceHtml(rs[j]) + "</a>");
					} else {
						html.append(replaceHtml(rs[j]));
					}
					
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 创建页面查询结果
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML2(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
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
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 验证保存信息
	 * @param model
	 * @return
	 */
	public String checkBcInfo(QgzxGwglForm model) {
		if(isExist(model)){
			return "同一学年,同一部门不可增加相同岗位名称的岗位信息,请确认";
		}
		return "true";
	}
	
	/**
	 * 验证增加岗位申请信息
	 * @param model
	 * @return
	 */
	public String checkZjGwsqInfo(QgzxGwglForm model) {
		if(checkZjGwsqInfoExist(model)){
			return "存在相同岗位名称的岗位信息,请确认";
		}
		return "true";
	}
	
	
	/**
	 * 验证删除信息
	 * @param model
	 * @return
	 */
	public String checkScInfo(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0; i < pkValue.length; i++){
			if(isUsed(pkValue[i])){
				return "岗位已被使用,不能被删除!请确认";
			}
		}
		return "true";
	}
	
	
	/**
	 * 验证复制信息
	 * @param model
	 * @return
	 */
	public String checkFzInfo(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwmcs="";
		for(int i = 0; i < pkValue.length; i++){
			model.setPkValue(pkValue[i]);
			HashMap<String,String> map = getGwxxMap(model);
			model.setYrbm(map.get("yrdwdm"));
			String gwmc = map.get("gwmc");
			String yrdwdm = map.get("yrdwdm");
			if(gwmcs.indexOf(","+gwmc+yrdwdm+",")!=-1){
				return "勾选岗位中包含相同名称的岗位信息，请确认";
			}
			gwmcs+=","+gwmc+yrdwdm+",";
			model.setGwmc(gwmc);
			if(isExist(model)){
				return "同一学年,同一部门不可增加相同岗位名称的岗位信息,请确认";
			}
		}
		return "true";
	}

	
	/**
	 * 批量保存岗位人员信息
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String bcZjryxx(QgzxGwglForm model) throws SQLException {
		DAO dao = DAO.getInstance(); 
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		//允许的非困难生人数
		int fknsrs = Integer.parseInt(model.getFknsrs());
		int count = 0;
		List<String[]> params = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				if(isOver(gwdm,xh[i])){
					return "学号为"+xh[i]+"的学生已到达当前学年最大岗位上限，请确认";
				}
				//验证困难生人数
				KnsrdCondition knsrd = new KnsrdCondition();
				
				HashMap<String, String> knsTj=new HashMap<String, String>();
				knsTj.put("xn", model.getXn());
				String kns= knsrd.getKnsrdInfo(xh[i],knsTj);
				
				KnsjgService knsjgService=new KnsjgService();
				List<HashMap<String, String>> knsList = knsjgService.getKnsInfoList(xh[i]);
				//验证是否当前勤工岗位的困难生
				if(StringUtil.isNull(kns) && knsList != null){
					//是否市困难生
					boolean isKns=false;
					for (int j = 0; j < knsList.size(); j++) {
						if(model.getXn().equals(knsList.get(i).get("xn"))){
							isKns= true;
						}
					}
					if(!isKns){
						return "学号为"+xh[i]+"的学生非当前勤工岗位对应学年下的困难生";
					}
				}
				
				if(kns==null || "".equals(kns)){
					kns = "0";
				}
				if(Integer.parseInt(kns)<=0){
					count++;
				}
				String[] el = new String[]{gwdm,xh[i]};
				params.add(el);
			}
		}
		if(count>fknsrs){
			return "该岗位的困难生数未能到达岗位要求，请确认";
		}
		return qgzxGwglDAO.bcZjryxx(params)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 是否达到岗位上限
	 * @param string
	 * @return
	 */
	private boolean isOver(String gwdm,String xh) {
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		String zdgws = qgzxCsszService.getCssz().get("xsgws");
		if("0".equalsIgnoreCase(zdgws)||null==zdgws){
			return false;
		}
		String gws = qgzxGwglDAO.getGws(gwdm,xh);
		return Integer.parseInt(zdgws)<=Integer.parseInt(gws);
		
	}


	/**
	 * 批量保存岗位人员信息
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String scRyxx(QgzxGwglForm model) throws SQLException {
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				String[] el = new String[]{gwdm,xh[i]};
				params.add(el);
			}
		}
		return qgzxGwglDAO.scRyxx(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	


	/**
	 * 批量保存退岗人员信息
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String bcTgryxx(QgzxGwglForm model) throws SQLException {
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		String[] sgsj = model.getSgsj().split("!!@@!!");
		String[] sqids = model.getSqbhs().split("!!@@!!");
		String tgyy = model.getTgyy();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramssq = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				String[] el = new String[]{tgyy,gwdm,xh[i],sgsj[i]};
				params.add(el);
				//对应申请数据还原
				String[] sqid = new String[]{sqids[i]};
				paramssq.add(sqid);
			}
		}
		return qgzxGwglDAO.bcTgryxx(params)&&qgzxGwglDAO.hySqsj(paramssq)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 人员信息查看
	 * @param myForm
	 * @param request
	 * @return
	 */
	public HashMap<String, String> ryxxCk(QgzxGwglForm model,
			HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		//岗位信息
		HashMap<String,String> rs = qgzxGwglDAO.getXsxx(model);
		rs.put("sfkns", dao.isKns(model.getXh())?"是":"否");
		//获得在岗人员并生成html
		model.setZgzt("zg");
		List<HashMap<String, String>> zggwList = getGwxx(model);
		rs.put("zggwHtml", createGwxxHtml(zggwList,model));
		return rs;
	}


	/**
	 * 获得岗位信息根据学号
	 * @param model
	 * @return
	 */
	private List<HashMap<String, String>> getGwxx(QgzxGwglForm model) {
		return qgzxGwglDAO.getGwxx(model);
	}

	
	/**
	 * 创建岗位信息页面
	 * @param gwxxList
	 * @param model
	 * @return
	 */
	private String createGwxxHtml(List<HashMap<String, String>> rsArrList,
			QgzxGwglForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String,String> rs = rsArrList.get(i);
				html.append("<tr><td>"+rs.get("r")+"</td>");
				html.append("<td>"+rs.get("xn")+"</td>");
				html.append("<td>"+rs.get("bmmc")+"</td>");
				html.append("<td>"+rs.get("gwmc")+"</td>");
				html.append("<td>"+rs.get("gwxzmc")+"</td>");
				html.append("<td>"+rs.get("sgsj")+"</td>");
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	/**
	 * 验证删除人员信息
	 * @param myForm
	 * @return
	 */
	public String checkScRyxx(QgzxGwglForm model) {
		String message = "true";
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		for(int i = 0; i < xh.length; i++){
			if(isUsed(gwdm,xh[i])){
				return "学号为"+xh[i]+"的学生已发放过酬金不能删除，请确认";
			}
		}
		return message;
	}


	/**
	 * 在此岗位上是否发放过酬金
	 * @param gwdm
	 * @param string
	 * @return
	 */
	private boolean isUsed(String gwdm, String xh) {
		return qgzxGwglDAO.isUsed(gwdm,xh);
	}


	/**
	 * 保存增加岗位申请
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String bcZjGwsq(QgzxGwglForm model) throws Exception {
		return qgzxGwglDAO.bcZjGwsq(model)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 岗位信息审核保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String gwxxshBc(QgzxGwglForm model) throws Exception {
		boolean flag = false;
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwxh ="";
		if("10052".equals(Base.xxdm)){
			gwxh = qgzxGwglDAO.GetGwxh(model.getYrbm(),model.getXn());
		}
		String shzt = model.getShzt();
		String shyj = model.getShyj();
		String shr = model.getUser().getUserName();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsByZs = new ArrayList<String[]>();
		if(pkValue.length == 0){
			return MessageInfo.MESSAGE_SH_FALSE;
		}
		if("1".equalsIgnoreCase(shzt)){ // 审核通过
			int okNum = 0;
			StringBuilder notOk = new StringBuilder(); // 重复岗位
			for(int i = 0; i < pkValue.length; i++){
				QgzxGwglForm formTemp = new QgzxGwglForm();
				formTemp.setPkValue(pkValue[i]);
				HashMap<String, String> formMap = getGwsqMap(formTemp);
				formTemp.setXn(formMap.get("xn"));
				formTemp.setYrbm(formMap.get("yrdwdm"));
				formTemp.setGwmc(formMap.get("gwmc"));
				if(isExist(formTemp)){ // 重复岗位
					notOk.append(formMap.get("gwmc") + "；");
				}else{
					String[] el = new String[]{shyj,shzt,shr,pkValue[i]};
					String[] el2 = new String[]{gwxh,pkValue[i]};
					params.add(el);
					paramsByZs.add(el2);
					okNum++;
				}
			}
			flag = qgzxGwglDAO.gwxxshBc(params);
			if(flag){
				flag = qgzxGwglDAO.insertByZs(paramsByZs);
			}
			String resultMsg = "审核成功"+okNum+"条！";
			if(pkValue.length - okNum > 0){
				resultMsg += "重复岗位：" + notOk.toString().substring(0, notOk.toString().length() - 1);
			}
			return flag?resultMsg : MessageInfo.MESSAGE_SH_FALSE;
		}else{
			for(int i = 0; i < pkValue.length; i++){
				String[] el = new String[]{shyj,shzt,shr,pkValue[i]};
				params.add(el);
			}
			flag = qgzxGwglDAO.gwxxshBc(params);
			return flag?MessageInfo.MESSAGE_SH_SUCCESS : MessageInfo.MESSAGE_SH_FALSE;
		}
	}
	/**
	 *  用人单位岗位申请 自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwsqExportList(QgzxGwglForm model,User user) throws Exception {

		return qgzxGwglDAO.getGwsqExportList(model,user);
	}
}
