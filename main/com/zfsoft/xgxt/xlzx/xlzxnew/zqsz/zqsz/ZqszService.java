package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class ZqszService extends SuperServiceImpl<ZqszForm, ZqszDao> {
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午01:48:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYbzqList(ZqszForm t, User user)
	throws Exception {
		return dao.getYbzqList(t, user);
	}
	

	/**
	 * 
	 * @描述: 验证周日程名称是否被使用过
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午02:25:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsZqMcNotUserd(String zbid,String zbzc,String xn,String xq){
		return dao.checkIsZqMcNotUserd(zbid, zbzc, xn, xq);
	}
	
	/**
	 * 
	 * @描述: 验证周日常是否被使用过
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午01:58:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsZqNotUserd(String[] zbids){
		return dao.checkIsZqNotUserd(zbids);
	}
	
	/**
	 * 
	 * @描述: 验证是否月周期名称可以使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午04:23:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param yf
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsYzqMcNotUsed(String xn,String yf,String ybid){
		return dao.checkIsYzqMcNotUsed(xn, yf,ybid);
	}
	
	/**
	 * 
	 * @描述: 验证月周期是否被使用过
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-17 下午03:01:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsYzqNotUserd(String[] ybids){
		return dao.checkIsYzqNotUserd(ybids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存月周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 上午11:56:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveYzqsz(ZqszForm t) throws Exception{
		return dao.saveYzqsz(t);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 修改周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午02:34:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYzqsz(ZqszForm t) throws Exception{
		return dao.updateYzqsz(t);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取月周期Model
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午02:45:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ZqszForm 返回类型 
	 * @throws
	 */
	public ZqszForm getYzqModel(String ybid) throws Exception {
		return dao.getYzqModel(ybid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除月周期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午04:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ybids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delYzqSz(String[] ybids) throws Exception{
		return dao.delYzqSz(ybids);
	}
	
	/**
	 * 
	 * @描述: 查询周期上报详细情况查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午05:50:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param sbbjlx
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZqsbxxqkCx(ZqszForm t, User user, String sbbjlx)throws Exception{
		return dao.getZqsbxxqkCx(t, user, sbbjlx);
	}
	
	/**
	 * 
	 * @描述: 验证周次时间是否重合
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-24 下午03:40:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kssj
	 * @param jssj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsTimeNotRepeat(String kssj,String jssj,String zbid){
		return dao.checkIsTimeNotRepeat(kssj, jssj, zbid);
	}
}
