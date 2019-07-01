package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz;

import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XlzxSbService extends SuperServiceImpl<XlzxSbJcszForm,XlzxSbDao> {
	/**
	 * @throws Exception 
	 * 
	 * @描述: 参数设置保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 下午05:25:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJcsz(String[]splcs,String[]lxs) throws Exception{
		dao.delJcsz();
		List<String[]> paraList = new ArrayList<String[]>();
		if(splcs != null && lxs != null){
			for (int i = 0; i < splcs.length; i++) {
				paraList.add(new String[]{splcs[i],lxs[i]});
			}
		}
		return dao.saveJcsz(paraList);
	}
	
	/**
	 * getModel重写
	 * @throws Exception 
	 */
	public XlzxSbJcszForm getModel(String lx) throws Exception{
		return dao.getModel(lx);
	}
}
