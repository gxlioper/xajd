package xsgzgl.xsxx.byjd.gdjs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class XsxxByjdService extends BasicService{

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	XsxxByjdDAO dao=new XsxxByjdDAO();
	
	/**
	 * 高级查询方式获取结果集
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getByjdList(BasicModel model) throws Exception{
		
		return dao.getByjdList(model);
	}

	/**
	 * 获取表头
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(BasicModel model,User user){
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao=DAO.getInstance();
		
		String[]en=new String[]{"","xh","xm","nj","xymc","zymc","bjmc","sfyjd"};
		
		String[]cn=new String[]{"","学号","姓名","年级",Base.YXPZXY_KEY,"专业","班级","是否已鉴定"};
		
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 构建结果集(综合测评_综测信息)
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
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					
					if(j==rs.length-1 && rs[j].equalsIgnoreCase("详细")){
					
						html.append("<a href=\"#\" onclick=\"showDetailDiv('"+rs[0]+"');return false;\" ><font color=\"blue\">详细</font></a>");
					
					}else{
						
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
	 * 根据传入得 键、值形式表字段信息进行修改
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
			
		return dao.updateInfo(model);
	} 
	
	/**
	 * 根据传入得 键、值形式表字段信息进行保存
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model,XsxxByjdForm myForm){
		
		//查看学生毕业鉴定信息
		HashMap<String,String>byjdDetail=getByjdDetail(myForm);
		
		boolean result=false;
		
		//判断记录是否存在
		if(byjdDetail!=null && byjdDetail.size()>0){
			//存在修改
			result=updateInfo(model);
		
		}else{
			//不存在增加
			result=dao.saveInfo(model);
		}
		
		return result;
	} 
	
	/**
	 * 删除
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getByjdDetail(XsxxByjdForm myForm){
		
		return dao.getByjdDetail(myForm);
		
	}
	
	/**
	 * 显示字段设置Div
	 * 
	 * @author qlj
	 * 
	 * @throws IOException
	 */
	public void showDetailDiv(XsxxByjdForm myForm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 字段列表
		HashMap<String, String> byjdDetail = dao.getByjdDetail(myForm);

		// 毕业鉴定
		String byjd = Base.isNull(byjdDetail.get("byjd"))?"":byjdDetail.get("byjd");
		
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
		html.append("毕业鉴定");
		html.append("</th>");
		html.append("<td>");

		
		html.append(" <textArea name=\"byjd_name\" id=\"byjd_id\" readonly=\"true\"");
		html.append(" rows='4' style='word-break:break-all;width:96%'>");
		html.append(replaceHtml(byjd));
		html.append("</textArea>");
		

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");

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
	 * 显示字段设置Div
	 * 
	 * @author qlj
	 * 
	 * @throws IOException
	 */
	public void showByjdDiv(XsxxByjdForm myForm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
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
		html.append("<th width='30%'>");
		html.append("毕业鉴定<br/><font color=\"red\">(限输入250字)</font>");
		html.append("</th>");
		html.append("<td>");

		html.append("<textArea name=\"byjd_name\" id=\"byjd_id\" ");	
		html.append("rows='4' style='word-break:break-all;width:96%' onblur=\"chLeng(this,250);\">");
		html.append("</textArea>");
	

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"saveByjd();return false;\">");
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
}
