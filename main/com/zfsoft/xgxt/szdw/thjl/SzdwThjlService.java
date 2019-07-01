/**
 * @部门:学工产品事业部
 * @日期：2014-7-17 上午10:08:21 
 */ 
package com.zfsoft.xgxt.szdw.thjl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述: 谈话记录维护
 * @作者： cq [工号:785]
 * @时间： 2014-7-17 上午10:08:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwThjlService extends
		SuperServiceImpl<SzdwThjlForm, SzdwThjlDao> {

	private ShlcInterface shlc = new CommShlcImpl();
private SzdwThjlDao dao = new SzdwThjlDao();
	
	public SzdwThjlService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:条件查询谈话记录信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getThjlListById(String id) throws Exception {

		return dao.getThjlListById(id);
	}

	public HashMap<String,String> getSqMap(String id)throws Exception {
		return dao.getSqMap(id);
	}
	/** 
	 * @描述:条件查询谈话记录信息(温州大学)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午04:59:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getThjlListByIdForWzdx(String id) throws Exception{
		return dao.getThjlListByIdForWzdx(id);
	}
	/**
	 * 
	 * @描述:条件查询谈话记录信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getThjlListByXh(String xh) throws Exception {
		
		return dao.getThjlListByXh(xh);
	}
	
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-19 下午04:04:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getThjlListByXhForWzdx(String xh) throws Exception {
		
		return dao.getThjlListByXhForWzdx(xh);
	}
	
	/**
	 * 保存至谈话记录信息表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveThjlInfo(SzdwThjlForm model)
			throws Exception {
		
		return dao.saveThjlInfo(model);
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午04:06:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveThjlInfoForWzdx(SzdwThjlForm model)
			throws Exception {

			return dao.saveThjlInfoForWzdx(model);
}
	
	
	
	/**
	 * 
	 * @描述:删除谈话记录信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delThjlById(String[] id) throws Exception {
		
		return dao.delThjlById(id);
	}
	
	/**
	 * 
	 * @描述:根据ID修改谈话记录信息
	 * @作者：1004
	 * @日期：2013-9-11 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	
	public boolean updateThjlInfo(SzdwThjlForm model) throws Exception{
		
		return dao.updateThjlInfo(model);
	}
	public boolean update(SzdwThjlForm model) throws Exception{
		boolean flag = dao.update(model);

		if(flag && "submit".equals(model.getType())){
			flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getZgh(),"szdw_thjl_thjl_sh.do","szdw_thjl_thjl_sq.do");
		}
		return flag;
	}
	/** 
	 * @描述:通过职工号取得教师信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-24 上午10:50:17
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getInfoByZgh(String zgh) {
		return dao.getInfoByZgh(zgh);
	}

	/** 
	 * @描述:取得教师列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-24 上午11:49:46
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJsInfoList(SzdwThjlForm myForm) {
		return dao.getJsInfoList(myForm);
	}
	
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午02:11:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhhwtList(){
		return dao.getKhhwtList();
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午02:11:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYthrgxList(){
		return dao.getYthrgxList();
	}

	public List<HashMap<String, String>> getAllXjydList(String xh) throws Exception{
		return dao.getAllXjydList(xh);
	}
	public List<HashMap<String,String>> getBjgcj(String xh) throws Exception{
		return dao.getBjgcj(xh);
	}
	public List<HashMap<String,String>> getSqList(SzdwThjlForm t, User user) throws Exception {
		return dao.getSqList(t,user);
	}

	public boolean zjsqBc(SzdwThjlForm model,String type) throws Exception {
		boolean flag;
		if("update".equals(type)) {
			model.setShzt("5");
			flag = dao.updateSq(model);
		} else {
			model.setSqid(UniqID.getInstance().getUniqIDHash());
			model.setSplc(dao.getSplc());
			model.setShzt("5");
			flag = dao.save(model);
		}

		if(flag){
			flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getZgh(),"szdw_thjl_thjl_sh.do","szdw_thjl_thjl_sq.do");
		}
		return flag;
	}

	public boolean del(String[] ids) throws Exception {
		return dao.del(ids);
	}
}
