package xsgzgl.xszz.general;

import java.lang.reflect.InvocationTargetException;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.inter.PjpyYwjkInterface;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;
import xsgzgl.szdw.general.inter.tjcx.TjcxBmqkInterface;
import xsgzgl.szdw.general.inter.tjcx.TjcxSzryInterface;
import xsgzgl.xszz.general.inter.XszzYwjkInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生资助_通用_Service类
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

public class XszzGeneralService extends CommService {

	// ===================【業務接口】 begin=====================
	/**
	 * 构造登记表格Service
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XszzYwjkInterface getXszzYwjkService()
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 学校代码
		String xxdm = Base.xxdm;
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);

		String className = "xsgzgl.xszz." + xxpymc + ".ywjk.XszzYwjkService";

		XszzYwjkInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.xszz." + xxpymc + ".ywjk.XszzYwjkService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (XszzYwjkInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}
	// ===================【業務接口】 end=====================
}
