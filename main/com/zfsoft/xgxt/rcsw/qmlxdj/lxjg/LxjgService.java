/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午09:07:29 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxjg;

import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午09:07:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxjgService extends SuperServiceImpl<LxjgForm, LxjgDao> {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";

	
	/**
	 * 
	 * @描述: 获取查看信息map
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:45:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCkxx(String id){
		return dao.getCkxx(id);
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:20:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxdjform
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJg(LxjgForm lxjgform,User user) throws Exception {
		lxjgform.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		String jgid = lxjgform.getJgid();
		boolean flag = true;
		if(StringUtils.isNotNull(jgid)){
			flag = dao.runUpdate(lxjgform);
			
		}else{
			flag = dao.runInsert(lxjgform);
		}
		 
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 审核通过之后删除结果表之后原有和审核通过记录重复的记录
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午11:33:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJgbyShTg(String xh,String xn,String xq) throws Exception{
		return dao.delJgbyShTg(xh, xn, xq);
	}

}
