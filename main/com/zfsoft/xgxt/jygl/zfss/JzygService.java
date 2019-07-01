/**
 * @部门:学工产品事业部
 * @日期：2013-6-8 下午02:16:24 
 */  
package com.zfsoft.xgxt.jygl.zfss;

import java.util.HashMap;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 社区管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： huj
 * @时间： 2013-6-8 下午02:16:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JzygService extends SuperServiceImpl<JzygForm, JzygDao> {

	private JzygDao dao = new JzygDao();
	
	public JzygService(){
		super.setDao(dao);
	}

	public HashMap<String,String> getXsjbxx(String zgh){
		return dao.getJzygxx(zgh);
	}

}
