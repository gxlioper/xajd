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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ������_ͨ��_Service��
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

public class XszzGeneralService extends CommService {

	// ===================���I�սӿڡ� begin=====================
	/**
	 * ����ǼǱ��Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XszzYwjkInterface getXszzYwjkService()
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
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
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XszzYwjkInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}
	// ===================���I�սӿڡ� end=====================
}
