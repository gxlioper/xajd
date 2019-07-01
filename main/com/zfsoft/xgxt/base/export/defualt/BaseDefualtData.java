/**
 * @部门:学工产品事业部
 * @日期：2014-1-16 上午10:37:45 
 */
package com.zfsoft.xgxt.base.export.defualt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 下载模板
 * @类功能描述: 获取模板默认数据
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-16 上午10:37:45
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BaseDefualtData implements IDefualtData {
	private String kzbm;
	private String kzlm;

	public List<String[]> getDefualtData(String drmkdm) {
		List<HashMap<String, String>> list = getDefualtDataForDb(drmkdm);
		List<String[]> defualtList = new ArrayList<String[]>();
		for (HashMap<String, String> hm : list) {
			// 数据库配置默认值
			defualtList.add(fomartParam(drmkdm, hm.get("hh")));
		}
		// 业务扩展设置的默认值
		List<String[]> business = getDefualtDataForBusiness(kzbm,
				kzlm, drmkdm);
		if (null != business) {
			defualtList.addAll(business);
		}
		return defualtList;
	}

	/**
	 * 
	 * @描述: 获取业务扩展配置默认值
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-16 上午11:30:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kzbm
	 *            扩展包名
	 * @param kzlm
	 *            扩展类名(必须实现IDefualtData接口)
	 * @param drmkdm
	 *            扩展模块代码
	 * @return List<String[]> 返回类型
	 */
	private List<String[]> getDefualtDataForBusiness(String kzbm,
			String kzlm, String drmkdm) {
		IDefualtData bd = getObject(kzbm, kzlm, null);
		if (null == bd) {
			return null;
		}
		return bd.getDefualtData(drmkdm);
	}

	/**
	 * 
	 * @描述: 获取对应扩展对象
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-2 下午04:20:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param classPath
	 * @param calssName
	 * @param obj
	 * @return BusinessExtend 返回类型
	 */
	private static IDefualtData getObject(String classPath, String calssName,
			Object... obj) {
		if (StringUtils.isNull(classPath) || StringUtils.isNull(calssName)) {
			return null;
		}
		try {
			if (null == obj) {
				return (IDefualtData) Class
						.forName(classPath + "." + calssName).newInstance();
			}
			// 构造参数
			Class[] classs = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				classs[i] = obj[i].getClass();
			}
			Class<IDefualtData> ib = (Class<IDefualtData>) Class
					.forName(classPath + "." + calssName);
			return ib.getConstructor(classs).newInstance(obj);
		} catch (Exception e) {
			throw new RuntimeException("配置错误，请检查 路径配置和扩展类是否实现IDefualtData接口:"
					+ classPath + "." + calssName);
		}
	}

	private List<HashMap<String, String>> getDefualtDataForDb(String drmkdm) {
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select drmkdm, hh from zfxg_dr_mrsj where drmkdm=?");
		sb.append(" group by drmkdm, hh order by hh");
		return DAO.getInstance().getListNotOut(sb.toString(),
				new String[] { drmkdm });
	}

	private String[] fomartParam(String drmkdm, String rowindex) {
		String kzbm;
		String kzlm;
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select * from zfxg_dr_mrsj where drmkdm=? and hh=? order by to_number(xh)");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] { drmkdm, rowindex });
		if(null==list||list.size()<=0){
			return new String[]{};
		}
		String maxXh=list.get(list.size()-1).get("xh");
		String[] param = new String[Integer.parseInt(maxXh)];
		int i = 0;
		for (HashMap<String, String> hm : list) {
			kzbm = hm.get("kzbm");
			kzlm = hm.get("kzlm");
			i=Integer.parseInt(hm.get("xh"));
			i=i-1;//xh从1开始，所以减1换成下标
			//如果已经有扩展类，则不再记录
			if (StringUtils.isNull(this.kzbm)
					&& StringUtils.isNotNull(kzbm)) {
				this.kzbm = kzbm;
			}
			if (StringUtils.isNull(this.kzlm)
					&& StringUtils.isNotNull(kzlm)) {
				this.kzlm = kzlm;
			}
			if(i>=param.length){
				throw new RuntimeException("配置序号(xh)错误，超出对应列下标。");
			}
			param[i] = hm.get("lz").toString();
			//i++;
		}
		return param;
	}
}
