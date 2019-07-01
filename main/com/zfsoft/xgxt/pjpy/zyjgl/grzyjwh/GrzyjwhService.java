/**
 * @部门:学工产品事业部
 * @日期：2015-12-22 上午10:09:25 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-专业奖管理-个人专业奖维护
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2015-12-22 上午10:09:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GrzyjwhService extends SuperServiceImpl<GrzyjwhForm, GrzyjwhDao> {
	private GrzyjwhDao dao = new GrzyjwhDao();
	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public GrzyjwhService() {
		super.setDao(dao);
	}
	
	//增加保存
	public boolean saveGrzyj(GrzyjwhForm model) throws Exception {
		boolean insertResult = dao.runInsert(model);
		return insertResult;
	}
	
	//修改保存
	public boolean updateGrzyj(GrzyjwhForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	//删除
	public int delGrzyj(String[] ids) throws Exception {
		return runDelete(ids);
	}
	//得到评定等级
	public List<HashMap<String, String>>getRddjList() {
		return dao.getRddjList();
	}
	
}
