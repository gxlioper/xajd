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
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzdwGeneralService extends BasicService {
	private SzdwNewDAO szdwNewDAO = new SzdwNewDAO();
	/**
	 * 构造Service【思政队伍维护】
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public SzdwDwwhInterface getSzdwDwwhService(SzdwGeneralForm myForm)
			throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 学校拼音名称
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
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (SzdwDwwhInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * 构造Service【思政队伍编班】
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public SzdwSzbbInterface getSzdwSzbbService(SzdwGeneralForm myForm)
			throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 学校拼音名称
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
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (SzdwSzbbInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * 构造Service【统计查询_思政人员】
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxSzryInterface getTjcxSzryService(SzdwGeneralForm myForm)
			throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 学校拼音名称
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
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (TjcxSzryInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * 构造Service【统计查询_部门情况】
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxBmqkInterface getTjcxBmqkService(SzdwGeneralForm myForm)
			throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 学校拼音名称
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
					// TODO 自动生成 catch 块
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
