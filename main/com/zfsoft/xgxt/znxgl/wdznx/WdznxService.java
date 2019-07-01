/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:56:07 
 */
package com.zfsoft.xgxt.znxgl.wdznx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbService;
import com.zfsoft.utils.*;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-27 下午06:56:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WdznxService extends SuperServiceImpl<WdznxForm, WdznxDao> {
	private SxbService sxbservice = new SxbService();
	public List<HashMap<String, String>> getFjxList(WdznxForm t, User user)
			throws Exception {
		return dao.getFjxList(t, user);
	}
	
	//主题类别list
	public List<HashMap<String, String>> getZtlbList() throws Exception{
		return dao.getZtlbList();
	}
	
	//获取发送表中插入数据的xjbh,用于插入接收表中
	public HashMap<String,String> getXjbh(WdznxForm t){
		return dao.getXjbh(t);
	}
	
	//教师发信
	public boolean saveteaXX(WdznxForm model) throws Exception{
		 boolean result = true;
		 String[] jsrbh = model.getJsrbhs();
		 String xjbh = UniqID.getInstance().getUniqIDHash();
		 xjbh = xjbh.toUpperCase();
		    //发信表插入一条发信记录
		    if(model != null){
		    	model.setXjbh(xjbh);
		    	 result = this.save(model);
		    	 if(result){
		    		  //信件编号不为空的时候才对接受表进行插入
		    		  if(!StringUtil.isNull(xjbh)){
		    			  for(int i=0;i<jsrbh.length;i++){
						    	SxbForm sxb = new SxbForm();
						    	sxb.setJsrbh(jsrbh[i]);
						    	sxb.setXjbh(xjbh);
						    	result = sxbservice.save(sxb);
						    	if(!result){
						    		//如果插入失败，返回假
						    	}
						 }
		    		  }else{
		    			  //信件编号为空，返回false
		    			  return false;
		    			  
		    		  }
		    		 
				 }else{
					//当插入不成功时,返回false
					 return false;
				 }
		    }
		   return result;
	}
	
	//学生发信
	public boolean savestuXX(WdznxForm model) throws Exception{
		 boolean result = true;
		 String xjbh = UniqID.getInstance().getUniqIDHash();
		 xjbh = xjbh.toUpperCase();
		    //发信表插入一条发信记录
		    if(model != null){
		    	 model.setXjbh(xjbh);
		    	 result = this.save(model);
		    	 if(result){
		    		  if(!StringUtil.isNull(xjbh)){
		    			  SxbForm sxb = new SxbForm();
		    			  sxb.setJsrbh(model.getJsrbh());
					      sxb.setXjbh(xjbh);
		    			  result = sxbservice.save(sxb);
		    		  }else{
		    			  //信件编号为空，返回false
		    			  return false;
		    			  
		    		  }
				 }else{
					//当插入不成功时,返回false
					 return false;
				 }
		    	
		    }
		   return result;
	}
	
	//获取学生列表
	public List<HashMap<String, String>> getXsxxList(WdznxForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		return dao.getXsxxList(model, user);
	}
	
	//获取教师列表
	public List<HashMap<String, String>> getTeaxxList(WdznxForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		return dao.getTeaList(model, user);
	}
	
	//信件查看
	public HashMap<String, String> XjckMap(WdznxForm t){
		return dao.XjckMap(t);
	}
	
	/**
     * 
     * @描述:保存
     * @作者：喻鑫源[工号：1206]
     * @日期：2015-12-7 下午03:19:04
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param sx
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean save(WdznxForm t) throws Exception{
    	return dao.save(t);
    } 
	
}
