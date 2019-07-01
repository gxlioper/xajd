/**
 * @部门:学工产品事业部
 * @日期：2014-2-24 下午05:11:48 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: 默认解析规则生成器
 * @作者： 张昌路[工号:982]
 * @时间： 2014-2-24 下午05:11:48
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class PreviewImp implements IGenerate {
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf) {
		StringBuffer str = new StringBuffer();
		//规则配置信息
		String xxlx = ftf.getXxlx();
		String zd = ftf.getTjszzd();
		String ylzpz = fgtxf.getYlz();
		String xxz = fgtxf.getXxz();
		String wsbl = fgtxf.getWsbl();// 末尾补零标志
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		str.append(ylzpz);
//		if (TJ_ZDY.equals(zd)) {
//			// 有预览值
//			if (StringUtils.isNotNull(fgtxf.getSfkxg())) {
//				str.append(fgtxs.formateYlz(ylzpz, false));
//			} else {
//				str.append(fgtxf.getXxz());
//			}
//		} else if (TJ_LSH.equals(zd)) {
//			
//			//补零暂时由 位数补零操控
//			/*
//			 String lsh = "";
//			 String ws =xxz;
//			if(_XXLX_SELECT.equals(xxlx)){
//				ws=getSelectValue(xxz, ftf.getXxz());
//			}
//			for (int i = 1; i < Integer.parseInt(ws); i++) {
//				lsh += "0";
//			}
//			str.append(lsh + "1");*/
//			str.append("1");
//		} else if (_XXLX_DEFUALTE.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//
//				str.append(fgtxs.formateYlz(ylzpz, false));
//			}
//		} else if (_XXLX_TEXT.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//
//				str.append(fgtxs.formateYlz(ylzpz, false));
//			}
//		} else if (_XXLX_SELECT.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//				String sjylz = fgtxs.formateYlz(ylzpz, false);
//				Integer xxzI = StringUtils.paseStringToInteger(xxz);
//				Integer length = xxzI > sjylz.length() ? sjylz.length() : xxzI;
//				// 按选项值截取所需位数
//				str.append(sjylz.substring(sjylz.length() - length, sjylz
//						.length()));
//			}
//		} else if (_XXLX_REGION.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//				String sjylz = fgtxs.formateYlz(ylzpz, false);
//				String qzj[] = xxz.split(_QJ_SPLIT);
//				Integer start = StringUtils.paseStringToInteger(qzj[0]);
//				Integer length = StringUtils.paseStringToInteger(qzj[1]);
//				Integer end = start + length > sjylz.length() ? sjylz.length()
//						: start + length;
//				// 按区间值截取所需位数
//				String nowYlz=sjylz.substring(start, end);
//				//已由位数补零操控，暂时注释
//				/*//补足位数
//				for(int i=0;i<length-nowYlz.length();i++){
//					str.append("0");
//				}*/
//				str.append(nowYlz);
//			}
//		}
//		// 是否补零
//		if (SFBL_BL.equals(wsbl)) {
//			// 选项值存放
//			Integer ws = StringUtils.paseStringToInteger(xxz);
//			if (str.length() < ws) {
//				// 不足自动补零
//				int cs = ws - str.length();
//				for (int i = 0; i < cs; i++) {
//					str.insert(0, "0");
//				}
//			}
//		}
		//处理存在的‘null’字符串
		String code=str.toString(); 
		code=StringUtils.isNull(code)||"null".equals(code)?"":code;
		return code;
	}
	private String getSelectValue(String select,String xxz){
		//暂时下拉 key和值匹配，例如 2：2位 直接取key
		return select;
	}
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf,
			Object obj) {
		return getCode(ftf, fgtxf);
	}
	/*
	      描述: @see com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate#getErrorMessage()
	 */
	
	public String getErrorMessage() {
		// TODO 自动生成方法存根
		return null;
	}
}
