/**
 * @部门:学工产品事业部
 * @日期：2015-8-7 上午10:41:06 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-7 上午10:41:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RtjgService extends SuperServiceImpl<RtjgForm, RtjgDao> {
	
	//获取社团信息
	public HashMap<String, String> getStxxMap(RtjgForm rtjg) throws Exception{
		return dao.getStxxMap(rtjg);
	}
	
	//获取社团成员信息（如果usertype = stu,只能查看本人的信息）
	public List<HashMap<String, String>> getStxxCylist(RtjgForm rtjg,User user){
		return dao.getStxxCylist(rtjg, user);
	}
	
	//获取申请设置开关，用于结果维护中判断是否给予修改权限（sqkg:1:给予；sqkg:0;不给予）
	public String getSqShKg(String stid) throws Exception{
		return dao.getSqShKg(stid);
	}
	
	//入团结果维护添加学生，学生选择页面
	public List<HashMap<String, String>> getXsxxList(RtjgForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)||null==xhArr){
			model.setXhArr(new String[]{});
		}
		else{
			model.setXhArr(xhArr.split(","));
		}
		return dao.getXsxxList(model, user);

	}
	
	//更新成员数量方法 by yxy 
	public boolean saveAddRtCySl(String stid,String num) throws Exception{
		return dao.saveAddRtCySl(stid, num);
	}
	
	//更新成员数量方法(del) by yxy 
	public boolean saveDelRtCySl(String stid,String num) throws Exception{
		return dao.saveDelRtCySl(stid, num);
	}
	
	//获取人员类别列表
	public List<HashMap<String, String>>  getRylbList(){
		return dao.getRylbList();
	}
	
	//获取下拉框html
	public String getSelectOption(){
		return dao.getSelectOption();
	}
}
