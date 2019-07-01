package xsgzgl.pjpy.zjlyzyxy.wdpj.xssq;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ѧ������_�㽭����ְҵѧԺ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class WdpjXssqService extends
		xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqService {
	
	WdpjXssqDAO dao = new WdpjXssqDAO();
	
	/**
	 * ��ʾѧ������DIV
	 * 
	 * @author qlj
	 * @throws IOException 
	 * 
	 * @throws IOException
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

		html.append("<div class=\"open_win01\" style=\"overflow-x:hidden;overflow-y:auto;height:330px\">");

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
		html.append("<tr>");
		html.append("<th width='25%' rowspan=\"2\">");
		html.append("�����");
		html.append("<br/><font color=\"blue\">(������500��)</font>");
		html.append("</th>");
		html.append("<tr>");
		html.append("<td style='word-break:break-all;width:96%'>");
		html.append("��Ҫ�������");
		html.append("<textarea id=\"hjqk\"  name=\"hjqk\" onblur='chLeng(this,500);'  rows=\"8\" cols=\"\" style='word-break:break-all;width:96%'>");
		if("modi".equalsIgnoreCase(opera)){
			if (!Base.isNull(xssqMap.get("kzzd1"))) {
				html.append(xssqMap.get("kzzd1"));
			}
		}
		html.append("</textarea></br>");
		html.append("���У�Ժ������<input type=\"text\" id=\"yjjl\" onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" maxlength=\"2\" style=\"width:40px\" value=\"");
		if("modi".equalsIgnoreCase(opera)){
			if (!Base.isNull(xssqMap.get("kzzd2"))) {
				html.append(xssqMap.get("kzzd2"));
			}
		}
		html.append("\"/>��;");
		html.append("&nbsp&nbspУ������<input type=\"text\" id=\"xjjl\" onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" maxlength=\"2\" style=\"width:40px\" value=\"");
		if("modi".equalsIgnoreCase(opera)){
			if (!Base.isNull(xssqMap.get("kzzd3"))) {
				html.append(xssqMap.get("kzzd3"));
			}
		}
		html.append("\"/>��;");
		html.append("&nbsp&nbspʡ�����Ͻ���<input type=\"text\" id=\"sjjl\" onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" maxlength=\"2\" style=\"width:40px\" value=\"");
		if("modi".equalsIgnoreCase(opera)){
			if (!Base.isNull(xssqMap.get("kzzd4"))) {
				html.append(xssqMap.get("kzzd4"));
			}
		}
		html.append("\"/>��");
		html.append("</td>");
		html.append("</tr>");

		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("��������");
		html.append("<br/><font color='blue'>(������500��)</font>");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
	
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
		html.append("</table>");
		html.append("</div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");

		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"save('"+opera+"','"+xmdm+"');return false;\">");
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

		response.getWriter().print(html.toString());
	}
	
	public boolean saveXssqInfo(BasicModel mode,HashMap<String, String> valueMap, User user) {

		PjszPjxmDAO pjxmDAO=new PjszPjxmDAO();
		HashMap<String,String>xmMap=pjxmDAO.getPjxmInfo(valueMap.get("xmdm"));
		mode.getValueMap().putAll(valueMap);
		
		HashMap<String,String>saveMap=mode.getValueMap();
		saveMap.putAll(valueMap);
		mode.setValueMap(saveMap);
		if("no".equalsIgnoreCase(xmMap.get("sfsh"))){
			mode.getValueMap().put("sqjg", "wxsh");
		}else{
			mode.getValueMap().put("sqjg", "wsh");
		}
		
		return saveInfo(mode);
	}
}
