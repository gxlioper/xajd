package xsgzgl.pjpy.general.wdpj.lssb;

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
public class WdpjLssbService extends CommService implements WdpjLssbInterface {

	WdpjLssbDAO dao= new WdpjLssbDAO();
	
	// --------------------------��ʦ�ϱ�����������Ϣ begin--------------------
	/**
	 * ��ñ�ͷ�ļ�(�ҵ�����_��ʦ�ϱ�)
	 * @author qlj
	 */
	public List<HashMap<String, String>> getWdpjLssbTop(PjpyWdpjModel model,
			User user) {
		
		PjpyZhcpDAO zhcpDAO=new PjpyZhcpDAO();
		
		DAO dao = DAO.getInstance();
		
		// ----------------------��ʦ�ϱ� en begin-------------------------------------
		String[]en=new String[]{"xh","xm","nj","bjmc","zd3"};

		// ���������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zypmList=zhcpDAO.getZyPmArr("en");
		
		en=dao.unionArray(en, zypmList);
		
		String[]other=new String[]{"zd1"};
		
		en=dao.unionArray(en, other);
		
		// �۲������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zcpmList=zhcpDAO.getZcPmArr("en");
		
		en=dao.unionArray(en, zcpmList);
		
		other=new String[]{"cz"};
		
		en=dao.unionArray(en, other);
		// ----------------------��ʦ�ϱ� en end-------------------------------------
		
		
		//  ----------------------��ʦ�ϱ� en begin-------------------------------------
		String[]cn=new String[]{"ѧ��","����","�꼶","�༶","������"};

		// ���������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zypmcnList=zhcpDAO.getZyPmArr("cn");
		
		cn=dao.unionArray(cn, zypmcnList);
		
		String[]otherCn=new String[]{"�۲��"};
		
		cn=dao.unionArray(cn, otherCn);
		
		// �۲������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zcpmcnList=zhcpDAO.getZcPmArr("cn");
		
		cn=dao.unionArray(cn, zcpmcnList);
		
		other=new String[]{"����"};
		
		cn=dao.unionArray(cn, other);
		// ----------------------��ʦ�ϱ� en end-------------------------------------
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ѯ����(�ҵ�����_��ʦ�ϱ�)
	 * @author qlj
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		// ָ��������Ŀ�������༶ѧ�������
		ArrayList<String[]> list = dao.getWdpjLssbList(myForm, model, user);
	
		return list;
	}

