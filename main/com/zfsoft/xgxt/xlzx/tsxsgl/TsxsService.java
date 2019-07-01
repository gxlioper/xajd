/**
 * @部门:学工产品事业部
 * @日期：2013-9-10 上午11:10:07 
 */  
package com.zfsoft.xgxt.xlzx.tsxsgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 特殊学生维护模块(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-9-10 上午11:10:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsService extends SuperServiceImpl<TsxsForm, TsxsDao> {
	
	private TsxsDao dao = new TsxsDao();
	
	public TsxsService() {
		super.setDao(dao);
	}
	
	
	/**
	 * 根据Id查询特殊学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String id){
		
		return dao.getTsxsInfoById(id);
	}
	
	/**
	 * 根据Id查询特殊学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXh(String xh){
		
		HashMap<String,String> xsxx = dao.getTsxsDetailByXh(xh);
		if(xsxx !=null && xsxx.size()>0){

			//获取困难类型名称
			if(xsxx.get("knlxdm")!=null && !"".equals(xsxx.get("knlxdm"))){
				String knlxmc = getKnlxMc(xsxx.get("knlxdm"));
				xsxx.put("knlxmc", knlxmc);
			}
		}
		return xsxx;
	}
	/**
	 * 根据Id查询特殊学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXhZc(TsxsForm myForm){
		
		HashMap<String,String> xsxx = dao.getTsxsDetailByXhZc(myForm);
		if(xsxx !=null && xsxx.size()>0){

			//获取困难类型名称
			if(xsxx.get("knlxdm")!=null && !"".equals(xsxx.get("knlxdm"))){
				String knlxmc = getKnlxMc(xsxx.get("knlxdm"));
				xsxx.put("knlxmc", knlxmc);
			}
		}
		return xsxx;
	}
	
	/**
	 * 保存至特殊学生信息表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveTsxsInfo(TsxsForm model)
			throws Exception {
		
		return dao.saveTsxsInfo(model);
	}
	

	/**
	 * 
	 * @描述:删除特殊学生信息
	 * @作者：1004
	 * @日期：2013-9-10 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delTsxsById(String[] id) throws Exception {
		
		return dao.delTsxsById(id);
	}
	/**
	 * 
	 * @描述:删除特殊学生关注状态
	 * @作者：1004
	 * @日期：2013-9-10 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		return dao.updateBatchGzStatus(id, gzzt);
	}
	
	/**
	 * 
	 * @描述:修改特殊学生信息
	 * @作者：1004
	 * @日期：2013-9-10 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean updateTsxsInfo(TsxsForm model) throws Exception{
		
		return dao.updateTsxsInfo(model);
	}
	
	public List<HashMap<String, String>> getKnlxList(String[] knlxdms){
		
		return dao.getKnlxList(knlxdms);
	}
	
	public List<HashMap<String, String>> getKnlxList(){
		
		return dao.getKnlxList();
	}
	
	public String getKnlxMc(String knlxdms){
		String knlxmc = "";
		if(knlxdms!=null){
			String[] knlxdm = knlxdms.split(",");
			List<HashMap<String, String>> knlxInfoList = this.getKnlxList(knlxdm);
			if(knlxInfoList!=null && knlxInfoList.size()>0){
				for(int i=0;i<knlxInfoList.size();i++){
					if(i==knlxInfoList.size()-1){
						knlxmc += knlxInfoList.get(i).get("knlxmc");
					}else{
						knlxmc += knlxInfoList.get(i).get("knlxmc")+" 、";
					}
				}
			}
		}
		return knlxmc;
	}


	/** 
	 * @描述:根据学号获取最新的特殊学生信息(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-4-8 下午04:59:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getZcTsxsInfo(TsxsForm myForm) {
		
		return dao.getZcTsxsInfo(myForm);
	}
}
