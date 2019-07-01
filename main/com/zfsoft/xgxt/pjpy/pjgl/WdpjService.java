/**
 * @部门:学工产品事业部
 * @日期：2013-5-24 下午02:40:48 
 */  
package com.zfsoft.xgxt.pjpy.pjgl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优之我的评奖 
 * @作者： zhangjw 
 * @时间： 2013-5-24 下午02:24:52 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WdpjService   extends SuperServiceImpl<WdpjForm, WdpjDAO>{

	private WdpjDAO dao = new WdpjDAO();
	/**
	 * @描述:荣誉证书打印
	 * @作者：zhangjw
	 * @日期：2013-5-24 下午04:51:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File getRyzsWord(String xh)throws Exception {

		Map<String,Object> data = new HashMap<String,Object>();
		StringBuffer fileName = new StringBuffer();
		if (!StringUtils.isNull(xh)){
			if(xh == null || "".equals(xh)){
				return null;
			}
			HashMap<String,String> map = dao.getRyzsdyXX(xh);
			//定义输出文件名称
			fileName.append(map.get("xm")).append("(").append(xh).append(")").append(map.get("xmmc"));
			if(map!=null){
				data.put("xm", map.get("xm"));
				data.put("xmmc", map.get("xmmc"));
				data.put("nd",map.get("xn"));
				String rq = DateTranCnDate.dateToCnDateSubMonth(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				data.put("sj",rq);
			}else{
				return null;
			}
		}
		
		File wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//pjpy","yrzsdy.xml",fileName.toString());
		return wordFile;
	}
}
