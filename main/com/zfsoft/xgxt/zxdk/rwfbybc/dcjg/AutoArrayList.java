/**
 * @部门:学工产品事业部
 * @日期：2017年1月13日 下午2:26:41 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.util.ArrayList;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 自动增长初始化有关对象的ArrayList，可用于struts的form接收List
 * @作者： xuwen[工号:1426]
 * @时间： 2017年1月13日 下午2:26:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class AutoArrayList extends ArrayList{
	private Class itemClass;
	 public AutoArrayList(Class itemClass) {
	  this.itemClass = itemClass;
	 }
	 public Object get(int index) {
	  try {
	   while (index >= size()) {
	    add(itemClass.newInstance());
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  return super.get(index);
	 }
}
