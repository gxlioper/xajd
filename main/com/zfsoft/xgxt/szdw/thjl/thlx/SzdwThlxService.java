/**
 * @部门:学工产品事业部
 * @日期： 2014-10-8 上午11:40:22
 */  
package com.zfsoft.xgxt.szdw.thjl.thlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍-谈话记录维护-谈话类型
 * @类功能描述: 
 * @作者： 江水才[工号:1150]
 * @时间： 2014-10-8 上午11:40:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwThlxService extends SuperServiceImpl<SzdwThlxForm, SzdwThlxDao>{

	public SzdwThlxService() {
		super.setDao(new SzdwThlxDao());
	}
	
	/** 
	 * @描述: 谈话类型代码是否存在
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-8 上午11:40:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 */
	public boolean thlxIsExist(SzdwThlxForm model) {
		return dao.thlxIsExist(model);
	}

	/**
	 * @描述:获取全部谈话类型
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-8 上午11:40:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllThlxList() throws Exception{
		return dao.getAllList(new SzdwThlxForm());
	}

	public List<HashMap<String,String>> getKhwtPageList(SzdwThlxForm model) throws Exception {
		return dao.getKhwtPageList(model);

	}

    public List<HashMap<String,String>> getWtmsPageList(SzdwThlxForm model) throws Exception {
		return dao.getWtmsPageList(model);
    }

	public List<HashMap<String,String>> getTgbzPageList(SzdwThlxForm model) throws Exception {
		return dao.getTgbzPageList(model);
	}

	public boolean addThlx(SzdwThlxForm model)   {
		boolean rs = false;
		try {
			rs = dao.runInsert(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean addKhwt(SzdwThlxForm model)   {
		boolean rs = false;
		try {
			rs = dao.addKhwt(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean addWtms(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs = dao.addWtms(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean addTgbz(SzdwThlxForm model)  {
		boolean rs = false;
		try {
			rs =  dao.addTgbz(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public HashMap<String,String> getKhwtListByThlx(String ssthlx) {
		return dao.getKhwtListByThlx(ssthlx);
	}

	/**
	 * @描述:获取困惑和问题信息
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 17:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getKhwtInfo(SzdwThlxForm model) {
		return dao.getKhwtInfo(model);
	}
	/**
	 * @描述:获取问题描述信息
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 17:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getWtmsInfo(SzdwThlxForm model) {
		return dao.getWtmsInfo(model);
	}
	/**
	 * @描述:获取提供帮助信息
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/11 17:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getTgbzInfo(SzdwThlxForm model) {
		return dao.getTgbzInfo(model);
	}

	/**
	 * @描述:修改困惑和问题
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/12 10:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean updateKhwt(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs =  dao.updateKhwt(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * @描述:修改问题描述
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/12 10:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean updateWtms(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs =  dao.updateWtms(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * @描述:修改提供帮助
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/12 10:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean updateTgbz(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs =  dao.updateTgbz(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * @描述：查看名称是否存在
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/12 10:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model, tableName]
	 * @return: boolean
	 */
	public boolean isExit(SzdwThlxForm model, String tableName) throws SQLException {
		int count = dao.getCount(model,tableName);
		return count == 0;
	}

	public int delete(String[] lxdms, String tableName) throws Exception {
		return dao.delete(lxdms,tableName);
	}

	public List<HashMap<String,String>> getWtmsListByKhwt(String sskhwt) {
		return dao.getWtmsListByKhwt(sskhwt);
	}

	public List<HashMap<String,String>> getAllTgbz() {
		return dao.getAllTgbz();
	}

	/**
	 * @描述:获取所有帮助结果
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/13 11:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getAllBzjg() {
		return dao.getAllBzjg();
	}

	/**
	 * @描述:根据代码数组获取帮助结果名称
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/13 11:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public String[] getBzjgmcByDms(String[] dms) throws SQLException {
		return dao.getBzjgmcByDms(dms);
	}
}
