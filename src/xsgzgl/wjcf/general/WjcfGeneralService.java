package xsgzgl.wjcf.general;

import java.lang.reflect.InvocationTargetException;

import xgxt.comm.CommService;
import xsgzgl.wjcf.general.inter.WjcfCfjcInterface;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;
import xsgzgl.wjcf.general.inter.WjcfCfsbInterface;
import xsgzgl.wjcf.general.inter.WjcfCfshInterface;

public class WjcfGeneralService extends CommService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造违纪处分Service
	 * 
	 * @author xucy
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public WjcfCfsbInterface getWjcfCfsbService(WjcfGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 学校拼音名称
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.wjcf." + xxpymc + ".cfsbgl.WjcfCfsbService";

		WjcfCfsbInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.wjcf." + xxpymc + ".cfsbgl.WjcfCfsbService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (WjcfCfsbInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}
	

	/**
	 * 构造违纪处分Service
	 * 
	 * @author xucy
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public WjcfCfshInterface getWjcfCfshService(WjcfGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 学校拼音名称
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.wjcf." + xxpymc + ".cfsbgl.WjcfCfshService";

		WjcfCfshInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.wjcf." + xxpymc + ".cfsbgl.WjcfCfshService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (WjcfCfshInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}
	
	
	/**
	 * 构造违纪处分解除Service
	 * 
	 * @author xucy
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public WjcfCfjcInterface getWjcfCfjcService(WjcfGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 学校拼音名称
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.wjcf." + xxpymc + ".cfjcgl.WjcfCfjcService";

		WjcfCfjcInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.wjcf." + xxpymc + ".cfjcgl.WjcfCfjcService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (WjcfCfjcInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}
	
	/**
	 * 构造违纪处分解除审核Service
	 * 
	 * @author xucy
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public WjcfCfjcshInterface getWjcfCfjcshService(WjcfGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 学校拼音名称
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.wjcf." + xxpymc + ".cfjcgl.WjcfCfjcshService";

		WjcfCfjcshInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.wjcf." + xxpymc + ".cfjcgl.WjcfCfjcshService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			service = (WjcfCfjcshInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}
	
	
	
}
