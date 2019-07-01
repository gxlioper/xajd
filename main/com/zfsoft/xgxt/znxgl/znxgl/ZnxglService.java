/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:54:19 
 */  
package com.zfsoft.xgxt.znxgl.znxgl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.znxgl.fxbgl.FxbService;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  yxy[工号:1206]
 * @时间： 2015-8-27 下午06:54:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZnxglService extends SuperServiceImpl<ZnxglForm, ZnxglDao> {
	private FxbService fxbservice = new FxbService();
	
	//管理员信件查看
	public HashMap<String, String> glyXjckMap(ZnxglForm t){
		return dao.glyXjckMap(t);
	}
	
	//获取clob字段（信件发送内容）
	public String getFsnr_Clob(String xjbh) throws Exception{
		return dao.getFsnr_Clob(xjbh);
	}
	
	//信件分配时删除分配人原有的接受记录
	public boolean delFprJsjl(ZnxglForm t) throws Exception{
		return dao.delFprJsjl(t);
	}
	
	//站内信提醒
	public String getZnxTx(String username){
		return dao.getZnxTx(username);
	}
	//更新收信表查看标志
	public boolean runUpate_sxbckjl(ZnxglForm sx)throws Exception{
		return dao.runUpate_sxbjl(sx);
	}
	//更新收信表删除标志
	public boolean runUpate_sxbscjl(ZnxglForm sx)throws Exception{
		return dao.runUpate_sxbscjl(sx);
	}
	
	//更新原收到信件的接收人编号
	public boolean runUpdate_znxsjx(ZnxglForm sx) throws Exception{
	    return dao.runUpdate_znxsjx(sx);
	}
	
	/**
	 * 
	 * @描述:保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-7 下午03:19:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sx
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean save(ZnxglForm sx) throws Exception{
	    return dao.save(sx);
	}
	
}


