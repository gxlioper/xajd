/**
 * @部门:学工产品事业部
 * @日期：2018-4-20 下午03:46:01 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：lgx[工号:1553]
 * @时间： 2018-4-20 下午03:46:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@SuppressWarnings("unused")
public class JqfxJbszService  extends SuperServiceImpl<JqfxJbszForm,JqfxJbszDao>{
	
    private JqfxJbszDao dao=new JqfxJbszDao();
	
	@SuppressWarnings("deprecation")
	public JqfxJbszService() {
		super.setDao(dao);
	}
	
	
	/**
	 * 查询基础设置信息
	 */
	public JqfxJbszForm getModel(JqfxJbszForm t)throws Exception{
		
		return dao.getModel();
		
	}
	
	/**
	 * 
	 * @描述:TODO(从返校代码维护表里取出代码和名称)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午03:46:01 
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
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午03:46:01 
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * @throws Exception
	  * JqfxJbszForm 返回类型 
	  * @throws
	  */
    public JqfxJbszForm getModel()throws Exception{
		
		return getModel(new JqfxJbszForm());
	}
	
   /**
    * 
    * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午03:46:01 
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param model
    * @return
    * @throws Exception
    * boolean 返回类型 
    * @throws
    */
	public boolean saveJbsz(JqfxJbszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;		
	}

	 
}
