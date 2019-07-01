/**
 * @部门:学工产品事业部
 * @日期：2016-2-19 上午10:13:17 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理假期返校基本设置管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Dulq[工号:995]
 * @时间： 2016-2-19 上午10:13:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

@SuppressWarnings("unused")
public class JqfxjcszService extends SuperServiceImpl<JqfxjcszForm,JqfxjcszDao>{
	
    private JqfxjcszDao dao=new JqfxjcszDao();
	
	@SuppressWarnings("deprecation")
	public JqfxjcszService() {
		super.setDao(dao);
	}
	
	
	/**
	 * 查询基础设置信息
	 */
	public JqfxjcszForm getModel(JqfxjcszForm t)throws Exception{
		
		return dao.getModel();
		
	}
	
	/**
	 * 
	 * @描述:TODO(从返校代码维护表里取出代码和名称)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-25 下午02:52:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFxmcList(){
		
		List<HashMap<String,String>> list =  dao.getfxmcList();		
		return list;	
		
	}
	
	 /**
	  * 
	  * @描述:TODO(这里用一句话描述这个方法的作用)
	  * @作者：杜利骑[工号：995]
	  * @日期：2016-3-11 上午09:32:45
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * @throws Exception
	  * JqfxjcszForm 返回类型 
	  * @throws
	  */
    public JqfxjcszForm getModel()throws Exception{
		
		return getModel(new JqfxjcszForm());
	}
	
   /**
    * 
    * @描述:TODO(这里用一句话描述这个方法的作用)
    * @作者：杜利骑[工号：995]
    * @日期：2016-3-11 上午09:32:56
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param model
    * @return
    * @throws Exception
    * boolean 返回类型 
    * @throws
    */
	public boolean saveJcsz(JqfxjcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;		
	}

	 
}
