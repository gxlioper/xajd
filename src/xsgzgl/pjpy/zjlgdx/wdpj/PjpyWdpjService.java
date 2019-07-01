package xsgzgl.pjpy.zjlgdx.wdpj;

import java.util.ArrayList;


import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ��ǿ
 * @version 1.0
 */
public class PjpyWdpjService  extends
		xsgzgl.pjpy.general.wdpj.PjpyWdpjService {

	PjpyWdpjDAO dao = new PjpyWdpjDAO();

	
	/**
	 * ���������(��������_�ҵ�������ѧ����)
	 * 
	 * @author ��ǿ
	 */
	public String createStuHTML(SearchRsModel rsModel, PjpyWdpjModel model,
			ArrayList<String[]> rsArrList, User user) {
		String userType = user.getUserType();

		// IE�汾
		String ie = rsModel.getIe();

		String stylePath = rsModel.getStylePath();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String xmdm=rs[rs.length-1];

				html.append("<tr onclick=\"rowOnClick(this);\" >");
				
				for (int j=0;j<rs.length-1;j++){
				// --------------------����HTML��չ�ֶ����������------------------------

				// -----------------------��Ŀ���� begin---------------------------
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				html.append(rs[j]);
				html.append("</td>");
				// -----------------------��Ŀ���� end---------------------------

				// -----------------------��ǰ���״̬ end---------------------------

				// -----------------------��ǰ���״̬
				// begin---------------------------
				if(j==2){
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(" <a href=\"#\" onclick=\"showWdpjView('"+xmdm+"');return false;\" ><font color=\"blue\">�鿴��ϸ��Ϣ</font></a>");
					html.append("/");
					html.append(" <a href=\"#\" onclick=\"printPj('"+rs[0]+"','"+user.getUserName()+"');return false;\"><font color=\"blue\">��ӡ�ǼǱ�</font></a> ");
					html.append("</td>");
					}
				// -----------------------��ǰ���״̬ end---------------------------
				}
				html.append("</tr>");
			}
		}

		return html.toString();
	}

}
