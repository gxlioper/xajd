/**
 * @部门:学工产品事业部
 * @日期：2013-7-2 下午06:55:35 
 */  
package com.zfsoft.xgxt.szdw.cssz;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:参数设置 service
 * @作者： zhangjw
 * @时间： 2013-7-2 下午06:53:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwCsszService extends SuperServiceImpl<SzdwCsszForm,SzdwCsszDAO>{

	private SzdwCsszDAO dao = new SzdwCsszDAO();
	
	public SzdwCsszService(){
		super.setDao(dao);
	}
	/**
	 * @描述:根据键获取流程配置信息
	 * @作者：zhangjw
	 * @日期：2013-7-3 下午04:33:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * @throws Exception
	 * SzdwCsszForm 返回类型
	 */
	public SzdwCsszForm getShlcsqkzb(String key) throws Exception{
		return dao.getModel(key);
	}
	/**
	 * @描述:验证参数设置
	 * @作者：zhangjw
	 * @日期：2013-7-4 下午02:29:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String yzCssz(String key) throws Exception{
		SzdwCsszForm cssz = getShlcsqkzb(key);
		String message = MessageUtil.getText("szdw_cssz_kg", cssz.getMs());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(!cssz.getKg().equals("1")){
		}else if(!(Integer.parseInt(sdf.format(new Date()))>=Integer.parseInt(cssz.getKssj()==null?"0":cssz.getKssj()) && Integer.parseInt(sdf.format(new Date()))<=Integer.parseInt(cssz.getJssj()==null?"99999999":cssz.getJssj()))){
		}else if(cssz.getSplc()==null || "".equals(cssz.getSplc()) ){
		}else{
			message = "true";
		}
		return message;
	}
}
