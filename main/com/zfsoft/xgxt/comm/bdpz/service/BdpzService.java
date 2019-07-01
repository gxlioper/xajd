package com.zfsoft.xgxt.comm.bdpz.service;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.comm.bdpz.dao.BdpzDao;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版，动态表单相关操作
 * @作者： Penghui.Qu 
 * @时间： 2013-4-18 下午04:39:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BdpzService {

	
	private BdpzDao dao = new BdpzDao();
	
	
	/**
	 * 资助项目学生基本信息配置
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String,String>> getJbxxpz(String gnmk){
		
		if (StringUtil.isNull(gnmk)){
			throw new NullPointerException();
		}
		
		return dao.getJbxxpz(gnmk);
	}
	
	
	
}
