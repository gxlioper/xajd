/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 上午08:52:36 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_学情月报管理模块
 * @类功能描述: TODO(北京中医药_学情月报_班级月报申请) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-24 上午08:52:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class BjxqybsqService extends SuperServiceImpl<BjxqybsqForm, BjxqybsqDao> {
	
	private BjxqybsqDao dao = new BjxqybsqDao();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	private ShlcInterface shlc = new CommShlcImpl();
	
	@SuppressWarnings("deprecation")
	public BjxqybsqService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-保存申请)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:31:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBjxqybsq(BjxqybsqForm model,User user) throws Exception {			
		model.setTxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}		
		String splc = dao.getShlcID();
		model.setSplc(splc);			
		boolean insertResult = super.runInsert(model);
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}	
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())){	
			String sqURL = "rcsw_xqybgl_bjxqybgl_bjxqyb.do";
			String shURL = "rcsw_xqybgl_bjxqybgl_bjxqybsh.do";
			result = shlc.runSubmit(model.getSqid(),splc,model.getBjdm(),shURL,sqURL);
		}		
		return result;
	}
	
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-判断班级学情月报申请是否已经存在)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 上午10:53:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByBjxqybsq(BjxqybsqForm model,String type)
	throws Exception {
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForBjxqybsqSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForBjxqybsqUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}	
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-更新班级月报申请)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:33:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBjxqybsq(BjxqybsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
			// 获取审批流程
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			//model.setShzt(Constants.YW_WTJ);//未提交
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getBjdm(),
					"rcsw_xqybgl_bjxqybgl_bjxqybsh.do","rcsw_xqybgl_bjxqybgl_bjxqybsq.do");
			return result;
		}else{
			return insertResult;
		}
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-提交申请)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-7 下午03:24:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBjxqybsq(BjxqybsqForm model) throws Exception {
		
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取审批流程
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultHcccqjtx = dao.updateBjxqybsq(model);
		boolean result = false;
		if(resultHcccqjtx){
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(),model.getSplc(),model.getBjdm(),"rcsw_xqybgl_bjxqybgl_bjxqybsh.do","rcsw_xqybgl_bjxqybgl_bjxqyb.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-按申请ID获取申请详细信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-7 下午03:23:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybsqInfo(BjxqybsqForm model){	
		return dao.getBjxqybsqInfo(model);
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-撤销-只有刚提交并且第一级未审核的前提下，申请人可以撤销)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 上午09:39:28
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
	 * @描述:TODO(班级学情月报管理-班级月报申请-取消月报申请)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 上午09:42:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelBjxqybsq(BjxqybsqForm model) throws Exception {
		boolean resultBjxqybsq = dao.cancelBjxqybsq(model);
		return resultBjxqybsq;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-删除月报申请)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 下午01:55:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteBjxqybsq(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getBjxqybsq(str);
				noDel.append(hm.get("bjdm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("bjmc"));				
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?BjxqybsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-按ID删除系统维护-审核状态表中对应的业务ID数据)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:38:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int BjxqybsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-查询班级)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-13 下午03:16:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjList(BjxqybsqForm t, User user)
		throws Exception {
		return dao.getBjList(t, user);
	}
	

}
