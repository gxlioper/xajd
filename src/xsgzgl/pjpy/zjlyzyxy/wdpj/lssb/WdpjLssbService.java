package xsgzgl.pjpy.zjlyzyxy.wdpj.lssb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.pjtj.WdpjPjtjDAO;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-11 ����10:27:12</p>
 */
public class WdpjLssbService extends xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService {
	
	/**
	 * ��ʦ�ϱ�����
	 * author qlj
	 */
	public void showLssbDiv(User user, String opera, String xmdm, String xh, HttpServletResponse response) throws IOException {
		
		// ���ñ����ʽ
		response.setContentType("text/html;charset=gbk");
		
		// ------------------������Ŀ��Ϣ begin -----------------------
		PjszPjxmService pjxmService = new PjszPjxmService();
		
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);
		// ------------------������Ŀ��Ϣ end  -----------------------
		HashMap<String,String>xssqMap=new HashMap<String,String>();
		
		// ------------------�޸Ĳ�������ѯ��������Ϣ-----------------
		if("modi".equalsIgnoreCase(opera)){
			WdpjXssqDAO xssqDAO=new WdpjXssqDAO();
			xssqMap.putAll(xssqDAO.getXssqMap(xmdm, xh));
		}
		
		StringBuilder html = new StringBuilder();
		// ------------------�ϱ����޸�html��ƴ��--------------------
		html.append("<div class=\"open_win01\" style=\"overflow-x:hidden;overflow-y:auto;height:340px\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��ʦ�ϱ�</span>");
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
		html.append(Base.isNull(pjxmModel.getXmsm())? "" : pjxmModel.getXmsm());
		
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
		html.append("�ϱ�����");
		html.append("<br/><font color=\"blue\">(������500��)</font>");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
	
		html.append("<textarea id=\"sqly\" onblur='chLeng(this,500);'  name=\"sqly\"  rows=\"8\" cols=\"\" style='word-break:break-all;width:96%'>");
		
		// �޸Ĳ�����ʾ��������
		if("modi".equalsIgnoreCase(opera)){
			html.append(Base.isNull(xssqMap.get("sqly"))?"":xssqMap.get("sqly"));// ���ֶδ���
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
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"save('"+opera+"','"+xmdm+"','"+xh+"');return false;\">");
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
	/**
	 * ������Ŀ�ϱ���Ϣ
	 * author qlj
	 */
	public Boolean saveXmsb(BasicModel mode, User user) {
		
		BasicService service=new BasicService();
		
		PjszPjxmDAO pjxmDAO=new PjszPjxmDAO();
		
		HashMap<String,String>xmMap=pjxmDAO.getPjxmInfo(mode.getValueMap().get("xmdm"));
		
		if("no".equalsIgnoreCase(xmMap.get("sfsh"))){
			mode.getValueMap().put("sqjg", "wxsh");
		}else{
			mode.getValueMap().put("sqjg", "wsh");
		}
		// �������Ϣ����
		return service.saveInfo(mode);
	}
	
}
