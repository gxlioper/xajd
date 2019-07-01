/**
 * @部门:学工产品事业部
 * @日期：2014-1-16 上午10:53:38 
 */
package com.zfsoft.xgxt.base.export.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 导入
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-16 上午10:53:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ExportUtils {
	/**
	 * 
	 * @描述: map转换为string数组
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-16 上午10:58:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hm
	 * @return String[] 返回类型
	 */
	public static String[] MapToArray(HashMap<String, String> hm) {
		if (null == hm || hm.size() <= 0) {
			return null;
		}
		String[] param = new String[hm.size()];
		int i = 0;
		Iterator<Entry<String, String>> it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			param[i] = (String) entry.getValue();
			i++;
		}
		return param;
	}
}
