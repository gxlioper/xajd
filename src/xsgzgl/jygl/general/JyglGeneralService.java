package xsgzgl.jygl.general;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.jygl.general.inter.JyglSxjyInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import xsgzgl.pjpy.general.inter.PjpyXmszInterface;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszBjdlInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszSjszInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmjdInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmsyInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 就业管理_通用_Service类
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

public class JyglGeneralService extends BasicService {
	
	//=================构造Service begin==========================
	/**
	 * 【实习就业】
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public JyglSxjyInterface getJyglSxjyService(JyglGeneralForm myForm)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();

		String className = "xsgzgl.sxjy." + xxpymc + ".sxjy.JyglSxjyService";

		JyglSxjyInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.jygl." + xxpymc + ".sxjy.JyglSxjyService";
				myForm.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (JyglSxjyInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}
	//=================构造Service end==========================
}
