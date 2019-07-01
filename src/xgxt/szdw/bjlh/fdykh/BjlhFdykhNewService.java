/**
 * @部门:学工产品事业部
 * @日期：2014-2-14 上午10:42:02 
 */  
package xgxt.szdw.bjlh.fdykh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 辅导员考核统计
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-2-14 上午10:42:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjlhFdykhNewService extends
		SuperServiceImpl<BjlhFdykhForm, BjlhFdykhNewDao> {

	private BjlhFdykhNewDao dao = new BjlhFdykhNewDao();

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param dao
	 */
	public BjlhFdykhNewService() {
		super.setDao(dao);
	}
	
	/**
	 * 按人数统计
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByRS(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByRS(myForm, user);
	}
	
	/**
	 * 
	 * @描述: 按班级统计
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-17 上午11:21:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByBJ(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByBJ(myForm, user);
	}
	
	/**
	 * 
	 * @描述:按职责统计
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-18 下午02:08:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByZZ(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByZZ(myForm, user);
	}
	
	/**
	 * 
	 * @描述:统计明细查询
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-19 下午02:20:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] 返回类型 
	 * @throws
	 */
	public Object[] getFdyKhMx(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhMx(myForm, user);
	}
	
	/**
	 * 
	 * @描述:统计明细查询列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-20 上午09:46:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] 返回类型 
	 * @throws
	 */
	public Object[] getFdyKhxxMxDetail(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxMxDetail(myForm, user);
	}

	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-21 上午10:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByRSAll(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByRSAll(myForm, user);
	}

	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-21 上午10:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByBJAll(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByBJAll(myForm, user);
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-21 上午10:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByZZAll(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByZZAll(myForm, user);
	}
}
