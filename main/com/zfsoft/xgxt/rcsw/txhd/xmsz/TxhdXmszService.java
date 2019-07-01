/**
 * @部门:学工产品事业部
 * @日期：2014-6-23 下午03:23:10 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import xgxt.action.base.BaseDAO;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.txhd.xmsh.TxhdXmShDao;
import com.zfsoft.xgxt.rcsw.txhd.xmsq.js.TxhdXmsqJsDao;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——学团活动
 * @类功能描述: 学团活动service
 * @作者： cq [工号:785]
 * @时间： 2014-6-23 下午03:23:10
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXmszService extends
		SuperServiceImpl<TxhdXmszForm, TxhdXmszDao> {

	/**
	 * 
	 * @描述:新增保存唯一性判断
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午01:47:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean isExistByXmwh(TxhdXmszForm from) throws Exception {

		String num = dao.checkExistForSave(from);
		return Integer.valueOf(num) > 0;

	}

	/** 
	 * @描述:判断申请表当中是否已存在
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午07:37:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistByXmsq(String values)throws Exception {
		return dao.isRalate(values);
	}
	
	/**
	 * 
	 * @描述:根据项目代码获得项目名称
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午07:54:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		return dao.getNameById(xmdm);
	}
	
	@Override
	public TxhdXmszForm getModel(TxhdXmszForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// 把学期代码转换为学期名称
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:判断申请、审核人数是否超过设置上限
	 * @作者：cq [工号：785]
	 * @日期：2014-6-28 下午01:31:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * void 返回类型 
	 * @throws
	 */
	public void sqrssx(TxhdXmszForm myForm) throws Exception{
		
		//判断申请人数是否超过设置申请人数
		if(!StringUtils.isBlank(myForm.getSqrssx())){
			//查看已申请人数
			TxhdXmsqJsDao txhdXmsqJsDao = new TxhdXmsqJsDao();
			String ysqrs = txhdXmsqJsDao.getSqrs(myForm.getXmdm());
			if(!StringUtils.isBlank(ysqrs)&&Integer.valueOf(myForm.getSqrssx())<Integer.valueOf(ysqrs)){
				throw new SystemException(MessageKey.RCSW_TXHD_RSKZ_FAIL_SQ,ysqrs);
			}
		}
		
		//判断审核人数是否超过设置审核人数
		
		if(!StringUtils.isBlank(myForm.getShrssx())){
			//查看审核已通过人数
			TxhdXmShDao txhdXmShDao = new TxhdXmShDao();
			String yshrs = txhdXmShDao.getTgrsByXmdm(myForm.getXmdm(), myForm.getRskzjb());
			if(!StringUtils.isBlank(yshrs)&&Integer.valueOf(myForm.getShrssx())<Integer.valueOf(yshrs)){
				throw new SystemException(MessageKey.RCSW_TXHD_RSKZ_FAIL_SH,yshrs);
			}
			
		}
		
	}
	
	
	/**
	 * 
	 * @描述:根据项目项目名称查询信息
	 * @作者：cq [工号：785]
	 * @日期：2014-6-30 上午09:34:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDateByName(String xmmc) throws Exception{
		return dao.getDateByName(xmmc);
	}

}
