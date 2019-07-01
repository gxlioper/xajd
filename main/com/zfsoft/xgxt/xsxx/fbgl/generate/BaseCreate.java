/**
 * @部门:学工产品事业部
 * @日期：2014-3-17 上午09:54:49 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理模块
 * @类功能描述: 基础生成类
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-17 上午09:54:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public abstract class BaseCreate implements IOrderBy {
	protected static final String _FB_SPLIT = ":";
	protected static final String _XHGZ_XY = "1";//按照学院取流水号
	protected static final String _XHGZ_ZY = "2";//按照专业取流水号
	protected static final String _XHGZ_BJ = "3";//按照班级取流水号
	private StringBuffer gzSql = new StringBuffer();
	// 子类主sql所需参数
	private List<String> paramList = new ArrayList<String>();
	// 其他子类所需参数
	private Map<String, String> mapParam = new HashMap<String, String>();

	/**
	 * 根据‘排序规则’获取排序后的数据
	 */
	public List<HashMap<String, String>> getGroupByData(
			List<HashMap<String, String>> tjPzxx) {
		gzSql.append(getBaseSql());
		String xxz;
		String tjszzd;
		String xxzs[];
		int i = 0;
		for (HashMap<String, String> hm : tjPzxx) {
			xxz = hm.get("xxz");
			tjszzd = hm.get("tjszzd");
			if (i == 0) {
				gzSql.append(" order by bjdm asc,");
			}
			gzSql.append(tjszzd);
			if (StringUtils.isNotNull(xxz)) {
				xxzs = xxz.split(_FB_SPLIT);
				gzSql.append(" ");
				gzSql.append(xxzs[0]);
			}
			if (i + 1 != tjPzxx.size()) {
				gzSql.append(",");
			}
			i++;
		}
		return DAO.getInstance().getListNotOut(gzSql.toString(),
				getParam().toArray(new String[] {}));
	}

	public List<String> getParam() {
		return paramList;
	}

	public void addParam(String param) {
		paramList.add(param);
	}

	public void addAllParam(List<String> paramList) {
		paramList.addAll(paramList);
	}

	/**
	 * 
	 * @描述: 根据规则和具体数据生成对应数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午02:20:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param bj
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public abstract List<HashMap<String, String>> generate(String pzgzid,
			List<HashMap<String, String>> bj);

	/**
	 * 
	 * @描述: 根据规则和具体数据生成对应数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午02:20:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param bj
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> generate(String pzgzid) {
		return generate(pzgzid, null);
	}

	/**
	 * 
	 * @描述:根据规则和具体参数生成
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-9 下午03:32:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param data
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> generate(String pzgzid, Object data) {
		return generate(pzgzid, (List<HashMap<String, String>>) data);
	}

	/**
	 * 
	 * @描述: 获取具体规则信息配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午02:20:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public abstract List<HashMap<String, String>> getTJpzxxId(String pzgzid);

	/**
	 * @return the mapParam
	 */
	public Map<String, String> getMapParam() {
		return mapParam;
	}

	public String getMapParam(String key) {
		return mapParam.get(key);
	}

	/**
	 * @param mapParam要设置的
	 *            mapParam
	 */
	public void putMapParam(String key, String value) {
		mapParam.put(key, value);
	}
}
