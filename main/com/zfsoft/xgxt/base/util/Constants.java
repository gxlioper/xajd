/**
 * @部门:学工产品事业部
 * @日期：2013-4-26 上午08:59:48 
 */  
package com.zfsoft.xgxt.base.util;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公共常量类
 * @作者：maxh
 * @时间： 2013-4-26 上午08:59:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public interface Constants {
	
	public static final String OPEN = "1"; //开放
	public static final String CLOSE = "0"; //关闭
	
	// -----------NEW 审核流程 2013-12后-START-----------
	public static final String YW_WTJ = "0"; //未提交
	public static final String YW_TG = "1"; //通过
	public static final String YW_BTG = "2";//不通过
	public static final String YW_YTH = "3";//已退回
	public static final String YW_SHZ = "5";//审核中
	public static final String YW_BJPYZ = "6";//班级评议中
	public static final String YW_BJPYBTG = "7";//班级评议不通过
	
	public static final String SH_DSH = "0"; //待审核
	public static final String SH_TG = "1"; //通过
	public static final String SH_BTG = "2";//不通过
	public static final String SH_TH = "3";//退回
	public static final String SH_XCS = "4";//需重审
	// -----------NEW 审核流程 2013-12后-END-----------
	
	// -----------OLD 审核流程 2013-12前-START-----------
	public static final String WSH = "0"; //未审核
	public static final String TG = "1"; //通过
	public static final String BTG = "2";//不通过
	public static final String TH = "3";//退回
	public static final String XCS = "4";//需重审
	public static final String SHZ = "5";//审核中
	public static final String CANCEL = "9";//取消审核
	// -----------OLD 审核流程 2013-12前-END-----------
	
	// ----------- 已退回标记 -----------------------
	public static final String YTH = "1"; //已退回
	

	public static String RSKZFW_BJ = "bj"; //项目维护，人数控制范围，班级
	public static String RSKZFW_NJXY = "njxy"; //项目维护，人数控制范围，年级+学院
	public static String RSKZFW_NJZY = "njzy"; //项目维护，人数控制范围，年级+专业
	public static String RSKZFW_XY = "xy"; //项目维护，人数控制范围，学院
	public static String RSKZFW_ZY = "zy"; //项目维护，人数控制范围，专业
	public static String RSKZFW_CPZ = "cpz"; //项目维护，人数控制范围，专业
	public static String RSKZFW_XX = "xx"; //项目维护，人数控制范围，学校
	public static String RSKZFW_SY = "sy";//项目维护，人数控制范围，书院

	public static String FILE_TYPE_XLS = ".xls";//文件类型
	public static String FILE_TEMP_DIR = "temp";//文件临时目录
	
	public static String TEP_DIR = "classpath:templates/";//模板文件路径
	
	public static final String JIAFEN = "01"; //日常行为加分
	public static final String JIANFEN = "02"; //日常行为减分
	

	public static final String CANCLE_FLG_ERROR = "-1"; //审核撤销返回状态：-1：失败
	public static final String CANCLE_FLG_SUCCESS = "0"; //审核撤销返回状态：0：成功（非最后一级）
	public static final String CANCLE_FLG_SUCCESS_ZHYJ = "1"; //审核撤销返回状态：1：成功（最后一级）
	
	public static final String SHLC_SFKPZSHLC_KEYPZ="0";//可以配置审核流程
	public static final String SHLC_SFKPZSHLC_BKEYPZ="1";//不可配置审核流程
	public static final String SHLC_KSQKG_KQ="1";//开启申请
	
	public static final String SJLY_SPL = "1"; //数据来源：1[审批流]

	public static final String SJLY_JGK = "0"; // 数据来源：0[结果库]
	public static final String ZDYBD_JTCYXX = "jtcyxxList"; // 自定义表单，家庭成员信息取值标识
	public static final String ZDYBD_XLSHJLXX = "xlshjlxxList"; // 自定义表单，学历社会经历取值标示

}
