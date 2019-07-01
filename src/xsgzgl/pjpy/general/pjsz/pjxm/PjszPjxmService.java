package xsgzgl.pjpy.general.pjsz.pjxm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ŀ_Service��
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

public class PjszPjxmService extends CommService implements PjszPjxmInterface {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	PjszPjxmDAO dao = new PjszPjxmDAO();

	/**
	 * ��ñ�ͷ�ļ�(��������_������Ŀ)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPjszPjxmTop(PjszPjxmModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xmmc", "xmlx", "xmxz","sfysq", "tjsz",
				"rskz", "jdkz", "sqzt", "shzt" };
		String[] cn = new String[] { "", "��Ŀ����", "��Ŀ����", "��Ŀ����","�Ƿ�������", "��������",
				"��������", "�������", "����״̬", "���״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����(��������_������Ŀ)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getPjszPjxmList(PjpyGeneralForm myForm,
			PjszPjxmModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszPjxmList(myForm, user);
	}

	/**
	 * ���������(��������_������Ŀ)
	 * 
	 * @author ΰ�����
	 */
	public String createPjszPjxmHTML(SearchRsModel rsModel,
			PjszPjxmModel model, ArrayList<String[]> rsArrList, User user) {
	
		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					
					if(j!=10){
						html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
						
	//					if(!"δ����".equalsIgnoreCase(pjbjmc)){
	//						html.append("bgcolor=\"#CCFFFF\"");
	//					}
						
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						
						if (j==1) {
							html.append("title=\""+rs[10]+"\"");
						} 
						
						html.append(">");
						
							
						if(j>4){
							html.append("<a href=\"#\"  onclick=\"");
							switch(j){
								case 5: html.append("showXmszPjtj('"+pk+"')");break; 
								case 6: html.append("showXmszRssz('"+pk+"')");break; 
								case 7: html.append("showXmszXmjd('"+pk+"')");break; 
								case 8: html.append("showXmszSjsz('"+pk+"')");break; 
								case 9: html.append("showXmszSjsz('"+pk+"')");break; 
							}
							html.append("\" >");
							html.append("<font ");
							if ("δ����".equalsIgnoreCase(rs[j])
									|| "�ر�����".equalsIgnoreCase(rs[j])
									|| "�ر����".equalsIgnoreCase(rs[j])) {
								html.append("color=\"red\" ");
							} else if ("������".equalsIgnoreCase(rs[j])
									|| "��������".equalsIgnoreCase(rs[j])
									|| "�������".equalsIgnoreCase(rs[j])) {
								html.append("color=\"green\" ");
							}else{
								html.append("color=\"black\" ");
							}
							html.append(">");
							html.append(rs[j]);
							html.append("</font>");
							html.append("</a>");
						}else {
						
							html.append(rs[j]);
						}
						html.append("</td>");
					}
				}
				