	/**
	 * ����HTML(�ҵ�����_��ʦ�ϱ�) 
	 * @author qlj
	 */
	public String createWdpjLssbHTML(SearchRsModel rsModel,PjpyWdpjModel model,
			HashMap<String,String>qdrsMap,ArrayList<String[]> rsArrList, User user) {
	
		// IE�汾
		String ie = rsModel.getIe();
		
		// ---------------ָ����Ŀ �༶�����޶� begin------------------
		String str_qdrs=Base.isNull(qdrsMap.get("qdrs")) ? "0" : qdrsMap.get("qdrs");
		int qdrs=Integer.parseInt(str_qdrs);
		// ---------------ָ����Ŀ �༶�����޶� end------------------
		
		
		// ------------------ƴ��html begin -------------------------
		StringBuilder html = new StringBuilder();
		
		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				int len=rs.length;
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<a href=\"#\" onclick=\"showXsxxDiv('"+rs[0]+"');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(rs[0]);
				html.append("</font>");
				html.append("</a>");
				
				// ------------------ϵͳ�Ƽ���Ա begin---------------
				if(i<qdrs){
					html.append("<font color=\"red\">��</font>");
				}
				// ------------------ϵͳ�Ƽ���Ա end---------------
				html.append("</td>");
				
				// -----------------�����ֶ���ʾ begin ----------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}
				// -----------------�����ֶ���ʾ end ----------------------			

				// -----------------��˽�� begin ----------------------
				if("wsq".equalsIgnoreCase(rs[len-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"checkXssqZg('add','"+rs[0]+"');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("�ϱ�");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}else if("wsh".equalsIgnoreCase(rs[len-2])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"disfrockXssqInfo('"+rs[len-1]+"');return false;\">");
					html.append("<font color=\"blue\">");
					html.append("����");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}else{
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<a href=\"#\" onclick=\"return false;\">");
					html.append("<font color=\"#A8A7A7\">");
					html.append("����");
					html.append("</font>");
					html.append("</a>");
					html.append("</td>");
				}
				// -----------------��˽�� end ----------------------				
				html.append("</tr>");
			}
		}
		// ------------------ƴ��html end -------------------------
		return html.toString();
		
	}

	/**
	 * ����������������
	 * @author qlj
	 */
	public String createKidneyDiv(SearchRsModel rsModel, PjpyWdpjModel model, HashMap<String, String> qdrsMap, ArrayList<String[]> rsArrList, User user) {

		StringBuilder html = new StringBuilder();
		
		StringBuilder message = new StringBuilder();
		// ��Ŀ����
		String xmmc = qdrsMap.get("xmmc");
		// ȷ������
		String qdrs= qdrsMap.get("qdrs");
		
		// -------------------��ʦ�ϱ�ҳ����ʾ��Ϣ��չ begin---------------------
		message.append("�������ϱ�:");
		message.append(xmmc);
		message.append("��Ŀ");
		
		// ---------------�ǿ�ʱ��ʾ --------------------
		if(!Base.isNull(qdrs)){
			message.append(",����Ŀ����������Ϊ");
			message.append(qdrs);
			message.append("�ˡ���<font color='red'>��</font>��ѧ��Ϊϵͳ�Ƽ�ѧ����");
		}
		// -------------------��ʦ�ϱ�ҳ����ʾ��Ϣ��չ end---------------------
		
		
		// --------------------������Ϣ�������� begin -------------------------
		html.append("<div style=\"display:none\">");
		html.append(" <input type=\"hidden\" name=\"tsxxInfo\" id=\"tsxxInfo\" value=\"");
		html.append(message);
		html.append("\"/>");
		html.append("</div>");
		// --------------------������Ϣ�������� end -------------------------	
		return html.toString();
	}
	// --------------------------��ʦ�ϱ�����������Ϣ end----------------------
	
	// ----------------------��ʦ�ϱ� begin------------------
	/**
	 * ������ʦ�ϱ���˱���Ϣ
	 * author qlj
	 */
	public boolean saveLssbShInfo(String xmdm, String xh) throws Exception {
		
		WdpjXssqDAO dao=new WdpjXssqDAO();
		// ��˱���Ϣ����
		return dao.saveWdpjShInfo(xmdm, xh);
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
	// ----------------------��ʦ�ϱ� end------------------	
	
	// ----------------------��ʦ�ϱ�����������Ϣ begin------------------	
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
		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>ѧ���ϱ�</span>");
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
		html.append("<th width='25%'>");
		html.append("�ϱ�����");
		html.append("<br/><font color=\"blue\">(������500��)</font>");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%'>");
	
		html.append("<textarea id=\"sqly\"  name=\"sqly\"  rows=\"8\" cols=\"\" style='word-break:break-all;width:96%'>");
		
		// �޸Ĳ�����ʾ��������
		if("modi".equalsIgnoreCase(opera)){
			html.append(Base.isNull(xssqMap.get("sqly"))?"":xssqMap.get("sqly"));// ���ֶδ���
		}
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");

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
		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * ѧ����Ϣ(�γ̳ɼ�)
	 * author qlj
	 */
	public List<HashMap<String,String>> getXscjList(String xh) throws IOException {
		return dao.getXscjList(xh);
	}
		
	
	/**
	 * ѧ����Ϣ(�γ̳ɼ����۲�ɼ�)
	 * author qlj
	 */
	public void showXsxxDiv(User user,String xmdm, String xh, HttpServletResponse response) throws IOException {
		
		XsxxglService stuService = new XsxxglService();
		
		response.setContentType("text/html;charset=gbk");
		
		PjpyZhcpService zhcpService = new PjpyZhcpService();
		
		HashMap<String,String>xsxxInfo=stuService.selectStuinfo(xh);

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\" style=\"overflow-x:hidden;overflow-y:auto;height:350px\" >");

		html.append("<table class=\"formlist\"  >");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>ѧ����Ϣ</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		// ---------------------ѧ��������Ϣ begin -----------------
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("ѧ��");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:24%'>");
		html.append(xsxxInfo.get("xh"));
		html.append("</td>");
		html.append("<th width='25%'>");
		html.append("����");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:24%'>");
		html.append(xsxxInfo.get("xm"));
		html.append("</td>");
		html.append("</tr>");
		// ---------------------ѧ��������Ϣ end -----------------
		
		// ---------------------ѧ���ɼ���Ϣ begin ---------------
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>ѧ���ɼ�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<table width=\"100%\">");
		html.append("<tr>");
		html.append("<th>");
		html.append("ѧ��");
		html.append("</th>");
		html.append("<th>");
		html.append("ѧ��");
		html.append("</th>");
		html.append("<th>");
		html.append("�γ�����");
		html.append("</th>");
		html.append("<th>");
		html.append("�γ�����");
		html.append("</th>");
		html.append("<th>");
		html.append("�ɼ�");
		html.append("</th>");
		html.append("</tr>");
		
		List<HashMap<String,String>>xscjList=dao.getXscjList(xh);
		
		for(int i=0;i<xscjList.size();i++){
			
			HashMap<String,String>xscjMap=xscjList.get(i);
			html.append("<tr>");
			html.append("<td>");
			html.append(xscjMap.get("xn"));
			html.append("</td>");
			html.append("<td>");
			html.append(Base.isNull(xscjMap.get("xqmc"))?"":xscjMap.get("xqmc"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("kcmc"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("kcxz"));
			html.append("</td>");
			html.append("<td>");
			html.append(xscjMap.get("cj"));
			html.append("</td>");
			html.append("</tr>");
		}
		html.append("</table>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ---------------------ѧ���ɼ���Ϣ end -----------------
		
		// ---------------------�����۲�ɼ� begin----------------
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span>�۲�ɼ�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div style=\"overflow:auto;height:120px\">");
		html.append("<table width=\"100%\">");
		
		List<HashMap<String,Object>>bczcInfo=zhcpService.getBczcInfo(xh);
		for(int i=0;i<bczcInfo.size();i++){
			
			HashMap<String,Object>zccjMap=bczcInfo.get(i);
			
			HashMap<String,String>left=(HashMap<String,String>)zccjMap.get("left");
			
			HashMap<String,String>right=(HashMap<String,String>)zccjMap.get("right");
			html.append("<tr>");
			html.append("<th width='25%'>");
			html.append(Base.isNull(left.get("cn"))?"":left.get("cn"));
			html.append("</th>");
			html.append("<td width='25%'>");
			html.append(Base.isNull(left.get("zcinfo"))?"":left.get("zcinfo"));
			html.append("</td>");
			html.append("<th  width='25%'>");
			html.append(Base.isNull(right.get("cn"))?"":right.get("cn"));
			html.append("</th>");
			html.append("<td width='25%'>");
			html.append(Base.isNull(right.get("zcinfo"))?"":right.get("zcinfo"));
			html.append("</td>");
			html.append("</tr>");
			
		}
		html.append("</table>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// ---------------------�����۲�ɼ� end-------------------
		

		html.append("</table>");
		html.append("</div>");
		
		html.append("<div class=\"open_win01\">");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\" align=\"right\">");
		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		
		
		response.getWriter().print(html.toString());
	}

	
	/**
	 * ��ȡ�������Ʒ�Χ
	 * Ϊ�༶��ȷ������
	 * @param xmdm
	 * @param bjdm
	 * @return
	 */
	public HashMap<String,String>getQdrsByBj(String xmdm,String bjdm){
		
		WdpjPjtjDAO pjtjDAO = new WdpjPjtjDAO();
		
		return pjtjDAO.getQdrsByBj(xmdm,bjdm);
	}

	public Boolean updateLssbInfo(BasicModel mode, User user) {
		
		BasicService service=new BasicService();
		
		// TODO �Զ����ɷ������
		return service.updateInfo(mode);
	}


}
