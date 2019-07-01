  
package com.zfsoft.xgxt.xlzx.yysqnew;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.xlzx.zxyyclnew.ZxyyclDao;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
/** 
 * 心理咨询管理模块
 */

public class YysqService extends SuperServiceImpl<YysqForm, YysqDao> {
	
	private YysqDao dao = new YysqDao();

	public YysqService() {
		super.setDao(dao);
	}
	
	/** 
	 * 预约咨询反馈查询页面
	 */
	public List<HashMap<String, String>> queryYyfkList(YysqForm model,User user) throws Exception {
		return dao.queryYyfkList(model, user);
	}
	/**
	 * 根据预约编号查询预约咨询详情
	 */
	public HashMap<String,String> getYyzxDetail(String id) throws Exception {
		return dao.getYyzxDetail(id);
	}
	/**
	 * 咨询历史信息
	 */
	public List<HashMap<String, String>> getZxlsxxList(String xh, String id) throws Exception {
		return dao.getZxlsxxList(xh, id);
	}
	/**
	 * 根据咨询师编号日期查询预约申请基本信息
	 */
	public List<HashMap<String, String>> getYysqByZghAnddDate(String yyzxrq,String zgh){
		return dao.getYysqByZghAnddDate(yyzxrq,zgh);
	}
	
	//条件过滤预约咨询信息列表（分页）
	public List<HashMap<String, String>> queryYyzxInfoList(YysqForm model,User user)
	throws Exception {
		
		return dao.queryYyzxInfoList(model, user);
	}
	//条件过滤预约咨询信息列表（不分页）
	public List<HashMap<String, String>> queryYyzxInfoListAll(YysqForm t,User user)
	throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.queryYyzxInfoList(t, user);
	}
	//条件过滤预约咨询信息列表（分页）
	public List<HashMap<String, String>> queryYyzxInfoListJg(YysqForm model,User user)
	throws Exception {
		
		return dao.queryYyzxInfoListJg(model, user);
	}
	//条件过滤预约咨询信息列表（不分页）
	public List<HashMap<String, String>> queryYyzxInfoListAllJg(YysqForm t,User user)
	throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.queryYyzxInfoListJg(t, user);
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
	public HashMap<String,String> getYysqByXhAnddDateId(String yyzxrq,String xh,String id){
		
		return  dao.getYysqByXhAnddDateId(yyzxrq,xh,id);
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
	
	/**
	 * 是咨询师
	 */
	public boolean isZxs(String zgh){
		return dao.isZxs(zgh);
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
	 * 删除预约（老师删除）
	 */
	public int delYyzxInfo(String[] ids) throws Exception{
		int count = dao.delYyzxInfo(ids);
		if(count > 0){
			dao.delXlzxInfo(ids);
			new ZxyyclDao().deleteJzxx(ids);
		}
		return count;
	}
	
	/**
	 * 根据学号日期查询预约申请基本信息[按时间段验证]
	 */
	public HashMap<String,String> getYysqByXhAnddDateForSjd(String yyzxrq,String xh,String id){
		return dao.getYysqByXhAnddDateForSjd(yyzxrq, xh,id);
	}

	/**
	 *  获取咨询问题类型列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-27 15:28
	 * @param
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getZxwtlxList() {
		return dao.getZxwtlxList();
	}

	public boolean updateQdzt(YysqForm model) throws Exception {
		return dao.updateQdzt(model);
	}

	/**
	 * @描述:家长信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/1/2 16:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [id]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getJzxx(String sqid) {
		return dao.getJzxx(sqid);
	}
}
