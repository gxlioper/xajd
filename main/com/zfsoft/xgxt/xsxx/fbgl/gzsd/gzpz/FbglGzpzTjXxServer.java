/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:10:46 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:10:46
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzpzTjXxServer extends
		SuperServiceImpl<FbglGzpzTjXxForm, FbglGzpzTjXxDao> {
	FbglGzpzTjXxDao dao = new FbglGzpzTjXxDao();
	// 分班规则 指定代码
	public static final String _FBGZ_CODE = "FBGZ_PJFP";
	// 2：下拉框;
	public static final String _XXLX_XLK = "2";
	// 3:起止位
	public static final String _XXLX_QZW = "3";
	// 可修改
	public static final String _SFKXG_KXG = "1";

	public static final String _SFKXG_SPLIC="-";
	
	public FbglGzpzTjXxServer() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @描述: 规则详细配置信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午11:17:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid) {
		return dao.getTJpzxxId(pzgzid);
	}
	/**
	 * 
	 * @描述: 根据条件规则id获取配置详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午11:18:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid,String tjgzid){
		return dao.getTJpzxxId(pzgzid,tjgzid);
	}
	/**
	 * 
	 * @描述: 根据配置规则id获取配置的条件规则类型
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-27 上午10:59:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjgzxx(String pzgzid){
		return dao.getTjgzxx(pzgzid);
	}
	/**
	 * 
	 * @描述: 规则配置对应的条件规则
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 下午02:41:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzpzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getGzpzTjxxLx(String gzpzid) {
		return dao.getGzpzTjxxLx(gzpzid);
	}

	/**
	 * 
	 * @描述: 根据规则类型获取规则配置详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 下午02:49:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @param gzpzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getGzpzTjxxForLx(String tjgzid,
			String gzpzid) {
		return getGzpzTjxxForLx(tjgzid, gzpzid, true);
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-21 下午02:38:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getKxgxx(String pzgzid) {
		return formateGzpz(dao.getXgXx(pzgzid, _SFKXG_KXG));
	}
	/**
	 * 
	 * @描述: 格式化规则配置信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-21 下午03:15:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> formateGzpz(
			List<HashMap<String, String>> list) {
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> hm : list) {
			String ylz = hm.get("ylz");
			hm.put("ylzstr", ylz);
			newList.add(hm);
		}
		return newList;
	}
	/**
	 * 
	 * @描述: 格式化预览值(包含字段名称)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-21 下午03:14:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ylz
	 * @return
	 * String 返回类型 
	 */
	public String formateYlz(String ylz) {
		return formateYlz(ylz,true);
	}
	/**
	 * 
	 * @描述: 格式化预览值
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-21 下午03:14:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ylz
	 * @param appendZd 是否包含字段名称
	 * @return
	 * String 返回类型 
	 */
	public String formateYlz(String ylz,boolean appendZd) {
		// 预览值规则：表明,字段，关联字段 xsxx,xymc,xydm
		if (StringUtils.isNotNull(ylz)) {
			String ylzs[] = ylz.split("-");
			if (ylzs.length != 3) {
				throw new RuntimeException("预览值配置错误,参数超出！请检查。");

			}
			StringBuffer sb = new StringBuffer();
			sb.append("select ");
			sb.append(ylzs[1]);
			sb.append(" ");
			sb.append("from ");
			sb.append(ylzs[0]);
			sb.append(" order by ");
			sb.append(ylzs[1]);
			sb.append(" nulls last");
			try {
				ylz = DAO.getInstance().getOneRs(sb.toString(), new String[] {},
						ylzs[1]);
			} catch (Exception e) {
				throw new RuntimeException("预览值配置错误，不存在对应表或字段！请检查。");
			}
			//是否返回包含字段名称
			if(appendZd){
				ylz += _SFKXG_SPLIC + ylzs[2]+_SFKXG_SPLIC+ylzs[1];
			}
		}
		return ylz;
	}
	/**
	 * 
	 * @描述: 格式化选项值设置集合（转换为map）
	 * @作者：张昌路[工号：982]
	 * @日期：2014-6-9 下午06:45:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxzsz
	 * Map<String,String> 返回类型
	 */
	private Map<String,String> fomartXxz(String xxzsz){
		Map<String,String> map=new HashMap<String, String>();
		if(StringUtils.isNull(xxzsz)){
			map.put(xxzsz, xxzsz);
			return map;
		}
		//去掉前后的{}
		xxzsz=xxzsz.replace("{", "");
		xxzsz=xxzsz.replace("}", "");
		//{2:2位,4:4位}
		String xxzszs[]=xxzsz.split(",");
		String xxzs[];
		for(String xxz:xxzszs){
			xxzs=xxz.split(":");
			if(null!=xxzs&&xxzs.length>0){
				map.put(xxzs[0], xxzs[1]);
			}
		}
		return map;
	}
	/**
	 * 
	 * @描述: 根据类型获取规则配置信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 上午09:12:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 *            类型id
	 * @param gzpzid
	 *            规则配置id
	 * @param isFormat
	 *            是否格式化数据为文本模式
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getGzpzTjxxForLx(String tjgzid,
			String gzpzid, boolean isFormat) {
		List<HashMap<String, String>> oldList = dao.getGzpzTjxxForLx(tjgzid,
				gzpzid);
		if (!isFormat) {
			return oldList;
		}
		Map<String,String> xxzMap=null;
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int i = 0;
		for (HashMap<String, String> hm : oldList) {
			// String xxzsz=hm.get("xxzsz");
			// 处理数据转换显示文本
			String xxlx = hm.get("xxlx");
			if (StringUtils.isNotNull(xxlx)) {
				// 2：下拉框;3:起止位
				if (xxlx.equals(_XXLX_XLK)) {
					// {2:2位,4:4位}
					if (_FBGZ_CODE.equals(hm.get("tjgzid"))) {
						hm.put("xxz", hm.get("xxz").split(":")[1]);
					} else {
						xxzMap=fomartXxz(hm.get("xxzsz"));
						hm.put("xxz", xxzMap.get(hm.get("xxz")));
					}
				} else if (xxlx.equals(_XXLX_QZW)) {
					String[] xxzs = hm.get("xxz").split("~");
					hm.put("xxz", "起止位：" + xxzs[0] + ";位数：" + xxzs[1]);
				}
			}
			// 合并显示
			StringBuffer tjxz = new StringBuffer();
			if (i != 0) {
				tjxz.append("&nbsp;&nbsp;+");
			}
			tjxz.append(hm.get("tjszmc"));
			// 选项限制
			if (StringUtils.isNotNull(hm.get("xxz"))) {
				tjxz.append("[" + hm.get("xxz") + "]");
			}
			// 是否补零
			if (StringUtils.isNotNull(hm.get("wsblt"))) {
				tjxz.append("[" + hm.get("wsblt") + "]");
			}
			// 是否可修改
			if (StringUtils.isNotNull(hm.get("sfkxgt"))) {
				tjxz.append("[" + hm.get("sfkxgt") + "]");
			}
			hm.put("tjxz", tjxz.toString());
			i++;
			list.add(hm);
		}
		return list;
	}

	/**
	 * 
	 * @描述: 获取对应序号的信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 下午03:06:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzpzid
	 * @param sx
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public HashMap<String, String> getGzpzTjgz(String pzgzid, String tjgzid,
			String tjszzd, String sx) {
		return dao.getGzpzTjgz(pzgzid, tjgzid, tjszzd, sx);
	}

	public boolean save(FbglGzpzTjXxForm ff) {
		try {
			return runInsert(ff);
		} catch (Exception e) {
			throw new RuntimeException("保存条件规则详细信息失败!");
		}
	}
}
