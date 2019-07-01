/**
 * @部门:学工产品(1)部
 * @日期：2017-4-20 上午09:15:38 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-基本设置-项目设置-报表设置
 * @类功能描述: 登记表、上报表设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-20 上午09:16:35 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbszService extends SuperServiceImpl<BbszForm,BbszDao>{
	private BbszDao dao = new BbszDao();
	
	public BbszService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 查询登记表相关信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-20 上午10:27:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bblx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		return dao.getBbxxList(bblx);
	}
	
	/**
	 * @描述: 报表预览
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-20 下午03:38:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxYlList(String bbdm){
		return dao.getBbxxYlList(bbdm);
	}
}
