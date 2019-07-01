package xsgzgl.pjpy.general.xmsz.xmjd;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmjdInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��Ŀ���_ͨ��_Service��
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

public class XmszXmjdService extends CommService implements XmszXmjdInterface {

	XmszXmjdDAO dao = new XmszXmjdDAO();

	/**
	 * ��ʼ����Ŀ�������
	 * 
	 * @author ΰ�����
	 */
	public void defaultXmjdSetting(XmszXmjdModel model, User user,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm=new PjpyGeneralForm();
		
		PjpyGeneralService myService = new PjpyGeneralService();
		// ������Ŀ����
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ���û�����Ϣ
		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);
		// �жϴ���Ŀ�Ƿ�����ѧ������
		boolean checkXssq=pjxmService.checkXssq(pjxmModel, user);
		// ����б�
		List<HashMap<String, String>> list = dao.getXmjdList(xmdm);
		// ����
		int rownum = 0;
		// ����
		int colnum = 4;
		if (list != null && list.size() > 0) {
			int space = list.size() % colnum;
			rownum = (space == 0) ? list.size() / colnum : list.size() / colnum
					+ 1;
		}

		rownum = rownum < 7 ? 7 : rownum;
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div style=\"width:100%;height:250px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"4\">	");
		html.append("<span>���ɼ����Ŀѡ��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		
		int num = 0;
		
		for (int i = 0; i < rownum; i++) {
			html.append("<tr>");
			for (int j = 0; j < colnum; j++) {
				
				html.append("<td width=\"25%\">");
				if (num < list.size()) {
					HashMap<String, String> map = list.get(num);
					html.append("<input type=\"checkbox\" ");
					html.append("name=\"array_fjddm\" ");
					html.append("id=\"checkbox_" + map.get("xmdm") + "\" ");
					html.append("value=\"" + map.get("xmdm") + "\" ");
					if (!Base.isNull(map.get("sfjd"))) {
						html.append("checked=\"true\" ");
					}
					html.append("/>");
					
					html.append("<span title=\"" + map.get("xmmc") + "\">");
					html.append(map.get("xmsx"));
					html.append("</span>");
					
					num++;
				}else{
					html.append("&nbsp;");
				}
				html.append("</td>");
			}
			html.append("</tr>");
		}
		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>");
		
		// -------------------------��ť---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\"><input type=\"checkbox\" id=\"chb_all\" onclick=\"clickAllJdxm()\">ȫѡ</div>");	
		html.append("<div class=\"btn\">");	
		html.append("<button type=\"button\"  onclick=\"checkSaveXmjd();return false;\"");
		if(!checkXssq){
			html.append(" disabled=\"true\"");
		}
		html.append(">�� ��</button>");
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
	 * ɾ����Ŀ���
	 * 
	 * @author ΰ�����
	 */
	public Boolean deleteXmjd(XmszXmjdModel model, User user) {
		
		boolean flag = false;
		// ��Ŀ����
		String xmdm = model.getXmdm();
		
		try {
			flag = dao.deleteJdszb(xmdm, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * ������Ŀ���
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveXmjd(XmszXmjdModel model, User user) {

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �Ǽ�ô���
		String[] fjddm = model.getFjddm();

		boolean flag = deleteXmjd(model, user);
		
		if(flag){
			try {
				dao.insertJdszb(xmdm, fjddm, user);
				dao.insertJdszb(fjddm, xmdm, user);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		
		return flag;
	}
}
