package xsgzgl.gygl.jqlx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-18 上午11:42:20</p>
 */
public class GyglJqlxService  extends BasicService{
	
	GyglJqlxDAO dao= new GyglJqlxDAO();
	
	
	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-1)+"%\" ");
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
	 * 获取假期留校基本设置信息
	 * @param id
	 * @author qlj
	 * @throws Exception
	 */
	public void setJbszInfo(GyglJqlxForm model) throws Exception{
		
		HashMap<String,String>jbszInfo =dao.getJbszInfo();
		
		getModel(model, jbszInfo);
		
	}
	
	/**
	 * 判断是否审核结束
	 * @param lcid
	 * @return
	 */
	public boolean checkShjs(String lcid){
		
		String num=dao.countShjsjl(lcid);
		
		if("0".equalsIgnoreCase(num)){
			
			return true;
		}
		
		return false;
	}
	
	// ----------------------------假期留校申请 begin ------------------------------

	/**
	 * 获取假期留校申请结果集
	 * @param model
	 * @author qlj
	 * @throws Exception
	 */
	public List<String[]>getLxsqList(BasicModel model) throws Exception{
		
		return dao.getLxsqList(model);
	}
	
	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createlxsQSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				
				html.append(" "+rs[rs.length-1]+" ");
				
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-2)+"%\" ");
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
	 * 保存假期留校审核表信息
	 * @param id
	 * @author qlj
	 * @throws Exception
	 */
	public boolean saveJqlxShb(String id,User user) throws Exception{
		
		return dao.saveJqlxShb(id,user);
	}
	// ----------------------------假期留校申请 end ------------------------------
	
	// ----------------------------假期留校审核 begin ----------------------------		
	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createLxshSearchHTML(SearchRsModel rsModel,
			BasicModel model,List<String[]> rsArrList, User user) {
	
		StringBuilder html=new StringBuilder();
		
		String ie=rsModel.getIe();
		
		String stylePath=rsModel.getStylePath();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_"+i+"' ");
				html.append(" "+rs[rs.length-1]+" ");
				html.append(" value='" +replaceHtml(rs[0]) + "'/> ");	
				
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_"+i+"' ");
				html.append(" value='" +replaceHtml(rs[1]) + "'/> ");	
				html.append("</td>");
				
				
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""+100/(rs.length-1)+"%\" ");
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
				
				if("未审核".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("通过".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("不通过".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("退回".equalsIgnoreCase(rs[rs.length-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("需重审".equalsIgnoreCase(rs[rs.length-2])){
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
	 * 假期留校审核查询结果集
	 * @param model
	 * @author qlj
	 * @throws Exception
	 */
	public List<String[]>getLxshList(BasicModel model) throws Exception{
		
		return dao.getLxshList(model);
	}
	

	public List<HashMap<String,String>>getSpgwList(GyglJqlxForm model,User user){
		
		return dao.getSpgwList(model, user);
	}
	
	/**
	 * 显示本用户岗位切换模式窗口
	 * @author qlj
	 * @throws IOException 
	 */
	public void showShgwDiv(GyglJqlxForm model, User user,
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
		
		for(int i=0;i<spgwList.size();i++){
			
			HashMap<String,String>spgwMap=spgwList.get(i);
			html.append("<input type='hidden' name='shjb' id=\"shjb_"+i+"\" value=\""+spgwMap.get("xh")+"\">");
			html.append("<input type=\"radio\" name=\"spgw\" ");
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
	 * 修改审核状态
	 * @param model
	 * @param basicModel
	 * @param request
	 * @param user
	 * @author qlj
	 * @throws Exception
	 */
	public boolean updateShzt(GyglJqlxForm model,BasicModel basicModel, 
			HttpServletRequest request, User user) throws Exception {

		setJbszInfo(model);
		
		getModelValue(model, request);
		
		String shzt=model.getShzt();
		
		basicModel.getValueMap().put("spgw", model.getSpgw());
		
		dao.resultShzt(model, user);
		
		boolean flag=dao.updateShzt(model, user);
		
		if("th".equalsIgnoreCase(shzt) && flag){
			
			 flag=dao.updateThzt(model,basicModel, user);
		}
		
		flag=dao.updateSqjg(model,basicModel, user);
		
		return  flag;
	}
	

	/**
	 * 获取指定用户所在审核岗位数
	 * @param user
	 * @author qlj
	 */
	public String countSpgw(User user){
		
		return dao.countSpgw(user);
		
	}
	// ----------------------------假期留校审核 end ------------------------------
	
	
	// ----------------------------假期留校结果查询 begin --------------------------
	/**
	 * 假期留校审核查询结果集
	 * @param model
	 * @author qlj
	 * @throws Exception
	 */
	public List<String[]>getLxjgList(BasicModel model) throws Exception{
		
		return dao.getLxjgList(model);
	}
	

	/**
	 * 获取学生申请信息
	 * @param sqid
	 * @author qlj
	 */
	public HashMap<String,String>getJqlxSqInfo(String sqid){
		
		return dao.getJqlxSqInfo(sqid);
	}
	
	/**
	 * 获取学生审核信息
	 * @param sqid
	 * @param spgw
	 * @author qlj
	 */
	public List<HashMap<String,String>>getJqlxShInfo(String sqid,String spgw){
		
		return dao.getJqlxShInfo(sqid, spgw);
	}
	
	// ----------------------------假期留校结果查询 end ----------------------------	
	
	
	/**
	 * 根据流程ID查找审批岗位
	 */
	public String[] getSpgwByLcid(String lcid) throws SQLException{
		
		String sql = "select spgw from xg_xtwh_spbz where splc=? order by xh";
		
		return DAO.getInstance().getArray(sql, new String[]{lcid}, "spgw");
	}
}
