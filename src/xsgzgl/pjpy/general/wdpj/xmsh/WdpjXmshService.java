package xsgzgl.pjpy.general.wdpj.xmsh;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_��Ŀ���_ͨ��_Service��
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

public class WdpjXmshService extends CommService implements WdpjXmshInterface {

	WdpjXmshDAO dao = new WdpjXmshDAO();
	
	
	// ===========================������Ŀ��� ����� begin =============================
	/**
	 * ����豾�û������Ŀ
	 * @author qlj
	 */
	public List<HashMap<String, String>> getCshXmList(WdpjXmshModel model,
			User user) {

		List<HashMap<String, String>> list = dao.getCshXmList(model, user);
		
		return list;
	}
	
	/**
	 * ����豾�û������Ŀ
	 * (������˿��ؼ�ʱ�����)
	 * @author qlj
	 */
	public List<HashMap<String, String>> getShxmList(WdpjXmshModel model, User user) {
		
		return dao.getShxmList(model, user);
	}
	
	/**
	 * ��ñ�ͷ(�ҵ�����_��Ŀ���)
	 * @author qlj
	 */
	public List<HashMap<String, String>> getWdpjXmshTop(WdpjXmshModel model,
			User user) {
		
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "nj", "bjmc", "sqsj",
				"shzt" };
		String[] cn = new String[] { "", "ѧ��", "����", "�꼶", "�༶", "����ʱ��", "���״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * ��ȡ������λ�����Ϣ
	 * @author qlj
	 */
	public ArrayList<String[]> getWdpjXmshList(PjpyGeneralForm myForm,
			WdpjXmshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		ArrayList<String[]> list = dao.getWdpjXmshList(myForm,model, user);
		
		return list;
	}

	/**
	 * ���������(�ҵ�����_��Ŀ���)
	 * @author qlj
	 */
	public String createWdpjXmshHTML(SearchRsModel rsModel,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user) {
	
		// IE�汾
		String ie = rsModel.getIe();
		// V4·��
		String stylePath = rsModel.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String disabled = rs[0];
				String pk = rs[1];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:5px\" ");		
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"div_pkValue\" ");
				
				if(!Base.isNull(disabled)){
					html.append(" disabled=\"true\"");
				}
				
				html.append( "value=\"" + pk + "\"/>");
				html.append("</td>");
				
				//------------2013.01.05 begin-----------------
				//------------edit by ΰ�����-----------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");

				html.append("<a href=\"#\" ");
				html.append("onclick=\"showXsxxDetail('" + rs[2] + "')\" ");
				html.append(">");
				html.append("<font color=\"blue\">");
				html.append(rs[2]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");
				
				for (int j = 3; j < rs.length-1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}

				//------------2013.01.05 end-----------------
				
				if("δ���".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("ͨ��".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("��ͨ��".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("�˻�".equalsIgnoreCase(rs[rs.length-1])){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");		
					html.append(">");
					html.append("<p><img src=\""+stylePath+"images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
					html.append("</td>");
				}else if("������".equalsIgnoreCase(rs[rs.length-1])){
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
	 * ������������(�ҵ�����_��Ŀ���)
	 * @author qlj
	 * @throws Exception 
	 */
	public String createKidneyDiv(SearchRsModel rsModel,RequestForm rForm,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user) throws Exception {
		
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(jbszModel);
		
		StringBuilder html=new StringBuilder();
		
		String[]qtzd=rForm.getQtzd();
		
		String[]qtzdz=rForm.getQtzdz();
		
		for(int i=0;i<qtzd.length;i++){
			
			html.append(" <input type=\"hidden\" name=\"hid_"+qtzd[i]+"\" id =\"hid_"+qtzd[i]+"\" value=\""+qtzdz[i]+"\"  >");
			
		}

		boolean checkShkz=pjtjService.checkShkz(model.getXmdm());
		
		html.append(" <input type=\"hidden\" name=\"shkz\" id =\"shkz\" value=\""+checkShkz+"\"  >");
		return html.toString();
		
	}

	// ===========================������Ŀ��� ����� end =============================
	
	
	// ===========================������λ��Ϣ begin===============================
	/**
	 * �����Ŀ��˸�λ
	 * 
	 * @author qlj
	 */
	public List<HashMap<String,String>> getSpgwList(WdpjXmshModel model,User user) {

		return dao.getSpgwList(model,user);
	}
	
	/**
	 * ��ʾ���û���λ�л�ģʽ����
	 * @author qlj
	 * @throws IOException 
	 */
	public void showShgwDiv(WdpjXmshModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
		
		List<HashMap<String,String>>spgwList=dao.getSpgwList(model, user);
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��˸�λѡ��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("��λѡ��");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");
		
		html.append(" <input type=\"hidden\" name=\"text_xmdm\" id=\"text_xmdm\" value=\""+model.getXmdm()+"\" /> ");
		for(int i=0;i<spgwList.size();i++){
			
			HashMap<String,String>spgwMap=spgwList.get(i);
			html.append(" <input type=\"radio\" name=\"spgw\" ");
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
		html.append("ȷ ��");
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
	 * �����Ŀ��˸�λ
	 * 
	 * @author qlj
	 */
	public boolean checkFirstSpgw(WdpjXmshModel model, User user) {
		
		// ��ȡ�ϼ���˸�λ
		HashMap<String, String> higherUp = dao.getHigherUpSpMap(model, user);

		boolean bool = true;

		if (!(higherUp != null && higherUp.size() > 0)) {
			
			bool = false;
			
		}
		// true:�����ϼ�
		// false:�������ϼ�
		// �������ϼ�����򱾼����Ϊ��һ����˸�λ
		return bool;
	}
	// ===========================������λ��Ϣ end===============================	
	
	
	// ===========================������Ŀ�����ϸ begin===============================
	public HashMap<String, Object> defaultWdpjXmsh(WdpjXmshModel model, User user) throws Exception {

		HashMap<String,Object>rs=new HashMap<String,Object>();
		
		WdpjLssbService xmsbService=new WdpjLssbService();
		
		PjpyZhcpService zhcpService=new PjpyZhcpService();
		
		String xmdm=model.getXmdm();
		
		String xh=model.getXh()[0];
		
		// ��������ѧ��������Ϣ��ѧ��������Ϣ��
		rs.putAll(dao.getXmsqInfo(model, user));
		// ������Ŀ�����Ϣ
		rs.put("xmshInfo",dao.getXmshInfo(model, user));
		// ѧ���γ̳ɼ�
		rs.put("kccjInfo",xmsbService.getXscjList(xh));
		// ѧ���۲�ɼ�
		rs.put("zccjInfo",zhcpService.getBczcInfo(xh));
		return rs;
	}
	// ===========================������Ŀ�����ϸ end===============================

	
	// ===========================���״̬�޸� begin===============================
	public boolean updateShzt(WdpjXmshModel model, HttpServletRequest request, User user) throws Exception {

		getModelValue(model, request);
		
		String shzt=model.getShzt();
		
		dao.resultShzt(model, user);
		
		boolean flag=dao.updateShzt(model, user);
		
		if("th".equalsIgnoreCase(shzt) && flag){
			
			 flag=dao.updateThzt(model, user);
		}
		
		flag=dao.updateSqjg(model, user);
		
		return  flag;
	}
	// ===========================���״̬�޸� end===============================	
	
	
	
}
