/**
 * @部门:学工产品事业部
 * @日期：2014-2-24 下午04:37:43 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: 规则生成接口
 * @作者： 张昌路[工号:982]
 * @时间： 2014-2-24 下午04:37:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public interface IGenerate {
	// 0：无；1：文本；2：下拉框;3:起止位
	String _XXLX_DEFUALTE = "0";
	String _XXLX_TEXT = "1";
	String _XXLX_SELECT = "2";
	String _XXLX_REGION = "3";
	// 预览参数分隔符
	String _YLZ_SPLIT = "-";
	// 区间分隔符
	String _QJ_SPLIT = "~";
	/**
	 * 是否可修改 可修改
	 */
	String _SFKXG_KXG = "1";
	/**
	 * 自定义
	 */
	String TJ_ZDY = "TJ_ZDY";
	/**
	 * 流水号
	 */
	String TJ_LSH = "TJ_LSH";
	/**
	 * 特殊字段
	 */
	String _TSZD = "TJ";
	/**
	 * 位数不足自动补零
	 */
	String SFBL_BL = "1";

	/**
	 * 
	 * @描述: 根据配置规则获取 班级代码（预览）
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-27 上午09:01:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ftf
	 * @param fgtxf
	 * @return String 返回类型
	 */
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf);

	/**
	 * 
	 * @描述: 根据规则配置和页面设定值生成班级代码
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-27 上午09:02:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ftf
	 * @param fgtxf
	 * @param fbf
	 * @return String 返回类型
	 */
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf, Object data);

	/**
	 * 
	 * @描述: 获取错误信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-11 下午03:29:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 */
	public String getErrorMessage();
}
