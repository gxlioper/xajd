/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:41:06 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:41:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdsqService extends SuperServiceImpl<LstdsqForm, LstdsqDao> {
	
	
	private LstdsqDao dao = new LstdsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public LstdsqService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:类型维护集合
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:37:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxwhList() {
		return dao.getLxwhList();
	}
	

	/**
	 * 
	 * @描述:新增
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:38:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveLstdsq(LstdsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), splc,model.getXh(),"rcsw_lstd_sh.do","rcsw_lstd_sq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:修改
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:41:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateLstdsq(LstdsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取审批流程
			String splc = dao.getShlcID();
			model.setSplc(splc);
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult) {
			shlc.deleteShjl(model.getSqid());
			if("update".equalsIgnoreCase(model.getType())){
				result = true;
			}else{
				result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_lstd_sh.do","rcsw_lstd_sq.do");
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteLstdsq(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getLstdsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?lstdDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:49:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int lstdDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	
	/**
	 * 
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:50:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitSq(LstdsqForm model) throws Exception {
		boolean resultSq = dao.updateSq(model);
		boolean result = false;
		if(resultSq){
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_lstd_sh.do","rcsw_lstd_sq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:更新申请
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:52:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSq(LstdsqForm model) throws Exception {
			boolean resultBbsq = dao.updateSq(model);
			return resultBbsq;
	}
	
	/**
	 * 
	 * @描述:单个查看
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:53:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getLstdsqInfo(LstdsqForm model) {
		if (StringUtil.isNull(model.getSqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getLstdsqInfo(model);
	}
	

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:54:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * 
	 * @描述:待审核的记录总数
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:55:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean allowUpdateSetting() {

		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @描述:按学号、学年和学期判断申请是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 下午01:55:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByLstdsq(LstdsqForm model)
	throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

}
