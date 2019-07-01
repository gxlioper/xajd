/**
 * @部门:学工产品事业部
 * @日期：2013-8-27 上午09:07:40 
 */  
package com.zfsoft.xgxt.xtwh.bjdl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 班级大类设置 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-8-27 上午09:07:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjdlService extends SuperServiceImpl<BjdlModel, BjdlDao> {

	private BjdlDao dao = new BjdlDao();
	
	public BjdlService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述: 设置班级大类
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-27 下午02:51:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean szBjdl(BjdlModel model,String[] bjdm) throws Exception{

		//初始化班级
		dao.initBjdl();
		
		if (bjdm == null || bjdm.length == 0){
			throw new NullPointerException("bjdm is null !");
		}
		
		String lbdm = model.getLbdm();
		
		if (StringUtil.isNull(lbdm)){
			lbdm = UniqID.getInstance().getUniqIDHash();
			dao.insertBjdl(lbdm, model.getLbmc());
		}
		
		
		return dao.szBjdl(lbdm, bjdm);
	}
	
	
	
	/**
	 * 
	 * @描述: 查询班级大类列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-27 下午02:59:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjdlList(){
		return dao.getBjdlList();
	}
	
	
	
	/**
	 * 
	 * @描述: 查询班级信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-27 下午03:03:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjxxList(BjdlModel model){
		
		String bjdm = model.getBjdm();
		
		if (StringUtil.isNull(bjdm)){
			throw new NullPointerException("bjdm is null !");
		}
		
		String[] bjdmArray = bjdm.split(",");
		
		return dao.getBjxxList(bjdmArray);
	}
	
	
	/**
	 * 
	 * @描述:取消班级大类
	 * @作者：cq [工号：785]
	 * @日期：2014-8-19 上午10:11:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxBjdl(String[] bjdm, BjdlModel model) throws Exception{
		return dao.qxBjdl(bjdm, model);
	}
	
}
