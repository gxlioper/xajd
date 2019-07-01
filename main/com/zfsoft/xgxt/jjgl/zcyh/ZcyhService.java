/**
 * @部门:学工产品事业部
 * @日期：2014-8-28 上午11:09:23 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-28 上午11:09:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcyhService extends SuperServiceImpl<ZcyhForm, ZcyhDao> {

	/**
	 * 
	 * @描述:获取子女信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 上午11:16:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getZnxxMapById(String id) throws Exception{
		return dao.getZnxxMapById(id);
	}
	
	/**
	 * 
	 * @描述:获取注册用户信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 上午11:27:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getZcyhMapById(String id) throws Exception{
		return dao.getZcyhMapById(id);
	}
	
	/**
	 * 
	 * @描述:获取所有子女信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 上午11:19:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZnxxMapByYhm(String yhm) throws Exception{
		return dao.getZnxxMapByYhm(yhm);
	}
	
	/**
	 * 
	 * @描述:设置黑名单
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午03:11:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean szHmd(ZcyhForm model)throws Exception{
		model.setHmdsj(DateUtils.getCurrTime());
		return dao.szHmd(model);
	}
	
	/**
	 * 
	 * @描述:Cancel黑名单
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午03:11:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelHmd(ZcyhForm model)throws Exception{
		return dao.cancelHmd(model);
	}

	/**
	 * 家长信息增加的保存.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:52
	 */
	public boolean jzxxSaveForAdd(ZcyhForm zcyhForm) throws Exception {

		zcyhForm.setZt("0");
		zcyhForm.setZcsj(DateUtils.getCurrDate());
		boolean result = dao.jzxxSaveForAdd(zcyhForm);
		if(result){
			List<ZnxxModel> znxxModelList = zcyhForm.getZnxxModelList();
			result = dao.saveZnxx(znxxModelList,zcyhForm.getYhm());
		}
		return result;
	}

	/**
	 * 判断用户名是否已经被注册.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:14
	 */
	public boolean isYhmExist(ZcyhForm zcyhForm) {

		return dao.isYhmExist(zcyhForm.getYhm());
	}

	/**
	 * 判断身份证号是否已经被注册.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:14
	 */
	public boolean isSfzhExist(ZcyhForm zcyhForm) {

		return dao.isSfzhExist(zcyhForm);
	}

	/**
	 * 判断联系电话是否已经被注册.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:14
	 */
	public boolean isLxdhExist(ZcyhForm zcyhForm) {

		return dao.isLxdhExist(zcyhForm);
	}

	/**
	 * 家长信息修改的保存.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:52
	 */
	public boolean jzxxSaveForEdit(ZcyhForm zcyhForm) throws Exception {

		zcyhForm.setZt("0");
		zcyhForm.setZcsj(DateUtils.getCurrDate());
		boolean result = dao.jzxxSaveForEdit(zcyhForm);
		if(result){
			List<ZnxxModel> znxxModelList = zcyhForm.getZnxxModelList();
			result = dao.saveZnxx(znxxModelList,zcyhForm.getYhm());
		}
		return result;
	}

	/**
	 * 家长信息的删除，级联删除掉其子女信息.
	 *
	 * @param ids
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 17:06
	 */
	public int jzxxDel(String[] ids) throws Exception {

		int num = dao.runDelete(ids);
		if(num > 0){
			dao.delZnxx(ids);
		}
		return num;
	}

	/**
	 * 根据家长用户名查询子女信息列表
	 * @param sqr
	 * @return
	 */
	public List<HashMap<String,String>> getZnxxListByPid(String sqr) {

		return dao.getZnxxListByPid(sqr);
	}

	public String getMaxYhm() throws SQLException {
		return dao.getMaxYhm();
	}

}
