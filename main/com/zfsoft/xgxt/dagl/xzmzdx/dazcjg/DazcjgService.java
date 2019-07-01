/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:53:53 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:54:21 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcjgService extends SuperServiceImpl<DazcjgForm,DazcjgDao>{
	
	/**
	 * @描述: 保存结果
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午04:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveFormDazcjg(DazcjgForm model,User user) throws Exception{
		boolean rs = true;
		
		/*生成唯一标识符*/
		String guid = UniqID.getInstance().getUniqIDHash();
		
		/*判断唯一键，学号(xh),项目名称(xmmc),参与时间(cysj)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*当前操作人员用户名塞入*/
		model.setSjlrr(user.getUserName());
		
		/*获取当前操作时间塞入表中，配合操作人工号防止老师耍赖*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
//		/*
//		 * 申请审核时：档案转成信息(dazcxx)直接插【1:已登记】
//		 * 
//		 * 结果增加时，档案转成信息(dazcxx)为【2:已转出】，条件:
//		 * 1、转出方式为邮寄，且邮寄状态为已邮寄
//		 * 2、转出方式为自带，且实际提档人不为空
//		 * 
//		 * 结果增加时，档案转成信息(dazcxx)为【1:已登记】，条件:
//		 * 1、转出方式为邮寄，且邮寄状态为未邮寄
//		 */
//		if("1".equals(model.getZcfs()) && "1".equals(model.getYjzt())){
//			model.setDazcxx("2");
//		}else if("1".equals(model.getZcfs()) && "2".equals(model.getYjzt())){
//			model.setDazcxx("1");
//		}else if("2".equals(model.getZcfs()) && StringUtils.isNotNull(model.getSjtdr())){
//			model.setDazcxx("2");
//		}else{
//			model.setDazcxx("");
//		}
//		
		/*
		 * 档案转成信息【1:已登记、2:已转出、3:未登记】
		 * 1、当转出方式为【邮寄】且邮寄状态为【已邮寄】，档案转成信息为【已转出】
		 * 2、当转出方式为【邮寄】且邮寄状态为【未邮寄】，档案转成信息为【已登记】
		 * 3、当转出方式为【自带】且实际提档人为【未填】，档案转成信息为【已登记】
		 * 4、当转出方式为【自带】且实际提档人为【已填】，档案转成信息为【已转出】
		 */
		if("1".equals(model.getZcfs()) && "1".equals(model.getYjzt())){
			model.setDazcxx("2");
		}else if("1".equals(model.getZcfs()) && "2".equals(model.getYjzt())){
			model.setDazcxx("1");
		}else if("2".equals(model.getZcfs()) && StringUtils.isNull(model.getSjtdr())){
			model.setDazcxx("1");
		}else if("2".equals(model.getZcfs()) && StringUtils.isNotNull(model.getSjtdr())){
			model.setDazcxx("2");
		}else{
			model.setDazcxx("");
		}
		
		
		/*判断该数据是否为修改数据*/
		if(StringUtils.isNotNull(model.getGuid())){
			
			/*当转出方式为【邮寄】的时候，修改操作要把自带承诺的值清空掉*/
			if("1".equals(model.getZcfs())){
				model.setZddacn("");
			}else{
				model.setYjzt("");
			}
			
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*塞入唯一ID*/
			model.setGuid(guid);
			/*1:申请审核、2:结果增加、3:导入*/
			model.setSjly("2");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 验证唯一性，学号(xh)
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午04:04:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcjgForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @描述: 通过guid查看学生档案转出相关信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午05:21:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String xh) throws Exception{
		return dao.getInfoByGuid(xh);
	}
	
	/**
	 * @描述: 根据学号查看结果表是否有数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 上午09:19:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getDazcjgRs(String xh) throws Exception{
		return dao.getDazcjgRs(xh);
	}
}
