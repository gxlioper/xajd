/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:37:22 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.jg;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 日常事务结果管理Service
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-29 下午03:37:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdjgService extends SuperServiceImpl<CdjgForm, CdjgDao> {

	/**
	 * @描述 场地信息服务接口
	 */
	private CdxxwhService cdxxwhService = new CdxxwhService();
	
	public CdjgService(){
		setDao(new CdjgDao());
	}
	
	/**
	 * 	获取场地结果信息			
	 * 
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-29 上午10:18:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getCdjgxx(String jgid){
		if(StringUtils.isNotBlank(jgid)){
			return dao.getCdjgxx(jgid);
		}
		return null;
	}
	/**
	 * 
	 * @描述:根据申请id删除结果表数据
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-30 上午09:11:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteJgBySqid(String sqid) throws Exception{
		return dao.deleteJgBySqid(sqid);
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:校验申请的时间段 > 1.申请人申请的时间必须没有被其他人申请！
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 上午10:41:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkSqSjd(CdjgForm model) throws Exception{
		boolean booleaVal = true;
		
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
		
		//申请人申请的时间必须没有被其他人申请
		if(cdxxModel != null){
			List<HashMap<String , String>> yscdsqList = dao.getYxCdsq(cdxxModel.getCdid()); //获取已经申请的场地使用列表
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

	public boolean updatePjxx(CdjgForm model) throws Exception {
		return dao.updatePjxx(model);
	}

	public boolean isExistPj(CdjgForm model) {
		return dao.isExistPj(model);
	}
}
