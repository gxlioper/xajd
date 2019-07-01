package xsgzgl.pjpy.czzyjsxy.zhcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ۺϲ���_����ְҵ����ѧԺ_Service��
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

public class PjpyZhcpService extends xsgzgl.pjpy.general.zhcp.PjpyZhcpService {

	PjpyZhcpDAO dao = new PjpyZhcpDAO();

	/**
	 * �ۺϷּ���������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean account(PjpyGeneralForm myForm, User user) throws Exception {

		boolean flag = false;

		// �۲��������(�۲�֡��Լ�ϵ��ּ���)
		flag = zcxmjs(myForm, user);

		if (flag) {
			// �۲��ܷ�����
			flag = getPlace(myForm, user);
		}

		if (flag) {
			// ��������������
			flag = getZyPlace(myForm, user);
		}

		return flag;
	}

	// =====================�۲��begin===========================

	/**
	 * �����۲�����
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean getPlace(PjpyGeneralForm model, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ������ѡ����������㷽ʽ��������
		String zcpm = jbszModel.getZcpm();

		boolean blog = false;

		// ����������
		if ("0".equalsIgnoreCase(zcpm)) {
			blog = cpzpmjs(model, user);
		} else {

			// �꼶ѧԺ����
			blog = xypmjs(model, user);

			// �꼶רҵ����
			blog = zypmjs(model, user);

			// �༶����
			blog = bjpmjs(model, user);

			// �۲�(��)�༶����
			zcsBjpm(model, user);
			// �۲�(��)�༶����
			zcxBjpm(model, user);
		}

		return blog;
	}

	/**
	 * ��������������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean cpzpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ��������������
		boolean flag = dao.cpzpmjs(model, user);
		if (flag) {
			flag = dao.updateCpzpm(model, user);
		}
		return flag;
	}

	/**
	 * �꼶ѧԺ��������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean xypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���꼶ѧԺ����������ʱ��
		boolean flag = dao.njxypmjs(model, user);
		if (flag) {
			flag = dao.updateXypm(model, user);
		}
		return flag;
	}

	/**
	 * �꼶רҵ��������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean zypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���꼶רҵ����������ʱ��
		boolean flag = dao.njzypmjs(model, user);
		if (flag) {
			flag = dao.updateZypm(model, user);
		}
		return flag;
	}

	/**
	 * �༶��������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean bjpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.bjpmjs(model, user);
		if (flag) {
			flag = dao.updateBjpm(model, user);
		}
		return flag;
	}

	/**
	 * �۲�(��)�༶��������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean zcsBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.zcsBjpm(model, user);
		if (flag) {
			flag = dao.updateZcspm(model, user);
		}
		return flag;
	}

	/**
	 * �۲�(��)�༶��������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean zcxBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.zcxBjpm(model, user);
		if (flag) {
			flag = dao.updateZcxpm(model, user);
		}
		return flag;
	}

	// =====================�۲��end===========================

	// =====================������begin===========================

	/**
	 * ������������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean getZyPlace(PjpyGeneralForm model, User user)
			throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ������ѡ����������㷽ʽ��������
		String zypm = jbszModel.getZypm();

		boolean blog = false;

		if ("0".equalsIgnoreCase(zypm)) {
			blog = cpzZypmjs(model, user);
		} else {

			// �꼶ѧԺ����
			blog = xyZypmjs(model, user);

			// �꼶רҵ����
			blog = zyZypmjs(model, user);

			// �༶����
			blog = bjZypmjs(model, user);

			// ����(��)�༶����
			zysBjpm(model, user);
			// ����(��)�༶����
			zyxBjpm(model, user);
		}

		return blog;

	}

	/**
	 * �������������㡾�����֡�
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean cpzZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ��������������
		boolean flag = dao.cpzZypmjs(model, user);
		if (flag) {
			flag = dao.updateCpzZypm(model, user);
		}
		return flag;
	}

	/**
	 * �꼶ѧԺ�������㡾�����֡�
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean xyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njxyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjXyZypm(model, user);
		}
		return flag;
	}

	/**
	 * �꼶רҵ�������㡾�����֡�
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean zyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njzyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjZyZypm(model, user);
		}
		return flag;
	}

	/**
	 * �༶�������㡾�����֡�
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean bjZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.bjZypmjs(model, user);
		if (flag) {
			flag = dao.updateBjZypm(model, user);
		}
		return flag;
	}

	/**
	 * �༶�������㡾�������ϡ�
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean zysBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.zysBjpm(model, user);
		if (flag) {
			flag = dao.updateZyspm(model, user);
		}
		return flag;
	}

	/**
	 * �༶�������㡾�������¡�
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean zyxBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.zyxBjpm(model, user);
		if (flag) {
			flag = dao.updateZyxpm(model, user);
		}
		return flag;
	}

	// =====================������end===========================

	// =====================�ۺϷֽ��begin===========================

	/**
	 * ��ȡ��ͷ��ʾ�ֶ�
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhcpResultTop(
			PjpyGeneralForm model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xh", "xm", "nj", "xymc", "zymc", "bjmc",
				"zd4", "zd5", "zd6", "zd7", "zd2", "zcspm", "zyspm", "zd28",
				"zd30", "zd8", "zd9", "zd10", "zd11", "zd3", "zcxpm", "zyxpm",
				"zd27", "zd29" };
		String[] cn = new String[] { "ѧ��", "����", "�꼶", "Ժϵ", "רҵ", "�༶",
				"������(��)", "������(��)", "������(��)", "������(��)", "�۲��(��)", "�۲�����(��)",
				"��������(��)", "�ȼ�(��)", "��ע(��)", "������(��)", "������(��)", "������(��)",
				"������(��)", "�۲��(��)", "�۲�����(��)", "��������(��)", "�ȼ�(��)", "��ע(��)" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ȡ�����
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 * @throws Exception
	 */
	public ArrayList<String[]> getZhcpResultList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws Exception {

		return (ArrayList<String[]>) dao.getZhcpResultList(myForm, user);
	}

	/**
	 * ���������
	 * 
	 * @data 2012-12-10
	 * @author ΰ�����
	 */
	public String createZhcpResultHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user) {

		BasicService basicService = new BasicService();

		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				// html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");
				//					
				// html.append("<input type='hidden' name='pkValue'   ");
				// html.append("  id='pkValue_"+i+"' ");
				// html.append(" value='" + rs[0] + "'/> ");
				//					
				// html.append("</td>");

				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 0; j < rs.length; j++) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(basicService.replaceHtml(rs[j]));
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}
	// =====================�ۺϷֽ��end===========================
}