				html.append("</tr>");
			}
		}

		return html.toString();
	
	}
	
	/**
	 * ��ʼ��������Ŀ����
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void defaultPjxmSetting(PjszPjxmModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
	
		//��������
		String step = model.getStep();
		StringBuilder html = new StringBuilder();
		
		if (Base.isNull(step)) {// ������������
			html.append(getPjjbszHTML(model, user));
		} else if ("shlcsz".equalsIgnoreCase(step)) {// �����������
			html.append(getShlcszHTML(model, user));
		} else if ("rskzsz".equalsIgnoreCase(step)) {// ������������
			html.append(getRskzszHTML(model, user));
		}
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * ��ʼ��������Ŀ����
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	public void defaultPjxmUpdate(PjszPjxmModel model, User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
	
		StringBuilder html = new StringBuilder();
		
		html.append(getPjxmUpdateHTML(model, user));
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * ���������������HTML
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	private String getPjjbszHTML(PjszPjxmModel model, User user) {

		// ��Ŀ�����б�
		List<HashMap<String, String>> xmxzList = getWhList("xg_pjpy_xmxzb",
				"xzdm", "xzmc", "", "", "", false);

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:99%;height:310px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"99%\" border=\"0\" class=\"formlist\">");
		// -------------------------��ͷ---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>������Ŀ����</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		// -------------------------����---------------------------
		html.append("<tbody>");
		// ��Ŀ����
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("<font color=\"red\">*</font>");
		html.append("��Ŀ����");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<input type=\"text\" name=\"xmmc\"");
		html.append("style=\"width:95%\" onblur=\"checkXmmc(this.value)\" ");
		html.append("id=\"xmmc\" maxlength=\"25\"/>");
		html.append("</td>");
		html.append("</tr>");
		// ��Ŀ����
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("��Ŀ����");
		html.append("</th>");
		html.append("<td width=\"25%\">");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_01\" value=\"01\"");
		html.append("checked=\"checked\"");
		html.append("/>");
		html.append("��ѧ��");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_02\" value=\"02\"");
		html.append("/>");
		html.append("�����ƺ�");
		html.append("</td>");
		// ���뷽ʽ
		html.append("<th width=\"20%\">");
		html.append("���뷽ʽ");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_xssq\" value=\"xssq\"");
		html.append("checked=\"checked\"");
		html.append("/>");
		html.append("ѧ������");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_lssb\" value=\"lssb\"");
		html.append("/>");
		html.append("��ʦ�ϱ�");
		html.append("</td>");
		html.append("</tr>");
		// ��Ŀ����
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("��Ŀ����");
		html.append("</th>");
		html.append("<td width=\"\"");
		html.append(">");
		html.append("<select id=\"xmxz\">");
		if (xmxzList != null && xmxzList.size() > 0) {
			for (int i = 0; i < xmxzList.size(); i++) {
				String xmxzdm = xmxzList.get(i).get("dm");
				String xmxzmc = xmxzList.get(i).get("mc");
				html.append("<option value=\"" + xmxzdm + "\">");
				if (xmxzmc.length() < 6) {
					html.append(xmxzmc);
				} else {
					html.append(xmxzmc.substring(0, 6) + "...");
				}
				html.append("</option>");
			}
		}
		html.append("</select>");
		html.append("</td>");
		// ��Ŀ���
		html.append("<th width=\"\">");
		html.append("��Ŀ���");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" name=\"xmje\" id=\"xmje\" ");
		html.append("onkeydown=\"return onlyNum(this,5)\" ");
		html.append("onmousedown=\"return onlyNum(this,5)\" ");
		html.append("maxlength=\"5\"");
		html.append("style=\"width:50px;ime-mode:disabled\" ");
		html.append("/>(Ԫ)");
		html.append("</td>");
		html.append("</tr>");	
		// ��ʾ˳��
//		html.append("<tr>");
//		html.append("<th width=\"\">");
//		html.append("��ʾ˳��");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"text\" name=\"xssx\" id=\"xssx\" ");
//		html.append("onkeydown=\"return onlyNum(this,3)\" ");
//		html.append("onmousedown=\"return onlyNum(this,3)\" ");
//		html.append("maxlength=\"3\"");
//		html.append("style=\"width:50px;ime-mode:disabled\" ");
//		html.append("/>");
//		html.append("</td>");
		// �Ƿ�����
//		html.append("<th>");
//		html.append("�Ƿ�����");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_yes\" value=\"yes\"");
//		html.append("checked=\"checked\"");
//		html.append("/>");
//		html.append("��");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_no\" value=\"no\"");
//		html.append("/>");
//		html.append("��");
//		html.append("</td>");
//		html.append("</tr>");	
		// �Ƿ���Ҫ���
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("�Ƿ���Ҫ���");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_yes\" value=\"yes\"");
		html.append("checked=\"checked\" onclick=\"checkBtn();\" ");
		html.append("/>");
		html.append("��");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_no\" value=\"no\"");
		html.append("onclick=\"checkBtn();\"/>");
		html.append("��");
		html.append("</td>");
		// �Ƿ���Ҫ��������
		html.append("<th width=\"\">");
		html.append("�Ƿ���Ҫ��������");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_yes\" value=\"yes\"");
		html.append("checked=\"checked\" onclick=\"checkBtn();\" ");
		html.append("/>");
		html.append("��");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_no\" value=\"no\"");
		html.append("onclick=\"checkBtn();\" />");
		html.append("��");
		html.append("</td>");
		html.append("</tr>");
		// ��Ŀ˵��
		html.append("<tr>");
		html.append("<th>");
		html.append("��Ŀ˵��");
		html.append("<br/>");
		html.append("<font color=\"blue\">(����¼��500��)</font>");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<textarea id=\"xmsm\" rows=\"5\"");
		html.append("onblur=\"chLeng(this,500)\"");
		html.append("  style=\"word-break:break-all;width:95%\" ");
		html.append("/>");
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------���� end---------------------------
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------��ť---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"��һ��\" id=\"btn_next\" onclick=\"nextStep('');return false;\" disabled=\"true\">��һ��</button>");
		html.append("<button type=\"button\"  name=\"����\" id=\"btn_bc\" onclick=\"checkSavePjxm();return false;\" disabled=\"true\" style=\"display:none\">�� ��</button>");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"Close();return false;\">�� ��</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------��ť end---------------------------
		
		return html.toString();
	}
	
	/**
	 * ��������������HTML
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	private String getShlcszHTML(PjszPjxmModel model, User user) {

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:310px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");

		// -------------------------�����ѡ��---------------------------
		html.append("<tbody>");
		
		// �����ѡ��
		html.append("<tr>");
		html.append("<td width=\"\">");
		
		html.append("<table width=\"100%\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th>");
		html.append("<span>��ѡ���������Ŀ���������</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// �����������б�
		List<HashMap<String, String>> shlcList = dao.getShlcList();
		if (shlcList != null && shlcList.size() > 0) {
			for (int i = 0; i < shlcList.size(); i++) {
				html.append("<tr>");
				html.append("<td>");

				String lcid = shlcList.get(i).get("lcid");
				String lcmc = shlcList.get(i).get("lcmc");
				String gzgw = shlcList.get(i).get("gzgw");

				html.append("<input type=\"radio\" name=\"lcid\" id=\"lcid_"
						+ lcid + "\" ");
				html.append("value=\"" + lcid + "\" ");
				html.append("onclick=\"clickShlc('" + lcid + "')\" ");
				html.append("/>");
				html.append(lcmc);
				html.append("��");
				String[] arr_gw = gzgw.split(",");
				if (arr_gw != null && arr_gw.length > 0) {
					for (int j = 0; j < arr_gw.length; j++) {
						if (j != 0) {
							html.append("-->");
						}
						html.append(arr_gw[j]);
					}
				}

				html.append("</td>");
				html.append("</tr>");
			}
		}else{
			html.append("<tr>");
			html.append("<td>");
			html.append("ϵͳ��δ����������ģ�鶨���������");
			html.append("<br/>");
			html.append("��ǰ��");
			html.append("<font color=\"blue\">");
			html.append("ϵͳά�� - ��������ά�� - ��������");
			html.append("</font>");
			html.append("��������");
			html.append("<br/>");
			html.append("ע������û����Ӧ��Ȩ�ޣ�����ϵ����Ա");
			html.append("</td>");
			html.append("<tr>");
		}
		html.append("</table>");

		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		// -------------------------�����ѡ�� end---------------------------
		html.append("</table>");
		
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");	
		// -------------------------�������ѡ��---------------------------
		html.append("<tbody>");
		
		// �����ѡ��
		html.append("<tr>");
		html.append("<td width=\"\">");
		html.append("<div id=\"div_shgw\">");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		// -------------------------�������ѡ�� end---------------------------
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------��ť---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"��һ��\" onclick=\"previousStep('shlcsz');return false;\">��һ��</button>");
		html.append("<button type=\"button\"  name=\"��һ��\" id=\"btn_next\" onclick=\"nextStep('shlcsz');return false;\">��һ��</button>");
		html.append("<button type=\"button\"  name=\"����\" id=\"btn_bc\" onclick=\"checkSavePjxm();return false;\" style=\"display:none\">�� ��</button>");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"Close();return false;\">�� ��</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------��ť end---------------------------
		
		return html.toString();
	}

	/**
	 * ���������������HTML
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	private String getRskzszHTML(PjszPjxmModel model, User user) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		//�Ƿ���Ҫ������
		String cpz = jbszModel.getCpz();
			
		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:310px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");

		// -------------------------��ͷ---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>�������Ʒ�Χ</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		html.append("<tbody>");
		
		// �������Ƽ���ѡ��
		html.append("<tr>");
		
		if("yes".equalsIgnoreCase(cpz)){
			html.append("<td width=\"\">");
			html.append("<table width=\"100%\">");
			html.append("<tr>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("�꼶����");
			html.append("</td>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append(Base.YXPZXY_KEY);
			html.append("����");
			html.append("</td>");
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("�꼶+").append(Base.YXPZXY_KEY).append("����");
			html.append("</td>");
			html.append("</tr>");
			html.append("<tr>");	
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("�꼶+רҵ����");
			html.append("</td>");
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled = \"true\" ");
			html.append("/>");
			html.append("�༶����");
			html.append("</td>");
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_cpz\" value=\"cpz\" checked=\"true\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("����������");
			html.append("</td>");
		}else{
			html.append("<td width=\"\">");
			html.append("<table width=\"100%\">");
			html.append("<tr>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" ");
			html.append("/>");
			html.append("�꼶����");
			html.append("</td>");	
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append(Base.YXPZXY_KEY);
			html.append("����");
			html.append("</td>");
			html.append("<td width=\"33%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("�꼶+").append(Base.YXPZXY_KEY).append("����");
			html.append("</td>");
			html.append("</tr>");
			html.append("<tr>");	
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("�꼶+רҵ����");
			html.append("</td>");
			html.append("<td>");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\"  checked=\"true\"  ");
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append("/>");
			html.append("�༶����");
			html.append("</td>");
			html.append("<td>");
		
			html.append("</td>");
		}
		
		html.append("</tr>");
		html.append("</table>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		
		// -------------------------��ͷ---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>������Ⱥѡ��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		//������Ⱥ�б�
		List<HashMap<String,String>> tsrqList = jbszModel.getTsrqList();
		
		html.append("<tbody>");
		
		// �������Ƽ���ѡ��
		html.append("<tr>");
		html.append("<td width=\"\">");
		
		html.append("<table width=\"100%\">");
		html.append("<tr>");	
		for (int i = 0; i < tsrqList.size(); i++) {
			HashMap<String, String> tsrq = tsrqList.get(i);
			html.append("<td width=\"33%\">");
			if(!Base.isNull(tsrq.get("tsrqdm"))){
				html.append("<input type=\"radio\" name=\"tsrq\" id=\"tsrq_"
						+ tsrq.get("tsrqdm") + "\" ");
				html.append("value=\"" + tsrq.get("tsrqdm") + "\" ");
				html.append("onclick=\"$('hidden_tsrq').value=this.value\"");
				html.append("/>");
				html.append(tsrq.get("tsrqmc"));
			}
			html.append("</td>");
		}
		html.append("</tr>");
		html.append("</table>");
		
//		for(int i=0;i<tsrqList.size();i++){
//			HashMap<String,String> tsrq = tsrqList.get(i);
//			html.append("<input type=\"radio\" name=\"tsrq\" id=\"tsrq_"+tsrq.get("tsrqdm")+"\" ");
//			html.append("value=\""+tsrq.get("tsrqdm")+"\" ");
//			html.append("onclick=\"$('hidden_tsrq').value=this.value\"");
//			html.append("/>");
//			html.append(tsrq.get("tsrqmc"));
//			html.append("<br/><br/>");
//		}
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------��ť---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"��һ��\" onclick=\"previousStep('rskzsz');return false;\">��һ��</button>");
		html.append("<button type=\"button\"  name=\"����\" onclick=\"checkSavePjxm();return false;\">�� ��</button>");
		html.append("<button type=\"button\"  name=\"�ر�\" onclick=\"Close();return false;\">�� ��</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------��ť end---------------------------
		
		return html.toString();
	}
	
	/**
	 * ��ʼ��������̸�λ��Ϣ
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void defaultShlcGwxx(PjszPjxmModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");
	
		// ����ID
		String lcid = model.getLcid();
		// ��������
		String rssz = model.getRssz();
		
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		// -------------------------��ͷ---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		//html.append("<th colspan=\"4\">	");
		//html.append("<span>������Ŀ����</span>");
		//html.append("</th>");
		
		html.append("<th>");
		html.append("��������");
		html.append("</th>");
		html.append("<th>");
		html.append("��������");
		html.append("</th>");
		if ("yes".equalsIgnoreCase(rssz)) {
			html.append("<th>");
			html.append("��������");
			html.append("</th>");
		}
		html.append("<th>");
		html.append("��ÿ���");
		html.append("</th>");
		
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		// -------------------------����---------------------------
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("����/�ϱ�");
		html.append("</th>");
		//��������
		html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
		html.append("<input type=\"radio\" ");
		html.append("checked=\"checked\" ");
		html.append("/>");
		html.append("</td>");
		
		if ("yes".equalsIgnoreCase(rssz)) {
			// ��������
			html.append("<td width=\"\" bgcolor=\"#FFF5EE\">");
			html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_sqsb\" ");
			html.append("value=\"sqsb\" ");
			html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
			html.append("/>");
			html.append("</td>");
		}
		
		//��ÿ���
		html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
		html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_sqsb\" ");
		html.append("value=\"sqsb\" ");
		html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
		html.append("/>");
		html.append("</td>");
		
		html.append("</tr>");
		
		List<HashMap<String, String>> gwxxList = dao.getGwxxList(lcid);
		if (gwxxList != null && gwxxList.size() > 0) {
			for (int i = 0; i < gwxxList.size(); i++) {
				HashMap<String, String> map = gwxxList.get(i);
				String gwdm = map.get("gwdm");
				String gwmc = map.get("gwmc");
				
				html.append("<tr>");
				html.append("<th width=\"25%\">");
				html.append(gwmc);
				html.append("</th>");
				
				//��������
				html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
				html.append("<input type=\"radio\" ");
				html.append("disabled=\"true\" ");
				html.append("/>");
				html.append("</td>");
				
				if ("yes".equalsIgnoreCase(rssz)) {
					//��������
					html.append("<td width=\"\" bgcolor=\"#FFF5EE\">");
					html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_"+gwdm+"\" ");
					html.append("value=\""+gwdm+"\" ");
					html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
					if (i == gwxxList.size() - 1) {
						html.append("checked=\"checked\" ");
					}
					html.append("/>");
					html.append("</td>");
				}
				
				//��ÿ���
				html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
				html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_"+gwdm+"\" ");
				html.append("value=\""+gwdm+"\" ");
				html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
				if (i == gwxxList.size() - 1) {
					html.append("checked=\"checked\" ");
				}
				html.append("/>");
				html.append("</td>");
			}
		} else {

		}
		
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------���� end---------------------------
		html.append("</table>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * ��ʼ��������̸�λ��Ϣ
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public String defaultShlcGwxx(PjszPjxmModel model,HashMap<String,String>pjxmMap, User user) throws IOException {

		// ����ID
		String lcid = model.getLcid();
		// ��������
		String rssz = pjxmMap.get("rssz");
		// �ж���Ŀ�Ƿ�������������
		String checkRssz=pjxmMap.get("checkRssz");
		// �ж�
		String checkXssq=pjxmMap.get("checkXssq");
		
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		// -------------------------��ͷ---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		//html.append("<th colspan=\"4\">	");
		//html.append("<span>������Ŀ����</span>");
		//html.append("</th>");
		
		html.append("<th>");
		html.append("��������");
		html.append("</th>");
		html.append("<th>");
		html.append("��������");
		html.append("</th>");
		
		
		html.append("<th name=\"rskzArr\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(" >��������");
		html.append("</th>");
		
		html.append("<th>");
		html.append("��ÿ���");
		html.append("</th>");
		
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		// -------------------------����---------------------------
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("����/�ϱ�");
		html.append("</th>");
		//��������
		html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
		html.append("<input type=\"radio\" ");
		html.append("checked=\"checked\" ");
		html.append("/>");
		//html.append("��������");
		html.append("</td>");
		
		String rskz=pjxmMap.get("rskz");
		String jdkz=pjxmMap.get("jdkz");
		
		//��������
		
		html.append("<td width=\"\" bgcolor=\"#FFF5EE\" name=\"rskzArr\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");
		html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_sqsb\" ");
		html.append("value=\"sqsb\" ");
		html.append((Boolean.parseBoolean(checkXssq) || Boolean
				.parseBoolean(checkRssz)) ? "" : " disabled ");
		html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
		if ("sqsb".equals(rskz)) {
			html.append("checked=\"checked\" ");
		}
		html.append("/>");
		html.append("</td>");
		
		
		//��ÿ���
		html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
		html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_sqsb\" ");
		html.append("value=\"sqsb\" ");
		html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
		if ("sqsb".equals(jdkz)) {
			html.append("checked=\"checked\" ");
		}
		html.append(Boolean.parseBoolean(checkXssq)? "": " disabled " );
		html.append("/>");
		//html.append("��ÿ���");
		html.append("</td>");
		
//		html.append("<td width=\"\" colspan=\"3\">");
//		// ��������
//		if ("yes".equalsIgnoreCase(rssz)) {
//			html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_sqsb\" ");
//			html.append("value=\"sqsb\" ");
//			html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
//			html.append("/>");
//			html.append("��������");
//		}
//		
//		html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_sqsb\" ");
//		html.append("value=\"sqsb\" ");
//		html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
//		html.append("/>");
//		html.append("��ÿ���");
//		
//		html.append("<input type=\"radio\" name=\"xmsy\" id=\"xmsy_sqsb\" ");
//		html.append("value=\"sqsb\" ");
//		html.append("onclick=\"$('hidden_xmsy').value=this.value\" ");
//		html.append("/>");
//		html.append("��Ŀ˳��");
//		html.append("</td>");
		html.append("</tr>");
		
		
		List<HashMap<String, String>> gwxxList = dao.getGwxxList(lcid);
		if (gwxxList != null && gwxxList.size() > 0) {
			for (int i = 0; i < gwxxList.size(); i++) {
				HashMap<String, String> map = gwxxList.get(i);
				String gwdm = map.get("gwdm");
				String gwmc = map.get("gwmc");
				
				html.append("<tr>");
				html.append("<th width=\"25%\">");
				html.append(gwmc);
				html.append("</th>");
				
				//��������
				html.append("<td width=\"\" bgcolor=\"#FFFAF0\">");
				html.append("<input type=\"radio\" ");
				html.append("disabled=\"true\" ");
				html.append("/>");
				//html.append("��������");
				html.append("</td>");
				
				//��������
				
				html.append("<td width=\"\" bgcolor=\"#FFF5EE\" name=\"rskzArr\" ");
				if("no".equalsIgnoreCase(rssz)){
					html.append(" style=\"display:none\" ");
				}
				html.append(">");
				html.append("<input type=\"radio\" name=\"rskz\" id=\"rskz_"+gwdm+"\" ");
				html.append("value=\""+gwdm+"\" ");
				html.append("onclick=\"$('hidden_rskz').value=this.value\" ");
				if (gwdm.equals(rskz)) {
					html.append("checked=\"checked\" ");
				}
				html.append((Boolean.parseBoolean(checkXssq) || Boolean
						.parseBoolean(checkRssz)) ? "" : " disabled ");
				html.append("/>");
				//html.append("��������");
				html.append("</td>");
				
				
				//��ÿ���
				html.append("<td width=\"\" bgcolor=\"#FFFFE0\">");
				html.append("<input type=\"radio\" name=\"jdkz\" id=\"jdkz_"+gwdm+"\" ");
				html.append("value=\""+gwdm+"\" ");
				html.append("onclick=\"$('hidden_jdkz').value=this.value\" ");
				if (gwdm.equals(jdkz)) {
					html.append("checked=\"checked\" ");
				}
				html.append(Boolean.parseBoolean(checkXssq)? "" : " disabled " );
				html.append("/>");
				//html.append("��ÿ���");
				html.append("</td>");
			}
		} else {

		}
		
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------���� end---------------------------
		html.append("</table>");

		return html.toString();
	}

		/**
	 * ����������Ŀ
	 * 
	 * @author ΰ�����
	 */
	public Boolean savePjxm(PjszPjxmModel model, User user) {

		boolean flag = false;

		String lcid = model.getLcid();// ����ID

		String rssz = model.getRssz();// ��������

		String xmmc = model.getXmmc();// ��Ŀ����

		String xmlx = model.getXmlx();// ��Ŀ����

		String xmxz = model.getXmxz();// ��Ŀ����

		String sqfs = model.getSqfs();// ���뷽ʽ

		String xmje = model.getXmje();// ��Ŀ���

		String xssx = model.getXssx();// ��ʾ˳��

		String sfsh = model.getSfsh();// �Ƿ����

		String sfqy = model.getSfqy();// �Ƿ�����
		
		String xmsm = model.getXmsm();// ��Ŀ˵��

		String rskz = model.getRskz();// ��������

		String jdkz = model.getJdkz();// ��ÿ���

		String xmsy = model.getXmsy();// ��Ŀ˳��

		String kzfw = model.getKzfw();// ���Ʒ�Χ
		
		String tsrq = model.getTsrq();// ������Ⱥ
		
		try {
			flag = dao.insertPjxmwhb(xmmc, xmlx, xmxz, sqfs, xmje, lcid, rssz,
					xssx, sfsh, sfqy, xmsm, rskz, jdkz, xmsy, kzfw, tsrq, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ����������Ŀ
	 * 
	 * @author ΰ�����
	 */
	public Boolean updatePjxm(PjszPjxmModel model, User user) {

		boolean flag = false;

		String lcid = model.getLcid();// ����ID

		String rssz = model.getRssz();// ��������
		
		String xmdm = model.getXmdm();// ��Ŀ����

		String xmmc = model.getXmmc();// ��Ŀ����

		String xmlx = model.getXmlx();// ��Ŀ����

		String xmxz = model.getXmxz();// ��Ŀ����

		String sqfs = model.getSqfs();// ���뷽ʽ

		String xmje = model.getXmje();// ��Ŀ���

		String xssx = model.getXssx();// ��ʾ˳��

		String sfsh = model.getSfsh();// �Ƿ����

		String sfqy = model.getSfqy();// ���뷽ʽ

		String xmsm = model.getXmsm();// ��Ŀ˵��

		String rskz = model.getRskz();// ��������

		String jdkz = model.getJdkz();// ��ÿ���

		String xmsy = model.getXmsy();// ��Ŀ˳��

		String kzfw = model.getKzfw();// ���Ʒ�Χ
		
		String tsrq = model.getTsrq();// ������Ⱥ
		
		if ("no".equalsIgnoreCase(sfsh)) {
			lcid = "";
		}
		
		try {
			flag = dao.updatePjxmwhb(xmdm,xmmc, xmlx, xmxz,
					sqfs, xmje, lcid, rssz, xssx,
					sfsh, sfqy, xmsm, rskz, jdkz,
					xmsy, kzfw, tsrq,  user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ɾ��������Ŀ
	 * 
	 * @author qlj
	 */
	public Boolean deletePjxm(PjszPjxmModel model, User user) {

		boolean flag = false;
		
		String[]pkValue=model.getPkValue();
		try {
			
			flag = dao.deletePjxmwhb(pkValue, user);
		
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ɾ��������Ŀ
	 * 
	 * @author qlj
	 */
	public Boolean checkSfsq(PjszPjxmModel model, User user) {

		boolean flag = false;
		
		String[]pkValue=model.getPkValue();
		try {
			
			flag = dao.deletePjxmwhb(pkValue, user);
		
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ���������Ŀ�����Ϣ(Model)
	 * 
	 * @author ΰ�����
	 */
	public PjszPjxmModel getPjxmModel(String xmdm, User user) {
		
		PjszPjxmModel model = new PjszPjxmModel();
		HashMap<String, String> map = dao.getPjxmInfo(xmdm);
			
		try {
			getModel(model, map);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return model;
	}

	/**
	 * ���������Ŀ�����Ϣ(Map)
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getPjxmMap(String xmdm, User user) {
		HashMap<String, String> map = dao.getPjxmInfo(xmdm);
		return map;
	}

	/**
	 * �����Ŀ����
	 * 
	 * @author ΰ�����
	 */
	public boolean checkXmmc(PjszPjxmModel model) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		String tableName = "xg_pjpy_pjxmwhb";
		String dm = "xmdm";
		String pk = "xmmc||pjxn||pjxq||pjnd";
		String pkValue = xmmc + pjxn + pjxq + pjnd;

		String xmdm = getOneValue(tableName, dm, pk, pkValue);

		boolean flag = Base.isNull(xmdm) ? true : false;

		return flag;
	}

	/**
	 * �����ʦ������Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLssbXmList() {
		List<HashMap<String, String>> list = dao.getLssbXmList();
		return list;
	}
	
	/**
	 * ���������������HTML
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 * @throws IOException
	 */
	private String getPjxmUpdateHTML(PjszPjxmModel model, User user) throws Exception {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String xmdm=model.getXmdm();
		
		HashMap<String,String>pjxmMap=dao.getPjxmInfo(xmdm);
		
		boolean checkRssz=checkRssz(model, user);
		
		boolean checkXssq=checkXssq(model, user);
		
		// ��Ŀ�����б�
		List<HashMap<String, String>> xmxzList = getWhList("xg_pjpy_xmxzb",
				"xzdm", "xzmc", "", "", "", false);

		StringBuilder html = new StringBuilder();

//		html.append("<div style=\"width:100%;height:430px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\">");
		// -------------------------��ͷ---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>������Ŀ����</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		// -------------------------����---------------------------
		html.append("<tbody>");
		// ��Ŀ����
		html.append("<tr>");
		html.append("<th width=\"25%\">");
		html.append("<font color=\"red\">*</font>");
		html.append("��Ŀ����");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<input type=\"text\" name=\"xmmc\"");
		html.append("style=\"width:95%\" onblur=\"checkXmmc(this.value)\" ");
		html.append(" id=\"xmmc\" ");
		html.append(" value=\""+pjxmMap.get("xmmc")+"\"");;
		html.append(" maxlength=\"25\"/>");
		
		html.append("<input type=\"hidden\" name=\"hid_xmmc\"");
		html.append(" id=\"hid_xmmc\" ");
		html.append(" value=\""+pjxmMap.get("xmmc")+"\" />");
		html.append("</td></tr>");
		// ��Ŀ����
		String xmlx=pjxmMap.get("xmlx");
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("��Ŀ����");
		html.append("</th>");
		html.append("<td width=\"25%\">");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_01\" value=\"01\"");
		html.append("01".equalsIgnoreCase(xmlx)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled" );
		html.append("/>");
		html.append("��ѧ��");
		html.append("<input type=\"radio\" name=\"xmlx\" id=\"xmlx_02\" value=\"02\"");
		html.append("02".equalsIgnoreCase(xmlx)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("�����ƺ�");
		html.append("</td>");
		// ���뷽ʽ
		String sqfs=pjxmMap.get("sqfs");
		html.append("<th width=\"20%\">");
		html.append("���뷽ʽ");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_xssq\" value=\"xssq\"");
		html.append("xssq".equalsIgnoreCase(sqfs)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("ѧ������");
		html.append("<input type=\"radio\" name=\"sqfs\" id=\"sqfs_lssb\" value=\"lssb\"");
		html.append("lssb".equalsIgnoreCase(sqfs)? "checked=\"checked\"" : "");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("��ʦ�ϱ�");
		html.append("</td>");
		html.append("</tr>");
		// ��Ŀ����
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("��Ŀ����");
		html.append("</th>");
		html.append("<td width=\"\"");
		html.append(">");
		
		String xmxz=pjxmMap.get("xmxz");
		html.append("<select id=\"xmxz\" ");
		html.append(checkXssq ? "" : "disabled");
		html.append(" >");
		if (xmxzList != null && xmxzList.size() > 0) {
			for (int i = 0; i < xmxzList.size(); i++) {
				String xmxzdm = xmxzList.get(i).get("dm");
				String xmxzmc = xmxzList.get(i).get("mc");
				html.append("<option value=\"" + xmxzdm + "\" ");
				if(xmxz.equalsIgnoreCase(xmxzdm)){
					html.append(" selected=\"selected\" ");
				}
				html.append(">");
				if (xmxzmc.length() < 6) {
					html.append(xmxzmc);
				} else {
					html.append(xmxzmc.substring(0, 6) + "...");
				}
				html.append("</option>");
			}
		}
		html.append("</select>");
		html.append("</td>");
		// ��Ŀ���
		String xmje = Base.isNull(pjxmMap.get("xmje")) ? "" : pjxmMap
				.get("xmje");
		html.append("<th width=\"\">");
		html.append("��Ŀ���");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"text\" name=\"xmje\" id=\"xmje\" ");
		html.append("onkeydown=\"return onlyNum(this,5)\" ");
		html.append("onmousedown=\"return onlyNum(this,5)\" ");
		html.append(checkXssq ? "" : "disabled ");
		html.append("maxlength=\"5\"");
		html.append("style=\"width:50px;ime-mode:disabled\" value=\""+xmje+"\" ");
		html.append("/>(Ԫ)");
		html.append("</td>");
		html.append("</tr>");	
		// ��ʾ˳��
//		html.append("<tr>");
//		html.append("<th width=\"\">");
//		html.append("��ʾ˳��");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"text\" name=\"xssx\" id=\"xssx\" ");
//		html.append("onkeydown=\"return onlyNum(this,3)\" ");
//		html.append("onmousedown=\"return onlyNum(this,3)\" ");
//		html.append("maxlength=\"3\"");
//		html.append("style=\"width:50px;ime-mode:disabled\" ");
//		html.append("/>");
//		html.append("</td>");
		// �Ƿ�����
//		html.append("<th>");
//		html.append("�Ƿ�����");
//		html.append("</th>");
//		html.append("<td width=\"\">");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_yes\" value=\"yes\"");
//		html.append("checked=\"checked\"");
//		html.append("/>");
//		html.append("��");
//		html.append("<input type=\"radio\" name=\"sfqy\" id=\"sfqy_no\" value=\"no\"");
//		html.append("/>");
//		html.append("��");
//		html.append("</td>");
//		html.append("</tr>");
		
		// �Ƿ���Ҫ���
		String sfsh=pjxmMap.get("sfsh");
		html.append("<tr>");
		html.append("<th width=\"\">");
		html.append("�Ƿ���Ҫ���");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_yes\" value=\"yes\"");
		html.append("yes".equalsIgnoreCase(sfsh)? "checked=\"checked\"" : "" );
		html.append(" onclick=\"checkSfsh();\" ");
		html.append(checkXssq ? "" : "disabled");
		html.append("/>");
		html.append("��");
		html.append("<input type=\"radio\" name=\"sfsh\" id=\"sfsh_no\" value=\"no\"");
		html.append("no".equalsIgnoreCase(sfsh)? "checked=\"checked\"" : "" );
		html.append("onclick=\"checkSfsh();\" ");
		html.append(checkXssq ? "" : "disabled");
		html.append(" />��");
		html.append("</td>");
		// �Ƿ���Ҫ��������
		String rssz=pjxmMap.get("rssz");
		html.append("<th width=\"\">");
		html.append("�Ƿ���Ҫ��������");
		html.append("</th>");
		html.append("<td width=\"\">");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_yes\" value=\"yes\"");
		html.append("yes".equalsIgnoreCase(rssz)? "checked=\"checked\"" : "" );
		html.append(" onclick=\"checkRssz();\" ");
		html.append((checkXssq || checkRssz) ? "" : "disabled");
		html.append("/>");
		html.append("��");
		html.append("<input type=\"radio\" name=\"rssz\" id=\"rssz_no\" value=\"no\"");
		html.append("no".equalsIgnoreCase(rssz)? "checked=\"checked\"" : "" );
		html.append("onclick=\"checkRssz();\" ");
		html.append((checkXssq || checkRssz )? "" : "disabled");
		html.append(" />��");
		html.append("</td>");
		html.append("</tr>");
		// ��Ŀ˵��
		String xmsm=Base.isNull(pjxmMap.get("xmsm"))? "" : pjxmMap.get("xmsm");
		html.append("<tr>");
		html.append("<th>");
		html.append("��Ŀ˵��");
		html.append("<br/>");
		html.append("<font color=\"blue\">(����¼��500��)</font>");
		html.append("</th>");
		html.append("<td width=\"\" colspan=\"3\">");
		html.append("<textarea id=\"xmsm\" rows=\"5\"");
		html.append("onblur=\"chLeng(this,500)\"");
		html.append("  style=\"word-break:break-all;width:95%\" ");
		html.append(">");
		html.append(xmsm);
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		// -------------------------���� end---------------------------
		html.append("</table>");
		
	
		// ==============================�����ѡ�� begin=================================
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\" id=\"tab_shlc\" ");
		if("no".equalsIgnoreCase(sfsh)){
			html.append("  style=\"display:none\" ");
		}
		html.append(">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th >	");
		html.append("<span>�������</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		
			html.append("<tr>");
			html.append("<td width=\"\">");
			//�����������б�
			String shlcid=Base.isNull(pjxmMap.get("lcid"))? "" : pjxmMap.get("lcid");
			List<HashMap<String,String>> shlcList = dao.getShlcList();
			if (shlcList != null && shlcList.size() > 0) {
				for (int i = 0; i < shlcList.size(); i++) {
					String lcid = shlcList.get(i).get("lcid");
					String lcmc = shlcList.get(i).get("lcmc");
					String gzgw = shlcList.get(i).get("gzgw");
					
					html.append("<input type=\"radio\" name=\"lcid\" id=\"lcid_"+lcid+"\" ");
					html.append("value=\""+lcid+"\" ");
					html.append(checkXssq ? "" : "disabled ");
					html.append("onclick=\"clickShlc('"+lcid+"')\" ");
					if(lcid.equalsIgnoreCase(shlcid)){
						html.append("checked=\"checked\" ");
					}
					html.append("/>");
					html.append(lcmc);
					html.append("��");
					String[] arr_gw = gzgw.split(",");
					if (arr_gw != null && arr_gw.length > 0) {
						for (int j = 0; j < arr_gw.length; j++) {
							if (j != 0) {
								html.append("-->");
							}
							html.append(arr_gw[j]);
						}
					}	
					html.append("<br/>");
				}
			}
			html.append("</select>");
			html.append("</td>");
			html.append("</tr>");
			
			html.append("</tbody>");
			html.append("</table>");
		
		// ==============================�����ѡ�� end=================================
		
		
			
		// ==============================�������ѡ�� begin==============================
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\"  id=\"tab_shgw\" ");
		if("no".equalsIgnoreCase(sfsh)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");	
	
		html.append("<tbody>");
		
		// �����ѡ��
		html.append("<tr>");
		html.append("<td width=\"\">");
		html.append("<div id=\"div_shgw\">");
		
		model.setLcid(pjxmMap.get("lcid"));
		try {
			
			pjxmMap.put("checkRssz", String.valueOf(checkRssz));
			pjxmMap.put("checkXssq", String.valueOf(checkXssq));
			
			html.append(defaultShlcGwxx(model,pjxmMap, user));
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
		
		// ==============================�������ѡ�� end==============================
			
		String cpz = jbszModel.getCpz();
		
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\" id=\"tab_rssz\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");	
		// ==============================�������Ʒ�Χ begin==============================	
		
		// -------------------------��ͷ---------------------------
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"5\">	");
		html.append("<span>�������Ʒ�Χ</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		html.append("<tbody>");
		
		// �������Ƽ���ѡ��
		String kzfw=Base.isNull(pjxmMap.get("kzfw")) ?  "" : pjxmMap.get("kzfw");
		html.append("<tr>");
		// ----------------��Ҫ��������� begin -----------------
		if("yes".equalsIgnoreCase(cpz)){
			html.append("<td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("nj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�꼶����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("xy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append(Base.YXPZXY_KEY);
			html.append("����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("njxy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�꼶+").append(Base.YXPZXY_KEY).append("����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("njzy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�꼶+רҵ����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\" ");
			html.append("bj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\" disabled ");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�༶����");
			html.append("</td></tr>");
			html.append("<tr><td colspan=\"5\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_cpz\" value=\"cpz\"");
			html.append("cpz".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("����������");
			html.append("<br/>");

			html.append("</td>");
		}else{
			
			html.append("<td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_nj\" value=\"nj\" ");
			html.append("nj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�꼶����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_xy\" value=\"xy\" ");
			html.append("xy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			html.append(Base.YXPZXY_KEY);
			html.append("����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njxy\" value=\"njxy\"");
			html.append("njxy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�꼶+").append(Base.YXPZXY_KEY).append("����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_njzy\" value=\"njzy\"");
			html.append("njzy".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�꼶+רҵ����");
			html.append("</td><td width=\"20%\">");
			html.append("<input type=\"radio\" name=\"kzfw\" id=\"kzfw_bj\" value=\"bj\" ");
			html.append("bj".equalsIgnoreCase(kzfw)? "checked=\"checked\"" : "" );
			html.append("onclick=\"$('hidden_kzfw').value=this.value\"");
			html.append(checkXssq ? "" : "disabled");
			html.append(checkRssz ? "" : "disabled");
			html.append("/>");
			html.append("�༶����");
			html.append("</td>");
		}
		html.append("</tr>");
		
		html.append("</tbody>");
		html.append("</table>");
		// ==============================�������Ʒ�Χ end==============================	
		
		
		// -------------------------��ͷ---------------------------
		html.append("<table width=\"95%\" border=\"0\" class=\"formlist\" ");
		if("no".equalsIgnoreCase(rssz)){
			html.append(" style=\"display:none\" ");
		}
		html.append(">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"5\">	");
		html.append("<span>������Ⱥѡ��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		// -------------------------��ͷ end---------------------------
		
		//������Ⱥ�б�
		List<HashMap<String,String>> tsrqList = jbszModel.getTsrqList();
		
		html.append("<tbody>");
		
		// �������Ƽ���ѡ��
		html.append("<tr style=\"height:22px\">");
		
		String tsrqxx=Base.isNull(pjxmMap.get("tsrq")) ?  "" : pjxmMap.get("tsrq");
		for(int i=0;i<tsrqList.size();i++){
			
			html.append("<td width=\"20%\">");
			HashMap<String,String> tsrq = tsrqList.get(i);
			if(!Base.isNull(tsrq.get("tsrqdm"))){
				html.append("<input type=\"radio\" name=\"tsrq\" id=\"tsrq_"+tsrq.get("tsrqdm")+"\" ");
				html.append("value=\""+tsrq.get("tsrqdm")+"\" ");
				html.append(tsrq.get("tsrqdm").equalsIgnoreCase(tsrqxx)? "checked=\"checked\"" : "" );
				html.append(checkXssq ? "" : " disabled ");
				html.append("onclick=\"$('hidden_tsrq').value=this.value\"");
			html.append("/>");
			}
			html.append(tsrq.get("tsrqmc"));
			html.append("</td>");
			if((i+1)%5==0){
				html.append("</tr><tr style=\"height:22px\">");
			}
		}
		
		if (tsrqList.size() % 5 != 0) {
			for(int i=0;i<5-tsrqList.size() % 5;i++){
				html.append("<td>&nbsp;");
				html.append("</td>");
			}
			html.append("</tr>");
		}
		
		html.append("</tbody>");
		html.append("</table>");

		
		return html.toString();
	}
	
	/**
	 * �ж��Ƿ�������������
	 * @author qlj
	 * flase: �������� true: ������
	 * @throws Exception
	 */
	public boolean checkRssz(PjszPjxmModel model, User user) throws Exception {
		
		String xmdm=model.getXmdm();
		
		List<HashMap<String,String>>rsszList=new ArrayList<HashMap<String,String>>();
		
		rsszList=dao.getRsszList(xmdm, user);
		
		if(rsszList!=null && rsszList.size()>0){
			
			return false;
		
		}
		
		return true;
	}
	
	/**
	 * �ж��Ƿ�������������
	 * @author qlj
	 * flase: �������� true: ������
	 * @throws Exception
	 */
	public String showRsszMessage(PjszPjxmModel model, User user) throws Exception {
		
		String[]pkValue=model.getPkValue();
		
		List<HashMap<String,String>>rsszList=new ArrayList<HashMap<String,String>>();
		
		rsszList=dao.getRsszList(pkValue, user);
		
		String message="";
		
		if(rsszList!=null && rsszList.size()>0){
			
			HashMap<String,String>yszrsMap=rsszList.get(0);
			
			message=yszrsMap.get("xmmc")+"��Ŀ��������������ɾ����";
		}
		
		return message;
	}
	
	/**
	 * �ж��Ƿ�������������
	 * @author qlj
	 * flase: �������� true: ������
	 * @throws Exception
	 */
	public String showXssqMessage(PjszPjxmModel model, User user) throws Exception {
		
		WdpjXssqDAO xssqDAO=new WdpjXssqDAO();
		
		String[]pkValue=model.getPkValue();
		
		List<HashMap<String,String>>xssqList=new ArrayList<HashMap<String,String>>();
		
		xssqList=xssqDAO.getXssqList(pkValue, user);
		
		StringBuilder message=new StringBuilder();
		
		if(xssqList!=null && xssqList.size()>0){
			
			HashMap<String,String>ysqMap=xssqList.get(0);
			message.append("��<font color='blue'>");
			message.append(ysqMap.get("xmmc"));
			message.append("</font>��");
			message.append("����ѧ�����룬����ɾ����");
		}
		
		return message.toString();
	}
	
	public String checkDelete(PjszPjxmModel model, User user) throws Exception {

		String message = showXssqMessage(model, user);

		return message;
	}
	
	/**
	 * �ж��Ƿ���ѧ�����������Ŀ
	 * flase: �������� true: ������
	 * @author qlj
	 * @throws Exception
	 */
	public boolean checkXssq(PjszPjxmModel model, User user) throws Exception {
		
		WdpjXssqDAO xssqDAO=new WdpjXssqDAO();
		
		String xmdm=model.getXmdm();
		
		// ������Ŀ�����ѧ�������б�
		List<HashMap<String,String>>xssqList=xssqDAO.getXssqList(xmdm, user);
		
		xssqList=xssqDAO.getXssqList(xmdm, user);
		
		if(xssqList!=null && xssqList.size()>0){
			
			return false;	
		}
		
		return true;
	}
	
	
	/**
	 * ������Ŀ�������������λ
	 */
	public String[] getSpgwByXmdm(String xmdm){
		
		try {
			return dao.getSpgwByXmdm(xmdm);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public HashMap<String, String> getPjxmInfo(String xmdm){
		return dao.getPjxmInfo(xmdm);
	}
}