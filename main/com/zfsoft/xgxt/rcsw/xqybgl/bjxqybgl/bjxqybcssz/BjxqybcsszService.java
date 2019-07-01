/**
 * @部门:学工产品事业部
 * @日期：2016-3-23 上午11:51:32 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_班级月报管理模块
 * @类功能描述: TODO(北京中医药_班级月报_参数设置) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-23 上午11:51:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class BjxqybcsszService extends
		SuperServiceImpl<BjxqybcsszForm, BjxqybcsszDao> {
	
	private BjxqybcsszDao dao = new BjxqybcsszDao();
	
	@SuppressWarnings("deprecation")
	public BjxqybcsszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(学情月报管理-班级月报申请-参数设置-查询参数设置信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-23 下午03:57:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * BjxqybcsszForm 返回类型 
	 * @throws
	 */
	public BjxqybcsszForm getModel() throws Exception {		   
		return dao.getModel();
	}
	
	/**
	 * 
	 * @描述:TODO(学情月报管理-班级月报申请-参数设置-保存参数设置信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-23 下午05:07:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBjxqybcssz(BjxqybcsszForm t) throws Exception{		
		boolean flag = false;
		flag = dao.deleteBjxqybcssz(t);
		if(flag){
			flag = dao.runInsert(t);
		}		
		return flag;
	}
}
