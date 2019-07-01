package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车优惠卡管理基本设置模块
 * @类功能描述: TODO(火车优惠卡管理基本设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午02:55:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcyhkJcszService extends SuperServiceImpl<HcyhkJcszForm, HcyhkJcszDao>{
	
	private HcyhkJcszDao dao = new HcyhkJcszDao();
	
	public HcyhkJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public HcyhkJcszForm getModel(HcyhkJcszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 
	 * @描述:TODO(查询基础设置信息(无参数))
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:46:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HcyhkJcszForm 返回类型 
	 * @throws
	 */
    public HcyhkJcszForm getModel()throws Exception{
		
		return getModel(new HcyhkJcszForm());
	}
	
    /**
     * 
     * @描述:TODO(保存参数设置信息)
     * @作者：Dlq[工号：995]
     * @日期：2013-12-26 上午09:47:07
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param model
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
	public boolean saveJcsz(HcyhkJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
			
		}
		return flag;
		
	}
}
