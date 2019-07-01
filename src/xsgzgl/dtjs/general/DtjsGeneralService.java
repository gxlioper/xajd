package xsgzgl.dtjs.general;

import java.lang.reflect.InvocationTargetException;

import xgxt.comm.CommService;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���Ž���_ͨ��_Service��
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

public class DtjsGeneralService extends CommService {

	/**
	 * ������Ա�ɷ�Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public DtjsTyjfInterface getDtjsTyjfService(DtjsGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУƴ������
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
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (DtjsTyjfInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}
}
