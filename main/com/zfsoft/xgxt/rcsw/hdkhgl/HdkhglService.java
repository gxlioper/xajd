/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午04:49:28 
 */  
package com.zfsoft.xgxt.rcsw.hdkhgl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-18 下午04:49:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HdkhglService extends SuperServiceImpl<HdkhglForm, HdkhglDAO> {
	
	//获取学生活动考勤信息
	public HashMap<String, String> getXsHdKhxx(HdkhglForm hdgl){
		return dao.getXsHdKhxx(hdgl);
	}
	
    public boolean savekqdj(HdkhglForm hdgl ) throws Exception{
    	boolean result = true;
    	String id = hdgl.getId();
    	if(hdgl.getSfcj().equals("是")){
			hdgl.setQqyy("");
		}
		if(id != null &&!id.equals("")){
			result = this.runUpdate(hdgl);
		}else{
			result = this.runInsert(hdgl);
		}
    	return  result;
		
	}
    
    //批量考勤登记保存
    public boolean savePlsh(HdkhglForm hdgl)  throws Exception{
    	boolean result = true;
    	String[] ids = hdgl.getIds();
		String[] hdjgids = hdgl.getHdjgids();
		String[] xhs = hdgl.getXhs();
		String[] xmmcs = hdgl.getXmmcs();
		String[] xns = hdgl.getXns();
		String[] xqs = hdgl.getXqs();
		String sfcj = hdgl.getSfcj();
		String qqyy = hdgl.getQqyy();
		for(int i=0;i<hdjgids.length;i++){
			HdkhglForm hdglform = new HdkhglForm();
			hdglform.setId(ids[i]);
			hdglform.setHdjgid(hdjgids[i]);
			hdglform.setXh(xhs[i]);
			hdglform.setXmmc(xmmcs[i]);
			hdglform.setXn(xns[i]);
			hdglform.setXq(xqs[i]);
			hdglform.setSfcj(sfcj);
			hdglform.setQqyy(qqyy);
			result = this.savekqdj(hdglform);
		}
    	return  result;
    }
}
