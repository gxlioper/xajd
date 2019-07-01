/**
 * @部门:学工产品事业部
 * @日期：2013-8-22 上午08:54:47 
 */  
package com.zfsoft.xgxt.xlzx.yysq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 预约申请
 * @作者： wanghj [工号：1004]
 * @时间： 2013-8-22 上午08:54:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YysqService extends SuperServiceImpl<YysqForm, YysqDao> {
	
	private YysqDao dao = new YysqDao();

	public YysqService() {
		super.setDao(dao);
	}
	
	
	//条件过滤预约咨询信息列表（分页）
	public List<HashMap<String, String>> queryYyzxInfoList(YysqForm model,User user)
	throws Exception {
		
		return dao.queryYyzxInfoList(model, user);
	}
	
	//根据预约编号查询预约咨询详情
	public HashMap<String,String> getYyzxDetail(String id) throws Exception{
		
		return dao.getYyzxDetail(id);
		
	}
	//预约申请信息详情
	public HashMap<String,String> getYysqInfoById(String id) throws Exception {
		
		return dao.getYysqInfoById(id);
	}
	
	public HashMap<String,String> getYysqById(String id){
		
		return  dao.getYysqById(id);
	}

	public HashMap<String,String> getYysqByCn(String xh,String zgh,String yyzxrq){
		
		return  dao.getYysqByCn(xh,zgh,yyzxrq);
	}
	
	public HashMap<String,String> getYysqByXh(String xh){
			
			return  dao.getYysqByXh(xh);
		}
	public HashMap<String,String> getYysqByXhAnddDate(String yyzxrq,String xh){
		
		return  dao.getYysqByXhAnddDate(yyzxrq,xh);
	}
	
	public List getYysqByZghAnddDate(String yyzxrq,String zgh){
		
		return  dao.getYysqByZghAnddDate(yyzxrq,zgh);
	}
	
	public boolean saveYysqInfo(YysqForm model)
	throws Exception {
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setId(guid);
		return dao.saveYysqInfo(model);
	}
	
	public boolean updateYysqInfo(YysqForm model)
	throws Exception {
		
		return dao.updateYysqInfo(model);
	}
	
	public boolean updateYysqStatus(YysqForm model)
	throws Exception {
		
		return dao.updateYysqStatus(model);
	}
	
	public int delYysqByZgh(String[] zgh) throws Exception {
		
		return dao.delYysqByZgh(zgh);
	}
	public HashMap<String,String> getStuInfoByXh(String xh){
		
		return dao.getStuInfoByXh(xh);
	}


	/** 
	 * @描述:删除预约（老师删除）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-28 下午02:27:44
	 * @param split
	 * @return
	 * String[] 返回类型 
	 * @throws 
	 */
	public int delYyzxInfo(String[] ids) throws Exception{
		int count = dao.delYyzxInfo(ids);
		if(count > 0){
			dao.delXlzxInfo(ids);
		}
		return count;
	}
	
	/**
	 * 
	 * @描述: 计算按时间段先预约当日人数上限
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-27 下午03:02:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSjdYySx(String pbdate){
		return dao.getSjdYySx(pbdate);
	}
	
	/**
	 * 
	 * @描述: 学生预约时间段(被预约或预约成功的进行过滤)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-28 上午09:12:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yyzxrq
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsYySjd(String yyzxrq,String zgh,String xh){
		return dao.getXsYySjd(yyzxrq, zgh,xh);
	}


	/**
	 * @描述:验证基本信息是否存在(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-12-26 下午04:14:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型
	 * @throws
	 */
	public boolean isExists(String xh){
		String num = dao.getRecord(xh);
		return Integer.valueOf(num)>0;
	}

	/**
	 * @描述:获取学生咨询结果表中的数据条数
	 * @作者：lgx [工号：1553]
	 * @日期：2018/12/27 15:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.lang.String
	 */
	public String getCountJg(String xh) {
		return dao.getCountJg(xh);
	}
}
