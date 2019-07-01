package xsgzgl.pjpy.bjlhdx.wdpj.xssq;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ѧ������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class WdpjXssqService extends xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqService{
	
	WdpjXssqDAO dao = new WdpjXssqDAO();

	public boolean updateXssqInfo(BasicModel mode,HashMap<String, String> valueMap, User user) {
		
		boolean flag=false;
		
		HashMap<String,String>modelMap=mode.getValueMap();
		
		modelMap.putAll(valueMap);
		mode.setValueMap(modelMap);
		
		flag=updateInfo(mode);
		
		if(flag){
			
			flag=dao.updateShzt(modelMap, user);
		}
		
		return flag;
	}
	
	/**
	 * ��ѯ����(�ҵ�����_ѧ������)
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
	 * ����HTML(�ҵ�����_ѧ������)
	 * 
	 * @author ΰ�����
	 */
	public String createWdpjXssqHTML(SearchRsModel rsModel,
			WdpjXssqModel model, ArrayList<String[]> rsArrList, User user) {

		// IE�汾
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
					
					html.append("<a href=\"#\" onclick=\"checkXssqZg('add','"+xmdm+"');return false;\"><font color=\"blue\">��&nbsp;&nbsp;��</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">��&nbsp;&nbsp;��</font></a>");
				
				}else if("ysq".equalsIgnoreCase(sqInfo)){
				
					html.append("<a href=\"#\" onclick=\"checkXssqZg('modi','"+xmdm+"');return false;\"><font color=\"blue\">��&nbsp;&nbsp;��</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"disfrockXssqInfo('"+pkValue+"');return false;\"><font color=\"blue\">��&nbsp;&nbsp;��</font></a>");
				
				}else if("ysh".equalsIgnoreCase(sqInfo)){
					
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">�����</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">��&nbsp;&nbsp;��</font></a>");
				
				}else if("th".equalsIgnoreCase(sqInfo)){
					
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">�����</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">��&nbsp;&nbsp;��</font></a>");
				}else if("xcs".equalsIgnoreCase(sqInfo)){
					
					html.append("<a href=\"#\"onclick=\"checkXssqZg('modi','"+xmdm+"');return false;\"><font color=\"blue\">��&nbsp;&nbsp;��</font></a>");
					html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					html.append("<a href=\"#\" onclick=\"return false;\"><font color=\"#A8A7A7\">��&nbsp;&nbsp;��</font></a>");
				
				}
				
				html.append("</td>");
				html.append("</tr>");
			}
			
		}
		return html.toString();
	}
	
	/**
	 * ��ʾѧ������DIV(��������)
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
		html.append("<span>ѧ������</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("��Ŀ˵��");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
		// ��Ŀ˵��
		html.append(Base.isNull(pjxmModel.getXmsm())?"":pjxmModel.getXmsm());
		
		html.append("</td>");
		html.append("</tr>");
		
		
		if("modi".equalsIgnoreCase(opera)){
			HashMap<String,String>xmshMap=dao.getXsshInfoMap(xmdm, xh);
			html.append("<tr>");
			html.append("<th width='25%'>");
			html.append("������");
			html.append("</th>");
			html.append("<td style='word-break:break-all;width:96%'>");
			html.append(Base.isNull(xmshMap.get("shyj"))? "" : xmshMap.get("shyj") );
			html.append("</td>");
			html.append("</tr>");
		}
		
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("��������");
		html.append("<br/><font color='blue'>(������300~500��)</font>");
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
		html.append("�� ��");
		html.append("</button>");

		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
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
