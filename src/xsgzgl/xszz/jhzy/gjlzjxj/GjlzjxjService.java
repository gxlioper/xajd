package xsgzgl.xszz.jhzy.gjlzjxj;

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
import xgxt.utils.date.DateUtils;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ������_������־��ѧ��_��ְҵ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */

public class GjlzjxjService extends CommService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	GjlzjxjDAO dao = new GjlzjxjDAO();

	// ===============����������begin=====================

	/**
	 * ��ù�����־��ѧ�������ͷ
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public List<HashMap<String, String>> getGjlzjxjTop(GjlzjxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "rddc", "sqsj", "shzt" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����",
				"�༶����", "�������϶�����", "����ʱ��", "���״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ù�����־��ѧ����������
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public ArrayList<String[]> getGjlzjxjsqList(GjlzjxjForm myForm, GjlzjxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGjlzjxjsq(myForm, user);
	}


	/**
	 * ��ù�����־��ѧ������ѧ��������Ϣ
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public HashMap<String, String> getGjlzjxjInfo(String xh, String xn) {
		return dao.getGjlzjxjInfo(xh, xn);
	}

	/**
	 * ���������־��ѧ������
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public Boolean saveGjlzjxjSq(GjlzjxjModel model, User user) {

		boolean flag = false;

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();

		// ѧ��
		String xn = csszModel.getXn();

		String xh = model.getXh();// ѧ��

		List<String[]> params = new ArrayList<String[]>(){};
		String[] value = {DateUtils.getYear()+DateUtils.getMonth()+DateUtils.getDayOfMonth(),model.getBxkms(),model.getJgms(),
				model.getCjpm(),model.getSxzhkppm(),model.getZhkppm(),model.getSqly(),
				model.getHjsj1(),model.getHjmc1(),model.getBjdw1(),model.getHjsj2(),
				model.getHjmc2(),model.getBjdw2(),model.getHjsj3(),model.getHjmc3(),
				model.getBjdw3(),model.getHjsj4(),model.getHjmc4(),model.getBjdw4(),model.getXh(),model.getXn()};
		params.add(value);

		boolean isExists = isExists("xg_xszz_jhzy_gjlzjxjsqb", "xn||xh", xn + xh);

		try {
			if (isExists) {
				flag = dao.updateGjlzjxjsqb(params, user);
			} else {
				flag = dao.insertGjlzjxjsqb(params, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ɾ��������־��ѧ������
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public Boolean deleteGjlzjxjSq(GjlzjxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();
		try {

			flag = dao.deleteGjlzjxjsqb(pkValue, user);

		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �������̸���HTML
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws IOException
	 */
	public void createLcgzHtml(GjlzjxjModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		String pk = model.getPk();
		String xn = pk.split("!!luojw!!")[0];
		String xh = pk.split("!!luojw!!")[1];

		HashMap<String, String> map = getGjlzjxjInfo(xh, xn);
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"6\">	");
		html.append("<span>������־��ѧ��������̸���</span>");
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
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");

		response.getWriter().print(html.toString());
	}

	// ===============������־��ѧ������end=====================

	// ===============������־��ѧ�����begin=====================
	/**
	 * ��ù�����־��ѧ����˱�ͷ
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public List<HashMap<String, String>> getKnsshTop(GjlzjxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "shzt", "tjdc" };
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����",
				"�༶����", "����ʱ��", "���״̬", "�Ƽ�����" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ù�����־��ѧ����˽����
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public ArrayList<String[]> getGjlzjxjshList(GjlzjxjForm myForm, GjlzjxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGjlzjxjshList(myForm, user);
	}

	/**
	 * ���������־��ѧ�����
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public Boolean saveGjlzjxjSh(GjlzjxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();// ����

		String xn = model.getXn();// ѧ��

		String xh = model.getXh();// ѧ��

		String shyj = model.getShyj();// ������

		String shzt = model.getShzt();// ���״̬

		String userStatus = user.getUserStatus();// �û����

		String shsj = dao.getNowTime("YYYYMMDD");// ���ʱ��


		try {
			if (pkValue != null && pkValue.length > 0) {
				flag = dao.updateGjlzjxjsh(pkValue, shzt, shsj, shyj,
						userStatus, user);
			} else {
				flag = dao.updateGjlzjxjsh(xh, xn, shzt, shsj, shyj,
						userStatus, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	// ===============���������end=====================

	// ===============���������begin=====================

	/**
	 * ��ù�����־��ѧ������ͷ
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public List<HashMap<String, String>> getGjlzjxjjgTop(GjlzjxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "tjdc", ""};
		String[] cn = new String[] { "", "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����",
				"�༶����","�������϶�����", "����ʱ��", "��������" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ù�����־��ѧ���������
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public ArrayList<String[]> getGjlzjxjjgList(GjlzjxjForm myForm, GjlzjxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGjlzjxjjgList(myForm, user);
	}
	
	public String cxBjrs(String xh) {
		return dao.cxBjrs(xh);
	}
	/**
	 * �ж�ѧ���Ƿ������־��ѧ��
	 * @param xh
	 * @param xn
	 * @return
	 */
	public boolean isKns(String xh, String xn) {
		return "0".equalsIgnoreCase(dao.isKns(xh, xn)) ? false : true;
	}
	
}
