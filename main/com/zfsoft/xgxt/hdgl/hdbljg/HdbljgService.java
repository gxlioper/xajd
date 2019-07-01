/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

/**
 * @className	： HdbljgService
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-22 上午09:55:30
 * @version 	V1.0 
 */

public class HdbljgService extends SuperServiceImpl<HdbljgForm, HdbljgDao> {
	/**
	 * @description	：插入
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-22 上午11:07:17
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runInsert(HdbljgForm model) throws Exception{
		
		String[] hdbqs = model.getHdbqs();/*获取活动标签*/
		String[] nlbqs = model.getNlbqs();/*获取能力标签*/
		String jgid = model.getJgid();
		
		boolean result = dao.runInsertNotCommit(model);
		/*插入活动标签*/
		if(null != hdbqs && hdbqs.length > 0){			
			result = dao.BatchInsertHdbqx(jgid, hdbqs);
		}
		/*插入能力标签*/
		if(null != nlbqs && nlbqs.length > 0){
			result = dao.BatchInsertNlbqx(jgid, nlbqs);
		}

		return result;
	}
	
	/**
	 * @description	： 删除
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 下午05:30:25
	 * @param values
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runDeleteJg(String[] values) throws Exception {
		
		boolean result = true;
		/*删除活动标签关联表*/
		result = dao.deleteHdbq(values);
		/*删除能力标签关联表*/
		result = dao.deleteNlbq(values);
		if(result){
			return dao.deleteHdjg(values);
		}
		return result;
		
	}
	
	/**
	 * @description	： 修改
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-23 下午03:23:30
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runUpdate(HdbljgForm model) throws Exception{
		boolean result = true;
		/*删除活动标签关联表*/
		result = dao.deleteHdbq(new String[]{model.getJgid()});
		/*删除能力标签关联表*/
		result = dao.deleteNlbq(new String[]{model.getJgid()});
		if(result){
			String[] hdbqs = model.getHdbqs();/*获取活动标签*/
			String[] nlbqs = model.getNlbqs();/*获取能力标签*/
			String jgid = model.getJgid();
			if(hdbqs != null && hdbqs.length>0){
				/*插入活动标签*/
				result = dao.BatchInsertHdbqx(jgid, hdbqs);
			}
			if(nlbqs != null && nlbqs.length>0){
				/*插入能力标签*/
				result = dao.BatchInsertNlbqx(jgid, nlbqs);
			}
			if(result){
				return dao.runUpdate(model);
			}
		}
		return result;
	}
	
	/**
	 * @description	： 根据申请id获取结果id
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午03:18:17
	 * @param sqid
	 * @return
	 */
	public String getJgidBySqid(String sqid){
		return dao.getJgidBySqid(sqid);
	}
	
	/**
	 * @description	： 获取model
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-23 下午04:14:05
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public HdbljgForm getModelForJg(HdbljgForm model) throws Exception{
		HdbljgForm model2 = getModel(model);
		HashMap<String, String> modelInfo = dao.getModelInfo(model);
		/*学期名称*/
		if(StringUtils.isNotNull(modelInfo.get("xqmc"))){
			model2.setXqmc(modelInfo.get("xqmc"));
		}
		/*活动类型名称*/
		if(StringUtils.isNotNull(modelInfo.get("hdlxmc"))){
			model2.setHdlxmc(modelInfo.get("hdlxmc"));
		}
		/*讲座类型名称*/
		if(StringUtils.isNotNull(modelInfo.get("jzlxmc"))){
			model2.setJzlxmc(modelInfo.get("jzlxmc"));
		}
		if(StringUtils.isNotNull(modelInfo.get("zxkclxmc"))){
			model2.setZxkclxmc(modelInfo.get("zxkclxmc"));
		}
		/*活动标签*/
		if(StringUtils.isNotNull(modelInfo.get("hdbq"))){
			model2.setHdbq(modelInfo.get("hdbq"));
			model2.setHdbqs(modelInfo.get("hdbq").split(","));
			model2.setHdbqmc(modelInfo.get("hdbqmc"));
		}
		/*能力标签*/
		if(StringUtils.isNotNull(modelInfo.get("nlbq"))){
			model2.setNlbq(modelInfo.get("nlbq"));
			model2.setNlbqs(modelInfo.get("nlbq").split(","));
			model2.setNlbqmc(modelInfo.get("nlbqmc"));
		}
		return model2;
	}
	
	/**
	 * @描述: 获取活动标签列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:12:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getHdbqList(){
		return dao.getHdbqList();
	}
	
	/**
	 * @描述: 获取讲座类型列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:12:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJzlxList(){
		return dao.getJzlxList();
	}
	public List<HashMap<String,String>> getZxkcDmList(){
		return dao.getZxkcDmList();
	}
	/**
	 * @描述: 获取能力标签列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:12:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAbilityLabelList(){
		return dao.getAbilityLabelList();
	}
}
