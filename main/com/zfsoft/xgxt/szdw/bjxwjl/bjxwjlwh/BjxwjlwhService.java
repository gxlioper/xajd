/**
 * @部门:学工产品事业部
 * @日期：2014-5-14 下午02:34:09 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjlwh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xsgzgl.szdw.general.dwwh.DwwhService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-14 下午02:34:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxwjlwhService extends SuperServiceImpl<BjxwjlwhForm, BjxwjlwhDao> {

	/**
	 * 辅导员信息
	 */
	private static final DwwhService dwwhService = new DwwhService();
	
	/**
	 * 
	 * @描述:获取辅导员信息
	 */
	public HashMap<String, String> getFdyxx(String zgh){
		
		 HashMap<String, String> data = dwwhService.ckJzgxx(zgh);
		
		 data.put("xn", Base.currXn);
		 data.put("xq", Base.currXq);
		 data.put("xqmc", DAO.getInstance().getXqmcForXqdm(Base.currXq));
		 
		 return data;
	}
	
	/**
	 * 
	 * @描述:获取辅导员带班班级信息
	 */
	public List<HashMap<String , String>> getFdyBjxxList(String zgh){
		return dao.getFdyBjxxList(zgh);
	}
	
	/**
	 * 
	 * @描述:获取类别信息
	 */
	public List<HashMap<String , String>> getLbList(){
		return dao.getLbList();
	}
	
	//保存班级记录
	public boolean saveBjjl(String xn , String xqdm , String zgh , List records) {
		
		return dao.saveBjjl(xn, xqdm, zgh, records);
	}
	
	/**
	 获取记录信息 guid
	 */
	public HashMap<String , String> getModelMap(String guid) throws Exception{
		return dao.getModelMap(guid);
	}
	
	public BjxwjlwhService(){
		setDao(new BjxwjlwhDao());
	}
	
}
