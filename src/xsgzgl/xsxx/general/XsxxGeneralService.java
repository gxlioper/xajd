package xsgzgl.xsxx.general;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.xsxx.general.inter.XsxxDljcInterface;
import xsgzgl.xsxx.general.inter.XsxxLsxsInterface;
import xsgzgl.xsxx.general.inter.XsxxXjydInterface;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgjgInterface;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;
import xsgzgl.xsxx.general.xjyd.XsxxXjydDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_ͨ��_Service��
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

public class XsxxGeneralService extends CommService {

	// ===============����Service=============================

	// ---------------------ѧ����Ϣ begin-----------------------

	/**
	 * ������Уѧ��Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XsxxZxxsInterface getXsxxZxxsService(XsxxGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.xsxx." + xxpymc + ".zxxs.XsxxZxxsService";

		XsxxZxxsInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.xsxx." + xxpymc + ".zxxs.XsxxZxxsService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XsxxZxxsInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * ������ʷѧ��Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public XsxxLsxsInterface getXsxxLsxsService(XsxxGeneralForm model)
			throws Exception {

		// ѧУƴ������
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.xsxx." + xxpymc + ".lsxs.XsxxLsxsService";

		Class interFaceClass = Class.forName(className);

		XsxxLsxsInterface service = (XsxxLsxsInterface) interFaceClass
				.getConstructor(null).newInstance(null);

		return service;
	}

	// ---------------------ѧ����Ϣ end-----------------------

	// =====================ѧ���춯 begin==========================

	/**
	 * ����ѧ���춯Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XsxxXjydInterface getXsxxXjydService(XsxxGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУƴ������
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.xsxx." + xxpymc + ".xjyd.XsxxXjydService";

		XsxxXjydInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.xsxx." + xxpymc + ".xjyd.XsxxXjydService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XsxxXjydInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xh", "xm", "xb", "nj", "xz", "bjmc" };
		String[] cn = new String[] { "", "ѧ��", "����", "�Ա�", "�꼶", "ѧ��", "�༶����" };
		return dao.arrayToList(en, cn);
	}

	public ArrayList<String[]> getXhAjax(XsxxGeneralForm myForm, User user)
			throws Exception {
		XsxxXjydDAO dao = new XsxxXjydDAO();
		return dao.getXhAjax(myForm, user);
	}

	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length - 1) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * �����ֳ��õ�HTML�ַ�ת��Ϊת���
	 * 
	 * @param html
	 * @return
	 */
	public String replaceHtml(String html) {

		if (!Base.isNull(html)) {

			html = html.replaceAll("\"", "&quot;");

			html = html.replaceAll("&", "&amp;");

			html = html.replaceAll("<", "&lt;");

			html = html.replaceAll(">", "&gt;");

		}
		return html;
	}

	// =====================ѧ���춯end==========================

	// =====================��¼��� begin========================

	/**
	 * �����¼����Service
	 * 
	 * @date 2012-12-17
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XsxxDljcInterface getXsxxDljcService(XsxxGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУƴ������
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.xsxx." + xxpymc + ".dljc.XsxxDljcService";

		XsxxDljcInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.xsxx." + xxpymc + ".dljc.XsxxDljcService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XsxxDljcInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	// =====================��¼��� end==========================

	// =====================��Ϣ�޸� begin========================

	/**
	 * ����Service����Ϣ�޸�_�޸Č��ˡ�
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XxxgXgshInterface getXxxgXgshService(XsxxGeneralForm myForm)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);

		String className = "xsgzgl.xsxx." + xxpymc + ".xxxg.xgsh.XgshService";

		XxxgXgshInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.xsxx." + xxpymc + ".xxxg.xgsh.XgshService";
				myForm.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XxxgXgshInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * ����Service����Ϣ�޸�_�޸ĽY����
	 * 
	 * @date 2013-01-28
	 * @author ΰ�����
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XxxgXgjgInterface getXxxgXgjgService(XsxxGeneralForm myForm)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);

		String className = "xsgzgl.xsxx." + xxpymc + ".xxxg.xgjg.XgjgService";

		XxxgXgjgInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.xsxx." + xxpymc + ".xxxg.xgjg.XgjgService";
				myForm.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XxxgXgjgInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	// =====================��Ϣ�޸� end========================

	/**
	 * ����ѧ����Ϣ
	 */
	public List<HashMap<String, String>> getXsxx(XsxxGeneralForm myForm,
			String xh) {
		XsxxXjydDAO dao = new XsxxXjydDAO();
		return dao.getXsxx(myForm, xh);
	}
}
