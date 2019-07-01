package com.zfsoft.xgxt.xlzx.zxswh;
/**
 * @部门:学工产品事业部
 * @时间： 2013-7-23 下午05:36:33
 */  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学工系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 咨询师信息查询
 * @作者： wanghj
 * @时间： 2013-8-13 下午03:36:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxsglService extends SuperServiceImpl<ZxsglForm, ZxsglDao> {
	
	private ZxsglDao dao = new ZxsglDao();
	
	public ZxsglService() {
		super.setDao(dao);
	}

	/**
	 * 根据咨询师编号查询咨询师信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getZxsInfoByZgh(String zgh) throws Exception {
		return dao.getZxsInfoByZgh(zgh);
	}
	
	
	public List<HashMap<String,String>> getZxsInfoByZgh(String[] zgh,String xq) throws Exception {
		return dao.getZxsInfoByZgh(zgh,xq);
	}
	
	/**
	 * 根据咨询师编号批量查询咨询师信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZxsInfoByZgh(String[] zgh) throws Exception {
		return dao.getZxsInfoByZgh(zgh,"");
	}
	
	/**
	 * 根据咨询师状态查询咨询师信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZxsList(String status) throws Exception {
		return dao.getZxsList(status);
	}
	/**
	 * 根据咨询师状态查询咨询师信息（当天是否有学生预约）
	 */
	public List<HashMap<String,String>> getZxsListXssq(String status, String pbdate) throws Exception {
		return dao.getZxsListXssq(status, pbdate);
	}
	
	/**
	 * 保存咨询师信息
	 * @param model
	 * @return
	 */
	public boolean saveZxsInfo(ZxsglForm model) throws Exception{
		return dao.saveZxsInfo(model);
	}
	/**
	 * 根据职工号,日期查询咨询师
	 */
	public List<HashMap<String, String>> getZxsListYyfk(String[] zghs, String zxrq){
		return dao.getZxsListYyfk(zghs, zxrq);
	}
	/**
	 * 删除咨询师信息
	 * @param model
	 * @return
	 */
	public int delZxsByzgh(String[] zgh) throws Exception{
		int a = dao.delZxsByZgh(zgh);
		return a;
	}
	
	/**
	 * 更新咨询师信息
	 * @param model
	 * @return
	 */
	public boolean updateZxsInfo(ZxsglForm model) throws Exception{

		return dao.updateZxsInfo(model);
	}
	
	/**
	 * 批量更新咨询师状态
	 * @param model
	 * @return
	 */
	public boolean updateBatchZgStatus(String[] zgh,String status) throws Exception{
		
		return dao.updateBatchZgStatus(zgh, status);
	}
	
	/**
	 * 根据职工号查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getFdyInfo(String zgh){
		
		return dao.getFdyInfo(zgh);
	}
	
	/**
	 * 查出所有咨询师
	 */
	public List<HashMap<String, String>> getZxsxxAllList(){
		return dao.getZxsxxAllList();
	}
	/**
	 * 查出所有咨询师  (根据预约时间)
	 */
	public List<HashMap<String, String>> getZxsxxAllByYysjList(String yysj){
		return dao.getZxsxxAllByYysjList(yysj);
	}
	
	/**
	 * 校区列表
	 */
	public List<HashMap<String, String>> getXq() {
		return dao.getXq();	
	}
	
	/**
	 * 
	 * @描述: 时间段预约咨询师列表查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-27 下午04:04:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param xq
	 * @param pbdate
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZxsInfoByZghForsjdYy(String[] zgh,String xq,String pbdate) throws Exception {
		return dao.getZxsInfoByZghForsjdYy(zgh, xq, pbdate);
	}
	
	/**
	 * 
	 * @描述: 时间段预约反馈查询反馈可预约学生
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-30 上午10:12:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zghs
	 * @param zxrq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxsListYyfkForSjd(String[] zghs, String zxrq){
		return dao.getZxsListYyfkForSjd(zghs, zxrq);
	}

	/** 
	 * @描述:查询擅长领域(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-3-5 上午09:38:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSclyList() {
		
		return dao.getSclyList();
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-3-5 上午10:04:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sclydms
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getSclyMc(String sclydms) {
		String sclymc = "";
		if(sclydms!=null){
			String[] sclydm = sclydms.split(",");
			List<HashMap<String, String>> sclyInfoList = this.getSclyList(sclydm);
			if(sclyInfoList!=null && sclyInfoList.size()>0){
				for(int i=0;i<sclyInfoList.size();i++){
					if(i == sclyInfoList.size()-1){
						sclymc += sclyInfoList.get(i).get("sclymc");
					}else{
						sclymc += sclyInfoList.get(i).get("sclymc")+" 、";
					}
				}
			}
		}
		return sclymc;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-3-5 上午10:06:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sclydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	private List<HashMap<String, String>> getSclyList(String[] sclydm) {
		return dao.getSclyList(sclydm);
	}

}
