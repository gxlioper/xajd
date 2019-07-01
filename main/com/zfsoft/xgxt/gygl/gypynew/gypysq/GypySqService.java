package com.zfsoft.xgxt.gygl.gypynew.gypysq;

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
import com.zfsoft.xgxt.gygl.gypynew.cssz.CsszService;

public class GypySqService extends SuperServiceImpl<GypySqForm, GypySqDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述: 获取楼栋代码List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 下午05:55:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(User user,String gyglyQx){
		 return dao.getLddmList(user, gyglyQx);
	}
	
	/**
	 * 
	 * @描述: 获取层号List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-26 下午02:01:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gyglyQx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(User user,String gyglyQx){
		return dao.getChList(user, gyglyQx);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-26 下午02:37:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gyglyQx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(User user,String gyglyQx){
		return dao.getQshList(user, gyglyQx);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午03:39:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSq(GypySqForm model,User user) throws Exception{
		boolean rs = true;
		//判断重复性
		if(!dao.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.XG_GYPY_SQ_REPEAT);
		}
		model.setSplc(new CsszService().getSplc().get("splc"));
		if("submit".equals(model.getSaveType())){
			model.setShzt(Constants.YW_SHZ);
		}else{
			model.setShzt(Constants.YW_WTJ);
		}
		if(StringUtils.isNotNull(model.getSqid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			model.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if("submit".equals(model.getSaveType())){
			rs = shlc.runSubmit(model.getSqid(),model.getSplc(),user.getUserName(), "gygl_gypynew_gypysh.do", "gygl_gypynew_gypysq.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	//提交
	public boolean submitBusi(GypySqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		String splc =new CsszService().getSplc().get("splc");
		model.setSplc(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplc(),user.getUserName(), "gygl_gypynew_gypysh.do", "gygl_gypynew_gypysq.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @描述: 获取寝室信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-28 下午02:25:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQshxx(GypySqForm model){
		return dao.getQshxx(model);
	}
	
	/**
	 * 
	 * @描述: 获取星级寝室申请基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 上午11:04:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXjqsSqJbxx(GypySqForm model){
		return dao.getXjqsSqJbxx(model);
	}
	
	/**
	 * 
	 * @描述: 获取违纪个数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 上午11:50:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getWjNum(GypySqForm model){
		return dao.getWjNum(model);
	}
	
	/**
	 * 
	 * @描述: 获取寝室所属班级，有可能是多个
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 上午11:55:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public String getQsssbj(GypySqForm model){
		return dao.getQsssbj(model);
	}
	
	/**
	 * 
	 * @描述: 获取违纪信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 下午01:53:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjxx(GypySqForm model){
		return dao.getWjxx(model);
	}
	
}
