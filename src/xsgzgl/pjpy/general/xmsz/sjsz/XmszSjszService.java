package xsgzgl.pjpy.general.xmsz.sjsz;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.inter.xmsz.XmszSjszInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_ʱ������_ͨ��_Service��
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

public class XmszSjszService extends CommService implements XmszSjszInterface {

	XmszSjszDAO dao = new XmszSjszDAO();

	/**
	 * ��ʼ����Ŀʱ������
	 * 
	 * @author ΰ�����
	 */
	public void defaultSjszSetting(XmszSjszModel model, User user,
			HttpServletResponse response) throws IOException {
		
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ʱ�������Ϣ
		HashMap<String, String> map = dao.getSjszInfo(xmdm);
		String sqkzkg = map.get("sqkzkg");// ������ƿ���
		String shkzkg = map.get("shkzkg");// ��˿��ƿ���
		String sqkssj = map.get("sqkssj");// ���뿪ʼʱ��
		sqkssj = Base.isNull(sqkssj) ? "" : sqkssj;
		String sqjssj = map.get("sqjssj");// �������ʱ��
		sqjssj = Base.isNull(sqjssj) ? "" : sqjssj;
		String shkssj = map.get("shkssj");// ��˿�ʼʱ��
		shkssj = Base.isNull(shkssj) ? "" : shkssj;
		String shjssj = map.get("shjssj");// ��˽���ʱ��
		shjssj = Base.isNull(shjssj) ? "" : shjssj;
		String bz = map.get("bz");// ��ע
		bz = Base.isNull(bz) ? "" : bz;
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"2\">	");
		html.append("<span>��Ŀʱ�����</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		//�������
		html.append("<tr>");
		html.append("<th width=\"30%\">��������ʱ��</th>");
		html.append("<td>");
		html.append("<input type=\"text\" name=\"str_sqkssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+sqkssj+"\" ");
		html.append("id=\"sqkssj\"/>");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("��");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("<input type=\"text\" name=\"str_sqjssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+sqjssj+"\" ");
		html.append("id=\"sqjssj\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("���뿪��");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" name=\"sqkzkg\" ");
		html.append("value=\"yes\" ");
		html.append("onclick=\"setCheckedValue(this);\" ");
		if("yes".equalsIgnoreCase(sqkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("id=\"sqkzkg_yes\"/>����");
		html.append("<input type=\"radio\" name=\"sqkzkg\" ");
		html.append("value=\"no\" ");
		html.append("onclick=\"setCheckedValue(this);\" ");
		if(Base.isNull(sqkzkg) || "no".equalsIgnoreCase(sqkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("id=\"sqkzkg_no\"/>�ر�");
		html.append("<input type=\"hidden\" name=\"str_sqkzkg\" id=\"sqkzkg_check\" ");
		if ("yes".equalsIgnoreCase(sqkzkg)) {
			html.append("value=\"yes\" ");
		} else if (Base.isNull(sqkzkg) || "no".equalsIgnoreCase(sqkzkg)) {
			html.append("value=\"no\" ");
		}
		html.append("/>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<th>");
		html.append("���뿪�ر�ע");
		html.append("<br/>");
		html.append("<font color=\"blue\">(��100��)</font>");
		html.append("</th>");
		html.append("<td>");
		html.append("<textarea name=\"str_bz\"");
		html.append("style=\"width:90%;word-break:break-all;\" ");
		html.append("onblur=\"chLeng(this,100)\" ");
		html.append(">");
		html.append(bz);
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		
		//������
		html.append("<tr>");
		html.append("<th width=\"30%\">�������ʱ��</th>");
		html.append("<td>");
		html.append("<input type=\"text\" name=\"str_shkssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+shkssj+"\" ");
		html.append("id=\"shkssj\"/>");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("��");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("<input type=\"text\" name=\"str_shjssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+shjssj+"\" ");
		html.append("id=\"shjssj\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("��˿���");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" name=\"shkzkg\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(shkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("onclick=\"setCheckedValue(this);\" ");
		html.append("id=\"shkzkg_yes\"/>����");
		html.append("<input type=\"radio\" name=\"shkzkg\" ");
		html.append("value=\"no\" ");
		if(Base.isNull(shkzkg) || "no".equalsIgnoreCase(shkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("onclick=\"setCheckedValue(this);\" ");
		html.append("id=\"shkzkg_no\"/>�ر�");
		html.append("<input type=\"hidden\" name=\"str_shkzkg\" id=\"shkzkg_check\" ");
		if ("yes".equalsIgnoreCase(shkzkg)) {
			html.append("value=\"yes\" ");
		} else if (Base.isNull(shkzkg) || "no".equalsIgnoreCase(shkzkg)) {
			html.append("value=\"no\" ");
		}
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		// -------------------------��ť---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  onclick=\"checkSaveSjsz();return false;\">�� ��</button>");
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">�� ��</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------��ť end---------------------------
		
		response.getWriter().print(html.toString());
		
	}

	/**
	 * ɾ��ʱ������
	 * 
	 * @author ΰ�����
	 */
	public Boolean deleteSjsz(XmszSjszModel model, User user) {

		boolean flag = false;
		// ��Ŀ����
		String xmdm = model.getXmdm();
		
		try {
			flag = dao.deleteSjszb(xmdm, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * ����ʱ������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveSjsz(XmszSjszModel model, User user) {

		String xmdm = model.getXmdm();// ��Ŀ����
		String sqkzkg = model.getSqkzkg();// ������ƿ���
		String shkzkg = model.getShkzkg();// ��˿��ƿ���
		String sqkssj = model.getSqkssj();// ���뿪ʼʱ��
		String sqjssj = model.getSqjssj();// �������ʱ��
		String shkssj = model.getShkssj();// ��˿�ʼʱ��
		String shjssj = model.getShjssj();// ��˽���ʱ��
		String bz = model.getBz();// ��ע
		
		boolean flag = deleteSjsz(model, user);

		if (flag) {
			try {
				flag = dao.insertSjszb(xmdm, sqkzkg, shkzkg, sqkssj, sqjssj,
						shkssj, shjssj, bz, user);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		
		return flag;
	}
}
