package xgxt.tags;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.RequestUtils;

import xgxt.base.DealString;

public class CustomTag extends TagSupport {
	private static final long serialVersionUID = -5447350596534864104L;

	// ������������Զ�ӦTestTag.tld�ļ��ж�selectResnodes�������������
	private String scope;

	private String nodeslist;

	private String rsname;

	private String doType;

	@SuppressWarnings("unchecked")
	public int doStartTag() {
		ActionForm form = null;
		HashMap<String, String> map = null;
		doType = DealString.toGBK(doType);
		try {
			form = (ActionForm) RequestUtils.lookup(pageContext, nodeslist,scope);
			map = (HashMap<String, String>) RequestUtils.lookup(pageContext,rsname, scope);
			// ��ֹΪ�պ󱨴�
			if (map == null) {
				map = new HashMap<String, String>();
			}
		} catch (JspException e1) {
			e1.printStackTrace();
		}
		JspWriter out = pageContext.getOut();
		try {
			// �ֶ�id
			String[] zdyZdid = (String[]) form.getClass().getMethod("getArrZdid",(Class[]) null).invoke(form, (Object[]) null);
			// �ֶ�����
			String[] zdyZdmc = (String[]) form.getClass().getMethod("getArrZdmc", (Class[]) null).invoke(form, (Object[]) null);
			// �ֶ�����
			String[] zdyZdlx = (String[]) form.getClass().getMethod("getArrZdlx", (Class[]) null).invoke(form, (Object[]) null);
			// �ֶγ���
			String[] zdyZdcd = (String[]) form.getClass().getMethod("getArrZdcd", (Class[]) null).invoke(form, (Object[]) null);
			// �Ƿ����
			String[] zdySfbt = (String[]) form.getClass().getMethod("getArrSfbt", (Class[]) null).invoke(form, (Object[]) null);
			// ������ѡ��
			HashMap<String, ArrayList<HashMap<String, String>>> opss = (HashMap<String, ArrayList<HashMap<String, String>>>) form
								.getClass().getMethod("getOpslist", (Class[]) null).invoke(form, (Object[]) null);

			int m = 0;
			
			StringBuilder sfbt = new StringBuilder();
			
			// ������ֶ�
			for (int i = 0; null != zdyZdid && i < zdyZdid.length; i++) {
				
				boolean sfTr = (m % 2 == 0) ? true : false;

				//��������ڶ��ֶΣ���������ѭ��
				if (!(zdyZdlx[i].equalsIgnoreCase("001")
						|| zdyZdlx[i].equalsIgnoreCase("006")
						|| zdyZdlx[i].equalsIgnoreCase("007")
						|| zdyZdlx[i].equalsIgnoreCase("002") 
						|| zdyZdlx[i].equalsIgnoreCase("003"))) {
					continue;
				} 
				
				//���뿪ʼtr�ͽ���tr
				if (sfTr) {
					if (m != 0) {
						out.println("</tr>");
					} 
					out.println("<tr>");
				} 
				
				// ���ı�
				if (zdyZdlx[i].equalsIgnoreCase("001")) {
					
					out.println("<th>");
					if ("��".equals(zdySfbt[i])) {
						
						sfbt.append(zdyZdid[i]).append("-");
						out.print("<font color='red'>*</font>");
					}
					out.println(zdyZdmc[i]);
					out.println("</th>");
					out.println("<td>");
					
					if ("view".equalsIgnoreCase(doType)) {
						out.println(DealString.toGBK(map.get(zdyZdid[i])));
					} else {
						out.print("<input type='text' name =\"");
						out.print(zdyZdid[i]);
						out.print("\" value='");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.print("' id =\"");
						out.print(zdyZdid[i]);
						out.print("\" maxlength = '");
						out.print(zdyZdcd[i]);
						out.print("'");
						out.println("/>");
						
					}
					
					out.println("</td>");
					m++;
					
				} else if ("006".equals(zdyZdlx[i])) {
					//����
					out.println("<th>");
					if ("��".equals(zdySfbt[i])) {
						
						sfbt.append(zdyZdid[i]).append("-");
						out.print("<font color='red'>*</font>");
					}
					out.println(zdyZdmc[i]);
					out.println("</th>");
					out.println("<td>");
					
					if ("view".equalsIgnoreCase(doType)) {
						out.println(DealString.toGBK(map.get(zdyZdid[i])));
					} else {
						out.print("<input type='text' onkeyup=\"value=value.replace(/[^\\d]/g,'');\" ");
						out.print("name = '");
						out.print(zdyZdid[i]);
						out.print("' value='");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.print("' id = '");
						out.print(zdyZdid[i]);
						out.print("' maxlength = '");
						out.print(zdyZdcd[i]);
						out.print("'");
						out.println("/>");
						
					}
					
					out.println("</td>");
					m++;
				} else if ("007".equals(zdyZdlx[i])) {
					//���---��δ��
					
					
					out.println("</td>");
					m++;
				} else if (zdyZdlx[i].equalsIgnoreCase("002")) {
					// ʱ��
					out.println("<th>");
					
					if ("��".equals(zdySfbt[i])) {
						
						sfbt.append(zdyZdid[i]).append("-");
						out.print("<font color='red'>*</font>");
					}
					out.println(zdyZdmc[i]);
					out.println("</th>");
					out.println("<td>");
					if ("view".equalsIgnoreCase(doType)) {
						out.println(DealString.toGBK(map.get(zdyZdid[i])));
					} else {
						
						out.print("<input type='text' name='");
						out.print(zdyZdid[i]);
						out.print("' value='");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.print("' onclick=\"return showCalendar('");
						out.print(zdyZdid[i]);
						out.print("','y-mm-dd');\" onblur=\"dateFormatChg(this)\" style='cursor:hand;' id='");
						out.print(zdyZdid[i] );
						out.println("'/>");
						
					}
					out.println("</td>");
					m++;
				} else if (zdyZdlx[i].equalsIgnoreCase("003")) {
					// ������
					// ȡ���������б��ֵ
					ArrayList<HashMap<String, String>> arrayList = opss.get(zdyZdid[i]);
					out.println("<th>");
					
					if ("��".equals(zdySfbt[i])) {
						
						sfbt.append(zdyZdid[i]).append("-");
						out.print("<font color='red'>*</font>");
					}
					
					out.println(zdyZdmc[i]);
					out.println("</th>");
					out.println("<td>");
					if ("view".equalsIgnoreCase(doType)) {
						out.println(DealString.toGBK(map.get(zdyZdid[i])));
					} else {
						
						out.print("<select name='");
						out.print(zdyZdid[i]);
						out.print("' id = '");
						out.print(zdyZdid[i]);
						out.println("'><option value=''></option>");
						
						for (int k = 0; k < arrayList.size(); k++) {
							HashMap<String, String> ops = arrayList.get(k);
							if (DealString.toGBK(map.get(zdyZdid[i]))
									.equalsIgnoreCase(ops.get("opid"))) {
								
								out.print("<option value='");
								out.print(ops.get("opid"));
								out.print("' selected='selected'>");
								out.print(ops.get("opmc"));
								out.println("</option>");
								
							} else {
								
								out.print("<option value='");
								out.print(ops.get("opid"));
								out.print("'>");
								out.print(ops.get("opmc"));
								out.println("</option>");
							}
						}
						out.println("");
						out.println("</select>");
					}
					out.println("</td>");
					m++;
				} 
				
				//ѭ�������һ�β�����
				if (i == zdyZdid.length - 1) {
					
					if (m%2 != 0) {
						out.println("<th></th>");
						out.println("<td></td>");
					}
					out.println("</tr>");
				} 
			}
			
			
			//���ı�
			for (int i = 0;  null != zdyZdid && i <zdyZdid.length; i++) {
				//boolean sfTr = (i % 2 == 0) ? true : false;
				if (zdyZdlx[i].equalsIgnoreCase("004")) {
					
					out.println("<tr>");
					out.println("<th>");
					if ("��".equals(zdySfbt[i])) {
						out.print("<font color='red'>*</font>");
					}
					out.println(zdyZdmc[i]);
					out.println("</th>");
					out.println("<td colspan=\"3\">");
					if ("view".equalsIgnoreCase(doType)) {
						out.println(DealString.toGBK(map.get(zdyZdid[i])));
					} else {
						
						out.print("<input type='text'  name = '");
						out.print(zdyZdid[i]);
						out.print("' value='");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.print("' id = '");
						out.print("zdyZd[i]");
						out.print("' maxlength = '");
						out.print(zdyZdcd[i]);
						out.println("' style='width:97%'/>");
						
					}
					out.println("</td>");
					out.println("</tr>");
				} else if (zdyZdlx[i].equalsIgnoreCase("005")) {
					// �ı���
					String zdcd = ((Integer) (Integer.parseInt(zdyZdcd[i]) / 2)).toString();
					String rows = ((Integer) (Integer.parseInt(zdyZdcd[i]) / 200 + 1)).toString();
					out.println("<tr>");
					out.println("<th>");
					
					/*if ("��".equals(zdySfbt[i])) {
						out.print("<font color='red'>*</font>");
					}*/
					out.println(zdyZdmc[i]);
					if (!doType.equalsIgnoreCase("view")) {
						out.println("<br/><span style=\"color:red\"><��"
								+ zdcd + "��></span>");
					}
					out.println("</th>");
					out.println("<td colspan=\"3\">");
					if ("view".equalsIgnoreCase(doType)) {
						
						out.print("<textarea name='");
						out.print(zdyZdid[i]);
						out.print("' rows='");
						out.print(rows);
						out.print("' style='width:97%;word-break:break-all;' value = '");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.print("' readonly='readonl'>");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.println("</textarea>");
						
					} else {
						
						out.print("<textarea name=\"");
						out.print(zdyZdid[i]);
						out.print("\" rows=\"");
						out.print(rows);
						out.print("\" style=\"width:97%;word-break:break-all;\" value = \"");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.print("\" onblur=\"chLeng(this,");
						out.print(zdcd);
						out.print(")\" >");
						out.print(DealString.toGBK(map.get(zdyZdid[i])));
						out.println("</textarea>");
						
					}
					out.println("</td>");
					out.println("</tr>");
				}
			}
			
			if (sfbt.length() > 0) {
				sfbt.deleteCharAt(sfbt.length()-1);
			}
			
			out.println("<input type=\"hidden\" id='sfbtString' value='");
			out.print(sfbt.toString());
			out.print("'>");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return EVAL_BODY_INCLUDE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeslist() {
		return nodeslist;
	}

	public void setNodeslist(String nodeslist) {
		this.nodeslist = nodeslist;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRsname() {
		return rsname;
	}

	public void setRsname(String rsname) {
		this.rsname = rsname;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}
}
