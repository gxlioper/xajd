/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午04:52:00 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称:日常事务管理模块
 * @类功能描述: 场地申请Service
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 下午04:52:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdsqService extends SuperServiceImpl<CdsqForm, CdsqDao> {

	/**
	 * @描述 审核流操作接口
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @描述 场地信息服务接口
	 */
	private CdxxwhService cdxxwhService = new CdxxwhService();
	
	public static final String PATH_SH = "rcsw_cdgl_cdshgl.do";
	
	public static final String PATH_SQ = "rcsw_cdgl_cdsqgl.do";
	
	/**
	 * 	获取场地申请信息			
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-29 上午10:18:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getCdsqxx(String sqid){
		if(StringUtils.isNotBlank(sqid)){
			return dao.getCdsqxx(sqid);
		}
		return null;
	}
	
	public List<HashMap<String, String>> getPageListOfSqjl(CdsqForm t, User user)
	throws Exception {
	
    return dao.getPageListOfSqjl(t,user);
	}
	/**
	 * 
	 * 
	 * @描述:保存场地申请草稿
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:28:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 
	 * @throws
	 */
	public HashMap<String , Object> saveCdsqNoSubmit(CdsqForm model) throws Exception{
		HashMap<String , Object> result = new HashMap<String, Object>(); //返回对象
				
		model.setShzt(Constants.YW_WTJ);//设置审核状态 未提交
		model.setSqsj(DateUtils.getCurrTime()); //设置申请时间
		boolean isSuccess = runInsert(model); //保存申请
		result.put("ISSUCCESS", isSuccess);
		return result;
	}
	
	/**
	 * 
	 * @描述:提交场地申请
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 下午02:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitCdsq(CdsqForm form) throws Exception{
		CdsqForm model = null;
		boolean checkConditions = false;
		if(form.getSqid() != null){
			model = getModel(form.getSqid());
		}
		if(model != null){
			checkConditions = checkSqSjd(model); //检查申请条件
		}

		if(!checkConditions){
			return false;
		}
		
		boolean isSuccess = false;
		model.setSplcid(form.getSplcid());
		model.setQxfw(form.getQxfw());
		
		if(model != null && StringUtils.isNotBlank(model.getSqid())){
			isSuccess = shlc.runSubmit(model.getSqid(), model.getSplcid(), model.getXh(), CdsqService.PATH_SH, CdsqService.PATH_SQ);
			if(isSuccess){
				model.setShzt(Constants.YW_SHZ);
				isSuccess = runUpdate(model);
			}
		}
		return isSuccess;
	}
	/**
	 * 更新场地申请
	 */
	public boolean updateCdsqNoSubmit(CdsqForm form) throws Exception{
		boolean checkConditions = checkSqSjd(form); //检查申请条件
		if(!checkConditions){
			return false;
		}
		boolean isSuccess = runUpdate(form);
		return isSuccess;
	}
	
	/**
	 * 
	 * @描述:删除场地申请
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 下午03:01:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteCdsq(String[] sqids) throws Exception{
		return dao.runDelete(sqids);
	}
	
	/**
	 * 
	 * @描述:撤销场地申请
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 下午03:34:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelCdsq(String sqid) throws Exception{
		boolean isSuccess = false;
		CdsqForm model = null;
		if(sqid != null){
			model = getModel(sqid);
		}
		
		if(model != null){
			isSuccess = shlc.firstStepCancle(model.getSqid(), model.getSplcid());
		}
		
		if(isSuccess){
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getSqid()))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			isSuccess = runUpdate(model);
		}
		
		return isSuccess;
	}
	
	/**
	 * 
	 * 
	 * @描述:保存场地申请并提交申请
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:28:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 
	 * @throws
	 */
	public HashMap<String , Object> saveCdsqWithSubmit(CdsqForm model) throws Exception{
		
		HashMap<String , Object> result = new HashMap<String, Object>(); //返回对象
		
		boolean checkConditions = checkSqSjd(model); //检查申请条件
		
		if(!checkConditions)
		{
			result.put("CODE", "CHK_ERROR");
			result.put("ISSUCCESS", Boolean.FALSE);
			return result;
		}
		
		model.setSqid(UniqID.getInstance().getUniqIDHash()); //设置sqid
		
		boolean isSuccess = (Boolean)saveCdsqNoSubmit(model).get("ISSUCCESS"); //保存申请
		isSuccess = submitCdsq(model);				 //提交申请
		
		result.put("ISSUCCESS", isSuccess);
		return result;
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:校验申请的时间段 > 1.申请时间比选落在开放时间段里；2。申请人申请的时间必须没有被其他人申请！
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 上午10:41:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkSqSjd(CdsqForm model) throws Exception{
		boolean booleaVal = true;
		//校验 1.申请时间比选落在开放时间段里
		CdxxwhForm cdxxModel = null ;
		if(model != null && StringUtils.isNotBlank(model.getCdid())){
			cdxxModel = cdxxwhService.getModel(model.getCdid());
		}
		if(null != cdxxModel){
			String dwkfkssj = cdxxModel.getDwkfsjkssj(); //对外开始申请时间
			String dwkfjssj = cdxxModel.getDwkfsjjssj(); //对外开始结束时间
			
			String sqkssj = StringUtils.substringAfter(model.getSqsjdkssj(), " "); //申请开始时间
			String sqjssj = StringUtils.substringAfter(model.getSqsjdjssj(), " "); //申请结束时间
			//比较时间段 ，申请时间段必须要落在开放申请时间段里
			if(!((DateUtils.compareTimes(sqkssj, dwkfkssj) >= 0) && (DateUtils.compareTimes(dwkfjssj, sqjssj) >= 0))){
				booleaVal = false;
			}
		}
		
		//校验 2。申请人申请的时间必须没有被其他人申请
		if(cdxxModel != null){
			List<HashMap<String , String>> yscdsqList = dao.getYxCdsq(cdxxModel.getCdid(), model.getSqid()); //获取已经申请的场地使用列表
			String sqkssj = model.getSqsjdkssj();
			String sqjssj = model.getSqsjdjssj();
			for (HashMap<String, String> cdsq : yscdsqList) {
				String sqsjdkssj = cdsq.get("sqsjdkssj");
				String sqsjdjssj = cdsq.get("sqsjdjssj");
				if(DateUtils.checkTimepriodDuplicate(sqkssj, sqjssj, sqsjdkssj, sqsjdjssj)){
					booleaVal = false;
					break;
				}
			}
		}
		
		return booleaVal;
	}
	
	/**
	 * 
	 * @描述:校验申请人申请状态
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 上午10:42:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkSqrSqzt(String xh){
		List<HashMap<String , String>> data = null;
		if(StringUtils.isNotBlank(xh)){
			data = dao.getCdsqInSpcByXh(xh);
		}
		if(data != null && data.size() >= 1){
			return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * @描述 ：初始化Dao
	 */
	public CdsqService() {
		super();
		super.setDao(new CdsqDao());
	}


	
	/** 
	 * @描述:验证是否可提交
	 * @作者：qlm
	 * @日期：2014-5-26 上午11:18:56
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkSfktj(CdsqForm model) {
		String num = dao.checkSfktj(model.getCdid());
		return Integer.valueOf(num) > 0;
	}

}
