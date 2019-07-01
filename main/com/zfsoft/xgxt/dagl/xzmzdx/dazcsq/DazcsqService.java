/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午03:12:55 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午03:13:09 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcsqService extends SuperServiceImpl<DazcsqForm,DazcsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @描述: 获得审批流程
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-8 上午11:57:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getSplc(){
		DazccsszDao dazccsszDao = new DazccsszDao();
		return dazccsszDao.getSplc();
	}
	
	/**
	 * @描述: 保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-9 下午01:56:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveFormDazcsq(DazcsqForm model,User user) throws Exception{
		boolean rs = true;
		
		/*生成唯一标识符*/
		String sqid = UniqID.getInstance().getUniqIDHash();
		/*取审批流程*/
		String splcid = this.getSplc().get("splc");
		
		/*判断唯一键，学号(xh)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*当前操作人员用户名塞入*/
		model.setSjlrr(user.getUserName());
		
		/*获取当前操作时间塞入表中，配合操作人工号防止老师耍赖*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*保存申请审核状态塞入*/
		if("save".equals(model.getSaveFlag())){
			/*点击 保存草稿 按钮审核状态为未提交【0】*/
			model.setShzt(Constants.YW_WTJ);
		}else{
			/*点击 提交申请 按钮审核状态为审核中【5】*/
			model.setShzt(Constants.YW_SHZ);
		}
		
		/*判断该数据是否为修改数据*/
		if(StringUtils.isNotNull(model.getSqid())){
			
			/*当转出方式为【邮寄】的时候，修改操作要把自带承诺的值清空掉*/
			if("1".equals(model.getZcfs())){
				model.setZddacn("");
			}
			
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*塞入唯一ID*/
			model.setSqid(sqid);
			model.setSplcid(splcid);
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		
		/*数据提交时，把sqid,审批流程、操作对象、.do插入审核流程*/
		if("submit".equals(model.getSaveFlag())){
			rs = shlc.runSubmit(model.getSqid(),splcid,model.getXh(), "xsxx_dagl_dazcsh.do", "xsxx_dagl_dazcsq.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 验证唯一性
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-9 下午01:59:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcsqForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @描述: 根据申请ID获得学生申请信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-9 下午05:46:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid) throws Exception{
		return dao.getInfoBySqid(sqid);
	}
	
	/**
	 * @描述: 删除时清理审核状态表中的垃圾数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午01:59:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		return dao.delShztbData(sqids);
	}
	
	/**
	 * @描述: 根据sqid获取学生相关信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:36:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr)throws Exception{
		return dao.getInfoBySqid(sqidArr);
	}
	
	/**
	 * @描述: 批量提交
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:37:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param splcid
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean plSubmit(String sqid,String splcid,String xh) throws Exception{
		boolean flag = false;
		DazcsqForm model = new DazcsqForm();
		model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splcid);
		model.setSqid(sqid);
		flag = dao.runUpdate(model);
		if(flag){
			shlc.runSubmit(sqid,splcid,xh, "xsxx_dagl_dazcsh.do", "xsxx_dagl_dazcsq.do");
		}
		return flag;
	}
	
	/**
	 * @描述: 撤销审核中的记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:46:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

}
