/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:49:10 
 */  
package com.zfsoft.xgxt.xsxx.djjd.cssz;

import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import common.newp.StringUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 技术等级鉴定 --参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-11 下午04:16:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DjjdCsszService extends SuperServiceImpl<DjjdCssz, DjjdCsszDao> implements Constants{

	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String startTime = " 00:00:00";
	private static final String endTime = " 23:59:59";
	
	public DjjdCssz getModel() throws Exception{
		
		DjjdCssz model = dao.getModel();
		
		if (model == null){
			model = new DjjdCssz();
			model.setSqkg(CLOSE);
		} else {
			
			String nowTime = GetTime.getTimeByFormat(DATE_FORMAT);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Date now = sdf.parse(nowTime);
			
			if (CLOSE.equals(model.getSqkg())){
				model.setSqkg(CLOSE);
			} else if (!StringUtil.isNull(model.getSqkssj()) && StringUtil.isNull(model.getSqjssj())){
				Date start = sdf.parse(model.getSqkssj()+startTime);
				model.setSqkg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getSqkssj()) && !StringUtil.isNull(model.getSqjssj())){
				Date end = sdf.parse(model.getSqjssj()+endTime);
				model.setSqkg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getSqkssj()) && !StringUtil.isNull(model.getSqjssj())){
				Date start = sdf.parse(model.getSqkssj()+startTime);
				Date end = sdf.parse(model.getSqjssj()+endTime);
				model.setSqkg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setSqkg(OPEN);
			}
		}
		
		return model;
	}
}
