/**
 * @部门:学工产品事业部
 * @日期：2014-6-20 上午11:21:53 
 */
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-学团活动
 * @类功能描述: 学团活动Service
 * @作者： cq [工号:785]
 * @时间： 2014-6-20 上午11:21:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdDmwhService extends
		SuperServiceImpl<TxhdDmwhForm, TxhdDmwhDao> {

	/**
	 * 
	 * @描述:获得下一个类别代码
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午02:41:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException int 返回类型
	 * @throws
	 */
	public int getNextLbdm() throws SQLException {

		int maxLbdm = 0;
		maxLbdm = dao.getMaxLbdm() + 1;

		return maxLbdm;
	}

	/**
	 * 
	 * @描述:判断类别代码在结果当中是否存在
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 下午04:13:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String checkLbForJg(String value) throws Exception {

		String resultLbmc = "";
		String[] lbmc = dao.lbCheckExistForJg(value);
		for (int i = 0; i < lbmc.length; i++) {
			if (i == lbmc.length - 1) {
				resultLbmc += lbmc[i];
			} else {
				resultLbmc += lbmc[i] + ",";
			}
		}
		return resultLbmc;
	}

	/**
	 * 
	 * @描述:判断类别代码在项目维护当中是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午02:48:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public String checkLbForXmwh(String value) throws Exception {

		String resultLbmc = "";
		String[] lbmc = dao.lbCheckExistForXmwh(value);
		for (int i = 0; i < lbmc.length; i++) {
			if (i == lbmc.length - 1) {
				resultLbmc += lbmc[i];
			} else {
				resultLbmc += lbmc[i] + ",";
			}
		}
		return resultLbmc;
	}

	/**
	 * 
	 * @描述:判断类别名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午02:54:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean isExistByLbmc(TxhdDmwhForm form) {

		boolean flag = false;

		String num = dao.lbmcCheckExist(form);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	
	
	/**
	 * 
	 * @描述:获取类别list
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 上午11:59:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLblist() throws Exception {
		return dao.getLblist();
	}
	
	public List<HashMap<String, String>> getHdggList(TxhdDmwhForm t)
	   throws Exception {
		return dao.getHdggList(t);
    }
	
	//活动规格重复验证
	public boolean checkIsExits(TxhdDmwhForm t){
		return dao.checkIsExits(t);
	}
	
	//活动规格新增保存
	public boolean saveHdgg(TxhdDmwhForm t) throws Exception{
		return dao.saveHdgg(t);
	}
	
	//活动规格新增保存
	public boolean saveUpdateHdgg(TxhdDmwhForm t) throws Exception{
		return dao.saveUpdateHdgg(t);
	}
	
	//删除
	public int delHdgg(String[] hdggdms) throws Exception{
		return dao.delHdgg(hdggdms);
	}

	//获取活动规格列表
	public List<HashMap<String, String>> getHdggList()  throws Exception{
		return dao.getHdggList();
	}
	

	//获取活动规格名称
	public String getHdggmc(String hdggdm){
		return dao.getHdggmc(hdggdm);
	}

}
