/**
 * @部门:学工产品事业部
 * @日期：2014-11-21 下午05:31:58 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjcsz;

import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 绿色通道基础设置
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2014-11-21 下午05:31:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdJcszService extends SuperServiceImpl<LstdJcszForm, LstdJcszDao> {
	
	private LstdJcszDao dao = new LstdJcszDao();
	
	public LstdJcszService() {
		super.setDao(dao);
	}
	
	
	/**
	 * 查询基础设置信息
	 */
	public LstdJcszForm getModel(LstdJcszForm t)throws Exception{
		
		LstdJcszForm  model= dao.getModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (model != null) {
			
			if (Constants.CLOSE.equals(model.getSqkg())){
				model.setIsopen("false");
			} else if (!StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
				
				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
				
				if(now.before(startTime)||now.after(endTime)){
					model.setIsopen("false");
				} else{
					model.setIsopen("true");
				}
					
			} else if (StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
				
				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
				if(now.after(endTime)){
					model.setIsopen("false");
				} else{
					model.setIsopen("true");
				}
				
			} else if (!StringUtil.isNull(model.getSqkssj())&&StringUtil.isNull(model.getSqjssj())){
				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
				if(now.before(startTime)){
					model.setIsopen("false");
				} else{
					model.setIsopen("true");
				}
			}else {
				model.setIsopen("true");
			}
		}
		return model;
	}
	

	/**
	 * 
	 * @描述:获取model
	 * @作者：cq [工号：785]
	 * @日期：2014-11-24 下午05:44:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * LstdJcszForm 返回类型 
	 * @throws
	 */
    public LstdJcszForm getModel()throws Exception{
		
		return getModel(new LstdJcszForm());
	}
	
   /**
    * 
    * @描述:TODO(这里用一句话描述这个方法的作用)
    * @作者：cq [工号：785]
    * @日期：2014-11-24 下午05:44:37
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param model
    * @return
    * @throws Exception
    * boolean 返回类型 
    * @throws
    */
	public boolean saveJcsz(LstdJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteLstdJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
		
	}

}
