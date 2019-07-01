/**
 * @部门:学工产品事业部
 * @日期：2013-12-4 下午03:07:14 
 */
package com.zfsoft.xgxt.base.export.business;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 获取业务扩展实体工厂
 * @类功能描述:获取对应业务扩展对象简单工厂
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-4 下午03:07:14
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BusinessFactory {
	public static IBaseBusiness getInstance(ImportModel im) {
		String drmkdm = im.getDrmkdm();
		String yzcs = im.getYzcs();
		HashMap<String, String> map = getRuleParam(drmkdm);
		String classPath = map.get("yzlbm");
		String calssName = map.get("yzlmc");
		String[] param = StringUtils.isNull(yzcs) ? new String[] {} : yzcs
				.split("-");
		param = Arrays2.addObject(param, drmkdm);
		return getObject(classPath, calssName, param);
	}

	/**
	 * 
	 * @描述: 获取配置参数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-2 下午04:20:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dryzbh
	 * @return HashMap<String,String> 返回类型
	 */
	private static HashMap<String, String> getRuleParam(String drmkdm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ZFXG_DR_DRYZB where dryzbh=?");
		return DAO.getInstance().getMapNotOut(sb.toString(),
				new String[] { drmkdm });
	}

	/**
	 * 
	 * @描述: 根据参数获取对应扩展对象
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-2 下午04:20:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param classPath
	 * @param calssName
	 * @param obj
	 * @return BusinessExtend 返回类型
	 */
	@SuppressWarnings("unchecked")
	private static BusinessExtend getObject(String classPath, String calssName,
			Object... obj) {
		if (StringUtils.isNull(classPath) || StringUtils.isNull(calssName)) {
			return null;
		}
		try {
			// 构造参数
			Class[] classs = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				classs[i] = obj[i].getClass();
			}
			Class<BusinessExtend> ib = (Class<BusinessExtend>) Class
					.forName(classPath + "." + calssName); 
			return ib.getConstructor(classs).newInstance(obj);
		} catch (Exception e) {
			throw new RuntimeException("配置验证类路径错误请检查:"+classPath + "." + calssName);
		}
	}
}
