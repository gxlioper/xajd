/**
 * @部门:学工产品事业部
 * @日期：2016-11-15 下午03:08:55 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.hsdxd;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-15 下午03:08:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HsdxdService extends SuperServiceImpl<HsdxdForm, HsdxdDao> {
	/**
	 * 
	 * @描述:获取续贷信息明细列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午04:00:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXdxxMx(String xh){
		return dao.getXdxxMx(xh);
	}
	
	/**
	 * 
	 * @描述: 贷款信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午04:46:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDkxxMap(String xh){
		return dao.getDkxxMap(xh);
	}
	
	/**
	 * 
	 * @描述: 贷款结果数量验证
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午05:29:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDkjgNum(String xh){
		return dao.getDkjgNum(xh);
	}
	
	/**
	 *
	 * @描述: 放贷金额总和
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午05:47:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFdjeZh(String xh){
		return dao.getFdjeZh(xh);
	}
	
	/**
	 * 
	 * @描述: 获取续贷申请数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-17 上午11:42:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
    public List<HashMap<String, String>> getXdsqList(String xh){
    	return dao.getXdsqList(xh);
    }
    
    /**
     * @throws Exception 
     * 
     * @描述:插入数据到申请表
     * @作者：yxy[工号：1206]
     * @日期：2016-11-17 下午02:42:53
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean insertInToXdb(HsdxdForm t) throws Exception{
    	return dao.insertInToXdb(t);
    }
    
    /**
     * @throws Exception 
     * 
     * @描述: 放弃续贷
     * @作者：yxy[工号：1206]
     * @日期：2016-11-18 上午09:15:54
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean qxsq(String jgid) throws Exception{
    	return dao.qxsq(jgid);
    }
    
    /**
     * @throws Exception 
     * 
     * @描述: 续贷审核通过插入放贷表
     * @作者：yxy[工号：1206]
     * @日期：2016-11-18 下午05:32:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param id
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean insertIntoFdb(String id) throws Exception{
    	return dao.insertIntoFdb(id);
    }
    
    /**
     * @throws Exception 
     * 
     * @描述:续贷审核撤销之后删除放贷表数据
     * @作者：yxy[工号：1206]
     * @日期：2016-11-18 下午05:40:18
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean delFdb(String id) throws Exception{
    	return dao.delFdb(id);
    }
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * 
     * @描述: 更新放贷表
     * @作者：yxy[工号：1206]
     * @日期：2016-11-21 上午09:30:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param t
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean updateFdb(HsdxdForm form) throws Exception{
    	return dao.updateFdb(form);
    	
    }
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * 
     * @描述: 获取放贷表实体model
     * @作者：yxy[工号：1206]
     * @日期：2016-11-21 上午10:32:15
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * HsdxdForm 返回类型 
     * @throws
     */
    public HsdxdForm getFdbModel(String id) throws Exception{
        return dao.getFdbModel(id);
        	
        
    }
    
    /**
     * 
     * @描述: 根据合同编号获取贷款账号
     * @作者：yxy[工号：1206]
     * @日期：2016-11-22 下午05:15:34
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * String 返回类型 
     * @throws
     */
    public String getDkzh(String htbh){
    	return dao.getDkzh(htbh);
    }
    
    /**
     * 
     * @描述:华师大个性化判断，如果已有放贷记录，助学贷款申请不允许删除，审核不允许撤销
     * @作者：yxy[工号：1206]
     * @日期：2016-11-23 上午09:26:09
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param htbh
     * @return
     * String 返回类型 
     * @throws
     */
    public String getfdNum(String htbh){
    	return dao.getfdNum(htbh);
    }
    
    /**
     * 
     * @描述: 获取放贷金额
     * @作者：yxy[工号：1206]
     * @日期：2016-11-23 上午10:56:35
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param jgid
     * @return
     * String 返回类型 
     * @throws
     */
    public String getFdjes(String jgid){
    	return dao.getFdjes(jgid);
    }
}
