package xsgzgl.dtjs.general;

import java.lang.reflect.InvocationTargetException;

import xgxt.comm.CommService;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 党团建设_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DtjsGeneralService extends CommService {

	/**
	 * 构造团员缴费Service
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public DtjsTyjfInterface getDtjsTyjfService(DtjsGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 学校拼音名称
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.dtjs." + xxpymc + ".tyjf.DtjsTyjfService";

		DtjsTyjfInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.dtjs." + xxpymc + ".tyjf.DtjsTyjfService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (DtjsTyjfInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}
}
