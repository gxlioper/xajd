/**
 * @部门:学工产品事业部
 * @日期：2015-10-8 上午09:27:21 
 */  
package com.zfsoft.xgxt.xtwh.ksdh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-10-8 上午09:27:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KsdhService extends SuperServiceImpl<KsdhForm, KsdhDao> {
	/**
	 * 
	 * @描述:TODO(首页我的应用入口菜单查询以及编辑窗口左边已设置项目查询)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-8 下午01:24:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
    private KsdhDao dao = new KsdhDao();
	public List<HashMap<String, String>> getGncdSzList(User user) throws Exception{
		//用户未编辑我的应用菜单入口时,给出默认值
		List<HashMap<String, String>> szzList = dao.getGncdSzList(user);
		if(szzList == null || szzList.size() == 0){
			szzList = dao.getGncdSzList_mr(user);
		}
		return szzList ;
		
	}
	
	//我的应用菜单是否有设置值判断
	public boolean checkExistSz(User user) throws Exception{
		String flag = dao.checkExistSz(user);
		return Integer.parseInt(flag)>0 ? true : false;
	}
	
	//首次编辑我的应用时插入数据
	public boolean insertInto_mrz(User user) throws Exception{
		return dao.insertInto_mrz(user);
	}
	
	//分类查询头
	public List<HashMap<String, String>> getFlcx_head(User user){
	    return dao.getFlcx_head(user);
	}
	
	//应用查询 @gnmkmc功能模块名称 @gnmkdm 功能分类代码
	public List<HashMap<String, String>> getFlcx(String usertype,String username){
		return dao.getFlcx(usertype, username);
	}
	
	
    /**
     * 
     * @描述:查询
     * @作者：yxy[工号：1206]
     * @日期：2015-10-19 上午11:31:40
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param usertype
     * @param gnmkmc
     * @param gnmkdm
     * @param username
     * @param htmlgnmkdm
     * @return
     * @throws Exception
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> QueryData(String usertype,String gnmkmc,String mkfldm,String username,String[] htmlgnmkdm) throws Exception{
    	return dao.QueryData(usertype, gnmkmc, mkfldm, username, htmlgnmkdm);
    }
    
    /**
     * 
     * @描述:保存时删除原有数据
     * @作者:yxy[工号：1206]
     * @日期：2015-10-19 下午01:42:10
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean del_originalData(User user) throws Exception{
    	return dao.del_originalData(user);
    }
    
    /**
     * 
     * @描述:单个用户功能授权保存之后删除快速导航表中的内容
     * @作者：yxy[工号：1206]
     * @日期：2015-10-23 下午03:04:17
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param yhm
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean del_Rubbish_data(String yhm){
    	return dao.del_Rubbish_data(yhm);
    }
    
    /**
     * 
     * @描述:用户组授权保存之后删除快速导航表中的内容
     * @作者：yxy[工号：1206]
     * @日期：2015-10-23 下午03:04:17
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param yhm
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean del_Rubbish_data_yhz(String yhm){
    	return dao.del_Rubbish_data_yhz(yhm);
    }
   
	
}
