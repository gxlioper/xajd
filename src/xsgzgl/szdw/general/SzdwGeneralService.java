package xsgzgl.szdw.general;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;
import xsgzgl.szdw.general.inter.tjcx.TjcxBmqkInterface;
import xsgzgl.szdw.general.inter.tjcx.TjcxSzryInterface;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SzdwGeneralService extends BasicService {
	private SzdwNewDAO szdwNewDAO = new SzdwNewDAO();
	/**
	 * ����Service��˼������ά����
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public SzdwDwwhInterface getSzdwDwwhService(SzdwGeneralForm myForm)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);

		String className = "xsgzgl.szdw." + xxpymc + ".dwwh.DwwhService";

		SzdwDwwhInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.szdw." + xxpymc + ".dwwh.DwwhService";
				myForm.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (SzdwDwwhInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * ����Service��˼�������ࡿ
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public SzdwSzbbInterface getSzdwSzbbService(SzdwGeneralForm myForm)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);

		String className = "xsgzgl.szdw." + xxpymc + ".szbb.SzbbService";

		SzdwSzbbInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.szdw." + xxpymc + ".szbb.SzbbService";
				myForm.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (SzdwSzbbInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * ����Service��ͳ�Ʋ�ѯ_˼����Ա��
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxSzryInterface getTjcxSzryService(SzdwGeneralForm myForm)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);

		String className = "xsgzgl.szdw." + xxpymc + ".tjcx.szry.SzryService";

		TjcxSzryInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.szdw." + xxpymc + ".tjcx.szry.SzryService";
				myForm.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxSzryInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * ����Service��ͳ�Ʋ�ѯ_���������
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxBmqkInterface getTjcxBmqkService(SzdwGeneralForm myForm)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);

		String className = "xsgzgl.szdw." + xxpymc + ".tjcx.bmqk.BmqkService";

		TjcxBmqkInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.szdw." + xxpymc + ".tjcx.bmqk.BmqkService";
				myForm.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxBmqkInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	public List<HashMap<String,String>> getJsxxList(SzdwGeneralForm myForm, User user) throws Exception {
		return szdwNewDAO.getPageList(myForm,user);
	}

	public List<HashMap<String, String>> getSzbbList(SzdwGeneralForm myForm, User user) throws Exception {
		return szdwNewDAO.getSzbbList(myForm,user);
	}
	public List<HashMap<String, String>> getSzBzrbbList(SzdwGeneralForm myForm, User user) throws Exception {
		return szdwNewDAO.getSzBzrbbList(myForm,user);
	}

	public List<HashMap<String, String>> getXsxxList(SzdwGeneralForm myForm) throws Exception {
		return szdwNewDAO.getXsxxList(myForm);
	}
}
