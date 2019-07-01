/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 上午10:50:33 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.attachupload.model.FileInfo;
import com.zfsoft.xgxt.comm.attachupload.service.FileInfoService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地信息维护Service 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 上午10:50:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdxxwhService extends SuperServiceImpl<CdxxwhForm, CdxxwhDao> {

	/**
	 * @throws Exception 
	 * 
	 * @描述:获取popup场地信息列表信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午01:45:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPopupCdxx(CdxxwhForm model) throws Exception{
		return dao.getPopupCdxx(model);
	}
	
	/**
	 * 
	 * @描述:获取开放开关列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午02:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getKfkgList(){
		/**
		 *  固定选项工具类
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> xzkgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return xzkgList;
	}
	
	/**
	 * 
	 * @描述:根据场地ID获取场地信息Map
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午04:21:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getCdxxByCdid(String cdid){
		HashMap<String , String> cdxx = dao.getCdxxByCdid(cdid);
		// ========== 协议文件是否存在 begin =============
		String xysfilepathGid = cdxx.get("xysfilepath");
		if(xysfilepathGid != null && !"".equals(xysfilepathGid)){
			FileInfoService fileInfoService  = new FileInfoService();
			List<FileInfo> infoList = fileInfoService.getFileInfoList(xysfilepathGid);
			if(infoList.size() == 0){
				cdxx.put("xysfilepath","");
			}else{
				cdxx.put("xysfilepathfid",infoList.get(0).getFid());
			}
		}
		// ========== 协议文件是否存在 end =============
		return cdxx;
	}
	
	/**
	 * 
	 * @描述:根据场地名称获取场地数
	 */
	public int getCdsByCdmc(String cdmc){
		return dao.getCdsByCdmc(cdmc);
	}
	
	
	/**
	 * 
	 * @描述:根据场地id检查是否被申请
	 */
	public boolean check(String cdid){
		return dao.check(cdid);
	}
	
	/**
	 * @描述 ：构造方法 初始化DAO
	 */
	public CdxxwhService() {
		super();
		super.setDao(new CdxxwhDao());
	}

	/**
	 * 
	 * @描述: 获取场地信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-17 下午03:44:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCdxx(String sqid,String tableName){
		return dao.getCdxx(sqid,tableName);
	}
	
	/**
	 * 
	 * @描述: 获取审核意见
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-17 下午04:24:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjList(String ywid){
		return dao.getShyjList(ywid);
	}
}
