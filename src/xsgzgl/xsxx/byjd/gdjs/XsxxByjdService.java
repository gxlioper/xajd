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
	 * �߼���ѯ��ʽ��ȡ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getByjdList(BasicModel model) throws Exception{
		
		return dao.getByjdList(model);
	}

	/**
	 * ��ȡ��ͷ
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(BasicModel model,User user){
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao=DAO.getInstance();
		
		String[]en=new String[]{"","xh","xm","nj","xymc","zymc","bjmc","sfyjd"};
		
		String[]cn=new String[]{"","ѧ��","����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","�Ƿ��Ѽ���"};
		
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ���������(�ۺϲ���_�۲���Ϣ)
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
				
				
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					
					if(j==rs.length-1 && rs[j].equalsIgnoreCase("��ϸ")){
					
						html.append("<a href=\"#\" onclick=\"showDetailDiv('"+rs[0]+"');return false;\" ><font color=\"blue\">��ϸ</font></a>");
					
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
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ�����޸�
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
			
		return dao.updateInfo(model);
	} 
	
	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model,XsxxByjdForm myForm){
		
		//�鿴ѧ����ҵ������Ϣ
		HashMap<String,String>byjdDetail=getByjdDetail(myForm);
		
		boolean result=false;
		
		//�жϼ�¼�Ƿ����
		if(byjdDetail!=null && byjdDetail.size()>0){
			//�����޸�
			result=updateInfo(model);
		
		}else{
			//����������
			result=dao.saveInfo(model);
		}
		
		return result;
	} 
	
	/**
	 * ɾ��
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getByjdDetail(XsxxByjdForm myForm){
		
		return dao.getByjdDetail(myForm);
		
	}
	
	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @author qlj
	 * 
	 * @throws IOException
	 */
	public void showDetailDiv(XsxxByjdForm myForm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// �ֶ��б�
		HashMap<String, String> byjdDetail = dao.getByjdDetail(myForm);

		// ��ҵ����
		String byjd = Base.isNull(byjdDetail.get("byjd"))?"":byjdDetail.get("byjd");
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��Ϣ�޸�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("��ҵ����");
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
	 * ��ʾ�ֶ�����Div
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
		html.append("<span>��Ϣ�޸�</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='30%'>");
		html.append("��ҵ����<br/><font color=\"red\">(������250��)</font>");
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
