/**
 * @部门:学工产品事业部
 * @日期：2013-4-25 上午08:49:23 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版--家庭情况调查 基础设置
 * @作者： Penghui.Qu 
 * @时间： 2013-4-25 上午08:49:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtqkdcJcszService extends
		SuperServiceImpl<JtqkdcJcszForm, JtqkdcJcszDao> {

	private JtqkdcJcszDao dao = new JtqkdcJcszDao();
	private static final String CLOSE = "0";
	
	public JtqkdcJcszService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述:查询家庭情况调查基础设置
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 上午09:03:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * JtqkdcJcszForm 返回类型 
	 * @throws
	 */
	public JtqkdcJcszForm getModel(){
		
		try {
			JtqkdcJcszForm model = dao.getModel();
			
			if (CLOSE.equals(model.getSqkg())){
				//false
				model.setIsOpen(String.valueOf(false));
			} else if (!StringUtil.isNull(model.getSqjssj())){
				
				Date startTime = new Date();
				Date endTime = new Date();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
				if(!StringUtils.isBlank(model.getSqkssj())){
					startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
				}
				if(!StringUtils.isBlank(model.getSqjssj())){
					endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
				}
				
				String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
				Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
				
				if ((startTime != null&&now.before(startTime)) ||(endTime!=null&& now.after(endTime))){
					//false
					model.setIsOpen(String.valueOf(false));
				} else {
					//true
					model.setIsOpen(String.valueOf(true));
				}
			} else {
				//true
				model.setIsOpen(String.valueOf(true));
			}
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean runInsert(JtqkdcJcszForm t) throws Exception {
		
		//先删 后增
		boolean flg = dao.delJcsz();
		
		if (flg){
			return super.runInsert(t);
		}
		
		return flg;
	}
	
	
	
}
