/**
 * @部门:学工产品事业部
 * @日期：2014-1-20 上午09:18:32 
 */  
package com.zfsoft.xgxt.base.export.defualt.demo;

import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.export.defualt.IDefualtData;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-20 上午09:18:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DefualtDemo implements IDefualtData{
	public List<String[]> getDefualtData(String drmkdm) {
		List<String[]> list=new ArrayList<String[]>();
		String[] col=new String[2];
		col[0]="测试默认1";
		col[1]="测试默认2";
		list.add(col);
		return list;
	}

}
