package xsgzgl.xszz.jhzy.gjzxj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ������_������ѧ��_��ְҵ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class GjzxjService extends CommService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	GjzxjDAO dao = new GjzxjDAO();

	// ===============������ѧ������begin=====================

	/**
	 * ��ù�����ѧ�������ͷ
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZxjsqTop(GjzxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "shzt" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����",
				"�༶����", "����ʱ��", "���״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ù�����ѧ����������
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getZxjsqList(GjzxjForm myForm, GjzxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZxjsqList(myForm, user);
	}

	/**
	 * ��ù�����ѧ������ѧ��������Ϣ
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public HashMap<String, String> getGjzxjInfo(String xh, String xn) {
		return dao.getGjzxjInfo(xh, xn);
	}

	/**
	 * ���������ѧ������
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public Boolean saveGjzxjSq(GjzxjModel model, User user) {

		boolean flag = false;

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();

		// ѧ��
		String xn = csszModel.getXn();

		String xh = model.getXh();// ѧ��

		String sqly = model.getSqly();// ��������

		boolean isExists = isExists("xg_xszz_jhzy_gjzxjsqb", "xn||xh", xn + xh);

		try {
			if (isExists) {
				flag = dao.updateZxjsqb(xh, xn, sqly, user);
			} else {
				flag = dao.insertZxjsqb(xh, xn, sqly, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ɾ��������ѧ������
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public Boolean deleteGjzxjSq(GjzxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();
		try {

			flag = dao.deleteZxjsqb(pkValue, user);

		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �������̸���HTML
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void createLcgzHtml(GjzxjModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		String pk = model.getPk();
		String xn = pk.split("luojw")[0];
		String xh = pk.split("luojw")[1];

		HashMap<String, String> map = getGjzxjInfo(xh, xn);
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"6\">	");
		html.append("<span>������ѧ���϶�������̸���</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th>");
		html.append("���");
		html.append("</th>");
		html.append("<th>");
		html.append("�û�����");
		html.append("</th>");
		html.append("<th>");
		html.append("���루��ˣ���");
		html.append("</th>");
		html.append("<th>");
		html.append("����׶�");
		html.append("</th>");
		html.append("<th>");
		html.append("����ʱ��");
		html.append("</th>");
		html.append("<th>");
		html.append("�Ƽ�����");
		html.append("</th>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("1");
		html.append("</td>");
		html.append("<td>");
		html.append("ѧ����");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("xm"));
		html.append("</td>");
		html.append("<td>");
		html.append("����");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("sqsj"));
		html.append("</td>");
		html.append("<td>");
		html.append("");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("2");
		html.append("</td>");
		html.append("<td>");
		html.append("������");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("bzrxm")) ? map.get("bzrxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("bzrsh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("bzrshsj")) ? map.get("bzrshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("bzrtjdc")) ? map.get("bzrtjdc") : "");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("3");
		html.append("</td>");
		html.append("<td>");
		html.append("����Ա");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("fdyxm")) ? map.get("fdyxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("fdysh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("fdyshsj")) ? map.get("fdyshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("fdytjdc")) ? map.get("fdytjdc") : "");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("4");
		html.append("</td>");
		html.append("<td>");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		html.append(Base.YXPZXY_KEY);
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xyxm")) ? map.get("xyxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("xysh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xyshsj")) ? map.get("xyshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xytjdc")) ? map.get("xytjdc") : "");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("5");
		html.append("</td>");
		html.append("<td>");
		html.append("ѧУ");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xxxm")) ? map.get("xxxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("xxsh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xxshsj")) ? map.get("xxshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xxtjdc")) ? map.get("xxtjdc") : "");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");

		response.getWriter().print(html.toString());
	}

	// ===============������ѧ������end=====================

	// ===============������ѧ�����begin=====================

	/**
	 * ��ù�����ѧ����˱�ͷ
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZxjshTop(GjzxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "knsdj", "sqsj", "shzt", "tjdc" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����",
				"�༶����", "�������ȼ�", "����ʱ��", "���״̬", "�Ƽ�����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ù�����ѧ����˽����
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getZxjshList(GjzxjForm myForm, GjzxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZxjshList(myForm, user);
	}

	/**
	 * ���������ѧ�����
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public Boolean saveGjzxjSh(GjzxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();// ����

		String xn = model.getXn();// ѧ��

		String xh = model.getXh();// ѧ��

		String shyj = model.getShyj();// ������

		String shzt = model.getShzt();// ���״̬

		String userStatus = user.getUserStatus();// �û����

		String shsj = dao.getNowTime("YYYYMMDD");// ���ʱ��

		String tjdc = model.getTjdc();// �Ƽ�����

		try {
			if (pkValue != null && pkValue.length > 0) {
				flag = dao.updateZxjsqb(pkValue, shzt, shsj, shyj, tjdc,
						userStatus, user);
			} else {
				flag = dao.updateZxjsqb(xh, xn, shzt, shsj, shyj, tjdc,
						userStatus, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	// ===============������ѧ�����end=====================

	// ===============������ѧ����begin=====================

	/**
	 * ��ù�����ѧ������ͷ
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZxjjgTop(GjzxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "knstjdc", "sqsj", "tjdc" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����",
				"�༶����", "����������", "����ʱ��", "�Ƽ�����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ù�����ѧ���������
	 * 
	 * @date 2012-12-05
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getZxjjgList(GjzxjForm myForm, GjzxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZxjjgList(myForm, user);
	}

	// ===============������ѧ����end=====================
}
