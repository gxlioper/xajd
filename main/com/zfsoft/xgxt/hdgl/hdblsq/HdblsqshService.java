/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgDao;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @className	： HdblsqshService
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-16 下午05:20:17
 * @version 	V1.0 
 */

public class HdblsqshService extends SuperServiceImpl<HdblsqshForm, HdblsqshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * @description	： 获取活动类型列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-17 下午03:15:20
	 * @return
	 */
	public List<HashMap<String,String>> getHdlxList(){
		return dao.getHdlxList();
	}
	
	/**
	 * @description	： 获取当前学期
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 下午02:04:45
	 * @return
	 */
	public String getCurrXq(){
		DAO dao = DAO.getInstance();
		return dao.getXqmcForXqdm(Base.currXq);
	}
	
	/**
	 * @description	： 保存申请
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 上午11:16:52
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean saveSq(HdblsqshForm model, User user) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc = model.getSplc();
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		String[] hdbqs = model.getHdbqs();/*获取活动标签*/
		String[] nlbqs = model.getNlbqs();/*获取能力标签*/

		if(flag){
			/*插入活动标签*/
			if(null != hdbqs && hdbqs.length > 0){
				flag = new HdbljgDao().BatchInsertHdbqx(sqid, hdbqs);
			}
			/*插入能力标签*/
			if(null != nlbqs && nlbqs.length > 0){
				flag = new HdbljgDao().BatchInsertNlbqx(sqid, nlbqs);
			}
		}
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "hdgl_hdbl_hdblsh.do", "hdgl_hdbl_hdblsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * @description	：修改保存
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 上午11:16:26
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@TransactionControl
	public boolean saveSqUpdate(HdblsqshForm model, User user) throws Exception{
		boolean result = false;
		HdbljgDao jgDao = new HdbljgDao();
		String sqid = model.getSqid();
		/*删除活动标签关联表*/
		result = jgDao.deleteHdbq(new String[]{sqid});
			/*删除能力标签关联表*/
		result = jgDao.deleteNlbq(new String[]{sqid});

		if(result){
			String[] hdbqs = model.getHdbqs();/*获取活动标签*/
			String[] nlbqs = model.getNlbqs();/*获取能力标签*/
			if(hdbqs != null && hdbqs.length>0){
				/*插入活动标签*/
				result = jgDao.BatchInsertHdbqx(sqid, hdbqs);
			}
			if(nlbqs != null && nlbqs.length>0){
				/*插入能力标签*/
				result = jgDao.BatchInsertNlbqx(sqid, nlbqs);
			}
		}
		if(result){
			// 如果提交，插入审核状态
			if ("updatesubmit".equalsIgnoreCase(model.getType())) {
				model.setShzt(Constants.YW_SHZ);// 审核中
				result = dao.runUpdate(model);
				ShlcInterface shlc = new CommShlcImpl();
				if (result) {
					result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "hdgl_hdbl_hdblsh.do", "hdgl_hdbl_hdblsq.do");
				}
			} else {
				result = runUpdate(model);
			}
		}
		return result;
	}

	/**
	 * @description	： 撤销
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 上午11:42:56
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @description	： 提交
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 上午11:54:28
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean submitHdblsq(HdblsqshForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// 审核中
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "hdgl_hdbl_hdblsh.do", "hdgl_hdbl_hdblsq.do");
		}
		return result;
	}

	public int runDelete(String[] values) throws Exception {
		int num = dao.runDelete(values);
		if(num>0){
			HdbljgDao jgDao = new HdbljgDao();
			jgDao.deleteHdbq(values);
			jgDao.deleteNlbq(values);
		}
		return num;
	}

	/**
	 * @description	： 获取活动信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 下午02:18:44
	 * @param model
	 * @return
	 */
	public HashMap<String,String> gethdblInfo(HdblsqshForm model){
		return dao.gethdblInfo(model);
	}

	/**
	 * @description ：获取大厅推送活动
	 * @date         ：2018-07-04 17:47
	 * @param hdid
	 * @return
	 */
	public HashMap<String,String> gethdInfo(String hdid){
		return dao.gethdInfo(hdid);
	}
	/**
	 * @description	： 获取活动标签list
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-19 下午05:26:30
	 * @return
	 */
	public List<HashMap<String,String>> getActivityLabelList(){
		List<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> bookMap = new HashMap<String, String>();
		bookMap.put("dm", "100本书");
		bookMap.put("mc", "100本书");
		HashMap<String,String> teacherMap = new HashMap<String, String>();
		teacherMap.put("dm", "100名老师");
		teacherMap.put("mc", "100名老师");
		HashMap<String,String> activityMap = new HashMap<String, String>();
		activityMap.put("dm", "100个活动");
		activityMap.put("mc", "100个活动");
		HashMap<String,String> lectureMap = new HashMap<String, String>();
		lectureMap.put("dm", "100个讲座");
		lectureMap.put("mc", "100个讲座");
		arrayList.add(bookMap);
		arrayList.add(teacherMap);
		arrayList.add(activityMap);
		arrayList.add(lectureMap);
		return arrayList;
	}

	/**
	 * @描述:主讲人职称
	 * @作者：lgx [工号：1553]
	 * @日期：2019/3/21 16:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
    public List<HashMap<String,String>> getZjrzcList() {
		return dao.getZjrzcList();
    }

    public List<HashMap<String,String>> getHdxxList(HdblsqshForm t,User user) throws Exception {
    	return  dao.getHdxxPageList(t,user);
	}
}
